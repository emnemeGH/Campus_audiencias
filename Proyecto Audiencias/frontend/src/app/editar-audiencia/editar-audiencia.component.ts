import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AudienciaService } from '../services/audiencia.service';

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
  selector: 'app-editar-audiencia',
  templateUrl: './editar-audiencia.component.html',
  styleUrls: ['./editar-audiencia.component.css']
})
export class EditarAudienciaComponent implements OnInit {
  audiencia: AudienciaForm = {
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

  constructor(
    private audienciaService: AudienciaService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      const audId = Number(id); // Convertir a número
      this.audienciaService.obtenerAudienciaPorId(audId).subscribe(
        data => {
          this.audiencia = data;
        },
        error => {
          console.error('Error al obtener la audiencia:', error);
        }
      );
    }
  }
  

  guardarCambios() {
    // Para reiniciar el ID: ALTER TABLE audiencia AUTO_INCREMENT = 5; Primero se deben borrar todas las audiencias no deseas y luego setear el id que queremos que arranque desde.
    console.log('Datos a enviar:', this.audiencia);
    this.audienciaService.editarAudiencia(this.audiencia).subscribe((nuevaAudiencia) => {
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
