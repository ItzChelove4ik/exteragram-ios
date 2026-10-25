package androidx.core.net;

import android.net.Uri;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public abstract class UriCompat {
    public static String toSafeString(Uri r7) {
        String r0 = r7.getScheme();
        String r1 = r7.getSchemeSpecificPart();
        if (r0 != null) goto L5;
    L49:
        StringBuilder r72 = new StringBuilder(64);
        if (r0 == null) goto L52;
        r72.append(r0);
        r72.append(':');
    L52:
        if (r1 == null) goto L55;
        r72.append(r1);
    L55:
        return r72.toString();
    L5:
        if (r0.equalsIgnoreCase("tel") == false) goto L7;
    L33:
        StringBuilder r73 = new StringBuilder(64);
        r73.append(r0);
        r73.append(':');
        if (r1 == null) goto L48;
        int r02 = 0;
    L37:
        if (r02 >= r1.length()) goto L48;
        char r2 = r1.charAt(r02);
        if (r2 == '-') goto L45;
        if (r2 == '@') goto L45;
        if (r2 == '.') goto L45;
        r73.append('x');
    L46:
        r02 = r02 + 1;
    L45:
        r73.append(r2);
    L48:
        return r73.toString();
    L7:
        if (r0.equalsIgnoreCase("sip") == true) goto L33;
        if (r0.equalsIgnoreCase("sms") == true) goto L33;
        if (r0.equalsIgnoreCase("smsto") == true) goto L33;
        if (r0.equalsIgnoreCase("mailto") == true) goto L33;
        if (r0.equalsIgnoreCase("nfc") == true) goto L33;
        if (r0.equalsIgnoreCase("http") == false) goto L20;
    L25:
        StringBuilder r12 = new StringBuilder("//");
        String r4 = r7.getHost();
        String r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r4 == null) goto L28;
        String r42 = r7.getHost();
    L29:
        r12.append(r42);
        if (r7.getPort() == (-1)) goto L32;
        r5 = ":" + r7.getPort();
    L32:
        r12.append(r5);
        r12.append("/...");
        r1 = r12.toString();
        goto L49
    L28:
        r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L29
    L20:
        if (r0.equalsIgnoreCase("https") == true) goto L25;
        if (r0.equalsIgnoreCase("ftp") == true) goto L25;
        if (r0.equalsIgnoreCase("rtsp") == false) goto L49;
        goto L25
    }
}
