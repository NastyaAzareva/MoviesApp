package com.example.movies_v8_1.data

import com.example.movies_v8_1.R

object MoviesData {
    val list = listOf<Movie>(
        Movie(
            "Реальные упыри",
            "История жизни Виаго, Дикона и Владислава — трёх соседей и по совместительству бессмертных вампиров, которые всего лишь пытаются выжить в современном мире, где есть арендная плата, фейсконтроль в ночных клубах, губительный солнечный свет и другие неприятности.",
            R.drawable.what_we_do_in_the_shadows
        ),
        Movie(
            "Адвокат дьявола",
            "В Нью-Йорк по приглашению главы крупного юридического концерна прибывает Кевин Ломакс, молодой адвокат. До этого он был известен тем, что защищал исключительно негодяев и притом не проиграл ни одного процесса. На новом месте работы он вполне счастлив, он живет в роскошной квартире с любящей женой, его окружают интересные люди.",
            R.drawable.the_devils_advocate
        ),
        Movie(
            "Перелом",
            "Ассистент окружного прокурора оказывается втянут в хитроумную игру в «кошки-мышки».",
            R.drawable.fracture
        ),
        Movie(
            "Счастливые люди",
            "Документальный фильм о трудах и днях охотников и их семей из посёлка Бахта в среднем течении реки Енисей в течение целого года.",
            R.drawable.schastlivie_ludi
        ),
        Movie(
            "Как Витька Чеснок вез Леху Штыря в дом инвалидов",
            "27-летний Витька Чеснок - парень с детдомовским прошлым, который мечтает сбежать от мешающих ему жить жены и сына. Недолюбленный в детстве и очерствевший сердцем парень встречает отца-уголовника, а теперь ещё и калеку. Витька решает отвезти отца в дом инвалидов, не подозревая, какие приключения и опасности ждут их по дороге.",
            R.drawable.kak_vitka_chesnok
        ),
        Movie(
            "Жизнь в деталях",
            "Семейная комедия, которая состоит из отдельных историй разных членов семьи.",
            R.drawable.lifi_in_pieces
        ),
        Movie(
            "Детство Шелдона",
            "История о непростом детстве вундеркинда Шелдона Купера. Родители юного гения не разделяют увлечения сына наукой: его мать очень религиозна, а отец, бывший футбольный тренер, предпочитает проводить вечера в компании пива и телевизора. Со сверстниками тоже не ладится, ведь Шелдона куда больше детских игрушек интересует, где можно взять обогащённый уран для научных экспериментов.",
            R.drawable.young_sheldon
        ),
        Movie(
            "Зеленая миля	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "Побег из Шоушенка	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "Властелин колец: Возвращение короля	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "Властелин колец: Две крепости	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "Властелин колец: Братство Кольца",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "Форрест Гамп",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "Иван Васильевич меняет профессию	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "Король Лев	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "Интерстеллар	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "Тайна Коко	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "ВАЛЛ·И	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "Карты, деньги, два ствола	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "Криминальное чтиво	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "Матрица	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "Начало	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "Клаус	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie("1+1	", "Описание описание описание описание описание описание описание", R.drawable.poster),
        Movie(
            "Список Шиндлера	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "Унесённые призраками	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "Назад в будущее	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "Операция «Ы» и другие приключения Шурика	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "Джентльмены удачи	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        ),
        Movie(
            "Приключения Шерлока Холмса и доктора Ватсона: Собака Баскервилей	",
            "Описание описание описание описание описание описание описание",
            R.drawable.poster
        )
    )

    fun setButtonPressed(name: String){
        list.filter { it.isButtonPressed }.forEach {
            it.isButtonPressed = false
        }
        list.findLast { it.name == name }?.isButtonPressed = true
    }

    fun getDescriptionByName(name: String): String{
        return list.findLast { it.name == name }?.description ?: ""
    }

    fun getPosterByName(name: String): Int{
        return list.findLast { it.name == name }?.poster ?: 0
    }

    fun getMovieByName(name: String): Movie?{
        return list.findLast { it.name == name }
    }

    fun setFavourite(name: String){
        getMovieByName(name)?.isLiked = true
        //To Do добавлять в избранное копию а не ссылку и настроить подсветку отдельно в следующем ДЗ
        getMovieByName(name)?.let {
            it.isButtonPressed = false
        }
    }

    fun removeFromFavourites(name: String){
        getMovieByName(name)?.isLiked = false
    }

    fun getFavouritesList(): List<Movie>{
        var f: MutableList<Movie> = mutableListOf()
        val set: Set<Movie> = LinkedHashSet(list.filter { it.isLiked })
        f.clear()
        f.addAll(set)

        return f
    }
}

data class Movie(
    val name: String,
    val description: String,
    val poster: Int,
    var isButtonPressed: Boolean = false, //To Do перенести в адаптер в следующем ДЗ
    var isLiked: Boolean =  false,
    var comments: MutableList<String> = mutableListOf()
)