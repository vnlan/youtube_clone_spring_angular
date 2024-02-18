import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddVideoDetailsComponent } from './add-video-details.component';

describe('AddVideoDetailsComponent', () => {
  let component: AddVideoDetailsComponent;
  let fixture: ComponentFixture<AddVideoDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddVideoDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddVideoDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
