package androidx.media3.common.text;

import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;

/* loaded from: classes3.dex */
public final class VoiceSpan {
    private static final String FIELD_NAME = null;
    public final String name;

    static {
        FIELD_NAME = Util.intToStringMaxRadix(0);
    }

    public VoiceSpan(String r1) {
        this.name = r1;
    }

    public static VoiceSpan fromBundle(Bundle r2) {
        return new VoiceSpan((String) Assertions.checkNotNull(r2.getString(FIELD_NAME)));
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putString(FIELD_NAME, this.name);
        return r0;
    }
}
