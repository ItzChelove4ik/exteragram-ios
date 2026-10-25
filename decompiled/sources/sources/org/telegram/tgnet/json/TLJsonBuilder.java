package org.telegram.tgnet.json;

import java.util.Iterator;
import java.util.List;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;

/* loaded from: classes6.dex */
public class TLJsonBuilder {
    private final JSONObject result2;

    /* loaded from: classes.dex */
    public interface Serializable {
        void serializeToJson(TLJsonBuilder r1);
    }

    private TLJsonBuilder() {
        this.result2 = new JSONObject();
    }

    public static JSONObject serialize(Serializable r2) {
        if (r2 != null) goto L10;
        return null;
    L10:
        TLJsonBuilder r1 = new TLJsonBuilder();     // Catch: Exception -> L7
        r2.serializeToJson(r1);     // Catch: Exception -> L7
        return r1.result2;
    L7:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private void write(String r1, Object r2) {
        this.result2.putOpt(r1, r2);     // Catch: JSONException -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L8;
        return;
    L8:
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }

    public void writeBoolean(String r1, boolean r2) {
        write(r1, Boolean.valueOf(r2));
    }

    public void writeInt32(String r2, int r3) {
        writeString(r2, Integer.toString(r3, 10));
    }

    public void writeInt64(String r2, long r3) {
        writeString(r2, Long.toString(r3, 10));
    }

    public void writeObject(String r1, Serializable r2) {
        write(r1, serialize(r2));
    }

    public void writeString(String r1, String r2) {
        write(r1, r2);
    }

    public <T extends Serializable> void writeVector(String r3, List<T> r4) {
        JSONArray r0 = new JSONArray();
        Iterator<T> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L8;
        JSONObject r1 = serialize(r42.next());
        if (r1 == null) goto L4;
        r0.put(r1);
        goto L4
    L8:
        write(r3, r0);
    }
}
