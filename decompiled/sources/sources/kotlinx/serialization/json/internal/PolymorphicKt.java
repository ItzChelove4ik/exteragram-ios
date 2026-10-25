package kotlinx.serialization.json.internal;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.json.ClassDiscriminatorMode;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonClassDiscriminator;
import kotlinx.serialization.json.JsonEncodingException;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a3\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\b\u001a\u0017\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u001b\u0010\u0005\u001a\u00020\u0004*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0005\u0010\u000f¨\u0006\u0010"}, d2 = {"Lkotlinx/serialization/json/Json;", "Lkotlinx/serialization/SerializationStrategy;", "serializer", "actualSerializer", _UrlKt.FRAGMENT_ENCODE_SET, "classDiscriminator", _UrlKt.FRAGMENT_ENCODE_SET, "checkEncodingConflicts", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/SerializationStrategy;Lkotlinx/serialization/SerializationStrategy;Ljava/lang/String;)V", "Lkotlinx/serialization/descriptors/SerialKind;", "kind", "checkKind", "(Lkotlinx/serialization/descriptors/SerialKind;)V", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "json", "(Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlinx/serialization/json/Json;)Ljava/lang/String;", "kotlinx-serialization-json"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPolymorphic.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Polymorphic.kt\nkotlinx/serialization/json/internal/PolymorphicKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 TreeJsonEncoder.kt\nkotlinx/serialization/json/internal/TreeJsonEncoderKt\n+ 4 JsonExceptions.kt\nkotlinx/serialization/json/internal/JsonExceptionsKt\n*L\n1#1,114:1\n1#2:115\n270#3,4:116\n274#3:128\n276#3:136\n21#4,7:120\n60#4:127\n28#4,7:129\n21#4,7:137\n60#4:144\n28#4,7:145\n*S KotlinDebug\n*F\n+ 1 Polymorphic.kt\nkotlinx/serialization/json/internal/PolymorphicKt\n*L\n85#1:116,4\n85#1:128\n85#1:136\n85#1:120,7\n85#1:127\n85#1:129,7\n92#1:137,7\n92#1:144\n92#1:145,7\n*E\n"})
/* loaded from: classes5.dex */
public abstract class PolymorphicKt {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    /* loaded from: classes.dex */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[ClassDiscriminatorMode.values().length];
            r0[ClassDiscriminatorMode.NONE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L11:
            r0[ClassDiscriminatorMode.POLYMORPHIC.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L15:
            r0[ClassDiscriminatorMode.ALL_JSON_OBJECTS.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L6:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public static final /* synthetic */ void access$checkEncodingConflicts(Json r0, SerializationStrategy r1, SerializationStrategy r2, String r3) {
        checkEncodingConflicts(r0, r1, r2, r3);
    }

    private static final void checkEncodingConflicts(Json r1, SerializationStrategy<?> r2, SerializationStrategy<?> r3, String r4) {
        if (JsonNamesMapKt.getJsonEncodedNames(r3.getDescriptor(), r1).contains(r4) == false) goto L12;
        String r22 = r2.getDescriptor().getSerialName();
        String r32 = r3.getDescriptor().getSerialName();
        if (r1.getConfiguration().getClassDiscriminatorMode() == ClassDiscriminatorMode.ALL_JSON_OBJECTS) goto L7;
    L9:
        String r12 = "as base class '" + r22 + '\'';
    L11:
        throw new JsonEncodingException("Class '" + r32 + "' cannot be serialized " + r12 + " because it has property name that conflicts with JSON class discriminator '" + r4 + "'.", r32, "You can either change class discriminator in JsonConfiguration, or rename property with @SerialName annotation.");
    L7:
        if (Intrinsics.areEqual(r22, r32) == false) goto L9;
        r12 = "in ALL_JSON_OBJECTS class discriminator mode";
        goto L11
    }

    public static final void checkKind(SerialKind r1) {
        if ((r1 instanceof SerialKind.ENUM) == false) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Enums cannot be serialized polymorphically with 'type' parameter. You can use 'JsonBuilder.useArrayPolymorphism' instead");
        return;
    L5:
        if ((r1 instanceof PrimitiveKind) == false) goto L7;
        Segment$$ExternalSyntheticBUOutline1.m("Primitives cannot be serialized polymorphically with 'type' parameter. You can use 'JsonBuilder.useArrayPolymorphism' instead");
        return;
    L7:
        if ((r1 instanceof PolymorphicKind) == true) goto L9;
        return;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("Actual serializer for polymorphic cannot be polymorphic itself");
    }

    public static final String classDiscriminator(SerialDescriptor r2, Json r3) {
        Iterator<Annotation> r22 = r2.getAnnotations().iterator();
    L4:
        if (r22.hasNext() == false) goto L10;
        Annotation r0 = r22.next();
        if ((r0 instanceof JsonClassDiscriminator) == false) goto L4;
        return ((JsonClassDiscriminator) r0).discriminator();
    L10:
        return r3.getConfiguration().getClassDiscriminator();
    }
}
