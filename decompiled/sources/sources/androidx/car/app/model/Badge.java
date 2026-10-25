package androidx.car.app.model;

import java.util.Objects;

/* loaded from: classes3.dex */
public class Badge {
    private final CarColor mBackgroundColor;
    private final boolean mHasDot;
    private final CarIcon mIcon;

    public static final class Builder {
    }

    private Badge() {
        this.mHasDot = false;
        this.mBackgroundColor = null;
        this.mIcon = null;
    }

    public Badge(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Badge) == true) goto L8;
        return false;
    L8:
        Badge r52 = (Badge) r5;
        if (this.mHasDot == r52.mHasDot) goto L11;
    L15:
        return false;
    L11:
        if (Objects.equals(this.mBackgroundColor, r52.mBackgroundColor) == false) goto L15;
        if (Objects.equals(this.mIcon, r52.mIcon) == false) goto L15;
        return true;
    }

    public CarColor getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public CarIcon getIcon() {
        return this.mIcon;
    }

    public boolean hasDot() {
        return this.mHasDot;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Boolean.valueOf(this.mHasDot), this.mBackgroundColor, this.mIcon});
    }

    public String toString() {
        return "[hasDot: " + this.mHasDot + ", backgroundColor: " + this.mBackgroundColor + ", icon: " + this.mIcon + "]";
    }
}
