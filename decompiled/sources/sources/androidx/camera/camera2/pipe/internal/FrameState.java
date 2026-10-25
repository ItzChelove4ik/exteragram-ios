package androidx.camera.camera2.pipe.internal;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0;
import androidx.camera.camera2.pipe.CameraStream;
import androidx.camera.camera2.pipe.FrameId;
import androidx.camera.camera2.pipe.FrameInfo;
import androidx.camera.camera2.pipe.OutputStatus;
import androidx.camera.camera2.pipe.OutputStream;
import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.camera2.pipe.internal.OutputDistributor;
import androidx.camera.camera2.pipe.internal.OutputResult;
import androidx.camera.camera2.pipe.media.OutputImage;
import androidx.camera.camera2.pipe.media.SharedOutputImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicLong;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.BuildConfig;
import org.telegram.messenger.NotificationBadge;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 82\u00020\u0001:\u00059:;<8B-\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\r¢\u0006\u0004\b\u0010\u0010\u000fJ\u0015\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001c\u001a\u0004\b\u001f\u0010\u001eR\u0017\u0010!\u001a\u00020 8\u0006¢\u0006\f\n\u0004\b!\u0010\u001c\u001a\u0004\b\"\u0010\u001eR\u001b\u0010$\u001a\u00060#R\u00020\u00008\u0006¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R!\u0010*\u001a\f\u0012\b\u0012\u00060)R\u00020\u00000(8\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u001a\u00100\u001a\b\u0012\u0004\u0012\u00020/0.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u0014\u00103\u001a\u0002028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00104R\u001a\u00106\u001a\b\u0012\u0004\u0012\u00020\u0001058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107¨\u0006="}, d2 = {"Landroidx/camera/camera2/pipe/internal/FrameState;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/RequestMetadata;", "requestMetadata", "Landroidx/camera/camera2/pipe/FrameNumber;", "frameNumber", "Landroidx/camera/camera2/pipe/CameraTimestamp;", "frameTimestamp", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraStream;", "imageStreams", "<init>", "(Landroidx/camera/camera2/pipe/RequestMetadata;JJLjava/util/Set;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "invokeOnFrameComplete", "()V", "onFrameInfoComplete", "Landroidx/camera/camera2/pipe/StreamId;", "streamId", "onStreamResultComplete-aKI5c8E", "(I)V", "onStreamResultComplete", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroidx/camera/camera2/pipe/RequestMetadata;", "getRequestMetadata", "()Landroidx/camera/camera2/pipe/RequestMetadata;", "J", "getFrameNumber-Ugla2oM", "()J", "getFrameTimestamp-LS1Wq50", "Landroidx/camera/camera2/pipe/FrameId;", "frameId", "getFrameId-OMxQvVY", "Landroidx/camera/camera2/pipe/internal/FrameState$FrameInfoOutput;", "frameInfoOutput", "Landroidx/camera/camera2/pipe/internal/FrameState$FrameInfoOutput;", "getFrameInfoOutput", "()Landroidx/camera/camera2/pipe/internal/FrameState$FrameInfoOutput;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/internal/FrameState$ImageOutput;", "imageOutputs", "Ljava/util/List;", "getImageOutputs", "()Ljava/util/List;", "Lkotlinx/atomicfu/AtomicRef;", "Landroidx/camera/camera2/pipe/internal/FrameState$State;", "state", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/atomicfu/AtomicInt;", "remainingStreamCount", "Lkotlinx/atomicfu/AtomicInt;", "Ljava/util/concurrent/CopyOnWriteArrayList;", "listenerStates", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Companion", "State", "FrameOutput", "FrameInfoOutput", "ImageOutput", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFrameState.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FrameState.kt\nandroidx/camera/camera2/pipe/internal/FrameState\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 AtomicFU.common.kt\nkotlinx/atomicfu/AtomicFU_commonKt\n*L\n1#1,288:1\n1#2:289\n1563#3:290\n1634#3,3:291\n186#4,4:294\n186#4,4:298\n*S KotlinDebug\n*F\n+ 1 FrameState.kt\nandroidx/camera/camera2/pipe/internal/FrameState\n*L\n88#1:290\n88#1:291,3\n113#1:294,4\n138#1:298,4\n*E\n"})
/* loaded from: classes3.dex */
public final class FrameState {
    public static final Companion Companion = null;
    private static final AtomicLong frameIds = null;
    private final long frameId;
    private final FrameInfoOutput frameInfoOutput;
    private final long frameNumber;
    private final long frameTimestamp;
    private final List<ImageOutput> imageOutputs;
    private final CopyOnWriteArrayList<Object> listenerStates;
    private final AtomicInt remainingStreamCount;
    private final RequestMetadata requestMetadata;
    private final AtomicRef<State> state;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0006\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/camera/camera2/pipe/internal/FrameState$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "frameIds", "Lkotlinx/atomicfu/AtomicLong;", "nextFrameId", "Landroidx/camera/camera2/pipe/FrameId;", "nextFrameId-OMxQvVY", "()J", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* renamed from: access$nextFrameId-OMxQvVY, reason: not valid java name */
        public static final /* synthetic */ long m559access$nextFrameIdOMxQvVY(Companion r2) {
            return r2.m560nextFrameIdOMxQvVY();
        }

        /* renamed from: nextFrameId-OMxQvVY, reason: not valid java name */
        private final long m560nextFrameIdOMxQvVY() {
            return FrameId.m263constructorimpl(FrameState.access$getFrameIds$cp().incrementAndGet());
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J=\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u000fH\u0014¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/camera/camera2/pipe/internal/FrameState$FrameInfoOutput;", "Landroidx/camera/camera2/pipe/internal/FrameState$FrameOutput;", "Landroidx/camera/camera2/pipe/FrameInfo;", "Landroidx/camera/camera2/pipe/internal/OutputDistributor$OutputListener;", "<init>", "(Landroidx/camera/camera2/pipe/internal/FrameState;)V", "Landroidx/camera/camera2/pipe/FrameNumber;", "cameraFrameNumber", "Landroidx/camera/camera2/pipe/CameraTimestamp;", "cameraTimestamp", _UrlKt.FRAGMENT_ENCODE_SET, "cameraOutputSequence", "outputNumber", "Landroidx/camera/camera2/pipe/internal/OutputResult;", "outputResult", _UrlKt.FRAGMENT_ENCODE_SET, "onOutputComplete-3ejhThk", "(JJJJLjava/lang/Object;)V", "onOutputComplete", BuildConfig.BUILD_TYPE, "()V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nFrameState.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FrameState.kt\nandroidx/camera/camera2/pipe/internal/FrameState$FrameInfoOutput\n+ 2 OutputResult.kt\nandroidx/camera/camera2/pipe/internal/OutputResult\n+ 3 OutputResult.kt\nandroidx/camera/camera2/pipe/internal/OutputResult$Companion\n*L\n1#1,288:1\n44#2,4:289\n44#2,4:295\n103#3,2:293\n106#3:299\n*S KotlinDebug\n*F\n+ 1 FrameState.kt\nandroidx/camera/camera2/pipe/internal/FrameState$FrameInfoOutput\n*L\n233#1:289,4\n235#1:295,4\n235#1:293,2\n235#1:299\n*E\n"})
    public final class FrameInfoOutput extends FrameOutput<FrameInfo> implements OutputDistributor.OutputListener<FrameInfo> {
        final /* synthetic */ FrameState this$0;

        public FrameInfoOutput(FrameState r1) {
            this.this$0 = r1;
        }

        @Override // androidx.camera.camera2.pipe.internal.OutputDistributor.OutputListener
        /* renamed from: onOutputComplete-3ejhThk, reason: not valid java name */
        public void mo561onOutputComplete3ejhThk(long r1, long r3, long r5, long r7, Object r9) {
            getInternalResult().complete(OutputResult.m572boximpl(r9));
            this.this$0.onFrameInfoComplete();
        }

        @Override // androidx.camera.camera2.pipe.internal.FrameState.FrameOutput
        public void release() {
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b \u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0004J\u000f\u0010\u0007\u001a\u00020\u0005H$¢\u0006\u0004\b\u0007\u0010\u0004R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\nR&\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f0\u000b8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/camera/camera2/pipe/internal/FrameState$FrameOutput;", _UrlKt.FRAGMENT_ENCODE_SET, "T", "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "decrement", BuildConfig.BUILD_TYPE, "Lkotlinx/atomicfu/AtomicInt;", NotificationBadge.NewHtcHomeBadger.COUNT, "Lkotlinx/atomicfu/AtomicInt;", "Lkotlinx/coroutines/CompletableDeferred;", "Landroidx/camera/camera2/pipe/internal/OutputResult;", "internalResult", "Lkotlinx/coroutines/CompletableDeferred;", "getInternalResult", "()Lkotlinx/coroutines/CompletableDeferred;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nFrameState.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FrameState.kt\nandroidx/camera/camera2/pipe/internal/FrameState$FrameOutput\n+ 2 AtomicFU.common.kt\nkotlinx/atomicfu/AtomicFU_commonKt\n+ 3 OutputResult.kt\nandroidx/camera/camera2/pipe/internal/OutputResult$Companion\n+ 4 OutputResult.kt\nandroidx/camera/camera2/pipe/internal/OutputResult\n*L\n1#1,288:1\n382#2,4:289\n79#3:293\n68#3:294\n87#3,11:295\n55#4,5:306\n*S KotlinDebug\n*F\n+ 1 FrameState.kt\nandroidx/camera/camera2/pipe/internal/FrameState$FrameOutput\n*L\n183#1:289,4\n197#1:293\n197#1:294\n209#1:295,11\n209#1:306,5\n*E\n"})
    public static abstract class FrameOutput<T> {
        private final AtomicInt count;
        private final CompletableDeferred<OutputResult<T>> internalResult;

        public FrameOutput() {
            this.count = AtomicFU.atomic(1);
            this.internalResult = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        }

        public final void decrement() {
            if (this.count.decrementAndGet() != 0) goto L6;
            OutputResult.Companion r0 = OutputResult.Companion;
            this.internalResult.complete(OutputResult.m572boximpl(OutputResult.m571access$constructorimpl(OutputStatus.m297boximpl(OutputStatus.Companion.m307getUNAVAILABLEU7r42EA()))));
            release();
            return;
        }

        public final CompletableDeferred<OutputResult<T>> getInternalResult() {
            return this.internalResult;
        }

        public abstract void release();
    }

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00040\u0003B\u001f\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ=\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0014H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0016H\u0014¢\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001c\u001a\u0004\b\u001f\u0010\u001eR\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010 ¨\u0006!"}, d2 = {"Landroidx/camera/camera2/pipe/internal/FrameState$ImageOutput;", "Landroidx/camera/camera2/pipe/internal/FrameState$FrameOutput;", "Landroidx/camera/camera2/pipe/media/SharedOutputImage;", "Landroidx/camera/camera2/pipe/internal/OutputDistributor$OutputListener;", "Landroidx/camera/camera2/pipe/media/OutputImage;", "Landroidx/camera/camera2/pipe/StreamId;", "streamId", "Landroidx/camera/camera2/pipe/OutputId;", "outputId", "Lkotlinx/atomicfu/AtomicInt;", "remainingOutputResults", "<init>", "(Landroidx/camera/camera2/pipe/internal/FrameState;IILkotlinx/atomicfu/AtomicInt;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "Landroidx/camera/camera2/pipe/FrameNumber;", "cameraFrameNumber", "Landroidx/camera/camera2/pipe/CameraTimestamp;", "cameraTimestamp", _UrlKt.FRAGMENT_ENCODE_SET, "cameraOutputSequence", "outputNumber", "Landroidx/camera/camera2/pipe/internal/OutputResult;", "outputResult", _UrlKt.FRAGMENT_ENCODE_SET, "onOutputComplete-3ejhThk", "(JJJJLjava/lang/Object;)V", "onOutputComplete", BuildConfig.BUILD_TYPE, "()V", "I", "getStreamId-ptHMqGs", "()I", "getOutputId-4LaLFng", "Lkotlinx/atomicfu/AtomicInt;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nFrameState.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FrameState.kt\nandroidx/camera/camera2/pipe/internal/FrameState$ImageOutput\n+ 2 OutputResult.kt\nandroidx/camera/camera2/pipe/internal/OutputResult\n+ 3 OutputResult.kt\nandroidx/camera/camera2/pipe/internal/OutputResult$Companion\n*L\n1#1,288:1\n44#2,4:289\n55#2,5:295\n44#2,4:304\n44#2,4:309\n44#2,4:315\n72#3:293\n64#3:294\n79#3:300\n68#3:301\n103#3,2:302\n106#3:308\n103#3,2:313\n106#3:319\n*S KotlinDebug\n*F\n+ 1 FrameState.kt\nandroidx/camera/camera2/pipe/internal/FrameState$ImageOutput\n*L\n254#1:289,4\n261#1:295,5\n273#1:304,4\n275#1:309,4\n278#1:315,4\n257#1:293\n257#1:294\n261#1:300\n261#1:301\n273#1:302,2\n273#1:308\n278#1:313,2\n278#1:319\n*E\n"})
    public final class ImageOutput extends FrameOutput<SharedOutputImage> implements OutputDistributor.OutputListener<OutputImage> {
        private final int outputId;
        private final AtomicInt remainingOutputResults;
        private final int streamId;
        final /* synthetic */ FrameState this$0;

        private ImageOutput(FrameState r1, int r2, int r3, AtomicInt r4) {
            this.this$0 = r1;
            this.streamId = r2;
            this.outputId = r3;
            this.remainingOutputResults = r4;
        }

        public /* synthetic */ ImageOutput(FrameState r1, int r2, int r3, AtomicInt r4, DefaultConstructorMarker r5) {
            this(r1, r2, r3, r4);
        }

        /* renamed from: getOutputId-4LaLFng, reason: not valid java name */
        public final int m562getOutputId4LaLFng() {
            return this.outputId;
        }

        /* renamed from: getStreamId-ptHMqGs, reason: not valid java name */
        public final int m563getStreamIdptHMqGs() {
            return this.streamId;
        }

        @Override // androidx.camera.camera2.pipe.internal.OutputDistributor.OutputListener
        /* renamed from: onOutputComplete-3ejhThk */
        public void mo561onOutputComplete3ejhThk(long r1, long r3, long r5, long r7, Object r9) {
            if (OutputResult.m575getAvailableimpl(r9) == false) goto L5;
            Object r12 = r9;
        L6:
            OutputImage r13 = (OutputImage) r12;
            if (r13 == null) goto L11;
            SharedOutputImage r14 = SharedOutputImage.Companion.from(r13);
            if (getInternalResult().complete(OutputResult.m572boximpl(OutputResult.m571access$constructorimpl(r14))) == true) goto L19;
            UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(r14);
        L19:
            if (this.remainingOutputResults.decrementAndGet() != 0) goto L26;
            Iterator r15 = FrameState.access$getListenerStates$p(this.this$0).iterator();
            if (r15.hasNext() == true) goto L24;
            this.this$0.m558onStreamResultCompleteaKI5c8E(this.streamId);
            return;
        L24:
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r15.next());
            throw null;
        L26:
            return;
        L11:
            CompletableDeferred<OutputResult<SharedOutputImage>> r16 = getInternalResult();
            if (OutputResult.m575getAvailableimpl(r9) == false) goto L14;
            int r32 = OutputStatus.Companion.m303getAVAILABLEU7r42EA();
        L17:
            r16.complete(OutputResult.m572boximpl(OutputResult.m571access$constructorimpl(OutputStatus.m297boximpl(r32))));
            goto L19
        L14:
            if (r9 != null) goto L16;
            r32 = OutputStatus.Companion.m307getUNAVAILABLEU7r42EA();
            goto L17
        L16:
            r32 = ((OutputStatus) r9).m302unboximpl();
            goto L17
        L5:
            r12 = null;
            goto L6
        }

        @Override // androidx.camera.camera2.pipe.internal.FrameState.FrameOutput
        public void release() {
            OutputResult.Companion r0 = OutputResult.Companion;
            CompletableDeferred<OutputResult<SharedOutputImage>> r2 = getInternalResult();
            Object r1 = null;
            if (r2.isCompleted() == true) goto L5;
        L9:
            SharedOutputImage r12 = (SharedOutputImage) r1;
            if (r12 == null) goto L13;
            UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(r12);
            return;
        L13:
            return;
        L5:
            if (r2.isCancelled() == true) goto L9;
            Object r22 = r2.getCompleted().m579unboximpl();
            if (OutputResult.m575getAvailableimpl(r22) == false) goto L9;
            r1 = r22;
            goto L9
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/pipe/internal/FrameState$State;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "STARTED", "FRAME_INFO_COMPLETE", "STREAM_RESULTS_COMPLETE", "COMPLETE", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum State extends Enum<State> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ State[] $VALUES = null;
        public static final State COMPLETE = null;
        public static final State FRAME_INFO_COMPLETE = null;
        public static final State STARTED = null;
        public static final State STREAM_RESULTS_COMPLETE = null;

        private static final /* synthetic */ State[] $values() {
            return new State[]{STARTED, FRAME_INFO_COMPLETE, STREAM_RESULTS_COMPLETE, COMPLETE};
        }

        static {
            STARTED = new State("STARTED", 0);
            FRAME_INFO_COMPLETE = new State("FRAME_INFO_COMPLETE", 1);
            STREAM_RESULTS_COMPLETE = new State("STREAM_RESULTS_COMPLETE", 2);
            COMPLETE = new State("COMPLETE", 3);
            State[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        State(String r1, int r2) {
        }

        public static State valueOf(String r1) {
            return (State) Enum.valueOf(State.class, r1);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[State.values().length];
            r0[State.STARTED.ordinal()] = 1;     // Catch: NoSuchFieldError -> L9
        L13:
            r0[State.FRAME_INFO_COMPLETE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L10
        L19:
            r0[State.STREAM_RESULTS_COMPLETE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L11
        L15:
            r0[State.COMPLETE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L12
        L7:
            $EnumSwitchMapping$0 = r0;
        }
    }

    static {
        Companion = new Companion(null);
        frameIds = AtomicFU.atomic(0);
    }

    private FrameState(RequestMetadata r7, long r8, long r10, Set<CameraStream> r12) {
        this.requestMetadata = r7;
        this.frameNumber = r8;
        this.frameTimestamp = r10;
        this.frameId = Companion.m559access$nextFrameIdOMxQvVY(Companion);
        this.frameInfoOutput = new FrameInfoOutput(this);
        List r82 = CollectionsKt.createListBuilder();
        Iterator<StreamId> r72 = r7.getStreams().keySet().iterator();
    L4:
        if (r72.hasNext() == false) goto L17;
        int r2 = r72.next().m407unboximpl();
        Iterator<T> r9 = r12.iterator();
    L7:
        if (r9.hasNext() == false) goto L11;
        Object r102 = r9.next();
        if (StreamId.m404equalsimpl0(((CameraStream) r102).m241getIdptHMqGs(), r2) == false) goto L7;
    L12:
        CameraStream r103 = (CameraStream) r102;
        if (r103 == null) goto L4;
        List<OutputStream> r92 = r103.getOutputs();
        AtomicInt r4 = AtomicFU.atomic(r92.size());
        int r104 = r92.size();
        int r11 = 0;
    L15:
        if (r11 >= r104) goto L4;
        r82.add(new ImageOutput(this, r2, r92.get(r11).mo311getId4LaLFng(), r4, null));
        r11 = r11 + 1;
        goto L15
    L11:
        r102 = null;
        goto L12
    L17:
        List<ImageOutput> r6 = CollectionsKt.build(r82);
        this.imageOutputs = r6;
        this.state = AtomicFU.atomic(State.STARTED);
        List<ImageOutput> r62 = r6;
        ArrayList r73 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r62, 10));
        Iterator<T> r63 = r62.iterator();
    L19:
        if (r63.hasNext() == false) goto L21;
        r73.add(StreamId.m401boximpl(((ImageOutput) r63.next()).m563getStreamIdptHMqGs()));
        goto L19
    L21:
        this.remainingStreamCount = AtomicFU.atomic(CollectionsKt.distinct(r73).size());
        this.listenerStates = new CopyOnWriteArrayList();
    }

    public /* synthetic */ FrameState(RequestMetadata r1, long r2, long r4, Set r6, DefaultConstructorMarker r7) {
        this(r1, r2, r4, r6);
    }

    public static final /* synthetic */ AtomicLong access$getFrameIds$cp() {
        return frameIds;
    }

    public static final /* synthetic */ CopyOnWriteArrayList access$getListenerStates$p(FrameState r0) {
        return r0.listenerStates;
    }

    private final void invokeOnFrameComplete() {
        Iterator<Object> r1 = this.listenerStates.iterator();
        if (r1.hasNext() == true) goto L5;
        return;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());
        throw null;
    }

    public final FrameInfoOutput getFrameInfoOutput() {
        return this.frameInfoOutput;
    }

    /* renamed from: getFrameNumber-Ugla2oM, reason: not valid java name */
    public final long m557getFrameNumberUgla2oM() {
        return this.frameNumber;
    }

    public final List<ImageOutput> getImageOutputs() {
        return this.imageOutputs;
    }

    public final void onFrameInfoComplete() {
        AtomicRef<State> r0 = this.state;
    L3:
        State r1 = r0.getValue();
        State r2 = r1;
        int r3 = WhenMappings.$EnumSwitchMapping$0[r2.ordinal()];
        if (r3 != 1) goto L6;
        State r22 = State.FRAME_INFO_COMPLETE;
    L12:
        if (r0.compareAndSet(r1, r22) == false) goto L3;
        Iterator<Object> r02 = this.listenerStates.iterator();
        if (r02.hasNext() == false) goto L16;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r02.next());
        throw null;
    L16:
        if (r22 != State.COMPLETE) goto L24;
        invokeOnFrameComplete();
        return;
    L24:
        return;
    L6:
        if (r3 != 3) goto L9;
        r22 = State.COMPLETE;
        goto L12
    L9:
        throw new IllegalStateException("Unexpected frame state for " + this + "! State is " + r2 + ' ');
    }

    /* renamed from: onStreamResultComplete-aKI5c8E, reason: not valid java name */
    public final void m558onStreamResultCompleteaKI5c8E(int r5) {
        if (this.remainingStreamCount.decrementAndGet() == 0) goto L5;
        return;
    L5:
        AtomicRef<State> r52 = this.state;
    L6:
        State r0 = r52.getValue();
        State r1 = r0;
        int r2 = WhenMappings.$EnumSwitchMapping$0[r1.ordinal()];
        if (r2 != 1) goto L9;
        State r12 = State.STREAM_RESULTS_COMPLETE;
    L15:
        if (r52.compareAndSet(r0, r12) == false) goto L6;
        Iterator<Object> r53 = this.listenerStates.iterator();
        if (r53.hasNext() == false) goto L19;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r53.next());
        throw null;
    L19:
        if (r12 != State.COMPLETE) goto L27;
        invokeOnFrameComplete();
        return;
    L27:
        return;
    L9:
        if (r2 != 2) goto L12;
        r12 = State.COMPLETE;
        goto L15
    L12:
        throw new IllegalStateException("Unexpected frame state for " + this + "! State is " + r1 + ' ');
    }

    public String toString() {
        return "Frame-" + FrameId.m264toStringimpl(this.frameId) + '(' + this.frameNumber + '@' + this.frameTimestamp + ')';
    }
}
