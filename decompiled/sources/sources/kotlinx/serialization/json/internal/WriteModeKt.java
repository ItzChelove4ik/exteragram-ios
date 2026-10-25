package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.descriptors.ContextAwareKt;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.modules.SerializersModule;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001b\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lkotlinx/serialization/json/Json;", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "desc", "Lkotlinx/serialization/json/internal/WriteMode;", "switchMode", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/descriptors/SerialDescriptor;)Lkotlinx/serialization/json/internal/WriteMode;", "Lkotlinx/serialization/modules/SerializersModule;", "module", "carrierDescriptor", "(Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlinx/serialization/modules/SerializersModule;)Lkotlinx/serialization/descriptors/SerialDescriptor;", "kotlinx-serialization-json"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWriteMode.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WriteMode.kt\nkotlinx/serialization/json/internal/WriteModeKt\n*L\n1#1,53:1\n36#1,9:54\n*S KotlinDebug\n*F\n+ 1 WriteMode.kt\nkotlinx/serialization/json/internal/WriteModeKt\n*L\n26#1:54,9\n*E\n"})
/* loaded from: classes.dex */
public abstract class WriteModeKt {
    public static final SerialDescriptor carrierDescriptor(SerialDescriptor r2, SerializersModule r3) {
        if (Intrinsics.areEqual(r2.getKind(), SerialKind.CONTEXTUAL.INSTANCE) == false) goto L11;
        SerialDescriptor r0 = ContextAwareKt.getContextualDescriptor(r3, r2);
        if (r0 == null) goto L14;
        SerialDescriptor r32 = carrierDescriptor(r0, r3);
        if (r32 == null) goto L16;
        return r32;
    L16:
        return r2;
    L14:
        return r2;
    L11:
        if (r2.isInline() == true) goto L13;
        return r2;
    L13:
        return carrierDescriptor(r2.getElementDescriptor(0), r3);
    }

    public static final WriteMode switchMode(Json r2, SerialDescriptor r3) {
        SerialKind r0 = r3.getKind();
        if ((r0 instanceof PolymorphicKind) == false) goto L7;
        return WriteMode.POLY_OBJ;
    L7:
        if (Intrinsics.areEqual(r0, StructureKind.LIST.INSTANCE) == false) goto L11;
        return WriteMode.LIST;
    L11:
        if (Intrinsics.areEqual(r0, StructureKind.MAP.INSTANCE) == false) goto L26;
        SerialDescriptor r32 = carrierDescriptor(r3.getElementDescriptor(0), r2.getSerializersModule());
        SerialKind r02 = r32.getKind();
        if ((r02 instanceof PrimitiveKind) == true) goto L24;
        if (Intrinsics.areEqual(r02, SerialKind.ENUM.INSTANCE) == true) goto L24;
        if (r2.getConfiguration().getAllowStructuredMapKeys() == false) goto L22;
        return WriteMode.LIST;
    L22:
        throw JsonExceptionsKt.InvalidKeyKindException(r32);
    L24:
        return WriteMode.MAP;
    L26:
        return WriteMode.OBJ;
    }
}
