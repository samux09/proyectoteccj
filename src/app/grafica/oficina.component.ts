import { Component, OnInit } from '@angular/core';
import { ProductosService } from '../productos.service';
import { CarritoService } from '../carrito.service';

@Component({
  selector: 'app-oficina',
  templateUrl: './oficina.component.html',
  styleUrls: ['./oficina.component.css']
})
export class OficinaComponent implements OnInit {
  listaProductos:Array<Object>;

  constructor(public _productosService:ProductosService, private carrito: CarritoService) {
 
  }

 ngOnInit() {
   this.listaProductos = this._productosService.obtenerProductosCategoria('Tarjeta de video');
 }

 agregarProducto(nuevoProducto: any){
  this.carrito.agregarProducto(nuevoProducto);
}
}
