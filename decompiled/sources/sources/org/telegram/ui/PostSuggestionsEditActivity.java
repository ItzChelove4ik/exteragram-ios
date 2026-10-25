package org.telegram.ui;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.SlideIntChooseView;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CrossfadeDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkActionView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.bots.AffiliateProgramFragment;

/* loaded from: classes6.dex */
public class PostSuggestionsEditActivity extends BaseFragment {
    private final long currentChatId;
    private ActionBarMenuItem doneButton;
    private CrossfadeDrawable doneButtonDrawable;
    private final boolean initialSuggestionsEnabled;
    private final long initialSuggestionsStarsCount;
    private boolean isSuggestionsEnabled;
    private boolean lastHasChanges;
    private LinkActionView linkView;
    private UniversalRecyclerView listView;
    private SlideIntChooseView slideView;
    private MessagesStorage.LongCallback starsCallback;
    private long suggestionsStarsCount;

    /* renamed from: org.telegram.ui.PostSuggestionsEditActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PostSuggestionsEditActivity this$0;

        public AnonymousClass1(PostSuggestionsEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 == (-1)) goto L5;
            if (r3 != 1) goto L12;
            PostSuggestionsEditActivity.m17578$$Nest$mprocessDone(this.this$0);
            return;
        L12:
            return;
        L5:
            if (this.this$0.onBackPressed(true) == false) goto L11;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: $r8$lambda$0bYrQYeygCotBPBkJpzO5-JE-QY, reason: not valid java name */
    public static /* synthetic */ void m17575$r8$lambda$0bYrQYeygCotBPBkJpzO5JEQY(PostSuggestionsEditActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$3dOgvMoCwp_9k3l32FJp4y3igGg(PostSuggestionsEditActivity r0, TLRPC.TL_error r1, TLObject r2, TL_stars.updatePaidMessagesPrice r3) {
        r0.lambda$processDone$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$7nyTGA_Y1Jr34mzdHo4sKx73cgE(PostSuggestionsEditActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onBackPressed$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$9UJEfy6Tq3XorP0t8Es2e_UsMzk(PostSuggestionsEditActivity r0, Integer r1) {
        r0.lambda$fillItems$1(r1);
    }

    public static /* synthetic */ CharSequence $r8$lambda$efkxi1fRkv5jAWLPL4Zu0G3oRFk(Integer r1, Integer r2) {
        if (r1.intValue() != 0) goto L7;
        return StarsIntroActivity.replaceStarsWithPlain(LocaleController.formatPluralStringComma("Stars", r2.intValue()), 0.66f);
    L7:
        return LocaleController.formatNumber(r2.intValue(), ',');
    }

    /* renamed from: $r8$lambda$lI_IrR2GqLrWUELjEBae-C__qyk, reason: not valid java name */
    public static /* synthetic */ void m17576$r8$lambda$lI_IrR2GqLrWUELjEBaeC__qyk(PostSuggestionsEditActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onItemClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$mm4rRljUkcN5Mb_TvGjtx_ict_I(PostSuggestionsEditActivity r0, TL_stars.updatePaidMessagesPrice r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$processDone$3(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$pz8vEzbtwZvN-jdKINPq16HG1p8, reason: not valid java name */
    public static /* synthetic */ void m17577$r8$lambda$pz8vEzbtwZvNjdKINPq16HG1p8(PostSuggestionsEditActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onBackPressed$4(r1, r2);
    }

    /* renamed from: -$$Nest$mprocessDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17578$$Nest$mprocessDone(PostSuggestionsEditActivity r0) {
        r0.processDone();
    }

    public PostSuggestionsEditActivity(long r10) {
        boolean r0 = true;
        this.lastHasChanges = true;
        this.currentChatId = r10;
        TLRPC.Chat r102 = getMessagesController().getChat(Long.valueOf(r10));
        long r1 = 0;
        if (r102 != null) goto L5;
    L7:
        TLRPC.Chat r11 = null;
    L8:
        if (r11 == null) goto L11;
        r1 = r11.send_paid_messages_stars;
    L11:
        if (r102 != null) goto L13;
    L15:
        r0 = false;
    L16:
        this.initialSuggestionsEnabled = r0;
        if (r0 == true) goto L20;
        r1 = getMessagesController().config.starsPaidMessagesChannelAmountDefault.get();
    L20:
        long r103 = Utilities.clamp(r1, getMessagesController().starsPaidMessageAmountMax, 0);
        this.initialSuggestionsStarsCount = r103;
        this.isSuggestionsEnabled = r0;
        this.suggestionsStarsCount = r103;
        return;
    L13:
        if (r102.broadcast_messages_allowed == false) goto L15;
    L5:
        if (r102.linked_monoforum_id == 0) goto L7;
        r11 = getMessagesController().getChat(Long.valueOf(r102.linked_monoforum_id));
        goto L8
    }

    private void checkDone(boolean r5) {
        if (this.doneButton == null) goto L35;
        boolean r0 = hasChanges();
        if (this.lastHasChanges != r0) goto L8;
        return;
    L8:
        this.lastHasChanges = r0;
        this.doneButton.setEnabled(r0);
        ActionBarMenuItem r1 = this.doneButton;
        float r2 = 0.0f;
        if (r5 == false) goto L23;
        ViewPropertyAnimator r4 = r1.animate();
        if (r0 == false) goto L13;
        float r52 = 1.0f;
    L14:
        ViewPropertyAnimator r42 = r4.alpha(r52);
        if (r0 == false) goto L17;
        float r53 = 1.0f;
    L18:
        ViewPropertyAnimator r43 = r42.scaleX(r53);
        if (r0 == false) goto L21;
        r2 = 1.0f;
    L21:
        r43.scaleY(r2).setDuration(180).start();
        return;
    L17:
        r53 = 0.0f;
        goto L18
    L13:
        r52 = 0.0f;
        goto L14
    L23:
        if (r0 == false) goto L25;
        float r54 = 1.0f;
    L26:
        r1.setAlpha(r54);
        ActionBarMenuItem r55 = this.doneButton;
        if (r0 == false) goto L29;
        float r12 = 1.0f;
    L30:
        r55.setScaleX(r12);
        ActionBarMenuItem r44 = this.doneButton;
        if (r0 == false) goto L33;
        r2 = 1.0f;
    L33:
        r44.setScaleY(r2);
        return;
    L29:
        r12 = 0.0f;
        goto L30
    L25:
        r54 = 0.0f;
        goto L26
    }

    private void fillItems(ArrayList<UItem> r9, UniversalAdapter r10) {
        r9.add(UItem.asTopView(LocaleController.getString(R.string.AllowPostSuggestionsHint2), R.raw.bubble));
        r9.add(UItem.asCheck(1, LocaleController.getString(R.string.AllowPostSuggestions)).setChecked(this.isSuggestionsEnabled));
        CharSequence r1 = null;
        r9.add(UItem.asShadow(2, null));
        if (this.isSuggestionsEnabled == false) goto L13;
        r9.add(UItem.asHeader(LocaleController.getString(R.string.PriceForEachSuggestion)));
        this.slideView.set((int) Utilities.clamp(this.suggestionsStarsCount, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, 0), SlideIntChooseView.Options.make(1, SlideIntChooseView.cut(new int[]{0, 10, 50, 100, 200, 250, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, 1000, 2500, 5000, 7500, 9000, 10000}, (int) getMessagesController().starsPaidMessageAmountMax), 20, new PostSuggestionsEditActivity$$ExternalSyntheticLambda4()), new PostSuggestionsEditActivity$$ExternalSyntheticLambda5(this));
        r9.add(UItem.asCustom(3, this.slideView));
        if (this.suggestionsStarsCount <= 0) goto L7;
        r1 = getIncomeInfo();
    L7:
        r9.add(UItem.asShadow(4, r1));
        TLRPC.Chat r102 = getMessagesController().getChat(Long.valueOf(this.currentChatId));
        if (r102 != null) goto L10;
        return;
    L10:
        if (TextUtils.isEmpty(ChatObject.getPublicUsername(r102)) == true) goto L15;
        this.linkView.setLink(getMessagesController().linkPrefix + "/" + ChatObject.getPublicUsername(r102) + "?direct");
        r9.add(UItem.asHeader(LocaleController.getString(R.string.ChannelLinkDirectMessages)));
        r9.add(UItem.asCustom(5, this.linkView));
        return;
    L15:
        return;
    }

    private CharSequence getIncomeInfo() {
        int r0 = getMessagesController().starsPaidMessageCommissionPermille;
        float r2 = this.suggestionsStarsCount;
        return LocaleController.formatString(R.string.PostSuggestionsPriceInfo2, new Object[]{AffiliateProgramFragment.percents(r0), String.valueOf(((int) (((r2 * (r0 / 1000.0f)) / 1000.0d) * getMessagesController().starsUsdWithdrawRate1000)) / 100.0d)});
    }

    private boolean hasChanges() {
        if (this.suggestionsStarsCount == this.initialSuggestionsStarsCount) goto L5;
        return true;
    L5:
        if (this.isSuggestionsEnabled != this.initialSuggestionsEnabled) goto L11;
        return false;
    L11:
        return true;
    }

    private /* synthetic */ void lambda$fillItems$1(Integer r7) {
        this.suggestionsStarsCount = r7.intValue();
        View r72 = this.listView.findViewByItemId(4);
        if ((r72 instanceof TextInfoPrivacyCell) == false) goto L9;
        TextInfoPrivacyCell r73 = (TextInfoPrivacyCell) r72;
        if (r73.getFixedSize() > 0) goto L9;
        if (this.suggestionsStarsCount <= 0) goto L9;
        r73.setText(getIncomeInfo());
    L10:
        checkDone(true);
        return;
    L9:
        this.listView.adapter.update(true);
        goto L10
    }

    private /* synthetic */ void lambda$onBackPressed$4(AlertDialog r1, int r2) {
        processDone();
    }

    private /* synthetic */ void lambda$onBackPressed$5(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$processDone$2(TLRPC.TL_error r3, TLObject r4, TL_stars.updatePaidMessagesPrice r5) {
        if (r3 == null) goto L5;
        this.doneButtonDrawable.animateToProgress(0.0f);
        BulletinFactory.showError(r3);
        return;
    L5:
        TLRPC.Updates r42 = (TLRPC.Updates) r4;
        getMessagesController().putChats(r42.chats, false);
        getMessagesController().processUpdates(r42, false);
        if (this.isFinished == false) goto L8;
        return;
    L8:
        if (this.finishing == true) goto L19;
        MessagesStorage.LongCallback r32 = this.starsCallback;
        if (r32 != null) goto L12;
    L16:
        finishFragment();
        return;
    L12:
        if (r5.suggestions_allowed == false) goto L14;
        long r43 = r5.send_paid_messages_stars;
    L15:
        r32.run(r43);
        goto L16
    L14:
        r43 = -1;
        goto L15
    }

    private /* synthetic */ void lambda$processDone$3(TL_stars.updatePaidMessagesPrice r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new PostSuggestionsEditActivity$$ExternalSyntheticLambda7(this, r4, r3, r2));
    }

    private void onItemClick(UItem r1, View r2, int r3, float r4, float r5) {
        if (r1.id != 1) goto L6;
        TextCheckCell r22 = (TextCheckCell) r2;
        boolean r12 = !r22.isChecked();
        this.isSuggestionsEnabled = r12;
        r22.setChecked(r12);
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    }

    private void processDone() {
        if (this.doneButtonDrawable.getProgress() <= 0.0f) goto L6;
        return;
    L6:
        if (hasChanges() == true) goto L9;
        finishFragment();
        return;
    L9:
        this.doneButtonDrawable.animateToProgress(1.0f);
        TL_stars.updatePaidMessagesPrice r0 = new TL_stars.updatePaidMessagesPrice();
        r0.channel = getMessagesController().getInputChannel(this.currentChatId);
        boolean r1 = this.isSuggestionsEnabled;
        if (r1 == false) goto L12;
        long r4 = this.suggestionsStarsCount;
    L13:
        r0.send_paid_messages_stars = r4;
        r0.suggestions_allowed = r1;
        getConnectionsManager().sendRequest(r0, new PostSuggestionsEditActivity$$ExternalSyntheticLambda6(this, r0));
        TLRPC.Chat r02 = getMessagesController().getChat(Long.valueOf(this.currentChatId));
        if (r02 == null) goto L26;
        boolean r12 = this.isSuggestionsEnabled;
        int r42 = r02.flags2;
        if (r12 == false) goto L18;
        r02.flags2 = 65536 | r42;
        r02.broadcast_messages_allowed = true;
    L19:
        getMessagesController().putChat(r02, true);
        TLRPC.Chat r03 = getMessagesController().getChat(Long.valueOf(r02.linked_monoforum_id));
        if (r03 == null) goto L26;
        boolean r13 = this.isSuggestionsEnabled;
        int r43 = r03.flags2;
        if (r13 == false) goto L24;
        r03.flags2 = r43 | 16384;
        r03.send_paid_messages_stars = this.suggestionsStarsCount;
    L25:
        getMessagesController().putChat(r03, true);
        goto L26
    L24:
        r03.flags2 = r43 & (-16385);
        r03.send_paid_messages_stars = 0;
        goto L25
    L18:
        r02.flags2 = (-65537) & r42;
        r02.broadcast_messages_allowed = false;
    L26:
        MessagesStorage.LongCallback r04 = this.starsCallback;
        if (r04 != null) goto L29;
        return;
    L29:
        if (this.isSuggestionsEnabled == false) goto L31;
        long r14 = this.suggestionsStarsCount;
    L32:
        r04.run(r14);
        return;
    L31:
        r14 = -1;
        goto L32
    L12:
        r4 = 0;
        goto L13
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r13) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.PostSuggestions));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        Drawable r0 = r13.getResources().getDrawable(R.drawable.ic_ab_done).mutate();
        int r4 = Theme.key_actionBarDefaultIcon;
        r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r4), PorterDuff.Mode.MULTIPLY));
        this.doneButtonDrawable = new CrossfadeDrawable(r0, new CircularProgressDrawable(Theme.getColor(r4)));
        this.doneButton = this.actionBar.createMenu().addItemWithWidth(1, this.doneButtonDrawable, AndroidUtilities.dp(56.0f), LocaleController.getString(R.string.Done));
        checkDone(false);
        FrameLayout r02 = new FrameLayout(r13);
        this.fragmentView = r02;
        r02.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        FrameLayout r10 = (FrameLayout) this.fragmentView;
        SlideIntChooseView r03 = new SlideIntChooseView(r13, this.resourceProvider);
        this.slideView = r03;
        int r11 = Theme.key_windowBackgroundWhite;
        r03.setBackgroundColor(getThemedColor(r11));
        LinkActionView r04 = new LinkActionView(r13, this, null, this.currentChatId, true, true);
        this.linkView = r04;
        r04.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(16.0f), 0);
        this.linkView.setBackgroundColor(getThemedColor(r11));
        this.linkView.hideRevokeOption(true);
        this.linkView.setUsers(0, null);
        UniversalRecyclerView r05 = new UniversalRecyclerView(r13, this.currentAccount, this.classGuid, new PostSuggestionsEditActivity$$ExternalSyntheticLambda2(this), new PostSuggestionsEditActivity$$ExternalSyntheticLambda3(this), null, this.resourceProvider);
        this.listView = r05;
        r05.setSections();
        r10.addView(this.listView, LayoutHelper.createFrame(-1, -1, 51));
        this.actionBar.setAdaptiveBackground(this.listView);
        return this.fragmentView;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r1) {
        return !hasChanges();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r3) {
        if (hasChanges() == false) goto L9;
        if (r3 == false) goto L10;
        AlertDialog.Builder r32 = new AlertDialog.Builder(getParentActivity());
        r32.setTitle(LocaleController.getString(R.string.UnsavedChanges));
        r32.setMessage(LocaleController.getString(R.string.MessageSuggestionsUnsavedChanges));
        r32.setPositiveButton(LocaleController.getString(R.string.ApplyTheme), new PostSuggestionsEditActivity$$ExternalSyntheticLambda0(this));
        r32.setNegativeButton(LocaleController.getString(R.string.Discard), new PostSuggestionsEditActivity$$ExternalSyntheticLambda1(this));
        showDialog(r32.create());
        return false;
    L10:
        return false;
    L9:
        return super.onBackPressed(r3);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        UniversalRecyclerView r1 = this.listView;
        if (r1 == null) goto L9;
        UniversalAdapter r12 = r1.adapter;
        if (r12 == null) goto L10;
        r12.update(false);
        return true;
    L10:
        return true;
    L9:
        return true;
    }

    public PostSuggestionsEditActivity setOnApplied(MessagesStorage.LongCallback r1) {
        this.starsCallback = r1;
        return this;
    }
}
