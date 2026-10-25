package com.google.firebase.datastorage;

import android.content.Context;
import android.os.Process;
import android.util.Log;
import androidx.datastore.core.CorruptionException;
import androidx.datastore.core.DataStore;
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler;
import androidx.datastore.preferences.PreferenceDataStoreDelegateKt;
import androidx.datastore.preferences.SharedPreferencesMigrationKt;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesFactory;
import androidx.datastore.preferences.core.PreferencesKt;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference2Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J)\u0010\f\u001a\u00028\u0000\"\u0004\b\u0000\u0010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t2\u0006\u0010\u000b\u001a\u00028\u0000¢\u0006\u0004\b\f\u0010\rJ)\u0010\u0010\u001a\u00020\u000f\"\u0004\b\u0000\u0010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t2\u0006\u0010\u000e\u001a\u00028\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0013\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0004\b\u0013\u0010\u0014J!\u0010\u0019\u001a\u00020\u000f2\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R%\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000f0%*\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u001a\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000f0%8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+¨\u0006,"}, d2 = {"Lcom/google/firebase/datastorage/JavaDataStorage;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/content/Context;", "context", _UrlKt.FRAGMENT_ENCODE_SET, "name", "<init>", "(Landroid/content/Context;Ljava/lang/String;)V", "T", "Landroidx/datastore/preferences/core/Preferences$Key;", PluginsConstants.Settings.KEY, "defaultValue", "getSync", "(Landroidx/datastore/preferences/core/Preferences$Key;Ljava/lang/Object;)Ljava/lang/Object;", "value", "Landroidx/datastore/preferences/core/Preferences;", "putSync", "(Landroidx/datastore/preferences/core/Preferences$Key;Ljava/lang/Object;)Landroidx/datastore/preferences/core/Preferences;", _UrlKt.FRAGMENT_ENCODE_SET, "getAllSync", "()Ljava/util/Map;", "Lkotlin/Function1;", "Landroidx/datastore/preferences/core/MutablePreferences;", _UrlKt.FRAGMENT_ENCODE_SET, "transform", "editSync", "(Lkotlin/jvm/functions/Function1;)Landroidx/datastore/preferences/core/Preferences;", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "Ljava/lang/ThreadLocal;", _UrlKt.FRAGMENT_ENCODE_SET, "editLock", "Ljava/lang/ThreadLocal;", "Landroidx/datastore/core/DataStore;", "dataStore$delegate", "Lkotlin/properties/ReadOnlyProperty;", "getDataStore", "(Landroid/content/Context;)Landroidx/datastore/core/DataStore;", "dataStore", "Landroidx/datastore/core/DataStore;", "com.google.firebase-firebase-common"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class JavaDataStorage {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = null;
    private final Context context;
    private final DataStore<Preferences> dataStore;
    private final ReadOnlyProperty dataStore$delegate;
    private final ThreadLocal<Boolean> editLock;
    private final String name;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/datastore/preferences/core/Preferences;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.google.firebase.datastorage.JavaDataStorage$editSync$1", f = "JavaDataStorage.kt", i = {}, l = {Opcodes.REM_INT_LIT8}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.google.firebase.datastorage.JavaDataStorage$editSync$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Preferences>, Object> {
        final /* synthetic */ Function1<MutablePreferences, Unit> $transform;
        int label;
        final /* synthetic */ JavaDataStorage this$0;

        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "it", "Landroidx/datastore/preferences/core/MutablePreferences;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "com.google.firebase.datastorage.JavaDataStorage$editSync$1$1", f = "JavaDataStorage.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.google.firebase.datastorage.JavaDataStorage$editSync$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C00241 extends SuspendLambda implements Function2<MutablePreferences, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<MutablePreferences, Unit> $transform;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: Multi-variable type inference failed */
            public C00241(Function1<? super MutablePreferences, Unit> r1, Continuation<? super C00241> r2) {
                this.$transform = r1;
                super(2, r2);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
                C00241 r0 = new C00241(this.$transform, r3);
                r0.L$0 = r2;
                return r0;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(MutablePreferences r1, Continuation<? super Unit> r2) {
                return ((C00241) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(MutablePreferences r1, Continuation<? super Unit> r2) {
                return invoke2(r1, r2);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r2) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) goto L6;
                ResultKt.throwOnFailure(r2);
                MutablePreferences r22 = (MutablePreferences) this.L$0;
                this.$transform.invoke(r22);
                return Unit.INSTANCE;
            L6:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(JavaDataStorage r1, Function1<? super MutablePreferences, Unit> r2, Continuation<? super AnonymousClass1> r3) {
            this.this$0 = r1;
            this.$transform = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            return new AnonymousClass1(this.this$0, this.$transform, r3);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Preferences> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Preferences> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r7) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L12;
            if (r1 != 1) goto L9;
            ResultKt.throwOnFailure(r7);     // Catch: Throwable -> L7
        L18:
            Preferences r72 = (Preferences) r7;     // Catch: Throwable -> L7
            JavaDataStorage.access$getEditLock$p(this.this$0).set(Boxing.boxBoolean(false));
            return r72;
        L9:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L12:
            ResultKt.throwOnFailure(r7);
            if (Intrinsics.areEqual(JavaDataStorage.access$getEditLock$p(this.this$0).get(), Boxing.boxBoolean(true)) == true) goto L23;
            JavaDataStorage.access$getEditLock$p(this.this$0).set(Boxing.boxBoolean(true));
            DataStore r73 = JavaDataStorage.access$getDataStore$p(this.this$0);     // Catch: Throwable -> L7
            C00241 r12 = new C00241(this.$transform, null);     // Catch: Throwable -> L7
            this.label = 1;     // Catch: Throwable -> L7
            r7 = PreferencesKt.edit(r73, r12, this);     // Catch: Throwable -> L7
            if (r7 != r0) goto L18;
            return r0;
        L23:
            Segment$$ExternalSyntheticBUOutline1.m("Don't call JavaDataStorage.edit() from within an existing edit() callback.\nThis causes deadlocks, and is generally indicative of a code smell.\nInstead, either pass around the initial `MutablePreferences` instance, or don't do everything in a single callback. ");
            return null;
        L7:
            th = move-exception;
            JavaDataStorage.access$getEditLock$p(this.this$0).set(Boxing.boxBoolean(false));
            throw th;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/datastore/preferences/core/Preferences$Key;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.google.firebase.datastorage.JavaDataStorage$getAllSync$1", f = "JavaDataStorage.kt", i = {}, l = {170}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.google.firebase.datastorage.JavaDataStorage$getAllSync$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes5.dex */
    public static final class C01601 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Map<Preferences.Key<?>, ? extends Object>>, Object> {
        int label;
        final /* synthetic */ JavaDataStorage this$0;

        public C01601(JavaDataStorage r1, Continuation<? super C01601> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new C01601(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Map<Preferences.Key<?>, ? extends Object>> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Map<Preferences.Key<?>, ? extends Object>> r2) {
            return ((C01601) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r4);
        L11:
            Preferences r42 = (Preferences) r4;
            if (r42 == null) goto L18;
            Map<Preferences.Key<?>, Object> r3 = r42.asMap();
            if (r3 == null) goto L18;
            return r3;
        L18:
            return MapsKt.emptyMap();
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r4);
            Flow r43 = JavaDataStorage.access$getDataStore$p(this.this$0).getData();
            this.label = 1;
            r4 = FlowKt.firstOrNull(r43, this);
            if (r4 != r0) goto L11;
            return r0;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.google.firebase.datastorage.JavaDataStorage$getSync$1", f = "JavaDataStorage.kt", i = {}, l = {104}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.google.firebase.datastorage.JavaDataStorage$getSync$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes5.dex */
    public static final class C01611<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ T $defaultValue;
        final /* synthetic */ Preferences.Key<T> $key;
        int label;
        final /* synthetic */ JavaDataStorage this$0;

        public C01611(JavaDataStorage r1, Preferences.Key<T> r2, T r3, Continuation<? super C01611> r4) {
            this.this$0 = r1;
            this.$key = r2;
            this.$defaultValue = r3;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            return new C01611(this.this$0, this.$key, this.$defaultValue, r4);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Object r2) {
            return invoke(r1, (Continuation) r2);
        }

        public final Object invoke(CoroutineScope r1, Continuation<? super T> r2) {
            return ((C01611) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r4);
        L11:
            Preferences r42 = (Preferences) r4;
            if (r42 == null) goto L18;
            Object r43 = r42.get(this.$key);
            if (r43 == null) goto L18;
            return r43;
        L18:
            return this.$defaultValue;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r4);
            Flow<T> r44 = JavaDataStorage.access$getDataStore$p(this.this$0).getData();
            this.label = 1;
            r4 = FlowKt.firstOrNull(r44, this);
            if (r4 != r0) goto L11;
            return r0;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/datastore/preferences/core/Preferences;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.google.firebase.datastorage.JavaDataStorage$putSync$1", f = "JavaDataStorage.kt", i = {}, l = {145}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.google.firebase.datastorage.JavaDataStorage$putSync$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes5.dex */
    public static final class C01621 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Preferences>, Object> {
        final /* synthetic */ Preferences.Key<T> $key;
        final /* synthetic */ T $value;
        int label;
        final /* synthetic */ JavaDataStorage this$0;

        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "it", "Landroidx/datastore/preferences/core/MutablePreferences;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "com.google.firebase.datastorage.JavaDataStorage$putSync$1$1", f = "JavaDataStorage.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.google.firebase.datastorage.JavaDataStorage$putSync$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C00251 extends SuspendLambda implements Function2<MutablePreferences, Continuation<? super Unit>, Object> {
            final /* synthetic */ Preferences.Key<T> $key;
            final /* synthetic */ T $value;
            /* synthetic */ Object L$0;
            int label;

            public C00251(Preferences.Key<T> r1, T r2, Continuation<? super C00251> r3) {
                this.$key = r1;
                this.$value = r2;
                super(2, r3);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
                C00251 r0 = new C00251(this.$key, this.$value, r4);
                r0.L$0 = r3;
                return r0;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(MutablePreferences r1, Continuation<? super Unit> r2) {
                return ((C00251) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(MutablePreferences r1, Continuation<? super Unit> r2) {
                return invoke2(r1, r2);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r2) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) goto L6;
                ResultKt.throwOnFailure(r2);
                ((MutablePreferences) this.L$0).set(this.$key, this.$value);
                return Unit.INSTANCE;
            L6:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }

        public C01621(JavaDataStorage r1, Preferences.Key<T> r2, T r3, Continuation<? super C01621> r4) {
            this.this$0 = r1;
            this.$key = r2;
            this.$value = r3;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            return new C01621(this.this$0, this.$key, this.$value, r4);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Preferences> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Preferences> r2) {
            return ((C01621) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r7) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L9;
            if (r1 != 1) goto L7;
            ResultKt.throwOnFailure(r7);
            return r7;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r7);
            DataStore r72 = JavaDataStorage.access$getDataStore$p(this.this$0);
            C00251 r12 = new C00251(this.$key, this.$value, null);
            this.label = 1;
            Object r6 = PreferencesKt.edit(r72, r12, this);
            if (r6 != r0) goto L12;
            return r0;
        L12:
            return r6;
        }
    }

    public static List $r8$lambda$I1FBvVBM_wXEXXYcdJcvNwWI60g(JavaDataStorage r2, Context r3) {
        return CollectionsKt.listOf(SharedPreferencesMigrationKt.SharedPreferencesMigration$default(r3, r2.name, null, 4, null));
    }

    /* renamed from: $r8$lambda$S4ENo8tfimHebz0yGbY9bmLO-Q0, reason: not valid java name */
    public static Preferences m1949$r8$lambda$S4ENo8tfimHebz0yGbY9bmLOQ0(JavaDataStorage r3, CorruptionException r4) {
        Log.w(Reflection.getOrCreateKotlinClass(JavaDataStorage.class).getSimpleName(), "CorruptionException in " + r3.name + " DataStore running in process " + Process.myPid(), r4);
        return PreferencesFactory.createEmpty();
    }

    static {
        $$delegatedProperties = new KProperty[]{Reflection.property2(new PropertyReference2Impl(JavaDataStorage.class, "dataStore", "getDataStore(Landroid/content/Context;)Landroidx/datastore/core/DataStore;", 0))};
    }

    public JavaDataStorage(Context r8, String r9) {
        this.context = r8;
        this.name = r9;
        this.editLock = new ThreadLocal();
        this.dataStore$delegate = PreferenceDataStoreDelegateKt.preferencesDataStore$default(r9, new ReplaceFileCorruptionHandler(new JavaDataStorage$$ExternalSyntheticLambda0(this)), new JavaDataStorage$$ExternalSyntheticLambda1(this), null, 8, null);
        this.dataStore = getDataStore(r8);
    }

    public static final /* synthetic */ DataStore access$getDataStore$p(JavaDataStorage r0) {
        return r0.dataStore;
    }

    public static final /* synthetic */ ThreadLocal access$getEditLock$p(JavaDataStorage r0) {
        return r0.editLock;
    }

    private final DataStore<Preferences> getDataStore(Context r3) {
        return (DataStore) this.dataStore$delegate.getValue(r3, $$delegatedProperties[0]);
    }

    public final Preferences editSync(Function1<? super MutablePreferences, Unit> r3) {
        return (Preferences) BuildersKt.runBlocking$default(null, new AnonymousClass1(this, r3, null), 1, null);
    }

    public final Map<Preferences.Key<?>, Object> getAllSync() {
        return (Map) BuildersKt.runBlocking$default(null, new C01601(this, null), 1, null);
    }

    public final <T> T getSync(Preferences.Key<T> r3, T r4) {
        return (T) BuildersKt.runBlocking$default(null, new C01611(this, r3, r4, null), 1, null);
    }

    public final <T> Preferences putSync(Preferences.Key<T> r3, T r4) {
        return (Preferences) BuildersKt.runBlocking$default(null, new C01621(this, r3, r4, null), 1, null);
    }
}
