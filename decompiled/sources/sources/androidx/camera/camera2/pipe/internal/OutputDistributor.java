package androidx.camera.camera2.pipe.internal;

import androidx.camera.camera2.pipe.CameraTimestamp;
import androidx.camera.camera2.pipe.FrameNumber;
import androidx.camera.camera2.pipe.OutputStatus;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.internal.OutputResult;
import androidx.camera.camera2.pipe.media.Finalizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u0007\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00060\u0002j\u0002`\u0003:\u000278B'\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ3\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00112\f\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000%¢\u0006\u0004\b&\u0010'J#\u0010(\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020\u00112\f\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000\u001d¢\u0006\u0004\b+\u0010,J\u0015\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020\u0014¢\u0006\u0004\b/\u00100J\"\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001a022\f\u00103\u001a\b\u0012\u0004\u0012\u00028\u00000\u001aH\u0003J,\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001a022\u0006\u00104\u001a\u00020\u000f2\u0006\u00105\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u0011H\u0002J\b\u00106\u001a\u00020\u001fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00020\u000f8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\u00118\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00020\u00118\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00148\u0002@\u0002X\u0083\u000e¢\u0006\u0004\n\u0002\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\u00118\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0017\u001a\u00020\u00118\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u001b\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001d0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Landroidx/camera/camera2/pipe/internal/OutputDistributor;", "T", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "maximumCachedOutputs", _UrlKt.FRAGMENT_ENCODE_SET, "outputFinalizer", "Landroidx/camera/camera2/pipe/media/Finalizer;", "outputMatcher", "Landroidx/camera/camera2/pipe/internal/OutputMatcher;", "<init>", "(ILandroidx/camera/camera2/pipe/media/Finalizer;Landroidx/camera/camera2/pipe/internal/OutputMatcher;)V", "lock", _UrlKt.FRAGMENT_ENCODE_SET, "closed", _UrlKt.FRAGMENT_ENCODE_SET, "cameraOutputSequenceNumbers", _UrlKt.FRAGMENT_ENCODE_SET, "newestCameraOutputNumber", "newestFrameNumber", "Landroidx/camera/camera2/pipe/FrameNumber;", "J", "lastFailedFrameNumber", "lastFailedCameraOutputNumber", "startedOutputs", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/internal/OutputDistributor$StartedOutput;", "availableOutputs", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/internal/OutputResult;", "onOutputStarted", _UrlKt.FRAGMENT_ENCODE_SET, "cameraFrameNumber", "cameraTimestamp", "Landroidx/camera/camera2/pipe/CameraTimestamp;", "cameraOutputNumber", "outputListener", "Landroidx/camera/camera2/pipe/internal/OutputDistributor$OutputListener;", "onOutputStarted-qGubWw0", "(JJJLandroidx/camera/camera2/pipe/internal/OutputDistributor$OutputListener;)V", "onOutputResult", "outputNumber", "outputResult", "onOutputResult-DvZWqE8", "(JLjava/lang/Object;)V", "onOutputFailure", "frameNumber", "onOutputFailure-Vw7M1qk", "(J)V", "removeOutputsOlderThan", _UrlKt.FRAGMENT_ENCODE_SET, "output", "isOutOfOrder", "cameraOutputSequence", "close", "OutputListener", "StartedOutput", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nOutputDistributor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OutputDistributor.kt\nandroidx/camera/camera2/pipe/internal/OutputDistributor\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 OutputResult.kt\nandroidx/camera/camera2/pipe/internal/OutputResult\n+ 6 OutputResult.kt\nandroidx/camera/camera2/pipe/internal/OutputResult$Companion\n*L\n1#1,398:1\n295#2,2:399\n295#2,2:403\n295#2,2:406\n1869#2,2:408\n295#2,2:416\n1869#2,2:422\n669#2,11:424\n774#2:435\n865#2,2:436\n71#3,2:401\n1#4:405\n44#5,4:410\n44#5,4:418\n44#5,4:438\n68#6:414\n68#6:415\n*S KotlinDebug\n*F\n+ 1 OutputDistributor.kt\nandroidx/camera/camera2/pipe/internal/OutputDistributor\n*L\n133#1:399,2\n151#1:403,2\n179#1:406,2\n211#1:408,2\n260#1:416,2\n291#1:422,2\n304#1:424,11\n333#1:435\n333#1:436,2\n135#1:401,2\n212#1:410,4\n290#1:418,4\n359#1:438,4\n217#1:414\n219#1:415\n*E\n"})
/* loaded from: classes3.dex */
public final class OutputDistributor<T> implements AutoCloseable {
    private final Map<Long, OutputResult<T>> availableOutputs;
    private long cameraOutputSequenceNumbers;
    private boolean closed;
    private long lastFailedCameraOutputNumber;
    private long lastFailedFrameNumber;
    private final Object lock;
    private final int maximumCachedOutputs;
    private long newestCameraOutputNumber;
    private long newestFrameNumber;
    private final Finalizer<T> outputFinalizer;
    private final OutputMatcher outputMatcher;
    private final List<StartedOutput<T>> startedOutputs;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002J=\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\rH&¢\u0006\u0004\b\u000e\u0010\u000fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/internal/OutputDistributor$OutputListener;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "onOutputComplete", _UrlKt.FRAGMENT_ENCODE_SET, "cameraFrameNumber", "Landroidx/camera/camera2/pipe/FrameNumber;", "cameraTimestamp", "Landroidx/camera/camera2/pipe/CameraTimestamp;", "cameraOutputSequence", _UrlKt.FRAGMENT_ENCODE_SET, "outputNumber", "outputResult", "Landroidx/camera/camera2/pipe/internal/OutputResult;", "onOutputComplete-3ejhThk", "(JJJJLjava/lang/Object;)V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface OutputListener<T> {
        /* renamed from: onOutputComplete-3ejhThk */
        void mo561onOutputComplete3ejhThk(long r1, long r3, long r5, long r7, Object r9);
    }

    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\b\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B=\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0013\u0010\u0014J#\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\t2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00010\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001d\u001a\u00020\u001cHÖ\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010 \u001a\u00020\u001fHÖ\u0001¢\u0006\u0004\b \u0010!J\u001a\u0010#\u001a\u00020\u00032\b\u0010\"\u001a\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b#\u0010$R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010%\u001a\u0004\b\u0004\u0010&R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010'\u001a\u0004\b(\u0010)R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010'\u001a\u0004\b*\u0010)R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010'\u001a\u0004\b+\u0010)R\u0017\u0010\u000b\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010'\u001a\u0004\b,\u0010)R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010-R\u0014\u0010/\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100¨\u00061"}, d2 = {"Landroidx/camera/camera2/pipe/internal/OutputDistributor$StartedOutput;", "T", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "isOutOfOrder", "Landroidx/camera/camera2/pipe/FrameNumber;", "cameraFrameNumber", "Landroidx/camera/camera2/pipe/CameraTimestamp;", "cameraTimestamp", _UrlKt.FRAGMENT_ENCODE_SET, "cameraOutputSequence", "cameraOutputNumber", "Landroidx/camera/camera2/pipe/internal/OutputDistributor$OutputListener;", "outputListener", "<init>", "(ZJJJJLandroidx/camera/camera2/pipe/internal/OutputDistributor$OutputListener;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "Landroidx/camera/camera2/pipe/OutputStatus;", "failureReason", _UrlKt.FRAGMENT_ENCODE_SET, "completeWithFailure-tXNfJfc", "(I)V", "completeWithFailure", "outputNumber", "Landroidx/camera/camera2/pipe/internal/OutputResult;", "outputResult", "completeWith-DvZWqE8", "(JLjava/lang/Object;)V", "completeWith", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Z", "()Z", "J", "getCameraFrameNumber-Ugla2oM", "()J", "getCameraTimestamp-LS1Wq50", "getCameraOutputSequence", "getCameraOutputNumber", "Landroidx/camera/camera2/pipe/internal/OutputDistributor$OutputListener;", "Lkotlinx/atomicfu/AtomicBoolean;", "complete", "Lkotlinx/atomicfu/AtomicBoolean;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nOutputDistributor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OutputDistributor.kt\nandroidx/camera/camera2/pipe/internal/OutputDistributor$StartedOutput\n+ 2 OutputResult.kt\nandroidx/camera/camera2/pipe/internal/OutputResult$Companion\n*L\n1#1,398:1\n68#2:399\n*S KotlinDebug\n*F\n+ 1 OutputDistributor.kt\nandroidx/camera/camera2/pipe/internal/OutputDistributor$StartedOutput\n*L\n381#1:399\n*E\n"})
    public static final class StartedOutput<T> {
        private final long cameraFrameNumber;
        private final long cameraOutputNumber;
        private final long cameraOutputSequence;
        private final long cameraTimestamp;
        private final AtomicBoolean complete;
        private final boolean isOutOfOrder;
        private final OutputListener<T> outputListener;

        private StartedOutput(boolean r1, long r2, long r4, long r6, long r8, OutputListener<T> r10) {
            this.isOutOfOrder = r1;
            this.cameraFrameNumber = r2;
            this.cameraTimestamp = r4;
            this.cameraOutputSequence = r6;
            this.cameraOutputNumber = r8;
            this.outputListener = r10;
            this.complete = AtomicFU.atomic(false);
        }

        public /* synthetic */ StartedOutput(boolean r1, long r2, long r4, long r6, long r8, OutputListener r10, DefaultConstructorMarker r11) {
            this(r1, r2, r4, r6, r8, r10);
        }

        /* renamed from: completeWith-DvZWqE8, reason: not valid java name */
        public final void m568completeWithDvZWqE8(long r12, Object r14) {
            if (this.complete.compareAndSet(false, true) == false) goto L7;
            this.outputListener.mo561onOutputComplete3ejhThk(this.cameraFrameNumber, this.cameraTimestamp, this.cameraOutputSequence, r12, r14);
            return;
        L7:
            throw new IllegalStateException(("Output " + this.cameraOutputSequence + " at " + FrameNumber.m272toStringimpl(this.cameraFrameNumber) + " for " + r12 + " was completed multiple times!").toString());
        }

        /* renamed from: completeWithFailure-tXNfJfc, reason: not valid java name */
        public final void m569completeWithFailuretXNfJfc(int r3) {
            OutputResult.Companion r0 = OutputResult.Companion;
            m568completeWithDvZWqE8(-1, OutputResult.m571access$constructorimpl(OutputStatus.m297boximpl(r3)));
        }

        public boolean equals(Object r8) {
            if (this != r8) goto L6;
            return true;
        L6:
            if ((r8 instanceof StartedOutput) == true) goto L8;
            return false;
        L8:
            StartedOutput r82 = (StartedOutput) r8;
            if (this.isOutOfOrder == r82.isOutOfOrder) goto L12;
            return false;
        L12:
            if (FrameNumber.m270equalsimpl0(this.cameraFrameNumber, r82.cameraFrameNumber) == true) goto L15;
            return false;
        L15:
            if (CameraTimestamp.m243equalsimpl0(this.cameraTimestamp, r82.cameraTimestamp) == true) goto L18;
            return false;
        L18:
            if (this.cameraOutputSequence == r82.cameraOutputSequence) goto L21;
            return false;
        L21:
            if (this.cameraOutputNumber == r82.cameraOutputNumber) goto L24;
            return false;
        L24:
            if (Intrinsics.areEqual(this.outputListener, r82.outputListener) == true) goto L26;
            return false;
        L26:
            return true;
        }

        /* renamed from: getCameraFrameNumber-Ugla2oM, reason: not valid java name */
        public final long m570getCameraFrameNumberUgla2oM() {
            return this.cameraFrameNumber;
        }

        public final long getCameraOutputNumber() {
            return this.cameraOutputNumber;
        }

        public final long getCameraOutputSequence() {
            return this.cameraOutputSequence;
        }

        public int hashCode() {
            return (((((((((Boolean.hashCode(this.isOutOfOrder) * 31) + FrameNumber.m271hashCodeimpl(this.cameraFrameNumber)) * 31) + CameraTimestamp.m244hashCodeimpl(this.cameraTimestamp)) * 31) + Long.hashCode(this.cameraOutputSequence)) * 31) + Long.hashCode(this.cameraOutputNumber)) * 31) + this.outputListener.hashCode();
        }

        public final boolean isOutOfOrder() {
            return this.isOutOfOrder;
        }

        public String toString() {
            return "StartedOutput(isOutOfOrder=" + this.isOutOfOrder + ", cameraFrameNumber=" + FrameNumber.m272toStringimpl(this.cameraFrameNumber) + ", cameraTimestamp=" + CameraTimestamp.m245toStringimpl(this.cameraTimestamp) + ", cameraOutputSequence=" + this.cameraOutputSequence + ", cameraOutputNumber=" + this.cameraOutputNumber + ", outputListener=" + this.outputListener + ')';
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OutputDistributor(int r3, Finalizer<? super T> r4, OutputMatcher r5) {
        this.maximumCachedOutputs = r3;
        this.outputFinalizer = r4;
        this.outputMatcher = r5;
        this.lock = new Object();
        this.cameraOutputSequenceNumbers = 1;
        this.newestCameraOutputNumber = Long.MIN_VALUE;
        this.newestFrameNumber = FrameNumber.m268constructorimpl(Long.MIN_VALUE);
        this.lastFailedFrameNumber = Long.MIN_VALUE;
        this.lastFailedCameraOutputNumber = Long.MIN_VALUE;
        this.startedOutputs = new ArrayList();
        this.availableOutputs = new LinkedHashMap();
    }

    public /* synthetic */ OutputDistributor(int r1, Finalizer r2, OutputMatcher r3, int r4, DefaultConstructorMarker r5) {
        if ((r4 & 1) == 0) goto L5;
        r1 = 3;
    L5:
        this(r1, r2, r3);
    }

    private final List<StartedOutput<T>> removeOutputsOlderThan(StartedOutput<T> r7) {
        return removeOutputsOlderThan(r7.isOutOfOrder(), r7.getCameraOutputSequence(), r7.getCameraOutputNumber());
    }

    private final List<StartedOutput<T>> removeOutputsOlderThan(boolean r7, long r8, long r10) {
        List<StartedOutput<T>> r0 = this.startedOutputs;
        ArrayList r1 = new ArrayList();
        Iterator<T> r02 = r0.iterator();
    L4:
        if (r02.hasNext() == false) goto L12;
        T r2 = r02.next();
        StartedOutput r3 = (StartedOutput) r2;
        if (r3.isOutOfOrder() != r7) goto L4;
        if (r3.getCameraOutputSequence() >= r8) goto L4;
        if (r3.getCameraOutputNumber() >= r10) goto L4;
        r1.add(r2);
        goto L4
    L12:
        this.startedOutputs.removeAll(r1);
        return r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.AutoCloseable
    public void close() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L24:
        th = move-exception;
        throw th;
    L5:
        if (this.closed == false) goto L9;
        monitor-exit(r0);
        return;
    L9:
        this.closed = true;     // Catch: Throwable -> L24
        List r1 = CollectionsKt.toMutableList(this.availableOutputs.values());     // Catch: Throwable -> L24
        this.availableOutputs.clear();     // Catch: Throwable -> L24
        List r2 = CollectionsKt.toMutableList(this.startedOutputs);     // Catch: Throwable -> L24
        this.startedOutputs.clear();     // Catch: Throwable -> L24
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L24
        monitor-exit(r0);
        Iterator r02 = r1.iterator();
    L13:
        if (r02.hasNext() == false) goto L19;
        Object r12 = ((OutputResult) r02.next()).m579unboximpl();
        Finalizer<T> r32 = this.outputFinalizer;
        if (OutputResult.m575getAvailableimpl(r12) == true) goto L18;
        r12 = null;
    L18:
        r32.finalize(r12);
        goto L13
    L19:
        Iterator r5 = r2.iterator();
    L21:
        if (r5.hasNext() == false) goto L23;
        ((StartedOutput) r5.next()).m569completeWithFailuretXNfJfc(OutputStatus.Companion.m304getERROR_OUTPUT_ABORTEDU7r42EA());
        goto L21
    }

    /* renamed from: onOutputFailure-Vw7M1qk, reason: not valid java name */
    public final void m565onOutputFailureVw7M1qk(long r9) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L16:
        th = move-exception;
        throw th;
    L5:
        if (this.closed == false) goto L8;
        monitor-exit(r0);
        return;
    L8:
        this.lastFailedFrameNumber = r9;     // Catch: Throwable -> L16
        Iterator<T> r1 = this.startedOutputs.iterator();     // Catch: Throwable -> L16
        StartedOutput r2 = null;
        boolean r3 = false;
        StartedOutput r4 = null;
    L10:
        if (r1.hasNext() == false) goto L18;
        T r5 = r1.next();     // Catch: Throwable -> L16
        if (FrameNumber.m270equalsimpl0(((StartedOutput) r5).m570getCameraFrameNumberUgla2oM(), r9) == false) goto L10;
        if (r3 == true) goto L14;
        r3 = true;
        r4 = r5;
    L14:
        r4 = null;
    L20:
        StartedOutput r42 = r4;     // Catch: Throwable -> L16
        if (r42 == null) goto L24;
        this.lastFailedCameraOutputNumber = r42.getCameraOutputNumber();     // Catch: Throwable -> L16
        this.startedOutputs.remove(r42);     // Catch: Throwable -> L16
        Unit r8 = Unit.INSTANCE;     // Catch: Throwable -> L16
        r2 = r42;
    L24:
        monitor-exit(r0);
        if (r2 == null) goto L38;
        r2.m569completeWithFailuretXNfJfc(OutputStatus.Companion.m305getERROR_OUTPUT_FAILEDU7r42EA());
        return;
    L38:
        return;
    L18:
        if (r3 == true) goto L20;
        goto L20
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: onOutputResult-DvZWqE8, reason: not valid java name */
    public final void m566onOutputResultDvZWqE8(long r9, Object r11) {
        Object r0 = this.lock;
        monitor-enter(r0);
        Object r2 = null;
        if (this.closed == false) goto L7;
    L26:
        OutputResult<T> r92 = OutputResult.m572boximpl(r11);     // Catch: Throwable -> L15
    L24:
        List<StartedOutput<T>> r1 = null;
    L27:
        Unit r10 = Unit.INSTANCE;     // Catch: Throwable -> L15
        monitor-exit(r0);
        OutputResult<T> r93 = r92;
        if (r93 == null) goto L36;
        Object r94 = r93.m579unboximpl();
        if (OutputResult.m575getAvailableimpl(r94) == false) goto L34;
        r2 = r94;
    L34:
        if (r2 == null) goto L36;
        this.outputFinalizer.finalize(r2);
    L36:
        if (r1 == null) goto L41;
        Iterator<T> r8 = r1.iterator();
    L39:
        if (r8.hasNext() == false) goto L50;
        ((StartedOutput) r8.next()).m569completeWithFailuretXNfJfc(OutputStatus.Companion.m306getERROR_OUTPUT_MISSINGU7r42EA());
        goto L39
    L50:
        return;
    L41:
        return;
    L7:
        if (this.outputMatcher.fuzzyEqual(this.lastFailedCameraOutputNumber, r9) == true) goto L26;
        Iterator<T> r12 = this.startedOutputs.iterator();     // Catch: Throwable -> L15
    L11:
        if (r12.hasNext() == false) goto L17;
        T r3 = r12.next();     // Catch: Throwable -> L15
        if (this.outputMatcher.fuzzyEqual(((StartedOutput) r3).getCameraOutputNumber(), r9) == false) goto L11;
    L18:
        StartedOutput<T> r32 = r3;     // Catch: Throwable -> L15
        if (r32 == null) goto L21;
        r1 = removeOutputsOlderThan(r32);     // Catch: Throwable -> L15
        r32.m568completeWithDvZWqE8(r9, r11);     // Catch: Throwable -> L15
        this.startedOutputs.remove(r32);     // Catch: Throwable -> L15
        r92 = null;
        goto L27
    L21:
        this.availableOutputs.put(Long.valueOf(r9), OutputResult.m572boximpl(r11));     // Catch: Throwable -> L15
        if (this.availableOutputs.size() <= this.maximumCachedOutputs) goto L25;
        r92 = this.availableOutputs.remove(Long.valueOf(((Number) CollectionsKt.first(this.availableOutputs.keySet())).longValue()));     // Catch: Throwable -> L15
        goto L24
    L25:
        r92 = null;
        r1 = null;
        goto L27
    L17:
        r3 = (T) null;
    L15:
        th = move-exception;
        throw th;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: onOutputStarted-qGubWw0, reason: not valid java name */
    public final void m567onOutputStartedqGubWw0(long r19, long r21, long r23, OutputListener<T> r25) {
        Ref.ObjectRef r12 = new Ref.ObjectRef();
        Object r13 = this.lock;
        monitor-enter(r13);
        Iterator<T> r1 = this.startedOutputs.iterator();     // Catch: Throwable -> L10
    L6:
        if (r1.hasNext() == false) goto L12;
        T r2 = r1.next();     // Catch: Throwable -> L10
        if (FrameNumber.m270equalsimpl0(((StartedOutput) r2).m570getCameraFrameNumberUgla2oM(), r19) == false) goto L6;
    L13:
        StartedOutput r22 = r2;     // Catch: Throwable -> L10
        if (r22 != null) goto L16;
        boolean r15 = this.closed;     // Catch: Throwable -> L10
        long r24 = this.cameraOutputSequenceNumbers;     // Catch: Throwable -> L10
        this.cameraOutputSequenceNumbers = 1 + r24;     // Catch: Throwable -> L10
        if (r15 == false) goto L23;
    L26:
        OutputDistributor<T> r14 = this;
        boolean r17 = r15;
        Iterator<T> r0 = r14.availableOutputs.keySet().iterator();     // Catch: Throwable -> L10
    L58:
        if (r0.hasNext() == false) goto L62;
        T r16 = r0.next();     // Catch: Throwable -> L10
        if (r14.outputMatcher.fuzzyEqual(r23, ((Number) r16).longValue()) == false) goto L58;
    L63:
        Long r10 = r16;     // Catch: Throwable -> L10
        if (r10 == null) goto L66;
        T r02 = (T) r14.availableOutputs.remove(r10);
    L67:
        r12.element = r02;     // Catch: Throwable -> L10
        Long r03 = r10;
        List<StartedOutput<T>> r18 = null;
        boolean r102 = true;
    L55:
        OutputResult<T> r11 = null;
    L68:
        Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L10
        monitor-exit(r13);
        if (r18 == null) goto L75;
        Iterator<T> r110 = r18.iterator();
    L73:
        if (r110.hasNext() == false) goto L75;
        ((StartedOutput) r110.next()).m569completeWithFailuretXNfJfc(OutputStatus.Companion.m306getERROR_OUTPUT_MISSINGU7r42EA());
    L75:
        OutputResult r111 = (OutputResult) r12.element;
        if (r111 == null) goto L83;
        Object r112 = r111.m579unboximpl();
        if (OutputResult.m575getAvailableimpl(r112) == true) goto L81;
        r112 = null;
    L81:
        if (r112 == null) goto L83;
        r14.outputFinalizer.finalize(r112);
    L83:
        if (r102 == false) goto L110;
        if (r17 == false) goto L87;
        OutputResult.Companion r113 = OutputResult.Companion;
        Object r114 = OutputResult.m571access$constructorimpl(OutputStatus.m297boximpl(OutputStatus.Companion.m304getERROR_OUTPUT_ABORTEDU7r42EA()));
    L86:
        Object r9 = r114;
        if (r03 == null) goto L94;
        long r04 = r03.longValue();
    L95:
        r25.mo561onOutputComplete3ejhThk(r19, r21, r24, r04, r9);
        return;
    L94:
        r04 = -1;
        goto L95
    L87:
        OutputResult<T> r115 = r11;
        if (r115 == null) goto L90;
        r114 = r115.m579unboximpl();
        goto L86
    L90:
        OutputResult.Companion r116 = OutputResult.Companion;
        r114 = OutputResult.m571access$constructorimpl(OutputStatus.m297boximpl(OutputStatus.Companion.m305getERROR_OUTPUT_FAILEDU7r42EA()));
        goto L86
    L110:
        return;
    L66:
        r02 = null;
        goto L67
    L62:
        r16 = (T) null;
        goto L63
    L23:
        if (this.lastFailedFrameNumber == r19) goto L26;
        if (this.lastFailedCameraOutputNumber == r23) goto L26;
        if (r19 >= this.newestFrameNumber) goto L30;
        boolean r117 = true;
    L31:
        if (r117 == true) goto L34;
        this.newestFrameNumber = r19;     // Catch: Throwable -> L10
    L34:
        if (r23 >= this.newestCameraOutputNumber) goto L36;
        boolean r42 = true;
    L37:
        if (r42 == true) goto L39;
        this.newestCameraOutputNumber = r23;     // Catch: Throwable -> L10
    L39:
        if (r117 == true) goto L43;
        if (r42 == true) goto L43;
        boolean r118 = false;
    L44:
        Iterator<T> r43 = this.availableOutputs.keySet().iterator();     // Catch: Throwable -> L10
    L46:
        if (r43.hasNext() == false) goto L50;
        T r5 = r43.next();     // Catch: Throwable -> L10
        if (this.outputMatcher.fuzzyEqual(r23, ((Number) r5).longValue()) == false) goto L46;
    L51:
        Long r103 = r5;     // Catch: Throwable -> L10
        if (r103 == null) goto L54;
        r11 = this.availableOutputs.remove(r103);     // Catch: Throwable -> L10
        r18 = removeOutputsOlderThan(r118, r24, r23);     // Catch: Throwable -> L10
        r14 = this;
        r03 = r103;
        r17 = r15;
        r102 = true;
        goto L68
    L54:
        r14 = this;
        List<StartedOutput<T>> r05 = r14.startedOutputs;     // Catch: Throwable -> L10
        r17 = r15;
        StartedOutput<T> r06 = new StartedOutput(r118, r19, r21, r24, r23, r25, null);     // Catch: Throwable -> L10
        r24 = r24;
        r05.add(r06);     // Catch: Throwable -> L10
        r102 = false;
        r03 = null;
        r18 = null;
        goto L55
    L50:
        r5 = (T) null;
    L43:
        r118 = true;
        goto L44
    L36:
        r42 = false;
        goto L37
    L30:
        r117 = false;
        goto L31
    L16:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L18;
        android.util.Log.w("CXCP", "onOutputStarted was invoked multiple times with a previously started output!onOutputStarted with " + FrameNumber.m272toStringimpl(r19) + ", " + CameraTimestamp.m245toStringimpl(r21) + ", " + r23 + ". Previously started output: " + r22 + ". Ignoring.");     // Catch: Throwable -> L10
    L18:
        monitor-exit(r13);
        return;
    L12:
        r2 = (T) null;
    L10:
        th = move-exception;
        throw th;
    }
}
