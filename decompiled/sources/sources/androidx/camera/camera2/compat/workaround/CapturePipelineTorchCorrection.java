package androidx.camera.camera2.compat.workaround;

import androidx.camera.camera2.adapter.CaptureConfigAdapter;
import androidx.camera.camera2.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.compat.quirk.TorchIsClosedAfterImageCapturingQuirk;
import androidx.camera.camera2.impl.CameraProperties;
import androidx.camera.camera2.impl.CapturePipeline;
import androidx.camera.camera2.impl.CapturePipelineImpl;
import androidx.camera.camera2.impl.TorchControl;
import androidx.camera.camera2.impl.UseCaseThreads;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.inject.Provider;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0007\u0018\u0000 62\u00020\u0001:\u00016B/\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ%\u0010\u0015\u001a\u00020\u00122\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0016\u0010\u0017JT\u0010#\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010 0\u001f0\r2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001bH\u0096@¢\u0006\u0004\b!\u0010\"R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010$R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010%R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010&R\u001b\u0010)\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010\u0017R#\u0010.\u001a\n **\u0004\u0018\u00010\u00050\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b+\u0010(\u001a\u0004\b,\u0010-R*\u00100\u001a\u00020\u001b2\u0006\u0010/\u001a\u00020\u001b8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b0\u00101\u001a\u0004\b2\u00103\"\u0004\b4\u00105¨\u00067"}, d2 = {"Landroidx/camera/camera2/compat/workaround/CapturePipelineTorchCorrection;", "Landroidx/camera/camera2/impl/CapturePipeline;", "Landroidx/camera/camera2/impl/CameraProperties;", "cameraProperties", "Ljavax/inject/Provider;", "Landroidx/camera/camera2/impl/CapturePipelineImpl;", "capturePipelineImplProvider", "Landroidx/camera/camera2/impl/UseCaseThreads;", "threads", "Landroidx/camera/camera2/impl/TorchControl;", "torchControl", "<init>", "(Landroidx/camera/camera2/impl/CameraProperties;Ljavax/inject/Provider;Landroidx/camera/camera2/impl/UseCaseThreads;Landroidx/camera/camera2/impl/TorchControl;)V", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/CaptureConfig;", "captureConfigs", "Landroidx/camera/camera2/pipe/RequestTemplate;", "requestTemplate", _UrlKt.FRAGMENT_ENCODE_SET, "isCorrectionRequired-0UCm73U", "(Ljava/util/List;I)Z", "isCorrectionRequired", "isTorchOn", "()Z", "configs", "Landroidx/camera/core/impl/Config;", "sessionConfigOptions", _UrlKt.FRAGMENT_ENCODE_SET, "captureMode", "flashType", "flashMode", "Lkotlinx/coroutines/Deferred;", "Ljava/lang/Void;", "submitStillCaptures-BvXKQx0", "(Ljava/util/List;ILandroidx/camera/core/impl/Config;IIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "submitStillCaptures", "Ljavax/inject/Provider;", "Landroidx/camera/camera2/impl/UseCaseThreads;", "Landroidx/camera/camera2/impl/TorchControl;", "isLegacyDevice$delegate", "Lkotlin/Lazy;", "isLegacyDevice", "kotlin.jvm.PlatformType", "capturePipelineImpl$delegate", "getCapturePipelineImpl", "()Landroidx/camera/camera2/impl/CapturePipelineImpl;", "capturePipelineImpl", "value", "template", "I", "getTemplate", "()I", "setTemplate", "(I)V", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCapturePipelineTorchCorrection.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CapturePipelineTorchCorrection.kt\nandroidx/camera/camera2/compat/workaround/CapturePipelineTorchCorrection\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,131:1\n1761#2,3:132\n*S KotlinDebug\n*F\n+ 1 CapturePipelineTorchCorrection.kt\nandroidx/camera/camera2/compat/workaround/CapturePipelineTorchCorrection\n*L\n118#1:132,3\n*E\n"})
/* loaded from: classes3.dex */
public final class CapturePipelineTorchCorrection implements CapturePipeline {
    public static final Companion Companion = null;
    private static final boolean isEnabled = false;
    private final Lazy capturePipelineImpl$delegate;
    private final Provider<CapturePipelineImpl> capturePipelineImplProvider;
    private final Lazy isLegacyDevice$delegate;
    private int template;
    private final UseCaseThreads threads;
    private final TorchControl torchControl;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/camera/camera2/compat/workaround/CapturePipelineTorchCorrection$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final boolean isEnabled() {
            return CapturePipelineTorchCorrection.access$isEnabled$cp();
        }
    }

    /* renamed from: $r8$lambda$3Ap0d8vE-z_oSobKLaOR3wveA1s, reason: not valid java name */
    public static CapturePipelineImpl m31$r8$lambda$3Ap0d8vEz_oSobKLaOR3wveA1s(CapturePipelineTorchCorrection r0) {
        return r0.capturePipelineImplProvider.get();
    }

    public static boolean $r8$lambda$irDySwcMGzQfb0BZOlN7Zg_pOLE(CameraProperties r1) {
        return CameraMetadata.Companion.isHardwareLevelLegacy(r1.getMetadata());
    }

    static {
        Companion = new Companion(null);
        if (DeviceQuirks.INSTANCE.get(TorchIsClosedAfterImageCapturingQuirk.class) == null) goto L5;
        boolean r0 = true;
    L6:
        isEnabled = r0;
        return;
    L5:
        r0 = false;
        goto L6
    }

    public CapturePipelineTorchCorrection(CameraProperties r1, Provider<CapturePipelineImpl> r2, UseCaseThreads r3, TorchControl r4) {
        this.capturePipelineImplProvider = r2;
        this.threads = r3;
        this.torchControl = r4;
        this.isLegacyDevice$delegate = LazyKt.lazy(new CapturePipelineTorchCorrection$$ExternalSyntheticLambda0(r1));
        this.capturePipelineImpl$delegate = LazyKt.lazy(new CapturePipelineTorchCorrection$$ExternalSyntheticLambda1(this));
        this.template = 1;
    }

    public static final /* synthetic */ TorchControl access$getTorchControl$p(CapturePipelineTorchCorrection r0) {
        return r0.torchControl;
    }

    public static final /* synthetic */ boolean access$isEnabled$cp() {
        return isEnabled;
    }

    private final CapturePipelineImpl getCapturePipelineImpl() {
        return (CapturePipelineImpl) this.capturePipelineImpl$delegate.getValue();
    }

    /* renamed from: isCorrectionRequired-0UCm73U, reason: not valid java name */
    private final boolean m32isCorrectionRequired0UCm73U(List<CaptureConfig> r4, int r5) {
        List<CaptureConfig> r42 = r4;
        if ((r42 instanceof Collection) == true) goto L5;
    L7:
        Iterator<T> r43 = r42.iterator();
    L9:
        if (r43.hasNext() == false) goto L22;
        CaptureConfig r0 = (CaptureConfig) r43.next();
        if (CaptureConfigAdapter.Companion.m12getStillCaptureTemplateCMLptTo$camera_camera2(r0, r5, isLegacyDevice()) != 2) goto L9;
        if (isTorchOn() == false) goto L16;
        return true;
    L16:
        return false;
    L22:
        return false;
    L5:
        if (r42.isEmpty() == false) goto L7;
        return false;
    }

    private final boolean isLegacyDevice() {
        return ((Boolean) this.isLegacyDevice$delegate.getValue()).booleanValue();
    }

    private final boolean isTorchOn() {
        Integer r1 = this.torchControl.getTorchStateLiveData().getValue();
        if (r1 != null) goto L6;
        return false;
    L6:
        if (r1.intValue() != 1) goto L8;
        return true;
    L8:
        return false;
    }

    @Override // androidx.camera.camera2.impl.CapturePipeline
    public void setTemplate(int r2) {
        getCapturePipelineImpl().setTemplate(r2);
        this.template = r2;
    }

    @Override // androidx.camera.camera2.impl.CapturePipeline
    /* renamed from: submitStillCaptures-BvXKQx0, reason: not valid java name */
    public Object mo33submitStillCapturesBvXKQx0(List<CaptureConfig> r13, int r14, Config r15, int r16, int r17, int r18, Continuation<? super List<? extends Deferred<Void>>> r19) {
        if ((r19 instanceof CapturePipelineTorchCorrection$submitStillCaptures$1) == false) goto L8;
        CapturePipelineTorchCorrection$submitStillCaptures$1 r1 = (CapturePipelineTorchCorrection$submitStillCaptures$1) r19;
        int r2 = r1.label;
        if ((r2 & Integer.MIN_VALUE) == 0) goto L8;
        r1.label = r2 - Integer.MIN_VALUE;
    L7:
        CapturePipelineTorchCorrection$submitStillCaptures$1 r9 = r1;
        Object r0 = r9.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r22 = r9.label;
        if (r22 == 0) goto L15;
        if (r22 != 1) goto L13;
        boolean r132 = r9.Z$0;
        ResultKt.throwOnFailure(r0);
    L19:
        List r02 = (List) r0;
        if (r132 == false) goto L22;
        BuildersKt.launch$default(this.threads.getSequentialScope(), null, null, new CapturePipelineTorchCorrection$submitStillCaptures$2(r02, this, null), 3, null);
    L22:
        return r02;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ResultKt.throwOnFailure(r0);
        boolean r03 = m32isCorrectionRequired0UCm73U(r13, r14);
        CapturePipelineImpl r23 = getCapturePipelineImpl();
        r9.Z$0 = r03;
        r9.label = 1;
        Object r133 = r23.mo33submitStillCapturesBvXKQx0(r13, r14, r15, r16, r17, r18, r9);
        if (r133 != r12) goto L18;
        return r12;
    L18:
        r0 = r133;
        r132 = r03;
    L8:
        r1 = new CapturePipelineTorchCorrection$submitStillCaptures$1(this, r19);
        goto L7
    }
}
