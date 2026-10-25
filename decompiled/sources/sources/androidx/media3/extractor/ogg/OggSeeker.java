package androidx.media3.extractor.ogg;

import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.SeekMap;

/* loaded from: classes4.dex */
interface OggSeeker {
    SeekMap createSeekMap();

    long read(ExtractorInput r1);

    void startSeek(long r1);
}
