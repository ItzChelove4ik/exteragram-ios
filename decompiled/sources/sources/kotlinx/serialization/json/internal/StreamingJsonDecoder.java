package kotlinx.serialization.json.internal;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.AbstractDecoder;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.internal.JsonInternalDependenciesKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.modules.SerializersModule;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0001\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0010\u0018\u00002\u00020\u00012\u00020\u00012\u00020\u0002:\u0001UB1\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0012\u001a\u00020\u0011*\u0004\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\u001d\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u0019H\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001f\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u001f\u0010 J\u001f\u0010\"\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\t2\u0006\u0010!\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\u0019H\u0002¢\u0006\u0004\b$\u0010\u001bJ\u000f\u0010%\u001a\u00020\u000fH\u0002¢\u0006\u0004\b%\u0010&J#\u0010*\u001a\u00028\u0000\"\u0004\b\u0000\u0010'2\f\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00000(H\u0016¢\u0006\u0004\b*\u0010+J\u0017\u0010-\u001a\u00020,2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b-\u0010.J\u0017\u0010/\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b/\u0010\u0016J\u000f\u00100\u001a\u00020\u0011H\u0016¢\u0006\u0004\b0\u00101J\u0011\u00103\u001a\u0004\u0018\u000102H\u0016¢\u0006\u0004\b3\u00104J=\u00106\u001a\u00028\u0000\"\u0004\b\u0000\u0010'2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u00192\f\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00000(2\b\u00105\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0004\b6\u00107J\u0017\u00108\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b8\u0010 J\u000f\u00109\u001a\u00020\u0011H\u0016¢\u0006\u0004\b9\u00101J\u000f\u0010:\u001a\u00020\u0019H\u0016¢\u0006\u0004\b:\u0010\u001bJ\u000f\u0010<\u001a\u00020;H\u0016¢\u0006\u0004\b<\u0010=J\u000f\u0010?\u001a\u00020>H\u0016¢\u0006\u0004\b?\u0010@J\u000f\u0010A\u001a\u00020\u000fH\u0016¢\u0006\u0004\bA\u0010&R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010B\u001a\u0004\bC\u0010DR\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010ER\u0014\u0010\b\u001a\u00020\u00078\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\b\u0010FR\u001a\u0010H\u001a\u00020G8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bH\u0010I\u001a\u0004\bJ\u0010KR\u0016\u0010L\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bL\u0010MR\u0018\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010NR\u0014\u0010P\u001a\u00020O8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bP\u0010QR\u0016\u0010S\u001a\u0004\u0018\u00010R8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bS\u0010T¨\u0006V"}, d2 = {"Lkotlinx/serialization/json/internal/StreamingJsonDecoder;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/serialization/encoding/AbstractDecoder;", "Lkotlinx/serialization/json/Json;", "json", "Lkotlinx/serialization/json/internal/WriteMode;", "mode", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "lexer", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", "Lkotlinx/serialization/json/internal/StreamingJsonDecoder$DiscriminatorHolder;", "discriminatorHolder", "<init>", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/internal/WriteMode;Lkotlinx/serialization/json/internal/AbstractJsonLexer;Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlinx/serialization/json/internal/StreamingJsonDecoder$DiscriminatorHolder;)V", _UrlKt.FRAGMENT_ENCODE_SET, "unknownKey", _UrlKt.FRAGMENT_ENCODE_SET, "trySkip", "(Lkotlinx/serialization/json/internal/StreamingJsonDecoder$DiscriminatorHolder;Ljava/lang/String;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "skipLeftoverElements", "(Lkotlinx/serialization/descriptors/SerialDescriptor;)V", "checkLeadingComma", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "decodeMapIndex", "()I", "index", "coerceInputValue", "(Lkotlinx/serialization/descriptors/SerialDescriptor;I)Z", "decodeObjectIndex", "(Lkotlinx/serialization/descriptors/SerialDescriptor;)I", PluginsConstants.Settings.KEY, "handleUnknown", "(Lkotlinx/serialization/descriptors/SerialDescriptor;Ljava/lang/String;)Z", "decodeListIndex", "decodeStringKey", "()Ljava/lang/String;", "T", "Lkotlinx/serialization/DeserializationStrategy;", "deserializer", "decodeSerializableValue", "(Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "Lkotlinx/serialization/encoding/CompositeDecoder;", "beginStructure", "(Lkotlinx/serialization/descriptors/SerialDescriptor;)Lkotlinx/serialization/encoding/CompositeDecoder;", "endStructure", "decodeNotNullMark", "()Z", _UrlKt.FRAGMENT_ENCODE_SET, "decodeNull", "()Ljava/lang/Void;", "previousValue", "decodeSerializableElement", "(Lkotlinx/serialization/descriptors/SerialDescriptor;ILkotlinx/serialization/DeserializationStrategy;Ljava/lang/Object;)Ljava/lang/Object;", "decodeElementIndex", "decodeBoolean", "decodeInt", _UrlKt.FRAGMENT_ENCODE_SET, "decodeLong", "()J", _UrlKt.FRAGMENT_ENCODE_SET, "decodeDouble", "()D", "decodeString", "Lkotlinx/serialization/json/Json;", "getJson", "()Lkotlinx/serialization/json/Json;", "Lkotlinx/serialization/json/internal/WriteMode;", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "Lkotlinx/serialization/modules/SerializersModule;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "currentIndex", "I", "Lkotlinx/serialization/json/internal/StreamingJsonDecoder$DiscriminatorHolder;", "Lkotlinx/serialization/json/JsonConfiguration;", "configuration", "Lkotlinx/serialization/json/JsonConfiguration;", "Lkotlinx/serialization/json/internal/JsonElementMarker;", "elementMarker", "Lkotlinx/serialization/json/internal/JsonElementMarker;", "DiscriminatorHolder", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nStreamingJsonDecoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StreamingJsonDecoder.kt\nkotlinx/serialization/json/internal/StreamingJsonDecoder\n+ 2 Polymorphic.kt\nkotlinx/serialization/json/internal/PolymorphicKt\n+ 3 TreeJsonEncoder.kt\nkotlinx/serialization/json/internal/TreeJsonEncoderKt\n+ 4 JsonExceptions.kt\nkotlinx/serialization/json/internal/JsonExceptionsKt\n+ 5 AbstractJsonLexer.kt\nkotlinx/serialization/json/internal/AbstractJsonLexer\n+ 6 JsonNamesMap.kt\nkotlinx/serialization/json/internal/JsonNamesMapKt\n+ 7 StreamingJsonDecoder.kt\nkotlinx/serialization/json/internal/StreamingJsonDecoderKt\n*L\n1#1,392:1\n80#2,6:393\n86#2,7:420\n94#2:435\n270#3,4:399\n274#3:411\n276#3:419\n21#4,7:403\n60#4:410\n28#4,7:412\n60#4:427\n28#4,7:428\n520#5,3:436\n520#5,3:439\n145#6,18:442\n385#7,5:460\n385#7,5:465\n*S KotlinDebug\n*F\n+ 1 StreamingJsonDecoder.kt\nkotlinx/serialization/json/internal/StreamingJsonDecoder\n*L\n75#1:393,6\n75#1:420,7\n75#1:435\n75#1:399,4\n75#1:411\n75#1:419\n75#1:403,7\n75#1:410\n75#1:412,7\n75#1:427\n75#1:428,7\n202#1:436,3\n203#1:439,3\n215#1:442,18\n309#1:460,5\n316#1:465,5\n*E\n"})
/* loaded from: classes.dex */
public class StreamingJsonDecoder extends AbstractDecoder implements Decoder, CompositeDecoder {
    private final JsonConfiguration configuration;
    private int currentIndex;
    private DiscriminatorHolder discriminatorHolder;
    private final JsonElementMarker elementMarker;
    private final Json json;

    @JvmField
    public final AbstractJsonLexer lexer;
    private final WriteMode mode;
    private final SerializersModule serializersModule;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlinx/serialization/json/internal/StreamingJsonDecoder$DiscriminatorHolder;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "discriminatorToSkip", "Ljava/lang/String;", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class DiscriminatorHolder {
        public String discriminatorToSkip;
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[WriteMode.values().length];
            r0[WriteMode.LIST.ordinal()] = 1;     // Catch: NoSuchFieldError -> L9
        L13:
            r0[WriteMode.MAP.ordinal()] = 2;     // Catch: NoSuchFieldError -> L10
        L19:
            r0[WriteMode.POLY_OBJ.ordinal()] = 3;     // Catch: NoSuchFieldError -> L11
        L15:
            r0[WriteMode.OBJ.ordinal()] = 4;     // Catch: NoSuchFieldError -> L12
        L7:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public StreamingJsonDecoder(Json r1, WriteMode r2, AbstractJsonLexer r3, SerialDescriptor r4, DiscriminatorHolder r5) {
        this.json = r1;
        this.mode = r2;
        this.lexer = r3;
        this.serializersModule = r1.getSerializersModule();
        this.currentIndex = -1;
        this.discriminatorHolder = r5;
        JsonConfiguration r12 = r1.getConfiguration();
        this.configuration = r12;
        if (r12.getExplicitNulls() == false) goto L5;
        JsonElementMarker r13 = null;
    L6:
        this.elementMarker = r13;
        return;
    L5:
        r13 = new JsonElementMarker(r4);
        goto L6
    }

    private final void checkLeadingComma() {
        if (this.lexer.peekNextToken() == 4) goto L5;
        return;
    L5:
        AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
    }

    private final boolean coerceInputValue(SerialDescriptor r6, int r7) {
        Json r0 = this.json;
        boolean r1 = r6.isElementOptional(r7);
        SerialDescriptor r62 = r6.getElementDescriptor(r7);
        if (r1 == false) goto L10;
        if (r62.isNullable() == true) goto L10;
        if (this.lexer.tryConsumeNull(true) == false) goto L10;
        return true;
    L10:
        if (Intrinsics.areEqual(r62.getKind(), SerialKind.ENUM.INSTANCE) == true) goto L12;
    L31:
        return false;
    L12:
        if (r62.isNullable() == true) goto L14;
    L16:
        String r2 = this.lexer.peekString(this.configuration.isLenient());
        if (r2 != null) goto L19;
        return false;
    L19:
        int r22 = JsonNamesMapKt.getJsonNameIndex(r62, r0, r2);
        if (r0.getConfiguration().getExplicitNulls() == false) goto L22;
    L24:
        boolean r63 = false;
    L26:
        if (r22 != (-3)) goto L31;
        if (r1 == true) goto L29;
        if (r63 == false) goto L31;
    L29:
        this.lexer.consumeString();
        return true;
    L22:
        if (r62.isNullable() == false) goto L24;
        r63 = true;
        goto L26
    L14:
        if (this.lexer.tryConsumeNull(false) == false) goto L16;
        return false;
    }

    private final int decodeListIndex() {
        boolean r0 = this.lexer.tryConsumeComma();
        if (this.lexer.canConsumeValue() == false) goto L13;
        int r1 = this.currentIndex;
        if (r1 == (-1)) goto L11;
        if (r0 == true) goto L11;
        AbstractJsonLexer.fail$default(this.lexer, "Expected end of the array or comma", 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L11:
        int r12 = r1 + 1;
        this.currentIndex = r12;
        return r12;
    L13:
        if (r0 == true) goto L15;
    L18:
        return -1;
    L15:
        if (this.json.getConfiguration().getAllowTrailingComma() == true) goto L18;
        JsonExceptionsKt.invalidTrailingComma(this.lexer, "array");
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    private final int decodeMapIndex() {
        int r0 = this.currentIndex;
        if ((r0 % 2) == 0) goto L5;
        boolean r1 = true;
    L7:
        if (r1 == false) goto L10;
        if (r0 == (-1)) goto L11;
        boolean r02 = this.lexer.tryConsumeComma();
    L13:
        if (this.lexer.canConsumeValue() == false) goto L29;
        if (r1 == false) goto L27;
        int r12 = this.currentIndex;
        AbstractJsonLexer r5 = this.lexer;
        if (r12 != (-1)) goto L22;
        int r7 = r5.currentPosition;
        if (r02 == false) goto L27;
        AbstractJsonLexer.fail$default(r5, "Unexpected leading comma", r7, null, 4, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L22:
        int r72 = r5.currentPosition;
        if (r02 == true) goto L27;
        AbstractJsonLexer.fail$default(r5, "Expected comma after the key-value pair", r72, null, 4, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L27:
        int r03 = this.currentIndex + 1;
        this.currentIndex = r03;
        return r03;
    L29:
        if (r02 == true) goto L31;
    L35:
        return -1;
    L31:
        if (this.json.getConfiguration().getAllowTrailingComma() == true) goto L35;
        JsonExceptionsKt.invalidTrailingComma$default(this.lexer, null, 1, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L11:
        r02 = false;
        goto L13
    L10:
        this.lexer.consumeNextToken(':');
        goto L11
    L5:
        r1 = false;
        goto L7
    }

    private final int decodeObjectIndex(SerialDescriptor r6) {
        boolean r0 = this.lexer.tryConsumeComma();
    L3:
        boolean r2 = true;
        if (this.lexer.canConsumeValue() == false) goto L20;
        String r02 = decodeStringKey();
        this.lexer.consumeNextToken(':');
        int r1 = JsonNamesMapKt.getJsonNameIndex(r6, this.json, r02);
        if (r1 != (-3)) goto L8;
        boolean r12 = false;
    L17:
        if (r2 == true) goto L18;
        r0 = r12;
        goto L3
    L18:
        r0 = handleUnknown(r6, r02);
        goto L3
    L8:
        if (this.configuration.getCoerceInputValues() == false) goto L12;
        if (coerceInputValue(r6, r1) == false) goto L12;
        r12 = this.lexer.tryConsumeComma();
        r2 = false;
    L12:
        JsonElementMarker r5 = this.elementMarker;
        if (r5 == null) goto L15;
        r5.mark$kotlinx_serialization_json(r1);
    L15:
        return r1;
    L20:
        if (r0 == true) goto L22;
    L26:
        JsonElementMarker r52 = this.elementMarker;
        if (r52 != null) goto L29;
        return -1;
    L29:
        return r52.nextUnmarkedIndex$kotlinx_serialization_json();
    L22:
        if (this.json.getConfiguration().getAllowTrailingComma() == true) goto L26;
        JsonExceptionsKt.invalidTrailingComma$default(this.lexer, null, 1, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    private final String decodeStringKey() {
        boolean r0 = this.configuration.isLenient();
        AbstractJsonLexer r1 = this.lexer;
        if (r0 == false) goto L7;
        return r1.consumeStringLenientNotNull();
    L7:
        return r1.consumeKeyString();
    }

    private final boolean handleUnknown(SerialDescriptor r2, String r3) {
        if (JsonNamesMapKt.ignoreUnknownKeys(r2, this.json) == false) goto L5;
    L8:
        this.lexer.skipElement(this.configuration.isLenient());
    L10:
        return this.lexer.tryConsumeComma();
    L5:
        if (trySkip(this.discriminatorHolder, r3) == true) goto L8;
        this.lexer.path.popDescriptor();
        this.lexer.failOnUnknownKey(r3);
        goto L10
    }

    private final void skipLeftoverElements(SerialDescriptor r3) {
    L3:
        if (decodeElementIndex(r3) != (-1)) goto L3;
    }

    private final boolean trySkip(DiscriminatorHolder r1, String r2) {
        return false;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor r8) {
        WriteMode r3 = WriteModeKt.switchMode(this.json, r8);
        this.lexer.path.pushDescriptor(r8);
        this.lexer.consumeNextToken(r3.begin);
        checkLeadingComma();
        int r0 = WhenMappings.$EnumSwitchMapping$0[r3.ordinal()];
        if (r0 == 1) goto L16;
        if (r0 == 2) goto L16;
        if (r0 == 3) goto L16;
        if (this.mode != r3) goto L14;
        if (this.json.getConfiguration().getExplicitNulls() == false) goto L14;
        return this;
    L14:
        return new StreamingJsonDecoder(this.json, r3, this.lexer, r8, this.discriminatorHolder);
    L16:
        return new StreamingJsonDecoder(this.json, r3, this.lexer, r8, this.discriminatorHolder);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public boolean decodeBoolean() {
        return this.lexer.consumeBooleanLenient();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public double decodeDouble() {
        AbstractJsonLexer r0 = this.lexer;
        String r1 = r0.consumeStringLenient();
        double r02 = Double.parseDouble(r1);     // Catch: IllegalArgumentException -> L12
        if (this.json.getConfiguration().getAllowSpecialFloatingPointValues() == false) goto L7;
        return r02;
    L7:
        if (Math.abs(r02) > Double.MAX_VALUE) goto L9;
        return r02;
    L9:
        JsonExceptionsKt.throwInvalidFloatingPointDecoded(this.lexer, Double.valueOf(r02));
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0.0d;
    L12:
        AbstractJsonLexer.fail$default(r0, "Failed to parse type 'double' for input '" + r1 + '\'', 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0.0d;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(SerialDescriptor r3) {
        WriteMode r0 = this.mode;
        int r02 = WhenMappings.$EnumSwitchMapping$0[r0.ordinal()];
        if (r02 != 2) goto L5;
        int r32 = decodeMapIndex();
    L10:
        if (this.mode == WriteMode.MAP) goto L12;
        this.lexer.path.updateDescriptorIndex(r32);
    L12:
        return r32;
    L5:
        if (r02 == 4) goto L7;
        r32 = decodeListIndex();
        goto L10
    L7:
        r32 = decodeObjectIndex(r3);
        goto L10
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeInt() {
        long r0 = this.lexer.consumeNumericLiteral();
        int r2 = (int) r0;
        if (r0 != r2) goto L5;
        return r2;
    L5:
        AbstractJsonLexer.fail$default(this.lexer, "Failed to parse int for input '" + r0 + '\'', 0, null, 6, null);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public long decodeLong() {
        return this.lexer.consumeNumericLiteral();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        JsonElementMarker r0 = this.elementMarker;
        if (r0 == null) goto L5;
        boolean r02 = r0.isUnmarkedNull$kotlinx_serialization_json();
    L6:
        if (r02 == false) goto L8;
    L10:
        return false;
    L8:
        if (AbstractJsonLexer.tryConsumeNull$default(this.lexer, false, 1, null) == true) goto L10;
        return true;
    L5:
        r02 = false;
        goto L6
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public Void decodeNull() {
        return null;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public <T> T decodeSerializableElement(SerialDescriptor r3, int r4, DeserializationStrategy<? extends T> r5, T r6) {
        if (this.mode == WriteMode.MAP) goto L5;
    L7:
        boolean r0 = false;
    L8:
        if (r0 == false) goto L10;
        this.lexer.path.resetCurrentMapKey();
    L10:
        T r32 = (T) super.decodeSerializableElement(r3, r4, r5, r6);
        if (r0 == false) goto L13;
        this.lexer.path.updateCurrentMapKey(r32);
    L13:
        return r32;
    L5:
        if ((r4 & 1) != 0) goto L7;
        r0 = true;
        goto L8
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public <T> T decodeSerializableValue(DeserializationStrategy<? extends T> r6) {
    L10:
        e = move-exception;
        if (StringsKt.contains$default(e.getMessage(), "at path", false, 2, null) == false) goto L18;
        throw e;
    L18:
        throw JsonInternalDependenciesKt.missingFieldExceptionWithNewMessage(e, e.getMessage() + " at path: " + this.lexer.path.getPath());
    L4:
        if ((r6 instanceof AbstractPolymorphicSerializer) == false) goto L13;
        if (this.json.getConfiguration().getUseArrayPolymorphism() == true) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r6);     // Catch: MissingFieldException -> L10
        throw null;
    L13:
        return r6.deserialize(this);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public String decodeString() {
        boolean r0 = this.configuration.isLenient();
        AbstractJsonLexer r1 = this.lexer;
        if (r0 == false) goto L7;
        return r1.consumeStringLenientNotNull();
    L7:
        return r1.consumeString();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor r2) {
        if (r2.getElementsCount() != 0) goto L8;
        if (JsonNamesMapKt.ignoreUnknownKeys(r2, this.json) == false) goto L8;
        skipLeftoverElements(r2);
    L8:
        if (this.lexer.tryConsumeComma() == true) goto L10;
    L14:
        this.lexer.consumeNextToken(this.mode.end);
        this.lexer.path.popDescriptor();
        return;
    L10:
        if (this.json.getConfiguration().getAllowTrailingComma() == true) goto L14;
        JsonExceptionsKt.invalidTrailingComma(this.lexer, _UrlKt.FRAGMENT_ENCODE_SET);
        InstantKt$$ExternalSyntheticBUOutline0.m();
    }
}
