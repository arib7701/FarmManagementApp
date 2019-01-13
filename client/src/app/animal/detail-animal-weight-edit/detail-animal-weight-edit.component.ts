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
  weights: Weight[];

  constructor(private weightService: WeightService) {}

  ngOnInit() {
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
    this.createForm();
  }

  createForm() {
    this.editWeightForm = new FormGroup({
      weightItems: new FormArray([])
    });
  }

  addControls(index) {
    const control = <FormArray>this.editWeightForm.controls['weightItems'];
    control.push(
      new FormGroup({
        measure: new FormControl(this.weights[index].measure, [
          Validators.required
        ]),
        weightDate: new FormControl(this.weights[index].date, [
          Validators.required
        ])
      })
    );
  }

  addNewWeight() {
    const control = <FormArray>this.editWeightForm.controls['weightItems'];
    control.push(
      new FormGroup({
        measure: new FormControl('', [Validators.required]),
        weightDate: new FormControl('', [Validators.required])
      })
    );
  }

  deleteWeight(weightId) {}

  get weightItems() {
    return this.editWeightForm.controls['weightItems'] as FormArray;
  }

  ngOnDestroy() {
    if (this.subscriptionWeights !== undefined) {
      this.subscriptionWeights.unsubscribe();
    }
  }
}
