package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.ArrayList;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Stories.StoriesStorage;

/* loaded from: classes5.dex */
public class ChatMessagesMetadataController {
    final ChatActivity chatActivity;
    ArrayList<Integer> extendedMediaRequests;
    private final LongSparseArray<ArrayList<MessageObject>> extendedMediaToCheck;
    ArrayList<Integer> reactionsRequests;
    private final ArrayList<MessageObject> reactionsToCheck;
    private final ArrayList<MessageObject> storiesToCheck;

    /* renamed from: $r8$lambda$K9zkA8oHfKmIf2jT-AHVOsFH-qo, reason: not valid java name */
    public static /* synthetic */ void m3916$r8$lambda$K9zkA8oHfKmIf2jTAHVOsFHqo(ChatMessagesMetadataController r0, ArrayList r1) {
        r0.lambda$loadStoriesForMessages$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$Ssc5JVcoGzdin8jM_XyFrZGJjiI(ChatMessagesMetadataController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadExtendedMediaForMessages$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ikVNc0FvbMcnhIs8OFgqQddkooI(ChatMessagesMetadataController r0, int r1, MessageObject r2, long r3, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$loadStoriesForMessages$2(r1, r2, r3, r5, r6);
    }

    /* renamed from: $r8$lambda$vUZqxbLZa7ONouz-BcKclDWmL_k, reason: not valid java name */
    public static /* synthetic */ void m3917$r8$lambda$vUZqxbLZa7ONouzBcKclDWmL_k(ChatMessagesMetadataController r0, MessageObject r1, long r2, TL_stories.StoryItem r4) {
        r0.lambda$loadStoriesForMessages$1(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$z_eBp5efoNesVEzdbQg7Pg4erJg(ChatMessagesMetadataController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadReactionsForMessages$3(r1, r2);
    }

    public ChatMessagesMetadataController(ChatActivity r3) {
        this.reactionsToCheck = new ArrayList(10);
        this.extendedMediaToCheck = new LongSparseArray();
        this.storiesToCheck = new ArrayList(10);
        this.reactionsRequests = new ArrayList();
        this.extendedMediaRequests = new ArrayList();
        this.chatActivity = r3;
    }

    private /* synthetic */ void lambda$loadExtendedMediaForMessages$4(TLObject r1, TLRPC.TL_error r2) {
        if (r2 != null) goto L5;
        this.chatActivity.getMessagesController().processUpdates((TLRPC.Updates) r1, false);
        return;
    }

    private /* synthetic */ void lambda$loadReactionsForMessages$3(TLObject r3, TLRPC.TL_error r4) {
        if (r4 != null) goto L15;
        TLRPC.Updates r32 = (TLRPC.Updates) r3;
        int r0 = 0;
    L5:
        if (r0 >= r32.updates.size()) goto L10;
        if ((r32.updates.get(r0) instanceof TL_update.TL_updateMessageReactions) == false) goto L9;
        ((TL_update.TL_updateMessageReactions) r32.updates.get(r0)).updateUnreadState = false;
    L9:
        r0 = r0 + 1;
        goto L5
    L10:
        this.chatActivity.getMessagesController().processUpdates(r32, false);
        return;
    }

    private /* synthetic */ void lambda$loadStoriesForMessages$0(ArrayList r1) {
        this.chatActivity.getMessagesController().getStoriesController().getStoriesStorage().updateMessagesWithStories(r1);
    }

    private /* synthetic */ void lambda$loadStoriesForMessages$1(MessageObject r3, long r4, TL_stories.StoryItem r6) {
        boolean r0 = r3.isExpiredStory();
        StoriesStorage.applyStory(this.chatActivity.getCurrentAccount(), r4, r3, r6);
        ArrayList<MessageObject> r42 = new ArrayList();
        r3.forceUpdate = true;
        r42.add(r3);
        this.chatActivity.getMessagesStorage().getStorageQueue().postRunnable(new ChatMessagesMetadataController$$ExternalSyntheticLambda4(this, r42));
        if (r0 == false) goto L5;
    L10:
        this.chatActivity.updateMessages(r42, false);
        return;
    L5:
        if (r3.isExpiredStory() == false) goto L10;
        if (r3.type != 24) goto L10;
        this.chatActivity.updateMessages(r42, true);
    }

    private /* synthetic */ void lambda$loadStoriesForMessages$2(int r7, MessageObject r8, long r9, TLObject r11, TLRPC.TL_error r12) {
        if (r11 == null) goto L11;
        TL_stories.TL_stories_stories r112 = (TL_stories.TL_stories_stories) r11;
        if (r112.stories.size() <= 0) goto L6;
        TL_stories.StoryItem r113 = r112.stories.get(0);
    L7:
        if (r113 != null) goto L9;
        r113 = new TL_stories.TL_storyItemDeleted();
    L9:
        TL_stories.StoryItem r5 = r113;
        r5.lastUpdateTime = System.currentTimeMillis();
        r5.id = r7;
        AndroidUtilities.runOnUIThread(new ChatMessagesMetadataController$$ExternalSyntheticLambda2(this, r8, r9, r5));
        return;
    L6:
        r113 = null;
        goto L7
    }

    private void loadStoriesForMessages(long r9, ArrayList<MessageObject> r11) {
        if (r11.isEmpty() == true) goto L28;
        int r10 = 0;
    L7:
        if (r10 >= r11.size()) goto L20;
        TL_stories.TL_stories_getStoriesByID r0 = new TL_stories.TL_stories_getStoriesByID();
        MessageObject r5 = r11.get(r10);
        new TL_stories.TL_storyItem();
        int r1 = r5.type;
        if (r1 != 23) goto L11;
    L17:
        TLRPC.MessageMedia r12 = r5.messageOwner.media;
        TL_stories.StoryItem r2 = r12.storyItem;
        r2.dialogId = r12.user_id;
        TL_stories.StoryItem r13 = r2;
    L18:
        long r6 = r13.dialogId;
        r0.peer = this.chatActivity.getMessagesController().getInputPeer(r6);
        r0.id.add(Integer.valueOf(r13.id));
        ChatMessagesMetadataController r3 = this;
        r3.extendedMediaRequests.add(Integer.valueOf(this.chatActivity.getConnectionsManager().sendRequest(r0, new ChatMessagesMetadataController$$ExternalSyntheticLambda3(r3, r13.id, r5, r6))));
    L19:
        r10 = r10 + 1;
        ChatMessagesMetadataController this = r3;
        goto L7
    L11:
        if (r1 == 24) goto L17;
        TLRPC.Message r14 = r5.messageOwner;
        TLRPC.MessageReplyHeader r22 = r14.reply_to;
        if (r22 == null) goto L16;
        r13 = r14.replyStory;
        r13.dialogId = DialogObject.getPeerDialogId(r22.peer);
        goto L18
    L16:
        r3 = this;
        goto L19
    L20:
        ChatMessagesMetadataController r32 = this;
        if (r32.extendedMediaRequests.size() <= 10) goto L27;
        r32.chatActivity.getConnectionsManager().cancelRequest(r32.extendedMediaRequests.remove(0).intValue(), false);
        return;
    L27:
        return;
    }

    public void checkMessages(ChatActivity.ChatActivityAdapter r9, int r10, int r11, long r12) {
        ArrayList<MessageObject> r0 = r9.getMessages();
        if (this.chatActivity.isInScheduleMode() == true) goto L72;
        if (r10 < 0) goto L73;
        if (r11 < 0) goto L74;
        boolean r1 = this.chatActivity.isFeedSearch();
        int r92 = r9.messagesStartRow;
        int r112 = (r11 - r92) - 10;
        int r102 = (r10 - r92) + 10;
        int r93 = 0;
        if (r112 >= 0) goto L10;
        r112 = 0;
    L10:
        if (r102 <= r0.size()) goto L12;
        r102 = r0.size();
    L12:
        this.reactionsToCheck.clear();
        this.extendedMediaToCheck.clear();
        this.storiesToCheck.clear();
    L13:
        if (r112 >= r102) goto L59;
        MessageObject r2 = r0.get(r112);
        if (r1 == true) goto L28;
        if (this.chatActivity.getThreadMessage() == r2) goto L28;
        if (r2.getId() <= 0) goto L28;
        if (r2.messageOwner.action == null) goto L25;
        if (r2.canSetReaction() == false) goto L28;
    L25:
        if ((r12 - r2.reactionsLastCheckTime) <= 15000) goto L28;
        r2.reactionsLastCheckTime = r12;
        this.reactionsToCheck.add(r2);
    L28:
        if (this.chatActivity.getThreadMessage() != r2) goto L30;
    L41:
        int r3 = r2.type;
        if (r3 == 23) goto L46;
        if (r3 == 24) goto L46;
        if (r2.messageOwner.replyStory != null) goto L46;
    L58:
        r112 = r112 + 1;
    L46:
        if (r3 == 23) goto L50;
        if (r3 == 24) goto L50;
        TL_stories.StoryItem r32 = r2.messageOwner.replyStory;
    L51:
        if (r32 == null) goto L58;
        if ((r32 instanceof TL_stories.TL_storyItemDeleted) == true) goto L58;
        if ((r12 - r32.lastUpdateTime) <= 300000) goto L58;
        r32.lastUpdateTime = r12;
        this.storiesToCheck.add(r2);
    L50:
        r32 = r2.messageOwner.media.storyItem;
        goto L51
    L30:
        if (r2.getId() <= 0) goto L41;
        if (r2.hasExtendedMediaPreview() == true) goto L36;
        if (r2.hasPaidMediaPreview() == false) goto L41;
    L36:
        if ((r12 - r2.extendedMediaLastCheckTime) <= NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L41;
        r2.extendedMediaLastCheckTime = r12;
        ArrayList<MessageObject> r33 = this.extendedMediaToCheck.get(r2.getDialogId());
        if (r33 != null) goto L40;
        r33 = new ArrayList();
        this.extendedMediaToCheck.put(r2.getDialogId(), r33);
    L40:
        r33.add(r2);
        goto L41
    L59:
        loadReactionsForMessages(this.chatActivity.getDialogId(), this.reactionsToCheck);
    L61:
        if (r93 >= this.extendedMediaToCheck.size()) goto L63;
        loadExtendedMediaForMessages(this.extendedMediaToCheck.keyAt(r93), this.extendedMediaToCheck.valueAt(r93));
        r93 = r93 + 1;
        goto L61
    L63:
        loadStoriesForMessages(this.chatActivity.getDialogId(), this.storiesToCheck);
        return;
    L74:
        return;
    L73:
        return;
    }

    public void loadExtendedMediaForMessages(long r4, ArrayList<MessageObject> r6) {
        if (r6.isEmpty() == true) goto L15;
        TLRPC.TL_messages_getExtendedMedia r0 = new TLRPC.TL_messages_getExtendedMedia();
        r0.peer = this.chatActivity.getMessagesController().getInputPeer(r4);
        int r5 = 0;
    L7:
        if (r5 >= r6.size()) goto L9;
        r0.id.add(Integer.valueOf(r6.get(r5).getId()));
        r5 = r5 + 1;
        goto L7
    L9:
        this.extendedMediaRequests.add(Integer.valueOf(this.chatActivity.getConnectionsManager().sendRequest(r0, new ChatMessagesMetadataController$$ExternalSyntheticLambda0(this))));
        if (this.extendedMediaRequests.size() <= 10) goto L14;
        this.chatActivity.getConnectionsManager().cancelRequest(this.extendedMediaRequests.remove(0).intValue(), false);
        return;
    L14:
        return;
    }

    public void loadReactionsForMessages(long r4, ArrayList<MessageObject> r6) {
        if (r6.isEmpty() == true) goto L15;
        TLRPC.TL_messages_getMessagesReactions r0 = new TLRPC.TL_messages_getMessagesReactions();
        r0.peer = this.chatActivity.getMessagesController().getInputPeer(r4);
        int r5 = 0;
    L7:
        if (r5 >= r6.size()) goto L9;
        r0.id.add(Integer.valueOf(r6.get(r5).getId()));
        r5 = r5 + 1;
        goto L7
    L9:
        this.reactionsRequests.add(Integer.valueOf(this.chatActivity.getConnectionsManager().sendRequest(r0, new ChatMessagesMetadataController$$ExternalSyntheticLambda1(this))));
        if (this.reactionsRequests.size() <= 5) goto L14;
        this.chatActivity.getConnectionsManager().cancelRequest(this.reactionsRequests.remove(0).intValue(), true);
        return;
    L14:
        return;
    }

    public void onFragmentDestroy() {
        int r1 = 0;
    L4:
        if (r1 >= this.reactionsRequests.size()) goto L6;
        this.chatActivity.getConnectionsManager().cancelRequest(this.reactionsRequests.get(r1).intValue(), false);
        r1 = r1 + 1;
        goto L4
    L6:
        this.reactionsRequests.clear();
        int r12 = 0;
    L8:
        if (r12 >= this.extendedMediaRequests.size()) goto L10;
        this.chatActivity.getConnectionsManager().cancelRequest(this.extendedMediaRequests.get(r12).intValue(), false);
        r12 = r12 + 1;
        goto L8
    L10:
        this.extendedMediaRequests.clear();
    }
}
