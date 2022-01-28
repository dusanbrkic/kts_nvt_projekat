import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZaposleniViewComponent } from './zaposleni-view.component';

describe('ZaposleniViewComponent', () => {
  let component: ZaposleniViewComponent;
  let fixture: ComponentFixture<ZaposleniViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZaposleniViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ZaposleniViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
