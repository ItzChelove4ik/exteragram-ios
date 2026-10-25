package androidx.datastore.preferences;

import android.content.Context;
import androidx.datastore.migrations.SharedPreferencesMigration;
import androidx.datastore.migrations.SharedPreferencesView;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\u001a5\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004H\u0007¢\u0006\u0004\b\b\u0010\t\u001a1\u0010\u000e\u001a$\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\nH\u0002¢\u0006\u0004\b\u000e\u0010\u000f\u001a9\u0010\u0012\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00102\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004H\u0002¢\u0006\u0004\b\u0012\u0010\u0013\" \u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Landroid/content/Context;", "context", _UrlKt.FRAGMENT_ENCODE_SET, "sharedPreferencesName", _UrlKt.FRAGMENT_ENCODE_SET, "keysToMigrate", "Landroidx/datastore/migrations/SharedPreferencesMigration;", "Landroidx/datastore/preferences/core/Preferences;", "SharedPreferencesMigration", "(Landroid/content/Context;Ljava/lang/String;Ljava/util/Set;)Landroidx/datastore/migrations/SharedPreferencesMigration;", "Lkotlin/Function3;", "Landroidx/datastore/migrations/SharedPreferencesView;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "getMigrationFunction", "()Lkotlin/jvm/functions/Function3;", "Lkotlin/Function2;", _UrlKt.FRAGMENT_ENCODE_SET, "getShouldRunMigration", "(Ljava/util/Set;)Lkotlin/jvm/functions/Function2;", "MIGRATE_ALL_KEYS", "Ljava/util/Set;", "getMIGRATE_ALL_KEYS", "()Ljava/util/Set;", "datastore-preferences_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
@JvmName(name = "SharedPreferencesMigrationKt")
/* loaded from: classes.dex */
public abstract class SharedPreferencesMigrationKt {
    private static final Set<String> MIGRATE_ALL_KEYS = null;

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", "Landroidx/datastore/preferences/core/Preferences;", "sharedPrefs", "Landroidx/datastore/migrations/SharedPreferencesView;", "currentData"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.preferences.SharedPreferencesMigrationKt$getMigrationFunction$1", f = "SharedPreferencesMigration.android.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    @SourceDebugExtension({"SMAP\nSharedPreferencesMigration.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SharedPreferencesMigration.android.kt\nandroidx/datastore/preferences/SharedPreferencesMigrationKt$getMigrationFunction$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,157:1\n1549#2:158\n1620#2,3:159\n515#3:162\n500#3,6:163\n*S KotlinDebug\n*F\n+ 1 SharedPreferencesMigration.android.kt\nandroidx/datastore/preferences/SharedPreferencesMigrationKt$getMigrationFunction$1\n*L\n108#1:158\n108#1:159,3\n111#1:162\n111#1:163,6\n*E\n"})
    /* renamed from: androidx.datastore.preferences.SharedPreferencesMigrationKt$getMigrationFunction$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function3<SharedPreferencesView, Preferences, Continuation<? super Preferences>, Object> {
        /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        public AnonymousClass1(Continuation<? super AnonymousClass1> r2) {
            super(3, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(SharedPreferencesView r1, Preferences r2, Continuation<? super Preferences> r3) {
            AnonymousClass1 r0 = new AnonymousClass1(r3);
            r0.L$0 = r1;
            r0.L$1 = r2;
            return r0.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(SharedPreferencesView r1, Preferences r2, Continuation<? super Preferences> r3) {
            return invoke2(r1, r2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r5) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L37;
            ResultKt.throwOnFailure(r5);
            SharedPreferencesView r52 = (SharedPreferencesView) this.L$0;
            Preferences r4 = (Preferences) this.L$1;
            Set<Preferences.Key<?>> r0 = r4.asMap().keySet();
            ArrayList r1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r0, 10));
            Iterator<T> r02 = r0.iterator();
        L6:
            if (r02.hasNext() == false) goto L8;
            r1.add(((Preferences.Key) r02.next()).getName());
            goto L6
        L8:
            Map<String, Object> r53 = r52.getAll();
            LinkedHashMap r03 = new LinkedHashMap();
            Iterator<Map.Entry<String, Object>> r54 = r53.entrySet().iterator();
        L10:
            if (r54.hasNext() == false) goto L14;
            Map.Entry<String, Object> r2 = r54.next();
            if (r1.contains(r2.getKey()) == true) goto L10;
            r03.put(r2.getKey(), r2.getValue());
            goto L10
        L14:
            MutablePreferences r42 = r4.toMutablePreferences();
            Iterator r55 = r03.entrySet().iterator();
        L16:
            if (r55.hasNext() == false) goto L36;
            Map.Entry r04 = (Map.Entry) r55.next();
            String r12 = (String) r04.getKey();
            Object r05 = r04.getValue();
            if ((r05 instanceof Boolean) == true) goto L19;
            if ((r05 instanceof Float) == true) goto L22;
            if ((r05 instanceof Integer) == true) goto L25;
            if ((r05 instanceof Long) == true) goto L28;
            if ((r05 instanceof String) == true) goto L31;
            if ((r05 instanceof Set) == false) goto L16;
            r42.set(PreferencesKeys.stringSetKey(r12), (Set) r05);
            goto L16
        L31:
            r42.set(PreferencesKeys.stringKey(r12), r05);
            goto L16
        L28:
            r42.set(PreferencesKeys.longKey(r12), r05);
            goto L16
        L25:
            r42.set(PreferencesKeys.intKey(r12), r05);
            goto L16
        L22:
            r42.set(PreferencesKeys.floatKey(r12), r05);
            goto L16
        L19:
            r42.set(PreferencesKeys.booleanKey(r12), r05);
            goto L16
        L36:
            return r42.toPreferences();
        L37:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "prefs", "Landroidx/datastore/preferences/core/Preferences;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.preferences.SharedPreferencesMigrationKt$getShouldRunMigration$1", f = "SharedPreferencesMigration.android.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    @SourceDebugExtension({"SMAP\nSharedPreferencesMigration.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SharedPreferencesMigration.android.kt\nandroidx/datastore/preferences/SharedPreferencesMigrationKt$getShouldRunMigration$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,157:1\n1549#2:158\n1620#2,3:159\n1747#2,3:162\n*S KotlinDebug\n*F\n+ 1 SharedPreferencesMigration.android.kt\nandroidx/datastore/preferences/SharedPreferencesMigrationKt$getShouldRunMigration$1\n*L\n147#1:158\n147#1:159,3\n152#1:162,3\n*E\n"})
    /* renamed from: androidx.datastore.preferences.SharedPreferencesMigrationKt$getShouldRunMigration$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01171 extends SuspendLambda implements Function2<Preferences, Continuation<? super Boolean>, Object> {
        final /* synthetic */ Set<String> $keysToMigrate;
        /* synthetic */ Object L$0;
        int label;

        public C01171(Set<String> r1, Continuation<? super C01171> r2) {
            this.$keysToMigrate = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            C01171 r0 = new C01171(this.$keysToMigrate, r3);
            r0.L$0 = r2;
            return r0;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Preferences r1, Continuation<? super Boolean> r2) {
            return ((C01171) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Preferences r1, Continuation<? super Boolean> r2) {
            return invoke2(r1, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L23;
            ResultKt.throwOnFailure(r4);
            Set<Preferences.Key<?>> r42 = ((Preferences) this.L$0).asMap().keySet();
            ArrayList r0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r42, 10));
            Iterator<T> r43 = r42.iterator();
        L6:
            if (r43.hasNext() == false) goto L8;
            r0.add(((Preferences.Key) r43.next()).getName());
            goto L6
        L8:
            boolean r2 = true;
            if (this.$keysToMigrate == SharedPreferencesMigrationKt.getMIGRATE_ALL_KEYS()) goto L22;
            Set<String> r3 = this.$keysToMigrate;
            if (r3 != null) goto L14;
        L16:
            Iterator<T> r32 = r3.iterator();
        L18:
            if (r32.hasNext() == false) goto L15;
            if (r0.contains((String) r32.next()) == true) goto L18;
        L15:
            r2 = false;
            goto L22
        L14:
            if (r3.isEmpty() == false) goto L16;
        L22:
            return Boxing.boxBoolean(r2);
        L23:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    static {
        MIGRATE_ALL_KEYS = new LinkedHashSet();
    }

    @JvmOverloads
    public static final SharedPreferencesMigration<Preferences> SharedPreferencesMigration(Context r9, String r10, Set<String> r11) {
        if (r11 != MIGRATE_ALL_KEYS) goto L7;
        return new SharedPreferencesMigration(r9, r10, null, getShouldRunMigration(r11), getMigrationFunction(), 4, null);
    L7:
        return new SharedPreferencesMigration(r9, r10, r11, getShouldRunMigration(r11), getMigrationFunction());
    }

    public static /* synthetic */ SharedPreferencesMigration SharedPreferencesMigration$default(Context r0, String r1, Set r2, int r3, Object r4) {
        if ((r3 & 4) == 0) goto L6;
        r2 = MIGRATE_ALL_KEYS;
    L6:
        return SharedPreferencesMigration(r0, r1, r2);
    }

    public static final Set<String> getMIGRATE_ALL_KEYS() {
        return MIGRATE_ALL_KEYS;
    }

    private static final Function3<SharedPreferencesView, Preferences, Continuation<? super Preferences>, Object> getMigrationFunction() {
        return new AnonymousClass1(null);
    }

    private static final Function2<Preferences, Continuation<? super Boolean>, Object> getShouldRunMigration(Set<String> r2) {
        return new C01171(r2, null);
    }
}
