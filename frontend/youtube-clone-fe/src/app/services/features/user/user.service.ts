import { UserDto } from './../../../dtos/UserDto';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private currentUser!: any;
 
  private backendUrl = "http://localhost:8080"
  constructor(private httpClient: HttpClient) {
    
   }

  subscribeToUser(userId: string): Observable<boolean>{
    return this.httpClient.post<boolean>(this.backendUrl+"/api/users/subscribe/"+ userId, null);
  }
  unsubscribeToUser(userId: string): Observable<boolean> {
    return this.httpClient.post<boolean>(this.backendUrl+"/api/users/unsubscribe/"+ userId, null);
  }
  getOrRegisterUser(): Observable<UserDto>{
    return this.httpClient.get<UserDto>(this.backendUrl+"/api/users/register");
  }
  registerUser() {

      this.httpClient.get<UserDto>(this.backendUrl+"/api/users/register")
      .subscribe(res => {
        this.currentUser = res;

        console.log(this.currentUser);
      });
      
  }
  getCurrentUser(): string{
    return this.currentUser;
  }
  
}

