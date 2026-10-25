package androidx.car.app.model;

import androidx.car.app.model.constraints.CarIconConstraints;
import androidx.core.graphics.drawable.IconCompat;
import com.google.android.gms.cast.MediaError;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class CarIcon {
    public static final CarIcon ALERT = null;
    public static final CarIcon APP_ICON = null;
    public static final CarIcon BACK = null;
    public static final CarIcon COMPOSE_MESSAGE = null;
    public static final CarIcon ERROR = null;
    public static final CarIcon PAN = null;
    public static final int TYPE_ALERT = 4;
    public static final int TYPE_APP_ICON = 5;
    public static final int TYPE_BACK = 3;
    public static final int TYPE_COMPOSE_MESSAGE = 8;
    public static final int TYPE_CUSTOM = 1;
    public static final int TYPE_ERROR = 6;
    public static final int TYPE_PAN = 7;
    private static final int TYPE_RESOURCE = 2;
    private static final int TYPE_URI = 4;
    private final IconCompat mIcon;
    private final CarColor mTint;
    private final int mType;

    public static final class Builder {
        private final IconCompat mIcon;
        private CarColor mTint;
        private final int mType;

        public Builder(IconCompat r2) {
            CarIconConstraints r0 = CarIconConstraints.UNCONSTRAINED;
            Objects.requireNonNull(r2);
            r0.checkSupportedIcon(r2);
            this.mType = 1;
            this.mIcon = r2;
            this.mTint = null;
        }

        public CarIcon build() {
            return new CarIcon(this.mIcon, this.mTint, this.mType);
        }
    }

    static {
        APP_ICON = forStandardType(5);
        BACK = forStandardType(3);
        ALERT = forStandardType(4);
        ERROR = forStandardType(6);
        PAN = forStandardType(7);
        COMPOSE_MESSAGE = forStandardType(8);
    }

    private CarIcon() {
        this.mType = 1;
        this.mIcon = null;
        this.mTint = null;
    }

    public CarIcon(IconCompat r1, CarColor r2, int r3) {
        this.mType = r3;
        this.mIcon = r1;
        this.mTint = r2;
    }

    private static CarIcon forStandardType(int r1) {
        return forStandardType(r1, CarColor.DEFAULT);
    }

    private static CarIcon forStandardType(int r2, CarColor r3) {
        return new CarIcon(null, r3, r2);
    }

    private boolean iconCompatEquals(IconCompat r5) {
        IconCompat r0 = this.mIcon;
        if (r0 != null) goto L7;
        if (r5 != null) goto L6;
        return true;
    L6:
        return false;
    L7:
        if (r5 != null) goto L9;
        return false;
    L9:
        int r02 = r0.getType();
        if (r02 == r5.getType()) goto L13;
        return false;
    L13:
        if (r02 != 2) goto L21;
        if (Objects.equals(this.mIcon.getResPackage(), r5.getResPackage()) == true) goto L17;
    L19:
        return false;
    L17:
        if (this.mIcon.getResId() != r5.getResId()) goto L19;
        return true;
    L21:
        if (r02 == 4) goto L23;
        return true;
    L23:
        return Objects.equals(this.mIcon.getUri(), r5.getUri());
    }

    private Object iconCompatHash() {
        IconCompat r0 = this.mIcon;
        if (r0 != null) goto L6;
        return null;
    L6:
        int r02 = r0.getType();
        if (r02 != 2) goto L11;
        return this.mIcon.getResPackage() + this.mIcon.getResId();
    L11:
        if (r02 != 4) goto L15;
        return this.mIcon.getUri();
    L15:
        return Integer.valueOf(r02);
    }

    private static String typeToString(int r0) {
        switch(r0) {
            case 1: goto L17;
            case 2: goto L3;
            case 3: goto L15;
            case 4: goto L13;
            case 5: goto L11;
            case 6: goto L9;
            case 7: goto L7;
            case 8: goto L5;
            default: goto L3;
        };
    L3:
        return "<unknown>";
    L5:
        return "COMPOSE_MESSAGE";
    L7:
        return "PAN";
    L9:
        return MediaError.ERROR_TYPE_ERROR;
    L11:
        return "APP";
    L13:
        return "ALERT";
    L15:
        return "BACK";
    L17:
        return "CUSTOM";
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof CarIcon) == true) goto L8;
        return false;
    L8:
        CarIcon r52 = (CarIcon) r5;
        if (this.mType == r52.mType) goto L11;
    L15:
        return false;
    L11:
        if (Objects.equals(this.mTint, r52.mTint) == false) goto L15;
        if (iconCompatEquals(r52.mIcon) == false) goto L15;
        return true;
    }

    public IconCompat getIcon() {
        return this.mIcon;
    }

    public CarColor getTint() {
        return this.mTint;
    }

    public int getType() {
        return this.mType;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.mType), this.mTint, iconCompatHash()});
    }

    public String toString() {
        return "[type: " + typeToString(this.mType) + ", tint: " + this.mTint + "]";
    }
}
