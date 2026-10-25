package org.telegram.ui.ActionBar;

import java.io.File;
import org.telegram.messenger.ImageReceiver;
import org.telegram.tgnet.ResultCallback;

/* loaded from: classes6.dex */
public final /* synthetic */ class EmojiThemes$$ExternalSyntheticLambda3 implements ImageReceiver.ImageReceiverDelegate {
    public final /* synthetic */ ResultCallback f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ File f$2;

    public /* synthetic */ EmojiThemes$$ExternalSyntheticLambda3(ResultCallback r1, long r2, File r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
    public final void didSetImage(ImageReceiver r9, boolean r10, boolean r11, boolean r12) {
        EmojiThemes.m5749$r8$lambda$jK27KEF1yFfzj46vlIaI1Pon0c(this.f$0, this.f$1, this.f$2, r9, r10, r11, r12);
    }
}
