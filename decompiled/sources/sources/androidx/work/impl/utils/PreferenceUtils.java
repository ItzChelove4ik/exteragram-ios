package androidx.work.impl.utils;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.Preference;

/* loaded from: classes.dex */
public class PreferenceUtils {
    private final WorkDatabase mWorkDatabase;

    public PreferenceUtils(WorkDatabase r1) {
        this.mWorkDatabase = r1;
    }

    public static void migrateLegacyPreferences(Context r9, SupportSQLiteDatabase r10) {
        SharedPreferences r92 = r9.getSharedPreferences("androidx.work.util.preferences", 0);
        if (r92.contains("reschedule_needed") == false) goto L5;
    L8:
        long r5 = 0;
        long r7 = r92.getLong("last_cancel_all_time_ms", 0);
        if (r92.getBoolean("reschedule_needed", false) == false) goto L11;
        r5 = 1;
    L11:
        r10.beginTransaction();
        r10.execSQL("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"last_cancel_all_time_ms", Long.valueOf(r7)});     // Catch: Throwable -> L15
        r10.execSQL("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"reschedule_needed", Long.valueOf(r5)});     // Catch: Throwable -> L15
        r92.edit().clear().apply();     // Catch: Throwable -> L15
        r10.setTransactionSuccessful();     // Catch: Throwable -> L15
        r10.endTransaction();
        return;
    L15:
        th = move-exception;
        r10.endTransaction();
        throw th;
    L5:
        if (r92.contains("last_cancel_all_time_ms") == true) goto L8;
    }

    public long getLastForceStopEventMillis() {
        Long r2 = this.mWorkDatabase.preferenceDao().getLongValue("last_force_stop_ms");
        if (r2 != null) goto L5;
        return 0;
    L5:
        return r2.longValue();
    }

    public boolean getNeedsReschedule() {
        Long r4 = this.mWorkDatabase.preferenceDao().getLongValue("reschedule_needed");
        if (r4 != null) goto L5;
        return false;
    L5:
        if (r4.longValue() != 1) goto L10;
        return true;
    L10:
        return false;
    }

    public void setLastForceStopEventMillis(long r3) {
        Preference r0 = new Preference("last_force_stop_ms", Long.valueOf(r3));
        this.mWorkDatabase.preferenceDao().insertPreference(r0);
    }

    public void setNeedsReschedule(boolean r3) {
        Preference r0 = new Preference("reschedule_needed", r3);
        this.mWorkDatabase.preferenceDao().insertPreference(r0);
    }
}
