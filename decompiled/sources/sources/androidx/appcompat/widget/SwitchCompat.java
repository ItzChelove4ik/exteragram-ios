package androidx.appcompat.widget;

import android.R;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.text.AllCapsTransformationMethod;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.emoji2.text.EmojiCompat;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public abstract class SwitchCompat extends CompoundButton {
    private static final String ACCESSIBILITY_EVENT_CLASS_NAME = "android.widget.Switch";
    private static final int[] CHECKED_STATE_SET = null;
    private static final int MONOSPACE = 3;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private static final int THUMB_ANIMATION_DURATION = 250;
    private static final Property<SwitchCompat, Float> THUMB_POS = null;
    private static final int TOUCH_MODE_DOWN = 1;
    private static final int TOUCH_MODE_DRAGGING = 2;
    private static final int TOUCH_MODE_IDLE = 0;
    private AppCompatEmojiTextHelper mAppCompatEmojiTextHelper;
    private EmojiCompatInitCallback mEmojiCompatInitCallback;
    private boolean mEnforceSwitchWidth;
    private boolean mHasThumbTint;
    private boolean mHasThumbTintMode;
    private boolean mHasTrackTint;
    private boolean mHasTrackTintMode;
    private int mMinFlingVelocity;
    private Layout mOffLayout;
    private Layout mOnLayout;
    ObjectAnimator mPositionAnimator;
    private boolean mShowText;
    private boolean mSplitTrack;
    private int mSwitchBottom;
    private int mSwitchHeight;
    private int mSwitchLeft;
    private int mSwitchMinWidth;
    private int mSwitchPadding;
    private int mSwitchRight;
    private int mSwitchTop;
    private TransformationMethod mSwitchTransformationMethod;
    private int mSwitchWidth;
    private final Rect mTempRect;
    private ColorStateList mTextColors;
    private final AppCompatTextHelper mTextHelper;
    private CharSequence mTextOff;
    private CharSequence mTextOffTransformed;
    private CharSequence mTextOn;
    private CharSequence mTextOnTransformed;
    private final TextPaint mTextPaint;
    private Drawable mThumbDrawable;
    float mThumbPosition;
    private int mThumbTextPadding;
    private ColorStateList mThumbTintList;
    private PorterDuff.Mode mThumbTintMode;
    private int mThumbWidth;
    private int mTouchMode;
    private int mTouchSlop;
    private float mTouchX;
    private float mTouchY;
    private Drawable mTrackDrawable;
    private ColorStateList mTrackTintList;
    private PorterDuff.Mode mTrackTintMode;
    private VelocityTracker mVelocityTracker;

    /* renamed from: androidx.appcompat.widget.SwitchCompat$1, reason: invalid class name */
    public class AnonymousClass1 extends Property<SwitchCompat, Float> {
        public AnonymousClass1(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public Float get2(SwitchCompat r1) {
            return Float.valueOf(r1.mThumbPosition);
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(SwitchCompat r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(SwitchCompat r1, Float r2) {
            r1.setThumbPosition(r2.floatValue());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(SwitchCompat r1, Float r2) {
            set2(r1, r2);
        }
    }

    public static class EmojiCompatInitCallback extends EmojiCompat.InitCallback {
        private final Reference<SwitchCompat> mOuterWeakRef;

        public EmojiCompatInitCallback(SwitchCompat r2) {
            this.mOuterWeakRef = new WeakReference(r2);
        }

        @Override // androidx.emoji2.text.EmojiCompat.InitCallback
        public void onFailed(Throwable r1) {
            SwitchCompat r0 = this.mOuterWeakRef.get();
            if (r0 == null) goto L6;
            r0.onEmojiCompatInitializedForSwitchText();
            return;
        }

        @Override // androidx.emoji2.text.EmojiCompat.InitCallback
        public void onInitialized() {
            SwitchCompat r0 = this.mOuterWeakRef.get();
            if (r0 == null) goto L6;
            r0.onEmojiCompatInitializedForSwitchText();
            return;
        }
    }

    static {
        THUMB_POS = new AnonymousClass1(Float.class, "thumbPos");
        CHECKED_STATE_SET = new int[]{R.attr.state_checked};
    }

    public SwitchCompat(Context r12, AttributeSet r13, int r14) {
        super(r12, r13, r14);
        this.mThumbTintList = null;
        this.mThumbTintMode = null;
        this.mHasThumbTint = false;
        this.mHasThumbTintMode = false;
        this.mTrackTintList = null;
        this.mTrackTintMode = null;
        this.mHasTrackTint = false;
        this.mHasTrackTintMode = false;
        this.mVelocityTracker = VelocityTracker.obtain();
        this.mEnforceSwitchWidth = true;
        this.mTempRect = new Rect();
        ThemeUtils.checkAppCompatTheme(this, getContext());
        TextPaint r3 = new TextPaint(1);
        this.mTextPaint = r3;
        r3.density = getResources().getDisplayMetrics().density;
        TintTypedArray r32 = TintTypedArray.obtainStyledAttributes(r12, r13, R$styleable.SwitchCompat, r14, 0);
        ViewCompat.saveAttributeDataForStyleable(this, r12, R$styleable.SwitchCompat, r13, r32.getWrappedTypeArray(), r14, 0);
        Drawable r11 = r32.getDrawable(R$styleable.SwitchCompat_android_thumb);
        this.mThumbDrawable = r11;
        if (r11 == null) goto L5;
        r11.setCallback(this);
    L5:
        Drawable r112 = r32.getDrawable(R$styleable.SwitchCompat_track);
        this.mTrackDrawable = r112;
        if (r112 == null) goto L8;
        r112.setCallback(this);
    L8:
        setTextOnInternal(r32.getText(R$styleable.SwitchCompat_android_textOn));
        setTextOffInternal(r32.getText(R$styleable.SwitchCompat_android_textOff));
        this.mShowText = r32.getBoolean(R$styleable.SwitchCompat_showText, true);
        this.mThumbTextPadding = r32.getDimensionPixelSize(R$styleable.SwitchCompat_thumbTextPadding, 0);
        this.mSwitchMinWidth = r32.getDimensionPixelSize(R$styleable.SwitchCompat_switchMinWidth, 0);
        this.mSwitchPadding = r32.getDimensionPixelSize(R$styleable.SwitchCompat_switchPadding, 0);
        this.mSplitTrack = r32.getBoolean(R$styleable.SwitchCompat_splitTrack, false);
        ColorStateList r113 = r32.getColorStateList(R$styleable.SwitchCompat_thumbTint);
        if (r113 == null) goto L11;
        this.mThumbTintList = r113;
        this.mHasThumbTint = true;
    L11:
        PorterDuff.Mode r114 = DrawableUtils.parseTintMode(r32.getInt(R$styleable.SwitchCompat_thumbTintMode, -1), null);
        if (this.mThumbTintMode == r114) goto L15;
        this.mThumbTintMode = r114;
        this.mHasThumbTintMode = true;
    L15:
        if (this.mHasThumbTint == false) goto L17;
    L18:
        applyThumbTint();
    L19:
        ColorStateList r115 = r32.getColorStateList(R$styleable.SwitchCompat_trackTint);
        if (r115 == null) goto L22;
        this.mTrackTintList = r115;
        this.mHasTrackTint = true;
    L22:
        PorterDuff.Mode r116 = DrawableUtils.parseTintMode(r32.getInt(R$styleable.SwitchCompat_trackTintMode, -1), null);
        if (this.mTrackTintMode == r116) goto L26;
        this.mTrackTintMode = r116;
        this.mHasTrackTintMode = true;
    L26:
        if (this.mHasTrackTint == false) goto L28;
    L29:
        applyTrackTint();
    L30:
        int r117 = r32.getResourceId(R$styleable.SwitchCompat_switchTextAppearance, 0);
        if (r117 == 0) goto L33;
        setSwitchTextAppearance(r12, r117);
    L33:
        AppCompatTextHelper r118 = new AppCompatTextHelper(this);
        this.mTextHelper = r118;
        r118.loadFromAttributes(r13, r14);
        r32.recycle();
        ViewConfiguration r119 = ViewConfiguration.get(r12);
        this.mTouchSlop = r119.getScaledTouchSlop();
        this.mMinFlingVelocity = r119.getScaledMinimumFlingVelocity();
        getEmojiTextViewHelper().loadFromAttributes(r13, r14);
        refreshDrawableState();
        setChecked(isChecked());
        return;
    L28:
        if (this.mHasTrackTintMode == false) goto L30;
    L17:
        if (this.mHasThumbTintMode == false) goto L19;
        goto L18
    }

    private void animateThumbToCheckedState(boolean r5) {
        if (r5 == false) goto L4;
        float r52 = 1.0f;
    L5:
        ObjectAnimator r53 = ObjectAnimator.ofFloat(this, THUMB_POS, new float[]{r52});
        this.mPositionAnimator = r53;
        r53.setDuration(250);
        this.mPositionAnimator.setAutoCancel(true);
        this.mPositionAnimator.start();
        return;
    L4:
        r52 = 0.0f;
        goto L5
    }

    private void applyThumbTint() {
        Drawable r0 = this.mThumbDrawable;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.mHasThumbTint == false) goto L7;
    L8:
        Drawable r02 = DrawableCompat.wrap(r0).mutate();
        this.mThumbDrawable = r02;
        if (this.mHasThumbTint == false) goto L12;
        DrawableCompat.setTintList(r02, this.mThumbTintList);
    L12:
        if (this.mHasThumbTintMode == false) goto L15;
        DrawableCompat.setTintMode(this.mThumbDrawable, this.mThumbTintMode);
    L15:
        if (this.mThumbDrawable.isStateful() == false) goto L20;
        this.mThumbDrawable.setState(getDrawableState());
        return;
    L20:
        return;
    L7:
        if (this.mHasThumbTintMode == true) goto L8;
    }

    private void applyTrackTint() {
        Drawable r0 = this.mTrackDrawable;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.mHasTrackTint == false) goto L7;
    L8:
        Drawable r02 = DrawableCompat.wrap(r0).mutate();
        this.mTrackDrawable = r02;
        if (this.mHasTrackTint == false) goto L12;
        DrawableCompat.setTintList(r02, this.mTrackTintList);
    L12:
        if (this.mHasTrackTintMode == false) goto L15;
        DrawableCompat.setTintMode(this.mTrackDrawable, this.mTrackTintMode);
    L15:
        if (this.mTrackDrawable.isStateful() == false) goto L20;
        this.mTrackDrawable.setState(getDrawableState());
        return;
    L20:
        return;
    L7:
        if (this.mHasTrackTintMode == true) goto L8;
    }

    private void cancelPositionAnimator() {
        ObjectAnimator r0 = this.mPositionAnimator;
        if (r0 == null) goto L6;
        r0.cancel();
        return;
    }

    private void cancelSuperTouch(MotionEvent r2) {
        MotionEvent r22 = MotionEvent.obtain(r2);
        r22.setAction(3);
        super.onTouchEvent(r22);
        r22.recycle();
    }

    private static float constrain(float r1, float r2, float r3) {
        if (r1 >= r2) goto L6;
        return r2;
    L6:
        if (r1 <= r3) goto L8;
        return r3;
    L8:
        return r1;
    }

    private CharSequence doTransformForOnOffText(CharSequence r3) {
        TransformationMethod r0 = getEmojiTextViewHelper().wrapTransformationMethod(this.mSwitchTransformationMethod);
        if (r0 != null) goto L5;
        return r3;
    L5:
        return r0.getTransformation(r3, this);
    }

    private AppCompatEmojiTextHelper getEmojiTextViewHelper() {
        if (this.mAppCompatEmojiTextHelper != null) goto L6;
        this.mAppCompatEmojiTextHelper = new AppCompatEmojiTextHelper(this);
    L6:
        return this.mAppCompatEmojiTextHelper;
    }

    private boolean getTargetCheckedState() {
        if (this.mThumbPosition <= 0.5f) goto L6;
        return true;
    L6:
        return false;
    }

    private int getThumbOffset() {
        boolean r0 = ViewUtils.isLayoutRtl(this);
        float r1 = this.mThumbPosition;
        if (r0 == false) goto L6;
        r1 = 1.0f - r1;
    L6:
        return (int) ((r1 * getThumbScrollRange()) + 0.5f);
    }

    private int getThumbScrollRange() {
        Drawable r0 = this.mTrackDrawable;
        if (r0 == null) goto L10;
        Rect r1 = this.mTempRect;
        r0.getPadding(r1);
        Drawable r02 = this.mThumbDrawable;
        if (r02 == null) goto L7;
        Rect r03 = DrawableUtils.getOpticalBounds(r02);
    L9:
        return ((((this.mSwitchWidth - this.mThumbWidth) - r1.left) - r1.right) - r03.left) - r03.right;
    L7:
        r03 = DrawableUtils.INSETS_NONE;
        goto L9
    L10:
        return 0;
    }

    private boolean hitThumb(float r8, float r9) {
        if (this.mThumbDrawable != null) goto L5;
        return false;
    L5:
        int r0 = getThumbOffset();
        this.mThumbDrawable.getPadding(this.mTempRect);
        int r2 = this.mSwitchTop;
        int r3 = this.mTouchSlop;
        int r22 = r2 - r3;
        int r4 = (this.mSwitchLeft + r0) - r3;
        int r02 = this.mThumbWidth + r4;
        Rect r5 = this.mTempRect;
        int r03 = ((r02 + r5.left) + r5.right) + r3;
        int r7 = this.mSwitchBottom + r3;
        if (r8 > r4) goto L8;
    L15:
        return false;
    L8:
        if (r8 >= r03) goto L15;
        if (r9 <= r22) goto L15;
        if (r9 >= r7) goto L15;
        return true;
    }

    private Layout makeLayout(CharSequence r9) {
        TextPaint r2 = this.mTextPaint;
        if (r9 == null) goto L6;
        int r8 = (int) Math.ceil(Layout.getDesiredWidth(r9, r2));
    L8:
        return new StaticLayout(r9, r2, r8, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    L6:
        r8 = 0;
        goto L8
    }

    private void setOffStateDescriptionOnRAndAbove() {
        if (Build.VERSION.SDK_INT < 30) goto L9;
        CharSequence r0 = this.mTextOff;
        if (r0 != null) goto L7;
        r0 = getResources().getString(R$string.abc_capital_off);
    L7:
        ViewCompat.setStateDescription(this, r0);
        return;
    }

    private void setOnStateDescriptionOnRAndAbove() {
        if (Build.VERSION.SDK_INT < 30) goto L9;
        CharSequence r0 = this.mTextOn;
        if (r0 != null) goto L7;
        r0 = getResources().getString(R$string.abc_capital_on);
    L7:
        ViewCompat.setStateDescription(this, r0);
        return;
    }

    private void setSwitchTypefaceByIndex(int r2, int r3) {
        if (r2 != 1) goto L5;
        Typeface r22 = Typeface.SANS_SERIF;
    L12:
        setSwitchTypeface(r22, r3);
        return;
    L5:
        if (r2 != 2) goto L7;
        r22 = Typeface.SERIF;
        goto L12
    L7:
        if (r2 == 3) goto L9;
        r22 = null;
        goto L12
    L9:
        r22 = Typeface.MONOSPACE;
        goto L12
    }

    private void setTextOffInternal(CharSequence r1) {
        this.mTextOff = r1;
        this.mTextOffTransformed = doTransformForOnOffText(r1);
        this.mOffLayout = null;
        if (this.mShowText == false) goto L6;
        setupEmojiCompatLoadCallback();
        return;
    }

    private void setTextOnInternal(CharSequence r1) {
        this.mTextOn = r1;
        this.mTextOnTransformed = doTransformForOnOffText(r1);
        this.mOnLayout = null;
        if (this.mShowText == false) goto L6;
        setupEmojiCompatLoadCallback();
        return;
    }

    private void setupEmojiCompatLoadCallback() {
        if (this.mEmojiCompatInitCallback == null) goto L5;
        return;
    L5:
        if (this.mAppCompatEmojiTextHelper.isEnabled() == true) goto L8;
        return;
    L8:
        if (EmojiCompat.isConfigured() == false) goto L15;
        EmojiCompat r0 = EmojiCompat.get();
        int r1 = r0.getLoadState();
        if (r1 == 3) goto L12;
        if (r1 == 0) goto L12;
        return;
    L12:
        EmojiCompatInitCallback r12 = new EmojiCompatInitCallback(this);
        this.mEmojiCompatInitCallback = r12;
        r0.registerInitCallback(r12);
        return;
    }

    private void stopDrag(MotionEvent r7) {
        this.mTouchMode = 0;
        boolean r2 = true;
        if (r7.getAction() == 1) goto L5;
    L7:
        boolean r1 = false;
    L8:
        boolean r3 = isChecked();
        if (r1 == false) goto L22;
        this.mVelocityTracker.computeCurrentVelocity(1000);
        float r12 = this.mVelocityTracker.getXVelocity();
        if (Math.abs(r12) > this.mMinFlingVelocity) goto L13;
        r2 = getTargetCheckedState();
    L23:
        if (r2 == r3) goto L25;
        playSoundEffect(0);
    L25:
        setChecked(r2);
        cancelSuperTouch(r7);
        return;
    L13:
        if (ViewUtils.isLayoutRtl(this) == false) goto L19;
        if (r12 < 0.0f) goto L23;
    L17:
        r2 = false;
        goto L23
    L19:
        if (r12 <= 0.0f) goto L17;
    L22:
        r2 = r3;
        goto L23
    L5:
        if (isEnabled() == false) goto L7;
        r1 = true;
        goto L8
    }

    @Override // android.view.View
    public void draw(Canvas r11) {
        Rect r0 = this.mTempRect;
        int r1 = this.mSwitchLeft;
        int r2 = this.mSwitchTop;
        int r3 = this.mSwitchRight;
        int r4 = this.mSwitchBottom;
        int r5 = getThumbOffset() + r1;
        Drawable r6 = this.mThumbDrawable;
        if (r6 == null) goto L5;
        Rect r62 = DrawableUtils.getOpticalBounds(r6);
    L6:
        Drawable r7 = this.mTrackDrawable;
        if (r7 == null) goto L26;
        r7.getPadding(r0);
        int r72 = r0.left;
        r5 = r5 + r72;
        if (r62 == null) goto L24;
        int r8 = r62.left;
        if (r8 <= r72) goto L13;
        r1 = r1 + (r8 - r72);
    L13:
        int r73 = r62.top;
        int r82 = r0.top;
        if (r73 <= r82) goto L16;
        int r74 = (r73 - r82) + r2;
    L17:
        int r83 = r62.right;
        int r9 = r0.right;
        if (r83 <= r9) goto L20;
        r3 = r3 - (r83 - r9);
    L20:
        int r63 = r62.bottom;
        int r84 = r0.bottom;
        if (r63 <= r84) goto L23;
        int r64 = r4 - (r63 - r84);
    L25:
        this.mTrackDrawable.setBounds(r1, r74, r3, r64);
    L23:
        r64 = r4;
        goto L25
    L16:
        r74 = r2;
        goto L17
    L24:
        r74 = r2;
    L26:
        Drawable r12 = this.mThumbDrawable;
        if (r12 == null) goto L31;
        r12.getPadding(r0);
        int r13 = r5 - r0.left;
        int r52 = (r5 + this.mThumbWidth) + r0.right;
        this.mThumbDrawable.setBounds(r13, r2, r52, r4);
        Drawable r02 = getBackground();
        if (r02 == null) goto L31;
        DrawableCompat.setHotspotBounds(r02, r13, r2, r52, r4);
    L31:
        super.draw(r11);
        return;
    L5:
        r62 = DrawableUtils.INSETS_NONE;
        goto L6
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableHotspotChanged(float r2, float r3) {
        super.drawableHotspotChanged(r2, r3);
        Drawable r0 = this.mThumbDrawable;
        if (r0 == null) goto L5;
        DrawableCompat.setHotspot(r0, r2, r3);
    L5:
        Drawable r1 = this.mTrackDrawable;
        if (r1 == null) goto L9;
        DrawableCompat.setHotspot(r1, r2, r3);
        return;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] r0 = getDrawableState();
        Drawable r1 = this.mThumbDrawable;
        if (r1 != null) goto L5;
    L7:
        boolean r12 = false;
    L8:
        Drawable r2 = this.mTrackDrawable;
        if (r2 != null) goto L11;
    L13:
        if (r12 == false) goto L16;
        invalidate();
        return;
    L16:
        return;
    L11:
        if (r2.isStateful() == false) goto L13;
        r12 = r12 | r2.setState(r0);
        goto L13
    L5:
        if (r1.isStateful() == false) goto L7;
        r12 = r1.setState(r0);
        goto L8
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        if (ViewUtils.isLayoutRtl(this) == false) goto L5;
        int r0 = super.getCompoundPaddingLeft() + this.mSwitchWidth;
        if (TextUtils.isEmpty(getText()) == false) goto L9;
        return r0;
    L9:
        return r0 + this.mSwitchPadding;
    L5:
        return super.getCompoundPaddingLeft();
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingRight() {
        if (ViewUtils.isLayoutRtl(this) == true) goto L5;
        int r0 = super.getCompoundPaddingRight() + this.mSwitchWidth;
        if (TextUtils.isEmpty(getText()) == false) goto L9;
        return r0;
    L9:
        return r0 + this.mSwitchPadding;
    L5:
        return super.getCompoundPaddingRight();
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return TextViewCompat.unwrapCustomSelectionActionModeCallback(super.getCustomSelectionActionModeCallback());
    }

    public boolean getShowText() {
        return this.mShowText;
    }

    public boolean getSplitTrack() {
        return this.mSplitTrack;
    }

    public int getSwitchMinWidth() {
        return this.mSwitchMinWidth;
    }

    public int getSwitchPadding() {
        return this.mSwitchPadding;
    }

    public CharSequence getTextOff() {
        return this.mTextOff;
    }

    public CharSequence getTextOn() {
        return this.mTextOn;
    }

    public Drawable getThumbDrawable() {
        return this.mThumbDrawable;
    }

    public final float getThumbPosition() {
        return this.mThumbPosition;
    }

    public int getThumbTextPadding() {
        return this.mThumbTextPadding;
    }

    public ColorStateList getThumbTintList() {
        return this.mThumbTintList;
    }

    public PorterDuff.Mode getThumbTintMode() {
        return this.mThumbTintMode;
    }

    public Drawable getTrackDrawable() {
        return this.mTrackDrawable;
    }

    public ColorStateList getTrackTintList() {
        return this.mTrackTintList;
    }

    public PorterDuff.Mode getTrackTintMode() {
        return this.mTrackTintMode;
    }

    public boolean isEmojiCompatEnabled() {
        return getEmojiTextViewHelper().isEnabled();
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable r0 = this.mThumbDrawable;
        if (r0 == null) goto L5;
        r0.jumpToCurrentState();
    L5:
        Drawable r02 = this.mTrackDrawable;
        if (r02 == null) goto L8;
        r02.jumpToCurrentState();
    L8:
        ObjectAnimator r03 = this.mPositionAnimator;
        if (r03 != null) goto L11;
        return;
    L11:
        if (r03.isStarted() == false) goto L15;
        this.mPositionAnimator.end();
        this.mPositionAnimator = null;
        return;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int r1) {
        int[] r12 = super.onCreateDrawableState(r1 + 1);
        if (isChecked() == false) goto L5;
        View.mergeDrawableStates(r12, CHECKED_STATE_SET);
    L5:
        return r12;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onDraw(Canvas r10) {
        super.onDraw(r10);
        Rect r0 = this.mTempRect;
        Drawable r1 = this.mTrackDrawable;
        if (r1 == null) goto L5;
        r1.getPadding(r0);
    L6:
        int r2 = this.mSwitchTop;
        int r3 = this.mSwitchBottom;
        int r22 = r2 + r0.top;
        int r32 = r3 - r0.bottom;
        Drawable r4 = this.mThumbDrawable;
        if (r1 != null) goto L9;
    L13:
        int r02 = r10.save();
        if (r4 == null) goto L17;
        r4.draw(r10);
    L17:
        if (getTargetCheckedState() == false) goto L19;
        Layout r12 = this.mOnLayout;
    L20:
        if (r12 == null) goto L29;
        int[] r5 = getDrawableState();
        ColorStateList r6 = this.mTextColors;
        if (r6 == null) goto L24;
        this.mTextPaint.setColor(r6.getColorForState(r5, 0));
    L24:
        this.mTextPaint.drawableState = r5;
        if (r4 == null) goto L27;
        Rect r9 = r4.getBounds();
        int r42 = r9.left + r9.right;
    L28:
        r10.translate((r42 / 2) - (r12.getWidth() / 2), ((r22 + r32) / 2) - (r12.getHeight() / 2));
        r12.draw(r10);
        goto L29
    L27:
        r42 = getWidth();
    L29:
        r10.restoreToCount(r02);
        return;
    L19:
        r12 = this.mOffLayout;
        goto L20
    L9:
        if (this.mSplitTrack == false) goto L12;
        if (r4 == null) goto L12;
        Rect r52 = DrawableUtils.getOpticalBounds(r4);
        r4.copyBounds(r0);
        r0.left += r52.left;
        r0.right -= r52.right;
        int r53 = r10.save();
        r10.clipRect(r0, Region.Op.DIFFERENCE);
        r1.draw(r10);
        r10.restoreToCount(r53);
    L12:
        r1.draw(r10);
        goto L13
    L5:
        r0.setEmpty();
        goto L6
    }

    public void onEmojiCompatInitializedForSwitchText() {
        setTextOnInternal(this.mTextOn);
        setTextOffInternal(this.mTextOff);
        requestLayout();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent r1) {
        super.onInitializeAccessibilityEvent(r1);
        r1.setClassName(ACCESSIBILITY_EVENT_CLASS_NAME);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
        super.onInitializeAccessibilityNodeInfo(r3);
        r3.setClassName(ACCESSIBILITY_EVENT_CLASS_NAME);
        if (Build.VERSION.SDK_INT < 30) goto L5;
        return;
    L5:
        if (isChecked() == false) goto L7;
        CharSequence r2 = this.mTextOn;
    L9:
        if (TextUtils.isEmpty(r2) == true) goto L17;
        CharSequence r0 = r3.getText();
        if (TextUtils.isEmpty(r0) == false) goto L14;
        r3.setText(r2);
        return;
    L14:
        StringBuilder r1 = new StringBuilder();
        r1.append(r0);
        r1.append(' ');
        r1.append(r2);
        r3.setText(r1);
        return;
    L17:
        return;
    L7:
        r2 = this.mTextOff;
        goto L9
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        int r22 = 0;
        if (this.mThumbDrawable == null) goto L9;
        Rect r12 = this.mTempRect;
        Drawable r32 = this.mTrackDrawable;
        if (r32 == null) goto L7;
        r32.getPadding(r12);
    L8:
        Rect r33 = DrawableUtils.getOpticalBounds(this.mThumbDrawable);
        int r42 = Math.max(0, r33.left - r12.left);
        r22 = Math.max(0, r33.right - r12.right);
    L11:
        if (ViewUtils.isLayoutRtl(this) == false) goto L13;
        int r13 = getPaddingLeft() + r42;
        int r34 = ((this.mSwitchWidth + r13) - r42) - r22;
    L14:
        int r23 = getGravity() & 112;
        if (r23 != 16) goto L17;
        int r24 = ((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2;
        int r43 = this.mSwitchHeight;
        int r25 = r24 - (r43 / 2);
    L19:
        int r44 = r43 + r25;
    L22:
        this.mSwitchLeft = r13;
        this.mSwitchTop = r25;
        this.mSwitchBottom = r44;
        this.mSwitchRight = r34;
        return;
    L17:
        if (r23 == 80) goto L20;
        r25 = getPaddingTop();
        r43 = this.mSwitchHeight;
        goto L19
    L20:
        r44 = getHeight() - getPaddingBottom();
        r25 = r44 - this.mSwitchHeight;
        goto L22
    L13:
        r34 = (getWidth() - getPaddingRight()) - r22;
        r13 = ((r34 - this.mSwitchWidth) + r42) + r22;
        goto L14
    L7:
        r12.setEmpty();
        goto L8
    L9:
        r42 = 0;
        goto L11
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int r7, int r8) {
        if (this.mShowText == true) goto L5;
    L10:
        Rect r0 = this.mTempRect;
        Drawable r1 = this.mThumbDrawable;
        int r2 = 0;
        if (r1 == null) goto L13;
        r1.getPadding(r0);
        int r12 = (this.mThumbDrawable.getIntrinsicWidth() - r0.left) - r0.right;
        int r3 = this.mThumbDrawable.getIntrinsicHeight();
    L15:
        if (this.mShowText == false) goto L17;
        int r4 = Math.max(this.mOnLayout.getWidth(), this.mOffLayout.getWidth()) + (this.mThumbTextPadding * 2);
    L18:
        this.mThumbWidth = Math.max(r4, r12);
        Drawable r13 = this.mTrackDrawable;
        if (r13 == null) goto L21;
        r13.getPadding(r0);
        r2 = this.mTrackDrawable.getIntrinsicHeight();
    L22:
        int r14 = r0.left;
        int r02 = r0.right;
        Drawable r42 = this.mThumbDrawable;
        if (r42 == null) goto L25;
        Rect r43 = DrawableUtils.getOpticalBounds(r42);
        r14 = Math.max(r14, r43.left);
        r02 = Math.max(r02, r43.right);
    L25:
        boolean r44 = this.mEnforceSwitchWidth;
        int r5 = this.mSwitchMinWidth;
        if (r44 == false) goto L28;
        r5 = Math.max(r5, ((this.mThumbWidth * 2) + r14) + r02);
    L28:
        int r03 = Math.max(r2, r3);
        this.mSwitchWidth = r5;
        this.mSwitchHeight = r03;
        super.onMeasure(r7, r8);
        if (getMeasuredHeight() >= r03) goto L32;
        setMeasuredDimension(getMeasuredWidthAndState(), r03);
        return;
    L32:
        return;
    L21:
        r0.setEmpty();
        goto L22
    L17:
        r4 = 0;
        goto L18
    L13:
        r12 = 0;
        r3 = 0;
        goto L15
    L5:
        if (this.mOnLayout != null) goto L8;
        this.mOnLayout = makeLayout(this.mTextOnTransformed);
    L8:
        if (this.mOffLayout != null) goto L10;
        this.mOffLayout = makeLayout(this.mTextOffTransformed);
        goto L10
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent r2) {
        super.onPopulateAccessibilityEvent(r2);
        if (isChecked() == false) goto L5;
        CharSequence r1 = this.mTextOn;
    L6:
        if (r1 == null) goto L9;
        r2.getText().add(r1);
        return;
    L9:
        return;
    L5:
        r1 = this.mTextOff;
        goto L6
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent r7) {
        this.mVelocityTracker.addMovement(r7);
        int r0 = r7.getActionMasked();
        if (r0 != 0) goto L5;
        float r02 = r7.getX();
        float r2 = r7.getY();
        if (isEnabled() == true) goto L42;
    L45:
        return super.onTouchEvent(r7);
    L42:
        if (hitThumb(r02, r2) == false) goto L45;
        this.mTouchMode = 1;
        this.mTouchX = r02;
        this.mTouchY = r2;
        goto L45
    L5:
        if (r0 == 1) goto L35;
        if (r0 != 2) goto L8;
        int r03 = this.mTouchMode;
        if (r03 == 1) goto L28;
        if (r03 != 2) goto L45;
        float r72 = r7.getX();
        int r04 = getThumbScrollRange();
        float r22 = r72 - this.mTouchX;
        if (r04 == 0) goto L18;
        float r23 = r22 / r04;
    L22:
        if (ViewUtils.isLayoutRtl(this) == false) goto L24;
        r23 = -r23;
    L24:
        float r05 = constrain(this.mThumbPosition + r23, 0.0f, 1.0f);
        if (r05 == this.mThumbPosition) goto L27;
        this.mTouchX = r72;
        setThumbPosition(r05);
    L27:
        return true;
    L18:
        if (r22 <= 0.0f) goto L20;
        r23 = 1.0f;
        goto L22
    L20:
        r23 = -1.0f;
        goto L22
    L28:
        float r06 = r7.getX();
        float r3 = r7.getY();
        if (Math.abs(r06 - this.mTouchX) <= this.mTouchSlop) goto L31;
    L32:
        this.mTouchMode = 2;
        getParent().requestDisallowInterceptTouchEvent(true);
        this.mTouchX = r06;
        this.mTouchY = r3;
        return true;
    L31:
        if (Math.abs(r3 - this.mTouchY) <= this.mTouchSlop) goto L45;
    L8:
        if (r0 != 3) goto L45;
    L35:
        if (this.mTouchMode != 2) goto L38;
        stopDrag(r7);
        super.onTouchEvent(r7);
        return true;
    L38:
        this.mTouchMode = 0;
        this.mVelocityTracker.clear();
        goto L45
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean r1) {
        super.setAllCaps(r1);
        getEmojiTextViewHelper().setAllCaps(r1);
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean r2) {
        super.setChecked(r2);
        boolean r22 = isChecked();
        if (r22 == false) goto L5;
        setOnStateDescriptionOnRAndAbove();
    L7:
        if (getWindowToken() != null) goto L9;
    L12:
        cancelPositionAnimator();
        if (r22 == false) goto L15;
        float r23 = 1.0f;
    L16:
        setThumbPosition(r23);
        return;
    L15:
        r23 = 0.0f;
        goto L16
    L9:
        if (isLaidOut() == false) goto L12;
        animateThumbToCheckedState(r22);
        return;
    L5:
        setOffStateDescriptionOnRAndAbove();
        goto L7
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback r1) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, r1));
    }

    public void setEmojiCompatEnabled(boolean r2) {
        getEmojiTextViewHelper().setEnabled(r2);
        setTextOnInternal(this.mTextOn);
        setTextOffInternal(this.mTextOff);
        requestLayout();
    }

    public final void setEnforceSwitchWidth(boolean r1) {
        this.mEnforceSwitchWidth = r1;
        invalidate();
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] r2) {
        super.setFilters(getEmojiTextViewHelper().getFilters(r2));
    }

    public void setShowText(boolean r2) {
        if (this.mShowText == r2) goto L8;
        this.mShowText = r2;
        requestLayout();
        if (r2 == false) goto L9;
        setupEmojiCompatLoadCallback();
        return;
    L9:
        return;
    }

    public void setSplitTrack(boolean r1) {
        this.mSplitTrack = r1;
        invalidate();
    }

    public void setSwitchMinWidth(int r1) {
        this.mSwitchMinWidth = r1;
        requestLayout();
    }

    public void setSwitchPadding(int r1) {
        this.mSwitchPadding = r1;
        requestLayout();
    }

    public void setSwitchTextAppearance(Context r4, int r5) {
        TintTypedArray r42 = TintTypedArray.obtainStyledAttributes(r4, r5, R$styleable.TextAppearance);
        ColorStateList r52 = r42.getColorStateList(R$styleable.TextAppearance_android_textColor);
        if (r52 == null) goto L5;
        this.mTextColors = r52;
    L6:
        int r53 = r42.getDimensionPixelSize(R$styleable.TextAppearance_android_textSize, 0);
        if (r53 == 0) goto L11;
        float r54 = r53;
        if (r54 == this.mTextPaint.getTextSize()) goto L11;
        this.mTextPaint.setTextSize(r54);
        requestLayout();
    L11:
        setSwitchTypefaceByIndex(r42.getInt(R$styleable.TextAppearance_android_typeface, -1), r42.getInt(R$styleable.TextAppearance_android_textStyle, -1));
        if (r42.getBoolean(R$styleable.TextAppearance_textAllCaps, false) == false) goto L14;
        this.mSwitchTransformationMethod = new AllCapsTransformationMethod(getContext());
    L15:
        setTextOnInternal(this.mTextOn);
        setTextOffInternal(this.mTextOff);
        r42.recycle();
        return;
    L14:
        this.mSwitchTransformationMethod = null;
        goto L15
    L5:
        this.mTextColors = getTextColors();
        goto L6
    }

    public void setSwitchTypeface(Typeface r2) {
        if (this.mTextPaint.getTypeface() == null) goto L7;
        if (this.mTextPaint.getTypeface().equals(r2) == true) goto L7;
    L9:
        this.mTextPaint.setTypeface(r2);
        requestLayout();
        invalidate();
        return;
    L7:
        if (this.mTextPaint.getTypeface() != null) goto L11;
        if (r2 != null) goto L9;
        return;
    }

    public void setSwitchTypeface(Typeface r4, int r5) {
        float r0 = 0.0f;
        boolean r1 = false;
        if (r5 <= 0) goto L19;
        if (r4 != null) goto L6;
        Typeface r42 = Typeface.defaultFromStyle(r5);
    L7:
        setSwitchTypeface(r42);
        if (r42 == null) goto L10;
        int r43 = r42.getStyle();
    L11:
        int r44 = (~r43) & r5;
        TextPaint r52 = this.mTextPaint;
        if ((r44 & 1) == 0) goto L14;
        r1 = true;
    L14:
        r52.setFakeBoldText(r1);
        TextPaint r3 = this.mTextPaint;
        if ((r44 & 2) == 0) goto L17;
        r0 = -0.25f;
    L17:
        r3.setTextSkewX(r0);
        return;
    L10:
        r43 = 0;
        goto L11
    L6:
        r42 = Typeface.create(r4, r5);
        goto L7
    L19:
        this.mTextPaint.setFakeBoldText(false);
        this.mTextPaint.setTextSkewX(0.0f);
        setSwitchTypeface(r4);
    }

    public void setTextOff(CharSequence r1) {
        setTextOffInternal(r1);
        requestLayout();
        if (isChecked() == true) goto L6;
        setOffStateDescriptionOnRAndAbove();
        return;
    }

    public void setTextOn(CharSequence r1) {
        setTextOnInternal(r1);
        requestLayout();
        if (isChecked() == false) goto L6;
        setOnStateDescriptionOnRAndAbove();
        return;
    }

    public void setThumbDrawable(Drawable r3) {
        Drawable r0 = this.mThumbDrawable;
        if (r0 == null) goto L5;
        r0.setCallback(null);
    L5:
        this.mThumbDrawable = r3;
        if (r3 == null) goto L8;
        r3.setCallback(this);
    L8:
        requestLayout();
    }

    public void setThumbPosition(float r1) {
        this.mThumbPosition = r1;
        invalidate();
    }

    public void setThumbResource(int r2) {
        setThumbDrawable(AppCompatResources.getDrawable(getContext(), r2));
    }

    public void setThumbTextPadding(int r1) {
        this.mThumbTextPadding = r1;
        requestLayout();
    }

    public void setThumbTintList(ColorStateList r1) {
        this.mThumbTintList = r1;
        this.mHasThumbTint = true;
        applyThumbTint();
    }

    public void setThumbTintMode(PorterDuff.Mode r1) {
        this.mThumbTintMode = r1;
        this.mHasThumbTintMode = true;
        applyThumbTint();
    }

    public void setTrackDrawable(Drawable r3) {
        Drawable r0 = this.mTrackDrawable;
        if (r0 == null) goto L5;
        r0.setCallback(null);
    L5:
        this.mTrackDrawable = r3;
        if (r3 == null) goto L8;
        r3.setCallback(this);
    L8:
        requestLayout();
    }

    public void setTrackResource(int r2) {
        setTrackDrawable(AppCompatResources.getDrawable(getContext(), r2));
    }

    public void setTrackTintList(ColorStateList r1) {
        this.mTrackTintList = r1;
        this.mHasTrackTint = true;
        applyTrackTint();
    }

    public void setTrackTintMode(PorterDuff.Mode r1) {
        this.mTrackTintMode = r1;
        this.mHasTrackTintMode = true;
        applyTrackTint();
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
        setChecked(!isChecked());
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (super.verifyDrawable(r2) == false) goto L5;
        return true;
    L5:
        if (r2 != this.mThumbDrawable) goto L7;
        return true;
    L7:
        if (r2 == this.mTrackDrawable) goto L14;
        return false;
    L14:
        return true;
    }
}
