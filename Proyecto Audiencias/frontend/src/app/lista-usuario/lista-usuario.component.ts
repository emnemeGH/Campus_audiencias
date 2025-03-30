import { Component, OnInit } from '@angular/core';
import { UsuariosService } from '../services/usuarios.service';

@Component({
  selector: 'app-lista-usuario',
  templateUrl: './lista-usuario.component.html',
  styleUrls: ['./lista-usuario.component.css']
})
export class ListaUsuarioComponent implements OnInit {
  usuarios: any[] = [];
  usuariosFiltrados: any[] = [];
  
  correoFiltro: string = '';
  usuarioFiltro: string = '';
  rolFiltro: string = '';

  constructor(private usuariosService: UsuariosService) {}

  ngOnInit() {
    this.obtenerUsuarios();
    
  }

  obtenerUsuarios() {
    this.usuariosService.obtenerTodos().subscribe(
      (data) => {
        this.usuarios = data.filter(u => u.estado);
        console.log("Usuarios obtenidos:", this.usuarios); 
        this.usuariosFiltrados = [...this.usuarios]; 
      },
      (error) => {
        console.error("Error al obtener usuarios:", error);
      }
    );
  }

  filtrarUsuarios() {
    this.usuariosFiltrados = this.usuarios.filter(usuario => {
      const coincideCorreo = this.correoFiltro
        ? usuario.correo && usuario.correo.toLowerCase().includes(this.correoFiltro.toLowerCase()) 
        : true;
  
      const coincideUsuario = this.usuarioFiltro
        ? usuario.username && usuario.username.toLowerCase().includes(this.usuarioFiltro.toLowerCase()) 
        : true;
  
      const coincideRol = this.rolFiltro
        ? usuario.rol === this.rolFiltro
        : true;
  
      return coincideCorreo && coincideUsuario && coincideRol;
    });
  }
  

  reiniciarFiltros() {
    this.correoFiltro = '';
    this.usuarioFiltro = '';
    this.rolFiltro = '';
    this.usuariosFiltrados = [...this.usuarios];
  }

  eliminarUsuario(id: number, rol: string) {
    if (rol == 'Autoridad') {
      if (confirm("¿Estás seguro de que deseas eliminar esta autoridad?")) {
        this.usuariosService.borrarAutoridad(id).subscribe({
          next: () => {
            this.usuarios = this.usuarios.filter(u => !(u.id === id && u.rol === rol));
            this.usuariosFiltrados = [...this.usuarios];
          },
          error: () => {
            alert("No se puede eliminar la autoridad porque tiene audiencias activas.");
          }
        });
      }
    } else {
      if (confirm("¿Estás seguro de que deseas eliminar este Usuario?")) {
        this.usuariosService.borrarUsuario(id).subscribe({
          next: () => {
            this.usuarios = this.usuarios.filter(u => !(u.id === id && u.rol !== 'Autoridad'));
            this.usuariosFiltrados = [...this.usuarios];
          },
          error: (error) => {
            alert(error.error.message || "Ocurrió un error al eliminar el usuario.");
          }
        });
      }
    }
  }
  
}
