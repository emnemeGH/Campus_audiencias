import { Component } from '@angular/core';
import { Router } from '@angular/router';
// Importamos el archivo de usuarios services
import { UsuariosService } from '../services/usuarios.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  usuarios: any[] = [];

  // Creamos una instancia de usuariosService para poder usar los metodos que se encuentran dentro del archivo
  constructor(private router: Router, private usuariosService: UsuariosService) {}

  //  ngOnInit() es un método del ciclo de vida del componente. Se ejecuta automáticamente cuando el componente se inicializa. Inicializar un componente significa que Angular ya lo preparó para que funcione correctamente. El constructor lo crea, pero ngOnInit() se ejecuta cuando ya está listo. Se usa para cargar datos y realizar inicializaciones. Es mejor que el constructor para llamadas a servicios.
  ngOnInit() {
    this.obtenerUsuarios();
  }

  obtenerUsuarios() {
    // .subscribe() escucha los cambios de un Observable y ejecuta una acción cuando recibe datos.
    this.usuariosService.getUsuarios().subscribe(
      (data) => {
        this.usuarios = data;
      },
      (error) => {
        console.error("Error al obtener usuarios:", error);
      }
    );
  }

  onLogin() {
    const user = this.usuarios.find(u => u.usrMail === this.email && u.usrPassword === this.password && u.usrEstado);
    if (user) {
      if (user.usrIsAdmin) {
        this.router.navigate(['/lista-usuarios']);
      } else {
        this.router.navigate(['/lista-audiencias']);
      }
    } else {
      alert('Credenciales incorrectas');
    }
  }
}

