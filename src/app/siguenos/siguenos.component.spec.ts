import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SiguenosComponent } from './siguenos.component';

describe('SiguenosComponent', () => {
  let component: SiguenosComponent;
  let fixture: ComponentFixture<SiguenosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SiguenosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SiguenosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
