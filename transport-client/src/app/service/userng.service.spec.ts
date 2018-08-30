import { TestBed, inject } from '@angular/core/testing';

import { UserngService } from './userng.service';

describe('UserngService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserngService]
    });
  });

  it('should be created', inject([UserngService], (service: UserngService) => {
    expect(service).toBeTruthy();
  }));
});
