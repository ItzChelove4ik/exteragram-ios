package org.mvel2.asm;

import com.android.dx.cf.attrib.AttAnnotationDefault;
import com.android.dx.cf.attrib.AttBootstrapMethods;
import com.android.dx.cf.attrib.AttCode;
import com.android.dx.cf.attrib.AttConstantValue;
import com.android.dx.cf.attrib.AttDeprecated;
import com.android.dx.cf.attrib.AttEnclosingMethod;
import com.android.dx.cf.attrib.AttExceptions;
import com.android.dx.cf.attrib.AttInnerClasses;
import com.android.dx.cf.attrib.AttLineNumberTable;
import com.android.dx.cf.attrib.AttLocalVariableTable;
import com.android.dx.cf.attrib.AttLocalVariableTypeTable;
import com.android.dx.cf.attrib.AttRuntimeInvisibleAnnotations;
import com.android.dx.cf.attrib.AttRuntimeInvisibleParameterAnnotations;
import com.android.dx.cf.attrib.AttRuntimeVisibleAnnotations;
import com.android.dx.cf.attrib.AttRuntimeVisibleParameterAnnotations;
import com.android.dx.cf.attrib.AttSignature;
import com.android.dx.cf.attrib.AttSourceDebugExtension;
import com.android.dx.cf.attrib.AttSourceFile;
import com.android.dx.cf.attrib.AttSynthetic;
import com.sun.jna.IntegerType$$ExternalSyntheticBUOutline0;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline0;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class ClassReader {
    static final int EXPAND_ASM_INSNS = 256;
    public static final int EXPAND_FRAMES = 8;
    private static final int INPUT_STREAM_DATA_CHUNK_SIZE = 4096;
    private static final int MAX_BUFFER_SIZE = 1048576;
    public static final int SKIP_CODE = 1;
    public static final int SKIP_DEBUG = 2;
    public static final int SKIP_FRAMES = 4;

    /* renamed from: b, reason: collision with root package name */
    @Deprecated
    public final byte[] f500b;
    private final int[] bootstrapMethodOffsets;
    final byte[] classFileBuffer;
    private final ConstantDynamic[] constantDynamicValues;
    private final String[] constantUtf8Values;
    private final int[] cpInfoOffsets;
    public final int header;
    private final int maxStringLength;

    public ClassReader(InputStream r2) {
        this(readStream(r2, false));
    }

    public ClassReader(String r4) {
        this(readStream(ClassLoader.getSystemResourceAsStream(r4.replace('.', '/') + ".class"), true));
    }

    public ClassReader(byte[] r3) {
        this(r3, 0, r3.length);
    }

    public ClassReader(byte[] r1, int r2, int r3) {
        this(r1, r2, true);
    }

    public ClassReader(byte[] r11, int r12, boolean r13) {
        this.classFileBuffer = r11;
        this.f500b = r11;
        if (r13 == false) goto L9;
        int r132 = r12 + 6;
        if (readShort(r132) <= 65) goto L9;
        IntegerType$$ExternalSyntheticBUOutline0.m("Unsupported class file major version ", readShort(r132));
        throw null;
    L9:
        int r133 = readUnsignedShort(r12 + 8);
        this.cpInfoOffsets = new int[r133];
        this.constantUtf8Values = new String[r133];
        int r122 = r12 + 10;
        int r0 = 0;
        boolean r2 = false;
        boolean r3 = false;
        int r4 = 1;
    L10:
        if (r4 >= r133) goto L25;
        int r6 = r4 + 1;
        int r7 = r122 + 1;
        this.cpInfoOffsets[r4] = r7;
        int r8 = 3;
        switch(r11[r122]) {
            case 1: goto L21;
            case 2: goto L13;
            case 3: goto L16;
            case 4: goto L16;
            case 5: goto L20;
            case 6: goto L20;
            case 7: goto L19;
            case 8: goto L19;
            case 9: goto L16;
            case 10: goto L16;
            case 11: goto L16;
            case 12: goto L16;
            case 13: goto L13;
            case 14: goto L13;
            case 15: goto L18;
            case 16: goto L19;
            case 17: goto L17;
            case 18: goto L15;
            case 19: goto L19;
            case 20: goto L19;
            default: goto L13;
        };
    L15:
        r3 = true;
    L16:
        r4 = r6;
        r8 = 5;
    L24:
        r122 = r122 + r8;
        goto L10
    L17:
        r2 = true;
        r3 = true;
        goto L16
    L18:
        r8 = 4;
    L19:
        r4 = r6;
        goto L24
    L20:
        r4 = r4 + 2;
        r8 = 9;
        goto L24
    L21:
        r8 = 3 + readUnsignedShort(r7);
        if (r8 <= r0) goto L19;
        r4 = r6;
        r0 = r8;
        goto L24
    L13:
        Segment$$ExternalSyntheticBUOutline0.m();
        throw null;
    L25:
        this.maxStringLength = r0;
        this.header = r122;
        int[] r112 = null;
        if (r2 == false) goto L28;
        ConstantDynamic[] r123 = new ConstantDynamic[r133];
    L29:
        this.constantDynamicValues = r123;
        if (r3 == false) goto L32;
        r112 = readBootstrapMethodsAttribute(r0);
    L32:
        this.bootstrapMethodOffsets = r112;
        return;
    L28:
        r123 = null;
        goto L29
    }

    private static int computeBufferSize(InputStream r1) {
        int r12 = r1.available();
        if (r12 >= 256) goto L7;
        return 4096;
    L7:
        return Math.min(r12, 1048576);
    }

    private void computeImplicitFrame(Context r10) {
        String r0 = r10.currentMethodDescriptor;
        Object[] r1 = r10.currentFrameLocalTypes;
        int r4 = 0;
        if ((r10.currentMethodAccessFlags & 8) == 0) goto L5;
    L9:
        int r9 = 1;
    L10:
        int r2 = r9 + 1;
        char r5 = r0.charAt(r9);
        if (r5 == 'F') goto L45;
        if (r5 != 'L') goto L15;
        int r92 = r2;
    L42:
        if (r0.charAt(r92) == ';') goto L44;
        r92 = r92 + 1;
        goto L42
    L44:
        r1[r4] = r0.substring(r2, r92);
        r4 = r4 + 1;
        r9 = r92 + 1;
        goto L10
    L15:
        if (r5 == 'S') goto L39;
        if (r5 == 'I') goto L39;
        if (r5 == 'J') goto L38;
        if (r5 == 'Z') goto L39;
        if (r5 != '[') goto L24;
    L30:
        if (r0.charAt(r2) != '[') goto L33;
        r2 = r2 + 1;
        goto L30
    L33:
        if (r0.charAt(r2) != 'L') goto L37;
    L34:
        r2 = r2 + 1;
        if (r0.charAt(r2) != ';') goto L34;
    L37:
        int r22 = r2 + 1;
        r1[r4] = r0.substring(r9, r22);
        r9 = r22;
        r4 = r4 + 1;
        goto L10
    L24:
        switch(r5) {
            case 66: goto L39;
            case 67: goto L39;
            case 68: goto L27;
            default: goto L25;
        };
    L27:
        int r93 = r4 + 1;
        r1[r4] = Opcodes.DOUBLE;
    L28:
        r4 = r93;
        r9 = r2;
        goto L10
    L25:
        r10.currentFrameLocalCount = r4;
        return;
    L38:
        r93 = r4 + 1;
        r1[r4] = Opcodes.LONG;
    L39:
        r93 = r4 + 1;
        r1[r4] = Opcodes.INTEGER;
        goto L28
    L45:
        r93 = r4 + 1;
        r1[r4] = Opcodes.FLOAT;
        goto L28
    L5:
        if ("<init>".equals(r10.currentMethodName) == false) goto L8;
        r1[0] = Opcodes.UNINITIALIZED_THIS;
    L7:
        r4 = 1;
        goto L9
    L8:
        r1[0] = readClass(this.header + 2, r10.charBuffer);
        goto L7
    }

    private void createDebugLabel(int r2, Label[] r3) {
        if (r3[r2] != null) goto L6;
        Label r1 = readLabel(r2, r3);
        r1.flags = (short) (r1.flags | 1);
        return;
    }

    private Label createLabel(int r1, Label[] r2) {
        Label r0 = readLabel(r1, r2);
        r0.flags = (short) (r0.flags & (-2));
        return r0;
    }

    private int getTypeAnnotationBytecodeOffset(int[] r3, int r4) {
        if (r3 != null) goto L4;
        return -1;
    L4:
        if (r4 < r3.length) goto L6;
        return -1;
    L6:
        if (readByte(r3[r4]) >= 67) goto L9;
        return -1;
    L9:
        return readUnsignedShort(r3[r4] + 1);
    }

    private Attribute readAttribute(Attribute[] r10, String r11, int r12, int r13, char[] r14, int r15, Label[] r16) {
        int r0 = r10.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L10;
        Attribute r2 = r10[r1];
        if (r2.type.equals(r11) == true) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return r2.read(this, r12, r13, r14, r15, r16);
    L10:
        return new Attribute(r11).read(this, r12, r13, null, -1, null);
    }

    private int[] readBootstrapMethodsAttribute(int r7) {
        char[] r72 = new char[r7];
        int r0 = getFirstAttributeOffset();
        int r1 = readUnsignedShort(r0 - 2);
    L3:
        if (r1 <= 0) goto L11;
        String r2 = readUTF8(r0, r72);
        int r3 = readInt(r0 + 2);
        int r4 = r0 + 6;
        if (AttBootstrapMethods.ATTRIBUTE_NAME.equals(r2) == true) goto L6;
        r0 = r4 + r3;
        r1 = r1 - 1;
        goto L3
    L6:
        int r73 = readUnsignedShort(r4);
        int[] r12 = new int[r73];
        int r02 = r0 + 8;
        int r22 = 0;
    L7:
        if (r22 >= r73) goto L9;
        r12[r22] = r02;
        r02 = r02 + ((readUnsignedShort(r02 + 2) * 2) + 4);
        r22 = r22 + 1;
        goto L7
    L9:
        return r12;
    L11:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    private void readCode(MethodVisitor r40, Context r41, int r42) {
        ClassReader r0 = this;
        MethodVisitor r8 = r40;
        byte[] r10 = r0.classFileBuffer;
        char[] r5 = r41.charBuffer;
        int r11 = r0.readUnsignedShort(r42);
        int r12 = r0.readUnsignedShort(r42 + 2);
        int r13 = r0.readInt(r42 + 4);
        int r14 = r42 + 8;
        if (r13 > (r0.classFileBuffer.length - r14)) goto L301;
        int r15 = r14 + r13;
        Label[] r7 = new Label[r13 + 1];
        r41.currentMethodLabels = r7;
        int r1 = r14;
    L6:
        if (r1 >= r15) goto L36;
        int r16 = r1 - r14;
        switch((r10[r1] & UByte.MAX_VALUE)) {
            case 0: goto L35;
            case 1: goto L35;
            case 2: goto L35;
            case 3: goto L35;
            case 4: goto L35;
            case 5: goto L35;
            case 6: goto L35;
            case 7: goto L35;
            case 8: goto L35;
            case 9: goto L35;
            case 10: goto L35;
            case 11: goto L35;
            case 12: goto L35;
            case 13: goto L35;
            case 14: goto L35;
            case 15: goto L35;
            case 16: goto L34;
            case 17: goto L12;
            case 18: goto L34;
            case 19: goto L12;
            case 20: goto L12;
            case 21: goto L34;
            case 22: goto L34;
            case 23: goto L34;
            case 24: goto L34;
            case 25: goto L34;
            case 26: goto L35;
            case 27: goto L35;
            case 28: goto L35;
            case 29: goto L35;
            case 30: goto L35;
            case 31: goto L35;
            case 32: goto L35;
            case 33: goto L35;
            case 34: goto L35;
            case 35: goto L35;
            case 36: goto L35;
            case 37: goto L35;
            case 38: goto L35;
            case 39: goto L35;
            case 40: goto L35;
            case 41: goto L35;
            case 42: goto L35;
            case 43: goto L35;
            case 44: goto L35;
            case 45: goto L35;
            case 46: goto L35;
            case 47: goto L35;
            case 48: goto L35;
            case 49: goto L35;
            case 50: goto L35;
            case 51: goto L35;
            case 52: goto L35;
            case 53: goto L35;
            case 54: goto L34;
            case 55: goto L34;
            case 56: goto L34;
            case 57: goto L34;
            case 58: goto L34;
            case 59: goto L35;
            case 60: goto L35;
            case 61: goto L35;
            case 62: goto L35;
            case 63: goto L35;
            case 64: goto L35;
            case 65: goto L35;
            case 66: goto L35;
            case 67: goto L35;
            case 68: goto L35;
            case 69: goto L35;
            case 70: goto L35;
            case 71: goto L35;
            case 72: goto L35;
            case 73: goto L35;
            case 74: goto L35;
            case 75: goto L35;
            case 76: goto L35;
            case 77: goto L35;
            case 78: goto L35;
            case 79: goto L35;
            case 80: goto L35;
            case 81: goto L35;
            case 82: goto L35;
            case 83: goto L35;
            case 84: goto L35;
            case 85: goto L35;
            case 86: goto L35;
            case 87: goto L35;
            case 88: goto L35;
            case 89: goto L35;
            case 90: goto L35;
            case 91: goto L35;
            case 92: goto L35;
            case 93: goto L35;
            case 94: goto L35;
            case 95: goto L35;
            case 96: goto L35;
            case 97: goto L35;
            case 98: goto L35;
            case 99: goto L35;
            case 100: goto L35;
            case 101: goto L35;
            case 102: goto L35;
            case 103: goto L35;
            case 104: goto L35;
            case 105: goto L35;
            case 106: goto L35;
            case 107: goto L35;
            case 108: goto L35;
            case 109: goto L35;
            case 110: goto L35;
            case 111: goto L35;
            case 112: goto L35;
            case 113: goto L35;
            case 114: goto L35;
            case 115: goto L35;
            case 116: goto L35;
            case 117: goto L35;
            case 118: goto L35;
            case 119: goto L35;
            case 120: goto L35;
            case 121: goto L35;
            case 122: goto L35;
            case 123: goto L35;
            case 124: goto L35;
            case 125: goto L35;
            case 126: goto L35;
            case 127: goto L35;
            case 128: goto L35;
            case 129: goto L35;
            case 130: goto L35;
            case 131: goto L35;
            case 132: goto L12;
            case 133: goto L35;
            case 134: goto L35;
            case 135: goto L35;
            case 136: goto L35;
            case 137: goto L35;
            case 138: goto L35;
            case 139: goto L35;
            case 140: goto L35;
            case 141: goto L35;
            case 142: goto L35;
            case 143: goto L35;
            case 144: goto L35;
            case 145: goto L35;
            case 146: goto L35;
            case 147: goto L35;
            case 148: goto L35;
            case 149: goto L35;
            case 150: goto L35;
            case 151: goto L35;
            case 152: goto L35;
            case 153: goto L33;
            case 154: goto L33;
            case 155: goto L33;
            case 156: goto L33;
            case 157: goto L33;
            case 158: goto L33;
            case 159: goto L33;
            case 160: goto L33;
            case 161: goto L33;
            case 162: goto L33;
            case 163: goto L33;
            case 164: goto L33;
            case 165: goto L33;
            case 166: goto L33;
            case 167: goto L33;
            case 168: goto L33;
            case 169: goto L34;
            case 170: goto L29;
            case 171: goto L25;
            case 172: goto L35;
            case 173: goto L35;
            case 174: goto L35;
            case 175: goto L35;
            case 176: goto L35;
            case 177: goto L35;
            case 178: goto L12;
            case 179: goto L12;
            case 180: goto L12;
            case 181: goto L12;
            case 182: goto L12;
            case 183: goto L12;
            case 184: goto L12;
            case 185: goto L14;
            case 186: goto L14;
            case 187: goto L12;
            case 188: goto L34;
            case 189: goto L12;
            case 190: goto L35;
            case 191: goto L35;
            case 192: goto L12;
            case 193: goto L12;
            case 194: goto L35;
            case 195: goto L35;
            case 196: goto L16;
            case 197: goto L15;
            case 198: goto L33;
            case 199: goto L33;
            case 200: goto L13;
            case 201: goto L13;
            case 202: goto L11;
            case 203: goto L11;
            case 204: goto L11;
            case 205: goto L11;
            case 206: goto L11;
            case 207: goto L11;
            case 208: goto L11;
            case 209: goto L11;
            case 210: goto L11;
            case 211: goto L11;
            case 212: goto L11;
            case 213: goto L11;
            case 214: goto L11;
            case 215: goto L11;
            case 216: goto L11;
            case 217: goto L11;
            case 218: goto L11;
            case 219: goto L11;
            case 220: goto L13;
            default: goto L9;
        };
    L9:
        Segment$$ExternalSyntheticBUOutline0.m();
        return;
    L13:
        r0.createLabel(r16 + r0.readInt(r1 + 1), r7);
    L14:
        r1 = r1 + 5;
    L15:
        r1 = r1 + 4;
        goto L6
    L16:
        int r3 = r10[r1 + 1] & UByte.MAX_VALUE;
        if (r3 != 132) goto L19;
        r1 = r1 + 6;
        goto L6
    L19:
        if (r3 == 169) goto L15;
        switch(r3) {
            case 21: goto L15;
            case 22: goto L15;
            case 23: goto L15;
            case 24: goto L15;
            case 25: goto L15;
            default: goto L21;
        };
    L21:
        switch(r3) {
            case 54: goto L15;
            case 55: goto L15;
            case 56: goto L15;
            case 57: goto L15;
            case 58: goto L15;
            default: goto L22;
        };
    L22:
        Segment$$ExternalSyntheticBUOutline0.m();
        return;
    L25:
        int r17 = r1 + (4 - (r16 & 3));
        r0.createLabel(r16 + r0.readInt(r17), r7);
        int r2 = r0.readInt(r17 + 4);
        r1 = r17 + 8;
    L26:
        int r32 = r2 - 1;
        if (r2 <= 0) goto L6;
        r0.createLabel(r16 + r0.readInt(r1 + 4), r7);
        r1 = r1 + 8;
        r2 = r32;
        goto L26
    L29:
        int r18 = r1 + (4 - (r16 & 3));
        r0.createLabel(r16 + r0.readInt(r18), r7);
        int r22 = (r0.readInt(r18 + 8) - r0.readInt(r18 + 4)) + 1;
        r1 = r18 + 12;
    L30:
        int r33 = r22 - 1;
        if (r22 <= 0) goto L6;
        r0.createLabel(r16 + r0.readInt(r1), r7);
        r1 = r1 + 4;
        r22 = r33;
        goto L30
    L34:
        r1 = r1 + 2;
        goto L6
    L35:
        r1 = r1 + 1;
        goto L6
    L11:
        r0.createLabel(r16 + r0.readUnsignedShort(r1 + 1), r7);
    L12:
        r1 = r1 + 3;
        goto L6
    L33:
        r0.createLabel(r16 + r0.readShort(r1 + 1), r7);
        goto L12
    L36:
        byte r172 = 8;
        int r4 = r0.readUnsignedShort(r1);
        int r19 = r1 + 2;
    L37:
        int r162 = r4 - 1;
        if (r4 <= 0) goto L40;
        Label r43 = r0.createLabel(r0.readUnsignedShort(r19), r7);
        Label r23 = r0.createLabel(r0.readUnsignedShort(r19 + 2), r7);
        Label r34 = r0.createLabel(r0.readUnsignedShort(r19 + 4), r7);
        String r6 = r0.readUTF8(r0.cpInfoOffsets[r0.readUnsignedShort(r19 + 6)], r5);
        r19 = r19 + 8;
        r8.visitTryCatchBlock(r43, r23, r34, r6);
        r4 = r162;
        goto L37
    L40:
        int r24 = r0.readUnsignedShort(r19);
        int r110 = r19 + 2;
        int[] r62 = null;
        int r163 = 0;
        int[] r21 = null;
        int r222 = 0;
        Attribute r232 = null;
        boolean r242 = true;
        int r25 = 0;
        int r26 = 0;
    L41:
        int r20 = r24 - 1;
        if (r24 <= 0) goto L83;
        String r27 = r0.readUTF8(r110, r5);
        int r35 = r0.readInt(r110 + 2);
        int r36 = r110 + 6;
        if (AttLocalVariableTable.ATTRIBUTE_NAME.equals(r27) == false) goto L55;
        if ((r41.parsingOptions & 2) != 0) goto L52;
        int r28 = r0.readUnsignedShort(r36);
        int r111 = r110 + 8;
    L48:
        int r44 = r28 - 1;
        if (r28 <= 0) goto L51;
        int r29 = r0.readUnsignedShort(r111);
        r0.createDebugLabel(r29, r7);
        int r252 = r111;
        r0.createDebugLabel(r29 + r0.readUnsignedShort(r252 + 2), r7);
        r111 = r252 + 10;
        r28 = r44;
        goto L48
    L51:
        r25 = r36;
    L52:
        int[] r292 = r62;
        byte r82 = r172;
    L53:
        ClassReader r63 = r0;
        Label[] r02 = r7;
    L82:
        r110 = r36 + r35;
        r7 = r02;
        r0 = r63;
        r172 = r82;
        r24 = r20;
        r62 = r292;
        r8 = r40;
        goto L41
    L55:
        if (AttLocalVariableTypeTable.ATTRIBUTE_NAME.equals(r27) == false) goto L58;
        r26 = r36;
        goto L52
    L58:
        if (AttLineNumberTable.ATTRIBUTE_NAME.equals(r27) == false) goto L66;
        if ((r41.parsingOptions & 2) != 0) goto L52;
        int r210 = r0.readUnsignedShort(r36);
        int r112 = r110 + 8;
    L62:
        int r45 = r210 - 1;
        if (r210 <= 0) goto L52;
        int r211 = r0.readUnsignedShort(r112);
        int r282 = r112;
        int r113 = r0.readUnsignedShort(r282 + 2);
        r0.createDebugLabel(r211, r7);
        r7[r211].addLineNumber(r113);
        r210 = r45;
        r112 = r282 + 4;
        goto L62
    L66:
        if ("RuntimeVisibleTypeAnnotations".equals(r27) == false) goto L69;
        r63 = r0;
        r292 = r0.readTypeAnnotations(r8, r41, r36, true);
        r02 = r7;
        r82 = r172;
        goto L82
    L69:
        if ("RuntimeInvisibleTypeAnnotations".equals(r27) == false) goto L72;
        r21 = r0.readTypeAnnotations(r8, r41, r36, false);
        goto L52
    L72:
        if ("StackMapTable".equals(r27) == false) goto L77;
        if ((r41.parsingOptions & 4) != 0) goto L52;
        r163 = r110 + 8;
        r222 = r36 + r35;
        goto L52
    L77:
        if ("StackMap".equals(r27) == true) goto L79;
        r292 = r62;
        r82 = r172;
        Attribute r114 = r0.readAttribute(r41.attributePrototypes, r27, r36, r35, r5, r42, r7);
        r63 = r0;
        r02 = r7;
        r114.nextAttribute = r232;
        r232 = r114;
        goto L82
    L79:
        if ((r41.parsingOptions & 4) != 0) goto L52;
        r163 = r110 + 8;
        r222 = r36 + r35;
        r292 = r62;
        r82 = r172;
        r242 = false;
        goto L53
    L83:
        int[] r293 = r62;
        byte r83 = r172;
        boolean r192 = true;
        ClassReader r64 = r0;
        Label[] r03 = r7;
        Attribute r72 = r232;
        if ((r41.parsingOptions & r83) == 0) goto L86;
        boolean r37 = true;
    L88:
        if (r163 == 0) goto L106;
        r41.currentFrameOffset = -1;
        r41.currentFrameType = 0;
        r41.currentFrameLocalCount = 0;
        r41.currentFrameLocalCountDelta = 0;
        r41.currentFrameLocalTypes = new Object[r12];
        r41.currentFrameStackCount = 0;
        r41.currentFrameStackTypes = new Object[r11];
        if (r37 == false) goto L92;
        r64.computeImplicitFrame(r41);
    L92:
        int r212 = r163;
    L93:
        int r46 = r222;
        if (r212 >= (r46 - 2)) goto L107;
        if (r10[r212] != r83) goto L103;
        int r115 = r64.readUnsignedShort(r212 + 1);
        if (r115 < 0) goto L103;
        if (r115 >= r13) goto L103;
        byte r182 = r83;
        int r173 = r212;
        if ((r10[r14 + r115] & UByte.MAX_VALUE) != 187) goto L104;
        r64.createLabel(r115, r03);
    L104:
        r212 = r173 + 1;
        r222 = r46;
        r83 = r182;
    L103:
        r173 = r212;
        r182 = r83;
    L107:
        if (r37 == true) goto L109;
    L112:
        char[] r84 = r5;
        Attribute r174 = r72;
        byte[] r202 = r10;
        int r213 = r12;
        int r223 = r13;
        Label[] r122 = r03;
        boolean r102 = r37;
        int r73 = r46;
        MethodVisitor r04 = r40;
    L111:
        int[] r116 = r293;
        int r38 = r64.getTypeAnnotationBytecodeOffset(r116, 0);
        int[] r52 = r21;
        int r214 = r64.getTypeAnnotationBytecodeOffset(r52, 0);
        if ((r41.parsingOptions & 256) != 0) goto L116;
        int r233 = 33;
    L117:
        int r47 = r163;
        int r164 = r38;
        int r39 = r47;
        int r132 = r14;
        int r283 = r214;
        boolean r48 = false;
        int r215 = 0;
        int r272 = 0;
    L118:
        if (r132 >= r15) goto L244;
        int r294 = r132;
        int r133 = r294 - r14;
        r64.readBytecodeInstructionOffset(r133);
        int[] r31 = r116;
        Label r117 = r122[r133];
        int r322 = r213;
        if (r117 == null) goto L126;
        if ((r41.parsingOptions & 2) != 0) goto L124;
        boolean r216 = r192;
    L125:
        r117.accept(r04, r216);
        goto L126
    L124:
        r216 = false;
    L126:
        if (r39 == 0) goto L132;
        int r118 = r41.currentFrameOffset;
        if (r118 == r133) goto L133;
        if (r118 != (-1)) goto L132;
    L133:
        if (r118 == (-1)) goto L140;
        if (r242 == false) goto L136;
        if (r102 == true) goto L136;
        int[] r352 = r52;
        int r362 = r14;
        boolean r142 = r242;
        int r243 = r15;
        int[] r152 = r31;
        int r312 = r11;
        int r119 = r39;
        r04.visitFrame(r41.currentFrameType, r41.currentFrameLocalCountDelta, r41.currentFrameLocalTypes, r41.currentFrameStackCount, r41.currentFrameStackTypes);
    L139:
        r48 = false;
    L141:
        if (r119 >= r73) goto L144;
        r39 = r64.readStackMapFrame(r119, r142, r102, r41);
        r04 = r40;
        r11 = r312;
        r52 = r352;
    L143:
        r31 = r152;
        r15 = r243;
        r242 = r142;
        r14 = r362;
        goto L126
    L144:
        r04 = r40;
        r11 = r312;
        r52 = r352;
        r39 = 0;
    L136:
        r352 = r52;
        r362 = r14;
        r142 = r242;
        r243 = r15;
        r152 = r31;
        r312 = r11;
        r119 = r39;
        r40.visitFrame(-1, r41.currentFrameLocalCount, r41.currentFrameLocalTypes, r41.currentFrameStackCount, r41.currentFrameStackTypes);
        goto L139
    L140:
        r352 = r52;
        r362 = r14;
        r142 = r242;
        r243 = r15;
        r152 = r31;
        r312 = r11;
        r119 = r39;
    L132:
        int[] r353 = r52;
        int r363 = r14;
        boolean r143 = r242;
        int r244 = r15;
        int[] r153 = r31;
        int r313 = r11;
        int r1110 = r39;
        if (r48 == true) goto L148;
        r04 = r40;
        boolean r332 = r48;
    L153:
        int r120 = r202[r294] & UByte.MAX_VALUE;
        switch(r120) {
            case 0: goto L222;
            case 1: goto L222;
            case 2: goto L222;
            case 3: goto L222;
            case 4: goto L222;
            case 5: goto L222;
            case 6: goto L222;
            case 7: goto L222;
            case 8: goto L222;
            case 9: goto L222;
            case 10: goto L222;
            case 11: goto L222;
            case 12: goto L222;
            case 13: goto L222;
            case 14: goto L222;
            case 15: goto L222;
            case 16: goto L221;
            case 17: goto L220;
            case 18: goto L219;
            case 19: goto L218;
            case 20: goto L218;
            case 21: goto L216;
            case 22: goto L216;
            case 23: goto L216;
            case 24: goto L216;
            case 25: goto L216;
            case 26: goto L215;
            case 27: goto L215;
            case 28: goto L215;
            case 29: goto L215;
            case 30: goto L215;
            case 31: goto L215;
            case 32: goto L215;
            case 33: goto L215;
            case 34: goto L215;
            case 35: goto L215;
            case 36: goto L215;
            case 37: goto L215;
            case 38: goto L215;
            case 39: goto L215;
            case 40: goto L215;
            case 41: goto L215;
            case 42: goto L215;
            case 43: goto L215;
            case 44: goto L215;
            case 45: goto L215;
            case 46: goto L222;
            case 47: goto L222;
            case 48: goto L222;
            case 49: goto L222;
            case 50: goto L222;
            case 51: goto L222;
            case 52: goto L222;
            case 53: goto L222;
            case 54: goto L216;
            case 55: goto L216;
            case 56: goto L216;
            case 57: goto L216;
            case 58: goto L216;
            case 59: goto L213;
            case 60: goto L213;
            case 61: goto L213;
            case 62: goto L213;
            case 63: goto L213;
            case 64: goto L213;
            case 65: goto L213;
            case 66: goto L213;
            case 67: goto L213;
            case 68: goto L213;
            case 69: goto L213;
            case 70: goto L213;
            case 71: goto L213;
            case 72: goto L213;
            case 73: goto L213;
            case 74: goto L213;
            case 75: goto L213;
            case 76: goto L213;
            case 77: goto L213;
            case 78: goto L213;
            case 79: goto L222;
            case 80: goto L222;
            case 81: goto L222;
            case 82: goto L222;
            case 83: goto L222;
            case 84: goto L222;
            case 85: goto L222;
            case 86: goto L222;
            case 87: goto L222;
            case 88: goto L222;
            case 89: goto L222;
            case 90: goto L222;
            case 91: goto L222;
            case 92: goto L222;
            case 93: goto L222;
            case 94: goto L222;
            case 95: goto L222;
            case 96: goto L222;
            case 97: goto L222;
            case 98: goto L222;
            case 99: goto L222;
            case 100: goto L222;
            case 101: goto L222;
            case 102: goto L222;
            case 103: goto L222;
            case 104: goto L222;
            case 105: goto L222;
            case 106: goto L222;
            case 107: goto L222;
            case 108: goto L222;
            case 109: goto L222;
            case 110: goto L222;
            case 111: goto L222;
            case 112: goto L222;
            case 113: goto L222;
            case 114: goto L222;
            case 115: goto L222;
            case 116: goto L222;
            case 117: goto L222;
            case 118: goto L222;
            case 119: goto L222;
            case 120: goto L222;
            case 121: goto L222;
            case 122: goto L222;
            case 123: goto L222;
            case 124: goto L222;
            case 125: goto L222;
            case 126: goto L222;
            case 127: goto L222;
            case 128: goto L222;
            case 129: goto L222;
            case 130: goto L222;
            case 131: goto L222;
            case 132: goto L212;
            case 133: goto L222;
            case 134: goto L222;
            case 135: goto L222;
            case 136: goto L222;
            case 137: goto L222;
            case 138: goto L222;
            case 139: goto L222;
            case 140: goto L222;
            case 141: goto L222;
            case 142: goto L222;
            case 143: goto L222;
            case 144: goto L222;
            case 145: goto L222;
            case 146: goto L222;
            case 147: goto L222;
            case 148: goto L222;
            case 149: goto L222;
            case 150: goto L222;
            case 151: goto L222;
            case 152: goto L222;
            case 153: goto L211;
            case 154: goto L211;
            case 155: goto L211;
            case 156: goto L211;
            case 157: goto L211;
            case 158: goto L211;
            case 159: goto L211;
            case 160: goto L211;
            case 161: goto L211;
            case 162: goto L211;
            case 163: goto L211;
            case 164: goto L211;
            case 165: goto L211;
            case 166: goto L211;
            case 167: goto L211;
            case 168: goto L211;
            case 169: goto L216;
            case 170: goto L207;
            case 171: goto L203;
            case 172: goto L222;
            case 173: goto L222;
            case 174: goto L222;
            case 175: goto L222;
            case 176: goto L222;
            case 177: goto L222;
            case 178: goto L190;
            case 179: goto L190;
            case 180: goto L190;
            case 181: goto L190;
            case 182: goto L190;
            case 183: goto L190;
            case 184: goto L190;
            case 185: goto L190;
            case 186: goto L186;
            case 187: goto L185;
            case 188: goto L221;
            case 189: goto L185;
            case 190: goto L222;
            case 191: goto L222;
            case 192: goto L185;
            case 193: goto L185;
            case 194: goto L222;
            case 195: goto L222;
            case 196: goto L179;
            case 197: goto L178;
            case 198: goto L211;
            case 199: goto L211;
            case 200: goto L175;
            case 201: goto L175;
            case 202: goto L161;
            case 203: goto L161;
            case 204: goto L161;
            case 205: goto L161;
            case 206: goto L161;
            case 207: goto L161;
            case 208: goto L161;
            case 209: goto L161;
            case 210: goto L161;
            case 211: goto L161;
            case 212: goto L161;
            case 213: goto L161;
            case 214: goto L161;
            case 215: goto L161;
            case 216: goto L161;
            case 217: goto L161;
            case 218: goto L161;
            case 219: goto L161;
            case 220: goto L157;
            default: goto L155;
        };
    L157:
        r04.visitJumpInsn(200, r122[r64.readInt(r294 + 1) + r133]);
        int r121 = r294 + 5;
        int r422 = r73;
        boolean r372 = r102;
        int r217 = r164;
        r48 = r192;
    L158:
        int r310 = r215;
    L223:
        if (r153 == null) goto L231;
        if (r310 >= r153.length) goto L231;
        if (r217 > r133) goto L231;
        if (r217 != r133) goto L229;
        int r218 = r64.readTypeAnnotationTarget(r41, r153[r310]);
        int r165 = r121;
        boolean r123 = r192;
        r64.readElementValues(r04.visitInsnAnnotation(r41.currentTypeAnnotationTarget, r41.currentTypeAnnotationTargetPath, r64.readUTF8(r218, r84), r123), r218 + 2, r123, r84);
    L230:
        r310 = r310 + 1;
        r217 = r64.getTypeAnnotationBytecodeOffset(r153, r310);
        r121 = r165;
        r192 = true;
        goto L223
    L229:
        r165 = r121;
    L231:
        int r166 = r121;
        int r124 = r272;
        int r53 = r283;
    L232:
        if (r353 == null) goto L242;
        int[] r103 = r353;
        if (r124 >= r103.length) goto L240;
        if (r53 > r133) goto L240;
        if (r53 != r133) goto L238;
        int r54 = r64.readTypeAnnotationTarget(r41, r103[r124]);
        int r219 = r124;
        int r273 = r217;
        int r333 = r1110;
        r64.readElementValues(r04.visitInsnAnnotation(r41.currentTypeAnnotationTarget, r41.currentTypeAnnotationTargetPath, r64.readUTF8(r54, r84), false), r54 + 2, true, r84);
    L239:
        r124 = r219 + 1;
        r53 = r64.getTypeAnnotationBytecodeOffset(r103, r124);
        r353 = r103;
        r217 = r273;
        r1110 = r333;
        goto L232
    L238:
        r219 = r124;
        r273 = r217;
        r333 = r1110;
    L240:
        int r2110 = r124;
        int r274 = r217;
        int r334 = r1110;
    L243:
        r73 = r422;
        r283 = r53;
        r52 = r103;
        r116 = r153;
        r132 = r166;
        r15 = r244;
        r164 = r274;
        r11 = r313;
        r213 = r322;
        r102 = r372;
        r192 = true;
        r242 = r143;
        r272 = r2110;
        r14 = r363;
        r215 = r310;
        r39 = r334;
        goto L118
    L242:
        r2110 = r124;
        r274 = r217;
        r334 = r1110;
        r103 = r353;
        goto L243
    L175:
        r04.visitJumpInsn(r120 - r233, r122[r64.readInt(r294 + 1) + r133]);
        r121 = r294 + 5;
    L176:
        r422 = r73;
        r372 = r102;
    L177:
        r217 = r164;
        r310 = r215;
        r48 = r332;
        goto L223
    L178:
        r04.visitMultiANewArrayInsn(r64.readClass(r294 + 1, r84), r202[r294 + 3] & UByte.MAX_VALUE);
        r121 = r294 + 4;
        goto L176
    L179:
        int r125 = r202[r294 + 1] & UByte.MAX_VALUE;
        char r220 = 132;
        if (r125 != 132) goto L184;
        r04.visitIincInsn(r64.readUnsignedShort(r294 + 2), r64.readShort(r294 + 4));
        r121 = r294 + 6;
    L182:
        r422 = r73;
        r372 = r102;
    L183:
        r217 = r164;
        r310 = r215;
        r48 = r332;
        goto L223
    L184:
        r04.visitVarInsn(r125, r64.readUnsignedShort(r294 + 2));
        r121 = r294 + 4;
        goto L182
    L185:
        r220 = 132;
        r04.visitTypeInsn(r120, r64.readClass(r294 + 1, r84));
        r121 = r294 + 3;
        goto L182
    L186:
        int r126 = r64.cpInfoOffsets[r64.readUnsignedShort(r294 + 1)];
        int r311 = r64.cpInfoOffsets[r64.readUnsignedShort(r126 + 2)];
        String r49 = r64.readUTF8(r311, r84);
        String r314 = r64.readUTF8(r311 + 2, r84);
        int r127 = r64.bootstrapMethodOffsets[r64.readUnsignedShort(r126)];
        Handle r55 = (Handle) r64.readConst(r64.readUnsignedShort(r127), r84);
        int r221 = r64.readUnsignedShort(r127 + 2);
        Object[] r128 = new Object[r221];
        r372 = r102;
        int r104 = r127 + 4;
        r422 = r73;
        int r74 = 0;
    L187:
        if (r74 >= r221) goto L189;
        r128[r74] = r64.readConst(r64.readUnsignedShort(r104), r84);
        r104 = r104 + 2;
        r74 = r74 + 1;
        r221 = r221;
        goto L187
    L189:
        r04.visitInvokeDynamicInsn(r49, r314, r55, r128);
        r121 = r294 + 5;
        goto L177
    L190:
        r422 = r73;
        r372 = r102;
        int r224 = r64.cpInfoOffsets[r64.readUnsignedShort(r294 + 1)];
        int r315 = r64.cpInfoOffsets[r64.readUnsignedShort(r224 + 2)];
        String r225 = r64.readClass(r224, r84);
        String r316 = r64.readUTF8(r315, r84);
        String r56 = r64.readUTF8(r315 + 2, r84);
        if (r120 >= 182) goto L194;
        r04.visitFieldInsn(r120, r225, r316, r56);
    L200:
        if (r120 != 185) goto L202;
        r121 = r294 + 5;
    L202:
        r121 = r294 + 3;
        goto L183
    L194:
        if (r202[r224 - 1] != 11) goto L197;
        String r410 = r56;
        boolean r57 = r192;
    L198:
        r04.visitMethodInsn(r120, r225, r316, r410, r57);
        goto L200
    L197:
        r410 = r56;
        r57 = false;
        goto L198
    L203:
        r422 = r73;
        r372 = r102;
        int r129 = r294 + (4 - (r133 & 3));
        Label r226 = r122[r64.readInt(r129) + r133];
        int r317 = r64.readInt(r129 + 4);
        r121 = r129 + 8;
        int[] r411 = new int[r317];
        Label[] r58 = new Label[r317];
        int r75 = 0;
    L204:
        if (r75 >= r317) goto L206;
        r411[r75] = r64.readInt(r121);
        r58[r75] = r122[r64.readInt(r121 + 4) + r133];
        r121 = r121 + 8;
        r75 = r75 + 1;
        goto L204
    L206:
        r04.visitLookupSwitchInsn(r226, r411, r58);
        goto L183
    L207:
        r422 = r73;
        r372 = r102;
        int r130 = r294 + (4 - (r133 & 3));
        Label r227 = r122[r64.readInt(r130) + r133];
        int r318 = r64.readInt(r130 + 4);
        int r412 = r64.readInt(r130 + 8);
        r121 = r130 + 12;
        int r59 = (r412 - r318) + 1;
        Label[] r76 = new Label[r59];
        int r105 = 0;
    L208:
        if (r105 >= r59) goto L210;
        r76[r105] = r122[r133 + r64.readInt(r121)];
        r121 = r121 + 4;
        r105 = r105 + 1;
        goto L208
    L210:
        r04.visitTableSwitchInsn(r318, r412, r227, r76);
        goto L183
    L211:
        r422 = r73;
        r372 = r102;
        r04.visitJumpInsn(r120, r122[r64.readShort(r294 + 1) + r133]);
        goto L202
    L212:
        r422 = r73;
        r372 = r102;
        r04.visitIincInsn(r202[r294 + 1] & UByte.MAX_VALUE, r202[r294 + 2]);
        goto L202
    L213:
        r422 = r73;
        r372 = r102;
        int r131 = r120 - 59;
        r04.visitVarInsn((r131 >> 2) + 54, r131 & 3);
    L214:
        r121 = r294 + 1;
        goto L183
    L215:
        r422 = r73;
        r372 = r102;
        int r134 = r120 - 26;
        r04.visitVarInsn((r134 >> 2) + 21, r134 & 3);
        goto L214
    L216:
        r422 = r73;
        r372 = r102;
        r04.visitVarInsn(r120, r202[r294 + 1] & UByte.MAX_VALUE);
    L217:
        r121 = r294 + 2;
        goto L183
    L218:
        r422 = r73;
        r372 = r102;
        r04.visitLdcInsn(r64.readConst(r64.readUnsignedShort(r294 + 1), r84));
        goto L202
    L219:
        r422 = r73;
        r372 = r102;
        r04.visitLdcInsn(r64.readConst(r202[r294 + 1] & UByte.MAX_VALUE, r84));
        goto L217
    L220:
        r422 = r73;
        r372 = r102;
        r04.visitIntInsn(r120, r64.readShort(r294 + 1));
        goto L202
    L221:
        r422 = r73;
        r372 = r102;
        r04.visitIntInsn(r120, r202[r294 + 1]);
        goto L217
    L222:
        r422 = r73;
        r372 = r102;
        r04.visitInsn(r120);
        goto L214
    L161:
        if (r120 >= 218) goto L163;
        int r135 = r120 - 49;
    L164:
        Label r319 = r122[r64.readUnsignedShort(r294 + 1) + r133];
        if (r135 != 167) goto L167;
    L173:
        r04.visitJumpInsn(r135 + 33, r319);
        boolean r320 = r332;
    L174:
        r121 = r294 + 3;
        r48 = r320;
        r422 = r73;
        r372 = r102;
        r217 = r164;
        goto L158
    L167:
        if (r135 == 168) goto L173;
        if (r135 >= 167) goto L171;
        int r136 = ((r135 + 1) ^ 1) - 1;
    L172:
        r04.visitJumpInsn(r136, r64.createLabel(r133 + 3, r122));
        r04.visitJumpInsn(200, r319);
        r320 = r192;
        goto L174
    L171:
        r136 = r135 ^ 1;
        goto L172
    L163:
        r135 = r120 - 20;
        goto L164
    L155:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return;
    L148:
        if ((r41.parsingOptions & 8) == 0) goto L150;
        r04 = r40;
        r04.visitFrame(256, 0, null, 0, null);
    L151:
        r332 = false;
        goto L153
    L150:
        r04 = r40;
        goto L151
    L244:
        int[] r154 = r116;
        int r323 = r213;
        int[] r106 = r52;
        int r3110 = r11;
        int r1111 = 0;
        Label r137 = r122[r223];
        if (r137 == null) goto L247;
        r04.visitLabel(r137);
    L247:
        int r413 = r25;
        if (r413 != 0) goto L250;
    L275:
        ClassReader r1112 = r64;
        int r1210 = 65;
        int r138 = 64;
        if (r154 == null) goto L286;
        int r144 = r154.length;
        int r05 = 0;
    L278:
        if (r05 >= r144) goto L286;
        int r139 = r154[r05];
        int r228 = r1112.readByte(r139);
        if (r228 == 64) goto L284;
        if (r228 == r1210) goto L284;
        int r167 = r05;
    L285:
        r05 = r167 + 1;
        r1210 = 65;
    L284:
        int r140 = r1112.readTypeAnnotationTarget(r41, r139);
        String r65 = r1112.readUTF8(r140, r84);
        r167 = r05;
        r1112.readElementValues(r40.visitLocalVariableAnnotation(r41.currentTypeAnnotationTarget, r41.currentTypeAnnotationTargetPath, r41.currentLocalVariableAnnotationRangeStarts, r41.currentLocalVariableAnnotationRangeEnds, r41.currentLocalVariableAnnotationRangeIndices, r65, true), r140 + 2, true, r84);
    L286:
        if (r106 == null) goto L296;
        int r1211 = r106.length;
        int r145 = 0;
    L288:
        if (r145 >= r1211) goto L296;
        int r06 = r106[r145];
        int r141 = r1112.readByte(r06);
        if (r141 == r138) goto L294;
        if (r141 == 65) goto L294;
    L295:
        r145 = r145 + 1;
        r138 = 64;
    L294:
        int r07 = r1112.readTypeAnnotationTarget(r41, r06);
        r1112.readElementValues(r40.visitLocalVariableAnnotation(r41.currentTypeAnnotationTarget, r41.currentTypeAnnotationTargetPath, r41.currentLocalVariableAnnotationRangeStarts, r41.currentLocalVariableAnnotationRangeEnds, r41.currentLocalVariableAnnotationRangeIndices, r1112.readUTF8(r07, r84), false), r07 + 2, true, r84);
    L296:
        Attribute r146 = r174;
    L297:
        if (r146 == null) goto L299;
        Attribute r229 = r146.nextAttribute;
        r146.nextAttribute = null;
        r40.visitAttribute(r146);
        r146 = r229;
        goto L297
    L299:
        r40.visitMaxs(r3110, r323);
        return;
    L250:
        if ((r41.parsingOptions & 2) != 0) goto L275;
        int r147 = r26;
        if (r147 == 0) goto L257;
        int r230 = r64.readUnsignedShort(r147) * 3;
        int[] r321 = new int[r230];
        int r148 = r147 + 2;
    L254:
        if (r230 <= 0) goto L256;
        r321[r230 - 1] = r148 + 6;
        r321[r230 - 2] = r64.readUnsignedShort(r148 + 8);
        r230 = r230 - 3;
        r321[r230] = r64.readUnsignedShort(r148);
        r148 = r148 + 10;
        goto L254
    L256:
        int[] r77 = r321;
    L258:
        int r149 = r64.readUnsignedShort(r413);
        int r231 = r413 + 2;
    L259:
        int r1310 = r149 - 1;
        if (r149 <= 0) goto L275;
        int r150 = r64.readUnsignedShort(r231);
        int r324 = r64.readUnsignedShort(r231 + 2);
        String r414 = r64.readUTF8(r231 + 4, r84);
        String r510 = r64.readUTF8(r231 + 6, r84);
        int r1410 = r64.readUnsignedShort(r231 + 8);
        int r168 = r231 + 10;
        if (r77 == null) goto L273;
        int r234 = r1111;
    L265:
        if (r234 >= r77.length) goto L273;
        if (r77[r234] != r150) goto L272;
        if (r77[r234 + 1] != r1410) goto L272;
        String r325 = r64.readUTF8(r77[r234 + 2], r84);
        int r235 = r324;
    L274:
        r04.visitLocalVariable(r414, r510, r325, r122[r150], r122[r235 + r150], r1410);
        r04 = r40;
        r64 = r64;
        r149 = r1310;
        r231 = r168;
        r1111 = 0;
    L272:
        r234 = r234 + 3;
    L273:
        r235 = r324;
        r325 = null;
        goto L274
    L257:
        r77 = null;
        goto L258
    L116:
        r233 = 0;
        goto L117
    L109:
        if ((r41.parsingOptions & 256) == 0) goto L112;
        r174 = r72;
        r202 = r10;
        r73 = r46;
        r102 = r37;
        r223 = r13;
        r84 = r5;
        r213 = r12;
        r122 = r03;
        r04 = r40;
        r04.visitFrame(-1, r213, null, 0, null);
        goto L111
    L106:
        r46 = r222;
        goto L107
    L86:
        r37 = false;
        goto L88
    L301:
        Segment$$ExternalSyntheticBUOutline0.m();
    }

    private ConstantDynamic readConstantDynamic(int r9, char[] r10) {
        ConstantDynamic r0 = this.constantDynamicValues[r9];
        if (r0 == null) goto L5;
        return r0;
    L5:
        int[] r02 = this.cpInfoOffsets;
        int r1 = r02[r9];
        int r03 = r02[readUnsignedShort(r1 + 2)];
        String r2 = readUTF8(r03, r10);
        String r04 = readUTF8(r03 + 2, r10);
        int r12 = this.bootstrapMethodOffsets[readUnsignedShort(r1)];
        Handle r3 = (Handle) readConst(readUnsignedShort(r12), r10);
        int r4 = readUnsignedShort(r12 + 2);
        Object[] r5 = new Object[r4];
        int r13 = r12 + 4;
        int r6 = 0;
    L6:
        if (r6 >= r4) goto L8;
        r5[r6] = readConst(readUnsignedShort(r13), r10);
        r13 = r13 + 2;
        r6 = r6 + 1;
        goto L6
    L8:
        ConstantDynamic[] r8 = this.constantDynamicValues;
        ConstantDynamic r102 = new ConstantDynamic(r2, r04, r3, r5);
        r8[r9] = r102;
        return r102;
    }

    private int readElementValue(AnnotationVisitor r11, int r12, String r13, char[] r14) {
        byte[] r0 = this.classFileBuffer;
        int r4 = 0;
        if (r11 != null) goto L16;
        int r112 = r0[r12] & UByte.MAX_VALUE;
        if (r112 == 64) goto L15;
        if (r112 == 91) goto L13;
        if (r112 == 101) goto L11;
        return r12 + 3;
    L11:
        return r12 + 5;
    L13:
        return readElementValues(null, r12 + 1, false, r14);
    L15:
        return readElementValues(null, r12 + 3, true, r14);
    L16:
        int r6 = r12 + 1;
        int r02 = r0[r12] & UByte.MAX_VALUE;
        if (r02 == 64) goto L115;
        if (r02 != 70) goto L21;
    L112:
        r11.visit(r13, readConst(readUnsignedShort(r6), r14));
        return r12 + 3;
    L21:
        if (r02 != 83) goto L23;
        r11.visit(r13, Short.valueOf((short) readInt(this.cpInfoOffsets[readUnsignedShort(r6)])));
        return r12 + 3;
    L23:
        if (r02 == 99) goto L108;
        if (r02 != 101) goto L26;
        r11.visitEnum(r13, readUTF8(r6, r14), readUTF8(r12 + 3, r14));
        return r12 + 5;
    L26:
        if (r02 != 115) goto L28;
        r11.visit(r13, readUTF8(r6, r14));
        return r12 + 3;
    L28:
        if (r02 == 73) goto L112;
        if (r02 == 74) goto L112;
        if (r02 == 90) goto L99;
        if (r02 == 91) goto L41;
        switch(r02) {
            case 66: goto L39;
            case 67: goto L37;
            case 68: goto L112;
            default: goto L35;
        };
    L35:
        Segment$$ExternalSyntheticBUOutline0.m();
        return 0;
    L37:
        r11.visit(r13, Character.valueOf((char) readInt(this.cpInfoOffsets[readUnsignedShort(r6)])));
        return r12 + 3;
    L39:
        r11.visit(r13, Byte.valueOf((byte) readInt(this.cpInfoOffsets[readUnsignedShort(r6)])));
        return r12 + 3;
    L41:
        int r03 = readUnsignedShort(r6);
        int r2 = r12 + 3;
        if (r03 == 0) goto L44;
        int r62 = this.classFileBuffer[r2] & UByte.MAX_VALUE;
        if (r62 == 70) goto L93;
        if (r62 == 83) goto L88;
        if (r62 == 90) goto L79;
        if (r62 == 73) goto L74;
        if (r62 == 74) goto L69;
        switch(r62) {
            case 66: goto L64;
            case 67: goto L59;
            case 68: goto L54;
            default: goto L53;
        };
    L54:
        double[] r122 = new double[r03];
    L55:
        if (r4 >= r03) goto L57;
        r122[r4] = Double.longBitsToDouble(readLong(this.cpInfoOffsets[readUnsignedShort(r2 + 1)]));
        r2 = r2 + 3;
        r4 = r4 + 1;
        goto L55
    L57:
        r11.visit(r13, r122);
        return r2;
    L59:
        char[] r123 = new char[r03];
    L60:
        if (r4 >= r03) goto L62;
        r123[r4] = (char) readInt(this.cpInfoOffsets[readUnsignedShort(r2 + 1)]);
        r2 = r2 + 3;
        r4 = r4 + 1;
        goto L60
    L62:
        r11.visit(r13, r123);
        return r2;
    L64:
        byte[] r124 = new byte[r03];
    L65:
        if (r4 >= r03) goto L67;
        r124[r4] = (byte) readInt(this.cpInfoOffsets[readUnsignedShort(r2 + 1)]);
        r2 = r2 + 3;
        r4 = r4 + 1;
        goto L65
    L67:
        r11.visit(r13, r124);
        return r2;
    L53:
        return readElementValues(r11.visitArray(r13), r12 + 1, false, r14);
    L69:
        long[] r125 = new long[r03];
    L70:
        if (r4 >= r03) goto L72;
        r125[r4] = readLong(this.cpInfoOffsets[readUnsignedShort(r2 + 1)]);
        r2 = r2 + 3;
        r4 = r4 + 1;
        goto L70
    L72:
        r11.visit(r13, r125);
        return r2;
    L74:
        int[] r126 = new int[r03];
    L75:
        if (r4 >= r03) goto L77;
        r126[r4] = readInt(this.cpInfoOffsets[readUnsignedShort(r2 + 1)]);
        r2 = r2 + 3;
        r4 = r4 + 1;
        goto L75
    L77:
        r11.visit(r13, r126);
        return r2;
    L79:
        boolean[] r127 = new boolean[r03];
        int r142 = 0;
    L80:
        if (r142 >= r03) goto L86;
        if (readInt(this.cpInfoOffsets[readUnsignedShort(r2 + 1)]) == 0) goto L84;
        boolean r1 = true;
    L85:
        r127[r142] = r1;
        r2 = r2 + 3;
        r142 = r142 + 1;
        goto L80
    L84:
        r1 = false;
        goto L85
    L86:
        r11.visit(r13, r127);
        return r2;
    L88:
        short[] r128 = new short[r03];
    L89:
        if (r4 >= r03) goto L91;
        r128[r4] = (short) readInt(this.cpInfoOffsets[readUnsignedShort(r2 + 1)]);
        r2 = r2 + 3;
        r4 = r4 + 1;
        goto L89
    L91:
        r11.visit(r13, r128);
        return r2;
    L93:
        float[] r129 = new float[r03];
    L94:
        if (r4 >= r03) goto L96;
        r129[r4] = Float.intBitsToFloat(readInt(this.cpInfoOffsets[readUnsignedShort(r2 + 1)]));
        r2 = r2 + 3;
        r4 = r4 + 1;
        goto L94
    L96:
        r11.visit(r13, r129);
        return r2;
    L44:
        return readElementValues(r11.visitArray(r13), r12 + 1, false, r14);
    L99:
        if (readInt(this.cpInfoOffsets[readUnsignedShort(r6)]) != 0) goto L101;
        Object r10 = Boolean.FALSE;
    L102:
        r11.visit(r13, r10);
        return r12 + 3;
    L101:
        r10 = Boolean.TRUE;
        goto L102
    L108:
        r11.visit(r13, Type.getType(readUTF8(r6, r14)));
        return r12 + 3;
    L115:
        return readElementValues(r11.visitAnnotation(r13, readUTF8(r6, r14)), r12 + 3, true, r14);
    }

    private int readElementValues(AnnotationVisitor r2, int r3, boolean r4, char[] r5) {
        int r0 = readUnsignedShort(r3);
        int r32 = r3 + 2;
        if (r4 == false) goto L7;
    L4:
        int r42 = r0 - 1;
        if (r0 <= 0) goto L10;
        r32 = readElementValue(r2, r32 + 2, readUTF8(r32, r5), r5);
        r0 = r42;
    L10:
        if (r2 == null) goto L12;
        r2.visitEnd();
    L12:
        return r32;
    L7:
        int r43 = r0 - 1;
        if (r0 <= 0) goto L10;
        r32 = readElementValue(r2, r32, null, r5);
        r0 = r43;
        goto L7
    }

    private int readField(ClassVisitor r22, Context r23, int r24) {
        Context r8 = r23;
        char[] r5 = r8.charBuffer;
        int r2 = readUnsignedShort(r24);
        String r11 = readUTF8(r24 + 2, r5);
        String r12 = readUTF8(r24 + 4, r5);
        int r3 = readUnsignedShort(r24 + 6);
        int r1 = r24 + 8;
        int r10 = r2;
        int r25 = 0;
        int r4 = 0;
        int r6 = 0;
        int r7 = 0;
        String r13 = null;
        Object r14 = null;
        Attribute r15 = null;
    L3:
        int r16 = r3 - 1;
        if (r3 <= 0) goto L38;
        int r32 = r25;
        String r26 = readUTF8(r1, r5);
        int r9 = readInt(r1 + 2);
        int r17 = r1 + 6;
        if (AttConstantValue.ATTRIBUTE_NAME.equals(r26) == false) goto L15;
        int r27 = readUnsignedShort(r17);
        if (r27 != 0) goto L10;
        r14 = null;
    L11:
        int r33 = r17;
    L12:
        r17 = r4;
    L13:
        int r42 = r9;
        r25 = r32;
    L37:
        int r34 = r33 + r42;
        r8 = r23;
        r4 = r17;
        r1 = r34;
        r3 = r16;
        goto L3
    L10:
        r14 = readConst(r27, r5);
        goto L11
    L15:
        if (AttSignature.ATTRIBUTE_NAME.equals(r26) == false) goto L18;
        r13 = readUTF8(r17, r5);
        goto L11
    L18:
        if (AttDeprecated.ATTRIBUTE_NAME.equals(r26) == false) goto L22;
        int r28 = 131072 | r10;
    L20:
        r33 = r17;
        r10 = r28;
        goto L12
    L22:
        if (AttSynthetic.ATTRIBUTE_NAME.equals(r26) == false) goto L25;
        r28 = r10 | 4096;
        goto L20
    L25:
        if (AttRuntimeVisibleAnnotations.ATTRIBUTE_NAME.equals(r26) == false) goto L28;
        r25 = r17;
        r33 = r25;
        r17 = r4;
        r42 = r9;
        goto L37
    L28:
        if ("RuntimeVisibleTypeAnnotations".equals(r26) == false) goto L31;
        r33 = r17;
        r6 = r33;
        goto L12
    L31:
        if (AttRuntimeInvisibleAnnotations.ATTRIBUTE_NAME.equals(r26) == false) goto L34;
        r33 = r17;
        goto L13
    L34:
        if ("RuntimeInvisibleTypeAnnotations".equals(r26) == false) goto L36;
        r33 = r17;
        r7 = r33;
        goto L12
    L36:
        r33 = r17;
        int r92 = r4;
        r42 = r9;
        Attribute r18 = readAttribute(r8.attributePrototypes, r26, r33, r42, r5, -1, null);
        r18.nextAttribute = r15;
        r15 = r18;
        r6 = r6;
        r17 = r92;
        r25 = r32;
        r7 = r7;
        goto L37
    L38:
        int r35 = r25;
        int r29 = r4;
        int r82 = r6;
        int r20 = r7;
        FieldVisitor r62 = r22.visitField(r10, r11, r12, r13, r14);
        if (r62 != null) goto L42;
        return r1;
    L42:
        if (r35 == 0) goto L47;
        int r93 = readUnsignedShort(r35);
        int r36 = r35 + 2;
    L44:
        int r102 = r93 - 1;
        if (r93 <= 0) goto L47;
        r36 = readElementValues(r62.visitAnnotation(readUTF8(r36, r5), true), r36 + 2, true, r5);
        r93 = r102;
    L47:
        if (r29 == 0) goto L52;
        int r37 = readUnsignedShort(r29);
        int r210 = r29 + 2;
    L49:
        int r94 = r37 - 1;
        if (r37 <= 0) goto L52;
        r210 = readElementValues(r62.visitAnnotation(readUTF8(r210, r5), false), r210 + 2, true, r5);
        r37 = r94;
    L52:
        if (r82 == 0) goto L58;
        int r211 = readUnsignedShort(r82);
        int r38 = r82 + 2;
    L54:
        int r83 = r211 - 1;
        if (r211 <= 0) goto L58;
        int r39 = readTypeAnnotationTarget(r23, r38);
        r38 = readElementValues(r62.visitTypeAnnotation(r23.currentTypeAnnotationTarget, r23.currentTypeAnnotationTargetPath, readUTF8(r39, r5), true), r39 + 2, true, r5);
        r211 = r83;
    L58:
        if (r20 == 0) goto L63;
        int r310 = readUnsignedShort(r20);
        int r84 = r20 + 2;
    L60:
        int r95 = r310 - 1;
        if (r310 <= 0) goto L63;
        int r311 = readTypeAnnotationTarget(r23, r84);
        r84 = readElementValues(r62.visitTypeAnnotation(r23.currentTypeAnnotationTarget, r23.currentTypeAnnotationTargetPath, readUTF8(r311, r5), false), r311 + 2, true, r5);
        r310 = r95;
    L63:
        if (r15 == null) goto L65;
        Attribute r0 = r15.nextAttribute;
        r15.nextAttribute = null;
        r62.visitAttribute(r15);
        r15 = r0;
        goto L63
    L65:
        r62.visitEnd();
        return r1;
    }

    private int readMethod(ClassVisitor r32, Context r33, int r34) {
        ClassReader r0 = this;
        char[] r5 = r33.charBuffer;
        r33.currentMethodAccessFlags = r0.readUnsignedShort(r34);
        r33.currentMethodName = r0.readUTF8(r34 + 2, r5);
        int r10 = r34 + 4;
        r33.currentMethodDescriptor = r0.readUTF8(r10, r5);
        int r1 = r0.readUnsignedShort(r34 + 6);
        int r7 = r34 + 8;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        int r6 = 0;
        Attribute r11 = null;
        int r13 = 0;
        int r14 = 0;
        int r15 = 0;
        String[] r19 = null;
        boolean r20 = false;
        int r21 = 0;
        int r22 = 0;
        int r23 = 0;
        int r24 = 0;
    L3:
        int r16 = r1 - 1;
        if (r1 <= 0) goto L53;
        int r12 = r2;
        String r25 = r0.readUTF8(r7, r5);
        int r122 = r0.readInt(r7 + 2);
        int r17 = r3;
        int r35 = r7 + 6;
        int r18 = r12;
        if (AttCode.ATTRIBUTE_NAME.equals(r25) == false) goto L13;
        if ((r33.parsingOptions & 1) != 0) goto L10;
        r24 = r35;
    L10:
        int r28 = r4;
    L11:
        char[] r26 = r5;
        int r42 = r122;
    L52:
        r7 = r35 + r42;
        r5 = r26;
        r1 = r16;
        r3 = r17;
        r2 = r18;
        r4 = r28;
        goto L3
    L13:
        if (AttExceptions.ATTRIBUTE_NAME.equals(r25) == false) goto L19;
        int r110 = r0.readUnsignedShort(r35);
        int r72 = r7 + 8;
        r19 = new String[r110];
        int r27 = 0;
    L15:
        if (r27 >= r110) goto L17;
        r19[r27] = r0.readClass(r72, r5);
        r72 = r72 + 2;
        r27 = r27 + 1;
        goto L15
    L17:
        r21 = r35;
        goto L10
    L19:
        if (AttSignature.ATTRIBUTE_NAME.equals(r25) == false) goto L22;
        r13 = r0.readUnsignedShort(r35);
        goto L10
    L22:
        if (AttDeprecated.ATTRIBUTE_NAME.equals(r25) == false) goto L25;
        r33.currentMethodAccessFlags |= 131072;
        goto L10
    L25:
        if (AttRuntimeVisibleAnnotations.ATTRIBUTE_NAME.equals(r25) == false) goto L28;
        r18 = r35;
        goto L10
    L28:
        if ("RuntimeVisibleTypeAnnotations".equals(r25) == false) goto L31;
        r28 = r35;
        goto L11
    L31:
        if (AttAnnotationDefault.ATTRIBUTE_NAME.equals(r25) == false) goto L34;
        r15 = r35;
        goto L10
    L34:
        if (AttSynthetic.ATTRIBUTE_NAME.equals(r25) == false) goto L37;
        r33.currentMethodAccessFlags |= 4096;
        r28 = r4;
        r26 = r5;
        r42 = r122;
        r20 = true;
        goto L52
    L37:
        if (AttRuntimeInvisibleAnnotations.ATTRIBUTE_NAME.equals(r25) == false) goto L40;
        r17 = r35;
        goto L10
    L40:
        if ("RuntimeInvisibleTypeAnnotations".equals(r25) == false) goto L43;
        r6 = r35;
        goto L10
    L43:
        if (AttRuntimeVisibleParameterAnnotations.ATTRIBUTE_NAME.equals(r25) == false) goto L46;
        r22 = r35;
        goto L10
    L46:
        if (AttRuntimeInvisibleParameterAnnotations.ATTRIBUTE_NAME.equals(r25) == false) goto L49;
        r23 = r35;
        goto L10
    L49:
        if ("MethodParameters".equals(r25) == false) goto L51;
        r14 = r35;
        goto L10
    L51:
        r28 = r4;
        r42 = r122;
        Attribute r111 = r0.readAttribute(r33.attributePrototypes, r25, r35, r42, r5, -1, null);
        r26 = r5;
        r111.nextAttribute = r11;
        r11 = r111;
        r6 = r6;
        goto L52
    L53:
        int r123 = r2;
        int r272 = r3;
        int r282 = r4;
        char[] r29 = r5;
        int r292 = r6;
        int r112 = r15;
        int r152 = r33.currentMethodAccessFlags;
        String r36 = r33.currentMethodName;
        String r43 = r33.currentMethodDescriptor;
        if (r13 != 0) goto L57;
        String r182 = null;
    L56:
        int r113 = r14;
        MethodVisitor r142 = r32.visitMethod(r152, r36, r43, r182, r19);
        if (r142 != null) goto L62;
        return r7;
    L62:
        if ((r142 instanceof MethodWriter) == false) goto L71;
        MethodWriter r44 = (MethodWriter) r142;
        int r153 = r112;
        if ((r33.currentMethodAccessFlags & 131072) == 0) goto L66;
        boolean r37 = true;
    L67:
        int r52 = r0.readUnsignedShort(r10);
        int r53 = r13;
        int r132 = r113;
        char[] r102 = r29;
        boolean r210 = r44.canCopyMethodAttributes(r0, r20, r37, r52, r53, r21);
        r0 = r0;
        if (r210 == false) goto L72;
        r44.setMethodAttributesSource(r34, r7 - r34);
        return r7;
    L72:
        if (r132 != 0) goto L74;
    L79:
        if (r153 == 0) goto L83;
        AnnotationVisitor r114 = r142.visitAnnotationDefault();
        r0.readElementValue(r114, r153, null, r102);
        if (r114 == null) goto L83;
        r114.visitEnd();
    L83:
        if (r123 == 0) goto L89;
        int r115 = r0.readUnsignedShort(r123);
        int r211 = r123 + 2;
    L85:
        int r38 = r115 - 1;
        if (r115 <= 0) goto L89;
        r211 = r0.readElementValues(r142.visitAnnotation(r0.readUTF8(r211, r102), true), r211 + 2, true, r102);
        r115 = r38;
    L89:
        if (r272 == 0) goto L95;
        int r212 = r0.readUnsignedShort(r272);
        int r39 = r272 + 2;
    L91:
        int r116 = r212 - 1;
        if (r212 <= 0) goto L95;
        r39 = r0.readElementValues(r142.visitAnnotation(r0.readUTF8(r39, r102), false), r39 + 2, true, r102);
        r212 = r116;
    L95:
        if (r282 == 0) goto L101;
        int r213 = r0.readUnsignedShort(r282);
        int r45 = r282 + 2;
    L97:
        int r117 = r213 - 1;
        if (r213 <= 0) goto L101;
        int r214 = r0.readTypeAnnotationTarget(r33, r45);
        r45 = r0.readElementValues(r142.visitTypeAnnotation(r33.currentTypeAnnotationTarget, r33.currentTypeAnnotationTargetPath, r0.readUTF8(r214, r102), true), r214 + 2, true, r102);
        r213 = r117;
    L101:
        if (r292 == 0) goto L106;
        int r118 = r0.readUnsignedShort(r292);
        int r62 = r292 + 2;
    L103:
        int r215 = r118 - 1;
        if (r118 <= 0) goto L106;
        int r119 = r0.readTypeAnnotationTarget(r33, r62);
        r62 = r0.readElementValues(r142.visitTypeAnnotation(r33.currentTypeAnnotationTarget, r33.currentTypeAnnotationTargetPath, r0.readUTF8(r119, r102), false), r119 + 2, true, r102);
        r118 = r215;
    L106:
        int r120 = r22;
        if (r120 == 0) goto L109;
        r0.readParameterAnnotations(r142, r33, r120, true);
    L109:
        int r121 = r23;
        if (r121 == 0) goto L112;
        r0.readParameterAnnotations(r142, r33, r121, false);
    L112:
        if (r11 == null) goto L114;
        Attribute r124 = r11.nextAttribute;
        r11.nextAttribute = null;
        r142.visitAttribute(r11);
        r11 = r124;
        goto L112
    L114:
        int r1110 = r24;
        if (r1110 == 0) goto L117;
        r142.visitCode();
        r0.readCode(r142, r33, r1110);
    L117:
        r142.visitEnd();
        return r7;
    L74:
        if ((r33.parsingOptions & 2) != 0) goto L79;
        int r125 = r0.readByte(r132);
        int r216 = r132 + 1;
    L76:
        int r310 = r125 - 1;
        if (r125 <= 0) goto L79;
        r142.visitParameter(r0.readUTF8(r216, r102), r0.readUnsignedShort(r216 + 2));
        r216 = r216 + 4;
        r125 = r310;
        goto L76
    L66:
        r37 = false;
        goto L67
    L71:
        r132 = r113;
        r102 = r29;
        r153 = r112;
        goto L72
    L57:
        r182 = r0.readUtf(r13, r29);
        goto L56
    }

    private void readModuleAttributes(ClassVisitor r8, Context r9, int r10, int r11, String r12) {
        char[] r92 = r9.charBuffer;
        int r3 = r10 + 6;
        ModuleVisitor r82 = r8.visitModule(readModule(r10, r92), readUnsignedShort(r10 + 2), readUTF8(r10 + 4, r92));
        if (r82 != null) goto L5;
        return;
    L5:
        if (r12 == null) goto L7;
        r82.visitMainClass(r12);
    L7:
        if (r11 == 0) goto L12;
        int r122 = readUnsignedShort(r11);
        int r112 = r11 + 2;
    L9:
        int r0 = r122 - 1;
        if (r122 <= 0) goto L12;
        r82.visitPackage(readPackage(r112, r92));
        r112 = r112 + 2;
        r122 = r0;
    L12:
        int r113 = readUnsignedShort(r3);
        int r102 = r10 + 8;
    L13:
        int r123 = r113 - 1;
        if (r113 <= 0) goto L16;
        String r114 = readModule(r102, r92);
        int r02 = readUnsignedShort(r102 + 2);
        String r1 = readUTF8(r102 + 4, r92);
        r102 = r102 + 6;
        r82.visitRequire(r114, r02, r1);
        r113 = r123;
        goto L13
    L16:
        int r115 = readUnsignedShort(r102);
        int r103 = r102 + 2;
    L17:
        int r124 = r115 - 1;
        String[] r03 = null;
        int r13 = 0;
        if (r115 <= 0) goto L25;
        String r116 = readPackage(r103, r92);
        int r2 = readUnsignedShort(r103 + 2);
        int r32 = readUnsignedShort(r103 + 4);
        r103 = r103 + 6;
        if (r32 == 0) goto L24;
        r03 = new String[r32];
    L22:
        if (r13 >= r32) goto L24;
        r03[r13] = readModule(r103, r92);
        r103 = r103 + 2;
        r13 = r13 + 1;
    L24:
        r82.visitExport(r116, r2, r03);
        r115 = r124;
        goto L17
    L25:
        int r117 = readUnsignedShort(r103);
        int r104 = r103 + 2;
    L26:
        int r125 = r117 - 1;
        if (r117 <= 0) goto L35;
        String r118 = readPackage(r104, r92);
        int r22 = readUnsignedShort(r104 + 2);
        int r33 = readUnsignedShort(r104 + 4);
        r104 = r104 + 6;
        if (r33 == 0) goto L33;
        String[] r4 = new String[r33];
        int r5 = 0;
    L31:
        if (r5 >= r33) goto L34;
        r4[r5] = readModule(r104, r92);
        r104 = r104 + 2;
        r5 = r5 + 1;
    L34:
        r82.visitOpen(r118, r22, r4);
        r117 = r125;
        goto L26
    L33:
        r4 = null;
        goto L34
    L35:
        int r119 = readUnsignedShort(r104);
        int r105 = r104 + 2;
    L36:
        int r126 = r119 - 1;
        if (r119 <= 0) goto L39;
        r82.visitUse(readClass(r105, r92));
        r105 = r105 + 2;
        r119 = r126;
        goto L36
    L39:
        int r1110 = readUnsignedShort(r105);
        int r106 = r105 + 2;
    L40:
        int r127 = r1110 - 1;
        if (r1110 <= 0) goto L46;
        String r1111 = readClass(r106, r92);
        int r04 = readUnsignedShort(r106 + 2);
        r106 = r106 + 4;
        String[] r23 = new String[r04];
        int r34 = 0;
    L43:
        if (r34 >= r04) goto L45;
        r23[r34] = readClass(r106, r92);
        r106 = r106 + 2;
        r34 = r34 + 1;
        goto L43
    L45:
        r82.visitProvide(r1111, r23);
        r1110 = r127;
        goto L40
    L46:
        r82.visitEnd();
    }

    private void readParameterAnnotations(MethodVisitor r6, Context r7, int r8, boolean r9) {
        int r1 = r8 + 1;
        int r82 = this.classFileBuffer[r8] & UByte.MAX_VALUE;
        r6.visitAnnotableParameterCount(r82, r9);
        char[] r72 = r7.charBuffer;
        int r0 = 0;
    L3:
        if (r0 >= r82) goto L9;
        int r2 = readUnsignedShort(r1);
        r1 = r1 + 2;
    L5:
        int r3 = r2 - 1;
        if (r2 <= 0) goto L8;
        r1 = readElementValues(r6.visitParameterAnnotation(r0, readUTF8(r1, r72), r9), r1 + 2, true, r72);
        r2 = r3;
        goto L5
    L8:
        r0 = r0 + 1;
        goto L3
    }

    private int readRecordComponent(ClassVisitor r21, Context r22, int r23) {
        char[] r5 = r22.charBuffer;
        String r9 = readUTF8(r23, r5);
        String r10 = readUTF8(r23 + 2, r5);
        int r2 = readUnsignedShort(r23 + 4);
        int r1 = r23 + 6;
        int r3 = 0;
        Attribute r4 = null;
        int r6 = 0;
        String r13 = null;
        int r14 = 0;
        int r15 = 0;
    L3:
        int r16 = r2 - 1;
        if (r2 <= 0) goto L23;
        String r24 = readUTF8(r1, r5);
        int r7 = readInt(r1 + 2);
        int r12 = r1 + 6;
        if (AttSignature.ATTRIBUTE_NAME.equals(r24) == false) goto L10;
        r13 = readUTF8(r12, r5);
        int r25 = r3;
        int r32 = r12;
        r12 = r25;
    L8:
        Attribute r26 = r4;
        int r42 = r7;
    L22:
        int r33 = r32 + r42;
        r3 = r12;
        r1 = r33;
        r4 = r26;
        r2 = r16;
        goto L3
    L10:
        if (AttRuntimeVisibleAnnotations.ATTRIBUTE_NAME.equals(r24) == false) goto L13;
        r14 = r12;
        r26 = r4;
        r42 = r7;
        r12 = r3;
        r32 = r14;
        goto L22
    L13:
        if ("RuntimeVisibleTypeAnnotations".equals(r24) == false) goto L16;
        r32 = r12;
        goto L8
    L16:
        if (AttRuntimeInvisibleAnnotations.ATTRIBUTE_NAME.equals(r24) == false) goto L19;
        r15 = r12;
        r26 = r4;
        r42 = r7;
        r12 = r3;
        r32 = r15;
        goto L22
    L19:
        if ("RuntimeInvisibleTypeAnnotations".equals(r24) == false) goto L21;
        r6 = r12;
        r26 = r4;
        r42 = r7;
        r12 = r3;
        r32 = r6;
        goto L22
    L21:
        int r11 = r3;
        r32 = r12;
        Attribute r18 = r4;
        r42 = r7;
        Attribute r17 = readAttribute(r22.attributePrototypes, r24, r32, r42, r5, -1, null);
        r17.nextAttribute = r18;
        r26 = r17;
        r12 = r11;
        r6 = r6;
        goto L22
    L23:
        int r112 = r3;
        Attribute r122 = r4;
        int r19 = r6;
        RecordComponentVisitor r27 = r21.visitRecordComponent(r9, r10, r13);
        if (r27 != null) goto L27;
        return r1;
    L27:
        if (r14 == 0) goto L32;
        int r43 = readUnsignedShort(r14);
        int r142 = r14 + 2;
    L29:
        int r62 = r43 - 1;
        if (r43 <= 0) goto L32;
        r142 = readElementValues(r27.visitAnnotation(readUTF8(r142, r5), true), r142 + 2, true, r5);
        r43 = r62;
    L32:
        if (r15 == 0) goto L37;
        int r44 = readUnsignedShort(r15);
        int r152 = r15 + 2;
    L34:
        int r63 = r44 - 1;
        if (r44 <= 0) goto L37;
        r152 = readElementValues(r27.visitAnnotation(readUTF8(r152, r5), false), r152 + 2, true, r5);
        r44 = r63;
    L37:
        if (r112 == 0) goto L43;
        int r45 = readUnsignedShort(r112);
        int r64 = r112 + 2;
    L39:
        int r72 = r45 - 1;
        if (r45 <= 0) goto L43;
        int r46 = readTypeAnnotationTarget(r22, r64);
        r64 = readElementValues(r27.visitTypeAnnotation(r22.currentTypeAnnotationTarget, r22.currentTypeAnnotationTargetPath, readUTF8(r46, r5), true), r46 + 2, true, r5);
        r45 = r72;
    L43:
        if (r19 == 0) goto L48;
        int r47 = readUnsignedShort(r19);
        int r65 = r19 + 2;
    L45:
        int r73 = r47 - 1;
        if (r47 <= 0) goto L48;
        int r48 = readTypeAnnotationTarget(r22, r65);
        r65 = readElementValues(r27.visitTypeAnnotation(r22.currentTypeAnnotationTarget, r22.currentTypeAnnotationTargetPath, readUTF8(r48, r5), false), r48 + 2, true, r5);
        r47 = r73;
    L48:
        Attribute r49 = r122;
    L49:
        if (r49 == null) goto L51;
        Attribute r0 = r49.nextAttribute;
        r49.nextAttribute = null;
        r27.visitAttribute(r49);
        r49 = r0;
        goto L49
    L51:
        r27.visitEnd();
        return r1;
    }

    private int readStackMapFrame(int r12, boolean r13, boolean r14, Context r15) {
        char[] r4 = r15.charBuffer;
        Label[] r5 = r15.currentMethodLabels;
        if (r13 == false) goto L5;
        int r1 = r12 + 1;
        int r122 = this.classFileBuffer[r12] & UByte.MAX_VALUE;
    L6:
        r15.currentFrameLocalCountDelta = 0;
        if (r122 >= 64) goto L10;
        r15.currentFrameType = 3;
        r15.currentFrameStackCount = 0;
        ClassReader r0 = this;
    L39:
        int r11 = r15.currentFrameOffset + (r122 + 1);
        r15.currentFrameOffset = r11;
        r0.createLabel(r11, r5);
        return r1;
    L10:
        if (r122 >= 128) goto L13;
        r122 = r122 - 64;
        r0 = this;
        r1 = r0.readVerificationTypeInfo(r1, r15.currentFrameStackTypes, 0, r4, r5);
        r15.currentFrameType = 4;
        r15.currentFrameStackCount = 1;
        goto L39
    L13:
        if (r122 < 247) goto L41;
        int r8 = readUnsignedShort(r1);
        int r9 = r1;
        r1 = r9 + 2;
        if (r122 != 247) goto L19;
        r0 = this;
        r1 = r0.readVerificationTypeInfo(r1, r15.currentFrameStackTypes, 0, r4, r5);
        r15.currentFrameType = 4;
        r15.currentFrameStackCount = 1;
    L17:
        r122 = r8;
        goto L39
    L19:
        if (r122 < 248) goto L22;
        if (r122 >= 251) goto L22;
        r15.currentFrameType = 2;
        int r123 = 251 - r122;
        r15.currentFrameLocalCountDelta = r123;
        r15.currentFrameLocalCount -= r123;
        r15.currentFrameStackCount = 0;
    L24:
        r0 = this;
    L22:
        if (r122 != 251) goto L25;
        r15.currentFrameType = 3;
        r15.currentFrameStackCount = 0;
        goto L24
    L25:
        if (r122 >= 255) goto L33;
        if (r14 == false) goto L28;
        int r142 = r15.currentFrameLocalCount;
    L29:
        int r124 = r122 - 251;
        int r3 = r142;
        int r143 = r124;
    L30:
        if (r143 <= 0) goto L32;
        r1 = readVerificationTypeInfo(r1, r15.currentFrameLocalTypes, r3, r4, r5);
        r143 = r143 - 1;
        r3 = r3 + 1;
        goto L30
    L32:
        r0 = this;
        r15.currentFrameType = 1;
        r15.currentFrameLocalCountDelta = r124;
        r15.currentFrameLocalCount += r124;
        r15.currentFrameStackCount = 0;
        goto L17
    L28:
        r142 = 0;
        goto L29
    L33:
        r0 = this;
        int r112 = r0.readUnsignedShort(r1);
        int r16 = r9 + 4;
        r15.currentFrameType = 0;
        r15.currentFrameLocalCountDelta = r112;
        r15.currentFrameLocalCount = r112;
        int r32 = 0;
    L34:
        if (r32 >= r112) goto L36;
        r16 = r0.readVerificationTypeInfo(r16, r15.currentFrameLocalTypes, r32, r4, r5);
        r32 = r32 + 1;
        goto L34
    L36:
        int r113 = r0.readUnsignedShort(r16);
        r1 = r16 + 2;
        r15.currentFrameStackCount = r113;
        int r33 = 0;
    L37:
        if (r33 >= r113) goto L17;
        r1 = r0.readVerificationTypeInfo(r1, r15.currentFrameStackTypes, r33, r4, r5);
        r33 = r33 + 1;
        goto L37
    L41:
        Segment$$ExternalSyntheticBUOutline0.m();
        return 0;
    L5:
        r15.currentFrameOffset = -1;
        r1 = r12;
        r122 = 255;
        goto L6
    }

    private static byte[] readStream(InputStream r7, boolean r8) {
        if (r7 == null) goto L30;
        int r0 = computeBufferSize(r7);
        ByteArrayOutputStream r1 = new ByteArrayOutputStream();     // Catch: Throwable -> L18
        byte[] r2 = new byte[r0];     // Catch: Throwable -> L9
        int r4 = 0;
    L6:
        int r5 = r7.read(r2, 0, r0);     // Catch: Throwable -> L9
        if (r5 == (-1)) goto L11;
        r1.write(r2, 0, r5);     // Catch: Throwable -> L9
        r4 = r4 + 1;     // Catch: Throwable -> L9
        goto L6
    L11:
        r1.flush();     // Catch: Throwable -> L9
        if (r4 != 1) goto L20;
        r1.close();     // Catch: Throwable -> L18
        if (r8 == false) goto L17;
        r7.close();
    L17:
        return r2;
    L20:
        byte[] r02 = r1.toByteArray();     // Catch: Throwable -> L9
        r1.close();     // Catch: Throwable -> L18
        if (r8 == false) goto L24;
        r7.close();
    L24:
        return r02;
    L9:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L32
    L26:
        throw th;     // Catch: Throwable -> L18
    L18:
        th = move-exception;
        if (r8 == false) goto L29;
        r7.close();
    L29:
        throw th;
    L30:
        Model$$ExternalSyntheticBUOutline0.m("Class not found");
        return null;
    }

    private String readStringish(int r2, char[] r3) {
        return readUTF8(this.cpInfoOffsets[readUnsignedShort(r2)], r3);
    }

    private int readTypeAnnotationTarget(Context r10, int r11) {
        int r0 = readInt(r11);
        int r1 = r0 >>> 24;
        if (r1 == 0) goto L19;
        if (r1 == 1) goto L19;
        switch(r1) {
            case 16: goto L18;
            case 17: goto L18;
            case 18: goto L18;
            case 19: goto L17;
            case 20: goto L17;
            case 21: goto L17;
            case 22: goto L19;
            case 23: goto L18;
            default: goto L7;
        };
    L7:
        int r4 = 0;
        switch(r1) {
            case 64: goto L14;
            case 65: goto L14;
            case 66: goto L18;
            case 67: goto L12;
            case 68: goto L12;
            case 69: goto L12;
            case 70: goto L12;
            case 71: goto L11;
            case 72: goto L11;
            case 73: goto L11;
            case 74: goto L11;
            case 75: goto L11;
            default: goto L9;
        };
    L9:
        Segment$$ExternalSyntheticBUOutline0.m();
        return 0;
    L11:
        int r02 = r0 & (-16776961);
        int r112 = r11 + 4;
    L20:
        r10.currentTypeAnnotationTarget = r02;
        int r03 = readByte(r112);
        if (r03 != 0) goto L23;
        TypePath r9 = null;
    L24:
        r10.currentTypeAnnotationTargetPath = r9;
        return (r112 + 1) + (r03 * 2);
    L23:
        r9 = new TypePath(this.classFileBuffer, r112);
        goto L24
    L12:
        r02 = r0 & (-16777216);
    L13:
        r112 = r11 + 3;
        goto L20
    L14:
        r02 = r0 & (-16777216);
        int r12 = readUnsignedShort(r11 + 1);
        r112 = r11 + 3;
        r10.currentLocalVariableAnnotationRangeStarts = new Label[r12];
        r10.currentLocalVariableAnnotationRangeEnds = new Label[r12];
        r10.currentLocalVariableAnnotationRangeIndices = new int[r12];
    L15:
        if (r4 >= r12) goto L20;
        int r3 = readUnsignedShort(r112);
        int r5 = readUnsignedShort(r112 + 2);
        int r6 = readUnsignedShort(r112 + 4);
        r112 = r112 + 6;
        r10.currentLocalVariableAnnotationRangeStarts[r4] = createLabel(r3, r10.currentMethodLabels);
        r10.currentLocalVariableAnnotationRangeEnds[r4] = createLabel(r3 + r5, r10.currentMethodLabels);
        r10.currentLocalVariableAnnotationRangeIndices[r4] = r6;
        r4 = r4 + 1;
        goto L15
    L17:
        r02 = r0 & (-16777216);
        r112 = r11 + 1;
    L18:
        r02 = r0 & (-256);
    L19:
        r02 = r0 & Opcodes.V_PREVIEW;
        r112 = r11 + 2;
        goto L20
    }

    private int[] readTypeAnnotations(MethodVisitor r12, Context r13, int r14, boolean r15) {
        char[] r0 = r13.charBuffer;
        int r1 = readUnsignedShort(r14);
        int[] r2 = new int[r1];
        int r142 = r14 + 2;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L24;
        r2[r3] = r142;
        int r4 = readInt(r142);
        int r5 = r4 >>> 24;
        TypePath r7 = null;
        if (r5 == 23) goto L15;
        switch(r5) {
            case 16: goto L15;
            case 17: goto L15;
            case 18: goto L15;
            default: goto L7;
        };
    L7:
        switch(r5) {
            case 64: goto L11;
            case 65: goto L11;
            case 66: goto L15;
            case 67: goto L15;
            case 68: goto L15;
            case 69: goto L15;
            case 70: goto L15;
            case 71: goto L10;
            case 72: goto L10;
            case 73: goto L10;
            case 74: goto L10;
            case 75: goto L10;
            default: goto L8;
        };
    L10:
        int r143 = r142 + 4;
    L16:
        int r6 = readByte(r143);
        if (r5 != 66) goto L22;
        if (r6 == 0) goto L21;
        r7 = new TypePath(this.classFileBuffer, r143);
    L21:
        int r144 = r143 + ((r6 * 2) + 1);
        r142 = readElementValues(r12.visitTryCatchAnnotation(r4 & (-256), r7, readUTF8(r144, r0), r15), r144 + 2, true, r0);
    L23:
        r3 = r3 + 1;
        goto L3
    L22:
        r142 = readElementValues(null, r143 + ((r6 * 2) + 3), true, r0);
        goto L23
    L11:
        int r62 = readUnsignedShort(r142 + 1);
        r143 = r142 + 3;
    L12:
        int r8 = r62 - 1;
        if (r62 <= 0) goto L16;
        int r63 = readUnsignedShort(r143);
        int r9 = readUnsignedShort(r143 + 2);
        r143 = r143 + 6;
        createLabel(r63, r13.currentMethodLabels);
        createLabel(r63 + r9, r13.currentMethodLabels);
        r62 = r8;
        goto L12
    L8:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    L15:
        r143 = r142 + 3;
        goto L16
    L24:
        return r2;
    }

    private String readUtf(int r7, int r8, char[] r9) {
        int r82 = r8 + r7;
        byte[] r6 = this.classFileBuffer;
        int r1 = 0;
    L3:
        if (r7 >= r82) goto L13;
        int r2 = r7 + 1;
        byte r3 = r6[r7];
        if ((r3 & ByteCompanionObject.MIN_VALUE) == 0) goto L6;
        if ((r3 & 224) != 192) goto L11;
        int r4 = r1 + 1;
        r7 = r7 + 2;
        r9[r1] = (char) (((r3 & 31) << 6) + (r6[r2] & 63));
    L10:
        r1 = r4;
        goto L3
    L11:
        r4 = r1 + 1;
        int r5 = r7 + 2;
        r7 = r7 + 3;
        r9[r1] = (char) ((((r3 & 15) << 12) + ((r6[r2] & 63) << 6)) + (r6[r5] & 63));
        goto L10
    L6:
        r9[r1] = (char) (r3 & ByteCompanionObject.MAX_VALUE);
        r1 = r1 + 1;
        r7 = r2;
        goto L3
    L13:
        return new String(r9, 0, r1);
    }

    private int readVerificationTypeInfo(int r3, Object[] r4, int r5, char[] r6, Label[] r7) {
        int r1 = r3 + 1;
        switch((this.classFileBuffer[r3] & UByte.MAX_VALUE)) {
            case 0: goto L22;
            case 1: goto L20;
            case 2: goto L18;
            case 3: goto L16;
            case 4: goto L14;
            case 5: goto L12;
            case 6: goto L10;
            case 7: goto L9;
            case 8: goto L6;
            default: goto L4;
        };
    L4:
        Segment$$ExternalSyntheticBUOutline0.m();
        return 0;
    L6:
        r4[r5] = createLabel(readUnsignedShort(r1), r7);
    L8:
        return r3 + 3;
    L9:
        r4[r5] = readClass(r1, r6);
        goto L8
    L10:
        r4[r5] = Opcodes.UNINITIALIZED_THIS;
        return r1;
    L12:
        r4[r5] = Opcodes.NULL;
        return r1;
    L14:
        r4[r5] = Opcodes.LONG;
        return r1;
    L16:
        r4[r5] = Opcodes.DOUBLE;
        return r1;
    L18:
        r4[r5] = Opcodes.FLOAT;
        return r1;
    L20:
        r4[r5] = Opcodes.INTEGER;
        return r1;
    L22:
        r4[r5] = Opcodes.TOP;
        return r1;
    }

    public void accept(ClassVisitor r2, int r3) {
        accept(r2, new Attribute[0], r3);
    }

    public void accept(ClassVisitor r33, Attribute[] r34, int r35) {
        Context r9 = new Context();
        r9.attributePrototypes = r34;
        r9.parsingOptions = r35;
        char[] r5 = new char[this.maxStringLength];
        r9.charBuffer = r5;
        int r2 = this.header;
        int r3 = readUnsignedShort(r2);
        String r10 = readClass(r2 + 2, r5);
        String r11 = readClass(r2 + 4, r5);
        int r4 = readUnsignedShort(r2 + 6);
        String[] r12 = new String[r4];
        int r14 = r2 + 8;
        int r22 = 0;
    L3:
        if (r22 >= r4) goto L5;
        r12[r22] = readClass(r14, r5);
        r14 = r14 + 2;
        r22 = r22 + 1;
        goto L3
    L5:
        int r23 = getFirstAttributeOffset();
        int r16 = readUnsignedShort(r23 - 2);
        String r42 = null;
        String r6 = null;
        String r7 = null;
        int r13 = 0;
        int r15 = 0;
        String r17 = null;
        int r18 = 0;
        int r19 = 0;
        String r20 = null;
        int r21 = 0;
        int r222 = 0;
        int r232 = 0;
        int r24 = 0;
        Attribute r25 = null;
        int r26 = 0;
        int r27 = 0;
        int r28 = 0;
    L6:
        if (r16 <= 0) goto L75;
        int r1 = r23;
        String r29 = readUTF8(r1, r5);
        int r110 = readInt(r1 + 2);
        String r30 = r42;
        int r43 = r1 + 6;
        String r292 = r6;
        if (AttSourceFile.ATTRIBUTE_NAME.equals(r29) == false) goto L11;
        String r8 = readUTF8(r43, r5);
        int r210 = r3;
        int r32 = r43;
        String r302 = r10;
        r6 = r292;
        int r44 = r110;
        Context r293 = r9;
    L74:
        int r111 = r32 + r44;
        r16 = r16 - 1;
        r3 = r210;
        r42 = r8;
        r9 = r293;
        r10 = r302;
        r23 = r111;
        goto L6
    L11:
        if (AttInnerClasses.ATTRIBUTE_NAME.equals(r29) == false) goto L16;
        r210 = r3;
        r32 = r43;
        r27 = r32;
    L13:
        r6 = r292;
        r8 = r30;
        r44 = r110;
        r293 = r9;
    L14:
        r302 = r10;
        goto L74
    L16:
        if (AttEnclosingMethod.ATTRIBUTE_NAME.equals(r29) == false) goto L19;
        r210 = r3;
        r32 = r43;
        r15 = r32;
        goto L13
    L19:
        if ("NestHost".equals(r29) == false) goto L24;
        r7 = readClass(r43, r5);
    L21:
        r210 = r3;
    L22:
        r32 = r43;
        goto L13
    L24:
        if ("NestMembers".equals(r29) == false) goto L27;
        r210 = r3;
        r32 = r43;
        r24 = r32;
        goto L13
    L27:
        if ("PermittedSubclasses".equals(r29) == false) goto L30;
        r210 = r3;
        r32 = r43;
        r26 = r32;
        goto L13
    L30:
        if (AttSignature.ATTRIBUTE_NAME.equals(r29) == false) goto L33;
        r17 = readUTF8(r43, r5);
        goto L21
    L33:
        if (AttRuntimeVisibleAnnotations.ATTRIBUTE_NAME.equals(r29) == false) goto L36;
        r210 = r3;
        r32 = r43;
        r13 = r32;
        goto L13
    L36:
        if ("RuntimeVisibleTypeAnnotations".equals(r29) == false) goto L39;
        r210 = r3;
        r32 = r43;
        r222 = r32;
        goto L13
    L39:
        if (AttDeprecated.ATTRIBUTE_NAME.equals(r29) == false) goto L42;
        r210 = 131072 | r3;
        goto L22
    L42:
        if (AttSynthetic.ATTRIBUTE_NAME.equals(r29) == false) goto L45;
        r210 = r3 | 4096;
        goto L22
    L45:
        if (AttSourceDebugExtension.ATTRIBUTE_NAME.equals(r29) == false) goto L52;
        if (r110 > (this.classFileBuffer.length - r43)) goto L49;
        r6 = readUtf(r43, r110, new char[r110]);
        r210 = r3;
        r32 = r43;
        r293 = r9;
        r8 = r30;
        r44 = r110;
        goto L14
    L49:
        Segment$$ExternalSyntheticBUOutline0.m();
        return;
    L52:
        if (AttRuntimeInvisibleAnnotations.ATTRIBUTE_NAME.equals(r29) == false) goto L55;
        r210 = r3;
        r32 = r43;
        r21 = r32;
        goto L13
    L55:
        if ("RuntimeInvisibleTypeAnnotations".equals(r29) == false) goto L58;
        r210 = r3;
        r32 = r43;
        r232 = r32;
        goto L13
    L58:
        if ("Record".equals(r29) == false) goto L61;
        r210 = 65536 | r3;
        r32 = r43;
        r28 = r32;
        goto L13
    L61:
        if ("Module".equals(r29) == false) goto L64;
        r210 = r3;
        r32 = r43;
        r18 = r32;
        goto L13
    L64:
        if ("ModuleMainClass".equals(r29) == false) goto L67;
        r20 = readClass(r43, r5);
        goto L21
    L67:
        if ("ModulePackages".equals(r29) == false) goto L70;
        r210 = r3;
        r32 = r43;
        r19 = r32;
        goto L13
    L70:
        if (AttBootstrapMethods.ATTRIBUTE_NAME.equals(r29) == true) goto L73;
        r293 = r9;
        String r92 = r292;
        r8 = r30;
        r302 = r10;
        String r102 = r7;
        int r31 = r3;
        r32 = r43;
        r44 = r110;
        Attribute r211 = readAttribute(r34, r29, r32, r44, r5, -1, null);
        r211.nextAttribute = r25;
        r25 = r211;
    L72:
        r6 = r92;
        r7 = r102;
        r210 = r31;
        goto L74
    L73:
        r293 = r9;
        r92 = r292;
        r31 = r3;
        r32 = r43;
        r8 = r30;
        r44 = r110;
        r302 = r10;
        r102 = r7;
        goto L72
    L75:
        String r82 = r42;
        Context r294 = r9;
        String r303 = r10;
        String r93 = r6;
        String r103 = r7;
        Attribute r72 = r25;
        r33.visit(readInt(this.cpInfoOffsets[1] - 7), r3, r303, r17, r11, r12);
        if ((r35 & 2) != 0) goto L81;
        if (r82 != null) goto L79;
        if (r93 == null) goto L81;
    L79:
        r33.visitSource(r82, r93);
    L81:
        if (r18 == 0) goto L83;
        Context r212 = r294;
        ClassReader r0 = this;
        r0.readModuleAttributes(r33, r212, r18, r19, r20);
    L84:
        if (r103 == null) goto L86;
        r33.visitNestHost(r103);
    L86:
        if (r15 == 0) goto L95;
        String r36 = r0.readClass(r15, r5);
        int r45 = r0.readUnsignedShort(r15 + 2);
        if (r45 != 0) goto L90;
        String r52 = null;
    L91:
        if (r45 != 0) goto L93;
        String r46 = null;
    L94:
        r33.visitOuterClass(r36, r52, r46);
        goto L95
    L93:
        r46 = r0.readUTF8(r0.cpInfoOffsets[r45] + 2, r5);
        goto L94
    L90:
        r52 = r0.readUTF8(r0.cpInfoOffsets[r45], r5);
    L95:
        if (r13 == 0) goto L100;
        int r37 = r0.readUnsignedShort(r13);
        int r132 = r13 + 2;
    L97:
        int r47 = r37 - 1;
        if (r37 <= 0) goto L100;
        r132 = r0.readElementValues(r33.visitAnnotation(r0.readUTF8(r132, r5), true), r132 + 2, true, r5);
        r37 = r47;
    L100:
        int r133 = r21;
        if (r133 == 0) goto L106;
        int r38 = r0.readUnsignedShort(r133);
        int r48 = r133 + 2;
    L103:
        int r53 = r38 - 1;
        if (r38 <= 0) goto L106;
        r48 = r0.readElementValues(r33.visitAnnotation(r0.readUTF8(r48, r5), false), r48 + 2, true, r5);
        r38 = r53;
    L106:
        int r134 = r222;
        if (r134 == 0) goto L112;
        int r39 = r0.readUnsignedShort(r134);
        int r49 = r134 + 2;
    L109:
        int r54 = r39 - 1;
        if (r39 <= 0) goto L112;
        int r310 = r0.readTypeAnnotationTarget(r212, r49);
        r49 = r0.readElementValues(r33.visitTypeAnnotation(r212.currentTypeAnnotationTarget, r212.currentTypeAnnotationTargetPath, r0.readUTF8(r310, r5), true), r310 + 2, true, r5);
        r39 = r54;
    L112:
        int r135 = r232;
        if (r135 == 0) goto L118;
        int r311 = r0.readUnsignedShort(r135);
        int r410 = r135 + 2;
    L115:
        int r55 = r311 - 1;
        if (r311 <= 0) goto L118;
        int r312 = r0.readTypeAnnotationTarget(r212, r410);
        r410 = r0.readElementValues(r33.visitTypeAnnotation(r212.currentTypeAnnotationTarget, r212.currentTypeAnnotationTargetPath, r0.readUTF8(r312, r5), false), r312 + 2, true, r5);
        r311 = r55;
    L118:
        if (r72 == null) goto L120;
        Attribute r313 = r72.nextAttribute;
        r72.nextAttribute = null;
        r33.visitAttribute(r72);
        r72 = r313;
        goto L118
    L120:
        int r136 = r24;
        if (r136 == 0) goto L126;
        int r314 = r0.readUnsignedShort(r136);
        int r411 = r136 + 2;
    L123:
        int r56 = r314 - 1;
        if (r314 <= 0) goto L126;
        r33.visitNestMember(r0.readClass(r411, r5));
        r411 = r411 + 2;
        r314 = r56;
    L126:
        int r137 = r26;
        if (r137 == 0) goto L132;
        int r315 = r0.readUnsignedShort(r137);
        int r412 = r137 + 2;
    L129:
        int r57 = r315 - 1;
        if (r315 <= 0) goto L132;
        r33.visitPermittedSubclass(r0.readClass(r412, r5));
        r412 = r412 + 2;
        r315 = r57;
    L132:
        int r138 = r27;
        if (r138 == 0) goto L138;
        int r316 = r0.readUnsignedShort(r138);
        int r413 = r138 + 2;
    L135:
        int r58 = r316 - 1;
        if (r316 <= 0) goto L138;
        r33.visitInnerClass(r0.readClass(r413, r5), r0.readClass(r413 + 2, r5), r0.readUTF8(r413 + 4, r5), r0.readUnsignedShort(r413 + 6));
        r413 = r413 + 8;
        r316 = r58;
    L138:
        int r139 = r28;
        if (r139 == 0) goto L144;
        int r317 = r0.readUnsignedShort(r139);
        int r414 = r139 + 2;
    L141:
        int r59 = r317 - 1;
        if (r317 <= 0) goto L144;
        r414 = r0.readRecordComponent(r33, r212, r414);
        r317 = r59;
    L144:
        int r318 = r0.readUnsignedShort(r14);
        int r142 = r14 + 2;
    L145:
        int r415 = r318 - 1;
        if (r318 <= 0) goto L148;
        r142 = r0.readField(r33, r212, r142);
        r318 = r415;
        goto L145
    L148:
        int r319 = r0.readUnsignedShort(r142);
        int r143 = r142 + 2;
    L149:
        int r416 = r319 - 1;
        if (r319 <= 0) goto L152;
        r143 = r0.readMethod(r33, r212, r143);
        r319 = r416;
        goto L149
    L152:
        r33.visitEnd();
        return;
    L83:
        r212 = r294;
        r0 = this;
        goto L84
    }

    public int getAccess() {
        return readUnsignedShort(this.header);
    }

    public String getClassName() {
        return readClass(this.header + 2, new char[this.maxStringLength]);
    }

    public final int getFirstAttributeOffset() {
        int r0 = this.header;
        int r1 = (r0 + 8) + (readUnsignedShort(r0 + 6) * 2);
        int r02 = readUnsignedShort(r1);
        int r12 = r1 + 2;
    L3:
        int r2 = r02 - 1;
        if (r02 <= 0) goto L10;
        int r03 = readUnsignedShort(r12 + 6);
        r12 = r12 + 8;
    L6:
        int r3 = r03 - 1;
        if (r03 <= 0) goto L9;
        r12 = r12 + (readInt(r12 + 2) + 6);
        r03 = r3;
        goto L6
    L9:
        r02 = r2;
        goto L3
    L10:
        int r04 = readUnsignedShort(r12);
        int r13 = r12 + 2;
    L11:
        int r22 = r04 - 1;
        if (r04 <= 0) goto L19;
        int r05 = readUnsignedShort(r13 + 6);
        r13 = r13 + 8;
    L14:
        int r32 = r05 - 1;
        if (r05 <= 0) goto L17;
        r13 = r13 + (readInt(r13 + 2) + 6);
        r05 = r32;
        goto L14
    L17:
        r04 = r22;
        goto L11
    L19:
        return r13 + 2;
    }

    public String[] getInterfaces() {
        int r0 = this.header + 6;
        int r1 = readUnsignedShort(r0);
        String[] r2 = new String[r1];
        if (r1 <= 0) goto L7;
        char[] r3 = new char[this.maxStringLength];
        int r4 = 0;
    L5:
        if (r4 >= r1) goto L7;
        r0 = r0 + 2;
        r2[r4] = readClass(r0, r3);
        r4 = r4 + 1;
    L7:
        return r2;
    }

    public int getItem(int r1) {
        return this.cpInfoOffsets[r1];
    }

    public int getItemCount() {
        return this.cpInfoOffsets.length;
    }

    public int getMaxStringLength() {
        return this.maxStringLength;
    }

    public String getSuperName() {
        return readClass(this.header + 4, new char[this.maxStringLength]);
    }

    public int readByte(int r1) {
        return this.classFileBuffer[r1] & UByte.MAX_VALUE;
    }

    public void readBytecodeInstructionOffset(int r1) {
    }

    public String readClass(int r1, char[] r2) {
        return readStringish(r1, r2);
    }

    public Object readConst(int r7, char[] r8) {
        int r0 = this.cpInfoOffsets[r7];
        byte r1 = this.classFileBuffer[r0 - 1];
        switch(r1) {
            case 3: goto L28;
            case 4: goto L26;
            case 5: goto L24;
            case 6: goto L22;
            case 7: goto L20;
            case 8: goto L18;
            default: goto L4;
        };
    L4:
        switch(r1) {
            case 15: goto L11;
            case 16: goto L10;
            case 17: goto L8;
            default: goto L5;
        };
    L5:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    L11:
        int r12 = readByte(r0);
        int r72 = this.cpInfoOffsets[readUnsignedShort(r0 + 1)];
        int r02 = this.cpInfoOffsets[readUnsignedShort(r72 + 2)];
        String r2 = readClass(r72, r8);
        String r3 = readUTF8(r02, r8);
        String r82 = readUTF8(r02 + 2, r8);
        if (this.classFileBuffer[r72 - 1] != 11) goto L14;
        boolean r5 = true;
    L16:
        return new Handle(r12, r2, r3, r82, r5);
    L14:
        r5 = false;
        goto L16
    L8:
        return readConstantDynamic(r7, r8);
    L10:
        return Type.getMethodType(readUTF8(r0, r8));
    L18:
        return readUTF8(r0, r8);
    L20:
        return Type.getObjectType(readUTF8(r0, r8));
    L22:
        return Double.valueOf(Double.longBitsToDouble(readLong(r0)));
    L24:
        return Long.valueOf(readLong(r0));
    L26:
        return Float.valueOf(Float.intBitsToFloat(readInt(r0)));
    L28:
        return Integer.valueOf(readInt(r0));
    }

    public int readInt(int r3) {
        byte[] r2 = this.classFileBuffer;
        int r0 = (((r2[r3] & UByte.MAX_VALUE) << 24) | ((r2[r3 + 1] & UByte.MAX_VALUE) << 16)) | ((r2[r3 + 2] & UByte.MAX_VALUE) << 8);
        return (r2[r3 + 3] & UByte.MAX_VALUE) | r0;
    }

    public Label readLabel(int r1, Label[] r2) {
        if (r2[r1] != null) goto L6;
        r2[r1] = new Label();
    L6:
        return r2[r1];
    }

    public long readLong(int r5) {
        return (readInt(r5 + 4) & 4294967295L) | (readInt(r5) << 32);
    }

    public String readModule(int r1, char[] r2) {
        return readStringish(r1, r2);
    }

    public String readPackage(int r1, char[] r2) {
        return readStringish(r1, r2);
    }

    public short readShort(int r2) {
        byte[] r1 = this.classFileBuffer;
        int r0 = (r1[r2] & UByte.MAX_VALUE) << 8;
        return (short) ((r1[r2 + 1] & UByte.MAX_VALUE) | r0);
    }

    public String readUTF8(int r2, char[] r3) {
        int r0 = readUnsignedShort(r2);
        if (r2 == 0) goto L8;
        if (r0 != 0) goto L7;
        return null;
    L7:
        return readUtf(r0, r3);
    L8:
        return null;
    }

    public int readUnsignedShort(int r2) {
        byte[] r1 = this.classFileBuffer;
        int r0 = (r1[r2] & UByte.MAX_VALUE) << 8;
        return (r1[r2 + 1] & UByte.MAX_VALUE) | r0;
    }

    public final String readUtf(int r4, char[] r5) {
        String[] r0 = this.constantUtf8Values;
        String r1 = r0[r4];
        if (r1 == null) goto L5;
        return r1;
    L5:
        int r12 = this.cpInfoOffsets[r4];
        String r3 = readUtf(r12 + 2, readUnsignedShort(r12), r5);
        r0[r4] = r3;
        return r3;
    }
}
