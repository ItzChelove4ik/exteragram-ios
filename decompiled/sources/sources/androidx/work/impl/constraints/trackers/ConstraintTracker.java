package androidx.work.impl.constraints.trackers;

import android.content.Context;
import androidx.work.Logger;
import androidx.work.impl.constraints.ConstraintListener;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0019\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fJ\u0014\u0010\u0016\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fJ\r\u0010\u001d\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u001aJ\b\u0010\u001e\u001a\u00020\u0014H&J\b\u0010\u001f\u001a\u00020\u0014H&R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\r\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f0\u000ej\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f`\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0012R$\u0010\u0018\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00028\u00008F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006 "}, d2 = {"Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Landroid/content/Context;", "taskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "<init>", "(Landroid/content/Context;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;)V", "appContext", "getAppContext", "()Landroid/content/Context;", "lock", "listeners", "Ljava/util/LinkedHashSet;", "Landroidx/work/impl/constraints/ConstraintListener;", "Lkotlin/collections/LinkedHashSet;", "currentState", "Ljava/lang/Object;", "addListener", _UrlKt.FRAGMENT_ENCODE_SET, "listener", "removeListener", "newState", "state", "getState", "()Ljava/lang/Object;", "setState", "(Ljava/lang/Object;)V", "readSystemState", "startTracking", "stopTracking", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nConstraintTracker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConstraintTracker.kt\nandroidx/work/impl/constraints/trackers/ConstraintTracker\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,112:1\n1869#2,2:113\n*S KotlinDebug\n*F\n+ 1 ConstraintTracker.kt\nandroidx/work/impl/constraints/trackers/ConstraintTracker\n*L\n89#1:113,2\n*E\n"})
/* loaded from: classes.dex */
public abstract class ConstraintTracker<T> {
    private final Context appContext;
    private T currentState;
    private final LinkedHashSet<ConstraintListener<T>> listeners;
    private final Object lock;
    private final TaskExecutor taskExecutor;

    public static void $r8$lambda$pWD0wJBuCqmpYo4cA6wnFke6RV0(List r2, ConstraintTracker r3) {
        Iterator<T> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        ((ConstraintListener) r22.next()).onConstraintChanged(r3.currentState);
        goto L4
    }

    public ConstraintTracker(Context r1, TaskExecutor r2) {
        this.taskExecutor = r2;
        this.appContext = r1.getApplicationContext();
        this.lock = new Object();
        this.listeners = new LinkedHashSet();
    }

    public final void addListener(ConstraintListener<T> r6) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.listeners.add(r6) == true) goto L7;
    L12:
        Unit r5 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r0);
        return;
    L7:
        if (this.listeners.size() != 1) goto L11;
        this.currentState = readSystemState();     // Catch: Throwable -> L9
        Logger.get().debug(ConstraintTrackerKt.access$getTAG$p(), getClass().getSimpleName() + ": initial state = " + this.currentState);     // Catch: Throwable -> L9
        startTracking();     // Catch: Throwable -> L9
    L11:
        r6.onConstraintChanged(this.currentState);     // Catch: Throwable -> L9
        goto L12
    }

    public final Context getAppContext() {
        return this.appContext;
    }

    public final T getState() {
        T r0 = this.currentState;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return readSystemState();
    }

    public abstract T readSystemState();

    public final void removeListener(ConstraintListener<T> r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.listeners.remove(r3) == true) goto L7;
    L11:
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r0);
        return;
    L7:
        if (this.listeners.isEmpty() == false) goto L11;
        stopTracking();     // Catch: Throwable -> L9
        goto L11
    }

    public final void setState(T r4) {
        Object r0 = this.lock;
        monitor-enter(r0);
        T r1 = this.currentState;     // Catch: Throwable -> L10
        if (r1 != null) goto L7;
    L12:
        this.currentState = r4;     // Catch: Throwable -> L10
        List r42 = CollectionsKt.toList(this.listeners);     // Catch: Throwable -> L10
        this.taskExecutor.getMainThreadExecutor().execute(new ConstraintTracker$$ExternalSyntheticLambda0(r42, this));     // Catch: Throwable -> L10
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L10
        monitor-exit(r0);
        return;
    L7:
        if (Intrinsics.areEqual(r1, r4) == false) goto L12;
        monitor-exit(r0);
        return;
    L10:
        th = move-exception;
        throw th;
    }

    public abstract void startTracking();

    public abstract void stopTracking();
}
