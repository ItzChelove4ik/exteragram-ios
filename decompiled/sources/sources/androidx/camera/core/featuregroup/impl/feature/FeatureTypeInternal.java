package androidx.camera.core.featuregroup.impl.feature;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Landroidx/camera/core/featuregroup/impl/feature/FeatureTypeInternal;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "DYNAMIC_RANGE", "FPS_RANGE", "VIDEO_STABILIZATION", "IMAGE_FORMAT", "RECORDING_QUALITY", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public enum FeatureTypeInternal extends Enum<FeatureTypeInternal> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ FeatureTypeInternal[] $VALUES = null;
    public static final FeatureTypeInternal DYNAMIC_RANGE = null;
    public static final FeatureTypeInternal FPS_RANGE = null;
    public static final FeatureTypeInternal IMAGE_FORMAT = null;
    public static final FeatureTypeInternal RECORDING_QUALITY = null;
    public static final FeatureTypeInternal VIDEO_STABILIZATION = null;

    private static final /* synthetic */ FeatureTypeInternal[] $values() {
        return new FeatureTypeInternal[]{DYNAMIC_RANGE, FPS_RANGE, VIDEO_STABILIZATION, IMAGE_FORMAT, RECORDING_QUALITY};
    }

    static {
        DYNAMIC_RANGE = new FeatureTypeInternal("DYNAMIC_RANGE", 0);
        FPS_RANGE = new FeatureTypeInternal("FPS_RANGE", 1);
        VIDEO_STABILIZATION = new FeatureTypeInternal("VIDEO_STABILIZATION", 2);
        IMAGE_FORMAT = new FeatureTypeInternal("IMAGE_FORMAT", 3);
        RECORDING_QUALITY = new FeatureTypeInternal("RECORDING_QUALITY", 4);
        FeatureTypeInternal[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    FeatureTypeInternal(String r1, int r2) {
    }

    public static EnumEntries<FeatureTypeInternal> getEntries() {
        return $ENTRIES;
    }

    public static FeatureTypeInternal valueOf(String r1) {
        return (FeatureTypeInternal) Enum.valueOf(FeatureTypeInternal.class, r1);
    }

    public static FeatureTypeInternal[] values() {
        return (FeatureTypeInternal[]) $VALUES.clone();
    }
}
