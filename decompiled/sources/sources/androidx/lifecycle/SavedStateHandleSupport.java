package androidx.lifecycle;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a!\u0010\u0004\u001a\u00020\u0003\"\f\b\u0000\u0010\u0002*\u00020\u0000*\u00020\u0001*\u00028\u0000H\u0007¢\u0006\u0004\b\u0004\u0010\u0005\u001a7\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\b2\u000e\u0010\f\u001a\n\u0018\u00010\nj\u0004\u0018\u0001`\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0013\u0010\u000e\u001a\u00020\r*\u00020\u0010H\u0007¢\u0006\u0004\b\u000e\u0010\u0011\"\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00000\u00128\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014\"\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u00128\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0014\"\u001e\u0010\u0016\u001a\f\u0012\b\u0012\u00060\nj\u0002`\u000b0\u00128\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0014\"\u0018\u0010\u001a\u001a\u00020\u0017*\u00020\u00018@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019\"\u0018\u0010\u001e\u001a\u00020\u001b*\u00020\u00008@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001f"}, d2 = {"Landroidx/savedstate/SavedStateRegistryOwner;", "Landroidx/lifecycle/ViewModelStoreOwner;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "enableSavedStateHandles", "(Landroidx/savedstate/SavedStateRegistryOwner;)V", "savedStateRegistryOwner", "viewModelStoreOwner", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "defaultArgs", "Landroidx/lifecycle/SavedStateHandle;", "createSavedStateHandle", "(Landroidx/savedstate/SavedStateRegistryOwner;Landroidx/lifecycle/ViewModelStoreOwner;Ljava/lang/String;Landroid/os/Bundle;)Landroidx/lifecycle/SavedStateHandle;", "Landroidx/lifecycle/viewmodel/CreationExtras;", "(Landroidx/lifecycle/viewmodel/CreationExtras;)Landroidx/lifecycle/SavedStateHandle;", "Landroidx/lifecycle/viewmodel/CreationExtras$Key;", "SAVED_STATE_REGISTRY_OWNER_KEY", "Landroidx/lifecycle/viewmodel/CreationExtras$Key;", "VIEW_MODEL_STORE_OWNER_KEY", "DEFAULT_ARGS_KEY", "Landroidx/lifecycle/SavedStateHandlesVM;", "getSavedStateHandlesVM", "(Landroidx/lifecycle/ViewModelStoreOwner;)Landroidx/lifecycle/SavedStateHandlesVM;", "savedStateHandlesVM", "Landroidx/lifecycle/SavedStateHandlesProvider;", "getSavedStateHandlesProvider", "(Landroidx/savedstate/SavedStateRegistryOwner;)Landroidx/lifecycle/SavedStateHandlesProvider;", "savedStateHandlesProvider", "lifecycle-viewmodel-savedstate"}, k = 2, mv = {2, 1, 0}, xi = 48)
@JvmName(name = "SavedStateHandleSupport")
@SourceDebugExtension({"SMAP\nSavedStateHandleSupport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SavedStateHandleSupport.kt\nandroidx/lifecycle/SavedStateHandleSupport\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 CreationExtras.kt\nandroidx/lifecycle/viewmodel/CreationExtras$Companion\n*L\n1#1,246:1\n1#2:247\n75#3,7:248\n75#3,7:255\n75#3,7:262\n*S KotlinDebug\n*F\n+ 1 SavedStateHandleSupport.kt\nandroidx/lifecycle/SavedStateHandleSupport\n*L\n236#1:248,7\n241#1:255,7\n245#1:262,7\n*E\n"})
/* loaded from: classes.dex */
public abstract class SavedStateHandleSupport {

    @JvmField
    public static final CreationExtras.Key<Bundle> DEFAULT_ARGS_KEY = null;

    @JvmField
    public static final CreationExtras.Key<SavedStateRegistryOwner> SAVED_STATE_REGISTRY_OWNER_KEY = null;

    @JvmField
    public static final CreationExtras.Key<ViewModelStoreOwner> VIEW_MODEL_STORE_OWNER_KEY = null;

    static {
        CreationExtras.Companion r0 = CreationExtras.Companion;
        SAVED_STATE_REGISTRY_OWNER_KEY = new SavedStateHandleSupport$special$$inlined$Key$1();
        VIEW_MODEL_STORE_OWNER_KEY = new SavedStateHandleSupport$special$$inlined$Key$2();
        DEFAULT_ARGS_KEY = new SavedStateHandleSupport$special$$inlined$Key$3();
    }

    public static final SavedStateHandle createSavedStateHandle(CreationExtras r5) {
        SavedStateRegistryOwner r0 = (SavedStateRegistryOwner) r5.get(SAVED_STATE_REGISTRY_OWNER_KEY);
        if (r0 == null) goto L14;
        ViewModelStoreOwner r2 = (ViewModelStoreOwner) r5.get(VIEW_MODEL_STORE_OWNER_KEY);
        if (r2 == null) goto L12;
        Bundle r3 = (Bundle) r5.get(DEFAULT_ARGS_KEY);
        String r52 = (String) r5.get(ViewModelProvider.VIEW_MODEL_KEY);
        if (r52 != null) goto L9;
        f$$ExternalSyntheticBUOutline1.m("CreationExtras must have a value by `VIEW_MODEL_KEY`");
        return null;
    L9:
        return createSavedStateHandle(r0, r2, r52, r3);
    L12:
        f$$ExternalSyntheticBUOutline1.m("CreationExtras must have a value by `VIEW_MODEL_STORE_OWNER_KEY`");
        return null;
    L14:
        f$$ExternalSyntheticBUOutline1.m("CreationExtras must have a value by `SAVED_STATE_REGISTRY_OWNER_KEY`");
        return null;
    }

    private static final SavedStateHandle createSavedStateHandle(SavedStateRegistryOwner r1, ViewModelStoreOwner r2, String r3, Bundle r4) {
        SavedStateHandlesProvider r12 = getSavedStateHandlesProvider(r1);
        SavedStateHandlesVM r22 = getSavedStateHandlesVM(r2);
        SavedStateHandle r0 = r22.getHandles().get(r3);
        if (r0 != null) goto L6;
        SavedStateHandle r13 = SavedStateHandle.Companion.createHandle(r12.consumeRestoredStateForKey(r3), r4);
        r22.getHandles().put(r3, r13);
        return r13;
    L6:
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T extends SavedStateRegistryOwner & ViewModelStoreOwner> void enableSavedStateHandles(T r4) {
        Lifecycle.State r0 = r4.getLifecycle().getCurrentState();
        if (r0 == Lifecycle.State.INITIALIZED) goto L10;
        if (r0 == Lifecycle.State.CREATED) goto L10;
        SavedStateHandleSupport$$ExternalSyntheticBUOutline0.m("Failed to enable `SavedStateHandle` for `", r4, "`. The `Lifecycle.State` must be `INITIALIZED` or `CREATED`, but was `", r0, "`. You must call `enableSavedStateHandles()` before the `Lifecycle.State` moves to `STARTED`.");
        return;
    L10:
        if (r4.getSavedStateRegistry().getSavedStateProvider("androidx.lifecycle.internal.SavedStateHandlesProvider") != null) goto L13;
        SavedStateHandlesProvider r02 = new SavedStateHandlesProvider(r4.getSavedStateRegistry(), r4);
        r4.getSavedStateRegistry().registerSavedStateProvider("androidx.lifecycle.internal.SavedStateHandlesProvider", r02);
        r4.getLifecycle().addObserver(new SavedStateHandleAttacher(r02));
        return;
    }

    public static final SavedStateHandlesProvider getSavedStateHandlesProvider(SavedStateRegistryOwner r2) {
        SavedStateRegistry.SavedStateProvider r22 = r2.getSavedStateRegistry().getSavedStateProvider("androidx.lifecycle.internal.SavedStateHandlesProvider");
        if ((r22 instanceof SavedStateHandlesProvider) == false) goto L5;
        SavedStateHandlesProvider r23 = (SavedStateHandlesProvider) r22;
    L6:
        if (r23 == null) goto L8;
        return r23;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("enableSavedStateHandles() wasn't called prior to createSavedStateHandle() call");
        return null;
    L5:
        r23 = null;
        goto L6
    }

    public static final SavedStateHandlesVM getSavedStateHandlesVM(ViewModelStoreOwner r6) {
        return (SavedStateHandlesVM) ViewModelProvider.Companion.create$default(ViewModelProvider.Companion, r6, new SavedStateHandleSupport$savedStateHandlesVM$1(), null, 4, null).get("androidx.lifecycle.internal.SavedStateHandlesVM", Reflection.getOrCreateKotlinClass(SavedStateHandlesVM.class));
    }
}
