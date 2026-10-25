package androidx.media3.exoplayer.dash;

import android.os.Handler;
import android.os.Message;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.dash.manifest.DashManifest;
import androidx.media3.exoplayer.source.SampleQueue;
import androidx.media3.exoplayer.source.chunk.Chunk;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.metadata.MetadataInputBuffer;
import androidx.media3.extractor.metadata.emsg.EventMessage;
import androidx.media3.extractor.metadata.emsg.EventMessageDecoder;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public final class PlayerEmsgHandler implements Handler.Callback {
    private final Allocator allocator;
    private boolean chunkLoadedCompletedSinceLastManifestRefreshRequest;
    private final EventMessageDecoder decoder;
    private long expiredManifestPublishTimeUs;
    private final Handler handler;
    private boolean isWaitingForManifestRefresh;
    private DashManifest manifest;
    private final TreeMap<Long, Long> manifestPublishTimeToExpiryTimeUs;
    private final PlayerEmsgCallback playerEmsgCallback;
    private boolean released;

    public static final class ManifestExpiryEventInfo {
        public final long eventTimeUs;
        public final long manifestPublishTimeMsInEmsg;

        public ManifestExpiryEventInfo(long r1, long r3) {
            this.eventTimeUs = r1;
            this.manifestPublishTimeMsInEmsg = r3;
        }
    }

    public interface PlayerEmsgCallback {
        void onDashManifestPublishTimeExpired(long r1);

        void onDashManifestRefreshRequested();
    }

    public final class PlayerTrackEmsgHandler implements TrackOutput {
        private final MetadataInputBuffer buffer;
        private final FormatHolder formatHolder;
        private long maxLoadedChunkEndTimeUs;
        private final SampleQueue sampleQueue;
        final /* synthetic */ PlayerEmsgHandler this$0;

        public PlayerTrackEmsgHandler(PlayerEmsgHandler r1, Allocator r2) {
            this.this$0 = r1;
            this.sampleQueue = SampleQueue.createWithoutDrm(r2);
            this.formatHolder = new FormatHolder();
            this.buffer = new MetadataInputBuffer();
            this.maxLoadedChunkEndTimeUs = -9223372036854775807L;
        }

        private MetadataInputBuffer dequeueSample() {
            this.buffer.clear();
            if (this.sampleQueue.read(this.formatHolder, this.buffer, 0, false) != (-4)) goto L6;
            this.buffer.flip();
            return this.buffer;
        L6:
            return null;
        }

        private void onManifestExpiredMessageEncountered(long r2, long r4) {
            ManifestExpiryEventInfo r0 = new ManifestExpiryEventInfo(r2, r4);
            PlayerEmsgHandler.access$300(this.this$0).sendMessage(PlayerEmsgHandler.access$300(this.this$0).obtainMessage(1, r0));
        }

        private void parseAndDiscardSamples() {
        L3:
            if (this.sampleQueue.isReady(false) == false) goto L13;
            MetadataInputBuffer r0 = dequeueSample();
            if (r0 == null) goto L3;
            long r2 = r0.timeUs;
            Metadata r02 = PlayerEmsgHandler.access$000(this.this$0).decode(r0);
            if (r02 == null) goto L3;
            EventMessage r03 = (EventMessage) r02.get(0);
            if (PlayerEmsgHandler.access$100(r03.schemeIdUri, r03.value) == false) goto L3;
            parsePlayerEmsgEvent(r2, r03);
            goto L3
        L13:
            this.sampleQueue.discardToRead();
        }

        private void parsePlayerEmsgEvent(long r5, EventMessage r7) {
            long r0 = PlayerEmsgHandler.access$200(r7);
            if (r0 != (-9223372036854775807L)) goto L5;
            return;
        L5:
            onManifestExpiredMessageEncountered(r5, r0);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void format(Format r1) {
            this.sampleQueue.format(r1);
        }

        public boolean maybeRefreshManifestBeforeLoadingNextChunk(long r1) {
            return this.this$0.maybeRefreshManifestBeforeLoadingNextChunk(r1);
        }

        public void onChunkLoadCompleted(Chunk r5) {
            long r0 = this.maxLoadedChunkEndTimeUs;
            if (r0 != (-9223372036854775807L)) goto L5;
        L6:
            this.maxLoadedChunkEndTimeUs = r5.endTimeUs;
        L7:
            this.this$0.onChunkLoadCompleted(r5);
            return;
        L5:
            if (r5.endTimeUs <= r0) goto L7;
            goto L6
        }

        public boolean onChunkLoadError(Chunk r5) {
            long r0 = this.maxLoadedChunkEndTimeUs;
            if (r0 != (-9223372036854775807L)) goto L5;
        L7:
            boolean r52 = false;
        L9:
            return this.this$0.onChunkLoadError(r52);
        L5:
            if (r0 >= r5.startTimeUs) goto L7;
            r52 = true;
            goto L9
        }

        public void release() {
            this.sampleQueue.release();
        }

        @Override // androidx.media3.extractor.TrackOutput
        public int sampleData(DataReader r1, int r2, boolean r3, int r4) {
            return this.sampleQueue.sampleData(r1, r2, r3);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void sampleData(ParsableByteArray r1, int r2, int r3) {
            this.sampleQueue.sampleData(r1, r2);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void sampleMetadata(long r8, int r10, int r11, int r12, TrackOutput.CryptoData r13) {
            this.sampleQueue.sampleMetadata(r8, r10, r11, r12, r13);
            parseAndDiscardSamples();
        }
    }

    public PlayerEmsgHandler(DashManifest r1, PlayerEmsgCallback r2, Allocator r3) {
        this.manifest = r1;
        this.playerEmsgCallback = r2;
        this.allocator = r3;
        this.manifestPublishTimeToExpiryTimeUs = new TreeMap();
        this.handler = Util.createHandlerForCurrentLooper(this);
        this.decoder = new EventMessageDecoder();
    }

    public static /* synthetic */ EventMessageDecoder access$000(PlayerEmsgHandler r0) {
        return r0.decoder;
    }

    public static /* synthetic */ boolean access$100(String r0, String r1) {
        return isPlayerEmsgEvent(r0, r1);
    }

    public static /* synthetic */ long access$200(EventMessage r2) {
        return getManifestPublishTimeMsInEmsg(r2);
    }

    public static /* synthetic */ Handler access$300(PlayerEmsgHandler r0) {
        return r0.handler;
    }

    private Map.Entry<Long, Long> ceilingExpiryEntryForPublishTime(long r1) {
        return this.manifestPublishTimeToExpiryTimeUs.ceilingEntry(Long.valueOf(r1));
    }

    private static long getManifestPublishTimeMsInEmsg(EventMessage r2) {
        return Util.parseXsDateTime(Util.fromUtf8Bytes(r2.messageData));
    L4:
        return -9223372036854775807L;
    }

    private void handleManifestExpiredMessage(long r3, long r5) {
        Long r0 = this.manifestPublishTimeToExpiryTimeUs.get(Long.valueOf(r5));
        if (r0 != null) goto L7;
        this.manifestPublishTimeToExpiryTimeUs.put(Long.valueOf(r5), Long.valueOf(r3));
        return;
    L7:
        if (r0.longValue() <= r3) goto L10;
        this.manifestPublishTimeToExpiryTimeUs.put(Long.valueOf(r5), Long.valueOf(r3));
        return;
    }

    private static boolean isPlayerEmsgEvent(String r1, String r2) {
        if ("urn:mpeg:dash:event:2012".equals(r1) == true) goto L5;
        return false;
    L5:
        if ("1".equals(r2) == false) goto L7;
        return true;
    L7:
        if ("2".equals(r2) == false) goto L9;
        return true;
    L9:
        if ("3".equals(r2) == false) goto L16;
        return true;
    L16:
        return false;
    }

    private void maybeNotifyDashManifestRefreshNeeded() {
        if (this.chunkLoadedCompletedSinceLastManifestRefreshRequest == true) goto L5;
        return;
    L5:
        this.isWaitingForManifestRefresh = true;
        this.chunkLoadedCompletedSinceLastManifestRefreshRequest = false;
        this.playerEmsgCallback.onDashManifestRefreshRequested();
    }

    private void notifyManifestPublishTimeExpired() {
        this.playerEmsgCallback.onDashManifestPublishTimeExpired(this.expiredManifestPublishTimeUs);
    }

    private void removePreviouslyExpiredManifestPublishTimeValues() {
        Iterator<Map.Entry<Long, Long>> r0 = this.manifestPublishTimeToExpiryTimeUs.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        if (r0.next().getKey().longValue() >= this.manifest.publishTimeMs) goto L4;
        r0.remove();
        goto L4
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message r7) {
        if (this.released == false) goto L6;
        return true;
    L6:
        if (r7.what == 1) goto L9;
        return false;
    L9:
        ManifestExpiryEventInfo r72 = (ManifestExpiryEventInfo) r7.obj;
        handleManifestExpiredMessage(r72.eventTimeUs, r72.manifestPublishTimeMsInEmsg);
        return true;
    }

    public boolean maybeRefreshManifestBeforeLoadingNextChunk(long r7) {
        DashManifest r0 = this.manifest;
        boolean r2 = false;
        if (r0.dynamic == true) goto L6;
        return false;
    L6:
        if (this.isWaitingForManifestRefresh == false) goto L8;
        return true;
    L8:
        Map.Entry<Long, Long> r02 = ceilingExpiryEntryForPublishTime(r0.publishTimeMs);
        if (r02 != null) goto L11;
    L13:
        if (r2 == false) goto L15;
        maybeNotifyDashManifestRefreshNeeded();
    L15:
        return r2;
    L11:
        if (r02.getValue().longValue() >= r7) goto L13;
        this.expiredManifestPublishTimeUs = r02.getKey().longValue();
        notifyManifestPublishTimeExpired();
        r2 = true;
        goto L13
    }

    public PlayerTrackEmsgHandler newPlayerTrackEmsgHandler() {
        return new PlayerTrackEmsgHandler(this, this.allocator);
    }

    public void onChunkLoadCompleted(Chunk r1) {
        this.chunkLoadedCompletedSinceLastManifestRefreshRequest = true;
    }

    public boolean onChunkLoadError(boolean r4) {
        if (this.manifest.dynamic == true) goto L6;
        return false;
    L6:
        if (this.isWaitingForManifestRefresh == false) goto L8;
        return true;
    L8:
        if (r4 == false) goto L11;
        maybeNotifyDashManifestRefreshNeeded();
        return true;
    L11:
        return false;
    }

    public void release() {
        this.released = true;
        this.handler.removeCallbacksAndMessages(null);
    }

    public void updateManifest(DashManifest r3) {
        this.isWaitingForManifestRefresh = false;
        this.expiredManifestPublishTimeUs = -9223372036854775807L;
        this.manifest = r3;
        removePreviouslyExpiredManifestPublishTimeValues();
    }
}
