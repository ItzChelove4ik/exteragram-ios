package com.google.android.material.color;

import androidx.appcompat.R$attr;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;

/* loaded from: classes5.dex */
public final class HarmonizedColorAttributes {
    private static final int[] HARMONIZED_MATERIAL_ATTRIBUTES = null;
    private final int[] attributes;
    private final int themeOverlay;

    static {
        HARMONIZED_MATERIAL_ATTRIBUTES = new int[]{R$attr.colorError, R.attr.colorOnError, R.attr.colorErrorContainer, R.attr.colorOnErrorContainer};
    }

    private HarmonizedColorAttributes(int[] r2, int r3) {
        if (r3 != 0) goto L5;
    L9:
        this.attributes = r2;
        this.themeOverlay = r3;
        return;
    L5:
        if (r2.length != 0) goto L9;
        f$$ExternalSyntheticBUOutline1.m("Theme overlay should be used with the accompanying int[] attributes.");
        throw null;
    }

    public static HarmonizedColorAttributes create(int[] r2) {
        return new HarmonizedColorAttributes(r2, 0);
    }

    public static HarmonizedColorAttributes create(int[] r1, int r2) {
        return new HarmonizedColorAttributes(r1, r2);
    }

    public static HarmonizedColorAttributes createMaterialDefaults() {
        return create(HARMONIZED_MATERIAL_ATTRIBUTES, R.style.ThemeOverlay_Material3_HarmonizedColors);
    }

    public int[] getAttributes() {
        return this.attributes;
    }

    public int getThemeOverlay() {
        return this.themeOverlay;
    }
}
