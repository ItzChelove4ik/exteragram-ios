package io.noties.markwon.html;

import java.io.IOException;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
abstract class AppendableUtils {
    public static void appendQuietly(Appendable r0, char r1) {
        r0.append(r1);     // Catch: IOException -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }

    public static void appendQuietly(Appendable r0, CharSequence r1) {
        r0.append(r1);     // Catch: IOException -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }
}
