package org.telegram.ui.iv;

import java.util.LinkedHashSet;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.iv.RichTableCellGrid;

/* loaded from: classes7.dex */
public final /* synthetic */ class RichTableCell$$ExternalSyntheticLambda2 implements RichTableCellGrid.CellSelectionProvider {
    public final /* synthetic */ LinkedHashSet f$0;

    public /* synthetic */ RichTableCell$$ExternalSyntheticLambda2(LinkedHashSet r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.iv.RichTableCellGrid.CellSelectionProvider
    public final boolean isSelected(TL_iv.pageTableCell r1) {
        return this.f$0.contains(r1);
    }
}
