package com.madonasyombua.roomexample.room.db;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.madonasyombua.roomexample.room.dao.ItemDao;
import com.madonasyombua.roomexample.room.entity.Item;

/**
 * @author madona
 * Database class Item Database which extends Room Database
 * include version number = 1
 * set export schema to false
 *
 */

@Database(entities = Item.class, version = 2, exportSchema = false)
public abstract class ItemDatabase extends RoomDatabase {

    private static final String LOG_TAG = ItemDatabase.class.getSimpleName();
    public abstract ItemDao itemDao();
    private static final String DATABASE_NAME = "item_table";


    private static ItemDatabase itemDataBaseInstance;

    private static Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };
    public static ItemDatabase getDatabase(final Context context){

        if(itemDataBaseInstance == null){
            Log.d(LOG_TAG,"Creating Database");
            itemDataBaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    ItemDatabase.class,ItemDatabase.DATABASE_NAME)

                    .addMigrations(MIGRATION_1_2)
                    .addCallback(roomCallback)
                    .build();
        }


        return  itemDataBaseInstance;
    }





    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line. for now let's show it
            //new PopulateDbAsync(itemDataBaseInstance).execute();
        }
    };
}
