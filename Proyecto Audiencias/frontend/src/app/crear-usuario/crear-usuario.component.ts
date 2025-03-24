import { Component } from '@angular/core';
import { UsuariosService } from '../services/usuarios.service';
import { Router } from '@angular/router';
import { DistritoService } from '../services/distrito.service';

@Component({
  selector: 'app-crear-usuario',
  templateUrl: './crear-usuario.component.html',
  styleUrls: ['./crear-usuario.component.css']
})
export class CrearUsuarioComponent {
  nuevoUsuario = {
    nombre: '',
    username: '',
    correo: '',
    password: '',
    rol: '',
    tipoAutoridad: '', // Tipo de autoridad (solo si es Autoridad)
    dis_id: 0
  };

  esAutoridad: boolean = false; // Variable para deshabilitar el campo nombre de usuario
  distritos: any[] = [];

  constructor(private usuariosService: UsuariosService, private router: Router, private distritoService: DistritoService) {}

  ngOnInit() {
    this.obtenerDistritos();
  }

  actualizarFormulario() {
    // Si el rol es "Autoridad", deshabilita el campo de nombre de usuario y muestra "Tipo de Autoridad"
    this.esAutoridad = this.nuevoUsuario.rol === 'Autoridad';
    if (this.esAutoridad) {
      this.nuevoUsuario.username = ''; 
    } else {
      this.nuevoUsuario.tipoAutoridad = '';
    }
  }

  obtenerDistritos() {
    this.distritoService.obtenerDistritos().subscribe(
      (data) => {
        this.distritos = data;
      },
      (error) => {
        console.error("Error al obtener distritos:", error);
      }
    );
  }

  guardarUsuario() {
    // Asegúrate de que dis_id sea un número (aunque si el campo en la base de datos puede ser null, puedes dejarlo como 0)
    this.nuevoUsuario.dis_id = Number(this.nuevoUsuario.dis_id || 0);
  
    console.log("Datos que se enviarán al backend:", this.nuevoUsuario);
  
    if (this.nuevoUsuario.rol === 'Autoridad') {
      // Si es autoridad, enviar los datos, incluyendo dis_id
      this.usuariosService.agregarAutoridad({
        aut_nombre: this.nuevoUsuario.nombre,
        autMail: this.nuevoUsuario.correo,
        aut_tipo: this.nuevoUsuario.tipoAutoridad,
        dis_id: this.nuevoUsuario.dis_id // Esto relacionará la autoridad con el distrito
      }).subscribe(
        () => {
          alert('Autoridad agregada correctamente.');
          this.router.navigate(['/lista-usuarios']);
        },
        error => {
          console.error('Error al agregar autoridad:', error);
          alert('Hubo un error al agregar la autoridad. Correo ya existente.');
        }
      );
    } else {
      // Enviar a la tabla de usuarios
      this.usuariosService.agregarUsuario({
        usuario: {
          usr_nombre: this.nuevoUsuario.nombre,
          usrUsername: this.nuevoUsuario.username,
          usrMail: this.nuevoUsuario.correo,
          usrPassword: this.nuevoUsuario.password,
          usrIsAdmin: this.nuevoUsuario.rol === 'Administrador' ? true : false
        }
      }).subscribe(
        () => {
          alert('Usuario agregado correctamente.');
          this.router.navigate(['/lista-usuarios']);
        },
        error => {
          console.error('Error al agregar usuario:', error);
          alert('Hubo un error al agregar el usuario. Correo o nombre de usuario ya existente.');
        }
      );
    }
  }
  
}
