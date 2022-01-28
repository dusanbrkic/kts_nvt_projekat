import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { MessageService } from 'primeng/api';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-id-input',
  templateUrl: './id-input.component.html',
  styleUrls: ['./id-input.component.scss'],
})
export class IdInputComponent implements OnChanges {
  @Input() displayModal: boolean = false;
  @Input() callback: any;
  @Input() onCloseCallback: any;
  @Input() role!: string;
  username: string = '';

  constructor(
    private authService: AuthService,
    private messageService: MessageService
  ) {}

  ngOnChanges(changes: SimpleChanges): void {
    
  }

  ngOnInit(): void {}

  login() {
    if (this.username) {
      this.authService
        .login({ username: this.username, password: 'identification' })
        .subscribe(
          (result) => {
            localStorage.setItem('user', JSON.stringify(result));
            switch (result.role) {
              case this.role: {
                this.callback()
                this.authService.logout()
                break;
              }
              default: {
                this.messageService.add({
                  severity: 'error',
                  summary: 'Error',
                  detail: 'Wrong role',
                });
              }
            }
          },
          (error) => {
            console.log(error);
            this.messageService.add({
              severity: 'error',
              summary: 'Bad credentials',
              detail: 'Username wrong',
            });
          }
        );
    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'Bad input',
        detail: 'Bad input',
      });
    }
  }

  close(){
    this.displayModal=false
    this.onCloseCallback()
  }
}
