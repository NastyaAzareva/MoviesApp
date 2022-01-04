package com.example.movies_v8_1.di

import android.content.Context
import androidx.room.Room
import com.example.movies_v8_1.common.Constants
import com.example.movies_v8_1.data.db.AppDatabase
import com.example.movies_v8_1.data.remote.Api
import com.example.movies_v8_1.data.repository.MovieRepositoryImp
import com.example.movies_v8_1.domain.repository.MovieRepository
import com.example.movies_v8_1.domain.use_case.favourites.GetFavouritesMoviesUseCase
import com.example.movies_v8_1.domain.use_case.get_movie.GetMovieUseCase
import com.example.movies_v8_1.domain.use_case.get_movies.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHTTPClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(client: OkHttpClient): Api {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: Api, db: AppDatabase): MovieRepository {
        return MovieRepositoryImp(api, db)
    }

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "movie-database").build()
    }

    @Provides
    @Singleton
    fun providesGetMovieUseCase(movieRepository: MovieRepository): GetMovieUseCase {
        return GetMovieUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun providesGetMoviesUseCase(movieRepository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun providesGetFavouritesMoviesUseCase(movieRepository: MovieRepository): GetFavouritesMoviesUseCase {
        return GetFavouritesMoviesUseCase(movieRepository)
    }

}