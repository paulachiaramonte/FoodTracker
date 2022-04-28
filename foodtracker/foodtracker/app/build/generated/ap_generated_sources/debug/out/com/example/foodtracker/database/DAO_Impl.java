package com.example.foodtracker.database;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class DAO_Impl implements DAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfFoodD;

  private final EntityInsertionAdapter __insertionAdapterOfShoppingD;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfFoodD;

  public DAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFoodD = new EntityInsertionAdapter<FoodD>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Food_table`(`id`,`food`,`place`,`expire`,`quantity`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FoodD value) {
        stmt.bindLong(1, value.id);
        if (value.food == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.food);
        }
        if (value.place == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.place);
        }
        if (value.expire == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.expire);
        }
        stmt.bindLong(5, value.quantity);
      }
    };
    this.__insertionAdapterOfShoppingD = new EntityInsertionAdapter<ShoppingD>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Shopping_table`(`id`,`food`,`quantity`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ShoppingD value) {
        stmt.bindLong(1, value.id);
        if (value.food == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.food);
        }
        stmt.bindLong(3, value.quantity);
      }
    };
    this.__deletionAdapterOfFoodD = new EntityDeletionOrUpdateAdapter<FoodD>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Food_table` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FoodD value) {
        stmt.bindLong(1, value.id);
      }
    };
  }

  @Override
  public void insertFoodD(FoodD food) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfFoodD.insert(food);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertShopping(ShoppingD food) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfShoppingD.insert(food);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(FoodD food) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfFoodD.handle(food);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<FoodD> getAllFoodD() {
    final String _sql = "SELECT * FROM Food_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final List<FoodD> _result = new ArrayList<FoodD>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final FoodD _item;
        _item = __entityCursorConverter_comExampleFoodtrackerDatabaseFoodD(_cursor);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public FoodD findById(long id) {
    final String _sql = "SELECT * FROM food_table WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final FoodD _result;
      if(_cursor.moveToFirst()) {
        _result = __entityCursorConverter_comExampleFoodtrackerDatabaseFoodD(_cursor);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<FoodD> loadAllByIds(long[] foodsIds) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM Food_table WHERE id IN (");
    final int _inputSize = foodsIds.length;
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (long _item : foodsIds) {
      _statement.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final List<FoodD> _result = new ArrayList<FoodD>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final FoodD _item_1;
        _item_1 = __entityCursorConverter_comExampleFoodtrackerDatabaseFoodD(_cursor);
        _result.add(_item_1);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> getFoodN() {
    final String _sql = "SELECT food FROM Food_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        _item = _cursor.getString(0);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> getExp() {
    final String _sql = "SELECT expire FROM Food_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        _item = _cursor.getString(0);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> getFood_place(String place) {
    final String _sql = "SELECT food FROM Food_table WHERE place= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (place == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, place);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        _item = _cursor.getString(0);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> getExp_place(String place) {
    final String _sql = "SELECT expire FROM Food_table WHERE place= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (place == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, place);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        _item = _cursor.getString(0);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> getFood_shop() {
    final String _sql = "SELECT food FROM Shopping_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        _item = _cursor.getString(0);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Integer> getQuantity() {
    final String _sql = "SELECT quantity FROM Shopping_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final List<Integer> _result = new ArrayList<Integer>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Integer _item;
        if (_cursor.isNull(0)) {
          _item = null;
        } else {
          _item = _cursor.getInt(0);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  private FoodD __entityCursorConverter_comExampleFoodtrackerDatabaseFoodD(Cursor cursor) {
    final FoodD _entity;
    final int _cursorIndexOfId = cursor.getColumnIndex("id");
    final int _cursorIndexOfFood = cursor.getColumnIndex("food");
    final int _cursorIndexOfPlace = cursor.getColumnIndex("place");
    final int _cursorIndexOfExpire = cursor.getColumnIndex("expire");
    final int _cursorIndexOfQuantity = cursor.getColumnIndex("quantity");
    final long _tmpId;
    if (_cursorIndexOfId == -1) {
      _tmpId = 0;
    } else {
      _tmpId = cursor.getLong(_cursorIndexOfId);
    }
    final String _tmpFood;
    if (_cursorIndexOfFood == -1) {
      _tmpFood = null;
    } else {
      _tmpFood = cursor.getString(_cursorIndexOfFood);
    }
    final String _tmpPlace;
    if (_cursorIndexOfPlace == -1) {
      _tmpPlace = null;
    } else {
      _tmpPlace = cursor.getString(_cursorIndexOfPlace);
    }
    final String _tmpExpire;
    if (_cursorIndexOfExpire == -1) {
      _tmpExpire = null;
    } else {
      _tmpExpire = cursor.getString(_cursorIndexOfExpire);
    }
    final int _tmpQuantity;
    if (_cursorIndexOfQuantity == -1) {
      _tmpQuantity = 0;
    } else {
      _tmpQuantity = cursor.getInt(_cursorIndexOfQuantity);
    }
    _entity = new FoodD(_tmpId,_tmpFood,_tmpPlace,_tmpExpire,_tmpQuantity);
    return _entity;
  }
}
