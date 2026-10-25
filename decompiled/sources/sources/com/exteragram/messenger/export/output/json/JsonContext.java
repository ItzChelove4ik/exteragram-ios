package com.exteragram.messenger.export.output.json;

import android.util.Base64;
import android.util.Pair;
import com.android.dx.dex.code.CstInsn$$ExternalSyntheticBUOutline0;
import com.exteragram.messenger.export.api.ApiWrap$ActionChatEditPhoto;
import com.exteragram.messenger.export.api.ApiWrap$ActionSuggestProfilePhoto;
import com.exteragram.messenger.export.api.ApiWrap$DialogInfo;
import com.exteragram.messenger.export.api.ApiWrap$Document;
import com.exteragram.messenger.export.api.ApiWrap$File;
import com.exteragram.messenger.export.api.ApiWrap$Game;
import com.exteragram.messenger.export.api.ApiWrap$GeoPoint;
import com.exteragram.messenger.export.api.ApiWrap$GiveawayResults;
import com.exteragram.messenger.export.api.ApiWrap$GiveawayStart;
import com.exteragram.messenger.export.api.ApiWrap$HistoryMessageMarkupButton;
import com.exteragram.messenger.export.api.ApiWrap$Image;
import com.exteragram.messenger.export.api.ApiWrap$Invoice;
import com.exteragram.messenger.export.api.ApiWrap$Media;
import com.exteragram.messenger.export.api.ApiWrap$Message;
import com.exteragram.messenger.export.api.ApiWrap$PaidMedia;
import com.exteragram.messenger.export.api.ApiWrap$Peer;
import com.exteragram.messenger.export.api.ApiWrap$Poll;
import com.exteragram.messenger.export.api.ApiWrap$Reaction;
import com.exteragram.messenger.export.api.ApiWrap$SharedContact;
import com.exteragram.messenger.export.api.ApiWrap$TextPart;
import com.exteragram.messenger.export.api.ApiWrap$UnsupportedMedia;
import com.exteragram.messenger.export.api.ApiWrap$User;
import com.exteragram.messenger.export.api.ApiWrap$Venue;
import com.exteragram.messenger.export.api.DataTypesUtils;
import com.exteragram.messenger.export.output.AbstractWriter;
import com.exteragram.messenger.export.output.OutputFile;
import com.exteragram.messenger.export.output.html.HtmlContext$$ExternalSyntheticBackport1;
import com.exteragram.messenger.export.output.html.HtmlWriter;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public class JsonContext {
    public boolean _currentNestingHadItem;
    protected OutputFile _file;
    public ArrayList<Boolean> nesting;

    /* renamed from: com.exteragram.messenger.export.output.json.JsonContext$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason = null;
        static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$Reaction$Type = null;
        static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type = null;

        static {
            int[] r0 = new int[ApiWrap$Reaction.Type.values().length];
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$Reaction$Type = r0;
            r0[ApiWrap$Reaction.Type.Emoji.ordinal()] = 1;     // Catch: NoSuchFieldError -> L36
        L77:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$Reaction$Type[ApiWrap$Reaction.Type.CustomEmoji.ordinal()] = 2;     // Catch: NoSuchFieldError -> L37
        L6:
            int[] r2 = new int[ApiWrap$File.SkipReason.values().length];
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason = r2;
            r2[ApiWrap$File.SkipReason.Unavailable.ordinal()] = 1;     // Catch: NoSuchFieldError -> L38
        L107:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[ApiWrap$File.SkipReason.FileSize.ordinal()] = 2;     // Catch: NoSuchFieldError -> L39
        L115:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[ApiWrap$File.SkipReason.FileType.ordinal()] = 3;     // Catch: NoSuchFieldError -> L40
        L65:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[ApiWrap$File.SkipReason.None.ordinal()] = 4;     // Catch: NoSuchFieldError -> L41
        L13:
            int[] r4 = new int[ApiWrap$TextPart.Type.values().length];
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type = r4;
            r4[ApiWrap$TextPart.Type.Unknown.ordinal()] = 1;     // Catch: NoSuchFieldError -> L42
        L93:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Mention.ordinal()] = 2;     // Catch: NoSuchFieldError -> L43
        L101:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Hashtag.ordinal()] = 3;     // Catch: NoSuchFieldError -> L44
        L103:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.BotCommand.ordinal()] = 4;     // Catch: NoSuchFieldError -> L45
        L67:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Url.ordinal()] = 5;     // Catch: NoSuchFieldError -> L46
        L75:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Email.ordinal()] = 6;     // Catch: NoSuchFieldError -> L47
        L79:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Bold.ordinal()] = 7;     // Catch: NoSuchFieldError -> L48
        L95:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Italic.ordinal()] = 8;     // Catch: NoSuchFieldError -> L49
        L97:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Code.ordinal()] = 9;     // Catch: NoSuchFieldError -> L50
        L113:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Pre.ordinal()] = 10;     // Catch: NoSuchFieldError -> L51
        L63:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Text.ordinal()] = 11;     // Catch: NoSuchFieldError -> L52
        L83:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.TextUrl.ordinal()] = 12;     // Catch: NoSuchFieldError -> L53
        L85:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.MentionName.ordinal()] = 13;     // Catch: NoSuchFieldError -> L54
        L99:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Phone.ordinal()] = 14;     // Catch: NoSuchFieldError -> L55
        L105:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Cashtag.ordinal()] = 15;     // Catch: NoSuchFieldError -> L56
        L69:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Underline.ordinal()] = 16;     // Catch: NoSuchFieldError -> L57
        L71:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Strike.ordinal()] = 17;     // Catch: NoSuchFieldError -> L58
        L87:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Blockquote.ordinal()] = 18;     // Catch: NoSuchFieldError -> L59
        L89:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.BankCard.ordinal()] = 19;     // Catch: NoSuchFieldError -> L60
        L109:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Spoiler.ordinal()] = 20;     // Catch: NoSuchFieldError -> L61
        L111:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.CustomEmoji.ordinal()] = 21;     // Catch: NoSuchFieldError -> L62
            return;
        }
    }

    /* renamed from: $r8$lambda$0G3A-K7RwrqIoB5h6-5Np1ClK9s, reason: not valid java name */
    public static /* synthetic */ String m1349$r8$lambda$0G3AK7RwrqIoB5h65Np1ClK9s(Long r4) {
        if (r4.longValue() >= 0) goto L7;
        return SerializeString("chat" + r4);
    L7:
        return SerializeString("user" + r4);
    }

    /* renamed from: $r8$lambda$0JEATGwgrDcrUlHLn-U4tlxdjgY, reason: not valid java name */
    public static /* synthetic */ void m1350$r8$lambda$0JEATGwgrDcrUlHLnU4tlxdjgY(Utilities.Callback3 r3, Utilities.Callback2 r4, ApiWrap$Image r5) {
        r3.run(r5.file, "photo", _UrlKt.FRAGMENT_ENCODE_SET);
        int r32 = r5.width;
        if (r32 != 0) goto L5;
        return;
    L5:
        if (r5.height == 0) goto L9;
        r4.run("width", Integer.valueOf(r32));
        r4.run("height", Integer.valueOf(r5.height));
        return;
    }

    public static /* synthetic */ String $r8$lambda$5FFhJI72fHDyOjZRMyMtJGXGYXw(JsonContext r2, ArrayList r3) {
        r2.nesting.remove(r0.size() - 1);
        return SerializeObject(r2, (Pair[]) r3.toArray(new Pair[r3.size()]));
    }

    /* renamed from: $r8$lambda$5ZTMoi45tFE-PX66k7pRMwqlwpI, reason: not valid java name */
    public static /* synthetic */ void m1351$r8$lambda$5ZTMoi45tFEPX66k7pRMwqlwpI(Utilities.Callback2 r2, ApiWrap$File r3, String r4, String r5) {
        if (r5.isEmpty() == false) goto L5;
        String r52 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        int r0 = AnonymousClass1.$SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[r3.skipReason.ordinal()];
        if (r0 != 1) goto L9;
        String r53 = r52.concat("(File unavailable, please try again later)");
    L16:
        if (r53 == null) goto L19;
        r2.run("skipReason", r53);
        r2.run("size", Long.valueOf(r3.size));
        return;
    L19:
        r2.run(r4, r3.relativePath);
        return;
    L9:
        if (r0 != 2) goto L11;
        r53 = r52.concat("(File exceeds maximum size. Change data exporting settings to download.)");
        goto L16
    L11:
        if (r0 == 3) goto L13;
        r53 = null;
        goto L16
    L13:
        r53 = r52.concat("(File not included. Change data exporting settings to download.)");
        goto L16
    L5:
        r52 = r5.concat(" ");
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$6HhGPqLT_zAglGl3BSE0SMSNzeo(ApiWrap$Message r4, Utilities.Callback2 r5, String r6) {
        int r0 = r4.replyToMsgId;
        if (r0 == 0) goto L8;
        r5.run(r6, Integer.valueOf(r0));
        long r02 = r4.replyToPeerId;
        if (r02 == 0) goto L9;
        r5.run("reply_to_peer_id", Long.valueOf(r02));
        return;
    L9:
        return;
    }

    public static /* synthetic */ String $r8$lambda$9xgHZCRbTeA9otd5aGbz2yjq7AM(Long r2) {
        return DataTypesUtils.NumberToString(r2.longValue());
    }

    /* renamed from: $r8$lambda$A7SAhk-qqvPMMCDUVzfseNfek48, reason: not valid java name */
    public static /* synthetic */ ApiWrap$Peer m1352$r8$lambda$A7SAhkqqvPMMCDUVzfseNfek48(HashMap r0, Long r1) {
        ApiWrap$Peer r02 = (ApiWrap$Peer) r0.get(r1);
        if (r02 == null) goto L6;
        return r02;
    L6:
        return new ApiWrap$Peer(new ApiWrap$User());
    }

    public static /* synthetic */ ApiWrap$User $r8$lambda$D7Q2WtzEt_GXKZraWikmhxU1gTk(Utilities.CallbackReturn r0, Long r1) {
        ApiWrap$User r02 = ((ApiWrap$Peer) r0.run(r1)).user;
        if (r02 == null) goto L6;
        return r02;
    L6:
        return new ApiWrap$User();
    }

    public static /* synthetic */ void $r8$lambda$DJcpDpppJ9SASMoVONnleTMyrRE(ArrayList r1, String r2, String r3) {
        if (r3.isEmpty() == true) goto L6;
        r1.add(new Pair(r2, r3));
        return;
    }

    public static /* synthetic */ String $r8$lambda$DZvvVfxINNbQgeVwZHbS0Lp0an4(JsonContext r4, ApiWrap$Poll.Answer r5) {
        r4.nesting.add(Boolean.FALSE);
        Pair r0 = new Pair("text", SerializeString(r5.text()));
        Pair r1 = new Pair("voters", DataTypesUtils.NumberToString(r5.votes()));
        if (r5.my() == false) goto L5;
        String r52 = "true";
    L6:
        String r53 = SerializeObject(r4, new Pair[]{r0, r1, new Pair("chosen", r52)});
        r4.nesting.remove(r4.size() - 1);
        return r53;
    L5:
        r52 = "false";
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$E9u9TYVlppS2vBxijZACzBTyoIE(Utilities.Callback2 r1, Utilities.CallbackReturn r2, String r3, Object r4) {
        if ((r4 instanceof Boolean) == false) goto L7;
        r1.run(r3, String.valueOf((Boolean) r4));
        return;
    L7:
        if ((r4 instanceof Integer) == false) goto L11;
        r1.run(r3, String.valueOf((Integer) r4));
        return;
    L11:
        if ((r4 instanceof Long) == false) goto L15;
        r1.run(r3, (String) r2.run((Long) r4));
        return;
    L15:
        if ((r4 instanceof String) == false) goto L20;
        String r42 = (String) r4;
        if (r42.isEmpty() == true) goto L21;
        r1.run(r3, SerializeString(r42));
        return;
    L21:
        return;
    }

    public static /* synthetic */ String $r8$lambda$EU5TyjJPYR1l70T5AEeziC4uHjA(Utilities.CallbackReturn r0, Long r1) {
        return StringAllowNull(((ApiWrap$Peer) r0.run(r1)).name());
    }

    /* renamed from: $r8$lambda$EaXFtMVkSG8-FH9mjKoB3SYeH3o, reason: not valid java name */
    public static /* synthetic */ void m1353$r8$lambda$EaXFtMVkSG8FH9mjKoB3SYeH3o(Utilities.Callback2 r1, String r2) {
        r1.run("action", r2);
    }

    public static /* synthetic */ ApiWrap$Peer $r8$lambda$HcPXe2yaCyXRahCOHGZF0WKJQiM(HashMap r0, Long r1) {
        ApiWrap$Peer r02 = (ApiWrap$Peer) r0.get(r1);
        if (r02 == null) goto L6;
        return r02;
    L6:
        return new ApiWrap$Peer(new ApiWrap$User());
    }

    public static /* synthetic */ void $r8$lambda$ITtAik57S_FX3F_idDa0ObsjTEY(Utilities.Callback2 r2, Utilities.CallbackReturn r3, String r4, Object r5) {
        if ((r5 instanceof Boolean) == false) goto L7;
        r2.run(r4, String.valueOf((Boolean) r5));
        return;
    L7:
        if ((r5 instanceof Integer) == false) goto L11;
        r2.run(r4, String.valueOf((Integer) r5));
        return;
    L11:
        if ((r5 instanceof Long) == false) goto L15;
        r2.run(r4, String.valueOf((Long) r5));
        return;
    L15:
        if ((r5 instanceof String) == false) goto L21;
        String r0 = (String) r5;
        if (r0.isEmpty() == true) goto L21;
        r2.run(r4, SerializeString(r0));
        return;
    L21:
        if ((r5 instanceof TLRPC.Peer) == false) goto L24;
        r2.run(r4, (String) r3.run(Long.valueOf(MessageObject.getPeerId((TLRPC.Peer) r5))));
        return;
    }

    public static /* synthetic */ String $r8$lambda$JSGl0gcx13Gm7REM9tQp0tkWgrM(JsonContext r1, Utilities.CallbackReturn r2, Utilities.CallbackReturn r3, ApiWrap$Reaction r4) {
        r1.nesting.add(Boolean.TRUE);
        new ArrayList().add(new Pair("type", SerializeString(ApiWrap$Reaction.TypeToString(r4))));     // Catch: Throwable -> L5
        throw null;     // Catch: Throwable -> L5
    L5:
        th = move-exception;
        r1.nesting.remove(r1.size() - 1);
        throw th;
    }

    public static /* synthetic */ void $r8$lambda$LPKh2UoSVjmlAI8IwxLCjW6wJuA(Utilities.Callback3 r3, Utilities.Callback2 r4, ApiWrap$Image r5) {
        r3.run(r5.file, "photo", _UrlKt.FRAGMENT_ENCODE_SET);
        r4.run("size", Long.valueOf(r5.file.size));
        int r32 = r5.width;
        if (r32 != 0) goto L5;
        return;
    L5:
        if (r5.height == 0) goto L9;
        r4.run("width", Integer.valueOf(r32));
        r4.run("height", Integer.valueOf(r5.height));
        return;
    }

    /* renamed from: $r8$lambda$Lb75cXPDJ068NTmfGbGV7wsa-yQ, reason: not valid java name */
    public static /* synthetic */ ApiWrap$User m1354$r8$lambda$Lb75cXPDJ068NTmfGbGV7wsayQ(Utilities.CallbackReturn r0, Long r1) {
        ApiWrap$User r02 = ((ApiWrap$Peer) r0.run(r1)).user;
        if (r02 == null) goto L6;
        return r02;
    L6:
        return new ApiWrap$User();
    }

    public static /* synthetic */ void $r8$lambda$V1GPUI3Ji8IitWs016dE88zBqRg(ApiWrap$Message r4, Utilities.Callback2 r5, String r6) {
        int r0 = r4.replyToMsgId;
        if (r0 == 0) goto L8;
        r5.run(r6, Integer.valueOf(r0));
        long r02 = r4.replyToPeerId;
        if (r02 == 0) goto L9;
        r5.run("reply_to_peer_id", Long.valueOf(r02));
        return;
    L9:
        return;
    }

    public static /* synthetic */ void $r8$lambda$Yw3sshGVvjPkXNdtDtvTvKbj2BM(ArrayList r1, String r2, String r3) {
        if (r3.isEmpty() == true) goto L6;
        r1.add(new Pair(r2, r3));
        return;
    }

    public static /* synthetic */ ApiWrap$Peer $r8$lambda$_82XuOS42idlN6YRygYUGxZazmU(HashMap r0, Long r1) {
        ApiWrap$Peer r02 = (ApiWrap$Peer) r0.get(r1);
        if (r02 == null) goto L6;
        return r02;
    L6:
        return new ApiWrap$Peer(new ApiWrap$User());
    }

    public static /* synthetic */ void $r8$lambda$fua3ETEVNn_xpfDnppx9CUbOjao(ArrayList r1, String r2, String r3) {
        if (r3.isEmpty() == true) goto L6;
        r1.add(new Pair(r2, r3));
        return;
    }

    /* renamed from: $r8$lambda$hmtII8_x5IKMyy-qN62IoDyQTf4, reason: not valid java name */
    public static /* synthetic */ String m1355$r8$lambda$hmtII8_x5IKMyyqN62IoDyQTf4(Utilities.CallbackReturn r0, ArrayList r1) {
        return (String) r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$hw2aHHYioc0DYTvaVxxcyJjnUJs(ApiWrap$Message r4, Utilities.Callback2 r5, Utilities.CallbackReturn r6, Utilities.Callback2 r7, String r8) {
        if (r8 != null) goto L4;
        r8 = "from";
    L4:
        long r0 = r4.fromId;
        if (r0 == 0) goto L8;
        r5.run(r8, (String) r6.run(Long.valueOf(r0)));
        TLRPC.TL_peerUser r52 = new TLRPC.TL_peerUser();
        r52.user_id = r4.fromId;
        r7.run(r8.concat("_id"), r52);
        return;
    }

    public static /* synthetic */ void $r8$lambda$j7sKj_P7pujVBJlz8XEZE23lUvo(Utilities.Callback2 r3, ApiWrap$File r4, String r5, String r6) {
        boolean r0 = r6.isEmpty();
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r0 == false) goto L5;
        String r62 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        int r02 = AnonymousClass1.$SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[r4.skipReason.ordinal()];
        if (r02 != 1) goto L9;
        r1 = r62.concat("(File unavailable, please try again later)");
    L19:
        r3.run(r5, r1);
        return;
    L9:
        if (r02 != 2) goto L11;
        r1 = r62.concat("(File exceeds maximum size. Change data exporting settings to download.)");
        goto L19
    L11:
        if (r02 != 3) goto L13;
        r1 = r62.concat("(File not included. Change data exporting settings to download.)");
        goto L19
    L13:
        if (r02 != 4) goto L19;
        r1 = r4.relativePath;
        goto L19
    L5:
        r62 = r6.concat(" ");
        goto L6
    }

    /* renamed from: $r8$lambda$l3kw5Q-6evJjim0J_zsiqvwknVk, reason: not valid java name */
    public static /* synthetic */ ApiWrap$User m1356$r8$lambda$l3kw5Q6evJjim0J_zsiqvwknVk(Utilities.CallbackReturn r0, Long r1) {
        ApiWrap$User r02 = ((ApiWrap$Peer) r0.run(r1)).user;
        if (r02 == null) goto L6;
        return r02;
    L6:
        return new ApiWrap$User();
    }

    public static /* synthetic */ String $r8$lambda$lKrxcF5Xyg1WzKTSgzvku1TscPg(JsonContext r2, ArrayList r3) {
        r2.nesting.add(Boolean.FALSE);
        List r32 = (List) r3.stream().map(new JsonContext$$ExternalSyntheticLambda13(r2)).collect(Collectors.toList());
        r2.nesting.remove(r0.size() - 1);
        return SerializeArray(r2, new ArrayList(r32));
    }

    public static /* synthetic */ String $r8$lambda$nuaLfrTKf9UaHQJH7HeIdHHHYPs(Long r4) {
        if (r4.longValue() >= 0) goto L7;
        return SerializeString("chat" + r4);
    L7:
        return SerializeString("user" + r4);
    }

    /* renamed from: $r8$lambda$oOTIAQU7H7Sd32rHCZyIkJm-95M, reason: not valid java name */
    public static /* synthetic */ String m1357$r8$lambda$oOTIAQU7H7Sd32rHCZyIkJm95M(Utilities.CallbackReturn r0, Long r1) {
        return StringAllowNull(((ApiWrap$Peer) r0.run(r1)).name());
    }

    public static /* synthetic */ void $r8$lambda$oOh1TbUsZyqtWEJs5VUnyheqEho(ApiWrap$Message r4, Utilities.Callback2 r5, Utilities.CallbackReturn r6, Utilities.Callback2 r7, String r8) {
        if (r8 != null) goto L4;
        r8 = "from";
    L4:
        long r0 = r4.fromId;
        if (r0 == 0) goto L8;
        r5.run(r8, (String) r6.run(Long.valueOf(r0)));
        r7.run(r8.concat("_id"), Long.valueOf(r4.fromId));
        return;
    }

    public static /* synthetic */ void $r8$lambda$pU0v_mdGOesT5IrtmWsJdviQKos(Utilities.Callback2 r2, Utilities.CallbackReturn r3, String r4, Object r5) {
        if ((r5 instanceof Boolean) == false) goto L7;
        r2.run(r4, String.valueOf((Boolean) r5));
        return;
    L7:
        if ((r5 instanceof Integer) == false) goto L11;
        r2.run(r4, String.valueOf((Integer) r5));
        return;
    L11:
        if ((r5 instanceof Long) == false) goto L15;
        r2.run(r4, String.valueOf((Long) r5));
        return;
    L15:
        if ((r5 instanceof String) == false) goto L21;
        String r0 = (String) r5;
        if (r0.isEmpty() == true) goto L21;
        r2.run(r4, SerializeString(r0));
        return;
    L21:
        if ((r5 instanceof TLRPC.Peer) == false) goto L24;
        r2.run(r4, (String) r3.run(Long.valueOf(MessageObject.getPeerId((TLRPC.Peer) r5))));
        return;
    }

    public static /* synthetic */ String $r8$lambda$pklfCh8IPhV8pOoKh1sadePkqzY(Utilities.CallbackReturn r0, ApiWrap$Reaction r1) {
        return (String) r0.run(r1);
    }

    /* renamed from: $r8$lambda$qketphKXD6umlaSqbPSIj-rbZ8c, reason: not valid java name */
    public static /* synthetic */ void m1358$r8$lambda$qketphKXD6umlaSqbPSIjrbZ8c(Utilities.CallbackReturn r4, Utilities.Callback2 r5, JsonContext r6, ArrayList r7, String r8) {
        ArrayList r0 = new ArrayList();
        int r1 = r7.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        Object r3 = r7.get(r2);
        r2 = r2 + 1;
        r0.add(StringAllowNull(((ApiWrap$User) r4.run((Long) r3)).name()));
        goto L3
    L5:
        r5.run(r8, SerializeArray(r6, r0));
    }

    public static /* synthetic */ String $r8$lambda$zTj1WpUulSBk65CIRhljeFYUJSk(Long r4) {
        if (r4.longValue() >= 0) goto L7;
        return SerializeString("chat" + r4);
    L7:
        return SerializeString("user" + r4);
    }

    /* renamed from: $r8$lambda$z_7ew4t-ZrjahCzTlJ65jxaK0v0, reason: not valid java name */
    public static /* synthetic */ String m1359$r8$lambda$z_7ew4tZrjahCzTlJ65jxaK0v0(JsonContext r5, ApiWrap$HistoryMessageMarkupButton r6) {
        ArrayList r0 = new ArrayList();
        r0.add(new Pair("type", SerializeString(ApiWrap$HistoryMessageMarkupButton.TypeToString(r6))));
        if (r6.text().isEmpty() == true) goto L6;
        r0.add(new Pair("text", SerializeString(r6.text())));
    L6:
        if (r6.data() == null) goto L17;
        if (r6.data().length == 0) goto L17;
        if (r6.type() != ApiWrap$HistoryMessageMarkupButton.Type.Callback) goto L12;
    L15:
        r0.add(new Pair("dataBase64", SerializeString(ChatUtils.getInstance().getTextFromCallback(r6.data()))));
        r0.add(new Pair("data", SerializeString(_UrlKt.FRAGMENT_ENCODE_SET)));
        goto L17
    L12:
        if (r6.type() == ApiWrap$HistoryMessageMarkupButton.Type.CallbackWithPassword) goto L15;
        r0.add(new Pair("data", SerializeString(ChatUtils.getInstance().getTextFromCallback(r6.data()))));
    L17:
        if (r6.forwardText() == null) goto L22;
        if (r6.forwardText().isEmpty() == true) goto L22;
        r0.add(new Pair("forward_text", SerializeString(r6.forwardText())));
    L22:
        if (r6.buttonId() == 0) goto L25;
        r0.add(new Pair("button_id", DataTypesUtils.NumberToString(r6.buttonId())));
    L25:
        return SerializeObject(r5, (Pair[]) r0.toArray(new Pair[r0.size()]));
    }

    public JsonContext(OutputFile r2) {
        this._currentNestingHadItem = false;
        this.nesting = new ArrayList();
        this._file = r2;
    }

    public static String Indentation(int r1) {
        return HtmlContext$$ExternalSyntheticBackport1.m(" ", r1);
    }

    public static String Indentation(JsonContext r0) {
        return Indentation(r0.nesting.size());
    }

    public static String SerializeArray(JsonContext r8, ArrayList<String> r9) {
        String r0 = Indentation(r8.nesting.size());
        StringBuilder r1 = new StringBuilder("\n");
        boolean r2 = true;
        r1.append(Indentation(r8.nesting.size() + 1));
        String r82 = r1.toString();
        StringBuilder r12 = new StringBuilder("[");
        int r3 = r9.size();
        int r5 = 0;
    L3:
        if (r5 >= r3) goto L9;
        String r6 = r9.get(r5);
        r5 = r5 + 1;
        String r62 = r6;
        if (r2 == false) goto L7;
        r2 = false;
    L8:
        r12.append(r82);
        r12.append(r62);
        goto L3
    L7:
        r12.append(',');
        goto L8
    L9:
        r12.append('\n');
        r12.append(r0);
        r12.append("]");
        return r12.toString();
    }

    public static String SerializeMessage(JsonContext r11, ApiWrap$Message r12, HashMap<Long, ApiWrap$Peer> r13, String r14) {
        if ((r12.media.content instanceof ApiWrap$UnsupportedMedia) == true) goto L5;
        JsonContext$$ExternalSyntheticLambda0 r0 = new JsonContext$$ExternalSyntheticLambda0(r13);
        JsonContext$$ExternalSyntheticLambda4 r3 = new JsonContext$$ExternalSyntheticLambda4(r0);
        ArrayList r4 = new ArrayList();
        r4.add(new Pair("id", Integer.valueOf(r12.id)));
        if (r12.action == null) goto L9;
        String r5 = "service";
    L10:
        r4.add(new Pair("type", SerializeString(r5)));
        r4.add(new Pair("date", SerializeString(String.valueOf(r12.date))));
        r11.nesting.add(Boolean.TRUE);
        JsonContext$$ExternalSyntheticLambda5 r1 = new JsonContext$$ExternalSyntheticLambda5(r11, r4);
        JsonContext$$ExternalSyntheticLambda6 r2 = new JsonContext$$ExternalSyntheticLambda6(r4);
        int r52 = r12.edited;
        if (r52 == 0) goto L13;
        r2.run("edited", String.valueOf(r52));
    L13:
        JsonContext$$ExternalSyntheticLambda7 r53 = new JsonContext$$ExternalSyntheticLambda7();
        JsonContext$$ExternalSyntheticLambda8 r6 = new JsonContext$$ExternalSyntheticLambda8(r2, r53);
        JsonContext$$ExternalSyntheticLambda9 r7 = new JsonContext$$ExternalSyntheticLambda9(r0);
        JsonContext$$ExternalSyntheticLambda10 r02 = new JsonContext$$ExternalSyntheticLambda10(r12, r2, r7, r6);
        JsonContext$$ExternalSyntheticLambda11 r8 = new JsonContext$$ExternalSyntheticLambda11(r12, r6);
        ApiWrap$Media r9 = r12.media;
        if (r9 == null) goto L19;
        if (r9.content == null) goto L19;
        r4.add(new Pair("media", SerializeMessageMedia(r12, r11, r13, r14)));
    L19:
        if (r12.action == null) goto L22;
        r4.add(new Pair("action", SerializeMessageAction(r11, r12.action, r12, r13)));
    L22:
        if (r12.action != null) goto L41;
        r02.run("from");
        r6.run("author", r12.signature);
        long r132 = r12.forwardedFromId;
        if (r132 == 0) goto L26;
        r2.run("forwarded_from", (String) r7.run(Long.valueOf(r132)));
    L31:
        long r133 = r12.savedFromChatId;
        if (r133 == 0) goto L34;
        r2.run("saved_from", (String) r7.run(Long.valueOf(r133)));
    L34:
        r8.run("reply_to_message_id");
        long r134 = r12.viaBotId;
        if (r134 == 0) goto L41;
        String r135 = ((ApiWrap$User) r3.run(Long.valueOf(r134))).username;
        if (r135 == null) goto L41;
        if (r135.isEmpty() == true) goto L41;
        r6.run("via_bot", r135);
        goto L41
    L26:
        String r136 = r12.forwardedFromName;
        if (r136 == null) goto L31;
        if (r136.isEmpty() == true) goto L31;
        r2.run("forwarded_from", StringAllowNull(r12.forwardedFromName));
    L41:
        r2.run("text_entities", SerializeText(r11, r12.text, true));
        ArrayList<ArrayList<ApiWrap$HistoryMessageMarkupButton>> r137 = r12.inlineButtonRows;
        if (r137 == null) goto L47;
        if (r137.isEmpty() == true) goto L47;
        JsonContext$$ExternalSyntheticLambda12 r138 = new JsonContext$$ExternalSyntheticLambda12(r11);
        r11.nesting.add(Boolean.FALSE);
        List r139 = (List) r12.inlineButtonRows.stream().map(new JsonContext$$ExternalSyntheticLambda1(r138)).collect(Collectors.toList());
        ArrayList<Boolean> r03 = r11.nesting;
        r03.remove(r03.size() - 1);
        r2.run("inline_bot_buttons", SerializeArray(r11, new ArrayList(r139)));
    L47:
        if (r12.reactions.isEmpty() == true) goto L50;
        JsonContext$$ExternalSyntheticLambda2 r1310 = new JsonContext$$ExternalSyntheticLambda2(r11, r7, r53);
        r11.nesting.add(Boolean.FALSE);
        r2.run("reactions", SerializeArray(r11, new ArrayList((List) r12.reactions.stream().map(new JsonContext$$ExternalSyntheticLambda3(r1310)).collect(Collectors.toList()))));
        ArrayList<Boolean> r112 = r11.nesting;
        r112.remove(r112.size() - 1);
    L50:
        return (String) r1.run();
    L9:
        r5 = "message";
        goto L10
    L5:
        return SerializeObject(r11, new Pair[]{new Pair("id", Integer.valueOf(r12.id)), new Pair("type", SerializeString("unsupported"))});
    }

    private static String SerializeMessageAction(JsonContext r24, TLRPC.MessageAction r25, ApiWrap$Message r26, HashMap<Long, ApiWrap$Peer> r27) {
        ArrayList r3 = new ArrayList();
        JsonContext$$ExternalSyntheticLambda14 r4 = new JsonContext$$ExternalSyntheticLambda14(r27);
        JsonContext$$ExternalSyntheticLambda18 r5 = new JsonContext$$ExternalSyntheticLambda18(r4);
        JsonContext$$ExternalSyntheticLambda19 r6 = new JsonContext$$ExternalSyntheticLambda19(r3);
        JsonContext$$ExternalSyntheticLambda21 r8 = new JsonContext$$ExternalSyntheticLambda21(r6, new JsonContext$$ExternalSyntheticLambda20());
        JsonContext$$ExternalSyntheticLambda22 r7 = new JsonContext$$ExternalSyntheticLambda22(r4);
        JsonContext$$ExternalSyntheticLambda23 r42 = new JsonContext$$ExternalSyntheticLambda23(r26, r6, r7, r8);
        JsonContext$$ExternalSyntheticLambda24 r9 = new JsonContext$$ExternalSyntheticLambda24(r26, r8);
        JsonContext$$ExternalSyntheticLambda25 r10 = new JsonContext$$ExternalSyntheticLambda25(r5, r6, r24);
        JsonContext$$ExternalSyntheticLambda15 r11 = new JsonContext$$ExternalSyntheticLambda15(new JsonContext$$ExternalSyntheticLambda26(r8), r8);
        JsonContext$$ExternalSyntheticLambda16 r52 = new JsonContext$$ExternalSyntheticLambda16(r8);
        if ((r25 instanceof TLRPC.TL_messageActionChatCreate) == false) goto L6;
        TLRPC.TL_messageActionChatCreate r1 = (TLRPC.TL_messageActionChatCreate) r25;
        r42.run("actor");
        r52.run("create_group");
        r8.run("title", r1.title);
        r10.run(r1.users, "members");
    L250:
        return SerializeObject(r24, (Pair[]) r3.toArray(new Pair[r3.size()]));
    L6:
        if ((r25 instanceof TLRPC.TL_messageActionChatEditTitle) == false) goto L9;
        r42.run("actor");
        r52.run("edit_group_title");
        r8.run("title", ((TLRPC.TL_messageActionChatEditTitle) r25).title);
        goto L250
    L9:
        if ((r25 instanceof TLRPC.TL_messageActionChatEditPhoto) == false) goto L14;
        r42.run("actor");
        r52.run("edit_group_photo");
        r11.run(((ApiWrap$ActionChatEditPhoto) r26.parsedAction).photo().image);
        if (((ApiWrap$ActionChatEditPhoto) r26.parsedAction).photo().spoilered == false) goto L250;
        r8.run("media_spoiler", Boolean.TRUE);
        goto L250
    L14:
        if ((r25 instanceof TLRPC.TL_messageActionChatDeletePhoto) == false) goto L18;
        r42.run("actor");
        r52.run("delete_group_photo");
    L16:
        r3 = r3;
        goto L250
    L18:
        if ((r25 instanceof TLRPC.TL_messageActionChatAddUser) == false) goto L21;
        r42.run("actor");
        r52.run("invite_members");
        r10.run(((TLRPC.TL_messageActionChatAddUser) r25).users, "members");
        goto L16
    L21:
        if ((r25 instanceof TLRPC.TL_messageActionChatDeleteUser) == false) goto L24;
        r42.run("actor");
        r52.run("remove_members");
        r10.run(new ArrayList(Collections.singletonList(Long.valueOf(((TLRPC.TL_messageActionChatDeleteUser) r25).user_id))), "members");
        goto L16
    L24:
        if ((r25 instanceof TLRPC.TL_messageActionChatJoinedByLink) == false) goto L27;
        r42.run("actor");
        r52.run("join_group_by_link");
        r6.run("inviter_id", _UrlKt.FRAGMENT_ENCODE_SET + ((TLRPC.TL_messageActionChatJoinedByLink) r25).inviter_id);
        goto L16
    L27:
        if ((r25 instanceof TLRPC.TL_messageActionChannelCreate) == false) goto L30;
        r42.run("actor");
        r52.run("create_channel");
        r8.run("title", ((TLRPC.TL_messageActionChannelCreate) r25).title);
        goto L16
    L30:
        if ((r25 instanceof TLRPC.TL_messageActionChatMigrateTo) == false) goto L33;
        r42.run("actor");
        r52.run("migrate_to_supergroup");
        goto L16
    L33:
        if ((r25 instanceof TLRPC.TL_messageActionChannelMigrateFrom) == false) goto L36;
        r42.run("actor");
        r52.run("migrate_from_group");
        r8.run("title", ((TLRPC.TL_messageActionChannelMigrateFrom) r25).title);
        goto L16
    L36:
        if ((r25 instanceof TLRPC.TL_messageActionPinMessage) == false) goto L39;
        r42.run("actor");
        r52.run("pin_message");
        r9.run("message_id");
        goto L16
    L39:
        if ((r25 instanceof TLRPC.TL_messageActionHistoryClear) == false) goto L42;
        r42.run("actor");
        r52.run("clear_history");
        goto L16
    L42:
        if ((r25 instanceof TLRPC.TL_messageActionGameScore) == false) goto L45;
        r42.run("actor");
        r52.run("score_in_game");
        r9.run("game_message_id");
        r8.run("score", Integer.valueOf(((TLRPC.TL_messageActionGameScore) r25).score));
        goto L16
    L45:
        if ((r25 instanceof TLRPC.TL_messageActionPaymentSent) == false) goto L53;
        TLRPC.TL_messageActionPaymentSent r12 = (TLRPC.TL_messageActionPaymentSent) r25;
        r52.run("send_payment");
        r8.run("amount", Long.valueOf(r12.total_amount));
        r8.run("currency", r12.currency);
        r9.run("invoice_message_id");
        if (r12.recurring_used == false) goto L50;
        r8.run("recurring", "used");
        goto L16
    L50:
        if (r12.recurring_init == false) goto L16;
        r8.run("recurring", "init");
        goto L16
    L53:
        if ((r25 instanceof TLRPC.TL_messageActionPhoneCall) == false) goto L72;
        TLRPC.TL_messageActionPhoneCall r13 = (TLRPC.TL_messageActionPhoneCall) r25;
        r42.run("actor");
        r52.run("phone_call");
        int r2 = r13.duration;
        if (r2 == 0) goto L57;
        r8.run("duration_seconds", Integer.valueOf(r2));
    L57:
        TLRPC.PhoneCallDiscardReason r14 = r13.reason;
        if ((r14 instanceof TLRPC.TL_phoneCallDiscardReasonHangup) == false) goto L61;
        String r32 = "hangup";
    L70:
        r8.run("discard_reason", r32);
        goto L16
    L61:
        if ((r14 instanceof TLRPC.TL_phoneCallDiscardReasonBusy) == false) goto L64;
        r32 = "busy";
        goto L70
    L64:
        if ((r14 instanceof TLRPC.TL_phoneCallDiscardReasonMissed) == false) goto L67;
        r32 = "missed";
        goto L70
    L67:
        if ((r14 instanceof TLRPC.TL_phoneCallDiscardReasonDisconnect) == false) goto L69;
        r32 = "disconnect";
        goto L70
    L69:
        r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L70
    L72:
        if ((r25 instanceof TLRPC.TL_messageActionScreenshotTaken) == false) goto L75;
        r42.run("actor");
        r52.run("take_screenshot");
        goto L16
    L75:
        if ((r25 instanceof TLRPC.TL_messageActionCustomAction) == false) goto L78;
        r42.run("actor");
        r8.run("information_text", ((TLRPC.TL_messageActionCustomAction) r25).message);
        goto L16
    L78:
        if ((r25 instanceof TLRPC.TL_messageActionBotAllowed) == false) goto L90;
        TLRPC.TL_messageActionBotAllowed r15 = (TLRPC.TL_messageActionBotAllowed) r25;
        if (r15.attach_menu == false) goto L83;
        r52.run("attach_menu_bot_allowed");
        goto L16
    L83:
        if (r15.from_request == false) goto L86;
        r52.run("web_app_bot_allowed");
        goto L16
    L86:
        if (r15.app.id == 0) goto L88;
        r52.run("allow_sending_messages");
        r8.run("reason_app_id", Long.valueOf(r15.app.id));
        r8.run("reason_app_name", r15.app.title);
        goto L16
    L88:
        r52.run("allow_sending_messages");
        r8.run("reason_domain", r15.domain);
        goto L16
    L90:
        if ((r25 instanceof TLRPC.TL_messageActionSecureValuesSent) == false) goto L136;
        r52.run("send_passport_values");
        ArrayList r22 = new ArrayList();
        ArrayList<TLRPC.SecureValueType> r16 = ((TLRPC.TL_messageActionSecureValuesSent) r25).types;
        int r33 = r16.size();
        int r43 = 0;
    L92:
        if (r43 >= r33) goto L134;
        TLRPC.SecureValueType r53 = r16.get(r43);
        r43 = r43 + 1;
        TLRPC.SecureValueType r54 = r53;
        if ((r54 instanceof TLRPC.TL_secureValueTypeAddress) == false) goto L97;
        String r55 = "address_information";
    L133:
        r22.add(SerializeString(r55));
        goto L92
    L97:
        if ((r54 instanceof TLRPC.TL_secureValueTypePassportRegistration) == false) goto L100;
        r55 = "passport_registration";
        goto L133
    L100:
        if ((r54 instanceof TLRPC.TL_secureValueTypeIdentityCard) == false) goto L103;
        r55 = "identity_card";
        goto L133
    L103:
        if ((r54 instanceof TLRPC.TL_secureValueTypeUtilityBill) == false) goto L106;
        r55 = "utility_bill";
        goto L133
    L106:
        if ((r54 instanceof TLRPC.TL_secureValueTypeBankStatement) == false) goto L109;
        r55 = "bank_statement";
        goto L133
    L109:
        if ((r54 instanceof TLRPC.TL_secureValueTypeEmail) == false) goto L112;
        r55 = "email";
        goto L133
    L112:
        if ((r54 instanceof TLRPC.TL_secureValueTypePersonalDetails) == false) goto L115;
        r55 = "personal_details";
        goto L133
    L115:
        if ((r54 instanceof TLRPC.TL_secureValueTypeTemporaryRegistration) == false) goto L118;
        r55 = "temporary_registration";
        goto L133
    L118:
        if ((r54 instanceof TLRPC.TL_secureValueTypePassport) == false) goto L121;
        r55 = "passport";
        goto L133
    L121:
        if ((r54 instanceof TLRPC.TL_secureValueTypeRentalAgreement) == false) goto L124;
        r55 = "rental_agreement";
        goto L133
    L124:
        if ((r54 instanceof TLRPC.TL_secureValueTypeDriverLicense) == false) goto L127;
        r55 = "driver_license";
        goto L133
    L127:
        if ((r54 instanceof TLRPC.TL_secureValueTypePhone) == false) goto L130;
        r55 = "phone_number";
        goto L133
    L130:
        if ((r54 instanceof TLRPC.TL_secureValueTypeInternalPassport) == false) goto L132;
        r55 = "internal_passport";
        goto L133
    L132:
        r55 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L133
    L134:
        r6.run("values", SerializeArray(r24, r22));
        goto L16
    L136:
        if ((r25 instanceof TLRPC.TL_messageActionContactSignUp) == false) goto L139;
        r42.run("actor");
        r52.run("joined_telegram");
        goto L16
    L139:
        if ((r25 instanceof TLRPC.TL_messageActionGeoProximityReached) == false) goto L148;
        TLRPC.TL_messageActionGeoProximityReached r17 = (TLRPC.TL_messageActionGeoProximityReached) r25;
        r52.run("proximity_reached");
        if (MessageObject.getPeerId(r17.from_id) == 0) goto L144;
        r6.run("from", (String) r7.run(Long.valueOf(MessageObject.getPeerId(r17.from_id))));
        r8.run("from_id", Long.valueOf(MessageObject.getPeerId(r17.from_id)));
    L144:
        if (MessageObject.getPeerId(r17.to_id) == 0) goto L146;
        r6.run("to", (String) r7.run(Long.valueOf(MessageObject.getPeerId(r17.to_id))));
        r8.run("to_id", Long.valueOf(MessageObject.getPeerId(r17.to_id)));
    L146:
        r8.run("distance", Integer.valueOf(r17.distance));
        goto L16
    L148:
        if ((r25 instanceof TLRPC.TL_messageActionPhoneNumberRequest) == false) goto L151;
        r42.run("actor");
        r52.run("requested_phone_number");
        goto L16
    L151:
        if ((r25 instanceof TLRPC.TL_messageActionGroupCall) == false) goto L156;
        r42.run("actor");
        r52.run("group_call");
        int r18 = ((TLRPC.TL_messageActionGroupCall) r25).duration;
        if (r18 == 0) goto L16;
        r8.run("duration", Integer.valueOf(r18));
        goto L16
    L156:
        if ((r25 instanceof TLRPC.TL_messageActionInviteToGroupCall) == false) goto L159;
        TLRPC.TL_messageActionInviteToGroupCall r19 = (TLRPC.TL_messageActionInviteToGroupCall) r25;
        r42.run("actor");
        r52.run("invite_to_group_call");
        r10.run(r19.users, "members");
        r6.run("values", SerializeArray(r24, new ArrayList((Collection) r19.users.stream().map(new JsonContext$$ExternalSyntheticLambda17()).collect(Collectors.toList()))));
        goto L16
    L159:
        if ((r25 instanceof TLRPC.TL_messageActionSetMessagesTTL) == false) goto L162;
        r42.run("actor");
        r52.run("set_messages_ttl");
        r8.run("period", Integer.valueOf(((TLRPC.TL_messageActionSetMessagesTTL) r25).period));
        goto L16
    L162:
        if ((r25 instanceof TLRPC.TL_messageActionGroupCallScheduled) == false) goto L165;
        r42.run("actor");
        r52.run("group_call_scheduled");
        r8.run("schedule_date", Integer.valueOf(((TLRPC.TL_messageActionGroupCallScheduled) r25).schedule_date));
        goto L16
    L165:
        if ((r25 instanceof TLRPC.TL_messageActionSetChatTheme) == false) goto L174;
        r42.run("actor");
        r52.run("edit_chat_theme");
        TLRPC.ChatTheme r110 = ((TLRPC.TL_messageActionSetChatTheme) r25).theme;
        if ((r110 instanceof TLRPC.TL_chatTheme) == false) goto L16;
        TLRPC.TL_chatTheme r111 = (TLRPC.TL_chatTheme) r110;
        String r23 = r111.emoticon;
        if (r23 == null) goto L16;
        if (r23.isEmpty() == true) goto L16;
        r8.run("emoticon", r111.emoticon);
        goto L16
    L174:
        if ((r25 instanceof TLRPC.TL_messageActionChatJoinedByRequest) == false) goto L177;
        r42.run("actor");
        r52.run("join_group_by_request");
        goto L16
    L177:
        if ((r25 instanceof TLRPC.TL_messageActionWebViewDataSent) == false) goto L180;
        r52.run("send_webview_data");
        r8.run("text", ((TLRPC.TL_messageActionWebViewDataSent) r25).text);
        goto L16
    L180:
        if ((r25 instanceof TLRPC.TL_messageActionGiftPremium) == false) goto L190;
        TLRPC.TL_messageActionGiftPremium r112 = (TLRPC.TL_messageActionGiftPremium) r25;
        r42.run("actor");
        r52.run("send_premium_gift");
        String r28 = r112.currency;
        if (r28 != null) goto L184;
    L186:
        int r113 = r112.months;
        if (r113 == 0) goto L16;
        r8.run("months", Integer.valueOf(r113));
        goto L16
    L184:
        if (r28.isEmpty() == true) goto L186;
        r8.run("amount", Long.valueOf(r112.amount));
        r8.run("currency", r112.currency);
        goto L186
    L190:
        if ((r25 instanceof TLRPC.TL_messageActionTopicCreate) == false) goto L193;
        r42.run("actor");
        r52.run("topic_created");
        r8.run("title", ((TLRPC.TL_messageActionTopicCreate) r25).title);
        goto L16
    L193:
        if ((r25 instanceof TLRPC.TL_messageActionTopicEdit) == false) goto L201;
        TLRPC.TL_messageActionTopicEdit r114 = (TLRPC.TL_messageActionTopicEdit) r25;
        r42.run("actor");
        r52.run("topic_edit");
        if (r114.title.isEmpty() == true) goto L197;
        r8.run("title", r114.title);
    L197:
        long r115 = r114.icon_emoji_id;
        if (r115 == 0) goto L16;
        r8.run("new_icon_emoji_id", Long.valueOf(r115));
        goto L16
    L201:
        if ((r25 instanceof TLRPC.TL_messageActionSuggestProfilePhoto) == false) goto L206;
        r42.run("actor");
        r52.run("suggest_profile_photo");
        r11.run(((ApiWrap$ActionSuggestProfilePhoto) r26.parsedAction).photo().image);
        if (((ApiWrap$ActionSuggestProfilePhoto) r26.parsedAction).photo().spoilered == false) goto L16;
        r8.run("media_spoiler", Boolean.TRUE);
        goto L16
    L206:
        if ((r25 instanceof TLRPC.TL_messageActionRequestedPeer) == false) goto L212;
        TLRPC.TL_messageActionRequestedPeer r116 = (TLRPC.TL_messageActionRequestedPeer) r25;
        r42.run("actor");
        r52.run("requested_peer");
        r8.run("button_id", Integer.valueOf(r116.button_id));
        ArrayList r29 = new ArrayList();
        ArrayList<TLRPC.Peer> r117 = r116.peers;
        int r34 = r117.size();
        int r44 = 0;
    L208:
        if (r44 >= r34) goto L210;
        TLRPC.Peer r56 = r117.get(r44);
        r44 = r44 + 1;
        r29.add(String.valueOf(MessageObject.getPeerId(r56)));
        goto L208
    L210:
        r8.run("peers", SerializeArray(r24, r29));
        goto L16
    L212:
        if ((r25 instanceof TLRPC.TL_messageActionGiftCode) == false) goto L218;
        TLRPC.TL_messageActionGiftCode r118 = (TLRPC.TL_messageActionGiftCode) r25;
        r52.run("gift_code_prize");
        r8.run("gift_code", r118.slug);
        if (MessageObject.getPeerId(r118.boost_peer) == 0) goto L216;
        r8.run("boost_peer_id", Long.valueOf(MessageObject.getPeerId(r118.boost_peer)));
    L216:
        r8.run("months", Integer.valueOf(r118.months));
        r8.run("is_unclaimed", Boolean.valueOf(r118.unclaimed));
        r8.run("via_giveaway", Boolean.valueOf(r118.via_giveaway));
        goto L16
    L218:
        if ((r25 instanceof TLRPC.TL_messageActionGiveawayLaunch) == false) goto L221;
        r52.run("giveaway_launch");
        goto L16
    L221:
        if ((r25 instanceof TLRPC.TL_messageActionGiveawayResults) == false) goto L224;
        TLRPC.TL_messageActionGiveawayResults r119 = (TLRPC.TL_messageActionGiveawayResults) r25;
        r52.run("giveaway_results");
        r8.run("winners", Integer.valueOf(r119.winners_count));
        r8.run("unclaimed", Integer.valueOf(r119.unclaimed_count));
        r8.run("stars_boolean", Boolean.valueOf(r119.stars));
        goto L16
    L224:
        if ((r25 instanceof TLRPC.TL_messageActionSetChatWallPaper) == false) goto L231;
        r42.run("actor");
        if (((TLRPC.TL_messageActionSetChatWallPaper) r25).same == false) goto L228;
        String r120 = "set_same_chat_wallpaper";
    L229:
        r52.run(r120);
        r9.run("message_id");
        goto L16
    L228:
        r120 = "set_chat_wallpaper";
        goto L229
    L231:
        if ((r25 instanceof TLRPC.TL_messageActionBoostApply) == false) goto L234;
        r42.run("actor");
        r52.run("boost_apply");
        r8.run("boosts", Integer.valueOf(((TLRPC.TL_messageActionBoostApply) r25).boosts));
        goto L16
    L234:
        if ((r25 instanceof TLRPC.TL_messageActionPaymentRefunded) == false) goto L237;
        TLRPC.TL_messageActionPaymentRefunded r121 = (TLRPC.TL_messageActionPaymentRefunded) r25;
        r52.run("refunded_payment");
        r8.run("amount", Long.valueOf(r121.total_amount));
        r8.run("currency", r121.currency);
        r6.run("peer_name", (String) r7.run(Long.valueOf(MessageObject.getPeerId(r121.peer))));
        r8.run("peer", r121.peer);
        r8.run("charge_id", r121.charge.id);
        goto L16
    L237:
        if ((r25 instanceof TLRPC.TL_messageActionGiftStars) == false) goto L242;
        TLRPC.TL_messageActionGiftStars r122 = (TLRPC.TL_messageActionGiftStars) r25;
        r42.run("actor");
        r52.run("send_stars_gift");
        r8.run("amount", Long.valueOf(r122.amount));
        r8.run("currency", r122.currency);
        long r123 = r122.stars;
        if (r123 == 0) goto L16;
        r8.run("stars", Long.valueOf(r123));
        goto L16
    L242:
        if ((r25 instanceof TLRPC.TL_messageActionPrizeStars) == false) goto L245;
        TLRPC.TL_messageActionPrizeStars r124 = (TLRPC.TL_messageActionPrizeStars) r25;
        r42.run("actor");
        r52.run("stars_prize");
        r8.run("boost_peer_id", Long.valueOf(MessageObject.getPeerId(r124.peer)));
        r6.run("boost_peer_name", (String) r7.run(Long.valueOf(MessageObject.getPeerId(r124.peer))));
        r8.run("stars", Long.valueOf(r124.amount));
        r8.run("is_unclaimed", Boolean.valueOf(r124.unclaimed));
        r8.run("giveaway_msg_id", Integer.valueOf(r124.giveaway_msg_id));
        r8.run("transaction_id", r124.transaction_id);
        goto L16
    L245:
        if ((r25 instanceof TLRPC.TL_messageActionStarGift) == false) goto L16;
        TLRPC.TL_messageActionStarGift r125 = (TLRPC.TL_messageActionStarGift) r25;
        r42.run("actor");
        r52.run("send_star_gift");
        r3 = r3;
        r3.add(new Pair("gift_id", Long.valueOf(r125.gift.id)));
        r3.add(new Pair("stars", Long.valueOf(r125.convert_stars)));
        r8.run("is_limited", Boolean.valueOf(r125.gift.limited));
        r8.run("is_anonymous", Boolean.valueOf(r125.name_hidden));
        if ((r125.flags & 2) == 0) goto L250;
        TLRPC.TL_textWithEntities r126 = r125.message;
        r6.run("gift_text", SerializeText(r24, DataTypesUtils.ParseText(r126.text, r126.entities), true));
        goto L250
    }

    private static String SerializeMessageMedia(ApiWrap$Message r25, JsonContext r26, HashMap<Long, ApiWrap$Peer> r27, String r28) {
        ArrayList r2 = new ArrayList();
        JsonContext$$ExternalSyntheticLambda27 r3 = new JsonContext$$ExternalSyntheticLambda27(r2);
        JsonContext$$ExternalSyntheticLambda30 r5 = new JsonContext$$ExternalSyntheticLambda30(r3, new JsonContext$$ExternalSyntheticLambda29());
        JsonContext$$ExternalSyntheticLambda32 r6 = new JsonContext$$ExternalSyntheticLambda32(new JsonContext$$ExternalSyntheticLambda31(r27));
        JsonContext$$ExternalSyntheticLambda33 r4 = new JsonContext$$ExternalSyntheticLambda33(r5);
        JsonContext$$ExternalSyntheticLambda34 r7 = new JsonContext$$ExternalSyntheticLambda34(r4, r5);
        Object r8 = r25.media.content;
        if ((r8 instanceof HtmlWriter.Photo) == false) goto L10;
        HtmlWriter.Photo r82 = (HtmlWriter.Photo) r8;
        r5.run("media_type", "photo");
        r7.run(r82.image);
        if (r82.spoilered == false) goto L7;
        r5.run("media_spoiler", Boolean.TRUE);
    L7:
        int r32 = r25.media.ttl;
        if (r32 == 0) goto L103;
        r5.run("ttl", Integer.valueOf(r32));
    L103:
        ApiWrap$File.SkipReason r0 = r25.getFile().skipReason;
        ApiWrap$File.SkipReason r02 = ApiWrap$File.SkipReason.None;
        return SerializeObject(r26, (Pair[]) r2.toArray(new Pair[r2.size()]));
    L10:
        boolean r72 = r8 instanceof ApiWrap$Document;
        String r13 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r72 == false) goto L47;
        ApiWrap$Document r83 = (ApiWrap$Document) r8;
        r4.run(r83.file, "file", _UrlKt.FRAGMENT_ENCODE_SET);
        r5.run("file_name", r83.name);
        if (r83.isSticker == false) goto L18;
        r5.run("media_type", "sticker");
        NativeByteBuffer r42 = new NativeByteBuffer(r83.sticker.getObjectSize());     // Catch: Exception -> L105
        r83.sticker.serializeToStream(r42);     // Catch: Exception -> L105
        r42.reuse();     // Catch: Exception -> L105
        r42.buffer.rewind();     // Catch: Exception -> L105
        byte[] r62 = new byte[r42.buffer.remaining()];     // Catch: Exception -> L105
        r42.buffer.get(r62);     // Catch: Exception -> L105
        r3.run("serializedSticker", SerializeString(Base64.encodeToString(r62, 0)));     // Catch: Exception -> L105
    L32:
        r3.run("mimeType", SerializeString(r83.mime));
        int r33 = r83.duration;
        if (r33 == 0) goto L35;
        r5.run("duration", Integer.valueOf(r33));
    L35:
        int r34 = r83.width;
        if (r34 == 0) goto L41;
        if (r83.height == 0) goto L41;
        r5.run("width", Integer.valueOf(r34));
        r5.run("height", Integer.valueOf(r83.height));
    L41:
        if (r83.spoilered == false) goto L43;
        r5.run("media_spoiler", Boolean.TRUE);
    L43:
        int r35 = r25.media.ttl;
        if (r35 == 0) goto L103;
        r5.run("ttl", Integer.valueOf(r35));
        goto L103
    L18:
        if (r83.isVideoMessage == false) goto L21;
        r5.run("media_type", "video_message");
        goto L32
    L21:
        if (r83.isVoiceMessage == false) goto L24;
        r5.run("media_type", "voice_message");
        goto L32
    L24:
        if (r83.isAnimated == false) goto L27;
        r5.run("media_type", "animation");
        goto L32
    L27:
        if (r83.isVideoFile == false) goto L30;
        r5.run("media_type", "video_file");
        goto L32
    L30:
        if (r83.isAudioFile == false) goto L32;
        r5.run("media_type", "audio_file");
        r5.run("performer", r83.songPerformer);
        r5.run("title", r83.songTitle);
        goto L32
    L47:
        if ((r8 instanceof ApiWrap$SharedContact) == false) goto L58;
        ApiWrap$SharedContact r84 = (ApiWrap$SharedContact) r8;
        r3.run("mediaType", SerializeString("contact"));
        r3.run("contact_information", SerializeObject(r26, new Pair[]{new Pair("first_name", SerializeString(r84.info.firstName)), new Pair("last_name", SerializeString(r84.info.lastName)), new Pair("phone_number", SerializeString(r84.info.phoneNumber))}));
        ApiWrap$File r36 = r84.vcard;
        byte[] r63 = r36.content;
        if (r63 == null) goto L103;
        if (r63.length == 0) goto L103;
        if (r36.skipReason != ApiWrap$File.SkipReason.None) goto L55;
        r4.run(r36, "contact_vcard", _UrlKt.FRAGMENT_ENCODE_SET);
    L56:
        r5.run("size", Long.valueOf(r84.vcard.size));
        goto L103
    L55:
        r4.run(r36, "skipReason", _UrlKt.FRAGMENT_ENCODE_SET);
        goto L56
    L58:
        if ((r8 instanceof ApiWrap$GeoPoint) == false) goto L67;
        ApiWrap$GeoPoint r85 = (ApiWrap$GeoPoint) r8;
        r3.run("mediaType", SerializeString("geopoint"));
        if (r85.valid == false) goto L62;
        String r43 = SerializeObject(r26, new Pair[]{new Pair("latitude", DataTypesUtils.NumberToString((int) r85.latitude)), new Pair("longitude", DataTypesUtils.NumberToString((int) r85.longitude))});
    L63:
        r3.run("location_information", r43);
        int r37 = r25.media.ttl;
        if (r37 == 0) goto L103;
        r5.run("ttl", Integer.valueOf(r37));
        goto L103
    L62:
        r43 = "null";
        goto L63
    L67:
        if ((r8 instanceof ApiWrap$Venue) == false) goto L72;
        ApiWrap$Venue r86 = (ApiWrap$Venue) r8;
        r3.run("mediaType", SerializeString("venue"));
        r5.run("place_name", r86.title);
        r5.run("address", r86.address);
        if (r86.point.valid == false) goto L103;
        r3.run("location_information", SerializeObject(r26, new Pair[]{new Pair("latitude", DataTypesUtils.NumberToString((int) r86.point.latitude)), new Pair("longitude", DataTypesUtils.NumberToString((int) r86.point.longitude))}));
        goto L103
    L72:
        if ((r8 instanceof ApiWrap$Game) == false) goto L83;
        ApiWrap$Game r87 = (ApiWrap$Game) r8;
        r3.run("mediaType", SerializeString("game"));
        r5.run("game_title", r87.title);
        r5.run("game_description", r87.description);
        if (r87.botId == 0) goto L103;
        if (r87.shortName.isEmpty() == true) goto L103;
        ApiWrap$User r38 = (ApiWrap$User) r6.run(Long.valueOf(r87.botId));
        if (r38.isBot == false) goto L103;
        if (r38.username.isEmpty() == true) goto L103;
        r5.run("game_short_name", r87.shortName);
        goto L103
    L83:
        if ((r8 instanceof ApiWrap$Invoice) == false) goto L89;
        ApiWrap$Invoice r88 = (ApiWrap$Invoice) r8;
        r3.run("mediaType", SerializeString("invoice"));
        Pair r44 = new Pair("title", SerializeString(r88.title));
        Pair r52 = new Pair(MediaTrack.ROLE_DESCRIPTION, SerializeString(r88.description));
        Pair r64 = new Pair("amount", DataTypesUtils.NumberToString(r88.amount));
        Pair r73 = new Pair("currency", SerializeString(r88.currency));
        int r89 = r88.receiptMsgId;
        if (r89 == 0) goto L87;
        r13 = DataTypesUtils.NumberToString(r89);
    L87:
        r3.run("invoice_information", SerializeObject(r26, new Pair[]{r44, r52, r64, r73, new Pair("receipt_message_id", r13)}));
        goto L103
    L89:
        if ((r8 instanceof ApiWrap$Poll) == false) goto L92;
        ApiWrap$Poll r810 = (ApiWrap$Poll) r8;
        r3.run("mediaType", SerializeString("poll"));
        r26.nesting.add(Boolean.TRUE);
        String r53 = SerializeArray(r26, new ArrayList((List) r810.answers.stream().map(new JsonContext$$ExternalSyntheticLambda35(r26)).collect(Collectors.toList())));
        r26.nesting.remove(r6.size() - 1);
        r3.run("poll", SerializeObject(r26, new Pair[]{new Pair("question", SerializeString(r810.question)), new Pair("closed", String.valueOf(r810.closed)), new Pair("total_voters", DataTypesUtils.NumberToString(r810.totalVotes)), new Pair("answers", r53)}));
        goto L103
    L92:
        if ((r8 instanceof ApiWrap$GiveawayStart) == false) goto L95;
        ApiWrap$GiveawayStart r811 = (ApiWrap$GiveawayStart) r8;
        r3.run("mediaType", SerializeString("giveawayStart"));
        ArrayList<Boolean> r45 = r26.nesting;
        Boolean r54 = Boolean.FALSE;
        r45.add(r54);
        String r46 = SerializeArray(r26, new ArrayList((List) r811.channels.stream().map(new JsonContext$$ExternalSyntheticLambda36()).collect(Collectors.toList())));
        r26.nesting.remove(r10.size() - 1);
        r26.nesting.add(r54);
        String r55 = SerializeArray(r26, new ArrayList((List) r811.countries.stream().map(new JsonContext$$ExternalSyntheticLambda37()).collect(Collectors.toList())));
        r26.nesting.remove(r10.size() - 1);
        r3.run("giveaway_information", SerializeObject(r26, new Pair[]{new Pair("quantity", DataTypesUtils.NumberToString(r811.quantity)), new Pair("months", DataTypesUtils.NumberToString(r811.months)), new Pair("until_date", DataTypesUtils.NumberToString(r811.untilDate)), new Pair("channels", r46), new Pair("countries", r55), new Pair("additional_prize", SerializeString(r811.additionalPrize)), new Pair("stars", DataTypesUtils.NumberToString(r811.credits)), new Pair("is_only_new_subscribers", String.valueOf(!r811.all))}));
        goto L103
    L95:
        if ((r8 instanceof ApiWrap$GiveawayResults) == false) goto L98;
        ApiWrap$GiveawayResults r812 = (ApiWrap$GiveawayResults) r8;
        r3.run("mediaType", SerializeString("giveawayResults"));
        r26.nesting.add(Boolean.FALSE);
        String r47 = SerializeArray(r26, new ArrayList((List) r812.winners.stream().map(new JsonContext$$ExternalSyntheticLambda28()).collect(Collectors.toList())));
        r26.nesting.remove(r5.size() - 1);
        r3.run("giveaway_results", SerializeObject(r26, new Pair[]{new Pair("channel", DataTypesUtils.NumberToString(r812.channel)), new Pair("winners", r47), new Pair("additional_prize", SerializeString(r812.additionalPrize)), new Pair("until_date", DataTypesUtils.NumberToString(r812.untilDate)), new Pair("launch_message_id", DataTypesUtils.NumberToString(r812.launchId)), new Pair("additional_peers_count", DataTypesUtils.NumberToString(r812.additionalPeersCount)), new Pair("winners_count", DataTypesUtils.NumberToString(r812.winnersCount)), new Pair("unclaimed_count", DataTypesUtils.NumberToString(r812.unclaimedCount)), new Pair("months", DataTypesUtils.NumberToString(r812.months)), new Pair("stars", DataTypesUtils.NumberToString(r812.credits)), new Pair("is_refunded", String.valueOf(r812.refunded)), new Pair("is_only_new_subscribers", String.valueOf(!r812.all))}));
        goto L103
    L98:
        if ((r8 instanceof ApiWrap$PaidMedia) == false) goto L101;
        r3.run("mediaType", SerializeString("paidMedia"));
        r5.run("paid_stars_amount", Long.valueOf(((ApiWrap$PaidMedia) r8).stars));
        goto L103
    L101:
        if ((r8 instanceof ApiWrap$UnsupportedMedia) == false) goto L103;
        FileLog.e("Export: Unsupported message");
        goto L103
    }

    public static String SerializeObject(JsonContext r11, Pair<String, String>... r12) {
        String r0 = Indentation(r11);
        r11.nesting.add(Boolean.TRUE);
        String r1 = "\n" + Indentation(r11);
        StringBuilder r2 = new StringBuilder("{");
        int r3 = r12.length;
        boolean r7 = true;
        int r6 = 0;
    L3:
        if (r6 >= r3) goto L12;
        Pair<String, String> r8 = r12[r6];
        String r9 = (String) r8.first;
        String r82 = String.valueOf(r8.second);
        if (r82.isEmpty() == true) goto L11;
        if (r7 == false) goto L9;
        r7 = false;
    L10:
        r2.append(r1);
        r2.append(SerializeString(r9));
        r2.append(": ");
        r2.append(r82);
        goto L11
    L9:
        r2.append(',');
    L11:
        r6 = r6 + 1;
        goto L3
    L12:
        r2.append('\n');
        r2.append(r0);
        r2.append("}");
        String r122 = r2.toString();     // Catch: Throwable -> L16
        ArrayList<Boolean> r112 = r11.nesting;
        r112.remove(r112.size() - 1);
        return r122;
    L16:
        th = move-exception;
        r11.nesting.remove(r11.nesting.size() - 1);
        throw th;
    }

    public static String SerializeString(String r8) {
        String r0 = r8 + r8.length();
        ArrayList r1 = new ArrayList();
        char[] r82 = r8.toCharArray();
        int r2 = r82.length;
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L5;
        r1.add(Character.valueOf(r82[r4]));
        r4 = r4 + 1;
        goto L3
    L5:
        StringBuilder r83 = new StringBuilder("\"");
    L7:
        if (r3 >= r1.size()) goto L46;
        Character r22 = (Character) r1.get(r3);
        if (r22.charValue() != '\n') goto L12;
        r83.append("\\n");
    L45:
        r3 = r3 + 1;
        goto L7
    L12:
        if (r22.charValue() != '\r') goto L15;
        r83.append("\\r");
        goto L45
    L15:
        if (r22.charValue() != '\t') goto L18;
        r83.append("\\t");
        goto L45
    L18:
        if (r22.charValue() != '\"') goto L21;
        r83.append("\\\"");
        goto L45
    L21:
        if (r22.charValue() != '\\') goto L24;
        r83.append("\\\\");
        goto L45
    L24:
        if (r22.charValue() < 0) goto L32;
        if (r22.charValue() >= ' ') goto L32;
        r83.append("\\x");
        r83.append((r22.charValue() >> 4) + 48);
        int r23 = r22.charValue() & 15;
        if (r23 < 10) goto L30;
        r83.append(r23 + 55);
        goto L45
    L30:
        r83.append(r23 + 48);
    L32:
        if (r22.charValue() != 226) goto L44;
        int r42 = r3 + 2;
        if (r42 >= r0.length()) goto L44;
        if ((r3 + 1) != 128) goto L44;
        if (r42 != 168) goto L41;
        r83.append("\\u2028");
        goto L45
    L41:
        if (r42 != 169) goto L43;
        r83.append("\\u2029");
        goto L45
    L43:
        r83.append(r22);
    L44:
        r83.append(r22);
        goto L45
    L46:
        r83.append(Typography.quote);
        return r83.toString();
    }

    public static String SerializeText(JsonContext r10, ArrayList<ApiWrap$TextPart> r11, boolean r12) {
        if (r11.isEmpty() == false) goto L9;
        if (r12 == false) goto L8;
        return _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
    L8:
        return SerializeString(_UrlKt.FRAGMENT_ENCODE_SET);
    L9:
        r10.nesting.add(Boolean.FALSE);
        ArrayList r0 = new ArrayList();
        int r2 = r11.size();
        int r4 = 0;
    L10:
        if (r4 >= r2) goto L59;
        ApiWrap$TextPart r5 = r11.get(r4);
        r4 = r4 + 1;
        ApiWrap$TextPart r52 = r5;
        ApiWrap$TextPart.Type r6 = r52.type;
        if (r6 != ApiWrap$TextPart.Type.Text) goto L16;
        if (r12 == true) goto L16;
        r0.add(SerializeString(r52.text));
    L16:
        switch(AnonymousClass1.$SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[r6.ordinal()]) {
            case 1: goto L39;
            case 2: goto L38;
            case 3: goto L37;
            case 4: goto L36;
            case 5: goto L35;
            case 6: goto L34;
            case 7: goto L33;
            case 8: goto L32;
            case 9: goto L31;
            case 10: goto L30;
            case 11: goto L29;
            case 12: goto L28;
            case 13: goto L27;
            case 14: goto L26;
            case 15: goto L25;
            case 16: goto L24;
            case 17: goto L23;
            case 18: goto L22;
            case 19: goto L21;
            case 20: goto L20;
            case 21: goto L19;
            default: goto L17;
        };
    L19:
        String r62 = "custom_emoji";
    L40:
        ApiWrap$TextPart.Type r7 = r52.type;
        if (r7 != ApiWrap$TextPart.Type.MentionName) goto L44;
        String r72 = r52.additional;
    L58:
        r0.add(SerializeObject(r10, new Pair[]{new Pair("type", SerializeString(r62)), new Pair("text", SerializeString(r52.text)), new Pair("additional", r72)}));
        goto L10
    L44:
        if (r7 != ApiWrap$TextPart.Type.Pre) goto L46;
    L57:
        r72 = SerializeString(r52.additional);
        goto L58
    L46:
        if (r7 == ApiWrap$TextPart.Type.TextUrl) goto L57;
        if (r7 == ApiWrap$TextPart.Type.CustomEmoji) goto L57;
        if (r7 == ApiWrap$TextPart.Type.Blockquote) goto L53;
        r72 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L58
    L53:
        if (r52.additional.isEmpty() == false) goto L55;
        r72 = "false";
        goto L58
    L55:
        r72 = "true";
        goto L58
    L20:
        r62 = "spoiler";
        goto L40
    L21:
        r62 = "bank_card";
        goto L40
    L22:
        r62 = "blockquote";
        goto L40
    L23:
        r62 = "strikethrough";
        goto L40
    L24:
        r62 = "underline";
        goto L40
    L25:
        r62 = "cashtag";
        goto L40
    L26:
        r62 = "phone";
        goto L40
    L27:
        r62 = "mention_name";
        goto L40
    L28:
        r62 = "text_link";
        goto L40
    L29:
        r62 = "plain";
        goto L40
    L30:
        r62 = "pre";
        goto L40
    L31:
        r62 = "code";
        goto L40
    L32:
        r62 = "italic";
        goto L40
    L33:
        r62 = "bold";
        goto L40
    L34:
        r62 = "email";
        goto L40
    L35:
        r62 = "link";
        goto L40
    L36:
        r62 = "bot_command";
        goto L40
    L37:
        r62 = "hashtag";
        goto L40
    L38:
        r62 = "mention";
        goto L40
    L39:
        r62 = "unknown";
        goto L40
    L17:
        CstInsn$$ExternalSyntheticBUOutline0.m("wtf is it? ", r52.text);
        return null;
    L59:
        ArrayList<Boolean> r1 = r10.nesting;
        r1.remove(r1.size() - 1);
        if (r12 == true) goto L68;
        if (r11.size() != 1) goto L68;
        if (r11.get(0).type != ApiWrap$TextPart.Type.Text) goto L68;
        return (String) r0.get(0);
    L68:
        return SerializeArray(r10, r0);
    }

    public static String StringAllowNull(String r1) {
        if (r1 != null) goto L4;
        return "null";
    L4:
        if (r1.isEmpty() == false) goto L7;
        return "null";
    L7:
        return SerializeString(r1);
    }

    public String SerializeDialog(ApiWrap$DialogInfo r6, boolean r7) {
        return Indentation(this) + SerializeObject(this, new Pair[]{new Pair("name", SerializeString(r6.name)), new Pair("id", Long.valueOf(r6.peerId)), new Pair("relativePath", SerializeString(r6.relativePath)), new Pair("left", Boolean.valueOf(r7))});
    }

    public AbstractWriter.Result writeBlock(String r1) {
        return this._file.writeBlock(r1);
    }
}
