package androidx.camera.camera2.adapter;

import android.util.Log;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.impl.UseCaseManager;
import androidx.camera.camera2.impl.UseCaseThreads;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.core.Logger;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraConfigs;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.BuildConfig;

@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B9\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0019\u001a\u0004\u0018\u00010\u0016H\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001aH\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010 \u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0010H\u0016¢\u0006\u0004\b \u0010\u0014J\u0017\u0010\"\u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\"\u0010\u0014J\u0015\u0010%\u001a\b\u0012\u0004\u0012\u00020$0#H\u0016¢\u0006\u0004\b%\u0010&J\u000f\u0010'\u001a\u00020\u0006H\u0016¢\u0006\u0004\b'\u0010(J\u000f\u0010)\u001a\u00020\bH\u0016¢\u0006\u0004\b)\u0010*J\u001d\u0010.\u001a\u00020\u00122\f\u0010-\u001a\b\u0012\u0004\u0012\u00020,0+H\u0016¢\u0006\u0004\b.\u0010/J\u001d\u00101\u001a\u00020\u00122\f\u00100\u001a\b\u0012\u0004\u0012\u00020,0+H\u0016¢\u0006\u0004\b1\u0010/J\u0017\u00103\u001a\u00020\u00122\u0006\u00102\u001a\u00020,H\u0016¢\u0006\u0004\b3\u00104J\u0017\u00105\u001a\u00020\u00122\u0006\u00102\u001a\u00020,H\u0016¢\u0006\u0004\b5\u00104J\u0017\u00106\u001a\u00020\u00122\u0006\u00102\u001a\u00020,H\u0016¢\u0006\u0004\b6\u00104J\u0017\u00107\u001a\u00020\u00122\u0006\u00102\u001a\u00020,H\u0016¢\u0006\u0004\b7\u00104J\u000f\u00109\u001a\u000208H\u0016¢\u0006\u0004\b9\u0010:J\u0019\u0010<\u001a\u00020\u00122\b\u0010;\u001a\u0004\u0018\u000108H\u0016¢\u0006\u0004\b<\u0010=J\u000f\u0010>\u001a\u00020\u0012H\u0016¢\u0006\u0004\b>\u0010?J\u000f\u0010@\u001a\u00020\u0010H\u0016¢\u0006\u0004\b@\u0010AJ\u000f\u0010C\u001a\u00020BH\u0016¢\u0006\u0004\bC\u0010DR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010ER\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010FR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010GR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010HR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010IR\u0014\u0010K\u001a\u00020J8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bK\u0010LR\u0016\u0010M\u001a\u0002088\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bM\u0010NR\u0014\u0010P\u001a\u00020O8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bP\u0010QR\u0014\u0010@\u001a\u00020R8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b@\u0010S¨\u0006T"}, d2 = {"Landroidx/camera/camera2/adapter/CameraInternalAdapter;", "Landroidx/camera/core/impl/CameraInternal;", "Landroidx/camera/camera2/config/CameraConfig;", "config", "Landroidx/camera/camera2/impl/UseCaseManager;", "useCaseManager", "Landroidx/camera/core/impl/CameraInfoInternal;", "cameraInfo", "Landroidx/camera/core/impl/CameraControlInternal;", "cameraController", "Landroidx/camera/camera2/impl/UseCaseThreads;", "threads", "Landroidx/camera/camera2/adapter/CameraStateAdapter;", "cameraStateAdapter", "<init>", "(Landroidx/camera/camera2/config/CameraConfig;Landroidx/camera/camera2/impl/UseCaseManager;Landroidx/camera/core/impl/CameraInfoInternal;Landroidx/camera/core/impl/CameraControlInternal;Landroidx/camera/camera2/impl/UseCaseThreads;Landroidx/camera/camera2/adapter/CameraStateAdapter;)V", _UrlKt.FRAGMENT_ENCODE_SET, "createImmediately", _UrlKt.FRAGMENT_ENCODE_SET, "setCameraGraphCreationMode$camera_camera2", "(Z)V", "setCameraGraphCreationMode", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "getDeferredCameraGraphConfig$camera_camera2", "()Landroidx/camera/camera2/pipe/CameraGraph$Config;", "getDeferredCameraGraphConfig", "Landroidx/camera/camera2/pipe/CameraGraph;", "cameraGraph", "resumeDeferredCameraGraphCreation$camera_camera2", "(Landroidx/camera/camera2/pipe/CameraGraph;)V", "resumeDeferredCameraGraphCreation", "isPrimary", "setPrimary", "enabled", "setActiveResumingMode", "Lcom/google/common/util/concurrent/ListenableFuture;", "Ljava/lang/Void;", BuildConfig.BUILD_TYPE, "()Lcom/google/common/util/concurrent/ListenableFuture;", "getCameraInfoInternal", "()Landroidx/camera/core/impl/CameraInfoInternal;", "getCameraControlInternal", "()Landroidx/camera/core/impl/CameraControlInternal;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "useCasesToAdd", "attachUseCases", "(Ljava/util/Collection;)V", "useCasesToRemove", "detachUseCases", "useCase", "onUseCaseActive", "(Landroidx/camera/core/UseCase;)V", "onUseCaseUpdated", "onUseCaseReset", "onUseCaseInactive", "Landroidx/camera/core/impl/CameraConfig;", "getExtendedConfig", "()Landroidx/camera/core/impl/CameraConfig;", "cameraConfig", "setExtendedConfig", "(Landroidx/camera/core/impl/CameraConfig;)V", "onRemoved", "()V", "isRemoved", "()Z", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroidx/camera/camera2/impl/UseCaseManager;", "Landroidx/camera/core/impl/CameraInfoInternal;", "Landroidx/camera/core/impl/CameraControlInternal;", "Landroidx/camera/camera2/impl/UseCaseThreads;", "Landroidx/camera/camera2/adapter/CameraStateAdapter;", "Landroidx/camera/camera2/pipe/CameraId;", "cameraId", "Ljava/lang/String;", "coreCameraConfig", "Landroidx/camera/core/impl/CameraConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "debugId", "I", "Lkotlinx/atomicfu/AtomicBoolean;", "Lkotlinx/atomicfu/AtomicBoolean;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraInternalAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraInternalAdapter.kt\nandroidx/camera/camera2/adapter/CameraInternalAdapter\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,172:1\n85#2,4:173\n85#2,4:177\n85#2,4:181\n85#2,4:185\n*S KotlinDebug\n*F\n+ 1 CameraInternalAdapter.kt\nandroidx/camera/camera2/adapter/CameraInternalAdapter\n*L\n61#1:173,4\n78#1:177,4\n82#1:181,4\n153#1:185,4\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraInternalAdapter implements CameraInternal {
    private final CameraControlInternal cameraController;
    private final String cameraId;
    private final CameraInfoInternal cameraInfo;
    private final CameraStateAdapter cameraStateAdapter;
    private CameraConfig coreCameraConfig;
    private final int debugId;
    private final AtomicBoolean isRemoved;
    private final UseCaseThreads threads;
    private final UseCaseManager useCaseManager;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.adapter.CameraInternalAdapter$onRemoved$2", f = "CameraInternalAdapter.kt", i = {}, l = {161}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.adapter.CameraInternalAdapter$onRemoved$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ CameraInternalAdapter this$0;

        public AnonymousClass2(CameraInternalAdapter r1, Continuation<? super AnonymousClass2> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass2(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r4);
        L12:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r4);
            CameraInternalAdapter.access$getCameraStateAdapter$p(this.this$0).onRemoved();
            UseCaseManager r42 = CameraInternalAdapter.access$getUseCaseManager$p(this.this$0);
            this.label = 1;
            if (r42.close(this) != r0) goto L12;
            return r0;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.adapter.CameraInternalAdapter$release$1", f = "CameraInternalAdapter.kt", i = {}, l = {96}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.adapter.CameraInternalAdapter$release$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ CameraInternalAdapter this$0;

        public AnonymousClass1(CameraInternalAdapter r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass1(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r4);
        L11:
            CoroutineScopeKt.cancel$default(CameraInternalAdapter.access$getThreads$p(this.this$0).getScope(), null, 1, null);
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r4);
            UseCaseManager r42 = CameraInternalAdapter.access$getUseCaseManager$p(this.this$0);
            this.label = 1;
            if (r42.close(this) != r0) goto L11;
            return r0;
        }
    }

    public CameraInternalAdapter(androidx.camera.camera2.config.CameraConfig r1, UseCaseManager r2, CameraInfoInternal r3, CameraControlInternal r4, UseCaseThreads r5, CameraStateAdapter r6) {
        this.useCaseManager = r2;
        this.cameraInfo = r3;
        this.cameraController = r4;
        this.threads = r5;
        this.cameraStateAdapter = r6;
        this.cameraId = r1.m37getCameraIdDz_R5H8();
        this.coreCameraConfig = CameraConfigs.defaultConfig();
        this.debugId = CameraInternalAdapterKt.getCameraAdapterIds().incrementAndGet();
        this.isRemoved = AtomicFU.atomic(false);
        Camera2Logger r12 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L6;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Created " + this + " for " + CameraId.m232toStringimpl(access$getCameraId$p(this)));
        return;
    }

    public static final /* synthetic */ String access$getCameraId$p(CameraInternalAdapter r0) {
        return r0.cameraId;
    }

    public static final /* synthetic */ CameraStateAdapter access$getCameraStateAdapter$p(CameraInternalAdapter r0) {
        return r0.cameraStateAdapter;
    }

    public static final /* synthetic */ UseCaseThreads access$getThreads$p(CameraInternalAdapter r0) {
        return r0.threads;
    }

    public static final /* synthetic */ UseCaseManager access$getUseCaseManager$p(CameraInternalAdapter r0) {
        return r0.useCaseManager;
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void attachUseCases(Collection<UseCase> r1) {
        this.useCaseManager.attach(CollectionsKt.toList(r1));
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void detachUseCases(Collection<UseCase> r1) {
        this.useCaseManager.detach(CollectionsKt.toList(r1));
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public CameraControlInternal getCameraControlInternal() {
        return this.cameraController;
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public CameraInfoInternal getCameraInfoInternal() {
        return this.cameraInfo;
    }

    public final CameraGraph.Config getDeferredCameraGraphConfig$camera_camera2() {
        return this.useCaseManager.getDeferredCameraGraphConfig$camera_camera2();
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public CameraConfig getExtendedConfig() {
        return this.coreCameraConfig;
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public boolean isRemoved() {
        return this.isRemoved.getValue();
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void onRemoved() {
        Camera2Logger r0 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L6;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), this + " received removed signal. Cleaning up.");
    L6:
        if (this.isRemoved.compareAndSet(false, true) == false) goto L9;
        BuildersKt.launch$default(this.threads.getScope(), null, null, new AnonymousClass2(this, null), 3, null);
        return;
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseActive(UseCase r1) {
        this.useCaseManager.activate(r1);
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseInactive(UseCase r1) {
        this.useCaseManager.deactivate(r1);
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseReset(UseCase r1) {
        this.useCaseManager.reset(r1);
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseUpdated(UseCase r1) {
        this.useCaseManager.update(r1);
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public ListenableFuture<Void> release() {
        return CoroutineAdaptersKt.asListenableFuture$default(BuildersKt.launch$default(this.threads.getScope(), null, null, new AnonymousClass1(this, null), 3, null), null, 1, null);
    }

    public final void resumeDeferredCameraGraphCreation$camera_camera2(CameraGraph r1) {
        this.useCaseManager.resumeDeferredComponentCreation$camera_camera2(r1);
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void setActiveResumingMode(boolean r1) {
        this.useCaseManager.setActiveResumeMode(r1);
    }

    public final void setCameraGraphCreationMode$camera_camera2(boolean r1) {
        this.useCaseManager.setCameraGraphCreationMode$camera_camera2(r1);
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void setExtendedConfig(CameraConfig r2) {
        if (r2 != null) goto L4;
        CameraConfig r0 = CameraConfigs.defaultConfig();
    L5:
        this.coreCameraConfig = r0;
        if (r2 == null) goto L8;
        r2.getSessionProcessor(null);
    L8:
        this.useCaseManager.setSessionProcessor$camera_camera2(null);
        return;
    L4:
        r0 = r2;
        goto L5
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void setPrimary(boolean r1) {
        this.useCaseManager.setPrimary(r1);
    }

    public String toString() {
        return "CameraInternalAdapter<" + CameraId.m232toStringimpl(this.cameraId) + '(' + this.debugId + ")>";
    }
}
