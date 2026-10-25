package kotlinx.serialization.json.internal;

import java.util.Arrays;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.BuildConfig;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004J\u0012\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004J\u0012\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004J\u0012\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004J\"\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0011H\u0082\u0080\u0004J\n\u0010\u0017\u001a\u00020\tH\u0096\u0080\u0004J\n\u0010\u0018\u001a\u00020\u0011H\u0096\u0080\u0004J\u0012\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u0007H\u0082\u0080\u0004J\u001a\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0083\u0080\bR\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lkotlinx/serialization/json/internal/JsonToStringWriter;", "Lkotlinx/serialization/json/internal/InternalJsonWriter;", "<init>", "()V", "array", _UrlKt.FRAGMENT_ENCODE_SET, "size", _UrlKt.FRAGMENT_ENCODE_SET, "writeLong", _UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, "writeChar", "char", _UrlKt.FRAGMENT_ENCODE_SET, "write", "text", _UrlKt.FRAGMENT_ENCODE_SET, "writeQuoted", "appendStringSlowPath", "firstEscapedChar", "currentSize", "string", BuildConfig.BUILD_TYPE, "toString", "ensureAdditionalCapacity", "expected", "ensureTotalCapacity", "oldSize", "additional", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public final class JsonToStringWriter implements InternalJsonWriter {
    private char[] array;
    private int size;

    public JsonToStringWriter() {
        this.array = CharArrayPool.INSTANCE.take();
    }

    private final void appendStringSlowPath(int r6, int r7, String r8) {
        int r0 = r8.length();
    L4:
        if (r6 >= r0) goto L16;
        int r72 = ensureTotalCapacity(r7, 2);
        char r2 = r8.charAt(r6);
        if (r2 >= StringOpsKt.getESCAPE_MARKERS().length) goto L14;
        byte r3 = StringOpsKt.getESCAPE_MARKERS()[r2];
        if (r3 != 0) goto L11;
        int r32 = r72 + 1;
        this.array[r72] = r2;
    L10:
        r7 = r32;
    L15:
        r6 = r6 + 1;
        goto L4
    L11:
        if (r3 != 1) goto L13;
        String r1 = StringOpsKt.getESCAPE_STRINGS()[r2];
        int r73 = ensureTotalCapacity(r72, r1.length());
        r1.getChars(0, r1.length(), this.array, r73);
        r7 = r73 + r1.length();
        this.size = r7;
        goto L15
    L13:
        char[] r12 = this.array;
        r12[r72] = '\\';
        r12[r72 + 1] = (char) r3;
        r7 = r72 + 2;
        this.size = r7;
        goto L15
    L14:
        r32 = r72 + 1;
        this.array[r72] = r2;
        goto L10
    L16:
        int r62 = ensureTotalCapacity(r7, 1);
        this.array[r62] = Typography.quote;
        this.size = r62 + 1;
    }

    private final void ensureAdditionalCapacity(int r2) {
        ensureTotalCapacity(this.size, r2);
    }

    @IgnorableReturnValue
    private final int ensureTotalCapacity(int r3, int r4) {
        int r42 = r4 + r3;
        char[] r0 = this.array;
        if (r0.length > r42) goto L5;
        this.array = Arrays.copyOf(r0, RangesKt.coerceAtLeast(r42, r3 * 2));
    L5:
        return r3;
    }

    public void release() {
        CharArrayPool.INSTANCE.release(this.array);
    }

    public String toString() {
        return new String(this.array, 0, this.size);
    }

    @Override // kotlinx.serialization.json.internal.InternalJsonWriter
    public void write(String r6) {
        int r0 = r6.length();
        if (r0 != 0) goto L5;
        return;
    L5:
        ensureAdditionalCapacity(r0);
        r6.getChars(0, r6.length(), this.array, this.size);
        this.size += r0;
    }

    @Override // kotlinx.serialization.json.internal.InternalJsonWriter
    public void writeChar(char r4) {
        ensureAdditionalCapacity(1);
        char[] r0 = this.array;
        int r1 = this.size;
        this.size = r1 + 1;
        r0[r1] = r4;
    }

    @Override // kotlinx.serialization.json.internal.InternalJsonWriter
    public void writeLong(long r1) {
        write(String.valueOf(r1));
    }

    @Override // kotlinx.serialization.json.internal.InternalJsonWriter
    public void writeQuoted(String r8) {
        ensureAdditionalCapacity(r8.length() + 2);
        char[] r0 = this.array;
        int r1 = this.size;
        int r2 = r1 + 1;
        r0[r1] = Typography.quote;
        int r12 = r8.length();
        r8.getChars(0, r12, r0, r2);
        int r13 = r12 + r2;
        int r4 = r2;
    L3:
        if (r4 >= r13) goto L11;
        char r5 = r0[r4];
        if (r5 >= StringOpsKt.getESCAPE_MARKERS().length) goto L10;
        if (StringOpsKt.getESCAPE_MARKERS()[r5] == 0) goto L10;
        appendStringSlowPath(r4 - r2, r4, r8);
        return;
    L10:
        r4 = r4 + 1;
        goto L3
    L11:
        r0[r13] = Typography.quote;
        this.size = r13 + 1;
    }
}
