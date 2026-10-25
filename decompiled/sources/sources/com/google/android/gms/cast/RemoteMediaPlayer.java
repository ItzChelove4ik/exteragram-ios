package com.google.android.gms.cast;

import android.annotation.SuppressLint;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

@SuppressLint({"MissingRemoteException"})
@Deprecated
/* loaded from: classes4.dex */
public class RemoteMediaPlayer implements Cast.MessageReceivedCallback {
    public static final String NAMESPACE = null;
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    public static final int STATUS_CANCELED = 2101;
    public static final int STATUS_FAILED = 2100;
    public static final int STATUS_REPLACED = 2103;
    public static final int STATUS_SUCCEEDED = 0;
    public static final int STATUS_TIMED_OUT = 2102;
    private final Object zza;
    private final com.google.android.gms.cast.internal.zzar zzb;
    private final zzcz zzc;
    private OnPreloadStatusUpdatedListener zzd;
    private OnQueueStatusUpdatedListener zze;
    private OnMetadataUpdatedListener zzf;
    private OnStatusUpdatedListener zzg;

    @Deprecated
    public interface MediaChannelResult extends Result {
        JSONObject getCustomData();

        @Override // com.google.android.gms.common.api.Result
        /* synthetic */ Status getStatus();
    }

    @Deprecated
    public interface OnMetadataUpdatedListener {
        void onMetadataUpdated();
    }

    @Deprecated
    public interface OnPreloadStatusUpdatedListener {
        void onPreloadStatusUpdated();
    }

    @Deprecated
    public interface OnQueueStatusUpdatedListener {
        void onQueueStatusUpdated();
    }

    @Deprecated
    public interface OnStatusUpdatedListener {
        void onStatusUpdated();
    }

    static {
        NAMESPACE = com.google.android.gms.cast.internal.zzar.zzb;
    }

    public RemoteMediaPlayer() {
        com.google.android.gms.cast.internal.zzar r0 = new com.google.android.gms.cast.internal.zzar(null);
        this.zza = new Object();
        this.zzb = r0;
        r0.zzi(new zzcl(this));
        zzcz r1 = new zzcz(this);
        this.zzc = r1;
        r0.zze(r1);
    }

    public long getApproximateStreamPosition() {
        Object r0 = this.zza;
        monitor-enter(r0);
        long r1 = this.zzb.zzv();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public MediaInfo getMediaInfo() {
        Object r0 = this.zza;
        monitor-enter(r0);
        MediaInfo r1 = this.zzb.zzB();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public MediaStatus getMediaStatus() {
        Object r0 = this.zza;
        monitor-enter(r0);
        MediaStatus r1 = this.zzb.zzA();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public String getNamespace() {
        return this.zzb.zzd();
    }

    public long getStreamDuration() {
        Object r0 = this.zza;
        monitor-enter(r0);
        long r1 = this.zzb.zzz();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient r9, MediaInfo r10) {
        return load(r9, r10, true, -1, null, null);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient r9, MediaInfo r10, boolean r11) {
        return load(r9, r10, r11, -1, null, null);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient r9, MediaInfo r10, boolean r11, long r12) {
        return load(r9, r10, r11, r12, null, null);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient r9, MediaInfo r10, boolean r11, long r12, JSONObject r14) {
        return load(r9, r10, r11, r12, null, r14);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient r10, MediaInfo r11, boolean r12, long r13, long[] r15, JSONObject r16) {
        return r10.execute(new zzcq(this, r10, r11, r12, r13, r15, r16));
    }

    @Override // com.google.android.gms.cast.Cast.MessageReceivedCallback
    public void onMessageReceived(CastDevice r1, String r2, String r3) {
        this.zzb.zzL(r3);
    }

    public PendingResult<MediaChannelResult> pause(GoogleApiClient r2) {
        return pause(r2, null);
    }

    public PendingResult<MediaChannelResult> pause(GoogleApiClient r2, JSONObject r3) {
        return r2.execute(new zzcr(this, r2, r3));
    }

    public PendingResult<MediaChannelResult> play(GoogleApiClient r2) {
        return play(r2, null);
    }

    public PendingResult<MediaChannelResult> play(GoogleApiClient r2, JSONObject r3) {
        return r2.execute(new zzct(this, r2, r3));
    }

    public PendingResult<MediaChannelResult> queueAppendItem(GoogleApiClient r2, MediaQueueItem r3, JSONObject r4) {
        return queueInsertItems(r2, new MediaQueueItem[]{r3}, 0, r4);
    }

    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(GoogleApiClient r9, MediaQueueItem r10, int r11, long r12, JSONObject r14) {
        return r9.execute(new zzcf(this, r9, r10, r11, r12, r14));
    }

    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(GoogleApiClient r8, MediaQueueItem r9, int r10, JSONObject r11) {
        return queueInsertAndPlayItem(r8, r9, r10, -1, r11);
    }

    public PendingResult<MediaChannelResult> queueInsertItems(GoogleApiClient r7, MediaQueueItem[] r8, int r9, JSONObject r10) {
        return r7.execute(new zzce(this, r7, r8, r9, r10));
    }

    public PendingResult<MediaChannelResult> queueJumpToItem(GoogleApiClient r8, int r9, long r10, JSONObject r12) {
        return r8.execute(new zzco(this, r8, r9, r10, r12));
    }

    public PendingResult<MediaChannelResult> queueJumpToItem(GoogleApiClient r7, int r8, JSONObject r9) {
        return queueJumpToItem(r7, r8, -1, r9);
    }

    public PendingResult<MediaChannelResult> queueLoad(GoogleApiClient r10, MediaQueueItem[] r11, int r12, int r13, long r14, JSONObject r16) {
        return r10.execute(new zzcd(this, r10, r11, r12, r13, r14, r16));
    }

    public PendingResult<MediaChannelResult> queueLoad(GoogleApiClient r9, MediaQueueItem[] r10, int r11, int r12, JSONObject r13) {
        return queueLoad(r9, r10, r11, r12, -1, r13);
    }

    public PendingResult<MediaChannelResult> queueMoveItemToNewIndex(GoogleApiClient r7, int r8, int r9, JSONObject r10) {
        return r7.execute(new zzcp(this, r7, r8, r9, r10));
    }

    public PendingResult<MediaChannelResult> queueNext(GoogleApiClient r2, JSONObject r3) {
        return r2.execute(new zzck(this, r2, r3));
    }

    public PendingResult<MediaChannelResult> queuePrev(GoogleApiClient r2, JSONObject r3) {
        return r2.execute(new zzcj(this, r2, r3));
    }

    public PendingResult<MediaChannelResult> queueRemoveItem(GoogleApiClient r2, int r3, JSONObject r4) {
        return r2.execute(new zzcn(this, r2, r3, r4));
    }

    public PendingResult<MediaChannelResult> queueRemoveItems(GoogleApiClient r2, int[] r3, JSONObject r4) {
        return r2.execute(new zzch(this, r2, r3, r4));
    }

    public PendingResult<MediaChannelResult> queueReorderItems(GoogleApiClient r7, int[] r8, int r9, JSONObject r10) {
        return r7.execute(new zzci(this, r7, r8, r9, r10));
    }

    public PendingResult<MediaChannelResult> queueSetRepeatMode(GoogleApiClient r2, int r3, JSONObject r4) {
        return r2.execute(new zzcm(this, r2, r3, r4));
    }

    public PendingResult<MediaChannelResult> queueUpdateItems(GoogleApiClient r2, MediaQueueItem[] r3, JSONObject r4) {
        return r2.execute(new zzcg(this, r2, r3, r4));
    }

    public PendingResult<MediaChannelResult> requestStatus(GoogleApiClient r2) {
        return r2.execute(new zzcx(this, r2));
    }

    public PendingResult<MediaChannelResult> seek(GoogleApiClient r7, long r8) {
        return seek(r7, r8, 0, null);
    }

    public PendingResult<MediaChannelResult> seek(GoogleApiClient r7, long r8, int r10) {
        return seek(r7, r8, r10, null);
    }

    public PendingResult<MediaChannelResult> seek(GoogleApiClient r8, long r9, int r11, JSONObject r12) {
        return r8.execute(new zzcu(this, r8, r9, r11, r12));
    }

    public PendingResult<MediaChannelResult> setActiveMediaTracks(GoogleApiClient r2, long[] r3) {
        return r2.execute(new zzcb(this, r2, r3));
    }

    public void setOnMetadataUpdatedListener(OnMetadataUpdatedListener r1) {
        this.zzf = r1;
    }

    public void setOnPreloadStatusUpdatedListener(OnPreloadStatusUpdatedListener r1) {
        this.zzd = r1;
    }

    public void setOnQueueStatusUpdatedListener(OnQueueStatusUpdatedListener r1) {
        this.zze = r1;
    }

    public void setOnStatusUpdatedListener(OnStatusUpdatedListener r1) {
        this.zzg = r1;
    }

    public PendingResult<MediaChannelResult> setStreamMute(GoogleApiClient r2, boolean r3) {
        return setStreamMute(r2, r3, null);
    }

    public PendingResult<MediaChannelResult> setStreamMute(GoogleApiClient r2, boolean r3, JSONObject r4) {
        return r2.execute(new zzcw(this, r2, r3, r4));
    }

    public PendingResult<MediaChannelResult> setStreamVolume(GoogleApiClient r2, double r3) {
        return setStreamVolume(r2, r3, null);
    }

    public PendingResult<MediaChannelResult> setStreamVolume(GoogleApiClient r7, double r8, JSONObject r10) {
        return r7.execute(new zzcv(this, r7, r8, r10));
    }

    public PendingResult<MediaChannelResult> setTextTrackStyle(GoogleApiClient r2, TextTrackStyle r3) {
        return r2.execute(new zzcc(this, r2, r3));
    }

    public PendingResult<MediaChannelResult> stop(GoogleApiClient r2) {
        return stop(r2, null);
    }

    public PendingResult<MediaChannelResult> stop(GoogleApiClient r2, JSONObject r3) {
        return r2.execute(new zzcs(this, r2, r3));
    }

    public final /* synthetic */ int zza(int r4) {
        MediaStatus r3 = getMediaStatus();
        if (r3 != null) goto L5;
        return -1;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= r3.getQueueItemCount()) goto L15;
        MediaQueueItem r2 = r3.getQueueItem(r1);
        if (r2 == null) goto L14;
        if (r2.getItemId() != r4) goto L14;
        return r1;
    L14:
        r1 = r1 + 1;
        goto L7
    L15:
        return -1;
    }

    public final /* synthetic */ void zzb() {
        OnStatusUpdatedListener r0 = this.zzg;
        if (r0 == null) goto L6;
        r0.onStatusUpdated();
        return;
    }

    public final /* synthetic */ void zzc() {
        OnMetadataUpdatedListener r0 = this.zzf;
        if (r0 == null) goto L6;
        r0.onMetadataUpdated();
        return;
    }

    public final /* synthetic */ void zzd() {
        OnQueueStatusUpdatedListener r0 = this.zze;
        if (r0 == null) goto L6;
        r0.onQueueStatusUpdated();
        return;
    }

    public final /* synthetic */ void zze() {
        OnPreloadStatusUpdatedListener r0 = this.zzd;
        if (r0 == null) goto L6;
        r0.onPreloadStatusUpdated();
        return;
    }

    public final /* synthetic */ Object zzf() {
        return this.zza;
    }

    public final /* synthetic */ com.google.android.gms.cast.internal.zzar zzg() {
        return this.zzb;
    }

    public final /* synthetic */ zzcz zzh() {
        return this.zzc;
    }
}
