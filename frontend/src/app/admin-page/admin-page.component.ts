import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.scss'],
})
export class AdminPageComponent implements OnInit {
  items!: MenuItem[];
  selectedTab: number = 0;

  constructor(private router: Router) {}

  ngOnInit(): void {
    localStorage.setItem('user', 'ADMIN');
    this.items = [
      {
        label: 'Layout',
        icon: 'pi pi-fw pi-th-large',
        command: (event) => {
          this.selectedTab = 0;
        },
      },
      {
        label: 'Zaposleni',
        icon: 'pi pi-fw pi-users',
        command: (event) => {
          this.selectedTab = 1;
        },
      },
      {
        label: 'Button Return',
        icon: 'pi pi-fw pi-power-off',
        style: { 'margin-left': 'auto' },
        command: (event) => {
          this.router.navigateByUrl('/');
        },
      },
    ];
  }
}
