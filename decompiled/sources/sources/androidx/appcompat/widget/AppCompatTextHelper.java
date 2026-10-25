package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.LocaleList;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.util.TypedValueCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.core.widget.TextViewCompat;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
class AppCompatTextHelper {
    private boolean mAsyncFontPending;
    private final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
    private TintInfo mDrawableBottomTint;
    private TintInfo mDrawableEndTint;
    private TintInfo mDrawableLeftTint;
    private TintInfo mDrawableRightTint;
    private TintInfo mDrawableStartTint;
    private TintInfo mDrawableTint;
    private TintInfo mDrawableTopTint;
    private Typeface mFontTypeface;
    private int mFontWeight;
    private int mStyle;
    private final TextView mView;

    /* renamed from: androidx.appcompat.widget.AppCompatTextHelper$1, reason: invalid class name */
    public class AnonymousClass1 extends ResourcesCompat.FontCallback {
        final /* synthetic */ AppCompatTextHelper this$0;
        final /* synthetic */ int val$fontWeight;
        final /* synthetic */ int val$style;
        final /* synthetic */ WeakReference val$textViewWeak;

        public AnonymousClass1(AppCompatTextHelper r1, int r2, int r3, WeakReference r4) {
            this.this$0 = r1;
            this.val$fontWeight = r2;
            this.val$style = r3;
            this.val$textViewWeak = r4;
        }

        @Override // androidx.core.content.res.ResourcesCompat.FontCallback
        public void onFontRetrievalFailed(int r1) {
        }

        @Override // androidx.core.content.res.ResourcesCompat.FontCallback
        public void onFontRetrieved(Typeface r3) {
            if (Build.VERSION.SDK_INT < 28) goto L11;
            int r0 = this.val$fontWeight;
            if (r0 == (-1)) goto L11;
            if ((this.val$style & 2) == 0) goto L9;
            boolean r1 = true;
        L10:
            r3 = Api28Impl.create(r3, r0, r1);
            goto L11
        L9:
            r1 = false;
        L11:
            this.this$0.onAsyncTypefaceReceived(this.val$textViewWeak, r3);
        }
    }

    /* renamed from: androidx.appcompat.widget.AppCompatTextHelper$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ AppCompatTextHelper this$0;
        final /* synthetic */ int val$style;
        final /* synthetic */ TextView val$textView;
        final /* synthetic */ Typeface val$typeface;

        public AnonymousClass2(AppCompatTextHelper r1, TextView r2, Typeface r3, int r4) {
            this.this$0 = r1;
            this.val$textView = r2;
            this.val$typeface = r3;
            this.val$style = r4;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.val$textView.setTypeface(this.val$typeface, this.val$style);
        }
    }

    public static class Api24Impl {
        public static LocaleList forLanguageTags(String r0) {
            return LocaleList.forLanguageTags(r0);
        }

        public static void setTextLocales(TextView r0, LocaleList r1) {
            r0.setTextLocales(r1);
        }
    }

    public static class Api26Impl {
        public static int getAutoSizeStepGranularity(TextView r0) {
            return r0.getAutoSizeStepGranularity();
        }

        public static void setAutoSizeTextTypeUniformWithConfiguration(TextView r0, int r1, int r2, int r3, int r4) {
            r0.setAutoSizeTextTypeUniformWithConfiguration(r1, r2, r3, r4);
        }

        public static void setAutoSizeTextTypeUniformWithPresetSizes(TextView r0, int[] r1, int r2) {
            r0.setAutoSizeTextTypeUniformWithPresetSizes(r1, r2);
        }

        public static boolean setFontVariationSettings(TextView r0, String r1) {
            return r0.setFontVariationSettings(r1);
        }
    }

    public static class Api28Impl {
        public static Typeface create(Typeface r0, int r1, boolean r2) {
            return Typeface.create(r0, r1, r2);
        }
    }

    public AppCompatTextHelper(TextView r2) {
        this.mStyle = 0;
        this.mFontWeight = -1;
        this.mView = r2;
        this.mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(r2);
    }

    private void applyCompoundDrawableTint(Drawable r1, TintInfo r2) {
        if (r1 == null) goto L6;
        if (r2 == null) goto L7;
        AppCompatDrawableManager.tintDrawable(r1, r2, this.mView.getDrawableState());
        return;
    L7:
        return;
    }

    private static TintInfo createTintInfo(Context r0, AppCompatDrawableManager r1, int r2) {
        ColorStateList r02 = r1.getTintList(r0, r2);
        if (r02 == null) goto L6;
        TintInfo r12 = new TintInfo();
        r12.mHasTintList = true;
        r12.mTintList = r02;
        return r12;
    L6:
        return null;
    }

    private void setCompoundDrawables(Drawable r6, Drawable r7, Drawable r8, Drawable r9, Drawable r10, Drawable r11) {
        if (r10 != null) goto L40;
        if (r11 != null) goto L40;
        if (r6 != null) goto L12;
        if (r7 != null) goto L12;
        if (r8 != null) goto L12;
        if (r9 != null) goto L12;
        return;
    L12:
        Drawable[] r102 = this.mView.getCompoundDrawablesRelative();
        Drawable r112 = r102[0];
        if (r112 == null) goto L15;
    L32:
        if (r7 != null) goto L35;
        r7 = r102[1];
    L35:
        if (r9 != null) goto L38;
        r9 = r102[3];
    L38:
        this.mView.setCompoundDrawablesRelativeWithIntrinsicBounds(r112, r7, r102[2], r9);
        return;
    L15:
        if (r102[2] != null) goto L32;
        Drawable[] r103 = this.mView.getCompoundDrawables();
        TextView r5 = this.mView;
        if (r6 != null) goto L21;
        r6 = r103[0];
    L21:
        if (r7 != null) goto L24;
        r7 = r103[1];
    L24:
        if (r8 != null) goto L27;
        r8 = r103[2];
    L27:
        if (r9 != null) goto L30;
        r9 = r103[3];
    L30:
        r5.setCompoundDrawablesWithIntrinsicBounds(r6, r7, r8, r9);
        return;
    L40:
        Drawable[] r62 = this.mView.getCompoundDrawablesRelative();
        if (r10 != null) goto L44;
        r10 = r62[0];
    L44:
        if (r7 != null) goto L47;
        r7 = r62[1];
    L47:
        if (r11 != null) goto L50;
        r11 = r62[2];
    L50:
        TextView r52 = this.mView;
        if (r9 != null) goto L54;
        r9 = r62[3];
    L54:
        r52.setCompoundDrawablesRelativeWithIntrinsicBounds(r10, r7, r11, r9);
    }

    private void setCompoundTints() {
        TintInfo r0 = this.mDrawableTint;
        this.mDrawableLeftTint = r0;
        this.mDrawableTopTint = r0;
        this.mDrawableRightTint = r0;
        this.mDrawableBottomTint = r0;
        this.mDrawableStartTint = r0;
        this.mDrawableEndTint = r0;
    }

    private void setTextSizeInternal(int r1, float r2) {
        this.mAutoSizeTextHelper.setTextSizeInternal(r1, r2);
    }

    private void updateTypefaceAndStyle(Context r11, TintTypedArray r12) {
        this.mStyle = r12.getInt(R$styleable.TextAppearance_android_textStyle, this.mStyle);
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 28) goto L7;
        int r4 = r12.getInt(R$styleable.TextAppearance_android_textFontWeight, -1);
        this.mFontWeight = r4;
        if (r4 == (-1)) goto L7;
        this.mStyle &= 2;
    L7:
        boolean r5 = true;
        if (r12.hasValue(R$styleable.TextAppearance_android_fontFamily) == false) goto L10;
    L26:
        this.mFontTypeface = null;
        if (r12.hasValue(R$styleable.TextAppearance_fontFamily) == false) goto L29;
        int r42 = R$styleable.TextAppearance_fontFamily;
    L30:
        int r7 = this.mFontWeight;
        int r8 = this.mStyle;
        if (r11.isRestricted() == true) goto L50;
        Typeface r112 = r12.getFont(r42, this.mStyle, new AnonymousClass1(this, r7, r8, new WeakReference(this.mView)));     // Catch: Throwable -> L64
        if (r112 == null) goto L45;
        if (r0 >= 28) goto L37;
    L43:
        this.mFontTypeface = r112;     // Catch: Throwable -> L64
        goto L45
    L37:
        if (this.mFontWeight == (-1)) goto L43;
        Typeface r113 = Typeface.create(r112, 0);     // Catch: Throwable -> L64
        int r02 = this.mFontWeight;     // Catch: Throwable -> L64
        if ((this.mStyle & 2) == 0) goto L41;
        boolean r72 = true;
    L42:
        this.mFontTypeface = Api28Impl.create(r113, r02, r72);     // Catch: Throwable -> L64
        goto L45
    L41:
        r72 = false;
    L45:
        if (this.mFontTypeface != null) goto L47;
        boolean r114 = true;
    L48:
        this.mAsyncFontPending = r114;     // Catch: Throwable -> L64
        goto L50
    L47:
        r114 = false;
    L50:
        if (this.mFontTypeface != null) goto L68;
        String r115 = r12.getString(r42);
        if (r115 != null) goto L54;
        return;
    L54:
        if (Build.VERSION.SDK_INT >= 28) goto L56;
    L62:
        this.mFontTypeface = Typeface.create(r115, this.mStyle);
        return;
    L56:
        if (this.mFontWeight == (-1)) goto L62;
        Typeface r116 = Typeface.create(r115, 0);
        int r122 = this.mFontWeight;
        if ((this.mStyle & 2) != 0) goto L61;
        r5 = false;
    L61:
        this.mFontTypeface = Api28Impl.create(r116, r122, r5);
        return;
    L68:
        return;
    L29:
        r42 = R$styleable.TextAppearance_android_fontFamily;
        goto L30
    L10:
        if (r12.hasValue(R$styleable.TextAppearance_fontFamily) == true) goto L26;
        if (r12.hasValue(R$styleable.TextAppearance_android_typeface) == false) goto L67;
        this.mAsyncFontPending = false;
        int r117 = r12.getInt(R$styleable.TextAppearance_android_typeface, 1);
        if (r117 == 1) goto L24;
        if (r117 != 2) goto L18;
        this.mFontTypeface = Typeface.SERIF;
        return;
    L18:
        if (r117 != 3) goto L71;
        this.mFontTypeface = Typeface.MONOSPACE;
        return;
    L71:
        return;
    L24:
        this.mFontTypeface = Typeface.SANS_SERIF;
        return;
    }

    public void applyCompoundDrawablesTints() {
        if (this.mDrawableLeftTint == null) goto L5;
    L10:
        Drawable[] r0 = this.mView.getCompoundDrawables();
        applyCompoundDrawableTint(r0[0], this.mDrawableLeftTint);
        applyCompoundDrawableTint(r0[1], this.mDrawableTopTint);
        applyCompoundDrawableTint(r0[2], this.mDrawableRightTint);
        applyCompoundDrawableTint(r0[3], this.mDrawableBottomTint);
    L12:
        if (this.mDrawableStartTint == null) goto L14;
    L17:
        Drawable[] r02 = this.mView.getCompoundDrawablesRelative();
        applyCompoundDrawableTint(r02[0], this.mDrawableStartTint);
        applyCompoundDrawableTint(r02[2], this.mDrawableEndTint);
        return;
    L14:
        if (this.mDrawableEndTint != null) goto L17;
        return;
    L5:
        if (this.mDrawableTopTint != null) goto L10;
        if (this.mDrawableRightTint != null) goto L10;
        if (this.mDrawableBottomTint == null) goto L12;
        goto L10
    }

    public void autoSizeText() {
        this.mAutoSizeTextHelper.autoSizeText();
    }

    public int getAutoSizeMaxTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMaxTextSize();
    }

    public int getAutoSizeMinTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMinTextSize();
    }

    public int getAutoSizeStepGranularity() {
        return this.mAutoSizeTextHelper.getAutoSizeStepGranularity();
    }

    public int[] getAutoSizeTextAvailableSizes() {
        return this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
    }

    public int getAutoSizeTextType() {
        return this.mAutoSizeTextHelper.getAutoSizeTextType();
    }

    public ColorStateList getCompoundDrawableTintList() {
        TintInfo r0 = this.mDrawableTint;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.mTintList;
    }

    public PorterDuff.Mode getCompoundDrawableTintMode() {
        TintInfo r0 = this.mDrawableTint;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.mTintMode;
    }

    public boolean isAutoSizeEnabled() {
        return this.mAutoSizeTextHelper.isAutoSizeEnabled();
    }

    @SuppressLint({"NewApi"})
    public void loadFromAttributes(AttributeSet r17, int r18) {
        Context r8 = this.mView.getContext();
        AppCompatDrawableManager r9 = AppCompatDrawableManager.get();
        TintTypedArray r11 = TintTypedArray.obtainStyledAttributes(r8, r17, R$styleable.AppCompatTextHelper, r18, 0);
        TextView r1 = this.mView;
        ViewCompat.saveAttributeDataForStyleable(r1, r1.getContext(), R$styleable.AppCompatTextHelper, r17, r11.getWrappedTypeArray(), r18, 0);
        int r12 = r11.getResourceId(R$styleable.AppCompatTextHelper_android_textAppearance, -1);
        if (r11.hasValue(R$styleable.AppCompatTextHelper_android_drawableLeft) == false) goto L6;
        this.mDrawableLeftTint = createTintInfo(r8, r9, r11.getResourceId(R$styleable.AppCompatTextHelper_android_drawableLeft, 0));
    L6:
        if (r11.hasValue(R$styleable.AppCompatTextHelper_android_drawableTop) == false) goto L9;
        this.mDrawableTopTint = createTintInfo(r8, r9, r11.getResourceId(R$styleable.AppCompatTextHelper_android_drawableTop, 0));
    L9:
        if (r11.hasValue(R$styleable.AppCompatTextHelper_android_drawableRight) == false) goto L12;
        this.mDrawableRightTint = createTintInfo(r8, r9, r11.getResourceId(R$styleable.AppCompatTextHelper_android_drawableRight, 0));
    L12:
        if (r11.hasValue(R$styleable.AppCompatTextHelper_android_drawableBottom) == false) goto L15;
        this.mDrawableBottomTint = createTintInfo(r8, r9, r11.getResourceId(R$styleable.AppCompatTextHelper_android_drawableBottom, 0));
    L15:
        if (r11.hasValue(R$styleable.AppCompatTextHelper_android_drawableStart) == false) goto L18;
        this.mDrawableStartTint = createTintInfo(r8, r9, r11.getResourceId(R$styleable.AppCompatTextHelper_android_drawableStart, 0));
    L18:
        if (r11.hasValue(R$styleable.AppCompatTextHelper_android_drawableEnd) == false) goto L20;
        this.mDrawableEndTint = createTintInfo(r8, r9, r11.getResourceId(R$styleable.AppCompatTextHelper_android_drawableEnd, 0));
    L20:
        r11.recycle();
        boolean r2 = this.mView.getTransformationMethod() instanceof PasswordTransformationMethod;
        if (r12 == (-1)) goto L39;
        TintTypedArray r13 = TintTypedArray.obtainStyledAttributes(r8, r12, R$styleable.TextAppearance);
        if (r2 == false) goto L25;
    L27:
        boolean r122 = false;
        boolean r132 = false;
    L28:
        updateTypefaceAndStyle(r8, r13);
        if (r13.hasValue(R$styleable.TextAppearance_textLocale) == false) goto L31;
        String r14 = r13.getString(R$styleable.TextAppearance_textLocale);
    L33:
        if (Build.VERSION.SDK_INT >= 26) goto L35;
    L37:
        String r15 = null;
    L38:
        r13.recycle();
    L40:
        TintTypedArray r16 = TintTypedArray.obtainStyledAttributes(r8, r17, R$styleable.TextAppearance, r18, 0);
        if (r2 == false) goto L43;
    L45:
        boolean r5 = r132;
    L47:
        if (r16.hasValue(R$styleable.TextAppearance_textLocale) == false) goto L49;
        r14 = r16.getString(R$styleable.TextAppearance_textLocale);
    L49:
        int r133 = Build.VERSION.SDK_INT;
        if (r133 < 26) goto L55;
        if (r16.hasValue(R$styleable.TextAppearance_fontVariationSettings) == false) goto L55;
        r15 = r16.getString(R$styleable.TextAppearance_fontVariationSettings);
    L55:
        if (r133 >= 28) goto L57;
    L61:
        updateTypefaceAndStyle(r8, r16);
        r16.recycle();
        if (r2 == true) goto L65;
        if (r5 == false) goto L65;
        setAllCaps(r122);
    L65:
        Typeface r19 = this.mFontTypeface;
        if (r19 == null) goto L71;
        int r22 = this.mFontWeight;
        TextView r3 = this.mView;
        if (r22 != (-1)) goto L70;
        r3.setTypeface(r19, this.mStyle);
        goto L71
    L70:
        r3.setTypeface(r19);
    L71:
        if (r15 == null) goto L73;
        Api26Impl.setFontVariationSettings(this.mView, r15);
    L73:
        if (r14 == null) goto L75;
        Api24Impl.setTextLocales(this.mView, Api24Impl.forLanguageTags(r14));
    L75:
        this.mAutoSizeTextHelper.loadFromAttributes(r17, r18);
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == true) goto L78;
    L85:
        TintTypedArray r10 = TintTypedArray.obtainStyledAttributes(r8, r17, R$styleable.AppCompatTextView);
        int r110 = r10.getResourceId(R$styleable.AppCompatTextView_drawableLeftCompat, -1);
        if (r110 == (-1)) goto L88;
        Drawable r111 = r9.getDrawable(r8, r110);
    L89:
        int r23 = r10.getResourceId(R$styleable.AppCompatTextView_drawableTopCompat, -1);
        if (r23 == (-1)) goto L92;
        Drawable r24 = r9.getDrawable(r8, r23);
    L93:
        int r32 = r10.getResourceId(R$styleable.AppCompatTextView_drawableRightCompat, -1);
        if (r32 == (-1)) goto L96;
        Drawable r33 = r9.getDrawable(r8, r32);
    L97:
        int r4 = r10.getResourceId(R$styleable.AppCompatTextView_drawableBottomCompat, -1);
        if (r4 == (-1)) goto L100;
        Drawable r42 = r9.getDrawable(r8, r4);
    L101:
        int r52 = r10.getResourceId(R$styleable.AppCompatTextView_drawableStartCompat, -1);
        if (r52 == (-1)) goto L104;
        Drawable r53 = r9.getDrawable(r8, r52);
    L105:
        int r6 = r10.getResourceId(R$styleable.AppCompatTextView_drawableEndCompat, -1);
        if (r6 == (-1)) goto L108;
        Drawable r62 = r9.getDrawable(r8, r6);
    L109:
        setCompoundDrawables(r111, r24, r33, r42, r53, r62);
        if (r10.hasValue(R$styleable.AppCompatTextView_drawableTint) == false) goto L113;
        TextViewCompat.setCompoundDrawableTintList(this.mView, r10.getColorStateList(R$styleable.AppCompatTextView_drawableTint));
    L113:
        if (r10.hasValue(R$styleable.AppCompatTextView_drawableTintMode) == false) goto L115;
        TextViewCompat.setCompoundDrawableTintMode(this.mView, DrawableUtils.parseTintMode(r10.getInt(R$styleable.AppCompatTextView_drawableTintMode, -1), null));
    L115:
        int r112 = r10.getDimensionPixelSize(R$styleable.AppCompatTextView_firstBaselineToTopHeight, -1);
        int r25 = r10.getDimensionPixelSize(R$styleable.AppCompatTextView_lastBaselineToBottomHeight, -1);
        if (r10.hasValue(R$styleable.AppCompatTextView_lineHeight) == false) goto L123;
        TypedValue r34 = r10.peekValue(R$styleable.AppCompatTextView_lineHeight);
        if (r34 != null) goto L120;
    L122:
        float r35 = r10.getDimensionPixelSize(R$styleable.AppCompatTextView_lineHeight, -1);
        int r43 = -1;
    L124:
        r10.recycle();
        if (r112 == (-1)) goto L127;
        TextViewCompat.setFirstBaselineToTopHeight(this.mView, r112);
    L127:
        if (r25 == (-1)) goto L130;
        TextViewCompat.setLastBaselineToBottomHeight(this.mView, r25);
    L130:
        if (r35 == (-1.0f)) goto L137;
        TextView r0 = this.mView;
        if (r43 != (-1)) goto L135;
        TextViewCompat.setLineHeight(r0, (int) r35);
        return;
    L135:
        TextViewCompat.setLineHeight(r0, r43, r35);
        return;
    L137:
        return;
    L120:
        if (r34.type != 5) goto L122;
        r43 = TypedValueCompat.getUnitFromComplexDimension(r34.data);
        r35 = TypedValue.complexToFloat(r34.data);
        goto L124
    L123:
        r43 = -1;
        r35 = -1.0f;
        goto L124
    L108:
        r62 = null;
        goto L109
    L104:
        r53 = null;
        goto L105
    L100:
        r42 = null;
        goto L101
    L96:
        r33 = null;
        goto L97
    L92:
        r24 = null;
        goto L93
    L88:
        r111 = null;
        goto L89
    L78:
        if (this.mAutoSizeTextHelper.getAutoSizeTextType() == 0) goto L85;
        int[] r113 = this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
        if (r113.length <= 0) goto L85;
        float r26 = Api26Impl.getAutoSizeStepGranularity(this.mView);
        TextView r36 = this.mView;
        if (r26 == (-1.0f)) goto L84;
        Api26Impl.setAutoSizeTextTypeUniformWithConfiguration(r36, this.mAutoSizeTextHelper.getAutoSizeMinTextSize(), this.mAutoSizeTextHelper.getAutoSizeMaxTextSize(), this.mAutoSizeTextHelper.getAutoSizeStepGranularity(), 0);
        goto L85
    L84:
        Api26Impl.setAutoSizeTextTypeUniformWithPresetSizes(r36, r113, 0);
        goto L85
    L57:
        if (r16.hasValue(R$styleable.TextAppearance_android_textSize) == false) goto L61;
        if (r16.getDimensionPixelSize(R$styleable.TextAppearance_android_textSize, -1) != 0) goto L61;
        this.mView.setTextSize(0, 0.0f);
        goto L61
    L43:
        if (r16.hasValue(R$styleable.TextAppearance_textAllCaps) == false) goto L45;
        r122 = r16.getBoolean(R$styleable.TextAppearance_textAllCaps, false);
        r5 = true;
        goto L47
    L35:
        if (r13.hasValue(R$styleable.TextAppearance_fontVariationSettings) == false) goto L37;
        r15 = r13.getString(R$styleable.TextAppearance_fontVariationSettings);
        goto L38
    L31:
        r14 = null;
        goto L33
    L25:
        if (r13.hasValue(R$styleable.TextAppearance_textAllCaps) == false) goto L27;
        r122 = r13.getBoolean(R$styleable.TextAppearance_textAllCaps, false);
        r132 = true;
        goto L28
    L39:
        r122 = false;
        r132 = false;
        r14 = null;
        r15 = null;
        goto L40
    }

    public void onAsyncTypefaceReceived(WeakReference<TextView> r3, Typeface r4) {
        if (this.mAsyncFontPending == false) goto L12;
        this.mFontTypeface = r4;
        TextView r32 = r3.get();
        if (r32 == null) goto L13;
        boolean r0 = r32.isAttachedToWindow();
        int r1 = this.mStyle;
        if (r0 == false) goto L10;
        r32.post(new AnonymousClass2(this, r32, r4, r1));
        return;
    L10:
        r32.setTypeface(r4, r1);
        return;
    L13:
        return;
    }

    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == true) goto L6;
        autoSizeText();
        return;
    }

    public void onSetCompoundDrawables() {
        applyCompoundDrawablesTints();
    }

    public void onSetTextAppearance(Context r4, int r5) {
        TintTypedArray r52 = TintTypedArray.obtainStyledAttributes(r4, r5, R$styleable.TextAppearance);
        if (r52.hasValue(R$styleable.TextAppearance_textAllCaps) == false) goto L6;
        setAllCaps(r52.getBoolean(R$styleable.TextAppearance_textAllCaps, false));
    L6:
        if (r52.hasValue(R$styleable.TextAppearance_android_textSize) == true) goto L8;
    L10:
        updateTypefaceAndStyle(r4, r52);
        if (Build.VERSION.SDK_INT >= 26) goto L13;
    L17:
        r52.recycle();
        Typeface r42 = this.mFontTypeface;
        if (r42 == null) goto L21;
        this.mView.setTypeface(r42, this.mStyle);
        return;
    L21:
        return;
    L13:
        if (r52.hasValue(R$styleable.TextAppearance_fontVariationSettings) == false) goto L17;
        String r43 = r52.getString(R$styleable.TextAppearance_fontVariationSettings);
        if (r43 == null) goto L17;
        Api26Impl.setFontVariationSettings(this.mView, r43);
        goto L17
    L8:
        if (r52.getDimensionPixelSize(R$styleable.TextAppearance_android_textSize, -1) != 0) goto L10;
        this.mView.setTextSize(0, 0.0f);
        goto L10
    }

    public void populateSurroundingTextIfNeeded(TextView r2, InputConnection r3, EditorInfo r4) {
        if (Build.VERSION.SDK_INT >= 30) goto L7;
        if (r3 == null) goto L8;
        EditorInfoCompat.setInitialSurroundingText(r4, r2.getText());
        return;
    L8:
        return;
    }

    public void setAllCaps(boolean r1) {
        this.mView.setAllCaps(r1);
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int r1, int r2, int r3, int r4) {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithConfiguration(r1, r2, r3, r4);
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] r1, int r2) {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(r1, r2);
    }

    public void setAutoSizeTextTypeWithDefaults(int r1) {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeWithDefaults(r1);
    }

    public void setCompoundDrawableTintList(ColorStateList r2) {
        if (this.mDrawableTint != null) goto L5;
        this.mDrawableTint = new TintInfo();
    L5:
        TintInfo r0 = this.mDrawableTint;
        r0.mTintList = r2;
        if (r2 == null) goto L8;
        boolean r22 = true;
    L9:
        r0.mHasTintList = r22;
        setCompoundTints();
        return;
    L8:
        r22 = false;
        goto L9
    }

    public void setCompoundDrawableTintMode(PorterDuff.Mode r2) {
        if (this.mDrawableTint != null) goto L5;
        this.mDrawableTint = new TintInfo();
    L5:
        TintInfo r0 = this.mDrawableTint;
        r0.mTintMode = r2;
        if (r2 == null) goto L8;
        boolean r22 = true;
    L9:
        r0.mHasTintMode = r22;
        setCompoundTints();
        return;
    L8:
        r22 = false;
        goto L9
    }

    public void setTextSize(int r2, float r3) {
        if (ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE == false) goto L5;
        return;
    L5:
        if (isAutoSizeEnabled() == true) goto L9;
        setTextSizeInternal(r2, r3);
        return;
    }
}
