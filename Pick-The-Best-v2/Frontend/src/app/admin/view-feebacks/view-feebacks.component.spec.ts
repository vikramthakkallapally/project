import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewFeebacksComponent } from './view-feebacks.component';

describe('ViewFeebacksComponent', () => {
  let component: ViewFeebacksComponent;
  let fixture: ComponentFixture<ViewFeebacksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewFeebacksComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewFeebacksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
