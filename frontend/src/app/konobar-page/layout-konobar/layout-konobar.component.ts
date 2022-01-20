import { Component, OnInit } from '@angular/core';
import Sto from 'src/app/model/Sto';
import Zona from 'src/app/model/Zona';
import { ZonaService } from 'src/app/services/zona-service.service';

@Component({
  selector: 'app-layout-konobar',
  templateUrl: './layout-konobar.component.html',
  styleUrls: ['./layout-konobar.component.scss']
})
export class LayoutKonobarComponent implements OnInit {

  zone!: Zona[];
  selectedZona!: Zona;

  showSidebar: boolean=false;
  selectedSto!:Sto;

  constructor(private zonaService: ZonaService) { }

  ngOnInit(): void {
    this.zonaService.loadZoneTest();
    this.zonaService.zone$.subscribe((value) => {
      this.zone = value;
      console.log(value);
    });

    this.selectedZona = this.zone[0];
  }

  openSidebar(sto:Sto){
    this.selectedSto=sto;
    this.showSidebar=true;
  }

}
