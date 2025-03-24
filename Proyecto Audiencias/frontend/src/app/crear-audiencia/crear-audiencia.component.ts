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
  sala:  {
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
  juez?: number;
  fiscal?: number;
  defensor?: number;
}

@Component({
  selector: 'app-agregar-audiencia',
  templateUrl: './crear-audiencia.component.html',
  styleUrls: ['./crear-audiencia.component.css']
})

export class CrearAudienciaComponent {
  audiencia: AudienciaForm = {
    aud_estado: true,
    sala: {
      sal_id: undefined,
      sal_nombre: undefined,
      distrito: {
        dis_id: undefined,
        dis_nombre: undefined
      }
    },
    juez: undefined,
    fiscal: undefined,
    defensor: undefined
  };
  
  constructor(private audienciaService: AudienciaService, private router: Router) {}

  
  guardarAudiencia() {
    // Si audiencia.juez tiene algun valor lo pasa a number
    this.audiencia.juez = this.audiencia.juez ? Number(this.audiencia.juez) : undefined;
    this.audiencia.fiscal = this.audiencia.fiscal ? Number(this.audiencia.fiscal) : undefined;
    this.audiencia.defensor = this.audiencia.defensor ? Number(this.audiencia.defensor) : undefined;

    // Asegurar que sal_id sea un número
  if (this.audiencia.sala.sal_id && typeof this.audiencia.sala.sal_id === 'object') {
    this.audiencia.sala.sal_id = this.audiencia.sala.sal_id;
  }

    // Para reiniciar el ID: ALTER TABLE audiencia AUTO_INCREMENT = 5; Primero se deben borrar todas las audiencias no deseas y luego setear el id que queremos que arranque desde.
    // Hacer lo mismo en audiencia_ext ALTER TABLE audiencia_ext AUTO_INCREMENT = 13;
    console.log('Datos antes de enviar:', this.audiencia);
    // el parámetro nuevaAudiencia es necesario, El ID (aud_id) es generado automaticamente por la base de datos. Cuando el servidor responde con la nueva audiencia creada, su aud_id es necesario para crear las relaciones en AUDIENCIA_EXT con juez, fiscal y defensor.
    this.audienciaService.agregarAudiencia(this.audiencia).subscribe((nuevaAudiencia) => {
      const audienciaId = nuevaAudiencia.aud_id;
  
      // Crear relaciones en AUDIENCIA_EXT para juez, fiscal y defensor
      if (this.audiencia.juez) {
        this.audienciaService.agregarRelacionAudienciaAutoridad(audienciaId, this.audiencia.juez)
          .subscribe();
      }
      if (this.audiencia.fiscal) {
        this.audienciaService.agregarRelacionAudienciaAutoridad(audienciaId, this.audiencia.fiscal)
          .subscribe();
      }
      if (this.audiencia.defensor) {
        this.audienciaService.agregarRelacionAudienciaAutoridad(audienciaId, this.audiencia.defensor)
          .subscribe();
      }
  
      this.router.navigate(['/lista-audiencias']);
    });
  }
  



}
