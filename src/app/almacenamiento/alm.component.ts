import { Component, OnInit } from '@angular/core';
import { ProductosService } from '../productos.service';
import { CarritoService } from '../carrito.service';

@Component({
  selector: 'app-almacenamiento',
  templateUrl: './alm.component.html',
  styleUrls: ['./alm.component.css']
})
export class AlmComponent implements OnInit {
  listaProductos:Array<Object>;

  constructor(public _productosService:ProductosService, private carrito: CarritoService) {
 
  }

 ngOnInit() {
   this.listaProductos = this._productosService.obtenerProductosCategoria('Almacenamiento');
 }
 agregarProducto(nuevoProducto: any){
  this.carrito.agregarProducto(nuevoProducto);
}

}
