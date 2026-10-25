package com.google.android.material.textfield;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$style;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.DrawableUtils;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.text.BidiFormatter;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import androidx.transition.Fade;
import androidx.transition.TransitionManager;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.StaticLayoutBuilderCompat;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.CornerTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.LinkedHashSet;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class TextInputLayout extends LinearLayout implements ViewTreeObserver.OnGlobalLayoutListener {
    public static final int BOX_BACKGROUND_FILLED = 1;
    public static final int BOX_BACKGROUND_NONE = 0;
    public static final int BOX_BACKGROUND_OUTLINE = 2;
    private static final int DEFAULT_PLACEHOLDER_FADE_DURATION = 87;
    private static final int DEF_STYLE_RES = 0;
    private static final int[][] EDIT_TEXT_BACKGROUND_RIPPLE_STATE = null;
    public static final int END_ICON_CLEAR_TEXT = 2;
    public static final int END_ICON_CUSTOM = -1;
    public static final int END_ICON_DROPDOWN_MENU = 3;
    public static final int END_ICON_NONE = 0;
    public static final int END_ICON_PASSWORD_TOGGLE = 1;
    private static final int INVALID_MAX_LENGTH = -1;
    private static final int LABEL_SCALE_ANIMATION_DURATION = 167;
    private static final String LOG_TAG = "TextInputLayout";
    private static final int NO_WIDTH = -1;
    private static final int PLACEHOLDER_START_DELAY = 67;
    private static final String TAG = "TextInputLayout";
    private ValueAnimator animator;
    private boolean areCornerRadiiRtl;
    private MaterialShapeDrawable boxBackground;
    private boolean boxBackgroundApplied;
    private int boxBackgroundColor;
    private int boxBackgroundMode;
    private int boxCollapsedPaddingTopPx;
    private final int boxLabelCutoutPaddingPx;
    private int boxStrokeColor;
    private int boxStrokeWidthDefaultPx;
    private int boxStrokeWidthFocusedPx;
    private int boxStrokeWidthPx;
    private MaterialShapeDrawable boxUnderlineDefault;
    private MaterialShapeDrawable boxUnderlineFocused;
    final CollapsingTextHelper collapsingTextHelper;
    boolean counterEnabled;
    private int counterMaxLength;
    private int counterOverflowTextAppearance;
    private ColorStateList counterOverflowTextColor;
    private boolean counterOverflowed;
    private int counterTextAppearance;
    private ColorStateList counterTextColor;
    private TextView counterView;
    private ColorStateList cursorColor;
    private ColorStateList cursorErrorColor;
    private int defaultFilledBackgroundColor;
    private ColorStateList defaultHintTextColor;
    private int defaultStrokeColor;
    private int disabledColor;
    private int disabledFilledBackgroundColor;
    EditText editText;
    private final LinkedHashSet<OnEditTextAttachedListener> editTextAttachedListeners;
    private Drawable endDummyDrawable;
    private int endDummyDrawableWidth;
    private final EndCompoundLayout endLayout;
    private boolean expandedHintEnabled;
    private final int extraSpaceBetweenPlaceholderAndHint;
    private StateListDrawable filledDropDownMenuBackground;
    private int focusedFilledBackgroundColor;
    private int focusedStrokeColor;
    private ColorStateList focusedTextColor;
    private boolean globalLayoutListenerAdded;
    private CharSequence hint;
    private boolean hintAnimationEnabled;
    private boolean hintEnabled;
    private boolean hintExpanded;
    private int hoveredFilledBackgroundColor;
    private int hoveredStrokeColor;
    private boolean inDrawableStateChanged;
    private final IndicatorViewController indicatorViewController;
    private final FrameLayout inputFrame;
    private boolean isProvidingHint;
    private LengthCounter lengthCounter;
    private int maxEms;
    private int maxWidth;
    private int minEms;
    private int minWidth;
    private Drawable originalEditTextEndDrawable;
    int originalEditTextMinimumHeight;
    private CharSequence originalHint;
    private MaterialShapeDrawable outlinedDropDownMenuBackground;
    private boolean placeholderEnabled;
    private Fade placeholderFadeIn;
    private Fade placeholderFadeOut;
    private CharSequence placeholderText;
    private int placeholderTextAppearance;
    private ColorStateList placeholderTextColor;
    private TextView placeholderTextView;
    private boolean restoringSavedState;
    private ShapeAppearanceModel shapeAppearanceModel;
    private Drawable startDummyDrawable;
    private int startDummyDrawableWidth;
    private final StartCompoundLayout startLayout;
    private ColorStateList strokeErrorColor;
    private final Rect tmpBoundsRect;
    private final Rect tmpRect;
    private final RectF tmpRectF;
    private Typeface typeface;

    /* renamed from: com.google.android.material.textfield.TextInputLayout$1, reason: invalid class name */
    public class AnonymousClass1 implements TextWatcher {
        int previousLineCount;
        final /* synthetic */ TextInputLayout this$0;
        final /* synthetic */ EditText val$editText;

        public AnonymousClass1(TextInputLayout r1, EditText r2) {
            this.this$0 = r1;
            this.val$editText = r2;
            this.previousLineCount = r2.getLineCount();
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r3) {
            this.this$0.updateLabelState(!TextInputLayout.access$000(r0));
            TextInputLayout r0 = this.this$0;
            if (r0.counterEnabled == false) goto L6;
            r0.updateCounter(r3);
        L6:
            if (TextInputLayout.access$100(this.this$0) == false) goto L8;
            TextInputLayout.access$200(this.this$0, r3);
        L8:
            int r32 = this.val$editText.getLineCount();
            int r02 = this.previousLineCount;
            if (r32 == r02) goto L16;
            if (r32 >= r02) goto L14;
            int r03 = this.val$editText.getMinimumHeight();
            int r1 = this.this$0.originalEditTextMinimumHeight;
            if (r03 == r1) goto L14;
            this.val$editText.setMinimumHeight(r1);
        L14:
            this.previousLineCount = r32;
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: com.google.android.material.textfield.TextInputLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends AccessibilityDelegateCompat {
        final /* synthetic */ TextInputLayout this$0;

        public AnonymousClass2(TextInputLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfoCompat r2) {
            super.onInitializeAccessibilityNodeInfo(r1, r2);
            r2.setVisibleToUser(false);
        }
    }

    /* renamed from: com.google.android.material.textfield.TextInputLayout$3, reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ TextInputLayout this$0;

        public AnonymousClass3(TextInputLayout r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout.access$300(this.this$0).checkEndIcon();
        }
    }

    /* renamed from: com.google.android.material.textfield.TextInputLayout$4, reason: invalid class name */
    public class AnonymousClass4 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ TextInputLayout this$0;

        public AnonymousClass4(TextInputLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r1) {
            this.this$0.collapsingTextHelper.setExpansionFraction(((Float) r1.getAnimatedValue()).floatValue());
        }
    }

    public static class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final TextInputLayout layout;

        public AccessibilityDelegate(TextInputLayout r1) {
            this.layout = r1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r14, AccessibilityNodeInfoCompat r15) {
            super.onInitializeAccessibilityNodeInfo(r14, r15);
            EditText r0 = this.layout.getEditText();
            if (r0 == null) goto L5;
            CharSequence r02 = r0.getText();
        L6:
            CharSequence r1 = this.layout.getHint();
            CharSequence r2 = this.layout.getHelperText();
            CharSequence r3 = this.layout.getError();
            CharSequence r4 = this.layout.getPlaceholderText();
            int r5 = this.layout.getCounterMaxLength();
            CharSequence r6 = this.layout.getCounterOverflowDescription();
            boolean r7 = TextUtils.isEmpty(r02);
            boolean r8 = TextUtils.isEmpty(r1);
            boolean r9 = this.layout.isHintExpanded();
            boolean r10 = TextUtils.isEmpty(r3);
            if (r10 == true) goto L9;
        L12:
            boolean r11 = true;
        L13:
            if (r8 == true) goto L15;
            String r12 = r1.toString();
        L17:
            if (TextUtils.isEmpty(r2) == false) goto L19;
        L24:
            TextInputLayout.access$500(this.layout).setupAccessibilityNodeInfo(r15);
            if (r7 == true) goto L28;
            r15.setText(r02);
        L36:
            if (TextUtils.isEmpty(r12) == false) goto L38;
        L44:
            if (r02 != null) goto L46;
        L48:
            r5 = -1;
        L49:
            r15.setMaxTextLength(r5);
            if (r11 == false) goto L55;
            if (r10 == false) goto L54;
            r3 = r6;
        L54:
            r15.setError(r3);
        L55:
            TextInputLayout.access$300(this.layout).getEndIconDelegate().onInitializeAccessibilityNodeInfo(r14, r15);
            return;
        L46:
            if (r02.length() != r5) goto L48;
        L38:
            if (Build.VERSION.SDK_INT < 26) goto L40;
            r15.setHintText(r12);
        L43:
            r15.setShowingHintText(r7);
            goto L44
        L40:
            if (r7 == true) goto L42;
            r12 = r02 + ", " + r12;
        L42:
            r15.setText(r12);
            goto L43
        L28:
            if (TextUtils.isEmpty(r12) == true) goto L33;
            r15.setText(r12);
            if (r9 == true) goto L36;
            if (r4 == null) goto L36;
            r15.setText(r12 + ", " + r4);
            goto L36
        L33:
            if (r4 == null) goto L36;
            r15.setText(r4);
            goto L36
        L19:
            if (TextInputLayout.access$400(this.layout).helperTextShouldBeShown() == false) goto L24;
            if (TextUtils.isEmpty(r12) == false) goto L23;
            r12 = r2.toString();
            goto L24
        L23:
            r12 = r12 + ", " + r2;
            goto L24
        L15:
            r12 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L17
        L9:
            if (TextUtils.isEmpty(r6) == false) goto L12;
            r11 = false;
            goto L13
        L5:
            r02 = null;
            goto L6
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onPopulateAccessibilityEvent(View r1, AccessibilityEvent r2) {
            super.onPopulateAccessibilityEvent(r1, r2);
            TextInputLayout.access$300(this.layout).getEndIconDelegate().onPopulateAccessibilityEvent(r1, r2);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BoxBackgroundMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EndIconMode {
    }

    public interface LengthCounter {
        int countLength(Editable r1);
    }

    public interface OnEditTextAttachedListener {
        void onEditTextAttached(TextInputLayout r1);
    }

    public interface OnEndIconChangedListener {
        void onEndIconChanged(TextInputLayout r1, int r2);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = null;
        CharSequence error;
        boolean isEndIconChecked;

        /* renamed from: com.google.android.material.textfield.TextInputLayout$SavedState$1, reason: invalid class name */
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
            this.error = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(r1);
            boolean r22 = true;
            if (r1.readInt() == 1) goto L6;
            r22 = false;
        L6:
            this.isEndIconChecked = r22;
        }

        public SavedState(Parcelable r1) {
            super(r1);
        }

        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + this.error + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel r2, int r3) {
            super.writeToParcel(r2, r3);
            TextUtils.writeToParcel(this.error, r2, r3);
            r2.writeInt(this.isEndIconChecked ? 1 : 0);
        }
    }

    public static /* synthetic */ void $r8$lambda$NESokDvisNvrx7LOMwXN0vdIT1k(TextInputLayout r0) {
        r0.editText.requestLayout();
    }

    /* renamed from: $r8$lambda$i0Jcz4Bf4UX4s-NHgXUgOqqaB-I, reason: not valid java name */
    public static /* synthetic */ void m1909$r8$lambda$i0Jcz4Bf4UX4sNHgXUgOqqaBI(TextInputLayout r0, StaticLayout.Builder r1) {
        r1.setBreakStrategy(r0.placeholderTextView.getBreakStrategy());
    }

    public static /* synthetic */ int $r8$lambda$o92w5de0KdW2_dMexjlsVyV6EjQ(Editable r0) {
        if (r0 != null) goto L4;
        return 0;
    L4:
        return r0.length();
    }

    static {
        DEF_STYLE_RES = R.style.Widget_Design_TextInputLayout;
        EDIT_TEXT_BACKGROUND_RIPPLE_STATE = new int[][]{new int[]{android.R.attr.state_pressed}, new int[0]};
    }

    public TextInputLayout(Context r2) {
        this(r2, null);
    }

    public TextInputLayout(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.textInputStyle);
    }

    public TextInputLayout(Context r17, AttributeSet r18, int r19) {
        int r5 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r17, r18, r19, r5), r18, r19);
        this.minEms = -1;
        this.maxEms = -1;
        this.minWidth = -1;
        this.maxWidth = -1;
        this.indicatorViewController = new IndicatorViewController(this);
        this.lengthCounter = new TextInputLayout$$ExternalSyntheticLambda1();
        this.tmpRect = new Rect();
        this.tmpBoundsRect = new Rect();
        this.tmpRectF = new RectF();
        this.editTextAttachedListeners = new LinkedHashSet();
        CollapsingTextHelper r1 = new CollapsingTextHelper(this);
        this.collapsingTextHelper = r1;
        this.globalLayoutListenerAdded = false;
        Context r3 = getContext();
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        FrameLayout r10 = new FrameLayout(r3);
        this.inputFrame = r10;
        r10.setAddStatesFromChildren(true);
        TimeInterpolator r6 = AnimationUtils.LINEAR_INTERPOLATOR;
        r1.setTextSizeInterpolator(r6);
        r1.setPositionInterpolator(r6);
        r1.setCollapsedTextGravity(8388659);
        TintTypedArray r32 = ThemeEnforcement.obtainTintedStyledAttributes(r3, r18, R.styleable.TextInputLayout, r19, r5, new int[]{R.styleable.TextInputLayout_counterTextAppearance, R.styleable.TextInputLayout_counterOverflowTextAppearance, R.styleable.TextInputLayout_errorTextAppearance, R.styleable.TextInputLayout_helperTextTextAppearance, R.styleable.TextInputLayout_hintTextAppearance});
        StartCompoundLayout r62 = new StartCompoundLayout(this, r32);
        this.startLayout = r62;
        this.hintEnabled = r32.getBoolean(R.styleable.TextInputLayout_hintEnabled, true);
        setHint(r32.getText(R.styleable.TextInputLayout_android_hint));
        this.hintAnimationEnabled = r32.getBoolean(R.styleable.TextInputLayout_hintAnimationEnabled, true);
        this.expandedHintEnabled = r32.getBoolean(R.styleable.TextInputLayout_expandedHintEnabled, true);
        if (r32.hasValue(R.styleable.TextInputLayout_android_minEms) == false) goto L6;
        setMinEms(r32.getInt(R.styleable.TextInputLayout_android_minEms, -1));
    L9:
        if (r32.hasValue(R.styleable.TextInputLayout_android_maxEms) == false) goto L12;
        setMaxEms(r32.getInt(R.styleable.TextInputLayout_android_maxEms, -1));
    L14:
        this.shapeAppearanceModel = ShapeAppearanceModel.builder(r3, r18, r19, r5).build();
        this.boxLabelCutoutPaddingPx = r3.getResources().getDimensionPixelOffset(R.dimen.mtrl_textinput_box_label_cutout_padding);
        this.boxCollapsedPaddingTopPx = r32.getDimensionPixelOffset(R.styleable.TextInputLayout_boxCollapsedPaddingTop, 0);
        this.extraSpaceBetweenPlaceholderAndHint = getResources().getDimensionPixelSize(R.dimen.m3_multiline_hint_filled_text_extra_space);
        this.boxStrokeWidthDefaultPx = r32.getDimensionPixelSize(R.styleable.TextInputLayout_boxStrokeWidth, r3.getResources().getDimensionPixelSize(R.dimen.mtrl_textinput_box_stroke_width_default));
        this.boxStrokeWidthFocusedPx = r32.getDimensionPixelSize(R.styleable.TextInputLayout_boxStrokeWidthFocused, r3.getResources().getDimensionPixelSize(R.dimen.mtrl_textinput_box_stroke_width_focused));
        this.boxStrokeWidthPx = this.boxStrokeWidthDefaultPx;
        float r2 = r32.getDimension(R.styleable.TextInputLayout_boxCornerRadiusTopStart, -1.0f);
        float r52 = r32.getDimension(R.styleable.TextInputLayout_boxCornerRadiusTopEnd, -1.0f);
        float r11 = r32.getDimension(R.styleable.TextInputLayout_boxCornerRadiusBottomEnd, -1.0f);
        float r4 = r32.getDimension(R.styleable.TextInputLayout_boxCornerRadiusBottomStart, -1.0f);
        ShapeAppearanceModel.Builder r12 = this.shapeAppearanceModel.toBuilder();
        if (r2 < 0.0f) goto L18;
        r12.setTopLeftCornerSize(r2);
    L18:
        if (r52 < 0.0f) goto L21;
        r12.setTopRightCornerSize(r52);
    L21:
        if (r11 < 0.0f) goto L24;
        r12.setBottomRightCornerSize(r11);
    L24:
        if (r4 < 0.0f) goto L26;
        r12.setBottomLeftCornerSize(r4);
    L26:
        this.shapeAppearanceModel = r12.build();
        ColorStateList r22 = MaterialResources.getColorStateList(r3, r32, R.styleable.TextInputLayout_boxBackgroundColor);
        if (r22 == null) goto L32;
        int r42 = r22.getDefaultColor();
        this.defaultFilledBackgroundColor = r42;
        this.boxBackgroundColor = r42;
        if (r22.isStateful() == false) goto L31;
        this.disabledFilledBackgroundColor = r22.getColorForState(new int[]{-16842910}, -1);
        this.focusedFilledBackgroundColor = r22.getColorForState(new int[]{android.R.attr.state_focused, android.R.attr.state_enabled}, -1);
        this.hoveredFilledBackgroundColor = r22.getColorForState(new int[]{android.R.attr.state_hovered, android.R.attr.state_enabled}, -1);
    L34:
        if (r32.hasValue(R.styleable.TextInputLayout_android_textColorHint) == false) goto L36;
        ColorStateList r23 = r32.getColorStateList(R.styleable.TextInputLayout_android_textColorHint);
        this.focusedTextColor = r23;
        this.defaultHintTextColor = r23;
    L36:
        ColorStateList r24 = MaterialResources.getColorStateList(r3, r32, R.styleable.TextInputLayout_boxStrokeColor);
        this.focusedStrokeColor = r32.getColor(R.styleable.TextInputLayout_boxStrokeColor, 0);
        this.defaultStrokeColor = ContextCompat.getColor(r3, R.color.mtrl_textinput_default_box_stroke_color);
        this.disabledColor = ContextCompat.getColor(r3, R.color.mtrl_textinput_disabled_color);
        this.hoveredStrokeColor = ContextCompat.getColor(r3, R.color.mtrl_textinput_hovered_box_stroke_color);
        if (r24 == null) goto L40;
        setBoxStrokeColorStateList(r24);
    L40:
        if (r32.hasValue(R.styleable.TextInputLayout_boxStrokeErrorColor) == false) goto L43;
        setBoxStrokeErrorColor(MaterialResources.getColorStateList(r3, r32, R.styleable.TextInputLayout_boxStrokeErrorColor));
    L43:
        if (r32.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, -1) == (-1)) goto L45;
        setHintTextAppearance(r32.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, 0));
    L45:
        this.cursorColor = r32.getColorStateList(R.styleable.TextInputLayout_cursorColor);
        this.cursorErrorColor = r32.getColorStateList(R.styleable.TextInputLayout_cursorErrorColor);
        int r13 = r32.getResourceId(R.styleable.TextInputLayout_errorTextAppearance, 0);
        CharSequence r25 = r32.getText(R.styleable.TextInputLayout_errorContentDescription);
        int r43 = r32.getInt(R.styleable.TextInputLayout_errorAccessibilityLiveRegion, 1);
        boolean r53 = r32.getBoolean(R.styleable.TextInputLayout_errorEnabled, false);
        int r112 = r32.getResourceId(R.styleable.TextInputLayout_helperTextTextAppearance, 0);
        boolean r122 = r32.getBoolean(R.styleable.TextInputLayout_helperTextEnabled, false);
        CharSequence r132 = r32.getText(R.styleable.TextInputLayout_helperText);
        int r14 = r32.getResourceId(R.styleable.TextInputLayout_placeholderTextAppearance, 0);
        CharSequence r15 = r32.getText(R.styleable.TextInputLayout_placeholderText);
        boolean r9 = r32.getBoolean(R.styleable.TextInputLayout_counterEnabled, false);
        setCounterMaxLength(r32.getInt(R.styleable.TextInputLayout_counterMaxLength, -1));
        this.counterTextAppearance = r32.getResourceId(R.styleable.TextInputLayout_counterTextAppearance, 0);
        this.counterOverflowTextAppearance = r32.getResourceId(R.styleable.TextInputLayout_counterOverflowTextAppearance, 0);
        setBoxBackgroundMode(r32.getInt(R.styleable.TextInputLayout_boxBackgroundMode, 0));
        setErrorContentDescription(r25);
        setErrorAccessibilityLiveRegion(r43);
        setCounterOverflowTextAppearance(this.counterOverflowTextAppearance);
        setHelperTextTextAppearance(r112);
        setErrorTextAppearance(r13);
        setCounterTextAppearance(this.counterTextAppearance);
        setPlaceholderText(r15);
        setPlaceholderTextAppearance(r14);
        if (r32.hasValue(R.styleable.TextInputLayout_errorTextColor) == false) goto L49;
        setErrorTextColor(r32.getColorStateList(R.styleable.TextInputLayout_errorTextColor));
    L49:
        if (r32.hasValue(R.styleable.TextInputLayout_helperTextTextColor) == false) goto L52;
        setHelperTextColor(r32.getColorStateList(R.styleable.TextInputLayout_helperTextTextColor));
    L52:
        if (r32.hasValue(R.styleable.TextInputLayout_hintTextColor) == false) goto L55;
        setHintTextColor(r32.getColorStateList(R.styleable.TextInputLayout_hintTextColor));
    L55:
        if (r32.hasValue(R.styleable.TextInputLayout_counterTextColor) == false) goto L58;
        setCounterTextColor(r32.getColorStateList(R.styleable.TextInputLayout_counterTextColor));
    L58:
        if (r32.hasValue(R.styleable.TextInputLayout_counterOverflowTextColor) == false) goto L61;
        setCounterOverflowTextColor(r32.getColorStateList(R.styleable.TextInputLayout_counterOverflowTextColor));
    L61:
        if (r32.hasValue(R.styleable.TextInputLayout_placeholderTextColor) == false) goto L63;
        setPlaceholderTextColor(r32.getColorStateList(R.styleable.TextInputLayout_placeholderTextColor));
    L63:
        EndCompoundLayout r16 = new EndCompoundLayout(this, r32);
        this.endLayout = r16;
        boolean r26 = r32.getBoolean(R.styleable.TextInputLayout_android_enabled, true);
        setHintMaxLines(r32.getInt(R.styleable.TextInputLayout_hintMaxLines, 1));
        r32.recycle();
        setImportantForAccessibility(2);
        if (Build.VERSION.SDK_INT < 26) goto L66;
        setImportantForAutofill(1);
    L66:
        r10.addView(r62);
        r10.addView(r16);
        addView(r10);
        setEnabled(r26);
        setHelperTextEnabled(r122);
        setErrorEnabled(r53);
        setCounterEnabled(r9);
        setHelperText(r132);
        return;
    L31:
        this.focusedFilledBackgroundColor = this.defaultFilledBackgroundColor;
        ColorStateList r27 = AppCompatResources.getColorStateList(r3, R.color.mtrl_filled_background_color);
        this.disabledFilledBackgroundColor = r27.getColorForState(new int[]{-16842910}, -1);
        this.hoveredFilledBackgroundColor = r27.getColorForState(new int[]{android.R.attr.state_hovered}, -1);
        goto L34
    L32:
        this.boxBackgroundColor = 0;
        this.defaultFilledBackgroundColor = 0;
        this.disabledFilledBackgroundColor = 0;
        this.focusedFilledBackgroundColor = 0;
        this.hoveredFilledBackgroundColor = 0;
        goto L34
    L12:
        if (r32.hasValue(R.styleable.TextInputLayout_android_maxWidth) == false) goto L14;
        setMaxWidth(r32.getDimensionPixelSize(R.styleable.TextInputLayout_android_maxWidth, -1));
        goto L14
    L6:
        if (r32.hasValue(R.styleable.TextInputLayout_android_minWidth) == false) goto L9;
        setMinWidth(r32.getDimensionPixelSize(R.styleable.TextInputLayout_android_minWidth, -1));
        goto L9
    }

    public static /* synthetic */ boolean access$000(TextInputLayout r0) {
        return r0.restoringSavedState;
    }

    public static /* synthetic */ boolean access$100(TextInputLayout r0) {
        return r0.placeholderEnabled;
    }

    public static /* synthetic */ void access$200(TextInputLayout r0, Editable r1) {
        r0.updatePlaceholderText(r1);
    }

    public static /* synthetic */ EndCompoundLayout access$300(TextInputLayout r0) {
        return r0.endLayout;
    }

    public static /* synthetic */ IndicatorViewController access$400(TextInputLayout r0) {
        return r0.indicatorViewController;
    }

    public static /* synthetic */ StartCompoundLayout access$500(TextInputLayout r0) {
        return r0.startLayout;
    }

    private void addPlaceholderTextView() {
        TextView r0 = this.placeholderTextView;
        if (r0 == null) goto L6;
        this.inputFrame.addView(r0);
        this.placeholderTextView.setVisibility(0);
        return;
    }

    private void adjustFilledEditTextPaddingForLargeFont() {
        if (this.editText != null) goto L5;
        return;
    L5:
        if (this.boxBackgroundMode == 1) goto L8;
        return;
    L8:
        if (isHintTextSingleLine() == true) goto L12;
        EditText r0 = this.editText;
        r0.setPaddingRelative(r0.getPaddingStart(), (int) (this.collapsingTextHelper.getCollapsedTextHeight() + this.extraSpaceBetweenPlaceholderAndHint), this.editText.getPaddingEnd(), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_1_3_padding_bottom));
        return;
    L12:
        if (MaterialResources.isFontScaleAtLeast2_0(getContext()) == false) goto L16;
        EditText r02 = this.editText;
        r02.setPaddingRelative(r02.getPaddingStart(), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_2_0_padding_top), this.editText.getPaddingEnd(), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_2_0_padding_bottom));
        return;
    L16:
        if (MaterialResources.isFontScaleAtLeast1_3(getContext()) == false) goto L20;
        EditText r03 = this.editText;
        r03.setPaddingRelative(r03.getPaddingStart(), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_1_3_padding_top), this.editText.getPaddingEnd(), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_1_3_padding_bottom));
        return;
    }

    private void applyBoxAttributes() {
        MaterialShapeDrawable r0 = this.boxBackground;
        if (r0 != null) goto L5;
        return;
    L5:
        ShapeAppearanceModel r02 = r0.getShapeAppearanceModel();
        ShapeAppearanceModel r1 = this.shapeAppearanceModel;
        if (r02 == r1) goto L9;
        this.boxBackground.setShapeAppearanceModel(r1);
    L9:
        if (canDrawOutlineStroke() == false) goto L11;
        this.boxBackground.setStroke(this.boxStrokeWidthPx, this.boxStrokeColor);
    L11:
        int r03 = calculateBoxBackgroundColor();
        this.boxBackgroundColor = r03;
        this.boxBackground.setFillColor(ColorStateList.valueOf(r03));
        applyBoxUnderlineAttributes();
        updateEditTextBoxBackgroundIfNeeded();
    }

    private void applyBoxUnderlineAttributes() {
        if (this.boxUnderlineDefault != null) goto L5;
        return;
    L5:
        if (this.boxUnderlineFocused != null) goto L8;
        return;
    L8:
        if (canDrawStroke() == false) goto L14;
        MaterialShapeDrawable r0 = this.boxUnderlineDefault;
        if (this.editText.isFocused() == false) goto L12;
        ColorStateList r1 = ColorStateList.valueOf(this.defaultStrokeColor);
    L13:
        r0.setFillColor(r1);
        this.boxUnderlineFocused.setFillColor(ColorStateList.valueOf(this.boxStrokeColor));
        goto L14
    L12:
        r1 = ColorStateList.valueOf(this.boxStrokeColor);
    L14:
        invalidate();
    }

    private void applyCutoutPadding(RectF r3) {
        float r0 = r3.left;
        int r2 = this.boxLabelCutoutPaddingPx;
        r3.left = r0 - r2;
        r3.right += r2;
    }

    private void assignBoxBackgroundByMode() {
        int r0 = this.boxBackgroundMode;
        if (r0 != 0) goto L5;
        this.boxBackground = null;
        this.boxUnderlineDefault = null;
        this.boxUnderlineFocused = null;
        return;
    L5:
        if (r0 != 1) goto L7;
        this.boxBackground = new MaterialShapeDrawable(this.shapeAppearanceModel);
        this.boxUnderlineDefault = new MaterialShapeDrawable();
        this.boxUnderlineFocused = new MaterialShapeDrawable();
        return;
    L7:
        if (r0 != 2) goto L17;
        if (this.hintEnabled == true) goto L11;
    L13:
        this.boxBackground = new MaterialShapeDrawable(this.shapeAppearanceModel);
    L14:
        this.boxUnderlineDefault = null;
        this.boxUnderlineFocused = null;
        return;
    L11:
        if ((this.boxBackground instanceof CutoutDrawable) == true) goto L13;
        this.boxBackground = CutoutDrawable.create(this.shapeAppearanceModel);
        goto L14
    L17:
        throw new IllegalArgumentException(this.boxBackgroundMode + " is illegal; only @BoxBackgroundMode constants are supported.");
    }

    private int calculateBoxBackgroundColor() {
        int r0 = this.boxBackgroundColor;
        if (this.boxBackgroundMode == 1) goto L5;
        return r0;
    L5:
        return MaterialColors.layer(MaterialColors.getColor(this, R.attr.colorSurface, 0), this.boxBackgroundColor);
    }

    private Rect calculateCollapsedTextBounds(Rect r6) {
        if (this.editText == null) goto L14;
        Rect r0 = this.tmpBoundsRect;
        boolean r1 = ViewUtils.isLayoutRtl(this);
        r0.bottom = r6.bottom;
        int r2 = this.boxBackgroundMode;
        if (r2 == 1) goto L12;
        int r3 = r6.left;
        if (r2 == 2) goto L10;
        r0.left = getLabelLeftBoundAlignedWithPrefixAndSuffix(r3, r1);
        r0.top = getPaddingTop();
        r0.right = getLabelRightBoundAlignedWithPrefixAndSuffix(r6.right, r1);
        return r0;
    L10:
        r0.left = r3 + this.editText.getPaddingLeft();
        r0.top = r6.top - calculateLabelMarginTop();
        r0.right = r6.right - this.editText.getPaddingRight();
        return r0;
    L12:
        r0.left = getLabelLeftBoundAlignedWithPrefixAndSuffix(r6.left, r1);
        r0.top = r6.top + this.boxCollapsedPaddingTopPx;
        r0.right = getLabelRightBoundAlignedWithPrefixAndSuffix(r6.right, r1);
        return r0;
    L14:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    private int calculateExpandedLabelBottom(Rect r2, Rect r3, float r4) {
        if (isSingleLineFilledTextField() == false) goto L7;
        return (int) (r3.top + r4);
    L7:
        return r2.bottom - this.editText.getCompoundPaddingBottom();
    }

    private int calculateExpandedLabelTop(Rect r3, float r4) {
        if (isSingleLineFilledTextField() == false) goto L7;
        return (int) (r3.centerY() - (r4 / 2.0f));
    L7:
        if (this.boxBackgroundMode == 0) goto L9;
    L11:
        int r42 = 0;
    L13:
        return (r3.top + this.editText.getCompoundPaddingTop()) - r42;
    L9:
        if (isHintTextSingleLine() == true) goto L11;
        r42 = (int) (this.collapsingTextHelper.getExpandedTextSingleLineHeight() / 2.0f);
        goto L13
    }

    private Rect calculateExpandedTextBounds(Rect r5) {
        if (this.editText == null) goto L10;
        Rect r0 = this.tmpBoundsRect;
        boolean r1 = isHintTextSingleLine();
        CollapsingTextHelper r2 = this.collapsingTextHelper;
        if (r1 == false) goto L7;
        float r12 = r2.getExpandedTextSingleLineHeight();
    L8:
        r0.left = r5.left + this.editText.getCompoundPaddingLeft();
        r0.top = calculateExpandedLabelTop(r5, r12);
        r0.right = r5.right - this.editText.getCompoundPaddingRight();
        r0.bottom = calculateExpandedLabelBottom(r5, r0, r12);
        return r0;
    L7:
        r12 = r2.getExpandedTextFullSingleLineHeight() * this.collapsingTextHelper.getExpandedLineCount();
        goto L8
    L10:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    private int calculateLabelMarginTop() {
        if (this.hintEnabled == true) goto L5;
        return 0;
    L5:
        int r0 = this.boxBackgroundMode;
        if (r0 == 0) goto L17;
        if (r0 == 2) goto L10;
        return 0;
    L10:
        boolean r02 = isHintTextSingleLine();
        CollapsingTextHelper r2 = this.collapsingTextHelper;
        if (r02 == false) goto L15;
        return (int) (r2.getCollapsedTextHeight() / 2.0f);
    L15:
        return Math.max(0, (int) (r2.getCollapsedTextHeight() - (this.collapsingTextHelper.getCollapsedSingleLineHeight() / 2.0f)));
    L17:
        return (int) this.collapsingTextHelper.getCollapsedTextHeight();
    }

    private boolean canDrawOutlineStroke() {
        if (this.boxBackgroundMode == 2) goto L5;
        return false;
    L5:
        if (canDrawStroke() == false) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean canDrawStroke() {
        if (this.boxStrokeWidthPx > (-1)) goto L5;
        return false;
    L5:
        if (this.boxStrokeColor == 0) goto L10;
        return true;
    L10:
        return false;
    }

    private void closeCutout() {
        if (cutoutEnabled() == false) goto L6;
        ((CutoutDrawable) this.boxBackground).removeCutout();
        return;
    }

    private void collapseHint(boolean r2) {
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L8;
        if (r0.isRunning() == false) goto L8;
        this.animator.cancel();
    L8:
        if (r2 == true) goto L10;
    L12:
        this.collapsingTextHelper.setExpansionFraction(1.0f);
    L13:
        this.hintExpanded = false;
        if (cutoutEnabled() == false) goto L16;
        openCutout();
    L16:
        updatePlaceholderText();
        this.startLayout.onHintStateChanged(false);
        this.endLayout.onHintStateChanged(false);
        return;
    L10:
        if (this.hintAnimationEnabled == false) goto L12;
        animateToExpansionFraction(1.0f);
        goto L13
    }

    private Fade createPlaceholderFadeTransition() {
        Fade r0 = new Fade();
        r0.setDuration(MotionUtils.resolveThemeDuration(getContext(), R.attr.motionDurationShort2, 87));
        r0.setInterpolator(MotionUtils.resolveThemeInterpolator(getContext(), R.attr.motionEasingLinearInterpolator, AnimationUtils.LINEAR_INTERPOLATOR));
        return r0;
    }

    private boolean cutoutEnabled() {
        if (this.hintEnabled == true) goto L5;
        return false;
    L5:
        if (TextUtils.isEmpty(this.hint) == false) goto L7;
        return false;
    L7:
        if ((this.boxBackground instanceof CutoutDrawable) == false) goto L13;
        return true;
    L13:
        return false;
    }

    private void dispatchOnEditTextAttached() {
        Iterator<OnEditTextAttachedListener> r0 = this.editTextAttachedListeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().onEditTextAttached(this);
        goto L4
    }

    private void drawBoxUnderline(Canvas r6) {
        if (this.boxUnderlineFocused == null) goto L10;
        MaterialShapeDrawable r0 = this.boxUnderlineDefault;
        if (r0 == null) goto L11;
        r0.draw(r6);
        if (this.editText.isFocused() == false) goto L12;
        Rect r02 = this.boxUnderlineFocused.getBounds();
        Rect r1 = this.boxUnderlineDefault.getBounds();
        float r2 = this.collapsingTextHelper.getExpansionFraction();
        int r3 = r1.centerX();
        r02.left = AnimationUtils.lerp(r3, r1.left, r2);
        r02.right = AnimationUtils.lerp(r3, r1.right, r2);
        this.boxUnderlineFocused.draw(r6);
        return;
    L12:
        return;
    L11:
        return;
    }

    private void drawHint(Canvas r2) {
        if (this.hintEnabled == false) goto L6;
        this.collapsingTextHelper.draw(r2);
        return;
    }

    private void expandHint(boolean r2) {
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L8;
        if (r0.isRunning() == false) goto L8;
        this.animator.cancel();
    L8:
        if (r2 == true) goto L10;
    L12:
        this.collapsingTextHelper.setExpansionFraction(0.0f);
    L14:
        if (cutoutEnabled() == true) goto L16;
    L18:
        this.hintExpanded = true;
        hidePlaceholderText();
        this.startLayout.onHintStateChanged(true);
        this.endLayout.onHintStateChanged(true);
        return;
    L16:
        if (((CutoutDrawable) this.boxBackground).hasCutout() == false) goto L18;
        closeCutout();
        goto L18
    L10:
        if (this.hintAnimationEnabled == false) goto L12;
        animateToExpansionFraction(0.0f);
        goto L14
    }

    private MaterialShapeDrawable getDropDownMaterialShapeDrawable(boolean r5) {
        float r0 = getResources().getDimensionPixelOffset(R.dimen.mtrl_shape_corner_size_small_component);
        if (r5 == false) goto L5;
        float r52 = r0;
    L6:
        EditText r1 = this.editText;
        if ((r1 instanceof MaterialAutoCompleteTextView) == false) goto L9;
        float r12 = ((MaterialAutoCompleteTextView) r1).getPopupElevation();
    L10:
        int r2 = getResources().getDimensionPixelOffset(R.dimen.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        ShapeAppearanceModel r53 = ShapeAppearanceModel.builder().setTopLeftCornerSize(r52).setTopRightCornerSize(r52).setBottomLeftCornerSize(r0).setBottomRightCornerSize(r0).build();
        EditText r02 = this.editText;
        if ((r02 instanceof MaterialAutoCompleteTextView) == false) goto L13;
        ColorStateList r03 = ((MaterialAutoCompleteTextView) r02).getDropDownBackgroundTintList();
    L14:
        MaterialShapeDrawable r4 = MaterialShapeDrawable.createWithElevationOverlay(getContext(), r12, r03);
        r4.setShapeAppearanceModel(r53);
        r4.setPadding(0, r2, 0, r2);
        return r4;
    L13:
        r03 = null;
        goto L14
    L9:
        r12 = getResources().getDimensionPixelOffset(R.dimen.m3_comp_outlined_autocomplete_menu_container_elevation);
        goto L10
    L5:
        r52 = 0.0f;
        goto L6
    }

    private Drawable getEditTextBoxBackground() {
        EditText r0 = this.editText;
        if ((r0 instanceof AutoCompleteTextView) == false) goto L18;
        if (EditTextUtils.isEditable(r0) == true) goto L18;
        int r02 = MaterialColors.getColor(this.editText, R$attr.colorControlHighlight);
        int r1 = this.boxBackgroundMode;
        if (r1 != 2) goto L12;
        return getOutlinedBoxBackgroundWithRipple(getContext(), this.boxBackground, r02, EDIT_TEXT_BACKGROUND_RIPPLE_STATE);
    L12:
        if (r1 == 1) goto L14;
        return null;
    L14:
        return getFilledBoxBackgroundWithRipple(this.boxBackground, this.boxBackgroundColor, r02, EDIT_TEXT_BACKGROUND_RIPPLE_STATE);
    L18:
        return this.boxBackground;
    }

    private static Drawable getFilledBoxBackgroundWithRipple(MaterialShapeDrawable r1, int r2, int r3, int[][] r4) {
        return new RippleDrawable(new ColorStateList(r4, new int[]{MaterialColors.layer(r3, r2, 0.1f), r2}), r1, r1);
    }

    private int getLabelLeftBoundAlignedWithPrefixAndSuffix(int r2, boolean r3) {
        if (r3 == false) goto L4;
    L8:
        if (r3 == true) goto L10;
    L12:
        int r1 = this.editText.getCompoundPaddingLeft();
    L7:
        return r2 + r1;
    L10:
        if (getSuffixText() == null) goto L12;
        r1 = this.endLayout.getSuffixTextEndOffset();
        goto L7
    L4:
        if (getPrefixText() == null) goto L8;
        r1 = this.startLayout.getPrefixTextStartOffset();
        goto L7
    }

    private int getLabelRightBoundAlignedWithPrefixAndSuffix(int r2, boolean r3) {
        if (r3 == false) goto L4;
    L8:
        if (r3 == true) goto L10;
    L12:
        int r1 = this.editText.getCompoundPaddingRight();
    L7:
        return r2 - r1;
    L10:
        if (getPrefixText() == null) goto L12;
        r1 = this.startLayout.getPrefixTextStartOffset();
        goto L7
    L4:
        if (getSuffixText() == null) goto L8;
        r1 = this.endLayout.getSuffixTextEndOffset();
        goto L7
    }

    private Drawable getOrCreateFilledDropDownMenuBackground() {
        if (this.filledDropDownMenuBackground != null) goto L6;
        StateListDrawable r0 = new StateListDrawable();
        this.filledDropDownMenuBackground = r0;
        r0.addState(new int[]{android.R.attr.state_above_anchor}, getOrCreateOutlinedDropDownMenuBackground());
        this.filledDropDownMenuBackground.addState(new int[0], getDropDownMaterialShapeDrawable(false));
    L6:
        return this.filledDropDownMenuBackground;
    }

    private Drawable getOrCreateOutlinedDropDownMenuBackground() {
        if (this.outlinedDropDownMenuBackground != null) goto L6;
        this.outlinedDropDownMenuBackground = getDropDownMaterialShapeDrawable(true);
    L6:
        return this.outlinedDropDownMenuBackground;
    }

    private static Drawable getOutlinedBoxBackgroundWithRipple(Context r4, MaterialShapeDrawable r5, int r6, int[][] r7) {
        int r42 = MaterialColors.getColor(r4, R.attr.colorSurface, "TextInputLayout");
        MaterialShapeDrawable r0 = new MaterialShapeDrawable(r5.getShapeAppearanceModel());
        int r62 = MaterialColors.layer(r6, r42, 0.1f);
        r0.setFillColor(new ColorStateList(r7, new int[]{r62, 0}));
        r0.setTint(r42);
        ColorStateList r63 = new ColorStateList(r7, new int[]{r62, r42});
        MaterialShapeDrawable r43 = new MaterialShapeDrawable(r5.getShapeAppearanceModel());
        r43.setTint(-1);
        return new LayerDrawable(new Drawable[]{new RippleDrawable(r63, r0, r43), r5});
    }

    private void hidePlaceholderText() {
        TextView r0 = this.placeholderTextView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.placeholderEnabled == false) goto L9;
        r0.setText(null);
        TransitionManager.beginDelayedTransition(this.inputFrame, this.placeholderFadeOut);
        this.placeholderTextView.setVisibility(4);
        return;
    }

    private boolean isHintTextSingleLine() {
        if (getHintMaxLines() != 1) goto L5;
        return true;
    L5:
        return false;
    }

    private boolean isOnError() {
        if (shouldShowError() == false) goto L5;
        return true;
    L5:
        if (this.counterView != null) goto L7;
        return false;
    L7:
        if (this.counterOverflowed == true) goto L14;
        return false;
    L14:
        return true;
    }

    private boolean isSingleLineFilledTextField() {
        if (this.boxBackgroundMode == 1) goto L5;
        return false;
    L5:
        if (this.editText.getMinLines() > 1) goto L9;
        return true;
    L9:
        return false;
    }

    private void onApplyBoxBackgroundMode() {
        assignBoxBackgroundByMode();
        updateEditTextBoxBackgroundIfNeeded();
        updateTextInputBoxState();
        updateBoxCollapsedPaddingTop();
        adjustFilledEditTextPaddingForLargeFont();
        if (this.boxBackgroundMode == 0) goto L5;
        updateInputLayoutMargins();
    L5:
        setDropDownMenuBackgroundIfNeeded();
    }

    private void openCutout() {
        if (cutoutEnabled() == false) goto L13;
        RectF r0 = this.tmpRectF;
        this.collapsingTextHelper.getCollapsedTextBottomTextBounds(r0, this.editText.getWidth(), this.editText.getGravity());
        if (r0.width() > 0.0f) goto L8;
        return;
    L8:
        if (r0.height() <= 0.0f) goto L14;
        applyCutoutPadding(r0);
        r0.offset(-getPaddingLeft(), ((-getPaddingTop()) - (r0.height() / 2.0f)) + this.boxStrokeWidthPx);
        r0.top = 0.0f;
        ((CutoutDrawable) this.boxBackground).setCutout(r0);
        return;
    L14:
        return;
    }

    private void recalculateCutout() {
        if (cutoutEnabled() == true) goto L5;
        return;
    L5:
        if (this.hintExpanded == true) goto L9;
        closeCutout();
        openCutout();
        return;
    }

    private static void recursiveSetEnabled(ViewGroup r4, boolean r5) {
        int r0 = r4.getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        View r2 = r4.getChildAt(r1);
        r2.setEnabled(r5);
        if ((r2 instanceof ViewGroup) == false) goto L7;
        recursiveSetEnabled((ViewGroup) r2, r5);
    L7:
        r1 = r1 + 1;
        goto L3
    }

    private void removePlaceholderTextView() {
        TextView r1 = this.placeholderTextView;
        if (r1 == null) goto L6;
        r1.setVisibility(8);
        return;
    }

    private void setDropDownMenuBackgroundIfNeeded() {
        EditText r0 = this.editText;
        if ((r0 instanceof AutoCompleteTextView) == false) goto L17;
        AutoCompleteTextView r02 = (AutoCompleteTextView) r0;
        if (r02.getDropDownBackground() != null) goto L15;
        int r1 = this.boxBackgroundMode;
        if (r1 != 2) goto L12;
        r02.setDropDownBackgroundDrawable(getOrCreateOutlinedDropDownMenuBackground());
        return;
    L12:
        if (r1 != 1) goto L16;
        r02.setDropDownBackgroundDrawable(getOrCreateFilledDropDownMenuBackground());
        return;
    L16:
        return;
    L15:
        return;
    }

    private void setEditText(EditText r5) {
        if (this.editText == null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("We already have an EditText, can only have one");
        return;
    L5:
        if (getEndIconMode() != 3) goto L7;
    L9:
        this.editText = r5;
        int r0 = this.minEms;
        if (r0 == (-1)) goto L12;
        setMinEms(r0);
    L13:
        int r02 = this.maxEms;
        if (r02 == (-1)) goto L16;
        setMaxEms(r02);
    L17:
        this.boxBackgroundApplied = false;
        onApplyBoxBackgroundMode();
        setTextInputAccessibilityDelegate(new AccessibilityDelegate(this));
        this.collapsingTextHelper.setTypefaces(this.editText.getTypeface());
        this.collapsingTextHelper.setExpandedTextSize(this.editText.getTextSize());
        this.collapsingTextHelper.setExpandedLetterSpacing(this.editText.getLetterSpacing());
        int r1 = this.editText.getGravity();
        this.collapsingTextHelper.setCollapsedTextGravity((r1 & (-113)) | 48);
        this.collapsingTextHelper.setExpandedTextGravity(r1);
        this.originalEditTextMinimumHeight = r5.getMinimumHeight();
        this.editText.addTextChangedListener(new AnonymousClass1(this, r5));
        if (this.defaultHintTextColor != null) goto L21;
        this.defaultHintTextColor = this.editText.getHintTextColors();
    L21:
        if (this.hintEnabled == false) goto L27;
        if (TextUtils.isEmpty(this.hint) == false) goto L25;
        CharSequence r12 = this.editText.getHint();
        this.originalHint = r12;
        setHint(r12);
        this.editText.setHint(null);
    L25:
        this.isProvidingHint = true;
    L27:
        if (Build.VERSION.SDK_INT < 29) goto L30;
        updateCursorColor();
    L30:
        if (this.counterView == null) goto L32;
        updateCounter(this.editText.getText());
    L32:
        updateEditTextBackground();
        this.indicatorViewController.adjustIndicatorPadding();
        this.startLayout.bringToFront();
        this.endLayout.bringToFront();
        dispatchOnEditTextAttached();
        this.endLayout.updateSuffixTextViewPadding();
        if (isEnabled() == true) goto L35;
        r5.setEnabled(false);
    L35:
        updateLabelState(false, true);
        return;
    L16:
        setMaxWidth(this.maxWidth);
        goto L17
    L12:
        setMinWidth(this.minWidth);
        goto L13
    L7:
        if ((r5 instanceof TextInputEditText) == true) goto L9;
        Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        goto L9
    }

    private void setHintInternal(CharSequence r2) {
        if (TextUtils.equals(r2, this.hint) == true) goto L8;
        this.hint = r2;
        this.collapsingTextHelper.setText(r2);
        if (this.hintExpanded == true) goto L9;
        openCutout();
        return;
    L9:
        return;
    }

    private void setPlaceholderTextEnabled(boolean r2) {
        if (this.placeholderEnabled != r2) goto L5;
        return;
    L5:
        if (r2 == false) goto L7;
        addPlaceholderTextView();
    L8:
        this.placeholderEnabled = r2;
        return;
    L7:
        removePlaceholderTextView();
        this.placeholderTextView = null;
        goto L8
    }

    private boolean shouldUpdateEndDummyDrawable() {
        if (this.endLayout.isErrorIconVisible() == true) goto L11;
        if (this.endLayout.hasEndIcon() == false) goto L9;
        if (isEndIconVisible() == true) goto L11;
    L9:
        if (this.endLayout.getSuffixText() != null) goto L11;
        return false;
    L11:
        if (this.endLayout.getMeasuredWidth() <= 0) goto L16;
        return true;
    L16:
        return false;
    }

    private boolean shouldUpdateStartDummyDrawable() {
        if (getStartIconDrawable() != null) goto L9;
        if (getPrefixText() != null) goto L7;
        return false;
    L7:
        if (getPrefixTextView().getVisibility() == 0) goto L9;
        return false;
    L9:
        if (this.startLayout.getMeasuredWidth() <= 0) goto L15;
        return true;
    L15:
        return false;
    }

    private void showPlaceholderText() {
        if (this.placeholderTextView != null) goto L5;
        return;
    L5:
        if (this.placeholderEnabled == true) goto L7;
        return;
    L7:
        if (TextUtils.isEmpty(this.placeholderText) == true) goto L12;
        this.placeholderTextView.setText(this.placeholderText);
        TransitionManager.beginDelayedTransition(this.inputFrame, this.placeholderFadeIn);
        this.placeholderTextView.setVisibility(0);
        this.placeholderTextView.bringToFront();
        return;
    }

    private void updateBoxCollapsedPaddingTop() {
        if (this.boxBackgroundMode == 1) goto L5;
        return;
    L5:
        if (MaterialResources.isFontScaleAtLeast2_0(getContext()) == false) goto L9;
        this.boxCollapsedPaddingTopPx = getResources().getDimensionPixelSize(R.dimen.material_font_2_0_box_collapsed_padding_top);
        return;
    L9:
        if (MaterialResources.isFontScaleAtLeast1_3(getContext()) == false) goto L13;
        this.boxCollapsedPaddingTopPx = getResources().getDimensionPixelSize(R.dimen.material_font_1_3_box_collapsed_padding_top);
        return;
    }

    private void updateBoxUnderlineBounds(Rect r6) {
        MaterialShapeDrawable r0 = this.boxUnderlineDefault;
        if (r0 == null) goto L5;
        int r1 = r6.bottom;
        int r2 = r1 - this.boxStrokeWidthDefaultPx;
        r0.setBounds(r6.left, r2, r6.right, r1);
    L5:
        MaterialShapeDrawable r02 = this.boxUnderlineFocused;
        if (r02 == null) goto L9;
        int r12 = r6.bottom;
        int r5 = r12 - this.boxStrokeWidthFocusedPx;
        r02.setBounds(r6.left, r5, r6.right, r12);
        return;
    }

    private void updateCollapsingTextDimens(int r3) {
        this.collapsingTextHelper.updateTextHeights(r3);
        Rect r0 = this.tmpRect;
        DescendantOffsetUtils.getDescendantRect(this, this.editText, r0);
        this.collapsingTextHelper.setCollapsedBounds(calculateCollapsedTextBounds(r0));
        updateInputLayoutMargins();
        adjustFilledEditTextPaddingForLargeFont();
        updateEditTextHeight(r3);
    }

    private void updateCounter() {
        if (this.counterView == null) goto L10;
        EditText r0 = this.editText;
        if (r0 != null) goto L7;
        Editable r02 = null;
    L8:
        updateCounter(r02);
        return;
    L7:
        r02 = r0.getText();
        goto L8
    }

    private static void updateCounterContentDescription(Context r0, TextView r1, int r2, int r3, boolean r4) {
        if (r4 == false) goto L4;
        int r42 = R.string.character_counter_overflowed_content_description;
    L5:
        r1.setContentDescription(r0.getString(r42, new Object[]{Integer.valueOf(r2), Integer.valueOf(r3)}));
        return;
    L4:
        r42 = R.string.character_counter_content_description;
        goto L5
    }

    private void updateCounterTextAppearanceAndColor() {
        TextView r0 = this.counterView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.counterOverflowed == false) goto L7;
        int r1 = this.counterOverflowTextAppearance;
    L8:
        setTextAppearanceCompatWithErrorFallback(r0, r1);
        if (this.counterOverflowed == true) goto L14;
        ColorStateList r02 = this.counterTextColor;
        if (r02 == null) goto L14;
        this.counterView.setTextColor(r02);
    L14:
        if (this.counterOverflowed == false) goto L20;
        ColorStateList r03 = this.counterOverflowTextColor;
        if (r03 == null) goto L21;
        this.counterView.setTextColor(r03);
        return;
    L21:
        return;
    L20:
        return;
    L7:
        r1 = this.counterTextAppearance;
        goto L8
    }

    private void updateCursorColor() {
        ColorStateList r0 = this.cursorColor;
        if (r0 != null) goto L6;
        r0 = MaterialColors.getColorStateListOrNull(getContext(), R$attr.colorControlActivated);
    L6:
        EditText r1 = this.editText;
        if (r1 != null) goto L9;
        return;
    L9:
        if (r1.getTextCursorDrawable() == null) goto L19;
        Drawable r12 = DrawableCompat.wrap(this.editText.getTextCursorDrawable()).mutate();
        if (isOnError() == false) goto L16;
        ColorStateList r3 = this.cursorErrorColor;
        if (r3 == null) goto L16;
        r0 = r3;
    L16:
        r12.setTintList(r0);
        return;
    }

    private void updateEditTextBoxBackground() {
        Drawable r0 = getEditTextBoxBackground();
        this.editText.setBackground(r0);
    }

    private void updateEditTextHeight(int r6) {
        if (this.editText == null) goto L28;
        float r0 = this.collapsingTextHelper.getExpandedTextHeight();
        float r2 = 0.0f;
        if (this.placeholderText == null) goto L21;
        TextPaint r1 = new TextPaint(129);
        r1.set(this.placeholderTextView.getPaint());
        r1.setTextSize(this.placeholderTextView.getTextSize());
        r1.setTypeface(this.placeholderTextView.getTypeface());
        r1.setLetterSpacing(this.placeholderTextView.getLetterSpacing());
        StaticLayoutBuilderCompat r62 = StaticLayoutBuilderCompat.obtain(this.placeholderText, r1, r6);     // Catch: StaticLayoutBuilderCompat.StaticLayoutBuilderCompatException -> L15
        if (getLayoutDirection() != 1) goto L11;
        boolean r12 = true;
    L12:
        StaticLayout r63 = r62.setIsRtl(r12).setIncludePad(true).setLineSpacing(this.placeholderTextView.getLineSpacingExtra(), this.placeholderTextView.getLineSpacingMultiplier()).setStaticLayoutBuilderConfigurer(new TextInputLayout$$ExternalSyntheticLambda2(this)).build();     // Catch: StaticLayoutBuilderCompat.StaticLayoutBuilderCompatException -> L15
        if (this.boxBackgroundMode != 1) goto L17;
        float r13 = (this.collapsingTextHelper.getCollapsedTextHeight() + this.boxCollapsedPaddingTopPx) + this.extraSpaceBetweenPlaceholderAndHint;     // Catch: StaticLayoutBuilderCompat.StaticLayoutBuilderCompatException -> L15
    L18:
        r2 = r63.getHeight() + r13;
        goto L21
    L17:
        r13 = 0.0f;
        goto L18
    L11:
        r12 = false;
    L15:
        e = move-exception;
        Log.e("TextInputLayout", e.getCause().getMessage(), e);
    L21:
        float r64 = Math.max(r0, r2);
        if (this.editText.getMeasuredHeight() >= r64) goto L27;
        this.editText.setMinimumHeight(Math.round(r64));
        return;
    L27:
        return;
    }

    private boolean updateEditTextHeightBasedOnIcon() {
        if (this.editText != null) goto L5;
        return false;
    L5:
        int r0 = Math.max(this.endLayout.getMeasuredHeight(), this.startLayout.getMeasuredHeight());
        if (this.editText.getMeasuredHeight() >= r0) goto L9;
        this.editText.setMinimumHeight(r0);
        return true;
    L9:
        return false;
    }

    private void updateInputLayoutMargins() {
        if (this.boxBackgroundMode == 1) goto L8;
        LinearLayout.LayoutParams r0 = (LinearLayout.LayoutParams) this.inputFrame.getLayoutParams();
        int r1 = calculateLabelMarginTop();
        if (r1 == r0.topMargin) goto L9;
        r0.topMargin = r1;
        this.inputFrame.requestLayout();
        return;
    L9:
        return;
    }

    private void updateLabelState(boolean r6, boolean r7) {
        boolean r0 = isEnabled();
        EditText r1 = this.editText;
        boolean r2 = false;
        if (r1 != null) goto L5;
    L7:
        boolean r12 = false;
    L8:
        EditText r4 = this.editText;
        if (r4 != null) goto L11;
    L13:
        ColorStateList r3 = this.defaultHintTextColor;
        if (r3 == null) goto L16;
        this.collapsingTextHelper.setCollapsedAndExpandedTextColor(r3);
    L16:
        if (r0 == true) goto L22;
        ColorStateList r02 = this.defaultHintTextColor;
        int r32 = this.disabledColor;
        if (r02 == null) goto L20;
        r32 = r02.getColorForState(new int[]{-16842910}, r32);
    L20:
        this.collapsingTextHelper.setCollapsedAndExpandedTextColor(ColorStateList.valueOf(r32));
    L33:
        if (r12 == false) goto L35;
    L45:
        if (r7 == false) goto L47;
    L50:
        collapseHint(r6);
        return;
    L47:
        if (this.hintExpanded == true) goto L50;
        return;
    L35:
        if (this.expandedHintEnabled == false) goto L45;
        if (isEnabled() == false) goto L40;
        if (r2 == true) goto L45;
    L40:
        if (r7 == false) goto L42;
    L43:
        expandHint(r6);
        return;
    L42:
        if (this.hintExpanded == false) goto L43;
        return;
    L22:
        if (shouldShowError() == false) goto L25;
        this.collapsingTextHelper.setCollapsedAndExpandedTextColor(this.indicatorViewController.getErrorViewTextColors());
        goto L33
    L25:
        if (this.counterOverflowed == false) goto L29;
        TextView r03 = this.counterView;
        if (r03 == null) goto L29;
        this.collapsingTextHelper.setCollapsedAndExpandedTextColor(r03.getTextColors());
    L29:
        if (r2 == false) goto L33;
        ColorStateList r04 = this.focusedTextColor;
        if (r04 == null) goto L33;
        this.collapsingTextHelper.setCollapsedTextColor(r04);
        goto L33
    L11:
        if (r4.hasFocus() == false) goto L13;
        r2 = true;
        goto L13
    L5:
        if (TextUtils.isEmpty(r1.getText()) == true) goto L7;
        r12 = true;
        goto L8
    }

    private void updatePlaceholderMeasurementsBasedOnEditText() {
        if (this.placeholderTextView == null) goto L8;
        EditText r0 = this.editText;
        if (r0 == null) goto L9;
        int r02 = r0.getGravity();
        this.placeholderTextView.setGravity(r02);
        this.placeholderTextView.setPadding(this.editText.getCompoundPaddingLeft(), this.editText.getCompoundPaddingTop(), this.editText.getCompoundPaddingRight(), this.editText.getCompoundPaddingBottom());
        return;
    L9:
        return;
    }

    private void updatePlaceholderText() {
        EditText r0 = this.editText;
        if (r0 != null) goto L5;
        Editable r02 = null;
    L6:
        updatePlaceholderText(r02);
        return;
    L5:
        r02 = r0.getText();
        goto L6
    }

    private void updatePlaceholderText(Editable r2) {
        if (this.lengthCounter.countLength(r2) == 0) goto L5;
    L8:
        hidePlaceholderText();
        return;
    L5:
        if (this.hintExpanded == true) goto L8;
        showPlaceholderText();
    }

    private void updateStrokeErrorColor(boolean r6, boolean r7) {
        int r0 = this.strokeErrorColor.getDefaultColor();
        int r1 = this.strokeErrorColor.getColorForState(new int[]{android.R.attr.state_hovered, android.R.attr.state_enabled}, r0);
        int r2 = this.strokeErrorColor.getColorForState(new int[]{android.R.attr.state_activated, android.R.attr.state_enabled}, r0);
        if (r6 == false) goto L6;
        this.boxStrokeColor = r2;
        return;
    L6:
        if (r7 == false) goto L9;
        this.boxStrokeColor = r1;
        return;
    L9:
        this.boxStrokeColor = r0;
    }

    public void addOnEditTextAttachedListener(OnEditTextAttachedListener r2) {
        this.editTextAttachedListeners.add(r2);
        if (this.editText == null) goto L6;
        r2.onEditTextAttached(this);
        return;
    }

    public void addOnEndIconChangedListener(OnEndIconChangedListener r1) {
        this.endLayout.addOnEndIconChangedListener(r1);
    }

    @Override // android.view.ViewGroup
    public void addView(View r2, int r3, ViewGroup.LayoutParams r4) {
        if ((r2 instanceof EditText) == false) goto L6;
        FrameLayout.LayoutParams r32 = new FrameLayout.LayoutParams(r4);
        r32.gravity = (r32.gravity & (-113)) | 16;
        this.inputFrame.addView(r2, r32);
        this.inputFrame.setLayoutParams(r4);
        updateInputLayoutMargins();
        setEditText((EditText) r2);
        return;
    L6:
        super.addView(r2, r3, r4);
    }

    public void animateToExpansionFraction(float r5) {
        if (this.collapsingTextHelper.getExpansionFraction() != r5) goto L6;
        return;
    L6:
        if (this.animator != null) goto L8;
        ValueAnimator r0 = new ValueAnimator();
        this.animator = r0;
        r0.setInterpolator(MotionUtils.resolveThemeInterpolator(getContext(), R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        this.animator.setDuration(MotionUtils.resolveThemeDuration(getContext(), R.attr.motionDurationMedium4, 167));
        this.animator.addUpdateListener(new AnonymousClass4(this));
    L8:
        this.animator.setFloatValues(new float[]{this.collapsingTextHelper.getExpansionFraction(), r5});
        this.animator.start();
    }

    public void clearOnEditTextAttachedListeners() {
        this.editTextAttachedListeners.clear();
    }

    public void clearOnEndIconChangedListeners() {
        this.endLayout.clearOnEndIconChangedListeners();
    }

    public boolean cutoutIsOpen() {
        if (cutoutEnabled() == true) goto L5;
        return false;
    L5:
        if (((CutoutDrawable) this.boxBackground).hasCutout() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideAutofillStructure(ViewStructure r5, int r6) {
        EditText r0 = this.editText;
        if (r0 != null) goto L6;
        super.dispatchProvideAutofillStructure(r5, r6);
        return;
    L6:
        int r2 = 0;
        if (this.originalHint == null) goto L15;
        boolean r1 = this.isProvidingHint;
        this.isProvidingHint = false;
        CharSequence r02 = r0.getHint();
        this.editText.setHint(this.originalHint);
        super.dispatchProvideAutofillStructure(r5, r6);     // Catch: Throwable -> L12
        this.editText.setHint(r02);
        this.isProvidingHint = r1;
        return;
    L12:
        th = move-exception;
        this.editText.setHint(r02);
        this.isProvidingHint = r1;
        throw th;
    L15:
        r5.setAutofillId(getAutofillId());
        onProvideAutofillStructure(r5, r6);
        onProvideAutofillVirtualStructure(r5, r6);
        r5.setChildCount(this.inputFrame.getChildCount());
    L17:
        if (r2 >= this.inputFrame.getChildCount()) goto L22;
        View r03 = this.inputFrame.getChildAt(r2);
        ViewStructure r12 = r5.newChild(r2);
        r03.dispatchProvideAutofillStructure(r12, r6);
        if (r03 != this.editText) goto L21;
        r12.setHint(getHint());
    L21:
        r2 = r2 + 1;
        goto L17
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> r2) {
        this.restoringSavedState = true;
        super.dispatchRestoreInstanceState(r2);
        this.restoringSavedState = false;
    }

    @Override // android.view.View
    public void draw(Canvas r1) {
        super.draw(r1);
        drawHint(r1);
        drawBoxUnderline(r1);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        if (this.inDrawableStateChanged == false) goto L5;
        return;
    L5:
        boolean r0 = true;
        this.inDrawableStateChanged = true;
        super.drawableStateChanged();
        int[] r1 = getDrawableState();
        CollapsingTextHelper r2 = this.collapsingTextHelper;
        if (r2 == null) goto L8;
        boolean r12 = r2.setState(r1);
    L10:
        if (this.editText != null) goto L12;
    L18:
        updateEditTextBackground();
        updateTextInputBoxState();
        if (r12 == false) goto L21;
        invalidate();
    L21:
        this.inDrawableStateChanged = false;
        return;
    L12:
        if (isLaidOut() == true) goto L14;
    L16:
        r0 = false;
    L17:
        updateLabelState(r0);
        goto L18
    L14:
        if (isEnabled() == false) goto L16;
    L8:
        r12 = false;
        goto L10
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText r0 = this.editText;
        if (r0 == null) goto L7;
        return (r0.getBaseline() + getPaddingTop()) + calculateLabelMarginTop();
    L7:
        return super.getBaseline();
    }

    public MaterialShapeDrawable getBoxBackground() {
        int r0 = this.boxBackgroundMode;
        if (r0 == 1) goto L10;
        if (r0 == 2) goto L10;
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    L10:
        return this.boxBackground;
    }

    public int getBoxBackgroundColor() {
        return this.boxBackgroundColor;
    }

    public int getBoxBackgroundMode() {
        return this.boxBackgroundMode;
    }

    public int getBoxCollapsedPaddingTop() {
        return this.boxCollapsedPaddingTopPx;
    }

    public float getBoxCornerRadiusBottomEnd() {
        boolean r0 = ViewUtils.isLayoutRtl(this);
        ShapeAppearanceModel r1 = this.shapeAppearanceModel;
        if (r0 == false) goto L7;
        return r1.getBottomLeftCornerSize().getCornerSize(this.tmpRectF);
    L7:
        return r1.getBottomRightCornerSize().getCornerSize(this.tmpRectF);
    }

    public float getBoxCornerRadiusBottomStart() {
        boolean r0 = ViewUtils.isLayoutRtl(this);
        ShapeAppearanceModel r1 = this.shapeAppearanceModel;
        if (r0 == false) goto L7;
        return r1.getBottomRightCornerSize().getCornerSize(this.tmpRectF);
    L7:
        return r1.getBottomLeftCornerSize().getCornerSize(this.tmpRectF);
    }

    public float getBoxCornerRadiusTopEnd() {
        boolean r0 = ViewUtils.isLayoutRtl(this);
        ShapeAppearanceModel r1 = this.shapeAppearanceModel;
        if (r0 == false) goto L7;
        return r1.getTopLeftCornerSize().getCornerSize(this.tmpRectF);
    L7:
        return r1.getTopRightCornerSize().getCornerSize(this.tmpRectF);
    }

    public float getBoxCornerRadiusTopStart() {
        boolean r0 = ViewUtils.isLayoutRtl(this);
        ShapeAppearanceModel r1 = this.shapeAppearanceModel;
        if (r0 == false) goto L7;
        return r1.getTopRightCornerSize().getCornerSize(this.tmpRectF);
    L7:
        return r1.getTopLeftCornerSize().getCornerSize(this.tmpRectF);
    }

    public int getBoxStrokeColor() {
        return this.focusedStrokeColor;
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.strokeErrorColor;
    }

    public int getBoxStrokeWidth() {
        return this.boxStrokeWidthDefaultPx;
    }

    public int getBoxStrokeWidthFocused() {
        return this.boxStrokeWidthFocusedPx;
    }

    public int getCounterMaxLength() {
        return this.counterMaxLength;
    }

    public CharSequence getCounterOverflowDescription() {
        if (this.counterEnabled == true) goto L5;
        return null;
    L5:
        if (this.counterOverflowed == false) goto L12;
        TextView r1 = this.counterView;
        if (r1 != null) goto L9;
        return null;
    L9:
        return r1.getContentDescription();
    L12:
        return null;
    }

    public ColorStateList getCounterOverflowTextColor() {
        return this.counterOverflowTextColor;
    }

    public ColorStateList getCounterTextColor() {
        return this.counterTextColor;
    }

    public ColorStateList getCursorColor() {
        return this.cursorColor;
    }

    public ColorStateList getCursorErrorColor() {
        return this.cursorErrorColor;
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.defaultHintTextColor;
    }

    public EditText getEditText() {
        return this.editText;
    }

    public CharSequence getEndIconContentDescription() {
        return this.endLayout.getEndIconContentDescription();
    }

    public Drawable getEndIconDrawable() {
        return this.endLayout.getEndIconDrawable();
    }

    public int getEndIconMinSize() {
        return this.endLayout.getEndIconMinSize();
    }

    public int getEndIconMode() {
        return this.endLayout.getEndIconMode();
    }

    public ImageView.ScaleType getEndIconScaleType() {
        return this.endLayout.getEndIconScaleType();
    }

    public CheckableImageButton getEndIconView() {
        return this.endLayout.getEndIconView();
    }

    public CharSequence getError() {
        if (this.indicatorViewController.isErrorEnabled() == true) goto L5;
        return null;
    L5:
        return this.indicatorViewController.getErrorText();
    }

    public int getErrorAccessibilityLiveRegion() {
        return this.indicatorViewController.getErrorAccessibilityLiveRegion();
    }

    public CharSequence getErrorContentDescription() {
        return this.indicatorViewController.getErrorContentDescription();
    }

    public int getErrorCurrentTextColors() {
        return this.indicatorViewController.getErrorViewCurrentTextColor();
    }

    public Drawable getErrorIconDrawable() {
        return this.endLayout.getErrorIconDrawable();
    }

    public CharSequence getHelperText() {
        if (this.indicatorViewController.isHelperTextEnabled() == true) goto L5;
        return null;
    L5:
        return this.indicatorViewController.getHelperText();
    }

    public int getHelperTextCurrentTextColor() {
        return this.indicatorViewController.getHelperTextViewCurrentTextColor();
    }

    public CharSequence getHint() {
        if (this.hintEnabled == true) goto L5;
        return null;
    L5:
        return this.hint;
    }

    public final float getHintCollapsedTextHeight() {
        return this.collapsingTextHelper.getCollapsedTextHeight();
    }

    public final int getHintCurrentCollapsedTextColor() {
        return this.collapsingTextHelper.getCurrentCollapsedTextColor();
    }

    public int getHintMaxLines() {
        return this.collapsingTextHelper.getExpandedMaxLines();
    }

    public ColorStateList getHintTextColor() {
        return this.focusedTextColor;
    }

    public LengthCounter getLengthCounter() {
        return this.lengthCounter;
    }

    public int getMaxEms() {
        return this.maxEms;
    }

    public int getMaxWidth() {
        return this.maxWidth;
    }

    public int getMinEms() {
        return this.minEms;
    }

    public int getMinWidth() {
        return this.minWidth;
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.endLayout.getPasswordVisibilityToggleContentDescription();
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.endLayout.getPasswordVisibilityToggleDrawable();
    }

    public CharSequence getPlaceholderText() {
        if (this.placeholderEnabled == true) goto L5;
        return null;
    L5:
        return this.placeholderText;
    }

    public int getPlaceholderTextAppearance() {
        return this.placeholderTextAppearance;
    }

    public ColorStateList getPlaceholderTextColor() {
        return this.placeholderTextColor;
    }

    public CharSequence getPrefixText() {
        return this.startLayout.getPrefixText();
    }

    public ColorStateList getPrefixTextColor() {
        return this.startLayout.getPrefixTextColor();
    }

    public TextView getPrefixTextView() {
        return this.startLayout.getPrefixTextView();
    }

    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.shapeAppearanceModel;
    }

    public CharSequence getStartIconContentDescription() {
        return this.startLayout.getStartIconContentDescription();
    }

    public Drawable getStartIconDrawable() {
        return this.startLayout.getStartIconDrawable();
    }

    public int getStartIconMinSize() {
        return this.startLayout.getStartIconMinSize();
    }

    public ImageView.ScaleType getStartIconScaleType() {
        return this.startLayout.getStartIconScaleType();
    }

    public CharSequence getSuffixText() {
        return this.endLayout.getSuffixText();
    }

    public ColorStateList getSuffixTextColor() {
        return this.endLayout.getSuffixTextColor();
    }

    public TextView getSuffixTextView() {
        return this.endLayout.getSuffixTextView();
    }

    public Typeface getTypeface() {
        return this.typeface;
    }

    public boolean isCounterEnabled() {
        return this.counterEnabled;
    }

    public boolean isEndIconCheckable() {
        return this.endLayout.isEndIconCheckable();
    }

    public boolean isEndIconVisible() {
        return this.endLayout.isEndIconVisible();
    }

    public boolean isErrorEnabled() {
        return this.indicatorViewController.isErrorEnabled();
    }

    public boolean isExpandedHintEnabled() {
        return this.expandedHintEnabled;
    }

    public final boolean isHelperTextDisplayed() {
        return this.indicatorViewController.helperTextIsDisplayed();
    }

    public boolean isHelperTextEnabled() {
        return this.indicatorViewController.isHelperTextEnabled();
    }

    public boolean isHintAnimationEnabled() {
        return this.hintAnimationEnabled;
    }

    public boolean isHintEnabled() {
        return this.hintEnabled;
    }

    public final boolean isHintExpanded() {
        return this.hintExpanded;
    }

    @Deprecated
    public boolean isPasswordVisibilityToggleEnabled() {
        return this.endLayout.isPasswordVisibilityToggleEnabled();
    }

    public boolean isProvidingHint() {
        return this.isProvidingHint;
    }

    public boolean isStartIconCheckable() {
        return this.startLayout.isStartIconCheckable();
    }

    public boolean isStartIconVisible() {
        return this.startLayout.isStartIconVisible();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration r1) {
        super.onConfigurationChanged(r1);
        this.collapsingTextHelper.maybeUpdateFontWeightAdjustment(r1);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        this.endLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        this.globalLayoutListenerAdded = false;
        boolean r0 = updateEditTextHeightBasedOnIcon();
        boolean r1 = updateDummyDrawables();
        if (r0 == true) goto L7;
        if (r1 == true) goto L7;
        return;
    L7:
        this.editText.post(new TextInputLayout$$ExternalSyntheticLambda0(this));
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        EditText r12 = this.editText;
        if (r12 == null) goto L12;
        Rect r22 = this.tmpRect;
        DescendantOffsetUtils.getDescendantRect(this, r12, r22);
        updateBoxUnderlineBounds(r22);
        if (this.hintEnabled == false) goto L13;
        this.collapsingTextHelper.setExpandedTextSize(this.editText.getTextSize());
        int r13 = this.editText.getGravity();
        this.collapsingTextHelper.setCollapsedTextGravity((r13 & (-113)) | 48);
        this.collapsingTextHelper.setExpandedTextGravity(r13);
        this.collapsingTextHelper.setCollapsedBounds(calculateCollapsedTextBounds(r22));
        this.collapsingTextHelper.setExpandedBounds(calculateExpandedTextBounds(r22));
        this.collapsingTextHelper.recalculate();
        if (cutoutEnabled() == true) goto L9;
        return;
    L9:
        if (this.hintExpanded == true) goto L15;
        openCutout();
        return;
    L15:
        return;
    L13:
        return;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        if (this.globalLayoutListenerAdded == true) goto L5;
        this.endLayout.getViewTreeObserver().addOnGlobalLayoutListener(this);
        this.globalLayoutListenerAdded = true;
    L5:
        updatePlaceholderMeasurementsBasedOnEditText();
        this.endLayout.updateSuffixTextViewPadding();
        if (isHintTextSingleLine() == true) goto L9;
        updateCollapsingTextDimens((this.editText.getMeasuredWidth() - this.editText.getCompoundPaddingLeft()) - this.editText.getCompoundPaddingRight());
        return;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable r2) {
        if ((r2 instanceof SavedState) == true) goto L6;
        super.onRestoreInstanceState(r2);
        return;
    L6:
        SavedState r22 = (SavedState) r2;
        super.onRestoreInstanceState(r22.getSuperState());
        setError(r22.error);
        if (r22.isEndIconChecked == false) goto L9;
        post(new AnonymousClass3(this));
    L9:
        requestLayout();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onRtlPropertiesChanged(int r10) {
        super.onRtlPropertiesChanged(r10);
        boolean r0 = true;
        if (r10 == 1) goto L7;
        r0 = false;
    L7:
        if (r0 == this.areCornerRadiiRtl) goto L10;
        float r102 = this.shapeAppearanceModel.getTopLeftCornerSize().getCornerSize(this.tmpRectF);
        float r1 = this.shapeAppearanceModel.getTopRightCornerSize().getCornerSize(this.tmpRectF);
        float r2 = this.shapeAppearanceModel.getBottomLeftCornerSize().getCornerSize(this.tmpRectF);
        float r3 = this.shapeAppearanceModel.getBottomRightCornerSize().getCornerSize(this.tmpRectF);
        CornerTreatment r4 = this.shapeAppearanceModel.getTopLeftCorner();
        CornerTreatment r5 = this.shapeAppearanceModel.getTopRightCorner();
        CornerTreatment r6 = this.shapeAppearanceModel.getBottomLeftCorner();
        CornerTreatment r7 = this.shapeAppearanceModel.getBottomRightCorner();
        ShapeAppearanceModel r103 = ShapeAppearanceModel.builder().setTopLeftCorner(r5).setTopRightCorner(r4).setBottomLeftCorner(r7).setBottomRightCorner(r6).setTopLeftCornerSize(r1).setTopRightCornerSize(r102).setBottomLeftCornerSize(r3).setBottomRightCornerSize(r2).build();
        this.areCornerRadiiRtl = r0;
        setShapeAppearanceModel(r103);
        return;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState r1 = new SavedState(super.onSaveInstanceState());
        if (shouldShowError() == false) goto L5;
        r1.error = getError();
    L5:
        r1.isEndIconChecked = this.endLayout.isEndIconChecked();
        return r1;
    }

    @Deprecated
    public void passwordVisibilityToggleRequested(boolean r1) {
        this.endLayout.togglePasswordVisibilityToggle(r1);
    }

    public void refreshEndIconDrawableState() {
        this.endLayout.refreshEndIconDrawableState();
    }

    public void refreshErrorIconDrawableState() {
        this.endLayout.refreshErrorIconDrawableState();
    }

    public void refreshStartIconDrawableState() {
        this.startLayout.refreshStartIconDrawableState();
    }

    public void removeOnEditTextAttachedListener(OnEditTextAttachedListener r1) {
        this.editTextAttachedListeners.remove(r1);
    }

    public void removeOnEndIconChangedListener(OnEndIconChangedListener r1) {
        this.endLayout.removeOnEndIconChangedListener(r1);
    }

    public void setBoxBackgroundColor(int r2) {
        if (this.boxBackgroundColor == r2) goto L6;
        this.boxBackgroundColor = r2;
        this.defaultFilledBackgroundColor = r2;
        this.focusedFilledBackgroundColor = r2;
        this.hoveredFilledBackgroundColor = r2;
        applyBoxAttributes();
        return;
    }

    public void setBoxBackgroundColorResource(int r2) {
        setBoxBackgroundColor(ContextCompat.getColor(getContext(), r2));
    }

    public void setBoxBackgroundColorStateList(ColorStateList r4) {
        int r0 = r4.getDefaultColor();
        this.defaultFilledBackgroundColor = r0;
        this.boxBackgroundColor = r0;
        this.disabledFilledBackgroundColor = r4.getColorForState(new int[]{-16842910}, -1);
        this.focusedFilledBackgroundColor = r4.getColorForState(new int[]{android.R.attr.state_focused, android.R.attr.state_enabled}, -1);
        this.hoveredFilledBackgroundColor = r4.getColorForState(new int[]{android.R.attr.state_hovered, android.R.attr.state_enabled}, -1);
        applyBoxAttributes();
    }

    public void setBoxBackgroundMode(int r2) {
        if (r2 == this.boxBackgroundMode) goto L10;
        this.boxBackgroundMode = r2;
        if (this.editText == null) goto L9;
        onApplyBoxBackgroundMode();
        return;
    L9:
        return;
    }

    public void setBoxCollapsedPaddingTop(int r1) {
        this.boxCollapsedPaddingTopPx = r1;
    }

    public void setBoxCornerFamily(int r3) {
        this.shapeAppearanceModel = this.shapeAppearanceModel.toBuilder().setTopLeftCorner(r3, this.shapeAppearanceModel.getTopLeftCornerSize()).setTopRightCorner(r3, this.shapeAppearanceModel.getTopRightCornerSize()).setBottomLeftCorner(r3, this.shapeAppearanceModel.getBottomLeftCornerSize()).setBottomRightCorner(r3, this.shapeAppearanceModel.getBottomRightCornerSize()).build();
        applyBoxAttributes();
    }

    public void setBoxCornerRadii(float r3, float r4, float r5, float r6) {
        boolean r0 = ViewUtils.isLayoutRtl(this);
        this.areCornerRadiiRtl = r0;
        if (r0 == false) goto L5;
        float r1 = r4;
    L6:
        if (r0 == true) goto L9;
        r3 = r4;
    L9:
        if (r0 == false) goto L11;
        float r42 = r6;
    L12:
        if (r0 == true) goto L15;
        r5 = r6;
    L15:
        MaterialShapeDrawable r62 = this.boxBackground;
        if (r62 != null) goto L18;
    L27:
        this.shapeAppearanceModel = this.shapeAppearanceModel.toBuilder().setTopLeftCornerSize(r1).setTopRightCornerSize(r3).setBottomLeftCornerSize(r42).setBottomRightCornerSize(r5).build();
        applyBoxAttributes();
        return;
    L18:
        if (r62.getTopLeftCornerResolvedSize() != r1) goto L27;
        if (this.boxBackground.getTopRightCornerResolvedSize() != r3) goto L27;
        if (this.boxBackground.getBottomLeftCornerResolvedSize() != r42) goto L27;
        if (this.boxBackground.getBottomRightCornerResolvedSize() != r5) goto L27;
        return;
    L11:
        r42 = r5;
        goto L12
    L5:
        r1 = r3;
        goto L6
    }

    public void setBoxCornerRadiiResources(int r2, int r3, int r4, int r5) {
        setBoxCornerRadii(getContext().getResources().getDimension(r2), getContext().getResources().getDimension(r3), getContext().getResources().getDimension(r5), getContext().getResources().getDimension(r4));
    }

    public void setBoxStrokeColor(int r2) {
        if (this.focusedStrokeColor == r2) goto L6;
        this.focusedStrokeColor = r2;
        updateTextInputBoxState();
        return;
    }

    public void setBoxStrokeColorStateList(ColorStateList r4) {
        if (r4.isStateful() == false) goto L6;
        this.defaultStrokeColor = r4.getDefaultColor();
        this.disabledColor = r4.getColorForState(new int[]{-16842910}, -1);
        this.hoveredStrokeColor = r4.getColorForState(new int[]{android.R.attr.state_hovered, android.R.attr.state_enabled}, -1);
        this.focusedStrokeColor = r4.getColorForState(new int[]{android.R.attr.state_focused, android.R.attr.state_enabled}, -1);
    L8:
        updateTextInputBoxState();
        return;
    L6:
        if (this.focusedStrokeColor == r4.getDefaultColor()) goto L8;
        this.focusedStrokeColor = r4.getDefaultColor();
        goto L8
    }

    public void setBoxStrokeErrorColor(ColorStateList r2) {
        if (this.strokeErrorColor == r2) goto L6;
        this.strokeErrorColor = r2;
        updateTextInputBoxState();
        return;
    }

    public void setBoxStrokeWidth(int r1) {
        this.boxStrokeWidthDefaultPx = r1;
        updateTextInputBoxState();
    }

    public void setBoxStrokeWidthFocused(int r1) {
        this.boxStrokeWidthFocusedPx = r1;
        updateTextInputBoxState();
    }

    public void setBoxStrokeWidthFocusedResource(int r2) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(r2));
    }

    public void setBoxStrokeWidthResource(int r2) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(r2));
    }

    public void setCounterEnabled(boolean r4) {
        if (this.counterEnabled != r4) goto L5;
        return;
    L5:
        if (r4 == false) goto L10;
        AppCompatTextView r1 = new AppCompatTextView(getContext());
        this.counterView = r1;
        r1.setId(R.id.textinput_counter);
        Typeface r12 = this.typeface;
        if (r12 == null) goto L9;
        this.counterView.setTypeface(r12);
    L9:
        this.counterView.setMaxLines(1);
        this.indicatorViewController.addIndicator(this.counterView, 2);
        ((ViewGroup.MarginLayoutParams) this.counterView.getLayoutParams()).setMarginStart(getResources().getDimensionPixelOffset(R.dimen.mtrl_textinput_counter_margin_start));
        updateCounterTextAppearanceAndColor();
        updateCounter();
    L11:
        this.counterEnabled = r4;
        return;
    L10:
        this.indicatorViewController.removeIndicator(this.counterView, 2);
        this.counterView = null;
        goto L11
    }

    public void setCounterMaxLength(int r2) {
        if (this.counterMaxLength == r2) goto L11;
        if (r2 <= 0) goto L6;
        this.counterMaxLength = r2;
    L8:
        if (this.counterEnabled == false) goto L12;
        updateCounter();
        return;
    L12:
        return;
    L6:
        this.counterMaxLength = -1;
        goto L8
    }

    public void setCounterOverflowTextAppearance(int r2) {
        if (this.counterOverflowTextAppearance == r2) goto L6;
        this.counterOverflowTextAppearance = r2;
        updateCounterTextAppearanceAndColor();
        return;
    }

    public void setCounterOverflowTextColor(ColorStateList r2) {
        if (this.counterOverflowTextColor == r2) goto L6;
        this.counterOverflowTextColor = r2;
        updateCounterTextAppearanceAndColor();
        return;
    }

    public void setCounterTextAppearance(int r2) {
        if (this.counterTextAppearance == r2) goto L6;
        this.counterTextAppearance = r2;
        updateCounterTextAppearanceAndColor();
        return;
    }

    public void setCounterTextColor(ColorStateList r2) {
        if (this.counterTextColor == r2) goto L6;
        this.counterTextColor = r2;
        updateCounterTextAppearanceAndColor();
        return;
    }

    public void setCursorColor(ColorStateList r2) {
        if (this.cursorColor == r2) goto L6;
        this.cursorColor = r2;
        updateCursorColor();
        return;
    }

    public void setCursorErrorColor(ColorStateList r2) {
        if (this.cursorErrorColor == r2) goto L8;
        this.cursorErrorColor = r2;
        if (isOnError() == false) goto L9;
        updateCursorColor();
        return;
    L9:
        return;
    }

    public void setDefaultHintTextColor(ColorStateList r1) {
        this.defaultHintTextColor = r1;
        this.focusedTextColor = r1;
        if (this.editText == null) goto L6;
        updateLabelState(false);
        return;
    }

    @Override // android.view.View
    public void setEnabled(boolean r1) {
        recursiveSetEnabled(this, r1);
        super.setEnabled(r1);
    }

    public void setEndIconActivated(boolean r1) {
        this.endLayout.setEndIconActivated(r1);
    }

    public void setEndIconCheckable(boolean r1) {
        this.endLayout.setEndIconCheckable(r1);
    }

    public void setEndIconContentDescription(int r1) {
        this.endLayout.setEndIconContentDescription(r1);
    }

    public void setEndIconContentDescription(CharSequence r1) {
        this.endLayout.setEndIconContentDescription(r1);
    }

    public void setEndIconDrawable(int r1) {
        this.endLayout.setEndIconDrawable(r1);
    }

    public void setEndIconDrawable(Drawable r1) {
        this.endLayout.setEndIconDrawable(r1);
    }

    public void setEndIconMinSize(int r1) {
        this.endLayout.setEndIconMinSize(r1);
    }

    public void setEndIconMode(int r1) {
        this.endLayout.setEndIconMode(r1);
    }

    public void setEndIconOnClickListener(View.OnClickListener r1) {
        this.endLayout.setEndIconOnClickListener(r1);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener r1) {
        this.endLayout.setEndIconOnLongClickListener(r1);
    }

    public void setEndIconScaleType(ImageView.ScaleType r1) {
        this.endLayout.setEndIconScaleType(r1);
    }

    public void setEndIconTintList(ColorStateList r1) {
        this.endLayout.setEndIconTintList(r1);
    }

    public void setEndIconTintMode(PorterDuff.Mode r1) {
        this.endLayout.setEndIconTintMode(r1);
    }

    public void setEndIconVisible(boolean r1) {
        this.endLayout.setEndIconVisible(r1);
    }

    public void setError(CharSequence r2) {
        if (this.indicatorViewController.isErrorEnabled() == false) goto L5;
    L8:
        boolean r0 = TextUtils.isEmpty(r2);
        IndicatorViewController r1 = this.indicatorViewController;
        if (r0 == true) goto L12;
        r1.showError(r2);
        return;
    L12:
        r1.hideError();
        return;
    L5:
        if (TextUtils.isEmpty(r2) == false) goto L7;
        return;
    L7:
        setErrorEnabled(true);
        goto L8
    }

    public void setErrorAccessibilityLiveRegion(int r1) {
        this.indicatorViewController.setErrorAccessibilityLiveRegion(r1);
    }

    public void setErrorContentDescription(CharSequence r1) {
        this.indicatorViewController.setErrorContentDescription(r1);
    }

    public void setErrorEnabled(boolean r1) {
        this.indicatorViewController.setErrorEnabled(r1);
    }

    public void setErrorIconDrawable(int r1) {
        this.endLayout.setErrorIconDrawable(r1);
    }

    public void setErrorIconDrawable(Drawable r1) {
        this.endLayout.setErrorIconDrawable(r1);
    }

    public void setErrorIconOnClickListener(View.OnClickListener r1) {
        this.endLayout.setErrorIconOnClickListener(r1);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener r1) {
        this.endLayout.setErrorIconOnLongClickListener(r1);
    }

    public void setErrorIconTintList(ColorStateList r1) {
        this.endLayout.setErrorIconTintList(r1);
    }

    public void setErrorIconTintMode(PorterDuff.Mode r1) {
        this.endLayout.setErrorIconTintMode(r1);
    }

    public void setErrorTextAppearance(int r1) {
        this.indicatorViewController.setErrorTextAppearance(r1);
    }

    public void setErrorTextColor(ColorStateList r1) {
        this.indicatorViewController.setErrorViewTextColor(r1);
    }

    public void setExpandedHintEnabled(boolean r2) {
        if (this.expandedHintEnabled == r2) goto L6;
        this.expandedHintEnabled = r2;
        updateLabelState(false);
        return;
    }

    public void setHelperText(CharSequence r2) {
        if (TextUtils.isEmpty(r2) == false) goto L9;
        if (isHelperTextEnabled() == false) goto L13;
        setHelperTextEnabled(false);
        return;
    L13:
        return;
    L9:
        if (isHelperTextEnabled() == true) goto L11;
        setHelperTextEnabled(true);
    L11:
        this.indicatorViewController.showHelper(r2);
    }

    public void setHelperTextColor(ColorStateList r1) {
        this.indicatorViewController.setHelperTextViewTextColor(r1);
    }

    public void setHelperTextEnabled(boolean r1) {
        this.indicatorViewController.setHelperTextEnabled(r1);
    }

    public void setHelperTextTextAppearance(int r1) {
        this.indicatorViewController.setHelperTextAppearance(r1);
    }

    public void setHint(int r2) {
        if (r2 == 0) goto L4;
        CharSequence r22 = getResources().getText(r2);
    L5:
        setHint(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    public void setHint(CharSequence r2) {
        if (this.hintEnabled == false) goto L6;
        setHintInternal(r2);
        sendAccessibilityEvent(2048);
        return;
    }

    public void setHintAnimationEnabled(boolean r1) {
        this.hintAnimationEnabled = r1;
    }

    public void setHintEnabled(boolean r3) {
        if (r3 == this.hintEnabled) goto L23;
        this.hintEnabled = r3;
        if (r3 == true) goto L12;
        this.isProvidingHint = false;
        if (TextUtils.isEmpty(this.hint) == false) goto L9;
    L11:
        setHintInternal(null);
    L20:
        if (this.editText == null) goto L24;
        updateInputLayoutMargins();
        return;
    L24:
        return;
    L9:
        if (TextUtils.isEmpty(this.editText.getHint()) == false) goto L11;
        this.editText.setHint(this.hint);
        goto L11
    L12:
        CharSequence r32 = this.editText.getHint();
        if (TextUtils.isEmpty(r32) == false) goto L15;
    L18:
        this.isProvidingHint = true;
        goto L20
    L15:
        if (TextUtils.isEmpty(this.hint) == false) goto L17;
        setHint(r32);
    L17:
        this.editText.setHint(null);
        goto L18
    }

    public void setHintMaxLines(int r2) {
        this.collapsingTextHelper.setCollapsedMaxLines(r2);
        this.collapsingTextHelper.setExpandedMaxLines(r2);
        requestLayout();
    }

    public void setHintTextAppearance(int r2) {
        this.collapsingTextHelper.setCollapsedTextAppearance(r2);
        this.focusedTextColor = this.collapsingTextHelper.getCollapsedTextColor();
        if (this.editText == null) goto L6;
        updateLabelState(false);
        updateInputLayoutMargins();
        return;
    }

    public void setHintTextColor(ColorStateList r2) {
        if (this.focusedTextColor != r2) goto L5;
        return;
    L5:
        if (this.defaultHintTextColor != null) goto L7;
        this.collapsingTextHelper.setCollapsedTextColor(r2);
    L7:
        this.focusedTextColor = r2;
        if (this.editText == null) goto L12;
        updateLabelState(false);
        return;
    }

    public void setLengthCounter(LengthCounter r1) {
        this.lengthCounter = r1;
    }

    public void setMaxEms(int r2) {
        this.maxEms = r2;
        EditText r1 = this.editText;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r2 == (-1)) goto L9;
        r1.setMaxEms(r2);
        return;
    }

    public void setMaxWidth(int r2) {
        this.maxWidth = r2;
        EditText r1 = this.editText;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r2 == (-1)) goto L9;
        r1.setMaxWidth(r2);
        return;
    }

    public void setMaxWidthResource(int r2) {
        setMaxWidth(getContext().getResources().getDimensionPixelSize(r2));
    }

    public void setMinEms(int r2) {
        this.minEms = r2;
        EditText r1 = this.editText;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r2 == (-1)) goto L9;
        r1.setMinEms(r2);
        return;
    }

    public void setMinWidth(int r2) {
        this.minWidth = r2;
        EditText r1 = this.editText;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r2 == (-1)) goto L9;
        r1.setMinWidth(r2);
        return;
    }

    public void setMinWidthResource(int r2) {
        setMinWidth(getContext().getResources().getDimensionPixelSize(r2));
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(int r1) {
        this.endLayout.setPasswordVisibilityToggleContentDescription(r1);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(CharSequence r1) {
        this.endLayout.setPasswordVisibilityToggleContentDescription(r1);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(int r1) {
        this.endLayout.setPasswordVisibilityToggleDrawable(r1);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable r1) {
        this.endLayout.setPasswordVisibilityToggleDrawable(r1);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean r1) {
        this.endLayout.setPasswordVisibilityToggleEnabled(r1);
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(ColorStateList r1) {
        this.endLayout.setPasswordVisibilityToggleTintList(r1);
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode r1) {
        this.endLayout.setPasswordVisibilityToggleTintMode(r1);
    }

    public void setPlaceholderText(CharSequence r5) {
        if (this.placeholderTextView != null) goto L6;
        AppCompatTextView r0 = new AppCompatTextView(getContext());
        this.placeholderTextView = r0;
        r0.setId(R.id.textinput_placeholder);
        this.placeholderTextView.setImportantForAccessibility(1);
        this.placeholderTextView.setAccessibilityLiveRegion(1);
        Fade r02 = createPlaceholderFadeTransition();
        this.placeholderFadeIn = r02;
        r02.setStartDelay(67);
        this.placeholderFadeOut = createPlaceholderFadeTransition();
        setPlaceholderTextAppearance(this.placeholderTextAppearance);
        setPlaceholderTextColor(this.placeholderTextColor);
        ViewCompat.setAccessibilityDelegate(this.placeholderTextView, new AnonymousClass2(this));
    L6:
        if (TextUtils.isEmpty(r5) == false) goto L9;
        setPlaceholderTextEnabled(false);
    L12:
        updatePlaceholderText();
        return;
    L9:
        if (this.placeholderEnabled == true) goto L11;
        setPlaceholderTextEnabled(true);
    L11:
        this.placeholderText = r5;
        goto L12
    }

    public void setPlaceholderTextAppearance(int r1) {
        this.placeholderTextAppearance = r1;
        TextView r0 = this.placeholderTextView;
        if (r0 == null) goto L6;
        TextViewCompat.setTextAppearance(r0, r1);
        return;
    }

    public void setPlaceholderTextColor(ColorStateList r2) {
        if (this.placeholderTextColor == r2) goto L9;
        this.placeholderTextColor = r2;
        TextView r1 = this.placeholderTextView;
        if (r1 == null) goto L10;
        if (r2 == null) goto L11;
        r1.setTextColor(r2);
        return;
    L11:
        return;
    L10:
        return;
    }

    public void setPrefixText(CharSequence r1) {
        this.startLayout.setPrefixText(r1);
    }

    public void setPrefixTextAppearance(int r1) {
        this.startLayout.setPrefixTextAppearance(r1);
    }

    public void setPrefixTextColor(ColorStateList r1) {
        this.startLayout.setPrefixTextColor(r1);
    }

    public void setShapeAppearanceModel(ShapeAppearanceModel r2) {
        MaterialShapeDrawable r0 = this.boxBackground;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getShapeAppearanceModel() == r2) goto L9;
        this.shapeAppearanceModel = r2;
        applyBoxAttributes();
        return;
    }

    public void setStartIconCheckable(boolean r1) {
        this.startLayout.setStartIconCheckable(r1);
    }

    public void setStartIconContentDescription(int r2) {
        if (r2 == 0) goto L4;
        CharSequence r22 = getResources().getText(r2);
    L5:
        setStartIconContentDescription(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    public void setStartIconContentDescription(CharSequence r1) {
        this.startLayout.setStartIconContentDescription(r1);
    }

    public void setStartIconDrawable(int r2) {
        if (r2 == 0) goto L4;
        Drawable r22 = AppCompatResources.getDrawable(getContext(), r2);
    L5:
        setStartIconDrawable(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    public void setStartIconDrawable(Drawable r1) {
        this.startLayout.setStartIconDrawable(r1);
    }

    public void setStartIconMinSize(int r1) {
        this.startLayout.setStartIconMinSize(r1);
    }

    public void setStartIconOnClickListener(View.OnClickListener r1) {
        this.startLayout.setStartIconOnClickListener(r1);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener r1) {
        this.startLayout.setStartIconOnLongClickListener(r1);
    }

    public void setStartIconScaleType(ImageView.ScaleType r1) {
        this.startLayout.setStartIconScaleType(r1);
    }

    public void setStartIconTintList(ColorStateList r1) {
        this.startLayout.setStartIconTintList(r1);
    }

    public void setStartIconTintMode(PorterDuff.Mode r1) {
        this.startLayout.setStartIconTintMode(r1);
    }

    public void setStartIconVisible(boolean r1) {
        this.startLayout.setStartIconVisible(r1);
    }

    public void setSuffixText(CharSequence r1) {
        this.endLayout.setSuffixText(r1);
    }

    public void setSuffixTextAppearance(int r1) {
        this.endLayout.setSuffixTextAppearance(r1);
    }

    public void setSuffixTextColor(ColorStateList r1) {
        this.endLayout.setSuffixTextColor(r1);
    }

    public void setTextAppearanceCompatWithErrorFallback(TextView r2, int r3) {
        TextViewCompat.setTextAppearance(r2, r3);     // Catch: Exception -> L9
        if (r2.getTextColors().getDefaultColor() == (-65281)) goto L7;
        return;
    L7:
        TextViewCompat.setTextAppearance(r2, R$style.TextAppearance_AppCompat_Caption);
        r2.setTextColor(ContextCompat.getColor(getContext(), R.color.design_error));
    }

    public void setTextInputAccessibilityDelegate(AccessibilityDelegate r1) {
        EditText r0 = this.editText;
        if (r0 == null) goto L6;
        ViewCompat.setAccessibilityDelegate(r0, r1);
        return;
    }

    public void setTypeface(Typeface r2) {
        if (r2 == this.typeface) goto L8;
        this.typeface = r2;
        this.collapsingTextHelper.setTypefaces(r2);
        this.indicatorViewController.setTypefaces(r2);
        TextView r1 = this.counterView;
        if (r1 == null) goto L9;
        r1.setTypeface(r2);
        return;
    L9:
        return;
    }

    public boolean shouldShowError() {
        return this.indicatorViewController.errorShouldBeShown();
    }

    public void updateCounter(Editable r8) {
        int r82 = this.lengthCounter.countLength(r8);
        boolean r0 = this.counterOverflowed;
        int r1 = this.counterMaxLength;
        if (r1 != (-1)) goto L5;
        this.counterView.setText(String.valueOf(r82));
        this.counterView.setContentDescription(null);
        this.counterOverflowed = false;
    L13:
        if (this.editText != null) goto L15;
        return;
    L15:
        if (r0 == this.counterOverflowed) goto L19;
        updateLabelState(false);
        updateTextInputBoxState();
        updateEditTextBackground();
        return;
    L19:
        return;
    L5:
        if (r82 <= r1) goto L7;
        boolean r12 = true;
    L8:
        this.counterOverflowed = r12;
        updateCounterContentDescription(getContext(), this.counterView, r82, this.counterMaxLength, this.counterOverflowed);
        if (r0 == this.counterOverflowed) goto L11;
        updateCounterTextAppearanceAndColor();
    L11:
        this.counterView.setText(BidiFormatter.getInstance().unicodeWrap(getContext().getString(R.string.character_counter_pattern, new Object[]{Integer.valueOf(r82), Integer.valueOf(this.counterMaxLength)})));
        goto L13
    L7:
        r12 = false;
        goto L8
    }

    public boolean updateDummyDrawables() {
        if (this.editText != null) goto L5;
        return false;
    L5:
        boolean r5 = true;
        if (shouldUpdateStartDummyDrawable() == false) goto L16;
        int r0 = Math.max(0, this.startLayout.getMeasuredWidth() - this.editText.getPaddingLeft());
        if (this.startDummyDrawable != null) goto L10;
    L11:
        ColorDrawable r6 = new ColorDrawable();
        this.startDummyDrawable = r6;
        this.startDummyDrawableWidth = r0;
        r6.setBounds(0, 0, r0, 1);
    L12:
        Drawable[] r02 = this.editText.getCompoundDrawablesRelative();
        Drawable r62 = r02[0];
        Drawable r7 = this.startDummyDrawable;
        if (r62 == r7) goto L19;
        this.editText.setCompoundDrawablesRelative(r7, r02[1], r02[2], r02[3]);
    L18:
        boolean r03 = true;
    L21:
        if (shouldUpdateEndDummyDrawable() == false) goto L38;
        int r2 = this.endLayout.getSuffixTextView().getMeasuredWidth() - this.editText.getPaddingRight();
        CheckableImageButton r63 = this.endLayout.getCurrentEndIconView();
        if (r63 == null) goto L25;
        r2 = (r2 + r63.getMeasuredWidth()) + ((ViewGroup.MarginLayoutParams) r63.getLayoutParams()).getMarginStart();
    L25:
        int r22 = Math.max(0, r2);
        Drawable[] r64 = this.editText.getCompoundDrawablesRelative();
        Drawable r72 = this.endDummyDrawable;
        if (r72 != null) goto L28;
    L31:
        if (r72 != null) goto L33;
        ColorDrawable r73 = new ColorDrawable();
        this.endDummyDrawable = r73;
        this.endDummyDrawableWidth = r22;
        r73.setBounds(0, 0, r22, 1);
    L33:
        Drawable r23 = r64[2];
        Drawable r3 = this.endDummyDrawable;
        if (r23 == r3) goto L45;
        this.originalEditTextEndDrawable = r23;
        this.editText.setCompoundDrawablesRelative(r64[0], r64[1], r3, r64[3]);
        return true;
    L45:
        return r03;
    L28:
        if (this.endDummyDrawableWidth == r22) goto L31;
        this.endDummyDrawableWidth = r22;
        r72.setBounds(0, 0, r22, 1);
        this.editText.setCompoundDrawablesRelative(r64[0], r64[1], this.endDummyDrawable, r64[3]);
        return true;
    L38:
        if (this.endDummyDrawable == null) goto L45;
        Drawable[] r65 = this.editText.getCompoundDrawablesRelative();
        if (r65[2] != this.endDummyDrawable) goto L42;
        this.editText.setCompoundDrawablesRelative(r65[0], r65[1], this.originalEditTextEndDrawable, r65[3]);
    L43:
        this.endDummyDrawable = null;
        return r5;
    L42:
        r5 = r03;
    L19:
        r03 = false;
        goto L21
    L10:
        if (this.startDummyDrawableWidth == r0) goto L12;
    L16:
        if (this.startDummyDrawable == null) goto L19;
        Drawable[] r04 = this.editText.getCompoundDrawablesRelative();
        this.editText.setCompoundDrawablesRelative(null, r04[1], r04[2], r04[3]);
        this.startDummyDrawable = null;
        goto L18
    }

    public void updateEditTextBackground() {
        EditText r0 = this.editText;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.boxBackgroundMode != 0) goto L26;
        Drawable r02 = r0.getBackground();
        if (r02 != null) goto L11;
        return;
    L11:
        if (DrawableUtils.canSafelyMutateDrawable(r02) == false) goto L14;
        r02 = r02.mutate();
    L14:
        if (shouldShowError() == false) goto L18;
        r02.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(getErrorCurrentTextColors(), PorterDuff.Mode.SRC_IN));
        return;
    L18:
        if (this.counterOverflowed == false) goto L23;
        TextView r1 = this.counterView;
        if (r1 == null) goto L23;
        r02.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(r1.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        return;
    L23:
        DrawableCompat.clearColorFilter(r02);
        this.editText.refreshDrawableState();
        return;
    }

    public void updateEditTextBoxBackgroundIfNeeded() {
        EditText r0 = this.editText;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.boxBackground != null) goto L7;
        return;
    L7:
        if (this.boxBackgroundApplied == true) goto L11;
        if (r0.getBackground() == null) goto L11;
        return;
    L11:
        if (this.boxBackgroundMode == 0) goto L18;
        updateEditTextBoxBackground();
        this.boxBackgroundApplied = true;
        return;
    }

    public void updateLabelState(boolean r2) {
        updateLabelState(r2, false);
    }

    public void updateTextInputBoxState() {
        if (this.boxBackground != null) goto L5;
        return;
    L5:
        if (this.boxBackgroundMode != 0) goto L8;
        return;
    L8:
        if (isFocused() == true) goto L15;
        EditText r0 = this.editText;
        if (r0 != null) goto L12;
    L14:
        boolean r02 = false;
    L17:
        if (isHovered() == true) goto L24;
        EditText r3 = this.editText;
        if (r3 != null) goto L21;
    L23:
        boolean r32 = false;
    L26:
        if (isEnabled() == true) goto L29;
        this.boxStrokeColor = this.disabledColor;
    L48:
        if (Build.VERSION.SDK_INT < 29) goto L50;
        updateCursorColor();
    L50:
        this.endLayout.onTextInputBoxStateUpdated();
        refreshStartIconDrawableState();
        if (this.boxBackgroundMode != 2) goto L62;
        int r4 = this.boxStrokeWidthPx;
        if (r02 == true) goto L55;
    L57:
        this.boxStrokeWidthPx = this.boxStrokeWidthDefaultPx;
    L59:
        if (this.boxStrokeWidthPx == r4) goto L62;
        recalculateCutout();
        goto L62
    L55:
        if (isEnabled() == false) goto L57;
        this.boxStrokeWidthPx = this.boxStrokeWidthFocusedPx;
    L62:
        if (this.boxBackgroundMode == 1) goto L64;
    L72:
        applyBoxAttributes();
        if (getEndIconMode() != 3) goto L83;
        EditText r03 = this.editText;
        if ((r03 instanceof AutoCompleteTextView) == true) goto L77;
    L80:
        getEndIconView().setFocusable(true);
        getEndIconView().setClickable(true);
        return;
    L77:
        if (EditTextUtils.isEditable(r03) == true) goto L80;
        getEndIconView().setFocusable(false);
        getEndIconView().setClickable(false);
        return;
    L83:
        return;
    L64:
        if (isEnabled() == true) goto L66;
        this.boxBackgroundColor = this.disabledFilledBackgroundColor;
        goto L72
    L66:
        if (r32 == false) goto L69;
        if (r02 == true) goto L69;
        this.boxBackgroundColor = this.hoveredFilledBackgroundColor;
    L69:
        if (r02 == false) goto L71;
        this.boxBackgroundColor = this.focusedFilledBackgroundColor;
        goto L72
    L71:
        this.boxBackgroundColor = this.defaultFilledBackgroundColor;
        goto L72
    L29:
        if (shouldShowError() == false) goto L35;
        if (this.strokeErrorColor == null) goto L33;
        updateStrokeErrorColor(r02, r32);
        goto L48
    L33:
        this.boxStrokeColor = getErrorCurrentTextColors();
        goto L48
    L35:
        if (this.counterOverflowed == false) goto L42;
        TextView r42 = this.counterView;
        if (r42 == null) goto L42;
        if (this.strokeErrorColor == null) goto L41;
        updateStrokeErrorColor(r02, r32);
        goto L48
    L41:
        this.boxStrokeColor = r42.getCurrentTextColor();
    L42:
        if (r02 == false) goto L44;
        this.boxStrokeColor = this.focusedStrokeColor;
        goto L48
    L44:
        if (r32 == false) goto L46;
        this.boxStrokeColor = this.hoveredStrokeColor;
        goto L48
    L46:
        this.boxStrokeColor = this.defaultStrokeColor;
        goto L48
    L21:
        if (r3.isHovered() == false) goto L23;
    L24:
        r32 = true;
        goto L26
    L12:
        if (r0.hasFocus() == false) goto L14;
    L15:
        r02 = true;
        goto L17
    }
}
