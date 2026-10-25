package androidx.media3.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public final class CopyOnWriteMultiset<E> implements Iterable<E> {
    private final Map<E, Integer> elementCounts;
    private Set<E> elementSet;
    private List<E> elements;
    private final Object lock;

    public CopyOnWriteMultiset() {
        this.lock = new Object();
        this.elementCounts = new HashMap();
        this.elementSet = Collections.EMPTY_SET;
        this.elements = Collections.EMPTY_LIST;
    }

    public void add(E r5) {
        Object r0 = this.lock;
        monitor-enter(r0);
        ArrayList r1 = new ArrayList(this.elements);     // Catch: Throwable -> L7
        r1.add(r5);     // Catch: Throwable -> L7
        this.elements = Collections.unmodifiableList(r1);     // Catch: Throwable -> L7
        Integer r12 = this.elementCounts.get(r5);     // Catch: Throwable -> L7
        if (r12 != null) goto L9;
        HashSet r2 = new HashSet(this.elementSet);     // Catch: Throwable -> L7
        r2.add(r5);     // Catch: Throwable -> L7
        this.elementSet = Collections.unmodifiableSet(r2);     // Catch: Throwable -> L7
    L9:
        Map<E, Integer> r4 = this.elementCounts;     // Catch: Throwable -> L7
        int r22 = 1;
        if (r12 == null) goto L12;
        r22 = 1 + r12.intValue();     // Catch: Throwable -> L7
    L12:
        r4.put(r5, Integer.valueOf(r22));     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public int count(E r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.elementCounts.containsKey(r3) == false) goto L9;
        int r2 = this.elementCounts.get(r3).intValue();     // Catch: Throwable -> L7
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r2;
    L9:
        r2 = 0;
        goto L10
    }

    public Set<E> elementSet() {
        Object r0 = this.lock;
        monitor-enter(r0);
        Set<E> r1 = this.elementSet;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // java.lang.Iterable
    public Iterator<E> iterator() {
        Object r0 = this.lock;
        monitor-enter(r0);
        Iterator<E> r1 = this.elements.iterator();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public void remove(E r6) {
        Object r0 = this.lock;
        monitor-enter(r0);
        Integer r1 = this.elementCounts.get(r6);     // Catch: Throwable -> L8
        if (r1 != null) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        ArrayList r2 = new ArrayList(this.elements);     // Catch: Throwable -> L8
        r2.remove(r6);     // Catch: Throwable -> L8
        this.elements = Collections.unmodifiableList(r2);     // Catch: Throwable -> L8
        int r22 = r1.intValue();     // Catch: Throwable -> L8
        Map<E, Integer> r3 = this.elementCounts;
        if (r22 != 1) goto L14;
        r3.remove(r6);     // Catch: Throwable -> L8
        HashSet r12 = new HashSet(this.elementSet);     // Catch: Throwable -> L8
        r12.remove(r6);     // Catch: Throwable -> L8
        this.elementSet = Collections.unmodifiableSet(r12);     // Catch: Throwable -> L8
    L15:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L14:
        r3.put(r6, Integer.valueOf(r1.intValue() - 1));     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }
}
