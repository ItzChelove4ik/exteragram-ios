package com.google.firebase.crashlytics.internal.metadata;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.gms.cast.MediaStatus;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.UByte;
import okio.Buffer$$ExternalSyntheticBUOutline2;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
class QueueFile implements Closeable {
    private static final Logger LOGGER = null;
    private final byte[] buffer;
    private int elementCount;
    int fileLength;
    private Element first;
    private Element last;
    private final RandomAccessFile raf;

    /* renamed from: com.google.firebase.crashlytics.internal.metadata.QueueFile$1, reason: invalid class name */
    public class AnonymousClass1 implements ElementReader {
        boolean first;
        final /* synthetic */ QueueFile this$0;
        final /* synthetic */ StringBuilder val$builder;

        public AnonymousClass1(QueueFile r1, StringBuilder r2) {
            this.this$0 = r1;
            this.val$builder = r2;
            this.first = true;
        }

        @Override // com.google.firebase.crashlytics.internal.metadata.QueueFile.ElementReader
        public void read(InputStream r2, int r3) {
            if (this.first == false) goto L5;
            this.first = false;
        L6:
            this.val$builder.append(r3);
            return;
        L5:
            this.val$builder.append(", ");
            goto L6
        }
    }

    public static class Element {
        static final Element NULL = null;
        final int length;
        final int position;

        static {
            NULL = new Element(0, 0);
        }

        public Element(int r1, int r2) {
            this.position = r1;
            this.length = r2;
        }

        public String toString() {
            return getClass().getSimpleName() + "[position = " + this.position + ", length = " + this.length + "]";
        }
    }

    public final class ElementInputStream extends InputStream implements InputStreamRetargetInterface {
        private int position;
        private int remaining;
        final /* synthetic */ QueueFile this$0;

        private ElementInputStream(QueueFile r2, Element r3) {
            this.this$0 = r2;
            this.position = QueueFile.access$100(r2, r3.position + 4);
            this.remaining = r3.length;
        }

        public /* synthetic */ ElementInputStream(QueueFile r1, Element r2, AnonymousClass1 r3) {
            this(r1, r2);
        }

        @Override // java.io.InputStream
        public int read() {
            if (this.remaining != 0) goto L6;
            return -1;
        L6:
            QueueFile.access$400(this.this$0).seek(this.position);
            int r0 = QueueFile.access$400(this.this$0).read();
            this.position = QueueFile.access$100(this.this$0, this.position + 1);
            this.remaining--;
            return r0;
        }

        @Override // java.io.InputStream
        public int read(byte[] r3, int r4, int r5) {
            QueueFile.access$200(r3, "buffer");
            if ((r4 | r5) < 0) goto L15;
            if (r5 > (r3.length - r4)) goto L15;
            int r0 = this.remaining;
            if (r0 <= 0) goto L12;
            if (r5 <= r0) goto L10;
            r5 = r0;
        L10:
            QueueFile.access$300(this.this$0, this.position, r3, r4, r5);
            this.position = QueueFile.access$100(this.this$0, this.position + r5);
            this.remaining -= r5;
            return r5;
        L12:
            return -1;
        L15:
            throw new ArrayIndexOutOfBoundsException();
        }

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public /* synthetic */ long transferTo(OutputStream r1) {
            return DesugarInputStream.transferTo(this, r1);
        }
    }

    public interface ElementReader {
        void read(InputStream r1, int r2);
    }

    static {
        LOGGER = Logger.getLogger(QueueFile.class.getName());
    }

    public QueueFile(File r2) {
        this.buffer = new byte[16];
        if (r2.exists() == true) goto L5;
        initialize(r2);
    L5:
        this.raf = open(r2);
        readHeader();
    }

    public static /* synthetic */ int access$100(QueueFile r0, int r1) {
        return r0.wrapPosition(r1);
    }

    public static /* synthetic */ Object access$200(Object r0, String r1) {
        return nonNull(r0, r1);
    }

    public static /* synthetic */ void access$300(QueueFile r0, int r1, byte[] r2, int r3, int r4) {
        r0.ringRead(r1, r2, r3, r4);
    }

    public static /* synthetic */ RandomAccessFile access$400(QueueFile r0) {
        return r0.raf;
    }

    private void expandIfNecessary(int r9) {
        int r92 = r9 + 4;
        int r0 = remainingBytes();
        if (r0 < r92) goto L5;
        return;
    L5:
        int r1 = this.fileLength;
    L6:
        r0 = r0 + r1;
        r1 = r1 << 1;
        if (r0 < r92) goto L6;
        setLength(r1);
        Element r93 = this.last;
        int r94 = wrapPosition((r93.position + 4) + r93.length);
        if (r94 >= this.first.position) goto L15;
        FileChannel r2 = this.raf.getChannel();
        r2.position(this.fileLength);
        long r5 = r94 - 4;
        if (r2.transferTo(16, r5, r2) == r5) goto L15;
        Buffer$$ExternalSyntheticBUOutline2.m("Copied insufficient number of bytes!");
        return;
    L15:
        int r95 = this.last.position;
        int r02 = this.first.position;
        if (r95 >= r02) goto L18;
        int r22 = (this.fileLength + r95) - 16;
        writeHeader(r1, this.elementCount, r02, r22);
        this.last = new Element(r22, this.last.length);
    L19:
        this.fileLength = r1;
        return;
    L18:
        writeHeader(r1, this.elementCount, r02, r95);
        goto L19
    }

    private static void initialize(File r5) {
        File r0 = new File(r5.getPath() + ".tmp");
        RandomAccessFile r1 = open(r0);
        r1.setLength(MediaStatus.COMMAND_EDIT_TRACKS);     // Catch: Throwable -> L9
        r1.seek(0);     // Catch: Throwable -> L9
        byte[] r2 = new byte[16];     // Catch: Throwable -> L9
        writeInts(r2, new int[]{4096, 0, 0, 0});     // Catch: Throwable -> L9
        r1.write(r2);     // Catch: Throwable -> L9
        r1.close();
        if (r0.renameTo(r5) == false) goto L7;
        return;
    L7:
        Model$$ExternalSyntheticBUOutline0.m("Rename failed!");
        return;
    L9:
        th = move-exception;
        r1.close();
        throw th;
    }

    private static <T> T nonNull(T r0, String r1) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        f$$ExternalSyntheticBUOutline2.m(r1);
        return null;
    }

    private static RandomAccessFile open(File r2) {
        return new RandomAccessFile(r2, "rwd");
    }

    private Element readElement(int r4) {
        if (r4 == 0) goto L4;
        this.raf.seek(r4);
        return new Element(r4, this.raf.readInt());
    L4:
        return Element.NULL;
    }

    private void readHeader() {
        this.raf.seek(0);
        this.raf.readFully(this.buffer);
        int r0 = readInt(this.buffer, 0);
        this.fileLength = r0;
        if (r0 > this.raf.length()) goto L7;
        this.elementCount = readInt(this.buffer, 4);
        int r02 = readInt(this.buffer, 8);
        int r1 = readInt(this.buffer, 12);
        this.first = readElement(r02);
        this.last = readElement(r1);
        return;
    L7:
        throw new IOException("File is truncated. Expected length: " + this.fileLength + ", Actual length: " + this.raf.length());
    }

    private static int readInt(byte[] r2, int r3) {
        return ((((r2[r3] & UByte.MAX_VALUE) << 24) + ((r2[r3 + 1] & UByte.MAX_VALUE) << 16)) + ((r2[r3 + 2] & UByte.MAX_VALUE) << 8)) + (r2[r3 + 3] & UByte.MAX_VALUE);
    }

    private int remainingBytes() {
        return this.fileLength - usedBytes();
    }

    private void ringRead(int r6, byte[] r7, int r8, int r9) {
        int r62 = wrapPosition(r6);
        int r0 = r62 + r9;
        int r1 = this.fileLength;
        RandomAccessFile r2 = this.raf;
        if (r0 > r1) goto L6;
        r2.seek(r62);
        this.raf.readFully(r7, r8, r9);
        return;
    L6:
        int r12 = r1 - r62;
        r2.seek(r62);
        this.raf.readFully(r7, r8, r12);
        this.raf.seek(16);
        this.raf.readFully(r7, r8 + r12, r9 - r12);
    }

    private void ringWrite(int r6, byte[] r7, int r8, int r9) {
        int r62 = wrapPosition(r6);
        int r0 = r62 + r9;
        int r1 = this.fileLength;
        RandomAccessFile r2 = this.raf;
        if (r0 > r1) goto L6;
        r2.seek(r62);
        this.raf.write(r7, r8, r9);
        return;
    L6:
        int r12 = r1 - r62;
        r2.seek(r62);
        this.raf.write(r7, r8, r12);
        this.raf.seek(16);
        this.raf.write(r7, r8 + r12, r9 - r12);
    }

    private void setLength(int r4) {
        this.raf.setLength(r4);
        this.raf.getChannel().force(true);
    }

    private int wrapPosition(int r1) {
        int r0 = this.fileLength;
        if (r1 >= r0) goto L6;
        return r1;
    L6:
        return (r1 + 16) - r0;
    }

    private void writeHeader(int r2, int r3, int r4, int r5) {
        writeInts(this.buffer, new int[]{r2, r3, r4, r5});
        this.raf.seek(0);
        this.raf.write(this.buffer);
    }

    private static void writeInt(byte[] r2, int r3, int r4) {
        r2[r3] = (byte) (r4 >> 24);
        r2[r3 + 1] = (byte) (r4 >> 16);
        r2[r3 + 2] = (byte) (r4 >> 8);
        r2[r3 + 3] = (byte) r4;
    }

    private static void writeInts(byte[] r4, int... r5) {
        int r0 = r5.length;
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L5;
        writeInt(r4, r2, r5[r1]);
        r2 = r2 + 4;
        r1 = r1 + 1;
        goto L3
    }

    public void add(byte[] r3) {
        add(r3, 0, r3.length);
    }

    public synchronized void add(byte[] r7, int r8, int r9) {
        monitor-enter(this);
        nonNull(r7, "buffer");     // Catch: Throwable -> L14
        if ((r8 | r9) < 0) goto L23;
        if (r9 > (r7.length - r8)) goto L23;
        expandIfNecessary(r9);     // Catch: Throwable -> L14
        boolean r0 = isEmpty();     // Catch: Throwable -> L14
        if (r0 == false) goto L10;
        int r2 = 16;
    L11:
        Element r3 = new Element(r2, r9);     // Catch: Throwable -> L14
        writeInt(this.buffer, 0, r9);     // Catch: Throwable -> L14
        ringWrite(r3.position, this.buffer, 0, 4);     // Catch: Throwable -> L14
        ringWrite(r3.position + 4, r7, r8, r9);     // Catch: Throwable -> L14
        if (r0 == false) goto L16;
        int r72 = r3.position;     // Catch: Throwable -> L14
    L17:
        writeHeader(this.fileLength, this.elementCount + 1, r72, r3.position);     // Catch: Throwable -> L14
        this.last = r3;     // Catch: Throwable -> L14
        this.elementCount++;
        if (r0 == false) goto L20;
        this.first = r3;     // Catch: Throwable -> L14
    L20:
        monitor-exit(this);
        return;
    L16:
        r72 = this.first.position;     // Catch: Throwable -> L14
        goto L17
    L10:
        Element r22 = this.last;     // Catch: Throwable -> L14
        r2 = wrapPosition((r22.position + 4) + r22.length);     // Catch: Throwable -> L14
    L23:
        throw new IndexOutOfBoundsException();     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        throw th;
    }

    public synchronized void clear() {
        monitor-enter(this);
        writeHeader(4096, 0, 0, 0);     // Catch: Throwable -> L7
        this.elementCount = 0;     // Catch: Throwable -> L7
        Element r0 = Element.NULL;     // Catch: Throwable -> L7
        this.first = r0;     // Catch: Throwable -> L7
        this.last = r0;     // Catch: Throwable -> L7
        if (this.fileLength <= 4096) goto L9;
        setLength(4096);     // Catch: Throwable -> L7
    L9:
        this.fileLength = 4096;     // Catch: Throwable -> L7
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        monitor-enter(this);
        this.raf.close();     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized void forEach(ElementReader r5) {
        monitor-enter(this);
        int r0 = this.first.position;     // Catch: Throwable -> L8
        int r1 = 0;
    L4:
        if (r1 >= this.elementCount) goto L10;
        Element r02 = readElement(r0);     // Catch: Throwable -> L8
        r5.read(new ElementInputStream(this, r02, null), r02.length);     // Catch: Throwable -> L8
        r0 = wrapPosition((r02.position + 4) + r02.length);     // Catch: Throwable -> L8
        r1 = r1 + 1;
        goto L4
    L10:
        monitor-exit(this);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public synchronized boolean isEmpty() {
        monitor-enter(this);
        if (this.elementCount != 0) goto L6;
        boolean r0 = true;
    L7:
        monitor-exit(this);
        return r0;
    L6:
        r0 = false;
    L9:
        th = move-exception;
        throw th;
    }

    public synchronized void remove() {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (isEmpty() == true) goto L14;
        if (this.elementCount != 1) goto L10;
        clear();     // Catch: Throwable -> L8
    L11:
        monitor-exit(this);
        return;
    L10:
        Element r0 = this.first;     // Catch: Throwable -> L8
        int r02 = wrapPosition((r0.position + 4) + r0.length);     // Catch: Throwable -> L8
        ringRead(r02, this.buffer, 0, 4);     // Catch: Throwable -> L8
        int r2 = readInt(this.buffer, 0);     // Catch: Throwable -> L8
        writeHeader(this.fileLength, this.elementCount - 1, r02, this.last.position);     // Catch: Throwable -> L8
        this.elementCount--;
        this.first = new Element(r02, r2);     // Catch: Throwable -> L8
        goto L11
    L14:
        throw new NoSuchElementException();     // Catch: Throwable -> L8
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        r0.append(getClass().getSimpleName());
        r0.append("[fileLength=");
        r0.append(this.fileLength);
        r0.append(", size=");
        r0.append(this.elementCount);
        r0.append(", first=");
        r0.append(this.first);
        r0.append(", last=");
        r0.append(this.last);
        r0.append(", element lengths=[");
        forEach(new AnonymousClass1(this, r0));     // Catch: IOException -> L5
    L7:
        r0.append("]]");
        return r0.toString();
    L5:
        e = move-exception;
        LOGGER.log(Level.WARNING, "read error", e);
        goto L7
    }

    public int usedBytes() {
        if (this.elementCount != 0) goto L5;
        return 16;
    L5:
        Element r0 = this.last;
        int r2 = r0.position;
        int r3 = this.first.position;
        if (r2 < r3) goto L10;
        return (((r2 - r3) + 4) + r0.length) + 16;
    L10:
        return (((r2 + 4) + r0.length) + this.fileLength) - r3;
    }
}
