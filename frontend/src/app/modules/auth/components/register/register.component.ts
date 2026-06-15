import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../../core/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  registerData = {
    nom: '',
    prenom: '',
    email: '',
    password: '',
    role: 'TOURISTE'
  };

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  register() {
    this.authService.register(this.registerData).subscribe({
      next: () => {
        alert('Compte créé avec succès');
        this.router.navigate(['/auth/login']);
      },
      error: (err: any) => {
        console.error(err);
        alert('Erreur lors de l’inscription');
      }
    });
  }
}