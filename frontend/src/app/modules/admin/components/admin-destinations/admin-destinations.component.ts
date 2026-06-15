import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({ selector: 'app-admin-destinations', template: '' })
export class AdminDestinationsComponent {
  constructor(private router: Router) {
    this.router.navigate(['/destinations']);
  }
}