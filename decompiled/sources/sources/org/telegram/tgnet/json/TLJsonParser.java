package org.telegram.tgnet.json;

import java.util.ArrayList;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.TranscribeButton;

/* loaded from: classes6.dex */
public class TLJsonParser {
    private final JSONObject jsonObject;

    /* loaded from: classes.dex */
    public interface Deserializable {
        void deserializeFromJson(TLJsonParser r1);
    }

    public TLJsonParser(JSONObject r1) {
        this.jsonObject = r1;
    }

    private static <T extends Deserializable> T parse(TLJsonParser r0, Utilities.CallbackReturn<TLJsonParser, T> r1) {
        return r1.run(r0);
    L4:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private boolean parseBoolean(Object r1, boolean r2) {
    L6:
        e = move-exception;
        FileLog.e(e);
        return r2;
    L3:
        if ((r1 instanceof Boolean) == false) goto L9;
        return ((Boolean) r1).booleanValue();
    L9:
        if ((r1 instanceof String) == true) goto L11;
        return r2;
    L11:
        return Boolean.parseBoolean((String) r1);
    }

    private int parseInt32(Object r1, int r2) {
    L6:
        e = move-exception;
        FileLog.e(e);
        return r2;
    L3:
        if ((r1 instanceof Number) == false) goto L9;
        return ((Number) r1).intValue();
    L9:
        if ((r1 instanceof String) == true) goto L11;
        return r2;
    L11:
        return Integer.parseInt((String) r1, 10);
    }

    private long parseInt64(Object r1, long r2) {
    L6:
        e = move-exception;
        FileLog.e(e);
        return r2;
    L3:
        if ((r1 instanceof Number) == false) goto L9;
        return ((Number) r1).intValue();
    L9:
        if ((r1 instanceof String) == true) goto L11;
        return r2;
    L11:
        return Long.parseLong((String) r1, 10);
    }

    private String parseString(Object r1, String r2) {
        if ((r1 instanceof String) == true) goto L5;
        return r2;
    L5:
        return (String) r1;
    }

    public boolean readBoolean(String r2, boolean r3) {
        return parseBoolean(this.jsonObject.opt(r2), r3);
    }

    public int readInt32(String r2, int r3) {
        return parseInt32(this.jsonObject.opt(r2), r3);
    }

    public long readInt64(String r3, int r4) {
        return parseInt64(this.jsonObject.opt(r3), r4);
    }

    public <T extends Deserializable> T readObject(String r1, Utilities.CallbackReturn<TLJsonParser, T> r2) {
        JSONObject r0 = this.jsonObject.optJSONObject(r1);
        if (r0 != null) goto L5;
        return null;
    L5:
        return (T) parse(new TLJsonParser(r0), r2);
    }

    public String readString(String r2) {
        return readString(r2, null);
    }

    public String readString(String r2, String r3) {
        return parseString(this.jsonObject.opt(r2), r3);
    }

    public <T extends Deserializable> ArrayList<T> readVector(String r5, Utilities.CallbackReturn<TLJsonParser, T> r6) {
        TranscribeButton.AnonymousClass1 r0 = (ArrayList<T>) new ArrayList();
        JSONArray r4 = this.jsonObject.optJSONArray(r5);
        if (r4 == null) goto L16;
        int r52 = r4.length();
        int r1 = 0;
    L5:
        if (r1 >= r52) goto L16;
        Deserializable r2 = parse(new TLJsonParser(r4.getJSONObject(r1)), r6);     // Catch: JSONException -> L10
        if (r2 == null) goto L13;
        r0.add(r2);     // Catch: JSONException -> L10
    L13:
        r1 = r1 + 1;
    L10:
        e = move-exception;
        FileLog.e(e);
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L13;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L16:
        return r0;
    }
}
