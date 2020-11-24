import {RouterModule, Routes} from '@angular/router'
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'
import { NotifierModule } from 'angular-notifier';
import { FormsModule, ReactiveFormsModule} from '@angular/forms'
import { NgxPayPalModule } from 'ngx-paypal';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EncabezadoComponent } from './encabezado/encabezado.component';
import { BusquedaComponent } from './busqueda/busqueda.component';
import { MenuComponent } from './menu/menu.component';
import { CuerpoComponent } from './cuerpo/cuerpo.component';
import { FooterComponent } from './footer/footer.component';
import { SiguenosComponent } from './siguenos/siguenos.component';
import { InicioComponent } from './inicio/inicio.component';
import { PromocionesComponent } from './promociones/promociones.component';
import { HogarComponent } from './procesadores/hogar.component';
import { OficinaComponent } from './grafica/oficina.component';
import { EscolarComponent } from './ram/escolar.component';
import { AlmComponent } from './almacenamiento/alm.component';
import { FuenteComponent } from './fuente/fuente.component';
import { ExterioresComponent } from './exteriores/exteriores.component';

import {ProductosService} from './productos.service'
import {CarritoService} from './carrito.service';
import { LoginComponent } from './login/login.component'
import { LoginService } from './login.service';
import { UserService } from './user.service';
import { CuentaComponent } from './cuenta/cuenta.component';
import { CarritoComponent } from './carrito/carrito.component';
import { BuscadorComponent } from './buscador/buscador.component';
import { OrdenComponent } from './orden/orden.component';
import { ProcesopagoComponent } from './procesopago/procesopago.component';
import { PaypalComponent } from './paypal/paypal.component';

@NgModule({
  declarations: [
    AppComponent,
    EncabezadoComponent,
    BusquedaComponent,
    MenuComponent,
    CuerpoComponent,
    FooterComponent,
    SiguenosComponent,
    InicioComponent,
    PromocionesComponent,
    HogarComponent,
    AlmComponent,
    FuenteComponent,
    OficinaComponent,
    EscolarComponent,
    ExterioresComponent,
    LoginComponent,
    CuentaComponent,
    CarritoComponent,
    BuscadorComponent,
    OrdenComponent,
    ProcesopagoComponent,
    PaypalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NotifierModule.withConfig( {
      position:{
        horizontal:{
          position:'right'
        }
      }
    } ),
    FormsModule,
    ReactiveFormsModule,
    NgxPayPalModule
  ],
  providers: [
    ProductosService,
    CarritoService,
    LoginService,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
