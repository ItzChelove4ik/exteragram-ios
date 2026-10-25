package kotlinx.serialization.json.internal;

import com.sun.jna.Structure$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.json.ClassDiscriminatorMode;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.modules.SerializersModuleCollector;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J#\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJM\u0010\u0013\u001a\u00020\n\"\b\b\u0000\u0010\u000e*\u00020\r\"\b\b\u0001\u0010\u000f*\u00028\u00002\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014JR\u0010\u001b\u001a\u00020\n\"\b\b\u0000\u0010\u000e*\u00020\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\b2)\u0010\u001a\u001a%\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00190\u0015H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJT\u0010!\u001a\u00020\n\"\b\b\u0000\u0010\u000e*\u00020\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\b2+\u0010 \u001a'\u0012\u0015\u0012\u0013\u0018\u00010\u001d¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001e\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001f0\u0015H\u0016¢\u0006\u0004\b!\u0010\u001cR\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010$¨\u0006&"}, d2 = {"Lkotlinx/serialization/json/internal/JsonSerializersModuleValidator;", "Lkotlinx/serialization/modules/SerializersModuleCollector;", "Lkotlinx/serialization/json/JsonConfiguration;", "configuration", "<init>", "(Lkotlinx/serialization/json/JsonConfiguration;)V", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", "Lkotlin/reflect/KClass;", "actualClass", _UrlKt.FRAGMENT_ENCODE_SET, "checkKind", "(Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlin/reflect/KClass;)V", _UrlKt.FRAGMENT_ENCODE_SET, "Base", "Sub", "baseClass", "Lkotlinx/serialization/KSerializer;", "actualSerializer", "polymorphic", "(Lkotlin/reflect/KClass;Lkotlin/reflect/KClass;Lkotlinx/serialization/KSerializer;)V", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "value", "Lkotlinx/serialization/SerializationStrategy;", "defaultSerializerProvider", "polymorphicDefaultSerializer", "(Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function1;)V", _UrlKt.FRAGMENT_ENCODE_SET, "className", "Lkotlinx/serialization/DeserializationStrategy;", "defaultDeserializerProvider", "polymorphicDefaultDeserializer", _UrlKt.FRAGMENT_ENCODE_SET, "useArrayPolymorphism", "Z", "isDiscriminatorRequired", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class JsonSerializersModuleValidator implements SerializersModuleCollector {
    private final boolean isDiscriminatorRequired;
    private final boolean useArrayPolymorphism;

    public JsonSerializersModuleValidator(JsonConfiguration r2) {
        this.useArrayPolymorphism = r2.getUseArrayPolymorphism();
        if (r2.getClassDiscriminatorMode() == ClassDiscriminatorMode.NONE) goto L5;
        boolean r22 = true;
    L6:
        this.isDiscriminatorRequired = r22;
        return;
    L5:
        r22 = false;
        goto L6
    }

    private final void checkKind(SerialDescriptor r3, KClass<?> r4) {
        SerialKind r32 = r3.getKind();
        if ((r32 instanceof PolymorphicKind) == false) goto L5;
    L23:
        Structure$$ExternalSyntheticBUOutline0.m("Serializer for ", r4.getSimpleName(), " can't be registered as a subclass for polymorphic serialization because its kind ", r32, " is not concrete. To work with multiple hierarchies, register it as a base class.");
        return;
    L5:
        if (Intrinsics.areEqual(r32, SerialKind.CONTEXTUAL.INSTANCE) == true) goto L23;
        if (this.useArrayPolymorphism == false) goto L10;
        return;
    L10:
        if (this.isDiscriminatorRequired == true) goto L13;
        return;
    L13:
        if (Intrinsics.areEqual(r32, StructureKind.LIST.INSTANCE) == false) goto L15;
    L21:
        Structure$$ExternalSyntheticBUOutline0.m("Serializer for ", r4.getSimpleName(), " of kind ", r32, " cannot be serialized polymorphically with class discriminator.");
        return;
    L15:
        if (Intrinsics.areEqual(r32, StructureKind.MAP.INSTANCE) == true) goto L21;
        if ((r32 instanceof PrimitiveKind) == true) goto L21;
        if ((r32 instanceof SerialKind.ENUM) == true) goto L21;
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public <Base, Sub extends Base> void polymorphic(KClass<Base> r1, KClass<Sub> r2, KSerializer<Sub> r3) {
        checkKind(r3.getDescriptor(), r2);
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public <Base> void polymorphicDefaultDeserializer(KClass<Base> r1, Function1<? super String, ? extends DeserializationStrategy<? extends Base>> r2) {
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public <Base> void polymorphicDefaultSerializer(KClass<Base> r1, Function1<? super Base, ? extends SerializationStrategy<? super Base>> r2) {
    }
}
