package androidx.privacysandbox.ads.adservices.measurement;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/privacysandbox/ads/adservices/measurement/MeasurementManagerApi31Ext9Impl;", "it", "Landroid/content/Context;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class MeasurementManager$Companion$obtain$1 extends Lambda implements Function1<Context, MeasurementManagerApi31Ext9Impl> {
    final /* synthetic */ Context $context;

    public MeasurementManager$Companion$obtain$1(Context r1) {
        this.$context = r1;
        super(1);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final MeasurementManagerApi31Ext9Impl invoke2(Context r1) {
        return new MeasurementManagerApi31Ext9Impl(this.$context);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ MeasurementManagerApi31Ext9Impl invoke(Context r1) {
        return invoke2(r1);
    }
}
