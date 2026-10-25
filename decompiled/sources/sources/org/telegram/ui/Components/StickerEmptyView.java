package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes3.dex */
public class StickerEmptyView extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private boolean animateLayoutChange;
    public final ButtonWithCounterView button;
    int colorKey1;
    int currentAccount;
    int keyboardSize;
    private int lastH;
    public LinearLayout linearLayout;
    boolean preventMoving;
    private RadialProgressView progressBar;
    private boolean progressShowing;
    public final View progressView;
    private final Theme.ResourcesProvider resourcesProvider;
    Runnable showProgressRunnable;
    private int stickerType;
    public BackupImageView stickerView;
    public final LinkSpanDrawable.LinksTextView subtitle;
    public final SpoilersTextView title;
    private ValueAnimator visibilityAnimator;
    private float visibilityFactor;
    private boolean visibilityValue;

    /* renamed from: org.telegram.ui.Components.StickerEmptyView$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ StickerEmptyView this$0;

        public AnonymousClass1(StickerEmptyView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            StickerEmptyView r0 = this.this$0;
            View r1 = r0.progressView;
            if (r1 != null) goto L5;
            StickerEmptyView.m13067$$Nest$fgetprogressBar(r0).animate().alpha(1.0f).scaleY(1.0f).scaleX(1.0f).setDuration(150).start();
            return;
        L5:
            if (r1.getVisibility() == 0) goto L7;
            this.this$0.progressView.setVisibility(0);
            this.this$0.progressView.setAlpha(0.0f);
        L7:
            this.this$0.progressView.animate().setListener(null).cancel();
            this.this$0.progressView.animate().alpha(1.0f).setDuration(150).start();
        }
    }

    /* renamed from: org.telegram.ui.Components.StickerEmptyView$2, reason: invalid class name */
    public class AnonymousClass2 extends LinearLayout {
        final /* synthetic */ StickerEmptyView this$0;

        public AnonymousClass2(StickerEmptyView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void setVisibility(int r3) {
            if (getVisibility() != 8) goto L8;
            if (r3 != 0) goto L8;
            StickerEmptyView.m13068$$Nest$msetSticker(this.this$0);
            if (LiteMode.isEnabled(3) == false) goto L10;
            this.this$0.stickerView.getImageReceiver().startAnimation();
        L10:
            super.setVisibility(r3);
            return;
        L8:
            if (r3 != 8) goto L10;
            this.this$0.stickerView.getImageReceiver().clearImage();
            goto L10
        }
    }

    /* renamed from: org.telegram.ui.Components.StickerEmptyView$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends FrameLayout {
        final /* synthetic */ StickerEmptyView this$0;

        public AnonymousClass3(StickerEmptyView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r1) {
            getParent().requestDisallowInterceptTouchEvent(true);
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.StickerEmptyView$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ StickerEmptyView this$0;

        public AnonymousClass4(StickerEmptyView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.progressView.setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.Components.StickerEmptyView$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ StickerEmptyView this$0;

        public AnonymousClass5(StickerEmptyView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.progressView.setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.Components.StickerEmptyView$6, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ StickerEmptyView this$0;

        public AnonymousClass6(StickerEmptyView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.progressView.setVisibility(8);
        }
    }

    public static /* synthetic */ void $r8$lambda$Tlw1EaAFHoxHrqf9Z9zJLuwc_Uo(StickerEmptyView r0, ValueAnimator r1) {
        r0.lambda$setVisibility$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$xcUvHrzmdPKM3uplc7W3jDcuXYc(Runnable r2, View r3) {
        AndroidUtilities.runOnUIThread(r2, 100);
    }

    public static /* synthetic */ void $r8$lambda$xt8Z9eNkrUgafrMleN7glHpAENQ(StickerEmptyView r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgetprogressBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadialProgressView m13067$$Nest$fgetprogressBar(StickerEmptyView r0) {
        return r0.progressBar;
    }

    /* renamed from: -$$Nest$msetSticker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13068$$Nest$msetSticker(StickerEmptyView r0) {
        r0.setSticker();
    }

    public StickerEmptyView(Context r2, View r3, int r4) {
        this(r2, r3, r4, null);
    }

    public StickerEmptyView(Context r12, View r13, int r14, Theme.ResourcesProvider r15) {
        super(r12);
        this.currentAccount = UserConfig.selectedAccount;
        this.showProgressRunnable = new AnonymousClass1(this);
        this.colorKey1 = Theme.key_emptyListPlaceholder;
        this.resourcesProvider = r15;
        this.progressView = r13;
        this.stickerType = r14;
        AnonymousClass2 r142 = new AnonymousClass2(this, r12);
        this.linearLayout = r142;
        r142.setOrientation(1);
        BackupImageView r143 = new BackupImageView(r12);
        this.stickerView = r143;
        r143.setOnClickListener(new StickerEmptyView$$ExternalSyntheticLambda1(this));
        SpoilersTextView r144 = new SpoilersTextView(r12);
        this.title = r144;
        r144.setTypeface(AndroidUtilities.bold());
        int r1 = Theme.key_windowBackgroundWhiteBlackText;
        r144.setTag(Integer.valueOf(r1));
        r144.setTextColor(getThemedColor(r1));
        r144.setTextSize(1, 20.0f);
        r144.setGravity(17);
        LinkSpanDrawable.LinksTextView r2 = new LinkSpanDrawable.LinksTextView(r12);
        this.subtitle = r2;
        int r3 = Theme.key_windowBackgroundWhiteGrayText;
        r2.setTag(Integer.valueOf(r3));
        r2.setTextColor(getThemedColor(r3));
        r2.setLinkTextColor(getThemedColor(Theme.key_windowBackgroundWhiteLinkText));
        r2.setTextSize(1, 14.0f);
        r2.setGravity(17);
        ButtonWithCounterView r32 = new ButtonWithCounterView(r12, r15).setRound();
        this.button = r32;
        r32.setVisibility(8);
        this.linearLayout.addView(this.stickerView, LayoutHelper.createLinear(117, 117, 1));
        this.linearLayout.addView(r144, LayoutHelper.createLinear(-2, -2, 1, 0, 12, 0, 0));
        this.linearLayout.addView(r2, LayoutHelper.createLinear(-2, -2, 1, 0, 8, 0, 0));
        this.linearLayout.addView(r32, LayoutHelper.createLinear(-1, 48, 1, 28, 16, 28, 0));
        addView(this.linearLayout, LayoutHelper.createFrame(-2, -2.0f, 17, 46.0f, 0.0f, 46.0f, 30.0f));
        if (r13 != null) goto L6;
        RadialProgressView r132 = new RadialProgressView(r12, r15);
        this.progressBar = r132;
        r132.setAlpha(0.0f);
        this.progressBar.setScaleY(0.5f);
        this.progressBar.setScaleX(0.5f);
        addView(this.progressBar, LayoutHelper.createFrame(-2, -2, 17));
        return;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private int getWhitespaceCount(CharSequence r3) {
        int r2 = 0;
        int r0 = 0;
    L4:
        if (r2 >= r3.length()) goto L9;
        if (Character.isWhitespace(r3.charAt(r2)) == false) goto L8;
        r0 = r0 + 1;
    L8:
        r2 = r2 + 1;
        goto L4
    L9:
        return r0;
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        this.stickerView.getImageReceiver().startAnimation();
    }

    private /* synthetic */ void lambda$setVisibility$2(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.visibilityFactor = r12;
        onVisibilityChange(r12);
    }

    private void setSticker() {
        int r0 = this.stickerType;
        if (r0 == 0) goto L43;
        boolean r1 = true;
        if (r0 == 1) goto L43;
        int r2 = this.currentAccount;
        TLRPC.Document r3 = null;
        if (r0 != 16) goto L10;
        TLRPC.TL_messages_stickerSet r10 = null;
        r3 = MediaDataController.getInstance(r2).getEmojiAnimatedSticker("👍");
    L21:
        if (LiteMode.isEnabled(3) == true) goto L24;
        String r02 = "130_130_firstframe";
    L23:
        String r7 = r02;
        if (r3 == null) goto L37;
        SvgHelper.SvgDrawable r9 = DocumentObject.getSvgThumb(r3.thumbs, this.colorKey1, 0.2f);
        if (r9 == null) goto L29;
        r9.overrideWidthAndHeight(512, 512);
    L29:
        this.stickerView.setImage(ImageLocation.getForDocument(r3), r7, "tgs", r9, r10);
        int r03 = this.stickerType;
        if (r03 == 9) goto L35;
        if (r03 == 0) goto L35;
        this.stickerView.getImageReceiver().setAutoRepeat(2);
        return;
    L35:
        this.stickerView.getImageReceiver().setAutoRepeat(1);
        return;
    L37:
        MediaDataController r04 = MediaDataController.getInstance(this.currentAccount);
        if (r10 == null) goto L41;
        r1 = false;
    L41:
        r04.loadStickersByEmojiOrName(AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME, false, r1);
        this.stickerView.getImageReceiver().clearImage();
        return;
    L24:
        r02 = "130_130";
        goto L23
    L10:
        TLRPC.TL_messages_stickerSet r05 = MediaDataController.getInstance(r2).getStickerSetByName(AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME);
        if (r05 != null) goto L13;
        r05 = MediaDataController.getInstance(this.currentAccount).getStickerSetByEmojiOrName(AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME);
    L13:
        if (r05 == null) goto L19;
        int r22 = this.stickerType;
        if (r22 < 0) goto L19;
        if (r22 >= r05.documents.size()) goto L19;
        r3 = r05.documents.get(this.stickerType);
    L19:
        r10 = r05;
    L43:
        this.stickerView.setImageDrawable(new RLottieDrawable(R.raw.utyan_empty, AndroidUtilities.dp(130.0f), AndroidUtilities.dp(130.0f)));
    }

    private void setVisibility(boolean r2, boolean r3, boolean r4) {
        if (this.visibilityValue != r2) goto L6;
        if (r4 == true) goto L6;
        return;
    L6:
        this.visibilityValue = r2;
        setEnabled(r2);
        ValueAnimator r42 = this.visibilityAnimator;
        if (r42 == null) goto L9;
        r42.cancel();
        this.visibilityAnimator = null;
    L9:
        float r43 = 0.0f;
        if (r3 == true) goto L15;
        if (r2 == false) goto L13;
        r43 = 1.0f;
    L13:
        this.visibilityFactor = r43;
        onVisibilityChange(r43);
        return;
    L15:
        float r32 = this.visibilityFactor;
        if (r2 == false) goto L18;
        r43 = 1.0f;
    L18:
        ValueAnimator r22 = ValueAnimator.ofFloat(new float[]{r32, r43});
        this.visibilityAnimator = r22;
        r22.setDuration(480);
        this.visibilityAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.visibilityAnimator.addUpdateListener(new StickerEmptyView$$ExternalSyntheticLambda0(this));
        this.visibilityAnimator.start();
    }

    public void createButtonLayout(CharSequence r10, Runnable r11) {
        LinearLayout.LayoutParams r0 = (LinearLayout.LayoutParams) this.subtitle.getLayoutParams();
        r0.topMargin = AndroidUtilities.dp(12.0f);
        TextView r02 = new TextView(getContext());
        r02.setText(r10);
        int r102 = Theme.key_featuredStickers_buttonText;
        r02.setTextColor(Theme.getColor(r102, this.resourcesProvider));
        r02.setPadding(AndroidUtilities.dp(45.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(45.0f), AndroidUtilities.dp(12.0f));
        r02.setGravity(17);
        r02.setTypeface(AndroidUtilities.bold());
        r02.setTextSize(1, 15.0f);
        AnonymousClass3 r1 = new AnonymousClass3(this, getContext());
        r1.setOnClickListener(new StickerEmptyView$$ExternalSyntheticLambda2(r11));
        r1.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider), ColorUtils.setAlphaComponent(Theme.getColor(r102, this.resourcesProvider), 30)));
        ScaleStateListAnimator.apply(r1, 0.05f, 1.5f);
        r1.addView(r02);
        this.linearLayout.setClipChildren(false);
        this.linearLayout.addView(r1, LayoutHelper.createLinear(-2, -2, 1, 0, 28, 0, 4));
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.diceStickersDidLoad) goto L5;
        return;
    L5:
        if (AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME.equals((String) r3[0]) == true) goto L7;
        return;
    L7:
        if (getVisibility() != 0) goto L12;
        setSticker();
        return;
    }

    public float getVisibilityFactor() {
        return this.visibilityFactor;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getVisibility() != 0) goto L5;
        setSticker();
    L5:
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.diceStickersDidLoad);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.diceStickersDidLoad);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        super.onLayout(r2, r3, r4, r5, r6);
        if (this.animateLayoutChange == false) goto L5;
    L6:
        int r22 = this.lastH;
        if (r22 > 0) goto L9;
    L18:
        this.lastH = getMeasuredHeight();
        return;
    L9:
        if (r22 == getMeasuredHeight()) goto L18;
        float r23 = (this.lastH - getMeasuredHeight()) / 2.0f;
        LinearLayout r32 = this.linearLayout;
        r32.setTranslationY(r32.getTranslationY() + r23);
        if (this.preventMoving == true) goto L13;
        this.linearLayout.animate().translationY(0.0f).setInterpolator(CubicBezierInterpolator.DEFAULT).setDuration(250);
    L13:
        RadialProgressView r33 = this.progressBar;
        if (r33 == null) goto L18;
        r33.setTranslationY(r33.getTranslationY() + r23);
        if (this.preventMoving == true) goto L18;
        this.progressBar.animate().translationY(0.0f).setInterpolator(CubicBezierInterpolator.DEFAULT).setDuration(250);
        goto L18
    L5:
        if (this.preventMoving == false) goto L18;
        goto L6
    }

    public void onVisibilityChange(float r1) {
        invalidate();
    }

    public void setAnimateLayoutChange(boolean r1) {
        this.animateLayoutChange = r1;
    }

    public void setColors(int r2, int r3, int r4, int r5) {
        this.title.setTag(Integer.valueOf(r2));
        this.title.setTextColor(getThemedColor(r2));
        this.subtitle.setTag(Integer.valueOf(r3));
        this.subtitle.setTextColor(getThemedColor(r3));
        this.colorKey1 = r4;
    }

    public void setKeyboardHeight(int r4, boolean r5) {
        if (this.keyboardSize == r4) goto L20;
        int r1 = 0;
        if (getVisibility() == 0) goto L7;
        r5 = false;
    L7:
        this.keyboardSize = r4;
        int r0 = -(r4 >> 1);
        if (r4 <= 0) goto L10;
        r1 = AndroidUtilities.dp(20.0f);
    L10:
        float r42 = r0 + r1;
        LinearLayout r02 = this.linearLayout;
        if (r5 == false) goto L16;
        ViewPropertyAnimator r52 = r02.animate().translationY(r42);
        CubicBezierInterpolator r03 = CubicBezierInterpolator.DEFAULT;
        r52.setInterpolator(r03).setDuration(250);
        RadialProgressView r3 = this.progressBar;
        if (r3 == null) goto L21;
        r3.animate().translationY(r42).setInterpolator(r03).setDuration(250);
        return;
    L21:
        return;
    L16:
        r02.setTranslationY(r42);
        RadialProgressView r32 = this.progressBar;
        if (r32 == null) goto L22;
        r32.setTranslationY(r42);
        return;
    L22:
        return;
    }

    public void setPreventMoving(boolean r2) {
        this.preventMoving = r2;
        if (r2 == true) goto L8;
        this.linearLayout.setTranslationY(0.0f);
        RadialProgressView r1 = this.progressBar;
        if (r1 == null) goto L9;
        r1.setTranslationY(0.0f);
        return;
    L9:
        return;
    }

    public void setStickerType(int r2) {
        if (this.stickerType == r2) goto L6;
        this.stickerType = r2;
        setSticker();
        return;
    }

    public void setSubtitle(CharSequence r8) {
        if (getWhitespaceCount(r8) > 4) goto L5;
    L18:
        this.subtitle.setText(r8);
        return;
    L5:
        if (r8.length() <= 20) goto L18;
        int r0 = r8.length() >> 1;
        int r4 = -1;
        int r3 = 0;
        int r5 = 0;
    L8:
        if (r3 >= r8.length()) goto L16;
        if (Character.isWhitespace(r8.charAt(r3)) == false) goto L15;
        int r6 = Math.abs(r0 - r3);
        if (r4 == (-1)) goto L14;
        if (r6 >= r5) goto L15;
    L14:
        r4 = r3;
        r5 = r6;
    L15:
        r3 = r3 + 1;
        goto L8
    L16:
        if (r4 <= 0) goto L18;
        r8 = r8.subSequence(0, r4) + "\n" + r8.subSequence(r4 + 1, r8.length());
        goto L18
    }

    @Override // android.view.View
    public void setVisibility(int r2) {
        setVisibility(r2, true);
    }

    public void setVisibility(int r10, boolean r11) {
        if (r10 != 0) goto L5;
        boolean r1 = true;
    L6:
        setVisibility(r1, r11, false);
        if (getVisibility() == r10) goto L17;
        if (r10 != 0) goto L17;
        boolean r112 = this.progressShowing;
        LinearLayout r7 = this.linearLayout;
        if (r112 == false) goto L12;
        r7.animate().alpha(0.0f).scaleY(0.8f).scaleX(0.8f).setDuration(150).start();
        this.progressView.animate().setListener(null).cancel();
        this.progressView.setVisibility(0);
        this.progressView.setAlpha(1.0f);
        goto L17
    L12:
        r7.animate().alpha(1.0f).scaleY(1.0f).scaleX(1.0f).setDuration(150).start();
        View r113 = this.progressView;
        if (r113 == null) goto L15;
        r113.animate().setListener(null).cancel();
        this.progressView.animate().setListener(new AnonymousClass4(this)).alpha(0.0f).setDuration(150).start();
    L16:
        this.stickerView.getImageReceiver().startAnimation();
        goto L17
    L15:
        this.progressBar.animate().alpha(0.0f).scaleY(0.5f).scaleX(0.5f).setDuration(150).start();
    L17:
        super.setVisibility(r10);
        if (getVisibility() != 0) goto L21;
        setSticker();
        return;
    L21:
        this.lastH = 0;
        this.linearLayout.setAlpha(0.0f);
        this.linearLayout.setScaleX(0.8f);
        this.linearLayout.setScaleY(0.8f);
        View r102 = this.progressView;
        if (r102 == null) goto L24;
        r102.animate().setListener(null).cancel();
        this.progressView.animate().setListener(new AnonymousClass5(this)).alpha(0.0f).setDuration(150).start();
    L25:
        this.stickerView.getImageReceiver().stopAnimation();
        this.stickerView.getImageReceiver().clearImage();
        return;
    L24:
        this.progressBar.setAlpha(0.0f);
        this.progressBar.setScaleX(0.5f);
        this.progressBar.setScaleY(0.5f);
        goto L25
    L5:
        r1 = false;
        goto L6
    }

    public void showProgress(boolean r2) {
        showProgress(r2, true);
    }

    public void showProgress(boolean r8, boolean r9) {
        if (this.progressShowing == r8) goto L33;
        this.progressShowing = r8;
        if (getVisibility() == 0) goto L8;
        return;
    L8:
        if (r9 == false) goto L19;
        LinearLayout r92 = this.linearLayout;
        if (r8 == false) goto L13;
        r92.animate().alpha(0.0f).scaleY(0.8f).scaleX(0.8f).setDuration(150).start();
        this.showProgressRunnable.run();
        return;
    L13:
        r92.animate().alpha(1.0f).scaleY(1.0f).scaleX(1.0f).setDuration(150).start();
        View r82 = this.progressView;
        if (r82 == null) goto L16;
        r82.animate().setListener(null).cancel();
        this.progressView.animate().setListener(new AnonymousClass6(this)).alpha(0.0f).setDuration(150).start();
    L17:
        this.stickerView.getImageReceiver().startAnimation();
        return;
    L16:
        this.progressBar.animate().alpha(0.0f).scaleY(0.5f).scaleX(0.5f).setDuration(150).start();
        goto L17
    L19:
        LinearLayout r93 = this.linearLayout;
        if (r8 == false) goto L27;
        r93.animate().cancel();
        this.linearLayout.setAlpha(0.0f);
        this.linearLayout.setScaleX(0.8f);
        this.linearLayout.setScaleY(0.8f);
        View r83 = this.progressView;
        if (r83 == null) goto L25;
        r83.animate().setListener(null).cancel();
        this.progressView.setAlpha(1.0f);
        this.progressView.setVisibility(0);
        return;
    L25:
        this.progressBar.setAlpha(1.0f);
        this.progressBar.setScaleX(1.0f);
        this.progressBar.setScaleY(1.0f);
        return;
    L27:
        r93.animate().cancel();
        this.linearLayout.setAlpha(1.0f);
        this.linearLayout.setScaleX(1.0f);
        this.linearLayout.setScaleY(1.0f);
        View r84 = this.progressView;
        if (r84 == null) goto L31;
        r84.animate().setListener(null).cancel();
        this.progressView.setVisibility(8);
        return;
    L31:
        this.progressBar.setAlpha(0.0f);
        this.progressBar.setScaleX(0.5f);
        this.progressBar.setScaleY(0.5f);
        return;
    }
}
