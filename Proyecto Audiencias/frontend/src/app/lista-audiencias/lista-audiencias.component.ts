import { Component, OnInit } from '@angular/core';
import { AudienciaService } from '../audiencia.service';

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
    "03 Venado Tuerto": ["Sala VC Venado Tuerto", "Sala 1 Venado Tuerto"],
    "04 Reconquista": ["Sala 1 Reconquista", "Sala 2 Reconquista"],
    "11 San Jorge": ["Sala 1 San Jorge", "Sala 2 San Jorge"]
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

}
