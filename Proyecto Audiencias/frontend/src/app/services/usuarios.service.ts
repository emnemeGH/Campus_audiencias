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

  getUsuarioPorId(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  getAutoridades(): Observable<any[]> {
    return this.http.get<any[]>(this.apiAutoridades);
  }

  getAutoridadesPorId(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiAutoridades}/${id}`);
  }

  editarUsuario(usuario: any): Observable<any> {
    if (!usuario.usr_id) {
      console.error("Error: El usuario no tiene un ID definido.");
    }
    return this.http.put<any>(`${this.apiUrl}/${usuario.usr_id}`, usuario);
  }

  editarAutoridad(autoridad: any): Observable<any> {
    if (!autoridad.aut_id) {
      console.error("Error: La autoridad no tiene un ID definido.");
    }
    return this.http.put<any>(`${this.apiAutoridades}/${autoridad.aut_id}`, autoridad);
  }

  agregarUsuario(usuario: any): Observable<any> {
    console.log("Enviando usuario al backend:", usuario);  // Debugging adicional
    return this.http.post<any>(this.apiUrl, usuario);
  }

  agregarAutoridad(autoridad: any): Observable<any>{
    return this.http.post<any>(this.apiAutoridades, autoridad);
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
          rol: usuario.usrIsAdmin ? 'Administrador' : 'Operador',  // Si usrIsAdmin es 1 -> Admin, si es 0 -> Operador
          estado: usuario.usrEstado
        }));

        // Mapear autoridades
        let listaAutoridades = autoridades.map(autoridad => ({
          id: autoridad.aut_id,  
          nombre: autoridad.aut_nombre,   
          correo: autoridad.aut_mail,  
          rol: 'Autoridad',
          estado: autoridad.autEstado
        }));

        return [...listaUsuarios, ...listaAutoridades]; // Combina ambas listas
      })
    );
  }

// UPDATE usuario SET usr_estado = 1 WHERE usr_id>0;
  borrarUsuario(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`); 
  }

  // UPDATE autoridad SET aut_estado=1 WHERE aut_id>0;
  borrarAutoridad(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiAutoridades}/${id}`); 
  }
}
