import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  private apiUrl = 'http://localhost:8080/api/usuarios'; 

  constructor(private http: HttpClient) {}

  // Un Observable en Angular es como una "promesa mejorada". Es una forma de manejar datos que pueden cambiar con el tiempo, como una lista de usuarios que viene de un servidor.
  // Observable<any[]> → Significa que este método devuelve un Observable que en algún momento tendrá una lista de usuarios (any[]).
  getUsuarios(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
