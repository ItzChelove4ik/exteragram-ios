package com.google.android.material.badge;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.material.R;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import java.util.Locale;

/* loaded from: classes5.dex */
public final class BadgeState {
    private static final String BADGE_RESOURCE_TAG = "badge";
    int badgeFixedEdge;
    final float badgeHeight;
    final float badgeRadius;
    final float badgeWidth;
    final float badgeWithTextHeight;
    final float badgeWithTextRadius;
    final float badgeWithTextWidth;
    private final State currentState;
    final int horizontalInset;
    final int horizontalInsetWithText;
    int offsetAlignmentMode;
    private final State overridingState;

    public static final class State implements Parcelable {
        private static final int BADGE_NUMBER_NONE = -1;
        public static final Parcelable.Creator<State> CREATOR = null;
        private static final int NOT_SET = -2;
        private Integer additionalHorizontalOffset;
        private Integer additionalVerticalOffset;
        private int alpha;
        private Boolean autoAdjustToWithinGrandparentBounds;
        private Integer backgroundColor;
        private Integer badgeFixedEdge;
        private Integer badgeGravity;
        private Integer badgeHorizontalPadding;
        private int badgeResId;
        private Integer badgeShapeAppearanceOverlayResId;
        private Integer badgeShapeAppearanceResId;
        private Integer badgeTextAppearanceResId;
        private Integer badgeTextColor;
        private Integer badgeVerticalPadding;
        private Integer badgeWithTextShapeAppearanceOverlayResId;
        private Integer badgeWithTextShapeAppearanceResId;
        private int contentDescriptionExceedsMaxBadgeNumberRes;
        private CharSequence contentDescriptionForText;
        private CharSequence contentDescriptionNumberless;
        private int contentDescriptionQuantityStrings;
        private Integer horizontalOffsetWithText;
        private Integer horizontalOffsetWithoutText;
        private Boolean isVisible;
        private Integer largeFontVerticalOffsetAdjustment;
        private int maxCharacterCount;
        private int maxNumber;
        private int number;
        private Locale numberLocale;
        private String text;
        private Integer verticalOffsetWithText;
        private Integer verticalOffsetWithoutText;

        /* renamed from: com.google.android.material.badge.BadgeState$State$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.Creator<State> {
            public AnonymousClass1() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public State createFromParcel(Parcel r1) {
                return new State(r1);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ State createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public State[] newArray(int r1) {
                return new State[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ State[] newArray(int r1) {
                return newArray(r1);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        public State() {
            this.alpha = 255;
            this.number = -2;
            this.maxCharacterCount = -2;
            this.maxNumber = -2;
            this.isVisible = Boolean.TRUE;
        }

        public State(Parcel r2) {
            this.alpha = 255;
            this.number = -2;
            this.maxCharacterCount = -2;
            this.maxNumber = -2;
            this.isVisible = Boolean.TRUE;
            this.badgeResId = r2.readInt();
            this.backgroundColor = (Integer) r2.readSerializable();
            this.badgeTextColor = (Integer) r2.readSerializable();
            this.badgeTextAppearanceResId = (Integer) r2.readSerializable();
            this.badgeShapeAppearanceResId = (Integer) r2.readSerializable();
            this.badgeShapeAppearanceOverlayResId = (Integer) r2.readSerializable();
            this.badgeWithTextShapeAppearanceResId = (Integer) r2.readSerializable();
            this.badgeWithTextShapeAppearanceOverlayResId = (Integer) r2.readSerializable();
            this.alpha = r2.readInt();
            this.text = r2.readString();
            this.number = r2.readInt();
            this.maxCharacterCount = r2.readInt();
            this.maxNumber = r2.readInt();
            this.contentDescriptionForText = r2.readString();
            this.contentDescriptionNumberless = r2.readString();
            this.contentDescriptionQuantityStrings = r2.readInt();
            this.badgeGravity = (Integer) r2.readSerializable();
            this.badgeHorizontalPadding = (Integer) r2.readSerializable();
            this.badgeVerticalPadding = (Integer) r2.readSerializable();
            this.horizontalOffsetWithoutText = (Integer) r2.readSerializable();
            this.verticalOffsetWithoutText = (Integer) r2.readSerializable();
            this.horizontalOffsetWithText = (Integer) r2.readSerializable();
            this.verticalOffsetWithText = (Integer) r2.readSerializable();
            this.largeFontVerticalOffsetAdjustment = (Integer) r2.readSerializable();
            this.additionalHorizontalOffset = (Integer) r2.readSerializable();
            this.additionalVerticalOffset = (Integer) r2.readSerializable();
            this.isVisible = (Boolean) r2.readSerializable();
            this.numberLocale = (Locale) r2.readSerializable();
            this.autoAdjustToWithinGrandparentBounds = (Boolean) r2.readSerializable();
            this.badgeFixedEdge = (Integer) r2.readSerializable();
        }

        public static /* synthetic */ int access$000(State r0) {
            return r0.badgeResId;
        }

        public static /* synthetic */ int access$002(State r0, int r1) {
            r0.badgeResId = r1;
            return r1;
        }

        public static /* synthetic */ int access$100(State r0) {
            return r0.alpha;
        }

        public static /* synthetic */ int access$1000(State r0) {
            return r0.maxNumber;
        }

        public static /* synthetic */ int access$1002(State r0, int r1) {
            r0.maxNumber = r1;
            return r1;
        }

        public static /* synthetic */ int access$102(State r0, int r1) {
            r0.alpha = r1;
            return r1;
        }

        public static /* synthetic */ Integer access$1100(State r0) {
            return r0.badgeShapeAppearanceResId;
        }

        public static /* synthetic */ Integer access$1102(State r0, Integer r1) {
            r0.badgeShapeAppearanceResId = r1;
            return r1;
        }

        public static /* synthetic */ Integer access$1200(State r0) {
            return r0.badgeShapeAppearanceOverlayResId;
        }

        public static /* synthetic */ Integer access$1202(State r0, Integer r1) {
            r0.badgeShapeAppearanceOverlayResId = r1;
            return r1;
        }

        public static /* synthetic */ Integer access$1300(State r0) {
            return r0.badgeWithTextShapeAppearanceResId;
        }

        public static /* synthetic */ Integer access$1302(State r0, Integer r1) {
            r0.badgeWithTextShapeAppearanceResId = r1;
            return r1;
        }

        public static /* synthetic */ Integer access$1400(State r0) {
            return r0.badgeWithTextShapeAppearanceOverlayResId;
        }

        public static /* synthetic */ Integer access$1402(State r0, Integer r1) {
            r0.badgeWithTextShapeAppearanceOverlayResId = r1;
            return r1;
        }

        public static /* synthetic */ Integer access$1500(State r0) {
            return r0.backgroundColor;
        }

        public static /* synthetic */ Integer access$1502(State r0, Integer r1) {
            r0.backgroundColor = r1;
            return r1;
        }

        public static /* synthetic */ Integer access$1600(State r0) {
            return r0.badgeTextAppearanceResId;
        }

        public static /* synthetic */ Integer access$1602(State r0, Integer r1) {
            r0.badgeTextAppearanceResId = r1;
            return r1;
        }

        public static /* synthetic */ Integer access$1700(State r0) {
            return r0.badgeTextColor;
        }

        public static /* synthetic */ Integer access$1702(State r0, Integer r1) {
            r0.badgeTextColor = r1;
            return r1;
        }

        public static /* synthetic */ Integer access$1800(State r0) {
            return r0.badgeGravity;
        }

        public static /* synthetic */ Integer access$1802(State r0, Integer r1) {
            r0.badgeGravity = r1;
            return r1;
        }

        public static /* synthetic */ Integer access$1900(State r0) {
            return r0.badgeHorizontalPadding;
        }

        public static /* synthetic */ Integer access$1902(State r0, Integer r1) {
            r0.badgeHorizontalPadding = r1;
            return r1;
        }

        public static /* synthetic */ int access$200(State r0) {
            return r0.number;
        }

        public static /* synthetic */ Integer access$2000(State r0) {
            return r0.badgeVerticalPadding;
        }

        public static /* synthetic */ Integer access$2002(State r0, Integer r1) {
            r0.badgeVerticalPadding = r1;
            return r1;
        }

        public static /* synthetic */ int access$202(State r0, int r1) {
            r0.number = r1;
            return r1;
        }

        public static /* synthetic */ Integer access$2100(State r0) {
            return r0.horizontalOffsetWithoutText;
        }

        public static /* synthetic */ Integer access$2102(State r0, Integer r1) {
            r0.horizontalOffsetWithoutText = r1;
            return r1;
        }

        public static /* synthetic */ Integer access$2200(State r0) {
            return r0.verticalOffsetWithoutText;
        }

        public static /* synthetic */ Integer access$2202(State r0, Integer r1) {
            r0.verticalOffsetWithoutText = r1;
            return r1;
        }

        public static /* synthetic */ Integer access$2300(State r0) {
            return r0.horizontalOffsetWithText;
        }

        public static /* synthetic */ Integer access$2302(State r0, Integer r1) {
            r0.horizontalOffsetWithText = r1;
            return r1;
        }

        public static /* synthetic */ Integer access$2400(State r0) {
            return r0.verticalOffsetWithText;
        }

        public static /* synthetic */ Integer access$2402(State r0, Integer r1) {
            r0.verticalOffsetWithText = r1;
            return r1;
        }

        public static /* synthetic */ Integer access$2500(State r0) {
            return r0.largeFontVerticalOffsetAdjustment;
        }

        public static /* synthetic */ Integer access$2502(State r0, Integer r1) {
            r0.largeFontVerticalOffsetAdjustment = r1;
            return r1;
        }

        public static /* synthetic */ Integer access$2600(State r0) {
            return r0.additionalHorizontalOffset;
        }

        public static /* synthetic */ Integer access$2602(State r0, Integer r1) {
            r0.additionalHorizontalOffset = r1;
            return r1;
        }

        public static /* synthetic */ Integer access$2700(State r0) {
            return r0.additionalVerticalOffset;
        }

        public static /* synthetic */ Integer access$2702(State r0, Integer r1) {
            r0.additionalVerticalOffset = r1;
            return r1;
        }

        public static /* synthetic */ Boolean access$2800(State r0) {
            return r0.autoAdjustToWithinGrandparentBounds;
        }

        public static /* synthetic */ Boolean access$2802(State r0, Boolean r1) {
            r0.autoAdjustToWithinGrandparentBounds = r1;
            return r1;
        }

        public static /* synthetic */ Locale access$2900(State r0) {
            return r0.numberLocale;
        }

        public static /* synthetic */ Locale access$2902(State r0, Locale r1) {
            r0.numberLocale = r1;
            return r1;
        }

        public static /* synthetic */ String access$300(State r0) {
            return r0.text;
        }

        public static /* synthetic */ String access$302(State r0, String r1) {
            r0.text = r1;
            return r1;
        }

        public static /* synthetic */ CharSequence access$400(State r0) {
            return r0.contentDescriptionForText;
        }

        public static /* synthetic */ CharSequence access$402(State r0, CharSequence r1) {
            r0.contentDescriptionForText = r1;
            return r1;
        }

        public static /* synthetic */ CharSequence access$500(State r0) {
            return r0.contentDescriptionNumberless;
        }

        public static /* synthetic */ CharSequence access$502(State r0, CharSequence r1) {
            r0.contentDescriptionNumberless = r1;
            return r1;
        }

        public static /* synthetic */ int access$600(State r0) {
            return r0.contentDescriptionQuantityStrings;
        }

        public static /* synthetic */ int access$602(State r0, int r1) {
            r0.contentDescriptionQuantityStrings = r1;
            return r1;
        }

        public static /* synthetic */ int access$700(State r0) {
            return r0.contentDescriptionExceedsMaxBadgeNumberRes;
        }

        public static /* synthetic */ int access$702(State r0, int r1) {
            r0.contentDescriptionExceedsMaxBadgeNumberRes = r1;
            return r1;
        }

        public static /* synthetic */ Boolean access$800(State r0) {
            return r0.isVisible;
        }

        public static /* synthetic */ Boolean access$802(State r0, Boolean r1) {
            r0.isVisible = r1;
            return r1;
        }

        public static /* synthetic */ int access$900(State r0) {
            return r0.maxCharacterCount;
        }

        public static /* synthetic */ int access$902(State r0, int r1) {
            r0.maxCharacterCount = r1;
            return r1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r2, int r3) {
            r2.writeInt(this.badgeResId);
            r2.writeSerializable(this.backgroundColor);
            r2.writeSerializable(this.badgeTextColor);
            r2.writeSerializable(this.badgeTextAppearanceResId);
            r2.writeSerializable(this.badgeShapeAppearanceResId);
            r2.writeSerializable(this.badgeShapeAppearanceOverlayResId);
            r2.writeSerializable(this.badgeWithTextShapeAppearanceResId);
            r2.writeSerializable(this.badgeWithTextShapeAppearanceOverlayResId);
            r2.writeInt(this.alpha);
            r2.writeString(this.text);
            r2.writeInt(this.number);
            r2.writeInt(this.maxCharacterCount);
            r2.writeInt(this.maxNumber);
            CharSequence r32 = this.contentDescriptionForText;
            String r0 = null;
            if (r32 == null) goto L5;
            String r33 = r32.toString();
        L6:
            r2.writeString(r33);
            CharSequence r34 = this.contentDescriptionNumberless;
            if (r34 == null) goto L9;
            r0 = r34.toString();
        L9:
            r2.writeString(r0);
            r2.writeInt(this.contentDescriptionQuantityStrings);
            r2.writeSerializable(this.badgeGravity);
            r2.writeSerializable(this.badgeHorizontalPadding);
            r2.writeSerializable(this.badgeVerticalPadding);
            r2.writeSerializable(this.horizontalOffsetWithoutText);
            r2.writeSerializable(this.verticalOffsetWithoutText);
            r2.writeSerializable(this.horizontalOffsetWithText);
            r2.writeSerializable(this.verticalOffsetWithText);
            r2.writeSerializable(this.largeFontVerticalOffsetAdjustment);
            r2.writeSerializable(this.additionalHorizontalOffset);
            r2.writeSerializable(this.additionalVerticalOffset);
            r2.writeSerializable(this.isVisible);
            r2.writeSerializable(this.numberLocale);
            r2.writeSerializable(this.autoAdjustToWithinGrandparentBounds);
            r2.writeSerializable(this.badgeFixedEdge);
            return;
        L5:
            r33 = null;
            goto L6
        }
    }

    public BadgeState(Context r6, int r7, int r8, int r9, State r10) {
        State r0 = new State();
        this.currentState = r0;
        if (r10 != null) goto L5;
        r10 = new State();
    L5:
        if (r7 == 0) goto L7;
        State.access$002(r10, r7);
    L7:
        TypedArray r72 = generateTypedArray(r6, State.access$000(r10), r8, r9);
        Resources r82 = r6.getResources();
        this.badgeRadius = r72.getDimensionPixelSize(R.styleable.Badge_badgeRadius, -1);
        this.horizontalInset = r6.getResources().getDimensionPixelSize(R.dimen.mtrl_badge_horizontal_edge_offset);
        this.horizontalInsetWithText = r6.getResources().getDimensionPixelSize(R.dimen.mtrl_badge_text_horizontal_edge_offset);
        this.badgeWithTextRadius = r72.getDimensionPixelSize(R.styleable.Badge_badgeWithTextRadius, -1);
        this.badgeWidth = r72.getDimension(R.styleable.Badge_badgeWidth, r82.getDimension(R.dimen.m3_badge_size));
        this.badgeWithTextWidth = r72.getDimension(R.styleable.Badge_badgeWithTextWidth, r82.getDimension(R.dimen.m3_badge_with_text_size));
        this.badgeHeight = r72.getDimension(R.styleable.Badge_badgeHeight, r82.getDimension(R.dimen.m3_badge_size));
        this.badgeWithTextHeight = r72.getDimension(R.styleable.Badge_badgeWithTextHeight, r82.getDimension(R.dimen.m3_badge_with_text_size));
        boolean r2 = true;
        this.offsetAlignmentMode = r72.getInt(R.styleable.Badge_offsetAlignmentMode, 1);
        this.badgeFixedEdge = r72.getInt(R.styleable.Badge_badgeFixedEdge, 0);
        if (State.access$100(r10) != (-2)) goto L10;
        int r92 = 255;
    L11:
        State.access$102(r0, r92);
        if (State.access$200(r10) == (-2)) goto L15;
        State.access$202(r0, State.access$200(r10));
    L19:
        if (State.access$300(r10) == null) goto L22;
        State.access$302(r0, State.access$300(r10));
    L24:
        State.access$402(r0, State.access$400(r10));
        if (State.access$500(r10) != null) goto L27;
        CharSequence r93 = r6.getString(R.string.mtrl_badge_numberless_content_description);
    L28:
        State.access$502(r0, r93);
        if (State.access$600(r10) != 0) goto L31;
        int r94 = R.plurals.mtrl_badge_content_description;
    L32:
        State.access$602(r0, r94);
        if (State.access$700(r10) != 0) goto L35;
        int r95 = R.string.mtrl_exceed_max_badge_number_content_description;
    L36:
        State.access$702(r0, r95);
        if (State.access$800(r10) != null) goto L39;
    L42:
        State.access$802(r0, Boolean.valueOf(r2));
        if (State.access$900(r10) != (-2)) goto L45;
        int r96 = r72.getInt(R.styleable.Badge_maxCharacterCount, -2);
    L46:
        State.access$902(r0, r96);
        if (State.access$1000(r10) != (-2)) goto L49;
        int r97 = r72.getInt(R.styleable.Badge_maxNumber, -2);
    L50:
        State.access$1002(r0, r97);
        if (State.access$1100(r10) != null) goto L53;
        int r98 = r72.getResourceId(R.styleable.Badge_badgeShapeAppearance, R.style.ShapeAppearance_M3_Sys_Shape_Corner_Full);
    L54:
        State.access$1102(r0, Integer.valueOf(r98));
        if (State.access$1200(r10) != null) goto L57;
        int r99 = r72.getResourceId(R.styleable.Badge_badgeShapeAppearanceOverlay, 0);
    L58:
        State.access$1202(r0, Integer.valueOf(r99));
        if (State.access$1300(r10) != null) goto L61;
        int r910 = r72.getResourceId(R.styleable.Badge_badgeWithTextShapeAppearance, R.style.ShapeAppearance_M3_Sys_Shape_Corner_Full);
    L62:
        State.access$1302(r0, Integer.valueOf(r910));
        if (State.access$1400(r10) != null) goto L65;
        int r911 = r72.getResourceId(R.styleable.Badge_badgeWithTextShapeAppearanceOverlay, 0);
    L66:
        State.access$1402(r0, Integer.valueOf(r911));
        if (State.access$1500(r10) != null) goto L69;
        int r912 = readColorFromAttributes(r6, r72, R.styleable.Badge_backgroundColor);
    L70:
        State.access$1502(r0, Integer.valueOf(r912));
        if (State.access$1600(r10) != null) goto L73;
        int r913 = r72.getResourceId(R.styleable.Badge_badgeTextAppearance, R.style.TextAppearance_MaterialComponents_Badge);
    L74:
        State.access$1602(r0, Integer.valueOf(r913));
        if (State.access$1700(r10) == null) goto L78;
        State.access$1702(r0, State.access$1700(r10));
    L82:
        if (State.access$1800(r10) != null) goto L84;
        int r62 = r72.getInt(R.styleable.Badge_badgeGravity, 8388661);
    L85:
        State.access$1802(r0, Integer.valueOf(r62));
        if (State.access$1900(r10) != null) goto L88;
        int r63 = r72.getDimensionPixelSize(R.styleable.Badge_badgeWidePadding, r82.getDimensionPixelSize(R.dimen.mtrl_badge_long_text_horizontal_padding));
    L89:
        State.access$1902(r0, Integer.valueOf(r63));
        if (State.access$2000(r10) != null) goto L92;
        int r64 = r72.getDimensionPixelSize(R.styleable.Badge_badgeVerticalPadding, r82.getDimensionPixelSize(R.dimen.m3_badge_with_text_vertical_padding));
    L93:
        State.access$2002(r0, Integer.valueOf(r64));
        if (State.access$2100(r10) != null) goto L96;
        int r65 = r72.getDimensionPixelOffset(R.styleable.Badge_horizontalOffset, 0);
    L97:
        State.access$2102(r0, Integer.valueOf(r65));
        if (State.access$2200(r10) != null) goto L100;
        int r66 = r72.getDimensionPixelOffset(R.styleable.Badge_verticalOffset, 0);
    L101:
        State.access$2202(r0, Integer.valueOf(r66));
        if (State.access$2300(r10) != null) goto L104;
        int r67 = r72.getDimensionPixelOffset(R.styleable.Badge_horizontalOffsetWithText, State.access$2100(r0).intValue());
    L105:
        State.access$2302(r0, Integer.valueOf(r67));
        if (State.access$2400(r10) != null) goto L108;
        int r68 = r72.getDimensionPixelOffset(R.styleable.Badge_verticalOffsetWithText, State.access$2200(r0).intValue());
    L109:
        State.access$2402(r0, Integer.valueOf(r68));
        if (State.access$2500(r10) != null) goto L112;
        int r69 = r72.getDimensionPixelOffset(R.styleable.Badge_largeFontVerticalOffsetAdjustment, 0);
    L113:
        State.access$2502(r0, Integer.valueOf(r69));
        if (State.access$2600(r10) != null) goto L116;
        int r610 = 0;
    L117:
        State.access$2602(r0, Integer.valueOf(r610));
        if (State.access$2700(r10) != null) goto L120;
        int r611 = 0;
    L121:
        State.access$2702(r0, Integer.valueOf(r611));
        if (State.access$2800(r10) != null) goto L124;
        boolean r612 = r72.getBoolean(R.styleable.Badge_autoAdjustToWithinGrandparentBounds, false);
    L125:
        State.access$2802(r0, Boolean.valueOf(r612));
        r72.recycle();
        if (State.access$2900(r10) != null) goto L128;
        State.access$2902(r0, Locale.getDefault(Locale.Category.FORMAT));
    L129:
        this.overridingState = r10;
        return;
    L128:
        State.access$2902(r0, State.access$2900(r10));
        goto L129
    L124:
        r612 = State.access$2800(r10).booleanValue();
        goto L125
    L120:
        r611 = State.access$2700(r10).intValue();
        goto L121
    L116:
        r610 = State.access$2600(r10).intValue();
        goto L117
    L112:
        r69 = State.access$2500(r10).intValue();
        goto L113
    L108:
        r68 = State.access$2400(r10).intValue();
        goto L109
    L104:
        r67 = State.access$2300(r10).intValue();
        goto L105
    L100:
        r66 = State.access$2200(r10).intValue();
        goto L101
    L96:
        r65 = State.access$2100(r10).intValue();
        goto L97
    L92:
        r64 = State.access$2000(r10).intValue();
        goto L93
    L88:
        r63 = State.access$1900(r10).intValue();
        goto L89
    L84:
        r62 = State.access$1800(r10).intValue();
        goto L85
    L78:
        if (r72.hasValue(R.styleable.Badge_badgeTextColor) == false) goto L80;
        State.access$1702(r0, Integer.valueOf(readColorFromAttributes(r6, r72, R.styleable.Badge_badgeTextColor)));
        goto L82
    L80:
        State.access$1702(r0, Integer.valueOf(new TextAppearance(r6, State.access$1600(r0).intValue()).getTextColor().getDefaultColor()));
        goto L82
    L73:
        r913 = State.access$1600(r10).intValue();
        goto L74
    L69:
        r912 = State.access$1500(r10).intValue();
        goto L70
    L65:
        r911 = State.access$1400(r10).intValue();
        goto L66
    L61:
        r910 = State.access$1300(r10).intValue();
        goto L62
    L57:
        r99 = State.access$1200(r10).intValue();
        goto L58
    L53:
        r98 = State.access$1100(r10).intValue();
        goto L54
    L49:
        r97 = State.access$1000(r10);
        goto L50
    L45:
        r96 = State.access$900(r10);
        goto L46
    L39:
        if (State.access$800(r10).booleanValue() == true) goto L42;
        r2 = false;
        goto L42
    L35:
        r95 = State.access$700(r10);
        goto L36
    L31:
        r94 = State.access$600(r10);
        goto L32
    L27:
        r93 = State.access$500(r10);
        goto L28
    L22:
        if (r72.hasValue(R.styleable.Badge_badgeText) == false) goto L24;
        State.access$302(r0, r72.getString(R.styleable.Badge_badgeText));
        goto L24
    L15:
        if (r72.hasValue(R.styleable.Badge_number) == false) goto L17;
        State.access$202(r0, r72.getInt(R.styleable.Badge_number, 0));
        goto L19
    L17:
        State.access$202(r0, -1);
        goto L19
    L10:
        r92 = State.access$100(r10);
        goto L11
    }

    private TypedArray generateTypedArray(Context r7, int r8, int r9, int r10) {
        if (r8 == 0) goto L6;
        AttributeSet r82 = DrawableUtils.parseDrawableXml(r7, r8, BADGE_RESOURCE_TAG);
        int r0 = r82.getStyleAttribute();
    L5:
        AttributeSet r1 = r82;
        if (r0 != 0) goto L9;
        int r4 = r10;
    L11:
        return ThemeEnforcement.obtainStyledAttributes(r7, r1, R.styleable.Badge, r9, r4, new int[0]);
    L9:
        r4 = r0;
        goto L11
    L6:
        r82 = null;
        r0 = 0;
        goto L5
    }

    private static int readColorFromAttributes(Context r0, TypedArray r1, int r2) {
        return MaterialResources.getColorStateList(r0, r1, r2).getDefaultColor();
    }

    public void clearNumber() {
        setNumber(-1);
    }

    public void clearText() {
        setText(null);
    }

    public int getAdditionalHorizontalOffset() {
        return State.access$2600(this.currentState).intValue();
    }

    public int getAdditionalVerticalOffset() {
        return State.access$2700(this.currentState).intValue();
    }

    public int getAlpha() {
        return State.access$100(this.currentState);
    }

    public int getBackgroundColor() {
        return State.access$1500(this.currentState).intValue();
    }

    public int getBadgeGravity() {
        return State.access$1800(this.currentState).intValue();
    }

    public int getBadgeHorizontalPadding() {
        return State.access$1900(this.currentState).intValue();
    }

    public int getBadgeShapeAppearanceOverlayResId() {
        return State.access$1200(this.currentState).intValue();
    }

    public int getBadgeShapeAppearanceResId() {
        return State.access$1100(this.currentState).intValue();
    }

    public int getBadgeTextColor() {
        return State.access$1700(this.currentState).intValue();
    }

    public int getBadgeVerticalPadding() {
        return State.access$2000(this.currentState).intValue();
    }

    public int getBadgeWithTextShapeAppearanceOverlayResId() {
        return State.access$1400(this.currentState).intValue();
    }

    public int getBadgeWithTextShapeAppearanceResId() {
        return State.access$1300(this.currentState).intValue();
    }

    public int getContentDescriptionExceedsMaxBadgeNumberStringResource() {
        return State.access$700(this.currentState);
    }

    public CharSequence getContentDescriptionForText() {
        return State.access$400(this.currentState);
    }

    public CharSequence getContentDescriptionNumberless() {
        return State.access$500(this.currentState);
    }

    public int getContentDescriptionQuantityStrings() {
        return State.access$600(this.currentState);
    }

    public int getHorizontalOffsetWithText() {
        return State.access$2300(this.currentState).intValue();
    }

    public int getHorizontalOffsetWithoutText() {
        return State.access$2100(this.currentState).intValue();
    }

    public int getLargeFontVerticalOffsetAdjustment() {
        return State.access$2500(this.currentState).intValue();
    }

    public int getMaxCharacterCount() {
        return State.access$900(this.currentState);
    }

    public int getMaxNumber() {
        return State.access$1000(this.currentState);
    }

    public int getNumber() {
        return State.access$200(this.currentState);
    }

    public Locale getNumberLocale() {
        return State.access$2900(this.currentState);
    }

    public State getOverridingState() {
        return this.overridingState;
    }

    public String getText() {
        return State.access$300(this.currentState);
    }

    public int getTextAppearanceResId() {
        return State.access$1600(this.currentState).intValue();
    }

    public int getVerticalOffsetWithText() {
        return State.access$2400(this.currentState).intValue();
    }

    public int getVerticalOffsetWithoutText() {
        return State.access$2200(this.currentState).intValue();
    }

    public boolean hasNumber() {
        if (State.access$200(this.currentState) == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean hasText() {
        if (State.access$300(this.currentState) == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Deprecated
    public boolean isAutoAdjustedToGrandparentBounds() {
        return State.access$2800(this.currentState).booleanValue();
    }

    public boolean isVisible() {
        return State.access$800(this.currentState).booleanValue();
    }

    public void setAdditionalHorizontalOffset(int r3) {
        State.access$2602(this.overridingState, Integer.valueOf(r3));
        State.access$2602(this.currentState, Integer.valueOf(r3));
    }

    public void setAdditionalVerticalOffset(int r3) {
        State.access$2702(this.overridingState, Integer.valueOf(r3));
        State.access$2702(this.currentState, Integer.valueOf(r3));
    }

    public void setAlpha(int r2) {
        State.access$102(this.overridingState, r2);
        State.access$102(this.currentState, r2);
    }

    @Deprecated
    public void setAutoAdjustToGrandparentBounds(boolean r3) {
        State.access$2802(this.overridingState, Boolean.valueOf(r3));
        State.access$2802(this.currentState, Boolean.valueOf(r3));
    }

    public void setBackgroundColor(int r3) {
        State.access$1502(this.overridingState, Integer.valueOf(r3));
        State.access$1502(this.currentState, Integer.valueOf(r3));
    }

    public void setBadgeGravity(int r3) {
        State.access$1802(this.overridingState, Integer.valueOf(r3));
        State.access$1802(this.currentState, Integer.valueOf(r3));
    }

    public void setBadgeHorizontalPadding(int r3) {
        State.access$1902(this.overridingState, Integer.valueOf(r3));
        State.access$1902(this.currentState, Integer.valueOf(r3));
    }

    public void setBadgeShapeAppearanceOverlayResId(int r3) {
        State.access$1202(this.overridingState, Integer.valueOf(r3));
        State.access$1202(this.currentState, Integer.valueOf(r3));
    }

    public void setBadgeShapeAppearanceResId(int r3) {
        State.access$1102(this.overridingState, Integer.valueOf(r3));
        State.access$1102(this.currentState, Integer.valueOf(r3));
    }

    public void setBadgeTextColor(int r3) {
        State.access$1702(this.overridingState, Integer.valueOf(r3));
        State.access$1702(this.currentState, Integer.valueOf(r3));
    }

    public void setBadgeVerticalPadding(int r3) {
        State.access$2002(this.overridingState, Integer.valueOf(r3));
        State.access$2002(this.currentState, Integer.valueOf(r3));
    }

    public void setBadgeWithTextShapeAppearanceOverlayResId(int r3) {
        State.access$1402(this.overridingState, Integer.valueOf(r3));
        State.access$1402(this.currentState, Integer.valueOf(r3));
    }

    public void setBadgeWithTextShapeAppearanceResId(int r3) {
        State.access$1302(this.overridingState, Integer.valueOf(r3));
        State.access$1302(this.currentState, Integer.valueOf(r3));
    }

    public void setContentDescriptionExceedsMaxBadgeNumberStringResource(int r2) {
        State.access$702(this.overridingState, r2);
        State.access$702(this.currentState, r2);
    }

    public void setContentDescriptionForText(CharSequence r2) {
        State.access$402(this.overridingState, r2);
        State.access$402(this.currentState, r2);
    }

    public void setContentDescriptionNumberless(CharSequence r2) {
        State.access$502(this.overridingState, r2);
        State.access$502(this.currentState, r2);
    }

    public void setContentDescriptionQuantityStringsResource(int r2) {
        State.access$602(this.overridingState, r2);
        State.access$602(this.currentState, r2);
    }

    public void setHorizontalOffsetWithText(int r3) {
        State.access$2302(this.overridingState, Integer.valueOf(r3));
        State.access$2302(this.currentState, Integer.valueOf(r3));
    }

    public void setHorizontalOffsetWithoutText(int r3) {
        State.access$2102(this.overridingState, Integer.valueOf(r3));
        State.access$2102(this.currentState, Integer.valueOf(r3));
    }

    public void setLargeFontVerticalOffsetAdjustment(int r3) {
        State.access$2502(this.overridingState, Integer.valueOf(r3));
        State.access$2502(this.currentState, Integer.valueOf(r3));
    }

    public void setMaxCharacterCount(int r2) {
        State.access$902(this.overridingState, r2);
        State.access$902(this.currentState, r2);
    }

    public void setMaxNumber(int r2) {
        State.access$1002(this.overridingState, r2);
        State.access$1002(this.currentState, r2);
    }

    public void setNumber(int r2) {
        State.access$202(this.overridingState, r2);
        State.access$202(this.currentState, r2);
    }

    public void setNumberLocale(Locale r2) {
        State.access$2902(this.overridingState, r2);
        State.access$2902(this.currentState, r2);
    }

    public void setText(String r2) {
        State.access$302(this.overridingState, r2);
        State.access$302(this.currentState, r2);
    }

    public void setTextAppearanceResId(int r3) {
        State.access$1602(this.overridingState, Integer.valueOf(r3));
        State.access$1602(this.currentState, Integer.valueOf(r3));
    }

    public void setVerticalOffsetWithText(int r3) {
        State.access$2402(this.overridingState, Integer.valueOf(r3));
        State.access$2402(this.currentState, Integer.valueOf(r3));
    }

    public void setVerticalOffsetWithoutText(int r3) {
        State.access$2202(this.overridingState, Integer.valueOf(r3));
        State.access$2202(this.currentState, Integer.valueOf(r3));
    }

    public void setVisible(boolean r3) {
        State.access$802(this.overridingState, Boolean.valueOf(r3));
        State.access$802(this.currentState, Boolean.valueOf(r3));
    }
}
