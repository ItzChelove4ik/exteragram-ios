package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Adapters.FiltersView;

/* loaded from: classes6.dex */
public final /* synthetic */ class FilteredSearchView$$ExternalSyntheticLambda4 implements Utilities.Callback2 {
    public final /* synthetic */ FilteredSearchView f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ FiltersView.MediaFilterData f$5;
    public final /* synthetic */ long f$6;
    public final /* synthetic */ long f$7;
    public final /* synthetic */ ArrayList f$8;
    public final /* synthetic */ ArrayList f$9;

    public /* synthetic */ FilteredSearchView$$ExternalSyntheticLambda4(FilteredSearchView r1, int r2, String r3, int r4, boolean r5, FiltersView.MediaFilterData r6, long r7, long r9, ArrayList r11, ArrayList r12) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r9;
        this.f$8 = r11;
        this.f$9 = r12;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r15, Object r16) {
        FilteredSearchView.$r8$lambda$UYc78hbV7URw5CtN_mHhsT5PrTI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, (TLRPC.messages_Messages) r15, (TLRPC.TL_error) r16);
    }
}
