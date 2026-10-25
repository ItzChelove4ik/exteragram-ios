package org.telegram.ui;

import com.exteragram.messenger.utils.chats.WidePosts;
import java.util.ArrayList;
import org.telegram.messenger.Utilities;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda366 implements Utilities.Callback2 {
    public final /* synthetic */ WidePosts.CommentsPostAuthorLoader f$0;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda366(WidePosts.CommentsPostAuthorLoader r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        this.f$0.setMessages((ArrayList) r1, (ChatActivity) r2);
    }
}
