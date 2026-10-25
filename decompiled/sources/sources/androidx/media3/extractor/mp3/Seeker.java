package androidx.media3.extractor.mp3;

import androidx.media3.extractor.SeekMap;

/* loaded from: classes4.dex */
interface Seeker extends SeekMap {

    public static class UnseekableSeeker extends SeekMap.Unseekable implements Seeker {
        public UnseekableSeeker() {
            super(-9223372036854775807L);
        }

        @Override // androidx.media3.extractor.mp3.Seeker
        public int getAverageBitrate() {
            return -2147483647;
        }

        @Override // androidx.media3.extractor.mp3.Seeker
        public long getDataEndPosition() {
            return -1;
        }

        @Override // androidx.media3.extractor.mp3.Seeker
        public long getDataStartPosition() {
            return 0;
        }

        @Override // androidx.media3.extractor.mp3.Seeker
        public long getTimeUs(long r1) {
            return 0;
        }
    }

    int getAverageBitrate();

    long getDataEndPosition();

    long getDataStartPosition();

    long getTimeUs(long r1);
}
