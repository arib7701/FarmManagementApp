import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { DeliveryService } from 'src/app/services/delivery.service';
import { FormGroup, FormArray, FormControl, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Delivery } from 'src/app/models/delivery';
import { FlashMessagesService } from 'angular2-flash-messages';

@Component({
  selector: 'app-detail-animal-delivery-edit',
  templateUrl: './detail-animal-delivery-edit.component.html',
  styleUrls: ['./detail-animal-delivery-edit.component.css']
})
export class DetailAnimalDeliveryEditComponent implements OnInit, OnDestroy {
  @Input()
  idAnimal: number;

  editDeliveryForm: FormGroup;
  subscriptionDeliveries: Subscription;
  subscriptionDeliveryDelete: Subscription;
  subscriptionDeliveryUpdate: Subscription;
  subscriptionDeliveryNew: Subscription;
  deliveries: Delivery[];
  counterEdit = 0;
  noDeliveries = false;
  motherIdControl = false;
  fatherIdControl = false;
  today = new Date();

  constructor( private deliveryService: DeliveryService,
    private flashMessagesService: FlashMessagesService) { }

  ngOnInit() {
    this.getDeliveriesInfo();
    this.createForm();
  }

  createForm() {
    this.editDeliveryForm = new FormGroup({
      deliveryItems: new FormArray([])
    });
  }

  getDeliveriesInfo() {
    this.counterEdit = 0;
    this.subscriptionDeliveries = this.deliveryService
      .getAllDeliveryByAnimalId(this.idAnimal)
      .subscribe(
        deliveries => {
          this.deliveries = deliveries;
          if (this.deliveries.length > 0) {

            if (this.idAnimal === this.deliveries[0].motherId) {
              this.motherIdControl = true;
              this.getIdBySex('M');
            } else {
              this.fatherIdControl = true;
              this.getIdBySex('F');
            }

            this.noDeliveries = false;
            for (let i = 0; i < this.deliveries.length; i++) {
              this.addControls(i);
            }
          } else {
            this.noDeliveries = true;
          }
        },
        error => {
          console.log('Error getting deliveries');
        }
      );
  }

  getIdBySex(sex: string) {
    // TO DO
  }

  addControls(index: number) {
    const control = <FormArray>this.editDeliveryForm.controls['deliveryItems'];
    control.push(
      new FormGroup({
        id: new FormControl({ value: this.deliveries[index].id, hidden: true }),
        number: new FormControl(this.deliveries[index].number, [
          Validators.required, Validators.min(0), Validators.max(20)
        ]),
        deliveryDate: new FormControl(this.deliveries[index].date, [
          Validators.required
        ]),
        motherId: new FormControl({value: this.deliveries[index].motherId, disabled: this.motherIdControl}, [
          Validators.required
        ]),
        fatherId: new FormControl({value: this.deliveries[index].fatherId, disabled: this.fatherIdControl}, [
          Validators.required
        ])
      })
    );
  }

  addNewDeliveryControl() {
    const control = <FormArray>this.editDeliveryForm.controls['deliveryItems'];
    let motherIdControlValue = null;
    let fatherIdControlValue = null;

    if (this.motherIdControl) {
      motherIdControlValue = this.idAnimal;
    } else {
      fatherIdControlValue = this.idAnimal;
    }

    this.deliveries.unshift(new Delivery());

    control.push(
      new FormGroup({
        id: new FormControl({ value: '', hidden: true }),
        number: new FormControl('', [Validators.required, Validators.min(0), Validators.max(20)]),
        deliveryDate: new FormControl('', [Validators.required]),
        motherId: new FormControl({value: motherIdControlValue, disabled: this.motherIdControl}, [Validators.required]),
        fatherId: new FormControl({value: fatherIdControlValue, disabled: this.fatherIdControl}, [Validators.required])
      })
    );
  }

  deleteDelivery(index: number) {
    if (confirm('Are you sure to want to delete this delivery permanently (this operation cannot be reversed)?')) {
      const control = <FormArray>this.editDeliveryForm.controls['deliveryItems'];

      if (this.deliveries[index] === undefined) {
        control.removeAt(index);
      } else {
        this.subscriptionDeliveryDelete = this.deliveryService
          .removeDeliveryById(this.deliveries[index].id)
          .subscribe(
            deliveryDeleted => {
              console.log('Successfully deleted delivery');
              this.deliveries.splice(index, 1);
              control.removeAt(index);
              this.flashMessagesService.show('Delivery successfully deleted.',
              { cssClass: 'alert-success', timeout: 5000 });
            },
            error => {
              console.log('Error deleting delivery');
              this.flashMessagesService.show('Error deleting the delivery.' + error.error,
              { cssClass: 'alert-error', timeout: 5000 });
            }
          );
      }
    }
  }

  get deliveryItems() {
    return this.editDeliveryForm.controls['deliveryItems'] as FormArray;
  }

  submitEditDelivery() {
    const formValues = this.editDeliveryForm.controls.deliveryItems.value;
    const control = <FormArray>this.editDeliveryForm.controls['deliveryItems'];
    const length = formValues.length;

    formValues.forEach((value, index: number) => {
      const id = value.id.value;
      if (id !== '') {
        this.updateDelivery(value, length);
      } else {
        this.addNewDelivery(value, length);
      }
    });

    for (let i = length - 1; i >= 0; i--) {
      control.removeAt(i);
    }
  }

  updateDelivery(deliveryInfo, length) {
    const delivery = new Delivery();
    delivery.id = deliveryInfo.id.value;
    delivery.number = deliveryInfo.number;
    delivery.date = deliveryInfo.deliveryDate;

    if (this.motherIdControl) {
      delivery.motherId = this.idAnimal;
      delivery.fatherId = deliveryInfo.fatherId;
    } else {
      delivery.motherId = deliveryInfo.motherId;
      delivery.fatherId = this.idAnimal;
    }

    this.subscriptionDeliveryUpdate = this.deliveryService
      .updateDelivery(delivery.id, delivery)
      .subscribe(
        deliveryUpdate => {
          console.log('Successfully updated delivery');
          this.counterEdit++;
          this.checkCounter(length);
        },
        error => {
          console.log('Error updating delivery');
        }
      );
  }

  addNewDelivery(deliveryInfo, length) {
    const delivery = new Delivery();
    delivery.number = deliveryInfo.number;
    delivery.date = deliveryInfo.deliveryDate;

    if (this.motherIdControl) {
      delivery.motherId = this.idAnimal;
      delivery.fatherId = deliveryInfo.fatherId;
    } else {
      delivery.motherId = deliveryInfo.motherId;
      delivery.fatherId = this.idAnimal;
    }

    console.log(delivery);

    this.subscriptionDeliveryUpdate = this.deliveryService
      .addDelivery(delivery)
      .subscribe(
        deliveryAdded => {
          console.log('Successfully added delivery');
          this.counterEdit++;
          this.checkCounter(length);
        },
        error => {
          console.log('Error updating delivery');
        }
      );
  }

  checkCounter(length) {
    if (this.counterEdit === length) {
      this.getDeliveriesInfo();

      this.flashMessagesService.show('Delivery Information successfully updated.',
      { cssClass: 'alert-success', timeout: 5000 });
    }
  }

  ngOnDestroy() {
    if (this.subscriptionDeliveries !== undefined) {
      this.subscriptionDeliveries.unsubscribe();
    }
    if (this.subscriptionDeliveryDelete !== undefined) {
      this.subscriptionDeliveryDelete.unsubscribe();
    }
    if (this.subscriptionDeliveryUpdate !== undefined) {
      this.subscriptionDeliveryUpdate.unsubscribe();
    }
    if (this.subscriptionDeliveryNew !== undefined) {
      this.subscriptionDeliveryNew.unsubscribe();
    }
  }

}
