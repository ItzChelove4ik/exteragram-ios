package org.telegram.messenger;

import java.util.HashMap;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.SendMessagesHelper;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda78 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ SendMessagesHelper.ImportingStickers f$1;
    public final /* synthetic */ HashMap f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ MessagesStorage.StringCallback f$4;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda78(SendMessagesHelper r1, SendMessagesHelper.ImportingStickers r2, HashMap r3, String r4, MessagesStorage.StringCallback r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$ykX0u_PTZIxVumVKjfTjcZOgQh4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
