import { UserService } from 'src/app/services/features/user/user.service';
import { Component, OnInit } from '@angular/core';
import {  FormControl, FormGroup } from '@angular/forms';
import { MatChipInputEvent } from '@angular/material/chips';
import { ENTER,COMMA } from '@angular/cdk/keycodes';
import { BehaviorSubject, Subscription } from 'rxjs';
import { VideoService } from 'src/app/services/features/video/video.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { VideoDto } from 'src/app/dtos/VideoDto';

@Component({
  selector: 'app-add-video-details',
  templateUrl: './add-video-details.component.html',
  styleUrls: ['./add-video-details.component.css']
})
export class AddVideoDetailsComponent implements OnInit {


    addVideoDetailsForm : FormGroup;
  
    title  :FormControl =  new FormControl();
    description :FormControl = new FormControl();
    videoStatus :FormControl = new FormControl();

    selectable = true;
    removable = true;
    addOnBlur = true;

    readonly separatorKeysCodes = [ENTER, COMMA] as const;
   
    showVideoUrl = false;
    videoUrlAvailable = false;
    videoUrl!: string;
    thumbnailUrl!: string;
    videoId!: string;
    selectedFile!: File;
    selectedFileName = '';
    uploadThumbnailSubscription!: Subscription;
    fileUploaded!: boolean;
    thumbnailUploaded: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

    tags: string[] = [];



  constructor(private activatedRoute : ActivatedRoute,
     private videoService: VideoService,
    private matSnackBar: MatSnackBar,
    private userService: UserService) { 
      
    this.addVideoDetailsForm = new FormGroup({
      title : this.title, 
      description : this.description, 
      videoStatus : this.videoStatus, 
    });

    this.videoId = this.activatedRoute.snapshot.params.videoId;
    this.videoService.getVideo(this.videoId).subscribe(res => {
      this.videoUrl = res.videoUrl;
      this.thumbnailUrl = res.thumbnailUrl;
    });

      
  }
  ngOnInit(): void {


  }


  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.tags.push(value);
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  remove(value: string): void {
    const index = this.tags.indexOf(value);

    if (index >= 0) {
      this.tags.splice(index, 1);

     
    }
  }
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.fileUploaded = true;
    this.selectedFileName = this.selectedFile.name;
  }

  onUpload(event: any) {
    this.uploadThumbnailSubscription = this.videoService.uploadThumbnail(this.selectedFile, this.videoId)
      .subscribe(data => {
        this.thumbnailUploaded.subscribe(() => {
          this.matSnackBar.open("Thumbnail Uploaded Successfully", "OK");
          this.fileUploaded = true;
        });
      });
  }
  saveVideo(event: any) {
   const videoMetadata :VideoDto = {

    "id": this.videoId,
    "title": this.addVideoDetailsForm.get('title')?.value,
    "description": this.addVideoDetailsForm.get('description')?.value,
    "tags": this.tags,
    "videoUrl": this.videoUrl,
    "videoStatus": this.addVideoDetailsForm.get('videoStatus')?.value,
    "thumbnailUrl": this.thumbnailUrl,
    "likeCount": 0,
    "dislikeCount": 0,
    "viewCount": 0,
    "userId": this.userService.getCurrentUser(),
   }
   this.videoService.saveVideo(videoMetadata).subscribe(res => {
    this.matSnackBar.open("Video details uploaded successfully!", "OK");
   });
  }

}
