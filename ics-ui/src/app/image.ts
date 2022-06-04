import { tagImage } from "./tag_image";

export interface Image{
    id: number;
    url: string;
    upload_date: string;
    status: string;
    width: number;
    height: number;
    links: tagImage[];
}