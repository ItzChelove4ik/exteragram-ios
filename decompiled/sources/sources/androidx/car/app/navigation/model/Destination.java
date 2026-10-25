package androidx.car.app.navigation.model;

import androidx.car.app.model.CarIcon;
import androidx.car.app.model.CarText;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class Destination {
    private final CarText mAddress;
    private final CarIcon mImage;
    private final CarText mName;

    public static final class Builder {
    }

    private Destination() {
        this.mName = null;
        this.mAddress = null;
        this.mImage = null;
    }

    public Destination(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Destination) == true) goto L8;
        return false;
    L8:
        Destination r52 = (Destination) r5;
        if (Objects.equals(this.mName, r52.mName) == true) goto L11;
    L15:
        return false;
    L11:
        if (Objects.equals(this.mAddress, r52.mAddress) == false) goto L15;
        if (Objects.equals(this.mImage, r52.mImage) == false) goto L15;
        return true;
    }

    public CarText getAddress() {
        return this.mAddress;
    }

    public CarIcon getImage() {
        return this.mImage;
    }

    public CarText getName() {
        return this.mName;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mName, this.mAddress, this.mImage});
    }

    public String toString() {
        return "[name: " + CarText.toShortString(this.mName) + ", address: " + CarText.toShortString(this.mAddress) + ", image: " + this.mImage + "]";
    }
}
