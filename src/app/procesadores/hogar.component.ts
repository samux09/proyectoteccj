import { Component, OnInit } from '@angular/core';
import {ProductosService} from '../productos.service'
import { CarritoService } from '../carrito.service';

@Component({
  selector: 'app-hogar',
  templateUrl: './hogar.component.html',
  styleUrls: ['./hogar.component.css']
})
export class HogarComponent implements OnInit {
  listaProductos:Array<Object>;

  constructor(public _productosService:ProductosService, private carrito: CarritoService) {
 
  }

 ngOnInit() {
   this.listaProductos = this._productosService.obtenerProductosCategoria('procesador');
 }

 agregarProducto(nuevoProducto: any){
    this.carrito.agregarProducto(nuevoProducto);
  }
}
