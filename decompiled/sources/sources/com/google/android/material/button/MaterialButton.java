package com.google.android.material.button;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.customview.view.AbsSavedState;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearance;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.shape.StateListShapeAppearanceModel;
import com.google.android.material.shape.StateListSizeChange;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.LinkedHashSet;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class MaterialButton extends AppCompatButton implements Checkable, Shapeable {
    private static final int[] CHECKABLE_STATE_SET = null;
    private static final int[] CHECKED_STATE_SET = null;
    private static final int DEF_STYLE_RES = 0;
    public static final int ICON_GRAVITY_END = 3;
    public static final int ICON_GRAVITY_START = 1;
    public static final int ICON_GRAVITY_TEXT_END = 4;
    public static final int ICON_GRAVITY_TEXT_START = 2;
    public static final int ICON_GRAVITY_TEXT_TOP = 32;
    public static final int ICON_GRAVITY_TOP = 16;
    private static final String LOG_TAG = "MaterialButton";
    private static final int MATERIAL_SIZE_OVERLAY_ATTR = 0;
    private static final float OPTICAL_CENTER_RATIO = 0.11f;
    private static final int UNSET = Integer.MIN_VALUE;
    private static final FloatPropertyCompat<MaterialButton> WIDTH_INCREASE = null;
    private String accessibilityClassName;
    int allowedWidthDecrease;
    private boolean broadcasting;
    private boolean checked;
    private float displayedWidthDecrease;
    private float displayedWidthIncrease;
    private Drawable icon;
    private int iconGravity;
    private int iconLeft;
    private int iconPadding;
    private int iconSize;
    private ColorStateList iconTint;
    private PorterDuff.Mode iconTintMode;
    private int iconTop;
    private boolean isInHorizontalButtonGroup;
    private final MaterialButtonHelper materialButtonHelper;
    private final LinkedHashSet<OnCheckedChangeListener> onCheckedChangeListeners;
    private OnPressedChangeListener onPressedChangeListenerInternal;
    private boolean opticalCenterEnabled;
    private int opticalCenterShift;
    private int orientation;
    private LinearLayout.LayoutParams originalLayoutParams;
    private int originalPaddingEnd;
    private int originalPaddingStart;
    private float originalWidth;
    private Drawable secondaryIcon;
    private int secondaryIconGravity;
    private int secondaryIconLeft;
    private ColorStateList secondaryIconTint;
    private PorterDuff.Mode secondaryIconTintMode;
    private int secondaryIconTop;
    StateListSizeChange sizeChange;
    private boolean stopNullSecondaryIconUpdate;
    private WidthChangeDirection widthChangeDirection;
    int widthChangeMax;
    private SpringAnimation widthIncreaseSpringAnimation;

    /* renamed from: com.google.android.material.button.MaterialButton$1, reason: invalid class name */
    public class AnonymousClass1 extends FloatPropertyCompat<MaterialButton> {
        public AnonymousClass1(String r1) {
            super(r1);
        }

        /* renamed from: getValue, reason: avoid collision after fix types in other method */
        public float getValue2(MaterialButton r1) {
            return MaterialButton.access$000(r1);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ float getValue(MaterialButton r1) {
            return getValue2(r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(MaterialButton r1, float r2) {
            MaterialButton.access$100(r1, r2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public /* bridge */ /* synthetic */ void setValue(MaterialButton r1, float r2) {
            setValue2(r1, r2);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface IconGravity {
    }

    public final class InspectionCompanion implements android.view.inspector.InspectionCompanion {
        private int mIconPaddingId;
        private boolean mPropertiesMapped;

        public InspectionCompanion() {
            this.mPropertiesMapped = false;
        }

        public void mapProperties(PropertyMapper r3) {
            this.mIconPaddingId = r3.mapInt("iconPadding", R.attr.iconPadding);
            this.mPropertiesMapped = true;
        }

        public void readProperties(MaterialButton r2, PropertyReader r3) {
            if (this.mPropertiesMapped == false) goto L7;
            r3.readInt(this.mIconPaddingId, r2.getIconPadding());
            return;
        L7:
            throw MaterialButton$InspectionCompanion$$ExternalSyntheticApiModelOutline0.m();
        }

        public /* bridge */ /* synthetic */ void readProperties(Object r1, PropertyReader r2) {
            readProperties((MaterialButton) r1, r2);
        }
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(MaterialButton r1, boolean r2);
    }

    public interface OnPressedChangeListener {
        void onPressedChanged(MaterialButton r1, boolean r2);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        boolean checked;

        /* renamed from: com.google.android.material.button.MaterialButton$SavedState$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.ClassLoaderCreator<SavedState> {
            public AnonymousClass1() {
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel r2) {
                return new SavedState(r2, null);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel r1, ClassLoader r2) {
                return new SavedState(r1, r2);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ Object createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public /* bridge */ /* synthetic */ SavedState createFromParcel(Parcel r1, ClassLoader r2) {
                return createFromParcel(r1, r2);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int r1) {
                return new SavedState[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ Object[] newArray(int r1) {
                return newArray(r1);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        public SavedState(Parcel r1, ClassLoader r2) {
            super(r1, r2);
            if (r2 != null) goto L5;
            getClass().getClassLoader();
        L5:
            readFromParcel(r1);
        }

        public SavedState(Parcelable r1) {
            super(r1);
        }

        private void readFromParcel(Parcel r2) {
            boolean r0 = true;
            if (r2.readInt() == 1) goto L6;
            r0 = false;
        L6:
            this.checked = r0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            super.writeToParcel(r1, r2);
            r1.writeInt(this.checked ? 1 : 0);
        }
    }

    public enum WidthChangeDirection extends Enum<WidthChangeDirection> {
        private static final /* synthetic */ WidthChangeDirection[] $VALUES = null;
        public static final WidthChangeDirection BOTH = null;
        public static final WidthChangeDirection END = null;
        public static final WidthChangeDirection NONE = null;
        public static final WidthChangeDirection START = null;

        private static /* synthetic */ WidthChangeDirection[] $values() {
            return new WidthChangeDirection[]{NONE, START, END, BOTH};
        }

        static {
            NONE = new WidthChangeDirection("NONE", 0);
            START = new WidthChangeDirection("START", 1);
            END = new WidthChangeDirection("END", 2);
            BOTH = new WidthChangeDirection("BOTH", 3);
            $VALUES = $values();
        }

        WidthChangeDirection(String r1, int r2) {
        }

        public static WidthChangeDirection valueOf(String r1) {
            return (WidthChangeDirection) Enum.valueOf(WidthChangeDirection.class, r1);
        }

        public static WidthChangeDirection[] values() {
            return (WidthChangeDirection[]) $VALUES.clone();
        }
    }

    /* renamed from: $r8$lambda$-n63mSAUlMv1ovFPtLcymPNJIzs, reason: not valid java name */
    public static /* synthetic */ void m1822$r8$lambda$n63mSAUlMv1ovFPtLcymPNJIzs(MaterialButton r0, Drawable r1) {
        r0.setIcon(r1);
    }

    /* renamed from: $r8$lambda$082l-6qUlajHJ6tYsmjt9Msiwck, reason: not valid java name */
    public static /* synthetic */ void m1823$r8$lambda$082l6qUlajHJ6tYsmjt9Msiwck(MaterialButton r1) {
        r1.opticalCenterShift = r1.getOpticalCenterShift();
        r1.updatePaddingsAndSizeForWidthAnimation();
        r1.invalidate();
    }

    public static /* synthetic */ void $r8$lambda$Bs8HZBuSJJrm54CbiwKYjTkjfPE(MaterialButton r0, Drawable r1) {
        r0.setIcon(r1);
    }

    public static /* synthetic */ void $r8$lambda$KVPicg6bnY_VCCRO776v1KVM2PU(MaterialButton r0, int r1) {
        r0.setIconSize(r1);
    }

    /* renamed from: $r8$lambda$S8Bw1Ddl9QWyZUUtNEn-eBlX3wE, reason: not valid java name */
    public static /* synthetic */ void m1824$r8$lambda$S8Bw1Ddl9QWyZUUtNEneBlX3wE(MaterialButton r0, Runnable r1) {
        r0.getClass();
        r1.run();
        r0.recoverOriginalLayoutParams();
        r0.requestLayout();
    }

    public static /* synthetic */ void $r8$lambda$ch2aaORFCLrGEn8lT2Fq9UtauwI(MaterialButton r1, float r2) {
        int r22 = (int) (r2 * OPTICAL_CENTER_RATIO);
        if (r1.opticalCenterShift == r22) goto L6;
        r1.opticalCenterShift = r22;
        r1.updatePaddingsAndSizeForWidthAnimation();
        r1.invalidate();
        return;
    }

    static {
        CHECKABLE_STATE_SET = new int[]{android.R.attr.state_checkable};
        CHECKED_STATE_SET = new int[]{android.R.attr.state_checked};
        DEF_STYLE_RES = R.style.Widget_MaterialComponents_Button;
        MATERIAL_SIZE_OVERLAY_ATTR = R.attr.materialSizeOverlay;
        WIDTH_INCREASE = new AnonymousClass1("widthIncrease");
    }

    public MaterialButton(Context r2) {
        this(r2, null);
    }

    public MaterialButton(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.materialButtonStyle);
    }

    public MaterialButton(Context r8, AttributeSet r9, int r10) {
        int r4 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r8, r9, r10, r4, new int[]{MATERIAL_SIZE_OVERLAY_ATTR}), r9, r10);
        this.onCheckedChangeListeners = new LinkedHashSet();
        boolean r82 = false;
        this.checked = false;
        this.broadcasting = false;
        this.orientation = Integer.MIN_VALUE;
        this.originalWidth = -2.1474836E9f;
        this.originalPaddingStart = Integer.MIN_VALUE;
        this.originalPaddingEnd = Integer.MIN_VALUE;
        this.allowedWidthDecrease = Integer.MIN_VALUE;
        this.widthChangeDirection = WidthChangeDirection.BOTH;
        Context r0 = getContext();
        TypedArray r92 = ThemeEnforcement.obtainStyledAttributes(r0, r9, R.styleable.MaterialButton, r10, r4, new int[0]);
        this.iconPadding = r92.getDimensionPixelSize(R.styleable.MaterialButton_iconPadding, 0);
        int r102 = r92.getInt(R.styleable.MaterialButton_iconTintMode, -1);
        PorterDuff.Mode r5 = PorterDuff.Mode.SRC_IN;
        this.iconTintMode = ViewUtils.parseTintMode(r102, r5);
        this.iconTint = MaterialResources.getColorStateList(getContext(), r92, R.styleable.MaterialButton_iconTint);
        this.icon = MaterialResources.getDrawable(getContext(), r92, R.styleable.MaterialButton_icon);
        this.iconGravity = r92.getInteger(R.styleable.MaterialButton_iconGravity, 1);
        this.iconSize = r92.getDimensionPixelSize(R.styleable.MaterialButton_iconSize, 0);
        this.secondaryIconTintMode = ViewUtils.parseTintMode(r92.getInt(R.styleable.MaterialButton_secondaryIconTintMode, -1), r5);
        if (r92.hasValue(R.styleable.MaterialButton_secondaryIconTint) == false) goto L5;
        ColorStateList r103 = MaterialResources.getColorStateList(getContext(), r92, R.styleable.MaterialButton_secondaryIconTint);
    L6:
        this.secondaryIconTint = r103;
        this.secondaryIconGravity = r92.getInteger(R.styleable.MaterialButton_secondaryIconGravity, 3);
        Drawable r104 = MaterialResources.getDrawable(getContext(), r92, R.styleable.MaterialButton_secondaryIcon);
        this.secondaryIcon = r104;
        if (r104 != null) goto L9;
        boolean r105 = true;
    L10:
        this.stopNullSecondaryIconUpdate = r105;
        ShapeAppearance r106 = StateListShapeAppearanceModel.create(r0, r92, R.styleable.MaterialButton_shapeAppearance);
        if (r106 != null) goto L14;
        r106 = ShapeAppearanceModel.builder(r0, r9, r10, r4).build();
    L14:
        boolean r02 = r92.getBoolean(R.styleable.MaterialButton_opticalCenterEnabled, false);
        MaterialButtonHelper r1 = new MaterialButtonHelper(this, r106);
        this.materialButtonHelper = r1;
        r1.loadFromAttributes(r92);
        setCheckedInternal(r92.getBoolean(R.styleable.MaterialButton_android_checked, false));
        if ((r106 instanceof StateListShapeAppearanceModel) == false) goto L17;
        r1.setCornerSpringForce(createSpringForce());
    L17:
        setOpticalCenterEnabled(r02);
        r92.recycle();
        setCompoundDrawablePadding(this.iconPadding);
        if (this.icon == null) goto L20;
        boolean r93 = true;
    L21:
        updateIcon(r93);
        if (this.secondaryIcon == null) goto L24;
        r82 = true;
    L24:
        updateSecondaryIcon(r82);
        return;
    L20:
        r93 = false;
        goto L21
    L9:
        r105 = false;
        goto L10
    L5:
        r103 = this.iconTint;
        goto L6
    }

    public static /* synthetic */ float access$000(MaterialButton r0) {
        return r0.getDisplayedWidthIncrease();
    }

    public static /* synthetic */ void access$100(MaterialButton r0, float r1) {
        r0.setDisplayedWidthIncrease(r1);
    }

    private boolean areIconsGravitySameAlignment() {
        if (isIconStart() == false) goto L7;
        if (isSecondaryIconStart() == false) goto L7;
        return true;
    L7:
        if (isIconEnd() == false) goto L11;
        if (isSecondaryIconEnd() == false) goto L11;
        return true;
    L11:
        if (isIconTop() == true) goto L13;
        return false;
    L13:
        if (isSecondaryIconTop() == false) goto L20;
        return true;
    L20:
        return false;
    }

    private int calculateEffectiveWidthChangeMax() {
        int r0 = this.widthChangeDirection.ordinal();
        if (r0 == 1) goto L12;
        if (r0 == 2) goto L12;
        if (r0 == 3) goto L10;
        return 0;
    L10:
        return this.widthChangeMax;
    L12:
        return this.widthChangeMax / 2;
    }

    private boolean canUpdateWithoutTextAlignment(int r3) {
        Layout.Alignment r2 = getActualTextAlignment();
        if (r3 != 1) goto L5;
    L17:
        return true;
    L5:
        if (r3 == 3) goto L17;
        if (r3 != 2) goto L11;
        if (r2 == Layout.Alignment.ALIGN_NORMAL) goto L17;
    L11:
        if (r3 == 4) goto L13;
        return false;
    L13:
        if (r2 == Layout.Alignment.ALIGN_OPPOSITE) goto L17;
        return false;
    }

    private SpringForce createSpringForce() {
        return MotionUtils.resolveThemeSpringForce(getContext(), R.attr.motionSpringFastSpatial, R.style.Motion_Material3_Spring_Standard_Fast_Spatial);
    }

    private Layout.Alignment getActualTextAlignment() {
        int r0 = getTextAlignment();
        if (r0 == 1) goto L17;
        if (r0 == 6) goto L15;
        if (r0 == 3) goto L15;
        if (r0 == 4) goto L13;
        return Layout.Alignment.ALIGN_NORMAL;
    L13:
        return Layout.Alignment.ALIGN_CENTER;
    L15:
        return Layout.Alignment.ALIGN_OPPOSITE;
    L17:
        return getGravityTextAlignment();
    }

    private float getDisplayedWidthIncrease() {
        return this.displayedWidthIncrease;
    }

    private Layout.Alignment getGravityTextAlignment() {
        int r1 = getGravity() & 8388615;
        if (r1 == 1) goto L13;
        if (r1 == 5) goto L11;
        if (r1 == 8388613) goto L11;
        return Layout.Alignment.ALIGN_NORMAL;
    L11:
        return Layout.Alignment.ALIGN_OPPOSITE;
    L13:
        return Layout.Alignment.ALIGN_CENTER;
    }

    private int getIconLeft(int r5, int r6) {
        Drawable r0 = this.icon;
        boolean r1 = false;
        if (r0 == null) goto L7;
        int r2 = this.iconSize;
        if (r2 != 0) goto L8;
        r2 = r0.getIntrinsicWidth();
    L8:
        Drawable r02 = this.secondaryIcon;
        if (r02 == null) goto L13;
        int r3 = this.iconSize;
        if (r3 != 0) goto L14;
        r3 = r02.getIntrinsicWidth();
    L14:
        int r52 = (((((r5 - getTextLayoutWidth()) - getPaddingEnd()) - r2) - r3) - this.iconPadding) - getPaddingStart();
        if (getActualTextAlignment() != Layout.Alignment.ALIGN_CENTER) goto L17;
        r52 = r52 / 2;
    L17:
        boolean r4 = isLayoutRTL();
        if (r6 != 4) goto L20;
        r1 = true;
    L20:
        if (r4 != r1) goto L22;
        return r52;
    L22:
        return -r52;
    L13:
        r3 = 0;
        goto L14
    L7:
        r2 = 0;
        goto L8
    }

    private int getIconTop(int r2, int r3) {
        return Math.max(0, (((((r2 - getTextHeight()) - getPaddingTop()) - r3) - this.iconPadding) - getPaddingBottom()) / 2);
    }

    private int getOpticalCenterShift() {
        if (this.opticalCenterEnabled == true) goto L5;
        return 0;
    L5:
        if (this.isInHorizontalButtonGroup == false) goto L12;
        MaterialShapeDrawable r1 = this.materialButtonHelper.getMaterialShapeDrawable();
        if (r1 != null) goto L9;
        return 0;
    L9:
        return (int) (r1.getCornerSizeDiffX() * OPTICAL_CENTER_RATIO);
    L12:
        return 0;
    }

    private int getTextHeight() {
        if (getLineCount() > 1) goto L5;
        TextPaint r0 = getPaint();
        String r1 = getText().toString();
        if (getTransformationMethod() == null) goto L9;
        r1 = getTransformationMethod().getTransformation(r1, this).toString();
    L9:
        Rect r2 = new Rect();
        r0.getTextBounds(r1, 0, r1.length(), r2);
        return Math.min(r2.height(), getLayout().getHeight());
    L5:
        return getLayout().getHeight();
    }

    private int getTextLayoutWidth() {
        int r0 = getLineCount();
        float r1 = 0.0f;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L6;
        r1 = Math.max(r1, getLayout().getLineWidth(r2));
        r2 = r2 + 1;
        goto L3
    L6:
        return (int) Math.ceil(r1);
    }

    private Drawable getUpdatedIconFor(int r3) {
        if (r3 == 0) goto L24;
        if (r3 == 1) goto L17;
        if (r3 == 2) goto L10;
        return null;
    L10:
        if (this.secondaryIcon != null) goto L12;
    L15:
        return null;
    L12:
        if (isSecondaryIconEnd() == false) goto L15;
        return this.secondaryIcon;
    L17:
        if (this.secondaryIcon != null) goto L19;
    L22:
        return null;
    L19:
        if (isSecondaryIconTop() == false) goto L22;
        return this.secondaryIcon;
    L24:
        if (this.secondaryIcon != null) goto L26;
    L29:
        return null;
    L26:
        if (isSecondaryIconStart() == false) goto L29;
        return this.secondaryIcon;
    }

    private Drawable getUpdatedSecondaryIconFor(int r3) {
        if (r3 == 0) goto L24;
        if (r3 == 1) goto L17;
        if (r3 == 2) goto L10;
        return null;
    L10:
        if (this.icon != null) goto L12;
    L15:
        return null;
    L12:
        if (isIconEnd() == false) goto L15;
        return this.icon;
    L17:
        if (this.icon != null) goto L19;
    L22:
        return null;
    L19:
        if (isIconEnd() == false) goto L22;
        return this.icon;
    L24:
        if (this.icon != null) goto L26;
    L29:
        return null;
    L26:
        if (isIconStart() == false) goto L29;
        return this.icon;
    }

    private void initializeSizeAnimation() {
        SpringAnimation r0 = new SpringAnimation(this, WIDTH_INCREASE);
        this.widthIncreaseSpringAnimation = r0;
        r0.setSpring(createSpringForce());
    }

    private boolean isIconEnd() {
        int r1 = this.iconGravity;
        if (r1 != 3) goto L5;
        return true;
    L5:
        if (r1 == 4) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean isIconStart() {
        int r2 = this.iconGravity;
        if (r2 != 1) goto L5;
    L9:
        return true;
    L5:
        if (r2 == 2) goto L9;
        return false;
    }

    private boolean isIconTop() {
        int r1 = this.iconGravity;
        if (r1 != 16) goto L5;
        return true;
    L5:
        if (r1 == 32) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean isInHorizontalButtonGroup() {
        if ((getParent() instanceof MaterialButtonGroup) == true) goto L5;
        return false;
    L5:
        if (((MaterialButtonGroup) getParent()).getOrientation() != 0) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isLayoutRTL() {
        if (getLayoutDirection() != 1) goto L5;
        return true;
    L5:
        return false;
    }

    private boolean isSecondaryIconEnd() {
        int r1 = this.secondaryIconGravity;
        if (r1 != 3) goto L5;
        return true;
    L5:
        if (r1 == 4) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean isSecondaryIconStart() {
        int r2 = this.secondaryIconGravity;
        if (r2 != 1) goto L5;
    L9:
        return true;
    L5:
        if (r2 == 2) goto L9;
        return false;
    }

    private boolean isSecondaryIconTop() {
        int r1 = this.secondaryIconGravity;
        if (r1 != 16) goto L5;
        return true;
    L5:
        if (r1 == 32) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean isUsingOriginalBackground() {
        MaterialButtonHelper r0 = this.materialButtonHelper;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isBackgroundOverwritten() == true) goto L10;
        return true;
    L10:
        return false;
    }

    private void maybeAnimateSize(boolean r4) {
        if (this.sizeChange != null) goto L6;
        return;
    L6:
        if (this.widthIncreaseSpringAnimation != null) goto L9;
        initializeSizeAnimation();
    L9:
        if (this.isInHorizontalButtonGroup == false) goto L14;
        this.widthIncreaseSpringAnimation.animateToFinalPosition(Math.min(calculateEffectiveWidthChangeMax(), this.sizeChange.getSizeChangeForState(getDrawableState()).widthChange.getChange(getWidth())));
        if (r4 == false) goto L15;
        this.widthIncreaseSpringAnimation.skipToEnd();
        return;
    L15:
        return;
    }

    private boolean maybeRunAfterWidthAnimation(Runnable r2) {
        SpringAnimation r0 = this.widthIncreaseSpringAnimation;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isRunning() == false) goto L10;
        post(new MaterialButton$$ExternalSyntheticLambda1(this, r2));
        return true;
    L10:
        return false;
    }

    private void setCheckedInternal(boolean r3) {
        if (isCheckable() == true) goto L5;
        return;
    L5:
        if (this.checked == r3) goto L20;
        this.checked = r3;
        refreshDrawableState();
        if ((getParent() instanceof MaterialButtonToggleGroup) == false) goto L10;
        ((MaterialButtonToggleGroup) getParent()).onButtonCheckedStateChanged(this, this.checked);
    L10:
        if (this.broadcasting == true) goto L21;
        this.broadcasting = true;
        Iterator<OnCheckedChangeListener> r32 = this.onCheckedChangeListeners.iterator();
    L14:
        if (r32.hasNext() == false) goto L16;
        r32.next().onCheckedChanged(this, this.checked);
        goto L14
    L16:
        this.broadcasting = false;
        return;
    L21:
        return;
    }

    private void setDisplayedWidthIncrease(float r2) {
        if (this.displayedWidthIncrease == r2) goto L8;
        this.displayedWidthIncrease = r2;
        updatePaddingsAndSizeForWidthAnimation();
        invalidate();
        if ((getParent() instanceof MaterialButtonGroup) == false) goto L9;
        ((MaterialButtonGroup) getParent()).onButtonWidthChanged(this, (int) this.displayedWidthIncrease);
        return;
    L9:
        return;
    }

    private void updateIcon(boolean r8) {
        Drawable r0 = this.icon;
        if (r0 == null) goto L16;
        Drawable r02 = DrawableCompat.wrap(r0).mutate();
        this.icon = r02;
        r02.setTintList(this.iconTint);
        PorterDuff.Mode r03 = this.iconTintMode;
        if (r03 == null) goto L7;
        this.icon.setTintMode(r03);
    L7:
        int r04 = this.iconSize;
        if (r04 != 0) goto L11;
        r04 = this.icon.getIntrinsicWidth();
    L11:
        int r2 = this.iconSize;
        if (r2 != 0) goto L15;
        r2 = this.icon.getIntrinsicHeight();
    L15:
        Drawable r3 = this.icon;
        int r4 = this.iconLeft;
        int r5 = this.iconTop;
        r3.setBounds(r4, r5, r04 + r4, r2 + r5);
        this.icon.setVisible(true, r8);
    L16:
        validateIconGravity();
        if (this.icon == null) goto L19;
    L23:
        Drawable[] r05 = getCompoundDrawablesRelative();
        Drawable r32 = r05[0];
        Drawable r42 = r05[1];
        Drawable r06 = r05[2];
        if (isIconStart() == false) goto L28;
        if (r32 == this.icon) goto L28;
    L35:
        boolean r07 = true;
    L37:
        if (r8 == true) goto L40;
        if (r07 == true) goto L40;
        return;
    L40:
        if (isIconStart() == false) goto L44;
        setCompoundDrawablesRelative(this.icon, getUpdatedIconFor(1), getUpdatedIconFor(2), null);
        return;
    L44:
        if (isIconEnd() == false) goto L48;
        setCompoundDrawablesRelative(getUpdatedIconFor(0), getUpdatedIconFor(1), this.icon, null);
        return;
    L48:
        if (isIconTop() == false) goto L52;
        setCompoundDrawablesRelative(getUpdatedIconFor(0), this.icon, getUpdatedIconFor(2), null);
        return;
    L52:
        return;
    L28:
        if (isIconEnd() == false) goto L32;
        if (r06 != this.icon) goto L35;
    L32:
        if (isIconTop() == true) goto L34;
    L36:
        r07 = false;
        goto L37
    L34:
        if (r42 == this.icon) goto L36;
    L19:
        if (this.secondaryIcon == null) goto L23;
        if (areIconsGravitySameAlignment() == false) goto L23;
    }

    private void updateIconPosition(int r3, int r4) {
        if (this.icon != null) goto L5;
        return;
    L5:
        if (getLayout() != null) goto L8;
        return;
    L8:
        if (isIconStart() == false) goto L10;
    L25:
        this.iconTop = 0;
        if (canUpdateWithoutTextAlignment(this.iconGravity) == false) goto L29;
        this.iconLeft = 0;
        updateIcon(false);
        return;
    L29:
        int r32 = getIconLeft(r3, this.iconGravity);
        if (this.iconLeft == r32) goto L36;
        this.iconLeft = r32;
        updateIcon(false);
        return;
    L36:
        return;
    L10:
        if (isIconEnd() == true) goto L25;
        if (isIconTop() == false) goto L34;
        this.iconLeft = 0;
        if (this.iconGravity != 16) goto L18;
        this.iconTop = 0;
        updateIcon(false);
        return;
    L18:
        int r33 = this.iconSize;
        if (r33 != 0) goto L21;
        r33 = this.icon.getIntrinsicHeight();
    L21:
        int r34 = getIconTop(r4, r33);
        if (this.iconTop == r34) goto L35;
        this.iconTop = r34;
        updateIcon(false);
        return;
    L35:
        return;
    }

    private void updatePaddingsAndSizeForWidthAnimation() {
        int r0 = (int) (this.displayedWidthIncrease - this.displayedWidthDecrease);
        boolean r1 = isLayoutRTL();
        int r2 = this.opticalCenterShift;
        if (r1 == false) goto L5;
        r2 = -r2;
    L5:
        int r12 = (r0 / 2) + r2;
        if (getLayoutParams() == null) goto L8;
        getLayoutParams().width = (int) (this.originalWidth + r0);
    L8:
        setPaddingRelative(this.originalPaddingStart + r12, getPaddingTop(), (this.originalPaddingEnd + r0) - r12, getPaddingBottom());
    }

    private void updateSecondaryIcon(boolean r8) {
        Drawable r0 = this.secondaryIcon;
        if (r0 == null) goto L16;
        Drawable r02 = DrawableCompat.wrap(r0).mutate();
        this.secondaryIcon = r02;
        r02.setTintList(this.secondaryIconTint);
        PorterDuff.Mode r03 = this.secondaryIconTintMode;
        if (r03 == null) goto L7;
        this.secondaryIcon.setTintMode(r03);
    L7:
        int r04 = this.iconSize;
        if (r04 != 0) goto L11;
        r04 = this.secondaryIcon.getIntrinsicWidth();
    L11:
        int r2 = this.iconSize;
        if (r2 != 0) goto L15;
        r2 = this.secondaryIcon.getIntrinsicHeight();
    L15:
        Drawable r3 = this.secondaryIcon;
        int r4 = this.secondaryIconLeft;
        int r5 = this.secondaryIconTop;
        r3.setBounds(r4, r5, r04 + r4, r2 + r5);
        this.secondaryIcon.setVisible(true, r8);
    L16:
        validateSecondaryIconGravity();
        if (this.secondaryIcon == null) goto L19;
    L25:
        Drawable[] r05 = getCompoundDrawablesRelative();
        Drawable r32 = r05[0];
        Drawable r42 = r05[1];
        Drawable r06 = r05[2];
        if (isSecondaryIconStart() == false) goto L30;
        if (r32 == this.secondaryIcon) goto L30;
    L37:
        boolean r07 = true;
    L39:
        if (r8 == true) goto L42;
        if (r07 == true) goto L42;
        return;
    L42:
        if (isSecondaryIconStart() == false) goto L46;
        setCompoundDrawablesRelative(this.secondaryIcon, getUpdatedSecondaryIconFor(1), getUpdatedSecondaryIconFor(2), null);
        return;
    L46:
        if (isSecondaryIconEnd() == false) goto L50;
        setCompoundDrawablesRelative(getUpdatedSecondaryIconFor(0), getUpdatedSecondaryIconFor(1), this.secondaryIcon, null);
        return;
    L50:
        if (isSecondaryIconTop() == false) goto L55;
        setCompoundDrawablesRelative(getUpdatedSecondaryIconFor(0), this.secondaryIcon, getUpdatedSecondaryIconFor(2), null);
        return;
    L55:
        return;
    L30:
        if (isSecondaryIconEnd() == false) goto L34;
        if (r06 != this.secondaryIcon) goto L37;
    L34:
        if (isSecondaryIconTop() == true) goto L36;
    L38:
        r07 = false;
        goto L39
    L36:
        if (r42 == this.secondaryIcon) goto L38;
    L19:
        if (this.stopNullSecondaryIconUpdate == false) goto L21;
        return;
    L21:
        if (this.icon == null) goto L25;
        if (areIconsGravitySameAlignment() == false) goto L25;
    }

    private void updateSecondaryIconPosition(int r3, int r4) {
        if (this.secondaryIcon != null) goto L5;
        return;
    L5:
        if (getLayout() != null) goto L8;
        return;
    L8:
        if (isSecondaryIconStart() == false) goto L10;
    L25:
        this.secondaryIconTop = 0;
        if (canUpdateWithoutTextAlignment(this.secondaryIconGravity) == false) goto L29;
        this.secondaryIconLeft = 0;
        updateSecondaryIcon(false);
        return;
    L29:
        int r32 = getIconLeft(r3, this.secondaryIconGravity);
        if (this.secondaryIconLeft == r32) goto L36;
        this.secondaryIconLeft = r32;
        updateSecondaryIcon(false);
        return;
    L36:
        return;
    L10:
        if (isSecondaryIconEnd() == true) goto L25;
        if (isSecondaryIconTop() == false) goto L34;
        this.secondaryIconLeft = 0;
        if (this.secondaryIconGravity != 16) goto L18;
        this.secondaryIconTop = 0;
        updateSecondaryIcon(false);
        return;
    L18:
        int r33 = this.iconSize;
        if (r33 != 0) goto L21;
        r33 = this.secondaryIcon.getIntrinsicHeight();
    L21:
        int r34 = getIconTop(r4, r33);
        if (this.secondaryIconTop == r34) goto L35;
        this.secondaryIconTop = r34;
        updateSecondaryIcon(false);
        return;
    L35:
        return;
    }

    private void validateIconGravity() {
        if (this.icon != null) goto L5;
        return;
    L5:
        if (this.secondaryIcon != null) goto L7;
        return;
    L7:
        if (areIconsGravitySameAlignment() == false) goto L13;
        f$$ExternalSyntheticBUOutline1.m("iconGravity cannot have the same alignment as secondaryIconGravity");
        return;
    }

    private void validateSecondaryIconGravity() {
        if (this.secondaryIcon != null) goto L5;
        return;
    L5:
        if (this.icon != null) goto L7;
        return;
    L7:
        if (areIconsGravitySameAlignment() == false) goto L13;
        f$$ExternalSyntheticBUOutline1.m("secondaryIconGravity cannot have the same alignment as iconGravity");
        return;
    }

    public void addOnCheckedChangeListener(OnCheckedChangeListener r1) {
        this.onCheckedChangeListeners.add(r1);
    }

    public void clearOnCheckedChangeListeners() {
        this.onCheckedChangeListeners.clear();
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public String getA11yClassName() {
        if (TextUtils.isEmpty(this.accessibilityClassName) == true) goto L7;
        return this.accessibilityClassName;
    L7:
        if (isCheckable() == false) goto L9;
        Class r1 = CompoundButton.class;
    L11:
        return r1.getName();
    L9:
        r1 = Button.class;
        goto L11
    }

    public int getAllowedWidthDecrease() {
        return this.allowedWidthDecrease;
    }

    @Override // android.view.View
    public ColorStateList getBackgroundTintList() {
        return getSupportBackgroundTintList();
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return getSupportBackgroundTintMode();
    }

    public int getCornerRadius() {
        if (isUsingOriginalBackground() == true) goto L5;
        return 0;
    L5:
        return this.materialButtonHelper.getCornerRadius();
    }

    public SpringForce getCornerSpringForce() {
        return this.materialButtonHelper.getCornerSpringForce();
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public int getIconGravity() {
        return this.iconGravity;
    }

    public int getIconPadding() {
        return this.iconPadding;
    }

    public int getIconSize() {
        return this.iconSize;
    }

    public ColorStateList getIconTint() {
        return this.iconTint;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.iconTintMode;
    }

    public int getInsetBottom() {
        return this.materialButtonHelper.getInsetBottom();
    }

    public int getInsetLeft() {
        return this.materialButtonHelper.getInsetLeft();
    }

    public int getInsetRight() {
        return this.materialButtonHelper.getInsetRight();
    }

    public int getInsetTop() {
        return this.materialButtonHelper.getInsetTop();
    }

    public ColorStateList getRippleColor() {
        if (isUsingOriginalBackground() == true) goto L5;
        return null;
    L5:
        return this.materialButtonHelper.getRippleColor();
    }

    public Drawable getSecondaryIcon() {
        return this.secondaryIcon;
    }

    public int getSecondaryIconGravity() {
        return this.secondaryIconGravity;
    }

    public ColorStateList getSecondaryIconTint() {
        return this.secondaryIconTint;
    }

    public PorterDuff.Mode getSecondaryIconTintMode() {
        return this.secondaryIconTintMode;
    }

    public ShapeAppearance getShapeAppearance() {
        if (isUsingOriginalBackground() == true) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Attempted to get ShapeAppearance from a MaterialButton which has an overwritten background.");
        return null;
    L5:
        return this.materialButtonHelper.getShapeAppearance();
    }

    @Override // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        if (isUsingOriginalBackground() == true) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
        return null;
    L5:
        return this.materialButtonHelper.getShapeAppearanceModel();
    }

    public ColorStateList getStrokeColor() {
        if (isUsingOriginalBackground() == true) goto L5;
        return null;
    L5:
        return this.materialButtonHelper.getStrokeColor();
    }

    public int getStrokeWidth() {
        if (isUsingOriginalBackground() == true) goto L5;
        return 0;
    L5:
        return this.materialButtonHelper.getStrokeWidth();
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public ColorStateList getSupportBackgroundTintList() {
        if (isUsingOriginalBackground() == false) goto L7;
        return this.materialButtonHelper.getSupportBackgroundTintList();
    L7:
        return super.getSupportBackgroundTintList();
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        if (isUsingOriginalBackground() == false) goto L7;
        return this.materialButtonHelper.getSupportBackgroundTintMode();
    L7:
        return super.getSupportBackgroundTintMode();
    }

    public boolean isCheckable() {
        MaterialButtonHelper r0 = this.materialButtonHelper;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isCheckable() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.checked;
    }

    public boolean isOpticalCenterEnabled() {
        return this.opticalCenterEnabled;
    }

    public boolean isToggleCheckedStateOnClick() {
        return this.materialButtonHelper.isToggleCheckedStateOnClick();
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isUsingOriginalBackground() == false) goto L6;
        MaterialShapeUtils.setParentAbsoluteElevation(this, this.materialButtonHelper.getMaterialShapeDrawable());
        return;
    }

    @Override // android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int r2) {
        int[] r22 = super.onCreateDrawableState(r2 + 2);
        if (isCheckable() == false) goto L6;
        View.mergeDrawableStates(r22, CHECKABLE_STATE_SET);
    L6:
        if (isChecked() == false) goto L8;
        View.mergeDrawableStates(r22, CHECKED_STATE_SET);
    L8:
        return r22;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent r2) {
        super.onInitializeAccessibilityEvent(r2);
        r2.setClassName(getA11yClassName());
        r2.setChecked(isChecked());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        r2.setClassName(getA11yClassName());
        r2.setCheckable(isCheckable());
        r2.setChecked(isChecked());
        r2.setClickable(isClickable());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        updateIconPosition(getMeasuredWidth(), getMeasuredHeight());
        updateSecondaryIconPosition(getMeasuredWidth(), getMeasuredHeight());
        int r12 = getResources().getConfiguration().orientation;
        if (this.orientation == r12) goto L6;
        this.orientation = r12;
        this.originalWidth = -2.1474836E9f;
    L6:
        if (this.originalWidth != (-2.1474836E9f)) goto L15;
        this.originalWidth = getMeasuredWidth();
        if (this.originalLayoutParams != null) goto L15;
        if ((getParent() instanceof MaterialButtonGroup) == false) goto L15;
        if (((MaterialButtonGroup) getParent()).getButtonSizeChange() == null) goto L15;
        this.originalLayoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        LinearLayout.LayoutParams r13 = new LinearLayout.LayoutParams(this.originalLayoutParams);
        r13.width = (int) this.originalWidth;
        setLayoutParams(r13);
    L15:
        if (this.allowedWidthDecrease != Integer.MIN_VALUE) goto L25;
        if (this.icon != null) goto L19;
        int r14 = 0;
    L23:
        this.allowedWidthDecrease = (getMeasuredWidth() - getTextLayoutWidth()) - r14;
        goto L25
    L19:
        int r15 = getIconPadding();
        int r32 = this.iconSize;
        if (r32 != 0) goto L22;
        r32 = this.icon.getIntrinsicWidth();
    L22:
        r14 = r15 + r32;
    L25:
        if (this.originalPaddingStart != Integer.MIN_VALUE) goto L28;
        this.originalPaddingStart = getPaddingStart();
    L28:
        if (this.originalPaddingEnd != Integer.MIN_VALUE) goto L30;
        this.originalPaddingEnd = getPaddingEnd();
    L30:
        this.isInHorizontalButtonGroup = isInHorizontalButtonGroup();
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable r2) {
        if ((r2 instanceof SavedState) == true) goto L6;
        super.onRestoreInstanceState(r2);
        return;
    L6:
        SavedState r22 = (SavedState) r2;
        super.onRestoreInstanceState(r22.getSuperState());
        setChecked(r22.checked);
    }

    @Override // android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState r1 = new SavedState(super.onSaveInstanceState());
        r1.checked = this.checked;
        return r1;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        super.onTextChanged(r1, r2, r3, r4);
        updateIconPosition(getMeasuredWidth(), getMeasuredHeight());
        updateSecondaryIconPosition(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public boolean performClick() {
        if (isEnabled() == true) goto L5;
    L7:
        boolean r0 = false;
    L8:
        boolean r2 = super.performClick();
        if (r0 == false) goto L12;
        if (r2 == true) goto L12;
        playSoundEffect(0);
    L12:
        return r2;
    L5:
        if (this.materialButtonHelper.isToggleCheckedStateOnClick() == false) goto L7;
        toggle();
        r0 = true;
        goto L8
    }

    public void recoverOriginalLayoutParams() {
        LinearLayout.LayoutParams r0 = this.originalLayoutParams;
        if (r0 == null) goto L6;
        setLayoutParams(r0);
        this.originalLayoutParams = null;
        this.originalWidth = -2.1474836E9f;
        return;
    }

    @Override // android.view.View
    public void refreshDrawableState() {
        super.refreshDrawableState();
        if (this.icon == null) goto L8;
        int[] r0 = getDrawableState();
        if (this.icon.setState(r0) == false) goto L9;
        invalidate();
        return;
    L9:
        return;
    }

    public void removeOnCheckedChangeListener(OnCheckedChangeListener r1) {
        this.onCheckedChangeListeners.remove(r1);
    }

    public void setA11yClassName(String r1) {
        this.accessibilityClassName = r1;
    }

    @Override // android.view.View
    public void setBackground(Drawable r1) {
        setBackgroundDrawable(r1);
    }

    @Override // android.view.View
    public void setBackgroundColor(int r2) {
        if (isUsingOriginalBackground() == false) goto L6;
        this.materialButtonHelper.setBackgroundColor(r2);
        return;
    L6:
        super.setBackgroundColor(r2);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundDrawable(Drawable r3) {
        if (isUsingOriginalBackground() == true) goto L5;
        super.setBackgroundDrawable(r3);
        return;
    L5:
        if (r3 == getBackground()) goto L8;
        Log.w(LOG_TAG, "MaterialButton manages its own background to control elevation, shape, color and states. Consider using backgroundTint, shapeAppearance and other attributes where available. A custom background will ignore these attributes and you should consider handling interaction states such as pressed, focused and disabled");
        this.materialButtonHelper.setBackgroundOverwritten();
        super.setBackgroundDrawable(r3);
        return;
    L8:
        getBackground().setState(r3.getState());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundResource(int r2) {
        if (r2 == 0) goto L4;
        Drawable r22 = AppCompatResources.getDrawable(getContext(), r2);
    L5:
        setBackgroundDrawable(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList r1) {
        setSupportBackgroundTintList(r1);
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode r1) {
        setSupportBackgroundTintMode(r1);
    }

    public void setCheckable(boolean r2) {
        if (isUsingOriginalBackground() == false) goto L6;
        this.materialButtonHelper.setCheckable(r2);
        return;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean r1) {
        setCheckedInternal(r1);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablePadding(int r2) {
        if (getCompoundDrawablePadding() == r2) goto L5;
        this.originalWidth = -2.1474836E9f;
    L5:
        super.setCompoundDrawablePadding(r2);
    }

    public void setCornerRadius(int r2) {
        if (isUsingOriginalBackground() == false) goto L6;
        this.materialButtonHelper.setCornerRadius(r2);
        return;
    }

    public void setCornerRadiusResource(int r2) {
        if (isUsingOriginalBackground() == false) goto L6;
        setCornerRadius(getResources().getDimensionPixelSize(r2));
        return;
    }

    public void setCornerSpringForce(SpringForce r1) {
        this.materialButtonHelper.setCornerSpringForce(r1);
    }

    public void setDisplayedWidthDecrease(int r2) {
        this.displayedWidthDecrease = Math.min(r2, this.allowedWidthDecrease);
        updatePaddingsAndSizeForWidthAnimation();
        invalidate();
    }

    @Override // android.view.View
    public void setElevation(float r2) {
        super.setElevation(r2);
        if (isUsingOriginalBackground() == false) goto L6;
        this.materialButtonHelper.getMaterialShapeDrawable().setElevation(r2);
        return;
    }

    public void setIcon(Drawable r2) {
        if (this.icon != r2) goto L5;
        return;
    L5:
        if (maybeRunAfterWidthAnimation(new MaterialButton$$ExternalSyntheticLambda2(this, r2)) == true) goto L10;
        this.originalWidth = -2.1474836E9f;
        this.icon = r2;
        updateIcon(true);
        updateIconPosition(getMeasuredWidth(), getMeasuredHeight());
        return;
    }

    public void setIconGravity(int r2) {
        if (this.iconGravity == r2) goto L6;
        validateIconGravity();
        this.iconGravity = r2;
        updateIconPosition(getMeasuredWidth(), getMeasuredHeight());
        return;
    }

    public void setIconPadding(int r2) {
        if (this.iconPadding == r2) goto L6;
        this.iconPadding = r2;
        setCompoundDrawablePadding(r2);
        return;
    }

    public void setIconResource(int r2) {
        if (r2 == 0) goto L4;
        Drawable r22 = AppCompatResources.getDrawable(getContext(), r2);
    L5:
        setIcon(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    public void setIconSize(int r2) {
        if (r2 >= 0) goto L4;
        f$$ExternalSyntheticBUOutline1.m("iconSize cannot be less than 0");
        return;
    L4:
        if (this.iconSize != r2) goto L6;
        return;
    L6:
        if (maybeRunAfterWidthAnimation(new MaterialButton$$ExternalSyntheticLambda3(this, r2)) == true) goto L13;
        this.originalWidth = -2.1474836E9f;
        this.iconSize = r2;
        updateIcon(true);
        updateSecondaryIcon(true);
        return;
    }

    public void setIconTint(ColorStateList r2) {
        if (this.iconTint == r2) goto L6;
        this.iconTint = r2;
        updateIcon(false);
        return;
    }

    public void setIconTintMode(PorterDuff.Mode r2) {
        if (this.iconTintMode == r2) goto L6;
        this.iconTintMode = r2;
        updateIcon(false);
        return;
    }

    public void setIconTintResource(int r2) {
        setIconTint(AppCompatResources.getColorStateList(getContext(), r2));
    }

    public void setInsetBottom(int r1) {
        this.materialButtonHelper.setInsetBottom(r1);
    }

    public void setInsetLeft(int r1) {
        this.materialButtonHelper.setInsetLeft(r1);
    }

    public void setInsetRight(int r1) {
        this.materialButtonHelper.setInsetRight(r1);
    }

    public void setInsetTop(int r1) {
        this.materialButtonHelper.setInsetTop(r1);
    }

    public void setInternalBackground(Drawable r1) {
        super.setBackgroundDrawable(r1);
    }

    public void setOnPressedChangeListenerInternal(OnPressedChangeListener r1) {
        this.onPressedChangeListenerInternal = r1;
    }

    public void setOpticalCenterEnabled(boolean r2) {
        if (this.opticalCenterEnabled == r2) goto L10;
        this.opticalCenterEnabled = r2;
        MaterialButtonHelper r0 = this.materialButtonHelper;
        if (r2 == false) goto L7;
        r0.setCornerSizeChangeListener(new MaterialButton$$ExternalSyntheticLambda4(this));
    L8:
        post(new MaterialButton$$ExternalSyntheticLambda5(this));
        return;
    L7:
        r0.setCornerSizeChangeListener(null);
        goto L8
    }

    @Override // android.view.View
    public void setPressed(boolean r2) {
        OnPressedChangeListener r0 = this.onPressedChangeListenerInternal;
        if (r0 == null) goto L5;
        r0.onPressedChanged(this, r2);
    L5:
        super.setPressed(r2);
        maybeAnimateSize(false);
    }

    public void setRippleColor(ColorStateList r2) {
        if (isUsingOriginalBackground() == false) goto L6;
        this.materialButtonHelper.setRippleColor(r2);
        return;
    }

    public void setRippleColorResource(int r2) {
        if (isUsingOriginalBackground() == false) goto L6;
        setRippleColor(AppCompatResources.getColorStateList(getContext(), r2));
        return;
    }

    public void setSecondaryIcon(Drawable r2) {
        if (this.secondaryIcon != r2) goto L5;
        return;
    L5:
        if (maybeRunAfterWidthAnimation(new MaterialButton$$ExternalSyntheticLambda0(this, r2)) == true) goto L10;
        this.originalWidth = -2.1474836E9f;
        this.secondaryIcon = r2;
        this.stopNullSecondaryIconUpdate = false;
        updateSecondaryIcon(true);
        updateSecondaryIconPosition(getMeasuredWidth(), getMeasuredHeight());
        return;
    }

    public void setSecondaryIconGravity(int r2) {
        if (this.secondaryIconGravity == r2) goto L6;
        validateSecondaryIconGravity();
        this.secondaryIconGravity = r2;
        updateSecondaryIconPosition(getMeasuredWidth(), getMeasuredHeight());
        return;
    }

    public void setSecondaryIconResource(int r2) {
        if (r2 == 0) goto L4;
        Drawable r22 = AppCompatResources.getDrawable(getContext(), r2);
    L5:
        setSecondaryIcon(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    public void setSecondaryIconTint(ColorStateList r2) {
        if (this.secondaryIconTint == r2) goto L6;
        this.secondaryIconTint = r2;
        updateSecondaryIcon(false);
        return;
    }

    public void setSecondaryIconTintMode(PorterDuff.Mode r2) {
        if (this.secondaryIconTintMode == r2) goto L6;
        this.secondaryIconTintMode = r2;
        updateSecondaryIcon(false);
        return;
    }

    public void setSecondaryIconTintResource(int r2) {
        setSecondaryIconTint(AppCompatResources.getColorStateList(getContext(), r2));
    }

    public void setShapeAppearance(ShapeAppearance r3) {
        if (isUsingOriginalBackground() == true) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Attempted to set ShapeAppearance on a MaterialButton which has an overwritten background.");
        return;
    L5:
        if (this.materialButtonHelper.getCornerSpringForce() == null) goto L7;
    L9:
        this.materialButtonHelper.setShapeAppearance(r3);
        return;
    L7:
        if (r3.isStateful() == false) goto L9;
        this.materialButtonHelper.setCornerSpringForce(createSpringForce());
        goto L9
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel r2) {
        if (isUsingOriginalBackground() == false) goto L6;
        this.materialButtonHelper.setShapeAppearance(r2);
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
    }

    public void setShouldDrawSurfaceColorStroke(boolean r2) {
        if (isUsingOriginalBackground() == false) goto L6;
        this.materialButtonHelper.setShouldDrawSurfaceColorStroke(r2);
        return;
    }

    public void setSizeChange(StateListSizeChange r2) {
        if (this.sizeChange == r2) goto L6;
        this.sizeChange = r2;
        maybeAnimateSize(true);
        return;
    }

    public void setStrokeColor(ColorStateList r2) {
        if (isUsingOriginalBackground() == false) goto L6;
        this.materialButtonHelper.setStrokeColor(r2);
        return;
    }

    public void setStrokeColorResource(int r2) {
        if (isUsingOriginalBackground() == false) goto L6;
        setStrokeColor(AppCompatResources.getColorStateList(getContext(), r2));
        return;
    }

    public void setStrokeWidth(int r2) {
        if (isUsingOriginalBackground() == false) goto L6;
        this.materialButtonHelper.setStrokeWidth(r2);
        return;
    }

    public void setStrokeWidthResource(int r2) {
        if (isUsingOriginalBackground() == false) goto L6;
        setStrokeWidth(getResources().getDimensionPixelSize(r2));
        return;
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public void setSupportBackgroundTintList(ColorStateList r2) {
        if (isUsingOriginalBackground() == false) goto L6;
        this.materialButtonHelper.setSupportBackgroundTintList(r2);
        return;
    L6:
        super.setSupportBackgroundTintList(r2);
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public void setSupportBackgroundTintMode(PorterDuff.Mode r2) {
        if (isUsingOriginalBackground() == false) goto L6;
        this.materialButtonHelper.setSupportBackgroundTintMode(r2);
        return;
    L6:
        super.setSupportBackgroundTintMode(r2);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence r2, TextView.BufferType r3) {
        this.originalWidth = -2.1474836E9f;
        super.setText(r2, r3);
    }

    @Override // android.view.View
    public void setTextAlignment(int r2) {
        super.setTextAlignment(r2);
        updateIconPosition(getMeasuredWidth(), getMeasuredHeight());
        updateSecondaryIconPosition(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    public void setTextAppearance(Context r2, int r3) {
        this.originalWidth = -2.1474836E9f;
        super.setTextAppearance(r2, r3);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    public void setTextSize(int r2, float r3) {
        this.originalWidth = -2.1474836E9f;
        super.setTextSize(r2, r3);
    }

    public void setToggleCheckedStateOnClick(boolean r1) {
        this.materialButtonHelper.setToggleCheckedStateOnClick(r1);
    }

    @Override // android.widget.TextView
    public void setWidth(int r2) {
        this.originalWidth = -2.1474836E9f;
        super.setWidth(r2);
    }

    public void setWidthChangeDirection(WidthChangeDirection r2) {
        if (this.widthChangeDirection == r2) goto L6;
        this.widthChangeDirection = r2;
        maybeAnimateSize(true);
        return;
    }

    public void setWidthChangeMax(int r2) {
        if (this.widthChangeMax == r2) goto L6;
        this.widthChangeMax = r2;
        maybeAnimateSize(true);
        return;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.checked);
    }
}
