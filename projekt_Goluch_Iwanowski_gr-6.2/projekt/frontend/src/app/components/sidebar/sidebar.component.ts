import {Component, OnInit} from '@angular/core';

declare interface RouteInfo {
  path: string;
  title: string;
  rtlTitle: string;
  icon: string;
  class: string;
}
export const ROUTES: RouteInfo[] = [

  {
    path: '/dashboard',
    title: 'Home',
    rtlTitle: 'Home',
    icon: 'icon-chart-pie-36',
    class: ''
  },
  {
    path:'/my-posts',
    title: 'My Posts',
    rtlTitle: 'My Posts',
    icon: 'icon-chart-pie-36',
    class:''
  },
  {
    path:'/user-profile',
    title: 'My profile',
    rtlTitle: 'My profile',
    icon: 'icon-chart-pie-36',
    class:''
  }
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];

  constructor() {}

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }
  isMobileMenu() {
    if (window.innerWidth > 991) {
      return false;
    }
    return true;
  }
}
