import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }
  
  login(user:string, password:string){
    let formData: FormData = new FormData();
    formData.append('opcion', '3'); 
    formData.append('correo', user); 
    formData.append('password', password); 
    
    return this.http.post('http://localhost/TechCH/api/apiOfficeConfort.php',formData);
  }

  crearCuenta(nombre: string, apellidos: string, telefono: string, domicilio: string, CP: string, correo : string, password : string){
    let formData: FormData = new FormData();
    formData.append('opcion', '2'); 
    formData.append('nombre', nombre); 
    formData.append('telefono', telefono); 
    formData.append('domicilio', domicilio); 
    formData.append('CP', CP); 
    formData.append('correo', correo); 
    formData.append('password', password); 

   return this.http.post('http://localhost/TechCH/api/apiOfficeConfort.php',
   formData
    );
  }
    
}

