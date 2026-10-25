package org.telegram.ui;

import androidx.collection.LongSparseArray;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AdminLogFilterAlert2;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelAdminLogActivity$$ExternalSyntheticLambda8 implements AdminLogFilterAlert2.AdminLogFilterAlertDelegate {
    public final /* synthetic */ ChannelAdminLogActivity f$0;

    public /* synthetic */ ChannelAdminLogActivity$$ExternalSyntheticLambda8(ChannelAdminLogActivity r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Components.AdminLogFilterAlert2.AdminLogFilterAlertDelegate
    public final void didSelectRights(TLRPC.TL_channelAdminLogEventsFilter r1, LongSparseArray r2) {
        ChannelAdminLogActivity.m7183$r8$lambda$q28jYRD7osOF8J6OV96e_sqj20(this.f$0, r1, r2);
    }
}
