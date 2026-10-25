package org.telegram.ui.Components;

import android.app.Activity;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class PermissionRequest$$ExternalSyntheticLambda4 implements Utilities.Callback {
    public final /* synthetic */ String[] f$0;
    public final /* synthetic */ Activity f$1;
    public final /* synthetic */ Utilities.Callback f$2;

    public /* synthetic */ PermissionRequest$$ExternalSyntheticLambda4(String[] r1, Activity r2, Utilities.Callback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        PermissionRequest.$r8$lambda$xa6dWFIyXHQ433VJXZn8tMIv4bE(this.f$0, this.f$1, this.f$2, (int[]) r3);
    }
}
