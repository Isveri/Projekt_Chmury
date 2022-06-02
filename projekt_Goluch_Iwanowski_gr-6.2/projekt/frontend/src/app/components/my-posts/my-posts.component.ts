import { Component, OnInit } from '@angular/core';
import {AlertService} from '../../services/alert.service';
import {PostService} from '../../services/post.service';
import {UserService} from '../../services/user.service';
import {User} from '../../domain/User';
;

@Component({
  selector: 'app-my-posts',
  templateUrl: './my-posts.component.html',
  styleUrls: ['./my-posts.component.scss']
})
export class MyPostsComponent implements OnInit {

  user: User;

  constructor(private alertService: AlertService, private postService: PostService, private userService:UserService) {
  }

  ngOnInit(): void {
    this.loadData();
  }

  removePost(postId: number) {
    this.postService.removePost(postId).subscribe(
      () => {
        this.alertService.success('You succesfully removed ur post');
        this.loadData();
      },
      () => this.alertService.error('Error while removing group'));
  }

  loadData() {
    this.userService.getUserPosts().subscribe(
      (data:User) => this.user = data,
      () => this.alertService.error('Error while getting groups')
    );
  }
}
