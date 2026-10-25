package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public class DynamicScheme {
    public final double contrastLevel;
    public final TonalPalette errorPalette;
    public final boolean isDark;
    public final TonalPalette neutralPalette;
    public final TonalPalette neutralVariantPalette;
    public final TonalPalette primaryPalette;
    public final TonalPalette secondaryPalette;
    public final int sourceColorArgb;
    public final Hct sourceColorHct;
    public final TonalPalette tertiaryPalette;
    public final Variant variant;

    public DynamicScheme(Hct r2, Variant r3, boolean r4, double r5, TonalPalette r7, TonalPalette r8, TonalPalette r9, TonalPalette r10, TonalPalette r11) {
        this.sourceColorArgb = r2.toInt();
        this.sourceColorHct = r2;
        this.variant = r3;
        this.isDark = r4;
        this.contrastLevel = r5;
        this.primaryPalette = r7;
        this.secondaryPalette = r8;
        this.tertiaryPalette = r9;
        this.neutralPalette = r10;
        this.neutralVariantPalette = r11;
        this.errorPalette = TonalPalette.fromHueAndChroma(25.0d, 84.0d);
    }

    public static double getRotatedHue(Hct r8, double[] r9, double[] r10) {
        double r0 = r8.getHue();
        int r3 = 0;
        if (r10.length == 1) goto L5;
        int r82 = r9.length;
    L8:
        if (r3 > (r82 - 2)) goto L16;
        double r4 = r9[r3];
        int r2 = r3 + 1;
        double r6 = r9[r2];
        if (r4 >= r0) goto L15;
        if (r0 >= r6) goto L15;
        return MathUtils.sanitizeDegreesDouble(r0 + r10[r3]);
    L15:
        r3 = r2;
        goto L8
    L16:
        return r0;
    L5:
        return MathUtils.sanitizeDegreesDouble(r0 + r10[0]);
    }

    public int getArgb(DynamicColor r1) {
        return r1.getArgb(this);
    }

    public int getBackground() {
        return getArgb(new MaterialDynamicColors().background());
    }

    public int getControlActivated() {
        return getArgb(new MaterialDynamicColors().controlActivated());
    }

    public int getControlHighlight() {
        return getArgb(new MaterialDynamicColors().controlHighlight());
    }

    public int getControlNormal() {
        return getArgb(new MaterialDynamicColors().controlNormal());
    }

    public int getError() {
        return getArgb(new MaterialDynamicColors().error());
    }

    public int getErrorContainer() {
        return getArgb(new MaterialDynamicColors().errorContainer());
    }

    public Hct getHct(DynamicColor r1) {
        return r1.getHct(this);
    }

    public int getInverseOnSurface() {
        return getArgb(new MaterialDynamicColors().inverseOnSurface());
    }

    public int getInversePrimary() {
        return getArgb(new MaterialDynamicColors().inversePrimary());
    }

    public int getInverseSurface() {
        return getArgb(new MaterialDynamicColors().inverseSurface());
    }

    public int getNeutralPaletteKeyColor() {
        return getArgb(new MaterialDynamicColors().neutralPaletteKeyColor());
    }

    public int getNeutralVariantPaletteKeyColor() {
        return getArgb(new MaterialDynamicColors().neutralVariantPaletteKeyColor());
    }

    public int getOnBackground() {
        return getArgb(new MaterialDynamicColors().onBackground());
    }

    public int getOnError() {
        return getArgb(new MaterialDynamicColors().onError());
    }

    public int getOnErrorContainer() {
        return getArgb(new MaterialDynamicColors().onErrorContainer());
    }

    public int getOnPrimary() {
        return getArgb(new MaterialDynamicColors().onPrimary());
    }

    public int getOnPrimaryContainer() {
        return getArgb(new MaterialDynamicColors().onPrimaryContainer());
    }

    public int getOnPrimaryFixed() {
        return getArgb(new MaterialDynamicColors().onPrimaryFixed());
    }

    public int getOnPrimaryFixedVariant() {
        return getArgb(new MaterialDynamicColors().onPrimaryFixedVariant());
    }

    public int getOnSecondary() {
        return getArgb(new MaterialDynamicColors().onSecondary());
    }

    public int getOnSecondaryContainer() {
        return getArgb(new MaterialDynamicColors().onSecondaryContainer());
    }

    public int getOnSecondaryFixed() {
        return getArgb(new MaterialDynamicColors().onSecondaryFixed());
    }

    public int getOnSecondaryFixedVariant() {
        return getArgb(new MaterialDynamicColors().onSecondaryFixedVariant());
    }

    public int getOnSurface() {
        return getArgb(new MaterialDynamicColors().onSurface());
    }

    public int getOnSurfaceVariant() {
        return getArgb(new MaterialDynamicColors().onSurfaceVariant());
    }

    public int getOnTertiary() {
        return getArgb(new MaterialDynamicColors().onTertiary());
    }

    public int getOnTertiaryContainer() {
        return getArgb(new MaterialDynamicColors().onTertiaryContainer());
    }

    public int getOnTertiaryFixed() {
        return getArgb(new MaterialDynamicColors().onTertiaryFixed());
    }

    public int getOnTertiaryFixedVariant() {
        return getArgb(new MaterialDynamicColors().onTertiaryFixedVariant());
    }

    public int getOutline() {
        return getArgb(new MaterialDynamicColors().outline());
    }

    public int getOutlineVariant() {
        return getArgb(new MaterialDynamicColors().outlineVariant());
    }

    public int getPrimary() {
        return getArgb(new MaterialDynamicColors().primary());
    }

    public int getPrimaryContainer() {
        return getArgb(new MaterialDynamicColors().primaryContainer());
    }

    public int getPrimaryFixed() {
        return getArgb(new MaterialDynamicColors().primaryFixed());
    }

    public int getPrimaryFixedDim() {
        return getArgb(new MaterialDynamicColors().primaryFixedDim());
    }

    public int getPrimaryPaletteKeyColor() {
        return getArgb(new MaterialDynamicColors().primaryPaletteKeyColor());
    }

    public int getScrim() {
        return getArgb(new MaterialDynamicColors().scrim());
    }

    public int getSecondary() {
        return getArgb(new MaterialDynamicColors().secondary());
    }

    public int getSecondaryContainer() {
        return getArgb(new MaterialDynamicColors().secondaryContainer());
    }

    public int getSecondaryFixed() {
        return getArgb(new MaterialDynamicColors().secondaryFixed());
    }

    public int getSecondaryFixedDim() {
        return getArgb(new MaterialDynamicColors().secondaryFixedDim());
    }

    public int getSecondaryPaletteKeyColor() {
        return getArgb(new MaterialDynamicColors().secondaryPaletteKeyColor());
    }

    public int getShadow() {
        return getArgb(new MaterialDynamicColors().shadow());
    }

    public int getSurface() {
        return getArgb(new MaterialDynamicColors().surface());
    }

    public int getSurfaceBright() {
        return getArgb(new MaterialDynamicColors().surfaceBright());
    }

    public int getSurfaceContainer() {
        return getArgb(new MaterialDynamicColors().surfaceContainer());
    }

    public int getSurfaceContainerHigh() {
        return getArgb(new MaterialDynamicColors().surfaceContainerHigh());
    }

    public int getSurfaceContainerHighest() {
        return getArgb(new MaterialDynamicColors().surfaceContainerHighest());
    }

    public int getSurfaceContainerLow() {
        return getArgb(new MaterialDynamicColors().surfaceContainerLow());
    }

    public int getSurfaceContainerLowest() {
        return getArgb(new MaterialDynamicColors().surfaceContainerLowest());
    }

    public int getSurfaceDim() {
        return getArgb(new MaterialDynamicColors().surfaceDim());
    }

    public int getSurfaceTint() {
        return getArgb(new MaterialDynamicColors().surfaceTint());
    }

    public int getSurfaceVariant() {
        return getArgb(new MaterialDynamicColors().surfaceVariant());
    }

    public int getTertiary() {
        return getArgb(new MaterialDynamicColors().tertiary());
    }

    public int getTertiaryContainer() {
        return getArgb(new MaterialDynamicColors().tertiaryContainer());
    }

    public int getTertiaryFixed() {
        return getArgb(new MaterialDynamicColors().tertiaryFixed());
    }

    public int getTertiaryFixedDim() {
        return getArgb(new MaterialDynamicColors().tertiaryFixedDim());
    }

    public int getTertiaryPaletteKeyColor() {
        return getArgb(new MaterialDynamicColors().tertiaryPaletteKeyColor());
    }

    public int getTextHintInverse() {
        return getArgb(new MaterialDynamicColors().textHintInverse());
    }

    public int getTextPrimaryInverse() {
        return getArgb(new MaterialDynamicColors().textPrimaryInverse());
    }

    public int getTextPrimaryInverseDisableOnly() {
        return getArgb(new MaterialDynamicColors().textPrimaryInverseDisableOnly());
    }

    public int getTextSecondaryAndTertiaryInverse() {
        return getArgb(new MaterialDynamicColors().textSecondaryAndTertiaryInverse());
    }

    public int getTextSecondaryAndTertiaryInverseDisabled() {
        return getArgb(new MaterialDynamicColors().textSecondaryAndTertiaryInverseDisabled());
    }
}
