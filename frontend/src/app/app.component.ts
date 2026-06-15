import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
    <app-navbar></app-navbar>

    <main style="min-height: calc(100vh - 64px)">
      <router-outlet></router-outlet>
    </main>
  `
})
export class AppComponent {}