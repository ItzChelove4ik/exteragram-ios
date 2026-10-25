package androidx.media3.extractor.mkv;

import androidx.media3.extractor.ExtractorInput;

/* loaded from: classes4.dex */
public interface EbmlProcessor {
    void binaryElement(int r1, int r2, ExtractorInput r3);

    void endMasterElement(int r1);

    void floatElement(int r1, double r2);

    int getElementType(int r1);

    void integerElement(int r1, long r2);

    boolean isLevel1Element(int r1);

    void startMasterElement(int r1, long r2, long r4);

    void stringElement(int r1, String r2);
}
