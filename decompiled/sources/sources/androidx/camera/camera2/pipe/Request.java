package androidx.camera.camera2.pipe;

import android.hardware.camera2.CaptureRequest;
import androidx.camera.camera2.pipe.Metadata;
import androidx.camera.camera2.pipe.core.Debug;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\b\u0007\u0018\u00002\u00020\u0001:\u0001,Bq\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0018\b\u0002\u0010\u0007\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00010\u0005\u0012\u0018\b\u0002\u0010\t\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u00020\u00010\u0005\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u0002\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0010\u0010\u0011J%\u0010\u0014\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ&\u0010\u001b\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0086\u0002¢\u0006\u0004\b\u001b\u0010\u0015J\u000f\u0010\u001c\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001e\u001a\u0004\b\u001f\u0010 R'\u0010\u0007\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010!\u001a\u0004\b\"\u0010#R'\u0010\t\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u00020\u00010\u00058\u0006¢\u0006\f\n\u0004\b\t\u0010!\u001a\u0004\b$\u0010#R\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u001e\u001a\u0004\b%\u0010 R\u0019\u0010\r\u001a\u0004\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\b\r\u0010&\u001a\u0004\b'\u0010(R\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010)\u001a\u0004\b*\u0010+¨\u0006-"}, d2 = {"Landroidx/camera/camera2/pipe/Request;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "streams", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest$Key;", "parameters", "Landroidx/camera/camera2/pipe/Metadata$Key;", "extras", "Landroidx/camera/camera2/pipe/Request$Listener;", "listeners", "Landroidx/camera/camera2/pipe/RequestTemplate;", "template", "Landroidx/camera/camera2/pipe/InputRequest;", "inputRequest", "<init>", "(Ljava/util/List;Ljava/util/Map;Ljava/util/Map;Ljava/util/List;Landroidx/camera/camera2/pipe/RequestTemplate;Landroidx/camera/camera2/pipe/InputRequest;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "T", PluginsConstants.Settings.KEY, "getUnchecked", "(Landroid/hardware/camera2/CaptureRequest$Key;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "verbose", _UrlKt.FRAGMENT_ENCODE_SET, "toStringInternal", "(Z)Ljava/lang/String;", "get", "toString", "()Ljava/lang/String;", "Ljava/util/List;", "getStreams", "()Ljava/util/List;", "Ljava/util/Map;", "getParameters", "()Ljava/util/Map;", "getExtras", "getListeners", "Landroidx/camera/camera2/pipe/RequestTemplate;", "getTemplate-ejQnlcg", "()Landroidx/camera/camera2/pipe/RequestTemplate;", "Landroidx/camera/camera2/pipe/InputRequest;", "getInputRequest", "()Landroidx/camera/camera2/pipe/InputRequest;", "Listener", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Request {
    private final Map<Metadata.Key<?>, Object> extras;
    private final InputRequest inputRequest;
    private final List<Listener> listeners;
    private final Map<CaptureRequest.Key<?>, Object> parameters;
    private final List<StreamId> streams;
    private final RequestTemplate template;

    @kotlin.Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J'\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bJ'\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J'\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J'\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u001b\u0010\u0018J'\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001eH\u0016¢\u0006\u0004\b\u001f\u0010 J'\u0010!\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\"H\u0016¢\u0006\u0004\b#\u0010\u000bJ'\u0010$\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010%\u001a\u00020&H\u0017¢\u0006\u0004\b'\u0010(J/\u0010$\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010)\u001a\u00020&2\u0006\u0010*\u001a\u00020+H\u0016¢\u0006\u0004\b,\u0010-J\u0010\u0010.\u001a\u00020\u00032\u0006\u0010/\u001a\u000200H\u0016J\u0010\u00101\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u00102\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u00103\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001f\u00104\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b5\u00106ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u00067À\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/pipe/Request$Listener;", _UrlKt.FRAGMENT_ENCODE_SET, "onStarted", _UrlKt.FRAGMENT_ENCODE_SET, "requestMetadata", "Landroidx/camera/camera2/pipe/RequestMetadata;", "frameNumber", "Landroidx/camera/camera2/pipe/FrameNumber;", "timestamp", "Landroidx/camera/camera2/pipe/CameraTimestamp;", "onStarted-uGKBvU4", "(Landroidx/camera/camera2/pipe/RequestMetadata;JJ)V", "onPartialCaptureResult", "captureResult", "Landroidx/camera/camera2/pipe/FrameMetadata;", "onPartialCaptureResult-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/FrameMetadata;)V", "onCaptureProgress", "progress", _UrlKt.FRAGMENT_ENCODE_SET, "onTotalCaptureResult", "totalCaptureResult", "Landroidx/camera/camera2/pipe/FrameInfo;", "onTotalCaptureResult-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/FrameInfo;)V", "onComplete", "result", "onComplete-CcXjc1I", "onFailed", "requestFailure", "Landroidx/camera/camera2/pipe/RequestFailure;", "onFailed-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/RequestFailure;)V", "onReadoutStarted", "Landroidx/camera/camera2/pipe/SensorTimestamp;", "onReadoutStarted-mP9r-9w", "onBufferLost", "stream", "Landroidx/camera/camera2/pipe/StreamId;", "onBufferLost-DlC0U5Y", "(Landroidx/camera/camera2/pipe/RequestMetadata;JI)V", "streamId", "outputId", "Landroidx/camera/camera2/pipe/OutputId;", "onBufferLost-iiEMlm4", "(Landroidx/camera/camera2/pipe/RequestMetadata;JII)V", "onAborted", PluginsConstants.REQUEST, "Landroidx/camera/camera2/pipe/Request;", "onRequestSequenceCreated", "onRequestSequenceSubmitted", "onRequestSequenceAborted", "onRequestSequenceCompleted", "onRequestSequenceCompleted-RuT0dZU", "(Landroidx/camera/camera2/pipe/RequestMetadata;J)V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface Listener {
        default void onAborted(Request r1) {
        }

        @Deprecated(message = "Use the onBufferLost with OutputId.")
        /* renamed from: onBufferLost-DlC0U5Y, reason: not valid java name */
        default void m363onBufferLostDlC0U5Y(RequestMetadata r1, long r2, int r4) {
        }

        /* renamed from: onBufferLost-iiEMlm4 */
        default void mo48onBufferLostiiEMlm4(RequestMetadata r1, long r2, int r4, int r5) {
        }

        default void onCaptureProgress(RequestMetadata r1, int r2) {
        }

        /* renamed from: onComplete-CcXjc1I */
        default void mo13onCompleteCcXjc1I(RequestMetadata r1, long r2, FrameInfo r4) {
        }

        /* renamed from: onFailed-CcXjc1I */
        default void mo14onFailedCcXjc1I(RequestMetadata r1, long r2, RequestFailure r4) {
        }

        /* renamed from: onPartialCaptureResult-CcXjc1I */
        default void mo49onPartialCaptureResultCcXjc1I(RequestMetadata r1, long r2, FrameMetadata r4) {
        }

        /* renamed from: onReadoutStarted-mP9r-9w */
        default void mo50onReadoutStartedmP9r9w(RequestMetadata r1, long r2, long r4) {
        }

        default void onRequestSequenceAborted(RequestMetadata r1) {
        }

        /* renamed from: onRequestSequenceCompleted-RuT0dZU */
        default void mo51onRequestSequenceCompletedRuT0dZU(RequestMetadata r1, long r2) {
        }

        default void onRequestSequenceCreated(RequestMetadata r1) {
        }

        default void onRequestSequenceSubmitted(RequestMetadata r1) {
        }

        /* renamed from: onStarted-uGKBvU4 */
        default void mo52onStarteduGKBvU4(RequestMetadata r1, long r2, long r4) {
        }

        /* renamed from: onTotalCaptureResult-CcXjc1I */
        default void mo15onTotalCaptureResultCcXjc1I(RequestMetadata r1, long r2, FrameInfo r4) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Request(List<StreamId> r1, Map<CaptureRequest.Key<?>, ? extends Object> r2, Map<Metadata.Key<?>, ? extends Object> r3, List<? extends Listener> r4, RequestTemplate r5, InputRequest r6) {
        this.streams = r1;
        this.parameters = r2;
        this.extras = r3;
        this.listeners = r4;
        this.template = r5;
        this.inputRequest = r6;
    }

    public /* synthetic */ Request(List r9, Map r10, Map r11, List r12, RequestTemplate r13, InputRequest r14, int r15, DefaultConstructorMarker r16) {
        if ((r15 & 2) == 0) goto L5;
        r10 = MapsKt.emptyMap();
    L5:
        Map r2 = r10;
        if ((r15 & 4) == 0) goto L8;
        r11 = MapsKt.emptyMap();
    L8:
        Map r3 = r11;
        if ((r15 & 8) == 0) goto L11;
        r12 = CollectionsKt.emptyList();
    L11:
        List r4 = r12;
        if ((r15 & 16) == 0) goto L14;
        RequestTemplate r5 = null;
    L16:
        if ((r15 & 32) == 0) goto L18;
        InputRequest r6 = null;
    L19:
        this(r9, r2, r3, r4, r5, r6, null);
        return;
    L18:
        r6 = r14;
        goto L19
    L14:
        r5 = r13;
        goto L16
    }

    public /* synthetic */ Request(List r1, Map r2, Map r3, List r4, RequestTemplate r5, InputRequest r6, DefaultConstructorMarker r7) {
        this(r1, r2, r3, r4, r5, r6);
    }

    private final <T> T getUnchecked(CaptureRequest.Key<T> r1) {
        return (T) this.parameters.get(r1);
    }

    private final String toStringInternal(boolean r7) {
        RequestTemplate r0 = this.template;
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r0 != null) goto L5;
        String r02 = _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        if (r7 == true) goto L9;
    L11:
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        if (r7 == false) goto L17;
        if (this.extras.isEmpty() == true) goto L17;
        r1 = ", extras=" + Debug.INSTANCE.formatParameterMap(this.extras, 5);
    L17:
        return "Request(streams=" + this.streams + r02 + r3 + r1 + ")@" + Integer.toHexString(hashCode());
    L9:
        if (this.parameters.isEmpty() == true) goto L11;
        r3 = ", parameters=" + Debug.INSTANCE.formatParameterMap(this.parameters, 5);
        goto L12
    L5:
        r02 = ", template=" + RequestTemplate.m376toStringimpl(this.template.m377unboximpl());
        goto L7
    }

    public final <T> T get(CaptureRequest.Key<T> r1) {
        return (T) getUnchecked(r1);
    }

    public final Map<Metadata.Key<?>, Object> getExtras() {
        return this.extras;
    }

    public final InputRequest getInputRequest() {
        return this.inputRequest;
    }

    public final List<Listener> getListeners() {
        return this.listeners;
    }

    public final Map<CaptureRequest.Key<?>, Object> getParameters() {
        return this.parameters;
    }

    public final List<StreamId> getStreams() {
        return this.streams;
    }

    /* renamed from: getTemplate-ejQnlcg, reason: not valid java name */
    public final RequestTemplate m362getTemplateejQnlcg() {
        return this.template;
    }

    public String toString() {
        return toStringInternal(false);
    }
}
