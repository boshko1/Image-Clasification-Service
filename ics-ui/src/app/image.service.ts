import { HttpClient } from '@angular/common/http';
import { Injectable, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Image } from './image';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getImages(): Observable<Image[]> {
    return this.http.get<Image[]>(`${this.apiServerUrl}/images`)
  }

  public getImageById(id: number): Observable<Image> {
    return this.http.get<Image>(`${this.apiServerUrl}/images/${id}`)
  }
/*
  public getImageByUrl(url: string): Observable<Image> {
    return this.http.get<Image>(`${this.apiServerUrl}/images/url`, {observe: 'body'})
  }*/

  public addImage(url: string): Observable<Image> {
    return this.http.post<Image>(`${this.apiServerUrl}/images`, url)
  }

  public getImagesByTag(tagName: string): Observable<Image[]> {
    return this.http.get<Image[]>(`${this.apiServerUrl}/images/tag/${tagName}`);
  }
}
