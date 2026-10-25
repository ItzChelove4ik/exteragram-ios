package org.telegram.messenger;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.collection.LongSparseArray;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import me.vkryl.core.BitwiseUtils;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.utils.tlutils.TlUtils;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_communities;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.GroupCallActivity;

/* loaded from: classes.dex */
public class ChatObject {
    public static final int ACTION_ADD_ADMINS = 4;
    public static final int ACTION_BLOCK_USERS = 2;
    public static final int ACTION_CHANGE_INFO = 1;
    public static final int ACTION_DELETE_MESSAGES = 13;
    public static final int ACTION_EDIT_MESSAGES = 12;
    public static final int ACTION_EMBED_LINKS = 9;
    public static final int ACTION_INVITE = 3;
    public static final int ACTION_MANAGE_CALLS = 14;
    public static final int ACTION_MANAGE_DIRECT = 24;
    public static final int ACTION_MANAGE_LINKED_CHATS = 27;
    public static final int ACTION_MANAGE_TAGS = 25;
    public static final int ACTION_MANAGE_TOPICS = 15;
    public static final int ACTION_MANAGE_WELCOME = 28;
    public static final int ACTION_PIN = 0;
    public static final int ACTION_POST = 5;
    public static final int ACTION_SEND = 6;
    public static final int ACTION_SEND_DOCUMENTS = 19;
    public static final int ACTION_SEND_GIFS = 23;
    public static final int ACTION_SEND_MEDIA = 7;
    public static final int ACTION_SEND_MUSIC = 18;
    public static final int ACTION_SEND_PHOTO = 16;
    public static final int ACTION_SEND_PLAIN = 22;
    public static final int ACTION_SEND_POLLS = 10;
    public static final int ACTION_SEND_REACTIONS = 26;
    public static final int ACTION_SEND_ROUND = 21;
    public static final int ACTION_SEND_STICKERS = 8;
    public static final int ACTION_SEND_TEXT = 22;
    public static final int ACTION_SEND_VIDEO = 17;
    public static final int ACTION_SEND_VOICE = 20;
    public static final int ACTION_VIEW = 11;
    public static final int CHAT_TYPE_CHANNEL = 2;
    public static final int CHAT_TYPE_CHAT = 0;
    public static final int CHAT_TYPE_FORUM = 5;
    public static final int CHAT_TYPE_MEGAGROUP = 4;
    public static final int CHAT_TYPE_USER = 3;
    private static final int MAX_PARTICIPANTS_COUNT = 5000;
    public static final int VIDEO_FRAME_HAS_FRAME = 2;
    public static final int VIDEO_FRAME_NO_FRAME = 0;
    public static final int VIDEO_FRAME_REQUESTING = 1;

    public static class Call {
        public static final int RECORD_TYPE_AUDIO = 0;
        public static final int RECORD_TYPE_VIDEO_LANDSCAPE = 2;
        public static final int RECORD_TYPE_VIDEO_PORTAIT = 1;
        private static int videoPointer;
        public int activeVideos;
        public TLRPC.GroupCall call;
        public boolean canStreamVideo;
        public long chatId;
        private Runnable checkQueueRunnable;
        public AccountInstance currentAccount;
        public final LongSparseArray<TLRPC.GroupCallParticipant> currentSpeakingPeers;
        public ArrayList<Long> invitedUsers;
        public HashSet<Long> invitedUsersMap;
        public HashMap<Long, InvitedUser> invitedUsersMessageIds;
        public boolean isConference;
        public ArrayList<Long> kickedUsers;
        private long lastGroupCallReloadTime;
        private int lastLoadGuid;
        public boolean loadedRtmpStreamParticipant;
        private boolean loadingGroupCall;
        private HashSet<Integer> loadingGuids;
        public boolean loadingMembers;
        private HashSet<Long> loadingSsrcs;
        private HashSet<Long> loadingUids;
        public boolean membersLoadEndReached;
        private String nextLoadOffset;
        public LongSparseArray<TLRPC.GroupCallParticipant> participants;
        public SparseArray<TLRPC.GroupCallParticipant> participantsByPresentationSources;
        public SparseArray<TLRPC.GroupCallParticipant> participantsBySources;
        public SparseArray<TLRPC.GroupCallParticipant> participantsByVideoSources;
        public long participantsReceivedTime;
        public boolean recording;
        public boolean reloadingMembers;
        public VideoParticipant rtmpStreamParticipant;
        public TLRPC.Peer selfPeer;
        public ArrayList<Long> shadyJoinParticipants;
        public ArrayList<Long> shadyLeftParticipants;
        public final ArrayList<TLRPC.GroupCallParticipant> sortedParticipants;
        public int speakingMembersCount;
        public final HashMap<String, Bitmap> thumbs;
        private Runnable typingUpdateRunnable;
        private boolean typingUpdateRunnableScheduled;
        private final Runnable updateCurrentSpeakingRunnable;
        private ArrayList<TL_update.TL_updateGroupCallParticipants> updatesQueue;
        private long updatesStartWaitTime;
        public VideoParticipant videoNotAvailableParticipant;
        private final HashMap<String, VideoParticipant> videoParticipantsCache;
        public final ArrayList<TLRPC.GroupCallParticipant> visibleParticipants;
        public final ArrayList<VideoParticipant> visibleVideoParticipants;

        /* renamed from: org.telegram.messenger.ChatObject$Call$1, reason: invalid class name */
        /* loaded from: classes5.dex */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ Call this$0;

            public AnonymousClass1(Call r1) {
                this.this$0 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                long r0 = SystemClock.uptimeMillis();
                int r2 = 0;
                boolean r3 = false;
            L3:
                int r4 = this.this$0.currentSpeakingPeers.size();
                Call r5 = this.this$0;
                if (r2 >= r4) goto L22;
                long r42 = r5.currentSpeakingPeers.keyAt(r2);
                if ((r0 - this.this$0.currentSpeakingPeers.get(r42).lastSpeakTime) < 500) goto L20;
                this.this$0.currentSpeakingPeers.remove(r42);
                Call r6 = this.this$0;
                String r8 = null;
                if (r42 <= 0) goto L14;
                TLRPC.User r32 = MessagesController.getInstance(r6.currentAccount.getCurrentAccount()).getUser(Long.valueOf(r42));
                StringBuilder r62 = new StringBuilder("remove from speaking ");
                r62.append(r42);
                r62.append(" ");
                if (r32 == null) goto L13;
                r8 = r32.first_name;
            L13:
                r62.append(r8);
                Log.d("GroupCall", r62.toString());
            L19:
                r2 = r2 - 1;
                r3 = true;
                goto L20
            L14:
                TLRPC.Chat r33 = MessagesController.getInstance(r6.currentAccount.getCurrentAccount()).getChat(Long.valueOf(-r42));
                StringBuilder r63 = new StringBuilder("remove from speaking ");
                r63.append(r42);
                r63.append(" ");
                if (r33 == null) goto L18;
                r8 = r33.title;
            L18:
                r63.append(r8);
                Log.d("GroupCall", r63.toString());
            L20:
                r2 = r2 + 1;
                goto L3
            L22:
                if (r5.currentSpeakingPeers.size() <= 0) goto L24;
                AndroidUtilities.runOnUIThread(Call.m3922$$Nest$fgetupdateCurrentSpeakingRunnable(this.this$0), 550);
            L24:
                if (r3 == false) goto L30;
                this.this$0.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.groupCallSpeakingUsersUpdated, new Object[]{Long.valueOf(this.this$0.chatId), Long.valueOf(this.this$0.call.id), Boolean.FALSE});
                return;
            }
        }

        /* loaded from: classes5.dex */
        public static class InvitedUser {
            public boolean calling;
            public int msg_id;
            public long startTime;

            public InvitedUser() {
            }

            public static InvitedUser make(int r3) {
                InvitedUser r0 = new InvitedUser();
                r0.msg_id = r3;
                r0.calling = true;
                r0.startTime = System.currentTimeMillis();
                return r0;
            }

            public boolean isCalling() {
                if (this.calling == true) goto L5;
                return false;
            L5:
                if (((System.currentTimeMillis() - this.startTime) / 1000) > MessagesController.getInstance(UserConfig.selectedAccount).callRingTimeout) goto L10;
                return true;
            L10:
                return false;
            }
        }

        /* loaded from: classes5.dex */
        public interface OnParticipantsLoad {
            void onLoad(ArrayList<Long> r1);
        }

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes5.dex */
        public @interface RecordType {
        }

        public static /* synthetic */ void $r8$lambda$9KXh5NXH0YnsFU9W8pXc5a2hwRY(Call r0, boolean r1, TL_phone.getGroupParticipants r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$loadMembers$3(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$9VxiqidTIDbBT0lncxUtvJA8C9M(Call r0, TLObject r1) {
            r0.lambda$loadGroupCall$10(r1);
        }

        public static /* synthetic */ void $r8$lambda$N6KE0n8sviUVUP9Tx9AMjlu3QhU(Call r0) {
            r0.checkQueue();
        }

        public static /* synthetic */ void $r8$lambda$QI4Y7T5xApN2fU1VynraduWXWtM(Call r0, int r1, TLObject r2, OnParticipantsLoad r3, ArrayList r4, HashSet r5) {
            r0.lambda$loadUnknownParticipants$5(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$VsIWc5ZgLy2AyU4A6AQSSIfI1g0(Call r0) {
            r0.lambda$createRtmpStreamParticipant$1();
        }

        public static /* synthetic */ int $r8$lambda$bjJKDYvvB7A3J8keyu6lyU0Xp0I(TL_update.TL_updateGroupCallParticipants r0, TL_update.TL_updateGroupCallParticipants r1) {
            return AndroidUtilities.compare(r0.version, r1.version);
        }

        public static /* synthetic */ void $r8$lambda$dz0gstgY4RRtAY9i7KtDcNzEI18(Call r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$setTitle$4(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$eoOUvrZMLleqlBXm_GGIH0srX58(Call r0, boolean r1, TLObject r2, TL_phone.getGroupParticipants r3) {
            r0.lambda$loadMembers$2(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$fXbmk8gYwbR3uaDY1miUEV_RhdI(Call r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$toggleRecord$13(r1, r2);
        }

        /* renamed from: $r8$lambda$gje5r_29mH0I_-9HpgEzL7P16cg, reason: not valid java name */
        public static /* synthetic */ void m3918$r8$lambda$gje5r_29mH0I_9HpgEzL7P16cg(Call r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$reloadGroupCall$9(r1, r2);
        }

        /* renamed from: $r8$lambda$kOrIs0bLEl-3nxAFcdKMTARYcUs, reason: not valid java name */
        public static /* synthetic */ void m3919$r8$lambda$kOrIs0bLEl3nxAFcdKMTARYcUs(Call r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$loadGroupCall$11(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$qhEXJ_bmOaKEtWOWfPkUs_M6PYs(Call r0) {
            r0.lambda$new$0();
        }

        /* renamed from: $r8$lambda$qheNEaMS9z-WZFvjziJ7cnG5YeQ, reason: not valid java name */
        public static /* synthetic */ void m3920$r8$lambda$qheNEaMS9zWZFvjziJ7cnG5YeQ(Call r0, int r1, OnParticipantsLoad r2, ArrayList r3, HashSet r4, TLObject r5, TLRPC.TL_error r6) {
            r0.lambda$loadUnknownParticipants$6(r1, r2, r3, r4, r5, r6);
        }

        public static /* synthetic */ void $r8$lambda$r_F5wKFCFEXkKmvyvm0R2YJ7jEc(Call r0, TLObject r1) {
            r0.lambda$reloadGroupCall$8(r1);
        }

        /* renamed from: $r8$lambda$zWg-zLHuYSE3MrgKycL7ydcx7E4, reason: not valid java name */
        public static /* synthetic */ int m3921$r8$lambda$zWgzLHuYSE3MrgKycL7ydcx7E4(Call r0, long r1, boolean r3, TLRPC.GroupCallParticipant r4, TLRPC.GroupCallParticipant r5) {
            return r0.lambda$sortParticipants$12(r1, r3, r4, r5);
        }

        /* renamed from: -$$Nest$fgetupdateCurrentSpeakingRunnable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m3922$$Nest$fgetupdateCurrentSpeakingRunnable(Call r0) {
            return r0.updateCurrentSpeakingRunnable;
        }

        public Call() {
            this.participants = new LongSparseArray();
            this.sortedParticipants = new ArrayList();
            this.visibleVideoParticipants = new ArrayList();
            this.visibleParticipants = new ArrayList();
            this.thumbs = new HashMap();
            this.videoParticipantsCache = new HashMap();
            this.invitedUsers = new ArrayList();
            this.invitedUsersMessageIds = new HashMap();
            this.shadyLeftParticipants = new ArrayList();
            this.shadyJoinParticipants = new ArrayList();
            this.invitedUsersMap = new HashSet();
            this.kickedUsers = new ArrayList();
            this.participantsBySources = new SparseArray();
            this.participantsByVideoSources = new SparseArray();
            this.participantsByPresentationSources = new SparseArray();
            this.typingUpdateRunnable = new ChatObject$Call$$ExternalSyntheticLambda9(this);
            this.loadingGuids = new HashSet();
            this.updatesQueue = new ArrayList();
            this.loadingUids = new HashSet();
            this.loadingSsrcs = new HashSet();
            this.currentSpeakingPeers = new LongSparseArray();
            this.updateCurrentSpeakingRunnable = new AnonymousClass1(this);
        }

        private void checkOnlineParticipants() {
            int r1 = 0;
            if (this.typingUpdateRunnableScheduled == false) goto L5;
            AndroidUtilities.cancelRunOnUIThread(this.typingUpdateRunnable);
            this.typingUpdateRunnableScheduled = false;
        L5:
            this.speakingMembersCount = 0;
            int r0 = this.currentAccount.getConnectionsManager().getCurrentTime();
            int r2 = this.sortedParticipants.size();
            int r4 = Integer.MAX_VALUE;
        L7:
            if (r1 >= r2) goto L15;
            TLRPC.GroupCallParticipant r6 = this.sortedParticipants.get(r1);
            int r7 = r0 - r6.active_date;
            if (r7 >= 5) goto L12;
            this.speakingMembersCount++;
            r4 = Math.min(r7, r4);
        L12:
            if (Math.max(r6.date, r6.active_date) <= (r0 - 5)) goto L15;
            r1 = r1 + 1;
        L15:
            if (r4 == Integer.MAX_VALUE) goto L20;
            AndroidUtilities.runOnUIThread(this.typingUpdateRunnable, r4 * 1000);
            this.typingUpdateRunnableScheduled = true;
            return;
        }

        private void checkQueue() {
            this.checkQueueRunnable = null;
            if (this.updatesStartWaitTime == 0) goto L11;
            if ((System.currentTimeMillis() - this.updatesStartWaitTime) < 1500) goto L11;
            if (BuildVars.LOGS_ENABLED == false) goto L9;
            FileLog.d("QUEUE GROUP CALL UPDATES WAIT TIMEOUT - CHECK QUEUE");
        L9:
            processUpdatesQueue();
        L11:
            if (this.updatesQueue.isEmpty() == true) goto L14;
            ChatObject$Call$$ExternalSyntheticLambda0 r0 = new ChatObject$Call$$ExternalSyntheticLambda0(this);
            this.checkQueueRunnable = r0;
            AndroidUtilities.runOnUIThread(r0, 1000);
            return;
        }

        private long getSelfId() {
            TLRPC.Peer r0 = this.selfPeer;
            if (r0 == null) goto L7;
            return MessageObject.getPeerId(r0);
        L7:
            return this.currentAccount.getUserConfig().getClientUserId();
        }

        private boolean isSameVideo(TLRPC.TL_groupCallParticipantVideo r10, TLRPC.TL_groupCallParticipantVideo r11) {
            if (r10 != null) goto L5;
            if (r11 == null) goto L5;
        L7:
            return false;
        L5:
            if (r10 == null) goto L9;
            if (r11 == null) goto L7;
        L9:
            if (r10 == null) goto L33;
            if (r11 == null) goto L33;
            if (TextUtils.equals(r10.endpoint, r11.endpoint) == true) goto L16;
            return false;
        L16:
            if (r10.source_groups.size() == r11.source_groups.size()) goto L18;
            return false;
        L18:
            int r1 = r10.source_groups.size();
            int r2 = 0;
        L19:
            if (r2 >= r1) goto L33;
            TLRPC.TL_groupCallParticipantVideoSourceGroup r3 = r10.source_groups.get(r2);
            TLRPC.TL_groupCallParticipantVideoSourceGroup r4 = r11.source_groups.get(r2);
            if (TextUtils.equals(r3.semantics, r4.semantics) == false) goto L22;
            if (r3.sources.size() != r4.sources.size()) goto L25;
            int r5 = r3.sources.size();
            int r6 = 0;
        L27:
            if (r6 >= r5) goto L32;
            if (r4.sources.contains(r3.sources.get(r6)) == false) goto L30;
            r6 = r6 + 1;
            goto L27
        L30:
            return false;
        L32:
            r2 = r2 + 1;
            goto L19
        L25:
            return false;
        L22:
            return false;
        L33:
            return true;
        }

        private int isValidUpdate(TL_update.TL_updateGroupCallParticipants r2) {
            int r1 = this.call.version;
            int r0 = r1 + 1;
            int r22 = r2.version;
            if (r0 == r22) goto L11;
            if (r1 == r22) goto L13;
            if (r1 >= r22) goto L9;
            return 1;
        L9:
            return 2;
        L13:
            return 0;
        L11:
            return 0;
        }

        private /* synthetic */ void lambda$createRtmpStreamParticipant$1() {
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{Long.valueOf(this.chatId), Long.valueOf(this.call.id), Boolean.FALSE});
        }

        private /* synthetic */ void lambda$loadGroupCall$10(TLObject r5) {
            this.lastGroupCallReloadTime = SystemClock.elapsedRealtime();
            this.loadingGroupCall = false;
            if (r5 == null) goto L11;
            TL_phone.groupParticipants r52 = (TL_phone.groupParticipants) r5;
            this.currentAccount.getMessagesController().putUsers(r52.users, false);
            this.currentAccount.getMessagesController().putChats(r52.chats, false);
            TLRPC.GroupCall r0 = this.call;
            int r1 = r0.participants_count;
            int r53 = r52.count;
            if (r1 == r53) goto L12;
            r0.participants_count = r53;
            if (BuildVars.LOGS_ENABLED == false) goto L9;
            FileLog.d("new participants reload count " + this.call.participants_count);
        L9:
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{Long.valueOf(this.chatId), Long.valueOf(this.call.id), Boolean.FALSE});
            return;
        L12:
            return;
        }

        private /* synthetic */ void lambda$loadGroupCall$11(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new ChatObject$Call$$ExternalSyntheticLambda8(this, r1));
        }

        private /* synthetic */ void lambda$loadMembers$2(boolean r11, TLObject r12, TL_phone.getGroupParticipants r13) {
            this.loadingMembers = false;
            if (r11 == false) goto L5;
            this.reloadingMembers = false;
        L5:
            if (r12 == null) goto L8;
            TL_phone.groupParticipants r122 = (TL_phone.groupParticipants) r12;
            this.currentAccount.getMessagesController().putUsers(r122.users, false);
            this.currentAccount.getMessagesController().putChats(r122.chats, false);
            onParticipantsLoad(r122.participants, r11, r13.offset, r122.next_offset, r122.version, r122.count);
            return;
        }

        private /* synthetic */ void lambda$loadMembers$3(boolean r1, TL_phone.getGroupParticipants r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new ChatObject$Call$$ExternalSyntheticLambda6(this, r1, r3, r2));
        }

        private /* synthetic */ void lambda$loadUnknownParticipants$5(int r8, TLObject r9, OnParticipantsLoad r10, ArrayList r11, HashSet r12) {
            if (this.loadingGuids.remove(Integer.valueOf(r8)) == true) goto L5;
            return;
        L5:
            if (r9 == null) goto L22;
            TL_phone.groupParticipants r92 = (TL_phone.groupParticipants) r9;
            this.currentAccount.getMessagesController().putUsers(r92.users, false);
            this.currentAccount.getMessagesController().putChats(r92.chats, false);
            int r82 = r92.participants.size();
            int r0 = 0;
        L7:
            if (r0 >= r82) goto L16;
            TLRPC.GroupCallParticipant r2 = r92.participants.get(r0);
            long r3 = MessageObject.getPeerId(r2.peer);
            TLRPC.GroupCallParticipant r5 = this.participants.get(r3);
            if (r5 == null) goto L11;
            this.sortedParticipants.remove(r5);
            processAllSources(r5, false);
        L11:
            this.participants.put(r3, r2);
            this.sortedParticipants.add(r2);
            processAllSources(r2, true);
            if (this.invitedUsersMap.contains(Long.valueOf(r3)) == false) goto L14;
            Long r22 = Long.valueOf(r3);
            this.invitedUsersMap.remove(r22);
            this.invitedUsers.remove(r22);
        L14:
            r0 = r0 + 1;
            goto L7
        L16:
            if (this.call.participants_count >= this.participants.size()) goto L18;
            this.call.participants_count = this.participants.size();
        L18:
            sortParticipants();
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{Long.valueOf(this.chatId), Long.valueOf(this.call.id), Boolean.FALSE});
            if (r10 == null) goto L21;
            r10.onLoad(r11);
            goto L22
        L21:
            setParticiapantsVolume();
        L22:
            r12.removeAll(r11);
        }

        private /* synthetic */ void lambda$loadUnknownParticipants$6(int r2, OnParticipantsLoad r3, ArrayList r4, HashSet r5, TLObject r6, TLRPC.TL_error r7) {
            AndroidUtilities.runOnUIThread(new ChatObject$Call$$ExternalSyntheticLambda10(this, r2, r6, r3, r4, r5));
        }

        private /* synthetic */ void lambda$new$0() {
            this.typingUpdateRunnableScheduled = false;
            checkOnlineParticipants();
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.groupCallTypingsUpdated, new Object[0]);
        }

        private /* synthetic */ void lambda$reloadGroupCall$8(TLObject r11) {
            if ((r11 instanceof TL_phone.groupCall) == false) goto L6;
            TL_phone.groupCall r112 = (TL_phone.groupCall) r11;
            this.call = r112.call;
            this.currentAccount.getMessagesController().putUsers(r112.users, false);
            this.currentAccount.getMessagesController().putChats(r112.chats, false);
            ArrayList<TLRPC.GroupCallParticipant> r4 = r112.participants;
            String r7 = r112.participants_next_offset;
            TLRPC.GroupCall r113 = r112.call;
            onParticipantsLoad(r4, true, _UrlKt.FRAGMENT_ENCODE_SET, r7, r113.version, r113.participants_count);
            return;
        }

        private /* synthetic */ void lambda$reloadGroupCall$9(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new ChatObject$Call$$ExternalSyntheticLambda4(this, r1));
        }

        private /* synthetic */ void lambda$setTitle$4(TLObject r1, TLRPC.TL_error r2) {
            if (r1 == null) goto L5;
            MessagesController r0 = this.currentAccount.getMessagesController();
            r0.processUpdates((TLRPC.Updates) r1, false);
            return;
        }

        private /* synthetic */ int lambda$sortParticipants$12(long r8, boolean r10, TLRPC.GroupCallParticipant r11, TLRPC.GroupCallParticipant r12) {
            int r0 = r11.videoIndex;
            boolean r1 = false;
            if (r0 <= 0) goto L5;
            boolean r3 = true;
        L6:
            int r4 = r12.videoIndex;
            if (r4 <= 0) goto L9;
            r1 = true;
        L9:
            if (r3 == false) goto L14;
            if (r1 == false) goto L14;
            return r4 - r0;
        L14:
            if (r3 == false) goto L16;
            return -1;
        L16:
            if (r1 == false) goto L18;
            return 1;
        L18:
            int r13 = r11.active_date;
            if (r13 == 0) goto L24;
            int r32 = r12.active_date;
            if (r32 == 0) goto L24;
            return Integer.compare(r32, r13);
        L24:
            if (r13 == 0) goto L27;
            return -1;
        L27:
            if (r12.active_date == 0) goto L30;
            return 1;
        L30:
            if (MessageObject.getPeerId(r11.peer) != r8) goto L33;
            return -1;
        L33:
            if (MessageObject.getPeerId(r12.peer) != r8) goto L35;
            return 1;
        L35:
            if (r10 == false) goto L49;
            long r82 = r11.raise_hand_rating;
            if (r82 == 0) goto L43;
            long r5 = r12.raise_hand_rating;
            if (r5 == 0) goto L43;
            return Long.compare(r5, r82);
        L43:
            if (r82 == 0) goto L46;
            return -1;
        L46:
            if (r12.raise_hand_rating == 0) goto L49;
            return 1;
        L49:
            if (this.call.join_date_asc == false) goto L53;
            return Integer.compare(r11.date, r12.date);
        L53:
            return Integer.compare(r12.date, r11.date);
        L5:
            r3 = false;
            goto L6
        }

        private /* synthetic */ void lambda$toggleRecord$13(TLObject r1, TLRPC.TL_error r2) {
            if (r1 == null) goto L5;
            MessagesController r0 = this.currentAccount.getMessagesController();
            r0.processUpdates((TLRPC.Updates) r1, false);
            return;
        }

        private void loadGroupCall() {
            if (this.loadingGroupCall == false) goto L5;
            return;
        L5:
            if ((SystemClock.elapsedRealtime() - this.lastGroupCallReloadTime) < NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L10;
            this.loadingGroupCall = true;
            TL_phone.getGroupParticipants r1 = new TL_phone.getGroupParticipants();
            r1.call = getInputGroupCall();
            r1.offset = _UrlKt.FRAGMENT_ENCODE_SET;
            r1.limit = 1;
            this.currentAccount.getConnectionsManager().sendRequest(r1, new ChatObject$Call$$ExternalSyntheticLambda13(this));
            return;
        }

        private void loadUnknownParticipants(ArrayList<Long> r10, boolean r11, OnParticipantsLoad r12) {
            if (r11 == false) goto L5;
            HashSet<Long> r0 = this.loadingUids;
        L4:
            HashSet<Long> r6 = r0;
            int r02 = r10.size();
            int r1 = 0;
            int r2 = 0;
        L7:
            if (r2 >= r02) goto L13;
            if (r6.contains(r10.get(r2)) == false) goto L11;
            r10.remove(r2);
            r2 = r2 - 1;
            r02 = r02 - 1;
        L11:
            r2 = r2 + 1;
            goto L7
        L13:
            if (r10.isEmpty() == false) goto L15;
            return;
        L15:
            int r3 = this.lastLoadGuid + 1;
            this.lastLoadGuid = r3;
            this.loadingGuids.add(Integer.valueOf(r3));
            r6.addAll(r10);
            TL_phone.getGroupParticipants r03 = new TL_phone.getGroupParticipants();
            r03.call = getInputGroupCall();
            int r22 = r10.size();
        L16:
            if (r1 >= r22) goto L22;
            long r4 = r10.get(r1).longValue();
            if (r11 == false) goto L20;
            r03.ids.add(this.currentAccount.getMessagesController().getInputPeer(r4));
        L21:
            r1 = r1 + 1;
            goto L16
        L20:
            r03.sources.add(Integer.valueOf((int) r4));
            goto L21
        L22:
            r03.offset = _UrlKt.FRAGMENT_ENCODE_SET;
            r03.limit = 100;
            this.currentAccount.getConnectionsManager().sendRequest(r03, new ChatObject$Call$$ExternalSyntheticLambda12(this, r3, r12, r10, r6));
            return;
        L5:
            r0 = this.loadingSsrcs;
            goto L4
        }

        private void onParticipantsLoad(ArrayList<TLRPC.GroupCallParticipant> r10, boolean r11, String r12, String r13, int r14, int r15) {
            this.participantsReceivedTime = System.currentTimeMillis();
            TLRPC.GroupCallParticipant r0 = this.participants.get(getSelfId());
            LongSparseArray<TLRPC.GroupCallParticipant> r2 = null;
            if (TextUtils.isEmpty(r12) == false) goto L9;
            int r1 = this.participants.size();
            LongSparseArray<TLRPC.GroupCallParticipant> r3 = this.participants;
            if (r1 == 0) goto L7;
            this.participants = new LongSparseArray();
            r2 = r3;
        L8:
            this.sortedParticipants.clear();
            this.participantsBySources.clear();
            this.participantsByVideoSources.clear();
            this.participantsByPresentationSources.clear();
            this.loadingGuids.clear();
            goto L9
        L7:
            r3.clear();
        L9:
            this.nextLoadOffset = r13;
            if (r10.isEmpty() == false) goto L12;
        L13:
            this.membersLoadEndReached = true;
        L15:
            if (TextUtils.isEmpty(r12) == false) goto L19;
            TLRPC.GroupCall r122 = this.call;
            r122.version = r14;
            r122.participants_count = r15;
            if (BuildVars.LOGS_ENABLED == false) goto L19;
            FileLog.d("new participants count " + this.call.participants_count);
        L19:
            long r123 = SystemClock.elapsedRealtime();
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.applyGroupCallVisibleParticipants, new Object[]{Long.valueOf(r123)});
            int r142 = r10.size();
            int r32 = 0;
            boolean r4 = false;
        L20:
            if (r32 > r142) goto L52;
            if (r32 != r142) goto L26;
            if (r11 == false) goto L50;
            if (r0 == null) goto L50;
            if (r4 == true) goto L50;
            TLRPC.GroupCallParticipant r5 = r0;
        L29:
            TLRPC.GroupCallParticipant r6 = this.participants.get(MessageObject.getPeerId(r5.peer));
            if (r6 == null) goto L38;
            this.sortedParticipants.remove(r6);
            processAllSources(r6, false);
            if (r6.self == false) goto L34;
            r5.lastTypingDate = r6.active_date;
        L36:
            if (r123 == r5.lastVisibleDate) goto L49;
            r5.active_date = r5.lastTypingDate;
        L49:
            this.participants.put(MessageObject.getPeerId(r5.peer), r5);
            this.sortedParticipants.add(r5);
            processAllSources(r5, true);
            goto L50
        L34:
            r5.lastTypingDate = Math.max(r5.active_date, r6.active_date);
            goto L36
        L38:
            if (r2 == null) goto L49;
            TLRPC.GroupCallParticipant r62 = r2.get(MessageObject.getPeerId(r5.peer));
            if (r62 == null) goto L49;
            if (r62.self == false) goto L44;
            r5.lastTypingDate = r62.active_date;
        L46:
            if (r123 == r5.lastVisibleDate) goto L48;
            r5.active_date = r5.lastTypingDate;
            goto L49
        L48:
            r5.active_date = r62.active_date;
            goto L49
        L44:
            r5.lastTypingDate = Math.max(r5.active_date, r62.active_date);
        L50:
            r32 = r32 + 1;
            goto L20
        L26:
            r5 = r10.get(r32);
            if (r5.self == false) goto L29;
            r4 = true;
            goto L29
        L52:
            if (this.call.participants_count >= this.participants.size()) goto L54;
            this.call.participants_count = this.participants.size();
        L54:
            sortParticipants();
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{Long.valueOf(this.chatId), Long.valueOf(this.call.id), Boolean.FALSE});
            setParticiapantsVolume();
            return;
        L12:
            if (TextUtils.isEmpty(this.nextLoadOffset) == false) goto L15;
            goto L13
        }

        private void processAllSources(TLRPC.GroupCallParticipant r11, boolean r12) {
            int r0 = r11.source;
            if (r0 == 0) goto L8;
            SparseArray<TLRPC.GroupCallParticipant> r1 = this.participantsBySources;
            if (r12 == false) goto L7;
            r1.put(r0, r11);
            goto L8
        L7:
            r1.remove(r0);
        L8:
            int r13 = 0;
        L10:
            if (r13 >= 2) goto L46;
            if (r13 != 0) goto L13;
            TLRPC.TL_groupCallParticipantVideo r3 = r11.video;
        L14:
            if (r3 == null) goto L45;
            if ((2 & r3.flags) == 0) goto L23;
            int r2 = r3.audio_source;
            if (r2 == 0) goto L23;
            SparseArray<TLRPC.GroupCallParticipant> r4 = this.participantsBySources;
            if (r12 == false) goto L22;
            r4.put(r2, r11);
            goto L23
        L22:
            r4.remove(r2);
        L23:
            if (r13 != 0) goto L25;
            SparseArray<TLRPC.GroupCallParticipant> r22 = this.participantsByVideoSources;
        L26:
            int r42 = r3.source_groups.size();
            int r5 = 0;
        L27:
            if (r5 >= r42) goto L36;
            TLRPC.TL_groupCallParticipantVideoSourceGroup r6 = r3.source_groups.get(r5);
            int r7 = r6.sources.size();
            int r8 = 0;
        L29:
            if (r8 >= r7) goto L35;
            int r9 = r6.sources.get(r8).intValue();
            if (r12 == false) goto L33;
            r22.put(r9, r11);
        L34:
            r8 = r8 + 1;
            goto L29
        L33:
            r22.remove(r9);
            goto L34
        L35:
            r5 = r5 + 1;
            goto L27
        L36:
            if (r12 == false) goto L42;
            String r23 = r3.endpoint;
            if (r13 != 0) goto L40;
            r11.videoEndpoint = r23;
            goto L45
        L40:
            r11.presentationEndpoint = r23;
            goto L45
        L42:
            if (r13 != 0) goto L44;
            r11.videoEndpoint = null;
            goto L45
        L44:
            r11.presentationEndpoint = null;
            goto L45
        L25:
            r22 = this.participantsByPresentationSources;
        L45:
            r13 = r13 + 1;
            goto L10
        L13:
            r3 = r11.presentation;
            goto L14
        }

        private void processUpdatesQueue() {
            Collections.sort(this.updatesQueue, new ChatObject$Call$$ExternalSyntheticLambda14());
            ArrayList<TL_update.TL_updateGroupCallParticipants> r0 = this.updatesQueue;
            if (r0 != null) goto L5;
        L33:
            this.updatesStartWaitTime = 0;
            return;
        L5:
            if (r0.isEmpty() == true) goto L33;
            boolean r3 = false;
        L7:
            int r4 = this.updatesQueue.size();
            ArrayList<TL_update.TL_updateGroupCallParticipants> r5 = this.updatesQueue;
            if (r4 <= 0) goto L30;
            TL_update.TL_updateGroupCallParticipants r42 = r5.get(0);
            int r52 = isValidUpdate(r42);
            if (r52 == 0) goto L11;
            if (r52 == 1) goto L14;
            this.updatesQueue.remove(0);
            goto L7
        L14:
            if (this.updatesStartWaitTime == 0) goto L25;
            if (r3 == true) goto L19;
            if (Math.abs(System.currentTimeMillis() - this.updatesStartWaitTime) > 1500) goto L25;
        L19:
            if (BuildVars.LOGS_ENABLED == false) goto L21;
            FileLog.d("HOLE IN GROUP CALL UPDATES QUEUE - will wait more time");
        L21:
            if (r3 == false) goto L42;
            this.updatesStartWaitTime = System.currentTimeMillis();
            return;
        L42:
            return;
        L25:
            if (BuildVars.LOGS_ENABLED == false) goto L27;
            FileLog.d("HOLE IN GROUP CALL UPDATES QUEUE - reload participants");
        L27:
            this.updatesStartWaitTime = 0;
            this.updatesQueue.clear();
            this.nextLoadOffset = null;
            loadMembers(true);
            return;
        L11:
            processParticipantsUpdate(r42, true);
            this.updatesQueue.remove(0);
            r3 = true;
            goto L7
        L30:
            r5.clear();
            if (BuildVars.LOGS_ENABLED == false) goto L33;
            FileLog.d("GROUP CALL UPDATES QUEUE PROCEED - OK");
            goto L33
        }

        private void setParticiapantsVolume() {
            VoIPService r0 = VoIPService.getSharedInstance();
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.getAccount() == this.currentAccount.getCurrentAccount()) goto L7;
            return;
        L7:
            if (r0.getChat() != null) goto L9;
            return;
        L9:
            if (r0.getChat().id != (-this.chatId)) goto L15;
            r0.setParticipantsVolume();
            return;
        }

        public static boolean videoIsActive(TLRPC.GroupCallParticipant r4, boolean r5, Call r6) {
            if (r4 != null) goto L5;
            return false;
        L5:
            VoIPService r1 = VoIPService.getSharedInstance();
            if (r1 != null) goto L9;
            return false;
        L9:
            if (r4.self == true) goto L11;
            VideoParticipant r12 = r6.rtmpStreamParticipant;
            if (r12 != null) goto L17;
        L18:
            VideoParticipant r13 = r6.videoNotAvailableParticipant;
            if (r13 == null) goto L23;
            if (r13.participant != r4) goto L23;
        L24:
            if (r5 == false) goto L30;
            if (r4.presentation == null) goto L28;
            return true;
        L28:
            return false;
        L30:
            if (r4.video == null) goto L32;
            return true;
        L32:
            return false;
        L23:
            if (r6.participants.get(MessageObject.getPeerId(r4.peer)) == null) goto L32;
        L17:
            if (r12.participant == r4) goto L24;
        L11:
            if (r1.getVideoState(r5) != 2) goto L13;
            return true;
        L13:
            return false;
        }

        public void addInvitedUser(long r4) {
            if (this.participants.get(r4) == null) goto L5;
            return;
        L5:
            if (this.invitedUsersMap.contains(Long.valueOf(r4)) == true) goto L10;
            this.invitedUsersMap.add(Long.valueOf(r4));
            this.invitedUsers.add(Long.valueOf(r4));
            this.kickedUsers.remove(Long.valueOf(r4));
            sortParticipants();
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{Long.valueOf(this.chatId), Long.valueOf(this.call.id), Boolean.FALSE});
            return;
        }

        public void addKickedUser(long r4) {
            if (this.kickedUsers.contains(Long.valueOf(r4)) == false) goto L5;
            return;
        L5:
            this.kickedUsers.add(Long.valueOf(r4));
            sortParticipants();
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{Long.valueOf(this.chatId), Long.valueOf(this.call.id), Boolean.FALSE});
        }

        public void addSelfDummyParticipant(boolean r8) {
            long r0 = getSelfId();
            if (this.participants.indexOfKey(r0) >= 0) goto L33;
            TLRPC.TL_groupCallParticipant r2 = new TLRPC.TL_groupCallParticipant();
            r2.peer = this.selfPeer;
            boolean r3 = true;
            r2.muted = true;
            r2.self = true;
            r2.video_joined = this.call.can_start_video;
            TLRPC.Chat r4 = this.currentAccount.getMessagesController().getChat(Long.valueOf(this.chatId));
            if (this.call.join_muted == true) goto L8;
        L11:
            r2.can_self_unmute = r3;
            r2.date = this.currentAccount.getConnectionsManager().getCurrentTime();
            if (ChatObject.canManageCalls(r4) == false) goto L14;
        L19:
            r2.active_date = this.currentAccount.getConnectionsManager().getCurrentTime();
        L20:
            AccountInstance r42 = this.currentAccount;
            if (r0 <= 0) goto L25;
            TLRPC.UserFull r32 = MessagesController.getInstance(r42.getCurrentAccount()).getUserFull(r0);
            if (r32 == null) goto L28;
            r2.about = r32.about;
        L28:
            this.participants.put(r0, r2);
            this.sortedParticipants.add(r2);
            sortParticipants();
            if (r8 == false) goto L32;
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{Long.valueOf(this.chatId), Long.valueOf(this.call.id), Boolean.FALSE});
            return;
        L32:
            return;
        L25:
            TLRPC.ChatFull r33 = MessagesController.getInstance(r42.getCurrentAccount()).getChatFull(-r0);
            if (r33 == null) goto L28;
            r2.about = r33.about;
            goto L28
        L14:
            if (ChatObject.isChannel(r4) == false) goto L19;
            if (r4.megagroup == true) goto L19;
            if (r2.can_self_unmute == false) goto L20;
        L8:
            if (ChatObject.canManageCalls(r4) == true) goto L11;
            r3 = false;
            goto L11
        }

        public boolean canRecordVideo() {
            if (this.canStreamVideo == true) goto L5;
            return false;
        L5:
            VoIPService r0 = VoIPService.getSharedInstance();
            if (r0 == null) goto L15;
            if (r0.groupCall != this) goto L15;
            if (r0.getVideoState(false) != 2) goto L12;
        L13:
            return true;
        L12:
            if (r0.getVideoState(true) == 2) goto L13;
        L15:
            if (this.activeVideos >= this.call.unmuted_video_limit) goto L17;
            return true;
        L17:
            return false;
        }

        public void clearVideFramesInfo() {
            int r1 = 0;
        L4:
            if (r1 >= this.sortedParticipants.size()) goto L6;
            this.sortedParticipants.get(r1).hasCameraFrame = 0;
            this.sortedParticipants.get(r1).hasPresentationFrame = 0;
            this.sortedParticipants.get(r1).videoIndex = 0;
            r1 = r1 + 1;
            goto L4
        L6:
            sortParticipants();
        }

        public void createNoVideoParticipant() {
            if (this.videoNotAvailableParticipant == null) goto L5;
            return;
        L5:
            TLRPC.TL_groupCallParticipant r0 = new TLRPC.TL_groupCallParticipant();
            TLRPC.TL_peerChannel r1 = new TLRPC.TL_peerChannel();
            r0.peer = r1;
            r1.channel_id = this.chatId;
            r0.muted = true;
            TLRPC.TL_groupCallParticipantVideo r2 = new TLRPC.TL_groupCallParticipantVideo();
            r0.video = r2;
            r2.paused = true;
            r2.endpoint = _UrlKt.FRAGMENT_ENCODE_SET;
            this.videoNotAvailableParticipant = new VideoParticipant(r0, false, false);
        }

        public void createRtmpStreamParticipant(List<TL_phone.TL_groupCallStreamChannel> r5) {
            if (this.loadedRtmpStreamParticipant == true) goto L5;
        L7:
            VideoParticipant r0 = this.rtmpStreamParticipant;
            if (r0 == null) goto L10;
            TLRPC.GroupCallParticipant r02 = r0.participant;
        L11:
            TLRPC.TL_peerChat r1 = new TLRPC.TL_peerChat();
            r02.peer = r1;
            r1.channel_id = this.chatId;
            r02.video = new TLRPC.TL_groupCallParticipantVideo();
            TLRPC.TL_groupCallParticipantVideoSourceGroup r12 = new TLRPC.TL_groupCallParticipantVideoSourceGroup();
            r12.semantics = "SIM";
            Iterator<TL_phone.TL_groupCallStreamChannel> r52 = r5.iterator();
        L13:
            if (r52.hasNext() == false) goto L15;
            TL_phone.TL_groupCallStreamChannel r2 = r52.next();
            r12.sources.add(Integer.valueOf(r2.channel));
            goto L13
        L15:
            r02.video.source_groups.add(r12);
            r02.video.endpoint = "unified";
            r02.videoEndpoint = "unified";
            this.rtmpStreamParticipant = new VideoParticipant(r02, false, false);
            sortParticipants();
            AndroidUtilities.runOnUIThread(new ChatObject$Call$$ExternalSyntheticLambda1(this));
            return;
        L10:
            r02 = new TLRPC.TL_groupCallParticipant();
            goto L11
        L5:
            if (this.rtmpStreamParticipant == null) goto L7;
        }

        public TLRPC.InputGroupCall getInputGroupCall() {
            return getInputGroupCall(this.isConference);
        }

        public TLRPC.InputGroupCall getInputGroupCall(boolean r3) {
            TLRPC.GroupCall r0 = this.call;
            if (r0 != null) goto L5;
            return null;
        L5:
            if (r3 == true) goto L7;
            TLRPC.TL_inputGroupCall r32 = new TLRPC.TL_inputGroupCall();
            TLRPC.GroupCall r2 = this.call;
            r32.id = r2.id;
            r32.access_hash = r2.access_hash;
            return r32;
        L7:
            if (r0.invite_link != null) goto L9;
            return null;
        L9:
            TLRPC.TL_inputGroupCallSlug r33 = new TLRPC.TL_inputGroupCallSlug();
            r33.slug = Uri.parse(this.call.invite_link).getPathSegments().get(r2.size() - 1);
            return r33;
        }

        public boolean isScheduled() {
            if ((this.call.flags & 128) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        public void loadMembers(boolean r4) {
            if (r4 == false) goto L8;
            if (this.reloadingMembers == true) goto L26;
            this.membersLoadEndReached = false;
            this.nextLoadOffset = null;
            goto L8
        L26:
            return;
        L8:
            if (this.membersLoadEndReached == false) goto L10;
            return;
        L10:
            if (this.sortedParticipants.size() <= ChatObject.MAX_PARTICIPANTS_COUNT) goto L13;
            return;
        L13:
            if (r4 == false) goto L15;
            this.reloadingMembers = true;
        L15:
            this.loadingMembers = true;
            TL_phone.getGroupParticipants r0 = new TL_phone.getGroupParticipants();
            r0.call = getInputGroupCall();
            String r1 = this.nextLoadOffset;
            if (r1 != null) goto L19;
            r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        L19:
            r0.offset = r1;
            if (this.isConference == false) goto L22;
            int r12 = this.currentAccount.getMessagesController().conferenceCallSizeLimit;
        L23:
            r0.limit = r12;
            this.currentAccount.getConnectionsManager().sendRequest(r0, new ChatObject$Call$$ExternalSyntheticLambda11(this, r4, r0));
            return;
        L22:
            r12 = 20;
            goto L23
        }

        public void migrateToChat(TLRPC.Chat r6) {
            this.chatId = r6.id;
            VoIPService r0 = VoIPService.getSharedInstance();
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.getAccount() == this.currentAccount.getCurrentAccount()) goto L7;
            return;
        L7:
            if (r0.getChat() != null) goto L9;
            return;
        L9:
            if (r0.getChat().id != (-this.chatId)) goto L15;
            r0.migrateToChat(r6);
            return;
        }

        public void processGroupCallUpdate(TLRPC.GroupCall r5) {
            boolean r2 = true;
            if (this.call.version >= r5.version) goto L5;
            this.nextLoadOffset = null;
            loadMembers(true);
        L5:
            this.call = TlUtils.applyGroupCallUpdate(this.call, r5);
            TLRPC.GroupCallParticipant r52 = this.participants.get(getSelfId());
            if (this.call.record_start_date != 0) goto L9;
            r2 = false;
        L9:
            this.recording = r2;
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{Long.valueOf(this.chatId), Long.valueOf(this.call.id), Boolean.FALSE});
        }

        public void processGroupCallUpdate(TL_update.TL_updateGroupCall r1) {
            processGroupCallUpdate(r1.call);
        }

        public void processParticipantsUpdate(TL_update.TL_updateGroupCallParticipants r29, boolean r30) {
            long r3 = 0;
            boolean r6 = true;
            if (r30 == true) goto L40;
            int r7 = r29.participants.size();
            int r8 = 0;
        L5:
            if (r8 >= r7) goto L10;
            if (r29.participants.get(r8).versioned == true) goto L8;
            r8 = r8 + 1;
            goto L5
        L8:
            boolean r72 = true;
        L11:
            if (r72 == true) goto L13;
        L33:
            if (r72 == false) goto L40;
            if (r29.version >= this.call.version) goto L40;
            if (BuildVars.LOGS_ENABLED == false) goto L227;
            FileLog.d("ignore processParticipantsUpdate because of version");
            return;
        L227:
            return;
        L13:
            if ((this.call.version + 1) >= r29.version) goto L33;
            if (this.reloadingMembers == true) goto L24;
            if (this.updatesStartWaitTime == 0) goto L24;
            if (Math.abs(System.currentTimeMillis() - this.updatesStartWaitTime) <= 1500) goto L24;
            this.nextLoadOffset = null;
            loadMembers(true);
            return;
        L24:
            if (this.updatesStartWaitTime != 0) goto L27;
            this.updatesStartWaitTime = System.currentTimeMillis();
        L27:
            if (BuildVars.LOGS_ENABLED == false) goto L29;
            FileLog.d("add TL_updateGroupCallParticipants to queue " + r29.version);
        L29:
            this.updatesQueue.add(r29);
            if (this.checkQueueRunnable != null) goto L226;
            ChatObject$Call$$ExternalSyntheticLambda0 r1 = new ChatObject$Call$$ExternalSyntheticLambda0(this);
            this.checkQueueRunnable = r1;
            AndroidUtilities.runOnUIThread(r1, 1500);
            return;
        L226:
            return;
        L10:
            r72 = false;
        L40:
            long r73 = getSelfId();
            long r9 = SystemClock.elapsedRealtime();
            if (this.sortedParticipants.isEmpty() == true) goto L43;
            ArrayList<TLRPC.GroupCallParticipant> r11 = this.sortedParticipants;
            int r112 = r11.get(r11.size() - 1).date;
        L44:
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.applyGroupCallVisibleParticipants, new Object[]{Long.valueOf(r9)});
            int r12 = r29.participants.size();
            long r19 = 0;
            int r13 = 0;
            boolean r14 = false;
            boolean r15 = false;
            boolean r16 = false;
            boolean r17 = false;
            boolean r18 = false;
        L45:
            if (r13 >= r12) goto L198;
            TLRPC.GroupCallParticipant r152 = r29.participants.get(r13);
            long r21 = r3;
            long r32 = MessageObject.getPeerId(r152.peer);
            if (BuildVars.LOGS_ENABLED == false) goto L49;
            boolean r23 = r6;
            FileLog.d("process participant " + r32 + " left = " + r152.left + " versioned " + r152.versioned + " flags = " + r152.flags + " self = " + r73 + " volume = " + r152.volume);
        L50:
            TLRPC.GroupCallParticipant r2 = this.participants.get(r32);
            if (r152.left == false) goto L89;
            if (r2 != null) goto L59;
            long r24 = r73;
            if (r29.version == this.call.version) goto L56;
        L60:
            if (r2 == null) goto L84;
            this.participants.remove(r32);
            processAllSources(r2, false);
            this.sortedParticipants.remove(r2);
            this.visibleParticipants.remove(r2);
            if (this.currentSpeakingPeers.get(r32, null) == null) goto L76;
            AccountInstance r82 = this.currentAccount;
            if (r32 <= r21) goto L70;
            TLRPC.User r74 = MessagesController.getInstance(r82.getCurrentAccount()).getUser(Long.valueOf(r32));
            StringBuilder r83 = new StringBuilder("left remove from speaking ");
            r83.append(r32);
            r83.append(" ");
            if (r74 != null) goto L68;
            String r5 = null;
        L69:
            r83.append(r5);
            Log.d("GroupCall", r83.toString());
            long r26 = r9;
        L75:
            this.currentSpeakingPeers.remove(r32);
            r17 = r23;
        L77:
            int r52 = 0;
        L79:
            if (r52 >= this.visibleVideoParticipants.size()) goto L85;
            if (MessageObject.getPeerId(this.visibleVideoParticipants.get(r52).participant.peer) != MessageObject.getPeerId(r2.peer)) goto L83;
            this.visibleVideoParticipants.remove(r52);
            r52 = r52 - 1;
        L83:
            r52 = r52 + 1;
        L85:
            TLRPC.GroupCall r22 = this.call;
            int r53 = r22.participants_count - 1;
            r22.participants_count = r53;
            if (r53 >= 0) goto L88;
            r22.participants_count = 0;
        L88:
            boolean r54 = r23;
        L195:
            if (r32 != r24) goto L197;
            r18 = r54;
        L197:
            r13 = r13 + 1;
            r6 = r54;
            r15 = r6;
            r3 = r21;
            r73 = r24;
            r9 = r26;
            goto L45
        L68:
            r5 = r74.first_name;
            goto L69
        L70:
            r26 = r9;
            TLRPC.Chat r75 = MessagesController.getInstance(r82.getCurrentAccount()).getChat(Long.valueOf(-r32));
            StringBuilder r84 = new StringBuilder("left remove from speaking ");
            r84.append(r32);
            r84.append(" ");
            if (r75 != null) goto L73;
            String r55 = null;
        L74:
            r84.append(r55);
            Log.d("GroupCall", r84.toString());
            goto L75
        L73:
            r55 = r75.title;
            goto L74
        L76:
            r26 = r9;
            goto L77
        L84:
            r26 = r9;
            goto L85
        L56:
            if (BuildVars.LOGS_ENABLED == false) goto L58;
            FileLog.d("unknowd participant left, reload call");
        L58:
            r14 = r23;
            goto L60
        L59:
            r24 = r73;
            goto L60
        L89:
            r24 = r73;
            r26 = r9;
            if (this.invitedUsersMap.contains(Long.valueOf(r32)) == false) goto L92;
            Long r76 = Long.valueOf(r32);
            this.invitedUsersMap.remove(r76);
            this.invitedUsers.remove(r76);
        L92:
            if (r2 == null) goto L150;
            if (BuildVars.LOGS_ENABLED == false) goto L96;
            FileLog.d("new participant, update old");
        L96:
            r2.muted = r152.muted;
            if (r152.muted == false) goto L114;
            if (this.currentSpeakingPeers.get(r32, null) == null) goto L114;
            this.currentSpeakingPeers.remove(r32);
            AccountInstance r92 = this.currentAccount;
            if (r32 <= r21) goto L107;
            TLRPC.User r77 = MessagesController.getInstance(r92.getCurrentAccount()).getUser(Long.valueOf(r32));
            StringBuilder r93 = new StringBuilder("muted remove from speaking ");
            r93.append(r32);
            r93.append(" ");
            if (r77 != null) goto L105;
            String r56 = null;
        L106:
            r93.append(r56);
            Log.d("GroupCall", r93.toString());
        L112:
            r17 = r23;
            goto L114
        L105:
            r56 = r77.first_name;
            goto L106
        L107:
            TLRPC.Chat r78 = MessagesController.getInstance(r92.getCurrentAccount()).getChat(Long.valueOf(-r32));
            StringBuilder r85 = new StringBuilder("muted remove from speaking ");
            r85.append(r32);
            r85.append(" ");
            if (r78 != null) goto L110;
            String r57 = null;
        L111:
            r85.append(r57);
            Log.d("GroupCall", r85.toString());
            goto L112
        L110:
            r57 = r78.title;
        L114:
            if (r152.min == true) goto L116;
            r2.volume = r152.volume;
            r2.muted_by_you = r152.muted_by_you;
        L126:
            r2.flags = r152.flags;
            r2.can_self_unmute = r152.can_self_unmute;
            r2.video_joined = r152.video_joined;
            if (r2.raise_hand_rating == r21) goto L129;
        L131:
            r2.raise_hand_rating = r152.raise_hand_rating;
            r2.date = r152.date;
            int r58 = Math.max(r2.active_date, r152.active_date);
            r2.lastTypingDate = r58;
            if (r26 == r2.lastVisibleDate) goto L135;
            r2.active_date = r58;
        L135:
            if (r2.source != r152.source) goto L147;
            if (isSameVideo(r2.video, r152.video) == false) goto L147;
            if (isSameVideo(r2.presentation, r152.presentation) == false) goto L147;
            TLRPC.TL_groupCallParticipantVideo r25 = r2.video;
            if (r25 == null) goto L148;
            TLRPC.TL_groupCallParticipantVideo r59 = r152.video;
            if (r59 == null) goto L148;
            r25.paused = r59.paused;
        L148:
            r54 = true;
        L185:
            if (r32 == r24) goto L187;
        L193:
            r16 = r54;
            goto L195
        L187:
            if (r152.active_date != 0) goto L193;
            if (r152.can_self_unmute == false) goto L191;
        L192:
            r152.active_date = this.currentAccount.getConnectionsManager().getCurrentTime();
            goto L193
        L191:
            if (r152.muted == true) goto L193;
        L147:
            processAllSources(r2, false);
            r2.video = r152.video;
            r2.presentation = r152.presentation;
            r2.source = r152.source;
            processAllSources(r2, r23);
            r152.presentationEndpoint = r2.presentationEndpoint;
            r152.videoEndpoint = r2.videoEndpoint;
            r152.videoIndex = r2.videoIndex;
            goto L148
        L129:
            if (r152.raise_hand_rating == r21) goto L131;
            r2.lastRaiseHandDate = SystemClock.elapsedRealtime();
            goto L131
        L116:
            int r510 = r152.flags;
            if ((r510 & 128) == 0) goto L122;
            if ((r2.flags & 128) != 0) goto L122;
            r152.flags = r510 & (-129);
        L122:
            if (r152.volume_by_admin == false) goto L126;
            if (r2.volume_by_admin == false) goto L126;
            r2.volume = r152.volume;
            goto L126
        L150:
            if (r152.just_joined == false) goto L164;
            if (r32 == r24) goto L154;
            r19 = r32;
        L154:
            TLRPC.GroupCall r27 = this.call;
            r27.participants_count++;
            if (r29.version != r27.version) goto L161;
            if (BuildVars.LOGS_ENABLED == false) goto L159;
            FileLog.d("new participant, just joined, reload call");
        L159:
            r14 = true;
            goto L164
        L161:
            if (BuildVars.LOGS_ENABLED == false) goto L164;
            FileLog.d("new participant, just joined");
        L164:
            if (r152.raise_hand_rating == r21) goto L167;
            r152.lastRaiseHandDate = SystemClock.elapsedRealtime();
        L167:
            if (r32 != r24) goto L169;
        L182:
            this.sortedParticipants.add(r152);
        L183:
            this.participants.put(r32, r152);
            r54 = true;
            processAllSources(r152, true);
            goto L185
        L169:
            if (this.sortedParticipants.size() < 20) goto L182;
            if (r152.date <= r112) goto L182;
            if (r152.active_date != 0) goto L182;
            if (r152.can_self_unmute == true) goto L182;
            if (r152.muted == false) goto L182;
            if (r152.min == false) goto L182;
            if (this.membersLoadEndReached == false) goto L183;
        L49:
            r23 = r6;
            goto L50
        L198:
            int r110 = r29.version;
            TLRPC.GroupCall r28 = this.call;
            if (r110 <= r28.version) goto L204;
            r28.version = r110;
            if (r30 == true) goto L204;
            processUpdatesQueue();
        L204:
            if (this.call.participants_count >= this.participants.size()) goto L207;
            this.call.participants_count = this.participants.size();
        L207:
            if (BuildVars.LOGS_ENABLED == false) goto L209;
            FileLog.d("new participants count after update " + this.call.participants_count);
        L209:
            if (r14 == false) goto L211;
            loadGroupCall();
        L211:
            if (r15 == false) goto L215;
            if (r16 == false) goto L214;
            sortParticipants();
        L214:
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{Long.valueOf(this.chatId), Long.valueOf(this.call.id), Boolean.valueOf(r18), Long.valueOf(r19)});
        L215:
            if (r17 == false) goto L228;
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.groupCallSpeakingUsersUpdated, new Object[]{Long.valueOf(this.chatId), Long.valueOf(this.call.id), Boolean.FALSE});
            return;
        L228:
            return;
        L43:
            r112 = 0;
            goto L44
        }

        public void processTypingsUpdate(AccountInstance r12, ArrayList<Long> r13, int r14) {
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.applyGroupCallVisibleParticipants, new Object[]{Long.valueOf(SystemClock.elapsedRealtime())});
            int r122 = r13.size();
            int r0 = 0;
            boolean r2 = false;
            ArrayList<Long> r3 = null;
        L4:
            if (r0 >= r122) goto L17;
            Long r5 = r13.get(r0);
            TLRPC.GroupCallParticipant r6 = this.participants.get(r5.longValue());
            if (r6 != null) goto L8;
            if (r3 != null) goto L15;
            r3 = new ArrayList();
        L15:
            r3.add(r5);
        L16:
            r0 = r0 + 1;
            goto L4
        L8:
            if ((r14 - r6.lastTypingDate) <= 10) goto L16;
            if (r6.lastVisibleDate == r14) goto L12;
            r6.active_date = r14;
        L12:
            r6.lastTypingDate = r14;
            r2 = true;
            goto L16
        L17:
            if (r3 == null) goto L19;
            loadUnknownParticipants(r3, true, null);
        L19:
            if (r2 == false) goto L26;
            sortParticipants();
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{Long.valueOf(this.chatId), Long.valueOf(this.call.id), Boolean.FALSE});
            return;
        }

        public void processUnknownVideoParticipants(int[] r7, OnParticipantsLoad r8) {
            ArrayList<Long> r3 = null;
            int r2 = 0;
        L4:
            if (r2 >= r7.length) goto L16;
            if (this.participantsBySources.get(r7[r2]) != null) goto L15;
            if (this.participantsByVideoSources.get(r7[r2]) != null) goto L15;
            if (this.participantsByPresentationSources.get(r7[r2]) != null) goto L15;
            if (r3 != null) goto L14;
            r3 = new ArrayList();
        L14:
            r3.add(Long.valueOf(r7[r2]));
        L15:
            r2 = r2 + 1;
            goto L4
        L16:
            if (r3 == null) goto L19;
            loadUnknownParticipants(r3, false, r8);
            return;
        L19:
            r8.onLoad(null);
        }

        public void processVoiceLevelsUpdate(int[] r27, float[] r28, boolean[] r29) {
            int[] r1 = r27;
            int r2 = this.currentAccount.getConnectionsManager().getCurrentTime();
            long r3 = SystemClock.elapsedRealtime();
            long r5 = SystemClock.uptimeMillis();
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.applyGroupCallVisibleParticipants, new Object[]{Long.valueOf(r3)});
            int r9 = 0;
            ArrayList<Long> r10 = null;
            boolean r11 = false;
            boolean r12 = false;
        L4:
            if (r9 >= r1.length) goto L67;
            int r13 = r1[r9];
            if (r13 != 0) goto L8;
            TLRPC.GroupCallParticipant r132 = this.participants.get(getSelfId());
        L9:
            if (r132 == null) goto L60;
            boolean r14 = r29[r9];
            r132.hasVoice = r14;
            if (r14 == false) goto L13;
        L14:
            r132.hasVoiceDelayed = r14;
            r132.lastVoiceUpdateTime = r3;
        L15:
            long r7 = MessageObject.getPeerId(r132.peer);
            float r142 = r28[r9];
            if (r142 <= 0.1f) goto L41;
            long r22 = r3;
            if (r29[r9] == true) goto L20;
        L25:
            r132.lastSpeakTime = r5;
            r132.amplitude = r142;
            if (this.currentSpeakingPeers.get(r7, null) != null) goto L40;
            AccountInstance r4 = this.currentAccount;
            if (r7 <= 0) goto L34;
            TLRPC.User r32 = MessagesController.getInstance(r4.getCurrentAccount()).getUser(Long.valueOf(r7));
            StringBuilder r42 = new StringBuilder("add to current speaking ");
            r42.append(r7);
            r42.append(" ");
            if (r32 != null) goto L32;
            String r33 = null;
        L33:
            r42.append(r33);
            Log.d("GroupCall", r42.toString());
            long r24 = r5;
        L39:
            this.currentSpeakingPeers.put(r7, r132);
            r12 = true;
        L66:
            r9 = r9 + 1;
            r1 = r27;
            r3 = r22;
            r5 = r24;
            goto L4
        L32:
            r33 = r32.first_name;
            goto L33
        L34:
            r24 = r5;
            TLRPC.Chat r34 = MessagesController.getInstance(r4.getCurrentAccount()).getChat(Long.valueOf(-r7));
            StringBuilder r43 = new StringBuilder("add to current speaking ");
            r43.append(r7);
            r43.append(" ");
            if (r34 != null) goto L37;
            String r35 = null;
        L38:
            r43.append(r35);
            Log.d("GroupCall", r43.toString());
            goto L39
        L37:
            r35 = r34.title;
            goto L38
        L40:
            r24 = r5;
            goto L66
        L20:
            if ((r132.lastTypingDate + 1) >= r2) goto L25;
            if (r22 == r132.lastVisibleDate) goto L24;
            r132.active_date = r2;
        L24:
            r132.lastTypingDate = r2;
            r11 = true;
            goto L25
        L41:
            r22 = r3;
            r24 = r5;
            if ((r24 - r132.lastSpeakTime) >= 500) goto L44;
        L58:
            boolean r44 = r11;
        L59:
            r132.amplitude = 0.0f;
        L65:
            r11 = r44;
            goto L66
        L44:
            if (this.currentSpeakingPeers.get(r7, null) == null) goto L58;
            this.currentSpeakingPeers.remove(r7);
            AccountInstance r45 = this.currentAccount;
            if (r7 <= 0) goto L52;
            TLRPC.User r36 = MessagesController.getInstance(r45.getCurrentAccount()).getUser(Long.valueOf(r7));
            StringBuilder r46 = new StringBuilder("remove from speaking ");
            r46.append(r7);
            r46.append(" ");
            if (r36 != null) goto L50;
            String r37 = null;
        L51:
            r46.append(r37);
            Log.d("GroupCall", r46.toString());
            r44 = r11;
        L57:
            r12 = true;
            goto L59
        L50:
            r37 = r36.first_name;
            goto L51
        L52:
            MessagesController r38 = MessagesController.getInstance(r45.getCurrentAccount());
            r44 = r11;
            TLRPC.Chat r39 = r38.getChat(Long.valueOf(-r7));
            StringBuilder r6 = new StringBuilder("remove from speaking ");
            r6.append(r7);
            r6.append(" ");
            if (r39 != null) goto L55;
            String r310 = null;
        L56:
            r6.append(r310);
            Log.d("GroupCall", r6.toString());
            goto L57
        L55:
            r310 = r39.title;
            goto L56
        L13:
            if ((r3 - r132.lastVoiceUpdateTime) <= 500) goto L15;
        L60:
            r22 = r3;
            r24 = r5;
            r44 = r11;
            if (r27[r9] == 0) goto L65;
            if (r10 != null) goto L64;
            r10 = new ArrayList();
        L64:
            r10.add(Long.valueOf(r27[r9]));
            goto L65
        L8:
            r132 = this.participantsBySources.get(r13);
            goto L9
        L67:
            boolean r47 = r11;
            if (r10 == null) goto L70;
            loadUnknownParticipants(r10, false, null);
        L70:
            if (r47 == false) goto L72;
            sortParticipants();
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{Long.valueOf(this.chatId), Long.valueOf(this.call.id), Boolean.FALSE});
        L72:
            if (r12 == true) goto L74;
            return;
        L74:
            if (this.currentSpeakingPeers.size() <= 0) goto L76;
            AndroidUtilities.cancelRunOnUIThread(this.updateCurrentSpeakingRunnable);
            AndroidUtilities.runOnUIThread(this.updateCurrentSpeakingRunnable, 550);
        L76:
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.groupCallSpeakingUsersUpdated, new Object[]{Long.valueOf(this.chatId), Long.valueOf(this.call.id), Boolean.FALSE});
        }

        public void reloadGroupCall() {
            TL_phone.getGroupCall r0 = new TL_phone.getGroupCall();
            r0.call = getInputGroupCall();
            r0.limit = 100;
            this.currentAccount.getConnectionsManager().sendRequest(r0, new ChatObject$Call$$ExternalSyntheticLambda2(this));
        }

        public void removeInvitedUser(long r4) {
            this.invitedUsersMap.remove(Long.valueOf(r4));
            this.invitedUsers.remove(Long.valueOf(r4));
            sortParticipants();
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{Long.valueOf(this.chatId), Long.valueOf(this.call.id), Boolean.FALSE});
        }

        public void saveActiveDates() {
            int r0 = this.sortedParticipants.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            this.sortedParticipants.get(r1).lastActiveDate = r2.active_date;
            r1 = r1 + 1;
            goto L3
        }

        public void setCall(AccountInstance r1, long r2, TLRPC.GroupCall r4) {
            this.chatId = r2;
            this.currentAccount = r1;
            this.call = TlUtils.applyGroupCallUpdate(this.call, r4);
            if (r4.record_start_date == 0) goto L5;
            boolean r12 = true;
        L6:
            this.recording = r12;
            sortParticipants();
            loadMembers(true);
            createNoVideoParticipant();
            if (r4.rtmp_stream == false) goto L10;
            createRtmpStreamParticipant(Collections.EMPTY_LIST);
            return;
        L10:
            return;
        L5:
            r12 = false;
            goto L6
        }

        public void setCall(AccountInstance r6, long r7, TL_phone.groupCall r9) {
            this.chatId = r7;
            this.currentAccount = r6;
            TLRPC.GroupCall r62 = TlUtils.applyGroupCallUpdate(this.call, r9.call);
            this.call = r62;
            int r72 = 0;
            if (r62.record_start_date == 0) goto L5;
            boolean r63 = true;
        L6:
            this.recording = r63;
            int r64 = r9.participants.size();
            int r0 = Integer.MAX_VALUE;
        L7:
            if (r72 >= r64) goto L9;
            TLRPC.GroupCallParticipant r1 = r9.participants.get(r72);
            this.participants.put(MessageObject.getPeerId(r1.peer), r1);
            this.sortedParticipants.add(r1);
            processAllSources(r1, true);
            r0 = Math.min(r0, r1.date);
            r72 = r72 + 1;
            goto L7
        L9:
            sortParticipants();
            this.nextLoadOffset = r9.participants_next_offset;
            loadMembers(true);
            createNoVideoParticipant();
            if (this.call.rtmp_stream == false) goto L14;
            createRtmpStreamParticipant(Collections.EMPTY_LIST);
            return;
        L14:
            return;
        L5:
            r63 = false;
            goto L6
        }

        public void setSelfPeer(TLRPC.InputPeer r3) {
            if (r3 != null) goto L6;
            this.selfPeer = null;
            return;
        L6:
            if ((r3 instanceof TLRPC.TL_inputPeerSelf) == false) goto L10;
            TLRPC.TL_peerUser r32 = new TLRPC.TL_peerUser();
            this.selfPeer = r32;
            r32.user_id = this.currentAccount.getUserConfig().getClientUserId();
            return;
        L10:
            if ((r3 instanceof TLRPC.TL_inputPeerUser) == false) goto L14;
            TLRPC.TL_peerUser r0 = new TLRPC.TL_peerUser();
            this.selfPeer = r0;
            r0.user_id = r3.user_id;
            return;
        L14:
            if ((r3 instanceof TLRPC.TL_inputPeerChat) == false) goto L17;
            TLRPC.TL_peerChat r02 = new TLRPC.TL_peerChat();
            this.selfPeer = r02;
            r02.chat_id = r3.chat_id;
            return;
        L17:
            TLRPC.TL_peerChannel r03 = new TLRPC.TL_peerChannel();
            this.selfPeer = r03;
            r03.channel_id = r3.channel_id;
        }

        public void setTitle(String r3) {
            TL_phone.editGroupCallTitle r0 = new TL_phone.editGroupCallTitle();
            r0.call = getInputGroupCall();
            r0.title = r3;
            this.currentAccount.getConnectionsManager().sendRequest(r0, new ChatObject$Call$$ExternalSyntheticLambda5(this));
        }

        public boolean shouldShowPanel() {
            TLRPC.GroupCall r0 = this.call;
            if (r0.participants_count <= 0) goto L5;
            return true;
        L5:
            if (r0.rtmp_stream == false) goto L7;
            return true;
        L7:
            if (isScheduled() == true) goto L14;
            return false;
        L14:
            return true;
        }

        public void sortParticipants() {
            this.visibleVideoParticipants.clear();
            this.visibleParticipants.clear();
            TLRPC.Chat r0 = this.currentAccount.getMessagesController().getChat(Long.valueOf(this.chatId));
            boolean r1 = ChatObject.canManageCalls(r0);
            VideoParticipant r2 = this.rtmpStreamParticipant;
            if (r2 == null) goto L5;
            this.visibleVideoParticipants.add(r2);
        L5:
            long r22 = getSelfId();
            VoIPService.getSharedInstance();
            TLRPC.GroupCallParticipant r4 = this.participants.get(r22);
            this.canStreamVideo = true;
            int r5 = 0;
            this.activeVideos = 0;
            int r6 = 0;
            boolean r7 = false;
        L7:
            if (r6 >= this.sortedParticipants.size()) goto L157;
            TLRPC.GroupCallParticipant r8 = this.sortedParticipants.get(r6);
            boolean r9 = videoIsActive(r8, false, this);
            boolean r10 = videoIsActive(r8, true, this);
            if (r8.self == true) goto L14;
            if (r9 == true) goto L12;
            if (r10 == false) goto L14;
        L12:
            this.activeVideos++;
        L14:
            if (this.kickedUsers.contains(Long.valueOf(DialogObject.getPeerDialogId(r8.peer))) == false) goto L16;
            this.sortedParticipants.remove(r6);
            r6 = r6 - 1;
        L16:
            if (r9 == true) goto L29;
            if (r10 == true) goto L29;
            if (r8.self == false) goto L22;
        L27:
            r8.videoIndex = 0;
        L38:
            r6 = r6 + 1;
            goto L7
        L22:
            if (this.canStreamVideo == false) goto L27;
            if (r8.video != null) goto L38;
            if (r8.presentation != null) goto L38;
        L29:
            if (this.canStreamVideo == true) goto L31;
            r8.videoIndex = 0;
        L37:
            r7 = true;
            goto L38
        L31:
            if (r8.videoIndex != 0) goto L37;
            if (r8.self == false) goto L35;
            r8.videoIndex = Integer.MAX_VALUE;
            goto L37
        L35:
            int r72 = videoPointer + 1;
            videoPointer = r72;
            r8.videoIndex = r72;
            goto L37
        L157:
            Collections.sort(this.sortedParticipants, new ChatObject$Call$$ExternalSyntheticLambda3(this, r22, r1));     // Catch: Exception -> L156
        L42:
            if (this.sortedParticipants.isEmpty() == false) goto L44;
            TLRPC.GroupCallParticipant r12 = null;
        L46:
            if (videoIsActive(r12, false, this) == false) goto L48;
        L49:
            int r23 = this.call.unmuted_video_count;
            if (r23 <= this.activeVideos) goto L61;
            this.activeVideos = r23;
            VoIPService r24 = VoIPService.getSharedInstance();
            if (r24 == null) goto L61;
            if (r24.groupCall != this) goto L61;
            if (r24.getVideoState(false) != 2) goto L58;
        L59:
            this.activeVideos--;
            goto L61
        L58:
            if (r24.getVideoState(true) == 2) goto L59;
        L61:
            if (this.sortedParticipants.size() > ChatObject.MAX_PARTICIPANTS_COUNT) goto L63;
        L73:
            checkOnlineParticipants();
            if (this.canStreamVideo == true) goto L79;
            if (r7 == false) goto L79;
            VideoParticipant r02 = this.videoNotAvailableParticipant;
            if (r02 == null) goto L79;
            this.visibleVideoParticipants.add(r02);
        L79:
            int r03 = 0;
            int r13 = 0;
        L80:
            int r25 = this.sortedParticipants.size();
            ArrayList<TLRPC.GroupCallParticipant> r62 = this.sortedParticipants;
            if (r03 >= r25) goto L132;
            TLRPC.GroupCallParticipant r26 = r62.get(r03);
            if (this.canStreamVideo == true) goto L85;
        L130:
            this.visibleParticipants.add(r26);
        L131:
            r03 = r03 + 1;
            goto L80
        L85:
            if (r26.videoIndex == 0) goto L130;
            if (r26.self == true) goto L108;
            if (videoIsActive(r26, true, this) == false) goto L108;
            if (videoIsActive(r26, false, this) == false) goto L108;
            VideoParticipant r63 = this.videoParticipantsCache.get(r26.videoEndpoint);
            if (r63 != null) goto L95;
            r63 = new VideoParticipant(r26, false, true);
            this.videoParticipantsCache.put(r26.videoEndpoint, r63);
        L96:
            VideoParticipant r82 = this.videoParticipantsCache.get(r26.presentationEndpoint);
            if (r82 != null) goto L99;
            r82 = new VideoParticipant(r26, true, true);
        L100:
            this.visibleVideoParticipants.add(r63);
            if (r63.aspectRatio <= 1.0f) goto L103;
            r13 = this.visibleVideoParticipants.size() - 1;
        L103:
            this.visibleVideoParticipants.add(r82);
            if (r82.aspectRatio <= 1.0f) goto L131;
            int r14 = this.visibleVideoParticipants.size();
        L106:
            r13 = r14 - 1;
            goto L131
        L99:
            r82.participant = r26;
            r82.presentation = true;
            r82.hasSame = true;
            goto L100
        L95:
            r63.participant = r26;
            r63.presentation = false;
            r63.hasSame = true;
        L108:
            if (r26.self == true) goto L110;
            boolean r64 = videoIsActive(r26, true, this);
            HashMap<String, VideoParticipant> r83 = this.videoParticipantsCache;
            if (r64 == false) goto L118;
            String r92 = r26.presentationEndpoint;
        L119:
            VideoParticipant r84 = r83.get(r92);
            if (r84 != null) goto L126;
            r84 = new VideoParticipant(r26, r64, false);
            HashMap<String, VideoParticipant> r93 = this.videoParticipantsCache;
            if (r64 == false) goto L124;
            String r27 = r26.presentationEndpoint;
        L125:
            r93.put(r27, r84);
        L127:
            this.visibleVideoParticipants.add(r84);
            if (r84.aspectRatio <= 1.0f) goto L131;
            r14 = this.visibleVideoParticipants.size();
            goto L106
        L124:
            r27 = r26.videoEndpoint;
            goto L125
        L126:
            r84.participant = r26;
            r84.presentation = r64;
            r84.hasSame = false;
            goto L127
        L118:
            r92 = r26.videoEndpoint;
            goto L119
        L110:
            if (videoIsActive(r26, true, this) == false) goto L113;
            this.visibleVideoParticipants.add(new VideoParticipant(r26, true, false));
        L113:
            if (videoIsActive(r26, false, this) == false) goto L131;
            this.visibleVideoParticipants.add(new VideoParticipant(r26, false, false));
            goto L131
        L132:
            int r04 = r62.size();
            int r28 = 0;
        L133:
            if (r28 >= r04) goto L135;
            TLRPC.GroupCallParticipant r73 = r62.get(r28);
            r28 = r28 + 1;
            this.kickedUsers.remove(Long.valueOf(DialogObject.getPeerDialogId(r73.peer)));
            goto L133
        L135:
            VoIPService r05 = VoIPService.getSharedInstance();
            if (this.call == null) goto L151;
            if (r05 == null) goto L151;
            if (r05.isConference() == false) goto L151;
            if (r05.groupCall != this) goto L151;
            ArrayList<TLRPC.GroupCallParticipant> r29 = this.sortedParticipants;
            int r65 = r29.size();
            int r74 = 0;
        L143:
            if (r74 >= r65) goto L145;
            TLRPC.GroupCallParticipant r85 = r29.get(r74);
            r74 = r74 + 1;
            r05.conference.joiningBlockchainParticipants.remove(Long.valueOf(DialogObject.getPeerDialogId(r85.peer)));
            goto L143
        L145:
            this.shadyLeftParticipants.clear();
            this.shadyLeftParticipants.addAll(r05.conference.getShadyLeftParticipants(this.sortedParticipants));
            this.shadyJoinParticipants.clear();
            this.shadyJoinParticipants.addAll(r05.conference.getShadyJoiningParticipants(this.sortedParticipants));
            ArrayList<TLRPC.GroupCallParticipant> r06 = this.sortedParticipants;
            int r210 = r06.size();
        L146:
            if (r5 >= r210) goto L151;
            TLRPC.GroupCallParticipant r66 = r06.get(r5);
            r5 = r5 + 1;
            long r67 = DialogObject.getPeerDialogId(r66.peer);
            if (this.invitedUsersMap.contains(Long.valueOf(r67)) == false) goto L146;
            this.invitedUsersMap.remove(Long.valueOf(r67));
            this.invitedUsers.remove(Long.valueOf(r67));
        L151:
            if (GroupCallActivity.isLandscapeMode == false) goto L153;
            return;
        L153:
            if ((this.visibleVideoParticipants.size() % 2) != 1) goto L182;
            this.visibleVideoParticipants.add(this.visibleVideoParticipants.remove(r13));
            return;
        L182:
            return;
        L63:
            if (ChatObject.canManageCalls(r0) == true) goto L65;
        L66:
            int r07 = this.sortedParticipants.size();
            int r15 = ChatObject.MAX_PARTICIPANTS_COUNT;
        L67:
            if (r15 >= r07) goto L73;
            TLRPC.GroupCallParticipant r211 = this.sortedParticipants.get(ChatObject.MAX_PARTICIPANTS_COUNT);
            if (r211.raise_hand_rating != 0) goto L72;
            processAllSources(r211, false);
            this.participants.remove(MessageObject.getPeerId(r211.peer));
            this.sortedParticipants.remove(ChatObject.MAX_PARTICIPANTS_COUNT);
        L72:
            r15 = r15 + 1;
            goto L67
        L65:
            if (r12.raise_hand_rating != 0) goto L73;
        L48:
            if (videoIsActive(r12, true, this) == false) goto L61;
        L44:
            ArrayList<TLRPC.GroupCallParticipant> r16 = this.sortedParticipants;
            r12 = r16.get(r16.size() - 1);
            goto L46
        }

        public void toggleRecord(String r4, int r5) {
            boolean r1 = true;
            this.recording = !this.recording;
            TL_phone.toggleGroupCallRecord r0 = new TL_phone.toggleGroupCallRecord();
            r0.call = getInputGroupCall();
            r0.start = this.recording;
            if (r4 == null) goto L5;
            r0.title = r4;
            r0.flags |= 2;
        L5:
            if (r5 == 1) goto L7;
            if (r5 == 2) goto L7;
        L12:
            this.currentAccount.getConnectionsManager().sendRequest(r0, new ChatObject$Call$$ExternalSyntheticLambda7(this));
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{Long.valueOf(this.chatId), Long.valueOf(this.call.id), Boolean.FALSE});
            return;
        L7:
            r0.flags |= 4;
            r0.video = true;
            if (r5 == 1) goto L11;
            r1 = false;
        L11:
            r0.video_portrait = r1;
            goto L12
        }

        public void updateVisibleParticipants() {
            sortParticipants();
            this.currentAccount.getNotificationCenter().postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{Long.valueOf(this.chatId), Long.valueOf(this.call.id), Boolean.FALSE, 0L});
        }
    }

    /* loaded from: classes5.dex */
    public static class VideoParticipant {
        public float aspectRatio;
        public int aspectRatioFromHeight;
        public int aspectRatioFromWidth;
        public boolean hasSame;
        public TLRPC.GroupCallParticipant participant;
        public boolean presentation;

        public VideoParticipant(TLRPC.GroupCallParticipant r1, boolean r2, boolean r3) {
            this.participant = r1;
            this.presentation = r2;
            this.hasSame = r3;
        }

        private void setAspectRatio(float r2, Call r3) {
            if (this.aspectRatio == r2) goto L10;
            this.aspectRatio = r2;
            if (GroupCallActivity.isLandscapeMode == false) goto L7;
            return;
        L7:
            if ((r3.visibleVideoParticipants.size() % 2) != 1) goto L12;
            r3.updateVisibleParticipants();
            return;
        L12:
            return;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L15:
            return false;
        L8:
            if (getClass() != r5.getClass()) goto L15;
            VideoParticipant r52 = (VideoParticipant) r5;
            if (this.presentation != r52.presentation) goto L15;
            if (MessageObject.getPeerId(this.participant.peer) != MessageObject.getPeerId(r52.participant.peer)) goto L15;
            return true;
        }

        public void setAspectRatio(int r1, int r2, Call r3) {
            this.aspectRatioFromWidth = r1;
            this.aspectRatioFromHeight = r2;
            setAspectRatio(r1 / r2, r3);
        }
    }

    public ChatObject() {
    }

    public static boolean areTabsEnabled(TLRPC.Chat r1) {
        if (SharedConfig.forceForumTabs == true) goto L10;
        if (r1 != null) goto L6;
        return false;
    L6:
        if (r1.forum_tabs == true) goto L13;
        return false;
    L13:
        return true;
    L10:
        return true;
    }

    public static boolean canAddAdmins(TLRPC.Chat r1) {
        return canUserDoAction(r1, 4);
    }

    public static boolean canAddBotsToChat(TLRPC.Chat r3) {
        if (isChannel(r3) == false) goto L16;
        if (r3.megagroup == false) goto L18;
        TLRPC.TL_chatAdminRights r0 = r3.admin_rights;
        if (r0 == null) goto L13;
        if (r0.post_messages == false) goto L11;
    L14:
        return true;
    L11:
        if (r0.add_admins == true) goto L14;
    L13:
        if (r3.creator == true) goto L14;
        return false;
    L18:
        return false;
    L16:
        if (r3.migrated_to != null) goto L21;
        return true;
    L21:
        return false;
    }

    public static boolean canAddChatToCommunity(TLRPC.Chat r1) {
        return canUserDoAction(r1, 27);
    }

    public static boolean canAddUsers(TLRPC.Chat r1) {
        return canUserDoAction(r1, 3);
    }

    public static boolean canBlockUsers(TLRPC.Chat r1) {
        return canUserDoAction(r1, 2);
    }

    public static boolean canChangeChatInfo(TLRPC.Chat r1) {
        return canUserDoAction(r1, 1);
    }

    public static boolean canCreateTopic(TLRPC.Chat r1) {
        return canUserDoAction(r1, 15);
    }

    public static boolean canDeleteTopic(int r4, TLRPC.Chat r5, long r6) {
        if (r6 != 1) goto L5;
        return false;
    L5:
        if (r5 != null) goto L7;
    L10:
        return false;
    L7:
        if (canDeleteTopic(r4, r5, MessagesController.getInstance(r4).getTopicsController().findTopic(r5.id, r6)) == false) goto L10;
        return true;
    }

    public static boolean canDeleteTopic(int r3, TLRPC.Chat r4, TLRPC.TL_forumTopic r5) {
        if (r5 == null) goto L8;
        if (r5.id != 1) goto L8;
        return false;
    L8:
        if (canUserDoAction(r4, 13) == false) goto L10;
    L25:
        return true;
    L10:
        if (isMyTopic(r3, r5) == false) goto L24;
        TLRPC.Message r32 = r5.topMessage;
        if (r32 == null) goto L24;
        TLRPC.Message r42 = r5.topicStartMessage;
        if (r42 == null) goto L24;
        int r33 = r32.id - r42.id;
        ArrayList<MessageObject> r43 = r5.groupedMessages;
        if (r43 != null) goto L18;
        int r44 = 0;
    L20:
        if (r33 > Math.max(1, r44)) goto L24;
        if (MessageObject.peersEqual(r5.from_id, r5.topMessage.from_id) == false) goto L24;
    L18:
        r44 = r43.size();
    L24:
        return false;
    }

    public static boolean canManageCalls(TLRPC.Chat r1) {
        return canUserDoAction(r1, 14);
    }

    public static boolean canManageMonoForum(int r1, long r2) {
        return canUserDoChannelDirectAdminAction(r1, r2, 24);
    }

    public static boolean canManageMonoForum(int r1, TLRPC.Chat r2) {
        return canUserDoChannelDirectAdminAction(r1, r2, 24);
    }

    public static boolean canManageMyTag(TLRPC.Chat r2) {
        if (r2 != null) goto L6;
        return false;
    L6:
        if (r2.creator == false) goto L8;
        return true;
    L8:
        TLRPC.TL_chatBannedRights r0 = r2.banned_rights;
        if (r0 != null) goto L16;
        TLRPC.TL_chatBannedRights r22 = r2.default_banned_rights;
        if (r22 != null) goto L13;
        return true;
    L13:
        boolean r23 = r22.edit_rank;
    L15:
        return !r23;
    L16:
        r23 = r0.edit_rank;
        goto L15
    }

    public static boolean canManageTags(TLRPC.Chat r1) {
        return canUserDoAction(r1, 25);
    }

    public static boolean canManageTopic(int r1, TLRPC.Chat r2, long r3) {
        if (canManageTopics(r2) == false) goto L5;
        return true;
    L5:
        if (isMyTopic(r1, r2, r3) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean canManageTopic(int r0, TLRPC.Chat r1, TLRPC.TL_forumTopic r2) {
        if (canManageTopics(r1) == false) goto L5;
        return true;
    L5:
        if (isMyTopic(r0, r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean canManageTopics(TLRPC.Chat r1) {
        return canUserDoAdminAction(r1, 15);
    }

    public static boolean canPinMessages(TLRPC.Chat r2) {
        if (canUserDoAction(r2, 0) == false) goto L5;
        return true;
    L5:
        if (isChannel(r2) == true) goto L7;
    L13:
        return false;
    L7:
        if (r2.megagroup == true) goto L13;
        TLRPC.TL_chatAdminRights r22 = r2.admin_rights;
        if (r22 == null) goto L13;
        if (r22.edit_messages == false) goto L13;
        return true;
    }

    public static boolean canPost(TLRPC.Chat r1) {
        return canUserDoAction(r1, 5);
    }

    public static boolean canRemoveBotFromCommunity(TLRPC.User r1, TLRPC.Chat r2) {
        if (r1 == null) goto L12;
        if (r2 != null) goto L5;
        return false;
    L5:
        if (r1.bot == false) goto L9;
        if (r1.bot_can_edit == false) goto L9;
        return true;
    L9:
        if (canUserDoAdminAction(r2, 27) == false) goto L16;
        return true;
    L16:
        return false;
    L12:
        return false;
    }

    public static boolean canRemoveChatFromCommunity(TLRPC.Chat r0, TLRPC.Chat r1) {
        if (r0 == null) goto L10;
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r0.creator == false) goto L7;
        return true;
    L7:
        if (canUserDoAdminAction(r1, 27) == false) goto L14;
        return true;
    L14:
        return false;
    L10:
        return false;
    }

    public static boolean canSendAnyMedia(TLRPC.Chat r1) {
        if (canSendPhoto(r1) == false) goto L5;
        return true;
    L5:
        if (canSendVideo(r1) == false) goto L7;
        return true;
    L7:
        if (canSendRoundVideo(r1) == false) goto L9;
        return true;
    L9:
        if (canSendVoice(r1) == false) goto L11;
        return true;
    L11:
        if (canSendDocument(r1) == false) goto L13;
        return true;
    L13:
        if (canSendMusic(r1) == false) goto L15;
        return true;
    L15:
        if (canSendStickers(r1) == true) goto L26;
        return false;
    L26:
        return true;
    }

    public static boolean canSendAsPeers(TLRPC.Chat r1) {
        if (isChannel(r1) == true) goto L5;
        return false;
    L5:
        if (r1.megagroup == true) goto L13;
        if (r1.signatures == false) goto L13;
        if (hasAdminRights(r1) == false) goto L13;
        if (canWriteToChat(r1) == false) goto L13;
        return true;
    L13:
        if (r1.megagroup == true) goto L15;
        return false;
    L15:
        if (isPublic(r1) == false) goto L17;
        return true;
    L17:
        if (r1.has_geo == false) goto L19;
        return true;
    L19:
        if (r1.has_link == false) goto L28;
        return true;
    L28:
        return false;
    }

    public static boolean canSendDocument(TLRPC.Chat r1) {
        if (isIgnoredChatRestrictionsForBoosters(r1) == false) goto L7;
        return true;
    L7:
        return canUserDoAction(r1, 19);
    }

    public static boolean canSendEmbed(TLRPC.Chat r1) {
        if (isIgnoredChatRestrictionsForBoosters(r1) == false) goto L7;
        return true;
    L7:
        return canUserDoAction(r1, 9);
    }

    public static boolean canSendMessages(TLRPC.Chat r1) {
        if (isNotInChat(r1) == false) goto L10;
        if (r1 == null) goto L10;
        if (r1.join_to_send == false) goto L10;
        return false;
    L10:
        if (isIgnoredChatRestrictionsForBoosters(r1) == false) goto L14;
        return true;
    L14:
        return canUserDoAction(r1, 6);
    }

    public static boolean canSendMusic(TLRPC.Chat r1) {
        if (isIgnoredChatRestrictionsForBoosters(r1) == false) goto L7;
        return true;
    L7:
        return canUserDoAction(r1, 18);
    }

    public static boolean canSendPhoto(TLRPC.Chat r1) {
        if (isIgnoredChatRestrictionsForBoosters(r1) == false) goto L7;
        return true;
    L7:
        return canUserDoAction(r1, 16);
    }

    public static boolean canSendPlain(TLRPC.Chat r1) {
        if (isIgnoredChatRestrictionsForBoosters(r1) == false) goto L7;
        return true;
    L7:
        return canUserDoAction(r1, 22);
    }

    public static boolean canSendPolls(TLRPC.Chat r1) {
        if (isMonoForum(r1) == false) goto L5;
        return false;
    L5:
        if (isIgnoredChatRestrictionsForBoosters(r1) == false) goto L9;
        return true;
    L9:
        return canUserDoAction(r1, 10);
    }

    public static boolean canSendRoundVideo(TLRPC.Chat r1) {
        if (isIgnoredChatRestrictionsForBoosters(r1) == false) goto L7;
        return true;
    L7:
        return canUserDoAction(r1, 21);
    }

    public static boolean canSendStickers(TLRPC.Chat r1) {
        if (isIgnoredChatRestrictionsForBoosters(r1) == false) goto L7;
        return true;
    L7:
        return canUserDoAction(r1, 8);
    }

    public static boolean canSendVideo(TLRPC.Chat r1) {
        if (isIgnoredChatRestrictionsForBoosters(r1) == false) goto L7;
        return true;
    L7:
        return canUserDoAction(r1, 17);
    }

    public static boolean canSendVoice(TLRPC.Chat r1) {
        if (isIgnoredChatRestrictionsForBoosters(r1) == false) goto L7;
        return true;
    L7:
        return canUserDoAction(r1, 20);
    }

    public static boolean canUserDoAction(TLRPC.Chat r4, int r5) {
        if (r4 != null) goto L6;
        return true;
    L6:
        if (canUserDoAdminAction(r4, r5) == false) goto L9;
        return true;
    L9:
        if (getBannedRight(r4.banned_rights, r5) == false) goto L12;
        return false;
    L12:
        if (isBannableAction(r5) == true) goto L14;
    L44:
        return false;
    L14:
        if (r4.admin_rights != null) goto L16;
    L18:
        TLRPC.TL_chatBannedRights r1 = r4.default_banned_rights;
        if (r1 == null) goto L21;
    L39:
        if (r1 == null) goto L44;
        if (getBannedRight(r1, r5) == true) goto L44;
        return true;
    L21:
        if ((r4 instanceof TLRPC.TL_chat_layer92) == false) goto L23;
    L38:
        return true;
    L23:
        if ((r4 instanceof TLRPC.TL_chat_old) == true) goto L38;
        if ((r4 instanceof TLRPC.TL_chat_old2) == true) goto L38;
        if ((r4 instanceof TLRPC.TL_channel_layer92) == true) goto L38;
        if ((r4 instanceof TLRPC.TL_channel_layer77) == true) goto L38;
        if ((r4 instanceof TLRPC.TL_channel_layer72) == true) goto L38;
        if ((r4 instanceof TLRPC.TL_channel_layer67) == true) goto L38;
        if ((r4 instanceof TLRPC.TL_channel_layer48) == true) goto L38;
        if ((r4 instanceof TLRPC.TL_channel_old) == false) goto L39;
    L16:
        if (isAdminAction(r5) == true) goto L18;
        return true;
    }

    public static boolean canUserDoAction(TLRPC.Chat r3, TLRPC.ChannelParticipant r4, int r5) {
        if (r3 != null) goto L6;
        return true;
    L6:
        if (r4 != null) goto L9;
        return false;
    L9:
        if (canUserDoAdminAction(r4.admin_rights, r5) == false) goto L12;
        return true;
    L12:
        if (getBannedRight(r4.banned_rights, r5) == false) goto L15;
        return false;
    L15:
        if (isBannableAction(r5) == true) goto L17;
    L47:
        return false;
    L17:
        if (r4.admin_rights != null) goto L19;
    L21:
        TLRPC.TL_chatBannedRights r42 = r3.default_banned_rights;
        if (r42 == null) goto L24;
    L42:
        if (r42 == null) goto L47;
        if (getBannedRight(r42, r5) == true) goto L47;
        return true;
    L24:
        if ((r3 instanceof TLRPC.TL_chat_layer92) == false) goto L26;
    L41:
        return true;
    L26:
        if ((r3 instanceof TLRPC.TL_chat_old) == true) goto L41;
        if ((r3 instanceof TLRPC.TL_chat_old2) == true) goto L41;
        if ((r3 instanceof TLRPC.TL_channel_layer92) == true) goto L41;
        if ((r3 instanceof TLRPC.TL_channel_layer77) == true) goto L41;
        if ((r3 instanceof TLRPC.TL_channel_layer72) == true) goto L41;
        if ((r3 instanceof TLRPC.TL_channel_layer67) == true) goto L41;
        if ((r3 instanceof TLRPC.TL_channel_layer48) == true) goto L41;
        if ((r3 instanceof TLRPC.TL_channel_old) == false) goto L42;
    L19:
        if (isAdminAction(r5) == true) goto L21;
        return true;
    }

    public static boolean canUserDoAdminAction(TLRPC.Chat r3, int r4) {
        if (r3 != null) goto L6;
        return false;
    L6:
        if (r3.creator == false) goto L8;
        return true;
    L8:
        TLRPC.TL_chatAdminRights r32 = r3.admin_rights;
        if (r32 == null) goto L46;
        if (r4 == 0) goto L43;
        if (r4 != 1) goto L13;
        boolean r33 = r32.change_info;
    L44:
        if (r33 == false) goto L46;
        return true;
    L13:
        if (r4 != 2) goto L15;
        r33 = r32.ban_users;
        goto L44
    L15:
        if (r4 != 3) goto L17;
        r33 = r32.invite_users;
        goto L44
    L17:
        if (r4 != 4) goto L19;
        r33 = r32.add_admins;
        goto L44
    L19:
        if (r4 != 5) goto L21;
        r33 = r32.post_messages;
        goto L44
    L21:
        if (r4 != 24) goto L23;
        r33 = r32.manage_direct_messages;
        goto L44
    L23:
        if (r4 != 25) goto L25;
        r33 = r32.manage_ranks;
        goto L44
    L25:
        if (r4 != 27) goto L27;
        r33 = r32.manage_linked_peers;
        goto L44
    L27:
        if (r4 == 28) goto L34;
        switch(r4) {
            case 12: goto L33;
            case 13: goto L32;
            case 14: goto L31;
            case 15: goto L30;
            default: goto L29;
        };
    L29:
        r33 = false;
        goto L44
    L30:
        r33 = r32.manage_topics;
        goto L44
    L31:
        r33 = r32.manage_call;
        goto L44
    L32:
        r33 = r32.delete_messages;
        goto L44
    L33:
        r33 = r32.edit_messages;
        goto L44
    L34:
        r33 = r32.manage_welcome_messages;
        goto L44
    L43:
        r33 = r32.pin_messages;
    L46:
        return false;
    }

    public static boolean canUserDoAdminAction(TLRPC.TL_chatAdminRights r3, int r4) {
        if (r3 != null) goto L5;
    L32:
        return false;
    L5:
        if (r4 == 0) goto L29;
        if (r4 != 1) goto L8;
        boolean r32 = r3.change_info;
    L30:
        if (r32 == false) goto L32;
        return true;
    L8:
        if (r4 != 2) goto L10;
        r32 = r3.ban_users;
        goto L30
    L10:
        if (r4 != 3) goto L12;
        r32 = r3.invite_users;
        goto L30
    L12:
        if (r4 != 4) goto L14;
        r32 = r3.add_admins;
        goto L30
    L14:
        if (r4 != 5) goto L16;
        r32 = r3.post_messages;
        goto L30
    L16:
        if (r4 == 27) goto L23;
        switch(r4) {
            case 12: goto L22;
            case 13: goto L21;
            case 14: goto L20;
            case 15: goto L19;
            default: goto L18;
        };
    L18:
        r32 = false;
        goto L30
    L19:
        r32 = r3.manage_topics;
        goto L30
    L20:
        r32 = r3.manage_call;
        goto L30
    L21:
        r32 = r3.delete_messages;
        goto L30
    L22:
        r32 = r3.edit_messages;
        goto L30
    L23:
        r32 = r3.manage_linked_peers;
        goto L30
    L29:
        r32 = r3.pin_messages;
        goto L30
    }

    public static boolean canUserDoChannelDirectAdminAction(int r0, long r1, int r3) {
        return canUserDoAdminAction(getChannelDirectChatInternal(r0, r1), r3);
    }

    public static boolean canUserDoChannelDirectAdminAction(int r0, TLRPC.Chat r1, int r2) {
        return canUserDoAdminAction(getChannelDirectChatInternal(r0, r1), r2);
    }

    public static boolean canWriteToChat(TLRPC.Chat r1) {
        if (isChannel(r1) == true) goto L5;
        return true;
    L5:
        if (r1.creator == true) goto L24;
        TLRPC.TL_chatAdminRights r0 = r1.admin_rights;
        if (r0 == null) goto L11;
        if (r0.post_messages == false) goto L11;
        return true;
    L11:
        if (r1.broadcast == true) goto L15;
        if (r1.gigagroup == true) goto L15;
        return true;
    L15:
        if (r1.gigagroup == true) goto L17;
        return false;
    L17:
        if (hasAdminRights(r1) == true) goto L27;
        return false;
    L27:
        return true;
    L24:
        return true;
    }

    public static String getAllowedSendString(TLRPC.Chat r3) {
        StringBuilder r0 = new StringBuilder();
        if (canSendPhoto(r3) == false) goto L6;
        r0.append(LocaleController.getString(R.string.SendMediaPermissionPhotos));
    L6:
        if (canSendVideo(r3) == false) goto L12;
        if (r0.length() <= 0) goto L10;
        r0.append(", ");
    L10:
        r0.append(LocaleController.getString(R.string.SendMediaPermissionVideos));
    L12:
        if (canSendStickers(r3) == false) goto L18;
        if (r0.length() <= 0) goto L16;
        r0.append(", ");
    L16:
        r0.append(LocaleController.getString(R.string.SendMediaPermissionStickersGifs));
    L18:
        if (canSendMusic(r3) == false) goto L24;
        if (r0.length() <= 0) goto L22;
        r0.append(", ");
    L22:
        r0.append(LocaleController.getString(R.string.SendMediaPermissionMusic));
    L24:
        if (canSendDocument(r3) == false) goto L30;
        if (r0.length() <= 0) goto L28;
        r0.append(", ");
    L28:
        r0.append(LocaleController.getString(R.string.SendMediaPermissionFiles));
    L30:
        if (canSendVoice(r3) == false) goto L36;
        if (r0.length() <= 0) goto L34;
        r0.append(", ");
    L34:
        r0.append(LocaleController.getString(R.string.SendMediaPermissionVoice));
    L36:
        if (canSendRoundVideo(r3) == false) goto L42;
        if (r0.length() <= 0) goto L40;
        r0.append(", ");
    L40:
        r0.append(LocaleController.getString(R.string.SendMediaPermissionRound));
    L42:
        if (canSendEmbed(r3) == false) goto L48;
        if (r0.length() <= 0) goto L46;
        r0.append(", ");
    L46:
        r0.append(LocaleController.getString(R.string.SendMediaEmbededLinks));
    L48:
        return r0.toString();
    }

    private static boolean getBannedRight(TLRPC.TL_chatBannedRights r2, int r3) {
        if (r2 != null) goto L5;
        return false;
    L5:
        if (r3 == 0) goto L54;
        if (r3 == 1) goto L52;
        if (r3 == 3) goto L50;
        if (r3 == 26) goto L48;
        if (r3 == 27) goto L46;
        switch(r3) {
            case 6: goto L44;
            case 7: goto L42;
            case 8: goto L40;
            case 9: goto L38;
            case 10: goto L36;
            case 11: goto L34;
            default: goto L15;
        };
    L15:
        switch(r3) {
            case 15: goto L32;
            case 16: goto L30;
            case 17: goto L28;
            case 18: goto L26;
            case 19: goto L24;
            case 20: goto L22;
            case 21: goto L20;
            case 22: goto L18;
            default: goto L16;
        };
    L16:
        return false;
    L18:
        return r2.send_plain;
    L20:
        return r2.send_roundvideos;
    L22:
        return r2.send_voices;
    L24:
        return r2.send_docs;
    L26:
        return r2.send_audios;
    L28:
        return r2.send_videos;
    L30:
        return r2.send_photos;
    L32:
        return r2.manage_topics;
    L34:
        return r2.view_messages;
    L36:
        return r2.send_polls;
    L38:
        return r2.embed_links;
    L40:
        return r2.send_stickers;
    L42:
        return r2.send_media;
    L44:
        return r2.send_messages;
    L46:
        return r2.manage_linked_peers;
    L48:
        return r2.send_reactions;
    L50:
        return r2.invite_users;
    L52:
        return r2.change_info;
    L54:
        return r2.pin_messages;
    }

    public static String getBannedRightsString(TLRPC.TL_chatBannedRights r2) {
        return (((((((((((((((((((((((_UrlKt.FRAGMENT_ENCODE_SET + (r2.view_messages ? 1 : 0)) + (r2.send_messages ? 1 : 0)) + (r2.send_media ? 1 : 0)) + (r2.send_stickers ? 1 : 0)) + (r2.send_gifs ? 1 : 0)) + (r2.send_games ? 1 : 0)) + (r2.send_inline ? 1 : 0)) + (r2.embed_links ? 1 : 0)) + (r2.send_polls ? 1 : 0)) + (r2.invite_users ? 1 : 0)) + (r2.change_info ? 1 : 0)) + (r2.pin_messages ? 1 : 0)) + (r2.manage_topics ? 1 : 0)) + (r2.send_photos ? 1 : 0)) + (r2.send_videos ? 1 : 0)) + (r2.send_roundvideos ? 1 : 0)) + (r2.send_voices ? 1 : 0)) + (r2.send_audios ? 1 : 0)) + (r2.send_docs ? 1 : 0)) + (r2.send_plain ? 1 : 0)) + (r2.edit_rank ? 1 : 0)) + (r2.send_reactions ? 1 : 0)) + (r2.manage_linked_peers ? 1 : 0)) + r2.until_date;
    }

    private static TLRPC.Chat getChannelDirectChatInternal(int r1, long r2) {
        return getChannelDirectChatInternal(r1, MessagesController.getInstance(r1).getChat(Long.valueOf(-r2)));
    }

    private static TLRPC.Chat getChannelDirectChatInternal(int r4, TLRPC.Chat r5) {
        if (r5 != null) goto L4;
        return null;
    L4:
        if (r5.linked_monoforum_id != 0) goto L7;
        return null;
    L7:
        if (r5.monoforum == true) goto L9;
        return r5;
    L9:
        return MessagesController.getInstance(r4).getChat(Long.valueOf(r5.linked_monoforum_id));
    }

    public static int getColorId(TLRPC.Chat r4) {
        if (r4 != null) goto L5;
        return 0;
    L5:
        TLRPC.PeerColor r0 = r4.color;
        if ((r0 instanceof TLRPC.TL_peerColor) == false) goto L12;
        if ((r0.flags & 1) == 0) goto L12;
        return r0.color;
    L12:
        return (int) (r4.id % 7);
    }

    public static long getEmojiId(TLRPC.Chat r2) {
        if (r2 == null) goto L9;
        TLRPC.PeerColor r22 = r2.color;
        if ((r22 instanceof TLRPC.TL_peerColor) == true) goto L6;
        return 0;
    L6:
        if ((r22.flags & 2) != 0) goto L8;
        return 0;
    L8:
        return r22.background_emoji_id;
    L9:
        return 0;
    }

    public static long getOnlyProfileEmojiId(TLRPC.Chat r2) {
        if (r2 == null) goto L9;
        TLRPC.PeerColor r22 = r2.profile_color;
        if ((r22 instanceof TLRPC.TL_peerColor) == true) goto L6;
        return 0;
    L6:
        if ((r22.flags & 2) != 0) goto L8;
        return 0;
    L8:
        return r22.background_emoji_id;
    L9:
        return 0;
    }

    public static int getParticipantVolume(TLRPC.GroupCallParticipant r1) {
        if ((r1.flags & 128) != 0) goto L5;
        return 10000;
    L5:
        return r1.volume;
    }

    public static MessagesController.PeerColor getPeerColorForAvatar(int r0, TLRPC.Chat r1) {
        return null;
    }

    public static TLRPC.ChatPhoto getPhoto(TLRPC.Chat r1) {
        if (hasPhoto(r1) == true) goto L5;
        return null;
    L5:
        return r1.photo;
    }

    public static long getProfileCollectibleId(TLRPC.Chat r2) {
        if (r2 == null) goto L7;
        TLRPC.EmojiStatus r22 = r2.emoji_status;
        if ((r22 instanceof TLRPC.TL_emojiStatusCollectible) == true) goto L6;
        return 0;
    L6:
        return ((TLRPC.TL_emojiStatusCollectible) r22).collectible_id;
    L7:
        return 0;
    }

    public static int getProfileColorId(TLRPC.Chat r1) {
        if (r1 != null) goto L5;
        return 0;
    L5:
        TLRPC.PeerColor r12 = r1.profile_color;
        if ((r12 instanceof TLRPC.TL_peerColor) == true) goto L8;
        return -1;
    L8:
        if ((r12.flags & 1) != 0) goto L10;
        return -1;
    L10:
        return r12.color;
    }

    public static long getProfileEmojiId(TLRPC.Chat r2) {
        if (r2 == null) goto L7;
        TLRPC.EmojiStatus r0 = r2.emoji_status;
        if ((r0 instanceof TLRPC.TL_emojiStatusCollectible) == false) goto L7;
        return ((TLRPC.TL_emojiStatusCollectible) r0).pattern_document_id;
    L7:
        if (r2 == null) goto L14;
        TLRPC.PeerColor r22 = r2.profile_color;
        if ((r22 instanceof TLRPC.TL_peerColor) == true) goto L11;
        return 0;
    L11:
        if ((r22.flags & 2) != 0) goto L13;
        return 0;
    L13:
        return r22.background_emoji_id;
    L14:
        return 0;
    }

    public static String getPublicUsername(TLRPC.Chat r1) {
        return getPublicUsername(r1, false);
    }

    public static String getPublicUsername(TLRPC.Chat r4, boolean r5) {
        if (r4 != null) goto L6;
        return null;
    L6:
        if (TextUtils.isEmpty(r4.username) == true) goto L11;
        if (r5 == true) goto L11;
        return r4.username;
    L11:
        if (r4.usernames == null) goto L28;
        int r1 = 0;
    L14:
        if (r1 >= r4.usernames.size()) goto L28;
        TLRPC.TL_username r2 = r4.usernames.get(r1);
        if (r2 == null) goto L26;
        if (r2.active == false) goto L21;
        if (r5 == true) goto L21;
    L23:
        if (TextUtils.isEmpty(r2.username) == true) goto L26;
        return r2.username;
    L21:
        if (r2.editable == true) goto L23;
    L26:
        r1 = r1 + 1;
    L28:
        if (TextUtils.isEmpty(r4.username) == true) goto L36;
        if (r5 == false) goto L36;
        ArrayList<TLRPC.TL_username> r52 = r4.usernames;
        if (r52 == null) goto L35;
        if (r52.size() > 0) goto L36;
    L35:
        return r4.username;
    L36:
        return null;
    }

    public static TL_account.RequirementToContact getRequirementToContact(TLRPC.Chat r5) {
        if (r5 != null) goto L6;
        return null;
    L6:
        if (r5.send_paid_messages_stars == 0) goto L9;
        TL_account.requirementToContactPaidMessages r0 = new TL_account.requirementToContactPaidMessages();
        r0.stars_amount = r5.send_paid_messages_stars;
        return r0;
    L9:
        return null;
    }

    public static String getRestrictedErrorText(TLRPC.Chat r2, int r3) {
        if (r3 != 23) goto L17;
        if (r2 == null) goto L15;
        if (isActionBannedByDefault(r2, r3) == true) goto L15;
        if (AndroidUtilities.isBannedForever(r2.banned_rights) == false) goto L13;
        return LocaleController.formatString("AttachGifRestrictedForever", R.string.AttachGifRestrictedForever, new Object[0]);
    L13:
        return LocaleController.formatString("AttachGifRestricted", R.string.AttachGifRestricted, new Object[]{LocaleController.formatDateForBan(r2.banned_rights.until_date)});
    L15:
        return LocaleController.getString(R.string.GlobalAttachGifRestricted);
    L17:
        if (r3 != 8) goto L31;
        if (r2 == null) goto L29;
        if (isActionBannedByDefault(r2, r3) == true) goto L29;
        if (AndroidUtilities.isBannedForever(r2.banned_rights) == false) goto L27;
        return LocaleController.formatString("AttachStickersRestrictedForever", R.string.AttachStickersRestrictedForever, new Object[0]);
    L27:
        return LocaleController.formatString("AttachStickersRestricted", R.string.AttachStickersRestricted, new Object[]{LocaleController.formatDateForBan(r2.banned_rights.until_date)});
    L29:
        return LocaleController.getString(R.string.GlobalAttachStickersRestricted);
    L31:
        if (r3 != 16) goto L45;
        if (r2 == null) goto L43;
        if (isActionBannedByDefault(r2, r3) == true) goto L43;
        if (AndroidUtilities.isBannedForever(r2.banned_rights) == false) goto L41;
        return LocaleController.formatString("AttachPhotoRestrictedForever", R.string.AttachPhotoRestrictedForever, new Object[0]);
    L41:
        return LocaleController.formatString("AttachPhotoRestricted", R.string.AttachPhotoRestricted, new Object[]{LocaleController.formatDateForBan(r2.banned_rights.until_date)});
    L43:
        return LocaleController.getString(R.string.GlobalAttachPhotoRestricted);
    L45:
        if (r3 != 17) goto L59;
        if (r2 == null) goto L57;
        if (isActionBannedByDefault(r2, r3) == true) goto L57;
        if (AndroidUtilities.isBannedForever(r2.banned_rights) == false) goto L55;
        return LocaleController.formatString("AttachVideoRestrictedForever", R.string.AttachVideoRestrictedForever, new Object[0]);
    L55:
        return LocaleController.formatString("AttachVideoRestricted", R.string.AttachVideoRestricted, new Object[]{LocaleController.formatDateForBan(r2.banned_rights.until_date)});
    L57:
        return LocaleController.getString(R.string.GlobalAttachVideoRestricted);
    L59:
        if (r3 != 19) goto L73;
        if (r2 == null) goto L71;
        if (isActionBannedByDefault(r2, r3) == true) goto L71;
        if (AndroidUtilities.isBannedForever(r2.banned_rights) == false) goto L69;
        return LocaleController.formatString("AttachDocumentsRestrictedForever", R.string.AttachDocumentsRestrictedForever, new Object[0]);
    L69:
        return LocaleController.formatString("AttachDocumentsRestricted", R.string.AttachDocumentsRestricted, new Object[]{LocaleController.formatDateForBan(r2.banned_rights.until_date)});
    L71:
        return LocaleController.getString(R.string.GlobalAttachDocumentsRestricted);
    L73:
        if (r3 != 7) goto L87;
        if (r2 == null) goto L85;
        if (isActionBannedByDefault(r2, r3) == true) goto L85;
        if (AndroidUtilities.isBannedForever(r2.banned_rights) == false) goto L83;
        return LocaleController.formatString("AttachMediaRestrictedForever", R.string.AttachMediaRestrictedForever, new Object[0]);
    L83:
        return LocaleController.formatString("AttachMediaRestricted", R.string.AttachMediaRestricted, new Object[]{LocaleController.formatDateForBan(r2.banned_rights.until_date)});
    L85:
        return LocaleController.getString(R.string.GlobalAttachMediaRestricted);
    L87:
        if (r3 != 18) goto L101;
        if (r2 == null) goto L99;
        if (isActionBannedByDefault(r2, r3) == true) goto L99;
        if (AndroidUtilities.isBannedForever(r2.banned_rights) == false) goto L97;
        return LocaleController.formatString("AttachAudioRestrictedForever", R.string.AttachAudioRestrictedForever, new Object[0]);
    L97:
        return LocaleController.formatString("AttachAudioRestricted", R.string.AttachAudioRestricted, new Object[]{LocaleController.formatDateForBan(r2.banned_rights.until_date)});
    L99:
        return LocaleController.getString(R.string.GlobalAttachAudioRestricted);
    L101:
        if (r3 != 22) goto L115;
        if (r2 == null) goto L113;
        if (isActionBannedByDefault(r2, r3) == true) goto L113;
        if (AndroidUtilities.isBannedForever(r2.banned_rights) == false) goto L111;
        return LocaleController.formatString("AttachPlainRestrictedForever", R.string.AttachPlainRestrictedForever, new Object[0]);
    L111:
        return LocaleController.formatString("AttachPlainRestricted", R.string.AttachPlainRestricted, new Object[]{LocaleController.formatDateForBan(r2.banned_rights.until_date)});
    L113:
        return LocaleController.getString(R.string.GlobalAttachPlainRestricted);
    L115:
        if (r3 != 21) goto L129;
        if (r2 == null) goto L127;
        if (isActionBannedByDefault(r2, r3) == true) goto L127;
        if (AndroidUtilities.isBannedForever(r2.banned_rights) == false) goto L125;
        return LocaleController.formatString("AttachRoundRestrictedForever", R.string.AttachRoundRestrictedForever, new Object[0]);
    L125:
        return LocaleController.formatString("AttachRoundRestricted", R.string.AttachRoundRestricted, new Object[]{LocaleController.formatDateForBan(r2.banned_rights.until_date)});
    L127:
        return LocaleController.getString(R.string.GlobalAttachRoundRestricted);
    L129:
        if (r3 != 20) goto L142;
        if (r2 == null) goto L141;
        if (isActionBannedByDefault(r2, r3) == true) goto L141;
        if (AndroidUtilities.isBannedForever(r2.banned_rights) == false) goto L139;
        return LocaleController.formatString("AttachVoiceRestrictedForever", R.string.AttachVoiceRestrictedForever, new Object[0]);
    L139:
        return LocaleController.formatString("AttachVoiceRestricted", R.string.AttachVoiceRestricted, new Object[]{LocaleController.formatDateForBan(r2.banned_rights.until_date)});
    L141:
        return LocaleController.getString(R.string.GlobalAttachVoiceRestricted);
    L142:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static long getSendAsPeerId(TLRPC.Chat r1, TLRPC.ChatFull r2) {
        return getSendAsPeerId(r1, r2, false);
    }

    public static long getSendAsPeerId(TLRPC.Chat r4, TLRPC.ChatFull r5, boolean r6) {
        if (r4 == null) goto L13;
        if (r5 == null) goto L13;
        TLRPC.Peer r52 = r5.default_send_as;
        if (r52 == null) goto L13;
        long r0 = r52.user_id;
        if (r0 == 0) goto L9;
        return r0;
    L9:
        long r42 = r52.channel_id;
        if (r6 == true) goto L12;
        return r42;
    L12:
        return -r42;
    L13:
        if (r4 == null) goto L22;
        TLRPC.TL_chatAdminRights r53 = r4.admin_rights;
        if (r53 == null) goto L22;
        if (r53.anonymous == false) goto L22;
        long r43 = r4.id;
        if (r6 == true) goto L21;
        return r43;
    L21:
        return -r43;
    L22:
        if (r4 == null) goto L32;
        if (isChannelAndNotMegaGroup(r4) == false) goto L32;
        if (r4.signatures == true) goto L32;
        long r44 = r4.id;
        if (r6 == true) goto L30;
        return r44;
    L30:
        return -r44;
    L32:
        return UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
    }

    public static boolean hasAdminRights(TLRPC.Chat r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.creator == true) goto L9;
        TLRPC.TL_chatAdminRights r12 = r1.admin_rights;
        if (r12 != null) goto L8;
        return false;
    L8:
        if (r12.flags == 0) goto L15;
        return true;
    L15:
        return false;
    L9:
        return true;
    }

    public static boolean hasPhoto(TLRPC.Chat r0) {
        if (r0 == null) goto L9;
        TLRPC.ChatPhoto r02 = r0.photo;
        if (r02 != null) goto L6;
        return false;
    L6:
        if ((r02 instanceof TLRPC.TL_chatPhotoEmpty) == true) goto L12;
        return true;
    L12:
        return false;
    L9:
        return false;
    }

    public static boolean hasPublicLink(TLRPC.Chat r4, String r5) {
        if (r4 != null) goto L6;
        return false;
    L6:
        if (TextUtils.isEmpty(r4.username) == true) goto L10;
        return r4.username.equalsIgnoreCase(r5);
    L10:
        if (r4.usernames == null) goto L25;
        int r1 = 0;
    L13:
        if (r1 >= r4.usernames.size()) goto L25;
        TLRPC.TL_username r2 = r4.usernames.get(r1);
        if (r2 == null) goto L24;
        if (r2.active == false) goto L24;
        if (TextUtils.isEmpty(r2.username) == true) goto L24;
        if (r2.username.equalsIgnoreCase(r5) == false) goto L24;
        return true;
    L24:
        r1 = r1 + 1;
    L25:
        return false;
    }

    public static boolean hasStories(TLRPC.Chat r3) {
        if (r3 != null) goto L4;
        return false;
    L4:
        if (MessagesController.getInstance(UserConfig.selectedAccount).getStoriesController().hasStories(-r3.id) == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isActionBanned(TLRPC.Chat r1, int r2) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (getBannedRight(r1.banned_rights, r2) == false) goto L6;
        return true;
    L6:
        if (getBannedRight(r1.default_banned_rights, r2) == false) goto L12;
        return true;
    L12:
        return false;
    }

    public static boolean isActionBannedByDefault(TLRPC.Chat r1, int r2) {
        if (r1 != null) goto L6;
        return false;
    L6:
        if (getBannedRight(r1.banned_rights, r2) == false) goto L12;
        if (getBannedRight(r1.default_banned_rights, r2) == false) goto L12;
        return true;
    L12:
        return getBannedRight(r1.default_banned_rights, r2);
    }

    private static boolean isAdminAction(int r2) {
        if (r2 == 0) goto L21;
        if (r2 == 1) goto L21;
        if (r2 == 2) goto L21;
        if (r2 == 3) goto L21;
        if (r2 == 4) goto L21;
        if (r2 == 5) goto L21;
        if (r2 == 12) goto L21;
        if (r2 == 13) goto L21;
        if (r2 == 15) goto L21;
        return false;
    L21:
        return true;
    }

    private static boolean isBannableAction(int r2) {
        if (r2 == 0) goto L15;
        if (r2 == 1) goto L15;
        if (r2 == 3) goto L15;
        if (r2 == 26) goto L15;
        if (r2 == 27) goto L15;
        switch(r2) {
            case 6: goto L15;
            case 7: goto L15;
            case 8: goto L15;
            case 9: goto L15;
            case 10: goto L15;
            case 11: goto L15;
            default: goto L12;
        };
    L12:
        switch(r2) {
            case 15: goto L15;
            case 16: goto L15;
            case 17: goto L15;
            case 18: goto L15;
            case 19: goto L15;
            case 20: goto L15;
            case 21: goto L15;
            case 22: goto L15;
            default: goto L13;
        };
    L13:
        return false;
    L15:
        return true;
    }

    public static boolean isBoostSupported(TLRPC.Chat r1) {
        if (isChannelAndNotMegaGroup(r1) == true) goto L7;
        if (isMegagroup(r1) == true) goto L7;
        return false;
    L7:
        if (isMonoForum(r1) == true) goto L12;
        return true;
    L12:
        return false;
    }

    public static boolean isBoosted(TLRPC.ChatFull r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (r0.boosts_applied <= 0) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isCanWriteToChannel(long r0, int r2) {
        TLRPC.Chat r02 = MessagesController.getInstance(r2).getChat(Long.valueOf(r0));
        if (canSendMessages(r02) == false) goto L5;
        return true;
    L5:
        if (r02.megagroup == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isChannel(long r0, int r2) {
        return isChannel(MessagesController.getInstance(r2).getChat(Long.valueOf(r0)));
    }

    public static boolean isChannel(TLRPC.Chat r1) {
        if ((r1 instanceof TLRPC.TL_channel) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof TLRPC.TL_channelForbidden) == false) goto L7;
        return true;
    L7:
        if (isCommunity(r1) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public static boolean isChannelAndNotMegaGroup(long r0, int r2) {
        return isChannelAndNotMegaGroup(MessagesController.getInstance(r2).getChat(Long.valueOf(r0)));
    }

    public static boolean isChannelAndNotMegaGroup(TLRPC.Chat r1) {
        if (isChannel(r1) == true) goto L5;
        return false;
    L5:
        if (isMegagroup(r1) == true) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isChannelOrGiga(TLRPC.Chat r1) {
        if (isChannel(r1) == true) goto L5;
        return false;
    L5:
        if (r1.megagroup == true) goto L7;
        return true;
    L7:
        if (r1.gigagroup == false) goto L13;
        return true;
    L13:
        return false;
    }

    public static boolean isChatCollapsedInCommunity(int r5, TLRPC.Chat r6) {
        if (r6 != null) goto L5;
    L13:
        return false;
    L5:
        if (r6.linked_community_id == 0) goto L13;
        TLRPC.Chat r52 = MessagesController.getInstance(r5).getChat(Long.valueOf(r6.linked_community_id));
        if (r52 == null) goto L13;
        if (r52.collapsed_in_dialogs == false) goto L13;
        return true;
    }

    public static boolean isChatCollapsedInCommunity(LongSparseArray<TLRPC.Chat> r5, TLRPC.Chat r6) {
        if (r6 == null) goto L14;
        long r1 = r6.linked_community_id;
        if (r1 == 0) goto L14;
        if (r5 == null) goto L14;
        TLRPC.Chat r52 = r5.get(r1);
        if (r52 == null) goto L14;
        if (r52.collapsed_in_dialogs == false) goto L14;
        return true;
    L14:
        return false;
    }

    public static boolean isCollapsedInCommunity(int r2, long r3) {
        if (r3 <= 0) goto L7;
        return isUserCollapsedInCommunity(r2, MessagesController.getInstance(r2).getUser(Long.valueOf(r3)));
    L7:
        return isChatCollapsedInCommunity(r2, MessagesController.getInstance(r2).getChat(Long.valueOf(-r3)));
    }

    public static boolean isCommunity(int r2, long r3) {
        if (r3 < 0) goto L5;
        return false;
    L5:
        if (isCommunity(MessagesController.getInstance(r2).getChat(Long.valueOf(-r3))) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isCommunity(long r0, int r2) {
        TLRPC.Chat r02 = MessagesController.getInstance(r2).getChat(Long.valueOf(r0));
        if ((r02 instanceof TLRPC.TL_community) == false) goto L5;
        return true;
    L5:
        if ((r02 instanceof TLRPC.TL_communityForbidden) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isCommunity(TLRPC.Chat r1) {
        if ((r1 instanceof TLRPC.TL_community) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof TLRPC.TL_communityForbidden) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isCommunityPeerHidden(TL_communities.CommunityPeer r2) {
        if (r2 != null) goto L4;
        return false;
    L4:
        if (BitwiseUtils.hasFlag(r2.flags, 1) == true) goto L6;
        return false;
    L6:
        if (r2.visible == true) goto L11;
        return true;
    L11:
        return false;
    }

    public static boolean isCreator(TLRPC.Chat r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (r0.creator == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isDiscussionGroup(int r1, long r2) {
        MessagesController r12 = MessagesController.getInstance(r1);
        return isDiscussionGroup(r12.getChat(Long.valueOf(r2)), r12.getChatFull(r2));
    }

    public static boolean isDiscussionGroup(TLRPC.Chat r2, TLRPC.ChatFull r3) {
        if (isMegagroup(r2) == false) goto L9;
        if (r3 != null) goto L6;
        return false;
    L6:
        if (r3.linked_chat_id == 0) goto L12;
        return true;
    L12:
        return false;
    L9:
        return false;
    }

    private static boolean isForbidden(TLRPC.Chat r1) {
        if ((r1 instanceof TLRPC.TL_chatForbidden) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof TLRPC.TL_channelForbidden) == false) goto L7;
        return true;
    L7:
        if ((r1 instanceof TLRPC.TL_communityForbidden) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public static boolean isForum(int r0, long r1) {
        TLRPC.Chat r02 = MessagesController.getInstance(r0).getChat(Long.valueOf(-r1));
        if (r02 != null) goto L5;
        return false;
    L5:
        return r02.forum;
    }

    public static boolean isForum(TLRPC.Chat r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (r0.forum == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isHiddenInCommunity(int r2, long r3) {
        if (r3 <= 0) goto L7;
        return isHiddenInCommunity(r2, MessagesController.getInstance(r2).getUser(Long.valueOf(r3)));
    L7:
        return isHiddenInCommunity(r2, MessagesController.getInstance(r2).getChat(Long.valueOf(-r3)));
    }

    private static boolean isHiddenInCommunity(int r4, long r5, long r7) {
        if (r5 != 0) goto L5;
    L20:
        return false;
    L5:
        if (r7 == 0) goto L20;
        TLRPC.ChatFull r42 = MessagesController.getInstance(r4).getChatFull(r5);
        if (r42 == null) goto L20;
        ArrayList<TL_communities.CommunityPeer> r43 = r42.linked_peers;
        if (r43 == null) goto L20;
        int r52 = r43.size();
        int r6 = 0;
    L13:
        if (r6 >= r52) goto L20;
        TL_communities.CommunityPeer r0 = r43.get(r6);
        r6 = r6 + 1;
        TL_communities.CommunityPeer r02 = r0;
        if (DialogObject.getPeerDialogId(r02.peer) != r7) goto L13;
        if (isCommunityPeerHidden(r02) == false) goto L13;
        return true;
    }

    public static boolean isHiddenInCommunity(int r4, TLRPC.Chat r5) {
        if (r5 != null) goto L4;
        return false;
    L4:
        if (isHiddenInCommunity(r4, r5.linked_community_id, -r5.id) == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isHiddenInCommunity(int r4, TLRPC.User r5) {
        if (r5 != null) goto L4;
        return false;
    L4:
        if (isHiddenInCommunity(r4, r5.linked_community_id, r5.id) == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isIgnoredChatRestrictionsForBoosters(TLRPC.Chat r3) {
        if (r3 != null) goto L4;
        return false;
    L4:
        return isIgnoredChatRestrictionsForBoosters(MessagesController.getInstance(UserConfig.selectedAccount).getChatFull(r3.id));
    }

    public static boolean isIgnoredChatRestrictionsForBoosters(TLRPC.ChatFull r1) {
        if (r1 == null) goto L9;
        int r0 = r1.boosts_unrestrict;
        if (r0 > 0) goto L6;
        return false;
    L6:
        if ((r1.boosts_applied - r0) < 0) goto L12;
        return true;
    L12:
        return false;
    L9:
        return false;
    }

    public static boolean isInChat(TLRPC.Chat r2) {
        if (r2 != null) goto L5;
    L18:
        return false;
    L5:
        if ((r2 instanceof TLRPC.TL_chatEmpty) == true) goto L18;
        if (isForbidden(r2) == true) goto L18;
        if (r2.left == true) goto L18;
        if (r2.kicked == true) goto L18;
        if (r2.deactivated == true) goto L18;
        return true;
    }

    public static boolean isKickedFromChat(TLRPC.Chat r1) {
        if (r1 != null) goto L4;
        return true;
    L4:
        if ((r1 instanceof TLRPC.TL_chatEmpty) == false) goto L6;
        return true;
    L6:
        if (isForbidden(r1) == false) goto L8;
        return true;
    L8:
        if (r1.kicked == false) goto L10;
        return true;
    L10:
        if (r1.deactivated == true) goto L24;
        TLRPC.TL_chatBannedRights r12 = r1.banned_rights;
        if (r12 != null) goto L14;
        return false;
    L14:
        if (r12.view_messages == true) goto L25;
        return false;
    L25:
        return true;
    L24:
        return true;
    }

    public static boolean isLeftFromChat(TLRPC.Chat r1) {
        if (r1 != null) goto L4;
        return true;
    L4:
        if ((r1 instanceof TLRPC.TL_chatEmpty) == false) goto L6;
        return true;
    L6:
        if (isForbidden(r1) == false) goto L8;
        return true;
    L8:
        if (r1.left == false) goto L10;
        return true;
    L10:
        if (r1.deactivated == true) goto L19;
        return false;
    L19:
        return true;
    }

    public static boolean isMegagroup(int r0, long r1) {
        TLRPC.Chat r02 = MessagesController.getInstance(r0).getChat(Long.valueOf(r1));
        if (isChannel(r02) == true) goto L5;
        return false;
    L5:
        if (r02.megagroup == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isMegagroup(TLRPC.Chat r1) {
        if (isChannel(r1) == true) goto L5;
        return false;
    L5:
        if (r1.megagroup == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isMonoForum(int r0, long r1) {
        TLRPC.Chat r02 = MessagesController.getInstance(r0).getChat(Long.valueOf(-r1));
        if (r02 != null) goto L5;
        return false;
    L5:
        return r02.monoforum;
    }

    public static boolean isMonoForum(TLRPC.Chat r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (r0.monoforum == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isMyTopic(int r1, long r2, long r4) {
        return isMyTopic(r1, MessagesController.getInstance(r1).getTopicsController().findTopic(r2, r4));
    }

    public static boolean isMyTopic(int r2, TLRPC.Chat r3, long r4) {
        if (r3 != null) goto L4;
        return false;
    L4:
        if (r3.forum == true) goto L6;
        return false;
    L6:
        if (isMyTopic(r2, r3.id, r4) == false) goto L12;
        return true;
    L12:
        return false;
    }

    public static boolean isMyTopic(int r2, TLRPC.TL_forumTopic r3) {
        if (r3 != null) goto L4;
        return false;
    L4:
        if (r3.my == true) goto L9;
        TLRPC.Peer r32 = r3.from_id;
        if ((r32 instanceof TLRPC.TL_peerUser) == true) goto L8;
        return false;
    L8:
        if (r32.user_id != UserConfig.getInstance(r2).clientUserId) goto L15;
        return true;
    L15:
        return false;
    L9:
        return true;
    }

    public static boolean isNotInChat(TLRPC.Chat r1) {
        if (r1 != null) goto L4;
        return true;
    L4:
        if ((r1 instanceof TLRPC.TL_chatEmpty) == false) goto L6;
        return true;
    L6:
        if (isForbidden(r1) == false) goto L8;
        return true;
    L8:
        if (r1.left == false) goto L10;
        return true;
    L10:
        if (r1.kicked == false) goto L12;
        return true;
    L12:
        if (r1.deactivated == true) goto L22;
        return false;
    L22:
        return true;
    }

    public static boolean isPossibleRemoveChatRestrictionsByBoosts(TLRPC.Chat r3) {
        if (r3 != null) goto L4;
        return false;
    L4:
        return isPossibleRemoveChatRestrictionsByBoosts(MessagesController.getInstance(UserConfig.selectedAccount).getChatFull(r3.id));
    }

    public static boolean isPossibleRemoveChatRestrictionsByBoosts(TLRPC.ChatFull r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (r0.boosts_unrestrict <= 0) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isPublic(TLRPC.Chat r0) {
        return !TextUtils.isEmpty(getPublicUsername(r0));
    }

    public static boolean isUserCollapsedInCommunity(int r5, TLRPC.User r6) {
        if (r6 != null) goto L5;
    L13:
        return false;
    L5:
        if (r6.linked_community_id == 0) goto L13;
        TLRPC.Chat r52 = MessagesController.getInstance(r5).getChat(Long.valueOf(r6.linked_community_id));
        if (r52 == null) goto L13;
        if (r52.collapsed_in_dialogs == false) goto L13;
        return true;
    }

    public static boolean isUserCollapsedInCommunity(LongSparseArray<TLRPC.Chat> r5, TLRPC.User r6) {
        if (r6 == null) goto L14;
        long r1 = r6.linked_community_id;
        if (r1 == 0) goto L14;
        if (r5 == null) goto L14;
        TLRPC.Chat r52 = r5.get(r1);
        if (r52 == null) goto L14;
        if (r52.collapsed_in_dialogs == false) goto L14;
        return true;
    L14:
        return false;
    }

    public static boolean reactionIsAvailable(TLRPC.ChatFull r4, String r5) {
        TLRPC.ChatReactions r42 = r4.available_reactions;
        if ((r42 instanceof TLRPC.TL_chatReactionsAll) == false) goto L6;
        return true;
    L6:
        if ((r42 instanceof TLRPC.TL_chatReactionsSome) == false) goto L16;
        TLRPC.TL_chatReactionsSome r43 = (TLRPC.TL_chatReactionsSome) r42;
        int r0 = 0;
    L9:
        if (r0 >= r43.reactions.size()) goto L16;
        if ((r43.reactions.get(r0) instanceof TLRPC.TL_reactionEmoji) == false) goto L15;
        if (TextUtils.equals(((TLRPC.TL_reactionEmoji) r43.reactions.get(r0)).emoticon, r5) == false) goto L15;
        return true;
    L15:
        r0 = r0 + 1;
    L16:
        return false;
    }

    public static boolean shouldSendAnonymously(TLRPC.Chat r0) {
        if (r0 == null) goto L9;
        TLRPC.TL_chatAdminRights r02 = r0.admin_rights;
        if (r02 != null) goto L6;
        return false;
    L6:
        if (r02.anonymous == false) goto L12;
        return true;
    L12:
        return false;
    L9:
        return false;
    }
}
