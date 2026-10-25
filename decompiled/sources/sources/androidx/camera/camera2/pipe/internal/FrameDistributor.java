package androidx.camera.camera2.pipe.internal;

import android.os.Build;
import androidx.camera.camera2.pipe.CameraStream;
import androidx.camera.camera2.pipe.FrameInfo;
import androidx.camera.camera2.pipe.FrameReference;
import androidx.camera.camera2.pipe.ImageSourceConfig;
import androidx.camera.camera2.pipe.OutputId;
import androidx.camera.camera2.pipe.OutputStatus;
import androidx.camera.camera2.pipe.OutputStream;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestFailure;
import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.camera2.pipe.graph.StreamGraphImpl;
import androidx.camera.camera2.pipe.internal.FrameState;
import androidx.camera.camera2.pipe.internal.OutputResult;
import androidx.camera.camera2.pipe.media.ClosingFinalizer;
import androidx.camera.camera2.pipe.media.ImageSource;
import androidx.camera.camera2.pipe.media.NoOpFinalizer;
import androidx.camera.camera2.pipe.media.OutputImage;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 C2\u00060\u0001j\u0002`\u00022\u00020\u0003:\u0002DCB'\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ'\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J'\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ/\u0010#\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u001fH\u0016¢\u0006\u0004\b!\u0010\"J'\u0010(\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010%\u001a\u00020$H\u0016¢\u0006\u0004\b&\u0010'J\u0017\u0010+\u001a\u00020\u00142\u0006\u0010*\u001a\u00020)H\u0016¢\u0006\u0004\b+\u0010,J\u000f\u0010-\u001a\u00020\u0014H\u0016¢\u0006\u0004\b-\u0010.R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010/R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u00100R\u001a\u00102\u001a\b\u0012\u0004\u0012\u00020\u0018018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R2\u00106\u001a \u0012\u0004\u0012\u00020\u001d\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002050104048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107R\u001a\u0010:\u001a\b\u0012\u0004\u0012\u000209088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\"\u0010=\u001a\u00020<8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@\"\u0004\bA\u0010B¨\u0006E"}, d2 = {"Landroidx/camera/camera2/pipe/internal/FrameDistributor;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "Landroidx/camera/camera2/pipe/Request$Listener;", "Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;", "streamGraphImpl", "Landroidx/camera/camera2/pipe/internal/FrameCaptureQueue;", "frameCaptureQueue", _UrlKt.FRAGMENT_ENCODE_SET, "isCameraTimebaseRealtime", _UrlKt.FRAGMENT_ENCODE_SET, "realtimeToMonotonicOffsetNs", "<init>", "(Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;Landroidx/camera/camera2/pipe/internal/FrameCaptureQueue;ZJ)V", "Landroidx/camera/camera2/pipe/RequestMetadata;", "requestMetadata", "Landroidx/camera/camera2/pipe/FrameNumber;", "frameNumber", "Landroidx/camera/camera2/pipe/CameraTimestamp;", "timestamp", _UrlKt.FRAGMENT_ENCODE_SET, "onStarted-uGKBvU4", "(Landroidx/camera/camera2/pipe/RequestMetadata;JJ)V", "onStarted", "Landroidx/camera/camera2/pipe/FrameInfo;", "result", "onComplete-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/FrameInfo;)V", "onComplete", "Landroidx/camera/camera2/pipe/StreamId;", "streamId", "Landroidx/camera/camera2/pipe/OutputId;", "outputId", "onBufferLost-iiEMlm4", "(Landroidx/camera/camera2/pipe/RequestMetadata;JII)V", "onBufferLost", "Landroidx/camera/camera2/pipe/RequestFailure;", "requestFailure", "onFailed-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/RequestFailure;)V", "onFailed", "Landroidx/camera/camera2/pipe/Request;", PluginsConstants.REQUEST, "onAborted", "(Landroidx/camera/camera2/pipe/Request;)V", "close", "()V", "Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;", "Landroidx/camera/camera2/pipe/internal/FrameCaptureQueue;", "Landroidx/camera/camera2/pipe/internal/OutputDistributor;", "frameInfoDistributor", "Landroidx/camera/camera2/pipe/internal/OutputDistributor;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/media/OutputImage;", "imageDistributors", "Ljava/util/Map;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraStream;", "imageStreams", "Ljava/util/Set;", "Landroidx/camera/camera2/pipe/internal/FrameDistributor$FrameStartedListener;", "frameStartedListener", "Landroidx/camera/camera2/pipe/internal/FrameDistributor$FrameStartedListener;", "getFrameStartedListener", "()Landroidx/camera/camera2/pipe/internal/FrameDistributor$FrameStartedListener;", "setFrameStartedListener", "(Landroidx/camera/camera2/pipe/internal/FrameDistributor$FrameStartedListener;)V", "Companion", "FrameStartedListener", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFrameDistributor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FrameDistributor.kt\nandroidx/camera/camera2/pipe/internal/FrameDistributor\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 OutputResult.kt\nandroidx/camera/camera2/pipe/internal/OutputResult$Companion\n*L\n1#1,409:1\n465#2:410\n415#2:411\n1252#3,4:412\n1563#3:416\n1634#3,3:417\n64#4:420\n68#4:421\n68#4:422\n*S KotlinDebug\n*F\n+ 1 FrameDistributor.kt\nandroidx/camera/camera2/pipe/internal/FrameDistributor\n*L\n101#1:410\n101#1:411\n101#1:412,4\n162#1:416\n162#1:417,3\n236#1:420\n276#1:421\n153#1:422\n*E\n"})
/* loaded from: classes3.dex */
public final class FrameDistributor implements AutoCloseable, Request.Listener {
    public static final Companion Companion = null;
    private final FrameCaptureQueue frameCaptureQueue;
    private final OutputDistributor<FrameInfo> frameInfoDistributor;
    private FrameStartedListener frameStartedListener;
    private final Map<StreamId, Map<OutputId, OutputDistributor<OutputImage>>> imageDistributors;
    private final Set<CameraStream> imageStreams;
    private final StreamGraphImpl streamGraphImpl;

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J7\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0003¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Landroidx/camera/camera2/pipe/internal/FrameDistributor$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/pipe/StreamId;", "cameraStreamId", "Landroidx/camera/camera2/pipe/CameraStream$Config;", "cameraStreamConfig", "Landroidx/camera/camera2/pipe/ImageSourceConfig;", "imageSourceConfig", _UrlKt.FRAGMENT_ENCODE_SET, "isCameraTimebaseRealtime", _UrlKt.FRAGMENT_ENCODE_SET, "realtimeToMonotonicOffsetNs", "Landroidx/camera/camera2/pipe/internal/OutputMatcher;", "selectTimestampMatcher-5y4XNsE", "(ILandroidx/camera/camera2/pipe/CameraStream$Config;Landroidx/camera/camera2/pipe/ImageSourceConfig;ZJ)Landroidx/camera/camera2/pipe/internal/OutputMatcher;", "selectTimestampMatcher", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nFrameDistributor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FrameDistributor.kt\nandroidx/camera/camera2/pipe/internal/FrameDistributor$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,409:1\n392#1,2:410\n394#1,2:414\n396#1:417\n399#1,3:418\n404#1,3:421\n380#1,2:424\n382#1:428\n383#1,5:430\n388#1:437\n389#1:439\n380#1,2:442\n382#1:446\n383#1:448\n1740#2,2:412\n1742#2:416\n1761#2,2:426\n1763#2:429\n1761#2,2:435\n1763#2:438\n1761#2,2:444\n1763#2:447\n1761#2,3:451\n1761#2,3:454\n1740#2,3:457\n50#3,2:440\n50#3,2:449\n*S KotlinDebug\n*F\n+ 1 FrameDistributor.kt\nandroidx/camera/camera2/pipe/internal/FrameDistributor$Companion\n*L\n347#1:410,2\n347#1:414,2\n347#1:417\n348#1:418,3\n349#1:421,3\n353#1:424,2\n353#1:428\n353#1:430,5\n353#1:437\n353#1:439\n366#1:442,2\n366#1:446\n366#1:448\n347#1:412,2\n347#1:416\n353#1:426,2\n353#1:429\n353#1:435,2\n353#1:438\n366#1:444,2\n366#1:447\n381#1:451,3\n387#1:454,3\n393#1:457,3\n357#1:440,2\n367#1:449,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* renamed from: access$selectTimestampMatcher-5y4XNsE, reason: not valid java name */
        public static final /* synthetic */ OutputMatcher m555access$selectTimestampMatcher5y4XNsE(Companion r0, int r1, CameraStream.Config r2, ImageSourceConfig r3, boolean r4, long r5) {
            return r0.m556selectTimestampMatcher5y4XNsE(r1, r2, r3, r4, r5);
        }

        @JvmStatic
        /* renamed from: selectTimestampMatcher-5y4XNsE, reason: not valid java name */
        private final OutputMatcher m556selectTimestampMatcher5y4XNsE(int r1, CameraStream.Config r2, ImageSourceConfig r3, boolean r4, long r5) {
            if (r4 == false) goto L23;
            if (Build.VERSION.SDK_INT < 33) goto L15;
            List<OutputStream.Config> r12 = r2.getOutputs();
            if ((r12 instanceof Collection) == true) goto L9;
        L11:
            Iterator<T> r13 = r12.iterator();
        L13:
            if (r13.hasNext() == false) goto L15;
            ((OutputStream.Config) r13.next()).m322getTimestampBasepcPfPbY();
            goto L13
        L9:
            if (r12.isEmpty() == false) goto L11;
        L15:
            int r14 = Build.VERSION.SDK_INT;
            if (r14 >= 29) goto L21;
            if (r14 < 33) goto L19;
            throw null;
        L19:
            return OutputMatcher.Companion.getEXACT();
        L21:
            throw null;
        L23:
            if (Build.VERSION.SDK_INT < 33) goto L34;
            List<OutputStream.Config> r0 = r2.getOutputs();
            if ((r0 instanceof Collection) == true) goto L27;
        L29:
            Iterator<T> r02 = r0.iterator();
        L31:
            if (r02.hasNext() == false) goto L34;
            ((OutputStream.Config) r02.next()).m322getTimestampBasepcPfPbY();
            OutputStream.TimestampBase.Companion.m361getTIMESTAMP_BASE_REALTIME6HVI0MA();
            goto L31
        L27:
            if (r0.isEmpty() == false) goto L29;
        L34:
            return OutputMatcher.Companion.getEXACT();
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/internal/FrameDistributor$FrameStartedListener;", _UrlKt.FRAGMENT_ENCODE_SET, "onFrameStarted", _UrlKt.FRAGMENT_ENCODE_SET, "frameReference", "Landroidx/camera/camera2/pipe/FrameReference;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface FrameStartedListener {
        void onFrameStarted(FrameReference r1);
    }

    public static void $r8$lambda$rWkGfvD7xGINf9EVDvFJlvgalyk(FrameReference r0) {
    }

    static {
        Companion = new Companion(null);
    }

    public FrameDistributor(StreamGraphImpl r19, FrameCaptureQueue r20, boolean r21, long r22) {
        this.streamGraphImpl = r19;
        this.frameCaptureQueue = r20;
        this.frameInfoDistributor = new OutputDistributor(0, NoOpFinalizer.INSTANCE, OutputMatcher.Companion.getEXACT(), 1, null);
        Map<StreamId, ImageSource> r1 = r19.getImageSourceMap$camera_camera2_pipe();
        LinkedHashMap r2 = new LinkedHashMap(MapsKt.mapCapacity(r1.size()));
        Iterator<T> r12 = r1.entrySet().iterator();
    L4:
        if (r12.hasNext() == false) goto L14;
        Map.Entry r3 = (Map.Entry) r12.next();
        Object r6 = r3.getKey();
        int r9 = ((StreamId) r3.getKey()).m407unboximpl();
        ImageSource r32 = (ImageSource) r3.getValue();
        CameraStream r7 = this.streamGraphImpl.m399getaKI5c8E(r9);
        if (r7 == null) goto L12;
        CameraStream.Config r10 = this.streamGraphImpl.m539getCameraStreamConfigaKI5c8E(r9);
        r10.getImageSourceConfig();
        OutputMatcher r15 = Companion.m555access$selectTimestampMatcher5y4XNsE(Companion, r9, r10, null, r21, r22);
        Map r4 = MapsKt.createMapBuilder();
        Iterator<OutputStream> r5 = r7.getOutputs().iterator();
    L9:
        if (r5.hasNext() == false) goto L11;
        OutputStream r8 = r5.next();
        OutputDistributor r122 = new OutputDistributor(0, ClosingFinalizer.INSTANCE, r15, 1, null);
        r4.put(OutputId.m290boximpl(r8.mo311getId4LaLFng()), r122);
        goto L9
    L11:
        Map r42 = MapsKt.build(r4);
        r32.setImageListener(new FrameDistributor$imageDistributors$1$1(r42, r32));
        r32.setExpectedOutputsListener(new FrameDistributor$$ExternalSyntheticLambda0(r7, r42));
        r2.put(r6, r42);
        goto L4
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        throw null;
    L14:
        this.imageDistributors = r2;
        Set r13 = r2.keySet();
        ArrayList r23 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r13, 10));
        Iterator r14 = r13.iterator();
    L16:
        if (r14.hasNext() == false) goto L22;
        int r33 = ((StreamId) r14.next()).m407unboximpl();
        CameraStream r34 = this.streamGraphImpl.m399getaKI5c8E(r33);
        if (r34 == null) goto L20;
        r23.add(r34);
        goto L16
    L20:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        throw null;
    L22:
        this.imageStreams = CollectionsKt.toSet(r23);
        this.frameStartedListener = new FrameDistributor$$ExternalSyntheticLambda1();
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.frameCaptureQueue.close();
        this.frameInfoDistributor.close();
        Iterator<Map<OutputId, OutputDistributor<OutputImage>>> r2 = this.imageDistributors.values().iterator();
    L4:
        if (r2.hasNext() == false) goto L9;
        Iterator<OutputDistributor<OutputImage>> r0 = r2.next().values().iterator();
    L7:
        if (r0.hasNext() == false) goto L4;
        r0.next().close();
        goto L7
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    public void onAborted(Request r1) {
        this.frameCaptureQueue.remove(r1);
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onBufferLost-iiEMlm4 */
    public void mo48onBufferLostiiEMlm4(RequestMetadata r2, long r3, int r5, int r6) {
        Map<OutputId, OutputDistributor<OutputImage>> r22 = this.imageDistributors.get(StreamId.m401boximpl(r5));
        if (r22 == null) goto L13;
        CameraStream.Config r1 = this.streamGraphImpl.m539getCameraStreamConfigaKI5c8E(r5);
        if (r1 == null) goto L16;
        r1.getImageSourceConfig();
        if (r22.containsKey(OutputId.m290boximpl(r6)) == false) goto L14;
        Iterator<OutputDistributor<OutputImage>> r12 = r22.values().iterator();
    L11:
        if (r12.hasNext() == false) goto L19;
        r12.next().m565onOutputFailureVw7M1qk(r3);
        goto L11
    L19:
        return;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return;
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onComplete-CcXjc1I */
    public void mo13onCompleteCcXjc1I(RequestMetadata r1, long r2, FrameInfo r4) {
        this.frameInfoDistributor.m566onOutputResultDvZWqE8(r2, OutputResult.m571access$constructorimpl(r4));
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onFailed-CcXjc1I */
    public void mo14onFailedCcXjc1I(RequestMetadata r3, long r4, RequestFailure r6) {
        OutputDistributor<FrameInfo> r0 = this.frameInfoDistributor;
        OutputResult.Companion r1 = OutputResult.Companion;
        r0.m566onOutputResultDvZWqE8(r4, OutputResult.m571access$constructorimpl(OutputStatus.m297boximpl(OutputStatus.Companion.m305getERROR_OUTPUT_FAILEDU7r42EA())));
        if (r6.getWasImageCaptured() == true) goto L14;
        Iterator<StreamId> r32 = r3.getStreams().keySet().iterator();
    L6:
        if (r32.hasNext() == false) goto L20;
        int r62 = r32.next().m407unboximpl();
        Map<OutputId, OutputDistributor<OutputImage>> r63 = this.imageDistributors.get(StreamId.m401boximpl(r62));
        if (r63 == null) goto L6;
        Iterator<OutputDistributor<OutputImage>> r64 = r63.values().iterator();
    L12:
        if (r64.hasNext() == false) goto L6;
        r64.next().m565onOutputFailureVw7M1qk(r4);
        goto L12
    L20:
        return;
    }

    @Override // androidx.camera.camera2.pipe.Request.Listener
    /* renamed from: onStarted-uGKBvU4 */
    public void mo52onStarteduGKBvU4(RequestMetadata r11, long r12, long r14) {
        FrameState r0 = new FrameState(r11, r12, r14, this.imageStreams, null);
        this.frameInfoDistributor.m567onOutputStartedqGubWw0(r12, r14, r12, r0.getFrameInfoOutput());
        int r112 = r0.getImageOutputs().size();
        int r122 = 0;
    L3:
        if (r122 >= r112) goto L16;
        FrameState.ImageOutput r9 = r0.getImageOutputs().get(r122);
        Map<OutputId, OutputDistributor<OutputImage>> r13 = this.imageDistributors.get(StreamId.m401boximpl(r9.m563getStreamIdptHMqGs()));
        if (r13 == null) goto L14;
        OutputDistributor<OutputImage> r132 = r13.get(OutputId.m290boximpl(r9.m562getOutputId4LaLFng()));
        if (r132 == null) goto L12;
        OutputDistributor<OutputImage> r2 = r132;
        r2.m567onOutputStartedqGubWw0(r12, r14, r14, r9);
        if (r11.getStreams().keySet().contains(StreamId.m401boximpl(r9.m563getStreamIdptHMqGs())) == true) goto L11;
        r2.m565onOutputFailureVw7M1qk(r0.m557getFrameNumberUgla2oM());
    L11:
        r122 = r122 + 1;
        goto L3
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return;
    L16:
        FrameImpl r113 = new FrameImpl(r0, null, 2, null);
        this.frameStartedListener.onFrameStarted(r113);
        if (r11.getRepeating() == true) goto L19;
        this.frameCaptureQueue.remove(r11.getRequest());
    L19:
        r113.close();
    }
}
