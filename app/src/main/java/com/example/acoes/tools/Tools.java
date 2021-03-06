package com.example.acoes.tools;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Tools {
    public static void showAlert(String title, String message, Context context){
        try{
            AlertDialog.Builder alert = new AlertDialog.Builder(context);

            alert.setTitle(title).setMessage(message).setPositiveButton("OK", null).show();
        }catch (Exception ex){
            Log.e("MostrarAlerta", ex.getMessage());
        }


    }
    public static void toastMessage(String message, Context context){

        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }
    public static String converterData(String data, String formatoInicial, String formatoDesejado) {
        String wDataConvertida = "";
        try {
            java.text.DateFormat parser = new SimpleDateFormat(formatoInicial, Locale.getDefault());
            java.text.DateFormat formatter = new SimpleDateFormat(formatoDesejado, Locale.getDefault());

            wDataConvertida = formatter.format(parser.parse(data));

        } catch (java.text.ParseException ex) {
            System.out.println(ex.getMessage().toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage().toString());
        } finally {
            return wDataConvertida;
        }
    }
}
