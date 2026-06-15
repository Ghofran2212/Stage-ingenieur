import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReservationFormComponent } from './components/reservation-form/reservation-form.component';
import { MesReservationsComponent } from './components/mes-reservations/mes-reservations.component';

const routes: Routes = [
  { path: 'mes-reservations',     component: MesReservationsComponent },
  { path: 'new/:destinationId',   component: ReservationFormComponent },
  { path: '', redirectTo: 'mes-reservations', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReservationsRoutingModule {}