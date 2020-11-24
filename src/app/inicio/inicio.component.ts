import { Component, OnInit } from '@angular/core';
import {ProductosService} from '../productos.service'
import { CarritoService } from '../carrito.service';
import { NotifierService } from 'angular-notifier';


@Component({
  selector: 'app-inicio',
  template: '',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  listaProductos:Array<Object>;
  private readonly notifier: NotifierService;

  constructor(notifierService: NotifierService, public _productosService:ProductosService, public carrito: CarritoService) {
    this.notifier = notifierService;
   }

  ngOnInit() {
    this.listaProductos = this._productosService.obtenerListaProductos();
  }

  agregarProducto(nuevoProducto: any){
    this.carrito.agregarProducto(nuevoProducto);
    this.notifier.notify('success', 'Se agregó '+nuevoProducto.nombre+" al carrito");
  }

}
