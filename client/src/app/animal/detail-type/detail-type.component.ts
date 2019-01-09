import { Component, OnInit, OnDestroy } from '@angular/core';
import { AnimalService } from 'src/app/services/animal.service';
import { Subscription } from 'rxjs';
import { Animal } from 'src/app/models/animal';
import { ActivatedRoute } from '@angular/router';
import { Type } from 'src/app/models/type';
import { TypeService } from 'src/app/services/type.service';
import { forEach } from '@angular/router/src/utils/collection';

@Component({
  selector: 'app-detail-type',
  templateUrl: './detail-type.component.html',
  styleUrls: ['./detail-type.component.css']
})
export class DetailTypeComponent implements OnInit, OnDestroy {
  idType: number;
  subscriptionAnimal: Subscription;
  subscriptionType: Subscription;
  type: Type;
  animals: Animal[];

  constructor(
    private animalService: AnimalService,
    private typeService: TypeService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.idType = +this.route.snapshot.paramMap.get('type');

    this.subscriptionType = this.typeService.getTypeById(this.idType).subscribe(
      type => {
        this.type = type;
      },
      error => {
        console.log('Error getting type');
      }
    );

    this.subscriptionAnimal = this.animalService
      .getAllAnimalByType(this.idType)
      .subscribe(
        animals => {
          this.animals = animals;
          this.getStatus();
          this.getAge();
        },
        error => {
          console.log('Error getting animals by type');
        }
      );
  }

  getStatus() {
    this.animals.forEach(animal => {
      if (animal.death !== null) {
        animal.status = 'DEAD';
      } else if (animal.departure !== null) {
        animal.status = 'SOLD';
      } else {
        animal.status = 'ALIVE';
      }
    });
  }

  getAge() {
    this.animals.forEach(animal => {
      if (animal.birth !== null) {
        animal.age =
          new Date().getFullYear() - new Date(animal.birth).getFullYear();
      }
    });
  }

  ngOnDestroy() {
    if (this.subscriptionAnimal !== undefined) {
      this.subscriptionAnimal.unsubscribe();
    }
    if (this.subscriptionType !== undefined) {
      this.subscriptionType.unsubscribe();
    }
  }
}
