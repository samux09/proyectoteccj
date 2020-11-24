import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcesoPagoComponent } from './proceso-pago.component';

describe('ProcesoPagoComponent', () => {
  let component: ProcesoPagoComponent;
  let fixture: ComponentFixture<ProcesoPagoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProcesoPagoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcesoPagoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
