package androidx.lifecycle;

import android.app.Application;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.internal.JvmViewModelProviders;
import androidx.lifecycle.viewmodel.internal.ViewModelProviderImpl;
import androidx.lifecycle.viewmodel.internal.ViewModelProviders;
import c.a$$ExternalSyntheticBUOutline0;
import c.f$$ExternalSyntheticBUOutline1;
import com.chaquo.python.internal.Common;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0016\u0018\u0000 \u001c2\u00020\u0001:\u0005\u001d\u001e\u001f \u001cB\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B#\b\u0017\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0004\u0010\fB\u0011\b\u0016\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0004\u0010\u000fJ(\u0010\u0014\u001a\u00028\u0000\"\b\b\u0000\u0010\u0011*\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012H\u0087\u0002¢\u0006\u0004\b\u0014\u0010\u0015J(\u0010\u0014\u001a\u00028\u0000\"\b\b\u0000\u0010\u0011*\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0096\u0002¢\u0006\u0004\b\u0014\u0010\u0017J0\u0010\u0014\u001a\u00028\u0000\"\b\b\u0000\u0010\u0011*\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00182\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012H\u0087\u0002¢\u0006\u0004\b\u0014\u0010\u001aR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001b¨\u0006!"}, d2 = {"Landroidx/lifecycle/ViewModelProvider;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/lifecycle/viewmodel/internal/ViewModelProviderImpl;", "impl", "<init>", "(Landroidx/lifecycle/viewmodel/internal/ViewModelProviderImpl;)V", "Landroidx/lifecycle/ViewModelStore;", "store", "Landroidx/lifecycle/ViewModelProvider$Factory;", PluginsConstants.Settings.FACTORY, "Landroidx/lifecycle/viewmodel/CreationExtras;", "defaultCreationExtras", "(Landroidx/lifecycle/ViewModelStore;Landroidx/lifecycle/ViewModelProvider$Factory;Landroidx/lifecycle/viewmodel/CreationExtras;)V", "Landroidx/lifecycle/ViewModelStoreOwner;", "owner", "(Landroidx/lifecycle/ViewModelStoreOwner;)V", "Landroidx/lifecycle/ViewModel;", "T", "Lkotlin/reflect/KClass;", "modelClass", "get", "(Lkotlin/reflect/KClass;)Landroidx/lifecycle/ViewModel;", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "(Ljava/lang/String;Lkotlin/reflect/KClass;)Landroidx/lifecycle/ViewModel;", "Landroidx/lifecycle/viewmodel/internal/ViewModelProviderImpl;", "Companion", "Factory", "OnRequeryFactory", "NewInstanceFactory", "AndroidViewModelFactory", "lifecycle-viewmodel"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nViewModelProvider.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ViewModelProvider.android.kt\nandroidx/lifecycle/ViewModelProvider\n+ 2 CreationExtras.kt\nandroidx/lifecycle/viewmodel/CreationExtras$Companion\n*L\n1#1,356:1\n75#2,7:357\n*S KotlinDebug\n*F\n+ 1 ViewModelProvider.android.kt\nandroidx/lifecycle/ViewModelProvider\n*L\n353#1:357,7\n*E\n"})
/* loaded from: classes.dex */
public class ViewModelProvider {
    public static final Companion Companion = null;

    @JvmField
    public static final CreationExtras.Key<String> VIEW_MODEL_KEY = null;
    private final ViewModelProviderImpl impl;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u001b\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\t\b\u0016¢\u0006\u0004\b\u0006\u0010\bB\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\tJ-\u0010\n\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0002\u0010\u0011J%\u0010\n\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000eH\u0016¢\u0006\u0002\u0010\u0012J-\u0010\n\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000e2\u0006\u0010\u0013\u001a\u00020\u0003H\u0002¢\u0006\u0002\u0010\u0014R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/lifecycle/ViewModelProvider$AndroidViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "application", "Landroid/app/Application;", "unused", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Landroid/app/Application;I)V", "()V", "(Landroid/app/Application;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "extras", "Landroidx/lifecycle/viewmodel/CreationExtras;", "(Ljava/lang/Class;Landroidx/lifecycle/viewmodel/CreationExtras;)Landroidx/lifecycle/ViewModel;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", Common.ASSET_APP, "(Ljava/lang/Class;Landroid/app/Application;)Landroidx/lifecycle/ViewModel;", "Companion", "lifecycle-viewmodel"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nViewModelProvider.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ViewModelProvider.android.kt\nandroidx/lifecycle/ViewModelProvider$AndroidViewModelFactory\n+ 2 CreationExtras.kt\nandroidx/lifecycle/viewmodel/CreationExtras$Companion\n*L\n1#1,356:1\n75#2,7:357\n*S KotlinDebug\n*F\n+ 1 ViewModelProvider.android.kt\nandroidx/lifecycle/ViewModelProvider$AndroidViewModelFactory\n*L\n332#1:357,7\n*E\n"})
    public static class AndroidViewModelFactory extends NewInstanceFactory {

        @JvmField
        public static final CreationExtras.Key<Application> APPLICATION_KEY = null;
        public static final Companion Companion = null;
        private static AndroidViewModelFactory _instance;
        private final Application application;

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/lifecycle/ViewModelProvider$AndroidViewModelFactory$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "_instance", "Landroidx/lifecycle/ViewModelProvider$AndroidViewModelFactory;", "getInstance", "application", "Landroid/app/Application;", "APPLICATION_KEY", "Landroidx/lifecycle/viewmodel/CreationExtras$Key;", "lifecycle-viewmodel"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            @JvmStatic
            public final AndroidViewModelFactory getInstance(Application r1) {
                if (AndroidViewModelFactory.access$get_instance$cp() != null) goto L6;
                AndroidViewModelFactory.access$set_instance$cp(new AndroidViewModelFactory(r1));
            L6:
                return AndroidViewModelFactory.access$get_instance$cp();
            }
        }

        static {
            Companion = new Companion(null);
            CreationExtras.Companion r0 = CreationExtras.Companion;
            APPLICATION_KEY = new ViewModelProvider$AndroidViewModelFactory$special$$inlined$Key$1();
        }

        public AndroidViewModelFactory() {
            this(null, 0);
        }

        public AndroidViewModelFactory(Application r2) {
            this(r2, 0);
        }

        private AndroidViewModelFactory(Application r1, int r2) {
            this.application = r1;
        }

        public static final /* synthetic */ AndroidViewModelFactory access$get_instance$cp() {
            return _instance;
        }

        public static final /* synthetic */ void access$set_instance$cp(AndroidViewModelFactory r0) {
            _instance = r0;
        }

        private final <T extends ViewModel> T create(Class<T> r3, Application r4) {
            if (AndroidViewModel.class.isAssignableFrom(r3) == false) goto L21;
            return r3.getConstructor(new Class[]{Application.class}).newInstance(new Object[]{r4});
        L10:
            e = move-exception;
            a$$ExternalSyntheticBUOutline0.m("Cannot create an instance of ", r3, e);
            return null;
        L8:
            e = move-exception;
            a$$ExternalSyntheticBUOutline0.m("Cannot create an instance of ", r3, e);
            return null;
        L12:
            e = move-exception;
            a$$ExternalSyntheticBUOutline0.m("Cannot create an instance of ", r3, e);
            return null;
        L6:
            e = move-exception;
            a$$ExternalSyntheticBUOutline0.m("Cannot create an instance of ", r3, e);
            return null;
        L21:
            return (T) super.create(r3);
        }

        @Override // androidx.lifecycle.ViewModelProvider.NewInstanceFactory, androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> r2) {
            Application r0 = this.application;
            if (r0 != null) goto L5;
            ByteString$$ExternalSyntheticBUOutline0.m("AndroidViewModelFactory constructed with empty constructor works only with create(modelClass: Class<T>, extras: CreationExtras).");
            return null;
        L5:
            return (T) create(r2, r0);
        }

        @Override // androidx.lifecycle.ViewModelProvider.NewInstanceFactory, androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> r2, CreationExtras r3) {
            if (this.application != null) goto L5;
            Application r32 = (Application) r3.get(APPLICATION_KEY);
            if (r32 == null) goto L11;
            return (T) create(r2, r32);
        L11:
            if (AndroidViewModel.class.isAssignableFrom(r2) == false) goto L13;
            f$$ExternalSyntheticBUOutline1.m("CreationExtras must have an application by `APPLICATION_KEY`");
            return null;
        L13:
            return (T) super.create(r2);
        L5:
            return (T) create(r2);
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u000b\u0010\fR\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/lifecycle/ViewModelProvider$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/lifecycle/ViewModelStoreOwner;", "owner", "Landroidx/lifecycle/ViewModelProvider$Factory;", PluginsConstants.Settings.FACTORY, "Landroidx/lifecycle/viewmodel/CreationExtras;", "extras", "Landroidx/lifecycle/ViewModelProvider;", "create", "(Landroidx/lifecycle/ViewModelStoreOwner;Landroidx/lifecycle/ViewModelProvider$Factory;Landroidx/lifecycle/viewmodel/CreationExtras;)Landroidx/lifecycle/ViewModelProvider;", "Landroidx/lifecycle/viewmodel/CreationExtras$Key;", _UrlKt.FRAGMENT_ENCODE_SET, "VIEW_MODEL_KEY", "Landroidx/lifecycle/viewmodel/CreationExtras$Key;", "lifecycle-viewmodel"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static /* synthetic */ ViewModelProvider create$default(Companion r0, ViewModelStoreOwner r1, Factory r2, CreationExtras r3, int r4, Object r5) {
            if ((r4 & 2) == 0) goto L6;
            r2 = ViewModelStoreOwnerDefaults.getViewModelProviderFactory(r1);
        L6:
            if ((r4 & 4) == 0) goto L9;
            r3 = ViewModelStoreOwnerDefaults.getViewModelCreationExtras(r1);
        L9:
            return r0.create(r1, r2, r3);
        }

        @JvmStatic
        public final ViewModelProvider create(ViewModelStoreOwner r1, Factory r2, CreationExtras r3) {
            return new ViewModelProvider(r1.getViewModelStore(), r2, r3);
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \r2\u00020\u0001:\u0001\rJ%\u0010\u0002\u001a\u0002H\u0003\"\b\b\u0000\u0010\u0003*\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0006H\u0016¢\u0006\u0002\u0010\u0007J-\u0010\u0002\u001a\u0002H\u0003\"\b\b\u0000\u0010\u0003*\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00062\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\nJ-\u0010\u0002\u001a\u0002H\u0003\"\b\b\u0000\u0010\u0003*\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00030\u000b2\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Landroidx/lifecycle/ViewModelProvider$Factory;", _UrlKt.FRAGMENT_ENCODE_SET, "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "extras", "Landroidx/lifecycle/viewmodel/CreationExtras;", "(Ljava/lang/Class;Landroidx/lifecycle/viewmodel/CreationExtras;)Landroidx/lifecycle/ViewModel;", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;Landroidx/lifecycle/viewmodel/CreationExtras;)Landroidx/lifecycle/ViewModel;", "Companion", "lifecycle-viewmodel"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface Factory {
        public static final Companion Companion = null;

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/lifecycle/ViewModelProvider$Factory$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "lifecycle-viewmodel"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = null;

            static {
                $$INSTANCE = new Companion();
            }

            private Companion() {
            }
        }

        static {
            Companion = Companion.$$INSTANCE;
        }

        default <T extends ViewModel> T create(Class<T> r1) {
            return (T) ViewModelProviders.INSTANCE.unsupportedCreateViewModel$lifecycle_viewmodel();
        }

        default <T extends ViewModel> T create(Class<T> r1, CreationExtras r2) {
            return (T) create(r1);
        }

        default <T extends ViewModel> T create(KClass<T> r1, CreationExtras r2) {
            return (T) create(JvmClassMappingKt.getJavaClass(r1), r2);
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0004\u001a\u0002H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00050\bH\u0016¢\u0006\u0002\u0010\tJ-\u0010\u0004\u001a\u0002H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00050\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\fJ-\u0010\u0004\u001a\u0002H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00050\r2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\u000e¨\u0006\u0010"}, d2 = {"Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "<init>", "()V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "extras", "Landroidx/lifecycle/viewmodel/CreationExtras;", "(Ljava/lang/Class;Landroidx/lifecycle/viewmodel/CreationExtras;)Landroidx/lifecycle/ViewModel;", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;Landroidx/lifecycle/viewmodel/CreationExtras;)Landroidx/lifecycle/ViewModel;", "Companion", "lifecycle-viewmodel"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static class NewInstanceFactory implements Factory {
        public static final Companion Companion = null;

        @JvmField
        public static final CreationExtras.Key<String> VIEW_MODEL_KEY = null;
        private static NewInstanceFactory _instance;

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\b\u001a\u00020\u00048GX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0007\u0010\u0003\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\nR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "getInstance", "()Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "getInstance$annotations", "instance", "_instance", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "Landroidx/lifecycle/viewmodel/CreationExtras$Key;", _UrlKt.FRAGMENT_ENCODE_SET, "VIEW_MODEL_KEY", "Landroidx/lifecycle/viewmodel/CreationExtras$Key;", "lifecycle-viewmodel"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            public final NewInstanceFactory getInstance() {
                if (NewInstanceFactory.access$get_instance$cp() != null) goto L6;
                NewInstanceFactory.access$set_instance$cp(new NewInstanceFactory());
            L6:
                return NewInstanceFactory.access$get_instance$cp();
            }
        }

        static {
            Companion = new Companion(null);
            VIEW_MODEL_KEY = ViewModelProvider.VIEW_MODEL_KEY;
        }

        public NewInstanceFactory() {
        }

        public static final /* synthetic */ NewInstanceFactory access$get_instance$cp() {
            return _instance;
        }

        public static final /* synthetic */ void access$set_instance$cp(NewInstanceFactory r0) {
            _instance = r0;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> r1) {
            return (T) JvmViewModelProviders.INSTANCE.createViewModel(r1);
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> r1, CreationExtras r2) {
            return (T) create(r1);
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(KClass<T> r1, CreationExtras r2) {
            return (T) create(JvmClassMappingKt.getJavaClass(r1), r2);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Landroidx/lifecycle/ViewModelProvider$OnRequeryFactory;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "onRequery", _UrlKt.FRAGMENT_ENCODE_SET, "viewModel", "Landroidx/lifecycle/ViewModel;", "lifecycle-viewmodel"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static class OnRequeryFactory {
        public OnRequeryFactory() {
        }

        public abstract void onRequery(ViewModel r1);
    }

    static {
        Companion = new Companion(null);
        CreationExtras.Companion r0 = CreationExtras.Companion;
        VIEW_MODEL_KEY = new ViewModelProvider$special$$inlined$Key$1();
    }

    @JvmOverloads
    public ViewModelProvider(ViewModelStore r7, Factory r8) {
        this(r7, r8, null, 4, null);
    }

    @JvmOverloads
    public ViewModelProvider(ViewModelStore r2, Factory r3, CreationExtras r4) {
        this(new ViewModelProviderImpl(r2, r3, r4));
    }

    public /* synthetic */ ViewModelProvider(ViewModelStore r1, Factory r2, CreationExtras r3, int r4, DefaultConstructorMarker r5) {
        if ((r4 & 4) == 0) goto L5;
        r3 = CreationExtras.Empty.INSTANCE;
    L5:
        this(r1, r2, r3);
    }

    public ViewModelProvider(ViewModelStoreOwner r3) {
        this(r3.getViewModelStore(), ViewModelStoreOwnerDefaults.getViewModelProviderFactory(r3), ViewModelStoreOwnerDefaults.getViewModelCreationExtras(r3));
    }

    private ViewModelProvider(ViewModelProviderImpl r1) {
        this.impl = r1;
    }

    public <T extends ViewModel> T get(Class<T> r1) {
        return (T) get(JvmClassMappingKt.getKotlinClass(r1));
    }

    public final <T extends ViewModel> T get(String r1, KClass<T> r2) {
        return (T) this.impl.getViewModel$lifecycle_viewmodel(r2, r1);
    }

    public final <T extends ViewModel> T get(KClass<T> r3) {
        return (T) ViewModelProviderImpl.getViewModel$lifecycle_viewmodel$default(this.impl, r3, null, 2, null);
    }
}
