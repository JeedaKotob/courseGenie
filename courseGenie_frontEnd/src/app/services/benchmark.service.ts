import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Benchmark, CLO, Course, CoursesBySemester } from '../home/course.model';

@Injectable({
    providedIn: 'root'
})
export class BenchmarkService {
    apiUrl: string = `${environment.apiUrl}/benchmarks`;
    constructor(private http: HttpClient) { }

    findAll(): Observable<Benchmark[]> {
        return this.http.get<Benchmark[]>(`${this.apiUrl}`);
    }

    getBenchmarkResults(sectionId: number | undefined, bm1Id: number | null, bm2Id: number | null): Observable<any> {
        return this.http.get<any>(`${this.apiUrl}/section/${sectionId}/firstBm/${bm1Id}/secondBm/${bm2Id}`);
    }

    getBenchmarkReflection(sectionId: number): Observable<{ reflection: string | null }> {
        return this.http.get<{ reflection: string | null }>(`${this.apiUrl}/section/${sectionId}/reflection`);
    }

    saveBenchmarkReflection(sectionId: number, reflection: string): Observable<{ reflection: string | null }> {
        return this.http.put<{ reflection: string | null }>(`${this.apiUrl}/section/${sectionId}/reflection`, { reflection });
    }
}
