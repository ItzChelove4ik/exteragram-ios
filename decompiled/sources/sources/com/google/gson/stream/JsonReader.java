package com.google.gson.stream;

import androidx.camera.core.UseCase$$ExternalSyntheticBUOutline0;
import com.google.gson.Strictness;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.TroubleshootingGuide;
import com.google.gson.internal.bind.JsonTreeReader;
import java.io.Closeable;
import java.io.EOFException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Objects;
import kotlin.text.Typography;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public class JsonReader implements Closeable {
    private final char[] buffer;
    private final Reader in;
    private int limit;
    private int lineNumber;
    private int lineStart;
    private int nestingLimit;
    private int[] pathIndices;
    private String[] pathNames;
    int peeked;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private int pos;
    private int[] stack;
    private int stackSize;
    private Strictness strictness;

    /* renamed from: com.google.gson.stream.JsonReader$1, reason: invalid class name */
    public class AnonymousClass1 extends JsonReaderInternalAccess {
        public AnonymousClass1() {
        }

        @Override // com.google.gson.internal.JsonReaderInternalAccess
        public void promoteNameToValue(JsonReader r2) {
            if ((r2 instanceof JsonTreeReader) == false) goto L6;
            ((JsonTreeReader) r2).promoteNameToValue();
            return;
        L6:
            int r1 = r2.peeked;
            if (r1 != 0) goto L10;
            r1 = r2.doPeek();
        L10:
            if (r1 != 13) goto L14;
            r2.peeked = 9;
            return;
        L14:
            if (r1 != 12) goto L18;
            r2.peeked = 8;
            return;
        L18:
            if (r1 != 14) goto L22;
            r2.peeked = 10;
            return;
        L22:
            throw JsonReader.access$000(r2, "a name");
        }
    }

    static {
        JsonReaderInternalAccess.INSTANCE = new AnonymousClass1();
    }

    public JsonReader(Reader r5) {
        this.strictness = Strictness.LEGACY_STRICT;
        this.nestingLimit = 255;
        this.buffer = new char[1024];
        this.pos = 0;
        this.limit = 0;
        this.lineNumber = 0;
        this.lineStart = 0;
        this.peeked = 0;
        int[] r2 = new int[32];
        this.stack = r2;
        this.stackSize = 1;
        r2[0] = 6;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
        Objects.requireNonNull(r5, "in == null");
        this.in = r5;
    }

    public static /* synthetic */ IllegalStateException access$000(JsonReader r0, String r1) {
        return r0.unexpectedTokenError(r1);
    }

    private void checkLenient() {
        if (this.strictness != Strictness.LENIENT) goto L6;
        return;
    L6:
        throw syntaxError("Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON");
    }

    private void consumeNonExecutePrefix() {
        nextNonWhitespace(true);
        int r0 = this.pos;
        this.pos = r0 - 1;
        if ((r0 + 4) > this.limit) goto L5;
    L7:
        int r02 = this.pos;
        char[] r1 = this.buffer;
        if (r1[r02] == ')') goto L10;
        return;
    L10:
        if (r1[r02 + 1] == ']') goto L12;
        return;
    L12:
        if (r1[r02 + 2] == '}') goto L14;
        return;
    L14:
        if (r1[r02 + 3] == '\'') goto L16;
        return;
    L16:
        if (r1[r02 + 4] != '\n') goto L25;
        this.pos = r02 + 5;
        return;
    L25:
        return;
    L5:
        if (fillBuffer(5) == true) goto L7;
    }

    private boolean fillBuffer(int r8) {
        char[] r0 = this.buffer;
        int r1 = this.lineStart;
        int r2 = this.pos;
        this.lineStart = r1 - r2;
        int r12 = this.limit;
        if (r12 == r2) goto L5;
        int r13 = r12 - r2;
        this.limit = r13;
        System.arraycopy(r0, r2, r0, 0, r13);
    L6:
        this.pos = 0;
    L7:
        Reader r14 = this.in;
        int r22 = this.limit;
        int r15 = r14.read(r0, r22, r0.length - r22);
        if (r15 == (-1)) goto L19;
        int r23 = this.limit + r15;
        this.limit = r23;
        if (this.lineNumber != 0) goto L17;
        int r16 = this.lineStart;
        if (r16 != 0) goto L17;
        if (r23 <= 0) goto L17;
        if (r0[0] != 65279) goto L17;
        this.pos++;
        this.lineStart = r16 + 1;
        r8 = r8 + 1;
    L17:
        if (r23 < r8) goto L7;
        return true;
    L19:
        return false;
    L5:
        this.limit = 0;
        goto L6
    }

    private String getPath(boolean r5) {
        StringBuilder r0 = new StringBuilder("$");
        int r1 = 0;
    L3:
        int r2 = this.stackSize;
        if (r1 >= r2) goto L21;
        int r3 = this.stack[r1];
        switch(r3) {
            case 1: goto L12;
            case 2: goto L12;
            case 3: goto L9;
            case 4: goto L9;
            case 5: goto L9;
            case 6: goto L19;
            case 7: goto L19;
            case 8: goto L19;
            default: goto L7;
        };
    L9:
        r0.append('.');
        String r22 = this.pathNames[r1];
        if (r22 == null) goto L19;
        r0.append(r22);
        goto L19
    L12:
        int r32 = this.pathIndices[r1];
        if (r5 == false) goto L18;
        if (r32 <= 0) goto L18;
        if (r1 != (r2 - 1)) goto L18;
        r32 = r32 - 1;
    L18:
        r0.append('[');
        r0.append(r32);
        r0.append(']');
        goto L19
    L7:
        UseCase$$ExternalSyntheticBUOutline0.m("Unknown scope value: ", r3);
        return null;
    L19:
        r1 = r1 + 1;
        goto L3
    L21:
        return r0.toString();
    }

    private boolean isLiteral(char r2) {
        if (r2 != '\t') goto L5;
        return false;
    L5:
        if (r2 != '\n') goto L7;
        return false;
    L7:
        if (r2 != '\f') goto L9;
        return false;
    L9:
        if (r2 != '\r') goto L11;
        return false;
    L11:
        if (r2 != ' ') goto L13;
        return false;
    L13:
        if (r2 != '#') goto L15;
    L31:
        checkLenient();
        return false;
    L15:
        if (r2 != ',') goto L17;
        return false;
    L17:
        if (r2 == '/') goto L31;
        if (r2 == '=') goto L31;
        if (r2 != '{') goto L23;
        return false;
    L23:
        if (r2 != '}') goto L25;
        return false;
    L25:
        if (r2 != ':') goto L27;
        return false;
    L27:
        if (r2 == ';') goto L31;
        switch(r2) {
            case 91: goto L43;
            case 92: goto L31;
            case 93: goto L43;
            default: goto L29;
        };
    L29:
        return true;
    L43:
        return false;
    }

    private int nextNonWhitespace(boolean r9) {
        char[] r0 = this.buffer;
        int r1 = this.pos;
        int r2 = this.limit;
    L4:
        if (r1 != r2) goto L13;
        this.pos = r1;
        if (fillBuffer(1) == false) goto L7;
        r1 = this.pos;
        r2 = this.limit;
        goto L13
    L7:
        if (r9 == true) goto L11;
        return -1;
    L11:
        throw new EOFException("End of input" + locationString());
    L13:
        int r4 = r1 + 1;
        char r5 = r0[r1];
        if (r5 == '\n') goto L15;
        if (r5 == ' ') goto L45;
        if (r5 == '\r') goto L45;
        if (r5 == '\t') goto L45;
        if (r5 == '/') goto L25;
        if (r5 != '#') goto L43;
        this.pos = r4;
        checkLenient();
        skipToEndOfLine();
        r1 = this.pos;
        r2 = this.limit;
        goto L4
    L43:
        this.pos = r4;
        return r5;
    L25:
        this.pos = r4;
        if (r4 != r2) goto L30;
        this.pos = r1;
        boolean r12 = fillBuffer(2);
        this.pos++;
        if (r12 == true) goto L30;
    L33:
        return r5;
    L30:
        checkLenient();
        int r13 = this.pos;
        char r22 = r0[r13];
        if (r22 != '*') goto L32;
        this.pos = r13 + 1;
        if (skipTo("*/") == false) goto L39;
        r1 = this.pos + 2;
        r2 = this.limit;
        goto L4
    L39:
        throw syntaxError("Unterminated comment");
    L32:
        if (r22 != '/') goto L33;
        this.pos = r13 + 1;
        skipToEndOfLine();
        r1 = this.pos;
        r2 = this.limit;
    L45:
        r1 = r4;
        goto L4
    L15:
        this.lineNumber++;
        this.lineStart = r4;
        goto L45
    }

    private String nextQuotedValue(char r11) {
        char[] r0 = this.buffer;
        StringBuilder r1 = null;
    L3:
        int r2 = this.pos;
        int r3 = this.limit;
    L4:
        int r4 = r3;
        int r32 = r2;
    L6:
        if (r2 >= r4) goto L31;
        int r7 = r2 + 1;
        char r22 = r0[r2];
        if (this.strictness == Strictness.STRICT) goto L10;
    L14:
        if (r22 == r11) goto L15;
        if (r22 == '\\') goto L23;
        if (r22 != '\n') goto L30;
        this.lineNumber++;
        this.lineStart = r7;
    L30:
        r2 = r7;
        goto L6
    L23:
        this.pos = r7;
        int r72 = r7 - r32;
        int r23 = r72 - 1;
        if (r1 != null) goto L26;
        r1 = new StringBuilder(Math.max(r72 * 2, 16));
    L26:
        r1.append(r0, r32, r23);
        r1.append(readEscapeCharacter());
        r2 = this.pos;
        r3 = this.limit;
        goto L4
    L15:
        this.pos = r7;
        int r73 = (r7 - r32) - 1;
        if (r1 == null) goto L18;
        r1.append(r0, r32, r73);
        return r1.toString();
    L18:
        return new String(r0, r32, r73);
    L10:
        if (r22 >= ' ') goto L14;
        throw syntaxError("Unescaped control characters (\\u0000-\\u001F) are not allowed in strict mode");
    L31:
        if (r1 != null) goto L33;
        r1 = new StringBuilder(Math.max((r2 - r32) * 2, 16));
    L33:
        r1.append(r0, r32, r2 - r32);
        this.pos = r2;
        if (fillBuffer(1) == true) goto L3;
        throw syntaxError("Unterminated string");
    }

    private String nextUnquotedValue() {
        StringBuilder r0 = null;
        int r1 = 0;
    L3:
        int r2 = 0;
    L4:
        int r3 = this.pos;
        int r4 = r3 + r2;
        int r5 = this.limit;
        char[] r6 = this.buffer;
        if (r4 < r5) goto L6;
        if (r2 >= r6.length) goto L41;
        if (fillBuffer(r2 + 1) == true) goto L4;
    L40:
        r1 = r2;
    L45:
        char[] r22 = this.buffer;
        if (r0 != null) goto L48;
        String r02 = new String(r22, this.pos, r1);
    L49:
        this.pos += r1;
        return r02;
    L48:
        r0.append(r22, this.pos, r1);
        r02 = r0.toString();
        goto L49
    L41:
        if (r0 != null) goto L43;
        r0 = new StringBuilder(Math.max(r2, 16));
    L43:
        r0.append(this.buffer, this.pos, r2);
        this.pos += r2;
        if (fillBuffer(1) == true) goto L3;
    L6:
        char r32 = r6[r3 + r2];
        if (r32 == '\t') goto L40;
        if (r32 == '\n') goto L40;
        if (r32 == '\f') goto L40;
        if (r32 == '\r') goto L40;
        if (r32 == ' ') goto L40;
        if (r32 == '#') goto L34;
        if (r32 == ',') goto L40;
        if (r32 == '/') goto L34;
        if (r32 == '=') goto L34;
        if (r32 == '{') goto L40;
        if (r32 == '}') goto L40;
        if (r32 == ':') goto L40;
        if (r32 == ';') goto L34;
        switch(r32) {
            case 91: goto L40;
            case 92: goto L34;
            case 93: goto L40;
            default: goto L33;
        };
    L33:
        r2 = r2 + 1;
    L34:
        checkLenient();
        goto L40
    }

    private int peekKeyword() {
        char r0 = this.buffer[this.pos];
        if (r0 != 't') goto L5;
    L20:
        String r02 = "true";
        String r1 = "TRUE";
        int r3 = 5;
    L22:
        if (this.strictness == Strictness.STRICT) goto L24;
        boolean r4 = true;
    L25:
        int r5 = r02.length();
        int r6 = 0;
    L26:
        int r7 = this.pos;
        if (r6 >= r5) goto L42;
        if ((r7 + r6) >= this.limit) goto L31;
    L33:
        char r72 = this.buffer[this.pos + r6];
        if (r72 == r02.charAt(r6)) goto L40;
        if (r4 == false) goto L39;
        if (r72 == r1.charAt(r6)) goto L40;
    L39:
        return 0;
    L40:
        r6 = r6 + 1;
        goto L26
    L31:
        if (fillBuffer(r6 + 1) == true) goto L33;
        return 0;
    L42:
        if ((r7 + r5) < this.limit) goto L46;
        if (fillBuffer(r5 + 1) == true) goto L46;
    L48:
        this.pos += r5;
        this.peeked = r3;
        return r3;
    L46:
        if (isLiteral(this.buffer[this.pos + r5]) == false) goto L48;
        return 0;
    L24:
        r4 = false;
        goto L25
    L5:
        if (r0 == 'T') goto L20;
        if (r0 != 'f') goto L10;
    L19:
        r02 = "false";
        r1 = "FALSE";
        r3 = 6;
        goto L22
    L10:
        if (r0 == 'F') goto L19;
        if (r0 != 'n') goto L15;
    L18:
        r02 = "null";
        r1 = "NULL";
        r3 = 7;
        goto L22
    L15:
        if (r0 == 'N') goto L18;
        return 0;
    }

    private int peekNumber() {
        char[] r1 = this.buffer;
        int r2 = this.pos;
        int r3 = this.limit;
        int r6 = 0;
        int r8 = 0;
        char r9 = 0;
        boolean r13 = false;
        int r10 = 1;
        long r11 = 0;
    L3:
        char r5 = 2;
        if ((r2 + r8) == r3) goto L6;
    L12:
        char r14 = r1[r2 + r8];
        int r18 = r6;
        if (r14 != '+') goto L15;
        r5 = 6;
        if (r9 != 5) goto L95;
    L50:
        r9 = r5;
    L94:
        r8 = r8 + 1;
        r6 = r18;
        goto L3
    L95:
        return r18;
    L15:
        if (r14 != 'E') goto L17;
    L85:
        if (r9 != 2) goto L87;
    L90:
        r9 = 5;
        goto L94
    L87:
        if (r9 == 4) goto L90;
        return r18;
    L17:
        if (r14 == 'e') goto L85;
        if (r14 != '-') goto L21;
        r5 = 6;
        if (r9 != 0) goto L82;
        r9 = 1;
        r13 = true;
        goto L94
    L82:
        if (r9 == 5) goto L50;
        return r18;
    L21:
        if (r14 != '.') goto L23;
        if (r9 != 2) goto L78;
        r9 = 3;
        goto L94
    L78:
        return r18;
    L23:
        if (r14 < '0') goto L52;
        if (r14 > '9') goto L52;
        if (r9 == 1) goto L49;
        if (r9 == 0) goto L49;
        if (r9 == 2) goto L32;
        if (r9 != 3) goto L45;
        r9 = 4;
        goto L94
    L45:
        if (r9 != 5) goto L47;
    L48:
        r9 = 7;
        goto L94
    L47:
        if (r9 != 6) goto L94;
    L32:
        if (r11 == 0) goto L33;
        long r4 = (10 * r11) - (r14 - '0');
        if (r11 > (-922337203685477580L)) goto L41;
        if (r11 == (-922337203685477580L)) goto L38;
    L40:
        int r62 = r18;
    L42:
        r10 = r10 & r62;
        r11 = r4;
        goto L94
    L38:
        if (r4 >= r11) goto L40;
    L41:
        r62 = 1;
        goto L42
    L33:
        return r18;
    L49:
        r11 = -(r14 - '0');
    L52:
        if (isLiteral(r14) == true) goto L75;
    L53:
        if (r9 != 2) goto L66;
        if (r10 == 0) goto L66;
        if (r11 != Long.MIN_VALUE) goto L59;
        if (r13 == false) goto L66;
    L59:
        if (r11 != 0) goto L61;
        if (r13 == true) goto L66;
    L61:
        if (r13 == true) goto L64;
        r11 = -r11;
    L64:
        this.peekedLong = r11;
        this.pos += r8;
        this.peeked = 15;
        return 15;
    L66:
        if (r9 != 2) goto L68;
    L73:
        this.peekedNumberLength = r8;
        this.peeked = 16;
        return 16;
    L68:
        if (r9 == 4) goto L73;
        if (r9 == 7) goto L73;
        return r18;
    L75:
        return r18;
    L6:
        if (r8 == r1.length) goto L7;
        if (fillBuffer(r8 + 1) == false) goto L10;
        r2 = this.pos;
        r3 = this.limit;
        goto L12
    L10:
        r18 = r6;
        goto L53
    L7:
        return r6;
    }

    private void push(int r4) {
        int r0 = this.stackSize;
        if ((r0 - 1) >= this.nestingLimit) goto L10;
        int[] r1 = this.stack;
        if (r0 != r1.length) goto L7;
        int r02 = r0 * 2;
        this.stack = Arrays.copyOf(r1, r02);
        this.pathIndices = Arrays.copyOf(this.pathIndices, r02);
        this.pathNames = (String[]) Arrays.copyOf(this.pathNames, r02);
    L7:
        int[] r03 = this.stack;
        int r12 = this.stackSize;
        this.stackSize = r12 + 1;
        r03[r12] = r4;
        return;
    L10:
        throw new MalformedJsonException("Nesting limit " + this.nestingLimit + " reached" + locationString());
    }

    private char readEscapeCharacter() {
        if (this.pos == this.limit) goto L5;
    L9:
        char[] r0 = this.buffer;
        int r1 = this.pos;
        int r4 = r1 + 1;
        this.pos = r4;
        char r02 = r0[r1];
        if (r02 == '\n') goto L72;
        if (r02 != '\"') goto L14;
    L76:
        return r02;
    L14:
        if (r02 == '\'') goto L75;
        if (r02 == '/') goto L76;
        if (r02 == '\\') goto L76;
        if (r02 != 'b') goto L22;
        return '\b';
    L22:
        if (r02 != 'f') goto L24;
        return '\f';
    L24:
        if (r02 != 'n') goto L26;
        return '\n';
    L26:
        if (r02 != 'r') goto L28;
        return '\r';
    L28:
        if (r02 != 't') goto L30;
        return '\t';
    L30:
        if (r02 != 'u') goto L61;
        if ((r1 + 5) > this.limit) goto L34;
    L38:
        int r03 = this.pos;
        int r12 = r03 + 4;
        int r2 = 0;
    L39:
        if (r03 >= r12) goto L58;
        char[] r5 = this.buffer;
        char r6 = r5[r03];
        int r22 = r2 << 4;
        if (r6 < '0') goto L47;
        if (r6 > '9') goto L47;
        int r62 = r6 - '0';
    L45:
        r2 = r22 + r62;
        r03 = r03 + 1;
    L47:
        if (r6 < 'a') goto L51;
        if (r6 > 'f') goto L51;
        r62 = r6 - 'W';
    L51:
        if (r6 < 'A') goto L57;
        if (r6 > 'F') goto L57;
        r62 = r6 - '7';
    L57:
        throw syntaxError("Malformed Unicode escape \\u".concat(new String(r5, this.pos, 4)));
    L58:
        this.pos += 4;
        return (char) r2;
    L34:
        if (fillBuffer(4) == true) goto L38;
        throw syntaxError("Unterminated escape sequence");
    L61:
        throw syntaxError("Invalid escape sequence");
    L75:
        if (this.strictness != Strictness.STRICT) goto L76;
        throw syntaxError("Invalid escaped character \"'\" in strict mode");
    L72:
        if (this.strictness == Strictness.STRICT) goto L80;
        this.lineNumber++;
        this.lineStart = r4;
        goto L75
    L80:
        throw syntaxError("Cannot escape a newline character in strict mode");
    L5:
        if (fillBuffer(1) == true) goto L9;
        throw syntaxError("Unterminated escape sequence");
    }

    private void skipQuotedValue(char r7) {
        char[] r0 = this.buffer;
    L3:
        int r1 = this.pos;
        int r2 = this.limit;
    L5:
        if (r1 >= r2) goto L17;
        int r4 = r1 + 1;
        char r12 = r0[r1];
        if (r12 == r7) goto L8;
        if (r12 == '\\') goto L12;
        if (r12 != '\n') goto L16;
        this.lineNumber++;
        this.lineStart = r4;
    L16:
        r1 = r4;
        goto L5
    L12:
        this.pos = r4;
        readEscapeCharacter();
        r1 = this.pos;
        r2 = this.limit;
        goto L5
    L8:
        this.pos = r4;
        return;
    L17:
        this.pos = r1;
        if (fillBuffer(1) == true) goto L3;
        throw syntaxError("Unterminated string");
    }

    private boolean skipTo(String r7) {
        int r0 = r7.length();
    L3:
        int r3 = 0;
        if ((this.pos + r0) > this.limit) goto L6;
    L9:
        char[] r1 = this.buffer;
        int r2 = this.pos;
        if (r1[r2] != '\n') goto L12;
        this.lineNumber++;
        this.lineStart = r2 + 1;
    L15:
        this.pos++;
    L12:
        if (r3 >= r0) goto L17;
        if (this.buffer[this.pos + r3] != r7.charAt(r3)) goto L15;
        r3 = r3 + 1;
        goto L12
    L17:
        return true;
    L6:
        if (fillBuffer(r0) == true) goto L9;
        return false;
    }

    private void skipToEndOfLine() {
    L3:
        if (this.pos >= this.limit) goto L5;
    L6:
        char[] r0 = this.buffer;
        int r1 = this.pos;
        int r3 = r1 + 1;
        this.pos = r3;
        char r02 = r0[r1];
        if (r02 == '\n') goto L8;
        if (r02 != '\r') goto L3;
        return;
    L8:
        this.lineNumber++;
        this.lineStart = r3;
        return;
    L5:
        if (fillBuffer(1) == true) goto L6;
    }

    private void skipUnquotedValue() {
    L2:
        int r0 = 0;
    L3:
        int r1 = this.pos;
        if ((r1 + r0) >= this.limit) goto L36;
        char r12 = this.buffer[r1 + r0];
        if (r12 == '\t') goto L34;
        if (r12 == '\n') goto L34;
        if (r12 == '\f') goto L34;
        if (r12 == '\r') goto L34;
        if (r12 == ' ') goto L34;
        if (r12 == '#') goto L33;
        if (r12 == ',') goto L34;
        if (r12 == '/') goto L33;
        if (r12 == '=') goto L33;
        if (r12 == '{') goto L34;
        if (r12 == '}') goto L34;
        if (r12 == ':') goto L34;
        if (r12 == ';') goto L33;
        switch(r12) {
            case 91: goto L34;
            case 92: goto L33;
            case 93: goto L34;
            default: goto L32;
        };
    L32:
        r0 = r0 + 1;
    L33:
        checkLenient();
    L34:
        this.pos += r0;
        return;
    L36:
        this.pos = r1 + r0;
        if (fillBuffer(1) == true) goto L2;
    }

    private MalformedJsonException syntaxError(String r3) {
        throw new MalformedJsonException(r3 + locationString() + "\nSee " + TroubleshootingGuide.createUrl("malformed-json"));
    }

    private IllegalStateException unexpectedTokenError(String r5) {
        if (peek() != JsonToken.NULL) goto L5;
        String r0 = "adapter-not-null-safe";
    L7:
        return new IllegalStateException("Expected " + r5 + " but was " + peek() + locationString() + "\nSee " + TroubleshootingGuide.createUrl(r0));
    L5:
        r0 = "unexpected-json-structure";
        goto L7
    }

    private void validateAscii(String r4) {
        int r0 = 0;
    L4:
        if (r0 >= r4.length()) goto L10;
        if (r4.charAt(r0) > 127) goto L9;
        r0 = r0 + 1;
        goto L4
    L9:
        throw syntaxError("String contains non-ASCII characters: ".concat(r4));
    }

    public void beginArray() {
        int r0 = this.peeked;
        if (r0 != 0) goto L6;
        r0 = doPeek();
    L6:
        if (r0 != 3) goto L10;
        push(1);
        this.pathIndices[this.stackSize - 1] = 0;
        this.peeked = 0;
        return;
    L10:
        throw unexpectedTokenError("BEGIN_ARRAY");
    }

    public void beginObject() {
        int r0 = this.peeked;
        if (r0 != 0) goto L6;
        r0 = doPeek();
    L6:
        if (r0 != 1) goto L10;
        push(3);
        this.peeked = 0;
        return;
    L10:
        throw unexpectedTokenError("BEGIN_OBJECT");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.peeked = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.in.close();
    }

    public int doPeek() {
        int[] r0 = this.stack;
        int r1 = this.stackSize;
        int r2 = r0[r1 - 1];
        if (r2 != 1) goto L5;
        r0[r1 - 1] = 2;
    L46:
        int r02 = nextNonWhitespace(true);
        if (r02 == 34) goto L84;
        if (r02 == 39) goto L82;
        if (r02 == 44) goto L75;
        if (r02 == 59) goto L75;
        if (r02 == 91) goto L73;
        if (r02 != 93) goto L55;
        if (r2 != 1) goto L75;
        this.peeked = 4;
        return 4;
    L55:
        if (r02 == 123) goto L68;
        this.pos--;
        int r03 = peekKeyword();
        if (r03 == 0) goto L59;
        return r03;
    L59:
        int r04 = peekNumber();
        if (r04 == 0) goto L63;
        return r04;
    L63:
        if (isLiteral(this.buffer[this.pos]) == false) goto L67;
        checkLenient();
        this.peeked = 10;
        return 10;
    L67:
        throw syntaxError("Expected value");
    L68:
        this.peeked = 1;
        return 1;
    L73:
        this.peeked = 3;
        return 3;
    L75:
        if (r2 == 1) goto L80;
        if (r2 == 2) goto L80;
        throw syntaxError("Unexpected value");
    L80:
        checkLenient();
        this.pos--;
        this.peeked = 7;
        return 7;
    L82:
        checkLenient();
        this.peeked = 8;
        return 8;
    L84:
        this.peeked = 9;
        return 9;
    L5:
        if (r2 != 2) goto L16;
        int r05 = nextNonWhitespace(true);
        if (r05 == 44) goto L46;
        if (r05 == 59) goto L14;
        if (r05 != 93) goto L13;
        this.peeked = 4;
        return 4;
    L13:
        throw syntaxError("Unterminated array");
    L14:
        checkLenient();
        goto L46
    L16:
        if (r2 == 3) goto L88;
        if (r2 == 5) goto L88;
        if (r2 != 4) goto L34;
        r0[r1 - 1] = 5;
        int r06 = nextNonWhitespace(true);
        if (r06 == 58) goto L46;
        if (r06 != 61) goto L32;
        checkLenient();
        if (this.pos >= this.limit) goto L27;
    L28:
        char[] r07 = this.buffer;
        int r12 = this.pos;
        if (r07[r12] != '>') goto L46;
        this.pos = r12 + 1;
        goto L46
    L27:
        if (fillBuffer(1) == false) goto L46;
    L32:
        throw syntaxError("Expected ':'");
    L34:
        if (r2 == 6) goto L36;
        if (r2 == 7) goto L41;
        if (r2 != 8) goto L46;
        Segment$$ExternalSyntheticBUOutline1.m("JsonReader is closed");
        return 0;
    L41:
        if (nextNonWhitespace(false) != (-1)) goto L44;
        this.peeked = 17;
        return 17;
    L44:
        checkLenient();
        this.pos--;
        goto L46
    L36:
        if (this.strictness != Strictness.LENIENT) goto L38;
        consumeNonExecutePrefix();
    L38:
        this.stack[this.stackSize - 1] = 7;
    L88:
        r0[r1 - 1] = 4;
        if (r2 != 5) goto L99;
        int r13 = nextNonWhitespace(true);
        if (r13 == 44) goto L99;
        if (r13 == 59) goto L98;
        if (r13 != 125) goto L97;
        this.peeked = 2;
        return 2;
    L97:
        throw syntaxError("Unterminated object");
    L98:
        checkLenient();
    L99:
        int r14 = nextNonWhitespace(true);
        if (r14 == 34) goto L117;
        if (r14 != 39) goto L103;
        checkLenient();
        this.peeked = 12;
        return 12;
    L103:
        if (r14 == 125) goto L110;
        checkLenient();
        this.pos--;
        if (isLiteral((char) r14) == false) goto L109;
        this.peeked = 14;
        return 14;
    L109:
        throw syntaxError("Expected name");
    L110:
        if (r2 == 5) goto L114;
        this.peeked = 2;
        return 2;
    L114:
        throw syntaxError("Expected name");
    L117:
        this.peeked = 13;
        return 13;
    }

    public void endArray() {
        int r0 = this.peeked;
        if (r0 != 0) goto L6;
        r0 = doPeek();
    L6:
        if (r0 != 4) goto L10;
        int r02 = this.stackSize;
        this.stackSize = r02 - 1;
        int[] r1 = this.pathIndices;
        int r03 = r02 - 2;
        r1[r03] = r1[r03] + 1;
        this.peeked = 0;
        return;
    L10:
        throw unexpectedTokenError("END_ARRAY");
    }

    public void endObject() {
        int r0 = this.peeked;
        if (r0 != 0) goto L6;
        r0 = doPeek();
    L6:
        if (r0 != 2) goto L10;
        int r02 = this.stackSize;
        int r2 = r02 - 1;
        this.stackSize = r2;
        this.pathNames[r2] = null;
        int[] r22 = this.pathIndices;
        int r03 = r02 - 2;
        r22[r03] = r22[r03] + 1;
        this.peeked = 0;
        return;
    L10:
        throw unexpectedTokenError("END_OBJECT");
    }

    public String getPath() {
        return getPath(false);
    }

    public String getPreviousPath() {
        return getPath(true);
    }

    public final Strictness getStrictness() {
        return this.strictness;
    }

    public boolean hasNext() {
        int r0 = this.peeked;
        if (r0 != 0) goto L6;
        r0 = doPeek();
    L6:
        if (r0 != 2) goto L8;
        return false;
    L8:
        if (r0 != 4) goto L10;
        return false;
    L10:
        if (r0 == 17) goto L16;
        return true;
    L16:
        return false;
    }

    public final boolean isLenient() {
        if (this.strictness != Strictness.LENIENT) goto L6;
        return true;
    L6:
        return false;
    }

    public String locationString() {
        return " at line " + (this.lineNumber + 1) + " column " + ((this.pos - this.lineStart) + 1) + " path " + getPath();
    }

    public boolean nextBoolean() {
        int r0 = this.peeked;
        if (r0 != 0) goto L6;
        r0 = doPeek();
    L6:
        if (r0 != 5) goto L10;
        this.peeked = 0;
        int[] r02 = this.pathIndices;
        int r4 = this.stackSize - 1;
        r02[r4] = r02[r4] + 1;
        return true;
    L10:
        if (r0 != 6) goto L14;
        this.peeked = 0;
        int[] r03 = this.pathIndices;
        int r42 = this.stackSize - 1;
        r03[r42] = r03[r42] + 1;
        return false;
    L14:
        throw unexpectedTokenError("a boolean");
    }

    public double nextDouble() {
        int r0 = this.peeked;
        if (r0 != 0) goto L6;
        r0 = doPeek();
    L6:
        if (r0 != 15) goto L10;
        this.peeked = 0;
        int[] r02 = this.pathIndices;
        int r1 = this.stackSize - 1;
        r02[r1] = r02[r1] + 1;
        return this.peekedLong;
    L10:
        if (r0 != 16) goto L13;
        this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
        this.pos += this.peekedNumberLength;
    L28:
        this.peeked = 11;
        double r03 = Double.parseDouble(this.peekedString);
        if (this.strictness != Strictness.LENIENT) goto L31;
    L37:
        this.peekedString = null;
        this.peeked = 0;
        int[] r2 = this.pathIndices;
        int r6 = this.stackSize - 1;
        r2[r6] = r2[r6] + 1;
        return r03;
    L31:
        if (Double.isNaN(r03) == true) goto L36;
        if (Double.isInfinite(r03) == false) goto L37;
    L36:
        throw syntaxError("JSON forbids NaN and infinities: " + r03);
    L13:
        if (r0 != 8) goto L15;
    L24:
        if (r0 != 8) goto L26;
        char r04 = '\'';
    L27:
        this.peekedString = nextQuotedValue(r04);
        goto L28
    L26:
        r04 = Typography.quote;
        goto L27
    L15:
        if (r0 == 9) goto L24;
        if (r0 != 10) goto L20;
        this.peekedString = nextUnquotedValue();
        goto L28
    L20:
        if (r0 == 11) goto L28;
        throw unexpectedTokenError("a double");
    }

    public int nextInt() {
        int r0 = this.peeked;
        if (r0 != 0) goto L6;
        r0 = doPeek();
    L6:
        if (r0 != 15) goto L14;
        long r02 = this.peekedLong;
        int r4 = (int) r02;
        if (r02 != r4) goto L12;
        this.peeked = 0;
        int[] r03 = this.pathIndices;
        int r7 = this.stackSize - 1;
        r03[r7] = r03[r7] + 1;
        return r4;
    L12:
        throw new NumberFormatException("Expected an int but was " + this.peekedLong + locationString());
    L14:
        if (r0 != 16) goto L17;
        this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
        this.pos += this.peekedNumberLength;
    L33:
        this.peeked = 11;
        double r04 = Double.parseDouble(this.peekedString);
        int r42 = (int) r04;
        if (r42 != r04) goto L38;
        this.peekedString = null;
        this.peeked = 0;
        int[] r05 = this.pathIndices;
        int r72 = this.stackSize - 1;
        r05[r72] = r05[r72] + 1;
        return r42;
    L38:
        throw new NumberFormatException("Expected an int but was " + this.peekedString + locationString());
    L17:
        if (r0 != 8) goto L19;
    L24:
        if (r0 != 10) goto L26;
        this.peekedString = nextUnquotedValue();
    L30:
        validateAscii(this.peekedString);
        int r06 = Integer.parseInt(this.peekedString);     // Catch: NumberFormatException -> L39
        this.peeked = 0;     // Catch: NumberFormatException -> L39
        int[] r1 = this.pathIndices;     // Catch: NumberFormatException -> L39
        int r43 = this.stackSize - 1;
        r1[r43] = r1[r43] + 1;     // Catch: NumberFormatException -> L39
        return r06;
    L26:
        if (r0 != 8) goto L28;
        char r07 = '\'';
    L29:
        this.peekedString = nextQuotedValue(r07);
        goto L30
    L28:
        r07 = Typography.quote;
        goto L29
    L19:
        if (r0 == 9) goto L24;
        if (r0 == 10) goto L24;
        throw unexpectedTokenError("an int");
    }

    public long nextLong() {
        int r0 = this.peeked;
        if (r0 != 0) goto L6;
        r0 = doPeek();
    L6:
        if (r0 != 15) goto L10;
        this.peeked = 0;
        int[] r02 = this.pathIndices;
        int r1 = this.stackSize - 1;
        r02[r1] = r02[r1] + 1;
        return this.peekedLong;
    L10:
        if (r0 != 16) goto L13;
        this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
        this.pos += this.peekedNumberLength;
    L29:
        this.peeked = 11;
        double r03 = Double.parseDouble(this.peekedString);
        long r3 = (long) r03;
        if (r3 != r03) goto L34;
        this.peekedString = null;
        this.peeked = 0;
        int[] r04 = this.pathIndices;
        int r7 = this.stackSize - 1;
        r04[r7] = r04[r7] + 1;
        return r3;
    L34:
        throw new NumberFormatException("Expected a long but was " + this.peekedString + locationString());
    L13:
        if (r0 != 8) goto L15;
    L20:
        if (r0 != 10) goto L22;
        this.peekedString = nextUnquotedValue();
    L26:
        validateAscii(this.peekedString);
        long r05 = Long.parseLong(this.peekedString);     // Catch: NumberFormatException -> L35
        this.peeked = 0;     // Catch: NumberFormatException -> L35
        int[] r32 = this.pathIndices;     // Catch: NumberFormatException -> L35
        int r4 = this.stackSize - 1;
        r32[r4] = r32[r4] + 1;     // Catch: NumberFormatException -> L35
        return r05;
    L22:
        if (r0 != 8) goto L24;
        char r06 = '\'';
    L25:
        this.peekedString = nextQuotedValue(r06);
        goto L26
    L24:
        r06 = Typography.quote;
        goto L25
    L15:
        if (r0 == 9) goto L20;
        if (r0 == 10) goto L20;
        throw unexpectedTokenError("a long");
    }

    public String nextName() {
        int r0 = this.peeked;
        if (r0 != 0) goto L6;
        r0 = doPeek();
    L6:
        if (r0 != 14) goto L9;
        String r02 = nextUnquotedValue();
    L14:
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = r02;
        return r02;
    L9:
        if (r0 != 12) goto L12;
        r02 = nextQuotedValue('\'');
        goto L14
    L12:
        if (r0 != 13) goto L17;
        r02 = nextQuotedValue(Typography.quote);
        goto L14
    L17:
        throw unexpectedTokenError("a name");
    }

    public void nextNull() {
        int r0 = this.peeked;
        if (r0 != 0) goto L6;
        r0 = doPeek();
    L6:
        if (r0 != 7) goto L10;
        this.peeked = 0;
        int[] r02 = this.pathIndices;
        int r2 = this.stackSize - 1;
        r02[r2] = r02[r2] + 1;
        return;
    L10:
        throw unexpectedTokenError("null");
    }

    public String nextString() {
        int r0 = this.peeked;
        if (r0 != 0) goto L6;
        r0 = doPeek();
    L6:
        if (r0 != 10) goto L9;
        String r02 = nextUnquotedValue();
    L23:
        this.peeked = 0;
        int[] r1 = this.pathIndices;
        int r4 = this.stackSize - 1;
        r1[r4] = r1[r4] + 1;
        return r02;
    L9:
        if (r0 != 8) goto L12;
        r02 = nextQuotedValue('\'');
        goto L23
    L12:
        if (r0 != 9) goto L15;
        r02 = nextQuotedValue(Typography.quote);
        goto L23
    L15:
        if (r0 != 11) goto L18;
        r02 = this.peekedString;
        this.peekedString = null;
        goto L23
    L18:
        if (r0 != 15) goto L21;
        r02 = Long.toString(this.peekedLong);
        goto L23
    L21:
        if (r0 != 16) goto L26;
        r02 = new String(this.buffer, this.pos, this.peekedNumberLength);
        this.pos += this.peekedNumberLength;
        goto L23
    L26:
        throw unexpectedTokenError("a string");
    }

    public JsonToken peek() {
        int r0 = this.peeked;
        if (r0 != 0) goto L5;
        r0 = doPeek();
    L5:
        switch(r0) {
            case 1: goto L27;
            case 2: goto L25;
            case 3: goto L23;
            case 4: goto L21;
            case 5: goto L19;
            case 6: goto L19;
            case 7: goto L17;
            case 8: goto L15;
            case 9: goto L15;
            case 10: goto L15;
            case 11: goto L15;
            case 12: goto L13;
            case 13: goto L13;
            case 14: goto L13;
            case 15: goto L11;
            case 16: goto L11;
            case 17: goto L9;
            default: goto L6;
        };
    L6:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return null;
    L9:
        return JsonToken.END_DOCUMENT;
    L11:
        return JsonToken.NUMBER;
    L13:
        return JsonToken.NAME;
    L15:
        return JsonToken.STRING;
    L17:
        return JsonToken.NULL;
    L19:
        return JsonToken.BOOLEAN;
    L21:
        return JsonToken.END_ARRAY;
    L23:
        return JsonToken.BEGIN_ARRAY;
    L25:
        return JsonToken.END_OBJECT;
    L27:
        return JsonToken.BEGIN_OBJECT;
    }

    public final void setStrictness(Strictness r1) {
        Objects.requireNonNull(r1);
        this.strictness = r1;
    }

    public void skipValue() {
        int r1 = 0;
    L3:
        int r2 = this.peeked;
        if (r2 != 0) goto L7;
        r2 = doPeek();
    L7:
        switch(r2) {
            case 1: goto L30;
            case 2: goto L27;
            case 3: goto L25;
            case 4: goto L23;
            case 5: goto L31;
            case 6: goto L31;
            case 7: goto L31;
            case 8: goto L22;
            case 9: goto L21;
            case 10: goto L20;
            case 11: goto L31;
            case 12: goto L17;
            case 13: goto L14;
            case 14: goto L11;
            case 15: goto L31;
            case 16: goto L10;
            case 17: goto L9;
            default: goto L31;
        };
    L10:
        this.pos += this.peekedNumberLength;
        goto L31
    L11:
        skipUnquotedValue();
        if (r1 != 0) goto L31;
        this.pathNames[this.stackSize - 1] = "<skipped>";
        goto L31
    L14:
        skipQuotedValue(Typography.quote);
        if (r1 != 0) goto L31;
        this.pathNames[this.stackSize - 1] = "<skipped>";
        goto L31
    L17:
        skipQuotedValue('\'');
        if (r1 != 0) goto L31;
        this.pathNames[this.stackSize - 1] = "<skipped>";
        goto L31
    L20:
        skipUnquotedValue();
        goto L31
    L21:
        skipQuotedValue(Typography.quote);
        goto L31
    L22:
        skipQuotedValue('\'');
        goto L31
    L23:
        this.stackSize--;
    L24:
        r1 = r1 - 1;
        goto L31
    L25:
        push(1);
    L26:
        r1 = r1 + 1;
        goto L31
    L27:
        if (r1 != 0) goto L29;
        this.pathNames[this.stackSize - 1] = null;
    L29:
        this.stackSize--;
        goto L24
    L30:
        push(3);
    L31:
        this.peeked = 0;
        if (r1 > 0) goto L3;
        int[] r0 = this.pathIndices;
        int r7 = this.stackSize - 1;
        r0[r7] = r0[r7] + 1;
        return;
    }

    public String toString() {
        return getClass().getSimpleName() + locationString();
    }
}
