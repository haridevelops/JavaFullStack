import { Component, OnInit } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'movie-tmdb-container',
  templateUrl: './tmdb-container.component.html',
  styleUrls: ['./tmdb-container.component.css']
})
export class TmdbContainerComponent implements OnInit {
  movies: Array<Movie>;
  movieType: string;

  constructor(private movieService: MovieService,
    private route: ActivatedRoute,
  private snack: MatSnackBar) {
      this.movies = [];
      this.route.data.subscribe((data) => {
        this.movieType = data.movieType;
      });
  }


  ngOnInit() {
    this.movieService.getMovies(this.movieType)
      .subscribe((moviesList) => {
        console.log(moviesList)
        this.movies.push(...moviesList);
    });
  }

  addMovie(movie) {
    console.log(movie);
    this.movieService.addMoviesToWatchlist(movie)
      .subscribe((movie) => {
        this.snack.open('Movie added to Watchlist...', '', {
          duration: 2000
        });
    });
  }
}
