import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem, MessageService } from 'primeng/api';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.scss'],
})
export class AdminPageComponent implements OnInit {
  items!: MenuItem[];
  selectedTab: number = 0;

  showPasswordChanger: boolean=false;
  passwordOnClose: any;

  constructor(private router: Router,private authService: AuthService) {}

  ngOnInit(): void {
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
        label: 'Button Change Password',
        icon: 'pi pi-fw pi-key',
        style: { 'margin-left': 'auto' },
        command: (event) => {
          this.showPasswordChanger=true
        },
      },
      {
        label: 'Button Return',
        icon: 'pi pi-fw pi-power-off',
        style: { align: 'left' },
        command: (event) => {
          this.authService.logout()
          this.router.navigateByUrl('/');
        },
      },
    ];

    this.passwordOnClose=()=>{
      this.showPasswordChanger=false;
    }
  }
}
