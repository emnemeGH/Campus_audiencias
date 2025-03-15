import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AudienciaService {
  private apiUrl = 'http://localhost:8080/api/audiencias';

  constructor(private http: HttpClient) {}

  getAudiencias(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  agregarAudiencia(audiencia: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, audiencia);
  }

  obtenerAudienciaPorId(id: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  editarAudiencia(audiencia: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${audiencia.aud_id}`, audiencia);
  }

  getAutoridadesPorAudiencia(id: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${id}/autoridades`);
  }

  agregarAudienciaExt(audienciaExt: any): Observable<any> {
    return this.http.post<any>(`http://localhost:8080/api/audienciasExt`, audienciaExt);
  }

  agregarRelacionAudienciaAutoridad(audienciaId: number, autoridadId: number): Observable<any> {
    const relacion = {
      audiencia: { aud_id: audienciaId },
      autoridad: { aut_id: autoridadId },
    };
    return this.http.post<any>(`http://localhost:8080/api/audienciasExt`, relacion);
  }
}

