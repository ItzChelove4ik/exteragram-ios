package androidx.media3.extractor.mkv;

import androidx.media3.extractor.ExtractorInput;

/* loaded from: classes4.dex */
interface EbmlReader {
    void init(EbmlProcessor r1);

    boolean read(ExtractorInput r1);

    void reset();
}
