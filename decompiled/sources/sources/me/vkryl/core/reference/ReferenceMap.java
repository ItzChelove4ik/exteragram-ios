package me.vkryl.core.reference;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import me.vkryl.core.reference.ReferenceList;

/* loaded from: classes.dex */
public class ReferenceMap<K, T> {
    private final boolean cacheIterator;
    private int fullnessCounter;
    private final ReferenceList.FullnessListener fullnessListenerHelper;
    private final boolean isThreadSafe;
    protected final Map<K, ReferenceList<T>> map;
    private ReferenceList<T> reuse;

    /* loaded from: classes5.dex */
    public interface FullnessListener<KK, TT> {
        void onFullnessStateChanged(ReferenceMap<KK, TT> r1, boolean r2);
    }

    /* renamed from: $r8$lambda$gHuhD3XXW3-9azdMVoAo5tjuxFY, reason: not valid java name */
    public static /* synthetic */ void m3598$r8$lambda$gHuhD3XXW39azdMVoAo5tjuxFY(ReferenceMap r0, FullnessListener r1, ReferenceList r2, boolean r3) {
        r0.lambda$new$0(r1, r2, r3);
    }

    public ReferenceMap(boolean r3) {
        this(r3, true, null);
    }

    public ReferenceMap(boolean r2, boolean r3, FullnessListener<K, T> r4) {
        this.map = new HashMap();
        this.isThreadSafe = r2;
        this.cacheIterator = r3;
        if (r4 == null) goto L6;
        this.fullnessListenerHelper = new ReferenceMap$$ExternalSyntheticLambda0(this, r4);
        return;
    L6:
        this.fullnessListenerHelper = null;
    }

    private /* synthetic */ void lambda$new$0(FullnessListener r2, ReferenceList r3, boolean r4) {
        monitor-enter(r2);
        int r32 = this.fullnessCounter;
        if (r4 == true) goto L6;
        int r33 = r32 - 1;     // Catch: Throwable -> L9
        this.fullnessCounter = r33;     // Catch: Throwable -> L9
        if (r33 != 0) goto L14;
        r2.onFullnessStateChanged(this, false);     // Catch: Throwable -> L9
    L14:
        monitor-exit(r2);     // Catch: Throwable -> L9
        return;
    L6:
        this.fullnessCounter = r32 + 1;     // Catch: Throwable -> L9
        if (r32 != 0) goto L14;
        r2.onFullnessStateChanged(this, true);     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }

    public final boolean add(K r6, T r7) {
        Map<K, ReferenceList<T>> r0 = this.map;
        monitor-enter(r0);
        ReferenceList<T> r1 = this.map.get(r6);     // Catch: Throwable -> L9
        if (r1 != null) goto L13;
        r1 = this.reuse;     // Catch: Throwable -> L9
        if (r1 == null) goto L11;
        this.reuse = r1.next;     // Catch: Throwable -> L9
        r1.next = null;     // Catch: Throwable -> L9
    L12:
        this.map.put(r6, r1);     // Catch: Throwable -> L9
        goto L13
    L11:
        r1 = new ReferenceList(this.isThreadSafe, this.cacheIterator, this.fullnessListenerHelper);     // Catch: Throwable -> L9
    L13:
        boolean r5 = r1.add(r7);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r5;
    L9:
        th = move-exception;
        throw th;
    }

    public final boolean has(K r2) {
        Map<K, ReferenceList<T>> r0 = this.map;
        monitor-enter(r0);
        ReferenceList<T> r1 = this.map.get(r2);     // Catch: Throwable -> L9
        if (r1 != null) goto L7;
    L11:
        boolean r12 = false;
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r12;
    L7:
        if (r1.isEmpty() == true) goto L11;
        r12 = true;
    L9:
        th = move-exception;
        throw th;
    }

    public final Iterator<T> iterator(K r2) {
        Map<K, ReferenceList<T>> r0 = this.map;
        monitor-enter(r0);
        ReferenceList<T> r1 = this.map.get(r2);     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        Iterator<T> r12 = r1.iterator();     // Catch: Throwable -> L7
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r12;
    L9:
        r12 = null;
    L7:
        th = move-exception;
        throw th;
    }

    public final Set<K> keySetUnchecked() {
        if (this.map.isEmpty() == false) goto L7;
        return null;
    L7:
        return this.map.keySet();
    }

    public final void remove(K r3, T r4) {
        Map<K, ReferenceList<T>> r0 = this.map;
        monitor-enter(r0);
        ReferenceList<T> r1 = this.map.get(r3);     // Catch: Throwable -> L9
        if (r1 == null) goto L11;
        r1.remove(r4);     // Catch: Throwable -> L9
        if (r1.isEmpty() == false) goto L11;
        this.map.remove(r3);     // Catch: Throwable -> L9
        r1.next = this.reuse;     // Catch: Throwable -> L9
        this.reuse = r1;     // Catch: Throwable -> L9
    L11:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        throw th;
    }
}
