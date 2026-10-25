package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.ReportBottomSheet;

/* loaded from: classes6.dex */
public final /* synthetic */ class ReportBottomSheet$Page$$ExternalSyntheticLambda1 implements Utilities.Callback2 {
    public final /* synthetic */ ReportBottomSheet.Page f$0;

    public /* synthetic */ ReportBottomSheet$Page$$ExternalSyntheticLambda1(ReportBottomSheet.Page r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        this.f$0.fillItems((ArrayList) r1, (UniversalAdapter) r2);
    }
}
