import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin.component';
import { adminRoutes } from './admin.routes';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  imports: [CommonModule, SharedModule, RouterModule.forChild(adminRoutes)],
  declarations: [AdminComponent, LoginComponent, HomeComponent]
})
export class AdminModule {}
