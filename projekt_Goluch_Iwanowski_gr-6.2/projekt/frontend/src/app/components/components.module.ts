import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {FooterComponent} from './footer/footer.component';
import {NavbarComponent} from './navbar/navbar.component';
import {SidebarComponent} from './sidebar/sidebar.component';
import {LoginComponent} from './login/login.component';
import {ReactiveFormsModule} from '@angular/forms';
import {RegisterComponent} from './register/register.component';
import {AlertComponent} from './alert/alert.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { PostAddComponent } from './post-add/post-add.component';
import { MyPostsComponent } from './my-posts/my-posts.component';
import { PostShowComponent } from './post-show/post-show.component';

@NgModule({
  imports: [CommonModule, RouterModule, NgbModule, ReactiveFormsModule],
  declarations: [FooterComponent, NavbarComponent, SidebarComponent, LoginComponent, RegisterComponent,
    AlertComponent, UserProfileComponent, PostAddComponent,
    MyPostsComponent,
    PostShowComponent],
    exports: [FooterComponent, NavbarComponent, SidebarComponent, AlertComponent]
})
export class ComponentsModule {
}
