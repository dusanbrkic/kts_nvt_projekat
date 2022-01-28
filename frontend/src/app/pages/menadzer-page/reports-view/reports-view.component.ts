import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { ReportsService } from 'src/app/services/reports.service';

@Component({
  selector: 'app-reports-view',
  templateUrl: './reports-view.component.html',
  styleUrls: ['./reports-view.component.scss'],
})
export class ReportsViewComponent implements OnInit {
  basicOptions: any;
  basicData: any;

  rangeDates!: Date[];

  constructor(private reportsService: ReportsService,private messageService: MessageService) {}

  ngOnInit(): void {}

  dobavi() {
    if(this.rangeDates){
      if (this.rangeDates[0] && this.rangeDates[1]) {
        this.reportsService.formReport(
          this.rangeDates[0],
          this.rangeDates[1],
          (labels: any, gains: any, losses: any, profits: any) => {
            this.basicData = {
              labels: labels,
              datasets: [
                {
                  label: 'Gains',
                  data: gains,
                  borderColor: '#42A5F5',
                },
                {
                  label: 'Losses',
                  data: losses,
                  borderColor: '#FFA726',
                },
                {
                  label: 'Profits',
                  data: profits,
                  borderColor: '#00bb7e',
                },
              ],
            };
          }
        );
      }else{
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Izaberite 2 datuma',
        });
      }
    }else{
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Izaberite 2 datuma',
      });
    }
  }
}
