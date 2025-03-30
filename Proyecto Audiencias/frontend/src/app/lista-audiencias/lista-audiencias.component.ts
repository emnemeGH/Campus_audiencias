import { Component, OnInit } from '@angular/core';
import { AudienciaService } from '../services/audiencia.service';
import { ActivatedRoute } from '@angular/router';
import { AutoridadesService } from '../services/autoridades.service'

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

@Component({
  selector: 'app-lista-audiencias',
  templateUrl: './lista-audiencias.component.html',
  styleUrls: ['./lista-audiencias.component.css']
})

export class ListaAudienciasComponent implements OnInit {
  esUsuario: boolean = true;
  audiencias: any[] = [];
  mostrarFormulario: boolean = false;
  distritoSeleccionado: string = '';
  salaSeleccionada: string = '';
  salasDisponibles: string[] = [];
  fiscalSeleccionado: string = '';
  juezSeleccionado: string = '';
  audienciasFiltradas: any[] = [];
  fechaSeleccionada: string = '';
  estadoSeleccionado: string = '';
  distritoId: number = 0;

  autoridades: Autoridad[] = [];
  autoridadesJuez: Autoridad[] = [];
  autoridadesFiscal: Autoridad[] = [];

  salasPorDistrito: { [key: string]: string[] } = {
    "Distrito Capital": ["Sala Penal 1", "Sala Civil 1", "Sala Familia 1", "Cámara Gessel 1"],
    "Distrito Sur": ["Sala Penal 2", "Sala Civil 2", "Sala Familia 2", "Cámara Gessel 2"],
    "Distrito Norte": ["Sala Penal 3", "Sala Civil 3", "Sala Familia 3", "Cámara Gessel 3"]
  };
  aut_nombre_log: string = '';

  constructor(
    private audienciaService: AudienciaService,
    private route: ActivatedRoute,
    private autoridadesService: AutoridadesService
  ) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      // Si params['esUsuario'] tiene algun valor se ejecuta la comparacion, de esta forma, aunque toquen la url y pongan true no se van a mostrar las funcionalidades
      if (params['esUsuario']) {
        this.esUsuario = false;
      }

      this.aut_nombre_log = params['idAut'];
      console.log(this.aut_nombre_log)
    });

    const usuario = JSON.parse(localStorage.getItem('usuario') || '{}');
    if (usuario && usuario.distrito && usuario.distrito.dis_id) {
      this.distritoId = usuario.distrito.dis_id;
      this.obtenerAudiencias();
    } else {
      console.error('Error: No se encontró el distrito del usuario.');
    }


  }

  obtenerAudiencias() {
    this.audienciaService.getAudiencias().subscribe(
      data => {
        if (this.esUsuario !== false) {
          // Filtrar audiencias por el distrito del operador
          this.audiencias = data.filter(audiencia => audiencia.sala?.distrito?.dis_id === this.distritoId && audiencia.audEstado);
          this.audienciasFiltradas = [...this.audiencias]; // Copia las audiencias filtradas
        } else {
          this.audiencias = data.filter(audiencia => audiencia.audEstado);
        }
  
        // Obtener autoridades por audiencia
        this.audiencias.forEach(audiencia => {
          this.audienciaService.getAutoridadesPorAudiencia(audiencia.aud_id).subscribe(
            autoridades => {
              audiencia.juez = autoridades.find(a => a.autoridad.aut_tipo === 'juez')?.autoridad.aut_nombre || '-';
              audiencia.fiscal = autoridades.find(a => a.autoridad.aut_tipo === 'fiscal')?.autoridad.aut_nombre || '-';
              audiencia.defensor = autoridades.find(a => a.autoridad.aut_tipo === 'defensor')?.autoridad.aut_nombre || '-';

              if (this.esUsuario == false) {
              this.filtrarAudienciasPorAutoridadLogeada(audiencia)
              }

            },
            error => console.error('Error obteniendo autoridades:', error)
          );
        });
        
      },
      error => {
        console.error('Error al obtener audiencias:', error);
      }
    );
  }

  filtrarAudienciasPorAutoridadLogeada(audiencia: any) {
      if(audiencia.juez == this.aut_nombre_log) {
        // Aca verificamos que no haya sido agregada previamente
        if (!this.audienciasFiltradas.some(a => a.aud_id === audiencia.aud_id)) {
          this.audienciasFiltradas.push(audiencia); // Agrega la audiencia
        }
        return;
      }

      if(audiencia.fiscal == this.aut_nombre_log) {
        if (!this.audienciasFiltradas.some(a => a.aud_id === audiencia.aud_id)) {
          this.audienciasFiltradas.push(audiencia);
        }
        return;
      }

      if(audiencia.defensor == this.aut_nombre_log) {
        if (!this.audienciasFiltradas.some(a => a.aud_id === audiencia.aud_id)) {
          this.audienciasFiltradas.push(audiencia);
        }
        return;
      }
  }
  

  toggleFormulario() {
    this.mostrarFormulario = !this.mostrarFormulario;
  }

  actualizarDatos() {
    this.obtenerAutoridadesPorDistrito()
    this.actualizarSalas()
    this.juezSeleccionado = ''; // Se reinician los valores 
    this.fiscalSeleccionado = '';
  }

  obtenerAutoridadesPorDistrito() {
    this.autoridadesService.getAutoridades().subscribe(
      (data: Autoridad[]) => {
        // Se trae a todas las autoridades segun el distrito que fue puesto en el html
        this.autoridades = data.filter(a => a.distrito?.dis_nombre === this.distritoSeleccionado);
        // almacenamos autoridades segun el tipo
        this.autoridadesJuez = this.autoridades.filter(a => a.aut_tipo === 'juez');
        this.autoridadesFiscal = this.autoridades.filter(a => a.aut_tipo === 'fiscal');
      })
  }

  actualizarSalas() {
    this.salasDisponibles = this.salasPorDistrito[this.distritoSeleccionado] || [];
    this.salaSeleccionada = ''; // Limpia la selección de sala cuando cambia de distrito
  }

  filtrarAudiencias() {

    this.audienciasFiltradas = this.audiencias.filter((audiencia) => {
      // Verificar si 'sal_id' y 'distrito' están definidos antes de acceder a ellos
      const coincideDistrito = this.distritoSeleccionado
        ? audiencia.sala?.distrito?.dis_nombre?.trim() === this.distritoSeleccionado.trim()
        : true;

      // Verificar si 'sal_nombre' está definido antes de acceder
      const coincideSala = this.salaSeleccionada
        ? audiencia.sala?.sal_nombre?.trim() === this.salaSeleccionada.trim()
        : true;

      // Filtrado por fecha
      const coincideFecha = this.fechaSeleccionada
        ? audiencia.aud_fecha === this.fechaSeleccionada
        : true;

      // utiliza el operador ternario (? :), que es una forma compacta de escribir una estructura if-else en una sola línea. Equivale a

      // let coincideJuez;
      // if (this.juezSeleccionado) { 
      //   coincideJuez = audiencia.juez === this.juezSeleccionado;
      // } else {
      //   coincideJuez = true;
      // }

      // La condición this.juezSeleccionado ? audiencia.juez === this.juezSeleccionado : true; es una forma de evitar filtrar por juez si no se selecciona ninguno. Si this.juezSeleccionado tiene un valor (es decir, si seleccionaste un juez), entonces se verifica si el juez de la audiencia coincide con el seleccionado (audiencia.juez === this.juezSeleccionado).Si this.juezSeleccionado no tiene valor (es decir, si no seleccionaste un juez), entonces se asigna true a coincideJuez. Esto significa que no se hace ninguna comprobación para el juez y se considera que "coincide".
      const coincideJuez = this.juezSeleccionado
        ? audiencia.juez === this.juezSeleccionado
        : true;

      const coincideFiscal = this.fiscalSeleccionado
        ? audiencia.fiscal === this.fiscalSeleccionado
        : true;

      const coincideEstado = this.estadoSeleccionado
        ? audiencia.aud_tipo === this.estadoSeleccionado
        : true;

      // Se filtran las audiencias Si todas las condiciones son true, la audiencia pasará el filtro y se mostrará en la interfaz. Si alguna condición es false, la audiencia no se mostrará.
      return coincideDistrito && coincideSala && coincideFecha && coincideJuez && coincideFiscal && coincideEstado;
    });
  }

  borrarFiltros() {
    // Reiniciar los filtros
    this.distritoSeleccionado = '';
    this.salaSeleccionada = '';
    this.fechaSeleccionada = '';
    this.juezSeleccionado = '';
    this.fiscalSeleccionado = '';
    this.estadoSeleccionado = '';

    // sacarle los filtros a la lista de audiencias
    this.audienciasFiltradas = [...this.audiencias];
  }


  tieneCuij(): boolean {
    return this.audienciasFiltradas.some(audiencia => audiencia.aud_cuij);
  }

  tieneCaratula(): boolean {
    return this.audienciasFiltradas.some(audiencia => audiencia.aud_caratula);
  }

  eliminarAudiencia(id: number) {
    if (!confirm("¿Estás seguro de que deseas eliminar esta audiencia?")) {
      return;
    }

    this.audienciaService.borrarAudiencia(id).subscribe(
      (response: any) => {  // ✅ Asegura que 'response' sea un objeto JSON
        console.log(response.mensaje);  // ✅ Ahora no dará error
        this.audiencias = this.audiencias.filter(a => a.aud_id !== id);
        this.audienciasFiltradas = [...this.audiencias];
        alert(response.mensaje);  // ✅ Muestra el mensaje correcto
      },
      (error) => {
        console.error("Error al eliminar la audiencia:", error);

        if (error.status === 400) {
          alert(error.error.error);  // ✅ Muestra el mensaje del backend
        } else {
          alert("Ocurrió un error al eliminar la audiencia.");
        }
      }
    );
  }



}
