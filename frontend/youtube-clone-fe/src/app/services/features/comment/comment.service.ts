import { CommentDto } from './../../../dtos/CommentDto';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  
  private backendUrl = "http://localhost:8080";

  constructor(private httpClient: HttpClient) { }

  postComment(commentDto: CommentDto, videoId:string): Observable<any>{
    return this.httpClient.post<any>(this.backendUrl+"/api/videos/" + videoId + "/comment",commentDto);
  }

  getAllComments(videoId : string): Observable<Array<CommentDto>> {
    return this.httpClient.get<CommentDto[]>(this.backendUrl+ "/api/videos/" + videoId + "/all-comments");
  }
}
