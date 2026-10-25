package org.telegram.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.TextBlockCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public class GroupInviteActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private long chatId;
    private int copyLinkRow;
    private EmptyTextProgressView emptyView;
    private TLRPC.TL_chatInviteExported invite;
    private int linkInfoRow;
    private int linkRow;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private boolean loading;
    private int revokeLinkRow;
    private int rowCount;
    private int shadowRow;
    private int shareLinkRow;

    /* renamed from: org.telegram.ui.GroupInviteActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ GroupInviteActivity this$0;

        public AnonymousClass1(GroupInviteActivity r1) {
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
        final /* synthetic */ GroupInviteActivity this$0;

        public ListAdapter(GroupInviteActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (GroupInviteActivity.m15431$$Nest$fgetloading(this.this$0) == false) goto L7;
            return 0;
        L7:
            return GroupInviteActivity.m15433$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 != GroupInviteActivity.m15427$$Nest$fgetcopyLinkRow(this.this$0)) goto L5;
        L21:
            return 0;
        L5:
            if (r3 == GroupInviteActivity.m15435$$Nest$fgetshareLinkRow(this.this$0)) goto L21;
            if (r3 == GroupInviteActivity.m15432$$Nest$fgetrevokeLinkRow(this.this$0)) goto L21;
            if (r3 != GroupInviteActivity.m15434$$Nest$fgetshadowRow(this.this$0)) goto L12;
            return 1;
        L12:
            if (r3 != GroupInviteActivity.m15429$$Nest$fgetlinkInfoRow(this.this$0)) goto L15;
            return 1;
        L15:
            if (r3 != GroupInviteActivity.m15430$$Nest$fgetlinkRow(this.this$0)) goto L18;
            return 2;
        L18:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            int r22 = r2.getAdapterPosition();
            if (r22 != GroupInviteActivity.m15432$$Nest$fgetrevokeLinkRow(this.this$0)) goto L5;
            return true;
        L5:
            if (r22 != GroupInviteActivity.m15427$$Nest$fgetcopyLinkRow(this.this$0)) goto L7;
            return true;
        L7:
            if (r22 != GroupInviteActivity.m15435$$Nest$fgetshareLinkRow(this.this$0)) goto L9;
            return true;
        L9:
            if (r22 == GroupInviteActivity.m15430$$Nest$fgetlinkRow(this.this$0)) goto L17;
            return false;
        L17:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r4, int r5) {
            int r0 = r4.getItemViewType();
            if (r0 == 0) goto L28;
            if (r0 != 1) goto L6;
            TextInfoPrivacyCell r42 = (TextInfoPrivacyCell) r4.itemView;
            if (r5 != GroupInviteActivity.m15434$$Nest$fgetshadowRow(this.this$0)) goto L19;
            r42.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            r42.setBackgroundDrawable(Theme.getThemedDrawableByKey(this.mContext, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
            return;
        L19:
            if (r5 != GroupInviteActivity.m15429$$Nest$fgetlinkInfoRow(this.this$0)) goto L40;
            TLRPC.Chat r52 = this.this$0.getMessagesController().getChat(Long.valueOf(GroupInviteActivity.m15426$$Nest$fgetchatId(this.this$0)));
            if (ChatObject.isChannel(r52) == true) goto L23;
        L25:
            r42.setText(LocaleController.getString(R.string.LinkInfo));
        L26:
            r42.setBackgroundDrawable(Theme.getThemedDrawableByKey(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
            return;
        L23:
            if (r52.megagroup == true) goto L25;
            r42.setText(LocaleController.getString(R.string.ChannelLinkInfo));
            goto L26
        L40:
            return;
        L6:
            if (r0 != 2) goto L42;
            TextBlockCell r43 = (TextBlockCell) r4.itemView;
            if (GroupInviteActivity.m15428$$Nest$fgetinvite(this.this$0) == null) goto L11;
            String r3 = GroupInviteActivity.m15428$$Nest$fgetinvite(this.this$0).link;
        L12:
            r43.setText(r3, false);
            return;
        L11:
            r3 = "error";
            goto L12
        L42:
            return;
        L28:
            TextSettingsCell r44 = (TextSettingsCell) r4.itemView;
            if (r5 != GroupInviteActivity.m15427$$Nest$fgetcopyLinkRow(this.this$0)) goto L33;
            r44.setText(LocaleController.getString(R.string.CopyLink), true);
            return;
        L33:
            if (r5 != GroupInviteActivity.m15435$$Nest$fgetshareLinkRow(this.this$0)) goto L37;
            r44.setText(LocaleController.getString(R.string.ShareLink), false);
            return;
        L37:
            if (r5 != GroupInviteActivity.m15432$$Nest$fgetrevokeLinkRow(this.this$0)) goto L41;
            r44.setText(LocaleController.getString(R.string.RevokeLink), true);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            if (r2 != 0) goto L4;
            View r12 = new TextSettingsCell(this.mContext);
            r12.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        L9:
            return new RecyclerListView.Holder(r12);
        L4:
            if (r2 == 1) goto L6;
            r12 = new TextBlockCell(this.mContext);
            r12.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            goto L9
        L6:
            r12 = new TextInfoPrivacyCell(this.mContext);
            goto L9
        }
    }

    /* renamed from: $r8$lambda$IJqI0u026wzAOqeHlk-vjaVgEQI, reason: not valid java name */
    public static /* synthetic */ void m15424$r8$lambda$IJqI0u026wzAOqeHlkvjaVgEQI(GroupInviteActivity r0, TLRPC.TL_error r1, TLObject r2, boolean r3) {
        r0.lambda$generateLink$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Nc6kvtzhgrqkz8sSWBgaRxvULMU(GroupInviteActivity r0, View r1, int r2) {
        r0.lambda$createView$1(r1, r2);
    }

    /* renamed from: $r8$lambda$qA3bOQifAcIxFm-CdFju2uOzhyM, reason: not valid java name */
    public static /* synthetic */ void m15425$r8$lambda$qA3bOQifAcIxFmCdFju2uOzhyM(GroupInviteActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$uCGuffHOHQhCNChkWavn2W8V8f8(GroupInviteActivity r0, boolean r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$generateLink$3(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetchatId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m15426$$Nest$fgetchatId(GroupInviteActivity r2) {
        return r2.chatId;
    }

    /* renamed from: -$$Nest$fgetcopyLinkRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15427$$Nest$fgetcopyLinkRow(GroupInviteActivity r0) {
        return r0.copyLinkRow;
    }

    /* renamed from: -$$Nest$fgetinvite, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_chatInviteExported m15428$$Nest$fgetinvite(GroupInviteActivity r0) {
        return r0.invite;
    }

    /* renamed from: -$$Nest$fgetlinkInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15429$$Nest$fgetlinkInfoRow(GroupInviteActivity r0) {
        return r0.linkInfoRow;
    }

    /* renamed from: -$$Nest$fgetlinkRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15430$$Nest$fgetlinkRow(GroupInviteActivity r0) {
        return r0.linkRow;
    }

    /* renamed from: -$$Nest$fgetloading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15431$$Nest$fgetloading(GroupInviteActivity r0) {
        return r0.loading;
    }

    /* renamed from: -$$Nest$fgetrevokeLinkRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15432$$Nest$fgetrevokeLinkRow(GroupInviteActivity r0) {
        return r0.revokeLinkRow;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15433$$Nest$fgetrowCount(GroupInviteActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetshadowRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15434$$Nest$fgetshadowRow(GroupInviteActivity r0) {
        return r0.shadowRow;
    }

    /* renamed from: -$$Nest$fgetshareLinkRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15435$$Nest$fgetshareLinkRow(GroupInviteActivity r0) {
        return r0.shareLinkRow;
    }

    public GroupInviteActivity(long r1) {
        this.chatId = r1;
    }

    private void generateLink(boolean r5) {
        this.loading = true;
        TLRPC.TL_messages_exportChatInvite r0 = new TLRPC.TL_messages_exportChatInvite();
        r0.peer = getMessagesController().getInputPeer(-this.chatId);
        int r52 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new GroupInviteActivity$$ExternalSyntheticLambda1(this, r5));
        ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(r52, this.classGuid);
        ListAdapter r4 = this.listAdapter;
        if (r4 == null) goto L6;
        r4.notifyDataSetChanged();
        return;
    }

    private /* synthetic */ void lambda$createView$0(AlertDialog r1, int r2) {
        generateLink(true);
    }

    private /* synthetic */ void lambda$createView$1(View r2, int r3) {
        if (getParentActivity() != null) goto L6;
        return;
    L6:
        if (r3 == this.copyLinkRow) goto L24;
        if (r3 == this.linkRow) goto L24;
        if (r3 != this.shareLinkRow) goto L20;
        if (this.invite == null) goto L38;
        Intent r22 = new Intent("android.intent.action.SEND");     // Catch: Exception -> L17
        r22.setType("text/plain");     // Catch: Exception -> L17
        r22.putExtra("android.intent.extra.TEXT", this.invite.link);     // Catch: Exception -> L17
        getParentActivity().startActivityForResult(Intent.createChooser(r22, LocaleController.getString(R.string.InviteToGroupByLink)), MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L17
        return;
    L17:
        e = move-exception;
        FileLog.e(e);
        return;
    L38:
        return;
    L20:
        if (r3 != this.revokeLinkRow) goto L36;
        AlertDialog.Builder r23 = new AlertDialog.Builder(getParentActivity());
        r23.setMessage(LocaleController.getString(R.string.RevokeAlert));
        r23.setTitle(LocaleController.getString(R.string.RevokeLink));
        r23.setPositiveButton(LocaleController.getString(R.string.RevokeButton), new GroupInviteActivity$$ExternalSyntheticLambda3(this));
        r23.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r23.create());
        return;
    L36:
        return;
    L24:
        if (this.invite == null) goto L39;
        ((ClipboardManager) ApplicationLoader.applicationContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", this.invite.link));     // Catch: Exception -> L28
        BulletinFactory.createCopyLinkBulletin(this).show();     // Catch: Exception -> L28
        return;
    L28:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    private /* synthetic */ void lambda$generateLink$2(TLRPC.TL_error r1, TLObject r2, boolean r3) {
        if (r1 != null) goto L9;
        this.invite = (TLRPC.TL_chatInviteExported) r2;
        if (r3 == false) goto L9;
        if (getParentActivity() != null) goto L8;
        return;
    L8:
        AlertDialog.Builder r12 = new AlertDialog.Builder(getParentActivity());
        r12.setMessage(LocaleController.getString(R.string.RevokeAlertNewLink));
        r12.setTitle(LocaleController.getString(R.string.RevokeLink));
        r12.setNegativeButton(LocaleController.getString(R.string.OK), null);
        showDialog(r12.create());
    L9:
        this.loading = false;
        this.listAdapter.notifyDataSetChanged();
    }

    private /* synthetic */ void lambda$generateLink$3(boolean r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new GroupInviteActivity$$ExternalSyntheticLambda2(this, r4, r3, r2));
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r8) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.InviteLink));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.listAdapter = new ListAdapter(this, r8);
        FrameLayout r0 = new FrameLayout(r8);
        this.fragmentView = r0;
        r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        EmptyTextProgressView r2 = new EmptyTextProgressView(r8);
        this.emptyView = r2;
        r2.showProgress();
        r0.addView(this.emptyView, LayoutHelper.createFrame(-1, -1, 51));
        RecyclerListView r22 = new RecyclerListView(r8);
        this.listView = r22;
        r22.setLayoutManager(new LinearLayoutManager(r8, 1, false));
        this.listView.setEmptyView(this.emptyView);
        this.listView.setVerticalScrollBarEnabled(false);
        r0.addView(this.listView, LayoutHelper.createFrame(-1, -1, 51));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new GroupInviteActivity$$ExternalSyntheticLambda0(this));
        return this.fragmentView;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r5, int r6, Object... r7) {
        if (r5 != NotificationCenter.chatInfoDidLoad) goto L16;
        TLRPC.ChatFull r62 = (TLRPC.ChatFull) r7[0];
        int r72 = ((Integer) r7[1]).intValue();
        if (r62.id == this.chatId) goto L7;
        return;
    L7:
        if (r72 != this.classGuid) goto L18;
        TLRPC.TL_chatInviteExported r63 = getMessagesController().getExportedInvite(this.chatId);
        this.invite = r63;
        if (r63 != null) goto L12;
        generateLink(false);
        return;
    L12:
        this.loading = false;
        ListAdapter r4 = this.listAdapter;
        if (r4 == null) goto L19;
        r4.notifyDataSetChanged();
        return;
    L19:
        return;
    L18:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{TextSettingsCell.class, TextBlockCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        ActionBar r3 = this.actionBar;
        int r4 = ThemeDescription.FLAG_BACKGROUND;
        int r19 = Theme.key_actionBarDefault;
        r1.add(new ThemeDescription(r3, r4, null, null, null, null, r19));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r19));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_progressCircle));
        int r21 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r21));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextBlockCell.class}, new String[]{"textView"}, null, null, null, r21));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.chatInfoDidLoad);
        getMessagesController().loadFullChat(this.chatId, this.classGuid, true);
        this.loading = true;
        this.linkRow = 0;
        int r0 = 1 + 1;
        this.linkInfoRow = 1;
        this.copyLinkRow = r0;
        this.revokeLinkRow = r0 + 1;
        int r1 = r0 + 3;
        this.shareLinkRow = r0 + 2;
        this.rowCount = r0 + 4;
        this.shadowRow = r1;
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.chatInfoDidLoad);
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
