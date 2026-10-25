package com.exteragram.messenger.preferences.appearance.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.TabCounterMode;
import com.exteragram.messenger.preferences.components.CustomPreferenceCell;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.FilterTabsView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public class FilterTabsPreviewCell extends FrameLayout implements CustomPreferenceCell, NotificationCenter.NotificationCenterDelegate {
    private final int COUNTER_SEED;
    private final FilterTabsView filterTabsView;
    private final Map<Integer, Integer> idsWithCounters;

    /* renamed from: com.exteragram.messenger.preferences.appearance.components.FilterTabsPreviewCell$1, reason: invalid class name */
    public class AnonymousClass1 implements FilterTabsView.FilterTabsViewDelegate {
        final /* synthetic */ FilterTabsPreviewCell this$0;

        /* renamed from: $r8$lambda$LUTilU3rkOEUjmtXHG-1chqCQuQ, reason: not valid java name */
        public static /* synthetic */ Integer m1671$r8$lambda$LUTilU3rkOEUjmtXHG1chqCQuQ(Integer r0) {
            return 0;
        }

        public AnonymousClass1(FilterTabsPreviewCell r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.FilterTabsView.FilterTabsViewDelegate
        public boolean canPerformActions() {
            return false;
        }

        @Override // org.telegram.ui.Components.FilterTabsView.FilterTabsViewDelegate
        public boolean didSelectTab(FilterTabsView.TabView r1, boolean r2) {
            return false;
        }

        @Override // org.telegram.ui.Components.FilterTabsView.FilterTabsViewDelegate
        public int getTabCounter(int r3) {
            if (ExteraConfig.getTabCounterMode() != TabCounterMode.HIDDEN) goto L7;
            return 0;
        L7:
            return ((Integer) FilterTabsPreviewCell.m1670$$Nest$fgetidsWithCounters(this.this$0).computeIfAbsent(Integer.valueOf(r3), new FilterTabsPreviewCell$1$$ExternalSyntheticLambda0())).intValue();
        }

        @Override // org.telegram.ui.Components.FilterTabsView.FilterTabsViewDelegate
        public boolean isTabMenuVisible() {
            return false;
        }

        @Override // org.telegram.ui.Components.FilterTabsView.FilterTabsViewDelegate
        public void onDeletePressed(int r1) {
        }

        @Override // org.telegram.ui.Components.FilterTabsView.FilterTabsViewDelegate
        public void onPageReorder(int r1, int r2) {
        }

        @Override // org.telegram.ui.Components.FilterTabsView.FilterTabsViewDelegate
        public void onPageScrolled(float r1) {
        }

        @Override // org.telegram.ui.Components.FilterTabsView.FilterTabsViewDelegate
        public void onPageSelected(FilterTabsView.Tab r1, boolean r2) {
        }

        @Override // org.telegram.ui.Components.FilterTabsView.FilterTabsViewDelegate
        public void onSamePageSelected() {
        }
    }

    /* renamed from: -$$Nest$fgetidsWithCounters, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Map m1670$$Nest$fgetidsWithCounters(FilterTabsPreviewCell r0) {
        return r0.idsWithCounters;
    }

    public FilterTabsPreviewCell(Context r12) {
        super(r12);
        this.idsWithCounters = new HashMap();
        this.COUNTER_SEED = Utilities.random.nextInt(40) + 20;
        setWillNotDraw(false);
        BlurredBackgroundSourceColor r1 = new BlurredBackgroundSourceColor();
        r1.setColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        BlurredBackgroundDrawableViewFactory r2 = new BlurredBackgroundDrawableViewFactory(r1);
        FilterTabsView r3 = new FilterTabsView(r12, null);
        this.filterTabsView = r3;
        r3.setStaticAllChats(true);
        r3.setPadding(0, AndroidUtilities.dp(7.0f), 0, AndroidUtilities.dp(7.0f));
        BlurredBackgroundDrawable r122 = r2.create(r3, BlurredBackgroundProviderImpl.topPanel(null));
        r122.setRadius(AndroidUtilities.dp(18.0f));
        r122.setPadding(AndroidUtilities.dp(6.666f));
        r3.setBlurredBackground(r122);
        r3.setColors(Theme.key_actionBarTabLine, Theme.key_actionBarTabActiveText, Theme.key_actionBarTabUnactiveText, Theme.key_actionBarTabSelector, Theme.key_actionBarDefault);
        r3.setDelegate(new AnonymousClass1(this));
        addView(r3, LayoutHelper.createFrame(-1, 50.0f, 17, 12.0f, 0.0f, 12.0f, 0.0f));
        updateTabs(false);
    }

    private void updateTabs(boolean r27) {
        this.filterTabsView.resetTabId();
        this.filterTabsView.removeTabs();
        this.idsWithCounters.clear();
        ArrayList<MessagesController.DialogFilter> r1 = MessagesController.getInstance(UserConfig.selectedAccount).getDialogFilters();
        int r4 = 0;
        int r5 = 0;
        int r6 = -1;
    L4:
        if (r4 >= r1.size()) goto L25;
        MessagesController.DialogFilter r7 = r1.get(r4);
        if (r7.isDefault() == true) goto L8;
    L10:
        if (r4 == 0) goto L13;
        if (r4 == 1) goto L13;
        if (r4 == 3) goto L13;
    L14:
        if (r6 != (-1)) goto L16;
        r6 = r7.id;
    L16:
        boolean r8 = r7.isDefault();
        FilterTabsView r9 = this.filterTabsView;
        if (r8 == false) goto L19;
        r9.addTab(r7.id, 0, LocaleController.getString(R.string.FilterAllChats), "💬", null, false, true, r7.locked);
    L23:
        r5 = r5 + 1;
    L24:
        r4 = r4 + 1;
        goto L4
    L19:
        int r10 = r7.id;
        int r11 = r7.localId;
        String r12 = r7.name;
        String r82 = r7.emoticon;
        if (r82 != null) goto L22;
        r82 = "📁";
    L22:
        r9.addTab(r10, r11, r12, r82, r7.entities, r7.title_noanimate, false, r7.locked);
    L13:
        this.idsWithCounters.put(Integer.valueOf(r7.id), Integer.valueOf(this.COUNTER_SEED / (r4 + 1)));
        goto L14
    L8:
        if (ExteraConfig.getHideAllChats() == false) goto L10;
    L25:
        int r42 = 6;
        if (r5 >= 6) goto L40;
        int[] r72 = {100, 101, 102, 103};
        String[] r102 = {LocaleController.getString(R.string.FilterContacts), LocaleController.getString(R.string.FilterGroups), LocaleController.getString(R.string.FilterChannels), LocaleController.getString(R.string.FilterBots)};
        String[] r112 = {"👤", "👥", "📢", "🤖"};
        int r122 = 0;
    L29:
        if (r122 >= 4) goto L40;
        if (r5 >= r42) goto L40;
        int r13 = r1.size() + r122;
        if (r13 == 0) goto L36;
        if (r13 == 1) goto L36;
        if (r13 == 3) goto L36;
    L37:
        if (r6 != (-1)) goto L39;
        r6 = r72[r122];
    L39:
        FilterTabsView r43 = this.filterTabsView;
        int r18 = r72[r122];
        r43.addTab(r18, r18, r102[r122], r112[r122], null, false, false, false);
        r5 = r5 + 1;
        r122 = r122 + 1;
        r42 = 6;
    L36:
        this.idsWithCounters.put(Integer.valueOf(r72[r122]), Integer.valueOf(this.COUNTER_SEED / (r13 + 1)));
    L40:
        this.filterTabsView.finishAddingTabs(r27);
        if (r6 == (-1)) goto L43;
    L45:
        FilterTabsView r0 = this.filterTabsView;
        if (r6 == (-1)) goto L49;
        r0.selectTabWithId(r6, 1.0f);
        return;
    L49:
        r0.selectFirstTab();
        return;
    L43:
        if (r1.isEmpty() == true) goto L45;
        r6 = r1.get(0).id;
        goto L45
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.dialogFiltersUpdated) goto L6;
        updateTabs(true);
        return;
    }

    @Override // com.exteragram.messenger.preferences.components.CustomPreferenceCell
    public boolean equals(Object r1) {
        if (this != r1) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(UserConfig.selectedAccount).addObserver(this, NotificationCenter.dialogFiltersUpdated);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(UserConfig.selectedAccount).removeObserver(this, NotificationCenter.dialogFiltersUpdated);
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        super.onDraw(r8);
        r8.drawLine(0.0f, getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, Theme.dividerPaint);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r1) {
        return true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(74.0f), TLObject.FLAG_30));
    }
}
