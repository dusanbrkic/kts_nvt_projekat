import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PorudzbineViewSankerComponent } from './porudzbine-view-sanker.component';

describe('PorudzbineViewSankerComponent', () => {
  let component: PorudzbineViewSankerComponent;
  let fixture: ComponentFixture<PorudzbineViewSankerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PorudzbineViewSankerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PorudzbineViewSankerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
