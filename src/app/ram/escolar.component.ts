import { Component, OnInit } from '@angular/core';
import { ProductosService } from '../productos.service';
import { CarritoService } from '../carrito.service';

@Component({
  selector: 'app-escolar',
  templateUrl: './escolar.component.html',
  styleUrls: ['./escolar.component.css']
})
export class EscolarComponent implements OnInit {
  listaProductos:Array<Object>;

  constructor(public _productosService:ProductosService, private carrito: CarritoService) {
 
  }

 ngOnInit() {
   this.listaProductos = this._productosService.obtenerProductosCategoria('Memoria RAM');
 }
 agregarProducto(nuevoProducto: any){
  this.carrito.agregarProducto(nuevoProducto);
}

}
