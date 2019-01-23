import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { WeightService } from 'src/app/services/weight.service';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Weight } from 'src/app/models/weight';
import { FlashMessagesService } from 'angular2-flash-messages';

@Component({
  selector: 'app-detail-animal-weight-edit',
  templateUrl: './detail-animal-weight-edit.component.html',
  styleUrls: ['./detail-animal-weight-edit.component.css']
})
export class DetailAnimalWeightEditComponent implements OnInit, OnDestroy {
  @Input()
  idAnimal: number;

  editWeightForm: FormGroup;
  subscriptionWeights: Subscription;
  subscriptionWeightDelete: Subscription;
  subscriptionWeightUpdate: Subscription;
  subscriptionWeightNew: Subscription;
  weights: Weight[];
  counterEdit = 0;
  diffs = new Array<string>();
  noWeights = false;
  today = new Date();
  paginationWeight = 1;

  constructor(
    private weightService: WeightService,
    private flashMessagesService: FlashMessagesService) {}

  ngOnInit() {
    this.getWeightsInfo();
    this.createForm();
  }

  createForm() {
    this.editWeightForm = new FormGroup({
      weightItems: new FormArray([])
    });
  }

  getWeightsInfo() {
    this.counterEdit = 0;
    this.subscriptionWeights = this.weightService
      .getAllWeightByAnimalId(this.idAnimal)
      .subscribe(
        weights => {
          this.weights = weights;
          if (this.weights.length > 0) {
            this.noWeights = false;
            for (let i = 0; i < this.weights.length; i++) {
              this.addControls(i);
            }
          } else {
            this.noWeights = true;
          }
        },
        error => {
          console.log('Error getting weights');
        }
      );
  }

  addControls(index: number) {
    const control = <FormArray>this.editWeightForm.controls['weightItems'];
    control.push(
      new FormGroup({
        id: new FormControl({ value: this.weights[index].id, hidden: true }),
        measure: new FormControl(this.weights[index].measure, [
          Validators.required, Validators.min(0), Validators.max(100)
        ]),
        weightDate: new FormControl(this.weights[index].date, [
          Validators.required
        ])
      })
    );
    this.getArrow(index);
  }

  getArrow(index: number) {
    if (index === 0) {
      this.diffs[index] = 'start';
    } else {
      if (this.weights[index].measure > this.weights[index - 1].measure) {
        this.diffs[index] = 'up';
      } else if (
        this.weights[index].measure === this.weights[index - 1].measure
      ) {
        this.diffs[index] = 'equal';
      } else {
        this.diffs[index] = 'down';
      }
    }
  }

  addNewWeightControl() {
    const control = <FormArray>this.editWeightForm.controls['weightItems'];
    control.push(
      new FormGroup({
        id: new FormControl({ value: '', hidden: true }),
        measure: new FormControl('', [Validators.required, Validators.min(0), Validators.max(100)]),
        weightDate: new FormControl('', [Validators.required])
      })
    );
  }

  deleteWeight(index: number) {
    if (confirm('Are you sure to want to delete this weight permanently (this operation cannot be reversed)?')) {
      const control = <FormArray>this.editWeightForm.controls['weightItems'];

      if (this.weights[index] === undefined) {
        control.removeAt(index);
      } else {
        this.subscriptionWeightDelete = this.weightService
          .removeWeightById(this.weights[index].id)
          .subscribe(
            weightDeleted => {
              console.log('Successfully deleted weight');
              this.weights.splice(index, 1);
              control.removeAt(index);
              this.flashMessagesService.show('Weight successfully deleted.',
              { cssClass: 'alert-success', timeout: 5000 });
            },
            error => {
              console.log('Error deleting weight');
              this.flashMessagesService.show('Error deleting the weight.' + error.error,
              { cssClass: 'alert-error', timeout: 5000 });
            }
          );
      }
    }
  }

  get weightItems() {
    return this.editWeightForm.controls['weightItems'] as FormArray;
  }

  submitEditWeight() {
    const formValues = this.editWeightForm.controls.weightItems.value;
    const control = <FormArray>this.editWeightForm.controls['weightItems'];
    const length = formValues.length;

    formValues.forEach((value, index: number) => {
      const id = value.id.value;
      if (id !== '') {
        this.updateWeight(value, length);
      } else {
        this.addNewWeight(value, length);
      }
    });

    for (let i = length - 1; i >= 0; i--) {
      control.removeAt(i);
    }
  }

  updateWeight(weightInfo, length) {
    const weight = new Weight();
    weight.id = weightInfo.id.value;
    weight.measure = weightInfo.measure;
    weight.date = weightInfo.weightDate;
    weight.animalId = this.idAnimal;

    this.subscriptionWeightUpdate = this.weightService
      .updateWeight(weight.id, weight)
      .subscribe(
        weightUpdate => {
          console.log('Successfully updated weight');
          this.counterEdit++;
          this.checkCounter(length);
        },
        error => {
          console.log('Error updating weight');
        }
      );
  }

  addNewWeight(weightInfo, length) {
    const weight = new Weight();
    weight.measure = weightInfo.measure;
    weight.date = weightInfo.weightDate;
    weight.animalId = this.idAnimal;

    this.subscriptionWeightUpdate = this.weightService
      .addWeight(weight)
      .subscribe(
        weightAdded => {
          console.log('Successfully added weight');
          this.counterEdit++;
          this.checkCounter(length);
        },
        error => {
          console.log('Error updating weight');
        }
      );
  }

  checkCounter(length) {
    if (this.counterEdit === length) {
      this.getWeightsInfo();

      this.flashMessagesService.show('Weight Information successfully updated.',
      { cssClass: 'alert-success', timeout: 5000 });
    }
  }

  ngOnDestroy() {
    if (this.subscriptionWeights !== undefined) {
      this.subscriptionWeights.unsubscribe();
    }
    if (this.subscriptionWeightDelete !== undefined) {
      this.subscriptionWeightDelete.unsubscribe();
    }
    if (this.subscriptionWeightUpdate !== undefined) {
      this.subscriptionWeightUpdate.unsubscribe();
    }
    if (this.subscriptionWeightNew !== undefined) {
      this.subscriptionWeightNew.unsubscribe();
    }
  }
}
