import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Hotel } from '../models/hotel';

@Injectable({
  providedIn: 'root'
})
export class HotelService {

  private apiUrl = 'http://localhost:8081/api/hotels';

  constructor(private http: HttpClient) {}

  findByDestination(destinationId: number): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(
      `${this.apiUrl}/destination/${destinationId}`
    );
  }
  findAll(): Observable<Hotel[]> {
  return this.http.get<Hotel[]>(this.apiUrl);
}

save(hotel: Hotel): Observable<Hotel> {
  return this.http.post<Hotel>(this.apiUrl, hotel);
}

delete(id: number): Observable<any> {
  return this.http.delete(`${this.apiUrl}/${id}`);
}
}