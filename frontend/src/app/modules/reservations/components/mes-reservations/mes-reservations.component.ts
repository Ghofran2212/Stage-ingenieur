import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../../../../core/services/reservation.service';

@Component({
  selector: 'app-mes-reservations',
  templateUrl: './mes-reservations.component.html',
  styleUrls: ['./mes-reservations.component.scss']
})
export class MesReservationsComponent implements OnInit {

  reservations: any[] = [];
  loading = true;
  error = '';

  constructor(private reservationService: ReservationService) {}

  ngOnInit(): void {
    this.loadReservations();
  }

  loadReservations(): void {
    this.reservationService.getMesReservations().subscribe({
      next: (data) => {
        this.reservations = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'Erreur lors du chargement des réservations';
        this.loading = false;
      }
    });
  }

  deleteReservation(id: number): void {
    if (!confirm('Supprimer cette réservation ?')) {
      return;
    }

    this.reservationService.delete(id).subscribe({
      next: () => {
        this.reservations = this.reservations.filter(r => r.id !== id);
      }
    });
  }
}