import {Post} from './Post';
import {Role} from './Role';

export class User {
  public id: number;
  username: string;
  password: string;
  email: string;
  name: string;
  public posts: Post[];
  role: Role;
}
