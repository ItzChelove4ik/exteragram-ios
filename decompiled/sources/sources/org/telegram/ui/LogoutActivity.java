package org.telegram.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextDetailSettingsCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public class LogoutActivity extends BaseFragment {
    private int addAccountRow;
    private int alternativeHeaderRow;
    private int alternativeSectionRow;
    private int cacheRow;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int logoutRow;
    private int logoutSectionRow;
    private int passcodeRow;
    private int phoneRow;
    private int rowCount;
    private int supportRow;

    /* renamed from: org.telegram.ui.LogoutActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ LogoutActivity this$0;

        public AnonymousClass1(LogoutActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ LogoutActivity this$0;

        public ListAdapter(LogoutActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return LogoutActivity.m16021$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != LogoutActivity.m16014$$Nest$fgetalternativeHeaderRow(this.this$0)) goto L7;
            return 0;
        L7:
            if (r2 != LogoutActivity.m16013$$Nest$fgetaddAccountRow(this.this$0)) goto L9;
            return 1;
        L9:
            if (r2 != LogoutActivity.m16019$$Nest$fgetpasscodeRow(this.this$0)) goto L11;
            return 1;
        L11:
            if (r2 != LogoutActivity.m16016$$Nest$fgetcacheRow(this.this$0)) goto L13;
            return 1;
        L13:
            if (r2 != LogoutActivity.m16020$$Nest$fgetphoneRow(this.this$0)) goto L15;
            return 1;
        L15:
            if (r2 != LogoutActivity.m16022$$Nest$fgetsupportRow(this.this$0)) goto L18;
            return 1;
        L18:
            if (r2 != LogoutActivity.m16015$$Nest$fgetalternativeSectionRow(this.this$0)) goto L22;
            return 2;
        L22:
            if (r2 != LogoutActivity.m16017$$Nest$fgetlogoutRow(this.this$0)) goto L25;
            return 3;
        L25:
            return 4;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            int r22 = r2.getAdapterPosition();
            if (r22 != LogoutActivity.m16013$$Nest$fgetaddAccountRow(this.this$0)) goto L5;
            return true;
        L5:
            if (r22 != LogoutActivity.m16019$$Nest$fgetpasscodeRow(this.this$0)) goto L7;
            return true;
        L7:
            if (r22 != LogoutActivity.m16016$$Nest$fgetcacheRow(this.this$0)) goto L9;
            return true;
        L9:
            if (r22 != LogoutActivity.m16020$$Nest$fgetphoneRow(this.this$0)) goto L11;
            return true;
        L11:
            if (r22 != LogoutActivity.m16022$$Nest$fgetsupportRow(this.this$0)) goto L13;
            return true;
        L13:
            if (r22 == LogoutActivity.m16017$$Nest$fgetlogoutRow(this.this$0)) goto L23;
            return false;
        L23:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r4, int r5) {
            int r0 = r4.getItemViewType();
            if (r0 != 0) goto L5;
            HeaderCell r42 = (HeaderCell) r4.itemView;
            if (r5 != LogoutActivity.m16014$$Nest$fgetalternativeHeaderRow(this.this$0)) goto L46;
            r42.setText(LocaleController.getString(R.string.AlternativeOptions));
            return;
        L46:
            return;
        L5:
            if (r0 != 1) goto L7;
            TextDetailSettingsCell r43 = (TextDetailSettingsCell) r4.itemView;
            if (r5 != LogoutActivity.m16013$$Nest$fgetaddAccountRow(this.this$0)) goto L24;
            r43.setTextAndValueAndIcon(LocaleController.getString(R.string.AddAnotherAccount), LocaleController.getString(R.string.AddAnotherAccountInfo), R.drawable.msg_contact_add, true);
            return;
        L24:
            if (r5 != LogoutActivity.m16019$$Nest$fgetpasscodeRow(this.this$0)) goto L28;
            r43.setTextAndValueAndIcon(LocaleController.getString(R.string.SetPasscode), LocaleController.getString(R.string.SetPasscodeInfo), R.drawable.msg_permissions, true);
            return;
        L28:
            if (r5 != LogoutActivity.m16016$$Nest$fgetcacheRow(this.this$0)) goto L32;
            r43.setTextAndValueAndIcon(LocaleController.getString(R.string.ClearCache), LocaleController.getString(R.string.ClearCacheInfo), R.drawable.msg_clearcache, true);
            return;
        L32:
            if (r5 != LogoutActivity.m16020$$Nest$fgetphoneRow(this.this$0)) goto L36;
            r43.setTextAndValueAndIcon(LocaleController.getString(R.string.ChangePhoneNumber), LocaleController.getString(R.string.ChangePhoneNumberInfo), R.drawable.msg_newphone, true);
            return;
        L36:
            if (r5 != LogoutActivity.m16022$$Nest$fgetsupportRow(this.this$0)) goto L45;
            r43.setTextAndValueAndIcon(LocaleController.getString(R.string.ContactSupport), LocaleController.getString(R.string.ContactSupportInfo), R.drawable.msg_help, false);
            return;
        L45:
            return;
        L7:
            if (r0 != 3) goto L9;
            TextSettingsCell r44 = (TextSettingsCell) r4.itemView;
            if (r5 != LogoutActivity.m16017$$Nest$fgetlogoutRow(this.this$0)) goto L44;
            r44.setTextColor(Theme.getColor(Theme.key_text_RedRegular));
            r44.setText(LocaleController.getString(R.string.LogOutTitle), false);
            return;
        L44:
            return;
        L9:
            if (r0 != 4) goto L47;
            TextInfoPrivacyCell r45 = (TextInfoPrivacyCell) r4.itemView;
            if (r5 != LogoutActivity.m16018$$Nest$fgetlogoutSectionRow(this.this$0)) goto L43;
            r45.setText(LocaleController.getString(R.string.LogOutInfo));
            return;
        L43:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 != 0) goto L4;
            View r22 = new HeaderCell(this.mContext);
            r22.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        L14:
            r22.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r22);
        L4:
            if (r3 != 1) goto L6;
            TextDetailSettingsCell r32 = new TextDetailSettingsCell(this.mContext);
            r32.setMultilineDetail(true);
            r32.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r22 = r32;
            goto L14
        L6:
            if (r3 != 2) goto L8;
            r22 = new ShadowSectionCell(this.mContext);
            goto L14
        L8:
            if (r3 == 3) goto L10;
            r22 = new TextInfoPrivacyCell(this.mContext);
            r22.setBackgroundDrawable(Theme.getThemedDrawableByKey(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
            goto L14
        L10:
            r22 = new TextSettingsCell(this.mContext);
            r22.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            goto L14
        }
    }

    /* renamed from: $r8$lambda$AModOZ9FON6aqgcsb-jgOeBWYJE, reason: not valid java name */
    public static /* synthetic */ void m16012$r8$lambda$AModOZ9FON6aqgcsbjgOeBWYJE(int r0, AlertDialog r1, int r2) {
        MessagesController.getInstance(r0).performLogout(1);
    }

    public static /* synthetic */ void $r8$lambda$qEM9ebemnTe_Y8JcL6I7Agr9XwA(LogoutActivity r0, View r1, int r2, float r3, float r4) {
        r0.lambda$createView$0(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetaddAccountRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16013$$Nest$fgetaddAccountRow(LogoutActivity r0) {
        return r0.addAccountRow;
    }

    /* renamed from: -$$Nest$fgetalternativeHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16014$$Nest$fgetalternativeHeaderRow(LogoutActivity r0) {
        return r0.alternativeHeaderRow;
    }

    /* renamed from: -$$Nest$fgetalternativeSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16015$$Nest$fgetalternativeSectionRow(LogoutActivity r0) {
        return r0.alternativeSectionRow;
    }

    /* renamed from: -$$Nest$fgetcacheRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16016$$Nest$fgetcacheRow(LogoutActivity r0) {
        return r0.cacheRow;
    }

    /* renamed from: -$$Nest$fgetlogoutRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16017$$Nest$fgetlogoutRow(LogoutActivity r0) {
        return r0.logoutRow;
    }

    /* renamed from: -$$Nest$fgetlogoutSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16018$$Nest$fgetlogoutSectionRow(LogoutActivity r0) {
        return r0.logoutSectionRow;
    }

    /* renamed from: -$$Nest$fgetpasscodeRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16019$$Nest$fgetpasscodeRow(LogoutActivity r0) {
        return r0.passcodeRow;
    }

    /* renamed from: -$$Nest$fgetphoneRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16020$$Nest$fgetphoneRow(LogoutActivity r0) {
        return r0.phoneRow;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16021$$Nest$fgetrowCount(LogoutActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetsupportRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16022$$Nest$fgetsupportRow(LogoutActivity r0) {
        return r0.supportRow;
    }

    public LogoutActivity() {
    }

    private /* synthetic */ void lambda$createView$0(View r7, int r8, float r9, float r10) {
        Integer r92 = null;
        if (r8 != this.addAccountRow) goto L24;
        int r72 = 0;
        int r82 = 15;
    L5:
        if (r82 < 0) goto L13;
        if (UserConfig.getInstance(r82).isClientActivated() == true) goto L11;
        r72 = r72 + 1;
        if (r92 != null) goto L11;
        r92 = Integer.valueOf(r82);
    L11:
        r82 = r82 - 1;
        goto L5
    L13:
        if (UserConfig.hasPremiumOnAccounts() == true) goto L15;
        r72 = r72 - 8;
    L15:
        if (r72 <= 0) goto L20;
        if (r92 == null) goto L20;
        presentFragment(new LoginActivity(r92.intValue()));
        return;
    L20:
        if (UserConfig.hasPremiumOnAccounts() == true) goto L50;
        showDialog(new LimitReachedBottomSheet(this, getContext(), 7, this.currentAccount, null));
        return;
    L50:
        return;
    L24:
        if (r8 != this.passcodeRow) goto L28;
        presentFragment(PasscodeActivity.determineOpenFragment());
        return;
    L28:
        if (r8 != this.cacheRow) goto L32;
        presentFragment(new CacheControlActivity());
        return;
    L32:
        if (r8 != this.phoneRow) goto L36;
        presentFragment(new ActionIntroActivity(3));
        return;
    L36:
        if (r8 != this.supportRow) goto L40;
        showDialog(AlertsCreator.createSupportAlert(this, null));
        return;
    L40:
        if (r8 == this.logoutRow) goto L42;
        return;
    L42:
        if (getParentActivity() == null) goto L52;
        showDialog(makeLogOutDialog(getParentActivity(), this.currentAccount));
        return;
    }

    public static AlertDialog makeLogOutDialog(Context r2, int r3) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(r2);
        r0.setMessage(LocaleController.getString(R.string.AreYouSureLogout));
        r0.setTitle(LocaleController.getString(R.string.LogOut));
        r0.setPositiveButton(LocaleController.getString(R.string.LogOut), new LogoutActivity$$ExternalSyntheticLambda1(r3));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r22 = r0.create();
        TextView r32 = (TextView) r22.getButton(-1);
        if (r32 == null) goto L5;
        r32.setTextColor(Theme.getColor(Theme.key_text_RedBold));
    L5:
        return r22;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r6) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setTitle(LocaleController.getString(R.string.LogOutTitle));
        INavigationLayout r0 = this.parentLayout;
        if (r0 != null) goto L5;
    L7:
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.listAdapter = new ListAdapter(this, r6);
        FrameLayout r02 = new FrameLayout(r6);
        this.fragmentView = r02;
        r02.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        FrameLayout r03 = (FrameLayout) this.fragmentView;
        RecyclerListView r3 = new RecyclerListView(r6);
        this.listView = r3;
        r3.setVerticalScrollBarEnabled(false);
        this.listView.setLayoutManager(new LinearLayoutManager(r6, 1, false));
        r03.addView(this.listView, LayoutHelper.createFrame(-1, -1, 51));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new LogoutActivity$$ExternalSyntheticLambda0(this));
        this.listView.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        return this.fragmentView;
    L5:
        if (r0.isLayersLayout() == false) goto L7;
        this.actionBar.setOccupyStatusBar(false);
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{TextSettingsCell.class, HeaderCell.class, TextDetailSettingsCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        int r20 = Theme.key_windowBackgroundGrayShadow;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, r20));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, Theme.key_text_RedRegular));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r20));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextDetailSettingsCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextDetailSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextDetailSettingsCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayIcon));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onDialogDismiss(Dialog r1) {
        DownloadController.getInstance(this.currentAccount).checkAutodownloadSettings();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        this.rowCount = 1;
        this.alternativeHeaderRow = 0;
        if (UserConfig.getActivatedAccountsCount() >= 16) goto L5;
        int r1 = this.rowCount;
        this.rowCount = r1 + 1;
        this.addAccountRow = r1;
    L7:
        if (SharedConfig.passcodeHash.length() > 0) goto L9;
        int r12 = this.rowCount;
        this.rowCount = r12 + 1;
        this.passcodeRow = r12;
    L10:
        int r13 = this.rowCount;
        this.cacheRow = r13;
        this.phoneRow = r13 + 1;
        this.supportRow = r13 + 2;
        this.alternativeSectionRow = r13 + 3;
        int r2 = r13 + 5;
        this.logoutRow = r13 + 4;
        this.rowCount = r13 + 6;
        this.logoutSectionRow = r2;
        return true;
    L9:
        this.passcodeRow = -1;
        goto L10
    L5:
        this.addAccountRow = -1;
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
        return;
    }
}
