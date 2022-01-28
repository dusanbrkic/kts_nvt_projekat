import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KuvarPageComponent } from './kuvar-page.component';

describe('KuvarPageComponent', () => {
  let component: KuvarPageComponent;
  let fixture: ComponentFixture<KuvarPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KuvarPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KuvarPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
