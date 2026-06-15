import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { ReservationsRoutingModule } from './reservations-routing.module';

import { ReservationFormComponent } from './components/reservation-form/reservation-form.component';
import { MesReservationsComponent } from './components/mes-reservations/mes-reservations.component';

@NgModule({
  declarations: [
    ReservationFormComponent,
    MesReservationsComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    ReservationsRoutingModule
  ]
})
export class ReservationsModule {}