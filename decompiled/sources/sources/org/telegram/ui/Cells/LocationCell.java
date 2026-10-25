package org.telegram.ui.Cells;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class LocationCell extends FrameLayout {
    private static FlickerLoadingView globalGradientView;
    private AnimatedTextView addressTextView;
    private boolean allowTextAnimation;
    private ShapeDrawable circleDrawable;
    private float enterAlpha;
    private ValueAnimator enterAnimator;
    private BackupImageView imageView;
    private CharSequence lastCompleteTitle;
    private String lastEmoji;
    private String lastTitle;
    private AnimatedTextView nameTextView;
    private boolean needDivider;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean wrapContent;

    public static /* synthetic */ void $r8$lambda$cIkUTMzqY4VgE_cNq3TVNN5DKzQ(LocationCell r0, long r1, long r3, float r5, float r6, ValueAnimator r7) {
        r0.lambda$setLocation$0(r1, r3, r5, r6, r7);
    }

    public LocationCell(Context r24, boolean r25, Theme.ResourcesProvider r26) {
        super(r24);
        this.enterAlpha = 0.0f;
        this.resourcesProvider = r26;
        this.wrapContent = r25;
        BackupImageView r3 = new BackupImageView(r24);
        this.imageView = r3;
        ShapeDrawable r4 = Theme.createCircleDrawable(AndroidUtilities.dp(42.0f), -1);
        this.circleDrawable = r4;
        r3.setBackground(r4);
        this.imageView.setSize(AndroidUtilities.dp(30.0f), AndroidUtilities.dp(30.0f));
        BackupImageView r32 = this.imageView;
        boolean r42 = LocaleController.isRTL;
        int r5 = 3;
        if (r42 == false) goto L5;
        int r7 = 5;
    L6:
        int r10 = r7 | 48;
        if (r42 == false) goto L9;
        float r11 = 0.0f;
    L10:
        if (r42 == false) goto L12;
        float r13 = 15.0f;
    L13:
        addView(r32, LayoutHelper.createFrame(42, 42.0f, r10, r11, 11.0f, r13, 0.0f));
        AnimatedTextView r72 = new AnimatedTextView(r24, true, true, true);
        this.nameTextView = r72;
        CubicBezierInterpolator r132 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r72.setAnimationProperties(0.4f, 0, 350, r132);
        this.nameTextView.setScaleProperty(0.6f);
        this.nameTextView.setTextSize(AndroidUtilities.dp(16.0f));
        this.nameTextView.setEllipsizeByGradient(true);
        this.nameTextView.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        this.nameTextView.setTypeface(AndroidUtilities.bold());
        AnimatedTextView r33 = this.nameTextView;
        if (LocaleController.isRTL == false) goto L16;
        int r73 = 5;
    L17:
        r33.setGravity(r73);
        this.nameTextView.getDrawable().setOverrideFullWidth(AndroidUtilities.displaySize.x);
        NotificationCenter.listenEmojiLoading(this.nameTextView);
        AnimatedTextView r34 = this.nameTextView;
        boolean r74 = LocaleController.isRTL;
        if (r74 == false) goto L20;
        int r8 = 5;
    L21:
        int r16 = r8 | 48;
        if (r74 == false) goto L24;
        int r82 = 16;
    L25:
        float r83 = r82;
        if (r74 == false) goto L28;
        int r75 = 73;
    L29:
        addView(r34, LayoutHelper.createFrame(-1, 22.0f, r16, r83, 10.0f, r75, 0.0f));
        AnimatedTextView r35 = new AnimatedTextView(r24, true, true, true);
        this.addressTextView = r35;
        r35.setScaleProperty(0.6f);
        this.addressTextView.setAnimationProperties(0.4f, 0, 350, r132);
        this.addressTextView.setTextSize(AndroidUtilities.dp(14.0f));
        this.addressTextView.setEllipsizeByGradient(true);
        this.addressTextView.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText3));
        AnimatedTextView r1 = this.addressTextView;
        if (LocaleController.isRTL == false) goto L32;
        int r2 = 5;
    L33:
        r1.setGravity(r2);
        AnimatedTextView r12 = this.addressTextView;
        boolean r22 = LocaleController.isRTL;
        if (r22 == false) goto L36;
        r5 = 5;
    L36:
        int r84 = r5 | 48;
        if (r22 == false) goto L39;
        int r36 = 16;
    L40:
        float r9 = r36;
        if (r22 == false) goto L43;
        int r23 = 73;
    L44:
        addView(r12, LayoutHelper.createFrame(-1, 20.0f, r84, r9, 35.0f, r23, 0.0f));
        this.imageView.setAlpha(this.enterAlpha);
        this.nameTextView.setAlpha(this.enterAlpha);
        this.addressTextView.setAlpha(this.enterAlpha);
        return;
    L43:
        r23 = 16;
        goto L44
    L39:
        r36 = 73;
        goto L40
    L32:
        r2 = 3;
        goto L33
    L28:
        r75 = 16;
        goto L29
    L24:
        r82 = 73;
        goto L25
    L20:
        r8 = 3;
        goto L21
    L16:
        r73 = 3;
        goto L17
    L12:
        r13 = 0.0f;
        goto L13
    L9:
        r11 = 15.0f;
        goto L10
    L5:
        r7 = 3;
        goto L6
    }

    public static int getColorForIndex(int r1) {
        int r12 = r1 % 7;
        if (r12 != 0) goto L5;
        return -1351584;
    L5:
        if (r12 != 1) goto L7;
        return -868277;
    L7:
        if (r12 != 2) goto L9;
        return -12214795;
    L9:
        if (r12 != 3) goto L11;
        return -13187226;
    L11:
        if (r12 != 4) goto L13;
        return -7900675;
    L13:
        if (r12 == 5) goto L16;
        return -1285237;
    L16:
        return -12338729;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private CharSequence getTitle(TLRPC.TL_messageMediaVenue r4) {
        if (r4 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        if (TextUtils.equals(this.lastEmoji, r4.emoji) == true) goto L8;
    L11:
        CharSequence r0 = r4.title;
        if (TextUtils.isEmpty(r4.emoji) == true) goto L14;
        r0 = Emoji.replaceEmoji(r4.emoji + " " + r0, this.nameTextView.getPaint().getFontMetricsInt(), false);
    L14:
        this.lastEmoji = r4.emoji;
        this.lastTitle = r4.title;
        this.lastCompleteTitle = r0;
        return r0;
    L8:
        if (TextUtils.equals(this.lastTitle, r4.title) == false) goto L11;
        return this.lastCompleteTitle;
    }

    private /* synthetic */ void lambda$setLocation$0(long r3, long r5, float r7, float r8, ValueAnimator r9) {
        float r4 = 1.0f;
        float r32 = Math.min(Math.max((SystemClock.elapsedRealtime() - r3) / r5, 0.0f), 1.0f);
        if (r5 <= 0) goto L6;
        r4 = r32;
    L6:
        float r33 = AndroidUtilities.lerp(r7, r8, r4);
        this.enterAlpha = r33;
        this.imageView.setAlpha(r33);
        this.nameTextView.setAlpha(this.enterAlpha);
        this.addressTextView.setAlpha(this.enterAlpha);
        invalidate();
    }

    private void updateContentDescription(TLRPC.TL_messageMediaVenue r4, String r5) {
        if (r4 == null) goto L34;
    L8:
        StringBuilder r1 = new StringBuilder();     // Catch: Exception -> L31
        if (r4 == null) goto L14;
        if (TextUtils.isEmpty(r4.title) == true) goto L14;
        r1.append(r4.title);     // Catch: Exception -> L31
    L14:
        if (TextUtils.isEmpty(r5) == false) goto L20;
        if (r4 == null) goto L18;
        r5 = r4.address;     // Catch: Exception -> L31
        goto L20
    L18:
        r5 = null;
    L20:
        if (TextUtils.isEmpty(r5) == true) goto L26;
        if (r1.length() <= 0) goto L24;
        r1.append(", ");     // Catch: Exception -> L31
    L24:
        r1.append(r5);     // Catch: Exception -> L31
    L26:
        if (r1.length() <= 0) goto L28;
        String r42 = r1.toString();     // Catch: Exception -> L31
    L29:
        setContentDescription(r42);     // Catch: Exception -> L31
        return;
    L28:
        r42 = null;
        goto L29
    L34:
    L31:
        setContentDescription(null);     // Catch: Exception -> L33
        return;
    L5:
        if (TextUtils.isEmpty(r5) == false) goto L8;
        setContentDescription(null);     // Catch: Exception -> L31
        return;
    }

    public BackupImageView getImageView() {
        return this.imageView;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ValueAnimator r0 = this.enterAnimator;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getDuration() == LongCompanionObject.MAX_VALUE) goto L7;
        return;
    L7:
        if (this.enterAnimator.isRunning() == true) goto L12;
        this.enterAnimator.start();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ValueAnimator r0 = this.enterAnimator;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getDuration() != LongCompanionObject.MAX_VALUE) goto L9;
        this.enterAnimator.cancel();
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r11) {
        if (globalGradientView != null) goto L6;
        FlickerLoadingView r1 = new FlickerLoadingView(getContext(), this.resourcesProvider);
        globalGradientView = r1;
        r1.setIsSingleCell(true);
    L6:
        if ((getParent() instanceof ViewGroup) == false) goto L8;
        int r12 = ((ViewGroup) getParent()).indexOfChild(this);
    L9:
        globalGradientView.setParentSize(getMeasuredWidth(), getMeasuredHeight(), (-r12) * AndroidUtilities.dp(56.0f));
        globalGradientView.setViewType(4);
        globalGradientView.updateColors();
        globalGradientView.updateGradient();
        r11.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) ((1.0f - this.enterAlpha) * 255.0f), 31);
        r11.translate(AndroidUtilities.dp(2.0f), (getMeasuredHeight() - AndroidUtilities.dp(56.0f)) / 2);
        globalGradientView.draw(r11);
        r11.restore();
        super.onDraw(r11);
        if (this.needDivider == false) goto L27;
        Theme.ResourcesProvider r2 = this.resourcesProvider;
        if (r2 != null) goto L14;
        Paint r22 = null;
    L15:
        if (r22 != null) goto L17;
        r22 = Theme.dividerPaint;
    L17:
        Paint r5 = r22;
        if (LocaleController.isRTL == false) goto L20;
        float r23 = 0.0f;
    L21:
        float r4 = getHeight() - 1;
        if (LocaleController.isRTL == false) goto L24;
        float r3 = getWidth() - AndroidUtilities.dp(72.0f);
    L25:
        r11.drawLine(r23, r4, r3, getHeight() - 1, r5);
        return;
    L24:
        r3 = getWidth();
        goto L25
    L20:
        r23 = AndroidUtilities.dp(72.0f);
        goto L21
    L14:
        r22 = r2.getPaint("paintDivider");
        goto L15
    L27:
        return;
    L8:
        r12 = 0;
        goto L9
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        if (this.wrapContent == false) goto L6;
        super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
        return;
    L6:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
    }

    public void setAllowTextAnimation(boolean r1) {
        this.allowTextAnimation = r1;
    }

    public void setLocation(TLRPC.TL_messageMediaVenue r7, int r8, boolean r9) {
        setLocation(r7, null, r8, r9, false);
    }

    public void setLocation(TLRPC.TL_messageMediaVenue r10, String r11, int r12, boolean r13, boolean r14) {
        this.needDivider = r13;
        if (r10 == null) goto L12;
        AnimatedTextView r1 = this.nameTextView;
        CharSequence r2 = getTitle(r10);
        if (this.allowTextAnimation == true) goto L7;
    L10:
        boolean r3 = false;
    L11:
        r1.setText(r2, r3);
        goto L12
    L7:
        if (LocaleController.isRTL == true) goto L10;
        if (r14 == false) goto L10;
        r3 = true;
    L12:
        if (r11 == null) goto L20;
        AnimatedTextView r142 = this.addressTextView;
        if (this.allowTextAnimation == true) goto L16;
    L18:
        boolean r15 = false;
    L19:
        r142.setText(r11, r15);
    L29:
        int r122 = getColorForIndex(r12);
        if (r10 == null) goto L40;
        String r143 = r10.icon;
        if (r143 == null) goto L40;
        if ("pin".equals(r143) == false) goto L36;
    L39:
        Drawable r144 = getResources().getDrawable(R.drawable.pin).mutate();
        r144.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_location_sendLocationIcon), PorterDuff.Mode.MULTIPLY));
        CombinedDrawable r16 = new CombinedDrawable(Theme.createCircleDrawable(AndroidUtilities.dp(42.0f), 0), r144);
        r16.setCustomSize(AndroidUtilities.dp(42.0f), AndroidUtilities.dp(42.0f));
        r16.setIconSize(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
        this.imageView.setImageDrawable(r16);
        goto L40
    L36:
        if (r10.icon.startsWith("emoji") == true) goto L39;
        this.imageView.setImage(r10.icon, null, null);
    L40:
        this.circleDrawable.getPaint().setColor(r122);
        setWillNotDraw(false);
        if (r10 != null) goto L43;
        boolean r123 = true;
    L44:
        setClickable(r123);
        ValueAnimator r124 = this.enterAnimator;
        if (r124 == null) goto L47;
        r124.cancel();
    L47:
        if (r10 != null) goto L49;
        boolean r125 = true;
    L50:
        float r7 = this.enterAlpha;
        if (r125 == false) goto L54;
        float r145 = 0.0f;
    L53:
        float r8 = r145;
        long r5 = (long) (Math.abs(r7 - r8) * 150.0f);
        this.enterAnimator = ValueAnimator.ofFloat(new float[]{r7, r8});
        this.enterAnimator.addUpdateListener(new LocationCell$$ExternalSyntheticLambda0(this, SystemClock.elapsedRealtime(), r5, r7, r8));
        ValueAnimator r9 = this.enterAnimator;
        if (r125 == false) goto L58;
        r5 = LongCompanionObject.MAX_VALUE;
    L58:
        r9.setDuration(r5);
        if (r125 == true) goto L61;
    L62:
        this.enterAnimator.start();
    L63:
        this.imageView.setAlpha(r7);
        this.nameTextView.setAlpha(r7);
        this.addressTextView.setAlpha(r7);
        updateContentDescription(r10, r11);
        invalidate();
        return;
    L61:
        if (isAttachedToWindow() == false) goto L63;
    L54:
        r145 = 1.0f;
        goto L53
    L49:
        r125 = false;
        goto L50
    L43:
        r123 = false;
        goto L44
    L16:
        if (LocaleController.isRTL == true) goto L18;
        r15 = true;
        goto L19
    L20:
        if (r10 == null) goto L29;
        AnimatedTextView r17 = this.addressTextView;
        String r22 = r10.address;
        if (this.allowTextAnimation == true) goto L24;
    L27:
        boolean r146 = false;
    L28:
        r17.setText(r22, r146);
        goto L29
    L24:
        if (LocaleController.isRTL == true) goto L27;
        if (r14 == false) goto L27;
        r146 = true;
        goto L28
    }
}
