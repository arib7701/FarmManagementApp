import { Component, OnInit, OnDestroy } from '@angular/core';
import { Animal } from 'src/app/models/animal';
import { Subscription } from 'rxjs';
import { AnimalService } from 'src/app/services/animal.service';
import { TypeService } from 'src/app/services/type.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-detail-animal',
  templateUrl: './detail-animal.component.html',
  styleUrls: ['./detail-animal.component.css']
})
export class DetailAnimalComponent implements OnInit, OnDestroy {
  idAnimal: number;
  sexAnimal: string;
  typeAnimal: string;
  animal: Animal;
  subscriptionAnimal: Subscription;
  subscriptionType: Subscription;
  today = new Date();
  sixMonthAgo = this.today.setMonth(this.today.getMonth() - 6);
  birthDate: Date;

  constructor(
    private animalService: AnimalService,
    private typeService: TypeService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.idAnimal = +this.route.snapshot.paramMap.get('id');

    this.subscriptionAnimal = this.animalService
      .getAnimalById(this.idAnimal)
      .subscribe(
        animal => {
          this.animal = animal;
          this.sexAnimal = this.animal.sex;
          this.birthDate = new Date(this.animal.birth);
          this.getType();
        },
        error => {
          console.log('Error getting all animal by type');
        }
      );
  }

  getType() {
    this.subscriptionType = this.typeService
      .getTypeById(this.animal.type)
      .subscribe(
        type => {
          this.typeAnimal = type.name;
          console.log('Success getting type');
        },
        error => {
          console.log('Error getting type');
        }
      );
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
