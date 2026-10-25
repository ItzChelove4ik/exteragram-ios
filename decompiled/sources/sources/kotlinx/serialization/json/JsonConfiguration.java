package kotlinx.serialization.json;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b'\u0018\u00002\u00020\u0001B¿\u0001\b\u0000\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0002\u0012\b\b\u0002\u0010\b\u001a\u00020\u0002\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0002\u0012\b\b\u0002\u0010\f\u001a\u00020\u0002\u0012\b\b\u0002\u0010\r\u001a\u00020\t\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\tH\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001c\u001a\u0004\b\u001f\u0010\u001eR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b\u0005\u0010\u001eR\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001c\u001a\u0004\b \u0010\u001eR\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001c\u001a\u0004\b!\u0010\u001eR\u0017\u0010\b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\u001c\u001a\u0004\b\"\u0010\u001eR\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010#\u001a\u0004\b$\u0010\u001bR\u0017\u0010\u000b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u001c\u001a\u0004\b%\u0010\u001eR\u0017\u0010\f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\f\u0010\u001c\u001a\u0004\b&\u0010\u001eR\u0017\u0010\r\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\r\u0010#\u001a\u0004\b'\u0010\u001bR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u001c\u001a\u0004\b(\u0010\u001eR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u001c\u001a\u0004\b)\u0010\u001eR\u0017\u0010\u0012\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u001c\u001a\u0004\b*\u0010\u001eR\u0017\u0010\u0013\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u001c\u001a\u0004\b+\u0010\u001eR\u0017\u0010\u0014\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u001c\u001a\u0004\b,\u0010\u001eR(\u0010\u0016\u001a\u00020\u00158\u0006@\u0007X\u0087\u000e¢\u0006\u0018\n\u0004\b\u0016\u0010-\u0012\u0004\b2\u00103\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R(\u0010\u0017\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b\u0017\u0010\u001c\u0012\u0004\b7\u00103\u001a\u0004\b4\u0010\u001e\"\u0004\b5\u00106R\"\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0011\u00108\u0012\u0004\b;\u00103\u001a\u0004\b9\u0010:¨\u0006<"}, d2 = {"Lkotlinx/serialization/json/JsonConfiguration;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "encodeDefaults", "ignoreUnknownKeys", "isLenient", "allowStructuredMapKeys", "prettyPrint", "explicitNulls", _UrlKt.FRAGMENT_ENCODE_SET, "prettyPrintIndent", "coerceInputValues", "useArrayPolymorphism", "classDiscriminator", "allowSpecialFloatingPointValues", "useAlternativeNames", "Lkotlinx/serialization/json/JsonNamingStrategy;", "namingStrategy", "decodeEnumsCaseInsensitive", "allowTrailingComma", "allowComments", "Lkotlinx/serialization/json/ClassDiscriminatorMode;", "classDiscriminatorMode", "exceptionsWithDebugInfo", "<init>", "(ZZZZZZLjava/lang/String;ZZLjava/lang/String;ZZLkotlinx/serialization/json/JsonNamingStrategy;ZZZLkotlinx/serialization/json/ClassDiscriminatorMode;Z)V", "toString", "()Ljava/lang/String;", "Z", "getEncodeDefaults", "()Z", "getIgnoreUnknownKeys", "getAllowStructuredMapKeys", "getPrettyPrint", "getExplicitNulls", "Ljava/lang/String;", "getPrettyPrintIndent", "getCoerceInputValues", "getUseArrayPolymorphism", "getClassDiscriminator", "getAllowSpecialFloatingPointValues", "getUseAlternativeNames", "getDecodeEnumsCaseInsensitive", "getAllowTrailingComma", "getAllowComments", "Lkotlinx/serialization/json/ClassDiscriminatorMode;", "getClassDiscriminatorMode", "()Lkotlinx/serialization/json/ClassDiscriminatorMode;", "setClassDiscriminatorMode", "(Lkotlinx/serialization/json/ClassDiscriminatorMode;)V", "getClassDiscriminatorMode$annotations", "()V", "getExceptionsWithDebugInfo", "setExceptionsWithDebugInfo", "(Z)V", "getExceptionsWithDebugInfo$annotations", "Lkotlinx/serialization/json/JsonNamingStrategy;", "getNamingStrategy", "()Lkotlinx/serialization/json/JsonNamingStrategy;", "getNamingStrategy$annotations", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public final class JsonConfiguration {
    private final boolean allowComments;
    private final boolean allowSpecialFloatingPointValues;
    private final boolean allowStructuredMapKeys;
    private final boolean allowTrailingComma;
    private final String classDiscriminator;
    private ClassDiscriminatorMode classDiscriminatorMode;
    private final boolean coerceInputValues;
    private final boolean decodeEnumsCaseInsensitive;
    private final boolean encodeDefaults;
    private boolean exceptionsWithDebugInfo;
    private final boolean explicitNulls;
    private final boolean ignoreUnknownKeys;
    private final boolean isLenient;
    private final boolean prettyPrint;
    private final String prettyPrintIndent;
    private final boolean useAlternativeNames;
    private final boolean useArrayPolymorphism;

    public JsonConfiguration(boolean r1, boolean r2, boolean r3, boolean r4, boolean r5, boolean r6, String r7, boolean r8, boolean r9, String r10, boolean r11, boolean r12, JsonNamingStrategy r13, boolean r14, boolean r15, boolean r16, ClassDiscriminatorMode r17, boolean r18) {
        this.encodeDefaults = r1;
        this.ignoreUnknownKeys = r2;
        this.isLenient = r3;
        this.allowStructuredMapKeys = r4;
        this.prettyPrint = r5;
        this.explicitNulls = r6;
        this.prettyPrintIndent = r7;
        this.coerceInputValues = r8;
        this.useArrayPolymorphism = r9;
        this.classDiscriminator = r10;
        this.allowSpecialFloatingPointValues = r11;
        this.useAlternativeNames = r12;
        this.decodeEnumsCaseInsensitive = r14;
        this.allowTrailingComma = r15;
        this.allowComments = r16;
        this.classDiscriminatorMode = r17;
        this.exceptionsWithDebugInfo = r18;
    }

    public /* synthetic */ JsonConfiguration(boolean r20, boolean r21, boolean r22, boolean r23, boolean r24, boolean r25, String r26, boolean r27, boolean r28, String r29, boolean r30, boolean r31, JsonNamingStrategy r32, boolean r33, boolean r34, boolean r35, ClassDiscriminatorMode r36, boolean r37, int r38, DefaultConstructorMarker r39) {
        if ((r38 & 1) == 0) goto L5;
        boolean r1 = false;
    L7:
        if ((r38 & 2) == 0) goto L9;
        boolean r3 = false;
    L11:
        if ((r38 & 4) == 0) goto L13;
        boolean r4 = false;
    L15:
        if ((r38 & 8) == 0) goto L17;
        boolean r5 = false;
    L19:
        if ((r38 & 16) == 0) goto L21;
        boolean r6 = false;
    L23:
        if ((r38 & 32) == 0) goto L25;
        boolean r7 = true;
    L27:
        if ((r38 & 64) == 0) goto L29;
        String r9 = "    ";
    L31:
        if ((r38 & 128) == 0) goto L33;
        boolean r10 = false;
    L35:
        if ((r38 & 256) == 0) goto L37;
        boolean r11 = false;
    L39:
        if ((r38 & 512) == 0) goto L41;
        String r12 = "type";
    L43:
        if ((r38 & 1024) == 0) goto L45;
        boolean r13 = false;
    L47:
        if ((r38 & 2048) == 0) goto L49;
        boolean r14 = true;
    L51:
        if ((r38 & 4096) == 0) goto L53;
        JsonNamingStrategy r15 = null;
    L55:
        if ((r38 & 8192) == 0) goto L57;
        boolean r2 = false;
    L59:
        if ((r38 & 16384) == 0) goto L61;
        boolean r8 = false;
    L63:
        if ((r38 & 32768) == 0) goto L65;
        boolean r16 = false;
    L67:
        if ((r38 & 65536) == 0) goto L69;
        ClassDiscriminatorMode r17 = ClassDiscriminatorMode.POLYMORPHIC;
    L71:
        if ((r38 & 131072) == 0) goto L74;
        boolean r382 = true;
    L75:
        this(r1, r3, r4, r5, r6, r7, r9, r10, r11, r12, r13, r14, r15, r2, r8, r16, r17, r382);
        return;
    L74:
        r382 = r37;
        goto L75
    L69:
        r17 = r36;
        goto L71
    L65:
        r16 = r35;
        goto L67
    L61:
        r8 = r34;
        goto L63
    L57:
        r2 = r33;
        goto L59
    L53:
        r15 = r32;
        goto L55
    L49:
        r14 = r31;
        goto L51
    L45:
        r13 = r30;
        goto L47
    L41:
        r12 = r29;
        goto L43
    L37:
        r11 = r28;
        goto L39
    L33:
        r10 = r27;
        goto L35
    L29:
        r9 = r26;
        goto L31
    L25:
        r7 = r25;
        goto L27
    L21:
        r6 = r24;
        goto L23
    L17:
        r5 = r23;
        goto L19
    L13:
        r4 = r22;
        goto L15
    L9:
        r3 = r21;
        goto L11
    L5:
        r1 = r20;
        goto L7
    }

    public final boolean getAllowComments() {
        return this.allowComments;
    }

    public final boolean getAllowSpecialFloatingPointValues() {
        return this.allowSpecialFloatingPointValues;
    }

    public final boolean getAllowStructuredMapKeys() {
        return this.allowStructuredMapKeys;
    }

    public final boolean getAllowTrailingComma() {
        return this.allowTrailingComma;
    }

    public final String getClassDiscriminator() {
        return this.classDiscriminator;
    }

    public final ClassDiscriminatorMode getClassDiscriminatorMode() {
        return this.classDiscriminatorMode;
    }

    public final boolean getCoerceInputValues() {
        return this.coerceInputValues;
    }

    public final boolean getDecodeEnumsCaseInsensitive() {
        return this.decodeEnumsCaseInsensitive;
    }

    public final boolean getEncodeDefaults() {
        return this.encodeDefaults;
    }

    public final boolean getExceptionsWithDebugInfo() {
        return this.exceptionsWithDebugInfo;
    }

    public final boolean getExplicitNulls() {
        return this.explicitNulls;
    }

    public final boolean getIgnoreUnknownKeys() {
        return this.ignoreUnknownKeys;
    }

    public final JsonNamingStrategy getNamingStrategy() {
        return null;
    }

    public final boolean getPrettyPrint() {
        return this.prettyPrint;
    }

    public final String getPrettyPrintIndent() {
        return this.prettyPrintIndent;
    }

    public final boolean getUseAlternativeNames() {
        return this.useAlternativeNames;
    }

    public final boolean getUseArrayPolymorphism() {
        return this.useArrayPolymorphism;
    }

    public final boolean isLenient() {
        return this.isLenient;
    }

    public String toString() {
        return "JsonConfiguration(encodeDefaults=" + this.encodeDefaults + ", ignoreUnknownKeys=" + this.ignoreUnknownKeys + ", isLenient=" + this.isLenient + ", allowStructuredMapKeys=" + this.allowStructuredMapKeys + ", prettyPrint=" + this.prettyPrint + ", explicitNulls=" + this.explicitNulls + ", prettyPrintIndent='" + this.prettyPrintIndent + "', coerceInputValues=" + this.coerceInputValues + ", useArrayPolymorphism=" + this.useArrayPolymorphism + ", classDiscriminator='" + this.classDiscriminator + "', allowSpecialFloatingPointValues=" + this.allowSpecialFloatingPointValues + ", useAlternativeNames=" + this.useAlternativeNames + ", namingStrategy=null, decodeEnumsCaseInsensitive=" + this.decodeEnumsCaseInsensitive + ", allowTrailingComma=" + this.allowTrailingComma + ", allowComments=" + this.allowComments + ", classDiscriminatorMode=" + this.classDiscriminatorMode + ", exceptionsWithDebugInfo=" + this.exceptionsWithDebugInfo + ')';
    }
}
