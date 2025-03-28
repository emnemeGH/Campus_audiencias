import { Component } from '@angular/core';
import { AudienciaService } from '../services/audiencia.service';
import { Router } from '@angular/router';
import { AutoridadesService } from '../services/autoridades.service'
import { UsuariosService } from '../services/usuarios.service';
import { DistritoService } from '../services/distrito.service';


export interface Usuario {
  usr_id?: number,
  usr_nombre?: string,
  usrMail?: string,
  usrUsername?: string,
  usrPassword?: string,
  usrIsAdmin?: boolean,
  distrito: {
    dis_id?: number;
    dis_nombre?: string;
  };
}

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

export interface Audiencia {
  aud_id?: number;  // ID autoincremental
  aud_nombre?: string;
  aud_usrins?: number;
  aud_fecins?: string;
  aud_usrmod?: number;
  aud_fecmod?: string;
  aud_estado?: boolean;
  aud_tipo?: 'Demorada' | 'Programada' | 'Realziada' | 'Suspendida';
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

@Component({
  selector: 'app-agregar-audiencia',
  templateUrl: './crear-audiencia.component.html',
  styleUrls: ['./crear-audiencia.component.css']
})

export class CrearAudienciaComponent {
  audiencia: Audiencia = {
    aud_estado: true,
    sala: {
      sal_id: undefined,
      sal_nombre: undefined,
      distrito: {
        dis_id: undefined,
        dis_nombre: undefined
      }
    }
  };

  juez?: number;
  fiscal?: number;
  defensor?: number;


  autoridades: Autoridad[] = [];
  autoridadesJuez: Autoridad[] = [];
  autoridadesFiscal: Autoridad[] = [];
  autoridadesDefensor: Autoridad[] = [];
  usuario: Usuario | undefined;
  distritos: string[] = []; // Almacena los distritos obtenidos desde la API

  constructor(
    private audienciaService: AudienciaService, 
    private router: Router,
    private autoridadesService: AutoridadesService,
    private usuariosService: UsuariosService,
    private distritoService: DistritoService  // Para obtener el id del usuario logueado
  ) { }

  ngOnInit() {
    this.obtenerUsuarioAutenticado();
    this.obtenerDistritos();
  }

  obtenerDistritos() {
    this.distritoService.obtenerDistritos().subscribe(
      (distritos: string[]) => {
        this.distritos = distritos; // Asignar los distritos a la variable
      },
      (error) => {
        console.error('Error al obtener los distritos:', error);
      }
    );
  }

  obtenerUsuarioAutenticado() {
    const usuarioId = parseInt(localStorage.getItem('usuarioId') || '0');

    if (usuarioId) {
      this.usuariosService.getUsuarioPorId(usuarioId).subscribe(
        (usuario: Usuario) => {
          this.usuario = usuario;  // Aquí ya no es un arreglo
          if (usuario?.distrito?.dis_nombre) {
            this.audiencia.sala.distrito.dis_nombre = usuario.distrito.dis_nombre;
            this.obtenerAutoridades();
          }
        },
        (error: any) => {
          console.error('Error al obtener el usuario autenticado:', error);
        }
      );
    } else {
      console.error('Usuario no autenticado o ID no encontrado.');
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

  guardarAudiencia() {
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
      if (this.juez) {
        this.audienciaService.agregarRelacionAudienciaAutoridad(audienciaId, this.juez)
          .subscribe();
      }
      if (this.fiscal) {
        this.audienciaService.agregarRelacionAudienciaAutoridad(audienciaId, this.fiscal)
          .subscribe();
      }
      if (this.defensor) {
        this.audienciaService.agregarRelacionAudienciaAutoridad(audienciaId, this.defensor)
          .subscribe();
      }

      this.router.navigate(['/lista-audiencias']);
    });
  }

  actualizarDatos() {
    this.juez = 0
    this.fiscal = 0
    this.defensor = 0
    this.audiencia.sala.sal_id = 0;
    this.obtenerAutoridades()
    // Lógica para actualizar los datos al cambiar el distrito
    console.log(this.audiencia.sala.distrito.dis_nombre);
  }


}
