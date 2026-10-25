package com.exteragram.messenger.export.api;

import com.exteragram.messenger.export.api.ApiWrap$HistoryMessageMarkupButton;
import com.exteragram.messenger.export.api.ApiWrap$Reaction;

/* loaded from: classes4.dex */
abstract /* synthetic */ class ApiWrap$1 {
    static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type = null;
    static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$Reaction$Type = null;

    static {
        int[] r0 = new int[ApiWrap$Reaction.Type.values().length];
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$Reaction$Type = r0;
        r0[ApiWrap$Reaction.Type.Empty.ordinal()] = 1;     // Catch: NoSuchFieldError -> L29
    L70:
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$Reaction$Type[ApiWrap$Reaction.Type.Emoji.ordinal()] = 2;     // Catch: NoSuchFieldError -> L30
    L58:
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$Reaction$Type[ApiWrap$Reaction.Type.CustomEmoji.ordinal()] = 3;     // Catch: NoSuchFieldError -> L31
    L68:
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$Reaction$Type[ApiWrap$Reaction.Type.Paid.ordinal()] = 4;     // Catch: NoSuchFieldError -> L32
    L10:
        int[] r4 = new int[ApiWrap$HistoryMessageMarkupButton.Type.values().length];
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type = r4;
        r4[ApiWrap$HistoryMessageMarkupButton.Type.Default.ordinal()] = 1;     // Catch: NoSuchFieldError -> L33
    L52:
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type[ApiWrap$HistoryMessageMarkupButton.Type.Url.ordinal()] = 2;     // Catch: NoSuchFieldError -> L34
    L76:
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type[ApiWrap$HistoryMessageMarkupButton.Type.Callback.ordinal()] = 3;     // Catch: NoSuchFieldError -> L35
    L86:
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type[ApiWrap$HistoryMessageMarkupButton.Type.CallbackWithPassword.ordinal()] = 4;     // Catch: NoSuchFieldError -> L36
    L50:
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type[ApiWrap$HistoryMessageMarkupButton.Type.RequestPhone.ordinal()] = 5;     // Catch: NoSuchFieldError -> L37
    L74:
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type[ApiWrap$HistoryMessageMarkupButton.Type.RequestLocation.ordinal()] = 6;     // Catch: NoSuchFieldError -> L38
    L90:
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type[ApiWrap$HistoryMessageMarkupButton.Type.RequestPoll.ordinal()] = 7;     // Catch: NoSuchFieldError -> L39
    L56:
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type[ApiWrap$HistoryMessageMarkupButton.Type.RequestPeer.ordinal()] = 8;     // Catch: NoSuchFieldError -> L40
    L80:
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type[ApiWrap$HistoryMessageMarkupButton.Type.SwitchInline.ordinal()] = 9;     // Catch: NoSuchFieldError -> L41
    L84:
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type[ApiWrap$HistoryMessageMarkupButton.Type.SwitchInlineSame.ordinal()] = 10;     // Catch: NoSuchFieldError -> L42
    L66:
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type[ApiWrap$HistoryMessageMarkupButton.Type.Game.ordinal()] = 11;     // Catch: NoSuchFieldError -> L43
    L72:
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type[ApiWrap$HistoryMessageMarkupButton.Type.Buy.ordinal()] = 12;     // Catch: NoSuchFieldError -> L44
    L60:
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type[ApiWrap$HistoryMessageMarkupButton.Type.Auth.ordinal()] = 13;     // Catch: NoSuchFieldError -> L45
    L62:
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type[ApiWrap$HistoryMessageMarkupButton.Type.UserProfile.ordinal()] = 14;     // Catch: NoSuchFieldError -> L46
    L88:
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type[ApiWrap$HistoryMessageMarkupButton.Type.WebView.ordinal()] = 15;     // Catch: NoSuchFieldError -> L47
    L54:
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type[ApiWrap$HistoryMessageMarkupButton.Type.SimpleWebView.ordinal()] = 16;     // Catch: NoSuchFieldError -> L48
    L78:
        $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type[ApiWrap$HistoryMessageMarkupButton.Type.CopyText.ordinal()] = 17;     // Catch: NoSuchFieldError -> L49
        return;
    }
}
