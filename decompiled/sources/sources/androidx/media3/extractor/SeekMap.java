package androidx.media3.extractor;

import androidx.media3.common.util.Assertions;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public interface SeekMap {

    public static final class SeekPoints {
        public final SeekPoint first;
        public final SeekPoint second;

        public SeekPoints(SeekPoint r1) {
            this(r1, r1);
        }

        public SeekPoints(SeekPoint r1, SeekPoint r2) {
            this.first = (SeekPoint) Assertions.checkNotNull(r1);
            this.second = (SeekPoint) Assertions.checkNotNull(r2);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L15:
            return false;
        L8:
            if (SeekPoints.class != r5.getClass()) goto L15;
            SeekPoints r52 = (SeekPoints) r5;
            if (this.first.equals(r52.first) == false) goto L15;
            if (this.second.equals(r52.second) == false) goto L15;
            return true;
        }

        public int hashCode() {
            return (this.first.hashCode() * 31) + this.second.hashCode();
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder("[");
            r0.append(this.first);
            if (this.first.equals(this.second) == false) goto L5;
            String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            r0.append(r3);
            r0.append("]");
            return r0.toString();
        L5:
            r3 = ", " + this.second;
            goto L6
        }
    }

    public static class Unseekable implements SeekMap {
        private final long durationUs;
        private final SeekPoints startSeekPoints;

        public Unseekable(long r3) {
            this(r3, 0);
        }

        public Unseekable(long r3, long r5) {
            this.durationUs = r3;
            if (r5 != 0) goto L5;
            SeekPoint r4 = SeekPoint.START;
        L6:
            this.startSeekPoints = new SeekPoints(r4);
            return;
        L5:
            r4 = new SeekPoint(0, r5);
            goto L6
        }

        @Override // androidx.media3.extractor.SeekMap
        public long getDurationUs() {
            return this.durationUs;
        }

        @Override // androidx.media3.extractor.SeekMap
        public SeekPoints getSeekPoints(long r1) {
            return this.startSeekPoints;
        }

        @Override // androidx.media3.extractor.SeekMap
        public boolean isSeekable() {
            return false;
        }
    }

    long getDurationUs();

    SeekPoints getSeekPoints(long r1);

    boolean isSeekable();
}
