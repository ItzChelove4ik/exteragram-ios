package com.exteragram.messenger.utils.ui;

import com.exteragram.messenger.ExteraConfig;

/* loaded from: classes4.dex */
public abstract class FabUiHelper {
    public static int getFabBottomMarginDp() {
        if (isMaterial3Fab() == false) goto L6;
        return 16;
    L6:
        return 14;
    }

    public static float getFabCornerRadiusDp() {
        if (ExteraConfig.getSquareFab() == false) goto L7;
        return UIUtil.getFabSquareCornerRadiusDp(getFabSizeDp());
    L7:
        return getFabSizeDp() / 2.0f;
    }

    public static float getFabElevationDp() {
        if (isMaterial3Fab() == false) goto L6;
        return 6.0f;
    L6:
        return 0.5f;
    }

    public static int getFabSizeDp() {
        if (isMaterial3Fab() == false) goto L6;
        return 56;
    L6:
        return 48;
    }

    public static float getSubFabBackgroundRadiusDp() {
        if (ExteraConfig.getSquareFab() == false) goto L11;
        if (isMaterial3Fab() == false) goto L8;
        return 12.0f;
    L8:
        return 10.0f;
    L11:
        return (getFabSizeDp() - (getSubFabPaddingDp() * 2.0f)) / 2.0f;
    }

    public static int getSubFabBottomMarginDp() {
        if (isMaterial3Fab() == false) goto L6;
        return 24;
    L6:
        return 14;
    }

    public static float getSubFabPaddingDp() {
        if (isMaterial3Fab() == false) goto L6;
        return 8.0f;
    L6:
        return 5.66f;
    }

    public static int getSubFabShadowColor() {
        if (isMaterial3Fab() == false) goto L6;
        return 805306368;
    L6:
        return 536870912;
    }

    public static float getSubFabShadowDyDp() {
        if (isMaterial3Fab() == false) goto L6;
        return 2.0f;
    L6:
        return 0.85f;
    }

    public static float getSubFabShadowRadiusDp() {
        if (isMaterial3Fab() == false) goto L6;
        return 6.0f;
    L6:
        return 2.667f;
    }

    public static boolean isMaterial3Fab() {
        return ExteraConfig.getNewFabStyle();
    }
}
