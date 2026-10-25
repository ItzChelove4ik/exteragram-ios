package androidx.media3.extractor.ts;

import android.util.SparseArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.extractor.ExtractorOutput;
import java.util.Collections;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public interface TsPayloadReader {

    public static final class DvbSubtitleInfo {
        public final byte[] initializationData;
        public final String language;
        public final int type;

        public DvbSubtitleInfo(String r1, int r2, byte[] r3) {
            this.language = r1;
            this.type = r2;
            this.initializationData = r3;
        }
    }

    public static final class EsInfo {
        public final int audioType;
        public final byte[] descriptorBytes;
        public final List<DvbSubtitleInfo> dvbSubtitleInfos;
        public final String language;
        public final int streamType;

        public EsInfo(int r1, String r2, int r3, List<DvbSubtitleInfo> r4, byte[] r5) {
            this.streamType = r1;
            this.language = r2;
            this.audioType = r3;
            if (r4 != null) goto L5;
            List<DvbSubtitleInfo> r12 = Collections.EMPTY_LIST;
        L6:
            this.dvbSubtitleInfos = r12;
            this.descriptorBytes = r5;
            return;
        L5:
            r12 = Collections.unmodifiableList(r4);
            goto L6
        }

        public int getRoleFlags() {
            int r1 = this.audioType;
            if (r1 != 2) goto L5;
            return 2048;
        L5:
            if (r1 == 3) goto L8;
            return 0;
        L8:
            return 512;
        }
    }

    public interface Factory {
        SparseArray<TsPayloadReader> createInitialPayloadReaders();

        TsPayloadReader createPayloadReader(int r1, EsInfo r2);
    }

    public static final class TrackIdGenerator {
        private final int firstTrackId;
        private String formatId;
        private final String formatIdPrefix;
        private int trackId;
        private final int trackIdIncrement;

        public TrackIdGenerator(int r2, int r3) {
            this(Integer.MIN_VALUE, r2, r3);
        }

        public TrackIdGenerator(int r4, int r5, int r6) {
            if (r4 == Integer.MIN_VALUE) goto L5;
            String r42 = r4 + "/";
        L6:
            this.formatIdPrefix = r42;
            this.firstTrackId = r5;
            this.trackIdIncrement = r6;
            this.trackId = Integer.MIN_VALUE;
            this.formatId = _UrlKt.FRAGMENT_ENCODE_SET;
            return;
        L5:
            r42 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L6
        }

        private void maybeThrowUninitializedError() {
            if (this.trackId == Integer.MIN_VALUE) goto L5;
            return;
        L5:
            Segment$$ExternalSyntheticBUOutline1.m("generateNewId() must be called before retrieving ids.");
        }

        public void generateNewId() {
            int r0 = this.trackId;
            if (r0 != Integer.MIN_VALUE) goto L5;
            int r02 = this.firstTrackId;
        L6:
            this.trackId = r02;
            this.formatId = this.formatIdPrefix + this.trackId;
            return;
        L5:
            r02 = r0 + this.trackIdIncrement;
            goto L6
        }

        public String getFormatId() {
            maybeThrowUninitializedError();
            return this.formatId;
        }

        public int getTrackId() {
            maybeThrowUninitializedError();
            return this.trackId;
        }
    }

    void consume(ParsableByteArray r1, int r2);

    void init(TimestampAdjuster r1, ExtractorOutput r2, TrackIdGenerator r3);

    void seek();
}
