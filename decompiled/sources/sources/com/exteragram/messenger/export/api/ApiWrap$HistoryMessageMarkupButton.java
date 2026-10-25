package com.exteragram.messenger.export.api;

import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.sun.jna.Callback;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class ApiWrap$HistoryMessageMarkupButton extends RecordTag {
    private final int buttonId;
    private final byte[] data;
    private final String forwardText;
    private final String text;
    private final Type type;

    public enum Type extends Enum<Type> {
        private static final /* synthetic */ Type[] $VALUES = null;
        public static final Type Auth = null;
        public static final Type Buy = null;
        public static final Type Callback = null;
        public static final Type CallbackWithPassword = null;
        public static final Type CopyText = null;
        public static final Type Default = null;
        public static final Type Game = null;
        public static final Type RequestLocation = null;
        public static final Type RequestPeer = null;
        public static final Type RequestPhone = null;
        public static final Type RequestPoll = null;
        public static final Type SimpleWebView = null;
        public static final Type SwitchInline = null;
        public static final Type SwitchInlineSame = null;
        public static final Type Url = null;
        public static final Type UserProfile = null;
        public static final Type WebView = null;

        private static /* synthetic */ Type[] $values() {
            return new Type[]{Default, Url, Callback, CallbackWithPassword, RequestPhone, RequestLocation, RequestPoll, RequestPeer, SwitchInline, SwitchInlineSame, Game, Buy, Auth, UserProfile, WebView, SimpleWebView, CopyText};
        }

        static {
            Default = new Type("Default", 0);
            Url = new Type("Url", 1);
            Callback = new Type("Callback", 2);
            CallbackWithPassword = new Type("CallbackWithPassword", 3);
            RequestPhone = new Type("RequestPhone", 4);
            RequestLocation = new Type("RequestLocation", 5);
            RequestPoll = new Type("RequestPoll", 6);
            RequestPeer = new Type("RequestPeer", 7);
            SwitchInline = new Type("SwitchInline", 8);
            SwitchInlineSame = new Type("SwitchInlineSame", 9);
            Game = new Type("Game", 10);
            Buy = new Type("Buy", 11);
            Auth = new Type("Auth", 12);
            UserProfile = new Type("UserProfile", 13);
            WebView = new Type("WebView", 14);
            SimpleWebView = new Type("SimpleWebView", 15);
            CopyText = new Type("CopyText", 16);
            $VALUES = $values();
        }

        Type(String r1, int r2) {
        }

        public static Type valueOf(String r1) {
            return (Type) Enum.valueOf(Type.class, r1);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }
    }

    private /* synthetic */ boolean $record$equals(Object r3) {
        if ((r3 instanceof ApiWrap$HistoryMessageMarkupButton) == false) goto L16;
        ApiWrap$HistoryMessageMarkupButton r32 = (ApiWrap$HistoryMessageMarkupButton) r3;
        if (this.buttonId == r32.buttonId) goto L7;
        return false;
    L7:
        if (Objects.equals(this.type, r32.type) == true) goto L9;
        return false;
    L9:
        if (Objects.equals(this.text, r32.text) == true) goto L11;
        return false;
    L11:
        if (Objects.equals(this.data, r32.data) == true) goto L13;
        return false;
    L13:
        if (Objects.equals(this.forwardText, r32.forwardText) == false) goto L22;
        return true;
    L22:
        return false;
    L16:
        return false;
    }

    private /* synthetic */ Object[] $record$getFieldsAsObjects() {
        return new Object[]{this.type, this.text, this.data, this.forwardText, Integer.valueOf(this.buttonId)};
    }

    public ApiWrap$HistoryMessageMarkupButton(Type r7, String r8) {
        this(r7, r8, null, null, 0);
    }

    public ApiWrap$HistoryMessageMarkupButton(Type r7, String r8, byte[] r9) {
        this(r7, r8, r9, null, 0);
    }

    public ApiWrap$HistoryMessageMarkupButton(Type r1, String r2, byte[] r3, String r4, int r5) {
        this.type = r1;
        this.text = r2;
        this.data = r3;
        this.forwardText = r4;
        this.buttonId = r5;
    }

    public static String TypeToString(ApiWrap$HistoryMessageMarkupButton r1) {
        switch(ApiWrap$1.$SwitchMap$com$exteragram$messenger$export$api$ApiWrap$HistoryMessageMarkupButton$Type[r1.type.ordinal()]) {
            case 1: goto L38;
            case 2: goto L36;
            case 3: goto L34;
            case 4: goto L32;
            case 5: goto L30;
            case 6: goto L28;
            case 7: goto L26;
            case 8: goto L24;
            case 9: goto L22;
            case 10: goto L20;
            case 11: goto L18;
            case 12: goto L16;
            case 13: goto L14;
            case 14: goto L12;
            case 15: goto L10;
            case 16: goto L8;
            case 17: goto L6;
            default: goto L5;
        };
    L6:
        return "copy_text";
    L8:
        return "simple_web_view";
    L10:
        return "web_view";
    L12:
        return "user_profile";
    L14:
        return "auth";
    L16:
        return "buy";
    L18:
        return "game";
    L20:
        return "switch_inline_same";
    L22:
        return "switch_inline";
    L24:
        return "request_peer";
    L26:
        return "request_poll";
    L28:
        return "request_location";
    L30:
        return "request_phone";
    L32:
        return "callback_with_password";
    L34:
        return Callback.METHOD_NAME;
    L36:
        return "url";
    L38:
        return PluginsConstants.Settings.DEFAULT;
    L5:
        throw new IncompatibleClassChangeError();
    }

    public int buttonId() {
        return this.buttonId;
    }

    public byte[] data() {
        return this.data;
    }

    public final boolean equals(Object r1) {
        return $record$equals(r1);
    }

    public String forwardText() {
        return this.forwardText;
    }

    public final int hashCode() {
        return ApiWrap$HistoryMessageMarkupButton$$ExternalSyntheticRecord0.m(this.buttonId, this.type, this.text, this.data, this.forwardText);
    }

    public String text() {
        return this.text;
    }

    public final String toString() {
        return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), ApiWrap$HistoryMessageMarkupButton.class, "type;text;data;forwardText;buttonId");
    }

    public Type type() {
        return this.type;
    }
}
