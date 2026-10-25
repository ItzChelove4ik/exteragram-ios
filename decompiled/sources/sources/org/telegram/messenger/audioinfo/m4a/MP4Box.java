package org.telegram.messenger.audioinfo.m4a;

import java.io.DataInput;
import java.io.DataInputStream;
import okio.internal.ZipFilesKt$$ExternalSyntheticBUOutline2;
import org.telegram.messenger.audioinfo.util.PositionInputStream;
import org.telegram.messenger.audioinfo.util.RangeInputStream;

/* loaded from: classes5.dex */
public abstract class MP4Box<I extends PositionInputStream> {
    private MP4Atom child;
    protected final DataInput data;
    private final I input;
    private final MP4Box<?> parent;
    private final String type;

    public MP4Box(I r1, MP4Box<?> r2, String r3) {
        this.input = r1;
        this.parent = r2;
        this.type = r3;
        this.data = new DataInputStream(r1);
    }

    public MP4Atom getChild() {
        return this.child;
    }

    public I getInput() {
        return this.input;
    }

    public MP4Box<?> getParent() {
        return this.parent;
    }

    public long getPosition() {
        return this.input.getPosition();
    }

    public String getType() {
        return this.type;
    }

    public MP4Atom nextChild() {
        MP4Atom r0 = this.child;
        if (r0 == null) goto L5;
        r0.skip();
    L5:
        int r02 = this.data.readInt();
        byte[] r1 = new byte[4];
        this.data.readFully(r1);
        String r2 = new String(r1, "ISO8859_1");
        if (r02 != 1) goto L8;
        RangeInputStream r3 = new RangeInputStream(this.input, 16, this.data.readLong() - 16);
    L9:
        MP4Atom r03 = new MP4Atom(r3, this, r2);
        this.child = r03;
        return r03;
    L8:
        r3 = new RangeInputStream(this.input, 8, r02 - 8);
        goto L9
    }

    public MP4Atom nextChild(String r3) {
        MP4Atom r2 = nextChild();
        if (r2.getType().matches(r3) == false) goto L5;
        return r2;
    L5:
        ZipFilesKt$$ExternalSyntheticBUOutline2.m("atom type mismatch, expected ", r3, ", got ", r2.getType());
        return null;
    }
}
