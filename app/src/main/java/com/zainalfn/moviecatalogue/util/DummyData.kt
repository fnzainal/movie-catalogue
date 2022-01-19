package com.zainalfn.moviecatalogue.util

import com.zainalfn.moviecatalogue.R
import com.zainalfn.moviecatalogue.data.source.local.CatalogueData

/**
 * Created by zainal on 1/4/22 - 7:58 AM
 */
object DummyData {
    fun getMovie(): ArrayList<CatalogueData> {
        val listMovies = ArrayList<CatalogueData>()

        listMovies.add(
            CatalogueData(
                0,
                "Alita: Battle Angle",
                "When Alita awakens with no memory of who she is in a future world she does " +
                        "not recognize, she is taken in by Ido, a compassionate doctor who realizes " +
                        "that somewhere in this abandoned cyborg shell is the heart and soul of a " +
                        "young woman with an extraordinary past.",
                "Action, Science Fiction, Adventure",
                "71",
                R.drawable.poster_alita,
                2019
            )
        )
        listMovies.add(
            CatalogueData(
                1,
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now " +
                        "an underwater kingdom ruled by the power-hungry King Orm. With a vast " +
                        "army at his disposal, Orm plans to conquer the remaining oceanic people " +
                        "and then the surface world. Standing in his way is Arthur Curry, " +
                        "Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "Action, Adventure, Fantasy",
                "69",
                R.drawable.poster_aquaman,
                2018
            )
        )
        listMovies.add(
            CatalogueData(
                2,
                "Cold Pursuit",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after " +
                        "his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he " +
                        "holds responsible for the killing, eliminating Viking's associates one by one. " +
                        "As Nels draws closer to Viking, his actions bring even more unexpected and " +
                        "violent consequences, as he proves that revenge is all in the execution.",
                "Action, Crime, Thriller",
                "57",
                R.drawable.poster_cold_persuit,
                2019
            )
        )

        listMovies.add(
            CatalogueData(
                3,
                "T-34",
                "In 1944, a courageous group of Russian soldiers managed to escape from " +
                        "German captivity in a half-destroyed legendary T-34 tank. Those were the " +
                        "times of unforgettable bravery, fierce fighting, unbreakable love, and " +
                        "legendary miracles.",
                "War, Action, Drama, History",
                "70",
                R.drawable.poster_t34,
                2018
            )
        )

        listMovies.add(
            CatalogueData(
                4,
                "Glass",
                "In a series of escalating encounters, former security guard David Dunn uses " +
                        "his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who" +
                        " has twenty-four personalities. Meanwhile, the shadowy presence of Elijah " +
                        "Price emerges as an orchestrator who holds secrets critical to both men.",
                "Thriller, Drama, Science Fiction",
                "67",
                R.drawable.poster_glass,
                2019
            )
        )

        listMovies.add(
            CatalogueData(
                5,
                "How to Train Your Dragon: The Hidden World",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ " +
                        "discovery of an untamed, elusive mate draws the Night Fury away. When danger" +
                        " mounts at home and Hiccup’s reign as village chief is tested, both dragon " +
                        "and rider must make impossible decisions to save their kind.",
                "Animation, Family, Adventure",
                "78",
                R.drawable.poster_how_to_train,
                2019
            )
        )

        listMovies.add(
            CatalogueData(
                6,
                "Avengers: Infinity War",
                "As the Avengers and their allies have continued to protect the world from " +
                        "threats too large for any one hero to handle, a new danger has emerged from" +
                        " the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is " +
                        "to collect all six Infinity Stones, artifacts of unimaginable power, and " +
                        "use them to inflict his twisted will on all of reality. Everything the " +
                        "Avengers have fought for has led up to this moment - the fate of Earth and " +
                        "existence itself has never been more uncertain.",
                "Adventure, Action, Science Fiction",
                "83",
                R.drawable.poster_infinity_war,
                2018
            )
        )

        listMovies.add(
            CatalogueData(
                7,
                "Overlord",
                "France, June 1944. On the eve of D-Day, some American paratroopers fall " +
                        "behind enemy lines after their aircraft crashes while on a mission to destroy" +
                        " a radio tower in a small village near the beaches of Normandy. After reaching" +
                        " their target, the surviving paratroopers realise that, in addition to fighting " +
                        "the Nazi troops that patrol the village, they also must fight against something else.",
                "Horror, War, Science Fiction",
                "67",
                R.drawable.poster_overlord,
                2018
            )
        )

        listMovies.add(
            CatalogueData(
                8,
                "Ralph Breaks the Internet",
                "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk " +
                        "it all by traveling to the World Wide Web in search of a replacement part " +
                        "to save Vanellopes video game, Sugar Rush. In way over their heads, Ralph " +
                        "and Vanellope rely on the citizens of the internet — the netizens — to help " +
                        "navigate their way, including an entrepreneur named Yesss, who is the head " +
                        "algorithm and the heart and soul of trend-making site BuzzzTube.",
                "Family, Animation, Comedy, Adventure",
                "72",
                R.drawable.poster_ralph,
                2018
            )
        )

        listMovies.add(
            CatalogueData(
                9,
                "Spider-Man: Into the Spider-Verse",
                "Miles Morales is juggling his life between being a high school student and " +
                        "being a spider-man. When Wilson Kingpin Fisk uses a super collider, others" +
                        " from across the Spider-Verse are transported to this dimension.",
                "Action, Adventure, Animation, Science Fiction, Comedy",
                "84",
                R.drawable.poster_spiderman,
                2018
            )
        )

        return listMovies
    }


    fun getTvShow(): ArrayList<CatalogueData> {
        val listTvShow = ArrayList<CatalogueData>()

        listTvShow.add(
            CatalogueData(
                0,
                "Dragon Ball",
                "Long ago in the mountains, a fighting master known as Gohan discovered a " +
                        "strange boy whom he named Goku. Gohan raised him and trained Goku in martial " +
                        "arts until he died. The young and very strong boy was on his own, but easily" +
                        " managed. Then one day, Goku met a teenage girl named Bulma, whose search for" +
                        " the mystical Dragon Balls brought her to Gokus home. Together, they set off" +
                        " to find all seven and to grant her wish.",
                "Comedy, Sci-Fi & Fantasy, Animation, Action & Adventure",
                "80",
                R.drawable.poster_dragon_ball,
                1986
            )
        )

        listTvShow.add(
            CatalogueData(
                1,
                "Doom Patrol",
                "The Doom Patrol’s members each suffered horrible accidents that gave them " +
                        "superhuman abilities — but also left them scarred and disfigured. Traumatized " +
                        "and downtrodden, the team found purpose through The Chief, who brought them " +
                        "together to investigate the weirdest phenomena in existence — and to protect " +
                        "Earth from what they find.",
                "Sci-Fi & Fantasy, Drama",
                "77",
                R.drawable.poster_doom_patrol,
                2019
            )
        )

        listTvShow.add(
            CatalogueData(
                2,
                "Family Guy",
                "Sick, twisted, politically incorrect and Freakin Sweet animated series " +
                        "featuring the adventures of the dysfunctional Griffin family. Bumbling Peter" +
                        " and long-suffering Lois have three kids. Stewie (a brilliant but sadistic " +
                        "baby bent on killing his mother and taking over the world), Meg (the oldest," +
                        " and is the most unpopular girl in town) and Chris (the middle kid, he is not" +
                        " very bright but has a passion for movies). The final member of the family " +
                        "is Brian - a talking dog and much more than a pet, he keeps Stewie in check " +
                        "whilst sipping Martinis and sorting through his own life issues.",
                "Animation, Comedy",
                "68",
                R.drawable.poster_family_guy,
                1999
            )
        )

        listTvShow.add(
            CatalogueData(
                3,
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry " +
                        "Allen is struck by lightning and falls into a coma. Months later he awakens " +
                        "with the power of super speed, granting him the ability to move through Central " +
                        "City like an unseen guardian angel. Though initially excited by his newfound " +
                        "powers, Barry is shocked to discover he is not the only meta-human who " +
                        "was created in the wake of the accelerator explosion -- and not everyone is " +
                        "using their new powers for good. Barry partners with S.T.A.R. Labs and " +
                        "dedicates his life to protect the innocent. For now, only a few close friends " +
                        "and associates know that Barry is literally the fastest man alive, but it " +
                        "wont be long before the world learns what Barry Allen has become...The Flash.",
                "Drama, Sci-Fi & Fantasy",
                "75",
                R.drawable.poster_flash,
                2014
            )
        )

        listTvShow.add(
            CatalogueData(
                4,
                "Marvels Iron Fist",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the " +
                        "power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny",
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                "64",
                R.drawable.poster_iron_fist,
                2017
            )
        )

        listTvShow.add(
            CatalogueData(
                5,
                "Naruto Shippuden",
                "Naruto Shippuuden is the continuation of the original animated TV series " +
                        "Naruto.The story revolves around an older and slightly more matured Uzumaki " +
                        "Naruto and his quest to save his friend Uchiha Sasuke from the grips of the " +
                        "snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally " +
                        "returns to his village of Konoha, and sets about putting his ambitions to " +
                        "work, though it will not be easy, as He has amassed a few (more dangerous) " +
                        "enemies, in the likes of the shinobi organization; Akatsuki.",
                "Animation, Comedy, Drama",
                "87",
                R.drawable.poster_naruto_shipudden,
                2007
            )
        )

        listTvShow.add(
            CatalogueData(
                6,
                "The Simpsons",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                "Family, Animation, Comedy",
                "79",
                R.drawable.poster_the_simpson,
                1989
            )
        )

        listTvShow.add(
            CatalogueData(
                7,
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Action, Adventure, Drama, Science Fiction",
                "67",
                R.drawable.poster_arrow,
                2012
            )
        )

        listTvShow.add(
            CatalogueData(
                8,
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "Crime, Drama, Mystery",
                "86",
                R.drawable.poster_rivervale,
                2017
            )
        )

        listTvShow.add(
            CatalogueData(
                9,
                "The Book of Boba Fett",
                "Legendary bounty hunter Boba Fett and mercenary Fennec Shand must navigate the galaxy’s underworld when they return to the sands of Tatooine to stake their claim on the territory once ruled by Jabba the Hutt and his crime syndicate.",
                "Sci-Fi & Fantasy, Action & Adventure",
                "84",
                R.drawable.poster_starwars_boba,
                2021
            )
        )
        return listTvShow
    }

    fun getDetailMovie(id: Int) = getMovie()[id]
    fun getDetailTvShow(id: Int) = getTvShow()[id]

}