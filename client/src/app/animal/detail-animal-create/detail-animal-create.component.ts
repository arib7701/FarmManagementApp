import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Animal } from 'src/app/models/animal';
import { Type } from 'src/app/models/type';
import { AnimalService } from 'src/app/services/animal.service';
import { TypeService } from 'src/app/services/type.service';
import { ActivatedRoute } from '@angular/router';
import { WeightService } from 'src/app/services/weight.service';
import { FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { Weight } from 'src/app/models/weight';
import { FlashMessagesService } from 'angular2-flash-messages';

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

  constructor(
    private animalService: AnimalService,
    private weightService: WeightService,
    private typeService: TypeService,
    private route: ActivatedRoute,
    private flashMessagesService: FlashMessagesService) { }

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

    this.subscriptionAnimalId = this.animalService.getAllAnimalByType(this.idType).subscribe(animals => {
      const allAnimals = animals;
      allAnimals.forEach(animal => {
        if (animal.sex === 'M') {
          this.animalsIdsMale.push(animal.id);
        } else {
          this.animalsIdsFemale.push(animal.id);
        }
      });
    }, error => {
      console.log('Error getting all animal by type');
    });

    this.createForm();
  }

  createForm () {
    this.newAnimalForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      sex: new FormControl('', [Validators.required]),
      barn: new FormControl(''),
      currentWeight: new FormControl('', [Validators.min(0)]),
      research: new FormControl(''),
      motherId: new FormControl(''),
      fatherId: new FormControl(''),
      birthDate: new FormControl(''),
      arrivalDate: new FormControl('')
    });
    this.newAnimalForm.setValidators([this.isDateSmallerTo('arrivalDate', 'birthDate'), this.isFutureDate('birthDate')]);
  }

  isDateSmallerTo(fromDateControl, toDateControl) {
    return (group: FormGroup): any => {
      const fromDate = group.controls[fromDateControl];
      const toDate = new Date(this.newAnimalForm.controls[toDateControl].value);
      if (fromDate.value < toDate) {
        fromDate.setErrors({'dateTooSmall': true});
      }
    };
  }

  isFutureDate(date) {
    return (group: FormGroup): any => {
      const birthDate = group.controls[date];
      const todayDate = Date.now();
      if (todayDate < birthDate.value) {
        birthDate.setErrors({'dateInFuture': true});
      }
    };
  }

  submitCreateAnimal () {

    this.animal = new Animal;
    this.animal.name = this.newAnimalForm.controls['name'].value;
    this.animal.sex = this.newAnimalForm.controls['sex'].value;
    this.animal.type = this.type.id;
    this.animal.barn = this.newAnimalForm.controls['barn'].value;
    this.animal.isResearch = this.newAnimalForm.controls['research'].value;
    this.animal.motherId = this.newAnimalForm.controls['motherId'].value;
    this.animal.fatherId = this.newAnimalForm.controls['fatherId'].value;
    this.animal.birth = this.newAnimalForm.controls['birthDate'].value;
    this.animal.death = null;
    this.animal.arrival = this.newAnimalForm.controls['arrivalDate'].value;
    this.animal.departure = null;
    this.animal.weights = null;

   this.subscriptionAnimal = this.animalService.addAnimal(this.animal).subscribe(animalSaved => {
    const animalId = animalSaved.id;
    console.log('Saving animal OK ');
    this.saveWeight(animalId);
   }, error => {
    console.log('Error saving new animal');
   });
  }

  saveWeight(animalId) {

     if (this.newAnimalForm.controls['currentWeight'].value !== null) {
      const currentWeight = new Weight;
      currentWeight.date = new Date();
      currentWeight.measure = this.newAnimalForm.controls['currentWeight'].value;
      currentWeight.animalId = animalId;

      this.subscriptionWeight = this.weightService.addWeight(currentWeight).subscribe(weightSaved => {
        console.log('Saving weight OK');
        this.flashMessagesService.show('Animal Information successfully created.',
           { cssClass: 'alert-success', timeout: 1000 });
       }, error => {
        console.log('Error saving new weight');
        this.flashMessagesService.show('Error creating the Animal Information, please try again.',
           { cssClass: 'alert-error', timeout: 1000 });
       });
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

}
