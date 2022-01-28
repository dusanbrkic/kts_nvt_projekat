import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SankerPageComponent } from './sanker-page.component';

describe('SankerPageComponent', () => {
  let component: SankerPageComponent;
  let fixture: ComponentFixture<SankerPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SankerPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SankerPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
