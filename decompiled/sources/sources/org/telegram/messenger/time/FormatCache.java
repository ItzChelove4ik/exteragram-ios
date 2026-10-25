package org.telegram.messenger.time;

import c.f$$ExternalSyntheticBUOutline2;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes.dex */
abstract class FormatCache<F extends Format> {
    static final int NONE = -1;
    private static final ConcurrentMap<MultipartKey, String> cDateTimeInstanceCache = null;
    private final ConcurrentMap<MultipartKey, F> cInstanceCache;

    public static class MultipartKey {
        private int hashCode;
        private final Object[] keys;

        public MultipartKey(Object... r1) {
            this.keys = r1;
        }

        public boolean equals(Object r1) {
            return Arrays.equals(this.keys, ((MultipartKey) r1).keys);
        }

        public int hashCode() {
            if (this.hashCode != 0) goto L12;
            Object[] r0 = this.keys;
            int r1 = r0.length;
            int r2 = 0;
            int r3 = 0;
        L5:
            if (r2 >= r1) goto L10;
            Object r4 = r0[r2];
            if (r4 == null) goto L9;
            r3 = (r3 * 7) + r4.hashCode();
        L9:
            r2 = r2 + 1;
            goto L5
        L10:
            this.hashCode = r3;
        L12:
            return this.hashCode;
        }
    }

    static {
        cDateTimeInstanceCache = new ConcurrentHashMap(7);
    }

    public FormatCache() {
        this.cInstanceCache = new ConcurrentHashMap(7);
    }

    private F getDateTimeInstance(Integer r1, Integer r2, TimeZone r3, Locale r4) {
        if (r4 != null) goto L5;
        r4 = Locale.getDefault();
    L5:
        return (F) getInstance(getPatternForStyle(r1, r2, r4), r3, r4);
    }

    public static String getPatternForStyle(Integer r3, Integer r4, Locale r5) {
        MultipartKey r0 = new MultipartKey(new Object[]{r3, r4, r5});
        ConcurrentMap<MultipartKey, String> r1 = cDateTimeInstanceCache;
        String r2 = r1.get(r0);
        if (r2 == null) goto L16;
        return r2;
    L16:
        if (r3 != null) goto L6;
        DateFormat r32 = DateFormat.getTimeInstance(r4.intValue(), r5);     // Catch: ClassCastException -> L13
    L9:
        String r33 = ((SimpleDateFormat) r32).toPattern();     // Catch: ClassCastException -> L13
        String r42 = r1.putIfAbsent(r0, r33);     // Catch: ClassCastException -> L13
        if (r42 == null) goto L12;
        return r42;
    L12:
        return r33;
    L6:
        if (r4 != null) goto L8;
        r32 = DateFormat.getDateInstance(r3.intValue(), r5);     // Catch: ClassCastException -> L13
        goto L9
    L8:
        r32 = DateFormat.getDateTimeInstance(r3.intValue(), r4.intValue(), r5);     // Catch: ClassCastException -> L13
    L13:
        Native$$ExternalSyntheticBUOutline5.m("No date time pattern for locale: ", r5);
        return null;
    }

    public abstract F createInstance(String r1, TimeZone r2, Locale r3);

    public F getDateInstance(int r2, TimeZone r3, Locale r4) {
        return (F) getDateTimeInstance(Integer.valueOf(r2), null, r3, r4);
    }

    public F getDateTimeInstance(int r1, int r2, TimeZone r3, Locale r4) {
        return (F) getDateTimeInstance(Integer.valueOf(r1), Integer.valueOf(r2), r3, r4);
    }

    public F getInstance() {
        return (F) getDateTimeInstance(3, 3, TimeZone.getDefault(), Locale.getDefault());
    }

    public F getInstance(String r3, TimeZone r4, Locale r5) {
        if (r3 == null) goto L14;
        if (r4 != null) goto L5;
        r4 = TimeZone.getDefault();
    L5:
        if (r5 != null) goto L7;
        r5 = Locale.getDefault();
    L7:
        MultipartKey r0 = new MultipartKey(new Object[]{r3, r4, r5});
        F r1 = this.cInstanceCache.get(r0);
        if (r1 != null) goto L13;
        F r32 = (F) createInstance(r3, r4, r5);
        F r2 = (F) this.cInstanceCache.putIfAbsent(r0, r32);
        if (r2 == null) goto L12;
        return r2;
    L12:
        return r32;
    L13:
        return r1;
    L14:
        f$$ExternalSyntheticBUOutline2.m("pattern must not be null");
        return null;
    }

    public F getTimeInstance(int r2, TimeZone r3, Locale r4) {
        return (F) getDateTimeInstance(null, Integer.valueOf(r2), r3, r4);
    }
}
