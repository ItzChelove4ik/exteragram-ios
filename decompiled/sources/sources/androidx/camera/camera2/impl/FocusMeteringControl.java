package androidx.camera.camera2.impl;

import android.graphics.PointF;
import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.util.Log;
import android.util.Rational;
import android.util.Size;
import androidx.camera.camera2.adapter.CoroutineAdaptersKt;
import androidx.camera.camera2.compat.ZoomCompat;
import androidx.camera.camera2.compat.workaround.MeteringRegionCorrection;
import androidx.camera.camera2.impl.UseCaseManager;
import androidx.camera.camera2.pipe.AeMode;
import androidx.camera.camera2.pipe.AfMode;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.FrameMetadata;
import androidx.camera.camera2.pipe.Lock3ABehavior;
import androidx.camera.camera2.pipe.Result3A;
import androidx.camera.core.CameraControl;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.Logger;
import androidx.camera.core.MeteringPoint;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.mediarouter.media.GlobalMediaRouter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u0000 o2\u00020\u00012\u00020\u0002:\u0001oB1\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ-\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u000f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0015\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J%\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u000f2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ/\u0010!\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020\u001d0\u001c2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010 \u001a\u00020\u001fH\u0002¢\u0006\u0004\b!\u0010\"J\u0017\u0010'\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\u0002¢\u0006\u0004\b%\u0010&J-\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u0015\u001a\u00020\u00142\u000e\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011H\u0002¢\u0006\u0004\b)\u0010*J'\u0010.\u001a\u00020\u0016\"\u0004\b\u0000\u0010+*\b\u0012\u0004\u0012\u00028\u00000\u00112\u0006\u0010-\u001a\u00020,H\u0002¢\u0006\u0004\b.\u0010/J\u001b\u00100\u001a\u00020\u0012*\u00020\u001d2\u0006\u0010 \u001a\u00020\u001fH\u0002¢\u0006\u0004\b0\u00101J\u0017\u00106\u001a\u00020\u001f2\u0006\u00103\u001a\u000202H\u0002¢\u0006\u0004\b4\u00105J\u001d\u0010:\u001a\u00020\u00162\f\u00109\u001a\b\u0012\u0004\u0012\u00020807H\u0016¢\u0006\u0004\b:\u0010;J\u000f\u0010<\u001a\u00020\u0016H\u0016¢\u0006\u0004\b<\u0010=J%\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00120A2\u0006\u0010?\u001a\u00020>2\b\b\u0002\u0010@\u001a\u00020\u000f¢\u0006\u0004\bB\u0010CJ\u0015\u0010D\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u001c¢\u0006\u0004\bD\u0010ER\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010FR\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010GR\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010HR\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010IR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010JR\u0018\u0010K\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bK\u0010LR\u0018\u0010N\u001a\u0004\u0018\u00010M8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bN\u0010OR\u001c\u0010R\u001a\n Q*\u0004\u0018\u00010P0P8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010SR\u001c\u0010T\u001a\n Q*\u0004\u0018\u00010P0P8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bT\u0010SR\u001c\u0010U\u001a\n Q*\u0004\u0018\u00010P0P8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bU\u0010SR\u0014\u0010V\u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bV\u0010WR\u001e\u0010Y\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010#\u0018\u00010X8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bY\u0010ZR\u001e\u0010[\u001a\f\u0012\u0006\u0012\u0004\u0018\u000102\u0018\u00010X8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b[\u0010ZR\u001e\u0010\\\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\\\u0010]R \u0010^\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001d\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b^\u0010]R\u0018\u0010`\u001a\u0004\u0018\u00010_8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b`\u0010aR\u0018\u0010b\u001a\u0004\u0018\u00010_8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bb\u0010aR\u0014\u0010f\u001a\u00020c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bd\u0010eR\u0014\u0010i\u001a\u00020M8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bg\u0010hR(\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010j\u001a\u0004\u0018\u00010\u00148V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bk\u0010l\"\u0004\bm\u0010n¨\u0006p"}, d2 = {"Landroidx/camera/camera2/impl/FocusMeteringControl;", "Landroidx/camera/camera2/impl/UseCaseCameraControl;", "Landroidx/camera/camera2/impl/UseCaseManager$RunningUseCasesChangeListener;", "Landroidx/camera/camera2/impl/CameraProperties;", "cameraProperties", "Landroidx/camera/camera2/compat/workaround/MeteringRegionCorrection;", "meteringRegionCorrection", "Landroidx/camera/camera2/impl/State3AControl;", "state3AControl", "Landroidx/camera/camera2/impl/UseCaseThreads;", "threads", "Landroidx/camera/camera2/compat/ZoomCompat;", "zoomCompat", "<init>", "(Landroidx/camera/camera2/impl/CameraProperties;Landroidx/camera/camera2/compat/workaround/MeteringRegionCorrection;Landroidx/camera/camera2/impl/State3AControl;Landroidx/camera/camera2/impl/UseCaseThreads;Landroidx/camera/camera2/compat/ZoomCompat;)V", _UrlKt.FRAGMENT_ENCODE_SET, "delayMillis", "Lkotlinx/coroutines/CompletableDeferred;", "Landroidx/camera/core/FocusMeteringResult;", "resultToCancel", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "requestControl", _UrlKt.FRAGMENT_ENCODE_SET, "triggerAutoCancel", "(JLkotlinx/coroutines/CompletableDeferred;Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;)V", "resultToComplete", "triggerFocusTimeout", "(JLkotlinx/coroutines/CompletableDeferred;)V", "Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/pipe/Result3A;", "resultDeferred", _UrlKt.FRAGMENT_ENCODE_SET, "shouldTriggerAf", "propagateToFocusMeteringResultDeferred", "(Lkotlinx/coroutines/Deferred;Lkotlinx/coroutines/CompletableDeferred;Z)V", "Landroidx/camera/camera2/pipe/AeMode;", "preferredMode", "getSupportedAeMode-rTgdhRc", "(I)I", "getSupportedAeMode", "signalToCancel", "cancelFocusAndMeteringNowAsync", "(Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;Lkotlinx/coroutines/CompletableDeferred;)Lkotlinx/coroutines/Deferred;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "message", "setCancelException", "(Lkotlinx/coroutines/CompletableDeferred;Ljava/lang/String;)V", "toFocusMeteringResult", "(Landroidx/camera/camera2/pipe/Result3A;Z)Landroidx/camera/core/FocusMeteringResult;", "Landroidx/camera/camera2/pipe/AfMode;", "afMode", "isAfModeSupported-wvCmZyc", "(I)Z", "isAfModeSupported", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "runningUseCases", "onRunningUseCasesChanged", "(Ljava/util/Set;)V", "reset", "()V", "Landroidx/camera/core/FocusMeteringAction;", "action", "autoFocusTimeoutMs", "Lcom/google/common/util/concurrent/ListenableFuture;", "startFocusAndMetering", "(Landroidx/camera/core/FocusMeteringAction;J)Lcom/google/common/util/concurrent/ListenableFuture;", "cancelFocusAndMeteringAsync", "()Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/impl/CameraProperties;", "Landroidx/camera/camera2/compat/workaround/MeteringRegionCorrection;", "Landroidx/camera/camera2/impl/State3AControl;", "Landroidx/camera/camera2/impl/UseCaseThreads;", "Landroidx/camera/camera2/compat/ZoomCompat;", "_requestControl", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "Landroid/util/Rational;", "previewAspectRatio", "Landroid/util/Rational;", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin.jvm.PlatformType", "maxAfRegionCount", "Ljava/lang/Integer;", "maxAeRegionCount", "maxAwbRegionCount", "supportsAutoFocusTrigger", "Z", _UrlKt.FRAGMENT_ENCODE_SET, "availableAeModes", "Ljava/util/List;", "availableAfModes", "updateSignal", "Lkotlinx/coroutines/CompletableDeferred;", "cancelSignal", "Lkotlinx/coroutines/Job;", "focusTimeoutJob", "Lkotlinx/coroutines/Job;", "autoCancelJob", "Landroid/graphics/Rect;", "getCropSensorRegion", "()Landroid/graphics/Rect;", "cropSensorRegion", "getDefaultAspectRatio", "()Landroid/util/Rational;", "defaultAspectRatio", "value", "getRequestControl", "()Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "setRequestControl", "(Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;)V", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFocusMeteringControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FocusMeteringControl.kt\nandroidx/camera/camera2/impl/FocusMeteringControl\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,556:1\n11258#2:557\n11593#2,3:558\n11258#2:561\n11593#2,3:562\n1#3:565\n85#4,4:566\n85#4,4:570\n129#4,4:574\n85#4,4:578\n*S KotlinDebug\n*F\n+ 1 FocusMeteringControl.kt\nandroidx/camera/camera2/impl/FocusMeteringControl\n*L\n110#1:557\n110#1:558,3\n114#1:561\n114#1:562,3\n198#1:566,4\n216#1:570,4\n295#1:574,4\n301#1:578,4\n*E\n"})
/* loaded from: classes3.dex */
public final class FocusMeteringControl implements UseCaseCameraControl, UseCaseManager.RunningUseCasesChangeListener {
    public static final Companion Companion = null;
    private UseCaseCameraRequestControl _requestControl;
    private Job autoCancelJob;
    private final List<AeMode> availableAeModes;
    private final List<AfMode> availableAfModes;
    private final CameraProperties cameraProperties;
    private CompletableDeferred<Result3A> cancelSignal;
    private Job focusTimeoutJob;
    private final Integer maxAeRegionCount;
    private final Integer maxAfRegionCount;
    private final Integer maxAwbRegionCount;
    private final MeteringRegionCorrection meteringRegionCorrection;
    private Rational previewAspectRatio;
    private final State3AControl state3AControl;
    private final boolean supportsAutoFocusTrigger;
    private final UseCaseThreads threads;
    private CompletableDeferred<FocusMeteringResult> updateSignal;
    private final ZoomCompat zoomCompat;

    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JB\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014J0\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J \u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000fH\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Landroidx/camera/camera2/impl/FocusMeteringControl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "METERING_WEIGHT_DEFAULT", _UrlKt.FRAGMENT_ENCODE_SET, "AUTO_FOCUS_TIMEOUT_DURATION", _UrlKt.FRAGMENT_ENCODE_SET, "meteringRegionsFromMeteringPoints", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/params/MeteringRectangle;", "meteringPoints", "Landroidx/camera/core/MeteringPoint;", "maxRegionCount", "cropSensorRegion", "Landroid/graphics/Rect;", "defaultAspectRatio", "Landroid/util/Rational;", "meteringMode", "meteringRegionCorrection", "Landroidx/camera/camera2/compat/workaround/MeteringRegionCorrection;", "getFovAdjustedPoint", "Landroid/graphics/PointF;", "meteringPoint", "cropRegionAspectRatio", "getMeteringRect", "normalizedPointF", "normalizedSize", _UrlKt.FRAGMENT_ENCODE_SET, "cropRegion", "isValid", _UrlKt.FRAGMENT_ENCODE_SET, "pt", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final PointF getFovAdjustedPoint(MeteringPoint r7, Rational r8, Rational r9, int r10, MeteringRegionCorrection r11) {
            Rational r6 = r7.getSurfaceAspectRatio();
            if (r6 == null) goto L6;
            r9 = r6;
        L6:
            PointF r62 = r11.getCorrectedPoint(r7, r10);
            if (Intrinsics.areEqual(r9, r8) == true) goto L15;
            if (r9.compareTo(r8) <= 0) goto L12;
            PointF r72 = new PointF(r62.x, r62.y);
            float r63 = (float) (r9.doubleValue() / r8.doubleValue());
            r72.y = (((float) ((r63 - 1.0d) / 2.0d)) + r72.y) * (1.0f / r63);
            return r72;
        L12:
            PointF r73 = new PointF(r62.x, r62.y);
            float r64 = (float) (r8.doubleValue() / r9.doubleValue());
            r73.x = (((float) ((r64 - 1.0d) / 2.0d)) + r73.x) * (1.0f / r64);
            return r73;
        L15:
            return new PointF(r62.x, r62.y);
        }

        private final MeteringRectangle getMeteringRect(PointF r5, float r6, Rect r7) {
            int r4 = (int) (r7.left + (r5.x * r7.width()));
            int r52 = (int) (r7.top + (r5.y * r7.height()));
            int r0 = ((int) (r7.width() * r6)) / 2;
            int r62 = ((int) (r6 * r7.height())) / 2;
            Rect r1 = new Rect(r4 - r0, r52 - r62, r4 + r0, r52 + r62);
            r1.left = RangesKt.coerceIn(r1.left, r7.left, r7.right);
            r1.right = RangesKt.coerceIn(r1.right, r7.left, r7.right);
            r1.top = RangesKt.coerceIn(r1.top, r7.top, r7.bottom);
            r1.bottom = RangesKt.coerceIn(r1.bottom, r7.top, r7.bottom);
            return new MeteringRectangle(r1, 1000);
        }

        private final boolean isValid(MeteringPoint r3) {
            if (r3.getX() >= 0.0f) goto L5;
            return false;
        L5:
            if (r3.getX() <= 1.0f) goto L7;
            return false;
        L7:
            if (r3.getY() >= 0.0f) goto L9;
            return false;
        L9:
            if (r3.getY() > 1.0f) goto L16;
            return true;
        L16:
            return false;
        }

        public final List<MeteringRectangle> meteringRegionsFromMeteringPoints(List<? extends MeteringPoint> r8, int r9, Rect r10, Rational r11, int r12, MeteringRegionCorrection r13) {
            if (r8.isEmpty() == true) goto L18;
            if (r9 == 0) goto L18;
            ArrayList r0 = new ArrayList();
            Rational r3 = new Rational(r10.width(), r10.height());
            Iterator<? extends MeteringPoint> r82 = r8.iterator();
        L8:
            if (r82.hasNext() == false) goto L16;
            MeteringPoint r2 = r82.next();
            if (r0.size() >= r9) goto L16;
            if (this.isValid(r2) == false) goto L8;
            Companion r1 = this;
            Rational r4 = r11;
            r0.add(r1.getMeteringRect(r1.getFovAdjustedPoint(r2, r3, r4, r12, r13), r2.getSize(), r10));
            Companion this = r1;
            r11 = r4;
        L16:
            return r0;
        L18:
            return CollectionsKt.emptyList();
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.FocusMeteringControl$triggerAutoCancel$1", f = "FocusMeteringControl.kt", i = {}, l = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_DISCONNECTED}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    @SourceDebugExtension({"SMAP\nFocusMeteringControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FocusMeteringControl.kt\nandroidx/camera/camera2/impl/FocusMeteringControl$triggerAutoCancel$1\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,556:1\n85#2,4:557\n*S KotlinDebug\n*F\n+ 1 FocusMeteringControl.kt\nandroidx/camera/camera2/impl/FocusMeteringControl$triggerAutoCancel$1\n*L\n267#1:557,4\n*E\n"})
    /* renamed from: androidx.camera.camera2.impl.FocusMeteringControl$triggerAutoCancel$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $delayMillis;
        final /* synthetic */ UseCaseCameraRequestControl $requestControl;
        final /* synthetic */ CompletableDeferred<FocusMeteringResult> $resultToCancel;
        int label;
        final /* synthetic */ FocusMeteringControl this$0;

        public AnonymousClass1(long r1, FocusMeteringControl r3, UseCaseCameraRequestControl r4, CompletableDeferred<FocusMeteringResult> r5, Continuation<? super AnonymousClass1> r6) {
            this.$delayMillis = r1;
            this.this$0 = r3;
            this.$requestControl = r4;
            this.$resultToCancel = r5;
            super(2, r6);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r8, Continuation<?> r9) {
            return new AnonymousClass1(this.$delayMillis, this.this$0, this.$requestControl, this.$resultToCancel, r9);
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
        public final Object invokeSuspend(Object r6) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r6);
        L11:
            Camera2Logger r62 = Camera2Logger.INSTANCE;
            long r02 = this.$delayMillis;
            if (Logger.isDebugEnabled("CXCP") == false) goto L14;
            Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "triggerAutoCancel: auto-canceling after " + r02 + " ms");
        L14:
            FocusMeteringControl.access$cancelFocusAndMeteringNowAsync(this.this$0, this.$requestControl, this.$resultToCancel);
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r6);
            long r3 = this.$delayMillis;
            this.label = 1;
            if (DelayKt.delay(r3, this) != r0) goto L11;
            return r0;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.FocusMeteringControl$triggerFocusTimeout$1", f = "FocusMeteringControl.kt", i = {}, l = {280}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    @SourceDebugExtension({"SMAP\nFocusMeteringControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FocusMeteringControl.kt\nandroidx/camera/camera2/impl/FocusMeteringControl$triggerFocusTimeout$1\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,556:1\n85#2,4:557\n*S KotlinDebug\n*F\n+ 1 FocusMeteringControl.kt\nandroidx/camera/camera2/impl/FocusMeteringControl$triggerFocusTimeout$1\n*L\n281#1:557,4\n*E\n"})
    /* renamed from: androidx.camera.camera2.impl.FocusMeteringControl$triggerFocusTimeout$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00881 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $delayMillis;
        final /* synthetic */ CompletableDeferred<FocusMeteringResult> $resultToComplete;
        int label;

        public C00881(long r1, CompletableDeferred<FocusMeteringResult> r3, Continuation<? super C00881> r4) {
            this.$delayMillis = r1;
            this.$resultToComplete = r3;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            return new C00881(this.$delayMillis, this.$resultToComplete, r4);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C00881) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r6) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r6);
        L11:
            Camera2Logger r62 = Camera2Logger.INSTANCE;
            long r02 = this.$delayMillis;
            if (Logger.isDebugEnabled("CXCP") == false) goto L14;
            Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "triggerFocusTimeout: completing with focus result unsuccessful after " + r02 + " ms");
        L14:
            this.$resultToComplete.complete(FocusMeteringResult.create(false));
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r6);
            long r3 = this.$delayMillis;
            this.label = 1;
            if (DelayKt.delay(r3, this) != r0) goto L11;
            return r0;
        }
    }

    public static Unit $r8$lambda$acTtX0ZmOPlAGU7Xv4zujOVEEf0(CompletableDeferred r2, Deferred r3, FocusMeteringControl r4, boolean r5, Throwable r6) {
        if (r6 == null) goto L8;
        Camera2Logger r32 = Camera2Logger.INSTANCE;
        if (Logger.isWarnEnabled("CXCP") == false) goto L7;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "propagateToFocusMeteringResultDeferred: completed exceptionally!", r6);
    L7:
        r2.completeExceptionally(r6);
    L19:
        return Unit.INSTANCE;
    L8:
        Result3A r33 = (Result3A) r3.getCompleted();
        Camera2Logger r62 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L11;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "propagateToFocusMeteringResultDeferred: result3A = " + r33);
    L11:
        int r63 = r33.m378getStatusJvTi9ms();
        Result3A.Status.Companion r0 = Result3A.Status.Companion;
        if (Result3A.Status.m380equalsimpl0(r63, r0.m386getSUBMIT_FAILEDJvTi9ms()) == false) goto L15;
        r2.completeExceptionally(new CameraControl.OperationCanceledException("Camera is not active."));
        goto L19
    L15:
        if (Result3A.Status.m380equalsimpl0(r33.m378getStatusJvTi9ms(), r0.m387getTIME_LIMIT_REACHEDJvTi9ms()) == false) goto L17;
        r2.complete(FocusMeteringResult.create(false));
        goto L19
    L17:
        r2.complete(r4.toFocusMeteringResult(r33, r5));
        goto L19
    }

    static {
        Companion = new Companion(null);
    }

    public FocusMeteringControl(CameraProperties r4, MeteringRegionCorrection r5, State3AControl r6, UseCaseThreads r7, ZoomCompat r8) {
        this.cameraProperties = r4;
        this.meteringRegionCorrection = r5;
        this.state3AControl = r6;
        this.threads = r7;
        this.zoomCompat = r8;
        int r72 = 0;
        this.maxAfRegionCount = (Integer) r4.getMetadata().getOrDefault(CameraCharacteristics.CONTROL_MAX_REGIONS_AF, 0);
        this.maxAeRegionCount = (Integer) r4.getMetadata().getOrDefault(CameraCharacteristics.CONTROL_MAX_REGIONS_AE, 0);
        this.maxAwbRegionCount = (Integer) r4.getMetadata().getOrDefault(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB, 0);
        this.supportsAutoFocusTrigger = CameraMetadata.Companion.getSupportsAutoFocusTrigger(r4.getMetadata());
        int[] r42 = (int[]) r4.getMetadata().get(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES);
        ArrayList r52 = null;
        if (r42 == null) goto L7;
        ArrayList r62 = new ArrayList(r42.length);
        int r82 = r42.length;
        int r0 = 0;
    L5:
        if (r0 >= r82) goto L8;
        r62.add(AeMode.Companion.m117fromIntOrNullkQd0u18(r42[r0]));
        r0 = r0 + 1;
    L8:
        this.availableAeModes = r62;
        int[] r43 = (int[]) this.cameraProperties.getMetadata().get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
        if (r43 == null) goto L13;
        r52 = new ArrayList(r43.length);
        int r63 = r43.length;
    L11:
        if (r72 >= r63) goto L13;
        r52.add(AfMode.Companion.m128fromIntOrNullMKXwA8g(r43[r72]));
        r72 = r72 + 1;
    L13:
        this.availableAfModes = r52;
        return;
    L7:
        r62 = null;
        goto L8
    }

    public static final /* synthetic */ Deferred access$cancelFocusAndMeteringNowAsync(FocusMeteringControl r0, UseCaseCameraRequestControl r1, CompletableDeferred r2) {
        return r0.cancelFocusAndMeteringNowAsync(r1, r2);
    }

    private final Deferred<Result3A> cancelFocusAndMeteringNowAsync(UseCaseCameraRequestControl r2, CompletableDeferred<FocusMeteringResult> r3) {
        if (r3 == null) goto L4;
        setCancelException(r3, "Cancelled by cancelFocusAndMetering()");
    L4:
        this.state3AControl.setPreferredFocusModeAsync(null);
        return r2.cancelFocusAndMeteringAsync();
    }

    private final Rect getCropSensorRegion() {
        return this.zoomCompat.getCropSensorRegion();
    }

    private final Rational getDefaultAspectRatio() {
        Rational r0 = this.previewAspectRatio;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return new Rational(getCropSensorRegion().width(), getCropSensorRegion().height());
    }

    /* renamed from: getSupportedAeMode-rTgdhRc, reason: not valid java name */
    private final int m73getSupportedAeModerTgdhRc(int r3) {
        List<AeMode> r0 = this.availableAeModes;
        if (r0 != null) goto L7;
        return AeMode.Companion.m118getOFFbOjpiJc();
    L7:
        if (r0.contains(AeMode.m109boximpl(r3)) == false) goto L9;
        return r3;
    L9:
        List<AeMode> r2 = this.availableAeModes;
        AeMode.Companion r32 = AeMode.Companion;
        if (r2.contains(AeMode.m109boximpl(r32.m119getONbOjpiJc())) == false) goto L14;
        return r32.m119getONbOjpiJc();
    L14:
        return r32.m118getOFFbOjpiJc();
    }

    /* renamed from: isAfModeSupported-wvCmZyc, reason: not valid java name */
    private final boolean m74isAfModeSupportedwvCmZyc(int r1) {
        List<AfMode> r0 = this.availableAfModes;
        if (r0 != null) goto L7;
        return false;
    L7:
        return r0.contains(AfMode.m120boximpl(r1));
    }

    private final void propagateToFocusMeteringResultDeferred(Deferred<Result3A> r2, CompletableDeferred<FocusMeteringResult> r3, boolean r4) {
        r2.invokeOnCompletion(new FocusMeteringControl$$ExternalSyntheticLambda0(r3, r2, this, r4));
    }

    private final <T> void setCancelException(CompletableDeferred<T> r1, String r2) {
        r1.completeExceptionally(new CameraControl.OperationCanceledException(r2));
    }

    public static /* synthetic */ ListenableFuture startFocusAndMetering$default(FocusMeteringControl r0, FocusMeteringAction r1, long r2, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = 5000;
    L6:
        return r0.startFocusAndMetering(r1, r2);
    }

    private final FocusMeteringResult toFocusMeteringResult(Result3A r4, boolean r5) {
        boolean r1 = false;
        if (Result3A.Status.m380equalsimpl0(r4.m378getStatusJvTi9ms(), Result3A.Status.Companion.m384getOKJvTi9ms()) == false) goto L5;
        FrameMetadata r0 = r4.getFrameMetadata();
        if (r0 == null) goto L9;
        Integer r02 = (Integer) r0.get(CaptureResult.CONTROL_AF_STATE);
    L10:
        if (r5 == false) goto L24;
        if (m74isAfModeSupportedwvCmZyc(AfMode.Companion.m129getAUTOvHZNRtE()) == true) goto L16;
    L14:
        r1 = true;
        goto L24
    L16:
        if (r4.getFrameMetadata() == null) goto L24;
        if (r02 == null) goto L14;
        if (r02.intValue() == 4) goto L14;
    L24:
        return FocusMeteringResult.create(r1);
    L9:
        r02 = null;
        goto L10
    L5:
        return FocusMeteringResult.create(false);
    }

    private final void triggerAutoCancel(long r10, CompletableDeferred<FocusMeteringResult> r12, UseCaseCameraRequestControl r13) {
        Job r0 = this.autoCancelJob;
        if (r0 == null) goto L5;
        Job.DefaultImpls.cancel$default(r0, null, 1, null);
    L5:
        this.autoCancelJob = BuildersKt.launch$default(this.threads.getSequentialScope(), null, null, new AnonymousClass1(r10, this, r13, r12, null), 3, null);
    }

    private final void triggerFocusTimeout(long r9, CompletableDeferred<FocusMeteringResult> r11) {
        Job r0 = this.focusTimeoutJob;
        if (r0 == null) goto L5;
        Job.DefaultImpls.cancel$default(r0, null, 1, null);
    L5:
        this.focusTimeoutJob = BuildersKt.launch$default(this.threads.getSequentialScope(), null, null, new C00881(r9, r11, null), 3, null);
    }

    public final Deferred<Result3A> cancelFocusAndMeteringAsync() {
        CompletableDeferred<Result3A> r2 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        UseCaseCameraRequestControl r3 = getRequestControl();
        if (r3 == null) goto L15;
        Job r4 = this.focusTimeoutJob;
        if (r4 == null) goto L7;
        Job.DefaultImpls.cancel$default(r4, null, 1, null);
    L7:
        Job r42 = this.autoCancelJob;
        if (r42 == null) goto L10;
        Job.DefaultImpls.cancel$default(r42, null, 1, null);
    L10:
        CompletableDeferred<Result3A> r0 = this.cancelSignal;
        if (r0 == null) goto L13;
        setCancelException(r0, "Cancelled by another cancelFocusAndMetering()");
    L13:
        this.cancelSignal = r2;
        CoroutineAdaptersKt.propagateTo(cancelFocusAndMeteringNowAsync(r3, this.updateSignal), r2);
        return r2;
    L15:
        r2.completeExceptionally(new CameraControl.OperationCanceledException("Camera is not active."));
        return r2;
    }

    public UseCaseCameraRequestControl getRequestControl() {
        return this._requestControl;
    }

    @Override // androidx.camera.camera2.impl.UseCaseManager.RunningUseCasesChangeListener
    public void onRunningUseCasesChanged(Set<? extends UseCase> r4) {
        this.previewAspectRatio = null;
        Iterator<? extends UseCase> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L10;
        UseCase r0 = r42.next();
        if ((r0 instanceof Preview) == false) goto L4;
        Size r02 = ((Preview) r0).getAttachedSurfaceResolution();
        if (r02 == null) goto L4;
        this.previewAspectRatio = new Rational(r02.getWidth(), r02.getHeight());
        goto L4
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraControl
    public void reset() {
        this.previewAspectRatio = null;
        cancelFocusAndMeteringAsync();
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraControl
    public void setRequestControl(UseCaseCameraRequestControl r1) {
        this._requestControl = r1;
    }

    public final ListenableFuture<FocusMeteringResult> startFocusAndMetering(FocusMeteringAction r21, long r22) {
        CompletableDeferred<FocusMeteringResult> r5 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        UseCaseCameraRequestControl r6 = getRequestControl();
        if (r6 == null) goto L74;
        Job r7 = this.focusTimeoutJob;
        if (r7 == null) goto L7;
        Job.DefaultImpls.cancel$default(r7, null, 1, null);
    L7:
        Job r72 = this.autoCancelJob;
        if (r72 == null) goto L10;
        Job.DefaultImpls.cancel$default(r72, null, 1, null);
    L10:
        CompletableDeferred<Result3A> r73 = this.cancelSignal;
        if (r73 == null) goto L13;
        setCancelException(r73, "Cancelled by another startFocusAndMetering()");
    L13:
        CompletableDeferred<FocusMeteringResult> r74 = this.updateSignal;
        if (r74 == null) goto L16;
        setCancelException(r74, "Cancelled by another startFocusAndMetering()");
    L16:
        this.updateSignal = r5;
        Companion r9 = Companion;
        List<MeteringRectangle> r75 = r9.meteringRegionsFromMeteringPoints(r21.getMeteringPointsAe(), this.maxAeRegionCount.intValue(), getCropSensorRegion(), getDefaultAspectRatio(), 2, this.meteringRegionCorrection);
        List<MeteringRectangle> r18 = r9.meteringRegionsFromMeteringPoints(r21.getMeteringPointsAf(), this.maxAfRegionCount.intValue(), getCropSensorRegion(), getDefaultAspectRatio(), 1, this.meteringRegionCorrection);
        List<MeteringRectangle> r8 = r9.meteringRegionsFromMeteringPoints(r21.getMeteringPointsAwb(), this.maxAwbRegionCount.intValue(), getCropSensorRegion(), getDefaultAspectRatio(), 4, this.meteringRegionCorrection);
        if (r75.isEmpty() == true) goto L19;
    L24:
        List<MeteringRectangle> r92 = r18;
        if (r92.isEmpty() == true) goto L28;
        this.state3AControl.setPreferredFocusModeAsync(1);
    L28:
        if (this.maxAeRegionCount.intValue() <= 0) goto L33;
        List<MeteringRectangle> r76 = r75;
        if (r76.isEmpty() == false) goto L32;
        r76 = ArraysKt.toList(CameraGraph.Constants3A.INSTANCE.getMETERING_REGIONS_DEFAULT());
    L32:
        List<MeteringRectangle> r77 = r76;
    L35:
        if (this.maxAfRegionCount.intValue() > 0) goto L37;
        List<MeteringRectangle> r93 = null;
    L42:
        if (this.maxAwbRegionCount.intValue() <= 0) goto L47;
        List<MeteringRectangle> r82 = r8;
        if (r82.isEmpty() == false) goto L46;
        r82 = ArraysKt.toList(CameraGraph.Constants3A.INSTANCE.getMETERING_REGIONS_DEFAULT());
    L46:
        List<MeteringRectangle> r83 = r82;
    L49:
        if (r18.isEmpty() == false) goto L51;
    L52:
        List<MeteringRectangle> r19 = r93;
        List<MeteringRectangle> r94 = r83;
        Camera2Logger r10 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L70;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "startFocusAndMetering: updating 3A regions only");
    L70:
        Deferred<Result3A> r78 = r6.update3aRegions(r77, r19, r94);
    L71:
        propagateToFocusMeteringResultDeferred(r78, r5, !r18.isEmpty());
        triggerFocusTimeout(r22, r5);
        if (r21.isAutoCancelEnabled() == false) goto L76;
        triggerAutoCancel(r21.getAutoCancelDurationInMillis(), r5, r6);
    L76:
        return CoroutineAdaptersKt.asListenableFuture$default(r5, null, 1, null);
    L51:
        if (this.supportsAutoFocusTrigger == false) goto L52;
        if (r21.isAutoCancelEnabled() == true) goto L56;
    L58:
        long r12 = r22;
    L59:
        Camera2Logger r102 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L63;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "startFocusAndMetering: updating 3A regions & triggering AF");
    L63:
        if (this.maxAfRegionCount.intValue() <= 0) goto L65;
        Lock3ABehavior r11 = Lock3ABehavior.m280boximpl(Lock3ABehavior.Companion.m289getIMMEDIATEhRqSH3k());
    L66:
        r78 = UseCaseCameraRequestControl.m96startFocusAndMeteringAsyncNxRnBj4$default(r6, r77, r93, r83, null, r11, null, AeMode.m109boximpl(m73getSupportedAeModerTgdhRc(AeMode.Companion.m119getONbOjpiJc())), TimeUnit.NANOSECONDS.convert(r12, TimeUnit.MILLISECONDS), 40, null);
        goto L71
    L65:
        r11 = null;
        goto L66
    L56:
        if (r21.getAutoCancelDurationInMillis() >= r22) goto L58;
        r12 = r21.getAutoCancelDurationInMillis();
        goto L59
    L47:
        r83 = null;
        goto L49
    L37:
        if (r92.isEmpty() == false) goto L39;
        r92 = ArraysKt.toList(CameraGraph.Constants3A.INSTANCE.getMETERING_REGIONS_DEFAULT());
    L39:
        r93 = r92;
        goto L42
    L33:
        r77 = null;
        goto L35
    L19:
        if (r18.isEmpty() == false) goto L24;
        if (r8.isEmpty() == false) goto L24;
        r5.completeExceptionally(new IllegalArgumentException("None of the specified AF/AE/AWB MeteringPoints is supported on this camera."));
        return CoroutineAdaptersKt.asListenableFuture$default(r5, null, 1, null);
    L74:
        r5.completeExceptionally(new CameraControl.OperationCanceledException("Camera is not active."));
        goto L76
    }
}
