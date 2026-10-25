package com.google.android.material.color;

import com.google.android.material.R;
import com.google.android.material.color.utilities.DynamicColor;
import com.google.android.material.color.utilities.DynamicScheme;
import com.google.android.material.color.utilities.MaterialDynamicColors;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public final class MaterialColorUtilitiesHelper {
    private static final Map<Integer, DynamicColor> colorResourceIdToColorValue = null;
    private static final MaterialDynamicColors dynamicColors = null;

    static {
        MaterialDynamicColors r0 = new MaterialDynamicColors();
        dynamicColors = r0;
        HashMap r1 = new HashMap();
        r1.put(Integer.valueOf(R.color.material_personalized_color_primary), r0.primary());
        r1.put(Integer.valueOf(R.color.material_personalized_color_on_primary), r0.onPrimary());
        r1.put(Integer.valueOf(R.color.material_personalized_color_primary_inverse), r0.inversePrimary());
        r1.put(Integer.valueOf(R.color.material_personalized_color_primary_container), r0.primaryContainer());
        r1.put(Integer.valueOf(R.color.material_personalized_color_on_primary_container), r0.onPrimaryContainer());
        r1.put(Integer.valueOf(R.color.material_personalized_color_secondary), r0.secondary());
        r1.put(Integer.valueOf(R.color.material_personalized_color_on_secondary), r0.onSecondary());
        r1.put(Integer.valueOf(R.color.material_personalized_color_secondary_container), r0.secondaryContainer());
        r1.put(Integer.valueOf(R.color.material_personalized_color_on_secondary_container), r0.onSecondaryContainer());
        r1.put(Integer.valueOf(R.color.material_personalized_color_tertiary), r0.tertiary());
        r1.put(Integer.valueOf(R.color.material_personalized_color_on_tertiary), r0.onTertiary());
        r1.put(Integer.valueOf(R.color.material_personalized_color_tertiary_container), r0.tertiaryContainer());
        r1.put(Integer.valueOf(R.color.material_personalized_color_on_tertiary_container), r0.onTertiaryContainer());
        r1.put(Integer.valueOf(R.color.material_personalized_color_background), r0.background());
        r1.put(Integer.valueOf(R.color.material_personalized_color_on_background), r0.onBackground());
        r1.put(Integer.valueOf(R.color.material_personalized_color_surface), r0.surface());
        r1.put(Integer.valueOf(R.color.material_personalized_color_on_surface), r0.onSurface());
        r1.put(Integer.valueOf(R.color.material_personalized_color_surface_variant), r0.surfaceVariant());
        r1.put(Integer.valueOf(R.color.material_personalized_color_on_surface_variant), r0.onSurfaceVariant());
        r1.put(Integer.valueOf(R.color.material_personalized_color_surface_inverse), r0.inverseSurface());
        r1.put(Integer.valueOf(R.color.material_personalized_color_on_surface_inverse), r0.inverseOnSurface());
        r1.put(Integer.valueOf(R.color.material_personalized_color_surface_bright), r0.surfaceBright());
        r1.put(Integer.valueOf(R.color.material_personalized_color_surface_dim), r0.surfaceDim());
        r1.put(Integer.valueOf(R.color.material_personalized_color_surface_container), r0.surfaceContainer());
        r1.put(Integer.valueOf(R.color.material_personalized_color_surface_container_low), r0.surfaceContainerLow());
        r1.put(Integer.valueOf(R.color.material_personalized_color_surface_container_high), r0.surfaceContainerHigh());
        r1.put(Integer.valueOf(R.color.material_personalized_color_surface_container_lowest), r0.surfaceContainerLowest());
        r1.put(Integer.valueOf(R.color.material_personalized_color_surface_container_highest), r0.surfaceContainerHighest());
        r1.put(Integer.valueOf(R.color.material_personalized_color_outline), r0.outline());
        r1.put(Integer.valueOf(R.color.material_personalized_color_outline_variant), r0.outlineVariant());
        r1.put(Integer.valueOf(R.color.material_personalized_color_error), r0.error());
        r1.put(Integer.valueOf(R.color.material_personalized_color_on_error), r0.onError());
        r1.put(Integer.valueOf(R.color.material_personalized_color_error_container), r0.errorContainer());
        r1.put(Integer.valueOf(R.color.material_personalized_color_on_error_container), r0.onErrorContainer());
        r1.put(Integer.valueOf(R.color.material_personalized_color_control_activated), r0.controlActivated());
        r1.put(Integer.valueOf(R.color.material_personalized_color_control_normal), r0.controlNormal());
        r1.put(Integer.valueOf(R.color.material_personalized_color_control_highlight), r0.controlHighlight());
        r1.put(Integer.valueOf(R.color.material_personalized_color_text_primary_inverse), r0.textPrimaryInverse());
        r1.put(Integer.valueOf(R.color.material_personalized_color_text_secondary_and_tertiary_inverse), r0.textSecondaryAndTertiaryInverse());
        r1.put(Integer.valueOf(R.color.material_personalized_color_text_secondary_and_tertiary_inverse_disabled), r0.textSecondaryAndTertiaryInverseDisabled());
        r1.put(Integer.valueOf(R.color.material_personalized_color_text_primary_inverse_disable_only), r0.textPrimaryInverseDisableOnly());
        r1.put(Integer.valueOf(R.color.material_personalized_color_text_hint_foreground_inverse), r0.textHintInverse());
        colorResourceIdToColorValue = Collections.unmodifiableMap(r1);
    }

    private MaterialColorUtilitiesHelper() {
    }

    public static Map<Integer, Integer> createColorResourcesIdsToColorValues(DynamicScheme r4) {
        HashMap r0 = new HashMap();
        Iterator<Map.Entry<Integer, DynamicColor>> r1 = colorResourceIdToColorValue.entrySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L7;
        Map.Entry<Integer, DynamicColor> r2 = r1.next();
        r0.put(r2.getKey(), Integer.valueOf(r2.getValue().getArgb(r4)));
        goto L4
    L7:
        return Collections.unmodifiableMap(r0);
    }
}
