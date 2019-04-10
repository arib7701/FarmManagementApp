import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { AnimalService } from 'src/app/services/animal.service';
import { Subscription } from 'rxjs';
import { Animal } from 'src/app/models/animal';
import { ActivatedRoute } from '@angular/router';
import { Type } from 'src/app/models/type';
import { TypeService } from 'src/app/services/type.service';
import { FlashMessagesService } from 'angular2-flash-messages';

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
  pagination = 1;
  columnTemp: string;
  column = 'all';
  columnFilter = 'all';
  choiceFilter = 'all';
  uniqueValues = new Array<string>();
  targetWeight = 6;

  @ViewChild('sexSelect') sexSelect;
  @ViewChild('barnSelect') barnSelect;
  @ViewChild('dateSelect') dateSelect;
  @ViewChild('weightSelect') weightSelect;
  @ViewChild('stateSelect') stateSelect;

  constructor(
    private animalService: AnimalService,
    private typeService: TypeService,
    private route: ActivatedRoute,
    private flashMessagesService: FlashMessagesService
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
        this.getAge();
        this.getLastWeight();
      },
      error => {
        console.log('Error getting animals by type');
      }
    );
  }

  deleteAnimal(id) {
    if (confirm('Are you sure to want to delete this animal permanently (this operation cannot be reversed)?')) {
      this.subscriptionAnimalDelete = this.animalService.removeAnimalById(id).subscribe(result => {
        this.flashMessagesService.show('Animal successfully deleted.',
           { cssClass: 'alert-success', timeout: 5000 });
        this.getAllAnimalByType();
      }, error => {
        console.log('Error deleting animal', error.error);
        this.flashMessagesService.show('Error deleting the animal.' + error.error,
           { cssClass: 'alert-error', timeout: 5000 });
      });
    }
  }

  getAge() {
    this.animals.forEach(animal => {
      if (animal.birth !== null) {
        const todayDate = new Date();
        animal.ageYear = todayDate.getFullYear() - new Date(animal.birth).getFullYear();
        animal.ageMonth = todayDate.getMonth() -  new Date(animal.birth).getMonth();

        if (animal.ageMonth <= 0) {
          animal.ageYear--;
          animal.ageMonth = (12 + animal.ageMonth);
        }

        if (animal.ageMonth === 12) {
          animal.ageYear =  animal.ageYear + 1;
          animal.ageMonth = 0;
        }

        animal.retired = this.shouldRetire(animal);
    }
    });
  }

  shouldRetire(animal: Animal): boolean {

    let retirementAge;

    if (animal.sex === 'F') {
      retirementAge = this.type.retirementYearsFemale;
    } else if (animal.sex === 'M') {
      retirementAge = this.type.retirementYearsMale;
    }

    const age = (animal.ageYear * 10 + animal.ageMonth) / 10;

    return (age > retirementAge);
  }

  getLastWeight() {
    this.animals.forEach(animal => {
      if (animal.weights.length !== 0) {
        animal.lastDateWeight = animal.weights[animal.weights.length - 1].date;
        animal.lastWeight = animal.weights[animal.weights.length - 1].measure;
      }
    });
  }

  filterBy(choice: string) {

    this.columnFilter = this.columnTemp;
    this.choiceFilter = choice;
  }

  getUniqueValuesForColumn(column) {

    this.columnFilter = 'all';
    this.choiceFilter = 'all';
    this.column = 'all';
    this.uniqueValues = new Array<string>();
    this.animals.forEach(animal => {

      let property;

      switch (column) {
        case 'sex':
          this.column = 'sex';
          property = animal.sex;
          break;
        case 'barn':
          this.column = 'barn';
          property = animal.barn;
          break;
        case 'state':
          this.column = 'state';
          property = animal.state;
          break;
        case 'lastDateWeight':
          this.column = 'date';
          property = animal.lastDateWeight;
          break;
        case 'lastWeight':
          this.column = 'weight';
          property = animal.lastWeight;
          break;
      }

      if (!this.uniqueValues.includes(property) && property !== null && property !== undefined) {
        this.uniqueValues.push(property);
      }

      this.columnTemp = column;

    });

    const that = this;
    setTimeout(function() { that.openMatSelect(column); }, 200);
  }

  openMatSelect(column) {
    switch (column) {
      case 'sex':
        this.sexSelect.open();
        break;
      case 'barn':
        this.barnSelect.open();
        break;
      case 'state':
        this.stateSelect.open();
        break;
      case 'lastDateWeight':
        this.dateSelect.open();
        break;
      case 'lastWeight':
        this.weightSelect.open();
        break;
    }
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
