import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { retry } from 'rxjs/operators/retry';
import { map } from 'rxjs/operators/map';
import { Movie } from './movie';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class MovieService {
  tmdbEndpoint: string;
  imagePrefix: string;
  apiKey: string;
  watchlistEndpoint: string;
  springEndppoint: string;
  searchEndpoint: string;


  constructor(private http: HttpClient) {
    this.apiKey = 'api_key=269ff5015d6a3b3ce62163dd525c8713';
    this.tmdbEndpoint = 'https://api.themoviedb.org/3/movie';
    this.imagePrefix = 'https://image.tmdb.org/t/p/w500';
    this.watchlistEndpoint = 'http://localhost:3000/watchlist';
    this.springEndppoint = 'http://localhost:8082/api/v1/movieservice';
    this.searchEndpoint = 'https://api.themoviedb.org/3/search';
  }

  getMovies(type: string, page: number = 1): Observable<Array<Movie>> {
    const endPoint = `${this.tmdbEndpoint}/${type}?${this.apiKey}&page=${page}`;
    return this.http.get(endPoint)
      .pipe(
        retry(3),
        map(this.pickResultsFromResponse),
        map(this.transformPosterPath.bind(this))
      );
  }

  transformPosterPath(movies): Array<Movie> {
    return movies.map(movie => {
      movie.poster_path = `${this.imagePrefix}${movie.poster_path}`;
      return movie;
    });
  }

  pickResultsFromResponse(response) {
    console.log(response)
    return response['results'];
  }

  addMoviesToWatchlist(movie) {
    console.log(movie);
    return this.http.post(this.springEndppoint+"/movie", movie);
  }

  getMovieFromWatchlist(): Observable<Array<Movie>> {
    return this.http.get<Array<Movie>>(this.springEndppoint+"/movies");
  }

  updateWatchlist(movie) {
    return this.http.put(this.springEndppoint, movie);
  }
  
  deleteFromWatchlist(movie: Movie) {
    return this.http.delete(this.springEndppoint + "/movie/"+movie.id, {responseType: 'text'});
  }

  updateComments(movie) {
    const url = `${this.springEndppoint}/movie/${movie.id}`;
    return this.http.put(url, movie);
  }

  searchMovie(searchString: string): Observable<Array<Movie>> {
    const url = `${this.searchEndpoint}/movie?${this.apiKey}&&language=en-US&page=1&include_adult=false&query=${searchString}`;
    if(searchString.length>0) {
      return this.http.get(url).pipe(
        retry(3),
        map(this.pickResultsFromResponse),
        map(this.transformPosterPath.bind(this))
      );
    }
  }
}
