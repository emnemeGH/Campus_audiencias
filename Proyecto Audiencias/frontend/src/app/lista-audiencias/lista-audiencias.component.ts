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
    this.audienciasFiltradas = this.audiencias.filter(audiencia => {
      const coincideDistrito = this.distritoSeleccionado ? audiencia.distrito.trim() === this.distritoSeleccionado.trim() : true;
      const coincideSala = this.salaSeleccionada ? audiencia.sala.trim() === this.salaSeleccionada.trim() : true;
      const coincideFecha = this.fechaSeleccionada ? audiencia.fecha === this.fechaSeleccionada : true;

      return coincideDistrito && coincideSala && coincideFecha;
    });
    
  }
}
