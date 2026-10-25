package androidx.lifecycle;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import okhttp3.OkHttpClient$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\u0016B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J3\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0007¢\u0006\u0004\b\r\u0010\u000eJ'\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Landroidx/lifecycle/LegacySavedStateHandleController;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/savedstate/SavedStateRegistry;", "registry", "Landroidx/lifecycle/Lifecycle;", "lifecycle", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "Landroid/os/Bundle;", "defaultArgs", "Landroidx/lifecycle/SavedStateHandleController;", "create", "(Landroidx/savedstate/SavedStateRegistry;Landroidx/lifecycle/Lifecycle;Ljava/lang/String;Landroid/os/Bundle;)Landroidx/lifecycle/SavedStateHandleController;", "Landroidx/lifecycle/ViewModel;", "viewModel", _UrlKt.FRAGMENT_ENCODE_SET, "attachHandleIfNeeded", "(Landroidx/lifecycle/ViewModel;Landroidx/savedstate/SavedStateRegistry;Landroidx/lifecycle/Lifecycle;)V", "tryToAddRecreator", "(Landroidx/savedstate/SavedStateRegistry;Landroidx/lifecycle/Lifecycle;)V", "OnRecreation", "lifecycle-viewmodel-savedstate"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LegacySavedStateHandleController {
    public static final LegacySavedStateHandleController INSTANCE = null;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Landroidx/lifecycle/LegacySavedStateHandleController$OnRecreation;", "Landroidx/savedstate/SavedStateRegistry$AutoRecreated;", "<init>", "()V", "onRecreated", _UrlKt.FRAGMENT_ENCODE_SET, "owner", "Landroidx/savedstate/SavedStateRegistryOwner;", "lifecycle-viewmodel-savedstate"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class OnRecreation implements SavedStateRegistry.AutoRecreated {
        public OnRecreation() {
        }

        @Override // androidx.savedstate.SavedStateRegistry.AutoRecreated
        public void onRecreated(SavedStateRegistryOwner r5) {
            if ((r5 instanceof ViewModelStoreOwner) == false) goto L15;
            ViewModelStore r4 = ((ViewModelStoreOwner) r5).getViewModelStore();
            SavedStateRegistry r0 = r5.getSavedStateRegistry();
            Iterator<Object> r1 = r4.keys().iterator();
        L6:
            if (r1.hasNext() == false) goto L12;
            ViewModel r2 = r4.get(r1.next());
            if (r2 == null) goto L6;
            LegacySavedStateHandleController.attachHandleIfNeeded(r2, r0, r5.getLifecycle());
            goto L6
        L12:
            if (r4.keys().isEmpty() == true) goto L22;
            r0.runOnNextRecreation(OnRecreation.class);
            return;
        L22:
            return;
        L15:
            OkHttpClient$$ExternalSyntheticBUOutline0.m("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner. Received owner: ", r5);
        }
    }

    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"androidx/lifecycle/LegacySavedStateHandleController$tryToAddRecreator$1", "Landroidx/lifecycle/LifecycleEventObserver;", "onStateChanged", _UrlKt.FRAGMENT_ENCODE_SET, "source", "Landroidx/lifecycle/LifecycleOwner;", "event", "Landroidx/lifecycle/Lifecycle$Event;", "lifecycle-viewmodel-savedstate"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: androidx.lifecycle.LegacySavedStateHandleController$tryToAddRecreator$1, reason: invalid class name */
    public static final class AnonymousClass1 implements LifecycleEventObserver {
        final /* synthetic */ Lifecycle $lifecycle;
        final /* synthetic */ SavedStateRegistry $registry;

        public AnonymousClass1(Lifecycle r1, SavedStateRegistry r2) {
            this.$lifecycle = r1;
            this.$registry = r2;
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner r1, Lifecycle.Event r2) {
            if (r2 != Lifecycle.Event.ON_START) goto L6;
            this.$lifecycle.removeObserver(this);
            this.$registry.runOnNextRecreation(OnRecreation.class);
            return;
        }
    }

    static {
        INSTANCE = new LegacySavedStateHandleController();
    }

    private LegacySavedStateHandleController() {
    }

    @JvmStatic
    public static final void attachHandleIfNeeded(ViewModel r1, SavedStateRegistry r2, Lifecycle r3) {
        SavedStateHandleController r12 = (SavedStateHandleController) r1.getCloseable("androidx.lifecycle.savedstate.vm.tag");
        if (r12 != null) goto L5;
        return;
    L5:
        if (r12.isAttached() == true) goto L9;
        r12.attachToLifecycle(r2, r3);
        INSTANCE.tryToAddRecreator(r2, r3);
        return;
    }

    @JvmStatic
    public static final SavedStateHandleController create(SavedStateRegistry r2, Lifecycle r3, String r4, Bundle r5) {
        Bundle r0 = r2.consumeRestoredStateForKey(r4);
        SavedStateHandleController r02 = new SavedStateHandleController(r4, SavedStateHandle.Companion.createHandle(r0, r5));
        r02.attachToLifecycle(r2, r3);
        INSTANCE.tryToAddRecreator(r2, r3);
        return r02;
    }

    private final void tryToAddRecreator(SavedStateRegistry r2, Lifecycle r3) {
        Lifecycle.State r1 = r3.getCurrentState();
        if (r1 != Lifecycle.State.INITIALIZED) goto L5;
    L9:
        r2.runOnNextRecreation(OnRecreation.class);
        return;
    L5:
        if (r1.isAtLeast(Lifecycle.State.STARTED) == true) goto L9;
        r3.addObserver(new AnonymousClass1(r3, r2));
    }
}
