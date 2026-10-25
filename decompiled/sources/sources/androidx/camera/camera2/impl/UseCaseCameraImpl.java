package androidx.camera.camera2.impl;

import android.util.Log;
import androidx.camera.camera2.adapter.SessionConfigAdapter;
import androidx.camera.camera2.config.UseCaseGraphContext;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.core.Logger;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.SessionProcessor;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.inject.Provider;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Job;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0007\u0018\u00002\u00020\u0001BU\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\n\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\n¢\u0006\u0004\b\u0011\u0010\u0012J\u0011\u0010\u0016\u001a\u0004\u0018\u00010\u0013H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J!\u0010\u001d\u001a\u00020\u001a2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0019\u001a\u00020\u0018H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010!\u001a\u00020 H\u0016¢\u0006\u0004\b!\u0010\"J\u0017\u0010%\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020#H\u0016¢\u0006\u0004\b%\u0010&J%\u0010+\u001a\u00020 2\u0006\u0010'\u001a\u00020#2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020)0(H\u0016¢\u0006\u0004\b+\u0010,J\u000f\u0010.\u001a\u00020-H\u0016¢\u0006\u0004\b.\u0010/R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u00100R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u00101R\u001a\u0010\t\u001a\u00020\b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\t\u00102\u001a\u0004\b3\u00104R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u00105R\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u00105R\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u00105R\u0014\u00107\u001a\u0002068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R#\u0010A\u001a\n <*\u0004\u0018\u00010\u000b0\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@R#\u0010E\u001a\n <*\u0004\u0018\u00010\r0\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bB\u0010>\u001a\u0004\bC\u0010DR#\u0010I\u001a\n <*\u0004\u0018\u00010\u000f0\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bF\u0010>\u001a\u0004\bG\u0010H¨\u0006J"}, d2 = {"Landroidx/camera/camera2/impl/UseCaseCameraImpl;", "Landroidx/camera/camera2/impl/UseCaseCamera;", "Landroidx/camera/camera2/config/UseCaseGraphContext;", "useCaseGraphContext", "Landroidx/camera/camera2/impl/UseCaseThreads;", "threads", "Landroidx/camera/core/impl/SessionProcessor;", "sessionProcessor", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "requestControl", "Ljavax/inject/Provider;", "Landroidx/camera/camera2/impl/UseCaseSurfaceManager;", "useCaseSurfaceManagerProvider", "Landroidx/camera/camera2/adapter/SessionConfigAdapter;", "sessionConfigAdapterProvider", "Landroidx/camera/camera2/impl/CapturePipeline;", "capturePipelineProvider", "<init>", "(Landroidx/camera/camera2/config/UseCaseGraphContext;Landroidx/camera/camera2/impl/UseCaseThreads;Landroidx/camera/core/impl/SessionProcessor;Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;Ljavax/inject/Provider;Ljavax/inject/Provider;Ljavax/inject/Provider;)V", "Landroidx/camera/camera2/pipe/StreamId;", "findStillCaptureStreamId-4TVKcYk", "()Landroidx/camera/camera2/pipe/StreamId;", "findStillCaptureStreamId", "stillCaptureStreamId", "Landroidx/camera/camera2/pipe/CameraGraph;", "cameraGraph", _UrlKt.FRAGMENT_ENCODE_SET, "setCaptureSessionRequestProcessor-9O56998", "(Landroidx/camera/camera2/pipe/StreamId;Landroidx/camera/camera2/pipe/CameraGraph;)V", "setCaptureSessionRequestProcessor", "start", "()V", "Lkotlinx/coroutines/Job;", "close", "()Lkotlinx/coroutines/Job;", _UrlKt.FRAGMENT_ENCODE_SET, "enabled", "setActiveResumeMode", "(Z)V", "isPrimary", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "runningUseCases", "updateRepeatingRequestAsync", "(ZLjava/util/Collection;)Lkotlinx/coroutines/Job;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroidx/camera/camera2/config/UseCaseGraphContext;", "Landroidx/camera/camera2/impl/UseCaseThreads;", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "getRequestControl", "()Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "Ljavax/inject/Provider;", _UrlKt.FRAGMENT_ENCODE_SET, "debugId", "I", "Lkotlinx/atomicfu/AtomicBoolean;", "closed", "Lkotlinx/atomicfu/AtomicBoolean;", "kotlin.jvm.PlatformType", "useCaseSurfaceManager$delegate", "Lkotlin/Lazy;", "getUseCaseSurfaceManager", "()Landroidx/camera/camera2/impl/UseCaseSurfaceManager;", "useCaseSurfaceManager", "sessionConfigAdapter$delegate", "getSessionConfigAdapter", "()Landroidx/camera/camera2/adapter/SessionConfigAdapter;", "sessionConfigAdapter", "capturePipeline$delegate", "getCapturePipeline", "()Landroidx/camera/camera2/impl/CapturePipeline;", "capturePipeline", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUseCaseCamera.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseCamera.kt\nandroidx/camera/camera2/impl/UseCaseCameraImpl\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 UseCaseThreads.kt\nandroidx/camera/camera2/impl/UseCaseThreads\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,229:1\n85#2,4:230\n194#3:234\n194#3:237\n194#3:238\n295#4,2:235\n*S KotlinDebug\n*F\n+ 1 UseCaseCamera.kt\nandroidx/camera/camera2/impl/UseCaseCameraImpl\n*L\n88#1:230,4\n96#1:234\n183#1:237\n195#1:238\n144#1:235,2\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseCameraImpl implements UseCaseCamera {
    private final Lazy capturePipeline$delegate;
    private final Provider<CapturePipeline> capturePipelineProvider;
    private final AtomicBoolean closed;
    private final int debugId;
    private final UseCaseCameraRequestControl requestControl;
    private final Lazy sessionConfigAdapter$delegate;
    private final Provider<SessionConfigAdapter> sessionConfigAdapterProvider;
    private final UseCaseThreads threads;
    private final UseCaseGraphContext useCaseGraphContext;
    private final Lazy useCaseSurfaceManager$delegate;
    private final Provider<UseCaseSurfaceManager> useCaseSurfaceManagerProvider;

    public static UseCaseSurfaceManager $r8$lambda$CfKAdXTgtHJW4L4tTKAd9BOZDbI(UseCaseCameraImpl r0) {
        return r0.useCaseSurfaceManagerProvider.get();
    }

    /* renamed from: $r8$lambda$D-_OQXyeg10yZNQLenFuuH53P_8, reason: not valid java name */
    public static CapturePipeline m90$r8$lambda$D_OQXyeg10yZNQLenFuuH53P_8(UseCaseCameraImpl r0) {
        return r0.capturePipelineProvider.get();
    }

    /* renamed from: $r8$lambda$d9EeqiQ4ZL__GvVM0t5iDA-Oay4, reason: not valid java name */
    public static SessionConfigAdapter m91$r8$lambda$d9EeqiQ4ZL__GvVM0t5iDAOay4(UseCaseCameraImpl r0) {
        return r0.sessionConfigAdapterProvider.get();
    }

    public UseCaseCameraImpl(UseCaseGraphContext r1, UseCaseThreads r2, SessionProcessor r3, UseCaseCameraRequestControl r4, Provider<UseCaseSurfaceManager> r5, Provider<SessionConfigAdapter> r6, Provider<CapturePipeline> r7) {
        this.useCaseGraphContext = r1;
        this.threads = r2;
        this.requestControl = r4;
        this.useCaseSurfaceManagerProvider = r5;
        this.sessionConfigAdapterProvider = r6;
        this.capturePipelineProvider = r7;
        this.debugId = UseCaseCameraKt.getUseCaseCameraIds().incrementAndGet();
        this.closed = AtomicFU.atomic(false);
        Camera2Logger r12 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L5;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Configured " + this);
    L5:
        this.useCaseSurfaceManager$delegate = LazyKt.lazy(new UseCaseCameraImpl$$ExternalSyntheticLambda0(this));
        this.sessionConfigAdapter$delegate = LazyKt.lazy(new UseCaseCameraImpl$$ExternalSyntheticLambda1(this));
        this.capturePipeline$delegate = LazyKt.lazy(new UseCaseCameraImpl$$ExternalSyntheticLambda2(this));
    }

    /* renamed from: access$findStillCaptureStreamId-4TVKcYk, reason: not valid java name */
    public static final /* synthetic */ StreamId m92access$findStillCaptureStreamId4TVKcYk(UseCaseCameraImpl r0) {
        return r0.m94findStillCaptureStreamId4TVKcYk();
    }

    public static final /* synthetic */ AtomicBoolean access$getClosed$p(UseCaseCameraImpl r0) {
        return r0.closed;
    }

    public static final /* synthetic */ SessionConfigAdapter access$getSessionConfigAdapter(UseCaseCameraImpl r0) {
        return r0.getSessionConfigAdapter();
    }

    public static final /* synthetic */ SessionProcessor access$getSessionProcessor$p(UseCaseCameraImpl r0) {
        r0.getClass();
        return null;
    }

    public static final /* synthetic */ UseCaseGraphContext access$getUseCaseGraphContext$p(UseCaseCameraImpl r0) {
        return r0.useCaseGraphContext;
    }

    public static final /* synthetic */ UseCaseSurfaceManager access$getUseCaseSurfaceManager(UseCaseCameraImpl r0) {
        return r0.getUseCaseSurfaceManager();
    }

    /* renamed from: access$setCaptureSessionRequestProcessor-9O56998, reason: not valid java name */
    public static final /* synthetic */ void m93access$setCaptureSessionRequestProcessor9O56998(UseCaseCameraImpl r0, StreamId r1, CameraGraph r2) {
        r0.m95setCaptureSessionRequestProcessor9O56998(r1, r2);
    }

    /* renamed from: findStillCaptureStreamId-4TVKcYk, reason: not valid java name */
    private final StreamId m94findStillCaptureStreamId4TVKcYk() {
        SessionConfig r0 = getSessionConfigAdapter().getValidSessionConfigOrNull();
        if (r0 != null) goto L5;
        return null;
    L5:
        List<DeferrableSurface> r2 = r0.getRepeatingCaptureConfig().getSurfaces();
        Iterator<T> r02 = r0.getSurfaces().iterator();
    L7:
        if (r02.hasNext() == false) goto L11;
        Object r3 = r02.next();
        if (r2.contains((DeferrableSurface) r3) == true) goto L7;
    L12:
        DeferrableSurface r32 = (DeferrableSurface) r3;
        if (r32 != null) goto L16;
        return null;
    L16:
        return (StreamId) CollectionsKt.firstOrNull(this.useCaseGraphContext.getStreamIdsFromSurfaces(CollectionsKt.listOf(r32)));
    L11:
        r3 = null;
        goto L12
    }

    private final SessionConfigAdapter getSessionConfigAdapter() {
        return (SessionConfigAdapter) this.sessionConfigAdapter$delegate.getValue();
    }

    private final UseCaseSurfaceManager getUseCaseSurfaceManager() {
        return (UseCaseSurfaceManager) this.useCaseSurfaceManager$delegate.getValue();
    }

    /* renamed from: setCaptureSessionRequestProcessor-9O56998, reason: not valid java name */
    private final void m95setCaptureSessionRequestProcessor9O56998(StreamId r1, CameraGraph r2) {
    }

    @Override // androidx.camera.camera2.impl.UseCaseCamera
    public Job close() {
        if (this.closed.compareAndSet(false, true) == false) goto L7;
        getRequestControl().close();
        return BuildersKt.launch$default(this.threads.getSequentialScope(), null, null, new UseCaseCameraImpl$close$$inlined$confineLaunch$1(null, this), 3, null);
    L7:
        return CompletableDeferredKt.CompletableDeferred(Unit.INSTANCE);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCamera
    public UseCaseCameraRequestControl getRequestControl() {
        return this.requestControl;
    }

    @Override // androidx.camera.camera2.impl.UseCaseCamera
    public void setActiveResumeMode(boolean r8) {
        BuildersKt.launch$default(this.threads.getSequentialScope(), null, null, new UseCaseCameraImpl$setActiveResumeMode$$inlined$confineLaunch$1(null, this, r8), 3, null);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCamera
    public void start() {
        BuildersKt.launch$default(this.threads.getSequentialScope(), null, null, new UseCaseCameraImpl$start$$inlined$confineLaunch$1(null, this), 3, null);
    }

    public String toString() {
        return "UseCaseCamera-" + this.debugId;
    }

    @Override // androidx.camera.camera2.impl.UseCaseCamera
    public Job updateRepeatingRequestAsync(boolean r1, Collection<? extends UseCase> r2) {
        return getRequestControl().updateRepeatingRequestAsync(r1, r2);
    }
}
