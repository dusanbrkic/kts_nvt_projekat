import {  Component } from '@angular/core';
import { MessageService } from 'primeng/api';
import { HeaderComponent } from './header/header.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [MessageService]
})
export class AppComponent  {
  title = 'frontend';
}
