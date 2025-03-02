import { Component, OnInit } from '@angular/core';
import { AudienciaService } from '../audiencia.service';
@Component({
  selector: 'app-lista-audiencias',
  templateUrl: './lista-audiencias.component.html',
  styleUrls: ['./lista-audiencias.component.css']
})
export class ListaAudienciasComponent implements OnInit{


  constructor(private audienciaService: AudienciaService){}

  audiencias: { cuij: string; caratula: string; tipo: string }[] = []; 
  
  ngOnInit() {
    this.audiencias = [
      { cuij: '21087069726', caratula: 'MARTINEZ s/Homicidio doloso simple y otros', tipo: 'Juicio de querella' },
      { cuij: '21087069727', caratula: 'PEREZ s/Robo agravado', tipo: 'Juicio oral' },
      { cuij: '21087069728', caratula: 'GOMEZ s/Estafa', tipo: 'Audiencia preliminar' },
    ];
  }
  editarAudiencia(){
    console.log("audiencia editada");
  }
}
