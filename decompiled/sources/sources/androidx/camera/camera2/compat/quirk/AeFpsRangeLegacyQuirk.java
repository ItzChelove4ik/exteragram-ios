package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.hardware.camera2.CameraCharacteristics;
import android.util.Range;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.internal.compat.quirk.AeFpsRangeQuirk;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0006\b\u0007\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\r\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0016\u0010\u000e\u001a\u0012\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0018\u00010\u000fH\u0002¢\u0006\u0002\u0010\u0010J\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016R#\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Landroidx/camera/camera2/compat/quirk/AeFpsRangeLegacyQuirk;", "Landroidx/camera/core/internal/compat/quirk/AeFpsRangeQuirk;", "cameraMetadata", "Landroidx/camera/camera2/pipe/CameraMetadata;", "<init>", "(Landroidx/camera/camera2/pipe/CameraMetadata;)V", "range", "Landroid/util/Range;", _UrlKt.FRAGMENT_ENCODE_SET, "getRange", "()Landroid/util/Range;", "range$delegate", "Lkotlin/Lazy;", "pickSuitableFpsRange", "availableFpsRanges", _UrlKt.FRAGMENT_ENCODE_SET, "([Landroid/util/Range;)Landroid/util/Range;", "getCorrectedFpsRange", "fpsRange", "getTargetAeFpsRange", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
/* loaded from: classes3.dex */
public final class AeFpsRangeLegacyQuirk implements AeFpsRangeQuirk {
    public static final Companion Companion = null;
    private final Lazy range$delegate;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/compat/quirk/AeFpsRangeLegacyQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "cameraMetadata", "Landroidx/camera/camera2/pipe/CameraMetadata;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final boolean isEnabled(CameraMetadata r1) {
            return CameraMetadata.Companion.isHardwareLevelLegacy(r1);
        }
    }

    public static Range $r8$lambda$M08z1plyKIceWjdadH5XAqm54nQ(CameraMetadata r1, AeFpsRangeLegacyQuirk r2) {
        return r2.pickSuitableFpsRange((Range[]) r1.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES));
    }

    static {
        Companion = new Companion(null);
    }

    public AeFpsRangeLegacyQuirk(CameraMetadata r2) {
        this.range$delegate = LazyKt.lazy(new AeFpsRangeLegacyQuirk$$ExternalSyntheticLambda0(r2, this));
    }

    private final Range<Integer> getCorrectedFpsRange(Range<Integer> r4) {
        Integer r3 = (Integer) r4.getUpper();
        Integer r0 = (Integer) r4.getLower();
        if (((Number) r4.getUpper()).intValue() < 1000) goto L6;
        r3 = Integer.valueOf(((Number) r4.getUpper()).intValue() / 1000);
    L6:
        if (((Number) r4.getLower()).intValue() < 1000) goto L9;
        r0 = Integer.valueOf(((Number) r4.getLower()).intValue() / 1000);
    L9:
        return new Range(r0, r3);
    }

    private final Range<Integer> getRange() {
        return (Range) this.range$delegate.getValue();
    }

    private final Range<Integer> pickSuitableFpsRange(Range<Integer>[] r7) {
        Range<Integer> r0 = null;
        if (r7 != null) goto L5;
    L21:
        return r0;
    L5:
        if (r7.length == 0) goto L21;
        int r1 = r7.length;
        int r2 = 0;
    L8:
        if (r2 >= r1) goto L21;
        Range<Integer> r3 = getCorrectedFpsRange(r7[r2]);
        Integer r4 = (Integer) r3.getUpper();
        if (r4 == null) goto L20;
        if (r4.intValue() != 30) goto L20;
        if (r0 != null) goto L18;
    L19:
        r0 = r3;
        goto L20
    L18:
        if (((Number) r3.getLower()).intValue() < ((Number) r0.getLower()).intValue()) goto L19;
    L20:
        r2 = r2 + 1;
        goto L8
    }

    @Override // androidx.camera.core.internal.compat.quirk.AeFpsRangeQuirk
    public Range<Integer> getTargetAeFpsRange() {
        Range<Integer> r0 = getRange();
        if (r0 == null) goto L5;
        return r0;
    L5:
        return StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
    }
}
