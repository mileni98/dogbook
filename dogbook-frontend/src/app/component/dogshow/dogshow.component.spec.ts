import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DogshowComponent } from './dogshow.component';

describe('DogshowComponent', () => {
  let component: DogshowComponent;
  let fixture: ComponentFixture<DogshowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DogshowComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DogshowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
