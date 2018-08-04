import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatInputModule } from '@angular/material/input';

import { MovieService } from './movie.service';
import { ContainerComponent } from './components/container/container.component';
import { MovieRouterModule } from './movie-router.module';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { MovieDialogComponent } from './components/movie-dialog/movie-dialog.component';
import { FormsModule } from '@angular/forms';
import { SearchComponent } from './components/search/search.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptor.service';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    MovieRouterModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    MatInputModule,
    FormsModule
  ],
  declarations: [
    ThumbnailComponent,
    ContainerComponent,
    TmdbContainerComponent,
    WatchlistComponent,
    MovieDialogComponent,
    SearchComponent,    
  ],
  exports: [
    MovieRouterModule,
    ThumbnailComponent,
    ContainerComponent,
    WatchlistComponent,
    MovieDialogComponent,
    SearchComponent
  ],
  providers: [MovieService, {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  }],
  entryComponents: [MovieDialogComponent]
})
export class MovieModule { }
