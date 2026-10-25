package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import androidx.core.view.ViewCompat;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.Buffer$$ExternalSyntheticBUOutline4;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
class AppCompatTextViewAutoSizeHelper {
    private static final RectF TEMP_RECTF = null;

    @SuppressLint({"BanConcurrentHashMap"})
    private static ConcurrentHashMap<String, Method> sTextViewMethodByNameCache;
    private float mAutoSizeMaxTextSizeInPx;
    private float mAutoSizeMinTextSizeInPx;
    private float mAutoSizeStepGranularityInPx;
    private int[] mAutoSizeTextSizesInPx;
    private int mAutoSizeTextType;
    private final Context mContext;
    private boolean mHasPresetAutoSizeValues;
    private final Impl mImpl;
    private boolean mNeedsAutoSizeText;
    private TextPaint mTempTextPaint;
    private final TextView mTextView;

    public static final class Api23Impl {
        public static StaticLayout createStaticLayoutForMeasuring(CharSequence r2, Layout.Alignment r3, int r4, int r5, TextView r6, TextPaint r7, Impl r8) {
            StaticLayout.Builder r22 = StaticLayout.Builder.obtain(r2, 0, r2.length(), r7, r4);
            StaticLayout.Builder r32 = r22.setAlignment(r3).setLineSpacing(r6.getLineSpacingExtra(), r6.getLineSpacingMultiplier()).setIncludePad(r6.getIncludeFontPadding()).setBreakStrategy(r6.getBreakStrategy()).setHyphenationFrequency(r6.getHyphenationFrequency());
            if (r5 != (-1)) goto L5;
            r5 = Integer.MAX_VALUE;
        L5:
            r32.setMaxLines(r5);
            r8.computeAndSetTextDirection(r22, r6);     // Catch: ClassCastException -> L8
        L10:
            return r22.build();
        L8:
            Log.w("ACTVAutoSizeHelper", "Failed to obtain TextDirectionHeuristic, auto size may be incorrect");
            goto L10
        }
    }

    public static class Impl {
        public Impl() {
        }

        public abstract void computeAndSetTextDirection(StaticLayout.Builder r1, TextView r2);

        public boolean isHorizontallyScrollable(TextView r2) {
            return ((Boolean) AppCompatTextViewAutoSizeHelper.invokeAndReturnWithDefault(r2, "getHorizontallyScrolling", Boolean.FALSE)).booleanValue();
        }
    }

    public static class Impl23 extends Impl {
        public Impl23() {
        }

        @Override // androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper.Impl
        public void computeAndSetTextDirection(StaticLayout.Builder r2, TextView r3) {
            r2.setTextDirection((TextDirectionHeuristic) AppCompatTextViewAutoSizeHelper.invokeAndReturnWithDefault(r3, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR));
        }
    }

    public static class Impl29 extends Impl23 {
        public Impl29() {
        }

        @Override // androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper.Impl23, androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper.Impl
        public void computeAndSetTextDirection(StaticLayout.Builder r1, TextView r2) {
            r1.setTextDirection(r2.getTextDirectionHeuristic());
        }

        @Override // androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper.Impl
        public boolean isHorizontallyScrollable(TextView r1) {
            return r1.isHorizontallyScrollable();
        }
    }

    static {
        TEMP_RECTF = new RectF();
        sTextViewMethodByNameCache = new ConcurrentHashMap();
    }

    public AppCompatTextViewAutoSizeHelper(TextView r3) {
        this.mAutoSizeTextType = 0;
        this.mNeedsAutoSizeText = false;
        this.mAutoSizeStepGranularityInPx = -1.0f;
        this.mAutoSizeMinTextSizeInPx = -1.0f;
        this.mAutoSizeMaxTextSizeInPx = -1.0f;
        this.mAutoSizeTextSizesInPx = new int[0];
        this.mHasPresetAutoSizeValues = false;
        this.mTextView = r3;
        this.mContext = r3.getContext();
        if (Build.VERSION.SDK_INT < 29) goto L6;
        this.mImpl = new Impl29();
        return;
    L6:
        this.mImpl = new Impl23();
    }

    private int[] cleanupAutoSizePresetSizes(int[] r6) {
        int r5 = r6.length;
        if (r5 == 0) goto L15;
        Arrays.sort(r6);
        ArrayList r0 = new ArrayList();
        int r1 = 0;
        int r2 = 0;
    L6:
        if (r2 >= r5) goto L14;
        int r3 = r6[r2];
        if (r3 <= 0) goto L12;
        if (Collections.binarySearch(r0, Integer.valueOf(r3)) >= 0) goto L12;
        r0.add(Integer.valueOf(r3));
    L12:
        r2 = r2 + 1;
        goto L6
    L14:
        if (r5 == r0.size()) goto L15;
        int r52 = r0.size();
        int[] r62 = new int[r52];
    L17:
        if (r1 >= r52) goto L19;
        r62[r1] = ((Integer) r0.get(r1)).intValue();
        r1 = r1 + 1;
        goto L17
    L19:
        return r62;
    L15:
        return r6;
    }

    private void clearAutoSizeConfiguration() {
        this.mAutoSizeTextType = 0;
        this.mAutoSizeMinTextSizeInPx = -1.0f;
        this.mAutoSizeMaxTextSizeInPx = -1.0f;
        this.mAutoSizeStepGranularityInPx = -1.0f;
        this.mAutoSizeTextSizesInPx = new int[0];
        this.mNeedsAutoSizeText = false;
    }

    private int findLargestTextSizeWhichFits(RectF r6) {
        int r0 = this.mAutoSizeTextSizesInPx.length;
        if (r0 == 0) goto L13;
        int r1 = 1;
        int r02 = r0 - 1;
        int r2 = 0;
    L5:
        int[] r3 = this.mAutoSizeTextSizesInPx;
        if (r1 > r02) goto L12;
        int r22 = (r1 + r02) / 2;
        if (suggestedSizeFitsInSpace(r3[r22], r6) == true) goto L9;
        r2 = r22 - 1;
        r02 = r2;
        goto L5
    L9:
        int r23 = r22 + 1;
        r2 = r1;
        r1 = r23;
        goto L5
    L12:
        return r3[r2];
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("No available text sizes to choose from.");
        return 0;
    }

    private static Method getTextViewMethod(String r4) {
        Method r1 = sTextViewMethodByNameCache.get(r4);     // Catch: Exception -> L9
        if (r1 != null) goto L11;
        r1 = TextView.class.getDeclaredMethod(r4, null);     // Catch: Exception -> L9
        if (r1 == null) goto L11;
        r1.setAccessible(true);     // Catch: Exception -> L9
        sTextViewMethodByNameCache.put(r4, r1);     // Catch: Exception -> L9
        return r1;
    L11:
        return r1;
    L9:
        e = move-exception;
        Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + r4 + "() method", e);
        return null;
    }

    @SuppressLint({"BanUncheckedReflection"})
    public static <T> T invokeAndReturnWithDefault(Object r2, String r3, T r4) {
        return (T) getTextViewMethod(r3).invoke(r2, null);
    L4:
        e = move-exception;
        Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + r3 + "() method", e);
        return r4;
    L6:
        th = move-exception;
        throw th;
    }

    @SuppressLint({"BanUncheckedReflection"})
    private void setRawTextSize(float r4) {
        if (r4 == this.mTextView.getPaint().getTextSize()) goto L21;
        this.mTextView.getPaint().setTextSize(r4);
        boolean r42 = this.mTextView.isInLayout();
        if (this.mTextView.getLayout() == null) goto L22;
        this.mNeedsAutoSizeText = false;
        Method r0 = getTextViewMethod("nullLayouts");     // Catch: Exception -> L11
        if (r0 == null) goto L13;
        r0.invoke(this.mTextView, null);     // Catch: Exception -> L11
    L13:
        TextView r02 = this.mTextView;
        if (r42 == true) goto L16;
        r02.requestLayout();
    L17:
        this.mTextView.invalidate();
        return;
    L16:
        r02.forceLayout();
    L11:
        e = move-exception;
        Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e);
        goto L13
    L22:
        return;
    }

    private boolean setupAutoSizeText() {
        int r1 = 0;
        if (supportsAutoSizeText() == true) goto L5;
    L15:
        this.mNeedsAutoSizeText = false;
    L17:
        return this.mNeedsAutoSizeText;
    L5:
        if (this.mAutoSizeTextType != 1) goto L15;
        if (this.mHasPresetAutoSizeValues == true) goto L9;
    L10:
        int r0 = ((int) Math.floor((this.mAutoSizeMaxTextSizeInPx - this.mAutoSizeMinTextSizeInPx) / this.mAutoSizeStepGranularityInPx)) + 1;
        int[] r3 = new int[r0];
    L11:
        if (r1 >= r0) goto L13;
        r3[r1] = Math.round(this.mAutoSizeMinTextSizeInPx + (r1 * this.mAutoSizeStepGranularityInPx));
        r1 = r1 + 1;
        goto L11
    L13:
        this.mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(r3);
    L14:
        this.mNeedsAutoSizeText = true;
        goto L17
    L9:
        if (this.mAutoSizeTextSizesInPx.length != 0) goto L14;
        goto L10
    }

    private void setupAutoSizeUniformPresetSizes(TypedArray r5) {
        int r0 = r5.length();
        int[] r1 = new int[r0];
        if (r0 <= 0) goto L10;
        int r2 = 0;
    L5:
        if (r2 >= r0) goto L7;
        r1[r2] = r5.getDimensionPixelSize(r2, -1);
        r2 = r2 + 1;
        goto L5
    L7:
        this.mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(r1);
        setupAutoSizeUniformPresetSizesConfiguration();
        return;
    }

    private boolean setupAutoSizeUniformPresetSizesConfiguration() {
        if (this.mAutoSizeTextSizesInPx.length <= 0) goto L5;
        boolean r4 = true;
    L6:
        this.mHasPresetAutoSizeValues = r4;
        if (r4 == false) goto L9;
        this.mAutoSizeTextType = 1;
        this.mAutoSizeMinTextSizeInPx = r0[0];
        this.mAutoSizeMaxTextSizeInPx = r0[r1 - 1];
        this.mAutoSizeStepGranularityInPx = -1.0f;
    L9:
        return r4;
    L5:
        r4 = false;
        goto L6
    }

    private boolean suggestedSizeFitsInSpace(int r5, RectF r6) {
        CharSequence r0 = this.mTextView.getText();
        TransformationMethod r1 = this.mTextView.getTransformationMethod();
        if (r1 == null) goto L7;
        CharSequence r12 = r1.getTransformation(r0, this.mTextView);
        if (r12 == null) goto L7;
        r0 = r12;
    L7:
        int r13 = this.mTextView.getMaxLines();
        initTempTextPaint(r5);
        StaticLayout r4 = createLayout(r0, (Layout.Alignment) invokeAndReturnWithDefault(this.mTextView, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL), Math.round(r6.right), r13);
        if (r13 == (-1)) goto L15;
        if (r4.getLineCount() <= r13) goto L12;
    L13:
        return false;
    L12:
        if (r4.getLineEnd(r4.getLineCount() - 1) != r0.length()) goto L13;
    L15:
        if (r4.getHeight() <= r6.bottom) goto L17;
        return false;
    L17:
        return true;
    }

    private boolean supportsAutoSizeText() {
        return !(this.mTextView instanceof AppCompatEditText);
    }

    private void validateAndSetAutoSizeTextTypeUniformConfiguration(float r4, float r5, float r6) {
        if (r4 <= 0.0f) goto L15;
        if (r5 <= r4) goto L13;
        if (r6 <= 0.0f) goto L11;
        this.mAutoSizeTextType = 1;
        this.mAutoSizeMinTextSizeInPx = r4;
        this.mAutoSizeMaxTextSizeInPx = r5;
        this.mAutoSizeStepGranularityInPx = r6;
        this.mHasPresetAutoSizeValues = false;
        return;
    L11:
        throw new IllegalArgumentException("The auto-size step granularity (" + r6 + "px) is less or equal to (0px)");
    L13:
        throw new IllegalArgumentException("Maximum auto-size text size (" + r5 + "px) is less or equal to minimum auto-size text size (" + r4 + "px)");
    L15:
        throw new IllegalArgumentException("Minimum auto-size text size (" + r4 + "px) is less or equal to (0px)");
    }

    public void autoSizeText() {
        if (isAutoSizeEnabled() == true) goto L6;
        return;
    L6:
        if (this.mNeedsAutoSizeText == true) goto L8;
    L32:
        this.mNeedsAutoSizeText = true;
        return;
    L8:
        if (this.mTextView.getMeasuredHeight() > 0) goto L10;
        return;
    L10:
        if (this.mTextView.getMeasuredWidth() > 0) goto L13;
        return;
    L13:
        if (this.mImpl.isHorizontallyScrollable(this.mTextView) == false) goto L15;
        int r0 = 1048576;
    L16:
        int r1 = (this.mTextView.getHeight() - this.mTextView.getCompoundPaddingBottom()) - this.mTextView.getCompoundPaddingTop();
        if (r0 <= 0) goto L36;
        if (r1 <= 0) goto L39;
        RectF r2 = TEMP_RECTF;
        monitor-enter(r2);
        r2.setEmpty();     // Catch: Throwable -> L25
        r2.right = r0;     // Catch: Throwable -> L25
        r2.bottom = r1;     // Catch: Throwable -> L25
        float r02 = findLargestTextSizeWhichFits(r2);     // Catch: Throwable -> L25
        if (r02 == this.mTextView.getTextSize()) goto L27;
        setTextSizeInternal(0, r02);     // Catch: Throwable -> L25
    L27:
        monitor-exit(r2);     // Catch: Throwable -> L25
    L25:
        th = move-exception;
        throw th;
    L39:
        return;
    L36:
        return;
    L15:
        r0 = (this.mTextView.getMeasuredWidth() - this.mTextView.getTotalPaddingLeft()) - this.mTextView.getTotalPaddingRight();
        goto L16
    }

    public StaticLayout createLayout(CharSequence r8, Layout.Alignment r9, int r10, int r11) {
        return Api23Impl.createStaticLayoutForMeasuring(r8, r9, r10, r11, this.mTextView, this.mTempTextPaint, this.mImpl);
    }

    public int getAutoSizeMaxTextSize() {
        return Math.round(this.mAutoSizeMaxTextSizeInPx);
    }

    public int getAutoSizeMinTextSize() {
        return Math.round(this.mAutoSizeMinTextSizeInPx);
    }

    public int getAutoSizeStepGranularity() {
        return Math.round(this.mAutoSizeStepGranularityInPx);
    }

    public int[] getAutoSizeTextAvailableSizes() {
        return this.mAutoSizeTextSizesInPx;
    }

    public int getAutoSizeTextType() {
        return this.mAutoSizeTextType;
    }

    public void initTempTextPaint(int r3) {
        TextPaint r0 = this.mTempTextPaint;
        if (r0 != null) goto L5;
        this.mTempTextPaint = new TextPaint();
    L6:
        this.mTempTextPaint.set(this.mTextView.getPaint());
        this.mTempTextPaint.setTextSize(r3);
        return;
    L5:
        r0.reset();
        goto L6
    }

    public boolean isAutoSizeEnabled() {
        if (supportsAutoSizeText() == true) goto L5;
        return false;
    L5:
        if (this.mAutoSizeTextType == 0) goto L10;
        return true;
    L10:
        return false;
    }

    public void loadFromAttributes(AttributeSet r11, int r12) {
        TypedArray r7 = this.mContext.obtainStyledAttributes(r11, R$styleable.AppCompatTextView, r12, 0);
        TextView r3 = this.mTextView;
        ViewCompat.saveAttributeDataForStyleable(r3, r3.getContext(), R$styleable.AppCompatTextView, r11, r7, r12, 0);
        if (r7.hasValue(R$styleable.AppCompatTextView_autoSizeTextType) == false) goto L6;
        this.mAutoSizeTextType = r7.getInt(R$styleable.AppCompatTextView_autoSizeTextType, 0);
    L6:
        if (r7.hasValue(R$styleable.AppCompatTextView_autoSizeStepGranularity) == false) goto L8;
        float r112 = r7.getDimension(R$styleable.AppCompatTextView_autoSizeStepGranularity, -1.0f);
    L10:
        if (r7.hasValue(R$styleable.AppCompatTextView_autoSizeMinTextSize) == false) goto L12;
        float r0 = r7.getDimension(R$styleable.AppCompatTextView_autoSizeMinTextSize, -1.0f);
    L14:
        if (r7.hasValue(R$styleable.AppCompatTextView_autoSizeMaxTextSize) == false) goto L16;
        float r1 = r7.getDimension(R$styleable.AppCompatTextView_autoSizeMaxTextSize, -1.0f);
    L18:
        if (r7.hasValue(R$styleable.AppCompatTextView_autoSizePresetSizes) == false) goto L22;
        int r32 = r7.getResourceId(R$styleable.AppCompatTextView_autoSizePresetSizes, 0);
        if (r32 <= 0) goto L22;
        TypedArray r33 = r7.getResources().obtainTypedArray(r32);
        setupAutoSizeUniformPresetSizes(r33);
        r33.recycle();
    L22:
        r7.recycle();
        if (supportsAutoSizeText() == true) goto L25;
        this.mAutoSizeTextType = 0;
        return;
    L25:
        if (this.mAutoSizeTextType == 1) goto L27;
        return;
    L27:
        if (this.mHasPresetAutoSizeValues == true) goto L38;
        DisplayMetrics r2 = this.mContext.getResources().getDisplayMetrics();
        if (r0 != (-1.0f)) goto L32;
        r0 = TypedValue.applyDimension(2, 12.0f, r2);
    L32:
        if (r1 != (-1.0f)) goto L35;
        r1 = TypedValue.applyDimension(2, 112.0f, r2);
    L35:
        if (r112 != (-1.0f)) goto L37;
        r112 = 1.0f;
    L37:
        validateAndSetAutoSizeTextTypeUniformConfiguration(r0, r1, r112);
    L38:
        setupAutoSizeText();
        return;
    L16:
        r1 = -1.0f;
        goto L18
    L12:
        r0 = -1.0f;
        goto L14
    L8:
        r112 = -1.0f;
        goto L10
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int r2, int r3, int r4, int r5) {
        if (supportsAutoSizeText() == false) goto L8;
        DisplayMetrics r0 = this.mContext.getResources().getDisplayMetrics();
        validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(r5, r2, r0), TypedValue.applyDimension(r5, r3, r0), TypedValue.applyDimension(r5, r4, r0));
        if (setupAutoSizeText() == false) goto L9;
        autoSizeText();
        return;
    L9:
        return;
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] r6, int r7) {
        if (supportsAutoSizeText() == false) goto L23;
        int r0 = r6.length;
        int r1 = 0;
        if (r0 <= 0) goto L17;
        int[] r2 = new int[r0];
        if (r7 != 0) goto L9;
        r2 = Arrays.copyOf(r6, r0);
    L12:
        this.mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(r2);
        if (setupAutoSizeUniformPresetSizesConfiguration() == true) goto L19;
        Buffer$$ExternalSyntheticBUOutline4.m("None of the preset sizes is valid: ", Arrays.toString(r6));
        return;
    L19:
        if (setupAutoSizeText() == false) goto L24;
        autoSizeText();
        return;
    L24:
        return;
    L9:
        DisplayMetrics r3 = this.mContext.getResources().getDisplayMetrics();
    L10:
        if (r1 >= r0) goto L12;
        r2[r1] = Math.round(TypedValue.applyDimension(r7, r6[r1], r3));
        r1 = r1 + 1;
        goto L10
    L17:
        this.mHasPresetAutoSizeValues = false;
        goto L19
    }

    public void setAutoSizeTextTypeWithDefaults(int r4) {
        if (supportsAutoSizeText() == false) goto L15;
        if (r4 != 0) goto L6;
        clearAutoSizeConfiguration();
        return;
    L6:
        if (r4 != 1) goto L11;
        DisplayMetrics r42 = this.mContext.getResources().getDisplayMetrics();
        validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(2, 12.0f, r42), TypedValue.applyDimension(2, 112.0f, r42), 1.0f);
        if (setupAutoSizeText() == false) goto L16;
        autoSizeText();
        return;
    L16:
        return;
    L11:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unknown auto-size text type: ", r4);
        return;
    }

    public void setTextSizeInternal(int r2, float r3) {
        Context r0 = this.mContext;
        if (r0 != null) goto L5;
        Resources r02 = Resources.getSystem();
    L6:
        setRawTextSize(TypedValue.applyDimension(r2, r3, r02.getDisplayMetrics()));
        return;
    L5:
        r02 = r0.getResources();
        goto L6
    }
}
