package androidx.media3.session;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.media3.common.Player;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class CommandButton {
    private static final String FIELD_DISPLAY_NAME = null;
    private static final String FIELD_ENABLED = null;
    private static final String FIELD_EXTRAS = null;
    private static final String FIELD_ICON = null;
    private static final String FIELD_ICON_RES_ID = null;
    private static final String FIELD_ICON_URI = null;
    private static final String FIELD_PLAYER_COMMAND = null;
    private static final String FIELD_SESSION_COMMAND = null;
    private static final String FIELD_SLOTS = null;

    static {
        FIELD_SESSION_COMMAND = Util.intToStringMaxRadix(0);
        FIELD_PLAYER_COMMAND = Util.intToStringMaxRadix(1);
        FIELD_ICON_RES_ID = Util.intToStringMaxRadix(2);
        FIELD_DISPLAY_NAME = Util.intToStringMaxRadix(3);
        FIELD_EXTRAS = Util.intToStringMaxRadix(4);
        FIELD_ENABLED = Util.intToStringMaxRadix(5);
        FIELD_ICON_URI = Util.intToStringMaxRadix(6);
        FIELD_ICON = Util.intToStringMaxRadix(7);
        FIELD_SLOTS = Util.intToStringMaxRadix(8);
    }

    public static boolean containsButtonForSlot(List<CommandButton> r1, int r2) {
        if (r1.size() > 0) goto L5;
        return false;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.get(0));
        throw null;
    }

    public static ImmutableList<CommandButton> copyWithUnavailableButtonsDisabled(List<CommandButton> r4, SessionCommands r5, Player.Commands r6) {
        ImmutableList.Builder r0 = new ImmutableList.Builder();
        int r1 = 0;
    L4:
        if (r1 >= r4.size()) goto L10;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r4.get(r1));
        if (isButtonCommandAvailable(null, r5, r6) == false) goto L8;
        r0.add(null);
        r1 = r1 + 1;
        goto L4
    L8:
        throw null;
    L10:
        return r0.build();
    }

    public static ImmutableList<CommandButton> getCustomLayoutFromMediaButtonPreferences(List<CommandButton> r2, boolean r3, boolean r4) {
        if (r2.isEmpty() == false) goto L7;
        return ImmutableList.of();
    L7:
        if (r2.size() > 0) goto L14;
        ImmutableList.Builder r32 = ImmutableList.builder();
        if (r2.size() <= 0) goto L11;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2.get(0));
        throw null;
    L11:
        return r32.build();
    L14:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2.get(0));
        throw null;
    }

    public static boolean isButtonCommandAvailable(CommandButton r0, SessionCommands r1, Player.Commands r2) {
        throw null;
    }
}
