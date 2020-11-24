import { Component, OnInit, Renderer2 } from '@angular/core';
import { LoginService } from '../login.service';
import { Router } from '@angular/router';
import { UserService } from '../user.service'
import { User } from '../user.model'
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  opcion: boolean = false;
  registerForm: FormGroup;
  submitted = false;
  mensaje: string;
  constructor(private notifier: NotifierService, private renderer: Renderer2, private loginService: LoginService, private router: Router, private userService: UserService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    if (this.userService.getUsuarioLogged()) {
      this.router.navigateByUrl('/inicio');
    }

    this.registerForm = this.formBuilder.group({
      txtNombres: ['', Validators.required],
      txtApellidos: ['', Validators.required],
      txtTelefono: ['', Validators.required],
      txtDomicilio: ['', Validators.required],
      txtCP: ['', [Validators.required, Validators.min(10000), Validators.max(99999)]],
      txtCorreoLogin: ['', [Validators.required, Validators.email]],
      txtPasswordLogin: ['', [Validators.required, Validators.minLength(6)]],
      txtPasswordConfirm: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  get f() { return this.registerForm.controls; }

  clickInicioSesion(elmInicio: HTMLElement, elmRegistro: HTMLElement) {
    this.renderer.addClass(elmInicio, 'active');
    this.renderer.removeClass(elmRegistro, 'active');
    this.opcion = false;
  }

  clickRegisto(elmInicio: HTMLElement, elmRegistro: HTMLElement) {
    this.renderer.addClass(elmRegistro, 'active');
    this.renderer.removeClass(elmInicio, 'active');
    this.opcion = true;
  }

  login(correo: string, password: string, event: Event) {
    event.preventDefault();

    this.loginService.login(correo, password).subscribe(
      (res: any) => {
        if (res.estatus == 1) {
          let u: User = { usuario: correo, password: password, nombre: res.respuesta.nombre, telefono: res.respuesta.telefono, domicilio: res.respuesta.domicilio, CP: res.respuesta.cp, tipousuario: 1 };
          this.userService.setUserLoggedIn(u);
          this.notifier.notify('success', "Usuario y contraseña correctos.");
          setTimeout(() => {
            this.navigate();
          },
            3000);
          
        } else if (res.estatus == -1) {
          this.notifier.notify('error', res.descripcion);
        }else{
          this.notifier.notify('error', res.descripcion);
        }

      },
      error => {
        console.log(error);
      },
      //() => this.navigate()
    );
  }

  navigate() {
    this.router.navigateByUrl('/');
  }

  crearCuenta(nombre: string, apellidos: string, telefono: string, domicilio: string, CP: string, correo: string, password: string, password2: string) {
    this.submitted = true;
    // stop here if form is invalid
    if (this.registerForm.invalid) {
      this.notifier.notify('error', 'Todos los campos son obligatorios.')
      return;
    }
    if (password != password2) {
      this.notifier.notify('error', 'Las contraseñas no coinciden.')
      return;
    }
    this.loginService.crearCuenta(nombre, apellidos, telefono, domicilio, CP, correo, password).subscribe(
      (res: any) => {
        if (res != null) {
          if (res.estatus == 1) {
            this.notifier.notify('success', 'Registrado correctamente.')
            setTimeout(() => {
              location.reload();
            },
              3000);
            
          } else {
            this.notifier.notify('error', res.descripcion);
          }
        }
      }
    )
  }
}
