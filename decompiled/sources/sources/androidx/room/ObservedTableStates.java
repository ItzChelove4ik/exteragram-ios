package androidx.room;

import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\u0018\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001:\u0001\"B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\f\u0010\nJ\u000f\u0010\u0011\u001a\u00020\u000eH\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0013\u001a\u00020\u000eH\u0000¢\u0006\u0004\b\u0012\u0010\u0010R\u0018\u0010\u0016\u001a\u00060\u0014j\u0002`\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0018\u0010 \u001a\u00060\u0014j\u0002`\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010\u0017R\u0016\u0010!\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b!\u0010\u001f¨\u0006#"}, d2 = {"Landroidx/room/ObservedTableStates;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "size", "<init>", "(I)V", _UrlKt.FRAGMENT_ENCODE_SET, "tableIds", _UrlKt.FRAGMENT_ENCODE_SET, "onObserverAdded$room_runtime", "([I)Z", "onObserverAdded", "onObserverRemoved$room_runtime", "onObserverRemoved", _UrlKt.FRAGMENT_ENCODE_SET, "resetTriggerState$room_runtime", "()V", "resetTriggerState", "forceNeedSync$room_runtime", "forceNeedSync", "Ljava/util/concurrent/locks/ReentrantLock;", "Landroidx/room/concurrent/ReentrantLock;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", _UrlKt.FRAGMENT_ENCODE_SET, "tableObserversCount", "[J", _UrlKt.FRAGMENT_ENCODE_SET, "tableObservedState", "[Z", "needsSync", "Z", "onSyncLock", "inProgressSync", "ObserveOp", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nInvalidationTracker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InvalidationTracker.kt\nandroidx/room/ObservedTableStates\n+ 2 ReentrantLock.kt\nandroidx/room/concurrent/ReentrantLockKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,640:1\n28#2,3:641\n28#2,5:644\n32#2:649\n28#2,3:650\n32#2:655\n28#2,3:656\n32#2:661\n28#2,5:662\n28#2,5:667\n13493#3,2:653\n13493#3,2:659\n*S KotlinDebug\n*F\n+ 1 InvalidationTracker.kt\nandroidx/room/ObservedTableStates\n*L\n526#1:641,3\n529#1:644,5\n526#1:649\n563#1:650,3\n563#1:655\n581#1:656,3\n581#1:661\n595#1:662,5\n601#1:667,5\n565#1:653,2\n583#1:659,2\n*E\n"})
/* loaded from: classes.dex */
public final class ObservedTableStates {
    private volatile boolean inProgressSync;
    private final ReentrantLock lock;
    private volatile boolean needsSync;
    private final ReentrantLock onSyncLock;
    private final boolean[] tableObservedState;
    private final long[] tableObserversCount;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/room/ObservedTableStates$ObserveOp;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "NO_OP", "ADD", "REMOVE", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum ObserveOp extends Enum<ObserveOp> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ ObserveOp[] $VALUES = null;
        public static final ObserveOp ADD = null;
        public static final ObserveOp NO_OP = null;
        public static final ObserveOp REMOVE = null;

        private static final /* synthetic */ ObserveOp[] $values() {
            return new ObserveOp[]{NO_OP, ADD, REMOVE};
        }

        static {
            NO_OP = new ObserveOp("NO_OP", 0);
            ADD = new ObserveOp("ADD", 1);
            REMOVE = new ObserveOp("REMOVE", 2);
            ObserveOp[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        ObserveOp(String r1, int r2) {
        }

        public static ObserveOp valueOf(String r1) {
            return (ObserveOp) Enum.valueOf(ObserveOp.class, r1);
        }

        public static ObserveOp[] values() {
            return (ObserveOp[]) $VALUES.clone();
        }
    }

    public ObservedTableStates(int r2) {
        this.lock = new ReentrantLock();
        this.tableObserversCount = new long[r2];
        this.tableObservedState = new boolean[r2];
        this.onSyncLock = new ReentrantLock();
    }

    public static final /* synthetic */ ReentrantLock access$getLock$p(ObservedTableStates r0) {
        return r0.lock;
    }

    public static final /* synthetic */ boolean access$getNeedsSync$p(ObservedTableStates r0) {
        return r0.needsSync;
    }

    public static final /* synthetic */ ReentrantLock access$getOnSyncLock$p(ObservedTableStates r0) {
        return r0.onSyncLock;
    }

    public static final /* synthetic */ boolean[] access$getTableObservedState$p(ObservedTableStates r0) {
        return r0.tableObservedState;
    }

    public static final /* synthetic */ long[] access$getTableObserversCount$p(ObservedTableStates r0) {
        return r0.tableObserversCount;
    }

    public static final /* synthetic */ void access$setInProgressSync$p(ObservedTableStates r0, boolean r1) {
        r0.inProgressSync = r1;
    }

    public static final /* synthetic */ void access$setNeedsSync$p(ObservedTableStates r0, boolean r1) {
        r0.needsSync = r1;
    }

    public final void forceNeedSync$room_runtime() {
        ReentrantLock r0 = this.lock;
        r0.lock();
        this.needsSync = true;     // Catch: Throwable -> L6
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L6
        r0.unlock();
        return;
    L6:
        th = move-exception;
        r0.unlock();
        throw th;
    }

    public final boolean onObserverAdded$room_runtime(int[] r13) {
        ReentrantLock r0 = this.lock;
        r0.lock();
        int r1 = r13.length;     // Catch: Throwable -> L9
        boolean r2 = false;
        int r3 = 0;
        boolean r4 = false;
    L5:
        if (r3 >= r1) goto L12;
        int r6 = r13[r3];     // Catch: Throwable -> L9
        long[] r7 = this.tableObserversCount;     // Catch: Throwable -> L9
        long r8 = r7[r6];     // Catch: Throwable -> L9
        r7[r6] = 1 + r8;     // Catch: Throwable -> L9
        if (r8 != 0) goto L11;
        this.needsSync = true;     // Catch: Throwable -> L9
        r4 = true;
    L11:
        r3 = r3 + 1;     // Catch: Throwable -> L9
        goto L5
    L12:
        if (r4 == false) goto L14;
    L17:
        r2 = true;
    L18:
        r0.unlock();
        return r2;
    L14:
        if (this.needsSync == true) goto L17;
        if (this.inProgressSync == false) goto L18;
    L9:
        th = move-exception;
        r0.unlock();
        throw th;
    }

    public final boolean onObserverRemoved$room_runtime(int[] r15) {
        ReentrantLock r0 = this.lock;
        r0.lock();
        int r1 = r15.length;     // Catch: Throwable -> L9
        boolean r2 = false;
        int r3 = 0;
        boolean r4 = false;
    L5:
        if (r3 >= r1) goto L12;
        int r6 = r15[r3];     // Catch: Throwable -> L9
        long[] r7 = this.tableObserversCount;     // Catch: Throwable -> L9
        long r8 = r7[r6];     // Catch: Throwable -> L9
        r7[r6] = r8 - 1;     // Catch: Throwable -> L9
        if (r8 != 1) goto L11;
        this.needsSync = true;     // Catch: Throwable -> L9
        r4 = true;
    L11:
        r3 = r3 + 1;     // Catch: Throwable -> L9
        goto L5
    L12:
        if (r4 == false) goto L14;
    L17:
        r2 = true;
    L18:
        r0.unlock();
        return r2;
    L14:
        if (this.needsSync == true) goto L17;
        if (this.inProgressSync == false) goto L18;
    L9:
        th = move-exception;
        r0.unlock();
        throw th;
    }

    public final void resetTriggerState$room_runtime() {
        ReentrantLock r1 = this.lock;
        r1.lock();
        ArraysKt.fill$default(this.tableObservedState, false, 0, 0, 6, null);     // Catch: Throwable -> L6
        this.needsSync = true;     // Catch: Throwable -> L6
        Unit r8 = Unit.INSTANCE;     // Catch: Throwable -> L6
        r1.unlock();
        return;
    L6:
        th = move-exception;
        r1.unlock();
        throw th;
    }
}
