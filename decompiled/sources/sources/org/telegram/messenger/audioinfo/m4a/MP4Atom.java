package org.telegram.messenger.audioinfo.m4a;

import java.io.EOFException;
import java.math.BigDecimal;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline1;
import okio.ZipFileSystem$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.audioinfo.util.RangeInputStream;

/* loaded from: classes5.dex */
public class MP4Atom extends MP4Box<RangeInputStream> {
    public MP4Atom(RangeInputStream r1, MP4Box<?> r2, String r3) {
        super(r1, r2, r3);
    }

    private StringBuffer appendPath(StringBuffer r2, MP4Box<?> r3) {
        if (r3.getParent() == null) goto L5;
        appendPath(r2, r3.getParent());
        r2.append("/");
    L5:
        r2.append(r3.getType());
        return r2;
    }

    public long getLength() {
        return getInput().getPosition() + getInput().getRemainingLength();
    }

    public long getOffset() {
        return getParent().getPosition() - getPosition();
    }

    public String getPath() {
        return appendPath(new StringBuffer(), this).toString();
    }

    public long getRemaining() {
        return getInput().getRemainingLength();
    }

    public boolean hasMoreChildren() {
        if (getChild() == null) goto L5;
        long r0 = getChild().getRemaining();
    L7:
        if (r0 >= getRemaining()) goto L10;
        return true;
    L10:
        return false;
    L5:
        r0 = 0;
        goto L7
    }

    public MP4Atom nextChildUpTo(String r5) {
    L3:
        if (getRemaining() <= 0) goto L7;
        MP4Atom r0 = nextChild();
        if (r0.getType().matches(r5) == false) goto L3;
        return r0;
    L7:
        ZipFileSystem$$ExternalSyntheticBUOutline0.m("atom type mismatch, not found: ", r5);
        return null;
    }

    public boolean readBoolean() {
        return this.data.readBoolean();
    }

    public byte readByte() {
        return this.data.readByte();
    }

    public byte[] readBytes() {
        return readBytes((int) getRemaining());
    }

    public byte[] readBytes(int r1) {
        byte[] r12 = new byte[r1];
        this.data.readFully(r12);
        return r12;
    }

    public int readInt() {
        return this.data.readInt();
    }

    public BigDecimal readIntegerFixedPoint() {
        return new BigDecimal(String.valueOf(this.data.readShort()) + _UrlKt.FRAGMENT_ENCODE_SET + String.valueOf(this.data.readUnsignedShort()));
    }

    public long readLong() {
        return this.data.readLong();
    }

    public short readShort() {
        return this.data.readShort();
    }

    public BigDecimal readShortFixedPoint() {
        return new BigDecimal(String.valueOf(this.data.readByte()) + _UrlKt.FRAGMENT_ENCODE_SET + String.valueOf(this.data.readUnsignedByte()));
    }

    public String readString(int r2, String r3) {
        String r0 = new String(readBytes(r2), r3);
        int r22 = r0.indexOf(0);
        if (r22 >= 0) goto L6;
        return r0;
    L6:
        return r0.substring(0, r22);
    }

    public String readString(String r3) {
        return readString((int) getRemaining(), r3);
    }

    public void skip() {
    L3:
        if (getRemaining() <= 0) goto L9;
        if (getInput().skip(getRemaining()) != 0) goto L3;
        throw new EOFException("Cannot skip atom");
    }

    public void skip(int r4) {
        int r0 = 0;
    L3:
        if (r0 >= r4) goto L11;
        int r1 = this.data.skipBytes(r4 - r0);
        if (r1 <= 0) goto L7;
        r0 = r0 + r1;
        goto L3
    L7:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return;
    }

    public String toString() {
        StringBuffer r0 = new StringBuffer();
        appendPath(r0, this);
        r0.append("[off=");
        r0.append(getOffset());
        r0.append(",pos=");
        r0.append(getPosition());
        r0.append(",len=");
        r0.append(getLength());
        r0.append("]");
        return r0.toString();
    }
}
