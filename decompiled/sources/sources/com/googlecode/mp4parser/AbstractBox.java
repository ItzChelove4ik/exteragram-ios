package com.googlecode.mp4parser;

import com.coremedia.iso.BoxParser;
import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.util.CastUtils;
import com.googlecode.mp4parser.util.Logger;
import com.googlecode.mp4parser.util.Path;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/* loaded from: classes5.dex */
public abstract class AbstractBox implements Box {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static Logger LOG;
    private ByteBuffer content;
    long contentStartPosition;
    DataSource dataSource;
    private ByteBuffer deadBytes;
    boolean isParsed;
    boolean isRead;
    long memMapSize;
    long offset;
    private Container parent;
    protected String type;
    private byte[] userType;

    static {
        LOG = Logger.getLogger(AbstractBox.class);
    }

    public AbstractBox(String r3) {
        this.memMapSize = -1;
        this.deadBytes = null;
        this.type = r3;
        this.isRead = true;
        this.isParsed = true;
    }

    private void getHeader(ByteBuffer r3) {
        if (isSmallBox() == false) goto L5;
        IsoTypeWriter.writeUInt32(r3, getSize());
        r3.put(IsoFile.fourCCtoBytes(getType()));
    L7:
        if ("uuid".equals(getType()) == false) goto L10;
        r3.put(getUserType());
        return;
    L10:
        return;
    L5:
        IsoTypeWriter.writeUInt32(r3, 1);
        r3.put(IsoFile.fourCCtoBytes(getType()));
        IsoTypeWriter.writeUInt64(r3, getSize());
        goto L7
    }

    private boolean isSmallBox() {
        if ("uuid".equals(getType()) == false) goto L5;
        int r0 = 24;
    L7:
        if (this.isRead == false) goto L23;
        if (this.isParsed == false) goto L19;
        long r6 = getContentSize();
        ByteBuffer r10 = this.deadBytes;
        if (r10 == null) goto L13;
        int r102 = r10.limit();
    L15:
        if (((r6 + r102) + r0) >= 4294967296L) goto L17;
        return true;
    L17:
        return false;
    L13:
        r102 = 0;
        goto L15
    L19:
        if ((this.content.limit() + r0) >= 4294967296L) goto L21;
        return true;
    L21:
        return false;
    L23:
        if ((this.memMapSize + r0) >= 4294967296L) goto L25;
        return true;
    L25:
        return false;
    L5:
        r0 = 8;
        goto L7
    }

    private synchronized void readContent() {
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L4:
        if (this.isRead == false) goto L18;
        monitor-exit(this);
        return;
    L18:
        LOG.logDebug("mem mapping " + getType());     // Catch: Throwable -> L10 IOException -> L12
    L9:
        throw null;     // Catch: Throwable -> L10
    L12:
        e = move-exception;
        throw new RuntimeException(e);     // Catch: Throwable -> L10
    }

    public abstract void _parseDetails(ByteBuffer r1);

    @Override // com.coremedia.iso.boxes.Box
    public void getBox(WritableByteChannel r6) {
        int r2 = 8;
        int r3 = 0;
        int r4 = 16;
        if (this.isRead == false) goto L26;
        if (this.isParsed == false) goto L16;
        ByteBuffer r0 = ByteBuffer.allocate(CastUtils.l2i(getSize()));
        getHeader(r0);
        getContent(r0);
        ByteBuffer r1 = this.deadBytes;
        if (r1 == null) goto L13;
        r1.rewind();
    L10:
        if (this.deadBytes.remaining() <= 0) goto L13;
        r0.put(this.deadBytes);
    L13:
        r6.write((ByteBuffer) r0.rewind());
        return;
    L16:
        if (isSmallBox() == true) goto L20;
        r2 = 16;
    L20:
        if ("uuid".equals(getType()) == true) goto L23;
        r4 = 0;
    L23:
        ByteBuffer r02 = ByteBuffer.allocate(r2 + r4);
        getHeader(r02);
        r6.write((ByteBuffer) r02.rewind());
        r6.write((ByteBuffer) this.content.position(0));
        return;
    L26:
        if (isSmallBox() == true) goto L30;
        r2 = 16;
    L30:
        if ("uuid".equals(getType()) == false) goto L32;
        r3 = 16;
    L32:
        ByteBuffer r03 = ByteBuffer.allocate(r2 + r3);
        getHeader(r03);
        r6.write((ByteBuffer) r03.rewind());
        throw null;
    }

    public abstract void getContent(ByteBuffer r1);

    public abstract long getContentSize();

    public long getOffset() {
        return this.offset;
    }

    @Override // com.coremedia.iso.boxes.Box
    public Container getParent() {
        return this.parent;
    }

    public String getPath() {
        return Path.createPath(this);
    }

    @Override // com.coremedia.iso.boxes.Box
    public long getSize() {
        int r1 = 0;
        if (this.isRead == true) goto L5;
        long r2 = this.memMapSize;
    L14:
        if (r2 < 4294967288L) goto L16;
        int r0 = 8;
    L17:
        int r02 = r0 + 8;
        if ("uuid".equals(getType()) == false) goto L20;
        int r4 = 16;
    L21:
        long r22 = r2 + (r02 + r4);
        ByteBuffer r6 = this.deadBytes;
        if (r6 == null) goto L26;
        r1 = r6.limit();
    L26:
        return r22 + r1;
    L20:
        r4 = 0;
        goto L21
    L16:
        r0 = 0;
        goto L17
    L5:
        if (this.isParsed == false) goto L7;
        r2 = getContentSize();
        goto L14
    L7:
        ByteBuffer r03 = this.content;
        if (r03 == null) goto L10;
        int r04 = r03.limit();
    L11:
        r2 = r04;
        goto L14
    L10:
        r04 = 0;
        goto L11
    }

    @Override // com.coremedia.iso.boxes.Box
    public String getType() {
        return this.type;
    }

    public byte[] getUserType() {
        return this.userType;
    }

    public boolean isParsed() {
        return this.isParsed;
    }

    public void parse(DataSource r5, ByteBuffer r6, long r7, BoxParser r9) {
        long r0 = r5.position();
        this.contentStartPosition = r0;
        this.offset = r0 - r6.remaining();
        this.memMapSize = r7;
        r5.position(r5.position() + r7);
        this.isRead = false;
        this.isParsed = false;
    }

    public final synchronized void parseDetails() {
        monitor-enter(this);
        readContent();     // Catch: Throwable -> L8
        LOG.logDebug("parsing details of " + getType());     // Catch: Throwable -> L8
        ByteBuffer r0 = this.content;     // Catch: Throwable -> L8
        if (r0 == null) goto L11;
        this.isParsed = true;     // Catch: Throwable -> L8
        r0.rewind();     // Catch: Throwable -> L8
        _parseDetails(r0);     // Catch: Throwable -> L8
        if (r0.remaining() <= 0) goto L10;
        this.deadBytes = r0.slice();     // Catch: Throwable -> L8
    L10:
        this.content = null;     // Catch: Throwable -> L8
    L11:
        monitor-exit(this);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public void setDeadBytes(ByteBuffer r1) {
        this.deadBytes = r1;
    }

    @Override // com.coremedia.iso.boxes.Box
    public void setParent(Container r1) {
        this.parent = r1;
    }
}
