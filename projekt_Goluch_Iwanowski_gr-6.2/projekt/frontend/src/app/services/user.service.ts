import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../domain/User';
import {Observable} from 'rxjs';
import {PasswordChangeDto} from '../domain/dto/PasswordChangeDto';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  editUserUrl = 'http://localhost:8080/api/v1/users/edit'
  baseURL = 'http://localhost:8080/api/v1/users'
  constructor(private http: HttpClient) {
  }

  editUser(user:User): Observable<any>{
    const headers = {'content-type': 'application/json'}
    const body = JSON.stringify(user);
  return this.http.put(this.editUserUrl,body,{headers});
  }

  getUser():Observable<User>{
    return this.http.get<User>(this.baseURL)
  }

  getUserPosts() {
    return this.http.get<User>(this.baseURL+'/my-posts')
  }
}
