import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredloziViewComponent } from './predlozi-view.component';

describe('PredloziViewComponent', () => {
  let component: PredloziViewComponent;
  let fixture: ComponentFixture<PredloziViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PredloziViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PredloziViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
