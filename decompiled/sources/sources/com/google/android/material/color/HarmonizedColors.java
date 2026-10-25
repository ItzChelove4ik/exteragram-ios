package com.google.android.material.color;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.view.ContextThemeWrapper;
import androidx.core.content.ContextCompat;
import com.google.android.material.R;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class HarmonizedColors {
    private static final String TAG = "HarmonizedColors";

    static {
    }

    private HarmonizedColors() {
    }

    private static void addHarmonizedColorAttributesToReplacementMap(Map<Integer, Integer> r4, TypedArray r5, TypedArray r6, int r7) {
        if (r6 != null) goto L5;
        r6 = r5;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= r5.getIndexCount()) goto L16;
        int r2 = r6.getResourceId(r1, 0);
        if (r2 == 0) goto L15;
        if (r5.hasValue(r1) == false) goto L15;
        if (ResourcesLoaderUtils.isColorResource(r5.getType(r1)) == false) goto L15;
        r4.put(Integer.valueOf(r2), Integer.valueOf(MaterialColors.harmonize(r5.getColor(r1, 0), r7)));
    L15:
        r1 = r1 + 1;
        goto L7
    }

    public static void applyToContextIfAvailable(Context r2, HarmonizedColorsOptions r3) {
        if (isHarmonizedColorAvailable() == false) goto L12;
        Map<Integer, Integer> r0 = createHarmonizedColorReplacementMap(r2, r3);
        int r32 = r3.getThemeOverlayResourceId(0);
        if (ResourcesLoaderUtils.addResourcesLoaderToContext(r2, r0) == false) goto L10;
        if (r32 == 0) goto L11;
        ThemeUtils.applyThemeOverlay(r2, r32);
        return;
    L11:
        return;
    L10:
        return;
    }

    private static Map<Integer, Integer> createHarmonizedColorReplacementMap(Context r7, HarmonizedColorsOptions r8) {
        HashMap r0 = new HashMap();
        int r1 = MaterialColors.getColor(r7, r8.getColorAttributeToHarmonizeWith(), TAG);
        int[] r2 = r8.getColorResourceIds();
        int r3 = r2.length;
        int r4 = 0;
    L3:
        if (r4 >= r3) goto L5;
        int r5 = r2[r4];
        int r6 = MaterialColors.harmonize(ContextCompat.getColor(r7, r5), r1);
        r0.put(Integer.valueOf(r5), Integer.valueOf(r6));
        r4 = r4 + 1;
        goto L3
    L5:
        HarmonizedColorAttributes r82 = r8.getColorAttributes();
        if (r82 == null) goto L16;
        int[] r22 = r82.getAttributes();
        if (r22.length <= 0) goto L16;
        int r83 = r82.getThemeOverlay();
        TypedArray r32 = r7.obtainStyledAttributes(r22);
        if (r83 == 0) goto L12;
        TypedArray r72 = new ContextThemeWrapper(r7, r83).obtainStyledAttributes(r22);
    L13:
        addHarmonizedColorAttributesToReplacementMap(r0, r32, r72, r1);
        r32.recycle();
        if (r72 == null) goto L16;
        r72.recycle();
        goto L16
    L12:
        r72 = null;
    L16:
        return r0;
    }

    public static boolean isHarmonizedColorAvailable() {
        if (Build.VERSION.SDK_INT < 30) goto L6;
        return true;
    L6:
        return false;
    }

    public static Context wrapContextIfAvailable(Context r2, HarmonizedColorsOptions r3) {
        if (isHarmonizedColorAvailable() == false) goto L8;
        Map<Integer, Integer> r0 = createHarmonizedColorReplacementMap(r2, r3);
        ContextThemeWrapper r1 = new ContextThemeWrapper(r2, r3.getThemeOverlayResourceId(R.style.ThemeOverlay_Material3_HarmonizedColors_Empty));
        r1.applyOverrideConfiguration(new Configuration());
        if (ResourcesLoaderUtils.addResourcesLoaderToContext(r1, r0) == false) goto L8;
        return r1;
    L8:
        return r2;
    }
}
