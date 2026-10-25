package org.telegram.messenger;

import org.telegram.tgnet.RequestTimeDelegate;
import org.telegram.ui.Components.ButtonSpan;

/* loaded from: classes5.dex */
public final /* synthetic */ class AndroidUtilities$$ExternalSyntheticLambda16 implements RequestTimeDelegate {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ ButtonSpan.TextViewButtons[] f$1;

    public /* synthetic */ AndroidUtilities$$ExternalSyntheticLambda16(boolean[] r1, ButtonSpan.TextViewButtons[] r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestTimeDelegate
    public final void run(long r2) {
        AndroidUtilities.$r8$lambda$adO3eTQHaoFbg8HbDUVYPWuzABQ(this.f$0, this.f$1, r2);
    }
}
