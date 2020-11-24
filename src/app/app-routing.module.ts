import { NgModule } from '@angular/core'; 
import { Routes, RouterModule, Router } from '@angular/router';
import { InicioComponent } from './inicio/inicio.component';
import { PromocionesComponent } from './promociones/promociones.component';
import { HogarComponent } from './procesadores/hogar.component';
import { OficinaComponent } from './grafica/oficina.component';
import { EscolarComponent } from './ram/escolar.component';
import { AlmComponent } from './almacenamiento/alm.component';
import { ExterioresComponent } from './exteriores/exteriores.component';
import { LoginComponent } from './login/login.component';
import { CuentaComponent } from './cuenta/cuenta.component';
import { CarritoComponent } from './carrito/carrito.component';
import { OrdenComponent } from './orden/orden.component';
import { BuscadorComponent } from './buscador/buscador.component';
import { ProcesopagoComponent } from './procesopago/procesopago.component';
import { FuenteComponent  } from './fuente/fuente.component';


const routes: Routes = [
  { path:'inicio', component: InicioComponent},
  { path:'promociones', component: PromocionesComponent},
  { path:'procesadores', component: HogarComponent},
  { path:'grafica', component: OficinaComponent},
  { path:'ram', component: EscolarComponent},
  { path:'almacenamiento', component: AlmComponent},
  { path:'fuente', component: FuenteComponent},
  { path:'exteriores', component: ExterioresComponent},
  { path:'login', component: LoginComponent},
  { path:'cuenta', component: CuentaComponent},
  { path:'carrito', component: CarritoComponent},
  { path:'orden', component: OrdenComponent},
  { path:'procesopago', component: ProcesopagoComponent},
  { path:'buscador/:categoria/:palabra', component: BuscadorComponent},
  
  { path:'', component: InicioComponent, pathMatch:'full'},
  { path:'**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { 
  constructor(){
  }
}