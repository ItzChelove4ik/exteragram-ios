package kotlinx.serialization.json.internal;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import kotlin.KotlinNothingValueException;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import kotlinx.serialization.json.JsonConfiguration;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\r\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b$\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b \u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u001c\u0010\u001bJ\u0017\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u0006H\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010\"\u001a\u00020!2\u0006\u0010 \u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020!H\u0016¢\u0006\u0004\b$\u0010%J\u0017\u0010'\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u0006H&¢\u0006\u0004\b'\u0010\u0016J\u000f\u0010(\u001a\u00020\u0011H&¢\u0006\u0004\b(\u0010\u0013J\u000f\u0010*\u001a\u00020)H&¢\u0006\u0004\b*\u0010+J\r\u0010,\u001a\u00020\u0011¢\u0006\u0004\b,\u0010\u0013J\u0017\u0010/\u001a\u00020\u00112\u0006\u0010.\u001a\u00020-H\u0004¢\u0006\u0004\b/\u00100J\r\u00101\u001a\u00020!¢\u0006\u0004\b1\u0010%J\u0017\u0010*\u001a\u00020!2\u0006\u00102\u001a\u00020-H&¢\u0006\u0004\b*\u00103J\u0017\u00104\u001a\u00020!2\u0006\u00102\u001a\u00020-H\u0004¢\u0006\u0004\b4\u00103J\u000f\u00105\u001a\u00020)H\u0016¢\u0006\u0004\b5\u0010+J\u0017\u00107\u001a\u00020\u00112\b\b\u0002\u00106\u001a\u00020\u0011¢\u0006\u0004\b7\u00108J\u000f\u00109\u001a\u00020\u0006H&¢\u0006\u0004\b9\u0010:J\u0017\u0010<\u001a\u0004\u0018\u00010\f2\u0006\u0010;\u001a\u00020\u0011¢\u0006\u0004\b<\u0010=J\u001f\u0010?\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010>\u001a\u00020\u0006H\u0016¢\u0006\u0004\b?\u0010\u000eJ\u000f\u0010@\u001a\u00020\fH&¢\u0006\u0004\b@\u0010\u0010J\r\u0010A\u001a\u00020\f¢\u0006\u0004\bA\u0010\u0010J'\u0010A\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0005¢\u0006\u0004\bA\u0010BJ\r\u0010C\u001a\u00020\f¢\u0006\u0004\bC\u0010\u0010J\r\u0010D\u001a\u00020\f¢\u0006\u0004\bD\u0010\u0010J\u001f\u0010G\u001a\u00020!2\u0006\u0010E\u001a\u00020\u00062\u0006\u0010F\u001a\u00020\u0006H\u0014¢\u0006\u0004\bG\u0010HJ\u0015\u0010J\u001a\u00020!2\u0006\u0010I\u001a\u00020\u0011¢\u0006\u0004\bJ\u0010KJ\u000f\u0010L\u001a\u00020\fH\u0016¢\u0006\u0004\bL\u0010\u0010J\u0015\u0010N\u001a\u00020!2\u0006\u0010M\u001a\u00020\f¢\u0006\u0004\bN\u0010OJ+\u0010S\u001a\u00020R2\u0006\u0010P\u001a\u00020\f2\b\b\u0002\u0010&\u001a\u00020\u00062\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\f¢\u0006\u0004\bS\u0010TJ\r\u0010V\u001a\u00020U¢\u0006\u0004\bV\u0010WJ\r\u0010X\u001a\u00020\u0011¢\u0006\u0004\bX\u0010\u0013R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010Y\u001a\u0004\bZ\u0010[R\u0016\u0010\u000b\u001a\u00020\u00068\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\\R\u0014\u0010^\u001a\u00020]8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b^\u0010_R\u0018\u0010`\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b`\u0010aR&\u0010d\u001a\u00060bj\u0002`c8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\bd\u0010e\u001a\u0004\bf\u0010g\"\u0004\bh\u0010iR\u0014\u0010\u0018\u001a\u00020\u00178$X¤\u0004¢\u0006\u0006\u001a\u0004\bj\u0010k¨\u0006l"}, d2 = {"Lkotlinx/serialization/json/internal/AbstractJsonLexer;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/serialization/json/JsonConfiguration;", "configuration", "<init>", "(Lkotlinx/serialization/json/JsonConfiguration;)V", _UrlKt.FRAGMENT_ENCODE_SET, "lastPosition", "current", "appendEscape", "(II)I", "currentPosition", _UrlKt.FRAGMENT_ENCODE_SET, "decodedString", "(II)Ljava/lang/String;", "takePeeked", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "wasUnquotedString", "()Z", "startPosition", "appendEsc", "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "source", "startPos", "appendHex", "(Ljava/lang/CharSequence;I)I", "fromHexChar", "start", "consumeBoolean", "(I)Z", "literalSuffix", _UrlKt.FRAGMENT_ENCODE_SET, "consumeBooleanLiteral", "(Ljava/lang/String;I)V", "ensureHaveChars", "()V", "position", "prefetchOrEof", "canConsumeValue", _UrlKt.FRAGMENT_ENCODE_SET, "consumeNextToken", "()B", "tryConsumeComma", _UrlKt.FRAGMENT_ENCODE_SET, "c", "isValidValueStart", "(C)Z", "expectEof", "expected", "(C)V", "unexpectedToken", "peekNextToken", "doConsume", "tryConsumeNull", "(Z)Z", "skipWhitespaces", "()I", "isLenient", "peekString", "(Z)Ljava/lang/String;", "endPos", "substring", "consumeKeyString", "consumeString", "(Ljava/lang/CharSequence;II)Ljava/lang/String;", "consumeStringLenientNotNull", "consumeStringLenient", "fromIndex", "toIndex", "appendRange", "(II)V", "allowLenientStrings", "skipElement", "(Z)V", "toString", PluginsConstants.Settings.KEY, "failOnUnknownKey", "(Ljava/lang/String;)V", "message", PluginsConstants.Settings.HINT, _UrlKt.FRAGMENT_ENCODE_SET, "fail", "(Ljava/lang/String;ILjava/lang/String;)Ljava/lang/Void;", _UrlKt.FRAGMENT_ENCODE_SET, "consumeNumericLiteral", "()J", "consumeBooleanLenient", "Lkotlinx/serialization/json/JsonConfiguration;", "getConfiguration$kotlinx_serialization_json", "()Lkotlinx/serialization/json/JsonConfiguration;", "I", "Lkotlinx/serialization/json/internal/JsonPath;", "path", "Lkotlinx/serialization/json/internal/JsonPath;", "peekedString", "Ljava/lang/String;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "escapedString", "Ljava/lang/StringBuilder;", "getEscapedString", "()Ljava/lang/StringBuilder;", "setEscapedString", "(Ljava/lang/StringBuilder;)V", "getSource", "()Ljava/lang/CharSequence;", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAbstractJsonLexer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n+ 2 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer$fail$1\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,759:1\n229#1,10:760\n751#1,5:771\n229#1,10:776\n229#1,10:788\n232#2:770\n232#2:786\n1#3:787\n*S KotlinDebug\n*F\n+ 1 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n*L\n209#1:760,10\n219#1:771,5\n226#1:776,10\n681#1:788,10\n209#1:770\n226#1:786\n*E\n"})
/* loaded from: classes.dex */
public abstract class AbstractJsonLexer {
    private final JsonConfiguration configuration;

    @JvmField
    public int currentPosition;
    private StringBuilder escapedString;

    @JvmField
    public final JsonPath path;
    private String peekedString;

    public AbstractJsonLexer(JsonConfiguration r2) {
        this.configuration = r2;
        this.path = new JsonPath(r2);
        this.escapedString = new StringBuilder();
    }

    public static final /* synthetic */ CharSequence access$getSource(AbstractJsonLexer r0) {
        return r0.getSource();
    }

    private final int appendEsc(int r15) {
        int r152 = prefetchOrEof(r15);
        if (r152 == (-1)) goto L14;
        int r2 = r152 + 1;
        char r153 = getSource().charAt(r152);
        if (r153 == 'u') goto L7;
        char r0 = AbstractJsonLexerKt.escapeToChar(r153);
        if (r0 == 0) goto L12;
        this.escapedString.append(r0);
        return r2;
    L12:
        fail$default(this, "Invalid escaped char '" + r153 + '\'', 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L7:
        return appendHex(getSource(), r2);
    L14:
        fail$default(this, "Expected escape sequence to continue, got EOF", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    private final int appendEscape(int r1, int r2) {
        appendRange(r1, r2);
        return appendEsc(r2 + 1);
    }

    private final int appendHex(CharSequence r7, int r8) {
        int r0 = r8 + 4;
        if (r0 < r7.length()) goto L10;
        this.currentPosition = r8;
        ensureHaveChars();
        if ((this.currentPosition + 4) < r7.length()) goto L7;
        fail$default(this, "Unexpected EOF during unicode escape", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L7:
        return appendHex(r7, this.currentPosition);
    L10:
        this.escapedString.append((char) ((((fromHexChar(r7, r8) << 12) + (fromHexChar(r7, r8 + 1) << 8)) + (fromHexChar(r7, r8 + 2) << 4)) + fromHexChar(r7, r8 + 3)));
        return r0;
    }

    private final boolean consumeBoolean(int r15) {
        int r152 = prefetchOrEof(r15);
        if (r152 < getSource().length()) goto L5;
    L16:
        fail$default(this, "EOF", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return false;
    L5:
        if (r152 == (-1)) goto L16;
        int r2 = r152 + 1;
        int r153 = getSource().charAt(r152) | ' ';
        if (r153 != 102) goto L9;
        consumeBooleanLiteral("alse", r2);
        return false;
    L9:
        if (r153 != 116) goto L12;
        consumeBooleanLiteral("rue", r2);
        return true;
    L12:
        fail$default(this, "Expected valid boolean literal prefix, but had '" + consumeStringLenient() + '\'', 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return false;
    }

    private final void consumeBooleanLiteral(String r13, int r14) {
        if ((getSource().length() - r14) < r13.length()) goto L13;
        int r0 = r13.length();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L11;
        if (r13.charAt(r1) != (getSource().charAt(r14 + r1) | ' ')) goto L9;
        r1 = r1 + 1;
        goto L5
    L9:
        fail$default(this, "Expected valid boolean literal prefix, but had '" + consumeStringLenient() + '\'', 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return;
    L11:
        this.currentPosition = r14 + r13.length();
        return;
    L13:
        fail$default(this, "Unexpected end of boolean literal", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
    }

    private static final double consumeNumericLiteral$calculateExponent(long r3, boolean r5) {
        if (r5 == true) goto L7;
        return Math.pow(10.0d, -r3);
    L7:
        if (r5 == true) goto L9;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return 0.0d;
    L9:
        return Math.pow(10.0d, r3);
    }

    private final String decodedString(int r1, int r2) {
        appendRange(r1, r2);
        String r12 = this.escapedString.toString();
        this.escapedString.setLength(0);
        return r12;
    }

    public static /* synthetic */ Void fail$default(AbstractJsonLexer r0, String r1, int r2, String r3, int r4, Object r5) {
        if (r5 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: fail");
        return null;
    L4:
        if ((r4 & 2) == 0) goto L7;
        r2 = r0.currentPosition;
    L7:
        if ((r4 & 4) == 0) goto L10;
        r3 = null;
    L10:
        return r0.fail(r1, r2, r3);
    }

    private final int fromHexChar(CharSequence r7, int r8) {
        char r72 = r7.charAt(r8);
        if ('0' > r72) goto L9;
        if (r72 >= ':') goto L9;
        return r72 - '0';
    L9:
        if ('a' > r72) goto L15;
        if (r72 >= 'g') goto L15;
        return r72 - 'W';
    L15:
        if ('A' <= r72) goto L17;
    L20:
        fail$default(this, "Invalid toHexChar char '" + r72 + "' in unicode escape", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L17:
        if (r72 >= 'G') goto L20;
        return r72 - '7';
    }

    private final String takePeeked() {
        String r0 = this.peekedString;
        this.peekedString = null;
        return r0;
    }

    public static /* synthetic */ boolean tryConsumeNull$default(AbstractJsonLexer r0, boolean r1, int r2, Object r3) {
        if (r3 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: tryConsumeNull");
        return false;
    L4:
        if ((r2 & 1) == 0) goto L7;
        r1 = true;
    L7:
        return r0.tryConsumeNull(r1);
    }

    private final boolean wasUnquotedString() {
        if (getSource().charAt(this.currentPosition - 1) == '\"') goto L5;
        return true;
    L5:
        return false;
    }

    public void appendRange(int r2, int r3) {
        this.escapedString.append(getSource(), r2, r3);
    }

    public abstract boolean canConsumeValue();

    public final boolean consumeBooleanLenient() {
        int r0 = skipWhitespaces();
        if (r0 != getSource().length()) goto L5;
        fail$default(this, "EOF", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return false;
    L5:
        if (getSource().charAt(r0) != '\"') goto L7;
        r0 = r0 + 1;
        boolean r1 = true;
    L8:
        boolean r02 = consumeBoolean(r0);
        if (r1 == true) goto L11;
        return r02;
    L11:
        if (this.currentPosition != getSource().length()) goto L13;
        fail$default(this, "EOF", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return false;
    L13:
        if (getSource().charAt(this.currentPosition) != '\"') goto L16;
        this.currentPosition++;
        return r02;
    L16:
        fail$default(this, "Expected closing quotation mark", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return false;
    L7:
        r1 = false;
        goto L8
    }

    public abstract String consumeKeyString();

    public abstract byte consumeNextToken();

    public abstract void consumeNextToken(char r1);

    public final long consumeNumericLiteral() {
        int r1 = prefetchOrEof(skipWhitespaces());
        long r6 = 0;
        if (r1 < getSource().length()) goto L5;
    L105:
        fail$default(this, "EOF", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L5:
        if (r1 == (-1)) goto L105;
        if (getSource().charAt(r1) != '\"') goto L13;
        r1 = r1 + 1;
        if (r1 == getSource().length()) goto L11;
        boolean r0 = true;
    L14:
        int r2 = r1;
        long r10 = 0;
        long r12 = 0;
        boolean r8 = false;
        boolean r9 = false;
    L15:
        boolean r14 = false;
    L17:
        if (r2 == getSource().length()) goto L64;
        char r15 = getSource().charAt(r2);
        long r16 = r6;
        if (r15 != 'e') goto L21;
    L22:
        if (r8 == false) goto L23;
    L29:
        if (r15 != '-') goto L36;
        if (r8 == false) goto L36;
        if (r2 == r1) goto L33;
        r2 = r2 + 1;
        r6 = r16;
        goto L15
    L33:
        fail$default(this, "Unexpected symbol '-' in numeric literal", r2, null, 4, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return r16;
    L36:
        if (r15 != '+') goto L42;
        if (r8 == false) goto L42;
        if (r2 == r1) goto L40;
        r2 = r2 + 1;
        r6 = r16;
    L25:
        r14 = true;
        goto L17
    L40:
        fail$default(this, "Unexpected symbol '+' in numeric literal", r2, null, 4, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return r16;
    L42:
        if (r15 == '-') goto L43;
        if (AbstractJsonLexerKt.charToTokenClass(r15) != 0) goto L65;
        int r4 = r2 + 1;
        int r7 = r15 - '0';
        if (r7 < 0) goto L62;
        if (r7 >= 10) goto L62;
        if (r8 == false) goto L57;
        r12 = (r12 * 10) + r7;
    L56:
        r2 = r4;
        r6 = r16;
        goto L17
    L57:
        r10 = (r10 * 10) - r7;
        if (r10 <= r16) goto L56;
        fail$default(this, "Numeric value overflow", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return r16;
    L62:
        fail$default(this, "Unexpected symbol '" + r15 + "' in numeric literal", r2, null, 4, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return r16;
    L65:
        if (r2 == r1) goto L67;
        boolean r42 = true;
    L68:
        if (r1 == r2) goto L103;
        if (r9 == true) goto L71;
    L72:
        if (r0 == false) goto L82;
        if (r42 == true) goto L75;
        fail$default(this, "EOF", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return r16;
    L75:
        if (getSource().charAt(r2) != '\"') goto L78;
        r2 = r2 + 1;
        goto L82
    L78:
        fail$default(this, "Expected closing quotation mark", r2, null, 4, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return r16;
    L82:
        this.currentPosition = r2;
        if (r8 == false) goto L95;
        double r13 = r10 * consumeNumericLiteral$calculateExponent(r12, r14);
        if (r13 <= 9.223372036854776E18d) goto L87;
    L93:
        fail$default(this, "Numeric value overflow", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return r16;
    L87:
        if (r13 < (-9.223372036854776E18d)) goto L93;
        if (Math.floor(r13) != r13) goto L91;
        r10 = (long) r13;
        goto L95
    L91:
        fail$default(this, "Can't convert " + r13 + " to Long", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return r16;
    L95:
        if (r9 == false) goto L98;
        return r10;
    L98:
        if (r10 != Long.MIN_VALUE) goto L100;
        fail$default(this, "Numeric value overflow", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return r16;
    L100:
        return -r10;
    L71:
        if (r1 != (r2 - 1)) goto L72;
    L103:
        fail$default(this, "Expected numeric literal", r2, null, 4, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return r16;
    L67:
        r42 = false;
        goto L68
    L43:
        if (r2 != r1) goto L45;
        r2 = r2 + 1;
        r6 = r16;
        r9 = true;
        goto L17
    L45:
        fail$default(this, "Unexpected symbol '-' in numeric literal", r2, null, 4, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return r16;
    L23:
        if (r2 == r1) goto L26;
        r2 = r2 + 1;
        r6 = r16;
        r8 = true;
        goto L25
    L26:
        fail$default(this, "Unexpected symbol '" + r15 + "' in numeric literal", r2, null, 4, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return r16;
    L21:
        if (r15 != 'E') goto L29;
    L64:
        r16 = r6;
        goto L65
    L11:
        fail$default(this, "EOF", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L13:
        r0 = false;
        goto L14
    }

    public final String consumeString() {
        if (this.peekedString == null) goto L7;
        return takePeeked();
    L7:
        return consumeKeyString();
    }

    public final String consumeString(CharSequence r11, int r12, int r13) {
        int r2 = r12;
        boolean r5 = false;
        char r4 = r11.charAt(r13);
        int r3 = r13;
    L4:
        if (r4 == '\"') goto L20;
        if (r4 != '\\') goto L12;
        r2 = prefetchOrEof(appendEscape(r2, r3));
        if (r2 == (-1)) goto L10;
    L9:
        r3 = r2;
        r5 = true;
    L19:
        r4 = r11.charAt(r3);
        goto L4
    L10:
        fail$default(this, "Unexpected EOF", r2, null, 4, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L12:
        r3 = r3 + 1;
        if (r3 < r11.length()) goto L19;
        appendRange(r2, r3);
        r2 = prefetchOrEof(r3);
        if (r2 != (-1)) goto L9;
        fail$default(this, "Unexpected EOF", r2, null, 4, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L20:
        if (r5 == true) goto L22;
        String r1 = substring(r2, r3);
    L23:
        this.currentPosition = r3 + 1;
        return r1;
    L22:
        r1 = decodedString(r2, r3);
        goto L23
    }

    public final String consumeStringLenient() {
        if (this.peekedString != null) goto L5;
        int r2 = skipWhitespaces();
        if (r2 < getSource().length()) goto L9;
    L33:
        fail$default(this, "EOF", r2, null, 4, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L9:
        if (r2 == (-1)) goto L33;
        byte r1 = AbstractJsonLexerKt.charToTokenClass(getSource().charAt(r2));
        if (r1 == 1) goto L13;
        if (r1 != 0) goto L31;
        boolean r4 = false;
    L17:
        if (AbstractJsonLexerKt.charToTokenClass(getSource().charAt(r2)) != 0) goto L25;
        r2 = r2 + 1;
        if (r2 < getSource().length()) goto L17;
        appendRange(this.currentPosition, r2);
        int r42 = prefetchOrEof(r2);
        if (r42 == (-1)) goto L22;
        r2 = r42;
        r4 = true;
        goto L17
    L22:
        this.currentPosition = r2;
        return decodedString(0, 0);
    L25:
        int r0 = this.currentPosition;
        if (r4 == true) goto L28;
        String r02 = substring(r0, r2);
    L29:
        this.currentPosition = r2;
        return r02;
    L28:
        r02 = decodedString(r0, r2);
        goto L29
    L31:
        fail$default(this, "Expected beginning of the string, but got " + getSource().charAt(r2), 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L13:
        return consumeString();
    L5:
        return takePeeked();
    }

    public final String consumeStringLenientNotNull() {
        String r0 = consumeStringLenient();
        if (Intrinsics.areEqual(r0, "null") == true) goto L5;
    L9:
        return r0;
    L5:
        if (wasUnquotedString() == false) goto L9;
        fail$default(this, "Unexpected 'null' value instead of string literal", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    public void ensureHaveChars() {
    }

    public final void expectEof() {
        if (consumeNextToken() != 10) goto L5;
        return;
    L5:
        fail$default(this, "Expected EOF after parsing, but had " + getSource().charAt(this.currentPosition - 1) + " instead", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
    }

    public final Void fail(String r8, int r9, String r10) {
        throw JsonExceptionsKt.decodingExceptionOf(this, r8, r9, this.path.getPath(), r10, getSource());
    }

    public final void failOnUnknownKey(String r9) {
        fail("Encountered an unknown key '" + r9 + '\'', StringsKt.lastIndexOf$default(substring(0, this.currentPosition), r9, 0, false, 6, null), "Use 'ignoreUnknownKeys = true' in 'Json {}' builder or '@JsonIgnoreUnknownKeys' annotation to ignore unknown keys.");
        throw new KotlinNothingValueException();
    }

    public final JsonConfiguration getConfiguration$kotlinx_serialization_json() {
        return this.configuration;
    }

    public abstract CharSequence getSource();

    public final boolean isValidValueStart(char r1) {
        if (r1 != ',') goto L5;
        return false;
    L5:
        if (r1 != ':') goto L7;
        return false;
    L7:
        if (r1 != ']') goto L9;
        return false;
    L9:
        if (r1 == '}') goto L16;
        return true;
    L16:
        return false;
    }

    public byte peekNextToken() {
        CharSequence r0 = getSource();
        int r1 = this.currentPosition;
    L3:
        int r12 = prefetchOrEof(r1);
        if (r12 == (-1)) goto L15;
        char r2 = r0.charAt(r12);
        if (r2 == '\t') goto L14;
        if (r2 == '\n') goto L14;
        if (r2 == '\r') goto L14;
        if (r2 == ' ') goto L14;
        this.currentPosition = r12;
        return AbstractJsonLexerKt.charToTokenClass(r2);
    L14:
        r1 = r12 + 1;
        goto L3
    L15:
        this.currentPosition = r12;
        return 10;
    }

    public final String peekString(boolean r4) {
        byte r0 = peekNextToken();
        if (r4 == false) goto L8;
        if (r0 == 1) goto L7;
        if (r0 == 0) goto L7;
        return null;
    L7:
        String r42 = consumeStringLenient();
    L11:
        this.peekedString = r42;
        return r42;
    L8:
        if (r0 == 1) goto L10;
        return null;
    L10:
        r42 = consumeString();
        goto L11
    }

    public abstract int prefetchOrEof(int r1);

    public final void skipElement(boolean r17) {
        ArrayList r0 = new ArrayList();
        byte r1 = peekNextToken();
        if (r1 == 8) goto L7;
        if (r1 == 6) goto L7;
        consumeStringLenient();
        return;
    L7:
        byte r12 = peekNextToken();
        if (r12 == 1) goto L9;
        if (r12 == 8) goto L34;
        if (r12 == 6) goto L34;
        if (r12 != 9) goto L23;
        if (((Number) CollectionsKt.last(r0)).byteValue() != 8) goto L20;
        CollectionsKt.removeLast(r0);
    L35:
        consumeNextToken();
        if (r0.size() != 0) goto L7;
        return;
    L20:
        fail$default(this, "found ] instead of }", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return;
    L23:
        if (r12 != 7) goto L30;
        if (((Number) CollectionsKt.last(r0)).byteValue() != 6) goto L27;
        CollectionsKt.removeLast(r0);
        goto L35
    L27:
        fail$default(this, "found } instead of ]", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return;
    L30:
        if (r12 != 10) goto L35;
        fail$default(this, "Unexpected end of input due to malformed JSON during ignoring unknown keys", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return;
    L34:
        r0.add(Byte.valueOf(r12));
        goto L35
    L9:
        if (r17 == true) goto L10;
        consumeKeyString();
        goto L7
    L10:
        consumeStringLenient();
        goto L7
    }

    public abstract int skipWhitespaces();

    public String substring(int r1, int r2) {
        return getSource().subSequence(r1, r2).toString();
    }

    public String toString() {
        return "JsonReader(source='" + getSource() + "', currentPosition=" + this.currentPosition + ')';
    }

    public final boolean tryConsumeComma() {
        int r0 = skipWhitespaces();
        CharSequence r1 = getSource();
        if (r0 < r1.length()) goto L5;
    L11:
        return false;
    L5:
        if (r0 == (-1)) goto L11;
        if (r1.charAt(r0) != ',') goto L11;
        this.currentPosition++;
        return true;
    }

    public final boolean tryConsumeNull(boolean r9) {
        int r0 = prefetchOrEof(skipWhitespaces());
        int r1 = getSource().length() - r0;
        if (r1 >= 4) goto L5;
    L21:
        return false;
    L5:
        if (r0 == (-1)) goto L21;
        int r4 = 0;
    L8:
        if (r4 >= 4) goto L13;
        if ("null".charAt(r4) != getSource().charAt(r0 + r4)) goto L11;
        r4 = r4 + 1;
        goto L8
    L11:
        return false;
    L13:
        if (r1 > 4) goto L15;
    L17:
        if (r9 == false) goto L24;
        this.currentPosition = r0 + 4;
        return true;
    L24:
        return true;
    L15:
        if (AbstractJsonLexerKt.charToTokenClass(getSource().charAt(r0 + 4)) != 0) goto L17;
        return false;
    }

    public final void unexpectedToken(char r9) {
        int r1 = this.currentPosition;
        if (r1 > 0) goto L5;
    L16:
        String r92 = AbstractJsonLexerKt.tokenDescription(AbstractJsonLexerKt.charToTokenClass(r9));
        int r0 = this.currentPosition;
        if (r0 <= 0) goto L19;
        int r4 = r0 - 1;
    L21:
        if (r0 == access$getSource(this).length()) goto L25;
        if (r4 < 0) goto L25;
        String r02 = String.valueOf(access$getSource(this).charAt(r4));
    L26:
        fail$default(this, "Expected " + r92 + ", but had '" + r02 + "' instead", r4, null, 4, null);
        throw new KotlinNothingValueException();
    L25:
        r02 = "EOF";
        goto L26
    L19:
        r4 = r0;
        goto L21
    L5:
        if (r9 != '\"') goto L16;
        this.currentPosition = r1 - 1;     // Catch: Throwable -> L13
        String r03 = consumeStringLenient();     // Catch: Throwable -> L13
        this.currentPosition = r1;
        if (Intrinsics.areEqual(r03, "null") == false) goto L16;
        fail("Expected string literal but 'null' literal was found", this.currentPosition - 1, "Use 'coerceInputValues = true' in 'Json {}' builder to coerce nulls if property has a default value.");
        throw new KotlinNothingValueException();
    L13:
        th = move-exception;
        this.currentPosition = r1;
        throw th;
    }
}
