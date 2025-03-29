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
  distritos: any[] = []; 
  distritoUsuario: any = null;

  constructor(
    private audienciaService: AudienciaService, 
    private router: Router,
    private autoridadesService: AutoridadesService,
    private usuariosService: UsuariosService,
    private distritoService: DistritoService  
  ) { }

  ngOnInit() {
    this.obtenerUsuarioAutenticado();
    this.obtenerAutoridades();
    this.obtenerDistritos();
  }

  obtenerDistritos() {
    this.distritoService.obtenerDistritos().subscribe(
      (distritos: any[]) => {
        this.distritos = distritos; // Asignar los distritos a la variable
        console.log("distritos obtenidos", distritos);
      },
      (error) => {
        console.error('Error al obtener los distritos:', error);
      }
    );
  }

  obtenerUsuarioAutenticado() {
    const usuarioString = localStorage.getItem('usuario'); 
    console.log("Valor crudo de localStorage:", usuarioString);

    const usuario = usuarioString ? JSON.parse(usuarioString) : null;

    if (usuario && usuario.distrito) {
        this.distritoUsuario = usuario.distrito; // Guarda el distrito del usuario
        console.log("Distrito del usuario autenticado:", this.distritoUsuario);
        this.audiencia.sala.distrito.dis_nombre = this.distritoUsuario.dis_nombre; // Setea el distrito en la audiencia
        console.log("Distrito de la audiencia:", this.audiencia.sala.distrito.dis_nombre);
    } else {
        console.error("Usuario no autenticado o distrito no encontrado.");
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
            console.log("autoridades:", this.autoridades);
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
    // Lógica para actualizar los datos al cambiar el distrito
    console.log(this.audiencia.sala.distrito.dis_nombre);
  }


}
