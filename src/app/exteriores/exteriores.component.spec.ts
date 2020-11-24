import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExterioresComponent } from './exteriores.component';

describe('ExterioresComponent', () => {
  let component: ExterioresComponent;
  let fixture: ComponentFixture<ExterioresComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExterioresComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExterioresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
