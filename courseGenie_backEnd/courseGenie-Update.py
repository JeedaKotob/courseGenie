import requests
import json
import re
import mysql.connector

# API and search configuration
API_URL = "https://tigercenter.rit.edu:443/tigerCenterApp/tc/class-search"
COOKIES = {
    "_ga": "GA1.1.206192597.1705604006",
    "_ga": "GA1.3.206192597.1705604006",
    "_gid": "GA1.3.444933135.1705687646",
    "_opensaml_req_ss%3Amem%3A54ce6f981566f34a7667f5573d75c732638110c763648b0bf9337045fd2bf4b1": "_6062c38eb18dcb5318c0526f0dd6fd7b",
    "_gat_UA-10681416-1": "1",
    "_opensaml_req_ss%3Amem%3A9a0244a492cb21be06571467c1348cbaeb07e9fc0e9cbe820fda410d5843e534": "_69c09c50ecc0a903bab9e4bc2c1dab1c",
    "_opensaml_req_ss%3Amem%3A66afe6607208c04eda3b31e21fc83b24153d1d3748d7fbc5b01e2101c23ce441": "_fb7a4bd34ed3de096d60e277ad725fb2",
    "_ga_DY6XXZS0V2": "GS1.1.1705687641.2.1.1705689344.47.0.0",
    "_ga_5GBLS4Z7EL": "GS1.1.1705687641.2.1.1705689373.0.0.0"
}
HEADERS = {
    "Sec-Ch-Ua": "\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\"",
    "Accept": "application/json, text/plain, */*",
    "Content-Type": "application/json;charset=UTF-8",
    "Sec-Ch-Ua-Mobile": "?0",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.6099.216 Safari/537.36",
    "Sec-Ch-Ua-Platform": "\"macOS\"",
    "Origin": "https://tigercenter.rit.edu",
    "Sec-Fetch-Site": "same-origin",
    "Sec-Fetch-Mode": "cors",
    "Sec-Fetch-Dest": "empty",
    "Referer": "https://tigercenter.rit.edu/tigerCenterApp/api/class-search",
    "Accept-Encoding": "gzip, deflate, br",
    "Accept-Language": "en-US,en;q=0.9",
    "Priority": "u=1, i",
    "Connection": "close"
}

values = [
    'ACCT ', 'DECS ', 'FINC ', 'INTB ', 'MGIS ', 'SCBI ', 'MGMT ', 'MKTG ',
    'EGEN ', 'CMPR ', 'EEEE ', 'ISEE ', 'MECE ', 'GCIS ', 'ISTE ', 'NSSA ',
    'SWEN ', 'CSEC ', 'CSCI ', 'PUBL ', 'ANTH ', 'COMM ', 'ECON ', 'ELCA ',
    'ENGL ', 'HIST ', 'MLAR ', 'MLFR ', 'MLSP ', 'PHIL ', 'PSYC ', 'UWRT ',
    'BIOG ', 'BIOL ', 'CHMG ', 'MATH ', 'PHYS ', 'STAT ', 'ACSC ', 'YOPS '
]

def get_course_data(url, headers, cookies,value):
    """
    Query Tiger Center API using the provided course code (query_value).
    Returns a JSON response as a Python dictionary.
    """
    data = {
        "searchParams": {
            "campus": "DUBAI",
            "career": None,
            "college": None,
            "component": None,
            "courseAttributeOptions": [],
            "courseAttributeOptionsPassed": [],
            "creditsMax": None,
            "creditsMin": None,
            "filterAnd": True,
            "instructionType": None,
            "instructor": None,
            "isAdvanced": True,
            "precision": None,
            "query": value,
            "session": None,
            "subject": None,
            "term": "2235"
        }
    }
    session = requests.session()
    response = session.post(url, headers=headers, cookies=cookies, json=data)

    return json.loads(response.text)


def parse_course_data(courses_data):
    """
    Parses the API response for courses and extracts necessary fields for database insertion.
    """
    parsed_courses = []
    for course in courses_data.get('searchResults', []):
        parsed_course = {
            'subject_catalogNumber': f"{course.get('subject', '')}-{course.get('catalogNumber', '')}",
            'maximumUnits': course.get('maximumUnits'),
            'courseDescription': course.get('courseDescription'),
            'courseTitleLong': course.get('courseTitleLong'),
            'term': course.get('term'),
            'academicGroupShort': course.get('academicGroupShort'),
            'graduate': course.get('academicCareer') == 'GRAD',
            'undergraduate': course.get('academicCareer') != 'GRAD',
            'preReqDescrsLong': course.get('preReqDescrsLong')
        }
        parsed_courses.append(parsed_course)
    return parsed_courses



# MySQL connection configuration (update with your actual credentials)
DB_CONFIG = {
    'host': 'localhost',
    'port': 3306,
    'user': 'root',
    'password': 'tiger',
    'database': 'course_genie'
}


def update_course_data(parsed_courses, cursor):
    """
    Updates or inserts course records in the database based on parsed data.
    """
    for course in parsed_courses:
        cursor.execute("""
            INSERT INTO courses (subject_catalogNumber, maximumUnits, courseDescription, courseTitleLong, 
            term, academicGroupShort, graduate, undergraduate, preReqDescrsLong)
            VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)
            ON DUPLICATE KEY UPDATE
            maximumUnits=VALUES(maximumUnits), courseDescription=VALUES(courseDescription), 
            courseTitleLong=VALUES(courseTitleLong), term=VALUES(term), academicGroupShort=VALUES(academicGroupShort),
            graduate=VALUES(graduate), undergraduate=VALUES(undergraduate), preReqDescrsLong=VALUES(preReqDescrsLong)
        """, (
            course['subject_catalogNumber'],
            course['maximumUnits'],
            course['courseDescription'],
            course['courseTitleLong'],
            course['term'],
            course['academicGroupShort'],
            course['graduate'],
            course['undergraduate'],
            course['preReqDescrsLong']
        ))



def process_courses():
    """
    Fetches each course, queries the API, parses the response, and updates the database.
    """
    connection = mysql.connector.connect(**DB_CONFIG)
    cursor = connection.cursor()
    try:
        for code in values:
            print(f"Processing course code: {code}")
            courses_data = get_course_data(API_URL, HEADERS, COOKIES, code.strip())
            parsed_courses = parse_course_data(courses_data)
            if parsed_courses:
                update_course_data(parsed_courses, cursor)
        connection.commit()
    except Exception as e:
        print(f"An error occurred: {e}")
    finally:
        cursor.close()
        connection.close()

if __name__ == '__main__':
    process_courses()



def main():
    for value in values:
        course_data = get_course_data(API_URL,HEADERS,COOKIES,value)
        print(course_data)


if __name__ == '__main__':
    main()