import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { PiceComponent } from './pice/pice.component';
import { KonobarPageComponent } from './konobar-page/konobar-page.component';
import { SankerPageComponent } from './sanker-page/sanker-page.component';
import { KuvarPageComponent } from './kuvar-page/kuvar-page.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'pice/:id', component: PiceComponent},
  {path: 'konobar',component:KonobarPageComponent},
  {path: 'sanker',component:SankerPageComponent},
  {path: 'kuvar',component:KuvarPageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
