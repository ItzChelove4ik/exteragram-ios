package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.Switch;
import org.telegram.ui.FilterCreateActivity;
import org.telegram.ui.PeerColorActivity;
import org.telegram.ui.SettingsActivity;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes6.dex */
public class TextCell extends FrameLayout {
    private boolean attached;
    private int changeProgressStartDelay;
    private Switch checkBox;
    private boolean drawLoading;
    private float drawLoadingProgress;
    private AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable emojiDrawable;
    public int heightDp;
    public int imageLeft;
    public final RLottieImageView imageView;
    private boolean inDialogs;
    private boolean incrementLoadingProgress;
    private boolean isIcon;
    private int lastWidth;
    public int leftPadding;
    private float loadingProgress;
    private int loadingSize;
    private boolean measureDelay;
    private boolean needDivider;
    public int offsetFromImage;
    Paint paint;
    private boolean prioritizeTitleOverValue;
    private Theme.ResourcesProvider resourcesProvider;
    public final SimpleTextView subtitleView;
    public final SimpleTextView textView;
    public int valueImageRight;
    private ImageView valueImageView;
    public final SimpleTextView valueSpoilersTextView;
    private CharSequence valueText;
    public final AnimatedTextView valueTextView;

    /* renamed from: org.telegram.ui.Cells.TextCell$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ TextCell this$0;
        final /* synthetic */ ImageReceiver val$imageReceiver;

        public AnonymousClass1(TextCell r1, ImageReceiver r2) {
            this.this$0 = r1;
            this.val$imageReceiver = r2;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
            this.val$imageReceiver.onAttachedToWindow();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
            this.val$imageReceiver.onDetachedFromWindow();
        }
    }

    /* renamed from: org.telegram.ui.Cells.TextCell$2, reason: invalid class name */
    public class AnonymousClass2 extends Drawable {
        final /* synthetic */ TextCell this$0;
        final /* synthetic */ ImageReceiver val$imageReceiver;

        public AnonymousClass2(TextCell r1, ImageReceiver r2) {
            this.this$0 = r1;
            this.val$imageReceiver = r2;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r3) {
            this.val$imageReceiver.setImageCoords(getBounds());
            this.val$imageReceiver.draw(r3);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(30.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return AndroidUtilities.dp(30.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r2) {
            this.val$imageReceiver.setAlpha(r2 / 255.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
            this.val$imageReceiver.setColorFilter(r1);
        }
    }

    public TextCell(Context r7) {
        this(r7, 21, false, false, null);
    }

    public TextCell(Context r7, int r8, boolean r9) {
        this(r7, r8, r9, false, null);
    }

    public TextCell(Context r18, int r19, boolean r20, boolean r21, Theme.ResourcesProvider r22) {
        super(r18);
        this.offsetFromImage = 71;
        this.heightDp = 50;
        this.imageLeft = 21;
        this.resourcesProvider = r22;
        this.leftPadding = r19;
        this.valueImageRight = r19;
        SimpleTextView r2 = new SimpleTextView(r18);
        this.textView = r2;
        if (r20 == false) goto L5;
        int r4 = Theme.key_dialogTextBlack;
    L6:
        r2.setTextColor(Theme.getColor(r4, r22));
        r2.setTextSize(16);
        int r6 = 3;
        if (LocaleController.isRTL == false) goto L9;
        int r5 = 5;
    L10:
        r2.setGravity(r5);
        r2.setImportantForAccessibility(2);
        addView(r2, LayoutHelper.createFrame(-2, -1.0f));
        SimpleTextView r23 = new SimpleTextView(r18);
        this.subtitleView = r23;
        if (r20 == false) goto L13;
        int r10 = Theme.key_dialogTextGray;
    L14:
        r23.setTextColor(Theme.getColor(r10, r22));
        r23.setTextSize(13);
        if (LocaleController.isRTL == false) goto L17;
        int r102 = 5;
    L18:
        r23.setGravity(r102);
        r23.setImportantForAccessibility(2);
        addView(r23, LayoutHelper.createFrame(-2, -1.0f));
        AnimatedTextView r24 = new AnimatedTextView(r18, false, true, true);
        this.valueTextView = r24;
        if (r20 == false) goto L21;
        int r103 = Theme.key_dialogTextBlue2;
    L22:
        r24.setTextColor(Theme.getColor(r103, r22));
        r24.setPadding(0, AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f));
        r24.setTextSize(AndroidUtilities.dp(16.0f));
        if (LocaleController.isRTL == false) goto L25;
        int r104 = 3;
    L26:
        r24.setGravity(r104);
        r24.setImportantForAccessibility(2);
        addView(r24);
        SimpleTextView r25 = new SimpleTextView(r18);
        this.valueSpoilersTextView = r25;
        r25.setEllipsizeByGradient(18, Boolean.FALSE);
        if (r20 == false) goto L29;
        int r105 = Theme.key_dialogTextBlue2;
    L30:
        r25.setTextColor(Theme.getColor(r105, r22));
        if (LocaleController.isRTL == false) goto L33;
        int r106 = 3;
    L34:
        r25.setGravity(r106);
        r25.setTextSize(16);
        r25.setImportantForAccessibility(2);
        r25.setVisibility(8);
        addView(r25);
        RLottieImageView r26 = new RLottieImageView(r18);
        this.imageView = r26;
        ImageView.ScaleType r107 = ImageView.ScaleType.CENTER;
        r26.setScaleType(r107);
        if (r20 == false) goto L37;
        int r12 = Theme.key_dialogIcon;
    L38:
        r26.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r12, r22), PorterDuff.Mode.SRC_IN));
        addView(r26);
        ImageView r27 = new ImageView(r18);
        this.valueImageView = r27;
        r27.setScaleType(r107);
        addView(this.valueImageView);
        Switch r28 = new Switch(r18, r22);
        this.checkBox = r28;
        r28.setVisibility(8);
        Switch r1 = this.checkBox;
        int r29 = Theme.key_switchTrack;
        int r3 = Theme.key_switchTrackChecked;
        int r52 = Theme.key_windowBackgroundWhite;
        r1.setColors(r29, r3, r52, r52);
        View r13 = this.checkBox;
        if (LocaleController.isRTL == true) goto L42;
        r6 = 5;
    L42:
        addView(r13, LayoutHelper.createFrame(37, 20.0f, r6 | 16, 22.0f, 0.0f, 22.0f, 0.0f));
        setFocusable(true);
        setClipChildren(false);
        return;
    L37:
        r12 = Theme.key_windowBackgroundWhiteGrayIcon;
        goto L38
    L33:
        r106 = 5;
        goto L34
    L29:
        r105 = Theme.key_windowBackgroundWhiteValueText;
        goto L30
    L25:
        r104 = 5;
        goto L26
    L21:
        r103 = Theme.key_windowBackgroundWhiteValueText;
        goto L22
    L17:
        r102 = 3;
        goto L18
    L13:
        r10 = Theme.key_windowBackgroundWhiteGrayText;
        goto L14
    L9:
        r5 = 3;
        goto L10
    L5:
        r4 = Theme.key_windowBackgroundWhiteBlackText;
        goto L6
    }

    public TextCell(Context r7, Theme.ResourcesProvider r8) {
        this(r7, 21, false, false, r8);
    }

    public static CharSequence applyNewSpan(CharSequence r1) {
        return applyNewSpan(r1, false);
    }

    public static CharSequence applyNewSpan(CharSequence r3, boolean r4) {
        SpannableStringBuilder r0 = new SpannableStringBuilder(r3);
        r0.append("  d");
        FilterCreateActivity.NewSpan r32 = new FilterCreateActivity.NewSpan(10.0f);
        r32.usePaintAlpha = r4;
        r32.setColor(Theme.getColor(Theme.key_premiumGradient1));
        r0.setSpan(r32, r0.length() - 1, r0.length(), 0);
        return r0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r9) {
        float r2 = 1.0f;
        if (this.drawLoading == true) goto L7;
        if (this.drawLoadingProgress != 0.0f) goto L7;
    L37:
        AnimatedTextView r0 = this.valueTextView;
        float r1 = 1.0f - this.drawLoadingProgress;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r3 = this.emojiDrawable;
        if (r3 != null) goto L40;
        float r32 = 1.0f;
    L41:
        float r12 = r1 * r32;
        if (isEnabled() == false) goto L44;
        float r33 = 1.0f;
    L45:
        r0.setAlpha(r12 * r33);
        SimpleTextView r02 = this.valueSpoilersTextView;
        float r13 = 1.0f - this.drawLoadingProgress;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r34 = this.emojiDrawable;
        if (r34 != null) goto L48;
        float r35 = 1.0f;
    L49:
        float r14 = r13 * r35;
        if (isEnabled() == true) goto L53;
        r2 = 0.5f;
    L53:
        r02.setAlpha(r14 * r2);
        super.dispatchDraw(r9);
        if (this.emojiDrawable == null) goto L57;
        updateEmojiBounds();
        this.emojiDrawable.draw(r9);
        return;
    L57:
        return;
    L48:
        r35 = 1.0f - r34.isNotEmpty();
        goto L49
    L44:
        r33 = 0.5f;
        goto L45
    L40:
        r32 = 1.0f - r3.isNotEmpty();
    L7:
        if (this.paint != null) goto L9;
        Paint r03 = new Paint(1);
        this.paint = r03;
        r03.setColor(Theme.getColor(Theme.key_dialogSearchBackground, this.resourcesProvider));
    L9:
        boolean r04 = this.incrementLoadingProgress;
        float r4 = this.loadingProgress;
        if (r04 == false) goto L14;
        float r42 = r4 + 0.016f;
        this.loadingProgress = r42;
        if (r42 <= 1.0f) goto L17;
        this.loadingProgress = 1.0f;
        this.incrementLoadingProgress = false;
    L17:
        int r05 = this.changeProgressStartDelay;
        if (r05 <= 0) goto L20;
        this.changeProgressStartDelay = r05 - 15;
    L33:
        this.paint.setAlpha((int) ((((this.loadingProgress * 0.4f) + 0.6f) * this.drawLoadingProgress) * 255.0f));
        int r06 = getMeasuredHeight() >> 1;
        RectF r15 = AndroidUtilities.rectTmp;
        r15.set((getMeasuredWidth() - AndroidUtilities.dp(21.0f)) - AndroidUtilities.dp(this.loadingSize), r06 - AndroidUtilities.dp(3.0f), getMeasuredWidth() - AndroidUtilities.dp(21.0f), r06 + AndroidUtilities.dp(3.0f));
        if (LocaleController.isRTL == false) goto L36;
        r15.left = getMeasuredWidth() - r15.left;
        r15.right = getMeasuredWidth() - r15.right;
    L36:
        r9.drawRoundRect(r15, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), this.paint);
        invalidate();
        goto L37
    L20:
        boolean r07 = this.drawLoading;
        if (r07 == false) goto L27;
        float r5 = this.drawLoadingProgress;
        if (r5 == 1.0f) goto L27;
        float r52 = r5 + 0.10666667f;
        this.drawLoadingProgress = r52;
        if (r52 <= 1.0f) goto L33;
        this.drawLoadingProgress = 1.0f;
    L27:
        if (r07 == true) goto L33;
        float r08 = this.drawLoadingProgress;
        if (r08 == 0.0f) goto L33;
        float r09 = r08 - 0.10666667f;
        this.drawLoadingProgress = r09;
        if (r09 >= 0.0f) goto L33;
        this.drawLoadingProgress = 0.0f;
        goto L33
    L14:
        float r43 = r4 - 0.016f;
        this.loadingProgress = r43;
        if (r43 >= 0.0f) goto L17;
        this.loadingProgress = 0.0f;
        this.incrementLoadingProgress = true;
        goto L17
    }

    public Switch getCheckBox() {
        return this.checkBox;
    }

    public RLottieImageView getImageView() {
        return this.imageView;
    }

    public int getOffsetFromImage(boolean r1) {
        if (r1 == false) goto L5;
        return 65;
    L5:
        return 71;
    }

    public SimpleTextView getTextView() {
        return this.textView;
    }

    public ImageView getValueImageView() {
        return this.valueImageView;
    }

    public AnimatedTextView getValueTextView() {
        return this.valueTextView;
    }

    public boolean isChecked() {
        Switch r0 = this.checkBox;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isChecked() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attached = true;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r1 = this.emojiDrawable;
        if (r1 == null) goto L6;
        r1.attach();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attached = false;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r1 = this.emojiDrawable;
        if (r1 == null) goto L6;
        r1.detach();
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        if (this.needDivider == false) goto L34;
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L7;
        Paint r02 = r0.getPaint("paintDivider");
    L8:
        if (r02 != null) goto L10;
        r02 = Theme.dividerPaint;
    L10:
        Paint r6 = r02;
        float r1 = 20.0f;
        int r2 = 68;
        if (LocaleController.isRTL == false) goto L14;
        float r03 = 0.0f;
    L22:
        float r4 = getMeasuredHeight() - 1;
        int r5 = getMeasuredWidth();
        if (LocaleController.isRTL == true) goto L25;
        int r12 = 0;
    L32:
        r9.drawLine(r03, r4, r5 - r12, getMeasuredHeight() - 1, r6);
        return;
    L25:
        if (this.imageView.getVisibility() == 0) goto L27;
    L30:
        r12 = AndroidUtilities.dp(r1);
        goto L32
    L27:
        if (this.inDialogs == false) goto L29;
        r2 = 72;
    L29:
        r1 = r2;
        goto L30
    L14:
        if (this.imageView.getVisibility() == 0) goto L16;
        float r04 = 20.0f;
    L21:
        r03 = AndroidUtilities.dp(r04);
        goto L22
    L16:
        if (this.inDialogs == false) goto L18;
        int r05 = 72;
    L19:
        r04 = r05;
        goto L21
    L18:
        r05 = 68;
        goto L19
    L7:
        r02 = null;
        goto L8
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r6) {
        super.onInitializeAccessibilityNodeInfo(r6);
        CharSequence r0 = this.textView.getText();
        if (TextUtils.isEmpty(r0) == true) goto L8;
        CharSequence r1 = this.valueTextView.getText();
        if (TextUtils.isEmpty(r1) == true) goto L8;
        r0 = TextUtils.concat(new CharSequence[]{r0, ": ", r1});
    L8:
        if (this.checkBox == null) goto L14;
        r6.setClassName("android.widget.Switch");
        r6.setCheckable(true);
        r6.setChecked(this.checkBox.isChecked());
        StringBuilder r02 = new StringBuilder();
        r02.append(this.textView.getText());
        if (TextUtils.isEmpty(this.valueTextView.getText()) == true) goto L12;
        r02.append('\n');
        r02.append(this.valueTextView.getText());
    L12:
        r6.setContentDescription(r02);
    L16:
        r6.addAction(16);
        return;
    L14:
        if (TextUtils.isEmpty(r0) == true) goto L16;
        r6.setText(r0);
        goto L16
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r6, int r7, int r8, int r9, int r10) {
        int r102 = r10 - r8;
        int r92 = r9 - r7;
        int r62 = ((r102 - Math.max(this.valueSpoilersTextView.getTextHeight(), this.valueTextView.getTextHeight())) / 2) + 1;
        if (LocaleController.isRTL == false) goto L5;
        int r82 = AndroidUtilities.dp(this.leftPadding);
    L7:
        if (this.prioritizeTitleOverValue == true) goto L9;
    L11:
        AnimatedTextView r0 = this.valueTextView;
        r0.layout(r82, r62, r0.getMeasuredWidth() + r82, this.valueTextView.getMeasuredHeight() + r62);
        if (LocaleController.isRTL == false) goto L14;
        int r83 = AndroidUtilities.dp(this.leftPadding);
    L15:
        SimpleTextView r02 = this.valueSpoilersTextView;
        r02.layout(r83, r62, r02.getMeasuredWidth() + r83, this.valueSpoilersTextView.getMeasuredHeight() + r62);
        if (LocaleController.isRTL == false) goto L24;
        int r63 = getMeasuredWidth() - this.textView.getMeasuredWidth();
        if (this.imageView.getVisibility() != 0) goto L21;
        int r84 = this.offsetFromImage;
    L22:
        int r64 = r63 - AndroidUtilities.dp(r84);
    L30:
        if (this.subtitleView.getVisibility() == 0) goto L32;
        int r85 = ((r102 - this.textView.getTextHeight()) / 2) + 1;
        SimpleTextView r1 = this.textView;
        r1.layout(r64, r85, r1.getMeasuredWidth() + r64, this.textView.getMeasuredHeight() + r85);
    L38:
        if (this.imageView.getVisibility() != 0) goto L49;
        if (this.heightDp <= 50) goto L42;
        float r65 = 0.0f;
    L43:
        int r66 = ((AndroidUtilities.dp(r65) + ((r102 - this.imageView.getMeasuredHeight()) / 2)) - this.imageView.getPaddingTop()) + AndroidUtilities.dp(1.0f);
        if (LocaleController.isRTL == true) goto L46;
        int r03 = AndroidUtilities.dp(this.imageLeft);
    L47:
        RLottieImageView r12 = this.imageView;
        r12.layout(r03, r66, r12.getMeasuredWidth() + r03, this.imageView.getMeasuredHeight() + r66);
        goto L49
    L46:
        r03 = (r92 - this.imageView.getMeasuredWidth()) - AndroidUtilities.dp(this.imageLeft);
        goto L47
    L42:
        r65 = 2.0f;
    L49:
        if (this.valueImageView.getVisibility() != 0) goto L56;
        int r67 = ((r102 - this.valueImageView.getMeasuredHeight()) / 2) + AndroidUtilities.dp(1.0f);
        if (LocaleController.isRTL == false) goto L53;
        int r86 = AndroidUtilities.dp(this.valueImageRight);
    L54:
        ImageView r04 = this.valueImageView;
        r04.layout(r86, r67, r04.getMeasuredWidth() + r86, this.valueImageView.getMeasuredHeight() + r67);
        goto L56
    L53:
        r86 = (r92 - this.valueImageView.getMeasuredWidth()) - AndroidUtilities.dp(this.valueImageRight);
    L56:
        if (this.checkBox.getVisibility() != 0) goto L63;
        int r103 = (r102 - this.checkBox.getMeasuredHeight()) / 2;
        if (LocaleController.isRTL == false) goto L60;
        int r68 = AndroidUtilities.dp(22.0f);
    L61:
        Switch r72 = this.checkBox;
        r72.layout(r68, r103, r72.getMeasuredWidth() + r68, this.checkBox.getMeasuredHeight() + r103);
        return;
    L60:
        r68 = (r92 - this.checkBox.getMeasuredWidth()) - AndroidUtilities.dp(22.0f);
        goto L61
    L63:
        return;
    L32:
        if (this.heightDp <= 50) goto L34;
        int r87 = 4;
    L35:
        float r88 = r87;
        int r13 = ((((r102 - this.textView.getTextHeight()) - this.subtitleView.getTextHeight()) - AndroidUtilities.dp(r88)) / 2) + 1;
        SimpleTextView r2 = this.textView;
        r2.layout(r64, r13, r2.getMeasuredWidth() + r64, this.textView.getMeasuredHeight() + r13);
        int r14 = (r13 + this.textView.getTextHeight()) + AndroidUtilities.dp(r88);
        SimpleTextView r89 = this.subtitleView;
        r89.layout(r64, r14, r89.getMeasuredWidth() + r64, this.subtitleView.getMeasuredHeight() + r14);
        goto L38
    L34:
        r87 = 2;
        goto L35
    L21:
        r84 = this.leftPadding;
        goto L22
    L24:
        if (this.imageView.getVisibility() != 0) goto L27;
        int r69 = this.offsetFromImage;
    L28:
        r64 = AndroidUtilities.dp(r69);
        goto L30
    L27:
        r69 = this.leftPadding;
        goto L28
    L14:
        r83 = (r92 - this.valueSpoilersTextView.getMeasuredWidth()) - AndroidUtilities.dp(this.leftPadding);
        goto L15
    L9:
        if (LocaleController.isRTL == true) goto L11;
        r82 = (r92 - this.valueTextView.getMeasuredWidth()) - AndroidUtilities.dp(this.leftPadding);
        goto L11
    L5:
        r82 = (r92 - this.valueTextView.getMeasuredWidth()) - AndroidUtilities.dp(this.leftPadding);
        goto L7
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r9, int r10) {
        int r92 = View.MeasureSpec.getSize(r9);
        int r102 = AndroidUtilities.dp(this.heightDp);
        int r0 = this.lastWidth;
        if (r0 == 0) goto L8;
        if (r0 == r92) goto L8;
        CharSequence r02 = this.valueText;
        if (r02 == null) goto L8;
        AnimatedTextView r2 = this.valueTextView;
        r2.setText(TextUtils.ellipsize(r02, r2.getPaint(), AndroidUtilities.displaySize.x / 2.5f, TextUtils.TruncateAt.END), false);
    L8:
        this.lastWidth = r92;
        if (this.prioritizeTitleOverValue == false) goto L19;
        this.textView.measure(View.MeasureSpec.makeMeasureSpec(r92 - AndroidUtilities.dp(this.leftPadding + 71), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30));
        this.subtitleView.measure(View.MeasureSpec.makeMeasureSpec(r92 - AndroidUtilities.dp(this.leftPadding + 71), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30));
        AnimatedTextView r03 = this.valueTextView;
        int r1 = (r92 - AndroidUtilities.dp(this.leftPadding + 103)) - this.textView.getTextWidth();
        if (LocaleController.isRTL == false) goto L13;
        int r5 = Integer.MIN_VALUE;
    L14:
        r03.measure(View.MeasureSpec.makeMeasureSpec(r1, r5), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30));
        SimpleTextView r04 = this.valueSpoilersTextView;
        int r12 = (r92 - AndroidUtilities.dp(this.leftPadding + 103)) - this.textView.getTextWidth();
        if (LocaleController.isRTL == false) goto L17;
        int r52 = Integer.MIN_VALUE;
    L18:
        r04.measure(View.MeasureSpec.makeMeasureSpec(r12, r52), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30));
    L29:
        if (this.imageView.getVisibility() != 0) goto L35;
        boolean r05 = this.isIcon;
        RLottieImageView r6 = this.imageView;
        if (r05 == false) goto L33;
        r6.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(24.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(31.0f), TLObject.FLAG_30));
        goto L35
    L33:
        r6.measure(View.MeasureSpec.makeMeasureSpec(r92, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r102, Integer.MIN_VALUE));
    L35:
        if (this.valueImageView.getVisibility() != 0) goto L40;
        boolean r06 = this.isIcon;
        ImageView r62 = this.valueImageView;
        if (r06 == false) goto L39;
        r62.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(24.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(31.0f), TLObject.FLAG_30));
        goto L40
    L39:
        r62.measure(View.MeasureSpec.makeMeasureSpec(r92, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r102, Integer.MIN_VALUE));
    L40:
        Switch r07 = this.checkBox;
        if (r07 == null) goto L43;
        r07.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(37.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30));
    L43:
        setMeasuredDimension(r92, r102 + (this.needDivider ? 1 : 0));
        return;
    L17:
        r52 = 1073741824;
        goto L18
    L13:
        r5 = 1073741824;
        goto L14
    L19:
        AnimatedTextView r08 = this.valueTextView;
        int r53 = r92 - AndroidUtilities.dp(this.leftPadding);
        if (LocaleController.isRTL == false) goto L22;
        int r63 = Integer.MIN_VALUE;
    L23:
        r08.measure(View.MeasureSpec.makeMeasureSpec(r53, r63), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30));
        SimpleTextView r09 = this.valueSpoilersTextView;
        int r54 = r92 - AndroidUtilities.dp(this.leftPadding);
        if (LocaleController.isRTL == false) goto L26;
        int r64 = Integer.MIN_VALUE;
    L27:
        r09.measure(View.MeasureSpec.makeMeasureSpec(r54, r64), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30));
        int r010 = Math.max(this.valueTextView.width(), this.valueSpoilersTextView.getTextWidth());
        this.textView.measure(View.MeasureSpec.makeMeasureSpec(Math.max(0, (r92 - AndroidUtilities.dp(this.leftPadding + 71)) - r010), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30));
        this.subtitleView.measure(View.MeasureSpec.makeMeasureSpec((r92 - AndroidUtilities.dp(this.leftPadding + 71)) - r010, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20.0f), TLObject.FLAG_30));
        goto L29
    L26:
        r64 = 1073741824;
        goto L27
    L22:
        r63 = 1073741824;
        goto L23
    }

    public int processColor(int r1) {
        return r1;
    }

    public void reset() {
        this.textView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.subtitleView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.valueTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET, false);
        this.valueSpoilersTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.imageView.setVisibility(8);
        this.valueTextView.setVisibility(8);
        this.valueSpoilersTextView.setVisibility(8);
        this.valueImageView.setVisibility(8);
        this.subtitleView.setVisibility(8);
        Switch r0 = this.checkBox;
        if (r0 == null) goto L5;
        r0.setVisibility(8);
    L5:
        this.imageView.setImageDrawable(null);
        RLottieImageView r02 = this.imageView;
        if (this.inDialogs == false) goto L8;
        int r4 = Theme.key_dialogIcon;
    L9:
        r02.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r4, this.resourcesProvider), PorterDuff.Mode.MULTIPLY));
        this.imageView.setBackground(null);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r03 = this.emojiDrawable;
        if (r03 == null) goto L12;
        r03.set(null, false);
    L12:
        this.textView.setRightDrawable(null);
        this.prioritizeTitleOverValue = false;
        this.offsetFromImage = 71;
        this.heightDp = 50;
        this.imageLeft = 21;
        this.valueImageRight = this.leftPadding;
        this.isIcon = false;
        this.imageView.setScaleType(ImageView.ScaleType.CENTER);
        return;
    L8:
        r4 = Theme.key_windowBackgroundWhiteGrayIcon;
        goto L9
    }

    public void setChecked(boolean r2) {
        this.checkBox.setChecked(r2, true);
    }

    public void setColorfulIcon(int r1, int r2) {
        setColorfulIcon(r1, r1, r2);
    }

    public void setColorfulIcon(int r2, int r3, int r4) {
        setColorfulIcon(r2, r3, r4, true);
    }

    public void setColorfulIcon(int r8, int r9, int r10, boolean r11) {
        boolean r0 = true;
        this.offsetFromImage = getOffsetFromImage(true);
        this.imageView.setVisibility(0);
        this.imageView.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f));
        RLottieImageView r1 = this.imageView;
        if (LocaleController.isRTL == false) goto L5;
        float r3 = 0.0f;
    L6:
        r1.setTranslationX(AndroidUtilities.dp(r3));
        this.imageView.setImageResource(r10);
        RLottieImageView r102 = this.imageView;
        if (r11 == true) goto L9;
        PorterDuffColorFilter r12 = null;
    L14:
        r102.setColorFilter(r12);
        Theme.ResourcesProvider r103 = this.resourcesProvider;
        if (r103 == null) goto L17;
        boolean r104 = r103.isDark();
    L18:
        SettingsActivity.SettingCell.Background r13 = new SettingsActivity.SettingCell.Background();
        if (r11 == true) goto L21;
    L23:
        r0 = false;
    L24:
        r13.setColor(r8, r9, r0);
        r13.setDrawBorder(r104);
        this.imageView.setBackground(r13);
        return;
    L21:
        if (Theme.isCurrentThemeMonet() == false) goto L23;
    L17:
        r104 = Theme.isCurrentThemeDark();
        goto L18
    L9:
        if (Theme.isCurrentThemeMonet() == false) goto L11;
        int r32 = Theme.getColor(Theme.key_chats_actionIcon);
    L12:
        r12 = new PorterDuffColorFilter(r32, PorterDuff.Mode.SRC_IN);
        goto L14
    L11:
        r32 = -1;
        goto L12
    L5:
        r3 = -3.0f;
        goto L6
    }

    public void setColors(int r4, int r5) {
        this.textView.setTextColor(Theme.getColor(r5, this.resourcesProvider));
        this.textView.setTag(Integer.valueOf(r5));
        if (r4 < 0) goto L5;
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r4, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
        this.imageView.setTag(Integer.valueOf(r4));
        this.valueImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r4, this.resourcesProvider), PorterDuff.Mode.MULTIPLY));
    L5:
        updateColors();
    }

    public void setDrawLoading(boolean r1, int r2, boolean r3) {
        this.drawLoading = r1;
        this.loadingSize = r2;
        if (r3 == true) goto L8;
        if (r1 == false) goto L6;
        float r12 = 1.0f;
    L7:
        this.drawLoadingProgress = r12;
    L9:
        invalidate();
        return;
    L6:
        r12 = 0.0f;
        goto L7
    L8:
        this.measureDelay = true;
        goto L9
    }

    @Override // android.view.View
    public void setEnabled(boolean r2) {
        setEnabled(r2, true);
    }

    public void setEnabled(boolean r4, boolean r5) {
        super.setEnabled(r4);
        Switch r0 = this.checkBox;
        if (r0 == null) goto L5;
        r0.setEnabled(r4);
    L5:
        SimpleTextView r02 = this.textView;
        float r1 = 0.5f;
        if (r5 == false) goto L32;
        ViewPropertyAnimator r52 = r02.animate();
        if (r4 == false) goto L10;
        float r03 = 1.0f;
    L11:
        r52.alpha(r03).start();
        ViewPropertyAnimator r53 = this.subtitleView.animate();
        if (r4 == false) goto L14;
        float r04 = 1.0f;
    L15:
        r53.alpha(r04).start();
        ViewPropertyAnimator r54 = this.valueTextView.animate();
        if (r4 == false) goto L18;
        float r05 = 1.0f;
    L19:
        r54.alpha(r05).start();
        ViewPropertyAnimator r55 = this.valueSpoilersTextView.animate();
        if (r4 == false) goto L22;
        float r06 = 1.0f;
    L23:
        r55.alpha(r06).start();
        ViewPropertyAnimator r56 = this.imageView.animate();
        if (r4 == false) goto L26;
        float r07 = 1.0f;
    L27:
        r56.alpha(r07).start();
        ViewPropertyAnimator r3 = this.valueImageView.animate();
        if (r4 == false) goto L30;
        r1 = 1.0f;
    L30:
        r3.alpha(r1).start();
        return;
    L26:
        r07 = 0.5f;
        goto L27
    L22:
        r06 = 0.5f;
        goto L23
    L18:
        r05 = 0.5f;
        goto L19
    L14:
        r04 = 0.5f;
        goto L15
    L10:
        r03 = 0.5f;
        goto L11
    L32:
        if (r4 == false) goto L34;
        float r57 = 1.0f;
    L35:
        r02.setAlpha(r57);
        SimpleTextView r58 = this.subtitleView;
        if (r4 == false) goto L38;
        float r08 = 1.0f;
    L39:
        r58.setAlpha(r08);
        AnimatedTextView r59 = this.valueTextView;
        if (r4 == false) goto L42;
        float r09 = 1.0f;
    L43:
        r59.setAlpha(r09);
        SimpleTextView r510 = this.valueSpoilersTextView;
        if (r4 == false) goto L46;
        float r010 = 1.0f;
    L47:
        r510.setAlpha(r010);
        RLottieImageView r511 = this.imageView;
        if (r4 == false) goto L50;
        float r011 = 1.0f;
    L51:
        r511.setAlpha(r011);
        ImageView r32 = this.valueImageView;
        if (r4 == false) goto L54;
        r1 = 1.0f;
    L54:
        r32.setAlpha(r1);
        return;
    L50:
        r011 = 0.5f;
        goto L51
    L46:
        r010 = 0.5f;
        goto L47
    L42:
        r09 = 0.5f;
        goto L43
    L38:
        r08 = 0.5f;
        goto L39
    L34:
        r57 = 0.5f;
        goto L35
    }

    public void setImageLeft(int r1) {
        this.imageLeft = r1;
    }

    public void setIsIcon(boolean r3) {
        this.isIcon = r3;
        RLottieImageView r0 = this.imageView;
        if (r3 == false) goto L5;
        ImageView.ScaleType r1 = ImageView.ScaleType.FIT_CENTER;
    L6:
        r0.setScaleType(r1);
        ImageView r2 = this.valueImageView;
        if (r3 == false) goto L9;
        ImageView.ScaleType r32 = ImageView.ScaleType.FIT_CENTER;
    L10:
        r2.setScaleType(r32);
        return;
    L9:
        r32 = ImageView.ScaleType.CENTER;
        goto L10
    L5:
        r1 = ImageView.ScaleType.CENTER;
        goto L6
    }

    public void setIsInDialogs() {
        this.inDialogs = true;
    }

    public void setLockLevel(boolean r5, int r6) {
        SimpleTextView r0 = this.textView;
        if (r6 > 0) goto L6;
        r0.setRightDrawable(null);
        return;
    L6:
        r0.setRightDrawable(new PeerColorActivity.LevelLock(getContext(), r5, r6, this.resourcesProvider));
        this.textView.setDrawablePadding(AndroidUtilities.dp(6.0f));
    }

    public void setNeedDivider(boolean r2) {
        if (this.needDivider == r2) goto L6;
        this.needDivider = r2;
        setWillNotDraw(!r2);
        invalidate();
        return;
    }

    public void setOffsetFromImage(int r1) {
        this.offsetFromImage = r1;
    }

    public void setPrioritizeTitleOverValue(boolean r2) {
        if (this.prioritizeTitleOverValue == r2) goto L6;
        this.prioritizeTitleOverValue = r2;
        requestLayout();
        return;
    }

    public void setSubtitle(CharSequence r3) {
        boolean r0 = TextUtils.isEmpty(r3);
        SimpleTextView r1 = this.subtitleView;
        if (r0 == true) goto L6;
        r1.setVisibility(0);
        this.subtitleView.setText(r3);
        return;
    L6:
        r1.setVisibility(8);
    }

    public void setText(CharSequence r4, boolean r5) {
        this.imageLeft = 21;
        this.textView.setText(r4);
        this.textView.setRightDrawable(null);
        AnimatedTextView r42 = this.valueTextView;
        this.valueText = null;
        r42.setText(null, false);
        this.imageView.setVisibility(8);
        this.valueTextView.setVisibility(8);
        this.valueSpoilersTextView.setVisibility(8);
        this.valueImageView.setVisibility(8);
        this.needDivider = r5;
        setWillNotDraw(!r5);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r3 = this.emojiDrawable;
        if (r3 == null) goto L6;
        r3.set(null, false);
        return;
    }

    public void setTextAndCheck(CharSequence r4, boolean r5, boolean r6) {
        this.imageLeft = 21;
        this.offsetFromImage = getOffsetFromImage(false);
        this.textView.setText(r4);
        this.textView.setRightDrawable(null);
        this.imageView.setVisibility(8);
        this.valueImageView.setVisibility(8);
        this.needDivider = r6;
        Switch r42 = this.checkBox;
        if (r42 == null) goto L5;
        r42.setVisibility(0);
        this.checkBox.setChecked(r5, false);
    L5:
        setWillNotDraw(!this.needDivider);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r3 = this.emojiDrawable;
        if (r3 == null) goto L9;
        r3.set(null, false);
        return;
    }

    public void setTextAndCheckAndColorfulIcon(CharSequence r10, boolean r11, int r12, int r13, boolean r14) {
        this.imageLeft = 21;
        this.offsetFromImage = getOffsetFromImage(false);
        this.textView.setText(r10);
        this.textView.setRightDrawable(null);
        this.valueTextView.setVisibility(8);
        this.valueSpoilersTextView.setVisibility(8);
        this.valueImageView.setVisibility(8);
        setColorfulIcon(r13, r12);
        if (this.checkBox != null) goto L9;
        Switch r102 = new Switch(getContext(), this.resourcesProvider);
        this.checkBox = r102;
        int r122 = Theme.key_switchTrack;
        int r132 = Theme.key_switchTrackChecked;
        int r2 = Theme.key_windowBackgroundWhite;
        r102.setColors(r122, r132, r2, r2);
        Switch r103 = this.checkBox;
        if (LocaleController.isRTL == false) goto L7;
        int r123 = 3;
    L8:
        addView(r103, LayoutHelper.createFrame(37, 20.0f, r123 | 16, 22.0f, 0.0f, 22.0f, 0.0f));
        goto L9
    L7:
        r123 = 5;
    L9:
        Switch r104 = this.checkBox;
        if (r104 == null) goto L12;
        r104.setVisibility(0);
        this.checkBox.setChecked(r11, false);
    L12:
        this.needDivider = r14;
        setWillNotDraw(!r14);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r9 = this.emojiDrawable;
        if (r9 == null) goto L16;
        r9.set(null, false);
        return;
    }

    public void setTextAndCheckAndIcon(CharSequence r4, boolean r5, int r6, boolean r7) {
        this.imageLeft = 21;
        this.offsetFromImage = getOffsetFromImage(false);
        this.textView.setText(r4);
        this.textView.setRightDrawable(null);
        this.valueTextView.setVisibility(8);
        this.valueSpoilersTextView.setVisibility(8);
        this.valueImageView.setVisibility(8);
        this.checkBox.setVisibility(0);
        this.checkBox.setChecked(r5, false);
        this.imageView.setVisibility(0);
        this.imageView.setPadding(0, AndroidUtilities.dp(7.0f), 0, 0);
        this.imageView.setImageResource(r6);
        this.needDivider = r7;
        setWillNotDraw(!r7);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r3 = this.emojiDrawable;
        if (r3 == null) goto L6;
        r3.set(null, false);
        return;
    }

    public void setTextAndIcon(CharSequence r3, int r4, boolean r5) {
        this.imageLeft = 21;
        this.offsetFromImage = getOffsetFromImage(false);
        this.textView.setText(r3);
        this.textView.setRightDrawable(null);
        AnimatedTextView r32 = this.valueTextView;
        this.valueText = null;
        r32.setText(null, false);
        this.imageView.setImageResource(r4);
        this.imageView.setVisibility(0);
        this.valueTextView.setVisibility(8);
        this.valueSpoilersTextView.setVisibility(8);
        this.valueImageView.setVisibility(8);
        this.imageView.setPadding(0, AndroidUtilities.dp(7.0f), 0, 0);
        this.needDivider = r5;
        setWillNotDraw(!r5);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r2 = this.emojiDrawable;
        if (r2 == null) goto L6;
        r2.set(null, false);
        return;
    }

    public void setTextAndIcon(CharSequence r4, Drawable r5, boolean r6) {
        this.offsetFromImage = 71;
        this.imageLeft = 18;
        this.textView.setText(r4);
        this.textView.setRightDrawable(null);
        AnimatedTextView r42 = this.valueTextView;
        this.valueText = null;
        r42.setText(null, false);
        this.imageView.setColorFilter(null);
        boolean r43 = r5 instanceof RLottieDrawable;
        RLottieImageView r2 = this.imageView;
        if (r43 == false) goto L5;
        r2.setAnimation((RLottieDrawable) r5);
    L6:
        this.imageView.setVisibility(0);
        this.valueTextView.setVisibility(8);
        this.valueImageView.setVisibility(8);
        this.imageView.setPadding(0, AndroidUtilities.dp(6.0f), 0, 0);
        this.needDivider = r6;
        setWillNotDraw(!r6);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r3 = this.emojiDrawable;
        if (r3 == null) goto L10;
        r3.set(null, false);
        return;
    L10:
        return;
    L5:
        r2.setImageDrawable(r5);
        goto L6
    }

    public void setTextAndIconAndValueDrawable(CharSequence r5, Drawable r6, Drawable r7, boolean r8) {
        this.offsetFromImage = 71;
        this.imageLeft = 21;
        this.textView.setText(r5);
        this.textView.setRightDrawable(null);
        AnimatedTextView r52 = this.valueTextView;
        this.valueText = null;
        r52.setText(null, false);
        this.valueTextView.setVisibility(8);
        this.valueSpoilersTextView.setVisibility(8);
        this.imageView.setColorFilter(null);
        boolean r53 = r6 instanceof RLottieDrawable;
        RLottieImageView r3 = this.imageView;
        if (r53 == false) goto L5;
        r3.setAnimation((RLottieDrawable) r6);
    L6:
        this.imageView.setVisibility(0);
        this.imageView.setPadding(0, AndroidUtilities.dp(6.0f), 0, 0);
        this.valueImageView.setVisibility(0);
        this.valueImageView.setImageDrawable(r7);
        this.needDivider = r8;
        setWillNotDraw(!r8);
        Switch r54 = this.checkBox;
        if (r54 == null) goto L9;
        r54.setVisibility(8);
    L9:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r4 = this.emojiDrawable;
        if (r4 == null) goto L13;
        r4.set(null, false);
        return;
    L13:
        return;
    L5:
        r3.setImageDrawable(r6);
        goto L6
    }

    public void setTextAndSpoilersValueAndIcon(String r4, CharSequence r5, int r6, boolean r7) {
        this.imageLeft = 21;
        this.offsetFromImage = getOffsetFromImage(false);
        this.textView.setText(r4);
        this.textView.setRightDrawable(null);
        this.valueSpoilersTextView.setVisibility(0);
        this.valueSpoilersTextView.setText(r5);
        this.valueTextView.setVisibility(8);
        this.valueImageView.setVisibility(8);
        this.imageView.setVisibility(0);
        this.imageView.setTranslationX(0.0f);
        this.imageView.setTranslationY(0.0f);
        this.imageView.setPadding(0, AndroidUtilities.dp(7.0f), 0, 0);
        this.imageView.setImageResource(r6);
        this.needDivider = r7;
        setWillNotDraw(!r7);
        Switch r42 = this.checkBox;
        if (r42 == null) goto L5;
        r42.setVisibility(8);
    L5:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r3 = this.emojiDrawable;
        if (r3 == null) goto L9;
        r3.set(null, false);
        return;
    }

    public void setTextAndSticker(CharSequence r4, String r5, boolean r6) {
        this.imageLeft = 21;
        this.offsetFromImage = getOffsetFromImage(false);
        this.textView.setText(r4);
        this.textView.setRightDrawable(null);
        AnimatedTextView r42 = this.valueTextView;
        this.valueText = null;
        r42.setText(null, false);
        this.valueImageView.setVisibility(8);
        this.valueTextView.setVisibility(8);
        this.valueSpoilersTextView.setVisibility(8);
        this.imageView.setVisibility(8);
        this.imageView.setPadding(0, AndroidUtilities.dp(7.0f), 0, 0);
        this.needDivider = r6;
        setWillNotDraw(!r6);
        Switch r43 = this.checkBox;
        if (r43 == null) goto L5;
        r43.setVisibility(8);
    L5:
        setValueSticker(r5);
    }

    public void setTextAndSticker(CharSequence r4, TLRPC.Document r5, boolean r6) {
        this.imageLeft = 21;
        this.offsetFromImage = getOffsetFromImage(false);
        this.textView.setText(r4);
        this.textView.setRightDrawable(null);
        AnimatedTextView r42 = this.valueTextView;
        this.valueText = null;
        r42.setText(null, false);
        this.valueImageView.setVisibility(8);
        this.valueTextView.setVisibility(8);
        this.valueSpoilersTextView.setVisibility(8);
        this.imageView.setVisibility(8);
        this.imageView.setPadding(0, AndroidUtilities.dp(7.0f), 0, 0);
        this.needDivider = r6;
        setWillNotDraw(!r6);
        Switch r43 = this.checkBox;
        if (r43 == null) goto L5;
        r43.setVisibility(8);
    L5:
        setValueSticker(r5);
    }

    public void setTextAndValue(CharSequence r2, CharSequence r3, boolean r4) {
        setTextAndValue(r2, r3, false, r4);
    }

    public void setTextAndValue(CharSequence r6, CharSequence r7, boolean r8, boolean r9) {
        this.imageLeft = 21;
        this.offsetFromImage = getOffsetFromImage(false);
        this.textView.setText(r6);
        this.textView.setRightDrawable(null);
        this.valueText = r7;
        AnimatedTextView r62 = this.valueTextView;
        if (r7 != null) goto L5;
        CharSequence r72 = null;
    L6:
        r62.setText(r72, r8);
        this.valueTextView.setVisibility(0);
        this.valueSpoilersTextView.setVisibility(8);
        this.imageView.setVisibility(8);
        this.valueImageView.setVisibility(8);
        this.needDivider = r9;
        setWillNotDraw(!r9);
        Switch r63 = this.checkBox;
        if (r63 == null) goto L9;
        r63.setVisibility(8);
    L9:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r5 = this.emojiDrawable;
        if (r5 == null) goto L13;
        r5.set(null, false);
        return;
    L13:
        return;
    L5:
        r72 = TextUtils.ellipsize(r7, r62.getPaint(), AndroidUtilities.displaySize.x / 2.5f, TextUtils.TruncateAt.END);
        goto L6
    }

    public void setTextAndValueAndColorfulIcon(String r6, CharSequence r7, boolean r8, int r9, int r10, int r11, boolean r12) {
        this.imageLeft = 21;
        this.offsetFromImage = getOffsetFromImage(false);
        this.textView.setText(r6);
        this.textView.setRightDrawable(null);
        AnimatedTextView r62 = this.valueTextView;
        if (r7 != null) goto L5;
        CharSequence r72 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        r62.setText(r72, r8);
        this.valueTextView.setVisibility(0);
        this.valueSpoilersTextView.setVisibility(8);
        setColorfulIcon(r10, r11, r9);
        this.valueImageView.setVisibility(8);
        this.needDivider = r12;
        setWillNotDraw(!r12);
        Switch r63 = this.checkBox;
        if (r63 == null) goto L9;
        r63.setVisibility(8);
    L9:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r5 = this.emojiDrawable;
        if (r5 == null) goto L13;
        r5.set(null, false);
        return;
    L13:
        return;
    L5:
        this.valueText = r7;
        r72 = TextUtils.ellipsize(r7, r62.getPaint(), AndroidUtilities.displaySize.x / 2.5f, TextUtils.TruncateAt.END);
        goto L6
    }

    public void setTextAndValueAndIcon(CharSequence r7, CharSequence r8, int r9, boolean r10) {
        setTextAndValueAndIcon(r7, r8, false, r9, r10);
    }

    public void setTextAndValueAndIcon(CharSequence r3, CharSequence r4, Drawable r5, boolean r6) {
        this.offsetFromImage = 71;
        this.imageLeft = 18;
        this.textView.setText(r3);
        this.textView.setRightDrawable(null);
        AnimatedTextView r32 = this.valueTextView;
        this.valueText = r4;
        r32.setText(r4, false);
        this.imageView.setColorFilter(null);
        boolean r33 = r5 instanceof RLottieDrawable;
        RLottieImageView r42 = this.imageView;
        if (r33 == false) goto L5;
        r42.setAnimation((RLottieDrawable) r5);
    L6:
        this.imageView.setVisibility(0);
        this.valueTextView.setVisibility(0);
        this.valueImageView.setVisibility(8);
        this.imageView.setPadding(0, AndroidUtilities.dp(6.0f), 0, 0);
        this.needDivider = r6;
        setWillNotDraw(!r6);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r2 = this.emojiDrawable;
        if (r2 == null) goto L10;
        r2.set(null, false);
        return;
    L10:
        return;
    L5:
        r42.setImageDrawable(r5);
        goto L6
    }

    public void setTextAndValueAndIcon(CharSequence r6, CharSequence r7, boolean r8, int r9, boolean r10) {
        this.imageLeft = 21;
        this.offsetFromImage = getOffsetFromImage(false);
        this.textView.setText(r6);
        this.textView.setRightDrawable(null);
        this.imageView.setVisibility(0);
        if (r7 == null) goto L5;
        int r62 = (int) Math.max(1.0f, AndroidUtilities.displaySize.x - ((AndroidUtilities.dp(this.offsetFromImage) + HintView2.measureCorrectly(r6, this.textView.getTextPaint())) + AndroidUtilities.dp(16.0f)));
        AnimatedTextView r1 = this.valueTextView;
        this.valueText = r7;
        r1.setText(TextUtils.ellipsize(r7, r1.getPaint(), r62, TextUtils.TruncateAt.END), r8);
    L6:
        this.valueTextView.setVisibility(0);
        this.valueSpoilersTextView.setVisibility(8);
        this.valueImageView.setVisibility(8);
        this.imageView.setTranslationX(0.0f);
        this.imageView.setTranslationY(0.0f);
        this.imageView.setPadding(0, AndroidUtilities.dp(7.0f), 0, 0);
        this.imageView.setImageResource(r9);
        this.needDivider = r10;
        setWillNotDraw(!r10);
        Switch r63 = this.checkBox;
        if (r63 == null) goto L9;
        r63.setVisibility(8);
    L9:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r5 = this.emojiDrawable;
        if (r5 == null) goto L13;
        r5.set(null, false);
        return;
    L13:
        return;
    L5:
        this.valueTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET, r8);
        goto L6
    }

    public void setTextAndValueDrawable(CharSequence r4, Drawable r5, boolean r6) {
        this.imageLeft = 21;
        this.offsetFromImage = getOffsetFromImage(false);
        this.textView.setText(r4);
        this.textView.setRightDrawable(null);
        AnimatedTextView r42 = this.valueTextView;
        this.valueText = null;
        r42.setText(null, false);
        this.valueImageView.setVisibility(0);
        this.valueImageView.setImageDrawable(r5);
        this.valueTextView.setVisibility(8);
        this.valueSpoilersTextView.setVisibility(8);
        this.imageView.setVisibility(8);
        this.imageView.setPadding(0, AndroidUtilities.dp(7.0f), 0, 0);
        this.needDivider = r6;
        setWillNotDraw(!r6);
        Switch r43 = this.checkBox;
        if (r43 == null) goto L5;
        r43.setVisibility(8);
    L5:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r3 = this.emojiDrawable;
        if (r3 == null) goto L9;
        r3.set(null, false);
        return;
    }

    public void setTextColor(int r1) {
        this.textView.setTextColor(r1);
    }

    public void setValue(CharSequence r1) {
        this.valueTextView.setText(r1);
    }

    public void setValue(CharSequence r4, boolean r5) {
        AnimatedTextView r0 = this.valueTextView;
        if (r4 != null) goto L5;
        CharSequence r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        r0.setText(r3, r5);
        return;
    L5:
        this.valueText = r4;
        r3 = TextUtils.ellipsize(r4, r0.getPaint(), AndroidUtilities.displaySize.x / 2.5f, TextUtils.TruncateAt.END);
        goto L6
    }

    public void setValueSticker(String r10) {
        if (this.emojiDrawable != null) goto L7;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this, AndroidUtilities.dp(30.0f));
        this.emojiDrawable = r0;
        if (this.attached == false) goto L7;
        r0.attach();
    L7:
        ImageReceiver r2 = new ImageReceiver(this);
        if (isAttachedToWindow() == false) goto L10;
        r2.onAttachedToWindow();
    L10:
        addOnAttachStateChangeListener(new AnonymousClass1(this, r2));
        r2.setImage(r10, "30_30", null, null, 0);
        this.emojiDrawable.set(new AnonymousClass2(this, r2), true);
        invalidate();
    }

    public void setValueSticker(TLRPC.Document r3) {
        if (this.emojiDrawable != null) goto L7;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this, AndroidUtilities.dp(30.0f));
        this.emojiDrawable = r0;
        if (this.attached == false) goto L7;
        r0.attach();
    L7:
        this.emojiDrawable.set(r3, 1, true);
        invalidate();
    }

    public void updateColors() {
        if ((this.textView.getTag() instanceof Integer) == false) goto L5;
        int r0 = ((Integer) this.textView.getTag()).intValue();
    L6:
        int r1 = Theme.getColor(r0, this.resourcesProvider);
        if (r0 != Theme.key_dialogTextBlack) goto L9;
    L11:
        this.textView.setTextColor(r1);
        if ((this.imageView.getTag() instanceof Integer) == false) goto L19;
        int r02 = ((Integer) this.imageView.getTag()).intValue();
        int r12 = Theme.getColor(r02, this.resourcesProvider);
        if (r02 != Theme.key_dialogIcon) goto L16;
    L18:
        this.imageView.setColorFilter(new PorterDuffColorFilter(r12, PorterDuff.Mode.SRC_IN));
        this.valueImageView.setColorFilter(new PorterDuffColorFilter(r12, PorterDuff.Mode.MULTIPLY));
        goto L19
    L16:
        if (r02 == Theme.key_windowBackgroundWhiteGrayIcon) goto L18;
        r12 = processColor(r12);
    L19:
        this.subtitleView.setTextColor(processColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, this.resourcesProvider)));
        AnimatedTextView r03 = this.valueTextView;
        int r13 = Theme.key_windowBackgroundWhiteValueText;
        r03.setTextColor(processColor(Theme.getColor(r13, this.resourcesProvider)));
        this.valueSpoilersTextView.setTextColor(processColor(Theme.getColor(r13, this.resourcesProvider)));
        return;
    L9:
        if (r0 == Theme.key_windowBackgroundWhiteBlackText) goto L11;
        r1 = processColor(r1);
        goto L11
    L5:
        r0 = Theme.key_windowBackgroundWhiteBlackText;
        goto L6
    }

    public void updateEmojiBounds() {
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = this.emojiDrawable;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setBounds((getWidth() - this.emojiDrawable.getIntrinsicWidth()) - AndroidUtilities.dp(18.0f), (getHeight() - this.emojiDrawable.getIntrinsicHeight()) / 2, getWidth() - AndroidUtilities.dp(18.0f), (getHeight() + this.emojiDrawable.getIntrinsicHeight()) / 2);
    }
}
