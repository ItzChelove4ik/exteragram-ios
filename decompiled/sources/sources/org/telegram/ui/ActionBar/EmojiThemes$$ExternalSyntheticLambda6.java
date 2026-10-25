package org.telegram.ui.ActionBar;

import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.Utilities;

/* loaded from: classes6.dex */
public final /* synthetic */ class EmojiThemes$$ExternalSyntheticLambda6 implements ImageReceiver.ImageReceiverDelegate {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ EmojiThemes$$ExternalSyntheticLambda6(int r1, Utilities.Callback r2, int r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
    public final void didSetImage(ImageReceiver r10, boolean r11, boolean r12, boolean r13) {
        EmojiThemes.m5750$r8$lambda$y3jiPzp6fdq2si5rHBAam_1H8(this.f$0, this.f$1, this.f$2, this.f$3, r10, r11, r12, r13);
    }
}
