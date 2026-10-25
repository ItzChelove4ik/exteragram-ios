package com.google.android.gms.internal.measurement;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;
import android.os.UserHandle;
import android.util.Log;
import com.google.common.base.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@TargetApi(24)
/* loaded from: classes4.dex */
public abstract class zzcf {
    private static final Method zza = null;
    private static final Method zzb = null;

    static {
        Method r3 = null;
        Method r0 = JobScheduler.class.getDeclaredMethod("scheduleAsPackage", new Class[]{JobInfo.class, String.class, Integer.TYPE, String.class});     // Catch: NoSuchMethodException -> L5
    L9:
        zza = r0;
        r3 = UserHandle.class.getDeclaredMethod("myUserId", null);     // Catch: NoSuchMethodException -> L12
    L15:
        zzb = r3;
        return;
    L13:
        if (Log.isLoggable("JobSchedulerCompat", 6) == false) goto L15;
        Log.e("JobSchedulerCompat", "No myUserId method available");
    L6:
        if (Log.isLoggable("JobSchedulerCompat", 6) == false) goto L8;
        Log.e("JobSchedulerCompat", "No scheduleAsPackage method available, falling back to schedule");
    L8:
        r0 = null;
        goto L9
    }

    public static int zza(Context r3, JobInfo r4, String r5, String r6) {
        JobScheduler r52 = (JobScheduler) Preconditions.checkNotNull((JobScheduler) r3.getSystemService("jobscheduler"));
        if (zza == null) goto L33;
        if (r3.checkSelfPermission("android.permission.UPDATE_DEVICE_STATS") != 0) goto L33;
        Method r32 = zzb;
        if (r32 != null) goto L36;
    L16:
        int r33 = 0;
    L20:
        Method r2 = zza;
        if (r2 == null) goto L31;
        Integer r34 = (Integer) r2.invoke(r52, new Object[]{r4, "com.google.android.gms", Integer.valueOf(r33), "UploadAlarm"});     // Catch: InvocationTargetException -> L26 Throwable -> L28
        if (r34 == null) goto L38;
        return r34.intValue();
    L38:
        return 0;
    L28:
        e = move-exception;
        Log.e("UploadAlarm", "error calling scheduleAsPackage", e);
    L31:
        return r52.schedule(r4);
    L36:
        Integer r35 = (Integer) r32.invoke(UserHandle.class, null);     // Catch: Throwable -> L12 IllegalAccessException -> L14
        if (r35 == null) goto L16;
        r33 = r35.intValue();     // Catch: Throwable -> L12 IllegalAccessException -> L14
    L12:
        e = move-exception;
        if (Log.isLoggable("JobSchedulerCompat", 6) == false) goto L16;
        Log.e("JobSchedulerCompat", "myUserId invocation illegal", e);
    L33:
        return r52.schedule(r4);
    }
}
