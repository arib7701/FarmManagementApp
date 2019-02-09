import { Component, OnInit, OnDestroy } from '@angular/core';
import { WeightService } from 'src/app/services/weight.service';
import { FlashMessagesService } from 'angular2-flash-messages';
import { FormGroup, FormArray, FormControl, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Weight } from 'src/app/models/weight';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-detail-animal-weight-batch',
  templateUrl: './detail-animal-weight-batch.component.html',
  styleUrls: ['./detail-animal-weight-batch.component.css']
})
export class DetailAnimalWeightBatchComponent implements OnInit, OnDestroy {
  addWeightsBatchForm: FormGroup;
  subscriptionWeights: Subscription;
  weights: Weight[];
  noWeights = false;
  today = new Date();
  counterEdit = 0;
  idType: number;

  constructor(
    private weightService: WeightService,
    private flashMessagesService: FlashMessagesService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.idType =  +this.route.snapshot.paramMap.get('type');

    this.createForm();
  }

  createForm() {
    this.addWeightsBatchForm = new FormGroup({
      weightItems: new FormArray([])
    });
    this.addControls();
  }

  addControls() {
    const control = <FormArray>this.addWeightsBatchForm.controls['weightItems'];
    control.push(
      new FormGroup({
        idAnimal: new FormControl('', Validators.required),
        measure: new FormControl('', [
          Validators.required, Validators.min(0), Validators.max(100)
        ]),
        weightDate: new FormControl(new Date(), [
          Validators.required
        ])
      })
    );
  }

  addNewWeightControl() {
    const control = <FormArray>this.addWeightsBatchForm.controls['weightItems'];
    control.push(
      new FormGroup({
        idAnimal: new FormControl('', Validators.required),
        measure: new FormControl('', [Validators.required, Validators.min(0), Validators.max(100)]),
        weightDate: new FormControl(new Date(), [Validators.required])
      })
    );
  }

  submitEditWeightsBatch() {
    const formValues = this.addWeightsBatchForm.controls.weightItems.value;
    const control = <FormArray>this.addWeightsBatchForm.controls['weightItems'];
    const length = formValues.length;

    formValues.forEach((value, index: number) => {
        this.addNewWeight(value, length);
    });

    for (let i = length - 1; i >= 0; i--) {
      control.removeAt(i);
    }
  }

  addNewWeight(weightInfo, length) {
    const weight = new Weight();
    weight.measure = weightInfo.measure;
    weight.date = weightInfo.weightDate;
    weight.animalId = weightInfo.idAnimal;

    this.subscriptionWeights = this.weightService
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
      this.flashMessagesService.show('Weight Information successfully updated.',
      { cssClass: 'alert-success', timeout: 5000 });
    }
  }

  ngOnDestroy() {
    if (this.subscriptionWeights !== undefined) {
      this.subscriptionWeights.unsubscribe();
    }
  }

}
