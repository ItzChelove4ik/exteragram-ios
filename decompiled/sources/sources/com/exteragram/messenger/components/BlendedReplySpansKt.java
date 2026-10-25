package com.exteragram.messenger.components;

import android.graphics.Color;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.ui.ActionBar.Theme;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\u001a\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"baseHsv", _UrlKt.FRAGMENT_ENCODE_SET, "nameHsv", "blendedReplyColor", _UrlKt.FRAGMENT_ENCODE_SET, "strength", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public abstract class BlendedReplySpansKt {
    private static final float[] baseHsv = null;
    private static final float[] nameHsv = null;

    static {
        baseHsv = new float[3];
        nameHsv = new float[3];
    }

    private static final int blendedReplyColor(float r6) {
        int r0 = Theme.chat_replyTextPaint.getColor();
        int r1 = Theme.chat_replyNamePaint.getColor();
        float[] r2 = baseHsv;
        Color.colorToHSV(r0, r2);
        float[] r3 = nameHsv;
        Color.colorToHSV(r1, r3);
        if (r2[2] >= 0.35f) goto L5;
        r2[2] = 0.5f;
    L5:
        r2[0] = r3[0];
        r2[1] = RangesKt.coerceIn(r3[1] * r6, 0.0f, 1.0f);
        return Color.HSVToColor(Color.alpha(r0), r2);
    }

    public static /* synthetic */ int blendedReplyColor$default(float r0, int r1, Object r2) {
        if ((r1 & 1) == 0) goto L6;
        r0 = 0.75f;
    L6:
        return blendedReplyColor(r0);
    }
}
