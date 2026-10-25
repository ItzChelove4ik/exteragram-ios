package org.telegram.ui;

import org.telegram.messenger.voip.NativeInstance;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$$ExternalSyntheticLambda31 implements NativeInstance.AudioLevelsCallback {
    public final /* synthetic */ GroupCallActivity f$0;

    public /* synthetic */ GroupCallActivity$$ExternalSyntheticLambda31(GroupCallActivity r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.voip.NativeInstance.AudioLevelsCallback
    public final void run(int[] r1, float[] r2, boolean[] r3) {
        GroupCallActivity.$r8$lambda$bjyiyCzYz3WlVUtDP7rjedHcEj0(this.f$0, r1, r2, r3);
    }
}
