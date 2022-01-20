import { TestBed } from '@angular/core/testing';

import { ZonaServiceService } from './zona-service.service';

describe('ZonaServiceService', () => {
  let service: ZonaServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ZonaServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
