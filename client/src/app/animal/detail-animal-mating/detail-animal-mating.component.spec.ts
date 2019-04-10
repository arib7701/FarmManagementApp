import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailAnimalMatingComponent } from './detail-animal-mating.component';

describe('DetailAnimalMatingComponent', () => {
  let component: DetailAnimalMatingComponent;
  let fixture: ComponentFixture<DetailAnimalMatingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailAnimalMatingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailAnimalMatingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
