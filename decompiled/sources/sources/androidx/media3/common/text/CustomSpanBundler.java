package androidx.media3.common.text;

import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.ArrayList;

/* loaded from: classes3.dex */
abstract class CustomSpanBundler {
    private static final String FIELD_END_INDEX = null;
    private static final String FIELD_FLAGS = null;
    private static final String FIELD_PARAMS = null;
    private static final String FIELD_START_INDEX = null;
    private static final String FIELD_TYPE = null;

    static {
        FIELD_START_INDEX = Util.intToStringMaxRadix(0);
        FIELD_END_INDEX = Util.intToStringMaxRadix(1);
        FIELD_FLAGS = Util.intToStringMaxRadix(2);
        FIELD_TYPE = Util.intToStringMaxRadix(3);
        FIELD_PARAMS = Util.intToStringMaxRadix(4);
    }

    public static ArrayList<Bundle> bundleCustomSpans(Spanned r8) {
        ArrayList<Bundle> r0 = new ArrayList();
        int r3 = 0;
        RubySpan[] r1 = (RubySpan[]) r8.getSpans(0, r8.length(), RubySpan.class);
        int r2 = r1.length;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L5;
        RubySpan r5 = r1[r4];
        r0.add(spanToBundle(r8, r5, 1, r5.toBundle()));
        r4 = r4 + 1;
        goto L3
    L5:
        TextEmphasisSpan[] r12 = (TextEmphasisSpan[]) r8.getSpans(0, r8.length(), TextEmphasisSpan.class);
        int r22 = r12.length;
        int r42 = 0;
    L6:
        if (r42 >= r22) goto L8;
        TextEmphasisSpan r52 = r12[r42];
        r0.add(spanToBundle(r8, r52, 2, r52.toBundle()));
        r42 = r42 + 1;
        goto L6
    L8:
        HorizontalTextInVerticalContextSpan[] r13 = (HorizontalTextInVerticalContextSpan[]) r8.getSpans(0, r8.length(), HorizontalTextInVerticalContextSpan.class);
        int r23 = r13.length;
        int r43 = 0;
    L9:
        if (r43 >= r23) goto L11;
        r0.add(spanToBundle(r8, r13[r43], 3, null));
        r43 = r43 + 1;
        goto L9
    L11:
        VoiceSpan[] r14 = (VoiceSpan[]) r8.getSpans(0, r8.length(), VoiceSpan.class);
        int r24 = r14.length;
    L12:
        if (r3 >= r24) goto L14;
        VoiceSpan r44 = r14[r3];
        r0.add(spanToBundle(r8, r44, 4, r44.toBundle()));
        r3 = r3 + 1;
        goto L12
    L14:
        return r0;
    }

    private static Bundle spanToBundle(Spanned r3, Object r4, int r5, Bundle r6) {
        Bundle r0 = new Bundle();
        r0.putInt(FIELD_START_INDEX, r3.getSpanStart(r4));
        r0.putInt(FIELD_END_INDEX, r3.getSpanEnd(r4));
        r0.putInt(FIELD_FLAGS, r3.getSpanFlags(r4));
        r0.putInt(FIELD_TYPE, r5);
        if (r6 == null) goto L5;
        r0.putBundle(FIELD_PARAMS, r6);
    L5:
        return r0;
    }

    public static void unbundleAndApplyCustomSpan(Bundle r5, Spannable r6) {
        int r0 = r5.getInt(FIELD_START_INDEX);
        int r1 = r5.getInt(FIELD_END_INDEX);
        int r2 = r5.getInt(FIELD_FLAGS);
        int r3 = r5.getInt(FIELD_TYPE, -1);
        Bundle r52 = r5.getBundle(FIELD_PARAMS);
        if (r3 != 1) goto L5;
        r6.setSpan(RubySpan.fromBundle((Bundle) Assertions.checkNotNull(r52)), r0, r1, r2);
        return;
    L5:
        if (r3 != 2) goto L7;
        r6.setSpan(TextEmphasisSpan.fromBundle((Bundle) Assertions.checkNotNull(r52)), r0, r1, r2);
        return;
    L7:
        if (r3 != 3) goto L9;
        r6.setSpan(new HorizontalTextInVerticalContextSpan(), r0, r1, r2);
        return;
    L9:
        if (r3 == 4) goto L11;
        return;
    L11:
        r6.setSpan(VoiceSpan.fromBundle((Bundle) Assertions.checkNotNull(r52)), r0, r1, r2);
    }
}
