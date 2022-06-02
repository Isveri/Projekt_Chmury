import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';
import {AlertService} from '../../services/alert.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  loginFormGroup: FormGroup;
  selected: string;
  fieldTextType: boolean;

  constructor(private authService: AuthService,
              private formBuilder: FormBuilder,
              private router: Router,
              private alertService: AlertService) {
  }

  ngOnInit(): void {
    this.loginFormGroup = this.formBuilder.group({
      user: this.formBuilder.group({
        username: new FormControl('',
          [Validators.required, Validators.minLength(3)]),
        password: new FormControl('',
          [Validators.required, Validators.minLength(3)]),
      })
    });
  }

  register() {
    console.log(this.loginFormGroup.get('user')?.value);
    if(this.loginFormGroup.valid){
    this.authService.register(
      this.loginFormGroup.get('user').get('username').value,
      this.loginFormGroup.get('user').get('password').value).subscribe({
        next: response => {
          if(response?.token == null){
            this.alertService.error("User with this username already exist");
          }else{
          this.authService.setToken(response.token);
          this.redirectToDashboard();
          }
        },
        error: err => {
          this.alertService.error(`Login is taken or invalid data given`);
        }
      }
    );}else{
     this.alertService.error("Login and password must contains at least 3 characters");
    }
  }

  private redirectToDashboard() {
    this.router.navigateByUrl('/dashboard');
  }

  toggleFieldTextType() {
    this.fieldTextType = !this.fieldTextType;
  }
}
