import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenadzerPageComponent } from './menadzer-page.component';

describe('MenadzerPageComponent', () => {
  let component: MenadzerPageComponent;
  let fixture: ComponentFixture<MenadzerPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenadzerPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MenadzerPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
