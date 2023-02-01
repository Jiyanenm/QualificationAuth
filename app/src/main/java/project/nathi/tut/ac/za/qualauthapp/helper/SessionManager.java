package project.nathi.tut.ac.za.qualauthapp.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Nkosinathi.Jiyane on 2017/03/10.
 */

public class SessionManager {
    //LogCat tag
    public static String TAG = SessionManager.class.getSimpleName();

    //Shared preferences
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    //Shared pref mode
    int PRIVATE_MODE = 0;

    //Shared preference file name
    private  static  final String PREF_NAME = "QualAuthLogin";

    private  static  final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public SessionManager(Context context)
    {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn)
    {
        //commit changes
        editor.commit();

        Log.d(TAG, "User login session modified");
    }

    public boolean isLoggedIn()
    {
        return  pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

}
