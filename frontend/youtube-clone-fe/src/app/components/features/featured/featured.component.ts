import { VideoService } from 'src/app/services/features/video/video.service';
import { Component, OnInit } from '@angular/core';
import { VideoDto } from 'src/app/dtos/VideoDto';

@Component({
  selector: 'app-featured',
  templateUrl: './featured.component.html',
  styleUrls: ['./featured.component.css']
})
export class FeaturedComponent implements OnInit {

  featuredVideos: Array<VideoDto> = [];

  constructor(private videoService: VideoService) { }

  ngOnInit(): void {
    this.videoService.getAllVideos().subscribe(res => {
      this.featuredVideos = res;
    });
  }

}
