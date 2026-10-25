package org.telegram.ui.Components.Premium;

/* loaded from: classes7.dex */
class DoubledLimitsBottomSheet$Limit {
    final int current;
    final int defaultLimit;
    final int premiumLimit;
    final String subtitle;
    final String title;
    public int yOffset;

    private DoubledLimitsBottomSheet$Limit(String r2, String r3, int r4, int r5) {
        this.current = -1;
        this.title = r2;
        this.subtitle = r3;
        this.defaultLimit = r4;
        this.premiumLimit = r5;
    }

    public /* synthetic */ DoubledLimitsBottomSheet$Limit(String r1, String r2, int r3, int r4, DoubledLimitsBottomSheetIA r5) {
        this(r1, r2, r3, r4);
    }
}
