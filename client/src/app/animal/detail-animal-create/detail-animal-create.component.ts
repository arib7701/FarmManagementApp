import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Animal } from 'src/app/models/animal';
import { Type } from 'src/app/models/type';
import { AnimalService } from 'src/app/services/animal.service';
import { TypeService } from 'src/app/services/type.service';
import { ActivatedRoute } from '@angular/router';
import { WeightService } from 'src/app/services/weight.service';
import {
  FormGroup,
  FormControl,
  Validators
} from '@angular/forms';
import { Weight } from 'src/app/models/weight';
import { FlashMessagesService } from 'angular2-flash-messages';
import { State } from '../../models/states';

@Component({
  selector: 'app-detail-animal-create',
  templateUrl: './detail-animal-create.component.html',
  styleUrls: ['./detail-animal-create.component.css']
})
export class DetailAnimalCreateComponent implements OnInit {
  idType: number;
  subscriptionAnimal: Subscription;
  subscriptionWeight: Subscription;
  subscriptionAnimalId: Subscription;
  subscriptionType: Subscription;
  type: Type;
  animal: Animal;
  newAnimalForm: FormGroup;
  animalsIdsMale = new Array<number>();
  animalsIdsFemale = new Array<number>();
  today = new Date();
  sixMonthAgo = new Date();
  statesPossible = new Array<State>();

  constructor(
    private animalService: AnimalService,
    private weightService: WeightService,
    private typeService: TypeService,
    private route: ActivatedRoute,
    private flashMessagesService: FlashMessagesService
  ) {}

  ngOnInit() {
    this.idType = +this.route.snapshot.paramMap.get('type');
    this.sixMonthAgo.setMonth(this.today.getMonth() - 6);

    this.subscriptionType = this.typeService.getTypeById(this.idType).subscribe(
      type => {
        this.type = type;
      },
      error => {
        console.log('Error getting type');
      }
    );

    this.subscriptionAnimalId = this.animalService
      .getAllAnimalAliveByType(this.idType)
      .subscribe(
        animals => {
          const allAnimals = animals;
          allAnimals.forEach(animal => {
            if (animal.sex === 'M' && (animal.state === 'supermale' || animal.state === 'retired')) {
              this.animalsIdsMale.push(animal.id);
            // tslint:disable-next-line:max-line-length
            } else  if (animal.sex === 'F' && (animal.state === 'pregnant' || animal.state === 'retired' || animal.state === 'resting' || animal.state === 'nursing')) {
              this.animalsIdsFemale.push(animal.id);
            }
          });
        },
        error => {
          console.log('Error getting all animal by type');
        }
      );

    this.createForm();
    this.getPossibleStates();
  }

  createForm() {
    this.newAnimalForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      sex: new FormControl('', [Validators.required]),
      barn: new FormControl(''),
      currentWeight: new FormControl('', [Validators.min(0)]),
      research: new FormControl(''),
      motherId: new FormControl(''),
      fatherId: new FormControl(''),
      birthDate: new FormControl(''),
      arrivalDate: new FormControl(''),
      state: new FormControl('', [Validators.required])
    });
    this.newAnimalForm.setValidators([
      this.isDateSmallerTo('arrivalDate', 'birthDate'),
      this.isFutureDate('birthDate'),
      this.checkState('state')
    ]);
  }

  getPossibleStates() {

    for (const state in State) {
      if ( state !== 'dead' && state !== 'sold') {
        this.statesPossible.push(state as State);
      }
    }
  }

  isDateSmallerTo(fromDateControl, toDateControl) {
    return (group: FormGroup): any => {
      const fromDate = group.controls[fromDateControl];
      const toDate = new Date(this.newAnimalForm.controls[toDateControl].value);
      if (fromDate.value !== '' && fromDate.value < toDate) {
        fromDate.setErrors({ dateTooSmall: true });
      } else {
        return this.newAnimalForm.valid;
      }
    };
  }

  isFutureDate(date) {
    return (group: FormGroup): any => {
      const dateControl = group.controls[date];
      const todayDate = Date.now();
      if (dateControl.value !== '' && todayDate < dateControl.value) {
        dateControl.setErrors({ dateInFuture: true });
      } else {
        return this.newAnimalForm.valid;
      }
    };
  }

  checkState(state) {
    return (group: FormGroup): any => {
      const stateForm = group.controls[state];
      const birthDate = new Date( this.newAnimalForm.controls['birthDate'].value );

      if (stateForm.value === 'teen' && birthDate < this.sixMonthAgo) {
        stateForm.setErrors({ stateInvalid: true });
      } else if (stateForm.value === 'pregnant' && birthDate > this.sixMonthAgo) {
        stateForm.setErrors({ stateInvalid: true });
      } else if (stateForm.value === 'nursing' && birthDate > this.sixMonthAgo) {
        stateForm.setErrors({ stateInvalid: true });
      } else if (stateForm.value === 'resting' && birthDate > this.sixMonthAgo) {
        stateForm.setErrors({ stateInvalid: true });
      } else if (stateForm.value === 'fattening' && birthDate > this.sixMonthAgo) {
        stateForm.setErrors({ stateInvalid: true });
      } else {
        return this.newAnimalForm.valid;
      }
    };
  }

  submitCreateAnimal() {
    this.animal = new Animal();
    this.animal.name = this.newAnimalForm.controls['name'].value;
    this.animal.sex = this.newAnimalForm.controls['sex'].value;
    this.animal.type = this.type.id;
    this.animal.barn = this.newAnimalForm.controls['barn'].value;
    this.animal.isResearch = this.newAnimalForm.controls['research'].value;
    this.animal.motherId = this.newAnimalForm.controls['motherId'].value;
    this.animal.fatherId = this.newAnimalForm.controls['fatherId'].value;
    this.animal.birth = this.newAnimalForm.controls['birthDate'].value;
    this.animal.death = null;
    this.animal.deathCause = null;
    this.animal.arrival = this.newAnimalForm.controls['arrivalDate'].value;
    this.animal.departure = null;
    this.animal.weights = null;
    this.animal.state = this.newAnimalForm.controls['state'].value;

    this.subscriptionAnimal = this.animalService
      .addAnimal(this.animal)
      .subscribe(
        animalSaved => {
          const animalId = animalSaved.id;
          this.saveWeight(animalId);
        },
        error => {
          console.log('Error saving new animal', error);
          this.flashMessagesService.show(
            'Error creating the Animal Information, please try again. ' +
              error.error,
            { cssClass: 'alert-error', timeout: 5000 }
          );
        }
      );
  }

  saveWeight(animalId) {
    if (this.newAnimalForm.controls['currentWeight'].value !== null) {
      const currentWeight = new Weight();
      currentWeight.date = new Date();
      currentWeight.measure = this.newAnimalForm.controls[
        'currentWeight'
      ].value;
      currentWeight.animalId = animalId;

      this.subscriptionWeight = this.weightService
        .addWeight(currentWeight)
        .subscribe(
          weightSaved => {
            this.flashMessagesService.show(
              'Animal Information successfully created.',
              { cssClass: 'alert-success', timeout: 5000 }
            );
          },
          error => {
            console.log('Error saving new weight');
            this.flashMessagesService.show(
              'Error creating the Animal Information, please try again.' +
                error.error,
              { cssClass: 'alert-error', timeout: 5000 }
            );
          }
        );
    }
  }

  get name() {
    return this.newAnimalForm.get('name');
  }
  get sex() {
    return this.newAnimalForm.get('sex');
  }
  get barn() {
    return this.newAnimalForm.get('barn');
  }
  get currentWeight() {
    return this.newAnimalForm.get('currentWeight');
  }
  get research() {
    return this.newAnimalForm.get('research');
  }
  get motherId() {
    return this.newAnimalForm.get('motherId');
  }
  get fatherId() {
    return this.newAnimalForm.get('fatherId');
  }
  get birthDate() {
    return this.newAnimalForm.get('birthDate');
  }
  get arrivalDate() {
    return this.newAnimalForm.get('arrivalDate');
  }
  get state() {
    return this.newAnimalForm.get('state');
  }
}
