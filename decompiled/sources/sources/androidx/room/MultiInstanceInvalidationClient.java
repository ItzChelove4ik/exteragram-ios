package androidx.room;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import android.util.Log;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000}\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u00010\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\n¢\u0006\u0004\b\u0011\u0010\fJ)\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00150\u00142\u000e\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0012¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001f\u001a\n \u001e*\u0004\u0018\u00010\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010%\u001a\u00020$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u0016\u0010(\u001a\u00020'8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010)R\u0018\u0010+\u001a\u0004\u0018\u00010*8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010,R \u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00150-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\u0014\u00101\u001a\u0002008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102R\u0014\u00104\u001a\u0002038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105R\u0014\u00107\u001a\u0002068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108¨\u00069"}, d2 = {"Landroidx/room/MultiInstanceInvalidationClient;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/content/Context;", "context", _UrlKt.FRAGMENT_ENCODE_SET, "name", "Landroidx/room/InvalidationTracker;", "invalidationTracker", "<init>", "(Landroid/content/Context;Ljava/lang/String;Landroidx/room/InvalidationTracker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "registerCallback", "()V", "Landroid/content/Intent;", "serviceIntent", "start", "(Landroid/content/Intent;)V", "stop", _UrlKt.FRAGMENT_ENCODE_SET, "resolvedTableNames", "Lkotlinx/coroutines/flow/Flow;", _UrlKt.FRAGMENT_ENCODE_SET, "createFlow", "([Ljava/lang/String;)Lkotlinx/coroutines/flow/Flow;", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "Landroidx/room/InvalidationTracker;", "getInvalidationTracker", "()Landroidx/room/InvalidationTracker;", "kotlin.jvm.PlatformType", "appContext", "Landroid/content/Context;", "Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "stopped", "Ljava/util/concurrent/atomic/AtomicBoolean;", _UrlKt.FRAGMENT_ENCODE_SET, "clientId", "I", "Landroidx/room/IMultiInstanceInvalidationService;", "invalidationService", "Landroidx/room/IMultiInstanceInvalidationService;", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "invalidatedTables", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "androidx/room/MultiInstanceInvalidationClient$observer$1", "observer", "Landroidx/room/MultiInstanceInvalidationClient$observer$1;", "Landroidx/room/IMultiInstanceInvalidationCallback;", "invalidationCallback", "Landroidx/room/IMultiInstanceInvalidationCallback;", "Landroid/content/ServiceConnection;", "serviceConnection", "Landroid/content/ServiceConnection;", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMultiInstanceInvalidationClient.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultiInstanceInvalidationClient.android.kt\nandroidx/room/MultiInstanceInvalidationClient\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 4 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 5 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,146:1\n1#2:147\n56#3:148\n59#3:152\n46#4:149\n51#4:151\n105#5:150\n*S KotlinDebug\n*F\n+ 1 MultiInstanceInvalidationClient.android.kt\nandroidx/room/MultiInstanceInvalidationClient\n*L\n133#1:148\n133#1:152\n133#1:149\n133#1:151\n133#1:150\n*E\n"})
/* loaded from: classes4.dex */
public final class MultiInstanceInvalidationClient {
    private final Context appContext;
    private int clientId;
    private final CoroutineScope coroutineScope;
    private final MutableSharedFlow<Set<String>> invalidatedTables;
    private final IMultiInstanceInvalidationCallback invalidationCallback;
    private IMultiInstanceInvalidationService invalidationService;
    private final InvalidationTracker invalidationTracker;
    private final String name;
    private final MultiInstanceInvalidationClient$observer$1 observer;
    private final ServiceConnection serviceConnection;
    private final AtomicBoolean stopped;

    public MultiInstanceInvalidationClient(Context r1, String r2, InvalidationTracker r3) {
        this.name = r2;
        this.invalidationTracker = r3;
        this.appContext = r1.getApplicationContext();
        this.coroutineScope = r3.getDatabase$room_runtime().getCoroutineScope();
        this.stopped = new AtomicBoolean(true);
        this.invalidatedTables = SharedFlowKt.MutableSharedFlow(0, 0, BufferOverflow.SUSPEND);
        this.observer = new MultiInstanceInvalidationClient$observer$1(this, r3.getTableNames$room_runtime());
        this.invalidationCallback = new MultiInstanceInvalidationClient$invalidationCallback$1(this);
        this.serviceConnection = new MultiInstanceInvalidationClient$serviceConnection$1(this);
    }

    public static final /* synthetic */ int access$getClientId$p(MultiInstanceInvalidationClient r0) {
        return r0.clientId;
    }

    public static final /* synthetic */ CoroutineScope access$getCoroutineScope$p(MultiInstanceInvalidationClient r0) {
        return r0.coroutineScope;
    }

    public static final /* synthetic */ MutableSharedFlow access$getInvalidatedTables$p(MultiInstanceInvalidationClient r0) {
        return r0.invalidatedTables;
    }

    public static final /* synthetic */ IMultiInstanceInvalidationService access$getInvalidationService$p(MultiInstanceInvalidationClient r0) {
        return r0.invalidationService;
    }

    public static final /* synthetic */ AtomicBoolean access$getStopped$p(MultiInstanceInvalidationClient r0) {
        return r0.stopped;
    }

    public static final /* synthetic */ void access$registerCallback(MultiInstanceInvalidationClient r0) {
        r0.registerCallback();
    }

    public static final /* synthetic */ void access$setInvalidationService$p(MultiInstanceInvalidationClient r0, IMultiInstanceInvalidationService r1) {
        r0.invalidationService = r1;
    }

    private final void registerCallback() {
        IMultiInstanceInvalidationService r0 = this.invalidationService;     // Catch: RemoteException -> L6
        if (r0 == null) goto L11;
        this.clientId = r0.registerCallback(this.invalidationCallback, this.name);     // Catch: RemoteException -> L6
        return;
    L11:
        return;
    L6:
        e = move-exception;
        Log.w("ROOM", "Cannot register multi-instance invalidation callback", e);
    }

    public final Flow<Set<String>> createFlow(String[] r2) {
        return new MultiInstanceInvalidationClient$createFlow$$inlined$mapNotNull$1(this.invalidatedTables, r2);
    }

    public final InvalidationTracker getInvalidationTracker() {
        return this.invalidationTracker;
    }

    public final void start(Intent r4) {
        if (this.stopped.compareAndSet(true, false) == false) goto L6;
        this.appContext.bindService(r4, this.serviceConnection, 1);
        this.invalidationTracker.addRemoteObserver$room_runtime(this.observer);
        return;
    }

    public final void stop() {
        if (this.stopped.compareAndSet(false, true) == false) goto L15;
        this.invalidationTracker.removeObserver(this.observer);
        IMultiInstanceInvalidationService r0 = this.invalidationService;     // Catch: RemoteException -> L9
        if (r0 == null) goto L11;
        r0.unregisterCallback(this.invalidationCallback, this.clientId);     // Catch: RemoteException -> L9
    L11:
        this.appContext.unbindService(this.serviceConnection);
        return;
    L9:
        e = move-exception;
        Log.w("ROOM", "Cannot unregister multi-instance invalidation callback", e);
        goto L11
    }
}
