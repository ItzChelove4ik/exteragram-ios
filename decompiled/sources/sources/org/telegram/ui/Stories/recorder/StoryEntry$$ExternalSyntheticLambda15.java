package org.telegram.ui.Stories.recorder;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryEntry$$ExternalSyntheticLambda15 implements RequestDelegate {
    public final /* synthetic */ StoryEntry f$0;
    public final /* synthetic */ TL_stories.StoryItem f$1;
    public final /* synthetic */ TLRPC.TL_messages_getAttachedStickers f$2;
    public final /* synthetic */ RequestDelegate f$3;

    public /* synthetic */ StoryEntry$$ExternalSyntheticLambda15(StoryEntry r1, TL_stories.StoryItem r2, TLRPC.TL_messages_getAttachedStickers r3, RequestDelegate r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        StoryEntry.$r8$lambda$qX16IuxE6lDY1hROj4iTNW2Xqh4(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
