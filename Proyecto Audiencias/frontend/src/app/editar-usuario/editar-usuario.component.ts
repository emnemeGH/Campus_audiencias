import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuariosService } from '../services/usuarios.service';


// Declaramos una estructura para los objetos
export interface Usuario {
  usr_id?:number,
  usr_nombre?: string,
  usrMail?: string,
  usrUsername?: string,
  usrPassword?: string,
  usrIsAdmin?: boolean
}

export interface Autoridad {
  aut_id?: number,
  aut_nombre?: string,
  autMail?:string,
  distrito: {
    dis_nombre?: string
  }
  aut_tipo?: 'juez' | 'fiscal' | 'defensor',
}


@Component({
  selector: 'app-editar-usuario',
  templateUrl: './editar-usuario.component.html',
  styleUrls: ['./editar-usuario.component.css']
})
export class EditarUsuarioComponent implements OnInit {

  usuario: Usuario = {
    usr_id: 0,
    usr_nombre: '',
    usrMail: '',
    usrUsername: '',
    usrPassword: '',
  };

  // Inicializamos como un objeto vacío. Si tiene un objeto adentro tambien hay que inicializarlo
  autoridad: Autoridad = {
    distrito: {
      dis_nombre: undefined
    }
  };

  constructor(
      private route: ActivatedRoute,
      private usuariosService: UsuariosService,
      private router: Router,
    ) {}

  rol:string | undefined;


  ngOnInit() {
    // Obtener el tipo y el ID desde la URL
    this.rol = this.route.snapshot.paramMap.get('tipo')!;

    if(this.rol == 'Autoridad') {
      this.autoridad.aut_id = Number(this.route.snapshot.paramMap.get('id'));
      this.usuariosService.getAutoridadesPorId(this.autoridad.aut_id).subscribe(
        (data) => { 
          this.autoridad = data;
          console.log('La autoridad es', this.autoridad)
        },
        (error) => console.error('Error al obtener autoridad:', error)
      );
    } else {
      this.usuario.usr_id = Number(this.route.snapshot.paramMap.get('id'));
      this.usuariosService.getUsuarioPorId(this.usuario.usr_id).subscribe(
        (data) => { 
          this.usuario = data;

          if(this.usuario.usrIsAdmin == true) {
            this.rol = 'Administrador';
          } else {
            this.rol = 'Operador';
          }
        },
        (error) => console.error('Error al obtener usuario:', error)
      );
    }
    
  }

  esAutoridad():boolean {
    if(this.rol == 'Autoridad') {
      return true;
    } else {
      return false;
    }
  }

  guardarCambios() { 
    if (this.rol === 'Autoridad') {
      this.usuariosService.editarAutoridad(this.autoridad).subscribe(
        (response) => {
          console.log('Autoridad editada con éxito', response);
          this.router.navigate(['/lista-usuarios']);
        },
        (error) => {
          console.error('Error al editar autoridad:', error);
        }
      );
    } else {
      // ✅ Convertir rol a booleano antes de enviarlo
      this.usuario.usrIsAdmin = (this.rol === 'Administrador');
  
      console.log("➡️ Datos enviados al backend para editar usuario:", this.usuario); // 🛠️ DEBUG
  
      this.usuariosService.editarUsuario(this.usuario).subscribe(
        (response) => {
          console.log('Usuario editado con éxito', response);
          this.router.navigate(['/lista-usuarios']);
        },
        (error) => {
          console.error('Error al editar usuario:', error);
        }
      );
    }
  }
  
  
}

