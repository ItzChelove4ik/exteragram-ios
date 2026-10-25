package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Context;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.PersistableBundle;
import androidx.work.BackoffPolicy;
import androidx.work.Clock;
import androidx.work.Constraints;
import androidx.work.Logger;
import androidx.work.NetworkType;
import androidx.work.impl.model.WorkSpec;
import java.util.Iterator;

/* loaded from: classes.dex */
class SystemJobInfoConverter {
    private static final String TAG = null;
    private final Clock mClock;
    private final boolean mMarkImportantWhileForeground;
    private final ComponentName mWorkServiceComponent;

    /* renamed from: androidx.work.impl.background.systemjob.SystemJobInfoConverter$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$work$NetworkType = null;

        static {
            int[] r0 = new int[NetworkType.values().length];
            $SwitchMap$androidx$work$NetworkType = r0;
            r0[NetworkType.NOT_REQUIRED.ordinal()] = 1;     // Catch: NoSuchFieldError -> L9
        L14:
            $SwitchMap$androidx$work$NetworkType[NetworkType.CONNECTED.ordinal()] = 2;     // Catch: NoSuchFieldError -> L10
        L18:
            $SwitchMap$androidx$work$NetworkType[NetworkType.UNMETERED.ordinal()] = 3;     // Catch: NoSuchFieldError -> L11
        L22:
            $SwitchMap$androidx$work$NetworkType[NetworkType.NOT_ROAMING.ordinal()] = 4;     // Catch: NoSuchFieldError -> L12
        L16:
            $SwitchMap$androidx$work$NetworkType[NetworkType.METERED.ordinal()] = 5;     // Catch: NoSuchFieldError -> L13
            return;
        }
    }

    static {
        TAG = Logger.tagWithPrefix("SystemJobInfoConverter");
    }

    public SystemJobInfoConverter(Context r2, Clock r3, boolean r4) {
        this.mClock = r3;
        this.mWorkServiceComponent = new ComponentName(r2.getApplicationContext(), SystemJobService.class);
        this.mMarkImportantWhileForeground = r4;
    }

    private static JobInfo.TriggerContentUri convertContentUriTrigger(Constraints.ContentUriTrigger r2) {
        boolean r0 = r2.isTriggeredForDescendants();
        return new JobInfo.TriggerContentUri(r2.getUri(), r0 ? 1 : 0);
    }

    public static int convertNetworkType(NetworkType r5) {
        int r0 = AnonymousClass1.$SwitchMap$androidx$work$NetworkType[r5.ordinal()];
        if (r0 != 1) goto L5;
        return 0;
    L5:
        if (r0 != 2) goto L7;
        return 1;
    L7:
        if (r0 != 3) goto L9;
        return 2;
    L9:
        if (r0 != 4) goto L11;
        return 3;
    L11:
        if (r0 == 5) goto L14;
    L16:
        Logger.get().debug(TAG, "API version too low. Cannot convert network type value " + r5);
        return 1;
    L14:
        if (Build.VERSION.SDK_INT < 26) goto L16;
        return 4;
    }

    public static void setRequiredNetwork(JobInfo.Builder r2, NetworkType r3) {
        if (Build.VERSION.SDK_INT >= 30) goto L5;
    L8:
        r2.setRequiredNetworkType(convertNetworkType(r3));
        return;
    L5:
        if (r3 != NetworkType.TEMPORARILY_UNMETERED) goto L8;
        r2.setRequiredNetwork(new NetworkRequest.Builder().addCapability(25).build());
    }

    public JobInfo convert(WorkSpec r11, int r12) {
        Constraints r0 = r11.constraints;
        PersistableBundle r1 = new PersistableBundle();
        r1.putString("EXTRA_WORK_SPEC_ID", r11.id);
        r1.putInt("EXTRA_WORK_SPEC_GENERATION", r11.getGeneration());
        r1.putBoolean("EXTRA_IS_PERIODIC", r11.isPeriodic());
        JobInfo.Builder r122 = new JobInfo.Builder(r12, this.mWorkServiceComponent).setRequiresCharging(r0.requiresCharging()).setRequiresDeviceIdle(r0.requiresDeviceIdle()).setExtras(r1);
        NetworkRequest r13 = r0.getRequiredNetworkRequest();
        int r2 = Build.VERSION.SDK_INT;
        if (r2 < 28) goto L6;
        if (r13 == null) goto L6;
        SystemJobInfoConverterExtKt.setRequiredNetworkRequest(r122, r13);
    L7:
        boolean r4 = false;
        if (r0.requiresDeviceIdle() == false) goto L10;
    L14:
        long r6 = Math.max(r11.calculateNextRunTime() - this.mClock.currentTimeMillis(), 0);
        if (r2 > 28) goto L18;
        r122.setMinimumLatency(r6);
    L26:
        if (r0.hasContentUriTriggers() == false) goto L32;
        Iterator<Constraints.ContentUriTrigger> r10 = r0.getContentUriTriggers().iterator();
    L29:
        if (r10.hasNext() == false) goto L31;
        r122.addTriggerContentUri(convertContentUriTrigger(r10.next()));
        goto L29
    L31:
        r122.setTriggerContentUpdateDelay(r0.getContentTriggerUpdateDelayMillis());
        r122.setTriggerContentMaxDelay(r0.getContentTriggerMaxDelayMillis());
    L32:
        r122.setPersisted(false);
        int r102 = Build.VERSION.SDK_INT;
        if (r102 < 26) goto L36;
        r122.setRequiresBatteryNotLow(r0.requiresBatteryNotLow());
        r122.setRequiresStorageNotLow(r0.requiresStorageNotLow());
    L36:
        if (r11.runAttemptCount <= 0) goto L38;
        boolean r02 = true;
    L40:
        if (r6 <= 0) goto L43;
        r4 = true;
    L43:
        if (r102 < 31) goto L50;
        if (r11.expedited == false) goto L50;
        if (r02 == true) goto L50;
        if (r4 == true) goto L50;
        r122.setExpedited(true);
    L50:
        if (r102 < 35) goto L55;
        String r103 = r11.getTraceTag();
        if (r103 == null) goto L55;
        r122.setTraceTag(r103);
    L55:
        return r122.build();
    L38:
        r02 = false;
        goto L40
    L18:
        if (r6 <= 0) goto L21;
        r122.setMinimumLatency(r6);
        goto L26
    L21:
        if (r11.expedited == true) goto L26;
        if (this.mMarkImportantWhileForeground == false) goto L26;
        r122.setImportantWhileForeground(true);
        goto L26
    L10:
        if (r11.backoffPolicy != BackoffPolicy.LINEAR) goto L12;
        int r14 = 0;
    L13:
        r122.setBackoffCriteria(r11.backoffDelayDuration, r14);
        goto L14
    L12:
        r14 = 1;
    L6:
        setRequiredNetwork(r122, r0.getRequiredNetworkType());
        goto L7
    }
}
