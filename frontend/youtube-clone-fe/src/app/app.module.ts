import {MatChipsModule} from '@angular/material/chips';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UploadVideoComponent } from './components/features/upload-video/upload-video.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NgxFileDropModule } from 'ngx-file-drop';
import { HeaderComponent } from './components/partials/header/header.component';
import { AddVideoDetailsComponent } from './components/features/add-video-details/add-video-details.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatOptionModule } from '@angular/material/core';
import {MatMenuModule} from '@angular/material/menu';

import {VgCoreModule} from '@videogular/ngx-videogular/core';
import {VgControlsModule} from '@videogular/ngx-videogular/controls';
import {VgBufferingModule} from '@videogular/ngx-videogular/buffering';
import {VgOverlayPlayModule} from '@videogular/ngx-videogular/overlay-play';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { VideoPlayerComponent } from './components/features/video-player/video-player.component';
import { AuthConfigModule } from './components/auth/config/auth-config.module';
import { AuthInterceptor } from 'angular-auth-oidc-client';
import { VideoDetailComponent } from './components/features/video-detail/video-detail.component';
import { HomeComponent } from './components/home/home.component';
import { HistoryComponent } from './components/features/history/history.component';
import { SubscriptionsComponent } from './components/features/subscriptions/subscriptions.component';
import { LikedVideosComponent } from './components/features/liked-videos/liked-videos.component';
import { SidebarComponent } from './components/partials/sidebar/sidebar.component';

//sidebar
import {MatSidenavModule } from '@angular/material/sidenav';
import {MatListModule } from '@angular/material/list';
import { FeaturedComponent } from './components/features/featured/featured.component';
import { VideoCardComponent } from './components/features/video-card/video-card.component';
import {MatCardModule} from '@angular/material/card';
import { VideoDescriptionComponent } from './components/features/video-description/video-description.component';
import { CommentsComponent } from './components/features/comments/comments.component';
import { SuggestionBarComponent } from './components/features/suggestion-bar/suggestion-bar.component';
import { VideoTagListComponent } from './components/features/video-tag-list/video-tag-list.component';


@NgModule({
  declarations: [
    AppComponent,
    UploadVideoComponent,
    HeaderComponent,
    AddVideoDetailsComponent,
    VideoPlayerComponent,
    VideoDetailComponent,
    HomeComponent,
    HistoryComponent,
    SubscriptionsComponent,
    LikedVideosComponent,
    SidebarComponent,
    FeaturedComponent,
    VideoCardComponent,
    VideoDescriptionComponent,
    CommentsComponent,
    SuggestionBarComponent,
    VideoTagListComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    NgxFileDropModule,
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
    FlexLayoutModule,
    MatFormFieldModule,
    MatSelectModule,
    MatOptionModule,
    MatInputModule,
    ReactiveFormsModule,
    MatChipsModule,
    MatSnackBarModule,

    VgCoreModule,
    VgControlsModule,
    VgOverlayPlayModule,
    VgBufferingModule,
    AuthConfigModule,

    //sidebar
    MatSidenavModule,
    MatListModule,

    //video-card
    MatCardModule,

    //header
    MatMenuModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
