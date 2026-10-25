package org.telegram.ui.community;

import android.content.Context;
import android.content.DialogInterface;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_communities;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.community.cells.CommunityPendingRequestCell;
import org.telegram.ui.community.sheet.CommunityAddOptionsSheet;

/* loaded from: classes7.dex */
public abstract class CommunityUtils {

    public static class DialogCellFactory extends UItem.UItemFactory<DialogCell> {
        static {
            UItem.UItemFactory.setup(new DialogCellFactory());
        }

        public DialogCellFactory() {
        }

        public static UItem asCell(MessagesController.CommunityPeerDialog r1, DialogCell.DialogCellDelegate r2) {
            TLRPC.User r0 = r1.user;
            if (r0 == null) goto L7;
            return asCell(r0, r2);
        L7:
            return asCell(r1.chat, r2);
        }

        public static UItem asCell(TLRPC.Chat r3, DialogCell.DialogCellDelegate r4) {
            UItem r0 = UItem.ofFactory(DialogCellFactory.class);
            if (r3 == null) goto L5;
            long r1 = -r3.id;
        L6:
            r0.longValue = r1;
            r0.id = Long.hashCode(r1);
            r0.object = r3;
            r0.object2 = r4;
            return r0;
        L5:
            r1 = 0;
            goto L6
        }

        public static UItem asCell(TLRPC.User r3, DialogCell.DialogCellDelegate r4) {
            UItem r0 = UItem.ofFactory(DialogCellFactory.class);
            if (r3 == null) goto L5;
            long r1 = r3.id;
        L6:
            r0.longValue = r1;
            r0.id = Long.hashCode(r1);
            r0.object = r3;
            r0.object2 = r4;
            return r0;
        L5:
            r1 = 0;
            goto L6
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r8, UItem r9, boolean r10, UniversalAdapter r11, UniversalRecyclerView r12) {
            DialogCell r0 = (DialogCell) r8;
            r0.setDialogCellDelegate((DialogCell.DialogCellDelegate) r9.object2);
            Object r7 = r9.object;
            boolean r102 = true;
            if ((r7 instanceof TLRPC.Chat) == false) goto L15;
            TLRPC.Chat r72 = (TLRPC.Chat) r7;
            r0.isHiddenInCommunity = ChatObject.isHiddenInCommunity(UserConfig.selectedAccount, r72);
            TLRPC.Dialog r82 = MessagesController.getInstance(UserConfig.selectedAccount).getDialog(-r72.id);
            if (r82 == null) goto L8;
            r102 = false;
        L8:
            r0.insideCommunityListNoDialog = r102;
            if (r82 == null) goto L12;
            r0.setCustomMessageWithoutRebuild(null);
            r0.setDialog(r82, 0, 0);
            return;
        L12:
            r0.setCustomMessageWithoutRebuild(LocaleController.formatPluralString("Members", r72.participants_count, new Object[0]));
            r0.setDialog(-r72.id, null, 0, false, false);
            return;
        L15:
            if ((r7 instanceof TLRPC.User) == false) goto L26;
            TLRPC.User r73 = (TLRPC.User) r7;
            r0.isHiddenInCommunity = ChatObject.isHiddenInCommunity(UserConfig.selectedAccount, r73);
            TLRPC.Dialog r83 = MessagesController.getInstance(UserConfig.selectedAccount).getDialog(r73.id);
            if (r83 == null) goto L20;
            r102 = false;
        L20:
            r0.insideCommunityListNoDialog = r102;
            if (r83 == null) goto L24;
            r0.setCustomMessageWithoutRebuild(null);
            r0.setDialog(r83, 0, 0);
            return;
        L24:
            r0.setCustomMessageWithoutRebuild(LocaleController.getString(R.string.Bot));
            r0.setDialog(r73.id, null, 0, false, false);
            return;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean contentsEquals(UItem r1, UItem r2) {
            return equals(r1, r2);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public DialogCell createView(Context r8, RecyclerListView r9, int r10, int r11, Theme.ResourcesProvider r12) {
            DialogCell r0 = new DialogCell(null, r8, false, false, r10, r12);
            r0.insideCommunityList = true;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean equals(UItem r1, UItem r2) {
            if (r1.id != r2.id) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public static class PendingRequests implements CommunityPendingRequestCell.ClickDelegate {
        private final BulletinFactory bulletinFactory;
        private TLRPC.Chat community;
        private final long communityId;
        private final Context context;
        private final int currentAccount;
        private Delegate delegate;
        private Runnable doCommitRunnable;
        private boolean finished;
        private final LongSparseArray<Void> hiddenJoinRequests;
        private long lastViewTime;
        private boolean loading;
        private String nextOffset;
        private ArrayList<TL_communities.CommunityPeerRequest> pendingRequests;
        private AlertDialog progressDialog;
        private int reqId;
        private final Theme.ResourcesProvider resourcesProvider;
        private int totalCount;
        private int unreadPendingRequests;

        public interface Delegate {
            void close();

            void onClickGroupOwner(long r1);

            void updateAdapter();
        }

        public static /* synthetic */ void $r8$lambda$0xn4cev8KtP6jAqp9uSklFtCs4o(PendingRequests r0, DialogInterface r1) {
            r0.lambda$onResolveAllJoinRequests$5(r1);
        }

        public static /* synthetic */ void $r8$lambda$IfIDt6SP26QwxxZOLjm3fdiLzO8(PendingRequests r0, TLRPC.Bool r1, TLRPC.TL_error r2) {
            r0.lambda$onResolveJoinRequest$1(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$O5NPBkwSB_hK_ksdXnLk5huWmak(PendingRequests r0, TLRPC.Bool r1, TLRPC.TL_error r2) {
            r0.lambda$onResolveAllJoinRequests$6(r1, r2);
        }

        /* renamed from: $r8$lambda$WBUE8EcTSG7VIQvHByJC8u7-QKI, reason: not valid java name */
        public static /* synthetic */ void m21564$r8$lambda$WBUE8EcTSG7VIQvHByJC8u7QKI(PendingRequests r0, TL_communities.PeerLinkRequests r1, TLRPC.TL_error r2) {
            r0.lambda$loadNext$0(r1, r2);
        }

        /* renamed from: $r8$lambda$_6BNtCPE3XXdzA-r5LuPtvDU8Rs, reason: not valid java name */
        public static /* synthetic */ void m21565$r8$lambda$_6BNtCPE3XXdzAr5LuPtvDU8Rs(PendingRequests r0, long r1, boolean r3) {
            r0.lambda$onResolveJoinRequest$2(r1, r3);
        }

        /* renamed from: $r8$lambda$lzBm1tze-l3WU1BB5qnrcvjXRDk, reason: not valid java name */
        public static /* synthetic */ void m21566$r8$lambda$lzBm1tzel3WU1BB5qnrcvjXRDk(PendingRequests r0, long r1) {
            r0.lambda$onResolveJoinRequest$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$uym3vQ8gRH4VL8O_eDBOIh7HrGI(PendingRequests r0, boolean r1) {
            r0.lambda$onResolveAllJoinRequests$4(r1);
        }

        public PendingRequests(Context r2, Theme.ResourcesProvider r3, BulletinFactory r4, int r5, long r6) {
            this.hiddenJoinRequests = new LongSparseArray();
            this.pendingRequests = new ArrayList();
            this.context = r2;
            this.resourcesProvider = r3;
            this.bulletinFactory = r4;
            this.currentAccount = r5;
            this.communityId = r6;
            this.community = MessagesController.getInstance(r5).getChat(Long.valueOf(r6));
            this.lastViewTime = MessagesController.getMainSettings(r5).getLong("community_requests_last_view_time_" + r6, 0);
        }

        private void calcUnreadPendingRequests() {
            int r0 = 0;
            this.unreadPendingRequests = 0;
            ArrayList<TL_communities.CommunityPeerRequest> r1 = this.pendingRequests;
            if (r1 == null) goto L14;
            int r12 = r1.size();
        L6:
            if (r0 >= r12) goto L19;
            if (this.hiddenJoinRequests.containsKey(DialogObject.getPeerDialogId(this.pendingRequests.get(r0).peer)) == true) goto L13;
            if (r2.date <= this.lastViewTime) goto L20;
            this.unreadPendingRequests++;
            goto L13
        L20:
            return;
        L13:
            r0 = r0 + 1;
            goto L6
        L19:
            return;
        }

        private /* synthetic */ void lambda$loadNext$0(TL_communities.PeerLinkRequests r3, TLRPC.TL_error r4) {
            boolean r42 = false;
            this.loading = false;
            if (r3 == null) goto L15;
            ArrayList<TL_communities.CommunityPeerRequest> r0 = this.pendingRequests;
            if (r0 != null) goto L7;
            this.pendingRequests = new ArrayList(r3.requests);
        L8:
            String r02 = r3.next_offset;
            this.nextOffset = r02;
            this.totalCount = r3.total_count;
            if (r02 != null) goto L11;
            r42 = true;
        L11:
            this.finished = r42;
            calcUnreadPendingRequests();
            Delegate r2 = this.delegate;
            if (r2 == null) goto L16;
            r2.updateAdapter();
            return;
        L16:
            return;
        L7:
            r0.addAll(r3.requests);
            goto L8
        }

        private /* synthetic */ void lambda$onResolveAllJoinRequests$4(boolean r2) {
            onResolveAllJoinRequests(r2, false);
        }

        private /* synthetic */ void lambda$onResolveAllJoinRequests$5(DialogInterface r3) {
            ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, true);
            this.progressDialog = null;
            this.reqId = 0;
        }

        private /* synthetic */ void lambda$onResolveAllJoinRequests$6(TLRPC.Bool r1, TLRPC.TL_error r2) {
            this.progressDialog.dismiss();
            this.progressDialog = null;
            this.reqId = 0;
            if (r2 == null) goto L6;
            this.bulletinFactory.showForError(r2);
            return;
        L6:
            Delegate r0 = this.delegate;
            if (r0 == null) goto L10;
            r0.close();
            return;
        }

        private /* synthetic */ void lambda$onResolveJoinRequest$1(TLRPC.Bool r1, TLRPC.TL_error r2) {
            if (r2 == null) goto L5;
            this.bulletinFactory.showForError(r2);
            return;
        }

        private /* synthetic */ void lambda$onResolveJoinRequest$2(long r9, boolean r11) {
            this.doCommitRunnable = null;
            this.hiddenJoinRequests.remove(r9);
            ArrayList<TL_communities.CommunityPeerRequest> r0 = this.pendingRequests;
            if (r0 == null) goto L10;
            int r02 = r0.size() - 1;
        L5:
            if (r02 < 0) goto L10;
            if (DialogObject.getPeerDialogId(this.pendingRequests.get(r02).peer) != r9) goto L9;
            this.pendingRequests.remove(r02);
        L9:
            r02 = r02 - 1;
        L10:
            calcUnreadPendingRequests();
            Delegate r03 = this.delegate;
            if (r03 == null) goto L13;
            r03.updateAdapter();
        L13:
            MessagesController.getInstance(this.currentAccount).resolveCommunityJoinPendingRequest(this.communityId, r9, !r11, new CommunityUtils$PendingRequests$$ExternalSyntheticLambda6(this));
        }

        private /* synthetic */ void lambda$onResolveJoinRequest$3(long r2) {
            this.doCommitRunnable = null;
            this.hiddenJoinRequests.remove(r2);
            this.totalCount++;
            calcUnreadPendingRequests();
            Delegate r1 = this.delegate;
            if (r1 == null) goto L6;
            r1.updateAdapter();
            return;
        }

        private void onResolveAllJoinRequests(boolean r8, boolean r9) {
            if (this.progressDialog == null) goto L5;
            return;
        L5:
            if (this.reqId != 0) goto L31;
            if (r9 == false) goto L26;
            Context r1 = this.context;
            Theme.ResourcesProvider r2 = this.resourcesProvider;
            if (r8 == false) goto L11;
            int r92 = R.string.CommunityAddAllChatsTitle;
        L12:
            String r3 = LocaleController.getString(r92);
            if (r8 == false) goto L15;
            String r93 = "CommunityAddAllChatsMessage";
        L16:
            SpannableStringBuilder r4 = AndroidUtilities.replaceTags(LocaleController.formatPluralString(r93, this.totalCount, new Object[0]));
            if (r8 == false) goto L19;
            int r94 = R.string.Add;
        L20:
            AlertDialog r7 = AlertsCreator.createSimpleConfirmAlert(r1, r2, r3, r4, LocaleController.getString(r94), new CommunityUtils$PendingRequests$$ExternalSyntheticLambda0(this, r8));
            r7.show();
            if (r8 == true) goto L29;
            TextView r72 = (TextView) r7.getButton(-1);
            if (r72 == null) goto L30;
            r72.setTextColor(Theme.getColor(Theme.key_text_RedBold));
            return;
        L30:
            return;
        L29:
            return;
        L19:
            r94 = R.string.Decline;
            goto L20
        L15:
            r93 = "CommunityDeclineAllMessage";
            goto L16
        L11:
            r92 = R.string.CommunityDeclineAllTitle;
            goto L12
        L26:
            commit();
            AlertDialog r95 = new AlertDialog(this.context, 3, this.resourcesProvider);
            this.progressDialog = r95;
            r95.setOnCancelListener(new CommunityUtils$PendingRequests$$ExternalSyntheticLambda1(this));
            this.progressDialog.showDelayed(500);
            this.reqId = MessagesController.getInstance(this.currentAccount).resolveCommunityAllJoinPendingRequests(this.communityId, !r8, new CommunityUtils$PendingRequests$$ExternalSyntheticLambda2(this));
            return;
        }

        private void onResolveJoinRequest(long r7, boolean r9) {
            this.hiddenJoinRequests.put(r7, null);
            this.totalCount--;
            calcUnreadPendingRequests();
            Delegate r0 = this.delegate;
            if (r0 == null) goto L5;
            r0.updateAdapter();
        L5:
            if (r9 == false) goto L7;
            int r02 = R.string.CommunityRequestApprovedToast;
        L8:
            SpannableStringBuilder r03 = AndroidUtilities.replaceTags(LocaleController.formatString(r02, new Object[]{DialogObject.getShortName(this.currentAccount, r7)}));
            commit();
            this.doCommitRunnable = new CommunityUtils$PendingRequests$$ExternalSyntheticLambda4(this, r7, r9);
            Bulletin.UsersLayout r92 = new Bulletin.UsersLayout(this.context, false, this.resourcesProvider);
            TLObject r2 = MessagesController.getInstance(this.currentAccount).getUserOrChat(r7);
            if (r2 == null) goto L11;
            r92.avatarsImageView.setCount(1);
            r92.avatarsImageView.setObject(0, UserConfig.selectedAccount, r2);
            int r22 = 1;
        L12:
            r92.avatarsImageView.setTranslationX(AndroidUtilities.dp(7.0f));
            r92.avatarsImageView.setScaleX(1.333f);
            r92.avatarsImageView.setScaleY(1.333f);
            r92.avatarsImageView.commitTransition(false);
            r92.textView.setSingleLine(false);
            r92.textView.setMaxLines(2);
            r92.textView.setTextSize(1, 14.0f);
            r92.textView.setText(r03);
            if ((r92.textView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) == false) goto L19;
            int r04 = AndroidUtilities.dp(74 - ((3 - r22) * 12));
            boolean r3 = LocaleController.isRTL;
            TextView r4 = r92.textView;
            if (r3 == false) goto L17;
            ((ViewGroup.MarginLayoutParams) r4.getLayoutParams()).rightMargin = r04;
            goto L19
        L17:
            ((ViewGroup.MarginLayoutParams) r4.getLayoutParams()).leftMargin = r04;
        L19:
            if (LocaleController.isRTL == false) goto L21;
            r92.avatarsImageView.setTranslationX(AndroidUtilities.dp(32 - ((r22 - 1) * 12)));
        L21:
            r92.setButton(new Bulletin.UndoButton(this.context, true, true, this.resourcesProvider).setText(LocaleController.getString(R.string.UndoNoCaps)).setUndoAction(new CommunityUtils$PendingRequests$$ExternalSyntheticLambda5(this, r7)).setDelayedAction(this.doCommitRunnable));
            this.bulletinFactory.create(r92, 5000).show();
            return;
        L11:
            r22 = 0;
            goto L12
        L7:
            r02 = R.string.CommunityRequestDeclinedToast;
            goto L8
        }

        public void checkLoadNext(UniversalRecyclerView r2) {
            if (this.loading == false) goto L5;
            return;
        L5:
            if (this.finished == false) goto L8;
            return;
        L8:
            if ((r2.layoutManager.findLastVisibleItemPosition() + 10) <= r2.adapter.getItemCount()) goto L12;
            loadNext();
            return;
        }

        public void commit() {
            Runnable r0 = this.doCommitRunnable;
            if (r0 == null) goto L5;
            r0.run();
        L5:
            this.doCommitRunnable = null;
        }

        public void fillItems(ArrayList<UItem> r4) {
            ArrayList<TL_communities.CommunityPeerRequest> r0 = this.pendingRequests;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isEmpty() == true) goto L13;
            CommunityUtils.fillPendingRequests(this.currentAccount, r4, this.pendingRequests, this.hiddenJoinRequests, this);
            if (this.finished == true) goto L12;
            r4.add(UItem.asFlicker(29));
            return;
        L12:
            return;
        }

        public int getTotalCount() {
            return this.totalCount;
        }

        public int getUnreadCount() {
            return this.unreadPendingRequests;
        }

        public boolean isFinished() {
            return this.finished;
        }

        public boolean isSingle() {
            if (this.finished == true) goto L5;
            return false;
        L5:
            if (this.totalCount != 1) goto L13;
            ArrayList<TL_communities.CommunityPeerRequest> r2 = this.pendingRequests;
            if (r2 != null) goto L9;
            return false;
        L9:
            if (r2.size() != 1) goto L15;
            return true;
        L15:
            return false;
        L13:
            return false;
        }

        public void loadNext() {
            if (this.loading == false) goto L5;
            return;
        L5:
            if (this.finished == false) goto L7;
            return;
        L7:
            if (ChatObject.canUserDoAdminAction(this.community, 27) == false) goto L13;
            this.loading = true;
            MessagesController.getInstance(this.currentAccount).fetchCommunityPendingJoinRequests(this.communityId, this.nextOffset, new CommunityUtils$PendingRequests$$ExternalSyntheticLambda3(this));
            return;
        }

        public void markAsViewed() {
            long r0 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
            this.lastViewTime = r0;
            MessagesController.getMainSettings(this.currentAccount).edit().putLong("community_requests_last_view_time_" + this.communityId, r0).apply();
            calcUnreadPendingRequests();
        }

        @Override // org.telegram.ui.community.cells.CommunityPendingRequestCell.ClickDelegate
        public void onClickApprove(long r2) {
            onResolveJoinRequest(r2, true);
        }

        @Override // org.telegram.ui.community.cells.CommunityPendingRequestCell.ClickDelegate
        public void onClickDecline(long r2) {
            onResolveJoinRequest(r2, false);
        }

        @Override // org.telegram.ui.community.cells.CommunityPendingRequestCell.ClickDelegate
        public void onClickGroupOwner(long r1) {
            Delegate r0 = this.delegate;
            if (r0 == null) goto L6;
            r0.onClickGroupOwner(r1);
            return;
        }

        public void onResolveAllJoinRequests(boolean r2) {
            onResolveAllJoinRequests(r2, true);
        }

        public void setDelegate(Delegate r1) {
            this.delegate = r1;
        }
    }

    /* renamed from: $r8$lambda$8dXqhjo-QeLjKgpGYrpZ1Vx1dHc, reason: not valid java name */
    public static /* synthetic */ void m21560$r8$lambda$8dXqhjoQeLjKgpGYrpZ1Vx1dHc(int r0, int r1, AlertDialog[] r2, DialogInterface r3) {
        ConnectionsManager.getInstance(r0).cancelRequest(r1, true);
        r2[0] = null;
    }

    public static /* synthetic */ void $r8$lambda$F03GPPe9DR9O_vJh6ow94AUudyw(BaseFragment r1, long r2, TLRPC.Bool r4, TLRPC.TL_error r5) {
        if (r5 != null) goto L4;
        onCommunityLinkSuccess(r1, -r2, 1);
        return;
    L4:
        if (TextUtils.equals("COMMUNITY_REQUEST_CREATED", r5.text) == false) goto L7;
        onCommunityLinkSuccess(r1, -r2, 2);
        return;
    L7:
        BulletinFactory.of(r1).showForError(r5);
    }

    /* renamed from: $r8$lambda$L38f6i3Rz-4OotbEnUhizO9g0eQ, reason: not valid java name */
    public static /* synthetic */ void m21561$r8$lambda$L38f6i3Rz4OotbEnUhizO9g0eQ(AlertDialog r4, BaseFragment r5, int r6, long r7, boolean r9, long r10) {
        r4.dismiss();
        if (r10 != 0) goto L5;
        return;
    L5:
        linkToCommunityWithoutConvert(r5, r6, r10, r7, r9);
    }

    public static /* synthetic */ void $r8$lambda$V7osAp95jpxjNUGaG8LtdLOutf8(int r1, ChatActivity r2, boolean r3) {
        if (r1 == 2) goto L5;
        r2.onPageDownClicked(true);
        r2.startFireworks();
    L5:
        showCommunityLinkSuccessToast(BulletinFactory.of(r2), r1, r3);
    }

    /* renamed from: $r8$lambda$mp-eruEnk74TvbVNSIns2W07vRo, reason: not valid java name */
    public static /* synthetic */ void m21562$r8$lambda$mperuEnk74TvbVNSIns2W07vRo(BaseFragment r6, TLRPC.Chat r7, int r8, TLRPC.Chat r9) {
        r6.showDialog(new CommunityAddOptionsSheet(r6.getContext(), r7, -r9.id, new CommunityUtils$$ExternalSyntheticLambda3(r6, r8, r9, r7)));
    }

    /* renamed from: $r8$lambda$nraCHsVClHd7uy0-9LZY5LtrLA0, reason: not valid java name */
    public static /* synthetic */ void m21563$r8$lambda$nraCHsVClHd7uy09LZY5LtrLA0(AlertDialog[] r2, BaseFragment r3, int r4, TLRPC.Chat r5, ArrayList r6, TLRPC.TL_error r7) {
        AlertDialog r1 = r2[0];
        if (r1 == null) goto L5;
        r1.dismiss();
        r2[0] = null;
    L5:
        if (r7 == null) goto L8;
        BulletinFactory.of(r3).showForError(r7);
        return;
    L8:
        if (r6 != null) goto L10;
        return;
    L10:
        if (r6.isEmpty() == false) goto L13;
        BulletinFactory.of(r3).createSimpleBulletin(R.raw.info, LocaleController.getString(R.string.CommunityNoChatsToAdd)).show();
        return;
    L13:
        showChatsToAddSheet(r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$qoYnhsjMxmmpmirw26LOZCaGsak(BaseFragment r6, int r7, TLRPC.Chat r8, TLRPC.Chat r9, Boolean r10) {
        linkToCommunityAndConvertIfNeeded(r6, r7, r8, r9.id, r10.booleanValue());
    }

    public static CharSequence buildServiceMessageText(MessageObject r8, String r9, String r10, boolean r11, boolean r12) {
        TLRPC.Message r0 = r8.messageOwner;
        TLRPC.TL_messageActionChangeCommunity r1 = (TLRPC.TL_messageActionChangeCommunity) r0.action;
        boolean r2 = false;
        if (DialogObject.getPeerDialogId(r0.peer_id) != DialogObject.getPeerDialogId(r0.from_id)) goto L5;
        boolean r02 = true;
    L7:
        if (r1.community_id != 0) goto L9;
        r2 = true;
    L9:
        if (r02 == false) goto L26;
        if (r2 == false) goto L18;
        if (r12 == false) goto L13;
        int r82 = R.string.CommunityServiceMessageBotRemovedUnknown;
    L17:
        return AndroidUtilities.replaceTags(LocaleController.getString(r82));
    L13:
        if (r11 == false) goto L15;
        r82 = R.string.CommunityServiceMessageChannelRemovedUnknown;
        goto L17
    L15:
        r82 = R.string.CommunityServiceMessageGroupRemovedUnknown;
        goto L17
    L18:
        if (r12 == false) goto L20;
        int r83 = R.string.CommunityServiceMessageBotAddedUnknown;
    L24:
        return AndroidUtilities.replaceTags(LocaleController.formatString(r83, new Object[]{r9}));
    L20:
        if (r11 == false) goto L22;
        r83 = R.string.CommunityServiceMessageChannelAddedUnknown;
        goto L24
    L22:
        r83 = R.string.CommunityServiceMessageGroupAddedUnknown;
        goto L24
    L26:
        if (r8.isOut() == false) goto L42;
        if (r2 == false) goto L35;
        if (r12 == false) goto L30;
        int r84 = R.string.CommunityServiceMessageBotYouRemoved;
    L34:
        return AndroidUtilities.replaceTags(LocaleController.getString(r84));
    L30:
        if (r11 == false) goto L32;
        r84 = R.string.CommunityServiceMessageChannelYouRemoved;
        goto L34
    L32:
        r84 = R.string.CommunityServiceMessageGroupYouRemoved;
        goto L34
    L35:
        if (r12 == false) goto L37;
        int r85 = R.string.CommunityServiceMessageBotYouAdded;
    L41:
        return AndroidUtilities.replaceTags(LocaleController.formatString(r85, new Object[]{r9}));
    L37:
        if (r11 == false) goto L39;
        r85 = R.string.CommunityServiceMessageChannelYouAdded;
        goto L41
    L39:
        r85 = R.string.CommunityServiceMessageGroupYouAdded;
        goto L41
    L42:
        if (r2 == false) goto L50;
        if (r12 == false) goto L45;
        int r86 = R.string.CommunityServiceMessageBotRemoved;
    L49:
        return AndroidUtilities.replaceTags(LocaleController.formatString(r86, new Object[]{r10}));
    L45:
        if (r11 == false) goto L47;
        r86 = R.string.CommunityServiceMessageChannelRemoved;
        goto L49
    L47:
        r86 = R.string.CommunityServiceMessageGroupRemoved;
        goto L49
    L50:
        if (r12 == false) goto L52;
        int r87 = R.string.CommunityServiceMessageBotAdded;
    L56:
        return AndroidUtilities.replaceTags(LocaleController.formatString(r87, new Object[]{r10, r9}));
    L52:
        if (r11 == false) goto L54;
        r87 = R.string.CommunityServiceMessageChannelAdded;
        goto L56
    L54:
        r87 = R.string.CommunityServiceMessageGroupAdded;
        goto L56
    L5:
        r02 = false;
        goto L7
    }

    public static void fillLinkedPeers(int r4, ArrayList<UItem> r5, DialogCell.DialogCellDelegate r6, long r7, boolean r9) {
        MessagesController.CommunityPeersDialog r42 = MessagesController.getInstance(r4).buildCommunityPeers(r7);
        if (r42 == null) goto L40;
        int r8 = 0;
        if (r42.chatsYouAreIn.isEmpty() == true) goto L11;
        r5.add(UItem.asHeader(21, LocaleController.getString(R.string.CommunitySectionChatsYouAreIn)));
        ArrayList<MessagesController.CommunityPeerDialog> r72 = r42.chatsYouAreIn;
        int r0 = r72.size();
        int r1 = 0;
    L8:
        if (r1 >= r0) goto L10;
        MessagesController.CommunityPeerDialog r2 = r72.get(r1);
        r1 = r1 + 1;
        r5.add(DialogCellFactory.asCell(r2, r6));
        goto L8
    L10:
        boolean r73 = r9;
    L13:
        if (r42.chatsYouCanView.isEmpty() == true) goto L21;
        if (r73 == false) goto L16;
        r5.add(UItem.asSpace(22, AndroidUtilities.dp(12.0f)));
    L16:
        r5.add(UItem.asHeader(23, LocaleController.getString(R.string.CommunitySectionChatsYouCanView)));
        ArrayList<MessagesController.CommunityPeerDialog> r74 = r42.chatsYouCanView;
        int r02 = r74.size();
        int r22 = 0;
    L17:
        if (r22 >= r02) goto L19;
        MessagesController.CommunityPeerDialog r3 = r74.get(r22);
        r22 = r22 + 1;
        r5.add(DialogCellFactory.asCell(r3, r6));
        goto L17
    L19:
        r73 = r9;
    L21:
        if (r42.chatsYouCanJoin.isEmpty() == true) goto L27;
        if (r73 == false) goto L24;
        r5.add(UItem.asSpace(24, AndroidUtilities.dp(12.0f)));
    L24:
        r5.add(UItem.asHeader(25, LocaleController.getString(R.string.CommunitySectionChatsYouCanRequestToJoin)));
        ArrayList<MessagesController.CommunityPeerDialog> r75 = r42.chatsYouCanJoin;
        int r03 = r75.size();
        int r23 = 0;
    L25:
        if (r23 >= r03) goto L29;
        MessagesController.CommunityPeerDialog r32 = r75.get(r23);
        r23 = r23 + 1;
        r5.add(DialogCellFactory.asCell(r32, r6));
    L29:
        if (r42.chatsOther.isEmpty() == true) goto L35;
        if (r9 == false) goto L32;
        r5.add(UItem.asSpace(26, AndroidUtilities.dp(12.0f)));
    L32:
        r5.add(UItem.asHeader(27, LocaleController.getString(R.string.CommunitySectionHiddenChats)));
        ArrayList<MessagesController.CommunityPeerDialog> r43 = r42.chatsOther;
        int r76 = r43.size();
    L33:
        if (r8 >= r76) goto L41;
        MessagesController.CommunityPeerDialog r92 = r43.get(r8);
        r8 = r8 + 1;
        r5.add(DialogCellFactory.asCell(r92, r6));
        goto L33
    L41:
        return;
    L35:
        return;
    L27:
        r9 = r73;
        goto L29
    L11:
        r73 = false;
        goto L13
    }

    public static void fillPendingRequests(int r11, ArrayList<UItem> r12, ArrayList<TL_communities.CommunityPeerRequest> r13, LongSparseArray<Void> r14, CommunityPendingRequestCell.ClickDelegate r15) {
        if (r13 != null) goto L4;
        return;
    L4:
        if (r13.isEmpty() == true) goto L24;
        int r0 = r13.size();
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L25;
        TL_communities.CommunityPeerRequest r3 = r13.get(r2);
        long r5 = DialogObject.getPeerDialogId(r3.peer);
        if (r14 != null) goto L11;
    L13:
        TLRPC.User r7 = MessagesController.getInstance(r11).getUser(Long.valueOf(r3.requested_by));
        boolean r8 = !r3.visible;
        if (r2 >= (r0 - 1)) goto L17;
        boolean r10 = true;
    L16:
        CommunityPendingRequestCell.ClickDelegate r9 = r15;
        r12.add(CommunityPendingRequestCell.Factory.asPendingRequest(r5, r7, r8, r9, r10));
    L19:
        r2 = r2 + 1;
        r15 = r9;
        goto L7
    L17:
        r10 = false;
        goto L16
    L11:
        if (r14.containsKey(r5) == false) goto L13;
        r9 = r15;
        goto L19
    L25:
        return;
    }

    public static CommunityChatType getCommunityChatType(int r9, long r10) {
        TLRPC.Dialog r3 = null;
        if (r10 <= 0) goto L8;
        TLRPC.User r2 = MessagesController.getInstance(r9).getUser(Long.valueOf(r10));
        if (r2 != null) goto L7;
        return null;
    L7:
        long r5 = r2.linked_community_id;
        TLRPC.User r4 = r2;
        TLRPC.Chat r22 = null;
    L13:
        if (r5 != 0) goto L15;
        return null;
    L15:
        TLRPC.ChatFull r0 = MessagesController.getInstance(r9).getChatFull(r5);
        if (r0 == null) goto L28;
        ArrayList<TL_communities.CommunityPeer> r02 = r0.linked_peers;
        if (r02 == null) goto L28;
        int r1 = r02.size();
        int r52 = 0;
    L21:
        if (r52 >= r1) goto L28;
        TL_communities.CommunityPeer r6 = r02.get(r52);
        r52 = r52 + 1;
        TL_communities.CommunityPeer r62 = r6;
        if (DialogObject.getPeerDialogId(r62.peer) != r10) goto L21;
        if (r4 == null) goto L27;
        r3 = MessagesController.getInstance(r9).getDialog(r4.id);
    L27:
        return getCommunityChatType(r22, r4, r3, r62);
    L28:
        return null;
    L8:
        r22 = MessagesController.getInstance(r9).getChat(Long.valueOf(-r10));
        if (r22 != null) goto L11;
        return null;
    L11:
        r5 = r22.linked_community_id;
        r4 = null;
        goto L13
    }

    public static CommunityChatType getCommunityChatType(TLRPC.Chat r1, TLRPC.User r2, TLRPC.Dialog r3, TL_communities.CommunityPeer r4) {
        if (r4 != null) goto L5;
        return null;
    L5:
        if (r2 == null) goto L11;
        if (r3 == null) goto L10;
        return CommunityChatType.YouAreIn;
    L10:
        return CommunityChatType.YouCanView;
    L11:
        if (r1 != null) goto L13;
        return null;
    L13:
        if (ChatObject.isInChat(r1) == false) goto L17;
        return CommunityChatType.YouAreIn;
    L17:
        if (ChatObject.isPublic(r1) == true) goto L28;
        if (r4.can_view_history == true) goto L28;
        if (ChatObject.isCommunityPeerHidden(r4) == false) goto L26;
        return CommunityChatType.HiddenUnavailable;
    L26:
        return CommunityChatType.YouCanSendJoinRequest;
    L28:
        return CommunityChatType.YouCanView;
    }

    public static void linkToCommunityAndConvertIfNeeded(BaseFragment r10, int r11, TLRPC.Chat r12, long r13, boolean r15) {
        if (ChatObject.isChannel(r12) == true) goto L6;
        AlertDialog r2 = new AlertDialog(r10.getContext(), 3);
        r2.showDelayed(250);
        MessagesController.getInstance(r11).convertToMegaGroup(r10.getParentActivity(), r12.id, r10, new CommunityUtils$$ExternalSyntheticLambda4(r2, r10, r11, r13, r15));
        return;
    L6:
        linkToCommunityWithoutConvert(r10, r11, r12.id, r13, r15);
    }

    public static void linkToCommunityWithoutConvert(BaseFragment r3, int r4, long r5, long r7, boolean r9) {
        MessagesController.getInstance(r4).linkCommunity(-r5, r7, r9, new CommunityUtils$$ExternalSyntheticLambda5(r3, r5));
    }

    public static void onCommunityLinkSuccess(BaseFragment r8, long r9, int r11) {
        List<BaseFragment> r1 = null;
        if (AndroidUtilities.isTablet() == true) goto L17;
        INavigationLayout r0 = r8.getParentLayout();
        if (r0 == null) goto L15;
        List<BaseFragment> r3 = r0.getFragmentStack();
        int r4 = r3.size() - 2;
    L7:
        if (r4 < 0) goto L14;
        BaseFragment r5 = r3.get(r4);
        if ((r5 instanceof ChatActivity) == false) goto L13;
        ChatActivity r52 = (ChatActivity) r5;
        if (r52.getDialogId() != r9) goto L13;
    L12:
        r1 = r3;
    L18:
        boolean r92 = ChatObject.isChannelAndNotMegaGroup(r9, r8.getCurrentAccount());
        if (r4 == (-1)) goto L26;
        int r10 = r1.size() - 2;
    L21:
        if (r10 <= r4) goto L23;
        r0.removeFragmentFromStack(r1.get(r10));
        r10 = r10 - 1;
        goto L21
    L23:
        r8.finishFragment();
        AndroidUtilities.runOnUIThread(new CommunityUtils$$ExternalSyntheticLambda6(r11, r52, r92), 250);
        return;
    L26:
        if ((r8 instanceof DialogsActivity) == true) goto L28;
        r8.finishFragment();
    L28:
        showCommunityLinkSuccessToast(BulletinFactory.global(), r11, r92);
        return;
    L13:
        r4 = r4 - 1;
        goto L7
    L14:
        r52 = null;
        r4 = -1;
        goto L12
    L15:
        r52 = null;
    L16:
        r4 = -1;
        goto L18
    L17:
        r0 = null;
        r52 = null;
        goto L16
    }

    private static void showChatsToAddSheet(BaseFragment r7, int r8, TLRPC.Chat r9, ArrayList<TLRPC.Chat> r10) {
        if (r10.isEmpty() == true) goto L6;
        r7.showDialog(new CommunitySheet(r7, 0, r10, new CommunityUtils$$ExternalSyntheticLambda2(r7, r9, r8)));
        return;
    L6:
        BulletinFactory.of(r7).createSimpleBulletin(R.raw.info, _UrlKt.FRAGMENT_ENCODE_SET).show();
    }

    public static void showChatsToAddToCommunity(AlertDialog[] r4, BaseFragment r5, int r6, TLRPC.Chat r7) {
        if (r4[0] == null) goto L5;
        return;
    L5:
        int r72 = MessagesController.getInstance(r6).fetchChatsToAddToCommunity(new CommunityUtils$$ExternalSyntheticLambda0(r4, r5, r6, r7));
        ConnectionsManager.getInstance(r6).bindRequestToGuid(r72, r5.getClassGuid());
        AlertDialog r1 = new AlertDialog(r5.getContext(), 3);
        r4[0] = r1;
        r1.showDelayed(500);
        r4[0].setOnCancelListener(new CommunityUtils$$ExternalSyntheticLambda1(r6, r72, r4));
    }

    public static void showCommunityLinkSuccessToast(BulletinFactory r3, int r4, boolean r5) {
        if (r4 != 2) goto L5;
        int r1 = R.raw.timer_toast;
    L6:
        if (r4 != 2) goto L8;
        int r0 = 24;
    L9:
        if (r4 != 0) goto L12;
        String r42 = LocaleController.getString(R.string.CommunityCommunityCreated);
    L18:
        r3.createSimpleBulletin(r1, r42, r0).show();
        return;
    L12:
        if (r4 != 1) goto L17;
        if (r5 == false) goto L15;
        int r43 = R.string.CommunityCommunityJoinedChannel;
    L16:
        r42 = LocaleController.getString(r43);
        goto L18
    L15:
        r43 = R.string.CommunityCommunityJoinedGroup;
        goto L16
    L17:
        r42 = LocaleController.getString(R.string.CommunityCommunityPending);
        goto L18
    L8:
        r0 = 36;
        goto L9
    L5:
        r1 = R.raw.contact_check;
        goto L6
    }
}
