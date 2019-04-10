import { Injectable } from '@angular/core';
import { Mating } from '../models/mating';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

const BASE_URL = 'http://localhost:8181/api/v1/matings';

@Injectable({
  providedIn: 'root'
})
export class MatingService {
  constructor(private http: HttpClient) {}

  getAllMating(): Observable<Mating[]> {
    return this.http.get<Mating[]>(`${BASE_URL}`);
  }

  getAllMatingByAnimalId(animalId: number): Observable<Mating[]> {
    return this.http.get<Mating[]>(`${BASE_URL}/animal/${animalId}`);
  }

  getMatingById(id: number): Observable<Mating> {
    return this.http.get<Mating>(`${BASE_URL}/${id}`);
  }

  getMatingByDate(date: Date): Observable<Mating> {
    return this.http.get<Mating>(`${BASE_URL}/date/${date}`);
  }

  addMating(mating: Mating): Observable<Mating> {
    return this.http.post<Mating>(`${BASE_URL}`, mating);
  }

  updateMating(id: number, mating: Mating): Observable<Mating> {
    return this.http.post<Mating>(`${BASE_URL}/${id}`, mating);
  }

  removeMatingById(id: number): Observable<string> {
    return this.http.delete(`${BASE_URL}/${id}`, {
      responseType: 'text'
    });
  }

  removeMatingByAnimalId(animalId: number): Observable<string> {
    return this.http.delete(`${BASE_URL}/animal/${animalId}`, {
      responseType: 'text'
    });
  }

  removeMatingByDate(date: Date): Observable<string> {
    return this.http.delete(`${BASE_URL}/date/${date}`, {
      responseType: 'text'
    });
  }
}
