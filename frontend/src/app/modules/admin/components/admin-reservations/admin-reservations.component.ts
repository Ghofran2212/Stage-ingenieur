import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../../../../core/services/reservation.service';
import { ReservationResponse } from '../../../../core/models/reservation';

@Component({
  selector: 'app-admin-reservations',
  templateUrl: './admin-reservations.component.html',
  styleUrls: ['./admin-reservations.component.scss']
})
export class AdminReservationsComponent implements OnInit {
  reservations: ReservationResponse[] = [];
  filtered:     ReservationResponse[] = [];
  loading    = true;
  error      = '';
  successMsg = '';
  filterStatut = '';

  constructor(private reservationService: ReservationService) {}

  ngOnInit(): void { this.load(); }

  load(): void {
  this.loading = true;

  this.reservationService.findAll().subscribe({
    next: (data) => {
      console.log('RESERVATIONS ADMIN =', data);

      this.reservations = data;
      this.applyFilter();
      this.loading = false;
    },
    error: (err) => {
      console.log('ERREUR ADMIN =', err);

      this.loading = false;
      this.error = 'Erreur chargement.';
    }
  });
}

  applyFilter(): void {
    this.filtered = this.filterStatut
      ? this.reservations.filter(r => r.statut === this.filterStatut)
      : [...this.reservations];
  }

  updateStatut(id: number, statut: string): void {
    this.reservationService.updateStatut(id, statut).subscribe({
      next: () => { this.successMsg = 'Statut mis à jour.'; this.load(); setTimeout(() => this.successMsg = '', 3000); },
      error: () => { this.error = 'Erreur mise à jour.'; }
    });
  }

  delete(id: number): void {
    if (!confirm('Supprimer cette réservation ?')) return;
    this.reservationService.delete(id).subscribe({
      next: () => { this.successMsg = 'Supprimée.'; this.load(); setTimeout(() => this.successMsg = '', 3000); },
      error: () => { this.error = 'Erreur suppression.'; }
    });
  }

  getStatutClass(s: string): string {
    return ({'EN_ATTENTE':'en-attente','CONFIRMEE':'confirmee','ANNULEE':'annulee'} as any)[s] || '';
  }
}