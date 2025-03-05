import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AudienciaService {
  private audiencias: any[] = [
    { hora: "07:30", distrito: "03 Venado Tuerto", sala: "Sala VC Venado Tuerto", cuij: "21-06017630-9", caratula: "Usurpación y Daño - Apelación", tipo: "Apelación - Multipropósito", juez: "Orso, Tomás Gabriel", fiscal: "Pepino, Susana del Valle", defensor: "Daniel Domínguez", estado: "REALIZADA",  fecha: "2024-03-07" },
    { hora: "08:00", distrito: "04 Reconquista", sala: "Sala 1 Reconquista", cuij: "21-09585001-1", caratula: "Delitos contra la integridad sexual", tipo: "Nueva Audiencia Prisión Preventiva", juez: "Martelossi, Mauricio Daniel", fiscal: "Díaz, Georgina Soledad", defensor: "Zanet Bruno", estado: "REALIZADA",  fecha: "2024-03-08" },
    { hora: "10:00", distrito: "04 Reconquista", sala: "Sala 2 Reconquista", cuij: "21-05065401-1", caratula: "Delitos contra la integridad sexual", tipo: "Nueva Audiencia Prisión Preventiva", juez: "Orso, Tomás Gabriel", fiscal: "Díaz, Georgina Soledad", defensor: "Zanet Bruno", estado: "REALIZADA",  fecha: "2024-03-07" }
  ];

  constructor() {}

  getAudiencias() {
    return this.audiencias;
  }

  agregarAudiencia(audiencia: any) {
    this.audiencias.push(audiencia);
  }
}

