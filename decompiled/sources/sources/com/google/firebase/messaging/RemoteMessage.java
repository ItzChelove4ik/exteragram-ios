package com.google.firebase.messaging;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.firebase.messaging.Constants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class RemoteMessage extends AbstractSafeParcelable {
    public static final Parcelable.Creator<RemoteMessage> CREATOR = null;
    Bundle bundle;
    private Map<String, String> data;

    static {
        CREATOR = new RemoteMessageCreator();
    }

    public RemoteMessage(Bundle r1) {
        this.bundle = r1;
    }

    public Map<String, String> getData() {
        if (this.data != null) goto L6;
        this.data = Constants.MessagePayloadKeys.extractDeveloperDefinedPayload(this.bundle);
    L6:
        return new HashMap(this.data);
    }

    public String getFrom() {
        return this.bundle.getString("from");
    }

    public long getSentTime() {
        Object r2 = this.bundle.get("google.sent_time");
        if ((r2 instanceof Long) == false) goto L7;
        return ((Long) r2).longValue();
    L7:
        if ((r2 instanceof String) == false) goto L15;
        return Long.parseLong((String) r2);
    L10:
        Log.w("FirebaseMessaging", "Invalid sent time: " + r2);
        return 0;
    L15:
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        RemoteMessageCreator.writeToParcel(this, r1, r2);
    }
}
