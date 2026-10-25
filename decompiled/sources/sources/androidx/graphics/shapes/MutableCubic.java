package androidx.graphics.shapes;

import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/graphics/shapes/MutableCubic;", "Landroidx/graphics/shapes/Cubic;", "<init>", "()V", "Landroidx/graphics/shapes/PointTransformer;", "f", _UrlKt.FRAGMENT_ENCODE_SET, "ix", _UrlKt.FRAGMENT_ENCODE_SET, "transformOnePoint", "(Landroidx/graphics/shapes/PointTransformer;I)V", "transform", "(Landroidx/graphics/shapes/PointTransformer;)V", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCubic.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Cubic.kt\nandroidx/graphics/shapes/MutableCubic\n+ 2 FloatFloatPair.kt\nandroidx/collection/FloatFloatPair\n+ 3 PackingHelpers.jvm.kt\nandroidx/collection/internal/PackingHelpers_jvmKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,448:1\n48#2:449\n54#2:451\n22#3:450\n22#3:452\n1#4:453\n*S KotlinDebug\n*F\n+ 1 Cubic.kt\nandroidx/graphics/shapes/MutableCubic\n*L\n433#1:449\n434#1:451\n433#1:450\n434#1:452\n*E\n"})
/* loaded from: classes3.dex */
public final class MutableCubic extends Cubic {
    public MutableCubic() {
        super(null, 1, null);
    }

    private final void transformOnePoint(PointTransformer r6, int r7) {
        int r2 = r7 + 1;
        long r0 = r6.mo742transformXgqJiTY(getPoints$graphics_shapes_release()[r7], getPoints$graphics_shapes_release()[r2]);
        getPoints$graphics_shapes_release()[r7] = Float.intBitsToFloat((int) (r0 >> 32));
        getPoints$graphics_shapes_release()[r2] = Float.intBitsToFloat((int) (4294967295L & r0));
    }

    public final void transform(PointTransformer r2) {
        transformOnePoint(r2, 0);
        transformOnePoint(r2, 2);
        transformOnePoint(r2, 4);
        transformOnePoint(r2, 6);
    }
}
