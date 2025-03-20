import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginAutoridadComponent } from './login-autoridad.component';

describe('LoginAutoridadComponent', () => {
  let component: LoginAutoridadComponent;
  let fixture: ComponentFixture<LoginAutoridadComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LoginAutoridadComponent]
    });
    fixture = TestBed.createComponent(LoginAutoridadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
