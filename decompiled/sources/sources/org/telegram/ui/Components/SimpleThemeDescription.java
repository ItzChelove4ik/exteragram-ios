package org.telegram.ui.Components;

import java.util.ArrayList;
import java.util.Objects;
import org.telegram.ui.ActionBar.ThemeDescription;

/* loaded from: classes7.dex */
public abstract class SimpleThemeDescription {
    public static void add(ArrayList<ThemeDescription> r1, Runnable r2, int... r3) {
        Objects.requireNonNull(r2);
        r1.addAll(createThemeDescriptions(new SimpleThemeDescription$$ExternalSyntheticLambda0(r2), r3));
    }

    public static ThemeDescription createThemeDescription(ThemeDescription.ThemeDescriptionDelegate r8, int r9) {
        return new ThemeDescription(null, 0, null, null, null, r8, r9);
    }

    public static ArrayList<ThemeDescription> createThemeDescriptions(ThemeDescription.ThemeDescriptionDelegate r4, int... r5) {
        ArrayList<ThemeDescription> r0 = new ArrayList(r5.length);
        int r1 = r5.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0.add(createThemeDescription(r4, r5[r2]));
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }
}
