import { TestBed } from '@angular/core/testing';

import { AudienciaExtensionService } from './audiencia-extension.service';

describe('AudienciaExtensionService', () => {
  let service: AudienciaExtensionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AudienciaExtensionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
