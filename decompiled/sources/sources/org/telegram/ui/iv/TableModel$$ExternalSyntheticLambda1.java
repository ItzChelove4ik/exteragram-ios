package org.telegram.ui.iv;

import java.util.IdentityHashMap;
import java.util.function.ToIntFunction;
import org.telegram.tgnet.tl.TL_iv;

/* loaded from: classes7.dex */
public final /* synthetic */ class TableModel$$ExternalSyntheticLambda1 implements ToIntFunction {
    public final /* synthetic */ IdentityHashMap f$0;

    public /* synthetic */ TableModel$$ExternalSyntheticLambda1(IdentityHashMap r1) {
        this.f$0 = r1;
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object r1) {
        return TableModel.$r8$lambda$hUWEgK6NjEiBDVZGfV8M3mrrKHg(this.f$0, (TL_iv.pageTableCell) r1);
    }
}
