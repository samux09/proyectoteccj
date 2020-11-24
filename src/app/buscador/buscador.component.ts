import { Component, OnInit } from '@angular/core';
import {ProductosService} from '../productos.service'
import { CarritoService } from '../carrito.service';
import { NotifierService } from 'angular-notifier';
import {ActivatedRoute, Params} from '@angular/router';

@Component({
  selector: 'app-buscador',
  templateUrl: './buscador.component.html',
  styleUrls: ['./buscador.component.css']
})
export class BuscadorComponent implements OnInit {
  listaProductos:Array<Object>;
  private readonly notifier: NotifierService;
  categoria : string;
  palabra : string;

  constructor(notifierService: NotifierService, public _productosService:ProductosService, public carrito: CarritoService, private router: ActivatedRoute) {
    this.notifier = notifierService;
    this.router.params.subscribe( params => {
      this.categoria = params['categoria'];
      this.palabra  = params['palabra']
      this.listaProductos = this._productosService.obtenerListaBusqueda(this.categoria, this.palabra);
    });
   }

  ngOnInit() {
    
  }

  agregarProducto(nuevoProducto: any){
    this.carrito.agregarProducto(nuevoProducto);
    this.notifier.notify('success', 'Se agregó '+nuevoProducto.nombre+" al carrito");
  }

}
