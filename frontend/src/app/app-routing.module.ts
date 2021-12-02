import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { JeloComponent } from './jelo/jelo.component';
import { LoginComponent } from './login/login.component';
import { PiceComponent } from './pice/pice.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'pice/:id', component: PiceComponent},
  {path: 'jelo/:id', component: JeloComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
