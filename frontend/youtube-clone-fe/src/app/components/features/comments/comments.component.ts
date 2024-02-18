import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommentDto } from 'src/app/dtos/CommentDto';
import { CommentService } from 'src/app/services/features/comment/comment.service';
import { UserService } from 'src/app/services/features/user/user.service';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  @Input()
  videoId!:string;

  allComments! : CommentDto[];
  commentsForm: FormGroup;

  constructor(private userService: UserService, private commentService: CommentService,
              private matSnackBar: MatSnackBar) { 
    this.commentsForm = new FormGroup({
      comment: new FormControl('comment'),
    });
    
  }

  ngOnInit(): void {
    this.getAllComments();
   
  }
  postComment(){
    const content = this.commentsForm.get('comment')?.value;

    const commentDto : CommentDto = {
      "content" : content,
      "authorId" : this.userService.getCurrentUser(),
      "likeCount" : 0,
      "dislikeCount" : 0
    }
    this.commentService.postComment(commentDto, this.videoId).subscribe( () => {
        this.matSnackBar.open("Comment posted Successfully!", "OK");
        this.commentsForm.get('comment')?.reset();
        this.getAllComments();
    });
   
  }

  getAllComments(){
    this.commentService.getAllComments(this.videoId).subscribe(res => {
      this.allComments = res;
      console.log(res);
    });
  }
}
