import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem, MessageService } from 'primeng/api';
import { AuthService } from '../services/auth.service';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-kuvar-page',
  templateUrl: './kuvar-page.component.html',
  styleUrls: ['./kuvar-page.component.scss'],
})
export class KuvarPageComponent implements OnInit {
  items!: MenuItem[];
  selectedTab: number = 0;

  stompClient: any;

  constructor(private router: Router,private authService: AuthService,private messageService: MessageService) {}

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
        label: 'Button Return',
        icon: 'pi pi-fw pi-power-off',
        style: { 'margin-left': 'auto' },
        command: (event) => {
          this.authService.logout()
          this.router.navigateByUrl('/');
        },
      },
    ];

    const socket = new SockJS(environment.baseUrl + 'websocket');
    this.stompClient = Stomp.over(socket);

    this.stompClient.connect({}, (frame: any) => {
      this.stompClient.subscribe('/topic/new-porudzbina-notification',(message: any) => {
        console.log(JSON.parse(message.body).content)
        this.messageService.add({severity:'info', sticky: true,summary:'Notification', detail:JSON.parse(message.body).content});
      });
    });
  }
}
