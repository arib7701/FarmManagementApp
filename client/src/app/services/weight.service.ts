import { Injectable } from '@angular/core';
import { Weight } from '../models/weight';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

// const BASE_URL = 'http://localhost:8181/api/v1/weights/';
const BASE_URL = 'http://localhost:8181/api/v1/weights';

@Injectable({
  providedIn: 'root'
})
export class WeightService {
  constructor(private http: HttpClient) {}

  getAllWeight(): Observable<Weight[]> {
    return this.http.get<Weight[]>(`${BASE_URL}`);
  }

  getAllWeightByAnimalId(animalId: number): Observable<Weight[]> {
    return this.http.get<Weight[]>(`${BASE_URL}/animal/${animalId}`);
  }

  getWeightById(id: number): Observable<Weight> {
    return this.http.get<Weight>(`${BASE_URL}/${id}`);
  }

  getWeightByDate(date: Date): Observable<Weight> {
    return this.http.get<Weight>(`${BASE_URL}/date/${date}`);
  }

  addWeight(weight: Weight): Observable<Weight> {
    return this.http.post<Weight>(`${BASE_URL}`, weight);
  }

  updateWeight(id: number, weight: Weight): Observable<Weight> {
    return this.http.post<Weight>(`${BASE_URL}/${id}`, weight);
  }

  removeWeightById(id: number): Observable<string> {
    return this.http.delete(`${BASE_URL}/${id}`, {
      responseType: 'text'
    });
  }

  removeWeightByAnimalId(animalId: number): Observable<string> {
    return this.http.delete(`${BASE_URL}/animal/${animalId}`, {
      responseType: 'text'
    });
  }

  removeWeightByDate(date: Date): Observable<string> {
    return this.http.delete(`${BASE_URL}/date/${date}`, {
      responseType: 'text'
    });
  }
}
