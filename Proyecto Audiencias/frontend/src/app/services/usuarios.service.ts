import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { forkJoin, map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  private apiUrl = 'http://localhost:8080/api/usuarios';
  private apiAutoridades = 'http://localhost:8080/api/autoridades';

  constructor(private http: HttpClient) {}

  // Un Observable en Angular es como una "promesa mejorada". Es una forma de manejar datos que pueden cambiar con el tiempo, como una lista de usuarios que viene de un servidor.
  // Observable<any[]> → Significa que este método devuelve un Observable que en algún momento tendrá una lista de usuarios (any[]).
  getUsuarios(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  getAutoridades(): Observable<any[]> {
    return this.http.get<any[]>(this.apiAutoridades);
  }

  obtenerTodos(): Observable<any[]> {
    return forkJoin({
      usuarios: this.getUsuarios(),
      autoridades: this.getAutoridades()
    }).pipe(
      map(({ usuarios, autoridades }) => {
        // Mapear usuarios
        let listaUsuarios = usuarios.map(usuario => ({
          id: usuario.usr_id,
          nombre: usuario.usr_nombre,
          username: usuario.usrUsername,
          correo: usuario.usrMail,
          rol: usuario.usrIsAdmin ? 'Administrador' : 'Operador'  // Si usrIsAdmin es 1 -> Admin, si es 0 -> Operador
        }));

        // Mapear autoridades
        let listaAutoridades = autoridades.map(autoridad => ({
          id: autoridad.aut_id,  
          nombre: autoridad.aut_nombre,   
          correo: autoridad.aut_mail,  
          rol: 'Autoridad'  
        }));

        return [...listaUsuarios, ...listaAutoridades]; // Combina ambas listas
      })
    );
  }
}
