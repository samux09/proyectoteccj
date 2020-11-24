import { Component, OnInit, Renderer2  } from '@angular/core';
import { CarritoService } from '../carrito.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-busqueda',
  templateUrl: './busqueda.component.html',
  styleUrls: ['./busqueda.component.css']
})
export class BusquedaComponent implements OnInit {
  iArticulosCarrito:number = 0;
  iArticulosWish:number = 0;
  arrCategorias:Array<String> = ['Promociones', 'procesadores', 'grafica', 'ram'];
  arrArticulos: any = []
  selectedOption : string = "todas";

  constructor(private renderer: Renderer2, public carrito: CarritoService, private router: Router) { }

  ngOnInit() {
    this.obtenerCarrito();

  }
  
  desplegar(elmBoton: HTMLElement, elmBoton2: HTMLElement){

    if(elmBoton.className.search('open') == -1){
      this.renderer.addClass(elmBoton, 'open');
      elmBoton.focus();
    }else
      this.renderer.removeClass(elmBoton, 'open');
  }

  getListaCategorias(){
    return this.arrCategorias;
  }

  agregarProducto(nuevoProducto: any){
    this.carrito.agregarProducto(nuevoProducto);
    this.obtenerCarrito();
  }

  obtenerCarrito(){
    this.arrArticulos = this.carrito.obtenerCarrito();
    return this.arrArticulos;
  }

  obtenerNumeroArticulos(){
    return this.carrito.obtenerCantidadArticulos();
  }

  obtenerSubtotal(){
    return this.carrito.obtenerSubTotal();
  }
  buscador(sCategoria : string, sPalabra : string){
    this.router.navigate(['/buscador', sCategoria, sPalabra]);
  }
}