package com.google.android.material.textfield;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.TextViewCompat;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Iterator;
import java.util.LinkedHashSet;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes5.dex */
class EndCompoundLayout extends LinearLayout {
    private final AccessibilityManager accessibilityManager;
    private EditText editText;
    private final TextWatcher editTextWatcher;
    private final LinkedHashSet<TextInputLayout.OnEndIconChangedListener> endIconChangedListeners;
    private final EndIconDelegates endIconDelegates;
    private final FrameLayout endIconFrame;
    private int endIconMinSize;
    private int endIconMode;
    private View.OnLongClickListener endIconOnLongClickListener;
    private ImageView.ScaleType endIconScaleType;
    private ColorStateList endIconTintList;
    private PorterDuff.Mode endIconTintMode;
    private final CheckableImageButton endIconView;
    private View.OnLongClickListener errorIconOnLongClickListener;
    private ColorStateList errorIconTintList;
    private PorterDuff.Mode errorIconTintMode;
    private final CheckableImageButton errorIconView;
    private boolean hintExpanded;
    private final TextInputLayout.OnEditTextAttachedListener onEditTextAttachedListener;
    private CharSequence suffixText;
    private final TextView suffixTextView;
    final TextInputLayout textInputLayout;
    private AccessibilityManager.TouchExplorationStateChangeListener touchExplorationStateChangeListener;

    /* renamed from: com.google.android.material.textfield.EndCompoundLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends TextWatcherAdapter {
        final /* synthetic */ EndCompoundLayout this$0;

        public AnonymousClass1(EndCompoundLayout r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
            this.this$0.getEndIconDelegate().afterEditTextChanged(r1);
        }

        @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            this.this$0.getEndIconDelegate().beforeEditTextChanged(r1, r2, r3, r4);
        }
    }

    /* renamed from: com.google.android.material.textfield.EndCompoundLayout$2, reason: invalid class name */
    public class AnonymousClass2 implements TextInputLayout.OnEditTextAttachedListener {
        final /* synthetic */ EndCompoundLayout this$0;

        public AnonymousClass2(EndCompoundLayout r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.textfield.TextInputLayout.OnEditTextAttachedListener
        public void onEditTextAttached(TextInputLayout r3) {
            if (EndCompoundLayout.access$000(this.this$0) != r3.getEditText()) goto L6;
            return;
        L6:
            if (EndCompoundLayout.access$000(this.this$0) == null) goto L10;
            EndCompoundLayout.access$000(this.this$0).removeTextChangedListener(EndCompoundLayout.access$100(this.this$0));
            if (EndCompoundLayout.access$000(this.this$0).getOnFocusChangeListener() != this.this$0.getEndIconDelegate().getOnEditTextFocusChangeListener()) goto L10;
            EndCompoundLayout.access$000(this.this$0).setOnFocusChangeListener(null);
        L10:
            EndCompoundLayout.access$002(this.this$0, r3.getEditText());
            if (EndCompoundLayout.access$000(this.this$0) == null) goto L13;
            EndCompoundLayout.access$000(this.this$0).addTextChangedListener(EndCompoundLayout.access$100(this.this$0));
        L13:
            this.this$0.getEndIconDelegate().onEditTextAttached(EndCompoundLayout.access$000(this.this$0));
            EndCompoundLayout r2 = this.this$0;
            EndCompoundLayout.access$200(r2, r2.getEndIconDelegate());
        }
    }

    /* renamed from: com.google.android.material.textfield.EndCompoundLayout$3, reason: invalid class name */
    public class AnonymousClass3 implements View.OnAttachStateChangeListener {
        final /* synthetic */ EndCompoundLayout this$0;

        public AnonymousClass3(EndCompoundLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
            EndCompoundLayout.access$300(this.this$0);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
            EndCompoundLayout.access$400(this.this$0);
        }
    }

    public static class EndIconDelegates {
        private final int customEndIconDrawableId;
        private final SparseArray<EndIconDelegate> delegates;
        private final EndCompoundLayout endLayout;
        private final int passwordIconDrawableId;

        public EndIconDelegates(EndCompoundLayout r2, TintTypedArray r3) {
            this.delegates = new SparseArray();
            this.endLayout = r2;
            this.customEndIconDrawableId = r3.getResourceId(R.styleable.TextInputLayout_endIconDrawable, 0);
            this.passwordIconDrawableId = r3.getResourceId(R.styleable.TextInputLayout_passwordToggleDrawable, 0);
        }

        public static /* synthetic */ int access$500(EndIconDelegates r0) {
            return r0.customEndIconDrawableId;
        }

        private EndIconDelegate create(int r2) {
            if (r2 == (-1)) goto L22;
            if (r2 == 0) goto L20;
            if (r2 == 1) goto L18;
            if (r2 == 2) goto L16;
            if (r2 == 3) goto L12;
            CharCodeKt$$ExternalSyntheticBUOutline0.m("Invalid end icon mode: ", r2);
            return null;
        L12:
            return new DropdownMenuEndIconDelegate(this.endLayout);
        L16:
            return new ClearTextEndIconDelegate(this.endLayout);
        L18:
            return new PasswordToggleEndIconDelegate(this.endLayout, this.passwordIconDrawableId);
        L20:
            return new NoEndIconDelegate(this.endLayout);
        L22:
            return new CustomEndIconDelegate(this.endLayout);
        }

        public EndIconDelegate get(int r2) {
            EndIconDelegate r0 = this.delegates.get(r2);
            if (r0 != null) goto L6;
            EndIconDelegate r02 = create(r2);
            this.delegates.append(r2, r02);
            return r02;
        L6:
            return r0;
        }
    }

    public static /* synthetic */ void $r8$lambda$IztteKYnB40iROh4NmVj4elY4VI(EndCompoundLayout r0, View r1, boolean r2) {
        CheckableImageButton r12 = r0.errorIconView;
        IconHelper.updateIconTooltip(r12, r0.errorIconOnLongClickListener, r12.getContentDescription());
    }

    public static /* synthetic */ void $r8$lambda$O80Xslln1syr7NPnYtcd_S2pYUo(EndCompoundLayout r0, View r1, boolean r2) {
        IconHelper.updateIconTooltip(r0.endIconView, r0.endIconOnLongClickListener, r0.getEndIconContentDescription());
    }

    public EndCompoundLayout(TextInputLayout r7, TintTypedArray r8) {
        super(r7.getContext());
        this.endIconMode = 0;
        this.endIconChangedListeners = new LinkedHashSet();
        this.editTextWatcher = new AnonymousClass1(this);
        AnonymousClass2 r1 = new AnonymousClass2(this);
        this.onEditTextAttachedListener = r1;
        this.accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        this.textInputLayout = r7;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388613));
        FrameLayout r0 = new FrameLayout(getContext());
        this.endIconFrame = r0;
        r0.setVisibility(8);
        r0.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        LayoutInflater r2 = LayoutInflater.from(getContext());
        CheckableImageButton r3 = createIconView(this, r2, R.id.text_input_error_icon);
        this.errorIconView = r3;
        CheckableImageButton r22 = createIconView(r0, r2, R.id.text_input_end_icon);
        this.endIconView = r22;
        this.endIconDelegates = new EndIconDelegates(this, r8);
        AppCompatTextView r4 = new AppCompatTextView(getContext());
        this.suffixTextView = r4;
        initErrorIconView(r8);
        initEndIconView(r8);
        initSuffixTextView(r8);
        r0.addView(r22);
        addView(r4);
        addView(r0);
        addView(r3);
        r3.setOnFocusableChangedListener(new EndCompoundLayout$$ExternalSyntheticLambda0(this));
        r22.setOnFocusableChangedListener(new EndCompoundLayout$$ExternalSyntheticLambda1(this));
        r7.addOnEditTextAttachedListener(r1);
        addOnAttachStateChangeListener(new AnonymousClass3(this));
    }

    public static /* synthetic */ EditText access$000(EndCompoundLayout r0) {
        return r0.editText;
    }

    public static /* synthetic */ EditText access$002(EndCompoundLayout r0, EditText r1) {
        r0.editText = r1;
        return r1;
    }

    public static /* synthetic */ TextWatcher access$100(EndCompoundLayout r0) {
        return r0.editTextWatcher;
    }

    public static /* synthetic */ void access$200(EndCompoundLayout r0, EndIconDelegate r1) {
        r0.setOnFocusChangeListenersIfNeeded(r1);
    }

    public static /* synthetic */ void access$300(EndCompoundLayout r0) {
        r0.addTouchExplorationStateChangeListenerIfNeeded();
    }

    public static /* synthetic */ void access$400(EndCompoundLayout r0) {
        r0.removeTouchExplorationStateChangeListenerIfNeeded();
    }

    private void addTouchExplorationStateChangeListenerIfNeeded() {
        if (this.touchExplorationStateChangeListener != null) goto L5;
        return;
    L5:
        if (this.accessibilityManager != null) goto L7;
        return;
    L7:
        if (isAttachedToWindow() == false) goto L12;
        this.accessibilityManager.addTouchExplorationStateChangeListener(this.touchExplorationStateChangeListener);
        return;
    }

    private CheckableImageButton createIconView(ViewGroup r3, LayoutInflater r4, int r5) {
        CheckableImageButton r32 = (CheckableImageButton) r4.inflate(R.layout.design_text_input_end_icon, r3, false);
        r32.setId(r5);
        IconHelper.setCompatRippleBackgroundIfNeeded(r32);
        if (MaterialResources.isFontScaleAtLeast1_3(getContext()) == false) goto L5;
        ((ViewGroup.MarginLayoutParams) r32.getLayoutParams()).setMarginStart(0);
    L5:
        return r32;
    }

    private void dispatchOnEndIconChanged(int r4) {
        Iterator<TextInputLayout.OnEndIconChangedListener> r0 = this.endIconChangedListeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().onEndIconChanged(this.textInputLayout, r4);
        goto L4
    }

    private int getIconResId(EndIconDelegate r1) {
        int r0 = EndIconDelegates.access$500(this.endIconDelegates);
        if (r0 == 0) goto L5;
        return r0;
    L5:
        return r1.getIconDrawableResId();
    }

    private void initEndIconView(TintTypedArray r6) {
        if (r6.hasValue(R.styleable.TextInputLayout_passwordToggleEnabled) == true) goto L11;
        if (r6.hasValue(R.styleable.TextInputLayout_endIconTint) == false) goto L8;
        this.endIconTintList = MaterialResources.getColorStateList(getContext(), r6, R.styleable.TextInputLayout_endIconTint);
    L8:
        if (r6.hasValue(R.styleable.TextInputLayout_endIconTintMode) == false) goto L11;
        this.endIconTintMode = ViewUtils.parseTintMode(r6.getInt(R.styleable.TextInputLayout_endIconTintMode, -1), null);
    L11:
        if (r6.hasValue(R.styleable.TextInputLayout_endIconMode) == false) goto L17;
        setEndIconMode(r6.getInt(R.styleable.TextInputLayout_endIconMode, 0));
        if (r6.hasValue(R.styleable.TextInputLayout_endIconContentDescription) == false) goto L15;
        setEndIconContentDescription(r6.getText(R.styleable.TextInputLayout_endIconContentDescription));
    L15:
        setEndIconCheckable(r6.getBoolean(R.styleable.TextInputLayout_endIconCheckable, true));
    L25:
        setEndIconMinSize(r6.getDimensionPixelSize(R.styleable.TextInputLayout_endIconMinSize, getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size)));
        if (r6.hasValue(R.styleable.TextInputLayout_endIconScaleType) == false) goto L29;
        setEndIconScaleType(IconHelper.convertScaleType(r6.getInt(R.styleable.TextInputLayout_endIconScaleType, -1)));
        return;
    L29:
        return;
    L17:
        if (r6.hasValue(R.styleable.TextInputLayout_passwordToggleEnabled) == false) goto L25;
        if (r6.hasValue(R.styleable.TextInputLayout_passwordToggleTint) == false) goto L22;
        this.endIconTintList = MaterialResources.getColorStateList(getContext(), r6, R.styleable.TextInputLayout_passwordToggleTint);
    L22:
        if (r6.hasValue(R.styleable.TextInputLayout_passwordToggleTintMode) == false) goto L24;
        this.endIconTintMode = ViewUtils.parseTintMode(r6.getInt(R.styleable.TextInputLayout_passwordToggleTintMode, -1), null);
    L24:
        setEndIconMode(r6.getBoolean(R.styleable.TextInputLayout_passwordToggleEnabled, false) ? 1 : 0);
        setEndIconContentDescription(r6.getText(R.styleable.TextInputLayout_passwordToggleContentDescription));
        goto L25
    }

    private void initErrorIconView(TintTypedArray r3) {
        if (r3.hasValue(R.styleable.TextInputLayout_errorIconTint) == false) goto L6;
        this.errorIconTintList = MaterialResources.getColorStateList(getContext(), r3, R.styleable.TextInputLayout_errorIconTint);
    L6:
        if (r3.hasValue(R.styleable.TextInputLayout_errorIconTintMode) == false) goto L9;
        this.errorIconTintMode = ViewUtils.parseTintMode(r3.getInt(R.styleable.TextInputLayout_errorIconTintMode, -1), null);
    L9:
        if (r3.hasValue(R.styleable.TextInputLayout_errorIconDrawable) == false) goto L11;
        setErrorIconDrawable(r3.getDrawable(R.styleable.TextInputLayout_errorIconDrawable));
    L11:
        this.errorIconView.setContentDescription(getResources().getText(R.string.error_icon_content_description));
        this.errorIconView.setImportantForAccessibility(2);
        this.errorIconView.setClickable(false);
        this.errorIconView.setPressable(false);
        this.errorIconView.setCheckable(false);
        this.errorIconView.setFocusable(false);
    }

    private void initSuffixTextView(TintTypedArray r5) {
        this.suffixTextView.setVisibility(8);
        this.suffixTextView.setId(R.id.textinput_suffix_text);
        this.suffixTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, 80.0f));
        this.suffixTextView.setAccessibilityLiveRegion(1);
        setSuffixTextAppearance(r5.getResourceId(R.styleable.TextInputLayout_suffixTextAppearance, 0));
        if (r5.hasValue(R.styleable.TextInputLayout_suffixTextColor) == false) goto L5;
        setSuffixTextColor(r5.getColorStateList(R.styleable.TextInputLayout_suffixTextColor));
    L5:
        setSuffixText(r5.getText(R.styleable.TextInputLayout_suffixText));
    }

    private void removeTouchExplorationStateChangeListenerIfNeeded() {
        AccessibilityManager.TouchExplorationStateChangeListener r0 = this.touchExplorationStateChangeListener;
        if (r0 == null) goto L8;
        AccessibilityManager r1 = this.accessibilityManager;
        if (r1 == null) goto L9;
        r1.removeTouchExplorationStateChangeListener(r0);
        return;
    L9:
        return;
    }

    private void setOnFocusChangeListenersIfNeeded(EndIconDelegate r3) {
        if (this.editText != null) goto L6;
        return;
    L6:
        if (r3.getOnEditTextFocusChangeListener() == null) goto L9;
        this.editText.setOnFocusChangeListener(r3.getOnEditTextFocusChangeListener());
    L9:
        if (r3.getOnIconViewFocusChangeListener() == null) goto L12;
        this.endIconView.setOnFocusChangeListener(r3.getOnIconViewFocusChangeListener());
        return;
    }

    private void setUpDelegate(EndIconDelegate r1) {
        r1.setUp();
        this.touchExplorationStateChangeListener = r1.getTouchExplorationStateChangeListener();
        addTouchExplorationStateChangeListenerIfNeeded();
    }

    private void tearDownDelegate(EndIconDelegate r2) {
        removeTouchExplorationStateChangeListenerIfNeeded();
        this.touchExplorationStateChangeListener = null;
        r2.tearDown();
    }

    private void tintEndIconOnError(boolean r3) {
        if (r3 == true) goto L4;
    L7:
        IconHelper.applyIconTint(this.textInputLayout, this.endIconView, this.endIconTintList, this.endIconTintMode);
        return;
    L4:
        if (getEndIconDrawable() == null) goto L7;
        Drawable r32 = DrawableCompat.wrap(getEndIconDrawable()).mutate();
        r32.setTint(this.textInputLayout.getErrorCurrentTextColors());
        this.endIconView.setImageDrawable(r32);
    }

    private void updateEndLayoutVisibility() {
        FrameLayout r0 = this.endIconFrame;
        int r2 = 8;
        if (this.endIconView.getVisibility() == 0) goto L5;
    L7:
        int r1 = 8;
    L8:
        r0.setVisibility(r1);
        if (this.suffixText != null) goto L11;
    L13:
        boolean r02 = 8;
    L15:
        if (isEndIconVisible() == false) goto L17;
    L19:
        r2 = 0;
    L20:
        setVisibility(r2);
        return;
    L17:
        if (isErrorIconVisible() == true) goto L19;
        if (r02 == true) goto L20;
    L11:
        if (this.hintExpanded == true) goto L13;
        r02 = false;
        goto L15
    L5:
        if (isErrorIconVisible() == true) goto L7;
        r1 = 0;
        goto L8
    }

    private void updateErrorIconVisibility() {
        int r1 = 0;
        if (getErrorIconDrawable() != null) goto L5;
    L9:
        boolean r0 = false;
    L10:
        CheckableImageButton r2 = this.errorIconView;
        if (r0 == true) goto L14;
        r1 = 8;
    L14:
        r2.setVisibility(r1);
        updateEndLayoutVisibility();
        updateSuffixTextViewPadding();
        if (hasEndIcon() == true) goto L18;
        this.textInputLayout.updateDummyDrawables();
        return;
    L18:
        return;
    L5:
        if (this.textInputLayout.isErrorEnabled() == false) goto L9;
        if (this.textInputLayout.shouldShowError() == false) goto L9;
        r0 = true;
        goto L10
    }

    private void updateSuffixTextVisibility() {
        int r0 = this.suffixTextView.getVisibility();
        boolean r2 = false;
        if (this.suffixText != null) goto L5;
    L7:
        int r1 = 8;
    L8:
        if (r0 == r1) goto L13;
        EndIconDelegate r02 = getEndIconDelegate();
        if (r1 != 0) goto L12;
        r2 = true;
    L12:
        r02.onSuffixVisibilityChanged(r2);
    L13:
        updateEndLayoutVisibility();
        this.suffixTextView.setVisibility(r1);
        this.textInputLayout.updateDummyDrawables();
        return;
    L5:
        if (this.hintExpanded == true) goto L7;
        r1 = 0;
        goto L8
    }

    public void addOnEndIconChangedListener(TextInputLayout.OnEndIconChangedListener r1) {
        this.endIconChangedListeners.add(r1);
    }

    public void checkEndIcon() {
        this.endIconView.performClick();
        this.endIconView.jumpDrawablesToCurrentState();
    }

    public void clearOnEndIconChangedListeners() {
        this.endIconChangedListeners.clear();
    }

    public CheckableImageButton getCurrentEndIconView() {
        if (isErrorIconVisible() == false) goto L7;
        return this.errorIconView;
    L7:
        if (hasEndIcon() == true) goto L9;
        return null;
    L9:
        if (isEndIconVisible() == true) goto L11;
        return null;
    L11:
        return this.endIconView;
    }

    public CharSequence getEndIconContentDescription() {
        return this.endIconView.getContentDescription();
    }

    public EndIconDelegate getEndIconDelegate() {
        return this.endIconDelegates.get(this.endIconMode);
    }

    public Drawable getEndIconDrawable() {
        return this.endIconView.getDrawable();
    }

    public int getEndIconMinSize() {
        return this.endIconMinSize;
    }

    public int getEndIconMode() {
        return this.endIconMode;
    }

    public ImageView.ScaleType getEndIconScaleType() {
        return this.endIconScaleType;
    }

    public CheckableImageButton getEndIconView() {
        return this.endIconView;
    }

    public Drawable getErrorIconDrawable() {
        return this.errorIconView.getDrawable();
    }

    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.endIconView.getContentDescription();
    }

    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.endIconView.getDrawable();
    }

    public CharSequence getSuffixText() {
        return this.suffixText;
    }

    public ColorStateList getSuffixTextColor() {
        return this.suffixTextView.getTextColors();
    }

    public int getSuffixTextEndOffset() {
        if (isEndIconVisible() == false) goto L5;
    L8:
        int r0 = this.endIconView.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) this.endIconView.getLayoutParams()).getMarginStart();
    L10:
        return (getPaddingEnd() + this.suffixTextView.getPaddingEnd()) + r0;
    L5:
        if (isErrorIconVisible() == true) goto L8;
        r0 = 0;
        goto L10
    }

    public TextView getSuffixTextView() {
        return this.suffixTextView;
    }

    public boolean hasEndIcon() {
        if (this.endIconMode == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isEndIconCheckable() {
        return this.endIconView.isCheckable();
    }

    public boolean isEndIconChecked() {
        if (hasEndIcon() == true) goto L5;
        return false;
    L5:
        if (this.endIconView.isChecked() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isEndIconVisible() {
        if (this.endIconFrame.getVisibility() == 0) goto L5;
        return false;
    L5:
        if (this.endIconView.getVisibility() != 0) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isErrorIconVisible() {
        if (this.errorIconView.getVisibility() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isPasswordVisibilityToggleEnabled() {
        if (this.endIconMode != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public void onHintStateChanged(boolean r1) {
        this.hintExpanded = r1;
        updateSuffixTextVisibility();
    }

    public void onTextInputBoxStateUpdated() {
        updateErrorIconVisibility();
        refreshErrorIconDrawableState();
        refreshEndIconDrawableState();
        if (getEndIconDelegate().shouldTintIconOnError() == false) goto L6;
        tintEndIconOnError(this.textInputLayout.shouldShowError());
        return;
    }

    public void refreshEndIconDrawableState() {
        IconHelper.refreshIconDrawableState(this.textInputLayout, this.endIconView, this.endIconTintList);
    }

    public void refreshErrorIconDrawableState() {
        IconHelper.refreshIconDrawableState(this.textInputLayout, this.errorIconView, this.errorIconTintList);
    }

    public void refreshIconState(boolean r5) {
        EndIconDelegate r0 = getEndIconDelegate();
        boolean r2 = true;
        if (r0.isIconCheckable() == false) goto L7;
        boolean r1 = this.endIconView.isChecked();
        if (r1 == r0.isIconChecked()) goto L7;
        this.endIconView.setChecked(!r1);
        boolean r12 = true;
    L9:
        if (r0.isIconActivable() == false) goto L13;
        boolean r3 = this.endIconView.isActivated();
        if (r3 == r0.isIconActivated()) goto L13;
        setEndIconActivated(!r3);
    L14:
        if (r5 == true) goto L18;
        if (r2 == true) goto L18;
        return;
    L18:
        refreshEndIconDrawableState();
        return;
    L13:
        r2 = r12;
    L7:
        r12 = false;
        goto L9
    }

    public void removeOnEndIconChangedListener(TextInputLayout.OnEndIconChangedListener r1) {
        this.endIconChangedListeners.remove(r1);
    }

    public void setEndIconActivated(boolean r1) {
        this.endIconView.setActivated(r1);
    }

    public void setEndIconCheckable(boolean r1) {
        this.endIconView.setCheckable(r1);
    }

    public void setEndIconContentDescription(int r2) {
        if (r2 == 0) goto L4;
        CharSequence r22 = getResources().getText(r2);
    L5:
        setEndIconContentDescription(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    public void setEndIconContentDescription(CharSequence r2) {
        if (getEndIconContentDescription() == r2) goto L6;
        this.endIconView.setContentDescription(r2);
        IconHelper.updateIconTooltip(this.endIconView, this.endIconOnLongClickListener, r2);
        return;
    }

    public void setEndIconDrawable(int r2) {
        if (r2 == 0) goto L4;
        Drawable r22 = AppCompatResources.getDrawable(getContext(), r2);
    L5:
        setEndIconDrawable(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    public void setEndIconDrawable(Drawable r4) {
        this.endIconView.setImageDrawable(r4);
        if (r4 == null) goto L6;
        IconHelper.applyIconTint(this.textInputLayout, this.endIconView, this.endIconTintList, this.endIconTintMode);
        refreshEndIconDrawableState();
        return;
    }

    public void setEndIconMinSize(int r2) {
        if (r2 >= 0) goto L4;
        f$$ExternalSyntheticBUOutline1.m("endIconSize cannot be less than 0");
        return;
    L4:
        if (r2 == this.endIconMinSize) goto L9;
        this.endIconMinSize = r2;
        IconHelper.setIconMinSize(this.endIconView, r2);
        IconHelper.setIconMinSize(this.errorIconView, r2);
        return;
    }

    public void setEndIconMode(int r5) {
        if (this.endIconMode != r5) goto L5;
        return;
    L5:
        tearDownDelegate(getEndIconDelegate());
        int r0 = this.endIconMode;
        this.endIconMode = r5;
        dispatchOnEndIconChanged(r0);
        if (r5 == 0) goto L8;
        boolean r1 = true;
    L9:
        setEndIconVisible(r1);
        EndIconDelegate r12 = getEndIconDelegate();
        setEndIconDrawable(getIconResId(r12));
        setEndIconCheckable(r12.isIconCheckable());
        if (r12.isBoxBackgroundModeSupported(this.textInputLayout.getBoxBackgroundMode()) == false) goto L17;
        setUpDelegate(r12);
        setEndIconOnClickListener(r12.getOnIconClickListener());
        setEndIconContentDescription(r12.getIconContentDescriptionResId());
        EditText r52 = this.editText;
        if (r52 == null) goto L14;
        r12.onEditTextAttached(r52);
        setOnFocusChangeListenersIfNeeded(r12);
    L14:
        IconHelper.applyIconTint(this.textInputLayout, this.endIconView, this.endIconTintList, this.endIconTintMode);
        refreshIconState(true);
        return;
    L17:
        throw new IllegalStateException("The current box background mode " + this.textInputLayout.getBoxBackgroundMode() + " is not supported by the end icon mode " + r5);
    L8:
        r1 = false;
        goto L9
    }

    public void setEndIconOnClickListener(View.OnClickListener r2) {
        IconHelper.setIconOnClickListener(this.endIconView, r2, this.endIconOnLongClickListener);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener r1) {
        this.endIconOnLongClickListener = r1;
        IconHelper.setIconOnLongClickListener(this.endIconView, r1);
    }

    public void setEndIconScaleType(ImageView.ScaleType r2) {
        this.endIconScaleType = r2;
        IconHelper.setIconScaleType(this.endIconView, r2);
        IconHelper.setIconScaleType(this.errorIconView, r2);
    }

    public void setEndIconTintList(ColorStateList r3) {
        if (this.endIconTintList == r3) goto L6;
        this.endIconTintList = r3;
        IconHelper.applyIconTint(this.textInputLayout, this.endIconView, r3, this.endIconTintMode);
        return;
    }

    public void setEndIconTintMode(PorterDuff.Mode r3) {
        if (this.endIconTintMode == r3) goto L6;
        this.endIconTintMode = r3;
        IconHelper.applyIconTint(this.textInputLayout, this.endIconView, this.endIconTintList, r3);
        return;
    }

    public void setEndIconVisible(boolean r2) {
        if (isEndIconVisible() == r2) goto L16;
        if (r2 == false) goto L6;
    L10:
        CheckableImageButton r0 = this.endIconView;
        if (r2 == false) goto L13;
        int r22 = 0;
    L14:
        r0.setVisibility(r22);
        updateEndLayoutVisibility();
        updateSuffixTextViewPadding();
        this.textInputLayout.updateDummyDrawables();
        return;
    L13:
        r22 = 8;
        goto L14
    L6:
        if (this.endIconView.hasFocus() == false) goto L10;
        EditText r02 = this.editText;
        if (r02 == null) goto L10;
        r02.requestFocus();
        goto L10
    }

    public void setErrorIconDrawable(int r2) {
        if (r2 == 0) goto L4;
        Drawable r22 = AppCompatResources.getDrawable(getContext(), r2);
    L5:
        setErrorIconDrawable(r22);
        refreshErrorIconDrawableState();
        return;
    L4:
        r22 = null;
        goto L5
    }

    public void setErrorIconDrawable(Drawable r3) {
        this.errorIconView.setImageDrawable(r3);
        updateErrorIconVisibility();
        IconHelper.applyIconTint(this.textInputLayout, this.errorIconView, this.errorIconTintList, this.errorIconTintMode);
    }

    public void setErrorIconOnClickListener(View.OnClickListener r2) {
        IconHelper.setIconOnClickListener(this.errorIconView, r2, this.errorIconOnLongClickListener);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener r1) {
        this.errorIconOnLongClickListener = r1;
        IconHelper.setIconOnLongClickListener(this.errorIconView, r1);
    }

    public void setErrorIconTintList(ColorStateList r3) {
        if (this.errorIconTintList == r3) goto L6;
        this.errorIconTintList = r3;
        IconHelper.applyIconTint(this.textInputLayout, this.errorIconView, r3, this.errorIconTintMode);
        return;
    }

    public void setErrorIconTintMode(PorterDuff.Mode r3) {
        if (this.errorIconTintMode == r3) goto L6;
        this.errorIconTintMode = r3;
        IconHelper.applyIconTint(this.textInputLayout, this.errorIconView, this.errorIconTintList, r3);
        return;
    }

    public void setPasswordVisibilityToggleContentDescription(int r2) {
        if (r2 == 0) goto L4;
        CharSequence r22 = getResources().getText(r2);
    L5:
        setPasswordVisibilityToggleContentDescription(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    public void setPasswordVisibilityToggleContentDescription(CharSequence r1) {
        this.endIconView.setContentDescription(r1);
    }

    public void setPasswordVisibilityToggleDrawable(int r2) {
        if (r2 == 0) goto L4;
        Drawable r22 = AppCompatResources.getDrawable(getContext(), r2);
    L5:
        setPasswordVisibilityToggleDrawable(r22);
        return;
    L4:
        r22 = null;
        goto L5
    }

    public void setPasswordVisibilityToggleDrawable(Drawable r1) {
        this.endIconView.setImageDrawable(r1);
    }

    public void setPasswordVisibilityToggleEnabled(boolean r3) {
        if (r3 == true) goto L4;
    L7:
        if (r3 == true) goto L10;
        setEndIconMode(0);
        return;
    L10:
        return;
    L4:
        if (this.endIconMode == 1) goto L7;
        setEndIconMode(1);
    }

    public void setPasswordVisibilityToggleTintList(ColorStateList r3) {
        this.endIconTintList = r3;
        IconHelper.applyIconTint(this.textInputLayout, this.endIconView, r3, this.endIconTintMode);
    }

    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode r3) {
        this.endIconTintMode = r3;
        IconHelper.applyIconTint(this.textInputLayout, this.endIconView, this.endIconTintList, r3);
    }

    public void setSuffixText(CharSequence r2) {
        if (TextUtils.isEmpty(r2) == false) goto L5;
        CharSequence r0 = null;
    L6:
        this.suffixText = r0;
        this.suffixTextView.setText(r2);
        updateSuffixTextVisibility();
        return;
    L5:
        r0 = r2;
        goto L6
    }

    public void setSuffixTextAppearance(int r1) {
        TextViewCompat.setTextAppearance(this.suffixTextView, r1);
    }

    public void setSuffixTextColor(ColorStateList r1) {
        this.suffixTextView.setTextColor(r1);
    }

    public void togglePasswordVisibilityToggle(boolean r3) {
        if (this.endIconMode != 1) goto L8;
        this.endIconView.performClick();
        if (r3 == false) goto L9;
        this.endIconView.jumpDrawablesToCurrentState();
        return;
    L9:
        return;
    }

    public void updateSuffixTextViewPadding() {
        if (this.textInputLayout.editText != null) goto L6;
        return;
    L6:
        if (isEndIconVisible() == false) goto L8;
    L11:
        int r0 = 0;
    L12:
        this.suffixTextView.setPaddingRelative(getContext().getResources().getDimensionPixelSize(R.dimen.material_input_text_to_prefix_suffix_padding), this.textInputLayout.editText.getPaddingTop(), r0, this.textInputLayout.editText.getPaddingBottom());
        return;
    L8:
        if (isErrorIconVisible() == true) goto L11;
        r0 = this.textInputLayout.editText.getPaddingEnd();
        goto L12
    }
}
