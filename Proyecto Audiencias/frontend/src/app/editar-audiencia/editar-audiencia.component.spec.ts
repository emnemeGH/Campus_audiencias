import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarAudienciaComponent } from './editar-audiencia.component';

describe('EditarAudienciaComponent', () => {
  let component: EditarAudienciaComponent;
  let fixture: ComponentFixture<EditarAudienciaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditarAudienciaComponent]
    });
    fixture = TestBed.createComponent(EditarAudienciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
