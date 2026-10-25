package androidx.work.impl.constraints;

import androidx.work.Logger;
import androidx.work.impl.constraints.controllers.ConstraintController;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tJ\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/work/impl/constraints/WorkConstraintsTracker;", _UrlKt.FRAGMENT_ENCODE_SET, "controllers", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/work/impl/constraints/controllers/ConstraintController;", "<init>", "(Ljava/util/List;)V", "trackers", "Landroidx/work/impl/constraints/trackers/Trackers;", "(Landroidx/work/impl/constraints/trackers/Trackers;)V", "track", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/work/impl/constraints/ConstraintsState;", "spec", "Landroidx/work/impl/model/WorkSpec;", "areAllConstraintsMet", _UrlKt.FRAGMENT_ENCODE_SET, "workSpec", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWorkConstraintsTracker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorkConstraintsTracker.kt\nandroidx/work/impl/constraints/WorkConstraintsTracker\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Zip.kt\nkotlinx/coroutines/flow/FlowKt__ZipKt\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 5 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,394:1\n774#2:395\n865#2,2:396\n1563#2:398\n1634#2,3:399\n774#2:407\n865#2,2:408\n283#3:402\n284#3:405\n37#4,2:403\n105#5:406\n*S KotlinDebug\n*F\n+ 1 WorkConstraintsTracker.kt\nandroidx/work/impl/constraints/WorkConstraintsTracker\n*L\n82#1:395\n82#1:396,2\n82#1:398\n82#1:399,3\n90#1:407\n90#1:408,2\n83#1:402\n83#1:405\n83#1:403,2\n83#1:406\n*E\n"})
/* loaded from: classes.dex */
public final class WorkConstraintsTracker {
    private final List<ConstraintController> controllers;

    /* renamed from: $r8$lambda$1G7rUPF21-ZPnX0hYpH5ELtRBso, reason: not valid java name */
    public static CharSequence m983$r8$lambda$1G7rUPF21ZPnX0hYpH5ELtRBso(ConstraintController r0) {
        return r0.getClass().getSimpleName();
    }

    public WorkConstraintsTracker(Trackers r1) {
        this(WorkConstraintsTrackerKt.access$createControllers(r1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public WorkConstraintsTracker(List<? extends ConstraintController> r1) {
        this.controllers = r1;
    }

    public final boolean areAllConstraintsMet(WorkSpec r12) {
        List<ConstraintController> r11 = this.controllers;
        ArrayList r0 = new ArrayList();
        Iterator<T> r112 = r11.iterator();
    L4:
        if (r112.hasNext() == false) goto L9;
        Object r1 = r112.next();
        if (((ConstraintController) r1).isCurrentlyConstrained(r12) == false) goto L4;
        r0.add(r1);
        goto L4
    L9:
        if (r0.isEmpty() == true) goto L12;
        Logger.get().debug(WorkConstraintsTrackerKt.access$getTAG$p(), "Work " + r12.id + " constrained by " + CollectionsKt.joinToString$default(r0, null, null, null, 0, null, new WorkConstraintsTracker$$ExternalSyntheticLambda0(), 31, null));
    L12:
        return r0.isEmpty();
    }

    public final Flow<ConstraintsState> track(WorkSpec r7) {
        List<ConstraintController> r6 = this.controllers;
        ArrayList r0 = new ArrayList();
        Iterator<T> r62 = r6.iterator();
    L4:
        if (r62.hasNext() == false) goto L8;
        Object r1 = r62.next();
        if (((ConstraintController) r1).hasConstraint(r7) == false) goto L4;
        r0.add(r1);
        goto L4
    L8:
        ArrayList r63 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r0, 10));
        int r12 = r0.size();
        int r3 = 0;
    L9:
        if (r3 >= r12) goto L12;
        Object r4 = r0.get(r3);
        r3 = r3 + 1;
        r63.add(((ConstraintController) r4).track(r7.constraints));
        goto L9
    L12:
        return FlowKt.distinctUntilChanged(new WorkConstraintsTracker$track$$inlined$combine$1((Flow[]) CollectionsKt.toList(r63).toArray(new Flow[0])));
    }
}
