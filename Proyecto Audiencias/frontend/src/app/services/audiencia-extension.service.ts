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
}
