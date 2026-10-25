package com.exteragram.messenger.updater;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes4.dex */
public class UpdateAppAlertDialog extends BottomSheet {
    private final int accountNum;
    protected final TLRPC.TL_help_appUpdate appUpdate;
    private final int bottomContentHeight;
    protected final TextView changelogTextView;
    protected final ButtonWithCounterView doneButton;
    protected final LinearLayout linearLayout;
    private final int[] location;
    protected final TextView messageTextView;
    private int scrollOffsetY;
    private final NestedScrollView scrollView;
    private final View shadow;
    private AnimatorSet shadowAnimation;
    private final Drawable shadowDrawable;
    protected final TextView textView;

    /* renamed from: com.exteragram.messenger.updater.UpdateAppAlertDialog$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ UpdateAppAlertDialog this$0;

        public AnonymousClass1(UpdateAppAlertDialog r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r6) {
            UpdateAppAlertDialog.m1753$$Nest$fgetshadowDrawable(this.this$0).setBounds(0, (int) ((UpdateAppAlertDialog.m1750$$Nest$fgetscrollOffsetY(this.this$0) - UpdateAppAlertDialog.access$100(this.this$0)) - getTranslationY()), getMeasuredWidth(), getMeasuredHeight());
            UpdateAppAlertDialog.m1753$$Nest$fgetshadowDrawable(this.this$0).draw(r6);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L13;
            if (UpdateAppAlertDialog.m1750$$Nest$fgetscrollOffsetY(this.this$0) == 0) goto L13;
            if (r3.getY() >= UpdateAppAlertDialog.m1750$$Nest$fgetscrollOffsetY(this.this$0)) goto L13;
            if (UpdateAppAlertDialog.access$000(this.this$0) == false) goto L13;
            this.this$0.dismiss();
            return true;
        L13:
            return super.onInterceptTouchEvent(r3);
        }

        @Override // android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent r2) {
            if (this.this$0.isDismissed() == false) goto L5;
            return false;
        L5:
            if (super.onTouchEvent(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
            UpdateAppAlertDialog.m1755$$Nest$mupdateLayout(this.this$0);
        }
    }

    /* renamed from: com.exteragram.messenger.updater.UpdateAppAlertDialog$2, reason: invalid class name */
    public class AnonymousClass2 extends NestedScrollView {
        private boolean ignoreLayout;
        final /* synthetic */ UpdateAppAlertDialog this$0;

        public AnonymousClass2(UpdateAppAlertDialog r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.core.widget.NestedScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            UpdateAppAlertDialog.m1755$$Nest$mupdateLayout(this.this$0);
        }

        @Override // androidx.core.widget.NestedScrollView, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r9, int r10) {
            int r0 = View.MeasureSpec.getSize(r10);
            measureChildWithMargins(this.this$0.linearLayout, r9, 0, r10, 0);
            int r8 = this.this$0.linearLayout.getMeasuredHeight();
            int r92 = (r0 / 5) * 2;
            if ((r8 - (r0 - r92)) >= AndroidUtilities.dp(90.0f)) goto L5;
        L6:
            r92 = r0 - r8;
        L8:
            if (r92 >= 0) goto L11;
            r92 = 0;
        L11:
            if (getPaddingTop() == r92) goto L13;
            this.ignoreLayout = true;
            setPadding(0, r92, 0, 0);
            this.ignoreLayout = false;
        L13:
            super.onMeasure(r9, View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30));
            return;
        L5:
            if (r8 >= ((r0 / 2) + AndroidUtilities.dp(90.0f))) goto L8;
            goto L6
        }

        @Override // androidx.core.widget.NestedScrollView, android.view.View
        public void onScrollChanged(int r1, int r2, int r3, int r4) {
            super.onScrollChanged(r1, r2, r3, r4);
            UpdateAppAlertDialog.m1755$$Nest$mupdateLayout(this.this$0);
        }

        @Override // androidx.core.widget.NestedScrollView, android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: com.exteragram.messenger.updater.UpdateAppAlertDialog$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ UpdateAppAlertDialog this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass3(UpdateAppAlertDialog r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (UpdateAppAlertDialog.m1752$$Nest$fgetshadowAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (UpdateAppAlertDialog.m1752$$Nest$fgetshadowAnimation(this.this$0).equals(r2) == false) goto L9;
            UpdateAppAlertDialog.m1754$$Nest$fputshadowAnimation(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (UpdateAppAlertDialog.m1752$$Nest$fgetshadowAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (UpdateAppAlertDialog.m1752$$Nest$fgetshadowAnimation(this.this$0).equals(r2) == true) goto L7;
            return;
        L7:
            if (this.val$show == true) goto L9;
            UpdateAppAlertDialog.m1751$$Nest$fgetshadow(this.this$0).setVisibility(4);
        L9:
            UpdateAppAlertDialog.m1754$$Nest$fputshadowAnimation(this.this$0, null);
        }
    }

    /* renamed from: $r8$lambda$0GMOk9KDhT5mabQd8jzKJ_xo-LE, reason: not valid java name */
    public static /* synthetic */ void m1748$r8$lambda$0GMOk9KDhT5mabQd8jzKJ_xoLE(UpdateAppAlertDialog r0) {
        r0.lambda$addContentAfterDoneButton$1();
    }

    /* renamed from: $r8$lambda$D1msRO-Btfrt9VOj15uT11LATIM, reason: not valid java name */
    public static /* synthetic */ void m1749$r8$lambda$D1msROBtfrt9VOj15uT11LATIM(UpdateAppAlertDialog r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$hKuTUggfHSB1hG3JWlvmgyqNjzE(Runnable r0, View r1) {
        r0.run();
    }

    /* renamed from: -$$Nest$fgetscrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1750$$Nest$fgetscrollOffsetY(UpdateAppAlertDialog r0) {
        return r0.scrollOffsetY;
    }

    /* renamed from: -$$Nest$fgetshadow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m1751$$Nest$fgetshadow(UpdateAppAlertDialog r0) {
        return r0.shadow;
    }

    /* renamed from: -$$Nest$fgetshadowAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m1752$$Nest$fgetshadowAnimation(UpdateAppAlertDialog r0) {
        return r0.shadowAnimation;
    }

    /* renamed from: -$$Nest$fgetshadowDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m1753$$Nest$fgetshadowDrawable(UpdateAppAlertDialog r0) {
        return r0.shadowDrawable;
    }

    /* renamed from: -$$Nest$fputshadowAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1754$$Nest$fputshadowAnimation(UpdateAppAlertDialog r0, AnimatorSet r1) {
        r0.shadowAnimation = r1;
    }

    /* renamed from: -$$Nest$mupdateLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1755$$Nest$mupdateLayout(UpdateAppAlertDialog r0) {
        r0.updateLayout();
    }

    public UpdateAppAlertDialog(Activity r26, TLRPC.TL_help_appUpdate r27, int r28) {
        super(r26, false);
        this.location = new int[2];
        this.appUpdate = r27;
        this.accountNum = r28;
        if (hasSecondaryButton() == false) goto L5;
        int r4 = 130;
    L6:
        int r42 = r4 + getExtraBottomContentHeight();
        this.bottomContentHeight = r42;
        fixNavigationBar();
        setApplyTopPadding(false);
        setApplyBottomPadding(false);
        Drawable r5 = r26.getResources().getDrawable(R.drawable.sheet_shadow_round).mutate();
        this.shadowDrawable = r5;
        r5.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogBackground), PorterDuff.Mode.MULTIPLY));
        AnonymousClass1 r52 = new AnonymousClass1(this, r26);
        r52.setWillNotDraw(false);
        this.containerView = r52;
        AnonymousClass2 r6 = new AnonymousClass2(this, r26);
        this.scrollView = r6;
        r6.setFillViewport(true);
        r6.setWillNotDraw(false);
        r6.setClipToPadding(false);
        r6.setVerticalScrollBarEnabled(false);
        r52.addView(r6, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, r42));
        LinearLayout r8 = new LinearLayout(r26);
        this.linearLayout = r8;
        r8.setOrientation(1);
        r6.addView(r8, LayoutHelper.createScroll(-1, -2, 51));
        if (r27.sticker == null) goto L13;
        BackupImageView r12 = new BackupImageView(r26);
        SvgHelper.SvgDrawable r15 = DocumentObject.getSvgThumb(r27.sticker.thumbs, Theme.key_windowBackgroundGray, 1.0f);
        ImageLocation r62 = ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r27.sticker.thumbs, 90), r27.sticker);
        if (r15 == null) goto L11;
        r12.setImage(ImageLocation.getForDocument(r27.sticker), "250_250", r15, 0, PluginsConstants.UPDATE);
    L12:
        r8.addView(r12, LayoutHelper.createLinear(160, 160, 49, 17, 8, 17, 0));
        goto L13
    L11:
        r12.setImage(ImageLocation.getForDocument(r27.sticker), "250_250", r62, null, 0, PluginsConstants.UPDATE);
    L13:
        TextView r63 = new TextView(r26);
        this.textView = r63;
        r63.setTypeface(AndroidUtilities.bold());
        r63.setTextSize(1, 20.0f);
        int r9 = Theme.key_dialogTextBlack;
        r63.setTextColor(Theme.getColor(r9));
        r63.setSingleLine(true);
        r63.setEllipsize(TextUtils.TruncateAt.END);
        r63.setText(getTitleText());
        r8.addView(r63, LayoutHelper.createLinear(-2, -2, 49, 23, 16, 23, 0));
        TextView r64 = new TextView(getContext());
        this.messageTextView = r64;
        r64.setTextColor(Theme.getColor(Theme.key_dialogTextGray3));
        r64.setTextSize(1, 14.0f);
        r64.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        int r13 = Theme.key_dialogTextLink;
        r64.setLinkTextColor(Theme.getColor(r13));
        r64.setText(LocaleController.formatString("AppUpdateVersionAndSize", R.string.AppUpdateVersionAndSize, new Object[]{r27.version, AndroidUtilities.formatFileSize(r27.document.size)}));
        r64.setGravity(49);
        r8.addView(r64, LayoutHelper.createLinear(-2, -2, 49, 23, 0, 23, 5));
        SpoilersTextView r3 = new SpoilersTextView(getContext());
        this.changelogTextView = r3;
        r3.setTextColor(Theme.getColor(r9));
        r3.setTextSize(1, 14.0f);
        r3.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        r3.setLinksClickable(false);
        r3.setLinkTextColor(Theme.getColor(r13));
        if (TextUtils.isEmpty(r27.text) == false) goto L16;
        r3.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.AppUpdateChangelogEmpty)));
    L17:
        r3.setGravity(51);
        r8.addView(r3, LayoutHelper.createLinear(-2, -2, 51, 23, 15, 23, 0));
        FrameLayout.LayoutParams r2 = new FrameLayout.LayoutParams(-1, AndroidUtilities.getShadowHeight(), 83);
        r2.bottomMargin = AndroidUtilities.dp(r42 - 3);
        View r32 = new View(r26);
        this.shadow = r32;
        r32.setBackgroundColor(Theme.getColor(Theme.key_dialogShadowLine));
        r32.setAlpha(0.0f);
        r32.setTag(1);
        r52.addView(r32, r2);
        addContentBeforeDoneButton(r52);
        ButtonWithCounterView r22 = new ButtonWithCounterView(r26, true, null);
        this.doneButton = r22;
        r22.setRound();
        r22.setText(getDoneButtonText(), false);
        r22.setOnClickListener(new UpdateAppAlertDialog$$ExternalSyntheticLambda0(this));
        if (hasSecondaryButton() == false) goto L20;
        int r1 = 64;
    L21:
        r52.addView(r22, LayoutHelper.createFrame(-1, 48.0f, 83, 22.0f, 14.0f, 22.0f, r1 + getExtraBottomContentHeight()));
        addContentAfterDoneButton(r52);
        return;
    L20:
        r1 = 8;
        goto L21
    L16:
        SpannableStringBuilder r43 = new SpannableStringBuilder(r27.text);
        MessageObject.addEntitiesToText(r43, r27.entities, false, false, false, false);
        MessageObject.replaceAnimatedEmoji(r43, r27.entities, r3.getPaint().getFontMetricsInt());
        r3.setText(r43);
        goto L17
    L5:
        r4 = 72;
        goto L6
    }

    public static /* synthetic */ boolean access$000(UpdateAppAlertDialog r0) {
        return r0.canDismissWithTouchOutside();
    }

    public static /* synthetic */ int access$100(UpdateAppAlertDialog r0) {
        return r0.backgroundPaddingTop;
    }

    private /* synthetic */ void lambda$addContentAfterDoneButton$1() {
        ExteraConfig.setUpdateScheduleTimestamp(System.currentTimeMillis());
        dismiss();
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        onDone();
    }

    private void runShadowAnimation(boolean r8) {
        if (r8 == true) goto L4;
    L5:
        if (r8 == false) goto L7;
        return;
    L7:
        if (this.shadow.getTag() != null) goto L25;
    L8:
        View r0 = this.shadow;
        if (r8 == false) goto L11;
        Integer r2 = null;
    L12:
        r0.setTag(r2);
        if (r8 == false) goto L15;
        this.shadow.setVisibility(0);
    L15:
        AnimatorSet r22 = this.shadowAnimation;
        if (r22 == null) goto L18;
        r22.cancel();
    L18:
        AnimatorSet r23 = new AnimatorSet();
        this.shadowAnimation = r23;
        View r3 = this.shadow;
        Property r4 = View.ALPHA;
        if (r8 == false) goto L21;
        float r5 = 1.0f;
    L22:
        r23.playTogether(new Animator[]{ObjectAnimator.ofFloat(r3, r4, new float[]{r5})});
        this.shadowAnimation.setDuration(150);
        this.shadowAnimation.addListener(new AnonymousClass3(this, r8));
        this.shadowAnimation.start();
        return;
    L21:
        r5 = 0.0f;
        goto L22
    L11:
        r2 = 1;
        goto L12
    L25:
        return;
    L4:
        if (this.shadow.getTag() != null) goto L8;
        goto L5
    }

    private void updateLayout() {
        boolean r1 = false;
        this.linearLayout.getChildAt(0).getLocationInWindow(this.location);
        int r0 = Math.max(this.location[1] - AndroidUtilities.dp(24.0f), 0);
        if ((this.location[1] + this.linearLayout.getMeasuredHeight()) <= ((this.container.getMeasuredHeight() - AndroidUtilities.dp(this.bottomContentHeight - 17)) + this.containerView.getTranslationY())) goto L5;
        r1 = true;
    L5:
        runShadowAnimation(r1);
        if (this.scrollOffsetY == r0) goto L9;
        this.scrollOffsetY = r0;
        this.scrollView.invalidate();
        return;
    }

    public void addContentAfterDoneButton(FrameLayout r2) {
        if (hasSecondaryButton() == true) goto L5;
        return;
    L5:
        addRemindLaterButton(r2, new UpdateAppAlertDialog$$ExternalSyntheticLambda2(this));
    }

    public void addContentBeforeDoneButton(FrameLayout r1) {
    }

    public void addRemindLaterButton(FrameLayout r9, Runnable r10) {
        ButtonWithCounterView r0 = new ButtonWithCounterView(getContext(), false, null);
        r0.setRound();
        r0.setText(LocaleController.getString(R.string.AppUpdateRemindMeLater), false);
        r0.setOnClickListener(new UpdateAppAlertDialog$$ExternalSyntheticLambda1(r10));
        r9.addView(r0, LayoutHelper.createFrame(-1, 48.0f, 83, 22.0f, 14.0f, 22.0f, 8.0f));
    }

    public String getDoneButtonText() {
        if (FileLoader.getInstance(this.currentAccount).getPathToAttach(SharedConfig.pendingAppUpdate.document, true).exists() == false) goto L7;
        int r2 = R.string.AppUpdateNow;
    L6:
        return LocaleController.getString(r2);
    L7:
        r2 = R.string.AppUpdateDownloadNow;
        goto L6
    }

    public int getExtraBottomContentHeight() {
        return 0;
    }

    public String getTitleText() {
        return LocaleController.getString(R.string.UpdateAvailable);
    }

    public boolean hasSecondaryButton() {
        return true;
    }

    public void onDone() {
        if (FileLoader.getInstance(this.currentAccount).getPathToAttach(SharedConfig.pendingAppUpdate.document, true).exists() == false) goto L5;
        ApplicationLoader.applicationLoaderInstance.openApkInstall((Activity) getContext(), SharedConfig.pendingAppUpdate.document);
    L6:
        dismiss();
        return;
    L5:
        FileLoader.getInstance(this.accountNum).loadFile(this.appUpdate.document, PluginsConstants.UPDATE, 1, 1);
        goto L6
    }
}
