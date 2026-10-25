package androidx.camera.camera2.internal;

import android.hardware.camera2.params.DynamicRangeProfiles;
import androidx.camera.core.DynamicRange;
import com.google.android.gms.cast.MediaStatus;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\u0006J\u001d\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\b\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/camera/camera2/internal/DynamicRangeConversions;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "PROFILE_TO_DR_MAP", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/DynamicRange;", "DR_TO_PROFILE_MAP", _UrlKt.FRAGMENT_ENCODE_SET, "profileToDynamicRange", "profile", "dynamicRangeToFirstSupportedProfile", "dynamicRange", "dynamicRangeProfiles", "Landroid/hardware/camera2/params/DynamicRangeProfiles;", "(Landroidx/camera/core/DynamicRange;Landroid/hardware/camera2/params/DynamicRangeProfiles;)Ljava/lang/Long;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DynamicRangeConversions {
    private static final Map<DynamicRange, List<Long>> DR_TO_PROFILE_MAP = null;
    public static final DynamicRangeConversions INSTANCE = null;
    private static final Map<Long, DynamicRange> PROFILE_TO_DR_MAP = null;

    static {
        INSTANCE = new DynamicRangeConversions();
        LinkedHashMap r0 = new LinkedHashMap();
        PROFILE_TO_DR_MAP = r0;
        LinkedHashMap r1 = new LinkedHashMap();
        DR_TO_PROFILE_MAP = r1;
        DynamicRange r3 = DynamicRange.SDR;
        r0.put(1L, r3);
        r1.put(r3, CollectionsKt.listOf(1L));
        r0.put(2L, DynamicRange.HLG_10_BIT);
        r1.put(r0.get(2L), CollectionsKt.listOf(2L));
        DynamicRange r32 = DynamicRange.HDR10_10_BIT;
        r0.put(4L, r32);
        r1.put(r32, CollectionsKt.listOf(4L));
        DynamicRange r33 = DynamicRange.HDR10_PLUS_10_BIT;
        r0.put(8L, r33);
        r1.put(r33, CollectionsKt.listOf(8L));
        List<Long> r02 = CollectionsKt.listOf(new Long[]{64L, 128L, 16L, 32L});
        Iterator<Long> r12 = r02.iterator();
    L4:
        if (r12.hasNext() == false) goto L6;
        long r2 = r12.next().longValue();
        PROFILE_TO_DR_MAP.put(Long.valueOf(r2), DynamicRange.DOLBY_VISION_10_BIT);
        goto L4
    L6:
        DR_TO_PROFILE_MAP.put(DynamicRange.DOLBY_VISION_10_BIT, r02);
        List<Long> r03 = CollectionsKt.listOf(new Long[]{1024L, Long.valueOf(MediaStatus.COMMAND_QUEUE_REPEAT_ONE), 256L, 512L});
        Iterator<Long> r13 = r03.iterator();
    L8:
        if (r13.hasNext() == false) goto L10;
        long r22 = r13.next().longValue();
        PROFILE_TO_DR_MAP.put(Long.valueOf(r22), DynamicRange.DOLBY_VISION_8_BIT);
        goto L8
    L10:
        DR_TO_PROFILE_MAP.put(DynamicRange.DOLBY_VISION_8_BIT, r03);
    }

    private DynamicRangeConversions() {
    }

    public final Long dynamicRangeToFirstSupportedProfile(DynamicRange r3, DynamicRangeProfiles r4) {
        List<Long> r2 = DR_TO_PROFILE_MAP.get(r3);
        if (r2 == null) goto L11;
        Set<Long> r32 = r4.getSupportedProfiles();
        Iterator<Long> r22 = r2.iterator();
    L6:
        if (r22.hasNext() == false) goto L16;
        long r0 = r22.next().longValue();
        if (r32.contains(Long.valueOf(r0)) == false) goto L6;
        return Long.valueOf(r0);
    L16:
        return null;
    L11:
        return null;
    }

    public final DynamicRange profileToDynamicRange(long r1) {
        return PROFILE_TO_DR_MAP.get(Long.valueOf(r1));
    }
}
