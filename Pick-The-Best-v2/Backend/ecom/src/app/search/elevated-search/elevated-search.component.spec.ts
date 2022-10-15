import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElevatedSearchComponent } from './elevated-search.component';

describe('ElevatedSearchComponent', () => {
  let component: ElevatedSearchComponent;
  let fixture: ComponentFixture<ElevatedSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ElevatedSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ElevatedSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
