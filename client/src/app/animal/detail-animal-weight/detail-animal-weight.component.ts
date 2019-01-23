import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Weight } from 'src/app/models/weight';
import { Chart } from 'chart.js';
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
  lineChart = [];
  noWeights = false;
  paginationWeight = 1;

  constructor(private weightService: WeightService) {}

  ngOnInit() {
    this.subscriptionWeights = this.weightService
      .getAllWeightByAnimalId(this.idAnimal)
      .subscribe(
        weights => {
          this.weights = weights;
          if (this.weights.length > 0) {
            for (let i = 0; i < this.weights.length; i++) {
              this.getArrow(i);
            }
            this.buildChart();
          } else {
            this.noWeights = true;
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

  buildChart() {

    const weightDate: Date[] = new Array<Date>();
    const weightMeasure: number[] = new Array<number>();

    for (const weight of this.weights) {
      weightDate.push(weight.date);
      weightMeasure.push(weight.measure);
    }

    this.lineChart = new Chart('canvasLine', {
      type: 'line',
      data: {
        labels: weightDate,
        datasets: [
          {
            data: weightMeasure,
            borderColor: '#00a65a',
            backgroundColor: 'white',
            pointBackgroundColor: 'black'
          }
        ]
      },
      options: {
        legend: false
      }
    });
  }

  ngOnDestroy() {
    if (this.subscriptionWeights !== undefined) {
      this.subscriptionWeights.unsubscribe();
    }
  }
}
