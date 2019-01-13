import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { WeightService } from 'src/app/services/weight.service';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Weight } from 'src/app/models/weight';

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

  constructor(private weightService: WeightService) {}

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
    this.subscriptionWeights = this.weightService
      .getAllWeightByAnimalId(this.idAnimal)
      .subscribe(
        weights => {
          this.weights = weights;
          for (let i = 0; i < this.weights.length; i++) {
            this.addControls(i);
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
          Validators.required
        ]),
        weightDate: new FormControl(this.weights[index].date, [
          Validators.required
        ])
      })
    );
  }

  addNewWeightControl() {
    const control = <FormArray>this.editWeightForm.controls['weightItems'];
    control.push(
      new FormGroup({
        id: new FormControl({ value: '', hidden: true }),
        measure: new FormControl('', [Validators.required]),
        weightDate: new FormControl('', [Validators.required])
      })
    );
  }

  deleteWeight(index: number) {
    const control = <FormArray>this.editWeightForm.controls['weightItems'];

    if (this.weights[index] === undefined) {
      control.removeAt(index);
    } else {
      this.subscriptionWeightDelete = this.weightService
        .removeWeightById(this.weights[index].id)
        .subscribe(
          weightDeleted => {
            console.log('Successfully deleted weight');
            control.removeAt(index);
          },
          error => {
            console.log('Error deleting weight');
          }
        );
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
        this.updateWeight(value, index, length);
      } else {
        this.addNewWeight(value, index, length);
      }
    });

    for (let i = length - 1; i >= 0; i--) {
      control.removeAt(i);
    }
  }

  updateWeight(weightInfo, index, length) {
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
          if (index === length - 1) {
            this.getWeightsInfo();
          }
        },
        error => {
          console.log('Error updating weight');
        }
      );
  }

  addNewWeight(weightInfo, index, length) {
    const weight = new Weight();
    weight.measure = weightInfo.measure;
    weight.date = weightInfo.weightDate;
    weight.animalId = this.idAnimal;

    this.subscriptionWeightUpdate = this.weightService
      .addWeight(weight)
      .subscribe(
        weightAdded => {
          console.log('Successfully added weight');

          if (index === length - 1) {
            this.getWeightsInfo();
          }
        },
        error => {
          console.log('Error updating weight');
        }
      );
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
