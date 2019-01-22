import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailAnimalDeliveryEditComponent } from './detail-animal-delivery-edit.component';

describe('DetailAnimalDeliveryEditComponent', () => {
  let component: DetailAnimalDeliveryEditComponent;
  let fixture: ComponentFixture<DetailAnimalDeliveryEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailAnimalDeliveryEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailAnimalDeliveryEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
