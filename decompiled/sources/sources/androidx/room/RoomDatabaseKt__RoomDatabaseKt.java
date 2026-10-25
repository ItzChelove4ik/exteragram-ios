package androidx.room;

import androidx.lifecycle.SavedStateHandleSupport$$ExternalSyntheticBUOutline0;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import c.f$$ExternalSyntheticBUOutline1;
import com.sun.jna.Native$$ExternalSyntheticBUOutline0;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import okhttp3.Request$Builder$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.Utf8$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000 \n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a+\u0010\u0005\u001a\u00020\u00042\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001b\u0010\n\u001a\u00020\u0004*\u00020\u00072\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a\u001b\u0010\f\u001a\u00020\u0004*\u00020\u00072\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "migrationStartAndEndVersions", "migrationsNotRequiredFrom", _UrlKt.FRAGMENT_ENCODE_SET, "validateMigrationsNotRequired", "(Ljava/util/Set;Ljava/util/Set;)V", "Landroidx/room/RoomDatabase;", "Landroidx/room/DatabaseConfiguration;", "configuration", "validateAutoMigrations", "(Landroidx/room/RoomDatabase;Landroidx/room/DatabaseConfiguration;)V", "validateTypeConverters", "room-runtime"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "androidx/room/RoomDatabaseKt")
@SourceDebugExtension({"SMAP\nRoomDatabase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RoomDatabase.kt\nandroidx/room/RoomDatabaseKt__RoomDatabaseKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,616:1\n1#2:617\n216#3,2:618\n*S KotlinDebug\n*F\n+ 1 RoomDatabase.kt\nandroidx/room/RoomDatabaseKt__RoomDatabaseKt\n*L\n585#1:618,2\n*E\n"})
/* loaded from: classes.dex */
abstract /* synthetic */ class RoomDatabaseKt__RoomDatabaseKt {
    public static final void validateAutoMigrations(RoomDatabase r9, DatabaseConfiguration r10) {
        LinkedHashMap r0 = new LinkedHashMap();
        Set<KClass<? extends AutoMigrationSpec>> r1 = r9.getRequiredAutoMigrationSpecClasses();
        int r2 = r10.autoMigrationSpecs.size();
        boolean[] r3 = new boolean[r2];
        Iterator<KClass<? extends AutoMigrationSpec>> r12 = r1.iterator();
    L3:
        int r5 = -1;
        if (r12.hasNext() == false) goto L17;
        KClass<? extends AutoMigrationSpec> r4 = r12.next();
        int r6 = r10.autoMigrationSpecs.size() - 1;
        if (r6 < 0) goto L13;
    L7:
        int r7 = r6 - 1;
        if (r4.isInstance(r10.autoMigrationSpecs.get(r6)) == true) goto L9;
        if (r7 < 0) goto L13;
        r6 = r7;
        goto L7
    L9:
        r3[r6] = true;
        r5 = r6;
    L13:
        if (r5 < 0) goto L15;
        r0.put(r4, r10.autoMigrationSpecs.get(r5));
        goto L3
    L15:
        Request$Builder$$ExternalSyntheticBUOutline0.m("A required auto migration spec (", r4.getQualifiedName(), ") is missing in the database configuration.");
        return;
    L17:
        int r13 = r10.autoMigrationSpecs.size() - 1;
        if (r13 < 0) goto L28;
    L19:
        int r42 = r13 - 1;
        if (r13 >= r2) goto L26;
        if (r3[r13] == false) goto L26;
        if (r42 < 0) goto L28;
        r13 = r42;
    L26:
        f$$ExternalSyntheticBUOutline1.m("Unexpected auto migration specs found. Annotate AutoMigrationSpec implementation with @ProvidedAutoMigrationSpec annotation or remove this spec from the builder.");
        return;
    L28:
        Iterator<Migration> r92 = r9.createAutoMigrations(r0).iterator();
    L30:
        if (r92.hasNext() == false) goto L34;
        Migration r02 = r92.next();
        if (r10.migrationContainer.contains(r02.startVersion, r02.endVersion) == true) goto L30;
        r10.migrationContainer.addMigration(r02);
        goto L30
    }

    public static final void validateMigrationsNotRequired(Set<Integer> r2, Set<Integer> r3) {
        if (r2.isEmpty() == true) goto L15;
        Iterator<Integer> r22 = r2.iterator();
    L6:
        if (r22.hasNext() == false) goto L16;
        int r0 = r22.next().intValue();
        if (r3.contains(Integer.valueOf(r0)) == false) goto L6;
        Utf8$$ExternalSyntheticBUOutline1.m("Inconsistency detected. A Migration was supplied to addMigration() that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(). Start version is: ", r0);
        return;
    L16:
        return;
    }

    public static final void validateTypeConverters(RoomDatabase r9, DatabaseConfiguration r10) {
        Map<KClass<?>, List<KClass<?>>> r0 = r9.getRequiredTypeConverterClassesMap$room_runtime();
        boolean[] r1 = new boolean[r10.typeConverters.size()];
        Iterator<Map.Entry<KClass<?>, List<KClass<?>>>> r02 = r0.entrySet().iterator();
    L4:
        if (r02.hasNext() == false) goto L21;
        Map.Entry<KClass<?>, List<KClass<?>>> r2 = r02.next();
        KClass<?> r4 = r2.getKey();
        Iterator<KClass<?>> r22 = r2.getValue().iterator();
    L7:
        if (r22.hasNext() == false) goto L4;
        KClass<?> r5 = r22.next();
        int r6 = r10.typeConverters.size() - 1;
        if (r6 < 0) goto L16;
    L10:
        int r7 = r6 - 1;
        if (r5.isInstance(r10.typeConverters.get(r6)) == true) goto L12;
        if (r7 < 0) goto L16;
        r6 = r7;
        goto L10
    L12:
        r1[r6] = true;
    L17:
        if (r6 < 0) goto L19;
        r9.addTypeConverter$room_runtime(r5, r10.typeConverters.get(r6));
        goto L7
    L19:
        SavedStateHandleSupport$$ExternalSyntheticBUOutline0.m("A required type converter (", r5.getQualifiedName(), ") for ", r4.getQualifiedName(), " is missing in the database configuration.");
        return;
    L16:
        r6 = -1;
        goto L17
    L21:
        int r92 = r10.typeConverters.size() - 1;
        if (r92 < 0) goto L37;
    L23:
        int r03 = r92 - 1;
        if (r1[r92] == false) goto L28;
        if (r03 < 0) goto L38;
        r92 = r03;
        goto L23
    L38:
        return;
    L28:
        Native$$ExternalSyntheticBUOutline0.m("Unexpected type converter ", r10.typeConverters.get(r92), ". Annotate TypeConverter class with @ProvidedTypeConverter annotation or remove this converter from the builder.");
        return;
    }
}
