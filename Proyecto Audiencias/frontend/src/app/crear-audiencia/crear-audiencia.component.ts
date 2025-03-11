import { Component } from '@angular/core';
import { AudienciaService } from '../services/audiencia.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-agregar-audiencia',
  templateUrl: './crear-audiencia.component.html',
  styleUrls: ['./crear-audiencia.component.css']
})
export class CrearAudienciaComponent {
  audiencia = {
    hora: '',
    distrito: '',
    sala: '',
    nombre: '',
    cuij: '',
    caratula: '',
    juez: '',
    fiscal: '',
    defensor: '',
    estado: ''
  };

  constructor(private audienciaService: AudienciaService, private router: Router) {}

  guardarAudiencia() {
    console.log('Datos a enviar:', this.audiencia); //PARA VER SI LA AUDIENCIA SE CARGA BIEN CUANDO ES ENVIADA
    this.audienciaService.agregarAudiencia(this.audiencia).subscribe(
      () => {
        alert('Audiencia guardada correctamente.');
        this.router.navigate(['/lista-audiencias']); // Redirige a la lista de audiencias
      },
      error => {
        console.error('Error al guardar audiencia:', error);
        alert('Hubo un error al guardar la audiencia.');
      }
    );
  }
}
