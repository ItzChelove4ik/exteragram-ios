package kotlinx.serialization.internal;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.descriptors.SerialDescriptor;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u0011\n\u0002\b\u0005\u001a\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a!\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00000\u0006*\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0005H\u0000¢\u0006\u0004\b\u0007\u0010\b\"\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00000\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lkotlinx/serialization/descriptors/SerialDescriptor;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "cachedSerialNames", "(Lkotlinx/serialization/descriptors/SerialDescriptor;)Ljava/util/Set;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "compactArray", "(Ljava/util/List;)[Lkotlinx/serialization/descriptors/SerialDescriptor;", "EMPTY_DESCRIPTOR_ARRAY", "[Lkotlinx/serialization/descriptors/SerialDescriptor;", "kotlinx-serialization-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPlatform.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Platform.common.kt\nkotlinx/serialization/internal/Platform_commonKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,190:1\n1#2:191\n37#3,2:192\n1849#4,3:194\n*S KotlinDebug\n*F\n+ 1 Platform.common.kt\nkotlinx/serialization/internal/Platform_commonKt\n*L\n74#1:192,2\n160#1:194,3\n*E\n"})
/* loaded from: classes5.dex */
public abstract class Platform_commonKt {
    private static final SerialDescriptor[] EMPTY_DESCRIPTOR_ARRAY = null;

    static {
        EMPTY_DESCRIPTOR_ARRAY = new SerialDescriptor[0];
    }

    public static final Set<String> cachedSerialNames(SerialDescriptor r4) {
        if ((r4 instanceof CachedNames) == true) goto L5;
        HashSet r0 = new HashSet(r4.getElementsCount());
        int r1 = r4.getElementsCount();
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L9;
        r0.add(r4.getElementName(r2));
        r2 = r2 + 1;
        goto L7
    L9:
        return r0;
    L5:
        return ((CachedNames) r4).getSerialNames();
    }

    public static final SerialDescriptor[] compactArray(List<? extends SerialDescriptor> r1) {
        List<? extends SerialDescriptor> r0 = r1;
        if (r0 != null) goto L5;
    L6:
        r1 = null;
    L7:
        if (r1 == null) goto L13;
        SerialDescriptor[] r12 = (SerialDescriptor[]) r1.toArray(new SerialDescriptor[0]);
        if (r12 == null) goto L13;
        return r12;
    L13:
        return EMPTY_DESCRIPTOR_ARRAY;
    L5:
        if (r0.isEmpty() == false) goto L7;
        goto L6
    }
}
