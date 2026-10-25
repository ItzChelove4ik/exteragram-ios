package com.google.android.material.slider;

import java.util.Locale;

/* loaded from: classes5.dex */
public final class BasicLabelFormatter implements LabelFormatter {
    private static final int BILLION = 1000000000;
    private static final int MILLION = 1000000;
    private static final int THOUSAND = 1000;
    private static final long TRILLION = 1000000000000L;

    public BasicLabelFormatter() {
    }

    @Override // com.google.android.material.slider.LabelFormatter
    public String getFormattedValue(float r2) {
        if (r2 < 1.0E12f) goto L7;
        return String.format(Locale.US, "%.1fT", new Object[]{Float.valueOf(r2 / 1.0E12f)});
    L7:
        if (r2 < 1.0E9f) goto L11;
        return String.format(Locale.US, "%.1fB", new Object[]{Float.valueOf(r2 / 1.0E9f)});
    L11:
        if (r2 < 1000000.0f) goto L15;
        return String.format(Locale.US, "%.1fM", new Object[]{Float.valueOf(r2 / 1000000.0f)});
    L15:
        if (r2 < 1000.0f) goto L19;
        return String.format(Locale.US, "%.1fK", new Object[]{Float.valueOf(r2 / 1000.0f)});
    L19:
        return String.format(Locale.US, "%.0f", new Object[]{Float.valueOf(r2)});
    }
}
