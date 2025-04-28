package co.edu.uniminuto.appproject.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniminuto.appproject.dataaccess.ManagerDataBase;
import co.edu.uniminuto.appproject.entities.Mascotas;
import co.edu.uniminuto.appproject.entities.User;


public class MascotasRepository {
    private ManagerDataBase dataBase;
    private View view;
    private Context context;
    private Mascotas mascotas;

    public MascotasRepository(View view, Context context) {
        this.view = view;
            this.context = context;
            this.dataBase = new ManagerDataBase(context);
        }
    
        public void insertMascotas( Mascotas mascotas){
            try {
                SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
                if(sqLiteDatabase != null){
                    ContentValues values = new ContentValues();
                    values.put("mas_nombre", mascotas.getNombreMascota());
                    values.put("mas_tipo", mascotas.getTipoMascota());
                    values.put("mas_edad", mascotas.getEdad());
                    values.put("mas_raza", mascotas.getRaza());
                    values.put("mas_peso", mascotas.getPeso());
                    values.put("mas_status", "1");
                    values.put("mas_idDueno", mascotas.getIdDueno());
                    long response = sqLiteDatabase.insert("mascotas", null, values);
                    Log.d("MascotaInsert", "Insertado con ID: " + response);
                    String message = (response >=1) ? "se registro correctamente" :
                            "no se registro correctamente";
                    Snackbar.make(this.view, message, Snackbar.LENGTH_LONG).show();
                    sqLiteDatabase.close();
    
                }
            }catch (Exception e){
                Log.i("Error en bases de datos", "insertUser: " +e.getMessage());
                throw new RuntimeException(e);
            }

    
        }
        public ArrayList<Mascotas> getAllMascotas(long idDueno){
            SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();
            String sql = "SELECT * FROM mascotas WHERE mas_status = 1 AND mas_idDueno = ?";
            ArrayList<Mascotas> mascotas = new ArrayList<>();
            Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{String.valueOf(idDueno)});
            if(cursor.moveToFirst()){
                do{
                    Mascotas mascota = new Mascotas();
                    mascota.setIdMascota(cursor.getInt(0));
                    mascota.setNombreMascota(cursor.getString(1));
                    mascota.setTipoMascota(cursor.getString(2)); //
                    mascota.setEdad(String.valueOf(cursor.getInt(3)));
                    mascota.setRaza(cursor.getString(4));
                    mascota.setPeso(cursor.getDouble(5));
                    if(cursor.getInt(6)==1) {
                        mascota.setStatus(1);

                    }else {
                        mascota.setStatus(0);
                    }

                    mascota.setIdDueno(cursor.getInt(7));


                    mascotas.add(mascota);
                }while (cursor.moveToNext());
            }cursor.close();
            return mascotas;
    
    
        }
        public  void updateMascotas(Mascotas mascotas){
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("mas_nombre", mascotas.getNombreMascota());
            values.put("mas_tipo", mascotas.getTipoMascota());
            values.put("mas_edad", mascotas.getEdad());
            values.put("mas_raza", mascotas.getRaza());
            values.put("mas_peso", mascotas.getPeso());
           int result=  sqLiteDatabase.update("mascotas", values, "mas_idMascota = ?", new String[]{String.valueOf(mascotas.getIdMascota())});
            if(result>0){
                Snackbar.make(this.view, "se actualizo correctamente", Snackbar.LENGTH_LONG).show();
            }else{
                Snackbar.make(this.view, "no se actualizo correctamente", Snackbar.LENGTH_LONG).show();
            }
            sqLiteDatabase.close();

        }catch (Exception e){
            Log.i("Error en bases de datos", "insertUser: " +e.getMessage());

        }

        }
        public boolean deleteMascotas(int idMascota){
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();
        boolean correcto = false;
        try{
            String sql = "UPDATE mascotas SET mas_status =0  WHERE mas_idMascota = ?";
            sqLiteDatabase.execSQL(sql, new String[]{String.valueOf(mascotas.getIdMascota())});
            correcto = true;
            Snackbar.make(this.view, "se elimino correctamente", Snackbar.LENGTH_LONG).show();

        }catch (SQLException e){
            Log.i("Error en bases de datos", "insertUser: " +e.getMessage());

        } sqLiteDatabase.close();
        return correcto;


        }
        public Mascotas getMascotaByID(int idMascota){
        SQLiteDatabase databaseSql = dataBase.getReadableDatabase();
        Mascotas mascotas = null;

        String sql = "SELECT * FROM mascotas WHERE mas_idMascota = ?";
        Cursor cursor = databaseSql.rawQuery(sql, new String[]{String.valueOf(idMascota)});
        if(cursor.moveToFirst()){
            mascotas = new Mascotas();
            mascotas.setIdMascota(cursor.getInt(0));
            mascotas.setNombreMascota(cursor.getString(1));
            mascotas.setTipoMascota(cursor.getString(2));
            mascotas.setEdad(String.valueOf(cursor.getInt(3)));
            mascotas.setRaza(cursor.getString(4));
            mascotas.setPeso(cursor.getDouble(5));
            mascotas.setStatus(cursor.getInt(6));
            mascotas.setIdDueno(cursor.getInt(7));
            cursor.close();
            databaseSql.close();
        }
        return mascotas;
    }







    }
