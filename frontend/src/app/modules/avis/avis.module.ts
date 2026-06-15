import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AvisRoutingModule } from './avis-routing.module';
import { AvisListComponent } from './components/avis-list/avis-list.component';
import { AvisFormComponent } from './components/avis-form/avis-form.component';


@NgModule({
  declarations: [
    AvisListComponent,
    AvisFormComponent
  ],
  imports: [
    CommonModule,
    AvisRoutingModule
  ]
})
export class AvisModule { }
