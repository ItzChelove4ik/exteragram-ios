package androidx.media3.exoplayer.drm;

import android.os.Build;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes4.dex */
abstract class ClearKeyUtil {
    public static byte[] adjustRequestData(byte[] r2) {
        if (Build.VERSION.SDK_INT < 27) goto L6;
        return r2;
    L6:
        return Util.getUtf8Bytes(base64ToBase64Url(Util.fromUtf8Bytes(r2)));
    }

    public static byte[] adjustResponseData(byte[] r5) {
        if (Build.VERSION.SDK_INT < 27) goto L17;
        return r5;
    L17:
        JSONObject r0 = new JSONObject(Util.fromUtf8Bytes(r5));     // Catch: JSONException -> L10
        StringBuilder r1 = new StringBuilder("{\"keys\":[");     // Catch: JSONException -> L10
        JSONArray r02 = r0.getJSONArray("keys");     // Catch: JSONException -> L10
        int r2 = 0;
    L7:
        if (r2 >= r02.length()) goto L13;
        if (r2 == 0) goto L12;
        r1.append(",");     // Catch: JSONException -> L10
    L12:
        JSONObject r3 = r02.getJSONObject(r2);     // Catch: JSONException -> L10
        r1.append("{\"k\":\"");     // Catch: JSONException -> L10
        r1.append(base64UrlToBase64(r3.getString("k")));     // Catch: JSONException -> L10
        r1.append("\",\"kid\":\"");     // Catch: JSONException -> L10
        r1.append(base64UrlToBase64(r3.getString("kid")));     // Catch: JSONException -> L10
        r1.append("\",\"kty\":\"");     // Catch: JSONException -> L10
        r1.append(r3.getString("kty"));     // Catch: JSONException -> L10
        r1.append("\"}");     // Catch: JSONException -> L10
        r2 = r2 + 1;     // Catch: JSONException -> L10
        goto L7
    L13:
        r1.append("]}");     // Catch: JSONException -> L10
        return Util.getUtf8Bytes(r1.toString());
    L10:
        e = move-exception;
        Log.e("ClearKeyUtil", "Failed to adjust response data: " + Util.fromUtf8Bytes(r5), e);
        return r5;
    }

    private static String base64ToBase64Url(String r2) {
        return r2.replace(SignatureVisitor.EXTENDS, SignatureVisitor.SUPER).replace('/', '_');
    }

    private static String base64UrlToBase64(String r2) {
        return r2.replace(SignatureVisitor.SUPER, SignatureVisitor.EXTENDS).replace('_', '/');
    }
}
