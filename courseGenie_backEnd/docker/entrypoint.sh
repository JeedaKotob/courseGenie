#!/bin/bash

# Admin credentials
LDAP_ADMIN_USER="cn=admin,dc=course,dc=genie"
LDAP_ADMIN_PASSWORD="root"

# LDAP URL
LDAP_URL="ldap://localhost:389"

# Timeout for waiting
TIMEOUT=60
WAIT_TIME=5
TRIES=0

# Function to check if the server is available
wait_for_ldap() {
    while ! ldapsearch -x -LLL -H $LDAP_URL -D "$LDAP_ADMIN_USER" -w $LDAP_ADMIN_PASSWORD -b "dc=course,dc=genie" > /dev/null 2>&1; do
        if [ $TRIES -ge $TIMEOUT ]; then
            echo "Timed out waiting for LDAP server to start"
            exit 1
        fi
        echo "Waiting for LDAP server to start... attempt $TRIES/$TIMEOUT"
        ((TRIES++))
        sleep $WAIT_TIME
    done
    echo "LDAP server is up!"
}

# Function to check if a user exists
user_exists() {
    ldapsearch -x -LLL -H $LDAP_URL -D "$LDAP_ADMIN_USER" -w $LDAP_ADMIN_PASSWORD -b "ou=Users,dc=course,dc=genie" "(uid=$1)" | grep "dn: uid=$1" > /dev/null
}

# Function to add user if they don't exist
add_user_if_not_exists() {
    local USERNAME=$1
    local LDIF_FILE=$2

    # Check if user already exists
    if user_exists $USERNAME; then
        echo "User $USERNAME already exists. Skipping insertion."
    else
        echo "Adding user $USERNAME..."
        ldapadd -x -D "$LDAP_ADMIN_USER" -w $LDAP_ADMIN_PASSWORD -f $LDIF_FILE
    fi
}

# Wait for the LDAP server to be ready
wait_for_ldap

# Add users
add_user_if_not_exists "user1" "/scripts/user1.ldif"
add_user_if_not_exists "user2" "/scripts/user2.ldif"
