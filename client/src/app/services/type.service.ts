import { Injectable } from '@angular/core';
import { Type } from '../models/type';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

// const BASE_URL = 'http://localhost:8181/api/v1/types/';
const BASE_URL = '/api/v1/types/';

@Injectable({
  providedIn: 'root'
})
export class TypeService {
  constructor(private http: HttpClient) {}

  getAllType(): Observable<Type[]> {
    return this.http.get<Type[]>(`${BASE_URL}`);
  }

  getTypeById(id: number): Observable<Type> {
    return this.http.get<Type>(`${BASE_URL}/${id}`);
  }

  getTypeByName(name: string): Observable<Type> {
    return this.http.get<Type>(`${BASE_URL}/${name}`);
  }

  addType(type: Type): Observable<Type> {
    return this.http.post<Type>(`${BASE_URL}`, type);
  }

  updateType(id: number, type: Type): Observable<Type> {
    return this.http.post<Type>(`${BASE_URL}/${id}`, type);
  }

  removeTypeById(id: number): Observable<string> {
    return this.http.delete(`${BASE_URL}/${id}`, {
      responseType: 'text'
    });
  }

  removeTypeByName(name: number): Observable<string> {
    return this.http.delete(`${BASE_URL}/${name}`, {
      responseType: 'text'
    });
  }
}
