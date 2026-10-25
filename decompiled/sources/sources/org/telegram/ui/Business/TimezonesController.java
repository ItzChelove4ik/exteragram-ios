package org.telegram.ui.Business;

import android.content.SharedPreferences;
import android.text.TextUtils;
import j$.time.Instant;
import j$.time.ZoneId;
import j$.time.format.TextStyle;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public class TimezonesController {
    private static volatile TimezonesController[] Instance;
    private static final Object[] lockObjects = null;
    public final int currentAccount;
    private boolean loaded;
    private boolean loading;
    private final ArrayList<TLRPC.TL_timezone> timezones;

    public static /* synthetic */ void $r8$lambda$lZbl1KMXn6igVK4mOPpyuY7xotA(TimezonesController r0, SharedPreferences r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$load$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$sAs_9obtQnCJ2O5YuZq_maAEzWE(TimezonesController r0, TLObject r1, SharedPreferences r2) {
        r0.lambda$load$0(r1, r2);
    }

    static {
        Instance = new TimezonesController[16];
        lockObjects = new Object[16];
        int r1 = 0;
    L3:
        if (r1 >= 16) goto L5;
        lockObjects[r1] = new Object();
        r1 = r1 + 1;
        goto L3
    }

    private TimezonesController(int r2) {
        this.timezones = new ArrayList();
        this.currentAccount = r2;
    }

    public static TimezonesController getInstance(int r3) {
        TimezonesController r0 = Instance[r3];
        if (r0 != null) goto L15;
        Object r1 = lockObjects[r3];
        monitor-enter(r1);
        TimezonesController r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        TimezonesController[] r03 = Instance;     // Catch: Throwable -> L9
        TimezonesController r2 = new TimezonesController(r3);     // Catch: Throwable -> L9
        r03[r3] = r2;     // Catch: Throwable -> L9
        r02 = r2;
    L11:
        monitor-exit(r1);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    L15:
        return r0;
    }

    private /* synthetic */ void lambda$load$0(TLObject r4, SharedPreferences r5) {
        if ((r4 instanceof TLRPC.TL_help_timezonesList) == false) goto L5;
        this.timezones.clear();
        this.timezones.addAll(((TLRPC.TL_help_timezonesList) r4).timezones);
        SerializedData r0 = new SerializedData(r4.getObjectSize());
        r4.serializeToStream(r0);
        r5.edit().putString("timezones", Utilities.bytesToHex(r0.toByteArray())).apply();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.timezonesUpdated, new Object[0]);
    L5:
        this.loaded = true;
        this.loading = false;
    }

    private /* synthetic */ void lambda$load$1(SharedPreferences r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new TimezonesController$$ExternalSyntheticLambda1(this, r2, r1));
    }

    public TLRPC.TL_timezone findTimezone(String r5) {
        if (r5 != null) goto L5;
        return null;
    L5:
        load();
        int r1 = 0;
    L7:
        if (r1 >= this.timezones.size()) goto L12;
        TLRPC.TL_timezone r2 = this.timezones.get(r1);
        if (TextUtils.equals(r2.id, r5) == true) goto L10;
        r1 = r1 + 1;
        goto L7
    L10:
        return r2;
    L12:
        return null;
    }

    public String getSystemTimezoneId() {
        ZoneId r0 = ZoneId.systemDefault();
        if (r0 == null) goto L5;
        String r1 = r0.getId();
    L7:
        if (this.loading == false) goto L9;
    L34:
        load();
        return r1;
    L9:
        if (this.loaded == false) goto L34;
        int r3 = 0;
    L13:
        if (r3 >= this.timezones.size()) goto L18;
        if (TextUtils.equals(this.timezones.get(r3).id, r1) == true) goto L33;
        r3 = r3 + 1;
    L33:
        return r1;
    L18:
        if (r0 == null) goto L20;
        int r02 = r0.getRules().getOffset(Instant.now()).getTotalSeconds();
    L21:
        int r32 = 0;
    L22:
        int r4 = this.timezones.size();
        ArrayList<TLRPC.TL_timezone> r5 = this.timezones;
        if (r32 >= r4) goto L30;
        TLRPC.TL_timezone r42 = r5.get(r32);
        if (r02 == r42.utc_offset) goto L27;
        r32 = r32 + 1;
        goto L22
    L27:
        return r42.id;
    L30:
        if (r5.isEmpty() == true) goto L33;
        return this.timezones.get(0).id;
    L20:
        r02 = 0;
        goto L21
    L5:
        r1 = null;
        goto L7
    }

    public String getTimezoneName(String r5, boolean r6) {
        TLRPC.TL_timezone r0 = findTimezone(r5);
        if (r0 != null) goto L5;
        ZoneId r4 = ZoneId.of(r5);
        String r52 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r4 != null) goto L9;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        if (r6 == false) goto L16;
        String r62 = r4.getRules().getOffset(Instant.now()).getDisplayName(TextStyle.FULL, LocaleController.getInstance().getCurrentLocale());
        String r2 = "GMT";
        if (r62.length() == 1) goto L13;
    L15:
        r2 = "GMT".concat(r62);
    L17:
        StringBuilder r63 = new StringBuilder();
        r63.append(r4.getId().replace("/", ", ").replace("_", " "));
        if (r2 == null) goto L20;
        r52 = ", ".concat(r2);
    L20:
        r63.append(r52);
        return r63.toString();
    L13:
        if (r62.charAt(0) != 'Z') goto L15;
    L16:
        r2 = null;
        goto L17
    L5:
        return getTimezoneName(r0, r6);
    }

    public String getTimezoneName(TLRPC.TL_timezone r2, boolean r3) {
        if (r2 != null) goto L5;
        return null;
    L5:
        String r0 = r2.name;
        if (r3 == true) goto L8;
        return r0;
    L8:
        return r0 + ", " + getTimezoneOffsetName(r2);
    }

    public String getTimezoneOffsetName(TLRPC.TL_timezone r6) {
        int r5 = r6.utc_offset;
        if (r5 == 0) goto L16;
        if (r5 >= 0) goto L6;
        String r52 = "-";
    L7:
        String r53 = "GMT".concat(r52);
        int r62 = Math.abs(r6.utc_offset) / 60;
        int r0 = r62 / 60;
        int r63 = r62 % 60;
        StringBuilder r1 = new StringBuilder();
        r1.append(r53);
        String r54 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r0 >= 10) goto L10;
        String r4 = MVEL.VERSION_SUB;
    L11:
        r1.append(r4);
        r1.append(r0);
        String r02 = r1.toString().concat(":");
        StringBuilder r12 = new StringBuilder();
        r12.append(r02);
        if (r63 >= 10) goto L14;
        r54 = MVEL.VERSION_SUB;
    L14:
        r12.append(r54);
        r12.append(r63);
        return r12.toString();
    L10:
        r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L11
    L6:
        r52 = "+";
        goto L7
    L16:
        return "GMT";
    }

    public ArrayList<TLRPC.TL_timezone> getTimezones() {
        load();
        return this.timezones;
    }

    public void load() {
        if (this.loading == false) goto L5;
        return;
    L5:
        if (this.loaded == true) goto L20;
        this.loading = true;
        SharedPreferences r0 = MessagesController.getInstance(this.currentAccount).getMainSettings();
        TLRPC.help_timezonesList r2 = null;
        String r1 = r0.getString("timezones", null);
        int r3 = 0;
        if (r1 == null) goto L10;
        SerializedData r22 = new SerializedData(Utilities.hexToBytes(r1));
        r2 = TLRPC.help_timezonesList.TLdeserialize(r22, r22.readInt32(false), false);
    L10:
        this.timezones.clear();
        if (r2 == null) goto L13;
        this.timezones.addAll(r2.timezones);
    L13:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.timezonesUpdated, new Object[0]);
        TLRPC.TL_help_getTimezonesList r12 = new TLRPC.TL_help_getTimezonesList();
        if (r2 == null) goto L17;
        r3 = r2.hash;
    L17:
        r12.hash = r3;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r12, new TimezonesController$$ExternalSyntheticLambda0(this, r0));
        return;
    }
}
