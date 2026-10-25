package org.telegram.ui.TON;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.SpannableStringBuilder;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.BirthdayController;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.utils.tlutils.AmountUtils$Amount;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.AccountFrozenAlert;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.FireworksOverlay;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.GLIcon.GLIconRenderer;
import org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView;
import org.telegram.ui.Components.Premium.StarParticlesView;
import org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.GradientHeaderActivity;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stars.BotStarsActivity;
import org.telegram.ui.Stars.BotStarsController;
import org.telegram.ui.Stars.ExplainStarsSheet;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.HintView2;
import org.telegram.ui.bots.ChannelAffiliateProgramsFragment;

/* loaded from: classes7.dex */
public class TONIntroActivity extends GradientHeaderActivity implements NotificationCenter.NotificationCenterDelegate {
    private final int BUTTON_AFFILIATE;
    private final int BUTTON_EXPAND;
    private final int BUTTON_GIFT;
    private final int BUTTON_SUBSCRIPTIONS_EXPAND;
    private FrameLayout aboveTitleView;
    private UniversalAdapter adapter;
    private final boolean allowTopUp;
    private LinearLayout balanceLayout;
    private ButtonWithCounterView buyButton;
    private View emptyLayout;
    private boolean expanded;
    private FireworksOverlay fireworksOverlay;
    private boolean hadTransactions;
    private GLIconTextureView iconTextureView;
    private FrameLayout oneButtonsLayout;
    private SpannableStringBuilder starBalanceIcon;
    private AnimatedTextView starBalanceTextView;
    private AnimatedTextView starBalanceTitleView;
    private ButtonWithCounterView topUpButton;
    private StarsIntroActivity.StarsTransactionsLayout transactionsLayout;
    private boolean twoButtons;
    private LinearLayout twoButtonsLayout;
    private ButtonWithCounterView withdrawButton;

    /* renamed from: org.telegram.ui.TON.TONIntroActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends View {
        final /* synthetic */ TONIntroActivity this$0;

        public AnonymousClass1(TONIntroActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r4, int r5) {
            TONIntroActivity r52 = this.this$0;
            if (r52.isLandscapeMode == false) goto L5;
            int r0 = (r52.statusBarHeight + TONIntroActivity.access$000(r52).getMeasuredHeight()) - AndroidUtilities.dp(16.0f);
        L9:
            super.onMeasure(r4, View.MeasureSpec.makeMeasureSpec((int) (r0 - (TONIntroActivity.access$100(this.this$0) * 2.5f)), TLObject.FLAG_30));
            return;
        L5:
            int r53 = AndroidUtilities.dp(140.0f);
            TONIntroActivity r02 = this.this$0;
            int r54 = r53 + r02.statusBarHeight;
            if ((r02.backgroundView.getMeasuredHeight() + AndroidUtilities.dp(24.0f)) <= r54) goto L8;
            r54 = this.this$0.backgroundView.getMeasuredHeight() + AndroidUtilities.dp(24.0f);
        L8:
            r0 = r54;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.TON.TONIntroActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ TONIntroActivity this$0;

        public AnonymousClass2(TONIntroActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            if (TONIntroActivity.m20602$$Nest$fgettwoButtons(this.this$0) == false) goto L7;
            return false;
        L7:
            return super.dispatchTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.TON.TONIntroActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends LinearLayout {
        final /* synthetic */ TONIntroActivity this$0;

        public AnonymousClass3(TONIntroActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            if (TONIntroActivity.m20602$$Nest$fgettwoButtons(this.this$0) == true) goto L7;
            return false;
        L7:
            return super.dispatchTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.TON.TONIntroActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends StarParticlesView {
        Paint[] paints;
        final /* synthetic */ int val$particlesCount;
        final /* synthetic */ int val$type;

        public static /* synthetic */ Paint $r8$lambda$qACkLKXeH7KLH5bYS2IAv2KMzYA(AnonymousClass4 r0, Integer r1) {
            return r0.lambda$configure$0(r1);
        }

        public AnonymousClass4(Context r1, int r2, int r3) {
            this.val$particlesCount = r2;
            this.val$type = r3;
            super(r1);
            setClipWithGradient();
        }

        private /* synthetic */ Paint lambda$configure$0(Integer r2) {
            return this.paints[r2.intValue() % this.paints.length];
        }

        @Override // org.telegram.ui.Components.Premium.StarParticlesView
        public void configure() {
            StarParticlesView.Drawable r0 = new StarParticlesView.Drawable(this.val$particlesCount);
            this.drawable = r0;
            r0.type = 106;
            int r1 = 0;
            r0.roundEffect = false;
            r0.useRotate = false;
            r0.useBlur = true;
            r0.checkBounds = true;
            r0.isCircle = false;
            r0.useScale = true;
            r0.startFromCenter = true;
            if (this.val$type != 1) goto L5;
            r0.centerOffsetY = AndroidUtilities.dp(24.0f);
        L5:
            this.paints = new Paint[20];
        L6:
            Paint[] r02 = this.paints;
            if (r1 >= r02.length) goto L9;
            r02[r1] = new Paint(1);
            this.paints[r1].setColorFilter(new PorterDuffColorFilter(ColorUtils.blendARGB(-13729319, -14238726, r1 / (this.paints.length - 1)), PorterDuff.Mode.SRC_IN));
            r1 = r1 + 1;
            goto L6
        L9:
            this.drawable.getPaint = new TONIntroActivity$4$$ExternalSyntheticLambda0(this);
            StarParticlesView.Drawable r7 = this.drawable;
            r7.size1 = 17;
            r7.size2 = 18;
            r7.size3 = 19;
            r7.colorKey = Theme.key_windowBackgroundWhiteBlackText;
            r7.init();
        }

        @Override // org.telegram.ui.Components.Premium.StarParticlesView
        public int getStarsRectWidth() {
            return getMeasuredWidth();
        }
    }

    /* renamed from: org.telegram.ui.TON.TONIntroActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends UniversalAdapter {
        final /* synthetic */ TONIntroActivity this$0;

        public AnonymousClass5(TONIntroActivity r1, RecyclerListView r2, Context r3, int r4, int r5, boolean r6, Utilities.Callback2 r7, Theme.ResourcesProvider r8) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        @Override // org.telegram.ui.Components.UniversalAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r9, int r10) {
            if (r10 != 42) goto L7;
            HeaderCell r1 = new HeaderCell(this.this$0.getContext(), Theme.key_windowBackgroundWhiteBlueHeader, 21, 0, false, TONIntroActivity.access$900(this.this$0));
            r1.setHeight(25);
            return new RecyclerListView.Holder(r1);
        L7:
            return super.onCreateViewHolder(r9, r10);
        }
    }

    public class NestedFrameLayout extends GradientHeaderActivity.ContentView implements NestedScrollingParent3 {
        private NestedScrollingParentHelper nestedScrollingParentHelper;
        final /* synthetic */ TONIntroActivity this$0;

        /* renamed from: $r8$lambda$WA6Y5PS5IUX6ECsCKhrlb-mHX4k, reason: not valid java name */
        public static /* synthetic */ void m20603$r8$lambda$WA6Y5PS5IUX6ECsCKhrlbmHX4k(NestedFrameLayout r0) {
            r0.lambda$onNestedScroll$0();
        }

        public NestedFrameLayout(TONIntroActivity r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
            this.nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        }

        private /* synthetic */ void lambda$onNestedScroll$0() {
            RecyclerListView r1 = TONIntroActivity.m20601$$Nest$fgettransactionsLayout(this.this$0).getCurrentListView();     // Catch: Throwable -> L8
            if (r1 != null) goto L5;
            return;
        L5:
            if (r1.getAdapter() == null) goto L12;
            r1.getAdapter().notifyDataSetChanged();     // Catch: Throwable -> L8
            return;
        L12:
            return;
        }

        @Override // org.telegram.ui.Components.NestedSizeNotifierLayout, android.view.ViewGroup, android.view.ViewParent
        public boolean onNestedPreFling(View r1, float r2, float r3) {
            return super.onNestedPreFling(r1, r2, r3);
        }

        @Override // org.telegram.ui.Components.NestedSizeNotifierLayout, androidx.core.view.NestedScrollingParent2
        public void onNestedPreScroll(View r5, int r6, int r7, int[] r8, int r9) {
            if (r5 == TONIntroActivity.access$400(this.this$0)) goto L5;
            return;
        L5:
            if (TONIntroActivity.m20601$$Nest$fgettransactionsLayout(this.this$0).isAttachedToWindow() == false) goto L47;
            boolean r52 = TONIntroActivity.access$500(this.this$0).isSearchFieldVisible();
            int r62 = (((View) TONIntroActivity.m20601$$Nest$fgettransactionsLayout(this.this$0).getParent()).getTop() - AndroidUtilities.statusBarHeight) - ActionBar.getCurrentActionBarHeight();
            int r92 = ((View) TONIntroActivity.m20601$$Nest$fgettransactionsLayout(this.this$0).getParent()).getBottom();
            boolean r0 = false;
            if (r7 < 0) goto L9;
            if (r52 == false) goto L38;
            RecyclerListView r4 = TONIntroActivity.m20601$$Nest$fgettransactionsLayout(this.this$0).getCurrentListView();
            r8[1] = r7;
            if (r62 <= 0) goto L33;
            r8[1] = 0;
        L33:
            if (r4 == null) goto L49;
            int r53 = r8[1];
            if (r53 <= 0) goto L50;
            r4.scrollBy(0, r53);
            return;
        L50:
            return;
        L49:
            return;
        L38:
            if (r7 <= 0) goto L51;
            RecyclerListView r54 = TONIntroActivity.m20601$$Nest$fgettransactionsLayout(this.this$0).getCurrentListView();
            if ((TONIntroActivity.access$700(this.this$0).getHeight() - r92) < 0) goto L52;
            if (r54 != null) goto L43;
            return;
        L43:
            if (r54.canScrollVertically(1) == true) goto L54;
            r8[1] = r7;
            TONIntroActivity.access$800(this.this$0).stopScroll();
            return;
        L54:
            return;
        L52:
            return;
        L51:
            return;
        L9:
            if ((TONIntroActivity.access$600(this.this$0).getHeight() - r92) < 0) goto L22;
            RecyclerListView r42 = TONIntroActivity.m20601$$Nest$fgettransactionsLayout(this.this$0).getCurrentListView();
            int r93 = ((LinearLayoutManager) r42.getLayoutManager()).findFirstVisibleItemPosition();
            int r2 = -1;
            if (r93 == (-1)) goto L22;
            RecyclerView.ViewHolder r3 = r42.findViewHolderForAdapterPosition(r93);
            if (r3 == null) goto L15;
            r2 = r3.itemView.getTop();
        L15:
            int r32 = r42.getPaddingTop();
            if (r2 != r32) goto L18;
            if (r93 == 0) goto L22;
        L18:
            if (r93 == 0) goto L20;
            int r94 = r7;
        L21:
            r8[1] = r94;
            r42.scrollBy(0, r7);
            r0 = true;
            goto L22
        L20:
            r94 = Math.max(r7, r2 - r32);
        L22:
            if (r52 == false) goto L48;
            if (r0 == true) goto L27;
            if (r62 >= 0) goto L27;
            r8[1] = r7 - Math.max(r62, r7);
            return;
        L27:
            r8[1] = r7;
            return;
        L48:
            return;
        }

        @Override // org.telegram.ui.Components.NestedSizeNotifierLayout, androidx.core.view.NestedScrollingParent2
        public void onNestedScroll(View r1, int r2, int r3, int r4, int r5, int r6) {
        }

        @Override // org.telegram.ui.Components.NestedSizeNotifierLayout, androidx.core.view.NestedScrollingParent3
        public void onNestedScroll(View r1, int r2, int r3, int r4, int r5, int r6, int[] r7) {
        L10:
            th = move-exception;
            FileLog.e(th);
            AndroidUtilities.runOnUIThread(new TONIntroActivity$NestedFrameLayout$$ExternalSyntheticLambda0(this));
            return;
        L3:
            if (r1 == TONIntroActivity.access$200(this.this$0)) goto L5;
            return;
        L5:
            if (TONIntroActivity.m20601$$Nest$fgettransactionsLayout(this.this$0).isAttachedToWindow() == false) goto L17;
            RecyclerListView r12 = TONIntroActivity.m20601$$Nest$fgettransactionsLayout(this.this$0).getCurrentListView();     // Catch: Throwable -> L10
            int r22 = ((View) TONIntroActivity.m20601$$Nest$fgettransactionsLayout(this.this$0).getParent()).getBottom();     // Catch: Throwable -> L10
            if ((TONIntroActivity.access$300(this.this$0).getHeight() - r22) < 0) goto L18;
            r7[1] = r5;     // Catch: Throwable -> L10
            r12.scrollBy(0, r5);     // Catch: Throwable -> L10
            return;
        L18:
            return;
        }

        @Override // org.telegram.ui.Components.NestedSizeNotifierLayout, androidx.core.view.NestedScrollingParent2
        public void onNestedScrollAccepted(View r1, View r2, int r3, int r4) {
            this.nestedScrollingParentHelper.onNestedScrollAccepted(r1, r2, r3);
        }

        @Override // org.telegram.ui.Components.NestedSizeNotifierLayout, androidx.core.view.NestedScrollingParent2
        public boolean onStartNestedScroll(View r1, View r2, int r3, int r4) {
            if (r3 != 2) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.NestedSizeNotifierLayout, android.view.ViewGroup, android.view.ViewParent
        public void onStopNestedScroll(View r1) {
        }

        @Override // org.telegram.ui.Components.NestedSizeNotifierLayout, androidx.core.view.NestedScrollingParent2
        public void onStopNestedScroll(View r1, int r2) {
            this.nestedScrollingParentHelper.onStopNestedScroll(r1);
        }
    }

    public static class StarsNeededSheet extends BottomSheetWithRecyclerListView implements NotificationCenter.NotificationCenterDelegate {
        private UniversalAdapter adapter;
        private final FrameLayout footerView;
        private final HeaderView headerView;
        private final AmountUtils$Amount requiredAmount;
        private final ButtonWithCounterView topUpButton;
        private Runnable whenPurchased;

        public static class HeaderView extends LinearLayout {
            public final GLIconTextureView iconView;
            public final StarParticlesView particlesView;
            public final TextView subtitleView;
            public final TextView titleView;
            private final FrameLayout topView;

            public HeaderView(Context r19, int r20, Theme.ResourcesProvider r21) {
                super(r19);
                setOrientation(1);
                FrameLayout r4 = new FrameLayout(r19);
                this.topView = r4;
                r4.setClipChildren(false);
                r4.setClipToPadding(false);
                StarParticlesView r6 = TONIntroActivity.makeParticlesView(r19, 70, 0);
                this.particlesView = r6;
                r4.addView(r6, LayoutHelper.createFrame(-1, -1.0f));
                GLIconTextureView r7 = new GLIconTextureView(r19, 1, 4);
                this.iconView = r7;
                GLIconRenderer r9 = r7.mRenderer;
                r9.colorKey1 = Theme.key_starsGradient1;
                r9.colorKey2 = Theme.key_starsGradient2;
                r9.updateColors();
                r7.setStarParticlesView(r6);
                r4.addView(r7, LayoutHelper.createFrame(170, 170.0f, 17, 0.0f, 32.0f, 0.0f, 24.0f));
                r7.setPaused(false);
                addView(r4, LayoutHelper.createFrame(-1, 180.0f));
                TextView r42 = new TextView(r19);
                this.titleView = r42;
                r42.setTextSize(1, 20.0f);
                r42.setTypeface(AndroidUtilities.bold());
                int r5 = Theme.key_dialogTextBlack;
                r42.setTextColor(Theme.getColor(r5, r21));
                r42.setGravity(17);
                addView(r42, LayoutHelper.createLinear(-2, -2, 1, 0, 2, 0, 0));
                TextView r43 = new TextView(r19);
                this.subtitleView = r43;
                r43.setTextSize(1, 14.0f);
                r43.setTextColor(Theme.getColor(r5, r21));
                r43.setGravity(17);
                addView(r43, LayoutHelper.createLinear(-2, -2, 1, 0, 9, 0, 18));
            }
        }

        public static /* synthetic */ void $r8$lambda$LoNCA_zwTduTpaF_fMxKXtoxrlo(StarsNeededSheet r0, View r1) {
            r0.lambda$new$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$bQTPZ1AY4PunU0IqOkcZFzCOEJg(StarsNeededSheet r0, View r1) {
            r0.lambda$new$2(r1);
        }

        /* renamed from: $r8$lambda$veGb-FFGfyv2pfiGpLhJpDmeP0Y, reason: not valid java name */
        public static /* synthetic */ void m20604$r8$lambda$veGbFFGfyv2pfiGpLhJpDmeP0Y(StarsNeededSheet r0, View r1, int r2) {
            r0.lambda$new$0(r1, r2);
        }

        public StarsNeededSheet(Context r9, Theme.ResourcesProvider r10, AmountUtils$Amount r11, boolean r12, Runnable r13) {
            super(r9, null, false, false, false, r10);
            this.topPadding = 0.2f;
            this.whenPurchased = r13;
            fixNavigationBar();
            RecyclerListView r8 = this.recyclerListView;
            int r92 = this.backgroundPaddingLeft;
            r8.setPadding(r92, 0, r92, 0);
            this.recyclerListView.setOnItemClickListener(new TONIntroActivity$StarsNeededSheet$$ExternalSyntheticLambda1(this));
            DefaultItemAnimator r82 = new DefaultItemAnimator();
            r82.setSupportsChangeAnimations(false);
            r82.setDelayAnimations(false);
            r82.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            r82.setDurations(350);
            this.recyclerListView.setItemAnimator(r82);
            setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, r10));
            this.requiredAmount = r11;
            HeaderView r83 = new HeaderView(r9, this.currentAccount, r10);
            this.headerView = r83;
            AmountUtils$Amount r93 = StarsController.getTonInstance(this.currentAccount).getBalanceAmount();
            r83.titleView.setText(LocaleController.formatString(R.string.TonNeededTitle, new Object[]{AmountUtils$Amount.fromNano(r11.asNano() - r93.asNano(), AmountUtils$Currency.TON).asFormatString()}));
            r83.subtitleView.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.FragmentAddFunds)));
            TextView r94 = r83.subtitleView;
            r94.setMaxWidth(HintView2.cutInFancyHalf(r94.getText(), r83.subtitleView.getPaint()));
            this.actionBar.setTitle(getTitle());
            FrameLayout r84 = new FrameLayout(r9);
            this.footerView = r84;
            ButtonWithCounterView r95 = new ButtonWithCounterView(getContext(), getResourcesProvider());
            this.topUpButton = r95;
            r84.addView(r95, LayoutHelper.createLinear(-1, 48, 17, 20, 10, 20, 20));
            if (r12 == false) goto L5;
        L8:
            r95.setText(LocaleController.getString(R.string.TopUpViaFragment), false);
            r95.setOnClickListener(new TONIntroActivity$StarsNeededSheet$$ExternalSyntheticLambda2(this));
        L9:
            UniversalAdapter r85 = this.adapter;
            if (r85 == null) goto L13;
            r85.update(false);
            return;
        L13:
            return;
        L5:
            if (TONIntroActivity.allowTopUp() == true) goto L8;
            r95.setText(LocaleController.getString(R.string.Close), false);
            r95.setOnClickListener(new TONIntroActivity$StarsNeededSheet$$ExternalSyntheticLambda3(this));
            goto L9
        }

        private /* synthetic */ void lambda$new$0(View r1, int r2) {
            UniversalAdapter r12 = this.adapter;
            if (r12 == null) goto L10;
            UItem r13 = r12.getItem(r2 - 1);
            if (r13 != null) goto L8;
            return;
        L8:
            onItemClick(r13, this.adapter);
            return;
        }

        private /* synthetic */ void lambda$new$1(View r1) {
            Browser.openUrlInSystemBrowser(getContext(), LocaleController.getString(R.string.TopUpViaFragmentLink));
        }

        private /* synthetic */ void lambda$new$2(View r1) {
            dismiss();
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
            UniversalAdapter r0 = new UniversalAdapter(this.recyclerListView, getContext(), this.currentAccount, 0, true, new TONIntroActivity$StarsNeededSheet$$ExternalSyntheticLambda0(this), this.resourcesProvider);
            this.adapter = r0;
            return r0;
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r5, int r6, Object... r7) {
            if (r5 != NotificationCenter.starOptionsLoaded) goto L5;
        L6:
            UniversalAdapter r52 = this.adapter;
            if (r52 == null) goto L9;
            r52.update(true);
        L9:
            AmountUtils$Amount r53 = StarsController.getTonInstance(this.currentAccount).getBalanceAmount();
            this.headerView.titleView.setText(LocaleController.formatString(R.string.TonNeededTitle, new Object[]{AmountUtils$Amount.fromNano(this.requiredAmount.asNano() - r53.asNano(), AmountUtils$Currency.TON).asFormatString()}));
            ActionBar r62 = this.actionBar;
            if (r62 == null) goto L13;
            r62.setTitle(getTitle());
        L13:
            if (r53.asNano() < this.requiredAmount.asNano()) goto L19;
            Runnable r54 = this.whenPurchased;
            if (r54 == null) goto L20;
            r54.run();
            this.whenPurchased = null;
            dismiss();
            return;
        L20:
            return;
        L19:
            return;
        L5:
            if (r5 == NotificationCenter.starBalanceUpdated) goto L6;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
        public void dismiss() {
            super.dismiss();
            HeaderView r1 = this.headerView;
            if (r1 == null) goto L6;
            r1.iconView.setPaused(true);
            return;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public void dismissInternal() {
            super.dismissInternal();
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starOptionsLoaded);
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starBalanceUpdated);
        }

        public void fillItems(ArrayList<UItem> r1, UniversalAdapter r2) {
            r1.add(UItem.asCustom(this.headerView));
            r1.add(UItem.asCustom(this.footerView));
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public CharSequence getTitle() {
            HeaderView r0 = this.headerView;
            if (r0 != null) goto L7;
            return null;
        L7:
            return r0.titleView.getText();
        }

        public void onItemClick(UItem r1, UniversalAdapter r2) {
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
        public void show() {
            if (StarsController.getTonInstance(this.currentAccount).getBalanceAmount().asNano() < this.requiredAmount.asNano()) goto L8;
            Runnable r0 = this.whenPurchased;
            if (r0 == null) goto L17;
            r0.run();
            this.whenPurchased = null;
            return;
        L17:
            return;
        L8:
            BaseFragment r02 = LaunchActivity.getLastFragment();
            if ((r02 instanceof ChatActivity) == false) goto L15;
            ChatActivity r03 = (ChatActivity) r02;
            if (r03.isKeyboardVisible() == false) goto L15;
            if (r03.getChatActivityEnterView() == null) goto L15;
            r03.getChatActivityEnterView().closeKeyboard();
        L15:
            super.show();
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starOptionsLoaded);
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starBalanceUpdated);
        }
    }

    /* renamed from: $r8$lambda$2-03enKkAWujzBaM0TIV3q_8LR8, reason: not valid java name */
    public static /* synthetic */ void m20599$r8$lambda$203enKkAWujzBaM0TIV3q_8LR8(TONIntroActivity r0, boolean r1) {
        r0.lambda$updateButtonsLayouts$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$4IzRi36QGwjfZoziUvTxXR61B6g(TONIntroActivity r0, View r1) {
        r0.lambda$createView$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$71UNYhXX4_wrgqm9xucR7OfTpGg(TONIntroActivity r0, View r1, int r2) {
        r0.lambda$createView$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$IqS4jlsGQd31Kq7SqWJ5qqB1qS8(TONIntroActivity r0, boolean r1) {
        r0.lambda$updateButtonsLayouts$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$hlkrSL3ZEXaEzTHQaOvsRePo0MA(Context r1) {
        new ExplainStarsSheet(r1).show();
    }

    public static /* synthetic */ void $r8$lambda$tB2zhF0k5FOPoPniSE6vNmLo0Qo(TONIntroActivity r0, View r1) {
        r0.lambda$createView$4(r1);
    }

    /* renamed from: $r8$lambda$yYpA-pn9Fi5G1-2-YPrcU6nkCnM, reason: not valid java name */
    public static /* synthetic */ void m20600$r8$lambda$yYpApn9Fi5G12YPrcU6nkCnM(TONIntroActivity r0, View r1) {
        r0.lambda$createView$3(r1);
    }

    /* renamed from: -$$Nest$fgettransactionsLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StarsIntroActivity.StarsTransactionsLayout m20601$$Nest$fgettransactionsLayout(TONIntroActivity r0) {
        return r0.transactionsLayout;
    }

    /* renamed from: -$$Nest$fgettwoButtons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20602$$Nest$fgettwoButtons(TONIntroActivity r0) {
        return r0.twoButtons;
    }

    public TONIntroActivity() {
        this.expanded = false;
        this.BUTTON_EXPAND = -1;
        this.BUTTON_GIFT = -2;
        this.BUTTON_SUBSCRIPTIONS_EXPAND = -3;
        this.BUTTON_AFFILIATE = -4;
        this.allowTopUp = allowTopUp();
        setWhiteBackground(true);
    }

    public static /* synthetic */ ActionBar access$000(TONIntroActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$100(TONIntroActivity r0) {
        return r0.yOffset;
    }

    public static /* synthetic */ RecyclerListView access$200(TONIntroActivity r0) {
        return r0.listView;
    }

    public static /* synthetic */ RecyclerListView access$300(TONIntroActivity r0) {
        return r0.listView;
    }

    public static /* synthetic */ RecyclerListView access$400(TONIntroActivity r0) {
        return r0.listView;
    }

    public static /* synthetic */ ActionBar access$500(TONIntroActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ RecyclerListView access$600(TONIntroActivity r0) {
        return r0.listView;
    }

    public static /* synthetic */ RecyclerListView access$700(TONIntroActivity r0) {
        return r0.listView;
    }

    public static /* synthetic */ RecyclerListView access$800(TONIntroActivity r0) {
        return r0.listView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$900(TONIntroActivity r0) {
        return r0.resourceProvider;
    }

    public static boolean allowTopUp() {
        return BuildVars.isBetaApp();
    }

    private /* synthetic */ void lambda$createView$1(View r1, int r2) {
        UniversalAdapter r12 = this.adapter;
        if (r12 == null) goto L10;
        UItem r13 = r12.getItem(r2);
        if (r13 != null) goto L8;
        return;
    L8:
        onItemClick(r13, r2);
        return;
    }

    private /* synthetic */ void lambda$createView$2(View r1) {
        Browser.openUrlInSystemBrowser(getContext(), LocaleController.getString(R.string.TopUpViaFragmentLink));
    }

    private /* synthetic */ void lambda$createView$3(View r1) {
        Browser.openUrlInSystemBrowser(getContext(), LocaleController.getString(R.string.TopUpViaFragmentLink));
    }

    private /* synthetic */ void lambda$createView$4(View r4) {
        presentFragment(new BotStarsActivity(1, getUserConfig().getClientUserId()));
    }

    private /* synthetic */ void lambda$updateButtonsLayouts$5(boolean r1) {
        if (r1 == false) goto L5;
        this.oneButtonsLayout.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$updateButtonsLayouts$6(boolean r1) {
        if (r1 == true) goto L5;
        this.twoButtonsLayout.setVisibility(8);
        return;
    }

    public static StarParticlesView makeParticlesView(Context r1, int r2, int r3) {
        return new AnonymousClass4(r1, r2, r3);
    }

    private void updateBalance() {
        StarsController r0 = StarsController.getTonInstance(this.currentAccount);
        double r1 = getMessagesController().config.tonUsdRate.get();
        TL_stars.StarsAmount r02 = r0.getBalance();
        SpannableStringBuilder r3 = new SpannableStringBuilder();
        r3.append(this.starBalanceIcon);
        r3.append(StarsIntroActivity.formatStarsAmount(r02, 0.66f, ' '));
        this.starBalanceTextView.setText(r3);
        int r03 = (int) (((r02.amount / 1.0E9d) * r1) * 100.0d);
        AnimatedTextView r12 = this.starBalanceTitleView;
        if (r03 <= 0) goto L5;
        r12.setText("≈" + BillingController.getInstance().formatCurrency(r03, "USD"));
    L6:
        TLRPC.TL_payments_starsRevenueStats r04 = BotStarsController.getInstance(this.currentAccount).getTONRevenueStats(getUserConfig().getClientUserId(), true);
        if (r04 == null) goto L13;
        TLRPC.TL_starsRevenueStatus r05 = r04.status;
        if (r05 == null) goto L13;
        if (r05.overall_revenue.positive() == false) goto L13;
        boolean r06 = true;
    L14:
        updateButtonsLayouts(r06, true);
        return;
    L13:
        r06 = false;
        goto L14
    L5:
        r12.setText(LocaleController.getString(R.string.YourTonBalance));
        goto L6
    }

    private void updateButtonsLayouts(boolean r5, boolean r6) {
        if (this.twoButtons != r5) goto L5;
        return;
    L5:
        this.twoButtons = r5;
        FrameLayout r0 = this.oneButtonsLayout;
        float r1 = 1.0f;
        int r3 = 0;
        if (r6 == false) goto L16;
        r0.setVisibility(0);
        this.twoButtonsLayout.setVisibility(0);
        ViewPropertyAnimator r62 = this.oneButtonsLayout.animate();
        if (r5 == false) goto L10;
        float r02 = 0.0f;
    L11:
        r62.alpha(r02).withEndAction(new TONIntroActivity$$ExternalSyntheticLambda5(this, r5)).start();
        ViewPropertyAnimator r63 = this.twoButtonsLayout.animate();
        if (r5 == true) goto L14;
        r1 = 0.0f;
    L14:
        r63.alpha(r1).withEndAction(new TONIntroActivity$$ExternalSyntheticLambda6(this, r5)).start();
        return;
    L10:
        r02 = 1.0f;
        goto L11
    L16:
        r0.animate().cancel();
        this.twoButtonsLayout.animate().cancel();
        LinearLayout r64 = this.twoButtonsLayout;
        if (r5 == false) goto L19;
        float r03 = 1.0f;
    L20:
        r64.setAlpha(r03);
        FrameLayout r65 = this.oneButtonsLayout;
        if (r5 == false) goto L23;
        r1 = 0.0f;
    L23:
        r65.setAlpha(r1);
        LinearLayout r66 = this.twoButtonsLayout;
        if (r5 == false) goto L26;
        int r12 = 0;
    L27:
        r66.setVisibility(r12);
        FrameLayout r4 = this.oneButtonsLayout;
        if (r5 == false) goto L30;
        r3 = 8;
    L30:
        r4.setVisibility(r3);
        return;
    L26:
        r12 = 8;
        goto L27
    L19:
        r03 = 0.0f;
        goto L20
    }

    public boolean attachedTransactionsLayout() {
        StarsIntroActivity.StarsTransactionsLayout r0 = this.transactionsLayout;
        if (r0 != null) goto L5;
    L11:
        return false;
    L5:
        if ((r0.getParent() instanceof View) == false) goto L11;
        int r02 = ((View) this.transactionsLayout.getParent()).getBottom();
        if ((this.listView.getHeight() - r02) < 0) goto L11;
        return true;
    }

    @Override // org.telegram.ui.GradientHeaderActivity
    public RecyclerView.Adapter<?> createAdapter() {
        AnonymousClass5 r0 = new AnonymousClass5(this, this.listView, getContext(), this.currentAccount, this.classGuid, true, new TONIntroActivity$$ExternalSyntheticLambda7(this), getResourceProvider());
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.ui.GradientHeaderActivity
    public GradientHeaderActivity.ContentView createContentView() {
        return new NestedFrameLayout(this, getContext());
    }

    @Override // org.telegram.ui.GradientHeaderActivity
    public StarParticlesView createParticlesView() {
        return makeParticlesView(getContext(), 75, 1);
    }

    @Override // org.telegram.ui.GradientHeaderActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r19) {
        this.useFillLastLayoutManager = false;
        this.particlesViewHeight = AndroidUtilities.dp(238.0f);
        this.transactionsLayout = new StarsIntroActivity.StarsTransactionsLayout(r19, this.currentAccount, true, 0, getClassGuid(), getResourceProvider());
        this.emptyLayout = new AnonymousClass1(this, r19);
        super.createView(r19);
        INavigationLayout r1 = this.parentLayout;
        if (r1 != null) goto L5;
    L7:
        FrameLayout r12 = new FrameLayout(r19);
        this.aboveTitleView = r12;
        r12.setClickable(true);
        GLIconTextureView r13 = new GLIconTextureView(r19, 1, 4);
        this.iconTextureView = r13;
        GLIconRenderer r14 = r13.mRenderer;
        r14.colorKey1 = Theme.key_starsGradient1;
        r14.colorKey2 = Theme.key_starsGradient2;
        r14.updateColors();
        this.iconTextureView.setStarParticlesView(this.particlesView);
        this.aboveTitleView.addView(this.iconTextureView, LayoutHelper.createFrame(170, 170.0f, 17, 0.0f, 32.0f, 0.0f, 24.0f));
        configureHeader(LocaleController.getString(R.string.TONBalanceTitle), AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.TONBalanceText), new TONIntroActivity$$ExternalSyntheticLambda0(r19)), true), this.aboveTitleView, null);
        this.listView.setOverScrollMode(2);
        DefaultItemAnimator r15 = new DefaultItemAnimator();
        r15.setSupportsChangeAnimations(false);
        r15.setDelayAnimations(false);
        r15.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r15.setDurations(350);
        this.listView.setItemAnimator(r15);
        this.listView.setOnItemClickListener(new TONIntroActivity$$ExternalSyntheticLambda1(this));
        FireworksOverlay r16 = new FireworksOverlay(getContext());
        this.fireworksOverlay = r16;
        this.contentView.addView(r16, LayoutHelper.createFrame(-1, -1.0f));
        StarsController.getTonInstance(this.currentAccount);
        LinearLayout r17 = new LinearLayout(getContext());
        this.balanceLayout = r17;
        r17.setOrientation(1);
        this.balanceLayout.setPadding(0, AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(10.0f));
        AnimatedTextView r18 = new AnimatedTextView(getContext(), false, true, false);
        this.starBalanceTextView = r18;
        r18.setTypeface(AndroidUtilities.bold());
        this.starBalanceTextView.setTextSize(AndroidUtilities.dp(32.0f));
        this.starBalanceTextView.setGravity(17);
        this.starBalanceTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourceProvider));
        this.starBalanceIcon = new SpannableStringBuilder("S");
        ColoredImageSpan r110 = new ColoredImageSpan(R.drawable.mini_gram_72);
        r110.setOverrideColor(-13397548);
        r110.setScale(0.5f, 0.5f);
        float r7 = 0.0f;
        r110.translate(-AndroidUtilities.dp(3.0f), 0.0f);
        this.starBalanceIcon.setSpan(r110, 0, 1, 33);
        this.balanceLayout.addView(this.starBalanceTextView, LayoutHelper.createFrame(-1, 40.0f, 17, 24.0f, 0.0f, 24.0f, 0.0f));
        AnimatedTextView r111 = new AnimatedTextView(getContext());
        this.starBalanceTitleView = r111;
        r111.setTextSize(AndroidUtilities.dp(14.0f));
        this.starBalanceTitleView.setGravity(17);
        this.starBalanceTitleView.setText(LocaleController.getString(R.string.YourTonBalance));
        this.starBalanceTitleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, this.resourceProvider));
        this.balanceLayout.addView(this.starBalanceTitleView, LayoutHelper.createFrame(-1, 20.0f, 17, 24.0f, 0.0f, 24.0f, 8.0f));
        FrameLayout r112 = new FrameLayout(getContext());
        AnonymousClass2 r4 = new AnonymousClass2(this, getContext());
        this.oneButtonsLayout = r4;
        r112.addView(r4);
        if (this.allowTopUp == false) goto L10;
        ButtonWithCounterView r42 = new ButtonWithCounterView(getContext(), this.resourceProvider).setRound();
        this.buyButton = r42;
        r42.setRound();
        this.buyButton.setText(LocaleController.getString(R.string.TopUpViaFragment), false);
        this.buyButton.setOnClickListener(new TONIntroActivity$$ExternalSyntheticLambda2(this));
        this.oneButtonsLayout.addView(this.buyButton, LayoutHelper.createFrame(-1, 48, 119));
    L10:
        AnonymousClass3 r43 = new AnonymousClass3(this, getContext());
        this.twoButtonsLayout = r43;
        r112.addView(r43);
        this.topUpButton = new ButtonWithCounterView(getContext(), this.resourceProvider).setRound();
        SpannableStringBuilder r44 = new SpannableStringBuilder("x  ");
        r44.setSpan(new ColoredImageSpan(R.drawable.mini_topup, 2), 0, 1, 33);
        r44.append(LocaleController.getString(R.string.TonTopUp));
        this.topUpButton.setText(r44, false);
        this.topUpButton.setOnClickListener(new TONIntroActivity$$ExternalSyntheticLambda3(this));
        if (this.allowTopUp == false) goto L13;
        this.twoButtonsLayout.addView(this.topUpButton, LayoutHelper.createLinear(-1, 48, 17.0f, 1, 0, 0, 8, 0));
    L13:
        this.withdrawButton = new ButtonWithCounterView(getContext(), this.resourceProvider).setRound();
        SpannableStringBuilder r45 = new SpannableStringBuilder("x  ");
        r45.setSpan(new ColoredImageSpan(R.drawable.mini_stats, 2), 0, 1, 33);
        r45.append(LocaleController.getString(R.string.TonStats));
        this.withdrawButton.setText(r45, false);
        this.withdrawButton.setOnClickListener(new TONIntroActivity$$ExternalSyntheticLambda4(this));
        this.twoButtonsLayout.addView(this.withdrawButton, LayoutHelper.createLinear(-1, 48, 17.0f, 1, 0, 0, 0, 0));
        this.balanceLayout.addView(r112, LayoutHelper.createFrame(-1, 48.0f, 17, 20.0f, 6.0f, 20.0f, 4.0f));
        this.oneButtonsLayout.animate().cancel();
        this.twoButtonsLayout.animate().cancel();
        LinearLayout r113 = this.twoButtonsLayout;
        if (this.twoButtons == false) goto L16;
        float r2 = 1.0f;
    L17:
        r113.setAlpha(r2);
        FrameLayout r114 = this.oneButtonsLayout;
        if (this.twoButtons == true) goto L21;
        r7 = 1.0f;
    L21:
        r114.setAlpha(r7);
        LinearLayout r115 = this.twoButtonsLayout;
        int r3 = 8;
        if (this.twoButtons == false) goto L24;
        int r22 = 0;
    L25:
        r115.setVisibility(r22);
        FrameLayout r116 = this.oneButtonsLayout;
        if (this.twoButtons == true) goto L29;
        r3 = 0;
    L29:
        r116.setVisibility(r3);
        updateBalance();
        UniversalAdapter r117 = this.adapter;
        if (r117 == null) goto L33;
        r117.update(false);
    L33:
        return this.fragmentView;
    L24:
        r22 = 8;
        goto L25
    L16:
        r2 = 0.0f;
        goto L17
    L5:
        if (r1.isRightLayout() == false) goto L7;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_close);
        goto L7
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.starOptionsLoaded) goto L15;
        saveScrollPosition();
        UniversalAdapter r32 = this.adapter;
        if (r32 == null) goto L8;
        r32.update(true);
    L8:
        if (this.savedScrollPosition == 0) goto L10;
    L12:
        applyScrolledPosition();
        return;
    L10:
        if (this.savedScrollOffset >= 0) goto L12;
        this.savedScrollOffset = 0;
        goto L12
    L15:
        if (r3 != NotificationCenter.starTransactionsLoaded) goto L29;
        StarsController r33 = StarsController.getTonInstance(this.currentAccount);
        if (this.hadTransactions == r33.hasTransactions()) goto L44;
        this.hadTransactions = r33.hasTransactions();
        saveScrollPosition();
        UniversalAdapter r34 = this.adapter;
        if (r34 == null) goto L22;
        r34.update(true);
    L22:
        if (this.savedScrollPosition == 0) goto L24;
    L26:
        applyScrolledPosition();
        return;
    L24:
        if (this.savedScrollOffset >= 0) goto L26;
        this.savedScrollOffset = 0;
        goto L26
    L44:
        return;
    L29:
        if (r3 != NotificationCenter.starSubscriptionsLoaded) goto L35;
        UniversalAdapter r2 = this.adapter;
        if (r2 == null) goto L45;
        r2.update(true);
        return;
    L45:
        return;
    L35:
        if (r3 != NotificationCenter.starBalanceUpdated) goto L39;
        updateBalance();
        return;
    L39:
        if (r3 == NotificationCenter.botStarsUpdated) goto L41;
        return;
    L41:
        if (getUserConfig().getClientUserId() != ((Long) r5[0]).longValue()) goto L47;
        updateBalance();
        return;
    }

    @Override // org.telegram.ui.GradientHeaderActivity
    public boolean drawActionBarShadow() {
        return !attachedTransactionsLayout();
    }

    public void fillItems(ArrayList<UItem> r2, UniversalAdapter r3) {
        if (getContext() != null) goto L5;
        return;
    L5:
        StarsController r32 = StarsController.getTonInstance(this.currentAccount);
        r2.add(UItem.asFullyCustom(getHeader(getContext())));
        r2.add(UItem.asCustom(this.balanceLayout));
        if (this.allowTopUp == false) goto L8;
        r2.add(UItem.asShadow(LocaleController.getString(R.string.TopUpViaFragmentInfo)));
    L8:
        boolean r33 = r32.hasTransactions();
        this.hadTransactions = r33;
        if (r33 == true) goto L11;
        r2.add(UItem.asCustomShadow(this.emptyLayout));
        return;
    L11:
        if (this.allowTopUp == true) goto L13;
        r2.add(UItem.asShadow(null));
    L13:
        r2.add(UItem.asFullscreenCustom(this.transactionsLayout, ((ActionBar.getCurrentActionBarHeight() + AndroidUtilities.statusBarHeight) + AndroidUtilities.dp(24.0f)) + AndroidUtilities.navigationBarHeight));
    }

    @Override // org.telegram.ui.GradientHeaderActivity
    public View getHeader(Context r1) {
        return super.getHeader(r1);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public int getNavigationBarColor() {
        return Theme.getColor(Theme.key_dialogBackgroundGray);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starOptionsLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starBalanceUpdated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starTransactionsLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starSubscriptionsLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.botStarsUpdated);
        StarsController.getTonInstance(this.currentAccount).invalidateTransactions(true);
        StarsController.getTonInstance(this.currentAccount).invalidateSubscriptions(true);
        StarsController.getTonInstance(this.currentAccount).getOptions();
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starOptionsLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starBalanceUpdated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starTransactionsLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starSubscriptionsLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.botStarsUpdated);
    }

    public void onItemClick(UItem r3, int r4) {
        int r32 = r3.id;
        if (r32 != (-1)) goto L7;
        this.expanded = !this.expanded;
        this.adapter.update(true);
        return;
    L7:
        if (r32 != (-2)) goto L11;
        StarsController.getTonInstance(this.currentAccount).getGiftOptions();
        UserSelectorBottomSheet.open(1, 0, BirthdayController.getInstance(this.currentAccount).getState());
        return;
    L11:
        if (r32 != (-3)) goto L15;
        StarsController.getTonInstance(this.currentAccount).loadSubscriptions();
        this.adapter.update(true);
        return;
    L15:
        if (r32 == (-4)) goto L17;
        return;
    L17:
        if (MessagesController.getInstance(this.currentAccount).isFrozen() == false) goto L20;
        AccountFrozenAlert.show(this.currentAccount);
        return;
    L20:
        presentFragment(new ChannelAffiliateProgramsFragment(getUserConfig().getClientUserId()));
    }

    @Override // org.telegram.ui.GradientHeaderActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        GLIconTextureView r0 = this.iconTextureView;
        if (r0 == null) goto L6;
        r0.setPaused(true);
        this.iconTextureView.setDialogVisible(true);
        return;
    }

    @Override // org.telegram.ui.GradientHeaderActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        GLIconTextureView r0 = this.iconTextureView;
        if (r0 == null) goto L6;
        r0.setPaused(false);
        this.iconTextureView.setDialogVisible(false);
        return;
    }
}
