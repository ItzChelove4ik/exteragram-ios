package org.telegram.ui.Stars;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.RelativeSizeSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.timepicker.TimeModel;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.tgnet.tl.TL_stats;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChannelMonetizationLayout;
import org.telegram.ui.Charts.data.ChartData;
import org.telegram.ui.ChatEditActivity;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ChatAvatarContainer;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.OutlineTextContainerView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.StatisticActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.TwoStepVerificationActivity;
import org.telegram.ui.TwoStepVerificationSetupActivity;
import org.telegram.ui.bots.AffiliateProgramFragment;
import org.telegram.ui.bots.ChannelAffiliateProgramsFragment;

/* loaded from: classes7.dex */
public class BotStarsActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private final int BALANCE;
    private final int BUTTON_AFFILIATE;
    private ButtonWithCounterView adsButton;
    private final ChannelMonetizationLayout.ProceedOverview availableValue;
    private ChatAvatarContainer avatarContainer;
    private int balanceBlockedUntil;
    private ButtonWithCounterView balanceButton;
    private LinearLayout balanceButtonsLayout;
    private EditTextBoldCursor balanceEditText;
    private boolean balanceEditTextAll;
    private OutlineTextContainerView balanceEditTextContainer;
    private boolean balanceEditTextIgnore;
    private long balanceEditTextValue;
    private CharSequence balanceInfo;
    private LinearLayout balanceLayout;
    private AnimatedTextView balanceSubtitle;
    private AnimatedTextView balanceTitle;
    private RelativeSizeSpan balanceTitleSizeSpan;
    public final long bot_id;
    private DecimalFormat formatter;
    private StatisticActivity.ChartViewData impressionsChart;
    private TLRPC.TL_payments_starsRevenueStats lastStats;
    private TLRPC.TL_starsRevenueStatus lastStatsStatus;
    private UniversalRecyclerView listView;
    private SpannableStringBuilder lock;
    private boolean proceedsAvailable;
    private CharSequence proceedsInfo;
    private double rate;
    private StatisticActivity.ChartViewData revenueChart;
    private StatisticActivity.ChartViewData revenueChartData;
    public final boolean self;
    private Runnable setBalanceButtonText;
    private int shakeDp;
    private ColoredImageSpan[] starRef;
    private int stats_dc;
    private CharSequence titleInfo;
    private final ChannelMonetizationLayout.ProceedOverview tonAvailableValue;
    private ButtonWithCounterView tonBalanceButton;
    private LinearLayout tonBalanceLayout;
    private AnimatedTextView tonBalanceSubtitle;
    private AnimatedTextView tonBalanceTitle;
    private RelativeSizeSpan tonBalanceTitleSizeSpan;
    private final ChannelMonetizationLayout.ProceedOverview tonLastWithdrawalValue;
    private final ChannelMonetizationLayout.ProceedOverview tonLifetimeValue;
    private final ArrayList<TL_stars.StarsTransaction> tonTransactions;
    private boolean tonTransactionsEndReached;
    private String tonTransactionsLastOffset;
    private boolean tonTransactionsLoading;
    private final ChannelMonetizationLayout.ProceedOverview totalProceedsValue;
    private final ChannelMonetizationLayout.ProceedOverview totalValue;
    private StarsIntroActivity.StarsTransactionsLayout transactionsLayout;
    public final int type;
    private final CharSequence withdrawInfo;
    private Bulletin withdrawalBulletin;

    /* renamed from: org.telegram.ui.Stars.BotStarsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ BotStarsActivity this$0;

        public AnonymousClass1(BotStarsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stars.BotStarsActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends LinearLayout {
        final /* synthetic */ BotStarsActivity this$0;

        public AnonymousClass2(BotStarsActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }
    }

    /* renamed from: org.telegram.ui.Stars.BotStarsActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends OutlineTextContainerView {
        final /* synthetic */ BotStarsActivity this$0;

        public AnonymousClass3(BotStarsActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (BotStarsActivity.m18867$$Nest$fgetbalanceEditText(this.this$0) == null) goto L13;
            if (BotStarsActivity.m18867$$Nest$fgetbalanceEditText(this.this$0).isFocusable() == true) goto L13;
            BotStarsActivity.m18867$$Nest$fgetbalanceEditText(this.this$0).setFocusable(true);
            BotStarsActivity.m18867$$Nest$fgetbalanceEditText(this.this$0).setFocusableInTouchMode(true);
            int r0 = BotStarsActivity.m18870$$Nest$fgetlistView(this.this$0).findPositionByItemId(1);
            if (r0 >= 0) goto L9;
        L11:
            BotStarsActivity.m18867$$Nest$fgetbalanceEditText(this.this$0).requestFocus();
            goto L13
        L9:
            if (r0 >= BotStarsActivity.m18870$$Nest$fgetlistView(this.this$0).adapter.getItemCount()) goto L11;
            BotStarsActivity.m18870$$Nest$fgetlistView(this.this$0).stopScroll();
            BotStarsActivity.m18870$$Nest$fgetlistView(this.this$0).smoothScrollToPosition(r0);
        L13:
            return super.dispatchTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.Stars.BotStarsActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends EditTextBoldCursor {
        final /* synthetic */ BotStarsActivity this$0;

        public AnonymousClass4(BotStarsActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            AndroidUtilities.hideKeyboard(this);
        }
    }

    /* renamed from: org.telegram.ui.Stars.BotStarsActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements TextWatcher {
        final /* synthetic */ BotStarsActivity this$0;

        public AnonymousClass5(BotStarsActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r7) {
            long r0 = BotStarsController.getInstance(BotStarsActivity.access$000(this.this$0)).getAvailableBalance(this.this$0.bot_id);
            BotStarsActivity r2 = this.this$0;
            if (TextUtils.isEmpty(r7) == false) goto L5;
            long r3 = 0;
        L6:
            BotStarsActivity.m18875$$Nest$fputbalanceEditTextValue(r2, r3);
            boolean r22 = true;
            if (BotStarsActivity.m18869$$Nest$fgetbalanceEditTextValue(this.this$0) <= r0) goto L9;
            BotStarsActivity.m18875$$Nest$fputbalanceEditTextValue(this.this$0, r0);
            BotStarsActivity.m18874$$Nest$fputbalanceEditTextIgnore(this.this$0, true);
            BotStarsActivity.m18867$$Nest$fgetbalanceEditText(this.this$0).setText(Long.toString(BotStarsActivity.m18869$$Nest$fgetbalanceEditTextValue(this.this$0)));
            BotStarsActivity.m18867$$Nest$fgetbalanceEditText(this.this$0).setSelection(BotStarsActivity.m18867$$Nest$fgetbalanceEditText(this.this$0).getText().length());
            BotStarsActivity.m18874$$Nest$fputbalanceEditTextIgnore(this.this$0, false);
        L9:
            BotStarsActivity r72 = this.this$0;
            if (BotStarsActivity.m18869$$Nest$fgetbalanceEditTextValue(r72) == r0) goto L13;
            r22 = false;
        L13:
            BotStarsActivity.m18873$$Nest$fputbalanceEditTextAll(r72, r22);
            AndroidUtilities.cancelRunOnUIThread(BotStarsActivity.m18871$$Nest$fgetsetBalanceButtonText(this.this$0));
            BotStarsActivity.m18871$$Nest$fgetsetBalanceButtonText(this.this$0).run();
            if (BotStarsActivity.m18868$$Nest$fgetbalanceEditTextIgnore(this.this$0) == false) goto L16;
            return;
        L16:
            BotStarsActivity.m18873$$Nest$fputbalanceEditTextAll(this.this$0, false);
            return;
        L5:
            r3 = Long.parseLong(r7.toString());
            goto L6
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Stars.BotStarsActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends ButtonWithCounterView {
        final /* synthetic */ BotStarsActivity this$0;

        public AnonymousClass6(BotStarsActivity r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Stories.recorder.ButtonWithCounterView
        public boolean subTextSplitToWords() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Stars.BotStarsActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends LinearLayout {
        final /* synthetic */ BotStarsActivity this$0;

        public AnonymousClass7(BotStarsActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }
    }

    /* renamed from: org.telegram.ui.Stars.BotStarsActivity$8, reason: invalid class name */
    public class AnonymousClass8 extends RecyclerView.OnScrollListener {
        final /* synthetic */ BotStarsActivity this$0;

        public AnonymousClass8(BotStarsActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            BotStarsActivity r12 = this.this$0;
            if (r12.type == 1) goto L5;
            return;
        L5:
            if (BotStarsActivity.m18870$$Nest$fgetlistView(r12).canScrollVertically(1) == true) goto L7;
        L8:
            BotStarsActivity.m18876$$Nest$mloadTonTransactions(this.this$0);
            return;
        L7:
            if (this.this$0.isLoadingVisible() == true) goto L8;
        }
    }

    public class NestedFrameLayout extends SizeNotifierFrameLayout implements NestedScrollingParent3 {
        private NestedScrollingParentHelper nestedScrollingParentHelper;
        final /* synthetic */ BotStarsActivity this$0;

        public static /* synthetic */ void $r8$lambda$73Manx0noNGbBHVvaW465I9RIgM(NestedFrameLayout r0) {
            r0.lambda$onNestedScroll$0();
        }

        public NestedFrameLayout(BotStarsActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        }

        private /* synthetic */ void lambda$onNestedScroll$0() {
            RecyclerListView r1 = BotStarsActivity.m18872$$Nest$fgettransactionsLayout(this.this$0).getCurrentListView();     // Catch: Throwable -> L8
            if (r1 != null) goto L5;
            return;
        L5:
            if (r1.getAdapter() == null) goto L12;
            r1.getAdapter().notifyDataSetChanged();     // Catch: Throwable -> L8
            return;
        L12:
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public boolean onNestedPreFling(View r1, float r2, float r3) {
            return super.onNestedPreFling(r1, r2, r3);
        }

        @Override // androidx.core.view.NestedScrollingParent2
        public void onNestedPreScroll(View r5, int r6, int r7, int[] r8, int r9) {
            if (r5 == BotStarsActivity.m18870$$Nest$fgetlistView(this.this$0)) goto L5;
            return;
        L5:
            if (BotStarsActivity.m18872$$Nest$fgettransactionsLayout(this.this$0).isAttachedToWindow() == false) goto L51;
            boolean r52 = BotStarsActivity.access$200(this.this$0).isSearchFieldVisible();
            int r62 = (((View) BotStarsActivity.m18872$$Nest$fgettransactionsLayout(this.this$0).getParent()).getTop() - AndroidUtilities.statusBarHeight) - ActionBar.getCurrentActionBarHeight();
            int r92 = ((View) BotStarsActivity.m18872$$Nest$fgettransactionsLayout(this.this$0).getParent()).getBottom();
            boolean r0 = false;
            if (r7 >= 0) goto L33;
            ActionBar r2 = BotStarsActivity.access$300(this.this$0);
            if ((BotStarsActivity.m18870$$Nest$fgetlistView(this.this$0).getHeight() - r92) >= 0) goto L11;
            boolean r3 = true;
        L12:
            r2.setCastShadows(r3);
            if ((BotStarsActivity.m18870$$Nest$fgetlistView(this.this$0).getHeight() - r92) < 0) goto L26;
            RecyclerListView r4 = BotStarsActivity.m18872$$Nest$fgettransactionsLayout(this.this$0).getCurrentListView();
            int r93 = ((LinearLayoutManager) r4.getLayoutManager()).findFirstVisibleItemPosition();
            int r22 = -1;
            if (r93 == (-1)) goto L26;
            RecyclerView.ViewHolder r32 = r4.findViewHolderForAdapterPosition(r93);
            if (r32 == null) goto L19;
            r22 = r32.itemView.getTop();
        L19:
            int r33 = r4.getPaddingTop();
            if (r22 != r33) goto L22;
            if (r93 == 0) goto L26;
        L22:
            if (r93 == 0) goto L24;
            int r94 = r7;
        L25:
            r8[1] = r94;
            r4.scrollBy(0, r7);
            r0 = true;
            goto L26
        L24:
            r94 = Math.max(r7, r22 - r33);
        L26:
            if (r52 == false) goto L52;
            if (r0 == true) goto L31;
            if (r62 >= 0) goto L31;
            r8[1] = r7 - Math.max(r62, r7);
            return;
        L31:
            r8[1] = r7;
            return;
        L52:
            return;
        L11:
            r3 = false;
            goto L12
        L33:
            if (r52 == false) goto L42;
            RecyclerListView r42 = BotStarsActivity.m18872$$Nest$fgettransactionsLayout(this.this$0).getCurrentListView();
            r8[1] = r7;
            if (r62 <= 0) goto L37;
            r8[1] = 0;
        L37:
            if (r42 == null) goto L53;
            int r53 = r8[1];
            if (r53 <= 0) goto L54;
            r42.scrollBy(0, r53);
            return;
        L54:
            return;
        L53:
            return;
        L42:
            if (r7 <= 0) goto L55;
            RecyclerListView r54 = BotStarsActivity.m18872$$Nest$fgettransactionsLayout(this.this$0).getCurrentListView();
            if ((BotStarsActivity.m18870$$Nest$fgetlistView(this.this$0).getHeight() - r92) < 0) goto L56;
            if (r54 != null) goto L47;
            return;
        L47:
            if (r54.canScrollVertically(1) == true) goto L58;
            r8[1] = r7;
            BotStarsActivity.m18870$$Nest$fgetlistView(this.this$0).stopScroll();
            return;
        L58:
            return;
        L56:
            return;
        L55:
            return;
        }

        @Override // androidx.core.view.NestedScrollingParent2
        public void onNestedScroll(View r1, int r2, int r3, int r4, int r5, int r6) {
        }

        @Override // androidx.core.view.NestedScrollingParent3
        public void onNestedScroll(View r2, int r3, int r4, int r5, int r6, int r7, int[] r8) {
        L14:
            th = move-exception;
            FileLog.e(th);
            AndroidUtilities.runOnUIThread(new BotStarsActivity$NestedFrameLayout$$ExternalSyntheticLambda0(this));
            return;
        L3:
            if (r2 == BotStarsActivity.m18870$$Nest$fgetlistView(this.this$0)) goto L5;
            return;
        L5:
            if (BotStarsActivity.m18872$$Nest$fgettransactionsLayout(this.this$0).isAttachedToWindow() == false) goto L21;
            RecyclerListView r22 = BotStarsActivity.m18872$$Nest$fgettransactionsLayout(this.this$0).getCurrentListView();     // Catch: Throwable -> L14
            int r32 = ((View) BotStarsActivity.m18872$$Nest$fgettransactionsLayout(this.this$0).getParent()).getBottom();     // Catch: Throwable -> L14
            ActionBar r42 = BotStarsActivity.access$100(this.this$0);     // Catch: Throwable -> L14
            if ((BotStarsActivity.m18870$$Nest$fgetlistView(this.this$0).getHeight() - r32) >= 0) goto L9;
            boolean r52 = true;
        L10:
            r42.setCastShadows(r52);     // Catch: Throwable -> L14
            if ((BotStarsActivity.m18870$$Nest$fgetlistView(this.this$0).getHeight() - r32) < 0) goto L22;
            r8[1] = r6;     // Catch: Throwable -> L14
            r22.scrollBy(0, r6);     // Catch: Throwable -> L14
            return;
        L22:
            return;
        L9:
            r52 = false;
            goto L10
        }

        @Override // androidx.core.view.NestedScrollingParent2
        public void onNestedScrollAccepted(View r1, View r2, int r3, int r4) {
            this.nestedScrollingParentHelper.onNestedScrollAccepted(r1, r2, r3);
        }

        @Override // androidx.core.view.NestedScrollingParent2
        public boolean onStartNestedScroll(View r1, View r2, int r3, int r4) {
            if (r3 != 2) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void onStopNestedScroll(View r1) {
        }

        @Override // androidx.core.view.NestedScrollingParent2
        public void onStopNestedScroll(View r1, int r2) {
            this.nestedScrollingParentHelper.onStopNestedScroll(r1);
        }
    }

    public static /* synthetic */ void $r8$lambda$0JDYV27YFf2sP9HXbmV9YaUtIbg(BotStarsActivity r0) {
        r0.lambda$new$19();
    }

    /* renamed from: $r8$lambda$0pZcdI68Hc0Pv-naf8T5Ssy3Rr8, reason: not valid java name */
    public static /* synthetic */ void m18859$r8$lambda$0pZcdI68Hc0Pvnaf8T5Ssy3Rr8(BotStarsActivity r0, TwoStepVerificationActivity r1) {
        r0.lambda$createView$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$1etP5KhtCrJf3BhWzgHDcgO5Pk0(BotStarsActivity r0) {
        r0.lambda$withdraw$11();
    }

    public static /* synthetic */ void $r8$lambda$3jojpMwrmFCZvY4c1PeLggRF7hk(BotStarsActivity r0, TwoStepVerificationActivity r1, Activity r2, boolean r3, long r4, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$initWithdraw$24(r1, r2, r3, r4, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$5foVasrZLqQbZZxw7yohZ6PXXtE(BotStarsActivity r0, int r1) {
        r0.lambda$fillItems$15(r1);
    }

    public static /* synthetic */ void $r8$lambda$7cddnmOwOyjimY9URECAQcbZViQ(BotStarsActivity r0) {
        r0.lambda$fillItems$16();
    }

    public static /* synthetic */ void $r8$lambda$7iSZTiQ1dc_wG7LcG9mLtcaVExs(BotStarsActivity r0, TLRPC.TL_error r1, TwoStepVerificationActivity r2, Activity r3, boolean r4, long r5, TLObject r7) {
        r0.lambda$initWithdraw$23(r1, r2, r3, r4, r5, r7);
    }

    public static /* synthetic */ void $r8$lambda$BcjlMQTuIZi_ExO0LPxqWvbt248(BotStarsActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadTonTransactions$17(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$D0LGBlDdGROM9O3sjEgRzjCYE04(BotStarsActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$EcQj2gmWJgLJEsIgRsGhbWBKzGI(BotStarsActivity r0, TwoStepVerificationActivity r1) {
        r0.lambda$withdraw$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$FLkJvPkTIwugOTsCbghjdIxbXRE(BotStarsActivity r0, TLObject r1, Context r2) {
        r0.lambda$createView$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$I5RczT58kvPHSwH2vblHI650PLA(BotStarsActivity r0, TwoStepVerificationActivity r1, TLRPC.InputCheckPasswordSRP r2) {
        r0.lambda$createView$8(r1, r2);
    }

    /* renamed from: $r8$lambda$Jp-SregkShTHWvnXmyZASLts0yg, reason: not valid java name */
    public static /* synthetic */ boolean m18860$r8$lambda$JpSregkShTHWvnXmyZASLts0yg(BotStarsActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        return r0.onItemLongClick(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$KIfpEkkzi-1qaDqvrzjY9CyMUZI, reason: not valid java name */
    public static /* synthetic */ void m18861$r8$lambda$KIfpEkkzi1qaDqvrzjY9CyMUZI(BotStarsActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadTonTransactions$18(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$M5WaWFu6jNWd61sVRhSlazmdJjs(BotStarsActivity r0, TLRPC.TL_error r1, TLObject r2, TwoStepVerificationActivity r3, boolean r4, long r5) {
        r0.lambda$initWithdraw$21(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ boolean $r8$lambda$NkvemjcHSu2kD88eFurT0ySLiKA(BotStarsActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$VeXps2QUMW53WQ8fHC_BN3O_FBs(BotStarsActivity r0, View r1, boolean r2) {
        r0.lambda$createView$1(r1, r2);
    }

    /* renamed from: $r8$lambda$X0jIcnbQeEOpUiMdaGcJ3zW0K-c, reason: not valid java name */
    public static /* synthetic */ void m18862$r8$lambda$X0jIcnbQeEOpUiMdaGcJ3zW0Kc(BotStarsActivity r0, View r1) {
        r0.lambda$createView$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$dCoECv0IG6I3F5LNuNg_y_8BHbA(BotStarsActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onItemClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$ffjzML_RJOV35qrPnoKBOQko5Y8(BotStarsActivity r0, Context r1, View r2) {
        r0.lambda$createView$7(r1, r2);
    }

    /* renamed from: $r8$lambda$hNNsKesRTIQprs4h4oEb-wXIHW8, reason: not valid java name */
    public static /* synthetic */ void m18863$r8$lambda$hNNsKesRTIQprs4h4oEbwXIHW8(BotStarsActivity r0, View r1) {
        r0.lambda$createView$10(r1);
    }

    /* renamed from: $r8$lambda$ilXLDbwoG-Nf_TVFJSglkYFtjW4, reason: not valid java name */
    public static /* synthetic */ void m18864$r8$lambda$ilXLDbwoGNf_TVFJSglkYFtjW4(BotStarsActivity r0, AlertDialog r1, int r2) {
        r0.lambda$initWithdraw$20(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$leq2EE2qk8M_VeC8ek1cHPVdls0(BotStarsActivity r0, TwoStepVerificationActivity r1, boolean r2, long r3, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$initWithdraw$22(r1, r2, r3, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$maAsgdD1wDxgee7D7nL1L6ouXig(BotStarsActivity r0) {
        r0.lambda$new$0();
    }

    /* renamed from: $r8$lambda$potYUyDVR_z7J-VZmuT50J2YPGs, reason: not valid java name */
    public static /* synthetic */ void m18865$r8$lambda$potYUyDVR_z7JVZmuT50J2YPGs(BotStarsActivity r0, Context r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$createView$6(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$qKBh_Aw9_Dk1HcIjplHG6JfASo0(BotStarsActivity r0) {
        r0.lambda$fillItems$14();
    }

    public static /* synthetic */ void $r8$lambda$vjtqdpjEfLioQoWH7LOmEhTgt0g(BotStarsActivity r0, long r1, TwoStepVerificationActivity r3, TLRPC.InputCheckPasswordSRP r4) {
        r0.lambda$withdraw$12(r1, r3, r4);
    }

    /* renamed from: $r8$lambda$wrGfjO6LgA-k1fQHh-a0Qro7xXk, reason: not valid java name */
    public static /* synthetic */ void m18866$r8$lambda$wrGfjO6LgAk1fQHha0Qro7xXk(BotStarsActivity r0) {
        r0.lambda$createView$4();
    }

    /* renamed from: -$$Nest$fgetbalanceEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor m18867$$Nest$fgetbalanceEditText(BotStarsActivity r0) {
        return r0.balanceEditText;
    }

    /* renamed from: -$$Nest$fgetbalanceEditTextIgnore, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18868$$Nest$fgetbalanceEditTextIgnore(BotStarsActivity r0) {
        return r0.balanceEditTextIgnore;
    }

    /* renamed from: -$$Nest$fgetbalanceEditTextValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m18869$$Nest$fgetbalanceEditTextValue(BotStarsActivity r2) {
        return r2.balanceEditTextValue;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalRecyclerView m18870$$Nest$fgetlistView(BotStarsActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetsetBalanceButtonText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m18871$$Nest$fgetsetBalanceButtonText(BotStarsActivity r0) {
        return r0.setBalanceButtonText;
    }

    /* renamed from: -$$Nest$fgettransactionsLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StarsIntroActivity.StarsTransactionsLayout m18872$$Nest$fgettransactionsLayout(BotStarsActivity r0) {
        return r0.transactionsLayout;
    }

    /* renamed from: -$$Nest$fputbalanceEditTextAll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18873$$Nest$fputbalanceEditTextAll(BotStarsActivity r0, boolean r1) {
        r0.balanceEditTextAll = r1;
    }

    /* renamed from: -$$Nest$fputbalanceEditTextIgnore, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18874$$Nest$fputbalanceEditTextIgnore(BotStarsActivity r0, boolean r1) {
        r0.balanceEditTextIgnore = r1;
    }

    /* renamed from: -$$Nest$fputbalanceEditTextValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18875$$Nest$fputbalanceEditTextValue(BotStarsActivity r0, long r1) {
        r0.balanceEditTextValue = r1;
    }

    /* renamed from: -$$Nest$mloadTonTransactions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18876$$Nest$mloadTonTransactions(BotStarsActivity r0) {
        r0.loadTonTransactions();
    }

    public BotStarsActivity(int r5, long r6) {
        this.availableValue = ChannelMonetizationLayout.ProceedOverview.as("XTR", LocaleController.getString(R.string.BotStarsOverviewAvailableBalance));
        this.totalValue = ChannelMonetizationLayout.ProceedOverview.as("XTR", LocaleController.getString(R.string.BotStarsOverviewTotalBalance));
        this.totalProceedsValue = ChannelMonetizationLayout.ProceedOverview.as("XTR", LocaleController.getString(R.string.BotStarsOverviewTotalProceeds));
        this.tonAvailableValue = ChannelMonetizationLayout.ProceedOverview.as("TON", LocaleController.getString(R.string.BotMonetizationOverviewAvailable));
        this.tonLastWithdrawalValue = ChannelMonetizationLayout.ProceedOverview.as("TON", LocaleController.getString(R.string.BotMonetizationOverviewLastWithdrawal));
        this.tonLifetimeValue = ChannelMonetizationLayout.ProceedOverview.as("TON", LocaleController.getString(R.string.BotMonetizationOverviewTotal));
        boolean r0 = false;
        this.balanceEditTextIgnore = false;
        this.balanceEditTextAll = true;
        this.starRef = new ColoredImageSpan[1];
        this.shakeDp = 4;
        this.BALANCE = 1;
        this.BUTTON_AFFILIATE = 2;
        this.tonTransactionsLoading = false;
        this.tonTransactionsEndReached = false;
        this.tonTransactions = new ArrayList();
        this.tonTransactionsLastOffset = _UrlKt.FRAGMENT_ENCODE_SET;
        this.setBalanceButtonText = new BotStarsActivity$$ExternalSyntheticLambda0(this);
        this.stats_dc = -1;
        this.type = r5;
        this.bot_id = r6;
        if (r6 != getUserConfig().getClientUserId()) goto L5;
        r0 = true;
    L5:
        this.self = r0;
        if (r5 != 0) goto L10;
        BotStarsController.getInstance(this.currentAccount).preloadStarsStats(r6);
        if (r0 == true) goto L12;
        BotStarsController.getInstance(this.currentAccount).invalidateTransactions(r6, true);
    L12:
        if (r0 == false) goto L14;
        String r52 = LocaleController.formatPluralStringComma("SelfStarsWithdrawInfo", (int) getMessagesController().starsRevenueWithdrawalMin);
    L15:
        this.withdrawInfo = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(r52, new BotStarsActivity$$ExternalSyntheticLambda1(this)), true);
        return;
    L14:
        r52 = LocaleController.getString(R.string.BotStarsWithdrawInfo);
        goto L15
    L10:
        if (r5 != 1) goto L12;
        BotStarsController.getInstance(this.currentAccount).preloadTonStats(r6);
        goto L12
    }

    public static /* synthetic */ int access$000(BotStarsActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ActionBar access$100(BotStarsActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$200(BotStarsActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$300(BotStarsActivity r0) {
        return r0.actionBar;
    }

    private void checkStats() {
        TLRPC.TL_payments_starsRevenueStats r0 = BotStarsController.getInstance(this.currentAccount).getStarsRevenueStats(this.bot_id);
        TLRPC.TL_starsRevenueStatus r2 = null;
        if (r0 != this.lastStats) goto L10;
        if (r0 != null) goto L6;
        TLRPC.TL_starsRevenueStatus r1 = null;
    L8:
        if (r1 != this.lastStatsStatus) goto L10;
        return;
    L6:
        r1 = r0.status;
    L10:
        this.lastStats = r0;
        if (r0 == null) goto L14;
        r2 = r0.status;
    L14:
        this.lastStatsStatus = r2;
        if (r0 == null) goto L31;
        this.rate = r0.usd_rate;
        StatisticActivity.ChartViewData r12 = StatisticActivity.createViewData(r0.revenue_graph, LocaleController.getString(R.string.BotStarsChartRevenue), 2);
        this.revenueChartData = r12;
        if (r12 == null) goto L27;
        ChartData r13 = r12.chartData;
        if (r13 == null) goto L27;
        ArrayList<ChartData.Line> r14 = r13.lines;
        if (r14 == null) goto L27;
        if (r14.isEmpty() == true) goto L27;
        if (this.revenueChartData.chartData.lines.get(0) == null) goto L27;
        StatisticActivity.ChartViewData r15 = this.revenueChartData;
        r15.showAll = true;
        r15.chartData.lines.get(0).colorKey = Theme.key_color_yellow;
        this.revenueChartData.chartData.yRate = (float) ((1.0d / this.rate) / 100.0d);
    L27:
        TLRPC.TL_starsRevenueStatus r02 = r0.status;
        setStarsBalance(r02.available_balance, r02.next_withdrawal_at);
        UniversalRecyclerView r7 = this.listView;
        if (r7 == null) goto L32;
        r7.adapter.update(true);
        return;
    L32:
        return;
    }

    private void fillItems(ArrayList<UItem> r22, UniversalAdapter r23) {
        BotStarsController r2 = BotStarsController.getInstance(this.currentAccount);
        int r3 = this.type;
        int r13 = 0;
        if (r3 != 0) goto L25;
        r22.add(UItem.asChart(2, this.stats_dc, this.revenueChartData));
        r22.add(UItem.asShadow(-1, null));
        r22.add(UItem.asBlackHeader(LocaleController.getString(R.string.BotStarsOverview)));
        TLRPC.TL_payments_starsRevenueStats r24 = r2.getStarsRevenueStats(this.bot_id);
        if (r24 == null) goto L13;
        TLRPC.TL_starsRevenueStatus r32 = r24.status;
        if (r32 == null) goto L13;
        ChannelMonetizationLayout.ProceedOverview r8 = this.availableValue;
        r8.contains1 = false;
        r8.contains2 = true;
        TL_stars.StarsAmount r10 = r32.available_balance;
        r8.crypto_amount2 = r10;
        r8.crypto_currency2 = "XTR";
        r8.currency = "USD";
        double r6 = r10.amount;
        double r4 = this.rate;
        r8.amount2 = (long) ((r6 * r4) * 100.0d);
        ChannelMonetizationLayout.ProceedOverview r62 = this.totalValue;
        r62.contains1 = false;
        r62.contains2 = true;
        r62.crypto_amount2 = r32.current_balance;
        r62.crypto_currency2 = "XTR";
        r62.amount2 = (long) ((r7.amount * r4) * 100.0d);
        r62.currency = "USD";
        ChannelMonetizationLayout.ProceedOverview r63 = this.totalProceedsValue;
        r63.contains1 = false;
        r63.contains2 = true;
        r63.crypto_amount2 = r32.overall_revenue;
        r63.crypto_currency2 = "XTR";
        r63.amount2 = (long) ((r7.amount * r4) * 100.0d);
        r63.currency = "USD";
        setStarsBalance(r10, r32.next_withdrawal_at);
        LinearLayout r33 = this.balanceButtonsLayout;
        if (r24.status.withdrawal_enabled == false) goto L11;
        int r64 = 0;
    L12:
        r33.setVisibility(r64);
        goto L13
    L11:
        r64 = 8;
    L13:
        r22.add(UItem.asProceedOverview(this.availableValue));
        r22.add(UItem.asProceedOverview(this.totalValue));
        r22.add(UItem.asProceedOverview(this.totalProceedsValue));
        if (this.self == false) goto L16;
        int r25 = R.string.SelfStarsOverviewInfo;
    L17:
        r22.add(UItem.asShadow(-2, LocaleController.getString(r25)));
        r22.add(UItem.asBlackHeader(LocaleController.getString(R.string.BotStarsAvailableBalance)));
        r22.add(UItem.asCustom(1, this.balanceLayout));
        r22.add(UItem.asShadow(-3, this.withdrawInfo));
        if (this.self == false) goto L20;
        return;
    L20:
        if (getMessagesController().starrefConnectAllowed == false) goto L22;
        r22.add(AffiliateProgramFragment.ColorfulTextCell.Factory.as(2, Theme.getColor(Theme.key_color_green, this.resourceProvider), R.drawable.filled_earn_stars, ChatEditActivity.applyNewSpan(LocaleController.getString(R.string.BotAffiliateProgramRowTitle)), LocaleController.getString(R.string.BotAffiliateProgramRowText)));
        r22.add(UItem.asShadow(-4, null));
    L22:
        r22.add(UItem.asFullscreenCustom(this.transactionsLayout, 0));
        return;
    L16:
        r25 = R.string.BotStarsOverviewInfo;
        goto L17
    L25:
        if (r3 != 1) goto L94;
        TLRPC.TL_payments_starsRevenueStats r26 = r2.getTONRevenueStats(this.bot_id, true);
        if (this.self == true) goto L33;
        if (this.titleInfo != null) goto L31;
        this.titleInfo = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.formatString(R.string.BotMonetizationInfo, new Object[]{50}), -1, 3, new BotStarsActivity$$ExternalSyntheticLambda16(this), this.resourceProvider), true);
    L31:
        r22.add(UItem.asCenterShadow(this.titleInfo));
    L33:
        if (this.impressionsChart != null) goto L38;
        if (r26 == null) goto L38;
        StatisticActivity.ChartViewData r34 = StatisticActivity.createViewData(r26.top_hours_graph, LocaleController.getString(R.string.BotMonetizationGraphImpressions), 0);
        this.impressionsChart = r34;
        if (r34 == null) goto L38;
        r34.useHourFormat = true;
    L38:
        StatisticActivity.ChartViewData r35 = this.impressionsChart;
        if (r35 == null) goto L44;
        if (r35.isEmpty == true) goto L44;
        r22.add(UItem.asChart(5, this.stats_dc, r35));
        r22.add(UItem.asShadow(-1, null));
    L44:
        if (this.revenueChart != null) goto L50;
        if (r26 == null) goto L50;
        TL_stats.StatsGraph r36 = r26.revenue_graph;
        if (r36 == null) goto L49;
        r36.rate = (float) (1.0E7d / r26.usd_rate);
    L49:
        this.revenueChart = StatisticActivity.createViewData(r36, LocaleController.getString(R.string.BotMonetizationGraphRevenue), 2);
    L50:
        StatisticActivity.ChartViewData r37 = this.revenueChart;
        if (r37 == null) goto L56;
        if (r37.isEmpty == true) goto L56;
        r22.add(UItem.asChart(2, this.stats_dc, r37));
        r22.add(UItem.asShadow(-2, null));
    L56:
        if (this.proceedsAvailable == true) goto L68;
        if (r26 == null) goto L68;
        TLRPC.TL_starsRevenueStatus r38 = r26.status;
        if (r38 == null) goto L68;
        double r5 = r26.usd_rate;
        ChannelMonetizationLayout.ProceedOverview r7 = this.tonAvailableValue;
        long r102 = r38.available_balance.amount;
        r7.crypto_amount = r102;
        long r42 = (long) (((r102 / 1.0E9d) * r5) * 100.0d);
        r7.amount = r42;
        setBalance(r102, r42);
        this.tonAvailableValue.currency = "USD";
        ChannelMonetizationLayout.ProceedOverview r43 = this.tonLastWithdrawalValue;
        TLRPC.TL_starsRevenueStatus r27 = r26.status;
        long r52 = r27.current_balance.amount;
        r43.crypto_amount = r52;
        r43.amount = (long) (((r52 / 1.0E9d) * r5) * 100.0d);
        r43.currency = "USD";
        ChannelMonetizationLayout.ProceedOverview r44 = this.tonLifetimeValue;
        r44.contains1 = true;
        long r53 = r27.overall_revenue.amount;
        r44.crypto_amount = r53;
        r44.amount = (long) (((r53 / 1.0E9d) * r5) * 100.0d);
        r44.currency = "USD";
        this.proceedsAvailable = true;
        ButtonWithCounterView r45 = this.tonBalanceButton;
        if (r27.available_balance.amount > 0) goto L63;
    L65:
        int r65 = 8;
    L66:
        r45.setVisibility(r65);
        goto L68
    L63:
        if (r27.withdrawal_enabled == false) goto L65;
        r65 = 0;
    L68:
        if (this.proceedsAvailable == false) goto L73;
        r22.add(UItem.asBlackHeader(LocaleController.getString(R.string.BotMonetizationOverview)));
        r22.add(UItem.asProceedOverview(this.tonAvailableValue));
        r22.add(UItem.asProceedOverview(this.tonLastWithdrawalValue));
        r22.add(UItem.asProceedOverview(this.tonLifetimeValue));
        if (this.proceedsInfo != null) goto L72;
        this.proceedsInfo = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.BotMonetizationProceedsTONInfo), -1, 3, new BotStarsActivity$$ExternalSyntheticLambda17(this, R.string.BotMonetizationProceedsTONInfoLink), this.resourceProvider), true);
    L72:
        r22.add(UItem.asShadow(-4, this.proceedsInfo));
    L73:
        r22.add(UItem.asBlackHeader(LocaleController.getString(R.string.BotMonetizationBalance)));
        r22.add(UItem.asCustom(this.tonBalanceLayout));
        if (this.balanceInfo == null) goto L76;
    L80:
        r22.add(UItem.asShadow(-5, this.balanceInfo));
        if (this.tonTransactionsEndReached == true) goto L83;
    L84:
        r22.add(UItem.asBlackHeader(LocaleController.getString(R.string.BotMonetizationTransactions)));
        ArrayList<TL_stars.StarsTransaction> r28 = this.tonTransactions;
        int r46 = r28.size();
    L85:
        if (r13 >= r46) goto L88;
        TL_stars.StarsTransaction r54 = r28.get(r13);
        r13 = r13 + 1;
        r22.add(StarsIntroActivity.StarsTransactionView.Factory.asTransaction(r54, true));
        goto L85
    L88:
        if (this.tonTransactionsEndReached == true) goto L90;
        r22.add(UItem.asFlicker(1, 7));
        r22.add(UItem.asFlicker(2, 7));
        r22.add(UItem.asFlicker(3, 7));
    L90:
        r22.add(UItem.asShadow(-6, null));
        return;
    L83:
        if (this.tonTransactions.isEmpty() == true) goto L90;
    L76:
        if (MessagesController.getInstance(this.currentAccount).channelRevenueWithdrawalEnabled == false) goto L78;
        int r29 = R.string.BotMonetizationBalanceInfo;
    L79:
        this.balanceInfo = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(r29), -1, 3, new BotStarsActivity$$ExternalSyntheticLambda18(this)), true);
        goto L80
    L78:
        r29 = R.string.BotMonetizationBalanceInfoNotAvailable;
        goto L79
    }

    private void initWithdraw(boolean r9, long r10, TLRPC.InputCheckPasswordSRP r12, TwoStepVerificationActivity r13) {
        Activity r3 = getParentActivity();
        TLRPC.User r0 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
        if (r3 == null) goto L20;
        if (r0 == null) goto L21;
        if (r9 == false) goto L13;
        TLRPC.TL_payments_getStarsRevenueWithdrawalUrl r02 = new TLRPC.TL_payments_getStarsRevenueWithdrawalUrl();
        r02.ton = false;
        r02.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.bot_id);
        if (r12 != null) goto L11;
        r12 = new TLRPC.TL_inputCheckPasswordEmpty();
    L11:
        r02.password = r12;
        r02.flags |= 2;
        r02.amount = r10;
    L18:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, new BotStarsActivity$$ExternalSyntheticLambda22(this, r13, r3, r9, r10));
        return;
    L13:
        r02 = new TLRPC.TL_payments_getStarsRevenueWithdrawalUrl();
        r02.ton = true;
        r02.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.bot_id);
        if (r12 != null) goto L17;
        r12 = new TLRPC.TL_inputCheckPasswordEmpty();
    L17:
        r02.password = r12;
        goto L18
    L21:
        return;
    }

    private /* synthetic */ void lambda$createView$1(View r1, boolean r2) {
        OutlineTextContainerView r0 = this.balanceEditTextContainer;
        if (r2 == false) goto L5;
        float r12 = 1.0f;
    L6:
        r0.animateSelection(r12);
        return;
    L5:
        r12 = 0.0f;
        goto L6
    }

    private /* synthetic */ void lambda$createView$10(View r3) {
        if (r3.isEnabled() == true) goto L5;
        return;
    L5:
        if (this.tonBalanceButton.isLoading() == true) goto L10;
        TwoStepVerificationActivity r32 = new TwoStepVerificationActivity();
        r32.setDelegate(1, new BotStarsActivity$$ExternalSyntheticLambda10(this, r32));
        this.tonBalanceButton.setLoading(true);
        r32.preload(new BotStarsActivity$$ExternalSyntheticLambda11(this, r32));
        return;
    }

    private /* synthetic */ boolean lambda$createView$2(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L6;
        withdraw();
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$createView$3(View r1) {
        withdraw();
    }

    private /* synthetic */ void lambda$createView$4() {
        this.adsButton.setLoading(false);
    }

    private /* synthetic */ void lambda$createView$5(TLObject r3, Context r4) {
        if ((r3 instanceof TLRPC.TL_payments_starsRevenueAdsAccountUrl) == false) goto L5;
        Browser.openUrl(r4, ((TLRPC.TL_payments_starsRevenueAdsAccountUrl) r3).url);
    L5:
        AndroidUtilities.runOnUIThread(new BotStarsActivity$$ExternalSyntheticLambda21(this), 1000);
    }

    private /* synthetic */ void lambda$createView$6(Context r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new BotStarsActivity$$ExternalSyntheticLambda19(this, r2, r1));
    }

    private /* synthetic */ void lambda$createView$7(Context r4, View r5) {
        if (r5.isEnabled() == true) goto L5;
        return;
    L5:
        if (this.adsButton.isLoading() == true) goto L10;
        this.adsButton.setLoading(true);
        TLRPC.TL_payments_getStarsRevenueAdsAccountUrl r52 = new TLRPC.TL_payments_getStarsRevenueAdsAccountUrl();
        r52.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.bot_id);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r52, new BotStarsActivity$$ExternalSyntheticLambda15(this, r4));
        return;
    }

    private /* synthetic */ void lambda$createView$8(TwoStepVerificationActivity r7, TLRPC.InputCheckPasswordSRP r8) {
        initWithdraw(false, 0, r8, r7);
    }

    private /* synthetic */ void lambda$createView$9(TwoStepVerificationActivity r3) {
        this.tonBalanceButton.setLoading(false);
        presentFragment(r3);
    }

    private /* synthetic */ void lambda$fillItems$14() {
        showDialog(ChannelMonetizationLayout.makeLearnSheet(getContext(), true, this.resourceProvider));
    }

    private /* synthetic */ void lambda$fillItems$15(int r1) {
        Browser.openUrl(getContext(), LocaleController.getString(r1));
    }

    private /* synthetic */ void lambda$fillItems$16() {
        Browser.openUrl(getContext(), LocaleController.getString(R.string.BotMonetizationBalanceInfoLink));
    }

    private /* synthetic */ void lambda$initWithdraw$20(AlertDialog r2, int r3) {
        presentFragment(new TwoStepVerificationSetupActivity(6, null));
    }

    private /* synthetic */ void lambda$initWithdraw$21(TLRPC.TL_error r7, TLObject r8, TwoStepVerificationActivity r9, boolean r10, long r11) {
        if (r7 != null) goto L5;
        TL_account.Password r82 = (TL_account.Password) r8;
        r9.setCurrentPasswordInfo(null, r82);
        TwoStepVerificationActivity.initPasswordNewAlgo(r82);
        initWithdraw(r10, r11, r9.getNewSrpPassword(), r9);
        return;
    }

    private /* synthetic */ void lambda$initWithdraw$22(TwoStepVerificationActivity r9, boolean r10, long r11, TLObject r13, TLRPC.TL_error r14) {
        AndroidUtilities.runOnUIThread(new BotStarsActivity$$ExternalSyntheticLambda27(this, r14, r13, r9, r10, r11));
    }

    private /* synthetic */ void lambda$initWithdraw$23(TLRPC.TL_error r27, TwoStepVerificationActivity r28, Activity r29, boolean r30, long r31, TLObject r33) {
        if (r27 != null) goto L5;
        r28.needHideProgress();
        r28.finishFragment();
        if ((r33 instanceof TL_stats.TL_broadcastRevenueWithdrawalUrl) == false) goto L76;
        Browser.openUrlInSystemBrowser(getContext(), ((TL_stats.TL_broadcastRevenueWithdrawalUrl) r33).url);
        return;
    L76:
        if ((r33 instanceof TLRPC.TL_payments_starsRevenueWithdrawalUrl) == false) goto L79;
        this.balanceEditTextAll = true;
        Browser.openUrlInSystemBrowser(getContext(), ((TLRPC.TL_payments_starsRevenueWithdrawalUrl) r33).url);
        return;
    L79:
        return;
    L5:
        if ("PASSWORD_MISSING".equals(r27.text) == true) goto L20;
        if (r27.text.startsWith("PASSWORD_TOO_FRESH_") == true) goto L20;
        if (r27.text.startsWith("SESSION_TOO_FRESH_") == true) goto L20;
        if ("SRP_ID_INVALID".equals(r27.text) == false) goto L16;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_account.getPassword(), new BotStarsActivity$$ExternalSyntheticLambda26(this, r28, r30, r31), 8);
        return;
    L16:
        if (r28 == null) goto L18;
        r28.needHideProgress();
        r28.finishFragment();
    L18:
        BulletinFactory.showError(r27);
        return;
    L20:
        if (r28 == null) goto L22;
        r28.needHideProgress();
    L22:
        AlertDialog.Builder r3 = new AlertDialog.Builder(r29);
        r3.setTitle(LocaleController.getString(R.string.EditAdminTransferAlertTitle));
        LinearLayout r7 = new LinearLayout(r29);
        r7.setPadding(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(24.0f), 0);
        r7.setOrientation(1);
        r3.setView(r7);
        TextView r8 = new TextView(r29);
        int r9 = Theme.key_dialogTextBlack;
        r8.setTextColor(Theme.getColor(r9));
        r8.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L25;
        int r12 = 5;
    L26:
        r8.setGravity(r12 | 48);
        r8.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.WithdrawChannelAlertText)));
        r7.addView(r8, LayoutHelper.createLinear(-1, -2));
        LinearLayout r82 = new LinearLayout(r29);
        r82.setOrientation(0);
        r7.addView(r82, LayoutHelper.createLinear(-1, -2, 0.0f, 11.0f, 0.0f, 0.0f));
        ImageView r13 = new ImageView(r29);
        r13.setImageResource(R.drawable.list_circle);
        if (LocaleController.isRTL == false) goto L29;
        int r14 = AndroidUtilities.dp(11.0f);
    L30:
        int r122 = AndroidUtilities.dp(9.0f);
        if (LocaleController.isRTL == false) goto L33;
        int r15 = 0;
    L34:
        r13.setPadding(r14, r122, r15, 0);
        int r142 = Theme.getColor(r9);
        PorterDuff.Mode r152 = PorterDuff.Mode.MULTIPLY;
        r13.setColorFilter(new PorterDuffColorFilter(r142, r152));
        TextView r123 = new TextView(r29);
        r123.setTextColor(Theme.getColor(r9));
        r123.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L37;
        int r143 = 5;
    L38:
        r123.setGravity(r143 | 48);
        r123.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.EditAdminTransferAlertText1)));
        if (LocaleController.isRTL == false) goto L41;
        r82.addView(r123, LayoutHelper.createLinear(-1, -2));
        r82.addView(r13, LayoutHelper.createLinear(-2, -2, 5));
    L42:
        LinearLayout r4 = new LinearLayout(r29);
        r4.setOrientation(0);
        r7.addView(r4, LayoutHelper.createLinear(-1, -2, 0.0f, 11.0f, 0.0f, 0.0f));
        ImageView r83 = new ImageView(r29);
        r83.setImageResource(R.drawable.list_circle);
        if (LocaleController.isRTL == false) goto L45;
        int r10 = AndroidUtilities.dp(11.0f);
    L46:
        int r124 = AndroidUtilities.dp(9.0f);
        if (LocaleController.isRTL == false) goto L49;
        int r132 = 0;
    L50:
        r83.setPadding(r10, r124, r132, 0);
        r83.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r9), r152));
        TextView r102 = new TextView(r29);
        r102.setTextColor(Theme.getColor(r9));
        r102.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L53;
        int r11 = 5;
    L54:
        r102.setGravity(r11 | 48);
        r102.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.EditAdminTransferAlertText2)));
        if (LocaleController.isRTL == false) goto L57;
        r4.addView(r102, LayoutHelper.createLinear(-1, -2));
        int r125 = 5;
        r4.addView(r83, LayoutHelper.createLinear(-2, -2, 5));
    L59:
        if ("PASSWORD_MISSING".equals(r27.text) == false) goto L61;
        r3.setPositiveButton(LocaleController.getString(R.string.EditAdminTransferSetPassword), new BotStarsActivity$$ExternalSyntheticLambda25(this));
        r3.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
    L66:
        if (r28 == null) goto L69;
        r28.showDialog(r3.create());
        return;
    L69:
        showDialog(r3.create());
        return;
    L61:
        TextView r0 = new TextView(r29);
        r0.setTextColor(Theme.getColor(r9));
        r0.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L64;
        int r133 = r125;
    L65:
        r0.setGravity(r133 | 48);
        r0.setText(LocaleController.getString(R.string.EditAdminTransferAlertText3));
        r7.addView(r0, LayoutHelper.createLinear(-1, -2, 0.0f, 11.0f, 0.0f, 0.0f));
        r3.setNegativeButton(LocaleController.getString(R.string.OK), null);
        goto L66
    L64:
        r133 = 3;
        goto L65
    L57:
        r125 = 5;
        r4.addView(r83, LayoutHelper.createLinear(-2, -2));
        r4.addView(r102, LayoutHelper.createLinear(-1, -2));
        goto L59
    L53:
        r11 = 3;
        goto L54
    L49:
        r132 = AndroidUtilities.dp(11.0f);
        goto L50
    L45:
        r10 = 0;
        goto L46
    L41:
        r82.addView(r13, LayoutHelper.createLinear(-2, -2));
        r82.addView(r123, LayoutHelper.createLinear(-1, -2));
        goto L42
    L37:
        r143 = 3;
        goto L38
    L33:
        r15 = AndroidUtilities.dp(11.0f);
        goto L34
    L29:
        r14 = 0;
        goto L30
    L25:
        r12 = 3;
        goto L26
    }

    private /* synthetic */ void lambda$initWithdraw$24(TwoStepVerificationActivity r10, Activity r11, boolean r12, long r13, TLObject r15, TLRPC.TL_error r16) {
        AndroidUtilities.runOnUIThread(new BotStarsActivity$$ExternalSyntheticLambda24(this, r16, r10, r11, r12, r13, r15));
    }

    private /* synthetic */ void lambda$loadTonTransactions$17(TLObject r4, TLRPC.TL_error r5) {
        if ((r4 instanceof TL_stars.StarsStatus) == false) goto L12;
        TL_stars.StarsStatus r42 = (TL_stars.StarsStatus) r4;
        MessagesController.getInstance(this.currentAccount).putUsers(r42.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r42.chats, false);
        this.tonTransactionsLastOffset = r42.next_offset;
        this.tonTransactions.addAll(r42.history);
        if (r42.history.isEmpty() == false) goto L7;
    L10:
        boolean r43 = true;
    L11:
        this.tonTransactionsEndReached = r43;
    L14:
        this.tonTransactionsLoading = false;
        UniversalAdapter r3 = this.listView.adapter;
        if (r3 == null) goto L18;
        r3.update(true);
        return;
    L18:
        return;
    L7:
        if (r42.next_offset == null) goto L10;
        r43 = false;
        goto L11
    L12:
        if (r5 == null) goto L14;
        BulletinFactory.showError(r5);
        this.tonTransactionsEndReached = true;
        goto L14
    }

    private /* synthetic */ void lambda$loadTonTransactions$18(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new BotStarsActivity$$ExternalSyntheticLambda23(this, r2, r3));
    }

    private /* synthetic */ void lambda$new$0() {
        Browser.openUrl(getContext(), LocaleController.getString(R.string.BotStarsWithdrawInfoLink));
    }

    private /* synthetic */ void lambda$new$19() {
        int r0 = getConnectionsManager().getCurrentTime();
        ButtonWithCounterView r1 = this.balanceButton;
        if (this.balanceEditTextValue <= 0) goto L5;
    L8:
        boolean r2 = true;
    L9:
        r1.setEnabled(r2);
        int r12 = this.balanceBlockedUntil;
        ButtonWithCounterView r22 = this.balanceButton;
        if (r0 >= r12) goto L23;
        r22.setText(LocaleController.getString(R.string.BotStarsButtonWithdrawShortUntil), true);
        if (this.lock != null) goto L14;
        this.lock = new SpannableStringBuilder("l");
        ColoredImageSpan r13 = new ColoredImageSpan(R.drawable.mini_switch_lock);
        r13.setTopOffset(1);
        this.lock.setSpan(r13, 0, 1, 33);
    L14:
        SpannableStringBuilder r14 = new SpannableStringBuilder();
        r14.append(this.lock).append(untilString(this.balanceBlockedUntil - r0));
        this.balanceButton.setSubText(r14, true);
        Bulletin r15 = this.withdrawalBulletin;
        if (r15 != null) goto L17;
    L21:
        AndroidUtilities.cancelRunOnUIThread(this.setBalanceButtonText);
        AndroidUtilities.runOnUIThread(this.setBalanceButtonText, 1000);
        return;
    L17:
        if ((r15.getLayout() instanceof Bulletin.LottieLayout) == false) goto L21;
        if (this.withdrawalBulletin.getLayout().isAttachedToWindow() == false) goto L21;
        ((Bulletin.LottieLayout) this.withdrawalBulletin.getLayout()).textView.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BotStarsWithdrawalToast, new Object[]{untilString(this.balanceBlockedUntil - r0)})));
        goto L21
    L23:
        r22.setSubText(null, true);
        ButtonWithCounterView r02 = this.balanceButton;
        if (this.balanceEditTextAll == false) goto L26;
        String r16 = LocaleController.getString(R.string.BotStarsButtonWithdrawShortAll);
    L27:
        r02.setText(StarsIntroActivity.replaceStars(r16, this.starRef), true);
        return;
    L26:
        r16 = LocaleController.formatPluralStringSpaced("BotStarsButtonWithdrawShort", (int) this.balanceEditTextValue);
        goto L27
    L5:
        if (this.balanceBlockedUntil > r0) goto L8;
        r2 = false;
        goto L9
    }

    private /* synthetic */ void lambda$withdraw$11() {
        Bulletin.hideVisible();
        long r0 = BotStarsController.getInstance(this.currentAccount).getAvailableBalance(this.bot_id);
        if (r0 >= getMessagesController().starsRevenueWithdrawalMin) goto L5;
        this.balanceEditTextAll = true;
        this.balanceEditTextValue = r0;
    L6:
        this.balanceEditTextIgnore = true;
        this.balanceEditText.setText(Long.toString(this.balanceEditTextValue));
        EditTextBoldCursor r02 = this.balanceEditText;
        r02.setSelection(r02.getText().length());
        this.balanceEditTextIgnore = false;
        AndroidUtilities.cancelRunOnUIThread(this.setBalanceButtonText);
        this.setBalanceButtonText.run();
        return;
    L5:
        this.balanceEditTextAll = false;
        this.balanceEditTextValue = getMessagesController().starsRevenueWithdrawalMin;
        goto L6
    }

    private /* synthetic */ void lambda$withdraw$12(long r7, TwoStepVerificationActivity r9, TLRPC.InputCheckPasswordSRP r10) {
        initWithdraw(true, r7, r10, r9);
    }

    private /* synthetic */ void lambda$withdraw$13(TwoStepVerificationActivity r3) {
        this.balanceButton.setLoading(false);
        presentFragment(r3);
    }

    private void loadTonTransactions() {
        if (this.tonTransactionsLoading == false) goto L5;
        return;
    L5:
        if (this.tonTransactionsEndReached == false) goto L7;
        return;
    L7:
        if (this.tonTransactionsLastOffset == null) goto L17;
        this.tonTransactionsLoading = true;
        TL_stars.TL_payments_getStarsTransactions r1 = new TL_stars.TL_payments_getStarsTransactions();
        r1.ton = true;
        r1.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.bot_id);
        r1.offset = this.tonTransactionsLastOffset;
        if (this.tonTransactions.isEmpty() == false) goto L12;
        int r0 = 5;
    L13:
        r1.limit = r0;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new BotStarsActivity$$ExternalSyntheticLambda20(this));
        return;
    L12:
        r0 = 20;
        goto L13
    }

    private void onItemClick(UItem r8, View r9, int r10, float r11, float r12) {
        boolean r92 = r8.instanceOf(StarsIntroActivity.StarsTransactionView.Factory.class);
        Object r102 = r8.object;
        if (r92 == false) goto L7;
        Context r0 = getContext();
        long r2 = this.bot_id;
        int r4 = this.currentAccount;
        StarsIntroActivity.showTransactionSheet(r0, true, r2, r4, (TL_stars.StarsTransaction) r102, getResourceProvider());
        return;
    L7:
        if ((r102 instanceof TL_stats.BroadcastRevenueTransaction) == false) goto L11;
        ChannelMonetizationLayout.showTransactionSheet(getContext(), this.currentAccount, (TL_stats.BroadcastRevenueTransaction) r8.object, this.bot_id, this.resourceProvider);
        return;
    L11:
        if (r8.id != 2) goto L14;
        presentFragment(new ChannelAffiliateProgramsFragment(this.bot_id));
        return;
    }

    private boolean onItemLongClick(UItem r1, View r2, int r3, float r4, float r5) {
        return false;
    }

    private void setBalance(long r6, long r8) {
        int r1 = 6;
        if (this.formatter != null) goto L5;
        DecimalFormatSymbols r0 = new DecimalFormatSymbols(Locale.US);
        r0.setDecimalSeparator('.');
        DecimalFormat r3 = new DecimalFormat("#.##", r0);
        this.formatter = r3;
        r3.setMinimumFractionDigits(2);
        this.formatter.setMaximumFractionDigits(6);
        this.formatter.setGroupingUsed(false);
    L5:
        DecimalFormat r02 = this.formatter;
        double r62 = r6 / 1.0E9d;
        if (r62 <= 1.5d) goto L8;
        r1 = 2;
    L8:
        r02.setMaximumFractionDigits(r1);
        SpannableStringBuilder r03 = new SpannableStringBuilder(ChannelMonetizationLayout.replaceTON("TON " + this.formatter.format(r62), this.tonBalanceTitle.getPaint(), 0.9f, true));
        int r63 = TextUtils.indexOf(r03, ".");
        if (r63 < 0) goto L11;
        r03.setSpan(this.tonBalanceTitleSizeSpan, r63, r03.length(), 33);
    L11:
        this.tonBalanceTitle.setText(r03);
        this.tonBalanceSubtitle.setText("≈" + BillingController.getInstance().formatCurrency(r8, "USD"));
    }

    private void setStarsBalance(TL_stars.StarsAmount r10, int r11) {
        if (this.balanceTitle != null) goto L5;
        return;
    L5:
        if (this.balanceSubtitle == null) goto L23;
        long r0 = (long) ((this.rate * r10.amount) * 100.0d);
        boolean r6 = false;
        SpannableStringBuilder r2 = new SpannableStringBuilder(StarsIntroActivity.replaceStarsWithPlain(TextUtils.concat(new CharSequence[]{"XTR ", StarsIntroActivity.formatStarsAmount(r10, 0.8f, ' ')}), 1.0f));
        int r3 = TextUtils.indexOf(r2, ".");
        if (r3 < 0) goto L10;
        r2.setSpan(this.balanceTitleSizeSpan, r3, r2.length(), 33);
    L10:
        this.balanceTitle.setText(r2);
        this.balanceSubtitle.setText("≈" + BillingController.getInstance().formatCurrency(r0, "USD"));
        OutlineTextContainerView r22 = this.balanceEditTextContainer;
        if (r0 <= 0) goto L13;
        int r02 = 0;
    L14:
        r22.setVisibility(r02);
        if (this.balanceEditTextAll == false) goto L20;
        this.balanceEditTextIgnore = true;
        EditTextBoldCursor r03 = this.balanceEditText;
        long r1 = r10.amount;
        this.balanceEditTextValue = r1;
        r03.setText(Long.toString(r1));
        EditTextBoldCursor r102 = this.balanceEditText;
        r102.setSelection(r102.getText().length());
        this.balanceEditTextIgnore = false;
        ButtonWithCounterView r103 = this.balanceButton;
        if (this.balanceEditTextValue <= 0) goto L19;
        r6 = true;
    L19:
        r103.setEnabled(r6);
    L20:
        this.balanceBlockedUntil = r11;
        AndroidUtilities.cancelRunOnUIThread(this.setBalanceButtonText);
        this.setBalanceButtonText.run();
        return;
    L13:
        r02 = 8;
        goto L14
    }

    public static String untilString(int r5) {
        int r1 = r5 / 86400;
        int r52 = r5 - (86400 * r1);
        int r0 = r52 / 3600;
        int r53 = r52 - (r0 * 3600);
        int r2 = r53 / 60;
        int r54 = r53 - (r2 * 60);
        if (r1 != 0) goto L9;
        if (r0 != 0) goto L8;
        return String.format(Locale.ENGLISH, "%02d:%02d", new Object[]{Integer.valueOf(r2), Integer.valueOf(r54)});
    L8:
        return String.format(Locale.ENGLISH, "%02d:%02d:%02d", new Object[]{Integer.valueOf(r0), Integer.valueOf(r2), Integer.valueOf(r54)});
    L9:
        int r55 = R.string.PeriodDHM;
        Locale r3 = Locale.ENGLISH;
        return LocaleController.formatString(r55, new Object[]{String.format(r3, TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r1)}), String.format(r3, TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r0)}), String.format(r3, TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r2)})});
    }

    private void withdraw() {
        if (this.balanceButton.isEnabled() == true) goto L5;
        return;
    L5:
        if (this.balanceButton.isLoading() == true) goto L18;
        int r0 = getConnectionsManager().getCurrentTime();
        if (this.balanceBlockedUntil <= r0) goto L12;
        this.withdrawalBulletin = BulletinFactory.of(this).createSimpleBulletin(R.raw.timer_3, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BotStarsWithdrawalToast, new Object[]{untilString(this.balanceBlockedUntil - r0)}))).show();
        return;
    L12:
        if (this.balanceEditTextValue >= getMessagesController().starsRevenueWithdrawalMin) goto L15;
        Drawable r02 = getContext().getResources().getDrawable(R.drawable.star_small_inner).mutate();
        BulletinFactory.of(this).createSimpleBulletin(r02, AndroidUtilities.replaceSingleTag(LocaleController.formatPluralString("BotStarsWithdrawMinLimit", (int) getMessagesController().starsRevenueWithdrawalMin, new Object[0]), new BotStarsActivity$$ExternalSyntheticLambda12(this))).show();
        return;
    L15:
        long r03 = this.balanceEditTextValue;
        TwoStepVerificationActivity r2 = new TwoStepVerificationActivity();
        r2.setDelegate(1, new BotStarsActivity$$ExternalSyntheticLambda13(this, r03, r2));
        this.balanceButton.setLoading(true);
        r2.preload(new BotStarsActivity$$ExternalSyntheticLambda14(this, r2));
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r34) {
        NestedFrameLayout r9 = new NestedFrameLayout(this, r34);
        ChatAvatarContainer r1 = new ChatAvatarContainer(r34, null, false);
        this.avatarContainer = r1;
        r1.setOccupyStatusBar(!AndroidUtilities.isTablet());
        this.avatarContainer.getAvatarImageView().setScaleX(0.9f);
        this.avatarContainer.getAvatarImageView().setScaleY(0.9f);
        this.avatarContainer.setRightAvatarPadding(-AndroidUtilities.dp(3.0f));
        ActionBar r12 = this.actionBar;
        ChatAvatarContainer r3 = this.avatarContainer;
        if (this.inPreviewMode == true) goto L5;
        float r17 = 50.0f;
    L6:
        r12.addView(r3, 0, LayoutHelper.createFrame(-2, -1.0f, 51, r17, 0.0f, 40.0f, 0.0f));
        TLRPC.User r13 = getMessagesController().getUser(Long.valueOf(this.bot_id));
        this.avatarContainer.setUserAvatar(r13, true);
        this.avatarContainer.setTitle(UserObject.getUserName(r13));
        int r14 = this.type;
        ChatAvatarContainer r32 = this.avatarContainer;
        if (r14 != 0) goto L9;
        r32.setSubtitle(LocaleController.getString(R.string.BotStatsStars));
    L10:
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ChatAvatarContainer r15 = this.avatarContainer;
        int r33 = Theme.key_player_actionBarTitle;
        r15.setTitleColors(Theme.getColor(r33), Theme.getColor(Theme.key_player_actionBarSubtitle));
        this.actionBar.setItemsColor(Theme.getColor(r33), false);
        this.actionBar.setItemsColor(Theme.getColor(r33), true);
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_actionBarActionModeDefaultSelector), false);
        ActionBar r16 = this.actionBar;
        int r142 = Theme.key_windowBackgroundWhite;
        r16.setBackgroundColor(Theme.getColor(r142));
        this.transactionsLayout = new StarsIntroActivity.StarsTransactionsLayout(r34, this.currentAccount, false, this.bot_id, getClassGuid(), getResourceProvider());
        AnonymousClass2 r18 = new AnonymousClass2(this, r34);
        this.balanceLayout = r18;
        r18.setOrientation(1);
        this.balanceLayout.setBackgroundColor(Theme.getColor(r142, getResourceProvider()));
        this.balanceLayout.setPadding(0, 0, 0, AndroidUtilities.dp(17.0f));
        AnimatedTextView r19 = new AnimatedTextView(r34, false, true, true);
        this.balanceTitle = r19;
        r19.setTypeface(AndroidUtilities.bold());
        AnimatedTextView r110 = this.balanceTitle;
        int r4 = Theme.key_windowBackgroundWhiteBlackText;
        r110.setTextColor(Theme.getColor(r4, getResourceProvider()));
        this.balanceTitle.setTextSize(AndroidUtilities.dp(32.0f));
        this.balanceTitle.setGravity(17);
        this.balanceTitleSizeSpan = new RelativeSizeSpan(0.6770833f);
        this.balanceLayout.addView(this.balanceTitle, LayoutHelper.createLinear(-1, 38, 49, 22, 15, 22, 0));
        AnimatedTextView r111 = new AnimatedTextView(r34, true, true, true);
        this.balanceSubtitle = r111;
        r111.setGravity(17);
        AnimatedTextView r112 = this.balanceSubtitle;
        int r8 = Theme.key_windowBackgroundWhiteGrayText;
        r112.setTextColor(Theme.getColor(r8, getResourceProvider()));
        this.balanceSubtitle.setTextSize(AndroidUtilities.dp(14.0f));
        this.balanceLayout.addView(this.balanceSubtitle, LayoutHelper.createFrame(-1, 17.0f, 49, 22.0f, 4.0f, 22.0f, 0.0f));
        AnonymousClass3 r113 = new AnonymousClass3(this, r34);
        this.balanceEditTextContainer = r113;
        r113.setText(LocaleController.getString(R.string.BotStarsWithdrawPlaceholder));
        this.balanceEditTextContainer.setLeftPadding(AndroidUtilities.dp(36.0f));
        AnonymousClass4 r114 = new AnonymousClass4(this, r34);
        this.balanceEditText = r114;
        r114.setFocusable(false);
        this.balanceEditText.setTextColor(getThemedColor(r4));
        this.balanceEditText.setCursorSize(AndroidUtilities.dp(20.0f));
        this.balanceEditText.setCursorWidth(1.5f);
        this.balanceEditText.setBackground(null);
        this.balanceEditText.setTextSize(1, 18.0f);
        this.balanceEditText.setMaxLines(1);
        int r115 = AndroidUtilities.dp(16.0f);
        this.balanceEditText.setPadding(AndroidUtilities.dp(6.0f), r115, r115, r115);
        this.balanceEditText.setInputType(2);
        this.balanceEditText.setTypeface(Typeface.DEFAULT);
        this.balanceEditText.setHighlightColor(getThemedColor(Theme.key_chat_inTextSelectionHighlight));
        this.balanceEditText.setHandlesColor(getThemedColor(Theme.key_chat_TextSelectionCursor));
        EditTextBoldCursor r116 = this.balanceEditText;
        if (LocaleController.isRTL == false) goto L13;
        int r35 = 5;
    L14:
        r116.setGravity(r35);
        this.balanceEditText.setOnFocusChangeListener(new BotStarsActivity$$ExternalSyntheticLambda2(this));
        this.balanceEditText.addTextChangedListener(new AnonymousClass5(this));
        LinearLayout r117 = new LinearLayout(r34);
        r117.setOrientation(0);
        ImageView r36 = new ImageView(r34);
        r36.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        r36.setImageResource(R.drawable.star_small_inner);
        r117.addView(r36, LayoutHelper.createLinear(-2, -2, 0.0f, 19, 14, 0, 0, 0));
        r117.addView(this.balanceEditText, LayoutHelper.createLinear(-1, -2, 1.0f, 119));
        this.balanceEditTextContainer.attachEditText(this.balanceEditText);
        this.balanceEditTextContainer.addView(r117, LayoutHelper.createFrame(-1, -2, 48));
        this.balanceEditText.setOnEditorActionListener(new BotStarsActivity$$ExternalSyntheticLambda3(this));
        this.balanceLayout.addView(this.balanceEditTextContainer, LayoutHelper.createLinear(-1, -2, 1, 18, 14, 18, 2));
        this.balanceEditTextContainer.setVisibility(8);
        LinearLayout r118 = new LinearLayout(r34);
        this.balanceButtonsLayout = r118;
        r118.setOrientation(0);
        ButtonWithCounterView r119 = new AnonymousClass6(this, r34, getResourceProvider()).setRound();
        this.balanceButton = r119;
        r119.setEnabled(MessagesController.getInstance(this.currentAccount).channelRevenueWithdrawalEnabled);
        this.balanceButton.setText(LocaleController.getString(R.string.BotStarsButtonWithdrawShortAll), false);
        this.balanceButton.setOnClickListener(new BotStarsActivity$$ExternalSyntheticLambda4(this));
        ButtonWithCounterView r120 = new ButtonWithCounterView(r34, getResourceProvider()).setRound();
        this.adsButton = r120;
        r120.setEnabled(true);
        this.adsButton.setText(LocaleController.getString(R.string.MonetizationStarsAds), false);
        this.adsButton.setOnClickListener(new BotStarsActivity$$ExternalSyntheticLambda5(this, r34));
        this.balanceButtonsLayout.addView(this.balanceButton, LayoutHelper.createLinear(-1, 48, 1.0f, 119));
        if (this.self == true) goto L17;
        this.balanceButtonsLayout.addView(new Space(r34), LayoutHelper.createLinear(8, 48, 0.0f, 119));
        this.balanceButtonsLayout.addView(this.adsButton, LayoutHelper.createLinear(-1, 48, 1.0f, 119));
    L17:
        this.balanceLayout.addView(this.balanceButtonsLayout, LayoutHelper.createFrame(-1, 48.0f, 55, 18.0f, 13.0f, 18.0f, 0.0f));
        AnonymousClass7 r121 = new AnonymousClass7(this, r34);
        this.tonBalanceLayout = r121;
        r121.setOrientation(1);
        this.tonBalanceLayout.setBackgroundColor(Theme.getColor(r142, this.resourceProvider));
        this.tonBalanceLayout.setPadding(0, 0, 0, AndroidUtilities.dp(17.0f));
        AnimatedTextView r122 = new AnimatedTextView(r34, false, true, true);
        this.tonBalanceTitle = r122;
        r122.setTypeface(AndroidUtilities.bold());
        this.tonBalanceTitle.setTextColor(Theme.getColor(r4, this.resourceProvider));
        this.tonBalanceTitle.setTextSize(AndroidUtilities.dp(32.0f));
        this.tonBalanceTitle.setGravity(17);
        this.tonBalanceTitleSizeSpan = new RelativeSizeSpan(0.6770833f);
        this.tonBalanceLayout.addView(this.tonBalanceTitle, LayoutHelper.createLinear(-1, 38, 49, 22, 15, 22, 0));
        AnimatedTextView r123 = new AnimatedTextView(r34, true, true, true);
        this.tonBalanceSubtitle = r123;
        r123.setGravity(17);
        this.tonBalanceSubtitle.setTextColor(Theme.getColor(r8, this.resourceProvider));
        this.tonBalanceSubtitle.setTextSize(AndroidUtilities.dp(14.0f));
        this.tonBalanceLayout.addView(this.tonBalanceSubtitle, LayoutHelper.createFrame(-1, 17.0f, 49, 22.0f, 4.0f, 22.0f, 0.0f));
        ButtonWithCounterView r124 = new ButtonWithCounterView(r34, this.resourceProvider);
        this.tonBalanceButton = r124;
        r124.setEnabled(MessagesController.getInstance(this.currentAccount).channelRevenueWithdrawalEnabled);
        ButtonWithCounterView r125 = this.tonBalanceButton;
        if (this.self == false) goto L20;
        int r2 = R.string.MonetizationSelfWithdraw;
    L21:
        r125.setText(LocaleController.getString(r2), false);
        this.tonBalanceButton.setVisibility(8);
        this.tonBalanceButton.setOnClickListener(new BotStarsActivity$$ExternalSyntheticLambda6(this));
        this.tonBalanceLayout.addView(this.tonBalanceButton, LayoutHelper.createFrame(-1, 48.0f, 55, 18.0f, 13.0f, 18.0f, 0.0f));
        UniversalRecyclerView r126 = new UniversalRecyclerView(this, new BotStarsActivity$$ExternalSyntheticLambda7(this), new BotStarsActivity$$ExternalSyntheticLambda8(this), new BotStarsActivity$$ExternalSyntheticLambda9(this));
        this.listView = r126;
        r126.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundGray));
        this.listView.setSections();
        r9.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setOnScrollListener(new AnonymousClass8(this));
        this.actionBar.setAdaptiveBackground(this.listView);
        this.fragmentView = r9;
        return r9;
    L20:
        r2 = R.string.MonetizationWithdraw;
        goto L21
    L13:
        r35 = 3;
        goto L14
    L9:
        r32.setSubtitle(LocaleController.getString(R.string.BotStatsTON));
        goto L10
    L5:
        r17 = 0.0f;
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 == NotificationCenter.botStarsUpdated) goto L5;
        return;
    L5:
        if (((Long) r5[0]).longValue() != this.bot_id) goto L9;
        checkStats();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (AndroidUtilities.computePerceivedBrightness(Theme.getColor(Theme.key_windowBackgroundWhite)) <= 0.721f) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isLoadingVisible() {
        int r1 = 0;
    L4:
        if (r1 >= this.listView.getChildCount()) goto L10;
        if ((this.listView.getChildAt(r1) instanceof FlickerLoadingView) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.botStarsUpdated);
        checkStats();
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.botStarsUpdated);
        super.onFragmentDestroy();
    }
}
