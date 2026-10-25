package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;

/* loaded from: classes6.dex */
public final /* synthetic */ class WearAuthSheet$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ int[] f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ AvatarDrawable f$2;
    public final /* synthetic */ TLRPC.User f$3;
    public final /* synthetic */ BackupImageView f$4;

    public /* synthetic */ WearAuthSheet$$ExternalSyntheticLambda4(int[] r1, int r2, AvatarDrawable r3, TLRPC.User r4, BackupImageView r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WearAuthSheet.$r8$lambda$OlMawrInXqaCFdhiTyWaQHdJHeI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
