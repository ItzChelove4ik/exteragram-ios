package androidx.lifecycle.viewmodel.internal;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ1\u0010\f\u001a\u0002H\r\"\b\b\u0000\u0010\r*\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/lifecycle/viewmodel/internal/ViewModelProviderImpl;", _UrlKt.FRAGMENT_ENCODE_SET, "store", "Landroidx/lifecycle/ViewModelStore;", PluginsConstants.Settings.FACTORY, "Landroidx/lifecycle/ViewModelProvider$Factory;", "defaultExtras", "Landroidx/lifecycle/viewmodel/CreationExtras;", "<init>", "(Landroidx/lifecycle/ViewModelStore;Landroidx/lifecycle/ViewModelProvider$Factory;Landroidx/lifecycle/viewmodel/CreationExtras;)V", "lock", "Landroidx/lifecycle/viewmodel/internal/SynchronizedObject;", "getViewModel", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Lkotlin/reflect/KClass;", PluginsConstants.Settings.KEY, _UrlKt.FRAGMENT_ENCODE_SET, "getViewModel$lifecycle_viewmodel", "(Lkotlin/reflect/KClass;Ljava/lang/String;)Landroidx/lifecycle/ViewModel;", "lifecycle-viewmodel"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nViewModelProviderImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ViewModelProviderImpl.kt\nandroidx/lifecycle/viewmodel/internal/ViewModelProviderImpl\n+ 2 SynchronizedObject.kt\nandroidx/lifecycle/viewmodel/internal/SynchronizedObjectKt\n+ 3 SynchronizedObject.jvmAndAndroid.kt\nandroidx/lifecycle/viewmodel/internal/SynchronizedObject_jvmKt\n*L\n1#1,84:1\n32#2:85\n26#3:86\n*S KotlinDebug\n*F\n+ 1 ViewModelProviderImpl.kt\nandroidx/lifecycle/viewmodel/internal/ViewModelProviderImpl\n*L\n47#1:85\n47#1:86\n*E\n"})
/* loaded from: classes.dex */
public final class ViewModelProviderImpl {
    private final CreationExtras defaultExtras;
    private final ViewModelProvider.Factory factory;
    private final SynchronizedObject lock;
    private final ViewModelStore store;

    public ViewModelProviderImpl(ViewModelStore r1, ViewModelProvider.Factory r2, CreationExtras r3) {
        this.store = r1;
        this.factory = r2;
        this.defaultExtras = r3;
        this.lock = new SynchronizedObject();
    }

    public static final /* synthetic */ CreationExtras access$getDefaultExtras$p(ViewModelProviderImpl r0) {
        return r0.defaultExtras;
    }

    public static final /* synthetic */ ViewModelProvider.Factory access$getFactory$p(ViewModelProviderImpl r0) {
        return r0.factory;
    }

    public static final /* synthetic */ ViewModelStore access$getStore$p(ViewModelProviderImpl r0) {
        return r0.store;
    }

    public static /* synthetic */ ViewModel getViewModel$lifecycle_viewmodel$default(ViewModelProviderImpl r0, KClass r1, String r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = ViewModelProviders.INSTANCE.getDefaultKey$lifecycle_viewmodel(r1);
    L6:
        return r0.getViewModel$lifecycle_viewmodel(r1, r2);
    }

    public final <T extends ViewModel> T getViewModel$lifecycle_viewmodel(KClass<T> r4, String r5) {
        SynchronizedObject r0 = this.lock;
        monitor-enter(r0);
        T r1 = (T) access$getStore$p(this).get(r5);
        if (r4.isInstance(r1) == true) goto L7;
        MutableCreationExtras r12 = new MutableCreationExtras(access$getDefaultExtras$p(this));     // Catch: Throwable -> L9
        r12.set(ViewModelProvider.VIEW_MODEL_KEY, r5);     // Catch: Throwable -> L9
        r1 = (T) ViewModelProviderImpl_androidKt.createViewModel(access$getFactory$p(this), r4, r12);
        access$getStore$p(this).put(r5, r1);     // Catch: Throwable -> L9
    L12:
        monitor-exit(r0);
        return r1;
    L7:
        if ((access$getFactory$p(this) instanceof ViewModelProvider.OnRequeryFactory) == false) goto L12;
        ((ViewModelProvider.OnRequeryFactory) access$getFactory$p(this)).onRequery(r1);     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }
}
