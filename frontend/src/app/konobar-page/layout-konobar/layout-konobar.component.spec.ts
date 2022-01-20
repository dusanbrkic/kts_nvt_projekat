import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LayoutKonobarComponent } from './layout-konobar.component';

describe('LayoutKonobarComponent', () => {
  let component: LayoutKonobarComponent;
  let fixture: ComponentFixture<LayoutKonobarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LayoutKonobarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LayoutKonobarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
