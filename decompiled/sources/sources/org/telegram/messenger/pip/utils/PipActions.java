package org.telegram.messenger.pip.utils;

import android.content.Intent;

/* loaded from: classes5.dex */
public abstract class PipActions {
    public static int getActionId(Intent r2) {
        return r2.getIntExtra("action_id", -1);
    }

    public static String getSourceId(Intent r1) {
        return r1.getStringExtra("source_id");
    }

    public static boolean isPipIntent(Intent r1) {
        return "PIP_CUSTOM_EVENT".equals(r1.getAction());
    }
}
