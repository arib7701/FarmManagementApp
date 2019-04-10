import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailAnimalMatingEditComponent } from './detail-animal-mating-edit.component';

describe('DetailAnimalMatingEditComponent', () => {
  let component: DetailAnimalMatingEditComponent;
  let fixture: ComponentFixture<DetailAnimalMatingEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailAnimalMatingEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailAnimalMatingEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
