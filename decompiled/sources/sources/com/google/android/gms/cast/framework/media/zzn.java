package com.google.android.gms.cast.framework.media;

import android.util.SparseIntArray;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.CastUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class zzn extends RemoteMediaClient.Callback {
    final /* synthetic */ MediaQueue zza;

    public zzn(MediaQueue r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void onStatusUpdated() {
        MediaQueue r4 = this.zza;
        long r0 = r4.zze();
        if (r0 == r4.zza) goto L8;
        r4.zza = r0;
        r4.zza();
        if (r4.zza == 0) goto L9;
        r4.zzb();
        return;
    L9:
        return;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzb(int[] r2) {
        MediaQueue r1 = this.zza;
        List r22 = CastUtils.zzf(r2);
        if (r1.zzb.equals(r22) == false) goto L5;
        return;
    L5:
        r1.zzh();
        r1.zzd.evictAll();
        r1.zze.clear();
        r1.zzb = r22;
        r1.zzg();
        r1.zzj();
        r1.zzi();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzc(int[] r4, int r5) {
        MediaQueue r0 = this.zza;
        if (r5 != 0) goto L5;
        int r52 = r0.zzb.size();
    L9:
        int r02 = r4.length;
        MediaQueue r3 = this.zza;
        r3.zzh();
        r3.zzb.addAll(r52, CastUtils.zzf(r4));
        r3.zzg();
        r3.zzk(r52, r02);
        r3.zzi();
        return;
    L5:
        r52 = r0.zzc.get(r5, -1);
        if (r52 != (-1)) goto L9;
        r0.zzb();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzd(int[] r7) {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r7.length) goto L10;
        int r2 = r7[r1];
        MediaQueue r3 = this.zza;
        r3.zzd.remove(Integer.valueOf(r2));
        int r22 = r3.zzc.get(r2, -1);
        if (r22 == (-1)) goto L7;
        r0.add(Integer.valueOf(r22));
        r1 = r1 + 1;
        goto L4
    L7:
        r3.zzb();
        return;
    L10:
        Collections.sort(r0);
        MediaQueue r6 = this.zza;
        r6.zzh();
        r6.zzl(CastUtils.zze(r0));
        r6.zzi();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zze(int[] r8) {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r8.length) goto L11;
        int r2 = r8[r1];
        MediaQueue r3 = this.zza;
        r3.zzd.remove(Integer.valueOf(r2));
        SparseIntArray r4 = r3.zzc;
        int r6 = r4.get(r2, -1);
        if (r6 == (-1)) goto L7;
        r4.delete(r2);
        r0.add(Integer.valueOf(r6));
        r1 = r1 + 1;
        goto L4
    L7:
        r3.zzb();
        return;
    L11:
        if (r0.isEmpty() == false) goto L13;
        return;
    L13:
        Collections.sort(r0);
        MediaQueue r7 = this.zza;
        r7.zzh();
        r7.zzb.removeAll(CastUtils.zzf(r8));
        r7.zzg();
        r7.zzm(CastUtils.zze(r0));
        r7.zzi();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzf(MediaQueueItem[] r9) {
        HashSet r0 = new HashSet();
        MediaQueue r8 = this.zza;
        List r1 = r8.zze;
        r1.clear();
        int r2 = 0;
    L4:
        if (r2 >= r9.length) goto L10;
        MediaQueueItem r3 = r9[r2];
        int r5 = r3.getItemId();
        r8.zzd.put(Integer.valueOf(r5), r3);
        int r32 = r8.zzc.get(r5, -1);
        if (r32 == (-1)) goto L7;
        r0.add(Integer.valueOf(r32));
        r2 = r2 + 1;
        goto L4
    L7:
        r8.zzb();
        return;
    L10:
        Iterator r92 = r1.iterator();
    L12:
        if (r92.hasNext() == false) goto L16;
        int r22 = ((Integer) r92.next()).intValue();
        int r23 = r8.zzc.get(r22, -1);
        if (r23 == (-1)) goto L12;
        r0.add(Integer.valueOf(r23));
        goto L12
    L16:
        r1.clear();
        ArrayList r93 = new ArrayList(r0);
        Collections.sort(r93);
        r8.zzh();
        r8.zzl(CastUtils.zze(r93));
        r8.zzi();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzg(List r6, List r7, int r8) {
        ArrayList r0 = new ArrayList();
        if (r8 != 0) goto L5;
        int r82 = this.zza.zzb.size();
    L11:
        Iterator r72 = r7.iterator();
    L13:
        if (r72.hasNext() == false) goto L19;
        int r2 = ((Integer) r72.next()).intValue();
        MediaQueue r3 = this.zza;
        int r22 = r3.zzc.get(r2, -1);
        if (r22 == (-1)) goto L16;
        r0.add(Integer.valueOf(r22));
        goto L13
    L16:
        r3.zzb();
        return;
    L19:
        MediaQueue r5 = this.zza;
        r5.zzh();
        r5.zzb = r6;
        r5.zzg();
        r5.zzn(r0, r82);
        r5.zzi();
        return;
    L5:
        boolean r23 = r7.isEmpty();
        MediaQueue r32 = this.zza;
        if (r23 == false) goto L8;
        r32.zzo().w("Received a Queue Reordered message with an empty reordered items IDs list.", new Object[0]);
        r82 = -1;
        goto L11
    L8:
        SparseIntArray r24 = r32.zzc;
        r82 = r24.get(r8, -1);
        if (r82 != (-1)) goto L11;
        r82 = r24.get(((Integer) r7.get(0)).intValue(), -1);
        goto L11
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzh() {
        this.zza.zzb();
    }
}
