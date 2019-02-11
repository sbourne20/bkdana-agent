package id.bkdana.agent.Util.Session;

import android.content.Context;
import android.content.SharedPreferences;

public class BKDanaAgentSession {

    private SharedPreferences mPref;
    private android.content.SharedPreferences.Editor mEditor;
    private Context mContext;

    private static int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "BKDanaAgent";
    private static final String KEY_IS_LOGIN = "IsLoggedIn";
    private static final String KEY_AUTHRIZATION = "auth";
    private static final String KEY_REFRESH_TOKEN = "refreshToken";
    private static final String OBJECT_DATA = "objectData";
    private static final String ID_MOD_AGENT = "idModAgent";
    private static final String FULLNAME_AGENT = "fulnameAgent";
    private static final String EMAIL_AGENT = "emailAgent";
    private static final String PHONE_AGENT ="phoneAgent";
    private static final String USERNAME_AGENT = "usernameAgent";
    private static final String STATUS_AGENT = "statusAgent";

    public BKDanaAgentSession(Context context){
        this.mContext = context;
        this.mPref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        this.mEditor = mPref.edit();
    }

    public void setAuthorization(String s) {
        mEditor.putString(KEY_AUTHRIZATION,s);
        mEditor.commit();
    }

    public void setProfile(String id, String fullname, String email, String phone, String username, String status) {
        mEditor.putString(ID_MOD_AGENT,id);
        mEditor.putString(FULLNAME_AGENT,fullname);
        mEditor.putString(EMAIL_AGENT,email);
        mEditor.putString(PHONE_AGENT,phone);
        mEditor.putString(USERNAME_AGENT,username);
        mEditor.putString(STATUS_AGENT,status);
        mEditor.commit();
    }



    public String getAutorization() {
        return mPref.getString(KEY_AUTHRIZATION,"");
    }

    public String getidMod() {
        return mPref.getString(ID_MOD_AGENT,"");

    }

    public String getFullname() {
        return mPref.getString(FULLNAME_AGENT,"");

    }

    public String getEmail() {
        return mPref.getString(EMAIL_AGENT,"");

    }

    public String getPhone() {
        return mPref.getString(PHONE_AGENT,"");

    }

    public String getUsername() {
        return mPref.getString(USERNAME_AGENT,"");

    }

    public String getStatus() {
        return mPref.getString(STATUS_AGENT,"");

    }


    public void clear() {
        mEditor.clear();
        mEditor.commit();
    }
}
