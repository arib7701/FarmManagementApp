import { Injectable } from '@angular/core';
import { Animal } from '../models/animal';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

// const BASE_URL = 'http://localhost:8181/api/v1/animals/';
const BASE_URL = 'http://localhost:8181/api/v1/animals';

@Injectable({
  providedIn: 'root'
})
export class AnimalService {
  constructor(private http: HttpClient) {}

  getAllAnimal(): Observable<Animal[]> {
    return this.http.get<Animal[]>(`${BASE_URL}`);
  }

  getAllAnimalByType(type: number): Observable<Animal[]> {
    return this.http.get<Animal[]>(`${BASE_URL}/type2/${type}`);
  }

  getAllAnimalAliveByType(type: number): Observable<Animal[]> {
    return this.http.get<Animal[]>(`${BASE_URL}/type3/${type}`);
  }

  getAnimalById(id: number): Observable<Animal> {
    return this.http.get<Animal>(`${BASE_URL}/${id}`);
  }

  getAnimalByName(name: String): Observable<Animal> {
    return this.http.get<Animal>(`${BASE_URL}/${name}`);
  }

  addAnimal(animal: Animal): Observable<Animal> {
    return this.http.post<Animal>(`${BASE_URL}`, animal);
  }

  updateAnimal(id: number, animal: Animal): Observable<Animal> {
    return this.http.post<Animal>(`${BASE_URL}/${id}`, animal);
  }

  removeAnimalById(id: number): Observable<string> {
    return this.http.delete(`${BASE_URL}/${id}`, {
      responseType: 'text'
    });
  }

  removeAnimalByName(name: number): Observable<string> {
    return this.http.delete(`${BASE_URL}/${name}`, {
      responseType: 'text'
    });
  }
}
