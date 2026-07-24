import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { Tutorial } from '../models/tutorial.model';
import { ApiResponse } from '../interface/apiresponse.interface';

@Injectable({
  providedIn: 'root',
})

export class TutorialService {
  private baseUrl = 'https://52.39.126.139/api/tutorials';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Tutorial[]> {    
    return this.http.get<any>(this.baseUrl).pipe(
      map(response => response.data)
    );
  }

  get(id: any): Observable<Tutorial> {
    return this.http.get<ApiResponse<Tutorial>>(`${this.baseUrl}/${id}`).pipe(
      map(response => response.data)
    );
  }

  create(data: any): Observable<any> {
    return this.http.post(this.baseUrl, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

}
