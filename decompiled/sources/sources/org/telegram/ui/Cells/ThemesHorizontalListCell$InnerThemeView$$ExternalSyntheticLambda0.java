package org.telegram.ui.Cells;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.ThemesHorizontalListCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class ThemesHorizontalListCell$InnerThemeView$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ ThemesHorizontalListCell.InnerThemeView f$0;

    public /* synthetic */ ThemesHorizontalListCell$InnerThemeView$$ExternalSyntheticLambda0(ThemesHorizontalListCell.InnerThemeView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        ThemesHorizontalListCell.InnerThemeView.$r8$lambda$jEANsvXhxZfDwzadlqDAJ8Sgbfc(this.f$0, r1, r2);
    }
}
