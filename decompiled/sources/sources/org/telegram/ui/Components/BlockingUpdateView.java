package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableStringBuilder;
import android.util.Property;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.updater.UpdaterUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.Components.voip.CellFlickerDrawable;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class BlockingUpdateView extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private final FrameLayout acceptButton;
    private final TextView acceptTextView;
    private int accountNum;
    private final TLRPC.TL_help_appUpdate appUpdate;
    private String fileName;
    Drawable gradientDrawableBottom;
    Drawable gradientDrawableTop;
    private int pressCount;
    private AnimatorSet progressAnimation;
    private final RadialProgress radialProgress;
    private final FrameLayout radialProgressView;
    private final ScrollView scrollView;
    private final TextView textView;

    /* renamed from: org.telegram.ui.Components.BlockingUpdateView$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        CellFlickerDrawable cellFlickerDrawable;
        final /* synthetic */ BlockingUpdateView this$0;

        public AnonymousClass1(BlockingUpdateView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r5) {
            super.onDraw(r5);
            if (this.cellFlickerDrawable != null) goto L5;
            CellFlickerDrawable r0 = new CellFlickerDrawable();
            this.cellFlickerDrawable = r0;
            r0.drawFrame = false;
            r0.repeatProgress = 2.0f;
        L5:
            this.cellFlickerDrawable.setParentWidth(getMeasuredWidth());
            RectF r02 = AndroidUtilities.rectTmp;
            r02.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            this.cellFlickerDrawable.draw(r5, r02, AndroidUtilities.dp(8.0f), null);
            invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.BlockingUpdateView$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ BlockingUpdateView this$0;

        public AnonymousClass2(BlockingUpdateView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r1) {
            BlockingUpdateView.m8995$$Nest$fgetradialProgress(this.this$0).draw(r1);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            int r12 = AndroidUtilities.dp(36.0f);
            int r42 = ((r4 - r2) - r12) / 2;
            int r52 = ((r5 - r3) - r12) / 2;
            BlockingUpdateView.m8995$$Nest$fgetradialProgress(this.this$0).setProgressRect(r42, r52, r42 + r12, r12 + r52);
        }
    }

    /* renamed from: org.telegram.ui.Components.BlockingUpdateView$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ BlockingUpdateView this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass3(BlockingUpdateView r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (BlockingUpdateView.m8994$$Nest$fgetprogressAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (BlockingUpdateView.m8994$$Nest$fgetprogressAnimation(this.this$0).equals(r2) == false) goto L9;
            BlockingUpdateView.m8997$$Nest$fputprogressAnimation(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (BlockingUpdateView.m8994$$Nest$fgetprogressAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (BlockingUpdateView.m8994$$Nest$fgetprogressAnimation(this.this$0).equals(r2) == false) goto L13;
            boolean r22 = this.val$show;
            BlockingUpdateView r1 = this.this$0;
            if (r22 == true) goto L10;
            BlockingUpdateView.m8996$$Nest$fgetradialProgressView(r1).setVisibility(4);
            return;
        L10:
            BlockingUpdateView.m8993$$Nest$fgetacceptTextView(r1).setVisibility(4);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$FdlCoGRYACkv4roY6qPTfKdcgVI(BlockingUpdateView r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: $r8$lambda$ZVBiyCmfrL-CfxqAKwiNie0wBLM, reason: not valid java name */
    public static /* synthetic */ void m8992$r8$lambda$ZVBiyCmfrLCfxqAKwiNie0wBLM(BlockingUpdateView r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$ctrkebBI0wGQfMja8oof30dWrGE(BlockingUpdateView r0, TLRPC.TL_help_appUpdate r1, TLRPC.TL_error r2) {
        r0.lambda$show$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ejGf7Jz4D5rLRjigWTiYsSQhg4k(BlockingUpdateView r0) {
        r0.lambda$show$2();
    }

    /* renamed from: -$$Nest$fgetacceptTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m8993$$Nest$fgetacceptTextView(BlockingUpdateView r0) {
        return r0.acceptTextView;
    }

    /* renamed from: -$$Nest$fgetprogressAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m8994$$Nest$fgetprogressAnimation(BlockingUpdateView r0) {
        return r0.progressAnimation;
    }

    /* renamed from: -$$Nest$fgetradialProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadialProgress m8995$$Nest$fgetradialProgress(BlockingUpdateView r0) {
        return r0.radialProgress;
    }

    /* renamed from: -$$Nest$fgetradialProgressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m8996$$Nest$fgetradialProgressView(BlockingUpdateView r0) {
        return r0.radialProgressView;
    }

    /* renamed from: -$$Nest$fputprogressAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8997$$Nest$fputprogressAnimation(BlockingUpdateView r0, AnimatorSet r1) {
        r0.progressAnimation = r1;
    }

    public BlockingUpdateView(Context r20, TLRPC.TL_help_appUpdate r21) {
        super(r20);
        GradientDrawable.Orientation r4 = GradientDrawable.Orientation.TOP_BOTTOM;
        int r5 = Theme.key_windowBackgroundWhite;
        this.gradientDrawableTop = new GradientDrawable(r4, new int[]{Theme.getColor(r5), 0});
        this.gradientDrawableBottom = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{Theme.getColor(r5), 0});
        this.appUpdate = r21;
        setBackgroundColor(Theme.getColor(r5));
        int r3 = (int) (AndroidUtilities.statusBarHeight / AndroidUtilities.density);
        FrameLayout r42 = new FrameLayout(r20);
        addView(r42, new FrameLayout.LayoutParams(-1, AndroidUtilities.dp(236.0f) + AndroidUtilities.statusBarHeight));
        RLottieImageView r52 = new RLottieImageView(r20);
        r52.setAutoRepeat(true);
        r52.setAnimation(R.raw.giveaway_results, 120, 120);
        r52.playAnimation();
        r52.setOnClickListener(new BlockingUpdateView$$ExternalSyntheticLambda1(this));
        r42.addView(r52, LayoutHelper.createFrame(120, 120.0f, 49, 0.0f, r3 + 30, 0.0f, 0.0f));
        TextView r53 = new TextView(r20);
        int r9 = Theme.key_windowBackgroundWhiteBlackText;
        r53.setTextColor(Theme.getColor(r9));
        r53.setTextSize(1, 20.0f);
        r53.setGravity(49);
        r53.setTypeface(AndroidUtilities.bold());
        r53.setText(LocaleController.getString(R.string.UpdateAvailable));
        r42.addView(r53, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, r3 + 175, 0.0f, 0.0f));
        TextView r54 = new TextView(getContext());
        r54.setTextColor(Theme.getColor(Theme.key_dialogTextGray3));
        r54.setTextSize(1, 14.0f);
        r54.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        r54.setLinkTextColor(Theme.getColor(Theme.key_dialogTextLink));
        r54.setText(LocaleController.formatString("AppUpdateVersionAndSize", R.string.AppUpdateVersionAndSize, new Object[]{r21.version, AndroidUtilities.formatFileSize(r21.document.size)}));
        r54.setGravity(49);
        r42.addView(r54, LayoutHelper.createFrame(-2, -2.0f, 1, 23.0f, r3 + Opcodes.MUL_DOUBLE_2ADDR, 23.0f, 0.0f));
        FrameLayout r2 = new FrameLayout(r20);
        ScrollView r43 = new ScrollView(r20);
        this.scrollView = r43;
        AndroidUtilities.setScrollViewEdgeEffectColor(r43, Theme.getColor(Theme.key_actionBarDefault));
        r43.setPadding(0, AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f));
        r43.setClipToPadding(false);
        addView(r43, LayoutHelper.createFrame(-1, -1.0f, 51, 27.0f, r3 + 238, 27.0f, 130.0f));
        r43.addView(r2);
        SpoilersTextView r32 = new SpoilersTextView(r20);
        this.textView = r32;
        r32.setTextColor(Theme.getColor(r9));
        r32.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText));
        r32.setTextSize(1, 13.0f);
        r32.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        r32.setGravity(51);
        r32.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        r2.addView(r32, LayoutHelper.createFrame(-1, -2.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        AnonymousClass1 r22 = new AnonymousClass1(this, r20);
        this.acceptButton = r22;
        ScaleStateListAnimator.apply(r22, 0.02f, 1.2f);
        r22.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{8.0f}));
        r22.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        addView(r22, LayoutHelper.createFrame(-1, 48.0f, 81, 16.0f, 0.0f, 16.0f, 45.0f));
        r22.setOnClickListener(new BlockingUpdateView$$ExternalSyntheticLambda2(this));
        TextView r33 = new TextView(r20);
        this.acceptTextView = r33;
        r33.setGravity(17);
        r33.setTypeface(AndroidUtilities.bold());
        r33.setTextColor(-1);
        r33.setText(LocaleController.getString(R.string.Update));
        r33.setTextSize(1, 14.0f);
        r22.addView(r33, LayoutHelper.createFrame(-2, -2, 17));
        AnonymousClass2 r34 = new AnonymousClass2(this, r20);
        this.radialProgressView = r34;
        r34.setWillNotDraw(false);
        r34.setAlpha(0.0f);
        r34.setScaleX(0.1f);
        r34.setScaleY(0.1f);
        r34.setVisibility(4);
        RadialProgress r1 = new RadialProgress(r34);
        this.radialProgress = r1;
        r1.setBackground(null, true, false);
        r1.setProgressColor(-1);
        r22.addView(r34, LayoutHelper.createFrame(36, 36, 17));
    }

    private /* synthetic */ void lambda$new$0(View r2) {
        int r22 = this.pressCount + 1;
        this.pressCount = r22;
        if (r22 < 10) goto L6;
        setVisibility(8);
        SharedConfig.pendingAppUpdate = null;
        SharedConfig.saveConfig();
        return;
    }

    private /* synthetic */ void lambda$new$1(View r5) {
        if (ApplicationLoader.applicationLoaderInstance.checkApkInstallPermissions(getContext()) == false) goto L17;
        TLRPC.TL_help_appUpdate r52 = this.appUpdate;
        if ((r52.document instanceof TLRPC.TL_document) == false) goto L12;
        if (ApplicationLoader.applicationLoaderInstance.openApkInstall((Activity) getContext(), this.appUpdate.document) == true) goto L15;
        FileLoader.getInstance(this.accountNum).loadFile(this.appUpdate.document, PluginsConstants.UPDATE, 3, 1);
        showProgress(true);
        return;
    L15:
        return;
    L12:
        if (r52.url == null) goto L16;
        Browser.openUrl(getContext(), this.appUpdate.url);
        return;
    L16:
        return;
    }

    private /* synthetic */ void lambda$show$2() {
        setVisibility(8);
        SharedConfig.pendingAppUpdate = null;
        SharedConfig.saveConfig();
    }

    private /* synthetic */ void lambda$show$3(TLRPC.TL_help_appUpdate r1, TLRPC.TL_error r2) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (r1.can_not_skip == true) goto L8;
        AndroidUtilities.runOnUIThread(new BlockingUpdateView$$ExternalSyntheticLambda3(this));
        return;
    }

    private void showProgress(boolean r20) {
        AnimatorSet r2 = this.progressAnimation;
        if (r2 == null) goto L5;
        r2.cancel();
    L5:
        this.progressAnimation = new AnimatorSet();
        Property r9 = View.ALPHA;
        Property r10 = View.SCALE_Y;
        Property r11 = View.SCALE_X;
        if (r20 == false) goto L8;
        this.radialProgressView.setVisibility(0);
        this.acceptButton.setEnabled(false);
        this.progressAnimation.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.acceptTextView, r11, new float[]{0.1f}), ObjectAnimator.ofFloat(this.acceptTextView, r10, new float[]{0.1f}), ObjectAnimator.ofFloat(this.acceptTextView, r9, new float[]{0.0f}), ObjectAnimator.ofFloat(this.radialProgressView, r11, new float[]{1.0f}), ObjectAnimator.ofFloat(this.radialProgressView, r10, new float[]{1.0f}), ObjectAnimator.ofFloat(this.radialProgressView, r9, new float[]{1.0f})});
    L9:
        this.progressAnimation.addListener(new AnonymousClass3(this, r20));
        this.progressAnimation.setDuration(150);
        this.progressAnimation.start();
        return;
    L8:
        this.acceptTextView.setVisibility(0);
        this.acceptButton.setEnabled(true);
        this.progressAnimation.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.radialProgressView, r11, new float[]{0.1f}), ObjectAnimator.ofFloat(this.radialProgressView, r10, new float[]{0.1f}), ObjectAnimator.ofFloat(this.radialProgressView, r9, new float[]{0.0f}), ObjectAnimator.ofFloat(this.acceptTextView, r11, new float[]{1.0f}), ObjectAnimator.ofFloat(this.acceptTextView, r10, new float[]{1.0f}), ObjectAnimator.ofFloat(this.acceptTextView, r9, new float[]{1.0f})});
        goto L9
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.fileLoaded) goto L11;
        String r32 = (String) r5[0];
        String r42 = this.fileName;
        if (r42 != null) goto L7;
        return;
    L7:
        if (r42.equals(r32) == false) goto L27;
        showProgress(false);
        ApplicationLoader.applicationLoaderInstance.openApkInstall((Activity) getContext(), this.appUpdate.document);
        return;
    L27:
        return;
    L11:
        if (r3 != NotificationCenter.fileLoadFailed) goto L19;
        String r33 = (String) r5[0];
        String r43 = this.fileName;
        if (r43 != null) goto L15;
        return;
    L15:
        if (r43.equals(r33) == false) goto L29;
        showProgress(false);
        return;
    L29:
        return;
    L19:
        if (r3 != NotificationCenter.fileLoadProgressChanged) goto L30;
        String r34 = (String) r5[0];
        String r44 = this.fileName;
        if (r44 != null) goto L23;
        return;
    L23:
        if (r44.equals(r34) == false) goto L32;
        Long r45 = (Long) r5[1];
        Long r52 = (Long) r5[2];
        this.radialProgress.setProgress(Math.min(1.0f, r45.longValue() / r52.longValue()), true);
        return;
    L32:
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r7) {
        super.dispatchDraw(r7);
        this.gradientDrawableTop.setBounds(this.scrollView.getLeft(), this.scrollView.getTop(), this.scrollView.getRight(), this.scrollView.getTop() + AndroidUtilities.dp(16.0f));
        this.gradientDrawableTop.draw(r7);
        this.gradientDrawableBottom.setBounds(this.scrollView.getLeft(), this.scrollView.getBottom() - AndroidUtilities.dp(18.0f), this.scrollView.getRight(), this.scrollView.getBottom());
        this.gradientDrawableBottom.draw(r7);
    }

    @Override // android.view.View
    public void setVisibility(int r2) {
        super.setVisibility(r2);
        if (r2 != 8) goto L6;
        NotificationCenter.getInstance(this.accountNum).removeObserver(this, NotificationCenter.fileLoaded);
        NotificationCenter.getInstance(this.accountNum).removeObserver(this, NotificationCenter.fileLoadFailed);
        NotificationCenter.getInstance(this.accountNum).removeObserver(this, NotificationCenter.fileLoadProgressChanged);
        return;
    }

    public void show(int r8, boolean r9) {
        this.pressCount = 0;
        this.accountNum = r8;
        TLRPC.Document r82 = this.appUpdate.document;
        if ((r82 instanceof TLRPC.TL_document) == false) goto L6;
        this.fileName = FileLoader.getAttachFileName(r82);
    L6:
        if (getVisibility() == 0) goto L8;
        setVisibility(0);
    L8:
        SpannableStringBuilder r1 = new SpannableStringBuilder(this.appUpdate.text);
        MessageObject.addEntitiesToText(r1, this.appUpdate.entities, false, false, false, false);
        MessageObject.replaceAnimatedEmoji(r1, this.appUpdate.entities, this.textView.getPaint().getFontMetricsInt());
        this.textView.setText(r1);
        NotificationCenter.getInstance(this.accountNum).addObserver(this, NotificationCenter.fileLoaded);
        NotificationCenter.getInstance(this.accountNum).addObserver(this, NotificationCenter.fileLoadFailed);
        NotificationCenter.getInstance(this.accountNum).addObserver(this, NotificationCenter.fileLoadProgressChanged);
        if (r9 == false) goto L12;
        UpdaterUtils.getAppUpdate(new BlockingUpdateView$$ExternalSyntheticLambda0(this));
        return;
    }
}
