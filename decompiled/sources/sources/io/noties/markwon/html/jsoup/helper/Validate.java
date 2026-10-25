package io.noties.markwon.html.jsoup.helper;

import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public abstract class Validate {
    public static void fail(String r1) {
        throw new IllegalArgumentException(r1);
    }

    public static void isFalse(boolean r0) {
        if (r0 == true) goto L4;
        return;
    L4:
        f$$ExternalSyntheticBUOutline1.m("Must be false");
    }

    public static void isFalse(boolean r0, String r1) {
        if (r0 == true) goto L4;
        return;
    L4:
        f$$ExternalSyntheticBUOutline1.m(r1);
    }

    public static void isTrue(boolean r0) {
        if (r0 == false) goto L4;
        return;
    L4:
        f$$ExternalSyntheticBUOutline1.m("Must be true");
    }

    public static void notEmpty(String r0) {
        if (r0 != null) goto L4;
    L6:
        f$$ExternalSyntheticBUOutline1.m("String must not be empty");
        return;
    L4:
        if (r0.length() == 0) goto L6;
    }

    public static void notNull(Object r0) {
        if (r0 == null) goto L4;
        return;
    L4:
        f$$ExternalSyntheticBUOutline1.m("Object must not be null");
    }
}
