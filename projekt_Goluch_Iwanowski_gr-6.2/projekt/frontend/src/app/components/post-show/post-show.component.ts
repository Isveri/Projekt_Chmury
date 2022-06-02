import { Component, OnInit } from '@angular/core';
import {PostService} from '../../services/post.service';
import {AlertService} from '../../services/alert.service';
import {Post} from '../../domain/Post';
import {Comment} from '../../domain/Comment';
import {Router} from '@angular/router';
import {User} from "../../domain/User";
import {UserService} from "../../services/user.service";


@Component({
  selector: 'app-post-show',
  templateUrl: './post-show.component.html',
  styleUrls: ['./post-show.component.scss']
})
export class PostShowComponent implements OnInit {

  id:number = history.state.data;
  currentPost:Post;
  inputComment:string;

  isAdmin = false;
  currentUser:User;

  constructor(private postService:PostService,
              private alertService:AlertService,
              private userService:UserService,
              private router:Router) {this.router.routeReuseStrategy.shouldReuseRoute = () => false; }

  ngOnInit(): void {
    this.showPostContent(this.id)
    this.checkIfAdmin();
  }

  showPostContent(postId: number) {
    if(postId===undefined){
      postId = Number(localStorage.getItem('postId'));
    }else{
      localStorage.setItem('postId',postId.toString());
    }
    this.postService.showPostContent(postId).subscribe((data:any)=> this.currentPost = data,
      ()=>this.alertService.error('Error while getting single post data'))
  }

  onKey(event){
    this.inputComment = event.target.value;
  }


   public addComment(){
    if(this.inputComment!=="" && this.inputComment!=null) {
      const newComment = this.createCommentObject();
      this.postService.addComment(newComment)
        .subscribe(
          () => {
            this.ngOnInit();
          }, () => {
            this.alertService.error('Something went wrong! Try again');
          }
        );
    }else{
      this.alertService.error("Cant send empty comment");
    }
  }

  createCommentObject(){
    console.log(this.inputComment)
    const newComment = new Comment();
    newComment.postId=this.currentPost.id;
    newComment.text=this.inputComment;
    return newComment;
  }

  deleteComment(commentId:number){
    this.postService.deleteComment(commentId).subscribe(
      () => {
        this.alertService.success('You succesfully removed this comment');
        this.showPostContent(this.id);
      },
      () => this.alertService.error('Error while removing comment'));
  }
  checkIfAdmin() {
    this.userService.getUser().subscribe(
      data => {
        this.currentUser = data
        if ( this.currentUser?.role.name === "ROLE_ADMIN") {
          this.isAdmin = true;
        }
      }, () => {
        this.alertService.error('Error');
      }
    );
  }
}
