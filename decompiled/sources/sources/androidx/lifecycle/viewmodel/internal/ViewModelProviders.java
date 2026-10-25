package androidx.lifecycle.viewmodel.internal;

import androidx.lifecycle.ViewModel;
import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u000b\u001a\u00020\b\"\b\b\u0000\u0010\u0005*\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0000¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\u000f\u001a\u00028\u0000\"\b\b\u0000\u0010\f*\u00020\u0004H\u0000¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Landroidx/lifecycle/viewmodel/internal/ViewModelProviders;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/lifecycle/ViewModel;", "T", "Lkotlin/reflect/KClass;", "modelClass", _UrlKt.FRAGMENT_ENCODE_SET, "getDefaultKey$lifecycle_viewmodel", "(Lkotlin/reflect/KClass;)Ljava/lang/String;", "getDefaultKey", "VM", "unsupportedCreateViewModel$lifecycle_viewmodel", "()Landroidx/lifecycle/ViewModel;", "unsupportedCreateViewModel", "lifecycle-viewmodel"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nViewModelProviders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ViewModelProviders.kt\nandroidx/lifecycle/viewmodel/internal/ViewModelProviders\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,75:1\n37#2:76\n36#2,3:77\n1310#3,2:80\n*S KotlinDebug\n*F\n+ 1 ViewModelProviders.kt\nandroidx/lifecycle/viewmodel/internal/ViewModelProviders\n*L\n56#1:76\n56#1:77,3\n69#1:80,2\n*E\n"})
/* loaded from: classes.dex */
public final class ViewModelProviders {
    public static final ViewModelProviders INSTANCE = null;

    static {
        INSTANCE = new ViewModelProviders();
    }

    private ViewModelProviders() {
    }

    public final <T extends ViewModel> String getDefaultKey$lifecycle_viewmodel(KClass<T> r1) {
        String r0 = CanonicalName_jvmKt.getCanonicalName(r1);
        if (r0 != null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Local and anonymous classes can not be ViewModels");
        return null;
    L5:
        return "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(r0);
    }

    public final <VM extends ViewModel> VM unsupportedCreateViewModel$lifecycle_viewmodel() {
        throw new UnsupportedOperationException("`Factory.create(String, CreationExtras)` is not implemented. You may need to override the method and provide a custom implementation. Note that using `Factory.create(String)` is not supported and considered an error.");
    }
}
