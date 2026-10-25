package com.google.android.material.color.utilities;

@Deprecated
/* loaded from: classes5.dex */
public class Scheme {
    private int background;
    private int error;
    private int errorContainer;
    private int inverseOnSurface;
    private int inversePrimary;
    private int inverseSurface;
    private int onBackground;
    private int onError;
    private int onErrorContainer;
    private int onPrimary;
    private int onPrimaryContainer;
    private int onSecondary;
    private int onSecondaryContainer;
    private int onSurface;
    private int onSurfaceVariant;
    private int onTertiary;
    private int onTertiaryContainer;
    private int outline;
    private int outlineVariant;
    private int primary;
    private int primaryContainer;
    private int scrim;
    private int secondary;
    private int secondaryContainer;
    private int shadow;
    private int surface;
    private int surfaceVariant;
    private int tertiary;
    private int tertiaryContainer;

    public Scheme() {
    }

    public Scheme(int r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9, int r10, int r11, int r12, int r13, int r14, int r15, int r16, int r17, int r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25, int r26, int r27, int r28, int r29) {
        this.primary = r1;
        this.onPrimary = r2;
        this.primaryContainer = r3;
        this.onPrimaryContainer = r4;
        this.secondary = r5;
        this.onSecondary = r6;
        this.secondaryContainer = r7;
        this.onSecondaryContainer = r8;
        this.tertiary = r9;
        this.onTertiary = r10;
        this.tertiaryContainer = r11;
        this.onTertiaryContainer = r12;
        this.error = r13;
        this.onError = r14;
        this.errorContainer = r15;
        this.onErrorContainer = r16;
        this.background = r17;
        this.onBackground = r18;
        this.surface = r19;
        this.onSurface = r20;
        this.surfaceVariant = r21;
        this.onSurfaceVariant = r22;
        this.outline = r23;
        this.outlineVariant = r24;
        this.shadow = r25;
        this.scrim = r26;
        this.inverseSurface = r27;
        this.inverseOnSurface = r28;
        this.inversePrimary = r29;
    }

    public static Scheme dark(int r0) {
        return darkFromCorePalette(CorePalette.of(r0));
    }

    public static Scheme darkContent(int r0) {
        return darkFromCorePalette(CorePalette.contentOf(r0));
    }

    private static Scheme darkFromCorePalette(CorePalette r7) {
        return new Scheme().withPrimary(r7.a1.tone(80)).withOnPrimary(r7.a1.tone(20)).withPrimaryContainer(r7.a1.tone(30)).withOnPrimaryContainer(r7.a1.tone(90)).withSecondary(r7.a2.tone(80)).withOnSecondary(r7.a2.tone(20)).withSecondaryContainer(r7.a2.tone(30)).withOnSecondaryContainer(r7.a2.tone(90)).withTertiary(r7.a3.tone(80)).withOnTertiary(r7.a3.tone(20)).withTertiaryContainer(r7.a3.tone(30)).withOnTertiaryContainer(r7.a3.tone(90)).withError(r7.error.tone(80)).withOnError(r7.error.tone(20)).withErrorContainer(r7.error.tone(30)).withOnErrorContainer(r7.error.tone(80)).withBackground(r7.n1.tone(10)).withOnBackground(r7.n1.tone(90)).withSurface(r7.n1.tone(10)).withOnSurface(r7.n1.tone(90)).withSurfaceVariant(r7.n2.tone(30)).withOnSurfaceVariant(r7.n2.tone(80)).withOutline(r7.n2.tone(60)).withOutlineVariant(r7.n2.tone(30)).withShadow(r7.n1.tone(0)).withScrim(r7.n1.tone(0)).withInverseSurface(r7.n1.tone(90)).withInverseOnSurface(r7.n1.tone(20)).withInversePrimary(r7.a1.tone(40));
    }

    public static Scheme light(int r0) {
        return lightFromCorePalette(CorePalette.of(r0));
    }

    public static Scheme lightContent(int r0) {
        return lightFromCorePalette(CorePalette.contentOf(r0));
    }

    private static Scheme lightFromCorePalette(CorePalette r6) {
        return new Scheme().withPrimary(r6.a1.tone(40)).withOnPrimary(r6.a1.tone(100)).withPrimaryContainer(r6.a1.tone(90)).withOnPrimaryContainer(r6.a1.tone(10)).withSecondary(r6.a2.tone(40)).withOnSecondary(r6.a2.tone(100)).withSecondaryContainer(r6.a2.tone(90)).withOnSecondaryContainer(r6.a2.tone(10)).withTertiary(r6.a3.tone(40)).withOnTertiary(r6.a3.tone(100)).withTertiaryContainer(r6.a3.tone(90)).withOnTertiaryContainer(r6.a3.tone(10)).withError(r6.error.tone(40)).withOnError(r6.error.tone(100)).withErrorContainer(r6.error.tone(90)).withOnErrorContainer(r6.error.tone(10)).withBackground(r6.n1.tone(99)).withOnBackground(r6.n1.tone(10)).withSurface(r6.n1.tone(99)).withOnSurface(r6.n1.tone(10)).withSurfaceVariant(r6.n2.tone(90)).withOnSurfaceVariant(r6.n2.tone(30)).withOutline(r6.n2.tone(50)).withOutlineVariant(r6.n2.tone(80)).withShadow(r6.n1.tone(0)).withScrim(r6.n1.tone(0)).withInverseSurface(r6.n1.tone(20)).withInverseOnSurface(r6.n1.tone(95)).withInversePrimary(r6.a1.tone(80));
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Scheme) == true) goto L8;
        return false;
    L8:
        Scheme r52 = (Scheme) r5;
        if (this.primary == r52.primary) goto L12;
        return false;
    L12:
        if (this.onPrimary == r52.onPrimary) goto L15;
        return false;
    L15:
        if (this.primaryContainer == r52.primaryContainer) goto L18;
        return false;
    L18:
        if (this.onPrimaryContainer == r52.onPrimaryContainer) goto L21;
        return false;
    L21:
        if (this.secondary == r52.secondary) goto L24;
        return false;
    L24:
        if (this.onSecondary == r52.onSecondary) goto L27;
        return false;
    L27:
        if (this.secondaryContainer == r52.secondaryContainer) goto L30;
        return false;
    L30:
        if (this.onSecondaryContainer == r52.onSecondaryContainer) goto L33;
        return false;
    L33:
        if (this.tertiary == r52.tertiary) goto L36;
        return false;
    L36:
        if (this.onTertiary == r52.onTertiary) goto L39;
        return false;
    L39:
        if (this.tertiaryContainer == r52.tertiaryContainer) goto L42;
        return false;
    L42:
        if (this.onTertiaryContainer == r52.onTertiaryContainer) goto L45;
        return false;
    L45:
        if (this.error == r52.error) goto L48;
        return false;
    L48:
        if (this.onError == r52.onError) goto L51;
        return false;
    L51:
        if (this.errorContainer == r52.errorContainer) goto L54;
        return false;
    L54:
        if (this.onErrorContainer == r52.onErrorContainer) goto L57;
        return false;
    L57:
        if (this.background == r52.background) goto L60;
        return false;
    L60:
        if (this.onBackground == r52.onBackground) goto L63;
        return false;
    L63:
        if (this.surface == r52.surface) goto L66;
        return false;
    L66:
        if (this.onSurface == r52.onSurface) goto L69;
        return false;
    L69:
        if (this.surfaceVariant == r52.surfaceVariant) goto L72;
        return false;
    L72:
        if (this.onSurfaceVariant == r52.onSurfaceVariant) goto L75;
        return false;
    L75:
        if (this.outline == r52.outline) goto L78;
        return false;
    L78:
        if (this.outlineVariant == r52.outlineVariant) goto L81;
        return false;
    L81:
        if (this.shadow == r52.shadow) goto L84;
        return false;
    L84:
        if (this.scrim == r52.scrim) goto L87;
        return false;
    L87:
        if (this.inverseSurface == r52.inverseSurface) goto L90;
        return false;
    L90:
        if (this.inverseOnSurface == r52.inverseOnSurface) goto L93;
        return false;
    L93:
        if (this.inversePrimary == r52.inversePrimary) goto L95;
        return false;
    L95:
        return true;
    }

    public int getBackground() {
        return this.background;
    }

    public int getError() {
        return this.error;
    }

    public int getErrorContainer() {
        return this.errorContainer;
    }

    public int getInverseOnSurface() {
        return this.inverseOnSurface;
    }

    public int getInversePrimary() {
        return this.inversePrimary;
    }

    public int getInverseSurface() {
        return this.inverseSurface;
    }

    public int getOnBackground() {
        return this.onBackground;
    }

    public int getOnError() {
        return this.onError;
    }

    public int getOnErrorContainer() {
        return this.onErrorContainer;
    }

    public int getOnPrimary() {
        return this.onPrimary;
    }

    public int getOnPrimaryContainer() {
        return this.onPrimaryContainer;
    }

    public int getOnSecondary() {
        return this.onSecondary;
    }

    public int getOnSecondaryContainer() {
        return this.onSecondaryContainer;
    }

    public int getOnSurface() {
        return this.onSurface;
    }

    public int getOnSurfaceVariant() {
        return this.onSurfaceVariant;
    }

    public int getOnTertiary() {
        return this.onTertiary;
    }

    public int getOnTertiaryContainer() {
        return this.onTertiaryContainer;
    }

    public int getOutline() {
        return this.outline;
    }

    public int getOutlineVariant() {
        return this.outlineVariant;
    }

    public int getPrimary() {
        return this.primary;
    }

    public int getPrimaryContainer() {
        return this.primaryContainer;
    }

    public int getScrim() {
        return this.scrim;
    }

    public int getSecondary() {
        return this.secondary;
    }

    public int getSecondaryContainer() {
        return this.secondaryContainer;
    }

    public int getShadow() {
        return this.shadow;
    }

    public int getSurface() {
        return this.surface;
    }

    public int getSurfaceVariant() {
        return this.surfaceVariant;
    }

    public int getTertiary() {
        return this.tertiary;
    }

    public int getTertiaryContainer() {
        return this.tertiaryContainer;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((System.identityHashCode(this) * 31) + this.primary) * 31) + this.onPrimary) * 31) + this.primaryContainer) * 31) + this.onPrimaryContainer) * 31) + this.secondary) * 31) + this.onSecondary) * 31) + this.secondaryContainer) * 31) + this.onSecondaryContainer) * 31) + this.tertiary) * 31) + this.onTertiary) * 31) + this.tertiaryContainer) * 31) + this.onTertiaryContainer) * 31) + this.error) * 31) + this.onError) * 31) + this.errorContainer) * 31) + this.onErrorContainer) * 31) + this.background) * 31) + this.onBackground) * 31) + this.surface) * 31) + this.onSurface) * 31) + this.surfaceVariant) * 31) + this.onSurfaceVariant) * 31) + this.outline) * 31) + this.outlineVariant) * 31) + this.shadow) * 31) + this.scrim) * 31) + this.inverseSurface) * 31) + this.inverseOnSurface) * 31) + this.inversePrimary;
    }

    public void setBackground(int r1) {
        this.background = r1;
    }

    public void setError(int r1) {
        this.error = r1;
    }

    public void setErrorContainer(int r1) {
        this.errorContainer = r1;
    }

    public void setInverseOnSurface(int r1) {
        this.inverseOnSurface = r1;
    }

    public void setInversePrimary(int r1) {
        this.inversePrimary = r1;
    }

    public void setInverseSurface(int r1) {
        this.inverseSurface = r1;
    }

    public void setOnBackground(int r1) {
        this.onBackground = r1;
    }

    public void setOnError(int r1) {
        this.onError = r1;
    }

    public void setOnErrorContainer(int r1) {
        this.onErrorContainer = r1;
    }

    public void setOnPrimary(int r1) {
        this.onPrimary = r1;
    }

    public void setOnPrimaryContainer(int r1) {
        this.onPrimaryContainer = r1;
    }

    public void setOnSecondary(int r1) {
        this.onSecondary = r1;
    }

    public void setOnSecondaryContainer(int r1) {
        this.onSecondaryContainer = r1;
    }

    public void setOnSurface(int r1) {
        this.onSurface = r1;
    }

    public void setOnSurfaceVariant(int r1) {
        this.onSurfaceVariant = r1;
    }

    public void setOnTertiary(int r1) {
        this.onTertiary = r1;
    }

    public void setOnTertiaryContainer(int r1) {
        this.onTertiaryContainer = r1;
    }

    public void setOutline(int r1) {
        this.outline = r1;
    }

    public void setOutlineVariant(int r1) {
        this.outlineVariant = r1;
    }

    public void setPrimary(int r1) {
        this.primary = r1;
    }

    public void setPrimaryContainer(int r1) {
        this.primaryContainer = r1;
    }

    public void setScrim(int r1) {
        this.scrim = r1;
    }

    public void setSecondary(int r1) {
        this.secondary = r1;
    }

    public void setSecondaryContainer(int r1) {
        this.secondaryContainer = r1;
    }

    public void setShadow(int r1) {
        this.shadow = r1;
    }

    public void setSurface(int r1) {
        this.surface = r1;
    }

    public void setSurfaceVariant(int r1) {
        this.surfaceVariant = r1;
    }

    public void setTertiary(int r1) {
        this.tertiary = r1;
    }

    public void setTertiaryContainer(int r1) {
        this.tertiaryContainer = r1;
    }

    public String toString() {
        return "Scheme{primary=" + this.primary + ", onPrimary=" + this.onPrimary + ", primaryContainer=" + this.primaryContainer + ", onPrimaryContainer=" + this.onPrimaryContainer + ", secondary=" + this.secondary + ", onSecondary=" + this.onSecondary + ", secondaryContainer=" + this.secondaryContainer + ", onSecondaryContainer=" + this.onSecondaryContainer + ", tertiary=" + this.tertiary + ", onTertiary=" + this.onTertiary + ", tertiaryContainer=" + this.tertiaryContainer + ", onTertiaryContainer=" + this.onTertiaryContainer + ", error=" + this.error + ", onError=" + this.onError + ", errorContainer=" + this.errorContainer + ", onErrorContainer=" + this.onErrorContainer + ", background=" + this.background + ", onBackground=" + this.onBackground + ", surface=" + this.surface + ", onSurface=" + this.onSurface + ", surfaceVariant=" + this.surfaceVariant + ", onSurfaceVariant=" + this.onSurfaceVariant + ", outline=" + this.outline + ", outlineVariant=" + this.outlineVariant + ", shadow=" + this.shadow + ", scrim=" + this.scrim + ", inverseSurface=" + this.inverseSurface + ", inverseOnSurface=" + this.inverseOnSurface + ", inversePrimary=" + this.inversePrimary + '}';
    }

    public Scheme withBackground(int r1) {
        this.background = r1;
        return this;
    }

    public Scheme withError(int r1) {
        this.error = r1;
        return this;
    }

    public Scheme withErrorContainer(int r1) {
        this.errorContainer = r1;
        return this;
    }

    public Scheme withInverseOnSurface(int r1) {
        this.inverseOnSurface = r1;
        return this;
    }

    public Scheme withInversePrimary(int r1) {
        this.inversePrimary = r1;
        return this;
    }

    public Scheme withInverseSurface(int r1) {
        this.inverseSurface = r1;
        return this;
    }

    public Scheme withOnBackground(int r1) {
        this.onBackground = r1;
        return this;
    }

    public Scheme withOnError(int r1) {
        this.onError = r1;
        return this;
    }

    public Scheme withOnErrorContainer(int r1) {
        this.onErrorContainer = r1;
        return this;
    }

    public Scheme withOnPrimary(int r1) {
        this.onPrimary = r1;
        return this;
    }

    public Scheme withOnPrimaryContainer(int r1) {
        this.onPrimaryContainer = r1;
        return this;
    }

    public Scheme withOnSecondary(int r1) {
        this.onSecondary = r1;
        return this;
    }

    public Scheme withOnSecondaryContainer(int r1) {
        this.onSecondaryContainer = r1;
        return this;
    }

    public Scheme withOnSurface(int r1) {
        this.onSurface = r1;
        return this;
    }

    public Scheme withOnSurfaceVariant(int r1) {
        this.onSurfaceVariant = r1;
        return this;
    }

    public Scheme withOnTertiary(int r1) {
        this.onTertiary = r1;
        return this;
    }

    public Scheme withOnTertiaryContainer(int r1) {
        this.onTertiaryContainer = r1;
        return this;
    }

    public Scheme withOutline(int r1) {
        this.outline = r1;
        return this;
    }

    public Scheme withOutlineVariant(int r1) {
        this.outlineVariant = r1;
        return this;
    }

    public Scheme withPrimary(int r1) {
        this.primary = r1;
        return this;
    }

    public Scheme withPrimaryContainer(int r1) {
        this.primaryContainer = r1;
        return this;
    }

    public Scheme withScrim(int r1) {
        this.scrim = r1;
        return this;
    }

    public Scheme withSecondary(int r1) {
        this.secondary = r1;
        return this;
    }

    public Scheme withSecondaryContainer(int r1) {
        this.secondaryContainer = r1;
        return this;
    }

    public Scheme withShadow(int r1) {
        this.shadow = r1;
        return this;
    }

    public Scheme withSurface(int r1) {
        this.surface = r1;
        return this;
    }

    public Scheme withSurfaceVariant(int r1) {
        this.surfaceVariant = r1;
        return this;
    }

    public Scheme withTertiary(int r1) {
        this.tertiary = r1;
        return this;
    }

    public Scheme withTertiaryContainer(int r1) {
        this.tertiaryContainer = r1;
        return this;
    }
}
