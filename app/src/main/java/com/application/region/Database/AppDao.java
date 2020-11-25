package com.application.region.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AppDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void addCountry(CountryDBitem country);

    @Query("select * from countries_table")
    public List<CountryDBitem> getCountries();

    @Delete
    public void deleteCountry(CountryDBitem country);

    @Query("DELETE FROM countries_table")
    public void deleteTable();
}
