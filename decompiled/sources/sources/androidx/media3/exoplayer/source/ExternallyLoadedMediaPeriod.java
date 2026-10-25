package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.ExternalLoader;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes4.dex */
final class ExternallyLoadedMediaPeriod implements MediaPeriod {
    private final AtomicBoolean loadingFinished;
    private ListenableFuture<?> loadingFuture;
    private final AtomicReference<Throwable> loadingThrowable;
    private final byte[] sampleData;
    private final ArrayList<SampleStreamImpl> sampleStreams;
    private final TrackGroupArray tracks;
    private final Uri uri;

    public final class SampleStreamImpl implements SampleStream {
        private int streamState;
        final /* synthetic */ ExternallyLoadedMediaPeriod this$0;

        public SampleStreamImpl(ExternallyLoadedMediaPeriod r1) {
            this.this$0 = r1;
            this.streamState = 0;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public boolean isReady() {
            return ExternallyLoadedMediaPeriod.access$000(this.this$0).get();
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public void maybeThrowError() {
            Throwable r1 = (Throwable) ExternallyLoadedMediaPeriod.access$100(this.this$0).get();
            if (r1 != null) goto L6;
            return;
        L6:
            throw new IOException(r1);
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int readData(FormatHolder r9, DecoderInputBuffer r10, int r11) {
            int r0 = this.streamState;
            if (r0 != 2) goto L7;
            r10.addFlag(4);
            return -4;
        L7:
            if ((r11 & 2) != 0) goto L21;
            if (r0 == 0) goto L21;
            if (ExternallyLoadedMediaPeriod.access$000(this.this$0).get() == true) goto L14;
            return -3;
        L14:
            int r92 = ExternallyLoadedMediaPeriod.access$300(this.this$0).length;
            r10.addFlag(1);
            r10.timeUs = 0;
            if ((r11 & 4) != 0) goto L18;
            r10.ensureSpaceForWrite(r92);
            r10.data.put(ExternallyLoadedMediaPeriod.access$300(this.this$0), 0, r92);
        L18:
            if ((r11 & 1) != 0) goto L20;
            this.streamState = 2;
        L20:
            return -4;
        L21:
            r9.format = ExternallyLoadedMediaPeriod.access$200(this.this$0).get(0).getFormat(0);
            this.streamState = 1;
            return -5;
        }

        public void reset() {
            if (this.streamState != 2) goto L6;
            this.streamState = 1;
            return;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int skipData(long r1) {
            return 0;
        }
    }

    public ExternallyLoadedMediaPeriod(Uri r2, String r3, ExternalLoader r4) {
        this.uri = r2;
        this.tracks = new TrackGroupArray(new TrackGroup[]{new TrackGroup(new Format[]{new Format.Builder().setSampleMimeType(r3).build()})});
        this.sampleData = r2.toString().getBytes(StandardCharsets.UTF_8);
        this.loadingFinished = new AtomicBoolean();
        this.loadingThrowable = new AtomicReference();
        this.sampleStreams = new ArrayList();
    }

    public static /* synthetic */ AtomicBoolean access$000(ExternallyLoadedMediaPeriod r0) {
        return r0.loadingFinished;
    }

    public static /* synthetic */ AtomicReference access$100(ExternallyLoadedMediaPeriod r0) {
        return r0.loadingThrowable;
    }

    public static /* synthetic */ TrackGroupArray access$200(ExternallyLoadedMediaPeriod r0) {
        return r0.tracks;
    }

    public static /* synthetic */ byte[] access$300(ExternallyLoadedMediaPeriod r0) {
        return r0.sampleData;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo r1) {
        return !this.loadingFinished.get();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long r1, boolean r3) {
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long r1, SeekParameters r3) {
        return r1;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        if (this.loadingFinished.get() == false) goto L6;
        return Long.MIN_VALUE;
    L6:
        return 0;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        if (this.loadingFinished.get() == false) goto L6;
        return Long.MIN_VALUE;
    L6:
        return 0;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.tracks;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return !this.loadingFinished.get();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() {
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback r1, long r2) {
        r1.onPrepared(this);
        new ExternalLoader.LoadRequest(this.uri);
        throw null;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        return -9223372036854775807L;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long r1) {
    }

    public void releasePeriod() {
        ListenableFuture<?> r1 = this.loadingFuture;
        if (r1 == null) goto L6;
        r1.cancel(false);
        return;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.sampleStreams.size()) goto L6;
        this.sampleStreams.get(r0).reset();
        r0 = r0 + 1;
        goto L4
    L6:
        return r3;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] r4, boolean[] r5, SampleStream[] r6, boolean[] r7, long r8) {
        int r0 = 0;
    L4:
        if (r0 >= r4.length) goto L18;
        SampleStream r1 = r6[r0];
        if (r1 == null) goto L13;
        if (r4[r0] != null) goto L10;
    L11:
        this.sampleStreams.remove(r1);
        r6[r0] = null;
        goto L13
    L10:
        if (r5[r0] == false) goto L11;
    L13:
        if (r6[r0] != null) goto L17;
        if (r4[r0] == null) goto L17;
        SampleStreamImpl r12 = new SampleStreamImpl(this);
        this.sampleStreams.add(r12);
        r6[r0] = r12;
        r7[r0] = true;
    L17:
        r0 = r0 + 1;
        goto L4
    L18:
        return r8;
    }
}
