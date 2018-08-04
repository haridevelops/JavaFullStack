import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ContainerComponent } from './components/container/container.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { SearchComponent } from './components/search/search.component';
import { AuthGuardService } from "../../authGuard.service";

const movieRoutes: Routes = [
    {
        path: 'movies',
        children: [
            {
                path: '',
                redirectTo: '/movies/popular',
                pathMatch: 'full',
                canActivate: [AuthGuardService]
            },
            {
                path: 'popular',
                component: TmdbContainerComponent,
                data: {
                    movieType: 'popular'
                },
                canActivate: [AuthGuardService]
            },
            {
                path: 'toprated',
                component: TmdbContainerComponent,
                data: {
                    movieType: 'top_rated'
                },
                canActivate: [AuthGuardService]
            },
            {
                path: 'watchlist',
                component: WatchlistComponent,
                canActivate: [AuthGuardService]
            },
            {
                path: 'search',
                component: SearchComponent,
                canActivate: [AuthGuardService]
            }
        ]
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(movieRoutes)
    ],
    exports: [
        RouterModule
    ]
})

export class MovieRouterModule {}