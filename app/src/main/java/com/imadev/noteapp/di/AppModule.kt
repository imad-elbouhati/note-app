package com.imadev.noteapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import androidx.security.crypto.MasterKeys.AES256_GCM_SPEC
import com.imadev.noteapp.data.local.NotesDatabase
import com.imadev.noteapp.data.remote.BasicAuthInterceptor
import com.imadev.noteapp.data.remote.NoteApi
import com.imadev.noteapp.other.Constants.BASE_URL
import com.imadev.noteapp.other.Constants.DATABASE_NAME
import com.imadev.noteapp.other.Constants.ENCRYPTED_SHARED_PREF_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNotesDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, NotesDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideNoteDao(db: NotesDatabase) = db.noteDao()


    @Singleton
    @Provides
    fun provideBasicAuthInterceptor() = BasicAuthInterceptor()


    @Singleton
    @Provides
    fun provideNoteApi(
        basicAuthInterceptor: BasicAuthInterceptor
    ): NoteApi {
        val client = OkHttpClient.Builder()
            .addInterceptor(basicAuthInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(NoteApi::class.java)
    }


    @Singleton
    @Provides
    fun provideEncryptedSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        val masterKey = MasterKeys.getOrCreate(AES256_GCM_SPEC)

        return EncryptedSharedPreferences.create(
            ENCRYPTED_SHARED_PREF_NAME,
            masterKey,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}
