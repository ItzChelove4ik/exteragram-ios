package androidx.camera.camera2.pipe.graph;

import android.hardware.camera2.params.MeteringRectangle;
import androidx.camera.camera2.pipe.AeMode;
import androidx.camera.camera2.pipe.AfMode;
import androidx.camera.camera2.pipe.AwbMode;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.FrameMetadata;
import androidx.camera.camera2.pipe.Lock3ABehavior;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.Result3A;
import androidx.camera.camera2.pipe.core.Token;
import androidx.camera.camera2.pipe.internal.CameraGraphParametersImpl;
import androidx.camera.camera2.pipe.internal.CameraGraphRequestListenersImpl;
import androidx.camera.camera2.pipe.internal.FrameCaptureQueue;
import androidx.lifecycle.LifecycleRegistry$$ExternalSyntheticBUOutline0;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0014\u001a\u00020\u00132\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u001b\u0010\u001aJc\u0010*\u001a\b\u0012\u0004\u0012\u00020'0&2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\u0010!\u001a\u0004\u0018\u00010 2\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010\u00102\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010\u00102\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010\u0010H\u0016¢\u0006\u0004\b(\u0010)J\u0015\u0010+\u001a\b\u0012\u0004\u0012\u00020'0&H\u0016¢\u0006\u0004\b+\u0010,J\u001f\u0010/\u001a\b\u0012\u0004\u0012\u00020'0&2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0016¢\u0006\u0004\b-\u0010.JÐ\u0001\u0010A\u001a\b\u0012\u0004\u0012\u00020'0&2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\u0010!\u001a\u0004\u0018\u00010 2\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010\u00102\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010\u00102\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010\u00102\b\u00101\u001a\u0004\u0018\u0001002\b\u00102\u001a\u0004\u0018\u0001002\b\u00103\u001a\u0004\u0018\u0001002\b\u00104\u001a\u0004\u0018\u00010\u001c2\u0014\u00108\u001a\u0010\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u000207\u0018\u0001052\u0014\u00109\u001a\u0010\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u000207\u0018\u0001052\u0006\u0010;\u001a\u00020:2\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020<H\u0096@¢\u0006\u0004\b?\u0010@JZ\u0010G\u001a\b\u0012\u0004\u0012\u00020'0&2\b\u0010B\u001a\u0004\u0018\u0001072\b\u0010C\u001a\u0004\u0018\u0001072\b\u0010D\u001a\u0004\u0018\u0001072\u0014\u0010E\u001a\u0010\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u000207\u0018\u0001052\u0006\u0010;\u001a\u00020:2\u0006\u0010F\u001a\u00020<H\u0096@¢\u0006\u0004\bG\u0010HJ6\u0010K\u001a\b\u0012\u0004\u0012\u00020'0&2\u0006\u0010I\u001a\u0002072\u0006\u0010J\u001a\u0002072\u0006\u0010;\u001a\u00020:2\u0006\u0010F\u001a\u00020<H\u0096@¢\u0006\u0004\bK\u0010LJ\u001e\u0010N\u001a\b\u0012\u0004\u0012\u00020'0&2\u0006\u0010M\u001a\u000207H\u0096@¢\u0006\u0004\bN\u0010OJ\u000f\u0010Q\u001a\u00020PH\u0016¢\u0006\u0004\bQ\u0010RR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010SR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010TR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010UR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010VR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010WR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010XR\u0014\u0010Y\u001a\u00020:8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bY\u0010Z¨\u0006["}, d2 = {"Landroidx/camera/camera2/pipe/graph/CameraGraphSessionImpl;", "Landroidx/camera/camera2/pipe/CameraGraph$Session;", "Landroidx/camera/camera2/pipe/core/Token;", "token", "Landroidx/camera/camera2/pipe/graph/GraphProcessor;", "graphProcessor", "Landroidx/camera/camera2/pipe/graph/Controller3A;", "controller3A", "Landroidx/camera/camera2/pipe/internal/FrameCaptureQueue;", "frameCaptureQueue", "Landroidx/camera/camera2/pipe/internal/CameraGraphParametersImpl;", "parameters", "Landroidx/camera/camera2/pipe/internal/CameraGraphRequestListenersImpl;", "listeners", "<init>", "(Landroidx/camera/camera2/pipe/core/Token;Landroidx/camera/camera2/pipe/graph/GraphProcessor;Landroidx/camera/camera2/pipe/graph/Controller3A;Landroidx/camera/camera2/pipe/internal/FrameCaptureQueue;Landroidx/camera/camera2/pipe/internal/CameraGraphParametersImpl;Landroidx/camera/camera2/pipe/internal/CameraGraphRequestListenersImpl;)V", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/Request;", "requests", _UrlKt.FRAGMENT_ENCODE_SET, "submit", "(Ljava/util/List;)V", PluginsConstants.REQUEST, "startRepeating", "(Landroidx/camera/camera2/pipe/Request;)V", "stopRepeating", "()V", "close", "Landroidx/camera/camera2/pipe/AeMode;", "aeMode", "Landroidx/camera/camera2/pipe/AfMode;", "afMode", "Landroidx/camera/camera2/pipe/AwbMode;", "awbMode", "Landroid/hardware/camera2/params/MeteringRectangle;", "aeRegions", "afRegions", "awbRegions", "Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/pipe/Result3A;", "update3A-ydBZfZg", "(Landroidx/camera/camera2/pipe/AeMode;Landroidx/camera/camera2/pipe/AfMode;Landroidx/camera/camera2/pipe/AwbMode;Ljava/util/List;Ljava/util/List;Ljava/util/List;)Lkotlinx/coroutines/Deferred;", "update3A", "setTorchOn", "()Lkotlinx/coroutines/Deferred;", "setTorchOff-NqN7i0k", "(Landroidx/camera/camera2/pipe/AeMode;)Lkotlinx/coroutines/Deferred;", "setTorchOff", "Landroidx/camera/camera2/pipe/Lock3ABehavior;", "aeLockBehavior", "afLockBehavior", "awbLockBehavior", "afTriggerStartAeMode", "Lkotlin/Function1;", "Landroidx/camera/camera2/pipe/FrameMetadata;", _UrlKt.FRAGMENT_ENCODE_SET, "convergedCondition", "lockedCondition", _UrlKt.FRAGMENT_ENCODE_SET, "frameLimit", _UrlKt.FRAGMENT_ENCODE_SET, "convergedTimeLimitNs", "lockedTimeLimitNs", "lock3A--tS25XM", "(Landroidx/camera/camera2/pipe/AeMode;Landroidx/camera/camera2/pipe/AfMode;Landroidx/camera/camera2/pipe/AwbMode;Ljava/util/List;Ljava/util/List;Ljava/util/List;Landroidx/camera/camera2/pipe/Lock3ABehavior;Landroidx/camera/camera2/pipe/Lock3ABehavior;Landroidx/camera/camera2/pipe/Lock3ABehavior;Landroidx/camera/camera2/pipe/AeMode;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;IJJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lock3A", "ae", "af", "awb", "unlockedCondition", "timeLimitNs", "unlock3A", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Lkotlin/jvm/functions/Function1;IJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "triggerAf", "waitForAwb", "lock3AForCapture", "(ZZIJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelAf", "unlock3APostCapture", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroidx/camera/camera2/pipe/core/Token;", "Landroidx/camera/camera2/pipe/graph/GraphProcessor;", "Landroidx/camera/camera2/pipe/graph/Controller3A;", "Landroidx/camera/camera2/pipe/internal/FrameCaptureQueue;", "Landroidx/camera/camera2/pipe/internal/CameraGraphParametersImpl;", "Landroidx/camera/camera2/pipe/internal/CameraGraphRequestListenersImpl;", "debugId", "I", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraGraphSessionImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraGraphSessionImpl.kt\nandroidx/camera/camera2/pipe/graph/CameraGraphSessionImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,216:1\n1#2:217\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraGraphSessionImpl implements CameraGraph.Session {
    private final Controller3A controller3A;
    private final int debugId;
    private final FrameCaptureQueue frameCaptureQueue;
    private final GraphProcessor graphProcessor;
    private final CameraGraphRequestListenersImpl listeners;
    private final CameraGraphParametersImpl parameters;
    private final Token token;

    public CameraGraphSessionImpl(Token r1, GraphProcessor r2, Controller3A r3, FrameCaptureQueue r4, CameraGraphParametersImpl r5, CameraGraphRequestListenersImpl r6) {
        this.token = r1;
        this.graphProcessor = r2;
        this.controller3A = r3;
        this.frameCaptureQueue = r4;
        this.parameters = r5;
        this.listeners = r6;
        this.debugId = CameraGraphSessionImplKt.getCameraGraphSessionIds().incrementAndGet();
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.parameters.flush();
        List<Request.Listener> r0 = this.listeners.fetchUpdatedListeners$camera_camera2_pipe();
        if (r0 == null) goto L5;
        this.graphProcessor.updateRequestListeners(r0);
    L5:
        this.token.release();
    }

    @Override // androidx.camera.camera2.pipe.CameraGraph.Session
    /* renamed from: lock3A--tS25XM */
    public Object mo225lock3AtS25XM(AeMode r17, AfMode r18, AwbMode r19, List<MeteringRectangle> r20, List<MeteringRectangle> r21, List<MeteringRectangle> r22, Lock3ABehavior r23, Lock3ABehavior r24, Lock3ABehavior r25, AeMode r26, Function1<? super FrameMetadata, Boolean> r27, Function1<? super FrameMetadata, Boolean> r28, int r29, long r30, long r32, Continuation<? super Deferred<Result3A>> r34) {
        if (this.token.getReleased() == false) goto L5;
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("Cannot call lock3A on ", this, " after close.");
        return null;
    L5:
        return this.controller3A.m518lock3AQz1gx5w(r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, Boxing.boxLong(r30), Boxing.boxLong(r32), r34);
    }

    @Override // androidx.camera.camera2.pipe.CameraGraph.Session
    public Object lock3AForCapture(boolean r1, boolean r2, int r3, long r4, Continuation<? super Deferred<Result3A>> r6) {
        if (this.token.getReleased() == false) goto L5;
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("Cannot call lock3AForCapture on ", this, " after close.");
        return null;
    L5:
        return this.controller3A.lock3AForCapture(r1, r2, r3, r4);
    }

    @Override // androidx.camera.camera2.pipe.CameraControls3A
    /* renamed from: setTorchOff-NqN7i0k */
    public Deferred<Result3A> mo165setTorchOffNqN7i0k(AeMode r2) {
        if (this.token.getReleased() == false) goto L5;
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("Cannot call setTorchOff on ", this, " after close.");
        return null;
    L5:
        return this.controller3A.m519setTorchOffNqN7i0k(r2);
    }

    @Override // androidx.camera.camera2.pipe.CameraControls3A
    public Deferred<Result3A> setTorchOn() {
        if (this.token.getReleased() == false) goto L5;
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("Cannot call setTorchOn on ", this, " after close.");
        return null;
    L5:
        return this.controller3A.setTorchOn();
    }

    @Override // androidx.camera.camera2.pipe.CameraGraph.Session
    public void startRepeating(Request r2) {
        if (this.token.getReleased() == true) goto L6;
        this.graphProcessor.setRepeatingRequest(r2);
        return;
    L6:
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("Cannot call startRepeating on ", this, " after close.");
    }

    @Override // androidx.camera.camera2.pipe.CameraGraph.Session
    public void stopRepeating() {
        if (this.token.getReleased() == true) goto L6;
        this.graphProcessor.setRepeatingRequest(null);
        return;
    L6:
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("Cannot call stopRepeating on ", this, " after close.");
    }

    @Override // androidx.camera.camera2.pipe.CameraGraph.Session
    public void submit(List<Request> r2) {
        if (this.token.getReleased() == false) goto L5;
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("Cannot call submit on ", this, " after close.");
        return;
    L5:
        if (r2.isEmpty() == true) goto L8;
        this.graphProcessor.submit(r2);
        return;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("Cannot call submit with an empty list of Requests!");
    }

    public String toString() {
        return "CameraGraph.Session-" + this.debugId;
    }

    @Override // androidx.camera.camera2.pipe.CameraGraph.Session
    public Object unlock3A(Boolean r1, Boolean r2, Boolean r3, Function1<? super FrameMetadata, Boolean> r4, int r5, long r6, Continuation<? super Deferred<Result3A>> r8) {
        if (this.token.getReleased() == false) goto L5;
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("Cannot call unlock3A on ", this, " after close.");
        return null;
    L5:
        return this.controller3A.unlock3A(r1, r2, r3, r4, r5, Boxing.boxLong(r6));
    }

    @Override // androidx.camera.camera2.pipe.CameraGraph.Session
    public Object unlock3APostCapture(boolean r1, Continuation<? super Deferred<Result3A>> r2) {
        if (this.token.getReleased() == false) goto L5;
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("Cannot call unlock3APostCapture on ", this, " after close.");
        return null;
    L5:
        return this.controller3A.unlock3APostCapture(r1);
    }

    @Override // androidx.camera.camera2.pipe.CameraControls3A
    /* renamed from: update3A-ydBZfZg */
    public Deferred<Result3A> mo166update3AydBZfZg(AeMode r12, AfMode r13, AwbMode r14, List<MeteringRectangle> r15, List<MeteringRectangle> r16, List<MeteringRectangle> r17) {
        if (this.token.getReleased() == false) goto L5;
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("Cannot call update3A on ", this, " after close.");
        return null;
    L5:
        return Controller3A.m517update3A169HPGg$default(this.controller3A, r12, r13, r14, null, r15, r16, r17, 8, null);
    }
}
