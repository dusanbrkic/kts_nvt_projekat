import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-kuvar-page',
  templateUrl: './kuvar-page.component.html',
  styleUrls: ['./kuvar-page.component.scss'],
})
export class KuvarPageComponent implements OnInit {
  items!: MenuItem[];
  selectedTab: number = 0;

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.items = [
      {
        label: 'Porudzbine',
        icon: 'pi pi-fw pi-th-large',
        command: (event) => {
          this.selectedTab = 0;
        },
      },
      {
        label: 'Jelovnik',
        icon: 'pi pi-fw pi-apple',
        command: (event) => {
          this.selectedTab = 1;
        },
      },
      {
        label: 'Button Notifications',
        icon: 'pi pi-fw pi-bell',
        style: { 'margin-left': 'auto' },
        command: (event) => {
          this.router.navigateByUrl('/');
        },
      },
      {
        label: 'Button Return',
        icon: 'pi pi-fw pi-power-off',
        style: { align: 'right' },
        command: (event) => {
          this.router.navigateByUrl('/');
        },
      },
    ];
  }
}
