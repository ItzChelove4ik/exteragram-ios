package kotlinx.serialization.json.internal;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.JsonDecodingException;
import kotlinx.serialization.json.JsonEncodingException;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0004\n\u0002\b\f\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a=\u0010\u0003\u001a\u00020\u0002*\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0004\b\u0003\u0010\f\u001a!\u0010\r\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a=\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\b\u0010\b\u001a\u0004\u0018\u00010\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\u0000H\u0002¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001d\u0010\u0013\u001a\u00020\u0012*\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u0017\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u001b\u0010\u001c\u001a\u00020\u0012*\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u001aH\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a#\u0010 \u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001a2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0000H\u0000¢\u0006\u0004\b \u0010!\u001a!\u0010\"\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0000H\u0002¢\u0006\u0004\b\"\u0010#\u001a\u001d\u0010$\u001a\u00020\n*\u00020\n2\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0000¢\u0006\u0004\b$\u0010%¨\u0006&"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "shortMessage", "Lkotlinx/serialization/json/JsonDecodingException;", "decodingExceptionOf", "(Ljava/lang/String;)Lkotlinx/serialization/json/JsonDecodingException;", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", _UrlKt.FRAGMENT_ENCODE_SET, "offset", "path", PluginsConstants.Settings.HINT, _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.TYPE_INPUT, "(Lkotlinx/serialization/json/internal/AbstractJsonLexer;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/CharSequence;)Lkotlinx/serialization/json/JsonDecodingException;", "formatEncodingException", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "formatDecodingException", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "entity", _UrlKt.FRAGMENT_ENCODE_SET, "invalidTrailingComma", "(Lkotlinx/serialization/json/internal/AbstractJsonLexer;Ljava/lang/String;)Ljava/lang/Void;", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "keyDescriptor", "Lkotlinx/serialization/json/JsonEncodingException;", "InvalidKeyKindException", "(Lkotlinx/serialization/descriptors/SerialDescriptor;)Lkotlinx/serialization/json/JsonEncodingException;", _UrlKt.FRAGMENT_ENCODE_SET, "result", "throwInvalidFloatingPointDecoded", "(Lkotlinx/serialization/json/internal/AbstractJsonLexer;Ljava/lang/Number;)Ljava/lang/Void;", "value", PluginsConstants.Settings.KEY, "InvalidFloatingPointEncoded", "(Ljava/lang/Number;Ljava/lang/String;)Lkotlinx/serialization/json/JsonEncodingException;", "nonFiniteFpMessage", "(Ljava/lang/Number;Ljava/lang/String;)Ljava/lang/String;", "minify", "(Ljava/lang/CharSequence;I)Ljava/lang/CharSequence;", "kotlinx-serialization-json"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nJsonExceptions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonExceptions.kt\nkotlinx/serialization/json/internal/JsonExceptionsKt\n*L\n1#1,136:1\n60#1:137\n60#1:138\n21#1,7:139\n60#1:146\n28#1,7:147\n*S KotlinDebug\n*F\n+ 1 JsonExceptions.kt\nkotlinx/serialization/json/internal/JsonExceptionsKt\n*L\n27#1:137\n47#1:138\n116#1:139,7\n116#1:146\n116#1:147,7\n*E\n"})
/* loaded from: classes5.dex */
public abstract class JsonExceptionsKt {
    public static final JsonEncodingException InvalidFloatingPointEncoded(Number r6, String r7) {
        return new JsonEncodingException(nonFiniteFpMessage(r6, r7), null, "It is possible to deserialize them using 'JsonBuilder.allowSpecialFloatingPointValues = true'", 2, null);
    }

    public static /* synthetic */ JsonEncodingException InvalidFloatingPointEncoded$default(Number r0, String r1, int r2, Object r3) {
        if ((r2 & 2) == 0) goto L6;
        r1 = null;
    L6:
        return InvalidFloatingPointEncoded(r0, r1);
    }

    public static final JsonEncodingException InvalidKeyKindException(SerialDescriptor r3) {
        return new JsonEncodingException("Value of type '" + r3.getSerialName() + "' can't be used in JSON as a key in the map. It should have either primitive or enum kind, but its kind is '" + r3.getKind() + '\'', r3.getSerialName(), "Use 'allowStructuredMapKeys = true' in 'Json {}' builder to convert such maps to [key1, value1, key2, value2,...] arrays.");
    }

    public static final JsonDecodingException decodingExceptionOf(String r7) {
        return new JsonDecodingException(formatDecodingException(-1, r7, null, null, null), r7, -1, null, null, null);
    }

    public static final JsonDecodingException decodingExceptionOf(AbstractJsonLexer r7, String r8, int r9, String r10, String r11, CharSequence r12) {
        if (r7.getConfiguration$kotlinx_serialization_json().getExceptionsWithDebugInfo() == false) goto L6;
        String r72 = minify(r12, r9).toString();
    L5:
        String r5 = r72;
        return new JsonDecodingException(formatDecodingException(r9, r8, r10, r11, r5), r8, r9, r10, r5, r11);
    L6:
        r72 = null;
        goto L5
    }

    private static final String formatDecodingException(int r3, String r4, String r5, String r6, String r7) {
        StringBuilder r0 = new StringBuilder();
        if (r3 < 0) goto L5;
        r0.append("Unexpected JSON token at offset " + r3 + ": ");
    L5:
        r0.append(r4);
        if (r5 != null) goto L8;
    L11:
        if (r6 != null) goto L13;
    L16:
        if (r7 == null) goto L19;
        r0.append("\nJSON input: ");
        r0.append(r7);
    L19:
        return r0.toString();
    L13:
        if (StringsKt.isBlank(r6) == true) goto L16;
        r0.append("\n" + r6);
        goto L16
    L8:
        if (StringsKt.isBlank(r5) == true) goto L11;
        r0.append(" at path: ");
        r0.append(r5);
        goto L11
    }

    public static final String formatEncodingException(String r2, String r3) {
        StringBuilder r0 = new StringBuilder();
        r0.append(r2);
        if (r3 != null) goto L5;
    L8:
        String r22 = _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        r0.append(r22);
        return r0.toString();
    L5:
        if (StringsKt.isBlank(r3) == true) goto L8;
        r22 = "\n" + r3;
        goto L9
    }

    public static final Void invalidTrailingComma(AbstractJsonLexer r2, String r3) {
        r2.fail("Trailing comma before the end of JSON " + r3, r2.currentPosition - 1, "Trailing commas are non-complaint JSON and not allowed by default. Use 'allowTrailingComma = true' in 'Json {}' builder to support them.");
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ Void invalidTrailingComma$default(AbstractJsonLexer r0, String r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = "object";
    L6:
        return invalidTrailingComma(r0, r1);
    }

    public static final CharSequence minify(CharSequence r5, int r6) {
        if (r5.length() < 200) goto L9;
        String r1 = ".....";
        if (r6 != (-1)) goto L12;
        int r62 = r5.length() - 60;
        if (r62 <= 0) goto L9;
        return "....." + r5.subSequence(r62, r5.length()).toString();
    L12:
        int r0 = r6 - 30;
        int r63 = r6 + 30;
        if (r0 > 0) goto L15;
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L17:
        if (r63 < r5.length()) goto L20;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L20:
        return r3 + r5.subSequence(RangesKt.coerceAtLeast(r0, 0), RangesKt.coerceAtMost(r63, r5.length())).toString() + r1;
    L15:
        r3 = ".....";
    L9:
        return r5;
    }

    private static final String nonFiniteFpMessage(Number r3, String r4) {
        StringBuilder r0 = new StringBuilder("Unexpected special floating-point value ");
        r0.append(r3);
        String r32 = ". ";
        if (r4 == null) goto L5;
        r32 = " with key " + r4 + ". ";
    L5:
        r0.append(r32);
        r0.append("By default, non-finite floating point values are prohibited because they do not conform JSON specification.");
        return r0.toString();
    }

    public static final Void throwInvalidFloatingPointDecoded(AbstractJsonLexer r7, Number r8) {
        AbstractJsonLexer.fail$default(r7, nonFiniteFpMessage(r8, null), 0, "It is possible to deserialize them using 'JsonBuilder.allowSpecialFloatingPointValues = true'", 2, null);
        throw new KotlinNothingValueException();
    }
}
