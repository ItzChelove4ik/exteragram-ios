package org.telegram.messenger;

import android.net.Uri;
import java.util.ArrayList;
import org.telegram.messenger.MessagesStorage;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda59 implements MessagesStorage.LongCallback {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ Uri f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ MessagesStorage.LongCallback f$3;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda59(SendMessagesHelper r1, Uri r2, ArrayList r3, MessagesStorage.LongCallback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.MessagesStorage.LongCallback
    public final void run(long r7) {
        SendMessagesHelper.$r8$lambda$urHonJCDFAjnfy1Ai6rnNktqdkM(this.f$0, this.f$1, this.f$2, this.f$3, r7);
    }
}
