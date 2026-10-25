package androidx.camera.camera2.impl;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.MeteringRectangle;
import androidx.camera.camera2.impl.UseCaseCameraRequestControl;
import androidx.camera.camera2.pipe.AeMode;
import androidx.camera.camera2.pipe.Lock3ABehavior;
import androidx.camera.camera2.pipe.Result3A;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.ExecutorsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\t\u0010\nJ=\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0016\u0010\u000e\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f\u0012\u0004\u0012\u00020\r0\u000b2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J=\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0016\u0010\u000e\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f\u0012\u0004\u0012\u00020\r0\u000b2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0017\u0010\u0016J/\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0010\u0010\u0019\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0\u00182\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ+\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u001d\u001a\u00020\u001c2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0016¢\u0006\u0004\b!\u0010\"J1\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010$\u001a\u00020#2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\r0\u000bH\u0016¢\u0006\u0004\b'\u0010(J\u0015\u0010*\u001a\b\u0012\u0004\u0012\u00020)0\u0013H\u0016¢\u0006\u0004\b*\u0010+J\u001d\u00100\u001a\b\u0012\u0004\u0012\u00020)0\u00132\u0006\u0010-\u001a\u00020,H\u0016¢\u0006\u0004\b.\u0010/Ju\u0010>\u001a\b\u0012\u0004\u0012\u00020)0\u00132\u000e\u00102\u001a\n\u0012\u0004\u0012\u000201\u0018\u00010\u00182\u000e\u00103\u001a\n\u0012\u0004\u0012\u000201\u0018\u00010\u00182\u000e\u00104\u001a\n\u0012\u0004\u0012\u000201\u0018\u00010\u00182\b\u00106\u001a\u0004\u0018\u0001052\b\u00107\u001a\u0004\u0018\u0001052\b\u00108\u001a\u0004\u0018\u0001052\b\u00109\u001a\u0004\u0018\u00010,2\u0006\u0010;\u001a\u00020:H\u0016¢\u0006\u0004\b<\u0010=J\u0015\u0010?\u001a\b\u0012\u0004\u0012\u00020)0\u0013H\u0016¢\u0006\u0004\b?\u0010+JE\u0010@\u001a\b\u0012\u0004\u0012\u00020)0\u00132\u000e\u00102\u001a\n\u0012\u0004\u0012\u000201\u0018\u00010\u00182\u000e\u00103\u001a\n\u0012\u0004\u0012\u000201\u0018\u00010\u00182\u000e\u00104\u001a\n\u0012\u0004\u0012\u000201\u0018\u00010\u0018H\u0016¢\u0006\u0004\b@\u0010AJC\u0010I\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010H0\u00130\u00182\f\u0010C\u001a\b\u0012\u0004\u0012\u00020B0\u00182\u0006\u0010E\u001a\u00020D2\u0006\u0010F\u001a\u00020D2\u0006\u0010G\u001a\u00020DH\u0016¢\u0006\u0004\bI\u0010JJ\u0010\u0010K\u001a\u00020\u001cH\u0096@¢\u0006\u0004\bK\u0010LJ\u000f\u0010M\u001a\u00020\u0014H\u0016¢\u0006\u0004\bM\u0010NR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010OR\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010PR\u0018\u0010Q\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bQ\u0010RR\u0014\u0010T\u001a\u00020S8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bT\u0010U¨\u0006V"}, d2 = {"Landroidx/camera/camera2/impl/DeferredUseCaseCameraRequestControl;", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "Ljavax/inject/Provider;", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl;", "implProvider", "Landroidx/camera/camera2/impl/UseCaseThreads;", "threads", "<init>", "(Ljavax/inject/Provider;Landroidx/camera/camera2/impl/UseCaseThreads;)V", "getOrCreateImpl", "()Landroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest$Key;", _UrlKt.FRAGMENT_ENCODE_SET, "values", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl$Type;", "type", "Landroidx/camera/core/impl/Config$OptionPriority;", "optionPriority", "Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET, "setParametersAsync", "(Ljava/util/Map;Landroidx/camera/camera2/impl/UseCaseCameraRequestControl$Type;Landroidx/camera/core/impl/Config$OptionPriority;)Lkotlinx/coroutines/Deferred;", "submitParameters", _UrlKt.FRAGMENT_ENCODE_SET, "keys", "removeParametersAsync", "(Ljava/util/List;Landroidx/camera/camera2/impl/UseCaseCameraRequestControl$Type;)Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET, "isPrimary", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "runningUseCases", "updateRepeatingRequestAsync", "(ZLjava/util/Collection;)Lkotlinx/coroutines/Deferred;", "Landroidx/camera/core/impl/Config;", "config", _UrlKt.FRAGMENT_ENCODE_SET, "tags", "updateCamera2ConfigAsync", "(Landroidx/camera/core/impl/Config;Ljava/util/Map;)Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/pipe/Result3A;", "setTorchOnAsync", "()Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/pipe/AeMode;", "aeMode", "setTorchOffAsync-MtizInI", "(I)Lkotlinx/coroutines/Deferred;", "setTorchOffAsync", "Landroid/hardware/camera2/params/MeteringRectangle;", "aeRegions", "afRegions", "awbRegions", "Landroidx/camera/camera2/pipe/Lock3ABehavior;", "aeLockBehavior", "afLockBehavior", "awbLockBehavior", "afTriggerStartAeMode", _UrlKt.FRAGMENT_ENCODE_SET, "timeLimitNs", "startFocusAndMeteringAsync-NxRnBj4", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Landroidx/camera/camera2/pipe/Lock3ABehavior;Landroidx/camera/camera2/pipe/Lock3ABehavior;Landroidx/camera/camera2/pipe/Lock3ABehavior;Landroidx/camera/camera2/pipe/AeMode;J)Lkotlinx/coroutines/Deferred;", "startFocusAndMeteringAsync", "cancelFocusAndMeteringAsync", "update3aRegions", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)Lkotlinx/coroutines/Deferred;", "Landroidx/camera/core/impl/CaptureConfig;", "captureSequence", _UrlKt.FRAGMENT_ENCODE_SET, "captureMode", "flashType", "flashMode", "Ljava/lang/Void;", "issueSingleCaptureAsync", "(Ljava/util/List;III)Ljava/util/List;", "awaitSurfaceSetup", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "close", "()V", "Ljavax/inject/Provider;", "Landroidx/camera/camera2/impl/UseCaseThreads;", "impl", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isClosed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDeferredUseCaseCameraRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DeferredUseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/DeferredUseCaseCameraRequestControl\n+ 2 UseCaseThreads.kt\nandroidx/camera/camera2/impl/UseCaseThreads\n*L\n1#1,223:1\n85#1,6:224\n85#1,6:230\n85#1,6:236\n85#1,6:242\n85#1,6:248\n85#1,6:254\n85#1,6:260\n85#1,6:266\n85#1,6:272\n85#1,6:278\n99#1,19:284\n126#1,6:303\n194#2:309\n*S KotlinDebug\n*F\n+ 1 DeferredUseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/DeferredUseCaseCameraRequestControl\n*L\n140#1:224,6\n146#1:230,6\n151#1:236,6\n156#1:242,6\n159#1:248,6\n163#1:254,6\n165#1:260,6\n178#1:266,6\n191#1:272,6\n199#1:278,6\n207#1:284,19\n211#1:303,6\n220#1:309\n*E\n"})
/* loaded from: classes3.dex */
public final class DeferredUseCaseCameraRequestControl implements UseCaseCameraRequestControl {
    private volatile UseCaseCameraRequestControlImpl impl;
    private final Provider<UseCaseCameraRequestControlImpl> implProvider;
    private final AtomicBoolean isClosed;
    private final UseCaseThreads threads;

    public DeferredUseCaseCameraRequestControl(Provider<UseCaseCameraRequestControlImpl> r1, UseCaseThreads r2) {
        this.implProvider = r1;
        this.threads = r2;
        this.isClosed = new AtomicBoolean(false);
    }

    public static final /* synthetic */ UseCaseCameraRequestControlImpl access$getImpl$p(DeferredUseCaseCameraRequestControl r0) {
        return r0.impl;
    }

    public static final /* synthetic */ UseCaseCameraRequestControlImpl access$getOrCreateImpl(DeferredUseCaseCameraRequestControl r0) {
        return r0.getOrCreateImpl();
    }

    private final UseCaseCameraRequestControlImpl getOrCreateImpl() {
        if (this.isClosed.get() == true) goto L14;
        UseCaseCameraRequestControlImpl r0 = this.impl;
        if (r0 == null) goto L7;
        return r0;
    L7:
        UseCaseCameraRequestControlImpl r02 = this.implProvider.get();
        if (this.isClosed.get() == true) goto L11;
        this.impl = r02;
        return r02;
    L11:
        r02.close();
        throw new CancellationException("UseCaseCameraRequestControl closed during initialization");
    L14:
        throw new CancellationException("UseCaseCameraRequestControl is closed");
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public Object awaitSurfaceSetup(Continuation<? super Boolean> r4) {
        UseCaseCameraRequestControlImpl r0 = this.impl;
        if (r0 == null) goto L7;
        return r0.awaitSurfaceSetup(r4);
    L7:
        return BuildersKt.withContext(ExecutorsKt.from(this.threads.getSequentialExecutor()), new DeferredUseCaseCameraRequestControl$awaitSurfaceSetup$$inlined$runOnSequentialSuspend$1(this, null), r4);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public Deferred<Result3A> cancelFocusAndMeteringAsync() {
        UseCaseCameraRequestControlImpl r0 = this.impl;
        if (r0 == null) goto L7;
        return r0.cancelFocusAndMeteringAsync();
    L7:
        return BuildersKt.async$default(this.threads.getSequentialScope(), null, null, new DeferredUseCaseCameraRequestControl$cancelFocusAndMeteringAsync$$inlined$runOnSequential$1(this, null), 3, null);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public void close() {
        if (this.isClosed.getAndSet(true) == false) goto L5;
        return;
    L5:
        BuildersKt.launch$default(this.threads.getSequentialScope(), null, null, new DeferredUseCaseCameraRequestControl$close$$inlined$confineLaunch$1(null, this), 3, null);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public List<Deferred<Void>> issueSingleCaptureAsync(List<CaptureConfig> r11, int r12, int r13, int r14) {
        int r0 = r11.size();
        UseCaseCameraRequestControlImpl r1 = this.impl;
        if (r1 != null) goto L5;
        Deferred r112 = BuildersKt.async$default(this.threads.getSequentialScope(), null, null, new DeferredUseCaseCameraRequestControl$issueSingleCaptureAsync$$inlined$runOnSequentialList$1(this, null, r11, r12, r13, r14), 3, null);
        ArrayList r122 = new ArrayList(r0);
        int r132 = 0;
    L7:
        if (r132 >= r0) goto L9;
        r122.add(BuildersKt.async$default(this.threads.getSequentialScope(), null, null, new DeferredUseCaseCameraRequestControl$runOnSequentialList$2$1(r112, r132, null), 3, null));
        r132 = r132 + 1;
        goto L7
    L9:
        return r122;
    L5:
        return r1.issueSingleCaptureAsync(r11, r12, r13, r14);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public Deferred<Unit> removeParametersAsync(List<? extends CaptureRequest.Key<?>> r8, UseCaseCameraRequestControl.Type r9) {
        UseCaseCameraRequestControlImpl r0 = this.impl;
        if (r0 == null) goto L7;
        return r0.removeParametersAsync(r8, r9);
    L7:
        return BuildersKt.async$default(this.threads.getSequentialScope(), null, null, new DeferredUseCaseCameraRequestControl$removeParametersAsync$$inlined$runOnSequential$1(this, null, r8, r9), 3, null);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public Deferred<Unit> setParametersAsync(Map<CaptureRequest.Key<?>, ? extends Object> r9, UseCaseCameraRequestControl.Type r10, Config.OptionPriority r11) {
        UseCaseCameraRequestControlImpl r0 = this.impl;
        if (r0 == null) goto L7;
        return r0.setParametersAsync(r9, r10, r11);
    L7:
        return BuildersKt.async$default(this.threads.getSequentialScope(), null, null, new DeferredUseCaseCameraRequestControl$setParametersAsync$$inlined$runOnSequential$1(this, null, r9, r10, r11), 3, null);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    /* renamed from: setTorchOffAsync-MtizInI, reason: not valid java name */
    public Deferred<Result3A> mo69setTorchOffAsyncMtizInI(int r8) {
        UseCaseCameraRequestControlImpl r0 = this.impl;
        if (r0 == null) goto L7;
        return r0.mo69setTorchOffAsyncMtizInI(r8);
    L7:
        return BuildersKt.async$default(this.threads.getSequentialScope(), null, null, new DeferredUseCaseCameraRequestControl$setTorchOffAsyncMtizInI$$inlined$runOnSequential$1(this, null, r8), 3, null);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public Deferred<Result3A> setTorchOnAsync() {
        UseCaseCameraRequestControlImpl r0 = this.impl;
        if (r0 == null) goto L7;
        return r0.setTorchOnAsync();
    L7:
        return BuildersKt.async$default(this.threads.getSequentialScope(), null, null, new DeferredUseCaseCameraRequestControl$setTorchOnAsync$$inlined$runOnSequential$1(this, null), 3, null);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    /* renamed from: startFocusAndMeteringAsync-NxRnBj4, reason: not valid java name */
    public Deferred<Result3A> mo70startFocusAndMeteringAsyncNxRnBj4(List<MeteringRectangle> r14, List<MeteringRectangle> r15, List<MeteringRectangle> r16, Lock3ABehavior r17, Lock3ABehavior r18, Lock3ABehavior r19, AeMode r20, long r21) {
        UseCaseCameraRequestControlImpl r0 = this.impl;
        if (r0 == null) goto L7;
        return r0.mo70startFocusAndMeteringAsyncNxRnBj4(r14, r15, r16, r17, r18, r19, r20, r21);
    L7:
        return BuildersKt.async$default(this.threads.getSequentialScope(), null, null, new DeferredUseCaseCameraRequestControl$startFocusAndMeteringAsyncNxRnBj4$$inlined$runOnSequential$1(this, null, r14, r15, r16, r17, r18, r19, r20, r21), 3, null);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public Deferred<Unit> submitParameters(Map<CaptureRequest.Key<?>, ? extends Object> r9, UseCaseCameraRequestControl.Type r10, Config.OptionPriority r11) {
        UseCaseCameraRequestControlImpl r0 = this.impl;
        if (r0 == null) goto L7;
        return r0.submitParameters(r9, r10, r11);
    L7:
        return BuildersKt.async$default(this.threads.getSequentialScope(), null, null, new DeferredUseCaseCameraRequestControl$submitParameters$$inlined$runOnSequential$1(this, null, r9, r10, r11), 3, null);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public Deferred<Result3A> update3aRegions(List<MeteringRectangle> r9, List<MeteringRectangle> r10, List<MeteringRectangle> r11) {
        UseCaseCameraRequestControlImpl r0 = this.impl;
        if (r0 == null) goto L7;
        return r0.update3aRegions(r9, r10, r11);
    L7:
        return BuildersKt.async$default(this.threads.getSequentialScope(), null, null, new DeferredUseCaseCameraRequestControl$update3aRegions$$inlined$runOnSequential$1(this, null, r9, r10, r11), 3, null);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public Deferred<Unit> updateCamera2ConfigAsync(Config r8, Map<String, ? extends Object> r9) {
        UseCaseCameraRequestControlImpl r0 = this.impl;
        if (r0 == null) goto L7;
        return r0.updateCamera2ConfigAsync(r8, r9);
    L7:
        return BuildersKt.async$default(this.threads.getSequentialScope(), null, null, new DeferredUseCaseCameraRequestControl$updateCamera2ConfigAsync$$inlined$runOnSequential$1(this, null, r8, r9), 3, null);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public Deferred<Unit> updateRepeatingRequestAsync(boolean r8, Collection<? extends UseCase> r9) {
        UseCaseCameraRequestControlImpl r0 = this.impl;
        if (r0 == null) goto L7;
        return r0.updateRepeatingRequestAsync(r8, r9);
    L7:
        return BuildersKt.async$default(this.threads.getSequentialScope(), null, null, new DeferredUseCaseCameraRequestControl$updateRepeatingRequestAsync$$inlined$runOnSequential$1(this, null, r8, r9), 3, null);
    }
}
