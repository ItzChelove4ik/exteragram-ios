package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.messenger.MessagesStorage;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda24 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ ArrayList f$4;
    public final /* synthetic */ MessagesStorage.StringCallback f$5;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda24(SendMessagesHelper r1, String r2, String r3, String r4, ArrayList r5, MessagesStorage.StringCallback r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$4gFs7tsD8iPNc6x2hDiGLKj2fq8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
