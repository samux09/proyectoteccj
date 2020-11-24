import { Injectable } from '@angular/core';
import { User } from './user.model'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private isLoggedIn: boolean;
  usuarioLogged: User;

  constructor() { 
    this.isLoggedIn = false;
  }

  setUserLoggedIn(user: User){
    this.isLoggedIn = true;
    this.usuarioLogged = user;
    localStorage.setItem('usuarioActual', JSON.stringify(user));
  }

  getUsuarioLogged(){
    return JSON.parse(localStorage.getItem('usuarioActual'));
  }

  cerrarSesion(){
    this.isLoggedIn = false;
    this.usuarioLogged = null;
    localStorage.removeItem('usuarioActual');
  }
}
