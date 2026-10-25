package com.google.android.gms.internal.measurement;

import android.net.Uri;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.text.TextUtils;
import android.util.Log;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zzru {
    private final Map zza;
    private final Map zzb;
    private final List zzc;

    public zzru(List r5) {
        List r0 = Collections.EMPTY_LIST;
        this.zza = new HashMap();
        this.zzb = new HashMap();
        this.zzc = new ArrayList();
        Iterator r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L13;
        zzsx r1 = (zzsx) r52.next();
        if (TextUtils.isEmpty(r1.zzc()) == true) goto L7;
        zzsx r2 = (zzsx) this.zza.put(r1.zzc(), r1);
        if (r2 == null) goto L4;
        String r53 = r2.getClass().getCanonicalName();
        String r02 = r1.getClass().getCanonicalName();
        int r12 = String.valueOf(r53).length();
        StringBuilder r22 = new StringBuilder((r12 + 30) + String.valueOf(r02).length());
        r22.append("Cannot override Backend ");
        r22.append(r53);
        r22.append(" with ");
        r22.append(r02);
        throw new IllegalArgumentException(r22.toString());
    L7:
        Log.w("MobStore.FileStorage", "Cannot register backend, name empty");
        goto L4
    L13:
        Iterator r54 = r0.iterator();
        if (r54.hasNext() == true) goto L17;
        this.zzc.addAll(r0);
        return;
    L17:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r54.next());
        throw null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final zzrs zze(Uri r8) {
        ImmutableList.Builder r0 = ImmutableList.builder();
        ImmutableList.Builder r1 = ImmutableList.builder();
        String r2 = r8.getEncodedFragment();
        if (TextUtils.isEmpty(r2) == false) goto L5;
    L8:
        ImmutableList r22 = ImmutableList.of();
    L9:
        int r3 = r22.size();
        int r5 = 0;
    L10:
        if (r5 >= r3) goto L12;
        r1.add(zzsp.zza((String) r22.get(r5)));
        r5 = r5 + 1;
        goto L10
    L12:
        ImmutableList r12 = r1.build();
        if (r12.size() > 0) goto L31;
        ImmutableList r02 = r0.build().reverse();
        zzrr r13 = new zzrr(null);
        String r32 = r8.getScheme();
        zzsx r4 = (zzsx) this.zza.get(r32);
        if (r4 == null) goto L30;
        r13.zza(r4);
        r13.zzc(this.zzc);
        r13.zzb(r02);
        r13.zze(r8);
        if (r02.isEmpty() == true) goto L27;
        ArrayList r7 = new ArrayList(r8.getPathSegments());
        if (r7.isEmpty() == true) goto L27;
        if (r8.getPath().endsWith("/") == true) goto L27;
        String r33 = (String) r7.get(r7.size() - 1);
        ListIterator<E> r03 = r02.listIterator(r02.size());
    L24:
        if (r03.hasPrevious() == false) goto L26;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r03.previous());
        goto L24
    L26:
        r7.set(r7.size() - 1, r33);
        r8 = r8.buildUpon().path(TextUtils.join("/", r7)).encodedFragment(null).build();
    L27:
        r13.zzd(r8);
        return new zzrs(r13);
    L30:
        throw new zzsk(String.format("Requested backend isn't registered: %s", new Object[]{r32}));
    L31:
        String r04 = (String) r12.get(0);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.zzb.get(r04));
        String r82 = String.valueOf(r8);
        StringBuilder r23 = new StringBuilder((String.valueOf(r04).length() + 40) + r82.length());
        r23.append("Requested transform isn't registered: ");
        r23.append(r04);
        r23.append(": ");
        r23.append(r82);
        throw new zzsk(r23.toString());
    L5:
        if (r2.startsWith("transform=") == false) goto L8;
        r22 = ImmutableList.copyOf(Splitter.on("+").omitEmptyStrings().split(r2.substring(10)));
        goto L9
    }

    public final Object zza(Uri r1, zzrt r2) {
        return r2.zza(zze(r1));
    }

    public final void zzb(Uri r1) {
        zzrs r0 = zze(r1);
        r0.zza().zzk(r0.zzb());
    }

    public final boolean zzc(Uri r1) {
        zzrs r0 = zze(r1);
        return r0.zza().zze(r0.zzb());
    }

    public final void zzd(Uri r2, Uri r3) {
        zzrs r22 = zze(r2);
        zzrs r1 = zze(r3);
        if (r22.zza() != r1.zza()) goto L7;
        r22.zza().zzl(r22.zzb(), r1.zzb());
        return;
    L7:
        throw new zzsk("Cannot rename file across backends");
    }
}
