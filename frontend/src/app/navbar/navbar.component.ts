import { Component, Input, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  @Input() items!: MenuItem[];
    
  activeItem!: MenuItem;

  constructor() { }

  ngOnInit(): void {
    this.activeItem = this.items[0];
  }

}
