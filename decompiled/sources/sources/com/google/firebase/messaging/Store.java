package com.google.firebase.messaging;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
class Store {
    final SharedPreferences store;

    public static class Token {
        private static final long REFRESH_PERIOD_MILLIS = 0;
        final String appVersion;
        final long timestamp;
        final String token;

        static {
            REFRESH_PERIOD_MILLIS = 604800000;
        }

        private Token(String r1, String r2, long r3) {
            this.token = r1;
            this.appVersion = r2;
            this.timestamp = r3;
        }

        public static String encode(String r2, String r3, long r4) {
            JSONObject r0 = new JSONObject();     // Catch: JSONException -> L4
            r0.put("token", r2);     // Catch: JSONException -> L4
            r0.put("appVersion", r3);     // Catch: JSONException -> L4
            r0.put("timestamp", r4);     // Catch: JSONException -> L4
            return r0.toString();
        L4:
            e = move-exception;
            Log.w("FirebaseMessaging", "Failed to encode token: " + e);
            return null;
        }

        public static Token parse(String r6) {
            if (TextUtils.isEmpty(r6) == false) goto L6;
            return null;
        L6:
            if (r6.startsWith("{") == false) goto L13;
            JSONObject r0 = new JSONObject(r6);     // Catch: JSONException -> L9
            return new Token(r0.getString("token"), r0.getString("appVersion"), r0.getLong("timestamp"));
        L9:
            e = move-exception;
            Log.w("FirebaseMessaging", "Failed to parse token: " + e);
            return null;
        L13:
            return new Token(r6, null, 0);
        }

        public boolean isFid() {
            if (this.token.length() > 22) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean needsRefresh(String r7) {
            if (System.currentTimeMillis() <= (this.timestamp + REFRESH_PERIOD_MILLIS)) goto L5;
            return true;
        L5:
            if (r7.equals(this.appVersion) == false) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public Store(Context r3) {
        this.store = r3.getSharedPreferences("com.google.android.gms.appid", 0);
        checkForRestore(r3, "com.google.android.gms.appid-no-backup");
    }

    private void checkForRestore(Context r3, String r4) {
        File r1 = new File(ContextCompat.getNoBackupFilesDir(r3), r4);
        if (r1.exists() == true) goto L21;
    L11:
        e = move-exception;
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L20;
        Log.d("FirebaseMessaging", "Error creating file in no backup dir: " + e.getMessage());
        return;
    L20:
        return;
    L6:
        if (r1.createNewFile() == true) goto L8;
        return;
    L8:
        if (isEmpty() == true) goto L19;
        Log.i("FirebaseMessaging", "App restored, clearing state");     // Catch: IOException -> L11
        deleteAll();     // Catch: IOException -> L11
        return;
    L19:
        return;
    }

    private String createTokenKey(String r1, String r2) {
        return r1 + "|T|" + r2 + "|*";
    }

    public synchronized void deleteAll() {
        monitor-enter(this);
        this.store.edit().clear().commit();     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized Token getToken(String r2, String r3) {
        monitor-enter(this);
        Token r22 = Token.parse(this.store.getString(createTokenKey(r2, r3), null));     // Catch: Throwable -> L6
        monitor-exit(this);
        return r22;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized boolean isEmpty() {
        monitor-enter(this);
        boolean r0 = this.store.getAll().isEmpty();     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized void saveToken(String r3, String r4, String r5, String r6) {
        monitor-enter(this);
        String r52 = Token.encode(r5, r6, System.currentTimeMillis());     // Catch: Throwable -> L10
        if (r52 != null) goto L7;
        monitor-exit(this);
        return;
    L7:
        SharedPreferences.Editor r62 = this.store.edit();     // Catch: Throwable -> L10
        r62.putString(createTokenKey(r3, r4), r52);     // Catch: Throwable -> L10
        r62.commit();     // Catch: Throwable -> L10
        monitor-exit(this);
        return;
    L10:
        th = move-exception;
        throw th;
    }
}
