import {  NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UploadVideoComponent } from './components/features/upload-video/upload-video.component';
import { AddVideoDetailsComponent } from './components/features/add-video-details/add-video-details.component';
import { VideoDetailComponent } from './components/features/video-detail/video-detail.component';
import { HomeComponent } from './components/home/home.component';
import { SubscriptionsComponent } from './components/features/subscriptions/subscriptions.component';
import { HistoryComponent } from './components/features/history/history.component';
import { LikedVideosComponent } from './components/features/liked-videos/liked-videos.component';
import { FeaturedComponent } from './components/features/featured/featured.component';
import { CallbackComponent } from './components/auth/callback/callback.component';

const routes: Routes = [
  {
    path: '', component: HomeComponent,
    children:[
      {
        path: 'featured', component: FeaturedComponent,
      },
      {
        path: 'subscriptions', component: SubscriptionsComponent,
      },
      {
        path: 'history', component: HistoryComponent,
      },
      {
        path: 'liked-videos', component: LikedVideosComponent,
      },
    
    ]
  },
 

  {
    path: 'upload-video', component: UploadVideoComponent,
  },
  {
    path: 'add-video-details/:videoId', component: AddVideoDetailsComponent,
  },
  {
    path: 'video-detail/:videoId', component: VideoDetailComponent,
  },
  {
    path: 'callback', component: CallbackComponent,
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
