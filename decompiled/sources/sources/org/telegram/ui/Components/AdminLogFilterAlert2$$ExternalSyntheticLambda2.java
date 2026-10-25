package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class AdminLogFilterAlert2$$ExternalSyntheticLambda2 implements Utilities.Callback2 {
    public final /* synthetic */ AdminLogFilterAlert2 f$0;

    public /* synthetic */ AdminLogFilterAlert2$$ExternalSyntheticLambda2(AdminLogFilterAlert2 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        this.f$0.fillItems((ArrayList) r1, (UniversalAdapter) r2);
    }
}
