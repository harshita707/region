package com.application.region.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CountryDBitem.class}, version = 1, exportSchema = true)
public abstract class AppDataBase extends RoomDatabase {

    public abstract AppDao dao();
}
