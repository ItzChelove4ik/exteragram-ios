package org.telegram.ui.Adapters;

import java.util.Comparator;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes3.dex */
public final /* synthetic */ class DialogsAdapter$$ExternalSyntheticLambda0 implements Comparator {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ DialogsAdapter$$ExternalSyntheticLambda0(MessagesController r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.util.Comparator
    public final int compare(Object r2, Object r3) {
        return DialogsAdapter.$r8$lambda$_IyqYv0ErkEI_Omrn2azgDWluho(this.f$0, this.f$1, (TLRPC.TL_contact) r2, (TLRPC.TL_contact) r3);
    }
}
