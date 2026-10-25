package androidx.camera.camera2.pipe.internal;

import androidx.camera.camera2.pipe.FrameReference;
import androidx.camera.camera2.pipe.OutputId;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.internal.FrameState;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.BuildConfig;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B!\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0004¢\u0006\u0004\b\u000f\u0010\u000eJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0013R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0014R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Landroidx/camera/camera2/pipe/internal/FrameImpl;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/internal/FrameState;", "frameState", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "imageStreams", "<init>", "(Landroidx/camera/camera2/pipe/internal/FrameState;Ljava/util/Set;)V", _UrlKt.FRAGMENT_ENCODE_SET, BuildConfig.BUILD_TYPE, "()Z", _UrlKt.FRAGMENT_ENCODE_SET, "close", "()V", "finalize", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroidx/camera/camera2/pipe/internal/FrameState;", "Ljava/util/Set;", "getImageStreams", "()Ljava/util/Set;", "Landroidx/camera/camera2/pipe/OutputId;", "outputStreams", "Lkotlinx/atomicfu/AtomicBoolean;", "closed", "Lkotlinx/atomicfu/AtomicBoolean;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFrameImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FrameImpl.kt\nandroidx/camera/camera2/pipe/internal/FrameImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,249:1\n1563#2:250\n1634#2,3:251\n1563#2:254\n1634#2,3:255\n774#2:260\n865#2,2:261\n774#2:263\n865#2,2:264\n295#2,2:266\n295#2,2:268\n774#2:270\n865#2,2:271\n1617#2,9:273\n1869#2:282\n1870#2:284\n1626#2:285\n774#2:286\n865#2,2:287\n1617#2,9:289\n1869#2:298\n1870#2:300\n1626#2:301\n774#2:302\n865#2,2:303\n1563#2:305\n1634#2,3:306\n1761#2,3:309\n1761#2,3:312\n1740#2,3:315\n295#2,2:318\n82#3,2:258\n1#4:283\n1#4:299\n1#4:320\n*S KotlinDebug\n*F\n+ 1 FrameImpl.kt\nandroidx/camera/camera2/pipe/internal/FrameImpl\n*L\n44#1:250\n44#1:251,3\n41#1:254\n41#1:255,3\n152#1:260\n152#1:261,2\n164#1:263\n164#1:264,2\n176#1:266,2\n183#1:268,2\n190#1:270\n190#1:271,2\n190#1:273,9\n190#1:282\n190#1:284\n190#1:285\n197#1:286\n197#1:287,2\n198#1:289,9\n198#1:298\n198#1:300\n198#1:301\n203#1:302\n203#1:303,2\n203#1:305\n203#1:306,3\n216#1:309,3\n223#1:312,3\n228#1:315,3\n238#1:318,2\n132#1:258,2\n190#1:283\n198#1:299\n*E\n"})
/* loaded from: classes3.dex */
public final class FrameImpl implements FrameReference, AutoCloseable {
    private final AtomicBoolean closed;
    private final FrameState frameState;
    private final Set<StreamId> imageStreams;
    private final Set<OutputId> outputStreams;

    public FrameImpl(FrameState r2, Set<StreamId> r3) {
        this.frameState = r2;
        this.imageStreams = r3;
        List<FrameState.ImageOutput> r22 = r2.getImageOutputs();
        ArrayList r32 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r22, 10));
        Iterator<T> r23 = r22.iterator();
    L4:
        if (r23.hasNext() == false) goto L6;
        r32.add(OutputId.m290boximpl(((FrameState.ImageOutput) r23.next()).m562getOutputId4LaLFng()));
        goto L4
    L6:
        this.outputStreams = CollectionsKt.toSet(r32);
        this.closed = AtomicFU.atomic(false);
    }

    public /* synthetic */ FrameImpl(FrameState r1, Set r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L9;
        List<FrameState.ImageOutput> r22 = r1.getImageOutputs();
        ArrayList r32 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r22, 10));
        Iterator<T> r23 = r22.iterator();
    L6:
        if (r23.hasNext() == false) goto L8;
        r32.add(StreamId.m401boximpl(((FrameState.ImageOutput) r23.next()).m563getStreamIdptHMqGs()));
        goto L6
    L8:
        r2 = CollectionsKt.toSet(r32);
    L9:
        this(r1, r2);
    }

    private final boolean release() {
        int r1 = 0;
        if (this.closed.compareAndSet(false, true) == false) goto L11;
        this.frameState.getFrameInfoOutput().decrement();
        int r0 = this.frameState.getImageOutputs().size();
    L5:
        if (r1 >= r0) goto L10;
        FrameState.ImageOutput r3 = this.frameState.getImageOutputs().get(r1);
        if (getImageStreams().contains(StreamId.m401boximpl(r3.m563getStreamIdptHMqGs())) == false) goto L9;
        r3.decrement();
    L9:
        r1 = r1 + 1;
        goto L5
    L10:
        return true;
    L11:
        return false;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        release();
    }

    public final void finalize() {
        if (release() == true) goto L5;
        return;
    L5:
        if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L9;
        android.util.Log.e("CXCP", "Failed to close " + this + "! This indicates a memory leak and could cause the camera to stall, or images to be lost.");
        return;
    }

    public Set<StreamId> getImageStreams() {
        return this.imageStreams;
    }

    public String toString() {
        return this.frameState.toString();
    }
}
