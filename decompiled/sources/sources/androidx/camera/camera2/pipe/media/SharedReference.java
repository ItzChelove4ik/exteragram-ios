package androidx.camera.camera2.pipe.media;

import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicRef;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.NotificationBadge;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u0003\u001a\u00028\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R$\u0010\u0012\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00040\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/camera/camera2/pipe/media/SharedReference;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "value", "Landroidx/camera/camera2/pipe/media/Finalizer;", "defaultFinalizer", "<init>", "(Ljava/lang/Object;Landroidx/camera/camera2/pipe/media/Finalizer;)V", "acquireOrNull", "()Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "decrement", "()V", "Ljava/lang/Object;", "Lkotlinx/atomicfu/AtomicInt;", NotificationBadge.NewHtcHomeBadger.COUNT, "Lkotlinx/atomicfu/AtomicInt;", "Lkotlinx/atomicfu/AtomicRef;", "currentFinalizer", "Lkotlinx/atomicfu/AtomicRef;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSharedReference.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SharedReference.kt\nandroidx/camera/camera2/pipe/media/SharedReference\n+ 2 AtomicFU.common.kt\nkotlinx/atomicfu/AtomicFU_commonKt\n*L\n1#1,112:1\n382#2,4:113\n175#2,4:117\n*S KotlinDebug\n*F\n+ 1 SharedReference.kt\nandroidx/camera/camera2/pipe/media/SharedReference\n*L\n45#1:113,4\n90#1:117,4\n*E\n"})
/* loaded from: classes3.dex */
public final class SharedReference<T> {
    private final AtomicInt count;
    private AtomicRef<Finalizer<T>> currentFinalizer;
    private final T value;

    public SharedReference(T r1, Finalizer<? super T> r2) {
        this.value = r1;
        this.count = AtomicFU.atomic(1);
        this.currentFinalizer = AtomicFU.atomic(r2);
    }

    public final T acquireOrNull() {
        AtomicInt r0 = this.count;
    L3:
        int r1 = r0.getValue();
        if (r1 != 0) goto L6;
        int r2 = 0;
    L8:
        if (r0.compareAndSet(r1, r2) == false) goto L3;
        if (r2 != 0) goto L11;
        return null;
    L11:
        return this.value;
    L6:
        r2 = r1 + 1;
        goto L8
    }

    public final void decrement() {
        if (this.count.decrementAndGet() != 0) goto L6;
        this.currentFinalizer.getAndSet(null).finalize(this.value);
        return;
    }
}
