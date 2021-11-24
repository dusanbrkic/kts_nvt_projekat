import { TestBed } from '@angular/core/testing';

import { PiceService } from './pice.service';

describe('PiceService', () => {
  let service: PiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
