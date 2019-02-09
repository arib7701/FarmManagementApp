import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { TypeService } from 'src/app/services/type.service';
import { FlashMessagesService } from 'angular2-flash-messages';
import { Type } from 'src/app/models/type';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-type-edit',
  templateUrl: './type-edit.component.html',
  styleUrls: ['./type-edit.component.css']
})
export class TypeEditComponent implements OnInit, OnDestroy {
  subscriptionAnimalType: Subscription;
  subscriptionAnimalTypeEdit: Subscription;
  type: Type;
  editAnimalTypeForm: FormGroup;
  idType: number;

  constructor(private typeService: TypeService,
    private flashMessagesService: FlashMessagesService,
    private route: ActivatedRoute) { }

  ngOnInit() {

    this.idType = +this.route.snapshot.paramMap.get('id');

    this.subscriptionAnimalType = this.typeService.getTypeById(this.idType).subscribe(type => {
      this.type = type;
      this.fillForm();
    }, error => {
      console.log('Error getting type');
      }
    );

     this.createForm();
  }

  createForm() {
    this.editAnimalTypeForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      imageUrl: new FormControl('', [Validators.required]),
      weeksGestation: new FormControl(''),
      monthsMaturity: new FormControl(''),
      minimumWeeksBetweenGestation: new FormControl(''),
      minimumWeeksSuckling: new FormControl('')
    });
  }

  fillForm() {
    this.editAnimalTypeForm = new FormGroup({
      name: new FormControl(this.type.name, [Validators.required]),
      imageUrl: new FormControl(this.type.imageUrl, [Validators.required]),
      weeksGestation: new FormControl(this.type.weeksGestation),
      monthsMaturity: new FormControl(this.type.monthsMaturity),
      minimumWeeksBetweenGestation: new FormControl(this.type.minimumWeeksBetweenGestation),
      minimumWeeksSuckling: new FormControl(this.type.minimumWeeksSuckling)
    });
  }

  submitEditAnimalType() {
    this.type = new Type();
    this.type.id = this.idType;
    this.type.name = this.editAnimalTypeForm.controls['name'].value;
    this.type.imageUrl = this.editAnimalTypeForm.controls['imageUrl'].value;
    this.type.weeksGestation = this.editAnimalTypeForm.controls[
      'weeksGestation'
    ].value;
    this.type.monthsMaturity = this.editAnimalTypeForm.controls[
      'monthsMaturity'
    ].value;
    this.type.minimumWeeksBetweenGestation = this.editAnimalTypeForm.controls[
      'minimumWeeksBetweenGestation'
    ].value;
    this.type.minimumWeeksSuckling = this.editAnimalTypeForm.controls[
      'minimumWeeksSuckling'
    ].value;

    this.subscriptionAnimalTypeEdit = this.typeService.updateType(this.idType, this.type).subscribe(
      animalTypeSaved => {
        console.log('Editing animal type OK ');
        this.flashMessagesService.show(
          'Animal Type Information successfully edited.',
          { cssClass: 'alert-success', timeout: 5000 }
        );
      },
      error => {
        console.log('Error editing the animal type info', error);
        this.flashMessagesService.show(
          'Error editing the Animal Information, please try again. ' +
            error.error,
          { cssClass: 'alert-error', timeout: 5000 }
        );
      }
    );
  }

  get name() {
    return this.editAnimalTypeForm.get('name');
  }
  get imageUrl() {
    return this.editAnimalTypeForm.get('imageUrl');
  }
  get weeksGestation() {
    return this.editAnimalTypeForm.get('weeksGestation');
  }
  get monthsMaturity() {
    return this.editAnimalTypeForm.get('monthsMaturity');
  }
  get minimumWeeksBetweenGestation() {
    return this.editAnimalTypeForm.get('minimumWeeksBetweenGestation');
  }
  get minimumWeeksSuckling() {
    return this.editAnimalTypeForm.get('minimumWeeksSuckling');
  }

  ngOnDestroy() {
    if (this.subscriptionAnimalType !== undefined) {
      this.subscriptionAnimalType.unsubscribe();
    }
    if (this.subscriptionAnimalTypeEdit !== undefined) {
      this.subscriptionAnimalTypeEdit.unsubscribe();
    }
  }
}
