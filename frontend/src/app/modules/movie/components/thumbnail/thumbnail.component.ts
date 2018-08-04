import { 
  Component, 
  OnInit,
  Input,
  Output,
  EventEmitter } from '@angular/core';
import { Movie } from '../../movie';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { MovieService } from '../../movie.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { MovieDialogComponent } from '../movie-dialog/movie-dialog.component';

@Component({
  selector: 'movie-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {
  @Input() search: boolean;
  @Input() movies: Array<Movie>;
  @Input() movie: Movie;
  @Input() useWatchlistAPI: boolean;

  @Output() deleteMovie = new EventEmitter();
  @Output() addMovie = new EventEmitter();

  constructor(public dialog: MatDialog,
    private snack: MatSnackBar) {
    }

  ngOnInit() {}

  addMovieToWatchlist() {
    this.addMovie.emit(this.movie);
  }

  UpdateWatchlist(actionType) {
    let dialogRef = this.dialog.open(MovieDialogComponent, {
      width: '250px',
      data: { obj: this.movie, actionType: actionType }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  deleteFromWatchlist() {
    this.deleteMovie.emit(this.movie);
  }
}
