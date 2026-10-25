package androidx.media3.extractor;

import androidx.media3.common.DataReader;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.Util;
import java.io.InterruptedIOException;
import java.util.Arrays;
import okio.Buffer$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class DefaultExtractorInput implements ExtractorInput {
    private final DataReader dataReader;
    private byte[] peekBuffer;
    private int peekBufferLength;
    private int peekBufferPosition;
    private long position;
    private final byte[] scratchSpace;
    private final long streamLength;

    static {
        MediaLibraryInfo.registerModule("media3.extractor");
    }

    public DefaultExtractorInput(DataReader r1, long r2, long r4) {
        this.dataReader = r1;
        this.position = r2;
        this.streamLength = r4;
        this.peekBuffer = new byte[65536];
        this.scratchSpace = new byte[4096];
    }

    private void commitBytesRead(int r5) {
        if (r5 == (-1)) goto L6;
        this.position += r5;
        return;
    }

    private void ensureSpaceForPeek(int r4) {
        int r0 = this.peekBufferPosition + r4;
        byte[] r42 = this.peekBuffer;
        if (r0 <= r42.length) goto L6;
        int r43 = Util.constrainValue(r42.length * 2, 65536 + r0, r0 + 524288);
        this.peekBuffer = Arrays.copyOf(this.peekBuffer, r43);
        return;
    }

    private int readFromPeekBuffer(byte[] r3, int r4, int r5) {
        int r0 = this.peekBufferLength;
        if (r0 != 0) goto L5;
        return 0;
    L5:
        int r52 = Math.min(r0, r5);
        System.arraycopy(this.peekBuffer, 0, r3, r4, r52);
        updatePeekBuffer(r52);
        return r52;
    }

    private int readFromUpstream(byte[] r2, int r3, int r4, int r5, boolean r6) {
        if (Thread.interrupted() == true) goto L14;
        int r1 = this.dataReader.read(r2, r3 + r5, r4 - r5);
        if (r1 != (-1)) goto L12;
        if (r5 != 0) goto L9;
        if (r6 == false) goto L9;
        return -1;
    L9:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return 0;
    L12:
        return r5 + r1;
    L14:
        throw new InterruptedIOException();
    }

    private int skipFromPeekBuffer(int r2) {
        int r22 = Math.min(this.peekBufferLength, r2);
        updatePeekBuffer(r22);
        return r22;
    }

    private void updatePeekBuffer(int r6) {
        int r0 = this.peekBufferLength - r6;
        this.peekBufferLength = r0;
        this.peekBufferPosition = 0;
        byte[] r2 = this.peekBuffer;
        if (r0 >= (r2.length - 524288)) goto L5;
        byte[] r3 = new byte[65536 + r0];
    L6:
        System.arraycopy(r2, r6, r3, 0, r0);
        this.peekBuffer = r3;
        return;
    L5:
        r3 = r2;
        goto L6
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void advancePeekPosition(int r2) {
        advancePeekPosition(r2, false);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public boolean advancePeekPosition(int r8, boolean r9) {
        ensureSpaceForPeek(r8);
        int r5 = this.peekBufferLength - this.peekBufferPosition;
    L3:
        if (r5 >= r8) goto L9;
        DefaultExtractorInput r1 = this;
        int r4 = r8;
        boolean r6 = r9;
        r5 = r1.readFromUpstream(this.peekBuffer, this.peekBufferPosition, r4, r5, r6);
        if (r5 == (-1)) goto L6;
        r1.peekBufferLength = r1.peekBufferPosition + r5;
        DefaultExtractorInput this = r1;
        r8 = r4;
        r9 = r6;
        goto L3
    L6:
        return false;
    L9:
        DefaultExtractorInput r12 = this;
        int r7 = r12.peekBufferPosition;
        r12.peekBufferPosition = r7 + r8;
        return true;
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public long getLength() {
        return this.streamLength;
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public long getPeekPosition() {
        return this.position + this.peekBufferPosition;
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public long getPosition() {
        return this.position;
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public int peek(byte[] r8, int r9, int r10) {
        ensureSpaceForPeek(r10);
        int r0 = this.peekBufferLength;
        int r3 = this.peekBufferPosition;
        int r02 = r0 - r3;
        if (r02 != 0) goto L8;
        DefaultExtractorInput r1 = this;
        int r7 = r1.readFromUpstream(this.peekBuffer, r3, r10, 0, true);
        if (r7 != (-1)) goto L7;
        return -1;
    L7:
        r1.peekBufferLength += r7;
    L9:
        System.arraycopy(r1.peekBuffer, r1.peekBufferPosition, r8, r9, r7);
        r1.peekBufferPosition += r7;
        return r7;
    L8:
        r1 = this;
        r7 = Math.min(r10, r02);
        goto L9
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void peekFully(byte[] r2, int r3, int r4) {
        peekFully(r2, r3, r4, false);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public boolean peekFully(byte[] r1, int r2, int r3, boolean r4) {
        if (advancePeekPosition(r3, r4) == true) goto L6;
        return false;
    L6:
        System.arraycopy(this.peekBuffer, this.peekBufferPosition - r3, r1, r2, r3);
        return true;
    }

    @Override // androidx.media3.extractor.ExtractorInput, androidx.media3.common.DataReader
    public int read(byte[] r8, int r9, int r10) {
        int r0 = readFromPeekBuffer(r8, r9, r10);
        if (r0 != 0) goto L5;
        DefaultExtractorInput r1 = this;
        r0 = r1.readFromUpstream(r8, r9, r10, 0, true);
    L6:
        r1.commitBytesRead(r0);
        return r0;
    L5:
        r1 = this;
        goto L6
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void readFully(byte[] r2, int r3, int r4) {
        readFully(r2, r3, r4, false);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public boolean readFully(byte[] r8, int r9, int r10, boolean r11) {
        int r5 = readFromPeekBuffer(r8, r9, r10);
    L4:
        if (r5 >= r10) goto L7;
        if (r5 == (-1)) goto L7;
        r5 = readFromUpstream(r8, r9, r10, r5, r11);
    L7:
        commitBytesRead(r5);
        if (r5 == (-1)) goto L11;
        return true;
    L11:
        return false;
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void resetPeekPosition() {
        this.peekBufferPosition = 0;
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public int skip(int r8) {
        int r0 = skipFromPeekBuffer(r8);
        if (r0 != 0) goto L5;
        byte[] r2 = this.scratchSpace;
        DefaultExtractorInput r1 = this;
        r0 = r1.readFromUpstream(r2, 0, Math.min(r8, r2.length), 0, true);
    L6:
        r1.commitBytesRead(r0);
        return r0;
    L5:
        r1 = this;
        goto L6
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void skipFully(int r2) {
        skipFully(r2, false);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public boolean skipFully(int r8, boolean r9) {
        int r5 = skipFromPeekBuffer(r8);
    L4:
        if (r5 >= r8) goto L7;
        if (r5 == (-1)) goto L7;
        int r4 = Math.min(r8, this.scratchSpace.length + r5);
        r5 = readFromUpstream(this.scratchSpace, -r5, r4, r5, r9);
    L7:
        commitBytesRead(r5);
        if (r5 == (-1)) goto L11;
        return true;
    L11:
        return false;
    }
}
