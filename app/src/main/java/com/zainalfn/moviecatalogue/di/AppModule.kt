package com.zainalfn.moviecatalogue.di

import com.zainalfn.core.domain.usecase.CatalogueInteractor
import com.zainalfn.core.domain.usecase.CatalogueUseCase
import com.zainalfn.moviecatalogue.ui.detail.DetailViewModel
import com.zainalfn.moviecatalogue.ui.discover.movie.MovieViewModel
import com.zainalfn.moviecatalogue.ui.discover.tvshow.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<CatalogueUseCase> { CatalogueInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}