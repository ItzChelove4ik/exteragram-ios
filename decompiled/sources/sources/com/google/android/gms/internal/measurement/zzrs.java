package com.google.android.gms.internal.measurement;

import android.net.Uri;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.common.collect.Iterables;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzrs {
    private final zzsx zza;
    private final List zzb;
    private final List zzc;
    private final Uri zzd;
    private final Uri zze;

    public zzrs(zzrr r2) {
        this.zza = r2.zzf();
        this.zzb = r2.zzg();
        this.zzc = r2.zzh();
        this.zzd = r2.zzi();
        this.zze = r2.zzj();
    }

    public final zzsx zza() {
        return this.zza;
    }

    public final Uri zzb() {
        return this.zze;
    }

    public final List zzc(InputStream r4) {
        ArrayList r0 = new ArrayList();
        r0.add(r4);
        List r1 = this.zzc;
        if (r1.isEmpty() == true) goto L7;
        zzrp r42 = zzrp.zza(r1, this.zzd, r4);
        if (r42 == null) goto L7;
        r0.add(r42);
    L7:
        Iterator r3 = this.zzb.iterator();
        if (r3.hasNext() == true) goto L11;
        Collections.reverse(r0);
        return r0;
    L11:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3.next());
        InputStream r32 = (InputStream) Iterables.getLast(r0);
        throw null;
    }

    public final List zzd(OutputStream r4) {
        ArrayList r0 = new ArrayList();
        r0.add(r4);
        List r1 = this.zzc;
        if (r1.isEmpty() == true) goto L7;
        zzrq r42 = zzrq.zza(r1, this.zzd, r4);
        if (r42 == null) goto L7;
        r0.add(r42);
    L7:
        Iterator r3 = this.zzb.iterator();
        if (r3.hasNext() == true) goto L11;
        Collections.reverse(r0);
        return r0;
    L11:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3.next());
        OutputStream r32 = (OutputStream) Iterables.getLast(r0);
        throw null;
    }

    public final boolean zze() {
        if (this.zzb.isEmpty() == true) goto L6;
        return true;
    L6:
        return false;
    }
}
