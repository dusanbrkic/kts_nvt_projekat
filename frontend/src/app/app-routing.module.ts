import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { KonobarPageComponent } from './konobar-page/konobar-page.component';
import { SankerPageComponent } from './sanker-page/sanker-page.component';
import { KuvarPageComponent } from './kuvar-page/kuvar-page.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { MenadzerPageComponent } from './menadzer-page/menadzer-page.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'konobar',component:KonobarPageComponent},
  {path: 'sanker',component:SankerPageComponent},
  {path: 'kuvar',component:KuvarPageComponent},
  {path: 'admin',component:AdminPageComponent},
  {path: 'menadzer',component:MenadzerPageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
