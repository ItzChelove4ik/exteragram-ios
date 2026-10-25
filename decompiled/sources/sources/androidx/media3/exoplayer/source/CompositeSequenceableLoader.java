package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.LoadingInfo;
import com.google.common.collect.ImmutableList;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes4.dex */
public final class CompositeSequenceableLoader implements SequenceableLoader {
    private long lastAudioVideoBufferedPositionUs;
    private final ImmutableList<SequenceableLoaderWithTrackTypes> loadersWithTrackTypes;

    public static final class SequenceableLoaderWithTrackTypes implements SequenceableLoader {
        private final SequenceableLoader loader;
        private final ImmutableList<Integer> trackTypes;

        public SequenceableLoaderWithTrackTypes(SequenceableLoader r1, List<Integer> r2) {
            this.loader = r1;
            this.trackTypes = ImmutableList.copyOf(r2);
        }

        @Override // androidx.media3.exoplayer.source.SequenceableLoader
        public boolean continueLoading(LoadingInfo r1) {
            return this.loader.continueLoading(r1);
        }

        @Override // androidx.media3.exoplayer.source.SequenceableLoader
        public long getBufferedPositionUs() {
            return this.loader.getBufferedPositionUs();
        }

        @Override // androidx.media3.exoplayer.source.SequenceableLoader
        public long getNextLoadPositionUs() {
            return this.loader.getNextLoadPositionUs();
        }

        public ImmutableList<Integer> getTrackTypes() {
            return this.trackTypes;
        }

        @Override // androidx.media3.exoplayer.source.SequenceableLoader
        public boolean isLoading() {
            return this.loader.isLoading();
        }

        @Override // androidx.media3.exoplayer.source.SequenceableLoader
        public void reevaluateBuffer(long r1) {
            this.loader.reevaluateBuffer(r1);
        }
    }

    public CompositeSequenceableLoader(List<? extends SequenceableLoader> r6, List<List<Integer>> r7) {
        ImmutableList.Builder r0 = ImmutableList.builder();
        int r3 = 0;
        if (r6.size() != r7.size()) goto L5;
        boolean r1 = true;
    L6:
        Assertions.checkArgument(r1);
    L8:
        if (r3 >= r6.size()) goto L10;
        r0.add(new SequenceableLoaderWithTrackTypes(r6.get(r3), r7.get(r3)));
        r3 = r3 + 1;
        goto L8
    L10:
        this.loadersWithTrackTypes = r0.build();
        this.lastAudioVideoBufferedPositionUs = -9223372036854775807L;
        return;
    L5:
        r1 = false;
        goto L6
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo r13) {
        boolean r1 = false;
    L3:
        long r2 = getNextLoadPositionUs();
        if (r2 == Long.MIN_VALUE) goto L5;
        int r6 = 0;
        boolean r7 = false;
    L8:
        if (r6 >= this.loadersWithTrackTypes.size()) goto L20;
        long r8 = this.loadersWithTrackTypes.get(r6).getNextLoadPositionUs();
        if (r8 != Long.MIN_VALUE) goto L12;
    L14:
        boolean r10 = false;
    L16:
        if (r8 == r2) goto L18;
        if (r10 == true) goto L18;
    L19:
        r6 = r6 + 1;
    L18:
        r7 = r7 | this.loadersWithTrackTypes.get(r6).continueLoading(r13);
        goto L19
    L12:
        if (r8 > r13.playbackPositionUs) goto L14;
        r10 = true;
        goto L16
    L20:
        r1 = r1 | r7;
        if (r7 == true) goto L3;
        return r1;
    L5:
        return r1;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        int r2 = 0;
        long r3 = Long.MAX_VALUE;
        long r5 = Long.MAX_VALUE;
    L4:
        if (r2 >= this.loadersWithTrackTypes.size()) goto L19;
        SequenceableLoaderWithTrackTypes r7 = this.loadersWithTrackTypes.get(r2);
        long r10 = r7.getBufferedPositionUs();
        if (r7.getTrackTypes().contains(1) == true) goto L12;
        if (r7.getTrackTypes().contains(2) == true) goto L12;
        if (r7.getTrackTypes().contains(4) == true) goto L12;
    L15:
        if (r10 == Long.MIN_VALUE) goto L17;
        r5 = Math.min(r5, r10);
    L17:
        r2 = r2 + 1;
    L12:
        if (r10 == Long.MIN_VALUE) goto L15;
        r3 = Math.min(r3, r10);
        goto L15
    L19:
        if (r3 == LongCompanionObject.MAX_VALUE) goto L23;
        this.lastAudioVideoBufferedPositionUs = r3;
        return r3;
    L23:
        if (r5 == LongCompanionObject.MAX_VALUE) goto L28;
        long r0 = this.lastAudioVideoBufferedPositionUs;
        if (r0 == (-9223372036854775807L)) goto L27;
        return r0;
    L27:
        return r5;
    L28:
        return Long.MIN_VALUE;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        int r2 = 0;
        long r3 = Long.MAX_VALUE;
    L4:
        if (r2 >= this.loadersWithTrackTypes.size()) goto L10;
        long r8 = this.loadersWithTrackTypes.get(r2).getNextLoadPositionUs();
        if (r8 == Long.MIN_VALUE) goto L8;
        r3 = Math.min(r3, r8);
    L8:
        r2 = r2 + 1;
        goto L4
    L10:
        if (r3 != LongCompanionObject.MAX_VALUE) goto L12;
        return Long.MIN_VALUE;
    L12:
        return r3;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        int r1 = 0;
    L4:
        if (r1 >= this.loadersWithTrackTypes.size()) goto L10;
        if (this.loadersWithTrackTypes.get(r1).isLoading() == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.loadersWithTrackTypes.size()) goto L6;
        this.loadersWithTrackTypes.get(r0).reevaluateBuffer(r3);
        r0 = r0 + 1;
        goto L4
    }
}
