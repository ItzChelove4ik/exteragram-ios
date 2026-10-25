package kotlinx.serialization.internal;

import java.util.Arrays;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorKt;
import kotlinx.serialization.descriptors.SerialKind;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a!\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0013\u0010\u0007\u001a\u00020\u0006*\u00020\u0000H\u0000¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lkotlinx/serialization/descriptors/SerialDescriptor;", _UrlKt.FRAGMENT_ENCODE_SET, "typeParams", _UrlKt.FRAGMENT_ENCODE_SET, "hashCodeImpl", "(Lkotlinx/serialization/descriptors/SerialDescriptor;[Lkotlinx/serialization/descriptors/SerialDescriptor;)I", _UrlKt.FRAGMENT_ENCODE_SET, "toStringImpl", "(Lkotlinx/serialization/descriptors/SerialDescriptor;)Ljava/lang/String;", "kotlinx-serialization-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPluginGeneratedSerialDescriptor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginGeneratedSerialDescriptor.kt\nkotlinx/serialization/internal/PluginGeneratedSerialDescriptorKt\n+ 2 Platform.common.kt\nkotlinx/serialization/internal/Platform_commonKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,133:1\n160#2:134\n160#2:138\n1849#3,3:135\n1849#3,3:139\n*S KotlinDebug\n*F\n+ 1 PluginGeneratedSerialDescriptor.kt\nkotlinx/serialization/internal/PluginGeneratedSerialDescriptorKt\n*L\n123#1:134\n124#1:138\n123#1:135,3\n124#1:139,3\n*E\n"})
/* loaded from: classes5.dex */
public abstract class PluginGeneratedSerialDescriptorKt {
    public static CharSequence $r8$lambda$y9POfBj3yiQqguvd0MDl7etzaF0(SerialDescriptor r2, int r3) {
        return r2.getElementName(r3) + ": " + r2.getElementDescriptor(r3).getSerialName();
    }

    public static final int hashCodeImpl(SerialDescriptor r5, SerialDescriptor[] r6) {
        int r0 = (r5.getSerialName().hashCode() * 31) + Arrays.hashCode(r6);
        Iterable<SerialDescriptor> r52 = SerialDescriptorKt.getElementDescriptors(r5);
        Iterator<SerialDescriptor> r62 = r52.iterator();
        int r1 = 1;
        int r2 = 1;
    L3:
        int r4 = 0;
        if (r62.hasNext() == false) goto L9;
        int r22 = r2 * 31;
        String r3 = r62.next().getSerialName();
        if (r3 == null) goto L8;
        r4 = r3.hashCode();
    L8:
        r2 = r22 + r4;
        goto L3
    L9:
        Iterator<SerialDescriptor> r53 = r52.iterator();
    L11:
        if (r53.hasNext() == false) goto L18;
        int r12 = r1 * 31;
        SerialKind r63 = r53.next().getKind();
        if (r63 == null) goto L15;
        int r64 = r63.hashCode();
    L16:
        r1 = r12 + r64;
        goto L11
    L15:
        r64 = 0;
        goto L16
    L18:
        return (((r0 * 31) + r2) * 31) + r1;
    }

    public static final String toStringImpl(SerialDescriptor r11) {
        return CollectionsKt.joinToString$default(RangesKt.until(0, r11.getElementsCount()), ", ", r11.getSerialName() + '(', ")", 0, null, new PluginGeneratedSerialDescriptorKt$$ExternalSyntheticLambda0(r11), 24, null);
    }
}
