import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormArray, FormControl, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Animal } from 'src/app/models/animal';
import { AnimalService } from 'src/app/services/animal.service';
import { FlashMessagesService } from 'angular2-flash-messages';
import { ActivatedRoute } from '@angular/router';
import { TypeService } from 'src/app/services/type.service';
import { Type } from 'src/app/models/type';

@Component({
  selector: 'app-detail-animal-create-batch',
  templateUrl: './detail-animal-create-batch.component.html',
  styleUrls: ['./detail-animal-create-batch.component.css']
})
export class DetailAnimalCreateBatchComponent implements OnInit, OnDestroy {
  addAnimalsBatchForm: FormGroup;
  subscriptionAnimals: Subscription;
  subscriptionAnimalId: Subscription;
  subscriptionType: Subscription;
  animals: Animal[];
  counterEdit = 0;
  idType: number;
  type: Type;
  today = new Date();
  animalsIdsMale = new Array<number>();
  animalsIdsFemale = new Array<number>();

  constructor( private animalService: AnimalService,
    private typeService: TypeService,
    private flashMessagesService: FlashMessagesService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.idType =  +this.route.snapshot.paramMap.get('type');

    this.subscriptionType = this.typeService.getTypeById(this.idType).subscribe(type => {
      this.type = type;
    }, error => {
      console.log('Error getting type');
    });

    this.subscriptionAnimalId = this.animalService
      .getAllAnimalAliveByType(this.idType)
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

    this.createForm();
  }

  createForm() {
    this.addAnimalsBatchForm = new FormGroup({
      animalItems: new FormArray([])
    });
    this.addControls();
  }

  addControls() {
    const control = <FormArray>this.addAnimalsBatchForm.controls['animalItems'];
    control.push(
      new FormGroup({
        name: new FormControl('', [Validators.required]),
        sex: new FormControl('F', [Validators.required]),
        motherId: new FormControl(''),
        fatherId: new FormControl(''),
        birthDate: new FormControl(new Date()),
        arrivalDate: new FormControl(new Date()),
      })
    );
  }

  addNewAnimalControl() {
    const control = <FormArray>this.addAnimalsBatchForm.controls['animalItems'];
    control.push(
      new FormGroup({
        name: new FormControl('', [Validators.required]),
        sex: new FormControl('F', [Validators.required]),
        motherId: new FormControl(''),
        fatherId: new FormControl(''),
        birthDate: new FormControl(new Date()),
        arrivalDate: new FormControl(new Date()),
      })
    );
  }

  submitEditAnimalsBatch() {
    const formValues = this.addAnimalsBatchForm.controls.animalItems.value;
    const control = <FormArray>this.addAnimalsBatchForm.controls['animalItems'];
    const length = formValues.length;

    formValues.forEach((value, index: number) => {
        this.addNewAnimal(value, length);
    });

    for (let i = length - 1; i >= 0; i--) {
      control.removeAt(i);
    }
  }

  addNewAnimal(animalInfo, length) {
    const animal = new Animal();
    animal.name = animalInfo.name;
    animal.type = this.idType;
    animal.sex = animalInfo.sex;
    animal.motherId = animalInfo.motherId;
    animal.fatherId = animalInfo.fatherId;
    animal.birth = animalInfo.birthDate;
    animal.arrival = animalInfo.arrivalDate;
    animal.barn = null;
    animal.isResearch = false;
    animal.death = null;
    animal.deathCause = null;
    animal.departure = null;
    animal.weights = null;

    this.subscriptionAnimals = this.animalService
      .addAnimal(animal)
      .subscribe(
        animalAdded => {
          console.log('Successfully added animal');
          this.counterEdit++;
          this.checkCounter(length);
        },
        error => {
          console.log('Error updating animal', error);
        }
      );
  }

  checkCounter(length) {
    if (this.counterEdit === length) {
      this.flashMessagesService.show('Animal Information successfully updated.',
      { cssClass: 'alert-success', timeout: 5000 });
    }
  }

  ngOnDestroy() {
    if (this.subscriptionAnimals !== undefined) {
      this.subscriptionAnimals.unsubscribe();
    }
    if (this.subscriptionAnimalId !== undefined) {
      this.subscriptionAnimalId.unsubscribe();
    }
    if (this.subscriptionType !== undefined) {
      this.subscriptionType.unsubscribe();
    }
  }

}
