import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlmComponent } from './alm.component';

describe('AlmComponent', () => {
  let component: AlmComponent;
  let fixture: ComponentFixture<AlmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
