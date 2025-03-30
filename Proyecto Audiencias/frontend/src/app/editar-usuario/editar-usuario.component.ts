import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuariosService } from '../services/usuarios.service';


// Declaramos una estructura para los objetos
export interface Usuario {
  usr_id?: number,
  usrNombre?: string,
  usrMail?: string,
  usrUsername?: string,
  usrPassword?: string,
  usrIsAdmin?: boolean,
  distrito: { dis_id: undefined };
}

export interface Autoridad {
  aut_id?: number;
  aut_nombre?: string;
  autMail?: string;
  distrito: {
    dis_id?: number;   // ✅ Asegura que tiene dis_id
    dis_nombre?: string;
  };
  aut_tipo?: 'juez' | 'fiscal' | 'defensor';
}


@Component({
  selector: 'app-editar-usuario',
  templateUrl: './editar-usuario.component.html',
  styleUrls: ['./editar-usuario.component.css']
})
export class EditarUsuarioComponent implements OnInit {

  usuario: Usuario = {
    usr_id: 0,
    usrNombre: '',
    usrMail: '',
    usrUsername: '',
    usrPassword: '',
    distrito: { dis_id: undefined }//  Inicializa el distrito
  };

  // Inicializamos como un objeto vacío. Si tiene un objeto adentro tambien hay que inicializarlo
  autoridad: Autoridad = { distrito: { dis_nombre: undefined } };
  listaDistritos = [
    { dis_id: 1, dis_nombre: 'Distrito Capital' },
    { dis_id: 2, dis_nombre: 'Distrito Sur' },
    { dis_id: 3, dis_nombre: 'Distrito Norte' }
  ];

  constructor(
    private route: ActivatedRoute,
    private usuariosService: UsuariosService,
    private router: Router,
  ) { }

  rol: string | undefined;


  ngOnInit() {
    // Obtener el tipo y el ID desde la URL
    this.rol = this.route.snapshot.paramMap.get('tipo')!;

    if (this.rol === 'Autoridad') {
      this.autoridad.aut_id = Number(this.route.snapshot.paramMap.get('id'));
      this.usuariosService.getAutoridadesPorId(this.autoridad.aut_id).subscribe(
        (data) => {
          this.autoridad = data;
          console.log('La autoridad es', this.autoridad)
        },
        (error) => {
          console.error("Error al editar autoridad:", error);
        }
      );

    } else {
      this.usuario.usr_id = Number(this.route.snapshot.paramMap.get('id'));
      this.usuariosService.getUsuarioPorId(this.usuario.usr_id).subscribe(
        (data) => {
          this.usuario = data;
          if (this.usuario.usrIsAdmin == true) {
            this.rol = 'Administrador';
          } else {
            this.rol = 'Operador';
          }
          //✅ Asegurar que distrito nunca sea undefined
          if (!this.usuario.distrito) {
            this.usuario.distrito = { dis_id: undefined };
          }
        },
        (error) => console.error('Error al obtener usuario:', error)
      );
    }

  }

  esAutoridad(): boolean {
    if (this.rol == 'Autoridad') {
      return true;
    } else {
      return false;
    }
  }

  guardarCambios() { 
    if (this.rol === 'Autoridad') {
        const autoridadEditada = {
            ...this.autoridad,
            distrito: this.autoridad.distrito?.dis_id
                ? { dis_id: Number(this.autoridad.distrito.dis_id) }
                : null
        };

        this.usuariosService.editarAutoridad(autoridadEditada).subscribe({
            next: (response) => {
                this.router.navigate(['/lista-usuarios']);
            },
            error: (error) => {
                alert(error.error?.error || "❌ Error al editar la autoridad.");
                console.error('❌ Error al editar autoridad:', error);
            }
        });

    } else {
        const usuarioEditado = {
            ...this.usuario,
            usrIsAdmin: this.rol === 'Administrador', // ✅ Se asegura de que el campo cambie
            distrito: this.usuario.distrito?.dis_id
                ? { dis_id: Number(this.usuario.distrito.dis_id) }
                : null
        };

        this.usuariosService.editarUsuario(usuarioEditado).subscribe({
            next: (response) => {
                console.log("✅ Usuario editado con éxito:", response);
                this.router.navigate(['/lista-usuarios']);
            },
            error: (error) => {
                alert(error.error?.error || "❌ Error al editar el usuario.");
                console.error("❌ Error al editar usuario:", error);
            }
        });
    }
}



}