package co.edu.uniminuto.appproject.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;



import com.google.android.material.snackbar.Snackbar;

import co.edu.uniminuto.appproject.dataaccess.ManagerDataBase;
import co.edu.uniminuto.appproject.entities.User;

public class UserRepository {
    private ManagerDataBase dataBase;
    private View view;
    private Context context;
    private User user;

    public UserRepository(View view, Context context) {
        this.view = view;
        this.context = context;
        this.dataBase = new ManagerDataBase(context);
    }
    public User login(String user, String password){
            SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();
            String sql = "SELECT * FROM users WHERE use_user = ? AND use_password = ?";
            Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{user, password});
            if(cursor.moveToFirst()){
                User us = new User();
                us.setId(cursor.getInt(0));
                us.setUser(cursor.getString(1));
                us.setName(cursor.getString(2));
                us.setPassword(cursor.getString(3));
                us.setEmail(cursor.getString(4));
                us.setPhone(cursor.getString(5));
                us.setAddress(cursor.getString(6));
                us.setStatus(cursor.getInt(7) == 1);
                cursor.close();
                return us;
            }else{
                return null;
            }




    }
    public long insertUser(User user){
        long newID=-1;
        try {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        if(sqLiteDatabase != null){
            ContentValues values = new ContentValues();
            values.put("use_user", user.getUser());
            values.put("use_name", user.getName());
            values.put("use_password", user.getPassword());
            values.put("use_email", user.getEmail());
            values.put("use_phone", user.getPhone());
            values.put("use_address", user.getAddress());
            values.put("use_status", "1");
            newID=sqLiteDatabase.insert("users", null, values);
            long response = sqLiteDatabase.insert("users", null, values);
            String message = (response >=1) ? "se registro correctamente" :
                    "no se registro correctamente";
            Snackbar.make(this.view, message, Snackbar.LENGTH_LONG).show();
            sqLiteDatabase.close();

        }
    }catch (Exception e){
            Log.i("Error en bases de datos", "insertUser: " +e.getMessage());
            throw new RuntimeException(e);
        }return newID;

        }
}
