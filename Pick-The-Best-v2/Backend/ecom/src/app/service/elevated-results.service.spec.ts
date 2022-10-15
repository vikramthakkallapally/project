import { TestBed } from '@angular/core/testing';

import { ElevatedResultsService } from './elevated-results.service';

describe('ElevatedResultsService', () => {
  let service: ElevatedResultsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ElevatedResultsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
