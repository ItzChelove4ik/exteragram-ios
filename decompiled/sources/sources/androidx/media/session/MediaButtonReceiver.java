package androidx.media.session;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.util.Log;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class MediaButtonReceiver extends BroadcastReceiver {
    public static ComponentName getMediaButtonReceiverComponent(Context r3) {
        Intent r0 = new Intent("android.intent.action.MEDIA_BUTTON");
        r0.setPackage(r3.getPackageName());
        List<ResolveInfo> r32 = r3.getPackageManager().queryBroadcastReceivers(r0, 0);
        if (r32.size() != 1) goto L7;
        ActivityInfo r33 = r32.get(0).activityInfo;
        return new ComponentName(r33.packageName, r33.name);
    L7:
        if (r32.size() <= 1) goto L11;
        Log.w("MediaButtonReceiver", "More than one BroadcastReceiver that handles android.intent.action.MEDIA_BUTTON was found, returning null.");
        return null;
    L11:
        return null;
    }
}
