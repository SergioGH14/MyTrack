package com.sdm.sergio.mytrack.util;

/**
 * Created by Sergio on 23/04/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sdm.sergio.mytrack.model.TMDBMovie;

import java.io.BufferedReader;
import java.io.StringReader;


public class TMDBMovieSQLHelper extends SQLiteOpenHelper{

    // Singleton pattern to centralize access to the database
    private static TMDBMovieSQLHelper instance;

    public synchronized static TMDBMovieSQLHelper getInstance(Context context) {
        if (instance == null) {
            instance = new TMDBMovieSQLHelper(context.getApplicationContext());
        }
        return instance;
    }

    /*
        Create a helper object to manage a database
    */
    private TMDBMovieSQLHelper(Context context) {
        // Parameters:
        //  context
        //  filename of the database, or null for in-memory database
        //  factory to create cursor objects, default if null
        //  version of the database (upgrades/downgrades existing ones)
        super(context, "tmdbMovie_database", null, 1);
    }

    /*
        This method is only called to create the database the first time it is accessed
    */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL query to create a TMDBMovie table with
        //  integer primary key: id
        //  String unique: text
        //  String: author
        db.execSQL("CREATE TABLE TMDBMovieTable " +
                "(id INTEGER PRIMARY KEY , TMDBMovie TEXT NOT NULL);");
    }

    /*
        This method is only called when the database needs to be upgraded,
        so it has been left blank
    */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*
        Get a TMDBMovie by id
     */
    public TMDBMovie getTMDBMovie(int id){
        String TMDBMovieSTRING = "";
        //Convertimos el entero en string para poder trabajar luego con el
        Integer iden = new Integer(id);
        String identificador = iden.toString();

        //Creamos los argumentos para la consulta
        String[] args = new String[] {identificador};
        // Get access to the database in read mode
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(
                "TMDBMovieTable", new String[]{"TMDBMovie"}, "id=?", args, null, null, null);
        //Nos aseguramos de que existe al menos un registro
        if (cursor.moveToFirst()) {
            TMDBMovieSTRING = cursor.getString(0);
        }
        cursor.close();
        database.close();
        StringReader reader = new StringReader(TMDBMovieSTRING);
        BufferedReader br = new BufferedReader(reader);
        GsonBuilder gbuilder = new GsonBuilder();
        Gson gson = gbuilder.create();
        TMDBMovie fullmovie = gson.fromJson(reader, TMDBMovie.class);
        return fullmovie;
    }

    /*
        Insert a new quotation into the database
    */
    public void addTMDBMovie(int  id, String TMDBMovieSTRING) {

        // Get access to the database in write mode
        SQLiteDatabase database = getWritableDatabase();
        // Insert the new TMDBMovie into the table
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("TMDBMovie", TMDBMovieSTRING);
        database.insert("TMDBMovieTable", null, values);
        // Close the database helper
        database.close();
    }

    /*
        Delete a given quotation from the database
    */
    public void deleteTMDBMovie(int id) {
        //Convertimos el entero en string para poder trabajar luego con el
        Integer iden = new Integer(id);
        String identificador = iden.toString();
        // Get access to the database in write mode
        SQLiteDatabase database = getWritableDatabase();
        // Delete from the table any entry with the given text
        database.delete("TMDBMovieTable", "id=?", new String[]{identificador});
        // Close the database helper
        database.close();
    }

}