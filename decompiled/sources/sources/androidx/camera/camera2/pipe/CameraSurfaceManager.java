package androidx.camera.camera2.pipe;

import android.view.Surface;
import androidx.camera.camera2.pipe.core.Log;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0003\u0019\u001a\u001bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bJ\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bJ\u0019\u0010\u0010\u001a\u00060\u0011j\u0002`\u00122\u0006\u0010\u0013\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u0014J\u0019\u0010\u0015\u001a\u00020\r2\n\u0010\u0016\u001a\u00060\u0017R\u00020\u0000H\u0000¢\u0006\u0002\b\u0018R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/camera/camera2/pipe/CameraSurfaceManager;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "lock", "useCountMap", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/view/Surface;", _UrlKt.FRAGMENT_ENCODE_SET, "listeners", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraSurfaceManager$SurfaceListener;", "addListener", _UrlKt.FRAGMENT_ENCODE_SET, "listener", "removeListener", "registerSurface", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "surface", "registerSurface$camera_camera2_pipe", "onTokenClosed", "surfaceToken", "Landroidx/camera/camera2/pipe/CameraSurfaceManager$SurfaceToken;", "onTokenClosed$camera_camera2_pipe", "SurfaceToken", "SurfaceListener", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraSurfaceManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraSurfaceManager.kt\nandroidx/camera/camera2/pipe/CameraSurfaceManager\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,179:1\n538#2:180\n523#2,6:181\n1869#3,2:187\n1869#3,2:192\n1869#3,2:194\n1#4:189\n71#5,2:190\n*S KotlinDebug\n*F\n+ 1 CameraSurfaceManager.kt\nandroidx/camera/camera2/pipe/CameraSurfaceManager\n*L\n101#1:180\n101#1:181,6\n104#1:187,2\n139#1:192,2\n170#1:194,2\n114#1:190,2\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraSurfaceManager {
    public static final Companion Companion = null;
    private static final AtomicInt surfaceTokenDebugIds = null;
    private final Set<SurfaceListener> listeners;
    private final Object lock;
    private final Map<Surface, Integer> useCountMap;

    @kotlin.Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/camera/camera2/pipe/CameraSurfaceManager$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "DEBUG", _UrlKt.FRAGMENT_ENCODE_SET, "surfaceTokenDebugIds", "Lkotlinx/atomicfu/AtomicInt;", "getSurfaceTokenDebugIds$camera_camera2_pipe", "()Lkotlinx/atomicfu/AtomicInt;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final AtomicInt getSurfaceTokenDebugIds$camera_camera2_pipe() {
            return CameraSurfaceManager.access$getSurfaceTokenDebugIds$cp();
        }
    }

    @kotlin.Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/CameraSurfaceManager$SurfaceListener;", _UrlKt.FRAGMENT_ENCODE_SET, "onSurfaceActive", _UrlKt.FRAGMENT_ENCODE_SET, "surface", "Landroid/view/Surface;", "onSurfaceInactive", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface SurfaceListener {
        void onSurfaceActive(Surface r1);

        void onSurfaceInactive(Surface r1);
    }

    @kotlin.Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/camera/camera2/pipe/CameraSurfaceManager$SurfaceToken;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "surface", "Landroid/view/Surface;", "<init>", "(Landroidx/camera/camera2/pipe/CameraSurfaceManager;Landroid/view/Surface;)V", "getSurface$camera_camera2_pipe", "()Landroid/view/Surface;", "debugId", _UrlKt.FRAGMENT_ENCODE_SET, "closed", "Lkotlinx/atomicfu/AtomicBoolean;", "close", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public final class SurfaceToken implements AutoCloseable {
        private final AtomicBoolean closed;
        private final int debugId;
        private final Surface surface;
        final /* synthetic */ CameraSurfaceManager this$0;

        public SurfaceToken(CameraSurfaceManager r1, Surface r2) {
            this.this$0 = r1;
            this.surface = r2;
            this.debugId = CameraSurfaceManager.Companion.getSurfaceTokenDebugIds$camera_camera2_pipe().incrementAndGet();
            this.closed = AtomicFU.atomic(false);
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            if (this.closed.compareAndSet(false, true) == false) goto L6;
            this.this$0.onTokenClosed$camera_camera2_pipe(this);
            return;
        }

        public final Surface getSurface$camera_camera2_pipe() {
            return this.surface;
        }

        public String toString() {
            return "SurfaceToken-" + this.debugId;
        }
    }

    static {
        Companion = new Companion(null);
        surfaceTokenDebugIds = AtomicFU.atomic(0);
    }

    public CameraSurfaceManager() {
        this.lock = new Object();
        this.useCountMap = new LinkedHashMap();
        this.listeners = new LinkedHashSet();
    }

    public static final /* synthetic */ AtomicInt access$getSurfaceTokenDebugIds$cp() {
        return surfaceTokenDebugIds;
    }

    public final void addListener(SurfaceListener r5) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.listeners.add(r5);     // Catch: Throwable -> L10
        Map<Surface, Integer> r4 = this.useCountMap;     // Catch: Throwable -> L10
        LinkedHashMap r1 = new LinkedHashMap();     // Catch: Throwable -> L10
        Iterator<Map.Entry<Surface, Integer>> r42 = r4.entrySet().iterator();     // Catch: Throwable -> L10
    L6:
        if (r42.hasNext() == false) goto L12;
        Map.Entry<Surface, Integer> r2 = r42.next();     // Catch: Throwable -> L10
        if (r2.getValue().intValue() <= 0) goto L6;
        r1.put(r2.getKey(), r2.getValue());     // Catch: Throwable -> L10
        goto L6
    L12:
        Set r43 = r1.keySet();     // Catch: Throwable -> L10
        monitor-exit(r0);
        Iterator r44 = r43.iterator();
    L16:
        if (r44.hasNext() == false) goto L18;
        r5.onSurfaceActive((Surface) r44.next());
        goto L16
    L18:
        return;
    L10:
        th = move-exception;
        throw th;
    }

    public final void onTokenClosed$camera_camera2_pipe(SurfaceToken r5) {
        Object r1 = this.lock;
        monitor-enter(r1);
        Surface r2 = r5.getSurface$camera_camera2_pipe();     // Catch: Throwable -> L9
        Integer r3 = this.useCountMap.get(r2);     // Catch: Throwable -> L9
        if (r3 == null) goto L21;
        int r52 = r3.intValue() - 1;
        this.useCountMap.put(r2, Integer.valueOf(r52));     // Catch: Throwable -> L9
        if (r52 != 0) goto L11;
        List r53 = CollectionsKt.toList(this.listeners);     // Catch: Throwable -> L9
        this.useCountMap.remove(r2);     // Catch: Throwable -> L9
    L12:
        Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r1);
        if (r53 == null) goto L19;
        Iterator r42 = r53.iterator();
    L17:
        if (r42.hasNext() == false) goto L26;
        ((SurfaceListener) r42.next()).onSurfaceInactive(r2);
        goto L17
    L26:
        return;
    L19:
        return;
    L11:
        r53 = null;
        goto L12
    L21:
        throw new IllegalStateException(("Surface " + r2 + " (" + r5 + ") has no use count").toString());     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }

    public final AutoCloseable registerSurface$camera_camera2_pipe(Surface r7) {
        if (r7.isValid() == false) goto L5;
    L7:
        Object r0 = this.lock;
        monitor-enter(r0);
        SurfaceToken r1 = new SurfaceToken(this, r7);     // Catch: Throwable -> L12
        Integer r2 = this.useCountMap.get(r7);     // Catch: Throwable -> L12
        if (r2 == null) goto L14;
        int r22 = r2.intValue();     // Catch: Throwable -> L12
    L15:
        int r23 = r22 + 1;     // Catch: Throwable -> L12
        this.useCountMap.put(r7, Integer.valueOf(r23));     // Catch: Throwable -> L12
        if (r23 != 1) goto L18;
        List r6 = CollectionsKt.toList(this.listeners);     // Catch: Throwable -> L12
    L19:
        Unit r24 = Unit.INSTANCE;     // Catch: Throwable -> L12
        monitor-exit(r0);
        if (r6 == null) goto L26;
        Iterator r62 = r6.iterator();
    L24:
        if (r62.hasNext() == false) goto L26;
        ((SurfaceListener) r62.next()).onSurfaceActive(r7);
    L26:
        return r1;
    L18:
        r6 = null;
        goto L19
    L14:
        r22 = 0;
    L12:
        th = move-exception;
        throw th;
    L5:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L7;
        android.util.Log.w("CXCP", "registerSurface: Surface " + r7 + " isn't valid!");
        goto L7
    }

    public final void removeListener(SurfaceListener r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.listeners.remove(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
