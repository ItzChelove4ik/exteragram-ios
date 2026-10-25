package org.telegram.messenger;

import android.content.SharedPreferences;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.NotificationBadge;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public class AuthTokensHelper {
    public AuthTokensHelper() {
    }

    public static void addLogOutToken(TLRPC.TL_auth_loggedOut r5) {
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("saved_tokens", 0);
        int r2 = r0.getInt(NotificationBadge.NewHtcHomeBadger.COUNT, 0);
        SerializedData r3 = new SerializedData(r5.getObjectSize());
        r5.serializeToStream(r3);
        r0.edit().putString("log_out_token_" + r2, Utilities.bytesToHex(r3.toByteArray())).putInt(NotificationBadge.NewHtcHomeBadger.COUNT, r2 + 1).apply();
        BackupAgent.requestBackup();
    }

    public static void clearLogInTokens() {
        ApplicationLoader.applicationContext.getSharedPreferences("saved_tokens_login", 0).edit().clear().apply();
        ApplicationLoader.applicationContext.getSharedPreferences("saved_tokens", 0).edit().clear().apply();
    }

    public static ArrayList<TLRPC.TL_auth_authorization> getSavedLogInTokens() {
        int r2 = 0;
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("saved_tokens_login", 0);
        int r1 = r0.getInt(NotificationBadge.NewHtcHomeBadger.COUNT, 0);
        if (r1 != 0) goto L6;
        return null;
    L6:
        ArrayList<TLRPC.TL_auth_authorization> r3 = new ArrayList();
    L7:
        if (r2 >= r1) goto L16;
        SerializedData r5 = new SerializedData(Utilities.hexToBytes(r0.getString("log_in_token_" + r2, _UrlKt.FRAGMENT_ENCODE_SET)));     // Catch: Exception -> L13
        TLRPC.auth_Authorization r4 = TLRPC.auth_Authorization.TLdeserialize(r5, r5.readInt32(true), true);     // Catch: Exception -> L13
        if ((r4 instanceof TLRPC.TL_auth_authorization) == false) goto L15;
        r3.add((TLRPC.TL_auth_authorization) r4);     // Catch: Exception -> L13
    L15:
        r2 = r2 + 1;
    L13:
        e = move-exception;
        FileLog.e(e);
        goto L15
    L16:
        return r3;
    }

    public static ArrayList<TLRPC.TL_auth_loggedOut> getSavedLogOutTokens() {
        int r2 = 0;
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("saved_tokens", 0);
        int r1 = r0.getInt(NotificationBadge.NewHtcHomeBadger.COUNT, 0);
        if (r1 != 0) goto L6;
        return null;
    L6:
        ArrayList<TLRPC.TL_auth_loggedOut> r3 = new ArrayList();
    L7:
        if (r2 >= r1) goto L12;
        SerializedData r5 = new SerializedData(Utilities.hexToBytes(r0.getString("log_out_token_" + r2, _UrlKt.FRAGMENT_ENCODE_SET)));
        TLRPC.TL_auth_loggedOut r4 = TLRPC.TL_auth_loggedOut.TLdeserialize(r5, r5.readInt32(true), true);
        if (r4 == null) goto L11;
        r3.add(r4);
    L11:
        r2 = r2 + 1;
        goto L7
    L12:
        return r3;
    }

    public static void saveLogInToken(TLRPC.TL_auth_authorization r3) {
        if (BuildVars.DEBUG_VERSION == false) goto L5;
        FileLog.d("saveLogInToken ".concat(new String(r3.future_auth_token, StandardCharsets.UTF_8)));
    L5:
        ArrayList<TLRPC.TL_auth_authorization> r0 = getSavedLogInTokens();
        if (r0 != null) goto L8;
        r0 = new ArrayList();
    L8:
        r0.add(0, r3);
        saveLogInTokens(r0);
    }

    private static void saveLogInTokens(ArrayList<TLRPC.TL_auth_authorization> r6) {
        int r2 = 0;
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("saved_tokens_login", 0);
        ArrayList r1 = new ArrayList();
        r0.edit().clear().apply();
        int r3 = 0;
    L4:
        if (r3 >= Math.min(20, r6.size())) goto L7;
        r1.add(r6.get(r3));
        r3 = r3 + 1;
        goto L4
    L7:
        if (r1.size() <= 0) goto L16;
        SharedPreferences.Editor r62 = r0.edit();
        r62.putInt(NotificationBadge.NewHtcHomeBadger.COUNT, r1.size());
    L10:
        if (r2 >= r1.size()) goto L12;
        SerializedData r02 = new SerializedData(((TLRPC.TL_auth_authorization) r1.get(r2)).getObjectSize());
        ((TLRPC.TL_auth_authorization) r1.get(r2)).serializeToStream(r02);
        r62.putString("log_in_token_" + r2, Utilities.bytesToHex(r02.toByteArray()));
        r2 = r2 + 1;
        goto L10
    L12:
        r62.apply();
        BackupAgent.requestBackup();
        return;
    }

    public static void saveLogOutTokens(ArrayList<TLRPC.TL_auth_loggedOut> r6) {
        int r2 = 0;
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("saved_tokens", 0);
        ArrayList r1 = new ArrayList();
        r0.edit().clear().apply();
        System.currentTimeMillis();
        int r3 = 0;
    L4:
        if (r3 >= Math.min(20, r6.size())) goto L7;
        r1.add(r6.get(r3));
        r3 = r3 + 1;
        goto L4
    L7:
        if (r1.size() <= 0) goto L16;
        SharedPreferences.Editor r62 = r0.edit();
        r62.putInt(NotificationBadge.NewHtcHomeBadger.COUNT, r1.size());
    L10:
        if (r2 >= r1.size()) goto L12;
        SerializedData r02 = new SerializedData(((TLRPC.TL_auth_loggedOut) r1.get(r2)).getObjectSize());
        ((TLRPC.TL_auth_loggedOut) r1.get(r2)).serializeToStream(r02);
        r62.putString("log_out_token_" + r2, Utilities.bytesToHex(r02.toByteArray()));
        r2 = r2 + 1;
        goto L10
    L12:
        r62.apply();
        return;
    }
}
