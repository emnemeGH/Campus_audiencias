import { Component } from '@angular/core';
import { Router } from '@angular/router';
// Importamos el archivo de usuarios services
import { UsuariosService } from '../services/usuarios.service';

@Component({
  selector: 'app-login-autoridad',
  templateUrl: './login-autoridad.component.html',
  styleUrls: ['../login/login.component.css']
})

export class LoginAutoridadComponent {
  email: string = '';
  idAut: number = 0;
  autoridades: any[] = [];

  // Creamos una instancia de usuariosService para poder usar los metodos que se encuentran dentro del archivo
  constructor(private router: Router, private usuariosService: UsuariosService) {}

  //  ngOnInit() es un método del ciclo de vida del componente. Se ejecuta automáticamente cuando el componente se inicializa. Inicializar un componente significa que Angular ya lo preparó para que funcione correctamente. El constructor lo crea, pero ngOnInit() se ejecuta cuando ya está listo. Se usa para cargar datos y realizar inicializaciones. Es mejor que el constructor para llamadas a servicios.
  ngOnInit() {
    this.obtenerAutoridades();
  }

  obtenerAutoridades() {
    // .subscribe() escucha los cambios de un Observable y ejecuta una acción cuando recibe datos.
    this.usuariosService.getAutoridades().subscribe(
      (data) => {
        this.autoridades = data;
      },
      (error) => {
        console.error("Error al obtener autoridades:", error);
      }
    );
  }

  onLogin() {
    const idAut = this.autoridades.find(a => a.id === this.idAut);
    const user = this.autoridades.find(a => a.autMail === this.email && a.autEstado);
    if (user) {
      this.router.navigate(['/lista-audiencias'], { queryParams: { esUsuario: false } });
      } else {
      alert('Credenciales incorrectas');
    }
  }
}
