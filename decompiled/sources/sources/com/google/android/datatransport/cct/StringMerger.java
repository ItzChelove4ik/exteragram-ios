package com.google.android.datatransport.cct;

import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public abstract class StringMerger {
    public static String mergeStrings(String r3, String r4) {
        int r0 = r3.length() - r4.length();
        if (r0 >= 0) goto L5;
    L15:
        f$$ExternalSyntheticBUOutline1.m("Invalid input received");
        return null;
    L5:
        if (r0 > 1) goto L15;
        StringBuilder r02 = new StringBuilder(r3.length() + r4.length());
        int r1 = 0;
    L8:
        if (r1 >= r3.length()) goto L14;
        r02.append(r3.charAt(r1));
        if (r4.length() <= r1) goto L12;
        r02.append(r4.charAt(r1));
    L12:
        r1 = r1 + 1;
        goto L8
    L14:
        return r02.toString();
    }
}
