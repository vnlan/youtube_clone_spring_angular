import { Component, Input, OnInit } from '@angular/core';
import { VideoDto } from 'src/app/dtos/VideoDto';

@Component({
  selector: 'app-video-card',
  templateUrl: './video-card.component.html',
  styleUrls: ['./video-card.component.css']
})
export class VideoCardComponent implements OnInit {

  @Input()
  video!: VideoDto;

  constructor() { }

  ngOnInit(): void {
  }

}
