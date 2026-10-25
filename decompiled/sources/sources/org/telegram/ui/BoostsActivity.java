package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
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
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.FillLastLinearLayoutManager;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkActionView;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.Premium.LimitPreviewView;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.Premium.boosts.BoostDialogs;
import org.telegram.ui.Components.Premium.boosts.BoostPagerBottomSheet;
import org.telegram.ui.Components.Premium.boosts.GiftInfoBottomSheet;
import org.telegram.ui.Components.Premium.boosts.cells.statistics.GiftedUserCell;
import org.telegram.ui.Components.Premium.boosts.cells.statistics.GiveawayCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScrollSlidingTextTabStrip;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.StatisticActivity;

/* loaded from: classes6.dex */
public class BoostsActivity extends GradientHeaderActivity implements NotificationCenter.NotificationCenterDelegate {
    AdapterWithDiffUtils adapter;
    private final ArrayList<TL_stories.Boost> boosters;
    private TL_stories.TL_premium_boostsStatus boostsStatus;
    private ScrollSlidingTextTabStrip boostsTabs;
    private ChannelBoostsController.CanApplyBoost canApplyBoost;
    int currentAccount;
    private final TLRPC.Chat currentChat;
    private final long dialogId;
    private final ArrayList<TL_stories.Boost> gifts;
    private boolean hasBoostsNext;
    private boolean hasGiftsNext;
    private final ArrayList<ItemInternal> items;
    private String lastBoostsOffset;
    private String lastGiftsOffset;
    private int limitBoosts;
    private int limitGifts;
    private LimitPreviewView limitPreviewView;
    private int nextBoostRemaining;
    private int nextGiftsRemaining;
    private LinearLayout progressLayout;
    private int selectedTab;
    private int totalBoosts;
    private int totalGifts;
    boolean usersLoading;

    /* renamed from: org.telegram.ui.BoostsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends AdapterWithDiffUtils {
        final /* synthetic */ BoostsActivity this$0;

        /* renamed from: org.telegram.ui.BoostsActivity$1$1, reason: invalid class name and collision with other inner class name */
        public class C00351 extends View {
            final /* synthetic */ AnonymousClass1 this$1;

            public C00351(AnonymousClass1 r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r3, int r4) {
                LinearLayoutManager r42 = this.this$1.this$0.layoutManager;
                if ((r42 instanceof FillLastLinearLayoutManager) == false) goto L5;
                int r43 = ((FillLastLinearLayoutManager) r42).getLastItemHeight();
            L6:
                super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(Math.max(0, r43), TLObject.FLAG_30));
                return;
            L5:
                r43 = 0;
                goto L6
            }
        }

        /* renamed from: org.telegram.ui.BoostsActivity$1$2, reason: invalid class name */
        public class AnonymousClass2 extends FrameLayout {
            private final Paint dividerPaint;
            final /* synthetic */ AnonymousClass1 this$1;

            public AnonymousClass2(AnonymousClass1 r1, Context r2) {
                this.this$1 = r1;
                super(r2);
                this.dividerPaint = new Paint(1);
            }

            @Override // android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r8) {
                super.dispatchDraw(r8);
                this.dividerPaint.setColor(Theme.getColor(Theme.key_windowBackgroundGray, BoostsActivity.access$200(this.this$1.this$0)));
                r8.drawRect(0.0f, getHeight() - 2, getWidth(), getHeight(), this.dividerPaint);
            }
        }

        /* renamed from: org.telegram.ui.BoostsActivity$1$3, reason: invalid class name */
        public class AnonymousClass3 implements ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate {
            final /* synthetic */ AnonymousClass1 this$1;

            public AnonymousClass3(AnonymousClass1 r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.Components.ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate
            public void onPageScrolled(float r1) {
            }

            @Override // org.telegram.ui.Components.ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate
            public void onPageSelected(int r1, boolean r2) {
                BoostsActivity.m6300$$Nest$fputselectedTab(this.this$1.this$0, r1);
                this.this$1.this$0.updateRows(true);
            }

            @Override // org.telegram.ui.Components.ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate
            public void onSamePageSelected() {
            }
        }

        /* renamed from: org.telegram.ui.BoostsActivity$1$4, reason: invalid class name */
        public class AnonymousClass4 extends FrameLayout {
            final /* synthetic */ AnonymousClass1 this$1;

            public AnonymousClass4(AnonymousClass1 r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
            }
        }

        /* renamed from: org.telegram.ui.BoostsActivity$1$5, reason: invalid class name */
        public class AnonymousClass5 extends ManageChatTextCell {
            final /* synthetic */ AnonymousClass1 this$1;

            public AnonymousClass5(AnonymousClass1 r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Cells.ManageChatTextCell
            public int getFullHeight() {
                return AndroidUtilities.dp(50.0f);
            }
        }

        public AnonymousClass1(BoostsActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return BoostsActivity.m6291$$Nest$fgetitems(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return ((ItemInternal) BoostsActivity.m6291$$Nest$fgetitems(this.this$0).get(r1)).viewType;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return ((ItemInternal) BoostsActivity.m6291$$Nest$fgetitems(this.this$0).get(r1.getAdapterPosition())).selectable;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r13, int r14) {
            if (r13.getItemViewType() != 4) goto L5;
            return;
        L5:
            if (r13.getItemViewType() != 14) goto L7;
            return;
        L7:
            if (r13.getItemViewType() != 15) goto L10;
            return;
        L10:
            if (r13.getItemViewType() != 1) goto L12;
        L84:
            ChartHeaderView r0 = (ChartHeaderView) r13.itemView;
            r0.setTitle(((ItemInternal) BoostsActivity.m6291$$Nest$fgetitems(this.this$0).get(r14)).title);
            r0.showDate(false);
            if (r13.getItemViewType() != 12) goto L92;
            r0.setPadding(AndroidUtilities.dp(3.0f), r0.getPaddingTop(), r0.getPaddingRight(), r0.getPaddingBottom());
            return;
        L92:
            return;
        L12:
            if (r13.getItemViewType() == 12) goto L84;
            if (r13.getItemViewType() == 16) goto L84;
            if (r13.getItemViewType() != 0) goto L35;
            StatisticActivity.OverviewCell r132 = (StatisticActivity.OverviewCell) r13.itemView;
            r132.setData(0, Integer.toString(BoostsActivity.m6286$$Nest$fgetboostsStatus(this.this$0).level), null, LocaleController.getString(R.string.BoostsLevel2));
            if (BoostsActivity.m6286$$Nest$fgetboostsStatus(this.this$0).premium_audience == null) goto L28;
            if (BoostsActivity.m6286$$Nest$fgetboostsStatus(this.this$0).premium_audience.total == 0.0d) goto L28;
            float r142 = (((float) BoostsActivity.m6286$$Nest$fgetboostsStatus(this.this$0).premium_audience.part) / ((float) BoostsActivity.m6286$$Nest$fgetboostsStatus(this.this$0).premium_audience.total)) * 100.0f;
            String r02 = "≈" + ((int) BoostsActivity.m6286$$Nest$fgetboostsStatus(this.this$0).premium_audience.part);
            String r143 = String.format(Locale.US, "%.1f", new Object[]{Float.valueOf(r142)}).concat("%");
            if (BoostsActivity.m6301$$Nest$misChannel(this.this$0) == false) goto L25;
            int r5 = R.string.PremiumSubscribers;
        L26:
            r132.setData(1, r02, r143, LocaleController.getString(r5));
        L32:
            r132.setData(2, String.valueOf(BoostsActivity.m6286$$Nest$fgetboostsStatus(this.this$0).boosts), null, LocaleController.getString(R.string.BoostsExisting));
            r132.setData(3, String.valueOf(Math.max(0, BoostsActivity.m6286$$Nest$fgetboostsStatus(this.this$0).next_level_boosts - BoostsActivity.m6286$$Nest$fgetboostsStatus(this.this$0).boosts)), null, LocaleController.getString(R.string.BoostsToLevel));
            r132.setPadding(AndroidUtilities.dp(23.0f), r132.getPaddingTop(), AndroidUtilities.dp(23.0f), r132.getPaddingBottom());
            return;
        L25:
            r5 = R.string.PremiumMembers;
        L28:
            if (BoostsActivity.m6301$$Nest$misChannel(this.this$0) == false) goto L30;
            int r144 = R.string.PremiumSubscribers;
        L31:
            r132.setData(1, "~0", "0%", LocaleController.getString(r144));
            goto L32
        L30:
            r144 = R.string.PremiumMembers;
            goto L31
        L35:
            if (r13.getItemViewType() != 5) goto L44;
            TL_stories.Boost r03 = ((ItemInternal) BoostsActivity.m6291$$Nest$fgetitems(this.this$0).get(r14)).booster;
            TLRPC.User r7 = MessagesController.getInstance(this.this$0.currentAccount).getUser(Long.valueOf(r03.user_id));
            GiftedUserCell r6 = (GiftedUserCell) r13.itemView;
            int r133 = r03.multiplier;
            int r1 = r03.expires;
            if (r133 <= 1) goto L40;
            String r134 = LocaleController.formatString("BoostsExpireOn", R.string.BoostsExpireOn, new Object[]{LocaleController.formatDate(r1)});
        L41:
            r6.setData(r7, ContactsController.formatName(r7), r134, 0, !((ItemInternal) BoostsActivity.m6291$$Nest$fgetitems(this.this$0).get(r14)).isLast);
            r6.setStatus(r03);
            r6.setAvatarPadding(5);
            return;
        L40:
            r134 = LocaleController.formatString("BoostExpireOn", R.string.BoostExpireOn, new Object[]{LocaleController.formatDate(r1)});
            goto L41
        L44:
            if (r13.getItemViewType() != 6) goto L48;
            TextInfoPrivacyCell r135 = (TextInfoPrivacyCell) r13.itemView;
            r135.setText(((ItemInternal) BoostsActivity.m6291$$Nest$fgetitems(this.this$0).get(r14)).title);
            r135.setTextColor(Theme.multAlpha(-1, 0.875f));
            return;
        L48:
            if (r13.getItemViewType() != 9) goto L56;
            ManageChatTextCell r136 = (ManageChatTextCell) r13.itemView;
            int r145 = BoostsActivity.m6296$$Nest$fgetselectedTab(this.this$0);
            BoostsActivity r12 = this.this$0;
            if (r145 != 0) goto L53;
            r136.setText(LocaleController.formatPluralString("BoostingShowMoreBoosts", BoostsActivity.m6293$$Nest$fgetnextBoostRemaining(r12), new Object[0]), null, R.drawable.arrow_more, false);
            return;
        L53:
            r136.setText(LocaleController.formatPluralString("BoostingShowMoreGifts", BoostsActivity.m6294$$Nest$fgetnextGiftsRemaining(r12), new Object[0]), null, R.drawable.arrow_more, false);
            return;
        L56:
            if (r13.getItemViewType() != 3) goto L60;
            ((LinkActionView) r13.itemView).setLink(((ItemInternal) BoostsActivity.m6291$$Nest$fgetitems(this.this$0).get(r14)).title);
            return;
        L60:
            if (r13.getItemViewType() != 11) goto L70;
            TL_stories.PrepaidGiveaway r72 = ((ItemInternal) BoostsActivity.m6291$$Nest$fgetitems(this.this$0).get(r14)).prepaidGiveaway;
            GiveawayCell r62 = (GiveawayCell) r13.itemView;
            if ((r72 instanceof TL_stories.TL_prepaidGiveaway) == false) goto L65;
            r62.setData(r72, LocaleController.formatPluralString("BoostingTelegramPremiumCountPlural", r72.quantity, new Object[0]), LocaleController.formatPluralString("BoostingSubscriptionsCountPlural", r72.quantity, new Object[]{LocaleController.formatPluralString("PrepaidGiveawayMonths", ((TL_stories.TL_prepaidGiveaway) r72).months, new Object[0])}), 0, !r12.isLast);
        L67:
            r62.setImage(r72);
            r62.setAvatarPadding(5);
            return;
        L65:
            if ((r72 instanceof TL_stories.TL_prepaidStarsGiveaway) == false) goto L67;
            TL_stories.TL_prepaidStarsGiveaway r137 = (TL_stories.TL_prepaidStarsGiveaway) r72;
            r62.setData(r72, LocaleController.formatPluralStringComma("BoostingStarsCountPlural", (int) r137.stars), LocaleController.formatPluralString("AmongWinners", r137.quantity, new Object[0]), 0, !r12.isLast);
            goto L67
        L70:
            if (r13.getItemViewType() == 13) goto L72;
            return;
        L72:
            if (BoostsActivity.m6287$$Nest$fgetboostsTabs(this.this$0).getTag() != null) goto L74;
        L75:
            BoostsActivity.m6287$$Nest$fgetboostsTabs(this.this$0).setTag(Integer.valueOf(Objects.hash(new Object[]{Integer.valueOf(BoostsActivity.m6297$$Nest$fgettotalBoosts(this.this$0)), Integer.valueOf(BoostsActivity.m6298$$Nest$fgettotalGifts(this.this$0))})));
            BoostsActivity.m6287$$Nest$fgetboostsTabs(this.this$0).removeTabs();
            BoostsActivity.m6287$$Nest$fgetboostsTabs(this.this$0).addTextTab(0, LocaleController.formatPluralString("BoostingBoostsCount", BoostsActivity.m6297$$Nest$fgettotalBoosts(this.this$0), new Object[0]));
            if (MessagesController.getInstance(this.this$0.currentAccount).giveawayGiftsPurchaseAvailable == true) goto L78;
        L82:
            BoostsActivity.m6287$$Nest$fgetboostsTabs(this.this$0).setInitialTabId(BoostsActivity.m6296$$Nest$fgetselectedTab(this.this$0));
            BoostsActivity.m6287$$Nest$fgetboostsTabs(this.this$0).finishAddingTabs();
            return;
        L78:
            if (BoostsActivity.m6298$$Nest$fgettotalGifts(this.this$0) <= 0) goto L82;
            if (BoostsActivity.m6298$$Nest$fgettotalGifts(this.this$0) == BoostsActivity.m6297$$Nest$fgettotalBoosts(this.this$0)) goto L82;
            BoostsActivity.m6287$$Nest$fgetboostsTabs(this.this$0).addTextTab(1, LocaleController.formatPluralString("BoostingGiftsCount", BoostsActivity.m6298$$Nest$fgettotalGifts(this.this$0), new Object[0]));
            goto L82
        L74:
            if (((Integer) BoostsActivity.m6287$$Nest$fgetboostsTabs(this.this$0).getTag()).intValue() != Objects.hash(new Object[]{Integer.valueOf(BoostsActivity.m6297$$Nest$fgettotalBoosts(this.this$0)), Integer.valueOf(BoostsActivity.m6298$$Nest$fgettotalGifts(this.this$0))})) goto L75;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r12, int r13) {
            switch(r13) {
                case 0: goto L24;
                case 1: goto L23;
                case 2: goto L22;
                case 3: goto L21;
                case 4: goto L4;
                case 5: goto L20;
                case 6: goto L19;
                case 7: goto L18;
                case 8: goto L13;
                case 9: goto L12;
                case 10: goto L11;
                case 11: goto L10;
                case 12: goto L9;
                case 13: goto L8;
                case 14: goto L7;
                case 15: goto L6;
                case 16: goto L23;
                default: goto L4;
            };
        L4:
            Type$$ExternalSyntheticBUOutline0.m();
            return null;
        L6:
            View r122 = new C00351(this, this.this$0.getContext());
            r122.setTag(-33024);
            View r123 = r122;
        L25:
            r123.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r123);
        L7:
            BoostsActivity r11 = this.this$0;
            r123 = r11.getHeader(r11.getContext());
            goto L25
        L8:
            BoostsActivity.m6299$$Nest$fputboostsTabs(this.this$0, new ScrollSlidingTextTabStrip(this.this$0.getContext(), BoostsActivity.access$100(this.this$0)));
            BoostsActivity.m6287$$Nest$fgetboostsTabs(this.this$0).setColors(Theme.key_profile_tabSelectedLine, Theme.key_profile_tabSelectedText, Theme.key_profile_tabText, Theme.key_profile_tabSelector);
            AnonymousClass2 r124 = new AnonymousClass2(this, this.this$0.getContext());
            BoostsActivity.m6287$$Nest$fgetboostsTabs(this.this$0).setDelegate(new AnonymousClass3(this));
            r124.addView(BoostsActivity.m6287$$Nest$fgetboostsTabs(this.this$0), LayoutHelper.createFrame(-2, 48.0f));
            r123 = r124;
            goto L25
        L9:
            View r125 = new ChartHeaderView(this.this$0.getContext());
            r125.setPadding(r125.getPaddingLeft(), AndroidUtilities.dp(16.0f), r125.getRight(), AndroidUtilities.dp(8.0f));
            r123 = r125;
            goto L25
        L10:
            r123 = new GiveawayCell(this.this$0.getContext(), 0, 0, false);
            goto L25
        L11:
            TextCell r126 = new TextCell(this.this$0.getContext());
            r126.setTextAndIcon(LocaleController.formatString(R.string.BoostingGetBoostsViaGifts, new Object[0]), R.drawable.msg_gift_premium, false);
            r126.offsetFromImage = 64;
            int r112 = Theme.key_windowBackgroundWhiteBlueText4;
            r126.setColors(r112, r112);
            r123 = r126;
            goto L25
        L12:
            AnonymousClass5 r127 = new AnonymousClass5(this, this.this$0.getContext());
            r127.setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
            r123 = r127;
            goto L25
        L13:
            AnonymousClass4 r128 = new AnonymousClass4(this, this.this$0.getContext());
            TextView r132 = new TextView(this.this$0.getContext());
            if (BoostsActivity.m6301$$Nest$misChannel(this.this$0) == false) goto L16;
            int r113 = R.string.NoBoostersHint;
        L17:
            r132.setText(LocaleController.getString(r113));
            r132.setTextSize(1, 14.0f);
            r132.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
            r132.setGravity(17);
            r128.addView(r132, LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 16.0f, 0.0f, 0.0f));
            r123 = r128;
            goto L25
        L16:
            r113 = R.string.NoBoostersGroupHint;
            goto L17
        L18:
            r123 = new FixedHeightEmptyCell(this.this$0.getContext(), 8);
            goto L25
        L19:
            r123 = new TextInfoPrivacyCell(r12.getContext(), 20, BoostsActivity.access$000(this.this$0));
            goto L25
        L20:
            r123 = new GiftedUserCell(this.this$0.getContext(), 0, 0, false);
            goto L25
        L21:
            LinkActionView r3 = new LinkActionView(this.this$0.getContext(), this.this$0, null, 0, false, false);
            r3.hideOptions();
            r3.setPadding(AndroidUtilities.dp(11.0f), 0, AndroidUtilities.dp(11.0f), AndroidUtilities.dp(24.0f));
            r123 = r3;
            goto L25
        L22:
            r123 = new ShadowSectionCell(r12.getContext(), 12, 0);
            goto L25
        L23:
            View r129 = new ChartHeaderView(this.this$0.getContext());
            r129.setPadding(r129.getPaddingLeft(), AndroidUtilities.dp(16.0f), r129.getRight(), AndroidUtilities.dp(16.0f));
            r123 = r129;
            goto L25
        L24:
            r123 = new StatisticActivity.OverviewCell(this.this$0.getContext());
            goto L25
        }
    }

    /* renamed from: org.telegram.ui.BoostsActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ BoostsActivity this$0;

        public AnonymousClass2(BoostsActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            BoostsActivity.m6295$$Nest$fgetprogressLayout(this.this$0).setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.BoostsActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends View {
        private final CircularProgressDrawable drawable;
        final /* synthetic */ BoostsActivity this$0;

        public AnonymousClass3(BoostsActivity r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.drawable = new CircularProgressDrawable(AndroidUtilities.dp(30.0f), AndroidUtilities.dp(3.0f), Theme.getColor(Theme.key_dialogTextBlue));
        }

        @Override // android.view.View
        public void onDraw(Canvas r5) {
            this.drawable.setBounds(0, 0, getWidth(), getHeight());
            this.drawable.setAlpha(255);
            this.drawable.draw(r5);
            invalidate();
            super.onDraw(r5);
        }
    }

    /* renamed from: org.telegram.ui.BoostsActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends FrameLayout {
        final /* synthetic */ BoostsActivity this$0;

        public AnonymousClass4(BoostsActivity r8, Context r9) {
            this.this$0 = r8;
            super(r9);
            if (BoostsActivity.m6286$$Nest$fgetboostsStatus(r8) == null) goto L5;
            View r92 = BoostsActivity.m6292$$Nest$fgetlimitPreviewView(r8);
        L7:
            if (BoostsActivity.m6286$$Nest$fgetboostsStatus(r8) == null) goto L10;
            float r82 = -2.0f;
        L11:
            addView(r92, LayoutHelper.createFrame(-1, r82, 0, 8.0f, 46.0f, 8.0f, 33.0f));
            return;
        L10:
            r82 = 110.0f;
            goto L11
        L5:
            r92 = new View(getContext());
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.BoostsActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends FrameLayout {
        private final HeaderButtonView buttonView1;
        private final HeaderButtonView buttonView2;
        private final HeaderButtonView buttonView3;
        final /* synthetic */ BoostsActivity this$0;

        /* renamed from: $r8$lambda$9cPek52rAWXx6KXVJ-P94fWFLUU, reason: not valid java name */
        public static /* synthetic */ void m6302$r8$lambda$9cPek52rAWXx6KXVJP94fWFLUU(AnonymousClass5 r0, DialogInterface r1) {
            r0.lambda$new$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$_lfmWw5bLbzFbuExqQ5HnZRLeLo(AnonymousClass5 r0, View r1) {
            r0.lambda$new$3(r1);
        }

        /* renamed from: $r8$lambda$d0wIdHOLx9iEs-RpTLLi1wg-PJ8, reason: not valid java name */
        public static /* synthetic */ void m6303$r8$lambda$d0wIdHOLx9iEsRpTLLi1wgPJ8(AnonymousClass5 r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$sZJuhXsRq9xniZ8iuKqDvcnWVa0(AnonymousClass5 r0, View r1) {
            r0.lambda$new$2(r1);
        }

        public AnonymousClass5(BoostsActivity r12, Context r13) {
            this.this$0 = r12;
            super(r13);
            setWillNotDraw(false);
            HeaderButtonView r0 = new HeaderButtonView(r12, getContext());
            this.buttonView1 = r0;
            HeaderButtonView r1 = new HeaderButtonView(r12, getContext());
            this.buttonView2 = r1;
            HeaderButtonView r2 = new HeaderButtonView(r12, getContext());
            this.buttonView3 = r2;
            r0.setTextAndIcon(LocaleController.getString(R.string.BoostBtn), R.drawable.filled_boost_plus);
            r1.setTextAndIcon(LocaleController.getString(R.string.GiveawayBtn), R.drawable.filled_gift_premium);
            r2.setTextAndIcon(LocaleController.getString(R.string.FeaturesBtn), R.drawable.filled_info);
            r0.setOnClickListener(new BoostsActivity$5$$ExternalSyntheticLambda0(this));
            r1.setOnClickListener(new BoostsActivity$5$$ExternalSyntheticLambda1(this));
            r2.setOnClickListener(new BoostsActivity$5$$ExternalSyntheticLambda2(this));
            LinearLayout r3 = new LinearLayout(getContext());
            r3.setOrientation(0);
            r3.addView(r0, LayoutHelper.createLinear(-2, -2, 6.0f, 0.0f, 6.0f, 0.0f));
            if (MessagesController.getInstance(r12.currentAccount).giveawayGiftsPurchaseAvailable == true) goto L5;
        L7:
            r3.addView(r2, LayoutHelper.createLinear(-2, -2, 6.0f, 0.0f, 6.0f, 0.0f));
            addView(r3, LayoutHelper.createFrame(-2, -2.0f, 1, 0.0f, 19.0f, 0.0f, 0.0f));
            return;
        L5:
            if (ChatObject.hasAdminRights(BoostsActivity.m6289$$Nest$fgetcurrentChat(r12)) == false) goto L7;
            r3.addView(r1, LayoutHelper.createLinear(-2, -2, 6.0f, 0.0f, 6.0f, 0.0f));
            goto L7
        }

        private /* synthetic */ void lambda$new$0(View r8) {
            BoostsActivity r0 = this.this$0;
            LimitReachedBottomSheet.openBoostsForUsers(r0, true, BoostsActivity.m6290$$Nest$fgetdialogId(r0), BoostsActivity.m6288$$Nest$fgetcanApplyBoost(this.this$0), BoostsActivity.m6286$$Nest$fgetboostsStatus(this.this$0), null);
        }

        private /* synthetic */ void lambda$new$1(DialogInterface r1) {
            this.this$0.updateDialogVisibility(false);
        }

        private /* synthetic */ void lambda$new$2(View r4) {
            this.this$0.updateDialogVisibility(true);
            BoostsActivity r42 = this.this$0;
            BoostPagerBottomSheet.show(r42, BoostsActivity.m6290$$Nest$fgetdialogId(r42), BoostsActivity.access$300(this.this$0));
            BoostPagerBottomSheet.getInstance().setOnHideListener(new BoostsActivity$5$$ExternalSyntheticLambda3(this));
        }

        private /* synthetic */ void lambda$new$3(View r7) {
            BoostsActivity r1 = this.this$0;
            Context r2 = getContext();
            BoostsActivity r72 = this.this$0;
            LimitReachedBottomSheet r0 = new LimitReachedBottomSheet(r1, r2, 31, r72.currentAccount, r72.getResourceProvider());
            r0.setBoostsStats(BoostsActivity.m6286$$Nest$fgetboostsStatus(this.this$0), true);
            r0.setDialogId(BoostsActivity.m6290$$Nest$fgetdialogId(this.this$0));
            this.this$0.showDialog(r0);
        }
    }

    public class HeaderButtonView extends FrameLayout {
        private final ImageView imageView;
        private final RectF rect;
        private final TextView textView;
        final /* synthetic */ BoostsActivity this$0;

        public HeaderButtonView(BoostsActivity r11, Context r12) {
            this.this$0 = r11;
            super(r12);
            this.rect = new RectF();
            setWillNotDraw(false);
            ImageView r0 = new ImageView(r12);
            this.imageView = r0;
            r0.setColorFilter(-1);
            TextView r2 = new TextView(r12);
            this.textView = r2;
            r2.setTextColor(-1);
            r2.setTypeface(AndroidUtilities.bold());
            r2.setTextSize(1, 12.0f);
            addView(r0, LayoutHelper.createFrame(-2, -2, 1));
            addView(r2, LayoutHelper.createFrame(-2, -2.0f, 1, 0.0f, 25.0f, 0.0f, 0.0f));
            setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
            setMinimumWidth(AndroidUtilities.dp(100.0f));
            setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(10.0f), 0, ColorUtils.setAlphaComponent(-16777216, 80)));
        }

        @Override // android.view.View
        public void draw(Canvas r5) {
            this.rect.set(0.0f, 0.0f, getWidth(), getHeight());
            r5.drawRoundRect(this.rect, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), this.this$0.setDarkGradientLocation(((ViewGroup) getParent()).getX() + getX(), ((ViewGroup) getParent().getParent().getParent()).getY()));
            invalidate();
            super.draw(r5);
        }

        public void setTextAndIcon(CharSequence r2, int r3) {
            this.textView.setText(r2);
            this.imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), r3));
        }
    }

    public class ItemInternal extends AdapterWithDiffUtils.Item {
        TL_stories.Boost booster;
        boolean isLast;
        TL_stories.PrepaidGiveaway prepaidGiveaway;
        int tab;
        final /* synthetic */ BoostsActivity this$0;
        String title;

        public ItemInternal(BoostsActivity r1, int r2, String r3) {
            this.this$0 = r1;
            super(r2, false);
            this.title = r3;
        }

        public ItemInternal(BoostsActivity r1, int r2, TL_stories.Boost r3, boolean r4, int r5) {
            this.this$0 = r1;
            super(r2, true);
            this.booster = r3;
            this.isLast = r4;
            this.tab = r5;
        }

        public ItemInternal(BoostsActivity r1, int r2, TL_stories.PrepaidGiveaway r3, boolean r4) {
            this.this$0 = r1;
            super(r2, true);
            this.prepaidGiveaway = r3;
            this.isLast = r4;
        }

        public ItemInternal(BoostsActivity r1, int r2, boolean r3) {
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

    /* renamed from: $r8$lambda$0ypz5oUnUF-oiD0nepNiRbhZTMs, reason: not valid java name */
    public static /* synthetic */ void m6283$r8$lambda$0ypz5oUnUFoiD0nepNiRbhZTMs(BoostsActivity r0, CountDownLatch r1, TLObject r2, Runnable r3) {
        r0.lambda$loadOnlyGifts$10(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$2mpCG099ujqs5jKw7X3dbjpwyt0(BoostsActivity r0) {
        r0.lambda$loadUsers$5();
    }

    public static /* synthetic */ void $r8$lambda$FFNjKVU9ub85hQd9wJneSqkljKE(BoostsActivity r0, TL_stories.TL_premium_boostsStatus r1) {
        r0.lambda$loadStatistic$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$HQD82Rf3DK6Kfy1JA9vnKnh16Oo(BoostsActivity r0) {
        r0.lambda$loadUsers$4();
    }

    public static /* synthetic */ void $r8$lambda$PSb9VhgMD5wRn6vJTzHzkHx0zMs(BoostsActivity r0) {
        r0.lambda$loadUsers$6();
    }

    public static /* synthetic */ void $r8$lambda$WnIADqGFklkOxlau83oUbWnrOG8(BoostsActivity r0, ChannelBoostsController.CanApplyBoost r1) {
        r0.lambda$loadCanApplyBoosts$2(r1);
    }

    /* renamed from: $r8$lambda$Z0gOsNipnnP902fxBSrn-XNOpEA, reason: not valid java name */
    public static /* synthetic */ void m6284$r8$lambda$Z0gOsNipnnP902fxBSrnXNOpEA(BoostsActivity r0, CountDownLatch r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$loadOnlyBoosts$9(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$cIqm7kAf3YJfIvyL7k8i2eVpLz4(BoostsActivity r0, CountDownLatch r1, TLObject r2, Runnable r3) {
        r0.lambda$loadOnlyBoosts$8(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$dworRXel_RZwOsKI_oKgZplYJKM(BoostsActivity r0) {
        r0.lambda$loadUsers$7();
    }

    public static /* synthetic */ void $r8$lambda$fIBLnRlKnYVQynhuqbJ3k1lOYrU(BoostsActivity r0, TL_stories.TL_premium_boostsStatus r1) {
        r0.lambda$loadStatistic$0(r1);
    }

    /* renamed from: $r8$lambda$ihuZmBcNlZJ6uRE3Z-brI9ola4Y, reason: not valid java name */
    public static /* synthetic */ void m6285$r8$lambda$ihuZmBcNlZJ6uRE3ZbrI9ola4Y(BoostsActivity r0) {
        r0.lambda$loadUsers$3();
    }

    public static /* synthetic */ void $r8$lambda$pZ7cxmLkyCYgJVrlSLMRXI3hQ0Q(BoostsActivity r0, Context r1, View r2, int r3) {
        r0.lambda$createView$12(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$v9KyYFhWgXNGasAsm5qZhbC7C6o(BoostsActivity r0, CountDownLatch r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$loadOnlyGifts$11(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetboostsStatus, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_stories.TL_premium_boostsStatus m6286$$Nest$fgetboostsStatus(BoostsActivity r0) {
        return r0.boostsStatus;
    }

    /* renamed from: -$$Nest$fgetboostsTabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ScrollSlidingTextTabStrip m6287$$Nest$fgetboostsTabs(BoostsActivity r0) {
        return r0.boostsTabs;
    }

    /* renamed from: -$$Nest$fgetcanApplyBoost, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChannelBoostsController.CanApplyBoost m6288$$Nest$fgetcanApplyBoost(BoostsActivity r0) {
        return r0.canApplyBoost;
    }

    /* renamed from: -$$Nest$fgetcurrentChat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Chat m6289$$Nest$fgetcurrentChat(BoostsActivity r0) {
        return r0.currentChat;
    }

    /* renamed from: -$$Nest$fgetdialogId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m6290$$Nest$fgetdialogId(BoostsActivity r2) {
        return r2.dialogId;
    }

    /* renamed from: -$$Nest$fgetitems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m6291$$Nest$fgetitems(BoostsActivity r0) {
        return r0.items;
    }

    /* renamed from: -$$Nest$fgetlimitPreviewView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LimitPreviewView m6292$$Nest$fgetlimitPreviewView(BoostsActivity r0) {
        return r0.limitPreviewView;
    }

    /* renamed from: -$$Nest$fgetnextBoostRemaining, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6293$$Nest$fgetnextBoostRemaining(BoostsActivity r0) {
        return r0.nextBoostRemaining;
    }

    /* renamed from: -$$Nest$fgetnextGiftsRemaining, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6294$$Nest$fgetnextGiftsRemaining(BoostsActivity r0) {
        return r0.nextGiftsRemaining;
    }

    /* renamed from: -$$Nest$fgetprogressLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m6295$$Nest$fgetprogressLayout(BoostsActivity r0) {
        return r0.progressLayout;
    }

    /* renamed from: -$$Nest$fgetselectedTab, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6296$$Nest$fgetselectedTab(BoostsActivity r0) {
        return r0.selectedTab;
    }

    /* renamed from: -$$Nest$fgettotalBoosts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6297$$Nest$fgettotalBoosts(BoostsActivity r0) {
        return r0.totalBoosts;
    }

    /* renamed from: -$$Nest$fgettotalGifts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6298$$Nest$fgettotalGifts(BoostsActivity r0) {
        return r0.totalGifts;
    }

    /* renamed from: -$$Nest$fputboostsTabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6299$$Nest$fputboostsTabs(BoostsActivity r0, ScrollSlidingTextTabStrip r1) {
        r0.boostsTabs = r1;
    }

    /* renamed from: -$$Nest$fputselectedTab, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6300$$Nest$fputselectedTab(BoostsActivity r0, int r1) {
        r0.selectedTab = r1;
    }

    /* renamed from: -$$Nest$misChannel, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6301$$Nest$misChannel(BoostsActivity r0) {
        return r0.isChannel();
    }

    public BoostsActivity(long r2) {
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
        this.dialogId = r2;
        this.currentChat = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r2));
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(BoostsActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$100(BoostsActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$200(BoostsActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$300(BoostsActivity r0) {
        return r0.resourceProvider;
    }

    private boolean isChannel() {
        return ChatObject.isChannelAndNotMegaGroup(this.currentChat);
    }

    private /* synthetic */ void lambda$createView$12(Context r12, View r13, int r14) {
        boolean r1 = false;
        if ((r13 instanceof GiftedUserCell) == false) goto L9;
        GiftedUserCell r0 = (GiftedUserCell) r13;
        TL_stories.Boost r6 = r0.getBoost();
        boolean r2 = r6.giveaway;
        if (r2 == true) goto L7;
    L10:
        boolean r122 = r6.gift;
        if (r122 == true) goto L14;
        if (r2 == true) goto L14;
    L16:
        if (r6.unclaimed == true) goto L25;
        if (r2 == true) goto L20;
    L22:
        if (r122 == true) goto L9;
        if (r2 == true) goto L9;
        presentFragment(ProfileActivity.of(r0.getDialogId()));
        goto L9
    L20:
        if (r6.user_id != (-1)) goto L22;
        Bulletin.LottieLayout r123 = new Bulletin.LottieLayout(getParentActivity(), getResourceProvider());
        r123.setAnimation(R.raw.chats_infotip, 36, 36, new String[0]);
        r123.textView.setText(LocaleController.getString(R.string.BoostingRecipientWillBeSelected));
        r123.textView.setSingleLine(false);
        r123.textView.setMaxLines(2);
        Bulletin.make(this, r123, 2750).show();
    L25:
        TLRPC.TL_payments_checkedGiftCode r62 = new TLRPC.TL_payments_checkedGiftCode();
        r62.giveaway_msg_id = r6.giveaway_msg_id;
        r62.to_id = r6.user_id;
        r62.from_id = MessagesController.getInstance(UserConfig.selectedAccount).getPeer(-this.currentChat.id);
        int r02 = r6.date;
        r62.date = r02;
        r62.via_giveaway = r6.giveaway;
        int r22 = r6.expires;
        r62.days = (r22 - r02) / 86400;
        r62.months = ((r22 - r02) / 30) / 86400;
        if (r6.unclaimed == false) goto L28;
        r62.to_id = -1;
        r62.flags = -1;
    L29:
        BoostsActivity r3 = this;
        new GiftInfoBottomSheet(r3, false, true, r62, r6.used_gift_slug).show();
    L31:
        if ((r13 instanceof TextCell) == false) goto L34;
        BoostPagerBottomSheet.show(r3, r3.dialogId, r3.resourceProvider);
    L34:
        if ((r13 instanceof GiveawayCell) == false) goto L37;
        BoostPagerBottomSheet.show(r3, r3.resourceProvider, r3.dialogId, ((GiveawayCell) r13).getPrepaidGiveaway());
    L37:
        if (r3.items.get(r14).viewType == 9) goto L39;
        return;
    L39:
        if (r3.selectedTab != 1) goto L41;
        r1 = true;
    L41:
        r3.loadUsers(Boolean.valueOf(r1));
        return;
    L28:
        r62.boost = r6;
    L14:
        if (r6.user_id >= 0) goto L25;
    L7:
        if (r6.stars <= 0) goto L10;
        StarsIntroActivity.showBoostsSheet(r12, this.currentAccount, this.dialogId, r6, getResourceProvider());
    L9:
        r3 = this;
        goto L31
    }

    private /* synthetic */ void lambda$loadCanApplyBoosts$2(ChannelBoostsController.CanApplyBoost r1) {
        this.canApplyBoost = r1;
    }

    private /* synthetic */ void lambda$loadOnlyBoosts$8(CountDownLatch r7, TLObject r8, Runnable r9) {
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

    private /* synthetic */ void lambda$loadOnlyBoosts$9(CountDownLatch r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new BoostsActivity$$ExternalSyntheticLambda12(this, r1, r3, r2));
    }

    private /* synthetic */ void lambda$loadOnlyGifts$10(CountDownLatch r7, TLObject r8, Runnable r9) {
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

    private /* synthetic */ void lambda$loadOnlyGifts$11(CountDownLatch r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new BoostsActivity$$ExternalSyntheticLambda11(this, r1, r3, r2));
    }

    private /* synthetic */ void lambda$loadStatistic$0(TL_stories.TL_premium_boostsStatus r3) {
        this.boostsStatus = r3;
        loadCanApplyBoosts();
        this.progressLayout.animate().cancel();
        this.progressLayout.animate().alpha(0.0f).setDuration(100).setStartDelay(0).setListener(new AnonymousClass2(this));
        resetHeader(true);
        updateRows(true);
        loadUsers(null);
    }

    private /* synthetic */ void lambda$loadStatistic$1(TL_stories.TL_premium_boostsStatus r2) {
        AndroidUtilities.runOnUIThread(new BoostsActivity$$ExternalSyntheticLambda9(this, r2));
    }

    private /* synthetic */ void lambda$loadUsers$3() {
        this.usersLoading = false;
        updateRows(true);
    }

    private /* synthetic */ void lambda$loadUsers$4() {
        AndroidUtilities.runOnUIThread(new BoostsActivity$$ExternalSyntheticLambda13(this));
    }

    private /* synthetic */ void lambda$loadUsers$5() {
        CountDownLatch r0 = new CountDownLatch(2);
        loadOnlyBoosts(r0, null);
        loadOnlyGifts(r0, null);
        r0.await();     // Catch: InterruptedException -> L6
    L4:
        NotificationCenter.getInstance(this.currentAccount).doOnIdle(new BoostsActivity$$ExternalSyntheticLambda8(this));
    }

    private /* synthetic */ void lambda$loadUsers$6() {
        this.usersLoading = false;
        updateRows(true);
    }

    private /* synthetic */ void lambda$loadUsers$7() {
        this.usersLoading = false;
        updateRows(true);
    }

    private void loadCanApplyBoosts() {
        if (this.boostsStatus != null) goto L5;
        return;
    L5:
        getMessagesController().getBoostsController().userCanBoostChannel(this.dialogId, this.boostsStatus, new BoostsActivity$$ExternalSyntheticLambda1(this));
    }

    private void loadOnlyBoosts(CountDownLatch r5, Runnable r6) {
        TL_stories.TL_premium_getBoostsList r0 = new TL_stories.TL_premium_getBoostsList();
        r0.limit = this.limitBoosts;
        r0.offset = this.lastBoostsOffset;
        r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new BoostsActivity$$ExternalSyntheticLambda10(this, r5, r6), 2);
    }

    private void loadOnlyGifts(CountDownLatch r5, Runnable r6) {
        TL_stories.TL_premium_getBoostsList r0 = new TL_stories.TL_premium_getBoostsList();
        r0.limit = this.limitGifts;
        r0.gifts = true;
        r0.offset = this.lastGiftsOffset;
        r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new BoostsActivity$$ExternalSyntheticLambda7(this, r5, r6), 2);
    }

    private void loadStatistic() {
        this.progressLayout.setAlpha(0.0f);
        TL_stories.TL_premium_boostsStatus r0 = this.boostsStatus;
        LinearLayout r1 = this.progressLayout;
        if (r0 != null) goto L6;
        r1.animate().alpha(1.0f).setDuration(200).setStartDelay(500).start();
        getMessagesController().getBoostsController().getBoostsStats(this.dialogId, new BoostsActivity$$ExternalSyntheticLambda2(this));
        return;
    L6:
        r1.setVisibility(8);
        loadUsers(null);
    }

    private void loadUsers(Boolean r2) {
        if (this.usersLoading == false) goto L5;
        return;
    L5:
        this.usersLoading = true;
        if (r2 != null) goto L10;
        Utilities.globalQueue.postRunnable(new BoostsActivity$$ExternalSyntheticLambda4(this));
        return;
    L10:
        if (r2.booleanValue() == false) goto L13;
        loadOnlyGifts(null, new BoostsActivity$$ExternalSyntheticLambda5(this));
        return;
    L13:
        loadOnlyBoosts(null, new BoostsActivity$$ExternalSyntheticLambda6(this));
    }

    private void resetHeader(boolean r8) {
        if (getContext() != null) goto L6;
        return;
    L6:
        if (this.limitPreviewView != null) goto L9;
        LimitPreviewView r1 = new LimitPreviewView(getContext(), R.drawable.filled_limit_boost, 0, 0, this.resourceProvider);
        this.limitPreviewView = r1;
        r1.isStatistic = true;
        r1.setDarkGradientProvider(new BoostsActivity$$ExternalSyntheticLambda3(this));
    L9:
        if (this.limitPreviewView.getParent() == null) goto L11;
        ((ViewGroup) this.limitPreviewView.getParent()).removeView(this.limitPreviewView);
    L11:
        TL_stories.TL_premium_boostsStatus r0 = this.boostsStatus;
        if (r0 == null) goto L17;
        this.limitPreviewView.setBoosts(r0, false);
        if (r8 == false) goto L17;
        this.limitPreviewView.setAlpha(0.0f);
        this.limitPreviewView.animate().alpha(1.0f).start();
    L17:
        if (isChannel() == false) goto L19;
        int r82 = R.string.BoostingBoostForChannels;
    L20:
        String r83 = LocaleController.getString(r82);
        if (isChannel() == false) goto L23;
        int r02 = R.string.BoostingBoostForChannelsInfo;
    L24:
        configureHeader(r83, AndroidUtilities.replaceTags(LocaleController.getString(r02)), new AnonymousClass4(this, getContext()), new AnonymousClass5(this, getContext()));
        return;
    L23:
        r02 = R.string.BoostingBoostForGroupsInfo;
        goto L24
    L19:
        r82 = R.string.BoostingBoostForGroups;
        goto L20
    }

    @Override // org.telegram.ui.GradientHeaderActivity
    public RecyclerView.Adapter<?> createAdapter() {
        return this.adapter;
    }

    public void createEmptyView(Context r9) {
        LinearLayout r0 = new LinearLayout(r9);
        this.progressLayout = r0;
        r0.setOrientation(1);
        AnonymousClass3 r02 = new AnonymousClass3(this, r9);
        this.progressLayout.addView(r02, LayoutHelper.createLinear(100, 100, 17, 0, 120, 0, 0));
        ((ViewGroup) this.fragmentView).addView(this.progressLayout, LayoutHelper.createFrame(-1, -2, 17));
    }

    @Override // org.telegram.ui.GradientHeaderActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r5) {
        View r0 = super.createView(r5);
        this.listView.setSegmentedSectionsEnabled(false);
        resetHeader(false);
        DefaultItemAnimator r1 = new DefaultItemAnimator();
        r1.setSupportsChangeAnimations(false);
        r1.setDelayAnimations(false);
        this.listView.setItemAnimator(r1);
        this.listView.setOnItemClickListener(new BoostsActivity$$ExternalSyntheticLambda0(this, r5));
        createEmptyView(getContext());
        loadStatistic();
        updateRows(false);
        return r0;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r6, int r7, Object... r8) {
        if (r6 != NotificationCenter.boostByChannelCreated) goto L36;
        TLRPC.Chat r62 = (TLRPC.Chat) r8[0];
        boolean r72 = ((Boolean) r8[1]).booleanValue();
        List<BaseFragment> r82 = getParentLayout().getFragmentStack();
        BaseFragment r4 = null;
        if (r82.size() < 2) goto L7;
        BaseFragment r83 = r82.get(r82.size() - 2);
    L9:
        if ((r83 instanceof ChatEditActivity) == false) goto L11;
        getParentLayout().removeFragmentFromStack(r83);
    L11:
        List<BaseFragment> r84 = getParentLayout().getFragmentStack();
        if (r84.size() < 2) goto L14;
        BaseFragment r2 = r84.get(r84.size() - 2);
    L15:
        if (r72 == true) goto L17;
        finishFragment();
        if ((r2 instanceof ProfileActivity) == false) goto L32;
    L33:
        BoostDialogs.showBulletin(r2, r62, false);
        return;
    L32:
        if ((r2 instanceof ChatActivity) == true) goto L33;
        return;
    L17:
        if (r84.size() < 3) goto L20;
        r4 = r84.get(r84.size() - 3);
    L20:
        if ((r2 instanceof ProfileActivity) == false) goto L22;
        getParentLayout().removeFragmentFromStack(r2);
    L22:
        finishFragment();
        if ((r4 instanceof ChatActivity) == false) goto L26;
        BoostDialogs.showBulletin(r4, r62, true);
    L26:
        if ((r2 instanceof ChatActivity) == false) goto L41;
        BoostDialogs.showBulletin(r2, r62, true);
        return;
    L41:
        return;
    L14:
        r2 = null;
        goto L15
    L7:
        r83 = null;
        goto L9
    L36:
        if (r6 == NotificationCenter.chatWasBoostedByUser) goto L38;
        return;
    L38:
        if (this.dialogId != ((Long) r8[2]).longValue()) goto L44;
        this.boostsStatus = (TL_stories.TL_premium_boostsStatus) r8[0];
        this.canApplyBoost = (ChannelBoostsController.CanApplyBoost) r8[1];
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.boostByChannelCreated);
        getNotificationCenter().addObserver(this, NotificationCenter.chatWasBoostedByUser);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        getNotificationCenter().removeObserver(this, NotificationCenter.boostByChannelCreated);
        getNotificationCenter().removeObserver(this, NotificationCenter.chatWasBoostedByUser);
        super.onFragmentDestroy();
    }

    public void setBoostsStatus(TL_stories.TL_premium_boostsStatus r1) {
        this.boostsStatus = r1;
        loadCanApplyBoosts();
    }

    public void updateRows(boolean r15) {
        ArrayList<? extends AdapterWithDiffUtils.Item> r6 = new ArrayList(this.items);
        this.items.clear();
        this.items.add(new ItemInternal(this, 14, false));
        if (this.boostsStatus == null) goto L76;
        this.items.add(new ItemInternal(this, 16, LocaleController.getString(R.string.StatisticOverview)));
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
        if (MessagesController.getInstance(this.currentAccount).giveawayGiftsPurchaseAvailable == true) goto L64;
    L74:
        this.items.add(new ItemInternal(this, 6, _UrlKt.FRAGMENT_ENCODE_SET));
    L75:
        this.items.add(new ItemInternal(this, 15, false));
        goto L76
    L64:
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
        goto L75
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
    L76:
        AdapterWithDiffUtils r08 = this.adapter;
        if (r15 == false) goto L80;
        r08.setItems(r6, this.items);
        return;
    L80:
        r08.notifyDataSetChanged();
    }
}
