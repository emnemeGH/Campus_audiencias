import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private router: Router) {}

  onLogin() {
    // Simulación de credenciales (esto debe venir de una API)
    const users = [
      { email: 'admin@example.com', password: 'admin123', role: 'admin' },
      { email: 'operador@example.com', password: 'operador123', role: 'operador' }
    ];

    const user = users.find(u => u.email === this.email && u.password === this.password);

    if (user) {
      if (user.role === 'admin') {
        this.router.navigate(['/registro']);
      } else if (user.role === 'operador') {
        this.router.navigate(['/lista-audiencias'])
      }
    } else {
      alert('Credenciales incorrectas');
    }
  }

}
