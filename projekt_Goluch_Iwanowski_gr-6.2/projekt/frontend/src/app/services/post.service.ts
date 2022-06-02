import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Post} from '../domain/Post';
import {Comment} from '../domain/Comment';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private baseUrl = 'http://localhost:8080/api/v1';

  constructor(private httpClient: HttpClient) { }

  getPosts(): Observable<Post[]> {

    return this.httpClient.get<Post[]>(this.baseUrl+'/posts/all');
  }


  deleteComment(commentId: number) {
    const url = this.baseUrl + '/posts/comment/'+commentId;
    return this.httpClient.delete(url);
  }

  showPostContent(postId:number):Observable<Post>{
    return this.httpClient.get<Post>(this.baseUrl+'/posts/' + postId)
  }

  addPost(newPost: Post) {
    const headers = {'content-type': 'application/json'}
    const body = JSON.stringify(newPost);
    return this.httpClient.post<Post>(this.baseUrl + '/posts/add',body,{headers});
  }

  addComment(newComment: Comment){
    const headers = {'content-type': 'application/json'}
    const body = JSON.stringify(newComment);
    return this.httpClient.post<Comment>(this.baseUrl+'/posts/newComment',body,{headers});
  }
  removePost(postId: number) {
    const url = this.baseUrl+'/posts/' + postId

    return this.httpClient.delete(url);
  }
}
