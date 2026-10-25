package androidx.media3.common.text;

import android.os.Bundle;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import java.util.List;

/* loaded from: classes3.dex */
public final class CueGroup {
    private static final Ordering<Cue> CUES_PRIORITY_COMPARATOR = null;
    public static final CueGroup EMPTY_TIME_ZERO = null;
    private static final String FIELD_CUES = null;
    private static final String FIELD_PRESENTATION_TIME_US = null;
    public final ImmutableList<Cue> cues;
    public final long presentationTimeUs;

    /* renamed from: $r8$lambda$bYB0Gn-rA6IhiQww2EWZwTdn4d4, reason: not valid java name */
    public static /* synthetic */ Integer m754$r8$lambda$bYB0GnrA6IhiQww2EWZwTdn4d4(Cue r0) {
        return Integer.valueOf(r0.zIndex);
    }

    static {
        CUES_PRIORITY_COMPARATOR = Ordering.natural().onResultOf(new CueGroup$$ExternalSyntheticLambda0());
        EMPTY_TIME_ZERO = new CueGroup(ImmutableList.of(), 0);
        FIELD_CUES = Util.intToStringMaxRadix(0);
        FIELD_PRESENTATION_TIME_US = Util.intToStringMaxRadix(1);
    }

    public CueGroup(List<Cue> r2, long r3) {
        this.cues = ImmutableList.sortedCopyOf(CUES_PRIORITY_COMPARATOR, r2);
        this.presentationTimeUs = r3;
    }

    private static ImmutableList<Cue> filterOutBitmapCues(List<Cue> r3) {
        ImmutableList.Builder r0 = ImmutableList.builder();
        int r1 = 0;
    L4:
        if (r1 >= r3.size()) goto L11;
        if (r3.get(r1).bitmap != null) goto L9;
        r0.add(r3.get(r1));
    L9:
        r1 = r1 + 1;
        goto L4
    L11:
        return r0.build();
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putParcelableArrayList(FIELD_CUES, BundleCollectionUtil.toBundleArrayList(filterOutBitmapCues(this.cues), new CueGroup$$ExternalSyntheticLambda1()));
        r0.putLong(FIELD_PRESENTATION_TIME_US, this.presentationTimeUs);
        return r0;
    }
}
