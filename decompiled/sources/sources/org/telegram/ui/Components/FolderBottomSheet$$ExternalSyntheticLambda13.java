package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class FolderBottomSheet$$ExternalSyntheticLambda13 implements Runnable {
    public final /* synthetic */ FolderBottomSheet f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ Utilities.Callback f$3;

    public /* synthetic */ FolderBottomSheet$$ExternalSyntheticLambda13(FolderBottomSheet r1, TLRPC.TL_error r2, TLObject r3, Utilities.Callback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FolderBottomSheet.$r8$lambda$Q6SqAq4739Nre6Iv4QouY1XBwcA(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
