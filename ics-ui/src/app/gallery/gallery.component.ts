import { Component, OnInit } from '@angular/core';
import { Image } from '../image'
import { ImageService } from '../image.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css']
})
export class GalleryComponent implements OnInit {

  private images: Image[] = [];
  private popupIsOpened = false;
  public currentImage!: Image;

  ngOnInit() {
    if (localStorage.getItem("tagName") === null) {
      this.setImages();
    }
    else {
      this.setImagesByTag(localStorage.getItem("tagName") as string);
    }
  }

  constructor(private imageService: ImageService, private activatedRoute: ActivatedRoute) { }

  public filterImages(tagName: string): void {

    this.closePopup();
    this.setImagesByTag(tagName);
  }

  public clearStorage() {
    localStorage.removeItem("tagName");
  }

  public setImages(): void {
    this.imageService.getImages().subscribe({
      next: (res) => this.images = res,
      error: console.error
    });
  }

  public setImagesByTag(tagName: string): void {
    if (tagName == "") {
      this.setImages();
    }
    else {
      localStorage.setItem("tagName", tagName);
      this.imageService.getImagesByTag(tagName).subscribe({
        next: (res) => {
          if (res.length==0) {
            alert("No images match this tag!");
            localStorage.removeItem("tagName");
          }
          else {
            this.images = res;
          }
        },
        error: console.error
      })
    }
  }

  public getImages(): Image[] {
    return this.images;
  }

  public getPopupStage(): boolean {
    return this.popupIsOpened;
  }

  public openPopup(image: Image): void {
    this.popupIsOpened = true;
    this.currentImage = image;
  }

  public closePopup(): void {
    this.popupIsOpened = false;
  }
  

}
