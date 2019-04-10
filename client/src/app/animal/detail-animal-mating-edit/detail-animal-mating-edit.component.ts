import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { FormGroup, FormArray, FormControl, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Mating } from 'src/app/models/mating';
import { MatingService } from 'src/app/services/mating.service';
import { FlashMessagesService } from 'angular2-flash-messages';

@Component({
  selector: 'app-detail-animal-mating-edit',
  templateUrl: './detail-animal-mating-edit.component.html',
  styleUrls: ['./detail-animal-mating-edit.component.css']
})
export class DetailAnimalMatingEditComponent implements OnInit, OnDestroy {
  @Input()
  idAnimal: number;

  @Input()
  sexAnimal: string;

  @Input()
  disabled: boolean;

  editMatingForm: FormGroup;
  subscriptionMatings: Subscription;
  subscriptionMatingDelete: Subscription;
  subscriptionMatingUpdate: Subscription;
  subscriptionMatingNew: Subscription;
  matings: Mating[];
  counterEdit = 0;
  noMatings = false;
  motherIdControl = false;
  fatherIdControl = false;
  today = new Date();

  constructor(
    private matingService: MatingService,
    private flashMessagesService: FlashMessagesService
  ) {}

  ngOnInit() {
    this.getMatingsInfo();
    this.createForm();
  }

  createForm() {
    this.editMatingForm = new FormGroup({
      matingItems: new FormArray([])
    });
  }

  getMatingsInfo() {
    this.counterEdit = 0;
    this.subscriptionMatings = this.matingService
      .getAllMatingByAnimalId(this.idAnimal)
      .subscribe(
        matings => {
          this.matings = matings;
          if (this.matings.length > 0) {
            if (this.idAnimal === this.matings[0].motherId) {
              this.motherIdControl = true;
              this.getIdBySex('F');
            } else {
              this.fatherIdControl = true;
              this.getIdBySex('M');
            }

            this.noMatings = false;
            for (let i = 0; i < this.matings.length; i++) {
              if (this.disabled) {
                this.disableFields(i);
              } else {
                this.addControls(i);
              }
            }
          } else {
            this.noMatings = true;
            if (this.sexAnimal === 'F') {
              this.motherIdControl = true;
            } else {
              this.fatherIdControl = true;
            }
          }
        },
        error => {
          console.log('Error getting matings');
        }
      );
  }

  getIdBySex(sex: string) {
    // TODO
  }

  addControls(index: number) {
    const control = <FormArray>this.editMatingForm.controls['matingItems'];
    control.push(
      new FormGroup({
        id: new FormControl({ value: this.matings[index].id, hidden: true }),
        matingDate: new FormControl(this.matings[index].date, [
          Validators.required
        ]),
        motherId: new FormControl(
          {
            value: this.matings[index].motherId,
            disabled: this.motherIdControl
          },
          [Validators.required]
        ),
        fatherId: new FormControl(
          {
            value: this.matings[index].fatherId,
            disabled: this.fatherIdControl
          },
          [Validators.required]
        ),
        expectedDate: new FormControl(this.matings[index].expectedDate, [
          Validators.required
        ]),
        successfull: new FormControl(this.matings[index].successfull)
      })
    );
  }

  disableFields(index: number) {
    const control = <FormArray>this.editMatingForm.controls['matingItems'];
    control.push(
      new FormGroup({
        id: new FormControl({ value: this.matings[index].id, hidden: true }),
        matingDate: new FormControl({ value: this.matings[index].date, disabled: true}),
        motherId: new FormControl(
          {
            value: this.matings[index].motherId,
            disabled: true
          }
        ),
        fatherId: new FormControl(
          {
            value: this.matings[index].fatherId,
            disabled: true
          }
        ),
        expectedDate: new FormControl(this.matings[index].expectedDate, [
          Validators.required
        ]),
        successfull: new FormControl(this.matings[index].successfull)
      })
    );
  }


  addNewMatingControl() {
    const control = <FormArray>this.editMatingForm.controls['matingItems'];
    let motherIdControlValue = null;
    let fatherIdControlValue = null;

    if (this.motherIdControl) {
      motherIdControlValue = this.idAnimal;
    } else {
      fatherIdControlValue = this.idAnimal;
    }

    this.matings.unshift(new Mating());

    control.push(
      new FormGroup({
        id: new FormControl({ value: '', hidden: true }),
        matingDate: new FormControl('', [Validators.required]),
        motherId: new FormControl(
          { value: motherIdControlValue, disabled: this.motherIdControl },
          [Validators.required]
        ),
        fatherId: new FormControl(
          { value: fatherIdControlValue, disabled: this.fatherIdControl },
          [Validators.required]
        ),
        expectedDate: new FormControl('', [Validators.required]),
        successfull: new FormControl(true, [Validators.required])
      })
    );
  }

  deleteMating(index: number) {
    if (
      confirm(
        'Are you sure to want to delete this mating permanently (this operation cannot be reversed)?'
      )
    ) {
      const control = <FormArray>(
        this.editMatingForm.controls['matingItems']
      );

      if (this.matings[index] === undefined) {
        control.removeAt(index);
      } else {
        this.subscriptionMatingDelete = this.matingService
          .removeMatingById(this.matings[index].id)
          .subscribe(
            matingDeleted => {
              console.log('Successfully deleted mating');
              this.matings.splice(index, 1);
              control.removeAt(index);
              this.flashMessagesService.show('Mating successfully deleted.', {
                cssClass: 'alert-success',
                timeout: 5000
              });
            },
            error => {
              console.log('Error deleting mating');
              this.flashMessagesService.show(
                'Error deleting the mating.' + error.error,
                { cssClass: 'alert-error', timeout: 5000 }
              );
            }
          );
      }
    }
  }

  get matingItems() {
    return this.editMatingForm.controls['matingItems'] as FormArray;
  }

  submitEditMating() {
    const formValues = this.editMatingForm.controls.matingItems.value;
    const control = <FormArray>this.editMatingForm.controls['matingItems'];
    const length = formValues.length;

    formValues.forEach((value, index: number) => {
      const id = value.id.value;
      if (id !== '') {
        this.updateMating(value, length);
      } else {
        this.addNewMating(value, length);
      }
    });

    for (let i = length - 1; i >= 0; i--) {
      control.removeAt(i);
    }
  }

  updateMating(matingInfo, length) {
    const mating = new Mating();
    mating.id = matingInfo.id.value;
    mating.date = matingInfo.matingDate;
    mating.expectedDate = matingInfo.expectedDate;
    mating.successfull = matingInfo.successfull;

    if (this.motherIdControl) {
      mating.motherId = this.idAnimal;
      mating.fatherId = matingInfo.fatherId;
    } else {
      mating.motherId = matingInfo.motherId;
      mating.fatherId = this.idAnimal;
    }

    this.subscriptionMatingUpdate = this.matingService
      .updateMating(mating.id, mating)
      .subscribe(
        matingUpdate => {
          console.log('Successfully updated mating');
          this.counterEdit++;
          this.checkCounter(length);
        },
        error => {
          console.log('Error updating mating');
        }
      );
  }

  addNewMating(matingInfo, length) {
    const mating = new Mating();
    mating.successfull = matingInfo.successfull;
    mating.expectedDate = matingInfo.expectedDate;
    mating.date = matingInfo.matingDate;

    if (this.motherIdControl) {
      mating.motherId = this.idAnimal;
      mating.fatherId = matingInfo.fatherId;
    } else {
      mating.motherId = matingInfo.motherId;
      mating.fatherId = this.idAnimal;
    }

    this.subscriptionMatingUpdate = this.matingService
      .addMating(mating)
      .subscribe(
        matingAdded => {
          console.log('Successfully added mating');
          this.counterEdit++;
          this.checkCounter(length);
        },
        error => {
          console.log('Error updating mating');
        }
      );
  }

  checkCounter(length) {
    if (this.counterEdit === length) {
      this.getMatingsInfo();

      this.flashMessagesService.show(
        'Mating Information successfully updated.',
        { cssClass: 'alert-success', timeout: 5000 }
      );
    }
  }

  ngOnDestroy() {
    if (this.subscriptionMatings !== undefined) {
      this.subscriptionMatings.unsubscribe();
    }
    if (this.subscriptionMatingDelete !== undefined) {
      this.subscriptionMatingDelete.unsubscribe();
    }
    if (this.subscriptionMatingUpdate !== undefined) {
      this.subscriptionMatingUpdate.unsubscribe();
    }
    if (this.subscriptionMatingNew !== undefined) {
      this.subscriptionMatingNew.unsubscribe();
    }
  }
}
