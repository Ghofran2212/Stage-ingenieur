import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from './core/guards/auth.guard';
import { AdminGuard } from './core/guards/admin.guard';

import { ChatbotComponent } from './modules/chat/chatbot/chatbot.component';

const routes: Routes = [

  {
    path: 'chatbot',
    component: ChatbotComponent
  },

  {
    path: '',
    redirectTo: '/destinations',
    pathMatch: 'full'
  },

  {
    path: 'auth',
    loadChildren: () =>
      import('./modules/auth/auth.module')
        .then(m => m.AuthModule)
  },

  {
    path: 'destinations',
    loadChildren: () =>
      import('./modules/destinations/destinations.module')
        .then(m => m.DestinationsModule)
  },

  {
    path: 'reservations',
    canActivate: [AuthGuard],
    loadChildren: () =>
      import('./modules/reservations/reservations.module')
        .then(m => m.ReservationsModule)
  },

  {
    path: 'avis',
    canActivate: [AuthGuard],
    loadChildren: () =>
      import('./modules/avis/avis.module')
        .then(m => m.AvisModule)
  },

  {
    path: 'admin',
    canActivate: [AuthGuard, AdminGuard],
    loadChildren: () =>
      import('./modules/admin/admin.module')
        .then(m => m.AdminModule)
  },

  {
    path: '**',
    redirectTo: '/destinations'
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }