import { Component, OnInit } from '@angular/core';
import { ImageService } from '../image.service';
import { Image } from '../image'

@Component({
  selector: 'app-image-submit',
  templateUrl: './image-submit.component.html',
  styleUrls: ['./image-submit.component.css']
})
export class ImageSubmitComponent implements OnInit {

  private images: Image[] = [];
  private popupIsOpened = false;
  public lastAdded: Image | undefined;


  ngOnInit() {
    this.setImages();
  }

  constructor(private imageService: ImageService) { }


  public setImages(): void {
    this.imageService.getImages().subscribe({
      next: (res) => this.images = res,
      error: console.error
    });
  }

  public addImage(url: string): void {
    this.imageService.addImage(url).subscribe({
      next: (res) => {this.lastAdded = res,
        this.popupIsOpened = true},
      error: () => {console.error, alert("Invalid image URL!")}
    });
  }

  public closePopup(): void {
    this.popupIsOpened = false;
    this.lastAdded = undefined;
  }

  public openPopup(url: string): void {

    if(url=="" || url == " "){
      return;
    }
    let url_present: boolean = false;

    for(let image of this.images){
      if(image.url == url){
        url_present = true;
        this.lastAdded = image;
        break;
      }
    }

    if(url_present){
      this.popupIsOpened = true;
      return;
    }
    else{
    this.addImage(url);
    } 
  }

  public getImages(): Image[]{
    return this.images;
  }

  public getPopupState(): boolean{
    return this.popupIsOpened;
  }
}
