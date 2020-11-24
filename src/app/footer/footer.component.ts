import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  telefono:string = "+526671751821";
  correo:string = "techch@gmail.com";
  direccion:string = "Pablo de Villavicencio 2933";
  direcMaps:string = "https://www.google.com";

  anio:number = new Date().getFullYear();
  constructor() { }

  ngOnInit() {
  }

}
