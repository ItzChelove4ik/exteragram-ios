package androidx.camera.camera2.compat;

import android.hardware.camera2.params.DynamicRangeProfiles;
import androidx.camera.camera2.compat.DynamicRangeProfilesCompat;
import androidx.camera.core.DynamicRange;
import androidx.core.util.Preconditions;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\n\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\n\u0010\u000bR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Landroidx/camera/camera2/compat/DynamicRangeProfilesCompatBaseImpl;", "Landroidx/camera/camera2/compat/DynamicRangeProfilesCompat$DynamicRangeProfilesCompatImpl;", "<init>", "()V", "Landroidx/camera/core/DynamicRange;", "dynamicRange", _UrlKt.FRAGMENT_ENCODE_SET, "getDynamicRangeCaptureRequestConstraints", "(Landroidx/camera/core/DynamicRange;)Ljava/util/Set;", "Landroid/hardware/camera2/params/DynamicRangeProfiles;", "unwrap", "()Landroid/hardware/camera2/params/DynamicRangeProfiles;", "getSupportedDynamicRanges", "()Ljava/util/Set;", "supportedDynamicRanges", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DynamicRangeProfilesCompatBaseImpl implements DynamicRangeProfilesCompat.DynamicRangeProfilesCompatImpl {
    private static final DynamicRangeProfilesCompat COMPAT_INSTANCE = null;
    public static final Companion Companion = null;
    private static final Set<DynamicRange> SDR_ONLY = null;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u001f\u0010\f\u001a\r\u0012\t\u0012\u00070\n¢\u0006\u0002\b\u000b0\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/camera/camera2/compat/DynamicRangeProfilesCompatBaseImpl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/compat/DynamicRangeProfilesCompat;", "COMPAT_INSTANCE", "Landroidx/camera/camera2/compat/DynamicRangeProfilesCompat;", "getCOMPAT_INSTANCE", "()Landroidx/camera/camera2/compat/DynamicRangeProfilesCompat;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/DynamicRange;", "Lkotlin/jvm/internal/EnhancedNullability;", "SDR_ONLY", "Ljava/util/Set;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final DynamicRangeProfilesCompat getCOMPAT_INSTANCE() {
            return DynamicRangeProfilesCompatBaseImpl.access$getCOMPAT_INSTANCE$cp();
        }
    }

    static {
        Companion = new Companion(null);
        COMPAT_INSTANCE = new DynamicRangeProfilesCompat(new DynamicRangeProfilesCompatBaseImpl());
        SDR_ONLY = SetsKt.setOf(DynamicRange.SDR);
    }

    public DynamicRangeProfilesCompatBaseImpl() {
    }

    public static final /* synthetic */ DynamicRangeProfilesCompat access$getCOMPAT_INSTANCE$cp() {
        return COMPAT_INSTANCE;
    }

    @Override // androidx.camera.camera2.compat.DynamicRangeProfilesCompat.DynamicRangeProfilesCompatImpl
    public Set<DynamicRange> getDynamicRangeCaptureRequestConstraints(DynamicRange r3) {
        Preconditions.checkArgument(Intrinsics.areEqual(DynamicRange.SDR, r3), "DynamicRange is not supported: " + r3);
        return SDR_ONLY;
    }

    @Override // androidx.camera.camera2.compat.DynamicRangeProfilesCompat.DynamicRangeProfilesCompatImpl
    public Set<DynamicRange> getSupportedDynamicRanges() {
        return SDR_ONLY;
    }

    @Override // androidx.camera.camera2.compat.DynamicRangeProfilesCompat.DynamicRangeProfilesCompatImpl
    public DynamicRangeProfiles unwrap() {
        return null;
    }
}
