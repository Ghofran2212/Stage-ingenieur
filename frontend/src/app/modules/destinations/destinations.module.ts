import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { DestinationsRoutingModule } from './destinations-routing.module';

import { DestinationListComponent } from './components/destination-list/destination-list.component';
import { DestinationFormComponent } from './components/destination-form/destination-form.component';
import { DestinationDetailComponent } from './components/destination-detail/destination-detail.component';

@NgModule({
  declarations: [
    DestinationListComponent,
    DestinationFormComponent,
    DestinationDetailComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    DestinationsRoutingModule
  ]
})
export class DestinationsModule {}