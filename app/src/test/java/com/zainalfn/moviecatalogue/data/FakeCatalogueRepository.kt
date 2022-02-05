package com.zainalfn.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.zainalfn.moviecatalogue.data.source.local.LocalDataSource
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueDetailEntity
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueEntity
import com.zainalfn.moviecatalogue.data.source.local.entity.TYPE_MOVIE
import com.zainalfn.moviecatalogue.data.source.local.entity.TYPE_TVSHOW
import com.zainalfn.moviecatalogue.data.source.remote.ApiResponse
import com.zainalfn.moviecatalogue.data.source.remote.RemoteDataSource
import com.zainalfn.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.zainalfn.moviecatalogue.data.source.remote.response.TvShowDetailResponse
import com.zainalfn.moviecatalogue.util.AppExecutors
import com.zainalfn.moviecatalogue.util.Resource
import com.zainalfn.moviecatalogue.util.toGenreString

class FakeCatalogueRepository(private val remoteDataSource: RemoteDataSource,
                              private val localDataSource: LocalDataSource,
                              private val appExecutors: AppExecutors
) : CatalogueDataSource {

    override fun getMovies(): LiveData<Resource<PagedList<CatalogueEntity>>> {
        return object :
            NetworkBoundResource<PagedList<CatalogueEntity>, ArrayList<MovieDetailResponse>>(
                appExecutors
            ) {
            override fun loadFromDb(): LiveData<PagedList<CatalogueEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<CatalogueEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<ArrayList<MovieDetailResponse>>> {
                return remoteDataSource.getMovies()
            }

            override fun saveCallResult(data: ArrayList<MovieDetailResponse>) {
                val movieList = ArrayList<CatalogueEntity>()
                for (response in data) {
                    with(response) {
                        val movie = CatalogueEntity(
                            id,
                            title,
                            voteAverage,
                            posterPath,
                            overview,
                            releaseDate,
                            TYPE_MOVIE
                        )
                        movieList.add(movie)
                    }
                }
                localDataSource.insertCatalogue(movieList)
            }

        }.asLiveData()
    }


    override fun getDetailMovie(movieId: String): LiveData<Resource<CatalogueDetailEntity>> {
        val liveData = MutableLiveData<Resource<CatalogueDetailEntity>>()
        liveData.postValue(Resource.loading())
        remoteDataSource.getDetailMovie(object : RemoteDataSource.LoadDetailMovieCallback {
            override fun onDetailMovieLoaded(movieDetail: MovieDetailResponse?) {
                if (movieDetail != null) {
                    with(movieDetail) {
                        val listGenres = ArrayList<String>()

                        genres?.forEach { genre ->
                            genre.name?.let { listGenres.add(it) }
                        }

                        val detailMovie = CatalogueDetailEntity(
                            id,
                            title,
                            overview,
                            genres.toGenreString(),
                            voteAverage,
                            posterPath,
                            releaseDate
                        )
                        liveData.postValue(Resource.success(detailMovie))
                    }
                } else {
                    liveData.postValue(Resource.error("Data detail not available"))
                }
            }

            override fun onFailed(error: String?) {
                liveData.postValue(Resource.error(error))
            }
        }, movieId)
        return liveData
    }

    override fun getTvShows(): LiveData<Resource<PagedList<CatalogueEntity>>> {
        return object :
            NetworkBoundResource<PagedList<CatalogueEntity>, ArrayList<TvShowDetailResponse>>(
                appExecutors
            ) {
            override fun loadFromDb(): LiveData<PagedList<CatalogueEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<CatalogueEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<ArrayList<TvShowDetailResponse>>> =
                remoteDataSource.getTvShows()

            override fun saveCallResult(data: ArrayList<TvShowDetailResponse>) {
                val tvList = ArrayList<CatalogueEntity>()
                for (response in data) {
                    with(response) {
                        val tvShow = CatalogueEntity(
                            id,
                            name,
                            voteAverage,
                            posterPath,
                            overview,
                            first_air_date,
                            TYPE_TVSHOW
                        )
                        tvList.add(tvShow)
                    }
                }
                localDataSource.insertCatalogue(tvList)

            }

        }.asLiveData()
    }

    override fun getDetailTvShow(tvShowId: String): LiveData<Resource<CatalogueDetailEntity>> {
        val movieDetailResult = MutableLiveData<Resource<CatalogueDetailEntity>>()
        movieDetailResult.postValue(Resource.loading())
        remoteDataSource.getDetailTvShow(object : RemoteDataSource.LoadDetailTvShowCallback {
            override fun onDetailTvShowLoaded(tvShowDetail: TvShowDetailResponse?) {
                if (tvShowDetail != null) {
                    with(tvShowDetail) {
                        val listGenres = ArrayList<String>()

                        genres?.forEach { genre ->
                            genre.name?.let { listGenres.add(it) }
                        }

                        val detailMovie = CatalogueDetailEntity(
                            id,
                            name,
                            overview,
                            genres.toGenreString(),
                            voteAverage,
                            posterPath,
                            first_air_date
                        )
                        movieDetailResult.postValue(
                            Resource.success(detailMovie)
                        )
                    }
                } else {
                    movieDetailResult.postValue(Resource.error("Data detail not available"))
                }
            }

            override fun onFailed(error: String?) {
                movieDetailResult.postValue(
                    Resource.error(error)
                )
            }
        }, tvShowId)
        return movieDetailResult
    }

    override fun getFavMovies(): LiveData<PagedList<CatalogueDetailEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavMovies(), config).build()
    }

    override fun getFavTvShows(): LiveData<PagedList<CatalogueDetailEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavTvShows(), config).build()
    }

    override fun getDetailFavorite(id: Int): LiveData<CatalogueDetailEntity?> {
        return localDataSource.getFavoriteById(id)
    }

    override fun addMovieToFavorite(entity: CatalogueDetailEntity) {
        appExecutors.diskIO().execute {
            localDataSource.setMovieFavorite(entity)
        }
    }

    override fun addTvShowToFavorite(entity: CatalogueDetailEntity) {
        appExecutors.diskIO().execute {
            localDataSource.setTvShowFavorite(entity)
        }
    }

    override fun removeFromFavorite(id: Int) {
        appExecutors.diskIO().execute {
            localDataSource.removeFavorite(id)
        }
    }
}