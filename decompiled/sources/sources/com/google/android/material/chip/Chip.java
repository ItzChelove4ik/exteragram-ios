package com.google.android.material.chip;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.google.android.material.R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.focus.FocusRingDrawable;
import com.google.android.material.internal.MaterialCheckable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.resources.TextAppearanceFontCallback;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class Chip extends AppCompatCheckBox implements ChipDrawable.Delegate, Shapeable, MaterialCheckable<Chip> {
    private static final String BUTTON_ACCESSIBILITY_CLASS_NAME = "android.widget.Button";
    private static final int[] CHECKABLE_STATE_SET = null;
    private static final int CHIP_BODY_VIRTUAL_ID = 0;
    private static final int CLOSE_ICON_VIRTUAL_ID = 1;
    private static final int DEF_STYLE_RES = 0;
    private static final Rect EMPTY_BOUNDS = null;
    private static final String GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME = "android.view.View";
    private static final String NAMESPACE_ANDROID = "http://schemas.android.com/apk/res/android";
    private static final String RADIO_BUTTON_ACCESSIBILITY_CLASS_NAME = "android.widget.RadioButton";
    private static final int[] SELECTED_STATE = null;
    private static final String TAG = "Chip";
    private CharSequence accessibilityClassName;
    private ChipDrawable chipDrawable;
    private boolean closeIconFocused;
    private boolean closeIconHovered;
    private boolean closeIconPressed;
    private boolean deferredCheckedValue;
    private boolean ensureMinTouchTargetSize;
    private final TextAppearanceFontCallback fontCallback;
    private InsetDrawable insetBackgroundDrawable;
    private int lastLayoutDirection;
    private int minTouchTargetSize;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
    private MaterialCheckable.OnCheckedChangeListener<Chip> onCheckedChangeListenerInternal;
    private View.OnClickListener onCloseIconClickListener;
    private final Rect rect;
    private final RectF rectF;
    private RippleDrawable ripple;
    private final ChipTouchHelper touchHelper;
    private boolean touchHelperEnabled;

    /* renamed from: com.google.android.material.chip.Chip$1, reason: invalid class name */
    public class AnonymousClass1 extends TextAppearanceFontCallback {
        final /* synthetic */ Chip this$0;

        public AnonymousClass1(Chip r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.resources.TextAppearanceFontCallback
        public void onFontRetrievalFailed(int r1) {
        }

        @Override // com.google.android.material.resources.TextAppearanceFontCallback
        public void onFontRetrieved(Typeface r2, boolean r3) {
            Chip r22 = this.this$0;
            boolean r32 = Chip.access$000(r22).shouldDrawText();
            Chip r0 = this.this$0;
            if (r32 == false) goto L5;
            CharSequence r33 = Chip.access$000(r0).getText();
        L6:
            r22.setText(r33);
            this.this$0.requestLayout();
            this.this$0.invalidate();
            return;
        L5:
            r33 = r0.getText();
            goto L6
        }
    }

    /* renamed from: com.google.android.material.chip.Chip$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewOutlineProvider {
        final /* synthetic */ Chip this$0;

        public AnonymousClass2(Chip r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r1, Outline r2) {
            if (Chip.access$000(this.this$0) == null) goto L6;
            Chip.access$000(this.this$0).getOutline(r2);
            return;
        L6:
            r2.setAlpha(0.0f);
        }
    }

    public class ChipTouchHelper extends ExploreByTouchHelper {
        final /* synthetic */ Chip this$0;

        public ChipTouchHelper(Chip r1, Chip r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public int getVirtualViewAt(float r2, float r3) {
            if (Chip.access$100(this.this$0) == true) goto L5;
            return 0;
        L5:
            if (Chip.access$200(this.this$0).contains(r2, r3) == false) goto L10;
            return 1;
        L10:
            return 0;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void getVisibleVirtualViews(List<Integer> r2) {
            r2.add(0);
            if (Chip.access$100(this.this$0) == true) goto L5;
            return;
        L5:
            if (this.this$0.isCloseIconVisible() == true) goto L7;
            return;
        L7:
            if (Chip.access$300(this.this$0) == null) goto L12;
            r2.add(1);
            return;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public boolean onPerformActionForVirtualView(int r1, int r2, Bundle r3) {
            if (r2 != 16) goto L11;
            if (r1 != 0) goto L8;
            return this.this$0.performClick();
        L8:
            if (r1 == 1) goto L10;
            return false;
        L10:
            return this.this$0.performCloseIconClick();
        L11:
            return false;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void onPopulateNodeForHost(AccessibilityNodeInfoCompat r2) {
            r2.setCheckable(this.this$0.isCheckable());
            r2.setClickable(this.this$0.isClickable());
            r2.setClassName(this.this$0.getAccessibilityClassName());
            r2.setText(this.this$0.getText());
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void onPopulateNodeForVirtualView(int r5, AccessibilityNodeInfoCompat r6) {
            CharSequence r1 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r5 != 1) goto L13;
            CharSequence r52 = this.this$0.getCloseIconContentDescription();
            if (r52 == null) goto L7;
            r6.setContentDescription(r52);
        L11:
            r6.setBoundsInParent(Chip.access$500(this.this$0));
            r6.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
            r6.setEnabled(this.this$0.isEnabled());
            r6.setClassName(Button.class.getName());
            return;
        L7:
            CharSequence r53 = this.this$0.getText();
            Context r0 = this.this$0.getContext();
            int r2 = R.string.mtrl_chip_close_icon_content_description;
            if (TextUtils.isEmpty(r53) == true) goto L10;
            r1 = r53;
        L10:
            r6.setContentDescription(r0.getString(r2, new Object[]{r1}).trim());
            goto L11
        L13:
            r6.setContentDescription(_UrlKt.FRAGMENT_ENCODE_SET);
            r6.setBoundsInParent(Chip.access$600());
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void onVirtualViewKeyboardFocusChanged(int r2, boolean r3) {
            if (r2 != 1) goto L6;
            Chip.access$402(this.this$0, r3);
        L6:
            if (Chip.access$000(this.this$0).refreshCloseIconFocus(Chip.access$400(this.this$0)) == false) goto L9;
            this.this$0.refreshDrawableState();
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$BX4ppTUZCDnixncojra1Ulz3cAk(Chip r1, CompoundButton r2, boolean r3) {
        MaterialCheckable.OnCheckedChangeListener<Chip> r0 = r1.onCheckedChangeListenerInternal;
        if (r0 == null) goto L5;
        r0.onCheckedChanged(r1, r3);
    L5:
        CompoundButton.OnCheckedChangeListener r12 = r1.onCheckedChangeListener;
        if (r12 == null) goto L9;
        r12.onCheckedChanged(r2, r3);
        return;
    }

    static {
        DEF_STYLE_RES = R.style.Widget_MaterialComponents_Chip_Action;
        EMPTY_BOUNDS = new Rect();
        SELECTED_STATE = new int[]{android.R.attr.state_selected};
        CHECKABLE_STATE_SET = new int[]{android.R.attr.state_checkable};
    }

    public Chip(Context r2) {
        this(r2, null);
    }

    public Chip(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.chipStyle);
    }

    public Chip(Context r7, AttributeSet r8, int r9) {
        int r4 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(r7, r8, r9, r4), r8, r9);
        this.rect = new Rect();
        this.rectF = new RectF();
        this.fontCallback = new AnonymousClass1(this);
        Context r0 = getContext();
        validateAttributes(r8);
        ChipDrawable r72 = ChipDrawable.createFromAttributes(r0, r8, r9, r4);
        initMinTouchTarget(r0, r8, r9);
        setChipDrawable(r72);
        r72.setElevation(getElevation());
        TypedArray r82 = ThemeEnforcement.obtainStyledAttributes(r0, r8, R.styleable.Chip, r9, r4, new int[0]);
        boolean r92 = r82.hasValue(R.styleable.Chip_shapeAppearance);
        r82.recycle();
        this.touchHelper = new ChipTouchHelper(this, this);
        updateAccessibilityDelegate();
        if (r92 == true) goto L5;
        initOutlineProvider();
    L5:
        setChecked(this.deferredCheckedValue);
        setText(r72.getText());
        setEllipsize(r72.getEllipsize());
        updateTextPaintDrawState();
        if (this.chipDrawable.shouldDrawText() == true) goto L8;
        setLines(1);
        setHorizontallyScrolling(true);
    L8:
        setGravity(NavigationBarView.ITEM_GRAVITY_START_CENTER);
        updatePaddingInternal();
        if (shouldEnsureMinTouchTargetSize() == false) goto L11;
        setMinHeight(this.minTouchTargetSize);
    L11:
        this.lastLayoutDirection = getLayoutDirection();
        super.setOnCheckedChangeListener(new Chip$$ExternalSyntheticLambda0(this));
    }

    public static /* synthetic */ ChipDrawable access$000(Chip r0) {
        return r0.chipDrawable;
    }

    public static /* synthetic */ boolean access$100(Chip r0) {
        return r0.hasCloseIcon();
    }

    public static /* synthetic */ RectF access$200(Chip r0) {
        return r0.getCloseIconTouchBounds();
    }

    public static /* synthetic */ View.OnClickListener access$300(Chip r0) {
        return r0.onCloseIconClickListener;
    }

    public static /* synthetic */ boolean access$400(Chip r0) {
        return r0.closeIconFocused;
    }

    public static /* synthetic */ boolean access$402(Chip r0, boolean r1) {
        r0.closeIconFocused = r1;
        return r1;
    }

    public static /* synthetic */ Rect access$500(Chip r0) {
        return r0.getCloseIconTouchBoundsInt();
    }

    public static /* synthetic */ Rect access$600() {
        return EMPTY_BOUNDS;
    }

    private void applyChipDrawable(ChipDrawable r1) {
        r1.setDelegate(this);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
    private int[] createCloseIconDrawableState() {
        ?? r0 = isEnabled();
        int r02 = r0;
        if (this.closeIconFocused == false) goto L5;
        r02 = r0 + 1;
    L5:
        int r03 = r02;
        if (this.closeIconHovered == false) goto L8;
        r03 = r02 + 1;
    L8:
        int r04 = r03;
        if (this.closeIconPressed == false) goto L11;
        r04 = r03 + 1;
    L11:
        int r05 = r04;
        if (isChecked() == false) goto L14;
        r05 = r04 + 1;
    L14:
        int[] r06 = new int[r05];
        int r2 = 0;
        if (isEnabled() == false) goto L18;
        r06[0] = 16842910;
        r2 = 1;
    L18:
        if (this.closeIconFocused == false) goto L21;
        r06[r2] = 16842908;
        r2 = r2 + 1;
    L21:
        if (this.closeIconHovered == false) goto L24;
        r06[r2] = 16843623;
        r2 = r2 + 1;
    L24:
        if (this.closeIconPressed == false) goto L27;
        r06[r2] = 16842919;
        r2 = r2 + 1;
    L27:
        if (isChecked() == false) goto L29;
        r06[r2] = 16842913;
    L29:
        return r06;
    }

    private RectF getCloseIconTouchBounds() {
        this.rectF.setEmpty();
        if (hasCloseIcon() == false) goto L8;
        if (this.onCloseIconClickListener == null) goto L8;
        this.chipDrawable.getCloseIconTouchBounds(this.rectF);
    L8:
        return this.rectF;
    }

    private Rect getCloseIconTouchBoundsInt() {
        RectF r0 = getCloseIconTouchBounds();
        this.rect.set((int) r0.left, (int) r0.top, (int) r0.right, (int) r0.bottom);
        return this.rect;
    }

    private TextAppearance getTextAppearance() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getTextAppearance();
    }

    private boolean hasCloseIcon() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.getCloseIcon() == null) goto L10;
        return true;
    L10:
        return false;
    }

    private void initMinTouchTarget(Context r8, AttributeSet r9, int r10) {
        TypedArray r82 = ThemeEnforcement.obtainStyledAttributes(r8, r9, R.styleable.Chip, r10, DEF_STYLE_RES, new int[0]);
        this.ensureMinTouchTargetSize = r82.getBoolean(R.styleable.Chip_ensureMinTouchTargetSize, false);
        float r92 = MaterialAttributes.resolveMinimumAccessibleTouchTarget(r8);
        this.minTouchTargetSize = (int) Math.ceil(r82.getDimension(R.styleable.Chip_chipMinTouchTargetSize, r92));
        r82.recycle();
    }

    private void initOutlineProvider() {
        setOutlineProvider(new AnonymousClass2(this));
    }

    private void insetChipBackgroundDrawable(int r7, int r8, int r9, int r10) {
        this.insetBackgroundDrawable = new InsetDrawable(this.chipDrawable, r7, r8, r9, r10);
    }

    private void removeBackgroundInset() {
        if (this.insetBackgroundDrawable == null) goto L6;
        this.insetBackgroundDrawable = null;
        setMinWidth(0);
        setMinHeight((int) getChipMinHeight());
        updateBackgroundDrawable();
        return;
    }

    private void setCloseIconHovered(boolean r2) {
        if (this.closeIconHovered == r2) goto L6;
        this.closeIconHovered = r2;
        refreshDrawableState();
        return;
    }

    private void setCloseIconPressed(boolean r2) {
        if (this.closeIconPressed == r2) goto L6;
        this.closeIconPressed = r2;
        refreshDrawableState();
        return;
    }

    private void unapplyChipDrawable(ChipDrawable r1) {
        if (r1 == null) goto L5;
        r1.setDelegate(null);
        return;
    }

    private void updateAccessibilityDelegate() {
        if (hasCloseIcon() == true) goto L5;
    L10:
        ViewCompat.setAccessibilityDelegate(this, null);
        this.touchHelperEnabled = false;
        return;
    L5:
        if (isCloseIconVisible() == false) goto L10;
        if (this.onCloseIconClickListener == null) goto L10;
        ViewCompat.setAccessibilityDelegate(this, this.touchHelper);
        this.touchHelperEnabled = true;
    }

    private void updateBackgroundDrawable() {
        updateFrameworkRippleBackground();
    }

    private void updateFrameworkRippleBackground() {
        RippleDrawable r0 = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(this.chipDrawable.getRippleColor()), getBackgroundDrawable(), null);
        FocusRingDrawable.layer(getContext(), r0, this.chipDrawable);
        this.ripple = r0;
        this.chipDrawable.setUseCompatRipple(false);
        setBackground(this.ripple);
        updatePaddingInternal();
    }

    private void updatePaddingInternal() {
        if (TextUtils.isEmpty(getText()) == true) goto L12;
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L13;
        int r02 = (int) ((r0.getChipEndPadding() + this.chipDrawable.getTextEndPadding()) + this.chipDrawable.calculateCloseIconWidth());
        int r1 = (int) ((this.chipDrawable.getChipStartPadding() + this.chipDrawable.getTextStartPadding()) + this.chipDrawable.calculateChipIconWidth());
        if (this.insetBackgroundDrawable == null) goto L10;
        Rect r2 = new Rect();
        this.insetBackgroundDrawable.getPadding(r2);
        r1 = r1 + r2.left;
        r02 = r02 + r2.right;
    L10:
        setPaddingRelative(r1, getPaddingTop(), r02, getPaddingBottom());
        return;
    L13:
        return;
    }

    private void updateTextPaintDrawState() {
        TextPaint r0 = getPaint();
        ChipDrawable r1 = this.chipDrawable;
        if (r1 == null) goto L5;
        r0.drawableState = r1.getState();
    L5:
        TextAppearance r12 = getTextAppearance();
        if (r12 == null) goto L9;
        r12.updateDrawState(getContext(), r0, this.fontCallback);
        return;
    }

    private void validateAttributes(AttributeSet r4) {
        if (r4 != null) goto L5;
        return;
    L5:
        if (r4.getAttributeValue(NAMESPACE_ANDROID, "background") == null) goto L8;
        Log.w(TAG, "Do not set the background; Chip manages its own background drawable.");
    L8:
        if (r4.getAttributeValue(NAMESPACE_ANDROID, "drawableLeft") == null) goto L10;
        ByteString$$ExternalSyntheticBUOutline0.m("Please set left drawable using R.attr#chipIcon.");
        return;
    L10:
        if (r4.getAttributeValue(NAMESPACE_ANDROID, "drawableStart") == null) goto L12;
        ByteString$$ExternalSyntheticBUOutline0.m("Please set start drawable using R.attr#chipIcon.");
        return;
    L12:
        if (r4.getAttributeValue(NAMESPACE_ANDROID, "drawableEnd") == null) goto L14;
        ByteString$$ExternalSyntheticBUOutline0.m("Please set end drawable using R.attr#closeIcon.");
        return;
    L14:
        if (r4.getAttributeValue(NAMESPACE_ANDROID, "drawableRight") == null) goto L16;
        ByteString$$ExternalSyntheticBUOutline0.m("Please set end drawable using R.attr#closeIcon.");
        return;
    L16:
        if (r4.getAttributeBooleanValue(NAMESPACE_ANDROID, "singleLine", true) == true) goto L18;
    L27:
        ByteString$$ExternalSyntheticBUOutline0.m("Chip does not support multi-line text");
        return;
    L18:
        if (r4.getAttributeIntValue(NAMESPACE_ANDROID, "lines", 1) != 1) goto L27;
        if (r4.getAttributeIntValue(NAMESPACE_ANDROID, "minLines", 1) != 1) goto L27;
        if (r4.getAttributeIntValue(NAMESPACE_ANDROID, "maxLines", 1) != 1) goto L27;
        if (r4.getAttributeIntValue(NAMESPACE_ANDROID, "gravity", NavigationBarView.ITEM_GRAVITY_START_CENTER) == 8388627) goto L37;
        Log.w(TAG, "Chip text must be vertically center and start aligned");
        return;
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent r2) {
        if (this.touchHelperEnabled == true) goto L7;
        return super.dispatchHoverEvent(r2);
    L7:
        if (this.touchHelper.dispatchHoverEvent(r2) == false) goto L9;
        return true;
    L9:
        if (super.dispatchHoverEvent(r2) == true) goto L15;
        return false;
    L15:
        return true;
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent r3) {
        if (this.touchHelperEnabled == true) goto L7;
        return super.dispatchKeyEvent(r3);
    L7:
        if (this.touchHelper.dispatchKeyEvent(r3) == false) goto L13;
        if (this.touchHelper.getKeyboardFocusedVirtualViewId() == Integer.MIN_VALUE) goto L13;
        return true;
    L13:
        return super.dispatchKeyEvent(r3);
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
    L7:
        boolean r02 = false;
    L8:
        if (r02 == false) goto L11;
        invalidate();
        return;
    L11:
        return;
    L5:
        if (r0.isCloseIconStateful() == false) goto L7;
        r02 = this.chipDrawable.setCloseIconState(createCloseIconDrawableState());
        goto L8
    }

    public boolean ensureAccessibleTouchTarget(int r6) {
        this.minTouchTargetSize = r6;
        int r1 = 0;
        if (shouldEnsureMinTouchTargetSize() == false) goto L5;
        int r0 = Math.max(0, r6 - this.chipDrawable.getIntrinsicHeight());
        int r2 = Math.max(0, r6 - this.chipDrawable.getIntrinsicWidth());
        if (r2 > 0) goto L17;
        if (r0 > 0) goto L17;
        if (this.insetBackgroundDrawable == null) goto L15;
        removeBackgroundInset();
    L16:
        return false;
    L15:
        updateBackgroundDrawable();
    L17:
        if (r2 <= 0) goto L19;
        int r22 = r2 / 2;
    L20:
        if (r0 <= 0) goto L23;
        r1 = r0 / 2;
    L23:
        if (this.insetBackgroundDrawable == null) goto L35;
        Rect r02 = new Rect();
        this.insetBackgroundDrawable.getPadding(r02);
        if (r02.top != r1) goto L35;
        if (r02.bottom != r1) goto L35;
        if (r02.left != r22) goto L35;
        if (r02.right != r22) goto L35;
        updateBackgroundDrawable();
        return true;
    L35:
        if (getMinHeight() == r6) goto L38;
        setMinHeight(r6);
    L38:
        if (getMinWidth() == r6) goto L40;
        setMinWidth(r6);
    L40:
        insetChipBackgroundDrawable(r22, r1, r22, r1);
        updateBackgroundDrawable();
        return true;
    L19:
        r22 = 0;
        goto L20
    L5:
        if (this.insetBackgroundDrawable == null) goto L7;
        removeBackgroundInset();
    L8:
        return false;
    L7:
        updateBackgroundDrawable();
        goto L8
    }

    @Override // android.widget.CheckBox, android.widget.CompoundButton, android.widget.Button, android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        if (TextUtils.isEmpty(this.accessibilityClassName) == true) goto L7;
        return this.accessibilityClassName;
    L7:
        if (isCheckable() == false) goto L16;
        ViewParent r2 = getParent();
        if ((r2 instanceof ChipGroup) == true) goto L11;
    L14:
        return BUTTON_ACCESSIBILITY_CLASS_NAME;
    L11:
        if (((ChipGroup) r2).isSingleSelection() == false) goto L14;
        return RADIO_BUTTON_ACCESSIBILITY_CLASS_NAME;
    L16:
        if (isClickable() == false) goto L18;
        return BUTTON_ACCESSIBILITY_CLASS_NAME;
    L18:
        return GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME;
    }

    public Drawable getBackgroundDrawable() {
        InsetDrawable r0 = this.insetBackgroundDrawable;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return this.chipDrawable;
    }

    public Drawable getCheckedIcon() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getCheckedIcon();
    }

    public ColorStateList getCheckedIconTint() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getCheckedIconTint();
    }

    public ColorStateList getChipBackgroundColor() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getChipBackgroundColor();
    }

    public float getChipCornerRadius() {
        ChipDrawable r1 = this.chipDrawable;
        if (r1 != null) goto L5;
        return 0.0f;
    L5:
        return Math.max(0.0f, r1.getChipCornerRadius());
    }

    public Drawable getChipDrawable() {
        return this.chipDrawable;
    }

    public float getChipEndPadding() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return 0.0f;
    L5:
        return r0.getChipEndPadding();
    }

    public Drawable getChipIcon() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getChipIcon();
    }

    public float getChipIconSize() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return 0.0f;
    L5:
        return r0.getChipIconSize();
    }

    public ColorStateList getChipIconTint() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getChipIconTint();
    }

    public float getChipMinHeight() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return 0.0f;
    L5:
        return r0.getChipMinHeight();
    }

    public float getChipStartPadding() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return 0.0f;
    L5:
        return r0.getChipStartPadding();
    }

    public ColorStateList getChipStrokeColor() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getChipStrokeColor();
    }

    public float getChipStrokeWidth() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return 0.0f;
    L5:
        return r0.getChipStrokeWidth();
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    public Drawable getCloseIcon() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getCloseIcon();
    }

    public CharSequence getCloseIconContentDescription() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getCloseIconContentDescription();
    }

    public float getCloseIconEndPadding() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return 0.0f;
    L5:
        return r0.getCloseIconEndPadding();
    }

    public float getCloseIconSize() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return 0.0f;
    L5:
        return r0.getCloseIconSize();
    }

    public float getCloseIconStartPadding() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return 0.0f;
    L5:
        return r0.getCloseIconStartPadding();
    }

    public ColorStateList getCloseIconTint() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getCloseIconTint();
    }

    @Override // android.widget.TextView
    public TextUtils.TruncateAt getEllipsize() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getEllipsize();
    }

    @Override // android.widget.TextView, android.view.View
    public void getFocusedRect(Rect r3) {
        if (this.touchHelperEnabled == true) goto L5;
    L10:
        super.getFocusedRect(r3);
        return;
    L5:
        if (this.touchHelper.getKeyboardFocusedVirtualViewId() != 1) goto L7;
    L8:
        r3.set(getCloseIconTouchBoundsInt());
        return;
    L7:
        if (this.touchHelper.getAccessibilityFocusedVirtualViewId() != 1) goto L10;
        goto L8
    }

    @Override // android.widget.TextView
    public String getFontVariationSettings() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L7;
        return r0.getFontVariationSettings();
    L7:
        return super.getFontVariationSettings();
    }

    public MotionSpec getHideMotionSpec() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getHideMotionSpec();
    }

    public float getIconEndPadding() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return 0.0f;
    L5:
        return r0.getIconEndPadding();
    }

    public float getIconStartPadding() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return 0.0f;
    L5:
        return r0.getIconStartPadding();
    }

    public ColorStateList getRippleColor() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getRippleColor();
    }

    @Override // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.chipDrawable.getShapeAppearanceModel();
    }

    public MotionSpec getShowMotionSpec() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getShowMotionSpec();
    }

    public float getTextEndPadding() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return 0.0f;
    L5:
        return r0.getTextEndPadding();
    }

    public float getTextStartPadding() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return 0.0f;
    L5:
        return r0.getTextStartPadding();
    }

    public boolean isCheckable() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isCheckable() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Deprecated
    public boolean isCheckedIconEnabled() {
        return isCheckedIconVisible();
    }

    public boolean isCheckedIconVisible() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isCheckedIconVisible() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Deprecated
    public boolean isChipIconEnabled() {
        return isChipIconVisible();
    }

    public boolean isChipIconVisible() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isChipIconVisible() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Deprecated
    public boolean isCloseIconEnabled() {
        return isCloseIconVisible();
    }

    public boolean isCloseIconVisible() {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isCloseIconVisible() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this, this.chipDrawable);
    }

    @Override // com.google.android.material.chip.ChipDrawable.Delegate
    public void onChipDrawableSizeChange() {
        ensureAccessibleTouchTarget(this.minTouchTargetSize);
        requestLayout();
        invalidateOutline();
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int r2) {
        int[] r22 = super.onCreateDrawableState(r2 + 2);
        if (isChecked() == false) goto L6;
        View.mergeDrawableStates(r22, SELECTED_STATE);
    L6:
        if (isCheckable() == false) goto L8;
        View.mergeDrawableStates(r22, CHECKABLE_STATE_SET);
    L8:
        return r22;
    }

    @Override // android.widget.TextView, android.view.View
    public void onFocusChanged(boolean r2, int r3, Rect r4) {
        super.onFocusChanged(r2, r3, r4);
        if (this.touchHelperEnabled == false) goto L6;
        this.touchHelper.onFocusChanged(r2, r3, r4);
        return;
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent r4) {
        int r0 = r4.getActionMasked();
        if (r0 != 7) goto L5;
        setCloseIconHovered(getCloseIconTouchBounds().contains(r4.getX(), r4.getY()));
    L10:
        return super.onHoverEvent(r4);
    L5:
        if (r0 != 10) goto L10;
        setCloseIconHovered(false);
        goto L10
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r9) {
        super.onInitializeAccessibilityNodeInfo(r9);
        r9.setClassName(getAccessibilityClassName());
        r9.setCheckable(isCheckable());
        r9.setClickable(isClickable());
        if ((getParent() instanceof ChipGroup) == false) goto L11;
        ChipGroup r0 = (ChipGroup) getParent();
        AccessibilityNodeInfoCompat r92 = AccessibilityNodeInfoCompat.wrap(r9);
        if (r0.isSingleLine() == false) goto L8;
        int r1 = r0.getIndexOfChip(this);
    L7:
        int r4 = r1;
        r92.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(r0.getRowIndex(this), 1, r4, 1, false, isChecked()));
        return;
    L8:
        r1 = -1;
        goto L7
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    @TargetApi(24)
    public PointerIcon onResolvePointerIcon(MotionEvent r4, int r5) {
        if (getCloseIconTouchBounds().contains(r4.getX(), r4.getY()) == false) goto L9;
        if (isEnabled() == false) goto L9;
        return PointerIcon.getSystemIcon(getContext(), 1002);
    L9:
        return super.onResolvePointerIcon(r4, r5);
    }

    @Override // android.widget.TextView, android.view.View
    public void onRtlPropertiesChanged(int r2) {
        super.onRtlPropertiesChanged(r2);
        if (this.lastLayoutDirection == r2) goto L6;
        this.lastLayoutDirection = r2;
        updatePaddingInternal();
        return;
    }

    @Override // android.widget.TextView, android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent r6) {
        int r0 = r6.getActionMasked();
        boolean r1 = getCloseIconTouchBounds().contains(r6.getX(), r6.getY());
        if (r0 == 0) goto L20;
        if (r0 == 1) goto L16;
        if (r0 == 2) goto L11;
        if (r0 != 3) goto L22;
    L18:
        boolean r02 = false;
    L19:
        setCloseIconPressed(false);
    L23:
        if (r02 == false) goto L25;
    L28:
        return true;
    L25:
        if (super.onTouchEvent(r6) == true) goto L28;
        return false;
    L22:
        r02 = false;
        goto L23
    L11:
        if (this.closeIconPressed == false) goto L22;
        if (r1 == true) goto L14;
        setCloseIconPressed(false);
    L14:
        r02 = true;
        goto L23
    L16:
        if (this.closeIconPressed == false) goto L18;
        performCloseIconClick();
        r02 = true;
        goto L19
    L20:
        if (r1 == false) goto L22;
        setCloseIconPressed(true);
        goto L14
    }

    public boolean performCloseIconClick() {
        boolean r0 = false;
        playSoundEffect(0);
        View.OnClickListener r1 = this.onCloseIconClickListener;
        if (r1 == null) goto L6;
        r1.onClick(this);
        r0 = true;
    L6:
        if (this.touchHelperEnabled == false) goto L8;
        this.touchHelper.sendEventForVirtualView(1, 1);
    L8:
        return r0;
    }

    public void setAccessibilityClassName(CharSequence r1) {
        this.accessibilityClassName = r1;
    }

    @Override // android.view.View
    public void setBackground(Drawable r2) {
        if (r2 != getBackgroundDrawable()) goto L5;
    L8:
        super.setBackground(r2);
        return;
    L5:
        if (r2 == this.ripple) goto L8;
        Log.w(TAG, "Do not set the background; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundColor(int r1) {
        Log.w(TAG, "Do not set the background color; Chip manages its own background drawable.");
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.view.View
    public void setBackgroundDrawable(Drawable r2) {
        if (r2 != getBackgroundDrawable()) goto L5;
    L8:
        super.setBackgroundDrawable(r2);
        return;
    L5:
        if (r2 == this.ripple) goto L8;
        Log.w(TAG, "Do not set the background drawable; Chip manages its own background drawable.");
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.view.View
    public void setBackgroundResource(int r1) {
        Log.w(TAG, "Do not set the background resource; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList r1) {
        Log.w(TAG, "Do not set the background tint list; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode r1) {
        Log.w(TAG, "Do not set the background tint mode; Chip manages its own background drawable.");
    }

    public void setCheckable(boolean r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setCheckable(r1);
        return;
    }

    public void setCheckableResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setCheckableResource(r1);
        return;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean r2) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 != null) goto L7;
        this.deferredCheckedValue = r2;
        return;
    L7:
        if (r0.isCheckable() == false) goto L10;
        super.setChecked(r2);
        return;
    }

    public void setCheckedIcon(Drawable r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setCheckedIcon(r1);
        return;
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean r1) {
        setCheckedIconVisible(r1);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int r1) {
        setCheckedIconVisible(r1);
    }

    public void setCheckedIconResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setCheckedIconResource(r1);
        return;
    }

    public void setCheckedIconTint(ColorStateList r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setCheckedIconTint(r1);
        return;
    }

    public void setCheckedIconTintResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setCheckedIconTintResource(r1);
        return;
    }

    public void setCheckedIconVisible(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setCheckedIconVisible(r1);
        return;
    }

    public void setCheckedIconVisible(boolean r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setCheckedIconVisible(r1);
        return;
    }

    public void setChipBackgroundColor(ColorStateList r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipBackgroundColor(r1);
        return;
    }

    public void setChipBackgroundColorResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipBackgroundColorResource(r1);
        return;
    }

    @Deprecated
    public void setChipCornerRadius(float r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipCornerRadius(r1);
        return;
    }

    @Deprecated
    public void setChipCornerRadiusResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipCornerRadiusResource(r1);
        return;
    }

    public void setChipDrawable(ChipDrawable r2) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == r2) goto L6;
        unapplyChipDrawable(r0);
        this.chipDrawable = r2;
        r2.setShouldDrawText(false);
        applyChipDrawable(this.chipDrawable);
        ensureAccessibleTouchTarget(this.minTouchTargetSize);
        return;
    }

    public void setChipEndPadding(float r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipEndPadding(r1);
        return;
    }

    public void setChipEndPaddingResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipEndPaddingResource(r1);
        return;
    }

    public void setChipIcon(Drawable r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipIcon(r1);
        return;
    }

    @Deprecated
    public void setChipIconEnabled(boolean r1) {
        setChipIconVisible(r1);
    }

    @Deprecated
    public void setChipIconEnabledResource(int r1) {
        setChipIconVisible(r1);
    }

    public void setChipIconResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipIconResource(r1);
        return;
    }

    public void setChipIconSize(float r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipIconSize(r1);
        return;
    }

    public void setChipIconSizeResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipIconSizeResource(r1);
        return;
    }

    public void setChipIconTint(ColorStateList r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipIconTint(r1);
        return;
    }

    public void setChipIconTintResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipIconTintResource(r1);
        return;
    }

    public void setChipIconVisible(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipIconVisible(r1);
        return;
    }

    public void setChipIconVisible(boolean r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipIconVisible(r1);
        return;
    }

    public void setChipMinHeight(float r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipMinHeight(r1);
        return;
    }

    public void setChipMinHeightResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipMinHeightResource(r1);
        return;
    }

    public void setChipStartPadding(float r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipStartPadding(r1);
        return;
    }

    public void setChipStartPaddingResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipStartPaddingResource(r1);
        return;
    }

    public void setChipStrokeColor(ColorStateList r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipStrokeColor(r1);
        return;
    }

    public void setChipStrokeColorResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipStrokeColorResource(r1);
        return;
    }

    public void setChipStrokeWidth(float r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipStrokeWidth(r1);
        return;
    }

    public void setChipStrokeWidthResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setChipStrokeWidthResource(r1);
        return;
    }

    @Deprecated
    public void setChipText(CharSequence r1) {
        setText(r1);
    }

    @Deprecated
    public void setChipTextResource(int r2) {
        setText(getResources().getString(r2));
    }

    public void setCloseIcon(Drawable r2) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L5;
        r0.setCloseIcon(r2);
    L5:
        updateAccessibilityDelegate();
    }

    public void setCloseIconContentDescription(CharSequence r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setCloseIconContentDescription(r1);
        return;
    }

    @Deprecated
    public void setCloseIconEnabled(boolean r1) {
        setCloseIconVisible(r1);
    }

    @Deprecated
    public void setCloseIconEnabledResource(int r1) {
        setCloseIconVisible(r1);
    }

    public void setCloseIconEndPadding(float r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setCloseIconEndPadding(r1);
        return;
    }

    public void setCloseIconEndPaddingResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setCloseIconEndPaddingResource(r1);
        return;
    }

    public void setCloseIconResource(int r2) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L5;
        r0.setCloseIconResource(r2);
    L5:
        updateAccessibilityDelegate();
    }

    public void setCloseIconSize(float r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setCloseIconSize(r1);
        return;
    }

    public void setCloseIconSizeResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setCloseIconSizeResource(r1);
        return;
    }

    public void setCloseIconStartPadding(float r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setCloseIconStartPadding(r1);
        return;
    }

    public void setCloseIconStartPaddingResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setCloseIconStartPaddingResource(r1);
        return;
    }

    public void setCloseIconTint(ColorStateList r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setCloseIconTint(r1);
        return;
    }

    public void setCloseIconTintResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setCloseIconTintResource(r1);
        return;
    }

    public void setCloseIconVisible(int r2) {
        setCloseIconVisible(getResources().getBoolean(r2));
    }

    public void setCloseIconVisible(boolean r2) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L5;
        r0.setCloseIconVisible(r2);
    L5:
        updateAccessibilityDelegate();
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.TextView
    public void setCompoundDrawables(Drawable r1, Drawable r2, Drawable r3, Drawable r4) {
        if (r1 != null) goto L8;
        if (r3 != null) goto L6;
        super.setCompoundDrawables(r1, r2, r3, r4);
        return;
    L6:
        ByteString$$ExternalSyntheticBUOutline0.m("Please set end drawable using R.attr#closeIcon.");
        return;
    L8:
        ByteString$$ExternalSyntheticBUOutline0.m("Please set start drawable using R.attr#chipIcon.");
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable r1, Drawable r2, Drawable r3, Drawable r4) {
        if (r1 != null) goto L8;
        if (r3 != null) goto L6;
        super.setCompoundDrawablesRelative(r1, r2, r3, r4);
        return;
    L6:
        ByteString$$ExternalSyntheticBUOutline0.m("Please set end drawable using R.attr#closeIcon.");
        return;
    L8:
        ByteString$$ExternalSyntheticBUOutline0.m("Please set start drawable using R.attr#chipIcon.");
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int r1, int r2, int r3, int r4) {
        if (r1 != 0) goto L8;
        if (r3 != 0) goto L6;
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(r1, r2, r3, r4);
        return;
    L6:
        ByteString$$ExternalSyntheticBUOutline0.m("Please set end drawable using R.attr#closeIcon.");
        return;
    L8:
        ByteString$$ExternalSyntheticBUOutline0.m("Please set start drawable using R.attr#chipIcon.");
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable r1, Drawable r2, Drawable r3, Drawable r4) {
        if (r1 != null) goto L8;
        if (r3 != null) goto L6;
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(r1, r2, r3, r4);
        return;
    L6:
        ByteString$$ExternalSyntheticBUOutline0.m("Please set end drawable using R.attr#closeIcon.");
        return;
    L8:
        ByteString$$ExternalSyntheticBUOutline0.m("Please set start drawable using R.attr#chipIcon.");
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int r1, int r2, int r3, int r4) {
        if (r1 != 0) goto L8;
        if (r3 != 0) goto L6;
        super.setCompoundDrawablesWithIntrinsicBounds(r1, r2, r3, r4);
        return;
    L6:
        ByteString$$ExternalSyntheticBUOutline0.m("Please set end drawable using R.attr#closeIcon.");
        return;
    L8:
        ByteString$$ExternalSyntheticBUOutline0.m("Please set start drawable using R.attr#chipIcon.");
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable r1, Drawable r2, Drawable r3, Drawable r4) {
        if (r1 != null) goto L8;
        if (r3 != null) goto L6;
        super.setCompoundDrawablesWithIntrinsicBounds(r1, r2, r3, r4);
        return;
    L6:
        ByteString$$ExternalSyntheticBUOutline0.m("Please set right drawable using R.attr#closeIcon.");
        return;
    L8:
        ByteString$$ExternalSyntheticBUOutline0.m("Please set left drawable using R.attr#chipIcon.");
    }

    @Override // android.view.View
    public void setElevation(float r1) {
        super.setElevation(r1);
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setElevation(r1);
        return;
    }

    @Override // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt r2) {
        if (this.chipDrawable != null) goto L6;
        return;
    L6:
        if (r2 == TextUtils.TruncateAt.MARQUEE) goto L11;
        super.setEllipsize(r2);
        ChipDrawable r1 = this.chipDrawable;
        if (r1 == null) goto L13;
        r1.setEllipsize(r2);
        return;
    L13:
        return;
    L11:
        ByteString$$ExternalSyntheticBUOutline0.m("Text within a chip are not allowed to scroll.");
    }

    public void setEnsureMinTouchTargetSize(boolean r1) {
        this.ensureMinTouchTargetSize = r1;
        ensureAccessibleTouchTarget(this.minTouchTargetSize);
    }

    @Override // android.widget.TextView
    public boolean setFontVariationSettings(String r2) {
        super.setFontVariationSettings(r2);
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setFontVariationSettings(r2);
        updateTextPaintDrawState();
        return true;
    L6:
        return false;
    }

    @Override // android.widget.TextView
    public void setGravity(int r2) {
        if (r2 == 8388627) goto L6;
        Log.w(TAG, "Chip text must be vertically center and start aligned");
        return;
    L6:
        super.setGravity(r2);
    }

    public void setHideMotionSpec(MotionSpec r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setHideMotionSpec(r1);
        return;
    }

    public void setHideMotionSpecResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setHideMotionSpecResource(r1);
        return;
    }

    public void setIconEndPadding(float r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setIconEndPadding(r1);
        return;
    }

    public void setIconEndPaddingResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setIconEndPaddingResource(r1);
        return;
    }

    public void setIconStartPadding(float r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setIconStartPadding(r1);
        return;
    }

    public void setIconStartPaddingResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setIconStartPaddingResource(r1);
        return;
    }

    @Override // com.google.android.material.internal.MaterialCheckable
    public void setInternalOnCheckedChangeListener(MaterialCheckable.OnCheckedChangeListener<Chip> r1) {
        this.onCheckedChangeListenerInternal = r1;
    }

    @Override // android.view.View
    public void setLayoutDirection(int r2) {
        if (this.chipDrawable != null) goto L5;
        return;
    L5:
        super.setLayoutDirection(r2);
    }

    @Override // android.widget.TextView
    public void setLines(int r2) {
        if (r2 > 1) goto L6;
        super.setLines(r2);
        return;
    L6:
        ByteString$$ExternalSyntheticBUOutline0.m("Chip does not support multi-line text");
    }

    @Override // android.widget.TextView
    public void setMaxLines(int r2) {
        if (r2 > 1) goto L6;
        super.setMaxLines(r2);
        return;
    L6:
        ByteString$$ExternalSyntheticBUOutline0.m("Chip does not support multi-line text");
    }

    @Override // android.widget.TextView
    public void setMaxWidth(int r1) {
        super.setMaxWidth(r1);
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setMaxWidth(r1);
        return;
    }

    @Override // android.widget.TextView
    public void setMinLines(int r2) {
        if (r2 > 1) goto L6;
        super.setMinLines(r2);
        return;
    L6:
        ByteString$$ExternalSyntheticBUOutline0.m("Chip does not support multi-line text");
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener r1) {
        this.onCheckedChangeListener = r1;
    }

    public void setOnCloseIconClickListener(View.OnClickListener r1) {
        this.onCloseIconClickListener = r1;
        updateAccessibilityDelegate();
    }

    public void setRippleColor(ColorStateList r2) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setRippleColor(r2);
    L6:
        if (this.chipDrawable.getUseCompatRipple() == true) goto L9;
        updateFrameworkRippleBackground();
        return;
    }

    public void setRippleColorResource(int r2) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L8;
        r0.setRippleColorResource(r2);
        if (this.chipDrawable.getUseCompatRipple() == true) goto L9;
        updateFrameworkRippleBackground();
        return;
    L9:
        return;
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel r1) {
        this.chipDrawable.setShapeAppearanceModel(r1);
    }

    public void setShowMotionSpec(MotionSpec r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setShowMotionSpec(r1);
        return;
    }

    public void setShowMotionSpecResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setShowMotionSpecResource(r1);
        return;
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean r1) {
        if (r1 == false) goto L5;
        super.setSingleLine(r1);
        return;
    L5:
        ByteString$$ExternalSyntheticBUOutline0.m("Chip does not support multi-line text");
    }

    @Override // android.widget.TextView
    public void setText(CharSequence r2, TextView.BufferType r3) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L16;
        if (r2 != null) goto L8;
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        if (r0.shouldDrawText() == false) goto L10;
        CharSequence r02 = null;
    L11:
        super.setText(r02, r3);
        ChipDrawable r1 = this.chipDrawable;
        if (r1 == null) goto L15;
        r1.setText(r2);
        return;
    L15:
        return;
    L10:
        r02 = r2;
        goto L11
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int r2) {
        super.setTextAppearance(r2);
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L5;
        r0.setTextAppearanceResource(r2);
    L5:
        updateTextPaintDrawState();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context r1, int r2) {
        super.setTextAppearance(r1, r2);
        ChipDrawable r12 = this.chipDrawable;
        if (r12 == null) goto L5;
        r12.setTextAppearanceResource(r2);
    L5:
        updateTextPaintDrawState();
    }

    public void setTextAppearance(TextAppearance r2) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L5;
        r0.setTextAppearance(r2);
    L5:
        updateTextPaintDrawState();
    }

    public void setTextAppearanceResource(int r2) {
        setTextAppearance(getContext(), r2);
    }

    public void setTextEndPadding(float r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setTextEndPadding(r1);
        return;
    }

    public void setTextEndPaddingResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setTextEndPaddingResource(r1);
        return;
    }

    @Override // android.widget.TextView
    public void setTextSize(int r3, float r4) {
        super.setTextSize(r3, r4);
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L5;
        r0.setTextSize(TypedValue.applyDimension(r3, r4, getResources().getDisplayMetrics()));
    L5:
        updateTextPaintDrawState();
    }

    public void setTextStartPadding(float r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setTextStartPadding(r1);
        return;
    }

    public void setTextStartPaddingResource(int r1) {
        ChipDrawable r0 = this.chipDrawable;
        if (r0 == null) goto L6;
        r0.setTextStartPaddingResource(r1);
        return;
    }

    public boolean shouldEnsureMinTouchTargetSize() {
        return this.ensureMinTouchTargetSize;
    }
}
