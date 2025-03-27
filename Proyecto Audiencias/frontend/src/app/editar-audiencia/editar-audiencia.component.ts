import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AudienciaService } from '../services/audiencia.service';
import { AudienciaExtensionService } from '../services/audiencia-extension.service';
import { AutoridadesService } from '../services/autoridades.service'

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
    sal_id?: undefined | number,
    sal_nombre?: string,
    distrito: {
      dis_id?: undefined | number,
      dis_nombre?: undefined | string,
    }
  };
  aud_fecha?: string;
  aud_hora?: string;
  aud_duracion?: string;
  aud_cuij?: number;
  aud_caratula?: string;
};

export interface Autoridad {
  autEstado?: boolean;
  autMail?: string;
  aut_id?: number;
  aut_nombre?: string;
  aut_tipo?: string;
  distrito: {
    dis_id?: number,
    dis_nombre?: string,
  },
}

export interface AudienciaExt {
  eau_id?: number;
  eau_nombre?: string;
  eau_usrins?: string;
  eau_fecins?: string;
  eau_usrmod?: number;
  eau_fecmod?: string;
  eauEstado?: boolean;
  autoridad: Autoridad;
  audiencia: Audiencia;
}

@Component({
  selector: 'app-editar-audiencia',
  templateUrl: './editar-audiencia.component.html',
  styleUrls: ['./editar-audiencia.component.css']
})
export class EditarAudienciaComponent implements OnInit {
  audiencia: Audiencia = {
    aud_id: 0,
    sala: {
      sal_id: undefined,
      sal_nombre: undefined,
      distrito: {
        dis_id: undefined,
        dis_nombre: undefined
      }
    },
  };

  juez?: number ;
  fiscal?: number ;
  defensor?: number ;

  audienciaExt: AudienciaExt = {
    autoridad: {
      distrito: {}
    },
    audiencia: {
      aud_id: 0,
      sala: {
        distrito: {}
      }
    }
  };
  // Aquí guardaremos todas las autoridades
  audienciasExt: AudienciaExt[] = []
  autoridades: Autoridad[] = [];
  autoridadesJuez: Autoridad[] = [];
  autoridadesFiscal: Autoridad[] = [];
  autoridadesDefensor: Autoridad[] = [];

  constructor(
    private audienciaService: AudienciaService,
    private audienciaExtensionService: AudienciaExtensionService,
    private route: ActivatedRoute,
    private autoridadesService: AutoridadesService,
    private router: Router
  ) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      const audId = Number(id); // Convertir a número
      this.audienciaService.obtenerAudienciaPorId(audId).subscribe(
        data => {
          this.audiencia = data;  // Verifica que esta asignación sea correcta
          // Este metodo es para obtener el juez, fiscal y defensor seleccionado
          this.obtenerAudienciasExtension()
          this.obtenerAutoridades(); // Ahora que la audiencia está cargada, llamamos obtenerAutoridades
        },
        error => {
          console.error('Error al obtener la audiencia:', error);
        }
      );
    }
  }
  
  obtenerAutoridades() {
    if (this.audiencia.sala?.distrito?.dis_nombre) {
      this.autoridadesService.getAutoridades().subscribe(
        (data: Autoridad[]) => {
          this.autoridades = data.filter(a => a.distrito?.dis_nombre === this.audiencia.sala.distrito.dis_nombre);
          this.autoridadesJuez = this.autoridades.filter(a => a.aut_tipo === 'juez');
          this.autoridadesFiscal = this.autoridades.filter(a => a.aut_tipo === 'fiscal');
          this.autoridadesDefensor = this.autoridades.filter(a => a.aut_tipo === 'defensor');
          // this.asignarAutoridadesSeleccionadas();  // Llamamos después de cargar las autoridades
        },
        error => {
          console.error('Error al obtener autoridades:', error);
        }
      );
    } else {
      console.error('No se ha definido el distrito de la audiencia.');
    }
  }


  obtenerAudienciasExtension() {
    this.audienciaExtensionService.getByAudiencia(this.audiencia.aud_id)
      .subscribe((audiencias: AudienciaExt[]) => {
        this.audienciasExt = audiencias; // Guardamos los datos en el array

        // Recorrer el array audienciasExt
      for (let audienciaExt of this.audienciasExt) {
        // Verificar si el tipo de autoridad es 'juez'
        if (audienciaExt.autoridad.aut_tipo === 'juez') {
          // Asignar el aut_id a la variable juez
          this.juez = audienciaExt.autoridad.aut_id;
        }

        if (audienciaExt.autoridad.aut_tipo === 'fiscal') {
          // Asignar el aut_id a la variable juez
          this.fiscal = audienciaExt.autoridad.aut_id;
        }

        if (audienciaExt.autoridad.aut_tipo === 'defensor') {
          // Asignar el aut_id a la variable juez
          this.defensor = audienciaExt.autoridad.aut_id;
        }
      }
      });
  }

  guardarCambios() {
    // Si no hay ningún juez, fiscal o defensor seleccionado, no hacemos nada
    if (this.juez === undefined || this.fiscal === undefined || this.defensor === undefined) {
      console.error('Faltan autoridades seleccionadas');
      return;
    }
  
    // Primero recorremos todas las audienciasExt para actualizar las autoridades correspondientes
    for (let audienciaExt of this.audienciasExt) {
      if (audienciaExt.autoridad.aut_tipo === 'juez') {
        // Si el tipo de autoridad es 'juez', actualizamos el ID de la autoridad
        audienciaExt.autoridad.aut_id = this.juez;
      }
      if (audienciaExt.autoridad.aut_tipo === 'fiscal') {
        // Si el tipo de autoridad es 'fiscal', actualizamos el ID de la autoridad
        audienciaExt.autoridad.aut_id = this.fiscal;
      }
      if (audienciaExt.autoridad.aut_tipo === 'defensor') {
        // Si el tipo de autoridad es 'defensor', actualizamos el ID de la autoridad
        audienciaExt.autoridad.aut_id = this.defensor;
      }
  
      // Actualizar la autoridad en el servidor mediante la API
      this.audienciaExtensionService.updateAutoridad(audienciaExt.eau_id!, audienciaExt).subscribe(
        response => {
          console.log('Autoridad actualizada: ', response);
        },
        error => {
          console.error('Error al actualizar la autoridad: ', error);
        }
      );
    }
  
    // Después de realizar las actualizaciones, redirigimos a la lista de audiencias
    this.router.navigate(['/lista-audiencias']);
  }
  
}
