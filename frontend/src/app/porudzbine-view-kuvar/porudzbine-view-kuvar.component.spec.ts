import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PorudzbineViewKuvarComponent } from './porudzbine-view-kuvar.component';

describe('PorudzbineViewKuvarComponent', () => {
  let component: PorudzbineViewKuvarComponent;
  let fixture: ComponentFixture<PorudzbineViewKuvarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PorudzbineViewKuvarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PorudzbineViewKuvarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
