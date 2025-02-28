import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleAudienciasComponent } from './detalle-audiencias.component';

describe('DetalleAudienciasComponent', () => {
  let component: DetalleAudienciasComponent;
  let fixture: ComponentFixture<DetalleAudienciasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DetalleAudienciasComponent]
    });
    fixture = TestBed.createComponent(DetalleAudienciasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
