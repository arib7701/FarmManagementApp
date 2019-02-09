import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailAnimalWeightBatchComponent } from './detail-animal-weight-batch.component';

describe('DetailAnimalWeightBatchComponent', () => {
  let component: DetailAnimalWeightBatchComponent;
  let fixture: ComponentFixture<DetailAnimalWeightBatchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailAnimalWeightBatchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailAnimalWeightBatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
