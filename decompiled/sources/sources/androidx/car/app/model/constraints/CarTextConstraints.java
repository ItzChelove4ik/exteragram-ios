package androidx.car.app.model.constraints;

import androidx.car.app.model.CarIconSpan;
import androidx.car.app.model.CarSpan;
import androidx.car.app.model.CarText;
import androidx.car.app.model.ClickableSpan;
import androidx.car.app.model.DistanceSpan;
import androidx.car.app.model.DurationSpan;
import androidx.car.app.model.ForegroundCarColorSpan;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public final class CarTextConstraints {
    public static final CarTextConstraints CLICKABLE_TEXT_ONLY = null;
    public static final CarTextConstraints COLOR_ONLY = null;
    public static final CarTextConstraints CONSERVATIVE = null;
    public static final CarTextConstraints TEXT_AND_ICON = null;
    public static final CarTextConstraints TEXT_ONLY = null;
    public static final CarTextConstraints TEXT_WITH_COLORS = null;
    public static final CarTextConstraints TEXT_WITH_COLORS_AND_ICON = null;
    public static final CarTextConstraints UNCONSTRAINED = null;
    private final HashSet<Class<? extends CarSpan>> mAllowedTypes;

    static {
        CONSERVATIVE = new CarTextConstraints(Collections.EMPTY_LIST);
        UNCONSTRAINED = new CarTextConstraints(Arrays.asList(new Class[]{CarIconSpan.class, ClickableSpan.class, DistanceSpan.class, DurationSpan.class, ForegroundCarColorSpan.class}));
        CLICKABLE_TEXT_ONLY = new CarTextConstraints(Arrays.asList(new Class[]{ClickableSpan.class, DistanceSpan.class, DurationSpan.class}));
        COLOR_ONLY = new CarTextConstraints(Arrays.asList(new Class[]{ForegroundCarColorSpan.class}));
        TEXT_ONLY = new CarTextConstraints(Arrays.asList(new Class[]{DistanceSpan.class, DurationSpan.class}));
        TEXT_AND_ICON = new CarTextConstraints(Arrays.asList(new Class[]{DistanceSpan.class, DurationSpan.class, CarIconSpan.class}));
        TEXT_WITH_COLORS = new CarTextConstraints(Arrays.asList(new Class[]{DistanceSpan.class, DurationSpan.class, ForegroundCarColorSpan.class}));
        TEXT_WITH_COLORS_AND_ICON = new CarTextConstraints(Arrays.asList(new Class[]{DistanceSpan.class, DurationSpan.class, ForegroundCarColorSpan.class, CarIconSpan.class}));
    }

    private CarTextConstraints(List<Class<? extends CarSpan>> r2) {
        this.mAllowedTypes = new HashSet(r2);
    }

    private void checkSupportedSpans(List<CarText.SpanWrapper> r3) {
        Iterator<CarText.SpanWrapper> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L13;
        Class<?> r0 = r32.next().getCarSpan().getClass();
        if (this.mAllowedTypes.contains(r0) == true) goto L4;
        f$$ExternalSyntheticBUOutline1.m("CarSpan type is not allowed: ".concat(r0.getSimpleName()));
        return;
    }

    public void validateOrThrow(CarText r2) {
        checkSupportedSpans(r2.getSpans());
        Iterator<List<CarText.SpanWrapper>> r22 = r2.getSpansForVariants().iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        checkSupportedSpans(r22.next());
        goto L4
    }
}
