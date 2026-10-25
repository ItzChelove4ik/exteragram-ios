package androidx.lifecycle;

import android.os.Bundle;
import androidx.core.os.BundleKt;
import androidx.savedstate.SavedStateReader;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateWriter;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\f\u0010\u0013\u001a\u00060\u000bj\u0002`\fH\u0016J\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\f2\u0006\u0010\u0017\u001a\u00020\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0019"}, d2 = {"Landroidx/lifecycle/SavedStateHandlesProvider;", "Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "savedStateRegistry", "Landroidx/savedstate/SavedStateRegistry;", "viewModelStoreOwner", "Landroidx/lifecycle/ViewModelStoreOwner;", "<init>", "(Landroidx/savedstate/SavedStateRegistry;Landroidx/lifecycle/ViewModelStoreOwner;)V", "restored", _UrlKt.FRAGMENT_ENCODE_SET, "restoredState", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "viewModel", "Landroidx/lifecycle/SavedStateHandlesVM;", "getViewModel", "()Landroidx/lifecycle/SavedStateHandlesVM;", "viewModel$delegate", "Lkotlin/Lazy;", "saveState", "performRestore", _UrlKt.FRAGMENT_ENCODE_SET, "consumeRestoredStateForKey", PluginsConstants.Settings.KEY, _UrlKt.FRAGMENT_ENCODE_SET, "lifecycle-viewmodel-savedstate"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSavedStateHandleSupport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SavedStateHandleSupport.kt\nandroidx/lifecycle/SavedStateHandlesProvider\n+ 2 SavedState.android.kt\nandroidx/savedstate/SavedStateKt__SavedState_androidKt\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 6 SavedState.kt\nandroidx/savedstate/SavedStateKt__SavedStateKt\n+ 7 SavedState.android.kt\nandroidx/savedstate/SavedStateKt__SavedState_androidKt$savedState$1\n*L\n1#1,246:1\n27#2:247\n47#2:248\n32#2,4:249\n31#2,8:259\n27#2:273\n47#2:274\n32#2,4:275\n31#2,8:285\n27#2:298\n47#2:299\n32#2,4:300\n31#2,8:310\n126#3:253\n153#3,3:254\n216#3:270\n217#3:272\n126#3:279\n153#3,3:280\n126#3:304\n153#3,3:305\n37#4,2:257\n37#4,2:283\n37#4,2:308\n1#5:267\n1#5:269\n1#5:293\n1#5:295\n1#5:318\n106#6:268\n90#6:271\n106#6:294\n90#6:296\n90#6:297\n106#6:319\n106#6:321\n90#6:322\n47#7:320\n*S KotlinDebug\n*F\n+ 1 SavedStateHandleSupport.kt\nandroidx/lifecycle/SavedStateHandlesProvider\n*L\n165#1:247\n165#1:248\n165#1:249,4\n165#1:259,8\n188#1:273\n188#1:274\n188#1:275,4\n188#1:285,8\n206#1:298\n206#1:299\n206#1:300,4\n206#1:310,8\n165#1:253\n165#1:254,3\n172#1:270\n172#1:272\n188#1:279\n188#1:280,3\n206#1:304\n206#1:305,3\n165#1:257,2\n188#1:283,2\n206#1:308,2\n165#1:267\n188#1:293\n206#1:318\n165#1:268\n174#1:271\n188#1:294\n204#1:296\n206#1:297\n206#1:319\n207#1:321\n208#1:322\n206#1:320\n*E\n"})
/* loaded from: classes.dex */
public final class SavedStateHandlesProvider implements SavedStateRegistry.SavedStateProvider {
    private boolean restored;
    private Bundle restoredState;
    private final SavedStateRegistry savedStateRegistry;
    private final Lazy viewModel$delegate;

    public static SavedStateHandlesVM $r8$lambda$BWYiFNbmaxFqlGaoxfo2jDYwXi4(ViewModelStoreOwner r0) {
        return SavedStateHandleSupport.getSavedStateHandlesVM(r0);
    }

    public SavedStateHandlesProvider(SavedStateRegistry r1, ViewModelStoreOwner r2) {
        this.savedStateRegistry = r1;
        this.viewModel$delegate = LazyKt.lazy(new SavedStateHandlesProvider$$ExternalSyntheticLambda0(r2));
    }

    private final SavedStateHandlesVM getViewModel() {
        return (SavedStateHandlesVM) this.viewModel$delegate.getValue();
    }

    public final Bundle consumeRestoredStateForKey(String r8) {
        performRestore();
        Bundle r0 = this.restoredState;
        if (r0 != null) goto L6;
        return null;
    L6:
        if (SavedStateReader.m950containsimpl(SavedStateReader.m949constructorimpl(r0), r8) == true) goto L8;
        return null;
    L8:
        Bundle r2 = SavedStateReader.m952getSavedStateOrNullimpl(SavedStateReader.m949constructorimpl(r0), r8);
        if (r2 != null) goto L19;
        Map r22 = MapsKt.emptyMap();
        if (r22.isEmpty() == false) goto L13;
        Pair[] r23 = new Pair[0];
    L18:
        r2 = BundleKt.bundleOf((Pair[]) Arrays.copyOf(r23, r23.length));
        SavedStateWriter.m956constructorimpl(r2);
        goto L19
    L13:
        ArrayList r3 = new ArrayList(r22.size());
        Iterator r24 = r22.entrySet().iterator();
    L15:
        if (r24.hasNext() == false) goto L17;
        Map.Entry r5 = (Map.Entry) r24.next();
        r3.add(TuplesKt.to((String) r5.getKey(), r5.getValue()));
        goto L15
    L17:
        r23 = (Pair[]) r3.toArray(new Pair[0]);
    L19:
        SavedStateWriter.m960removeimpl(SavedStateWriter.m956constructorimpl(r0), r8);
        if (SavedStateReader.m954isEmptyimpl(SavedStateReader.m949constructorimpl(r0)) == false) goto L22;
        this.restoredState = null;
    L22:
        return r2;
    }

    public final void performRestore() {
        if (this.restored == true) goto L20;
        Bundle r0 = this.savedStateRegistry.consumeRestoredStateForKey("androidx.lifecycle.internal.SavedStateHandlesProvider");
        Map r1 = MapsKt.emptyMap();
        if (r1.isEmpty() == false) goto L7;
        Pair[] r12 = new Pair[0];
    L12:
        Bundle r13 = BundleKt.bundleOf((Pair[]) Arrays.copyOf(r12, r12.length));
        Bundle r2 = SavedStateWriter.m956constructorimpl(r13);
        Bundle r3 = this.restoredState;
        if (r3 == null) goto L15;
        SavedStateWriter.m957putAllimpl(r2, r3);
    L15:
        if (r0 == null) goto L17;
        SavedStateWriter.m957putAllimpl(r2, r0);
    L17:
        this.restoredState = r13;
        this.restored = true;
        getViewModel();
        return;
    L7:
        ArrayList r22 = new ArrayList(r1.size());
        Iterator r14 = r1.entrySet().iterator();
    L9:
        if (r14.hasNext() == false) goto L11;
        Map.Entry r4 = (Map.Entry) r14.next();
        r22.add(TuplesKt.to((String) r4.getKey(), r4.getValue()));
        goto L9
    L11:
        r12 = (Pair[]) r22.toArray(new Pair[0]);
        goto L12
    }

    @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
    public Bundle saveState() {
        Map r0 = MapsKt.emptyMap();
        if (r0.isEmpty() == false) goto L5;
        Pair[] r02 = new Pair[0];
    L10:
        Bundle r03 = BundleKt.bundleOf((Pair[]) Arrays.copyOf(r02, r02.length));
        Bundle r1 = SavedStateWriter.m956constructorimpl(r03);
        Bundle r3 = this.restoredState;
        if (r3 == null) goto L13;
        SavedStateWriter.m957putAllimpl(r1, r3);
    L13:
        Iterator<Map.Entry<String, SavedStateHandle>> r32 = getViewModel().getHandles().entrySet().iterator();
    L15:
        if (r32.hasNext() == false) goto L19;
        Map.Entry<String, SavedStateHandle> r4 = r32.next();
        String r5 = r4.getKey();
        Bundle r42 = r4.getValue().savedStateProvider().saveState();
        if (SavedStateReader.m954isEmptyimpl(SavedStateReader.m949constructorimpl(r42)) == true) goto L15;
        SavedStateWriter.m958putSavedStateimpl(r1, r5, r42);
        goto L15
    L19:
        this.restored = false;
        return r03;
    L5:
        ArrayList r12 = new ArrayList(r0.size());
        Iterator r04 = r0.entrySet().iterator();
    L7:
        if (r04.hasNext() == false) goto L9;
        Map.Entry r33 = (Map.Entry) r04.next();
        r12.add(TuplesKt.to((String) r33.getKey(), r33.getValue()));
        goto L7
    L9:
        r02 = (Pair[]) r12.toArray(new Pair[0]);
        goto L10
    }
}
