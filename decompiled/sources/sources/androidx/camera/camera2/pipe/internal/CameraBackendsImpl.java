package androidx.camera.camera2.pipe.internal;

import android.content.Context;
import androidx.camera.camera2.pipe.CameraBackend;
import androidx.camera.camera2.pipe.CameraBackendFactory;
import androidx.camera.camera2.pipe.CameraBackendId;
import androidx.camera.camera2.pipe.CameraBackends;
import androidx.camera.camera2.pipe.CameraContext;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.graph.SurfaceGraph$$ExternalSyntheticBUOutline0;
import androidx.camera.camera2.pipe.internal.CameraPipeLifetime;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001:\u0001$B=\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\b\u0001\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fH\u0096@¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0012\u001a\u00020\u0002H\u0096\u0002¢\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0017R \u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0018R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u0019R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR \u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00130\u001e8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010\u0018R\u001a\u0010 \u001a\u00020\u00138\u0016X\u0096\u0004¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#¨\u0006%"}, d2 = {"Landroidx/camera/camera2/pipe/internal/CameraBackendsImpl;", "Landroidx/camera/camera2/pipe/CameraBackends;", "Landroidx/camera/camera2/pipe/CameraBackendId;", "defaultBackendId", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraBackendFactory;", "cameraBackends", "Landroid/content/Context;", "cameraPipeContext", "Landroidx/camera/camera2/pipe/core/Threads;", "threads", "Landroidx/camera/camera2/pipe/internal/CameraPipeLifetime;", "cameraPipeLifetime", "<init>", "(Ljava/lang/String;Ljava/util/Map;Landroid/content/Context;Landroidx/camera/camera2/pipe/core/Threads;Landroidx/camera/camera2/pipe/internal/CameraPipeLifetime;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "shutdown", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "backendId", "Landroidx/camera/camera2/pipe/CameraBackend;", "get-SG3A4s8", "(Ljava/lang/String;)Landroidx/camera/camera2/pipe/CameraBackend;", "get", "Ljava/lang/String;", "Ljava/util/Map;", "Landroid/content/Context;", "Landroidx/camera/camera2/pipe/core/Threads;", _UrlKt.FRAGMENT_ENCODE_SET, "lock", "Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "activeCameraBackends", PluginsConstants.Settings.DEFAULT, "Landroidx/camera/camera2/pipe/CameraBackend;", "getDefault", "()Landroidx/camera/camera2/pipe/CameraBackend;", "CameraBackendContext", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraBackendsImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraBackendsImpl.kt\nandroidx/camera/camera2/pipe/internal/CameraBackendsImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 4 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,94:1\n1#2:95\n50#3,2:96\n126#4:98\n153#4,3:99\n*S KotlinDebug\n*F\n+ 1 CameraBackendsImpl.kt\nandroidx/camera/camera2/pipe/internal/CameraBackendsImpl\n*L\n64#1:96,2\n65#1:98\n65#1:99,3\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraBackendsImpl implements CameraBackends {
    private final Map<CameraBackendId, CameraBackend> activeCameraBackends;
    private final Map<CameraBackendId, CameraBackendFactory> cameraBackends;
    private final Context cameraPipeContext;

    /* renamed from: default, reason: not valid java name */
    private final CameraBackend f1default;
    private final String defaultBackendId;
    private final Object lock;
    private final Threads threads;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/camera/camera2/pipe/internal/CameraBackendsImpl$CameraBackendContext;", "Landroidx/camera/camera2/pipe/CameraContext;", "Landroid/content/Context;", "appContext", "Landroidx/camera/camera2/pipe/core/Threads;", "threads", "Landroidx/camera/camera2/pipe/CameraBackends;", "cameraBackends", "<init>", "(Landroid/content/Context;Landroidx/camera/camera2/pipe/core/Threads;Landroidx/camera/camera2/pipe/CameraBackends;)V", "Landroid/content/Context;", "getAppContext", "()Landroid/content/Context;", "Landroidx/camera/camera2/pipe/core/Threads;", "getThreads", "()Landroidx/camera/camera2/pipe/core/Threads;", "Landroidx/camera/camera2/pipe/CameraBackends;", "getCameraBackends", "()Landroidx/camera/camera2/pipe/CameraBackends;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class CameraBackendContext implements CameraContext {
        private final Context appContext;
        private final CameraBackends cameraBackends;
        private final Threads threads;

        public CameraBackendContext(Context r1, Threads r2, CameraBackends r3) {
            this.appContext = r1;
            this.threads = r2;
            this.cameraBackends = r3;
        }
    }

    public static void $r8$lambda$0KcRHolqeaFxGlSbhbjtR0oK0DQ(CameraBackendsImpl r2) {
        BuildersKt.runBlocking$default(null, new CameraBackendsImpl$1$1(r2, null), 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private CameraBackendsImpl(String r1, Map<CameraBackendId, ? extends CameraBackendFactory> r2, Context r3, Threads r4, CameraPipeLifetime r5) {
        this.defaultBackendId = r1;
        this.cameraBackends = r2;
        this.cameraPipeContext = r3;
        this.threads = r4;
        this.lock = new Object();
        this.activeCameraBackends = new LinkedHashMap();
        r5.addShutdownAction(CameraPipeLifetime.ShutdownType.CAMERA, new CameraBackendsImpl$$ExternalSyntheticLambda0(this));
        CameraBackend r32 = mo158getSG3A4s8(r1);
        if (r32 == null) goto L6;
        this.f1default = r32;
        return;
    L6:
        StringBuilder r0 = new StringBuilder("Failed to load the default backend for ");
        r0.append(CameraBackendId.m156toStringimpl(r1));
        SurfaceGraph$$ExternalSyntheticBUOutline0.m(r0, "! Available backends are ", r2.keySet());
        throw null;
    }

    public /* synthetic */ CameraBackendsImpl(String r1, Map r2, Context r3, Threads r4, CameraPipeLifetime r5, DefaultConstructorMarker r6) {
        this(r1, r2, r3, r4, r5);
    }

    @Override // androidx.camera.camera2.pipe.CameraBackends
    /* renamed from: get-SG3A4s8 */
    public CameraBackend mo158getSG3A4s8(String r7) {
        Object r1 = this.lock;
        monitor-enter(r1);
        CameraBackend r2 = this.activeCameraBackends.get(CameraBackendId.m151boximpl(r7));     // Catch: Throwable -> L11
        if (r2 == null) goto L8;
        monitor-exit(r1);
        return r2;
    L8:
        CameraBackendFactory r22 = this.cameraBackends.get(CameraBackendId.m151boximpl(r7));     // Catch: Throwable -> L11
        if (r22 == null) goto L13;
        CameraBackend r23 = r22.create(new CameraBackendContext(this.cameraPipeContext, this.threads, this));     // Catch: Throwable -> L11
    L14:
        if (r23 != null) goto L16;
    L20:
        monitor-exit(r1);
        return r23;
    L16:
        if (CameraBackendId.m154equalsimpl0(r7, r23.mo149getIdQwmhuAM()) == false) goto L19;
        this.activeCameraBackends.put(CameraBackendId.m151boximpl(r7), r23);     // Catch: Throwable -> L11
        goto L20
    L19:
        throw new IllegalStateException(("Unexpected backend id! Expected " + CameraBackendId.m156toStringimpl(r7) + " but it was actually " + CameraBackendId.m156toStringimpl(r23.mo149getIdQwmhuAM())).toString());     // Catch: Throwable -> L11
    L13:
        r23 = null;
    L11:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.pipe.CameraBackends
    public CameraBackend getDefault() {
        return this.f1default;
    }

    public Object shutdown(Continuation<? super Unit> r3) {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", "CameraBackends#shutdown");
    L5:
        Map<CameraBackendId, CameraBackend> r2 = this.activeCameraBackends;
        ArrayList r0 = new ArrayList(r2.size());
        Iterator<Map.Entry<CameraBackendId, CameraBackend>> r22 = r2.entrySet().iterator();
    L7:
        if (r22.hasNext() == false) goto L9;
        r0.add(r22.next().getValue().shutdownAsync());
        goto L7
    L9:
        Object r23 = AwaitKt.joinAll(r0, r3);
        if (r23 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L13;
        return r23;
    L13:
        return Unit.INSTANCE;
    }
}
