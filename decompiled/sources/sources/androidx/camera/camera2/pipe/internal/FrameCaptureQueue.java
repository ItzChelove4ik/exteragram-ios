package androidx.camera.camera2.pipe.internal;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.camera.camera2.pipe.OutputStatus;
import androidx.camera.camera2.pipe.Request;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0001\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0001\u0015B\t\b\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001b\u0010\b\u001a\b\u0018\u00010\u0007R\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\u0004R\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u001e\u0010\u0010\u001a\f\u0012\b\u0012\u00060\u0007R\u00020\u00000\u000f8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0013\u001a\u00020\u00128\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Landroidx/camera/camera2/pipe/internal/FrameCaptureQueue;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Landroidx/camera/camera2/pipe/Request;", PluginsConstants.REQUEST, "Landroidx/camera/camera2/pipe/internal/FrameCaptureQueue$FrameCaptureImpl;", "remove", "(Landroidx/camera/camera2/pipe/Request;)Landroidx/camera/camera2/pipe/internal/FrameCaptureQueue$FrameCaptureImpl;", _UrlKt.FRAGMENT_ENCODE_SET, "close", _UrlKt.FRAGMENT_ENCODE_SET, "lock", "Ljava/lang/Object;", "Lkotlin/collections/ArrayDeque;", "queue", "Lkotlin/collections/ArrayDeque;", _UrlKt.FRAGMENT_ENCODE_SET, "closed", "Z", "FrameCaptureImpl", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFrameCaptureQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FrameCaptureQueue.kt\nandroidx/camera/camera2/pipe/internal/FrameCaptureQueue\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,194:1\n295#2,2:195\n1563#2:198\n1634#2,3:199\n1#3:197\n*S KotlinDebug\n*F\n+ 1 FrameCaptureQueue.kt\nandroidx/camera/camera2/pipe/internal/FrameCaptureQueue\n*L\n51#1:195,2\n76#1:198\n76#1:199,3\n*E\n"})
/* loaded from: classes3.dex */
public final class FrameCaptureQueue implements AutoCloseable {
    private boolean closed;
    private final Object lock;
    private final ArrayDeque<FrameCaptureImpl> queue;

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Landroidx/camera/camera2/pipe/internal/FrameCaptureQueue$FrameCaptureImpl;", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nFrameCaptureQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FrameCaptureQueue.kt\nandroidx/camera/camera2/pipe/internal/FrameCaptureQueue$FrameCaptureImpl\n+ 2 OutputResult.kt\nandroidx/camera/camera2/pipe/internal/OutputResult$Companion\n+ 3 OutputResult.kt\nandroidx/camera/camera2/pipe/internal/OutputResult\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,194:1\n72#2:195\n64#2:196\n79#2:197\n68#2:198\n103#2,2:199\n106#2:205\n87#2,11:206\n103#2,2:227\n106#2:233\n103#2,2:234\n106#2:240\n44#3,4:201\n55#3,5:217\n44#3,4:222\n44#3,4:229\n44#3,4:236\n1#4:226\n*S KotlinDebug\n*F\n+ 1 FrameCaptureQueue.kt\nandroidx/camera/camera2/pipe/internal/FrameCaptureQueue$FrameCaptureImpl\n*L\n115#1:195\n115#1:196\n135#1:197\n135#1:198\n154#1:199,2\n154#1:205\n160#1:206,11\n173#1:227,2\n173#1:233\n185#1:234,2\n185#1:240\n154#1:201,4\n160#1:217,5\n165#1:222,4\n173#1:229,4\n185#1:236,4\n*E\n"})
    public final class FrameCaptureImpl implements AutoCloseable {
    }

    public FrameCaptureQueue() {
        this.lock = new Object();
        this.queue = new ArrayDeque();
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L17:
        th = move-exception;
        throw th;
    L5:
        if (this.closed == false) goto L9;
        monitor-exit(r0);
        return;
    L9:
        this.closed = true;     // Catch: Throwable -> L17
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L17
        monitor-exit(r0);
        Iterator<FrameCaptureImpl> r02 = this.queue.iterator();
        if (r02.hasNext() == true) goto L15;
        this.queue.clear();
        return;
    L15:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r02.next());
        OutputStatus.Companion.m304getERROR_OUTPUT_ABORTEDU7r42EA();
        throw null;
    }

    public final FrameCaptureImpl remove(Request r3) {
        Object r32 = this.lock;
        monitor-enter(r32);
    L14:
        th = move-exception;
        throw th;
    L6:
        if (this.closed == false) goto L9;
        monitor-exit(r32);
        return null;
    L9:
        Iterator<FrameCaptureImpl> r2 = this.queue.iterator();     // Catch: Throwable -> L14
        if (r2.hasNext() == true) goto L16;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(null);     // Catch: Throwable -> L14
        monitor-exit(r32);
        return null;
    L16:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2.next());     // Catch: Throwable -> L14
        throw null;     // Catch: Throwable -> L14
    }
}
