package com.example.foodtracker.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class AppDatabase_Impl extends AppDatabase {
  private volatile DAO _dAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Food_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `food` TEXT, `place` TEXT, `expire` TEXT, `quantity` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Shopping_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `food` TEXT, `quantity` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"b62c2b680224017f80193eab0c2bbd50\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Food_table`");
        _db.execSQL("DROP TABLE IF EXISTS `Shopping_table`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsFoodTable = new HashMap<String, TableInfo.Column>(5);
        _columnsFoodTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsFoodTable.put("food", new TableInfo.Column("food", "TEXT", false, 0));
        _columnsFoodTable.put("place", new TableInfo.Column("place", "TEXT", false, 0));
        _columnsFoodTable.put("expire", new TableInfo.Column("expire", "TEXT", false, 0));
        _columnsFoodTable.put("quantity", new TableInfo.Column("quantity", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFoodTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFoodTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFoodTable = new TableInfo("Food_table", _columnsFoodTable, _foreignKeysFoodTable, _indicesFoodTable);
        final TableInfo _existingFoodTable = TableInfo.read(_db, "Food_table");
        if (! _infoFoodTable.equals(_existingFoodTable)) {
          throw new IllegalStateException("Migration didn't properly handle Food_table(com.example.foodtracker.database.FoodD).\n"
                  + " Expected:\n" + _infoFoodTable + "\n"
                  + " Found:\n" + _existingFoodTable);
        }
        final HashMap<String, TableInfo.Column> _columnsShoppingTable = new HashMap<String, TableInfo.Column>(3);
        _columnsShoppingTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsShoppingTable.put("food", new TableInfo.Column("food", "TEXT", false, 0));
        _columnsShoppingTable.put("quantity", new TableInfo.Column("quantity", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysShoppingTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesShoppingTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoShoppingTable = new TableInfo("Shopping_table", _columnsShoppingTable, _foreignKeysShoppingTable, _indicesShoppingTable);
        final TableInfo _existingShoppingTable = TableInfo.read(_db, "Shopping_table");
        if (! _infoShoppingTable.equals(_existingShoppingTable)) {
          throw new IllegalStateException("Migration didn't properly handle Shopping_table(com.example.foodtracker.database.ShoppingD).\n"
                  + " Expected:\n" + _infoShoppingTable + "\n"
                  + " Found:\n" + _existingShoppingTable);
        }
      }
    }, "b62c2b680224017f80193eab0c2bbd50", "48cc5f6e9180e78cd9fa67b0ac41c041");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Food_table","Shopping_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Food_table`");
      _db.execSQL("DELETE FROM `Shopping_table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public DAO DAO() {
    if (_dAO != null) {
      return _dAO;
    } else {
      synchronized(this) {
        if(_dAO == null) {
          _dAO = new DAO_Impl(this);
        }
        return _dAO;
      }
    }
  }
}
