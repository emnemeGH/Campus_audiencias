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
        console.log("Usuarios obtenidos:", data); // Verifica los datos en consola
        this.usuarios = data;
        this.usuariosFiltrados = [...this.usuarios]; // Copia inicial sin filtros
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
}
