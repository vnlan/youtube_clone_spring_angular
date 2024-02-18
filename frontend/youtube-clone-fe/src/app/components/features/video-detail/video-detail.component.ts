import { UserDto } from './../../../dtos/UserDto';
import { VideoService } from '../../../services/features/video/video.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/features/user/user.service';

@Component({
  selector: 'app-video-detail',
  templateUrl: './video-detail.component.html',
  styleUrls: ['./video-detail.component.css']
})
export class VideoDetailComponent implements OnInit {


  videoId!: string;
  videoUrl!:string;
  videoTitle!: string;
  videoDescription!: string;
  tags: Array<string> = [];
  videoAvailable : boolean = false;
  likeCount: number = 0;
  dislikeCount: number = 0;
  viewCount: number = 0;
  showSubscribeButton: boolean = true;
  showUnsubscribeButton: boolean = false;

  currentUser !: any;

  // userInfo!: UserDto;

  constructor(private activatedRoute: ActivatedRoute,
              private videoService: VideoService,
              private userService: UserService) { 
    this.videoId = this.activatedRoute.snapshot.params.videoId;

    this.videoService.getVideo(this.videoId).subscribe(res => {
      this.videoUrl = res.videoUrl;
      this.videoTitle = res.title;
      this.videoDescription = res.description;
      this.tags = res.tags;
      this.videoAvailable = true;
      this.likeCount = res.likeCount;
      this.dislikeCount = res.dislikeCount;
      this.viewCount = res.viewCount;
      
    });



  }

  ngOnInit(): void {
     
  }


  likeVideo() {
    this.videoService.likeVideo(this.videoId).subscribe(res => {
      this.likeCount = res.likeCount;
      this.dislikeCount = res.dislikeCount;
    });
  }

  dislikeVideo() {
    this.videoService.dislikeVideo(this.videoId).subscribe(res => {
      this.likeCount = res.likeCount;
      this.dislikeCount = res.dislikeCount;
    });
  }
  subscribeToUser(){
    
    // this.userInfo = this.userService.getCurrentUser();
    this.userService.getOrRegisterUser().subscribe(res => {
      this.currentUser = res;
      this.userService.subscribeToUser(this.currentUser.id).subscribe(res => {
        this.showUnsubscribeButton = true;
        this.showSubscribeButton = false;
      });
    });
    

      
      console.log(this.currentUser);
  }
  unSubscribeToUser(){
   
    // this.userService.unsubscribeToUser(this.userId).subscribe(res => {

    // });
    this.userService.getOrRegisterUser().subscribe(res => {
      this.currentUser = res;
      this.userService.unsubscribeToUser(this.currentUser.id).subscribe(res => {
        this.showUnsubscribeButton = false;
        this.showSubscribeButton = true;
      });
    });
  }

  // testHihi(){
  //   console.log(this.userService.testHihi());
    
  // }
}
