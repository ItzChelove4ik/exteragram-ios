package io.noties.markwon.html.jsoup.helper;

import java.util.Locale;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public abstract class Normalizer {
    public static String lowerCase(String r1) {
        if (r1 != null) goto L4;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L4:
        return r1.toLowerCase(Locale.ENGLISH);
    }
}
