package org.telegram.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.RelativeSizeSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
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
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Charts.data.ChartData;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.OutlineTextContainerView;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.ViewPagerFixed;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Stars.BotStarsActivity;
import org.telegram.ui.Stars.BotStarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.StatisticActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.bots.AffiliateProgramFragment;
import org.telegram.ui.bots.ChannelAffiliateProgramsFragment;

/* loaded from: classes6.dex */
public class ChannelMonetizationLayout extends SizeNotifierFrameLayout implements NestedScrollingParent3 {
    public static ChannelMonetizationLayout instance;
    private static HashMap<Integer, SpannableString> tonString;
    private ActionBar actionBar;
    private final ProceedOverview availableValue;
    private final ButtonWithCounterView balanceButton;
    private final CharSequence balanceInfo;
    private final LinearLayout balanceLayout;
    private final AnimatedTextView balanceSubtitle;
    private final AnimatedTextView balanceTitle;
    private final RelativeSizeSpan balanceTitleSizeSpan;
    private TL_stories.TL_premium_boostsStatus boostsStatus;
    private final int currentAccount;
    private int currentBoostLevel;
    public final long dialogId;
    private DecimalFormat formatter;
    private final BaseFragment fragment;
    public IBlur3Capture iBlur3Capture;
    private StatisticActivity.ChartViewData impressionsChart;
    private boolean initialSwitchOffValue;
    private final ProceedOverview lastWithdrawalValue;
    private final ProceedOverview lifetimeValue;
    public final UniversalRecyclerView listView;
    private SpannableStringBuilder lock;
    private NestedScrollingParentHelper nestedScrollingParentHelper;
    private boolean proceedsAvailable;
    private final CharSequence proceedsInfo;
    private final FrameLayout progress;
    private final Theme.ResourcesProvider resourcesProvider;
    private StatisticActivity.ChartViewData revenueChart;
    private final Runnable sendCpmUpdateRunnable;
    private Runnable setStarsBalanceButtonText;
    private int shakeDp;
    private ColoredImageSpan[] starRef;
    private final ButtonWithCounterView starsAdsButton;
    private TL_stars.StarsAmount starsBalance;
    private int starsBalanceBlockedUntil;
    private final ButtonWithCounterView starsBalanceButton;
    private final LinearLayout starsBalanceButtonsLayout;
    private EditTextBoldCursor starsBalanceEditText;
    private boolean starsBalanceEditTextAll;
    private OutlineTextContainerView starsBalanceEditTextContainer;
    private boolean starsBalanceEditTextIgnore;
    private long starsBalanceEditTextValue;
    private final CharSequence starsBalanceInfo;
    private final LinearLayout starsBalanceLayout;
    private final AnimatedTextView starsBalanceSubtitle;
    private final AnimatedTextView starsBalanceTitle;
    private final RelativeSizeSpan starsBalanceTitleSizeSpan;
    public final boolean starsRevenueAvailable;
    private StatisticActivity.ChartViewData starsRevenueChart;
    private double stars_rate;
    private boolean switchOffValue;
    private final CharSequence titleInfo;
    public final boolean tonRevenueAvailable;
    private double ton_rate;
    private final ChannelTransactionsView transactionsLayout;
    private Bulletin withdrawalBulletin;

    /* renamed from: org.telegram.ui.ChannelMonetizationLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends LinearLayout {
        final /* synthetic */ ChannelMonetizationLayout this$0;

        public AnonymousClass1(ChannelMonetizationLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }
    }

    /* renamed from: org.telegram.ui.ChannelMonetizationLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends LinearLayout {
        final /* synthetic */ ChannelMonetizationLayout this$0;

        public AnonymousClass2(ChannelMonetizationLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }
    }

    /* renamed from: org.telegram.ui.ChannelMonetizationLayout$3, reason: invalid class name */
    public class AnonymousClass3 extends OutlineTextContainerView {
        final /* synthetic */ ChannelMonetizationLayout this$0;

        public AnonymousClass3(ChannelMonetizationLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (ChannelMonetizationLayout.m7355$$Nest$fgetstarsBalanceEditText(this.this$0) == null) goto L13;
            if (ChannelMonetizationLayout.m7355$$Nest$fgetstarsBalanceEditText(this.this$0).isFocusable() == true) goto L13;
            ChannelMonetizationLayout.m7355$$Nest$fgetstarsBalanceEditText(this.this$0).setFocusable(true);
            ChannelMonetizationLayout.m7355$$Nest$fgetstarsBalanceEditText(this.this$0).setFocusableInTouchMode(true);
            int r0 = this.this$0.listView.findPositionByItemId(3);
            if (r0 >= 0) goto L9;
        L11:
            ChannelMonetizationLayout.m7355$$Nest$fgetstarsBalanceEditText(this.this$0).requestFocus();
            goto L13
        L9:
            if (r0 >= this.this$0.listView.adapter.getItemCount()) goto L11;
            this.this$0.listView.stopScroll();
            this.this$0.listView.smoothScrollToPosition(r0);
        L13:
            return super.dispatchTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.ChannelMonetizationLayout$4, reason: invalid class name */
    public class AnonymousClass4 extends EditTextBoldCursor {
        final /* synthetic */ ChannelMonetizationLayout this$0;

        public AnonymousClass4(ChannelMonetizationLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            AndroidUtilities.hideKeyboard(this);
        }
    }

    /* renamed from: org.telegram.ui.ChannelMonetizationLayout$5, reason: invalid class name */
    public class AnonymousClass5 implements TextWatcher {
        final /* synthetic */ ChannelMonetizationLayout this$0;

        public AnonymousClass5(ChannelMonetizationLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r7) {
            if (ChannelMonetizationLayout.m7356$$Nest$fgetstarsBalanceEditTextIgnore(this.this$0) == false) goto L5;
            return;
        L5:
            ChannelMonetizationLayout r0 = this.this$0;
            if (TextUtils.isEmpty(r7) == false) goto L8;
            long r1 = 0;
        L9:
            ChannelMonetizationLayout.m7360$$Nest$fputstarsBalanceEditTextValue(r0, r1);
            boolean r02 = true;
            if (ChannelMonetizationLayout.m7357$$Nest$fgetstarsBalanceEditTextValue(this.this$0) <= ChannelMonetizationLayout.m7354$$Nest$fgetstarsBalance(this.this$0).amount) goto L12;
            ChannelMonetizationLayout r72 = this.this$0;
            ChannelMonetizationLayout.m7360$$Nest$fputstarsBalanceEditTextValue(r72, ChannelMonetizationLayout.m7354$$Nest$fgetstarsBalance(r72).amount);
            ChannelMonetizationLayout.m7359$$Nest$fputstarsBalanceEditTextIgnore(this.this$0, true);
            ChannelMonetizationLayout.m7355$$Nest$fgetstarsBalanceEditText(this.this$0).setText(Long.toString(ChannelMonetizationLayout.m7357$$Nest$fgetstarsBalanceEditTextValue(this.this$0)));
            ChannelMonetizationLayout.m7355$$Nest$fgetstarsBalanceEditText(this.this$0).setSelection(ChannelMonetizationLayout.m7355$$Nest$fgetstarsBalanceEditText(this.this$0).getText().length());
            ChannelMonetizationLayout.m7359$$Nest$fputstarsBalanceEditTextIgnore(this.this$0, false);
        L12:
            ChannelMonetizationLayout r73 = this.this$0;
            if (ChannelMonetizationLayout.m7357$$Nest$fgetstarsBalanceEditTextValue(r73) == ChannelMonetizationLayout.m7354$$Nest$fgetstarsBalance(this.this$0).amount) goto L16;
            r02 = false;
        L16:
            ChannelMonetizationLayout.m7358$$Nest$fputstarsBalanceEditTextAll(r73, r02);
            AndroidUtilities.cancelRunOnUIThread(ChannelMonetizationLayout.m7353$$Nest$fgetsetStarsBalanceButtonText(this.this$0));
            ChannelMonetizationLayout.m7353$$Nest$fgetsetStarsBalanceButtonText(this.this$0).run();
            ChannelMonetizationLayout.m7358$$Nest$fputstarsBalanceEditTextAll(this.this$0, false);
            return;
        L8:
            r1 = Long.parseLong(r7.toString());
            goto L9
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.ChannelMonetizationLayout$6, reason: invalid class name */
    public class AnonymousClass6 extends ButtonWithCounterView {
        final /* synthetic */ ChannelMonetizationLayout this$0;

        public AnonymousClass6(ChannelMonetizationLayout r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Stories.recorder.ButtonWithCounterView
        public boolean subTextSplitToWords() {
            return false;
        }
    }

    public class ChannelTransactionsView extends LinearLayout {
        private final PageAdapter adapter;
        private final int currentAccount;
        private final long dialogId;
        private boolean[] loadingTransactions;
        private String starsLastOffset;
        private final ArrayList<TL_stars.StarsTransaction> starsTransactions;
        private final ViewPagerFixed.TabsView tabsView;
        final /* synthetic */ ChannelMonetizationLayout this$0;
        private final ArrayList<TL_stars.StarsTransaction> tonTransactions;
        private String tonTransactionsLastOffset;
        private final Runnable updateParentList;
        private final ViewPagerFixed viewPager;

        public class Page extends FrameLayout {
            private final long bot_id;
            private final int currentAccount;
            private final UniversalRecyclerView listView;
            private final Runnable loadMore;
            private final Theme.ResourcesProvider resourcesProvider;
            final /* synthetic */ ChannelTransactionsView this$1;
            private final int type;

            /* renamed from: org.telegram.ui.ChannelMonetizationLayout$ChannelTransactionsView$Page$1, reason: invalid class name */
            public class AnonymousClass1 extends RecyclerView.OnScrollListener {
                final /* synthetic */ Page this$2;
                final /* synthetic */ Runnable val$loadMore;
                final /* synthetic */ ChannelTransactionsView val$this$1;

                public AnonymousClass1(Page r1, ChannelTransactionsView r2, Runnable r3) {
                    this.this$2 = r1;
                    this.val$this$1 = r2;
                    this.val$loadMore = r3;
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView r1, int r2, int r3) {
                    if (Page.m7369$$Nest$fgetlistView(this.this$2).canScrollVertically(1) == true) goto L5;
                L8:
                    this.val$loadMore.run();
                    return;
                L5:
                    if (this.this$2.isLoadingVisible() == true) goto L8;
                }
            }

            public static /* synthetic */ void $r8$lambda$811mX8YYSVnOV8c1A8h7oBp2abg(Page r0, ArrayList r1, UniversalAdapter r2) {
                r0.fillItems(r1, r2);
            }

            /* renamed from: $r8$lambda$Cy4n9sLZI-6gxp_e8XZoHGqluC4, reason: not valid java name */
            public static /* synthetic */ void m7368$r8$lambda$Cy4n9sLZI6gxp_e8XZoHGqluC4(Page r0, UItem r1, View r2, int r3, float r4, float r5) {
                r0.onClick(r1, r2, r3, r4, r5);
            }

            /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
            public static /* bridge */ /* synthetic */ UniversalRecyclerView m7369$$Nest$fgetlistView(Page r0) {
                return r0.listView;
            }

            public Page(ChannelTransactionsView r11, Context r12, long r13, int r15, int r16, int r17, Runnable r18, Theme.ResourcesProvider r19) {
                this.this$1 = r11;
                super(r12);
                this.type = r15;
                this.currentAccount = r16;
                this.bot_id = r13;
                this.resourcesProvider = r19;
                this.loadMore = r18;
                UniversalRecyclerView r1 = new UniversalRecyclerView(r12, r16, r17, true, new ChannelMonetizationLayout$ChannelTransactionsView$Page$$ExternalSyntheticLambda0(this), new ChannelMonetizationLayout$ChannelTransactionsView$Page$$ExternalSyntheticLambda1(this), null, r19);
                this.listView = r1;
                addView(r1, LayoutHelper.createFrame(-1, -1.0f));
                r1.setOnScrollListener(new AnonymousClass1(this, r11, r18));
            }

            private void fillItems(ArrayList<UItem> r6, UniversalAdapter r7) {
                int r72 = this.type;
                int r0 = 0;
                if (r72 != 0) goto L11;
                ArrayList r73 = ChannelTransactionsView.m7364$$Nest$fgetstarsTransactions(this.this$1);
                int r3 = r73.size();
            L5:
                if (r0 >= r3) goto L8;
                Object r4 = r73.get(r0);
                r0 = r0 + 1;
                r6.add(StarsIntroActivity.StarsTransactionView.Factory.asTransaction((TL_stars.StarsTransaction) r4, true));
                goto L5
            L8:
                if (TextUtils.isEmpty(ChannelTransactionsView.m7363$$Nest$fgetstarsLastOffset(this.this$1)) == true) goto L21;
                r6.add(UItem.asFlicker(r6.size(), 7));
                r6.add(UItem.asFlicker(r6.size(), 7));
                r6.add(UItem.asFlicker(r6.size(), 7));
                return;
            L21:
                return;
            L11:
                if (r72 != 1) goto L22;
                ArrayList r74 = ChannelTransactionsView.m7365$$Nest$fgettonTransactions(this.this$1);
                int r32 = r74.size();
            L13:
                if (r0 >= r32) goto L16;
                Object r42 = r74.get(r0);
                r0 = r0 + 1;
                r6.add(StarsIntroActivity.StarsTransactionView.Factory.asTransaction((TL_stars.StarsTransaction) r42, true));
                goto L13
            L16:
                if (TextUtils.isEmpty(ChannelTransactionsView.m7366$$Nest$fgettonTransactionsLastOffset(this.this$1)) == true) goto L23;
                r6.add(UItem.asFlicker(r6.size(), 7));
                r6.add(UItem.asFlicker(r6.size(), 7));
                r6.add(UItem.asFlicker(r6.size(), 7));
                return;
            L23:
                return;
            }

            private void onClick(UItem r8, View r9, int r10, float r11, float r12) {
                Object r92 = r8.object;
                if ((r92 instanceof TL_stars.StarsTransaction) == false) goto L7;
                StarsIntroActivity.showTransactionSheet(getContext(), true, ChannelTransactionsView.m7362$$Nest$fgetdialogId(this.this$1), this.currentAccount, (TL_stars.StarsTransaction) r8.object, this.resourcesProvider);
                return;
            L7:
                if ((r92 instanceof TL_stats.BroadcastRevenueTransaction) == false) goto L10;
                ChannelMonetizationLayout.showTransactionSheet(getContext(), this.currentAccount, (TL_stats.BroadcastRevenueTransaction) r8.object, ChannelTransactionsView.m7362$$Nest$fgetdialogId(this.this$1), this.resourcesProvider);
                return;
            }

            public void checkMore() {
                if (this.listView.canScrollVertically(1) == true) goto L5;
            L8:
                this.loadMore.run();
                return;
            L5:
                if (isLoadingVisible() == true) goto L8;
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

            @Override // android.view.ViewGroup, android.view.View
            public void onAttachedToWindow() {
                super.onAttachedToWindow();
                this.listView.adapter.update(false);
            }
        }

        public class PageAdapter extends ViewPagerFixed.Adapter {
            private final int classGuid;
            private final Context context;
            private final int currentAccount;
            private final long dialogId;
            private final ArrayList<UItem> items;
            private final Theme.ResourcesProvider resourcesProvider;
            final /* synthetic */ ChannelTransactionsView this$1;

            public static /* synthetic */ void $r8$lambda$CTMCgZLdNu0siy0Y9kaRjrXzWKU(PageAdapter r0, int r1) {
                r0.lambda$createView$0(r1);
            }

            public PageAdapter(ChannelTransactionsView r1, Context r2, int r3, long r4, int r6, Theme.ResourcesProvider r7) {
                this.this$1 = r1;
                this.items = new ArrayList();
                this.context = r2;
                this.currentAccount = r3;
                this.classGuid = r6;
                this.resourcesProvider = r7;
                this.dialogId = r4;
                fill();
            }

            private /* synthetic */ void lambda$createView$0(int r1) {
                ChannelTransactionsView.m7367$$Nest$mloadTransactions(this.this$1, r1);
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public void bindView(View r1, int r2, int r3) {
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public View createView(int r11) {
                return new Page(this.this$1, this.context, this.dialogId, r11, this.currentAccount, this.classGuid, new ChannelMonetizationLayout$ChannelTransactionsView$PageAdapter$$ExternalSyntheticLambda0(this, r11), this.resourcesProvider);
            }

            public void fill() {
                this.items.clear();
                if (ChannelTransactionsView.m7365$$Nest$fgettonTransactions(this.this$1).isEmpty() == true) goto L6;
                this.items.add(UItem.asSpace(1));
            L6:
                if (ChannelTransactionsView.m7364$$Nest$fgetstarsTransactions(this.this$1).isEmpty() == true) goto L9;
                this.items.add(UItem.asSpace(0));
                return;
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public int getItemCount() {
                return this.items.size();
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public /* bridge */ /* synthetic */ CharSequence getItemTitle(int r1) {
                return getItemTitle(r1);
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public String getItemTitle(int r1) {
                int r0 = getItemViewType(r1);
                if (r0 == 0) goto L11;
                if (r0 == 1) goto L9;
                return _UrlKt.FRAGMENT_ENCODE_SET;
            L9:
                return LocaleController.getString(R.string.MonetizationTransactionsTON);
            L11:
                return LocaleController.getString(R.string.MonetizationTransactionsStars);
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public int getItemViewType(int r2) {
                if (r2 >= 0) goto L4;
                return 1;
            L4:
                if (r2 < this.items.size()) goto L7;
                return 1;
            L7:
                return this.items.get(r2).intValue;
            }
        }

        public static /* synthetic */ void $r8$lambda$DpbOIbWUvudBR06qW4VYLeEASvw(ChannelTransactionsView r0, TLObject r1, int r2, TLRPC.TL_error r3, boolean r4, boolean r5) {
            r0.lambda$loadTransactions$2(r1, r2, r3, r4, r5);
        }

        /* renamed from: $r8$lambda$VFo2tFnwmfXfefc-MEOooByWDeE, reason: not valid java name */
        public static /* synthetic */ void m7361$r8$lambda$VFo2tFnwmfXfefcMEOooByWDeE(ChannelTransactionsView r0, TLObject r1, int r2, TLRPC.TL_error r3, boolean r4, boolean r5) {
            r0.lambda$loadTransactions$0(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$eafSbki6JpJtyaHAzsMrEEsWE1A(ChannelTransactionsView r0, int r1, boolean r2, boolean r3, TLObject r4, TLRPC.TL_error r5) {
            r0.lambda$loadTransactions$3(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$emIxKTzfzT4mOCWJ8dbfkSVwrWU(ChannelTransactionsView r0, int r1, boolean r2, boolean r3, TLObject r4, TLRPC.TL_error r5) {
            r0.lambda$loadTransactions$1(r1, r2, r3, r4, r5);
        }

        /* renamed from: -$$Nest$fgetdialogId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m7362$$Nest$fgetdialogId(ChannelTransactionsView r2) {
            return r2.dialogId;
        }

        /* renamed from: -$$Nest$fgetstarsLastOffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m7363$$Nest$fgetstarsLastOffset(ChannelTransactionsView r0) {
            return r0.starsLastOffset;
        }

        /* renamed from: -$$Nest$fgetstarsTransactions, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m7364$$Nest$fgetstarsTransactions(ChannelTransactionsView r0) {
            return r0.starsTransactions;
        }

        /* renamed from: -$$Nest$fgettonTransactions, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m7365$$Nest$fgettonTransactions(ChannelTransactionsView r0) {
            return r0.tonTransactions;
        }

        /* renamed from: -$$Nest$fgettonTransactionsLastOffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m7366$$Nest$fgettonTransactionsLastOffset(ChannelTransactionsView r0) {
            return r0.tonTransactionsLastOffset;
        }

        /* renamed from: -$$Nest$mloadTransactions, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m7367$$Nest$mloadTransactions(ChannelTransactionsView r0, int r1) {
            r0.loadTransactions(r1);
        }

        public ChannelTransactionsView(ChannelMonetizationLayout r10, Context r11, int r12, long r13, int r15, Runnable r16, Theme.ResourcesProvider r17) {
            this.this$0 = r10;
            super(r11);
            this.tonTransactionsLastOffset = _UrlKt.FRAGMENT_ENCODE_SET;
            this.tonTransactions = new ArrayList();
            this.starsTransactions = new ArrayList();
            this.starsLastOffset = _UrlKt.FRAGMENT_ENCODE_SET;
            this.loadingTransactions = new boolean[]{false, false};
            this.currentAccount = r12;
            this.dialogId = r13;
            this.updateParentList = r16;
            setOrientation(1);
            ViewPagerFixed r8 = new ViewPagerFixed(r11);
            this.viewPager = r8;
            PageAdapter r0 = new PageAdapter(this, r11, r12, r13, r15, r17);
            this.adapter = r0;
            r8.setAdapter(r0);
            ViewPagerFixed.TabsView r122 = r8.createTabsView(true, 3);
            this.tabsView = r122;
            View r132 = new View(r11);
            r132.setBackgroundColor(Theme.getColor(Theme.key_divider, r17));
            addView(r122, LayoutHelper.createLinear(-1, 48));
            addView(r132, LayoutHelper.createLinear(-1.0f, 1.0f / AndroidUtilities.density));
            addView(r8, LayoutHelper.createLinear(-1, -1));
            setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, r17));
            loadTransactions(1);
            loadTransactions(0);
        }

        private /* synthetic */ void lambda$loadTransactions$0(TLObject r3, int r4, TLRPC.TL_error r5, boolean r6, boolean r7) {
            if ((r3 instanceof TL_stars.StarsStatus) == false) goto L5;
            TL_stars.StarsStatus r32 = (TL_stars.StarsStatus) r3;
            MessagesController.getInstance(this.currentAccount).putUsers(r32.users, false);
            MessagesController.getInstance(this.currentAccount).putChats(r32.chats, false);
            this.tonTransactions.addAll(r32.history);
            this.tonTransactionsLastOffset = r32.next_offset;
            this.loadingTransactions[r4] = false;
            updateLists(true, true);
        L8:
            if (hasTransactions() == r6) goto L13;
            Runnable r33 = this.updateParentList;
            if (r33 == null) goto L13;
            r33.run();
        L13:
            if (hasTransactions(r4) == r7) goto L16;
            updateTabs();
            return;
        L16:
            return;
        L5:
            if (r5 == null) goto L8;
            BulletinFactory.showError(r5);
            goto L8
        }

        private /* synthetic */ void lambda$loadTransactions$1(int r8, boolean r9, boolean r10, TLObject r11, TLRPC.TL_error r12) {
            AndroidUtilities.runOnUIThread(new ChannelMonetizationLayout$ChannelTransactionsView$$ExternalSyntheticLambda3(this, r11, r8, r12, r9, r10));
        }

        private /* synthetic */ void lambda$loadTransactions$2(TLObject r3, int r4, TLRPC.TL_error r5, boolean r6, boolean r7) {
            if ((r3 instanceof TL_stars.StarsStatus) == false) goto L5;
            TL_stars.StarsStatus r32 = (TL_stars.StarsStatus) r3;
            MessagesController.getInstance(this.currentAccount).putUsers(r32.users, false);
            MessagesController.getInstance(this.currentAccount).putChats(r32.chats, false);
            this.starsTransactions.addAll(r32.history);
            this.starsLastOffset = r32.next_offset;
            this.loadingTransactions[r4] = false;
            updateLists(true, true);
        L8:
            if (hasTransactions() == r6) goto L13;
            Runnable r33 = this.updateParentList;
            if (r33 == null) goto L13;
            r33.run();
        L13:
            if (hasTransactions(r4) == r7) goto L16;
            updateTabs();
            return;
        L16:
            return;
        L5:
            if (r5 == null) goto L8;
            BulletinFactory.showError(r5);
            goto L8
        }

        private /* synthetic */ void lambda$loadTransactions$3(int r8, boolean r9, boolean r10, TLObject r11, TLRPC.TL_error r12) {
            AndroidUtilities.runOnUIThread(new ChannelMonetizationLayout$ChannelTransactionsView$$ExternalSyntheticLambda2(this, r11, r8, r12, r9, r10));
        }

        private void loadTransactions(int r9) {
            if (this.loadingTransactions[r9] == true) goto L31;
            boolean r0 = hasTransactions();
            boolean r1 = hasTransactions(r9);
            int r2 = 20;
            if (r9 == 1) goto L8;
            if (r9 == 0) goto L19;
            return;
        L19:
            if (this.starsLastOffset != null) goto L21;
            return;
        L21:
            if (this.this$0.starsRevenueAvailable == false) goto L33;
            this.loadingTransactions[r9] = true;
            TL_stars.TL_payments_getStarsTransactions r4 = new TL_stars.TL_payments_getStarsTransactions();
            r4.ton = false;
            r4.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r4.offset = this.starsLastOffset;
            if (this.starsTransactions.isEmpty() == false) goto L26;
            r2 = 5;
        L26:
            r4.limit = r2;
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r4, new ChannelMonetizationLayout$ChannelTransactionsView$$ExternalSyntheticLambda1(this, r9, r0, r1));
            return;
        L33:
            return;
        L8:
            if (this.tonTransactionsLastOffset != null) goto L10;
            return;
        L10:
            if (this.this$0.tonRevenueAvailable == false) goto L32;
            this.loadingTransactions[r9] = true;
            TL_stars.TL_payments_getStarsTransactions r5 = new TL_stars.TL_payments_getStarsTransactions();
            r5.ton = true;
            r5.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r5.offset = this.tonTransactionsLastOffset;
            if (this.tonTransactions.isEmpty() == false) goto L15;
            r2 = 5;
        L15:
            r5.limit = r2;
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r5, new ChannelMonetizationLayout$ChannelTransactionsView$$ExternalSyntheticLambda0(this, r9, r0, r1));
            return;
        L32:
            return;
        }

        private void updateLists(boolean r4, boolean r5) {
            int r0 = 0;
        L4:
            if (r0 >= this.viewPager.getViewPages().length) goto L11;
            View r1 = this.viewPager.getViewPages()[r0];
            if ((r1 instanceof Page) == false) goto L10;
            Page r12 = (Page) r1;
            Page.m7369$$Nest$fgetlistView(r12).adapter.update(r4);
            if (r5 == false) goto L10;
            r12.checkMore();
        L10:
            r0 = r0 + 1;
            goto L4
        }

        private void updateTabs() {
            this.adapter.fill();
            this.viewPager.fillTabs(false);
            this.viewPager.updateCurrent();
        }

        public RecyclerListView getCurrentListView() {
            View r1 = this.viewPager.getCurrentView();
            if ((r1 instanceof Page) == true) goto L7;
            return null;
        L7:
            return Page.m7369$$Nest$fgetlistView((Page) r1);
        }

        public boolean hasTransactions() {
            if (this.tonTransactions.isEmpty() == true) goto L5;
            return true;
        L5:
            if (this.starsTransactions.isEmpty() == false) goto L11;
            return false;
        L11:
            return true;
        }

        public boolean hasTransactions(int r2) {
            if (r2 != 1) goto L7;
            boolean r1 = this.tonTransactions.isEmpty();
        L6:
            return !r1;
        L7:
            if (r2 != 0) goto L9;
            r1 = this.starsTransactions.isEmpty();
            goto L6
        L9:
            return false;
        }

        public void reloadTransactions() {
            boolean r0 = hasTransactions();
            int r2 = 0;
        L4:
            if (r2 >= 2) goto L14;
            if (this.loadingTransactions[r2] == true) goto L25;
            if (r2 != 1) goto L11;
            this.tonTransactions.clear();
            this.tonTransactionsLastOffset = _UrlKt.FRAGMENT_ENCODE_SET;
        L12:
            this.loadingTransactions[r2] = false;
            loadTransactions(r2);
            r2 = r2 + 1;
            goto L4
        L11:
            this.starsTransactions.clear();
            this.starsLastOffset = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L12
        L25:
            return;
        L14:
            if (hasTransactions() != r0) goto L16;
            return;
        L16:
            if (this.updateParentList == null) goto L24;
            updateTabs();
            this.updateParentList.run();
            return;
        }
    }

    public static class FeatureCell extends FrameLayout {
        public FeatureCell(Context r13, int r14, CharSequence r15, CharSequence r16, Theme.ResourcesProvider r17) {
            super(r13);
            ImageView r1 = new ImageView(r13);
            r1.setScaleType(ImageView.ScaleType.CENTER);
            int r3 = Theme.key_windowBackgroundWhiteBlackText;
            r1.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r3, r17), PorterDuff.Mode.SRC_IN));
            r1.setImageResource(r14);
            addView(r1, LayoutHelper.createFrame(24, 24.0f, 51, 0.0f, 5.0f, 18.0f, 0.0f));
            LinearLayout r12 = new LinearLayout(r13);
            r12.setOrientation(1);
            addView(r12, LayoutHelper.createFrame(-1, -2.0f, 55, 42.0f, 0.0f, 0.0f, 0.0f));
            LinkSpanDrawable.LinksTextView r122 = new LinkSpanDrawable.LinksTextView(r13);
            r122.setTypeface(AndroidUtilities.bold());
            r122.setTextSize(1, 14.0f);
            r122.setTextColor(Theme.getColor(r3, r17));
            int r32 = Theme.key_chat_messageLinkIn;
            r122.setLinkTextColor(Theme.getColor(r32, r17));
            r122.setText(r15);
            r12.addView(r122, LayoutHelper.createLinear(-1, -2, 55, 0, 0, 0, 2));
            LinkSpanDrawable.LinksTextView r123 = new LinkSpanDrawable.LinksTextView(r13);
            r123.setTextSize(1, 14.0f);
            r123.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r17));
            r123.setLinkTextColor(Theme.getColor(r32, r17));
            r123.setText(r16);
            r12.addView(r123, LayoutHelper.createLinear(-1, -2, 55, 0, 0, 0, 0));
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(r3), AndroidUtilities.dp(325.0f)), View.MeasureSpec.getMode(r3)), r4);
        }
    }

    /* loaded from: classes.dex */
    public static class ProceedOverview {
        public long amount;
        public long amount2;
        public boolean contains1;
        public boolean contains2;
        public long crypto_amount;
        public TL_stars.StarsAmount crypto_amount2;
        public String crypto_currency;
        public String crypto_currency2;
        public String currency;
        public CharSequence text;

        public ProceedOverview() {
            this.contains1 = true;
            this.crypto_amount2 = TL_stars.StarsAmount.ofStars(0);
        }

        public static ProceedOverview as(String r1, CharSequence r2) {
            ProceedOverview r0 = new ProceedOverview();
            r0.crypto_currency = r1;
            r0.text = r2;
            return r0;
        }

        public static ProceedOverview as(String r2, String r3, CharSequence r4) {
            ProceedOverview r0 = new ProceedOverview();
            r0.contains1 = false;
            r0.crypto_currency = r2;
            r0.crypto_currency2 = r3;
            r0.text = r4;
            return r0;
        }
    }

    public static class ProceedOverviewCell extends LinearLayout {
        private final LinearLayout[] amountContainer;
        private final TextView[] amountView;
        private final AnimatedEmojiSpan.TextViewEmojis[] cryptoAmountView;
        private final DecimalFormat formatter;
        private final LinearLayout layout;
        private final Theme.ResourcesProvider resourcesProvider;
        private final TextView titleView;

        public ProceedOverviewCell(Context r21, Theme.ResourcesProvider r22) {
            super(r21);
            this.amountContainer = new LinearLayout[2];
            this.cryptoAmountView = new AnimatedEmojiSpan.TextViewEmojis[2];
            this.amountView = new TextView[2];
            this.resourcesProvider = r22;
            setOrientation(1);
            LinearLayout r5 = new LinearLayout(r21);
            this.layout = r5;
            r5.setOrientation(1);
            addView(r5, LayoutHelper.createLinear(-1, -2, 22.0f, 9.0f, 22.0f, 0.0f));
            int r6 = 0;
        L3:
            if (r6 >= 2) goto L5;
            this.amountContainer[r6] = new LinearLayout(r21);
            this.amountContainer[r6].setOrientation(0);
            this.layout.addView(this.amountContainer[r6], LayoutHelper.createLinear(-1, -2, 1.0f, 119));
            this.cryptoAmountView[r6] = new AnimatedEmojiSpan.TextViewEmojis(r21);
            this.cryptoAmountView[r6].setTypeface(AndroidUtilities.bold());
            this.cryptoAmountView[r6].setTextSize(1, 16.0f);
            this.cryptoAmountView[r6].setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r22));
            this.amountContainer[r6].addView(this.cryptoAmountView[r6], LayoutHelper.createLinear(-2, -2, 80, 0, 0, 5, 0));
            this.amountView[r6] = new AnimatedEmojiSpan.TextViewEmojis(r21);
            this.amountView[r6].setTextSize(1, 11.5f);
            this.amountView[r6].setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r22));
            this.amountContainer[r6].addView(this.amountView[r6], LayoutHelper.createLinear(-2, -2, 80));
            r6 = r6 + 1;
            goto L3
        L5:
            TextView r62 = new TextView(r21);
            this.titleView = r62;
            r62.setTextSize(1, 13.0f);
            r62.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r22));
            addView(r62, LayoutHelper.createLinear(-1, -2, 55, 22, 5, 22, 9));
            DecimalFormatSymbols r1 = new DecimalFormatSymbols(Locale.US);
            r1.setDecimalSeparator('.');
            DecimalFormat r2 = new DecimalFormat("#.##", r1);
            this.formatter = r2;
            r2.setMinimumFractionDigits(2);
            r2.setMaximumFractionDigits(12);
            r2.setGroupingUsed(false);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r7v6, types: [java.lang.CharSequence] */
        public void set(ProceedOverview r19) {
            this.titleView.setText(r19.text);
            int r3 = 0;
        L4:
            if (r3 >= 2) goto L42;
            if (r3 != 0) goto L7;
            String r4 = r19.crypto_currency;
        L8:
            if (r3 != 0) goto L10;
            long r5 = r19.amount;
        L12:
            if (r3 != 0) goto L17;
            if (r19.contains1 == true) goto L17;
            this.amountContainer[r3].setVisibility(8);
        L41:
            r3 = r3 + 1;
        L17:
            if (r3 == 1) goto L19;
        L21:
            SpannableStringBuilder r7 = new SpannableStringBuilder(r4 + " ");
            if ("TON".equalsIgnoreCase(r4) == false) goto L29;
            String r10 = this.formatter.format(r19.crypto_amount / 1.0E9d);
            int r12 = r10.indexOf(46);
            if (r12 < 0) goto L26;
            r7.append(LocaleController.formatNumber((long) Math.floor(r19.crypto_amount / 1.0E9d), ' '));
            r7.append(r10.substring(r12));
        L27:
            SpannableStringBuilder r72 = ChannelMonetizationLayout.replaceTON(r7, this.cryptoAmountView[r3].getPaint(), 1.05f, true);
        L35:
            SpannableStringBuilder r8 = new SpannableStringBuilder(r72);
            if ("TON".equalsIgnoreCase(r4) == false) goto L40;
            int r42 = TextUtils.indexOf(r8, ".");
            if (r42 < 0) goto L40;
            r8.setSpan(new RelativeSizeSpan(0.8125f), r42, r8.length(), 33);
        L40:
            this.amountContainer[r3].setVisibility(0);
            this.cryptoAmountView[r3].setText(r8);
            this.amountView[r3].setText("≈" + BillingController.getInstance().formatCurrency(r5, r19.currency));
            goto L41
        L26:
            r7.append(r10);
            goto L27
        L29:
            if ("XTR".equalsIgnoreCase(r4) == false) goto L34;
            if (r3 != 0) goto L32;
            r7.append(LocaleController.formatNumber(r19.crypto_amount, ' '));
        L33:
            r72 = StarsIntroActivity.replaceStarsWithPlain(r7, 0.7f);
            goto L35
        L32:
            r7.append(StarsIntroActivity.formatStarsAmount(r19.crypto_amount2, 0.8f, ' '));
            goto L33
        L34:
            r7.append(Long.toString(r19.crypto_amount));
            r72 = r7;
            goto L35
        L19:
            if (r19.contains2 == true) goto L21;
            this.amountContainer[r3].setVisibility(8);
            goto L41
        L10:
            r5 = r19.amount2;
            goto L12
        L7:
            r4 = r19.crypto_currency2;
            goto L8
        }
    }

    public static class TransactionCell extends FrameLayout {
        private final TextView dateView;
        private final DecimalFormat formatter;
        private final LinearLayout layout;
        private boolean needDivider;
        private final Theme.ResourcesProvider resourcesProvider;
        private final TextView titleView;
        private final AnimatedEmojiSpan.TextViewEmojis valueText;

        public TransactionCell(Context r12, Theme.ResourcesProvider r13) {
            super(r12);
            this.resourcesProvider = r13;
            LinearLayout r0 = new LinearLayout(r12);
            this.layout = r0;
            r0.setOrientation(1);
            addView(r0, LayoutHelper.createFrame(-1, -2.0f, 119, 17.0f, 9.0f, 130.0f, 9.0f));
            TextView r2 = new TextView(r12);
            this.titleView = r2;
            r2.setTextSize(1, 16.0f);
            r2.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r13));
            r0.addView(r2, LayoutHelper.createLinear(-1, -2));
            TextView r22 = new TextView(r12);
            this.dateView = r22;
            r22.setTextSize(1, 13.0f);
            r22.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r13));
            r0.addView(r22, LayoutHelper.createLinear(-1, -2, 0.0f, 4.0f, 0.0f, 0.0f));
            AnimatedEmojiSpan.TextViewEmojis r132 = new AnimatedEmojiSpan.TextViewEmojis(r12);
            this.valueText = r132;
            r132.setTypeface(AndroidUtilities.bold());
            r132.setTextSize(1, 13.0f);
            addView(r132, LayoutHelper.createFrame(-2, -2.0f, 21, 0.0f, 0.0f, 18.0f, 0.0f));
            DecimalFormatSymbols r122 = new DecimalFormatSymbols(Locale.US);
            r122.setDecimalSeparator('.');
            DecimalFormat r133 = new DecimalFormat("#.##", r122);
            this.formatter = r133;
            r133.setMinimumFractionDigits(2);
            r133.setMaximumFractionDigits(12);
            r133.setGroupingUsed(false);
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            super.onDraw(r8);
            if (this.needDivider == false) goto L21;
            Theme.ResourcesProvider r0 = this.resourcesProvider;
            if (r0 == null) goto L8;
            Paint r02 = r0.getPaint("paintDivider");
        L7:
            Paint r6 = r02;
            if (r6 != null) goto L11;
            return;
        L11:
            if (LocaleController.isRTL == false) goto L14;
            float r03 = 0.0f;
        L13:
            float r2 = r03;
            float r3 = getMeasuredHeight() - 1;
            int r04 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L18;
            int r1 = AndroidUtilities.dp(17.0f);
        L19:
            r8.drawLine(r2, r3, r04 - r1, getMeasuredHeight() - 1, r6);
            return;
        L18:
            r1 = 0;
            goto L19
        L14:
            r03 = AndroidUtilities.dp(17.0f);
            goto L13
        L8:
            r02 = Theme.dividerPaint;
            goto L7
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }

        public void set(TL_stats.BroadcastRevenueTransaction r9, boolean r10) {
            if ((r9 instanceof TL_stats.TL_broadcastRevenueTransactionWithdrawal) == false) goto L14;
            TL_stats.TL_broadcastRevenueTransactionWithdrawal r92 = (TL_stats.TL_broadcastRevenueTransactionWithdrawal) r9;
            this.titleView.setText(LocaleController.getString(R.string.MonetizationTransactionWithdraw));
            if (r92.pending == false) goto L7;
            this.dateView.setText(LocaleController.getString(R.string.MonetizationTransactionPending));
            boolean r0 = false;
        L12:
            long r3 = r92.amount;
            char r93 = 65535;
        L20:
            TextView r5 = this.dateView;
            if (r0 == false) goto L23;
            int r02 = Theme.key_text_RedRegular;
        L24:
            r5.setTextColor(Theme.getColor(r02, this.resourcesProvider));
            SpannableStringBuilder r03 = new SpannableStringBuilder();
            if (r93 >= 0) goto L27;
            String r52 = "-";
        L28:
            r03.append(r52);
            r03.append("TON ");
            r03.append(this.formatter.format(Math.abs(r3) / 1.0E9d));
            int r32 = TextUtils.indexOf(r03, ".");
            if (r32 < 0) goto L31;
            r03.setSpan(new RelativeSizeSpan(1.15f), 0, r32 + 1, 33);
        L31:
            AnimatedEmojiSpan.TextViewEmojis r33 = this.valueText;
            r33.setText(ChannelMonetizationLayout.replaceTON(r03, r33.getPaint(), 1.1f, AndroidUtilities.dp(0.33f), false));
            AnimatedEmojiSpan.TextViewEmojis r04 = this.valueText;
            if (r93 >= 0) goto L34;
            int r94 = Theme.key_text_RedBold;
        L35:
            r04.setTextColor(Theme.getColor(r94, this.resourcesProvider));
            this.needDivider = r10;
            setWillNotDraw(!r10);
            return;
        L34:
            r94 = Theme.key_avatar_nameInMessageGreen;
            goto L35
        L27:
            r52 = "+";
            goto L28
        L23:
            r02 = Theme.key_windowBackgroundWhiteGrayText;
            goto L24
        L7:
            r0 = r92.failed;
            TextView r34 = this.dateView;
            StringBuilder r4 = new StringBuilder();
            r4.append(LocaleController.formatShortDateTime(r92.date));
            if (r0 == false) goto L10;
            String r53 = " — " + LocaleController.getString(R.string.MonetizationTransactionNotCompleted);
        L11:
            r4.append(r53);
            r34.setText(r4.toString());
            goto L12
        L10:
            r53 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L11
        L14:
            if ((r9 instanceof TL_stats.TL_broadcastRevenueTransactionProceeds) == false) goto L18;
            this.titleView.setText(LocaleController.getString(R.string.MonetizationTransactionProceed));
            this.dateView.setText(LocaleController.formatShortDateTime(r9.from_date) + " - " + LocaleController.formatShortDateTime(r9.to_date));
            r3 = ((TL_stats.TL_broadcastRevenueTransactionProceeds) r9).amount;
        L16:
            r93 = 1;
            r0 = false;
            goto L20
        L18:
            if ((r9 instanceof TL_stats.TL_broadcastRevenueTransactionRefund) == false) goto L37;
            this.titleView.setText(LocaleController.getString(R.string.MonetizationTransactionRefund));
            this.dateView.setText(LocaleController.formatShortDateTime(r9.from_date));
            r3 = ((TL_stats.TL_broadcastRevenueTransactionRefund) r9).amount;
            goto L16
        }
    }

    /* renamed from: $r8$lambda$-iHdSay6NanFpsCVCBmnYeWjMtg, reason: not valid java name */
    public static /* synthetic */ void m7338$r8$lambda$iHdSay6NanFpsCVCBmnYeWjMtg(ChannelMonetizationLayout r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$initLevel$33(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$35LhSsHmoqabS1danaaXRjaE39U(ChannelMonetizationLayout r0) {
        r0.lambda$onNestedScroll$42();
    }

    public static /* synthetic */ void $r8$lambda$3YycWHVDF7yuIr8Jz1CCY3Z5cn4(ChannelMonetizationLayout r0, TL_stories.TL_premium_boostsStatus r1) {
        r0.lambda$initLevel$29(r1);
    }

    public static /* synthetic */ void $r8$lambda$3vksFnduStG984F4e7FfDJTgRuc(ChannelMonetizationLayout r0, Context r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$new$14(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$5uaN54agQU47PPmcIS8LXs-kHI8, reason: not valid java name */
    public static /* synthetic */ void m7339$r8$lambda$5uaN54agQU47PPmcIS8LXskHI8(ChannelMonetizationLayout r0, BaseFragment r1, TwoStepVerificationActivity r2) {
        r0.lambda$new$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$6dcny5dPEDXDtRAJ_iev3VGUzUY(ChannelMonetizationLayout r0, BaseFragment r1, TwoStepVerificationActivity r2) {
        r0.lambda$new$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$7nOnottnebEXAnJc8IhH0tSx7a8(ChannelMonetizationLayout r0, int r1, BaseFragment r2, View r3) {
        r0.lambda$new$11(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$AYEGyKdwyDsB8OOMd1oBQzIIPaM(ChannelMonetizationLayout r0, TwoStepVerificationActivity r1, TLRPC.InputCheckPasswordSRP r2) {
        r0.lambda$new$9(r1, r2);
    }

    /* renamed from: $r8$lambda$DIMgAs_KP7ffhYqcqfCcduiso-U, reason: not valid java name */
    public static /* synthetic */ void m7340$r8$lambda$DIMgAs_KP7ffhYqcqfCcduisoU(BaseFragment r1, Context r2, Theme.ResourcesProvider r3) {
        r1.showDialog(makeLearnSheet(r2, false, r3));
    }

    /* renamed from: $r8$lambda$EdSP6Y9DxUVL-jaAv9YbEZXKKD4, reason: not valid java name */
    public static /* synthetic */ void m7341$r8$lambda$EdSP6Y9DxUVLjaAv9YbEZXKKD4(ChannelMonetizationLayout r0) {
        r0.lambda$new$12();
    }

    public static /* synthetic */ void $r8$lambda$EhPaPbw0TXKCygyOBBd355tAAjU(ChannelMonetizationLayout r0, int r1) {
        r0.lambda$new$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$ErXuG7ppd3I6mYIrquM62n_93nQ(ChannelMonetizationLayout r0, TLRPC.TL_error r1, TwoStepVerificationActivity r2, Activity r3, boolean r4, TLObject r5) {
        r0.lambda$initWithdraw$23(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$Fovq5A9n2ic7O06kzhgUJonPB3Q(ChannelMonetizationLayout r0, TLRPC.TL_error r1, TLObject r2, TwoStepVerificationActivity r3, boolean r4) {
        r0.lambda$initWithdraw$21(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$JQfjSS_eH-fiCujTfaDc5gtT568, reason: not valid java name */
    public static /* synthetic */ void m7342$r8$lambda$JQfjSS_eHfiCujTfaDc5gtT568(ChannelMonetizationLayout r0, int r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$JXPyVz9gzPoBu9RLsP2y79ZO3k0(TLRPC.TL_error r0) {
        BulletinFactory.showError(r0);
    }

    /* renamed from: $r8$lambda$K6WqfVOWtXhINOL-ILkhHMNoD2g, reason: not valid java name */
    public static /* synthetic */ void m7343$r8$lambda$K6WqfVOWtXhINOLILkhHMNoD2g(ChannelMonetizationLayout r0) {
        r0.lambda$new$3();
    }

    public static /* synthetic */ void $r8$lambda$Ki3F_PPdL4v6xvGu07ZIZ5kH5Ho(BottomSheet r0, View r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$MoaiWRKa4DeE8HTbdR4E7JdfGnQ(ChannelMonetizationLayout r0, BaseFragment r1, View r2) {
        r0.lambda$new$6(r1, r2);
    }

    /* renamed from: $r8$lambda$OFcmI0mYXXh8N-2z4z8Y0dD5Q6k, reason: not valid java name */
    public static /* synthetic */ void m7344$r8$lambda$OFcmI0mYXXh8N2z4z8Y0dD5Q6k(ChannelMonetizationLayout r0, TL_stories.TL_premium_boostsStatus r1) {
        r0.lambda$initLevel$30(r1);
    }

    /* renamed from: $r8$lambda$OIj3e0e6pVs8fI1Iro0AXu-pLBs, reason: not valid java name */
    public static /* synthetic */ void m7345$r8$lambda$OIj3e0e6pVs8fI1Iro0AXupLBs(ChannelMonetizationLayout r0, TwoStepVerificationActivity r1, TLRPC.InputCheckPasswordSRP r2) {
        r0.lambda$new$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$PDx1hlcSxof_zzqbcD0a7QX4lDc(ChannelMonetizationLayout r0, TLRPC.TL_payments_starsRevenueStats r1) {
        r0.lambda$loadStarsStats$25(r1);
    }

    /* renamed from: $r8$lambda$PfS0bggCG6IlGKJdrr-SNhZ1pGw, reason: not valid java name */
    public static /* synthetic */ void m7346$r8$lambda$PfS0bggCG6IlGKJdrrSNhZ1pGw(BottomSheet r0, View r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$Qhur1K7XAySjUrCfZfd9DDDNhQE(ChannelMonetizationLayout r0) {
        r0.lambda$initLevel$31();
    }

    public static /* synthetic */ boolean $r8$lambda$UsvbA5PEibBYxuAMTsWuRabc0qQ(ChannelMonetizationLayout r0, UItem r1, View r2, int r3, float r4, float r5) {
        return r0.onLongClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$ZdXxzkW2ZM5ECAlLezmcuuquzX8(ChannelMonetizationLayout r0) {
        r0.lambda$new$1();
    }

    /* renamed from: $r8$lambda$eS8w-0evI-1GnKc4cT0Ik-aajzI, reason: not valid java name */
    public static /* synthetic */ void m7347$r8$lambda$eS8w0evI1GnKc4cT0IkaajzI(ChannelMonetizationLayout r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$epGEZICkfAmZMvu8QNhAtezYzk4(ChannelMonetizationLayout r0, TLObject r1) {
        r0.lambda$initLevel$32(r1);
    }

    public static /* synthetic */ void $r8$lambda$eqxJOa8uNPG_RPlkUQKPdXDXZeg(ChannelMonetizationLayout r0) {
        r0.lambda$sendCpmUpdate$36();
    }

    public static /* synthetic */ void $r8$lambda$g3ZZZxXT4YrtQCvhUrzMEuDnTS4(ChannelMonetizationLayout r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$hgkMifEaBDMs67Cpc6ytFJ6UkQ8(ChannelMonetizationLayout r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadStarsStats$27(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$jT0LycC7w8mU0gYEKWD4Hg6nCFA(ChannelMonetizationLayout r0, int r1, long r2, Context r4, View r5) {
        r0.lambda$new$15(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$jZKPLtNp18O01Gfl8ROX6KhqYjA(ChannelMonetizationLayout r0) {
        r0.lambda$applyStarsStats$28();
    }

    public static /* synthetic */ boolean $r8$lambda$kPKDfdcK_nu1ROzVy4kBKzLmHU8(ChannelMonetizationLayout r0, BaseFragment r1, TextView r2, int r3, KeyEvent r4) {
        return r0.lambda$new$18(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$lfUjRoZTX9vdsYj32NFwDQbM4z8(ChannelMonetizationLayout r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$sendCpmUpdate$37(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$nMyvJ9Bh6Z6_GtNUyrooh27RHBU(ChannelMonetizationLayout r0, View r1, boolean r2) {
        r0.lambda$new$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$pZXgeHD3q2_A8BuUaEtC2IhjJd8(ChannelMonetizationLayout r0, LimitReachedBottomSheet r1, ChannelBoostsController.CanApplyBoost r2) {
        r0.lambda$onClick$34(r1, r2);
    }

    /* renamed from: $r8$lambda$q0fjt0dGvq8pX0-knD4tOgLUic0, reason: not valid java name */
    public static /* synthetic */ void m7348$r8$lambda$q0fjt0dGvq8pX0knD4tOgLUic0(Context r0, TL_stats.TL_broadcastRevenueTransactionWithdrawal r1, View r2) {
        Browser.openUrl(r0, r1.transaction_url);
    }

    /* renamed from: $r8$lambda$qFtqr7Sj6y2oqS73vPaTXD-tXp0, reason: not valid java name */
    public static /* synthetic */ void m7349$r8$lambda$qFtqr7Sj6y2oqS73vPaTXDtXp0(ChannelMonetizationLayout r0, AlertDialog r1, int r2) {
        r0.lambda$initWithdraw$20(r1, r2);
    }

    /* renamed from: $r8$lambda$qOruqvRrUWAtOhBGvkgAQLz6-E4, reason: not valid java name */
    public static /* synthetic */ void m7350$r8$lambda$qOruqvRrUWAtOhBGvkgAQLz6E4(Context r0, boolean r1) {
        if (r1 == false) goto L4;
        int r12 = R.string.BotMonetizationInfoTONLink;
    L5:
        Browser.openUrl(r0, LocaleController.getString(r12));
        return;
    L4:
        r12 = R.string.MonetizationInfoTONLink;
        goto L5
    }

    public static /* synthetic */ void $r8$lambda$rcE3SDHwFf_1o7jiYRBsWHhAvr4(ChannelMonetizationLayout r0, TwoStepVerificationActivity r1, boolean r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$initWithdraw$22(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$rxRNiwiLU4D7PHnxHGydLOs-XTo, reason: not valid java name */
    public static /* synthetic */ void m7351$r8$lambda$rxRNiwiLU4D7PHnxHGydLOsXTo(ChannelMonetizationLayout r0) {
        r0.sendCpmUpdate();
    }

    public static /* synthetic */ void $r8$lambda$sg4VKXZIOhsW81Z2JL18QNHVYRo(ChannelMonetizationLayout r0, BaseFragment r1, TwoStepVerificationActivity r2) {
        r0.lambda$new$17(r1, r2);
    }

    /* renamed from: $r8$lambda$tOi1XS9p-6X2ZPYv2MAxgJ3-0PY, reason: not valid java name */
    public static /* synthetic */ void m7352$r8$lambda$tOi1XS9p6X2ZPYv2MAxgJ30PY(ChannelMonetizationLayout r0, TLObject r1, Context r2) {
        r0.lambda$new$13(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$w3f_dhk2k7EDV_SAndA2R9Ot1ZI(ChannelMonetizationLayout r0, int r1) {
        r0.lambda$new$19(r1);
    }

    public static /* synthetic */ void $r8$lambda$wl0KdogpbFFLm4SxoA3JahmNwqQ(ChannelMonetizationLayout r0, TwoStepVerificationActivity r1, Activity r2, boolean r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$initWithdraw$24(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$wvCpRYOWgpnsswvmohkeIT1HBTk(ChannelMonetizationLayout r0, TwoStepVerificationActivity r1, TLRPC.InputCheckPasswordSRP r2) {
        r0.lambda$new$16(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$x9N4XXhP668aFghCEbPDMrx9BHc(ChannelMonetizationLayout r0, TLObject r1) {
        r0.lambda$loadStarsStats$26(r1);
    }

    /* renamed from: -$$Nest$fgetsetStarsBalanceButtonText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m7353$$Nest$fgetsetStarsBalanceButtonText(ChannelMonetizationLayout r0) {
        return r0.setStarsBalanceButtonText;
    }

    /* renamed from: -$$Nest$fgetstarsBalance, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_stars.StarsAmount m7354$$Nest$fgetstarsBalance(ChannelMonetizationLayout r0) {
        return r0.starsBalance;
    }

    /* renamed from: -$$Nest$fgetstarsBalanceEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor m7355$$Nest$fgetstarsBalanceEditText(ChannelMonetizationLayout r0) {
        return r0.starsBalanceEditText;
    }

    /* renamed from: -$$Nest$fgetstarsBalanceEditTextIgnore, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7356$$Nest$fgetstarsBalanceEditTextIgnore(ChannelMonetizationLayout r0) {
        return r0.starsBalanceEditTextIgnore;
    }

    /* renamed from: -$$Nest$fgetstarsBalanceEditTextValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m7357$$Nest$fgetstarsBalanceEditTextValue(ChannelMonetizationLayout r2) {
        return r2.starsBalanceEditTextValue;
    }

    /* renamed from: -$$Nest$fputstarsBalanceEditTextAll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7358$$Nest$fputstarsBalanceEditTextAll(ChannelMonetizationLayout r0, boolean r1) {
        r0.starsBalanceEditTextAll = r1;
    }

    /* renamed from: -$$Nest$fputstarsBalanceEditTextIgnore, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7359$$Nest$fputstarsBalanceEditTextIgnore(ChannelMonetizationLayout r0, boolean r1) {
        r0.starsBalanceEditTextIgnore = r1;
    }

    /* renamed from: -$$Nest$fputstarsBalanceEditTextValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7360$$Nest$fputstarsBalanceEditTextValue(ChannelMonetizationLayout r0, long r1) {
        r0.starsBalanceEditTextValue = r1;
    }

    public ChannelMonetizationLayout(Context r28, BaseFragment r29, int r30, long r31, Theme.ResourcesProvider r33, boolean r34, boolean r35) {
        super(r28);
        this.shakeDp = 4;
        this.starsBalance = TL_stars.StarsAmount.ofStars(0);
        this.starRef = new ColoredImageSpan[1];
        this.starsBalanceEditTextIgnore = false;
        this.starsBalanceEditTextAll = true;
        this.switchOffValue = false;
        this.initialSwitchOffValue = false;
        this.proceedsAvailable = false;
        this.availableValue = ProceedOverview.as("TON", "XTR", LocaleController.getString(R.string.MonetizationOverviewAvailable));
        this.lastWithdrawalValue = ProceedOverview.as("TON", "XTR", LocaleController.getString(R.string.MonetizationOverviewLastWithdrawal));
        this.lifetimeValue = ProceedOverview.as("TON", "XTR", LocaleController.getString(R.string.MonetizationOverviewTotal));
        this.sendCpmUpdateRunnable = new ChannelMonetizationLayout$$ExternalSyntheticLambda2(this);
        this.nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        this.tonRevenueAvailable = r34;
        this.starsRevenueAvailable = r35;
        DecimalFormatSymbols r7 = new DecimalFormatSymbols(Locale.US);
        r7.setDecimalSeparator('.');
        DecimalFormat r12 = new DecimalFormat("#.##", r7);
        this.formatter = r12;
        r12.setMinimumFractionDigits(2);
        this.formatter.setMaximumFractionDigits(12);
        this.formatter.setGroupingUsed(false);
        this.fragment = r29;
        this.resourcesProvider = r33;
        this.currentAccount = r30;
        this.dialogId = r31;
        initLevel();
        TLRPC.Chat r122 = MessagesController.getInstance(r30).getChat(Long.valueOf(-r31));
        this.titleInfo = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.formatString(R.string.MonetizationInfo, new Object[]{50}), -1, 3, new ChannelMonetizationLayout$$ExternalSyntheticLambda8(r29, r28, r33), r33), true);
        if (MessagesController.getInstance(r30).channelRevenueWithdrawalEnabled == false) goto L5;
        int r72 = R.string.MonetizationBalanceInfo;
    L6:
        this.balanceInfo = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(r72), -1, 3, new ChannelMonetizationLayout$$ExternalSyntheticLambda9(this)), true);
        if (r35 == false) goto L10;
        if (r34 == false) goto L10;
        int r73 = R.string.MonetizationProceedsStarsTONInfo;
    L13:
        if (r35 == false) goto L16;
        if (r34 == false) goto L16;
        int r0 = R.string.MonetizationProceedsStarsTONInfoLink;
    L19:
        this.proceedsInfo = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(r73), -1, 3, new ChannelMonetizationLayout$$ExternalSyntheticLambda10(this, r0), r33), true);
        if (ChatObject.isChannelAndNotMegaGroup(r122) == false) goto L22;
        int r02 = R.string.MonetizationStarsInfo;
    L23:
        this.starsBalanceInfo = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(r02), new ChannelMonetizationLayout$$ExternalSyntheticLambda11(this)), true);
        int r14 = Theme.key_windowBackgroundGray;
        setBackgroundColor(Theme.getColor(r14, r33));
        this.transactionsLayout = new ChannelTransactionsView(this, r28, r30, r31, r29.getClassGuid(), new ChannelMonetizationLayout$$ExternalSyntheticLambda12(this), r33);
        AnonymousClass1 r03 = new AnonymousClass1(this, r28);
        this.balanceLayout = r03;
        r03.setOrientation(1);
        int r4 = Theme.key_windowBackgroundWhite;
        r03.setBackgroundColor(Theme.getColor(r4, r33));
        r03.setPadding(0, 0, 0, AndroidUtilities.dp(17.0f));
        AnimatedTextView r6 = new AnimatedTextView(r28, false, true, true);
        this.balanceTitle = r6;
        r6.setTypeface(AndroidUtilities.bold());
        int r74 = Theme.key_windowBackgroundWhiteBlackText;
        r6.setTextColor(Theme.getColor(r74, r33));
        r6.setTextSize(AndroidUtilities.dp(32.0f));
        r6.setGravity(17);
        this.balanceTitleSizeSpan = new RelativeSizeSpan(0.6770833f);
        r03.addView(r6, LayoutHelper.createLinear(-1, 38, 49, 22, 15, 22, 0));
        AnimatedTextView r62 = new AnimatedTextView(r28, true, true, true);
        this.balanceSubtitle = r62;
        r62.setGravity(17);
        int r13 = Theme.key_windowBackgroundWhiteGrayText;
        r62.setTextColor(Theme.getColor(r13, r33));
        r62.setTextSize(AndroidUtilities.dp(14.0f));
        r03.addView(r62, LayoutHelper.createFrame(-1, 17.0f, 49, 22.0f, 4.0f, 22.0f, 0.0f));
        ButtonWithCounterView r63 = new ButtonWithCounterView(r28, r33).setRound();
        this.balanceButton = r63;
        r63.setEnabled(MessagesController.getInstance(r30).channelRevenueWithdrawalEnabled);
        r63.setText(LocaleController.getString(R.string.MonetizationWithdraw), false);
        r63.setVisibility(8);
        r63.setOnClickListener(new ChannelMonetizationLayout$$ExternalSyntheticLambda13(this, r29));
        r03.addView(r63, LayoutHelper.createFrame(-1, 48.0f, 55, 18.0f, 13.0f, 18.0f, 0.0f));
        AnonymousClass2 r64 = new AnonymousClass2(this, r28);
        this.starsBalanceLayout = r64;
        r64.setOrientation(1);
        r64.setBackgroundColor(Theme.getColor(r4, r33));
        r64.setPadding(0, 0, 0, AndroidUtilities.dp(17.0f));
        AnimatedTextView r04 = new AnimatedTextView(r28, false, true, true);
        this.starsBalanceTitle = r04;
        r04.setTypeface(AndroidUtilities.bold());
        r04.setTextColor(Theme.getColor(r74, r33));
        r04.setTextSize(AndroidUtilities.dp(32.0f));
        r04.setGravity(17);
        this.starsBalanceTitleSizeSpan = new RelativeSizeSpan(0.6770833f);
        r64.addView(r04, LayoutHelper.createLinear(-1, 38, 49, 22, 15, 22, 0));
        AnimatedTextView r05 = new AnimatedTextView(r28, true, true, true);
        this.starsBalanceSubtitle = r05;
        r05.setGravity(17);
        r05.setTextColor(Theme.getColor(r13, r33));
        r05.setTextSize(AndroidUtilities.dp(14.0f));
        r64.addView(r05, LayoutHelper.createFrame(-1, 17.0f, 49, 22.0f, 4.0f, 22.0f, 0.0f));
        AnonymousClass3 r06 = new AnonymousClass3(this, r28);
        this.starsBalanceEditTextContainer = r06;
        r06.setVisibility(8);
        this.starsBalanceEditTextContainer.setText(LocaleController.getString(R.string.BotStarsWithdrawPlaceholder));
        this.starsBalanceEditTextContainer.setLeftPadding(AndroidUtilities.dp(36.0f));
        AnonymousClass4 r07 = new AnonymousClass4(this, r28);
        this.starsBalanceEditText = r07;
        r07.setFocusable(false);
        this.starsBalanceEditText.setTextColor(Theme.getColor(r74, r33));
        this.starsBalanceEditText.setCursorSize(AndroidUtilities.dp(20.0f));
        this.starsBalanceEditText.setCursorWidth(1.5f);
        this.starsBalanceEditText.setBackground(null);
        this.starsBalanceEditText.setTextSize(1, 18.0f);
        this.starsBalanceEditText.setMaxLines(1);
        int r08 = AndroidUtilities.dp(16.0f);
        this.starsBalanceEditText.setPadding(AndroidUtilities.dp(6.0f), r08, r08, r08);
        this.starsBalanceEditText.setInputType(2);
        this.starsBalanceEditText.setTypeface(Typeface.DEFAULT);
        this.starsBalanceEditText.setHighlightColor(Theme.getColor(Theme.key_chat_inTextSelectionHighlight, r33));
        this.starsBalanceEditText.setHandlesColor(Theme.getColor(Theme.key_chat_TextSelectionCursor, r33));
        EditTextBoldCursor r09 = this.starsBalanceEditText;
        if (LocaleController.isRTL == false) goto L26;
        int r132 = 5;
    L27:
        r09.setGravity(r132);
        this.starsBalanceEditText.setOnFocusChangeListener(new ChannelMonetizationLayout$$ExternalSyntheticLambda14(this));
        this.starsBalanceEditText.addTextChangedListener(new AnonymousClass5(this));
        LinearLayout r010 = new LinearLayout(r28);
        r010.setOrientation(0);
        ImageView r42 = new ImageView(r28);
        r42.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        r42.setImageResource(R.drawable.star_small_inner);
        r010.addView(r42, LayoutHelper.createLinear(-2, -2, 0.0f, 19, 14, 0, 0, 0));
        r010.addView(this.starsBalanceEditText, LayoutHelper.createLinear(-1, -2, 1.0f, 119));
        this.starsBalanceEditTextContainer.attachEditText(this.starsBalanceEditText);
        this.starsBalanceEditTextContainer.addView(r010, LayoutHelper.createFrame(-1, -2, 48));
        r64.addView(this.starsBalanceEditTextContainer, LayoutHelper.createLinear(-1, -2, 1, 18, 14, 18, 2));
        LinearLayout r75 = new LinearLayout(r28);
        this.starsBalanceButtonsLayout = r75;
        r75.setOrientation(0);
        ButtonWithCounterView r011 = new AnonymousClass6(this, r28, r33).setRound();
        this.starsBalanceButton = r011;
        r011.setEnabled(false);
        r011.setText(LocaleController.formatPluralString("MonetizationStarsWithdraw", 0, new Object[0]), false);
        r011.setVisibility(0);
        r011.setOnClickListener(new ChannelMonetizationLayout$$ExternalSyntheticLambda15(this, r30, r29));
        ButtonWithCounterView r43 = new ButtonWithCounterView(r28, r33).setRound();
        this.starsAdsButton = r43;
        r43.setEnabled(false);
        r43.setText(LocaleController.getString(R.string.MonetizationStarsAds), false);
        r43.setOnClickListener(new ChannelMonetizationLayout$$ExternalSyntheticLambda16(this, r30, r31, r28));
        r75.addView(r011, LayoutHelper.createLinear(-1, 48, 1.0f, 119));
        if (ChatObject.isChannelAndNotMegaGroup(r122) == false) goto L30;
        r75.addView(new Space(r28), LayoutHelper.createLinear(8, 48, 0.0f, 119));
        r75.addView(r43, LayoutHelper.createLinear(-1, 48, 1.0f, 119));
    L30:
        r64.addView(r75, LayoutHelper.createFrame(-1, 48.0f, 55, 18.0f, 13.0f, 18.0f, 0.0f));
        this.starsBalanceEditText.setOnEditorActionListener(new ChannelMonetizationLayout$$ExternalSyntheticLambda3(this, r29));
        this.setStarsBalanceButtonText = new ChannelMonetizationLayout$$ExternalSyntheticLambda4(this, r30);
        UniversalRecyclerView r012 = new UniversalRecyclerView(r29, new ChannelMonetizationLayout$$ExternalSyntheticLambda5(this), new ChannelMonetizationLayout$$ExternalSyntheticLambda6(this), new ChannelMonetizationLayout$$ExternalSyntheticLambda7(this));
        this.listView = r012;
        r012.setClipToPadding(false);
        r012.setSections();
        addView(r012);
        LinearLayout r013 = new LinearLayout(r28);
        r013.setOrientation(1);
        FrameLayout r44 = new FrameLayout(r28);
        this.progress = r44;
        r44.setBackgroundColor(Theme.getColor(r14, r33));
        r44.addView(r013, LayoutHelper.createFrame(-2, -2, 17));
        RLottieImageView r5 = new RLottieImageView(r28);
        r5.setAutoRepeat(true);
        r5.setAnimation(R.raw.statistic_preload, 120, 120);
        r5.playAnimation();
        TextView r65 = new TextView(r28);
        r65.setTextSize(1, 20.0f);
        r65.setTypeface(AndroidUtilities.bold());
        int r3 = Theme.key_player_actionBarTitle;
        r65.setTextColor(Theme.getColor(r3));
        r65.setTag(Integer.valueOf(r3));
        r65.setText(LocaleController.getString("LoadingStats", R.string.LoadingStats));
        r65.setGravity(1);
        TextView r76 = new TextView(r28);
        r76.setTextSize(1, 15.0f);
        int r2 = Theme.key_player_actionBarSubtitle;
        r76.setTextColor(Theme.getColor(r2));
        r76.setTag(Integer.valueOf(r2));
        r76.setText(LocaleController.getString(R.string.LoadingStatsDescription));
        r76.setGravity(1);
        r013.addView(r5, LayoutHelper.createLinear(120, 120, 1, 0, 0, 0, 20));
        r013.addView(r65, LayoutHelper.createLinear(-2, -2, 1, 0, 0, 0, 10));
        r013.addView(r76, LayoutHelper.createLinear(-2, -2, 1));
        addView(r44, LayoutHelper.createFrame(-1, -1, 119));
        return;
    L26:
        r132 = 3;
        goto L27
    L22:
        r02 = R.string.MonetizationStarsInfoGroup;
    L16:
        if (r35 == false) goto L18;
        r0 = R.string.MonetizationProceedsStarsInfoLink;
        goto L19
    L18:
        r0 = R.string.MonetizationProceedsTONInfoLink;
    L10:
        if (r35 == false) goto L12;
        r73 = R.string.MonetizationProceedsStarsInfo;
        goto L13
    L12:
        r73 = R.string.MonetizationProceedsTONInfo;
        goto L13
    L5:
        r72 = R.string.MonetizationBalanceInfoNotAvailable;
        goto L6
    }

    private void applyStarsStats(TLRPC.TL_payments_starsRevenueStats r8) {
        if (this.starsRevenueChart != null) goto L5;
        boolean r0 = true;
    L6:
        this.stars_rate = r8.usd_rate;
        StatisticActivity.ChartViewData r2 = StatisticActivity.createViewData(r8.revenue_graph, LocaleController.getString(R.string.MonetizationGraphStarsRevenue), 2);
        this.starsRevenueChart = r2;
        if (r2 == null) goto L17;
        ChartData r22 = r2.chartData;
        if (r22 == null) goto L17;
        ArrayList<ChartData.Line> r23 = r22.lines;
        if (r23 == null) goto L17;
        if (r23.isEmpty() == true) goto L17;
        if (this.starsRevenueChart.chartData.lines.get(0) == null) goto L17;
        this.starsRevenueChart.chartData.lines.get(0).colorKey = Theme.key_statisticChartLine_golden;
        this.starsRevenueChart.chartData.yRate = (float) ((1.0d / this.stars_rate) / 100.0d);
    L17:
        setupBalances(false, r8.status);
        if (this.tonRevenueAvailable == true) goto L22;
        FrameLayout r82 = this.progress;
        if (r82 == null) goto L22;
        r82.animate().alpha(0.0f).setDuration(380).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).withEndAction(new ChannelMonetizationLayout$$ExternalSyntheticLambda44(this)).start();
    L22:
        UniversalRecyclerView r83 = this.listView;
        if (r83 == null) goto L28;
        r83.adapter.update(!r0);
        if (r0 == false) goto L29;
        this.listView.scrollToPosition(0);
        return;
    L29:
        return;
    L28:
        return;
    L5:
        r0 = false;
        goto L6
    }

    private void checkLearnSheet() {
        if (isAttachedToWindow() == true) goto L5;
        return;
    L5:
        if (this.tonRevenueAvailable == true) goto L7;
        return;
    L7:
        if (this.proceedsAvailable == true) goto L9;
        return;
    L9:
        if (MessagesController.getGlobalMainSettings().getBoolean("monetizationadshint", true) == false) goto L15;
        this.fragment.showDialog(makeLearnSheet(getContext(), false, this.resourcesProvider));
        MessagesController.getGlobalMainSettings().edit().putBoolean("monetizationadshint", false).apply();
        return;
    }

    private void fillItems(ArrayList<UItem> r7, UniversalAdapter r8) {
        TLRPC.Chat r82 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
        TLRPC.ChatFull r0 = MessagesController.getInstance(this.currentAccount).getChatFull(-this.dialogId);
        if (r0 == null) goto L5;
        int r02 = r0.stats_dc;
    L7:
        if (this.tonRevenueAvailable == false) goto L19;
        r7.add(UItem.asCenterShadow(this.titleInfo));
        StatisticActivity.ChartViewData r2 = this.impressionsChart;
        if (r2 != null) goto L11;
    L13:
        StatisticActivity.ChartViewData r1 = this.revenueChart;
        if (r1 == null) goto L19;
        if (r1.isEmpty == true) goto L19;
        r7.add(UItem.asChart(2, r02, r1));
        r7.add(UItem.asShadow(-2, null));
        goto L19
    L11:
        if (r2.isEmpty == true) goto L13;
        r7.add(UItem.asChart(5, r02, r2));
        r7.add(UItem.asShadow(-1, null));
    L19:
        if (this.starsRevenueAvailable == false) goto L26;
        StatisticActivity.ChartViewData r12 = this.starsRevenueChart;
        if (r12 == null) goto L26;
        if (r12.isEmpty == true) goto L26;
        r7.add(UItem.asChart(2, r02, r12));
        r7.add(UItem.asShadow(-3, null));
    L26:
        if (this.proceedsAvailable == false) goto L29;
        r7.add(UItem.asBlackHeader(LocaleController.getString(R.string.MonetizationOverview)));
        r7.add(UItem.asProceedOverview(this.availableValue));
        r7.add(UItem.asProceedOverview(this.lastWithdrawalValue));
        r7.add(UItem.asProceedOverview(this.lifetimeValue));
        r7.add(UItem.asShadow(-4, this.proceedsInfo));
    L29:
        if (r82 == null) goto L48;
        if (r82.creator == false) goto L48;
        if (this.tonRevenueAvailable == false) goto L45;
        r7.add(UItem.asBlackHeader(LocaleController.getString(R.string.MonetizationBalance)));
        r7.add(UItem.asCustom(this.balanceLayout));
        r7.add(UItem.asShadow(-5, this.balanceInfo));
        int r83 = MessagesController.getInstance(this.currentAccount).channelRestrictSponsoredLevelMin;
        String r13 = LocaleController.getString(R.string.MonetizationSwitchOff);
        boolean r3 = false;
        if (this.currentBoostLevel >= r83) goto L37;
        int r22 = r83;
    L38:
        UItem r14 = UItem.asCheck(1, PeerColorActivity.withLevelLock(r13, r22));
        if (this.currentBoostLevel >= r83) goto L41;
    L43:
        r7.add(r14.setChecked(r3));
        r7.add(UItem.asShadow(-8, LocaleController.getString(R.string.MonetizationSwitchOffInfo)));
        goto L45
    L41:
        if (this.switchOffValue == false) goto L43;
        r3 = true;
        goto L43
    L37:
        r22 = 0;
    L45:
        if (this.starsRevenueAvailable == false) goto L48;
        r7.add(UItem.asBlackHeader(LocaleController.getString(R.string.MonetizationStarsBalance)));
        r7.add(UItem.asCustom(3, this.starsBalanceLayout));
        r7.add(UItem.asShadow(-6, this.starsBalanceInfo));
    L48:
        if (ChatObject.isChannelAndNotMegaGroup(MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId))) == false) goto L53;
        if (MessagesController.getInstance(this.currentAccount).starrefConnectAllowed == false) goto L53;
        r7.add(AffiliateProgramFragment.ColorfulTextCell.Factory.as(4, Theme.getColor(Theme.key_color_green, this.resourcesProvider), R.drawable.filled_earn_stars, ChatEditActivity.applyNewSpan(LocaleController.getString(R.string.ChannelAffiliateProgramRowTitle)), LocaleController.getString(R.string.ChannelAffiliateProgramRowText)));
        r7.add(UItem.asShadow(-7, null));
    L53:
        if (this.transactionsLayout.hasTransactions() == false) goto L56;
        r7.add(UItem.asFullscreenCustom(this.transactionsLayout, AndroidUtilities.dp(24.0f), true));
        return;
    L56:
        r7.add(UItem.asShadow(-10, null));
        return;
    L5:
        r02 = -1;
        goto L7
    }

    private void initLevel() {
        TLRPC.Chat r0 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
        if (r0 == null) goto L5;
        this.currentBoostLevel = r0.level;
    L5:
        MessagesController.getInstance(this.currentAccount).getBoostsController().getBoostsStats(this.dialogId, new ChannelMonetizationLayout$$ExternalSyntheticLambda28(this));
        loadStarsStats(false);
        if (this.tonRevenueAvailable == false) goto L12;
        TLRPC.TL_payments_getStarsRevenueStats r2 = new TLRPC.TL_payments_getStarsRevenueStats();
        r2.dark = Theme.isCurrentThemeDark();
        r2.ton = true;
        r2.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        TLRPC.ChatFull r02 = MessagesController.getInstance(this.currentAccount).getChatFull(-this.dialogId);
        if (r02 == null) goto L10;
        boolean r03 = r02.restricted_sponsored;
        this.switchOffValue = r03;
        this.initialSwitchOffValue = r03;
    L10:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r2, new ChannelMonetizationLayout$$ExternalSyntheticLambda29(this), null, null, 0, Integer.MAX_VALUE, 1, true);
        return;
    }

    private void initWithdraw(boolean r6, TLRPC.InputCheckPasswordSRP r7, TwoStepVerificationActivity r8) {
        BaseFragment r0 = this.fragment;
        if (r0 == null) goto L23;
        Activity r02 = r0.getParentActivity();
        TLRPC.User r1 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
        if (r02 == null) goto L22;
        if (r1 == null) goto L24;
        if (r6 == false) goto L15;
        TLRPC.TL_payments_getStarsRevenueWithdrawalUrl r12 = new TLRPC.TL_payments_getStarsRevenueWithdrawalUrl();
        r12.ton = false;
        r12.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        if (r7 != null) goto L14;
        r7 = new TLRPC.TL_inputCheckPasswordEmpty();
    L14:
        r12.password = r7;
        r12.flags |= 2;
        r12.amount = this.starsBalanceEditTextValue;
    L20:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r12, new ChannelMonetizationLayout$$ExternalSyntheticLambda32(this, r8, r02, r6));
        return;
    L15:
        r12 = new TLRPC.TL_payments_getStarsRevenueWithdrawalUrl();
        r12.ton = true;
        r12.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        if (r7 != null) goto L19;
        r7 = new TLRPC.TL_inputCheckPasswordEmpty();
    L19:
        r12.password = r7;
        goto L20
    L24:
        return;
    L22:
        return;
    }

    private /* synthetic */ void lambda$applyStarsStats$28() {
        this.progress.setVisibility(8);
    }

    private /* synthetic */ void lambda$initLevel$29(TL_stories.TL_premium_boostsStatus r1) {
        this.boostsStatus = r1;
        if (r1 == null) goto L5;
        this.currentBoostLevel = r1.level;
    L5:
        UniversalRecyclerView r0 = this.listView;
        if (r0 == null) goto L11;
        UniversalAdapter r02 = r0.adapter;
        if (r02 == null) goto L12;
        r02.update(true);
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$initLevel$30(TL_stories.TL_premium_boostsStatus r2) {
        AndroidUtilities.runOnUIThread(new ChannelMonetizationLayout$$ExternalSyntheticLambda33(this, r2));
    }

    private /* synthetic */ void lambda$initLevel$31() {
        this.progress.setVisibility(8);
    }

    private /* synthetic */ void lambda$initLevel$32(TLObject r6) {
        if ((r6 instanceof TLRPC.TL_payments_starsRevenueStats) == false) goto L12;
        TLRPC.TL_payments_starsRevenueStats r62 = (TLRPC.TL_payments_starsRevenueStats) r6;
        this.impressionsChart = StatisticActivity.createViewData(r62.top_hours_graph, LocaleController.getString(R.string.MonetizationGraphImpressions), 0);
        TL_stats.StatsGraph r0 = r62.revenue_graph;
        if (r0 == null) goto L7;
        r0.rate = (float) (1.0E7d / r62.usd_rate);
    L7:
        this.revenueChart = StatisticActivity.createViewData(r0, LocaleController.getString(R.string.MonetizationGraphRevenue), 2);
        StatisticActivity.ChartViewData r02 = this.impressionsChart;
        if (r02 == null) goto L10;
        r02.useHourFormat = true;
    L10:
        this.ton_rate = r62.usd_rate;
        setupBalances(true, r62.status);
        this.progress.animate().alpha(0.0f).setDuration(380).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).withEndAction(new ChannelMonetizationLayout$$ExternalSyntheticLambda42(this)).start();
        checkLearnSheet();
        return;
    }

    private /* synthetic */ void lambda$initLevel$33(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new ChannelMonetizationLayout$$ExternalSyntheticLambda37(this, r1));
    }

    private /* synthetic */ void lambda$initWithdraw$20(AlertDialog r2, int r3) {
        this.fragment.presentFragment(new TwoStepVerificationSetupActivity(6, null));
    }

    private /* synthetic */ void lambda$initWithdraw$21(TLRPC.TL_error r1, TLObject r2, TwoStepVerificationActivity r3, boolean r4) {
        if (r1 != null) goto L5;
        TL_account.Password r22 = (TL_account.Password) r2;
        r3.setCurrentPasswordInfo(null, r22);
        TwoStepVerificationActivity.initPasswordNewAlgo(r22);
        initWithdraw(r4, r3.getNewSrpPassword(), r3);
        return;
    }

    private /* synthetic */ void lambda$initWithdraw$22(TwoStepVerificationActivity r7, boolean r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new ChannelMonetizationLayout$$ExternalSyntheticLambda47(this, r10, r9, r7, r8));
    }

    private /* synthetic */ void lambda$initWithdraw$23(TLRPC.TL_error r29, TwoStepVerificationActivity r30, Activity r31, boolean r32, TLObject r33) {
        if (r29 != null) goto L5;
        r30.needHideProgress();
        r30.finishFragment();
        if ((r33 instanceof TLRPC.TL_payments_starsRevenueWithdrawalUrl) == false) goto L75;
        Browser.openUrl(getContext(), ((TLRPC.TL_payments_starsRevenueWithdrawalUrl) r33).url);
        if (r32 == false) goto L75;
        loadStarsStats(true);
    L75:
        reloadTransactions();
        return;
    L5:
        if ("PASSWORD_MISSING".equals(r29.text) == false) goto L7;
    L19:
        if (r30 == null) goto L21;
        r30.needHideProgress();
    L21:
        AlertDialog.Builder r4 = new AlertDialog.Builder(r31);
        r4.setTitle(LocaleController.getString(R.string.EditAdminTransferAlertTitle));
        LinearLayout r5 = new LinearLayout(r31);
        r5.setPadding(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(24.0f), 0);
        r5.setOrientation(1);
        r4.setView(r5);
        TextView r8 = new TextView(r31);
        int r9 = Theme.key_dialogTextBlack;
        r8.setTextColor(Theme.getColor(r9));
        r8.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L24;
        int r12 = 5;
    L25:
        r8.setGravity(r12 | 48);
        r8.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.WithdrawChannelAlertText)));
        r5.addView(r8, LayoutHelper.createLinear(-1, -2));
        LinearLayout r82 = new LinearLayout(r31);
        r82.setOrientation(0);
        r5.addView(r82, LayoutHelper.createLinear(-1, -2, 0.0f, 11.0f, 0.0f, 0.0f));
        ImageView r13 = new ImageView(r31);
        r13.setImageResource(R.drawable.list_circle);
        if (LocaleController.isRTL == false) goto L28;
        int r14 = AndroidUtilities.dp(11.0f);
    L29:
        int r122 = AndroidUtilities.dp(9.0f);
        if (LocaleController.isRTL == false) goto L32;
        int r15 = 0;
    L33:
        r13.setPadding(r14, r122, r15, 0);
        int r142 = Theme.getColor(r9);
        PorterDuff.Mode r152 = PorterDuff.Mode.MULTIPLY;
        r13.setColorFilter(new PorterDuffColorFilter(r142, r152));
        TextView r123 = new TextView(r31);
        r123.setTextColor(Theme.getColor(r9));
        r123.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L36;
        int r143 = 5;
    L37:
        r123.setGravity(r143 | 48);
        r123.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.EditAdminTransferAlertText1)));
        if (LocaleController.isRTL == false) goto L40;
        r82.addView(r123, LayoutHelper.createLinear(-1, -2));
        r82.addView(r13, LayoutHelper.createLinear(-2, -2, 5));
    L41:
        LinearLayout r6 = new LinearLayout(r31);
        r6.setOrientation(0);
        r5.addView(r6, LayoutHelper.createLinear(-1, -2, 0.0f, 11.0f, 0.0f, 0.0f));
        ImageView r83 = new ImageView(r31);
        r83.setImageResource(R.drawable.list_circle);
        if (LocaleController.isRTL == false) goto L44;
        int r10 = AndroidUtilities.dp(11.0f);
    L45:
        int r124 = AndroidUtilities.dp(9.0f);
        if (LocaleController.isRTL == false) goto L48;
        int r132 = 0;
    L49:
        r83.setPadding(r10, r124, r132, 0);
        r83.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r9), r152));
        TextView r102 = new TextView(r31);
        r102.setTextColor(Theme.getColor(r9));
        r102.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L52;
        int r11 = 5;
    L53:
        r102.setGravity(r11 | 48);
        r102.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.EditAdminTransferAlertText2)));
        if (LocaleController.isRTL == false) goto L56;
        r6.addView(r102, LayoutHelper.createLinear(-1, -2));
        int r125 = 5;
        r6.addView(r83, LayoutHelper.createLinear(-2, -2, 5));
    L58:
        if ("PASSWORD_MISSING".equals(r29.text) == false) goto L60;
        r4.setPositiveButton(LocaleController.getString(R.string.EditAdminTransferSetPassword), new ChannelMonetizationLayout$$ExternalSyntheticLambda45(this));
        r4.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
    L65:
        if (r30 == null) goto L68;
        r30.showDialog(r4.create());
        return;
    L68:
        this.fragment.showDialog(r4.create());
        return;
    L60:
        TextView r1 = new TextView(r31);
        r1.setTextColor(Theme.getColor(r9));
        r1.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L63;
        int r133 = r125;
    L64:
        r1.setGravity(r133 | 48);
        r1.setText(LocaleController.getString(R.string.EditAdminTransferAlertText3));
        r5.addView(r1, LayoutHelper.createLinear(-1, -2, 0.0f, 11.0f, 0.0f, 0.0f));
        r4.setNegativeButton(LocaleController.getString(R.string.OK), null);
        goto L65
    L63:
        r133 = 3;
        goto L64
    L56:
        r125 = 5;
        r6.addView(r83, LayoutHelper.createLinear(-2, -2));
        r6.addView(r102, LayoutHelper.createLinear(-1, -2));
        goto L58
    L52:
        r11 = 3;
        goto L53
    L48:
        r132 = AndroidUtilities.dp(11.0f);
        goto L49
    L44:
        r10 = 0;
        goto L45
    L40:
        r82.addView(r13, LayoutHelper.createLinear(-2, -2));
        r82.addView(r123, LayoutHelper.createLinear(-1, -2));
        goto L41
    L36:
        r143 = 3;
        goto L37
    L32:
        r15 = AndroidUtilities.dp(11.0f);
        goto L33
    L28:
        r14 = 0;
        goto L29
    L24:
        r12 = 3;
        goto L25
    L7:
        if (r29.text.startsWith("PASSWORD_TOO_FRESH_") == true) goto L19;
        if (r29.text.startsWith("SESSION_TOO_FRESH_") == true) goto L19;
        if ("SRP_ID_INVALID".equals(r29.text) == false) goto L15;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_account.getPassword(), new ChannelMonetizationLayout$$ExternalSyntheticLambda46(this, r30, r32), 8);
        return;
    L15:
        if (r30 == null) goto L17;
        r30.needHideProgress();
        r30.finishFragment();
    L17:
        BulletinFactory.showError(r29);
    }

    private /* synthetic */ void lambda$initWithdraw$24(TwoStepVerificationActivity r8, Activity r9, boolean r10, TLObject r11, TLRPC.TL_error r12) {
        AndroidUtilities.runOnUIThread(new ChannelMonetizationLayout$$ExternalSyntheticLambda43(this, r12, r8, r9, r10, r11));
    }

    private /* synthetic */ void lambda$loadStarsStats$25(TLRPC.TL_payments_starsRevenueStats r1) {
        applyStarsStats(r1);
    }

    private /* synthetic */ void lambda$loadStarsStats$26(TLObject r2) {
        if ((r2 instanceof TLRPC.TL_payments_starsRevenueStats) == false) goto L6;
        applyStarsStats((TLRPC.TL_payments_starsRevenueStats) r2);
        return;
    }

    private /* synthetic */ void lambda$loadStarsStats$27(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new ChannelMonetizationLayout$$ExternalSyntheticLambda40(this, r1));
    }

    private /* synthetic */ void lambda$new$1() {
        Browser.openUrl(getContext(), LocaleController.getString(R.string.MonetizationBalanceInfoLink));
    }

    private /* synthetic */ void lambda$new$10(BaseFragment r2, TwoStepVerificationActivity r3) {
        this.starsBalanceButton.setLoading(false);
        r2.presentFragment(r3);
    }

    private /* synthetic */ void lambda$new$11(int r5, BaseFragment r6, View r7) {
        if (r7.isEnabled() == true) goto L5;
        return;
    L5:
        if (this.starsBalanceButton.isLoading() == false) goto L7;
        return;
    L7:
        if (this.balanceButton.isLoading() == true) goto L21;
        int r72 = ConnectionsManager.getInstance(r5).getCurrentTime();
        if (this.starsBalanceBlockedUntil <= r72) goto L14;
        this.withdrawalBulletin = BulletinFactory.of(r6).createSimpleBulletin(R.raw.timer_3, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BotStarsWithdrawalToast, new Object[]{BotStarsActivity.untilString(this.starsBalanceBlockedUntil - r72)}))).show();
        return;
    L14:
        if (this.starsBalanceEditTextValue >= MessagesController.getInstance(r5).starsRevenueWithdrawalMin) goto L17;
        Drawable r73 = getContext().getResources().getDrawable(R.drawable.star_small_inner).mutate();
        BulletinFactory.of(r6).createSimpleBulletin(r73, AndroidUtilities.replaceSingleTag(LocaleController.formatPluralString("BotStarsWithdrawMinLimit", (int) MessagesController.getInstance(r5).starsRevenueWithdrawalMin, new Object[0]), new ChannelMonetizationLayout$$ExternalSyntheticLambda24(this, r5))).show();
        return;
    L17:
        TwoStepVerificationActivity r52 = new TwoStepVerificationActivity();
        r52.setDelegate(1, new ChannelMonetizationLayout$$ExternalSyntheticLambda25(this, r52));
        this.starsBalanceButton.setLoading(true);
        r52.preload(new ChannelMonetizationLayout$$ExternalSyntheticLambda26(this, r6, r52));
        return;
    }

    private /* synthetic */ void lambda$new$12() {
        this.starsAdsButton.setLoading(false);
    }

    private /* synthetic */ void lambda$new$13(TLObject r3, Context r4) {
        if ((r3 instanceof TLRPC.TL_payments_starsRevenueAdsAccountUrl) == false) goto L5;
        Browser.openUrl(r4, ((TLRPC.TL_payments_starsRevenueAdsAccountUrl) r3).url);
    L5:
        AndroidUtilities.runOnUIThread(new ChannelMonetizationLayout$$ExternalSyntheticLambda41(this), 1000);
    }

    private /* synthetic */ void lambda$new$14(Context r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ChannelMonetizationLayout$$ExternalSyntheticLambda36(this, r2, r1));
    }

    private /* synthetic */ void lambda$new$15(int r2, long r3, Context r5, View r6) {
        if (r6.isEnabled() == true) goto L5;
        return;
    L5:
        if (this.starsAdsButton.isLoading() == true) goto L10;
        this.starsAdsButton.setLoading(true);
        TLRPC.TL_payments_getStarsRevenueAdsAccountUrl r62 = new TLRPC.TL_payments_getStarsRevenueAdsAccountUrl();
        r62.peer = MessagesController.getInstance(r2).getInputPeer(r3);
        ConnectionsManager.getInstance(r2).sendRequest(r62, new ChannelMonetizationLayout$$ExternalSyntheticLambda30(this, r5));
        return;
    }

    private /* synthetic */ void lambda$new$16(TwoStepVerificationActivity r2, TLRPC.InputCheckPasswordSRP r3) {
        initWithdraw(true, r3, r2);
    }

    private /* synthetic */ void lambda$new$17(BaseFragment r2, TwoStepVerificationActivity r3) {
        this.starsBalanceButton.setLoading(false);
        r2.presentFragment(r3);
    }

    private /* synthetic */ boolean lambda$new$18(BaseFragment r1, TextView r2, int r3, KeyEvent r4) {
        if (r3 != 5) goto L6;
        TwoStepVerificationActivity r22 = new TwoStepVerificationActivity();
        r22.setDelegate(1, new ChannelMonetizationLayout$$ExternalSyntheticLambda22(this, r22));
        this.starsBalanceButton.setLoading(true);
        r22.preload(new ChannelMonetizationLayout$$ExternalSyntheticLambda23(this, r1, r22));
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$new$19(int r6) {
        int r62 = ConnectionsManager.getInstance(r6).getCurrentTime();
        ButtonWithCounterView r0 = this.starsBalanceButton;
        if (this.starsBalanceEditTextValue <= 0) goto L5;
    L8:
        boolean r1 = true;
    L9:
        r0.setEnabled(r1);
        int r02 = this.starsBalanceBlockedUntil;
        ButtonWithCounterView r12 = this.starsBalanceButton;
        if (r62 >= r02) goto L23;
        r12.setText(LocaleController.getString(R.string.MonetizationStarsWithdrawUntil), true);
        if (this.lock != null) goto L14;
        this.lock = new SpannableStringBuilder("l");
        ColoredImageSpan r03 = new ColoredImageSpan(R.drawable.mini_switch_lock);
        r03.setTopOffset(1);
        this.lock.setSpan(r03, 0, 1, 33);
    L14:
        SpannableStringBuilder r04 = new SpannableStringBuilder();
        r04.append(this.lock).append(BotStarsActivity.untilString(this.starsBalanceBlockedUntil - r62));
        this.starsBalanceButton.setSubText(r04, true);
        Bulletin r05 = this.withdrawalBulletin;
        if (r05 != null) goto L17;
    L21:
        AndroidUtilities.cancelRunOnUIThread(this.setStarsBalanceButtonText);
        AndroidUtilities.runOnUIThread(this.setStarsBalanceButtonText, 1000);
        return;
    L17:
        if ((r05.getLayout() instanceof Bulletin.LottieLayout) == false) goto L21;
        if (this.withdrawalBulletin.getLayout().isAttachedToWindow() == false) goto L21;
        ((Bulletin.LottieLayout) this.withdrawalBulletin.getLayout()).textView.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BotStarsWithdrawalToast, new Object[]{BotStarsActivity.untilString(this.starsBalanceBlockedUntil - r62)})));
        goto L21
    L23:
        r12.setSubText(null, true);
        ButtonWithCounterView r63 = this.starsBalanceButton;
        if (this.starsBalanceEditTextAll == false) goto L26;
        String r06 = LocaleController.getString(R.string.MonetizationStarsWithdrawAll);
    L27:
        r63.setText(StarsIntroActivity.replaceStars(r06, this.starRef), true);
        return;
    L26:
        r06 = LocaleController.formatPluralStringSpaced("MonetizationStarsWithdraw", (int) this.starsBalanceEditTextValue);
        goto L27
    L5:
        if (this.starsBalanceBlockedUntil > r62) goto L8;
        r1 = false;
        goto L9
    }

    private /* synthetic */ void lambda$new$2(int r1) {
        Browser.openUrl(getContext(), LocaleController.getString(r1));
    }

    private /* synthetic */ void lambda$new$3() {
        Browser.openUrl(getContext(), LocaleController.getString(R.string.MonetizationStarsInfoLink));
    }

    private /* synthetic */ void lambda$new$4(TwoStepVerificationActivity r2, TLRPC.InputCheckPasswordSRP r3) {
        initWithdraw(false, r3, r2);
    }

    private /* synthetic */ void lambda$new$5(BaseFragment r2, TwoStepVerificationActivity r3) {
        this.balanceButton.setLoading(false);
        r2.presentFragment(r3);
    }

    private /* synthetic */ void lambda$new$6(BaseFragment r3, View r4) {
        if (r4.isEnabled() == true) goto L5;
        return;
    L5:
        if (this.balanceButton.isLoading() == true) goto L14;
        ButtonWithCounterView r42 = this.starsBalanceButton;
        if (r42 != null) goto L9;
    L11:
        TwoStepVerificationActivity r43 = new TwoStepVerificationActivity();
        r43.setDelegate(1, new ChannelMonetizationLayout$$ExternalSyntheticLambda20(this, r43));
        this.balanceButton.setLoading(true);
        r43.preload(new ChannelMonetizationLayout$$ExternalSyntheticLambda21(this, r3, r43));
        return;
    L9:
        if (r42.isLoading() == false) goto L11;
        return;
    }

    private /* synthetic */ void lambda$new$7(View r1, boolean r2) {
        OutlineTextContainerView r0 = this.starsBalanceEditTextContainer;
        if (r2 == false) goto L5;
        float r12 = 1.0f;
    L6:
        r0.animateSelection(r12);
        return;
    L5:
        r12 = 0.0f;
        goto L6
    }

    private /* synthetic */ void lambda$new$8(int r6) {
        Bulletin.hideVisible();
        if (this.starsBalance.amount >= MessagesController.getInstance(r6).starsRevenueWithdrawalMin) goto L5;
        this.starsBalanceEditTextAll = true;
        this.starsBalanceEditTextValue = this.starsBalance.amount;
    L6:
        this.starsBalanceEditTextIgnore = true;
        this.starsBalanceEditText.setText(Long.toString(this.starsBalanceEditTextValue));
        EditTextBoldCursor r62 = this.starsBalanceEditText;
        r62.setSelection(r62.getText().length());
        this.starsBalanceEditTextIgnore = false;
        AndroidUtilities.cancelRunOnUIThread(this.setStarsBalanceButtonText);
        this.setStarsBalanceButtonText.run();
        return;
    L5:
        this.starsBalanceEditTextAll = false;
        this.starsBalanceEditTextValue = MessagesController.getInstance(r6).starsRevenueWithdrawalMin;
        goto L6
    }

    private /* synthetic */ void lambda$new$9(TwoStepVerificationActivity r2, TLRPC.InputCheckPasswordSRP r3) {
        initWithdraw(true, r3, r2);
    }

    private /* synthetic */ void lambda$onClick$34(LimitReachedBottomSheet r1, ChannelBoostsController.CanApplyBoost r2) {
        r1.setCanApplyBoost(r2);
        this.fragment.showDialog(r1);
    }

    private /* synthetic */ void lambda$onNestedScroll$42() {
        RecyclerListView r1 = this.transactionsLayout.getCurrentListView();     // Catch: Throwable -> L8
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.getAdapter() == null) goto L12;
        r1.getAdapter().notifyDataSetChanged();     // Catch: Throwable -> L8
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$sendCpmUpdate$36() {
        this.initialSwitchOffValue = this.switchOffValue;
    }

    private /* synthetic */ void lambda$sendCpmUpdate$37(TLObject r1, TLRPC.TL_error r2) {
        if (r2 == null) goto L6;
        AndroidUtilities.runOnUIThread(new ChannelMonetizationLayout$$ExternalSyntheticLambda34(r2));
        return;
    L6:
        if ((r1 instanceof TLRPC.Updates) == false) goto L9;
        AndroidUtilities.runOnUIThread(new ChannelMonetizationLayout$$ExternalSyntheticLambda35(this));
        MessagesController.getInstance(this.currentAccount).processUpdates((TLRPC.Updates) r1, false);
        return;
    }

    private void loadStarsStats(boolean r4) {
        if (this.starsRevenueAvailable == true) goto L5;
        return;
    L5:
        TLRPC.TL_payments_starsRevenueStats r42 = BotStarsController.getInstance(this.currentAccount).getStarsRevenueStats(this.dialogId, r4);
        if (r42 == null) goto L9;
        AndroidUtilities.runOnUIThread(new ChannelMonetizationLayout$$ExternalSyntheticLambda38(this, r42));
        return;
    L9:
        TLRPC.TL_payments_getStarsRevenueStats r43 = new TLRPC.TL_payments_getStarsRevenueStats();
        r43.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        r43.dark = Theme.isCurrentThemeDark();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r43, new ChannelMonetizationLayout$$ExternalSyntheticLambda39(this));
    }

    public static BottomSheet makeLearnSheet(Context r21, boolean r22, Theme.ResourcesProvider r23) {
        BottomSheet r7 = new BottomSheet(r21, false, r23);
        r7.fixNavigationBar();
        LinearLayout r9 = new LinearLayout(r21);
        r9.setOrientation(1);
        r9.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        RLottieImageView r0 = new RLottieImageView(r21);
        r0.setScaleType(ImageView.ScaleType.CENTER);
        r0.setImageResource(R.drawable.large_monetize);
        r0.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        r0.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), Theme.getColor(Theme.key_featuredStickers_addButton, r23)));
        r9.addView(r0, LayoutHelper.createLinear(80, 80, 1, 0, 16, 0, 16));
        TextView r02 = new TextView(r21);
        r02.setGravity(17);
        r02.setTextSize(1, 20.0f);
        r02.setTypeface(AndroidUtilities.bold());
        int r13 = Theme.key_windowBackgroundWhiteBlackText;
        r02.setTextColor(Theme.getColor(r13, r23));
        if (r22 == false) goto L5;
        int r2 = R.string.BotMonetizationInfoTitle;
    L6:
        r02.setText(LocaleController.getString(r2));
        r9.addView(r02, LayoutHelper.createLinear(-1, -2, 8.0f, 0.0f, 8.0f, 25.0f));
        int r24 = R.drawable.msg_channel;
        if (r22 == false) goto L9;
        int r3 = R.string.BotMonetizationInfoFeature1Name;
    L10:
        String r32 = LocaleController.getString(r3);
        if (r22 == false) goto L13;
        int r4 = R.string.BotMonetizationInfoFeature1Text;
    L14:
        r9.addView(new FeatureCell(r21, r24, r32, LocaleController.getString(r4), r23), LayoutHelper.createLinear(-1, -2, 49, 8, 0, 8, 16));
        int r25 = R.drawable.menu_feature_split;
        if (r22 == false) goto L17;
        int r1 = R.string.BotMonetizationInfoFeature2Name;
    L18:
        String r33 = LocaleController.getString(r1);
        if (r22 == false) goto L21;
        int r12 = R.string.BotMonetizationInfoFeature2Text;
    L22:
        r9.addView(new FeatureCell(r21, r25, r33, LocaleController.getString(r12), r23), LayoutHelper.createLinear(-1, -2, 49, 8, 0, 8, 16));
        int r26 = R.drawable.menu_feature_withdrawals;
        if (r22 == false) goto L25;
        int r14 = R.string.BotMonetizationInfoFeature3Name;
    L26:
        String r34 = LocaleController.getString(r14);
        if (r22 == false) goto L29;
        int r15 = R.string.BotMonetizationInfoFeature3Text;
    L30:
        r9.addView(new FeatureCell(r21, r26, r34, LocaleController.getString(r15), r23), LayoutHelper.createLinear(-1, -2, 49, 8, 0, 8, 16));
        View r03 = new View(r21);
        r03.setBackgroundColor(Theme.getColor(Theme.key_divider, r23));
        r9.addView(r03, LayoutHelper.createLinear(-1, 1.0f / AndroidUtilities.density, 55, 12, 0, 12, 0));
        AnimatedEmojiSpan.TextViewEmojis r04 = new AnimatedEmojiSpan.TextViewEmojis(r21);
        r04.setGravity(17);
        r04.setTextSize(1, 20.0f);
        r04.setTypeface(AndroidUtilities.bold());
        r04.setTextColor(Theme.getColor(r13, r23));
        SpannableString r27 = new SpannableString("💎");
        ColoredImageSpan r42 = new ColoredImageSpan(R.drawable.mini_gram_72);
        r42.setScale(0.9f, 0.9f);
        r42.setColorKey(Theme.key_windowBackgroundWhiteBlueText2);
        r42.setRelativeSize(r04.getPaint().getFontMetricsInt());
        r42.spaceScaleX = 0.9f;
        r27.setSpan(r42, 0, r27.length(), 33);
        if (r22 == false) goto L33;
        int r43 = R.string.BotMonetizationInfoTONTitle;
    L34:
        r04.setText(AndroidUtilities.replaceCharSequence("💎", LocaleController.getString(r43), r27));
        r9.addView(r04, LayoutHelper.createLinear(-1, -2, 8.0f, 20.0f, 8.0f, 0.0f));
        LinkSpanDrawable.LinksTextView r05 = new LinkSpanDrawable.LinksTextView(r21, r23);
        r05.setGravity(17);
        r05.setTextSize(1, 14.0f);
        r05.setTextColor(Theme.getColor(r13, r23));
        r05.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r23));
        if (r22 == false) goto L37;
        int r28 = R.string.BotMonetizationInfoTONText;
    L38:
        r05.setText(AndroidUtilities.withLearnMore(AndroidUtilities.replaceTags(LocaleController.getString(r28)), new ChannelMonetizationLayout$$ExternalSyntheticLambda0(r21, r22)));
        r9.addView(r05, LayoutHelper.createLinear(-1, -2, 28.0f, 9.0f, 28.0f, 0.0f));
        ButtonWithCounterView r06 = new ButtonWithCounterView(r21, r23).setRound();
        r06.setText(LocaleController.getString(R.string.GotIt), false);
        r06.setOnClickListener(new ChannelMonetizationLayout$$ExternalSyntheticLambda1(r7));
        r9.addView(r06, LayoutHelper.createLinear(-1, 48, 55, 10, 25, 10, 14));
        r7.setCustomView(r9);
        return r7;
    L37:
        r28 = R.string.MonetizationInfoTONText;
        goto L38
    L33:
        r43 = R.string.MonetizationInfoTONTitle;
        goto L34
    L29:
        r15 = R.string.MonetizationInfoFeature3Text;
        goto L30
    L25:
        r14 = R.string.MonetizationInfoFeature3Name;
        goto L26
    L21:
        r12 = R.string.MonetizationInfoFeature2Text;
        goto L22
    L17:
        r1 = R.string.MonetizationInfoFeature2Name;
        goto L18
    L13:
        r4 = R.string.MonetizationInfoFeature1Text;
        goto L14
    L9:
        r3 = R.string.MonetizationInfoFeature1Name;
        goto L10
    L5:
        r2 = R.string.MonetizationInfoTitle;
        goto L6
    }

    private void onClick(UItem r7, View r8, int r9, float r10, float r11) {
        int r72 = r7.id;
        if (r72 != 1) goto L14;
        if (this.currentBoostLevel < MessagesController.getInstance(this.currentAccount).channelRestrictSponsoredLevelMin) goto L7;
        this.switchOffValue = !this.switchOffValue;
        AndroidUtilities.cancelRunOnUIThread(this.sendCpmUpdateRunnable);
        AndroidUtilities.runOnUIThread(this.sendCpmUpdateRunnable, 1000);
        this.listView.adapter.update(true);
        return;
    L7:
        if (this.boostsStatus == null) goto L18;
        LimitReachedBottomSheet r0 = new LimitReachedBottomSheet(this.fragment, getContext(), 30, this.currentAccount, this.resourcesProvider);
        r0.setDialogId(this.dialogId);
        r0.setBoostsStats(this.boostsStatus, true);
        MessagesController.getInstance(this.currentAccount).getBoostsController().userCanBoostChannel(this.dialogId, this.boostsStatus, new ChannelMonetizationLayout$$ExternalSyntheticLambda31(this, r0));
        return;
    L18:
        return;
    L14:
        if (r72 != 4) goto L17;
        this.fragment.presentFragment(new ChannelAffiliateProgramsFragment(this.dialogId));
        return;
    }

    private boolean onLongClick(UItem r1, View r2, int r3, float r4, float r5) {
        return false;
    }

    public static CharSequence replaceTON(CharSequence r2, TextPaint r3) {
        return replaceTON(r2, r3, 1.0f, true);
    }

    public static CharSequence replaceTON(CharSequence r4, TextPaint r5, float r6, float r7, boolean r8) {
        if (tonString != null) goto L5;
        tonString = new HashMap();
    L5:
        int r0 = r5.getFontMetricsInt().bottom;
        if (r8 == false) goto L8;
        int r1 = 1;
    L9:
        int r02 = ((r0 * r1) * ((int) (r6 * 100.0f))) - ((int) (100.0f * r7));
        SpannableString r12 = tonString.get(Integer.valueOf(r02));
        if (r12 != null) goto L17;
        r12 = new SpannableString("T");
        if (r8 == false) goto L14;
        ColoredImageSpan r72 = new ColoredImageSpan(R.drawable.mini_gram_72);
        r72.setScale(r6, r6);
        r72.setColorKey(Theme.key_windowBackgroundWhiteBlueText2);
        r72.setRelativeSize(r5.getFontMetricsInt());
        r72.spaceScaleX = 0.9f;
        r12.setSpan(r72, 0, r12.length(), 33);
    L15:
        tonString.put(Integer.valueOf(r02), r12);
        goto L17
    L14:
        ColoredImageSpan r52 = new ColoredImageSpan(R.drawable.mini_gram_16);
        r52.setScale(r6, r6);
        r52.setTranslateY(r7);
        r52.spaceScaleX = 0.95f;
        r12.setSpan(r52, 0, r12.length(), 33);
    L17:
        return AndroidUtilities.replaceMultipleCharSequence("TON", r4, r12);
    L8:
        r1 = -1;
        goto L9
    }

    public static CharSequence replaceTON(CharSequence r1, TextPaint r2, float r3, boolean r4) {
        return replaceTON(r1, r2, r3, 0.0f, r4);
    }

    private void sendCpmUpdate() {
        AndroidUtilities.cancelRunOnUIThread(this.sendCpmUpdateRunnable);
        if (this.switchOffValue != this.initialSwitchOffValue) goto L5;
        return;
    L5:
        TLRPC.TL_channels_restrictSponsoredMessages r0 = new TLRPC.TL_channels_restrictSponsoredMessages();
        r0.channel = MessagesController.getInstance(this.currentAccount).getInputChannel(-this.dialogId);
        r0.restricted = this.switchOffValue;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new ChannelMonetizationLayout$$ExternalSyntheticLambda27(this));
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
        SpannableStringBuilder r03 = new SpannableStringBuilder(replaceTON("TON " + this.formatter.format(r62), this.balanceTitle.getPaint(), 0.9f, true));
        int r63 = TextUtils.indexOf(r03, ".");
        if (r63 < 0) goto L11;
        r03.setSpan(this.balanceTitleSizeSpan, r63, r03.length(), 33);
    L11:
        this.balanceTitle.setText(r03);
        this.balanceSubtitle.setText("≈" + BillingController.getInstance().formatCurrency(r8, "USD"));
    }

    private void setStarsBalance(TL_stars.StarsAmount r10, int r11) {
        if (this.balanceTitle != null) goto L5;
        return;
    L5:
        if (this.balanceSubtitle == null) goto L30;
        boolean r4 = false;
        SpannableStringBuilder r0 = new SpannableStringBuilder(StarsIntroActivity.replaceStarsWithPlain(TextUtils.concat(new CharSequence[]{"XTR ", StarsIntroActivity.formatStarsAmount(r10, 0.8f, ' ')}), 1.0f));
        int r1 = TextUtils.indexOf(r0, ".");
        if (r1 < 0) goto L10;
        r0.setSpan(this.balanceTitleSizeSpan, r1, r0.length(), 33);
    L10:
        this.starsBalance = r10;
        this.starsBalanceTitle.setText(r0);
        this.starsBalanceSubtitle.setText("≈" + BillingController.getInstance().formatCurrency((long) ((this.stars_rate * r10.amount) * 100.0d), "USD"));
        OutlineTextContainerView r02 = this.starsBalanceEditTextContainer;
        if (r10.amount <= 0) goto L13;
        int r12 = 0;
    L14:
        r02.setVisibility(r12);
        if (this.starsBalanceEditTextAll == false) goto L21;
        this.starsBalanceEditTextIgnore = true;
        EditTextBoldCursor r03 = this.starsBalanceEditText;
        long r13 = r10.amount;
        this.starsBalanceEditTextValue = r13;
        r03.setText(Long.toString(r13));
        EditTextBoldCursor r04 = this.starsBalanceEditText;
        r04.setSelection(r04.getText().length());
        this.starsBalanceEditTextIgnore = false;
        ButtonWithCounterView r05 = this.starsBalanceButton;
        if (this.starsBalanceEditTextValue <= 0) goto L19;
        boolean r14 = true;
    L20:
        r05.setEnabled(r14);
        goto L21
    L19:
        r14 = false;
    L21:
        ButtonWithCounterView r06 = this.starsAdsButton;
        if (r06 != null) goto L24;
    L27:
        this.starsBalanceBlockedUntil = r11;
        AndroidUtilities.cancelRunOnUIThread(this.setStarsBalanceButtonText);
        this.setStarsBalanceButtonText.run();
        return;
    L24:
        if (r10.amount <= 0) goto L26;
        r4 = true;
    L26:
        r06.setEnabled(r4);
        goto L27
    L13:
        r12 = 8;
        goto L14
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void showTransactionSheet(Context r35, int r36, TL_stats.BroadcastRevenueTransaction r37, long r38, Theme.ResourcesProvider r40) {
        BottomSheet r5 = new BottomSheet(r35, false, r40);
        r5.fixNavigationBar();
        LinearLayout r7 = new LinearLayout(r35);
        r7.setOrientation(1);
        boolean r9 = r37 instanceof TL_stats.TL_broadcastRevenueTransactionWithdrawal;
        if (r9 == false) goto L5;
        TL_stats.TL_broadcastRevenueTransactionWithdrawal r12 = (TL_stats.TL_broadcastRevenueTransactionWithdrawal) r37;
        String r13 = LocaleController.getString(R.string.MonetizationTransactionDetailWithdraw);
        long r14 = r12.date;
        long r16 = 0;
        long r10 = r12.amount;
        boolean r8 = r12.pending;
        long r20 = r14;
        char r15 = 65535;
        ViewGroup r19 = r7;
        String r142 = r13;
        long r6 = 0;
        boolean r132 = r12.failed;
    L12:
        long r23 = r6;
        DecimalFormatSymbols r122 = new DecimalFormatSymbols(Locale.US);
        r122.setDecimalSeparator('.');
        DecimalFormat r62 = new DecimalFormat("#.##", r122);
        r62.setMinimumFractionDigits(2);
        r62.setMaximumFractionDigits(12);
        r62.setGroupingUsed(false);
        TextView r72 = new TextView(r35);
        r72.setGravity(17);
        r72.setTypeface(AndroidUtilities.bold());
        boolean r27 = r8;
        r72.setTextSize(1, 18.0f);
        if (r15 >= 0) goto L15;
        int r82 = Theme.key_text_RedBold;
    L16:
        r72.setTextColor(Theme.getColor(r82));
        SpannableStringBuilder r83 = new SpannableStringBuilder();
        if (r15 >= 0) goto L19;
        String r123 = "-";
    L20:
        r83.append(r123);
        r83.append(r62.format(Math.round((Math.abs(r10) / 1.0E9d) * 100000.0d) / 100000.0d));
        r83.append(" TON");
        int r63 = TextUtils.indexOf(r83, ".");
        if (r63 < 0) goto L23;
        r83.setSpan(new RelativeSizeSpan(1.3333334f), 0, r63, 33);
    L23:
        r72.setText(r83);
        ViewGroup r84 = r19;
        r84.addView(r72, LayoutHelper.createLinear(-1, -2, 49, 0, 24, 0, 6));
        TextView r64 = new TextView(r35);
        r64.setGravity(17);
        r64.setTextSize(1, 13.0f);
        r64.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r40));
        if (r27 == false) goto L27;
        r64.setText(LocaleController.getString(R.string.MonetizationTransactionPending));
    L33:
        if (r132 == false) goto L35;
        r64.setTextColor(Theme.getColor(Theme.key_text_RedBold, r40));
        r64.setText(TextUtils.concat(new CharSequence[]{r64.getText(), " — ", LocaleController.getString(R.string.MonetizationTransactionNotCompleted)}));
    L35:
        r84.addView(r64, LayoutHelper.createLinear(-1, -2, 49, 0, 0, 0, 0));
        TextView r65 = new TextView(r35);
        r65.setGravity(17);
        r65.setTypeface(AndroidUtilities.bold());
        r65.setTextSize(1, 14.0f);
        r65.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r40));
        r65.setText(r142);
        r84.addView(r65, LayoutHelper.createLinear(-1, -2, 49, 0, 27, 0, 0));
        if ((r37 instanceof TL_stats.TL_broadcastRevenueTransactionProceeds) == false) goto L45;
        FrameLayout r66 = new FrameLayout(r35);
        r66.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), Theme.getColor(Theme.key_groupcreate_spanBackground, r40)));
        if (r38 >= r16) goto L43;
        TLRPC.Chat r2 = MessagesController.getInstance(r36).getChat(Long.valueOf(-r38));
        if (r2 != 0) goto L42;
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        TLRPC.User r22 = r2;
    L44:
        BackupImageView r11 = new BackupImageView(r35);
        r11.setRoundRadius(AndroidUtilities.dp(28.0f));
        AvatarDrawable r102 = new AvatarDrawable();
        r102.setInfo(r22);
        r11.setForUserOrChat(r22, r102);
        r66.addView(r11, LayoutHelper.createFrame(28, 28, 51));
        TextView r24 = new TextView(r35);
        r24.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r40));
        r24.setTextSize(1, 13.0f);
        r24.setSingleLine();
        r24.setText(r3);
        r66.addView(r24, LayoutHelper.createFrame(-2, -2.0f, 19, 37.0f, 0.0f, 10.0f, 0.0f));
        r84.addView(r66, LayoutHelper.createLinear(-2, 28, 1, 42, 10, 42, 0));
        goto L45
    L42:
        r3 = r2.title;
        r22 = r2;
        goto L44
    L43:
        TLRPC.User r25 = MessagesController.getInstance(r36).getUser(Long.valueOf(r38));
        r3 = UserObject.getUserName(r25);
        r22 = r25;
    L45:
        ButtonWithCounterView r26 = new ButtonWithCounterView(r35, r40).setRound();
        if (r9 == false) goto L50;
        TL_stats.TL_broadcastRevenueTransactionWithdrawal r1 = (TL_stats.TL_broadcastRevenueTransactionWithdrawal) r37;
        if ((r1.flags & 2) == 0) goto L50;
        r26.setText(LocaleController.getString(R.string.MonetizationTransactionDetailWithdrawButton), false);
        r26.setOnClickListener(new ChannelMonetizationLayout$$ExternalSyntheticLambda17(r35, r1));
    L51:
        r84.addView(r26, LayoutHelper.createLinear(-1, 48, 55, 18, 30, 18, 14));
        r5.setCustomView(r84);
        r5.show();
        return;
    L50:
        r26.setText(LocaleController.getString(R.string.OK), false);
        r26.setOnClickListener(new ChannelMonetizationLayout$$ExternalSyntheticLambda18(r5));
        goto L51
    L27:
        if (r20 != r16) goto L30;
        r64.setText(LocaleController.formatShortDateTime(r23));
        goto L33
    L30:
        if (r23 != r16) goto L32;
        r64.setText(LocaleController.formatShortDateTime(r20));
        goto L33
    L32:
        r64.setText(LocaleController.formatShortDateTime(r20) + " - " + LocaleController.formatShortDateTime(r23));
        goto L33
    L19:
        r123 = "+";
        goto L20
    L15:
        r82 = Theme.key_avatar_nameInMessageGreen;
        goto L16
    L5:
        r16 = 0;
        if ((r37 instanceof TL_stats.TL_broadcastRevenueTransactionProceeds) == false) goto L9;
        TL_stats.TL_broadcastRevenueTransactionProceeds r85 = (TL_stats.TL_broadcastRevenueTransactionProceeds) r37;
        String r133 = LocaleController.getString(R.string.MonetizationTransactionDetailProceed);
        long r143 = r85.from_date;
        long r103 = r85.to_date;
        r19 = r7;
        r10 = r85.amount;
        r6 = r103;
        r20 = r143;
    L8:
        r8 = false;
        r15 = 1;
        r142 = r133;
        r132 = false;
        goto L12
    L9:
        r19 = r7;
        if ((r37 instanceof TL_stats.TL_broadcastRevenueTransactionRefund) == false) goto L53;
        TL_stats.TL_broadcastRevenueTransactionRefund r67 = (TL_stats.TL_broadcastRevenueTransactionRefund) r37;
        r133 = LocaleController.getString(R.string.MonetizationTransactionDetailRefund);
        long r144 = r67.from_date;
        r10 = r67.amount;
        r20 = r144;
        r6 = 0;
        goto L8
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        instance = this;
        super.onAttachedToWindow();
        checkLearnSheet();
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        instance = null;
        super.onDetachedFromWindow();
        ActionBar r1 = this.actionBar;
        if (r1 == null) goto L6;
        r1.setCastShadows(true);
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
    public void onNestedPreScroll(View r4, int r5, int r6, int[] r7, int r8) {
        if (r4 == this.listView) goto L5;
        return;
    L5:
        if (this.transactionsLayout.isAttachedToWindow() == false) goto L42;
        ((View) this.transactionsLayout.getParent()).getTop();
        int r42 = AndroidUtilities.REPLACING_TAG_TYPE_LINK;
        ActionBar.getCurrentActionBarHeight();
        int r43 = ((View) this.transactionsLayout.getParent()).getBottom();
        if (r6 >= 0) goto L33;
        ActionBar r0 = this.actionBar;
        if (r0 == null) goto L19;
        if (isAttachedToWindow() == true) goto L13;
    L16:
        boolean r2 = true;
    L17:
        r0.setCastShadows(r2);
        goto L19
    L13:
        if ((this.listView.getHeight() - r43) < 0) goto L16;
        r2 = false;
    L19:
        if ((this.listView.getHeight() - r43) < (this.listView.getPaddingBottom() + AndroidUtilities.dp(8.0f))) goto L43;
        RecyclerListView r3 = this.transactionsLayout.getCurrentListView();
        int r44 = ((LinearLayoutManager) r3.getLayoutManager()).findFirstVisibleItemPosition();
        int r52 = -1;
        if (r44 == (-1)) goto L44;
        RecyclerView.ViewHolder r02 = r3.findViewHolderForAdapterPosition(r44);
        if (r02 == null) goto L25;
        r52 = r02.itemView.getTop();
    L25:
        int r03 = r3.getPaddingTop();
        if (r52 != r03) goto L28;
        if (r44 != 0) goto L28;
        return;
    L28:
        if (r44 == 0) goto L30;
        int r45 = r6;
    L31:
        r7[1] = r45;
        r3.scrollBy(0, r6);
        return;
    L30:
        r45 = Math.max(r6, r52 - r03);
        goto L31
    L44:
        return;
    L43:
        return;
    L33:
        if (r6 <= 0) goto L46;
        RecyclerListView r04 = this.transactionsLayout.getCurrentListView();
        if ((this.listView.getHeight() - r43) < (this.listView.getPaddingBottom() + AndroidUtilities.dp(8.0f))) goto L47;
        if (r04 != null) goto L38;
        return;
    L38:
        if (r04.canScrollVertically(1) == true) goto L49;
        r7[1] = r6;
        this.listView.stopScroll();
        return;
    L49:
        return;
    L47:
        return;
    L46:
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
        AndroidUtilities.runOnUIThread(new ChannelMonetizationLayout$$ExternalSyntheticLambda19(this));
        return;
    L3:
        if (r2 == this.listView) goto L5;
        return;
    L5:
        if (this.transactionsLayout.isAttachedToWindow() == false) goto L27;
        RecyclerListView r22 = this.transactionsLayout.getCurrentListView();     // Catch: Throwable -> L14
        int r32 = ((View) this.transactionsLayout.getParent()).getBottom();     // Catch: Throwable -> L14
        ActionBar r42 = this.actionBar;     // Catch: Throwable -> L14
        if (r42 == null) goto L19;
        if (isAttachedToWindow() == true) goto L11;
    L16:
        boolean r0 = true;
    L17:
        r42.setCastShadows(r0);     // Catch: Throwable -> L14
        goto L19
    L11:
        if ((this.listView.getHeight() - r32) < 0) goto L16;
        r0 = false;
    L19:
        if ((this.listView.getHeight() - r32) < (this.listView.getPaddingBottom() + AndroidUtilities.dp(8.0f))) goto L28;
        r8[1] = r6;     // Catch: Throwable -> L14
        r22.scrollBy(0, r6);     // Catch: Throwable -> L14
        return;
    L28:
        return;
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

    public void reloadTransactions() {
        this.transactionsLayout.reloadTransactions();
    }

    public void setActionBar(ActionBar r1) {
        this.actionBar = r1;
    }

    public void setupBalances(boolean r20, TLRPC.TL_starsRevenueStatus r21) {
        int r4 = 8;
        if (r20 == false) goto L10;
        ProceedOverview r10 = this.availableValue;
        r10.contains1 = true;
        long r11 = r21.available_balance.amount;
        r10.crypto_amount = r11;
        long r2 = (long) (((r11 / 1.0E9d) * this.ton_rate) * 100.0d);
        r10.amount = r2;
        setBalance(r11, r2);
        this.availableValue.currency = "USD";
        ProceedOverview r22 = this.lastWithdrawalValue;
        r22.contains1 = true;
        long r102 = r21.current_balance.amount;
        r22.crypto_amount = r102;
        double r12 = this.ton_rate;
        r22.amount = (long) (((r102 / 1.0E9d) * r12) * 100.0d);
        r22.currency = "USD";
        ProceedOverview r23 = this.lifetimeValue;
        r23.contains1 = true;
        long r103 = r21.overall_revenue.amount;
        r23.crypto_amount = r103;
        r23.amount = (long) (((r103 / 1.0E9d) * r12) * 100.0d);
        r23.currency = "USD";
        this.proceedsAvailable = true;
        ButtonWithCounterView r24 = this.balanceButton;
        if (r21.available_balance.amount > 0) goto L7;
    L9:
        r24.setVisibility(r4);
    L28:
        UniversalRecyclerView r0 = this.listView;
        if (r0 == null) goto L34;
        UniversalAdapter r02 = r0.adapter;
        if (r02 == null) goto L35;
        r02.update(true);
        return;
    L35:
        return;
    L34:
        return;
    L7:
        if (r21.withdrawal_enabled == false) goto L9;
        r4 = 0;
        goto L9
    L10:
        double r25 = this.stars_rate;
        if (r25 == 0.0d) goto L36;
        ProceedOverview r104 = this.availableValue;
        r104.contains2 = true;
        TL_stars.StarsAmount r112 = r21.available_balance;
        r104.crypto_amount2 = r112;
        r104.amount2 = (long) ((r112.amount * r25) * 100.0d);
        setStarsBalance(r112, r21.next_withdrawal_at);
        this.availableValue.currency = "USD";
        ProceedOverview r26 = this.lastWithdrawalValue;
        r26.contains2 = true;
        TL_stars.StarsAmount r3 = r21.current_balance;
        r26.crypto_amount2 = r3;
        double r105 = r3.amount;
        double r122 = this.stars_rate;
        r26.amount2 = (long) ((r105 * r122) * 100.0d);
        r26.currency = "USD";
        ProceedOverview r27 = this.lifetimeValue;
        r27.contains2 = true;
        r27.crypto_amount2 = r21.overall_revenue;
        r27.amount2 = (long) ((r3.amount * r122) * 100.0d);
        r27.currency = "USD";
        this.proceedsAvailable = true;
        LinearLayout r28 = this.starsBalanceButtonsLayout;
        if (r28 != null) goto L16;
    L20:
        ButtonWithCounterView r29 = this.starsBalanceButton;
        if (r29 == null) goto L28;
        if (r21.available_balance.amount <= 0) goto L25;
    L26:
        r4 = 0;
    L27:
        r29.setVisibility(r4);
        goto L28
    L25:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L27;
    L16:
        if (r21.withdrawal_enabled == false) goto L18;
        int r32 = 0;
    L19:
        r28.setVisibility(r32);
        goto L20
    L18:
        r32 = 8;
        goto L19
    }

    public void updateList() {
        UniversalRecyclerView r1 = this.listView;
        if (r1 == null) goto L6;
        r1.adapter.update(true);
        return;
    }
}
