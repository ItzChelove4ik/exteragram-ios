package com.google.android.material.chip;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.text.BidiFormatter;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.canvas.CanvasCompat;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.focus.FocusRingDrawable;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Opcodes;

/* loaded from: classes5.dex */
public class ChipDrawable extends MaterialShapeDrawable implements Drawable.Callback, TextDrawableHelper.TextDrawableDelegate {
    private static final boolean DEBUG = false;
    private static final int[] DEFAULT_STATE = null;
    private static final int MAX_CHIP_ICON_HEIGHT = 24;
    private static final String NAMESPACE_APP = "http://schemas.android.com/apk/res-auto";
    private static final ShapeDrawable closeIconRippleMask = null;
    private int alpha;
    private boolean checkable;
    private Drawable checkedIcon;
    private ColorStateList checkedIconTint;
    private boolean checkedIconVisible;
    private ColorStateList chipBackgroundColor;
    private float chipCornerRadius;
    private float chipEndPadding;
    private Drawable chipIcon;
    private float chipIconSize;
    private ColorStateList chipIconTint;
    private boolean chipIconVisible;
    private float chipMinHeight;
    private final Paint chipPaint;
    private float chipStartPadding;
    private ColorStateList chipStrokeColor;
    private float chipStrokeWidth;
    private ColorStateList chipSurfaceColor;
    private Drawable closeIcon;
    private CharSequence closeIconContentDescription;
    private float closeIconEndPadding;
    private Drawable closeIconRipple;
    private float closeIconSize;
    private float closeIconStartPadding;
    private int[] closeIconStateSet;
    private ColorStateList closeIconTint;
    private boolean closeIconVisible;
    private ColorFilter colorFilter;
    private ColorStateList compatRippleColor;
    private final Context context;
    private boolean currentChecked;
    private int currentChipBackgroundColor;
    private int currentChipStrokeColor;
    private int currentChipSurfaceColor;
    private int currentCompatRippleColor;
    private int currentCompositeSurfaceBackgroundColor;
    private int currentTextColor;
    private int currentTint;
    private final Paint debugPaint;
    private WeakReference<Delegate> delegate;
    private final Paint.FontMetrics fontMetrics;
    private boolean hasChipIconTint;
    private MotionSpec hideMotionSpec;
    private float iconEndPadding;
    private float iconStartPadding;
    private boolean isShapeThemingEnabled;
    private int maxWidth;
    private final PointF pointF;
    private final RectF rectF;
    private ColorStateList rippleColor;
    private final Path shapePath;
    private boolean shouldDrawText;
    private MotionSpec showMotionSpec;
    private CharSequence text;
    private final TextDrawableHelper textDrawableHelper;
    private float textEndPadding;
    private float textStartPadding;
    private ColorStateList tint;
    private PorterDuffColorFilter tintFilter;
    private PorterDuff.Mode tintMode;
    private TextUtils.TruncateAt truncateAt;
    private boolean useCompatRipple;

    public interface Delegate {
        void onChipDrawableSizeChange();
    }

    static {
        DEFAULT_STATE = new int[]{R.attr.state_enabled};
        closeIconRippleMask = new ShapeDrawable(new OvalShape());
    }

    private ChipDrawable(Context r2, AttributeSet r3, int r4, int r5) {
        super(r2, r3, r4, r5);
        this.chipCornerRadius = -1.0f;
        this.chipPaint = new Paint(1);
        this.fontMetrics = new Paint.FontMetrics();
        this.rectF = new RectF();
        this.pointF = new PointF();
        this.shapePath = new Path();
        this.alpha = 255;
        this.tintMode = PorterDuff.Mode.SRC_IN;
        this.delegate = new WeakReference(null);
        initializeElevationOverlay(r2);
        this.context = r2;
        TextDrawableHelper r32 = new TextDrawableHelper(this);
        this.textDrawableHelper = r32;
        this.text = _UrlKt.FRAGMENT_ENCODE_SET;
        TextPaint r33 = r32.getTextPaint();
        r33.density = r2.getResources().getDisplayMetrics().density;
        this.debugPaint = null;
        int[] r22 = DEFAULT_STATE;
        setState(r22);
        setCloseIconState(r22);
        this.shouldDrawText = true;
        closeIconRippleMask.setTint(-1);
    }

    private void applyChildDrawable(Drawable r3) {
        if (r3 == null) goto L21;
        r3.setCallback(this);
        DrawableCompat.setLayoutDirection(r3, DrawableCompat.getLayoutDirection(this));
        r3.setLevel(getLevel());
        r3.setVisible(isVisible(), false);
        if (r3 != this.closeIcon) goto L10;
        r3.setTintList(this.closeIconTint);
        if (r3.isStateful() == false) goto L19;
        r3.setState(getCloseIconState());
        return;
    L19:
        return;
    L10:
        Drawable r0 = this.chipIcon;
        if (r3 != r0) goto L16;
        if (this.hasChipIconTint == false) goto L16;
        r0.setTintList(this.chipIconTint);
    L16:
        if (r3.isStateful() == false) goto L20;
        r3.setState(getState());
        return;
    L20:
        return;
    }

    private void calculateChipIconBounds(Rect r4, RectF r5) {
        r5.setEmpty();
        if (showsChipIcon() == false) goto L5;
    L8:
        float r0 = this.chipStartPadding + this.iconStartPadding;
        float r1 = getCurrentChipIconWidth();
        if (DrawableCompat.getLayoutDirection(this) != 0) goto L11;
        float r2 = r4.left + r0;
        r5.left = r2;
        r5.right = r2 + r1;
    L12:
        float r3 = getCurrentChipIconHeight();
        float r42 = r4.exactCenterY() - (r3 / 2.0f);
        r5.top = r42;
        r5.bottom = r42 + r3;
        return;
    L11:
        float r22 = r4.right - r0;
        r5.right = r22;
        r5.left = r22 - r1;
        goto L12
    L5:
        if (showsCheckedIcon() == true) goto L8;
    }

    private void calculateChipTouchBounds(Rect r3, RectF r4) {
        r4.set(r3);
        if (showsCloseIcon() == false) goto L10;
        float r0 = (((this.chipEndPadding + this.closeIconEndPadding) + this.closeIconSize) + this.closeIconStartPadding) + this.textEndPadding;
        if (DrawableCompat.getLayoutDirection(this) != 0) goto L8;
        r4.right = r3.right - r0;
        return;
    L8:
        r4.left = r3.left + r0;
        return;
    }

    private void calculateCloseIconBounds(Rect r3, RectF r4) {
        r4.setEmpty();
        if (showsCloseIcon() == false) goto L10;
        float r0 = this.chipEndPadding + this.closeIconEndPadding;
        if (DrawableCompat.getLayoutDirection(this) != 0) goto L7;
        float r1 = r3.right - r0;
        r4.right = r1;
        r4.left = r1 - this.closeIconSize;
    L8:
        float r32 = r3.exactCenterY();
        float r2 = this.closeIconSize;
        float r33 = r32 - (r2 / 2.0f);
        r4.top = r33;
        r4.bottom = r33 + r2;
        return;
    L7:
        float r12 = r3.left + r0;
        r4.left = r12;
        r4.right = r12 + this.closeIconSize;
        goto L8
    }

    private void calculateCloseIconTouchBounds(Rect r3, RectF r4) {
        r4.setEmpty();
        if (showsCloseIcon() == false) goto L10;
        float r0 = (((this.chipEndPadding + this.closeIconEndPadding) + this.closeIconSize) + this.closeIconStartPadding) + this.textEndPadding;
        if (DrawableCompat.getLayoutDirection(this) != 0) goto L7;
        float r2 = r3.right;
        r4.right = r2;
        r4.left = r2 - r0;
    L8:
        r4.top = r3.top;
        r4.bottom = r3.bottom;
        return;
    L7:
        int r22 = r3.left;
        r4.left = r22;
        r4.right = r22 + r0;
        goto L8
    }

    private void calculateTextBounds(Rect r4, RectF r5) {
        r5.setEmpty();
        if (this.text == null) goto L10;
        float r0 = (this.chipStartPadding + calculateChipIconWidth()) + this.textStartPadding;
        float r1 = (this.chipEndPadding + calculateCloseIconWidth()) + this.textEndPadding;
        if (DrawableCompat.getLayoutDirection(this) != 0) goto L7;
        r5.left = r4.left + r0;
        r5.right = r4.right - r1;
    L8:
        r5.top = r4.top;
        r5.bottom = r4.bottom;
        return;
    L7:
        r5.left = r4.left + r1;
        r5.right = r4.right - r0;
        goto L8
    }

    private float calculateTextCenterFromBaseline() {
        this.textDrawableHelper.getTextPaint().getFontMetrics(this.fontMetrics);
        Paint.FontMetrics r2 = this.fontMetrics;
        return (r2.descent + r2.ascent) / 2.0f;
    }

    private boolean canShowCheckedIcon() {
        if (this.checkedIconVisible == true) goto L5;
        return false;
    L5:
        if (this.checkedIcon != null) goto L7;
        return false;
    L7:
        if (this.checkable == false) goto L13;
        return true;
    L13:
        return false;
    }

    public static ChipDrawable createFromAttributes(Context r1, AttributeSet r2, int r3, int r4) {
        ChipDrawable r0 = new ChipDrawable(r1, r2, r3, r4);
        r0.loadFromAttributes(r2, r3, r4);
        return r0;
    }

    public static ChipDrawable createFromResource(Context r2, int r3) {
        AttributeSet r32 = DrawableUtils.parseDrawableXml(r2, r3, "chip");
        int r0 = r32.getStyleAttribute();
        if (r0 != 0) goto L6;
        r0 = com.google.android.material.R.style.Widget_MaterialComponents_Chip_Entry;
    L6:
        return createFromAttributes(r2, r32, com.google.android.material.R.attr.chipStandaloneStyle, r0);
    }

    private void drawCheckedIcon(Canvas r6, Rect r7) {
        if (showsCheckedIcon() == false) goto L6;
        calculateChipIconBounds(r7, this.rectF);
        RectF r72 = this.rectF;
        float r0 = r72.left;
        float r73 = r72.top;
        r6.translate(r0, r73);
        this.checkedIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
        this.checkedIcon.draw(r6);
        r6.translate(-r0, -r73);
        return;
    }

    private void drawChipBackground(Canvas r3, Rect r4) {
        if (this.isShapeThemingEnabled == true) goto L6;
        this.chipPaint.setColor(this.currentChipBackgroundColor);
        this.chipPaint.setStyle(Paint.Style.FILL);
        this.chipPaint.setColorFilter(getTintColorFilter());
        this.rectF.set(r4);
        r3.drawRoundRect(this.rectF, getChipCornerRadius(), getChipCornerRadius(), this.chipPaint);
        return;
    }

    private void drawChipIcon(Canvas r6, Rect r7) {
        if (showsChipIcon() == false) goto L6;
        calculateChipIconBounds(r7, this.rectF);
        RectF r72 = this.rectF;
        float r0 = r72.left;
        float r73 = r72.top;
        r6.translate(r0, r73);
        this.chipIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
        this.chipIcon.draw(r6);
        r6.translate(-r0, -r73);
        return;
    }

    private void drawChipStroke(Canvas r8, Rect r9) {
        if (this.chipStrokeWidth > 0.0f) goto L5;
        return;
    L5:
        if (this.isShapeThemingEnabled == true) goto L12;
        this.chipPaint.setColor(this.currentChipStrokeColor);
        this.chipPaint.setStyle(Paint.Style.STROKE);
        if (this.isShapeThemingEnabled == true) goto L9;
        this.chipPaint.setColorFilter(getTintColorFilter());
    L9:
        RectF r0 = this.rectF;
        float r1 = r9.left;
        float r2 = this.chipStrokeWidth;
        r0.set(r1 + (r2 / 2.0f), r9.top + (r2 / 2.0f), r9.right - (r2 / 2.0f), r9.bottom - (r2 / 2.0f));
        float r92 = this.chipCornerRadius - (this.chipStrokeWidth / 2.0f);
        r8.drawRoundRect(this.rectF, r92, r92, this.chipPaint);
        return;
    }

    private void drawChipSurface(Canvas r3, Rect r4) {
        if (this.isShapeThemingEnabled == true) goto L6;
        this.chipPaint.setColor(this.currentChipSurfaceColor);
        this.chipPaint.setStyle(Paint.Style.FILL);
        this.rectF.set(r4);
        r3.drawRoundRect(this.rectF, getChipCornerRadius(), getChipCornerRadius(), this.chipPaint);
        return;
    }

    private void drawCloseIcon(Canvas r6, Rect r7) {
        if (showsCloseIcon() == false) goto L6;
        calculateCloseIconBounds(r7, this.rectF);
        RectF r72 = this.rectF;
        float r0 = r72.left;
        float r73 = r72.top;
        r6.translate(r0, r73);
        this.closeIcon.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
        this.closeIconRipple.setBounds(this.closeIcon.getBounds());
        this.closeIconRipple.jumpToCurrentState();
        this.closeIconRipple.draw(r6);
        r6.translate(-r0, -r73);
        return;
    }

    private void drawCompatRipple(Canvas r3, Rect r4) {
        this.chipPaint.setColor(this.currentCompatRippleColor);
        this.chipPaint.setStyle(Paint.Style.FILL);
        this.rectF.set(r4);
        if (this.isShapeThemingEnabled == true) goto L6;
        r3.drawRoundRect(this.rectF, getChipCornerRadius(), getChipCornerRadius(), this.chipPaint);
        return;
    L6:
        calculatePathForSize(new RectF(r4), this.shapePath);
        super.drawShape(r3, this.chipPaint, this.shapePath, getBoundsAsRectF());
    }

    private void drawDebug(Canvas r10, Rect r11) {
        Paint r0 = this.debugPaint;
        if (r0 == null) goto L18;
        r0.setColor(ColorUtils.setAlphaComponent(-16777216, 127));
        r10.drawRect(r11, this.debugPaint);
        if (showsChipIcon() == false) goto L7;
    L8:
        calculateChipIconBounds(r11, this.rectF);
        r10.drawRect(this.rectF, this.debugPaint);
    L10:
        if (this.text == null) goto L12;
        Canvas r3 = r10;
        r3.drawLine(r11.left, r11.exactCenterY(), r11.right, r11.exactCenterY(), this.debugPaint);
    L14:
        if (showsCloseIcon() == false) goto L16;
        calculateCloseIconBounds(r11, this.rectF);
        r3.drawRect(this.rectF, this.debugPaint);
    L16:
        this.debugPaint.setColor(ColorUtils.setAlphaComponent(Opcodes.V_PREVIEW, 127));
        calculateChipTouchBounds(r11, this.rectF);
        r3.drawRect(this.rectF, this.debugPaint);
        this.debugPaint.setColor(ColorUtils.setAlphaComponent(-16711936, 127));
        calculateCloseIconTouchBounds(r11, this.rectF);
        r3.drawRect(this.rectF, this.debugPaint);
        return;
    L12:
        r3 = r10;
        goto L14
    L7:
        if (showsCheckedIcon() == false) goto L10;
    }

    private void drawText(Canvas r10, Rect r11) {
        if (this.text == null) goto L22;
        Paint.Align r0 = calculateTextOriginAndAlignment(r11, this.pointF);
        calculateTextBounds(r11, this.rectF);
        if (this.textDrawableHelper.getTextAppearance() == null) goto L7;
        this.textDrawableHelper.getTextPaint().drawableState = getState();
        this.textDrawableHelper.updateTextPaintDrawState(this.context);
    L7:
        this.textDrawableHelper.getTextPaint().setTextAlign(r0);
        int r1 = 0;
        if (Math.round(this.textDrawableHelper.getTextWidth(getText().toString())) <= Math.round(this.rectF.width())) goto L10;
        boolean r112 = true;
    L11:
        if (r112 == false) goto L13;
        r1 = r10.save();
        r10.clipRect(this.rectF);
    L13:
        CharSequence r02 = this.text;
        if (r112 == true) goto L16;
    L18:
        CharSequence r3 = r02;
        int r5 = r3.length();
        PointF r03 = this.pointF;
        r10.drawText(r3, 0, r5, r03.x, r03.y, this.textDrawableHelper.getTextPaint());
        if (r112 == false) goto L23;
        r10.restoreToCount(r1);
        return;
    L23:
        return;
    L16:
        if (this.truncateAt == null) goto L18;
        r02 = TextUtils.ellipsize(r02, this.textDrawableHelper.getTextPaint(), this.rectF.width(), this.truncateAt);
        goto L18
    L10:
        r112 = false;
        goto L11
    }

    private float getCurrentChipIconHeight() {
        if (this.currentChecked == false) goto L5;
        Drawable r0 = this.checkedIcon;
    L6:
        float r1 = this.chipIconSize;
        if (r1 > 0.0f) goto L13;
        if (r0 == null) goto L13;
        float r3 = (float) Math.ceil(ViewUtils.dpToPx(this.context, 24));
        if (r0.getIntrinsicHeight() <= r3) goto L12;
        return r3;
    L12:
        return r0.getIntrinsicHeight();
    L13:
        return r1;
    L5:
        r0 = this.chipIcon;
        goto L6
    }

    private float getCurrentChipIconWidth() {
        if (this.currentChecked == false) goto L5;
        Drawable r0 = this.checkedIcon;
    L6:
        float r2 = this.chipIconSize;
        if (r2 > 0.0f) goto L11;
        if (r0 != null) goto L10;
        return r2;
    L10:
        return r0.getIntrinsicWidth();
    L11:
        return r2;
    L5:
        r0 = this.chipIcon;
        goto L6
    }

    private ColorFilter getTintColorFilter() {
        ColorFilter r0 = this.colorFilter;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return this.tintFilter;
    }

    private static boolean hasState(int[] r4, int r5) {
        if (r4 != null) goto L5;
        return false;
    L5:
        int r1 = r4.length;
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L12;
        if (r4[r2] == r5) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    }

    private static boolean isStateful(ColorStateList r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (r0.isStateful() == false) goto L9;
        return true;
    L9:
        return false;
    }

    private static boolean isStateful(Drawable r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (r0.isStateful() == false) goto L9;
        return true;
    L9:
        return false;
    }

    private static boolean isStateful(TextAppearance r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.getTextColor() != null) goto L6;
        return false;
    L6:
        if (r1.getTextColor().isStateful() == false) goto L12;
        return true;
    L12:
        return false;
    }

    private void loadFromAttributes(AttributeSet r8, int r9, int r10) {
        TypedArray r82 = ThemeEnforcement.obtainStyledAttributes(this.context, r8, com.google.android.material.R.styleable.Chip, r9, r10, new int[0]);
        this.isShapeThemingEnabled = r82.hasValue(com.google.android.material.R.styleable.Chip_shapeAppearance);
        setChipSurfaceColor(MaterialResources.getColorStateList(this.context, r82, com.google.android.material.R.styleable.Chip_chipSurfaceColor));
        setChipBackgroundColor(MaterialResources.getColorStateList(this.context, r82, com.google.android.material.R.styleable.Chip_chipBackgroundColor));
        setChipMinHeight(r82.getDimension(com.google.android.material.R.styleable.Chip_chipMinHeight, 0.0f));
        if (r82.hasValue(com.google.android.material.R.styleable.Chip_chipCornerRadius) == false) goto L5;
        setChipCornerRadius(r82.getDimension(com.google.android.material.R.styleable.Chip_chipCornerRadius, 0.0f));
    L5:
        setChipStrokeColor(MaterialResources.getColorStateList(this.context, r82, com.google.android.material.R.styleable.Chip_chipStrokeColor));
        setChipStrokeWidth(r82.getDimension(com.google.android.material.R.styleable.Chip_chipStrokeWidth, 0.0f));
        setRippleColor(MaterialResources.getColorStateList(this.context, r82, com.google.android.material.R.styleable.Chip_rippleColor));
        setText(r82.getText(com.google.android.material.R.styleable.Chip_android_text));
        TextAppearance r92 = MaterialResources.getTextAppearance(this.context, r82, com.google.android.material.R.styleable.Chip_android_textAppearance);
        r92.setTextSize(r82.getDimension(com.google.android.material.R.styleable.Chip_android_textSize, r92.getTextSize()));
        if (Build.VERSION.SDK_INT < 26) goto L10;
        int r0 = MaterialResources.getIndexWithValue(r82, com.google.android.material.R.styleable.Chip_fontVariationSettings, com.google.android.material.R.styleable.Chip_android_fontVariationSettings);
        if (r82.hasValue(r0) == false) goto L10;
        r92.setFontVariationSettings(r82.getString(r0));
    L10:
        setTextAppearance(r92);
        int r93 = r82.getInt(com.google.android.material.R.styleable.Chip_android_ellipsize, 0);
        if (r93 != 1) goto L13;
        setEllipsize(TextUtils.TruncateAt.START);
    L20:
        setChipIconVisible(r82.getBoolean(com.google.android.material.R.styleable.Chip_chipIconVisible, false));
        if (r8 != null) goto L23;
    L27:
        setChipIcon(MaterialResources.getDrawable(this.context, r82, com.google.android.material.R.styleable.Chip_chipIcon));
        if (r82.hasValue(com.google.android.material.R.styleable.Chip_chipIconTint) == false) goto L30;
        setChipIconTint(MaterialResources.getColorStateList(this.context, r82, com.google.android.material.R.styleable.Chip_chipIconTint));
    L30:
        setChipIconSize(r82.getDimension(com.google.android.material.R.styleable.Chip_chipIconSize, -1.0f));
        setCloseIconVisible(r82.getBoolean(com.google.android.material.R.styleable.Chip_closeIconVisible, false));
        if (r8 != null) goto L33;
    L37:
        setCloseIcon(MaterialResources.getDrawable(this.context, r82, com.google.android.material.R.styleable.Chip_closeIcon));
        setCloseIconTint(MaterialResources.getColorStateList(this.context, r82, com.google.android.material.R.styleable.Chip_closeIconTint));
        setCloseIconSize(r82.getDimension(com.google.android.material.R.styleable.Chip_closeIconSize, 0.0f));
        setCheckable(r82.getBoolean(com.google.android.material.R.styleable.Chip_android_checkable, false));
        setCheckedIconVisible(r82.getBoolean(com.google.android.material.R.styleable.Chip_checkedIconVisible, false));
        if (r8 != null) goto L40;
    L44:
        setCheckedIcon(MaterialResources.getDrawable(this.context, r82, com.google.android.material.R.styleable.Chip_checkedIcon));
        if (r82.hasValue(com.google.android.material.R.styleable.Chip_checkedIconTint) == false) goto L47;
        setCheckedIconTint(MaterialResources.getColorStateList(this.context, r82, com.google.android.material.R.styleable.Chip_checkedIconTint));
    L47:
        setShowMotionSpec(MotionSpec.createFromAttribute(this.context, r82, com.google.android.material.R.styleable.Chip_showMotionSpec));
        setHideMotionSpec(MotionSpec.createFromAttribute(this.context, r82, com.google.android.material.R.styleable.Chip_hideMotionSpec));
        setChipStartPadding(r82.getDimension(com.google.android.material.R.styleable.Chip_chipStartPadding, 0.0f));
        setIconStartPadding(r82.getDimension(com.google.android.material.R.styleable.Chip_iconStartPadding, 0.0f));
        setIconEndPadding(r82.getDimension(com.google.android.material.R.styleable.Chip_iconEndPadding, 0.0f));
        setTextStartPadding(r82.getDimension(com.google.android.material.R.styleable.Chip_textStartPadding, 0.0f));
        setTextEndPadding(r82.getDimension(com.google.android.material.R.styleable.Chip_textEndPadding, 0.0f));
        setCloseIconStartPadding(r82.getDimension(com.google.android.material.R.styleable.Chip_closeIconStartPadding, 0.0f));
        setCloseIconEndPadding(r82.getDimension(com.google.android.material.R.styleable.Chip_closeIconEndPadding, 0.0f));
        setChipEndPadding(r82.getDimension(com.google.android.material.R.styleable.Chip_chipEndPadding, 0.0f));
        setMaxWidth(r82.getDimensionPixelSize(com.google.android.material.R.styleable.Chip_android_maxWidth, Integer.MAX_VALUE));
        r82.recycle();
        return;
    L40:
        if (r8.getAttributeValue(NAMESPACE_APP, "checkedIconEnabled") == null) goto L44;
        if (r8.getAttributeValue(NAMESPACE_APP, "checkedIconVisible") != null) goto L44;
        setCheckedIconVisible(r82.getBoolean(com.google.android.material.R.styleable.Chip_checkedIconEnabled, false));
        goto L44
    L33:
        if (r8.getAttributeValue(NAMESPACE_APP, "closeIconEnabled") == null) goto L37;
        if (r8.getAttributeValue(NAMESPACE_APP, "closeIconVisible") != null) goto L37;
        setCloseIconVisible(r82.getBoolean(com.google.android.material.R.styleable.Chip_closeIconEnabled, false));
        goto L37
    L23:
        if (r8.getAttributeValue(NAMESPACE_APP, "chipIconEnabled") == null) goto L27;
        if (r8.getAttributeValue(NAMESPACE_APP, "chipIconVisible") != null) goto L27;
        setChipIconVisible(r82.getBoolean(com.google.android.material.R.styleable.Chip_chipIconEnabled, false));
        goto L27
    L13:
        if (r93 != 2) goto L15;
        setEllipsize(TextUtils.TruncateAt.MIDDLE);
        goto L20
    L15:
        if (r93 != 3) goto L20;
        setEllipsize(TextUtils.TruncateAt.END);
        goto L20
    }

    private boolean onStateChange(int[] r7, int[] r8) {
        boolean r0 = super.onStateChange(r7);
        ColorStateList r1 = this.chipSurfaceColor;
        if (r1 == null) goto L5;
        int r12 = r1.getColorForState(r7, this.currentChipSurfaceColor);
    L6:
        int r13 = compositeElevationOverlayIfNeeded(r12);
        boolean r4 = true;
        if (this.currentChipSurfaceColor == r13) goto L9;
        this.currentChipSurfaceColor = r13;
        r0 = true;
    L9:
        ColorStateList r3 = this.chipBackgroundColor;
        if (r3 == null) goto L12;
        int r32 = r3.getColorForState(r7, this.currentChipBackgroundColor);
    L13:
        int r33 = compositeElevationOverlayIfNeeded(r32);
        if (this.currentChipBackgroundColor == r33) goto L16;
        this.currentChipBackgroundColor = r33;
        r0 = true;
    L16:
        int r14 = MaterialColors.layer(r13, r33);
        if (this.currentCompositeSurfaceBackgroundColor == r14) goto L19;
        boolean r34 = true;
    L21:
        if (getFillColor() != null) goto L23;
        boolean r5 = true;
    L25:
        if ((r34 | r5) == false) goto L27;
        this.currentCompositeSurfaceBackgroundColor = r14;
        setFillColor(ColorStateList.valueOf(r14));
        r0 = true;
    L27:
        ColorStateList r15 = this.chipStrokeColor;
        if (r15 == null) goto L30;
        int r16 = r15.getColorForState(r7, this.currentChipStrokeColor);
    L32:
        if (this.currentChipStrokeColor == r16) goto L35;
        this.currentChipStrokeColor = r16;
        r0 = true;
    L35:
        if (this.compatRippleColor != null) goto L37;
    L39:
        int r17 = 0;
    L41:
        if (this.currentCompatRippleColor == r17) goto L46;
        this.currentCompatRippleColor = r17;
        if (this.useCompatRipple == false) goto L46;
        r0 = true;
    L46:
        if (this.textDrawableHelper.getTextAppearance() != null) goto L48;
    L50:
        int r18 = 0;
    L52:
        if (this.currentTextColor == r18) goto L55;
        this.currentTextColor = r18;
        r0 = true;
    L55:
        if (hasState(getState(), R.attr.state_checked) == true) goto L57;
    L59:
        boolean r19 = false;
    L61:
        if (this.currentChecked != r19) goto L63;
    L68:
        boolean r110 = false;
    L69:
        ColorStateList r35 = this.tint;
        if (r35 == null) goto L72;
        int r36 = r35.getColorForState(r7, this.currentTint);
    L74:
        if (this.currentTint == r36) goto L76;
        this.currentTint = r36;
        this.tintFilter = DrawableUtils.updateTintFilter(this, this.tint, this.tintMode);
    L78:
        if (isStateful(this.chipIcon) == false) goto L81;
        r4 = r4 | this.chipIcon.setState(r7);
    L81:
        if (isStateful(this.checkedIcon) == false) goto L84;
        r4 = r4 | this.checkedIcon.setState(r7);
    L84:
        if (isStateful(this.closeIcon) == false) goto L87;
        int[] r02 = new int[r7.length + r8.length];
        System.arraycopy(r7, 0, r02, 0, r7.length);
        System.arraycopy(r8, 0, r02, r7.length, r8.length);
        r4 = r4 | this.closeIcon.setState(r02);
    L87:
        if (isStateful(this.closeIconRipple) == false) goto L89;
        r4 = r4 | this.closeIconRipple.setState(r8);
    L89:
        if (r4 == false) goto L91;
        invalidateSelf();
    L91:
        if (r110 == false) goto L93;
        onSizeChange();
    L93:
        return r4;
    L76:
        r4 = r0;
        goto L78
    L72:
        r36 = 0;
        goto L74
    L63:
        if (this.checkedIcon == null) goto L68;
        float r03 = calculateChipIconWidth();
        this.currentChecked = r19;
        if (r03 == calculateChipIconWidth()) goto L67;
        r0 = true;
        r110 = true;
        goto L69
    L67:
        r110 = false;
        r0 = true;
        goto L69
    L57:
        if (this.checkable == false) goto L59;
        r19 = true;
        goto L61
    L48:
        if (this.textDrawableHelper.getTextAppearance().getTextColor() == null) goto L50;
        r18 = this.textDrawableHelper.getTextAppearance().getTextColor().getColorForState(r7, this.currentTextColor);
        goto L52
    L37:
        if (RippleUtils.shouldDrawRippleCompat(r7) == false) goto L39;
        r17 = this.compatRippleColor.getColorForState(r7, this.currentCompatRippleColor);
        goto L41
    L30:
        r16 = 0;
        goto L32
    L23:
        r5 = false;
        goto L25
    L19:
        r34 = false;
        goto L21
    L12:
        r32 = 0;
        goto L13
    L5:
        r12 = 0;
        goto L6
    }

    private void setChipSurfaceColor(ColorStateList r2) {
        if (this.chipSurfaceColor == r2) goto L6;
        this.chipSurfaceColor = r2;
        onStateChange(getState());
        return;
    }

    private boolean showsCheckedIcon() {
        if (this.checkedIconVisible == true) goto L5;
        return false;
    L5:
        if (this.checkedIcon != null) goto L7;
        return false;
    L7:
        if (this.currentChecked == false) goto L13;
        return true;
    L13:
        return false;
    }

    private boolean showsChipIcon() {
        if (this.chipIconVisible == true) goto L5;
        return false;
    L5:
        if (this.chipIcon == null) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean showsCloseIcon() {
        if (this.closeIconVisible == true) goto L5;
        return false;
    L5:
        if (this.closeIcon == null) goto L10;
        return true;
    L10:
        return false;
    }

    private void unapplyChildDrawable(Drawable r1) {
        if (r1 == null) goto L5;
        r1.setCallback(null);
        return;
    }

    private void updateCompatRippleColor() {
        if (this.useCompatRipple == false) goto L5;
        ColorStateList r0 = RippleUtils.sanitizeRippleDrawableColor(this.rippleColor);
    L6:
        this.compatRippleColor = r0;
        return;
    L5:
        r0 = null;
        goto L6
    }

    private void updateFrameworkCloseIconRipple() {
        RippleDrawable r0 = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(getRippleColor()), this.closeIcon, closeIconRippleMask);
        FocusRingDrawable.layer(this.context, r0);
        this.closeIconRipple = r0;
    }

    public float calculateChipIconWidth() {
        if (showsChipIcon() == true) goto L10;
        if (showsCheckedIcon() == true) goto L10;
        return 0.0f;
    L10:
        return (this.iconStartPadding + getCurrentChipIconWidth()) + this.iconEndPadding;
    }

    public float calculateCloseIconWidth() {
        if (showsCloseIcon() == true) goto L5;
        return 0.0f;
    L5:
        return (this.closeIconStartPadding + this.closeIconSize) + this.closeIconEndPadding;
    }

    public Paint.Align calculateTextOriginAndAlignment(Rect r4, PointF r5) {
        r5.set(0.0f, 0.0f);
        Paint.Align r0 = Paint.Align.LEFT;
        if (this.text == null) goto L9;
        float r1 = (this.chipStartPadding + calculateChipIconWidth()) + this.textStartPadding;
        if (DrawableCompat.getLayoutDirection(this) != 0) goto L7;
        r5.x = r4.left + r1;
    L8:
        r5.y = r4.centerY() - calculateTextCenterFromBaseline();
        goto L9
    L7:
        r5.x = r4.right - r1;
        r0 = Paint.Align.RIGHT;
    L9:
        return r0;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas r9) {
        Rect r0 = getBounds();
        if (r0.isEmpty() == false) goto L5;
        return;
    L5:
        if (getAlpha() == 0) goto L23;
        int r7 = this.alpha;
        if (r7 >= 255) goto L10;
        Canvas r2 = r9;
        int r92 = CanvasCompat.saveLayerAlpha(r2, r0.left, r0.top, r0.right, r0.bottom, r7);
    L11:
        drawChipSurface(r2, r0);
        drawChipBackground(r2, r0);
        if (this.isShapeThemingEnabled == false) goto L14;
        super.draw(r2);
    L14:
        drawChipStroke(r2, r0);
        drawCompatRipple(r2, r0);
        drawChipIcon(r2, r0);
        drawCheckedIcon(r2, r0);
        if (this.shouldDrawText == false) goto L17;
        drawText(r2, r0);
    L17:
        drawCloseIcon(r2, r0);
        drawDebug(r2, r0);
        if (this.alpha >= 255) goto L22;
        r2.restoreToCount(r92);
        return;
    L22:
        return;
    L10:
        r2 = r9;
        r92 = 0;
        goto L11
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.alpha;
    }

    public Drawable getCheckedIcon() {
        return this.checkedIcon;
    }

    public ColorStateList getCheckedIconTint() {
        return this.checkedIconTint;
    }

    public ColorStateList getChipBackgroundColor() {
        return this.chipBackgroundColor;
    }

    public float getChipCornerRadius() {
        if (this.isShapeThemingEnabled == false) goto L7;
        return getTopLeftCornerResolvedSize();
    L7:
        return this.chipCornerRadius;
    }

    public float getChipEndPadding() {
        return this.chipEndPadding;
    }

    public Drawable getChipIcon() {
        Drawable r0 = this.chipIcon;
        if (r0 != null) goto L5;
        return null;
    L5:
        return DrawableCompat.unwrap(r0);
    }

    public float getChipIconSize() {
        return this.chipIconSize;
    }

    public ColorStateList getChipIconTint() {
        return this.chipIconTint;
    }

    public float getChipMinHeight() {
        return this.chipMinHeight;
    }

    public float getChipStartPadding() {
        return this.chipStartPadding;
    }

    public ColorStateList getChipStrokeColor() {
        return this.chipStrokeColor;
    }

    public float getChipStrokeWidth() {
        return this.chipStrokeWidth;
    }

    public void getChipTouchBounds(RectF r2) {
        calculateChipTouchBounds(getBounds(), r2);
    }

    public Drawable getCloseIcon() {
        Drawable r0 = this.closeIcon;
        if (r0 != null) goto L5;
        return null;
    L5:
        return DrawableCompat.unwrap(r0);
    }

    public CharSequence getCloseIconContentDescription() {
        return this.closeIconContentDescription;
    }

    public float getCloseIconEndPadding() {
        return this.closeIconEndPadding;
    }

    public float getCloseIconSize() {
        return this.closeIconSize;
    }

    public float getCloseIconStartPadding() {
        return this.closeIconStartPadding;
    }

    public int[] getCloseIconState() {
        return this.closeIconStateSet;
    }

    public ColorStateList getCloseIconTint() {
        return this.closeIconTint;
    }

    public void getCloseIconTouchBounds(RectF r2) {
        calculateCloseIconTouchBounds(getBounds(), r2);
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    public TextUtils.TruncateAt getEllipsize() {
        return this.truncateAt;
    }

    public String getFontVariationSettings() {
        TextAppearance r2 = getTextAppearance();
        if (r2 != null) goto L5;
        return null;
    L5:
        if (Build.VERSION.SDK_INT >= 26) goto L7;
        return null;
    L7:
        return r2.getFontVariationSettings();
    }

    public MotionSpec getHideMotionSpec() {
        return this.hideMotionSpec;
    }

    public float getIconEndPadding() {
        return this.iconEndPadding;
    }

    public float getIconStartPadding() {
        return this.iconStartPadding;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.chipMinHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return Math.min(Math.round((((((this.chipStartPadding + calculateChipIconWidth()) + this.textStartPadding) + this.textDrawableHelper.getTextWidth(getText().toString())) + this.textEndPadding) + calculateCloseIconWidth()) + this.chipEndPadding), this.maxWidth);
    }

    public int getMaxWidth() {
        return this.maxWidth;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void getOutline(Outline r9) {
        if (this.isShapeThemingEnabled == false) goto L6;
        super.getOutline(r9);
        return;
    L6:
        Rect r0 = getBounds();
        if (r0.isEmpty() == true) goto L9;
        r9.setRoundRect(r0, this.chipCornerRadius);
        Outline r2 = r9;
    L10:
        r2.setAlpha(getAlpha() / 255.0f);
        return;
    L9:
        r2 = r9;
        r2.setRoundRect(0, 0, getIntrinsicWidth(), getIntrinsicHeight(), this.chipCornerRadius);
        goto L10
    }

    public ColorStateList getRippleColor() {
        return this.rippleColor;
    }

    public MotionSpec getShowMotionSpec() {
        return this.showMotionSpec;
    }

    public CharSequence getText() {
        return this.text;
    }

    public TextAppearance getTextAppearance() {
        return this.textDrawableHelper.getTextAppearance();
    }

    public float getTextEndPadding() {
        return this.textEndPadding;
    }

    public float getTextStartPadding() {
        return this.textStartPadding;
    }

    public boolean getUseCompatRipple() {
        return this.useCompatRipple;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable r1) {
        Drawable.Callback r12 = getCallback();
        if (r12 == null) goto L6;
        r12.invalidateDrawable(this);
        return;
    }

    public boolean isCheckable() {
        return this.checkable;
    }

    @Deprecated
    public boolean isCheckedIconEnabled() {
        return isCheckedIconVisible();
    }

    public boolean isCheckedIconVisible() {
        return this.checkedIconVisible;
    }

    @Deprecated
    public boolean isChipIconEnabled() {
        return isChipIconVisible();
    }

    public boolean isChipIconVisible() {
        return this.chipIconVisible;
    }

    @Deprecated
    public boolean isCloseIconEnabled() {
        return isCloseIconVisible();
    }

    public boolean isCloseIconStateful() {
        return isStateful(this.closeIcon);
    }

    public boolean isCloseIconVisible() {
        return this.closeIconVisible;
    }

    public boolean isShapeThemingEnabled() {
        return this.isShapeThemingEnabled;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public boolean isStateful() {
        if (isStateful(this.chipSurfaceColor) == false) goto L5;
        return true;
    L5:
        if (isStateful(this.chipBackgroundColor) == false) goto L7;
        return true;
    L7:
        if (isStateful(this.chipStrokeColor) == false) goto L9;
        return true;
    L9:
        if (this.useCompatRipple == false) goto L13;
        if (isStateful(this.compatRippleColor) == false) goto L13;
        return true;
    L13:
        if (isStateful(this.textDrawableHelper.getTextAppearance()) == false) goto L15;
        return true;
    L15:
        if (canShowCheckedIcon() == false) goto L17;
        return true;
    L17:
        if (isStateful(this.chipIcon) == false) goto L19;
        return true;
    L19:
        if (isStateful(this.checkedIcon) == false) goto L21;
        return true;
    L21:
        if (isStateful(this.tint) == true) goto L34;
        return false;
    L34:
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int r3) {
        boolean r0 = super.onLayoutDirectionChanged(r3);
        if (showsChipIcon() == false) goto L6;
        r0 = r0 | DrawableCompat.setLayoutDirection(this.chipIcon, r3);
    L6:
        if (showsCheckedIcon() == false) goto L9;
        r0 = r0 | DrawableCompat.setLayoutDirection(this.checkedIcon, r3);
    L9:
        if (showsCloseIcon() == false) goto L11;
        r0 = r0 | DrawableCompat.setLayoutDirection(this.closeIcon, r3);
    L11:
        if (r0 == false) goto L15;
        invalidateSelf();
        return true;
    L15:
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int r3) {
        boolean r0 = super.onLevelChange(r3);
        if (showsChipIcon() == false) goto L6;
        r0 = r0 | this.chipIcon.setLevel(r3);
    L6:
        if (showsCheckedIcon() == false) goto L9;
        r0 = r0 | this.checkedIcon.setLevel(r3);
    L9:
        if (showsCloseIcon() == false) goto L11;
        r0 = r0 | this.closeIcon.setLevel(r3);
    L11:
        if (r0 == false) goto L13;
        invalidateSelf();
    L13:
        return r0;
    }

    public void onSizeChange() {
        Delegate r0 = this.delegate.get();
        if (r0 == null) goto L6;
        r0.onChipDrawableSizeChange();
        return;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(int[] r2) {
        if (this.isShapeThemingEnabled == false) goto L6;
        super.onStateChange(r2);
    L6:
        return onStateChange(r2, getCloseIconState());
    }

    @Override // com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public void onTextSizeChange() {
        onSizeChange();
        invalidateSelf();
    }

    public boolean refreshCloseIconFocus(boolean r3) {
        if (this.closeIcon == null) goto L9;
        if (r3 == false) goto L6;
        int[] r32 = {R.attr.state_pressed, R.attr.state_enabled};
    L8:
        return setCloseIconState(r32);
    L6:
        r32 = DEFAULT_STATE;
        goto L8
    L9:
        return false;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
        Drawable.Callback r12 = getCallback();
        if (r12 == null) goto L6;
        r12.scheduleDrawable(this, r2, r3);
        return;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        if (this.alpha == r2) goto L6;
        this.alpha = r2;
        invalidateSelf();
        return;
    }

    public void setCheckable(boolean r2) {
        if (this.checkable == r2) goto L13;
        this.checkable = r2;
        float r0 = calculateChipIconWidth();
        if (r2 == false) goto L7;
    L9:
        float r22 = calculateChipIconWidth();
        invalidateSelf();
        if (r0 == r22) goto L14;
        onSizeChange();
        return;
    L14:
        return;
    L7:
        if (this.currentChecked == false) goto L9;
        this.currentChecked = false;
        goto L9
    }

    public void setCheckableResource(int r2) {
        setCheckable(this.context.getResources().getBoolean(r2));
    }

    public void setCheckedIcon(Drawable r3) {
        if (this.checkedIcon == r3) goto L8;
        float r0 = calculateChipIconWidth();
        this.checkedIcon = r3;
        float r32 = calculateChipIconWidth();
        unapplyChildDrawable(this.checkedIcon);
        applyChildDrawable(this.checkedIcon);
        invalidateSelf();
        if (r0 == r32) goto L9;
        onSizeChange();
        return;
    L9:
        return;
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean r1) {
        setCheckedIconVisible(r1);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int r2) {
        setCheckedIconVisible(this.context.getResources().getBoolean(r2));
    }

    public void setCheckedIconResource(int r2) {
        setCheckedIcon(AppCompatResources.getDrawable(this.context, r2));
    }

    public void setCheckedIconTint(ColorStateList r2) {
        if (this.checkedIconTint == r2) goto L9;
        this.checkedIconTint = r2;
        if (canShowCheckedIcon() == false) goto L7;
        this.checkedIcon.setTintList(r2);
    L7:
        onStateChange(getState());
        return;
    }

    public void setCheckedIconTintResource(int r2) {
        setCheckedIconTint(AppCompatResources.getColorStateList(this.context, r2));
    }

    public void setCheckedIconVisible(int r2) {
        setCheckedIconVisible(this.context.getResources().getBoolean(r2));
    }

    public void setCheckedIconVisible(boolean r2) {
        if (this.checkedIconVisible == r2) goto L12;
        boolean r0 = showsCheckedIcon();
        this.checkedIconVisible = r2;
        boolean r22 = showsCheckedIcon();
        if (r0 == r22) goto L13;
        Drawable r02 = this.checkedIcon;
        if (r22 == false) goto L9;
        applyChildDrawable(r02);
    L10:
        invalidateSelf();
        onSizeChange();
        return;
    L9:
        unapplyChildDrawable(r02);
        goto L10
    L13:
        return;
    }

    public void setChipBackgroundColor(ColorStateList r2) {
        if (this.chipBackgroundColor == r2) goto L6;
        this.chipBackgroundColor = r2;
        onStateChange(getState());
        return;
    }

    public void setChipBackgroundColorResource(int r2) {
        setChipBackgroundColor(AppCompatResources.getColorStateList(this.context, r2));
    }

    @Deprecated
    public void setChipCornerRadius(float r2) {
        if (this.chipCornerRadius == r2) goto L6;
        this.chipCornerRadius = r2;
        setShapeAppearanceModel(getShapeAppearanceModel().withCornerSize(r2));
        return;
    }

    @Deprecated
    public void setChipCornerRadiusResource(int r2) {
        setChipCornerRadius(this.context.getResources().getDimension(r2));
    }

    public void setChipEndPadding(float r2) {
        if (this.chipEndPadding == r2) goto L6;
        this.chipEndPadding = r2;
        invalidateSelf();
        onSizeChange();
        return;
    }

    public void setChipEndPaddingResource(int r2) {
        setChipEndPadding(this.context.getResources().getDimension(r2));
    }

    public void setChipIcon(Drawable r3) {
        Drawable r0 = getChipIcon();
        if (r0 == r3) goto L15;
        float r1 = calculateChipIconWidth();
        if (r3 == null) goto L7;
        Drawable r32 = DrawableCompat.wrap(r3).mutate();
    L8:
        this.chipIcon = r32;
        float r33 = calculateChipIconWidth();
        unapplyChildDrawable(r0);
        if (showsChipIcon() == false) goto L11;
        applyChildDrawable(this.chipIcon);
    L11:
        invalidateSelf();
        if (r1 == r33) goto L16;
        onSizeChange();
        return;
    L16:
        return;
    L7:
        r32 = null;
        goto L8
    }

    @Deprecated
    public void setChipIconEnabled(boolean r1) {
        setChipIconVisible(r1);
    }

    @Deprecated
    public void setChipIconEnabledResource(int r1) {
        setChipIconVisible(r1);
    }

    public void setChipIconResource(int r2) {
        setChipIcon(AppCompatResources.getDrawable(this.context, r2));
    }

    public void setChipIconSize(float r2) {
        if (this.chipIconSize == r2) goto L8;
        float r0 = calculateChipIconWidth();
        this.chipIconSize = r2;
        float r22 = calculateChipIconWidth();
        invalidateSelf();
        if (r0 == r22) goto L9;
        onSizeChange();
        return;
    L9:
        return;
    }

    public void setChipIconSizeResource(int r2) {
        setChipIconSize(this.context.getResources().getDimension(r2));
    }

    public void setChipIconTint(ColorStateList r2) {
        this.hasChipIconTint = true;
        if (this.chipIconTint == r2) goto L9;
        this.chipIconTint = r2;
        if (showsChipIcon() == false) goto L7;
        this.chipIcon.setTintList(r2);
    L7:
        onStateChange(getState());
        return;
    }

    public void setChipIconTintResource(int r2) {
        setChipIconTint(AppCompatResources.getColorStateList(this.context, r2));
    }

    public void setChipIconVisible(int r2) {
        setChipIconVisible(this.context.getResources().getBoolean(r2));
    }

    public void setChipIconVisible(boolean r2) {
        if (this.chipIconVisible == r2) goto L12;
        boolean r0 = showsChipIcon();
        this.chipIconVisible = r2;
        boolean r22 = showsChipIcon();
        if (r0 == r22) goto L13;
        Drawable r02 = this.chipIcon;
        if (r22 == false) goto L9;
        applyChildDrawable(r02);
    L10:
        invalidateSelf();
        onSizeChange();
        return;
    L9:
        unapplyChildDrawable(r02);
        goto L10
    L13:
        return;
    }

    public void setChipMinHeight(float r2) {
        if (this.chipMinHeight == r2) goto L6;
        this.chipMinHeight = r2;
        invalidateSelf();
        onSizeChange();
        return;
    }

    public void setChipMinHeightResource(int r2) {
        setChipMinHeight(this.context.getResources().getDimension(r2));
    }

    public void setChipStartPadding(float r2) {
        if (this.chipStartPadding == r2) goto L6;
        this.chipStartPadding = r2;
        invalidateSelf();
        onSizeChange();
        return;
    }

    public void setChipStartPaddingResource(int r2) {
        setChipStartPadding(this.context.getResources().getDimension(r2));
    }

    public void setChipStrokeColor(ColorStateList r2) {
        if (this.chipStrokeColor == r2) goto L9;
        this.chipStrokeColor = r2;
        if (this.isShapeThemingEnabled == false) goto L7;
        setStrokeColor(r2);
    L7:
        onStateChange(getState());
        return;
    }

    public void setChipStrokeColorResource(int r2) {
        setChipStrokeColor(AppCompatResources.getColorStateList(this.context, r2));
    }

    public void setChipStrokeWidth(float r2) {
        if (this.chipStrokeWidth == r2) goto L9;
        this.chipStrokeWidth = r2;
        this.chipPaint.setStrokeWidth(r2);
        if (this.isShapeThemingEnabled == false) goto L7;
        super.setStrokeWidth(r2);
    L7:
        invalidateSelf();
        return;
    }

    public void setChipStrokeWidthResource(int r2) {
        setChipStrokeWidth(this.context.getResources().getDimension(r2));
    }

    public void setCloseIcon(Drawable r3) {
        Drawable r0 = getCloseIcon();
        if (r0 == r3) goto L15;
        float r1 = calculateCloseIconWidth();
        if (r3 == null) goto L7;
        Drawable r32 = DrawableCompat.wrap(r3).mutate();
    L8:
        this.closeIcon = r32;
        updateFrameworkCloseIconRipple();
        float r33 = calculateCloseIconWidth();
        unapplyChildDrawable(r0);
        if (showsCloseIcon() == false) goto L11;
        applyChildDrawable(this.closeIcon);
    L11:
        invalidateSelf();
        if (r1 == r33) goto L16;
        onSizeChange();
        return;
    L16:
        return;
    L7:
        r32 = null;
        goto L8
    }

    public void setCloseIconContentDescription(CharSequence r2) {
        if (this.closeIconContentDescription == r2) goto L6;
        this.closeIconContentDescription = BidiFormatter.getInstance().unicodeWrap(r2);
        invalidateSelf();
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

    public void setCloseIconEndPadding(float r2) {
        if (this.closeIconEndPadding == r2) goto L8;
        this.closeIconEndPadding = r2;
        invalidateSelf();
        if (showsCloseIcon() == false) goto L9;
        onSizeChange();
        return;
    L9:
        return;
    }

    public void setCloseIconEndPaddingResource(int r2) {
        setCloseIconEndPadding(this.context.getResources().getDimension(r2));
    }

    public void setCloseIconResource(int r2) {
        setCloseIcon(AppCompatResources.getDrawable(this.context, r2));
    }

    public void setCloseIconSize(float r2) {
        if (this.closeIconSize == r2) goto L8;
        this.closeIconSize = r2;
        invalidateSelf();
        if (showsCloseIcon() == false) goto L9;
        onSizeChange();
        return;
    L9:
        return;
    }

    public void setCloseIconSizeResource(int r2) {
        setCloseIconSize(this.context.getResources().getDimension(r2));
    }

    public void setCloseIconStartPadding(float r2) {
        if (this.closeIconStartPadding == r2) goto L8;
        this.closeIconStartPadding = r2;
        invalidateSelf();
        if (showsCloseIcon() == false) goto L9;
        onSizeChange();
        return;
    L9:
        return;
    }

    public void setCloseIconStartPaddingResource(int r2) {
        setCloseIconStartPadding(this.context.getResources().getDimension(r2));
    }

    public boolean setCloseIconState(int[] r2) {
        if (Arrays.equals(this.closeIconStateSet, r2) == true) goto L8;
        this.closeIconStateSet = r2;
        if (showsCloseIcon() == true) goto L7;
        return false;
    L7:
        return onStateChange(getState(), r2);
    L8:
        return false;
    }

    public void setCloseIconTint(ColorStateList r2) {
        if (this.closeIconTint == r2) goto L9;
        this.closeIconTint = r2;
        if (showsCloseIcon() == false) goto L7;
        this.closeIcon.setTintList(r2);
    L7:
        onStateChange(getState());
        return;
    }

    public void setCloseIconTintResource(int r2) {
        setCloseIconTint(AppCompatResources.getColorStateList(this.context, r2));
    }

    public void setCloseIconVisible(int r2) {
        setCloseIconVisible(this.context.getResources().getBoolean(r2));
    }

    public void setCloseIconVisible(boolean r2) {
        if (this.closeIconVisible == r2) goto L12;
        boolean r0 = showsCloseIcon();
        this.closeIconVisible = r2;
        boolean r22 = showsCloseIcon();
        if (r0 == r22) goto L13;
        Drawable r02 = this.closeIcon;
        if (r22 == false) goto L9;
        applyChildDrawable(r02);
    L10:
        invalidateSelf();
        onSizeChange();
        return;
    L9:
        unapplyChildDrawable(r02);
        goto L10
    L13:
        return;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        if (this.colorFilter == r2) goto L6;
        this.colorFilter = r2;
        invalidateSelf();
        return;
    }

    public void setDelegate(Delegate r2) {
        this.delegate = new WeakReference(r2);
    }

    public void setEllipsize(TextUtils.TruncateAt r1) {
        this.truncateAt = r1;
    }

    public void setFontVariationSettings(String r3) {
        TextAppearance r2 = getTextAppearance();
        if (r2 != null) goto L5;
        return;
    L5:
        if (Build.VERSION.SDK_INT < 26) goto L9;
        r2.setFontVariationSettings(r3);
        return;
    }

    public void setHideMotionSpec(MotionSpec r1) {
        this.hideMotionSpec = r1;
    }

    public void setHideMotionSpecResource(int r2) {
        setHideMotionSpec(MotionSpec.createFromResource(this.context, r2));
    }

    public void setIconEndPadding(float r2) {
        if (this.iconEndPadding == r2) goto L8;
        float r0 = calculateChipIconWidth();
        this.iconEndPadding = r2;
        float r22 = calculateChipIconWidth();
        invalidateSelf();
        if (r0 == r22) goto L9;
        onSizeChange();
        return;
    L9:
        return;
    }

    public void setIconEndPaddingResource(int r2) {
        setIconEndPadding(this.context.getResources().getDimension(r2));
    }

    public void setIconStartPadding(float r2) {
        if (this.iconStartPadding == r2) goto L8;
        float r0 = calculateChipIconWidth();
        this.iconStartPadding = r2;
        float r22 = calculateChipIconWidth();
        invalidateSelf();
        if (r0 == r22) goto L9;
        onSizeChange();
        return;
    L9:
        return;
    }

    public void setIconStartPaddingResource(int r2) {
        setIconStartPadding(this.context.getResources().getDimension(r2));
    }

    public void setMaxWidth(int r1) {
        this.maxWidth = r1;
    }

    public void setRippleColor(ColorStateList r2) {
        if (this.rippleColor == r2) goto L6;
        this.rippleColor = r2;
        updateCompatRippleColor();
        onStateChange(getState());
        return;
    }

    public void setRippleColorResource(int r2) {
        setRippleColor(AppCompatResources.getColorStateList(this.context, r2));
    }

    public void setShouldDrawText(boolean r1) {
        this.shouldDrawText = r1;
    }

    public void setShowMotionSpec(MotionSpec r1) {
        this.showMotionSpec = r1;
    }

    public void setShowMotionSpecResource(int r2) {
        setShowMotionSpec(MotionSpec.createFromResource(this.context, r2));
    }

    public void setText(CharSequence r2) {
        if (r2 != null) goto L5;
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        if (TextUtils.equals(this.text, r2) == true) goto L8;
        this.text = r2;
        this.textDrawableHelper.setTextWidthDirty(true);
        invalidateSelf();
        onSizeChange();
        return;
    }

    public void setTextAppearance(TextAppearance r2) {
        this.textDrawableHelper.setTextAppearance(r2, this.context);
    }

    public void setTextAppearanceResource(int r3) {
        setTextAppearance(new TextAppearance(this.context, r3));
    }

    public void setTextColor(int r1) {
        setTextColor(ColorStateList.valueOf(r1));
    }

    public void setTextColor(ColorStateList r2) {
        TextAppearance r0 = getTextAppearance();
        if (r0 == null) goto L6;
        r0.setTextColor(r2);
        invalidateSelf();
        return;
    }

    public void setTextEndPadding(float r2) {
        if (this.textEndPadding == r2) goto L6;
        this.textEndPadding = r2;
        invalidateSelf();
        onSizeChange();
        return;
    }

    public void setTextEndPaddingResource(int r2) {
        setTextEndPadding(this.context.getResources().getDimension(r2));
    }

    public void setTextResource(int r2) {
        setText(this.context.getResources().getString(r2));
    }

    public void setTextSize(float r2) {
        TextAppearance r0 = getTextAppearance();
        if (r0 == null) goto L6;
        r0.setTextSize(r2);
        this.textDrawableHelper.getTextPaint().setTextSize(r2);
        onTextSizeChange();
        return;
    }

    public void setTextStartPadding(float r2) {
        if (this.textStartPadding == r2) goto L6;
        this.textStartPadding = r2;
        invalidateSelf();
        onSizeChange();
        return;
    }

    public void setTextStartPaddingResource(int r2) {
        setTextStartPadding(this.context.getResources().getDimension(r2));
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void setTintList(ColorStateList r2) {
        if (this.tint == r2) goto L6;
        this.tint = r2;
        onStateChange(getState());
        return;
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode r2) {
        if (this.tintMode == r2) goto L6;
        this.tintMode = r2;
        this.tintFilter = DrawableUtils.updateTintFilter(this, this.tint, r2);
        invalidateSelf();
        return;
    }

    public void setUseCompatRipple(boolean r2) {
        if (this.useCompatRipple == r2) goto L6;
        this.useCompatRipple = r2;
        updateCompatRippleColor();
        onStateChange(getState());
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean r3, boolean r4) {
        boolean r0 = super.setVisible(r3, r4);
        if (showsChipIcon() == false) goto L6;
        r0 = r0 | this.chipIcon.setVisible(r3, r4);
    L6:
        if (showsCheckedIcon() == false) goto L9;
        r0 = r0 | this.checkedIcon.setVisible(r3, r4);
    L9:
        if (showsCloseIcon() == false) goto L11;
        r0 = r0 | this.closeIcon.setVisible(r3, r4);
    L11:
        if (r0 == false) goto L13;
        invalidateSelf();
    L13:
        return r0;
    }

    public boolean shouldDrawText() {
        return this.shouldDrawText;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable r1, Runnable r2) {
        Drawable.Callback r12 = getCallback();
        if (r12 == null) goto L6;
        r12.unscheduleDrawable(this, r2);
        return;
    }
}
