import { Component, OnInit } from '@angular/core';
import { ProductosService } from '../productos.service';
import { CarritoService } from '../carrito.service';

@Component({
  selector: 'app-exteriores',
  templateUrl: './exteriores.component.html',
  styleUrls: ['./exteriores.component.css']
})
export class ExterioresComponent implements OnInit {
  listaProductos: Array<Object>;

  constructor(public _productosService: ProductosService, private carrito: CarritoService) {

  }

  ngOnInit() {
    this.listaProductos = this._productosService.obtenerProductosCategoria('Gabinete');
  }

  agregarProducto(nuevoProducto: any) {
    this.carrito.agregarProducto(nuevoProducto);
  }


}
