package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.text.PrecomputedTextCompat;
import androidx.core.widget.TextViewCompat;
import java.util.concurrent.Future;

/* loaded from: classes3.dex */
public class AppCompatTextView extends TextView {
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private AppCompatEmojiTextHelper mEmojiTextViewHelper;
    private boolean mIsSetTypefaceProcessing;
    private Future<PrecomputedTextCompat> mPrecomputedTextFuture;
    private SuperCaller mSuperCaller;
    private final AppCompatTextClassifierHelper mTextClassifierHelper;
    private final AppCompatTextHelper mTextHelper;

    public interface SuperCaller {
        int getAutoSizeMaxTextSize();

        int getAutoSizeMinTextSize();

        int getAutoSizeStepGranularity();

        int[] getAutoSizeTextAvailableSizes();

        int getAutoSizeTextType();

        TextClassifier getTextClassifier();

        void setAutoSizeTextTypeUniformWithConfiguration(int r1, int r2, int r3, int r4);

        void setAutoSizeTextTypeUniformWithPresetSizes(int[] r1, int r2);

        void setAutoSizeTextTypeWithDefaults(int r1);

        void setFirstBaselineToTopHeight(int r1);

        void setLastBaselineToBottomHeight(int r1);

        void setLineHeight(int r1, float r2);

        void setTextClassifier(TextClassifier r1);
    }

    public class SuperCallerApi26 implements SuperCaller {
        final /* synthetic */ AppCompatTextView this$0;

        public SuperCallerApi26(AppCompatTextView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public int getAutoSizeMaxTextSize() {
            return AppCompatTextView.access$001(this.this$0);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public int getAutoSizeMinTextSize() {
            return AppCompatTextView.access$101(this.this$0);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public int getAutoSizeStepGranularity() {
            return AppCompatTextView.access$201(this.this$0);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public int[] getAutoSizeTextAvailableSizes() {
            return AppCompatTextView.access$301(this.this$0);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public int getAutoSizeTextType() {
            return AppCompatTextView.access$401(this.this$0);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public TextClassifier getTextClassifier() {
            return AppCompatTextView.access$501(this.this$0);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void setAutoSizeTextTypeUniformWithConfiguration(int r1, int r2, int r3, int r4) {
            AppCompatTextView.access$601(this.this$0, r1, r2, r3, r4);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void setAutoSizeTextTypeUniformWithPresetSizes(int[] r1, int r2) {
            AppCompatTextView.access$701(this.this$0, r1, r2);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void setAutoSizeTextTypeWithDefaults(int r1) {
            AppCompatTextView.access$801(this.this$0, r1);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void setFirstBaselineToTopHeight(int r1) {
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void setLastBaselineToBottomHeight(int r1) {
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void setLineHeight(int r1, float r2) {
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void setTextClassifier(TextClassifier r1) {
            AppCompatTextView.access$901(this.this$0, r1);
        }
    }

    public class SuperCallerApi28 extends SuperCallerApi26 {
        final /* synthetic */ AppCompatTextView this$0;

        public SuperCallerApi28(AppCompatTextView r1) {
            this.this$0 = r1;
            super(r1);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCallerApi26, androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void setFirstBaselineToTopHeight(int r1) {
            AppCompatTextView.access$1001(this.this$0, r1);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCallerApi26, androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void setLastBaselineToBottomHeight(int r1) {
            AppCompatTextView.access$1101(this.this$0, r1);
        }
    }

    public class SuperCallerApi34 extends SuperCallerApi28 {
        final /* synthetic */ AppCompatTextView this$0;

        public SuperCallerApi34(AppCompatTextView r1) {
            this.this$0 = r1;
            super(r1);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCallerApi26, androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void setLineHeight(int r1, float r2) {
            AppCompatTextView.access$1201(this.this$0, r1, r2);
        }
    }

    public AppCompatTextView(Context r2) {
        this(r2, null);
    }

    public AppCompatTextView(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.textViewStyle);
    }

    public AppCompatTextView(Context r1, AttributeSet r2, int r3) {
        super(TintContextWrapper.wrap(r1), r2, r3);
        this.mIsSetTypefaceProcessing = false;
        this.mSuperCaller = null;
        ThemeUtils.checkAppCompatTheme(this, getContext());
        AppCompatBackgroundHelper r12 = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = r12;
        r12.loadFromAttributes(r2, r3);
        AppCompatTextHelper r13 = new AppCompatTextHelper(this);
        this.mTextHelper = r13;
        r13.loadFromAttributes(r2, r3);
        r13.applyCompoundDrawablesTints();
        this.mTextClassifierHelper = new AppCompatTextClassifierHelper(this);
        getEmojiTextViewHelper().loadFromAttributes(r2, r3);
    }

    public static /* synthetic */ int access$001(AppCompatTextView r0) {
        return super.getAutoSizeMaxTextSize();
    }

    public static /* synthetic */ void access$1001(AppCompatTextView r0, int r1) {
        super.setFirstBaselineToTopHeight(r1);
    }

    public static /* synthetic */ int access$101(AppCompatTextView r0) {
        return super.getAutoSizeMinTextSize();
    }

    public static /* synthetic */ void access$1101(AppCompatTextView r0, int r1) {
        super.setLastBaselineToBottomHeight(r1);
    }

    public static /* synthetic */ void access$1201(AppCompatTextView r0, int r1, float r2) {
        super.setLineHeight(r1, r2);
    }

    public static /* synthetic */ int access$201(AppCompatTextView r0) {
        return super.getAutoSizeStepGranularity();
    }

    public static /* synthetic */ int[] access$301(AppCompatTextView r0) {
        return super.getAutoSizeTextAvailableSizes();
    }

    public static /* synthetic */ int access$401(AppCompatTextView r0) {
        return super.getAutoSizeTextType();
    }

    public static /* synthetic */ TextClassifier access$501(AppCompatTextView r0) {
        return super.getTextClassifier();
    }

    public static /* synthetic */ void access$601(AppCompatTextView r0, int r1, int r2, int r3, int r4) {
        super.setAutoSizeTextTypeUniformWithConfiguration(r1, r2, r3, r4);
    }

    public static /* synthetic */ void access$701(AppCompatTextView r0, int[] r1, int r2) {
        super.setAutoSizeTextTypeUniformWithPresetSizes(r1, r2);
    }

    public static /* synthetic */ void access$801(AppCompatTextView r0, int r1) {
        super.setAutoSizeTextTypeWithDefaults(r1);
    }

    public static /* synthetic */ void access$901(AppCompatTextView r0, TextClassifier r1) {
        super.setTextClassifier(r1);
    }

    private void consumeTextFutureAndSetBlocking() {
        Future<PrecomputedTextCompat> r0 = this.mPrecomputedTextFuture;
        if (r0 != null) goto L8;
        return;
    L8:
        this.mPrecomputedTextFuture = null;     // Catch: Throwable -> L7
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r0.get());     // Catch: Throwable -> L7
        TextViewCompat.setPrecomputedText(this, null);     // Catch: Throwable -> L7
        return;
    }

    private AppCompatEmojiTextHelper getEmojiTextViewHelper() {
        if (this.mEmojiTextViewHelper != null) goto L6;
        this.mEmojiTextViewHelper = new AppCompatEmojiTextHelper(this);
    L6:
        return this.mEmojiTextViewHelper;
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L5;
        r0.applySupportBackgroundTint();
    L5:
        AppCompatTextHelper r1 = this.mTextHelper;
        if (r1 == null) goto L9;
        r1.applyCompoundDrawablesTints();
        return;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == true) goto L5;
        AppCompatTextHelper r1 = this.mTextHelper;
        if (r1 != null) goto L9;
        return -1;
    L9:
        return r1.getAutoSizeMaxTextSize();
    L5:
        return getSuperCaller().getAutoSizeMaxTextSize();
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == true) goto L5;
        AppCompatTextHelper r1 = this.mTextHelper;
        if (r1 != null) goto L9;
        return -1;
    L9:
        return r1.getAutoSizeMinTextSize();
    L5:
        return getSuperCaller().getAutoSizeMinTextSize();
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == true) goto L5;
        AppCompatTextHelper r1 = this.mTextHelper;
        if (r1 != null) goto L9;
        return -1;
    L9:
        return r1.getAutoSizeStepGranularity();
    L5:
        return getSuperCaller().getAutoSizeStepGranularity();
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == true) goto L5;
        AppCompatTextHelper r1 = this.mTextHelper;
        if (r1 == null) goto L11;
        return r1.getAutoSizeTextAvailableSizes();
    L11:
        return new int[0];
    L5:
        return getSuperCaller().getAutoSizeTextAvailableSizes();
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == true) goto L5;
        AppCompatTextHelper r2 = this.mTextHelper;
        if (r2 != null) goto L11;
        return 0;
    L11:
        return r2.getAutoSizeTextType();
    L5:
        if (getSuperCaller().getAutoSizeTextType() != 1) goto L7;
        return 1;
    L7:
        return 0;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return TextViewCompat.unwrapCustomSelectionActionModeCallback(super.getCustomSelectionActionModeCallback());
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return TextViewCompat.getFirstBaselineToTopHeight(this);
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return TextViewCompat.getLastBaselineToBottomHeight(this);
    }

    public SuperCaller getSuperCaller() {
        if (this.mSuperCaller != null) goto L14;
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 34) goto L8;
        this.mSuperCaller = new SuperCallerApi34(this);
        goto L14
    L8:
        if (r0 < 28) goto L11;
        this.mSuperCaller = new SuperCallerApi28(this);
        goto L14
    L11:
        if (r0 < 26) goto L14;
        this.mSuperCaller = new SuperCallerApi26(this);
    L14:
        return this.mSuperCaller;
    }

    public ColorStateList getSupportBackgroundTintList() {
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getSupportBackgroundTintList();
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getSupportBackgroundTintMode();
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.mTextHelper.getCompoundDrawableTintList();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.mTextHelper.getCompoundDrawableTintMode();
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        consumeTextFutureAndSetBlocking();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        if (Build.VERSION.SDK_INT >= 28) goto L10;
        AppCompatTextClassifierHelper r0 = this.mTextClassifierHelper;
        if (r0 == null) goto L10;
        return r0.getTextClassifier();
    L10:
        return getSuperCaller().getTextClassifier();
    }

    public PrecomputedTextCompat.Params getTextMetricsParamsCompat() {
        return TextViewCompat.getTextMetricsParams(this);
    }

    public boolean isEmojiCompatEnabled() {
        return getEmojiTextViewHelper().isEnabled();
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo r3) {
        InputConnection r0 = super.onCreateInputConnection(r3);
        this.mTextHelper.populateSurroundingTextIfNeeded(this, r0, r3);
        return AppCompatHintHelper.onCreateInputConnection(r0, r3, this);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        int r0 = Build.VERSION.SDK_INT;
        if (r0 >= 30) goto L5;
        return;
    L5:
        if (r0 < 33) goto L7;
        return;
    L7:
        if (onCheckIsTextEditor() == false) goto L12;
        ((InputMethodManager) getContext().getSystemService("input_method")).isActive(this);
        return;
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        AppCompatTextHelper r0 = this.mTextHelper;
        if (r0 == null) goto L6;
        r0.onLayout(r1, r2, r3, r4, r5);
        return;
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int r1, int r2) {
        consumeTextFutureAndSetBlocking();
        super.onMeasure(r1, r2);
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        super.onTextChanged(r1, r2, r3, r4);
        AppCompatTextHelper r12 = this.mTextHelper;
        if (r12 != null) goto L5;
        return;
    L5:
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == false) goto L7;
        return;
    L7:
        if (r12.isAutoSizeEnabled() == false) goto L12;
        this.mTextHelper.autoSizeText();
        return;
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean r1) {
        super.setAllCaps(r1);
        getEmojiTextViewHelper().setAllCaps(r1);
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int r2, int r3, int r4, int r5) {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == false) goto L6;
        getSuperCaller().setAutoSizeTextTypeUniformWithConfiguration(r2, r3, r4, r5);
        return;
    L6:
        AppCompatTextHelper r1 = this.mTextHelper;
        if (r1 == null) goto L10;
        r1.setAutoSizeTextTypeUniformWithConfiguration(r2, r3, r4, r5);
        return;
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] r2, int r3) {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == false) goto L6;
        getSuperCaller().setAutoSizeTextTypeUniformWithPresetSizes(r2, r3);
        return;
    L6:
        AppCompatTextHelper r1 = this.mTextHelper;
        if (r1 == null) goto L10;
        r1.setAutoSizeTextTypeUniformWithPresetSizes(r2, r3);
        return;
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int r2) {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == false) goto L6;
        getSuperCaller().setAutoSizeTextTypeWithDefaults(r2);
        return;
    L6:
        AppCompatTextHelper r1 = this.mTextHelper;
        if (r1 == null) goto L10;
        r1.setAutoSizeTextTypeWithDefaults(r2);
        return;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable r1) {
        super.setBackgroundDrawable(r1);
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L6;
        r0.onSetBackgroundDrawable(r1);
        return;
    }

    @Override // android.view.View
    public void setBackgroundResource(int r1) {
        super.setBackgroundResource(r1);
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L6;
        r0.onSetBackgroundResource(r1);
        return;
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable r1, Drawable r2, Drawable r3, Drawable r4) {
        super.setCompoundDrawables(r1, r2, r3, r4);
        AppCompatTextHelper r0 = this.mTextHelper;
        if (r0 == null) goto L6;
        r0.onSetCompoundDrawables();
        return;
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable r1, Drawable r2, Drawable r3, Drawable r4) {
        super.setCompoundDrawablesRelative(r1, r2, r3, r4);
        AppCompatTextHelper r0 = this.mTextHelper;
        if (r0 == null) goto L6;
        r0.onSetCompoundDrawables();
        return;
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int r3, int r4, int r5, int r6) {
        Context r0 = getContext();
        Drawable r1 = null;
        if (r3 == 0) goto L5;
        Drawable r32 = AppCompatResources.getDrawable(r0, r3);
    L6:
        if (r4 == 0) goto L8;
        Drawable r42 = AppCompatResources.getDrawable(r0, r4);
    L9:
        if (r5 == 0) goto L11;
        Drawable r52 = AppCompatResources.getDrawable(r0, r5);
    L12:
        if (r6 == 0) goto L14;
        r1 = AppCompatResources.getDrawable(r0, r6);
    L14:
        setCompoundDrawablesRelativeWithIntrinsicBounds(r32, r42, r52, r1);
        AppCompatTextHelper r2 = this.mTextHelper;
        if (r2 == null) goto L18;
        r2.onSetCompoundDrawables();
        return;
    L18:
        return;
    L11:
        r52 = null;
        goto L12
    L8:
        r42 = null;
        goto L9
    L5:
        r32 = null;
        goto L6
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable r1, Drawable r2, Drawable r3, Drawable r4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(r1, r2, r3, r4);
        AppCompatTextHelper r0 = this.mTextHelper;
        if (r0 == null) goto L6;
        r0.onSetCompoundDrawables();
        return;
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int r3, int r4, int r5, int r6) {
        Context r0 = getContext();
        Drawable r1 = null;
        if (r3 == 0) goto L5;
        Drawable r32 = AppCompatResources.getDrawable(r0, r3);
    L6:
        if (r4 == 0) goto L8;
        Drawable r42 = AppCompatResources.getDrawable(r0, r4);
    L9:
        if (r5 == 0) goto L11;
        Drawable r52 = AppCompatResources.getDrawable(r0, r5);
    L12:
        if (r6 == 0) goto L14;
        r1 = AppCompatResources.getDrawable(r0, r6);
    L14:
        setCompoundDrawablesWithIntrinsicBounds(r32, r42, r52, r1);
        AppCompatTextHelper r2 = this.mTextHelper;
        if (r2 == null) goto L18;
        r2.onSetCompoundDrawables();
        return;
    L18:
        return;
    L11:
        r52 = null;
        goto L12
    L8:
        r42 = null;
        goto L9
    L5:
        r32 = null;
        goto L6
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable r1, Drawable r2, Drawable r3, Drawable r4) {
        super.setCompoundDrawablesWithIntrinsicBounds(r1, r2, r3, r4);
        AppCompatTextHelper r0 = this.mTextHelper;
        if (r0 == null) goto L6;
        r0.onSetCompoundDrawables();
        return;
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback r1) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, r1));
    }

    public void setEmojiCompatEnabled(boolean r1) {
        getEmojiTextViewHelper().setEnabled(r1);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] r2) {
        super.setFilters(getEmojiTextViewHelper().getFilters(r2));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int r3) {
        if (Build.VERSION.SDK_INT < 28) goto L6;
        getSuperCaller().setFirstBaselineToTopHeight(r3);
        return;
    L6:
        TextViewCompat.setFirstBaselineToTopHeight(this, r3);
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int r3) {
        if (Build.VERSION.SDK_INT < 28) goto L6;
        getSuperCaller().setLastBaselineToBottomHeight(r3);
        return;
    L6:
        TextViewCompat.setLastBaselineToBottomHeight(this, r3);
    }

    @Override // android.widget.TextView
    public void setLineHeight(int r1) {
        TextViewCompat.setLineHeight(this, r1);
    }

    @Override // android.widget.TextView
    public void setLineHeight(int r3, float r4) {
        if (Build.VERSION.SDK_INT < 34) goto L6;
        getSuperCaller().setLineHeight(r3, r4);
        return;
    L6:
        TextViewCompat.setLineHeight(this, r3, r4);
    }

    public void setPrecomputedText(PrecomputedTextCompat r1) {
        TextViewCompat.setPrecomputedText(this, r1);
    }

    public void setSupportBackgroundTintList(ColorStateList r1) {
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L6;
        r0.setSupportBackgroundTintList(r1);
        return;
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode r1) {
        AppCompatBackgroundHelper r0 = this.mBackgroundTintHelper;
        if (r0 == null) goto L6;
        r0.setSupportBackgroundTintMode(r1);
        return;
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList r2) {
        this.mTextHelper.setCompoundDrawableTintList(r2);
        this.mTextHelper.applyCompoundDrawablesTints();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode r2) {
        this.mTextHelper.setCompoundDrawableTintMode(r2);
        this.mTextHelper.applyCompoundDrawablesTints();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context r1, int r2) {
        super.setTextAppearance(r1, r2);
        AppCompatTextHelper r0 = this.mTextHelper;
        if (r0 == null) goto L6;
        r0.onSetTextAppearance(r1, r2);
        return;
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier r3) {
        if (Build.VERSION.SDK_INT >= 28) goto L9;
        AppCompatTextClassifierHelper r0 = this.mTextClassifierHelper;
        if (r0 == null) goto L9;
        r0.setTextClassifier(r3);
        return;
    L9:
        getSuperCaller().setTextClassifier(r3);
    }

    public void setTextFuture(Future<PrecomputedTextCompat> r1) {
        this.mPrecomputedTextFuture = r1;
        if (r1 == null) goto L6;
        requestLayout();
        return;
    }

    public void setTextMetricsParamsCompat(PrecomputedTextCompat.Params r1) {
        TextViewCompat.setTextMetricsParams(this, r1);
    }

    @Override // android.widget.TextView
    public void setTextSize(int r2, float r3) {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == false) goto L6;
        super.setTextSize(r2, r3);
        return;
    L6:
        AppCompatTextHelper r1 = this.mTextHelper;
        if (r1 == null) goto L10;
        r1.setTextSize(r2, r3);
        return;
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface r3, int r4) {
        if (this.mIsSetTypefaceProcessing == false) goto L5;
        return;
    L5:
        if (r3 == null) goto L8;
        if (r4 <= 0) goto L8;
        Typeface r0 = TypefaceCompat.create(getContext(), r3, r4);
    L9:
        this.mIsSetTypefaceProcessing = true;
        if (r0 == null) goto L19;
        r3 = r0;
    L19:
        super.setTypeface(r3, r4);     // Catch: Throwable -> L16
        this.mIsSetTypefaceProcessing = false;
        return;
    L16:
        th = move-exception;
        this.mIsSetTypefaceProcessing = false;
        throw th;
    L8:
        r0 = null;
        goto L9
    }
}
