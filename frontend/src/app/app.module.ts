import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { HeaderComponent } from './shared/header/header.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { KonobarPageComponent } from './pages/konobar-page/konobar-page.component';
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
import { MenuComponent } from './shared/menu/menu.component';
import {DataViewModule} from 'primeng/dataview';
import {TableModule} from 'primeng/table';
import {TabViewModule} from 'primeng/tabview';
import {DragDropModule} from 'primeng/dragdrop';
import {TabMenuModule} from 'primeng/tabmenu';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { SankerPageComponent } from './pages/sanker-page/sanker-page.component';
import {TooltipModule} from 'primeng/tooltip';
import { PorudzbineViewSankerComponent } from './pages/sanker-page/porudzbine-view-sanker/porudzbine-view-sanker.component';
import {AccordionModule} from 'primeng/accordion';
import {ToastModule} from 'primeng/toast';
import {ToolbarModule} from 'primeng/toolbar';
import { KuvarPageComponent } from './pages/kuvar-page/kuvar-page.component';
import {InplaceModule} from 'primeng/inplace';
import { PorudzbineViewKuvarComponent } from './pages/kuvar-page/porudzbine-view-kuvar/porudzbine-view-kuvar.component';
import {PickListModule} from 'primeng/picklist';
import {PanelModule} from 'primeng/panel';
import { LayoutAdminComponent } from './pages/admin-page/layout-admin/layout-admin.component';
import { AdminPageComponent } from './pages/admin-page/admin-page.component';
import { DragDropModule as DragDropCDKModule} from '@angular/cdk/drag-drop';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import { ZaposleniViewComponent } from './shared/zaposleni-view/zaposleni-view.component';
import {ContextMenuModule} from 'primeng/contextmenu';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { MenuJelaComponent } from './shared/menu/menu-jela/menu-jela.component';
import { MenuPicaComponent } from './shared/menu/menu-pica/menu-pica.component';
import { MenadzerPageComponent } from './pages/menadzer-page/menadzer-page.component';
import { LayoutKonobarComponent } from './pages/konobar-page/layout-konobar/layout-konobar.component';
import {SidebarModule} from 'primeng/sidebar';
import { FileUploadModule } from 'primeng/fileupload';
import {ImageModule} from 'primeng/image';
import {CardModule} from 'primeng/card';
import {SplitterModule} from 'primeng/splitter';
import { TagModule } from 'primeng/tag';
import {CarouselModule} from 'primeng/carousel';
import { InterInterceptor } from './utils/interceptor/inter.interceptor';
import { PredloziViewComponent } from './pages/menadzer-page/predlozi-view/predlozi-view.component';
import {BadgeModule} from 'primeng/badge';
import { IdInputComponent } from './shared/id-input/id-input.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
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
    MenuJelaComponent,
    MenuPicaComponent,
    MenadzerPageComponent,
    LayoutKonobarComponent,
    PredloziViewComponent,
    IdInputComponent,
  ],
  imports: [
    ImageModule,
    FileUploadModule,
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
    ContextMenuModule,
    ConfirmDialogModule,
    SidebarModule,
    CardModule,
    SplitterModule,
    TagModule,
    AutoCompleteModule,
    CarouselModule,
    BadgeModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: InterInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
