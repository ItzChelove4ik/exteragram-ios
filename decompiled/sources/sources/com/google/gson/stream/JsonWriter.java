package com.google.gson.stream;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.dex.code.CstInsn$$ExternalSyntheticBUOutline0;
import com.google.gson.FormattingStyle;
import com.google.gson.Strictness;
import java.io.Closeable;
import java.io.Flushable;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.vosk.Model$$ExternalSyntheticBUOutline0;
import retrofit2.Utils$$ExternalSyntheticBUOutline2;

/* loaded from: classes.dex */
public class JsonWriter implements Closeable, Flushable {
    private static final String[] HTML_SAFE_REPLACEMENT_CHARS = null;
    private static final String[] REPLACEMENT_CHARS = null;
    private static final Pattern VALID_JSON_NUMBER_PATTERN = null;
    private String deferredName;
    private String formattedColon;
    private String formattedComma;
    private FormattingStyle formattingStyle;
    private boolean htmlSafe;
    private final Writer out;
    private boolean serializeNulls;
    private int[] stack;
    private int stackSize;
    private Strictness strictness;
    private boolean usesEmptyNewlineAndIndent;

    static {
        VALID_JSON_NUMBER_PATTERN = Pattern.compile("-?(?:0|[1-9][0-9]*)(?:\\.[0-9]+)?(?:[eE][-+]?[0-9]+)?");
        REPLACEMENT_CHARS = new String[128];
        int r0 = 0;
    L4:
        if (r0 > 31) goto L6;
        REPLACEMENT_CHARS[r0] = String.format("\\u%04x", new Object[]{Integer.valueOf(r0)});
        r0 = r0 + 1;
        goto L4
    L6:
        String[] r02 = REPLACEMENT_CHARS;
        r02[34] = "\\\"";
        r02[92] = "\\\\";
        r02[9] = "\\t";
        r02[8] = "\\b";
        r02[10] = "\\n";
        r02[13] = "\\r";
        r02[12] = "\\f";
        String[] r03 = (String[]) r02.clone();
        HTML_SAFE_REPLACEMENT_CHARS = r03;
        r03[60] = "\\u003c";
        r03[62] = "\\u003e";
        r03[38] = "\\u0026";
        r03[61] = "\\u003d";
        r03[39] = "\\u0027";
    }

    public JsonWriter(Writer r2) {
        this.stack = new int[32];
        this.stackSize = 0;
        push(6);
        this.strictness = Strictness.LEGACY_STRICT;
        this.serializeNulls = true;
        Objects.requireNonNull(r2, "out == null");
        this.out = r2;
        setFormattingStyle(FormattingStyle.COMPACT);
    }

    private static boolean alwaysCreatesValidJsonNumber(Class<? extends Number> r1) {
        if (r1 != Integer.class) goto L5;
        return true;
    L5:
        if (r1 != Long.class) goto L7;
        return true;
    L7:
        if (r1 != Byte.class) goto L9;
        return true;
    L9:
        if (r1 != Short.class) goto L11;
        return true;
    L11:
        if (r1 != BigDecimal.class) goto L13;
        return true;
    L13:
        if (r1 != BigInteger.class) goto L15;
        return true;
    L15:
        if (r1 != AtomicInteger.class) goto L17;
        return true;
    L17:
        if (r1 == AtomicLong.class) goto L29;
        return false;
    L29:
        return true;
    }

    private void beforeName() {
        int r0 = peek();
        if (r0 != 5) goto L6;
        this.out.write(this.formattedComma);
    L7:
        newline();
        replaceTop(4);
        return;
    L6:
        if (r0 == 3) goto L7;
        Segment$$ExternalSyntheticBUOutline1.m("Nesting problem.");
    }

    private void beforeValue() {
        int r0 = peek();
        if (r0 == 1) goto L23;
        if (r0 != 2) goto L6;
        this.out.append(this.formattedComma);
        newline();
        return;
    L6:
        if (r0 != 4) goto L8;
        this.out.append(this.formattedColon);
        replaceTop(5);
        return;
    L8:
        if (r0 == 6) goto L17;
        if (r0 == 7) goto L11;
        Segment$$ExternalSyntheticBUOutline1.m("Nesting problem.");
        return;
    L11:
        if (this.strictness == Strictness.LENIENT) goto L17;
        Segment$$ExternalSyntheticBUOutline1.m("JSON must have only one top-level value.");
        return;
    L17:
        replaceTop(7);
        return;
    L23:
        replaceTop(2);
        newline();
    }

    private JsonWriter closeScope(int r2, int r3, char r4) {
        int r0 = peek();
        if (r0 == r3) goto L9;
        if (r0 == r2) goto L9;
        Segment$$ExternalSyntheticBUOutline1.m("Nesting problem.");
        return null;
    L9:
        if (this.deferredName != null) goto L15;
        this.stackSize--;
        if (r0 != r3) goto L13;
        newline();
    L13:
        this.out.write(r4);
        return this;
    L15:
        CstInsn$$ExternalSyntheticBUOutline0.m("Dangling name: ", this.deferredName);
        return null;
    }

    private void newline() {
        if (this.usesEmptyNewlineAndIndent == true) goto L8;
        this.out.write(this.formattingStyle.getNewline());
        int r0 = this.stackSize;
        int r1 = 1;
    L6:
        if (r1 >= r0) goto L10;
        this.out.write(this.formattingStyle.getIndent());
        r1 = r1 + 1;
        goto L6
    L10:
        return;
    }

    private JsonWriter openScope(int r1, char r2) {
        beforeValue();
        push(r1);
        this.out.write(r2);
        return this;
    }

    private int peek() {
        int r0 = this.stackSize;
        if (r0 != 0) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("JsonWriter is closed.");
        return 0;
    L5:
        return this.stack[r0 - 1];
    }

    private void push(int r4) {
        int r0 = this.stackSize;
        int[] r1 = this.stack;
        if (r0 != r1.length) goto L5;
        this.stack = Arrays.copyOf(r1, r0 * 2);
    L5:
        int[] r02 = this.stack;
        int r12 = this.stackSize;
        this.stackSize = r12 + 1;
        r02[r12] = r4;
    }

    private void replaceTop(int r2) {
        this.stack[this.stackSize - 1] = r2;
    }

    private void string(String r9) {
        if (this.htmlSafe == false) goto L5;
        String[] r0 = HTML_SAFE_REPLACEMENT_CHARS;
    L6:
        this.out.write(34);
        int r1 = r9.length();
        int r3 = 0;
        int r4 = 0;
    L7:
        if (r3 >= r1) goto L23;
        char r5 = r9.charAt(r3);
        if (r5 >= 128) goto L14;
        String r52 = r0[r5];
        if (r52 == null) goto L22;
    L19:
        if (r4 >= r3) goto L21;
        this.out.write(r9, r4, r3 - r4);
    L21:
        this.out.write(r52);
        r4 = r3 + 1;
    L22:
        r3 = r3 + 1;
        goto L7
    L14:
        if (r5 != 8232) goto L17;
        r52 = "\\u2028";
        goto L19
    L17:
        if (r5 != 8233) goto L22;
        r52 = "\\u2029";
        goto L19
    L23:
        if (r4 >= r1) goto L25;
        this.out.write(r9, r4, r1 - r4);
    L25:
        this.out.write(34);
        return;
    L5:
        r0 = REPLACEMENT_CHARS;
        goto L6
    }

    private void writeDeferredName() {
        if (this.deferredName == null) goto L6;
        beforeName();
        string(this.deferredName);
        this.deferredName = null;
        return;
    }

    public JsonWriter beginArray() {
        writeDeferredName();
        return openScope(1, '[');
    }

    public JsonWriter beginObject() {
        writeDeferredName();
        return openScope(3, '{');
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.out.close();
        int r0 = this.stackSize;
        if (r0 > 1) goto L9;
        if (r0 == 1) goto L6;
    L7:
        this.stackSize = 0;
        return;
    L6:
        if (this.stack[r0 - 1] == 7) goto L7;
    L9:
        Model$$ExternalSyntheticBUOutline0.m("Incomplete document");
    }

    public JsonWriter endArray() {
        return closeScope(1, 2, ']');
    }

    public JsonWriter endObject() {
        return closeScope(3, 5, '}');
    }

    public void flush() {
        if (this.stackSize == 0) goto L6;
        this.out.flush();
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("JsonWriter is closed.");
    }

    public final boolean getSerializeNulls() {
        return this.serializeNulls;
    }

    public final Strictness getStrictness() {
        return this.strictness;
    }

    public final boolean isHtmlSafe() {
        return this.htmlSafe;
    }

    public boolean isLenient() {
        if (this.strictness != Strictness.LENIENT) goto L6;
        return true;
    L6:
        return false;
    }

    public JsonWriter name(String r4) {
        Objects.requireNonNull(r4, "name == null");
        if (this.deferredName != null) goto L13;
        int r0 = peek();
        if (r0 != 3) goto L7;
    L11:
        this.deferredName = r4;
        return this;
    L7:
        if (r0 == 5) goto L11;
        Segment$$ExternalSyntheticBUOutline1.m("Please begin an object before writing a name.");
        return null;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("Already wrote a name, expecting a value.");
        return null;
    }

    public JsonWriter nullValue() {
        if (this.deferredName != null) goto L5;
    L9:
        beforeValue();
        this.out.write("null");
        return this;
    L5:
        if (this.serializeNulls == false) goto L7;
        writeDeferredName();
        goto L9
    L7:
        this.deferredName = null;
        return this;
    }

    public final void setFormattingStyle(FormattingStyle r2) {
        Objects.requireNonNull(r2);
        this.formattingStyle = r2;
        this.formattedComma = ",";
        if (r2.usesSpaceAfterSeparators() == false) goto L7;
        this.formattedColon = ": ";
        if (this.formattingStyle.getNewline().isEmpty() == false) goto L9;
        this.formattedComma = ", ";
    L9:
        if (this.formattingStyle.getNewline().isEmpty() == true) goto L11;
    L13:
        boolean r22 = false;
    L14:
        this.usesEmptyNewlineAndIndent = r22;
        return;
    L11:
        if (this.formattingStyle.getIndent().isEmpty() == false) goto L13;
        r22 = true;
        goto L14
    L7:
        this.formattedColon = ":";
        goto L9
    }

    public final void setHtmlSafe(boolean r1) {
        this.htmlSafe = r1;
    }

    public final void setSerializeNulls(boolean r1) {
        this.serializeNulls = r1;
    }

    public final void setStrictness(Strictness r1) {
        Objects.requireNonNull(r1);
        this.strictness = r1;
    }

    public JsonWriter value(double r3) {
        writeDeferredName();
        if (this.strictness != Strictness.LENIENT) goto L5;
    L11:
        beforeValue();
        this.out.append(Double.toString(r3));
        return this;
    L5:
        if (Double.isNaN(r3) == true) goto L10;
        if (Double.isInfinite(r3) == false) goto L11;
    L10:
        throw new IllegalArgumentException("Numeric values must be finite, but was " + r3);
    }

    public JsonWriter value(long r2) {
        writeDeferredName();
        beforeValue();
        this.out.write(Long.toString(r2));
        return this;
    }

    public JsonWriter value(Boolean r2) {
        if (r2 == null) goto L4;
        writeDeferredName();
        beforeValue();
        Writer r0 = this.out;
        if (r2.booleanValue() == false) goto L8;
        String r22 = "true";
    L9:
        r0.write(r22);
        return this;
    L8:
        r22 = "false";
        goto L9
    L4:
        return nullValue();
    }

    public JsonWriter value(Number r4) {
        if (r4 == null) goto L4;
        writeDeferredName();
        String r0 = r4.toString();
        Class<?> r42 = r4.getClass();
        if (alwaysCreatesValidJsonNumber(r42) == false) goto L8;
    L28:
        beforeValue();
        this.out.append(r0);
        return this;
    L8:
        if (r0.equals("-Infinity") == true) goto L24;
        if (r0.equals("Infinity") == true) goto L24;
        if (r0.equals("NaN") == true) goto L24;
        if (r42 == Float.class) goto L28;
        if (r42 == Double.class) goto L28;
        if (VALID_JSON_NUMBER_PATTERN.matcher(r0).matches() == true) goto L28;
        Utils$$ExternalSyntheticBUOutline2.m("String created by ", r42, " is not a valid JSON number: ", r0);
        return null;
    L24:
        if (this.strictness == Strictness.LENIENT) goto L28;
        f$$ExternalSyntheticBUOutline1.m("Numeric values must be finite, but was ".concat(r0));
        return null;
    L4:
        return nullValue();
    }

    public JsonWriter value(String r1) {
        if (r1 == null) goto L4;
        writeDeferredName();
        beforeValue();
        string(r1);
        return this;
    L4:
        return nullValue();
    }

    public JsonWriter value(boolean r2) {
        writeDeferredName();
        beforeValue();
        Writer r0 = this.out;
        if (r2 == false) goto L5;
        String r22 = "true";
    L6:
        r0.write(r22);
        return this;
    L5:
        r22 = "false";
        goto L6
    }
}
