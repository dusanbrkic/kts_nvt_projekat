import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuJelaComponent } from './menu-jela.component';

describe('MenuJelaComponent', () => {
  let component: MenuJelaComponent;
  let fixture: ComponentFixture<MenuJelaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuJelaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuJelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
