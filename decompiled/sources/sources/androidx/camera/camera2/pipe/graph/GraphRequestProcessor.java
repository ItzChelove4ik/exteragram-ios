package androidx.camera.camera2.pipe.graph;

import android.hardware.camera2.CameraAccessException;
import android.os.Trace;
import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0;
import androidx.camera.camera2.pipe.CaptureSequence;
import androidx.camera.camera2.pipe.CaptureSequenceProcessor;
import androidx.camera.camera2.pipe.CaptureSequences;
import androidx.camera.camera2.pipe.InputRequest;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.camera2.pipe.core.Debug;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.media.ImageWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\b\u0005*\u0001+\u0018\u0000 .2\u00020\u0001:\u0001.B#\b\u0002\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\n\u001a\u00020\u0007H\u0000¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\f\u001a\u00020\u0007H\u0000¢\u0006\u0004\b\u000b\u0010\tJ\u0010\u0010\u000f\u001a\u00020\u0007H\u0080@¢\u0006\u0004\b\r\u0010\u000eJo\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0012\u0010\u0016\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00152\u0012\u0010\u0017\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00152\u0012\u0010\u0018\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00152\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u0012H\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b\u001f\u0010 R&\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u0014\u0010&\u001a\u00020%8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'R\u001e\u0010)\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030(8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010,\u001a\u00020+8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-¨\u0006/"}, d2 = {"Landroidx/camera/camera2/pipe/graph/GraphRequestProcessor;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CaptureSequenceProcessor;", "Landroidx/camera/camera2/pipe/CaptureSequence;", "captureSequenceProcessor", "<init>", "(Landroidx/camera/camera2/pipe/CaptureSequenceProcessor;)V", _UrlKt.FRAGMENT_ENCODE_SET, "abortCaptures$camera_camera2_pipe", "()V", "abortCaptures", "stopRepeating$camera_camera2_pipe", "stopRepeating", "shutdown$camera_camera2_pipe", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shutdown", _UrlKt.FRAGMENT_ENCODE_SET, "isRepeating", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/Request;", "requests", _UrlKt.FRAGMENT_ENCODE_SET, "defaultParameters", "graphParameters", "requiredParameters", "Landroidx/camera/camera2/pipe/Request$Listener;", "listeners", "submit$camera_camera2_pipe", "(ZLjava/util/List;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/List;)Z", "submit", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroidx/camera/camera2/pipe/CaptureSequenceProcessor;", _UrlKt.FRAGMENT_ENCODE_SET, "debugId", "I", "Lkotlinx/atomicfu/AtomicBoolean;", "closed", "Lkotlinx/atomicfu/AtomicBoolean;", _UrlKt.FRAGMENT_ENCODE_SET, "activeCaptureSequences", "Ljava/util/List;", "androidx/camera/camera2/pipe/graph/GraphRequestProcessor$activeBurstListener$1", "activeBurstListener", "Landroidx/camera/camera2/pipe/graph/GraphRequestProcessor$activeBurstListener$1;", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGraphRequestProcessor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GraphRequestProcessor.kt\nandroidx/camera/camera2/pipe/graph/GraphRequestProcessor\n+ 2 CaptureSequence.kt\nandroidx/camera/camera2/pipe/CaptureSequences\n+ 3 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n+ 4 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 6 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,246:1\n235#1:247\n236#1:277\n239#1:305\n240#1:335\n235#1:338\n236#1:368\n243#1:382\n244#1:412\n235#1:417\n236#1:447\n235#1:448\n236#1:478\n235#1:479\n236#1:509\n235#1:510\n236#1:540\n55#2:248\n58#2,8:253\n66#2,12:265\n55#2:306\n58#2,8:311\n66#2,12:323\n55#2:339\n58#2,8:344\n66#2,12:356\n55#2:383\n58#2,8:388\n66#2,12:400\n55#2:418\n58#2,8:423\n66#2,12:435\n55#2:449\n58#2,8:454\n66#2,12:466\n55#2:480\n58#2,8:485\n66#2,12:497\n55#2:511\n58#2,8:516\n66#2,12:528\n55#2:541\n58#2,8:546\n66#2,12:558\n55#2:570\n58#2,8:575\n66#2,12:587\n55#2:599\n58#2,8:604\n66#2,12:616\n71#3,4:249\n78#3,4:261\n48#3,2:282\n71#3,4:284\n50#3,3:288\n78#3,4:291\n71#3,4:307\n78#3,4:319\n71#3,4:340\n78#3,4:352\n48#3,2:369\n71#3,4:371\n50#3,3:375\n78#3,4:378\n71#3,4:384\n78#3,4:396\n71#3,4:419\n78#3,4:431\n71#3,4:450\n78#3,4:462\n71#3,4:481\n78#3,4:493\n71#3,4:512\n78#3,4:524\n71#3,4:542\n78#3,4:554\n71#3,4:571\n78#3,4:583\n71#3,4:600\n78#3,4:612\n50#4,2:278\n71#4,2:280\n71#4,2:298\n71#4,2:300\n50#4,2:303\n71#4,2:336\n50#4,2:413\n71#4,2:415\n1761#5,3:295\n1#6:302\n*S KotlinDebug\n*F\n+ 1 GraphRequestProcessor.kt\nandroidx/camera/camera2/pipe/graph/GraphRequestProcessor\n*L\n95#1:247\n95#1:277\n181#1:305\n181#1:335\n223#1:338\n223#1:368\n204#1:382\n204#1:412\n223#1:417\n223#1:447\n223#1:448\n223#1:478\n223#1:479\n223#1:509\n223#1:510\n223#1:540\n95#1:248\n95#1:253,8\n95#1:265,12\n181#1:306\n181#1:311,8\n181#1:323,12\n223#1:339\n223#1:344,8\n223#1:356,12\n204#1:383\n204#1:388,8\n204#1:400,12\n223#1:418\n223#1:423,8\n223#1:435,12\n223#1:449\n223#1:454,8\n223#1:466,12\n223#1:480\n223#1:485,8\n223#1:497,12\n223#1:511\n223#1:516,8\n223#1:528,12\n235#1:541\n235#1:546,8\n235#1:558,12\n239#1:570\n239#1:575,8\n239#1:587,12\n243#1:599\n243#1:604,8\n243#1:616,12\n95#1:249,4\n95#1:261,4\n130#1:282,2\n130#1:284,4\n130#1:288,3\n130#1:291,4\n181#1:307,4\n181#1:319,4\n223#1:340,4\n223#1:352,4\n196#1:369,2\n196#1:371,4\n196#1:375,3\n196#1:378,4\n204#1:384,4\n204#1:396,4\n223#1:419,4\n223#1:431,4\n223#1:450,4\n223#1:462,4\n223#1:481,4\n223#1:493,4\n223#1:512,4\n223#1:524,4\n235#1:542,4\n235#1:554,4\n239#1:571,4\n239#1:583,4\n243#1:600,4\n243#1:612,4\n108#1:278,2\n124#1:280,2\n159#1:298,2\n169#1:300,2\n180#1:303,2\n192#1:336,2\n206#1:413,2\n209#1:415,2\n144#1:295,3\n*E\n"})
/* loaded from: classes3.dex */
public final class GraphRequestProcessor {
    public static final Companion Companion = null;
    private final GraphRequestProcessor$activeBurstListener$1 activeBurstListener;
    private final List<CaptureSequence<?>> activeCaptureSequences;
    private final CaptureSequenceProcessor<Object, CaptureSequence<Object>> captureSequenceProcessor;
    private final AtomicBoolean closed;
    private final int debugId;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0007¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/pipe/graph/GraphRequestProcessor$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "from", "Landroidx/camera/camera2/pipe/graph/GraphRequestProcessor;", "captureSequenceProcessor", "Landroidx/camera/camera2/pipe/CaptureSequenceProcessor;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final GraphRequestProcessor from(CaptureSequenceProcessor<?, ?> r2) {
            return new GraphRequestProcessor(r2, null);
        }
    }

    static {
        Companion = new Companion(null);
    }

    private GraphRequestProcessor(CaptureSequenceProcessor<? extends Object, CaptureSequence<Object>> r1) {
        this.captureSequenceProcessor = r1;
        this.debugId = GraphRequestProcessorKt.getGraphRequestProcessorIds().incrementAndGet();
        this.closed = AtomicFU.atomic(false);
        this.activeCaptureSequences = new ArrayList();
        this.activeBurstListener = new GraphRequestProcessor$activeBurstListener$1(this);
    }

    public /* synthetic */ GraphRequestProcessor(CaptureSequenceProcessor r1, DefaultConstructorMarker r2) {
        this(r1);
    }

    public static final /* synthetic */ GraphRequestProcessor$activeBurstListener$1 access$getActiveBurstListener$p(GraphRequestProcessor r0) {
        return r0.activeBurstListener;
    }

    public static final /* synthetic */ List access$getActiveCaptureSequences$p(GraphRequestProcessor r0) {
        return r0.activeCaptureSequences;
    }

    public static final /* synthetic */ CaptureSequenceProcessor access$getCaptureSequenceProcessor$p(GraphRequestProcessor r0) {
        return r0.captureSequenceProcessor;
    }

    public final void abortCaptures$camera_camera2_pipe() {
        List<CaptureSequence<?>> r0 = this.activeCaptureSequences;
        monitor-enter(r0);
        List r1 = CollectionsKt.toList(this.activeCaptureSequences);     // Catch: Throwable -> L24
        this.activeCaptureSequences.clear();     // Catch: Throwable -> L24
        monitor-exit(r0);
        Iterator r02 = r1.iterator();
    L8:
        if (r02.hasNext() == false) goto L22;
        CaptureSequence r12 = (CaptureSequence) r02.next();
        CaptureSequences r2 = CaptureSequences.INSTANCE;
        Debug r22 = Debug.INSTANCE;
        Trace.beginSection("InvokeInternalListeners");
        int r23 = r12.getCaptureMetadataList().size();
        int r4 = 0;
    L10:
        if (r4 >= r23) goto L15;
        RequestMetadata r5 = r12.getCaptureMetadataList().get(r4);
        int r6 = r12.getListeners().size();
        int r7 = 0;
    L12:
        if (r7 >= r6) goto L14;
        r12.getListeners().get(r7).onAborted(r5.getRequest());
        r7 = r7 + 1;
        goto L12
    L14:
        r4 = r4 + 1;
        goto L10
    L15:
        Debug r24 = Debug.INSTANCE;
        Trace.endSection();
        Trace.beginSection("InvokeRequestListeners");
        int r25 = r12.getCaptureMetadataList().size();
        int r42 = 0;
    L16:
        if (r42 >= r25) goto L21;
        RequestMetadata r52 = r12.getCaptureMetadataList().get(r42);
        int r62 = r52.getRequest().getListeners().size();
        int r72 = 0;
    L18:
        if (r72 >= r62) goto L20;
        r52.getRequest().getListeners().get(r72).onAborted(r52.getRequest());
        r72 = r72 + 1;
        goto L18
    L20:
        r42 = r42 + 1;
        goto L16
    L21:
        Debug r13 = Debug.INSTANCE;
        Trace.endSection();
        goto L8
    L22:
        this.captureSequenceProcessor.abortCaptures();
        return;
    L24:
        th = move-exception;
        throw th;
    }

    public final Object shutdown$camera_camera2_pipe(Continuation<? super Unit> r4) {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L6;
        android.util.Log.d("CXCP", "Closing " + this);
    L6:
        if (this.closed.compareAndSet(false, true) == false) goto L13;
        Object r3 = this.captureSequenceProcessor.shutdown(r4);
        if (r3 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L11;
        return r3;
    L11:
        return Unit.INSTANCE;
    L13:
        return Unit.INSTANCE;
    }

    public final void stopRepeating$camera_camera2_pipe() {
        this.captureSequenceProcessor.stopRepeating();
    }

    public final boolean submit$camera_camera2_pipe(boolean r11, List<Request> r12, Map<?, ? extends Object> r13, Map<?, ? extends Object> r14, Map<?, ? extends Object> r15, List<? extends Request.Listener> r16) {
        if (this.closed.getValue() == true) goto L5;
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection("CXCP#buildCaptureSequence");     // Catch: Throwable -> L220
        CaptureSequence<?> r112 = access$getCaptureSequenceProcessor$p(this).build(r11, r12, r13, r14, r15, access$getActiveBurstListener$p(this), r16);     // Catch: Throwable -> L220
        Trace.endSection();
        boolean r132 = true;
        if (r112 != null) goto L40;
        List<Request> r113 = r12;
        if ((r113 instanceof Collection) == true) goto L15;
    L17:
        Iterator<T> r114 = r113.iterator();
    L19:
        if (r114.hasNext() == false) goto L36;
        if (((Request) r114.next()).getInputRequest() == null) goto L19;
        Iterator<Request> r10 = r12.iterator();
    L24:
        if (r10.hasNext() == false) goto L34;
        Request r115 = r10.next();
        InputRequest r122 = r115.getInputRequest();
        if (r122 == null) goto L30;
        ImageWrapper r123 = r122.getImage();
        if (r123 == null) goto L30;
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(r123);
        Unit r124 = Unit.INSTANCE;
    L30:
        Iterator<Request.Listener> r125 = r115.getListeners().iterator();
    L32:
        if (r125.hasNext() == false) goto L24;
        r125.next().onAborted(r115);
        goto L32
    L34:
        return true;
    L36:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L38;
        android.util.Log.w("CXCP", "Failed to submit " + r12 + ": " + this + " failed to build CaptureSequence.");
    L38:
        return false;
    L15:
        if (r113.isEmpty() == false) goto L17;
    L40:
        if (this.closed.getValue() == false) goto L46;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L44;
        android.util.Log.w("CXCP", "Failed to submit " + r12 + ": " + this + " is closed.");
    L44:
        return false;
    L46:
        if (r112.getRepeating() == true) goto L237;
        List<CaptureSequence<?>> r126 = this.activeCaptureSequences;
        monitor-enter(r126);
        this.activeCaptureSequences.add(r112);     // Catch: Throwable -> L52
        monitor-exit(r126);
    L52:
        th = move-exception;
        throw th;
    L237:
    L61:
        r132 = false;
    L195:
        if (r132 == false) goto L197;
    L219:
        return false;
    L197:
        if (r112.getRepeating() == true) goto L219;
        List<CaptureSequence<?>> r127 = this.activeCaptureSequences;
        monitor-enter(r127);
        this.activeCaptureSequences.remove(r112);     // Catch: Throwable -> L215
        monitor-exit(r127);
        CaptureSequences r102 = CaptureSequences.INSTANCE;
        Debug r103 = Debug.INSTANCE;
        Trace.beginSection("InvokeInternalListeners");
        int r104 = r112.getCaptureMetadataList().size();
        int r128 = 0;
    L203:
        if (r128 >= r104) goto L208;
        RequestMetadata r133 = r112.getCaptureMetadataList().get(r128);
        int r142 = r112.getListeners().size();
        int r02 = 0;
    L205:
        if (r02 >= r142) goto L207;
        r112.getListeners().get(r02).onAborted(r133.getRequest());
        r02 = r02 + 1;
        goto L205
    L207:
        r128 = r128 + 1;
        goto L203
    L208:
        Debug r105 = Debug.INSTANCE;
        Trace.endSection();
        Trace.beginSection("InvokeRequestListeners");
        int r106 = r112.getCaptureMetadataList().size();
        int r129 = 0;
    L209:
        if (r129 >= r106) goto L214;
        RequestMetadata r134 = r112.getCaptureMetadataList().get(r129);
        int r143 = r134.getRequest().getListeners().size();
        int r03 = 0;
    L211:
        if (r03 >= r143) goto L213;
        r134.getRequest().getListeners().get(r03).onAborted(r134.getRequest());
        r03 = r03 + 1;
        goto L211
    L213:
        r129 = r129 + 1;
        goto L209
    L214:
        Debug r107 = Debug.INSTANCE;
        Trace.endSection();
    L215:
        th = move-exception;
        throw th;
    L59:
        th = move-exception;
        Throwable r1210 = th;
        r132 = false;
    L170:
        if (r132 == false) goto L172;
        throw r1210;
    L172:
        if (r112.getRepeating() == true) goto L269;
        List<CaptureSequence<?>> r135 = this.activeCaptureSequences;
        monitor-enter(r135);
        this.activeCaptureSequences.remove(r112);     // Catch: Throwable -> L190
        monitor-exit(r135);
        CaptureSequences r108 = CaptureSequences.INSTANCE;
        Debug r109 = Debug.INSTANCE;
        Trace.beginSection("InvokeInternalListeners");
        int r1010 = r112.getCaptureMetadataList().size();
        int r136 = 0;
    L178:
        if (r136 >= r1010) goto L183;
        RequestMetadata r144 = r112.getCaptureMetadataList().get(r136);
        int r04 = r112.getListeners().size();
        int r2 = 0;
    L180:
        if (r2 >= r04) goto L182;
        r112.getListeners().get(r2).onAborted(r144.getRequest());
        r2 = r2 + 1;
        goto L180
    L182:
        r136 = r136 + 1;
        goto L178
    L183:
        Debug r1011 = Debug.INSTANCE;
        Trace.endSection();
        Trace.beginSection("InvokeRequestListeners");
        int r1012 = r112.getCaptureMetadataList().size();
        int r137 = 0;
    L184:
        if (r137 >= r1012) goto L189;
        RequestMetadata r145 = r112.getCaptureMetadataList().get(r137);
        int r05 = r145.getRequest().getListeners().size();
        int r22 = 0;
    L186:
        if (r22 >= r05) goto L188;
        r145.getRequest().getListeners().get(r22).onAborted(r145.getRequest());
        r22 = r22 + 1;
        goto L186
    L188:
        r137 = r137 + 1;
        goto L184
    L189:
        Debug r1013 = Debug.INSTANCE;
        Trace.endSection();
        throw r1210;
    L190:
        th = move-exception;
        throw th;
    L269:
        throw r1210;
    L57:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L62;
        android.util.Log.d("CXCP", this + " submitting " + r112);     // Catch: Throwable -> L59 CameraAccessException -> L61
    L62:
        CaptureSequences r1211 = CaptureSequences.INSTANCE;     // Catch: Throwable -> L59 CameraAccessException -> L61
        Trace.beginSection("InvokeInternalListeners");     // Catch: Throwable -> L59 CameraAccessException -> L61
        int r1212 = r112.getCaptureMetadataList().size();     // Catch: Throwable -> L59 CameraAccessException -> L61
        int r146 = 0;
    L63:
        if (r146 >= r1212) goto L68;
        RequestMetadata r06 = r112.getCaptureMetadataList().get(r146);     // Catch: Throwable -> L59 CameraAccessException -> L61
        int r23 = r112.getListeners().size();     // Catch: Throwable -> L59 CameraAccessException -> L61
        int r3 = 0;
    L65:
        if (r3 >= r23) goto L67;
        r112.getListeners().get(r3).onRequestSequenceCreated(r06);     // Catch: Throwable -> L59 CameraAccessException -> L61
        r3 = r3 + 1;     // Catch: Throwable -> L59 CameraAccessException -> L61
        goto L65
    L67:
        r146 = r146 + 1;     // Catch: Throwable -> L59 CameraAccessException -> L61
        goto L63
    L68:
        Debug r1213 = Debug.INSTANCE;     // Catch: Throwable -> L59 CameraAccessException -> L61
        Trace.endSection();     // Catch: Throwable -> L59 CameraAccessException -> L61
        Trace.beginSection("InvokeRequestListeners");     // Catch: Throwable -> L59 CameraAccessException -> L61
        int r1214 = r112.getCaptureMetadataList().size();     // Catch: Throwable -> L59 CameraAccessException -> L61
        int r147 = 0;
    L69:
        if (r147 >= r1214) goto L74;
        RequestMetadata r07 = r112.getCaptureMetadataList().get(r147);     // Catch: Throwable -> L59 CameraAccessException -> L61
        int r24 = r07.getRequest().getListeners().size();     // Catch: Throwable -> L59 CameraAccessException -> L61
        int r32 = 0;
    L71:
        if (r32 >= r24) goto L73;
        r07.getRequest().getListeners().get(r32).onRequestSequenceCreated(r07);     // Catch: Throwable -> L59 CameraAccessException -> L61
        r32 = r32 + 1;     // Catch: Throwable -> L59 CameraAccessException -> L61
        goto L71
    L73:
        r147 = r147 + 1;     // Catch: Throwable -> L59 CameraAccessException -> L61
        goto L69
    L74:
        Debug r1215 = Debug.INSTANCE;     // Catch: Throwable -> L59 CameraAccessException -> L61
        Trace.endSection();     // Catch: Throwable -> L59 CameraAccessException -> L61
        monitor-enter(r112);     // Catch: Throwable -> L59 CameraAccessException -> L61
    L82:
        th = move-exception;
        throw th;     // Catch: Throwable -> L59 CameraAccessException -> L61
    L77:
        if (this.closed.getValue() == false) goto L228;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L84;
        android.util.Log.w("CXCP", "Failed to submit " + r112 + ": " + this + " is closed.");     // Catch: Throwable -> L82
    L84:
        monitor-exit(r112);     // Catch: Throwable -> L59 CameraAccessException -> L61
        if (r112.getRepeating() == true) goto L104;
        List<CaptureSequence<?>> r1216 = this.activeCaptureSequences;
        monitor-enter(r1216);
        this.activeCaptureSequences.remove(r112);
        monitor-exit(r1216);
        CaptureSequences r1014 = CaptureSequences.INSTANCE;
        Trace.beginSection("InvokeInternalListeners");
        int r1015 = r112.getCaptureMetadataList().size();
        int r1217 = 0;
    L92:
        if (r1217 >= r1015) goto L97;
        RequestMetadata r138 = r112.getCaptureMetadataList().get(r1217);
        int r148 = r112.getListeners().size();
        int r08 = 0;
    L94:
        if (r08 >= r148) goto L96;
        r112.getListeners().get(r08).onAborted(r138.getRequest());
        r08 = r08 + 1;
        goto L94
    L96:
        r1217 = r1217 + 1;
        goto L92
    L97:
        Debug r1016 = Debug.INSTANCE;
        Trace.endSection();
        Trace.beginSection("InvokeRequestListeners");
        int r1017 = r112.getCaptureMetadataList().size();
        int r1218 = 0;
    L98:
        if (r1218 >= r1017) goto L103;
        RequestMetadata r139 = r112.getCaptureMetadataList().get(r1218);
        int r149 = r139.getRequest().getListeners().size();
        int r09 = 0;
    L100:
        if (r09 >= r149) goto L102;
        r139.getRequest().getListeners().get(r09).onAborted(r139.getRequest());
        r09 = r09 + 1;
        goto L100
    L102:
        r1218 = r1218 + 1;
        goto L98
    L103:
        Debug r1018 = Debug.INSTANCE;
        Trace.endSection();
    L104:
        return false;
    L228:
        Trace.beginSection("CXCP#submit(CaptureSequence)");     // Catch: Throwable -> L109
        Integer r1219 = access$getCaptureSequenceProcessor$p(this).submit(r112);     // Catch: Throwable -> L109
        if (r1219 == null) goto L111;
        int r1220 = r1219.intValue();     // Catch: Throwable -> L109
    L112:
        r112.setSequenceNumber(r1220);     // Catch: Throwable -> L109
        Trace.endSection();     // Catch: Throwable -> L82
        monitor-exit(r112);     // Catch: Throwable -> L59 CameraAccessException -> L61
        if (r1220 == (-1)) goto L137;
        CaptureSequences r1221 = CaptureSequences.INSTANCE;     // Catch: Throwable -> L59 CameraAccessException -> L61
        Trace.beginSection("InvokeInternalListeners");     // Catch: Throwable -> L59 CameraAccessException -> L61
        int r1222 = r112.getCaptureMetadataList().size();     // Catch: Throwable -> L59 CameraAccessException -> L61
        int r1410 = 0;
    L117:
        if (r1410 >= r1222) goto L122;
        RequestMetadata r010 = r112.getCaptureMetadataList().get(r1410);     // Catch: Throwable -> L59 CameraAccessException -> L61
        int r25 = r112.getListeners().size();     // Catch: Throwable -> L59 CameraAccessException -> L61
        int r33 = 0;
    L119:
        if (r33 >= r25) goto L121;
        r112.getListeners().get(r33).onRequestSequenceSubmitted(r010);     // Catch: Throwable -> L59 CameraAccessException -> L61
        r33 = r33 + 1;     // Catch: Throwable -> L59 CameraAccessException -> L61
        goto L119
    L121:
        r1410 = r1410 + 1;     // Catch: Throwable -> L59 CameraAccessException -> L61
        goto L117
    L122:
        Debug r1223 = Debug.INSTANCE;     // Catch: Throwable -> L59 CameraAccessException -> L61
        Trace.endSection();     // Catch: Throwable -> L59 CameraAccessException -> L61
        Trace.beginSection("InvokeRequestListeners");     // Catch: Throwable -> L59 CameraAccessException -> L61
        int r1224 = r112.getCaptureMetadataList().size();     // Catch: Throwable -> L59 CameraAccessException -> L61
        int r1411 = 0;
    L123:
        if (r1411 >= r1224) goto L128;
        RequestMetadata r011 = r112.getCaptureMetadataList().get(r1411);     // Catch: Throwable -> L59 CameraAccessException -> L61
        int r26 = r011.getRequest().getListeners().size();     // Catch: Throwable -> L59 CameraAccessException -> L61
        int r34 = 0;
    L125:
        if (r34 >= r26) goto L127;
        r011.getRequest().getListeners().get(r34).onRequestSequenceSubmitted(r011);     // Catch: Throwable -> L59 CameraAccessException -> L61
        r34 = r34 + 1;     // Catch: Throwable -> L59 CameraAccessException -> L61
        goto L125
    L127:
        r1411 = r1411 + 1;     // Catch: Throwable -> L59 CameraAccessException -> L61
        goto L123
    L128:
        Debug r1225 = Debug.INSTANCE;     // Catch: Throwable -> L59 CameraAccessException -> L61
        Trace.endSection();     // Catch: Throwable -> L59 CameraAccessException -> L61
    L133:
        th = move-exception;
        r1210 = th;
        goto L170
    L130:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L135;
        android.util.Log.d("CXCP", this + " submitted " + r112);     // Catch: Throwable -> L133 CameraAccessException -> L223
    L135:
        boolean r1226 = true;
    L140:
        if (r1226 == false) goto L142;
    L165:
        return r132;
    L142:
        if (r112.getRepeating() == true) goto L165;
        List<CaptureSequence<?>> r1227 = this.activeCaptureSequences;
        monitor-enter(r1227);
        this.activeCaptureSequences.remove(r112);     // Catch: Throwable -> L161
        monitor-exit(r1227);
        CaptureSequences r1019 = CaptureSequences.INSTANCE;
        Trace.beginSection("InvokeInternalListeners");
        int r1020 = r112.getCaptureMetadataList().size();
        int r1228 = 0;
    L148:
        if (r1228 >= r1020) goto L153;
        RequestMetadata r1412 = r112.getCaptureMetadataList().get(r1228);
        int r012 = r112.getListeners().size();
        int r27 = 0;
    L150:
        if (r27 >= r012) goto L152;
        r112.getListeners().get(r27).onAborted(r1412.getRequest());
        r27 = r27 + 1;
        goto L150
    L152:
        r1228 = r1228 + 1;
        goto L148
    L153:
        Debug r1021 = Debug.INSTANCE;
        Trace.endSection();
        Trace.beginSection("InvokeRequestListeners");
        int r1022 = r112.getCaptureMetadataList().size();
        int r1229 = 0;
    L154:
        if (r1229 >= r1022) goto L159;
        RequestMetadata r1413 = r112.getCaptureMetadataList().get(r1229);
        int r013 = r1413.getRequest().getListeners().size();
        int r28 = 0;
    L156:
        if (r28 >= r013) goto L158;
        r1413.getRequest().getListeners().get(r28).onAborted(r1413.getRequest());
        r28 = r28 + 1;
        goto L156
    L158:
        r1229 = r1229 + 1;
        goto L154
    L159:
        Debug r1023 = Debug.INSTANCE;
        Trace.endSection();
        return r132;
    L161:
        th = move-exception;
        throw th;
    L137:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L139;
        android.util.Log.w("CXCP", "Failed to submit " + r112 + ": " + this + " received -1 from submit.");     // Catch: Throwable -> L59 CameraAccessException -> L61
    L139:
        r1226 = false;
        r132 = false;
        goto L140
    L111:
        r1220 = -1;
    L109:
        th = move-exception;
        Trace.endSection();     // Catch: Throwable -> L82
        throw th;     // Catch: Throwable -> L82
    L220:
        th = move-exception;
        Trace.endSection();
        throw th;
    L5:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L7;
        android.util.Log.w("CXCP", "Failed to submit " + r12 + ": " + this + " is closed.");
    L7:
        return false;
    }

    public String toString() {
        return "GraphRequestProcessor-" + this.debugId;
    }
}
