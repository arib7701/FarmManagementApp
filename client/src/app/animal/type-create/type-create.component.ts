import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { TypeService } from 'src/app/services/type.service';
import { FlashMessagesService } from 'angular2-flash-messages';
import { Type } from 'src/app/models/type';

@Component({
  selector: 'app-type-create',
  templateUrl: './type-create.component.html',
  styleUrls: ['./type-create.component.css']
})
export class TypeCreateComponent implements OnInit {
  subscriptionAnimalType: Subscription;
  type: Type;
  newAnimalTypeForm: FormGroup;

  constructor(
    private typeService: TypeService,
    private flashMessagesService: FlashMessagesService
  ) {}

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.newAnimalTypeForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      imageUrl: new FormControl('', [Validators.required]),
      weeksGestation: new FormControl(''),
      monthsMaturity: new FormControl(''),
      minimumWeeksBetweenGestation: new FormControl(''),
      minimumWeeksSuckling: new FormControl('')
    });
  }

  submitCreateAnimalType() {
    this.type = new Type();
    this.type.name = this.newAnimalTypeForm.controls['name'].value;
    this.type.imageUrl = this.newAnimalTypeForm.controls['imageUrl'].value;
    this.type.weeksGestation = this.newAnimalTypeForm.controls[
      'weeksGestation'
    ].value;
    this.type.monthsMaturity = this.newAnimalTypeForm.controls[
      'monthsMaturity'
    ].value;
    this.type.minimumWeeksBetweenGestation = this.newAnimalTypeForm.controls[
      'minimumWeeksBetweenGestation'
    ].value;
    this.type.minimumWeeksSuckling = this.newAnimalTypeForm.controls[
      'minimumWeeksSuckling'
    ].value;

    this.subscriptionAnimalType = this.typeService.addType(this.type).subscribe(
      animalTypeSaved => {
        console.log('Saving animal type OK ');
        this.flashMessagesService.show(
          'Animal Type Information successfully created.',
          { cssClass: 'alert-success', timeout: 5000 }
        );
      },
      error => {
        console.log('Error saving new animal type', error);
        this.flashMessagesService.show(
          'Error creating the Animal Information, please try again. ' +
            error.error,
          { cssClass: 'alert-error', timeout: 5000 }
        );
      }
    );
  }

  get name() {
    return this.newAnimalTypeForm.get('name');
  }
  get imageUrl() {
    return this.newAnimalTypeForm.get('imageUrl');
  }
  get weeksGestation() {
    return this.newAnimalTypeForm.get('weeksGestation');
  }
  get monthsMaturity() {
    return this.newAnimalTypeForm.get('monthsMaturity');
  }
  get minimumWeeksBetweenGestation() {
    return this.newAnimalTypeForm.get('minimumWeeksBetweenGestation');
  }
  get minimumWeeksSuckling() {
    return this.newAnimalTypeForm.get('minimumWeeksSuckling');
  }
}
