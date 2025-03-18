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

export interface AudienciaForm extends Audiencia {
  aud_juez?: number;
  aud_fiscal?: number;
  aud_defensor?: number;
}

@Component({
  selector: 'app-agregar-audiencia',
  templateUrl: './crear-audiencia.component.html',
  styleUrls: ['./crear-audiencia.component.css']
})

export class CrearAudienciaComponent {
  audiencia: AudienciaForm = {
    aud_estado: true,
    sal_id: {
      sal_id: undefined,
      sal_nombre: undefined,
      distrito: {
        dis_id: undefined,
        dis_nombre: undefined
      }
    },
    aud_juez: undefined,
    aud_fiscal: undefined,
    aud_defensor: undefined
  };
  
  constructor(private audienciaService: AudienciaService, private router: Router) {}

  
  guardarAudiencia() {
    // Para reiniciar el ID: ALTER TABLE audiencia AUTO_INCREMENT = 5; Primero se deben borrar todas las audiencias no deseas y luego setear el id que queremos que arranque desde.
    console.log('Datos a enviar:', this.audiencia);
    this.audienciaService.agregarAudiencia(this.audiencia).subscribe((nuevaAudiencia) => {
      const audienciaId = nuevaAudiencia.aud_id;
  
      // Crear relaciones en AUDIENCIA_EXT para juez, fiscal y defensor
      if (this.audiencia.aud_juez) {
        this.audienciaService.agregarRelacionAudienciaAutoridad(audienciaId, this.audiencia.aud_juez)
          .subscribe();
      }
      if (this.audiencia.aud_fiscal) {
        this.audienciaService.agregarRelacionAudienciaAutoridad(audienciaId, this.audiencia.aud_fiscal)
          .subscribe();
      }
      if (this.audiencia.aud_defensor) {
        this.audienciaService.agregarRelacionAudienciaAutoridad(audienciaId, this.audiencia.aud_defensor)
          .subscribe();
      }
  
      this.router.navigate(['/lista-audiencias']);
    });
  }
  



}
