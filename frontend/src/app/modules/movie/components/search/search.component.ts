import { Component, OnInit } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { MatButton } from '@angular/material/button';

@Component({
  selector: 'movie-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  movies: Array<Movie>;
  search: boolean;
  constructor(private movieService: MovieService) { 
    this.movies = [];
    this.search = false;
  }

  ngOnInit() {
  }

  onEnter(searchKey) {
    console.log('search key', searchKey);
    this.movieService.searchMovie(searchKey).subscribe((moviess) => {
      console.log(moviess)
      this.movies = moviess;
      this.search = true;
    });

  }

}
