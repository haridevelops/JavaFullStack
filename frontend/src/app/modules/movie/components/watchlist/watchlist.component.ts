import { Component, OnInit } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'movie-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {
  movies: Array<Movie>;

  useWatchlistAPI: boolean = true;

  constructor(private movieService: MovieService,
    private route: ActivatedRoute, private matSnackBar: MatSnackBar) {
      this.movies = [];      
  }

  ngOnInit() {
    this.movieService.getMovieFromWatchlist()
      .subscribe((movieList) => {
        console.log(movieList);
        this.movies.push(...movieList);
      })
  }

  deleteMovieFromWL(movie) {
    let message = `${movie.title} deleted from your watchlist`;
    
    for(let i=0; i<this.movies.length; i++) {
      if(this.movies[i].title === movie.title) {
        this.movies.splice(i, 1);
      }
    }

    this.movieService.deleteFromWatchlist(movie)
      .subscribe((movie) => {
        this.matSnackBar.open(message, '', {
          duration: 2000,
          announcementMessage: message
        });
      });
  }

  updateMovieFromWL(movie) {
    this.movieService.updateWatchlist(movie)
    .subscribe((movie) => {
      this.matSnackBar.open('Update Success!', '', {
        duration: 2000,
      });
    })
  }

}
