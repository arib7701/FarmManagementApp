import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Animal } from 'src/app/models/animal';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Type } from 'src/app/models/type';
import { AnimalService } from 'src/app/services/animal.service';
import { WeightService } from 'src/app/services/weight.service';
import { TypeService } from 'src/app/services/type.service';
import { ActivatedRoute } from '@angular/router';
import { getTypeNameForDebugging } from '@angular/common/src/directives/ng_for_of';

@Component({
  selector: 'app-detail-animal-edit',
  templateUrl: './detail-animal-edit.component.html',
  styleUrls: ['./detail-animal-edit.component.css']
})
export class DetailAnimalEditComponent implements OnInit {
  idAnimal: number;
  typeAnimal: string;
  subscriptionAnimal: Subscription;
  subscriptionWeight: Subscription;
  subscriptionAnimalId: Subscription;
  subscriptionType: Subscription;
  type: Type;
  animal: Animal;
  editAnimalForm: FormGroup;
  animalsIdsMale = new Array<number>();
  animalsIdsFemale = new Array<number>();

  constructor( private animalService: AnimalService,
    private weightService: WeightService,
    private typeService: TypeService,
    private route: ActivatedRoute) { }

  ngOnInit() {

    this.idAnimal = +this.route.snapshot.paramMap.get('id');

    this.subscriptionAnimalId = this.animalService.getAnimalById(this.idAnimal).subscribe(animal => {
      this.animal = animal;
      this.getType();
      this.loadItems();
    }, error => {
      console.log('Error getting all animal by type');
    });

    this.createForm();
  }

  createForm () {
    this.editAnimalForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      sex: new FormControl('', [Validators.required]),
      barn: new FormControl(''),
      currentWeight: new FormControl(''),
      research: new FormControl(''),
      motherId: new FormControl(''),
      fatherId: new FormControl(''),
      birthDate: new FormControl(''),
      arrivalDate: new FormControl('')
    });
  }

  loadItems() {
    this.editAnimalForm = new FormGroup({
      name: new FormControl(this.animal.name, [Validators.required]),
      sex: new FormControl(this.animal.sex, [Validators.required]),
      barn: new FormControl(this.animal.barn),
      currentWeight: new FormControl(this.animal.lastWeight),
      research: new FormControl(this.animal.isResearch),
      motherId: new FormControl(this.animal.motherId),
      fatherId: new FormControl(this.animal.fatherId),
      birthDate: new FormControl(this.animal.birth),
      arrivalDate: new FormControl(this.animal.arrival)
    });
  }

  getType() {
    this.subscriptionType = this.typeService.getTypeById(this.animal.type).subscribe( type => {
      this.typeAnimal = type.name;
      console.log('Success getting type');
    }, error => {
      console.log('Error getting type');
    });
  }

  submitEditAnimal () {

    this.animal = new Animal;
    this.animal.name = this.editAnimalForm.controls['name'].value;
    this.animal.sex = this.editAnimalForm.controls['sex'].value;
    this.animal.barn = this.editAnimalForm.controls['barn'].value;
    this.animal.isResearch = this.editAnimalForm.controls['research'].value;
    this.animal.motherId = this.editAnimalForm.controls['motherId'].value;
    this.animal.fatherId = this.editAnimalForm.controls['fatherId'].value;
    this.animal.birth = this.editAnimalForm.controls['birthDate'].value;
    this.animal.death = null;
    this.animal.arrival = this.editAnimalForm.controls['arrivalDate'].value;
    this.animal.departure = null;
    this.animal.weights = null;

   this.subscriptionAnimal = this.animalService.addAnimal(this.animal).subscribe(animalSaved => {
    const animalId = animalSaved.id;
    console.log('Saving animal OK ');
  }, error => {
    console.log('Error saving new animal');
   });
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
  get currentWeight() {
    return this.editAnimalForm.get('currentWeight');
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

}
