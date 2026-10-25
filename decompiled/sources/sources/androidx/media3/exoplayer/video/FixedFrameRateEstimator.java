package androidx.media3.exoplayer.video;

import java.util.Arrays;

/* loaded from: classes4.dex */
final class FixedFrameRateEstimator {
    private Matcher candidateMatcher;
    private boolean candidateMatcherActive;
    private Matcher currentMatcher;
    private int framesWithoutSyncCount;
    private long lastFramePresentationTimeNs;
    private boolean switchToCandidateMatcherWhenSynced;

    public static final class Matcher {
        private long firstFrameDurationNs;
        private long firstFramePresentationTimeNs;
        private long frameCount;
        private long lastFramePresentationTimeNs;
        private long matchingFrameCount;
        private long matchingFrameDurationSumNs;
        private int recentFrameOutlierCount;
        private final boolean[] recentFrameOutlierFlags;

        public Matcher() {
            this.recentFrameOutlierFlags = new boolean[15];
        }

        private static int getRecentFrameOutlierIndex(long r2) {
            return (int) (r2 % 15);
        }

        public long getFrameDurationNs() {
            long r0 = this.matchingFrameCount;
            if (r0 != 0) goto L6;
            return 0;
        L6:
            return this.matchingFrameDurationSumNs / r0;
        }

        public long getMatchingFrameDurationSumNs() {
            return this.matchingFrameDurationSumNs;
        }

        public boolean isLastFrameOutlier() {
            long r0 = this.frameCount;
            if (r0 != 0) goto L7;
            return false;
        L7:
            return this.recentFrameOutlierFlags[getRecentFrameOutlierIndex(r0 - 1)];
        }

        public boolean isSynced() {
            if (this.frameCount > 15) goto L5;
            return false;
        L5:
            if (this.recentFrameOutlierCount != 0) goto L10;
            return true;
        L10:
            return false;
        }

        public void onNextFrame(long r10) {
            long r0 = this.frameCount;
            if (r0 != 0) goto L6;
            this.firstFramePresentationTimeNs = r10;
        L16:
            this.frameCount++;
            this.lastFramePresentationTimeNs = r10;
            return;
        L6:
            if (r0 != 1) goto L8;
            long r02 = r10 - this.firstFramePresentationTimeNs;
            this.firstFrameDurationNs = r02;
            this.matchingFrameDurationSumNs = r02;
            this.matchingFrameCount = 1;
            goto L16
        L8:
            long r5 = r10 - this.lastFramePresentationTimeNs;
            int r03 = getRecentFrameOutlierIndex(r0);
            if (Math.abs(r5 - this.firstFrameDurationNs) > 1000000) goto L13;
            this.matchingFrameCount++;
            this.matchingFrameDurationSumNs += r5;
            boolean[] r1 = this.recentFrameOutlierFlags;
            if (r1[r03] == false) goto L16;
            r1[r03] = false;
            this.recentFrameOutlierCount--;
            goto L16
        L13:
            boolean[] r12 = this.recentFrameOutlierFlags;
            if (r12[r03] == true) goto L16;
            r12[r03] = true;
            this.recentFrameOutlierCount++;
            goto L16
        }

        public void reset() {
            this.frameCount = 0;
            this.matchingFrameCount = 0;
            this.matchingFrameDurationSumNs = 0;
            this.recentFrameOutlierCount = 0;
            Arrays.fill(this.recentFrameOutlierFlags, false);
        }
    }

    public FixedFrameRateEstimator() {
        this.currentMatcher = new Matcher();
        this.candidateMatcher = new Matcher();
        this.lastFramePresentationTimeNs = -9223372036854775807L;
    }

    public long getFrameDurationNs() {
        if (isSynced() == true) goto L5;
        return -9223372036854775807L;
    L5:
        return this.currentMatcher.getFrameDurationNs();
    }

    public float getFrameRate() {
        if (isSynced() == true) goto L5;
        return -1.0f;
    L5:
        return (float) (1.0E9d / this.currentMatcher.getFrameDurationNs());
    }

    public int getFramesWithoutSyncCount() {
        return this.framesWithoutSyncCount;
    }

    public long getMatchingFrameDurationSumNs() {
        if (isSynced() == true) goto L5;
        return -9223372036854775807L;
    L5:
        return this.currentMatcher.getMatchingFrameDurationSumNs();
    }

    public boolean isSynced() {
        return this.currentMatcher.isSynced();
    }

    public void onNextFrame(long r8) {
        this.currentMatcher.onNextFrame(r8);
        int r2 = 0;
        if (this.currentMatcher.isSynced() == false) goto L8;
        if (this.switchToCandidateMatcherWhenSynced == true) goto L8;
        this.candidateMatcherActive = false;
    L16:
        if (this.candidateMatcherActive == true) goto L18;
    L20:
        this.lastFramePresentationTimeNs = r8;
        if (this.currentMatcher.isSynced() == true) goto L24;
        r2 = this.framesWithoutSyncCount + 1;
    L24:
        this.framesWithoutSyncCount = r2;
        return;
    L18:
        if (this.candidateMatcher.isSynced() == false) goto L20;
        Matcher r0 = this.currentMatcher;
        this.currentMatcher = this.candidateMatcher;
        this.candidateMatcher = r0;
        this.candidateMatcherActive = false;
        this.switchToCandidateMatcherWhenSynced = false;
    L8:
        if (this.lastFramePresentationTimeNs == (-9223372036854775807L)) goto L16;
        if (this.candidateMatcherActive == true) goto L12;
    L13:
        this.candidateMatcher.reset();
        this.candidateMatcher.onNextFrame(this.lastFramePresentationTimeNs);
    L14:
        this.candidateMatcherActive = true;
        this.candidateMatcher.onNextFrame(r8);
        goto L16
    L12:
        if (this.candidateMatcher.isLastFrameOutlier() == false) goto L14;
        goto L13
    }

    public void reset() {
        this.currentMatcher.reset();
        this.candidateMatcher.reset();
        this.candidateMatcherActive = false;
        this.lastFramePresentationTimeNs = -9223372036854775807L;
        this.framesWithoutSyncCount = 0;
    }
}
