package org.telegram.ui;

import android.content.Context;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.RadioCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SectionsScrollView;
import org.telegram.ui.Components.StickerImageView;

/* loaded from: classes6.dex */
public class AutoDeleteMessagesActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    RadioCellInternal afterOneDay;
    RadioCellInternal afterOneMonth;
    RadioCellInternal afterOneWeek;
    ArrayList<RadioCellInternal> arrayList;
    LinearLayout checkBoxContainer;
    RadioCellInternal customTimeButton;
    RadioCellInternal offCell;
    public int startFromTtl;

    /* renamed from: org.telegram.ui.AutoDeleteMessagesActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ AutoDeleteMessagesActivity this$0;

        public AnonymousClass1(AutoDeleteMessagesActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.AutoDeleteMessagesActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ AutoDeleteMessagesActivity this$0;

        /* renamed from: $r8$lambda$NWIq4xld3hYCCf2-yHgAAh8snpI, reason: not valid java name */
        public static /* synthetic */ void m6243$r8$lambda$NWIq4xld3hYCCf2yHgAAh8snpI(AnonymousClass2 r0, ArrayList r1, int r2) {
            r0.lambda$run$1(r1, r2);
        }

        /* renamed from: $r8$lambda$wnVLw-sxHE6ZksjdDMQIC0JM_zk, reason: not valid java name */
        public static /* synthetic */ void m6244$r8$lambda$wnVLwsxHE6ZksjdDMQIC0JM_zk(AnonymousClass2 r0, ArrayList r1) {
            r0.lambda$run$0(r1);
        }

        public AnonymousClass2(AutoDeleteMessagesActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$run$0(ArrayList r6) {
            if (r6.isEmpty() == true) goto L15;
            int r0 = 0;
        L5:
            int r1 = r6.size();
            AutoDeleteMessagesActivity r2 = this.this$0;
            if (r0 >= r1) goto L8;
            r2.getMessagesController().setDialogHistoryTTL(((Long) r6.get(r0)).longValue(), AutoDeleteMessagesActivity.m6241$$Nest$mgetSelectedTime(this.this$0) * 60);
            r0 = r0 + 1;
            goto L5
        L8:
            int r02 = AutoDeleteMessagesActivity.m6241$$Nest$mgetSelectedTime(r2);
            AutoDeleteMessagesActivity r12 = this.this$0;
            if (r02 <= 0) goto L12;
            BulletinFactory.of(r12).createSimpleBulletin(R.raw.fire_on, AndroidUtilities.replaceTags(LocaleController.formatString("AutodeleteTimerEnabledForChats", R.string.AutodeleteTimerEnabledForChats, new Object[]{LocaleController.formatTTLString(AutoDeleteMessagesActivity.m6241$$Nest$mgetSelectedTime(this.this$0) * 60), LocaleController.formatPluralString("Chats", r6.size(), new Object[]{Integer.valueOf(r6.size())})}))).show();
            return;
        L12:
            BulletinFactory.of(r12).createSimpleBulletin(R.raw.fire_off, LocaleController.formatString("AutodeleteTimerDisabledForChats", R.string.AutodeleteTimerDisabledForChats, new Object[]{LocaleController.formatPluralString("Chats", r6.size(), new Object[]{Integer.valueOf(r6.size())})})).show();
            return;
        }

        private /* synthetic */ void lambda$run$1(ArrayList r1, int r2) {
            AndroidUtilities.runOnUIThread(new AutoDeleteMessagesActivity$2$$ExternalSyntheticLambda1(this, r1), 100);
        }

        @Override // java.lang.Runnable
        public void run() {
            UsersSelectActivity r0 = new UsersSelectActivity(1);
            r0.setTtlPeriod(AutoDeleteMessagesActivity.m6241$$Nest$mgetSelectedTime(this.this$0));
            r0.setDelegate(new AutoDeleteMessagesActivity$2$$ExternalSyntheticLambda0(this));
            this.this$0.presentFragment(r0);
        }
    }

    /* renamed from: org.telegram.ui.AutoDeleteMessagesActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements AlertsCreator.ScheduleDatePickerDelegate {
        final /* synthetic */ AutoDeleteMessagesActivity this$0;

        /* renamed from: $r8$lambda$1Ttc-NkSJTQXuQPaAO2mbwFe_sc, reason: not valid java name */
        public static /* synthetic */ void m6245$r8$lambda$1TtcNkSJTQXuQPaAO2mbwFe_sc(AnonymousClass3 r0, int r1) {
            r0.lambda$didSelectDate$0(r1);
        }

        public AnonymousClass3(AutoDeleteMessagesActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$didSelectDate$0(int r2) {
            AutoDeleteMessagesActivity.m6242$$Nest$mselectDate(this.this$0, r2, true);
        }

        @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
        public void didSelectDate(boolean r1, int r2, int r3) {
            AndroidUtilities.runOnUIThread(new AutoDeleteMessagesActivity$3$$ExternalSyntheticLambda0(this, r2), 50);
        }
    }

    /* renamed from: org.telegram.ui.AutoDeleteMessagesActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements RequestDelegate {
        final /* synthetic */ AutoDeleteMessagesActivity this$0;

        public AnonymousClass4(AutoDeleteMessagesActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.tgnet.RequestDelegate
        public void run(TLObject r1, TLRPC.TL_error r2) {
        }
    }

    public class RadioCellInternal extends RadioCell {
        boolean custom;
        final /* synthetic */ AutoDeleteMessagesActivity this$0;
        int time;

        public RadioCellInternal(AutoDeleteMessagesActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }
    }

    public static /* synthetic */ void $r8$lambda$E3PtHVOg7FDJ3x66kcpf5jQO414(AutoDeleteMessagesActivity r0, View r1, AlertDialog r2, int r3) {
        r0.lambda$updateItems$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$JDbWicMk7D2pmGeQEFW0p3R65yA(AutoDeleteMessagesActivity r0, View r1) {
        r0.lambda$updateItems$2(r1);
    }

    /* renamed from: $r8$lambda$Yebu48CeoMh-NozhGx1bP_4hpCc, reason: not valid java name */
    public static /* synthetic */ void m6240$r8$lambda$Yebu48CeoMhNozhGx1bP_4hpCc(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    /* renamed from: -$$Nest$mgetSelectedTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6241$$Nest$mgetSelectedTime(AutoDeleteMessagesActivity r0) {
        return r0.getSelectedTime();
    }

    /* renamed from: -$$Nest$mselectDate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6242$$Nest$mselectDate(AutoDeleteMessagesActivity r0, int r1, boolean r2) {
        r0.selectDate(r1, r2);
    }

    public AutoDeleteMessagesActivity() {
        this.arrayList = new ArrayList();
        this.startFromTtl = 0;
    }

    private int getSelectedTime() {
        int r0 = 0;
    L4:
        if (r0 >= this.arrayList.size()) goto L11;
        if (this.arrayList.get(r0).isChecked() == true) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return this.arrayList.get(r0).time;
    L11:
        return this.startFromTtl;
    }

    private /* synthetic */ void lambda$updateItems$1(View r1, AlertDialog r2, int r3) {
        r2.dismiss();
        selectRadioButton(r1, true);
    }

    private /* synthetic */ void lambda$updateItems$2(View r5) {
        if (r5 != this.customTimeButton) goto L6;
        AlertsCreator.createAutoDeleteDatePickerDialog(getContext(), 1, null, new AnonymousClass3(this));
        return;
    L6:
        int r0 = ((RadioCellInternal) r5).time;
        if (getSelectedTime() != 0) goto L11;
        if (r0 <= 0) goto L11;
        AlertDialog.Builder r1 = new AlertDialog.Builder(getContext());
        r1.setTitle(LocaleController.getString(R.string.MessageLifetime));
        r1.setMessage(LocaleController.formatString("AutoDeleteConfirmMessage", R.string.AutoDeleteConfirmMessage, new Object[]{LocaleController.formatTTLString(r0 * 60)}));
        r1.setNegativeButton(LocaleController.getString(R.string.Cancel), new AutoDeleteMessagesActivity$$ExternalSyntheticLambda1());
        r1.setPositiveButton(LocaleController.getString(R.string.Enable), new AutoDeleteMessagesActivity$$ExternalSyntheticLambda2(this, r5));
        r1.show();
        return;
    L11:
        selectRadioButton(r5, true);
    }

    private void selectDate(int r9, boolean r10) {
        TransitionSet r0 = new TransitionSet();
        ChangeBounds r1 = new ChangeBounds();
        r1.setDuration(150);
        Fade r4 = new Fade(1);
        r4.setDuration(150);
        r0.addTransition(new Fade(2).setDuration(150)).addTransition(r1).addTransition(r4);
        r0.setOrdering(0);
        r0.setInterpolator(CubicBezierInterpolator.DEFAULT);
        TransitionManager.beginDelayedTransition(this.checkBoxContainer, r0);
        int r02 = 0;
    L4:
        if (r02 >= this.arrayList.size()) goto L10;
        if (this.arrayList.get(r02).time == r9) goto L7;
        r02 = r02 + 1;
        goto L4
    L7:
        selectRadioButton(this.arrayList.get(r02), r10);
        return;
    L10:
        int r03 = 0;
    L11:
        int r2 = this.arrayList.size();
        ArrayList<RadioCellInternal> r3 = this.arrayList;
        if (r03 >= r2) goto L17;
        if (r3.get(r03).custom == false) goto L16;
        this.checkBoxContainer.removeView(this.arrayList.get(r03));
        this.arrayList.remove(r03);
        r03 = r03 - 1;
    L16:
        r03 = r03 + 1;
        goto L11
    L17:
        int r04 = r3.size();
        int r22 = 0;
    L19:
        if (r22 >= this.arrayList.size()) goto L24;
        if (r9 < this.arrayList.get(r22).time) goto L22;
        r22 = r22 + 1;
        goto L19
    L22:
        r04 = r22 + 1;
    L24:
        RadioCellInternal r23 = new RadioCellInternal(this, getContext());
        r23.custom = true;
        r23.time = r9;
        r23.setText(LocaleController.formatString("AutoDeleteAfterShort", R.string.AutoDeleteAfterShort, new Object[]{LocaleController.formatTTLString(r9 * 60)}), false, true);
        this.arrayList.add(r04, r23);
        this.checkBoxContainer.addView(r23, r04);
        updateItems();
        selectRadioButton(r23, r10);
    }

    private void selectRadioButton(View r6, boolean r7) {
        int r1 = 0;
    L4:
        if (r1 >= this.arrayList.size()) goto L10;
        RadioCellInternal r2 = this.arrayList.get(r1);
        ArrayList<RadioCellInternal> r3 = this.arrayList;
        if (r2 != r6) goto L8;
        r3.get(r1).setChecked(true, this.fragmentBeginToShow);
    L9:
        r1 = r1 + 1;
        goto L4
    L8:
        r3.get(r1).setChecked(false, this.fragmentBeginToShow);
        goto L9
    L10:
        if (r7 == false) goto L18;
        int r62 = ((RadioCellInternal) r6).time;
        if (r62 <= 0) goto L19;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.fire_on, AndroidUtilities.replaceTags(LocaleController.formatString("AutoDeleteGlobalTimerEnabled", R.string.AutoDeleteGlobalTimerEnabled, new Object[]{LocaleController.formatTTLString(r62 * 60)}))).show();
        return;
    L19:
        return;
    }

    private void updateItems() {
        int r0 = 0;
    L4:
        if (r0 >= this.arrayList.size()) goto L6;
        this.arrayList.get(r0).setBackground(Theme.createSelectorWithBackgroundDrawable(Theme.getColor(Theme.key_windowBackgroundWhite), Theme.getColor(Theme.key_listSelector)));
        this.arrayList.get(r0).setOnClickListener(new AutoDeleteMessagesActivity$$ExternalSyntheticLambda0(this));
        r0 = r0 + 1;
        goto L4
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r8) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.AutoDeleteMessages));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        FrameLayout r0 = new FrameLayout(r8);
        this.fragmentView = r0;
        r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        SectionsScrollView.SectionsLinearLayout r2 = new SectionsScrollView.SectionsLinearLayout(getContext());
        SectionsScrollView r3 = new SectionsScrollView(getContext(), r2, this.resourceProvider);
        r2.setOrientation(1);
        r3.addView(r2);
        r0.addView(r3);
        this.actionBar.setAdaptiveBackground(r3);
        FrameLayout r02 = new FrameLayout(r8);
        StickerImageView r32 = new StickerImageView(r8, this.currentAccount);
        r32.setStickerNum(10);
        r02.addView(r32, LayoutHelper.createFrame(130, 130, 17));
        r02.setTag(-33024);
        r2.addView(r02, LayoutHelper.createLinear(-1, 170));
        LinearLayout r03 = new LinearLayout(getContext());
        this.checkBoxContainer = r03;
        r03.setOrientation(1);
        this.checkBoxContainer.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        r2.addView(this.checkBoxContainer, LayoutHelper.createLinear(-1, -2));
        HeaderCell r04 = new HeaderCell(getContext());
        r04.setText(LocaleController.getString(R.string.MessageLifetime));
        this.checkBoxContainer.addView(r04);
        RadioCellInternal r05 = new RadioCellInternal(this, getContext());
        this.offCell = r05;
        r05.setText(LocaleController.getString(R.string.ShortMessageLifetimeForever), false, true);
        RadioCellInternal r06 = this.offCell;
        r06.time = 0;
        this.checkBoxContainer.addView(r06);
        RadioCellInternal r07 = new RadioCellInternal(this, getContext());
        this.afterOneDay = r07;
        r07.setText(LocaleController.getString(R.string.AutoDeleteAfter1Day), false, true);
        RadioCellInternal r08 = this.afterOneDay;
        r08.time = 1440;
        this.checkBoxContainer.addView(r08);
        RadioCellInternal r09 = new RadioCellInternal(this, getContext());
        this.afterOneWeek = r09;
        r09.setText(LocaleController.getString(R.string.AutoDeleteAfter1Week), false, true);
        RadioCellInternal r010 = this.afterOneWeek;
        r010.time = 10080;
        this.checkBoxContainer.addView(r010);
        RadioCellInternal r011 = new RadioCellInternal(this, getContext());
        this.afterOneMonth = r011;
        r011.setText(LocaleController.getString(R.string.AutoDeleteAfter1Month), false, true);
        RadioCellInternal r012 = this.afterOneMonth;
        r012.time = 44640;
        this.checkBoxContainer.addView(r012);
        RadioCellInternal r013 = new RadioCellInternal(this, getContext());
        this.customTimeButton = r013;
        r013.setText(LocaleController.getString(R.string.SetCustomTime), false, false);
        this.customTimeButton.hideRadioButton();
        this.checkBoxContainer.addView(this.customTimeButton);
        this.arrayList.add(this.offCell);
        this.arrayList.add(this.afterOneDay);
        this.arrayList.add(this.afterOneWeek);
        this.arrayList.add(this.afterOneMonth);
        this.arrayList.add(this.customTimeButton);
        updateItems();
        TextInfoPrivacyCell r014 = new TextInfoPrivacyCell(r8, 12, this.resourceProvider);
        r014.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.GlobalAutoDeleteInfo), new AnonymousClass2(this)));
        r2.addView(r014, LayoutHelper.createLinear(-1, -2));
        selectDate(this.startFromTtl, false);
        return this.fragmentView;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        int r0 = getUserConfig().getGlobalTTl();
        this.startFromTtl = r0;
        if (r0 >= 0) goto L5;
        this.startFromTtl = 0;
    L5:
        getUserConfig().loadGlobalTTl();
        getNotificationCenter().addObserver(this, NotificationCenter.didUpdateGlobalAutoDeleteTimer);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        getNotificationCenter().removeObserver(this, NotificationCenter.didUpdateGlobalAutoDeleteTimer);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        int r1 = 0;
    L4:
        if (r1 >= this.arrayList.size()) goto L15;
        if (this.arrayList.get(r1).isChecked() == true) goto L8;
        r1 = r1 + 1;
        goto L4
    L8:
        if (this.arrayList.get(r1).time == this.startFromTtl) goto L12;
        this.startFromTtl = this.arrayList.get(r1).time;
        TLRPC.TL_messages_setDefaultHistoryTTL r2 = new TLRPC.TL_messages_setDefaultHistoryTTL();
        r2.period = this.arrayList.get(r1).time * 60;
        getConnectionsManager().sendRequest(r2, new AnonymousClass4(this));
        getUserConfig().setGlobalTtl(this.startFromTtl);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.didUpdateGlobalAutoDeleteTimer, new Object[0]);
        return;
    L12:
        return;
    }
}
