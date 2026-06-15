import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../../core/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  loginData = {
    email: '',
    password: ''
  };

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  login() {
    this.authService.login(this.loginData).subscribe({
      next: () => {
        this.router.navigate(['/destinations']);
      },
      error: (err: any) => {
        console.error(err);
        alert('Email ou mot de passe incorrect');
      }
    });
  }
}