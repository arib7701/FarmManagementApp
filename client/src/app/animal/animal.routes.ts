import { Routes, RouterModule } from '@angular/router';
import { AnimalComponent } from './animal.component';
import { TypeComponent } from './type/type.component';
import { DetailAnimalComponent } from './detail-animal/detail-animal.component';
import { DetailTypeComponent } from './detail-type/detail-type.component';
import { DetailAnimalEditComponent } from './detail-animal-edit/detail-animal-edit.component';
import { DetailAnimalCreateComponent } from './detail-animal-create/detail-animal-create.component';
import { TypeCreateComponent } from './type-create/type-create.component';
import { TypeEditComponent } from './type-edit/type-edit.component';

export const animalRoutes: Routes = [
  {
    path: '',
    component: AnimalComponent,
    children: [
      {
        path: '',
        component: TypeComponent
      },
      {
        path: 'types/:type',
        component: DetailTypeComponent
      },
      {
        path: 'add',
        component: TypeCreateComponent
      },
      {
        path: 'category/:id',
        component: TypeEditComponent
      },
      {
        path: 'detail/:id',
        component: DetailAnimalComponent
      },
      {
        path: 'edit/:id',
        component: DetailAnimalEditComponent
      },
      {
        path: 'add/:type',
        component: DetailAnimalCreateComponent
      }
    ]
  }
];
