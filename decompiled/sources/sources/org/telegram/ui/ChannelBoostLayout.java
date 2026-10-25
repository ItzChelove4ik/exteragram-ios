package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stats;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.FixedHeightEmptyCell;
import org.telegram.ui.Cells.ManageChatTextCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Charts.view_data.ChartHeaderView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkActionView;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.Premium.LimitPreviewView;
import org.telegram.ui.Components.Premium.boosts.BoostPagerBottomSheet;
import org.telegram.ui.Components.Premium.boosts.GiftInfoBottomSheet;
import org.telegram.ui.Components.Premium.boosts.cells.statistics.GiftedUserCell;
import org.telegram.ui.Components.Premium.boosts.cells.statistics.GiveawayCell;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScrollSlidingTextTabStrip;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.StatisticActivity;

/* loaded from: classes6.dex */
public class ChannelBoostLayout extends FrameLayout {
    AdapterWithDiffUtils adapter;
    private final ArrayList<TL_stories.Boost> boosters;
    TL_stories.TL_premium_boostsStatus boostsStatus;
    private ScrollSlidingTextTabStrip boostsTabs;
    int currentAccount;
    private TLRPC.Chat currentChat;
    private final long dialogId;
    BaseFragment fragment;
    private final ArrayList<TL_stories.Boost> gifts;
    private boolean hasBoostsNext;
    private boolean hasGiftsNext;
    public IBlur3Capture iBlur3Capture;
    private final ArrayList<ItemInternal> items;
    private String lastBoostsOffset;
    private String lastGiftsOffset;
    private int limitBoosts;
    private int limitGifts;
    public final RecyclerListView listView;
    private int nextBoostRemaining;
    private int nextGiftsRemaining;
    private LinearLayout progressLayout;
    private final Theme.ResourcesProvider resourcesProvider;
    private int selectedTab;
    private int totalBoosts;
    private int totalGifts;
    boolean usersLoading;

    /* renamed from: org.telegram.ui.ChannelBoostLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends AdapterWithDiffUtils {
        private int remTotalBoosts;
        private int remTotalGifts;
        final /* synthetic */ ChannelBoostLayout this$0;

        /* renamed from: org.telegram.ui.ChannelBoostLayout$1$1, reason: invalid class name and collision with other inner class name */
        public class C00441 extends FrameLayout {
            private final Paint dividerPaint;
            final /* synthetic */ AnonymousClass1 this$1;

            public C00441(AnonymousClass1 r1, Context r2) {
                this.this$1 = r1;
                super(r2);
                this.dividerPaint = new Paint(1);
            }

            @Override // android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r8) {
                super.dispatchDraw(r8);
                this.dividerPaint.setColor(Theme.getColor(Theme.key_windowBackgroundGray, ChannelBoostLayout.m7266$$Nest$fgetresourcesProvider(this.this$1.this$0)));
                r8.drawRect(0.0f, getHeight() - 2, getWidth(), getHeight(), this.dividerPaint);
            }
        }

        /* renamed from: org.telegram.ui.ChannelBoostLayout$1$2, reason: invalid class name */
        public class AnonymousClass2 implements ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate {
            final /* synthetic */ AnonymousClass1 this$1;

            public AnonymousClass2(AnonymousClass1 r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.Components.ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate
            public void onPageScrolled(float r1) {
            }

            @Override // org.telegram.ui.Components.ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate
            public void onPageSelected(int r1, boolean r2) {
                ChannelBoostLayout.m7271$$Nest$fputselectedTab(this.this$1.this$0, r1);
                this.this$1.this$0.updateRows(true);
            }

            @Override // org.telegram.ui.Components.ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate
            public void onSamePageSelected() {
            }
        }

        /* renamed from: org.telegram.ui.ChannelBoostLayout$1$3, reason: invalid class name */
        public class AnonymousClass3 extends FrameLayout {
            final /* synthetic */ AnonymousClass1 this$1;

            public AnonymousClass3(AnonymousClass1 r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
            }
        }

        /* renamed from: org.telegram.ui.ChannelBoostLayout$1$4, reason: invalid class name */
        public class AnonymousClass4 extends ManageChatTextCell {
            final /* synthetic */ AnonymousClass1 this$1;

            public AnonymousClass4(AnonymousClass1 r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Cells.ManageChatTextCell
            public int getFullHeight() {
                return AndroidUtilities.dp(50.0f);
            }
        }

        public AnonymousClass1(ChannelBoostLayout r1) {
            this.this$0 = r1;
            this.remTotalBoosts = -1;
            this.remTotalGifts = -1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return ChannelBoostLayout.m7262$$Nest$fgetitems(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return ((ItemInternal) ChannelBoostLayout.m7262$$Nest$fgetitems(this.this$0).get(r1)).viewType;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return ((ItemInternal) ChannelBoostLayout.m7262$$Nest$fgetitems(this.this$0).get(r1.getAdapterPosition())).selectable;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r13, int r14) {
            if (r13.getItemViewType() != 4) goto L6;
            return;
        L6:
            if (r13.getItemViewType() != 1) goto L8;
        L78:
            ChartHeaderView r0 = (ChartHeaderView) r13.itemView;
            r0.setTitle(((ItemInternal) ChannelBoostLayout.m7262$$Nest$fgetitems(this.this$0).get(r14)).title);
            r0.showDate(false);
            if (r13.getItemViewType() != 12) goto L84;
            r0.setPadding(AndroidUtilities.dp(3.0f), r0.getPaddingTop(), r0.getPaddingRight(), r0.getPaddingBottom());
            return;
        L84:
            return;
        L8:
            if (r13.getItemViewType() == 12) goto L78;
            if (r13.getItemViewType() != 0) goto L29;
            StatisticActivity.OverviewCell r132 = (StatisticActivity.OverviewCell) r13.itemView;
            r132.setData(0, Integer.toString(this.this$0.boostsStatus.level), null, LocaleController.getString(R.string.BoostsLevel2));
            ChannelBoostLayout r142 = this.this$0;
            TL_stats.TL_statsPercentValue r02 = r142.boostsStatus.premium_audience;
            if (r02 == null) goto L22;
            double r5 = r02.total;
            if (r5 == 0.0d) goto L22;
            float r143 = (((float) r02.part) / ((float) r5)) * 100.0f;
            String r03 = "≈" + ((int) this.this$0.boostsStatus.premium_audience.part);
            String r144 = String.format(Locale.US, "%.1f", new Object[]{Float.valueOf(r143)}).concat("%");
            if (ChannelBoostLayout.m7272$$Nest$misChannel(this.this$0) == false) goto L19;
            int r52 = R.string.PremiumSubscribers;
        L20:
            r132.setData(1, r03, r144, LocaleController.getString(r52));
        L26:
            r132.setData(2, String.valueOf(this.this$0.boostsStatus.boosts), null, LocaleController.getString(R.string.BoostsExisting));
            TL_stories.TL_premium_boostsStatus r12 = this.this$0.boostsStatus;
            r132.setData(3, String.valueOf(Math.max(0, r12.next_level_boosts - r12.boosts)), null, LocaleController.getString(R.string.BoostsToLevel));
            return;
        L19:
            r52 = R.string.PremiumMembers;
        L22:
            if (ChannelBoostLayout.m7272$$Nest$misChannel(r142) == false) goto L24;
            int r145 = R.string.PremiumSubscribers;
        L25:
            r132.setData(1, "≈0", "0%", LocaleController.getString(r145));
            goto L26
        L24:
            r145 = R.string.PremiumMembers;
            goto L25
        L29:
            if (r13.getItemViewType() != 5) goto L38;
            TL_stories.Boost r04 = ((ItemInternal) ChannelBoostLayout.m7262$$Nest$fgetitems(this.this$0).get(r14)).booster;
            TLRPC.User r7 = MessagesController.getInstance(this.this$0.currentAccount).getUser(Long.valueOf(r04.user_id));
            GiftedUserCell r6 = (GiftedUserCell) r13.itemView;
            int r133 = r04.multiplier;
            int r1 = r04.expires;
            if (r133 <= 1) goto L34;
            String r134 = LocaleController.formatString("BoostsExpireOn", R.string.BoostsExpireOn, new Object[]{LocaleController.formatDate(r1)});
        L35:
            r6.setData(r7, ContactsController.formatName(r7), r134, 0, !((ItemInternal) ChannelBoostLayout.m7262$$Nest$fgetitems(this.this$0).get(r14)).isLast);
            r6.setStatus(r04);
            r6.setAvatarPadding(5);
            return;
        L34:
            r134 = LocaleController.formatString("BoostExpireOn", R.string.BoostExpireOn, new Object[]{LocaleController.formatDate(r1)});
            goto L35
        L38:
            if (r13.getItemViewType() != 6) goto L42;
            ((TextInfoPrivacyCell) r13.itemView).setText(((ItemInternal) ChannelBoostLayout.m7262$$Nest$fgetitems(this.this$0).get(r14)).title);
            return;
        L42:
            if (r13.getItemViewType() != 9) goto L50;
            ManageChatTextCell r135 = (ManageChatTextCell) r13.itemView;
            int r146 = ChannelBoostLayout.m7267$$Nest$fgetselectedTab(this.this$0);
            ChannelBoostLayout r122 = this.this$0;
            if (r146 != 0) goto L47;
            r135.setText(LocaleController.formatPluralString("BoostingShowMoreBoosts", ChannelBoostLayout.m7263$$Nest$fgetnextBoostRemaining(r122), new Object[0]), null, R.drawable.arrow_more, false);
            return;
        L47:
            r135.setText(LocaleController.formatPluralString("BoostingShowMoreGifts", ChannelBoostLayout.m7264$$Nest$fgetnextGiftsRemaining(r122), new Object[0]), null, R.drawable.arrow_more, false);
            return;
        L50:
            if (r13.getItemViewType() != 3) goto L54;
            ((LinkActionView) r13.itemView).setLink(((ItemInternal) ChannelBoostLayout.m7262$$Nest$fgetitems(this.this$0).get(r14)).title);
            return;
        L54:
            if (r13.getItemViewType() != 11) goto L64;
            TL_stories.PrepaidGiveaway r72 = ((ItemInternal) ChannelBoostLayout.m7262$$Nest$fgetitems(this.this$0).get(r14)).prepaidGiveaway;
            GiveawayCell r62 = (GiveawayCell) r13.itemView;
            if ((r72 instanceof TL_stories.TL_prepaidGiveaway) == false) goto L59;
            r62.setData(r72, LocaleController.formatPluralString("BoostingTelegramPremiumCountPlural", r72.quantity, new Object[0]), LocaleController.formatPluralString("BoostingSubscriptionsCountPlural", r72.quantity, new Object[]{LocaleController.formatPluralString("PrepaidGiveawayMonths", ((TL_stories.TL_prepaidGiveaway) r72).months, new Object[0])}), 0, !r12.isLast);
        L61:
            r62.setImage(r72);
            r62.setAvatarPadding(5);
            return;
        L59:
            if ((r72 instanceof TL_stories.TL_prepaidStarsGiveaway) == false) goto L61;
            TL_stories.TL_prepaidStarsGiveaway r136 = (TL_stories.TL_prepaidStarsGiveaway) r72;
            r62.setData(r72, LocaleController.formatPluralStringComma("BoostingStarsCountPlural", (int) r136.stars), LocaleController.formatPluralString("AmongWinners", r136.quantity, new Object[0]), 0, !r12.isLast);
            goto L61
        L64:
            if (r13.getItemViewType() == 13) goto L66;
            return;
        L66:
            if (this.remTotalBoosts == ChannelBoostLayout.m7268$$Nest$fgettotalBoosts(this.this$0)) goto L68;
        L69:
            this.remTotalBoosts = ChannelBoostLayout.m7268$$Nest$fgettotalBoosts(this.this$0);
            this.remTotalGifts = ChannelBoostLayout.m7269$$Nest$fgettotalGifts(this.this$0);
            ChannelBoostLayout.m7261$$Nest$fgetboostsTabs(this.this$0).removeTabs();
            ChannelBoostLayout.m7261$$Nest$fgetboostsTabs(this.this$0).addTextTab(0, LocaleController.formatPluralString("BoostingBoostsCount", ChannelBoostLayout.m7268$$Nest$fgettotalBoosts(this.this$0), new Object[0]));
            if (MessagesController.getInstance(this.this$0.currentAccount).giveawayGiftsPurchaseAvailable == true) goto L72;
        L76:
            ChannelBoostLayout.m7261$$Nest$fgetboostsTabs(this.this$0).setInitialTabId(ChannelBoostLayout.m7267$$Nest$fgetselectedTab(this.this$0));
            ChannelBoostLayout.m7261$$Nest$fgetboostsTabs(this.this$0).finishAddingTabs();
            return;
        L72:
            if (ChannelBoostLayout.m7269$$Nest$fgettotalGifts(this.this$0) <= 0) goto L76;
            if (ChannelBoostLayout.m7269$$Nest$fgettotalGifts(this.this$0) == ChannelBoostLayout.m7268$$Nest$fgettotalBoosts(this.this$0)) goto L76;
            ChannelBoostLayout.m7261$$Nest$fgetboostsTabs(this.this$0).addTextTab(1, LocaleController.formatPluralString("BoostingGiftsCount", ChannelBoostLayout.m7269$$Nest$fgettotalGifts(this.this$0), new Object[0]));
            goto L76
        L68:
            if (this.remTotalGifts != ChannelBoostLayout.m7269$$Nest$fgettotalGifts(this.this$0)) goto L69;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r14, int r15) {
            switch(r15) {
                case 0: goto L23;
                case 1: goto L22;
                case 2: goto L21;
                case 3: goto L20;
                case 4: goto L19;
                case 5: goto L18;
                case 6: goto L17;
                case 7: goto L16;
                case 8: goto L11;
                case 9: goto L10;
                case 10: goto L9;
                case 11: goto L8;
                case 12: goto L7;
                case 13: goto L6;
                default: goto L4;
            };
        L4:
            Type$$ExternalSyntheticBUOutline0.m();
            return null;
        L6:
            ChannelBoostLayout.m7270$$Nest$fputboostsTabs(this.this$0, new ScrollSlidingTextTabStrip(this.this$0.fragment.getContext(), ChannelBoostLayout.m7266$$Nest$fgetresourcesProvider(this.this$0)));
            ChannelBoostLayout.m7261$$Nest$fgetboostsTabs(this.this$0).setColors(Theme.key_profile_tabSelectedLine, Theme.key_profile_tabSelectedText, Theme.key_profile_tabText, Theme.key_profile_tabSelector);
            C00441 r142 = new C00441(this, this.this$0.fragment.getContext());
            ChannelBoostLayout.m7261$$Nest$fgetboostsTabs(this.this$0).setDelegate(new AnonymousClass2(this));
            r142.addView(ChannelBoostLayout.m7261$$Nest$fgetboostsTabs(this.this$0), LayoutHelper.createFrame(-2, 48.0f));
            View r143 = r142;
        L24:
            r143.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r143);
        L7:
            View r144 = new ChartHeaderView(this.this$0.getContext());
            r144.setPadding(r144.getPaddingLeft(), AndroidUtilities.dp(16.0f), r144.getRight(), AndroidUtilities.dp(8.0f));
            r143 = r144;
            goto L24
        L8:
            r143 = new GiveawayCell(this.this$0.getContext(), 0, 0, false);
            goto L24
        L9:
            TextCell r145 = new TextCell(this.this$0.getContext());
            r145.setTextAndIcon(LocaleController.formatString("BoostingGetBoostsViaGifts", R.string.BoostingGetBoostsViaGifts, new Object[0]), R.drawable.msg_gift_premium, false);
            r145.offsetFromImage = 64;
            int r13 = Theme.key_windowBackgroundWhiteBlueText4;
            r145.setColors(r13, r13);
            r143 = r145;
            goto L24
        L10:
            AnonymousClass4 r146 = new AnonymousClass4(this, this.this$0.getContext());
            r146.setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
            r143 = r146;
            goto L24
        L11:
            AnonymousClass3 r147 = new AnonymousClass3(this, this.this$0.getContext());
            TextView r152 = new TextView(this.this$0.getContext());
            if (ChannelBoostLayout.m7272$$Nest$misChannel(this.this$0) == false) goto L14;
            int r132 = R.string.NoBoostersHint;
        L15:
            r152.setText(LocaleController.getString(r132));
            r152.setTextSize(1, 14.0f);
            r152.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
            r152.setGravity(17);
            r147.addView(r152, LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 16.0f, 0.0f, 0.0f));
            r143 = r147;
            goto L24
        L14:
            r132 = R.string.NoBoostersGroupHint;
            goto L15
        L16:
            r143 = new FixedHeightEmptyCell(this.this$0.getContext(), 8);
            goto L24
        L17:
            r143 = new TextInfoPrivacyCell(r14.getContext(), 20, ChannelBoostLayout.m7266$$Nest$fgetresourcesProvider(this.this$0));
            goto L24
        L18:
            r143 = new GiftedUserCell(this.this$0.getContext(), 0, 0, false);
            goto L24
        L19:
            LimitPreviewView r4 = new LimitPreviewView(this.this$0.getContext(), R.drawable.filled_limit_boost, 0, 0, ChannelBoostLayout.m7266$$Nest$fgetresourcesProvider(this.this$0));
            r4.isStatistic = true;
            r4.setTag(-33024);
            r4.setPadding(0, AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f));
            r4.setBoosts(this.this$0.boostsStatus, false);
            r143 = r4;
            goto L24
        L20:
            LinkActionView r5 = new LinkActionView(this.this$0.getContext(), this.this$0.fragment, null, 0, false, false);
            r5.hideOptions();
            r5.setPadding(AndroidUtilities.dp(11.0f), 0, AndroidUtilities.dp(11.0f), AndroidUtilities.dp(24.0f));
            r143 = r5;
            goto L24
        L21:
            r143 = new ShadowSectionCell(r14.getContext(), 12, Theme.getColor(Theme.key_windowBackgroundGray));
            goto L24
        L22:
            View r148 = new ChartHeaderView(this.this$0.getContext());
            r148.setPadding(r148.getPaddingLeft(), AndroidUtilities.dp(16.0f), r148.getRight(), AndroidUtilities.dp(16.0f));
            r143 = r148;
            goto L24
        L23:
            r143 = new StatisticActivity.OverviewCell(this.this$0.getContext());
            goto L24
        }
    }

    /* renamed from: org.telegram.ui.ChannelBoostLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ ChannelBoostLayout this$0;

        public AnonymousClass2(ChannelBoostLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            ChannelBoostLayout.m7265$$Nest$fgetprogressLayout(this.this$0).setVisibility(8);
        }
    }

    public class ItemInternal extends AdapterWithDiffUtils.Item {
        TL_stories.Boost booster;
        boolean isLast;
        TL_stories.PrepaidGiveaway prepaidGiveaway;
        int tab;
        final /* synthetic */ ChannelBoostLayout this$0;
        String title;

        public ItemInternal(ChannelBoostLayout r1, int r2, String r3) {
            this.this$0 = r1;
            super(r2, false);
            this.title = r3;
        }

        public ItemInternal(ChannelBoostLayout r1, int r2, TL_stories.Boost r3, boolean r4, int r5) {
            this.this$0 = r1;
            super(r2, true);
            this.booster = r3;
            this.isLast = r4;
            this.tab = r5;
        }

        public ItemInternal(ChannelBoostLayout r1, int r2, TL_stories.PrepaidGiveaway r3, boolean r4) {
            this.this$0 = r1;
            super(r2, true);
            this.prepaidGiveaway = r3;
            this.isLast = r4;
        }

        public ItemInternal(ChannelBoostLayout r1, int r2, boolean r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        public boolean equals(Object r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L33:
            return false;
        L8:
            if (getClass() != r7.getClass()) goto L33;
            ItemInternal r72 = (ItemInternal) r7;
            TL_stories.PrepaidGiveaway r2 = this.prepaidGiveaway;
            if (r2 == null) goto L20;
            TL_stories.PrepaidGiveaway r3 = r72.prepaidGiveaway;
            if (r3 == null) goto L20;
            if (r2.id == r3.id) goto L17;
        L19:
            return false;
        L17:
            if (this.isLast != r72.isLast) goto L19;
            return true;
        L20:
            TL_stories.Boost r22 = this.booster;
            if (r22 != null) goto L23;
        L32:
            return true;
        L23:
            if (r72.booster == null) goto L32;
            if (r22.id.hashCode() == r72.booster.id.hashCode()) goto L27;
        L31:
            return false;
        L27:
            if (this.isLast != r72.isLast) goto L31;
            if (this.tab != r72.tab) goto L31;
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.title, this.booster, this.prepaidGiveaway, Boolean.valueOf(this.isLast), Integer.valueOf(this.tab)});
        }
    }

    public static /* synthetic */ void $r8$lambda$015FbW3eJs2yqc7AP0z_NyGif8E(ChannelBoostLayout r0, TL_stories.TL_premium_boostsStatus r1) {
        r0.lambda$loadStatistic$1(r1);
    }

    /* renamed from: $r8$lambda$5ub_VTUrQ5rbCQ5-mD5Wermerno, reason: not valid java name */
    public static /* synthetic */ void m7257$r8$lambda$5ub_VTUrQ5rbCQ5mD5Wermerno(ChannelBoostLayout r0, CountDownLatch r1, TLObject r2, Runnable r3) {
        r0.lambda$loadOnlyBoosts$7(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$EgcoehUXoT5JaMcpsKRCRguXnKQ(ChannelBoostLayout r0) {
        r0.lambda$loadUsers$4();
    }

    /* renamed from: $r8$lambda$Gh-urS3FtoHJm20tUPuMDQ0AnCc, reason: not valid java name */
    public static /* synthetic */ void m7258$r8$lambda$GhurS3FtoHJm20tUPuMDQ0AnCc(ChannelBoostLayout r0, Context r1, long r2, Theme.ResourcesProvider r4, BaseFragment r5, View r6, int r7) {
        r0.lambda$new$0(r1, r2, r4, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$MbNqT3-nOR2EdEcngSMP2g8ILT8, reason: not valid java name */
    public static /* synthetic */ void m7259$r8$lambda$MbNqT3nOR2EdEcngSMP2g8ILT8(ChannelBoostLayout r0, CountDownLatch r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$loadOnlyGifts$10(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$PkqECz9DenWRGpOjM3pkfpoqKG4(ChannelBoostLayout r0, TL_stories.TL_premium_boostsStatus r1) {
        r0.lambda$loadStatistic$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$Wh2dCJgGO9LlBDABjCEZxqeF1Vg(ChannelBoostLayout r0) {
        r0.lambda$loadUsers$5();
    }

    public static /* synthetic */ void $r8$lambda$d15h2ESYlTaRC3o8i4kbiZHzopE(ChannelBoostLayout r0) {
        r0.lambda$loadUsers$6();
    }

    /* renamed from: $r8$lambda$lKaiD1p1i-32O72w8CGoZoXP59k, reason: not valid java name */
    public static /* synthetic */ void m7260$r8$lambda$lKaiD1p1i32O72w8CGoZoXP59k(ChannelBoostLayout r0) {
        r0.lambda$loadUsers$3();
    }

    public static /* synthetic */ void $r8$lambda$mXlJx1UoTPbDHqk58ySSIzSX1KE(ChannelBoostLayout r0, CountDownLatch r1, TLObject r2, Runnable r3) {
        r0.lambda$loadOnlyGifts$9(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$tGcCu56cKhbwWEiM7lDU0HS5RlE(ChannelBoostLayout r0, CountDownLatch r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$loadOnlyBoosts$8(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetboostsTabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ScrollSlidingTextTabStrip m7261$$Nest$fgetboostsTabs(ChannelBoostLayout r0) {
        return r0.boostsTabs;
    }

    /* renamed from: -$$Nest$fgetitems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m7262$$Nest$fgetitems(ChannelBoostLayout r0) {
        return r0.items;
    }

    /* renamed from: -$$Nest$fgetnextBoostRemaining, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7263$$Nest$fgetnextBoostRemaining(ChannelBoostLayout r0) {
        return r0.nextBoostRemaining;
    }

    /* renamed from: -$$Nest$fgetnextGiftsRemaining, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7264$$Nest$fgetnextGiftsRemaining(ChannelBoostLayout r0) {
        return r0.nextGiftsRemaining;
    }

    /* renamed from: -$$Nest$fgetprogressLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m7265$$Nest$fgetprogressLayout(ChannelBoostLayout r0) {
        return r0.progressLayout;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m7266$$Nest$fgetresourcesProvider(ChannelBoostLayout r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetselectedTab, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7267$$Nest$fgetselectedTab(ChannelBoostLayout r0) {
        return r0.selectedTab;
    }

    /* renamed from: -$$Nest$fgettotalBoosts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7268$$Nest$fgettotalBoosts(ChannelBoostLayout r0) {
        return r0.totalBoosts;
    }

    /* renamed from: -$$Nest$fgettotalGifts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7269$$Nest$fgettotalGifts(ChannelBoostLayout r0) {
        return r0.totalGifts;
    }

    /* renamed from: -$$Nest$fputboostsTabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7270$$Nest$fputboostsTabs(ChannelBoostLayout r0, ScrollSlidingTextTabStrip r1) {
        r0.boostsTabs = r1;
    }

    /* renamed from: -$$Nest$fputselectedTab, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7271$$Nest$fputselectedTab(ChannelBoostLayout r0, int r1) {
        r0.selectedTab = r1;
    }

    /* renamed from: -$$Nest$misChannel, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7272$$Nest$misChannel(ChannelBoostLayout r0) {
        return r0.isChannel();
    }

    public ChannelBoostLayout(BaseFragment r10, long r11, Theme.ResourcesProvider r13) {
        super(r10.getContext());
        this.currentAccount = UserConfig.selectedAccount;
        this.boosters = new ArrayList();
        this.gifts = new ArrayList();
        this.items = new ArrayList();
        this.selectedTab = 0;
        this.adapter = new AnonymousClass1(this);
        this.lastBoostsOffset = _UrlKt.FRAGMENT_ENCODE_SET;
        this.lastGiftsOffset = _UrlKt.FRAGMENT_ENCODE_SET;
        this.limitGifts = 5;
        this.limitBoosts = 5;
        this.fragment = r10;
        Context r4 = r10.getContext();
        this.resourcesProvider = r13;
        this.dialogId = r11;
        this.currentChat = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r11));
        RecyclerListView r1 = new RecyclerListView(r4);
        this.listView = r1;
        r1.setSegmentedSectionsEnabled(false);
        r1.setSections(true);
        r1.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray, r13));
        r1.setLayoutManager(new LinearLayoutManager(r4));
        DefaultItemAnimator r2 = new DefaultItemAnimator();
        r2.setSupportsChangeAnimations(false);
        r2.setDelayAnimations(false);
        r1.setItemAnimator(r2);
        r1.setClipToPadding(false);
        r1.setOnItemClickListener(new ChannelBoostLayout$$ExternalSyntheticLambda0(this, r4, r11, r13, r10));
        addView(r1);
        loadStatistic();
        r1.setAdapter(this.adapter);
        updateRows(false);
        createEmptyView(getContext());
        this.progressLayout.setAlpha(0.0f);
        this.progressLayout.animate().alpha(1.0f).setDuration(200).setStartDelay(500).start();
        StarsController.getInstance(this.currentAccount).getGiveawayOptions();
    }

    private boolean isChannel() {
        return ChatObject.isChannelAndNotMegaGroup(this.currentChat);
    }

    private /* synthetic */ void lambda$loadOnlyBoosts$7(CountDownLatch r7, TLObject r8, Runnable r9) {
        if (r7 == null) goto L4;
        r7.countDown();
    L4:
        if (r8 == null) goto L24;
        this.limitBoosts = 20;
        TL_stories.TL_premium_boostsList r82 = (TL_stories.TL_premium_boostsList) r8;
        boolean r1 = false;
        MessagesController.getInstance(this.currentAccount).putUsers(r82.users, false);
        this.lastBoostsOffset = r82.next_offset;
        this.boosters.addAll(r82.boosts);
        ArrayList<TL_stories.Boost> r72 = this.boosters;
        int r0 = r72.size();
        int r2 = 0;
        int r3 = 0;
    L6:
        int r4 = 1;
        if (r3 >= r0) goto L12;
        TL_stories.Boost r5 = r72.get(r3);
        r3 = r3 + 1;
        int r52 = r5.multiplier;
        if (r52 <= 0) goto L11;
        r4 = r52;
    L11:
        r2 = r2 + r4;
        goto L6
    L12:
        this.nextBoostRemaining = Math.max(0, r82.count - r2);
        if (TextUtils.isEmpty(r82.next_offset) == false) goto L15;
    L17:
        this.hasBoostsNext = r1;
        this.totalBoosts = r82.count;
        if (r9 == null) goto L25;
        r9.run();
        return;
    L25:
        return;
    L15:
        if (this.nextBoostRemaining <= 0) goto L17;
        r1 = true;
        goto L17
    }

    private /* synthetic */ void lambda$loadOnlyBoosts$8(CountDownLatch r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new ChannelBoostLayout$$ExternalSyntheticLambda10(this, r1, r3, r2));
    }

    private /* synthetic */ void lambda$loadOnlyGifts$10(CountDownLatch r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new ChannelBoostLayout$$ExternalSyntheticLambda9(this, r1, r3, r2));
    }

    private /* synthetic */ void lambda$loadOnlyGifts$9(CountDownLatch r7, TLObject r8, Runnable r9) {
        if (r7 == null) goto L4;
        r7.countDown();
    L4:
        if (r8 == null) goto L24;
        this.limitGifts = 20;
        TL_stories.TL_premium_boostsList r82 = (TL_stories.TL_premium_boostsList) r8;
        boolean r1 = false;
        MessagesController.getInstance(this.currentAccount).putUsers(r82.users, false);
        this.lastGiftsOffset = r82.next_offset;
        this.gifts.addAll(r82.boosts);
        ArrayList<TL_stories.Boost> r72 = this.gifts;
        int r0 = r72.size();
        int r2 = 0;
        int r3 = 0;
    L6:
        int r4 = 1;
        if (r3 >= r0) goto L12;
        TL_stories.Boost r5 = r72.get(r3);
        r3 = r3 + 1;
        int r52 = r5.multiplier;
        if (r52 <= 0) goto L11;
        r4 = r52;
    L11:
        r2 = r2 + r4;
        goto L6
    L12:
        this.nextGiftsRemaining = Math.max(0, r82.count - r2);
        if (TextUtils.isEmpty(r82.next_offset) == false) goto L15;
    L17:
        this.hasGiftsNext = r1;
        this.totalGifts = r82.count;
        if (r9 == null) goto L25;
        r9.run();
        return;
    L25:
        return;
    L15:
        if (this.nextGiftsRemaining <= 0) goto L17;
        r1 = true;
        goto L17
    }

    private /* synthetic */ void lambda$loadStatistic$1(TL_stories.TL_premium_boostsStatus r3) {
        this.boostsStatus = r3;
        this.progressLayout.animate().cancel();
        this.progressLayout.animate().alpha(0.0f).setDuration(100).setStartDelay(0).setListener(new AnonymousClass2(this));
        updateRows(true);
        loadUsers(null);
    }

    private /* synthetic */ void lambda$loadStatistic$2(TL_stories.TL_premium_boostsStatus r2) {
        AndroidUtilities.runOnUIThread(new ChannelBoostLayout$$ExternalSyntheticLambda8(this, r2));
    }

    private /* synthetic */ void lambda$loadUsers$3() {
        this.usersLoading = false;
        updateRows(true);
    }

    private /* synthetic */ void lambda$loadUsers$4() {
        CountDownLatch r0 = new CountDownLatch(2);
        loadOnlyBoosts(r0, null);
        loadOnlyGifts(r0, null);
        r0.await();     // Catch: InterruptedException -> L6
    L4:
        AndroidUtilities.runOnUIThread(new ChannelBoostLayout$$ExternalSyntheticLambda7(this));
    }

    private /* synthetic */ void lambda$loadUsers$5() {
        this.usersLoading = false;
        updateRows(true);
    }

    private /* synthetic */ void lambda$loadUsers$6() {
        this.usersLoading = false;
        updateRows(true);
    }

    private /* synthetic */ void lambda$new$0(Context r18, long r19, Theme.ResourcesProvider r21, BaseFragment r22, View r23, int r24) {
        boolean r9 = false;
        if ((r23 instanceof GiftedUserCell) == false) goto L31;
        GiftedUserCell r1 = (GiftedUserCell) r23;
        TL_stories.Boost r5 = r1.getBoost();
        boolean r2 = r5.giveaway;
        if (r2 == true) goto L7;
    L10:
        long r10 = r19;
        Theme.ResourcesProvider r12 = r21;
        boolean r6 = r5.gift;
        if (r6 == true) goto L14;
        if (r2 == true) goto L14;
    L16:
        if (r5.unclaimed == true) goto L25;
        if (r2 == true) goto L20;
    L22:
        if (r6 == false) goto L23;
    L9:
        BaseFragment r25 = r22;
    L33:
        if ((r23 instanceof TextCell) == false) goto L36;
        BoostPagerBottomSheet.show(r25, r10, r12);
    L36:
        if ((r23 instanceof GiveawayCell) == false) goto L39;
        BoostPagerBottomSheet.show(r25, r12, r10, ((GiveawayCell) r23).getPrepaidGiveaway());
    L39:
        if (this.items.get(r24).viewType == 9) goto L41;
        return;
    L41:
        if (this.selectedTab != 1) goto L43;
        r9 = true;
    L43:
        loadUsers(Boolean.valueOf(r9));
        return;
    L23:
        if (r2 == true) goto L9;
        r22.presentFragment(ProfileActivity.of(r1.getDialogId()));
        goto L9
    L20:
        if (r5.user_id != (-1)) goto L22;
        Bulletin.LottieLayout r13 = new Bulletin.LottieLayout(r22.getParentActivity(), r22.getResourceProvider());
        r13.setAnimation(R.raw.chats_infotip, 36, 36, new String[0]);
        r13.textView.setText(LocaleController.getString(R.string.BoostingRecipientWillBeSelected));
        r13.textView.setSingleLine(false);
        r13.textView.setMaxLines(2);
        Bulletin.make(r22, r13, 2750).show();
    L25:
        TLRPC.TL_payments_checkedGiftCode r14 = new TLRPC.TL_payments_checkedGiftCode();
        r14.giveaway_msg_id = r5.giveaway_msg_id;
        r14.to_id = r5.user_id;
        r14.from_id = MessagesController.getInstance(UserConfig.selectedAccount).getPeer(-this.currentChat.id);
        int r26 = r5.date;
        r14.date = r26;
        r14.via_giveaway = r5.giveaway;
        int r3 = r5.expires;
        r14.days = (r3 - r26) / 86400;
        r14.months = ((r3 - r26) / 30) / 86400;
        if (r5.unclaimed == false) goto L29;
        r14.to_id = -1;
        r14.flags = -1;
    L30:
        r25 = r22;
        new GiftInfoBottomSheet(r25, false, true, r14, r5.used_gift_slug).show();
        goto L33
    L29:
        r14.boost = r5;
    L14:
        if (r5.user_id >= 0) goto L25;
    L7:
        if (r5.stars <= 0) goto L10;
        StarsIntroActivity.showBoostsSheet(r18, this.currentAccount, r19, r5, r21);
        r10 = r19;
        r12 = r21;
        goto L9
    L31:
        r10 = r19;
        r12 = r21;
        goto L9
    }

    private void loadOnlyBoosts(CountDownLatch r5, Runnable r6) {
        TL_stories.TL_premium_getBoostsList r0 = new TL_stories.TL_premium_getBoostsList();
        r0.limit = this.limitBoosts;
        r0.offset = this.lastBoostsOffset;
        r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new ChannelBoostLayout$$ExternalSyntheticLambda6(this, r5, r6), 2);
    }

    private void loadOnlyGifts(CountDownLatch r5, Runnable r6) {
        TL_stories.TL_premium_getBoostsList r0 = new TL_stories.TL_premium_getBoostsList();
        r0.limit = this.limitGifts;
        r0.gifts = true;
        r0.offset = this.lastGiftsOffset;
        r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new ChannelBoostLayout$$ExternalSyntheticLambda5(this, r5, r6), 2);
    }

    private void loadStatistic() {
        MessagesController.getInstance(this.currentAccount).getBoostsController().getBoostsStats(this.dialogId, new ChannelBoostLayout$$ExternalSyntheticLambda1(this));
    }

    private void loadUsers(Boolean r2) {
        if (this.usersLoading == false) goto L5;
        return;
    L5:
        this.usersLoading = true;
        if (r2 != null) goto L10;
        Utilities.globalQueue.postRunnable(new ChannelBoostLayout$$ExternalSyntheticLambda2(this));
        return;
    L10:
        if (r2.booleanValue() == false) goto L13;
        loadOnlyGifts(null, new ChannelBoostLayout$$ExternalSyntheticLambda3(this));
        return;
    L13:
        loadOnlyBoosts(null, new ChannelBoostLayout$$ExternalSyntheticLambda4(this));
    }

    public void createEmptyView(Context r12) {
        LinearLayout r0 = new LinearLayout(r12);
        this.progressLayout = r0;
        r0.setOrientation(1);
        RLottieImageView r02 = new RLottieImageView(r12);
        r02.setAutoRepeat(true);
        r02.setAnimation(R.raw.statistic_preload, 120, 120);
        r02.playAnimation();
        TextView r2 = new TextView(r12);
        r2.setTextSize(1, 20.0f);
        r2.setTypeface(AndroidUtilities.bold());
        int r3 = Theme.key_player_actionBarTitle;
        r2.setTextColor(Theme.getColor(r3));
        r2.setTag(Integer.valueOf(r3));
        r2.setText(LocaleController.getString(R.string.LoadingStats));
        r2.setGravity(1);
        TextView r32 = new TextView(r12);
        r32.setTextSize(1, 15.0f);
        int r122 = Theme.key_player_actionBarSubtitle;
        r32.setTextColor(Theme.getColor(r122));
        r32.setTag(Integer.valueOf(r122));
        r32.setText(LocaleController.getString(R.string.LoadingStatsDescription));
        r32.setGravity(1);
        this.progressLayout.addView(r02, LayoutHelper.createLinear(120, 120, 1, 0, 0, 0, 20));
        this.progressLayout.addView(r2, LayoutHelper.createLinear(-2, -2, 1, 0, 0, 0, 10));
        this.progressLayout.addView(r32, LayoutHelper.createLinear(-2, -2, 1));
        addView(this.progressLayout, LayoutHelper.createFrame(240, -2.0f, 17, 0.0f, 0.0f, 0.0f, 30.0f));
    }

    public void updateRows(boolean r15) {
        ArrayList<? extends AdapterWithDiffUtils.Item> r6 = new ArrayList(this.items);
        this.items.clear();
        if (this.boostsStatus == null) goto L74;
        this.items.add(new ItemInternal(this, 4, false));
        this.items.add(new ItemInternal(this, 1, LocaleController.getString(R.string.StatisticOverview)));
        this.items.add(new ItemInternal(this, 0, false));
        this.items.add(new ItemInternal(this, 2, false));
        if (this.boostsStatus.prepaid_giveaways.size() <= 0) goto L15;
        this.items.add(new ItemInternal(this, 12, LocaleController.getString(R.string.BoostingPreparedGiveaways)));
        int r0 = 0;
    L8:
        if (r0 >= this.boostsStatus.prepaid_giveaways.size()) goto L14;
        TL_stories.PrepaidGiveaway r2 = this.boostsStatus.prepaid_giveaways.get(r0);
        ArrayList<ItemInternal> r4 = this.items;
        if (r0 != (this.boostsStatus.prepaid_giveaways.size() - 1)) goto L12;
        boolean r10 = true;
    L13:
        r4.add(new ItemInternal(this, 11, r2, r10));
        r0 = r0 + 1;
        goto L8
    L12:
        r10 = false;
        goto L13
    L14:
        this.items.add(new ItemInternal(this, 6, LocaleController.getString(R.string.BoostingSelectPaidGiveaway)));
    L15:
        this.items.add(new ItemInternal(this, 13, LocaleController.getString(R.string.Boosters)));
        if (this.selectedTab != 0) goto L40;
        if (this.boosters.isEmpty() == false) goto L20;
        this.items.add(new ItemInternal(this, 8, false));
        this.items.add(new ItemInternal(this, 2, false));
    L61:
        this.items.add(new ItemInternal(this, 1, LocaleController.getString(R.string.LinkForBoosting)));
        this.items.add(new ItemInternal(this, 3, this.boostsStatus.boost_url));
        if (MessagesController.getInstance(this.currentAccount).giveawayGiftsPurchaseAvailable == false) goto L74;
        if (ChatObject.hasAdminRights(this.currentChat) == false) goto L74;
        ArrayList<ItemInternal> r02 = this.items;
        if (isChannel() == false) goto L68;
        int r3 = R.string.BoostingShareThisLink;
    L69:
        r02.add(new ItemInternal(this, 6, LocaleController.getString(r3)));
        this.items.add(new ItemInternal(this, 10, true));
        ArrayList<ItemInternal> r03 = this.items;
        if (isChannel() == false) goto L72;
        int r32 = R.string.BoostingGetMoreBoosts2;
    L73:
        r03.add(new ItemInternal(this, 6, LocaleController.getString(r32)));
        goto L74
    L72:
        r32 = R.string.BoostingGetMoreBoostsGroup;
        goto L73
    L68:
        r3 = R.string.BoostingShareThisLinkGroup;
        goto L69
    L20:
        int r12 = 0;
    L22:
        if (r12 >= this.boosters.size()) goto L30;
        ArrayList<ItemInternal> r13 = this.items;
        TL_stories.Boost r33 = this.boosters.get(r12);
        if (r12 == (this.boosters.size() - 1)) goto L26;
    L28:
        boolean r42 = false;
    L29:
        r13.add(new ItemInternal(this, 5, r33, r42, this.selectedTab));
        r12 = r12 + 1;
        goto L22
    L26:
        if (this.hasBoostsNext == true) goto L28;
        r42 = true;
        goto L29
    L30:
        boolean r04 = this.hasBoostsNext;
        ArrayList<ItemInternal> r22 = this.items;
        if (r04 == false) goto L33;
        r22.add(new ItemInternal(this, 9, true));
    L34:
        ArrayList<ItemInternal> r05 = this.items;
        if (isChannel() == false) goto L37;
        int r34 = R.string.BoostersInfoDescription;
    L38:
        r05.add(new ItemInternal(this, 6, LocaleController.getString(r34)));
        goto L61
    L37:
        r34 = R.string.BoostersInfoGroupDescription;
        goto L38
    L33:
        r22.add(new ItemInternal(this, 7, false));
        goto L34
    L40:
        if (this.gifts.isEmpty() == false) goto L42;
        this.items.add(new ItemInternal(this, 8, false));
        this.items.add(new ItemInternal(this, 2, false));
        goto L61
    L42:
        int r122 = 0;
    L44:
        if (r122 >= this.gifts.size()) goto L52;
        ArrayList<ItemInternal> r132 = this.items;
        TL_stories.Boost r35 = this.gifts.get(r122);
        if (r122 == (this.gifts.size() - 1)) goto L48;
    L50:
        boolean r43 = false;
    L51:
        r132.add(new ItemInternal(this, 5, r35, r43, this.selectedTab));
        r122 = r122 + 1;
        goto L44
    L48:
        if (this.hasGiftsNext == true) goto L50;
        r43 = true;
        goto L51
    L52:
        boolean r06 = this.hasGiftsNext;
        ArrayList<ItemInternal> r23 = this.items;
        if (r06 == false) goto L55;
        r23.add(new ItemInternal(this, 9, true));
    L56:
        ArrayList<ItemInternal> r07 = this.items;
        if (isChannel() == false) goto L59;
        int r36 = R.string.BoostersInfoDescription;
    L60:
        r07.add(new ItemInternal(this, 6, LocaleController.getString(r36)));
        goto L61
    L59:
        r36 = R.string.BoostersInfoGroupDescription;
        goto L60
    L55:
        r23.add(new ItemInternal(this, 7, false));
    L74:
        AdapterWithDiffUtils r08 = this.adapter;
        if (r15 == false) goto L78;
        r08.setItems(r6, this.items);
        return;
    L78:
        r08.notifyDataSetChanged();
    }
}
