package androidx.core.app;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes3.dex */
abstract class NotificationCompatJellybean {
    private static final Object sActionsLock = null;
    private static final Object sExtrasLock = null;

    static {
        sExtrasLock = new Object();
        sActionsLock = new Object();
    }

    public static Bundle getBundleForAction(NotificationCompat.Action r4) {
        Bundle r0 = new Bundle();
        IconCompat r1 = r4.getIconCompat();
        if (r1 == null) goto L5;
        int r12 = r1.getResId();
    L6:
        r0.putInt("icon", r12);
        r0.putCharSequence("title", r4.getTitle());
        r0.putParcelable("actionIntent", r4.getActionIntent());
        if (r4.getExtras() == null) goto L9;
        Bundle r13 = new Bundle(r4.getExtras());
    L10:
        r13.putBoolean("android.support.allowGeneratedReplies", r4.getAllowGeneratedReplies());
        r0.putBundle("extras", r13);
        r0.putParcelableArray("remoteInputs", toBundleArray(r4.getRemoteInputs()));
        r0.putBoolean("showsUserInterface", r4.getShowsUserInterface());
        r0.putInt("semanticAction", r4.getSemanticAction());
        return r0;
    L9:
        r13 = new Bundle();
        goto L10
    L5:
        r12 = 0;
        goto L6
    }

    private static Bundle toBundle(RemoteInput r3) {
        Bundle r0 = new Bundle();
        r0.putString("resultKey", r3.getResultKey());
        r0.putCharSequence("label", r3.getLabel());
        r0.putCharSequenceArray("choices", r3.getChoices());
        r0.putBoolean("allowFreeFormInput", r3.getAllowFreeFormInput());
        r0.putBundle("extras", r3.getExtras());
        Set<String> r32 = r3.getAllowedDataTypes();
        if (r32 != null) goto L5;
    L11:
        return r0;
    L5:
        if (r32.isEmpty() == true) goto L11;
        ArrayList<String> r1 = new ArrayList(r32.size());
        Iterator<String> r33 = r32.iterator();
    L8:
        if (r33.hasNext() == false) goto L10;
        r1.add(r33.next());
        goto L8
    L10:
        r0.putStringArrayList("allowedDataTypes", r1);
        goto L11
    }

    private static Bundle[] toBundleArray(RemoteInput[] r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        Bundle[] r0 = new Bundle[r3.length];
        int r1 = 0;
    L7:
        if (r1 >= r3.length) goto L9;
        r0[r1] = toBundle(r3[r1]);
        r1 = r1 + 1;
        goto L7
    L9:
        return r0;
    }
}
