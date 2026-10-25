package androidx.car.app.model;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class CarColor {
    public static final CarColor BLUE = null;
    public static final CarColor DEFAULT = null;
    public static final CarColor GREEN = null;
    public static final CarColor PRIMARY = null;
    public static final CarColor RED = null;
    public static final CarColor SECONDARY = null;
    public static final int TYPE_BLUE = 6;
    public static final int TYPE_CUSTOM = 0;
    public static final int TYPE_DEFAULT = 1;
    public static final int TYPE_GREEN = 5;
    public static final int TYPE_PRIMARY = 2;
    public static final int TYPE_RED = 4;
    public static final int TYPE_SECONDARY = 3;
    public static final int TYPE_YELLOW = 7;
    public static final CarColor YELLOW = null;
    private final int mColor;
    private final int mColorDark;
    private final int mType;

    static {
        DEFAULT = create(1);
        PRIMARY = create(2);
        SECONDARY = create(3);
        RED = create(4);
        GREEN = create(5);
        BLUE = create(6);
        YELLOW = create(7);
    }

    private CarColor() {
        this.mType = 1;
        this.mColor = 0;
        this.mColorDark = 0;
    }

    private CarColor(int r1, int r2, int r3) {
        this.mType = r1;
        this.mColor = r2;
        this.mColorDark = r3;
    }

    private static CarColor create(int r2) {
        return new CarColor(r2, 0, 0);
    }

    public static CarColor createCustom(int r2, int r3) {
        return new CarColor(0, r2, r3);
    }

    private static String typeToString(int r0) {
        switch(r0) {
            case 0: goto L19;
            case 1: goto L17;
            case 2: goto L15;
            case 3: goto L13;
            case 4: goto L11;
            case 5: goto L9;
            case 6: goto L7;
            case 7: goto L5;
            default: goto L3;
        };
    L3:
        return "<unknown>";
    L5:
        return "YELLOW";
    L7:
        return "BLUE";
    L9:
        return "GREEN";
    L11:
        return "RED";
    L13:
        return "SECONDARY";
    L15:
        return "PRIMARY";
    L17:
        return PluginsConstants.Strategy.DEFAULT;
    L19:
        return "CUSTOM";
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof CarColor) == true) goto L8;
        return false;
    L8:
        CarColor r52 = (CarColor) r5;
        if (this.mColor == r52.mColor) goto L11;
    L15:
        return false;
    L11:
        if (this.mColorDark != r52.mColorDark) goto L15;
        if (this.mType != r52.mType) goto L15;
        return true;
    }

    public int getColor() {
        return this.mColor;
    }

    public int getColorDark() {
        return this.mColorDark;
    }

    public int getType() {
        return this.mType;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.mType), Integer.valueOf(this.mColor), Integer.valueOf(this.mColorDark)});
    }

    public String toString() {
        return "[type: " + typeToString(this.mType) + ", color: " + this.mColor + ", dark: " + this.mColorDark + "]";
    }
}
