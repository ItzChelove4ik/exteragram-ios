package androidx.media3.session;

import android.app.PendingIntent;
import android.media.session.MediaSession;
import android.os.Bundle;
import androidx.core.app.BundleCompat;
import androidx.media3.common.Player;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;

/* loaded from: classes4.dex */
class ConnectionState {
    private static final String FIELD_COMMAND_BUTTONS_FOR_MEDIA_ITEMS = null;
    private static final String FIELD_CUSTOM_LAYOUT = null;
    private static final String FIELD_IN_PROCESS_BINDER = null;
    private static final String FIELD_LIBRARY_VERSION = null;
    private static final String FIELD_MEDIA_BUTTON_PREFERENCES = null;
    private static final String FIELD_PLATFORM_TOKEN = null;
    private static final String FIELD_PLAYER_COMMANDS_FROM_PLAYER = null;
    private static final String FIELD_PLAYER_COMMANDS_FROM_SESSION = null;
    private static final String FIELD_PLAYER_INFO = null;
    private static final String FIELD_SESSION_ACTIVITY = null;
    private static final String FIELD_SESSION_BINDER = null;
    private static final String FIELD_SESSION_COMMANDS = null;
    private static final String FIELD_SESSION_EXTRAS = null;
    private static final String FIELD_SESSION_INTERFACE_VERSION = null;
    private static final String FIELD_TOKEN_EXTRAS = null;
    public final ImmutableList<CommandButton> commandButtonsForMediaItems;
    public final ImmutableList<CommandButton> customLayout;
    public final int libraryVersion;
    public final ImmutableList<CommandButton> mediaButtonPreferences;
    public final MediaSession.Token platformToken;
    public final Player.Commands playerCommandsFromPlayer;
    public final Player.Commands playerCommandsFromSession;
    public final PlayerInfo playerInfo;
    public final PendingIntent sessionActivity;
    public final IMediaSession sessionBinder;
    public final SessionCommands sessionCommands;
    public final Bundle sessionExtras;
    public final int sessionInterfaceVersion;
    public final Bundle tokenExtras;

    static {
        FIELD_LIBRARY_VERSION = Util.intToStringMaxRadix(0);
        FIELD_SESSION_BINDER = Util.intToStringMaxRadix(1);
        FIELD_SESSION_ACTIVITY = Util.intToStringMaxRadix(2);
        FIELD_CUSTOM_LAYOUT = Util.intToStringMaxRadix(9);
        FIELD_MEDIA_BUTTON_PREFERENCES = Util.intToStringMaxRadix(14);
        FIELD_COMMAND_BUTTONS_FOR_MEDIA_ITEMS = Util.intToStringMaxRadix(13);
        FIELD_SESSION_COMMANDS = Util.intToStringMaxRadix(3);
        FIELD_PLAYER_COMMANDS_FROM_SESSION = Util.intToStringMaxRadix(4);
        FIELD_PLAYER_COMMANDS_FROM_PLAYER = Util.intToStringMaxRadix(5);
        FIELD_TOKEN_EXTRAS = Util.intToStringMaxRadix(6);
        FIELD_SESSION_EXTRAS = Util.intToStringMaxRadix(11);
        FIELD_PLAYER_INFO = Util.intToStringMaxRadix(7);
        FIELD_SESSION_INTERFACE_VERSION = Util.intToStringMaxRadix(8);
        FIELD_IN_PROCESS_BINDER = Util.intToStringMaxRadix(10);
        FIELD_PLATFORM_TOKEN = Util.intToStringMaxRadix(12);
    }

    public ConnectionState(int r1, int r2, IMediaSession r3, PendingIntent r4, ImmutableList<CommandButton> r5, ImmutableList<CommandButton> r6, ImmutableList<CommandButton> r7, SessionCommands r8, Player.Commands r9, Player.Commands r10, Bundle r11, Bundle r12, PlayerInfo r13, MediaSession.Token r14) {
        this.libraryVersion = r1;
        this.sessionInterfaceVersion = r2;
        this.sessionBinder = r3;
        this.sessionActivity = r4;
        this.customLayout = r5;
        this.mediaButtonPreferences = r6;
        this.commandButtonsForMediaItems = r7;
        this.sessionCommands = r8;
        this.playerCommandsFromSession = r9;
        this.playerCommandsFromPlayer = r10;
        this.tokenExtras = r11;
        this.sessionExtras = r12;
        this.playerInfo = r13;
        this.platformToken = r14;
    }

    public Bundle toBundleForRemoteProcess(int r6) {
        Bundle r0 = new Bundle();
        r0.putInt(FIELD_LIBRARY_VERSION, this.libraryVersion);
        BundleCompat.putBinder(r0, FIELD_SESSION_BINDER, this.sessionBinder.asBinder());
        r0.putParcelable(FIELD_SESSION_ACTIVITY, this.sessionActivity);
        if (this.customLayout.isEmpty() == true) goto L6;
        r0.putParcelableArrayList(FIELD_CUSTOM_LAYOUT, BundleCollectionUtil.toBundleArrayList(this.customLayout, new ConnectionState$$ExternalSyntheticLambda0()));
    L6:
        if (this.mediaButtonPreferences.isEmpty() == true) goto L12;
        ImmutableList<CommandButton> r1 = this.mediaButtonPreferences;
        if (r6 < 7) goto L10;
        r0.putParcelableArrayList(FIELD_MEDIA_BUTTON_PREFERENCES, BundleCollectionUtil.toBundleArrayList(r1, new ConnectionState$$ExternalSyntheticLambda1()));
        goto L12
    L10:
        ImmutableList<CommandButton> r12 = CommandButton.getCustomLayoutFromMediaButtonPreferences(r1, true, true);
        r0.putParcelableArrayList(FIELD_CUSTOM_LAYOUT, BundleCollectionUtil.toBundleArrayList(r12, new ConnectionState$$ExternalSyntheticLambda2()));
    L12:
        if (this.commandButtonsForMediaItems.isEmpty() == true) goto L14;
        r0.putParcelableArrayList(FIELD_COMMAND_BUTTONS_FOR_MEDIA_ITEMS, BundleCollectionUtil.toBundleArrayList(this.commandButtonsForMediaItems, new ConnectionState$$ExternalSyntheticLambda3()));
    L14:
        r0.putBundle(FIELD_SESSION_COMMANDS, this.sessionCommands.toBundle());
        r0.putBundle(FIELD_PLAYER_COMMANDS_FROM_SESSION, this.playerCommandsFromSession.toBundle());
        r0.putBundle(FIELD_PLAYER_COMMANDS_FROM_PLAYER, this.playerCommandsFromPlayer.toBundle());
        r0.putBundle(FIELD_TOKEN_EXTRAS, this.tokenExtras);
        r0.putBundle(FIELD_SESSION_EXTRAS, this.sessionExtras);
        Player.Commands r13 = MediaUtils.intersect(this.playerCommandsFromSession, this.playerCommandsFromPlayer);
        r0.putBundle(FIELD_PLAYER_INFO, this.playerInfo.filterByAvailableCommands(r13, false, false).toBundleForRemoteProcess(r6));
        r0.putInt(FIELD_SESSION_INTERFACE_VERSION, this.sessionInterfaceVersion);
        MediaSession.Token r5 = this.platformToken;
        if (r5 == null) goto L17;
        r0.putParcelable(FIELD_PLATFORM_TOKEN, r5);
    L17:
        return r0;
    }
}
