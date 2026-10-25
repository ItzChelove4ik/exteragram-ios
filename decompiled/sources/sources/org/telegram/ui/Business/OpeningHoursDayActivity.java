package org.telegram.ui.Business;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import java.util.ArrayList;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Business.OpeningHoursActivity;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

/* loaded from: classes6.dex */
public class OpeningHoursDayActivity extends BaseFragment {
    public boolean enabled;
    private UniversalRecyclerView listView;
    private final int max;
    private final int maxPeriodsCount;
    private final int min;
    private final ArrayList<OpeningHoursActivity.Period> periods;
    private final CharSequence title;
    private Runnable whenApplied;
    public Runnable whenDone;

    /* renamed from: org.telegram.ui.Business.OpeningHoursDayActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ OpeningHoursDayActivity this$0;

        public AnonymousClass1(OpeningHoursDayActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: $r8$lambda$-wwo58OfLtXsbh5V9muLMXJJpk4, reason: not valid java name */
    public static /* synthetic */ void m6381$r8$lambda$wwo58OfLtXsbh5V9muLMXJJpk4(OpeningHoursDayActivity r0, View r1, OpeningHoursActivity.Period r2, Integer r3) {
        r0.lambda$onClick$1(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$397ontJ2WOVVk5Bz2gPK-GvOfRs, reason: not valid java name */
    public static /* synthetic */ void m6382$r8$lambda$397ontJ2WOVVk5Bz2gPKGvOfRs(OpeningHoursDayActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$I7rMQwmgbAYkETFMTa75CYEi69k(OpeningHoursDayActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    /* renamed from: $r8$lambda$KwY8X0APdXpTV4-xXRS4Nc6q9Kg, reason: not valid java name */
    public static /* synthetic */ void m6383$r8$lambda$KwY8X0APdXpTV4xXRS4Nc6q9Kg(OpeningHoursDayActivity r0, View r1, OpeningHoursActivity.Period r2, Integer r3) {
        r0.lambda$onClick$0(r1, r2, r3);
    }

    public OpeningHoursDayActivity(CharSequence r1, ArrayList<OpeningHoursActivity.Period> r2, int r3, int r4, int r5) {
        this.title = r1;
        this.periods = r2;
        this.min = r3;
        this.max = r4;
        this.maxPeriodsCount = r5;
        this.enabled = !r2.isEmpty();
    }

    private void fillItems(ArrayList<UItem> r6, UniversalAdapter r7) {
        r6.add(UItem.asRippleCheck(-1, LocaleController.getString(R.string.BusinessHoursDayOpen)).setChecked(this.enabled));
        r6.add(UItem.asShadow(null));
        if (this.enabled == false) goto L22;
        int r0 = 0;
    L6:
        if (r0 >= this.periods.size()) goto L15;
        if (r0 <= 0) goto L9;
        r6.add(UItem.asShadow(null));
    L9:
        OpeningHoursActivity.Period r1 = this.periods.get(r0);
        if (is24() == true) goto L13;
        int r2 = r0 * 3;
        r6.add(UItem.asButton(r2, LocaleController.getString(R.string.BusinessHoursDayOpenHour), OpeningHoursActivity.Period.timeToString(r1.start)));
        r6.add(UItem.asButton(r2 + 1, LocaleController.getString(R.string.BusinessHoursDayCloseHour), OpeningHoursActivity.Period.timeToString(r1.end)));
        r6.add(UItem.asButton(r2 + 2, LocaleController.getString(R.string.Remove)).red());
    L13:
        r0 = r0 + 1;
        goto L6
    L15:
        if (showAddButton() == false) goto L17;
        r6.add(UItem.asShadow(null));
        r6.add(UItem.asButton(-2, R.drawable.menu_premium_clock_add, LocaleController.getString(R.string.BusinessHoursDayAdd)).accent());
    L17:
        r6.add(UItem.asShadow(LocaleController.getString(R.string.BusinessHoursDayInfo)));
        return;
    }

    private boolean is24() {
        if (this.periods.size() == 1) goto L5;
    L9:
        return false;
    L5:
        if (this.periods.get(0).start != 0) goto L9;
        if (this.periods.get(0).end != 1439) goto L9;
        return true;
    }

    private /* synthetic */ void lambda$onClick$0(View r2, OpeningHoursActivity.Period r3, Integer r4) {
        boolean r0 = showAddButton();
        int r42 = r4.intValue();
        r3.start = r42;
        ((TextCell) r2).setValue(OpeningHoursActivity.Period.timeToString(r42), true);
        if (r0 == showAddButton()) goto L5;
        this.listView.adapter.update(true);
    L5:
        Runnable r1 = this.whenApplied;
        if (r1 == null) goto L9;
        r1.run();
        return;
    }

    private /* synthetic */ void lambda$onClick$1(View r2, OpeningHoursActivity.Period r3, Integer r4) {
        boolean r0 = showAddButton();
        int r42 = r4.intValue();
        r3.end = r42;
        ((TextCell) r2).setValue(OpeningHoursActivity.Period.timeToString(r42), true);
        if (r0 == showAddButton()) goto L5;
        this.listView.adapter.update(true);
    L5:
        Runnable r1 = this.whenApplied;
        if (r1 == null) goto L9;
        r1.run();
        return;
    }

    private void onClick(UItem r15, View r16, int r17, float r18, float r19) {
        int r3 = r15.id;
        if (r3 != (-1)) goto L16;
        this.enabled = !this.enabled;
        this.periods.clear();
        if (this.enabled == false) goto L7;
        this.periods.add(new OpeningHoursActivity.Period(0, 1439));
    L7:
        TextCheckCell r2 = (TextCheckCell) r16;
        boolean r32 = this.enabled;
        r15.checked = r32;
        r2.setChecked(r32);
        boolean r1 = this.enabled;
        if (r1 == false) goto L10;
        int r33 = Theme.key_windowBackgroundChecked;
    L11:
        r2.setBackgroundColorAnimated(r1, Theme.getColor(r33));
        this.listView.adapter.update(true);
        Runnable r0 = this.whenApplied;
        if (r0 == null) goto L71;
        r0.run();
        return;
    L71:
        return;
    L10:
        r33 = Theme.key_windowBackgroundUnchecked;
        goto L11
    L16:
        if (r3 != (-2)) goto L33;
        if (this.periods.isEmpty() == true) goto L24;
        if (is24() == true) goto L24;
        ArrayList<OpeningHoursActivity.Period> r12 = this.periods;
        int r13 = r12.get(r12.size() - 1).end;
        int r22 = Utilities.clamp(r13 + 30, this.max - 1, this.min);
        this.periods.add(new OpeningHoursActivity.Period(r22, Utilities.clamp((r13 + 1560) / 2, this.max, r22 + 1)));
    L27:
        Runnable r14 = this.whenApplied;
        if (r14 == null) goto L30;
        r14.run();
    L30:
        this.listView.adapter.update(true);
        return;
    L24:
        if (is24() == false) goto L26;
        this.periods.clear();
    L26:
        int r110 = Utilities.clamp(480, this.max - 1, this.min);
        this.periods.add(new OpeningHoursActivity.Period(r110, Utilities.clamp(1200, this.max, r110 + 1)));
        goto L27
    L33:
        if (r15.viewType != 3) goto L72;
        int r34 = r3 / 3;
        if (r34 >= 0) goto L37;
        return;
    L37:
        if (r34 >= this.periods.size()) goto L76;
        int r4 = r34 - 1;
        OpeningHoursActivity.Period r10 = null;
        if (r4 < 0) goto L42;
        OpeningHoursActivity.Period r42 = this.periods.get(r4);
    L43:
        OpeningHoursActivity.Period r11 = this.periods.get(r34);
        int r122 = r34 + 1;
        if (r122 >= this.periods.size()) goto L46;
        r10 = this.periods.get(r122);
    L46:
        int r111 = r15.id;
        if ((r111 % 3) != 0) goto L55;
        Context r112 = getContext();
        String r35 = LocaleController.getString(R.string.BusinessHoursDayOpenHourPicker);
        int r5 = r11.start;
        if (r42 != null) goto L51;
        int r43 = this.min;
    L52:
        AlertsCreator.createTimePickerDialog(r112, r35, r5, r43, r11.end - 1, new OpeningHoursDayActivity$$ExternalSyntheticLambda2(this, r16, r11));
        return;
    L51:
        r43 = r42.end + 1;
        goto L52
    L55:
        if ((r111 % 3) != 1) goto L63;
        Context r113 = getContext();
        String r36 = LocaleController.getString(R.string.BusinessHoursDayCloseHourPicker);
        int r44 = r11.end;
        int r52 = r11.start + 1;
        if (r10 != null) goto L59;
        int r6 = this.max;
    L60:
        AlertsCreator.createTimePickerDialog(r113, r36, r44, r52, r6, new OpeningHoursDayActivity$$ExternalSyntheticLambda3(this, r16, r11));
        return;
    L59:
        r6 = r10.start - 1;
        goto L60
    L63:
        if ((r111 % 3) != 2) goto L74;
        this.periods.remove(r34);
        if (this.periods.isEmpty() == false) goto L67;
        this.periods.add(new OpeningHoursActivity.Period(0, 1439));
    L67:
        this.listView.adapter.update(true);
        Runnable r02 = this.whenApplied;
        if (r02 == null) goto L75;
        r02.run();
        return;
    L75:
        return;
    L74:
        return;
    L42:
        r42 = null;
        goto L43
    L76:
        return;
    }

    private boolean showAddButton() {
        if (this.periods.size() < this.maxPeriodsCount) goto L6;
        return false;
    L6:
        if (this.periods.isEmpty() == false) goto L8;
    L13:
        return true;
    L8:
        if (is24() == true) goto L13;
        ArrayList<OpeningHoursActivity.Period> r0 = this.periods;
        if (r0.get(r0.size() - 1).end < Math.min(1438, this.max - 2)) goto L13;
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r5) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(this.title);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        FrameLayout r0 = new FrameLayout(r5);
        r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        UniversalRecyclerView r52 = new UniversalRecyclerView(this, new OpeningHoursDayActivity$$ExternalSyntheticLambda0(this), new OpeningHoursDayActivity$$ExternalSyntheticLambda1(this), null);
        this.listView = r52;
        r52.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        r0.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.fragmentView = r0;
        return r0;
    }

    public OpeningHoursDayActivity onApplied(Runnable r1) {
        this.whenApplied = r1;
        return this;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyHidden() {
        Runnable r0 = this.whenDone;
        if (r0 == null) goto L5;
        r0.run();
    L5:
        super.onBecomeFullyHidden();
    }

    public OpeningHoursDayActivity onDone(Runnable r1) {
        this.whenDone = r1;
        return this;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        if (this.enabled == false) goto L5;
        return;
    L5:
        if (this.periods.isEmpty() == true) goto L11;
        this.periods.clear();
        Runnable r1 = this.whenApplied;
        if (r1 == null) goto L12;
        r1.run();
        return;
    L12:
        return;
    }
}
