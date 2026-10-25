package com.exteragram.messenger.badges;

import com.exteragram.messenger.api.dto.BadgeDTO;
import java.util.function.Consumer;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public final /* synthetic */ class BadgesController$$ExternalSyntheticLambda1 implements Utilities.Callback {
    public final /* synthetic */ Consumer f$0;
    public final /* synthetic */ BadgeDTO f$1;

    public /* synthetic */ BadgesController$$ExternalSyntheticLambda1(Consumer r1, BadgeDTO r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        BadgesController.$r8$lambda$q2QZZ0ppiIjePLLSVYRB87x0Q6Y(this.f$0, this.f$1, (String) r2);
    }
}
