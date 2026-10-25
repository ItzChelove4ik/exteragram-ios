package androidx.lifecycle;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B%\b\u0017\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ/\u0010\u0011\u001a\u00028\u0000\"\b\b\u0000\u0010\f*\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J/\u0010\u0011\u001a\u00028\u0000\"\b\b\u0000\u0010\f*\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00132\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0014J-\u0010\u0011\u001a\u00028\u0000\"\b\b\u0000\u0010\f*\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00152\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013¢\u0006\u0004\b\u0011\u0010\u0017J'\u0010\u0011\u001a\u00028\u0000\"\b\b\u0000\u0010\f*\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016¢\u0006\u0004\b\u0011\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u000bH\u0017¢\u0006\u0004\b\u001b\u0010\u001cR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0004\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\b\u0010 R\u0018\u0010\"\u001a\u0004\u0018\u00010!8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#R\u0018\u0010%\u001a\u0004\u0018\u00010$8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010&¨\u0006'"}, d2 = {"Landroidx/lifecycle/SavedStateViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$OnRequeryFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "Landroid/app/Application;", "application", "Landroidx/savedstate/SavedStateRegistryOwner;", "owner", "Landroid/os/Bundle;", "defaultArgs", "<init>", "(Landroid/app/Application;Landroidx/savedstate/SavedStateRegistryOwner;Landroid/os/Bundle;)V", "Landroidx/lifecycle/ViewModel;", "T", "Lkotlin/reflect/KClass;", "modelClass", "Landroidx/lifecycle/viewmodel/CreationExtras;", "extras", "create", "(Lkotlin/reflect/KClass;Landroidx/lifecycle/viewmodel/CreationExtras;)Landroidx/lifecycle/ViewModel;", "Ljava/lang/Class;", "(Ljava/lang/Class;Landroidx/lifecycle/viewmodel/CreationExtras;)Landroidx/lifecycle/ViewModel;", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "(Ljava/lang/String;Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "viewModel", _UrlKt.FRAGMENT_ENCODE_SET, "onRequery", "(Landroidx/lifecycle/ViewModel;)V", "Landroid/app/Application;", PluginsConstants.Settings.FACTORY, "Landroidx/lifecycle/ViewModelProvider$Factory;", "Landroid/os/Bundle;", "Landroidx/lifecycle/Lifecycle;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "Landroidx/savedstate/SavedStateRegistry;", "savedStateRegistry", "Landroidx/savedstate/SavedStateRegistry;", "lifecycle-viewmodel-savedstate"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SavedStateViewModelFactory extends ViewModelProvider.OnRequeryFactory implements ViewModelProvider.Factory {
    private Application application;
    private Bundle defaultArgs;
    private final ViewModelProvider.Factory factory;
    private Lifecycle lifecycle;
    private SavedStateRegistry savedStateRegistry;

    @SuppressLint({"LambdaLast"})
    public SavedStateViewModelFactory(Application r2, SavedStateRegistryOwner r3, Bundle r4) {
        this.savedStateRegistry = r3.getSavedStateRegistry();
        this.lifecycle = r3.getLifecycle();
        this.defaultArgs = r4;
        this.application = r2;
        if (r2 == null) goto L5;
        ViewModelProvider.AndroidViewModelFactory r22 = ViewModelProvider.AndroidViewModelFactory.Companion.getInstance(r2);
    L6:
        this.factory = r22;
        return;
    L5:
        r22 = new ViewModelProvider.AndroidViewModelFactory();
        goto L6
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> r2) {
        String r0 = r2.getCanonicalName();
        if (r0 != null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Local and anonymous classes can not be ViewModels");
        return null;
    L5:
        return (T) create(r0, r2);
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> r4, CreationExtras r5) {
        String r0 = (String) r5.get(ViewModelProvider.VIEW_MODEL_KEY);
        if (r0 != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
        return null;
    L5:
        if (r5.get(SavedStateHandleSupport.SAVED_STATE_REGISTRY_OWNER_KEY) == null) goto L23;
        if (r5.get(SavedStateHandleSupport.VIEW_MODEL_STORE_OWNER_KEY) == null) goto L23;
        Application r02 = (Application) r5.get(ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY);
        boolean r1 = AndroidViewModel.class.isAssignableFrom(r4);
        if (r1 == false) goto L12;
        if (r02 == null) goto L12;
        Constructor r2 = SavedStateViewModelFactory_androidKt.findMatchingConstructor(r4, SavedStateViewModelFactory_androidKt.access$getANDROID_VIEWMODEL_SIGNATURE$p());
    L13:
        if (r2 == null) goto L15;
        if (r1 == false) goto L21;
        if (r02 == null) goto L21;
        return (T) SavedStateViewModelFactory_androidKt.newInstance(r4, r2, new Object[]{r02, SavedStateHandleSupport.createSavedStateHandle(r5)});
    L21:
        return (T) SavedStateViewModelFactory_androidKt.newInstance(r4, r2, new Object[]{SavedStateHandleSupport.createSavedStateHandle(r5)});
    L15:
        return (T) this.factory.create(r4, r5);
    L12:
        r2 = SavedStateViewModelFactory_androidKt.findMatchingConstructor(r4, SavedStateViewModelFactory_androidKt.access$getVIEWMODEL_SIGNATURE$p());
    L23:
        if (this.lifecycle != null) goto L25;
        Segment$$ExternalSyntheticBUOutline1.m("SAVED_STATE_REGISTRY_OWNER_KEY andVIEW_MODEL_STORE_OWNER_KEY must be provided in the creation extras tosuccessfully create a ViewModel.");
        return null;
    L25:
        return (T) create(r0, r4);
    }

    public final <T extends ViewModel> T create(String r6, Class<T> r7) {
        Lifecycle r0 = this.lifecycle;
        if (r0 == null) goto L25;
        boolean r1 = AndroidViewModel.class.isAssignableFrom(r7);
        if (r1 == true) goto L7;
    L9:
        Constructor r2 = SavedStateViewModelFactory_androidKt.findMatchingConstructor(r7, SavedStateViewModelFactory_androidKt.access$getVIEWMODEL_SIGNATURE$p());
    L10:
        if (r2 == null) goto L12;
        SavedStateHandleController r62 = LegacySavedStateHandleController.create(this.savedStateRegistry, r0, r6, this.defaultArgs);
        if (r1 == false) goto L22;
        Application r5 = this.application;
        if (r5 == null) goto L22;
        T r52 = (T) SavedStateViewModelFactory_androidKt.newInstance(r7, r2, new Object[]{r5, r62.getHandle()});
    L23:
        r52.addCloseable("androidx.lifecycle.savedstate.vm.tag", r62);
        return r52;
    L22:
        r52 = (T) SavedStateViewModelFactory_androidKt.newInstance(r7, r2, new Object[]{r62.getHandle()});
        goto L23
    L12:
        if (this.application == null) goto L16;
        return (T) this.factory.create(r7);
    L16:
        return (T) ViewModelProvider.NewInstanceFactory.Companion.getInstance().create(r7);
    L7:
        if (this.application == null) goto L9;
        r2 = SavedStateViewModelFactory_androidKt.findMatchingConstructor(r7, SavedStateViewModelFactory_androidKt.access$getANDROID_VIEWMODEL_SIGNATURE$p());
        goto L10
    L25:
        ByteString$$ExternalSyntheticBUOutline0.m("SavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
        return null;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(KClass<T> r1, CreationExtras r2) {
        return (T) create(JvmClassMappingKt.getJavaClass(r1), r2);
    }

    @Override // androidx.lifecycle.ViewModelProvider.OnRequeryFactory
    public void onRequery(ViewModel r2) {
        Lifecycle r0 = this.lifecycle;
        if (r0 == null) goto L6;
        LegacySavedStateHandleController.attachHandleIfNeeded(r2, this.savedStateRegistry, r0);
        return;
    }
}
