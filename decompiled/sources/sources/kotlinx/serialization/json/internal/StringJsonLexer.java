package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import kotlinx.serialization.json.JsonConfiguration;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0010\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\r\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\r\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0003\u001a\u00020\u00028\u0014X\u0094\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u0019¨\u0006\u001c"}, d2 = {"Lkotlinx/serialization/json/internal/StringJsonLexer;", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", _UrlKt.FRAGMENT_ENCODE_SET, "source", "Lkotlinx/serialization/json/JsonConfiguration;", "configuration", "<init>", "(Ljava/lang/String;Lkotlinx/serialization/json/JsonConfiguration;)V", _UrlKt.FRAGMENT_ENCODE_SET, "position", "prefetchOrEof", "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "consumeNextToken", "()B", _UrlKt.FRAGMENT_ENCODE_SET, "canConsumeValue", "()Z", "skipWhitespaces", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "expected", _UrlKt.FRAGMENT_ENCODE_SET, "(C)V", "consumeKeyString", "()Ljava/lang/String;", "Ljava/lang/String;", "getSource", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nStringJsonLexer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringJsonLexer.kt\nkotlinx/serialization/json/internal/StringJsonLexer\n+ 2 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n+ 3 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer$fail$1\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,133:1\n160#2:134\n160#2:135\n160#2:136\n160#2:137\n229#2,10:138\n232#3:148\n1915#4,2:149\n*S KotlinDebug\n*F\n+ 1 StringJsonLexer.kt\nkotlinx/serialization/json/internal/StringJsonLexer\n*L\n28#1:134\n43#1:135\n62#1:136\n78#1:137\n100#1:138,10\n100#1:148\n114#1:149,2\n*E\n"})
/* loaded from: classes.dex */
public class StringJsonLexer extends AbstractJsonLexer {
    private final String source;

    public StringJsonLexer(String r1, JsonConfiguration r2) {
        super(r2);
        this.source = r1;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public boolean canConsumeValue() {
        int r0 = this.currentPosition;
        if (r0 != (-1)) goto L5;
        return false;
    L5:
        String r1 = getSource();
    L7:
        if (r0 >= r1.length()) goto L20;
        char r3 = r1.charAt(r0);
        if (r3 == ' ') goto L19;
        if (r3 == '\n') goto L19;
        if (r3 == '\r') goto L19;
        if (r3 == '\t') goto L19;
        this.currentPosition = r0;
        return isValidValueStart(r3);
    L19:
        r0 = r0 + 1;
        goto L7
    L20:
        this.currentPosition = r0;
        return false;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public String consumeKeyString() {
        consumeNextToken(Typography.quote);
        int r3 = this.currentPosition;
        int r0 = StringsKt.indexOf$default(getSource(), Typography.quote, r3, false, 4, null);
        if (r0 != (-1)) goto L12;
        consumeStringLenient();
        String r02 = AbstractJsonLexerKt.tokenDescription((byte) 1);
        int r32 = this.currentPosition;
        if (r32 == AbstractJsonLexer.access$getSource(this).length()) goto L9;
        if (r32 < 0) goto L9;
        String r1 = String.valueOf(AbstractJsonLexer.access$getSource(this).charAt(r32));
    L10:
        AbstractJsonLexer.fail$default(this, "Expected " + r02 + ", but had '" + r1 + "' instead", r32, null, 4, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L9:
        r1 = "EOF";
        goto L10
    L12:
        int r7 = r3;
    L13:
        if (r7 >= r0) goto L19;
        if (getSource().charAt(r7) == '\\') goto L17;
        r7 = r7 + 1;
        goto L13
    L17:
        return consumeString(getSource(), this.currentPosition, r7);
    L19:
        this.currentPosition = r0 + 1;
        return getSource().substring(r3, r0);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public byte consumeNextToken() {
        String r0 = getSource();
        int r1 = this.currentPosition;
    L4:
        if (r1 == (-1)) goto L18;
        if (r1 >= r0.length()) goto L18;
        int r2 = r1 + 1;
        char r12 = r0.charAt(r1);
        if (r12 == ' ') goto L17;
        if (r12 == '\n') goto L17;
        if (r12 == '\r') goto L17;
        if (r12 == '\t') goto L17;
        this.currentPosition = r2;
        return AbstractJsonLexerKt.charToTokenClass(r12);
    L17:
        r1 = r2;
    L18:
        this.currentPosition = r0.length();
        return 10;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public void consumeNextToken(char r6) {
        if (this.currentPosition != (-1)) goto L5;
        unexpectedToken(r6);
    L5:
        String r0 = getSource();
        int r2 = this.currentPosition;
    L7:
        if (r2 >= r0.length()) goto L22;
        int r3 = r2 + 1;
        char r22 = r0.charAt(r2);
        if (r22 == ' ') goto L21;
        if (r22 == '\n') goto L21;
        if (r22 == '\r') goto L21;
        if (r22 == '\t') goto L21;
        this.currentPosition = r3;
        if (r22 == r6) goto L19;
        unexpectedToken(r6);
        goto L21
    L19:
        return;
    L21:
        r2 = r3;
        goto L7
    L22:
        this.currentPosition = -1;
        unexpectedToken(r6);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public /* bridge */ /* synthetic */ CharSequence getSource() {
        return getSource();
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public String getSource() {
        return this.source;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public int prefetchOrEof(int r1) {
        if (r1 >= getSource().length()) goto L5;
        return r1;
    L5:
        return -1;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public int skipWhitespaces() {
        int r0 = this.currentPosition;
        if (r0 != (-1)) goto L5;
        return r0;
    L5:
        String r1 = getSource();
    L7:
        if (r0 >= r1.length()) goto L17;
        char r2 = r1.charAt(r0);
        if (r2 == ' ') goto L16;
        if (r2 == '\n') goto L16;
        if (r2 == '\r') goto L16;
        if (r2 != '\t') goto L17;
    L16:
        r0 = r0 + 1;
    L17:
        this.currentPosition = r0;
        return r0;
    }
}
