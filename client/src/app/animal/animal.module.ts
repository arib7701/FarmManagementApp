import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TypeComponent } from './type/type.component';
import { DetailTypeComponent } from './detail-type/detail-type.component';
import { DetailAnimalComponent } from './detail-animal/detail-animal.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [TypeComponent, DetailTypeComponent, DetailAnimalComponent]
})
export class AnimalModule { }
