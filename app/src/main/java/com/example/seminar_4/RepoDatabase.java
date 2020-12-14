package com.example.seminar_4;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {User.class}, version = 1)
public abstract class RepoDatabase extends RoomDatabase {

    private static volatile RepoDatabase INSTANCE;

    public abstract UserDao userDao();

    public static RepoDatabase getInstance(Context context) {
        if (INSTANCE == null)
        {
            synchronized (RepoDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(), RepoDatabase.class, "Sample1.db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}

