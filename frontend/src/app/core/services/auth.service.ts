import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { JwtResponse, LoginRequest, RegisterRequest } from '../models/user';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private apiUrl = 'http://localhost:8081/api/auth';
  private currentUserSubject = new BehaviorSubject<JwtResponse | null>(this.getFromStorage());
  currentUser$ = this.currentUserSubject.asObservable();

  constructor(private http: HttpClient, private router: Router) {}

  private getFromStorage(): JwtResponse | null {
    const u = localStorage.getItem('currentUser');
    return u ? JSON.parse(u) : null;
  }

  get currentUser() { return this.currentUserSubject.value; }
  get isLoggedIn()  { return !!this.currentUser; }
  get isAdmin()     { return this.currentUser?.role === 'ADMIN'; }
  get token()       { return this.currentUser?.token ?? null; }

  login(req: LoginRequest): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(`${this.apiUrl}/login`, req).pipe(
      tap(r => { localStorage.setItem('currentUser', JSON.stringify(r)); this.currentUserSubject.next(r); })
    );
  }

  register(req: RegisterRequest): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(`${this.apiUrl}/register`, req).pipe(
      tap(r => { localStorage.setItem('currentUser', JSON.stringify(r)); this.currentUserSubject.next(r); })
    );
  }

  logout(): void {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
    this.router.navigate(['/auth/login']);
  }
}