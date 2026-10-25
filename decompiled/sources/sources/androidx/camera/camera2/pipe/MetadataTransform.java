package androidx.camera.camera2.pipe;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@kotlin.Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u0018B%\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\rR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0012\u001a\u0004\b\u0014\u0010\rR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Landroidx/camera/camera2/pipe/MetadataTransform;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "past", "future", "Landroidx/camera/camera2/pipe/MetadataTransform$TransformFn;", "transformFn", "<init>", "(IILandroidx/camera/camera2/pipe/MetadataTransform$TransformFn;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "I", "getPast", "getFuture", "Landroidx/camera/camera2/pipe/MetadataTransform$TransformFn;", "getTransformFn", "()Landroidx/camera/camera2/pipe/MetadataTransform$TransformFn;", "TransformFn", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MetadataTransform {
    private final int future;
    private final int past;
    private final TransformFn transformFn;

    @kotlin.Metadata(d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"androidx/camera/camera2/pipe/MetadataTransform$1", "Landroidx/camera/camera2/pipe/MetadataTransform$TransformFn;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: androidx.camera.camera2.pipe.MetadataTransform$1, reason: invalid class name */
    public static final class AnonymousClass1 implements TransformFn {
        public AnonymousClass1() {
        }
    }

    @kotlin.Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u00002\u00020\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0002À\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/pipe/MetadataTransform$TransformFn;", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface TransformFn {
    }

    public MetadataTransform(int r1, int r2, TransformFn r3) {
        this.past = r1;
        this.future = r2;
        this.transformFn = r3;
        if (r1 < 0) goto L8;
        if (r2 < 0) goto L6;
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        throw null;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        throw null;
    }

    public /* synthetic */ MetadataTransform(int r2, int r3, TransformFn r4, int r5, DefaultConstructorMarker r6) {
        if ((r5 & 1) == 0) goto L6;
        r2 = 0;
    L6:
        if ((r5 & 2) == 0) goto L9;
        r3 = 0;
    L9:
        if ((r5 & 4) == 0) goto L11;
        r4 = new AnonymousClass1();
    L11:
        this(r2, r3, r4);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof MetadataTransform) == true) goto L8;
        return false;
    L8:
        MetadataTransform r52 = (MetadataTransform) r5;
        if (this.past == r52.past) goto L12;
        return false;
    L12:
        if (this.future == r52.future) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.transformFn, r52.transformFn) == true) goto L17;
        return false;
    L17:
        return true;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.past) * 31) + Integer.hashCode(this.future)) * 31) + this.transformFn.hashCode();
    }

    public String toString() {
        return "MetadataTransform(past=" + this.past + ", future=" + this.future + ", transformFn=" + this.transformFn + ')';
    }
}
