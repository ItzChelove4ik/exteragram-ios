package com.exteragram.messenger.plugins.pip;

import com.exteragram.messenger.plugins.pip.PipController;
import org.simplifiles.archive.CancellationToken;

/* loaded from: classes4.dex */
public final /* synthetic */ class PipController$$ExternalSyntheticLambda2 implements CancellationToken {
    public final /* synthetic */ PipController.InstallerDelegate f$0;

    public /* synthetic */ PipController$$ExternalSyntheticLambda2(PipController.InstallerDelegate r1) {
        this.f$0 = r1;
    }

    @Override // org.simplifiles.archive.CancellationToken
    public final boolean isCancellationRequested() {
        return PipController.$r8$lambda$87_LTqo6sTR2itQ72BHILUyS0HU(this.f$0);
    }
}
