import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/features/user/user.service';

@Component({
  selector: 'app-callback',
  templateUrl: './callback.component.html',
  styleUrls: ['./callback.component.css']
})
export class CallbackComponent implements OnInit {

  constructor(private userService: UserService) {
    this.userService.registerUser();
   }

  ngOnInit(): void {
  }

}
