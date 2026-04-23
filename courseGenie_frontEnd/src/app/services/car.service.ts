import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Car } from '../home/course.model';

@Injectable({
  providedIn: 'root'
})
export class CarService {
  apiUrl: string = `${environment.apiUrl}/cars`;

  constructor(private http: HttpClient) {}

  getCarBySection(sectionId: number): Observable<Car> {
    return this.http.get<Car>(`${this.apiUrl}/section/${sectionId}`);
  }

  updateCar(car: Car): Observable<Car> {
    return this.http.put<Car>(`${this.apiUrl}/update`, car);
  }

  getCarHtml(sectionId: number): Observable<string> {
    return this.http.get(`${this.apiUrl}/section/${sectionId}/html`, {
      responseType: 'text'
    });
  }

  submitCar(carId: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/submit/${carId}`, null);
  }
}
