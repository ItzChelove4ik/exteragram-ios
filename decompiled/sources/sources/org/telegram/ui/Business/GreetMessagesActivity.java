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
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CrossfadeDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

/* loaded from: classes6.dex */
public class GreetMessagesActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    public TL_account.TL_businessGreetingMessage currentValue;
    private final int[] daysOfInactivity;
    private final String[] daysOfInactivityTexts;
    private ActionBarMenuItem doneButton;
    private CrossfadeDrawable doneButtonDrawable;
    public boolean enabled;
    public boolean exclude;
    public int inactivityDays;
    private UniversalRecyclerView listView;
    private BusinessRecipientsHelper recipientsHelper;
    private int shiftDp;
    private boolean valueSet;

    /* renamed from: org.telegram.ui.Business.GreetMessagesActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ GreetMessagesActivity this$0;

        public AnonymousClass1(GreetMessagesActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 == (-1)) goto L5;
            if (r3 != 1) goto L12;
            GreetMessagesActivity.m6366$$Nest$mprocessDone(this.this$0);
            return;
        L12:
            return;
        L5:
            if (this.this$0.onBackPressed(true) == false) goto L11;
            this.this$0.finishFragment();
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$5_6P1u7hFljiUUikYYokZg2ZNJQ(GreetMessagesActivity r0, int r1) {
        r0.chooseInactivity(r1);
    }

    public static /* synthetic */ void $r8$lambda$CKKrIsGkrvEN0RZkQD6j6dArzxk(GreetMessagesActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$processDone$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$JWcMWEv8qDqOSBKGneLgWfwQitk(GreetMessagesActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onBackPressed$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$UbWEd40NYtmr5_wv_xPO0DX8BLc(GreetMessagesActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Uge91d2HkvhkyP8YSnGLUq7xTf4(GreetMessagesActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$processDone$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$dVbyDNbJtfHlJGR5u1L8E4KBMts(GreetMessagesActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$nfunCUZ_he6noVbS2Tj1tWWQEqc(GreetMessagesActivity r0) {
        r0.lambda$createView$0();
    }

    public static /* synthetic */ void $r8$lambda$pDyx93ey04hv7WPeyUTCL18Co5k(GreetMessagesActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onBackPressed$3(r1, r2);
    }

    /* renamed from: -$$Nest$mprocessDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6366$$Nest$mprocessDone(GreetMessagesActivity r0) {
        r0.processDone();
    }

    public GreetMessagesActivity() {
        int[] r0 = {7, 14, 21, 28};
        this.daysOfInactivity = r0;
        this.shiftDp = -4;
        this.inactivityDays = 7;
        this.daysOfInactivityTexts = new String[r0.length];
        int r1 = 0;
    L3:
        int[] r2 = this.daysOfInactivity;
        if (r1 >= r2.length) goto L6;
        this.daysOfInactivityTexts[r1] = LocaleController.formatPluralString("DaysSchedule", r2[r1], new Object[0]);
        r1 = r1 + 1;
        goto L3
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

    private void chooseInactivity(int r2) {
        this.inactivityDays = this.daysOfInactivity[r2];
        checkDone(true);
    }

    private void fillItems(ArrayList<UItem> r6, UniversalAdapter r7) {
        r6.add(UItem.asTopView(LocaleController.getString(R.string.BusinessGreet), LocaleController.getString(R.string.BusinessGreetInfo), "RestrictedEmoji", "👋"));
        r6.add(UItem.asCheck(1, LocaleController.getString(R.string.BusinessGreetSend)).setChecked(this.enabled));
        r6.add(UItem.asShadow(null));
        if (this.enabled == false) goto L20;
        QuickRepliesController.QuickReply r2 = QuickRepliesController.getInstance(this.currentAccount).findReply("hello");
        if (r2 == null) goto L7;
        r6.add(UItem.asLargeQuickReply(r2));
    L8:
        r6.add(UItem.asShadow(null));
        r6.add(UItem.asHeader(LocaleController.getString(R.string.BusinessRecipients)));
        r6.add(UItem.asRadio(3, LocaleController.getString(R.string.BusinessChatsAllPrivateExcept2)).setChecked(this.exclude));
        r6.add(UItem.asRadio(4, LocaleController.getString(R.string.BusinessChatsOnlySelected2)).setChecked(true ^ this.exclude));
        r6.add(UItem.asShadow(null));
        this.recipientsHelper.fillItems(r6, r7);
        r6.add(UItem.asShadow(LocaleController.getString(R.string.BusinessGreetRecipientsInfo)));
        r6.add(UItem.asHeader(LocaleController.getString(R.string.BusinessGreetPeriod)));
        int r72 = 0;
    L9:
        int[] r0 = this.daysOfInactivity;
        if (r72 >= r0.length) goto L15;
        if (r0[r72] == this.inactivityDays) goto L16;
        r72 = r72 + 1;
    L16:
        r6.add(UItem.asSlideView(this.daysOfInactivityTexts, r72, new GreetMessagesActivity$$ExternalSyntheticLambda6(this)));
        r6.add(UItem.asShadow(LocaleController.getString(R.string.BusinessGreetPeriodInfo)));
        return;
    L15:
        r72 = -1;
        goto L16
    L7:
        r6.add(UItem.asButton(2, R.drawable.msg2_chats_add, LocaleController.getString(R.string.BusinessGreetCreate)).accent());
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
        AndroidUtilities.runOnUIThread(new GreetMessagesActivity$$ExternalSyntheticLambda7(this, r3, r2));
    }

    private void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        if (this.recipientsHelper.onClick(r1) == true) goto L25;
        int r22 = r1.id;
        if (r22 != 2) goto L8;
    L22:
        Bundle r12 = new Bundle();
        r12.putLong("user_id", getUserConfig().getClientUserId());
        r12.putInt("chatMode", 5);
        r12.putString("quick_reply", "hello");
        presentFragment(new ChatActivity(r12));
        return;
    L8:
        if (r1.viewType == 17) goto L22;
        if (r22 != 1) goto L15;
        this.enabled = !this.enabled;
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L15:
        if (r22 != 3) goto L19;
        BusinessRecipientsHelper r23 = this.recipientsHelper;
        this.exclude = true;
        r23.setExclude(true);
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L19:
        if (r22 != 4) goto L24;
        BusinessRecipientsHelper r24 = this.recipientsHelper;
        this.exclude = false;
        r24.setExclude(false);
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L24:
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
        QuickRepliesController.QuickReply r0 = QuickRepliesController.getInstance(this.currentAccount).findReply("hello");
        boolean r1 = this.enabled;
        if (r1 == false) goto L14;
        if (r0 != null) goto L14;
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        View r02 = this.listView.findViewByItemId(2);
        int r12 = -this.shiftDp;
        this.shiftDp = r12;
        AndroidUtilities.shakeViewSpring(r02, r12);
        return;
    L14:
        if (r1 == true) goto L16;
    L18:
        this.doneButtonDrawable.animateToProgress(1.0f);
        TLRPC.UserFull r13 = getMessagesController().getUserFull(getUserConfig().getClientUserId());
        TL_account.updateBusinessGreetingMessage r2 = new TL_account.updateBusinessGreetingMessage();
        if (this.enabled == false) goto L23;
        TL_account.TL_inputBusinessGreetingMessage r3 = new TL_account.TL_inputBusinessGreetingMessage();
        r2.message = r3;
        r3.shortcut_id = r0.id;
        r3.recipients = this.recipientsHelper.getInputValue();
        r2.message.no_activity_days = this.inactivityDays;
        r2.flags |= 1;
        if (r13 == null) goto L25;
        r13.flags2 |= 4;
        TL_account.TL_businessGreetingMessage r32 = new TL_account.TL_businessGreetingMessage();
        r13.business_greeting_message = r32;
        r32.shortcut_id = r0.id;
        r32.recipients = this.recipientsHelper.getValue();
        r13.business_greeting_message.no_activity_days = this.inactivityDays;
    L25:
        getConnectionsManager().sendRequest(r2, new GreetMessagesActivity$$ExternalSyntheticLambda5(this));
        getMessagesStorage().updateUserInfo(r13, false);
        return;
    L23:
        if (r13 == null) goto L25;
        r13.flags2 &= -5;
        r13.business_greeting_message = null;
        goto L25
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
        TL_account.TL_businessGreetingMessage r02 = r0.business_greeting_message;
        this.currentValue = r02;
        if (r02 == null) goto L12;
        boolean r2 = true;
    L13:
        this.enabled = r2;
        if (r02 == null) goto L16;
        int r22 = r02.no_activity_days;
    L17:
        this.inactivityDays = r22;
        if (r02 == null) goto L20;
        boolean r23 = r02.recipients.exclude_selected;
    L21:
        this.exclude = r23;
        BusinessRecipientsHelper r24 = this.recipientsHelper;
        if (r24 == null) goto L27;
        if (r02 != null) goto L25;
        TL_account.TL_businessRecipients r03 = null;
    L26:
        r24.setValue(r03);
        goto L27
    L25:
        r03 = r02.recipients;
    L27:
        UniversalRecyclerView r04 = this.listView;
        if (r04 == null) goto L32;
        UniversalAdapter r05 = r04.adapter;
        if (r05 == null) goto L32;
        r05.update(true);
    L32:
        checkDone(true);
        this.valueSet = true;
        return;
    L20:
        r23 = true;
        goto L21
    L16:
        r22 = 7;
        goto L17
    L12:
        r2 = false;
        goto L13
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r7) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.BusinessGreet));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        Drawable r0 = r7.getResources().getDrawable(R.drawable.ic_ab_done).mutate();
        int r3 = Theme.key_actionBarDefaultIcon;
        r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r3), PorterDuff.Mode.MULTIPLY));
        this.doneButtonDrawable = new CrossfadeDrawable(r0, new CircularProgressDrawable(Theme.getColor(r3)));
        this.doneButton = this.actionBar.createMenu().addItemWithWidth(1, this.doneButtonDrawable, AndroidUtilities.dp(56.0f), LocaleController.getString(R.string.Done));
        checkDone(false);
        FrameLayout r2 = new FrameLayout(r7);
        r2.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        BusinessRecipientsHelper r72 = new BusinessRecipientsHelper(this, new GreetMessagesActivity$$ExternalSyntheticLambda2(this));
        this.recipientsHelper = r72;
        r72.doNotExcludeNewChats();
        BusinessRecipientsHelper r73 = this.recipientsHelper;
        TL_account.TL_businessGreetingMessage r32 = this.currentValue;
        if (r32 != null) goto L5;
        TL_account.TL_businessRecipients r33 = null;
    L6:
        r73.setValue(r33);
        UniversalRecyclerView r74 = new UniversalRecyclerView(this, new GreetMessagesActivity$$ExternalSyntheticLambda3(this), new GreetMessagesActivity$$ExternalSyntheticLambda4(this), null);
        this.listView = r74;
        r74.setSections();
        this.listView.adapter.setApplyBackground(false);
        r2.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.actionBar.setAdaptiveBackground(this.listView, true);
        setValue();
        this.fragmentView = r2;
        return r2;
    L5:
        r33 = r32.recipients;
        goto L6
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
        TL_account.TL_businessGreetingMessage r2 = this.currentValue;
        if (r2 == null) goto L8;
        boolean r4 = true;
    L9:
        if (r0 == r4) goto L11;
        return true;
    L11:
        if (r0 == false) goto L24;
        if (r2 == null) goto L24;
        if (r2.no_activity_days == this.inactivityDays) goto L17;
        return true;
    L17:
        if (r2.recipients.exclude_selected == this.exclude) goto L19;
        return true;
    L19:
        BusinessRecipientsHelper r5 = this.recipientsHelper;
        if (r5 == null) goto L24;
        if (r5.hasChanges() == false) goto L24;
        return true;
    L24:
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
        r42.setMessage(LocaleController.getString(R.string.BusinessGreetUnsavedChanges));
        r42.setPositiveButton(LocaleController.getString(R.string.ApplyTheme), new GreetMessagesActivity$$ExternalSyntheticLambda0(this));
        r42.setNegativeButton(LocaleController.getString(R.string.PassportDiscard), new GreetMessagesActivity$$ExternalSyntheticLambda1(this));
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
