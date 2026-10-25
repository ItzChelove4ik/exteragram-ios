package androidx.media3.common.text;

import android.os.Bundle;
import androidx.media3.common.util.Util;

/* loaded from: classes3.dex */
public final class TextEmphasisSpan {
    private static final String FIELD_MARK_FILL = null;
    private static final String FIELD_MARK_SHAPE = null;
    private static final String FIELD_POSITION = null;
    public int markFill;
    public int markShape;
    public final int position;

    static {
        FIELD_MARK_SHAPE = Util.intToStringMaxRadix(0);
        FIELD_MARK_FILL = Util.intToStringMaxRadix(1);
        FIELD_POSITION = Util.intToStringMaxRadix(2);
    }

    public TextEmphasisSpan(int r1, int r2, int r3) {
        this.markShape = r1;
        this.markFill = r2;
        this.position = r3;
    }

    public static TextEmphasisSpan fromBundle(Bundle r4) {
        return new TextEmphasisSpan(r4.getInt(FIELD_MARK_SHAPE), r4.getInt(FIELD_MARK_FILL), r4.getInt(FIELD_POSITION));
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putInt(FIELD_MARK_SHAPE, this.markShape);
        r0.putInt(FIELD_MARK_FILL, this.markFill);
        r0.putInt(FIELD_POSITION, this.position);
        return r0;
    }
}
