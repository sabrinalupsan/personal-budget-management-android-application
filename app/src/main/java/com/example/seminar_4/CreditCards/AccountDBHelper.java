package com.example.seminar_4.CreditCards;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.example.seminar_4.CreditCards.AccountTable;

import java.util.Calendar;

public class AccountDBHelper extends SQLiteOpenHelper {

    public static final String DEBUG_TAG = "AccountDatabase";
    private static final String DATABASE_NAME = "AccountDatabase.db";
    private static final int DATABASE_VERSION = 1;

    public AccountDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {

        AccountTable.onCreate(database);
    }

    // Method is called during an upgrade of the database,
    // e.g. if you increase the database version
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {
        AccountTable.onUpgrade(database, oldVersion, newVersion);
    }

    public Cursor getDataCursor() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AccountTable.TABLE_TODO, null, null, null, null, null, null, null);
        // Make sure that potential listeners are getting notified
        return cursor;
    }

//    public String getLimit(String id)
//    {
//        SQLiteDatabase db = getWritableDatabase();
//        String query = "SELECT * FROM " + AccountTable.TABLE_TODO + " WHERE "+AccountTable.COLUMN_IBAN + " = '" + id+"'";
//        //Cursor point to a location in your results
//        Cursor c = db.rawQuery(query, null);
//        if (c != null) {
//            c.moveToFirst();
//        }
//        return c.getString(c.getColumnIndex("_id"));
//    }

    public String getLimit(String id) throws SQLException {
        SQLiteDatabase db = getWritableDatabase();
        Cursor mCursor = db.rawQuery(" SELECT " + AccountTable.COLUMN_LIMIT + " FROM " +
                AccountTable.TABLE_TODO + " WHERE " + AccountTable.COLUMN_IBAN + " = \"" + id + "\"" ,
                null,null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor.getString(mCursor.getColumnIndex("AmountLimit"));

    }

    public void insertSample(String iban, String bank, String limit) {

        SQLiteDatabase db = this.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(AccountTable.COLUMN_IBAN, iban);
            cv.put(AccountTable.COLUMN_BANK, bank);
            cv.put(AccountTable.COLUMN_LIMIT, limit);
    }

    public void deleteItemById(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = new String[]{value};
        int result = db.delete(AccountTable.TABLE_TODO," _id=?", args);
        Log.d("DatabaseOperation", "Deleted value: " + result);
    }
}