import { Component, OnInit } from '@angular/core';
import { ProductosService } from '../productos.service'
import { CarritoService } from '../carrito.service';

@Component({
  selector: 'app-promociones',
  templateUrl: './promociones.component.html',
  styleUrls: ['./promociones.component.css']
})
export class PromocionesComponent implements OnInit {
  listaProductos: Array<Object>;

  constructor(public _productosService: ProductosService, private carrito: CarritoService) { }

  ngOnInit() {
    this.listaProductos = this._productosService.obtenerProductosPromocion()
  }

  agregarProducto(nuevoProducto: any) {
    this.carrito.agregarProducto(nuevoProducto);
  }

}
