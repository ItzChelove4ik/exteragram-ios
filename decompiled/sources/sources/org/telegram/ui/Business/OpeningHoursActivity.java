package org.telegram.ui.Business;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import j$.time.DayOfWeek;
import j$.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.NotificationsCheckCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CrossfadeDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

/* loaded from: classes6.dex */
public class OpeningHoursActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    public String currentTimezoneId;
    public ArrayList<Period>[] currentValue;
    private ActionBarMenuItem doneButton;
    private CrossfadeDrawable doneButtonDrawable;
    public boolean enabled;
    private UniversalRecyclerView listView;
    public String timezoneId;
    public ArrayList<Period>[] value;
    private boolean valueSet;

    /* renamed from: org.telegram.ui.Business.OpeningHoursActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ OpeningHoursActivity this$0;

        public AnonymousClass1(OpeningHoursActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 == (-1)) goto L5;
            if (r3 != 1) goto L12;
            OpeningHoursActivity.m6380$$Nest$mprocessDone(this.this$0);
            return;
        L12:
            return;
        L5:
            if (this.this$0.onBackPressed(true) == false) goto L11;
            this.this$0.finishFragment();
            return;
        }
    }

    public static class Period {
        public int end;
        public int start;

        public Period(int r1, int r2) {
            this.start = r1;
            this.end = r2;
        }

        public static String timeToString(int r1) {
            return timeToString(r1, true);
        }

        public static String timeToString(int r6, boolean r7) {
            int r5 = r6 % 60;
            int r4 = ((r6 - r5) / 60) % 24;
            Calendar r0 = Calendar.getInstance();
            r0.set(0, 0, 0, r4, r5);
            String r02 = LocaleController.getInstance().getFormatterConstDay().format(r0.getTime());
            if (r6 <= 1440) goto L7;
            if (r7 == false) goto L7;
            return LocaleController.formatString(R.string.BusinessHoursNextDay, new Object[]{r02});
        L7:
            return r02;
        }

        public String toString() {
            return timeToString(this.start) + " - " + timeToString(this.end);
        }
    }

    public static /* synthetic */ void $r8$lambda$4ftUeJbHw6mWpTNJl7XPlTPdAns(OpeningHoursActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$7CW3GK17Tbi1Ft75l55lBS_h438(OpeningHoursActivity r0) {
        r0.lambda$onClick$4();
    }

    public static /* synthetic */ void $r8$lambda$CEWHGocDCPql8XYcHLoiIvD0gTI(OpeningHoursActivity r0, UItem r1) {
        r0.lambda$onClick$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$QjRuC4A8Wmwh2rlcj23LRN4xQi0(OpeningHoursActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    /* renamed from: $r8$lambda$e8LPlc3wp-Ocbipb8BSgQa5RZ4c, reason: not valid java name */
    public static /* synthetic */ int m6378$r8$lambda$e8LPlc3wpOcbipb8BSgQa5RZ4c(TL_account.TL_businessWeeklyOpen r0, TL_account.TL_businessWeeklyOpen r1) {
        return r0.start_minute - r1.start_minute;
    }

    public static /* synthetic */ void $r8$lambda$enWeDqjj4KWYcgpoJDuFnBBYGDc(OpeningHoursActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$processDone$1(r1, r2);
    }

    /* renamed from: $r8$lambda$fsrLZPaZVz_B9ep3PlErIN4M-Fo, reason: not valid java name */
    public static /* synthetic */ void m6379$r8$lambda$fsrLZPaZVz_B9ep3PlErIN4MFo(OpeningHoursActivity r0, View r1, String r2) {
        r0.lambda$onClick$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$kq9Oq72s_VXk4fgrople8WVlS9g(OpeningHoursActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$processDone$2(r1, r2);
    }

    /* renamed from: -$$Nest$mprocessDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6380$$Nest$mprocessDone(OpeningHoursActivity r0) {
        r0.processDone();
    }

    public OpeningHoursActivity() {
        this.currentValue = null;
        this.value = new ArrayList[]{new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList()};
    }

    private void adaptPrevDay(int r5) {
        Period r1 = null;
        if (this.value[r5].isEmpty() == false) goto L5;
        Period r0 = null;
    L6:
        if (r0 == null) goto L26;
        int r52 = (r5 + 6) % 7;
        if (this.value[r52].isEmpty() == true) goto L12;
        ArrayList<Period> r02 = this.value[r52];
        r1 = r02.get(r02.size() - 1);
    L12:
        if (r1 != null) goto L14;
        return;
    L14:
        if (r1.end <= 1439) goto L25;
        r1.end = 1439;
        if (r1.start < 1439) goto L18;
        this.value[r52].remove(r1);
    L18:
        View r03 = this.listView.findViewByItemId(r52);
        if ((r03 instanceof NotificationsCheckCell) == false) goto L22;
        ((NotificationsCheckCell) r03).setValue(getPeriodsValue(this.value[r52]));
        return;
    L22:
        this.listView.adapter.update(true);
        return;
    L25:
        return;
    L26:
        return;
    L5:
        ArrayList<Period> r04 = this.value[r5];
        r0 = r04.get(r04.size() - 1);
        goto L6
    }

    public static ArrayList<TL_account.TL_businessWeeklyOpen> adaptWeeklyOpen(ArrayList<TL_account.TL_businessWeeklyOpen> r9, int r10) {
        ArrayList r0 = new ArrayList(r9);
        ArrayList<TL_account.TL_businessWeeklyOpen> r92 = new ArrayList(r0.size());
        int r2 = 0;
    L4:
        if (r2 >= r0.size()) goto L24;
        TL_account.TL_businessWeeklyOpen r3 = (TL_account.TL_businessWeeklyOpen) r0.get(r2);
        TL_account.TL_businessWeeklyOpen r4 = new TL_account.TL_businessWeeklyOpen();
        if (r10 == 0) goto L14;
        int r5 = r3.start_minute;
        int r6 = r5 % 1440;
        int r7 = r3.end_minute;
        int r8 = (r7 - r5) + r6;
        if (r6 != 0) goto L14;
        if (r8 != 1440) goto L12;
    L13:
        r4.start_minute = r5;
        r4.end_minute = r7;
        r92.add(r4);
    L23:
        r2 = r2 + 1;
        goto L4
    L12:
        if (r8 == 1439) goto L13;
    L14:
        r4.start_minute = r3.start_minute + r10;
        r4.end_minute = r3.end_minute + r10;
        r92.add(r4);
        int r52 = r4.start_minute;
        int r62 = r4.end_minute;
        if (r52 >= 0) goto L19;
        if (r62 >= 0) goto L18;
        r4.start_minute = r52 + 10080;
        r4.end_minute = r62 + 10080;
        goto L23
    L18:
        r4.start_minute = 0;
        TL_account.TL_businessWeeklyOpen r42 = new TL_account.TL_businessWeeklyOpen();
        r42.start_minute = (r3.start_minute + 10080) + r10;
        r42.end_minute = 10079;
        r92.add(r42);
        goto L23
    L19:
        if (r62 <= 10080) goto L23;
        if (r52 <= 10080) goto L22;
        r4.start_minute = r52 - 10080;
        r4.end_minute = r62 - 10080;
        goto L23
    L22:
        r4.end_minute = 10079;
        TL_account.TL_businessWeeklyOpen r43 = new TL_account.TL_businessWeeklyOpen();
        r43.start_minute = 0;
        r43.end_minute = (r3.end_minute + r10) - 10079;
        r92.add(r43);
        goto L23
    L24:
        Collections.sort(r92, new OpeningHoursActivity$$ExternalSyntheticLambda0());
        return r92;
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
        r7.add(UItem.asTopView(LocaleController.getString(R.string.BusinessHours), LocaleController.getString(R.string.BusinessHoursInfo), R.raw.biz_clock));
        r7.add(UItem.asCheck(-1, LocaleController.getString(R.string.BusinessHoursShow)).setChecked(this.enabled));
        r7.add(UItem.asShadow(-100, null));
        if (this.enabled == false) goto L16;
        r7.add(UItem.asHeader(LocaleController.getString(R.string.BusinessHours)));
        int r1 = 0;
    L5:
        ArrayList<Period>[] r2 = this.value;
        if (r1 >= r2.length) goto L11;
        if (r2[r1] != null) goto L10;
        r2[r1] = new ArrayList();
    L10:
        String r22 = DayOfWeek.values()[r1].getDisplayName(TextStyle.FULL, LocaleController.getInstance().getCurrentLocale());
        r7.add(UItem.asButtonCheck(r1, r22.substring(0, 1).toUpperCase() + r22.substring(1), getPeriodsValue(this.value[r1])).setChecked(!this.value[r1].isEmpty()));
        r1 = r1 + 1;
        goto L5
    L11:
        r7.add(UItem.asShadow(-101, null));
        r7.add(UItem.asButton(-2, LocaleController.getString(R.string.BusinessHoursTimezone), TimezonesController.getInstance(this.currentAccount).getTimezoneName(this.timezoneId, false)));
        r7.add(UItem.asShadow(-102, null));
        return;
    }

    public static ArrayList<TL_account.TL_businessWeeklyOpen> fromDaysHours(ArrayList<Period>[] r8) {
        ArrayList<TL_account.TL_businessWeeklyOpen> r0 = new ArrayList();
        if (r8 == null) goto L14;
        int r2 = 0;
    L6:
        if (r2 >= r8.length) goto L14;
        if (r8[r2] == null) goto L13;
        int r3 = 0;
    L11:
        if (r3 >= r8[r2].size()) goto L13;
        Period r4 = r8[r2].get(r3);
        TL_account.TL_businessWeeklyOpen r5 = new TL_account.TL_businessWeeklyOpen();
        int r6 = r2 * 1440;
        r5.start_minute = r4.start + r6;
        r5.end_minute = r6 + r4.end;
        r0.add(r5);
        r3 = r3 + 1;
    L13:
        r2 = r2 + 1;
    L14:
        return r0;
    }

    public static ArrayList<Period>[] getDaysHours(ArrayList<TL_account.TL_businessWeeklyOpen> r12) {
        ArrayList<Period>[] r1 = new ArrayList[7];
        int r3 = 0;
    L3:
        if (r3 >= 7) goto L5;
        r1[r3] = new ArrayList();
        r3 = r3 + 1;
        goto L3
    L5:
        int r32 = 0;
    L7:
        if (r32 >= r12.size()) goto L9;
        TL_account.TL_businessWeeklyOpen r4 = r12.get(r32);
        int r5 = r4.start_minute;
        int r7 = r5 % 1440;
        r1[(r5 / 1440) % 7].add(new Period(r7, (r4.end_minute - r5) + r7));
        r32 = r32 + 1;
        goto L7
    L9:
        int r33 = 0;
    L10:
        if (r33 >= 7) goto L45;
        int r52 = r33 + 1;
        int r6 = r52 * 1440;
        int r72 = 0;
        int r8 = r33 * 1440;
    L13:
        if (r72 >= r12.size()) goto L21;
        TL_account.TL_businessWeeklyOpen r9 = r12.get(r72);
        if (r9.start_minute > r8) goto L19;
        int r92 = r9.end_minute;
        if (r92 < r8) goto L19;
        r8 = r92 + 1;
    L19:
        r72 = r72 + 1;
        goto L13
    L21:
        if (r8 < r6) goto L35;
        int r62 = (r33 + 6) % 7;
        if (r1[r62].isEmpty() == false) goto L25;
    L27:
        int r42 = Math.min((r8 - r4) - 1, 2879);
        ArrayList<Period> r63 = r1[(r33 + 8) % 7];
        if (r42 >= 1440) goto L30;
    L34:
        r1[r33].clear();
        r1[r33].add(new Period(0, r42));
    L44:
        r33 = r52;
        goto L10
    L30:
        if (r63.isEmpty() == true) goto L34;
        if (r63.get(0).start >= (r42 - 1440)) goto L34;
        r42 = r63.get(0).start + 1439;
        goto L34
    L25:
        if (r1[r62].get(r10.size() - 1).end < 1440) goto L27;
        r1[r62].get(r6.size() - 1).end = 1439;
        goto L27
    L35:
        int r43 = r52 % 7;
        if (r1[r33].isEmpty() == true) goto L44;
        if (r1[r43].isEmpty() == true) goto L44;
        Period r34 = r1[r33].get(r3.size() - 1);
        Period r44 = r1[r43].get(0);
        int r64 = r34.end;
        if (r64 <= 1440) goto L44;
        if ((r64 - 1439) != r44.start) goto L44;
        r34.end = 1439;
        r44.start = 0;
        goto L44
    L45:
        return r1;
    }

    private String getPeriodsValue(ArrayList<Period> r4) {
        if (r4.isEmpty() == false) goto L7;
        return LocaleController.getString(R.string.BusinessHoursDayClosed);
    L7:
        if (isFull(r4) == true) goto L9;
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        int r0 = 0;
    L12:
        if (r0 >= r4.size()) goto L17;
        Period r1 = r4.get(r0);
        if (r0 <= 0) goto L16;
        r3 = r3.concat("\n");
    L16:
        r3 = r3 + Period.timeToString(r1.start) + " - " + Period.timeToString(r1.end);
        r0 = r0 + 1;
        goto L12
    L17:
        return r3;
    L9:
        return LocaleController.getString(R.string.BusinessHoursDayFullOpened);
    }

    public static boolean is24x7(TL_account.TL_businessWorkHours r5) {
        if (r5 != null) goto L5;
    L18:
        return false;
    L5:
        if (r5.weekly_open.isEmpty() == true) goto L18;
        int r1 = 0;
        int r2 = 0;
    L9:
        if (r1 >= r5.weekly_open.size()) goto L15;
        TL_account.TL_businessWeeklyOpen r3 = r5.weekly_open.get(r1);
        if (r3.start_minute > (r2 + 1)) goto L12;
        r2 = r3.end_minute;
        r1 = r1 + 1;
        goto L9
    L12:
        return false;
    L15:
        if (r2 < 10079) goto L18;
        return true;
    }

    public static boolean isFull(ArrayList<Period> r5) {
        if (r5 != null) goto L5;
    L22:
        return false;
    L5:
        if (r5.isEmpty() == true) goto L22;
        int r1 = 0;
        int r2 = 0;
    L9:
        if (r1 >= r5.size()) goto L15;
        Period r3 = r5.get(r1);
        if (r2 < r3.start) goto L12;
        r2 = r3.end;
        r1 = r1 + 1;
        goto L9
    L12:
        return false;
    L15:
        if (r2 != 1439) goto L17;
        return true;
    L17:
        if (r2 == 1440) goto L25;
        return false;
    L25:
        return true;
    }

    private /* synthetic */ void lambda$onClick$3(View r3, String r4) {
        TimezonesController r0 = TimezonesController.getInstance(this.currentAccount);
        this.timezoneId = r4;
        ((TextCell) r3).setValue(r0.getTimezoneName(r4, false), true);
        checkDone(true);
    }

    private /* synthetic */ void lambda$onClick$4() {
        this.listView.adapter.update(true);
        checkDone(true);
    }

    private /* synthetic */ void lambda$onClick$5(UItem r1) {
        adaptPrevDay(r1.id);
    }

    private /* synthetic */ void lambda$processDone$1(TLRPC.TL_error r2, TLObject r3) {
        if (r2 == null) goto L7;
        this.doneButtonDrawable.animateToProgress(0.0f);
        BulletinFactory.showError(r2);
        return;
    L7:
        if ((r3 instanceof TLRPC.TL_boolFalse) == false) goto L14;
        if (getContext() == null) goto L21;
        this.doneButtonDrawable.animateToProgress(0.0f);
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
        return;
    L21:
        return;
    L14:
        if (this.isFinished == false) goto L16;
        return;
    L16:
        if (this.finishing == true) goto L20;
        finishFragment();
        return;
    }

    private /* synthetic */ void lambda$processDone$2(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new OpeningHoursActivity$$ExternalSyntheticLambda7(this, r3, r2));
    }

    private int maxPeriodsFor(int r4) {
        int r42 = 0;
        int r0 = 0;
    L4:
        if (r42 >= 7) goto L11;
        ArrayList<Period> r1 = this.value[r42];
        if (r1 == null) goto L9;
        r0 = r0 + Math.max(1, r1.size());
    L9:
        r42 = r42 + 1;
        goto L4
    L11:
        return 28 - r0;
    }

    private void onClick(UItem r10, View r11, int r12, float r13, float r14) {
        int r122 = r10.id;
        if (r122 != (-1)) goto L7;
        boolean r102 = !this.enabled;
        this.enabled = r102;
        ((TextCheckCell) r11).setChecked(r102);
        this.listView.adapter.update(true);
        checkDone(true);
        return;
    L7:
        if (r122 != (-2)) goto L11;
        presentFragment(new TimezoneSelector().setValue(this.timezoneId).whenSelected(new OpeningHoursActivity$$ExternalSyntheticLambda4(this, r11)));
        return;
    L11:
        if (r10.viewType != 5) goto L50;
        if (r122 >= 0) goto L14;
        return;
    L14:
        if (r122 >= this.value.length) goto L52;
        int r2 = 0;
        if (LocaleController.isRTL == false) goto L21;
        if (r13 <= AndroidUtilities.dp(76.0f)) goto L23;
    L28:
        int r112 = (r10.id + 6) % 7;
        int r123 = 0;
        int r132 = 0;
    L30:
        if (r123 >= this.value[r112].size()) goto L35;
        if (this.value[r112].get(r123).end <= r132) goto L34;
        r132 = this.value[r112].get(r123).end;
    L34:
        r123 = r123 + 1;
        goto L30
    L35:
        int r6 = Math.max(0, r132 - 1439);
        int r113 = (r10.id + 1) % 7;
        int r124 = 1440;
    L37:
        if (r2 >= this.value[r113].size()) goto L42;
        if (this.value[r113].get(r2).start >= r124) goto L41;
        r124 = this.value[r113].get(r2).start;
    L41:
        r2 = r2 + 1;
        goto L37
    L42:
        int r7 = r124 + 1439;
        CharSequence r4 = r10.text;
        ArrayList<Period>[] r114 = this.value;
        int r125 = r10.id;
        presentFragment(new OpeningHoursDayActivity(r4, r114[r125], r6, r7, maxPeriodsFor(r125)).onApplied(new OpeningHoursActivity$$ExternalSyntheticLambda5(this)).onDone(new OpeningHoursActivity$$ExternalSyntheticLambda6(this, r10)));
        return;
    L23:
        if (this.value[r10.id].isEmpty() == false) goto L25;
        ((NotificationsCheckCell) r11).setChecked(true);
        this.value[r10.id].add(new Period(0, 1439));
        adaptPrevDay(r10.id);
    L26:
        ((NotificationsCheckCell) r11).setValue(getPeriodsValue(this.value[r10.id]));
        checkDone(true);
        return;
    L25:
        this.value[r10.id].clear();
        ((NotificationsCheckCell) r11).setChecked(false);
        goto L26
    L21:
        if (r13 < (r11.getMeasuredWidth() - AndroidUtilities.dp(76.0f))) goto L28;
    L52:
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
        TLRPC.UserFull r0 = getMessagesController().getUserFull(getUserConfig().getClientUserId());
        TL_account.updateBusinessWorkHours r1 = new TL_account.updateBusinessWorkHours();
        ArrayList<TL_account.TL_businessWeeklyOpen> r2 = fromDaysHours(this.value);
        if (this.enabled == true) goto L12;
    L16:
        if (r0 == null) goto L18;
        r0.flags2 &= -2;
        r0.business_work_hours = null;
    L18:
        getConnectionsManager().sendRequest(r1, new OpeningHoursActivity$$ExternalSyntheticLambda3(this));
        getMessagesStorage().updateUserInfo(r0, false);
        return;
    L12:
        if (r2.isEmpty() == true) goto L16;
        TL_account.TL_businessWorkHours r3 = new TL_account.TL_businessWorkHours();
        r3.timezone_id = this.timezoneId;
        r3.weekly_open.addAll(r2);
        r1.flags |= 1;
        r1.business_work_hours = r3;
        if (r0 == null) goto L18;
        r0.flags2 |= 1;
        r0.business_work_hours = r3;
        goto L18
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
        TL_account.TL_businessWorkHours r2 = r0.business_work_hours;
        if (r2 == null) goto L12;
        boolean r4 = true;
    L13:
        this.enabled = r4;
        if (r4 == false) goto L16;
        String r42 = r2.timezone_id;
        this.timezoneId = r42;
        this.currentTimezoneId = r42;
        this.currentValue = getDaysHours(r2.weekly_open);
        this.value = getDaysHours(r0.business_work_hours.weekly_open);
    L25:
        UniversalRecyclerView r02 = this.listView;
        if (r02 == null) goto L30;
        UniversalAdapter r03 = r02.adapter;
        if (r03 == null) goto L30;
        r03.update(true);
    L30:
        checkDone(false);
        this.valueSet = true;
        return;
    L16:
        String r04 = TimezonesController.getInstance(this.currentAccount).getSystemTimezoneId();
        this.timezoneId = r04;
        this.currentTimezoneId = r04;
        this.currentValue = null;
        this.value = new ArrayList[7];
        int r05 = 0;
    L17:
        ArrayList<Period>[] r22 = this.value;
        if (r05 >= r22.length) goto L25;
        r22[r05] = new ArrayList();
        if (r05 < 0) goto L24;
        if (r05 >= 5) goto L24;
        this.value[r05].add(new Period(0, 1439));
    L24:
        r05 = r05 + 1;
        goto L17
    L12:
        r4 = false;
        goto L13
    }

    public static String toString(int r9, TLRPC.User r10, TL_account.TL_businessWorkHours r11) {
        if (r11 != null) goto L5;
        return null;
    L5:
        ArrayList<Period>[] r0 = getDaysHours(r11.weekly_open);
        StringBuilder r1 = new StringBuilder();
        if (r10 == null) goto L8;
        r1.append(LocaleController.formatString(R.string.BusinessHoursCopyHeader, new Object[]{UserObject.getUserName(r10)}));
        r1.append("\n");
    L8:
        int r102 = 0;
        int r3 = 0;
    L10:
        if (r3 >= r0.length) goto L24;
        ArrayList<Period> r4 = r0[r3];
        String r6 = DayOfWeek.values()[r3].getDisplayName(TextStyle.FULL, LocaleController.getInstance().getCurrentLocale());
        r1.append(r6.substring(0, 1).toUpperCase() + r6.substring(1));
        r1.append(": ");
        if (isFull(r4) == false) goto L15;
        r1.append(LocaleController.getString(R.string.BusinessHoursProfileOpen));
    L23:
        r1.append("\n");
        r3 = r3 + 1;
        goto L10
    L15:
        if (r4.isEmpty() == false) goto L17;
        r1.append(LocaleController.getString(R.string.BusinessHoursProfileClose));
        goto L23
    L17:
        int r5 = 0;
    L19:
        if (r5 >= r4.size()) goto L23;
        if (r5 <= 0) goto L22;
        r1.append(", ");
    L22:
        Period r62 = r4.get(r5);
        r1.append(Period.timeToString(r62.start));
        r1.append(" - ");
        r1.append(Period.timeToString(r62.end));
        r5 = r5 + 1;
        goto L19
    L24:
        TLRPC.TL_timezone r112 = TimezonesController.getInstance(r9).findTimezone(r11.timezone_id);
        int r02 = Calendar.getInstance().getTimeZone().getRawOffset() / 1000;
        if (r112 == null) goto L29;
        r102 = r112.utc_offset;
    L29:
        if (((r02 - r102) / 60) == 0) goto L33;
        if (r112 == null) goto L33;
        r1.append(LocaleController.formatString(R.string.BusinessHoursCopyFooter, new Object[]{TimezonesController.getInstance(r9).getTimezoneName(r112, true)}));
    L33:
        return r1.toString();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r7) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.BusinessHours));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        Drawable r0 = r7.getResources().getDrawable(R.drawable.ic_ab_done).mutate();
        int r3 = Theme.key_actionBarDefaultIcon;
        r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r3), PorterDuff.Mode.MULTIPLY));
        this.doneButtonDrawable = new CrossfadeDrawable(r0, new CircularProgressDrawable(Theme.getColor(r3)));
        this.doneButton = this.actionBar.createMenu().addItemWithWidth(1, this.doneButtonDrawable, AndroidUtilities.dp(56.0f), LocaleController.getString(R.string.Done));
        checkDone(false);
        FrameLayout r2 = new FrameLayout(r7);
        r2.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        UniversalRecyclerView r72 = new UniversalRecyclerView(this, new OpeningHoursActivity$$ExternalSyntheticLambda1(this), new OpeningHoursActivity$$ExternalSyntheticLambda2(this), null);
        this.listView = r72;
        r72.setSections();
        this.listView.adapter.setApplyBackground(false);
        r2.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.actionBar.setAdaptiveBackground(this.listView, true);
        setValue();
        this.fragmentView = r2;
        return r2;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.userInfoDidLoad) goto L7;
        setValue();
        return;
    L7:
        if (r1 == NotificationCenter.timezonesUpdated) goto L9;
        return;
    L9:
        if (this.currentValue != null) goto L11;
        this.timezoneId = TimezonesController.getInstance(this.currentAccount).getSystemTimezoneId();
    L11:
        UniversalRecyclerView r0 = this.listView;
        if (r0 == null) goto L18;
        UniversalAdapter r02 = r0.adapter;
        if (r02 == null) goto L19;
        r02.update(true);
        return;
    L19:
        return;
    }

    public boolean hasChanges() {
        if (this.currentValue == null) goto L5;
        boolean r0 = true;
    L7:
        if (r0 == this.enabled) goto L10;
        return true;
    L10:
        if (TextUtils.equals(this.currentTimezoneId, this.timezoneId) == true) goto L13;
        return true;
    L13:
        if (this.currentValue != null) goto L15;
    L36:
        return false;
    L15:
        if (this.enabled == false) goto L36;
        if (this.value != null) goto L19;
        return true;
    L19:
        int r02 = 0;
    L20:
        ArrayList<Period>[] r3 = this.currentValue;
        if (r02 >= r3.length) goto L36;
        if (r3[r02].size() != this.value[r02].size()) goto L24;
        int r32 = 0;
    L27:
        if (r32 >= this.value[r02].size()) goto L35;
        Period r4 = this.currentValue[r02].get(r32);
        Period r5 = this.value[r02].get(r32);
        if (r4.start != r5.start) goto L34;
        if (r4.end != r5.end) goto L34;
        r32 = r32 + 1;
    L34:
        return true;
    L35:
        r02 = r02 + 1;
        goto L20
    L24:
        return true;
    L5:
        r0 = false;
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        TimezonesController.getInstance(this.currentAccount).load();
        this.timezoneId = TimezonesController.getInstance(this.currentAccount).getSystemTimezoneId();
        getNotificationCenter().addObserver(this, NotificationCenter.userInfoDidLoad);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        getNotificationCenter().removeObserver(this, NotificationCenter.userInfoDidLoad);
        super.onFragmentDestroy();
        processDone();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }
}
