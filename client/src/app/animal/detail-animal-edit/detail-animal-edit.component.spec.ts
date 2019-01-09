import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailAnimalEditComponent } from './detail-animal-edit.component';

describe('DetailAnimalEditComponent', () => {
  let component: DetailAnimalEditComponent;
  let fixture: ComponentFixture<DetailAnimalEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailAnimalEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailAnimalEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
