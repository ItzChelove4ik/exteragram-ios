package org.telegram.ui.Stories;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public class MessageMediaStoryFull extends TLRPC.TL_messageMediaStory {
    public static int constructor = -946147811;

    static {
    }

    public MessageMediaStoryFull() {
    }

    @Override // org.telegram.tgnet.TLRPC.TL_messageMediaStory, org.telegram.tgnet.TLObject
    public void readParams(InputSerializedData r3, boolean r4) {
        this.user_id = r3.readInt64(r4);
        this.id = r3.readInt32(r4);
        this.storyItem = TL_stories.StoryItem.TLdeserialize(r3, r3.readInt32(r4), r4);
        this.via_mention = r3.readBool(r4);
        this.peer = MessagesController.getInstance(UserConfig.selectedAccount).getPeer(this.user_id);
    }

    @Override // org.telegram.tgnet.TLRPC.TL_messageMediaStory, org.telegram.tgnet.TLObject
    public void serializeToStream(OutputSerializedData r3) {
        r3.writeInt32(constructor);
        r3.writeInt64(this.user_id);
        r3.writeInt32(this.id);
        this.storyItem.serializeToStream(r3);
        r3.writeBool(this.via_mention);
    }
}
