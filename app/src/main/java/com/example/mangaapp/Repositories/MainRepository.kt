package com.example.mangaapp.Repositories


import com.example.mangaapp.Database.Dao.WebToonDao
import com.example.mangaapp.Database.Models.WebToonModel
import javax.inject.Inject

class MainRepository @Inject constructor(private val webToonDao: WebToonDao) {


    suspend fun getToons():List<WebToonModel>{
        val toons = webToonDao.getAllToons()
        if (toons.isEmpty()){
            val webToonList = seedHomePage()
            webToonDao.addToons(webToonList)
            return webToonList
        }else{
            return toons
        }
    }

    suspend fun updateRate(webToonModel: WebToonModel){
        webToonDao.updateDao(webToonModel)
    }

    private fun seedHomePage():List<WebToonModel>{
        val list = listOf(
            WebToonModel(1,"Solo Leveling" ,
                "https://animemangatoon.com/wp-content/uploads/2024/06/Screenshot-2024-10-01-090334-750x375.webp",
                "Chugong",0f,
                "One of the best action fantasy manhwa and the most talked about adaptation in recent times is undoubtedly Solo Leveling. It is set in a world where humans have discovered supernatural skills, while our protagonist, Sung Jin-Woo, is a nobody with his E-Rank hunting skills. Things will take an interesting turn for him when he becomes the sole survivor of a dungeon raid. Awakened with strange new powers, Sung Jin-Woo will level up from being the weakest Hunter and eventually become the most powerful entity in the universe."
                ),
            WebToonModel(2,
                "Tower of God",
                "https://animemangatoon.com/wp-content/uploads/2024/06/tower-of-god.webp",
                "SIU (Lee Jong Hui)",
                0f,
                "Even if you are not a manhwa fan, you must have heard of Tower of God. This action fantasy manhwa became especially popular after its anime adaptation. Tower of God focuses on Twenty-Fifth Bam, the young protagonist of the manhwa, who is determined to climb a mysterious Tower to find his friend Rachel. It is to be noted that the titular tower has different floors, and each floor has different obstacles. His quest is not going to be an easy one, and whether or not he will be able to meet his friend remains to be seen. Tower of God Season 2 has also been recently released."
            ),
            WebToonModel(
                3,
                "Hardcore Leveling Warrior",
                "https://animemangatoon.com/wp-content/uploads/2024/06/hard-levelign-warrior-750x375.webp",
                "Sehoon Kim",
                0f,
                "Gong Won-Ho is the top player of Lucid Adventure because he uses his alias, Hardcore Leveling Warrior, to stay on top. However, one day, the unimaginable happens – he gets defeated, and now he has to get back on the top from the bottom. The most fascinating aspect of this action fantasy manhwa is how he climbed his way to the top in the first place."
            ),
            WebToonModel(
                4,
                "Noblesse",
                "https://animemangatoon.com/wp-content/uploads/2024/06/noblesse-750x375.webp",
                "Jeho Son",
                0f,
                "After being in a slumber for over 800 years, Cadis Etrama Di Raizel, aka Rai, wakes up in an unfamiliar modern world. Fortunately, he meets his loyal servant Frankenstein, who is now the owner of a high school. After attending the school, Rai tries to live an ordinary life, concealing his true identity; however, that won’t happen for long. This supernatural action fantasy manhwa will keep you engaged with its beautiful illustration and unique narrative till the end. It also has its anime adaptation."
            ), WebToonModel(
                5,
                "The God of High School",
                "https://animemangatoon.com/wp-content/uploads/2024/06/Screenshot-2024-10-01-000548-750x375.webp",
                "Yongje Park",
                0f,
                "The God of High School is one of the best action fantasy manhwa that has been adapted into an anime. Jin Mori, the protagonist of the story, takes part in a suspicious tournament, the prize of which is whatever the winner wants. Like Twenty-Fifth Baam, Mori seems like an ordinary individual, but as he confronts challenges, he understands more about the three realms and his powers."
            )

        )
        return list
    }




}