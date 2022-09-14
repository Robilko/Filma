package com.example.filma

const val BASE_URL = "https://api.kinopoisk.dev/"
const val API_KEY = "HCVDFTQ-0FN4AF3-QKAGXJ5-9V0KGMX"
const val MOVIE_BY_ID = "movie?field=id"
const val SEARCH_BY_NAME_LIST = "movie?field=name&sortField[]=votes.kp&sortType[]=-1"
const val NEW_RELEASES_LIST = "movie?field=year&search=2022&sortField[]=year&sortType[]=-1&sortField[]=votes.kp&sortType[]=-1"
const val TAG = "TAG"
const val MOVIE_ITEM_ID = "Movie item id"
const val PERSON_PROFESSION_DIRECTOR = "director"
const val PERSON_PROFESSION_ACTOR = "actor"
const val MOVIES_ON_PAGE_LIMIT = 30
const val STARTING_PAGE_KEY = 1