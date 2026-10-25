package androidx.graphics.shapes;

import androidx.collection.MutableFloatList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 \r2\u00020\u0001:\u0001\rB1\u0012*\u0010\u0002\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00040\u0003\"\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\u000e\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005J\u000e\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/graphics/shapes/DoubleMapper;", _UrlKt.FRAGMENT_ENCODE_SET, "mappings", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Pair;", _UrlKt.FRAGMENT_ENCODE_SET, "([Lkotlin/Pair;)V", "sourceValues", "Landroidx/collection/MutableFloatList;", "targetValues", "map", "x", "mapBack", "Companion", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DoubleMapper {
    public static final Companion Companion = null;

    @JvmField
    public static final DoubleMapper Identity = null;
    private final MutableFloatList sourceValues;
    private final MutableFloatList targetValues;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/graphics/shapes/DoubleMapper$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "Identity", "Landroidx/graphics/shapes/DoubleMapper;", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
        Float r1 = Float.valueOf(0.0f);
        Pair r12 = TuplesKt.to(r1, r1);
        Float r2 = Float.valueOf(0.5f);
        Identity = new DoubleMapper(new Pair[]{r12, TuplesKt.to(r2, r2)});
    }

    public DoubleMapper(Pair<Float, Float>... r5) {
        this.sourceValues = new MutableFloatList(r5.length);
        this.targetValues = new MutableFloatList(r5.length);
        int r0 = r5.length;
        int r1 = 0;
    L3:
        MutableFloatList r2 = this.sourceValues;
        if (r1 >= r0) goto L6;
        r2.add(r5[r1].getFirst().floatValue());
        this.targetValues.add(r5[r1].getSecond().floatValue());
        r1 = r1 + 1;
        goto L3
    L6:
        FloatMappingKt.validateProgress(r2);
        FloatMappingKt.validateProgress(this.targetValues);
    }

    public final float map(float r2) {
        return FloatMappingKt.linearMap(this.sourceValues, this.targetValues, r2);
    }

    public final float mapBack(float r2) {
        return FloatMappingKt.linearMap(this.targetValues, this.sourceValues, r2);
    }
}
