import { Component } from '@angular/core';
import { AudienciaService } from '../services/audiencia.service';
import { Router } from '@angular/router';


export interface Audiencia {
  aud_id?: number;  // ID autoincremental
  aud_nombre?: string;
  aud_usrins?: number;
  aud_fecins?: string;
  aud_usrmod?: number;
  aud_fecmod?: string;
  aud_estado?: boolean;
  aud_tipo?: 'Demorada' | 'Programada' | 'Realziada' | 'Suspendida';
  sal_id:  {
    sal_id: undefined | number,
    sal_nombre?: string,
    distrito: {
      dis_id: undefined | number,
      dis_nombre: undefined | string,
  }
  };
  aud_fecha?: string;
  aud_hora?: string;
  aud_duracion?: string;
  aud_cuij?: number;
  aud_caratula?: string;
};

@Component({
  selector: 'app-agregar-audiencia',
  templateUrl: './crear-audiencia.component.html',
  styleUrls: ['./crear-audiencia.component.css']
})

export class CrearAudienciaComponent {
  audiencia: Audiencia = {
    sal_id: {
      sal_id: undefined,
      sal_nombre: undefined,
      distrito: {
        dis_id: undefined,
        dis_nombre: undefined
      }
    }
  };
  constructor(private audienciaService: AudienciaService, private router: Router) {}

  
guardarAudiencia() {
  // Para reiniciar el ID: ALTER TABLE audiencia AUTO_INCREMENT = 5; Primero se deben borrar todas las audiencias no deseas y luego setear el id que queremos que arranque desde.
  console.log('Datos a enviar:', this.audiencia);  // Verifica los datos antes de enviarlos
  this.audienciaService.agregarAudiencia(this.audiencia).subscribe(() => {
      this.router.navigate(['/lista-audiencias']);
  });
}



}
