package kotlinx.serialization.descriptors;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\n\u0010\u0005\u001a\u00020\u0006H\u0096\u0082\u0004J\n\u0010\u0007\u001a\u00020\u0002H\u0096\u0082\u0004R\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"kotlinx/serialization/descriptors/SerialDescriptorKt$elementDescriptors$1$1", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/serialization/descriptors/SerialDescriptor;", "elementsLeft", _UrlKt.FRAGMENT_ENCODE_SET, "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "next", "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SerialDescriptorKt$elementDescriptors$1$1 implements Iterator<SerialDescriptor>, KMappedMarker {
    final /* synthetic */ SerialDescriptor $this_elementDescriptors;
    private int elementsLeft;

    public SerialDescriptorKt$elementDescriptors$1$1(SerialDescriptor r1) {
        this.$this_elementDescriptors = r1;
        this.elementsLeft = r1.getElementsCount();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.elementsLeft <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator
    public /* bridge */ /* synthetic */ SerialDescriptor next() {
        return next2();
    }

    @Override // java.util.Iterator
    /* renamed from: next, reason: avoid collision after fix types in other method */
    public SerialDescriptor next2() {
        SerialDescriptor r0 = this.$this_elementDescriptors;
        int r1 = r0.getElementsCount();
        int r2 = this.elementsLeft;
        this.elementsLeft = r2 - 1;
        return r0.getElementDescriptor(r1 - r2);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
