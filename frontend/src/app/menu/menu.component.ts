import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { LazyLoadEvent } from 'primeng/api';
import { Inplace } from 'primeng/inplace';
import Jelo from '../model/Jelo';
import Pice from '../model/Pice';
import { JeloService } from '../services/jelo.service';
import { PiceService } from '../services/pice.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss'],
})
export class MenuComponent implements OnInit {

  constructor() {}

  ngOnInit(): void {
  }
}
