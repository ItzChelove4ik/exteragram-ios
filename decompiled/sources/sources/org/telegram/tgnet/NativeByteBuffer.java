package org.telegram.tgnet;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import okhttp3.internal.url._UrlKt;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public class NativeByteBuffer extends AbstractSerializedData {
    private static final int MAX_POOLED_WRAPPERS = 64;
    private static final ThreadLocal<LinkedList<NativeByteBuffer>> addressWrappers = null;
    protected long address;
    public ByteBuffer buffer;
    private boolean justCalc;
    private int len;
    public boolean reused;

    /* renamed from: org.telegram.tgnet.NativeByteBuffer$1, reason: invalid class name */
    public class AnonymousClass1 extends ThreadLocal<LinkedList<NativeByteBuffer>> {
        public AnonymousClass1() {
        }

        @Override // java.lang.ThreadLocal
        public /* bridge */ /* synthetic */ LinkedList<NativeByteBuffer> initialValue() {
            return initialValue2();
        }

        @Override // java.lang.ThreadLocal
        /* renamed from: initialValue, reason: avoid collision after fix types in other method */
        public LinkedList<NativeByteBuffer> initialValue2() {
            return new LinkedList();
        }
    }

    static {
        addressWrappers = new AnonymousClass1();
    }

    public NativeByteBuffer(int r5) {
        this.reused = true;
        if (r5 < 0) goto L9;
        long r0 = native_getFreeBuffer(r5);
        this.address = r0;
        if (r0 == 0) goto L10;
        ByteBuffer r02 = native_getJavaByteBuffer(r0);
        this.buffer = r02;
        r02.position(0);
        this.buffer.limit(r5);
        this.buffer.order(ByteOrder.LITTLE_ENDIAN);
        return;
    L10:
        return;
    L9:
        throw new Exception("invalid NativeByteBuffer size");
    }

    private NativeByteBuffer(int r1, boolean r2) {
        this.reused = true;
    }

    public NativeByteBuffer(boolean r2) {
        this.reused = true;
        this.justCalc = r2;
    }

    public static native long native_getFreeBuffer(int r0);

    public static native ByteBuffer native_getJavaByteBuffer(long r0);

    public static native int native_limit(long r0);

    public static native int native_position(long r0);

    public static native void native_reuse(long r0);

    public static NativeByteBuffer wrap(long r3) {
        if (r3 == 0) goto L12;
        NativeByteBuffer r0 = addressWrappers.get().poll();
        if (r0 != null) goto L7;
        r0 = new NativeByteBuffer(0, true);
    L7:
        r0.address = r3;
        r0.reused = false;
        ByteBuffer r1 = native_getJavaByteBuffer(r3);
        r0.buffer = r1;
        r1.limit(native_limit(r3));
        int r32 = native_position(r3);
        if (r32 > r0.buffer.limit()) goto L10;
        r0.buffer.position(r32);
    L10:
        r0.buffer.order(ByteOrder.LITTLE_ENDIAN);
        return r0;
    L12:
        return null;
    }

    public int capacity() {
        return this.buffer.capacity();
    }

    public void compact() {
        this.buffer.compact();
    }

    public void finalize() {
        if (this.reused == true) goto L5;
        reuse(true);
    L5:
        super.finalize();
    }

    public int getIntFromByte(byte r1) {
        if (r1 < 0) goto L5;
        return r1;
    L5:
        return r1 + 256;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData, org.telegram.tgnet.OutputSerializedData
    public int getPosition() {
        return this.buffer.position();
    }

    public boolean hasRemaining() {
        return this.buffer.hasRemaining();
    }

    public String hex() {
        return Utilities.bytesToHex(readData(Math.min(limit(), 1024), true));
    L4:
        e = move-exception;
        FileLog.e(e);
        return "<err>";
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData
    public int length() {
        if (this.justCalc == true) goto L7;
        return this.buffer.position();
    L7:
        return this.len;
    }

    public int limit() {
        return this.buffer.limit();
    }

    public void limit(int r1) {
        this.buffer.limit(r1);
    }

    public int position() {
        return this.buffer.position();
    }

    public void position(int r1) {
        this.buffer.position(r1);
    }

    public void put(ByteBuffer r1) {
        this.buffer.put(r1);
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData
    public boolean readBool(boolean r3) {
        int r2 = readInt32(r3);
        if (r2 != (-1720552011)) goto L7;
        return true;
    L7:
        if (r2 != (-1132882121)) goto L10;
        return false;
    L10:
        if (r3 == false) goto L12;
        GlShader$$ExternalSyntheticBUOutline1.m("Not bool value!");
        return false;
    L12:
        if (BuildVars.LOGS_ENABLED == false) goto L14;
        FileLog.e("Not bool value!");
    L14:
        return false;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData
    public byte readByte(boolean r2) {
        return this.buffer.get();
    L4:
        e = move-exception;
        if (r2 == false) goto L8;
        Make$Map$$ExternalSyntheticBUOutline0.m("read byte error", e);
        return 0;
    L8:
        if (BuildVars.LOGS_ENABLED == false) goto L16;
        FileLog.e("read byte error");
        FileLog.e(e);
        return 0;
    L16:
        return 0;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData
    public byte[] readByteArray(boolean r6) {
        int r0 = getIntFromByte(this.buffer.get());     // Catch: Exception -> L5
        if (r0 < 254) goto L7;
        r0 = (getIntFromByte(this.buffer.get()) | (getIntFromByte(this.buffer.get()) << 8)) | (getIntFromByte(this.buffer.get()) << 16);     // Catch: Exception -> L5
        int r1 = 4;
    L9:
        if (r0 > remaining()) goto L17;
        if (r0 < 0) goto L17;
        byte[] r3 = new byte[r0];     // Catch: Exception -> L5
        this.buffer.get(r3);     // Catch: Exception -> L5
    L13:
        if (((r0 + r1) % 4) == 0) goto L15;
        this.buffer.get();     // Catch: Exception -> L5
        r1 = r1 + 1;     // Catch: Exception -> L5
        goto L13
    L15:
        return r3;
    L17:
        throw new RuntimeException("byte array size too big");     // Catch: Exception -> L5
    L7:
        r1 = 1;
    L5:
        e = move-exception;
        if (r6 == false) goto L21;
        Make$Map$$ExternalSyntheticBUOutline0.m("read byte array error", e);
        return null;
    L21:
        if (BuildVars.LOGS_ENABLED == false) goto L24;
        FileLog.e("read byte array error");
        FileLog.e(e);
    L24:
        return new byte[0];
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData
    public NativeByteBuffer readByteBuffer(boolean r8) {
        int r0 = getIntFromByte(this.buffer.get());     // Catch: Exception -> L5
        if (r0 < 254) goto L7;
        r0 = (getIntFromByte(this.buffer.get()) | (getIntFromByte(this.buffer.get()) << 8)) | (getIntFromByte(this.buffer.get()) << 16);     // Catch: Exception -> L5
        int r1 = 4;
    L9:
        if (r0 > remaining()) goto L17;
        if (r0 < 0) goto L17;
        NativeByteBuffer r3 = new NativeByteBuffer(r0);     // Catch: Exception -> L5
        int r4 = this.buffer.limit();     // Catch: Exception -> L5
        ByteBuffer r5 = this.buffer;     // Catch: Exception -> L5
        r5.limit(r5.position() + r0);     // Catch: Exception -> L5
        r3.buffer.put(this.buffer);     // Catch: Exception -> L5
        this.buffer.limit(r4);     // Catch: Exception -> L5
        r3.buffer.position(0);     // Catch: Exception -> L5
    L13:
        if (((r0 + r1) % 4) == 0) goto L15;
        this.buffer.get();     // Catch: Exception -> L5
        r1 = r1 + 1;     // Catch: Exception -> L5
        goto L13
    L15:
        return r3;
    L17:
        throw new RuntimeException("byte array size too big");     // Catch: Exception -> L5
    L7:
        r1 = 1;
    L5:
        e = move-exception;
        if (r8 == false) goto L21;
        Make$Map$$ExternalSyntheticBUOutline0.m("read byte array error", e);
        return null;
    L21:
        if (BuildVars.LOGS_ENABLED == false) goto L29;
        FileLog.e("read byte array error");
        FileLog.e(e);
        return null;
    L29:
        return null;
    }

    public void readBytes(byte[] r1, int r2, int r3, boolean r4) {
        this.buffer.get(r1, r2, r3);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        if (r4 == false) goto L8;
        Make$Map$$ExternalSyntheticBUOutline0.m("read raw error", e);
        return;
    L8:
        if (BuildVars.LOGS_ENABLED == false) goto L15;
        FileLog.e("read raw error");
        FileLog.e(e);
        return;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData
    public void readBytes(byte[] r1, boolean r2) {
        this.buffer.get(r1);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        if (r2 == false) goto L8;
        Make$Map$$ExternalSyntheticBUOutline0.m("read raw error", e);
        return;
    L8:
        if (BuildVars.LOGS_ENABLED == false) goto L15;
        FileLog.e("read raw error");
        FileLog.e(e);
        return;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData
    public byte[] readData(int r1, boolean r2) {
        byte[] r12 = new byte[r1];
        readBytes(r12, r2);
        return r12;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData
    public double readDouble(boolean r3) {
        return Double.longBitsToDouble(readInt64(r3));
    L4:
        e = move-exception;
        if (r3 == false) goto L8;
        Make$Map$$ExternalSyntheticBUOutline0.m("read double error", e);
        return 0.0d;
    L8:
        if (BuildVars.LOGS_ENABLED == false) goto L16;
        FileLog.e("read double error");
        FileLog.e(e);
        return 0.0d;
    L16:
        return 0.0d;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData
    public float readFloat(boolean r2) {
        return Float.intBitsToFloat(this.buffer.getInt());
    L4:
        e = move-exception;
        if (r2 == false) goto L8;
        Make$Map$$ExternalSyntheticBUOutline0.m("read float error", e);
        return 0.0f;
    L8:
        if (BuildVars.LOGS_ENABLED == false) goto L16;
        FileLog.e("read float error");
        FileLog.e(e);
        return 0.0f;
    L16:
        return 0.0f;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData
    public int readInt32(boolean r2) {
        return this.buffer.getInt();
    L4:
        e = move-exception;
        if (r2 == false) goto L8;
        Make$Map$$ExternalSyntheticBUOutline0.m("read int32 error", e);
        return 0;
    L8:
        if (BuildVars.LOGS_ENABLED == false) goto L16;
        FileLog.e("read int32 error");
        FileLog.e(e);
        return 0;
    L16:
        return 0;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData
    public long readInt64(boolean r2) {
        return this.buffer.getLong();
    L4:
        e = move-exception;
        if (r2 == false) goto L8;
        Make$Map$$ExternalSyntheticBUOutline0.m("read int64 error", e);
        return 0;
    L8:
        if (BuildVars.LOGS_ENABLED == false) goto L16;
        FileLog.e("read int64 error");
        FileLog.e(e);
        return 0;
    L16:
        return 0;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData
    public String readString(boolean r7) {
        int r0 = getPosition();
        int r1 = getIntFromByte(this.buffer.get());     // Catch: Exception -> L6
        if (r1 < 254) goto L8;
        r1 = (getIntFromByte(this.buffer.get()) | (getIntFromByte(this.buffer.get()) << 8)) | (getIntFromByte(this.buffer.get()) << 16);     // Catch: Exception -> L6
        int r2 = 4;
    L10:
        if (r1 > remaining()) goto L19;
        if (r1 < 0) goto L19;
        byte[] r4 = new byte[r1];     // Catch: Exception -> L6
        this.buffer.get(r4);     // Catch: Exception -> L6
    L14:
        if (((r1 + r2) % 4) == 0) goto L17;
        this.buffer.get();     // Catch: Exception -> L6
        r2 = r2 + 1;     // Catch: Exception -> L6
        goto L14
    L17:
        return new String(r4, StandardCharsets.UTF_8);
    L19:
        throw new RuntimeException("string size too big");     // Catch: Exception -> L6
    L8:
        r2 = 1;
    L6:
        e = move-exception;
        if (r7 == false) goto L23;
        Make$Map$$ExternalSyntheticBUOutline0.m("read string error", e);
        return null;
    L23:
        if (BuildVars.LOGS_ENABLED == false) goto L25;
        FileLog.e("read string error");
        FileLog.e(e);
    L25:
        position(r0);
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData
    public int remaining() {
        return this.buffer.remaining();
    }

    public void reuse() {
        reuse(false);
    }

    public void reuse(boolean r5) {
        if (this.address == 0) goto L10;
        if (r5 == true) goto L8;
        LinkedList<NativeByteBuffer> r52 = addressWrappers.get();
        if (r52.size() >= 64) goto L8;
        r52.add(this);
    L8:
        this.reused = true;
        native_reuse(this.address);
        return;
    }

    public void rewind() {
        if (this.justCalc == false) goto L6;
        this.len = 0;
        return;
    L6:
        this.buffer.rewind();
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData, org.telegram.tgnet.OutputSerializedData
    public void skip(int r2) {
        if (r2 != 0) goto L5;
        return;
    L5:
        if (this.justCalc == true) goto L8;
        ByteBuffer r1 = this.buffer;
        r1.position(r1.position() + r2);
        return;
    L8:
        this.len += r2;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.OutputSerializedData
    public void writeBool(boolean r2) {
        if (this.justCalc == true) goto L9;
        if (r2 == false) goto L7;
        writeInt32(TLRPC.TL_boolTrue.constructor);
        return;
    L7:
        writeInt32(TLRPC.TL_boolFalse.constructor);
        return;
    L9:
        this.len += 4;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.OutputSerializedData
    public void writeByte(byte r2) {
    L8:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L14;
        FileLog.e("write byte error");
        FileLog.e(e);
        return;
    L14:
        return;
    L3:
        if (this.justCalc == true) goto L6;
        this.buffer.put(r2);     // Catch: Exception -> L8
        return;
    L6:
        this.len++;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.OutputSerializedData
    public void writeByte(int r1) {
        writeByte((byte) r1);
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.OutputSerializedData
    public void writeByteArray(byte[] r6) {
        int r0 = r6.length;     // Catch: Exception -> L26
        boolean r1 = this.justCalc;
        if (r0 > 253) goto L8;
        if (r1 == true) goto L7;
        this.buffer.put((byte) r6.length);     // Catch: Exception -> L26
    L12:
        if (this.justCalc == true) goto L14;
        this.buffer.put(r6);     // Catch: Exception -> L26
    L16:
        if (r6.length > 253) goto L18;
        int r02 = 1;
    L19:
        if (((r6.length + r02) % 4) == 0) goto L38;
        if (this.justCalc == true) goto L24;
        this.buffer.put((byte) 0);     // Catch: Exception -> L26
    L25:
        r02 = r02 + 1;
        goto L19
    L24:
        this.len++;
        goto L25
    L38:
        return;
    L18:
        r02 = 4;
        goto L19
    L14:
        this.len += r6.length;
        goto L16
    L7:
        this.len++;
        goto L12
    L8:
        if (r1 == true) goto L10;
        this.buffer.put((byte) -2);     // Catch: Exception -> L26
        this.buffer.put((byte) r6.length);     // Catch: Exception -> L26
        this.buffer.put((byte) (r6.length >> 8));     // Catch: Exception -> L26
        this.buffer.put((byte) (r6.length >> 16));     // Catch: Exception -> L26
        goto L12
    L10:
        this.len += 4;
    L26:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L37;
        FileLog.e("write byte array error");
        FileLog.e(e);
        return;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.OutputSerializedData
    public void writeByteArray(byte[] r6, int r7, int r8) {
        boolean r0 = this.justCalc;
        if (r8 > 253) goto L7;
        if (r0 == true) goto L6;
        this.buffer.put((byte) r8);     // Catch: Exception -> L24
    L11:
        if (this.justCalc == true) goto L13;
        this.buffer.put(r6, r7, r8);     // Catch: Exception -> L24
    L14:
        if (r8 > 253) goto L16;
        int r62 = 1;
    L17:
        if (((r8 + r62) % 4) == 0) goto L36;
        if (this.justCalc == true) goto L22;
        this.buffer.put((byte) 0);     // Catch: Exception -> L24
    L23:
        r62 = r62 + 1;
        goto L17
    L22:
        this.len++;
        goto L23
    L36:
        return;
    L16:
        r62 = 4;
        goto L17
    L13:
        this.len += r8;
        goto L14
    L6:
        this.len++;
        goto L11
    L7:
        if (r0 == true) goto L9;
        this.buffer.put((byte) -2);     // Catch: Exception -> L24
        this.buffer.put((byte) r8);     // Catch: Exception -> L24
        this.buffer.put((byte) (r8 >> 8));     // Catch: Exception -> L24
        this.buffer.put((byte) (r8 >> 16));     // Catch: Exception -> L24
        goto L11
    L9:
        this.len += 4;
    L24:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L35;
        FileLog.e("write byte array error");
        FileLog.e(e);
        return;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.OutputSerializedData
    public void writeByteBuffer(NativeByteBuffer r7) {
        int r0 = r7.limit();     // Catch: Exception -> L26
        boolean r1 = this.justCalc;
        if (r0 > 253) goto L8;
        if (r1 == true) goto L7;
        this.buffer.put((byte) r0);     // Catch: Exception -> L26
    L12:
        if (this.justCalc == true) goto L14;
        r7.rewind();     // Catch: Exception -> L26
        this.buffer.put(r7.buffer);     // Catch: Exception -> L26
    L15:
        if (r0 > 253) goto L17;
        int r72 = 1;
    L18:
        if (((r0 + r72) % 4) == 0) goto L25;
        if (this.justCalc == true) goto L23;
        this.buffer.put((byte) 0);     // Catch: Exception -> L26
    L24:
        r72 = r72 + 1;
        goto L18
    L23:
        this.len++;
        goto L24
    L25:
        return;
    L17:
        r72 = 4;
        goto L18
    L14:
        this.len += r0;
        goto L15
    L7:
        this.len++;
        goto L12
    L8:
        if (r1 == true) goto L10;
        this.buffer.put((byte) -2);     // Catch: Exception -> L26
        this.buffer.put((byte) r0);     // Catch: Exception -> L26
        this.buffer.put((byte) (r0 >> 8));     // Catch: Exception -> L26
        this.buffer.put((byte) (r0 >> 16));     // Catch: Exception -> L26
        goto L12
    L10:
        this.len += 4;
    L26:
        e = move-exception;
        FileLog.e(e);
    }

    public void writeBytes(NativeByteBuffer r2) {
        if (this.justCalc == false) goto L6;
        this.len += r2.limit();
        return;
    L6:
        r2.rewind();
        this.buffer.put(r2.buffer);
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.OutputSerializedData
    public void writeBytes(byte[] r2) {
    L8:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L14;
        FileLog.e("write raw error");
        FileLog.e(e);
        return;
    L14:
        return;
    L3:
        if (this.justCalc == true) goto L6;
        this.buffer.put(r2);     // Catch: Exception -> L8
        return;
    L6:
        this.len += r2.length;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.OutputSerializedData
    public void writeBytes(byte[] r2, int r3, int r4) {
    L8:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L14;
        FileLog.e("write raw error");
        FileLog.e(e);
        return;
    L14:
        return;
    L3:
        if (this.justCalc == true) goto L6;
        this.buffer.put(r2, r3, r4);     // Catch: Exception -> L8
        return;
    L6:
        this.len += r4;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.OutputSerializedData
    public void writeDouble(double r1) {
        writeInt64(Double.doubleToRawLongBits(r1));     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L11;
        FileLog.e("write double error");
        FileLog.e(e);
        return;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.OutputSerializedData
    public void writeFloat(float r2) {
    L8:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L14;
        FileLog.e("write float error");
        FileLog.e(e);
        return;
    L14:
        return;
    L3:
        if (this.justCalc == true) goto L6;
        this.buffer.putInt(Float.floatToIntBits(r2));     // Catch: Exception -> L8
        return;
    L6:
        this.len += 4;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.OutputSerializedData
    public void writeInt32(int r2) {
    L8:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L14;
        FileLog.e("write int32 error");
        FileLog.e(e);
        return;
    L14:
        return;
    L3:
        if (this.justCalc == true) goto L6;
        this.buffer.putInt(r2);     // Catch: Exception -> L8
        return;
    L6:
        this.len += 4;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.OutputSerializedData
    public void writeInt64(long r2) {
    L8:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L14;
        FileLog.e("write int64 error");
        FileLog.e(e);
        return;
    L14:
        return;
    L3:
        if (this.justCalc == true) goto L6;
        this.buffer.putLong(r2);     // Catch: Exception -> L8
        return;
    L6:
        this.len += 8;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.OutputSerializedData
    public void writeString(String r2) {
        if (r2 == null) goto L4;
    L14:
        writeByteArray(r2.getBytes("UTF-8"));     // Catch: Exception -> L9
        return;
    L9:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L16;
        FileLog.e("write string error");
        FileLog.e(e);
        return;
    L16:
        return;
    L4:
        if (BuildVars.LOGS_ENABLED == false) goto L6;
        FileLog.e("write string null");
        FileLog.e(new Throwable());
    L6:
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L14
    }
}
