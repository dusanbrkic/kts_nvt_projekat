import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PiceComponent } from './pice.component';

describe('PiceComponent', () => {
  let component: PiceComponent;
  let fixture: ComponentFixture<PiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PiceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
