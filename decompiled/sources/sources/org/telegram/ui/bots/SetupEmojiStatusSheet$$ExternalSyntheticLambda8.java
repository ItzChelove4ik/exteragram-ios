package org.telegram.ui.bots;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class SetupEmojiStatusSheet$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ TLRPC.Document f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ Utilities.Callback2 f$4;

    public /* synthetic */ SetupEmojiStatusSheet$$ExternalSyntheticLambda8(int r1, TLRPC.User r2, TLRPC.Document r3, int r4, Utilities.Callback2 r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SetupEmojiStatusSheet.$r8$lambda$ucmKbYbq6KPA0hOkeStQQwoEK5Q(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
