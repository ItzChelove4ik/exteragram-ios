package androidx.car.app.utils;

/* loaded from: classes3.dex */
public abstract class StringUtils {
    public static String shortenString(String r3) {
        if (r3.length() > 16) goto L6;
        return r3;
    L6:
        return r3.substring(0, 8) + "~" + r3.substring(r3.length() - 8);
    }
}
