import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DestinationListComponent } from './components/destination-list/destination-list.component';
import { DestinationFormComponent } from './components/destination-form/destination-form.component';
import { DestinationDetailComponent } from './components/destination-detail/destination-detail.component';
import { AdminGuard } from '../../core/guards/admin.guard';

const routes: Routes = [
  { path: '',               component: DestinationListComponent },
  { path: 'admin/new',      component: DestinationFormComponent, canActivate: [AdminGuard] },
  { path: 'admin/edit/:id', component: DestinationFormComponent, canActivate: [AdminGuard] },
  { path: ':id',            component: DestinationDetailComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DestinationsRoutingModule {}