package androidx.media3.session;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class SessionCommand {
    private static final String FIELD_COMMAND_CODE = null;
    private static final String FIELD_CUSTOM_ACTION = null;
    private static final String FIELD_CUSTOM_EXTRAS = null;
    static final ImmutableList<Integer> LIBRARY_COMMANDS = null;
    static final ImmutableList<Integer> SESSION_COMMANDS = null;
    public final int commandCode;
    public final String customAction;
    public final Bundle customExtras;

    static {
        SESSION_COMMANDS = ImmutableList.of(40010);
        LIBRARY_COMMANDS = ImmutableList.of(50000, 50001, 50002, 50003, 50004, 50005, 50006);
        FIELD_COMMAND_CODE = Util.intToStringMaxRadix(0);
        FIELD_CUSTOM_ACTION = Util.intToStringMaxRadix(1);
        FIELD_CUSTOM_EXTRAS = Util.intToStringMaxRadix(2);
    }

    public SessionCommand(int r3) {
        if (r3 == 0) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0, "commandCode shouldn't be COMMAND_CODE_CUSTOM");
        this.commandCode = r3;
        this.customAction = _UrlKt.FRAGMENT_ENCODE_SET;
        this.customExtras = Bundle.EMPTY;
        return;
    L5:
        r0 = false;
        goto L6
    }

    public SessionCommand(String r2, Bundle r3) {
        this.commandCode = 0;
        this.customAction = (String) Assertions.checkNotNull(r2);
        this.customExtras = new Bundle((Bundle) Assertions.checkNotNull(r3));
    }

    public static SessionCommand fromBundle(Bundle r2) {
        int r0 = r2.getInt(FIELD_COMMAND_CODE, 0);
        if (r0 != 0) goto L5;
        String r02 = (String) Assertions.checkNotNull(r2.getString(FIELD_CUSTOM_ACTION));
        Bundle r22 = r2.getBundle(FIELD_CUSTOM_EXTRAS);
        if (r22 != null) goto L10;
        r22 = Bundle.EMPTY;
    L10:
        return new SessionCommand(r02, r22);
    L5:
        return new SessionCommand(r0);
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof SessionCommand) == true) goto L5;
        return false;
    L5:
        SessionCommand r42 = (SessionCommand) r4;
        if (this.commandCode == r42.commandCode) goto L8;
    L11:
        return false;
    L8:
        if (TextUtils.equals(this.customAction, r42.customAction) == false) goto L11;
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.customAction, Integer.valueOf(this.commandCode)});
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putInt(FIELD_COMMAND_CODE, this.commandCode);
        r0.putString(FIELD_CUSTOM_ACTION, this.customAction);
        r0.putBundle(FIELD_CUSTOM_EXTRAS, this.customExtras);
        return r0;
    }
}
