import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { AdminDestinationsComponent } from './components/admin-destinations/admin-destinations.component';
import { AdminReservationsComponent } from './components/admin-reservations/admin-reservations.component';
import { AdminHotelsComponent } from './components/admin-hotels/admin-hotels.component';

@NgModule({
  declarations: [AdminDashboardComponent, AdminDestinationsComponent, AdminReservationsComponent, AdminHotelsComponent],
  imports: [CommonModule, FormsModule, RouterModule, AdminRoutingModule]
})
export class AdminModule {}