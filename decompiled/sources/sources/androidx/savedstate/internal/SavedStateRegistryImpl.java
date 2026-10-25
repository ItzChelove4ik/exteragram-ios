package androidx.savedstate.internal;

import android.os.Bundle;
import androidx.core.os.BundleKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.savedstate.SavedStateReader;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
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
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.OkHttpClient$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\b\u0000\u0018\u0000 42\u00020\u0001:\u00014B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\r\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\f2\u0006\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0011\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u001a\u001a\u00020\u00052\u000e\u0010\u0017\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\fH\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u001d\u001a\u00020\u00052\n\u0010\u001b\u001a\u00060\u000bj\u0002`\fH\u0001¢\u0006\u0004\b\u001c\u0010\u0019R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001eR \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b \u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R \u0010&\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f0%8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'R\u0016\u0010)\u001a\u00020(8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u001e\u0010+\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010,R$\u0010.\u001a\u00020(2\u0006\u0010-\u001a\u00020(8G@BX\u0086\u000e¢\u0006\f\n\u0004\b.\u0010*\u001a\u0004\b.\u0010/R\"\u00100\u001a\u00020(8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b0\u0010*\u001a\u0004\b1\u0010/\"\u0004\b2\u00103¨\u00065"}, d2 = {"Landroidx/savedstate/internal/SavedStateRegistryImpl;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/savedstate/SavedStateRegistryOwner;", "owner", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "onAttach", "<init>", "(Landroidx/savedstate/SavedStateRegistryOwner;Lkotlin/jvm/functions/Function0;)V", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "consumeRestoredStateForKey", "(Ljava/lang/String;)Landroid/os/Bundle;", "Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "provider", "registerSavedStateProvider", "(Ljava/lang/String;Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;)V", "getSavedStateProvider", "(Ljava/lang/String;)Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "performAttach", "()V", "savedState", "performRestore$savedstate", "(Landroid/os/Bundle;)V", "performRestore", "outBundle", "performSave$savedstate", "performSave", "Landroidx/savedstate/SavedStateRegistryOwner;", "Lkotlin/jvm/functions/Function0;", "getOnAttach$savedstate", "()Lkotlin/jvm/functions/Function0;", "Landroidx/savedstate/internal/SynchronizedObject;", "lock", "Landroidx/savedstate/internal/SynchronizedObject;", _UrlKt.FRAGMENT_ENCODE_SET, "keyToProviders", "Ljava/util/Map;", _UrlKt.FRAGMENT_ENCODE_SET, "attached", "Z", "restoredState", "Landroid/os/Bundle;", "value", "isRestored", "()Z", "isAllowingSavingState", "isAllowingSavingState$savedstate", "setAllowingSavingState$savedstate", "(Z)V", "Companion", "savedstate"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSavedStateRegistryImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SavedStateRegistryImpl.kt\nandroidx/savedstate/internal/SavedStateRegistryImpl\n+ 2 SavedState.kt\nandroidx/savedstate/SavedStateKt__SavedStateKt\n+ 3 SynchronizedObject.kt\nandroidx/savedstate/internal/SynchronizedObjectKt\n+ 4 SynchronizedObject.jvm.kt\nandroidx/savedstate/internal/SynchronizedObject_jvmKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 6 SavedState.android.kt\nandroidx/savedstate/SavedStateKt__SavedState_androidKt\n+ 7 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 8 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,154:1\n90#2:155\n106#2:156\n90#2:157\n90#2:165\n106#2:187\n90#2:190\n106#2:191\n41#3:158\n41#3:160\n41#3:162\n41#3:188\n23#4:159\n23#4:161\n23#4:163\n23#4:189\n1#5:164\n1#5:186\n27#6:166\n47#6:167\n32#6,4:168\n31#6,8:178\n126#7:172\n153#7,3:173\n37#8,2:176\n*S KotlinDebug\n*F\n+ 1 SavedStateRegistryImpl.kt\nandroidx/savedstate/internal/SavedStateRegistryImpl\n*L\n55#1:155\n56#1:156\n57#1:157\n121#1:165\n135#1:187\n144#1:190\n145#1:191\n66#1:158\n75#1:160\n84#1:162\n137#1:188\n66#1:159\n75#1:161\n84#1:163\n137#1:189\n135#1:186\n135#1:166\n135#1:167\n135#1:168,4\n135#1:178,8\n135#1:172\n135#1:173,3\n135#1:176,2\n*E\n"})
/* loaded from: classes.dex */
public final class SavedStateRegistryImpl {
    private static final Companion Companion = null;
    private boolean attached;
    private boolean isAllowingSavingState;
    private boolean isRestored;
    private final Map<String, SavedStateRegistry.SavedStateProvider> keyToProviders;
    private final SynchronizedObject lock;
    private final Function0<Unit> onAttach;
    private final SavedStateRegistryOwner owner;
    private Bundle restoredState;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/savedstate/internal/SavedStateRegistryImpl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "SAVED_COMPONENTS_KEY", _UrlKt.FRAGMENT_ENCODE_SET, "savedstate"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    /* renamed from: $r8$lambda$6xnOyEL_uTccN7fW0CY8JkIx-HI, reason: not valid java name */
    public static void m961$r8$lambda$6xnOyEL_uTccN7fW0CY8JkIxHI(SavedStateRegistryImpl r0, LifecycleOwner r1, Lifecycle.Event r2) {
        if (r2 != Lifecycle.Event.ON_START) goto L7;
        r0.isAllowingSavingState = true;
        return;
    L7:
        if (r2 != Lifecycle.Event.ON_STOP) goto L10;
        r0.isAllowingSavingState = false;
        return;
    }

    static {
        Companion = new Companion(null);
    }

    public SavedStateRegistryImpl(SavedStateRegistryOwner r1, Function0<Unit> r2) {
        this.owner = r1;
        this.onAttach = r2;
        this.lock = new SynchronizedObject();
        this.keyToProviders = new LinkedHashMap();
        this.isAllowingSavingState = true;
    }

    public static final /* synthetic */ Map access$getKeyToProviders$p(SavedStateRegistryImpl r0) {
        return r0.keyToProviders;
    }

    public final Bundle consumeRestoredStateForKey(String r5) {
        if (this.isRestored == false) goto L15;
        Bundle r0 = this.restoredState;
        if (r0 != null) goto L7;
        return null;
    L7:
        Bundle r2 = SavedStateReader.m949constructorimpl(r0);
        if (SavedStateReader.m950containsimpl(r2, r5) == false) goto L10;
        Bundle r22 = SavedStateReader.m951getSavedStateimpl(r2, r5);
    L11:
        SavedStateWriter.m960removeimpl(SavedStateWriter.m956constructorimpl(r0), r5);
        if (SavedStateReader.m954isEmptyimpl(SavedStateReader.m949constructorimpl(r0)) == false) goto L14;
        this.restoredState = null;
    L14:
        return r22;
    L10:
        r22 = null;
        goto L11
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("You can 'consumeRestoredStateForKey' only after the corresponding component has moved to the 'CREATED' state");
        return null;
    }

    public final SavedStateRegistry.SavedStateProvider getSavedStateProvider(String r5) {
        SynchronizedObject r0 = this.lock;
        monitor-enter(r0);
        Iterator r4 = access$getKeyToProviders$p(this).entrySet().iterator();     // Catch: Throwable -> L12
    L5:
        SavedStateRegistry.SavedStateProvider r2 = null;
        if (r4.hasNext() == false) goto L14;
        Map.Entry r1 = (Map.Entry) r4.next();     // Catch: Throwable -> L12
        String r3 = (String) r1.getKey();     // Catch: Throwable -> L12
        SavedStateRegistry.SavedStateProvider r12 = (SavedStateRegistry.SavedStateProvider) r1.getValue();     // Catch: Throwable -> L12
        if (Intrinsics.areEqual(r3, r5) == false) goto L10;
        r2 = r12;
    L10:
        if (r2 == null) goto L5;
    L14:
        monitor-exit(r0);
        return r2;
    L12:
        th = move-exception;
        throw th;
    }

    public final boolean isAllowingSavingState$savedstate() {
        return this.isAllowingSavingState;
    }

    public final void performAttach() {
        if (this.owner.getLifecycle().getCurrentState() == Lifecycle.State.INITIALIZED) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Restarter must be created only during owner's initialization stage");
        return;
    L5:
        if (this.attached == true) goto L8;
        this.onAttach.invoke();
        this.owner.getLifecycle().addObserver(new SavedStateRegistryImpl$$ExternalSyntheticLambda0(this));
        this.attached = true;
        return;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("SavedStateRegistry was already attached.");
    }

    public final void performRestore$savedstate(Bundle r4) {
        if (this.attached == true) goto L6;
        performAttach();
    L6:
        if (this.owner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED) == false) goto L8;
        OkHttpClient$$ExternalSyntheticBUOutline0.m("performRestore cannot be called when owner is ", this.owner.getLifecycle().getCurrentState());
        return;
    L8:
        if (this.isRestored == true) goto L16;
        Bundle r0 = null;
        if (r4 == null) goto L14;
        Bundle r42 = SavedStateReader.m949constructorimpl(r4);
        if (SavedStateReader.m950containsimpl(r42, "androidx.lifecycle.BundlableSavedStateRegistry.key") == false) goto L14;
        r0 = SavedStateReader.m951getSavedStateimpl(r42, "androidx.lifecycle.BundlableSavedStateRegistry.key");
    L14:
        this.restoredState = r0;
        this.isRestored = true;
        return;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("SavedStateRegistry was already restored.");
    }

    public final void performSave$savedstate(Bundle r6) {
        Map r0 = MapsKt.emptyMap();
        if (r0.isEmpty() == false) goto L5;
        Pair[] r02 = new Pair[0];
    L10:
        Bundle r03 = BundleKt.bundleOf((Pair[]) Arrays.copyOf(r02, r02.length));
        Bundle r1 = SavedStateWriter.m956constructorimpl(r03);
        Bundle r2 = this.restoredState;
        if (r2 == null) goto L13;
        SavedStateWriter.m957putAllimpl(r1, r2);
    L13:
        SynchronizedObject r22 = this.lock;
        monitor-enter(r22);
        Iterator r5 = access$getKeyToProviders$p(this).entrySet().iterator();     // Catch: Throwable -> L19
    L17:
        if (r5.hasNext() == false) goto L21;
        Map.Entry r3 = (Map.Entry) r5.next();     // Catch: Throwable -> L19
        SavedStateWriter.m958putSavedStateimpl(r1, (String) r3.getKey(), ((SavedStateRegistry.SavedStateProvider) r3.getValue()).saveState());     // Catch: Throwable -> L19
        goto L17
    L21:
        Unit r52 = Unit.INSTANCE;     // Catch: Throwable -> L19
        monitor-exit(r22);
        if (SavedStateReader.m954isEmptyimpl(SavedStateReader.m949constructorimpl(r03)) == true) goto L33;
        SavedStateWriter.m958putSavedStateimpl(SavedStateWriter.m956constructorimpl(r6), "androidx.lifecycle.BundlableSavedStateRegistry.key", r03);
        return;
    L33:
        return;
    L19:
        th = move-exception;
        throw th;
    L5:
        ArrayList r12 = new ArrayList(r0.size());
        Iterator r04 = r0.entrySet().iterator();
    L7:
        if (r04.hasNext() == false) goto L9;
        Map.Entry r32 = (Map.Entry) r04.next();
        r12.add(TuplesKt.to((String) r32.getKey(), r32.getValue()));
        goto L7
    L9:
        r02 = (Pair[]) r12.toArray(new Pair[0]);
        goto L10
    }

    public final void registerSavedStateProvider(String r3, SavedStateRegistry.SavedStateProvider r4) {
        SynchronizedObject r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (access$getKeyToProviders$p(this).containsKey(r3) == true) goto L12;
        access$getKeyToProviders$p(this).put(r3, r4);     // Catch: Throwable -> L9
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r0);
        return;
    L12:
        throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");     // Catch: Throwable -> L9
    }
}
