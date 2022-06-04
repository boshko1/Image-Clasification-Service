import { tagImage } from "./tag_image";

export interface Tag{
    id: number;
    name: string;
    links: tagImage[];
}