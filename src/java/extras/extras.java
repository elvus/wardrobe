/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extras;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elvis
 */
public class extras {

    public static String IsNull(String v) {
        if (v == null || "".equals(v)) {
            v = "NO POSEE";
        }
        return v;
    }

    public static String IsInt(String i) {
        if (i == null || "".equals(i)) {
            i = "0";
        }
        return i;
    }

    public static String sqlDf(String date) {
        Date fecha = null;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dfsql = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fecha = df.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(extras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dfsql.format(fecha);
    }

    public static String fechaActual() {
        Date fecha = new Date();
        SimpleDateFormat dfsql = new SimpleDateFormat("yyyy-MM-dd");
        return dfsql.format(fecha);
    }

    public static String inputDf(String date) {
        Date fecha = null;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dfsql = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fecha = dfsql.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(extras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return df.format(fecha);
    }
}
