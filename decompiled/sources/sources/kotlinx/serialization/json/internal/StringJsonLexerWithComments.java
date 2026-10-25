package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import kotlinx.serialization.json.JsonConfiguration;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004J\n\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004J\u0012\u0010\b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004J\n\u0010\u000f\u001a\u00020\tH\u0096\u0080\u0004J\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004¨\u0006\u0012"}, d2 = {"Lkotlinx/serialization/json/internal/StringJsonLexerWithComments;", "Lkotlinx/serialization/json/internal/StringJsonLexer;", "source", _UrlKt.FRAGMENT_ENCODE_SET, "configuration", "Lkotlinx/serialization/json/JsonConfiguration;", "<init>", "(Ljava/lang/String;Lkotlinx/serialization/json/JsonConfiguration;)V", "consumeNextToken", _UrlKt.FRAGMENT_ENCODE_SET, "canConsumeValue", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "expected", _UrlKt.FRAGMENT_ENCODE_SET, "peekNextToken", "skipWhitespaces", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCommentLexers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CommentLexers.kt\nkotlinx/serialization/json/internal/StringJsonLexerWithComments\n+ 2 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n*L\n1#1,221:1\n160#2:222\n*S KotlinDebug\n*F\n+ 1 CommentLexers.kt\nkotlinx/serialization/json/internal/StringJsonLexerWithComments\n*L\n68#1:222\n*E\n"})
/* loaded from: classes5.dex */
public final class StringJsonLexerWithComments extends StringJsonLexer {
    public StringJsonLexerWithComments(String r1, JsonConfiguration r2) {
        super(r1, r2);
    }

    @Override // kotlinx.serialization.json.internal.StringJsonLexer, kotlinx.serialization.json.internal.AbstractJsonLexer
    public boolean canConsumeValue() {
        int r0 = skipWhitespaces();
        if (r0 < getSource().length()) goto L5;
        return false;
    L5:
        if (r0 != (-1)) goto L8;
        return false;
    L8:
        return isValidValueStart(getSource().charAt(r0));
    }

    @Override // kotlinx.serialization.json.internal.StringJsonLexer, kotlinx.serialization.json.internal.AbstractJsonLexer
    public byte consumeNextToken() {
        String r0 = getSource();
        int r1 = skipWhitespaces();
        if (r1 < r0.length()) goto L5;
        return 10;
    L5:
        if (r1 == (-1)) goto L11;
        this.currentPosition = r1 + 1;
        return AbstractJsonLexerKt.charToTokenClass(r0.charAt(r1));
    L11:
        return 10;
    }

    @Override // kotlinx.serialization.json.internal.StringJsonLexer, kotlinx.serialization.json.internal.AbstractJsonLexer
    public void consumeNextToken(char r5) {
        String r0 = getSource();
        int r1 = skipWhitespaces();
        if (r1 >= r0.length()) goto L5;
        if (r1 == (-1)) goto L5;
    L6:
        char r02 = r0.charAt(r1);
        this.currentPosition = r1 + 1;
        if (r02 != r5) goto L9;
        return;
    L9:
        unexpectedToken(r5);
        return;
    L5:
        this.currentPosition = -1;
        unexpectedToken(r5);
        goto L6
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public byte peekNextToken() {
        String r0 = getSource();
        int r1 = skipWhitespaces();
        if (r1 < r0.length()) goto L5;
        return 10;
    L5:
        if (r1 == (-1)) goto L11;
        this.currentPosition = r1;
        return AbstractJsonLexerKt.charToTokenClass(r0.charAt(r1));
    L11:
        return 10;
    }

    @Override // kotlinx.serialization.json.internal.StringJsonLexer, kotlinx.serialization.json.internal.AbstractJsonLexer
    public int skipWhitespaces() {
        int r0 = this.currentPosition;
        if (r0 != (-1)) goto L5;
        return r0;
    L5:
        String r2 = getSource();
    L7:
        if (r0 >= r2.length()) goto L34;
        char r3 = r2.charAt(r0);
        if (r3 == ' ') goto L33;
        if (r3 == '\n') goto L33;
        if (r3 == '\r') goto L33;
        if (r3 == '\t') goto L33;
        if (r3 != '/') goto L34;
        int r32 = r0 + 1;
        if (r32 >= r2.length()) goto L34;
        char r33 = r2.charAt(r32);
        if (r33 != '*') goto L23;
        int r02 = StringsKt.indexOf$default(r2, "*/", r0 + 2, false, 4, null);
        if (r02 == (-1)) goto L31;
        r0 = r02 + 2;
        goto L7
    L31:
        this.currentPosition = r2.length();
        AbstractJsonLexer.fail$default(this, "Expected end of the block comment: \"*/\", but had EOF instead", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L23:
        if (r33 != '/') goto L34;
        r0 = StringsKt.indexOf$default(r2, '\n', r0 + 2, false, 4, null);
        if (r0 != (-1)) goto L33;
        r0 = r2.length();
    L33:
        r0 = r0 + 1;
    L34:
        this.currentPosition = r0;
        return r0;
    }
}
