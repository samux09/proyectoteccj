import { Component, OnInit } from '@angular/core';
import {
  IPayPalConfig,
  ICreateOrderRequest
} from 'ngx-paypal';
import { CarritoService } from '../carrito.service';
import { NotifierService } from 'angular-notifier';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../user.model';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-procesopago',
  templateUrl: './procesopago.component.html',
  styleUrls: ['./procesopago.component.css']
})
export class ProcesopagoComponent implements OnInit {

  public payPalConfig?: IPayPalConfig;
  showSuccess: boolean;
  showCancel: boolean;
  showError: boolean;
  domicilioForm: FormGroup;
  submitted = false;
  etapa : string = "etapa1";
  usuario : User;
  folio : string;


  private readonly notifier: NotifierService;

  ngOnInit(): void {
    if(this.carrito.obtenerCantidadArticulos() < 1){
      this.router.navigateByUrl('/inicio')
    }

    this.initConfig();

    this.domicilioForm= this.formBuilder.group({
      txtNombre: ['', Validators.required],
      txtEstado: ['', Validators.required],
      txtCiudad: ['', Validators.required],
      txtCalle: ['', Validators.required],
      txtCP: ['', Validators.required]
    });
    if(!this.user.getUsuarioLogged()){
      this.router.navigateByUrl('/login');
    }
  }
  constructor(notifierService: NotifierService,public carrito: CarritoService, private formBuilder: FormBuilder, private router: Router, private user : UserService, private http: HttpClient) {
    this.notifier = notifierService;
    this.usuario = user.getUsuarioLogged();
  }



  private initConfig(): void {
    this.payPalConfig = {
      currency: 'MXN',
      clientId: 'AVmlD8cY-qui_QWqrYVhsDTI571zI8BO2LjvWSBHu3eO58476c6k7fjNdGVq2hgYR_gLQSzXUAzJvyqj',
      createOrderOnClient: (data) => <ICreateOrderRequest>{
        intent: 'CAPTURE',
        purchase_units: [{
          amount: {
            currency_code: 'MXN',
            value: this.carrito.obtenerSubTotal().toString(),
            breakdown: {
              item_total: {
                currency_code: 'MXN',
                value: this.carrito.obtenerSubTotal().toString()
              }
            }
          },
          items: [{
            name: 'OfficeConfort',
            quantity: '1',
            category: 'DIGITAL_GOODS',
            unit_amount: {
              currency_code: 'MXN',
              value: this.carrito.obtenerSubTotal().toString(),
            },
          }]
        }]
      },
      advanced: {
        commit: 'true'
      },
      style: {
        label: 'paypal',
        layout: 'vertical'
      },
      onApprove: (data, actions) => {
        console.log('onApprove - transaction was approved, but not authorized', data, actions);
        this.notifier.notify('success', 'El pago fue aprovado.');
        this.generarOrden(this.user.getUsuarioLogged().usuario,this.carrito.obtenerSubTotal());
        actions.order.get().then(details => {
          console.log('onApprove - you can get full order details inside onApprove: ', details);
        });

      },
      onClientAuthorization: (data) => {
        console.log('onClientAuthorization - you should probably inform your server about completed transaction at this point', data);
        this.showSuccess = true;
        this.etapa = 'etapa3';
        
      },
      onCancel: (data, actions) => {
        console.log('OnCancel', data, actions);
        this.notifier.notify('error', 'El pago fue cancelado.');
        this.showCancel = true;

      },
      onError: err => {
        console.log('OnError', err);
        this.notifier.notify('error', 'Ocurrió un error al procesar el pago.');
        this.showError = true;
      },
      onClick: (data, actions) => {
        console.log('onClick', data, actions);
        this.resetStatus();
      },
    };
  }
  resetStatus() {
    this.showSuccess = false;
    this.showCancel = false;
    this.showError = false;
  }
  get f() { return this.domicilioForm.controls; }

  obtenerItemsCarrito() {
    let arrPay: any = [];
    this.carrito.obtenerCarrito().forEach(element => {
      arrPay.push({
        name: element.nombre, quantity: element.cantidad.toString(), category: element.categoria, unit_amount : { currency_code: 'MXN', value: ((element.precio*element.cantidad)*(1-element.descuento)).toString() },
      })
    });
   return arrPay;
  }
  validarDomicilio(){
    this.submitted = true;
    // stop here if form is invalid
    if (this.domicilioForm.invalid) {
      this.notifier.notify('error','Todos los campos son obligatorios.')
      return;
    }
    this.etapa = "etapa2";
  }

  generarOrden(usuario : string , total: number){
      let formData: FormData = new FormData();
      formData.append('opcion', '4'); 
      formData.append('usuario', usuario); 
      formData.append('total', total.toString()); 

     this.http.post('http://localhost/TechCH/api/apiOfficeConfort.php',
     formData
      ).subscribe(
        (res: any) => {
          console.log(res);
          this.folio = res.respuesta;
          this.showSuccess = true;
          this.etapa = 'etapa3';
          this.carrito.limpiarCarrito();
        } 
      )
  }
}
