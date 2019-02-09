import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailAnimalCreateBatchComponent } from './detail-animal-create-batch.component';

describe('DetailAnimalCreateBatchComponent', () => {
  let component: DetailAnimalCreateBatchComponent;
  let fixture: ComponentFixture<DetailAnimalCreateBatchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailAnimalCreateBatchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailAnimalCreateBatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
