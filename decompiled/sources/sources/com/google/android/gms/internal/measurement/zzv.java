package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzv extends zzai {
    private final zzz zza;

    public zzv(zzz r2) {
        super("internal.registerCallback");
        this.zza = r2;
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzao zza(zzg r5, List r6) {
        zzh.zza(this.zzd, 3, r6);
        String r0 = r5.zza((zzao) r6.get(0)).zzc();
        zzao r1 = r5.zza((zzao) r6.get(1));
        if ((r1 instanceof zzan) == false) goto L18;
        zzao r52 = r5.zza((zzao) r6.get(2));
        if ((r52 instanceof zzal) == false) goto L16;
        zzal r53 = (zzal) r52;
        if (r53.zzj("type") == false) goto L14;
        String r62 = r53.zzk("type").zzc();
        if (r53.zzj(PluginsConstants.MenuItemProperties.PRIORITY) == false) goto L11;
        int r54 = zzh.zzg(r53.zzk(PluginsConstants.MenuItemProperties.PRIORITY).zzd().doubleValue());
    L12:
        this.zza.zza(r0, r54, (zzan) r1, r62);
        return zzao.zzf;
    L11:
        r54 = 1000;
        goto L12
    L14:
        f$$ExternalSyntheticBUOutline1.m("Undefined rule type");
        return null;
    L16:
        f$$ExternalSyntheticBUOutline1.m("Invalid callback params");
        return null;
    L18:
        f$$ExternalSyntheticBUOutline1.m("Invalid callback type");
        return null;
    }
}
