package androidx.media3.common.text;

import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;

/* loaded from: classes3.dex */
public final class RubySpan {
    private static final String FIELD_POSITION = null;
    private static final String FIELD_TEXT = null;
    public final int position;
    public final String rubyText;

    static {
        FIELD_TEXT = Util.intToStringMaxRadix(0);
        FIELD_POSITION = Util.intToStringMaxRadix(1);
    }

    public RubySpan(String r1, int r2) {
        this.rubyText = r1;
        this.position = r2;
    }

    public static RubySpan fromBundle(Bundle r3) {
        return new RubySpan((String) Assertions.checkNotNull(r3.getString(FIELD_TEXT)), r3.getInt(FIELD_POSITION));
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putString(FIELD_TEXT, this.rubyText);
        r0.putInt(FIELD_POSITION, this.position);
        return r0;
    }
}
