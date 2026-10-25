package androidx.camera.camera2.pipe.graph;

import android.hardware.camera2.CaptureResult;
import androidx.camera.camera2.pipe.FrameMetadata;
import androidx.camera.camera2.pipe.FrameNumber;
import androidx.camera.camera2.pipe.RequestNumber;
import androidx.camera.camera2.pipe.Result3A;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B3\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bB?\b\u0010\u0012\u001c\u0010\f\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\r\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u0011J\u0017\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001fH\u0016¢\u0006\u0004\b#\u0010$J\u001f\u0010%\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020\u0004H\u0016¢\u0006\u0004\b'\u0010(J\b\u0010)\u001a\u00020!H\u0016J\b\u0010*\u001a\u00020!H\u0016J\b\u0010+\u001a\u00020!H\u0016R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0012\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001d\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0013R\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Landroidx/camera/camera2/pipe/graph/Result3AStateListenerImpl;", "Landroidx/camera/camera2/pipe/graph/Result3AStateListener;", "exitCondition", "Lkotlin/Function1;", "Landroidx/camera/camera2/pipe/FrameMetadata;", _UrlKt.FRAGMENT_ENCODE_SET, "frameLimit", _UrlKt.FRAGMENT_ENCODE_SET, "timeLimitNs", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Integer;Ljava/lang/Long;)V", "exitConditionForKeys", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureResult$Key;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/util/Map;Ljava/lang/Integer;Ljava/lang/Long;)V", "Ljava/lang/Integer;", "Ljava/lang/Long;", "_result", "Lkotlinx/coroutines/CompletableDeferred;", "Landroidx/camera/camera2/pipe/Result3A;", "result", "Lkotlinx/coroutines/Deferred;", "getResult", "()Lkotlinx/coroutines/Deferred;", "frameNumberOfFirstUpdate", "Landroidx/camera/camera2/pipe/FrameNumber;", "timestampOfFirstUpdateNs", "initialRequestNumber", "Landroidx/camera/camera2/pipe/RequestNumber;", "onRequestSequenceCreated", _UrlKt.FRAGMENT_ENCODE_SET, "requestNumber", "onRequestSequenceCreated-DThHKJ0", "(J)V", PluginsConstants.UPDATE, "frameMetadata", "update-voP-kFw", "(JLandroidx/camera/camera2/pipe/FrameMetadata;)Z", "onStopRepeating", "onGraphStopped", "onGraphShutdown", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Result3AStateListenerImpl implements Result3AStateListener {
    private final CompletableDeferred<Result3A> _result;
    private final Function1<FrameMetadata, Boolean> exitCondition;
    private final Integer frameLimit;
    private volatile FrameNumber frameNumberOfFirstUpdate;
    private RequestNumber initialRequestNumber;
    private final Long timeLimitNs;
    private volatile Long timestampOfFirstUpdateNs;

    public Result3AStateListenerImpl(Map<CaptureResult.Key<?>, ? extends List<? extends Object>> r1, Integer r2, Long r3) {
        this(Result3AStateListenerKt.toConditionChecker(r1), r2, r3);
    }

    public /* synthetic */ Result3AStateListenerImpl(Map r2, Integer r3, Long r4, int r5, DefaultConstructorMarker r6) {
        if ((r5 & 2) == 0) goto L6;
        r3 = null;
    L6:
        if ((r5 & 4) == 0) goto L8;
        r4 = null;
    L8:
        this(r2, r3, r4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Result3AStateListenerImpl(Function1<? super FrameMetadata, Boolean> r1, Integer r2, Long r3) {
        this.exitCondition = r1;
        this.frameLimit = r2;
        this.timeLimitNs = r3;
        this._result = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
    }

    public /* synthetic */ Result3AStateListenerImpl(Function1 r2, Integer r3, Long r4, int r5, DefaultConstructorMarker r6) {
        if ((r5 & 2) == 0) goto L6;
        r3 = null;
    L6:
        if ((r5 & 4) == 0) goto L8;
        r4 = null;
    L8:
        this(r2, r3, r4);
    }

    public final Deferred<Result3A> getResult() {
        return this._result;
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphLoop.Listener
    public void onGraphShutdown() {
        this._result.complete(new Result3A(Result3A.Status.Companion.m385getSUBMIT_CANCELLEDJvTi9ms(), null, 2, null));
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphLoop.Listener
    public void onGraphStopped() {
        this._result.complete(new Result3A(Result3A.Status.Companion.m385getSUBMIT_CANCELLEDJvTi9ms(), null, 2, null));
    }

    @Override // androidx.camera.camera2.pipe.graph.Result3AStateListener
    /* renamed from: onRequestSequenceCreated-DThHKJ0 */
    public void mo532onRequestSequenceCreatedDThHKJ0(long r2) {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (this.initialRequestNumber != null) goto L8;
        this.initialRequestNumber = RequestNumber.m364boximpl(r2);     // Catch: Throwable -> L6
    L8:
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L6
        monitor-exit(this);
    }

    @Override // androidx.camera.camera2.pipe.graph.GraphLoop.Listener
    public void onStopRepeating() {
        this._result.complete(new Result3A(Result3A.Status.Companion.m385getSUBMIT_CANCELLEDJvTi9ms(), null, 2, null));
    }

    @Override // androidx.camera.camera2.pipe.graph.Result3AStateListener
    /* renamed from: update-voP-kFw */
    public boolean mo533updatevoPkFw(long r9, FrameMetadata r11) {
        if (this._result.isCompleted() == false) goto L5;
    L50:
        return true;
    L5:
        if (this._result.isCancelled() == true) goto L50;
        monitor-enter(this);
        RequestNumber r0 = this.initialRequestNumber;     // Catch: Throwable -> L44
        if (r0 != null) goto L11;
    L46:
        monitor-exit(this);
        return false;
    L11:
        if (r9 < r0.m369unboximpl()) goto L46;
        Unit r92 = Unit.INSTANCE;     // Catch: Throwable -> L44
        monitor-exit(this);
        Long r93 = (Long) r11.get(CaptureResult.SENSOR_TIMESTAMP);
        long r3 = r11.mo266getFrameNumberUgla2oM();
        if (r93 != null) goto L18;
    L20:
        Long r10 = this.timestampOfFirstUpdateNs;
        if (this.timeLimitNs == null) goto L29;
        if (r10 == null) goto L29;
        if (r93 == null) goto L29;
        if ((r93.longValue() - r10.longValue()) <= this.timeLimitNs.longValue()) goto L29;
        this._result.complete(new Result3A(Result3A.Status.Companion.m387getTIME_LIMIT_REACHEDJvTi9ms(), r11, null));
        return true;
    L29:
        if (this.frameNumberOfFirstUpdate != null) goto L31;
        this.frameNumberOfFirstUpdate = FrameNumber.m267boximpl(r3);
    L31:
        FrameNumber r94 = this.frameNumberOfFirstUpdate;
        if (r94 == null) goto L40;
        if (this.frameLimit == null) goto L40;
        if ((r3 - r94.m273unboximpl()) <= this.frameLimit.intValue()) goto L40;
        this._result.complete(new Result3A(Result3A.Status.Companion.m383getFRAME_LIMIT_REACHEDJvTi9ms(), r11, null));
        return true;
    L40:
        if (this.exitCondition.invoke(r11).booleanValue() == true) goto L42;
        return false;
    L42:
        this._result.complete(new Result3A(Result3A.Status.Companion.m384getOKJvTi9ms(), r11, null));
        return true;
    L18:
        if (this.timestampOfFirstUpdateNs != null) goto L20;
        this.timestampOfFirstUpdateNs = r93;
    L44:
        th = move-exception;
        throw th;
    }
}
