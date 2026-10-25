package org.telegram.ui.Business;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Business.QuickRepliesController;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CrossfadeDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

/* loaded from: classes6.dex */
public class AwayMessagesActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private int currentScheduleCustomEnd;
    private int currentScheduleCustomStart;
    public TL_account.TL_businessAwayMessage currentValue;
    public int currentValueScheduleType;
    private ActionBarMenuItem doneButton;
    private CrossfadeDrawable doneButtonDrawable;
    public boolean enabled;
    public boolean exclude;
    private boolean hasHours;
    private UniversalRecyclerView listView;
    public boolean offline_only;
    private BusinessRecipientsHelper recipientsHelper;
    public int schedule;
    public int scheduleCustomEnd;
    public int scheduleCustomStart;
    private int shiftDp;
    private boolean valueSet;

    /* renamed from: org.telegram.ui.Business.AwayMessagesActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ AwayMessagesActivity this$0;

        public AnonymousClass1(AwayMessagesActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 == (-1)) goto L5;
            if (r3 != 1) goto L12;
            AwayMessagesActivity.m6312$$Nest$mprocessDone(this.this$0);
            return;
        L12:
            return;
        L5:
            if (this.this$0.onBackPressed(true) == false) goto L11;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: $r8$lambda$-l3U8NwtqC94M5kM7mnHEhP5SOs, reason: not valid java name */
    public static /* synthetic */ void m6307$r8$lambda$l3U8NwtqC94M5kM7mnHEhP5SOs(AwayMessagesActivity r0, View r1, boolean r2, int r3, int r4) {
        r0.lambda$onClick$6(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$5uHIhzjf2D_gShZnFXCiqAp57uE(AwayMessagesActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$processDone$2(r1, r2);
    }

    /* renamed from: $r8$lambda$I1HNdbJ-qEyXC-QG_ggqxORBrFg, reason: not valid java name */
    public static /* synthetic */ void m6308$r8$lambda$I1HNdbJqEyXCQG_ggqxORBrFg(AwayMessagesActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$processDone$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$LIghhMVTRc9m1E59uyCyJIFFYGk(AwayMessagesActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onBackPressed$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$N8b_66QvnXH7l_g8JBVzgM094wY(AwayMessagesActivity r0) {
        r0.lambda$createView$0();
    }

    /* renamed from: $r8$lambda$ZAK8RpH2tv9mwvm8RCZASDvE-7k, reason: not valid java name */
    public static /* synthetic */ void m6309$r8$lambda$ZAK8RpH2tv9mwvm8RCZASDvE7k(AwayMessagesActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onBackPressed$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$gD4BA1L8JuZjP2IpGYtRcYi_ktY(AwayMessagesActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClick(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$tzsmDAvbfqYPHJeQD_nOuwczf-k, reason: not valid java name */
    public static /* synthetic */ void m6310$r8$lambda$tzsmDAvbfqYPHJeQD_nOuwczfk(AwayMessagesActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    /* renamed from: $r8$lambda$yqwCTg-TG5AJpvUJG_h40ARkVGE, reason: not valid java name */
    public static /* synthetic */ void m6311$r8$lambda$yqwCTgTG5AJpvUJG_h40ARkVGE(AwayMessagesActivity r0, View r1, boolean r2, int r3, int r4) {
        r0.lambda$onClick$5(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$mprocessDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6312$$Nest$mprocessDone(AwayMessagesActivity r0) {
        r0.processDone();
    }

    public AwayMessagesActivity() {
        this.shiftDp = -4;
    }

    private void checkDone(boolean r5) {
        if (this.doneButton != null) goto L5;
        return;
    L5:
        boolean r0 = hasChanges();
        this.doneButton.setEnabled(r0);
        ActionBarMenuItem r1 = this.doneButton;
        float r2 = 0.0f;
        if (r5 == false) goto L20;
        ViewPropertyAnimator r4 = r1.animate();
        if (r0 == false) goto L10;
        float r52 = 1.0f;
    L11:
        ViewPropertyAnimator r42 = r4.alpha(r52);
        if (r0 == false) goto L14;
        float r53 = 1.0f;
    L15:
        ViewPropertyAnimator r43 = r42.scaleX(r53);
        if (r0 == false) goto L18;
        r2 = 1.0f;
    L18:
        r43.scaleY(r2).setDuration(180).start();
        return;
    L14:
        r53 = 0.0f;
        goto L15
    L10:
        r52 = 0.0f;
        goto L11
    L20:
        if (r0 == false) goto L22;
        float r54 = 1.0f;
    L23:
        r1.setAlpha(r54);
        ActionBarMenuItem r55 = this.doneButton;
        if (r0 == false) goto L26;
        float r12 = 1.0f;
    L27:
        r55.setScaleX(r12);
        ActionBarMenuItem r44 = this.doneButton;
        if (r0 == false) goto L30;
        r2 = 1.0f;
    L30:
        r44.setScaleY(r2);
        return;
    L26:
        r12 = 0.0f;
        goto L27
    L22:
        r54 = 0.0f;
        goto L23
    }

    private void fillItems(ArrayList<UItem> r7, UniversalAdapter r8) {
        r7.add(UItem.asTopView(LocaleController.getString(R.string.BusinessAway), LocaleController.getString(R.string.BusinessAwayInfo), "RestrictedEmoji", "💤"));
        r7.add(UItem.asCheck(1, LocaleController.getString(R.string.BusinessAwaySend)).setChecked(this.enabled));
        r7.add(UItem.asShadow(null));
        if (this.enabled == false) goto L27;
        QuickRepliesController.QuickReply r2 = QuickRepliesController.getInstance(this.currentAccount).findReply("away");
        if (r2 == null) goto L7;
        r7.add(UItem.asLargeQuickReply(r2));
    L8:
        r7.add(UItem.asShadow(null));
        r7.add(UItem.asHeader(LocaleController.getString(R.string.BusinessAwaySchedule)));
        UItem r22 = UItem.asRadio(3, LocaleController.getString(R.string.BusinessAwayScheduleAlways));
        boolean r5 = false;
        if (this.schedule != 0) goto L11;
        boolean r4 = true;
    L12:
        r7.add(r22.setChecked(r4));
        if (this.hasHours == false) goto L19;
        UItem r23 = UItem.asRadio(4, LocaleController.getString(R.string.BusinessAwayScheduleOutsideHours));
        if (this.schedule != 1) goto L17;
        boolean r42 = true;
    L18:
        r7.add(r23.setChecked(r42));
        goto L19
    L17:
        r42 = false;
    L19:
        UItem r24 = UItem.asRadio(5, LocaleController.getString(R.string.BusinessAwayScheduleCustom));
        if (this.schedule != 2) goto L22;
        r5 = true;
    L22:
        r7.add(r24.setChecked(r5));
        if (this.schedule != 2) goto L25;
        r7.add(UItem.asShadow(null));
        r7.add(UItem.asHeader(LocaleController.getString(R.string.BusinessAwaySchedule)));
        r7.add(UItem.asButton(8, LocaleController.getString(R.string.BusinessAwayScheduleCustomStart), LocaleController.formatShortDateTime(this.scheduleCustomStart)));
        r7.add(UItem.asButton(9, LocaleController.getString(R.string.BusinessAwayScheduleCustomEnd), LocaleController.formatShortDateTime(this.scheduleCustomEnd)));
    L25:
        r7.add(UItem.asShadow(null));
        r7.add(UItem.asCheck(10, LocaleController.getString(R.string.BusinessAwayOnlyOffline)).setChecked(this.offline_only));
        r7.add(UItem.asShadow(LocaleController.getString(R.string.BusinessAwayOnlyOfflineInfo)));
        r7.add(UItem.asHeader(LocaleController.getString(R.string.BusinessRecipients)));
        r7.add(UItem.asRadio(6, LocaleController.getString(R.string.BusinessChatsAllPrivateExcept2)).setChecked(this.exclude));
        r7.add(UItem.asRadio(7, LocaleController.getString(R.string.BusinessChatsOnlySelected2)).setChecked(true ^ this.exclude));
        r7.add(UItem.asShadow(null));
        this.recipientsHelper.fillItems(r7, r8);
        r7.add(UItem.asShadow(null));
        return;
    L11:
        r4 = false;
        goto L12
    L7:
        r7.add(UItem.asButton(2, R.drawable.msg2_chats_add, LocaleController.getString(R.string.BusinessAwayCreate)).accent());
        goto L8
    }

    private /* synthetic */ void lambda$createView$0() {
        this.listView.adapter.update(true);
        checkDone(true);
    }

    private /* synthetic */ void lambda$onBackPressed$3(AlertDialog r1, int r2) {
        processDone();
    }

    private /* synthetic */ void lambda$onBackPressed$4(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$onClick$5(View r1, boolean r2, int r3, int r4) {
        this.scheduleCustomStart = r3;
        ((TextCell) r1).setValue(LocaleController.formatShortDateTime(r3), true);
        checkDone(true);
    }

    private /* synthetic */ void lambda$onClick$6(View r1, boolean r2, int r3, int r4) {
        this.scheduleCustomEnd = r3;
        ((TextCell) r1).setValue(LocaleController.formatShortDateTime(r3), true);
        checkDone(true);
    }

    private /* synthetic */ void lambda$processDone$1(TLRPC.TL_error r2, TLObject r3) {
        if (r2 == null) goto L7;
        this.doneButtonDrawable.animateToProgress(0.0f);
        BulletinFactory.showError(r2);
        return;
    L7:
        if ((r3 instanceof TLRPC.TL_boolFalse) == false) goto L10;
        this.doneButtonDrawable.animateToProgress(0.0f);
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
        return;
    L10:
        finishFragment();
    }

    private /* synthetic */ void lambda$processDone$2(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new AwayMessagesActivity$$ExternalSyntheticLambda8(this, r3, r2));
    }

    private void onClick(UItem r7, View r8, int r9, float r10, float r11) {
        if (this.recipientsHelper.onClick(r7) == true) goto L48;
        int r92 = r7.id;
        if (r92 != 2) goto L8;
    L45:
        Bundle r72 = new Bundle();
        r72.putLong("user_id", getUserConfig().getClientUserId());
        r72.putInt("chatMode", 5);
        r72.putString("quick_reply", "away");
        presentFragment(new ChatActivity(r72));
        return;
    L8:
        if (r7.viewType == 17) goto L45;
        if (r92 != 1) goto L15;
        this.enabled = !this.enabled;
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L15:
        if (r92 != 6) goto L19;
        BusinessRecipientsHelper r82 = this.recipientsHelper;
        this.exclude = true;
        r82.setExclude(true);
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L19:
        if (r92 != 7) goto L23;
        BusinessRecipientsHelper r83 = this.recipientsHelper;
        this.exclude = false;
        r83.setExclude(false);
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L23:
        if (r92 != 3) goto L27;
        this.schedule = 0;
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L27:
        if (r92 != 4) goto L30;
        this.schedule = 1;
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L30:
        if (r92 != 5) goto L34;
        this.schedule = 2;
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L34:
        if (r92 != 8) goto L38;
        AlertsCreator.createDatePickerDialog(getContext(), LocaleController.getString(R.string.BusinessAwayScheduleCustomStartTitle), LocaleController.getString(R.string.BusinessAwayScheduleCustomSetButton), this.scheduleCustomStart, new AwayMessagesActivity$$ExternalSyntheticLambda6(this, r8));
        return;
    L38:
        if (r92 != 9) goto L42;
        AlertsCreator.createDatePickerDialog(getContext(), LocaleController.getString(R.string.BusinessAwayScheduleCustomEndTitle), LocaleController.getString(R.string.BusinessAwayScheduleCustomSetButton), this.scheduleCustomEnd, new AwayMessagesActivity$$ExternalSyntheticLambda7(this, r8));
        return;
    L42:
        if (r92 != 10) goto L47;
        boolean r93 = !this.offline_only;
        this.offline_only = r93;
        ((TextCheckCell) r8).setChecked(r93);
        checkDone(true);
        return;
    L47:
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
        QuickRepliesController.QuickReply r0 = QuickRepliesController.getInstance(this.currentAccount).findReply("away");
        boolean r1 = this.enabled;
        if (r1 == false) goto L14;
        if (r0 != null) goto L14;
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        View r02 = this.listView.findViewByItemId(2);
        int r12 = -this.shiftDp;
        this.shiftDp = r12;
        AndroidUtilities.shakeViewSpring(r02, r12);
        UniversalRecyclerView r6 = this.listView;
        r6.smoothScrollToPosition(r6.findPositionByItemId(2));
        return;
    L14:
        if (r1 == true) goto L16;
    L18:
        this.doneButtonDrawable.animateToProgress(1.0f);
        TLRPC.UserFull r13 = getMessagesController().getUserFull(getUserConfig().getClientUserId());
        TL_account.updateBusinessAwayMessage r3 = new TL_account.updateBusinessAwayMessage();
        if (this.enabled == false) goto L30;
        TL_account.TL_inputBusinessAwayMessage r4 = new TL_account.TL_inputBusinessAwayMessage();
        r3.message = r4;
        r4.offline_only = this.offline_only;
        r4.shortcut_id = r0.id;
        r4.recipients = this.recipientsHelper.getInputValue();
        int r42 = this.schedule;
        if (r42 != 0) goto L23;
        r3.message.schedule = new TL_account.TL_businessAwayMessageScheduleAlways();
    L27:
        r3.flags |= 1;
        if (r13 == null) goto L32;
        r13.flags2 |= 8;
        TL_account.TL_businessAwayMessage r2 = new TL_account.TL_businessAwayMessage();
        r13.business_away_message = r2;
        r2.offline_only = this.offline_only;
        r2.shortcut_id = r0.id;
        r2.recipients = this.recipientsHelper.getValue();
        r13.business_away_message.schedule = r3.message.schedule;
    L32:
        getConnectionsManager().sendRequest(r3, new AwayMessagesActivity$$ExternalSyntheticLambda5(this));
        getMessagesStorage().updateUserInfo(r13, false);
        return;
    L23:
        if (r42 != 1) goto L25;
        r3.message.schedule = new TL_account.TL_businessAwayMessageScheduleOutsideWorkHours();
        goto L27
    L25:
        if (r42 != 2) goto L27;
        TL_account.TL_businessAwayMessageScheduleCustom r22 = new TL_account.TL_businessAwayMessageScheduleCustom();
        r22.start_date = this.scheduleCustomStart;
        r22.end_date = this.scheduleCustomEnd;
        r3.message.schedule = r22;
        goto L27
    L30:
        if (r13 == null) goto L32;
        r13.flags2 &= -9;
        r13.business_away_message = null;
        goto L32
    L16:
        if (this.recipientsHelper.validate(this.listView) == true) goto L18;
    }

    private void setValue() {
        if (this.valueSet == false) goto L5;
        return;
    L5:
        TLRPC.UserFull r0 = getMessagesController().getUserFull(getUserConfig().getClientUserId());
        if (r0 != null) goto L9;
        getMessagesController().loadUserInfo(getUserConfig().getCurrentUser(), true, getClassGuid());
        return;
    L9:
        TL_account.TL_businessAwayMessage r2 = r0.business_away_message;
        this.currentValue = r2;
        if (r0.business_work_hours == null) goto L12;
        boolean r02 = true;
    L13:
        this.hasHours = r02;
        if (r2 == null) goto L16;
        boolean r03 = true;
    L17:
        this.enabled = r03;
        if (r2 == null) goto L20;
        boolean r04 = r2.recipients.exclude_selected;
    L21:
        this.exclude = r04;
        if (r2 == null) goto L24;
        boolean r05 = r2.offline_only;
    L25:
        this.offline_only = r05;
        BusinessRecipientsHelper r06 = this.recipientsHelper;
        if (r06 == null) goto L31;
        if (r2 != null) goto L29;
        TL_account.TL_businessRecipients r22 = null;
    L30:
        r06.setValue(r22);
        goto L31
    L29:
        r22 = r2.recipients;
    L31:
        TL_account.TL_businessAwayMessage r07 = this.currentValue;
        if (r07 == null) goto L36;
        TL_account.BusinessAwayMessageSchedule r08 = r07.schedule;
        if ((r08 instanceof TL_account.TL_businessAwayMessageScheduleCustom) == false) goto L36;
        this.currentValueScheduleType = 2;
        this.schedule = 2;
        int r23 = ((TL_account.TL_businessAwayMessageScheduleCustom) r08).start_date;
        this.currentScheduleCustomStart = r23;
        this.scheduleCustomStart = r23;
        int r09 = ((TL_account.TL_businessAwayMessageScheduleCustom) r08).end_date;
        this.currentScheduleCustomEnd = r09;
        this.scheduleCustomEnd = r09;
    L46:
        UniversalRecyclerView r010 = this.listView;
        if (r010 == null) goto L51;
        UniversalAdapter r011 = r010.adapter;
        if (r011 == null) goto L51;
        r011.update(true);
    L51:
        checkDone(true);
        this.valueSet = true;
        return;
    L36:
        this.scheduleCustomStart = getConnectionsManager().getCurrentTime();
        this.scheduleCustomEnd = getConnectionsManager().getCurrentTime() + 86400;
        TL_account.TL_businessAwayMessage r012 = this.currentValue;
        if (r012 != null) goto L39;
    L41:
        if (r012 != null) goto L43;
    L45:
        this.currentValueScheduleType = 0;
        this.schedule = 0;
        goto L46
    L43:
        if ((r012.schedule instanceof TL_account.TL_businessAwayMessageScheduleOutsideWorkHours) == false) goto L45;
        this.currentValueScheduleType = 1;
        this.schedule = 1;
        goto L46
    L39:
        if ((r012.schedule instanceof TL_account.TL_businessAwayMessageScheduleAlways) == false) goto L41;
        this.currentValueScheduleType = 0;
        this.schedule = 0;
        goto L46
    L24:
        r05 = true;
        goto L25
    L20:
        r04 = true;
        goto L21
    L16:
        r03 = false;
        goto L17
    L12:
        r02 = false;
        goto L13
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r7) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.BusinessAway));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        Drawable r0 = r7.getResources().getDrawable(R.drawable.ic_ab_done).mutate();
        int r3 = Theme.key_actionBarDefaultIcon;
        r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r3), PorterDuff.Mode.MULTIPLY));
        this.doneButtonDrawable = new CrossfadeDrawable(r0, new CircularProgressDrawable(Theme.getColor(r3)));
        this.doneButton = this.actionBar.createMenu().addItemWithWidth(1, this.doneButtonDrawable, AndroidUtilities.dp(56.0f), LocaleController.getString(R.string.Done));
        checkDone(false);
        FrameLayout r2 = new FrameLayout(r7);
        r2.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        BusinessRecipientsHelper r72 = new BusinessRecipientsHelper(this, new AwayMessagesActivity$$ExternalSyntheticLambda0(this));
        this.recipientsHelper = r72;
        r72.setExclude(this.exclude);
        BusinessRecipientsHelper r73 = this.recipientsHelper;
        if (r73 == null) goto L9;
        TL_account.TL_businessAwayMessage r4 = this.currentValue;
        if (r4 != null) goto L7;
        TL_account.TL_businessRecipients r42 = null;
    L8:
        r73.setValue(r42);
        goto L9
    L7:
        r42 = r4.recipients;
    L9:
        UniversalRecyclerView r74 = new UniversalRecyclerView(this, new AwayMessagesActivity$$ExternalSyntheticLambda1(this), new AwayMessagesActivity$$ExternalSyntheticLambda2(this), null);
        this.listView = r74;
        r74.setSections();
        this.listView.adapter.setApplyBackground(false);
        r2.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.actionBar.setAdaptiveBackground(this.listView, true);
        setValue();
        this.fragmentView = r2;
        return r2;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.quickRepliesUpdated) goto L12;
        UniversalRecyclerView r12 = this.listView;
        if (r12 == null) goto L9;
        UniversalAdapter r13 = r12.adapter;
        if (r13 == null) goto L9;
        r13.update(true);
    L9:
        checkDone(true);
        return;
    L12:
        if (r1 != NotificationCenter.userInfoDidLoad) goto L15;
        setValue();
        return;
    }

    public boolean hasChanges() {
        if (this.valueSet == true) goto L5;
        return false;
    L5:
        boolean r0 = this.enabled;
        TL_account.TL_businessAwayMessage r2 = this.currentValue;
        if (r2 == null) goto L8;
        boolean r4 = true;
    L9:
        if (r0 == r4) goto L11;
        return true;
    L11:
        if (r0 == false) goto L34;
        if (r2 == null) goto L34;
        if (r2.recipients.exclude_selected == this.exclude) goto L16;
        return true;
    L16:
        BusinessRecipientsHelper r02 = this.recipientsHelper;
        if (r02 != null) goto L19;
    L21:
        int r03 = this.currentValueScheduleType;
        int r22 = this.schedule;
        if (r03 == r22) goto L25;
        return true;
    L25:
        if (this.currentValue.offline_only == this.offline_only) goto L28;
        return true;
    L28:
        if (r22 != 2) goto L34;
        if (this.currentScheduleCustomStart == this.scheduleCustomStart) goto L32;
    L33:
        return true;
    L32:
        if (this.currentScheduleCustomEnd == this.scheduleCustomEnd) goto L34;
    L19:
        if (r02.hasChanges() == false) goto L21;
        return true;
    L34:
        return false;
    L8:
        r4 = false;
        goto L9
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r4) {
        if (hasChanges() == false) goto L13;
        if (r4 == true) goto L7;
    L11:
        return false;
    L7:
        if (this.enabled == true) goto L10;
        processDone();
        return false;
    L10:
        AlertDialog.Builder r42 = new AlertDialog.Builder(getParentActivity());
        r42.setTitle(LocaleController.getString(R.string.UnsavedChanges));
        r42.setMessage(LocaleController.getString(R.string.BusinessAwayUnsavedChanges));
        r42.setPositiveButton(LocaleController.getString(R.string.ApplyTheme), new AwayMessagesActivity$$ExternalSyntheticLambda3(this));
        r42.setNegativeButton(LocaleController.getString(R.string.PassportDiscard), new AwayMessagesActivity$$ExternalSyntheticLambda4(this));
        showDialog(r42.create());
        goto L11
    L13:
        return super.onBackPressed(r4);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.quickRepliesUpdated);
        getNotificationCenter().addObserver(this, NotificationCenter.userInfoDidLoad);
        QuickRepliesController.getInstance(this.currentAccount).load();
        setValue();
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        getNotificationCenter().removeObserver(this, NotificationCenter.quickRepliesUpdated);
        getNotificationCenter().removeObserver(this, NotificationCenter.userInfoDidLoad);
        super.onFragmentDestroy();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }
}
