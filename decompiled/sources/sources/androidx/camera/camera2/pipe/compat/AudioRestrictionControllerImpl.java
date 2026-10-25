package androidx.camera.camera2.pipe.compat;

import android.os.Build;
import androidx.camera.camera2.pipe.AudioRestrictionMode;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.compat.AudioRestrictionController;
import androidx.camera.camera2.pipe.core.CoroutineMutex;
import androidx.camera.camera2.pipe.core.MutexesKt;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.internal.CameraPipeLifetime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B#\b\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\r\u001a\u0004\u0018\u00010\nH\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\u0012\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\nH\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u001b\u0010\u001aR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R.\u0010&\u001a\u0004\u0018\u00010\n2\b\u0010%\u001a\u0004\u0018\u00010\n8V@VX\u0096\u000e¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b(\u0010\f\"\u0004\b)\u0010\u0011R \u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\n0*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,R\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00170-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010/¨\u00060"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AudioRestrictionControllerImpl;", "Landroidx/camera/camera2/pipe/compat/AudioRestrictionController;", "Landroidx/camera/camera2/pipe/core/Threads;", "threads", "Landroidx/camera/camera2/pipe/internal/CameraPipeLifetime;", "cameraPipeLifetime", "Lkotlinx/coroutines/Job;", "cameraPipeJob", "<init>", "(Landroidx/camera/camera2/pipe/core/Threads;Landroidx/camera/camera2/pipe/internal/CameraPipeLifetime;Lkotlinx/coroutines/Job;)V", "Landroidx/camera/camera2/pipe/AudioRestrictionMode;", "computeAudioRestrictionMode-4o0Og1A", "()Landroidx/camera/camera2/pipe/AudioRestrictionMode;", "computeAudioRestrictionMode", "previousMode", _UrlKt.FRAGMENT_ENCODE_SET, "updateListenersMode-3NUV5dA", "(Landroidx/camera/camera2/pipe/AudioRestrictionMode;)V", "updateListenersMode", "Landroidx/camera/camera2/pipe/CameraGraph;", "cameraGraph", "removeCameraGraph", "(Landroidx/camera/camera2/pipe/CameraGraph;)V", "Landroidx/camera/camera2/pipe/compat/AudioRestrictionController$Listener;", "listener", "addListener", "(Landroidx/camera/camera2/pipe/compat/AudioRestrictionController$Listener;)V", "removeListener", "Lkotlinx/coroutines/CoroutineScope;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "Landroidx/camera/camera2/pipe/core/CoroutineMutex;", "coroutineMutex", "Landroidx/camera/camera2/pipe/core/CoroutineMutex;", _UrlKt.FRAGMENT_ENCODE_SET, "lock", "Ljava/lang/Object;", "value", "globalAudioRestrictionMode", "Landroidx/camera/camera2/pipe/AudioRestrictionMode;", "getGlobalAudioRestrictionMode-4o0Og1A", "setGlobalAudioRestrictionMode-3NUV5dA", _UrlKt.FRAGMENT_ENCODE_SET, "audioRestrictionModeMap", "Ljava/util/Map;", "Ljava/util/concurrent/CopyOnWriteArrayList;", "activeListeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAudioRestrictionController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AudioRestrictionController.kt\nandroidx/camera/camera2/pipe/compat/AudioRestrictionControllerImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,189:1\n1#2:190\n*E\n"})
/* loaded from: classes3.dex */
public final class AudioRestrictionControllerImpl implements AudioRestrictionController {
    private final CopyOnWriteArrayList<AudioRestrictionController.Listener> activeListeners;
    private final Map<CameraGraph, AudioRestrictionMode> audioRestrictionModeMap;
    private final CoroutineMutex coroutineMutex;
    private AudioRestrictionMode globalAudioRestrictionMode;
    private final Object lock;
    private final CoroutineScope scope;

    public static void $r8$lambda$n9zEtjutmQkpYlBPAGIjAyjReBI(AudioRestrictionControllerImpl r2) {
        CoroutineScopeKt.cancel$default(r2.scope, null, 1, null);
    }

    public AudioRestrictionControllerImpl(Threads r3, CameraPipeLifetime r4, Job r5) {
        this.scope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob(r5).plus(r3.getLightweightDispatcher().plus(new CoroutineName("CXCP-AudioRestrictionControllerImpl"))));
        this.coroutineMutex = new CoroutineMutex();
        this.lock = new Object();
        this.audioRestrictionModeMap = new LinkedHashMap();
        this.activeListeners = new CopyOnWriteArrayList();
        r4.addShutdownAction(CameraPipeLifetime.ShutdownType.SCOPE, new AudioRestrictionControllerImpl$$ExternalSyntheticLambda0(this));
    }

    public static final /* synthetic */ CopyOnWriteArrayList access$getActiveListeners$p(AudioRestrictionControllerImpl r0) {
        return r0.activeListeners;
    }

    /* renamed from: computeAudioRestrictionMode-4o0Og1A, reason: not valid java name */
    private final AudioRestrictionMode m424computeAudioRestrictionMode4o0Og1A() {
        Map<CameraGraph, AudioRestrictionMode> r0 = this.audioRestrictionModeMap;
        AudioRestrictionMode.Companion r1 = AudioRestrictionMode.Companion;
        if (r0.containsValue(AudioRestrictionMode.m130boximpl(r1.m139getAUDIO_RESTRICTION_VIBRATION_SOUND_b5Q8KE())) == true) goto L33;
        AudioRestrictionMode r02 = m426getGlobalAudioRestrictionMode4o0Og1A();
        int r2 = r1.m139getAUDIO_RESTRICTION_VIBRATION_SOUND_b5Q8KE();
        boolean r3 = false;
        if (r02 != null) goto L7;
        boolean r03 = false;
    L8:
        if (r03 == true) goto L33;
        if (this.audioRestrictionModeMap.containsValue(AudioRestrictionMode.m130boximpl(r1.m138getAUDIO_RESTRICTION_VIBRATION_b5Q8KE())) == true) goto L31;
        AudioRestrictionMode r04 = m426getGlobalAudioRestrictionMode4o0Og1A();
        int r22 = r1.m138getAUDIO_RESTRICTION_VIBRATION_b5Q8KE();
        if (r04 != null) goto L15;
        boolean r05 = false;
    L16:
        if (r05 == true) goto L31;
        if (this.audioRestrictionModeMap.containsValue(AudioRestrictionMode.m130boximpl(r1.m137getAUDIO_RESTRICTION_NONE_b5Q8KE())) == true) goto L29;
        AudioRestrictionMode r4 = m426getGlobalAudioRestrictionMode4o0Og1A();
        int r06 = r1.m137getAUDIO_RESTRICTION_NONE_b5Q8KE();
        if (r4 == null) goto L24;
        r3 = AudioRestrictionMode.m133equalsimpl0(r4.m136unboximpl(), r06);
    L24:
        if (r3 == true) goto L29;
        return null;
    L29:
        return AudioRestrictionMode.m130boximpl(r1.m137getAUDIO_RESTRICTION_NONE_b5Q8KE());
    L15:
        r05 = AudioRestrictionMode.m133equalsimpl0(r04.m136unboximpl(), r22);
    L31:
        return AudioRestrictionMode.m130boximpl(r1.m138getAUDIO_RESTRICTION_VIBRATION_b5Q8KE());
    L7:
        r03 = AudioRestrictionMode.m133equalsimpl0(r02.m136unboximpl(), r2);
    L33:
        return AudioRestrictionMode.m130boximpl(r1.m139getAUDIO_RESTRICTION_VIBRATION_SOUND_b5Q8KE());
    }

    /* renamed from: updateListenersMode-3NUV5dA, reason: not valid java name */
    private final void m425updateListenersMode3NUV5dA(AudioRestrictionMode r5) {
        AudioRestrictionMode r0 = m424computeAudioRestrictionMode4o0Og1A();
        if (r0 != null) goto L5;
        return;
    L5:
        if (Intrinsics.areEqual(r0, r5) == true) goto L9;
        MutexesKt.withLockLaunch(this.coroutineMutex, this.scope, new AudioRestrictionControllerImpl$updateListenersMode$1(this, r0, null));
        return;
    }

    @Override // androidx.camera.camera2.pipe.compat.AudioRestrictionController
    public void addListener(AudioRestrictionController.Listener r6) {
        if (Build.VERSION.SDK_INT >= 30) goto L5;
        return;
    L5:
        Object r0 = this.lock;
        monitor-enter(r0);
        this.activeListeners.add(r6);     // Catch: Throwable -> L10
        AudioRestrictionMode r1 = m424computeAudioRestrictionMode4o0Og1A();     // Catch: Throwable -> L10
        if (r1 == null) goto L12;
        MutexesKt.withLockLaunch(this.coroutineMutex, this.scope, new AudioRestrictionControllerImpl$addListener$1$1(r6, r1, null));     // Catch: Throwable -> L10
    L12:
        Unit r5 = Unit.INSTANCE;     // Catch: Throwable -> L10
        monitor-exit(r0);
        return;
    L10:
        th = move-exception;
        throw th;
    }

    /* renamed from: getGlobalAudioRestrictionMode-4o0Og1A, reason: not valid java name */
    public AudioRestrictionMode m426getGlobalAudioRestrictionMode4o0Og1A() {
        Object r0 = this.lock;
        monitor-enter(r0);
        AudioRestrictionMode r1 = this.globalAudioRestrictionMode;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.pipe.compat.AudioRestrictionController
    public void removeCameraGraph(CameraGraph r4) {
        Object r0 = this.lock;
        monitor-enter(r0);
        AudioRestrictionMode r1 = m424computeAudioRestrictionMode4o0Og1A();     // Catch: Throwable -> L7
        this.audioRestrictionModeMap.remove(r4);     // Catch: Throwable -> L7
        m425updateListenersMode3NUV5dA(r1);     // Catch: Throwable -> L7
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.pipe.compat.AudioRestrictionController
    public void removeListener(AudioRestrictionController.Listener r3) {
        if (Build.VERSION.SDK_INT >= 30) goto L5;
        return;
    L5:
        this.activeListeners.remove(r3);
    }
}
