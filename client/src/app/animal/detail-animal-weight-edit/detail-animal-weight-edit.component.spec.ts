import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailAnimalWeightEditComponent } from './detail-animal-weight-edit.component';

describe('DetailAnimalWeightEditComponent', () => {
  let component: DetailAnimalWeightEditComponent;
  let fixture: ComponentFixture<DetailAnimalWeightEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailAnimalWeightEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailAnimalWeightEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
