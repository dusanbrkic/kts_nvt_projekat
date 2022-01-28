import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { KonobarPageComponent } from './pages/konobar-page/konobar-page.component';
import { SankerPageComponent } from './pages/sanker-page/sanker-page.component';
import { KuvarPageComponent } from './pages/kuvar-page/kuvar-page.component';
import { AdminPageComponent } from './pages/admin-page/admin-page.component';
import { MenadzerPageComponent } from './pages/menadzer-page/menadzer-page.component';
import { RoleGuard } from './utils/guards/role.guard';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'konobar', component: KonobarPageComponent },
  { path: 'sanker', component: SankerPageComponent },
  { path: 'kuvar', component: KuvarPageComponent },
  {
    path: 'admin',
    component: AdminPageComponent,
    canActivate: [RoleGuard],
    data: {
      role: 'ROLE_ADMIN',
    },
  },
  {
    path: 'menadzer',
    component: MenadzerPageComponent,
    canActivate: [RoleGuard],
    data: {
      role: 'ROLE_MANAGER',
    },
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
