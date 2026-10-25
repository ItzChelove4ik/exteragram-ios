package androidx.room.util;

import androidx.room.DatabaseConfiguration;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmName;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0000\u001a\u001c\u0010\u0006\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0000\u001a$\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b*\u00020\u00072\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0000\u001a:\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b*\u00020\u00072\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0002¨\u0006\u0013"}, d2 = {"isMigrationRequired", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/DatabaseConfiguration;", "fromVersion", _UrlKt.FRAGMENT_ENCODE_SET, "toVersion", "contains", "Landroidx/room/RoomDatabase$MigrationContainer;", "startVersion", "endVersion", "findMigrationPath", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/migration/Migration;", "start", "end", "findUpMigrationPath", "result", _UrlKt.FRAGMENT_ENCODE_SET, "upgrade", "room-runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
@JvmName(name = "MigrationUtil")
/* loaded from: classes.dex */
public abstract class MigrationUtil {
    public static final boolean contains(RoomDatabase.MigrationContainer r1, int r2, int r3) {
        Map<Integer, Map<Integer, Migration>> r12 = r1.getMigrations();
        if (r12.containsKey(Integer.valueOf(r2)) == false) goto L9;
        Map<Integer, Migration> r13 = r12.get(Integer.valueOf(r2));
        if (r13 != null) goto L8;
        r13 = MapsKt.emptyMap();
    L8:
        return r13.containsKey(Integer.valueOf(r3));
    L9:
        return false;
    }

    public static final List<Migration> findMigrationPath(RoomDatabase.MigrationContainer r2, int r3, int r4) {
        if (r3 == r4) goto L4;
        if (r4 <= r3) goto L7;
        boolean r0 = true;
    L9:
        return findUpMigrationPath(r2, new ArrayList(), r0, r3, r4);
    L7:
        r0 = false;
        goto L9
    L4:
        return CollectionsKt.emptyList();
    }

    private static final List<Migration> findUpMigrationPath(RoomDatabase.MigrationContainer r5, List<Migration> r6, boolean r7, int r8, int r9) {
    L2:
        if (r7 == false) goto L6;
        if (r8 >= r9) goto L5;
    L7:
        if (r7 == false) goto L9;
        Pair<Map<Integer, Migration>, Iterable<Integer>> r0 = r5.getSortedDescendingNodes$room_runtime(r8);
    L11:
        if (r0 == null) goto L12;
        Map<Integer, Migration> r2 = r0.component1();
        Iterator<Integer> r02 = r0.component2().iterator();
    L15:
        if (r02.hasNext() == false) goto L25;
        int r3 = r02.next().intValue();
        if (r7 == true) goto L19;
        if (r9 > r3) goto L15;
        if (r3 >= r8) goto L15;
    L24:
        r6.add(r2.get(Integer.valueOf(r3)));
        boolean r82 = true;
    L26:
        if (r82 == false) goto L27;
        r8 = r3;
        goto L2
    L27:
        return null;
    L19:
        if ((r8 + 1) > r3) goto L15;
        if (r3 > r9) goto L15;
    L25:
        r3 = r8;
        r82 = false;
        goto L26
    L12:
        return null;
    L9:
        r0 = r5.getSortedNodes$room_runtime(r8);
        goto L11
    L5:
        return r6;
    L6:
        if (r8 > r9) goto L7;
        return r6;
    }

    public static final boolean isMigrationRequired(DatabaseConfiguration r1, int r2, int r3) {
        if (r2 > r3) goto L5;
    L7:
        Set<Integer> r32 = r1.getMigrationNotRequiredFrom$room_runtime();
        if (r1.requireMigration == false) goto L14;
        if (r32 != null) goto L11;
        return true;
    L11:
        if (r32.contains(Integer.valueOf(r2)) == true) goto L14;
        return true;
    L14:
        return false;
    L5:
        if (r1.allowDestructiveMigrationOnDowngrade == false) goto L7;
        return false;
    }
}
