import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';

import { FormsModule } from '@angular/forms';
import { PopupImageComponent } from './popup-image/popup-image.component';
import { GalleryComponent } from './gallery/gallery.component';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ImageSubmitComponent } from './image-submit/image-submit.component';

@NgModule({
  declarations: [				
    AppComponent,
      PopupImageComponent,
      GalleryComponent,
      ImageSubmitComponent
   ],
   entryComponents: [],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [ImageSubmitComponent, GalleryComponent],
  bootstrap: [AppComponent],
  exports: [ImageSubmitComponent]
})
export class AppModule { }
