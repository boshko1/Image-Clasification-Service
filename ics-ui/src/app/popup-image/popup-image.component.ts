import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { GalleryComponent } from '../gallery/gallery.component';
import { Image } from '../image';

@Component({
  selector: 'app-popup-image',
  templateUrl: './popup-image.component.html',
  styleUrls: ['./popup-image.component.css']
})
export class PopupImageComponent implements OnInit {

  constructor(private gallery: GalleryComponent) { }

  @Input() image?: Image;


  @Output() popup = new EventEmitter<boolean>();

  ngOnInit() {
  }

  public setToLocalStorage(tagName: string) {
    localStorage.setItem("tagName", tagName);
  }

  public closePopup() {
    this.gallery.ngOnInit();
    this.popup.emit(false);
  }

}
