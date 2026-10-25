package androidx.core.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Iterator;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public final class TaskStackBuilder implements Iterable<Intent> {
    private final ArrayList<Intent> mIntents;
    private final Context mSourceContext;

    public interface SupportParentable {
        Intent getSupportParentActivityIntent();
    }

    private TaskStackBuilder(Context r2) {
        this.mIntents = new ArrayList();
        this.mSourceContext = r2;
    }

    public static TaskStackBuilder create(Context r1) {
        return new TaskStackBuilder(r1);
    }

    public TaskStackBuilder addNextIntent(Intent r2) {
        this.mIntents.add(r2);
        return this;
    }

    public TaskStackBuilder addNextIntentWithParentStack(Intent r2) {
        ComponentName r0 = r2.getComponent();
        if (r0 != null) goto L5;
        r0 = r2.resolveActivity(this.mSourceContext.getPackageManager());
    L5:
        if (r0 == null) goto L7;
        addParentStack(r0);
    L7:
        addNextIntent(r2);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TaskStackBuilder addParentStack(Activity r2) {
        if ((r2 instanceof SupportParentable) == false) goto L5;
        Intent r0 = ((SupportParentable) r2).getSupportParentActivityIntent();
    L6:
        if (r0 != null) goto L8;
        r0 = NavUtils.getParentActivityIntent(r2);
    L8:
        if (r0 == null) goto L13;
        ComponentName r22 = r0.getComponent();
        if (r22 != null) goto L12;
        r22 = r0.resolveActivity(this.mSourceContext.getPackageManager());
    L12:
        addParentStack(r22);
        addNextIntent(r0);
    L13:
        return this;
    L5:
        r0 = null;
        goto L6
    }

    public TaskStackBuilder addParentStack(ComponentName r3) {
        int r0 = this.mIntents.size();
        Intent r32 = NavUtils.getParentActivityIntent(this.mSourceContext, r3);     // Catch: PackageManager.NameNotFoundException -> L8
    L4:
        if (r32 == null) goto L7;
        this.mIntents.add(r0, r32);     // Catch: PackageManager.NameNotFoundException -> L8
        r32 = NavUtils.getParentActivityIntent(this.mSourceContext, r32.getComponent());     // Catch: PackageManager.NameNotFoundException -> L8
        goto L4
    L7:
        return this;
    L8:
        e = move-exception;
        Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public PendingIntent getPendingIntent(int r2, int r3) {
        return getPendingIntent(r2, r3, null);
    }

    public PendingIntent getPendingIntent(int r5, int r6, Bundle r7) {
        if (this.mIntents.isEmpty() == true) goto L6;
        Intent[] r0 = (Intent[]) this.mIntents.toArray(new Intent[0]);
        r0[0] = new Intent(r0[0]).addFlags(268484608);
        return PendingIntent.getActivities(this.mSourceContext, r5, r0, r6, r7);
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("No intents added to TaskStackBuilder; cannot getPendingIntent");
        return null;
    }

    @Override // java.lang.Iterable
    @Deprecated
    public Iterator<Intent> iterator() {
        return this.mIntents.iterator();
    }

    public void startActivities() {
        startActivities(null);
    }

    public void startActivities(Bundle r5) {
        if (this.mIntents.isEmpty() == true) goto L8;
        Intent[] r0 = (Intent[]) this.mIntents.toArray(new Intent[0]);
        r0[0] = new Intent(r0[0]).addFlags(268484608);
        if (ContextCompat.startActivities(this.mSourceContext, r0, r5) == true) goto L10;
        Intent r52 = new Intent(r0[r0.length - 1]);
        r52.addFlags(268435456);
        this.mSourceContext.startActivity(r52);
        return;
    L10:
        return;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("No intents added to TaskStackBuilder; cannot startActivities");
    }
}
