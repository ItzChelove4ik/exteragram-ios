package androidx.camera.camera2.impl;

import androidx.camera.camera2.pipe.FrameInfo;
import androidx.camera.camera2.pipe.FrameMetadata;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestFailure;
import androidx.camera.camera2.pipe.RequestMetadata;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal._UtilJvmKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\n\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ/\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J'\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ'\u0010$\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010!\u001a\u00020 H\u0016¢\u0006\u0004\b\"\u0010#J'\u0010)\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010&\u001a\u00020%H\u0016¢\u0006\u0004\b'\u0010(J\u0017\u0010*\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b*\u0010+J\u001f\u0010.\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b,\u0010-J\u0017\u0010/\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b/\u0010+J\u0017\u00100\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b0\u0010+J'\u00105\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u00102\u001a\u000201H\u0016¢\u0006\u0004\b3\u00104J'\u00108\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u00106\u001a\u00020\u001bH\u0016¢\u0006\u0004\b7\u0010\u001eR \u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0005098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R<\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050<2\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050<8\u0007@BX\u0086\u000e¢\u0006\f\n\u0004\b>\u0010;\u001a\u0004\b?\u0010@¨\u0006A"}, d2 = {"Landroidx/camera/camera2/impl/ComboRequestListener;", "Landroidx/camera/camera2/pipe/Request$Listener;", "<init>", "()V", "listener", "Ljava/util/concurrent/Executor;", "executor", _UrlKt.FRAGMENT_ENCODE_SET, "addListener", "(Landroidx/camera/camera2/pipe/Request$Listener;Ljava/util/concurrent/Executor;)V", "removeListener", "(Landroidx/camera/camera2/pipe/Request$Listener;)V", "Landroidx/camera/camera2/pipe/Request;", PluginsConstants.REQUEST, "onAborted", "(Landroidx/camera/camera2/pipe/Request;)V", "Landroidx/camera/camera2/pipe/RequestMetadata;", "requestMetadata", "Landroidx/camera/camera2/pipe/FrameNumber;", "frameNumber", "Landroidx/camera/camera2/pipe/StreamId;", "streamId", "Landroidx/camera/camera2/pipe/OutputId;", "outputId", "onBufferLost-iiEMlm4", "(Landroidx/camera/camera2/pipe/RequestMetadata;JII)V", "onBufferLost", "Landroidx/camera/camera2/pipe/FrameInfo;", "result", "onComplete-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/FrameInfo;)V", "onComplete", "Landroidx/camera/camera2/pipe/RequestFailure;", "requestFailure", "onFailed-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/RequestFailure;)V", "onFailed", "Landroidx/camera/camera2/pipe/FrameMetadata;", "captureResult", "onPartialCaptureResult-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/FrameMetadata;)V", "onPartialCaptureResult", "onRequestSequenceAborted", "(Landroidx/camera/camera2/pipe/RequestMetadata;)V", "onRequestSequenceCompleted-RuT0dZU", "(Landroidx/camera/camera2/pipe/RequestMetadata;J)V", "onRequestSequenceCompleted", "onRequestSequenceCreated", "onRequestSequenceSubmitted", "Landroidx/camera/camera2/pipe/CameraTimestamp;", "timestamp", "onStarted-uGKBvU4", "(Landroidx/camera/camera2/pipe/RequestMetadata;JJ)V", "onStarted", "totalCaptureResult", "onTotalCaptureResult-CcXjc1I", "onTotalCaptureResult", _UrlKt.FRAGMENT_ENCODE_SET, "requestListeners", "Ljava/util/Map;", _UrlKt.FRAGMENT_ENCODE_SET, "value", "listeners", "getListeners", "()Ljava/util/Map;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nComboRequestListener.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ComboRequestListener.kt\nandroidx/camera/camera2/impl/ComboRequestListener\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,168:1\n1#2:169\n216#3,2:170\n216#3,2:172\n216#3,2:174\n216#3,2:176\n216#3,2:178\n216#3,2:180\n216#3,2:182\n216#3,2:184\n216#3,2:186\n216#3,2:188\n216#3,2:190\n*S KotlinDebug\n*F\n+ 1 ComboRequestListener.kt\nandroidx/camera/camera2/impl/ComboRequestListener\n*L\n64#1:170,2\n75#1:172,2\n87#1:174,2\n97#1:176,2\n107#1:178,2\n115#1:180,2\n124#1:182,2\n130#1:184,2\n136#1:186,2\n146#1:188,2\n156#1:190,2\n*E\n"})
/* loaded from: classes3.dex */
public final class ComboRequestListener implements Request.Listener {
    private volatile Map<Request.Listener, ? extends Executor> listeners;
    private final Map<Request.Listener, Executor> requestListeners;

    public static /* synthetic */ void $r8$lambda$2ZXqZi6dlwnI6p0axLgb2KRFK0E(Request.Listener r0, RequestMetadata r1, long r2) {
        onRequestSequenceCompleted_RuT0dZU$lambda$0$0(r0, r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$3_sSK1DNXo8fKI0osgusaiU9ZJ4(Request.Listener r0, RequestMetadata r1, long r2, int r4, int r5) {
        onBufferLost_iiEMlm4$lambda$0$0(r0, r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$7rkPM_7WH32ODeSIUTwHJqT1D9M(Request.Listener r0, RequestMetadata r1, long r2, FrameInfo r4) {
        onComplete_CcXjc1I$lambda$0$0(r0, r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$E9ODtXCbLpuROptrEFqoLHFOkio(Request.Listener r0, Request r1) {
        onAborted$lambda$0$0(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$LcxW0_mUoZxH3eC5ZQCHd3kx2cY(Request.Listener r0, RequestMetadata r1, long r2, long r4) {
        onStarted_uGKBvU4$lambda$0$0(r0, r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$STw5poy3cPuFFjOHqAbWO_1YVsM(Request.Listener r0, RequestMetadata r1) {
        onRequestSequenceSubmitted$lambda$0$0(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$V8l73qwPNm62uCazDUDFIhkg3JI(Request.Listener r0, RequestMetadata r1, long r2, FrameMetadata r4) {
        onPartialCaptureResult_CcXjc1I$lambda$0$0(r0, r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$XGgYw_mFOPwpHwoPmAE7AdNg2EE(Request.Listener r0, RequestMetadata r1) {
        onRequestSequenceAborted$lambda$0$0(r0, r1);
    }

    /* renamed from: $r8$lambda$_RPrTYani4Vl2_e-cNMrH07MWUI, reason: not valid java name */
    public static /* synthetic */ void m68$r8$lambda$_RPrTYani4Vl2_ecNMrH07MWUI(Request.Listener r0, RequestMetadata r1, long r2, RequestFailure r4) {
        onFailed_CcXjc1I$lambda$0$0(r0, r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$bomjp4AIYjzkgdmuj8CC2VQTf7g(Request.Listener r0, RequestMetadata r1, long r2, FrameInfo r4) {
        onTotalCaptureResult_CcXjc1I$lambda$0$0(r0, r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$dMagU45qO6aq0UTPbzx28ernX08(Request.Listener r0, RequestMetadata r1) {
        onRequestSequenceCreated$lambda$0$0(r0, r1);
    }

    public ComboRequestListener() {
        this.requestListeners = new LinkedHashMap();
        this.listeners = MapsKt.emptyMap();
    }

    private static final void onAborted$lambda$0$0(Request.Listener r0, Request r1) {
        r0.onAborted(r1);
    }

    private static final void onBufferLost_iiEMlm4$lambda$0$0(Request.Listener r0, RequestMetadata r1, long r2, int r4, int r5) {
        r0.mo48onBufferLostiiEMlm4(r1, r2, r4, r5);
    }

    private static final void onComplete_CcXjc1I$lambda$0$0(Request.Listener r0, RequestMetadata r1, long r2, FrameInfo r4) {
        r0.mo13onCompleteCcXjc1I(r1, r2, r4);
    }

    private static final void onFailed_CcXjc1I$lambda$0$0(Request.Listener r0, RequestMetadata r1, long r2, RequestFailure r4) {
        r0.mo14onFailedCcXjc1I(r1, r2, r4);
    }

    private static final void onPartialCaptureResult_CcXjc1I$lambda$0$0(Request.Listener r0, RequestMetadata r1, long r2, FrameMetadata r4) {
        r0.mo49onPartialCaptureResultCcXjc1I(r1, r2, r4);
    }

    private static final void onRequestSequenceAborted$lambda$0$0(Request.Listener r0, RequestMetadata r1) {
        r0.onRequestSequenceAborted(r1);
    }

    private static final void onRequestSequenceCompleted_RuT0dZU$lambda$0$0(Request.Listener r0, RequestMetadata r1, long r2) {
        r0.mo51onRequestSequenceCompletedRuT0dZU(r1, r2);
    }

    private static final void onRequestSequenceCreated$lambda$0$0(Request.Listener r0, RequestMetadata r1) {
        r0.onRequestSequenceCreated(r1);
    }

    private static final void onRequestSequenceSubmitted$lambda$0$0(Request.Listener r0, RequestMetadata r1) {
        r0.onRequestSequenceSubmitted(r1);
    }

    private static final void onStarted_uGKBvU4$lambda$0$0(Request.Listener r0, RequestMetadata r1, long r2, long r4) {
        r0.mo52onStarteduGKBvU4(r1, r2, r4);
    }

    private static final void onTotalCaptureResult_CcXjc1I$lambda$0$0(Request.Listener r0, RequestMetadata r1, long r2, FrameInfo r4) {
        r0.mo15onTotalCaptureResultCcXjc1I(r1, r2, r4);
    }

    public final void addListener(Request.Listener r3, Executor r4) {
        if (this.listeners.containsKey(r3) == true) goto L12;
        Map<Request.Listener, Executor> r0 = this.requestListeners;
        monitor-enter(r0);
        this.requestListeners.put(r3, r4);     // Catch: Throwable -> L9
        this.listeners = MapsKt.toMap(this.requestListeners);     // Catch: Throwable -> L9
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r0);
        return;
    L9:
        th = move-exception;
        throw th;
    L12:
        _UtilJvmKt$$ExternalSyntheticBUOutline0.m(r3, " was already registered!");
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    public void onAborted(Request r4) {
        Iterator<Map.Entry<Request.Listener, ? extends Executor>> r3 = this.listeners.entrySet().iterator();
    L4:
        if (r3.hasNext() == false) goto L6;
        Map.Entry<Request.Listener, ? extends Executor> r0 = r3.next();
        Request.Listener r1 = r0.getKey();
        r0.getValue().execute(new ComboRequestListener$$ExternalSyntheticLambda0(r1, r4));
        goto L4
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onBufferLost-iiEMlm4 */
    public void mo48onBufferLostiiEMlm4(RequestMetadata r10, long r11, int r13, int r14) {
        Iterator<Map.Entry<Request.Listener, ? extends Executor>> r9 = this.listeners.entrySet().iterator();
    L4:
        if (r9.hasNext() == false) goto L6;
        Map.Entry<Request.Listener, ? extends Executor> r0 = r9.next();
        Request.Listener r3 = r0.getKey();
        r0.getValue().execute(new ComboRequestListener$$ExternalSyntheticLambda5(r3, r10, r11, r13, r14));
        goto L4
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onComplete-CcXjc1I */
    public void mo13onCompleteCcXjc1I(RequestMetadata r9, long r10, FrameInfo r12) {
        Iterator<Map.Entry<Request.Listener, ? extends Executor>> r8 = this.listeners.entrySet().iterator();
    L4:
        if (r8.hasNext() == false) goto L6;
        Map.Entry<Request.Listener, ? extends Executor> r0 = r8.next();
        Request.Listener r3 = r0.getKey();
        r0.getValue().execute(new ComboRequestListener$$ExternalSyntheticLambda10(r3, r9, r10, r12));
        goto L4
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onFailed-CcXjc1I */
    public void mo14onFailedCcXjc1I(RequestMetadata r9, long r10, RequestFailure r12) {
        Iterator<Map.Entry<Request.Listener, ? extends Executor>> r8 = this.listeners.entrySet().iterator();
    L4:
        if (r8.hasNext() == false) goto L6;
        Map.Entry<Request.Listener, ? extends Executor> r0 = r8.next();
        Request.Listener r3 = r0.getKey();
        r0.getValue().execute(new ComboRequestListener$$ExternalSyntheticLambda4(r3, r9, r10, r12));
        goto L4
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onPartialCaptureResult-CcXjc1I */
    public void mo49onPartialCaptureResultCcXjc1I(RequestMetadata r9, long r10, FrameMetadata r12) {
        Iterator<Map.Entry<Request.Listener, ? extends Executor>> r8 = this.listeners.entrySet().iterator();
    L4:
        if (r8.hasNext() == false) goto L6;
        Map.Entry<Request.Listener, ? extends Executor> r0 = r8.next();
        Request.Listener r3 = r0.getKey();
        r0.getValue().execute(new ComboRequestListener$$ExternalSyntheticLambda6(r3, r9, r10, r12));
        goto L4
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    public void onRequestSequenceAborted(RequestMetadata r4) {
        Iterator<Map.Entry<Request.Listener, ? extends Executor>> r3 = this.listeners.entrySet().iterator();
    L4:
        if (r3.hasNext() == false) goto L6;
        Map.Entry<Request.Listener, ? extends Executor> r0 = r3.next();
        Request.Listener r1 = r0.getKey();
        r0.getValue().execute(new ComboRequestListener$$ExternalSyntheticLambda1(r1, r4));
        goto L4
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onRequestSequenceCompleted-RuT0dZU */
    public void mo51onRequestSequenceCompletedRuT0dZU(RequestMetadata r4, long r5) {
        Iterator<Map.Entry<Request.Listener, ? extends Executor>> r3 = this.listeners.entrySet().iterator();
    L4:
        if (r3.hasNext() == false) goto L6;
        Map.Entry<Request.Listener, ? extends Executor> r0 = r3.next();
        Request.Listener r1 = r0.getKey();
        r0.getValue().execute(new ComboRequestListener$$ExternalSyntheticLambda7(r1, r4, r5));
        goto L4
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    public void onRequestSequenceCreated(RequestMetadata r4) {
        Iterator<Map.Entry<Request.Listener, ? extends Executor>> r3 = this.listeners.entrySet().iterator();
    L4:
        if (r3.hasNext() == false) goto L6;
        Map.Entry<Request.Listener, ? extends Executor> r0 = r3.next();
        Request.Listener r1 = r0.getKey();
        r0.getValue().execute(new ComboRequestListener$$ExternalSyntheticLambda2(r1, r4));
        goto L4
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    public void onRequestSequenceSubmitted(RequestMetadata r4) {
        Iterator<Map.Entry<Request.Listener, ? extends Executor>> r3 = this.listeners.entrySet().iterator();
    L4:
        if (r3.hasNext() == false) goto L6;
        Map.Entry<Request.Listener, ? extends Executor> r0 = r3.next();
        Request.Listener r1 = r0.getKey();
        r0.getValue().execute(new ComboRequestListener$$ExternalSyntheticLambda9(r1, r4));
        goto L4
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onStarted-uGKBvU4 */
    public void mo52onStarteduGKBvU4(RequestMetadata r10, long r11, long r13) {
        Iterator<Map.Entry<Request.Listener, ? extends Executor>> r9 = this.listeners.entrySet().iterator();
    L4:
        if (r9.hasNext() == false) goto L6;
        Map.Entry<Request.Listener, ? extends Executor> r0 = r9.next();
        Request.Listener r3 = r0.getKey();
        r0.getValue().execute(new ComboRequestListener$$ExternalSyntheticLambda8(r3, r10, r11, r13));
        goto L4
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onTotalCaptureResult-CcXjc1I */
    public void mo15onTotalCaptureResultCcXjc1I(RequestMetadata r9, long r10, FrameInfo r12) {
        Iterator<Map.Entry<Request.Listener, ? extends Executor>> r8 = this.listeners.entrySet().iterator();
    L4:
        if (r8.hasNext() == false) goto L6;
        Map.Entry<Request.Listener, ? extends Executor> r0 = r8.next();
        Request.Listener r3 = r0.getKey();
        r0.getValue().execute(new ComboRequestListener$$ExternalSyntheticLambda3(r3, r9, r10, r12));
        goto L4
    }

    public final void removeListener(Request.Listener r3) {
        Map<Request.Listener, Executor> r0 = this.requestListeners;
        monitor-enter(r0);
        this.requestListeners.remove(r3);     // Catch: Throwable -> L7
        this.listeners = MapsKt.toMap(this.requestListeners);     // Catch: Throwable -> L7
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
