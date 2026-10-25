package androidx.camera.camera2.pipe.graph;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import androidx.camera.camera2.pipe.AeMode;
import androidx.camera.camera2.pipe.AfMode;
import androidx.camera.camera2.pipe.AwbMode;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.FlashMode;
import androidx.camera.camera2.pipe.FrameMetadata;
import androidx.camera.camera2.pipe.FrameNumber;
import androidx.camera.camera2.pipe.Lock3ABehavior;
import androidx.camera.camera2.pipe.Result3A;
import androidx.camera.camera2.pipe.core.Log;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.JobKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001f\b\u0001\u0018\u0000 c2\u00020\u0001:\u0001cB)\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ]\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u001a\b\u0002\u0010\u000e\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f2\u0016\b\u0002\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\b\b\u0002\u0010\u001b\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u001c\u0010\u001dJi\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\u0010 \u001a\u0004\u0018\u00010\u001e2\b\u0010!\u001a\u0004\u0018\u00010\u001e2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\"2\u0014\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0002¢\u0006\u0004\b$\u0010%J=\u0010,\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030*\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010+0\f2\u0006\u0010'\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\u0011H\u0002¢\u0006\u0004\b,\u0010-J=\u00101\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030*\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010+0\f2\u0006\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u0011H\u0002¢\u0006\u0004\b1\u0010-J+\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f2\u0006\u00102\u001a\u00020\u00112\u0006\u00103\u001a\u00020\u0011H\u0002¢\u0006\u0004\b4\u00105J=\u00109\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030*\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010+0\f2\u0006\u00106\u001a\u00020\u00112\u0006\u00107\u001a\u00020\u00112\u0006\u00108\u001a\u00020\u0011H\u0002¢\u0006\u0004\b9\u0010-J?\u0010D\u001a\u00020A2\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010;2\n\b\u0002\u0010>\u001a\u0004\u0018\u00010=2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010?H\u0002¢\u0006\u0004\bB\u0010CJy\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010;2\n\b\u0002\u0010>\u001a\u0004\u0018\u00010=2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010?2\u0010\b\u0002\u0010F\u001a\n\u0012\u0004\u0012\u00020E\u0018\u00010+2\u0010\b\u0002\u0010G\u001a\n\u0012\u0004\u0012\u00020E\u0018\u00010+2\u0010\b\u0002\u0010H\u001a\n\u0012\u0004\u0012\u00020E\u0018\u00010+¢\u0006\u0004\bI\u0010JJÎ\u0001\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0010\b\u0002\u0010F\u001a\n\u0012\u0004\u0012\u00020E\u0018\u00010+2\u0010\b\u0002\u0010G\u001a\n\u0012\u0004\u0012\u00020E\u0018\u00010+2\u0010\b\u0002\u0010H\u001a\n\u0012\u0004\u0012\u00020E\u0018\u00010+2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001e2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001e2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\"2\u0016\b\u0002\u0010L\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000f2\u0016\b\u0002\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\u0015H\u0086@¢\u0006\u0004\bO\u0010PJe\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u00112\u0016\b\u0002\u0010R\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015¢\u0006\u0004\bS\u0010TJ;\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\b\b\u0002\u0010U\u001a\u00020\u00112\b\b\u0002\u00103\u001a\u00020\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0019\u0010VJ\u001d\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\b\b\u0002\u0010\u001b\u001a\u00020\u0011¢\u0006\u0004\bW\u0010\u001dJ\u0013\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017¢\u0006\u0004\bX\u0010YJ\u001f\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\"¢\u0006\u0004\bZ\u0010[R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010]R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010^R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010_R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010`R\u001e\u0010a\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00178\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\ba\u0010b¨\u0006d"}, d2 = {"Landroidx/camera/camera2/pipe/graph/Controller3A;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/graph/GraphProcessor;", "graphProcessor", "Landroidx/camera/camera2/pipe/CameraMetadata;", "metadata", "Landroidx/camera/camera2/pipe/graph/GraphState3A;", "graphState3A", "Landroidx/camera/camera2/pipe/graph/Listener3A;", "graphListener3A", "<init>", "(Landroidx/camera/camera2/pipe/graph/GraphProcessor;Landroidx/camera/camera2/pipe/CameraMetadata;Landroidx/camera/camera2/pipe/graph/GraphState3A;Landroidx/camera/camera2/pipe/graph/Listener3A;)V", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest$Key;", "triggerCondition", "Lkotlin/Function1;", "Landroidx/camera/camera2/pipe/FrameMetadata;", _UrlKt.FRAGMENT_ENCODE_SET, "lockedCondition", _UrlKt.FRAGMENT_ENCODE_SET, "frameLimit", _UrlKt.FRAGMENT_ENCODE_SET, "timeLimitNs", "Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/pipe/Result3A;", "lock3AForCapture", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;IJ)Lkotlinx/coroutines/Deferred;", "cancelAf", "unlock3APostCaptureAndroidMAndAbove", "(Z)Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/pipe/Lock3ABehavior;", "aeLockBehavior", "afLockBehavior", "awbLockBehavior", "Landroidx/camera/camera2/pipe/AeMode;", "afTriggerStartAeMode", "lock3ANow-R6AlCjU", "(Landroidx/camera/camera2/pipe/Lock3ABehavior;Landroidx/camera/camera2/pipe/Lock3ABehavior;Landroidx/camera/camera2/pipe/Lock3ABehavior;Landroidx/camera/camera2/pipe/AeMode;Lkotlin/jvm/functions/Function1;Ljava/lang/Integer;Ljava/lang/Long;)Lkotlinx/coroutines/Deferred;", "lock3ANow", "waitForAeToConverge", "waitForAfToConverge", "waitForAwbToConverge", "Landroid/hardware/camera2/CaptureResult$Key;", _UrlKt.FRAGMENT_ENCODE_SET, "createConverged3AExitConditions", "(ZZZ)Ljava/util/Map;", "waitForAeToLock", "waitForAfToLock", "waitForAwbToLock", "createLocked3AExitConditions", "isAfTriggered", "waitForAwb", "createLock3AForCaptureExitConditions", "(ZZ)Lkotlin/jvm/functions/Function1;", "ae", "af", "awb", "createUnLocked3AExitConditions", "aeMode", "Landroidx/camera/camera2/pipe/AfMode;", "afMode", "Landroidx/camera/camera2/pipe/AwbMode;", "awbMode", "Landroidx/camera/camera2/pipe/FlashMode;", "flashMode", "Landroidx/camera/camera2/pipe/graph/Result3AStateListenerImpl;", "createListenerFor3AParams-0dPwJB0", "(Landroidx/camera/camera2/pipe/AeMode;Landroidx/camera/camera2/pipe/AfMode;Landroidx/camera/camera2/pipe/AwbMode;Landroidx/camera/camera2/pipe/FlashMode;)Landroidx/camera/camera2/pipe/graph/Result3AStateListenerImpl;", "createListenerFor3AParams", "Landroid/hardware/camera2/params/MeteringRectangle;", "aeRegions", "afRegions", "awbRegions", "update3A-169HPGg", "(Landroidx/camera/camera2/pipe/AeMode;Landroidx/camera/camera2/pipe/AfMode;Landroidx/camera/camera2/pipe/AwbMode;Landroidx/camera/camera2/pipe/FlashMode;Ljava/util/List;Ljava/util/List;Ljava/util/List;)Lkotlinx/coroutines/Deferred;", "update3A", "convergedCondition", "convergedTimeLimitNs", "lockedTimeLimitNs", "lock3A-Qz1gx5w", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Landroidx/camera/camera2/pipe/Lock3ABehavior;Landroidx/camera/camera2/pipe/Lock3ABehavior;Landroidx/camera/camera2/pipe/Lock3ABehavior;Landroidx/camera/camera2/pipe/AeMode;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;ILjava/lang/Long;Ljava/lang/Long;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lock3A", "unlockedCondition", "unlock3A", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Lkotlin/jvm/functions/Function1;ILjava/lang/Long;)Lkotlinx/coroutines/Deferred;", "triggerAf", "(ZZIJ)Lkotlinx/coroutines/Deferred;", "unlock3APostCapture", "setTorchOn", "()Lkotlinx/coroutines/Deferred;", "setTorchOff-NqN7i0k", "(Landroidx/camera/camera2/pipe/AeMode;)Lkotlinx/coroutines/Deferred;", "setTorchOff", "Landroidx/camera/camera2/pipe/graph/GraphProcessor;", "Landroidx/camera/camera2/pipe/CameraMetadata;", "Landroidx/camera/camera2/pipe/graph/GraphState3A;", "Landroidx/camera/camera2/pipe/graph/Listener3A;", "lastUpdate3AResult", "Lkotlinx/coroutines/Deferred;", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nController3A.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Controller3A.kt\nandroidx/camera/camera2/pipe/graph/Controller3A\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 5 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,897:1\n50#2,2:898\n50#2,2:913\n50#2,2:915\n50#2,2:917\n50#2,2:919\n50#2,2:921\n50#2,2:923\n50#2,2:925\n50#2,2:929\n50#2,2:931\n50#2,2:933\n50#2,2:935\n50#2,2:937\n1#3:900\n37#4:901\n36#4,3:902\n37#4:905\n36#4,3:906\n37#4:909\n36#4,3:910\n216#5,2:927\n*S KotlinDebug\n*F\n+ 1 Controller3A.kt\nandroidx/camera/camera2/pipe/graph/Controller3A\n*L\n223#1:898,2\n325#1:913,2\n361#1:915,2\n366#1:917,2\n374#1:919,2\n426#1:921,2\n428#1:923,2\n448#1:925,2\n577#1:929,2\n601#1:931,2\n665#1:933,2\n684#1:935,2\n780#1:937,2\n252#1:901\n252#1:902,3\n253#1:905\n253#1:906,3\n254#1:909\n254#1:910,3\n559#1:927,2\n*E\n"})
/* loaded from: classes3.dex */
public final class Controller3A {
    public static final Companion Companion = null;
    private static final List<Integer> aeConvergedStateList = null;
    private static final List<Integer> aeLockedStateList = null;
    private static final List<Integer> aePostPrecaptureStateList = null;
    private static final Map<CaptureRequest.Key<?>, Object> aePrecaptureAndAfCancelParams = null;
    private static final Map<CaptureRequest.Key<?>, Object> aePrecaptureCancelParams = null;
    private static final List<Integer> aeUnlockedStateList = null;
    private static final List<Integer> afConvergedStateList = null;
    private static final List<Integer> afLockedStateList = null;
    private static final List<Integer> afUnlockedStateList = null;
    private static final List<Integer> awbConvergedStateList = null;
    private static final List<Integer> awbLockedStateList = null;
    private static final List<Integer> awbPostPrecaptureStateList = null;
    private static final List<Integer> awbUnlockedStateList = null;
    private static final CompletableDeferred<Result3A> deferredResult3ASubmitFailed = null;
    private static final Map<CaptureRequest.Key<?>, Object> parameterForAfTriggerCancel = null;
    private static final Map<CaptureRequest.Key<?>, Object> parameterForAfTriggerStart = null;
    private static final Map<CaptureRequest.Key<?>, Object> parametersForAePrecapture = null;
    private static final Map<CaptureRequest.Key<?>, Object> parametersForAePrecaptureAndAfTrigger = null;
    private static final Function1<FrameMetadata, Boolean> unlock3APostCaptureAfUnlockedCondition = null;
    private static final Map<? extends CaptureRequest.Key<? extends Object>, Object> unlock3APostCaptureLockAeAndCancelAfParams = null;
    private static final Map<CaptureRequest.Key<Boolean>, Boolean> unlock3APostCaptureLockAeParams = null;
    private static final Map<CaptureRequest.Key<?>, Object> unlock3APostCaptureUnlockAeParams = null;
    private final Listener3A graphListener3A;
    private final GraphProcessor graphProcessor;
    private final GraphState3A graphState3A;
    private Deferred<Result3A> lastUpdate3AResult;
    private final CameraMetadata metadata;

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u0007R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0007R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u0007R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\u0007R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\u0007R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u0007R\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u0007R$\u0010\u0011\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0010\u0012\u0004\u0012\u00020\u00010\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0010\u0012\u0004\u0012\u00020\u00010\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0012R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0007R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u0007R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u0007R3\u0010\u001e\u001a!\u0012\u0017\u0012\u0015\u0012\f\u0012\n \u001c*\u0004\u0018\u00010\u001b0\u001b0\u0010¢\u0006\u0002\b\u001d\u0012\u0004\u0012\u00020\u001b0\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u0012R2\u0010\u001f\u001a \u0012\u0016\b\u0001\u0012\u0012\u0012\u000e\b\u0001\u0012\n \u001c*\u0004\u0018\u00010\u00010\u00010\u0010\u0012\u0004\u0012\u00020\u00010\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010\u0012R$\u0010 \u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0010\u0012\u0004\u0012\u00020\u00010\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010\u0012R$\u0010!\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0010\u0012\u0004\u0012\u00020\u00010\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\u0012R$\u0010\"\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0010\u0012\u0004\u0012\u00020\u00010\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010\u0012R \u0010%\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u001b0#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&¨\u0006'"}, d2 = {"Landroidx/camera/camera2/pipe/graph/Controller3A$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "aeConvergedStateList", "Ljava/util/List;", "awbConvergedStateList", "afConvergedStateList", "aeLockedStateList", "awbLockedStateList", "afLockedStateList", "aePostPrecaptureStateList", "awbPostPrecaptureStateList", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest$Key;", "parametersForAePrecapture", "Ljava/util/Map;", "parametersForAePrecaptureAndAfTrigger", "Lkotlinx/coroutines/CompletableDeferred;", "Landroidx/camera/camera2/pipe/Result3A;", "deferredResult3ASubmitFailed", "Lkotlinx/coroutines/CompletableDeferred;", "aeUnlockedStateList", "afUnlockedStateList", "awbUnlockedStateList", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin.jvm.PlatformType", "Lkotlin/jvm/internal/EnhancedNullability;", "unlock3APostCaptureLockAeParams", "unlock3APostCaptureLockAeAndCancelAfParams", "unlock3APostCaptureUnlockAeParams", "aePrecaptureCancelParams", "aePrecaptureAndAfCancelParams", "Lkotlin/Function1;", "Landroidx/camera/camera2/pipe/FrameMetadata;", "unlock3APostCaptureAfUnlockedCondition", "Lkotlin/jvm/functions/Function1;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    public static boolean $r8$lambda$ic7xTz5jmtx4Wl9pgzoHiHyPyIQ(boolean r6, boolean r7, FrameMetadata r8) {
        Integer r0 = (Integer) r8.get(CaptureResult.CONTROL_AF_MODE);
        if (r0 == null) goto L12;
        int r02 = AfMode.m121constructorimpl(r0.intValue());
        if (AfMode.m125isOnimpl(r02) == true) goto L7;
    L6:
        boolean r72 = true;
    L13:
        Integer r03 = (Integer) r8.get(CaptureResult.CONTROL_AE_MODE);
        if (r03 != null) goto L16;
    L20:
        boolean r04 = false;
    L21:
        Integer r3 = (Integer) r8.get(CaptureResult.CONTROL_AWB_MODE);
        if (r3 == null) goto L24;
        int r4 = r3.intValue();
    L25:
        int r42 = AwbMode.m141constructorimpl(r4);
        if (r6 == false) goto L29;
        if (r3 != null) goto L29;
        boolean r62 = false;
    L35:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L37;
        android.util.Log.d("CXCP", "lock3AForCapture state " + FrameNumber.m272toStringimpl(r8.mo266getFrameNumberUgla2oM()) + ": meetsAeCondition = " + r04 + ", meetsAfCondition = " + r72 + ", meetsAwbCondition = " + r62);
    L37:
        if (r04 == false) goto L41;
        if (r72 == false) goto L41;
        if (r62 == false) goto L41;
        return true;
    L41:
        return false;
    L29:
        if (r6 == true) goto L31;
    L33:
        r62 = true;
        goto L35
    L31:
        if (AwbMode.m144isOnimpl(r42) == false) goto L33;
        r62 = Controller3AKt.access$isNullOrIn(r8.get(CaptureResult.CONTROL_AWB_STATE), awbPostPrecaptureStateList);
        goto L35
    L24:
        r4 = 0;
        goto L25
    L16:
        if (AeMode.m114isOnimpl(AeMode.m110constructorimpl(r03.intValue())) == true) goto L18;
    L19:
        r04 = true;
        goto L21
    L18:
        if (Controller3AKt.access$isNullOrIn(r8.get(CaptureResult.CONTROL_AE_STATE), aePostPrecaptureStateList) == false) goto L20;
    L7:
        if (r7 == false) goto L10;
        r72 = Controller3AKt.access$isNullOrIn(r8.get(CaptureResult.CONTROL_AF_STATE), afLockedStateList);
        goto L13
    L10:
        if (AfMode.m124isContinuousimpl(r02) == false) goto L6;
        r72 = CollectionsKt.contains(afConvergedStateList, r8.get(CaptureResult.CONTROL_AF_STATE));
        goto L13
    L12:
        r72 = false;
        goto L13
    }

    static {
        Companion = new Companion(null);
        aeConvergedStateList = CollectionsKt.listOf(new Integer[]{2, 4, 3});
        awbConvergedStateList = CollectionsKt.listOf(new Integer[]{2, 3});
        afConvergedStateList = CollectionsKt.listOf(new Integer[]{2, 6, 4, 5});
        aeLockedStateList = CollectionsKt.listOf(3);
        awbLockedStateList = CollectionsKt.listOf(3);
        afLockedStateList = CollectionsKt.listOf(new Integer[]{4, 5});
        aePostPrecaptureStateList = CollectionsKt.listOf(new Integer[]{2, 4, 3});
        awbPostPrecaptureStateList = CollectionsKt.listOf(new Integer[]{2, 3});
        CaptureRequest.Key r6 = CaptureRequest.CONTROL_AF_TRIGGER;
        parameterForAfTriggerStart = MapsKt.mapOf(TuplesKt.to(r6, 1));
        parameterForAfTriggerCancel = MapsKt.mapOf(TuplesKt.to(r6, 2));
        CaptureRequest.Key r8 = CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER;
        parametersForAePrecapture = MapsKt.mapOf(TuplesKt.to(r8, 1));
        parametersForAePrecaptureAndAfTrigger = MapsKt.mapOf(new Pair[]{TuplesKt.to(r6, 1), TuplesKt.to(r8, 1)});
        deferredResult3ASubmitFailed = CompletableDeferredKt.CompletableDeferred(new Result3A(Result3A.Status.Companion.m386getSUBMIT_FAILEDJvTi9ms(), null, 2, null));
        aeUnlockedStateList = CollectionsKt.listOf(new Integer[]{0, 1, 2, 4});
        List<Integer> r1 = CollectionsKt.listOf(new Integer[]{0, 3, 1, 2, 6});
        afUnlockedStateList = r1;
        awbUnlockedStateList = CollectionsKt.listOf(new Integer[]{0, 1, 2});
        CaptureRequest.Key r0 = CaptureRequest.CONTROL_AE_LOCK;
        Boolean r3 = Boolean.TRUE;
        unlock3APostCaptureLockAeParams = MapsKt.mapOf(TuplesKt.to(r0, r3));
        unlock3APostCaptureLockAeAndCancelAfParams = MapsKt.mapOf(new Pair[]{TuplesKt.to(r6, 2), TuplesKt.to(r0, r3)});
        unlock3APostCaptureUnlockAeParams = MapsKt.mapOf(TuplesKt.to(r0, Boolean.FALSE));
        aePrecaptureCancelParams = MapsKt.mapOf(TuplesKt.to(r8, 2));
        aePrecaptureAndAfCancelParams = MapsKt.mapOf(new Pair[]{TuplesKt.to(r6, 2), TuplesKt.to(r8, 2)});
        unlock3APostCaptureAfUnlockedCondition = Result3AStateListenerKt.toConditionChecker(MapsKt.mapOf(TuplesKt.to(CaptureResult.CONTROL_AF_STATE, r1)));
    }

    public Controller3A(GraphProcessor r1, CameraMetadata r2, GraphState3A r3, Listener3A r4) {
        this.graphProcessor = r1;
        this.metadata = r2;
        this.graphState3A = r3;
        this.graphListener3A = r4;
    }

    public static final /* synthetic */ Deferred access$getLastUpdate3AResult$p(Controller3A r0) {
        return r0.lastUpdate3AResult;
    }

    private final Map<CaptureResult.Key<?>, List<Object>> createConverged3AExitConditions(boolean r2, boolean r3, boolean r4) {
        if (r2 == true) goto L7;
        if (r3 == true) goto L7;
        if (r4 == true) goto L7;
        return MapsKt.emptyMap();
    L7:
        LinkedHashMap r1 = new LinkedHashMap();
        if (r2 == false) goto L10;
        r1.put(CaptureResult.CONTROL_AE_STATE, aeConvergedStateList);
    L10:
        if (r4 == false) goto L12;
        r1.put(CaptureResult.CONTROL_AWB_STATE, awbConvergedStateList);
    L12:
        if (r3 == false) goto L14;
        r1.put(CaptureResult.CONTROL_AF_STATE, afConvergedStateList);
    L14:
        return r1;
    }

    /* renamed from: createListenerFor3AParams-0dPwJB0, reason: not valid java name */
    private final Result3AStateListenerImpl m515createListenerFor3AParams0dPwJB0(AeMode r7, AfMode r8, AwbMode r9, FlashMode r10) {
        LinkedHashMap r6 = new LinkedHashMap();
        if (r7 == null) goto L5;
        int r72 = r7.m116unboximpl();
        List r73 = (List) r6.put(CaptureResult.CONTROL_AE_MODE, CollectionsKt.listOf(Integer.valueOf(r72)));
    L5:
        if (r8 == null) goto L7;
        int r74 = r8.m127unboximpl();
        List r75 = (List) r6.put(CaptureResult.CONTROL_AF_MODE, CollectionsKt.listOf(Integer.valueOf(r74)));
    L7:
        if (r9 == null) goto L9;
        int r76 = r9.m146unboximpl();
        List r77 = (List) r6.put(CaptureResult.CONTROL_AWB_MODE, CollectionsKt.listOf(Integer.valueOf(r76)));
    L9:
        if (r10 == null) goto L12;
        int r78 = r10.m260unboximpl();
        List r79 = (List) r6.put(CaptureResult.FLASH_MODE, CollectionsKt.listOf(Integer.valueOf(r78)));
    L12:
        return new Result3AStateListenerImpl(MapsKt.toMap(r6), null, null, 6, null);
    }

    private final Function1<FrameMetadata, Boolean> createLock3AForCaptureExitConditions(boolean r1, boolean r2) {
        return new Controller3A$$ExternalSyntheticLambda0(r2, r1);
    }

    private final Map<CaptureResult.Key<?>, List<Object>> createLocked3AExitConditions(boolean r2, boolean r3, boolean r4) {
        if (r2 == true) goto L7;
        if (r3 == true) goto L7;
        if (r4 == true) goto L7;
        return MapsKt.emptyMap();
    L7:
        LinkedHashMap r1 = new LinkedHashMap();
        if (r2 == false) goto L10;
        r1.put(CaptureResult.CONTROL_AE_STATE, aeLockedStateList);
    L10:
        if (r3 == false) goto L12;
        r1.put(CaptureResult.CONTROL_AF_STATE, afLockedStateList);
    L12:
        if (r4 == false) goto L14;
        r1.put(CaptureResult.CONTROL_AWB_STATE, awbLockedStateList);
    L14:
        return r1;
    }

    private final Map<CaptureResult.Key<?>, List<Object>> createUnLocked3AExitConditions(boolean r2, boolean r3, boolean r4) {
        if (r2 == true) goto L7;
        if (r3 == true) goto L7;
        if (r4 == true) goto L7;
        return MapsKt.emptyMap();
    L7:
        LinkedHashMap r1 = new LinkedHashMap();
        if (r2 == false) goto L10;
        r1.put(CaptureResult.CONTROL_AE_STATE, aeUnlockedStateList);
    L10:
        if (r3 == false) goto L12;
        r1.put(CaptureResult.CONTROL_AF_STATE, afUnlockedStateList);
    L12:
        if (r4 == false) goto L14;
        r1.put(CaptureResult.CONTROL_AWB_STATE, awbUnlockedStateList);
    L14:
        return r1;
    }

    private final Deferred<Result3A> lock3AForCapture(Map<CaptureRequest.Key<?>, ? extends Object> r7, Function1<? super FrameMetadata, Boolean> r8, int r9, long r10) {
        if (this.graphProcessor.getRepeatingRequest() == null) goto L5;
        if (r7 != null) goto L8;
        r7 = parametersForAePrecaptureAndAfTrigger;
    L8:
        Iterator<Map.Entry<CaptureRequest.Key<?>, ? extends Object>> r0 = r7.entrySet().iterator();
        boolean r2 = false;
    L10:
        if (r0.hasNext() == false) goto L15;
        if (Intrinsics.areEqual(r0.next().getValue(), 1) == false) goto L10;
        r2 = true;
        goto L10
    L15:
        if (r8 != null) goto L17;
        r8 = createLock3AForCaptureExitConditions(r2, false);
    L17:
        Result3AStateListenerImpl r02 = new Result3AStateListenerImpl(r8, Integer.valueOf(r9), Long.valueOf(r10));
        this.graphListener3A.addListener(r02);
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L21;
        android.util.Log.d("CXCP", "lock3AForCapture - sending a request to trigger ae precapture metering and af.");
    L21:
        if (this.graphProcessor.trigger(r7) == true) goto L24;
        this.graphListener3A.removeListener(r02);
        return deferredResult3ASubmitFailed;
    L24:
        this.graphProcessor.update3AParameters(this.graphState3A.toCaptureRequestParametersMap());
        return r02.getResult();
    L5:
        return deferredResult3ASubmitFailed;
    }

    /* renamed from: lock3ANow-R6AlCjU, reason: not valid java name */
    private final Deferred<Result3A> m516lock3ANowR6AlCjU(Lock3ABehavior r19, Lock3ABehavior r20, Lock3ABehavior r21, AeMode r22, Function1<? super FrameMetadata, Boolean> r23, Integer r24, Long r25) {
        AeMode r1 = null;
        if (r19 != null) goto L5;
        Boolean r11 = null;
    L6:
        if (r21 != null) goto L8;
        Boolean r13 = null;
    L9:
        boolean r2 = false;
        if (r11 == null) goto L12;
        boolean r4 = true;
    L13:
        if (r20 == null) goto L15;
        boolean r5 = true;
    L16:
        if (r13 == null) goto L18;
        r2 = true;
    L18:
        Map<CaptureResult.Key<?>, List<Object>> r26 = createLocked3AExitConditions(r4, r5, r2);
        if (r23 == null) goto L21;
    L24:
        if (r23 != null) goto L26;
        Function1<? super FrameMetadata, Boolean> r27 = Result3AStateListenerKt.toConditionChecker(r26);
    L27:
        Result3AStateListenerImpl r42 = new Result3AStateListenerImpl(r27, r24, r25);
        this.graphListener3A.addListener(r42);
        String r28 = "CXCP";
        GraphState3A.m527update7jOEVJU$default(this.graphState3A, null, null, null, null, null, null, null, r11, null, r13, 383, null);
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L30;
        android.util.Log.d(r28, "lock3A - submitting request with aeLock=" + r11 + " , awbLock=" + r13);
    L30:
        this.graphProcessor.update3AParameters(this.graphState3A.toCaptureRequestParametersMap());
        Deferred<Result3A> r3 = r42.getResult();
    L31:
        if (r20 == null) goto L45;
        if (r22 == null) goto L36;
        int r12 = r22.m116unboximpl();
        AeMode r43 = this.graphState3A.getCurrent().m535getAeModeO_cDUUs();
        GraphState3A.m527update7jOEVJU$default(this.graphState3A, AeMode.m109boximpl(r12), null, null, null, null, null, null, null, null, null, 1022, null);
        this.graphProcessor.update3AParameters(this.graphState3A.toCaptureRequestParametersMap());
        r1 = r43;
    L36:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L39;
        android.util.Log.d(r28, "lock3A - submitting a request to lock af.");
    L39:
        if (this.graphProcessor.trigger(parameterForAfTriggerStart) == false) goto L41;
        GraphState3A.m527update7jOEVJU$default(this.graphState3A, null, null, null, null, null, null, null, null, Boolean.TRUE, null, 767, null);
        if (r1 == null) goto L45;
        GraphState3A.m527update7jOEVJU$default(this.graphState3A, AeMode.m109boximpl(r1.m116unboximpl()), null, null, null, null, null, null, null, null, null, 1022, null);
        this.graphProcessor.update3AParameters(this.graphState3A.toCaptureRequestParametersMap());
        goto L45
    L41:
        return deferredResult3ASubmitFailed;
    L45:
        return r3;
    L26:
        r27 = r23;
        goto L27
    L21:
        if (r26.isEmpty() == false) goto L24;
        r28 = "CXCP";
        r3 = null;
        goto L31
    L15:
        r5 = false;
        goto L16
    L12:
        r4 = false;
        goto L13
    L8:
        r13 = Boolean.TRUE;
        goto L9
    L5:
        r11 = Boolean.TRUE;
        goto L6
    }

    private final Deferred<Result3A> unlock3APostCaptureAndroidMAndAbove(boolean r8) {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", "unlock3APostCapture - sending a request to reset af and ae precapture metering.");
    L5:
        if (r8 == false) goto L7;
        Map<CaptureRequest.Key<?>, Object> r0 = aePrecaptureAndAfCancelParams;
    L9:
        if (this.graphProcessor.trigger(r0) == false) goto L11;
        if (r8 == false) goto L14;
        Result3AStateListenerImpl r02 = new Result3AStateListenerImpl(unlock3APostCaptureAfUnlockedCondition, null, null, 6, null);
    L15:
        this.graphListener3A.addListener(r02);
        this.graphProcessor.update3AParameters(this.graphState3A.toCaptureRequestParametersMap());
        return r02.getResult();
    L14:
        r02 = new Result3AStateListenerImpl(MapsKt.emptyMap(), null, null, 6, null);
        goto L15
    L11:
        return deferredResult3ASubmitFailed;
    L7:
        r0 = aePrecaptureCancelParams;
        goto L9
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: update3A-169HPGg$default, reason: not valid java name */
    public static /* synthetic */ Deferred m517update3A169HPGg$default(Controller3A r1, AeMode r2, AfMode r3, AwbMode r4, FlashMode r5, List r6, List r7, List r8, int r9, Object r10) {
        if ((r9 & 1) == 0) goto L6;
        r2 = null;
    L6:
        if ((r9 & 2) == 0) goto L9;
        r3 = null;
    L9:
        if ((r9 & 4) == 0) goto L12;
        r4 = null;
    L12:
        if ((r9 & 8) == 0) goto L15;
        r5 = null;
    L15:
        if ((r9 & 16) == 0) goto L18;
        r6 = null;
    L18:
        if ((r9 & 32) == 0) goto L21;
        r7 = null;
    L21:
        if ((r9 & 64) == 0) goto L24;
        r8 = null;
    L24:
        return r1.m520update3A169HPGg(r2, r3, r4, r5, r6, r7, r8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: lock3A-Qz1gx5w, reason: not valid java name */
    public final Object m518lock3AQz1gx5w(List<MeteringRectangle> r26, List<MeteringRectangle> r27, List<MeteringRectangle> r28, Lock3ABehavior r29, Lock3ABehavior r30, Lock3ABehavior r31, AeMode r32, Function1<? super FrameMetadata, Boolean> r33, Function1<? super FrameMetadata, Boolean> r34, int r35, Long r36, Long r37, Continuation<? super Deferred<Result3A>> r38) {
        Lock3ABehavior r2 = r31;
        if ((r38 instanceof Controller3A$lock3A$1) == false) goto L7;
        Controller3A$lock3A$1 r4 = (Controller3A$lock3A$1) r38;
        int r5 = r4.label;
        if ((r5 & Integer.MIN_VALUE) == 0) goto L7;
        r4.label = r5 - Integer.MIN_VALUE;
    L8:
        Object r3 = r4.result;
        Object r52 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r6 = r4.label;
        Long r9 = null;
        if (r6 == 0) goto L14;
        if (r6 != 1) goto L12;
        int r1 = r4.I$0;
        Result3AStateListenerImpl r22 = (Result3AStateListenerImpl) r4.L$6;
        Ref.ObjectRef r53 = (Ref.ObjectRef) r4.L$5;
        Long r62 = (Long) r4.L$4;
        Function1<? super FrameMetadata, Boolean> r7 = (Function1) r4.L$3;
        AeMode r10 = (AeMode) r4.L$2;
        Lock3ABehavior r11 = (Lock3ABehavior) r4.L$1;
        Lock3ABehavior r42 = (Lock3ABehavior) r4.L$0;
        ResultKt.throwOnFailure(r3);
        int r14 = r1;
        Result3AStateListenerImpl r12 = r22;
        r2 = r11;
    L79:
        Result3A r39 = (Result3A) r3;
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L86;
        StringBuilder r112 = new StringBuilder("lock3A - converged at frame number=");
        FrameMetadata r122 = r39.getFrameMetadata();
        if (r122 == null) goto L84;
        r9 = Boxing.boxLong(r122.mo266getFrameNumberUgla2oM());
    L84:
        r112.append(r9);
        r112.append(", status=");
        r112.append(Result3A.Status.m382toStringimpl(r39.m378getStatusJvTi9ms()));
        android.util.Log.d("CXCP", r112.toString());
    L86:
        if (Result3A.Status.m380equalsimpl0(r39.m378getStatusJvTi9ms(), Result3A.Status.Companion.m384getOKJvTi9ms()) == false) goto L88;
        Ref.ObjectRef r310 = r53;
    L91:
        return m516lock3ANowR6AlCjU(r42, (Lock3ABehavior) r310.element, r2, r10, r7, Boxing.boxInt(r14), r62);
    L88:
        return r12.getResult();
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r3);
        r310 = new Ref.ObjectRef();
        r310.element = r30;
        if (CameraMetadata.Companion.getSupportsAutoFocusTrigger(this.metadata) == true) goto L17;
        r310.element = null;
    L17:
        if (r29 == null) goto L19;
    L23:
        GraphState3A.m527update7jOEVJU$default(this.graphState3A, null, null, null, null, r26, r27, r28, null, null, null, 911, null);
        this.graphProcessor.update3AParameters(this.graphState3A.toCaptureRequestParametersMap());
        if (this.graphProcessor.getRepeatingRequest() != null) goto L28;
        return deferredResult3ASubmitFailed;
    L28:
        if (Controller3AKt.m522shouldUnlockAft6FjEyI((Lock3ABehavior) r310.element) == false) goto L37;
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L33;
        android.util.Log.d("CXCP", "lock3A - sending a request to unlock af first.");
    L33:
        if (this.graphProcessor.trigger(parameterForAfTriggerCancel) == true) goto L37;
        return deferredResult3ASubmitFailed;
    L37:
        if (Controller3AKt.m524shouldWaitForAeToConverget6FjEyI(r29) == false) goto L39;
    L44:
        if (r33 != null) goto L46;
        Function1<? super FrameMetadata, Boolean> r63 = Result3AStateListenerKt.toConditionChecker(createConverged3AExitConditions(Controller3AKt.m524shouldWaitForAeToConverget6FjEyI(r29), Controller3AKt.m525shouldWaitForAfToConverget6FjEyI((Lock3ABehavior) r310.element), Controller3AKt.m526shouldWaitForAwbToConverget6FjEyI(r2)));
    L47:
        Result3AStateListenerImpl r102 = new Result3AStateListenerImpl(r63, Boxing.boxInt(r35), r36);
        this.graphListener3A.addListener(r102);
        if (Controller3AKt.m521shouldUnlockAet6FjEyI(r29) == false) goto L50;
        Boolean r64 = Boxing.boxBoolean(false);
    L52:
        if (Controller3AKt.m523shouldUnlockAwbt6FjEyI(r2) == false) goto L54;
        Boolean r113 = Boxing.boxBoolean(false);
    L55:
        if (r64 != null) goto L58;
        if (r113 != null) goto L58;
    L61:
        this.graphProcessor.update3AParameters(this.graphState3A.toCaptureRequestParametersMap());
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L75;
        StringBuilder r65 = new StringBuilder("lock3A - waiting for");
        boolean r114 = Controller3AKt.m524shouldWaitForAeToConverget6FjEyI(r29);
        String r123 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r114 == false) goto L66;
        String r115 = " ae";
    L67:
        r65.append(r115);
        if (Controller3AKt.m525shouldWaitForAfToConverget6FjEyI((Lock3ABehavior) r310.element) == false) goto L70;
        String r116 = " af";
    L71:
        r65.append(r116);
        if (Controller3AKt.m526shouldWaitForAwbToConverget6FjEyI(r2) == false) goto L74;
        r123 = " awb";
    L74:
        r65.append(r123);
        r65.append(" to converge before locking them.");
        android.util.Log.d("CXCP", r65.toString());
        goto L75
    L70:
        r116 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L71
    L66:
        r115 = _UrlKt.FRAGMENT_ENCODE_SET;
    L75:
        Deferred<Result3A> r66 = r102.getResult();
        r4.L$0 = r29;
        r4.L$1 = r2;
        r4.L$2 = r32;
        r4.L$3 = r34;
        r4.L$4 = r37;
        r4.L$5 = r310;
        r4.L$6 = r102;
        r14 = r35;
        r4.I$0 = r14;
        r4.label = 1;
        Object r43 = r66.await(r4);
        if (r43 != r52) goto L78;
        return r52;
    L78:
        r53 = r310;
        r3 = r43;
        r7 = r34;
        r62 = r37;
        r42 = r29;
        r12 = r102;
        r10 = r32;
    L58:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L60;
        android.util.Log.d("CXCP", "lock3A - setting aeLock=" + r64 + ", awbLock=" + r113);
    L60:
        GraphState3A.m527update7jOEVJU$default(this.graphState3A, null, null, null, null, null, null, null, r64, null, r113, 383, null);
        goto L61
    L54:
        r113 = null;
        goto L55
    L50:
        r64 = null;
        goto L52
    L46:
        r63 = r33;
        goto L47
    L39:
        if (Controller3AKt.m525shouldWaitForAfToConverget6FjEyI((Lock3ABehavior) r310.element) == true) goto L44;
        if (Controller3AKt.m526shouldWaitForAwbToConverget6FjEyI(r2) == true) goto L44;
        r10 = r32;
        r7 = r34;
        r14 = r35;
        r62 = r37;
        r42 = r29;
        goto L91
    L19:
        if (r310.element != 0) goto L23;
        if (r2 != null) goto L23;
        return CompletableDeferredKt.CompletableDeferred(new Result3A(Result3A.Status.Companion.m384getOKJvTi9ms(), null, null));
    L7:
        r4 = new Controller3A$lock3A$1(this, r38);
        goto L8
    }

    public final Deferred<Result3A> lock3AForCapture(boolean r2, boolean r3, int r4, long r5) {
        if (r2 == false) goto L4;
        Map<CaptureRequest.Key<?>, ? extends Object> r0 = parametersForAePrecaptureAndAfTrigger;
    L5:
        Function1<FrameMetadata, Boolean> r32 = createLock3AForCaptureExitConditions(r2, r3);
        return lock3AForCapture(r0, r32, r4, r5);
    L4:
        r0 = parametersForAePrecapture;
        goto L5
    }

    /* renamed from: setTorchOff-NqN7i0k, reason: not valid java name */
    public final Deferred<Result3A> m519setTorchOffNqN7i0k(AeMode r12) {
        return m517update3A169HPGg$default(this, r12, null, null, FlashMode.m255boximpl(FlashMode.Companion.m261getOFFLe5xUZU()), null, null, null, 118, null);
    }

    public final Deferred<Result3A> setTorchOn() {
        AeMode r0 = this.graphState3A.getCurrent().m535getAeModeO_cDUUs();
        AeMode.Companion r1 = AeMode.Companion;
        int r2 = r1.m119getONbOjpiJc();
        boolean r3 = false;
        if (r0 != null) goto L5;
        boolean r22 = false;
    L6:
        if (r22 == true) goto L15;
        int r23 = r1.m118getOFFbOjpiJc();
        if (r0 == null) goto L11;
        r3 = AeMode.m112equalsimpl0(r0.m116unboximpl(), r23);
    L11:
        if (r3 == true) goto L15;
        AeMode r02 = AeMode.m109boximpl(r1.m119getONbOjpiJc());
    L17:
        return m517update3A169HPGg$default(this, r02, null, null, FlashMode.m255boximpl(FlashMode.Companion.m262getTORCHLe5xUZU()), null, null, null, 118, null);
    L15:
        r02 = null;
        goto L17
    L5:
        r22 = AeMode.m112equalsimpl0(r0.m116unboximpl(), r2);
        goto L6
    }

    public final Deferred<Result3A> unlock3A(Boolean r22, Boolean r23, Boolean r24, Function1<? super FrameMetadata, Boolean> r25, int r26, Long r27) {
        Boolean r4 = null;
        if (CameraMetadata.Companion.getSupportsAutoFocusTrigger(this.metadata) == true) goto L5;
        Boolean r3 = null;
    L6:
        Boolean r5 = Boolean.TRUE;
        if (Intrinsics.areEqual(r22, r5) == true) goto L15;
        if (Intrinsics.areEqual(r3, r5) == true) goto L15;
        if (Intrinsics.areEqual(r24, r5) == true) goto L15;
        return CompletableDeferredKt.CompletableDeferred(new Result3A(Result3A.Status.Companion.m384getOKJvTi9ms(), null, null));
    L15:
        if (this.graphProcessor.getRepeatingRequest() != null) goto L19;
        return deferredResult3ASubmitFailed;
    L19:
        if (Intrinsics.areEqual(r3, r5) == false) goto L31;
        Log r6 = Log.INSTANCE;
        if (r6.getDEBUG_LOGGABLE() == false) goto L24;
        android.util.Log.d("CXCP", "unlock3A - sending a request to unlock af first.");
    L24:
        if (this.graphProcessor.trigger(parameterForAfTriggerCancel) == false) goto L26;
        GraphState3A.m527update7jOEVJU$default(this.graphState3A, null, null, null, null, null, null, null, null, Boolean.FALSE, null, 767, null);
        goto L31
    L26:
        if (r6.getDEBUG_LOGGABLE() == false) goto L29;
        android.util.Log.d("CXCP", "unlock3A - failed to send a request to unlock af first.");
    L29:
        return deferredResult3ASubmitFailed;
    L31:
        if (r25 != null) goto L33;
        Function1<? super FrameMetadata, Boolean> r32 = Result3AStateListenerKt.toConditionChecker(createUnLocked3AExitConditions(Intrinsics.areEqual(r22, r5), Intrinsics.areEqual(r3, r5), Intrinsics.areEqual(r24, r5)));
    L34:
        Result3AStateListenerImpl r62 = new Result3AStateListenerImpl(r32, Integer.valueOf(r26), r27);
        this.graphListener3A.addListener(r62);
        if (Intrinsics.areEqual(r22, r5) == false) goto L37;
        Boolean r1 = Boolean.FALSE;
    L39:
        if (Intrinsics.areEqual(r24, r5) == false) goto L41;
        r4 = Boolean.FALSE;
    L41:
        if (r1 != null) goto L44;
        if (r4 != null) goto L44;
    L47:
        this.graphProcessor.update3AParameters(this.graphState3A.toCaptureRequestParametersMap());
        return r62.getResult();
    L44:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L46;
        android.util.Log.d("CXCP", "unlock3A - updating graph state, aeLock=" + r1 + ", awbLock=" + r4);
    L46:
        GraphState3A.m527update7jOEVJU$default(this.graphState3A, null, null, null, null, null, null, null, r1, null, r4, 383, null);
        goto L47
    L37:
        r1 = null;
        goto L39
    L33:
        r32 = r25;
        goto L34
    L5:
        r3 = r23;
        goto L6
    }

    public final Deferred<Result3A> unlock3APostCapture(boolean r2) {
        if (this.graphProcessor.getRepeatingRequest() != null) goto L7;
        return deferredResult3ASubmitFailed;
    L7:
        return unlock3APostCaptureAndroidMAndAbove(r2);
    }

    /* renamed from: update3A-169HPGg, reason: not valid java name */
    public final Deferred<Result3A> m520update3A169HPGg(AeMode r17, AfMode r18, AwbMode r19, FlashMode r20, List<MeteringRectangle> r21, List<MeteringRectangle> r22, List<MeteringRectangle> r23) {
        if (this.graphProcessor.getRepeatingRequest() != null) goto L6;
        GraphState3A.m527update7jOEVJU$default(this.graphState3A, r17, r18, r19, r20, r21, r22, r23, null, null, null, 896, null);
        this.graphProcessor.update3AParameters(this.graphState3A.toCaptureRequestParametersMap());
        return deferredResult3ASubmitFailed;
    L6:
        Result3AStateListenerImpl r15 = m515createListenerFor3AParams0dPwJB0(r17, r18, r19, r20);
        this.graphListener3A.addListener(r15);
        GraphState3A.m527update7jOEVJU$default(this.graphState3A, r17, r18, r19, r20, r21, r22, r23, null, null, null, 896, null);
        this.graphProcessor.update3AParameters(this.graphState3A.toCaptureRequestParametersMap());
        Deferred<Result3A> r2 = r15.getResult();
        monitor-enter(this);
    L11:
        th = move-exception;
        throw th;
    L9:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L13;
        android.util.Log.d("CXCP", "Controller3A#update3A: cancelling previous request " + access$getLastUpdate3AResult$p(this));     // Catch: Throwable -> L11
    L13:
        Deferred<Result3A> r0 = this.lastUpdate3AResult;     // Catch: Throwable -> L11
        if (r0 == null) goto L16;
        JobKt.cancel$default(r0, "A newer call for 3A state update initiated.", null, 2, null);     // Catch: Throwable -> L11
    L16:
        this.lastUpdate3AResult = r2;     // Catch: Throwable -> L11
        Unit r02 = Unit.INSTANCE;     // Catch: Throwable -> L11
        monitor-exit(this);
        return r2;
    }
}
