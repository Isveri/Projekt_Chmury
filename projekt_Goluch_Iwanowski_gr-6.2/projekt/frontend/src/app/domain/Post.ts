import {User} from "./User";
import {Comment} from "./Comment";

export class Post {
  public id: number;
  public title: string;
  public description: string;
  public creator: User;
  public comments: Comment[];
}
