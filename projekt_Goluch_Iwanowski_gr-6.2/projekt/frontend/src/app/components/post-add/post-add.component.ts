import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AlertService} from '../../services/alert.service';
import {PostService} from '../../services/post.service';
import {Post} from '../../domain/Post';

@Component({
  selector: 'app-post-add',
  templateUrl: './post-add.component.html',
  styleUrls: ['./post-add.component.scss']
})
export class PostAddComponent implements OnInit {

  postAddForm: FormGroup;

  constructor(private postService: PostService,
              private formBuilder: FormBuilder,
              private router: Router,
              private alertService: AlertService) {
  }

  ngOnInit(): void {
    this.postAddForm = this.formBuilder.group({
      newPost: this.formBuilder.group({
        title: new FormControl('',
          [Validators.required, Validators.minLength(2)]),
        description: new FormControl('',[Validators.required, Validators.minLength(2)])
      })
    });
  }

  // addGroupRoom() {
  //   console.log(this.passwordAddFormGroup.get('new-password')?.value);
  //
  //   const password = this.createPasswordObject();
  //
  //   this.groupRoomService.(password)
  //     .subscribe(
  //       () => {
  //         this.router.navigateByUrl('/dashboard');
  //       }, () => {
  //         this.alertService.error('Title, login and password must be set');
  //       }
  //     );
  // }

  public addPost() {
    const newPost = this.createPostObject();
    this.postService.addPost(newPost)
        .subscribe(
          () => {
            this.router.navigateByUrl('/dashboard');
          }, () => {
            this.alertService.error('Something went wrong! Try again');
          }
        );
  }

  private createPostObject(): Post {
    const post = new Post();
    post.title = this.postAddForm.get('newPost').get('title').value;
    post.description = this.postAddForm.get('newPost').get('description').value;

    return post;
  }
}
