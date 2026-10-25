package androidx.graphics.shapes;

import android.graphics.Matrix;
import android.graphics.Path;
import androidx.collection.FloatFloatPair;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002\u001a\u001c\u0010\u0007\u001a\u00020\u0003*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a\u0016\u0010\u0007\u001a\u00020\u0003*\u00020\u000b2\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0012\u0010\f\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e¨\u0006\u000f"}, d2 = {"pathFromCubics", _UrlKt.FRAGMENT_ENCODE_SET, "path", "Landroid/graphics/Path;", "cubics", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/graphics/shapes/Cubic;", "toPath", "Landroidx/graphics/shapes/Morph;", "progress", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/graphics/shapes/RoundedPolygon;", "transformed", "matrix", "Landroid/graphics/Matrix;", "graphics-shapes_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class Shapes_androidKt {

    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\u0010\u0000\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "Landroidx/collection/FloatFloatPair;", "Landroidx/graphics/shapes/TransformResult;", "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "transform-XgqJiTY", "(FF)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.graphics.shapes.Shapes_androidKt$transformed$1, reason: invalid class name */
    public static final class AnonymousClass1 implements PointTransformer {
        final /* synthetic */ Matrix $matrix;
        final /* synthetic */ float[] $tempArray;

        public AnonymousClass1(float[] r1, Matrix r2) {
            this.$tempArray = r1;
            this.$matrix = r2;
        }

        @Override // androidx.graphics.shapes.PointTransformer
        /* renamed from: transform-XgqJiTY */
        public final long mo742transformXgqJiTY(float r3, float r4) {
            float[] r0 = this.$tempArray;
            r0[0] = r3;
            r0[1] = r4;
            this.$matrix.mapPoints(r0);
            float[] r2 = this.$tempArray;
            return FloatFloatPair.m659constructorimpl(r2[0], r2[1]);
        }
    }

    private static final void pathFromCubics(Path r12, List<? extends Cubic> r13) {
        r12.rewind();
        int r0 = r13.size();
        boolean r1 = true;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L8;
        Cubic r4 = r13.get(r3);
        if (r1 == false) goto L7;
        r12.moveTo(r4.getAnchor0X(), r4.getAnchor0Y());
        r1 = false;
    L7:
        r12.cubicTo(r4.getControl0X(), r4.getControl0Y(), r4.getControl1X(), r4.getControl1Y(), r4.getAnchor1X(), r4.getAnchor1Y());
        r3 = r3 + 1;
        goto L3
    L8:
        r12.close();
    }

    public static final Path toPath(Morph r0, float r1, Path r2) {
        pathFromCubics(r2, r0.asCubics(r1));
        return r2;
    }

    @JvmOverloads
    public static final Path toPath(RoundedPolygon r2) {
        return toPath$default(r2, null, 1, null);
    }

    @JvmOverloads
    public static final Path toPath(RoundedPolygon r0, Path r1) {
        pathFromCubics(r1, r0.getCubics());
        return r1;
    }

    public static /* synthetic */ Path toPath$default(RoundedPolygon r0, Path r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = new Path();
    L6:
        return toPath(r0, r1);
    }

    public static final RoundedPolygon transformed(RoundedPolygon r2, Matrix r3) {
        return r2.transformed(new AnonymousClass1(new float[2], r3));
    }
}
