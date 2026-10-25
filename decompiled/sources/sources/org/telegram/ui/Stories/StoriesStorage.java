package org.telegram.ui.Stories;

import android.text.TextUtils;
import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.SQLite.SQLiteException;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.Timer;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.support.LongSparseIntArray;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes3.dex */
public class StoriesStorage {
    int currentAccount;
    MessagesStorage storage;

    /* renamed from: $r8$lambda$-jWyKPCElLwW7zLz8y1KmYBaQXk, reason: not valid java name */
    public static /* synthetic */ void m19722$r8$lambda$jWyKPCElLwW7zLz8y1KmYBaQXk(StoriesStorage r0, TL_stories.PeerStories r1) {
        r0.lambda$updateStories$10(r1);
    }

    /* renamed from: $r8$lambda$Dx9-rGYaAIOVnoTM2M9jwUcJ_uU, reason: not valid java name */
    public static /* synthetic */ void m19723$r8$lambda$Dx9rGYaAIOVnoTM2M9jwUcJ_uU(StoriesStorage r0, TL_stories.PeerStories r1) {
        r0.lambda$putPeerStories$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$E1bn7TjcWyJlychiUjMa9ii0hnI(Utilities.Callback r1) {
        r1.run(null);
    }

    public static /* synthetic */ void $r8$lambda$EsUKlyVoTOXDNQm2IHilTTVReHk(StoriesStorage r0, Utilities.Callback r1) {
        r0.lambda$getMaxReadIds$16(r1);
    }

    /* renamed from: $r8$lambda$FPp50-5R03c8eTGSl13t6hmdIVk, reason: not valid java name */
    public static /* synthetic */ void m19724$r8$lambda$FPp505R03c8eTGSl13t6hmdIVk(StoriesStorage r0, Timer.Task r1, ArrayList r2, long r3, boolean r5, Timer r6, int[] r7, Runnable r8, TLObject r9, TLRPC.TL_error r10) {
        r0.lambda$fillMessagesWithStories$14(r1, r2, r3, r5, r6, r7, r8, r9, r10);
    }

    /* renamed from: $r8$lambda$IyzXtwRrm3EtnB0aCA1OLF-tHvM, reason: not valid java name */
    public static /* synthetic */ void m19725$r8$lambda$IyzXtwRrm3EtnB0aCA1OLFtHvM(StoriesStorage r0, long r1, int r3) {
        r0.lambda$deleteStory$11(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$Nbwyn0IjLAJPT0zX3tCmEyXD6q4(Utilities.Callback r0, TL_stories.TL_stories_allStories r1) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$TuEX9in9jKiW9ueolr1_vtzkXZQ(StoriesStorage r0, long r1, TL_stories.StoryItem r3) {
        r0.lambda$updateStoryItem$7(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$WgHJC4rPtZtXzaplJRgaQzowPHY(StoriesStorage r0, long r1) {
        r0.lambda$deleteAllUserStories$18(r1);
    }

    /* renamed from: $r8$lambda$_KfHaC2KCtdbb-45utXf7JnwXjU, reason: not valid java name */
    public static /* synthetic */ void m19726$r8$lambda$_KfHaC2KCtdbb45utXf7JnwXjU(Utilities.Callback r0, LongSparseIntArray r1) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$aMkk4q0qCu5MWXc85mcrGRoCuoc(StoriesStorage r0, long r1, int r3) {
        r0.lambda$updateMaxReadId$8(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$cUPr9_tlcpK1fR2UQvjRvamVkkc(StoriesStorage r0, TL_stories.TL_updateStory r1) {
        r0.lambda$processUpdate$9(r1);
    }

    /* renamed from: $r8$lambda$lp_VI3GV20BS2s9AhgJ-VtA5zd0, reason: not valid java name */
    public static /* synthetic */ void m19727$r8$lambda$lp_VI3GV20BS2s9AhgJVtA5zd0(StoriesStorage r0, ArrayList r1) {
        r0.lambda$fillMessagesWithStories$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$n17FZn4XWrtxjwxUZAmVg4HTbvs(StoriesStorage r0, ArrayList r1, long r2) {
        r0.lambda$deleteStories$12(r1, r2);
    }

    public static /* synthetic */ int $r8$lambda$oObnqwHhX94CgPGTFQagIu0JO2g(TL_stories.PeerStories r1) {
        return -r1.stories.get(r1.size() - 1).date;
    }

    public static /* synthetic */ void $r8$lambda$ojAnorhxAaQFlkiGqSJMWJBcZaQ(StoriesStorage r0, Utilities.Callback r1) {
        r0.lambda$getAllStories$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$tqlIMd7PZK_bjhkSlN7Of8r6yQA(StoriesStorage r0, ArrayList r1, boolean r2, boolean r3, Runnable r4) {
        r0.lambda$saveAllStories$4(r1, r2, r3, r4);
    }

    public StoriesStorage(int r1) {
        this.currentAccount = r1;
        this.storage = MessagesStorage.getInstance(r1);
    }

    public static void applyStory(int r5, long r6, MessageObject r8, TL_stories.StoryItem r9) {
        TLRPC.Message r0 = r8.messageOwner;
        TLRPC.MessageReplyHeader r1 = r0.reply_to;
        if ((r1 instanceof TLRPC.TL_messageReplyStoryHeader) == true) goto L5;
    L7:
        int r02 = r8.type;
        if (r02 != 23) goto L10;
    L11:
        MessageMediaStoryFull r03 = new MessageMediaStoryFull();
        r03.user_id = DialogObject.getPeerDialogId(r8.messageOwner.media.peer);
        TLRPC.MessageMedia r12 = r8.messageOwner.media;
        r03.peer = r12.peer;
        r03.id = r12.id;
        r03.storyItem = checkExpiredStateLocal(r5, r6, r9);
        TLRPC.Message r13 = r8.messageOwner;
        r03.via_mention = r13.media.via_mention;
        r13.media = r03;
    L12:
        TLRPC.MessageMedia r04 = r8.messageOwner.media;
        if (r04 == null) goto L27;
        TLRPC.WebPage r05 = r04.webpage;
        if (r05 != null) goto L17;
        return;
    L17:
        if (r05.attributes == null) goto L33;
        int r06 = 0;
    L20:
        if (r06 >= r8.messageOwner.media.webpage.attributes.size()) goto L34;
        TLRPC.WebPageAttribute r14 = r8.messageOwner.media.webpage.attributes.get(r06);
        if ((r14 instanceof TLRPC.TL_webPageAttributeStory) == false) goto L26;
        TLRPC.TL_webPageAttributeStory r2 = (TLRPC.TL_webPageAttributeStory) r14;
        if (r2.id != r9.id) goto L26;
        r14.flags |= 1;
        r2.storyItem = checkExpiredStateLocal(r5, r6, r9);
    L26:
        r06 = r06 + 1;
        goto L20
    L34:
        return;
    L33:
        return;
    L27:
        return;
    L10:
        if (r02 != 24) goto L12;
    L5:
        if (r1.story_id != r9.id) goto L7;
        r0.replyStory = checkExpiredStateLocal(r5, r6, r9);
        goto L7
    }

    public static TL_stories.StoryItem checkExpiredStateLocal(int r4, long r5, TL_stories.StoryItem r7) {
        if ((r7 instanceof TL_stories.TL_storyItemDeleted) == false) goto L5;
        return r7;
    L5:
        int r0 = ConnectionsManager.getInstance(r4).getCurrentTime();
        int r1 = r7.expire_date;
        boolean r2 = false;
        if (r1 <= 0) goto L10;
        if (r0 <= r1) goto L13;
    L8:
        r2 = true;
    L13:
        if (r7.pinned == true) goto L21;
        if (r2 == false) goto L21;
        if (r5 == 0) goto L21;
        if (r5 == UserConfig.getInstance(r4).clientUserId) goto L21;
        TL_stories.TL_storyItemDeleted r42 = new TL_stories.TL_storyItemDeleted();
        r42.id = r7.id;
        return r42;
    L21:
        return r7;
    L10:
        if ((r0 - r7.date) <= 86400) goto L13;
        goto L8
    }

    private void checkExpiredStories(long r7, ArrayList<TL_stories.StoryItem> r9) {
        int r0 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        SQLiteDatabase r6 = this.storage.getDatabase();
        ArrayList r1 = null;
        int r3 = 0;
        ArrayList r2 = null;
    L4:
        if (r3 >= r9.size()) goto L11;
        TL_stories.StoryItem r4 = r9.get(r3);
        if (r0 <= r9.get(r3).expire_date) goto L10;
        if (r2 != null) goto L9;
        r2 = new ArrayList();
        r1 = new ArrayList();
    L9:
        r2.add(Integer.valueOf(r4.id));
        r1.add(r4);
        r9.remove(r3);
        r3 = r3 - 1;
    L10:
        r3 = r3 + 1;
        goto L4
    L11:
        if (r1 != null) goto L18;
        return;
    L18:
        r6.executeFast(String.format(Locale.US, "DELETE FROM stories WHERE dialog_id = %d AND story_id IN (%s)", new Object[]{Long.valueOf(r7), TextUtils.join(", ", r2)})).stepThis().dispose();     // Catch: SQLiteException -> L15
        return;
    L15:
        e = move-exception;
        FileLog.e(e);
    }

    private void fillSkippedStories(long r4, TL_stories.PeerStories r6) {
        if (r6 == null) goto L21;
        ArrayList<TL_stories.StoryItem> r62 = r6.stories;     // Catch: Exception -> L12
        int r0 = 0;
    L4:
        if (r0 >= r62.size()) goto L22;
        if ((r62.get(r0) instanceof TL_stories.TL_storyItemSkipped) == false) goto L11;
        TL_stories.StoryItem r1 = getStoryInternal(r4, r62.get(r0).id);     // Catch: Exception -> L12
        if ((r1 instanceof TL_stories.TL_storyItem) == false) goto L11;
        r62.set(r0, r1);     // Catch: Exception -> L12
    L11:
        r0 = r0 + 1;
        goto L4
    L22:
        return;
    L12:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    private static int getStoryId(MessageObject r3) {
        int r0 = r3.type;
        if (r0 == 23) goto L24;
        if (r0 == 24) goto L24;
        TLRPC.MessageMedia r02 = r3.messageOwner.media;
        if (r02 == null) goto L22;
        TLRPC.WebPage r03 = r02.webpage;
        if (r03 == null) goto L22;
        if (r03.attributes == null) goto L22;
        int r04 = 0;
    L15:
        if (r04 >= r3.messageOwner.media.webpage.attributes.size()) goto L22;
        TLRPC.WebPageAttribute r1 = r3.messageOwner.media.webpage.attributes.get(r04);
        if ((r1 instanceof TLRPC.TL_webPageAttributeStory) == true) goto L19;
        r04 = r04 + 1;
        goto L15
    L19:
        return ((TLRPC.TL_webPageAttributeStory) r1).id;
    L22:
        return r3.messageOwner.reply_to.story_id;
    L24:
        return r3.messageOwner.media.id;
    }

    private TL_stories.StoryItem getStoryInternal(long r5, int r7) {
        SQLiteDatabase r4 = this.storage.getDatabase();
        TL_stories.StoryItem r0 = null;
        SQLiteCursor r42 = r4.queryFinalized(String.format(Locale.US, "SELECT data, custom_params FROM stories WHERE dialog_id = %d AND story_id = %d", new Object[]{Long.valueOf(r5), Integer.valueOf(r7)}), new Object[0]);     // Catch: SQLiteException -> L8
        if (r42.next() == false) goto L14;
        NativeByteBuffer r72 = r42.byteBufferValue(0);     // Catch: SQLiteException -> L8
        NativeByteBuffer r2 = r42.byteBufferValue(1);     // Catch: SQLiteException -> L8
        if (r72 == null) goto L10;
        r0 = TL_stories.StoryItem.TLdeserialize(r72, r72.readInt32(true), true);     // Catch: SQLiteException -> L8
        r0.dialogId = r5;     // Catch: SQLiteException -> L8
        r72.reuse();     // Catch: SQLiteException -> L8
    L10:
        if (r0 == null) goto L12;
        StoryCustomParamsHelper.readLocalParams(r0, r2);     // Catch: SQLiteException -> L8
    L12:
        if (r2 == null) goto L14;
        r2.reuse();     // Catch: SQLiteException -> L8
    L14:
        r42.dispose();     // Catch: SQLiteException -> L8
        return r0;
    L8:
        e = move-exception;
        FileLog.e(e);
        return r0;
    }

    private /* synthetic */ void lambda$deleteAllUserStories$18(long r4) {
        SQLiteDatabase r0 = this.storage.getDatabase();
        r0.executeFast(String.format(Locale.US, "DELETE FROM stories WHERE dialog_id = %d", new Object[]{Long.valueOf(r4)})).stepThis().dispose();     // Catch: Throwable -> L5
        return;
    L5:
        th = move-exception;
        this.storage.checkSQLException(th);
    }

    private /* synthetic */ void lambda$deleteStories$12(ArrayList r4, long r5) {
        SQLiteDatabase r0 = this.storage.getDatabase();
        String r42 = TextUtils.join(", ", r4);     // Catch: Throwable -> L5
        r0.executeFast(String.format(Locale.US, "DELETE FROM stories WHERE dialog_id = %d AND story_id IN (%s)", new Object[]{Long.valueOf(r5), r42})).stepThis().dispose();     // Catch: Throwable -> L5
        return;
    L5:
        th = move-exception;
        this.storage.checkSQLException(th);
    }

    private /* synthetic */ void lambda$deleteStory$11(long r4, int r6) {
        SQLiteDatabase r0 = this.storage.getDatabase();
        r0.executeFast(String.format(Locale.US, "DELETE FROM stories WHERE dialog_id = %d AND story_id = %d", new Object[]{Long.valueOf(r4), Integer.valueOf(r6)})).stepThis().dispose();     // Catch: Throwable -> L5
        return;
    L5:
        th = move-exception;
        this.storage.checkSQLException(th);
    }

    private /* synthetic */ void lambda$fillMessagesWithStories$13(ArrayList r1) {
        updateMessagesWithStories(r1);
    }

    private /* synthetic */ void lambda$fillMessagesWithStories$14(Timer.Task r4, ArrayList r5, long r6, boolean r8, Timer r9, int[] r10, Runnable r11, TLObject r12, TLRPC.TL_error r13) {
        Timer.done(r4);
        if (r12 == null) goto L18;
        TL_stories.TL_stories_stories r122 = (TL_stories.TL_stories_stories) r12;
        int r92 = 0;
    L6:
        if (r92 >= r5.size()) goto L20;
        MessageObject r132 = (MessageObject) r5.get(r92);
        int r0 = 0;
    L9:
        if (r0 >= r122.stories.size()) goto L14;
        if (r122.stories.get(r0).id == getStoryId(r132)) goto L12;
        r0 = r0 + 1;
        goto L9
    L12:
        applyStory(this.currentAccount, r6, r132, r122.stories.get(r0));
    L15:
        if (r8 == false) goto L17;
        this.storage.getStorageQueue().postRunnable(new StoriesStorage$$ExternalSyntheticLambda5(this, r5));
    L17:
        r92 = r92 + 1;
        goto L6
    L14:
        TL_stories.TL_storyItemDeleted r02 = new TL_stories.TL_storyItemDeleted();
        r02.id = getStoryId(r132);
        applyStory(this.currentAccount, r6, r132, r02);
    L20:
        int r3 = r10[0] - 1;
        r10[0] = r3;
        if (r3 != 0) goto L29;
        r11.run();
        return;
    L29:
        return;
    L18:
        if (r13 == null) goto L20;
        Timer.log(r9, "fillMessagesWithStories: getStoriesByID error " + r13.code + " " + r13.text);
        goto L20
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r11v4 */
    private /* synthetic */ void lambda$getAllStories$3(Utilities.Callback r24) {
        SQLiteDatabase r2 = this.storage.getDatabase();
        ArrayList<TL_stories.PeerStories> r3 = new ArrayList();
        ArrayList<Long> r4 = new ArrayList();
        ArrayList<Long> r5 = new ArrayList();
        ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        int r8 = 0;
        SQLiteCursor r7 = r2.queryFinalized("SELECT dialog_id, max_read FROM stories_counter", new Object[0]);     // Catch: Throwable -> L56
        LongSparseIntArray r9 = new LongSparseIntArray();     // Catch: Throwable -> L10
    L5:
        boolean r11 = true;
        if (r7.next() == false) goto L13;
        long r12 = r7.longValue(0);     // Catch: Throwable -> L10
        r9.put(r12, r7.intValue(1));     // Catch: Throwable -> L10
        if (r12 > 0) goto L9;
        r5.add(Long.valueOf(r12));     // Catch: Throwable -> L10
        goto L5
    L9:
        r4.add(Long.valueOf(r12));     // Catch: Throwable -> L10
        goto L5
    L13:
        r7.dispose();     // Catch: Throwable -> L10
        int r72 = 0;
    L15:
        if (r72 >= r9.size()) goto L58;
        long r122 = r9.keyAt(r72);     // Catch: Throwable -> L56
        int r10 = r9.valueAt(r72);     // Catch: Throwable -> L56
        SQLiteCursor r6 = r2.queryFinalized(String.format(Locale.US, "SELECT data, custom_params FROM stories WHERE dialog_id = %d", new Object[]{Long.valueOf(r122)}), new Object[r8]);     // Catch: Throwable -> L56
        ArrayList<TL_stories.StoryItem> r14 = new ArrayList();     // Catch: Throwable -> L47
        r6 = r6;
        ?? r112 = r11;
    L19:
        if (r6.next() == false) goto L53;
        NativeByteBuffer r15 = r6.byteBufferValue(r8);     // Catch: Throwable -> L47
        NativeByteBuffer r82 = r6.byteBufferValue(r112);     // Catch: Throwable -> L47
        if (r15 == 0) goto L49;
        SQLiteDatabase r17 = r2;
        TL_stories.StoryItem r22 = TL_stories.StoryItem.TLdeserialize(r15, r15.readInt32(r112), r112);     // Catch: Throwable -> L47
        r22.dialogId = r122;     // Catch: Throwable -> L47
        boolean r18 = r112;
        TL_stories.StoryFwdHeader r113 = r22.fwd_from;     // Catch: Throwable -> L47
        if (r113 != null) goto L78;
    L31:
        Object r19 = r6;
        int r114 = 0;
    L86:
        if (r114 >= r22.media_areas.size()) goto L43;
        if ((r22.media_areas.get(r114) instanceof TL_stories.TL_mediaAreaChannelPost) == false) goto L41;
        int r20 = r72;
        long r62 = ((TL_stories.TL_mediaAreaChannelPost) r22.media_areas.get(r114)).channel_id;     // Catch: Throwable -> L39
        if (r5.contains(Long.valueOf(r62)) == true) goto L42;
        r5.add(Long.valueOf(r62));     // Catch: Throwable -> L39
    L42:
        r114 = r114 + 1;     // Catch: Throwable -> L39
        r72 = r20;
        goto L86
    L41:
        r20 = r72;
        goto L42
    L43:
        int r202 = r72;
        TLRPC.Peer r63 = r22.from_id;     // Catch: Throwable -> L39
        if (r63 == null) goto L46;
        MessagesStorage.addLoadPeerInfo(r63, r4, r5);     // Catch: Throwable -> L39
    L46:
        StoryCustomParamsHelper.readLocalParams(r22, r82);     // Catch: Throwable -> L39
        r14.add(r22);     // Catch: Throwable -> L39
        r15.reuse();     // Catch: Throwable -> L39
        Object r192 = r19;
    L50:
        if (r82 == null) goto L52;
        r82.reuse();     // Catch: Throwable -> L39
    L52:
        r2 = r17;
        r112 = r18;
        r6 = r192;
        r72 = r202;
        r8 = 0;
    L39:
        th = th;
        r6 = r19;
    L82:
        FileLog.e(th);     // Catch: Throwable -> L72
        if (r6 == 0) goto L70;
        r6.dispose();
    L70:
        AndroidUtilities.runOnUIThread(new StoriesStorage$$ExternalSyntheticLambda10(r24));
        return;
    L72:
        th = move-exception;
        if (r6 == 0) goto L75;
        r6.dispose();
    L75:
        throw th;
    L78:
        TLRPC.Peer r115 = r113.from;     // Catch: Throwable -> L29
        if (r115 == null) goto L31;
        MessagesStorage.addLoadPeerInfo(r115, r4, r5);     // Catch: Throwable -> L29
    L29:
        th = th;
        goto L82
    L49:
        r17 = r2;
        r192 = r6;
        r202 = r72;
        r18 = r112;
        goto L50
    L53:
        SQLiteDatabase r172 = r2;
        int r203 = r72;
        boolean r182 = r112;
        r6.dispose();     // Catch: Throwable -> L39
        TL_stories.TL_peerStories r23 = new TL_stories.TL_peerStories();     // Catch: Throwable -> L56
        r23.stories = r14;     // Catch: Throwable -> L56
        r23.max_read_id = r10;     // Catch: Throwable -> L56
        r23.peer = MessagesController.getInstance(this.currentAccount).getPeer(r122);     // Catch: Throwable -> L56
        r3.add(r23);     // Catch: Throwable -> L56
        r72 = r203 + 1;
        r2 = r172;
        r11 = r182;
        r8 = 0;
    L47:
        th = th;
        goto L82
    L58:
        boolean r116 = r11 ? 1 : 0;
        TL_stories.TL_stories_allStories r25 = new TL_stories.TL_stories_allStories();
        r25.peer_stories = r3;
        r25.users = this.storage.getUsers(r4);
        r25.chats = this.storage.getChats(r5);
        int r83 = 0;
    L59:
        int r32 = r25.peer_stories.size();
        ArrayList<TL_stories.PeerStories> r42 = r25.peer_stories;
        if (r83 >= r32) goto L65;
        TL_stories.PeerStories r33 = r42.get(r83);
        checkExpiredStories(DialogObject.getPeerDialogId(r33.peer), r33.stories);
        if (r33.stories.isEmpty() == false) goto L64;
        r25.peer_stories.remove(r83);
        r83 = r83 - 1;
    L64:
        Collections.sort(r33.stories, StoriesController.storiesComparator);
        r83 = r83 + 1;
        goto L59
    L65:
        Collections.sort(r42, Comparator.comparingInt(new StoriesStorage$$ExternalSyntheticLambda11()));
        AndroidUtilities.runOnUIThread(new StoriesStorage$$ExternalSyntheticLambda12(r24, r25));
        return;
    L10:
        th = th;
        r6 = r7;
    L56:
        th = th;
        r6 = 0;
        goto L82
    }

    private /* synthetic */ void lambda$getMaxReadIds$16(Utilities.Callback r8) {
        SQLiteDatabase r0 = this.storage.getDatabase();
        LongSparseIntArray r1 = new LongSparseIntArray();
        SQLiteCursor r2 = null;
        r2 = r0.queryFinalized("SELECT dialog_id, max_read FROM stories_counter", new Object[0]);     // Catch: Throwable -> L8 Exception -> L10
    L4:
        if (r2.next() == false) goto L12;
        r1.put(r2.longValue(0), r2.intValue(1));     // Catch: Throwable -> L8 Exception -> L10
    L12:
        r2.dispose();
    L16:
        AndroidUtilities.runOnUIThread(new StoriesStorage$$ExternalSyntheticLambda6(r8, r1));
        return;
    L10:
        e = move-exception;
        this.storage.checkSQLException(e);     // Catch: Throwable -> L8
        if (r2 == null) goto L16;
    L8:
        th = move-exception;
        if (r2 == null) goto L20;
        r2.dispose();
    L20:
        throw th;
    }

    private /* synthetic */ void lambda$processUpdate$9(TL_stories.TL_updateStory r12) {
        SQLiteDatabase r1 = this.storage.getDatabase();
        long r2 = DialogObject.getPeerDialogId(r12.peer);     // Catch: Throwable -> L10
        TL_stories.StoryItem r122 = r12.story;     // Catch: Throwable -> L10
        int r4 = r122.id;     // Catch: Throwable -> L10
        int r7 = 0;
        if ((r122 instanceof TL_stories.TL_storyItemDeleted) == false) goto L20;
        Locale r123 = Locale.US;     // Catch: Throwable -> L10
        SQLiteCursor r5 = r1.queryFinalized(String.format(r123, "SELECT data, custom_params FROM stories WHERE dialog_id = %d AND story_id = %d", new Object[]{Long.valueOf(r2), Integer.valueOf(r4)}), new Object[0]);     // Catch: Throwable -> L10
        if (r5.next() == false) goto L15;
        NativeByteBuffer r8 = r5.byteBufferValue(0);     // Catch: Throwable -> L10
        NativeByteBuffer r9 = r5.byteBufferValue(1);     // Catch: Throwable -> L10
        if (r8 == null) goto L12;
        StoryCustomParamsHelper.readLocalParams(TL_stories.StoryItem.TLdeserialize(r8, r8.readInt32(true), true), r9);     // Catch: Throwable -> L10
        r8.reuse();     // Catch: Throwable -> L10
    L12:
        if (r9 == null) goto L14;
        r9.reuse();     // Catch: Throwable -> L10
    L14:
        boolean r82 = true;
    L16:
        r5.dispose();     // Catch: Throwable -> L10
        r1.executeFast(String.format(r123, "DELETE FROM stories WHERE dialog_id = %d AND story_id = %d", new Object[]{Long.valueOf(r2), Integer.valueOf(r4)})).stepThis().dispose();     // Catch: Throwable -> L10
        if (r82 == false) goto L24;
        int r124 = -1;
    L25:
        SQLiteCursor r0 = r1.queryFinalized("SELECT count, max_read FROM stories_counter WHERE dialog_id = " + r2, new Object[0]);     // Catch: Throwable -> L10
        if (r0.next() == false) goto L28;
        r7 = r0.intValue(1);     // Catch: Throwable -> L10
    L28:
        r0.dispose();     // Catch: Throwable -> L10
        r1.executeFast(String.format(Locale.US, "UPDATE stories_counter SET count = %d WHERE dialog_id = %d", new Object[]{Integer.valueOf(r7 + r124), Long.valueOf(r2)})).stepThis().dispose();     // Catch: Throwable -> L10
        return;
    L24:
        r124 = 0;
        goto L25
    L15:
        r82 = false;
        goto L16
    L20:
        if ((r122 instanceof TL_stories.TL_storyItem) == false) goto L24;
        updateStoryItemInternal(r2, r122);     // Catch: Throwable -> L10
        SQLiteCursor r125 = r1.queryFinalized(String.format(Locale.US, "SELECT story_id FROM stories WHERE dialog_id = %d AND story_id = %d", new Object[]{Long.valueOf(r2), Integer.valueOf(r4)}), new Object[0]);     // Catch: Throwable -> L10
        boolean r42 = r125.next();     // Catch: Throwable -> L10
        r125.dispose();     // Catch: Throwable -> L10
        if (r42 == true) goto L24;
        r124 = 1;
    L10:
        th = move-exception;
        this.storage.checkSQLException(th);
    }

    private /* synthetic */ void lambda$putPeerStories$17(TL_stories.PeerStories r3) {
        putStoriesInternal(DialogObject.getPeerDialogId(r3.peer), r3);
    }

    private /* synthetic */ void lambda$saveAllStories$4(ArrayList r10, boolean r11, boolean r12, Runnable r13) {
        SQLiteDatabase r0 = this.storage.getDatabase();
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r2 >= r10.size()) goto L6;
        TL_stories.PeerStories r3 = (TL_stories.PeerStories) r10.get(r2);
        fillSkippedStories(DialogObject.getPeerDialogId(r3.peer), r3);
        r2 = r2 + 1;
        goto L4
    L6:
        if (r11 == true) goto L48;
        SQLiteCursor r112 = null;
        r112 = r0.queryFinalized("SELECT DISTINCT dialog_id FROM stories", new Object[0]);     // Catch: Throwable -> L17
        ArrayList r22 = new ArrayList();     // Catch: Throwable -> L17
    L10:
        if (r112.next() == false) goto L36;
        long r32 = r112.longValue(0);     // Catch: Throwable -> L17
        int r6 = this.currentAccount;
        if (r32 > 0) goto L14;
        MessagesController r5 = MessagesController.getInstance(r6);     // Catch: Throwable -> L17
        long r62 = -r32;
        TLRPC.Chat r52 = r5.getChat(Long.valueOf(r62));     // Catch: Throwable -> L17
        if (r52 != null) goto L28;
        r52 = MessagesStorage.getInstance(this.currentAccount).getChat(r62);     // Catch: Throwable -> L17
    L28:
        if (r52 == null) goto L33;
        if (r52.stories_hidden != r12) goto L10;
        if (r22.contains(Long.valueOf(r32)) == true) goto L10;
    L33:
        r22.add(Long.valueOf(r32));     // Catch: Throwable -> L17
        goto L10
    L14:
        TLRPC.User r53 = MessagesController.getInstance(r6).getUser(Long.valueOf(r32));     // Catch: Throwable -> L17
        if (r53 != null) goto L19;
        r53 = MessagesStorage.getInstance(this.currentAccount).getUser(r32);     // Catch: Throwable -> L17
    L19:
        if (r53 == null) goto L24;
        if (r53.stories_hidden != r12) goto L10;
        if (r22.contains(Long.valueOf(r32)) == true) goto L10;
    L24:
        r22.add(Long.valueOf(r32));     // Catch: Throwable -> L17
        goto L10
    L36:
        if (BuildVars.LOGS_ENABLED == false) goto L38;
        FileLog.d("StoriesStorage delete dialogs " + TextUtils.join(",", r22));     // Catch: Throwable -> L17
    L38:
        r0.executeFast(String.format(Locale.US, "DELETE FROM stories WHERE dialog_id IN(%s)", new Object[]{TextUtils.join(",", r22)})).stepThis().dispose();     // Catch: Throwable -> L17
    L39:
        r112.dispose();
    L17:
        th = move-exception;
        this.storage.checkSQLException(th);     // Catch: Throwable -> L43
        if (r112 == null) goto L48;
    L43:
        th = move-exception;
        if (r112 == null) goto L46;
        r112.dispose();
    L46:
        throw th;
    L48:
        if (r1 >= r10.size()) goto L50;
        TL_stories.PeerStories r113 = (TL_stories.PeerStories) r10.get(r1);
        putStoriesInternal(DialogObject.getPeerDialogId(r113.peer), r113);
        r1 = r1 + 1;
        goto L48
    L50:
        if (r13 == null) goto L76;
        AndroidUtilities.runOnUIThread(r13);
        return;
    }

    private /* synthetic */ void lambda$updateMaxReadId$8(long r4, int r6) {
        SQLiteDatabase r0 = this.storage.getDatabase();
        r0.executeFast(String.format(Locale.US, "REPLACE INTO stories_counter VALUES(%d, 0, %d)", new Object[]{Long.valueOf(r4), Integer.valueOf(r6)})).stepThis().dispose();     // Catch: Throwable -> L5
        return;
    L5:
        th = move-exception;
        this.storage.checkSQLException(th);
    }

    private /* synthetic */ void lambda$updateStories$10(TL_stories.PeerStories r5) {
        int r0 = 0;
    L4:
        if (r0 >= r5.stories.size()) goto L6;
        updateStoryItemInternal(DialogObject.getPeerDialogId(r5.peer), r5.stories.get(r0));
        r0 = r0 + 1;
        goto L4
    }

    private /* synthetic */ void lambda$updateStoryItem$7(long r1, TL_stories.StoryItem r3) {
        updateStoryItemInternal(r1, r3);
    }

    private void updateStoryItemInternal(long r3, TL_stories.StoryItem r5) {
        if (r3 == 0) goto L32;
        if (r5 != null) goto L7;
        return;
    L7:
        if ((r5 instanceof TL_stories.TL_storyItemDeleted) == false) goto L10;
        FileLog.e("StoriesStorage: try write deleted story");
    L10:
        if (StoriesUtilities.isExpired(this.currentAccount, r5) == false) goto L12;
        FileLog.e("StoriesStorage: try write expired story");
    L12:
        SQLitePreparedStatement r0 = null;
        r0 = this.storage.getDatabase().executeFast("REPLACE INTO stories VALUES(?, ?, ?, ?)");     // Catch: Throwable -> L16 Exception -> L18
        r0.bindLong(1, r3);     // Catch: Throwable -> L16 Exception -> L18
        r0.bindLong(2, r5.id);     // Catch: Throwable -> L16 Exception -> L18
        NativeByteBuffer r2 = new NativeByteBuffer(r5.getObjectSize());     // Catch: Throwable -> L16 Exception -> L18
        r5.serializeToStream(r2);     // Catch: Throwable -> L16 Exception -> L18
        r0.bindByteBuffer(3, r2);     // Catch: Throwable -> L16 Exception -> L18
        NativeByteBuffer r32 = StoryCustomParamsHelper.writeLocalParams(r5);     // Catch: Throwable -> L16 Exception -> L18
        if (r32 == null) goto L20;
        r0.bindByteBuffer(4, r32);     // Catch: Throwable -> L16 Exception -> L18
    L21:
        if (r32 == null) goto L23;
        r32.reuse();     // Catch: Throwable -> L16 Exception -> L18
    L23:
        r0.step();     // Catch: Throwable -> L16 Exception -> L18
        r2.reuse();     // Catch: Throwable -> L16 Exception -> L18
        r0.dispose();
        return;
    L20:
        r0.bindNull(4);     // Catch: Throwable -> L16 Exception -> L18
    L16:
        th = move-exception;
        if (r0 == null) goto L31;
        r0.dispose();
    L31:
        throw th;
    L18:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L16
        if (r0 == null) goto L35;
        r0.dispose();
        return;
    L35:
        return;
    }

    public void deleteAllUserStories(long r3) {
        this.storage.getStorageQueue().postRunnable(new StoriesStorage$$ExternalSyntheticLambda1(this, r3));
    }

    public void deleteStories(long r3, ArrayList<Integer> r5) {
        this.storage.getStorageQueue().postRunnable(new StoriesStorage$$ExternalSyntheticLambda15(this, r5, r3));
    }

    public void deleteStory(long r3, int r5) {
        this.storage.getStorageQueue().postRunnable(new StoriesStorage$$ExternalSyntheticLambda0(this, r3, r5));
    }

    public void fillMessagesWithStories(LongSparseArray<ArrayList<MessageObject>> r7, Runnable r8, int r9, Timer r10) {
        fillMessagesWithStories(r7, r8, r9, true, r10);
    }

    public void fillMessagesWithStories(LongSparseArray<ArrayList<MessageObject>> r17, Runnable r18, int r19, boolean r20, Timer r21) {
        Timer r7 = r21;
        if (r18 == null) goto L39;
        if (r17 != null) goto L8;
        r18.run();
        return;
    L8:
        ArrayList r0 = new ArrayList();
        Timer.Task r2 = Timer.start(r7, "fillMessagesWithStories: applying stories for existing array");
        int r3 = 0;
    L10:
        if (r3 >= r17.size()) goto L23;
        long r4 = r17.keyAt(r3);
        ArrayList<MessageObject> r6 = r17.valueAt(r3);
        int r8 = 0;
    L13:
        if (r8 >= r6.size()) goto L22;
        MessageObject r9 = r6.get(r8);
        TL_stories.StoryItem r13 = getStoryInternal(r4, getStoryId(r9));
        if (r13 == null) goto L21;
        if ((r13 instanceof TL_stories.TL_storyItemSkipped) == true) goto L21;
        applyStory(this.currentAccount, r4, r9, r13);
        r0.add(r9);
        r6.remove(r8);
        r8 = r8 - 1;
        if (r6.isEmpty() == false) goto L21;
        r17.removeAt(r3);
        r3 = r3 - 1;
    L21:
        r8 = r8 + 1;
        goto L13
    L22:
        r3 = r3 + 1;
        goto L10
    L23:
        Timer.done(r2);
        if (r20 == false) goto L27;
        updateMessagesWithStories(r0);
    L27:
        if (r17.isEmpty() == true) goto L40;
        int[] r82 = {r17.size()};
        int r132 = 0;
    L30:
        if (r132 >= r17.size()) goto L52;
        long r42 = r17.keyAt(r132);
        ArrayList<MessageObject> r32 = r17.valueAt(r132);
        TL_stories.TL_stories_getStoriesByID r14 = new TL_stories.TL_stories_getStoriesByID();
        r14.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r42);
        int r02 = 0;
    L33:
        if (r02 >= r32.size()) goto L35;
        r14.id.add(Integer.valueOf(getStoryId(r32.get(r02))));
        r02 = r02 + 1;
        goto L33
    L35:
        int r03 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r14, new StoriesStorage$$ExternalSyntheticLambda2(this, Timer.start(r7, "fillMessagesWithStories: getStoriesByID did=" + r42 + " ids=" + TextUtils.join(",", r14.id)), r32, r42, r20, r7, r82, r18));
        if (r19 == 0) goto L38;
        ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(r03, r19);
    L38:
        r132 = r132 + 1;
        r7 = r21;
        goto L30
    L52:
        return;
    L40:
        r18.run();
        return;
    }

    public void getAllStories(Utilities.Callback<TL_stories.TL_stories_allStories> r3) {
        this.storage.getStorageQueue().postRunnable(new StoriesStorage$$ExternalSyntheticLambda8(this, r3));
    }

    public void getMaxReadIds(Utilities.Callback<LongSparseIntArray> r3) {
        this.storage.getStorageQueue().postRunnable(new StoriesStorage$$ExternalSyntheticLambda4(this, r3));
    }

    public void processUpdate(TL_stories.TL_updateStory r3) {
        this.storage.getStorageQueue().postRunnable(new StoriesStorage$$ExternalSyntheticLambda9(this, r3));
    }

    public void putPeerStories(TL_stories.PeerStories r3) {
        this.storage.getStorageQueue().postRunnable(new StoriesStorage$$ExternalSyntheticLambda13(this, r3));
    }

    public void putStoriesInternal(long r9, TL_stories.PeerStories r11) {
        SQLiteDatabase r8 = this.storage.getDatabase();
        if (r11 == null) goto L28;
        ArrayList<TL_stories.StoryItem> r0 = r11.stories;     // Catch: Exception -> L20
        SQLitePreparedStatement r1 = r8.executeFast("REPLACE INTO stories VALUES(?, ?, ?, ?)");     // Catch: Exception -> L20
        int r3 = 0;
    L6:
        if (r3 >= r0.size()) goto L18;
        r1.requery();     // Catch: Exception -> L20
        TL_stories.StoryItem r4 = r0.get(r3);     // Catch: Exception -> L20
        if ((r4 instanceof TL_stories.TL_storyItemDeleted) == false) goto L10;
        FileLog.e("try write deleted story");     // Catch: Exception -> L20
    L17:
        r3 = r3 + 1;     // Catch: Exception -> L20
        goto L6
    L10:
        r1.bindLong(1, r9);     // Catch: Exception -> L20
        r1.bindLong(2, r4.id);     // Catch: Exception -> L20
        NativeByteBuffer r5 = new NativeByteBuffer(r4.getObjectSize());     // Catch: Exception -> L20
        r4.serializeToStream(r5);     // Catch: Exception -> L20
        r1.bindByteBuffer(3, r5);     // Catch: Exception -> L20
        NativeByteBuffer r42 = StoryCustomParamsHelper.writeLocalParams(r4);     // Catch: Exception -> L20
        if (r42 == null) goto L13;
        r1.bindByteBuffer(4, r42);     // Catch: Exception -> L20
    L14:
        if (r42 == null) goto L16;
        r42.reuse();     // Catch: Exception -> L20
    L16:
        r1.step();     // Catch: Exception -> L20
        r5.reuse();     // Catch: Exception -> L20
        goto L17
    L13:
        r1.bindNull(4);     // Catch: Exception -> L20
        goto L14
    L18:
        r1.dispose();     // Catch: Exception -> L20
        r8.executeFast(String.format(Locale.US, "REPLACE INTO stories_counter VALUES(%d, %d, %d)", new Object[]{Long.valueOf(r9), 0, Integer.valueOf(r11.max_read_id)})).stepThis().dispose();     // Catch: Exception -> L20
        return;
    L20:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    public void putStoryInternal(long r2, TL_stories.StoryItem r4) {
        SQLitePreparedStatement r1 = this.storage.getDatabase().executeFast("REPLACE INTO stories VALUES(?, ?, ?, ?)");     // Catch: Exception -> L15
        if ((r4 instanceof TL_stories.TL_storyItemDeleted) == false) goto L7;
        FileLog.e("putStoryInternal: try write deleted story");     // Catch: Exception -> L15
        return;
    L7:
        r1.bindLong(1, r2);     // Catch: Exception -> L15
        r1.bindLong(2, r4.id);     // Catch: Exception -> L15
        NativeByteBuffer r22 = new NativeByteBuffer(r4.getObjectSize());     // Catch: Exception -> L15
        r4.serializeToStream(r22);     // Catch: Exception -> L15
        r1.bindByteBuffer(3, r22);     // Catch: Exception -> L15
        NativeByteBuffer r3 = StoryCustomParamsHelper.writeLocalParams(r4);     // Catch: Exception -> L15
        if (r3 == null) goto L10;
        r1.bindByteBuffer(4, r3);     // Catch: Exception -> L15
    L11:
        if (r3 == null) goto L13;
        r3.reuse();     // Catch: Exception -> L15
    L13:
        r1.step();     // Catch: Exception -> L15
        r22.reuse();     // Catch: Exception -> L15
        r1.dispose();     // Catch: Exception -> L15
        return;
    L10:
        r1.bindNull(4);     // Catch: Exception -> L15
    L15:
        e = move-exception;
        FileLog.e(e);
    }

    public void saveAllStories(ArrayList<TL_stories.PeerStories> r8, boolean r9, boolean r10, Runnable r11) {
        this.storage.getStorageQueue().postRunnable(new StoriesStorage$$ExternalSyntheticLambda14(this, r8, r9, r10, r11));
    }

    public void updateMaxReadId(long r6, int r8) {
        int r1 = this.currentAccount;
        if (r6 <= 0) goto L9;
        TLRPC.UserFull r0 = MessagesController.getInstance(r1).getUserFull(r6);
        if (r0 == null) goto L14;
        TL_stories.PeerStories r12 = r0.stories;
        if (r12 == null) goto L14;
        r12.max_read_id = r8;
        this.storage.updateUserInfo(r0, false);
    L14:
        this.storage.getStorageQueue().postRunnable(new StoriesStorage$$ExternalSyntheticLambda7(this, r6, r8));
        return;
    L9:
        TLRPC.ChatFull r02 = MessagesController.getInstance(r1).getChatFull(-r6);
        if (r02 == null) goto L14;
        TL_stories.PeerStories r13 = r02.stories;
        if (r13 == null) goto L14;
        r13.max_read_id = r8;
        this.storage.updateChatInfo(r02, false);
        goto L14
    }

    public void updateMessagesWithStories(List<MessageObject> r17) {
        SQLiteDatabase r0 = this.storage.getDatabase();     // Catch: Throwable -> L22
        if (r17.isEmpty() == true) goto L65;
        SQLitePreparedStatement r2 = r0.executeFast("UPDATE messages_v2 SET replydata = ? WHERE mid = ? AND uid = ?");     // Catch: Throwable -> L22
        SQLitePreparedStatement r3 = r0.executeFast("UPDATE messages_topics SET replydata = ? WHERE mid = ? AND uid = ?");     // Catch: Throwable -> L22
        SQLitePreparedStatement r4 = r0.executeFast("UPDATE messages_v2 SET data = ? WHERE mid = ? AND uid = ?");     // Catch: Throwable -> L22
        SQLitePreparedStatement r02 = r0.executeFast("UPDATE messages_topics SET data = ? WHERE mid = ? AND uid = ?");     // Catch: Throwable -> L22
        int r6 = 0;
    L7:
        if (r6 >= r17.size()) goto L46;
        MessageObject r8 = r17.get(r6);     // Catch: Throwable -> L22
        int r9 = 0;
    L10:
        if (r9 >= 2) goto L45;
        NativeByteBuffer r13 = null;
        if (r8.messageOwner.replyStory == null) goto L30;
        if (r9 != 0) goto L16;
        SQLitePreparedStatement r11 = r2;
    L17:
        if (r11 == null) goto L38;
        NativeByteBuffer r15 = new NativeByteBuffer(r8.messageOwner.replyStory.getObjectSize());     // Catch: Throwable -> L26
        r8.messageOwner.replyStory.serializeToStream(r15);     // Catch: Throwable -> L24
        r11.requery();     // Catch: Throwable -> L24
        r11.bindByteBuffer(1, r15);     // Catch: Throwable -> L24
        r11.bindInteger(2, r8.getId());     // Catch: Throwable -> L24
        r11.bindLong(3, r8.getDialogId());     // Catch: Throwable -> L24
        r11.step();     // Catch: Throwable -> L24
        r15.reuse();     // Catch: Throwable -> L22
    L24:
        th = th;
        r13 = r15;
    L27:
        if (r13 == null) goto L29;
        r13.reuse();     // Catch: Throwable -> L22
    L29:
        throw th;     // Catch: Throwable -> L22
    L26:
        th = th;
    L38:
        r9 = r9 + 1;     // Catch: Throwable -> L22
        goto L10
    L16:
        r11 = r3;
        goto L17
    L30:
        if (r9 != 0) goto L32;
        SQLitePreparedStatement r5 = r4;
    L33:
        if (r5 == null) goto L38;
        NativeByteBuffer r112 = new NativeByteBuffer(r8.messageOwner.getObjectSize());     // Catch: Throwable -> L41
        r8.messageOwner.serializeToStream(r112);     // Catch: Throwable -> L39
        r5.requery();     // Catch: Throwable -> L39
        r5.bindByteBuffer(1, r112);     // Catch: Throwable -> L39
        r5.bindInteger(2, r8.getId());     // Catch: Throwable -> L39
        r5.bindLong(3, r8.getDialogId());     // Catch: Throwable -> L39
        r5.step();     // Catch: Throwable -> L39
        r112.reuse();     // Catch: Throwable -> L22
    L39:
        th = th;
        r13 = r112;
    L42:
        if (r13 == null) goto L44;
        r13.reuse();     // Catch: Throwable -> L22
    L44:
        throw th;     // Catch: Throwable -> L22
    L41:
        th = th;
        goto L42
    L32:
        r5 = r02;
        goto L33
    L45:
        r6 = r6 + 1;     // Catch: Throwable -> L22
        goto L7
    L46:
        r2.dispose();     // Catch: Throwable -> L22
        r3.dispose();     // Catch: Throwable -> L22
        r4.dispose();     // Catch: Throwable -> L22
        r02.dispose();     // Catch: Throwable -> L22
        return;
    L65:
        return;
    L22:
        th = move-exception;
        this.storage.checkSQLException(th);
    }

    public void updateStories(TL_stories.PeerStories r3) {
        this.storage.getStorageQueue().postRunnable(new StoriesStorage$$ExternalSyntheticLambda16(this, r3));
    }

    public void updateStoryItem(long r3, TL_stories.StoryItem r5) {
        if (r3 != 0) goto L5;
        return;
    L5:
        this.storage.getStorageQueue().postRunnable(new StoriesStorage$$ExternalSyntheticLambda3(this, r3, r5));
    }
}
