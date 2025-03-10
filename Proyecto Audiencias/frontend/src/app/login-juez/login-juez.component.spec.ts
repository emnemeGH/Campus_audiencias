import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginJuezComponent } from './login-juez.component';

describe('LoginJuezComponent', () => {
  let component: LoginJuezComponent;
  let fixture: ComponentFixture<LoginJuezComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LoginJuezComponent]
    });
    fixture = TestBed.createComponent(LoginJuezComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
