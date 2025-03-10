import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AudienciaService } from '../services/audiencia.service';

@Component({
  selector: 'app-editar-audiencia',
  templateUrl: './editar-audiencia.component.html',
  styleUrls: ['./editar-audiencia.component.css']
})
export class EditarAudienciaComponent implements OnInit {
  audiencia: any = {};

  constructor(
    private audienciaService: AudienciaService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.audienciaService.obtenerAudienciaPorId(id).subscribe(
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
    this.audienciaService.editarAudiencia(this.audiencia).subscribe(
      () => {
        alert('Cambios guardados correctamente.');
        this.router.navigate(['/audiencias']);
      },
      error => {
        console.error('Error al guardar cambios:', error);
        alert('Hubo un error al guardar los cambios.');
      }
    );
  }
}
