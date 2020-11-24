import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  listaCarrito: any = [];
  cantidadArticulos : number = 0;

  constructor() { }

  ngOnInit(): void {
   this.listaCarrito = JSON.parse(localStorage.getItem('carrito'));
  }
  disminuirCantidad(id : number){
    console.log(id);
    if(JSON.parse(localStorage.getItem('carrito')) !=null )
      this.listaCarrito = JSON.parse(localStorage.getItem('carrito'));

    let index = this.listaCarrito.findIndex(prod=> prod.idproducto == id)
    if(this.listaCarrito[index].cantidad > 1){
      this.listaCarrito[index].cantidad--;
    }else{
      this.listaCarrito = this.listaCarrito.filter(
        producto => producto.idproducto != id);
    }
    localStorage.clear();
    localStorage.setItem('carrito', JSON.stringify(this.listaCarrito));
  }
  aumentarCantidad(id : number){
    if(JSON.parse(localStorage.getItem('carrito')) !=null )
      this.listaCarrito = JSON.parse(localStorage.getItem('carrito'));

    let index = this.listaCarrito.findIndex(prod=> prod.idproducto == id)
    if(this.listaCarrito[index].cantidad > 0){
      this.listaCarrito[index].cantidad++;
    }
    localStorage.clear();
    localStorage.setItem('carrito', JSON.stringify(this.listaCarrito));
  }
  agregarProducto(producto: any){
    producto.cantidad = 1;
    console.log(producto.idproducto);
    if(JSON.parse(localStorage.getItem('carrito')) !=null )
      this.listaCarrito = JSON.parse(localStorage.getItem('carrito'));

    if(this.listaCarrito != null){

      let index = this.listaCarrito.findIndex(prod=> prod.idproducto == producto.idproducto)
      if(index > -1){
        this.listaCarrito[index].cantidad++;
      }else{
        this.listaCarrito.push(producto);
      }
      
    }else{
      console.log("aloooo");
      this.listaCarrito.push(producto);
      
    }
    localStorage.clear();
    localStorage.setItem('carrito', JSON.stringify(this.listaCarrito));
  }

  obtenerCarrito(){
    return JSON.parse(localStorage.getItem('carrito'));
  }

  eliminarArticulo(idProducto : number){
    if(JSON.parse(localStorage.getItem('carrito')) !=null )
      this.listaCarrito = JSON.parse(localStorage.getItem('carrito'));

    this.listaCarrito = this.listaCarrito.filter(
      producto => producto.idproducto !=  idProducto
    );
    
    localStorage.clear();
    localStorage.setItem('carrito', JSON.stringify(this.listaCarrito));
  }

  obtenerCantidadArticulos(){
    let total :number = 0;
    if(JSON.parse(localStorage.getItem('carrito'))!=null){
      JSON.parse(localStorage.getItem('carrito')).forEach(element => {
        total += element.cantidad;
      });
    }
    return total;
  }
  obtenerSubTotal(){
    let total :number = 0;
    if(JSON.parse(localStorage.getItem('carrito'))!=null){
      JSON.parse(localStorage.getItem('carrito')).forEach(element => {
        total += (element.precio*element.cantidad)*(1-element.descuento);
      });
    }
    return total;
  }

  limpiarCarrito(){
    this.listaCarrito = null;
    localStorage.removeItem('carrito');
  }
}
