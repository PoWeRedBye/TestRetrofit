package com.example.maxim_ozarovskiy.testretrofitrestapp.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.maxim_ozarovskiy.testretrofitrestapp.model.CityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxim_Ozarovskiy on 19.07.2017.
 */

public class CityCheckDBAdapter {

    private static final String TAG = "CityCheckDBAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;




    public class MyFieldDB {
        public static final String ROWID = "id";
        public static final String CITY_NAME = "city_name";


        private static final String DATABASE_NAME = "CityNameBook";
        private static final String SQLITE_TABLE = "CityName";
        private static final int DATABASE_VERSION = 1;


        private static final String DATABASE_CREATE =
                "CREATE TABLE IF NOT EXISTS " + SQLITE_TABLE + " (" +
                        ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        CITY_NAME + "," +
                        " UNIQUE (" + ROWID + "));";

    }

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, MyFieldDB.DATABASE_NAME, null, MyFieldDB.DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, MyFieldDB.DATABASE_CREATE);
            db.execSQL(MyFieldDB.DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version" + oldVersion + " to "
                    + newVersion+ ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + MyFieldDB.SQLITE_TABLE);
            onCreate(db);
        }
    }

    public CityCheckDBAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public CityCheckDBAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createCityField(String name) {

        ContentValues initialValues = new ContentValues();

        initialValues.put(MyFieldDB.CITY_NAME, name);


        return mDb.insert(MyFieldDB.SQLITE_TABLE, null, initialValues);
    }


    public void delRec(long id) {
        mDb.delete(MyFieldDB.SQLITE_TABLE, MyFieldDB.ROWID + " = " + id, null);
    }

    public List<CityModel> getDataFromDB() {
        List<CityModel> cityModelList = new ArrayList<CityModel>();
        String query = "select * from " + MyFieldDB.SQLITE_TABLE;
        mDb = mDbHelper.getWritableDatabase();
        Cursor cursor = mDb.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                CityModel cityModel = new CityModel();
                cityModel.setId(cursor.getString(0));
                cityModel.setCityName(cursor.getString(1));


                cityModelList.add(cityModel);

            } while (cursor.moveToNext());
        }


        Log.d("city check data", cityModelList.toString());


        return cityModelList;
    }

}
