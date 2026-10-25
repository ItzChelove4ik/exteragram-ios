package androidx.lifecycle.internal;

import android.os.Bundle;
import androidx.core.os.BundleKt;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateWriter;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.MutableStateFlow;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0002¢\u0006\u0004\b\u0005\u0010\u0006J(\u0010\u000b\u001a\u00020\n\"\u0004\b\u0000\u0010\u00072\u0006\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00018\u0000H\u0087\u0002¢\u0006\u0004\b\u000b\u0010\fR%\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R \u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00120\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u000fR(\u0010\u0015\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00140\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u000fR+\u0010\u0016\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00140\r8\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0017\u0010\u0011R\u0017\u0010\u0018\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Landroidx/lifecycle/internal/SavedStateHandleImpl;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "initialState", "<init>", "(Ljava/util/Map;)V", "T", PluginsConstants.Settings.KEY, "value", _UrlKt.FRAGMENT_ENCODE_SET, "set", "(Ljava/lang/String;Ljava/lang/Object;)V", _UrlKt.FRAGMENT_ENCODE_SET, "regular", "Ljava/util/Map;", "getRegular", "()Ljava/util/Map;", "Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "providers", "Lkotlinx/coroutines/flow/MutableStateFlow;", "flows", "mutableFlows", "getMutableFlows", "savedStateProvider", "Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "getSavedStateProvider", "()Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "lifecycle-viewmodel-savedstate"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSavedStateHandleImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SavedStateHandleImpl.kt\nandroidx/lifecycle/internal/SavedStateHandleImpl\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 SavedState.android.kt\nandroidx/savedstate/SavedStateKt__SavedState_androidKt\n+ 4 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 5 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 6 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 7 SavedState.kt\nandroidx/savedstate/SavedStateKt__SavedStateKt\n+ 8 SavedState.android.kt\nandroidx/savedstate/SavedStateKt__SavedState_androidKt$savedState$1\n*L\n1#1,131:1\n384#2,7:132\n384#2,7:139\n27#3:146\n47#3:147\n32#3,4:148\n31#3,8:158\n126#4:152\n153#4,3:153\n37#5,2:156\n1#6:166\n106#7:167\n47#8:168\n*S KotlinDebug\n*F\n+ 1 SavedStateHandleImpl.kt\nandroidx/lifecycle/internal/SavedStateHandleImpl\n*L\n60#1:132,7\n77#1:139,7\n47#1:146\n47#1:147\n47#1:148,4\n47#1:158,8\n47#1:152\n47#1:153,3\n47#1:156,2\n47#1:166\n47#1:167\n47#1:168\n*E\n"})
/* loaded from: classes3.dex */
public final class SavedStateHandleImpl {
    private final Map<String, MutableStateFlow<Object>> flows;
    private final Map<String, MutableStateFlow<Object>> mutableFlows;
    private final Map<String, SavedStateRegistry.SavedStateProvider> providers;
    private final Map<String, Object> regular;
    private final SavedStateRegistry.SavedStateProvider savedStateProvider;

    /* renamed from: $r8$lambda$-OwJNegmCu5Gt1ZLmTJOtaJJkzo, reason: not valid java name */
    public static Bundle m752$r8$lambda$OwJNegmCu5Gt1ZLmTJOtaJJkzo(SavedStateHandleImpl r4) {
        Iterator r0 = MapsKt.toMap(r4.mutableFlows).entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        Map.Entry r1 = (Map.Entry) r0.next();
        r4.set((String) r1.getKey(), ((MutableStateFlow) r1.getValue()).getValue());
        goto L4
    L6:
        Iterator r02 = MapsKt.toMap(r4.providers).entrySet().iterator();
    L8:
        if (r02.hasNext() == false) goto L10;
        Map.Entry r12 = (Map.Entry) r02.next();
        r4.set((String) r12.getKey(), ((SavedStateRegistry.SavedStateProvider) r12.getValue()).saveState());
        goto L8
    L10:
        Map<String, Object> r42 = r4.regular;
        if (r42.isEmpty() == false) goto L13;
        Pair[] r43 = new Pair[0];
    L18:
        Bundle r44 = BundleKt.bundleOf((Pair[]) Arrays.copyOf(r43, r43.length));
        SavedStateWriter.m956constructorimpl(r44);
        return r44;
    L13:
        ArrayList r03 = new ArrayList(r42.size());
        Iterator<Map.Entry<String, Object>> r45 = r42.entrySet().iterator();
    L15:
        if (r45.hasNext() == false) goto L17;
        Map.Entry<String, Object> r2 = r45.next();
        r03.add(TuplesKt.to(r2.getKey(), r2.getValue()));
        goto L15
    L17:
        r43 = (Pair[]) r03.toArray(new Pair[0]);
        goto L18
    }

    public SavedStateHandleImpl(Map<String, ? extends Object> r1) {
        this.regular = MapsKt.toMutableMap(r1);
        this.providers = new LinkedHashMap();
        this.flows = new LinkedHashMap();
        this.mutableFlows = new LinkedHashMap();
        this.savedStateProvider = new SavedStateHandleImpl$$ExternalSyntheticLambda0(this);
    }

    public /* synthetic */ SavedStateHandleImpl(Map r1, int r2, DefaultConstructorMarker r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = MapsKt.emptyMap();
    L5:
        this(r1);
    }

    public final SavedStateRegistry.SavedStateProvider getSavedStateProvider() {
        return this.savedStateProvider;
    }

    public final <T> void set(String r2, T r3) {
        this.regular.put(r2, r3);
        MutableStateFlow<Object> r0 = this.flows.get(r2);
        if (r0 == null) goto L5;
        r0.setValue(r3);
    L5:
        MutableStateFlow<Object> r1 = this.mutableFlows.get(r2);
        if (r1 == null) goto L9;
        r1.setValue(r3);
        return;
    }
}
