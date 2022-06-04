import { Image } from "./image";
import { Tag } from "./tag";

export interface tagImage{
    image_id: number;
    tag_id: number;
    tag_name: string;
    image: Image;
    tag: Tag;
    confidence: number;
}