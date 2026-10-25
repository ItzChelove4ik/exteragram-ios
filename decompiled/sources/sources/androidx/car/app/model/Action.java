package androidx.car.app.model;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class Action {
    public static final Action APP_ICON = null;
    public static final Action BACK = null;
    public static final Action COMPOSE_MESSAGE = null;
    public static final int FLAG_DEFAULT = 4;
    public static final int FLAG_IS_PERSISTENT = 2;
    public static final int FLAG_PRIMARY = 1;
    public static final Action PAN = null;
    public static final int TYPE_APP_ICON = 65538;
    public static final int TYPE_BACK = 65539;
    public static final int TYPE_COMPOSE_MESSAGE = 65541;
    public static final int TYPE_CUSTOM = 1;
    public static final int TYPE_PAN = 65540;
    static final int TYPE_STANDARD = 65536;
    private final CarColor mBackgroundColor;
    private final int mFlags;
    private final CarIcon mIcon;
    private final boolean mIsEnabled;
    private final OnClickDelegate mOnClickDelegate;
    private final CarText mTitle;
    private final int mType;

    public static final class Builder {
    }

    static {
        APP_ICON = new Action(65538);
        COMPOSE_MESSAGE = new Action(TYPE_COMPOSE_MESSAGE);
        BACK = new Action(TYPE_BACK);
        PAN = new Action(TYPE_PAN);
    }

    private Action() {
        this.mTitle = null;
        this.mIcon = null;
        this.mBackgroundColor = CarColor.DEFAULT;
        this.mOnClickDelegate = null;
        this.mType = 1;
        this.mFlags = 0;
        this.mIsEnabled = true;
    }

    private Action(int r4) {
        if (r4 == 1) goto L6;
        this.mTitle = null;
        this.mIcon = null;
        this.mBackgroundColor = CarColor.DEFAULT;
        this.mOnClickDelegate = null;
        this.mType = r4;
        this.mFlags = 0;
        this.mIsEnabled = true;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Standard action constructor used with non standard type");
        throw null;
    }

    public Action(Builder r1) {
        throw null;
    }

    public static boolean isStandardActionType(int r1) {
        if ((r1 & 65536) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static String typeToString(int r1) {
        if (r1 == 1) goto L15;
        switch(r1) {
            case 65538: goto L13;
            case 65539: goto L11;
            case 65540: goto L9;
            case 65541: goto L7;
            default: goto L5;
        };
    L5:
        return "<unknown>";
    L7:
        return "COMPOSE_MESSAGE";
    L9:
        return "PAN";
    L11:
        return "BACK";
    L13:
        return "APP_ICON";
    L15:
        return "CUSTOM";
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Action) == true) goto L8;
        return false;
    L8:
        Action r52 = (Action) r5;
        if (Objects.equals(this.mTitle, r52.mTitle) == true) goto L11;
    L29:
        return false;
    L11:
        if (this.mType != r52.mType) goto L29;
        if (Objects.equals(this.mIcon, r52.mIcon) == false) goto L29;
        if (this.mOnClickDelegate != null) goto L17;
        boolean r1 = true;
    L18:
        Boolean r12 = Boolean.valueOf(r1);
        if (r52.mOnClickDelegate != null) goto L21;
        boolean r3 = true;
    L23:
        if (r12.equals(Boolean.valueOf(r3)) == false) goto L29;
        if (Integer.valueOf(this.mFlags).equals(Integer.valueOf(r52.mFlags)) == false) goto L29;
        if (this.mIsEnabled != r52.mIsEnabled) goto L29;
        return true;
    L21:
        r3 = false;
        goto L23
    L17:
        r1 = false;
        goto L18
    }

    public CarColor getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public CarIcon getIcon() {
        return this.mIcon;
    }

    public OnClickDelegate getOnClickDelegate() {
        return this.mOnClickDelegate;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public int getType() {
        return this.mType;
    }

    public int hashCode() {
        CarText r0 = this.mTitle;
        Integer r1 = Integer.valueOf(this.mType);
        boolean r3 = false;
        if (this.mOnClickDelegate != null) goto L5;
        boolean r2 = true;
    L6:
        Boolean r22 = Boolean.valueOf(r2);
        if (this.mIcon != null) goto L10;
        r3 = true;
    L10:
        return Objects.hash(new Object[]{r0, r1, r22, Boolean.valueOf(r3), Boolean.valueOf(this.mIsEnabled)});
    L5:
        r2 = false;
        goto L6
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public boolean isStandard() {
        return isStandardActionType(this.mType);
    }

    public String toString() {
        return "[type: " + typeToString(this.mType) + ", icon: " + this.mIcon + ", bkg: " + this.mBackgroundColor + ", isEnabled: " + this.mIsEnabled + "]";
    }
}
