package org.telegram.ui.Business;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

/* loaded from: classes6.dex */
public class TimezoneSelector extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private String currentTimezone;
    private LinearLayout emptyView;
    private UniversalRecyclerView listView;
    private String query;
    private ActionBarMenuItem searchItem;
    private boolean searching;
    private String systemTimezone;
    private boolean useSystem;
    private Utilities.Callback<String> whenTimezoneSelected;

    /* renamed from: org.telegram.ui.Business.TimezoneSelector$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ TimezoneSelector this$0;

        public AnonymousClass1(TimezoneSelector r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Business.TimezoneSelector$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ TimezoneSelector this$0;

        public AnonymousClass2(TimezoneSelector r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            TimezoneSelector.m6409$$Nest$fputsearching(this.this$0, false);
            TimezoneSelector.m6408$$Nest$fputquery(this.this$0, null);
            TimezoneSelector.m6407$$Nest$fgetlistView(this.this$0).adapter.update(true);
            TimezoneSelector.m6407$$Nest$fgetlistView(this.this$0).scrollToPosition(0);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            TimezoneSelector.m6409$$Nest$fputsearching(this.this$0, true);
            TimezoneSelector.m6407$$Nest$fgetlistView(this.this$0).adapter.update(true);
            TimezoneSelector.m6407$$Nest$fgetlistView(this.this$0).scrollToPosition(0);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r2) {
            TimezoneSelector.m6408$$Nest$fputquery(this.this$0, r2.getText().toString());
            TimezoneSelector.m6407$$Nest$fgetlistView(this.this$0).adapter.update(true);
            TimezoneSelector.m6407$$Nest$fgetlistView(this.this$0).scrollToPosition(0);
        }
    }

    /* renamed from: org.telegram.ui.Business.TimezoneSelector$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ TimezoneSelector this$0;

        public AnonymousClass3(TimezoneSelector r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(this.this$0.getParentActivity().getCurrentFocus());
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$XsK6i7gXdvR0vh4DtEbK2BsV420(TimezoneSelector r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$hR77QYcH_nPkxpd9Dn9uYC_RoZQ(TimezoneSelector r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClick(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalRecyclerView m6407$$Nest$fgetlistView(TimezoneSelector r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fputquery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6408$$Nest$fputquery(TimezoneSelector r0, String r1) {
        r0.query = r1;
    }

    /* renamed from: -$$Nest$fputsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6409$$Nest$fputsearching(TimezoneSelector r0, boolean r1) {
        r0.searching = r1;
    }

    public TimezoneSelector() {
    }

    private void fillItems(ArrayList<UItem> r13, UniversalAdapter r14) {
        if (this.searching == true) goto L5;
    L7:
        boolean r0 = false;
    L8:
        TimezonesController r3 = TimezonesController.getInstance(this.currentAccount);
        if (r0 == true) goto L11;
        r14.whiteSectionStart();
        r13.add(UItem.asRippleCheck(-1, LocaleController.getString(R.string.TimezoneDetectAutomatically)).setChecked(this.useSystem));
        r14.whiteSectionEnd();
        r13.add(UItem.asShadow(LocaleController.formatString(R.string.TimezoneDetectAutomaticallyInfo, new Object[]{r3.getTimezoneName(this.currentTimezone, true)})));
    L11:
        r14.whiteSectionStart();
        if (r0 == true) goto L14;
        r13.add(UItem.asHeader(LocaleController.getString(R.string.TimezoneHeader)));
    L14:
        int r4 = 0;
        boolean r5 = true;
    L16:
        if (r4 >= r3.getTimezones().size()) goto L33;
        TLRPC.TL_timezone r6 = r3.getTimezones().get(r4);
        CharSequence r7 = r3.getTimezoneName(r6, false);
        if (r0 == false) goto L25;
        String r8 = AndroidUtilities.translitSafe(r6.name).toLowerCase().replace("/", " ");
        String r9 = AndroidUtilities.translitSafe(this.query).toLowerCase();
        if (r8.contains(" " + r9) == false) goto L22;
    L24:
        r7 = AndroidUtilities.highlightText(r7, this.query, this.resourceProvider);
        goto L25
    L22:
        if (r8.startsWith(r9) == true) goto L24;
    L32:
        r4 = r4 + 1;
    L25:
        UItem r52 = UItem.asRadio(r4, r7, r3.getTimezoneOffsetName(r6)).setChecked(TextUtils.equals(r6.id, this.currentTimezone));
        if (this.useSystem == false) goto L30;
        if (r0 == true) goto L30;
        boolean r62 = false;
    L31:
        r13.add(r52.setEnabled(r62));
        r5 = false;
    L30:
        r62 = true;
        goto L31
    L33:
        r14.whiteSectionEnd();
        if (r5 == false) goto L37;
        r13.add(UItem.asCustomShadow(this.emptyView).setTransparent(true));
        return;
    L37:
        r13.add(UItem.asShadow(null));
        return;
    L5:
        if (TextUtils.isEmpty(this.query) == true) goto L7;
        r0 = true;
        goto L8
    }

    private void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        if (r1.id != (-1)) goto L16;
        boolean r12 = this.useSystem;
        this.useSystem = !r12;
        if (r12 == true) goto L9;
        String r13 = this.systemTimezone;
        this.currentTimezone = r13;
        Utilities.Callback<String> r32 = this.whenTimezoneSelected;
        if (r32 == null) goto L9;
        r32.run(r13);
    L9:
        TextCheckCell r22 = (TextCheckCell) r2;
        r22.setChecked(this.useSystem);
        boolean r14 = this.useSystem;
        if (r14 == false) goto L12;
        int r33 = Theme.key_windowBackgroundChecked;
    L13:
        r22.setBackgroundColorAnimated(r14, Theme.getColor(r33));
        this.listView.adapter.update(true);
        return;
    L12:
        r33 = Theme.key_windowBackgroundUnchecked;
        goto L13
    L16:
        if (r2.isEnabled() == false) goto L32;
        TimezonesController r23 = TimezonesController.getInstance(this.currentAccount);
        int r34 = r1.id;
        if (r34 >= 0) goto L21;
        return;
    L21:
        if (r34 >= r23.getTimezones().size()) goto L33;
        TLRPC.TL_timezone r15 = r23.getTimezones().get(r1.id);
        this.useSystem = false;
        String r16 = r15.id;
        this.currentTimezone = r16;
        Utilities.Callback<String> r24 = this.whenTimezoneSelected;
        if (r24 == null) goto L27;
        r24.run(r16);
    L27:
        if (this.searching == false) goto L29;
        this.actionBar.closeSearchField(true);
    L29:
        this.listView.adapter.update(true);
        return;
    L33:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r12) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.TimezoneTitle));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ActionBarMenuItem r0 = this.actionBar.createMenu().addItem(1, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass2(this));
        this.searchItem = r0;
        r0.setSearchFieldHint(LocaleController.getString(R.string.Search));
        FrameLayout r02 = new FrameLayout(r12);
        r02.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        UniversalRecyclerView r2 = new UniversalRecyclerView(this, new TimezoneSelector$$ExternalSyntheticLambda0(this), new TimezoneSelector$$ExternalSyntheticLambda1(this), null);
        this.listView = r2;
        r2.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        r02.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setOnScrollListener(new AnonymousClass3(this));
        LinearLayout r22 = new LinearLayout(r12);
        this.emptyView = r22;
        r22.setOrientation(1);
        this.emptyView.setMinimumHeight(AndroidUtilities.dp(500.0f));
        BackupImageView r23 = new BackupImageView(r12);
        r23.getImageReceiver().setAllowLoadingOnAttachedOnly(false);
        MediaDataController.getInstance(this.currentAccount).setPlaceholderImage(r23, "RestrictedEmoji", "🌖", "130_130");
        this.emptyView.addView(r23, LayoutHelper.createLinear(130, 130, 49, 0, 42, 0, 12));
        TextView r24 = new TextView(r12);
        r24.setText(LocaleController.getString(R.string.TimezoneNotFound));
        r24.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, this.resourceProvider));
        r24.setTextSize(1, 15.0f);
        this.emptyView.addView(r24, LayoutHelper.createLinear(-2, -2, 49, 0, 0, 0, 0));
        this.fragmentView = r02;
        return r02;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.timezonesUpdated) goto L10;
        UniversalRecyclerView r0 = this.listView;
        if (r0 == null) goto L11;
        UniversalAdapter r02 = r0.adapter;
        if (r02 == null) goto L12;
        r02.update(true);
        return;
    L12:
        return;
    L11:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        String r0 = TimezonesController.getInstance(this.currentAccount).getSystemTimezoneId();
        this.systemTimezone = r0;
        this.useSystem = TextUtils.equals(r0, this.currentTimezone);
        getNotificationCenter().addObserver(this, NotificationCenter.timezonesUpdated);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        getNotificationCenter().removeObserver(this, NotificationCenter.timezonesUpdated);
        super.onFragmentDestroy();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }

    public TimezoneSelector setValue(String r1) {
        this.currentTimezone = r1;
        return this;
    }

    public TimezoneSelector whenSelected(Utilities.Callback<String> r1) {
        this.whenTimezoneSelected = r1;
        return this;
    }
}
