package com.google.android.gms.cast.framework.media;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.cast.AdBreakInfo;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLoadOptions;
import com.google.android.gms.cast.MediaLoadRequestData;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.SessionState;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzfk;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class RemoteMediaClient implements Cast.MessageReceivedCallback {
    public static final String NAMESPACE = null;
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    public static final int STATUS_FAILED = 2100;
    public static final int STATUS_REPLACED = 2103;
    public static final int STATUS_SUCCEEDED = 0;
    private static final Logger zza = null;
    private final Object zzb;
    private final Handler zzc;
    private final com.google.android.gms.cast.internal.zzar zzd;
    private final zzax zze;

    @NotOnlyInitialized
    private final MediaQueue zzf;
    private com.google.android.gms.cast.zzq zzg;
    private TaskCompletionSource zzh;
    private final List zzi;
    private final List zzj;
    private final Map zzk;
    private final Map zzl;
    private ParseAdsInfoCallback zzm;

    public static abstract class Callback {
        public Callback() {
        }

        public void onAdBreakStatusUpdated() {
        }

        public void onMediaError(MediaError r1) {
        }

        public void onMetadataUpdated() {
        }

        public void onPreloadStatusUpdated() {
        }

        public void onQueueStatusUpdated() {
        }

        public void onSendingRemoteMediaRequest() {
        }

        public void onStatusUpdated() {
        }

        public void zza(String r1, long r2, int r4, long r5, long r7) {
        }

        public void zzb(int[] r1) {
        }

        public void zzc(int[] r1, int r2) {
        }

        public void zzd(int[] r1) {
        }

        public void zze(int[] r1) {
        }

        public void zzf(MediaQueueItem[] r1) {
        }

        public void zzg(List r1, List r2, int r3) {
        }

        public void zzh() {
        }
    }

    @Deprecated
    public interface Listener {
        void onAdBreakStatusUpdated();

        void onMetadataUpdated();

        void onPreloadStatusUpdated();

        void onQueueStatusUpdated();

        void onSendingRemoteMediaRequest();

        void onStatusUpdated();
    }

    public interface MediaChannelResult extends Result {
        JSONObject getCustomData();

        MediaError getMediaError();

        @Override // com.google.android.gms.common.api.Result
        /* synthetic */ Status getStatus();
    }

    public interface ParseAdsInfoCallback {
        List<AdBreakInfo> parseAdBreaksFromMediaStatus(MediaStatus r1);

        boolean parseIsPlayingAdFromMediaStatus(MediaStatus r1);
    }

    public interface ProgressListener {
        void onProgressUpdated(long r1, long r3);
    }

    static {
        zza = new Logger("RemoteMediaClient");
        NAMESPACE = com.google.android.gms.cast.internal.zzar.zzb;
    }

    public RemoteMediaClient(com.google.android.gms.cast.internal.zzar r4) {
        this.zzi = new CopyOnWriteArrayList();
        this.zzj = new CopyOnWriteArrayList();
        this.zzk = new ConcurrentHashMap();
        this.zzl = new ConcurrentHashMap();
        this.zzb = new Object();
        this.zzc = new zzfk(Looper.getMainLooper());
        zzax r0 = new zzax(this);
        this.zze = r0;
        com.google.android.gms.cast.internal.zzar r42 = (com.google.android.gms.cast.internal.zzar) Preconditions.checkNotNull(r4);
        this.zzd = r42;
        r42.zzi(new zzbe(this, null));
        r42.zze(r0);
        this.zzf = new MediaQueue(this, 20, 20);
    }

    public static PendingResult zzn(int r2, String r3) {
        zzaz r0 = new zzaz();
        r0.setResult(new zzay(r0, new Status(r2, r3)));
        return r0;
    }

    public static /* synthetic */ Logger zzq() {
        return zza;
    }

    private final boolean zzx() {
        if (this.zzg == null) goto L6;
        return true;
    L6:
        return false;
    }

    private final void zzy(Set r6) {
        HashSet r0 = new HashSet(r6);
        if (isPlaying() == false) goto L5;
    L25:
        Iterator r62 = r0.iterator();
    L27:
        if (r62.hasNext() == false) goto L36;
        ((ProgressListener) r62.next()).onProgressUpdated(getApproximateStreamPosition(), getStreamDuration());
        goto L27
    L36:
        return;
    L5:
        if (isPaused() == true) goto L25;
        if (isBuffering() == true) goto L25;
        if (zzi() == true) goto L25;
        if (isLoadingNextItem() == false) goto L21;
        MediaQueueItem r5 = getLoadingItem();
        if (r5 == null) goto L29;
        MediaInfo r52 = r5.getMedia();
        if (r52 == null) goto L33;
        Iterator r63 = r0.iterator();
    L19:
        if (r63.hasNext() == false) goto L34;
        ((ProgressListener) r63.next()).onProgressUpdated(0, r52.getStreamDuration());
        goto L19
    L34:
        return;
    L33:
        return;
    L29:
        return;
    L21:
        Iterator r53 = r0.iterator();
    L23:
        if (r53.hasNext() == false) goto L35;
        ((ProgressListener) r53.next()).onProgressUpdated(0, 0);
        goto L23
    }

    private static final zzbc zzz(zzbc r2) {
        r2.zzc();     // Catch: Throwable -> L4 IllegalArgumentException -> L6
        return r2;
    L6:
        e = move-exception;
        throw e;
    L4:
        r2.setResult(new zzbb(r2, new Status(2100)));
        return r2;
    }

    @Deprecated
    public void addListener(Listener r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (r2 == null) goto L6;
        this.zzi.add(r2);
        return;
    }

    public boolean addProgressListener(ProgressListener r5, long r6) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (r5 == null) goto L15;
        Map r0 = this.zzk;
        if (r0.containsKey(r5) == true) goto L18;
        Map r1 = this.zzl;
        Long r2 = Long.valueOf(r6);
        zzbg r3 = (zzbg) r1.get(r2);
        if (r3 != null) goto L10;
        r3 = new zzbg(this, r6);
        r1.put(r2, r3);
    L10:
        r3.zzb(r5);
        r0.put(r5, r3);
        if (hasMediaSession() == false) goto L17;
        r3.zze();
        return true;
    L17:
        return true;
    L18:
        return false;
    L15:
        return false;
    }

    public long getApproximateAdBreakClipPositionMs() {
        Object r0 = this.zzb;
        monitor-enter(r0);
        Preconditions.checkMainThread("Must be called from the main thread.");     // Catch: Throwable -> L7
        long r1 = this.zzd.zzy();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public long getApproximateLiveSeekableRangeEnd() {
        Object r0 = this.zzb;
        monitor-enter(r0);
        Preconditions.checkMainThread("Must be called from the main thread.");     // Catch: Throwable -> L7
        long r1 = this.zzd.zzx();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public long getApproximateLiveSeekableRangeStart() {
        Object r0 = this.zzb;
        monitor-enter(r0);
        Preconditions.checkMainThread("Must be called from the main thread.");     // Catch: Throwable -> L7
        long r1 = this.zzd.zzw();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public long getApproximateStreamPosition() {
        Object r0 = this.zzb;
        monitor-enter(r0);
        Preconditions.checkMainThread("Must be called from the main thread.");     // Catch: Throwable -> L7
        long r1 = this.zzd.zzv();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public MediaQueueItem getCurrentItem() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus r1 = getMediaStatus();
        if (r1 != null) goto L7;
        return null;
    L7:
        return r1.getQueueItemById(r1.getCurrentItemId());
    }

    public int getIdleReason() {
        Object r0 = this.zzb;
        monitor-enter(r0);
        Preconditions.checkMainThread("Must be called from the main thread.");     // Catch: Throwable -> L7
        MediaStatus r2 = getMediaStatus();     // Catch: Throwable -> L7
        if (r2 == null) goto L9;
        int r22 = r2.getIdleReason();     // Catch: Throwable -> L7
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r22;
    L9:
        r22 = 0;
    L7:
        th = move-exception;
        throw th;
    }

    public MediaQueueItem getLoadingItem() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus r1 = getMediaStatus();
        if (r1 != null) goto L7;
        return null;
    L7:
        return r1.getQueueItemById(r1.getLoadingItemId());
    }

    public MediaInfo getMediaInfo() {
        Object r0 = this.zzb;
        monitor-enter(r0);
        Preconditions.checkMainThread("Must be called from the main thread.");     // Catch: Throwable -> L7
        MediaInfo r2 = this.zzd.zzB();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r2;
    L7:
        th = move-exception;
        throw th;
    }

    public MediaQueue getMediaQueue() {
        Object r0 = this.zzb;
        monitor-enter(r0);
        Preconditions.checkMainThread("Must be called from the main thread.");     // Catch: Throwable -> L7
        MediaQueue r2 = this.zzf;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r2;
    L7:
        th = move-exception;
        throw th;
    }

    public MediaStatus getMediaStatus() {
        Object r0 = this.zzb;
        monitor-enter(r0);
        Preconditions.checkMainThread("Must be called from the main thread.");     // Catch: Throwable -> L7
        MediaStatus r2 = this.zzd.zzA();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r2;
    L7:
        th = move-exception;
        throw th;
    }

    public String getNamespace() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzd.zzd();
    }

    public int getPlayerState() {
        Object r0 = this.zzb;
        monitor-enter(r0);
        Preconditions.checkMainThread("Must be called from the main thread.");     // Catch: Throwable -> L7
        MediaStatus r2 = getMediaStatus();     // Catch: Throwable -> L7
        if (r2 == null) goto L9;
        int r22 = r2.getPlayerState();     // Catch: Throwable -> L7
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r22;
    L9:
        r22 = 1;
    L7:
        th = move-exception;
        throw th;
    }

    public MediaQueueItem getPreloadedItem() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus r1 = getMediaStatus();
        if (r1 != null) goto L7;
        return null;
    L7:
        return r1.getQueueItemById(r1.getPreloadedItemId());
    }

    public long getStreamDuration() {
        Object r0 = this.zzb;
        monitor-enter(r0);
        Preconditions.checkMainThread("Must be called from the main thread.");     // Catch: Throwable -> L7
        long r1 = this.zzd.zzz();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public boolean hasMediaSession() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (isBuffering() == false) goto L5;
        return true;
    L5:
        if (zzi() == false) goto L7;
        return true;
    L7:
        if (isPlaying() == false) goto L9;
        return true;
    L9:
        if (isPaused() == false) goto L11;
        return true;
    L11:
        if (isLoadingNextItem() == true) goto L20;
        return false;
    L20:
        return true;
    }

    public boolean isBuffering() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus r1 = getMediaStatus();
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.getPlayerState() != 4) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isLiveStream() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaInfo r1 = getMediaInfo();
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.getStreamType() != 2) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isLoadingNextItem() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus r1 = getMediaStatus();
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.getLoadingItemId() == 0) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isPaused() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus r0 = getMediaStatus();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.getPlayerState() != 3) goto L7;
        return true;
    L7:
        if (isLiveStream() == true) goto L9;
        return false;
    L9:
        if (getIdleReason() == 2) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isPlaying() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus r1 = getMediaStatus();
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.getPlayerState() != 2) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isPlayingAd() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus r1 = getMediaStatus();
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.isPlayingAd() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo r2) {
        return load(r2, new MediaLoadOptions.Builder().build());
    }

    public PendingResult<MediaChannelResult> load(MediaInfo r4, MediaLoadOptions r5) {
        MediaLoadRequestData.Builder r0 = new MediaLoadRequestData.Builder();
        r0.setMediaInfo(r4);
        r0.setAutoplay(Boolean.valueOf(r5.getAutoplay()));
        r0.setCurrentTime(r5.getPlayPosition());
        r0.setPlaybackRate(r5.getPlaybackRate());
        r0.setActiveTrackIds(r5.getActiveTrackIds());
        r0.setCustomData(r5.getCustomData());
        r0.setCredentials(r5.getCredentials());
        r0.setCredentialsType(r5.getCredentialsType());
        return load(r0.build());
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo r2, boolean r3) {
        MediaLoadOptions.Builder r0 = new MediaLoadOptions.Builder();
        r0.setAutoplay(r3);
        return load(r2, r0.build());
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo r2, boolean r3, long r4) {
        MediaLoadOptions.Builder r0 = new MediaLoadOptions.Builder();
        r0.setAutoplay(r3);
        r0.setPlayPosition(r4);
        return load(r2, r0.build());
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo r2, boolean r3, long r4, JSONObject r6) {
        MediaLoadOptions.Builder r0 = new MediaLoadOptions.Builder();
        r0.setAutoplay(r3);
        r0.setPlayPosition(r4);
        r0.setCustomData(r6);
        return load(r2, r0.build());
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo r2, boolean r3, long r4, long[] r6, JSONObject r7) {
        MediaLoadOptions.Builder r0 = new MediaLoadOptions.Builder();
        r0.setAutoplay(r3);
        r0.setPlayPosition(r4);
        r0.setActiveTrackIds(r6);
        r0.setCustomData(r7);
        return load(r2, r0.build());
    }

    public PendingResult<MediaChannelResult> load(MediaLoadRequestData r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzan r0 = new zzan(this, r2);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    @Override // com.google.android.gms.cast.Cast.MessageReceivedCallback
    public void onMessageReceived(CastDevice r1, String r2, String r3) {
        this.zzd.zzL(r3);
    }

    public PendingResult<MediaChannelResult> pause() {
        return pause(null);
    }

    public PendingResult<MediaChannelResult> pause(JSONObject r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzap r0 = new zzap(this, r2);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> play() {
        return play(null);
    }

    public PendingResult<MediaChannelResult> play(JSONObject r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzar r0 = new zzar(this, r2);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> queueAppendItem(MediaQueueItem r2, JSONObject r3) {
        return queueInsertItems(new MediaQueueItem[]{r2}, 0, r3);
    }

    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(MediaQueueItem r8, int r9, long r10, JSONObject r12) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzz r0 = new zzz(this, r8, r9, r10, r12);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(MediaQueueItem r7, int r8, JSONObject r9) {
        return queueInsertAndPlayItem(r7, r8, -1, r9);
    }

    public PendingResult<MediaChannelResult> queueInsertItems(MediaQueueItem[] r2, int r3, JSONObject r4) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzy r0 = new zzy(this, r2, r3, r4);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> queueJumpToItem(int r7, long r8, JSONObject r10) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzai r0 = new zzai(this, r7, r8, r10);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> queueJumpToItem(int r3, JSONObject r4) {
        return queueJumpToItem(r3, -1, r4);
    }

    public PendingResult<MediaChannelResult> queueLoad(MediaQueueItem[] r9, int r10, int r11, long r12, JSONObject r14) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzx r0 = new zzx(this, r9, r10, r11, r12, r14);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> queueLoad(MediaQueueItem[] r8, int r9, int r10, JSONObject r11) {
        return queueLoad(r8, r9, r10, -1, r11);
    }

    public PendingResult<MediaChannelResult> queueMoveItemToNewIndex(int r2, int r3, JSONObject r4) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzaj r0 = new zzaj(this, r2, r3, r4);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> queueNext(JSONObject r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzaf r0 = new zzaf(this, r2);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> queuePrev(JSONObject r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzae r0 = new zzae(this, r2);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> queueRemoveItem(int r2, JSONObject r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzah r0 = new zzah(this, r2, r3);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> queueRemoveItems(int[] r2, JSONObject r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzab r0 = new zzab(this, r2, r3);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> queueReorderItems(int[] r2, int r3, JSONObject r4) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzac r0 = new zzac(this, r2, r3, r4);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> queueSetRepeatMode(int r2, JSONObject r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzag r0 = new zzag(this, r2, r3);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> queueShuffle(JSONObject r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzad r0 = new zzad(this, true, r3);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> queueUpdateItems(MediaQueueItem[] r2, JSONObject r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzaa r0 = new zzaa(this, r2, r3);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public void registerCallback(Callback r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (r2 == null) goto L6;
        this.zzj.add(r2);
        return;
    }

    @Deprecated
    public void removeListener(Listener r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (r2 == null) goto L6;
        this.zzi.remove(r2);
        return;
    }

    public void removeProgressListener(ProgressListener r4) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzbg r0 = (zzbg) this.zzk.remove(r4);
        if (r0 == null) goto L8;
        r0.zzc(r4);
        if (r0.zzd() == true) goto L9;
        this.zzl.remove(Long.valueOf(r0.zza()));
        r0.zzf();
        return;
    L9:
        return;
    }

    public PendingResult<MediaChannelResult> requestStatus() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzu r0 = new zzu(this);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    @Deprecated
    public PendingResult<MediaChannelResult> seek(long r3) {
        return seek(r3, 0, null);
    }

    @Deprecated
    public PendingResult<MediaChannelResult> seek(long r2, int r4) {
        return seek(r2, r4, null);
    }

    @Deprecated
    public PendingResult<MediaChannelResult> seek(long r2, int r4, JSONObject r5) {
        MediaSeekOptions.Builder r0 = new MediaSeekOptions.Builder();
        r0.setPosition(r2);
        r0.setResumeState(r4);
        r0.setCustomData(r5);
        return seek(r0.build());
    }

    public PendingResult<MediaChannelResult> seek(MediaSeekOptions r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzas r0 = new zzas(this, r2);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> setActiveMediaTracks(long[] r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzv r0 = new zzv(this, r2);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public void setParseAdsInfoCallback(ParseAdsInfoCallback r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        this.zzm = r2;
    }

    public PendingResult<MediaChannelResult> setPlaybackRate(double r2) {
        return setPlaybackRate(r2, null);
    }

    public PendingResult<MediaChannelResult> setPlaybackRate(double r2, JSONObject r4) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzav r0 = new zzav(this, r2, r4);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> setStreamMute(boolean r2) {
        return setStreamMute(r2, null);
    }

    public PendingResult<MediaChannelResult> setStreamMute(boolean r2, JSONObject r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzau r0 = new zzau(this, r2, r3);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> setStreamVolume(double r2) {
        return setStreamVolume(r2, null);
    }

    public PendingResult<MediaChannelResult> setStreamVolume(double r2, JSONObject r4) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzat r0 = new zzat(this, r2, r4);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> setTextTrackStyle(TextTrackStyle r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzw r0 = new zzw(this, r2);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> skipAd() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzt r0 = new zzt(this);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public PendingResult<MediaChannelResult> stop() {
        return stop(null);
    }

    public PendingResult<MediaChannelResult> stop(JSONObject r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzaq r0 = new zzaq(this, r2);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public void togglePlayback() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        int r0 = getPlayerState();
        if (r0 != 4) goto L5;
    L9:
        pause();
        return;
    L5:
        if (r0 == 2) goto L9;
        play();
    }

    public void unregisterCallback(Callback r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (r2 == null) goto L6;
        this.zzj.remove(r2);
        return;
    }

    public final void zza(com.google.android.gms.cast.zzq r3) {
        com.google.android.gms.cast.zzq r0 = this.zzg;
        if (r0 == r3) goto L12;
        if (r0 == null) goto L7;
        this.zzd.zzh();
        this.zzf.zza();
        r0.zzr(getNamespace());
        this.zze.zza(null);
        this.zzc.removeCallbacksAndMessages(null);
    L7:
        this.zzg = r3;
        if (r3 == null) goto L11;
        this.zze.zza(r3);
        return;
    L11:
        return;
    }

    public final void zzb() {
        com.google.android.gms.cast.zzq r0 = this.zzg;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.zzq(getNamespace(), this);
        requestStatus();
    }

    public final PendingResult zzc() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzak r0 = new zzak(this, true);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public final PendingResult zzd(int[] r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzal r0 = new zzal(this, true, r3);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public final PendingResult zze(int r7, int r8, int r9) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzam r0 = new zzam(this, true, r7, r8, r9);
        zzz(r0);
        return r0;
    L5:
        return zzn(17, null);
    }

    public final Task zzf(JSONObject r5) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        this.zzh = new TaskCompletionSource();
        zza.d("create SessionState with cached mediaInfo and mediaStatus", new Object[0]);
        MediaInfo r52 = getMediaInfo();
        MediaStatus r0 = getMediaStatus();
        SessionState r1 = null;
        if (r52 == null) goto L11;
        if (r0 == null) goto L11;
        MediaLoadRequestData.Builder r12 = new MediaLoadRequestData.Builder();
        r12.setMediaInfo(r52);
        r12.setCurrentTime(getApproximateStreamPosition());
        r12.setQueueData(r0.getQueueData());
        r12.setPlaybackRate(r0.getPlaybackRate());
        r12.setActiveTrackIds(r0.getActiveTrackIds());
        r12.setCustomData(r0.getCustomData());
        MediaLoadRequestData r53 = r12.build();
        SessionState.Builder r02 = new SessionState.Builder();
        r02.setLoadRequestData(r53);
        r1 = r02.build();
    L11:
        TaskCompletionSource r54 = this.zzh;
        if (r1 == null) goto L14;
        r54.setResult(r1);
    L16:
        return this.zzh.getTask();
    L14:
        r54.setException(new com.google.android.gms.cast.internal.zzap());
        goto L16
    L5:
        return Tasks.forException(new com.google.android.gms.cast.internal.zzap());
    }

    public final void zzg(SessionState r4) {
        if (r4 == null) goto L9;
        MediaLoadRequestData r42 = r4.getLoadRequestData();
        if (r42 == null) goto L8;
        zza.d("resume SessionState", new Object[0]);
        load(r42);
        return;
    L8:
        return;
    }

    public final PendingResult zzh(String r3, List r4) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzx() == false) goto L5;
        zzao r42 = new zzao(this, true, r3, null);
        zzz(r42);
        return r42;
    L5:
        return zzn(17, null);
    }

    public final boolean zzi() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus r1 = getMediaStatus();
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.getPlayerState() != 5) goto L10;
        return true;
    L10:
        return false;
    }

    public final boolean zzj() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (isLiveStream() == true) goto L5;
        return true;
    L5:
        MediaStatus r4 = getMediaStatus();
        if (r4 != null) goto L9;
        return false;
    L9:
        if (r4.isMediaCommandSupported(2) == true) goto L11;
    L13:
        return false;
    L11:
        if (r4.getLiveSeekableRange() == null) goto L13;
        return true;
    }

    public final int zzk() {
        if (getMediaInfo() != null) goto L5;
    L25:
        return 0;
    L5:
        if (hasMediaSession() == false) goto L25;
        if (isBuffering() == false) goto L11;
        return 6;
    L11:
        if (isPlaying() == false) goto L15;
        return 3;
    L15:
        if (isPaused() == false) goto L19;
        return 2;
    L19:
        if (isLoadingNextItem() == false) goto L25;
        MediaQueueItem r3 = getLoadingItem();
        if (r3 == null) goto L25;
        if (r3.getMedia() == null) goto L25;
        return 6;
    }

    public final boolean zzl() {
        if (hasMediaSession() == true) goto L5;
        return false;
    L5:
        MediaStatus r4 = (MediaStatus) Preconditions.checkNotNull(getMediaStatus());
        if (r4.isMediaCommandSupported(128) == false) goto L9;
        return true;
    L9:
        if (r4.getQueueRepeatMode() != 0) goto L16;
        Integer r42 = r4.getIndexById(r4.getCurrentItemId());
        if (r42 != null) goto L13;
    L15:
        return false;
    L13:
        if (r42.intValue() <= 0) goto L15;
        return true;
    L16:
        return true;
    }

    public final boolean zzm() {
        if (hasMediaSession() == true) goto L5;
        return false;
    L5:
        MediaStatus r4 = (MediaStatus) Preconditions.checkNotNull(getMediaStatus());
        if (r4.isMediaCommandSupported(64) == false) goto L9;
        return true;
    L9:
        if (r4.getQueueRepeatMode() != 0) goto L16;
        Integer r0 = r4.getIndexById(r4.getCurrentItemId());
        if (r0 != null) goto L13;
    L15:
        return false;
    L13:
        if (r0.intValue() >= (r4.getQueueItemCount() - 1)) goto L15;
        return true;
    L16:
        return true;
    }

    public final /* synthetic */ void zzo(Set r1) {
        zzy(r1);
    }

    public final /* synthetic */ void zzp() {
        Iterator r0 = this.zzl.values().iterator();
    L4:
        if (r0.hasNext() == false) goto L26;
        zzbg r1 = (zzbg) r0.next();
        if (hasMediaSession() == false) goto L11;
        if (r1.zzg() == true) goto L11;
        r1.zze();
    L16:
        if (r1.zzg() == false) goto L4;
        if (isBuffering() == true) goto L25;
        if (zzi() == true) goto L25;
        if (isPaused() == true) goto L25;
        if (isLoadingNextItem() == false) goto L4;
    L25:
        zzy(r1.zzh());
    L11:
        if (hasMediaSession() == true) goto L16;
        if (r1.zzg() == false) goto L16;
        r1.zzf();
        goto L16
    }

    public final /* synthetic */ Object zzr() {
        return this.zzb;
    }

    public final /* synthetic */ Handler zzs() {
        return this.zzc;
    }

    public final /* synthetic */ com.google.android.gms.cast.internal.zzar zzt() {
        return this.zzd;
    }

    public final /* synthetic */ List zzu() {
        return this.zzi;
    }

    public final /* synthetic */ List zzv() {
        return this.zzj;
    }

    public final /* synthetic */ ParseAdsInfoCallback zzw() {
        return this.zzm;
    }
}
