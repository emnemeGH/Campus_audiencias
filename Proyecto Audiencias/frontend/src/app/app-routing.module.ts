import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistroComponent } from './registro/registro.component';
import { ListaAudienciasComponent } from './lista-audiencias/lista-audiencias.component';
import { CrearAudienciaComponent } from './crear-audiencia/crear-audiencia.component';

const routes: Routes = [
  { path: '', component: LoginComponent },  // Ruta para el login
  { path: 'registro', component: RegistroComponent },  // Ruta para el registro
  { path: 'lista-audiencias', component: ListaAudienciasComponent },  // Ruta para la pág principal donde se ven todas las audiencias
  { path: 'crear-audiencia', component: CrearAudienciaComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
