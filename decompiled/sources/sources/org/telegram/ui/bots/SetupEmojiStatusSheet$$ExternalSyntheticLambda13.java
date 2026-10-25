package org.telegram.ui.bots;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes7.dex */
public final /* synthetic */ class SetupEmojiStatusSheet$$ExternalSyntheticLambda13 implements Runnable {
    public final /* synthetic */ TLObject f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ Utilities.Callback f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ TL_account.updateEmojiStatus f$4;

    public /* synthetic */ SetupEmojiStatusSheet$$ExternalSyntheticLambda13(TLObject r1, boolean[] r2, Utilities.Callback r3, int r4, TL_account.updateEmojiStatus r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SetupEmojiStatusSheet.$r8$lambda$qjBVdEmBi2OOINVrp3sTDsRgkV4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
