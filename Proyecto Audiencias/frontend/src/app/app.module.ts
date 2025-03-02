import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegistroComponent } from './registro/registro.component';
import { FormsModule } from '@angular/forms';
import { ListaAudienciasComponent } from './lista-audiencias/lista-audiencias.component';
import { DetalleAudienciasComponent } from './detalle-audiencias/detalle-audiencias.component';
import { CrearAudienciaComponent } from './crear-audiencia/crear-audiencia.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistroComponent,
    ListaAudienciasComponent,
    DetalleAudienciasComponent,
    CrearAudienciaComponent
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
