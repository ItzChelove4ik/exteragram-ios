package androidx.media3.exoplayer.upstream;

import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import java.io.IOException;

/* loaded from: classes4.dex */
public interface LoadErrorHandlingPolicy {

    public static final class FallbackOptions {
        public final int numberOfExcludedLocations;
        public final int numberOfExcludedTracks;
        public final int numberOfLocations;
        public final int numberOfTracks;

        public FallbackOptions(int r1, int r2, int r3, int r4) {
            this.numberOfLocations = r1;
            this.numberOfExcludedLocations = r2;
            this.numberOfTracks = r3;
            this.numberOfExcludedTracks = r4;
        }

        public boolean isFallbackAvailable(int r3) {
            if (r3 != 1) goto L9;
            if ((this.numberOfLocations - this.numberOfExcludedLocations) <= 1) goto L7;
            return true;
        L7:
            return false;
        L9:
            if ((this.numberOfTracks - this.numberOfExcludedTracks) <= 1) goto L11;
            return true;
        L11:
            return false;
        }
    }

    public static final class FallbackSelection {
        public final long exclusionDurationMs;
        public final int type;

        public FallbackSelection(int r3, long r4) {
            if (r4 < 0) goto L5;
            boolean r0 = true;
        L6:
            Assertions.checkArgument(r0);
            this.type = r3;
            this.exclusionDurationMs = r4;
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    public static final class LoadErrorInfo {
        public final int errorCount;
        public final IOException exception;
        public final LoadEventInfo loadEventInfo;
        public final MediaLoadData mediaLoadData;

        public LoadErrorInfo(LoadEventInfo r1, MediaLoadData r2, IOException r3, int r4) {
            this.loadEventInfo = r1;
            this.mediaLoadData = r2;
            this.exception = r3;
            this.errorCount = r4;
        }
    }

    FallbackSelection getFallbackSelectionFor(FallbackOptions r1, LoadErrorInfo r2);

    int getMinimumLoadableRetryCount(int r1);

    long getRetryDelayMsFor(LoadErrorInfo r1);

    default void onLoadTaskConcluded(long r1) {
    }
}
