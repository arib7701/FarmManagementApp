import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailAnimalWeightComponent } from './detail-animal-weight.component';

describe('DetailAnimalWeightComponent', () => {
  let component: DetailAnimalWeightComponent;
  let fixture: ComponentFixture<DetailAnimalWeightComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailAnimalWeightComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailAnimalWeightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
