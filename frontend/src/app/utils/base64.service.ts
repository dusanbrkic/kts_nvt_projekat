import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Base64Service {

  constructor() { }

  encode(inputFile: any, callback: any){

    if (inputFile) {
        const reader = new FileReader();

        reader.onloadend = function () {
          callback(reader.result);
        };

        reader.readAsDataURL(inputFile);
    }
  }

  decode(inputString: string) : Blob{
    const byteString = window.atob(inputString);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const int8Array = new Uint8Array(arrayBuffer);
    for (let i = 0; i < byteString.length; i++) {
      int8Array[i] = byteString.charCodeAt(i);
    }
    const blob = new Blob([int8Array], { type: 'image/png' });
    return blob;
  }
}
