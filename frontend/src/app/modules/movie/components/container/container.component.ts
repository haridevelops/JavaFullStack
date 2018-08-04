import { Component, OnInit, Input } from '@angular/core';
import { Movie } from '../../movie';

@Component({
  selector: 'movie-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {
  @Input() movies: Array<Movie>;

  constructor() {}

  ngOnInit() {}

}
