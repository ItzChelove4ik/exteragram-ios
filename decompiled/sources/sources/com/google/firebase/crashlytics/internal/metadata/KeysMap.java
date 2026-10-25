package com.google.firebase.crashlytics.internal.metadata;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
class KeysMap {
    private final Map<String, String> keys;
    private final int maxEntries;
    private final int maxEntryLength;

    public KeysMap(int r2, int r3) {
        this.keys = new HashMap();
        this.maxEntries = r2;
        this.maxEntryLength = r3;
    }

    private String sanitizeKey(String r1) {
        if (r1 != null) goto L4;
        f$$ExternalSyntheticBUOutline1.m("Custom attribute key must not be null.");
        return null;
    L4:
        return sanitizeString(r1, this.maxEntryLength);
    }

    public static String sanitizeString(String r1, int r2) {
        if (r1 == null) goto L7;
        String r12 = r1.trim();
        if (r12.length() > r2) goto L6;
        return r12;
    L6:
        return r12.substring(0, r2);
    L7:
        return r1;
    }

    public synchronized Map<String, String> getKeys() {
        monitor-enter(this);
        Map<String, String> r0 = Collections.unmodifiableMap(new HashMap(this.keys));     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized boolean setKey(String r6, String r7) {
        monitor-enter(this);
        String r1 = sanitizeKey(r6);     // Catch: Throwable -> L12
        if (this.keys.size() >= this.maxEntries) goto L7;
    L14:
        String r62 = sanitizeString(r7, this.maxEntryLength);     // Catch: Throwable -> L12
        if (CommonUtils.nullSafeEquals(this.keys.get(r1), r62) == false) goto L18;
        monitor-exit(this);
        return false;
    L18:
        Map<String, String> r0 = this.keys;     // Catch: Throwable -> L12
        if (r7 != null) goto L21;
        r62 = _UrlKt.FRAGMENT_ENCODE_SET;
    L21:
        r0.put(r1, r62);     // Catch: Throwable -> L12
        monitor-exit(this);
        return true;
    L7:
        if (this.keys.containsKey(r1) == true) goto L14;
        Logger.getLogger().w("Ignored entry \"" + r6 + "\" when adding custom keys. Maximum allowable: " + this.maxEntries);     // Catch: Throwable -> L12
        monitor-exit(this);
        return false;
    L12:
        th = move-exception;
        throw th;
    }

    public synchronized void setKeys(Map<String, String> r6) {
        monitor-enter(this);
        Iterator<Map.Entry<String, String>> r62 = r6.entrySet().iterator();     // Catch: Throwable -> L12
        int r0 = 0;
    L5:
        if (r62.hasNext() == false) goto L19;
        Map.Entry<String, String> r1 = r62.next();     // Catch: Throwable -> L12
        String r2 = sanitizeKey(r1.getKey());     // Catch: Throwable -> L12
        if (this.keys.size() < this.maxEntries) goto L14;
        if (this.keys.containsKey(r2) == true) goto L14;
        r0 = r0 + 1;     // Catch: Throwable -> L12
    L14:
        String r12 = r1.getValue();     // Catch: Throwable -> L12
        Map<String, String> r3 = this.keys;     // Catch: Throwable -> L12
        if (r12 != null) goto L17;
        String r13 = _UrlKt.FRAGMENT_ENCODE_SET;
    L18:
        r3.put(r2, r13);     // Catch: Throwable -> L12
        goto L5
    L17:
        r13 = sanitizeString(r12, this.maxEntryLength);     // Catch: Throwable -> L12
        goto L18
    L19:
        if (r0 <= 0) goto L21;
        Logger.getLogger().w("Ignored " + r0 + " entries when adding custom keys. Maximum allowable: " + this.maxEntries);     // Catch: Throwable -> L12
    L21:
        monitor-exit(this);
        return;
    L12:
        th = move-exception;
        throw th;
    }
}
