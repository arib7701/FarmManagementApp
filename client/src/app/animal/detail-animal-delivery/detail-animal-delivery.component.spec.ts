import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailAnimalDeliveryComponent } from './detail-animal-delivery.component';

describe('DetailAnimalDeliveryComponent', () => {
  let component: DetailAnimalDeliveryComponent;
  let fixture: ComponentFixture<DetailAnimalDeliveryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailAnimalDeliveryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailAnimalDeliveryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
