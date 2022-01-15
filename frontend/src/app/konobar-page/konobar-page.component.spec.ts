import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KonobarPageComponent } from './konobar-page.component';

describe('KonobarPageComponent', () => {
  let component: KonobarPageComponent;
  let fixture: ComponentFixture<KonobarPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KonobarPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KonobarPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
