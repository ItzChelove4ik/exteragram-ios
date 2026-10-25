package androidx.camera.camera2.pipe;

import androidx.camera.camera2.pipe.CaptureSequence;
import androidx.camera.camera2.pipe.Request;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\bg\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0001*\u000e\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u0004Jw\u0010\u0005\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0012\u0010\u000b\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\f2\u0012\u0010\r\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\f2\u0012\u0010\u000e\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\f2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\tH&¢\u0006\u0002\u0010\u0013J\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0019H&J\b\u0010\u001a\u001a\u00020\u0019H&J\u000e\u0010\u001b\u001a\u00020\u0019H¦@¢\u0006\u0002\u0010\u001cø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001dÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/CaptureSequenceProcessor;", "TCaptureRequest", "TCaptureSequence", "Landroidx/camera/camera2/pipe/CaptureSequence;", _UrlKt.FRAGMENT_ENCODE_SET, "build", "isRepeating", _UrlKt.FRAGMENT_ENCODE_SET, "requests", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/Request;", "defaultParameters", _UrlKt.FRAGMENT_ENCODE_SET, "graphParameters", "requiredParameters", "sequenceListener", "Landroidx/camera/camera2/pipe/CaptureSequence$CaptureSequenceListener;", "listeners", "Landroidx/camera/camera2/pipe/Request$Listener;", "(ZLjava/util/List;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Landroidx/camera/camera2/pipe/CaptureSequence$CaptureSequenceListener;Ljava/util/List;)Landroidx/camera/camera2/pipe/CaptureSequence;", "submit", _UrlKt.FRAGMENT_ENCODE_SET, "captureSequence", "(Landroidx/camera/camera2/pipe/CaptureSequence;)Ljava/lang/Integer;", "abortCaptures", _UrlKt.FRAGMENT_ENCODE_SET, "stopRepeating", "shutdown", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CaptureSequenceProcessor<TCaptureRequest, TCaptureSequence extends CaptureSequence<? extends TCaptureRequest>> {
    void abortCaptures();

    TCaptureSequence build(boolean r1, List<Request> r2, Map<?, ? extends Object> r3, Map<?, ? extends Object> r4, Map<?, ? extends Object> r5, CaptureSequence.CaptureSequenceListener r6, List<? extends Request.Listener> r7);

    Object shutdown(Continuation<? super Unit> r1);

    void stopRepeating();

    Integer submit(TCaptureSequence r1);
}
