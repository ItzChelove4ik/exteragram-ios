package com.google.firebase.installations.local;

import android.util.Base64;
import android.util.Log;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import com.google.firebase.FirebaseApp;
import com.google.firebase.datastorage.JavaDataStorage;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import okhttp3.internal.url._UrlKt;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class IidStore {
    private static final String[] ALLOWABLE_SCOPES = null;
    private static final Preferences.Key<String> STORE_KEY_ID = null;
    private static final Preferences.Key<String> STORE_KEY_PUB = null;
    private final String defaultSenderId;
    private final JavaDataStorage iidDataStore;

    static {
        STORE_KEY_PUB = PreferencesKeys.stringKey("|S||P|");
        STORE_KEY_ID = PreferencesKeys.stringKey("|S|id");
        ALLOWABLE_SCOPES = new String[]{"*", "FCM", "GCM", _UrlKt.FRAGMENT_ENCODE_SET};
    }

    public IidStore(FirebaseApp r4) {
        this.iidDataStore = new JavaDataStorage(r4.getApplicationContext(), "com.google.android.gms.appid");
        this.defaultSenderId = getDefaultSenderId(r4);
    }

    private Preferences.Key<String> createTokenKey(String r2, String r3) {
        return PreferencesKeys.stringKey("|T|" + r2 + "|" + r3);
    }

    private static String getDefaultSenderId(FirebaseApp r3) {
        String r0 = r3.getOptions().getGcmSenderId();
        if (r0 == null) goto L5;
        return r0;
    L5:
        String r32 = r3.getOptions().getApplicationId();
        if (r32.startsWith("1:") == false) goto L8;
    L10:
        String[] r33 = r32.split(":");
        if (r33.length == 4) goto L13;
        return null;
    L13:
        String r34 = r33[1];
        if (r34.isEmpty() == false) goto L16;
        return null;
    L16:
        return r34;
    L8:
        if (r32.startsWith("2:") == true) goto L10;
        return r32;
    }

    private static String getIdFromPublicKey(PublicKey r3) {
        byte[] r32 = r3.getEncoded();
        byte[] r33 = MessageDigest.getInstance("SHA1").digest(r32);     // Catch: NoSuchAlgorithmException -> L5
        r33[0] = (byte) (((r33[0] & 15) + 112) & 255);     // Catch: NoSuchAlgorithmException -> L5
        return Base64.encodeToString(r33, 0, 8, 11);
    L5:
        Log.w("ContentValues", "Unexpected error, device missing required algorithms");
        return null;
    }

    private String parseIidTokenFromJson(String r1) {
        return new JSONObject(r1).getString("token");
    L4:
        return null;
    }

    private PublicKey parseKey(String r2) {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(r2, 8)));
    L5:
        e = move-exception;
        Log.w("ContentValues", "Invalid key stored " + e);
        return null;
    }

    private String readInstanceIdFromLocalStorage() {
        return (String) this.iidDataStore.getSync(STORE_KEY_ID, null);
    }

    private String readPublicKeyFromLocalStorageAndCalculateInstanceId() {
        String r0 = (String) this.iidDataStore.getSync(STORE_KEY_PUB, null);
        if (r0 != null) goto L5;
        return null;
    L5:
        PublicKey r3 = parseKey(r0);
        if (r3 != null) goto L9;
        return null;
    L9:
        return getIdFromPublicKey(r3);
    }

    public String readIid() {
        String r0 = readInstanceIdFromLocalStorage();
        if (r0 == null) goto L6;
        return r0;
    L6:
        return readPublicKeyFromLocalStorageAndCalculateInstanceId();
    }

    public String readToken() {
        String[] r0 = ALLOWABLE_SCOPES;
        int r1 = r0.length;
        int r2 = 0;
    L4:
        if (r2 >= r1) goto L15;
        String r4 = r0[r2];
        Preferences.Key<String> r42 = createTokenKey(this.defaultSenderId, r4);
        String r3 = (String) this.iidDataStore.getSync(r42, null);
        if (r3 == null) goto L14;
        if (r3.isEmpty() == true) goto L14;
        if (r3.startsWith("{") == true) goto L12;
        return r3;
    L12:
        return parseIidTokenFromJson(r3);
    L14:
        r2 = r2 + 1;
        goto L4
    L15:
        return null;
    }
}
