import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AudienciaService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getAudiencias(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  agregarAudiencia(audiencia: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, audiencia);
  }
}

