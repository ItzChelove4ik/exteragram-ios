package org.telegram.ui.Components.Premium;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EmojiPacksAlert;
import org.telegram.ui.Components.FireworksOverlay;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.LoadingSpan;
import org.telegram.ui.Components.Premium.GLIcon.GLIconRenderer;
import org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView;
import org.telegram.ui.Components.Premium.PremiumGradient;
import org.telegram.ui.Components.Premium.StarParticlesView;
import org.telegram.ui.Components.Premium.boosts.cells.TextInfoCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.PremiumFeatureCell;
import org.telegram.ui.PremiumPreviewFragment;

/* loaded from: classes7.dex */
public class PremiumPreviewBottomSheet extends BottomSheetWithRecyclerListView implements NotificationCenter.NotificationCenterDelegate {
    public Integer accentColor;
    protected int additionEndRow;
    protected int additionStartRow;
    boolean animateConfetti;
    boolean animateConfettiWithStars;
    FrameLayout bulletinContainer;
    FrameLayout buttonContainer;
    protected int buttonRow;
    int[] coords;
    int currentAccount;
    PremiumFeatureCell dummyCell;
    public TLRPC.TL_emojiStatusCollectible emojiStatusCollectible;
    ValueAnimator enterAnimator;
    boolean enterTransitionInProgress;
    float enterTransitionProgress;
    protected int featuresEndRow;
    protected int featuresStartRow;
    FireworksOverlay fireworksOverlay;
    BaseFragment fragment;
    protected TL_stars.StarGift gift;
    protected GiftPremiumBottomSheet$GiftTier giftTier;
    PremiumGradient.PremiumGradientTools gradientTools;
    protected int helpUsRow;
    ViewGroup iconContainer;
    GLIconTextureView iconTextureView;
    public boolean isEmojiStatus;
    boolean isOutboundGift;
    public View overrideTitleIcon;
    protected int paddingRow;
    protected ArrayList<PremiumPreviewFragment.PremiumFeatureData> premiumFeatures;
    protected int rowCount;
    protected int sectionRow;
    StarParticlesView starParticlesView;
    public float startEnterFromScale;
    public View startEnterFromView;
    public float startEnterFromX;
    public float startEnterFromX1;
    public float startEnterFromY;
    public float startEnterFromY1;
    public TLRPC.InputStickerSet statusStickerSet;
    protected LinkSpanDrawable.LinksTextView subtitleView;
    protected int termsRow;
    protected LinkSpanDrawable.LinksTextView[] titleView;
    private FrameLayout titleViewContainer;
    int totalGradientHeight;
    protected TLRPC.User user;

    /* renamed from: org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends ClickableSpan {
        final /* synthetic */ PremiumPreviewBottomSheet this$0;

        public AnonymousClass1(PremiumPreviewBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r2) {
            super.updateDrawState(r2);
            r2.setUnderlineText(false);
            Integer r1 = this.this$0.accentColor;
            if (r1 == null) goto L6;
            r2.setColor(r1.intValue());
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends BaseFragment {
        final /* synthetic */ PremiumPreviewBottomSheet this$0;

        public AnonymousClass2(PremiumPreviewBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public int getCurrentAccount() {
            return this.currentAccount;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public View getFragmentView() {
            return PremiumPreviewBottomSheet.access$1200(this.this$0);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public FrameLayout getLayoutContainer() {
            return this.this$0.bulletinContainer;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Activity getParentActivity() {
            BaseFragment r0 = this.this$0.fragment;
            if (r0 != null) goto L7;
            return null;
        L7:
            return r0.getParentActivity();
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Dialog showDialog(Dialog r1) {
            r1.show();
            return r1;
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends EmojiPacksAlert {
        final /* synthetic */ PremiumPreviewBottomSheet this$0;

        public AnonymousClass3(PremiumPreviewBottomSheet r1, BaseFragment r2, Context r3, Theme.ResourcesProvider r4, ArrayList r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.EmojiPacksAlert
        public void onCloseByLink() {
            this.this$0.dismiss();
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ PremiumPreviewBottomSheet this$0;
        final /* synthetic */ Drawable val$startEnterFromDrawable;

        /* renamed from: $r8$lambda$BHIipVqvhL88Ts2TTEygvK-D5Lg, reason: not valid java name */
        public static /* synthetic */ void m12045$r8$lambda$BHIipVqvhL88Ts2TTEygvKD5Lg(AnonymousClass4 r0, Drawable r1, ValueAnimator r2) {
            r0.lambda$onAnimationEnd$0(r1, r2);
        }

        public AnonymousClass4(PremiumPreviewBottomSheet r1, Drawable r2) {
            this.this$0 = r1;
            this.val$startEnterFromDrawable = r2;
        }

        private /* synthetic */ void lambda$onAnimationEnd$0(Drawable r1, ValueAnimator r2) {
            r1.setAlpha(((Integer) r2.getAnimatedValue()).intValue());
            View r0 = this.this$0.startEnterFromView;
            if ((r0 instanceof ChatMessageCell) == false) goto L6;
            ((ChatMessageCell) r0).invalidateOutbounds();
            return;
        L6:
            r0.invalidate();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            PremiumPreviewBottomSheet r0 = this.this$0;
            r0.enterTransitionInProgress = false;
            r0.enterTransitionProgress = 1.0f;
            r0.iconContainer.invalidate();
            if (this.val$startEnterFromDrawable == null) goto L5;
            ValueAnimator r02 = ValueAnimator.ofInt(new int[]{0, 255});
            r02.addUpdateListener(new PremiumPreviewBottomSheet$4$$ExternalSyntheticLambda0(this, this.val$startEnterFromDrawable));
            r02.start();
        L5:
            super.onAnimationEnd(r4);
        }
    }

    public class Adapter extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ PremiumPreviewBottomSheet this$0;

        /* renamed from: org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet$Adapter$1, reason: invalid class name */
        public class AnonymousClass1 extends LinearLayout {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass1(Adapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.ViewGroup
            public boolean drawChild(Canvas r3, View r4, long r5) {
                PremiumPreviewBottomSheet r0 = this.this$1.this$0;
                if (r4 != r0.iconTextureView) goto L9;
                if (r0.enterTransitionInProgress == false) goto L9;
                return true;
            L9:
                return super.drawChild(r3, r4, r5);
            }
        }

        /* renamed from: org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet$Adapter$2, reason: invalid class name */
        public class AnonymousClass2 extends GLIconTextureView {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass2(Adapter r1, Context r2, int r3) {
                this.this$1 = r1;
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

        /* renamed from: org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet$Adapter$3, reason: invalid class name */
        public class AnonymousClass3 extends LinkSpanDrawable.LinksTextView {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass3(Adapter r1, Context r2, Theme.ResourcesProvider r3) {
                this.this$1 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView
            public int emojiCacheType() {
                return 3;
            }
        }

        /* renamed from: org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet$Adapter$4, reason: invalid class name */
        public class AnonymousClass4 extends StarParticlesView {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass4(Adapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Components.Premium.StarParticlesView
            public void configure() {
                super.configure();
                StarParticlesView.Drawable r2 = this.drawable;
                r2.useGradient = true;
                r2.useBlur = false;
                r2.forceMaxAlpha = true;
                r2.checkBounds = true;
                r2.init();
            }

            @Override // org.telegram.ui.Components.Premium.StarParticlesView, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, r3);
                this.drawable.rect2.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight() - AndroidUtilities.dp(52.0f));
            }
        }

        /* renamed from: org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet$Adapter$5, reason: invalid class name */
        public class AnonymousClass5 extends FrameLayout {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass5(Adapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, r3);
                PremiumPreviewBottomSheet r22 = this.this$1.this$0;
                GLIconTextureView r32 = r22.iconTextureView;
                if (r32 == null) goto L6;
                float r23 = r32.getTop();
                int r33 = this.this$1.this$0.iconTextureView.getMeasuredHeight();
            L5:
                float r24 = r23 + (r33 / 2.0f);
            L10:
                this.this$1.this$0.starParticlesView.setTranslationY(r24 - (r1.getMeasuredHeight() / 2.0f));
                return;
            L6:
                View r25 = r22.overrideTitleIcon;
                if (r25 == null) goto L9;
                r23 = r25.getTop();
                r33 = this.this$1.this$0.overrideTitleIcon.getMeasuredHeight();
                goto L5
            L9:
                r24 = 0.0f;
                goto L10
            }
        }

        /* renamed from: org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet$Adapter$6, reason: invalid class name */
        public class AnonymousClass6 extends PremiumFeatureCell {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass6(Adapter r1, Context r2, Theme.ResourcesProvider r3) {
                this.this$1 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.PremiumFeatureCell, android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r10) {
                RectF r0 = AndroidUtilities.rectTmp;
                r0.set(this.imageView.getLeft(), this.imageView.getTop(), this.imageView.getRight(), this.imageView.getBottom());
                this.this$1.this$0.gradientTools.gradientMatrix(0, 0, getMeasuredWidth(), this.this$1.this$0.totalGradientHeight, 0.0f, -this.data.yOffset);
                r10.drawRoundRect(r0, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), this.this$1.this$0.gradientTools.paint);
                super.dispatchDraw(r10);
            }
        }

        /* renamed from: org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet$Adapter$7, reason: invalid class name */
        public class AnonymousClass7 extends View {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass7(Adapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(68.0f), TLObject.FLAG_30));
            }
        }

        public static /* synthetic */ void $r8$lambda$KxIkfQMmoqt9hYfewVVMsttg7oA(Adapter r0) {
            r0.lambda$onCreateViewHolder$0();
        }

        public static /* synthetic */ void $r8$lambda$MjOvCdXTNuH72K32z2LrjwfiH9Y(Adapter r0) {
            r0.lambda$onCreateViewHolder$1();
        }

        private Adapter(PremiumPreviewBottomSheet r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(PremiumPreviewBottomSheet r1, PremiumPreviewBottomSheetIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$onCreateViewHolder$0() {
            Browser.openUrl(this.this$0.fragment.getParentActivity(), LocaleController.getString(R.string.TermsOfServiceUrl));
        }

        private /* synthetic */ void lambda$onCreateViewHolder$1() {
            Browser.openUrl(this.this$0.fragment.getParentActivity(), LocaleController.getString(R.string.PrivacyPolicyUrl));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.this$0.rowCount;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            PremiumPreviewBottomSheet r0 = this.this$0;
            if (r3 != r0.paddingRow) goto L7;
            return 0;
        L7:
            if (r3 < r0.additionStartRow) goto L13;
            if (r3 >= r0.additionEndRow) goto L13;
            return r0.getAdditionItemViewType(r3);
        L13:
            if (r3 < r0.featuresStartRow) goto L19;
            if (r3 >= r0.featuresEndRow) goto L19;
            return 1;
        L19:
            if (r3 != r0.sectionRow) goto L23;
            return 2;
        L23:
            if (r3 != r0.buttonRow) goto L27;
            return 3;
        L27:
            if (r3 != r0.helpUsRow) goto L31;
            return 4;
        L31:
            if (r3 != r0.termsRow) goto L35;
            return 5;
        L35:
            return super.getItemViewType(r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r3) {
            if (r3.getItemViewType() != 1) goto L5;
        L9:
            return true;
        L5:
            if (this.this$0.isAdditionViewClickable(r3.getItemViewType()) == true) goto L9;
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r4, int r5) {
            PremiumPreviewBottomSheet r0 = this.this$0;
            int r1 = r0.featuresStartRow;
            if (r5 < r1) goto L13;
            if (r5 >= r0.featuresEndRow) goto L13;
            PremiumFeatureCell r42 = (PremiumFeatureCell) r4.itemView;
            PremiumPreviewFragment.PremiumFeatureData r02 = r0.premiumFeatures.get(r5 - r1);
            boolean r12 = true;
            if (r5 != (this.this$0.featuresEndRow - 1)) goto L10;
            r12 = false;
        L10:
            r42.setData(r02, r12);
            return;
        L13:
            if (r5 >= r0.additionStartRow) goto L15;
            return;
        L15:
            if (r5 >= r0.additionEndRow) goto L19;
            r0.onBindAdditionCell(r4.itemView, r5);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r18, int r19) {
            Context r2 = r18.getContext();
            View r3 = this.this$0.onCreateAdditionCell(r19, r2);
            if (r3 == null) goto L7;
            r3.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r3);
        L7:
            if (r19 == 0) goto L20;
            if (r19 != 2) goto L10;
            View r32 = new ShadowSectionCell(r2, 12, PremiumPreviewBottomSheet.access$1000(this.this$0, Theme.key_windowBackgroundGray));
        L52:
            r32.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            this.this$0.afterCellCreated(r19, r32);
            return new RecyclerListView.Holder(r32);
        L10:
            if (r19 != 3) goto L12;
            r32 = new AnonymousClass7(this, r2);
            goto L52
        L12:
            if (r19 != 4) goto L14;
            r32 = new AboutPremiumView(r2);
            goto L52
        L14:
            if (r19 == 5) goto L16;
            r32 = new AnonymousClass6(this, r2, PremiumPreviewBottomSheet.access$900(this.this$0));
            goto L52
        L16:
            TextInfoCell r33 = new TextInfoCell(r2, PremiumPreviewBottomSheet.access$1100(this.this$0));
            r33.setBackground(true);
            String r22 = LocaleController.getString("GiftPremiumPrivacyPolicyAndTerms", R.string.GiftPremiumPrivacyPolicyAndTerms);
            int r7 = Theme.key_chat_messageLinkIn;
            r33.setText(AndroidUtilities.replaceCharSequence("%1$s", AndroidUtilities.replaceSingleTag(r22, r7, 0, new PremiumPreviewBottomSheet$Adapter$$ExternalSyntheticLambda0(this)), AndroidUtilities.replaceSingleTag(LocaleController.getString("GiftPremiumPrivacyPolicy", R.string.GiftPremiumPrivacyPolicy), r7, 0, new PremiumPreviewBottomSheet$Adapter$$ExternalSyntheticLambda1(this))));
            r32 = r33;
            goto L52
        L20:
            AnonymousClass1 r8 = new AnonymousClass1(this, r2);
            this.this$0.iconContainer = r8;
            r8.setOrientation(1);
            PremiumPreviewBottomSheet r9 = this.this$0;
            View r10 = r9.overrideTitleIcon;
            if (r10 != null) goto L24;
            r9.iconTextureView = new AnonymousClass2(this, r2, 1);
            Bitmap r92 = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
            Canvas r102 = new Canvas(r92);
            PremiumPreviewBottomSheet r11 = this.this$0;
            int r12 = Theme.key_premiumGradient2;
            r102.drawColor(ColorUtils.blendARGB(PremiumPreviewBottomSheet.access$000(r11, r12), PremiumPreviewBottomSheet.access$100(this.this$0, Theme.key_dialogBackground), 0.5f));
            this.this$0.iconTextureView.setBackgroundBitmap(r92);
            GLIconRenderer r93 = this.this$0.iconTextureView.mRenderer;
            r93.colorKey1 = r12;
            r93.colorKey2 = Theme.key_premiumGradient1;
            r93.updateColors();
            r8.addView(this.this$0.iconTextureView, LayoutHelper.createLinear(160, 160, 1));
        L28:
            if (PremiumPreviewBottomSheet.m12043$$Nest$fgettitleViewContainer(this.this$0) != null) goto L41;
            PremiumPreviewBottomSheet.m12044$$Nest$fputtitleViewContainer(this.this$0, new FrameLayout(r2));
            PremiumPreviewBottomSheet.m12043$$Nest$fgettitleViewContainer(this.this$0).setClipChildren(false);
            PremiumPreviewBottomSheet r103 = this.this$0;
            Integer r112 = r103.accentColor;
            if (r112 != null) goto L32;
            int r104 = PremiumPreviewBottomSheet.access$200(r103, Theme.key_windowBackgroundWhiteBlueIcon);
        L33:
            new PorterDuffColorFilter(r104, PorterDuff.Mode.SRC_IN);
            this.this$0.titleView = new LinkSpanDrawable.LinksTextView[2];
            int r94 = 0;
        L34:
            if (r94 >= 2) goto L41;
            this.this$0.titleView[r94] = new AnonymousClass3(this, r2, PremiumPreviewBottomSheet.access$300(this.this$0));
            LinkSpanDrawable.LinksTextView r105 = this.this$0.titleView[r94];
            if (r94 != 0) goto L38;
            int r113 = 0;
        L39:
            r105.setVisibility(r113);
            this.this$0.titleView[r94].setTextSize(1, 16.0f);
            this.this$0.titleView[r94].setTypeface(AndroidUtilities.bold());
            this.this$0.titleView[r94].setGravity(1);
            PremiumPreviewBottomSheet r106 = this.this$0;
            r106.titleView[r94].setTextColor(PremiumPreviewBottomSheet.access$400(r106, Theme.key_windowBackgroundWhiteBlackText));
            PremiumPreviewBottomSheet r107 = this.this$0;
            r107.titleView[r94].setLinkTextColor(PremiumPreviewBottomSheet.access$500(r107, Theme.key_windowBackgroundWhiteLinkText));
            PremiumPreviewBottomSheet.m12043$$Nest$fgettitleViewContainer(this.this$0).addView(this.this$0.titleView[r94], LayoutHelper.createFrame(-1, -2.0f));
            r94 = r94 + 1;
            goto L34
        L38:
            r113 = 8;
            goto L39
        L32:
            r104 = r112.intValue();
        L41:
            if (PremiumPreviewBottomSheet.m12043$$Nest$fgettitleViewContainer(this.this$0).getParent() == null) goto L43;
            ((ViewGroup) PremiumPreviewBottomSheet.m12043$$Nest$fgettitleViewContainer(this.this$0).getParent()).removeView(PremiumPreviewBottomSheet.m12043$$Nest$fgettitleViewContainer(this.this$0));
        L43:
            r8.addView(PremiumPreviewBottomSheet.m12043$$Nest$fgettitleViewContainer(this.this$0), LayoutHelper.createLinear(-2, -2, 0.0f, 1, 40, 0, 40, 0));
            PremiumPreviewBottomSheet r34 = this.this$0;
            if (r34.subtitleView != null) goto L47;
            r34.subtitleView = new LinkSpanDrawable.LinksTextView(this.this$0.getContext(), PremiumPreviewBottomSheet.access$600(this.this$0));
            this.this$0.subtitleView.setTextSize(1, 14.0f);
            this.this$0.subtitleView.setGravity(1);
            PremiumPreviewBottomSheet r35 = this.this$0;
            r35.subtitleView.setTextColor(PremiumPreviewBottomSheet.access$700(r35, Theme.key_windowBackgroundWhiteBlackText));
            PremiumPreviewBottomSheet r36 = this.this$0;
            r36.subtitleView.setLinkTextColor(PremiumPreviewBottomSheet.access$800(r36, Theme.key_windowBackgroundWhiteLinkText));
        L47:
            if (this.this$0.subtitleView.getParent() == null) goto L49;
            ((ViewGroup) this.this$0.subtitleView.getParent()).removeView(this.this$0.subtitleView);
        L49:
            r8.addView(this.this$0.subtitleView, LayoutHelper.createLinear(-1, -2, 0.0f, 0, 24, 9, 24, 20));
            this.this$0.setTitle(false);
            this.this$0.starParticlesView = new AnonymousClass4(this, r2);
            AnonymousClass5 r37 = new AnonymousClass5(this, r2);
            r37.setClipChildren(false);
            r37.addView(this.this$0.starParticlesView);
            r37.addView(r8);
            PremiumPreviewBottomSheet r23 = this.this$0;
            GLIconTextureView r6 = r23.iconTextureView;
            r32 = r37;
            if (r6 == null) goto L52;
            r6.setStarParticlesView(r23.starParticlesView);
            r32 = r37;
            goto L52
        L24:
            if (r10.getParent() == null) goto L26;
            ((ViewGroup) this.this$0.overrideTitleIcon.getParent()).removeView(this.this$0.overrideTitleIcon);
        L26:
            this.this$0.attachIconContainer(r8);
            goto L28
        }
    }

    public static /* synthetic */ void $r8$lambda$6AtBpv0seYbohkUY9nS8tfe26Dc(PremiumPreviewBottomSheet r0, ValueAnimator r1) {
        r0.lambda$titleLoaded$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$D7JXqvOhmNYG_xcWkkh6JWkWcKo(PremiumPreviewBottomSheet r0, ValueAnimator r1) {
        r0.lambda$onCustomOpenAnimation$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$EtEg0_JtZynS_cOHIZVwp0RSn98(PremiumPreviewBottomSheet r0) {
        r0.lambda$setTitle$5();
    }

    /* renamed from: $r8$lambda$Fr_grz-DGiFbjciGbm1KtvCQuNs, reason: not valid java name */
    public static /* synthetic */ void m12040$r8$lambda$Fr_grzDGiFbjciGbm1KtvCQuNs(PremiumPreviewBottomSheet r0, DialogInterface r1) {
        r0.lambda$showDialog$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$VKiQwI8SEymlgaBpiVlSpNts5lQ(PremiumPreviewBottomSheet r0, int r1, BaseFragment r2, View r3, int r4) {
        r0.lambda$new$0(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$W0GnYcd9sQn7HNfX--xLqi-Vaxg, reason: not valid java name */
    public static /* synthetic */ void m12041$r8$lambda$W0GnYcd9sQn7HNfXxLqiVaxg(PremiumPreviewBottomSheet r0) {
        r0.lambda$titleLoaded$3();
    }

    /* renamed from: $r8$lambda$nsMcnbXLr-2Xy_NAKQVxNq8CWj0, reason: not valid java name */
    public static /* synthetic */ void m12042$r8$lambda$nsMcnbXLr2Xy_NAKQVxNq8CWj0(PremiumPreviewBottomSheet r0, ClickableSpan r1) {
        r0.lambda$setTitle$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$sGeAG10wJfWOml68J7IX4Qi1P28(PremiumPreviewBottomSheet r0, View r1) {
        r0.lambda$onViewCreated$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$sd7dXXlF4w7RX5syHaefXdxjhGo(PremiumPreviewBottomSheet r0) {
        r0.lambda$show$7();
    }

    /* renamed from: -$$Nest$fgettitleViewContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m12043$$Nest$fgettitleViewContainer(PremiumPreviewBottomSheet r0) {
        return r0.titleViewContainer;
    }

    /* renamed from: -$$Nest$fputtitleViewContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12044$$Nest$fputtitleViewContainer(PremiumPreviewBottomSheet r0, FrameLayout r1) {
        r0.titleViewContainer = r1;
    }

    public PremiumPreviewBottomSheet(BaseFragment r8, int r9, TLRPC.User r10, Theme.ResourcesProvider r11) {
        this(r8, r9, r10, null, null, r11);
    }

    public PremiumPreviewBottomSheet(BaseFragment r7, int r8, TLRPC.User r9, GiftPremiumBottomSheet$GiftTier r10, TL_stars.StarGift r11, Theme.ResourcesProvider r12) {
        super(r7, false, false, false, r12);
        this.premiumFeatures = new ArrayList();
        this.coords = new int[2];
        this.enterTransitionProgress = 0.0f;
        fixNavigationBar();
        this.fragment = r7;
        this.topPadding = 0.26f;
        this.user = r9;
        this.currentAccount = r8;
        this.giftTier = r10;
        this.gift = r11;
        this.dummyCell = new PremiumFeatureCell(getContext());
        PremiumPreviewFragment.fillPremiumFeaturesList(this.premiumFeatures, r8, false);
        if (this.giftTier == null) goto L5;
    L6:
        this.buttonContainer.setVisibility(8);
    L7:
        PremiumGradient.PremiumGradientTools r72 = new PremiumGradient.PremiumGradientTools(Theme.key_premiumGradient1, Theme.key_premiumGradient2, Theme.key_premiumGradient3, Theme.key_premiumGradient4);
        this.gradientTools = r72;
        r72.exactly = true;
        r72.x1 = 0.0f;
        r72.y1 = 1.0f;
        r72.x2 = 0.0f;
        r72.y2 = 0.0f;
        r72.cx = 0.0f;
        r72.cy = 0.0f;
        updateRows();
        this.recyclerListView.setPadding(AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(6.0f), 0);
        this.recyclerListView.setOnItemClickListener(new PremiumPreviewBottomSheet$$ExternalSyntheticLambda4(this, r8, r7));
        MediaDataController.getInstance(r8).preloadPremiumPreviewStickers();
        PremiumPreviewFragment.sentShowScreenStat("profile");
        FireworksOverlay r6 = new FireworksOverlay(getContext());
        this.fireworksOverlay = r6;
        this.container.addView(r6, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r62 = new FrameLayout(getContext());
        this.bulletinContainer = r62;
        this.containerView.addView(r62, LayoutHelper.createFrame(-1, 140, 87));
        return;
    L5:
        if (UserConfig.getInstance(r8).isPremium() == false) goto L7;
        goto L6
    }

    public static /* synthetic */ int access$000(PremiumPreviewBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$100(PremiumPreviewBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1000(PremiumPreviewBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1100(PremiumPreviewBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$1200(PremiumPreviewBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$200(PremiumPreviewBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$300(PremiumPreviewBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$400(PremiumPreviewBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$500(PremiumPreviewBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$600(PremiumPreviewBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$700(PremiumPreviewBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$800(PremiumPreviewBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$900(PremiumPreviewBottomSheet r0) {
        return r0.resourcesProvider;
    }

    private /* synthetic */ void lambda$new$0(int r2, BaseFragment r3, View r4, int r5) {
        if ((r4 instanceof PremiumFeatureCell) == false) goto L5;
        PremiumFeatureCell r52 = (PremiumFeatureCell) r4;
        PremiumPreviewFragment.sentShowFeaturePreview(r2, r52.data.type);
        showDialog(new PremiumFeatureBottomSheet(r3, r52.data.type, false));
    L5:
        onAdditionItemClicked(r4);
    }

    private /* synthetic */ void lambda$onCustomOpenAnimation$8(ValueAnimator r1) {
        this.enterTransitionProgress = ((Float) r1.getAnimatedValue()).floatValue();
        this.container.invalidate();
    }

    private /* synthetic */ void lambda$onViewCreated$2(View r1) {
        PremiumPreviewFragment.sentPremiumButtonClick();
        PremiumPreviewFragment.buyPremium(this.fragment, "profile");
    }

    private /* synthetic */ void lambda$setTitle$5() {
        Browser.openUrl(getContext(), "https://" + MessagesController.getInstance(this.currentAccount).linkPrefix + "/nft/" + this.emojiStatusCollectible.slug);
    }

    private /* synthetic */ void lambda$setTitle$6(ClickableSpan r7) {
        ArrayList r5 = new ArrayList();
        r5.add(this.statusStickerSet);
        AnonymousClass2 r2 = new AnonymousClass2(this);
        BaseFragment r72 = this.fragment;
        if (r72 == null) goto L5;
        r2.setParentFragment(r72);
    L5:
        new AnonymousClass3(this, r2, getContext(), this.resourcesProvider, r5).show();
    }

    private /* synthetic */ void lambda$show$7() {
        this.container.performHapticFeedback(3, 2);     // Catch: Exception -> L5
    L3:
        this.fireworksOverlay.start(this.animateConfettiWithStars);
    }

    private /* synthetic */ void lambda$showDialog$1(DialogInterface r2) {
        GLIconTextureView r22 = this.iconTextureView;
        if (r22 == null) goto L5;
        r22.setDialogVisible(false);
    L5:
        this.starParticlesView.setPaused(false);
    }

    private /* synthetic */ void lambda$titleLoaded$3() {
        this.titleView[0].setVisibility(8);
    }

    private /* synthetic */ void lambda$titleLoaded$4(ValueAnimator r5) {
        float r52 = ((Float) r5.getAnimatedValue()).floatValue();
        ViewGroup.LayoutParams r0 = this.titleViewContainer.getLayoutParams();
        r0.height = AndroidUtilities.lerp(this.titleView[0].getHeight(), this.titleView[1].getHeight(), r52);
        this.titleViewContainer.requestLayout();
    }

    private void measureGradient(int r7, int r8) {
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= this.premiumFeatures.size()) goto L6;
        this.dummyCell.setData(this.premiumFeatures.get(r1), false);
        this.dummyCell.measure(View.MeasureSpec.makeMeasureSpec(r7, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r8, Integer.MIN_VALUE));
        this.premiumFeatures.get(r1).yOffset = r2;
        r2 = r2 + this.dummyCell.getMeasuredHeight();
        r1 = r1 + 1;
        goto L4
    L6:
        this.totalGradientHeight = r2;
    }

    private void titleLoaded(CharSequence r5, boolean r6) {
        LinkSpanDrawable.LinksTextView[] r0 = this.titleView;
        if (r0 == null) goto L14;
        r0[1].setText(r5);
        if (this.titleView[1].getVisibility() == 0) goto L13;
        LinkSpanDrawable.LinksTextView[] r52 = this.titleView;
        if (r6 == false) goto L11;
        r52[1].setAlpha(0.0f);
        this.titleView[1].setVisibility(0);
        ViewPropertyAnimator r53 = this.titleView[1].animate().alpha(1.0f);
        CubicBezierInterpolator r62 = CubicBezierInterpolator.DEFAULT;
        r53.setInterpolator(r62).setDuration(200).start();
        this.titleView[0].animate().alpha(0.0f).setInterpolator(r62).setDuration(200).withEndAction(new PremiumPreviewBottomSheet$$ExternalSyntheticLambda7(this)).start();
        ValueAnimator r54 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r54.addUpdateListener(new PremiumPreviewBottomSheet$$ExternalSyntheticLambda8(this));
        r54.setInterpolator(r62);
        r54.setDuration(200);
        r54.start();
        return;
    L11:
        r52[1].setAlpha(1.0f);
        this.titleView[1].setVisibility(0);
        this.titleView[0].setAlpha(0.0f);
        this.titleView[0].setVisibility(8);
        return;
    L13:
        return;
    }

    public void afterCellCreated(int r1, View r2) {
    }

    public void attachIconContainer(LinearLayout r9) {
        r9.addView(this.overrideTitleIcon, LayoutHelper.createLinear(140, 140, 1.0f, 17, 10, 10, 10, 10));
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r2) {
        return new Adapter(this, null);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.groupStickersDidLoad) goto L10;
        TLRPC.InputStickerSet r32 = this.statusStickerSet;
        if (r32 != null) goto L7;
        return;
    L7:
        if (r32.id != ((Long) r5[0]).longValue()) goto L12;
        setTitle(true);
        return;
    L12:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{4});
        ValueAnimator r0 = this.enterAnimator;
        if (r0 == null) goto L6;
        r0.cancel();
    L6:
        if (this.fireworksOverlay.isStarted() == false) goto L9;
        this.fireworksOverlay.animate().alpha(0.0f).setDuration(150).start();
        return;
    }

    public int getAdditionItemViewType(int r1) {
        return 0;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.TelegramPremium);
    }

    public boolean isAdditionViewClickable(int r1) {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void mainContainerDispatchDraw(Canvas r18) {
        View r2 = this.overrideTitleIcon;
        if (r2 != null) goto L5;
    L9:
        super.mainContainerDispatchDraw(r18);
        if (this.startEnterFromView != null) goto L12;
        return;
    L12:
        if (this.enterTransitionInProgress == false) goto L43;
        View r22 = this.overrideTitleIcon;
        if (r22 != null) goto L16;
        View r4 = this.iconTextureView;
    L17:
        if (r4 != r22) goto L19;
        r22.setVisibility(0);
    L19:
        r18.save();
        float[] r7 = {this.startEnterFromX, this.startEnterFromY};
        this.startEnterFromView.getMatrix().mapPoints(r7);
        View r5 = this.startEnterFromView;
        if ((r5 instanceof SimpleTextView) == false) goto L23;
        Drawable r52 = ((SimpleTextView) r5).getRightDrawable();
    L26:
        if (r52 != null) goto L29;
        r18.restore();
        return;
    L29:
        int[] r8 = this.coords;
        float r9 = ((-r8[0]) + this.startEnterFromX1) + r7[0];
        float r82 = ((-r8[1]) + this.startEnterFromY1) + r7[1];
        if (AndroidUtilities.isTablet() == false) goto L32;
        ViewGroup r23 = this.fragment.getParentLayout().getView();
        r9 = r9 + (r23.getX() + r23.getPaddingLeft());
        r82 = r82 + (r23.getY() + r23.getPaddingTop());
    L32:
        float r24 = this.startEnterFromScale * r52.getIntrinsicWidth();
        float r72 = r4.getMeasuredHeight() * 0.8f;
        float r10 = r72 / r24;
        float r25 = r24 / r72;
        float r73 = r4.getMeasuredWidth() / 2.0f;
        View r12 = r4;
    L34:
        if (r12 == this.container) goto L38;
        if (r12 == null) goto L38;
        r73 = r73 + r12.getX();
        r12 = (View) r12.getParent();
    L38:
        float r122 = (((r4.getY() + 0.0f) + ((View) r4.getParent()).getY()) + ((View) r4.getParent().getParent()).getY()) + (r4.getMeasuredHeight() / 2.0f);
        float r74 = AndroidUtilities.lerp(r9, r73, CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(this.enterTransitionProgress));
        float r83 = AndroidUtilities.lerp(r82, r122, this.enterTransitionProgress);
        float r92 = this.startEnterFromScale;
        float r123 = this.enterTransitionProgress;
        float r93 = (r92 * (1.0f - r123)) + (r10 * r123);
        r18.save();
        r18.scale(r93, r93, r74, r83);
        int r94 = (int) r74;
        int r124 = (int) r83;
        r52.setBounds(r94 - (r52.getIntrinsicWidth() / 2), r124 - (r52.getIntrinsicHeight() / 2), r94 + (r52.getIntrinsicWidth() / 2), r124 + (r52.getIntrinsicHeight() / 2));
        r52.setAlpha((int) ((1.0f - Utilities.clamp(this.enterTransitionProgress, 1.0f, 0.0f)) * 255.0f));
        r52.draw(r18);
        r52.setAlpha(0);
        r18.restore();
        float r0 = AndroidUtilities.lerp(r25, 1.0f, this.enterTransitionProgress);
        r18.scale(r0, r0, r74, r83);
        r18.translate(r74 - (r4.getMeasuredWidth() / 2.0f), r83 - (r4.getMeasuredHeight() / 2.0f));
        r4.draw(r18);
        r18.restore();
        return;
    L23:
        if ((r5 instanceof ChatMessageCell) == false) goto L25;
        r52 = ((ChatMessageCell) r5).currentNameStatusDrawable;
        goto L26
    L25:
        r52 = null;
        goto L26
    L16:
        r4 = r22;
        goto L17
    L43:
        return;
    L5:
        if (this.enterTransitionInProgress == false) goto L7;
        int r42 = 4;
    L8:
        r2.setVisibility(r42);
        goto L9
    L7:
        r42 = 0;
        goto L8
    }

    public boolean needDefaultPremiumBtn() {
        return true;
    }

    public void onAdditionItemClicked(View r1) {
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(UserConfig.selectedAccount).addObserver(this, NotificationCenter.groupStickersDidLoad);
    }

    public void onBindAdditionCell(View r1, int r2) {
    }

    public View onCreateAdditionCell(int r1, Context r2) {
        return null;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean onCustomOpenAnimation() {
        if (this.startEnterFromView != null) goto L5;
        return false;
    L5:
        this.enterAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.enterTransitionProgress = 0.0f;
        this.enterTransitionInProgress = true;
        this.iconContainer.invalidate();
        View r0 = this.startEnterFromView;
        if ((r0 instanceof SimpleTextView) == false) goto L9;
        Drawable r02 = ((SimpleTextView) r0).getRightDrawable();
    L12:
        if (r02 == null) goto L14;
        r02.setAlpha(0);
    L14:
        View r1 = this.startEnterFromView;
        if ((r1 instanceof ChatMessageCell) == false) goto L17;
        ((ChatMessageCell) r1).invalidateOutbounds();
    L18:
        GLIconTextureView r12 = this.iconTextureView;
        if (r12 == null) goto L21;
        r12.startEnterAnimation(-360, 100);
    L21:
        this.enterAnimator.addUpdateListener(new PremiumPreviewBottomSheet$$ExternalSyntheticLambda5(this));
        this.enterAnimator.addListener(new AnonymousClass4(this, r02));
        this.enterAnimator.setDuration(600);
        this.enterAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.enterAnimator.start();
        return super.onCustomOpenAnimation();
    L17:
        r1.invalidate();
        goto L18
    L9:
        if ((r0 instanceof ChatMessageCell) == false) goto L11;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r2 = ((ChatMessageCell) r0).currentNameStatusDrawable;
        ((ChatMessageCell) r0).invalidateOutbounds();
        r02 = r2;
        goto L12
    L11:
        r02 = null;
        goto L12
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(UserConfig.selectedAccount).removeObserver(this, NotificationCenter.groupStickersDidLoad);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public void onPreMeasure(int r1, int r2) {
        super.onPreMeasure(r1, r2);
        measureGradient(View.MeasureSpec.getSize(r1), View.MeasureSpec.getSize(r2));
        this.container.getLocationOnScreen(this.coords);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public void onViewCreated(FrameLayout r13) {
        super.onViewCreated(r13);
        this.currentAccount = UserConfig.selectedAccount;
        PremiumButtonView r0 = new PremiumButtonView(getContext(), false, this.resourcesProvider);
        r0.setButton(PremiumPreviewFragment.getPremiumButtonText(this.currentAccount, null), new PremiumPreviewBottomSheet$$ExternalSyntheticLambda3(this));
        this.buttonContainer = new FrameLayout(getContext());
        View r1 = new View(getContext());
        r1.setBackgroundColor(getThemedColor(Theme.key_divider));
        this.buttonContainer.addView(r1, LayoutHelper.createFrame(-1, 1.0f));
        r1.getLayoutParams().height = 1;
        AndroidUtilities.updateViewVisibilityAnimated(r1, true, 1.0f, false);
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L5;
        return;
    L5:
        if (needDefaultPremiumBtn() == false) goto L9;
        this.buttonContainer.addView(r0, LayoutHelper.createFrame(-1, 48.0f, 16, 16.0f, 0.0f, 16.0f, 0.0f));
        this.buttonContainer.setBackgroundColor(getThemedColor(Theme.key_dialogBackground));
        r13.addView(this.buttonContainer, LayoutHelper.createFrame(-1, 68, 80));
        return;
    }

    public PremiumPreviewBottomSheet setAnimateConfetti(boolean r1) {
        this.animateConfetti = r1;
        return this;
    }

    public PremiumPreviewBottomSheet setAnimateConfettiWithStars(boolean r1) {
        this.animateConfettiWithStars = r1;
        return this;
    }

    public PremiumPreviewBottomSheet setOutboundGift(boolean r1) {
        this.isOutboundGift = r1;
        return this;
    }

    public void setTitle(boolean r13) {
        LinkSpanDrawable.LinksTextView[] r0 = this.titleView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.subtitleView == null) goto L120;
        TLRPC.TL_emojiStatusCollectible r1 = this.emojiStatusCollectible;
        if (r1 == null) goto L14;
        String r132 = r1.title;
        int r02 = r132.lastIndexOf(32);
        if (r02 < 0) goto L12;
        r132 = r132.substring(0, r02);
    L12:
        this.titleView[0].setText(AndroidUtilities.replaceSingleTag(LocaleController.formatString(R.string.TelegramPremiumUserStatusCollectibleDialogTitle, new Object[]{DialogObject.getShortName(this.user), r132}), new PremiumPreviewBottomSheet$$ExternalSyntheticLambda1(this)));
        this.subtitleView.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.TelegramPremiumUserStatusDialogSubtitle)));
    L115:
        LinkSpanDrawable.LinksTextView r133 = this.titleView[0];     // Catch: Exception -> L112
        r133.setText(Emoji.replaceEmoji(r133.getText(), this.titleView[0].getPaint().getFontMetricsInt(), false));     // Catch: Exception -> L112
        return;
    L121:
        return;
    L14:
        if (this.statusStickerSet == null) goto L55;
        int r03 = R.string.TelegramPremiumUserStatusDialogTitle;
        TLRPC.User r12 = this.user;
        String r04 = LocaleController.formatString(r03, new Object[]{ContactsController.formatName(r12.first_name, r12.last_name), "<STICKERSET>"});
        Integer r14 = this.accentColor;
        if (r14 != null) goto L18;
        int r15 = getThemedColor(Theme.key_windowBackgroundWhiteBlueButton);
    L19:
        CharSequence r05 = AndroidUtilities.replaceSingleLink(r04, r15);
        r05 = Emoji.replaceEmoji(r05, this.titleView[0].getPaint().getFontMetricsInt(), false);     // Catch: Exception -> L111
    L22:
        if ((r05 instanceof SpannableStringBuilder) == false) goto L24;
        SpannableStringBuilder r16 = (SpannableStringBuilder) r05;
    L25:
        int r06 = r05.toString().indexOf("<STICKERSET>");
        if (r06 < 0) goto L53;
        TLRPC.TL_messages_stickerSet r4 = MediaDataController.getInstance(this.currentAccount).getStickerSet(this.statusStickerSet, false);
        if (r4 != null) goto L30;
    L40:
        TLRPC.Document r6 = null;
    L42:
        if (r6 == null) goto L48;
        SpannableStringBuilder r8 = new SpannableStringBuilder("x");
        r8.setSpan(new AnimatedEmojiSpan(r6, this.titleView[0].getPaint().getFontMetricsInt()), 0, r8.length(), 33);
        if (r4 != null) goto L46;
    L49:
        r16.replace(r06, r06 + 12, r8);
        r16.setSpan(new AnonymousClass1(this), r06, r8.length() + r06, 33);
        this.titleView[1].setOnLinkPressListener(new PremiumPreviewBottomSheet$$ExternalSyntheticLambda2(this));
        if (r6 == null) goto L52;
        titleLoaded(r16, r13);
        goto L53
    L52:
        this.titleView[0].setText(r16, null);
        goto L53
    L46:
        if (r4.set == null) goto L49;
        r8.append(" ").append(r4.set.title);
        goto L49
    L48:
        r8 = new SpannableStringBuilder("xxxxxx");
        r8.setSpan(new LoadingSpan(this.titleView[0], AndroidUtilities.dp(100.0f)), 0, r8.length(), 33);
        goto L49
    L30:
        if (r4.documents.isEmpty() == true) goto L40;
        r6 = r4.documents.get(0);
        if (r4.set == null) goto L42;
        int r7 = 0;
    L35:
        if (r7 >= r4.documents.size()) goto L42;
        if (r4.documents.get(r7).id == r4.set.thumb_document_id) goto L38;
        r7 = r7 + 1;
        goto L35
    L38:
        r6 = r4.documents.get(r7);
    L53:
        this.subtitleView.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.TelegramPremiumUserStatusDialogSubtitle)));
        goto L115
    L24:
        r16 = new SpannableStringBuilder(r05);
        goto L25
    L18:
        r15 = r14.intValue();
        goto L19
    L55:
        if (this.isEmojiStatus == false) goto L57;
        LinkSpanDrawable.LinksTextView r134 = r0[0];
        int r07 = R.string.TelegramPremiumUserStatusDefaultDialogTitle;
        TLRPC.User r17 = this.user;
        r134.setText(AndroidUtilities.replaceTags(LocaleController.formatString(r07, new Object[]{ContactsController.formatName(r17.first_name, r17.last_name)})));
        LinkSpanDrawable.LinksTextView r135 = this.subtitleView;
        int r08 = R.string.TelegramPremiumUserStatusDialogSubtitle;
        TLRPC.User r18 = this.user;
        r135.setText(AndroidUtilities.replaceTags(LocaleController.formatString(r08, new Object[]{ContactsController.formatName(r18.first_name, r18.last_name)})));
        goto L115
    L57:
        GiftPremiumBottomSheet$GiftTier r136 = this.giftTier;
        if (r136 == null) goto L95;
        if (this.isOutboundGift == false) goto L77;
        LinkSpanDrawable.LinksTextView r09 = r0[0];
        int r19 = R.string.TelegramPremiumUserGiftedPremiumOutboundDialogTitleWithPlural;
        TLRPC.User r42 = this.user;
        String r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r42 == null) goto L64;
        String r43 = r42.first_name;
    L65:
        String r137 = LocaleController.formatString(r19, new Object[]{r43, LocaleController.formatPluralString("GiftMonths", r136.getMonths(), new Object[0])});
        Integer r110 = this.accentColor;
        if (r110 != null) goto L68;
        int r111 = getThemedColor(Theme.key_windowBackgroundWhiteBlueButton);
    L69:
        r09.setText(AndroidUtilities.replaceSingleLink(r137, r111));
        LinkSpanDrawable.LinksTextView r138 = this.subtitleView;
        int r010 = R.string.TelegramPremiumUserGiftedPremiumOutboundDialogSubtitle;
        TLRPC.User r112 = this.user;
        if (r112 == null) goto L72;
        r5 = r112.first_name;
    L72:
        String r011 = LocaleController.formatString(r010, new Object[]{r5});
        Integer r113 = this.accentColor;
        if (r113 != null) goto L75;
        int r114 = getThemedColor(Theme.key_windowBackgroundWhiteBlueButton);
    L76:
        r138.setText(AndroidUtilities.replaceSingleLink(r011, r114));
        goto L115
    L75:
        r114 = r113.intValue();
        goto L76
    L68:
        r111 = r110.intValue();
        goto L69
    L64:
        r43 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L65
    L77:
        TLRPC.User r139 = this.user;
        if (r139 != null) goto L80;
    L89:
        LinkSpanDrawable.LinksTextView r1310 = this.titleView[0];
        String r012 = LocaleController.formatString(R.string.TelegramPremiumUserGiftedPremiumDialogTitleWithPluralSomeone, new Object[]{LocaleController.formatPluralString("GiftMonths", this.giftTier.getMonths(), new Object[0])});
        Integer r115 = this.accentColor;
        if (r115 != null) goto L92;
        int r116 = getThemedColor(Theme.key_windowBackgroundWhiteBlueButton);
    L93:
        r1310.setText(AndroidUtilities.replaceSingleLink(r012, r116));
        this.subtitleView.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.TelegramPremiumUserGiftedPremiumDialogSubtitle)));
        goto L115
    L92:
        r116 = r115.intValue();
        goto L93
    L80:
        if (TextUtils.isEmpty(r139.first_name) == true) goto L89;
        TLRPC.User r1311 = this.user;
        if (r1311.id == 777000) goto L89;
        LinkSpanDrawable.LinksTextView r013 = this.titleView[0];
        String r1312 = LocaleController.formatString(R.string.TelegramPremiumUserGiftedPremiumDialogTitleWithPlural, new Object[]{r1311.first_name, LocaleController.formatPluralString("GiftMonths", this.giftTier.getMonths(), new Object[0])});
        Integer r117 = this.accentColor;
        if (r117 != null) goto L87;
        int r118 = getThemedColor(Theme.key_windowBackgroundWhiteBlueButton);
    L88:
        r013.setText(AndroidUtilities.replaceSingleLink(r1312, r118));
        this.subtitleView.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.TelegramPremiumUserGiftedPremiumDialogSubtitle)));
        goto L115
    L87:
        r118 = r117.intValue();
        goto L88
    L95:
        if (this.gift == null) goto L101;
        r0[0].setText(LocaleController.getString(R.string.Gift2PremiumTitle));
        this.titleView[0].setTextSize(1, 20.0f);
        TL_stars.StarGift r1313 = this.gift;
        boolean r014 = r1313.limited_per_user;
        LinkSpanDrawable.LinksTextView r119 = this.subtitleView;
        if (r014 == false) goto L99;
        r119.setText(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("Gift2PremiumSubtitleMany", r1313.per_user_total)));
    L100:
        this.subtitleView.setTextSize(1, 14.0f);
        goto L115
    L99:
        r119.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.Gift2PremiumSubtitle)));
        goto L100
    L101:
        TLRPC.User r1314 = this.user;
        if (r1314 != null) goto L104;
        r0[0].setText(LocaleController.getString(R.string.TelegramPremium));
        this.subtitleView.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.TelegramPremiumSubscribedSubtitle)));
        goto L115
    L104:
        LinkSpanDrawable.LinksTextView r015 = r0[0];
        String r1315 = LocaleController.formatString(R.string.TelegramPremiumUserDialogTitle, new Object[]{ContactsController.formatName(r1314.first_name, r1314.last_name)});
        Integer r120 = this.accentColor;
        if (r120 != null) goto L107;
        int r121 = getThemedColor(Theme.key_windowBackgroundWhiteBlueButton);
    L108:
        r015.setText(AndroidUtilities.replaceSingleLink(r1315, r121));
        this.subtitleView.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.TelegramPremiumUserDialogSubtitle)));
        goto L115
    L107:
        r121 = r120.intValue();
        goto L108
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{4});
        if (this.animateConfetti == false) goto L6;
        AndroidUtilities.runOnUIThread(new PremiumPreviewBottomSheet$$ExternalSyntheticLambda6(this), 200);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public boolean showDialog(Dialog r3) {
        GLIconTextureView r0 = this.iconTextureView;
        if (r0 == null) goto L5;
        r0.setDialogVisible(true);
    L5:
        this.starParticlesView.setPaused(true);
        r3.setOnDismissListener(new PremiumPreviewBottomSheet$$ExternalSyntheticLambda0(this));
        r3.show();
        return true;
    }

    public void updateRows() {
        int r0 = this.rowCount;
        int r1 = r0 + 1;
        this.rowCount = r1;
        this.paddingRow = r0;
        this.featuresStartRow = r1;
        int r12 = r1 + this.premiumFeatures.size();
        this.featuresEndRow = r12;
        this.rowCount = r12 + 1;
        this.sectionRow = r12;
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L5;
        return;
    L5:
        if (this.giftTier != null) goto L9;
        int r02 = this.rowCount;
        this.rowCount = r02 + 1;
        this.buttonRow = r02;
        return;
    }
}
