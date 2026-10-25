package org.telegram.messenger;

import android.net.Uri;
import java.util.ArrayList;
import org.telegram.messenger.MessagesStorage;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda60 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ Uri f$3;
    public final /* synthetic */ MessagesStorage.LongCallback f$4;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda60(SendMessagesHelper r1, ArrayList r2, long r3, Uri r5, MessagesStorage.LongCallback r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$ra7bvuYJKJGh7XqfvMSenpLp1WI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
