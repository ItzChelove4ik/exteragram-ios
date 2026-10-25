package androidx.camera.core;

import android.content.Context;
import android.view.OrientationEventListener;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u0000 *2\u00020\u0001:\u0003+,*B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u001b\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0004\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u000b¢\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR \u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020!0 8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010$\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010&R$\u0010(\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u00068\u0007@BX\u0086\u000e¢\u0006\f\n\u0004\b(\u0010&\u001a\u0004\b(\u0010)¨\u0006-"}, d2 = {"Landroidx/camera/core/RotationProvider;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/content/Context;", "appContext", "<init>", "(Landroid/content/Context;)V", _UrlKt.FRAGMENT_ENCODE_SET, "ignoreCanDetectForTest", "(Landroid/content/Context;Z)V", _UrlKt.FRAGMENT_ENCODE_SET, "newRotation", _UrlKt.FRAGMENT_ENCODE_SET, "updateRotation", "(I)V", "orientation", "orientationToSurfaceRotation", "(I)I", "Ljava/util/concurrent/Executor;", "executor", "Landroidx/camera/core/RotationProvider$Listener;", "listener", "addListener", "(Ljava/util/concurrent/Executor;Landroidx/camera/core/RotationProvider$Listener;)Z", "removeListener", "(Landroidx/camera/core/RotationProvider$Listener;)V", "shutdown", "()V", "lock", "Ljava/lang/Object;", "Landroid/view/OrientationEventListener;", "orientationListener", "Landroid/view/OrientationEventListener;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/RotationProvider$ListenerWrapper;", "listeners", "Ljava/util/Map;", "rotation", "I", "Z", "value", "isShutdown", "()Z", "Companion", "Listener", "ListenerWrapper", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRotationProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RotationProvider.kt\nandroidx/camera/core/RotationProvider\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,218:1\n1#2:219\n1869#3,2:220\n*S KotlinDebug\n*F\n+ 1 RotationProvider.kt\nandroidx/camera/core/RotationProvider\n*L\n90#1:220,2\n*E\n"})
/* loaded from: classes3.dex */
public final class RotationProvider {
    private static final Companion Companion = null;
    private final boolean ignoreCanDetectForTest;
    private boolean isShutdown;
    private final Map<Listener, ListenerWrapper> listeners;
    private final Object lock;
    private final OrientationEventListener orientationListener;
    private volatile int rotation;

    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"androidx/camera/core/RotationProvider$1", "Landroid/view/OrientationEventListener;", "onOrientationChanged", _UrlKt.FRAGMENT_ENCODE_SET, "orientation", _UrlKt.FRAGMENT_ENCODE_SET, "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: androidx.camera.core.RotationProvider$1, reason: invalid class name */
    public static final class AnonymousClass1 extends OrientationEventListener {
        final /* synthetic */ RotationProvider this$0;

        public AnonymousClass1(Context r1, RotationProvider r2) {
            this.this$0 = r2;
            super(r1);
        }

        @Override // android.view.OrientationEventListener
        public void onOrientationChanged(int r2) {
            if (r2 != (-1)) goto L5;
            return;
        L5:
            int r22 = RotationProvider.access$orientationToSurfaceRotation(this.this$0, r2);
            RotationProvider.access$updateRotation(this.this$0, r22);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/camera/core/RotationProvider$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/camera/core/RotationProvider$Listener;", _UrlKt.FRAGMENT_ENCODE_SET, "onRotationChanged", _UrlKt.FRAGMENT_ENCODE_SET, "rotation", _UrlKt.FRAGMENT_ENCODE_SET, "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface Listener {
        void onRotationChanged(int r1);
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/camera/core/RotationProvider$ListenerWrapper;", _UrlKt.FRAGMENT_ENCODE_SET, "listener", "Landroidx/camera/core/RotationProvider$Listener;", "executor", "Ljava/util/concurrent/Executor;", "<init>", "(Landroidx/camera/core/RotationProvider$Listener;Ljava/util/concurrent/Executor;)V", "enabled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "onRotationChanged", _UrlKt.FRAGMENT_ENCODE_SET, "rotation", _UrlKt.FRAGMENT_ENCODE_SET, "disable", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class ListenerWrapper {
        private final AtomicBoolean enabled;
        private final Executor executor;
        private final Listener listener;

        public static void $r8$lambda$540uX7lYQInAUuBr1aMPK2LyrOU(ListenerWrapper r1, int r2) {
            if (r1.enabled.get() == false) goto L6;
            r1.listener.onRotationChanged(r2);
            return;
        }

        public ListenerWrapper(Listener r1, Executor r2) {
            this.listener = r1;
            this.executor = r2;
            this.enabled = new AtomicBoolean(true);
        }

        public final void disable() {
            this.enabled.set(false);
        }

        public final void onRotationChanged(int r3) {
            if (this.enabled.get() == false) goto L10;
            this.executor.execute(new RotationProvider$ListenerWrapper$$ExternalSyntheticLambda0(this, r3));     // Catch: RejectedExecutionException -> L6
            return;
        L6:
            Logger.w("RotationProvider", "Failed to execute the command. Maybe the executor has been shutdown.");
            return;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public RotationProvider(Context r2) {
        this(r2, false);
    }

    public RotationProvider(Context r2, boolean r3) {
        this.lock = new Object();
        this.listeners = new LinkedHashMap();
        this.rotation = -1;
        this.ignoreCanDetectForTest = r3;
        this.orientationListener = new AnonymousClass1(r2, this);
    }

    public static final /* synthetic */ int access$orientationToSurfaceRotation(RotationProvider r0, int r1) {
        return r0.orientationToSurfaceRotation(r1);
    }

    public static final /* synthetic */ void access$updateRotation(RotationProvider r0, int r1) {
        r0.updateRotation(r1);
    }

    private final int orientationToSurfaceRotation(int r7) {
        if (this.rotation == (-1)) goto L5;
        if (r7 < 0) goto L26;
        if (r7 >= 40) goto L26;
    L29:
        return 0;
    L26:
        if (320 > r7) goto L31;
        if (r7 < 360) goto L29;
    L31:
        if (50 > r7) goto L36;
        if (r7 >= 130) goto L36;
        return 3;
    L36:
        if (140 > r7) goto L41;
        if (r7 >= 220) goto L41;
        return 2;
    L41:
        if (230 > r7) goto L46;
        if (r7 >= 310) goto L46;
        return 1;
    L46:
        return this.rotation;
    L5:
        if (r7 < 0) goto L9;
        if (r7 >= 45) goto L9;
        return 0;
    L9:
        if (45 > r7) goto L13;
        if (r7 >= 135) goto L13;
        return 3;
    L13:
        if (135 > r7) goto L16;
        if (r7 >= 225) goto L16;
        return 2;
    L16:
        if (225 <= r7) goto L18;
    L20:
        return 0;
    L18:
        if (r7 >= 315) goto L20;
        return 1;
    }

    private final void updateRotation(int r3) {
        if (this.rotation == r3) goto L15;
        this.rotation = r3;
        Object r0 = this.lock;
        monitor-enter(r0);
        List r2 = CollectionsKt.toList(this.listeners.values());     // Catch: Throwable -> L12
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L12
        monitor-exit(r0);
        Iterator r22 = r2.iterator();
    L10:
        if (r22.hasNext() == false) goto L19;
        ((ListenerWrapper) r22.next()).onRotationChanged(r3);
        goto L10
    L19:
        return;
    L12:
        th = move-exception;
        throw th;
    }

    public final boolean addListener(Executor r3, Listener r4) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (this.ignoreCanDetectForTest == false) goto L7;
    L13:
        ListenerWrapper r1 = new ListenerWrapper(r4, r3);     // Catch: Throwable -> L11
        this.listeners.put(r4, r1);     // Catch: Throwable -> L11
        if (this.rotation == (-1)) goto L17;
        r1.onRotationChanged(this.rotation);     // Catch: Throwable -> L11
    L17:
        if (this.listeners.size() != 1) goto L19;
        this.orientationListener.enable();     // Catch: Throwable -> L11
    L19:
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L11
        monitor-exit(r0);
        return true;
    L7:
        if (this.orientationListener.canDetectOrientation() == true) goto L13;
        monitor-exit(r0);
        return false;
    }

    public final void removeListener(Listener r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
        ListenerWrapper r1 = this.listeners.get(r3);     // Catch: Throwable -> L7
        if (r1 == null) goto L10;
        r1.disable();     // Catch: Throwable -> L7
        this.listeners.remove(r3);     // Catch: Throwable -> L7
    L10:
        if (this.listeners.isEmpty() == false) goto L12;
        this.orientationListener.disable();     // Catch: Throwable -> L7
        this.rotation = -1;     // Catch: Throwable -> L7
    L12:
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final void shutdown() {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.orientationListener.disable();     // Catch: Throwable -> L7
        this.listeners.clear();     // Catch: Throwable -> L7
        this.isShutdown = true;     // Catch: Throwable -> L7
        this.rotation = -1;     // Catch: Throwable -> L7
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
