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
  subscriptionAnimalDelete: Subscription;
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
        this.getAllAnimalByType();
      },
      error => {
        console.log('Error getting type');
      }
    );
  }

  getAllAnimalByType() {
    this.subscriptionAnimal = this.animalService
    .getAllAnimalByType(this.idType)
    .subscribe(
      animals => {
        this.animals = animals;
        this.getStatus();
        this.getAge();
        this.getLastWeight();
      },
      error => {
        console.log('Error getting animals by type');
      }
    );
  }

  deleteAnimal(id) {
    this.subscriptionAnimalDelete = this.animalService.removeAnimalById(id).subscribe(result => {
      console.log('Delete Animal successfull, ', result);
      this.getAllAnimalByType();
    }, error => {
      console.log('Error deleting animal');
    });
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

  getLastWeight() {
    this.animals.forEach(animal => {
      animal.lastDateWeight = animal.weights[animal.weights.length - 1].date;
      animal.lastWeight = animal.weights[animal.weights.length - 1].measure;
    });
  }

  ngOnDestroy() {
    if (this.subscriptionAnimal !== undefined) {
      this.subscriptionAnimal.unsubscribe();
    }
    if (this.subscriptionType !== undefined) {
      this.subscriptionType.unsubscribe();
    }
    if (this.subscriptionAnimalDelete !== undefined) {
      this.subscriptionAnimalDelete.unsubscribe();
    }
  }
}
