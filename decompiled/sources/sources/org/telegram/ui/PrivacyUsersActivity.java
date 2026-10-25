package org.telegram.ui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ManageChatTextCell;
import org.telegram.ui.Cells.ManageChatUserCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public class PrivacyUsersActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private int blockUserDetailRow;
    private int blockUserRow;
    private boolean blockedUsersActivity;
    private int currentType;
    private int deleteAllRow;
    private EmptyTextProgressView emptyView;
    private boolean isAlwaysShare;
    private boolean isGroup;
    private LinearLayoutManager layoutManager;
    private RecyclerListView listView;
    private ListAdapter listViewAdapter;
    private int rowCount;
    public int rulesType;
    private ArrayList<Long> uidArray;
    private int usersDetailRow;
    private int usersEndRow;
    private int usersHeaderRow;
    private int usersStartRow;

    /* renamed from: org.telegram.ui.PrivacyUsersActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PrivacyUsersActivity this$0;

        public AnonymousClass1(PrivacyUsersActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.PrivacyUsersActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.OnScrollListener {
        final /* synthetic */ PrivacyUsersActivity this$0;

        public AnonymousClass2(PrivacyUsersActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (this.this$0.getMessagesController().blockedEndReached == true) goto L13;
            int r22 = Math.abs(PrivacyUsersActivity.m17782$$Nest$fgetlayoutManager(this.this$0).findLastVisibleItemPosition() - PrivacyUsersActivity.m17782$$Nest$fgetlayoutManager(this.this$0).findFirstVisibleItemPosition()) + 1;
            int r12 = r1.getAdapter().getItemCount();
            if (r22 > 0) goto L8;
            return;
        L8:
            if (PrivacyUsersActivity.m17782$$Nest$fgetlayoutManager(this.this$0).findLastVisibleItemPosition() < (r12 - 10)) goto L12;
            this.this$0.getMessagesController().getBlockedPeers(false);
            return;
        L12:
            return;
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ PrivacyUsersActivity this$0;

        /* renamed from: $r8$lambda$1IgnhQdxtW-eOewKJgjUwGVo5LE, reason: not valid java name */
        public static /* synthetic */ boolean m17790$r8$lambda$1IgnhQdxtWeOewKJgjUwGVo5LE(ListAdapter r0, ManageChatUserCell r1, boolean r2) {
            return r0.lambda$onCreateViewHolder$0(r1, r2);
        }

        public ListAdapter(PrivacyUsersActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        private /* synthetic */ boolean lambda$onCreateViewHolder$0(ManageChatUserCell r1, boolean r2) {
            if (r2 == false) goto L6;
            PrivacyUsersActivity.m17789$$Nest$mshowUnblockAlert(this.this$0, (Long) r1.getTag(), r1);
            return true;
        L6:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return PrivacyUsersActivity.m17783$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != PrivacyUsersActivity.m17781$$Nest$fgetdeleteAllRow(this.this$0)) goto L7;
            return 4;
        L7:
            if (r2 != PrivacyUsersActivity.m17787$$Nest$fgetusersHeaderRow(this.this$0)) goto L11;
            return 3;
        L11:
            if (r2 != PrivacyUsersActivity.m17779$$Nest$fgetblockUserRow(this.this$0)) goto L15;
            return 2;
        L15:
            if (r2 != PrivacyUsersActivity.m17778$$Nest$fgetblockUserDetailRow(this.this$0)) goto L17;
            return 1;
        L17:
            if (r2 == PrivacyUsersActivity.m17785$$Nest$fgetusersDetailRow(this.this$0)) goto L23;
            return 0;
        L23:
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            int r0 = r1.getItemViewType();
            if (r0 != 0) goto L5;
            return true;
        L5:
            if (r0 != 2) goto L7;
            return true;
        L7:
            if (r0 == 4) goto L14;
            return false;
        L14:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
            int r0 = r9.getItemViewType();
            boolean r2 = false;
            if (r0 == 0) goto L39;
            if (r0 != 1) goto L6;
            TextInfoPrivacyCell r92 = (TextInfoPrivacyCell) r9.itemView;
            int r02 = PrivacyUsersActivity.m17778$$Nest$fgetblockUserDetailRow(this.this$0);
            PrivacyUsersActivity r8 = this.this$0;
            if (r10 != r02) goto L36;
            if (PrivacyUsersActivity.m17780$$Nest$fgetcurrentType(r8) != 1) goto L33;
            r92.setFixedSize(0);
            r92.setText(LocaleController.getString(R.string.BlockedUsersInfo));
            return;
        L33:
            r92.setFixedSize(8);
            r92.setText(null);
            return;
        L36:
            if (r10 != PrivacyUsersActivity.m17785$$Nest$fgetusersDetailRow(r8)) goto L79;
            r92.setFixedSize(12);
            r92.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L79:
            return;
        L6:
            if (r0 != 2) goto L8;
            ManageChatTextCell r93 = (ManageChatTextCell) r9.itemView;
            r93.setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
            if (PrivacyUsersActivity.m17780$$Nest$fgetcurrentType(this.this$0) != 1) goto L22;
            r93.setText(LocaleController.getString(R.string.BlockUser), null, R.drawable.msg_contact_add, false);
            return;
        L22:
            String r102 = LocaleController.getString(R.string.PrivacyAddAnException);
            int r03 = R.drawable.msg_contact_add;
            if (PrivacyUsersActivity.m17784$$Nest$fgetuidArray(this.this$0).size() <= 0) goto L25;
            r2 = true;
        L25:
            r93.setText(r102, null, r03, r2);
            return;
        L8:
            if (r0 != 3) goto L82;
            HeaderCell r94 = (HeaderCell) r9.itemView;
            if (r10 == PrivacyUsersActivity.m17787$$Nest$fgetusersHeaderRow(this.this$0)) goto L13;
            return;
        L13:
            if (PrivacyUsersActivity.m17780$$Nest$fgetcurrentType(this.this$0) != 1) goto L16;
            r94.setText(LocaleController.formatPluralString("BlockedUsersCount", this.this$0.getMessagesController().totalBlockedCount, new Object[0]));
            return;
        L16:
            r94.setText(LocaleController.getString(R.string.PrivacyExceptions));
            return;
        L82:
            return;
        L39:
            ManageChatUserCell r95 = (ManageChatUserCell) r9.itemView;
            int r04 = PrivacyUsersActivity.m17780$$Nest$fgetcurrentType(this.this$0);
            PrivacyUsersActivity r4 = this.this$0;
            if (r04 != 1) goto L42;
            long r42 = r4.getMessagesController().blockePeers.keyAt(r10 - PrivacyUsersActivity.m17788$$Nest$fgetusersStartRow(this.this$0));
        L43:
            r95.setTag(Long.valueOf(r42));
            PrivacyUsersActivity r6 = this.this$0;
            if (r42 <= 0) goto L61;
            TLRPC.User r05 = r6.getMessagesController().getUser(Long.valueOf(r42));
            if (r05 != null) goto L48;
            return;
        L48:
            if (r05.bot == false) goto L50;
            String r43 = LocaleController.getString(R.string.Bot).substring(0, 1).toUpperCase() + LocaleController.getString(R.string.Bot).substring(1);
        L57:
            if (r10 == (PrivacyUsersActivity.m17786$$Nest$fgetusersEndRow(this.this$0) - 1)) goto L59;
            r2 = true;
        L59:
            r95.setData(r05, null, r43, r2);
            return;
        L50:
            String r44 = r05.phone;
            if (r44 != null) goto L53;
        L55:
            r43 = LocaleController.getString(R.string.NumberUnknown);
            goto L57
        L53:
            if (r44.length() == 0) goto L55;
            r43 = PhoneFormat.getInstance().format("+" + r05.phone);
            goto L57
        L61:
            TLRPC.Chat r06 = r6.getMessagesController().getChat(Long.valueOf(-r42));
            if (r06 == null) goto L81;
            int r45 = r06.participants_count;
            if (r45 == 0) goto L67;
            String r46 = LocaleController.formatPluralString("Members", r45, new Object[0]);
        L74:
            if (r10 == (PrivacyUsersActivity.m17786$$Nest$fgetusersEndRow(this.this$0) - 1)) goto L76;
            r2 = true;
        L76:
            r95.setData(r06, null, r46, r2);
            return;
        L67:
            if (r06.has_geo == false) goto L70;
            r46 = LocaleController.getString(R.string.MegaLocation);
            goto L74
        L70:
            if (ChatObject.isPublic(r06) == true) goto L72;
            r46 = LocaleController.getString(R.string.MegaPrivate);
            goto L74
        L72:
            r46 = LocaleController.getString(R.string.MegaPublic);
            goto L74
        L81:
            return;
        L42:
            r42 = ((Long) PrivacyUsersActivity.m17784$$Nest$fgetuidArray(r4).get(r10 - PrivacyUsersActivity.m17788$$Nest$fgetusersStartRow(this.this$0))).longValue();
            goto L43
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r8, int r9) {
            if (r9 == 0) goto L13;
            if (r9 != 1) goto L6;
            FrameLayout r1 = new TextInfoPrivacyCell(this.mContext);
        L15:
            return new RecyclerListView.Holder(r1);
        L6:
            if (r9 != 2) goto L8;
            r1 = new ManageChatTextCell(this.mContext);
            goto L15
        L8:
            if (r9 == 4) goto L10;
            HeaderCell r12 = new HeaderCell(this.mContext, Theme.key_windowBackgroundWhiteBlueHeader, 21, 11, false);
            r12.setHeight(43);
            r1 = r12;
            goto L15
        L10:
            TextCell r13 = new TextCell(r8.getContext());
            r13.setText(LocaleController.getString(R.string.NotificationsDeleteAllException), false);
            r13.setColors(-1, Theme.key_text_RedRegular);
            r1 = r13;
            goto L15
        L13:
            ManageChatUserCell r14 = new ManageChatUserCell(this.mContext, 7, 6, true);
            r14.setDelegate(new PrivacyUsersActivity$ListAdapter$$ExternalSyntheticLambda0(this));
            r1 = r14;
            goto L15
        }
    }

    public static /* synthetic */ boolean $r8$lambda$Hi6SwhBK9d4hNAar6iWqkzz_Qhk(PrivacyUsersActivity r0, View r1, int r2) {
        return r0.lambda$createView$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$IRxsFfqJeF8bYIFfYbr6z3JW5Wc(PrivacyUsersActivity r0, Long r1) {
        r0.lambda$showUnblockAlert$6(r1);
    }

    /* renamed from: $r8$lambda$IghMll34DG_QbBMWc-jigkQrbXs, reason: not valid java name */
    public static /* synthetic */ Integer m17775$r8$lambda$IghMll34DG_QbBMWcjigkQrbXs(PrivacyUsersActivity r0, Integer r1) {
        return r0.lambda$createView$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$M3hy4hzUNZJ1G8Bdgm6KLqooY0g(PrivacyUsersActivity r0, View r1, int r2) {
        r0.lambda$createView$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$WGJutYDDjZeEWNyXB3G3aD1YKDY(PrivacyUsersActivity r0, boolean r1, boolean r2, ArrayList r3) {
        r0.lambda$createView$2(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$b5XJN0VO-b18cehwR2i7Iqv8Tsc, reason: not valid java name */
    public static /* synthetic */ void m17776$r8$lambda$b5XJN0VOb18cehwR2i7Iqv8Tsc(PrivacyUsersActivity r0) {
        r0.lambda$createView$1();
    }

    /* renamed from: $r8$lambda$fVdMY7E8-IRr1KTOZrpE4FA24wc, reason: not valid java name */
    public static /* synthetic */ void m17777$r8$lambda$fVdMY7E8IRr1KTOZrpE4FA24wc(PrivacyUsersActivity r0) {
        r0.lambda$getThemeDescriptions$7();
    }

    public static /* synthetic */ void $r8$lambda$hnWlICHfp5IdeRLJ_VQDPfezJOM(PrivacyUsersActivity r0, Long r1) {
        r0.lambda$showUnblockAlert$5(r1);
    }

    /* renamed from: -$$Nest$fgetblockUserDetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17778$$Nest$fgetblockUserDetailRow(PrivacyUsersActivity r0) {
        return r0.blockUserDetailRow;
    }

    /* renamed from: -$$Nest$fgetblockUserRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17779$$Nest$fgetblockUserRow(PrivacyUsersActivity r0) {
        return r0.blockUserRow;
    }

    /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17780$$Nest$fgetcurrentType(PrivacyUsersActivity r0) {
        return r0.currentType;
    }

    /* renamed from: -$$Nest$fgetdeleteAllRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17781$$Nest$fgetdeleteAllRow(PrivacyUsersActivity r0) {
        return r0.deleteAllRow;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m17782$$Nest$fgetlayoutManager(PrivacyUsersActivity r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17783$$Nest$fgetrowCount(PrivacyUsersActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetuidArray, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m17784$$Nest$fgetuidArray(PrivacyUsersActivity r0) {
        return r0.uidArray;
    }

    /* renamed from: -$$Nest$fgetusersDetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17785$$Nest$fgetusersDetailRow(PrivacyUsersActivity r0) {
        return r0.usersDetailRow;
    }

    /* renamed from: -$$Nest$fgetusersEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17786$$Nest$fgetusersEndRow(PrivacyUsersActivity r0) {
        return r0.usersEndRow;
    }

    /* renamed from: -$$Nest$fgetusersHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17787$$Nest$fgetusersHeaderRow(PrivacyUsersActivity r0) {
        return r0.usersHeaderRow;
    }

    /* renamed from: -$$Nest$fgetusersStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17788$$Nest$fgetusersStartRow(PrivacyUsersActivity r0) {
        return r0.usersStartRow;
    }

    /* renamed from: -$$Nest$mshowUnblockAlert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17789$$Nest$mshowUnblockAlert(PrivacyUsersActivity r0, Long r1, View r2) {
        r0.showUnblockAlert(r1, r2);
    }

    public PrivacyUsersActivity() {
        this.currentType = 1;
        this.blockedUsersActivity = true;
    }

    private /* synthetic */ Integer lambda$createView$0(Integer r1) {
        if (r1.intValue() == this.deleteAllRow) goto L5;
        return null;
    L5:
        return Integer.valueOf(Theme.multAlpha(Theme.getColor(Theme.key_text_RedRegular), 0.12f));
    }

    private /* synthetic */ void lambda$createView$1() {
        this.uidArray.clear();
        updateRows();
        finishFragment();
    }

    private /* synthetic */ void lambda$createView$2(boolean r3, boolean r4, ArrayList r5) {
        int r32 = r5.size();
        int r42 = 0;
    L3:
        if (r42 >= r32) goto L8;
        Object r0 = r5.get(r42);
        r42 = r42 + 1;
        Long r02 = (Long) r0;
        if (this.uidArray.contains(r02) == true) goto L3;
        this.uidArray.add(r02);
        goto L3
    L8:
        updateRows();
    }

    private /* synthetic */ void lambda$createView$3(View r7, int r8) {
        if (r8 != this.deleteAllRow) goto L7;
        AlertDialog r6 = AlertsCreator.createSimpleAlert(getContext(), LocaleController.getString(R.string.NotificationsDeleteAllExceptionTitle), LocaleController.getString(R.string.NotificationsDeleteAllExceptionAlert), LocaleController.getString(R.string.Delete), new PrivacyUsersActivity$$ExternalSyntheticLambda6(this), null).create();
        r6.show();
        r6.redPositive();
        return;
    L7:
        if (r8 != this.blockUserRow) goto L33;
        if (this.currentType != 1) goto L12;
        presentFragment(new DialogOrContactPickerActivity());
        return;
    L12:
        Bundle r72 = new Bundle();
        if (this.isAlwaysShare == false) goto L15;
        String r82 = "isAlwaysShare";
    L16:
        r72.putBoolean(r82, true);
        if (this.isGroup == false) goto L20;
        r72.putInt("chatAddType", 1);
    L23:
        if (this.isAlwaysShare == false) goto L28;
        if (this.rulesType != 1) goto L28;
        r72.putBoolean("allowPremium", true);
    L30:
        GroupCreateActivity r83 = new GroupCreateActivity(r72);
        r83.setDelegate(new PrivacyUsersActivity$$ExternalSyntheticLambda7(this));
        presentFragment(r83);
        return;
    L28:
        if (this.rulesType != 12) goto L30;
        r72.putBoolean("allowMiniapps", true);
        goto L30
    L20:
        if (this.currentType != 2) goto L23;
        r72.putInt("chatAddType", 2);
        goto L23
    L15:
        r82 = "isNeverShare";
        goto L16
    L33:
        if (r8 >= this.usersStartRow) goto L35;
        return;
    L35:
        if (r8 < this.usersEndRow) goto L37;
        return;
    L37:
        if (this.currentType != 1) goto L40;
        Bundle r73 = new Bundle();
        r73.putLong("user_id", getMessagesController().blockePeers.keyAt(r8 - this.usersStartRow));
        presentFragment(new ProfileActivity(r73));
        return;
    L40:
        Bundle r74 = new Bundle();
        long r2 = this.uidArray.get(r8 - this.usersStartRow).longValue();
        if (DialogObject.isUserDialog(r2) == false) goto L43;
        r74.putLong("user_id", r2);
    L44:
        presentFragment(new ProfileActivity(r74));
        return;
    L43:
        r74.putLong("chat_id", -r2);
        goto L44
    }

    private /* synthetic */ boolean lambda$createView$4(View r4, int r5) {
        int r0 = this.usersStartRow;
        if (r5 >= r0) goto L5;
        return false;
    L5:
        if (r5 < this.usersEndRow) goto L7;
        return false;
    L7:
        if (this.currentType != 1) goto L9;
        showUnblockAlert(Long.valueOf(getMessagesController().blockePeers.keyAt(r5 - this.usersStartRow)), r4);
    L10:
        return true;
    L9:
        showUnblockAlert(this.uidArray.get(r5 - r0), r4);
        goto L10
    }

    private /* synthetic */ void lambda$getThemeDescriptions$7() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L10;
        int r02 = r0.getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L14;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof ManageChatUserCell) == false) goto L9;
        ((ManageChatUserCell) r3).update(0);
    L9:
        r2 = r2 + 1;
        goto L5
    L14:
        return;
    }

    private /* synthetic */ void lambda$showUnblockAlert$5(Long r3) {
        getMessagesController().unblockPeer(r3.longValue());
    }

    private /* synthetic */ void lambda$showUnblockAlert$6(Long r2) {
        this.uidArray.remove(r2);
        updateRows();
        if (this.uidArray.isEmpty() == false) goto L6;
        finishFragment();
        return;
    }

    private void showUnblockAlert(Long r12, View r13) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        ItemOptions r132 = ItemOptions.makeOptions(this, r13).setScrimViewBackground(new ColorDrawable(Theme.getColor(Theme.key_windowBackgroundWhite)));
        int r1 = 0;
        if (this.currentType != 1) goto L8;
        boolean r0 = true;
    L9:
        ItemOptions r5 = r132.addIf(r0, 0, LocaleController.getString(R.string.Unblock), new PrivacyUsersActivity$$ExternalSyntheticLambda4(this, r12));
        int r133 = this.currentType;
        if (r133 == 1) goto L12;
        boolean r6 = true;
    L13:
        if (r133 != 0) goto L15;
        r1 = R.drawable.msg_user_remove;
    L15:
        r5.addIf(r6, r1, LocaleController.getString(R.string.Remove), true, new PrivacyUsersActivity$$ExternalSyntheticLambda5(this, r12)).setMinWidth(190).show();
        return;
    L12:
        r6 = false;
        goto L13
    L8:
        r0 = false;
        goto L9
    }

    private void updateRows() {
        this.rowCount = 0;
        this.blockUserRow = -1;
        this.usersHeaderRow = -1;
        this.blockUserDetailRow = -1;
        this.deleteAllRow = -1;
        if (this.blockedUsersActivity == true) goto L5;
    L6:
        int r1 = this.rowCount;
        int r2 = r1 + 1;
        this.rowCount = r2;
        this.blockUserRow = r1;
        int r3 = this.currentType;
        if (r3 != 1) goto L9;
        this.rowCount = r1 + 2;
        this.blockUserDetailRow = r2;
    L9:
        if (r3 != 1) goto L11;
        int r12 = getMessagesController().blockePeers.size();
    L12:
        if (r12 == 0) goto L19;
        int r0 = this.currentType;
        if (r0 != 1) goto L16;
        int r22 = this.rowCount;
        this.rowCount = r22 + 1;
        this.usersHeaderRow = r22;
    L16:
        int r23 = this.rowCount;
        this.usersStartRow = r23;
        int r24 = r23 + r12;
        this.usersEndRow = r24;
        int r13 = r24 + 1;
        this.rowCount = r13;
        this.usersDetailRow = r24;
        if (r0 == 1) goto L20;
        this.rowCount = r24 + 2;
        this.deleteAllRow = r13;
    L20:
        ListAdapter r5 = this.listViewAdapter;
        if (r5 == null) goto L24;
        r5.notifyDataSetChanged();
        return;
    L24:
        return;
    L19:
        this.usersHeaderRow = -1;
        this.usersStartRow = -1;
        this.usersEndRow = -1;
        this.usersDetailRow = -1;
        this.deleteAllRow = -1;
        goto L20
    L11:
        r12 = this.uidArray.size();
        goto L12
    L5:
        if (getMessagesController().totalBlockedCount < 0) goto L20;
        goto L6
    }

    private void updateVisibleRows(int r5) {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L11;
        int r02 = r0.getChildCount();
        int r1 = 0;
    L6:
        if (r1 >= r02) goto L15;
        View r2 = this.listView.getChildAt(r1);
        if ((r2 instanceof ManageChatUserCell) == false) goto L10;
        ((ManageChatUserCell) r2).update(r5);
    L10:
        r1 = r1 + 1;
        goto L6
    L15:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r9) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        int r0 = this.currentType;
        int r2 = 2;
        if (r0 != 1) goto L5;
        this.actionBar.setTitle(LocaleController.getString(R.string.BlockedUsers));
    L20:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        FrameLayout r02 = new FrameLayout(r9);
        this.fragmentView = r02;
        r02.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        EmptyTextProgressView r3 = new EmptyTextProgressView(r9);
        this.emptyView = r3;
        if (this.currentType != 1) goto L23;
        r3.setText(LocaleController.getString(R.string.NoBlocked));
    L24:
        r02.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        RecyclerListView r32 = new RecyclerListView(r9);
        this.listView = r32;
        r32.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        this.listView.setItemSelectorColorProvider(new PrivacyUsersActivity$$ExternalSyntheticLambda1(this));
        this.listView.setEmptyView(this.emptyView);
        RecyclerListView r33 = this.listView;
        LinearLayoutManager r6 = new LinearLayoutManager(r9, 1, false);
        this.layoutManager = r6;
        r33.setLayoutManager(r6);
        this.listView.setVerticalScrollBarEnabled(false);
        RecyclerListView r34 = this.listView;
        ListAdapter r62 = new ListAdapter(this, r9);
        this.listViewAdapter = r62;
        r34.setAdapter(r62);
        RecyclerListView r92 = this.listView;
        if (LocaleController.isRTL == false) goto L27;
        r2 = 1;
    L27:
        r92.setVerticalScrollbarPosition(r2);
        r02.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setOnItemClickListener(new PrivacyUsersActivity$$ExternalSyntheticLambda2(this));
        this.listView.setOnItemLongClickListener(new PrivacyUsersActivity$$ExternalSyntheticLambda3(this));
        if (this.currentType != 1) goto L33;
        this.listView.setOnScrollListener(new AnonymousClass2(this));
        int r93 = getMessagesController().totalBlockedCount;
        EmptyTextProgressView r03 = this.emptyView;
        if (r93 >= 0) goto L32;
        r03.showProgress();
        goto L33
    L32:
        r03.showTextView();
    L33:
        updateRows();
        return this.fragmentView;
    L23:
        r3.setText(LocaleController.getString(R.string.NoContacts));
        goto L24
    L5:
        if (r0 != 2) goto L10;
        boolean r04 = this.isAlwaysShare;
        ActionBar r35 = this.actionBar;
        if (r04 == false) goto L9;
        r35.setTitle(LocaleController.getString(R.string.FilterAlwaysShow));
        goto L20
    L9:
        r35.setTitle(LocaleController.getString(R.string.FilterNeverShow));
        goto L20
    L10:
        boolean r05 = this.isGroup;
        boolean r36 = this.isAlwaysShare;
        if (r05 == false) goto L16;
        ActionBar r06 = this.actionBar;
        if (r36 == false) goto L15;
        r06.setTitle(LocaleController.getString(R.string.AlwaysAllow));
        goto L20
    L15:
        r06.setTitle(LocaleController.getString(R.string.NeverAllow));
        goto L20
    L16:
        ActionBar r07 = this.actionBar;
        if (r36 == false) goto L19;
        r07.setTitle(LocaleController.getString(R.string.AlwaysShareWithTitle));
        goto L20
    L19:
        r07.setTitle(LocaleController.getString(R.string.NeverShareWithTitle));
        goto L20
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.updateInterfaces) goto L11;
        int r12 = ((Integer) r3[0]).intValue();
        if ((MessagesController.UPDATE_MASK_AVATAR & r12) == 0) goto L7;
    L8:
        updateVisibleRows(r12);
        return;
    L7:
        if ((MessagesController.UPDATE_MASK_NAME & r12) != 0) goto L8;
        return;
    L11:
        if (r1 != NotificationCenter.blockedUsersDidLoad) goto L15;
        this.emptyView.showTextView();
        updateRows();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        PrivacyUsersActivity$$ExternalSyntheticLambda0 r8 = new PrivacyUsersActivity$$ExternalSyntheticLambda0(this);
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{ManageChatUserCell.class, ManageChatTextCell.class, HeaderCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_emptyListPlaceholder));
        r1.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_progressCircle));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow));
        int r19 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, new String[]{"nameTextView"}, null, null, null, r19));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, new String[]{"statusColor"}, null, null, r8, Theme.key_windowBackgroundWhiteGrayText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, new String[]{"statusOnlineColor"}, null, null, r8, Theme.key_windowBackgroundWhiteBlueText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, null, Theme.avatarDrawables, null, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"textView"}, null, null, null, r19));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayIcon));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueButton));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueIcon));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    public PrivacyUsersActivity loadBlocked() {
        getMessagesController().getBlockedPeers(true);
        return this;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.updateInterfaces);
        if (this.currentType != 1) goto L5;
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.blockedUsersDidLoad);
    L5:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.updateInterfaces);
        if (this.currentType != 1) goto L6;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.blockedUsersDidLoad);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ListAdapter r0 = this.listViewAdapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
        return;
    }
}
