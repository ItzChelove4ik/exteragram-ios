package androidx.datastore.migrations;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.datastore.core.DataMigration;
import java.util.Iterator;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0010#\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002:\u0001,B\u0089\u0001\b\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012$\b\u0002\u0010\r\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\t\u0012(\u0010\u0010\u001a$\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000e\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0014\u0010\u0015By\b\u0017\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0016\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012$\b\u0002\u0010\r\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\t\u0012(\u0010\u0010\u001a$\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000e¢\u0006\u0004\b\u0014\u0010\u0017J\u001f\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u0018\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00028\u0000H\u0096@¢\u0006\u0004\b\u001c\u0010\u001dJ\u0018\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00028\u0000H\u0096@¢\u0006\u0004\b\u0010\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0018H\u0096@¢\u0006\u0004\b\u001e\u0010\u001fR0\u0010\r\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010 R6\u0010\u0010\u001a$\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010!R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\"R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010#R\u001b\u0010(\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R\u001c\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+¨\u0006-"}, d2 = {"Landroidx/datastore/migrations/SharedPreferencesMigration;", "T", "Landroidx/datastore/core/DataMigration;", "Lkotlin/Function0;", "Landroid/content/SharedPreferences;", "produceSharedPreferences", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "keysToMigrate", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "shouldRunMigration", "Lkotlin/Function3;", "Landroidx/datastore/migrations/SharedPreferencesView;", "migrate", "Landroid/content/Context;", "context", "name", "<init>", "(Lkotlin/jvm/functions/Function0;Ljava/util/Set;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroid/content/Context;Ljava/lang/String;)V", "sharedPreferencesName", "(Landroid/content/Context;Ljava/lang/String;Ljava/util/Set;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)V", _UrlKt.FRAGMENT_ENCODE_SET, "deleteSharedPreferences", "(Landroid/content/Context;Ljava/lang/String;)V", "currentData", "shouldMigrate", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cleanUp", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function3;", "Landroid/content/Context;", "Ljava/lang/String;", "sharedPrefs$delegate", "Lkotlin/Lazy;", "getSharedPrefs", "()Landroid/content/SharedPreferences;", "sharedPrefs", _UrlKt.FRAGMENT_ENCODE_SET, "keySet", "Ljava/util/Set;", "Api24Impl", "datastore_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSharedPreferencesMigration.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SharedPreferencesMigration.android.kt\nandroidx/datastore/migrations/SharedPreferencesMigration\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,323:1\n1747#2,3:324\n1855#2,2:327\n*S KotlinDebug\n*F\n+ 1 SharedPreferencesMigration.android.kt\nandroidx/datastore/migrations/SharedPreferencesMigration\n*L\n158#1:324,3\n178#1:327,2\n*E\n"})
/* loaded from: classes.dex */
public final class SharedPreferencesMigration<T> implements DataMigration<T> {
    private final Context context;
    private final Set<String> keySet;
    private final Function3<SharedPreferencesView, T, Continuation<? super T>, Object> migrate;
    private final String name;
    private final Lazy sharedPrefs$delegate;
    private final Function2<T, Continuation<? super Boolean>, Object> shouldRunMigration;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H\u008a@"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "T", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.migrations.SharedPreferencesMigration$3", f = "SharedPreferencesMigration.android.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.datastore.migrations.SharedPreferencesMigration$3, reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass3 extends SuspendLambda implements Function2<T, Continuation<? super Boolean>, Object> {
        int label;

        public AnonymousClass3(Continuation<? super AnonymousClass3> r2) {
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass3(r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object r1, Continuation<? super Boolean> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(T r1, Continuation<? super Boolean> r2) {
            return ((AnonymousClass3) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r1) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L6;
            ResultKt.throwOnFailure(r1);
            return Boxing.boxBoolean(true);
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroid/content/SharedPreferences;", "T", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.datastore.migrations.SharedPreferencesMigration$4, reason: invalid class name */
    public static final class AnonymousClass4 extends Lambda implements Function0<SharedPreferences> {
        final /* synthetic */ Context $context;
        final /* synthetic */ String $sharedPreferencesName;

        public AnonymousClass4(Context r1, String r2) {
            this.$context = r1;
            this.$sharedPreferencesName = r2;
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SharedPreferences invoke() {
            return this.$context.getSharedPreferences(this.$sharedPreferencesName, 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ SharedPreferences invoke() {
            return invoke();
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Landroidx/datastore/migrations/SharedPreferencesMigration$Api24Impl;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "deleteSharedPreferences", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Landroid/content/Context;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "datastore_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Api24Impl {
        public static final Api24Impl INSTANCE = null;

        static {
            INSTANCE = new Api24Impl();
        }

        private Api24Impl() {
        }

        @JvmStatic
        public static final boolean deleteSharedPreferences(Context r0, String r1) {
            return r0.deleteSharedPreferences(r1);
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.migrations.SharedPreferencesMigration", f = "SharedPreferencesMigration.android.kt", i = {0}, l = {151}, m = "shouldMigrate", n = {"this"}, s = {"L$0"})
    /* renamed from: androidx.datastore.migrations.SharedPreferencesMigration$shouldMigrate$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SharedPreferencesMigration<T> this$0;

        public AnonymousClass1(SharedPreferencesMigration<T> r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.shouldMigrate(null, this);
        }
    }

    @JvmOverloads
    public SharedPreferencesMigration(Context r8, String r9, Set<String> r10, Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> r11, Function3<? super SharedPreferencesView, ? super T, ? super Continuation<? super T>, ? extends Object> r12) {
        this(new AnonymousClass4(r8, r9), r10, r11, r12, r8, r9);
    }

    public /* synthetic */ SharedPreferencesMigration(Context r7, String r8, Set r9, Function2 r10, Function3 r11, int r12, DefaultConstructorMarker r13) {
        if ((r12 & 4) == 0) goto L5;
        r9 = SharedPreferencesMigration_androidKt.getMIGRATE_ALL_KEYS();
    L5:
        Set r3 = r9;
        if ((r12 & 8) == 0) goto L8;
        r10 = new AnonymousClass3(null);
    L8:
        this(r7, r8, r3, r10, r11);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private SharedPreferencesMigration(Function0<? extends SharedPreferences> r1, Set<String> r2, Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> r3, Function3<? super SharedPreferencesView, ? super T, ? super Continuation<? super T>, ? extends Object> r4, Context r5, String r6) {
        this.shouldRunMigration = r3;
        this.migrate = r4;
        this.context = r5;
        this.name = r6;
        this.sharedPrefs$delegate = LazyKt.lazy(r1);
        if (r2 != SharedPreferencesMigration_androidKt.getMIGRATE_ALL_KEYS()) goto L5;
        Set<String> r12 = null;
    L6:
        this.keySet = r12;
        return;
    L5:
        r12 = CollectionsKt.toMutableSet(r2);
        goto L6
    }

    private final void deleteSharedPreferences(Context r1, String r2) {
        Api24Impl.deleteSharedPreferences(r1, r2);
    }

    private final SharedPreferences getSharedPrefs() {
        return (SharedPreferences) this.sharedPrefs$delegate.getValue();
    }

    @Override // androidx.datastore.core.DataMigration
    public Object cleanUp(Continuation<? super Unit> r3) {
        SharedPreferences.Editor r32 = getSharedPrefs().edit();
        Set<String> r0 = this.keySet;
        if (r0 != null) goto L5;
        r32.clear();
    L10:
        if (r32.commit() == true) goto L12;
        Model$$ExternalSyntheticBUOutline0.m("Unable to delete migrated keys from SharedPreferences.");
        return null;
    L12:
        if (getSharedPrefs().getAll().isEmpty() == false) goto L18;
        Context r33 = this.context;
        if (r33 == null) goto L18;
        String r02 = this.name;
        if (r02 == null) goto L18;
        deleteSharedPreferences(r33, r02);
    L18:
        Set<String> r2 = this.keySet;
        if (r2 == null) goto L22;
        r2.clear();
    L22:
        return Unit.INSTANCE;
    L5:
        Iterator<T> r03 = r0.iterator();
    L7:
        if (r03.hasNext() == false) goto L10;
        r32.remove((String) r03.next());
        goto L7
    }

    @Override // androidx.datastore.core.DataMigration
    public Object migrate(T r4, Continuation<? super T> r5) {
        return this.migrate.invoke(new SharedPreferencesView(getSharedPrefs(), this.keySet), r4, r5);
    }

    @Override // androidx.datastore.core.DataMigration
    public Object shouldMigrate(T r5, Continuation<? super Boolean> r6) {
        if ((r6 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r6;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r62 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        boolean r3 = true;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        SharedPreferencesMigration<T> this = (SharedPreferencesMigration) r0.L$0;
        ResultKt.throwOnFailure(r62);
    L18:
        if (((Boolean) r62).booleanValue() == false) goto L20;
        Set<String> r52 = this.keySet;
        if (r52 == null) goto L24;
        SharedPreferences r4 = this.getSharedPrefs();
        if (r52 != null) goto L30;
    L32:
        Iterator<T> r53 = r52.iterator();
    L34:
        if (r53.hasNext() == false) goto L26;
        if (r4.contains((String) r53.next()) == false) goto L34;
    L38:
        return Boxing.boxBoolean(r3);
    L26:
        r3 = false;
        goto L38
    L30:
        if (r52.isEmpty() == false) goto L32;
    L24:
        if (this.getSharedPrefs().getAll().isEmpty() == true) goto L26;
    L20:
        return Boxing.boxBoolean(false);
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r62);
        Function2<T, Continuation<? super Boolean>, Object> r63 = this.shouldRunMigration;
        r0.L$0 = this;
        r0.label = 1;
        r62 = r63.invoke(r5, r0);
        if (r62 != r12) goto L18;
        return r12;
    L7:
        r0 = new AnonymousClass1(this, r6);
        goto L8
    }
}
