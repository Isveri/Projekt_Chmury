import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AlertService} from '../../services/alert.service';
import {Error} from '../../domain/Error';
import {COMMON_ERROR_MESSAGE} from '../../domain/CommonMessages';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginFormGroup: FormGroup;
  fieldTextType: boolean;

  constructor(private authService: AuthService,
              private formBuilder: FormBuilder,
              private router: Router,
              private alertService: AlertService) {
  }

  ngOnInit(): void {
    this.loginFormGroup = this.formBuilder.group({
      user: this.formBuilder.group({
        login: new FormControl('',
          [Validators.required, Validators.minLength(2)]),
        password: new FormControl('',
          [Validators.required, Validators.minLength(2)])
      })
    });
  }

  login() {
    if(this.loginFormGroup.valid) {
    this.authService.login(this.loginFormGroup.get('user').get('login').value, this.loginFormGroup.get('user').get('password').value)
      .subscribe(
        response => {
            this.authService.setToken(response.token);
            this.router.navigateByUrl('/dashboard');
          },
        error => {
          this.alertService.error("Wrong credentials");
        }
      );}else{
      this.alertService.error("Wrong credentials");
    }
  }

  logout() {
    this.authService.logout();
  }

  toggleFieldTextType() {
    this.fieldTextType = !this.fieldTextType;
  }
}
