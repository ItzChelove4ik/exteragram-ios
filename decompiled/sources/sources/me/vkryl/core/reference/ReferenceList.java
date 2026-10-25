package me.vkryl.core.reference;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class ReferenceList<T> implements Iterable<T>, ReferenceCreator<T> {
    private final boolean cacheIterator;
    private final FullnessListener fullnessListener;
    private boolean isFull;
    private boolean isLocked;
    private final List<Reference<T>> items;
    private final List<Reference<T>> itemsToAdd;
    private final List<Reference<T>> itemsToRemove;
    private ReferenceList<T>.Itr itr;
    ReferenceList<T> next;
    private final Semaphore semaphore;

    /* loaded from: classes5.dex */
    public interface FullnessListener {
        void onFullnessStateChanged(ReferenceList<?> r1, boolean r2);
    }

    public final class Itr implements Iterator<T> {
        private int index;
        private T nextItem;
        final /* synthetic */ ReferenceList this$0;

        /* renamed from: -$$Nest$fputindex, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3596$$Nest$fputindex(Itr r0, int r1) {
            r0.index = r1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: -$$Nest$fputnextItem, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3597$$Nest$fputnextItem(Itr r0, Object r1) {
            r0.nextItem = r1;
        }

        private Itr(ReferenceList r1) {
            this.this$0 = r1;
            this.index = ReferenceList.m3592$$Nest$fgetitems(r1).size();
        }

        public /* synthetic */ Itr(ReferenceList r1, ReferenceListIA r2) {
            this(r1);
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            List r0 = ReferenceList.m3592$$Nest$fgetitems(this.this$0);
            monitor-enter(r0);
            this.nextItem = null;     // Catch: Throwable -> L15
        L7:
            if (this.nextItem != null) goto L18;
            if (this.index <= 0) goto L18;
            List r1 = ReferenceList.m3592$$Nest$fgetitems(this.this$0);     // Catch: Throwable -> L15
            int r3 = this.index - 1;     // Catch: Throwable -> L15
            this.index = r3;     // Catch: Throwable -> L15
            Reference r12 = (Reference) r1.get(r3);     // Catch: Throwable -> L15
            T r32 = (T) r12.get();
            if (r32 == null) goto L7;
            if (ReferenceList.m3593$$Nest$fgetitemsToRemove(this.this$0).contains(r12) == true) goto L7;
            this.nextItem = r32;     // Catch: Throwable -> L15
        L18:
            if (this.nextItem == null) goto L20;
        L22:
            monitor-exit(r0);     // Catch: Throwable -> L15
            if (this.nextItem == null) goto L26;
            return true;
        L26:
            if (ReferenceList.m3594$$Nest$fgetsemaphore(this.this$0) == null) goto L42;
            ReferenceList.m3594$$Nest$fgetsemaphore(this.this$0).release();
            return false;
        L42:
            return false;
        L20:
            if (ReferenceList.m3591$$Nest$fgetcacheIterator(this.this$0) == false) goto L22;
            ReferenceList.m3595$$Nest$munlock(this.this$0);     // Catch: Throwable -> L15
        L15:
            th = move-exception;
            throw th;
        }

        @Override // java.util.Iterator
        public final T next() {
            T r0 = this.nextItem;
            if (r0 == null) goto L5;
            return r0;
        L5:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }
    }

    /* renamed from: -$$Nest$fgetcacheIterator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m3591$$Nest$fgetcacheIterator(ReferenceList r0) {
        return r0.cacheIterator;
    }

    /* renamed from: -$$Nest$fgetitems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m3592$$Nest$fgetitems(ReferenceList r0) {
        return r0.items;
    }

    /* renamed from: -$$Nest$fgetitemsToRemove, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m3593$$Nest$fgetitemsToRemove(ReferenceList r0) {
        return r0.itemsToRemove;
    }

    /* renamed from: -$$Nest$fgetsemaphore, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Semaphore m3594$$Nest$fgetsemaphore(ReferenceList r0) {
        return r0.semaphore;
    }

    /* renamed from: -$$Nest$munlock, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3595$$Nest$munlock(ReferenceList r0) {
        r0.unlock();
    }

    public ReferenceList() {
        this(false, true, null);
    }

    public ReferenceList(boolean r3) {
        this(r3, true, null);
    }

    public ReferenceList(boolean r2, boolean r3, FullnessListener r4) {
        this.itemsToRemove = new ArrayList();
        this.itemsToAdd = new ArrayList();
        if (r2 == false) goto L5;
        Semaphore r22 = new Semaphore(1);
    L6:
        this.semaphore = r22;
        this.cacheIterator = r3;
        this.items = new ArrayList();
        this.fullnessListener = r4;
        return;
    L5:
        r22 = null;
        goto L6
    }

    private void checkFull() {
        if (this.fullnessListener == null) goto L8;
        boolean r0 = !this.items.isEmpty();
        if (this.isFull == r0) goto L9;
        this.isFull = r0;
        this.fullnessListener.onFullnessStateChanged(this, r0);
        return;
    L9:
        return;
    }

    private int indexOf(T r4) {
        if (r4 != null) goto L5;
        return -1;
    L5:
        int r1 = this.items.size() - 1;
    L6:
        if (r1 < 0) goto L11;
        if (this.items.get(r1).get() == r4) goto L9;
        r1 = r1 - 1;
        goto L6
    L9:
        return r1;
    L11:
        return -1;
    }

    private void lock() {
        if (this.isLocked == true) goto L6;
        this.isLocked = true;
        return;
    L6:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }

    private void unlock() {
        if (this.isLocked == false) goto L12;
        this.isLocked = false;
        if (this.itemsToRemove.isEmpty() == true) goto L8;
        this.items.removeAll(this.itemsToRemove);
        this.itemsToRemove.clear();
    L8:
        if (this.itemsToAdd.isEmpty() == true) goto L10;
        this.items.addAll(this.itemsToAdd);
        this.itemsToAdd.clear();
    L10:
        checkFull();
        return;
    L12:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }

    public final boolean add(T r4) {
        List<Reference<T>> r0 = this.items;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (indexOf(r4) == (-1)) goto L12;
        monitor-exit(r0);     // Catch: Throwable -> L9
        return false;
    L12:
        if (this.isLocked == false) goto L16;
        boolean r1 = ReferenceUtils.addReference(this, this.itemsToAdd, r4);     // Catch: Throwable -> L9
        ReferenceUtils.removeReference(this.itemsToRemove, r4);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r1;
    L16:
        Reference<T> r42 = newReference(r4);     // Catch: Throwable -> L9
        this.items.add(r42);     // Catch: Throwable -> L9
        checkFull();     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return true;
    }

    public final void clear() {
        List<Reference<T>> r0 = this.items;
        monitor-enter(r0);
        boolean r1 = this.isLocked;     // Catch: Throwable -> L13
        List<Reference<T>> r2 = this.items;
        if (r1 == false) goto L16;
        Iterator<Reference<T>> r12 = r2.iterator();     // Catch: Throwable -> L13
    L9:
        if (r12.hasNext() == false) goto L17;
        Reference<T> r22 = r12.next();     // Catch: Throwable -> L13
        if (this.itemsToRemove.contains(r22) == true) goto L15;
        this.itemsToRemove.add(r22);     // Catch: Throwable -> L13
    L15:
        ReferenceUtils.removeReference(this.itemsToAdd, r22.get());     // Catch: Throwable -> L13
    L17:
        monitor-exit(r0);     // Catch: Throwable -> L13
        return;
    L16:
        r2.clear();     // Catch: Throwable -> L13
        checkFull();     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        throw th;
    }

    public final boolean isEmpty() {
        List<Reference<T>> r0 = this.items;
        monitor-enter(r0);
        boolean r1 = this.isLocked;     // Catch: Throwable -> L12
        List<Reference<T>> r2 = this.items;
        if (r1 == true) goto L8;
        ReferenceUtils.gcReferenceList(r2);     // Catch: Throwable -> L12
        boolean r3 = this.items.isEmpty();     // Catch: Throwable -> L12
        monitor-exit(r0);     // Catch: Throwable -> L12
        return r3;
    L8:
        if (r2.isEmpty() == true) goto L10;
    L14:
        boolean r32 = false;
    L15:
        monitor-exit(r0);     // Catch: Throwable -> L12
        return r32;
    L10:
        if (this.itemsToAdd.isEmpty() == false) goto L14;
        r32 = true;
    L12:
        th = move-exception;
        throw th;
    }

    @Override // java.lang.Iterable
    public final Iterator<T> iterator() {
        Semaphore r0 = this.semaphore;
        if (r0 != null) goto L32;
    L8:
        List<Reference<T>> r02 = this.items;
        monitor-enter(r02);
    L15:
        th = move-exception;
        throw th;
    L11:
        if (this.cacheIterator == false) goto L22;
        lock();     // Catch: Throwable -> L15
        ReferenceList<T>.Itr r2 = this.itr;     // Catch: Throwable -> L15
        if (r2 != null) goto L17;
        this.itr = new Itr(this, null);     // Catch: Throwable -> L15
    L18:
        ReferenceList<T>.Itr r4 = this.itr;     // Catch: Throwable -> L15
        monitor-exit(r02);     // Catch: Throwable -> L15
        return r4;
    L17:
        Itr.m3596$$Nest$fputindex(r2, this.items.size());     // Catch: Throwable -> L15
        Itr.m3597$$Nest$fputnextItem(this.itr, null);     // Catch: Throwable -> L15
        goto L18
    L22:
        if (this.items.isEmpty() == false) goto L26;
        Iterator<T> r42 = Collections.emptyIterator();     // Catch: Throwable -> L15
        monitor-exit(r02);     // Catch: Throwable -> L15
        return r42;
    L26:
        Itr r22 = new Itr(this, null);     // Catch: Throwable -> L15
        monitor-exit(r02);     // Catch: Throwable -> L15
        return r22;
    L32:
        r0.acquire();     // Catch: InterruptedException -> L6
    L6:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    public final boolean remove(T r4) {
        List<Reference<T>> r0 = this.items;
        monitor-enter(r0);
        int r42 = indexOf(r4);     // Catch: Throwable -> L9
        if (r42 == (-1)) goto L7;
        boolean r1 = this.isLocked;     // Catch: Throwable -> L9
        List<Reference<T>> r2 = this.items;
        if (r1 == false) goto L18;
        Reference<T> r43 = r2.get(r42);     // Catch: Throwable -> L9
        if (this.itemsToRemove.contains(r43) == true) goto L17;
        this.itemsToRemove.add(r43);     // Catch: Throwable -> L9
    L17:
        ReferenceUtils.removeReference(this.itemsToAdd, r43.get());     // Catch: Throwable -> L9
    L20:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return true;
    L18:
        r2.remove(r42);     // Catch: Throwable -> L9
        checkFull();     // Catch: Throwable -> L9
        goto L20
    L7:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return false;
    L9:
        th = move-exception;
        throw th;
    }
}
