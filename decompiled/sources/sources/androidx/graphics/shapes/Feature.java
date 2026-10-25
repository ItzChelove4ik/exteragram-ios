package androidx.graphics.shapes;

import androidx.collection.FloatFloatPair;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018\u00002\u00020\u0001:\u0002\f\rB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0015\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nH ¢\u0006\u0002\b\u000bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Landroidx/graphics/shapes/Feature;", _UrlKt.FRAGMENT_ENCODE_SET, "cubics", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/graphics/shapes/Cubic;", "(Ljava/util/List;)V", "getCubics", "()Ljava/util/List;", "transformed", "f", "Landroidx/graphics/shapes/PointTransformer;", "transformed$graphics_shapes_release", "Corner", "Edge", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class Feature {
    private final List<Cubic> cubics;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B7\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0010\u0007\u001a\u00060\u0005j\u0002`\u0006\u0012\n\u0010\b\u001a\u00060\u0005j\u0002`\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\rH\u0010¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014R!\u0010\u0007\u001a\u00060\u0005j\u0002`\u00068\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0007\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R!\u0010\b\u001a\u00060\u0005j\u0002`\u00068\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\b\u0010\u0015\u001a\u0004\b\u0018\u0010\u0017R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001c"}, d2 = {"Landroidx/graphics/shapes/Feature$Corner;", "Landroidx/graphics/shapes/Feature;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/graphics/shapes/Cubic;", "cubics", "Landroidx/collection/FloatFloatPair;", "Landroidx/graphics/shapes/Point;", "vertex", "roundedCenter", _UrlKt.FRAGMENT_ENCODE_SET, "convex", "<init>", "(Ljava/util/List;JJZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "Landroidx/graphics/shapes/PointTransformer;", "f", "transformed$graphics_shapes_release", "(Landroidx/graphics/shapes/PointTransformer;)Landroidx/graphics/shapes/Feature;", "transformed", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "J", "getVertex-1ufDz9w", "()J", "getRoundedCenter-1ufDz9w", "Z", "getConvex", "()Z", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Corner extends Feature {
        private final boolean convex;
        private final long roundedCenter;
        private final long vertex;

        private Corner(List<? extends Cubic> r1, long r2, long r4, boolean r6) {
            super(r1);
            this.vertex = r2;
            this.roundedCenter = r4;
            this.convex = r6;
        }

        public /* synthetic */ Corner(List r1, long r2, long r4, boolean r6, DefaultConstructorMarker r7) {
            this(r1, r2, r4, r6);
        }

        public final boolean getConvex() {
            return this.convex;
        }

        public String toString() {
            return "Corner: vertex=" + FloatFloatPair.m663toStringimpl(this.vertex) + ", center=" + FloatFloatPair.m663toStringimpl(this.roundedCenter) + ", convex=" + this.convex;
        }

        @Override // androidx.graphics.shapes.Feature
        public Feature transformed$graphics_shapes_release(PointTransformer r12) {
            List r0 = CollectionsKt.createListBuilder();
            int r1 = getCubics().size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L6;
            r0.add(getCubics().get(r2).transformed(r12));
            r2 = r2 + 1;
            goto L3
        L6:
            return new Corner(CollectionsKt.build(r0), PointKt.m741transformedso9K2fw(this.vertex, r12), PointKt.m741transformedso9K2fw(this.roundedCenter, r12), this.convex, null);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0015\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nH\u0010¢\u0006\u0002\b\u000b¨\u0006\f"}, d2 = {"Landroidx/graphics/shapes/Feature$Edge;", "Landroidx/graphics/shapes/Feature;", "cubics", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/graphics/shapes/Cubic;", "(Ljava/util/List;)V", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "transformed", "f", "Landroidx/graphics/shapes/PointTransformer;", "transformed$graphics_shapes_release", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Edge extends Feature {
        public Edge(List<? extends Cubic> r1) {
            super(r1);
        }

        public String toString() {
            return "Edge";
        }

        @Override // androidx.graphics.shapes.Feature
        public Edge transformed$graphics_shapes_release(PointTransformer r5) {
            List r0 = CollectionsKt.createListBuilder();
            int r1 = getCubics().size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L6;
            r0.add(getCubics().get(r2).transformed(r5));
            r2 = r2 + 1;
            goto L3
        L6:
            return new Edge(CollectionsKt.build(r0));
        }

        @Override // androidx.graphics.shapes.Feature
        public /* bridge */ /* synthetic */ Feature transformed$graphics_shapes_release(PointTransformer r1) {
            return transformed$graphics_shapes_release(r1);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Feature(List<? extends Cubic> r1) {
        this.cubics = r1;
    }

    public final List<Cubic> getCubics() {
        return this.cubics;
    }

    public abstract Feature transformed$graphics_shapes_release(PointTransformer r1);
}
