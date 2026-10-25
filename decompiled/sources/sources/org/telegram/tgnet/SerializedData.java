package org.telegram.tgnet;

import com.android.dx.io.Opcodes;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.tgnet.TLRPC;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public class SerializedData extends AbstractSerializedData {
    private DataInputStream in;
    private ByteArrayInputStream inbuf;
    protected boolean isOut;
    private boolean justCalc;
    private int len;
    private DataOutputStream out;
    private ByteArrayOutputStream outbuf;

    public SerializedData() {
        this.isOut = true;
        this.justCalc = false;
        this.outbuf = new ByteArrayOutputStream();
        this.out = new DataOutputStream(this.outbuf);
    }

    public SerializedData(int r2) {
        this.isOut = true;
        this.justCalc = false;
        this.outbuf = new ByteArrayOutputStream(r2);
        this.out = new DataOutputStream(this.outbuf);
    }

    public SerializedData(File r5) {
        this.isOut = true;
        this.justCalc = false;
        FileInputStream r1 = new FileInputStream(r5);
        byte[] r52 = new byte[(int) r5.length()];
        new DataInputStream(r1).readFully(r52);
        r1.close();
        this.isOut = false;
        this.inbuf = new ByteArrayInputStream(r52);
        this.in = new DataInputStream(this.inbuf);
    }

    public SerializedData(InputStream r4) {
        this.isOut = true;
        this.justCalc = false;
        byte[] r1 = new byte[r4.available()];
        new DataInputStream(r4).readFully(r1);
        this.isOut = false;
        this.inbuf = new ByteArrayInputStream(r1);
        this.in = new DataInputStream(this.inbuf);
    }

    public SerializedData(boolean r4) {
        this.isOut = true;
        this.justCalc = false;
        if (r4 == true) goto L5;
        this.outbuf = new ByteArrayOutputStream();
        this.out = new DataOutputStream(this.outbuf);
    L5:
        this.justCalc = r4;
        this.len = 0;
    }

    public SerializedData(byte[] r3) {
        this.justCalc = false;
        this.isOut = false;
        this.inbuf = new ByteArrayInputStream(r3);
        this.in = new DataInputStream(this.inbuf);
        this.len = 0;
    }

    private void writeInt32(int r2, DataOutputStream r3) {
        int r1 = 0;
    L4:
        if (r1 >= 4) goto L17;
        r3.write(r2 >> (r1 * 8));     // Catch: Exception -> L8
        r1 = r1 + 1;
    L8:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L16;
        FileLog.e("write int32 error");
        FileLog.e(e);
        return;
    L16:
        return;
    }

    private void writeInt64(long r3, DataOutputStream r5) {
        int r2 = 0;
    L4:
        if (r2 >= 8) goto L17;
        r5.write((int) (r3 >> (r2 * 8)));     // Catch: Exception -> L8
        r2 = r2 + 1;
    L8:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L16;
        FileLog.e("write int64 error");
        FileLog.e(e);
        return;
    L16:
        return;
    }

    public void cleanup() {
        ByteArrayInputStream r1 = this.inbuf;     // Catch: Exception -> L7
        if (r1 == null) goto L30;
        r1.close();     // Catch: Exception -> L7
        this.inbuf = null;     // Catch: Exception -> L7
    L30:
        DataInputStream r12 = this.in;     // Catch: Exception -> L13
        if (r12 == null) goto L32;
        r12.close();     // Catch: Exception -> L13
        this.in = null;     // Catch: Exception -> L13
    L32:
        ByteArrayOutputStream r13 = this.outbuf;     // Catch: Exception -> L19
        if (r13 == null) goto L34;
        r13.close();     // Catch: Exception -> L19
        this.outbuf = null;     // Catch: Exception -> L19
    L34:
        DataOutputStream r14 = this.out;     // Catch: Exception -> L25
        if (r14 == null) goto L36;
        r14.close();     // Catch: Exception -> L25
        this.out = null;     // Catch: Exception -> L25
        return;
    L36:
        return;
    L25:
        e = move-exception;
        FileLog.e(e);
        return;
    L19:
        e = move-exception;
        FileLog.e(e);
    L13:
        e = move-exception;
        FileLog.e(e);
    L7:
        e = move-exception;
        FileLog.e(e);
        goto L30
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData, org.telegram.tgnet.OutputSerializedData
    public int getPosition() {
        return this.len;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData
    public int length() {
        if (this.justCalc == true) goto L11;
        if (this.isOut == false) goto L9;
        return this.outbuf.size();
    L9:
        return this.inbuf.available();
    L11:
        return this.len;
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
    public byte readByte(boolean r3) {
        byte r0 = this.in.readByte();     // Catch: Exception -> L4
        this.len++;
        return r0;
    L4:
        e = move-exception;
        if (r3 == false) goto L8;
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
    public byte[] readByteArray(boolean r7) {
        int r0 = this.in.read();     // Catch: Exception -> L5
        this.len++;
        if (r0 < 254) goto L7;
        r0 = (this.in.read() | (this.in.read() << 8)) | (this.in.read() << 16);     // Catch: Exception -> L5
        this.len += 3;
        int r1 = 4;
    L9:
        if (r0 > remaining()) goto L17;
        if (r0 < 0) goto L17;
        byte[] r4 = new byte[r0];     // Catch: Exception -> L5
        this.in.read(r4);     // Catch: Exception -> L5
        this.len++;
    L13:
        if (((r0 + r1) % 4) == 0) goto L15;
        this.in.read();     // Catch: Exception -> L5
        this.len++;
        r1 = r1 + 1;     // Catch: Exception -> L5
        goto L13
    L15:
        return r4;
    L17:
        throw new RuntimeException("byte array size too big");     // Catch: Exception -> L5
    L7:
        r1 = 1;
    L5:
        e = move-exception;
        if (r7 == false) goto L21;
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

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData
    public NativeByteBuffer readByteBuffer(boolean r1) {
        return null;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData
    public void readBytes(byte[] r2, boolean r3) {
        this.in.read(r2);     // Catch: Exception -> L4
        this.len += r2.length;
        return;
    L4:
        e = move-exception;
        if (r3 == false) goto L8;
        Make$Map$$ExternalSyntheticBUOutline0.m("read bytes error", e);
        return;
    L8:
        if (BuildVars.LOGS_ENABLED == false) goto L15;
        FileLog.e("read bytes error");
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
        return Float.intBitsToFloat(readInt32(r2));
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
    public int readInt32(boolean r6) {
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= 4) goto L16;
        r2 = r2 | (this.in.read() << (r1 * 8));     // Catch: Exception -> L7
        this.len++;
        r1 = r1 + 1;
    L7:
        e = move-exception;
        if (r6 == false) goto L11;
        Make$Map$$ExternalSyntheticBUOutline0.m("read int32 error", e);
        return 0;
    L11:
        if (BuildVars.LOGS_ENABLED == false) goto L13;
        FileLog.e("read int32 error");
        FileLog.e(e);
    L13:
        return 0;
    L16:
        return r2;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData
    public long readInt64(boolean r9) {
        int r2 = 0;
        long r3 = 0;
    L4:
        if (r2 >= 8) goto L16;
        r3 = r3 | (this.in.read() << (r2 * 8));     // Catch: Exception -> L7
        this.len++;
        r2 = r2 + 1;
    L7:
        e = move-exception;
        if (r9 == false) goto L11;
        Make$Map$$ExternalSyntheticBUOutline0.m("read int64 error", e);
        return 0;
    L11:
        if (BuildVars.LOGS_ENABLED == false) goto L13;
        FileLog.e("read int64 error");
        FileLog.e(e);
    L13:
        return 0;
    L16:
        return r3;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData
    public String readString(boolean r7) {
        int r0 = this.in.read();     // Catch: Exception -> L5
        this.len++;
        if (r0 < 254) goto L7;
        r0 = (this.in.read() | (this.in.read() << 8)) | (this.in.read() << 16);     // Catch: Exception -> L5
        this.len += 3;
        int r1 = 4;
    L9:
        if (r0 > remaining()) goto L18;
        if (r0 < 0) goto L18;
        byte[] r4 = new byte[r0];     // Catch: Exception -> L5
        this.in.read(r4);     // Catch: Exception -> L5
        this.len++;
    L13:
        if (((r0 + r1) % 4) == 0) goto L16;
        this.in.read();     // Catch: Exception -> L5
        this.len++;
        r1 = r1 + 1;     // Catch: Exception -> L5
        goto L13
    L16:
        return new String(r4, StandardCharsets.UTF_8);
    L18:
        throw new RuntimeException("string size too big");     // Catch: Exception -> L5
    L7:
        r1 = 1;
    L5:
        e = move-exception;
        if (r7 == false) goto L22;
        Make$Map$$ExternalSyntheticBUOutline0.m("read string error", e);
        return null;
    L22:
        if (BuildVars.LOGS_ENABLED == false) goto L30;
        FileLog.e("read string error");
        FileLog.e(e);
        return null;
    L30:
        return null;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData
    public int remaining() {
        return this.in.available();
    L4:
        return Integer.MAX_VALUE;
    }

    public void set(byte[] r2) {
        this.isOut = false;
        this.inbuf = new ByteArrayInputStream(r2);
        this.in = new DataInputStream(this.inbuf);
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.InputSerializedData, org.telegram.tgnet.OutputSerializedData
    public void skip(int r2) {
        if (r2 != 0) goto L5;
        return;
    L5:
        if (this.justCalc == true) goto L13;
        DataInputStream r1 = this.in;
        if (r1 == null) goto L17;
        r1.skipBytes(r2);     // Catch: Exception -> L10
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
        return;
    L17:
        return;
    L13:
        this.len += r2;
    }

    public byte[] toByteArray() {
        return this.outbuf.toByteArray();
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
        this.out.writeByte(r2);     // Catch: Exception -> L8
        return;
    L6:
        this.len++;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.OutputSerializedData
    public void writeByte(int r2) {
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
        this.out.writeByte((byte) r2);     // Catch: Exception -> L8
        return;
    L6:
        this.len++;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.OutputSerializedData
    public void writeByteArray(byte[] r6) {
        int r0 = r6.length;     // Catch: Exception -> L26
        boolean r1 = this.justCalc;
        if (r0 > 253) goto L8;
        if (r1 == true) goto L7;
        this.out.write(r6.length);     // Catch: Exception -> L26
    L12:
        if (this.justCalc == true) goto L14;
        this.out.write(r6);     // Catch: Exception -> L26
    L16:
        if (r6.length > 253) goto L18;
        int r02 = 1;
    L19:
        if (((r6.length + r02) % 4) == 0) goto L38;
        if (this.justCalc == true) goto L24;
        this.out.write(0);     // Catch: Exception -> L26
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
        this.out.write(Opcodes.CONST_METHOD_HANDLE);     // Catch: Exception -> L26
        this.out.write(r6.length);     // Catch: Exception -> L26
        this.out.write(r6.length >> 8);     // Catch: Exception -> L26
        this.out.write(r6.length >> 16);     // Catch: Exception -> L26
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
        this.out.write(r8);     // Catch: Exception -> L24
    L11:
        if (this.justCalc == true) goto L13;
        this.out.write(r6, r7, r8);     // Catch: Exception -> L24
    L14:
        if (r8 > 253) goto L16;
        int r62 = 1;
    L17:
        if (((r8 + r62) % 4) == 0) goto L36;
        if (this.justCalc == true) goto L22;
        this.out.write(0);     // Catch: Exception -> L24
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
        this.out.write(Opcodes.CONST_METHOD_HANDLE);     // Catch: Exception -> L24
        this.out.write(r8);     // Catch: Exception -> L24
        this.out.write(r8 >> 8);     // Catch: Exception -> L24
        this.out.write(r8 >> 16);     // Catch: Exception -> L24
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
    public void writeByteBuffer(NativeByteBuffer r1) {
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
        this.out.write(r2);     // Catch: Exception -> L8
        return;
    L6:
        this.len += r2.length;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.OutputSerializedData
    public void writeBytes(byte[] r2, int r3, int r4) {
    L8:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L14;
        FileLog.e("write bytes error");
        FileLog.e(e);
        return;
    L14:
        return;
    L3:
        if (this.justCalc == true) goto L6;
        this.out.write(r2, r3, r4);     // Catch: Exception -> L8
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
    public void writeFloat(float r1) {
        writeInt32(Float.floatToIntBits(r1));     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L11;
        FileLog.e("write float error");
        FileLog.e(e);
        return;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.OutputSerializedData
    public void writeInt32(int r2) {
        if (this.justCalc == true) goto L6;
        writeInt32(r2, this.out);
        return;
    L6:
        this.len += 4;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.OutputSerializedData
    public void writeInt64(long r2) {
        if (this.justCalc == true) goto L6;
        writeInt64(r2, this.out);
        return;
    L6:
        this.len += 8;
    }

    @Override // org.telegram.tgnet.AbstractSerializedData, org.telegram.tgnet.OutputSerializedData
    public void writeString(String r2) {
        writeByteArray(r2.getBytes("UTF-8"));     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L11;
        FileLog.e("write string error");
        FileLog.e(e);
        return;
    }
}
