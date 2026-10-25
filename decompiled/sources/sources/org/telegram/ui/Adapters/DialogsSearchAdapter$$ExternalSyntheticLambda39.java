package org.telegram.ui.Adapters;

import org.telegram.messenger.MessagesStorage;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsSearchAdapter$$ExternalSyntheticLambda39 implements MessagesStorage.IntCallback {
    public final /* synthetic */ DialogsSearchAdapter f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ Object f$2;

    public /* synthetic */ DialogsSearchAdapter$$ExternalSyntheticLambda39(DialogsSearchAdapter r1, long r2, Object r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // org.telegram.messenger.MessagesStorage.IntCallback
    public final void run(int r4) {
        DialogsSearchAdapter.$r8$lambda$hY8kbpgahiObI09CVX97PHu5IAw(this.f$0, this.f$1, this.f$2, r4);
    }
}
