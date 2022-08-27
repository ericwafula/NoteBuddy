package tech.ericwathome.notebuddy.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.ericwathome.notebuddy.data.local.NoteBuddyDatabase
import tech.ericwathome.notebuddy.data.local.NoteDao
import tech.ericwathome.notebuddy.data.remote.NoteBudyApiService
import tech.ericwathome.notebuddy.util.BASE_URL
import tech.ericwathome.notebuddy.util.DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideNoteBuddyApiService(retrofit: Retrofit): NoteBudyApiService = retrofit.create(NoteBudyApiService::class.java)

    @Provides
    @Singleton
    fun provideNoteBuddyDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        NoteBuddyDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideNoteDao(database: NoteBuddyDatabase) = database.noteDao()
}