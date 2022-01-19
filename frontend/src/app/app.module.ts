import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { PiceComponent } from './pice/pice.component';
import { JeloComponent } from './jelo/jelo.component';
import { HttpClientModule } from '@angular/common/http';
import { KonobarPageComponent } from './konobar-page/konobar-page.component';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AutoCompleteModule } from "primeng/autocomplete";
import { CalendarModule } from "primeng/calendar";
import { ChipsModule } from "primeng/chips";
import { DropdownModule } from "primeng/dropdown";
import { InputMaskModule } from "primeng/inputmask";
import { InputNumberModule } from "primeng/inputnumber";
import { CascadeSelectModule } from "primeng/cascadeselect";
import { MultiSelectModule } from "primeng/multiselect";
import { InputTextareaModule } from "primeng/inputtextarea";
import { InputTextModule } from "primeng/inputtext";
import { FormsModule } from "@angular/forms";
import {PasswordModule} from 'primeng/password';
import { DividerModule } from "primeng/divider";
import { MenuComponent } from './menu/menu.component';
import {DataViewModule} from 'primeng/dataview';
import {TableModule} from 'primeng/table';
import {TabViewModule} from 'primeng/tabview';
import {DragDropModule} from 'primeng/dragdrop';
import {TabMenuModule} from 'primeng/tabmenu';
import { NavbarComponent } from './navbar/navbar.component';
import { SankerPageComponent } from './sanker-page/sanker-page.component';
import {TooltipModule} from 'primeng/tooltip';
import { PorudzbineViewSankerComponent } from './porudzbine-view-sanker/porudzbine-view-sanker.component';
import {AccordionModule} from 'primeng/accordion';
import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import { KuvarPageComponent } from './kuvar-page/kuvar-page.component';
import {InplaceModule} from 'primeng/inplace';
import { PorudzbineViewKuvarComponent } from './porudzbine-view-kuvar/porudzbine-view-kuvar.component';
import {PickListModule} from 'primeng/picklist';
import {PanelModule} from 'primeng/panel';
import { LayoutAdminComponent } from './admin-page/layout-admin/layout-admin.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { DragDropModule as DragDropCDKModule} from '@angular/cdk/drag-drop';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import { ZaposleniViewComponent } from './zaposleni-view/zaposleni-view.component';
import {ContextMenuModule} from 'primeng/contextmenu';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    HeaderComponent,
    PiceComponent,
    JeloComponent,
    KonobarPageComponent,
    MenuComponent,
    NavbarComponent,
    SankerPageComponent,
    PorudzbineViewSankerComponent,
    KuvarPageComponent,
    PorudzbineViewKuvarComponent,
    LayoutAdminComponent,
    AdminPageComponent,
    ZaposleniViewComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    DialogModule,
    ButtonModule,
    AutoCompleteModule,
    FormsModule,
    HttpClientModule,
    CalendarModule,
    ChipsModule,
    DropdownModule,
    InputMaskModule,
    InputNumberModule,
    CascadeSelectModule,
    MultiSelectModule,
    InputTextareaModule,
    InputTextModule,
    PasswordModule,
    DividerModule,
    DataViewModule,
    TableModule,
    TabViewModule,
    DragDropModule,
    TabMenuModule,
    TooltipModule,
    AccordionModule,
    ToastModule,
    ToolbarModule,
    InplaceModule,
    PickListModule,
    PanelModule,
    DragDropCDKModule,
    MessageModule,
    MessagesModule,
    ContextMenuModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
