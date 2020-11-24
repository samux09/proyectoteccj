import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-orden',
  templateUrl: './orden.component.html',
  styleUrls: ['./orden.component.css']
})
export class OrdenComponent implements OnInit {

  constructor(private router: Router, private user : UserService) { }

  ngOnInit() {
    if(!this.user.getUsuarioLogged()){
      this.router.navigateByUrl('/login');
    }
  }

}
