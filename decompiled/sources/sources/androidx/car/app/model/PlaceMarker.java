package androidx.car.app.model;

import java.util.Objects;

/* loaded from: classes3.dex */
public final class PlaceMarker {
    private static final int MAX_LABEL_LENGTH = 3;
    public static final int TYPE_ICON = 0;
    public static final int TYPE_IMAGE = 1;
    private final CarColor mColor;
    private final CarIcon mIcon;
    private final int mIconType;
    private final CarText mLabel;

    public static final class Builder {
    }

    private PlaceMarker() {
        this.mIcon = null;
        this.mIconType = 0;
        this.mLabel = null;
        this.mColor = null;
    }

    public PlaceMarker(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof PlaceMarker) == true) goto L8;
        return false;
    L8:
        PlaceMarker r52 = (PlaceMarker) r5;
        if (Objects.equals(this.mIcon, r52.mIcon) == true) goto L11;
    L17:
        return false;
    L11:
        if (Objects.equals(this.mLabel, r52.mLabel) == false) goto L17;
        if (Objects.equals(this.mColor, r52.mColor) == false) goto L17;
        if (this.mIconType != r52.mIconType) goto L17;
        return true;
    }

    public CarColor getColor() {
        return this.mColor;
    }

    public CarIcon getIcon() {
        return this.mIcon;
    }

    public int getIconType() {
        return this.mIconType;
    }

    public CarText getLabel() {
        return this.mLabel;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mIcon, this.mLabel, this.mColor, Integer.valueOf(this.mIconType)});
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("[");
        CarIcon r1 = this.mIcon;
        if (r1 == null) goto L5;
        String r2 = r1.toString();
    L9:
        r0.append(r2);
        r0.append("]");
        return r0.toString();
    L5:
        CarText r12 = this.mLabel;
        if (r12 == null) goto L8;
        r2 = CarText.toShortString(r12);
        goto L9
    L8:
        r2 = super.toString();
        goto L9
    }
}
