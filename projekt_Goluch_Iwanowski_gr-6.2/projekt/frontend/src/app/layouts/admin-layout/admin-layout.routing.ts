import {Routes} from '@angular/router';

import {DashboardComponent} from '../../pages/dashboard/dashboard.component';
import {LoginComponent} from '../../components/login/login.component';
import {AuthGuard} from '../../services/AuthGuard';
import {RegisterComponent} from '../../components/register/register.component';
import {UserProfileComponent} from '../../components/user-profile/user-profile.component';
import {MyPostsComponent} from '../../components/my-posts/my-posts.component';
import {PostAddComponent} from '../../components/post-add/post-add.component';
import {PostShowComponent} from '../../components/post-show/post-show.component';

export const AdminLayoutRoutes: Routes = [
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'my-posts', component: MyPostsComponent,canActivate:[AuthGuard] },
  { path: 'post-add',component:PostAddComponent, canActivate: [AuthGuard]},
  { path: 'user-profile',component:UserProfileComponent, canActivate: [AuthGuard]},
  { path: 'post-show',component:PostShowComponent, canActivate: [AuthGuard]}
];
