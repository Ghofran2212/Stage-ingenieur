import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { ReservationService } from '../../../../core/services/reservation.service';
import { DestinationService } from '../../../../core/services/destination.service';
import { HotelService } from '../../../../core/services/hotel.service';

import { Destination } from '../../../../core/models/destination';
import { Hotel } from '../../../../core/models/hotel';

@Component({
  selector: 'app-reservation-form',
  templateUrl: './reservation-form.component.html',
  styleUrls: ['./reservation-form.component.scss']
})
export class ReservationFormComponent implements OnInit {

  form: FormGroup;

  destination: Destination | null = null;

  hotels: Hotel[] = [];
  selectedHotel: Hotel | null = null;

  loading = true;
  saving = false;
  error = '';

  today = new Date().toISOString().split('T')[0];

  constructor(
    private fb: FormBuilder,
    private reservationService: ReservationService,
    private destinationService: DestinationService,
    private hotelService: HotelService,
    private route: ActivatedRoute,
    private router: Router
  ) {

    this.form = this.fb.group({
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],
      nombrePersonnes: [
        1,
        [
          Validators.required,
          Validators.min(1),
          Validators.max(20)
        ]
      ]
    });

  }

  ngOnInit(): void {

    const id = this.route.snapshot.paramMap.get('destinationId');

    if (id) {

      this.destinationService.findById(+id).subscribe({

        next: (d) => {

          this.destination = d;

          this.hotelService.findByDestination(d.id!).subscribe({

            next: (hotels) => {
              this.hotels = hotels;
              this.loading = false;
            },

            error: () => {
              this.loading = false;
            }

          });

        },

        error: () => {
          this.loading = false;
          this.error = 'Destination introuvable.';
        }

      });

    }

  }

  get dateDebut() {
    return this.form.get('dateDebut')!;
  }

  get dateFin() {
    return this.form.get('dateFin')!;
  }

  get nombrePersonnes() {
    return this.form.get('nombrePersonnes')!;
  }

  get nbNuits(): number {

    if (!this.dateDebut.value || !this.dateFin.value) {
      return 0;
    }

    const diff =
      new Date(this.dateFin.value).getTime()
      -
      new Date(this.dateDebut.value).getTime();

    return Math.max(
      0,
      Math.ceil(diff / (1000 * 60 * 60 * 24))
    );

  }

  get totalPrix(): number {

    if (!this.destination) {
      return 0;
    }

    const prixDestination =
      this.nbNuits *
      this.destination.prix *
      (this.nombrePersonnes.value || 1);

    const prixHotel =
      this.selectedHotel
        ? this.nbNuits * this.selectedHotel.prixParNuit
        : 0;

    return prixDestination + prixHotel;

  }

  decrement(): void {

    const v = this.nombrePersonnes.value;

    if (v > 1) {
      this.nombrePersonnes.setValue(v - 1);
    }

  }

  increment(): void {

    const v = this.nombrePersonnes.value;

    if (v < 20) {
      this.nombrePersonnes.setValue(v + 1);
    }

  }

  onSubmit(): void {

    if (this.form.invalid || !this.destination) {

      this.form.markAllAsTouched();
      return;

    }

    this.saving = true;
    this.error = '';

    this.reservationService.save({

      destinationId: this.destination.id,

      hotelId: this.selectedHotel?.id,

      dateDebut: this.dateDebut.value,
      dateFin: this.dateFin.value,
      nombrePersonnes: this.nombrePersonnes.value

    }).subscribe({

      next: () => {

        this.saving = false;

        this.router.navigate([
          '/reservations/mes-reservations'
        ]);

      },

      error: (e) => {

        this.saving = false;

        this.error =
          e.error?.message ||
          'Erreur lors de la réservation.';

      }

    });

  }

  cancel(): void {
    this.router.navigate(['/destinations']);
  }

}