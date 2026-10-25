package kotlinx.serialization.json;

import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.modules.SerializersModule;
import okhttp3.internal.url._UrlKt;
import okio.Options$Companion$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\t\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u0007\u0010\bR\"\u0010\u000b\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\"\u0010\u0011\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\"\u0010\u0014\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\f\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\"\u0010\u0017\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\f\u001a\u0004\b\u0017\u0010\u000e\"\u0004\b\u0018\u0010\u0010R\"\u0010\u0019\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\f\u001a\u0004\b\u001a\u0010\u000e\"\u0004\b\u001b\u0010\u0010R\"\u0010\u001d\u001a\u00020\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\"\u0010#\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b#\u0010\f\u001a\u0004\b$\u0010\u000e\"\u0004\b%\u0010\u0010R\"\u0010&\u001a\u00020\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b&\u0010\u001e\u001a\u0004\b'\u0010 \"\u0004\b(\u0010\"R(\u0010*\u001a\u00020)8\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b*\u0010+\u0012\u0004\b0\u00101\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\"\u00102\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b2\u0010\f\u001a\u0004\b3\u0010\u000e\"\u0004\b4\u0010\u0010R\"\u00105\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b5\u0010\f\u001a\u0004\b6\u0010\u000e\"\u0004\b7\u0010\u0010R\"\u00108\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b8\u0010\f\u001a\u0004\b9\u0010\u000e\"\u0004\b:\u0010\u0010R\"\u0010;\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b;\u0010\f\u001a\u0004\b<\u0010\u000e\"\u0004\b=\u0010\u0010R\"\u0010>\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b>\u0010\f\u001a\u0004\b?\u0010\u000e\"\u0004\b@\u0010\u0010R\"\u0010A\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bA\u0010\f\u001a\u0004\bB\u0010\u000e\"\u0004\bC\u0010\u0010R\"\u0010D\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bD\u0010\f\u001a\u0004\bE\u0010\u000e\"\u0004\bF\u0010\u0010R\"\u0010H\u001a\u00020G8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bH\u0010I\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR(\u0010N\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\bN\u0010\f\u0012\u0004\bQ\u00101\u001a\u0004\bO\u0010\u000e\"\u0004\bP\u0010\u0010¨\u0006R"}, d2 = {"Lkotlinx/serialization/json/JsonBuilder;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/serialization/json/Json;", "json", "<init>", "(Lkotlinx/serialization/json/Json;)V", "Lkotlinx/serialization/json/JsonConfiguration;", "build$kotlinx_serialization_json", "()Lkotlinx/serialization/json/JsonConfiguration;", "build", _UrlKt.FRAGMENT_ENCODE_SET, "encodeDefaults", "Z", "getEncodeDefaults", "()Z", "setEncodeDefaults", "(Z)V", "explicitNulls", "getExplicitNulls", "setExplicitNulls", "ignoreUnknownKeys", "getIgnoreUnknownKeys", "setIgnoreUnknownKeys", "isLenient", "setLenient", "prettyPrint", "getPrettyPrint", "setPrettyPrint", _UrlKt.FRAGMENT_ENCODE_SET, "prettyPrintIndent", "Ljava/lang/String;", "getPrettyPrintIndent", "()Ljava/lang/String;", "setPrettyPrintIndent", "(Ljava/lang/String;)V", "coerceInputValues", "getCoerceInputValues", "setCoerceInputValues", "classDiscriminator", "getClassDiscriminator", "setClassDiscriminator", "Lkotlinx/serialization/json/ClassDiscriminatorMode;", "classDiscriminatorMode", "Lkotlinx/serialization/json/ClassDiscriminatorMode;", "getClassDiscriminatorMode", "()Lkotlinx/serialization/json/ClassDiscriminatorMode;", "setClassDiscriminatorMode", "(Lkotlinx/serialization/json/ClassDiscriminatorMode;)V", "getClassDiscriminatorMode$annotations", "()V", "useAlternativeNames", "getUseAlternativeNames", "setUseAlternativeNames", "decodeEnumsCaseInsensitive", "getDecodeEnumsCaseInsensitive", "setDecodeEnumsCaseInsensitive", "allowTrailingComma", "getAllowTrailingComma", "setAllowTrailingComma", "allowComments", "getAllowComments", "setAllowComments", "allowSpecialFloatingPointValues", "getAllowSpecialFloatingPointValues", "setAllowSpecialFloatingPointValues", "allowStructuredMapKeys", "getAllowStructuredMapKeys", "setAllowStructuredMapKeys", "useArrayPolymorphism", "getUseArrayPolymorphism", "setUseArrayPolymorphism", "Lkotlinx/serialization/modules/SerializersModule;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "setSerializersModule", "(Lkotlinx/serialization/modules/SerializersModule;)V", "exceptionsWithDebugInfo", "getExceptionsWithDebugInfo", "setExceptionsWithDebugInfo", "getExceptionsWithDebugInfo$annotations", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nJson.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Json.kt\nkotlinx/serialization/json/JsonBuilder\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,750:1\n1088#2,2:751\n*S KotlinDebug\n*F\n+ 1 Json.kt\nkotlinx/serialization/json/JsonBuilder\n*L\n712#1:751,2\n*E\n"})
/* loaded from: classes5.dex */
public final class JsonBuilder {
    private boolean allowComments;
    private boolean allowSpecialFloatingPointValues;
    private boolean allowStructuredMapKeys;
    private boolean allowTrailingComma;
    private String classDiscriminator;
    private ClassDiscriminatorMode classDiscriminatorMode;
    private boolean coerceInputValues;
    private boolean decodeEnumsCaseInsensitive;
    private boolean encodeDefaults;
    private boolean exceptionsWithDebugInfo;
    private boolean explicitNulls;
    private boolean ignoreUnknownKeys;
    private boolean isLenient;
    private boolean prettyPrint;
    private String prettyPrintIndent;
    private SerializersModule serializersModule;
    private boolean useAlternativeNames;
    private boolean useArrayPolymorphism;

    public JsonBuilder(Json r2) {
        this.encodeDefaults = r2.getConfiguration().getEncodeDefaults();
        this.explicitNulls = r2.getConfiguration().getExplicitNulls();
        this.ignoreUnknownKeys = r2.getConfiguration().getIgnoreUnknownKeys();
        this.isLenient = r2.getConfiguration().isLenient();
        this.prettyPrint = r2.getConfiguration().getPrettyPrint();
        this.prettyPrintIndent = r2.getConfiguration().getPrettyPrintIndent();
        this.coerceInputValues = r2.getConfiguration().getCoerceInputValues();
        this.classDiscriminator = r2.getConfiguration().getClassDiscriminator();
        this.classDiscriminatorMode = r2.getConfiguration().getClassDiscriminatorMode();
        this.useAlternativeNames = r2.getConfiguration().getUseAlternativeNames();
        r2.getConfiguration().getNamingStrategy();
        this.decodeEnumsCaseInsensitive = r2.getConfiguration().getDecodeEnumsCaseInsensitive();
        this.allowTrailingComma = r2.getConfiguration().getAllowTrailingComma();
        this.allowComments = r2.getConfiguration().getAllowComments();
        this.allowSpecialFloatingPointValues = r2.getConfiguration().getAllowSpecialFloatingPointValues();
        this.allowStructuredMapKeys = r2.getConfiguration().getAllowStructuredMapKeys();
        this.useArrayPolymorphism = r2.getConfiguration().getUseArrayPolymorphism();
        this.serializersModule = r2.getSerializersModule();
        this.exceptionsWithDebugInfo = r2.getConfiguration().getExceptionsWithDebugInfo();
    }

    public final JsonConfiguration build$kotlinx_serialization_json() {
        if (this.useArrayPolymorphism == true) goto L5;
    L13:
        boolean r1 = this.prettyPrint;
        String r3 = this.prettyPrintIndent;
        if (r1 == true) goto L21;
        if (Intrinsics.areEqual(r3, "    ") == true) goto L38;
        f$$ExternalSyntheticBUOutline1.m("Indent should not be specified when default printing mode is used");
        return null;
    L38:
        return new JsonConfiguration(this.encodeDefaults, this.ignoreUnknownKeys, this.isLenient, this.allowStructuredMapKeys, this.prettyPrint, this.explicitNulls, this.prettyPrintIndent, this.coerceInputValues, this.useArrayPolymorphism, this.classDiscriminator, this.allowSpecialFloatingPointValues, this.useAlternativeNames, null, this.decodeEnumsCaseInsensitive, this.allowTrailingComma, this.allowComments, this.classDiscriminatorMode, this.exceptionsWithDebugInfo);
    L21:
        if (Intrinsics.areEqual(r3, "    ") == true) goto L38;
        String r12 = this.prettyPrintIndent;
        int r32 = 0;
    L24:
        if (r32 >= r12.length()) goto L38;
        char r4 = r12.charAt(r32);
        if (r4 == ' ') goto L36;
        if (r4 == '\t') goto L36;
        if (r4 == '\r') goto L36;
        if (r4 == '\n') goto L36;
        Options$Companion$$ExternalSyntheticBUOutline0.m("Only whitespace, tab, newline and carriage return are allowed as pretty print symbols. Had ", this.prettyPrintIndent);
        return null;
    L36:
        r32 = r32 + 1;
        goto L24
    L5:
        if (Intrinsics.areEqual(this.classDiscriminator, "type") == true) goto L7;
        f$$ExternalSyntheticBUOutline1.m("Class discriminator should not be specified when array polymorphism is specified");
        return null;
    L7:
        if (this.classDiscriminatorMode == ClassDiscriminatorMode.POLYMORPHIC) goto L13;
        f$$ExternalSyntheticBUOutline1.m("useArrayPolymorphism option can only be used if classDiscriminatorMode in a default POLYMORPHIC state.");
        return null;
    }

    public final SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    public final void setIgnoreUnknownKeys(boolean r1) {
        this.ignoreUnknownKeys = r1;
    }
}
