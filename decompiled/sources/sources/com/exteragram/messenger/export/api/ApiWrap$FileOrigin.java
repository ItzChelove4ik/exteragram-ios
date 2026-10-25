package com.exteragram.messenger.export.api;

import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import java.util.Objects;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final class ApiWrap$FileOrigin extends RecordTag {
    private final long customEmojiId;
    private final int messageId;
    private final TLRPC.InputPeer peer;
    private final int split;
    private final int storyId;

    private /* synthetic */ boolean $record$equals(Object r5) {
        if ((r5 instanceof ApiWrap$FileOrigin) == false) goto L16;
        ApiWrap$FileOrigin r52 = (ApiWrap$FileOrigin) r5;
        if (this.split == r52.split) goto L7;
        return false;
    L7:
        if (this.messageId == r52.messageId) goto L9;
        return false;
    L9:
        if (this.storyId == r52.storyId) goto L11;
        return false;
    L11:
        if (this.customEmojiId == r52.customEmojiId) goto L13;
        return false;
    L13:
        if (Objects.equals(this.peer, r52.peer) == false) goto L22;
        return true;
    L22:
        return false;
    L16:
        return false;
    }

    private /* synthetic */ Object[] $record$getFieldsAsObjects() {
        return new Object[]{Integer.valueOf(this.split), this.peer, Integer.valueOf(this.messageId), Integer.valueOf(this.storyId), Long.valueOf(this.customEmojiId)};
    }

    public ApiWrap$FileOrigin() {
        this(0, null, 0, 0, 0);
    }

    public ApiWrap$FileOrigin(int r1, TLRPC.InputPeer r2, int r3, int r4, long r5) {
        this.split = r1;
        this.peer = r2;
        this.messageId = r3;
        this.storyId = r4;
        this.customEmojiId = r5;
    }

    public final boolean equals(Object r1) {
        return $record$equals(r1);
    }

    public final int hashCode() {
        return ApiWrap$FileOrigin$$ExternalSyntheticRecord0.m(this.split, this.messageId, this.storyId, this.customEmojiId, this.peer);
    }

    public int messageId() {
        return this.messageId;
    }

    public TLRPC.InputPeer peer() {
        return this.peer;
    }

    public int split() {
        return this.split;
    }

    public int storyId() {
        return this.storyId;
    }

    public final String toString() {
        return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), ApiWrap$FileOrigin.class, "split;peer;messageId;storyId;customEmojiId");
    }
}
