package org.telegram.ui.ActionBar;

import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public final /* synthetic */ class Theme$PatternsLoader$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ Theme.PatternsLoader f$0;
    public final /* synthetic */ ArrayList f$1;

    public /* synthetic */ Theme$PatternsLoader$$ExternalSyntheticLambda3(Theme.PatternsLoader r1, ArrayList r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        Theme.PatternsLoader.$r8$lambda$3rNQzJ7XM6Kf_cPG0Io0cqq9hz0(this.f$0, this.f$1, r2, r3);
    }
}
