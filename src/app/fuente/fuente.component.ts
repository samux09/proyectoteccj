import { Component, OnInit } from '@angular/core';
import { ProductosService } from '../productos.service';
import { CarritoService } from '../carrito.service';

@Component({
  selector: 'app-fuente',
  templateUrl: './fuente.component.html',
  styleUrls: ['./fuente.component.css']
})
export class FuenteComponent implements OnInit {
  listaProductos:Array<Object>;

  constructor(public _productosService:ProductosService, private carrito: CarritoService) {
 
  }

 ngOnInit() {
   this.listaProductos = this._productosService.obtenerProductosCategoria('Fuente de poder');
 }
 agregarProducto(nuevoProducto: any){
  this.carrito.agregarProducto(nuevoProducto);
}

}
