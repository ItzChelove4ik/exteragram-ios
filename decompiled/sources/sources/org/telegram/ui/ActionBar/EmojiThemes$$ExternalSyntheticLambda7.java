package org.telegram.ui.ActionBar;

import org.telegram.messenger.ImageReceiver;
import org.telegram.tgnet.ResultCallback;

/* loaded from: classes6.dex */
public final /* synthetic */ class EmojiThemes$$ExternalSyntheticLambda7 implements ImageReceiver.ImageReceiverDelegate {
    public final /* synthetic */ ResultCallback f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ EmojiThemes$$ExternalSyntheticLambda7(ResultCallback r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
    public final void didSetImage(ImageReceiver r8, boolean r9, boolean r10, boolean r11) {
        EmojiThemes.$r8$lambda$HUzepghFisrH5drtBM375ntzctQ(this.f$0, this.f$1, r8, r9, r10, r11);
    }
}
