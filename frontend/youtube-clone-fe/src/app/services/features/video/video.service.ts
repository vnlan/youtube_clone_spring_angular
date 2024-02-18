
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UploadVideoResponseDto } from 'src/app/dtos/UploadVideoResponseDto';
import { VideoDto } from 'src/app/dtos/VideoDto';

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  private backendUrl = "http://localhost:8080";

  constructor(private httpClient: HttpClient) { }

  uploadVideo(fileEntry:File) : Observable<UploadVideoResponseDto>{
    const formData = new FormData();
    formData.append('file',fileEntry ,fileEntry.name);
    
    return this.httpClient.post<UploadVideoResponseDto>(this.backendUrl+"/api/videos",formData);

  }
  uploadThumbnail(fileEntry:File, videoId: string) : Observable<string>{
    const formData = new FormData();
    formData.append('file',fileEntry ,fileEntry.name);
    formData.append('videoId', videoId);
    
    return this.httpClient.post(this.backendUrl+"/api/videos/thumbnail",formData,{
        responseType: "text",
    });

  }
  getVideo(videoId: string): Observable<VideoDto>{
    return this.httpClient.get<VideoDto>(this.backendUrl+"/api/videos/"+videoId);
  }

  saveVideo(videoMetadata: VideoDto): Observable<VideoDto> {
   return this.httpClient.put<VideoDto>(this.backendUrl+"/api/videos/", videoMetadata);
  }
  getAllVideos(): Observable<Array<VideoDto>>{
    return this.httpClient.get<Array<VideoDto>>(this.backendUrl+"/api/videos");
  }

  likeVideo(videoId: string): Observable<VideoDto> {
    return this.httpClient.post<VideoDto>(this.backendUrl+"/api/videos/"+videoId+"/like", null);
  }
  dislikeVideo(videoId: string): Observable<VideoDto> {
    return this.httpClient.post<VideoDto>(this.backendUrl+"/api/videos/"+videoId+"/dislike", null);
  }
}
