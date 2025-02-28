import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaAudienciasComponent } from './lista-audiencias.component';

describe('ListaAudienciasComponent', () => {
  let component: ListaAudienciasComponent;
  let fixture: ComponentFixture<ListaAudienciasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListaAudienciasComponent]
    });
    fixture = TestBed.createComponent(ListaAudienciasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
