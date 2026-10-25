package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.datatransport.runtime.util.PriorityMapping;

/* loaded from: classes4.dex */
public class AlarmManagerSchedulerBroadcastReceiver extends BroadcastReceiver {
    public static /* synthetic */ void $r8$lambda$a3lqwlC30QrIt23aPM9gZcB4sXs() {
    }

    public AlarmManagerSchedulerBroadcastReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r4, Intent r5) {
        String r3 = r5.getData().getQueryParameter("backendName");
        String r0 = r5.getData().getQueryParameter("extras");
        int r1 = Integer.valueOf(r5.getData().getQueryParameter(PluginsConstants.MenuItemProperties.PRIORITY)).intValue();
        int r52 = r5.getExtras().getInt("attemptNumber");
        TransportRuntime.initialize(r4);
        TransportContext.Builder r32 = TransportContext.builder().setBackendName(r3).setPriority(PriorityMapping.valueOf(r1));
        if (r0 == null) goto L5;
        r32.setExtras(Base64.decode(r0, 0));
    L5:
        TransportRuntime.getInstance().getUploader().upload(r32.build(), r52, new AlarmManagerSchedulerBroadcastReceiver$$ExternalSyntheticLambda0());
    }
}
