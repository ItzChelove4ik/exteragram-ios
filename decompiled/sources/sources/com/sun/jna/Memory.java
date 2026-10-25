package com.sun.jna;

import c.f$$ExternalSyntheticBUOutline1;
import com.sun.jna.internal.Cleaner;
import java.io.Closeable;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class Memory extends Pointer implements Closeable {
    private static final Map<Long, Reference<Memory>> allocatedMemory = null;
    private static final WeakMemoryHolder buffers = null;
    private final Cleaner.Cleanable cleanable;
    protected long size;

    public static final class MemoryDisposer implements Runnable {
        private long peer;

        public MemoryDisposer(long r1) {
            this.peer = r1;
        }

        @Override // java.lang.Runnable
        public synchronized void run() {
            monitor-enter(this);
            Memory.free(this.peer);     // Catch: Throwable -> L10
            Memory.access$000().remove(Long.valueOf(this.peer));     // Catch: Throwable -> L8
            this.peer = 0;     // Catch: Throwable -> L8
            monitor-exit(this);
            return;
        L8:
            th = move-exception;
            throw th;
        L10:
            th = move-exception;
            Memory.access$000().remove(Long.valueOf(this.peer));     // Catch: Throwable -> L8
            this.peer = 0;     // Catch: Throwable -> L8
            throw th;     // Catch: Throwable -> L8
        }
    }

    public class SharedMemory extends Memory {
        final /* synthetic */ Memory this$0;

        public SharedMemory(Memory r1, long r2, long r4) {
            this.this$0 = r1;
            this.size = r4;
            this.peer = r1.peer + r2;
        }

        @Override // com.sun.jna.Memory
        public void boundsCheck(long r6, long r8) {
            Memory r0 = this.this$0;
            r0.boundsCheck((this.peer - r0.peer) + r6, r8);
        }

        @Override // com.sun.jna.Memory
        public synchronized void dispose() {
            monitor-enter(this);
            this.peer = 0;     // Catch: Throwable -> L7
            monitor-exit(this);
            return;
        L7:
            th = move-exception;
            throw th;
        }

        @Override // com.sun.jna.Memory, com.sun.jna.Pointer
        public String toString() {
            return super.toString() + " (shared from " + this.this$0.toString() + ")";
        }
    }

    static {
        allocatedMemory = new ConcurrentHashMap();
        buffers = new WeakMemoryHolder();
    }

    public Memory() {
        this.cleanable = null;
    }

    public Memory(long r5) {
        this.size = r5;
        if (r5 <= 0) goto L10;
        long r2 = malloc(r5);
        this.peer = r2;
        if (r2 == 0) goto L9;
        allocatedMemory.put(Long.valueOf(r2), new WeakReference(this));
        this.cleanable = Cleaner.getCleaner().register(this, new MemoryDisposer(this.peer));
        return;
    L9:
        throw new OutOfMemoryError("Cannot allocate " + r5 + " bytes");
    L10:
        f$$ExternalSyntheticBUOutline1.m("Allocation size must be greater than zero");
        throw null;
    }

    public static /* synthetic */ Map access$000() {
        return allocatedMemory;
    }

    public static void disposeAll() {
        ArrayList r0 = new ArrayList(allocatedMemory.values());
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L7;
        Object r3 = r0.get(r2);
        r2 = r2 + 1;
        Memory r32 = (Memory) ((Reference) r3).get();
        if (r32 == null) goto L3;
        r32.close();
        goto L3
    }

    public static void free(long r2) {
        if (r2 == 0) goto L6;
        Native.free(r2);
        return;
    }

    public static long malloc(long r0) {
        return Native.malloc(r0);
    }

    public static void purge() {
        buffers.clean();
    }

    private Pointer shareReferenceIfInBounds(Pointer r5) {
        if (r5 != null) goto L5;
        return null;
    L5:
        long r0 = r5.peer - this.peer;
        if (r0 >= 0) goto L8;
    L11:
        return r5;
    L8:
        if (r0 >= this.size) goto L11;
        return share(r0);
    }

    public Memory align(int r11) {
        if (r11 <= 0) goto L19;
        int r0 = 0;
    L5:
        if (r0 >= 32) goto L17;
        if (r11 == (1 << r0)) goto L8;
        r0 = r0 + 1;
        goto L5
    L8:
        long r02 = r11;
        long r4 = ~(r02 - 1);
        long r6 = this.peer;
        if ((r6 & r4) == r6) goto L23;
        long r03 = ((r02 + r6) - 1) & r4;
        long r2 = (this.size + r6) - r03;
        if (r2 > 0) goto L13;
        f$$ExternalSyntheticBUOutline1.m("Insufficient memory to align to the requested boundary");
        return null;
    L13:
        return (Memory) share(r03 - r6, r2);
    L23:
        return this;
    L17:
        f$$ExternalSyntheticBUOutline1.m("Byte boundary must be a power of two");
        return null;
    L19:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Byte boundary must be positive: ", r11);
        return null;
    }

    public void boundsCheck(long r3, long r5) {
        if (r3 < 0) goto L10;
        long r32 = r3 + r5;
        if (r32 > this.size) goto L8;
        return;
    L8:
        throw new IndexOutOfBoundsException("Bounds exceeds available space : size=" + this.size + ", offset=" + r32);
    L10:
        throw new IndexOutOfBoundsException("Invalid offset: " + r3);
    }

    public void clear() {
        clear(this.size);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.peer = 0;
        Cleaner.Cleanable r2 = this.cleanable;
        if (r2 == null) goto L6;
        r2.clean();
        return;
    }

    @Deprecated
    public void dispose() {
        close();
    }

    public String dump() {
        return dump(0, (int) size());
    }

    @Override // com.sun.jna.Pointer
    public byte getByte(long r3) {
        boundsCheck(r3, 1);
        return super.getByte(r3);
    }

    @Override // com.sun.jna.Pointer
    public ByteBuffer getByteBuffer(long r1, long r3) {
        boundsCheck(r1, r3);
        ByteBuffer r12 = super.getByteBuffer(r1, r3);
        buffers.put(r12, this);
        return r12;
    }

    @Override // com.sun.jna.Pointer
    public char getChar(long r3) {
        boundsCheck(r3, Native.WCHAR_SIZE);
        return super.getChar(r3);
    }

    @Override // com.sun.jna.Pointer
    public double getDouble(long r3) {
        boundsCheck(r3, 8);
        return super.getDouble(r3);
    }

    @Override // com.sun.jna.Pointer
    public float getFloat(long r3) {
        boundsCheck(r3, 4);
        return super.getFloat(r3);
    }

    @Override // com.sun.jna.Pointer
    public int getInt(long r3) {
        boundsCheck(r3, 4);
        return super.getInt(r3);
    }

    @Override // com.sun.jna.Pointer
    public long getLong(long r3) {
        boundsCheck(r3, 8);
        return super.getLong(r3);
    }

    @Override // com.sun.jna.Pointer
    public Pointer getPointer(long r3) {
        boundsCheck(r3, Native.POINTER_SIZE);
        return shareReferenceIfInBounds(super.getPointer(r3));
    }

    @Override // com.sun.jna.Pointer
    public short getShort(long r3) {
        boundsCheck(r3, 2);
        return super.getShort(r3);
    }

    @Override // com.sun.jna.Pointer
    public String getString(long r3, String r5) {
        boundsCheck(r3, 0);
        return super.getString(r3, r5);
    }

    @Override // com.sun.jna.Pointer
    public String getWideString(long r3) {
        boundsCheck(r3, 0);
        return super.getWideString(r3);
    }

    @Override // com.sun.jna.Pointer
    public void read(long r3, byte[] r5, int r6, int r7) {
        boundsCheck(r3, r7);
        super.read(r3, r5, r6, r7);
    }

    @Override // com.sun.jna.Pointer
    public void read(long r3, char[] r5, int r6, int r7) {
        boundsCheck(r3, Native.WCHAR_SIZE * r7);
        super.read(r3, r5, r6, r7);
    }

    @Override // com.sun.jna.Pointer
    public void read(long r5, double[] r7, int r8, int r9) {
        boundsCheck(r5, r9 * 8);
        super.read(r5, r7, r8, r9);
    }

    @Override // com.sun.jna.Pointer
    public void read(long r5, float[] r7, int r8, int r9) {
        boundsCheck(r5, r9 * 4);
        super.read(r5, r7, r8, r9);
    }

    @Override // com.sun.jna.Pointer
    public void read(long r5, int[] r7, int r8, int r9) {
        boundsCheck(r5, r9 * 4);
        super.read(r5, r7, r8, r9);
    }

    @Override // com.sun.jna.Pointer
    public void read(long r5, long[] r7, int r8, int r9) {
        boundsCheck(r5, r9 * 8);
        super.read(r5, r7, r8, r9);
    }

    @Override // com.sun.jna.Pointer
    public void read(long r3, Pointer[] r5, int r6, int r7) {
        boundsCheck(r3, Native.POINTER_SIZE * r7);
        super.read(r3, r5, r6, r7);
    }

    @Override // com.sun.jna.Pointer
    public void read(long r5, short[] r7, int r8, int r9) {
        boundsCheck(r5, r9 * 2);
        super.read(r5, r7, r8, r9);
    }

    @Override // com.sun.jna.Pointer
    public void setByte(long r3, byte r5) {
        boundsCheck(r3, 1);
        super.setByte(r3, r5);
    }

    @Override // com.sun.jna.Pointer
    public void setChar(long r3, char r5) {
        boundsCheck(r3, Native.WCHAR_SIZE);
        super.setChar(r3, r5);
    }

    @Override // com.sun.jna.Pointer
    public void setDouble(long r3, double r5) {
        boundsCheck(r3, 8);
        super.setDouble(r3, r5);
    }

    @Override // com.sun.jna.Pointer
    public void setFloat(long r3, float r5) {
        boundsCheck(r3, 4);
        super.setFloat(r3, r5);
    }

    @Override // com.sun.jna.Pointer
    public void setInt(long r3, int r5) {
        boundsCheck(r3, 4);
        super.setInt(r3, r5);
    }

    @Override // com.sun.jna.Pointer
    public void setLong(long r3, long r5) {
        boundsCheck(r3, 8);
        super.setLong(r3, r5);
    }

    @Override // com.sun.jna.Pointer
    public void setPointer(long r3, Pointer r5) {
        boundsCheck(r3, Native.POINTER_SIZE);
        super.setPointer(r3, r5);
    }

    @Override // com.sun.jna.Pointer
    public void setShort(long r3, short r5) {
        boundsCheck(r3, 2);
        super.setShort(r3, r5);
    }

    @Override // com.sun.jna.Pointer
    public void setString(long r5, String r7, String r8) {
        boundsCheck(r5, Native.getBytes(r7, r8).length + 1);
        super.setString(r5, r7, r8);
    }

    @Override // com.sun.jna.Pointer
    public void setWideString(long r5, String r7) {
        boundsCheck(r5, (r7.length() + 1) * Native.WCHAR_SIZE);
        super.setWideString(r5, r7);
    }

    @Override // com.sun.jna.Pointer
    public Pointer share(long r3) {
        return share(r3, size() - r3);
    }

    @Override // com.sun.jna.Pointer
    public Pointer share(long r7, long r9) {
        boundsCheck(r7, r9);
        return new SharedMemory(this, r7, r9);
    }

    public long size() {
        return this.size;
    }

    @Override // com.sun.jna.Pointer
    public String toString() {
        return "allocated@0x" + Long.toHexString(this.peer) + " (" + this.size + " bytes)";
    }

    public boolean valid() {
        if (this.peer == 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.sun.jna.Pointer
    public void write(long r3, byte[] r5, int r6, int r7) {
        boundsCheck(r3, r7);
        super.write(r3, r5, r6, r7);
    }

    @Override // com.sun.jna.Pointer
    public void write(long r3, char[] r5, int r6, int r7) {
        boundsCheck(r3, Native.WCHAR_SIZE * r7);
        super.write(r3, r5, r6, r7);
    }

    @Override // com.sun.jna.Pointer
    public void write(long r5, double[] r7, int r8, int r9) {
        boundsCheck(r5, r9 * 8);
        super.write(r5, r7, r8, r9);
    }

    @Override // com.sun.jna.Pointer
    public void write(long r5, float[] r7, int r8, int r9) {
        boundsCheck(r5, r9 * 4);
        super.write(r5, r7, r8, r9);
    }

    @Override // com.sun.jna.Pointer
    public void write(long r5, int[] r7, int r8, int r9) {
        boundsCheck(r5, r9 * 4);
        super.write(r5, r7, r8, r9);
    }

    @Override // com.sun.jna.Pointer
    public void write(long r5, long[] r7, int r8, int r9) {
        boundsCheck(r5, r9 * 8);
        super.write(r5, r7, r8, r9);
    }

    @Override // com.sun.jna.Pointer
    public void write(long r3, Pointer[] r5, int r6, int r7) {
        boundsCheck(r3, Native.POINTER_SIZE * r7);
        super.write(r3, r5, r6, r7);
    }

    @Override // com.sun.jna.Pointer
    public void write(long r5, short[] r7, int r8, int r9) {
        boundsCheck(r5, r9 * 2);
        super.write(r5, r7, r8, r9);
    }
}
