package org.telegram.messenger.voip;

import java.util.ArrayList;
import me.vkryl.core.BitwiseUtils;
import org.telegram.messenger.MediaDataController;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;

/* loaded from: classes6.dex */
public class GroupCallMessage {
    private static final int FLAG_IS_OUT = 1;
    private static final int FLAG_SEND_CONFIRMED = 8;
    private static final int FLAG_SEND_DELAYED = 2;
    private static final int FLAG_SEND_ERROR = 4;
    public final int currentAccount;
    private int flags;
    public final long fromId;
    private final ArrayList<Runnable> listeners;
    public final TLRPC.TL_textWithEntities message;
    public final long randomId;
    public final long reactionAnimatedEmojiId;
    public final ReactionsLayoutInBubble.VisibleReaction visibleReaction;

    public GroupCallMessage(int r3, long r4, long r6, TLRPC.TL_textWithEntities r8) {
        this.listeners = new ArrayList();
        this.currentAccount = r3;
        this.fromId = r4;
        this.randomId = r6;
        this.message = r8;
        ArrayList<TLRPC.MessageEntity> r42 = r8.entities;
        if (r42 != null) goto L5;
    L9:
        long r0 = 0;
    L11:
        if (r0 == 0) goto L13;
        ReactionsLayoutInBubble.VisibleReaction r32 = ReactionsLayoutInBubble.VisibleReaction.fromCustomEmoji(Long.valueOf(r0));
    L21:
        this.reactionAnimatedEmojiId = r0;
        this.visibleReaction = r32;
        return;
    L13:
        ArrayList<TLRPC.MessageEntity> r43 = r8.entities;
        if (r43 != null) goto L16;
    L17:
        TLRPC.TL_availableReaction r33 = MediaDataController.getInstance(r3).getReactionsMap().get(r8.text);
        if (r33 == null) goto L20;
        r32 = ReactionsLayoutInBubble.VisibleReaction.fromEmojicon(r33);
    L20:
        r32 = null;
        goto L21
    L16:
        if (r43.isEmpty() == false) goto L20;
    L5:
        if (r42.size() != 1) goto L9;
        TLRPC.MessageEntity r44 = r8.entities.get(0);
        if ((r44 instanceof TLRPC.TL_messageEntityCustomEmoji) == false) goto L9;
        r0 = ((TLRPC.TL_messageEntityCustomEmoji) r44).document_id;
        goto L11
    }

    public boolean isOut() {
        return BitwiseUtils.hasFlag(this.flags, 1);
    }

    public boolean isSendConfirmed() {
        return BitwiseUtils.hasFlag(this.flags, 8);
    }

    public boolean isSendDelayed() {
        return BitwiseUtils.hasFlag(this.flags, 2);
    }

    public boolean isSendError() {
        return BitwiseUtils.hasFlag(this.flags, 4);
    }

    public void notifyStateUpdate() {
        ArrayList<Runnable> r3 = this.listeners;
        int r0 = r3.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Runnable r2 = r3.get(r1);
        r1 = r1 + 1;
        r2.run();
        goto L3
    }

    public void setIsOut(boolean r3) {
        this.flags = BitwiseUtils.setFlag(this.flags, 1, r3);
    }

    public void setIsSendConfirmed(boolean r3) {
        this.flags = BitwiseUtils.setFlag(this.flags, 8, r3);
    }

    public void setIsSendDelayed(boolean r3) {
        this.flags = BitwiseUtils.setFlag(this.flags, 2, r3);
    }

    public void setIsSendError(boolean r3) {
        this.flags = BitwiseUtils.setFlag(this.flags, 4, r3);
    }

    public void subscribeToStateUpdates(Runnable r1) {
        this.listeners.add(r1);
    }

    public void unsubscribeFromStateUpdates(Runnable r1) {
        this.listeners.remove(r1);
    }
}
