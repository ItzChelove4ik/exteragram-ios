package org.telegram.ui.Components;

import android.content.SharedPreferences;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.export.ui.ExportMapper$$ExternalSyntheticLambda2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_communities;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.CollapseTextCell;
import org.telegram.ui.Cells.TextCheckCell2;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class DeleteMessagesBottomSheet extends BottomSheetWithRecyclerListView {
    private ButtonWithCounterView actionButton;
    private UniversalAdapter adapter;
    private boolean banChecked;
    private boolean[] banFilter;
    private boolean banFromCommunity;
    private TL_communities.ParticipantJoinedChats banFromCommunityChats;
    private long banFromCommunityDialogId;
    private Action banOrRestrict;
    private TLRPC.TL_chatBannedRights bannedRights;
    private boolean canRestrict;
    private TLRPC.TL_chatBannedRights defaultBannedRights;
    private Action deleteAll;
    private Action deleteAllReactions;
    private TLRPC.Chat inChat;
    private TLRPC.Chat inCommunity;
    private boolean isForum;
    private final boolean isReactionOnlyMode;
    private final boolean isSingleUsersMode;
    private long mergeDialogId;
    private ArrayList<MessageObject> messages;
    private int mode;
    private boolean monoforum;
    private Runnable onDelete;
    private int[] participantMessageCounts;
    private boolean participantMessageCountsLoaded;
    private boolean participantMessageCountsLoading;
    private ArrayList<TLRPC.TL_chatBannedRights> participantsBannedRights;
    private Action report;
    private boolean restrict;
    private boolean[] restrictFilter;
    private boolean restrictUserCollapsed;
    private boolean restrictUserDeleteAllMessages;
    private boolean restrictUserDeleteAllReactions;
    private boolean sendMediaCollapsed;
    private float shiftDp;
    private int topicId;

    /* renamed from: org.telegram.ui.Components.DeleteMessagesBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends DefaultItemAnimator {
        final /* synthetic */ DeleteMessagesBottomSheet this$0;

        public AnonymousClass1(DeleteMessagesBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            super.onMoveAnimationUpdate(r1);
            DeleteMessagesBottomSheet.access$000(this.this$0).invalidate();
        }
    }

    public class Action {
        boolean[] checks;
        boolean collapsed;
        boolean[] filter;
        int filteredCount;
        ArrayList<TLObject> options;
        int selectedCount;
        final /* synthetic */ DeleteMessagesBottomSheet this$0;
        String title;
        int totalCount;
        int type;

        public Action(DeleteMessagesBottomSheet r1, int r2, ArrayList<TLObject> r3) {
            this.this$0 = r1;
            this.type = r2;
            int r12 = r3.size();
            this.totalCount = r12;
            this.selectedCount = 0;
            if (r12 <= 0) goto L6;
            this.options = r3;
            this.checks = new boolean[r12];
            this.collapsed = true;
            updateTitle();
            return;
        }

        public boolean areAllSelected() {
            int r1 = 0;
        L4:
            if (r1 >= this.totalCount) goto L14;
            if (this.checks[r1] == false) goto L13;
            boolean[] r2 = this.filter;
            if (r2 == null) goto L12;
            if (r2[r1] == false) goto L13;
        L12:
            r1 = r1 + 1;
        L13:
            return false;
        L14:
            return true;
        }

        public void collapseOrExpand() {
            this.collapsed = !this.collapsed;
            DeleteMessagesBottomSheet.m10118$$Nest$fgetadapter(this.this$0).update(true);
        }

        public TLObject first() {
            int r0 = 0;
        L4:
            if (r0 >= this.totalCount) goto L13;
            boolean[] r1 = this.filter;
            if (r1 == null) goto L12;
            if (r1[r0] == true) goto L12;
            r0 = r0 + 1;
        L12:
            return this.options.get(r0);
        L13:
            return null;
        }

        public void forEach(Utilities.IndexedConsumer<TLObject> r3) {
            int r0 = 0;
        L4:
            if (r0 >= this.totalCount) goto L11;
            boolean[] r1 = this.filter;
            if (r1 != null) goto L8;
        L9:
            r3.accept(this.options.get(r0), r0);
        L10:
            r0 = r0 + 1;
            goto L4
        L8:
            if (r1[r0] == false) goto L10;
        }

        public void forEachSelected(Utilities.IndexedConsumer<TLObject> r3) {
            int r0 = 0;
        L4:
            if (r0 >= this.totalCount) goto L13;
            if (this.checks[r0] == false) goto L12;
            boolean[] r1 = this.filter;
            if (r1 != null) goto L10;
        L11:
            r3.accept(this.options.get(r0), r0);
            goto L12
        L10:
            if (r1[r0] == true) goto L11;
        L12:
            r0 = r0 + 1;
            goto L4
        }

        public int getCount() {
            if (this.filter == null) goto L7;
            return this.filteredCount;
        L7:
            return this.totalCount;
        }

        public boolean isExpandable() {
            if (getCount() <= 1) goto L5;
            return true;
        L5:
            return false;
        }

        public boolean isOneSelected() {
            int r1 = 0;
        L4:
            if (r1 >= this.totalCount) goto L14;
            if (this.checks[r1] == false) goto L13;
            boolean[] r2 = this.filter;
            if (r2 == null) goto L11;
            if (r2[r1] == false) goto L13;
            return true;
        L11:
            return true;
        L13:
            r1 = r1 + 1;
            goto L4
        L14:
            return false;
        }

        public boolean isPresent() {
            if (getCount() <= 0) goto L6;
            return true;
        L6:
            return false;
        }

        public void setAllChecks(boolean r2) {
            setAllChecks(r2, true);
        }

        public void setAllChecks(boolean r2, boolean r3) {
            Arrays.fill(this.checks, r2);
            updateCounters();
            if (r3 == false) goto L6;
            DeleteMessagesBottomSheet.m10118$$Nest$fgetadapter(this.this$0).update(true);
            return;
        }

        public void setFilter(boolean[] r2) {
            if (this.totalCount != 0) goto L5;
            return;
        L5:
            this.filter = r2;
            updateCounters();
            updateTitle();
        }

        public void toggleAllChecks() {
            setAllChecks(!isOneSelected());
        }

        public void toggleCheck(int r4) {
            boolean[] r0 = this.filter;
            if (r0 != null) goto L5;
        L7:
            boolean[] r02 = this.checks;
            boolean r1 = r02[r4];
            r02[r4] = !r1;
            int r42 = this.selectedCount;
            if (r1 == true) goto L10;
            this.selectedCount = r42 + 1;
        L11:
            DeleteMessagesBottomSheet.m10118$$Nest$fgetadapter(this.this$0).update(true);
            return;
        L10:
            this.selectedCount = r42 - 1;
            goto L11
        L5:
            if (r0[r4] == true) goto L7;
        }

        public void updateCounters() {
            int r0 = 0;
            this.selectedCount = 0;
            this.filteredCount = 0;
        L4:
            if (r0 >= this.totalCount) goto L16;
            boolean[] r1 = this.filter;
            if (r1 != null) goto L11;
            if (this.checks[r0] == false) goto L15;
            this.selectedCount++;
        L15:
            r0 = r0 + 1;
            goto L4
        L11:
            if (r1[r0] == false) goto L15;
            this.filteredCount++;
            if (this.checks[r0] == false) goto L15;
            this.selectedCount++;
            goto L15
        }

        public void updateTitle() {
            if (this.totalCount == 0) goto L46;
            TLObject r0 = first();
            if ((r0 instanceof TLRPC.User) == false) goto L8;
            String r02 = UserObject.getForcedFirstName((TLRPC.User) r0);
        L9:
            int r1 = this.type;
            if (r1 != 0) goto L14;
            this.title = LocaleController.getString(R.string.DeleteReportSpam);
            return;
        L14:
            if (r1 != 1) goto L22;
            if (isExpandable() == false) goto L18;
            String r03 = LocaleController.getString(R.string.DeleteAllMessagesFromUsers);
        L19:
            this.title = r03;
            return;
        L18:
            r03 = LocaleController.formatString(R.string.DeleteAllFrom, new Object[]{r02});
            goto L19
        L22:
            if (r1 != 3) goto L30;
            if (isExpandable() == false) goto L26;
            String r04 = LocaleController.getString(R.string.DeleteAllReactionsFromUsers);
        L27:
            this.title = r04;
            return;
        L26:
            r04 = LocaleController.formatString(R.string.DeleteAllReactionsFrom, new Object[]{r02});
            goto L27
        L30:
            if (r1 == 2) goto L32;
            return;
        L32:
            if (DeleteMessagesBottomSheet.m10119$$Nest$fgetrestrict(this.this$0) == false) goto L40;
            if (isExpandable() == false) goto L36;
            String r05 = LocaleController.getString(R.string.DeleteRestrictUsers);
        L37:
            this.title = r05;
            return;
        L36:
            r05 = LocaleController.formatString(R.string.DeleteRestrict, new Object[]{r02});
            goto L37
        L40:
            if (isExpandable() == false) goto L42;
            String r06 = LocaleController.getString(R.string.DeleteBanUsers);
        L43:
            this.title = r06;
            return;
        L42:
            r06 = LocaleController.formatString(R.string.DeleteBan, new Object[]{r02});
            goto L43
        L8:
            r02 = ContactsController.formatName(r0);
            goto L9
        }
    }

    public static /* synthetic */ void $r8$lambda$07PaQnSGIeDVf6A4rtdlJykStuM(DeleteMessagesBottomSheet r0, TLObject r1, int r2) {
        r0.lambda$performDelete$24(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$35qXVpSNhjhZVWk1ZIQyYdcwRIc(DeleteMessagesBottomSheet r0, MessageObject r1) {
        return r0.lambda$performDelete$19(r1);
    }

    public static /* synthetic */ void $r8$lambda$3aD_GFuyKFLa8QoUV4uVSjGfOx8(ArrayList r1, Action r2, TLObject r3, int r4) {
        r1.add(UItem.asUserCheckbox((r2.type << 24) | r4, r3).setChecked(r2.checks[r4]).setPad(1));
    }

    public static /* synthetic */ void $r8$lambda$4soAuJ2i58OmZrzK9uf3uihtFXI(DeleteMessagesBottomSheet r0, TLObject r1, TLRPC.InputPeer r2, int r3, int[] r4) {
        r0.lambda$updateParticipantMessageCounts$6(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$86FJ7aYVXPFqmf3TQ6wq5ZYuHYQ(TLRPC.Bool r0, TLRPC.TL_error r1) {
    }

    /* renamed from: $r8$lambda$8JR7vqCgfxZePcpzmTzTMj-J_sU, reason: not valid java name */
    public static /* synthetic */ void m10108$r8$lambda$8JR7vqCgfxZePcpzmTzTMjJ_sU(DeleteMessagesBottomSheet r0, long r1) {
        r0.lambda$fillItems$12(r1);
    }

    /* renamed from: $r8$lambda$GSpXnIQG9mVILkjemsHzN-hQ5YA, reason: not valid java name */
    public static /* synthetic */ void m10109$r8$lambda$GSpXnIQG9mVILkjemsHzNhQ5YA(DeleteMessagesBottomSheet r0, TLObject r1, int r2) {
        r0.lambda$performDelete$22(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$MWuZ_mhaFJOpxbQgOW4oIDpVXL0(DeleteMessagesBottomSheet r0, UniversalAdapter r1, View r2) {
        r0.lambda$fillItems$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$POHGwk5wNYIWtGQBhf22Uq9NjVY(DeleteMessagesBottomSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    /* renamed from: $r8$lambda$QIvH-7bEXEguG4GwIXI9sq6go7E, reason: not valid java name */
    public static /* synthetic */ void m10110$r8$lambda$QIvH7bEXEguG4GwIXI9sq6go7E(DeleteMessagesBottomSheet r0) {
        r0.lambda$proceed$26();
    }

    /* renamed from: $r8$lambda$QUv3cgwFT-XSWXwE54QKQYxRR5s, reason: not valid java name */
    public static /* synthetic */ TLRPC.TL_chatBannedRights m10111$r8$lambda$QUv3cgwFTXSWXwE54QKQYxRR5s(TLRPC.ChannelParticipant r0) {
        if (r0 != null) goto L6;
        return null;
    L6:
        return r0.banned_rights;
    }

    public static /* synthetic */ void $r8$lambda$R6F8dXVwzQ0KWcXghExadhBeGOc(DeleteMessagesBottomSheet r0, int r1, UniversalAdapter r2, View r3) {
        r0.lambda$fillItems$11(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$WCmA7SvGZZAS3-ctn1NbXb8NPyA, reason: not valid java name */
    public static /* synthetic */ void m10112$r8$lambda$WCmA7SvGZZAS3ctn1NbXb8NPyA(DeleteMessagesBottomSheet r0, TLObject r1, int r2) {
        r0.lambda$performDelete$18(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$WfRAz7HB3tQPK1RX3c4526Xsr_I(DeleteMessagesBottomSheet r0, TL_communities.ParticipantJoinedChats r1, TLRPC.TL_error r2) {
        r0.lambda$new$2(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$Z6edFMb0U5mfK8D1txzIg4DltdQ(TLRPC.InputPeer r0, MessageObject r1) {
        return MessageObject.peersEqual(r0, r1.messageOwner.from_id);
    }

    public static /* synthetic */ void $r8$lambda$aV2joAVutLkjRe8wbuvYsNW6ozI(DeleteMessagesBottomSheet r0, Action r1, View r2) {
        r0.lambda$fillAction$8(r1, r2);
    }

    /* renamed from: $r8$lambda$fR8mukw9QSUO8tZCWJVn-_dbhKM, reason: not valid java name */
    public static /* synthetic */ void m10113$r8$lambda$fR8mukw9QSUO8tZCWJVn_dbhKM(DeleteMessagesBottomSheet r0) {
        r0.lambda$fillItems$13();
    }

    public static /* synthetic */ void $r8$lambda$fcEhyIKCJGYsWj2q5ddtfwlElcc(DeleteMessagesBottomSheet r0, long r1) {
        r0.lambda$proceed$25(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$gw8LTTCkjGeKs402AxemaLkVg0A(TLObject r2, MessageObject r3) {
        if ((r2 instanceof TLRPC.User) == false) goto L8;
        if (r3.messageOwner.from_id.user_id == ((TLRPC.User) r2).id) goto L15;
        return false;
    L15:
        return true;
    L8:
        if ((r2 instanceof TLRPC.Chat) == true) goto L10;
        return false;
    L10:
        if (r3.messageOwner.from_id.user_id != ((TLRPC.Chat) r2).id) goto L17;
        return true;
    L17:
        return false;
    }

    /* renamed from: $r8$lambda$hJWlIMFy5TJJzXHC6e-oDw4GtSE, reason: not valid java name */
    public static /* synthetic */ boolean m10114$r8$lambda$hJWlIMFy5TJJzXHC6eoDw4GtSE(DeleteMessagesBottomSheet r0, MessageObject r1) {
        return r0.lambda$performDelete$16(r1);
    }

    public static /* synthetic */ void $r8$lambda$haoAPRIUoowoPluY1Z0IGSjqVTY(DeleteMessagesBottomSheet r0, View r1, int r2, float r3, float r4) {
        r0.lambda$new$0(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$herzxpai-0Se8wfZdEMnAwN1Uh8, reason: not valid java name */
    public static /* synthetic */ boolean m10115$r8$lambda$herzxpai0Se8wfZdEMnAwN1Uh8(DeleteMessagesBottomSheet r0, MessageObject r1) {
        return r0.lambda$performDelete$15(r1);
    }

    public static /* synthetic */ void $r8$lambda$i3PmP4NbRslT0lEpyOWYsXOOibM(DeleteMessagesBottomSheet r0, TLObject r1, int r2) {
        r0.lambda$performDelete$23(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$lr5TI7IwGYMRpFSQInDIQ_kTjw0(DeleteMessagesBottomSheet r0, int[] r1, TLObject r2, int r3) {
        r0.lambda$getTitle$4(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$mIyG_zoj8xyA-_KT6unVuhohYXE, reason: not valid java name */
    public static /* synthetic */ void m10116$r8$lambda$mIyG_zoj8xyA_KT6unVuhohYXE(DeleteMessagesBottomSheet r0, TLRPC.InputPeer r1, int r2, int[] r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$updateParticipantMessageCounts$7(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$mPEhV-OPHawr-3RqmJcXjVYnauU, reason: not valid java name */
    public static /* synthetic */ void m10117$r8$lambda$mPEhVOPHawr3RqmJcXjVYnauU(DeleteMessagesBottomSheet r0, TLObject r1, int r2) {
        r0.lambda$performDelete$21(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$sjSz9wb_ARcdT3udoPGEFTgmFVg(DeleteMessagesBottomSheet r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$umC2lTsxTYGr6tJO0J1Qjo9jgiI(DeleteMessagesBottomSheet r0, ArrayList r1, ArrayList r2, TLObject r3, int r4) {
        r0.lambda$performDelete$17(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalAdapter m10118$$Nest$fgetadapter(DeleteMessagesBottomSheet r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetrestrict, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10119$$Nest$fgetrestrict(DeleteMessagesBottomSheet r0) {
        return r0.restrict;
    }

    public DeleteMessagesBottomSheet(BaseFragment r20, TLRPC.Chat r21, ArrayList<MessageObject> r22, ArrayList<TLObject> r23, TLRPC.ChannelParticipant[] r24, long r25, int r27, int r28, boolean r29, Runnable r30) {
        super(r20.getContext(), r20, false, false, false, true, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, r20.getResourceProvider());
        this.restrict = false;
        this.participantMessageCountsLoading = false;
        this.participantMessageCountsLoaded = false;
        this.sendMediaCollapsed = true;
        this.restrictUserCollapsed = true;
        this.restrictUserDeleteAllMessages = false;
        this.restrictUserDeleteAllReactions = false;
        this.shiftDp = 10.0f;
        int r4 = Theme.key_windowBackgroundGray;
        setBackgroundColor(getThemedColor(r4));
        setShowHandle(true);
        fixNavigationBar(getThemedColor(r4));
        this.takeTranslationIntoAccount = true;
        this.isReactionOnlyMode = r29;
        RecyclerListView r42 = this.recyclerListView;
        int r5 = this.backgroundPaddingLeft;
        r42.setPadding(r5, this.headerTotalHeight, r5, AndroidUtilities.dp(63.0f));
        this.recyclerListView.setClipToPadding(false);
        this.recyclerListView.setOnItemClickListener(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda1(this));
        this.takeTranslationIntoAccount = true;
        AnonymousClass1 r43 = new AnonymousClass1(this);
        r43.setSupportsChangeAnimations(false);
        r43.setDelayAnimations(false);
        r43.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r43.setDurations(350);
        this.recyclerListView.setItemAnimator(r43);
        this.recyclerListView.setSections();
        ButtonWithCounterView r44 = new ButtonWithCounterView(getContext(), true, this.resourcesProvider);
        this.actionButton = r44;
        r44.setRound();
        this.actionButton.setText(LocaleController.getString(R.string.DeleteProceedBtn));
        this.actionButton.setOnClickListener(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda2(this));
        this.containerView.addView(this.actionButton, LayoutHelper.createFrameMarginPx(-1, 48.0f, 87, this.backgroundPaddingLeft + AndroidUtilities.dp(10.0f), 0, this.backgroundPaddingLeft + AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f)));
        this.inChat = r21;
        this.isForum = ChatObject.isForum(r21);
        this.messages = r22;
        this.mergeDialogId = r25;
        this.topicId = r27;
        this.mode = r28;
        this.onDelete = r30;
        this.defaultBannedRights = r21.default_banned_rights;
        TLRPC.TL_chatBannedRights r3 = new TLRPC.TL_chatBannedRights();
        this.bannedRights = r3;
        TLRPC.TL_chatBannedRights r45 = this.defaultBannedRights;
        if (r45.view_messages == false) goto L6;
        r3.view_messages = true;
    L6:
        if (r45.send_messages == false) goto L9;
        r3.send_messages = true;
    L9:
        if (r45.send_media == false) goto L12;
        r3.send_media = true;
    L12:
        if (r45.send_stickers == false) goto L15;
        r3.send_stickers = true;
    L15:
        if (r45.send_gifs == false) goto L18;
        r3.send_gifs = true;
    L18:
        if (r45.send_games == false) goto L21;
        r3.send_games = true;
    L21:
        if (r45.send_inline == false) goto L24;
        r3.send_inline = true;
    L24:
        if (r45.embed_links == false) goto L27;
        r3.embed_links = true;
    L27:
        if (r45.send_polls == false) goto L30;
        r3.send_polls = true;
    L30:
        if (r45.invite_users == false) goto L33;
        r3.invite_users = true;
    L33:
        if (r45.change_info == false) goto L36;
        r3.change_info = true;
    L36:
        if (r45.pin_messages == false) goto L39;
        r3.pin_messages = true;
    L39:
        if (r45.manage_topics == false) goto L42;
        r3.manage_topics = true;
    L42:
        if (r45.send_photos == false) goto L45;
        r3.send_photos = true;
    L45:
        if (r45.send_videos == false) goto L48;
        r3.send_videos = true;
    L48:
        if (r45.send_audios == false) goto L51;
        r3.send_audios = true;
    L51:
        if (r45.send_docs == false) goto L54;
        r3.send_docs = true;
    L54:
        if (r45.send_voices == false) goto L57;
        r3.send_voices = true;
    L57:
        if (r45.send_roundvideos == false) goto L60;
        r3.send_roundvideos = true;
    L60:
        if (r45.send_plain == false) goto L63;
        r3.send_plain = true;
    L63:
        if (r45.send_reactions == false) goto L65;
        r3.send_reactions = true;
    L65:
        MessagesController.getInstance(this.currentAccount).getMainSettings();
        this.report = new Action(this, 0, r23);
        this.deleteAll = new Action(this, 1, r23);
        this.deleteAllReactions = new Action(this, 3, r23);
        if (r23.size() != 1) goto L68;
        boolean r32 = true;
    L69:
        this.isSingleUsersMode = r32;
        this.monoforum = ChatObject.isMonoForum(r21);
        if (r21.linked_community_id == 0) goto L73;
        this.inCommunity = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r21.linked_community_id));
    L73:
        if (ChatObject.canUserDoAdminAction(this.inCommunity, 2) == false) goto L79;
        if (ChatObject.canUserDoAdminAction(this.inCommunity, 27) == false) goto L79;
        if (r32 == false) goto L79;
        this.banFromCommunityDialogId = DialogObject.getDialogId(r23.get(0));
        MessagesController.getInstance(this.currentAccount).fetchCommunityJoinedChats(this.inCommunity.id, this.banFromCommunityDialogId, new DeleteMessagesBottomSheet$$ExternalSyntheticLambda3(this));
    L79:
        if (ChatObject.canBlockUsers(r21) == false) goto L126;
        this.banFilter = new boolean[r23.size()];
        int r33 = 0;
    L81:
        TLRPC.ChannelParticipant r6 = null;
        if (r33 >= r23.size()) goto L102;
        if (r33 >= r24.length) goto L87;
        r6 = r24[r33];
    L87:
        if (r21.creator == true) goto L94;
        if ((r6 instanceof TLRPC.TL_channelParticipantAdmin) == true) goto L101;
        if ((r6 instanceof TLRPC.TL_channelParticipantCreator) == false) goto L94;
    L101:
        r33 = r33 + 1;
    L94:
        if ((r6 instanceof TLRPC.TL_channelParticipantBanned) == false) goto L100;
        TLRPC.TL_chatBannedRights r46 = r6.banned_rights;
        if (r46 == null) goto L100;
        if (isBanned(r46) == true) goto L101;
    L100:
        this.banFilter[r33] = true;
        goto L101
    L102:
        this.restrictFilter = new boolean[r23.size()];
        if (hasAnyDefaultRights() == false) goto L125;
        int r34 = 0;
    L106:
        if (r34 >= r23.size()) goto L125;
        if (r34 >= r24.length) goto L110;
        TLRPC.ChannelParticipant r47 = r24[r34];
    L112:
        if ((r23.get(r34) instanceof TLRPC.Chat) == true) goto L124;
        if ((r47 instanceof TLRPC.TL_channelParticipantBanned) == false) goto L122;
        TLRPC.TL_chatBannedRights r48 = r47.banned_rights;
        if (r48 == null) goto L122;
        if (canBeRestricted(r48) == false) goto L124;
    L122:
        if (this.banFilter[r34] == false) goto L124;
        this.restrictFilter[r34] = true;
        this.canRestrict = true;
    L124:
        r34 = r34 + 1;
        goto L106
    L110:
        r47 = null;
    L125:
        this.participantsBannedRights = (ArrayList) Arrays.stream(r24).map(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda4()).collect(Collectors.toCollection(new ExportMapper$$ExternalSyntheticLambda2()));
        Action r2 = new Action(this, 2, r23);
        this.banOrRestrict = r2;
        r2.setFilter(this.banFilter);
    L127:
        this.adapter.update(false);
        this.actionBar.setTitle(getTitle());
        return;
    L126:
        this.banOrRestrict = new Action(this, 2, new ArrayList(0));
        goto L127
    L68:
        r32 = false;
        goto L69
    }

    public static /* synthetic */ ViewGroup access$000(DeleteMessagesBottomSheet r0) {
        return r0.containerView;
    }

    private boolean allDefaultMediaBanned() {
        TLRPC.TL_chatBannedRights r1 = this.defaultBannedRights;
        if (r1.send_photos == true) goto L5;
        return false;
    L5:
        if (r1.send_videos == true) goto L7;
        return false;
    L7:
        if (r1.send_stickers == true) goto L9;
        return false;
    L9:
        if (r1.send_audios == true) goto L11;
        return false;
    L11:
        if (r1.send_docs == true) goto L13;
        return false;
    L13:
        if (r1.send_voices == true) goto L15;
        return false;
    L15:
        if (r1.send_roundvideos == true) goto L17;
        return false;
    L17:
        if (r1.embed_links == true) goto L19;
        return false;
    L19:
        if (r1.send_polls == true) goto L21;
        return false;
    L21:
        if (r1.send_reactions == false) goto L34;
        return true;
    L34:
        return false;
    }

    public static TLRPC.TL_chatBannedRights bannedRightsOr(TLRPC.TL_chatBannedRights r4, TLRPC.TL_chatBannedRights r5) {
        if (r4 != null) goto L4;
        return r5;
    L4:
        if (r5 != null) goto L6;
        return r4;
    L6:
        TLRPC.TL_chatBannedRights r0 = new TLRPC.TL_chatBannedRights();
        boolean r2 = true;
        if (r4.view_messages == false) goto L9;
    L12:
        boolean r1 = true;
    L13:
        r0.view_messages = r1;
        if (r4.send_messages == false) goto L16;
    L19:
        boolean r12 = true;
    L20:
        r0.send_messages = r12;
        if (r4.send_media == false) goto L23;
    L26:
        boolean r13 = true;
    L27:
        r0.send_media = r13;
        if (r4.send_stickers == false) goto L30;
    L33:
        boolean r14 = true;
    L34:
        r0.send_stickers = r14;
        if (r4.send_gifs == false) goto L37;
    L40:
        boolean r15 = true;
    L41:
        r0.send_gifs = r15;
        if (r4.send_games == false) goto L44;
    L47:
        boolean r16 = true;
    L48:
        r0.send_games = r16;
        if (r4.send_inline == false) goto L51;
    L54:
        boolean r17 = true;
    L55:
        r0.send_inline = r17;
        if (r4.embed_links == false) goto L58;
    L61:
        boolean r18 = true;
    L62:
        r0.embed_links = r18;
        if (r4.send_polls == false) goto L65;
    L68:
        boolean r19 = true;
    L69:
        r0.send_polls = r19;
        if (r4.send_reactions == false) goto L72;
    L75:
        boolean r110 = true;
    L76:
        r0.send_reactions = r110;
        if (r4.change_info == false) goto L79;
    L82:
        boolean r111 = true;
    L83:
        r0.change_info = r111;
        if (r4.invite_users == false) goto L86;
    L89:
        boolean r112 = true;
    L90:
        r0.invite_users = r112;
        if (r4.pin_messages == false) goto L93;
    L96:
        boolean r113 = true;
    L97:
        r0.pin_messages = r113;
        if (r4.manage_topics == false) goto L100;
    L103:
        boolean r114 = true;
    L104:
        r0.manage_topics = r114;
        if (r4.send_photos == false) goto L107;
    L110:
        boolean r115 = true;
    L111:
        r0.send_photos = r115;
        if (r4.send_videos == false) goto L114;
    L117:
        boolean r116 = true;
    L118:
        r0.send_videos = r116;
        if (r4.send_roundvideos == false) goto L121;
    L124:
        boolean r117 = true;
    L125:
        r0.send_roundvideos = r117;
        if (r4.send_audios == false) goto L128;
    L131:
        boolean r118 = true;
    L132:
        r0.send_audios = r118;
        if (r4.send_voices == false) goto L135;
    L138:
        boolean r119 = true;
    L139:
        r0.send_voices = r119;
        if (r4.send_docs == false) goto L142;
    L145:
        boolean r120 = true;
    L146:
        r0.send_docs = r120;
        if (r4.send_plain == false) goto L149;
    L152:
        r0.send_plain = r2;
        return r0;
    L149:
        if (r5.send_plain == true) goto L152;
        r2 = false;
        goto L152
    L142:
        if (r5.send_docs == true) goto L145;
        r120 = false;
        goto L146
    L135:
        if (r5.send_voices == true) goto L138;
        r119 = false;
        goto L139
    L128:
        if (r5.send_audios == true) goto L131;
        r118 = false;
        goto L132
    L121:
        if (r5.send_roundvideos == true) goto L124;
        r117 = false;
        goto L125
    L114:
        if (r5.send_videos == true) goto L117;
        r116 = false;
        goto L118
    L107:
        if (r5.send_photos == true) goto L110;
        r115 = false;
        goto L111
    L100:
        if (r5.manage_topics == true) goto L103;
        r114 = false;
        goto L104
    L93:
        if (r5.pin_messages == true) goto L96;
        r113 = false;
        goto L97
    L86:
        if (r5.invite_users == true) goto L89;
        r112 = false;
        goto L90
    L79:
        if (r5.change_info == true) goto L82;
        r111 = false;
        goto L83
    L72:
        if (r5.send_reactions == true) goto L75;
        r110 = false;
        goto L76
    L65:
        if (r5.send_polls == true) goto L68;
        r19 = false;
        goto L69
    L58:
        if (r5.embed_links == true) goto L61;
        r18 = false;
        goto L62
    L51:
        if (r5.send_inline == true) goto L54;
        r17 = false;
        goto L55
    L44:
        if (r5.send_games == true) goto L47;
        r16 = false;
        goto L48
    L37:
        if (r5.send_gifs == true) goto L40;
        r15 = false;
        goto L41
    L30:
        if (r5.send_stickers == true) goto L33;
        r14 = false;
        goto L34
    L23:
        if (r5.send_media == true) goto L26;
        r13 = false;
        goto L27
    L16:
        if (r5.send_messages == true) goto L19;
        r12 = false;
        goto L20
    L9:
        if (r5.view_messages == true) goto L12;
        r1 = false;
        goto L13
    }

    private boolean canBeRestricted(TLRPC.TL_chatBannedRights r3) {
        if (r3.send_stickers == true) goto L7;
        if (this.defaultBannedRights.send_stickers == true) goto L7;
        return true;
    L7:
        if (r3.send_gifs == true) goto L11;
        if (this.defaultBannedRights.send_gifs == true) goto L11;
        return true;
    L11:
        if (r3.send_games == true) goto L15;
        if (this.defaultBannedRights.send_games == true) goto L15;
        return true;
    L15:
        if (r3.send_inline == true) goto L19;
        if (this.defaultBannedRights.send_inline == true) goto L19;
        return true;
    L19:
        if (r3.embed_links == true) goto L27;
        if (r3.send_plain == true) goto L27;
        TLRPC.TL_chatBannedRights r0 = this.defaultBannedRights;
        if (r0.embed_links == true) goto L27;
        if (r0.send_plain == true) goto L27;
        return true;
    L27:
        if (r3.send_polls == true) goto L31;
        if (this.defaultBannedRights.send_polls == true) goto L31;
        return true;
    L31:
        if (r3.send_reactions == true) goto L35;
        if (this.defaultBannedRights.send_reactions == true) goto L35;
        return true;
    L35:
        if (r3.change_info == true) goto L39;
        if (this.defaultBannedRights.change_info == true) goto L39;
        return true;
    L39:
        if (r3.invite_users == true) goto L43;
        if (this.defaultBannedRights.invite_users == true) goto L43;
        return true;
    L43:
        if (r3.pin_messages == true) goto L47;
        if (this.defaultBannedRights.pin_messages == true) goto L47;
        return true;
    L47:
        if (r3.manage_topics == true) goto L53;
        if (this.defaultBannedRights.manage_topics == true) goto L53;
        if (this.isForum == false) goto L53;
        return true;
    L53:
        if (r3.send_photos == true) goto L57;
        if (this.defaultBannedRights.send_photos == true) goto L57;
        return true;
    L57:
        if (r3.send_videos == true) goto L61;
        if (this.defaultBannedRights.send_videos == true) goto L61;
        return true;
    L61:
        if (r3.send_roundvideos == true) goto L65;
        if (this.defaultBannedRights.send_roundvideos == true) goto L65;
        return true;
    L65:
        if (r3.send_audios == true) goto L69;
        if (this.defaultBannedRights.send_audios == true) goto L69;
        return true;
    L69:
        if (r3.send_voices == true) goto L73;
        if (this.defaultBannedRights.send_voices == true) goto L73;
        return true;
    L73:
        if (r3.send_docs == true) goto L77;
        if (this.defaultBannedRights.send_docs == true) goto L77;
        return true;
    L77:
        if (r3.send_plain == false) goto L79;
        return false;
    L79:
        if (this.defaultBannedRights.send_plain == true) goto L101;
        return true;
    L101:
        return false;
    }

    private void fillAction(ArrayList<UItem> r6, Action r7) {
        if (r7.isPresent() == false) goto L24;
        boolean r0 = r7.isExpandable();
        int r1 = r7.type;
        boolean r2 = false;
        if (r0 == true) goto L12;
        UItem r5 = UItem.asRoundCheckbox(r1, r7.title);
        if (r7.selectedCount <= 0) goto L10;
        r2 = true;
    L10:
        r6.add(r5.setChecked(r2));
        return;
    L12:
        String r02 = r7.title;
        int r4 = r7.selectedCount;
        if (r4 > 0) goto L16;
        r4 = r7.getCount();
    L16:
        UItem r03 = UItem.asUserGroupCheckbox(r1, r02, String.valueOf(r4));
        if (r7.selectedCount <= 0) goto L19;
        r2 = true;
    L19:
        r6.add(r03.setChecked(r2).setCollapsed(r7.collapsed).setClickCallback(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda23(this, r7)));
        if (r7.collapsed == true) goto L23;
        r7.forEach(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda24(r6, r7));
        return;
    L23:
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void fillItems(ArrayList<UItem> r10, UniversalAdapter r11) {
        if (this.messages == null) goto L144;
        r10.add(UItem.asSpace(AndroidUtilities.dp(12.0f)));
        r10.add(UItem.asHeader(LocaleController.getString(R.string.DeleteAdditionalActions)));
        fillAction(r10, this.report);
        boolean r1 = this.isSingleUsersMode;
        Action r2 = this.deleteAll;
        if (r1 == false) goto L14;
        r2.updateTitle();
        int r12 = (this.restrictUserDeleteAllMessages ? 1 : 0) + (this.restrictUserDeleteAllReactions ? 1 : 0);
        UItem r22 = UItem.asRoundGroupCheckbox(100, this.deleteAll.title, String.format(Locale.US, "%d/2", new Object[]{Integer.valueOf(r12)}));
        if (r12 != 2) goto L10;
        boolean r13 = true;
    L11:
        r10.add(r22.setChecked(r13).setCollapsed(this.restrictUserCollapsed).setClickCallback(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda7(this, r11)));
        if (this.restrictUserCollapsed == true) goto L15;
        r10.add(UItem.asRoundCheckbox(101, LocaleController.getString(R.string.RestrictUserDeleteAllMessages)).setChecked(this.restrictUserDeleteAllMessages).setPad(1));
        r10.add(UItem.asRoundCheckbox(102, LocaleController.getString(R.string.RestrictUserDeleteAllReactions)).setChecked(this.restrictUserDeleteAllReactions).setPad(1));
    L15:
        fillAction(r10, this.banOrRestrict);
        if (this.monoforum == false) goto L18;
    L132:
        Object[] r112 = true;
    L134:
        if (this.banFromCommunityDialogId == 0) goto L143;
        if (r112 == false) goto L137;
        r10.add(UItem.asSpace(AndroidUtilities.dp(12.0f)));
    L137:
        r10.add(UItem.asSwitchNoIcon(103, LocaleController.getString(R.string.CommunityBanFromCommunity)).setChecked(this.banFromCommunity));
        TL_communities.ParticipantJoinedChats r113 = this.banFromCommunityChats;
        if (r113 == null) goto L140;
        int r114 = r113.joined_chat_ids.size();
    L141:
        r10.add(UItem.asShadow(104, AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.formatPluralString("CommunityBanFromCommunityInfo", r114, new Object[0]), new DeleteMessagesBottomSheet$$ExternalSyntheticLambda9(this)), true)));
        return;
    L140:
        r114 = 1;
        goto L141
    L143:
        return;
    L18:
        if (this.banOrRestrict.isPresent() == false) goto L132;
        if (this.restrict == false) goto L130;
        r10.add(UItem.asShadow(null));
        if (this.banOrRestrict.isExpandable() == false) goto L24;
        r10.add(UItem.asAnimatedHeader(0, LocaleController.formatPluralString("UserRestrictionsCanDoUsers", this.banOrRestrict.selectedCount, new Object[0])));
    L25:
        UItem r14 = UItem.asSwitch(0, LocaleController.getString(R.string.UserRestrictionsSend));
        if (this.bannedRights.send_plain == false) goto L28;
    L30:
        boolean r23 = false;
    L31:
        r10.add(r14.setChecked(r23).setLocked(this.defaultBannedRights.send_plain));
        int r15 = getSendMediaSelectedCount();
        UItem r24 = UItem.asExpandableSwitch(1, LocaleController.getString(R.string.UserRestrictionsSendMedia), String.format(Locale.US, "%d/10", new Object[]{Integer.valueOf(r15)}));
        if (r15 <= 0) goto L34;
        boolean r6 = true;
    L35:
        r10.add(r24.setChecked(r6).setLocked(allDefaultMediaBanned()).setCollapsed(this.sendMediaCollapsed).setClickCallback(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda8(this, r15, r11)));
        if (this.sendMediaCollapsed == true) goto L102;
        UItem r115 = UItem.asRoundCheckbox(6, LocaleController.getString(R.string.SendMediaPermissionPhotos));
        if (this.bannedRights.send_photos == false) goto L40;
    L42:
        boolean r16 = false;
    L43:
        r10.add(r115.setChecked(r16).setLocked(this.defaultBannedRights.send_photos).setPad(1));
        UItem r116 = UItem.asRoundCheckbox(7, LocaleController.getString(R.string.SendMediaPermissionVideos));
        if (this.bannedRights.send_videos == false) goto L46;
    L48:
        boolean r17 = false;
    L49:
        r10.add(r116.setChecked(r17).setLocked(this.defaultBannedRights.send_videos).setPad(1));
        UItem r117 = UItem.asRoundCheckbox(8, LocaleController.getString(R.string.SendMediaPermissionFiles));
        if (this.bannedRights.send_docs == false) goto L52;
    L54:
        boolean r18 = false;
    L55:
        r10.add(r117.setChecked(r18).setLocked(this.defaultBannedRights.send_docs).setPad(1));
        UItem r118 = UItem.asRoundCheckbox(9, LocaleController.getString(R.string.SendMediaPermissionMusic));
        if (this.bannedRights.send_audios == false) goto L58;
    L60:
        boolean r19 = false;
    L61:
        r10.add(r118.setChecked(r19).setLocked(this.defaultBannedRights.send_audios).setPad(1));
        UItem r119 = UItem.asRoundCheckbox(10, LocaleController.getString(R.string.SendMediaPermissionVoice));
        if (this.bannedRights.send_voices == false) goto L64;
    L66:
        boolean r110 = false;
    L67:
        r10.add(r119.setChecked(r110).setLocked(this.defaultBannedRights.send_voices).setPad(1));
        UItem r1110 = UItem.asRoundCheckbox(11, LocaleController.getString(R.string.SendMediaPermissionRound));
        if (this.bannedRights.send_roundvideos == false) goto L70;
    L72:
        boolean r111 = false;
    L73:
        r10.add(r1110.setChecked(r111).setLocked(this.defaultBannedRights.send_roundvideos).setPad(1));
        UItem r1111 = UItem.asRoundCheckbox(12, LocaleController.getString(R.string.SendMediaPermissionStickersGifs));
        if (this.bannedRights.send_stickers == false) goto L76;
    L78:
        boolean r120 = false;
    L79:
        r10.add(r1111.setChecked(r120).setLocked(this.defaultBannedRights.send_stickers).setPad(1));
        UItem r1112 = UItem.asRoundCheckbox(13, LocaleController.getString(R.string.SendMediaPolls));
        if (this.bannedRights.send_polls == false) goto L82;
    L84:
        boolean r121 = false;
    L85:
        r10.add(r1112.setChecked(r121).setLocked(this.defaultBannedRights.send_polls).setPad(1));
        UItem r1113 = UItem.asRoundCheckbox(14, LocaleController.getString(R.string.UserRestrictionsEmbedLinks));
        TLRPC.TL_chatBannedRights r122 = this.bannedRights;
        if (r122.embed_links == true) goto L94;
        TLRPC.TL_chatBannedRights r25 = this.defaultBannedRights;
        if (r25.embed_links == true) goto L94;
        if (r122.send_plain == true) goto L94;
        if (r25.send_plain == true) goto L94;
        boolean r123 = true;
    L95:
        r10.add(r1113.setChecked(r123).setLocked(this.defaultBannedRights.embed_links).setPad(1));
        UItem r1114 = UItem.asRoundCheckbox(15, LocaleController.getString(R.string.UserRestrictionsSendReactions));
        if (this.bannedRights.send_reactions == false) goto L98;
    L100:
        boolean r124 = false;
    L101:
        r10.add(r1114.setChecked(r124).setLocked(this.defaultBannedRights.send_reactions).setPad(1));
        goto L102
    L98:
        if (this.defaultBannedRights.send_reactions == true) goto L100;
        r124 = true;
    L94:
        r123 = false;
        goto L95
    L82:
        if (this.defaultBannedRights.send_polls == true) goto L84;
        r121 = true;
        goto L85
    L76:
        if (this.defaultBannedRights.send_stickers == true) goto L78;
        r120 = true;
        goto L79
    L70:
        if (this.defaultBannedRights.send_roundvideos == true) goto L72;
        r111 = true;
        goto L73
    L64:
        if (this.defaultBannedRights.send_voices == true) goto L66;
        r110 = true;
        goto L67
    L58:
        if (this.defaultBannedRights.send_audios == true) goto L60;
        r19 = true;
        goto L61
    L52:
        if (this.defaultBannedRights.send_docs == true) goto L54;
        r18 = true;
        goto L55
    L46:
        if (this.defaultBannedRights.send_videos == true) goto L48;
        r17 = true;
        goto L49
    L40:
        if (this.defaultBannedRights.send_photos == true) goto L42;
        r16 = true;
    L102:
        UItem r1115 = UItem.asSwitch(2, LocaleController.getString(R.string.UserRestrictionsInviteUsers));
        if (this.bannedRights.invite_users == false) goto L105;
    L107:
        boolean r125 = false;
    L108:
        r10.add(r1115.setChecked(r125).setLocked(this.defaultBannedRights.invite_users));
        UItem r1116 = UItem.asSwitch(3, LocaleController.getString(R.string.UserRestrictionsPinMessages));
        if (this.bannedRights.pin_messages == false) goto L111;
    L113:
        boolean r126 = false;
    L114:
        r10.add(r1116.setChecked(r126).setLocked(this.defaultBannedRights.pin_messages));
        UItem r1117 = UItem.asSwitch(4, LocaleController.getString(R.string.UserRestrictionsChangeInfo));
        if (this.bannedRights.change_info == false) goto L117;
    L119:
        boolean r127 = false;
    L120:
        r10.add(r1117.setChecked(r127).setLocked(this.defaultBannedRights.change_info));
        if (this.isForum == false) goto L130;
        UItem r1118 = UItem.asSwitch(5, LocaleController.getString(R.string.CreateTopicsPermission));
        if (this.bannedRights.manage_topics == false) goto L125;
    L127:
        boolean r128 = false;
    L128:
        r10.add(r1118.setChecked(r128).setLocked(this.defaultBannedRights.manage_topics));
        goto L130
    L125:
        if (this.defaultBannedRights.manage_topics == true) goto L127;
        r128 = true;
        goto L128
    L117:
        if (this.defaultBannedRights.change_info == true) goto L119;
        r127 = true;
        goto L120
    L111:
        if (this.defaultBannedRights.pin_messages == true) goto L113;
        r126 = true;
        goto L114
    L105:
        if (this.defaultBannedRights.invite_users == true) goto L107;
        r125 = true;
        goto L108
    L34:
        r6 = false;
        goto L35
    L28:
        if (this.defaultBannedRights.send_plain == true) goto L30;
        r23 = true;
        goto L31
    L24:
        r10.add(UItem.asAnimatedHeader(0, LocaleController.getString(R.string.UserRestrictionsCanDo)));
    L130:
        if (this.canRestrict == false) goto L132;
        r10.add(UItem.asShadowCollapseButton(1, LocaleController.getString(getRestrictToggleTextKey())).setCollapsed(!this.restrict).accent());
        r112 = false;
        goto L134
    L10:
        r13 = false;
        goto L11
    L14:
        fillAction(r10, r2);
        fillAction(r10, this.deleteAllReactions);
        goto L15
    }

    private int getRestrictToggleTextKey() {
        boolean r0 = this.banOrRestrict.isExpandable();
        boolean r1 = this.restrict;
        if (r0 == true) goto L9;
        if (r1 == false) goto L8;
        return R.string.DeleteToggleBanUser;
    L8:
        return R.string.DeleteToggleRestrictUser;
    L9:
        if (r1 == false) goto L13;
        return R.string.DeleteToggleBanUsers;
    L13:
        return R.string.DeleteToggleRestrictUsers;
    }

    private int getSendMediaSelectedCount() {
        TLRPC.TL_chatBannedRights r0 = this.bannedRights;
        if (r0.send_photos == false) goto L5;
    L7:
        int r1 = 0;
    L9:
        if (r0.send_videos == true) goto L14;
        if (this.defaultBannedRights.send_videos == true) goto L14;
        r1 = r1 + 1;
    L14:
        if (r0.send_stickers == true) goto L19;
        if (this.defaultBannedRights.send_stickers == true) goto L19;
        r1 = r1 + 1;
    L19:
        if (r0.send_audios == true) goto L24;
        if (this.defaultBannedRights.send_audios == true) goto L24;
        r1 = r1 + 1;
    L24:
        if (r0.send_docs == true) goto L29;
        if (this.defaultBannedRights.send_docs == true) goto L29;
        r1 = r1 + 1;
    L29:
        if (r0.send_voices == true) goto L34;
        if (this.defaultBannedRights.send_voices == true) goto L34;
        r1 = r1 + 1;
    L34:
        if (r0.send_roundvideos == true) goto L39;
        if (this.defaultBannedRights.send_roundvideos == true) goto L39;
        r1 = r1 + 1;
    L39:
        if (r0.embed_links == true) goto L48;
        TLRPC.TL_chatBannedRights r3 = this.defaultBannedRights;
        if (r3.embed_links == true) goto L48;
        if (r0.send_plain == true) goto L48;
        if (r3.send_plain == true) goto L48;
        r1 = r1 + 1;
    L48:
        if (r0.send_polls == true) goto L53;
        if (this.defaultBannedRights.send_polls == true) goto L53;
        r1 = r1 + 1;
    L53:
        if (r0.send_reactions == false) goto L55;
        return r1;
    L55:
        if (this.defaultBannedRights.send_reactions == false) goto L57;
        return r1;
    L57:
        return r1 + 1;
    L5:
        if (this.defaultBannedRights.send_photos == true) goto L7;
        r1 = 1;
        goto L9
    }

    private boolean hasAnyDefaultRights() {
        TLRPC.TL_chatBannedRights r0 = this.defaultBannedRights;
        if (r0.send_messages == true) goto L5;
        return true;
    L5:
        if (r0.send_media == true) goto L7;
        return true;
    L7:
        if (r0.send_stickers == true) goto L9;
        return true;
    L9:
        if (r0.send_gifs == true) goto L11;
        return true;
    L11:
        if (r0.send_games == true) goto L13;
        return true;
    L13:
        if (r0.send_inline == true) goto L15;
        return true;
    L15:
        if (r0.embed_links == true) goto L17;
        return true;
    L17:
        if (r0.send_polls == true) goto L19;
        return true;
    L19:
        if (r0.send_reactions == true) goto L21;
        return true;
    L21:
        if (r0.change_info == true) goto L23;
        return true;
    L23:
        if (r0.invite_users == true) goto L25;
        return true;
    L25:
        if (r0.pin_messages == true) goto L27;
        return true;
    L27:
        if (r0.manage_topics == true) goto L31;
        if (this.isForum == false) goto L31;
        return true;
    L31:
        if (r0.send_photos == true) goto L33;
        return true;
    L33:
        if (r0.send_videos == true) goto L35;
        return true;
    L35:
        if (r0.send_roundvideos == true) goto L37;
        return true;
    L37:
        if (r0.send_audios == true) goto L39;
        return true;
    L39:
        if (r0.send_voices == true) goto L41;
        return true;
    L41:
        if (r0.send_docs == true) goto L43;
        return true;
    L43:
        if (r0.send_plain == false) goto L67;
        return false;
    L67:
        return true;
    }

    private static boolean isBanned(TLRPC.TL_chatBannedRights r0) {
        return r0.view_messages;
    }

    private /* synthetic */ void lambda$fillAction$8(Action r1, View r2) {
        saveScrollPosition();
        r1.collapseOrExpand();
        applyScrolledPosition(true);
    }

    private /* synthetic */ void lambda$fillItems$10(UniversalAdapter r2, View r3) {
        this.restrictUserCollapsed = !this.restrictUserCollapsed;
        r2.update(true);
    }

    private /* synthetic */ void lambda$fillItems$11(int r3, UniversalAdapter r4, View r5) {
        if (allDefaultMediaBanned() == false) goto L7;
        new AlertDialog.Builder(getContext()).setTitle(LocaleController.getString(R.string.UserRestrictionsCantModify)).setMessage(LocaleController.getString(R.string.UserRestrictionsCantModifyDisabled)).setPositiveButton(LocaleController.getString(R.string.OK), null).create().show();
        return;
    L7:
        if (r3 > 0) goto L9;
        boolean r32 = true;
    L10:
        TLRPC.TL_chatBannedRights r0 = this.bannedRights;
        r0.send_media = !r32;
        r0.send_photos = !r32;
        r0.send_videos = !r32;
        r0.send_stickers = !r32;
        r0.send_gifs = !r32;
        r0.send_inline = !r32;
        r0.send_games = !r32;
        r0.send_audios = !r32;
        r0.send_docs = !r32;
        r0.send_voices = !r32;
        r0.send_roundvideos = !r32;
        r0.embed_links = !r32;
        r0.send_polls = !r32;
        r0.send_reactions = !r32;
        onRestrictionsChanged();
        r4.update(true);
        return;
    L9:
        r32 = false;
        goto L10
    }

    private /* synthetic */ void lambda$fillItems$12(long r2) {
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (r0 == null) goto L5;
        r0.presentFragment(ChatActivity.of(r2));
    L5:
        dismiss();
    }

    private /* synthetic */ void lambda$fillItems$13() {
        AlertsCreator.showBanGroupCreatorFromCommunityJoinedChatsAlert(getContext(), this.resourcesProvider, this.currentAccount, this.banFromCommunityDialogId, this.banFromCommunityChats.joined_chat_ids, new DeleteMessagesBottomSheet$$ExternalSyntheticLambda25(this));
    }

    private /* synthetic */ void lambda$getTitle$4(int[] r2, TLObject r3, int r4) {
        r2[0] = r2[0] + this.participantMessageCounts[r4];
    }

    private /* synthetic */ void lambda$new$0(View r9, int r10, float r11, float r12) {
        UItem r3 = this.adapter.getItem(r10 - 1);
        if (r3 != null) goto L5;
        return;
    L5:
        onClick(r3, r9, r10, r11, r12);
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        proceed();
    }

    private /* synthetic */ void lambda$new$2(TL_communities.ParticipantJoinedChats r1, TLRPC.TL_error r2) {
        if (r1 == null) goto L5;
        this.banFromCommunityChats = r1;
        this.adapter.update(true);
        return;
    }

    private /* synthetic */ boolean lambda$performDelete$15(MessageObject r5) {
        TLRPC.Peer r52 = r5.messageOwner.peer_id;
        if (r52 == null) goto L7;
        if (r52.chat_id == (-this.mergeDialogId)) goto L7;
        return true;
    L7:
        if (this.mergeDialogId == 0) goto L12;
        return false;
    L12:
        return true;
    }

    private /* synthetic */ boolean lambda$performDelete$16(MessageObject r5) {
        TLRPC.Peer r52 = r5.messageOwner.peer_id;
        if (r52 == null) goto L10;
        long r0 = r52.chat_id;
        long r4 = this.mergeDialogId;
        if (r0 == (-r4)) goto L7;
        return false;
    L7:
        if (r4 == 0) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$performDelete$17(ArrayList r8, ArrayList r9, TLObject r10, int r11) {
        long r3 = DialogObject.getDialogId(r10);
        int r102 = r8.size();
        int r112 = 0;
        int r0 = 0;
    L3:
        if (r0 >= r102) goto L5;
        MessagesController.getInstance(this.currentAccount).deleteReactionsFromMessage(-this.inChat.id, r3, ((Integer) r8.get(r0)).intValue());
        r0 = r0 + 1;
        goto L3
    L5:
        int r82 = r9.size();
    L6:
        if (r112 >= r82) goto L8;
        Object r103 = r9.get(r112);
        r112 = r112 + 1;
        MessagesController.getInstance(this.currentAccount).deleteReactionsFromMessage(this.mergeDialogId, r3, ((Integer) r103).intValue());
        goto L6
    }

    private /* synthetic */ void lambda$performDelete$18(TLObject r14, int r15) {
        TLRPC.Chat r0 = this.inChat;
        long r1 = r0.id;
        if (ChatObject.isMonoForum(r0) == true) goto L5;
    L9:
        long r6 = r1;
    L11:
        if (this.restrict == false) goto L21;
        TLRPC.TL_chatBannedRights r10 = bannedRightsOr(this.bannedRights, this.participantsBannedRights.get(r15));
        if ((r14 instanceof TLRPC.User) == false) goto L17;
        MessagesController.getInstance(this.currentAccount).setParticipantBannedRole(r6, (TLRPC.User) r14, null, r10, false, getBaseFragment());
        return;
    L17:
        if ((r14 instanceof TLRPC.Chat) == false) goto L28;
        MessagesController.getInstance(this.currentAccount).setParticipantBannedRole(r6, null, (TLRPC.Chat) r14, r10, false, getBaseFragment());
        return;
    L28:
        return;
    L21:
        if ((r14 instanceof TLRPC.User) == false) goto L25;
        MessagesController.getInstance(this.currentAccount).deleteParticipantFromChat(r6, (TLRPC.User) r14, null, false, false);
        return;
    L25:
        if ((r14 instanceof TLRPC.Chat) == false) goto L29;
        MessagesController.getInstance(this.currentAccount).deleteParticipantFromChat(r6, null, (TLRPC.Chat) r14, false, false);
        return;
    L29:
        return;
    L5:
        if (ChatObject.canManageMonoForum(this.currentAccount, this.inChat) == false) goto L9;
        long r3 = this.inChat.linked_monoforum_id;
        if (r3 == 0) goto L9;
        r6 = r3;
        goto L11
    }

    private /* synthetic */ boolean lambda$performDelete$19(MessageObject r3) {
        TLRPC.Peer r32 = r3.messageOwner.peer_id;
        if (r32 != null) goto L5;
        return false;
    L5:
        if (r32.chat_id == (-this.mergeDialogId)) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$performDelete$21(TLObject r4, int r5) {
        ArrayList<Integer> r52 = (ArrayList) this.messages.stream().filter(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda27(this)).filter(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda28(r4)).map(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda18()).collect(Collectors.toCollection(new ExportMapper$$ExternalSyntheticLambda2()));
        if (this.isReactionOnlyMode == true) goto L5;
    L10:
        TLRPC.TL_channels_reportSpam r0 = new TLRPC.TL_channels_reportSpam();
        r0.channel = MessagesController.getInputChannel(this.inChat);
        if ((r4 instanceof TLRPC.User) == false) goto L14;
        r0.participant = MessagesController.getInputPeer((TLRPC.User) r4);
    L16:
        r0.id = r52;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, null);
        return;
    L14:
        if ((r4 instanceof TLRPC.Chat) == false) goto L16;
        r0.participant = MessagesController.getInputPeer((TLRPC.Chat) r4);
        goto L16
    L5:
        if ((r4 instanceof TLRPC.User) == false) goto L10;
        if (r52.size() != 1) goto L10;
        TLRPC.TL_messages_reportReaction r02 = new TLRPC.TL_messages_reportReaction();
        r02.peer = MessagesController.getInputPeer(this.inChat);
        r02.user_id = MessagesController.getInstance(this.currentAccount).getInputUser((TLRPC.User) r4);
        r02.id = r52.get(0).intValue();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, null);
    }

    private /* synthetic */ void lambda$performDelete$22(TLObject r5, int r6) {
        if (this.restrictUserDeleteAllMessages == false) goto L11;
        if ((r5 instanceof TLRPC.User) == false) goto L8;
        MessagesController.getInstance(this.currentAccount).deleteUserChannelHistory(this.inChat, (TLRPC.User) r5, null, 0);
        goto L11
    L8:
        if ((r5 instanceof TLRPC.Chat) == false) goto L11;
        MessagesController.getInstance(this.currentAccount).deleteUserChannelHistory(this.inChat, null, (TLRPC.Chat) r5, 0);
    L11:
        if (this.restrictUserDeleteAllReactions == true) goto L13;
        return;
    L13:
        if ((r5 instanceof TLRPC.User) == false) goto L17;
        MessagesController.getInstance(this.currentAccount).deleteUserChannelAllReactions(this.inChat, (TLRPC.User) r5, null);
        return;
    L17:
        if ((r5 instanceof TLRPC.Chat) == false) goto L21;
        MessagesController.getInstance(this.currentAccount).deleteUserChannelAllReactions(this.inChat, null, (TLRPC.Chat) r5);
        return;
    }

    private /* synthetic */ void lambda$performDelete$23(TLObject r3, int r4) {
        if ((r3 instanceof TLRPC.User) == false) goto L7;
        MessagesController.getInstance(this.currentAccount).deleteUserChannelHistory(this.inChat, (TLRPC.User) r3, null, 0);
        return;
    L7:
        if ((r3 instanceof TLRPC.Chat) == false) goto L10;
        MessagesController.getInstance(this.currentAccount).deleteUserChannelHistory(this.inChat, null, (TLRPC.Chat) r3, 0);
        return;
    }

    private /* synthetic */ void lambda$performDelete$24(TLObject r2, int r3) {
        if ((r2 instanceof TLRPC.User) == false) goto L7;
        MessagesController.getInstance(this.currentAccount).deleteUserChannelAllReactions(this.inChat, (TLRPC.User) r2, null);
        return;
    L7:
        if ((r2 instanceof TLRPC.Chat) == false) goto L10;
        MessagesController.getInstance(this.currentAccount).deleteUserChannelAllReactions(this.inChat, null, (TLRPC.Chat) r2);
        return;
    }

    private /* synthetic */ void lambda$proceed$25(long r2) {
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (r0 == null) goto L5;
        r0.presentFragment(ChatActivity.of(r2));
    L5:
        dismiss();
    }

    private /* synthetic */ void lambda$proceed$26() {
        proceed(false);
    }

    private /* synthetic */ void lambda$updateParticipantMessageCounts$6(TLObject r3, TLRPC.InputPeer r4, int r5, int[] r6) {
        if ((r3 instanceof TLRPC.TL_messages_channelMessages) == false) goto L5;
        int r32 = ((TLRPC.TL_messages_channelMessages) r3).count;
        int r42 = (int) this.messages.stream().filter(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda26(r4)).count();
        this.participantMessageCounts[r5] = r32 - r42;
    L5:
        int r43 = r6[0] - 1;
        r6[0] = r43;
        if (r43 != 0) goto L9;
        this.participantMessageCountsLoading = false;
        this.participantMessageCountsLoaded = true;
        updateTitleAnimated();
        return;
    }

    private /* synthetic */ void lambda$updateParticipantMessageCounts$7(TLRPC.InputPeer r2, int r3, int[] r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda12(this, r5, r2, r3, r4));
    }

    private void onClick(UItem r7, View r8, int r9, float r10, float r11) {
        int r92 = r7.id;
        if (r92 != 103) goto L6;
        boolean r72 = !this.banFromCommunity;
        this.banFromCommunity = r72;
        ((TextCheckCell2) r8).setChecked(r72);
        return;
    L6:
        int r82 = r7.viewType;
        if (r82 != 37) goto L21;
        int r73 = r92 >>> 24;
        int r83 = 16777215 & r92;
        if (r73 != 0) goto L12;
        this.report.toggleCheck(r83);
        return;
    L12:
        if (r73 != 1) goto L15;
        this.deleteAll.toggleCheck(r83);
        onDeleteAllChanged();
        return;
    L15:
        if (r92 != 3) goto L18;
        this.deleteAllReactions.toggleCheck(r83);
        onDeleteAllChanged();
        return;
    L18:
        if (r73 != 2) goto L137;
        this.banOrRestrict.toggleCheck(r83);
        return;
    L137:
        return;
    L21:
        int r2 = 0;
        if (r82 == 36) goto L60;
        if (r82 == 35) goto L60;
        if (r82 != 39) goto L45;
        if (r7.locked == false) goto L30;
        new AlertDialog.Builder(getContext()).setTitle(LocaleController.getString(R.string.UserRestrictionsCantModify)).setMessage(LocaleController.getString(R.string.UserRestrictionsCantModifyDisabled)).setPositiveButton(LocaleController.getString(R.string.OK), null).create().show();
        return;
    L30:
        if (r92 != 2) goto L32;
        this.bannedRights.invite_users = !r7.invite_users;
        onRestrictionsChanged();
    L42:
        this.adapter.update(true);
        return;
    L32:
        if (r92 != 3) goto L35;
        this.bannedRights.pin_messages = !r7.pin_messages;
        onRestrictionsChanged();
        goto L42
    L35:
        if (r92 != 4) goto L38;
        this.bannedRights.change_info = !r7.change_info;
        onRestrictionsChanged();
        goto L42
    L38:
        if (r92 != 5) goto L40;
        this.bannedRights.manage_topics = !r7.manage_topics;
        onRestrictionsChanged();
        goto L42
    L40:
        if (r92 != 0) goto L42;
        this.bannedRights.send_plain = !r7.send_plain;
        onRestrictionsChanged();
        goto L42
    L45:
        if (r82 != 40) goto L49;
        this.sendMediaCollapsed = !this.sendMediaCollapsed;
        saveScrollPosition();
        this.adapter.update(true);
        applyScrolledPosition(true);
        return;
    L49:
        if (r92 != 100) goto L53;
        this.restrictUserCollapsed = false;
        boolean r74 = !this.restrictUserDeleteAllMessages;
        this.restrictUserDeleteAllMessages = r74;
        this.restrictUserDeleteAllReactions = r74;
        saveScrollPosition();
        this.adapter.update(true);
        applyScrolledPosition(true);
        updateTitleAnimated();
        return;
    L53:
        if (r82 != 38) goto L138;
        boolean r75 = this.restrict;
        this.restrict = !r75;
        Action r84 = this.banOrRestrict;
        if (r75 == true) goto L57;
        boolean[] r76 = this.restrictFilter;
    L58:
        r84.setFilter(r76);
        this.adapter.update(true);
        onRestrictionsChanged();
        return;
    L57:
        r76 = this.banFilter;
        goto L58
    L138:
        return;
    L60:
        if (r92 != 0) goto L63;
        this.report.toggleAllChecks();
        return;
    L63:
        if (r92 != 1) goto L66;
        this.deleteAll.toggleAllChecks();
        onDeleteAllChanged();
        return;
    L66:
        if (r92 != 3) goto L69;
        this.deleteAllReactions.toggleAllChecks();
        onDeleteAllChanged();
        return;
    L69:
        if (r92 != 2) goto L72;
        this.banOrRestrict.toggleAllChecks();
        return;
    L72:
        if (r82 == 35) goto L74;
        return;
    L74:
        if (r7.locked == false) goto L78;
        new AlertDialog.Builder(getContext()).setTitle(LocaleController.getString(R.string.UserRestrictionsCantModify)).setMessage(LocaleController.getString(R.string.UserRestrictionsCantModifyDisabled)).setPositiveButton(LocaleController.getString(R.string.OK), null).create().show();
        return;
    L78:
        if (r92 != 6) goto L81;
        this.bannedRights.send_photos = !r7.send_photos;
        onRestrictionsChanged();
    L130:
        this.adapter.update(true);
        return;
    L81:
        if (r92 != 7) goto L84;
        this.bannedRights.send_videos = !r7.send_videos;
        onRestrictionsChanged();
        goto L130
    L84:
        if (r92 != 9) goto L87;
        this.bannedRights.send_audios = !r7.send_audios;
        onRestrictionsChanged();
        goto L130
    L87:
        if (r92 != 8) goto L90;
        this.bannedRights.send_docs = !r7.send_docs;
        onRestrictionsChanged();
        goto L130
    L90:
        if (r92 != 11) goto L93;
        this.bannedRights.send_roundvideos = !r7.send_roundvideos;
        onRestrictionsChanged();
        goto L130
    L93:
        if (r92 != 10) goto L96;
        this.bannedRights.send_voices = !r7.send_voices;
        onRestrictionsChanged();
        goto L130
    L96:
        if (r92 != 15) goto L99;
        this.bannedRights.send_reactions = !r7.send_reactions;
        onRestrictionsChanged();
        goto L130
    L99:
        if (r92 != 12) goto L102;
        TLRPC.TL_chatBannedRights r77 = this.bannedRights;
        boolean r85 = !r77.send_stickers;
        r77.send_inline = r85;
        r77.send_gifs = r85;
        r77.send_games = r85;
        r77.send_stickers = r85;
        onRestrictionsChanged();
        goto L130
    L102:
        if (r92 != 14) goto L122;
        TLRPC.TL_chatBannedRights r78 = this.bannedRights;
        if (r78.send_plain == true) goto L110;
        if (this.defaultBannedRights.send_plain == true) goto L110;
        r78.embed_links = !r78.embed_links;
        onRestrictionsChanged();
    L110:
        if (r2 >= this.adapter.getItemCount()) goto L119;
        UItem r79 = this.adapter.getItem(r2);
        if (r79.viewType != 39) goto L118;
        if (r79.id != 0) goto L118;
        RecyclerView.ViewHolder r710 = this.recyclerListView.findViewHolderForAdapterPosition(r2 + 1);
        if (r710 == null) goto L119;
        View r711 = r710.itemView;
        float r86 = -this.shiftDp;
        this.shiftDp = r86;
        AndroidUtilities.shakeViewSpring(r711, r86);
    L118:
        r2 = r2 + 1;
    L119:
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        return;
    L122:
        if (r92 != 13) goto L125;
        this.bannedRights.send_polls = !r7.send_polls;
        onRestrictionsChanged();
        goto L130
    L125:
        if (r92 != 101) goto L128;
        this.restrictUserDeleteAllMessages = !this.restrictUserDeleteAllMessages;
        updateTitleAnimated();
        goto L130
    L128:
        if (r92 != 102) goto L130;
        this.restrictUserDeleteAllReactions = !this.restrictUserDeleteAllReactions;
        updateTitleAnimated();
        goto L130
    }

    private void onDeleteAllChanged() {
        if (this.participantMessageCountsLoaded == false) goto L6;
        updateTitleAnimated();
        return;
    L6:
        updateParticipantMessageCounts();
    }

    private void onRestrictionsChanged() {
        boolean r1 = false;
        if (this.restrict == false) goto L12;
        if (this.banOrRestrict.isPresent() == false) goto L12;
        if (this.banOrRestrict.selectedCount <= 0) goto L9;
        boolean r0 = true;
    L10:
        this.banChecked = r0;
        goto L12
    L9:
        r0 = false;
    L12:
        if (this.restrict == false) goto L19;
        if (this.banOrRestrict.isPresent() == false) goto L19;
        Action r02 = this.banOrRestrict;
        if (r02.selectedCount != 0) goto L19;
        r02.toggleAllChecks();
    L29:
        if (this.restrict == false) goto L31;
        return;
    L31:
        if (this.banOrRestrict.isPresent() == true) goto L33;
        return;
    L33:
        if (this.banOrRestrict.selectedCount <= 0) goto L35;
        r1 = true;
    L35:
        this.banChecked = r1;
        return;
    L19:
        if (this.restrict == true) goto L29;
        if (this.banOrRestrict.isPresent() == false) goto L29;
        boolean r03 = this.banChecked;
        Action r3 = this.banOrRestrict;
        if (r3.selectedCount <= 0) goto L25;
        boolean r4 = true;
    L26:
        if (r03 == r4) goto L29;
        r3.toggleAllChecks();
        goto L29
    L25:
        r4 = false;
        goto L26
    }

    private void performDelete() {
        if (this.banFromCommunityDialogId != 0) goto L5;
    L7:
        ArrayList<Integer> r2 = (ArrayList) this.messages.stream().filter(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda17(this)).map(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda18()).collect(Collectors.toCollection(new ExportMapper$$ExternalSyntheticLambda2()));
        ArrayList<Integer> r0 = (ArrayList) this.messages.stream().filter(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda19(this)).map(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda18()).collect(Collectors.toCollection(new ExportMapper$$ExternalSyntheticLambda2()));
        if (this.isReactionOnlyMode == false) goto L13;
        if (this.restrictUserDeleteAllReactions == true) goto L18;
        this.deleteAll.forEach(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda20(this, r2, r0));
    L18:
        this.banOrRestrict.forEachSelected(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda21(this));
        this.report.forEachSelected(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda22(this));
        boolean r02 = this.isSingleUsersMode;
        Action r1 = this.deleteAll;
        if (r02 == false) goto L22;
        r1.forEach(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda14(this));
        return;
    L22:
        r1.forEachSelected(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda15(this));
        this.deleteAllReactions.forEachSelected(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda16(this));
        return;
    L13:
        if (r2.isEmpty() == true) goto L16;
        MessagesController.getInstance(this.currentAccount).deleteMessages(r2, null, null, -this.inChat.id, this.topicId, false, this.mode);
    L16:
        if (r0.isEmpty() == true) goto L18;
        MessagesController.getInstance(this.currentAccount).deleteMessages(r0, null, null, this.mergeDialogId, this.topicId, true, this.mode);
        goto L18
    L5:
        if (this.banFromCommunity == false) goto L7;
        MessagesController.getInstance(this.currentAccount).toggleCommunityParticipantBanned(this.inCommunity.id, this.banFromCommunityDialogId, true, new DeleteMessagesBottomSheet$$ExternalSyntheticLambda13());
        goto L7
    }

    private void proceed() {
        proceed(true);
    }

    private void proceed(boolean r9) {
        if (r9 == true) goto L4;
    L11:
        dismiss();
        Runnable r92 = this.onDelete;
        if (r92 == null) goto L14;
        r92.run();
    L14:
        int r93 = this.report.selectedCount;
        String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        boolean r1 = false;
        if (r93 <= 0) goto L18;
        r0 = _UrlKt.FRAGMENT_ENCODE_SET + LocaleController.formatPluralString("UsersReported", this.report.selectedCount, new Object[0]);
    L18:
        if (this.banOrRestrict.selectedCount <= 0) goto L27;
        if (TextUtils.isEmpty(r0) == true) goto L23;
        r0 = r0.concat("\n");
    L23:
        if (this.restrict == false) goto L25;
        r0 = r0 + LocaleController.formatPluralString("UsersRestricted", this.banOrRestrict.selectedCount, new Object[0]);
        goto L27
    L25:
        r0 = r0 + LocaleController.formatPluralString("UsersBanned", this.banOrRestrict.selectedCount, new Object[0]);
    L27:
        if (this.isReactionOnlyMode == false) goto L32;
        if (this.restrictUserDeleteAllMessages == true) goto L32;
        r1 = true;
    L32:
        if (this.banOrRestrict.selectedCount <= 0) goto L34;
        int r94 = R.raw.ic_admin;
    L36:
        if (TextUtils.isEmpty(r0) == false) goto L42;
        BulletinFactory r02 = BulletinFactory.of(getBaseFragment());
        if (r1 == false) goto L40;
        int r12 = R.string.ReactionsDeleted;
    L41:
        r02.createSimpleBulletin(r94, LocaleController.getString(r12)).show();
    L47:
        performDelete();
        return;
    L40:
        r12 = R.string.MessagesDeleted;
        goto L41
    L42:
        BulletinFactory r2 = BulletinFactory.of(getBaseFragment());
        if (r1 == false) goto L45;
        int r13 = R.string.ReactionsDeleted;
    L46:
        r2.createSimpleBulletin(r94, LocaleController.getString(r13), r0).show();
        goto L47
    L45:
        r13 = R.string.MessagesDeleted;
        goto L46
    L34:
        r94 = R.raw.contact_check;
        goto L36
    L4:
        if (this.banFromCommunity == false) goto L11;
        TL_communities.ParticipantJoinedChats r95 = this.banFromCommunityChats;
        if (r95 == null) goto L11;
        if (r95.creator_chat_ids.isEmpty() == true) goto L11;
        AlertsCreator.showBanGroupCreatorFromCommunityConfirmAlert(getContext(), this.resourcesProvider, this.currentAccount, this.banFromCommunityDialogId, this.banFromCommunityChats.creator_chat_ids, new DeleteMessagesBottomSheet$$ExternalSyntheticLambda10(this), new DeleteMessagesBottomSheet$$ExternalSyntheticLambda11(this));
    }

    private void savePreferences() {
        SharedPreferences.Editor r0 = MessagesController.getInstance(this.currentAccount).getMainSettings().edit();
        r0.putBoolean("delete_report", this.report.areAllSelected());
        r0.putBoolean("delete_deleteAll", this.deleteAll.areAllSelected());
        if (this.restrict == false) goto L5;
    L7:
        boolean r3 = false;
    L8:
        r0.putBoolean("delete_ban", r3);
        r0.apply();
        return;
    L5:
        if (this.banOrRestrict.areAllSelected() == false) goto L7;
        r3 = true;
        goto L8
    }

    private void updateParticipantMessageCounts() {
        if (this.participantMessageCountsLoading == true) goto L9;
        this.participantMessageCountsLoading = true;
        int r1 = this.deleteAll.totalCount;
        this.participantMessageCounts = new int[r1];
        int[] r12 = {r1};
        int r2 = 0;
    L7:
        if (r2 >= this.deleteAll.totalCount) goto L11;
        TLRPC.TL_messages_search r3 = new TLRPC.TL_messages_search();
        r3.peer = MessagesController.getInputPeer(this.inChat);
        r3.q = _UrlKt.FRAGMENT_ENCODE_SET;
        TLRPC.InputPeer r4 = MessagesController.getInputPeer(this.deleteAll.options.get(r2));
        r3.from_id = r4;
        r3.flags |= 1;
        r3.filter = new TLRPC.TL_inputMessagesFilterEmpty();
        r3.limit = 1;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r3, new DeleteMessagesBottomSheet$$ExternalSyntheticLambda6(this, r4, r2, r12));
        r2 = r2 + 1;
        goto L7
    L11:
        return;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public boolean canHighlightChildAt(View r1, float r2, float r3) {
        return !(r1 instanceof CollapseTextCell);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(r9, getContext(), this.currentAccount, getBaseFragment().getClassGuid(), true, new DeleteMessagesBottomSheet$$ExternalSyntheticLambda0(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        savePreferences();
        super.dismiss();
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        if (this.isReactionOnlyMode == true) goto L5;
        ArrayList<MessageObject> r0 = this.messages;
        if (r0 == null) goto L17;
        int r02 = r0.size();
    L18:
        int[] r03 = {r02};
        if (this.participantMessageCounts == null) goto L24;
        if (this.participantMessageCountsLoaded == false) goto L24;
        this.deleteAll.forEachSelected(new DeleteMessagesBottomSheet$$ExternalSyntheticLambda5(this, r03));
    L24:
        return LocaleController.formatPluralString("DeleteOptionsTitle", r03[0], new Object[0]);
    L17:
        r02 = 0;
        goto L18
    L5:
        if (this.restrictUserDeleteAllMessages == false) goto L9;
        return LocaleController.getString(R.string.DeleteMessagesOptionsTitleAll);
    L9:
        if (this.restrictUserDeleteAllReactions == false) goto L13;
        return LocaleController.getString(R.string.DeleteReactionOptionsTitleAll);
    L13:
        return LocaleController.formatPluralString("DeleteReactionOptionsTitle", 1, new Object[0]);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onContainerLayout(int r1, int r2, int r3, int r4) {
        super.onContainerLayout(r1, r2, r3, r4);
        Rect r12 = AndroidUtilities.rectTmp2;
        r12.set(0, 0, this.recyclerListView.getMeasuredWidth(), this.recyclerListView.getMeasuredHeight() - AndroidUtilities.dp(34.0f));
        this.recyclerListView.setClipBounds(r12);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        Bulletin.hideVisible();
    }
}
