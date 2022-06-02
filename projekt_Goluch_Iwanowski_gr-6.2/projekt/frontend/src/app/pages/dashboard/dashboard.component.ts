import {Component, OnInit} from '@angular/core';

import {ControlHelperService} from '../../services/control-helper.service';
import {Router} from '@angular/router';
import {AlertService} from '../../services/alert.service';
import {FormBuilder} from '@angular/forms';
import {Post} from '../../domain/Post';
import {PostService} from '../../services/post.service';
import {UserService} from "../../services/user.service";
import {AuthService} from "../../services/auth.service";
import {User} from "../../domain/User";

@Component({
  selector: 'app-dashboard',
  templateUrl: 'dashboard.component.html'
})
export class DashboardComponent implements OnInit {

  public data: any;
  public isAdmin = false;

  display = 'none';

  currentUser:User;
  currentPost: Post;
  posts: Post[] = [];

  constructor(private postService: PostService,
              private controlHelperService: ControlHelperService,
              private formBuilder: FormBuilder,
              private alertService: AlertService,
              private router: Router,
              private userService:UserService,
              private authService:AuthService,) {
  }

  ngOnInit() {
    this.loadData();
    if(this.checkIfLoggedIn()) {
      this.checkIfAdmin()
    }
    }
  loadData() {
    this.postService.getPosts().subscribe(
      (data:any) => this.posts = data,
      () => this.alertService.error('Error while getting posts')
    );
  }
  deletePost(postId:number){
    this.postService.removePost(postId).subscribe(
      () => {
        this.alertService.success('You succesfully removed ur post');
        this.loadData();
      },
      () => this.alertService.error('Error while removing post'));
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
  checkIfLoggedIn() {
    if (this.authService.getToken()) {
      return true;
    }
    else return false;
  }
}
