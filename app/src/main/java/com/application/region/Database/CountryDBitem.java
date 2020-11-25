package com.application.region.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.application.region.model.Language;

import java.util.List;

@Entity (tableName = "countries_table")
public class CountryDBitem {

    @PrimaryKey
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "country_name")
    private String name;

    @ColumnInfo(name = "country_capital")
    private String capital;

    @ColumnInfo(name = "country_flag")
    private String flag;

    @ColumnInfo(name = "country_region")
    private String region;

    @ColumnInfo(name = "country_subregion")
    private String subregion;

    @ColumnInfo(name = "country_population")
    private int population;

    @ColumnInfo(name = "country_borders")
    private String borders;

    @ColumnInfo(name = "country_languages")
    private String languages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getBorders() {
        return borders;
    }

    public void setBorders(String borders) {
        this.borders = borders;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }
}
