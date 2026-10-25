package androidx.media3.extractor;

import androidx.media3.common.DataReader;

/* loaded from: classes4.dex */
public interface ExtractorInput extends DataReader {
    void advancePeekPosition(int r1);

    boolean advancePeekPosition(int r1, boolean r2);

    long getLength();

    long getPeekPosition();

    long getPosition();

    int peek(byte[] r1, int r2, int r3);

    void peekFully(byte[] r1, int r2, int r3);

    boolean peekFully(byte[] r1, int r2, int r3, boolean r4);

    @Override // androidx.media3.common.DataReader
    int read(byte[] r1, int r2, int r3);

    void readFully(byte[] r1, int r2, int r3);

    boolean readFully(byte[] r1, int r2, int r3, boolean r4);

    void resetPeekPosition();

    int skip(int r1);

    void skipFully(int r1);

    boolean skipFully(int r1, boolean r2);
}
