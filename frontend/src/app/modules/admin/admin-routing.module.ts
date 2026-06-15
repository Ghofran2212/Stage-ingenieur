import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { AdminDestinationsComponent } from './components/admin-destinations/admin-destinations.component';
import { AdminReservationsComponent } from './components/admin-reservations/admin-reservations.component';
import { AdminHotelsComponent } from './components/admin-hotels/admin-hotels.component';
const routes: Routes = [
  { path: 'dashboard', component: AdminDashboardComponent },
  { path: 'destinations', component: AdminDestinationsComponent },
  { path: 'reservations', component: AdminReservationsComponent },

  { path: 'hotels', component: AdminHotelsComponent },

  { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {}