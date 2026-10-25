package com.google.android.material.elevation;

import android.content.Context;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;

/* loaded from: classes5.dex */
public enum SurfaceColors extends Enum<SurfaceColors> {
    private static final /* synthetic */ SurfaceColors[] $VALUES = null;
    public static final SurfaceColors SURFACE_0 = null;
    public static final SurfaceColors SURFACE_1 = null;
    public static final SurfaceColors SURFACE_2 = null;
    public static final SurfaceColors SURFACE_3 = null;
    public static final SurfaceColors SURFACE_4 = null;
    public static final SurfaceColors SURFACE_5 = null;
    private final int elevationResId;

    private static /* synthetic */ SurfaceColors[] $values() {
        return new SurfaceColors[]{SURFACE_0, SURFACE_1, SURFACE_2, SURFACE_3, SURFACE_4, SURFACE_5};
    }

    static {
        SURFACE_0 = new SurfaceColors("SURFACE_0", 0, R.dimen.m3_sys_elevation_level0);
        SURFACE_1 = new SurfaceColors("SURFACE_1", 1, R.dimen.m3_sys_elevation_level1);
        SURFACE_2 = new SurfaceColors("SURFACE_2", 2, R.dimen.m3_sys_elevation_level2);
        SURFACE_3 = new SurfaceColors("SURFACE_3", 3, R.dimen.m3_sys_elevation_level3);
        SURFACE_4 = new SurfaceColors("SURFACE_4", 4, R.dimen.m3_sys_elevation_level4);
        SURFACE_5 = new SurfaceColors("SURFACE_5", 5, R.dimen.m3_sys_elevation_level5);
        $VALUES = $values();
    }

    SurfaceColors(String r1, int r2, int r3) {
        this.elevationResId = r3;
    }

    public static int getColorForElevation(Context r3, float r4) {
        return new ElevationOverlayProvider(r3).compositeOverlay(MaterialColors.getColor(r3, R.attr.colorSurface, 0), r4);
    }

    public static SurfaceColors valueOf(String r1) {
        return (SurfaceColors) Enum.valueOf(SurfaceColors.class, r1);
    }

    public static SurfaceColors[] values() {
        return (SurfaceColors[]) $VALUES.clone();
    }

    public int getColor(Context r2) {
        return getColorForElevation(r2, r2.getResources().getDimension(this.elevationResId));
    }
}
