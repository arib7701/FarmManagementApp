import { Injectable } from '@angular/core';
import { Delivery } from '../models/delivery';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

const BASE_URL = 'http://localhost:8181/api/v1/deliveries';

@Injectable({
  providedIn: 'root'
})
export class DeliveryService {
  constructor(private http: HttpClient) {}

  getAllDelivery(): Observable<Delivery[]> {
    return this.http.get<Delivery[]>(`${BASE_URL}`);
  }

  getAllDeliveryByAnimalId(animalId: number): Observable<Delivery[]> {
    return this.http.get<Delivery[]>(`${BASE_URL}/animal/${animalId}`);
  }

  getDeliveryById(id: number): Observable<Delivery> {
    return this.http.get<Delivery>(`${BASE_URL}/${id}`);
  }

  getDeliveryByDate(date: Date): Observable<Delivery> {
    return this.http.get<Delivery>(`${BASE_URL}/date/${date}`);
  }

  addDelivery(delivery: Delivery): Observable<Delivery> {
    return this.http.post<Delivery>(`${BASE_URL}`, delivery);
  }

  updateDelivery(id: number, delivery: Delivery): Observable<Delivery> {
    return this.http.post<Delivery>(`${BASE_URL}/${id}`, delivery);
  }

  removeDeliveryById(id: number): Observable<string> {
    return this.http.delete(`${BASE_URL}/${id}`, {
      responseType: 'text'
    });
  }

  removeDeliveryByAnimalId(animalId: number): Observable<string> {
    return this.http.delete(`${BASE_URL}/animal/${animalId}`, {
      responseType: 'text'
    });
  }

  removeDeliveryByDate(date: Date): Observable<string> {
    return this.http.delete(`${BASE_URL}/date/${date}`, {
      responseType: 'text'
    });
  }
}
