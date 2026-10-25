package com.exteragram.messenger.feed;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.ArrayList;
import java.util.HashSet;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
final class FeedBackfillCoordinator {
    private final int currentAccount;
    private final HashSet<Long> exhausted;
    private final int guid;
    private int loadIndex;
    private final Runnable onRoundFinished;
    private final HashSet<Long> pending;
    private int roundId;
    private boolean running;

    public static /* synthetic */ void $r8$lambda$7yiiRU2Ck2bGoRgmbz2gWego_5U(FeedBackfillCoordinator r0, int r1) {
        r0.lambda$startRound$0(r1);
    }

    public FeedBackfillCoordinator(int r2, Runnable r3) {
        this.guid = ConnectionsManager.generateClassGuid();
        this.pending = new HashSet();
        this.exhausted = new HashSet();
        this.currentAccount = r2;
        this.onRoundFinished = r3;
    }

    private void finishRound() {
        this.running = false;
        this.roundId++;
        this.pending.clear();
        this.onRoundFinished.run();
    }

    private /* synthetic */ void lambda$startRound$0(int r2) {
        if (r2 == this.roundId) goto L5;
        return;
    L5:
        if (this.running == false) goto L9;
        this.exhausted.addAll(this.pending);
        finishRound();
        return;
    }

    private void onResult(long r2) {
        if (this.running == true) goto L5;
        return;
    L5:
        if (this.pending.remove(Long.valueOf(r2)) == true) goto L8;
        return;
    L8:
        if (this.pending.isEmpty() == false) goto L12;
        finishRound();
        return;
    }

    public void cancel() {
        this.running = false;
        this.roundId++;
        this.pending.clear();
        ConnectionsManager.getInstance(this.currentAccount).cancelRequestsForGuid(this.guid);
    }

    public void clearExhausted() {
        this.exhausted.clear();
    }

    public HashSet<Long> getExhaustedSnapshot() {
        return new HashSet(this.exhausted);
    }

    public void onLoadingMessagesFailed(Object... r6) {
        if (((Integer) r6[0]).intValue() == this.guid) goto L5;
        return;
    L5:
        Object r62 = r6[1];
        if ((r62 instanceof TLRPC.TL_messages_getHistory) == false) goto L13;
        TLRPC.InputPeer r63 = ((TLRPC.TL_messages_getHistory) r62).peer;
        if (r63 == null) goto L13;
        long r3 = r63.channel_id;
        if (r3 == 0) goto L12;
    L11:
        long r32 = -r3;
    L15:
        if (r32 == 0) goto L17;
        this.exhausted.add(Long.valueOf(r32));
    L17:
        onResult(r32);
        return;
    L12:
        r3 = r63.chat_id;
    L13:
        r32 = 0;
        goto L15
    }

    public void onMessagesDidLoad(Object... r5) {
        if (((Integer) r5[10]).intValue() == this.guid) goto L5;
        return;
    L5:
        Long r0 = (Long) r5[0];
        long r1 = r0.longValue();
        if (((ArrayList) r5[2]).size() >= 20) goto L8;
        this.exhausted.add(r0);
    L8:
        onResult(r1);
    }

    public void startRound(ArrayList<long[]> r28) {
        this.running = true;
        int r3 = this.roundId + 1;
        this.roundId = r3;
        this.pending.clear();
        int r4 = Math.min(4, r28.size());
        int r6 = 0;
    L3:
        if (r6 >= r4) goto L5;
        this.pending.add(Long.valueOf(r28.get(r6)[0]));
        r6 = r6 + 1;
        goto L3
    L5:
        MessagesController r7 = MessagesController.getInstance(this.currentAccount);
        int r62 = 0;
    L6:
        if (r62 >= r4) goto L8;
        long r9 = r28.get(r62)[0];
        int r14 = (int) r28.get(r62)[1];
        int r8 = this.guid;
        int r11 = this.loadIndex;
        this.loadIndex = r11 + 1;
        r7.loadMessages(r9, 0, false, 20, r14, 0, false, 0, r8, 0, 0, 0, 0, 0, r11, false);
        r62 = r62 + 1;
        goto L6
    L8:
        AndroidUtilities.runOnUIThread(new FeedBackfillCoordinator$$ExternalSyntheticLambda0(this, r3), NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
    }
}
