import { Component, OnInit} from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-cuenta',
  templateUrl: './cuenta.component.html',
  styleUrls: ['./cuenta.component.css']
})
export class CuentaComponent implements OnInit {

  constructor(private router: Router, private user : UserService) { }

  ngOnInit() {
    if(!this.user.getUsuarioLogged()){
      this.router.navigateByUrl('/login');
    }
  }

  cerrarSesion(){
    this.user.cerrarSesion();
    this.router.navigateByUrl('/inicio');
  }

}
