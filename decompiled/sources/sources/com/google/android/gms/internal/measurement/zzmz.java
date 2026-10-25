package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import java.io.File;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzmz {
    private final BaseEncoding zza;
    private final Supplier zzb;
    private final Supplier zzc;

    public zzmz(zzacr r1, String r2, String r3) {
        this.zza = BaseEncoding.base64Url();
        this.zzb = Suppliers.memoize(new zzmy(this, r1));
        this.zzc = Suppliers.memoize(new zzmx(this, r2, _UrlKt.FRAGMENT_ENCODE_SET));
    }

    public final File zza() {
        String r0 = (String) this.zzb.get();
        String r4 = (String) this.zzc.get();
        int r2 = String.valueOf(r0).length();
        int r22 = r2 + 1;
        StringBuilder r3 = new StringBuilder((r22 + String.valueOf(r4).length()) + 3);
        r3.append(r0);
        r3.append("/");
        r3.append(r4);
        r3.append(".pb");
        return new File(r3.toString());
    }

    public final /* synthetic */ String zzb(zzacr r1) {
        return this.zza.encode(r1.zzm());
    }

    public final /* synthetic */ String zzc(String r1, String r2) {
        byte[] r12 = Hashing.murmur3_128().newHasher().putBytes(r1.getBytes()).putByte((byte) 0).putBytes(_UrlKt.FRAGMENT_ENCODE_SET.getBytes()).hash().asBytes();
        return this.zza.encode(r12);
    }
}
