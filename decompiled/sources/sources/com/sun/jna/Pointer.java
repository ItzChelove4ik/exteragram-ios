package com.sun.jna;

import com.sun.jna.Structure;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import kotlin.UByte;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.MVEL;

/* loaded from: classes5.dex */
public class Pointer {
    public static final Pointer NULL = null;
    protected long peer;

    /* renamed from: com.sun.jna.Pointer$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class Opaque extends Pointer {
        private final String MSG;

        private Opaque(long r1) {
            super(r1);
            this.MSG = "This pointer is opaque: " + this;
        }

        public /* synthetic */ Opaque(long r1, AnonymousClass1 r3) {
            this(r1);
        }

        @Override // com.sun.jna.Pointer
        public void clear(long r1) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public String dump(long r1, int r3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public byte getByte(long r1) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public ByteBuffer getByteBuffer(long r1, long r3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public char getChar(long r1) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public double getDouble(long r1) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public float getFloat(long r1) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public int getInt(long r1) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public long getLong(long r1) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public Pointer getPointer(long r1) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public short getShort(long r1) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public String getString(long r1, String r3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public String getWideString(long r1) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public long indexOf(long r1, byte r3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void read(long r1, byte[] r3, int r4, int r5) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void read(long r1, char[] r3, int r4, int r5) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void read(long r1, double[] r3, int r4, int r5) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void read(long r1, float[] r3, int r4, int r5) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void read(long r1, int[] r3, int r4, int r5) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void read(long r1, long[] r3, int r4, int r5) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void read(long r1, Pointer[] r3, int r4, int r5) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void read(long r1, short[] r3, int r4, int r5) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setByte(long r1, byte r3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setChar(long r1, char r3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setDouble(long r1, double r3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setFloat(long r1, float r3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setInt(long r1, int r3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setLong(long r1, long r3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setMemory(long r1, long r3, byte r5) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setPointer(long r1, Pointer r3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setShort(long r1, short r3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setString(long r1, String r3, String r4) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setWideString(long r1, String r3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public Pointer share(long r1, long r3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public String toString() {
            return "const@0x" + Long.toHexString(this.peer);
        }

        @Override // com.sun.jna.Pointer
        public void write(long r1, byte[] r3, int r4, int r5) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void write(long r1, char[] r3, int r4, int r5) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void write(long r1, double[] r3, int r4, int r5) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void write(long r1, float[] r3, int r4, int r5) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void write(long r1, int[] r3, int r4, int r5) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void write(long r1, long[] r3, int r4, int r5) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void write(long r1, Pointer[] r3, int r4, int r5) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void write(long r1, short[] r3, int r4, int r5) {
            throw new UnsupportedOperationException(this.MSG);
        }
    }

    static {
    }

    public Pointer() {
    }

    public Pointer(long r1) {
        this.peer = r1;
    }

    public static final Pointer createConstant(int r5) {
        return new Opaque(r5 & 4294967295L, null);
    }

    public static final Pointer createConstant(long r2) {
        return new Opaque(r2, null);
    }

    public static long nativeValue(Pointer r2) {
        if (r2 != null) goto L6;
        return 0;
    L6:
        return r2.peer;
    }

    public static void nativeValue(Pointer r0, long r1) {
        r0.peer = r1;
    }

    private void readArray(long r8, Object r10, Class<?> r11) {
        int r5 = Array.getLength(r10);
        if (r11 != Byte.TYPE) goto L7;
        read(r8, (byte[]) r10, 0, r5);
        return;
    L7:
        if (r11 != Short.TYPE) goto L11;
        read(r8, (short[]) r10, 0, r5);
        return;
    L11:
        if (r11 != Character.TYPE) goto L15;
        read(r8, (char[]) r10, 0, r5);
        return;
    L15:
        if (r11 != Integer.TYPE) goto L19;
        read(r8, (int[]) r10, 0, r5);
        return;
    L19:
        if (r11 != Long.TYPE) goto L23;
        read(r8, (long[]) r10, 0, r5);
        return;
    L23:
        if (r11 != Float.TYPE) goto L27;
        read(r8, (float[]) r10, 0, r5);
        return;
    L27:
        if (r11 != Double.TYPE) goto L31;
        read(r8, (double[]) r10, 0, r5);
        return;
    L31:
        if (Pointer.class.isAssignableFrom(r11) == false) goto L34;
        read(r8, (Pointer[]) r10, 0, r5);
        return;
    L34:
        int r82 = 0;
        if (Structure.class.isAssignableFrom(r11) == false) goto L55;
        Structure[] r102 = (Structure[]) r10;
        if (Structure.ByReference.class.isAssignableFrom(r11) == false) goto L42;
        Pointer[] r7 = getPointerArray(r8, r102.length);
    L40:
        if (r82 >= r102.length) goto L60;
        r102[r82] = Structure.updateStructureByReference(r11, r102[r82], r7[r82]);
        r82 = r82 + 1;
        goto L40
    L60:
        return;
    L42:
        Structure r72 = r102[0];
        if (r72 != null) goto L45;
        r72 = Structure.newInstance(r11, share(r8));
        r72.conditionalAutoRead();
        r102[0] = r72;
    L46:
        Structure[] r73 = r72.toArray(r102.length);
        int r83 = 1;
    L48:
        if (r83 >= r102.length) goto L68;
        Structure r112 = r102[r83];
        if (r112 != null) goto L52;
        r102[r83] = r73[r83];
    L53:
        r83 = r83 + 1;
        goto L48
    L52:
        r112.useMemory(this, (int) ((r112.size() * r83) + r8), true);
        r102[r83].read();
        goto L53
    L68:
        return;
    L45:
        r72.useMemory(this, (int) r8, true);
        r72.read();
        goto L46
    L55:
        if (NativeMapped.class.isAssignableFrom(r11) == false) goto L61;
        NativeMapped[] r74 = (NativeMapped[]) r10;
        NativeMappedConverter r9 = NativeMappedConverter.getInstance(r11);
        int r103 = Native.getNativeSize(r10.getClass(), r10) / r74.length;
    L58:
        if (r82 >= r74.length) goto L69;
        r74[r82] = (NativeMapped) r9.fromNative(getValue((r103 * r82) + r8, r9.nativeType(), r74[r82]), new FromNativeContext(r11));
        r82 = r82 + 1;
        goto L58
    L69:
        return;
    L61:
        Native$$ExternalSyntheticBUOutline0.m("Reading array of ", r11, " from memory not supported");
    }

    private void writeArray(long r12, Object r14, Class<?> r15) {
        if (r15 != Byte.TYPE) goto L7;
        byte[] r3 = (byte[]) r14;
        write(r12, r3, 0, r3.length);
        return;
    L7:
        if (r15 != Short.TYPE) goto L11;
        short[] r32 = (short[]) r14;
        write(r12, r32, 0, r32.length);
        return;
    L11:
        if (r15 != Character.TYPE) goto L15;
        char[] r33 = (char[]) r14;
        write(r12, r33, 0, r33.length);
        return;
    L15:
        if (r15 != Integer.TYPE) goto L19;
        int[] r34 = (int[]) r14;
        write(r12, r34, 0, r34.length);
        return;
    L19:
        if (r15 != Long.TYPE) goto L23;
        long[] r35 = (long[]) r14;
        write(r12, r35, 0, r35.length);
        return;
    L23:
        if (r15 != Float.TYPE) goto L27;
        float[] r36 = (float[]) r14;
        write(r12, r36, 0, r36.length);
        return;
    L27:
        if (r15 != Double.TYPE) goto L31;
        double[] r37 = (double[]) r14;
        write(r12, r37, 0, r37.length);
        return;
    L31:
        if (Pointer.class.isAssignableFrom(r15) == false) goto L35;
        Pointer[] r38 = (Pointer[]) r14;
        write(r12, r38, 0, r38.length);
        return;
    L35:
        if (Structure.class.isAssignableFrom(r15) == false) goto L60;
        Structure[] r0 = (Structure[]) r14;
        if (Structure.ByReference.class.isAssignableFrom(r15) == false) goto L48;
        int r5 = r0.length;
        int r2 = 0;
        Pointer[] r39 = new Pointer[r5];
    L40:
        if (r2 >= r0.length) goto L46;
        Structure r1 = r0[r2];
        if (r1 != null) goto L44;
        r39[r2] = null;
    L45:
        r2 = r2 + 1;
        goto L40
    L44:
        r39[r2] = r1.getPointer();
        r0[r2].write();
        goto L45
    L46:
        write(r12, r39, 0, r5);
        return;
    L48:
        Structure r6 = r0[0];
        if (r6 != null) goto L51;
        r6 = Structure.newInstance(r15, share(r12));
        r0[0] = r6;
    L52:
        r6.write();
        Structure[] r13 = r6.toArray(r0.length);
        int r22 = 1;
    L54:
        if (r22 >= r0.length) goto L66;
        Structure r62 = r0[r22];
        if (r62 != null) goto L58;
        r0[r22] = r13[r22];
    L59:
        r0[r22].write();
        r22 = r22 + 1;
        goto L54
    L58:
        r62.useMemory(this, (int) ((r62.size() * r22) + r12), true);
        goto L59
    L66:
        return;
    L51:
        r6.useMemory(this, (int) r12, true);
        goto L52
    L60:
        int r23 = 0;
        if (NativeMapped.class.isAssignableFrom(r15) == false) goto L67;
        NativeMapped[] r63 = (NativeMapped[]) r14;
        NativeMappedConverter r16 = NativeMappedConverter.getInstance(r15);
        Class<?> r7 = r16.nativeType();
        int r02 = Native.getNativeSize(r14.getClass(), r14) / r63.length;
    L64:
        if (r23 >= r63.length) goto L76;
        setValue((r23 * r02) + r12, r16.toNative(r63[r23], new ToNativeContext()), r7);
        r23 = r23 + 1;
        goto L64
    L76:
        return;
    L67:
        Native$$ExternalSyntheticBUOutline0.m("Writing array of ", r15, " to memory not supported");
    }

    public void clear(long r7) {
        setMemory(0, r7, (byte) 0);
    }

    public String dump(long r8, int r10) {
        StringWriter r0 = new StringWriter((13 + (r10 * 2)) + ((r10 / 4) * 4));
        PrintWriter r1 = new PrintWriter(r0);
        r1.println("memory dump");
        int r2 = 0;
    L4:
        if (r2 >= r10) goto L19;
        byte r4 = getByte(r2 + r8);
        int r5 = r2 % 4;
        if (r5 != 0) goto L8;
        r1.print("[");
    L8:
        if (r4 >= 0) goto L10;
    L12:
        r1.print(Integer.toHexString(r4 & UByte.MAX_VALUE));
        if (r5 != 3) goto L17;
        if (r2 >= (r10 - 1)) goto L17;
        r1.println("]");
    L17:
        r2 = r2 + 1;
        goto L4
    L10:
        if (r4 >= 16) goto L12;
        r1.print(MVEL.VERSION_SUB);
        goto L12
    L19:
        if (r0.getBuffer().charAt(r0.getBuffer().length() - 2) == ']') goto L22;
        r1.println("]");
    L22:
        return r0.toString();
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if (r5 != null) goto L9;
        return false;
    L9:
        if ((r5 instanceof Pointer) == true) goto L11;
    L13:
        return false;
    L11:
        if (((Pointer) r5).peer != this.peer) goto L13;
        return true;
    }

    public byte getByte(long r3) {
        return Native.getByte(this, this.peer, r3);
    }

    public byte[] getByteArray(long r7, int r9) {
        byte[] r3 = new byte[r9];
        read(r7, r3, 0, r9);
        return r3;
    }

    public ByteBuffer getByteBuffer(long r8, long r10) {
        return Native.getDirectByteBuffer(this, this.peer, r8, r10).order(ByteOrder.nativeOrder());
    }

    public char getChar(long r3) {
        return Native.getChar(this, this.peer, r3);
    }

    public char[] getCharArray(long r7, int r9) {
        char[] r3 = new char[r9];
        read(r7, r3, 0, r9);
        return r3;
    }

    public double getDouble(long r3) {
        return Native.getDouble(this, this.peer, r3);
    }

    public double[] getDoubleArray(long r7, int r9) {
        double[] r3 = new double[r9];
        read(r7, r3, 0, r9);
        return r3;
    }

    public float getFloat(long r3) {
        return Native.getFloat(this, this.peer, r3);
    }

    public float[] getFloatArray(long r7, int r9) {
        float[] r3 = new float[r9];
        read(r7, r3, 0, r9);
        return r3;
    }

    public int getInt(long r3) {
        return Native.getInt(this, this.peer, r3);
    }

    public int[] getIntArray(long r7, int r9) {
        int[] r3 = new int[r9];
        read(r7, r3, 0, r9);
        return r3;
    }

    public long getLong(long r3) {
        return Native.getLong(this, this.peer, r3);
    }

    public long[] getLongArray(long r7, int r9) {
        long[] r3 = new long[r9];
        read(r7, r3, 0, r9);
        return r3;
    }

    public NativeLong getNativeLong(long r4) {
        if (NativeLong.SIZE != 8) goto L5;
        long r3 = getLong(r4);
    L7:
        return new NativeLong(r3);
    L5:
        r3 = getInt(r4);
        goto L7
    }

    public Pointer getPointer(long r3) {
        return Native.getPointer(this.peer + r3);
    }

    public Pointer[] getPointerArray(long r7) {
        ArrayList r0 = new ArrayList();
        Pointer r1 = getPointer(r7);
        int r3 = 0;
    L3:
        if (r1 == null) goto L6;
        r0.add(r1);
        r3 = r3 + Native.POINTER_SIZE;
        r1 = getPointer(r3 + r7);
        goto L3
    L6:
        return (Pointer[]) r0.toArray(new Pointer[0]);
    }

    public Pointer[] getPointerArray(long r7, int r9) {
        Pointer[] r3 = new Pointer[r9];
        read(r7, r3, 0, r9);
        return r3;
    }

    public short getShort(long r3) {
        return Native.getShort(this, this.peer, r3);
    }

    public short[] getShortArray(long r7, int r9) {
        short[] r3 = new short[r9];
        read(r7, r3, 0, r9);
        return r3;
    }

    public String getString(long r2) {
        return getString(r2, Native.getDefaultStringEncoding());
    }

    public String getString(long r1, String r3) {
        return Native.getString(this, r1, r3);
    }

    public String[] getStringArray(long r3) {
        return getStringArray(r3, -1, Native.getDefaultStringEncoding());
    }

    public String[] getStringArray(long r2, int r4) {
        return getStringArray(r2, r4, Native.getDefaultStringEncoding());
    }

    public String[] getStringArray(long r12, int r14, String r15) {
        ArrayList r0 = new ArrayList();
        if (r14 == (-1)) goto L17;
        Pointer r1 = getPointer(r12);
        int r6 = 0;
        int r7 = 0;
    L5:
        int r8 = r6 + 1;
        if (r6 >= r14) goto L26;
        if (r1 != null) goto L10;
        String r62 = null;
    L13:
        r0.add(r62);
        if (r8 >= r14) goto L16;
        r7 = r7 + Native.POINTER_SIZE;
        r1 = getPointer(r7 + r12);
    L16:
        r6 = r8;
        goto L5
    L10:
        if ("--WIDE-STRING--".equals(r15) == false) goto L12;
        r62 = r1.getWideString(0);
        goto L13
    L12:
        r62 = r1.getString(0, r15);
    L26:
        return (String[]) r0.toArray(new String[0]);
    L17:
        int r142 = 0;
    L18:
        Pointer r13 = getPointer(r142 + r12);
        if (r13 == null) goto L26;
        if ("--WIDE-STRING--".equals(r15) == false) goto L23;
        String r16 = r13.getWideString(0);
    L24:
        r0.add(r16);
        r142 = r142 + Native.POINTER_SIZE;
        goto L18
    L23:
        r16 = r13.getString(0, r15);
        goto L24
    }

    public String[] getStringArray(long r2, String r4) {
        return getStringArray(r2, -1, r4);
    }

    public Object getValue(long r5, Class<?> r7, Object r8) {
        boolean r1 = true;
        if (Structure.class.isAssignableFrom(r7) == false) goto L11;
        Structure r82 = (Structure) r8;
        if (Structure.ByReference.class.isAssignableFrom(r7) == true) goto L7;
        r82.useMemory(this, (int) r5, true);
        r82.read();
        return r82;
    L7:
        return Structure.updateStructureByReference(r7, r82, getPointer(r5));
    L11:
        if (r7 == Boolean.TYPE) goto L138;
        if (r7 == Boolean.class) goto L138;
        if (r7 == Byte.TYPE) goto L136;
        if (r7 == Byte.class) goto L136;
        if (r7 == Short.TYPE) goto L134;
        if (r7 == Short.class) goto L134;
        if (r7 == Character.TYPE) goto L132;
        if (r7 == Character.class) goto L132;
        if (r7 == Integer.TYPE) goto L130;
        if (r7 == Integer.class) goto L130;
        if (r7 == Long.TYPE) goto L128;
        if (r7 == Long.class) goto L128;
        if (r7 == Float.TYPE) goto L126;
        if (r7 == Float.class) goto L126;
        if (r7 == Double.TYPE) goto L124;
        if (r7 == Double.class) goto L124;
        Pointer r12 = null;
        if (Pointer.class.isAssignableFrom(r7) == false) goto L65;
        Pointer r4 = getPointer(r5);
        if (r4 != null) goto L55;
        return null;
    L55:
        if ((r8 instanceof Pointer) == false) goto L57;
        r12 = (Pointer) r8;
    L57:
        if (r12 != null) goto L59;
    L62:
        return r4;
    L59:
        if (r4.peer != r12.peer) goto L62;
        return r12;
    L65:
        if (r7 != String.class) goto L72;
        Pointer r42 = getPointer(r5);
        if (r42 != null) goto L69;
        return null;
    L69:
        return r42.getString(0);
    L72:
        if (r7 != WString.class) goto L79;
        Pointer r43 = getPointer(r5);
        if (r43 != null) goto L76;
        return null;
    L76:
        return new WString(r43.getWideString(0));
    L79:
        if (Callback.class.isAssignableFrom(r7) == false) goto L89;
        Pointer r44 = getPointer(r5);
        if (r44 != null) goto L83;
        return null;
    L83:
        Callback r83 = (Callback) r8;
        if (r44.equals(CallbackReference.getFunctionPointer(r83)) == false) goto L86;
        return r83;
    L86:
        return CallbackReference.getCallback(r7, r44);
    L89:
        if (Platform.HAS_BUFFERS == false) goto L105;
        if (Buffer.class.isAssignableFrom(r7) == false) goto L105;
        Pointer r45 = getPointer(r5);
        if (r45 != null) goto L95;
        return null;
    L95:
        if (r8 != null) goto L97;
        Pointer r52 = null;
    L98:
        if (r52 != null) goto L100;
    L102:
        Segment$$ExternalSyntheticBUOutline1.m("Can't autogenerate a direct buffer on memory read");
        return null;
    L100:
        if (r52.equals(r45) == false) goto L102;
        return r8;
    L97:
        r52 = Native.getDirectBufferPointer((Buffer) r8);
    L105:
        if (NativeMapped.class.isAssignableFrom(r7) == false) goto L115;
        NativeMapped r84 = (NativeMapped) r8;
        if (r84 == null) goto L112;
        Object r46 = r84.fromNative(getValue(r5, r84.nativeType(), null), new FromNativeContext(r7));
        if (r84.equals(r46) == false) goto L111;
        return r84;
    L111:
        return r46;
    L112:
        NativeMappedConverter r85 = NativeMappedConverter.getInstance(r7);
        return r85.fromNative(getValue(r5, r85.nativeType(), null), new FromNativeContext(r7));
    L115:
        if (r7.isArray() == false) goto L121;
        if (r8 == null) goto L119;
        readArray(r5, r8, r7.getComponentType());
        return r8;
    L119:
        Segment$$ExternalSyntheticBUOutline1.m("Need an initialized array");
        return null;
    L121:
        Native$$ExternalSyntheticBUOutline0.m("Reading \"", r7, "\" from memory is not supported");
        return null;
    L124:
        return Double.valueOf(getDouble(r5));
    L126:
        return Float.valueOf(getFloat(r5));
    L128:
        return Long.valueOf(getLong(r5));
    L130:
        return Integer.valueOf(getInt(r5));
    L132:
        return Character.valueOf(getChar(r5));
    L134:
        return Short.valueOf(getShort(r5));
    L136:
        return Byte.valueOf(getByte(r5));
    L138:
        if (getInt(r5) != 0) goto L142;
        r1 = false;
    L142:
        return Function.valueOf(r1);
    }

    public String getWideString(long r3) {
        return Native.getWideString(this, this.peer, r3);
    }

    public String[] getWideStringArray(long r2) {
        return getWideStringArray(r2, -1);
    }

    public String[] getWideStringArray(long r2, int r4) {
        return getStringArray(r2, r4, "--WIDE-STRING--");
    }

    public int hashCode() {
        long r0 = this.peer;
        return (int) ((r0 >>> 32) + (r0 & 4294967295L));
    }

    public long indexOf(long r7, byte r9) {
        return Native.indexOf(this, this.peer, r7, r9);
    }

    public void read(long r9, byte[] r11, int r12, int r13) {
        Native.read(this, this.peer, r9, r11, r12, r13);
    }

    public void read(long r9, char[] r11, int r12, int r13) {
        Native.read(this, this.peer, r9, r11, r12, r13);
    }

    public void read(long r9, double[] r11, int r12, int r13) {
        Native.read(this, this.peer, r9, r11, r12, r13);
    }

    public void read(long r9, float[] r11, int r12, int r13) {
        Native.read(this, this.peer, r9, r11, r12, r13);
    }

    public void read(long r9, int[] r11, int r12, int r13) {
        Native.read(this, this.peer, r9, r11, r12, r13);
    }

    public void read(long r9, long[] r11, int r12, int r13) {
        Native.read(this, this.peer, r9, r11, r12, r13);
    }

    public void read(long r9, Pointer[] r11, int r12, int r13) {
        int r0 = 0;
    L3:
        if (r0 >= r13) goto L11;
        Pointer r1 = getPointer((Native.POINTER_SIZE * r0) + r9);
        int r2 = r0 + r12;
        Pointer r3 = r11[r2];
        if (r3 == null) goto L9;
        if (r1 == null) goto L9;
        if (r1.peer != r3.peer) goto L9;
    L10:
        r0 = r0 + 1;
    L9:
        r11[r2] = r1;
        goto L10
    }

    public void read(long r9, short[] r11, int r12, int r13) {
        Native.read(this, this.peer, r9, r11, r12, r13);
    }

    public void setByte(long r7, byte r9) {
        Native.setByte(this, this.peer, r7, r9);
    }

    public void setChar(long r7, char r9) {
        Native.setChar(this, this.peer, r7, r9);
    }

    public void setDouble(long r8, double r10) {
        Native.setDouble(this, this.peer, r8, r10);
    }

    public void setFloat(long r7, float r9) {
        Native.setFloat(this, this.peer, r7, r9);
    }

    public void setInt(long r7, int r9) {
        Native.setInt(this, this.peer, r7, r9);
    }

    public void setLong(long r8, long r10) {
        Native.setLong(this, this.peer, r8, r10);
    }

    public void setMemory(long r9, long r11, byte r13) {
        Native.setMemory(this, this.peer, r9, r11, r13);
    }

    public void setNativeLong(long r3, NativeLong r5) {
        if (NativeLong.SIZE != 8) goto L6;
        setLong(r3, r5.longValue());
        return;
    L6:
        setInt(r3, r5.intValue());
    }

    public void setPointer(long r8, Pointer r10) {
        long r1 = this.peer;
        if (r10 == null) goto L6;
        long r3 = r10.peer;
    L7:
        Native.setPointer(this, r1, r8, r3);
        return;
    L6:
        r3 = 0;
        goto L7
    }

    public void setShort(long r7, short r9) {
        Native.setShort(this, this.peer, r7, r9);
    }

    public void setString(long r1, WString r3) {
        if (r3 != null) goto L4;
        String r32 = null;
    L5:
        setWideString(r1, r32);
        return;
    L4:
        r32 = r3.toString();
        goto L5
    }

    public void setString(long r2, String r4) {
        setString(r2, r4, Native.getDefaultStringEncoding());
    }

    public void setString(long r7, String r9, String r10) {
        byte[] r3 = Native.getBytes(r9, r10);
        write(r7, r3, 0, r3.length);
        setByte(r7 + r3.length, (byte) 0);
    }

    public void setValue(long r3, Object r5, Class<?> r6) {
        int r1 = 0;
        int r12 = 0;
        char r13 = 0;
        short r14 = 0;
        byte r15 = 0;
        if (r6 == Boolean.TYPE) goto L126;
        if (r6 == Boolean.class) goto L126;
        if (r6 != Byte.TYPE) goto L10;
    L120:
        if (r5 == null) goto L123;
        r15 = ((Byte) r5).byteValue();
    L123:
        setByte(r3, r15);
        return;
    L10:
        if (r6 == Byte.class) goto L120;
        if (r6 != Short.TYPE) goto L15;
    L115:
        if (r5 == null) goto L118;
        r14 = ((Short) r5).shortValue();
    L118:
        setShort(r3, r14);
        return;
    L15:
        if (r6 == Short.class) goto L115;
        if (r6 != Character.TYPE) goto L20;
    L110:
        if (r5 == null) goto L113;
        r13 = ((Character) r5).charValue();
    L113:
        setChar(r3, r13);
        return;
    L20:
        if (r6 == Character.class) goto L110;
        if (r6 != Integer.TYPE) goto L25;
    L105:
        if (r5 == null) goto L108;
        r12 = ((Integer) r5).intValue();
    L108:
        setInt(r3, r12);
        return;
    L25:
        if (r6 == Integer.class) goto L105;
        if (r6 != Long.TYPE) goto L30;
    L100:
        if (r5 != null) goto L102;
        long r52 = 0;
    L103:
        setLong(r3, r52);
        return;
    L102:
        r52 = ((Long) r5).longValue();
        goto L103
    L30:
        if (r6 == Long.class) goto L100;
        if (r6 != Float.TYPE) goto L35;
    L95:
        if (r5 != null) goto L97;
        float r53 = 0.0f;
    L98:
        setFloat(r3, r53);
        return;
    L97:
        r53 = ((Float) r5).floatValue();
        goto L98
    L35:
        if (r6 == Float.class) goto L95;
        if (r6 != Double.TYPE) goto L40;
    L90:
        if (r5 != null) goto L92;
        double r54 = 0.0d;
    L93:
        setDouble(r3, r54);
        return;
    L92:
        r54 = ((Double) r5).doubleValue();
        goto L93
    L40:
        if (r6 == Double.class) goto L90;
        if (r6 != Pointer.class) goto L47;
        setPointer(r3, (Pointer) r5);
        return;
    L47:
        if (r6 != String.class) goto L51;
        setPointer(r3, (Pointer) r5);
        return;
    L51:
        if (r6 != WString.class) goto L54;
        setPointer(r3, (Pointer) r5);
        return;
    L54:
        Pointer r16 = null;
        if (Structure.class.isAssignableFrom(r6) == false) goto L68;
        Structure r55 = (Structure) r5;
        if (Structure.ByReference.class.isAssignableFrom(r6) == false) goto L65;
        if (r55 == null) goto L61;
        r16 = r55.getPointer();
    L61:
        setPointer(r3, r16);
        if (r55 == null) goto L130;
        r55.autoWrite();
        return;
    L130:
        return;
    L65:
        r55.useMemory(this, (int) r3, true);
        r55.write();
        return;
    L68:
        if (Callback.class.isAssignableFrom(r6) == false) goto L72;
        setPointer(r3, CallbackReference.getFunctionPointer((Callback) r5));
        return;
    L72:
        if (Platform.HAS_BUFFERS == false) goto L81;
        if (Buffer.class.isAssignableFrom(r6) == false) goto L81;
        if (r5 == null) goto L78;
        r16 = Native.getDirectBufferPointer((Buffer) r5);
    L78:
        setPointer(r3, r16);
        return;
    L81:
        if (NativeMapped.class.isAssignableFrom(r6) == false) goto L85;
        NativeMappedConverter r62 = NativeMappedConverter.getInstance(r6);
        Class<?> r0 = r62.nativeType();
        setValue(r3, r62.toNative(r5, new ToNativeContext()), r0);
        return;
    L85:
        if (r6.isArray() == false) goto L88;
        writeArray(r3, r5, r6.getComponentType());
        return;
    L88:
        Native$$ExternalSyntheticBUOutline0.m("Writing ", r6, " to memory is not supported");
        return;
    L126:
        if (Boolean.TRUE.equals(r5) == false) goto L128;
        r1 = -1;
    L128:
        setInt(r3, r1);
    }

    public void setWideString(long r7, String r9) {
        Native.setWideString(this, this.peer, r7, r9);
    }

    public Pointer share(long r3) {
        return share(r3, 0);
    }

    public Pointer share(long r3, long r5) {
        if (r3 != 0) goto L6;
        return this;
    L6:
        return new Pointer(this.peer + r3);
    }

    public String toString() {
        return "native@0x" + Long.toHexString(this.peer);
    }

    public void write(long r9, byte[] r11, int r12, int r13) {
        Native.write(this, this.peer, r9, r11, r12, r13);
    }

    public void write(long r9, char[] r11, int r12, int r13) {
        Native.write(this, this.peer, r9, r11, r12, r13);
    }

    public void write(long r9, double[] r11, int r12, int r13) {
        Native.write(this, this.peer, r9, r11, r12, r13);
    }

    public void write(long r9, float[] r11, int r12, int r13) {
        Native.write(this, this.peer, r9, r11, r12, r13);
    }

    public void write(long r9, int[] r11, int r12, int r13) {
        Native.write(this, this.peer, r9, r11, r12, r13);
    }

    public void write(long r9, long[] r11, int r12, int r13) {
        Native.write(this, this.peer, r9, r11, r12, r13);
    }

    public void write(long r5, Pointer[] r7, int r8, int r9) {
        int r0 = 0;
    L3:
        if (r0 >= r9) goto L5;
        setPointer((Native.POINTER_SIZE * r0) + r5, r7[r8 + r0]);
        r0 = r0 + 1;
        goto L3
    }

    public void write(long r9, short[] r11, int r12, int r13) {
        Native.write(this, this.peer, r9, r11, r12, r13);
    }
}
