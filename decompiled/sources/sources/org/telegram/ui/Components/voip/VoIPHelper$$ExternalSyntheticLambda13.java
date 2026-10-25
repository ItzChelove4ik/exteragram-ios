package org.telegram.ui.Components.voip;

import android.app.Activity;
import java.util.HashSet;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class VoIPHelper$$ExternalSyntheticLambda13 implements Runnable {
    public final /* synthetic */ Activity f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.InputGroupCall f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ TLRPC.GroupCall f$4;
    public final /* synthetic */ HashSet f$5;

    public /* synthetic */ VoIPHelper$$ExternalSyntheticLambda13(Activity r1, int r2, TLRPC.InputGroupCall r3, boolean r4, TLRPC.GroupCall r5, HashSet r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VoIPHelper.$r8$lambda$Zot_BVY_wR6YUwOD4KisCMnlB1c(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
