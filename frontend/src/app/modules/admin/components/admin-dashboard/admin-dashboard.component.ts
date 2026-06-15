import { Component, OnInit } from '@angular/core';
import { forkJoin } from 'rxjs';
import { DestinationService } from '../../../../core/services/destination.service';
import { ReservationService } from '../../../../core/services/reservation.service';
import { AuthService } from '../../../../core/services/auth.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit {
  loading = true;
  stats = {
    totalDestinations: 0,
    totalReservations: 0,
    confirmeesCount:   0,
    enAttenteCount:    0,
    annuleesCount:     0,
    totalTouristes:    0
  };
  recentReservations: any[] = [];

  constructor(
    private destinationService: DestinationService,
    private reservationService: ReservationService,
    public authService: AuthService
  ) {}

  ngOnInit(): void {
    forkJoin({
      destinations: this.destinationService.findAll(),
      reservations: this.reservationService.findAll()
    }).subscribe({
      next: ({ destinations, reservations }) => {
        this.stats.totalDestinations = destinations.length;
        this.stats.totalReservations = reservations.length;
        this.stats.confirmeesCount   = reservations.filter(r => r.statut === 'CONFIRMEE').length;
        this.stats.enAttenteCount    = reservations.filter(r => r.statut === 'EN_ATTENTE').length;
        this.stats.annuleesCount     = reservations.filter(r => r.statut === 'ANNULEE').length;
        this.stats.totalTouristes    = new Set(reservations.map(r => r.userEmail)).size;
        this.recentReservations      = reservations.slice(0, 5);
        this.loading = false;
      },
      error: () => { this.loading = false; }
    });
  }

  getStatutClass(s: string): string {
    return ({'EN_ATTENTE':'en-attente','CONFIRMEE':'confirmee','ANNULEE':'annulee'} as any)[s] || '';
  }
  getStatutLabel(s: string): string {
    return ({'EN_ATTENTE':'⏳ En attente','CONFIRMEE':'✅ Confirmée','ANNULEE':'❌ Annulée'} as any)[s] || s;
  }
}