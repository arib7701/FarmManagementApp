import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailAnimalCreateComponent } from './detail-animal-create.component';

describe('DetailAnimalCreateComponent', () => {
  let component: DetailAnimalCreateComponent;
  let fixture: ComponentFixture<DetailAnimalCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailAnimalCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailAnimalCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
