package org.telegram.ui.Components.Premium.boosts.cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.GLIcon.GLIconRenderer;
import org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView;
import org.telegram.ui.Components.Premium.StarParticlesView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class HeaderCell extends FrameLayout {
    private ValueAnimator goldenAnimator;
    private final GLIconTextureView iconTextureView;
    private final LinearLayout linearLayout;
    private LinkSpanDrawable.LinkCollector links;
    private final Paint[] paints;
    private final Theme.ResourcesProvider resourcesProvider;
    private final StarParticlesView starParticlesView;
    private final LinkSpanDrawable.LinksTextView subtitleView;
    private final TextView titleView;

    /* renamed from: org.telegram.ui.Components.Premium.boosts.cells.HeaderCell$1, reason: invalid class name */
    public class AnonymousClass1 extends GLIconTextureView {
        final /* synthetic */ HeaderCell this$0;

        public AnonymousClass1(HeaderCell r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView, android.view.TextureView, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            setPaused(false);
        }

        @Override // org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            setPaused(true);
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.cells.HeaderCell$2, reason: invalid class name */
    public class AnonymousClass2 extends StarParticlesView {
        final /* synthetic */ HeaderCell this$0;

        public AnonymousClass2(HeaderCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.Premium.StarParticlesView, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            HeaderCell.m12162$$Nest$fgetstarParticlesView(this.this$0).setPaused(false);
        }

        @Override // org.telegram.ui.Components.Premium.StarParticlesView, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            HeaderCell.m12162$$Nest$fgetstarParticlesView(this.this$0).setPaused(true);
        }

        @Override // org.telegram.ui.Components.Premium.StarParticlesView, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, r3);
            this.drawable.rect2.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight() - AndroidUtilities.dp(52.0f));
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.cells.HeaderCell$3, reason: invalid class name */
    public class AnonymousClass3 extends ViewOutlineProvider {
        final /* synthetic */ HeaderCell this$0;

        public AnonymousClass3(HeaderCell r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            float r5 = AndroidUtilities.dp(12.0f);
            r8.setRoundRect(0, 0, r7.getWidth(), (int) (r7.getHeight() + r5), r5);
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.cells.HeaderCell$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ HeaderCell this$0;
        final /* synthetic */ float val$from;
        final /* synthetic */ float[] val$lastT;
        final /* synthetic */ boolean val$stars;
        final /* synthetic */ float val$to;

        public AnonymousClass4(HeaderCell r1, float[] r2, float r3, float r4, boolean r5) {
            this.this$0 = r1;
            this.val$lastT = r2;
            this.val$from = r3;
            this.val$to = r4;
            this.val$stars = r5;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            float[] r52 = this.val$lastT;
            float r1 = 1.0f - r52[0];
            r52[0] = 1.0f;
            HeaderCell.m12161$$Nest$fgeticonTextureView(this.this$0).mRenderer.golden = AndroidUtilities.lerp(this.val$from, this.val$to, 1.0f);
            GLIconRenderer r53 = HeaderCell.m12161$$Nest$fgeticonTextureView(this.this$0).mRenderer;
            float r0 = r53.angleX3;
            float r12 = r1 * 360.0f;
            if (this.val$stars == false) goto L5;
            int r2 = 1;
        L6:
            r53.angleX3 = r0 + (r12 * r2);
            HeaderCell.m12161$$Nest$fgeticonTextureView(this.this$0).mRenderer.updateColors();
            HeaderCell r54 = this.this$0;
            HeaderCell.m12163$$Nest$mupdatePaints(r54, HeaderCell.m12161$$Nest$fgeticonTextureView(r54).mRenderer.golden);
            HeaderCell.m12161$$Nest$fgeticonTextureView(this.this$0).scheduleIdleAnimation(750);
            return;
        L5:
            r2 = -1;
            goto L6
        }
    }

    public static /* synthetic */ void $r8$lambda$0qnKBGaLK8PXfa2mKFqOQYJL8Zo(Utilities.Callback r0, TLRPC.User r1) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$Bq63cJTqtH91yKODp60P2MwmUqY(HeaderCell r0, float[] r1, float r2, float r3, boolean r4, ValueAnimator r5) {
        r0.lambda$setStars$2(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ Paint $r8$lambda$uvjx1kIGoMuWJW3XFHm5H1qELe8(HeaderCell r0, Integer r1) {
        return r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgeticonTextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GLIconTextureView m12161$$Nest$fgeticonTextureView(HeaderCell r0) {
        return r0.iconTextureView;
    }

    /* renamed from: -$$Nest$fgetstarParticlesView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StarParticlesView m12162$$Nest$fgetstarParticlesView(HeaderCell r0) {
        return r0.starParticlesView;
    }

    /* renamed from: -$$Nest$mupdatePaints, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12163$$Nest$mupdatePaints(HeaderCell r0, float r1) {
        r0.updatePaints(r1);
    }

    public HeaderCell(Context r18, Theme.ResourcesProvider r19) {
        super(r18);
        this.resourcesProvider = r19;
        LinearLayout r3 = new LinearLayout(r18);
        this.linearLayout = r3;
        r3.setOrientation(1);
        AnonymousClass1 r5 = new AnonymousClass1(this, r18, 1);
        this.iconTextureView = r5;
        Bitmap r6 = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
        Canvas r7 = new Canvas(r6);
        int r8 = Theme.key_premiumGradient2;
        r7.drawColor(ColorUtils.blendARGB(Theme.getColor(r8, r19), Theme.getColor(Theme.key_dialogBackground, r19), 0.5f));
        r5.setBackgroundBitmap(r6);
        GLIconRenderer r62 = r5.mRenderer;
        r62.colorKey1 = r8;
        r62.colorKey2 = Theme.key_premiumGradient1;
        r62.updateColors();
        r3.addView(r5, LayoutHelper.createLinear(160, 160, 1));
        AnonymousClass2 r63 = new AnonymousClass2(this, r18);
        this.starParticlesView = r63;
        this.paints = new Paint[20];
        updatePaints(0.0f);
        StarParticlesView.Drawable r72 = r63.drawable;
        r72.useGradient = false;
        r72.useBlur = false;
        r72.forceMaxAlpha = true;
        r72.checkBounds = true;
        r72.getPaint = new HeaderCell$$ExternalSyntheticLambda1(this);
        r63.drawable.init();
        r5.setStarParticlesView(r63);
        TextView r52 = new TextView(r18);
        this.titleView = r52;
        r52.setTypeface(AndroidUtilities.bold());
        r52.setTextSize(1, 22.0f);
        int r73 = Theme.key_windowBackgroundWhiteBlackText;
        r52.setTextColor(Theme.getColor(r73, r19));
        r52.setGravity(1);
        r3.addView(r52, LayoutHelper.createLinear(-2, -2, 1, 24, -8, 24, 0));
        LinkSpanDrawable.LinkCollector r9 = new LinkSpanDrawable.LinkCollector(this);
        this.links = r9;
        LinkSpanDrawable.LinksTextView r53 = new LinkSpanDrawable.LinksTextView(r18, r9, r19);
        this.subtitleView = r53;
        r53.setTextSize(1, 15.0f);
        r53.setGravity(17);
        r53.setTextColor(Theme.getColor(r73, r19));
        r53.setMovementMethod(LinkMovementMethod.getInstance());
        r53.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText, r19));
        r53.setImportantForAccessibility(2);
        r3.addView(r53, LayoutHelper.createFrame(-1, -2.0f, 17, 24.0f, 8.0f, 24.0f, 18.0f));
        setClipChildren(false);
        addView(r63, LayoutHelper.createFrame(-1, 234, 48));
        addView(r3);
        setWillNotDraw(false);
    }

    private /* synthetic */ Paint lambda$new$0(Integer r2) {
        return this.paints[r2.intValue() % this.paints.length];
    }

    private /* synthetic */ void lambda$setStars$2(float[] r3, float r4, float r5, boolean r6, ValueAnimator r7) {
        float r72 = ((Float) r7.getAnimatedValue()).floatValue();
        float r1 = r72 - r3[0];
        r3[0] = r72;
        this.iconTextureView.mRenderer.golden = AndroidUtilities.lerp(r4, r5, r72);
        GLIconRenderer r32 = this.iconTextureView.mRenderer;
        float r42 = r32.angleX3;
        float r12 = r1 * 360.0f;
        if (r6 == false) goto L5;
        int r52 = 1;
    L6:
        r32.angleX3 = r42 + (r12 * r52);
        r32.updateColors();
        updatePaints(this.iconTextureView.mRenderer.golden);
        return;
    L5:
        r52 = -1;
        goto L6
    }

    private void updatePaints(float r8) {
        int r0 = Theme.getColor(Theme.key_premiumGradient1, this.resourcesProvider);
        int r1 = Theme.getColor(Theme.key_premiumGradient2, this.resourcesProvider);
        int r02 = ColorUtils.blendARGB(r0, -371690, r8);
        int r82 = ColorUtils.blendARGB(r1, -14281, r8);
        int r12 = 0;
    L3:
        Paint[] r2 = this.paints;
        if (r12 >= r2.length) goto L6;
        r2[r12] = new Paint(1);
        this.paints[r12].setColorFilter(new PorterDuffColorFilter(ColorUtils.blendARGB(r02, r82, r12 / (this.paints.length - 1)), PorterDuff.Mode.SRC_IN));
        r12 = r12 + 1;
        goto L3
    }

    @Override // android.view.View
    public void onDraw(Canvas r3) {
        super.onDraw(r3);
        if (this.links == null) goto L9;
        r3.save();
        r3.translate(this.subtitleView.getLeft(), this.subtitleView.getTop());
        if (this.links.draw(r3) == false) goto L7;
        invalidate();
    L7:
        r3.restore();
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, r3);
        float r22 = this.iconTextureView.getTop() + (this.iconTextureView.getMeasuredHeight() / 2.0f);
        this.starParticlesView.setTranslationY(r22 - (r1.getMeasuredHeight() / 2.0f));
    }

    public void setBoostViaGifsText(TLRPC.Chat r6) {
        setOutlineProvider(new AnonymousClass3(this));
        setClipToOutline(true);
        ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) getLayoutParams();
        r0.topMargin = -AndroidUtilities.dp(6.0f);
        setLayoutParams(r0);
        setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray, this.resourcesProvider));
        this.titleView.setText(LocaleController.formatString("BoostingBoostsViaGifts", R.string.BoostingBoostsViaGifts, new Object[0]));
        boolean r62 = ChatObject.isChannelAndNotMegaGroup(r6);
        LinkSpanDrawable.LinksTextView r02 = this.subtitleView;
        if (r62 == false) goto L5;
        int r63 = R.string.BoostingGetMoreBoost2;
    L6:
        r02.setText(LocaleController.formatString(r63, new Object[0]));
        this.subtitleView.setTextColor(Theme.getColor(Theme.key_dialogTextGray3, this.resourcesProvider));
        return;
    L5:
        r63 = R.string.BoostingGetMoreBoostGroup;
        goto L6
    }

    public void setGiftLinkText() {
        this.titleView.setText(LocaleController.formatString("BoostingGiftLink", R.string.BoostingGiftLink, new Object[0]));
        this.subtitleView.setText(AndroidUtilities.replaceTags(LocaleController.formatString("BoostingLinkAllows", R.string.BoostingLinkAllows, new Object[0])));
    }

    public void setGiftLinkToUserText(long r5, Utilities.Callback<TLObject> r7) {
        this.titleView.setText(LocaleController.formatString("BoostingGiftLink", R.string.BoostingGiftLink, new Object[0]));
        SpannableStringBuilder r0 = AndroidUtilities.replaceTags(LocaleController.getString(R.string.BoostingLinkAllowsToUser));
        TLRPC.User r52 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Long.valueOf(r5));
        this.subtitleView.setText(AndroidUtilities.replaceCharSequence("%1$s", r0, AndroidUtilities.replaceSingleTag("**" + UserObject.getUserName(r52) + "**", Theme.key_chat_messageLinkIn, 2, new HeaderCell$$ExternalSyntheticLambda2(r7, r52), this.resourcesProvider)));
    }

    public void setPaused(boolean r2) {
        this.iconTextureView.setPaused(r2);
        this.starParticlesView.setPaused(r2);
    }

    public void setStars(boolean r8) {
        ValueAnimator r0 = this.goldenAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        float r4 = this.iconTextureView.mRenderer.golden;
        if (r8 == false) goto L8;
        float r5 = 1.0f;
    L9:
        this.goldenAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        float[] r3 = {0.0f};
        this.iconTextureView.cancelIdleAnimation();
        this.iconTextureView.cancelAnimatons();
        this.iconTextureView.startBackAnimation();
        this.goldenAnimator.addUpdateListener(new HeaderCell$$ExternalSyntheticLambda0(this, r3, r4, r5, r8));
        this.goldenAnimator.addListener(new AnonymousClass4(this, r3, r4, r5, r8));
        this.goldenAnimator.setDuration(680);
        this.goldenAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.goldenAnimator.start();
        return;
    L8:
        r5 = 0.0f;
        goto L9
    }

    public void setUnclaimedText() {
        this.titleView.setText(LocaleController.formatString("BoostingGiftLink", R.string.BoostingGiftLink, new Object[0]));
        this.subtitleView.setText(AndroidUtilities.replaceTags(LocaleController.formatString("BoostingLinkAllowsAnyone", R.string.BoostingLinkAllowsAnyone, new Object[0])));
    }

    public void setUsedGiftLinkText() {
        this.titleView.setText(LocaleController.formatString("BoostingUsedGiftLink", R.string.BoostingUsedGiftLink, new Object[0]));
        this.subtitleView.setText(AndroidUtilities.replaceTags(LocaleController.formatString("BoostingLinkUsed", R.string.BoostingLinkUsed, new Object[0])));
    }
}
