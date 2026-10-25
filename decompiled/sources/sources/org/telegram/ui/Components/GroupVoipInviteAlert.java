package org.telegram.ui.Components;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Adapters.SearchAdapterHelper;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.ManageChatTextCell;
import org.telegram.ui.Cells.ManageChatUserCell;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public class GroupVoipInviteAlert extends UsersAlertBase {
    private int addNewRow;
    private ArrayList<TLObject> contacts;
    private boolean contactsEndReached;
    private int contactsEndRow;
    private int contactsHeaderRow;
    private LongSparseArray<TLObject> contactsMap;
    private int contactsStartRow;
    private TLRPC.Chat currentChat;
    private int delayResults;
    private GroupVoipInviteAlertDelegate delegate;
    private int emptyRow;
    private boolean firstLoaded;
    private int flickerProgressRow;
    private LongSparseArray<TLRPC.GroupCallParticipant> ignoredUsers;
    private TLRPC.ChatFull info;
    private HashSet<Long> invitedUsers;
    private int lastRow;
    private boolean loadingUsers;
    private int membersHeaderRow;
    private ArrayList<TLObject> participants;
    private int participantsEndRow;
    private LongSparseArray<TLObject> participantsMap;
    private int participantsStartRow;
    private int rowCount;
    private final SearchAdapter searchAdapter;
    private boolean showContacts;

    public interface GroupVoipInviteAlertDelegate {
        void copyInviteLink();

        void inviteUser(long r1);

        void needOpenSearch(MotionEvent r1, EditTextBoldCursor r2);
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ GroupVoipInviteAlert this$0;

        public ListAdapter(GroupVoipInviteAlert r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        public TLObject getItem(int r2) {
            if (r2 < GroupVoipInviteAlert.m10881$$Nest$fgetparticipantsStartRow(this.this$0)) goto L9;
            if (r2 >= GroupVoipInviteAlert.m10880$$Nest$fgetparticipantsEndRow(this.this$0)) goto L9;
            return (TLObject) GroupVoipInviteAlert.m10879$$Nest$fgetparticipants(this.this$0).get(r2 - GroupVoipInviteAlert.m10881$$Nest$fgetparticipantsStartRow(this.this$0));
        L9:
            if (r2 >= GroupVoipInviteAlert.m10868$$Nest$fgetcontactsStartRow(this.this$0)) goto L11;
            return null;
        L11:
            if (r2 < GroupVoipInviteAlert.m10866$$Nest$fgetcontactsEndRow(this.this$0)) goto L13;
            return null;
        L13:
            return (TLObject) GroupVoipInviteAlert.m10865$$Nest$fgetcontacts(this.this$0).get(r2 - GroupVoipInviteAlert.m10868$$Nest$fgetcontactsStartRow(this.this$0));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return GroupVoipInviteAlert.m10882$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 < GroupVoipInviteAlert.m10881$$Nest$fgetparticipantsStartRow(this.this$0)) goto L7;
            if (r3 >= GroupVoipInviteAlert.m10880$$Nest$fgetparticipantsEndRow(this.this$0)) goto L7;
        L10:
            return 0;
        L7:
            if (r3 < GroupVoipInviteAlert.m10868$$Nest$fgetcontactsStartRow(this.this$0)) goto L12;
            if (r3 < GroupVoipInviteAlert.m10866$$Nest$fgetcontactsEndRow(this.this$0)) goto L10;
        L12:
            if (r3 != GroupVoipInviteAlert.m10864$$Nest$fgetaddNewRow(this.this$0)) goto L16;
            return 1;
        L16:
            if (r3 != GroupVoipInviteAlert.m10878$$Nest$fgetmembersHeaderRow(this.this$0)) goto L18;
            return 2;
        L18:
            if (r3 != GroupVoipInviteAlert.m10867$$Nest$fgetcontactsHeaderRow(this.this$0)) goto L21;
            return 2;
        L21:
            if (r3 != GroupVoipInviteAlert.m10870$$Nest$fgetemptyRow(this.this$0)) goto L25;
            return 3;
        L25:
            if (r3 != GroupVoipInviteAlert.m10876$$Nest$fgetlastRow(this.this$0)) goto L29;
            return 4;
        L29:
            if (r3 != GroupVoipInviteAlert.m10872$$Nest$fgetflickerProgressRow(this.this$0)) goto L32;
            return 5;
        L32:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r4) {
            View r0 = r4.itemView;
            if ((r0 instanceof ManageChatUserCell) == true) goto L5;
        L7:
            int r3 = r4.getItemViewType();
            if (r3 == 0) goto L12;
            if (r3 == 1) goto L12;
            return false;
        L12:
            return true;
        L5:
            if (GroupVoipInviteAlert.m10875$$Nest$fgetinvitedUsers(this.this$0).contains(Long.valueOf(((ManageChatUserCell) r0).getUserId())) == false) goto L7;
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r10, int r11) {
            int r0 = r10.getItemViewType();
            boolean r1 = false;
            if (r0 == 0) goto L34;
            if (r0 != 1) goto L6;
            ManageChatTextCell r3 = (ManageChatTextCell) r10.itemView;
            if (r11 == GroupVoipInviteAlert.m10864$$Nest$fgetaddNewRow(this.this$0)) goto L23;
            return;
        L23:
            if (GroupVoipInviteAlert.m10877$$Nest$fgetloadingUsers(this.this$0) == false) goto L27;
            if (GroupVoipInviteAlert.m10871$$Nest$fgetfirstLoaded(this.this$0) == true) goto L27;
        L31:
            boolean r8 = false;
        L32:
            r3.setText(LocaleController.getString(R.string.VoipGroupCopyInviteLink), null, R.drawable.msg_link, 7, r8);
            return;
        L27:
            if (GroupVoipInviteAlert.m10878$$Nest$fgetmembersHeaderRow(this.this$0) != (-1)) goto L31;
            if (GroupVoipInviteAlert.m10879$$Nest$fgetparticipants(this.this$0).isEmpty() == true) goto L31;
            r8 = true;
            goto L32
        L6:
            if (r0 != 2) goto L60;
            GraySectionCell r102 = (GraySectionCell) r10.itemView;
            if (r11 != GroupVoipInviteAlert.m10878$$Nest$fgetmembersHeaderRow(this.this$0)) goto L13;
            r102.setText(LocaleController.getString(R.string.ChannelOtherMembers));
            return;
        L13:
            if (r11 == GroupVoipInviteAlert.m10867$$Nest$fgetcontactsHeaderRow(this.this$0)) goto L15;
            return;
        L15:
            if (GroupVoipInviteAlert.m10883$$Nest$fgetshowContacts(this.this$0) == false) goto L18;
            r102.setText(LocaleController.getString(R.string.YourContactsToInvite));
            return;
        L18:
            r102.setText(LocaleController.getString(R.string.GroupContacts));
            return;
        L60:
            return;
        L34:
            ManageChatUserCell r103 = (ManageChatUserCell) r10.itemView;
            r103.setTag(Integer.valueOf(r11));
            TLObject r02 = getItem(r11);
            if (r11 >= GroupVoipInviteAlert.m10881$$Nest$fgetparticipantsStartRow(this.this$0)) goto L37;
        L39:
            int r32 = GroupVoipInviteAlert.m10866$$Nest$fgetcontactsEndRow(this.this$0);
        L41:
            if ((r02 instanceof TLRPC.TL_contact) == false) goto L44;
            long r4 = ((TLRPC.TL_contact) r02).user_id;
        L50:
            TLRPC.User r03 = MessagesController.getInstance(GroupVoipInviteAlert.access$300(this.this$0)).getUser(Long.valueOf(r4));
            if (r03 == null) goto L59;
            r103.setCustomImageVisible(GroupVoipInviteAlert.m10875$$Nest$fgetinvitedUsers(this.this$0).contains(Long.valueOf(r03.id)));
            if (r11 == (r32 - 1)) goto L55;
            r1 = true;
        L55:
            r103.setData(r03, null, null, r1);
            return;
        L59:
            return;
        L44:
            if ((r02 instanceof TLRPC.User) == false) goto L47;
            r4 = ((TLRPC.User) r02).id;
            goto L50
        L47:
            if ((r02 instanceof TLRPC.ChannelParticipant) == false) goto L49;
            r4 = MessageObject.getPeerId(((TLRPC.ChannelParticipant) r02).peer);
            goto L50
        L49:
            r4 = ((TLRPC.ChatParticipant) r02).user_id;
            goto L50
        L37:
            if (r11 >= GroupVoipInviteAlert.m10880$$Nest$fgetparticipantsEndRow(this.this$0)) goto L39;
            r32 = GroupVoipInviteAlert.m10880$$Nest$fgetparticipantsEndRow(this.this$0);
            goto L41
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v10, types: [android.view.View] */
        /* JADX WARN: Type inference failed for: r3v6, types: [org.telegram.ui.Cells.ManageChatTextCell] */
        /* JADX WARN: Type inference failed for: r3v7, types: [android.view.View, org.telegram.ui.Cells.GraySectionCell] */
        /* JADX WARN: Type inference failed for: r3v8, types: [android.view.View] */
        /* JADX WARN: Type inference failed for: r4v5, types: [org.telegram.ui.Components.FlickerLoadingView] */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 != 0) goto L5;
            ManageChatUserCell r42 = new ManageChatUserCell(this.mContext, 6, 2, false);
            r42.setCustomRightImage(R.drawable.msg_invited);
            r42.setNameColor(Theme.getColor(Theme.key_voipgroup_nameText));
            r42.setStatusColors(Theme.getColor(Theme.key_voipgroup_lastSeenTextUnscrolled), Theme.getColor(Theme.key_voipgroup_listeningText));
            r42.setDividerColor(Theme.key_voipgroup_actionBar);
            ManageChatUserCell r43 = r42;
        L13:
            ManageChatUserCell r32 = r43;
        L19:
            return new RecyclerListView.Holder(r32);
        L5:
            if (r4 == 1) goto L16;
            if (r4 != 2) goto L8;
            ?? r33 = new GraySectionCell(this.mContext);
            r33.setBackgroundColor(Theme.getColor(Theme.key_voipgroup_actionBarUnscrolled));
            r33.setTextColor(Theme.key_voipgroup_searchPlaceholder);
            r32 = r33;
            goto L19
        L8:
            if (r4 != 3) goto L10;
            ?? r34 = new View(this.mContext);
            r34.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(56.0f)));
            r32 = r34;
            goto L19
        L10:
            if (r4 == 5) goto L12;
            r32 = new View(this.mContext);
            goto L19
        L12:
            ?? r44 = new FlickerLoadingView(this.mContext);
            r44.setViewType(6);
            r44.setIsSingleCell(true);
            r44.setColors(Theme.key_voipgroup_inviteMembersBackground, Theme.key_voipgroup_searchBackground, Theme.key_voipgroup_actionBarUnscrolled);
            r43 = r44;
            goto L13
        L16:
            ?? r35 = new ManageChatTextCell(this.mContext);
            int r2 = Theme.key_voipgroup_listeningText;
            r35.setColors(r2, r2);
            r35.setDividerColor(Theme.key_voipgroup_actionBar);
            r32 = r35;
            goto L19
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(RecyclerView.ViewHolder r1) {
            View r0 = r1.itemView;
            if ((r0 instanceof ManageChatUserCell) == false) goto L6;
            ((ManageChatUserCell) r0).recycle();
            return;
        }
    }

    public class SearchAdapter extends RecyclerListView.SelectionAdapter {
        private int emptyRow;
        private int globalStartRow;
        private int groupStartRow;
        private int lastRow;
        private int lastSearchId;
        private Context mContext;
        private SearchAdapterHelper searchAdapterHelper;
        private boolean searchInProgress;
        private Runnable searchRunnable;
        final /* synthetic */ GroupVoipInviteAlert this$0;
        private int totalCount;

        /* renamed from: org.telegram.ui.Components.GroupVoipInviteAlert$SearchAdapter$1, reason: invalid class name */
        public class AnonymousClass1 implements SearchAdapterHelper.SearchAdapterHelperDelegate {
            final /* synthetic */ SearchAdapter this$1;
            final /* synthetic */ GroupVoipInviteAlert val$this$0;

            public AnonymousClass1(SearchAdapter r1, GroupVoipInviteAlert r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // org.telegram.ui.Adapters.SearchAdapterHelper.SearchAdapterHelperDelegate
            public LongSparseArray<TLRPC.GroupCallParticipant> getExcludeCallParticipants() {
                return GroupVoipInviteAlert.m10873$$Nest$fgetignoredUsers(this.this$1.this$0);
            }

            @Override // org.telegram.ui.Adapters.SearchAdapterHelper.SearchAdapterHelperDelegate
            public void onDataSetChanged(int r4) {
                if (r4 >= 0) goto L4;
                return;
            L4:
                if (r4 == SearchAdapter.m10884$$Nest$fgetlastSearchId(this.this$1)) goto L6;
                return;
            L6:
                if (SearchAdapter.m10886$$Nest$fgetsearchInProgress(this.this$1) == true) goto L25;
                boolean r0 = true;
                int r42 = this.this$1.getItemCount() - 1;
                if (this.this$1.this$0.emptyView.getVisibility() == 0) goto L12;
                r0 = false;
            L12:
                this.this$1.notifyDataSetChanged();
                if (this.this$1.getItemCount() <= r42) goto L16;
                this.this$1.this$0.showItemsAnimated(r42);
            L16:
                if (SearchAdapter.m10885$$Nest$fgetsearchAdapterHelper(this.this$1).isSearchInProgress() == false) goto L18;
                return;
            L18:
                if (this.this$1.this$0.listView.emptyViewIsVisible() == false) goto L24;
                this.this$1.this$0.emptyView.showProgress(false, r0);
                return;
            L24:
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$53fKeHyW3cVHfl4LNVQVPQcpdt8(SearchAdapter r0, String r1, int r2, ArrayList r3) {
            r0.lambda$processSearch$1(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$S6yGOC7S2TPKWb3HBTLCGiMkOCI(SearchAdapter r0, String r1, int r2) {
            r0.lambda$searchUsers$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$ZOJa1L9J8enxMppxdda7tH0D4Is(SearchAdapter r0, int r1, ArrayList r2) {
            r0.lambda$updateSearchResults$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$se4u3ec1dXc65fFbo4z2eCgfC6w(SearchAdapter r0, String r1, int r2) {
            r0.lambda$processSearch$2(r1, r2);
        }

        /* renamed from: -$$Nest$fgetlastSearchId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m10884$$Nest$fgetlastSearchId(SearchAdapter r0) {
            return r0.lastSearchId;
        }

        /* renamed from: -$$Nest$fgetsearchAdapterHelper, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SearchAdapterHelper m10885$$Nest$fgetsearchAdapterHelper(SearchAdapter r0) {
            return r0.searchAdapterHelper;
        }

        /* renamed from: -$$Nest$fgetsearchInProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m10886$$Nest$fgetsearchInProgress(SearchAdapter r0) {
            return r0.searchInProgress;
        }

        public SearchAdapter(GroupVoipInviteAlert r2, Context r3) {
            this.this$0 = r2;
            this.mContext = r3;
            SearchAdapterHelper r32 = new SearchAdapterHelper(true);
            this.searchAdapterHelper = r32;
            r32.setDelegate(new AnonymousClass1(this, r2));
        }

        private /* synthetic */ void lambda$processSearch$1(String r19, int r20, ArrayList r21) {
            String r2 = r19.trim().toLowerCase();
            if (r2.length() != 0) goto L6;
            updateSearchResults(new ArrayList(), r20);
            return;
        L6:
            String r3 = LocaleController.getInstance().getTranslitString(r2);
            if (r2.equals(r3) == false) goto L9;
        L10:
            r3 = null;
        L11:
            int r4 = 0;
            if (r3 == null) goto L14;
            int r7 = 1;
        L15:
            int r72 = r7 + 1;
            String[] r8 = new String[r72];
            r8[0] = r2;
            if (r3 == null) goto L18;
            r8[1] = r3;
        L18:
            ArrayList<TLObject> r22 = new ArrayList();
            int r32 = r21.size();
            int r9 = 0;
        L19:
            if (r9 >= r32) goto L54;
            TLObject r11 = (TLObject) r21.get(r9);
            if ((r11 instanceof TLRPC.ChatParticipant) == false) goto L24;
            long r12 = ((TLRPC.ChatParticipant) r11).user_id;
        L26:
            TLRPC.User r122 = MessagesController.getInstance(GroupVoipInviteAlert.access$200(this.this$0)).getUser(Long.valueOf(r12));
            if (UserObject.isUserSelf(r122) == true) goto L53;
            String r13 = UserObject.getUserName(r122).toLowerCase();
            String r14 = LocaleController.getInstance().getTranslitString(r13);
            if (r13.equals(r14) == false) goto L32;
            r14 = null;
        L32:
            int r15 = r4;
            int r16 = r15;
        L33:
            if (r15 >= r72) goto L53;
            String r42 = r8[r15];
            if (r13.startsWith(r42) == false) goto L37;
        L49:
            r16 = 1;
        L50:
            if (r16 != 0) goto L51;
            r15 = r15 + 1;
            goto L33
        L51:
            r22.add(r11);
            goto L53
        L37:
            if (r13.contains(" " + r42) == true) goto L49;
            if (r14 != null) goto L40;
        L44:
            String r5 = UserObject.getPublicUsername(r122);
            if (r5 == null) goto L50;
            if (r5.startsWith(r42) == false) goto L50;
            r16 = 2;
            goto L50
        L40:
            if (r14.startsWith(r42) == true) goto L49;
            if (r14.contains(" " + r42) == false) goto L44;
        L53:
            r9 = r9 + 1;
            r4 = 0;
            goto L19
        L24:
            if ((r11 instanceof TLRPC.ChannelParticipant) == false) goto L53;
            r12 = MessageObject.getPeerId(((TLRPC.ChannelParticipant) r11).peer);
            goto L26
        L54:
            updateSearchResults(r22, r20);
            return;
        L14:
            r7 = 0;
            goto L15
        L9:
            if (r3.length() != 0) goto L11;
            goto L10
        }

        private /* synthetic */ void lambda$processSearch$2(String r14, int r15) {
            ArrayList r0 = null;
            this.searchRunnable = null;
            if (ChatObject.isChannel(GroupVoipInviteAlert.m10869$$Nest$fgetcurrentChat(this.this$0)) == false) goto L5;
        L7:
            if (r0 == null) goto L9;
            Utilities.searchQueue.postRunnable(new GroupVoipInviteAlert$SearchAdapter$$ExternalSyntheticLambda2(this, r14, r15, r0));
        L10:
            SearchAdapterHelper r1 = this.searchAdapterHelper;
            boolean r3 = ChatObject.canAddUsers(GroupVoipInviteAlert.m10869$$Nest$fgetcurrentChat(this.this$0));
            if (ChatObject.isChannel(GroupVoipInviteAlert.m10869$$Nest$fgetcurrentChat(this.this$0)) == false) goto L14;
            long r4 = GroupVoipInviteAlert.m10869$$Nest$fgetcurrentChat(this.this$0).id;
        L15:
            r1.queryServerSearch(r14, r3, false, true, false, false, r4, false, 2, r15);
            return;
        L14:
            r4 = 0;
            goto L15
        L9:
            this.searchInProgress = false;
            goto L10
        L5:
            if (GroupVoipInviteAlert.m10874$$Nest$fgetinfo(this.this$0) == null) goto L7;
            r0 = new ArrayList(GroupVoipInviteAlert.m10874$$Nest$fgetinfo(this.this$0).participants.participants);
            goto L7
        }

        private /* synthetic */ void lambda$searchUsers$0(String r2, int r3) {
            if (this.searchRunnable != null) goto L5;
            return;
        L5:
            this.searchRunnable = null;
            processSearch(r2, r3);
        }

        private /* synthetic */ void lambda$updateSearchResults$3(int r3, ArrayList r4) {
            if (r3 != this.lastSearchId) goto L26;
            this.searchInProgress = false;
            if (ChatObject.isChannel(GroupVoipInviteAlert.m10869$$Nest$fgetcurrentChat(this.this$0)) == true) goto L8;
            this.searchAdapterHelper.addGroupMembers(r4);
        L8:
            boolean r0 = true;
            int r42 = getItemCount() - 1;
            if (this.this$0.emptyView.getVisibility() == 0) goto L12;
            r0 = false;
        L12:
            notifyDataSetChanged();
            if (getItemCount() <= r42) goto L16;
            this.this$0.showItemsAnimated(r42);
        L16:
            if (this.searchInProgress == false) goto L18;
            return;
        L18:
            if (this.searchAdapterHelper.isSearchInProgress() == false) goto L20;
            return;
        L20:
            if (this.this$0.listView.emptyViewIsVisible() == false) goto L25;
            this.this$0.emptyView.showProgress(false, r0);
            return;
        L25:
            return;
        }

        private void processSearch(String r2, int r3) {
            AndroidUtilities.runOnUIThread(new GroupVoipInviteAlert$SearchAdapter$$ExternalSyntheticLambda1(this, r2, r3));
        }

        private void updateSearchResults(ArrayList<TLObject> r2, int r3) {
            AndroidUtilities.runOnUIThread(new GroupVoipInviteAlert$SearchAdapter$$ExternalSyntheticLambda3(this, r3, r2));
        }

        public TLObject getItem(int r3) {
            int r0 = this.groupStartRow;
            if (r0 < 0) goto L9;
            if (r3 <= r0) goto L9;
            if (r3 >= ((r0 + 1) + this.searchAdapterHelper.getGroupSearch().size())) goto L9;
            return this.searchAdapterHelper.getGroupSearch().get((r3 - this.groupStartRow) - 1);
        L9:
            int r02 = this.globalStartRow;
            if (r02 < 0) goto L16;
            if (r3 > r02) goto L13;
            return null;
        L13:
            if (r3 < ((r02 + 1) + this.searchAdapterHelper.getGlobalSearch().size())) goto L15;
            return null;
        L15:
            return this.searchAdapterHelper.getGlobalSearch().get((r3 - this.globalStartRow) - 1);
        L16:
            return null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.totalCount;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != this.emptyRow) goto L7;
            return 2;
        L7:
            if (r2 != this.lastRow) goto L11;
            return 3;
        L11:
            if (r2 != this.globalStartRow) goto L13;
            return 1;
        L13:
            if (r2 == this.groupStartRow) goto L19;
            return 0;
        L19:
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r4) {
            View r0 = r4.itemView;
            if ((r0 instanceof ManageChatUserCell) == false) goto L8;
            if (GroupVoipInviteAlert.m10875$$Nest$fgetinvitedUsers(this.this$0).contains(Long.valueOf(((ManageChatUserCell) r0).getUserId())) == false) goto L8;
            return false;
        L8:
            if (r4.getItemViewType() != 0) goto L11;
            return true;
        L11:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            this.totalCount = 1;
            this.emptyRow = 0;
            int r1 = this.searchAdapterHelper.getGroupSearch().size();
            if (r1 == 0) goto L5;
            int r3 = this.totalCount;
            this.groupStartRow = r3;
            this.totalCount = r3 + (r1 + 1);
        L6:
            int r12 = this.searchAdapterHelper.getGlobalSearch().size();
            if (r12 == 0) goto L9;
            int r2 = this.totalCount;
            this.globalStartRow = r2;
            this.totalCount = r2 + (r12 + 1);
        L10:
            int r0 = this.totalCount;
            this.totalCount = r0 + 1;
            this.lastRow = r0;
            super.notifyDataSetChanged();
            return;
        L9:
            this.globalStartRow = -1;
            goto L10
        L5:
            this.groupStartRow = -1;
            goto L6
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r13, int r14) {
            int r0 = r13.getItemViewType();
            if (r0 == 0) goto L14;
            if (r0 != 1) goto L63;
            GraySectionCell r132 = (GraySectionCell) r13.itemView;
            if (r14 != this.groupStartRow) goto L11;
            r132.setText(LocaleController.getString(R.string.ChannelMembers));
            return;
        L11:
            if (r14 != this.globalStartRow) goto L61;
            r132.setText(LocaleController.getString(R.string.GlobalSearch));
            return;
        L61:
            return;
        L63:
            return;
        L14:
            TLObject r02 = getItem(r14);
            if ((r02 instanceof TLRPC.User) == false) goto L18;
            TLRPC.User r03 = (TLRPC.User) r02;
        L23:
            String r2 = UserObject.getPublicUsername(r03);
            int r3 = this.searchAdapterHelper.getGroupSearch().size();
            SpannableStringBuilder r5 = null;
            if (r3 == 0) goto L29;
            int r32 = r3 + 1;
            if (r32 <= r14) goto L28;
            String r33 = this.searchAdapterHelper.getLastFoundChannel();
            boolean r6 = true;
        L31:
            if (r6 == true) goto L52;
            if (r2 == null) goto L52;
            int r62 = this.searchAdapterHelper.getGlobalSearch().size();
            if (r62 == 0) goto L52;
            if ((r62 + 1) <= r14) goto L52;
            String r63 = this.searchAdapterHelper.getLastFoundUsername();
            if (r63.startsWith("@") == false) goto L59;
            r63 = r63.substring(1);
        L59:
            SpannableStringBuilder r1 = new SpannableStringBuilder();     // Catch: Exception -> L48
            r1.append("@");     // Catch: Exception -> L48
            r1.append(r2);     // Catch: Exception -> L48
            int r9 = AndroidUtilities.indexOfIgnoreCase(r2, r63);     // Catch: Exception -> L48
            if (r9 == (-1)) goto L50;
            int r64 = r63.length();     // Catch: Exception -> L48
            if (r9 != 0) goto L45;
            r64 = r64 + 1;     // Catch: Exception -> L48
        L46:
            r1.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_voipgroup_listeningText)), r9, r64 + r9, 33);     // Catch: Exception -> L48
            goto L50
        L45:
            r9 = r9 + 1;     // Catch: Exception -> L48
        L50:
            r2 = r1;
        L53:
            if (r33 == null) goto L57;
            String r12 = UserObject.getUserName(r03);
            r5 = new SpannableStringBuilder(r12);
            int r15 = AndroidUtilities.indexOfIgnoreCase(r12, r33);
            if (r15 == (-1)) goto L57;
            r5.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_voipgroup_listeningText)), r15, r33.length() + r15, 33);
        L57:
            ManageChatUserCell r133 = (ManageChatUserCell) r13.itemView;
            r133.setTag(Integer.valueOf(r14));
            r133.setCustomImageVisible(GroupVoipInviteAlert.m10875$$Nest$fgetinvitedUsers(this.this$0).contains(Long.valueOf(r03.id)));
            r133.setData(r03, r5, r2, false);
            return;
        L48:
            e = move-exception;
            FileLog.e(e);
        L52:
            r2 = null;
            goto L53
        L28:
            r14 = r14 - r32;
        L29:
            r6 = false;
            r33 = null;
            goto L31
        L18:
            if ((r02 instanceof TLRPC.ChannelParticipant) == false) goto L21;
            r03 = MessagesController.getInstance(GroupVoipInviteAlert.access$000(this.this$0)).getUser(Long.valueOf(MessageObject.getPeerId(((TLRPC.ChannelParticipant) r02).peer)));
            goto L23
        L21:
            if ((r02 instanceof TLRPC.ChatParticipant) == false) goto L62;
            r03 = MessagesController.getInstance(GroupVoipInviteAlert.access$100(this.this$0)).getUser(Long.valueOf(((TLRPC.ChatParticipant) r02).user_id));
            goto L23
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v6, types: [android.view.View, org.telegram.ui.Cells.GraySectionCell] */
        /* JADX WARN: Type inference failed for: r2v7, types: [android.view.View] */
        /* JADX WARN: Type inference failed for: r2v8, types: [android.view.View] */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 != 0) goto L5;
            ManageChatUserCell r32 = new ManageChatUserCell(this.mContext, 2, 2, false);
            r32.setCustomRightImage(R.drawable.msg_invited);
            r32.setNameColor(Theme.getColor(Theme.key_voipgroup_nameText));
            r32.setStatusColors(Theme.getColor(Theme.key_voipgroup_lastSeenTextUnscrolled), Theme.getColor(Theme.key_voipgroup_listeningText));
            r32.setDividerColor(Theme.key_voipgroup_listViewBackground);
            ManageChatUserCell r22 = r32;
        L12:
            return new RecyclerListView.Holder(r22);
        L5:
            if (r3 == 1) goto L9;
            if (r3 == 2) goto L8;
            r22 = new View(this.mContext);
            goto L12
        L8:
            ?? r23 = new View(this.mContext);
            r23.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(56.0f)));
            r22 = r23;
            goto L12
        L9:
            ?? r24 = new GraySectionCell(this.mContext);
            r24.setBackgroundColor(Theme.getColor(Theme.key_voipgroup_actionBarUnscrolled));
            r24.setTextColor(Theme.key_voipgroup_searchPlaceholder);
            r22 = r24;
            goto L12
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(RecyclerView.ViewHolder r1) {
            View r0 = r1.itemView;
            if ((r0 instanceof ManageChatUserCell) == false) goto L6;
            ((ManageChatUserCell) r0).recycle();
            return;
        }

        public void searchUsers(String r15) {
            Runnable r0 = this.searchRunnable;
            if (r0 == null) goto L5;
            AndroidUtilities.cancelRunOnUIThread(r0);
            this.searchRunnable = null;
        L5:
            this.searchAdapterHelper.mergeResults(null);
            this.searchAdapterHelper.queryServerSearch(null, true, false, true, false, false, GroupVoipInviteAlert.m10869$$Nest$fgetcurrentChat(this.this$0).id, false, 2, -1);
            if (TextUtils.isEmpty(r15) == true) goto L11;
            this.this$0.emptyView.showProgress(true, true);
            this.this$0.listView.setAnimateEmptyView(false, 0);
            notifyDataSetChanged();
            this.this$0.listView.setAnimateEmptyView(true, 0);
            this.searchInProgress = true;
            int r02 = this.lastSearchId + 1;
            this.lastSearchId = r02;
            GroupVoipInviteAlert$SearchAdapter$$ExternalSyntheticLambda0 r1 = new GroupVoipInviteAlert$SearchAdapter$$ExternalSyntheticLambda0(this, r15, r02);
            this.searchRunnable = r1;
            AndroidUtilities.runOnUIThread(r1, 300);
            RecyclerView.Adapter r152 = this.this$0.listView.getAdapter();
            GroupVoipInviteAlert r14 = this.this$0;
            RecyclerView.Adapter r03 = r14.searchListViewAdapter;
            if (r152 == r03) goto L13;
            r14.listView.setAdapter(r03);
            return;
        L13:
            return;
        L11:
            this.lastSearchId = -1;
        }
    }

    public static /* synthetic */ void $r8$lambda$0VTEIly3Y1tSwf95PHnIa2NtlCI(GroupVoipInviteAlert r0, TLRPC.TL_channels_getParticipants r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$loadChatParticipants$4(r1, r2, r3);
    }

    public static /* synthetic */ int $r8$lambda$9OSNhVYSa54tsObmuRTw7KPXAr0(GroupVoipInviteAlert r0, int r1, TLObject r2, TLObject r3) {
        return r0.lambda$loadChatParticipants$2(r1, r2, r3);
    }

    public static /* synthetic */ int $r8$lambda$Jxjb1g4dHVPktfHUABWJR3wxpuE(MessagesController r4, int r5, TLObject r6, TLObject r7) {
        TLRPC.User r1 = null;
        if ((r7 instanceof TLRPC.TL_contact) == false) goto L5;
        TLRPC.User r72 = r4.getUser(Long.valueOf(((TLRPC.TL_contact) r7).user_id));
    L7:
        if ((r6 instanceof TLRPC.TL_contact) == false) goto L10;
        r1 = r4.getUser(Long.valueOf(((TLRPC.TL_contact) r6).user_id));
    L10:
        if (r72 != null) goto L12;
    L17:
        int r73 = 0;
    L18:
        if (r1 != null) goto L20;
    L25:
        int r52 = 0;
    L26:
        if (r73 <= 0) goto L32;
        if (r52 <= 0) goto L32;
        if (r73 > r52) goto L53;
        if (r73 < r52) goto L51;
    L47:
        return 0;
    L51:
        return -1;
    L53:
        return 1;
    L32:
        if (r73 >= 0) goto L38;
        if (r52 >= 0) goto L38;
        if (r73 > r52) goto L54;
        if (r73 >= r52) goto L47;
        return -1;
    L54:
        return 1;
    L38:
        if (r73 >= 0) goto L40;
        if (r52 <= 0) goto L40;
        return -1;
    L40:
        if (r73 != 0) goto L44;
        if (r52 == 0) goto L44;
        return -1;
    L44:
        if (r52 < 0) goto L48;
        if (r73 == 0) goto L47;
        return 1;
    L48:
        return 1;
    L20:
        if (r1.self == false) goto L22;
        r52 = r5 + 50000;
        goto L26
    L22:
        TLRPC.UserStatus r42 = r1.status;
        if (r42 == null) goto L25;
        r52 = r42.expires;
        goto L26
    L12:
        if (r72.self == false) goto L14;
        r73 = r5 + 50000;
        goto L18
    L14:
        TLRPC.UserStatus r74 = r72.status;
        if (r74 == null) goto L17;
        r73 = r74.expires;
        goto L18
    L5:
        r72 = null;
        goto L7
    }

    public static /* synthetic */ void $r8$lambda$idTHaAcEXpURs1qnq_l6_IYBQws(GroupVoipInviteAlert r0, View r1, int r2) {
        r0.lambda$new$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$qQLYxm0fGhXt8FIkHbu7CB7XB2A(GroupVoipInviteAlert r0, TLRPC.TL_error r1, TLObject r2, TLRPC.TL_channels_getParticipants r3) {
        r0.lambda$loadChatParticipants$3(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetaddNewRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10864$$Nest$fgetaddNewRow(GroupVoipInviteAlert r0) {
        return r0.addNewRow;
    }

    /* renamed from: -$$Nest$fgetcontacts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10865$$Nest$fgetcontacts(GroupVoipInviteAlert r0) {
        return r0.contacts;
    }

    /* renamed from: -$$Nest$fgetcontactsEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10866$$Nest$fgetcontactsEndRow(GroupVoipInviteAlert r0) {
        return r0.contactsEndRow;
    }

    /* renamed from: -$$Nest$fgetcontactsHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10867$$Nest$fgetcontactsHeaderRow(GroupVoipInviteAlert r0) {
        return r0.contactsHeaderRow;
    }

    /* renamed from: -$$Nest$fgetcontactsStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10868$$Nest$fgetcontactsStartRow(GroupVoipInviteAlert r0) {
        return r0.contactsStartRow;
    }

    /* renamed from: -$$Nest$fgetcurrentChat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Chat m10869$$Nest$fgetcurrentChat(GroupVoipInviteAlert r0) {
        return r0.currentChat;
    }

    /* renamed from: -$$Nest$fgetemptyRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10870$$Nest$fgetemptyRow(GroupVoipInviteAlert r0) {
        return r0.emptyRow;
    }

    /* renamed from: -$$Nest$fgetfirstLoaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10871$$Nest$fgetfirstLoaded(GroupVoipInviteAlert r0) {
        return r0.firstLoaded;
    }

    /* renamed from: -$$Nest$fgetflickerProgressRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10872$$Nest$fgetflickerProgressRow(GroupVoipInviteAlert r0) {
        return r0.flickerProgressRow;
    }

    /* renamed from: -$$Nest$fgetignoredUsers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m10873$$Nest$fgetignoredUsers(GroupVoipInviteAlert r0) {
        return r0.ignoredUsers;
    }

    /* renamed from: -$$Nest$fgetinfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.ChatFull m10874$$Nest$fgetinfo(GroupVoipInviteAlert r0) {
        return r0.info;
    }

    /* renamed from: -$$Nest$fgetinvitedUsers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashSet m10875$$Nest$fgetinvitedUsers(GroupVoipInviteAlert r0) {
        return r0.invitedUsers;
    }

    /* renamed from: -$$Nest$fgetlastRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10876$$Nest$fgetlastRow(GroupVoipInviteAlert r0) {
        return r0.lastRow;
    }

    /* renamed from: -$$Nest$fgetloadingUsers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10877$$Nest$fgetloadingUsers(GroupVoipInviteAlert r0) {
        return r0.loadingUsers;
    }

    /* renamed from: -$$Nest$fgetmembersHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10878$$Nest$fgetmembersHeaderRow(GroupVoipInviteAlert r0) {
        return r0.membersHeaderRow;
    }

    /* renamed from: -$$Nest$fgetparticipants, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10879$$Nest$fgetparticipants(GroupVoipInviteAlert r0) {
        return r0.participants;
    }

    /* renamed from: -$$Nest$fgetparticipantsEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10880$$Nest$fgetparticipantsEndRow(GroupVoipInviteAlert r0) {
        return r0.participantsEndRow;
    }

    /* renamed from: -$$Nest$fgetparticipantsStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10881$$Nest$fgetparticipantsStartRow(GroupVoipInviteAlert r0) {
        return r0.participantsStartRow;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10882$$Nest$fgetrowCount(GroupVoipInviteAlert r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetshowContacts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10883$$Nest$fgetshowContacts(GroupVoipInviteAlert r0) {
        return r0.showContacts;
    }

    public GroupVoipInviteAlert(Context r3, int r4, TLRPC.Chat r5, TLRPC.ChatFull r6, LongSparseArray<TLRPC.GroupCallParticipant> r7, HashSet<Long> r8) {
        super(r3, false, r4, null);
        this.participants = new ArrayList();
        this.contacts = new ArrayList();
        this.participantsMap = new LongSparseArray();
        this.contactsMap = new LongSparseArray();
        setDimBehindAlpha(75);
        this.currentChat = r5;
        this.info = r6;
        this.ignoredUsers = r7;
        this.invitedUsers = r8;
        this.listView.setOnItemClickListener(new GroupVoipInviteAlert$$ExternalSyntheticLambda0(this));
        SearchAdapter r42 = new SearchAdapter(this, r3);
        this.searchAdapter = r42;
        this.searchListViewAdapter = r42;
        RecyclerListView r43 = this.listView;
        ListAdapter r52 = new ListAdapter(this, r3);
        this.listViewAdapter = r52;
        r43.setAdapter(r52);
        loadChatParticipants(0, 200);
        updateRows();
        setColorProgress(0.0f);
    }

    public static /* synthetic */ int access$000(GroupVoipInviteAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(GroupVoipInviteAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(GroupVoipInviteAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$300(GroupVoipInviteAlert r0) {
        return r0.currentAccount;
    }

    private void fillContacts() {
        if (this.showContacts == true) goto L5;
        return;
    L5:
        this.contacts.addAll(ContactsController.getInstance(this.currentAccount).contacts);
        long r0 = UserConfig.getInstance(this.currentAccount).clientUserId;
        int r2 = this.contacts.size();
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L18;
        TLObject r4 = this.contacts.get(r3);
        if ((r4 instanceof TLRPC.TL_contact) == false) goto L17;
        long r42 = ((TLRPC.TL_contact) r4).user_id;
        if (r42 != r0) goto L13;
    L16:
        this.contacts.remove(r3);
        r3 = r3 - 1;
        r2 = r2 - 1;
        goto L17
    L13:
        if (this.ignoredUsers.indexOfKey(r42) >= 0) goto L16;
        if (this.invitedUsers.contains(Long.valueOf(r42)) == true) goto L16;
    L17:
        r3 = r3 + 1;
        goto L6
    L18:
        int r02 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        Collections.sort(this.contacts, new GroupVoipInviteAlert$$ExternalSyntheticLambda2(MessagesController.getInstance(this.currentAccount), r02));
    }

    private /* synthetic */ int lambda$loadChatParticipants$2(int r4, TLObject r5, TLObject r6) {
        TLRPC.User r52 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(MessageObject.getPeerId(((TLRPC.ChannelParticipant) r5).peer)));
        TLRPC.User r3 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(MessageObject.getPeerId(((TLRPC.ChannelParticipant) r6).peer)));
        if (r52 == null) goto L10;
        TLRPC.UserStatus r1 = r52.status;
        if (r1 == null) goto L10;
        if (r52.self == false) goto L9;
        int r53 = r4 + 50000;
    L11:
        if (r3 == null) goto L18;
        TLRPC.UserStatus r12 = r3.status;
        if (r12 == null) goto L18;
        if (r3.self == false) goto L17;
        int r42 = r4 + 50000;
    L20:
        if (r53 <= 0) goto L27;
        if (r42 <= 0) goto L27;
        if (r53 <= r42) goto L24;
        return 1;
    L24:
        if (r53 >= r42) goto L26;
        return -1;
    L26:
        return 0;
    L27:
        if (r53 >= 0) goto L34;
        if (r42 >= 0) goto L34;
        if (r53 <= r42) goto L31;
        return 1;
    L31:
        if (r53 >= r42) goto L33;
        return -1;
    L33:
        return 0;
    L34:
        if (r53 >= 0) goto L36;
        if (r42 <= 0) goto L36;
    L38:
        return -1;
    L36:
        if (r53 != 0) goto L39;
        if (r42 != 0) goto L38;
    L39:
        if (r42 >= 0) goto L41;
        if (r53 <= 0) goto L41;
    L43:
        return 1;
    L41:
        if (r42 != 0) goto L44;
        if (r53 != 0) goto L43;
    L44:
        return 0;
    L17:
        r42 = r12.expires;
    L18:
        r42 = 0;
        goto L20
    L9:
        r53 = r1.expires;
    L10:
        r53 = 0;
        goto L11
    }

    private /* synthetic */ void lambda$loadChatParticipants$3(TLRPC.TL_error r8, TLObject r9, TLRPC.TL_channels_getParticipants r10) {
        if (r8 != null) goto L46;
        TLRPC.TL_channels_channelParticipants r92 = (TLRPC.TL_channels_channelParticipants) r9;
        MessagesController.getInstance(this.currentAccount).putUsers(r92.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r92.chats, false);
        long r2 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        int r82 = 0;
    L6:
        if (r82 >= r92.participants.size()) goto L11;
        if (MessageObject.getPeerId(r92.participants.get(r82).peer) == r2) goto L9;
        r82 = r82 + 1;
        goto L6
    L9:
        r92.participants.remove(r82);
    L11:
        this.delayResults--;
        if ((r10.filter instanceof TLRPC.TL_channelParticipantsContacts) == false) goto L14;
        ArrayList<TLObject> r83 = this.contacts;
        LongSparseArray<TLObject> r102 = this.contactsMap;
    L15:
        r83.clear();
        r83.addAll(r92.participants);
        int r22 = r92.participants.size();
        int r3 = 0;
    L16:
        if (r3 >= r22) goto L18;
        TLRPC.ChannelParticipant r4 = r92.participants.get(r3);
        r102.put(MessageObject.getPeerId(r4.peer), r4);
        r3 = r3 + 1;
        goto L16
    L18:
        int r93 = this.participants.size();
        int r103 = 0;
    L19:
        if (r103 >= r93) goto L67;
        long r23 = MessageObject.getPeerId(((TLRPC.ChannelParticipant) this.participants.get(r103)).peer);
        if (this.contactsMap.get(r23) == null) goto L23;
    L22:
        boolean r42 = true;
    L29:
        TLRPC.User r5 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r23));
        if (r5 == null) goto L34;
        if (r5.bot == false) goto L34;
    L35:
        r42 = true;
    L36:
        if (r42 == false) goto L38;
        this.participants.remove(r103);
        this.participantsMap.remove(r23);
        r103 = r103 - 1;
        r93 = r93 - 1;
    L38:
        r103 = r103 + 1;
    L34:
        if (UserObject.isDeleted(r5) == false) goto L36;
    L23:
        LongSparseArray<TLRPC.GroupCallParticipant> r43 = this.ignoredUsers;
        if (r43 != null) goto L26;
    L28:
        r42 = false;
        goto L29
    L26:
        if (r43.indexOfKey(r23) < 0) goto L28;
    L67:
    L43:
        e = move-exception;
        FileLog.e(e);
        goto L46
    L40:
        if (this.info.participants_count > 200) goto L46;
        Collections.sort(r83, new GroupVoipInviteAlert$$ExternalSyntheticLambda4(this, ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()));     // Catch: Exception -> L43
        goto L46
    L14:
        r83 = this.participants;
        r102 = this.participantsMap;
    L46:
        if (this.delayResults > 0) goto L57;
        this.loadingUsers = false;
        this.firstLoaded = true;
        if (this.flickerProgressRow != 1) goto L50;
        int r84 = 1;
    L54:
        showItemsAnimated(r84);
        if (this.participants.isEmpty() == false) goto L57;
        this.showContacts = true;
        fillContacts();
        goto L57
    L50:
        RecyclerView.Adapter r85 = this.listViewAdapter;
        if (r85 == null) goto L53;
        r84 = r85.getItemCount() - 1;
        goto L54
    L53:
        r84 = 0;
    L57:
        updateRows();
        RecyclerView.Adapter r86 = this.listViewAdapter;
        if (r86 == null) goto L74;
        r86.notifyDataSetChanged();
        if (this.emptyView != null) goto L62;
        return;
    L62:
        if (this.listViewAdapter.getItemCount() == 0) goto L64;
        return;
    L64:
        if (this.firstLoaded == false) goto L77;
        this.emptyView.showProgress(false, true);
        return;
    L77:
        return;
    }

    private /* synthetic */ void lambda$loadChatParticipants$4(TLRPC.TL_channels_getParticipants r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new GroupVoipInviteAlert$$ExternalSyntheticLambda3(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$new$0(View r3, int r4) {
        if (r4 != this.addNewRow) goto L7;
        this.delegate.copyInviteLink();
        dismiss();
        return;
    L7:
        if ((r3 instanceof ManageChatUserCell) == false) goto L13;
        ManageChatUserCell r32 = (ManageChatUserCell) r3;
        if (this.invitedUsers.contains(Long.valueOf(r32.getUserId())) == true) goto L14;
        this.delegate.inviteUser(r32.getUserId());
        return;
    L14:
        return;
    }

    private void loadChatParticipants(int r2, int r3) {
        if (this.loadingUsers == false) goto L5;
        return;
    L5:
        this.contactsEndReached = false;
        loadChatParticipants(r2, r3, true);
    }

    private void updateRows() {
        this.addNewRow = -1;
        this.participantsStartRow = -1;
        this.participantsEndRow = -1;
        this.contactsHeaderRow = -1;
        this.contactsStartRow = -1;
        this.contactsEndRow = -1;
        this.membersHeaderRow = -1;
        this.lastRow = -1;
        boolean r0 = true;
        this.rowCount = 1;
        this.emptyRow = 0;
        if (ChatObject.isPublic(this.currentChat) == false) goto L5;
    L6:
        int r2 = this.rowCount;
        this.rowCount = r2 + 1;
        this.addNewRow = r2;
    L8:
        if (this.loadingUsers == false) goto L12;
        if (this.firstLoaded == true) goto L12;
    L21:
        if (this.loadingUsers == false) goto L23;
        int r02 = this.rowCount;
        this.rowCount = r02 + 1;
        this.flickerProgressRow = r02;
    L23:
        int r03 = this.rowCount;
        this.rowCount = r03 + 1;
        this.lastRow = r03;
        return;
    L12:
        if (this.contacts.isEmpty() == true) goto L14;
        int r1 = this.rowCount;
        int r22 = r1 + 1;
        this.rowCount = r22;
        this.contactsHeaderRow = r1;
        this.contactsStartRow = r22;
        int r23 = r22 + this.contacts.size();
        this.rowCount = r23;
        this.contactsEndRow = r23;
    L16:
        if (this.participants.isEmpty() == true) goto L21;
        if (r0 == false) goto L19;
        int r04 = this.rowCount;
        this.rowCount = r04 + 1;
        this.membersHeaderRow = r04;
    L19:
        int r05 = this.rowCount;
        this.participantsStartRow = r05;
        int r06 = r05 + this.participants.size();
        this.rowCount = r06;
        this.participantsEndRow = r06;
        goto L21
    L14:
        r0 = false;
        goto L16
    L5:
        if (ChatObject.canUserDoAdminAction(this.currentChat, 3) == false) goto L8;
        goto L6
    }

    public void loadChatParticipants(int r7, int r8, boolean r9) {
        int r1 = 0;
        if (ChatObject.isChannel(this.currentChat) == true) goto L30;
        this.loadingUsers = false;
        this.participants.clear();
        this.contacts.clear();
        this.participantsMap.clear();
        this.contactsMap.clear();
        if (this.info == null) goto L26;
        long r72 = UserConfig.getInstance(this.currentAccount).clientUserId;
        int r92 = this.info.participants.participants.size();
    L7:
        if (r1 >= r92) goto L24;
        TLRPC.ChatParticipant r2 = this.info.participants.participants.get(r1);
        long r3 = r2.user_id;
        if (r3 == r72) goto L22;
        LongSparseArray<TLRPC.GroupCallParticipant> r5 = this.ignoredUsers;
        if (r5 != null) goto L14;
    L16:
        TLRPC.User r32 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r2.user_id));
        if (UserObject.isDeleted(r32) == true) goto L22;
        if (r32.bot == true) goto L22;
        this.participants.add(r2);
        this.participantsMap.put(r2.user_id, r2);
        goto L22
    L14:
        if (r5.indexOfKey(r3) < 0) goto L16;
    L22:
        r1 = r1 + 1;
        goto L7
    L24:
        if (this.participants.isEmpty() == false) goto L26;
        this.showContacts = true;
        fillContacts();
    L26:
        updateRows();
        RecyclerView.Adapter r6 = this.listViewAdapter;
        if (r6 == null) goto L53;
        r6.notifyDataSetChanged();
        return;
    L53:
        return;
    L30:
        this.loadingUsers = true;
        StickerEmptyView r93 = this.emptyView;
        if (r93 == null) goto L33;
        r93.showProgress(true, false);
    L33:
        RecyclerView.Adapter r94 = this.listViewAdapter;
        if (r94 == null) goto L36;
        r94.notifyDataSetChanged();
    L36:
        TLRPC.TL_channels_getParticipants r95 = new TLRPC.TL_channels_getParticipants();
        r95.channel = MessagesController.getInputChannel(this.currentChat);
        TLRPC.ChatFull r22 = this.info;
        if (r22 == null) goto L42;
        if (r22.participants_count > 200) goto L42;
        r95.filter = new TLRPC.TL_channelParticipantsRecent();
    L45:
        r95.filter.q = _UrlKt.FRAGMENT_ENCODE_SET;
        r95.offset = r7;
        r95.limit = r8;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r95, new GroupVoipInviteAlert$$ExternalSyntheticLambda1(this, r95));
        return;
    L42:
        if (this.contactsEndReached == true) goto L44;
        this.delayResults = 2;
        r95.filter = new TLRPC.TL_channelParticipantsContacts();
        this.contactsEndReached = true;
        loadChatParticipants(0, 200, false);
        goto L45
    L44:
        r95.filter = new TLRPC.TL_channelParticipantsRecent();
        goto L45
    }

    @Override // org.telegram.ui.Components.UsersAlertBase
    public void onSearchViewTouched(MotionEvent r1, EditTextBoldCursor r2) {
        this.delegate.needOpenSearch(r1, r2);
    }

    @Override // org.telegram.ui.Components.UsersAlertBase
    public void search(String r1) {
        this.searchAdapter.searchUsers(r1);
    }

    public void setDelegate(GroupVoipInviteAlertDelegate r1) {
        this.delegate = r1;
    }

    @Override // org.telegram.ui.Components.UsersAlertBase
    public void updateColorKeys() {
        this.keyScrollUp = Theme.key_voipgroup_scrollUp;
        this.keyListSelector = Theme.key_voipgroup_listSelector;
        this.keySearchBackground = Theme.key_voipgroup_searchBackground;
        this.keyInviteMembersBackground = Theme.key_voipgroup_inviteMembersBackground;
        this.keyListViewBackground = Theme.key_voipgroup_listViewBackground;
        this.keyActionBarUnscrolled = Theme.key_voipgroup_actionBarUnscrolled;
        this.keyNameText = Theme.key_voipgroup_nameText;
        this.keyLastSeenText = Theme.key_voipgroup_lastSeenText;
        this.keyLastSeenTextUnscrolled = Theme.key_voipgroup_lastSeenTextUnscrolled;
        this.keySearchPlaceholder = Theme.key_voipgroup_searchPlaceholder;
        this.keySearchText = Theme.key_voipgroup_searchText;
        this.keySearchIcon = Theme.key_voipgroup_mutedIcon;
        this.keySearchIconUnscrolled = Theme.key_voipgroup_mutedIconUnscrolled;
    }
}
