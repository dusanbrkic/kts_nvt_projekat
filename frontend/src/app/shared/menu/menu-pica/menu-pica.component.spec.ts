import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuPicaComponent } from './menu-pica.component';

describe('MenuPicaComponent', () => {
  let component: MenuPicaComponent;
  let fixture: ComponentFixture<MenuPicaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuPicaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuPicaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
