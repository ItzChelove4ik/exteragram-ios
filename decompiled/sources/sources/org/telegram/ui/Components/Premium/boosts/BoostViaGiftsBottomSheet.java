package org.telegram.ui.Components.Premium.boosts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.FireworksOverlay;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet;
import org.telegram.ui.Components.Premium.boosts.SelectorBottomSheet;
import org.telegram.ui.Components.Premium.boosts.adapters.BoostAdapter;
import org.telegram.ui.Components.Premium.boosts.cells.ActionBtnCell;
import org.telegram.ui.Components.Premium.boosts.cells.AddChannelCell;
import org.telegram.ui.Components.Premium.boosts.cells.BaseCell;
import org.telegram.ui.Components.Premium.boosts.cells.BoostTypeCell;
import org.telegram.ui.Components.Premium.boosts.cells.DateEndCell;
import org.telegram.ui.Components.Premium.boosts.cells.DurationCell;
import org.telegram.ui.Components.Premium.boosts.cells.ParticipantsTypeCell;
import org.telegram.ui.Components.Premium.boosts.cells.StarGiveawayOptionCell;
import org.telegram.ui.Components.Premium.boosts.cells.SwitcherCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;

/* loaded from: classes7.dex */
public class BoostViaGiftsBottomSheet extends BottomSheetWithRecyclerListView implements SelectorBottomSheet.SelectedObjectsListener, NotificationCenter.NotificationCenterDelegate {
    private ActionBtnCell actionBtn;
    private ActionListener actionListener;
    private BoostAdapter adapter;
    private String additionalPrize;
    private final TLRPC.Chat currentChat;
    private final List<TLRPC.TL_premiumGiftCodeOption> giftCodeOptions;
    private final Runnable hideKeyboardRunnable;
    private boolean isAdditionalPrizeSelected;
    private boolean isShowWinnersSelected;
    private final ArrayList<BoostAdapter.Item> items;
    private Runnable onCloseClick;
    private final TL_stories.PrepaidGiveaway prepaidGiveaway;
    private int selectedBoostSubType;
    private int selectedBoostType;
    private final List<TLObject> selectedChats;
    private final List<TLObject> selectedCountries;
    private long selectedEndDate;
    private int selectedMonths;
    private int selectedParticipantsType;
    private int selectedSliderIndex;
    private long selectedStars;
    private int selectedStarsSliderIndex;
    private final List<TLObject> selectedUsers;
    private final List<Integer> sliderStarsValues;
    private final List<Integer> sliderValues;
    private boolean starOptionsExpanded;
    private final List<Integer> starsNotExtended;
    private int top;

    /* renamed from: org.telegram.ui.Components.Premium.boosts.BoostViaGiftsBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerView.OnScrollListener {
        final /* synthetic */ BoostViaGiftsBottomSheet this$0;

        public AnonymousClass1(BoostViaGiftsBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(r1);
            return;
        }
    }

    public interface ActionListener {
        void onAddChat(List<TLObject> r1);

        void onSelectCountries(List<TLObject> r1);

        void onSelectUser(List<TLObject> r1);
    }

    /* renamed from: $r8$lambda$0T0Hy7wI0DQzM-pwyofn6aUJ6Tk, reason: not valid java name */
    public static /* synthetic */ void m12098$r8$lambda$0T0Hy7wI0DQzMpwyofn6aUJ6Tk(BoostViaGiftsBottomSheet r0) {
        r0.lambda$updateRows$24();
    }

    public static /* synthetic */ void $r8$lambda$0urw42Y7WADGtQuSUMBSFJCcSUo(BoostViaGiftsBottomSheet r0, TL_stories.TL_prepaidStarsGiveaway r1, long r2, long r4, TL_stories.PrepaidGiveaway r6, Void r7) {
        r0.lambda$new$8(r1, r2, r4, r6, r7);
    }

    /* renamed from: $r8$lambda$595nZ-jE6JvxZvR81eC23-yK5j8, reason: not valid java name */
    public static /* synthetic */ void m12099$r8$lambda$595nZjE6JvxZvR81eC23yK5j8(BoostViaGiftsBottomSheet r0, TLRPC.Chat r1) {
        r0.lambda$new$4(r1);
    }

    /* renamed from: $r8$lambda$5bEM7sM28y-5QW54j9zALkwfZMM, reason: not valid java name */
    public static /* synthetic */ void m12100$r8$lambda$5bEM7sM28y5QW54j9zALkwfZMM(BoostViaGiftsBottomSheet r0, Void r1) {
        r0.lambda$new$14(r1);
    }

    /* renamed from: $r8$lambda$5bRfvHvjcVLtHilvA17Mn9B-jC8, reason: not valid java name */
    public static /* synthetic */ void m12101$r8$lambda$5bRfvHvjcVLtHilvA17Mn9BjC8(BoostViaGiftsBottomSheet r0, TL_stars.TL_starsGiveawayOption r1, Boolean r2, String r3) {
        r0.lambda$new$12(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$7sb3k93c-sGAnqmwiRsH7ULEbiI, reason: not valid java name */
    public static /* synthetic */ void m12102$r8$lambda$7sb3k93csGAnqmwiRsH7ULEbiI(ChatActivity r4, TL_stars.TL_starsGiveawayOption r5) {
        BulletinFactory.of(r4).createSimpleBulletin(R.raw.stars_send, LocaleController.getString(R.string.StarsGiveawaySentPopup), AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("StarsGiveawaySentPopupInfo", (int) r5.stars))).setDuration(5000).show(true);
    }

    /* renamed from: $r8$lambda$8_jh4Ii-on3bBt5yXC7s3CYzZ4k, reason: not valid java name */
    public static /* synthetic */ void m12103$r8$lambda$8_jh4Iion3bBt5yXC7s3CYzZ4k(BoostViaGiftsBottomSheet r0, DialogInterface r1) {
        r0.lambda$updateRows$26(r1);
    }

    public static /* synthetic */ void $r8$lambda$94KF6KaZTu0gZ0mjKNIOLuhZwVU(BoostViaGiftsBottomSheet r0) {
        r0.lambda$new$13();
    }

    public static /* synthetic */ void $r8$lambda$B4PzjYjdgn9F5Z5eSAvAcOrohQk(BoostViaGiftsBottomSheet r0, int r1) {
        r0.lambda$new$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$BEssDOuXlmeVbR5gsIXDIisyfes(BoostViaGiftsBottomSheet r0, TLRPC.TL_error r1) {
        r0.lambda$new$19(r1);
    }

    public static /* synthetic */ void $r8$lambda$EiBLA8r3Hz4YmCLYyeYRy8urOXc(ChatActivity r3, long r4) {
        BulletinFactory.of(r3).createSimpleBulletin(R.raw.stars_topup, LocaleController.getString(R.string.StarsGiveawaySentPopup), AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("StarsGiveawaySentPopupInfo", (int) r4))).show(true);
    }

    public static /* synthetic */ void $r8$lambda$JV_J_PTQJiWaYSYCiJpOVThyKiY(BoostViaGiftsBottomSheet r0, DialogInterface r1) {
        r0.lambda$updateRows$25(r1);
    }

    /* renamed from: $r8$lambda$Mh0PWL9e8r3-YOkC_lNO6gfjNKY, reason: not valid java name */
    public static /* synthetic */ void m12104$r8$lambda$Mh0PWL9e8r3YOkC_lNO6gfjNKY(BoostViaGiftsBottomSheet r0, TL_stories.PrepaidGiveaway r1, long r2, BaseFragment r4, View r5) {
        r0.lambda$new$20(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$NDYeFOiTMKGsa7dFnHS5t9aW8lE(BoostViaGiftsBottomSheet r0, TLRPC.TL_premiumGiftCodeOption r1) {
        r0.lambda$new$16(r1);
    }

    public static /* synthetic */ void $r8$lambda$Q9OWZCf1l0I8Amskl5Xl9EthW1k(BoostViaGiftsBottomSheet r0, TLRPC.TL_error r1) {
        r0.lambda$new$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$Rp4DFuXklFKlp8Q4VQjadlVjQww(BoostViaGiftsBottomSheet r0, String r1) {
        r0.lambda$new$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$SEdBJHyopp0XOnjmer8wDaTxN8M(BoostViaGiftsBottomSheet r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$VFQy_K4KnUdHRaU11ODkmuYlv9s(BoostViaGiftsBottomSheet r0) {
        r0.lambda$updateRows$27();
    }

    public static /* synthetic */ void $r8$lambda$WZx9TeDJMR3pjsNTWGlBmooTqrE(BoostViaGiftsBottomSheet r0, DialogInterface r1) {
        r0.lambda$updateRows$22(r1);
    }

    public static /* synthetic */ void $r8$lambda$Y8CPx9_vhdbTxZpZvwHTAeOAUng(BoostViaGiftsBottomSheet r0, TL_stories.PrepaidGiveaway r1) {
        r0.lambda$new$7(r1);
    }

    /* renamed from: $r8$lambda$_aS6zNk5TgPVYYJ-aH_Qx2-l-BA, reason: not valid java name */
    public static /* synthetic */ void m12105$r8$lambda$_aS6zNk5TgPVYYJaH_Qx2lBA(BoostViaGiftsBottomSheet r0, boolean r1, int r2, int r3) {
        r0.lambda$new$1(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$fsuxN0Oi8P5RD3jAyfw-erBvlaI, reason: not valid java name */
    public static /* synthetic */ void m12106$r8$lambda$fsuxN0Oi8P5RD3jAyfwerBvlaI(BoostViaGiftsBottomSheet r0) {
        r0.lambda$new$17();
    }

    /* renamed from: $r8$lambda$g0k15WHfpzcZKY6C-t9VltN1ptQ, reason: not valid java name */
    public static /* synthetic */ void m12107$r8$lambda$g0k15WHfpzcZKY6Ct9VltN1ptQ(BoostViaGiftsBottomSheet r0, List r1) {
        r0.lambda$loadOptions$21(r1);
    }

    /* renamed from: $r8$lambda$guK904eDA3KlKRXxr62hSrhYZ-Y, reason: not valid java name */
    public static /* synthetic */ void m12108$r8$lambda$guK904eDA3KlKRXxr62hSrhYZY(BoostViaGiftsBottomSheet r0, BaseFragment r1, View r2, int r3) {
        r0.lambda$new$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$luJ7eKgvxal7xl_tzExJj3tBA6c(BoostViaGiftsBottomSheet r0, TLRPC.TL_error r1) {
        r0.lambda$new$15(r1);
    }

    public static /* synthetic */ void $r8$lambda$moYJJJ_3RnQU1i1k46p1EaR21YA(BoostViaGiftsBottomSheet r0, DialogInterface r1) {
        r0.lambda$updateRows$23(r1);
    }

    public static /* synthetic */ void $r8$lambda$u6x80IJ70WnKD7UTRkoBZ9RLpJY(BoostViaGiftsBottomSheet r0, TL_stories.PrepaidGiveaway r1, TL_stories.TL_prepaidStarsGiveaway r2, long r3, long r5) {
        r0.lambda$new$10(r1, r2, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$xWYkvikJCD5LhQ_83fg5wMgxR_4(BoostViaGiftsBottomSheet r0, Void r1) {
        r0.lambda$new$18(r1);
    }

    public BoostViaGiftsBottomSheet(BaseFragment r19, boolean r20, boolean r21, long r22, TL_stories.PrepaidGiveaway r24) {
        super(r19, r20, r21);
        ArrayList<BoostAdapter.Item> r13 = new ArrayList();
        this.items = r13;
        if (BoostRepository.isGoogleBillingAvailable() == false) goto L5;
        List<Integer> r11 = Arrays.asList(new Integer[]{1, 3, 5, 7, 10, 25, 50});
    L6:
        this.sliderValues = r11;
        if (BoostRepository.isGoogleBillingAvailable() == false) goto L9;
        List<Integer> r3 = Arrays.asList(new Integer[]{1, 3, 5, 7, 10, 25, 50});
    L10:
        this.sliderStarsValues = r3;
        this.starsNotExtended = Arrays.asList(new Integer[]{750, 10000, 50000});
        this.selectedChats = new ArrayList();
        this.selectedUsers = new ArrayList();
        this.selectedCountries = new ArrayList();
        this.giftCodeOptions = new ArrayList();
        this.selectedBoostType = BoostTypeCell.TYPE_PREMIUM;
        this.selectedBoostSubType = BoostTypeCell.TYPE_GIVEAWAY;
        this.selectedParticipantsType = ParticipantsTypeCell.TYPE_ALL;
        this.selectedMonths = 12;
        this.selectedEndDate = BoostDialogs.getThreeDaysAfterToday();
        this.selectedSliderIndex = 2;
        this.selectedStarsSliderIndex = 2;
        this.additionalPrize = _UrlKt.FRAGMENT_ENCODE_SET;
        this.isShowWinnersSelected = true;
        this.hideKeyboardRunnable = new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda0(this);
        this.prepaidGiveaway = r24;
        this.topPadding = 0.15f;
        setApplyTopPadding(false);
        setApplyBottomPadding(false);
        this.useBackgroundTopPadding = false;
        this.backgroundPaddingLeft = 0;
        updateTitle();
        ((ViewGroup.MarginLayoutParams) this.actionBar.getLayoutParams()).leftMargin = 0;
        ((ViewGroup.MarginLayoutParams) this.actionBar.getLayoutParams()).rightMargin = 0;
        if ((r24 instanceof TL_stories.TL_prepaidStarsGiveaway) == false) goto L13;
        this.selectedBoostType = BoostTypeCell.TYPE_STARS;
    L13:
        DefaultItemAnimator r0 = new DefaultItemAnimator();
        r0.setDurations(350);
        r0.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r0.setDelayAnimations(false);
        r0.setSupportsChangeAnimations(false);
        this.recyclerListView.setItemAnimator(r0);
        RecyclerListView r02 = this.recyclerListView;
        int r32 = this.backgroundPaddingLeft;
        r02.setPadding(r32, 0, r32, AndroidUtilities.dp(68.0f));
        this.recyclerListView.setOnScrollListener(new AnonymousClass1(this));
        this.recyclerListView.setOnItemClickListener(new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda1(this, r19));
        TLRPC.Chat r12 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r22));
        this.currentChat = r12;
        this.adapter.setItems(r12, r13, this.recyclerListView, new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda2(this), new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda3(this), new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda4(this));
        updateRows(false, false);
        ActionBtnCell r7 = new ActionBtnCell(getContext(), this.resourcesProvider);
        this.actionBtn = r7;
        r7.setOnClickListener(new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda5(this, r24, r22, r19));
        updateActionButton(false);
        this.containerView.addView(this.actionBtn, LayoutHelper.createFrame(-1, 68.0f, 80, 0.0f, 0.0f, 0.0f, 0.0f));
        loadOptions();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starGiveawayOptionsLoaded);
        return;
    L9:
        r3 = Arrays.asList(new Integer[]{1, 3, 5, 7, 10, 25, 50, 100});
        goto L10
    L5:
        r11 = Arrays.asList(new Integer[]{1, 3, 5, 7, 10, 25, 50, 100});
        goto L6
    }

    private List<Long> getPerUserStarsValues() {
        return getPerUserStarsValues(this.selectedStars);
    }

    private List<Long> getPerUserStarsValues(long r5) {
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        TL_stars.TL_starsGiveawayOption r4 = getSelectedStarsOption(r5);
        if (r4 == null) goto L11;
        int r52 = 0;
    L6:
        if (r52 >= r4.winners.size()) goto L11;
        TL_stars.TL_starsGiveawayWinnersOption r6 = r4.winners.get(r52);
        if (r0.contains(Integer.valueOf(r6.users)) == true) goto L10;
        r0.add(Integer.valueOf(r6.users));
        r1.add(Long.valueOf(r6.per_user_stars));
    L10:
        r52 = r52 + 1;
    L11:
        return r1;
    }

    private long getSelectedPerUserStars() {
        List<Long> r0 = getPerUserStarsValues();
        int r1 = this.selectedStarsSliderIndex;
        if (r1 >= 0) goto L5;
    L6:
        this.selectedStarsSliderIndex = 0;
    L8:
        if (this.selectedStarsSliderIndex < r0.size()) goto L12;
        return 1;
    L12:
        return r0.get(this.selectedStarsSliderIndex).longValue();
    L5:
        if (r1 < r0.size()) goto L8;
        goto L6
    }

    private long getSelectedPerUserStars(long r3) {
        List<Long> r0 = getPerUserStarsValues(r3);
        if (r0.isEmpty() == false) goto L7;
        return Math.round(r3 / getSelectedPerUserStars());
    L7:
        return r0.get(Utilities.clamp(this.selectedStarsSliderIndex, r0.size() - 1, 0)).longValue();
    }

    private int getSelectedSliderValue() {
        if (this.selectedBoostType == BoostTypeCell.TYPE_PREMIUM) goto L5;
        List<Integer> r0 = getSliderValues();
        int r1 = this.selectedStarsSliderIndex;
        if (r1 >= 0) goto L9;
    L10:
        this.selectedStarsSliderIndex = 0;
    L12:
        if (this.selectedStarsSliderIndex < r0.size()) goto L15;
        return 0;
    L15:
        return r0.get(this.selectedStarsSliderIndex).intValue();
    L9:
        if (r1 < r0.size()) goto L12;
    L5:
        return this.sliderValues.get(this.selectedSliderIndex).intValue();
    }

    private int getSelectedSliderValueWithBoosts() {
        if (this.selectedBoostType != BoostTypeCell.TYPE_PREMIUM) goto L7;
        int r2 = this.sliderValues.get(this.selectedSliderIndex).intValue();
        int r0 = BoostRepository.giveawayBoostsPerPremium();
    L6:
        return r2 * r0;
    L7:
        TL_stars.TL_starsGiveawayOption r02 = getSelectedStarsOption();
        if (r02 != null) goto L10;
        r2 = getSelectedSliderValue();
        r0 = BoostRepository.giveawayBoostsPerPremium();
        goto L6
    L10:
        return r02.yearly_boosts;
    }

    private List<Integer> getSliderValues() {
        if (this.selectedBoostType == BoostTypeCell.TYPE_PREMIUM) goto L5;
        ArrayList r0 = new ArrayList();
        TL_stars.TL_starsGiveawayOption r4 = getSelectedStarsOption();
        if (r4 == null) goto L15;
        int r1 = 0;
    L10:
        if (r1 >= r4.winners.size()) goto L15;
        TL_stars.TL_starsGiveawayWinnersOption r2 = r4.winners.get(r1);
        if (r0.contains(Integer.valueOf(r2.users)) == true) goto L14;
        r0.add(Integer.valueOf(r2.users));
    L14:
        r1 = r1 + 1;
    L15:
        return r0;
    L5:
        return this.sliderValues;
    }

    private List<Long> getStarsOptions() {
        ArrayList<TL_stars.TL_starsGiveawayOption> r5 = StarsController.getInstance(this.currentAccount).getGiveawayOptions();
        ArrayList r0 = new ArrayList();
        if (r5 == null) goto L13;
        int r1 = 0;
    L6:
        if (r1 >= r5.size()) goto L13;
        TL_stars.TL_starsGiveawayOption r2 = r5.get(r1);
        if (r2 == null) goto L12;
        if (r0.contains(Long.valueOf(r2.stars)) == true) goto L12;
        r0.add(Long.valueOf(r2.stars));
    L12:
        r1 = r1 + 1;
    L13:
        return r0;
    }

    private boolean isGiveaway() {
        if (this.selectedBoostSubType != BoostTypeCell.TYPE_GIVEAWAY) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isPreparedGiveaway() {
        if (this.prepaidGiveaway == null) goto L6;
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$loadOptions$21(List r2) {
        this.giftCodeOptions.clear();
        this.giftCodeOptions.addAll(r2);
        updateRows(true, true);
    }

    private /* synthetic */ void lambda$new$0() {
        AndroidUtilities.hideKeyboard(this.recyclerListView);
    }

    private /* synthetic */ void lambda$new$1(boolean r3, int r4, int r5) {
        this.selectedEndDate = r4 * 1000;
        updateRows(false, true);
    }

    private /* synthetic */ void lambda$new$10(TL_stories.PrepaidGiveaway r18, TL_stories.TL_prepaidStarsGiveaway r19, long r20, long r22) {
        int r8 = BoostRepository.prepareServerDate(this.selectedEndDate);
        if (this.selectedParticipantsType != ParticipantsTypeCell.TYPE_NEW) goto L5;
        boolean r9 = true;
    L6:
        this.actionBtn.updateLoading(true);
        BoostRepository.launchPreparedGiveaway(r18, this.selectedChats, this.selectedCountries, this.currentChat, r8, r9, this.isShowWinnersSelected, this.isAdditionalPrizeSelected, r18.quantity, this.additionalPrize, new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda21(this, r19, r20, r22, r18), new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda22(this));
        return;
    L5:
        r9 = false;
        goto L6
    }

    private /* synthetic */ void lambda$new$12(TL_stars.TL_starsGiveawayOption r3, Boolean r4, String r5) {
        this.actionBtn.button.setLoading(false);
        if (getContext() == null) goto L19;
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        FireworksOverlay r1 = LaunchActivity.instance.getFireworksOverlay();
        if (r0 != null) goto L9;
        return;
    L9:
        if (r4.booleanValue() == false) goto L14;
        dismiss();
        ChatActivity r2 = ChatActivity.of(-this.currentChat.id);
        r0.presentFragment(r2);
        r0.whenFullyVisible(new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda25(r2, r3));
        if (r1 == null) goto L17;
        r1.start(true);
        return;
    L17:
        return;
    L14:
        if (r5 == null) goto L18;
        dismiss();
        BulletinFactory.of(r0).createSimpleBulletin(R.raw.error, LocaleController.formatString(R.string.UnknownErrorCode, new Object[]{r5})).show();
        return;
    L18:
        return;
    }

    private /* synthetic */ void lambda$new$13() {
        NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationName(NotificationCenter.boostByChannelCreated, new Object[]{this.currentChat, Boolean.FALSE});
    }

    private /* synthetic */ void lambda$new$14(Void r3) {
        dismiss();
        AndroidUtilities.runOnUIThread(new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda24(this), 220);
    }

    private /* synthetic */ void lambda$new$15(TLRPC.TL_error r3) {
        this.actionBtn.updateLoading(false);
        BoostDialogs.showToastError(getContext(), r3);
    }

    private /* synthetic */ void lambda$new$16(TLRPC.TL_premiumGiftCodeOption r2) {
        this.selectedSliderIndex = this.sliderValues.indexOf(Integer.valueOf(r2.users));
        updateRows(true, true);
        updateActionButton(true);
    }

    private /* synthetic */ void lambda$new$17() {
        NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationName(NotificationCenter.boostByChannelCreated, new Object[]{this.currentChat, Boolean.TRUE});
    }

    private /* synthetic */ void lambda$new$18(Void r3) {
        dismiss();
        AndroidUtilities.runOnUIThread(new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda23(this), 220);
    }

    private /* synthetic */ void lambda$new$19(TLRPC.TL_error r3) {
        this.actionBtn.updateLoading(false);
        BoostDialogs.showToastError(getContext(), r3);
    }

    private /* synthetic */ void lambda$new$2(BaseFragment r6, View r7, int r8) {
        if ((r7 instanceof SwitcherCell) == false) goto L14;
        SwitcherCell r82 = (SwitcherCell) r7;
        int r2 = r82.getType();
        boolean r3 = !r82.isChecked();
        r82.setChecked(r3);
        if (r2 != SwitcherCell.TYPE_WINNERS) goto L8;
        this.isShowWinnersSelected = r3;
        updateRows(false, false);
        goto L14
    L8:
        if (r2 != SwitcherCell.TYPE_ADDITION_PRIZE) goto L14;
        r82.setDivider(r3);
        this.isAdditionalPrizeSelected = r3;
        updateRows(false, false);
        this.adapter.notifyAdditionalPrizeItem(r3);
        this.adapter.notifyAllVisibleTextDividers();
        boolean r83 = this.isAdditionalPrizeSelected;
        Runnable r22 = this.hideKeyboardRunnable;
        if (r83 == true) goto L12;
        AndroidUtilities.runOnUIThread(r22, 250);
        goto L14
    L12:
        AndroidUtilities.cancelRunOnUIThread(r22);
    L14:
        if ((r7 instanceof BaseCell) == false) goto L39;
        if ((r7 instanceof BoostTypeCell) == false) goto L37;
        int r84 = ((BoostTypeCell) r7).getSelectedType();
        if (r84 == BoostTypeCell.TYPE_PREMIUM) goto L29;
        if (r84 == BoostTypeCell.TYPE_STARS) goto L29;
        if (r84 != BoostTypeCell.TYPE_SPECIFIC_USERS) goto L27;
        ActionListener r85 = this.actionListener;
        if (r85 == null) goto L39;
        r85.onSelectUser(this.selectedUsers);
        goto L39
    L27:
        this.selectedBoostSubType = r84;
        updateRows(true, true);
        updateActionButton(true);
        updateTitle();
    L29:
        if (r84 == BoostTypeCell.TYPE_PREMIUM) goto L31;
    L36:
        this.selectedBoostType = r84;
        updateRows(true, true);
        updateActionButton(true);
        updateTitle();
        goto L39
    L31:
        if (this.selectedBoostType != r84) goto L36;
        ActionListener r62 = this.actionListener;
        if (r62 == null) goto L71;
        r62.onSelectUser(this.selectedUsers);
        return;
    L71:
        return;
    L37:
        ((BaseCell) r7).markChecked(this.recyclerListView);
    L39:
        if ((r7 instanceof ParticipantsTypeCell) == false) goto L48;
        int r63 = ((ParticipantsTypeCell) r7).getSelectedType();
        if (this.selectedParticipantsType != r63) goto L45;
        ActionListener r72 = this.actionListener;
        if (r72 == null) goto L45;
        r72.onSelectCountries(this.selectedCountries);
    L45:
        this.selectedParticipantsType = r63;
        updateRows(false, false);
        return;
    L48:
        if ((r7 instanceof DurationCell) == false) goto L52;
        this.selectedMonths = ((TLRPC.TL_premiumGiftCodeOption) ((DurationCell) r7).getGifCode()).months;
        updateRows(false, false);
        this.adapter.notifyAllVisibleTextDividers();
        return;
    L52:
        if ((r7 instanceof DateEndCell) == false) goto L56;
        BoostDialogs.showDatePicker(r6.getContext(), this.selectedEndDate, new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda10(this), this.resourcesProvider);
        return;
    L56:
        if ((r7 instanceof AddChannelCell) == false) goto L62;
        ActionListener r64 = this.actionListener;
        if (r64 == null) goto L72;
        r64.onAddChat(this.selectedChats);
        return;
    L72:
        return;
    L62:
        if ((r7 instanceof StarGiveawayOptionCell) == false) goto L68;
        TL_stars.TL_starsGiveawayOption r65 = ((StarGiveawayOptionCell) r7).getOption();
        if (r65 == null) goto L73;
        this.selectedStars = r65.stars;
        updateRows(true, true);
        updateActionButton(true);
        updateTitle();
        return;
    L73:
        return;
    L68:
        if ((r7 instanceof StarsIntroActivity.ExpandView) == false) goto L74;
        this.starOptionsExpanded = true;
        updateRows(true, true);
        return;
    }

    private /* synthetic */ void lambda$new$20(TL_stories.PrepaidGiveaway r25, long r26, BaseFragment r28, View r29) {
        if (this.actionBtn.isLoading() == false) goto L6;
        return;
    L6:
        if (isPreparedGiveaway() == true) goto L8;
        int r3 = 0;
        if (this.selectedBoostType != BoostTypeCell.TYPE_STARS) goto L37;
        Activity r0 = AndroidUtilities.findActivity(getContext());
        if (r0 != null) goto L23;
        r0 = LaunchActivity.instance;
    L23:
        Activity r6 = r0;
        if (r6 != null) goto L26;
        return;
    L26:
        if (r6.isFinishing() == true) goto L79;
        TL_stars.TL_starsGiveawayOption r9 = getSelectedStarsOption();
        int r10 = getSelectedSliderValue();
        if (r9 == null) goto L80;
        this.actionBtn.button.setLoading(true);
        if (this.selectedParticipantsType != ParticipantsTypeCell.TYPE_NEW) goto L34;
        boolean r14 = true;
    L35:
        StarsController.getInstance(this.currentAccount).buyGiveaway(r6, this.currentChat, this.selectedChats, r9, r10, this.selectedCountries, BoostRepository.prepareServerDate(this.selectedEndDate), this.isShowWinnersSelected, r14, this.isAdditionalPrizeSelected, this.additionalPrize, new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda12(this, r9));
        return;
    L34:
        r14 = false;
        goto L35
    L80:
        return;
    L79:
        return;
    L37:
        int r02 = this.selectedBoostSubType;
        int r2 = BoostTypeCell.TYPE_SPECIFIC_USERS;
        List<TLRPC.TL_premiumGiftCodeOption> r5 = this.giftCodeOptions;
        if (r02 != r2) goto L54;
        List<TLRPC.TL_premiumGiftCodeOption> r03 = BoostRepository.filterGiftOptions(r5, this.selectedUsers.size());
    L41:
        if (r3 >= r03.size()) goto L83;
        TLRPC.TL_premiumGiftCodeOption r62 = r03.get(r3);
        if (r62.months != this.selectedMonths) goto L53;
        if (this.selectedUsers.size() <= 0) goto L53;
        if (BoostRepository.isGoogleBillingAvailable() == true) goto L49;
    L51:
        this.actionBtn.updateLoading(true);
        BoostRepository.payGiftCode(this.selectedUsers, r62, this.currentChat, null, r28, new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda13(this), new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda14(this));
        return;
    L49:
        if (BoostDialogs.checkReduceUsers(getContext(), this.resourcesProvider, this.giftCodeOptions, r62) == false) goto L51;
        return;
    L53:
        r3 = r3 + 1;
        goto L41
    L83:
        return;
    L54:
        List<TLRPC.TL_premiumGiftCodeOption> r04 = BoostRepository.filterGiftOptions(r5, getSelectedSliderValue());
        int r22 = 0;
    L56:
        if (r22 >= r04.size()) goto L84;
        TLRPC.TL_premiumGiftCodeOption r102 = r04.get(r22);
        if (r102.months == this.selectedMonths) goto L60;
        r22 = r22 + 1;
        goto L56
    L60:
        if (BoostRepository.isGoogleBillingAvailable() == false) goto L65;
        if (BoostDialogs.checkReduceQuantity(this.sliderValues, getContext(), this.resourcesProvider, this.giftCodeOptions, r102, new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda15(this)) == false) goto L65;
        return;
    L65:
        if (this.selectedParticipantsType != ParticipantsTypeCell.TYPE_NEW) goto L67;
        boolean r17 = true;
    L68:
        int r16 = BoostRepository.prepareServerDate(this.selectedEndDate);
        this.actionBtn.updateLoading(true);
        BoostRepository.payGiveAway(this.selectedChats, this.selectedCountries, r102, this.currentChat, r16, r17, r28, this.isShowWinnersSelected, this.isAdditionalPrizeSelected, this.additionalPrize, new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda16(this), new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda17(this));
        return;
    L67:
        r17 = false;
        goto L68
    L84:
        return;
    L8:
        if ((r25 instanceof TL_stories.TL_prepaidStarsGiveaway) == false) goto L11;
        TL_stories.TL_prepaidStarsGiveaway r05 = (TL_stories.TL_prepaidStarsGiveaway) r25;
    L10:
        TL_stories.TL_prepaidStarsGiveaway r32 = r05;
        if (r32 == null) goto L15;
        long r4 = r32.stars;
    L16:
        BoostDialogs.showStartGiveawayDialog(new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda11(this, r25, r32, r26, r4));
        return;
    L15:
        r4 = 0;
        goto L16
    L11:
        r05 = null;
        goto L10
    }

    private /* synthetic */ void lambda$new$3(int r3) {
        if (this.selectedBoostType != BoostTypeCell.TYPE_PREMIUM) goto L5;
        this.selectedSliderIndex = r3;
    L6:
        this.actionBtn.updateCounter(getSelectedSliderValueWithBoosts());
        if (this.selectedBoostType != BoostTypeCell.TYPE_STARS) goto L9;
        updateRows(true, true);
    L10:
        this.adapter.updateBoostCounter(getSelectedSliderValueWithBoosts());
        return;
    L9:
        updateRows(false, false);
        goto L10
    L5:
        this.selectedStarsSliderIndex = r3;
        goto L6
    }

    private /* synthetic */ void lambda$new$4(TLRPC.Chat r2) {
        this.selectedChats.remove(r2);
        updateRows(true, true);
    }

    private /* synthetic */ void lambda$new$5(String r1) {
        this.additionalPrize = r1;
        updateRows(false, false);
        updateRows(true, true);
    }

    private /* synthetic */ void lambda$new$7(TL_stories.PrepaidGiveaway r4) {
        NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationName(NotificationCenter.boostByChannelCreated, new Object[]{this.currentChat, Boolean.TRUE, r4});
    }

    private /* synthetic */ void lambda$new$8(TL_stories.TL_prepaidStarsGiveaway r1, long r2, long r4, TL_stories.PrepaidGiveaway r6, Void r7) {
        dismiss();
        if (r1 == null) goto L8;
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 == null) goto L10;
        ChatActivity r12 = ChatActivity.of(r2);
        r12.whenFullyVisible(new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda26(r12, r4));
        r0.presentFragment(r12);
        return;
    L10:
        return;
    L8:
        AndroidUtilities.runOnUIThread(new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda27(this, r6), 220);
    }

    private /* synthetic */ void lambda$new$9(TLRPC.TL_error r3) {
        this.actionBtn.updateLoading(false);
        BoostDialogs.showToastError(getContext(), r3);
    }

    private /* synthetic */ void lambda$updateRows$22(DialogInterface r1) {
        this.adapter.setPausedStars(false);
    }

    private /* synthetic */ void lambda$updateRows$23(DialogInterface r1) {
        this.adapter.setPausedStars(true);
    }

    private /* synthetic */ void lambda$updateRows$24() {
        PremiumPreviewBottomSheet r0 = new PremiumPreviewBottomSheet(getBaseFragment(), this.currentAccount, null, this.resourcesProvider);
        r0.setOnDismissListener(new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda8(this));
        r0.setOnShowListener(new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda9(this));
        r0.show();
    }

    private /* synthetic */ void lambda$updateRows$25(DialogInterface r1) {
        this.adapter.setPausedStars(false);
    }

    private /* synthetic */ void lambda$updateRows$26(DialogInterface r1) {
        this.adapter.setPausedStars(true);
    }

    private /* synthetic */ void lambda$updateRows$27() {
        PremiumPreviewBottomSheet r0 = new PremiumPreviewBottomSheet(getBaseFragment(), this.currentAccount, null, this.resourcesProvider);
        r0.setOnDismissListener(new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda19(this));
        r0.setOnShowListener(new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda20(this));
        r0.show();
    }

    private void loadOptions() {
        BoostRepository.loadGiftOptions(this.currentAccount, this.currentChat, new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda18(this));
    }

    private void updateActionButton(boolean r4) {
        if (isPreparedGiveaway() == false) goto L10;
        TL_stories.PrepaidGiveaway r0 = this.prepaidGiveaway;
        boolean r1 = r0 instanceof TL_stories.TL_prepaidStarsGiveaway;
        ActionBtnCell r3 = this.actionBtn;
        if (r1 == false) goto L8;
        r3.setStartGiveAwayStyle(r0.quantity, r4);
        return;
    L8:
        r3.setStartGiveAwayStyle(r0.quantity * BoostRepository.giveawayBoostsPerPremium(), r4);
        return;
    L10:
        int r02 = this.selectedBoostSubType;
        int r12 = BoostTypeCell.TYPE_GIVEAWAY;
        ActionBtnCell r2 = this.actionBtn;
        if (r02 != r12) goto L14;
        r2.setStartGiveAwayStyle(getSelectedSliderValueWithBoosts(), r4);
        return;
    L14:
        int r03 = this.selectedUsers.size() * BoostRepository.giveawayBoostsPerPremium();
        if (this.selectedUsers.size() <= 0) goto L17;
        boolean r32 = true;
    L18:
        r2.setGiftPremiumStyle(r03, r4, r32);
        return;
    L17:
        r32 = false;
        goto L18
    }

    @SuppressLint({"NotifyDataSetChanged"})
    private void updateRows(boolean r26, boolean r27) {
        ArrayList<? extends AdapterWithDiffUtils.Item> r1 = new ArrayList(this.items);
        this.items.clear();
        ArrayList<BoostAdapter.Item> r2 = this.items;
        if (this.selectedBoostType != BoostTypeCell.TYPE_STARS) goto L5;
        boolean r3 = true;
    L6:
        r2.add(BoostAdapter.Item.asHeader(r3));
        boolean r22 = isPreparedGiveaway();
        ArrayList<BoostAdapter.Item> r32 = this.items;
        if (r22 == false) goto L9;
        r32.add(BoostAdapter.Item.asSingleBoost(this.prepaidGiveaway));
    L10:
        this.items.add(BoostAdapter.Item.asDivider());
        boolean r23 = ChatObject.isChannelAndNotMegaGroup(this.currentChat);
        if (this.selectedBoostType != BoostTypeCell.TYPE_STARS) goto L84;
        if (isPreparedGiveaway() == true) goto L55;
        BoostAdapter.Item r33 = BoostAdapter.Item.asSubTitleWithCounter(LocaleController.getString(R.string.BoostingStarsOptions), getSelectedSliderValueWithBoosts());
        this.items.add(r33);
        List<Long> r4 = getStarsOptions();
        int r7 = 0;
        int r8 = 0;
    L16:
        if (r7 >= r4.size()) goto L40;
        TL_stars.TL_starsGiveawayOption r11 = getSelectedStarsOption(r4.get(r7).longValue());
        if (r11.missingStorePrice == false) goto L21;
    L29:
        List<Long> r17 = r4;
    L39:
        r7 = r7 + 1;
        r4 = r17;
        goto L16
    L21:
        if (this.selectedStars != 0) goto L26;
        if (r11.isDefault == false) goto L26;
        this.selectedStars = r11.stars;
    L26:
        if (r11.extended == true) goto L28;
    L30:
        r8 = r8 + 1;
        ArrayList<BoostAdapter.Item> r9 = this.items;
        if (this.starOptionsExpanded == false) goto L33;
        int r12 = r7;
    L34:
        long r13 = getSelectedPerUserStars(r11.stars);
        r17 = r4;
        if (this.selectedStars != r11.stars) goto L37;
        boolean r15 = true;
    L38:
        r9.add(BoostAdapter.Item.asOption(r11, r12, r13, r15, true));
        goto L39
    L37:
        r15 = false;
        goto L38
    L33:
        r12 = r7 + 2;
        goto L34
    L28:
        if (this.starOptionsExpanded == true) goto L30;
    L40:
        List<Long> r172 = r4;
        if (this.starOptionsExpanded == false) goto L43;
    L45:
        if (r8 > 0) goto L47;
        this.items.add(BoostAdapter.Item.asOption(null, 0, 1, false, true));
        this.items.add(BoostAdapter.Item.asOption(null, 1, 1, false, true));
        this.items.add(BoostAdapter.Item.asOption(null, 2, 1, false, false));
    L47:
        r33.intValue = getSelectedSliderValueWithBoosts();
        this.items.add(BoostAdapter.Item.asDivider(LocaleController.getString(R.string.BoostingStarsOptionsInfo), false));
        List<Integer> r34 = getSliderValues();
        int r42 = this.selectedStarsSliderIndex;
        if (r42 >= 0) goto L50;
    L51:
        this.selectedStarsSliderIndex = 0;
    L53:
        if (r34.size() <= 1) goto L58;
        this.items.add(BoostAdapter.Item.asSubTitle(LocaleController.getString(R.string.BoostingStarsQuantityPrizes)));
        this.items.add(BoostAdapter.Item.asSlider(getSliderValues(), this.selectedStarsSliderIndex));
        this.items.add(BoostAdapter.Item.asDivider(LocaleController.getString(R.string.BoostingStarsQuantityPrizesInfo), false));
    L58:
        this.items.add(BoostAdapter.Item.asSubTitle(LocaleController.getString(R.string.BoostingChannelsGroupsIncludedGiveaway)));
        if (isPreparedGiveaway() == false) goto L64;
        TL_stories.PrepaidGiveaway r35 = this.prepaidGiveaway;
        boolean r43 = r35 instanceof TL_stories.TL_prepaidStarsGiveaway;
        ArrayList<BoostAdapter.Item> r5 = this.items;
        if (r43 == false) goto L63;
        r5.add(BoostAdapter.Item.asChat(this.currentChat, false, r35.quantity));
    L65:
        Iterator<TLObject> r36 = this.selectedChats.iterator();
    L67:
        if (r36.hasNext() == false) goto L76;
        TLObject r44 = r36.next();
        if ((r44 instanceof TLRPC.Chat) == false) goto L71;
        boolean r10 = true;
        this.items.add(BoostAdapter.Item.asChat((TLRPC.Chat) r44, true, getSelectedSliderValueWithBoosts()));
    L73:
        if ((r44 instanceof TLRPC.InputPeer) == false) goto L67;
        this.items.add(BoostAdapter.Item.asPeer((TLRPC.InputPeer) r44, r10, getSelectedSliderValueWithBoosts()));
        goto L67
    L71:
        r10 = true;
        goto L73
    L76:
        if (this.selectedChats.size() >= BoostRepository.giveawayAddPeersMax()) goto L78;
        this.items.add(BoostAdapter.Item.asAddChannel());
    L78:
        this.items.add(BoostAdapter.Item.asDivider(LocaleController.getString(R.string.BoostingChooseChannelsGroupsNeedToJoin), false));
        this.items.add(BoostAdapter.Item.asSubTitle(LocaleController.getString(R.string.BoostingEligibleUsers)));
        this.items.add(BoostAdapter.Item.asParticipants(ParticipantsTypeCell.TYPE_ALL, this.selectedParticipantsType, true, this.selectedCountries));
        this.items.add(BoostAdapter.Item.asParticipants(ParticipantsTypeCell.TYPE_NEW, this.selectedParticipantsType, false, this.selectedCountries));
        ArrayList<BoostAdapter.Item> r37 = this.items;
        if (r23 == false) goto L81;
        int r45 = R.string.BoostingChooseLimitGiveaway;
    L82:
        r37.add(BoostAdapter.Item.asDivider(LocaleController.getString(r45), false));
    L137:
        if (this.selectedBoostType != BoostTypeCell.TYPE_STARS) goto L139;
    L140:
        ArrayList<BoostAdapter.Item> r38 = this.items;
        String r46 = LocaleController.getString(R.string.BoostingGiveawayAdditionalPrizes);
        boolean r52 = this.isAdditionalPrizeSelected;
        r38.add(BoostAdapter.Item.asSwitcher(r46, r52, r52, SwitcherCell.TYPE_ADDITION_PRIZE));
        if (this.isAdditionalPrizeSelected == true) goto L143;
        ArrayList<BoostAdapter.Item> r39 = this.items;
        if (this.selectedBoostType != BoostTypeCell.TYPE_STARS) goto L159;
        int r47 = R.string.BoostingStarsGiveawayAdditionPrizeHint;
    L160:
        r39.add(BoostAdapter.Item.asDivider(LocaleController.getString(r47), false));
    L161:
        this.items.add(BoostAdapter.Item.asSubTitle(LocaleController.getString(R.string.BoostingDateWhenGiveawayEnds)));
        this.items.add(BoostAdapter.Item.asDateEnd(this.selectedEndDate));
        if (this.selectedBoostType != BoostTypeCell.TYPE_STARS) goto L172;
        boolean r310 = isPreparedGiveaway();
        ArrayList<BoostAdapter.Item> r48 = this.items;
        String r6 = "BoostingStarsChooseRandomGroup";
        if (r310 == true) goto L169;
        if (r23 == false) goto L167;
        r6 = "BoostingStarsChooseRandom";
    L167:
        r48.add(BoostAdapter.Item.asDivider(LocaleController.formatPluralString(r6, getSelectedSliderValue(), new Object[]{LocaleController.formatPluralString("BoostingStarsChooseRandomStars", (int) this.selectedStars, new Object[0])}), false));
        boolean r72 = false;
    L181:
        this.items.add(BoostAdapter.Item.asSwitcher(LocaleController.getString(R.string.BoostingGiveawayShowWinners), this.isShowWinnersSelected, r72, SwitcherCell.TYPE_WINNERS));
        boolean r24 = isPreparedGiveaway();
        ArrayList<BoostAdapter.Item> r311 = this.items;
        if (r24 == true) goto L184;
        r311.add(BoostAdapter.Item.asDivider(LocaleController.getString(R.string.BoostingGiveawayShowWinnersHint), r72));
    L189:
        BoostAdapter r25 = this.adapter;
        if (r25 == null) goto L213;
        if (r27 == true) goto L194;
        return;
    L194:
        if (r26 == false) goto L197;
        r25.setItems(r1, this.items);
        return;
    L197:
        r25.notifyDataSetChanged();
        return;
    L213:
        return;
    L184:
        StringBuilder r28 = new StringBuilder();
        r28.append(LocaleController.getString(R.string.BoostingGiveawayShowWinnersHint));
        if (this.selectedBoostType == BoostTypeCell.TYPE_STARS) goto L187;
        String r49 = "\n\n" + LocaleController.getString(R.string.BoostingStoriesFeaturesAndTerms);
    L188:
        r28.append(r49);
        r311.add(BoostAdapter.Item.asDivider(AndroidUtilities.replaceSingleTag(r28.toString(), Theme.key_chat_messageLinkIn, 0, new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda7(this), this.resourcesProvider), true));
        goto L189
    L187:
        r49 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L188
    L169:
        if (r23 == false) goto L171;
        r6 = "BoostingStarsChooseRandom";
    L171:
        r48.add(BoostAdapter.Item.asDivider(LocaleController.formatPluralString(r6, this.prepaidGiveaway.quantity, new Object[]{LocaleController.formatPluralString("BoostingStarsChooseRandomStars", (int) this.selectedStars, new Object[0])}), false));
        r72 = false;
        goto L181
    L172:
        boolean r312 = isPreparedGiveaway();
        ArrayList<BoostAdapter.Item> r410 = this.items;
        String r53 = "BoostingChooseRandomGroup";
        if (r312 == true) goto L177;
        if (r23 == false) goto L176;
        r53 = "BoostingChooseRandom";
    L176:
        r72 = false;
        r410.add(BoostAdapter.Item.asDivider(LocaleController.formatPluralString(r53, getSelectedSliderValue(), new Object[0]), false));
        goto L181
    L177:
        r72 = false;
        if (r23 == false) goto L180;
        r53 = "BoostingChooseRandom";
    L180:
        r410.add(BoostAdapter.Item.asDivider(LocaleController.formatPluralString(r53, this.prepaidGiveaway.quantity, new Object[0]), false));
        goto L181
    L159:
        r47 = R.string.BoostingGiveawayAdditionPrizeHint;
        goto L160
    L143:
        if (isPreparedGiveaway() == false) goto L145;
        int r313 = this.prepaidGiveaway.quantity;
    L146:
        this.items.add(BoostAdapter.Item.asEnterPrize(r313));
        String r411 = LocaleController.formatPluralString("BoldMonths", this.selectedMonths, new Object[0]);
        int r54 = this.selectedBoostType;
        int r62 = BoostTypeCell.TYPE_STARS;
        String r73 = this.additionalPrize;
        if (r54 != r62) goto L152;
        boolean r412 = r73.isEmpty();
        ArrayList<BoostAdapter.Item> r55 = this.items;
        if (r412 == false) goto L151;
        r55.add(BoostAdapter.Item.asDivider(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingStarsGiveawayAdditionPrizeCountHint", (int) this.selectedStars, new Object[0])), false));
        goto L161
    L151:
        r55.add(BoostAdapter.Item.asDivider(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingStarsGiveawayAdditionPrizeCountNameHint", (int) this.selectedStars, new Object[]{Integer.valueOf(r313), this.additionalPrize})), false));
        goto L161
    L152:
        boolean r56 = r73.isEmpty();
        ArrayList<BoostAdapter.Item> r74 = this.items;
        if (r56 == false) goto L155;
        r74.add(BoostAdapter.Item.asDivider(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGiveawayAdditionPrizeCountHint", r313, new Object[]{r411})), false));
        goto L161
    L155:
        r74.add(BoostAdapter.Item.asDivider(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGiveawayAdditionPrizeCountNameHint", r313, new Object[]{this.additionalPrize, r411})), false));
        goto L161
    L145:
        r313 = getSelectedSliderValue();
        goto L146
    L139:
        if (this.selectedBoostSubType != BoostTypeCell.TYPE_GIVEAWAY) goto L189;
    L81:
        r45 = R.string.BoostingChooseLimitGiveawayGroups;
        goto L82
    L63:
        r5.add(BoostAdapter.Item.asChat(this.currentChat, false, r35.quantity * BoostRepository.giveawayBoostsPerPremium()));
        goto L65
    L64:
        this.items.add(BoostAdapter.Item.asChat(this.currentChat, false, getSelectedSliderValueWithBoosts()));
        goto L65
    L50:
        if (r42 < r34.size()) goto L53;
    L43:
        if (r8 >= r172.size()) goto L45;
        this.items.add(BoostAdapter.Item.asExpandOptions());
        goto L45
    L55:
        TL_stories.PrepaidGiveaway r314 = this.prepaidGiveaway;
        if ((r314 instanceof TL_stories.TL_prepaidStarsGiveaway) == false) goto L58;
        this.selectedStars = ((TL_stories.TL_prepaidStarsGiveaway) r314).stars;
        goto L58
    L84:
        if (this.selectedBoostSubType != BoostTypeCell.TYPE_GIVEAWAY) goto L114;
        if (isPreparedGiveaway() == true) goto L88;
        this.items.add(BoostAdapter.Item.asSubTitleWithCounter(LocaleController.getString(R.string.BoostingQuantityPrizes), getSelectedSliderValueWithBoosts()));
        this.items.add(BoostAdapter.Item.asSlider(this.sliderValues, this.selectedSliderIndex));
        this.items.add(BoostAdapter.Item.asDivider(LocaleController.getString(R.string.BoostingChooseHowMany), false));
    L88:
        this.items.add(BoostAdapter.Item.asSubTitle(LocaleController.getString(R.string.BoostingChannelsGroupsIncludedGiveaway)));
        if (isPreparedGiveaway() == false) goto L94;
        TL_stories.PrepaidGiveaway r315 = this.prepaidGiveaway;
        boolean r413 = r315 instanceof TL_stories.TL_prepaidStarsGiveaway;
        ArrayList<BoostAdapter.Item> r57 = this.items;
        if (r413 == false) goto L93;
        r57.add(BoostAdapter.Item.asChat(this.currentChat, false, r315.quantity));
    L95:
        Iterator<TLObject> r316 = this.selectedChats.iterator();
    L97:
        if (r316.hasNext() == false) goto L106;
        TLObject r414 = r316.next();
        if ((r414 instanceof TLRPC.Chat) == false) goto L101;
        boolean r102 = true;
        this.items.add(BoostAdapter.Item.asChat((TLRPC.Chat) r414, true, getSelectedSliderValueWithBoosts()));
    L103:
        if ((r414 instanceof TLRPC.InputPeer) == false) goto L97;
        this.items.add(BoostAdapter.Item.asPeer((TLRPC.InputPeer) r414, r102, getSelectedSliderValueWithBoosts()));
        goto L97
    L101:
        r102 = true;
        goto L103
    L106:
        if (this.selectedChats.size() >= BoostRepository.giveawayAddPeersMax()) goto L108;
        this.items.add(BoostAdapter.Item.asAddChannel());
    L108:
        this.items.add(BoostAdapter.Item.asDivider(LocaleController.getString(R.string.BoostingChooseChannelsGroupsNeedToJoin), false));
        this.items.add(BoostAdapter.Item.asSubTitle(LocaleController.getString(R.string.BoostingEligibleUsers)));
        this.items.add(BoostAdapter.Item.asParticipants(ParticipantsTypeCell.TYPE_ALL, this.selectedParticipantsType, true, this.selectedCountries));
        this.items.add(BoostAdapter.Item.asParticipants(ParticipantsTypeCell.TYPE_NEW, this.selectedParticipantsType, false, this.selectedCountries));
        ArrayList<BoostAdapter.Item> r317 = this.items;
        if (r23 == false) goto L111;
        int r415 = R.string.BoostingChooseLimitGiveaway;
    L112:
        r317.add(BoostAdapter.Item.asDivider(LocaleController.getString(r415), false));
        goto L114
    L111:
        r415 = R.string.BoostingChooseLimitGiveawayGroups;
        goto L112
    L93:
        r57.add(BoostAdapter.Item.asChat(this.currentChat, false, r315.quantity * BoostRepository.giveawayBoostsPerPremium()));
        goto L95
    L94:
        this.items.add(BoostAdapter.Item.asChat(this.currentChat, false, getSelectedSliderValueWithBoosts()));
    L114:
        if (isPreparedGiveaway() == true) goto L134;
        this.items.add(BoostAdapter.Item.asSubTitle(LocaleController.getString(R.string.BoostingDurationOfPremium)));
        List<TLRPC.TL_premiumGiftCodeOption> r318 = this.giftCodeOptions;
        if (isGiveaway() == false) goto L118;
        int r416 = getSelectedSliderValue();
    L119:
        List<TLRPC.TL_premiumGiftCodeOption> r319 = BoostRepository.filterGiftOptions(r318, r416);
        int r417 = 0;
    L121:
        if (r417 >= r319.size()) goto L134;
        TLRPC.TL_premiumGiftCodeOption r58 = r319.get(r417);
        ArrayList<BoostAdapter.Item> r63 = this.items;
        int r75 = r58.months;
        if (isGiveaway() == false) goto L126;
        int r82 = getSelectedSliderValue();
    L125:
        int r19 = r82;
        long r83 = r58.amount;
        int r112 = this.selectedMonths;
        String r122 = r58.currency;
        if (r417 == (r319.size() - 1)) goto L131;
        boolean r242 = true;
    L132:
        r63.add(BoostAdapter.Item.asDuration(r58, r75, r19, r83, r112, r122, r242));
        r417 = r417 + 1;
        goto L121
    L131:
        r242 = false;
        goto L132
    L126:
        r82 = this.selectedUsers.size();
        goto L125
    L118:
        r416 = this.selectedUsers.size();
    L134:
        if (isPreparedGiveaway() == true) goto L137;
        this.items.add(BoostAdapter.Item.asDivider(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.BoostingStoriesFeaturesAndTerms), Theme.key_chat_messageLinkIn, 0, new BoostViaGiftsBottomSheet$$ExternalSyntheticLambda6(this), this.resourcesProvider), true));
        goto L137
    L9:
        r32.add(BoostAdapter.Item.asBoost(BoostTypeCell.TYPE_PREMIUM, this.selectedUsers.size(), null, this.selectedBoostType));
        this.items.add(BoostAdapter.Item.asBoost(BoostTypeCell.TYPE_STARS, this.selectedUsers.size(), null, this.selectedBoostType));
        goto L10
    L5:
        r3 = false;
        goto L6
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r2) {
        BoostAdapter r22 = new BoostAdapter(this.resourcesProvider);
        this.adapter = r22;
        return r22;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.starGiveawayOptionsLoaded) goto L10;
        RecyclerListView r12 = this.recyclerListView;
        if (r12 != null) goto L7;
        return;
    L7:
        if (r12.isAttachedToWindow() == false) goto L12;
        updateRows(true, true);
        return;
    L12:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        Runnable r0 = this.onCloseClick;
        if (r0 == null) goto L5;
        r0.run();
    L5:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starGiveawayOptionsLoaded);
    }

    public TL_stars.TL_starsGiveawayOption getSelectedStarsOption() {
        return getSelectedStarsOption(this.selectedStars);
    }

    public TL_stars.TL_starsGiveawayOption getSelectedStarsOption(long r5) {
        ArrayList<TL_stars.TL_starsGiveawayOption> r4 = StarsController.getInstance(this.currentAccount).getGiveawayOptions();
        if (r4 == null) goto L13;
        int r0 = 0;
    L6:
        if (r0 >= r4.size()) goto L19;
        TL_stars.TL_starsGiveawayOption r1 = r4.get(r0);
        if (r1 == null) goto L12;
        if (r1.stars != r5) goto L12;
        return r1;
    L12:
        r0 = r0 + 1;
        goto L6
    L19:
        return null;
    L13:
        return null;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        if (this.selectedBoostSubType != BoostTypeCell.TYPE_SPECIFIC_USERS) goto L7;
        return LocaleController.getString(R.string.GiftPremium);
    L7:
        return LocaleController.formatString("BoostingStartGiveaway", R.string.BoostingStartGiveaway, new Object[0]);
    }

    public int getTop() {
        int r1 = -AndroidUtilities.dp(16.0f);
        int r2 = this.top;
        if (this.actionBar.getVisibility() != 0) goto L5;
        int r3 = AndroidUtilities.statusBarHeight + AndroidUtilities.dp(16.0f);
    L7:
        return Math.max(r1, r2 - r3);
    L5:
        r3 = 0;
        goto L7
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public boolean needPaddingShadow() {
        return false;
    }

    @Override // org.telegram.ui.Components.Premium.boosts.SelectorBottomSheet.SelectedObjectsListener
    public void onChatsSelected(List<TLRPC.Chat> r2, boolean r3) {
        this.selectedChats.clear();
        this.selectedChats.addAll(r2);
        updateRows(r3, true);
    }

    @Override // org.telegram.ui.Components.Premium.boosts.SelectorBottomSheet.SelectedObjectsListener
    public void onCountrySelected(List<TLRPC.TL_help_country> r2) {
        this.selectedCountries.clear();
        this.selectedCountries.addAll(r2);
        updateRows(false, true);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public void onPreDraw(Canvas r1, int r2, float r3) {
        this.top = r2;
    }

    @Override // org.telegram.ui.Components.Premium.boosts.SelectorBottomSheet.SelectedObjectsListener
    public void onUsersSelected(List<TLRPC.User> r2) {
        this.selectedUsers.clear();
        this.selectedUsers.addAll(r2);
        if (r2.isEmpty() == false) goto L5;
        this.selectedBoostSubType = BoostTypeCell.TYPE_GIVEAWAY;
    L6:
        this.selectedSliderIndex = 0;
        updateRows(false, true);
        updateActionButton(true);
        updateTitle();
        return;
    L5:
        this.selectedBoostSubType = BoostTypeCell.TYPE_SPECIFIC_USERS;
        goto L6
    }

    public void setActionListener(ActionListener r1) {
        this.actionListener = r1;
    }

    public void setOnCloseClick(Runnable r1) {
        this.onCloseClick = r1;
    }
}
