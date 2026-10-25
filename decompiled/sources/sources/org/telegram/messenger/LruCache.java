package org.telegram.messenger;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class LruCache<T> {
    private final LinkedHashMap<String, T> map;
    private final LinkedHashMap<String, ArrayList<String>> mapFilters;
    private int maxSize;
    private int size;

    public LruCache(int r4) {
        if (r4 <= 0) goto L6;
        this.maxSize = r4;
        this.map = new LinkedHashMap(0, 0.75f, true);
        this.mapFilters = new LinkedHashMap();
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("maxSize <= 0");
        throw null;
    }

    private int safeSizeOf(String r3, T r4) {
        int r2 = sizeOf(r3, r4);
        if (r2 < 0) goto L6;
        return r2;
    L6:
        throw new IllegalStateException("Negative size: " + r3 + "=" + r4);
    }

    private void trimToSize(int r9, String r10) {
        monitor-enter(this);
        Iterator<Map.Entry<String, T>> r0 = this.map.entrySet().iterator();     // Catch: Throwable -> L16
    L5:
        if (r0.hasNext() == false) goto L26;
        if (this.size <= r9) goto L26;
        if (this.map.isEmpty() == true) goto L26;
        Map.Entry<String, T> r1 = r0.next();     // Catch: Throwable -> L16
        String r2 = r1.getKey();     // Catch: Throwable -> L16
        if (r10 == null) goto L18;
        if (r10.equals(r2) == true) goto L5;
    L18:
        T r12 = r1.getValue();     // Catch: Throwable -> L16
        this.size -= safeSizeOf(r2, r12);
        r0.remove();     // Catch: Throwable -> L16
        String[] r3 = r2.split("@");     // Catch: Throwable -> L16
        if (r3.length <= 1) goto L25;
        ArrayList<String> r4 = this.mapFilters.get(r3[0]);     // Catch: Throwable -> L16
        if (r4 == null) goto L25;
        r4.remove(r3[1]);     // Catch: Throwable -> L16
        if (r4.isEmpty() == false) goto L25;
        this.mapFilters.remove(r3[0]);     // Catch: Throwable -> L16
    L25:
        entryRemoved(true, r2, r12, null);     // Catch: Throwable -> L16
    L26:
        monitor-exit(this);     // Catch: Throwable -> L16
        return;
    L16:
        th = move-exception;
        throw th;
    }

    public synchronized boolean contains(String r2) {
        monitor-enter(this);
        boolean r22 = this.map.containsKey(r2);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r22;
    L6:
        th = move-exception;
        throw th;
    }

    public void entryRemoved(boolean r1, String r2, T r3, T r4) {
    }

    public final synchronized Set<Map.Entry<String, T>> entrySet() {
        monitor-enter(this);
        Set<Map.Entry<String, T>> r0 = this.map.entrySet();     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final void evictAll() {
        trimToSize(-1, null);
    }

    public final T get(String r3) {
        if (r3 == null) goto L15;
        monitor-enter(this);
        T r32 = this.map.get(r3);     // Catch: Throwable -> L9
        if (r32 == null) goto L11;
        monitor-exit(this);     // Catch: Throwable -> L9
        return r32;
    L11:
        monitor-exit(this);     // Catch: Throwable -> L9
        return null;
    L9:
        th = move-exception;
        throw th;
    L15:
        f$$ExternalSyntheticBUOutline2.m("key == null");
        return null;
    }

    public synchronized ArrayList<String> getFilterKeys(String r2) {
        monitor-enter(this);
        ArrayList<String> r22 = this.mapFilters.get(r2);     // Catch: Throwable -> L8
        if (r22 == null) goto L10;
        ArrayList<String> r0 = new ArrayList(r22);     // Catch: Throwable -> L8
        monitor-exit(this);
        return r0;
    L10:
        monitor-exit(this);
        return null;
    L8:
        th = move-exception;
        throw th;
    }

    public final synchronized int maxSize() {
        monitor-enter(this);
        int r0 = this.maxSize;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized void moveToFront(String r3) {
        monitor-enter(this);
        T r0 = this.map.remove(r3);     // Catch: Throwable -> L7
        if (r0 == null) goto L9;
        this.map.put(r3, r0);     // Catch: Throwable -> L7
    L9:
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public T put(String r8, T r9) {
        if (r8 == null) goto L25;
        if (r9 == null) goto L25;
        monitor-enter(this);
        this.size += safeSizeOf(r8, r9);
        T r0 = this.map.put(r8, r9);     // Catch: Throwable -> L8
        if (r0 == null) goto L10;
        this.size -= safeSizeOf(r8, r0);
    L10:
        String[] r1 = r8.split("@");     // Catch: Throwable -> L8
        if (r1.length <= 1) goto L18;
        ArrayList<String> r2 = this.mapFilters.get(r1[0]);     // Catch: Throwable -> L8
        if (r2 != null) goto L16;
        r2 = new ArrayList();     // Catch: Throwable -> L8
        this.mapFilters.put(r1[0], r2);     // Catch: Throwable -> L8
    L16:
        if (r2.contains(r1[1]) == true) goto L18;
        r2.add(r1[1]);     // Catch: Throwable -> L8
    L18:
        monitor-exit(this);     // Catch: Throwable -> L8
        if (r0 == null) goto L21;
        entryRemoved(false, r8, r0, r9);
    L21:
        trimToSize(this.maxSize, r8);
        return r0;
    L8:
        th = move-exception;
        throw th;
    L25:
        f$$ExternalSyntheticBUOutline2.m("key == null || value == null");
        return null;
    }

    public final T remove(String r8) {
        if (r8 == null) goto L22;
        monitor-enter(this);
        T r1 = this.map.remove(r8);     // Catch: Throwable -> L14
        if (r1 == null) goto L16;
        this.size -= safeSizeOf(r8, r1);
        String[] r3 = r8.split("@");     // Catch: Throwable -> L14
        if (r3.length <= 1) goto L16;
        ArrayList<String> r4 = this.mapFilters.get(r3[0]);     // Catch: Throwable -> L14
        if (r4 == null) goto L16;
        r4.remove(r3[1]);     // Catch: Throwable -> L14
        if (r4.isEmpty() == false) goto L16;
        this.mapFilters.remove(r3[0]);     // Catch: Throwable -> L14
    L16:
        monitor-exit(this);     // Catch: Throwable -> L14
        if (r1 == null) goto L19;
        entryRemoved(false, r8, r1, null);
    L19:
        return r1;
    L14:
        th = move-exception;
        throw th;
    L22:
        f$$ExternalSyntheticBUOutline2.m("key == null");
        return null;
    }

    public final synchronized int size() {
        monitor-enter(this);
        int r0 = this.size;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public int sizeOf(String r1, T r2) {
        return 1;
    }
}
