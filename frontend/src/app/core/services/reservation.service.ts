import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private apiUrl = 'http://localhost:8081/api/reservations';

  constructor(private http: HttpClient) {}

  findAll(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  getMesReservations(): Observable<any[]> {
    return this.http.get<any[]>(
      `${this.apiUrl}/mes-reservations`
    );
  }

  save(data: any): Observable<any> {
    return this.http.post(this.apiUrl, data);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  updateStatut(id: number, statut: string): Observable<any> {
    return this.http.patch(
      `${this.apiUrl}/${id}/statut?statut=${statut}`,
      {}
    );
  }
}