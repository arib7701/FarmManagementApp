import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Animal } from 'src/app/models/animal';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Type } from 'src/app/models/type';
import { AnimalService } from 'src/app/services/animal.service';
import { TypeService } from 'src/app/services/type.service';
import { ActivatedRoute } from '@angular/router';
import { FlashMessagesService } from 'angular2-flash-messages';
import { controlNameBinding } from '@angular/forms/src/directives/reactive_directives/form_control_name';

@Component({
  selector: 'app-detail-animal-edit',
  templateUrl: './detail-animal-edit.component.html',
  styleUrls: ['./detail-animal-edit.component.css']
})
export class DetailAnimalEditComponent implements OnInit, OnDestroy {
  idAnimal: number;
  typeAnimal: string;
  subscriptionAnimal: Subscription;
  subscriptionAnimalId: Subscription;
  subscriptionType: Subscription;
  type: Type;
  animal: Animal;

  editAnimalForm: FormGroup;
  animalsIdsMale = new Array<number>();
  animalsIdsFemale = new Array<number>();

  constructor(
    private animalService: AnimalService,
    private typeService: TypeService,
    private route: ActivatedRoute,
    private flashMessagesService: FlashMessagesService
  ) {}

  ngOnInit() {
    this.idAnimal = +this.route.snapshot.paramMap.get('id');

    this.subscriptionAnimalId = this.animalService
      .getAnimalById(this.idAnimal)
      .subscribe(
        animal => {
          this.animal = animal;
          this.getType();
          this.loadItems();
        },
        error => {
          console.log('Error getting all animal by type');
        }
      );

    this.createForm();
  }

  createForm() {
    this.editAnimalForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      sex: new FormControl('', [Validators.required]),
      barn: new FormControl(''),
      research: new FormControl(''),
      motherId: new FormControl(''),
      fatherId: new FormControl(''),
      birthDate: new FormControl(''),
      arrivalDate: new FormControl(''),
      deathDate: new FormControl(''),
      departureDate: new FormControl(''),
      deathCause: new FormControl('')
    });
  }

  loadItems() {
    this.editAnimalForm = new FormGroup({
      name: new FormControl(this.animal.name, [Validators.required]),
      sex: new FormControl(this.animal.sex, [Validators.required]),
      barn: new FormControl(this.animal.barn),
      research: new FormControl(this.animal.isResearch),
      motherId: new FormControl(this.animal.motherId),
      fatherId: new FormControl(this.animal.fatherId),
      birthDate: new FormControl(this.animal.birth),
      arrivalDate: new FormControl(this.animal.arrival),
      deathDate: new FormControl(this.animal.death),
      departureDate: new FormControl(this.animal.departure),
      deathCause: new FormControl({value: this.animal.deathCause, disabled: true})
    });
    this.editAnimalForm.setValidators(
      [this.isDateSmallerTo('arrivalDate', 'birthDate'),
      this.isDateSmallerTo('deathDate', 'birthDate'),
      this.isDateSmallerTo('departureDate', 'arrivalDate'),
      this.isFutureDate('birthDate'),
      this.isFutureDate('deathDate')]);


     /* this.editAnimalForm.get('deathDate').valueChanges.subscribe(val => {
        const deathDate = this.editAnimalForm.controls['deathDate'];
        const deathCause = this.editAnimalForm.controls['deathCause'];
        console.log('deathDate is ', deathDate.status);
        if (deathDate.status === 'VALID') {
          deathCause.enable();
        }
      }); */
  }

  isDateSmallerTo(fromDaTeControl, toDateControl) {
    return (group: FormGroup): any => {
      const fromDate = group.controls[fromDaTeControl];
      const toDate = new Date(this.editAnimalForm.controls[toDateControl].value);
      if (fromDate.value !== null && fromDate.value < toDate) {
        fromDate.setErrors({'dateTooSmall': true});
      } else {
        return this.editAnimalForm.valid;
      }
    };
  }

  isFutureDate(date) {
    return (group: FormGroup): any => {
      const formDate = group.controls[date];
      const todayDate = Date.now();
      if (formDate.value !== null && todayDate < formDate.value) {
        formDate.setErrors({'dateInFuture': true});
      } else {
        return this.editAnimalForm.valid;
      }
    };
  }


  getType() {
    this.subscriptionType = this.typeService
      .getTypeById(this.animal.type)
      .subscribe(
        type => {
          this.typeAnimal = type.name;
          console.log('Success getting type');
          this.getAnimalIds();
        },
        error => {
          console.log('Error getting type', error.error);
        }
      );
  }

  getAnimalIds() {
    this.subscriptionAnimalId = this.animalService
      .getAllAnimalByType(this.animal.type)
      .subscribe(
        animals => {
          const allAnimals = animals;
          allAnimals.forEach(animal => {
            if (animal.sex === 'M') {
              this.animalsIdsMale.push(animal.id);
            } else {
              this.animalsIdsFemale.push(animal.id);
            }
          });
        },
        error => {
          console.log('Error getting all animal by type');
        }
      );
  }

  submitEditAnimal() {
    this.animal.name = this.editAnimalForm.controls['name'].value;
    this.animal.sex = this.editAnimalForm.controls['sex'].value;
    this.animal.barn = this.editAnimalForm.controls['barn'].value;
    this.animal.isResearch = this.editAnimalForm.controls['research'].value;
    this.animal.motherId = this.editAnimalForm.controls['motherId'].value;
    this.animal.fatherId = this.editAnimalForm.controls['fatherId'].value;
    this.animal.birth = this.editAnimalForm.controls['birthDate'].value;
    this.animal.death = this.editAnimalForm.controls['deathDate'].value;
    this.animal.arrival = this.editAnimalForm.controls['arrivalDate'].value;
    this.animal.departure = this.editAnimalForm.controls['departureDate'].value;
    this.animal.deathCause = this.editAnimalForm.controls['deathCause'].value;
    this.animal.weights = null;

    console.log('animal ', this.animal);

    this.subscriptionAnimal = this.animalService
      .updateAnimal(this.idAnimal, this.animal)
      .subscribe(
        animalUpdated => {
          console.log('Updating animal OK ');
          this.flashMessagesService.show('Animal Information successfully updated.',
           { cssClass: 'alert-success', timeout: 1000 });
        },
        error => {
          console.log('Error updating new animal', error.error);
          this.flashMessagesService.show('Error updating the Animal Information, please try again.',
           { cssClass: 'alert-error', timeout: 1000 });
        }
      );
  }

  get name() {
    return this.editAnimalForm.get('name');
  }
  get sex() {
    return this.editAnimalForm.get('sex');
  }
  get barn() {
    return this.editAnimalForm.get('barn');
  }
  get research() {
    return this.editAnimalForm.get('research');
  }
  get motherId() {
    return this.editAnimalForm.get('motherId');
  }
  get fatherId() {
    return this.editAnimalForm.get('fatherId');
  }
  get birthDate() {
    return this.editAnimalForm.get('birthDate');
  }
  get arrivalDate() {
    return this.editAnimalForm.get('arrivalDate');
  }
  get deathDate() {
    return this.editAnimalForm.get('deathDate');
  }
  get departureDate() {
    return this.editAnimalForm.get('departureDate');
  }
  get deathCause() {
    return this.editAnimalForm.get('deathCause');
  }

  ngOnDestroy() {
    if (this.subscriptionAnimal !== undefined) {
      this.subscriptionAnimal.unsubscribe();
    }
    if (this.subscriptionAnimalId !== undefined) {
      this.subscriptionAnimalId.unsubscribe();
    }
    if (this.subscriptionType !== undefined) {
      this.subscriptionType.unsubscribe();
    }
  }
}
