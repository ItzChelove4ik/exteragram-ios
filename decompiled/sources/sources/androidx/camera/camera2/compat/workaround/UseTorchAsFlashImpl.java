package androidx.camera.camera2.compat.workaround;

import android.os.Build;
import androidx.camera.camera2.compat.quirk.CameraQuirks;
import androidx.camera.camera2.compat.quirk.UltraWideFlashCaptureUnderexposureQuirk;
import androidx.camera.camera2.internal.IntrinsicZoomCalculator;
import androidx.camera.camera2.pipe.CameraDevices;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.FrameMetadata;
import androidx.camera.camera2.pipe.core.Log;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ.\u0010\u0010\u001a\u00020\u000b2\u001e\u0010\u0011\u001a\u001a\b\u0001\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0012H\u0096@¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u000b*\u00020\u0014H\u0003¢\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\u001a"}, d2 = {"Landroidx/camera/camera2/compat/workaround/UseTorchAsFlashImpl;", "Landroidx/camera/camera2/compat/workaround/UseTorchAsFlash;", "cameraQuirks", "Landroidx/camera/camera2/compat/quirk/CameraQuirks;", "cameraDevices", "Landroidx/camera/camera2/pipe/CameraDevices;", "intrinsicZoomCalculator", "Landroidx/camera/camera2/internal/IntrinsicZoomCalculator;", "<init>", "(Landroidx/camera/camera2/compat/quirk/CameraQuirks;Landroidx/camera/camera2/pipe/CameraDevices;Landroidx/camera/camera2/internal/IntrinsicZoomCalculator;)V", "hasUwCameraUnderexposedFlashCaptureQuirk", _UrlKt.FRAGMENT_ENCODE_SET, "getHasUwCameraUnderexposedFlashCaptureQuirk", "()Z", "hasUwCameraUnderexposedFlashCaptureQuirk$delegate", "Lkotlin/Lazy;", "shouldUseTorchAsFlash", "frameMetadata", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "Landroidx/camera/camera2/pipe/FrameMetadata;", _UrlKt.FRAGMENT_ENCODE_SET, "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isUltraWideCamera", "(Landroidx/camera/camera2/pipe/FrameMetadata;)Ljava/lang/Boolean;", "shouldDisableAePrecapture", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUseTorchAsFlash.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseTorchAsFlash.kt\nandroidx/camera/camera2/compat/workaround/UseTorchAsFlashImpl\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,171:1\n50#2,2:172\n71#2,2:174\n71#2,2:176\n71#2,2:178\n71#2,2:180\n71#2,2:182\n50#2,2:184\n*S KotlinDebug\n*F\n+ 1 UseTorchAsFlash.kt\nandroidx/camera/camera2/compat/workaround/UseTorchAsFlashImpl\n*L\n91#1:172,2\n106#1:174,2\n116#1:176,2\n131#1:178,2\n141#1:180,2\n148#1:182,2\n152#1:184,2\n*E\n"})
/* loaded from: classes3.dex */
public final class UseTorchAsFlashImpl implements UseTorchAsFlash {
    private final CameraDevices cameraDevices;
    private final CameraQuirks cameraQuirks;
    private final Lazy hasUwCameraUnderexposedFlashCaptureQuirk$delegate;
    private final IntrinsicZoomCalculator intrinsicZoomCalculator;

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.compat.workaround.UseTorchAsFlashImpl", f = "UseTorchAsFlash.kt", i = {}, l = {113}, m = "shouldUseTorchAsFlash", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.compat.workaround.UseTorchAsFlashImpl$shouldUseTorchAsFlash$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ UseTorchAsFlashImpl this$0;

        public AnonymousClass1(UseTorchAsFlashImpl r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.shouldUseTorchAsFlash(null, this);
        }
    }

    /* renamed from: $r8$lambda$l-JwfugR0JrcGF_aZNdHv-ivxig, reason: not valid java name */
    public static boolean m36$r8$lambda$lJwfugR0JrcGF_aZNdHvivxig(UseTorchAsFlashImpl r1) {
        return r1.cameraQuirks.getQuirks().contains(UltraWideFlashCaptureUnderexposureQuirk.class);
    }

    public UseTorchAsFlashImpl(CameraQuirks r1, CameraDevices r2, IntrinsicZoomCalculator r3) {
        this.cameraQuirks = r1;
        this.cameraDevices = r2;
        this.intrinsicZoomCalculator = r3;
        this.hasUwCameraUnderexposedFlashCaptureQuirk$delegate = LazyKt.lazy(new UseTorchAsFlashImpl$$ExternalSyntheticLambda1(this));
    }

    public static final /* synthetic */ boolean access$getHasUwCameraUnderexposedFlashCaptureQuirk(UseTorchAsFlashImpl r0) {
        return r0.getHasUwCameraUnderexposedFlashCaptureQuirk();
    }

    private final boolean getHasUwCameraUnderexposedFlashCaptureQuirk() {
        return ((Boolean) this.hasUwCameraUnderexposedFlashCaptureQuirk$delegate.getValue()).booleanValue();
    }

    private final Boolean isUltraWideCamera(FrameMetadata r6) {
        String r62 = (String) r6.get(UseTorchAsFlashImpl$$ExternalSyntheticApiModelOutline0.m());
        if (r62 == null) goto L5;
        CameraMetadata r2 = CameraDevices.m168awaitCameraMetadataFpsL5FU$default(this.cameraDevices, CameraId.m228constructorimpl(r62), null, 2, null);
        if (r2 == null) goto L11;
        Float r5 = this.intrinsicZoomCalculator.calculateIntrinsicZoomRatio(r2);
        if (r5 == null) goto L26;
        float r52 = r5.floatValue();
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L20;
        android.util.Log.d("CXCP", "isUltraWideCamera: cameraId = " + r62 + ", intrinsicZoomRatio = " + r52);
    L20:
        if (r52 >= 1.0f) goto L22;
        boolean r53 = true;
    L24:
        return Boolean.valueOf(r53);
    L22:
        r53 = false;
        goto L24
    L26:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L28;
        android.util.Log.w("CXCP", "isUltraWideCamera: could not calculate intrinsic zoom ratio.");
    L28:
        return null;
    L11:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L13;
        android.util.Log.w("CXCP", "isUltraWideCamera: failed to get CameraMetadata for ".concat(r62));
    L13:
        return null;
    L5:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L7;
        android.util.Log.w("CXCP", "isUltraWideCamera: could not get active physical camera ID to identify if it's ultra wide camera.");
    L7:
        return null;
    }

    @Override // androidx.camera.camera2.compat.workaround.UseTorchAsFlash
    public boolean shouldDisableAePrecapture() {
        return !getHasUwCameraUnderexposedFlashCaptureQuirk();
    }

    @Override // androidx.camera.camera2.compat.workaround.UseTorchAsFlash
    public Object shouldUseTorchAsFlash(Function1<? super Continuation<? super FrameMetadata>, ? extends Object> r7, Continuation<? super Boolean> r8) {
        if ((r8 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r8;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r82 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        boolean r4 = true;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        ResultKt.throwOnFailure(r82);
    L31:
        FrameMetadata r83 = (FrameMetadata) r82;
        if (r83 == null) goto L34;
        Boolean r6 = isUltraWideCamera(r83);
        if (r6 == null) goto L42;
        r4 = r6.booleanValue();
    L42:
        return Boxing.boxBoolean(r4);
    L34:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L37;
        android.util.Log.w("CXCP", "shouldUseTorchAsFlash: frameMetadata is null, defaulting to workaround for safety.");
    L37:
        return Boxing.boxBoolean(true);
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r82);
        Log r84 = Log.INSTANCE;
        if (r84.getDEBUG_LOGGABLE() == false) goto L18;
        android.util.Log.d("CXCP", "shouldUseTorchAsFlash: hasUwCameraUnderexposedFlashCaptureQuirk = " + access$getHasUwCameraUnderexposedFlashCaptureQuirk(this));
    L18:
        if (getHasUwCameraUnderexposedFlashCaptureQuirk() == true) goto L22;
        return Boxing.boxBoolean(true);
    L22:
        if (Build.VERSION.SDK_INT < 29) goto L24;
        r0.label = 1;
        r82 = r7.invoke(r0);
        if (r82 != r12) goto L31;
        return r12;
    L24:
        if (r84.getWARN_LOGGABLE() == false) goto L27;
        android.util.Log.w("CXCP", "shouldUseTorchAsFlash: API level is too low to know if it's ultra wide camera, defaulting to workaround for safety.");
    L27:
        return Boxing.boxBoolean(true);
    L7:
        r0 = new AnonymousClass1(this, r8);
        goto L8
    }
}
