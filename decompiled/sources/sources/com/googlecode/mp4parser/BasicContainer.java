package com.googlecode.mp4parser;

import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.util.Logger;
import java.io.Closeable;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class BasicContainer implements Container, Iterator<Box>, Closeable {
    private static final Box EOF = null;
    private static Logger LOG;
    private List<Box> boxes;
    long endPosition;
    Box lookahead;
    long parsePosition;
    long startPosition;

    /* renamed from: com.googlecode.mp4parser.BasicContainer$1, reason: invalid class name */
    public class AnonymousClass1 extends AbstractBox {
        public AnonymousClass1(String r1) {
            super(r1);
        }

        @Override // com.googlecode.mp4parser.AbstractBox
        public void _parseDetails(ByteBuffer r1) {
        }

        @Override // com.googlecode.mp4parser.AbstractBox
        public void getContent(ByteBuffer r1) {
        }

        @Override // com.googlecode.mp4parser.AbstractBox
        public long getContentSize() {
            return 0;
        }
    }

    static {
        EOF = new AnonymousClass1("eof ");
        LOG = Logger.getLogger(BasicContainer.class);
    }

    public BasicContainer() {
        this.lookahead = null;
        this.parsePosition = 0;
        this.startPosition = 0;
        this.endPosition = 0;
        this.boxes = new ArrayList();
    }

    public void addBox(Box r3) {
        if (r3 == null) goto L5;
        this.boxes = new ArrayList(getBoxes());
        r3.setParent(this);
        this.boxes.add(r3);
        return;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw null;
    }

    @Override // com.coremedia.iso.boxes.Container
    public List<Box> getBoxes() {
        return this.boxes;
    }

    public long getContainerSize() {
        long r0 = 0;
        int r2 = 0;
    L4:
        if (r2 >= getBoxes().size()) goto L5;
        r0 = r0 + this.boxes.get(r2).getSize();
        r2 = r2 + 1;
        goto L4
    L5:
        return r0;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        Box r0 = this.lookahead;
        if (r0 != EOF) goto L6;
        return false;
    L6:
        if (r0 == null) goto L12;
        return true;
    L12:
        this.lookahead = next();     // Catch: NoSuchElementException -> L10
        return true;
    L10:
        this.lookahead = EOF;
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    public Box next() {
        Box r0 = this.lookahead;
        if (r0 != null) goto L5;
    L8:
        this.lookahead = EOF;
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        if (r0 == EOF) goto L8;
        this.lookahead = null;
        return r0;
    }

    @Override // java.util.Iterator
    public /* bridge */ /* synthetic */ Box next() {
        return next();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        r0.append(getClass().getSimpleName());
        r0.append("[");
        int r1 = 0;
    L4:
        if (r1 >= this.boxes.size()) goto L5;
        if (r1 <= 0) goto L9;
        r0.append(";");
    L9:
        r0.append(this.boxes.get(r1).toString());
        r1 = r1 + 1;
        goto L4
    L5:
        r0.append("]");
        return r0.toString();
    }

    public final void writeContainer(WritableByteChannel r2) {
        Iterator<Box> r1 = getBoxes().iterator();
    L4:
        if (r1.hasNext() == false) goto L5;
        r1.next().getBox(r2);
        goto L4
    }
}
