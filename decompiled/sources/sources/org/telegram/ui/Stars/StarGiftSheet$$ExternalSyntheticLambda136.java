package org.telegram.ui.Stars;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Components.ButtonSpan;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda136 implements Utilities.Callback {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ TL_stars.StarGift f$1;
    public final /* synthetic */ TL_stars.StarGiftAttribute f$2;
    public final /* synthetic */ ButtonSpan.TextViewButtons[] f$3;
    public final /* synthetic */ boolean[] f$4;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda136(StarGiftSheet r1, TL_stars.StarGift r2, TL_stars.StarGiftAttribute r3, ButtonSpan.TextViewButtons[] r4, boolean[] r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r7) {
        StarGiftSheet.$r8$lambda$gRxzNna633vOpaAkv0XC0kMCDIg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (ArrayList) r7);
    }
}
