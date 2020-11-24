import { Component, OnInit } from '@angular/core';
import { CarritoService } from '../carrito.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css']
})
export class CarritoComponent implements OnInit {
  arrArticulos: any;

  constructor(public carrito: CarritoService, private router: Router) { }

  ngOnInit() {
    if (this.carrito.obtenerCantidadArticulos() < 1){
      this.router.navigateByUrl('/inicio');
    }
  }

  obtenerCarrito() {
    this.arrArticulos = this.carrito.obtenerCarrito();
    return this.arrArticulos;
  }
  obtenerSubtotal() {
    return this.carrito.obtenerSubTotal();
  }
  aumentar(id: number) {
    this.carrito.aumentarCantidad(id);
  }
  disminuir(id: number) {
    this.carrito.disminuirCantidad(id);
    if (this.carrito.obtenerCantidadArticulos() < 1){
      this.router.navigateByUrl('/inicio');
    }
  }
}
