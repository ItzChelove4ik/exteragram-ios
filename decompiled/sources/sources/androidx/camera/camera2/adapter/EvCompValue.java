package androidx.camera.camera2.adapter;

import android.annotation.SuppressLint;
import android.util.Range;
import android.util.Rational;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\f\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\r\u0010\u000eJ>\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u001a\u0010\u0018\u001a\u00020\u00022\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001aR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001bR\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u001cR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u001d¨\u0006\u001e"}, d2 = {"Landroidx/camera/camera2/adapter/EvCompValue;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "supported", _UrlKt.FRAGMENT_ENCODE_SET, "index", "Landroid/util/Range;", "range", "Landroid/util/Rational;", "step", "<init>", "(ZILandroid/util/Range;Landroid/util/Rational;)V", "newIndex", "updateIndex$camera_camera2", "(I)Landroidx/camera/camera2/adapter/EvCompValue;", "updateIndex", "copy", "(ZILandroid/util/Range;Landroid/util/Rational;)Landroidx/camera/camera2/adapter/EvCompValue;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Z", "I", "Landroid/util/Range;", "Landroid/util/Rational;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"UnsafeOptInUsageError"})
/* loaded from: classes3.dex */
public final class EvCompValue {
    private final int index;
    private final Range<Integer> range;
    private final Rational step;
    private final boolean supported;

    public EvCompValue(boolean r1, int r2, Range<Integer> r3, Rational r4) {
        this.supported = r1;
        this.index = r2;
        this.range = r3;
        this.step = r4;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ EvCompValue copy$default(EvCompValue r0, boolean r1, int r2, Range r3, Rational r4, int r5, Object r6) {
        if ((r5 & 1) == 0) goto L6;
        r1 = r0.supported;
    L6:
        if ((r5 & 2) == 0) goto L9;
        r2 = r0.index;
    L9:
        if ((r5 & 4) == 0) goto L12;
        r3 = r0.range;
    L12:
        if ((r5 & 8) == 0) goto L15;
        r4 = r0.step;
    L15:
        return r0.copy(r1, r2, r3, r4);
    }

    public final EvCompValue copy(boolean r1, int r2, Range<Integer> r3, Rational r4) {
        return new EvCompValue(r1, r2, r3, r4);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof EvCompValue) == true) goto L8;
        return false;
    L8:
        EvCompValue r52 = (EvCompValue) r5;
        if (this.supported == r52.supported) goto L12;
        return false;
    L12:
        if (this.index == r52.index) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.range, r52.range) == true) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(this.step, r52.step) == true) goto L20;
        return false;
    L20:
        return true;
    }

    public int hashCode() {
        return (((((Boolean.hashCode(this.supported) * 31) + Integer.hashCode(this.index)) * 31) + this.range.hashCode()) * 31) + this.step.hashCode();
    }

    public String toString() {
        return "EvCompValue(supported=" + this.supported + ", index=" + this.index + ", range=" + this.range + ", step=" + this.step + ')';
    }

    public final EvCompValue updateIndex$camera_camera2(int r8) {
        return copy$default(this, false, r8, null, null, 13, null);
    }
}
