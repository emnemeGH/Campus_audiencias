import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AudienciaExtensionService {

  private apiUrl = 'http://localhost:8080/api/audienciasExt';

  constructor(private http: HttpClient) { }

  editarAudienciaExtension(audienciaExt: any): Observable<any> {
    if (!audienciaExt.eau_id) {
      console.error("Error: La audienciaExtension no tiene un ID definido.");
    }
    return this.http.put<any>(`${this.apiUrl}/${audienciaExt.eau_id}`, audienciaExt);
  }

  // Obtener audiencias_ext por aud_id. Para probar en postman: http://localhost:8080/api/audienciasExt/audiencia/4
  getByAudiencia(audId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/audiencia/${audId}`);
  }

  // para probar en postman http://localhost:8080/api/audienciasExt/86
  updateAutoridad(id: number, data: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, data);
  }

  // El objeto
  // {
  //   "eau_id": 87,
  //   "eau_nombre": "Nombre ejemplo",
  //   "eau_usrins": "Usuario1",
  //   "eau_fecins": "2024-03-27",
  //   "eau_usrmod": 2,
  //   "eau_fecmod": "2024-03-27",
  //   "eauEstado": true,
  //   "autoridad": {
  //     "aut_id": 2
  //   },
  //   "audiencia": {
  //     "aud_id": 13
  //   }
  // }
  
}
