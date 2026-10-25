package androidx.camera.camera2.pipe.internal;

import androidx.camera.camera2.pipe.core.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Job;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0001\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000bJ\u0010\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u000bH\u0002J\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u000bH\u0002J\u0010\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u000bH\u0002J\u0006\u0010\u001a\u001a\u00020\u0013J\b\u0010\u001b\u001a\u00020\u0013H\u0002J\u000f\u0010\u001c\u001a\u0004\u0018\u00010\u0013H\u0002¢\u0006\u0002\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\b8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Landroidx/camera/camera2/pipe/internal/CameraPipeLifetime;", _UrlKt.FRAGMENT_ENCODE_SET, "cameraPipeJob", "Lkotlinx/coroutines/Job;", "<init>", "(Lkotlinx/coroutines/Job;)V", "cameraLock", "isCameraShutdown", _UrlKt.FRAGMENT_ENCODE_SET, "cameraShutdownActions", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/Runnable;", "scopeLock", "isScopeShutdown", "scopeShutdownActions", "threadLock", "isThreadShutdown", "threadShutdownActions", "addShutdownAction", _UrlKt.FRAGMENT_ENCODE_SET, "shutdownType", "Landroidx/camera/camera2/pipe/internal/CameraPipeLifetime$ShutdownType;", "shutdownAction", "addCameraShutdownAction", "addScopeShutdownAction", "addThreadShutdownAction", "shutdown", "shutdownCamera", "shutdownScope", "()Lkotlin/Unit;", "shutdownThread", "ShutdownType", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraPipeLifetime.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraPipeLifetime.kt\nandroidx/camera/camera2/pipe/internal/CameraPipeLifetime\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,144:1\n82#2,2:145\n50#2,2:147\n50#2,2:149\n50#2,2:151\n*S KotlinDebug\n*F\n+ 1 CameraPipeLifetime.kt\nandroidx/camera/camera2/pipe/internal/CameraPipeLifetime\n*L\n63#1:145,2\n106#1:147,2\n114#1:149,2\n128#1:151,2\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraPipeLifetime {
    public static final Companion Companion = null;
    private final Object cameraLock;
    private final Job cameraPipeJob;
    private final List<Runnable> cameraShutdownActions;
    private boolean isCameraShutdown;
    private boolean isScopeShutdown;
    private boolean isThreadShutdown;
    private final Object scopeLock;
    private final List<Runnable> scopeShutdownActions;
    private final Object threadLock;
    private final List<Runnable> threadShutdownActions;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/camera/camera2/pipe/internal/CameraPipeLifetime$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "CAMERA_PIPE_JOB_CANCEL_TIMEOUT_MS", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/camera/camera2/pipe/internal/CameraPipeLifetime$ShutdownType;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "CAMERA", "SCOPE", "THREAD", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum ShutdownType extends Enum<ShutdownType> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ ShutdownType[] $VALUES = null;
        public static final ShutdownType CAMERA = null;
        public static final ShutdownType SCOPE = null;
        public static final ShutdownType THREAD = null;

        private static final /* synthetic */ ShutdownType[] $values() {
            return new ShutdownType[]{CAMERA, SCOPE, THREAD};
        }

        static {
            CAMERA = new ShutdownType("CAMERA", 0);
            SCOPE = new ShutdownType("SCOPE", 1);
            THREAD = new ShutdownType("THREAD", 2);
            ShutdownType[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        ShutdownType(String r1, int r2) {
        }

        public static ShutdownType valueOf(String r1) {
            return (ShutdownType) Enum.valueOf(ShutdownType.class, r1);
        }

        public static ShutdownType[] values() {
            return (ShutdownType[]) $VALUES.clone();
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[ShutdownType.values().length];
            r0[ShutdownType.CAMERA.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L11:
            r0[ShutdownType.SCOPE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L15:
            r0[ShutdownType.THREAD.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L6:
            $EnumSwitchMapping$0 = r0;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public CameraPipeLifetime(Job r1) {
        this.cameraPipeJob = r1;
        this.cameraLock = new Object();
        this.cameraShutdownActions = new ArrayList();
        this.scopeLock = new Object();
        this.scopeShutdownActions = new ArrayList();
        this.threadLock = new Object();
        this.threadShutdownActions = new ArrayList();
    }

    public static final /* synthetic */ Job access$getCameraPipeJob$p(CameraPipeLifetime r0) {
        return r0.cameraPipeJob;
    }

    private final boolean addCameraShutdownAction(Runnable r3) {
        Object r0 = this.cameraLock;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (this.isCameraShutdown == false) goto L7;
        boolean r2 = false;
    L8:
        monitor-exit(r0);
        return r2;
    L7:
        r2 = this.cameraShutdownActions.add(r3);     // Catch: Throwable -> L10
        goto L8
    }

    private final boolean addScopeShutdownAction(Runnable r3) {
        Object r0 = this.scopeLock;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (this.isScopeShutdown == false) goto L7;
        boolean r2 = false;
    L8:
        monitor-exit(r0);
        return r2;
    L7:
        r2 = this.scopeShutdownActions.add(r3);     // Catch: Throwable -> L10
        goto L8
    }

    private final boolean addThreadShutdownAction(Runnable r3) {
        Object r0 = this.threadLock;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (this.isThreadShutdown == false) goto L7;
        boolean r2 = false;
    L8:
        monitor-exit(r0);
        return r2;
    L7:
        r2 = this.threadShutdownActions.add(r3);     // Catch: Throwable -> L10
        goto L8
    }

    private final void shutdownCamera() {
        Object r0 = this.cameraLock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L9;
        android.util.Log.d("CXCP", "Shutting down cameras...");     // Catch: Throwable -> L7
    L9:
        Iterator<Runnable> r3 = this.cameraShutdownActions.iterator();     // Catch: Throwable -> L7
    L11:
        if (r3.hasNext() == false) goto L13;
        r3.next().run();     // Catch: Throwable -> L7
        goto L11
    L13:
        Unit r32 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
    }

    private final Unit shutdownScope() {
        Object r0 = this.scopeLock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L9;
        android.util.Log.d("CXCP", "Shutting down scopes...");     // Catch: Throwable -> L7
    L9:
        Iterator<Runnable> r1 = this.scopeShutdownActions.iterator();     // Catch: Throwable -> L7
    L11:
        if (r1.hasNext() == false) goto L13;
        r1.next().run();     // Catch: Throwable -> L7
        goto L11
    L13:
        Unit r3 = (Unit) BuildersKt.runBlocking$default(null, new CameraPipeLifetime$shutdownScope$1$2(this, null), 1, null);     // Catch: Throwable -> L7
        monitor-exit(r0);
        return r3;
    }

    private final void shutdownThread() {
        Object r0 = this.threadLock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L9;
        android.util.Log.d("CXCP", "Shutting down threads...");     // Catch: Throwable -> L7
    L9:
        Iterator<Runnable> r3 = this.threadShutdownActions.iterator();     // Catch: Throwable -> L7
    L11:
        if (r3.hasNext() == false) goto L13;
        r3.next().run();     // Catch: Throwable -> L7
        goto L11
    L13:
        Unit r32 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
    }

    public final void addShutdownAction(ShutdownType r3, Runnable r4) {
        int r0 = WhenMappings.$EnumSwitchMapping$0[r3.ordinal()];
        if (r0 != 1) goto L5;
        boolean r2 = addCameraShutdownAction(r4);
    L13:
        if (r2 == false) goto L15;
        return;
    L15:
        if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L17;
        android.util.Log.e("CXCP", "CameraPipeLifetime already shut down. This is unexpected. Executing " + r3 + " shutdown action immediately...");
    L17:
        r4.run();
        return;
    L5:
        if (r0 != 2) goto L7;
        r2 = addScopeShutdownAction(r4);
        goto L13
    L7:
        if (r0 != 3) goto L9;
        r2 = addThreadShutdownAction(r4);
        goto L13
    L9:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
    }

    public final void shutdown() {
        shutdownCamera();
        shutdownScope();
        shutdownThread();
    }
}
