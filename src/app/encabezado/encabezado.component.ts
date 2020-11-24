import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../user.model';
import { Router, NavigationStart } from '@angular/router';

@Component({
  selector: 'app-encabezado',
  templateUrl: './encabezado.component.html',
  styleUrls: ['./encabezado.component.css']
})
export class EncabezadoComponent implements OnInit {

  telefono:string = "+52 6671751821";
  correo:string = "techch@gmail.com";
  direccion:string = "Pablo de Villavicencio 2933";
  direcMaps:string = "https://www.google.com/";
  userLogged : User;


  constructor(private usuarioService : UserService, private router: Router) {
    router.events.forEach((event) => {
      if(event instanceof NavigationStart) {
        this.userLogged = this.usuarioService.getUsuarioLogged();
      }
    });
  }

  ngOnInit() {
    
  }

}
