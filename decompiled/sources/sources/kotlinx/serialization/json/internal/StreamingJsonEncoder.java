package kotlinx.serialization.json.internal;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.PolymorphicSerializerKt;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.AbstractEncoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.json.ClassDiscriminatorMode;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonEncoder;
import kotlinx.serialization.json.internal.PolymorphicKt;
import kotlinx.serialization.modules.SerializersModule;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B1\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0010\u0010\n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\t¢\u0006\u0004\b\u000b\u0010\fB1\b\u0010\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t¢\u0006\u0004\b\u000b\u0010\u000fJ\u001f\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ+\u0010!\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u001d2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010 \u001a\u00028\u0000H\u0016¢\u0006\u0004\b!\u0010\"J\u0017\u0010$\u001a\u00020#2\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b$\u0010%J\u0017\u0010&\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b&\u0010'J\u001f\u0010(\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b(\u0010\u001cJA\u0010*\u001a\u00020\u0013\"\b\b\u0000\u0010\u001d*\u00020)2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\b\u0010 \u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0004\b*\u0010+J\u000f\u0010,\u001a\u00020\u0013H\u0016¢\u0006\u0004\b,\u0010-J\u0017\u0010.\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u001aH\u0016¢\u0006\u0004\b.\u0010/J\u0017\u00100\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u0018H\u0016¢\u0006\u0004\b0\u00101J\u0017\u00103\u001a\u00020\u00132\u0006\u0010 \u001a\u000202H\u0016¢\u0006\u0004\b3\u00104J\u0017\u00106\u001a\u00020\u00132\u0006\u0010 \u001a\u000205H\u0016¢\u0006\u0004\b6\u00107J\u0017\u00108\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u0010H\u0016¢\u0006\u0004\b8\u00109R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010:R\u001a\u0010\u0006\u001a\u00020\u00058\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0006\u0010;\u001a\u0004\b<\u0010=R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010>R\u001e\u0010\n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010?R\u001a\u0010A\u001a\u00020@8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bA\u0010B\u001a\u0004\bC\u0010DR\u0014\u0010F\u001a\u00020E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bF\u0010GR\u0016\u0010H\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bH\u0010IR\u0018\u0010J\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bJ\u0010KR\u0018\u0010L\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bL\u0010K¨\u0006M"}, d2 = {"Lkotlinx/serialization/json/internal/StreamingJsonEncoder;", "Lkotlinx/serialization/json/JsonEncoder;", "Lkotlinx/serialization/encoding/AbstractEncoder;", "Lkotlinx/serialization/json/internal/Composer;", "composer", "Lkotlinx/serialization/json/Json;", "json", "Lkotlinx/serialization/json/internal/WriteMode;", "mode", _UrlKt.FRAGMENT_ENCODE_SET, "modeReuseCache", "<init>", "(Lkotlinx/serialization/json/internal/Composer;Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/internal/WriteMode;[Lkotlinx/serialization/json/JsonEncoder;)V", "Lkotlinx/serialization/json/internal/InternalJsonWriter;", "output", "(Lkotlinx/serialization/json/internal/InternalJsonWriter;Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/internal/WriteMode;[Lkotlinx/serialization/json/JsonEncoder;)V", _UrlKt.FRAGMENT_ENCODE_SET, "discriminator", "serialName", _UrlKt.FRAGMENT_ENCODE_SET, "encodeTypeInfo", "(Ljava/lang/String;Ljava/lang/String;)V", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", _UrlKt.FRAGMENT_ENCODE_SET, "index", _UrlKt.FRAGMENT_ENCODE_SET, "shouldEncodeElementDefault", "(Lkotlinx/serialization/descriptors/SerialDescriptor;I)Z", "T", "Lkotlinx/serialization/SerializationStrategy;", "serializer", "value", "encodeSerializableValue", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "Lkotlinx/serialization/encoding/CompositeEncoder;", "beginStructure", "(Lkotlinx/serialization/descriptors/SerialDescriptor;)Lkotlinx/serialization/encoding/CompositeEncoder;", "endStructure", "(Lkotlinx/serialization/descriptors/SerialDescriptor;)V", "encodeElement", _UrlKt.FRAGMENT_ENCODE_SET, "encodeNullableSerializableElement", "(Lkotlinx/serialization/descriptors/SerialDescriptor;ILkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "encodeNull", "()V", "encodeBoolean", "(Z)V", "encodeInt", "(I)V", _UrlKt.FRAGMENT_ENCODE_SET, "encodeLong", "(J)V", _UrlKt.FRAGMENT_ENCODE_SET, "encodeDouble", "(D)V", "encodeString", "(Ljava/lang/String;)V", "Lkotlinx/serialization/json/internal/Composer;", "Lkotlinx/serialization/json/Json;", "getJson", "()Lkotlinx/serialization/json/Json;", "Lkotlinx/serialization/json/internal/WriteMode;", "[Lkotlinx/serialization/json/JsonEncoder;", "Lkotlinx/serialization/modules/SerializersModule;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "Lkotlinx/serialization/json/JsonConfiguration;", "configuration", "Lkotlinx/serialization/json/JsonConfiguration;", "forceQuoting", "Z", "polymorphicDiscriminator", "Ljava/lang/String;", "polymorphicSerialName", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nStreamingJsonEncoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StreamingJsonEncoder.kt\nkotlinx/serialization/json/internal/StreamingJsonEncoder\n+ 2 Polymorphic.kt\nkotlinx/serialization/json/internal/PolymorphicKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,232:1\n178#1,2:261\n178#1,2:263\n19#2,12:233\n33#2,15:246\n1#3:245\n1#3:265\n*S KotlinDebug\n*F\n+ 1 StreamingJsonEncoder.kt\nkotlinx/serialization/json/internal/StreamingJsonEncoder\n*L\n168#1:261,2\n169#1:263,2\n68#1:233,12\n68#1:246,15\n68#1:245\n*E\n"})
/* loaded from: classes.dex */
public final class StreamingJsonEncoder extends AbstractEncoder implements JsonEncoder {
    private final Composer composer;
    private final JsonConfiguration configuration;
    private boolean forceQuoting;
    private final Json json;
    private final WriteMode mode;
    private final JsonEncoder[] modeReuseCache;
    private String polymorphicDiscriminator;
    private String polymorphicSerialName;
    private final SerializersModule serializersModule;

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[WriteMode.values().length];
            r0[WriteMode.LIST.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L11:
            r0[WriteMode.MAP.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L15:
            r0[WriteMode.POLY_OBJ.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L6:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public StreamingJsonEncoder(Composer r1, Json r2, WriteMode r3, JsonEncoder[] r4) {
        this.composer = r1;
        this.json = r2;
        this.mode = r3;
        this.modeReuseCache = r4;
        this.serializersModule = getJson().getSerializersModule();
        this.configuration = getJson().getConfiguration();
        int r12 = r3.ordinal();
        if (r4 == null) goto L9;
        JsonEncoder r22 = r4[r12];
        if (r22 != null) goto L7;
        if (r22 != this) goto L7;
        return;
    L7:
        r4[r12] = this;
        return;
    }

    public StreamingJsonEncoder(InternalJsonWriter r1, Json r2, WriteMode r3, JsonEncoder[] r4) {
        this(ComposersKt.Composer(r1, r2), r2, r3, r4);
    }

    private final void encodeTypeInfo(String r2, String r3) {
        this.composer.nextItem();
        encodeString(r2);
        this.composer.print(':');
        this.composer.space();
        encodeString(r3);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public CompositeEncoder beginStructure(SerialDescriptor r4) {
        WriteMode r0 = WriteModeKt.switchMode(getJson(), r4);
        char r1 = r0.begin;
        if (r1 == 0) goto L5;
        this.composer.print(r1);
        this.composer.indent();
    L5:
        String r12 = this.polymorphicDiscriminator;
        if (r12 == null) goto L12;
        String r2 = this.polymorphicSerialName;
        if (r2 != null) goto L10;
        r2 = r4.getSerialName();
    L10:
        encodeTypeInfo(r12, r2);
        this.polymorphicDiscriminator = null;
        this.polymorphicSerialName = null;
    L12:
        if (this.mode != r0) goto L14;
        return this;
    L14:
        JsonEncoder[] r42 = this.modeReuseCache;
        if (r42 == null) goto L20;
        JsonEncoder r43 = r42[r0.ordinal()];
        if (r43 == null) goto L20;
        return r43;
    L20:
        return new StreamingJsonEncoder(this.composer, getJson(), r0, this.modeReuseCache);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeBoolean(boolean r2) {
        if (this.forceQuoting == false) goto L6;
        encodeString(String.valueOf(r2));
        return;
    L6:
        this.composer.print(r2);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeDouble(double r5) {
        if (this.forceQuoting == false) goto L5;
        encodeString(String.valueOf(r5));
    L7:
        if (this.configuration.getAllowSpecialFloatingPointValues() == false) goto L9;
        return;
    L9:
        if (Math.abs(r5) > Double.MAX_VALUE) goto L12;
        return;
    L12:
        throw JsonExceptionsKt.InvalidFloatingPointEncoded$default(Double.valueOf(r5), null, 2, null);
    L5:
        this.composer.print(r5);
        goto L7
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder
    public boolean encodeElement(SerialDescriptor r7, int r8) {
        int r0 = WhenMappings.$EnumSwitchMapping$0[this.mode.ordinal()];
        if (r0 == 1) goto L25;
        boolean r4 = false;
        if (r0 == 2) goto L17;
        if (r0 != 3) goto L9;
        if (r8 != 0) goto L14;
        this.forceQuoting = true;
    L14:
        if (r8 != 1) goto L28;
        this.composer.print(',');
        this.composer.space();
        this.forceQuoting = false;
    L28:
        return true;
    L9:
        if (this.composer.getWritingFirst() == true) goto L11;
        this.composer.print(',');
    L11:
        this.composer.nextItem();
        encodeString(JsonNamesMapKt.getJsonElementName(r7, getJson(), r8));
        this.composer.print(':');
        this.composer.space();
        goto L28
    L17:
        if (this.composer.getWritingFirst() == true) goto L23;
        int r82 = r8 % 2;
        Composer r72 = this.composer;
        if (r82 != 0) goto L21;
        r72.print(',');
        this.composer.nextItem();
        r4 = true;
    L22:
        this.forceQuoting = r4;
        goto L28
    L21:
        r72.print(':');
        this.composer.space();
        goto L22
    L23:
        this.forceQuoting = true;
        this.composer.nextItem();
        goto L28
    L25:
        if (this.composer.getWritingFirst() == true) goto L27;
        this.composer.print(',');
    L27:
        this.composer.nextItem();
        goto L28
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeInt(int r2) {
        if (this.forceQuoting == false) goto L6;
        encodeString(String.valueOf(r2));
        return;
    L6:
        this.composer.print(r2);
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeLong(long r2) {
        if (this.forceQuoting == false) goto L6;
        encodeString(String.valueOf(r2));
        return;
    L6:
        this.composer.print(r2);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeNull() {
        this.composer.print("null");
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.CompositeEncoder
    public <T> void encodeNullableSerializableElement(SerialDescriptor r2, int r3, SerializationStrategy<? super T> r4, T r5) {
        if (r5 == null) goto L4;
    L7:
        super.encodeNullableSerializableElement(r2, r3, r4, r5);
        return;
    L4:
        if (this.configuration.getExplicitNulls() == true) goto L7;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public <T> void encodeSerializableValue(SerializationStrategy<? super T> r5, T r6) {
        if (getJson().getConfiguration().getUseArrayPolymorphism() == false) goto L6;
        r5.serialize(this, r6);
        return;
    L6:
        boolean r0 = r5 instanceof AbstractPolymorphicSerializer;
        if (r0 == true) goto L9;
        int r2 = PolymorphicKt.WhenMappings.$EnumSwitchMapping$0[getJson().getConfiguration().getClassDiscriminatorMode().ordinal()];
        if (r2 != 1) goto L14;
    L24:
        String r22 = null;
    L25:
        if (r0 == false) goto L31;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r5);
        if (r6 == 0) goto L29;
        SerializationStrategy<? super T> r02 = PolymorphicSerializerKt.findPolymorphicSerializer(null, this, r6);
    L32:
        if (r22 == null) goto L34;
        PolymorphicKt.access$checkEncodingConflicts(getJson(), r5, r02, r22);
        PolymorphicKt.checkKind(r02.getDescriptor().getKind());
        String r52 = r02.getDescriptor().getSerialName();
        this.polymorphicDiscriminator = r22;
        this.polymorphicSerialName = r52;
    L34:
        r02.serialize(this, r6);
        return;
    L29:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r5);
        throw null;
    L31:
        r02 = r5;
        goto L32
    L14:
        if (r2 == 2) goto L24;
        if (r2 != 3) goto L22;
        SerialKind r23 = r5.getDescriptor().getKind();
        if (Intrinsics.areEqual(r23, StructureKind.CLASS.INSTANCE) == false) goto L20;
    L21:
        r22 = PolymorphicKt.classDiscriminator(r5.getDescriptor(), getJson());
        goto L25
    L20:
        if (Intrinsics.areEqual(r23, StructureKind.OBJECT.INSTANCE) == false) goto L24;
    L22:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return;
    L9:
        if (getJson().getConfiguration().getClassDiscriminatorMode() == ClassDiscriminatorMode.NONE) goto L24;
        goto L24
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeString(String r1) {
        this.composer.printQuoted(r1);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public void endStructure(SerialDescriptor r1) {
        if (this.mode.end == 0) goto L6;
        this.composer.unIndent();
        this.composer.nextItemIfNotFirst();
        this.composer.print(this.mode.end);
        return;
    }

    @Override // kotlinx.serialization.json.JsonEncoder
    public Json getJson() {
        return this.json;
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public boolean shouldEncodeElementDefault(SerialDescriptor r1, int r2) {
        return this.configuration.getEncodeDefaults();
    }
}
