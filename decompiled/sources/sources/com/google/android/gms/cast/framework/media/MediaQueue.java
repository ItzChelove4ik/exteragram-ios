package com.google.android.gms.cast.framework.media;

import android.os.Handler;
import android.os.Looper;
import android.util.LruCache;
import android.util.SparseIntArray;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzfk;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

/* loaded from: classes4.dex */
public class MediaQueue {
    long zza;
    List zzb;
    final SparseIntArray zzc;
    LruCache zzd;
    final List zze;
    final Deque zzf;
    private final Logger zzg;
    private final RemoteMediaClient zzh;
    private final int zzi;
    private final Handler zzj;
    private final TimerTask zzk;
    private PendingResult zzl;
    private PendingResult zzm;
    private final Set zzn;

    public static abstract class Callback {
        public Callback() {
        }

        public void itemsInsertedInRange(int r1, int r2) {
        }

        public void itemsReloaded() {
        }

        public void itemsRemovedAtIndexes(int[] r1) {
        }

        public void itemsReorderedAtIndexes(List<Integer> r1, int r2) {
        }

        public void itemsUpdatedAtIndexes(int[] r1) {
        }

        public void mediaQueueChanged() {
        }

        public void mediaQueueWillChange() {
        }
    }

    public MediaQueue(RemoteMediaClient r2, int r3, int r4) {
        this.zzn = Collections.synchronizedSet(new HashSet());
        this.zzg = new Logger("MediaQueue");
        this.zzh = r2;
        this.zzi = Math.max(20, 1);
        this.zzb = new ArrayList();
        this.zzc = new SparseIntArray();
        this.zze = new ArrayList();
        this.zzf = new ArrayDeque(20);
        this.zzj = new zzfk(Looper.getMainLooper());
        this.zzk = new zzj(this);
        r2.registerCallback(new zzn(this));
        zzp(20);
        this.zza = zzu();
        zzb();
    }

    private final void zzp(int r2) {
        this.zzd = new zzk(this, r2);
    }

    private final void zzq() {
        zzr();
        this.zzj.postDelayed(this.zzk, 500);
    }

    private final void zzr() {
        this.zzj.removeCallbacks(this.zzk);
    }

    private final void zzs() {
        PendingResult r0 = this.zzm;
        if (r0 == null) goto L6;
        r0.cancel();
        this.zzm = null;
        return;
    }

    private final void zzt() {
        PendingResult r0 = this.zzl;
        if (r0 == null) goto L6;
        r0.cancel();
        this.zzl = null;
        return;
    }

    private final long zzu() {
        MediaStatus r2 = this.zzh.getMediaStatus();
        if (r2 != null) goto L5;
        return 0;
    L5:
        if (r2.zzc() == false) goto L8;
        return 0;
    L8:
        return r2.zza();
    }

    private final void zzv() {
        Set r2 = this.zzn;
        monitor-enter(r2);
        Iterator r0 = r2.iterator();     // Catch: Throwable -> L8
    L6:
        if (r0.hasNext() == false) goto L10;
        ((Callback) r0.next()).mediaQueueWillChange();     // Catch: Throwable -> L8
        goto L6
    L10:
        monitor-exit(r2);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    private final void zzw() {
        Set r2 = this.zzn;
        monitor-enter(r2);
        Iterator r0 = r2.iterator();     // Catch: Throwable -> L8
    L6:
        if (r0.hasNext() == false) goto L10;
        ((Callback) r0.next()).mediaQueueChanged();     // Catch: Throwable -> L8
        goto L6
    L10:
        monitor-exit(r2);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    private final void zzx() {
        Set r2 = this.zzn;
        monitor-enter(r2);
        Iterator r0 = r2.iterator();     // Catch: Throwable -> L8
    L6:
        if (r0.hasNext() == false) goto L10;
        ((Callback) r0.next()).itemsReloaded();     // Catch: Throwable -> L8
        goto L6
    L10:
        monitor-exit(r2);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    private final void zzy(int[] r3) {
        Set r2 = this.zzn;
        monitor-enter(r2);
        Iterator r0 = r2.iterator();     // Catch: Throwable -> L8
    L6:
        if (r0.hasNext() == false) goto L10;
        ((Callback) r0.next()).itemsUpdatedAtIndexes(r3);     // Catch: Throwable -> L8
        goto L6
    L10:
        monitor-exit(r2);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public PendingResult<RemoteMediaClient.MediaChannelResult> fetchMoreItemsRelativeToIndex(int r5, int r6, int r7) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (this.zza == 0) goto L5;
        int r52 = itemIdAtIndex(r5);
        if (r52 != 0) goto L11;
        return RemoteMediaClient.zzn(CastStatusCodes.INVALID_REQUEST, "index out of bound");
    L11:
        return this.zzh.zze(r52, r6, r7);
    L5:
        return RemoteMediaClient.zzn(2100, "No active media session");
    }

    public MediaQueueItem getItemAtIndex(int r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return getItemAtIndex(r2, true);
    }

    public MediaQueueItem getItemAtIndex(int r4, boolean r5) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (r4 >= 0) goto L5;
        return null;
    L5:
        if (r4 >= this.zzb.size()) goto L20;
        Integer r42 = (Integer) this.zzb.get(r4);
        r42.intValue();
        MediaQueueItem r0 = (MediaQueueItem) this.zzd.get(r42);
        if (r0 != null) goto L16;
        if (r5 == false) goto L16;
        Deque r52 = this.zzf;
        if (r52.contains(r42) == true) goto L16;
    L12:
        int r1 = this.zzi;
        if (r52.size() < r1) goto L15;
        r52.removeFirst();
        goto L12
    L15:
        r52.add(r42);
        zzq();
    L16:
        return r0;
    L20:
        return null;
    }

    public int getItemCount() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzb.size();
    }

    public int[] getItemIds() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return CastUtils.zze(this.zzb);
    }

    public int indexOfItemWithId(int r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzc.get(r2, -1);
    }

    public int itemIdAtIndex(int r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (r2 >= 0) goto L5;
        return 0;
    L5:
        if (r2 < this.zzb.size()) goto L7;
        return 0;
    L7:
        return ((Integer) this.zzb.get(r2)).intValue();
    }

    public void registerCallback(Callback r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        this.zzn.add(r2);
    }

    public void setCacheCapacity(int r7) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        LruCache r0 = this.zzd;
        ArrayList r1 = new ArrayList();
        zzp(r7);
        int r2 = r0.size();
        Iterator r02 = r0.snapshot().entrySet().iterator();
    L4:
        if (r02.hasNext() == false) goto L13;
        Map.Entry r3 = (Map.Entry) r02.next();
        if (r2 <= r7) goto L10;
        int r32 = this.zzc.get(((Integer) r3.getKey()).intValue(), -1);
        if (r32 == (-1)) goto L11;
        r1.add(Integer.valueOf(r32));
    L11:
        r2 = r2 - 1;
        goto L4
    L10:
        this.zzd.put((Integer) r3.getKey(), (MediaQueueItem) r3.getValue());
        goto L11
    L13:
        if (r1.isEmpty() == false) goto L15;
        return;
    L15:
        Collections.sort(r1);
        zzv();
        zzy(CastUtils.zze(r1));
        zzw();
    }

    public void unregisterCallback(Callback r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        this.zzn.remove(r2);
    }

    public final void zza() {
        zzv();
        this.zzb.clear();
        this.zzc.clear();
        this.zzd.evictAll();
        this.zze.clear();
        zzr();
        this.zzf.clear();
        zzs();
        zzt();
        zzx();
        zzw();
    }

    public final void zzb() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (this.zza != 0) goto L6;
        return;
    L6:
        if (this.zzm != null) goto L9;
        zzs();
        zzt();
        PendingResult r0 = this.zzh.zzc();
        this.zzm = r0;
        r0.setResultCallback(new zzm(this));
        return;
    }

    public final void zzc(RemoteMediaClient.MediaChannelResult r3) {
        Status r32 = r3.getStatus();
        int r0 = r32.getStatusCode();
        if (r0 == 0) goto L5;
        this.zzg.w(String.format("Error fetching queue items, statusCode=%s, statusMessage=%s", new Object[]{Integer.valueOf(r0), r32.getStatusMessage()}), new Object[0]);
    L5:
        this.zzl = null;
        if (this.zzf.isEmpty() == true) goto L9;
        zzq();
        return;
    }

    public final void zzd(RemoteMediaClient.MediaChannelResult r3) {
        Status r32 = r3.getStatus();
        int r0 = r32.getStatusCode();
        if (r0 == 0) goto L5;
        this.zzg.w(String.format("Error fetching queue item ids, statusCode=%s, statusMessage=%s", new Object[]{Integer.valueOf(r0), r32.getStatusMessage()}), new Object[0]);
    L5:
        this.zzm = null;
        if (this.zzf.isEmpty() == true) goto L9;
        zzq();
        return;
    }

    public final /* synthetic */ long zze() {
        return zzu();
    }

    public final /* synthetic */ void zzf() {
        Deque r0 = this.zzf;
        if (r0.isEmpty() == false) goto L6;
        return;
    L6:
        if (this.zzl == null) goto L8;
        return;
    L8:
        if (this.zza == 0) goto L12;
        PendingResult r1 = this.zzh.zzd(CastUtils.zze(r0));
        this.zzl = r1;
        r1.setResultCallback(new zzl(this));
        r0.clear();
        return;
    }

    public final /* synthetic */ void zzg() {
        SparseIntArray r0 = this.zzc;
        r0.clear();
        int r1 = 0;
    L4:
        if (r1 >= this.zzb.size()) goto L6;
        r0.put(((Integer) this.zzb.get(r1)).intValue(), r1);
        r1 = r1 + 1;
        goto L4
    }

    public final /* synthetic */ void zzh() {
        zzv();
    }

    public final /* synthetic */ void zzi() {
        zzw();
    }

    public final /* synthetic */ void zzj() {
        zzx();
    }

    public final /* synthetic */ void zzk(int r3, int r4) {
        Set r2 = this.zzn;
        monitor-enter(r2);
        Iterator r0 = r2.iterator();     // Catch: Throwable -> L8
    L6:
        if (r0.hasNext() == false) goto L10;
        ((Callback) r0.next()).itemsInsertedInRange(r3, r4);     // Catch: Throwable -> L8
        goto L6
    L10:
        monitor-exit(r2);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ void zzl(int[] r1) {
        zzy(r1);
    }

    public final /* synthetic */ void zzm(int[] r3) {
        Set r2 = this.zzn;
        monitor-enter(r2);
        Iterator r0 = r2.iterator();     // Catch: Throwable -> L8
    L6:
        if (r0.hasNext() == false) goto L10;
        ((Callback) r0.next()).itemsRemovedAtIndexes(r3);     // Catch: Throwable -> L8
        goto L6
    L10:
        monitor-exit(r2);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ void zzn(List r3, int r4) {
        Set r2 = this.zzn;
        monitor-enter(r2);
        Iterator r0 = r2.iterator();     // Catch: Throwable -> L8
    L6:
        if (r0.hasNext() == false) goto L10;
        ((Callback) r0.next()).itemsReorderedAtIndexes(r3, r4);     // Catch: Throwable -> L8
        goto L6
    L10:
        monitor-exit(r2);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ Logger zzo() {
        return this.zzg;
    }
}
