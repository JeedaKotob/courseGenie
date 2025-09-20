import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface Book {
  id: number;
  isbn: string;
  title: string;
  url: string;
  sectionIds: number[];
  requiredReading: boolean;
  suggestedReading: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private apiUrl = `${environment.apiUrl}/books`;

  constructor(private http: HttpClient) {}

  getAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(this.apiUrl);
  }

  importBook(isbn: string): Observable<Book> {
    const url = `${this.apiUrl}/import?isbn=${isbn}`;
    return this.http.post<Book>(url, {});
  }

  updateBook(bookId: number, book: Book): Observable<Book> {
    const url = `${this.apiUrl}/${bookId}`;
    return this.http.put<Book>(url, book);
  }

  updateMapping(bookId: number, sectionId: number, mapped: boolean, requiredReading: boolean, suggestedReading: boolean): Observable<Book> {
    const url = `${this.apiUrl}/${bookId}/updateMapping?sectionId=${sectionId}&mapped=${mapped}&requiredReading=${requiredReading}&suggestedReading=${suggestedReading}`;
    return this.http.put<Book>(url, {});
  }
}
