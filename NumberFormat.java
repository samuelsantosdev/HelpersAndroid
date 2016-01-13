package Helpers;

import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Created by marbeik on 09/01/16.
 */
public class NumberFormat {

    private String format;
    private boolean currency= false;
    private boolean reverse = false;
    private EditText editable;
    private TextWatcher context;

    public NumberFormat(TextWatcher context){
        this.context = context;
    }

    public void setEditable(EditText editable){
        this.editable = editable;
    }

    public NumberFormat(){

    }

    public void setMask(String str){
        this.format = str;
    }

    public void setCurrency(boolean bl){
        this.currency = bl;
    }

    public void setReverse(boolean bl){
        this.reverse = bl;
    }

    private String reverse(String str){
        return new StringBuilder(str).reverse().toString();
    }

    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s).replace(' ', '0');
    }

    public static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s).replace(' ', '0');
    }

    public void apply() {

        this.editable.removeTextChangedListener(this.context);
        String str = this.editable.getText().toString();
        str = str.replaceAll("\\D+", "");
        Integer nt = Integer.parseInt(str);
        str = nt.toString();

        String frmted = this.applyMask(str);

        this.editable.setText(frmted);
        this.editable.setSelection(frmted.length());
        this.editable.addTextChangedListener(this.context);

    }

    private String applyMask(String str){

        if(this.reverse) {
            str = this.reverse(str);
            this.format = this.reverse(this.format);
        }

        if(str.length() < 3 && this.currency)
            str = this.padRight(str, 3);

        String frmted = new String();
        char[] format = this.format.toCharArray();
        char[] string = str.toCharArray();
        int k = 0;

        for(int i = 0; i < format.length; i++){
            if( string.length-1 < k)
                break;

            char c = format[i];
            if ( Character.toString(c).matches("#") ) {
                frmted += Character.toString(string[k]).trim();
                k++;
            } else {
                frmted += Character.toString(c).trim();
            }
        }

        if(this.reverse)
            frmted = this.reverse(frmted);

        return frmted;
    }

    public String apply(Double db, String format){
        this.format = format;
        DecimalFormat df = new DecimalFormat("0.00");
        String str = df.format(db).replaceAll("\\D+", "");
        Integer nt = Integer.parseInt(str);
        str = nt.toString();
        return this.applyMask(str);
    }

    public Double toDouble(String db){
        String str = db.replaceAll("[\\,+]", "!").replaceAll("[\\.+]", "").replaceAll("[\\!+]", ".");
        return Double.parseDouble(str);
    }

}
