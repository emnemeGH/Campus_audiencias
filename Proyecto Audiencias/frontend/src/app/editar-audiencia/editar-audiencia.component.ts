import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AudienciaService } from '../services/audiencia.service';
import { AudienciaExtensionService } from '../services/audiencia-extension.service';

export interface Audiencia {
  aud_id: number;  // ID autoincremental
  aud_nombre?: string;
  aud_usrins?: number;
  aud_fecins?: string;
  aud_usrmod?: number;
  aud_fecmod?: string;
  aud_estado?: boolean;
  aud_tipo?: 'Demorada' | 'Programada' | 'Realizada' | 'Suspendida';
  sala: {
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
  selector: 'app-editar-audiencia',
  templateUrl: './editar-audiencia.component.html',
  styleUrls: ['./editar-audiencia.component.css']
})
export class EditarAudienciaComponent implements OnInit {
  audiencia: AudienciaForm = {
    aud_id: 0,
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

  constructor(
    private audienciaService: AudienciaService,
    private audienciaExtensionService: AudienciaExtensionService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      const audId = Number(id); // Convertir a número
      console.log('ID obtenido de la URL:', audId);
      this.audienciaService.obtenerAudienciaPorId(audId).subscribe(
        data => {
          console.log('Audiencia recibida:', data); 
          this.audiencia = data;
          this.obtenerAutoridades();
        },
        error => {
          console.error('Error al obtener la audiencia:', error);
        }
      );
    }
  }

  obtenerAutoridades() {
    this.audienciaService.getAutoridadesPorAudiencia(this.audiencia.aud_id).subscribe(
      autoridades => {
        this.audiencia.juez = autoridades.find(a => a.autoridad.aut_tipo === 'juez')?.autoridad.aut_nombre || '-';
        this.audiencia.fiscal = autoridades.find(a => a.autoridad.aut_tipo === 'fiscal')?.autoridad.aut_nombre || '-';
        this.audiencia.defensor = autoridades.find(a => a.autoridad.aut_tipo === 'defensor')?.autoridad.aut_nombre || '-';
      },
      error => console.error('Error obteniendo autoridades:', error)
    );
  }


  guardarCambios() {
    // Para reiniciar el ID: ALTER TABLE audiencia AUTO_INCREMENT = 5; Primero se deben borrar todas las audiencias no deseas y luego setear el id que queremos que arranque desde.
    console.log('Datos a enviar:', this.audiencia);
    this.audienciaService.editarAudiencia(this.audiencia).subscribe(() => {

      // Crear relaciones en AUDIENCIA_EXT para juez, fiscal y defensor
      if (this.audiencia.juez) {
        this.audienciaService.agregarRelacionAudienciaAutoridad(this.audiencia.aud_id, this.audiencia.juez)
          .subscribe();
      }
      if (this.audiencia.fiscal) {
        this.audienciaService.agregarRelacionAudienciaAutoridad(this.audiencia.aud_id, this.audiencia.fiscal)
          .subscribe();
      }
      if (this.audiencia.defensor) {
        this.audienciaService.agregarRelacionAudienciaAutoridad(this.audiencia.aud_id, this.audiencia.defensor)
          .subscribe();
      }

      this.router.navigate(['/lista-audiencias']);
    });
  }
}
