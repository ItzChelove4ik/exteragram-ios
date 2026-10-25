package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Trace;
import android.util.ArrayMap;
import android.view.Surface;
import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0;
import androidx.camera.camera2.pipe.CameraStream;
import androidx.camera.camera2.pipe.CaptureSequence;
import androidx.camera.camera2.pipe.CaptureSequenceProcessor;
import androidx.camera.camera2.pipe.InputStream;
import androidx.camera.camera2.pipe.OutputId;
import androidx.camera.camera2.pipe.OutputStream;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestTemplate;
import androidx.camera.camera2.pipe.RequestsKt;
import androidx.camera.camera2.pipe.StreamFormat;
import androidx.camera.camera2.pipe.StreamGraph;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.camera2.pipe.StrictMode;
import androidx.camera.camera2.pipe.core.Debug;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.media.AndroidImageWriter;
import androidx.camera.camera2.pipe.media.ImageWrapper;
import androidx.camera.camera2.pipe.media.ImageWriterWrapper;
import androidx.lifecycle.LifecycleRegistry$$ExternalSyntheticBUOutline0;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 E2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001EBa\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\r0\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017Jr\u0010\u001f\u001a\u0004\u0018\u00010\u00032\u0006\u0010 \u001a\u00020\u00152\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0012\u0010$\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u000b2\u0012\u0010%\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u000b2\u0012\u0010&\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u000b2\u0006\u0010'\u001a\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\"H\u0016J\u0017\u0010+\u001a\u0004\u0018\u00010\u001a2\u0006\u0010,\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010-J\b\u0010.\u001a\u00020/H\u0016J\b\u00100\u001a\u00020/H\u0016J\u000e\u00101\u001a\u00020/H\u0096@¢\u0006\u0002\u00102J\r\u00103\u001a\u00020/H\u0000¢\u0006\u0002\b4J\b\u00105\u001a\u000206H\u0016J\u0010\u00107\u001a\u00020/2\u0006\u0010,\u001a\u00020\u0003H\u0002J\u001e\u0010:\u001a\u00020\u00152\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002JR\u0010;\u001a\u00020\u00152\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\f0=2\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000f0=2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0=H\u0002J!\u0010?\u001a\u0004\u0018\u00010@2\u0006\u0010A\u001a\u00020#2\u0006\u0010B\u001a\u00020\tH\u0002¢\u0006\u0004\bC\u0010DR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\r0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u001d\u001a\u00020\u00158\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u0004\u0018\u000109X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2CaptureSequenceProcessor;", "Landroidx/camera/camera2/pipe/CaptureSequenceProcessor;", "Landroid/hardware/camera2/CaptureRequest;", "Landroidx/camera/camera2/pipe/compat/Camera2CaptureSequence;", "session", "Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper;", "threads", "Landroidx/camera/camera2/pipe/core/Threads;", "template", "Landroidx/camera/camera2/pipe/RequestTemplate;", "streamToSurfaceMap", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "Landroid/view/Surface;", "outputToSurfaceMap", "Landroidx/camera/camera2/pipe/OutputId;", "streamGraph", "Landroidx/camera/camera2/pipe/StreamGraph;", "strictMode", "Landroidx/camera/camera2/pipe/StrictMode;", "awaitRepeatingRequestOnDisconnect", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper;Landroidx/camera/camera2/pipe/core/Threads;ILjava/util/Map;Ljava/util/Map;Landroidx/camera/camera2/pipe/StreamGraph;Landroidx/camera/camera2/pipe/StrictMode;ZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "I", "debugId", _UrlKt.FRAGMENT_ENCODE_SET, "lock", _UrlKt.FRAGMENT_ENCODE_SET, "disconnected", "lastSingleRepeatingRequestSequence", "build", "isRepeating", "requests", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/Request;", "defaultParameters", "graphParameters", "requiredParameters", "sequenceListener", "Landroidx/camera/camera2/pipe/CaptureSequence$CaptureSequenceListener;", "listeners", "Landroidx/camera/camera2/pipe/Request$Listener;", "submit", "captureSequence", "(Landroidx/camera/camera2/pipe/compat/Camera2CaptureSequence;)Ljava/lang/Integer;", "abortCaptures", _UrlKt.FRAGMENT_ENCODE_SET, "stopRepeating", "shutdown", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "disconnect", "disconnect$camera_camera2_pipe", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "awaitRepeatingRequestStarted", "imageWriter", "Landroidx/camera/camera2/pipe/media/ImageWriterWrapper;", "validateRequestList", "buildSurfaceMaps", "surfaceToStreamMap", _UrlKt.FRAGMENT_ENCODE_SET, "surfaceToOutputMap", "buildCaptureRequestBuilder", "Landroid/hardware/camera2/CaptureRequest$Builder;", PluginsConstants.REQUEST, "requestTemplate", "buildCaptureRequestBuilder-0UCm73U", "(Landroidx/camera/camera2/pipe/Request;I)Landroid/hardware/camera2/CaptureRequest$Builder;", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCamera2CaptureSequenceProcessor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2CaptureSequenceProcessor.kt\nandroidx/camera/camera2/pipe/compat/Camera2CaptureSequenceProcessor\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n*L\n1#1,667:1\n86#2,2:668\n50#2,2:670\n50#2,2:672\n82#2,2:674\n71#2,2:676\n50#2,2:678\n50#2,2:680\n71#2,2:688\n50#2,2:690\n50#2,2:692\n50#2,2:707\n82#2,2:709\n82#2,2:717\n82#2,2:725\n82#2,2:730\n59#2,2:732\n59#2,2:734\n59#2,2:736\n1761#3,2:682\n1761#3,3:684\n1763#3:687\n1761#3,2:711\n1761#3,3:713\n1763#3:716\n1761#3,2:719\n1761#3,3:721\n1763#3:724\n1740#3,3:727\n48#4,2:694\n71#4,4:696\n50#4,3:700\n78#4,4:703\n*S KotlinDebug\n*F\n+ 1 Camera2CaptureSequenceProcessor.kt\nandroidx/camera/camera2/pipe/compat/Camera2CaptureSequenceProcessor\n*L\n424#1:668,2\n431#1:670,2\n145#1:672,2\n172#1:674,2\n180#1:676,2\n184#1:678,2\n187#1:680,2\n316#1:688,2\n347#1:690,2\n353#1:692,2\n387#1:707,2\n397#1:709,2\n470#1:717,2\n492#1:725,2\n507#1:730,2\n564#1:732,2\n601#1:734,2\n606#1:736,2\n229#1:682,2\n230#1:684,3\n229#1:687\n459#1:711,2\n460#1:713,3\n459#1:716\n482#1:719,2\n483#1:721,3\n482#1:724\n504#1:727,3\n363#1:694,2\n363#1:696,4\n363#1:700,3\n363#1:703,4\n*E\n"})
/* loaded from: classes3.dex */
public final class Camera2CaptureSequenceProcessor implements CaptureSequenceProcessor<CaptureRequest, Camera2CaptureSequence> {
    public static final Companion Companion = null;
    private final boolean awaitRepeatingRequestOnDisconnect;
    private final int debugId;
    private boolean disconnected;
    private final ImageWriterWrapper imageWriter;
    private Camera2CaptureSequence lastSingleRepeatingRequestSequence;
    private final Object lock;
    private final Map<OutputId, Surface> outputToSurfaceMap;
    private final CameraCaptureSessionWrapper session;
    private final StreamGraph streamGraph;
    private final Map<StreamId, Surface> streamToSurfaceMap;
    private final StrictMode strictMode;
    private final int template;
    private final Threads threads;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2CaptureSequenceProcessor$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "WAIT_FOR_REPEATING_TIMEOUT_MS", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.Camera2CaptureSequenceProcessor$awaitRepeatingRequestStarted$2", f = "Camera2CaptureSequenceProcessor.kt", i = {}, l = {395}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.compat.Camera2CaptureSequenceProcessor$awaitRepeatingRequestStarted$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Camera2CaptureSequence $captureSequence;
        int label;

        public AnonymousClass2(Camera2CaptureSequence r1, Continuation<? super AnonymousClass2> r2) {
            this.$captureSequence = r1;
            super(1, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> r2) {
            return new AnonymousClass2(this.$captureSequence, r2);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Unit> r1) {
            return invoke2(r1);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Continuation<? super Unit> r1) {
            return ((AnonymousClass2) create(r1)).invokeSuspend(Unit.INSTANCE);
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
            Camera2CaptureSequence r42 = this.$captureSequence;
            this.label = 1;
            if (r42.awaitStarted$camera_camera2_pipe(this) != r0) goto L12;
            return r0;
        }
    }

    static {
        Companion = new Companion(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Camera2CaptureSequenceProcessor(CameraCaptureSessionWrapper r9, Threads r10, int r11, Map<StreamId, ? extends Surface> r12, Map<OutputId, ? extends Surface> r13, StreamGraph r14, StrictMode r15, boolean r16) {
        this.session = r9;
        this.threads = r10;
        this.template = r11;
        this.streamToSurfaceMap = r12;
        this.outputToSurfaceMap = r13;
        this.streamGraph = r14;
        this.strictMode = r15;
        this.awaitRepeatingRequestOnDisconnect = r16;
        this.debugId = Camera2CaptureSequenceProcessorKt.getCaptureSequenceProcessorDebugIds().incrementAndGet();
        this.lock = new Object();
        ImageWriterWrapper r122 = null;
        if (r14.getInputs().isEmpty() == true) goto L18;
        InputStream r112 = (InputStream) CollectionsKt.first(r14.getInputs());
        Surface r3 = r9.getInputSurface();
        if (r3 != null) goto L20;
        Segment$$ExternalSyntheticBUOutline1.m("inputSurface is required to create instance of imageWriter.");
        throw null;
    L20:
        r122 = AndroidImageWriter.Companion.m580createU86x6Zg(r3, r112.mo276getIdm1bwn9M(), r112.getMaxImages(), StreamFormat.m389boximpl(r112.mo275getFormat8FPWQzE()), r10.getCamera2Handler());     // Catch: RuntimeException -> L8
    L12:
        if (r122 == null) goto L18;
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L18;
        android.util.Log.d("CXCP", "Created ImageWriter " + r122 + " for session " + access$getSession$p(this));
    L8:
        e = move-exception;
        if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L12;
        android.util.Log.e("CXCP", "Failed to create ImageWriter for session " + access$getSession$p(this) + "! Reprocessing will not be supported!", e);
    L18:
        this.imageWriter = r122;
    }

    public /* synthetic */ Camera2CaptureSequenceProcessor(CameraCaptureSessionWrapper r1, Threads r2, int r3, Map r4, Map r5, StreamGraph r6, StrictMode r7, boolean r8, DefaultConstructorMarker r9) {
        this(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static final /* synthetic */ void access$awaitRepeatingRequestStarted(Camera2CaptureSequenceProcessor r0, Camera2CaptureSequence r1) {
        r0.awaitRepeatingRequestStarted(r1);
    }

    public static final /* synthetic */ boolean access$getAwaitRepeatingRequestOnDisconnect$p(Camera2CaptureSequenceProcessor r0) {
        return r0.awaitRepeatingRequestOnDisconnect;
    }

    public static final /* synthetic */ boolean access$getDisconnected$p(Camera2CaptureSequenceProcessor r0) {
        return r0.disconnected;
    }

    public static final /* synthetic */ ImageWriterWrapper access$getImageWriter$p(Camera2CaptureSequenceProcessor r0) {
        return r0.imageWriter;
    }

    public static final /* synthetic */ Camera2CaptureSequence access$getLastSingleRepeatingRequestSequence$p(Camera2CaptureSequenceProcessor r0) {
        return r0.lastSingleRepeatingRequestSequence;
    }

    public static final /* synthetic */ Object access$getLock$p(Camera2CaptureSequenceProcessor r0) {
        return r0.lock;
    }

    public static final /* synthetic */ CameraCaptureSessionWrapper access$getSession$p(Camera2CaptureSequenceProcessor r0) {
        return r0.session;
    }

    public static final /* synthetic */ StreamGraph access$getStreamGraph$p(Camera2CaptureSequenceProcessor r0) {
        return r0.streamGraph;
    }

    public static final /* synthetic */ void access$setDisconnected$p(Camera2CaptureSequenceProcessor r0, boolean r1) {
        r0.disconnected = r1;
    }

    private final void awaitRepeatingRequestStarted(Camera2CaptureSequence r7) {
        Log r0 = Log.INSTANCE;
        if (r0.getDEBUG_LOGGABLE() == false) goto L6;
        android.util.Log.d("CXCP", "Waiting for the last repeating request sequence: " + r7);
    L6:
        if (((Unit) this.threads.runBlockingCheckedOrNull(2000, new AnonymousClass2(r7, null))) == null) goto L8;
        return;
    L8:
        if (r0.getERROR_LOGGABLE() == false) goto L12;
        android.util.Log.e("CXCP", this + "#close: awaitStarted on last repeating request timed out, lastSingleRepeatingRequestSequence = " + r7);
        return;
    }

    /* renamed from: buildCaptureRequestBuilder-0UCm73U, reason: not valid java name */
    private final CaptureRequest.Builder m442buildCaptureRequestBuilder0UCm73U(Request r4, int r5) {
        if (r4.getInputRequest() == null) goto L9;
        TotalCaptureResult r0 = (TotalCaptureResult) r4.getInputRequest().getFrameInfo().unwrapAs(Reflection.getOrCreateKotlinClass(TotalCaptureResult.class));
        if (r0 == null) goto L7;
        CaptureRequest.Builder r3 = this.session.getDevice().createReprocessCaptureRequest(r0);
    L10:
        if (r3 == null) goto L12;
        return r3;
    L12:
        if (r4.getInputRequest() == null) goto L17;
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L19;
        android.util.Log.i("CXCP", "Failed to create a ReprocessingCaptureRequest.Builder from " + r4.getInputRequest().getFrameInfo() + '!');
    L19:
        return null;
    L17:
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L19;
        android.util.Log.i("CXCP", "Failed to create a CaptureRequest.Builder from " + RequestTemplate.m376toStringimpl(r5) + '!');
        goto L19
    L7:
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("Failed to unwrap FrameInfo ", r4.getInputRequest().getFrameInfo(), " as TotalCaptureResult");
        return null;
    L9:
        r3 = this.session.getDevice().mo411createCaptureRequest2PPcXtw(r5);
        goto L10
    }

    private final boolean buildSurfaceMaps(List<Request> r10, Map<Surface, StreamId> r11, Map<Surface, OutputId> r12, Map<StreamId, Surface> r13) {
        if (r10.isEmpty() == true) goto L37;
        Iterator<Request> r102 = r10.iterator();
    L6:
        if (r102.hasNext() == false) goto L36;
        Request r0 = r102.next();
        Iterator<StreamId> r3 = r0.getStreams().iterator();
        boolean r4 = false;
    L9:
        if (r3.hasNext() == false) goto L27;
        int r5 = r3.next().m407unboximpl();
        if (r13.containsKey(StreamId.m401boximpl(r5)) == true) goto L12;
        Surface r6 = this.streamToSurfaceMap.get(StreamId.m401boximpl(r5));
        if (r6 == null) goto L9;
        r11.put(r6, StreamId.m401boximpl(r5));
        r13.put(StreamId.m401boximpl(r5), r6);
        CameraStream r42 = this.streamGraph.m399getaKI5c8E(r5);
        if (r42 == null) goto L25;
        Iterator<OutputStream> r43 = r42.getOutputs().iterator();
    L19:
        if (r43.hasNext() == false) goto L12;
        OutputStream r62 = r43.next();
        Surface r7 = this.outputToSurfaceMap.get(OutputId.m290boximpl(r62.mo311getId4LaLFng()));
        if (r7 == null) goto L23;
        r12.put(r7, OutputId.m290boximpl(r62.mo311getId4LaLFng()));
        goto L19
    L23:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return false;
    L25:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return false;
    L12:
        r4 = true;
        goto L9
    L27:
        if (r4 == false) goto L29;
        if (r4 == true) goto L6;
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return false;
    L29:
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L31;
        android.util.Log.i("CXCP", "  Failed to bind any surfaces for " + r0 + '!');
    L31:
        return false;
    L36:
        return true;
    L37:
        Segment$$ExternalSyntheticBUOutline1.m("build(...) should never be called with an empty request list!");
        return false;
    }

    private final boolean validateRequestList(List<Request> r14, CameraCaptureSessionWrapper r15) {
        if (r14.isEmpty() == false) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("build(...) should never be called with an empty request list!");
        return false;
    L5:
        if ((r15 instanceof CameraConstrainedHighSpeedCaptureSessionWrapper) == false) goto L108;
        Iterator<Request> r142 = r14.iterator();
        Boolean r152 = null;
        Boolean r2 = null;
    L8:
        if (r142.hasNext() == false) goto L108;
        Request r3 = r142.next();
        List<StreamId> r4 = r3.getStreams();
        if ((r4 instanceof Collection) == true) goto L12;
    L14:
        Iterator<T> r42 = r4.iterator();
    L16:
        if (r42.hasNext() == false) goto L13;
        ((StreamId) r42.next()).m407unboximpl();
        List<OutputStream> r5 = this.streamGraph.getOutputs();
        if ((r5 instanceof Collection) == true) goto L20;
    L22:
        Iterator<T> r52 = r5.iterator();
    L24:
        if (r52.hasNext() == false) goto L21;
        OutputStream r6 = (OutputStream) r52.next();
        OutputStream.StreamUseCase r7 = r6.mo313getStreamUseCase8x2ez34();
        long r8 = OutputStream.StreamUseCase.Companion.m349getPREVIEWvrKr8v8();
        if (r7 != null) goto L28;
        boolean r72 = false;
    L29:
        if (r72 == true) goto L39;
        OutputStream.StreamUseHint r73 = r6.mo314getStreamUseHintHIPxoCc();
        long r82 = OutputStream.StreamUseHint.Companion.m358getDEFAULT4VYZOf8();
        if (r73 != null) goto L33;
        boolean r74 = false;
    L34:
        if (r74 == true) goto L39;
        if (r6.mo314getStreamUseHintHIPxoCc() == null) goto L39;
        boolean r62 = false;
    L40:
        if (r62 == false) goto L24;
        boolean r53 = true;
    L42:
        if (r53 == false) goto L16;
        boolean r43 = true;
    L44:
        Boolean r54 = Boolean.valueOf(r43);
        if (r152 != null) goto L47;
    L51:
        List<StreamId> r153 = r3.getStreams();
        if ((r153 instanceof Collection) == true) goto L54;
    L56:
        Iterator<T> r154 = r153.iterator();
    L58:
        if (r154.hasNext() == false) goto L55;
        ((StreamId) r154.next()).m407unboximpl();
        List<OutputStream> r32 = this.streamGraph.getOutputs();
        if ((r32 instanceof Collection) == true) goto L62;
    L64:
        Iterator<T> r33 = r32.iterator();
    L66:
        if (r33.hasNext() == false) goto L63;
        OutputStream r44 = (OutputStream) r33.next();
        OutputStream.StreamUseCase r83 = r44.mo313getStreamUseCase8x2ez34();
        long r9 = OutputStream.StreamUseCase.Companion.m350getVIDEO_RECORDvrKr8v8();
        if (r83 != null) goto L70;
        boolean r84 = false;
    L71:
        if (r84 == true) goto L79;
        OutputStream.StreamUseHint r45 = r44.mo314getStreamUseHintHIPxoCc();
        long r85 = OutputStream.StreamUseHint.Companion.m359getVIDEO_RECORD4VYZOf8();
        if (r45 != null) goto L75;
        boolean r46 = false;
    L76:
        if (r46 == true) goto L79;
        boolean r47 = false;
    L80:
        if (r47 == false) goto L66;
        boolean r34 = true;
    L82:
        if (r34 == false) goto L58;
        boolean r155 = true;
    L84:
        Boolean r35 = Boolean.valueOf(r155);
        if (r2 != null) goto L87;
    L91:
        List<OutputStream> r156 = this.streamGraph.getOutputs();
        if ((r156 instanceof Collection) == true) goto L94;
    L96:
        Iterator<T> r157 = r156.iterator();
    L98:
        if (r157.hasNext() == false) goto L95;
        if (((OutputStream) r157.next()).isValidForHighSpeedOperatingMode() == true) goto L98;
        boolean r158 = false;
    L102:
        if (r158 == false) goto L104;
        r2 = r35;
        r152 = r54;
        goto L8
    L104:
        if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L106;
        android.util.Log.e("CXCP", "HIGH_SPEED CameraGraph must only contain Preview and/or Video streams. Configured outputs are " + access$getStreamGraph$p(this).getOutputs());
    L106:
        return false;
    L95:
        r158 = true;
        goto L102
    L94:
        if (r156.isEmpty() == false) goto L96;
    L87:
        if (Intrinsics.areEqual(r2, r35) == true) goto L91;
        if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L91;
        android.util.Log.e("CXCP", "The previous high speed request and the current high speed request do not have the same video stream use case. Previous request contains video stream use case: " + r2.booleanValue() + ". Current request contains video stream use case: " + r155 + '.');
        goto L91
    L75:
        r46 = OutputStream.StreamUseHint.m354equalsimpl0(r45.m357unboximpl(), r85);
    L79:
        r47 = true;
        goto L80
    L70:
        r84 = OutputStream.StreamUseCase.m344equalsimpl0(r83.m347unboximpl(), r9);
    L63:
        r34 = false;
        goto L82
    L62:
        if (r32.isEmpty() == false) goto L64;
    L55:
        r155 = false;
        goto L84
    L54:
        if (r153.isEmpty() == false) goto L56;
    L47:
        if (Intrinsics.areEqual(r152, r54) == true) goto L51;
        if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L51;
        android.util.Log.e("CXCP", "The previous high speed request and the current high speed request must both have a preview stream use case or hint. Previous request contains preview stream use case or hint: " + r152.booleanValue() + ". Current request contains preview stream use case or hint: " + r43 + '.');
        goto L51
    L33:
        r74 = OutputStream.StreamUseHint.m354equalsimpl0(r73.m357unboximpl(), r82);
    L39:
        r62 = true;
        goto L40
    L28:
        r72 = OutputStream.StreamUseCase.m344equalsimpl0(r7.m347unboximpl(), r8);
    L21:
        r53 = false;
        goto L42
    L20:
        if (r5.isEmpty() == false) goto L22;
    L13:
        r43 = false;
        goto L44
    L12:
        if (r4.isEmpty() == false) goto L14;
    L108:
        return true;
    }

    @Override // androidx.camera.camera2.pipe.CaptureSequenceProcessor
    public void abortCaptures() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L9;
        android.util.Log.d("CXCP", this + "#abortCaptures");     // Catch: Throwable -> L7
    L9:
        this.session.abortCaptures();     // Catch: Throwable -> L7
        Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
    }

    @Override // androidx.camera.camera2.pipe.CaptureSequenceProcessor
    public /* bridge */ /* synthetic */ CaptureSequence build(boolean r1, List r2, Map r3, Map r4, Map r5, CaptureSequence.CaptureSequenceListener r6, List r7) {
        return build(r1, r2, r3, r4, r5, r6, r7);
    }

    @Override // androidx.camera.camera2.pipe.CaptureSequenceProcessor
    public Camera2CaptureSequence build(boolean r26, List<Request> r27, Map<?, ? extends Object> r28, Map<?, ? extends Object> r29, Map<?, ? extends Object> r30, CaptureSequence.CaptureSequenceListener r31, List<? extends Request.Listener> r32) {
        Camera2CaptureSequenceProcessor r0 = this;
        Map<?, ? extends Object> r4 = r28;
        Map<?, ? extends Object> r6 = r30;
        ArrayList r14 = new ArrayList(r27.size());
        ArrayList r15 = new ArrayList(r27.size());
        ArrayMap r2 = new ArrayMap();
        ArrayMap r3 = new ArrayMap();
        ArrayMap r7 = new ArrayMap();
        if (r0.validateRequestList(r27, r0.session) == true) goto L6;
        return null;
    L6:
        if (r0.buildSurfaceMaps(r27, r2, r3, r7) == true) goto L8;
        return null;
    L8:
        Iterator<Request> r17 = r27.iterator();
    L10:
        if (r17.hasNext() == false) goto L117;
        Request r10 = r17.next();
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L14;
        android.util.Log.d("CXCP", "Building CaptureRequest for " + r10);
    L14:
        RequestTemplate r1 = r10.m362getTemplateejQnlcg();
        if (r1 == null) goto L18;
        int r12 = r1.m377unboximpl();
    L17:
        int r8 = r12;
        CaptureRequest.Builder r13 = r0.m442buildCaptureRequestBuilder0UCm73U(r10, r8);
        if (r13 == null) goto L21;
        CameraPipeKeys r5 = CameraPipeKeys.INSTANCE;
        Object r9 = r6.get(r5.getCamera2CaptureRequestTag());
        if (r9 != null) goto L25;
        r9 = r4.get(r5.getCamera2CaptureRequestTag());
    L25:
        r13.setTag(r9);
        int r52 = r10.getStreams().size();
        int r11 = 0;
        boolean r122 = false;
    L26:
        if (r11 >= r52) goto L31;
        Surface r132 = (Surface) r7.get(r10.getStreams().get(r11));
        if (r132 == null) goto L30;
        r13.addTarget(r132);
        r122 = true;
    L30:
        r11 = r11 + 1;
        goto L26
    L31:
        if (r122 == false) goto L115;
        if (r10.getInputRequest() != null) goto L35;
        RequestsKt.writeParameters(r13, r4);
        RequestsKt.writeParameters(r13, r29);
        RequestsKt.writeParameters(r13, r10.getParameters());
        RequestsKt.writeParameters(r13, r6);
    L67:
        long r112 = Camera2CaptureSequenceProcessorKt.nextRequestNumber();
        CaptureRequest r16 = r13.build();
        ArrayMap r92 = r2;
        CameraCaptureSessionWrapper r22 = r0.session;
        if ((r22 instanceof CameraConstrainedHighSpeedCaptureSessionWrapper) == true) goto L69;
        ArrayList r19 = r14;
        r4 = r28;
        r6 = r30;
        Camera2RequestMetadata r18 = new Camera2RequestMetadata(r22, r16, r4, r29, r6, r7, r8, r26, r10, r112, null);
        r15.add(r16);
        r19.add(r18);
        r14 = r19;
        r2 = r92;
        r3 = r3;
        r0 = this;
        goto L10
    L69:
        List<CaptureRequest> r110 = ((CameraConstrainedHighSpeedCaptureSessionWrapper) r22).createHighSpeedRequestList(r16);
        if (r110 == null) goto L71;
        List<StreamId> r23 = r10.getStreams();
        if ((r23 instanceof Collection) == true) goto L75;
    L77:
        Iterator r24 = r23.iterator();
    L79:
        if (r24.hasNext() == false) goto L76;
        ((StreamId) r24.next()).m407unboximpl();
        List<OutputStream> r133 = r0.streamGraph.getOutputs();
        Iterator r192 = r24;
        if ((r133 instanceof Collection) == true) goto L83;
    L85:
        Iterator r25 = r133.iterator();
    L87:
        if (r25.hasNext() == false) goto L84;
        OutputStream r134 = (OutputStream) r25.next();
        OutputStream.StreamUseCase r20 = r134.mo313getStreamUseCase8x2ez34();
        Iterator r232 = r25;
        ArrayMap r222 = r3;
        long r210 = OutputStream.StreamUseCase.Companion.m350getVIDEO_RECORDvrKr8v8();
        if (r20 != null) goto L91;
        boolean r211 = false;
    L92:
        if (r211 == true) goto L100;
        OutputStream.StreamUseHint r212 = r134.mo314getStreamUseHintHIPxoCc();
        long r33 = OutputStream.StreamUseHint.Companion.m359getVIDEO_RECORD4VYZOf8();
        if (r212 != null) goto L96;
        boolean r213 = false;
    L97:
        if (r213 == true) goto L100;
        boolean r214 = false;
    L101:
        if (r214 == true) goto L102;
        r3 = r222;
        r25 = r232;
        goto L87
    L102:
        boolean r215 = true;
    L104:
        if (r215 == true) goto L105;
        r24 = r192;
        r3 = r222;
        goto L79
    L105:
        boolean r135 = true;
    L107:
        if (r135 == true) goto L110;
        r6 = r30;
        ArrayMap r182 = r92;
        r4 = r28;
        Camera2RequestMetadata r216 = new Camera2RequestMetadata(r0.session, r110.get(0), r4, r29, r6, r7, r8, r26, r10, r112, null);
        r15.add(r110.get(0));
        ArrayList r217 = r14;
        r217.add(r216);
        r0 = this;
    L109:
        r14 = r217;
        r2 = r182;
        r3 = r222;
        goto L10
    L110:
        r182 = r92;
        r217 = r14;
        int r02 = 0;
        int r111 = r110.size();
    L111:
        if (r02 >= r111) goto L113;
        int r34 = r111;
        ArrayList r193 = r217;
        Camera2RequestMetadata r113 = new Camera2RequestMetadata(this.session, r110.get(r02), r28, r29, r30, r7, r8, r26, r10, r112, null);
        r15.add(r110.get(r02));
        r193.add(r113);
        r02 = r02 + 1;
        r217 = r193;
        r111 = r34;
        goto L111
    L113:
        r0 = this;
        r4 = r28;
        r6 = r30;
        goto L109
    L96:
        r213 = OutputStream.StreamUseHint.m354equalsimpl0(r212.m357unboximpl(), r33);
    L100:
        r214 = true;
        goto L101
    L91:
        r211 = OutputStream.StreamUseCase.m344equalsimpl0(r20.m347unboximpl(), r210);
    L84:
        r222 = r3;
        r215 = false;
        goto L104
    L83:
        if (r133.isEmpty() == false) goto L85;
    L76:
        r222 = r3;
        r135 = false;
        goto L107
    L75:
        if (r23.isEmpty() == false) goto L77;
    L71:
        return null;
    L35:
        if (r0.imageWriter == null) goto L37;
        ImageWrapper r53 = r10.getInputRequest().getImage();
        Object r114 = r0.lock;
        monitor-enter(r114);
    L48:
        th = move-exception;
        throw th;
    L43:
        if (r0.disconnected == true) goto L45;
        Unit r123 = Unit.INSTANCE;     // Catch: Throwable -> L48
        monitor-exit(r114);
        Log r115 = Log.INSTANCE;
        if (r115.getDEBUG_LOGGABLE() == false) goto L58;
        android.util.Log.d("CXCP", "Queuing image " + r53 + " for reprocessing to ImageWriter " + access$getImageWriter$p(r0));
    L58:
        if (r0.imageWriter.queueInputImage(r53) == false) goto L60;
        RequestsKt.writeParameters(r13, r10.getParameters());
        goto L67
    L60:
        if (r115.getDEBUG_LOGGABLE() == false) goto L62;
        android.util.Log.d("CXCP", "Failed to queue image " + r53 + " for reprocessing to ImageWriter " + access$getImageWriter$p(r0));
    L62:
        return null;
    L45:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L50;
        android.util.Log.w("CXCP", r0 + " disconnected. " + r53 + " can't be queued to " + access$getImageWriter$p(r0));     // Catch: Throwable -> L48
    L50:
        monitor-exit(r114);
        return null;
    L37:
        if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L39;
        android.util.Log.e("CXCP", "Failed to queue request to ImageWriter - No ImageWriter available!");
    L39:
        return null;
    L115:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return null;
    L21:
        return null;
    L18:
        r12 = r0.template;
        goto L17
    L117:
        ArrayList r194 = r14;
        return new Camera2CaptureSequence(this.session.getDevice().mo412getCameraIdDz_R5H8(), r26, r15, r194, r32, r31, r2, r3, this.streamGraph, this.strictMode, null);
    }

    public final void disconnect$camera_camera2_pipe() {
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection(this + "#disconnect");     // Catch: Throwable -> L28
        Object r02 = access$getLock$p(this);     // Catch: Throwable -> L28
        monitor-enter(r02);     // Catch: Throwable -> L28
    L10:
        th = move-exception;
        throw th;     // Catch: Throwable -> L28
    L6:
        if (access$getDisconnected$p(this) == true) goto L17;
        access$setDisconnected$p(this, true);     // Catch: Throwable -> L10
        ImageWriterWrapper r1 = access$getImageWriter$p(this);     // Catch: Throwable -> L10
        if (r1 == null) goto L12;
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(r1);     // Catch: Throwable -> L10
    L12:
        Surface r12 = access$getSession$p(this).getInputSurface();     // Catch: Throwable -> L10
        if (r12 == null) goto L15;
        r12.release();     // Catch: Throwable -> L10
    L15:
        Camera2CaptureSequence r13 = access$getLastSingleRepeatingRequestSequence$p(this);     // Catch: Throwable -> L10
    L18:
        monitor-exit(r02);     // Catch: Throwable -> L28
        if (access$getAwaitRepeatingRequestOnDisconnect$p(this) == false) goto L23;
        if (r13 == null) goto L23;
        access$awaitRepeatingRequestStarted(this, r13);     // Catch: Throwable -> L28
    L23:
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L28
        Trace.endSection();
        return;
    L17:
        r13 = null;
    L28:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    @Override // androidx.camera.camera2.pipe.CaptureSequenceProcessor
    public Object shutdown(Continuation<? super Unit> r1) {
        disconnect$camera_camera2_pipe();
        return Unit.INSTANCE;
    }

    @Override // androidx.camera.camera2.pipe.CaptureSequenceProcessor
    public void stopRepeating() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L9;
        android.util.Log.d("CXCP", this + "#stopRepeating");     // Catch: Throwable -> L7
    L9:
        this.session.stopRepeating();     // Catch: Throwable -> L7
        Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
    }

    @Override // androidx.camera.camera2.pipe.CaptureSequenceProcessor
    public /* bridge */ /* synthetic */ Integer submit(CaptureSequence r1) {
        return submit((Camera2CaptureSequence) r1);
    }

    public Integer submit(Camera2CaptureSequence r4) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (this.disconnected == false) goto L16;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L12;
        android.util.Log.w("CXCP", this + " disconnected. " + r4 + " won't be submitted");     // Catch: Throwable -> L10
    L12:
        monitor-exit(r0);
        return null;
    L16:
        if (r4.getCaptureRequestList().size() == 1) goto L18;
    L26:
        boolean r1 = r4.getRepeating();     // Catch: Throwable -> L10
        CameraCaptureSessionWrapper r3 = this.session;
        if (r1 == false) goto L30;
        Integer r32 = r3.setRepeatingBurst(r4.getCaptureRequestList(), r4);     // Catch: Throwable -> L10
    L31:
        monitor-exit(r0);
        return r32;
    L30:
        r32 = r3.captureBurst(r4.getCaptureRequestList(), r4);     // Catch: Throwable -> L10
        goto L31
    L18:
        if ((this.session instanceof CameraConstrainedHighSpeedCaptureSessionWrapper) == true) goto L26;
        if (r4.getRepeating() == true) goto L22;
        r32 = this.session.capture(r4.getCaptureRequestList().get(0), r4);     // Catch: Throwable -> L10
        goto L31
    L22:
        if (this.awaitRepeatingRequestOnDisconnect == false) goto L24;
        this.lastSingleRepeatingRequestSequence = r4;     // Catch: Throwable -> L10
    L24:
        r32 = this.session.setRepeatingRequest(r4.getCaptureRequestList().get(0), r4);     // Catch: Throwable -> L10
        goto L31
    }

    public String toString() {
        return "Camera2CaptureSequenceProcessor-" + this.debugId;
    }
}
