import { Component, OnInit } from '@angular/core';
import { AudienciaService } from '../services/audiencia.service';

@Component({
  selector: 'app-lista-audiencias',
  templateUrl: './lista-audiencias.component.html',
  styleUrls: ['./lista-audiencias.component.css']
})
export class ListaAudienciasComponent implements OnInit {
  audiencias: any[] = [];
  mostrarFormulario: boolean = false;
  distritoSeleccionado: string = '';
  salaSeleccionada: string = '';
  salasDisponibles: string[] = [];
  audienciasFiltradas: any[] = [];
  fechaSeleccionada: string = '';

  salasPorDistrito: { [key: string]: string[] } = {
    "Distrito Capital": ["Sala Penal 1", "Sala Civil 1", "Sala Familia 1", "Cámara Gessel 1"],
    "Distrito Sur": ["Sala Penal 2", "Sala Civil 2", "Sala Familia 2", "Cámara Gessel 2"],
    "Distrito Norte": ["Sala Penal 3", "Sala Civil 3", "Sala Familia 3", "Cámara Gessel 3"]
  };

  constructor(private audienciaService: AudienciaService) {}

  ngOnInit() {
    this.obtenerAudiencias();
  }

  obtenerAudiencias() {
    this.audienciaService.getAudiencias().subscribe(
      data => {
        this.audiencias = data;
        this.audienciasFiltradas = [...this.audiencias];
        },
      error => {
        console.error('Error al obtener audiencias:', error);
        }
    );
  }

  toggleFormulario() {
    this.mostrarFormulario = !this.mostrarFormulario;
  }

  actualizarSalas() {
    this.salasDisponibles = this.salasPorDistrito[this.distritoSeleccionado] || [];
    this.salaSeleccionada = ''; // Limpia la selección de sala cuando cambia de distrito
  }

  filtrarAudiencias() {
    console.log(this.audiencias)
  this.audienciasFiltradas = this.audiencias.filter((audiencia) => {
    // Verificar si 'sal_id' y 'distrito' están definidos antes de acceder a ellos
    const coincideDistrito = this.distritoSeleccionado 
      ? audiencia.sal_id && audiencia.sal_id.distrito && audiencia.sal_id.distrito.dis_id
        ? audiencia.sal_id.distrito.dis_nombre.trim() === this.distritoSeleccionado.trim() 
        : false 
      : true;

    // Verificar si 'sal_nombre' está definido antes de acceder
    const coincideSala = this.salaSeleccionada 
      ? audiencia.sal_id && audiencia.sal_id.sal_nombre 
        ? audiencia.sal_id.sal_nombre.trim() === this.salaSeleccionada.trim() 
        : false 
      : true;

    // Filtrado por fecha
    const coincideFecha = this.fechaSeleccionada 
      ? audiencia.aud_fecha === this.fechaSeleccionada 
      : true;

    return coincideDistrito && coincideSala && coincideFecha;
  });
  console.log(this.audienciasFiltradas);
}

borrarFiltros() {
  // Reiniciar los filtros
  this.distritoSeleccionado = '';
  this.salaSeleccionada = '';
  this.fechaSeleccionada = '';

  // sacarle los filtros a la lista de audiencias
  this.audienciasFiltradas = [...this.audiencias];
}

}
