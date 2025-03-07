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
    "Venado Tuerto": ["Sala VC Venado Tuerto", "Sala 1 Venado Tuerto"],
    "Reconquista": ["Sala 1 Reconquista", "Sala 2 Reconquista"],
    "San Jorge": ["Sala 1 San Jorge", "Sala 2 San Jorge"]
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
    const distrito = this.distritoSeleccionado?.trim() || '';
    const sala = this.salaSeleccionada?.trim() || '';
    const fecha = this.fechaSeleccionada || '';
  
    this.audienciasFiltradas = this.audiencias.filter(audiencia => {
      return (!distrito || audiencia.distrito?.trim() === distrito) &&
             (!sala || audiencia.sala?.trim() === sala) &&
             (!fecha || audiencia.fecha === fecha);
    });
  }
}
