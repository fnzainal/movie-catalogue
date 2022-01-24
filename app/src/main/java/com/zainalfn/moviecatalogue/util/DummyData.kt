package com.zainalfn.moviecatalogue.util

import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueDetailEntity
import com.zainalfn.moviecatalogue.data.source.local.entity.CatalogueEntity
import com.zainalfn.moviecatalogue.data.source.remote.response.Genres
import com.zainalfn.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.zainalfn.moviecatalogue.data.source.remote.response.TvShowDetailResponse

/**
 * Created by zainal on 1/4/22 - 7:58 AM
 */
object DummyData {
    fun getMovies(): ArrayList<CatalogueEntity> {
        val listMovies = ArrayList<CatalogueEntity>()

        listMovies.add(
            CatalogueEntity(
                634649,
                "Spider-Man: No Way Home",
                8.4,
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "2021-12-15"
            )
        )

        listMovies.add(
            CatalogueEntity(
                524434,
                "Eternals",
                7.2,
                "/b6qUu00iIIkXX13szFy7d0CyNcg.jpg",
                "The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
                "2021-11-03"
            )
        )

        listMovies.add(
            CatalogueEntity(
                568124,
                "Encanto",
                7.8,
                "/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg",
                "The tale of an extraordinary family, the Madrigals, who live hidden in the mountains of Colombia, in a magical house, in a vibrant town, in a wondrous, charmed place called an Encanto. The magic of the Encanto has blessed every child in the family with a unique gift from super strength to the power to heal—every child except one, Mirabel. But when she discovers that the magic surrounding the Encanto is in danger, Mirabel decides that she, the only ordinary Madrigal, might just be her exceptional family's last hope.",
                "2021-11-24"
            )
        )

        return listMovies
    }


    fun getTvShow(): ArrayList<CatalogueEntity> {
        val listTvShows = ArrayList<CatalogueEntity>()

        listTvShows.add(
            CatalogueEntity(
                77169,
                "Cobra Kai",
                8.2,
                "/6POBWybSBDBKjSs1VAQcnQC1qyt.jpg",
                "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                "2018-05-02"
            )
        )

        listTvShows.add(
            CatalogueEntity(
                60735,
                "The Flash",
                7.8,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "2014-10-07"
            )
        )

        return listTvShows
    }

    fun getDetailMovie(): ArrayList<CatalogueDetailEntity> {
        val listMovieDetails = ArrayList<CatalogueDetailEntity>()

        listMovieDetails.add(
            CatalogueDetailEntity(
                634649,
                "Spider-Man: No Way Home",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                arrayListOf(
                    Genres(28, "Action"),
                    Genres(12, "Adventure"),
                    Genres(878, "Science Fiction")
                ).toGenreString(),
                8.5,
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "2021-12-15",
            )
        )

        listMovieDetails.add(
            CatalogueDetailEntity(
                524434,
                "Eternals",
                "The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
                arrayListOf(
                    Genres(28, "Action"),
                    Genres(12, "Adventure"),
                    Genres(14, "Fantasy"),
                    Genres(878, "Science Fiction")
                ).toGenreString(),
                7.2,
                "/b6qUu00iIIkXX13szFy7d0CyNcg.jpg",
                "2021-11-03"

            )
        )

        listMovieDetails.add(
            CatalogueDetailEntity(
                568124,
                "Encanto",
                "The tale of an extraordinary family, the Madrigals, who live hidden in the mountains of Colombia, in a magical house, in a vibrant town, in a wondrous, charmed place called an Encanto. The magic of the Encanto has blessed every child in the family with a unique gift from super strength to the power to heal—every child except one, Mirabel. But when she discovers that the magic surrounding the Encanto is in danger, Mirabel decides that she, the only ordinary Madrigal, might just be her exceptional family's last hope.",
                arrayListOf(
                    Genres(14, "Fantasy"),
                    Genres(16, "Animation"),
                    Genres(35, "Comedy"),
                    Genres(10751, "Family")
                ).toGenreString(),
                7.8,
                "/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg",
                "2021-11-24"
            )
        )

        return listMovieDetails
    }

    fun getDetailTvShow(): ArrayList<CatalogueDetailEntity> {
        val listTvShowDetails = ArrayList<CatalogueDetailEntity>()

        listTvShowDetails.add(
            CatalogueDetailEntity(
                77169,
                "Cobra Kai",
                "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                arrayListOf(
                    Genres(10759, "Action & Adventure"),
                    Genres(18, "Drama")
                ).toGenreString(),
                8.2,
                "/6POBWybSBDBKjSs1VAQcnQC1qyt.jpg",
                "2018-05-02"
            )
        )

        listTvShowDetails.add(
            CatalogueDetailEntity(
                60735,
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                arrayListOf(
                    Genres(18, "Drama"),
                    Genres(10765, "Sci-Fi & Fantasy")
                ).toGenreString(),
                7.8,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "2014-10-07"
            )
        )
        return listTvShowDetails
    }

    fun getRemoteDetailMovie(): ArrayList<MovieDetailResponse> {
        val listMovieDetails = ArrayList<MovieDetailResponse>()

        listMovieDetails.add(
            MovieDetailResponse(
                634649,
                "Spider-Man: No Way Home",
                8.4,
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "2021-12-15",
                arrayListOf(
                    Genres(28, "Action"),
                    Genres(12, "Adventure"),
                    Genres(878, "Science Fiction")
                ),
            )
        )

        listMovieDetails.add(
            MovieDetailResponse(
                524434,
                "Eternals",
                7.2,
                "/b6qUu00iIIkXX13szFy7d0CyNcg.jpg",
                "The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
                "2021-11-03",
                arrayListOf(
                    Genres(28, "Action"),
                    Genres(12, "Adventure"),
                    Genres(14, "Fantasy"),
                    Genres(878, "Science Fiction")
                )
            )
        )

        listMovieDetails.add(
            MovieDetailResponse(
                568124,
                "Encanto",
                7.8,
                "/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg",
                "The tale of an extraordinary family, the Madrigals, who live hidden in the mountains of Colombia, in a magical house, in a vibrant town, in a wondrous, charmed place called an Encanto. The magic of the Encanto has blessed every child in the family with a unique gift from super strength to the power to heal—every child except one, Mirabel. But when she discovers that the magic surrounding the Encanto is in danger, Mirabel decides that she, the only ordinary Madrigal, might just be her exceptional family's last hope.",
                "2021-11-24",
                arrayListOf(
                    Genres(14, "Fantasy"),
                    Genres(16, "Animation"),
                    Genres(35, "Comedy"),
                    Genres(10751, "Family")
                )
            )
        )

        return listMovieDetails
    }

    fun getRemoteDetailTvShow(): ArrayList<TvShowDetailResponse> {
        val listTvShowDetails = ArrayList<TvShowDetailResponse>()

        listTvShowDetails.add(
            TvShowDetailResponse(
                77169,
                "Cobra Kai",
                8.2,
                "/6POBWybSBDBKjSs1VAQcnQC1qyt.jpg",
                "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                arrayListOf(
                    Genres(10759, "Action & Adventure"),
                    Genres(18, "Drama")
                ),
                "2018-05-02"
            )
        )

        listTvShowDetails.add(
            TvShowDetailResponse(
                60735,
                "The Flash",
                7.8,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                arrayListOf(
                    Genres(18, "Drama"),
                    Genres(10765, "Sci-Fi & Fantasy")
                ),
                "2014-10-07"
            )
        )

        return listTvShowDetails
    }

    fun getRemoteMovies(): ArrayList<MovieDetailResponse> {
        val listMovies = ArrayList<MovieDetailResponse>()

        listMovies.add(
            MovieDetailResponse(
                634649,
                "Spider-Man: No Way Home",
                8.4,
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "2021-12-15"
            )
        )

        listMovies.add(
            MovieDetailResponse(
                524434,
                "Eternals",
                7.2,
                "/b6qUu00iIIkXX13szFy7d0CyNcg.jpg",
                "The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
                "2021-11-03"
            )
        )

        listMovies.add(
            MovieDetailResponse(
                568124,
                "Encanto",
                7.8,
                "/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg",
                "The tale of an extraordinary family, the Madrigals, who live hidden in the mountains of Colombia, in a magical house, in a vibrant town, in a wondrous, charmed place called an Encanto. The magic of the Encanto has blessed every child in the family with a unique gift from super strength to the power to heal—every child except one, Mirabel. But when she discovers that the magic surrounding the Encanto is in danger, Mirabel decides that she, the only ordinary Madrigal, might just be her exceptional family's last hope.",
                "2021-11-24"
            )
        )

        return listMovies
    }


    fun getRemoteTvShow(): ArrayList<TvShowDetailResponse> {
        val listTvShows = ArrayList<TvShowDetailResponse>()

        listTvShows.add(
            TvShowDetailResponse(
                77169,
                "Cobra Kai",
                8.2,
                "/6POBWybSBDBKjSs1VAQcnQC1qyt.jpg",
                "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                first_air_date = "2018-05-02"
            )
        )

        listTvShows.add(
            TvShowDetailResponse(
                60735,
                "The Flash",
                7.8,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                first_air_date = "2014-10-07"
            )
        )

        return listTvShows
    }

}