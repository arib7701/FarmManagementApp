import { Routes, RouterModule } from '@angular/router';

export const appRoutes: Routes = [
  {
    path: '',
    redirectTo: '/',
    pathMatch: 'full'
  },

  {
    path: 'animal',
    loadChildren: './animal/animal.module#AnimalModule'
  }
];
