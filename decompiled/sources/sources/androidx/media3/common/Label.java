package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.Objects;

/* loaded from: classes3.dex */
public class Label {
    private static final String FIELD_LANGUAGE_INDEX = null;
    private static final String FIELD_VALUE_INDEX = null;
    public final String language;
    public final String value;

    static {
        FIELD_LANGUAGE_INDEX = Util.intToStringMaxRadix(0);
        FIELD_VALUE_INDEX = Util.intToStringMaxRadix(1);
    }

    public Label(String r1, String r2) {
        this.language = Util.normalizeLanguageCode(r1);
        this.value = r2;
    }

    public static Label fromBundle(Bundle r3) {
        return new Label(r3.getString(FIELD_LANGUAGE_INDEX), (String) Assertions.checkNotNull(r3.getString(FIELD_VALUE_INDEX)));
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L15:
        return false;
    L8:
        if (getClass() != r5.getClass()) goto L15;
        Label r52 = (Label) r5;
        if (Objects.equals(this.language, r52.language) == false) goto L15;
        if (Objects.equals(this.value, r52.value) == false) goto L15;
        return true;
    }

    public int hashCode() {
        int r0 = this.value.hashCode() * 31;
        String r1 = this.language;
        if (r1 == null) goto L5;
        int r12 = r1.hashCode();
    L7:
        return r0 + r12;
    L5:
        r12 = 0;
        goto L7
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        String r1 = this.language;
        if (r1 == null) goto L5;
        r0.putString(FIELD_LANGUAGE_INDEX, r1);
    L5:
        r0.putString(FIELD_VALUE_INDEX, this.value);
        return r0;
    }
}
