import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AutoridadesService {

  private apiAutoridades = 'http://localhost:8080/api/autoridades';

  constructor(private http: HttpClient) {}

  getAutoridades(): Observable<any[]> {
    return this.http.get<any[]>(this.apiAutoridades);
  }

  getAutoridadPorId(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiAutoridades}/${id}`);
  }

  editarAutoridad(autoridad: any): Observable<any> {
    if (!autoridad.aut_id) {
      return new Observable();
    }
    console.log("➡️ Enviando petición PUT a:", `${this.apiAutoridades}/${autoridad.aut_id}`);
    return this.http.put<any>(`${this.apiAutoridades}/editarAut/${autoridad.aut_id}`, autoridad);
  }

  agregarAutoridad(autoridad: any): Observable<any> {
    return this.http.post<any>(this.apiAutoridades + '/registrarAut', autoridad);
  }

  borrarAutoridad(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiAutoridades}/${id}`); 
  }

  /** 🔹 Método para obtener la lista de autoridades formateada */
  obtenerListaAutoridades(): Observable<any[]> {
    return this.getAutoridades().pipe(
      map(autoridades => autoridades.map(autoridad => ({
        id: autoridad.aut_id,
        nombre: autoridad.aut_nombre,
        correo: autoridad.autMail,
        rol: 'Autoridad',
        estado: autoridad.autEstado
      })))
    );
  }
}
