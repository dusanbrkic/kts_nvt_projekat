import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JeloComponent } from './jelo.component';

describe('JeloComponent', () => {
  let component: JeloComponent;
  let fixture: ComponentFixture<JeloComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JeloComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JeloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
