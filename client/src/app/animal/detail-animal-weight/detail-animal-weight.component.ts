import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Weight } from 'src/app/models/weight';
import { WeightService } from 'src/app/services/weight.service';

@Component({
  selector: 'app-detail-animal-weight',
  templateUrl: './detail-animal-weight.component.html',
  styleUrls: ['./detail-animal-weight.component.css']
})
export class DetailAnimalWeightComponent implements OnInit, OnDestroy {
  @Input()
  idAnimal: number;
  subscriptionWeights: Subscription;
  weights: Weight[];
  diffs = new Array<String>();

  constructor(private weightService: WeightService) {}

  ngOnInit() {
    this.subscriptionWeights = this.weightService
      .getAllWeightByAnimalId(this.idAnimal)
      .subscribe(
        weights => {
          this.weights = weights;
          for (let i = 0; i < this.weights.length; i++) {
            this.getArrow(i);
          }
        },
        error => {
          console.log('Error getting weights');
        }
      );
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

  ngOnDestroy() {
    if (this.subscriptionWeights !== undefined) {
      this.subscriptionWeights.unsubscribe();
    }
  }
}
