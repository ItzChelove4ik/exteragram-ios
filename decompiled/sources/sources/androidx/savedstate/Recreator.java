package androidx.savedstate;

import android.os.Bundle;
import androidx.core.os.BundleKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.savedstate.SavedStateRegistry;
import c.a$$ExternalSyntheticBUOutline0;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline2;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00102\u00020\u0001:\u0002\u000f\u0010B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/savedstate/Recreator;", "Landroidx/lifecycle/LifecycleEventObserver;", "owner", "Landroidx/savedstate/SavedStateRegistryOwner;", "<init>", "(Landroidx/savedstate/SavedStateRegistryOwner;)V", "onStateChanged", _UrlKt.FRAGMENT_ENCODE_SET, "source", "Landroidx/lifecycle/LifecycleOwner;", "event", "Landroidx/lifecycle/Lifecycle$Event;", "reflectiveNew", "className", _UrlKt.FRAGMENT_ENCODE_SET, "SavedStateProvider", "Companion", "savedstate"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRecreator.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Recreator.android.kt\nandroidx/savedstate/Recreator\n+ 2 SavedState.kt\nandroidx/savedstate/SavedStateKt__SavedStateKt\n*L\n1#1,98:1\n90#2:99\n*S KotlinDebug\n*F\n+ 1 Recreator.android.kt\nandroidx/savedstate/Recreator\n*L\n34#1:99\n*E\n"})
/* loaded from: classes.dex */
public final class Recreator implements LifecycleEventObserver {
    public static final Companion Companion = null;
    private final SavedStateRegistryOwner owner;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/savedstate/Recreator$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "CLASSES_KEY", _UrlKt.FRAGMENT_ENCODE_SET, "COMPONENT_KEY", "savedstate"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\t\u001a\u00060\nj\u0002`\u000bH\u0016J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/savedstate/Recreator$SavedStateProvider;", "Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "registry", "Landroidx/savedstate/SavedStateRegistry;", "<init>", "(Landroidx/savedstate/SavedStateRegistry;)V", "classes", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "saveState", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "add", _UrlKt.FRAGMENT_ENCODE_SET, "className", "savedstate"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nRecreator.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Recreator.android.kt\nandroidx/savedstate/Recreator$SavedStateProvider\n+ 2 SavedState.android.kt\nandroidx/savedstate/SavedStateKt__SavedState_androidKt\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 6 SavedState.kt\nandroidx/savedstate/SavedStateKt__SavedStateKt\n*L\n1#1,98:1\n27#2:99\n47#2:100\n32#2,4:101\n31#2,8:111\n126#3:105\n153#3,3:106\n37#4,2:109\n1#5:119\n106#6:120\n*S KotlinDebug\n*F\n+ 1 Recreator.android.kt\nandroidx/savedstate/Recreator$SavedStateProvider\n*L\n84#1:99\n84#1:100\n84#1:101,4\n84#1:111,8\n84#1:105\n84#1:106,3\n84#1:109,2\n84#1:119\n84#1:120\n*E\n"})
    /* loaded from: classes4.dex */
    public static final class SavedStateProvider implements SavedStateRegistry.SavedStateProvider {
        private final Set<String> classes;

        public SavedStateProvider(SavedStateRegistry r2) {
            this.classes = new LinkedHashSet();
            r2.registerSavedStateProvider("androidx.savedstate.Restarter", this);
        }

        public final void add(String r1) {
            this.classes.add(r1);
        }

        @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
        public Bundle saveState() {
            Map r0 = MapsKt.emptyMap();
            if (r0.isEmpty() == false) goto L5;
            Pair[] r02 = new Pair[0];
        L10:
            Bundle r03 = BundleKt.bundleOf((Pair[]) Arrays.copyOf(r02, r02.length));
            SavedStateWriter.m959putStringListimpl(SavedStateWriter.m956constructorimpl(r03), "classes_to_restore", CollectionsKt.toList(this.classes));
            return r03;
        L5:
            ArrayList r1 = new ArrayList(r0.size());
            Iterator r04 = r0.entrySet().iterator();
        L7:
            if (r04.hasNext() == false) goto L9;
            Map.Entry r3 = (Map.Entry) r04.next();
            r1.add(TuplesKt.to((String) r3.getKey(), r3.getValue()));
            goto L7
        L9:
            r02 = (Pair[]) r1.toArray(new Pair[0]);
            goto L10
        }
    }

    static {
        Companion = new Companion(null);
    }

    public Recreator(SavedStateRegistryOwner r1) {
        this.owner = r1;
    }

    private final void reflectiveNew(String r4) {
        Class<? extends U> r1 = Class.forName(r4, false, Recreator.class.getClassLoader()).asSubclass(SavedStateRegistry.AutoRecreated.class);     // Catch: ClassNotFoundException -> L16
        Constructor r0 = r1.getDeclaredConstructor(null);     // Catch: NoSuchMethodException -> L13
        r0.setAccessible(true);
        ((SavedStateRegistry.AutoRecreated) r0.newInstance(null)).onRecreated(this.owner);
        return;
    L10:
        e = move-exception;
        a$$ExternalSyntheticBUOutline0.m("Failed to instantiate ", r4, e);
        return;
    L13:
        e = move-exception;
        throw new IllegalStateException("Class " + r1.getSimpleName() + " must have default constructor in order to be automatically recreated", e);
    L16:
        e = move-exception;
        Recreator$$ExternalSyntheticBUOutline0.m("Class ", r4, " wasn't found", e);
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner r2, Lifecycle.Event r3) {
        if (r3 != Lifecycle.Event.ON_CREATE) goto L16;
        r2.getLifecycle().removeObserver(this);
        Bundle r22 = this.owner.getSavedStateRegistry().consumeRestoredStateForKey("androidx.savedstate.Restarter");
        if (r22 == null) goto L13;
        List<String> r23 = SavedStateReader.m953getStringListOrNullimpl(SavedStateReader.m949constructorimpl(r22), "classes_to_restore");
        if (r23 == null) goto L14;
        Iterator<String> r24 = r23.iterator();
    L11:
        if (r24.hasNext() == false) goto L19;
        reflectiveNew(r24.next());
        goto L11
    L19:
        return;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("SavedState with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
        return;
    L13:
        return;
    L16:
        Buffer$$ExternalSyntheticBUOutline2.m("Next event must be ON_CREATE");
    }
}
