package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Trace;
import android.view.Surface;
import androidx.camera.camera2.impl.DisplayInfoManager$$ExternalSyntheticBUOutline0;
import androidx.camera.camera2.pipe.CameraStream;
import androidx.camera.camera2.pipe.CameraTimestamp;
import androidx.camera.camera2.pipe.CaptureSequence;
import androidx.camera.camera2.pipe.CaptureSequences;
import androidx.camera.camera2.pipe.FrameNumber;
import androidx.camera.camera2.pipe.OutputId;
import androidx.camera.camera2.pipe.OutputStream;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestFailure;
import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.camera2.pipe.SensorTimestamp;
import androidx.camera.camera2.pipe.StreamGraph;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.camera2.pipe.StrictMode;
import androidx.camera.camera2.pipe.core.Debug;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.graph.SurfaceGraph$$ExternalSyntheticBUOutline0;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import retrofit2.Utils$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\b\u0012\u0004\u0012\u00020\u00040\u0003B\u0081\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\n\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\n\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012\u0012\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00160\u0012\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u001a¢\u0006\u0004\b\u001b\u0010\u001cJ(\u00106\u001a\u00020,2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u00042\u0006\u0010:\u001a\u00020)2\u0006\u0010;\u001a\u00020)H\u0016J \u00106\u001a\u00020,2\u0006\u00109\u001a\u00020\u00042\u0006\u0010;\u001a\u00020)2\u0006\u0010:\u001a\u00020)H\u0016J \u0010<\u001a\u00020,2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u00042\u0006\u0010=\u001a\u00020>H\u0016J\u0018\u0010<\u001a\u00020,2\u0006\u00109\u001a\u00020\u00042\u0006\u0010=\u001a\u00020>H\u0016J(\u0010?\u001a\u00020,2\u0006\u0010@\u001a\u0002082\u0006\u00109\u001a\u00020\u00042\u0006\u0010:\u001a\u00020)2\u0006\u0010;\u001a\u00020)H\u0016J \u0010A\u001a\u00020,2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u00042\u0006\u0010B\u001a\u00020CH\u0016J'\u0010A\u001a\u00020,2\u0006\u00109\u001a\u00020\u00042\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020EH\u0016¢\u0006\u0004\bF\u0010GJ\u0018\u0010H\u001a\u00020,2\u0006\u00109\u001a\u00020\u00042\u0006\u0010I\u001a\u00020.H\u0016J \u0010J\u001a\u00020,2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u00042\u0006\u0010K\u001a\u00020LH\u0016J'\u0010M\u001a\u00020,2\u0006\u0010N\u001a\u00020\f2\u0006\u0010D\u001a\u00020E2\u0006\u0010O\u001a\u00020PH\u0002¢\u0006\u0004\bQ\u0010RJ\u001f\u0010J\u001a\u00020,2\u0006\u00109\u001a\u00020\u00042\u0006\u0010D\u001a\u00020EH\u0016¢\u0006\u0004\bS\u0010TJ(\u0010U\u001a\u00020,2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u00042\u0006\u0010V\u001a\u00020\u00132\u0006\u0010W\u001a\u00020)H\u0016J\u0017\u0010X\u001a\u0004\u0018\u00010\u00142\u0006\u0010V\u001a\u00020\u0013H\u0002¢\u0006\u0002\bYJ \u0010Z\u001a\u00020,2\u0006\u00107\u001a\u0002082\u0006\u0010[\u001a\u00020.2\u0006\u0010;\u001a\u00020)H\u0016J\u0018\u0010Z\u001a\u00020,2\u0006\u0010[\u001a\u00020.2\u0006\u0010;\u001a\u00020)H\u0016J\u0018\u0010\\\u001a\u00020,2\u0006\u00107\u001a\u0002082\u0006\u0010[\u001a\u00020.H\u0016J\u0010\u0010\\\u001a\u00020,2\u0006\u0010[\u001a\u00020.H\u0016J\u0010\u0010]\u001a\u00020\f2\u0006\u00109\u001a\u00020\u0004H\u0002J\u0010\u0010^\u001a\u00020,H\u0080@¢\u0006\u0004\b_\u0010`J\b\u0010a\u001a\u00020bH\u0016R\u0016\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010#R\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00160\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0004\n\u0002\u0010/R$\u00101\u001a\u00020.2\u0006\u00100\u001a\u00020.8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b2\u00103\"\u0004\b4\u00105¨\u0006c"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2CaptureSequence;", "Landroidx/camera/camera2/pipe/compat/Camera2CaptureCallback;", "Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;", "Landroidx/camera/camera2/pipe/CaptureSequence;", "Landroid/hardware/camera2/CaptureRequest;", "cameraId", "Landroidx/camera/camera2/pipe/CameraId;", "repeating", _UrlKt.FRAGMENT_ENCODE_SET, "captureRequestList", _UrlKt.FRAGMENT_ENCODE_SET, "captureMetadataList", "Landroidx/camera/camera2/pipe/RequestMetadata;", "listeners", "Landroidx/camera/camera2/pipe/Request$Listener;", "sequenceListener", "Landroidx/camera/camera2/pipe/CaptureSequence$CaptureSequenceListener;", "surfaceToStreamMap", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/view/Surface;", "Landroidx/camera/camera2/pipe/StreamId;", "surfaceToOutputMap", "Landroidx/camera/camera2/pipe/OutputId;", "streamGraph", "Landroidx/camera/camera2/pipe/StreamGraph;", "strictMode", "Landroidx/camera/camera2/pipe/StrictMode;", "<init>", "(Ljava/lang/String;ZLjava/util/List;Ljava/util/List;Ljava/util/List;Landroidx/camera/camera2/pipe/CaptureSequence$CaptureSequenceListener;Ljava/util/Map;Ljava/util/Map;Landroidx/camera/camera2/pipe/StreamGraph;Landroidx/camera/camera2/pipe/StrictMode;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getCameraId-Dz_R5H8", "()Ljava/lang/String;", "Ljava/lang/String;", "getRepeating", "()Z", "getCaptureRequestList", "()Ljava/util/List;", "getCaptureMetadataList", "getListeners", "getSequenceListener", "()Landroidx/camera/camera2/pipe/CaptureSequence$CaptureSequenceListener;", "debugId", _UrlKt.FRAGMENT_ENCODE_SET, "hasStarted", "Lkotlinx/coroutines/CompletableDeferred;", _UrlKt.FRAGMENT_ENCODE_SET, "_sequenceNumber", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/Integer;", "value", "sequenceNumber", "getSequenceNumber", "()I", "setSequenceNumber", "(I)V", "onCaptureStarted", "captureSession", "Landroid/hardware/camera2/CameraCaptureSession;", "captureRequest", "captureTimestamp", "captureFrameNumber", "onCaptureProgressed", "partialCaptureResult", "Landroid/hardware/camera2/CaptureResult;", "onReadoutStarted", "session", "onCaptureCompleted", "captureResult", "Landroid/hardware/camera2/TotalCaptureResult;", "frameNumber", "Landroidx/camera/camera2/pipe/FrameNumber;", "onCaptureCompleted-rmrZIYk", "(Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/TotalCaptureResult;J)V", "onCaptureProcessProgressed", "progress", "onCaptureFailed", "captureFailure", "Landroid/hardware/camera2/CaptureFailure;", "invokeCaptureFailure", PluginsConstants.REQUEST, "requestFailure", "Landroidx/camera/camera2/pipe/RequestFailure;", "invokeCaptureFailure-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/RequestFailure;)V", "onCaptureFailed-RuT0dZU", "(Landroid/hardware/camera2/CaptureRequest;J)V", "onCaptureBufferLost", "surface", "frameId", "getStreamId", "getStreamId-Lfjdq8s", "onCaptureSequenceCompleted", "captureSequenceId", "onCaptureSequenceAborted", "readRequestMetadata", "awaitStarted", "awaitStarted$camera_camera2_pipe", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCamera2CaptureSequence.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2CaptureSequence.kt\nandroidx/camera/camera2/pipe/compat/Camera2CaptureSequence\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n+ 4 CaptureSequence.kt\nandroidx/camera/camera2/pipe/CaptureSequences\n+ 5 StrictMode.kt\nandroidx/camera/camera2/pipe/StrictMode\n+ 6 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,348:1\n1#2:349\n71#3,4:350\n71#3,4:355\n78#3,4:364\n71#3,4:369\n78#3,4:378\n78#3,4:383\n71#3,4:387\n71#3,4:392\n78#3,4:401\n71#3,4:406\n78#3,4:415\n78#3,4:420\n71#3,4:424\n71#3,4:429\n78#3,4:438\n71#3,4:443\n78#3,4:452\n78#3,4:457\n71#3,4:461\n71#3,4:465\n78#3,4:469\n71#3,4:473\n71#3,4:478\n78#3,4:487\n71#3,4:492\n78#3,4:501\n78#3,4:506\n71#3,4:510\n71#3,4:515\n78#3,4:524\n71#3,4:529\n78#3,4:538\n78#3,4:543\n78#3,4:547\n71#3,4:551\n71#3,4:556\n78#3,4:565\n71#3,4:570\n78#3,4:579\n78#3,4:584\n71#3,4:588\n78#3,4:592\n71#3,4:597\n78#3,4:606\n71#3,4:619\n78#3,4:623\n71#3,4:627\n71#3,4:632\n78#3,4:641\n71#3,4:646\n78#3,4:655\n71#3,4:661\n78#3,4:670\n71#3,4:675\n78#3,4:684\n78#3,4:689\n71#3,4:693\n71#3,4:709\n78#3,4:721\n71#3,4:726\n78#3,4:738\n78#3,4:743\n71#3,4:747\n71#3,4:763\n78#3,4:775\n71#3,4:780\n78#3,4:792\n78#3,4:797\n87#4:354\n91#4,5:359\n96#4:368\n99#4,5:373\n104#4:382\n87#4:391\n91#4,5:396\n96#4:405\n99#4,5:410\n104#4:419\n87#4:428\n91#4,5:433\n96#4:442\n99#4,5:447\n104#4:456\n87#4:477\n91#4,5:482\n96#4:491\n99#4,5:496\n104#4:505\n87#4:514\n91#4,5:519\n96#4:528\n99#4,5:533\n104#4:542\n87#4:555\n91#4,5:560\n96#4:569\n99#4,5:574\n104#4:583\n87#4:596\n91#4,5:601\n96#4,9:610\n87#4:631\n91#4,5:636\n96#4:645\n99#4,5:650\n104#4:659\n87#4:660\n91#4,5:665\n96#4:674\n99#4,5:679\n104#4:688\n55#4:708\n58#4,8:713\n66#4:725\n69#4,8:730\n77#4:742\n55#4:762\n58#4,8:767\n66#4:779\n69#4,8:784\n77#4:796\n25#5,4:697\n29#5,5:703\n25#5,4:751\n29#5,5:757\n71#6,2:701\n71#6,2:755\n*S KotlinDebug\n*F\n+ 1 Camera2CaptureSequence.kt\nandroidx/camera/camera2/pipe/compat/Camera2CaptureSequence\n*L\n105#1:350,4\n115#1:355,4\n115#1:364,4\n115#1:369,4\n115#1:378,4\n116#1:383,4\n129#1:387,4\n137#1:392,4\n137#1:401,4\n137#1:406,4\n137#1:415,4\n138#1:420,4\n147#1:424,4\n155#1:429,4\n155#1:438,4\n155#1:443,4\n155#1:452,4\n156#1:457,4\n170#1:461,4\n171#1:465,4\n173#1:469,4\n180#1:473,4\n181#1:478,4\n181#1:487,4\n181#1:492,4\n181#1:501,4\n182#1:506,4\n186#1:510,4\n187#1:515,4\n187#1:524,4\n187#1:529,4\n187#1:538,4\n188#1:543,4\n189#1:547,4\n193#1:551,4\n197#1:556,4\n197#1:565,4\n197#1:570,4\n197#1:579,4\n198#1:584,4\n206#1:588,4\n219#1:592,4\n228#1:597,4\n228#1:606,4\n232#1:619,4\n247#1:623,4\n257#1:627,4\n268#1:632,4\n268#1:641,4\n268#1:646,4\n268#1:655,4\n269#1:661,4\n269#1:670,4\n269#1:675,4\n269#1:684,4\n270#1:689,4\n292#1:693,4\n302#1:709,4\n302#1:721,4\n302#1:726,4\n302#1:738,4\n305#1:743,4\n314#1:747,4\n323#1:763,4\n323#1:775,4\n323#1:780,4\n323#1:792,4\n324#1:797,4\n115#1:354\n115#1:359,5\n115#1:368\n115#1:373,5\n115#1:382\n137#1:391\n137#1:396,5\n137#1:405\n137#1:410,5\n137#1:419\n155#1:428\n155#1:433,5\n155#1:442\n155#1:447,5\n155#1:456\n181#1:477\n181#1:482,5\n181#1:491\n181#1:496,5\n181#1:505\n187#1:514\n187#1:519,5\n187#1:528\n187#1:533,5\n187#1:542\n197#1:555\n197#1:560,5\n197#1:569\n197#1:574,5\n197#1:583\n228#1:596\n228#1:601,5\n228#1:610,9\n268#1:631\n268#1:636,5\n268#1:645\n268#1:650,5\n268#1:659\n269#1:660\n269#1:665,5\n269#1:674\n269#1:679,5\n269#1:688\n302#1:708\n302#1:713,8\n302#1:725\n302#1:730,8\n302#1:742\n323#1:762\n323#1:767,8\n323#1:779\n323#1:784,8\n323#1:796\n296#1:697,4\n296#1:703,5\n318#1:751,4\n318#1:757,5\n296#1:701,2\n318#1:755,2\n*E\n"})
/* loaded from: classes3.dex */
public final class Camera2CaptureSequence extends CameraCaptureSession.CaptureCallback implements Camera2CaptureCallback, CaptureSequence<CaptureRequest> {
    private volatile Integer _sequenceNumber;
    private final String cameraId;
    private final List<RequestMetadata> captureMetadataList;
    private final List<CaptureRequest> captureRequestList;
    private final long debugId;
    private final CompletableDeferred<Unit> hasStarted;
    private final List<Request.Listener> listeners;
    private final boolean repeating;
    private final CaptureSequence.CaptureSequenceListener sequenceListener;
    private final StreamGraph streamGraph;
    private final StrictMode strictMode;
    private final Map<Surface, OutputId> surfaceToOutputMap;
    private final Map<Surface, StreamId> surfaceToStreamMap;

    /* JADX WARN: Multi-variable type inference failed */
    private Camera2CaptureSequence(String r1, boolean r2, List<CaptureRequest> r3, List<? extends RequestMetadata> r4, List<? extends Request.Listener> r5, CaptureSequence.CaptureSequenceListener r6, Map<Surface, StreamId> r7, Map<Surface, OutputId> r8, StreamGraph r9, StrictMode r10) {
        this.cameraId = r1;
        this.repeating = r2;
        this.captureRequestList = r3;
        this.captureMetadataList = r4;
        this.listeners = r5;
        this.sequenceListener = r6;
        this.surfaceToStreamMap = r7;
        this.surfaceToOutputMap = r8;
        this.streamGraph = r9;
        this.strictMode = r10;
        this.debugId = Camera2CaptureSequenceProcessorKt.getCaptureSequenceDebugIds().incrementAndGet();
        this.hasStarted = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        if (getCaptureRequestList().size() != getCaptureMetadataList().size()) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("CaptureRequestList and CaptureMetadataList must have a 1:1 mapping.");
        throw null;
    }

    public /* synthetic */ Camera2CaptureSequence(String r1, boolean r2, List r3, List r4, List r5, CaptureSequence.CaptureSequenceListener r6, Map r7, Map r8, StreamGraph r9, StrictMode r10, DefaultConstructorMarker r11) {
        this(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    /* renamed from: getStreamId-Lfjdq8s, reason: not valid java name */
    private final StreamId m439getStreamIdLfjdq8s(Surface r2) {
        StreamId r0 = this.surfaceToStreamMap.get(r2);
        if (r0 == null) goto L5;
        return r0;
    L5:
        OutputId r22 = this.surfaceToOutputMap.get(r2);
        if (r22 == null) goto L8;
        OutputStream r1 = this.streamGraph.m400getiYJqvbA(r22.m296unboximpl());
    L9:
        if (r1 == null) goto L14;
        CameraStream r12 = r1.getStream();
        if (r12 == null) goto L14;
        return StreamId.m401boximpl(r12.m241getIdptHMqGs());
    L14:
        return null;
    L8:
        r1 = null;
        goto L9
    }

    /* renamed from: invokeCaptureFailure-CcXjc1I, reason: not valid java name */
    private final void m440invokeCaptureFailureCcXjc1I(RequestMetadata r5, long r6, RequestFailure r8) {
        getSequenceListener().onCaptureSequenceComplete(this);
        CaptureSequences r0 = CaptureSequences.INSTANCE;
        Debug r02 = Debug.INSTANCE;
        Trace.beginSection("InvokeInternalListeners");
        int r03 = getListeners().size();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r2 >= r03) goto L5;
        getListeners().get(r2).mo14onFailedCcXjc1I(r5, r6, r8);
        r2 = r2 + 1;
        goto L3
    L5:
        Debug r4 = Debug.INSTANCE;
        Trace.endSection();
        Trace.beginSection("InvokeRequestListeners");
        int r42 = r5.getRequest().getListeners().size();
    L6:
        if (r1 >= r42) goto L8;
        r5.getRequest().getListeners().get(r1).mo14onFailedCcXjc1I(r5, r6, r8);
        r1 = r1 + 1;
        goto L6
    L8:
        Debug r43 = Debug.INSTANCE;
        Trace.endSection();
    }

    private final RequestMetadata readRequestMetadata(CaptureRequest r4) {
        int r0 = getCaptureRequestList().size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        if (getCaptureRequestList().get(r1) == r4) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return getCaptureMetadataList().get(r1);
    L9:
        StringBuilder r02 = new StringBuilder("Failed to find CaptureRequest ");
        r02.append(r4);
        Utils$$ExternalSyntheticBUOutline1.m(r02, " in ", getCaptureRequestList());
        return null;
    }

    public final Object awaitStarted$camera_camera2_pipe(Continuation<? super Unit> r1) {
        Object r0 = this.hasStarted.await(r1);
        if (r0 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r0;
    L6:
        return Unit.INSTANCE;
    }

    /* renamed from: getCameraId-Dz_R5H8, reason: not valid java name */
    public String m441getCameraIdDz_R5H8() {
        return this.cameraId;
    }

    @Override // androidx.camera.camera2.pipe.CaptureSequence
    public List<RequestMetadata> getCaptureMetadataList() {
        return this.captureMetadataList;
    }

    public List<CaptureRequest> getCaptureRequestList() {
        return this.captureRequestList;
    }

    @Override // androidx.camera.camera2.pipe.CaptureSequence
    public List<Request.Listener> getListeners() {
        return this.listeners;
    }

    @Override // androidx.camera.camera2.pipe.CaptureSequence
    public boolean getRepeating() {
        return this.repeating;
    }

    public CaptureSequence.CaptureSequenceListener getSequenceListener() {
        return this.sequenceListener;
    }

    public int getSequenceNumber() {
        if (this._sequenceNumber != null) goto L16;
        monitor-enter(this);
        Integer r1 = this._sequenceNumber;     // Catch: Throwable -> L10
        if (r1 == null) goto L13;
        int r0 = r1.intValue();     // Catch: Throwable -> L10
        monitor-exit(this);
        return r0;
    L13:
        throw new IllegalStateException(("SequenceNumber has not been set for " + this + '!').toString());     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    L16:
        Integer r02 = this._sequenceNumber;
        if (r02 != null) goto L19;
        DisplayInfoManager$$ExternalSyntheticBUOutline0.m("SequenceNumber has not been set for ", this);
        return 0;
    L19:
        return r02.intValue();
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureBufferLost(CameraCaptureSession r8, CaptureRequest r9, Surface r10, long r11) {
        Debug r82 = Debug.INSTANCE;
        Trace.beginSection("onCaptureBufferLost");
        long r2 = FrameNumber.m268constructorimpl(r11);
        StreamId r83 = m439getStreamIdLfjdq8s(r10);
        OutputId r112 = this.surfaceToOutputMap.get(r10);
        if (r83 == null) goto L21;
        if (r112 == null) goto L19;
        RequestMetadata r1 = readRequestMetadata(r9);
        CaptureSequences r92 = CaptureSequences.INSTANCE;
        Trace.beginSection("InvokeInternalListeners");
        int r102 = getListeners().size();
        int r12 = 0;
        int r0 = 0;
    L6:
        if (r0 >= r102) goto L8;
        getListeners().get(r0).m363onBufferLostDlC0U5Y(r1, r2, r83.m407unboximpl());
        r0 = r0 + 1;
        goto L6
    L8:
        Debug r103 = Debug.INSTANCE;
        Trace.endSection();
        Trace.beginSection("InvokeRequestListeners");
        int r02 = r1.getRequest().getListeners().size();
        int r4 = 0;
    L9:
        if (r4 >= r02) goto L11;
        r1.getRequest().getListeners().get(r4).m363onBufferLostDlC0U5Y(r1, r2, r83.m407unboximpl());
        r4 = r4 + 1;
        goto L9
    L11:
        Debug r03 = Debug.INSTANCE;
        Trace.endSection();
        CaptureSequences r04 = CaptureSequences.INSTANCE;
        Trace.beginSection("InvokeInternalListeners");
        int r93 = getListeners().size();
        int r6 = 0;
    L12:
        if (r6 >= r93) goto L14;
        getListeners().get(r6).mo48onBufferLostiiEMlm4(r1, r2, r83.m407unboximpl(), r112.m296unboximpl());
        r6 = r6 + 1;
        goto L12
    L14:
        Debug r7 = Debug.INSTANCE;
        Trace.endSection();
        Trace.beginSection("InvokeRequestListeners");
        int r72 = r1.getRequest().getListeners().size();
    L15:
        if (r12 >= r72) goto L17;
        r1.getRequest().getListeners().get(r12).mo48onBufferLostiiEMlm4(r1, r2, r83.m407unboximpl(), r112.m296unboximpl());
        r12 = r12 + 1;
        goto L15
    L17:
        Debug r73 = Debug.INSTANCE;
        Trace.endSection();
        Trace.endSection();
        return;
    L19:
        StringBuilder r74 = new StringBuilder("Unable to find the outputId for ");
        r74.append(r10);
        SurfaceGraph$$ExternalSyntheticBUOutline0.m(r74, " on ", FrameNumber.m272toStringimpl(r2));
        return;
    L21:
        StringBuilder r75 = new StringBuilder("Unable to find the streamId for ");
        r75.append(r10);
        SurfaceGraph$$ExternalSyntheticBUOutline0.m(r75, " on ", FrameNumber.m272toStringimpl(r2));
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureCompleted(CameraCaptureSession r3, CaptureRequest r4, TotalCaptureResult r5) {
        mo437onCaptureCompletedrmrZIYk(r4, r5, FrameNumber.m268constructorimpl(r5.getFrameNumber()));
    }

    @Override // androidx.camera.camera2.pipe.compat.Camera2CaptureCallback
    /* renamed from: onCaptureCompleted-rmrZIYk */
    public void mo437onCaptureCompletedrmrZIYk(CaptureRequest r7, TotalCaptureResult r8, long r9) {
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection("onCaptureCompleted");
        Trace.beginSection("onCaptureSequenceComplete");
        getSequenceListener().onCaptureSequenceComplete(this);
        Trace.endSection();
        RequestMetadata r72 = readRequestMetadata(r7);
        AndroidFrameInfo r02 = new AndroidFrameInfo(r8, m441getCameraIdDz_R5H8(), r72, null);
        Trace.beginSection("onTotalCaptureResult");
        CaptureSequences r82 = CaptureSequences.INSTANCE;
        Trace.beginSection("InvokeInternalListeners");
        int r1 = getListeners().size();
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        getListeners().get(r3).mo15onTotalCaptureResultCcXjc1I(r72, r9, r02);
        r3 = r3 + 1;
        goto L3
    L5:
        Debug r12 = Debug.INSTANCE;
        Trace.endSection();
        Trace.beginSection("InvokeRequestListeners");
        int r32 = r72.getRequest().getListeners().size();
        int r4 = 0;
    L6:
        if (r4 >= r32) goto L8;
        r72.getRequest().getListeners().get(r4).mo15onTotalCaptureResultCcXjc1I(r72, r9, r02);
        r4 = r4 + 1;
        goto L6
    L8:
        Debug r33 = Debug.INSTANCE;
        Trace.endSection();
        Trace.endSection();
        Trace.beginSection("onComplete");
        CaptureSequences r34 = CaptureSequences.INSTANCE;
        Trace.beginSection("InvokeInternalListeners");
        int r83 = getListeners().size();
        int r35 = 0;
    L9:
        if (r35 >= r83) goto L11;
        getListeners().get(r35).mo13onCompleteCcXjc1I(r72, r9, r02);
        r35 = r35 + 1;
        goto L9
    L11:
        Debug r6 = Debug.INSTANCE;
        Trace.endSection();
        Trace.beginSection("InvokeRequestListeners");
        int r62 = r72.getRequest().getListeners().size();
    L12:
        if (r2 >= r62) goto L14;
        r72.getRequest().getListeners().get(r2).mo13onCompleteCcXjc1I(r72, r9, r02);
        r2 = r2 + 1;
        goto L12
    L14:
        Debug r63 = Debug.INSTANCE;
        Trace.endSection();
        Trace.endSection();
        Trace.endSection();
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureFailed(CameraCaptureSession r3, CaptureRequest r4, CaptureFailure r5) {
        Debug r32 = Debug.INSTANCE;
        Trace.beginSection("onCaptureFailed");
        this.hasStarted.complete(Unit.INSTANCE);
        RequestMetadata r33 = readRequestMetadata(r4);
        AndroidCaptureFailure r42 = new AndroidCaptureFailure(r33, r5);
        m440invokeCaptureFailureCcXjc1I(r33, FrameNumber.m268constructorimpl(r5.getFrameNumber()), r42);
        Trace.endSection();
    }

    @Override // androidx.camera.camera2.pipe.compat.Camera2CaptureCallback
    /* renamed from: onCaptureFailed-RuT0dZU */
    public void mo438onCaptureFailedRuT0dZU(CaptureRequest r10, long r11) {
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection("onCaptureFailed");
        this.hasStarted.complete(Unit.INSTANCE);
        RequestMetadata r3 = readRequestMetadata(r10);
        m440invokeCaptureFailureCcXjc1I(r3, r11, new ExtensionRequestFailure(r3, false, r11, 0, null));
        Trace.endSection();
    }

    @Override // androidx.camera.camera2.pipe.compat.Camera2CaptureCallback
    public void onCaptureProcessProgressed(CaptureRequest r5, int r6) {
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection("onCaptureProcessProgressed");
        RequestMetadata r52 = readRequestMetadata(r5);
        CaptureSequences r02 = CaptureSequences.INSTANCE;
        Trace.beginSection("InvokeInternalListeners");
        int r03 = getListeners().size();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r2 >= r03) goto L5;
        getListeners().get(r2).onCaptureProgress(r52, r6);
        r2 = r2 + 1;
        goto L3
    L5:
        Debug r4 = Debug.INSTANCE;
        Trace.endSection();
        Trace.beginSection("InvokeRequestListeners");
        int r42 = r52.getRequest().getListeners().size();
    L6:
        if (r1 >= r42) goto L8;
        r52.getRequest().getListeners().get(r1).onCaptureProgress(r52, r6);
        r1 = r1 + 1;
        goto L6
    L8:
        Debug r43 = Debug.INSTANCE;
        Trace.endSection();
        Trace.endSection();
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureProgressed(CameraCaptureSession r1, CaptureRequest r2, CaptureResult r3) {
        onCaptureProgressed(r2, r3);
    }

    public void onCaptureProgressed(CaptureRequest r7, CaptureResult r8) {
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection("onCaptureProgressed");
        long r02 = FrameNumber.m268constructorimpl(r8.getFrameNumber());
        AndroidFrameMetadata r2 = new AndroidFrameMetadata(r8, m441getCameraIdDz_R5H8(), null);
        RequestMetadata r72 = readRequestMetadata(r7);
        CaptureSequences r82 = CaptureSequences.INSTANCE;
        Trace.beginSection("InvokeInternalListeners");
        int r83 = getListeners().size();
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r83) goto L5;
        getListeners().get(r4).mo49onPartialCaptureResultCcXjc1I(r72, r02, r2);
        r4 = r4 + 1;
        goto L3
    L5:
        Debug r6 = Debug.INSTANCE;
        Trace.endSection();
        Trace.beginSection("InvokeRequestListeners");
        int r62 = r72.getRequest().getListeners().size();
    L6:
        if (r3 >= r62) goto L8;
        r72.getRequest().getListeners().get(r3).mo49onPartialCaptureResultCcXjc1I(r72, r02, r2);
        r3 = r3 + 1;
        goto L6
    L8:
        Debug r63 = Debug.INSTANCE;
        Trace.endSection();
        Trace.endSection();
    }

    @Override // androidx.camera.camera2.pipe.compat.Camera2CaptureCallback
    public void onCaptureSequenceAborted(int r7) {
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection("onCaptureSequenceAborted");
        this.hasStarted.complete(Unit.INSTANCE);
        getSequenceListener().onCaptureSequenceComplete(this);
        StrictMode r02 = this.strictMode;
        if (getSequenceNumber() != r7) goto L5;
        boolean r1 = true;
    L6:
        if (r1 == true) goto L14;
        String r72 = "onCaptureSequenceAborted was invoked on " + getSequenceNumber() + ", but expected " + r7 + '!';
        if (r02.getEnabled() == false) goto L10;
        Segment$$ExternalSyntheticBUOutline1.m(r72);
        return;
    L10:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L14;
        android.util.Log.w("CXCP", r72);
    L14:
        CaptureSequences r73 = CaptureSequences.INSTANCE;
        Trace.beginSection("InvokeInternalListeners");
        int r74 = getCaptureMetadataList().size();
        int r03 = 0;
    L15:
        if (r03 >= r74) goto L20;
        RequestMetadata r12 = getCaptureMetadataList().get(r03);
        int r3 = getListeners().size();
        int r4 = 0;
    L17:
        if (r4 >= r3) goto L19;
        getListeners().get(r4).onRequestSequenceAborted(r12);
        r4 = r4 + 1;
        goto L17
    L19:
        r03 = r03 + 1;
        goto L15
    L20:
        Debug r75 = Debug.INSTANCE;
        Trace.endSection();
        Trace.beginSection("InvokeRequestListeners");
        int r76 = getCaptureMetadataList().size();
        int r04 = 0;
    L21:
        if (r04 >= r76) goto L26;
        RequestMetadata r13 = getCaptureMetadataList().get(r04);
        int r32 = r13.getRequest().getListeners().size();
        int r42 = 0;
    L23:
        if (r42 >= r32) goto L25;
        r13.getRequest().getListeners().get(r42).onRequestSequenceAborted(r13);
        r42 = r42 + 1;
        goto L23
    L25:
        r04 = r04 + 1;
        goto L21
    L26:
        Debug r6 = Debug.INSTANCE;
        Trace.endSection();
        Trace.endSection();
        return;
    L5:
        r1 = false;
        goto L6
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureSequenceAborted(CameraCaptureSession r1, int r2) {
        onCaptureSequenceAborted(r2);
    }

    @Override // androidx.camera.camera2.pipe.compat.Camera2CaptureCallback
    public void onCaptureSequenceCompleted(int r7, long r8) {
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection("onCaptureSequenceCompleted");
        this.hasStarted.complete(Unit.INSTANCE);
        getSequenceListener().onCaptureSequenceComplete(this);
        StrictMode r02 = this.strictMode;
        if (getSequenceNumber() != r7) goto L5;
        boolean r1 = true;
    L6:
        if (r1 == true) goto L14;
        String r72 = "onCaptureSequenceCompleted was invoked on " + getSequenceNumber() + ", but expected " + r7 + '!';
        if (r02.getEnabled() == false) goto L10;
        Segment$$ExternalSyntheticBUOutline1.m(r72);
        return;
    L10:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L14;
        android.util.Log.w("CXCP", r72);
    L14:
        long r73 = FrameNumber.m268constructorimpl(r8);
        CaptureSequences r9 = CaptureSequences.INSTANCE;
        Trace.beginSection("InvokeInternalListeners");
        int r92 = getCaptureMetadataList().size();
        int r03 = 0;
    L15:
        if (r03 >= r92) goto L20;
        RequestMetadata r12 = getCaptureMetadataList().get(r03);
        int r3 = getListeners().size();
        int r4 = 0;
    L17:
        if (r4 >= r3) goto L19;
        getListeners().get(r4).mo51onRequestSequenceCompletedRuT0dZU(r12, r73);
        r4 = r4 + 1;
        goto L17
    L19:
        r03 = r03 + 1;
        goto L15
    L20:
        Debug r93 = Debug.INSTANCE;
        Trace.endSection();
        Trace.beginSection("InvokeRequestListeners");
        int r94 = getCaptureMetadataList().size();
        int r04 = 0;
    L21:
        if (r04 >= r94) goto L26;
        RequestMetadata r13 = getCaptureMetadataList().get(r04);
        int r32 = r13.getRequest().getListeners().size();
        int r42 = 0;
    L23:
        if (r42 >= r32) goto L25;
        r13.getRequest().getListeners().get(r42).mo51onRequestSequenceCompletedRuT0dZU(r13, r73);
        r42 = r42 + 1;
        goto L23
    L25:
        r04 = r04 + 1;
        goto L21
    L26:
        Debug r6 = Debug.INSTANCE;
        Trace.endSection();
        Trace.endSection();
        return;
    L5:
        r1 = false;
        goto L6
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureSequenceCompleted(CameraCaptureSession r1, int r2, long r3) {
        onCaptureSequenceCompleted(r2, r3);
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureStarted(CameraCaptureSession r3, CaptureRequest r4, long r5, long r7) {
        onCaptureStarted(r4, r7, r5);
    }

    @Override // androidx.camera.camera2.pipe.compat.Camera2CaptureCallback
    public void onCaptureStarted(CaptureRequest r10, long r11, long r13) {
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection("onCaptureStarted");
        long r132 = CameraTimestamp.m242constructorimpl(r13);
        long r112 = FrameNumber.m268constructorimpl(r11);
        this.hasStarted.complete(Unit.INSTANCE);
        RequestMetadata r102 = readRequestMetadata(r10);
        CaptureSequences r02 = CaptureSequences.INSTANCE;
        Trace.beginSection("InvokeInternalListeners");
        int r03 = getListeners().size();
        int r7 = 0;
        int r8 = 0;
    L3:
        if (r8 >= r03) goto L5;
        getListeners().get(r8).mo52onStarteduGKBvU4(r102, r112, r132);
        r8 = r8 + 1;
        goto L3
    L5:
        Debug r9 = Debug.INSTANCE;
        Trace.endSection();
        Trace.beginSection("InvokeRequestListeners");
        int r04 = r102.getRequest().getListeners().size();
    L6:
        if (r7 >= r04) goto L8;
        r102.getRequest().getListeners().get(r7).mo52onStarteduGKBvU4(r102, r112, r132);
        r7 = r7 + 1;
        goto L6
    L8:
        Debug r92 = Debug.INSTANCE;
        Trace.endSection();
        Trace.endSection();
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onReadoutStarted(CameraCaptureSession r7, CaptureRequest r8, long r9, long r11) {
        Debug r72 = Debug.INSTANCE;
        Trace.beginSection("onReadoutStarted");
        long r4 = SensorTimestamp.m388constructorimpl(r9);
        long r2 = FrameNumber.m268constructorimpl(r11);
        RequestMetadata r1 = readRequestMetadata(r8);
        CaptureSequences r73 = CaptureSequences.INSTANCE;
        Trace.beginSection("InvokeInternalListeners");
        int r74 = getListeners().size();
        int r82 = 0;
        int r92 = 0;
    L3:
        if (r92 >= r74) goto L5;
        getListeners().get(r92).mo50onReadoutStartedmP9r9w(r1, r2, r4);
        r92 = r92 + 1;
        goto L3
    L5:
        Debug r6 = Debug.INSTANCE;
        Trace.endSection();
        Trace.beginSection("InvokeRequestListeners");
        int r62 = r1.getRequest().getListeners().size();
    L6:
        if (r82 >= r62) goto L8;
        r1.getRequest().getListeners().get(r82).mo50onReadoutStartedmP9r9w(r1, r2, r4);
        r82 = r82 + 1;
        goto L6
    L8:
        Debug r63 = Debug.INSTANCE;
        Trace.endSection();
        Trace.endSection();
    }

    @Override // androidx.camera.camera2.pipe.CaptureSequence
    public void setSequenceNumber(int r1) {
        this._sequenceNumber = Integer.valueOf(r1);
    }

    public String toString() {
        return "Camera2CaptureSequence-" + this.debugId;
    }
}
