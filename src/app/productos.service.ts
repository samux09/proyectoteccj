import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  productos: any[];

  constructor(private http: HttpClient, private router: Router) {
    this.consultarProductos();
  }

  consultarProductos() {
    this.productos = [
      { idproducto: 1, nombre: 'Gabinete Cooler Master Mastercase H500P Mesh White RGB / Cristal Templado', categoria: 'Gabinete', precio: 3000, descuento: .5, photopath: '/assets/img/gabinete.jpg' },
      { idproducto: 2, nombre: 'Tarjeta de video Radeon RX 5700 XT 8GB GDDR6 / Asus TUF Gaming 3 Fan Edition / HDMI, DP', categoria: 'Tarjeta de video', precio: 3000, descuento: 0, photopath: '/assets/img/tarjetadevideo.jpg' },
      { idproducto: 3, nombre: 'Unidad de Estado Solido SSD M.2 Gen 3 X4 2TB addlink S70 / AD2TBS70M2P', categoria: 'Almacenamiento', precio: 1750, descuento: 0, photopath: '/assets/img/ssd.jpg' },
      { idproducto: 5, nombre: 'Memoria RAM DDR4 4GB 2400MHz Kingston Hyper X Fury 1 Modulo Negro HX424C15FB3/4', categoria: 'Memoria RAM', precio: 3750, descuento: 0, photopath: '/assets/img/ram.jpg' },
      { idproducto: 6, nombre: 'Procesador Intel Core i5 9400F 2.90GHz / 4.10GHz / 6 Nucleos / 6 Hilos / Socket 1151-v2 - Requiere Tarjeta de Video', categoria: 'Procesador', precio: 4500, descuento: 0, photopath: '/assets/img/procesador.jpg' },
      { idproducto: 7, nombre: 'Enfriamiento Liquido Eagle Warrior Cyclone 240 RGB / Intel y AMD / AWC240CYCLONEEGW', categoria: 'Enf.Liquido', precio: 4500, descuento: 0, photopath: '/assets/img/enfliquido.jpg' },
      { idproducto: 4, nombre: 'Fuente de poder Asus ROG Strix 650W / 80 Plus Gold Full Modular / ROG-STRIX-650G', categoria: 'Fuente de poder', precio: 2600, descuento: 0, photopath: '/assets/img/fuentedepoder.jpg' },
      { idproducto: 4, nombre: 'Fuente de poder EVGA 600W / 80 Plus B / EVGA600-B', categoria: 'Fuente de poder', precio: 2600, descuento: 0, photopath: '/assets/img/600.png' },
      { idproducto: 4, nombre: 'Fuente de poder EVGA 700W / 80 Plus / EVGA-700', categoria: 'Fuente de poder', precio: 2600, descuento: 0, photopath: '/assets/img/700.jpg' }
    ];
      let formData: FormData = new FormData();
      formData.append('opcion', '1'); 
  }

  obtenerListaProductos() {
    return this.productos;
  }

  obtenerProductosCategoria(sCategoria: string) {
    return this.productos.filter(
      producto => producto.categoria.toLowerCase() === sCategoria.toLowerCase());
  }
  obtenerProductosPromocion() {
    return this.productos.filter(
      producto => producto.descuento > 0
    );
  }
  obtenerListaBusqueda(sCategoria: string, sPalabra: string) {
    if (sCategoria.toLocaleLowerCase() == 'todas') {
      return this.productos.filter(
        (producto => producto.nombre.toLocaleLowerCase().indexOf(sPalabra.toLocaleLowerCase()) >= 0)
      );
    }else if (sCategoria.toLocaleLowerCase() == 'promociones'){
      return this.productos.filter(
        (producto => producto.descuento > 0 &&
          producto.nombre.toLocaleLowerCase().indexOf(sPalabra.toLocaleLowerCase()) >= 0)
      );
    } else {
      return this.productos.filter(
        (producto => producto.nombre.toLocaleLowerCase().indexOf(sPalabra.toLocaleLowerCase()) >= 0 &&
          producto.categoria.toLocaleLowerCase().indexOf(sCategoria.toLocaleLowerCase()) >= 0)
      );
    }
  }
}