import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegistroComponent } from './registro/registro.component';
import { FormsModule } from '@angular/forms';
import { ListaAudienciasComponent } from './lista-audiencias/lista-audiencias.component';
import { CrearAudienciaComponent } from './crear-audiencia/crear-audiencia.component';
import { HttpClientModule } from '@angular/common/http';
import { EditarAudienciaComponent } from './editar-audiencia/editar-audiencia.component';
import { HoraPipe } from './pipes/hora-pipe.pipe';
import { ListaUsuarioComponent } from './lista-usuario/lista-usuario.component';
import { CrearUsuarioComponent } from './crear-usuario/crear-usuario.component';
import { LoginAutoridadComponent } from './login-autoridad/login-autoridad.component';
import { EditarUsuarioComponent } from './editar-usuario/editar-usuario.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistroComponent,
    ListaAudienciasComponent,
    CrearAudienciaComponent,
    EditarAudienciaComponent,
    HoraPipe,
    ListaUsuarioComponent,
    CrearUsuarioComponent,
    LoginAutoridadComponent,
    EditarUsuarioComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
