package com.exteragram.messenger.export.api;

import android.text.TextUtils;
import android.util.Base64;
import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.export.ExportSettings;
import com.exteragram.messenger.export.api.ApiWrap$DialogInfo;
import com.exteragram.messenger.export.api.ApiWrap$File;
import com.exteragram.messenger.export.api.ApiWrap$HistoryMessageMarkupButton;
import com.exteragram.messenger.export.api.ApiWrap$Poll;
import com.exteragram.messenger.export.api.ApiWrap$TextPart;
import com.exteragram.messenger.export.output.FileManager;
import com.exteragram.messenger.export.output.html.HtmlContext;
import com.exteragram.messenger.export.output.html.HtmlWriter;
import com.exteragram.messenger.utils.chats.ChatUtils;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.stream.Collectors;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline4;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.tgnet.tl.TL_stories;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes4.dex */
public abstract class DataTypesUtils {

    /* renamed from: com.exteragram.messenger.export.api.DataTypesUtils$1, reason: invalid class name */
    public class AnonymousClass1 extends HashMap<Integer, Integer> {
        public AnonymousClass1() {
            put(1, 0);
            put(7, 0);
            put(6, 1);
            put(21724, 1);
            put(2834, 2);
            put(2496, 3);
            put(2040, 4);
            put(1429, 5);
        }
    }

    /* renamed from: com.exteragram.messenger.export.api.DataTypesUtils$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type = null;
        static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason = null;
        static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type = null;

        static {
            int[] r0 = new int[ApiWrap$TextPart.Type.values().length];
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type = r0;
            r0[ApiWrap$TextPart.Type.Text.ordinal()] = 1;     // Catch: NoSuchFieldError -> L52
        L110:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Unknown.ordinal()] = 2;     // Catch: NoSuchFieldError -> L53
        L132:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.BankCard.ordinal()] = 3;     // Catch: NoSuchFieldError -> L54
        L140:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Mention.ordinal()] = 4;     // Catch: NoSuchFieldError -> L55
        L146:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Hashtag.ordinal()] = 5;     // Catch: NoSuchFieldError -> L56
        L94:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.BotCommand.ordinal()] = 6;     // Catch: NoSuchFieldError -> L57
        L96:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Url.ordinal()] = 7;     // Catch: NoSuchFieldError -> L58
        L122:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Email.ordinal()] = 8;     // Catch: NoSuchFieldError -> L59
        L126:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Bold.ordinal()] = 9;     // Catch: NoSuchFieldError -> L60
        L150:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Italic.ordinal()] = 10;     // Catch: NoSuchFieldError -> L61
        L152:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Code.ordinal()] = 11;     // Catch: NoSuchFieldError -> L62
        L104:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Pre.ordinal()] = 12;     // Catch: NoSuchFieldError -> L63
        L108:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.TextUrl.ordinal()] = 13;     // Catch: NoSuchFieldError -> L64
        L130:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.MentionName.ordinal()] = 14;     // Catch: NoSuchFieldError -> L65
        L136:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Phone.ordinal()] = 15;     // Catch: NoSuchFieldError -> L66
        L156:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Cashtag.ordinal()] = 16;     // Catch: NoSuchFieldError -> L67
        L90:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Underline.ordinal()] = 17;     // Catch: NoSuchFieldError -> L68
        L112:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Strike.ordinal()] = 18;     // Catch: NoSuchFieldError -> L69
        L118:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Blockquote.ordinal()] = 19;     // Catch: NoSuchFieldError -> L70
        L138:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.Spoiler.ordinal()] = 20;     // Catch: NoSuchFieldError -> L71
        L144:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[ApiWrap$TextPart.Type.CustomEmoji.ordinal()] = 21;     // Catch: NoSuchFieldError -> L72
        L34:
            int[] r11 = new int[ApiWrap$File.SkipReason.values().length];
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason = r11;
            r11[ApiWrap$File.SkipReason.Unavailable.ordinal()] = 1;     // Catch: NoSuchFieldError -> L73
        L100:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[ApiWrap$File.SkipReason.FileSize.ordinal()] = 2;     // Catch: NoSuchFieldError -> L74
        L124:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[ApiWrap$File.SkipReason.FileType.ordinal()] = 3;     // Catch: NoSuchFieldError -> L75
        L134:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[ApiWrap$File.SkipReason.None.ordinal()] = 4;     // Catch: NoSuchFieldError -> L76
        L39:
            int[] r112 = new int[ApiWrap$DialogInfo.Type.values().length];
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type = r112;
            r112[ApiWrap$DialogInfo.Type.Self.ordinal()] = 1;     // Catch: NoSuchFieldError -> L77
        L154:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.Personal.ordinal()] = 2;     // Catch: NoSuchFieldError -> L78
        L102:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.Bot.ordinal()] = 3;     // Catch: NoSuchFieldError -> L79
        L114:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.PrivateGroup.ordinal()] = 4;     // Catch: NoSuchFieldError -> L80
        L116:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.PrivateSupergroup.ordinal()] = 5;     // Catch: NoSuchFieldError -> L81
        L128:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.PublicSupergroup.ordinal()] = 6;     // Catch: NoSuchFieldError -> L82
        L148:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.PrivateChannel.ordinal()] = 7;     // Catch: NoSuchFieldError -> L83
        L158:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.PublicChannel.ordinal()] = 8;     // Catch: NoSuchFieldError -> L84
        L88:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.Unknown.ordinal()] = 9;     // Catch: NoSuchFieldError -> L85
        L98:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.Replies.ordinal()] = 10;     // Catch: NoSuchFieldError -> L86
        L120:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.VerifyCodes.ordinal()] = 11;     // Catch: NoSuchFieldError -> L87
            return;
        }
    }

    public static /* synthetic */ Boolean $r8$lambda$I4Kps2tU7cRrhDBKAe79CFoVM2c(TLRPC.Document r0, String r1) {
        return Boolean.valueOf(!r0.mime_type.equalsIgnoreCase(r1));
    }

    public static /* synthetic */ String $r8$lambda$YnixTTMCGZSy3zr12T7r1r9bYeo(String r9, String r10, ApiWrap$TextPart r11) {
        String r0 = HtmlContext.SerializeString(r11.text);
        switch(AnonymousClass2.$SwitchMap$com$exteragram$messenger$export$api$ApiWrap$TextPart$Type[r11.type.ordinal()]) {
            case 1: goto L42;
            case 2: goto L42;
            case 3: goto L42;
            case 4: goto L41;
            case 5: goto L39;
            case 6: goto L37;
            case 7: goto L35;
            case 8: goto L33;
            case 9: goto L31;
            case 10: goto L29;
            case 11: goto L27;
            case 12: goto L25;
            case 13: goto L23;
            case 14: goto L21;
            case 15: goto L19;
            case 16: goto L17;
            case 17: goto L15;
            case 18: goto L13;
            case 19: goto L11;
            case 20: goto L9;
            case 21: goto L7;
            default: goto L5;
        };
    L42:
        return r0;
    L5:
        throw new IncompatibleClassChangeError();
    L7:
        return FormatCustomEmoji(r11.additional, r0, r10);
    L9:
        return "<span class=\"spoiler hidden\" onclick=\"ShowSpoiler(this)\"><span aria-hidden=\"true\">" + r0 + "</span></span>";
    L11:
        return "<blockquote>" + r0 + "</blockquote>";
    L13:
        return "<s>" + r0 + "</s>";
    L15:
        return "<u>" + r0 + "</u>";
    L17:
        return "<a href=\"\" onclick=\"return ShowCashtag(" + HtmlContext.SerializeString("\"" + r0.substring(1) + Typography.quote) + ")\">" + r0 + "</a>";
    L19:
        return "<a href=\"tel:" + r0 + "\">" + r0 + "</a>";
    L21:
        return "<a href=\"\" onclick=\"return ShowMentionName()\">" + r0 + "</a>";
    L23:
        return "<a href=\"" + HtmlContext.SerializeString(r11.additional) + "\">" + r0 + "</a>";
    L25:
        return "<pre>" + r0 + "</pre>";
    L27:
        return "<code>" + r0 + "</code>";
    L29:
        return "<em>" + r0 + "</em>";
    L31:
        return "<strong>" + r0 + "</strong>";
    L33:
        return "<a href=\"mailto:" + r0 + "\">" + r0 + "</a>";
    L35:
        return "<a href=\"" + r0 + "\">" + r0 + "</a>";
    L37:
        return "<a href=\"\" onclick=\"return ShowBotCommand(" + HtmlContext.SerializeString("\"" + r0.substring(1) + Typography.quote) + ")\">" + r0 + "</a>";
    L39:
        return "<a href=\"\" onclick=\"return ShowHashtag(" + HtmlContext.SerializeString("\"" + r0.substring(1) + Typography.quote) + ")\">" + r0 + "</a>";
    L41:
        return "<a href=\"" + r9 + r0.substring(1) + "\">" + r0 + "</a>";
    }

    public static /* synthetic */ String $r8$lambda$hVjdozXZbebV18GIZWQWbnIt_UI(ArrayList r0, Integer r1) {
        return (String) r0.get(r1.intValue());
    }

    public static /* synthetic */ void $r8$lambda$vQE9eoidmhRnt1aHYXJ5PfuJR64(HashMap r6, ArrayList r7, ArrayList r8) {
        int r0 = r8.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        Object r2 = r8.get(r1);
        r1 = r1 + 1;
        TLRPC.TL_topPeer r22 = (TLRPC.TL_topPeer) r2;
        ApiWrap$TopPeer r3 = new ApiWrap$TopPeer();
        ApiWrap$Peer r4 = (ApiWrap$Peer) r6.get(Long.valueOf(MessageObject.getPeerId(r22.peer)));
        if (r4 != null) goto L8;
        r4 = EmptyPeer(r22.peer);
    L8:
        r3.peer = r4;
        r3.rating = r22.rating;
        r7.add(r3);
        goto L3
    }

    static {
    }

    public static boolean AddMigrateFromSlice(ApiWrap$DialogInfo r5, ApiWrap$DialogInfo r6, int r7, int r8) {
        TLRPC.InputPeer r0 = r5.migratedFromInput;
        if ((r0 instanceof TLRPC.TL_inputPeerEmpty) == false) goto L6;
    L9:
        int r02 = 0;
    L11:
        if (r02 == r6.splits.size()) goto L13;
        r5.splits.add(Integer.valueOf(r6.splits.get(r02).intValue() - r8));
        r5.messagesCountPerSplit.add(r6.messagesCountPerSplit.get(r02));
        r02 = r02 + 1;
        goto L11
    L13:
        r5.migratedFromInput = r6.input;
        r5.splits.add(Integer.valueOf(r7 - r8));
        r5.messagesCountPerSplit.add(0);
        return true;
    L6:
        if ((r0 instanceof TLRPC.TL_inputPeerChat) == true) goto L8;
    L15:
        return false;
    L8:
        if (r6.peerId != ((TLRPC.TL_inputPeerChat) r0).chat_id) goto L15;
        goto L9
    }

    public static ApiWrap$MessagesSlice AdjustMigrateMessageIds(ApiWrap$MessagesSlice r10) {
        ArrayList<ApiWrap$Message> r0 = r10.list;
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L9;
        ApiWrap$Message r3 = r0.get(r2);
        r2 = r2 + 1;
        ApiWrap$Message r32 = r3;
        r32.id -= 1000000000;
        int r4 = r32.replyToMsgId;
        if (r4 == 0) goto L3;
        if (r32.replyToPeerId != 0) goto L3;
        r32.replyToMsgId = r4 - 1000000000;
        goto L3
    L9:
        return r10;
    }

    public static boolean AppendTopPeers(ApiWrap$ContactsList r8, TLRPC.contacts_TopPeers r9) {
        if ((r9 instanceof TLRPC.TL_contacts_topPeersNotModified) == false) goto L6;
        return false;
    L6:
        if ((r9 instanceof TLRPC.TL_contacts_topPeersDisabled) == false) goto L9;
        return true;
    L9:
        if ((r9 instanceof TLRPC.TL_contacts_topPeers) == false) goto L23;
        TLRPC.TL_contacts_topPeers r92 = (TLRPC.TL_contacts_topPeers) r9;
        DataTypesUtils$$ExternalSyntheticLambda3 r3 = new DataTypesUtils$$ExternalSyntheticLambda3(ParsePeersLists(r92.users, r92.chats));
        ArrayList<TLRPC.TL_topPeerCategoryPeers> r93 = r92.categories;
        int r0 = r93.size();
        int r4 = 0;
    L11:
        if (r4 >= r0) goto L22;
        TLRPC.TL_topPeerCategoryPeers r5 = r93.get(r4);
        r4 = r4 + 1;
        TLRPC.TL_topPeerCategoryPeers r52 = r5;
        TLRPC.TopPeerCategory r6 = r52.category;
        if ((r6 instanceof TLRPC.TL_topPeerCategoryCorrespondents) == true) goto L14;
        if ((r6 instanceof TLRPC.TL_topPeerCategoryBotsInline) == true) goto L17;
        if ((r6 instanceof TLRPC.TL_topPeerCategoryPhoneCalls) == false) goto L21;
        r3.run(r8.phoneCalls, r52.peers);
        goto L11
    L21:
        return false;
    L17:
        r3.run(r8.inlineBots, r52.peers);
        goto L11
    L14:
        r3.run(r8.correspondents, r52.peers);
        goto L11
    L22:
        return true;
    L23:
        return false;
    }

    public static int ApplicationColorIndex(int r2) {
        Integer r0 = new AnonymousClass1().get(Integer.valueOf(r2));
        if (r0 == null) goto L7;
        return r0.intValue();
    L7:
        return PeerColorIndex(r2);
    }

    private static ArrayList<ArrayList<ApiWrap$HistoryMessageMarkupButton>> ButtonRowsFromTL(TLRPC.TL_replyKeyboardMarkup r15) {
        ArrayList<TL_keyboard.KeyboardButtonRow> r152 = r15.rows;
        if (r152.isEmpty() == true) goto L5;
        ArrayList<ArrayList<ApiWrap$HistoryMessageMarkupButton>> r0 = new ArrayList();
        r0.ensureCapacity(r152.size());
        int r1 = r152.size();
        int r3 = 0;
    L7:
        if (r3 >= r1) goto L70;
        TL_keyboard.KeyboardButtonRow r4 = r152.get(r3);
        r3 = r3 + 1;
        TL_keyboard.KeyboardButtonRow r42 = r4;
        ArrayList<ApiWrap$HistoryMessageMarkupButton> r5 = new ArrayList();
        r5.ensureCapacity(r42.buttons.size());
        ArrayList<TL_keyboard.KeyboardButton> r43 = r42.buttons;
        int r6 = r43.size();
        int r7 = 0;
    L9:
        if (r7 >= r6) goto L68;
        TL_keyboard.KeyboardButton r8 = r43.get(r7);
        r7 = r7 + 1;
        TL_keyboard.KeyboardButton r82 = r8;
        String r11 = r82.text;
        TL_keyboard.ButtonTypeProto r83 = r82.getType();
        if ((r83 instanceof TL_keyboard.TL_buttonTypeDefault) == true) goto L12;
        if ((r83 instanceof TL_keyboard.TL_inlineButtonTypeCallback) == true) goto L15;
        if ((r83 instanceof TL_keyboard.TL_buttonTypeRequestGeoLocation) == true) goto L22;
        if ((r83 instanceof TL_keyboard.TL_buttonTypeRequestPhone) == true) goto L25;
        if ((r83 instanceof TL_keyboard.TL_buttonTypeRequestPeer) == true) goto L28;
        if ((r83 instanceof TL_keyboard.TL_inlineButtonTypeUrl) == true) goto L31;
        if ((r83 instanceof TL_keyboard.TL_inlineButtonTypeSwitchInline) == true) goto L34;
        if ((r83 instanceof TL_keyboard.TL_inlineButtonTypeGame) == true) goto L41;
        if ((r83 instanceof TL_keyboard.TL_inlineButtonTypeBuy) == true) goto L44;
        if ((r83 instanceof TL_keyboard.TL_inlineButtonTypeUrlAuth) == true) goto L47;
        if ((r83 instanceof TL_keyboard.TL_buttonTypeRequestPoll) == true) goto L51;
        if ((r83 instanceof TL_keyboard.TL_inlineButtonTypeUserProfile) == true) goto L57;
        if ((r83 instanceof TL_keyboard.TL_inlineButtonTypeWebView) == true) goto L60;
        if ((r83 instanceof TL_keyboard.TL_buttonTypeSimpleWebView) == true) goto L63;
        if ((r83 instanceof TL_keyboard.TL_inlineButtonTypeCopy) == false) goto L9;
        r5.add(new ApiWrap$HistoryMessageMarkupButton(ApiWrap$HistoryMessageMarkupButton.Type.CopyText, r11, ((TL_keyboard.TL_inlineButtonTypeCopy) r83).copy_text.getBytes(StandardCharsets.UTF_8)));
        goto L9
    L63:
        r5.add(new ApiWrap$HistoryMessageMarkupButton(ApiWrap$HistoryMessageMarkupButton.Type.SimpleWebView, r11, ((TL_keyboard.TL_buttonTypeSimpleWebView) r83).url.getBytes(StandardCharsets.UTF_8)));
        goto L9
    L60:
        r5.add(new ApiWrap$HistoryMessageMarkupButton(ApiWrap$HistoryMessageMarkupButton.Type.WebView, r11, ((TL_keyboard.TL_inlineButtonTypeWebView) r83).url.getBytes(StandardCharsets.UTF_8)));
        goto L9
    L57:
        r5.add(new ApiWrap$HistoryMessageMarkupButton(ApiWrap$HistoryMessageMarkupButton.Type.UserProfile, r11, String.valueOf(((TL_keyboard.TL_inlineButtonTypeUserProfile) r83).user_id).getBytes(StandardCharsets.UTF_8)));
        goto L9
    L51:
        if (((TL_keyboard.TL_buttonTypeRequestPoll) r83).quiz == false) goto L53;
        byte[] r84 = new byte[1];
    L54:
        r5.add(new ApiWrap$HistoryMessageMarkupButton(ApiWrap$HistoryMessageMarkupButton.Type.RequestPoll, r11, r84));
        goto L9
    L53:
        r84 = new byte[0];
        goto L54
    L47:
        TL_keyboard.TL_inlineButtonTypeUrlAuth r85 = (TL_keyboard.TL_inlineButtonTypeUrlAuth) r83;
        r5.add(new ApiWrap$HistoryMessageMarkupButton(ApiWrap$HistoryMessageMarkupButton.Type.Auth, r11, r85.url.getBytes(StandardCharsets.UTF_8), r85.fwd_text, r85.button_id));
        goto L9
    L44:
        r5.add(new ApiWrap$HistoryMessageMarkupButton(ApiWrap$HistoryMessageMarkupButton.Type.Buy, r11));
        goto L9
    L41:
        r5.add(new ApiWrap$HistoryMessageMarkupButton(ApiWrap$HistoryMessageMarkupButton.Type.Game, r11));
        goto L9
    L34:
        TL_keyboard.TL_inlineButtonTypeSwitchInline r86 = (TL_keyboard.TL_inlineButtonTypeSwitchInline) r83;
        if (r86.same_peer == false) goto L37;
        ApiWrap$HistoryMessageMarkupButton.Type r10 = ApiWrap$HistoryMessageMarkupButton.Type.SwitchInlineSame;
    L38:
        r5.add(new ApiWrap$HistoryMessageMarkupButton(r10, r11, r86.query.getBytes(StandardCharsets.UTF_8)));
        goto L9
    L37:
        r10 = ApiWrap$HistoryMessageMarkupButton.Type.SwitchInline;
        goto L38
    L31:
        r5.add(new ApiWrap$HistoryMessageMarkupButton(ApiWrap$HistoryMessageMarkupButton.Type.Url, r11, ((TL_keyboard.TL_inlineButtonTypeUrl) r83).url.getBytes(StandardCharsets.UTF_8)));
        goto L9
    L28:
        r5.add(new ApiWrap$HistoryMessageMarkupButton(ApiWrap$HistoryMessageMarkupButton.Type.RequestPeer, r11, "unsupported".getBytes(StandardCharsets.UTF_8), _UrlKt.FRAGMENT_ENCODE_SET, ((TL_keyboard.TL_buttonTypeRequestPeer) r83).button_id));
        goto L9
    L25:
        r5.add(new ApiWrap$HistoryMessageMarkupButton(ApiWrap$HistoryMessageMarkupButton.Type.RequestPhone, r11));
        goto L9
    L22:
        r5.add(new ApiWrap$HistoryMessageMarkupButton(ApiWrap$HistoryMessageMarkupButton.Type.RequestLocation, r11));
        goto L9
    L15:
        TL_keyboard.TL_inlineButtonTypeCallback r87 = (TL_keyboard.TL_inlineButtonTypeCallback) r83;
        if (r87.requires_password == false) goto L18;
        ApiWrap$HistoryMessageMarkupButton.Type r102 = ApiWrap$HistoryMessageMarkupButton.Type.CallbackWithPassword;
    L19:
        r5.add(new ApiWrap$HistoryMessageMarkupButton(r102, r11, r87.data));
        goto L9
    L18:
        r102 = ApiWrap$HistoryMessageMarkupButton.Type.Callback;
        goto L19
    L12:
        r5.add(new ApiWrap$HistoryMessageMarkupButton(ApiWrap$HistoryMessageMarkupButton.Type.Default, r11));
        goto L9
    L68:
        if (r5.isEmpty() == true) goto L7;
        r0.add(r5);
        goto L7
    L70:
        return r0;
    L5:
        return new ArrayList();
    }

    public static String ComposeName(HtmlWriter.UserpicData r1, String r2) {
        if (r1.firstName.isEmpty() == false) goto L8;
        if (r1.lastName.isEmpty() == false) goto L8;
        return r2;
    L8:
        return r1.firstName + ' ' + r1.lastName;
    }

    public static String ComputeDocumentName(ApiWrap$ParseMediaContext r1, TLRPC.Document r2, int r3, String r4) {
        if (r2 == null) goto L27;
        if (r4 != null) goto L5;
    L7:
        String r42 = getExtensionFromMime(r2.mime_type, r2);
        new DataTypesUtils$$ExternalSyntheticLambda2(r2);
        if (MessageObject.isVoiceDocument(r2) == false) goto L16;
        boolean r22 = r2.mime_type.equalsIgnoreCase("audio/mp3");
        StringBuilder r43 = new StringBuilder(MediaStreamTrack.AUDIO_TRACK_KIND);
        int r0 = r1.audios + 1;
        r1.audios = r0;
        r43.append(r0);
        r43.append(PrepareFileNameDatePart(r3));
        if (r22 == true) goto L12;
        String r12 = ".mp3";
    L13:
        r43.append(r12);
        return r43.toString();
    L12:
        r12 = ".ogg";
        goto L13
    L16:
        if (MessageObject.isVideoDocument(r2) == false) goto L23;
        if (r42.isEmpty() == false) goto L20;
        r42 = ".mov";
    L20:
        StringBuilder r23 = new StringBuilder("video_");
        int r02 = r1.videos + 1;
        r1.videos = r02;
        r23.append(r02);
        r23.append(PrepareFileNameDatePart(r3));
        r23.append(r42);
        return r23.toString();
    L23:
        if (r42.isEmpty() == false) goto L25;
        r42 = ".unknown";
    L25:
        StringBuilder r24 = new StringBuilder("file_");
        int r03 = r1.files + 1;
        r1.files = r03;
        r24.append(r03);
        r24.append(PrepareFileNameDatePart(r3));
        r24.append(r42);
        return r24.toString();
    L5:
        if (r4.isEmpty() == true) goto L7;
        return r4;
    L27:
        f$$ExternalSyntheticBUOutline1.m("trying to pass null document!!!");
        return null;
    }

    public static String ComputeLocationKey(ApiWrap$FileLocation r3) {
        String r0 = r3.dcId + "_";
        TLRPC.InputFileLocation r1 = r3.data;
        if ((r1 instanceof TLRPC.TL_inputDocumentFileLocation) == false) goto L7;
        return r0 + "doc_" + ((TLRPC.TL_inputDocumentFileLocation) r1).id;
    L7:
        if ((r1 instanceof TLRPC.TL_inputPhotoFileLocation) == false) goto L11;
        return r0 + "photo_" + ((TLRPC.TL_inputPhotoFileLocation) r1).id;
    L11:
        if ((r1 instanceof ExportRequests$TL_inputTakeoutFileLocation) == true) goto L13;
        FileLog.e("wtf! File location type in Export::ComputeLocationKey. " + r3);
        return r0;
    L13:
        return r0.concat("takeout");
    }

    public static String CountString(int r3, boolean r4) {
        if (r3 != 1) goto L9;
        if (r4 == false) goto L7;
        return "1 outgoing message";
    L7:
        return "1 message";
    L9:
        if (r3 != 0) goto L15;
        if (r4 == false) goto L13;
        return "No outgoing messages";
    L13:
        return "No messages";
    L15:
        StringBuilder r0 = new StringBuilder();
        r0.append(NumberToString(String.valueOf(r3), 0, ' '));
        if (r4 == false) goto L18;
        String r32 = " outgoing messages";
    L19:
        r0.append(r32);
        return r0.toString();
    L18:
        r32 = " messages";
        goto L19
    }

    public static String DeletedString(ApiWrap$DialogInfo.Type r1) {
        switch(AnonymousClass2.$SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[r1.ordinal()]) {
            case 1: goto L10;
            case 2: goto L10;
            case 3: goto L10;
            case 4: goto L8;
            case 5: goto L8;
            case 6: goto L8;
            case 7: goto L6;
            case 8: goto L6;
            case 9: goto L10;
            case 10: goto L10;
            case 11: goto L10;
            default: goto L5;
        };
    L6:
        return "Deleted Channel";
    L8:
        return "Deleted Group";
    L10:
        return "Deleted Account";
    L5:
        throw new IncompatibleClassChangeError();
    }

    public static ApiWrap$DialogInfo DialogInfoFromChat(ApiWrap$Chat r3) {
        ApiWrap$DialogInfo r0 = new ApiWrap$DialogInfo();
        r0.input = r3.input;
        r0.name = r3.title;
        r0.peerId = r3.bareId;
        r0.topMessageDate = 0;
        r0.topMessageId = 0;
        r0.type = DialogTypeFromChat(r3);
        r0.migratedToChannelId = r3.migratedToChannelId;
        r0.isMonoforum = r3.isMonoforum;
        if (r3.isMonoforumAdmin == false) goto L5;
        r0.monoforumBroadcastInput = r3.monoforumBroadcastInput;
    L5:
        return r0;
    }

    public static ApiWrap$DialogInfo DialogInfoFromUser(ApiWrap$User r5) {
        ApiWrap$DialogInfo r0 = new ApiWrap$DialogInfo();
        TLRPC.TL_inputPeerUser r1 = new TLRPC.TL_inputPeerUser();
        r0.input = r1;
        TLRPC.InputUser r2 = r5.input;
        r1.user_id = r2.user_id;
        r1.access_hash = r2.access_hash;
        ApiWrap$ContactInfo r12 = r5.info;
        r0.name = r12.firstName;
        r0.lastName = r12.lastName;
        r0.peerId = r5.id.longValue();
        r0.topMessageDate = 0;
        r0.topMessageId = 0;
        r0.type = DialogTypeFromUser(r5);
        r0.isLeftChannel = false;
        return r0;
    }

    public static ApiWrap$DialogInfo.Type DialogTypeFromChat(ApiWrap$Chat r2) {
        if (r2 != null) goto L6;
        return ApiWrap$DialogInfo.Type.Unknown;
    L6:
        if (r2.isMonoforum == true) goto L8;
    L11:
        boolean r0 = r2.isMonoforumAdmin;
        if (r0 == true) goto L14;
    L17:
        if (r0 == true) goto L19;
        boolean r02 = r2.username.isEmpty();
        boolean r1 = r2.isBroadcast;
        if (r02 == false) goto L31;
        if (r1 == false) goto L26;
        return ApiWrap$DialogInfo.Type.PrivateChannel;
    L26:
        if (r2.isSupergroup == false) goto L30;
        return ApiWrap$DialogInfo.Type.PrivateSupergroup;
    L30:
        return ApiWrap$DialogInfo.Type.PrivateGroup;
    L31:
        if (r1 == false) goto L35;
        return ApiWrap$DialogInfo.Type.PublicChannel;
    L35:
        return ApiWrap$DialogInfo.Type.PublicSupergroup;
    L19:
        return ApiWrap$DialogInfo.Type.PrivateSupergroup;
    L14:
        if (r2.isMonoforumOfPublicBroadcast == false) goto L17;
        return ApiWrap$DialogInfo.Type.PublicSupergroup;
    L8:
        if (r2.isMonoforumAdmin == true) goto L11;
        return ApiWrap$DialogInfo.Type.Personal;
    }

    public static ApiWrap$DialogInfo.Type DialogTypeFromUser(ApiWrap$User r1) {
        if (r1.isSelf == false) goto L7;
        return ApiWrap$DialogInfo.Type.Self;
    L7:
        if (r1.isReplies == false) goto L11;
        return ApiWrap$DialogInfo.Type.Replies;
    L11:
        if (r1.isVerifyCodes == false) goto L15;
        return ApiWrap$DialogInfo.Type.VerifyCodes;
    L15:
        if (r1.isBot == false) goto L19;
        return ApiWrap$DialogInfo.Type.Bot;
    L19:
        return ApiWrap$DialogInfo.Type.Personal;
    }

    public static boolean DisplayDate(int r3, int r4) {
        if (r4 != 0) goto L6;
        return true;
    L6:
        return !Objects.equals(LocaleController.formatDate(r3), LocaleController.formatDate(r4));
    }

    private static String DocumentFolder(ApiWrap$Document r1) {
        if (r1.isVideoFile == false) goto L7;
        return "video_files";
    L7:
        if (r1.isAnimated == false) goto L11;
        return "animations";
    L11:
        if (r1.isSticker == false) goto L15;
        return "stickers";
    L15:
        if (r1.isVoiceMessage == false) goto L19;
        return "voice_messages";
    L19:
        if (r1.isVideoMessage == false) goto L22;
        return "round_video_messages";
    L22:
        return "files";
    }

    public static ApiWrap$Chat EmptyChat(long r1) {
        TLRPC.TL_chatEmpty r0 = new TLRPC.TL_chatEmpty();
        r0.id = r1;
        return ParseChat(r0);
    }

    public static ApiWrap$Peer EmptyPeer(TLRPC.Peer r5) {
        long r0 = r5.user_id;
        if (r0 != 0) goto L5;
        long r02 = r5.chat_id;
        if (r02 != 0) goto L9;
        long r03 = r5.channel_id;
        if (r03 != 0) goto L13;
        Buffer$$ExternalSyntheticBUOutline4.m("PeerId in EmptyPeer: ", ExteraConfig.getGSON().toJson(r5));
        return null;
    L13:
        return new ApiWrap$Peer(EmptyChat(r03));
    L9:
        return new ApiWrap$Peer(EmptyChat(r02));
    L5:
        return new ApiWrap$Peer(EmptyUser(r0));
    }

    public static ApiWrap$User EmptyUser(long r1) {
        TLRPC.TL_userEmpty r0 = new TLRPC.TL_userEmpty();
        r0.id = r1;
        return ParseUser(r0);
    }

    private static String FillLeft(String r2, int r3, char r4) {
        if (r3 > r2.length()) goto L5;
        return r2;
    L5:
        StringBuilder r0 = new StringBuilder();
        int r32 = r3 - r2.length();
        int r1 = 0;
    L6:
        if (r1 == r32) goto L8;
        r0.append(r4);
        r1 = r1 + 1;
        goto L6
    L8:
        r0.append(r2);
        return r0.toString();
    }

    public static void FillUserpicNames(HtmlWriter.UserpicData r2, ApiWrap$Peer r3) {
        if (r3 == null) goto L23;
        ApiWrap$User r0 = r3.user;
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r0 == null) goto L16;
        ApiWrap$ContactInfo r32 = r0.info;
        String r02 = r32.firstName;
        if (r02 != null) goto L10;
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        r2.firstName = r02;
        String r33 = r32.lastName;
        if (r33 == null) goto L13;
        r1 = r33;
    L13:
        r2.lastName = r1;
        return;
    L16:
        if (r3.chat != null) goto L18;
        return;
    L18:
        if (r3.name() == null) goto L20;
        r1 = r3.name();
    L20:
        r2.firstName = r1;
        return;
    }

    public static void FillUserpicNames(HtmlWriter.UserpicData r4, String r5) {
        String[] r52 = r5.split(" ");
        r4.firstName = r52[0];
        int r1 = 1;
    L4:
        if (r1 == r52.length) goto L13;
        if (r52[r1].isEmpty() == true) goto L12;
        StringBuilder r2 = new StringBuilder();
        if (r4.lastName.isEmpty() == true) goto L11;
        r2.append(" ");
    L11:
        r2.append(r52[r1]);
        r4.lastName = r2.toString();
    L12:
        r1 = r1 + 1;
        goto L4
    }

    public static void FinalizeDialogsInfo(ApiWrap$DialogsInfo r12, ExportSettings r13) {
        ArrayList<ApiWrap$DialogInfo> r0 = r12.chats;
        ArrayList<ApiWrap$DialogInfo> r122 = r12.left;
        int r3 = 0;
        int r1 = NumberToString(String.valueOf((r0.size() + r122.size()) - 1), 0, '0').length();
        int r5 = r0.size();
        int r6 = 0;
        int r7 = 0;
    L4:
        if (r7 >= r5) goto L16;
        ApiWrap$DialogInfo r9 = r0.get(r7);
        r7 = r7 + 1;
        ApiWrap$DialogInfo r92 = r9;
        r6 = r6 + 1;
        String r10 = NumberToString(String.valueOf(r6), r1, '0');
        if (r13.onlySinglePeer() == false) goto L8;
        String r8 = _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        r92.relativePath = r8;
        ApiWrap$DialogInfo.Type r82 = r92.type;
        if (r82 != ApiWrap$DialogInfo.Type.Personal) goto L12;
    L14:
        boolean r83 = false;
    L15:
        r92.onlyMyMessages = r83;
        Collections.sort(r92.splits);
        goto L4
    L12:
        if ((SettingsFromDialogsType(r82) & 96) == SettingsFromDialogsType(r92.type)) goto L14;
        r83 = true;
        goto L15
    L8:
        r8 = "chats/chat_" + r10 + '/';
        goto L9
    L16:
        int r132 = r122.size();
    L17:
        if (r3 >= r132) goto L19;
        ApiWrap$DialogInfo r02 = r122.get(r3);
        r3 = r3 + 1;
        ApiWrap$DialogInfo r03 = r02;
        r6 = r6 + 1;
        r03.relativePath = "chats/chat_" + NumberToString(String.valueOf(r6), r1, '0') + "/";
        r03.onlyMyMessages = true;
        goto L17
    }

    public static String FormatCustomEmoji(String r3, String r4, String r5) {
        StringBuilder r0 = new StringBuilder();
        if (r3.isEmpty() == false) goto L6;
        String r32 = "<a href=\"\" onclick=\"return ShowNotLoadedEmoji();\">";
    L9:
        r0.append(r32);
        r0.append(r4);
        r0.append("</a>");
        return r0.toString();
    L6:
        if (r3.equals(ApiWrap$TextPart.UnavailableEmoji()) == false) goto L8;
        r32 = "<a href=\"\" onclick=\"return ShowNotAvailableEmoji();\">";
        goto L9
    L8:
        r32 = "<a href = \"" + r5 + r3 + "\">";
        goto L9
    }

    public static String FormatText(ArrayList<ApiWrap$TextPart> r1, String r2, String r3) {
        return (String) r1.stream().map(new DataTypesUtils$$ExternalSyntheticLambda1(r2, r3)).collect(Collectors.joining());
    }

    public static String NoFileDescription(ApiWrap$File.SkipReason r1) {
        int r12 = AnonymousClass2.$SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[r1.ordinal()];
        if (r12 != 1) goto L5;
        return "Unavailable, please try again later.";
    L5:
        if (r12 != 2) goto L7;
        return "Exceeds maximum size, change data exporting settings to download.";
    L7:
        if (r12 != 3) goto L9;
        return "Not included, change data exporting settings to download.";
    L9:
        if (r12 != 4) goto L12;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        GlShader$$ExternalSyntheticBUOutline1.m("Skip reason in NoFileDescription.");
        return null;
    }

    public static String NumberToString(int r2) {
        return NumberToString(r2 + _UrlKt.FRAGMENT_ENCODE_SET, 0, '0');
    }

    public static String NumberToString(long r1) {
        return NumberToString(r1 + _UrlKt.FRAGMENT_ENCODE_SET, 0, '0');
    }

    public static String NumberToString(String r0, int r1, char r2) {
        return FillLeft(r0, r1, r2).replace(',', '.');
    }

    public static void ParseAttributes(ApiWrap$Document r5, ArrayList<TLRPC.DocumentAttribute> r6) {
        int r0 = r6.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L43;
        TLRPC.DocumentAttribute r2 = r6.get(r1);
        r1 = r1 + 1;
        TLRPC.DocumentAttribute r22 = r2;
        if ((r22 instanceof TLRPC.TL_documentAttributeImageSize) == true) goto L6;
        if ((r22 instanceof TLRPC.TL_documentAttributeAnimated) == true) goto L9;
        if ((r22 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L12;
        if ((r22 instanceof TLRPC.TL_documentAttributeCustomEmoji) == true) goto L15;
        if ((r22 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L18;
        if ((r22 instanceof TLRPC.TL_documentAttributeAudio) == true) goto L25;
        if ((r22 instanceof TLRPC.TL_documentAttributeFilename) == false) goto L3;
        r5.name = ((TLRPC.TL_documentAttributeFilename) r22).file_name;
        goto L3
    L25:
        TLRPC.TL_documentAttributeAudio r23 = (TLRPC.TL_documentAttributeAudio) r22;
        if (r23.voice == false) goto L28;
        r5.isVoiceMessage = true;
    L29:
        String r3 = r23.performer;
        if (r3 != null) goto L32;
    L34:
        String r32 = r23.title;
        if (r32 != null) goto L37;
    L39:
        r5.duration = (int) r23.duration;
        goto L3
    L37:
        if (r32.isEmpty() == true) goto L39;
        r5.songTitle = r32;
        goto L39
    L32:
        if (r3.isEmpty() == true) goto L34;
        r5.songPerformer = r3;
        goto L34
    L28:
        r5.isAudioFile = true;
        goto L29
    L18:
        TLRPC.TL_documentAttributeVideo r24 = (TLRPC.TL_documentAttributeVideo) r22;
        if (r24.round_message == false) goto L21;
        r5.isVideoMessage = true;
    L22:
        r5.width = r24.w;
        r5.height = r24.h;
        r5.duration = (int) r24.duration;
        goto L3
    L21:
        r5.isVideoFile = true;
        goto L22
    L15:
        r5.isSticker = true;
        r5.stickerEmoji = ((TLRPC.TL_documentAttributeCustomEmoji) r22).alt;
        goto L3
    L12:
        r5.isSticker = true;
        r5.stickerEmoji = ((TLRPC.TL_documentAttributeSticker) r22).alt;
        goto L3
    L9:
        r5.isAnimated = true;
        goto L3
    L6:
        TLRPC.TL_documentAttributeImageSize r25 = (TLRPC.TL_documentAttributeImageSize) r22;
        r5.width = r25.w;
        r5.height = r25.h;
        goto L3
    }

    public static ApiWrap$Chat ParseChat(TLRPC.Chat r5) {
        ApiWrap$Chat r0 = new ApiWrap$Chat();
        if ((r5 instanceof TLRPC.TL_chat) == false) goto L9;
        TLRPC.TL_chat r52 = (TLRPC.TL_chat) r5;
        r0.bareId = r52.id;
        r0.title = r52.title;
        TLRPC.TL_inputPeerChat r1 = new TLRPC.TL_inputPeerChat();
        r0.input = r1;
        r1.chat_id = r0.bareId;
        TLRPC.InputChannel r53 = r52.migrated_to;
        if ((r53 instanceof TLRPC.TL_inputChannel) == false) goto L44;
        r0.migratedToChannelId = ((TLRPC.TL_inputChannel) r53).channel_id;
        return r0;
    L44:
        return r0;
    L9:
        if ((r5 instanceof TLRPC.TL_chatEmpty) == false) goto L13;
        TLRPC.TL_chatEmpty r54 = (TLRPC.TL_chatEmpty) r5;
        r0.bareId = r54.id;
        TLRPC.TL_inputPeerChat r12 = new TLRPC.TL_inputPeerChat();
        r0.input = r12;
        r12.chat_id = r54.id;
        return r0;
    L13:
        if ((r5 instanceof TLRPC.TL_chatForbidden) == false) goto L17;
        TLRPC.TL_chatForbidden r55 = (TLRPC.TL_chatForbidden) r5;
        r0.bareId = r55.id;
        r0.title = r55.title;
        TLRPC.TL_inputPeerChat r13 = new TLRPC.TL_inputPeerChat();
        r0.input = r13;
        r13.chat_id = r55.id;
        return r0;
    L17:
        if ((r5 instanceof TLRPC.TL_channel) == false) goto L42;
        TLRPC.TL_channel r14 = (TLRPC.TL_channel) r5;
        long r2 = r14.id;
        r0.bareId = r2;
        TLRPC.PeerColor r4 = r14.color;
        if (r4 == null) goto L23;
        int r42 = r4.color;
        if (r42 == 0) goto L23;
    L24:
        r0.colorIndex = r42;
        r0.isMonoforum = r14.monoforum;
        boolean r22 = r14.broadcast;
        r0.isBroadcast = r22;
        r0.isSupergroup = r14.megagroup;
        if (r22 == true) goto L27;
    L33:
        boolean r23 = false;
    L34:
        r0.hasMonoforumAdminRights = r23;
        r0.monoforumLinkId = r5.linked_monoforum_id;
        r0.title = r14.title;
        String r56 = r14.username;
        if (r56 != null) goto L37;
    L39:
        TLRPC.TL_inputPeerChannel r57 = new TLRPC.TL_inputPeerChannel();
        r0.input = r57;
        r57.channel_id = r14.id;
        r57.access_hash = r14.access_hash;
        return r0;
    L37:
        if (r56.isEmpty() == true) goto L39;
        r0.username = r14.username;
        goto L39
    L27:
        if (r5.creator == true) goto L32;
        TLRPC.TL_chatAdminRights r24 = r5.admin_rights;
        if (r24 == null) goto L33;
        if (r24.manage_direct_messages == false) goto L33;
    L32:
        r23 = true;
    L23:
        r42 = PeerColorIndex(r2);
        goto L24
    L42:
        if ((r5 instanceof TLRPC.TL_channelForbidden) == false) goto L44;
        TLRPC.TL_channelForbidden r58 = (TLRPC.TL_channelForbidden) r5;
        r0.bareId = r58.id;
        r0.isBroadcast = r58.broadcast;
        r0.isSupergroup = r58.megagroup;
        r0.title = r58.title;
        TLRPC.TL_inputPeerChannel r15 = new TLRPC.TL_inputPeerChannel();
        r0.input = r15;
        r15.channel_id = r58.id;
        r15.access_hash = r58.access_hash;
        goto L44
    }

    public static ApiWrap$ContactInfo ParseContactInfo(TLRPC.User r3) {
        ApiWrap$ContactInfo r0 = new ApiWrap$ContactInfo();
        if ((r3 instanceof TLRPC.TL_user) == false) goto L24;
        TLRPC.TL_user r32 = (TLRPC.TL_user) r3;
        Long r1 = Long.valueOf(r32.id);
        r0.userId = r1;
        TLRPC.PeerColor r2 = r32.color;
        if (r2 == null) goto L9;
        int r22 = r2.color;
        if (r22 == 0) goto L9;
    L10:
        r0.colorIndex = r22;
        String r12 = r32.first_name;
        String r23 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r12 != null) goto L14;
        r12 = _UrlKt.FRAGMENT_ENCODE_SET;
    L14:
        r0.firstName = r12;
        String r13 = r32.last_name;
        if (r13 != null) goto L18;
        r13 = _UrlKt.FRAGMENT_ENCODE_SET;
    L18:
        r0.lastName = r13;
        String r33 = r32.phone;
        if (r33 == null) goto L21;
        r23 = r33;
    L21:
        r0.phoneNumber = r23;
        return r0;
    L9:
        r22 = PeerColorIndex(r1.longValue());
        goto L10
    L24:
        if ((r3 instanceof TLRPC.TL_userEmpty) == false) goto L26;
        Long r34 = Long.valueOf(((TLRPC.TL_userEmpty) r3).id);
        r0.userId = r34;
        r0.colorIndex = PeerColorIndex(r34.longValue());
    L26:
        return r0;
    }

    public static ApiWrap$ContactsList ParseContactsList(Vector<ExportRequests$SavedContact> r7) {
        ApiWrap$ContactsList r0 = new ApiWrap$ContactsList();
        r0.list.ensureCapacity(r7.objects.size());
        ArrayList<T> r72 = r7.objects;
        int r1 = r72.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        Object r3 = r72.get(r2);
        r2 = r2 + 1;
        ExportRequests$SavedContact r32 = (ExportRequests$SavedContact) r3;
        ApiWrap$ContactInfo r4 = new ApiWrap$ContactInfo();
        r4.firstName = r32.first_name;
        r4.lastName = r32.last_name;
        String r5 = r32.phone;
        r4.phoneNumber = r5;
        r4.date = r32.date;
        r4.colorIndex = PeerColorIndex(StringBarePeerId(r5));
        r0.list.add(r4);
        goto L3
    L5:
        return r0;
    }

    public static ApiWrap$Document ParseDocument(ApiWrap$ParseMediaContext r4, TLRPC.Document r5, String r6, int r7) {
        ApiWrap$Document r0 = new ApiWrap$Document();
        if ((r5 instanceof TLRPC.TL_document) == false) goto L9;
        TLRPC.TL_document r52 = (TLRPC.TL_document) r5;
        r0.id = r52.id;
        r0.date = r52.date;
        r0.mime = r52.mime_type;
        ParseAttributes(r0, r52.attributes);
        ApiWrap$File r1 = new ApiWrap$File();
        r0.file = r1;
        r1.size = r52.size;
        r1.dcId = r52.dc_id;
        TLRPC.TL_inputDocumentFileLocation r12 = new TLRPC.TL_inputDocumentFileLocation();
        r12.id = r52.id;
        r12.access_hash = r52.access_hash;
        r12.file_reference = r52.file_reference;
        r12.thumb_size = _UrlKt.FRAGMENT_ENCODE_SET;
        r0.file.location = new ApiWrap$FileLocation();
        ApiWrap$File r2 = r0.file;
        ApiWrap$FileLocation r3 = r2.location;
        r3.data = r12;
        r3.dcId = r52.dc_id;
        r2.suggestedPath = r6 + DocumentFolder(r0) + "/" + FileManager.fileNameFromUserString(ComputeDocumentName(r4, r52, r7, r0.name));
        r0.thumb = ParseDocumentThumb(r52, r0.file.suggestedPath);
        if (MessageObject.isStickerDocument(r52) == false) goto L11;
        r0.sticker = r52;
        return r0;
    L11:
        return r0;
    L9:
        if ((r5 instanceof TLRPC.TL_documentEmpty) == false) goto L11;
        r0.id = ((TLRPC.TL_documentEmpty) r5).id;
        goto L11
    }

    private static ApiWrap$Image ParseDocumentThumb(TLRPC.Document r10, String r11) {
        ArrayList<TLRPC.PhotoSize> r0 = r10.thumbs;
        if (r0.isEmpty() == true) goto L5;
        int r1 = r0.size();
        TLRPC.PhotoSize r3 = null;
        long r4 = Long.MIN_VALUE;
        int r6 = 0;
    L7:
        if (r6 >= r1) goto L11;
        TLRPC.PhotoSize r7 = r0.get(r6);
        r6 = r6 + 1;
        TLRPC.PhotoSize r72 = r7;
        if (getArea(r72) <= r4) goto L7;
        r4 = getArea(r72);
        r3 = r72;
        goto L7
    L11:
        if (r3 != null) goto L15;
        return new ApiWrap$Image();
    L15:
        if ((r3 instanceof TLRPC.TL_photoSizeEmpty) == true) goto L35;
        if ((r3 instanceof TLRPC.TL_photoStrippedSize) == true) goto L35;
        if ((r3 instanceof TLRPC.TL_photoPathSize) == true) goto L35;
        ApiWrap$Image r02 = new ApiWrap$Image();
        r02.width = r3.w;
        r02.height = r3.h;
        TLRPC.TL_inputDocumentFileLocation r12 = new TLRPC.TL_inputDocumentFileLocation();
        r12.id = r10.id;
        r12.access_hash = r10.access_hash;
        r12.file_reference = r10.file_reference;
        r12.thumb_size = r3.type;
        r02.file.location = new ApiWrap$FileLocation();
        ApiWrap$File r42 = r02.file;
        ApiWrap$FileLocation r5 = r42.location;
        r5.data = r12;
        r5.dcId = r10.dc_id;
        if ((r3 instanceof TLRPC.TL_photoCachedSize) == false) goto L25;
        r42.content = ((TLRPC.TL_photoCachedSize) r3).bytes;
        r42.size = r3.size;
    L32:
        r02.file.suggestedPath = r11 + "_thumb.jpg";
        return r02;
    L25:
        if ((r3 instanceof TLRPC.TL_photoSizeProgressive) == false) goto L31;
        TLRPC.TL_photoSizeProgressive r32 = (TLRPC.TL_photoSizeProgressive) r3;
        if (r32.sizes.isEmpty() == true) goto L29;
        ApiWrap$File r102 = r02.file;
        r102.content = new byte[0];
        ArrayList<Integer> r13 = r32.sizes;
        r102.size = r13.get(r13.size() - 1).intValue();
        goto L32
    L29:
        return new ApiWrap$Image();
    L31:
        r42.content = new byte[0];
        r42.size = r10.size;
    L35:
        return new ApiWrap$Image();
    L5:
        return new ApiWrap$Image();
    }

    public static ApiWrap$DialogsInfo ParseLeftChannelsInfo(TLRPC.messages_Chats r5) {
        ApiWrap$DialogsInfo r0 = new ApiWrap$DialogsInfo();
        ArrayList<TLRPC.Chat> r52 = r5.chats;
        int r1 = r52.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        TLRPC.Chat r3 = r52.get(r2);
        r2 = r2 + 1;
        ApiWrap$DialogInfo r32 = DialogInfoFromChat(ParseChat(r3));
        r32.isLeftChannel = true;
        r0.left.add(r32);
        goto L3
    L5:
        return r0;
    }

    private static ApiWrap$Image ParseMaxImage(TLRPC.Photo r12, String r13) {
        if (r12 != null) goto L5;
        return null;
    L5:
        ApiWrap$Image r0 = new ApiWrap$Image();
        r0.file.suggestedPath = r13;
        ArrayList<TLRPC.PhotoSize> r132 = r12.sizes;
        int r1 = r132.size();
        long r3 = 0;
        int r5 = 0;
    L6:
        if (r5 >= r1) goto L29;
        TLRPC.PhotoSize r6 = r132.get(r5);
        r5 = r5 + 1;
        TLRPC.PhotoSize r62 = r6;
        if ((r62 instanceof TLRPC.TL_photoSizeEmpty) == true) goto L6;
        if ((r62 instanceof TLRPC.TL_photoStrippedSize) == true) goto L6;
        if ((r62 instanceof TLRPC.TL_photoPathSize) == true) goto L6;
        int r7 = r62.w;
        int r8 = r62.h;
        long r9 = r7 * r8;
        if (r9 <= r3) goto L6;
        r0.width = r7;
        r0.height = r8;
        TLRPC.TL_inputPhotoFileLocation r32 = new TLRPC.TL_inputPhotoFileLocation();
        r32.id = r12.id;
        r32.access_hash = r12.access_hash;
        r32.file_reference = r12.file_reference;
        r32.thumb_size = r62.type;
        r0.file.location = new ApiWrap$FileLocation();
        ApiWrap$File r4 = r0.file;
        ApiWrap$FileLocation r72 = r4.location;
        r72.data = r32;
        r72.dcId = r12.dc_id;
        if ((r62 instanceof TLRPC.TL_photoCachedSize) == false) goto L22;
        r4.content = ((TLRPC.TL_photoCachedSize) r62).bytes;
        r4.size = r3.length;
    L28:
        r3 = r9;
        goto L6
    L22:
        if ((r62 instanceof TLRPC.TL_photoSizeProgressive) == false) goto L27;
        TLRPC.TL_photoSizeProgressive r63 = (TLRPC.TL_photoSizeProgressive) r62;
        if (r63.sizes.isEmpty() == true) goto L28;
        ApiWrap$File r33 = r0.file;
        r33.content = new byte[0];
        ArrayList<Integer> r42 = r63.sizes;
        r33.size = r42.get(r42.size() - 1).intValue();
        goto L28
    L27:
        r4.content = new byte[0];
        r4.size = r62.size;
        goto L28
    L29:
        return r0;
    }

    private static ApiWrap$Media ParseMedia(ApiWrap$ParseMediaContext r9, TLRPC.MessageMedia r10, String r11, int r12) {
        ApiWrap$Media r0 = new ApiWrap$Media();
        if ((r10 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L14;
        TLRPC.Photo r1 = ((TLRPC.TL_messageMediaPhoto) r10).photo;
        if (r1 == null) goto L7;
        StringBuilder r2 = new StringBuilder();
        r2.append(r11);
        r2.append("photos/");
        int r112 = r9.photos + 1;
        r9.photos = r112;
        r2.append(PreparePhotoFileName(r112, r12));
        HtmlWriter.Photo r92 = ParsePhoto(r1, r2.toString());
    L8:
        r92.spoilered = r10.spoiler;
        int r102 = r10.ttl_seconds;
        if (r102 == 0) goto L11;
        r0.ttl = r102;
        r92.image.file = new ApiWrap$File();
    L11:
        r0.content = r92;
        return r0;
    L7:
        r92 = new HtmlWriter.Photo();
        goto L8
    L14:
        if ((r10 instanceof TLRPC.TL_messageMediaGeo) == false) goto L18;
        r0.content = parseGeoPoint(((TLRPC.TL_messageMediaGeo) r10).geo);
        return r0;
    L18:
        if ((r10 instanceof TLRPC.TL_messageMediaContact) == false) goto L27;
        TLRPC.TL_messageMediaContact r122 = (TLRPC.TL_messageMediaContact) r10;
        ApiWrap$SharedContact r13 = new ApiWrap$SharedContact();
        r13.info.userId = Long.valueOf(r122.user_id);
        ApiWrap$ContactInfo r22 = r13.info;
        r22.firstName = r10.first_name;
        r22.lastName = r10.last_name;
        r22.phoneNumber = r10.phone_number;
        String r103 = r122.vcard;
        if (r103 != null) goto L22;
    L24:
        r0.content = r13;
        return r0;
    L22:
        if (r103.isEmpty() == true) goto L24;
        ApiWrap$File r104 = new ApiWrap$File();
        r13.vcard = r104;
        r104.content = r122.vcard.getBytes(StandardCharsets.UTF_8);
        r13.vcard.size = r122.vcard.length();
        ApiWrap$File r105 = r13.vcard;
        StringBuilder r123 = new StringBuilder();
        r123.append(r11);
        r123.append("contacts/contact_");
        int r113 = r9.contacts + 1;
        r9.contacts = r113;
        r123.append(r113);
        r123.append(".vcard");
        r105.suggestedPath = r123.toString();
        goto L24
    L27:
        if ((r10 instanceof TLRPC.TL_messageMediaUnsupported) == false) goto L30;
        return r0;
    L30:
        if ((r10 instanceof TLRPC.TL_messageMediaDocument) == false) goto L41;
        TLRPC.TL_messageMediaDocument r106 = (TLRPC.TL_messageMediaDocument) r10;
        TLRPC.Document r14 = r106.document;
        if (r14 == null) goto L34;
        ApiWrap$Document r93 = ParseDocument(r9, r14, r11, r12);
    L35:
        int r114 = r106.ttl_seconds;
        if (r114 == 0) goto L38;
        r0.ttl = r114;
        r93.file = new ApiWrap$File();
    L38:
        r93.spoilered = r106.spoiler;
        r0.content = r93;
        return r0;
    L34:
        r93 = new ApiWrap$Document();
        goto L35
    L41:
        if ((r10 instanceof TLRPC.TL_messageMediaVenue) == false) goto L45;
        TLRPC.TL_messageMediaVenue r107 = (TLRPC.TL_messageMediaVenue) r10;
        ApiWrap$Venue r94 = new ApiWrap$Venue();
        r94.point = parseGeoPoint(r107.geo);
        r94.title = r107.title;
        r94.address = r107.address;
        r0.content = r94;
        return r0;
    L45:
        if ((r10 instanceof TLRPC.TL_messageMediaGame) == false) goto L52;
        ApiWrap$Game r115 = new ApiWrap$Game();
        TLRPC.TL_game r108 = ((TLRPC.TL_messageMediaGame) r10).game;
        if (r108 == null) goto L49;
        r115.id = r108.id;
        r115.title = r108.title;
        r115.description = r108.description;
        r115.shortName = r108.short_name;
        r115.botId = r9.botId;
    L49:
        r0.content = r115;
        return r0;
    L52:
        if ((r10 instanceof TLRPC.TL_messageMediaInvoice) == false) goto L59;
        TLRPC.TL_messageMediaInvoice r109 = (TLRPC.TL_messageMediaInvoice) r10;
        ApiWrap$Invoice r95 = new ApiWrap$Invoice();
        r95.title = r109.title;
        r95.description = r109.description;
        r95.currency = r109.currency;
        r95.amount = r109.total_amount;
        int r1010 = r109.receipt_msg_id;
        if (r1010 == 0) goto L56;
        r95.receiptMsgId = r1010;
    L56:
        r0.content = r95;
        return r0;
    L59:
        if ((r10 instanceof TLRPC.TL_messageMediaGeoLive) == false) goto L62;
        TLRPC.TL_messageMediaGeoLive r1011 = (TLRPC.TL_messageMediaGeoLive) r10;
        r0.content = parseGeoPoint(r1011.geo);
        r0.ttl = r1011.period;
        return r0;
    L62:
        int r23 = 0;
        if ((r10 instanceof TLRPC.TL_messageMediaPoll) == false) goto L85;
        TLRPC.TL_messageMediaPoll r1012 = (TLRPC.TL_messageMediaPoll) r10;
        ApiWrap$Poll r96 = new ApiWrap$Poll();
        TLRPC.Poll r116 = r1012.poll;
        if ((r116 instanceof TLRPC.TL_poll) == false) goto L82;
        TLRPC.TL_poll r117 = (TLRPC.TL_poll) r116;
        r96.id = r117.id;
        r96.question = r117.question.text;
        r96.closed = r117.closed;
        HashMap r124 = new HashMap();
        TLRPC.PollResults r1013 = r1012.results;
        if ((r1013 instanceof TLRPC.TL_pollResults) == false) goto L73;
        TLRPC.TL_pollResults r1014 = (TLRPC.TL_pollResults) r1013;
        ArrayList<TLRPC.PollAnswerVoters> r15 = r1014.results;
        if (r15 == null) goto L73;
        r96.totalVotes = r1014.total_voters;
        int r1015 = r15.size();
        int r3 = 0;
    L71:
        if (r3 >= r1015) goto L73;
        TLRPC.PollAnswerVoters r4 = r15.get(r3);
        r3 = r3 + 1;
        TLRPC.PollAnswerVoters r42 = r4;
        r124.put(Base64.encodeToString(r42.option, 0), r42);
    L73:
        ArrayList<TLRPC.PollAnswer> r1016 = r117.answers;
        int r118 = r1016.size();
        int r16 = 0;
    L74:
        if (r16 >= r118) goto L82;
        TLRPC.PollAnswer r32 = r1016.get(r16);
        r16 = r16 + 1;
        TLRPC.PollAnswer r33 = r32;
        if ((r33 instanceof TLRPC.TL_pollAnswer) == false) goto L74;
        TLRPC.TL_pollAnswer r34 = (TLRPC.TL_pollAnswer) r33;
        String r43 = r34.text.text;
        byte[] r35 = r34.option;
        TLRPC.PollAnswerVoters r5 = (TLRPC.PollAnswerVoters) r124.get(Base64.encodeToString(r35, 0));
        if (r5 == null) goto L80;
        int r6 = r5.voters;
        boolean r52 = r5.chosen;
    L81:
        r96.answers.add(new ApiWrap$Poll.Answer(r43, r35, r6, r52));
        goto L74
    L80:
        r52 = false;
        r6 = 0;
    L82:
        r0.content = r96;
        return r0;
    L85:
        if ((r10 instanceof TLRPC.TL_messageMediaGiveaway) == false) goto L89;
        r0.content = parseGiveaway((TLRPC.TL_messageMediaGiveaway) r10);
        return r0;
    L89:
        if ((r10 instanceof TLRPC.TL_messageMediaGiveawayResults) == false) goto L93;
        r0.content = parseGiveaway((TLRPC.TL_messageMediaGiveawayResults) r10);
        return r0;
    L93:
        if ((r10 instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L103;
        TLRPC.TL_messageMediaPaidMedia r1017 = (TLRPC.TL_messageMediaPaidMedia) r10;
        ApiWrap$PaidMedia r17 = new ApiWrap$PaidMedia();
        r17.stars = r1017.stars_amount;
        ArrayList<TLRPC.MessageExtendedMedia> r1018 = r1017.extended_media;
        int r36 = r1018.size();
    L95:
        if (r23 >= r36) goto L102;
        TLRPC.MessageExtendedMedia r44 = r1018.get(r23);
        r23 = r23 + 1;
        TLRPC.MessageExtendedMedia r45 = r44;
        if ((r45 instanceof TLRPC.TL_messageExtendedMediaPreview) == true) goto L98;
        if ((r45 instanceof TLRPC.TL_messageExtendedMedia) == false) goto L95;
        r17.extended.add(ParseMedia(r9, ((TLRPC.TL_messageExtendedMedia) r45).media, r11, r12));
        goto L95
    L98:
        r17.extended.add(new ApiWrap$Media());
        goto L95
    L102:
        r0.content = r17;
    L103:
        return r0;
    }

    public static ApiWrap$Message ParseMessage(ApiWrap$ParseMediaContext r11, TLRPC.Message r12, String r13) {
        ApiWrap$Message r0 = new ApiWrap$Message();
        r0.id = r12.id;
        if ((r12 instanceof TLRPC.TL_messageEmpty) == true) goto L21;
        r0.date = r12.date;
        r0.out = r12.out;
        r0.selfId = r11.selfPeerId;
        long r4 = MessageObject.getPeerId(r12.peer_id);
        r0.peerId = r4;
        TLRPC.Peer r1 = r12.from_id;
        if (r1 == null) goto L7;
        r0.fromId = MessageObject.getPeerId(r1);
    L8:
        TLRPC.MessageReplyHeader r14 = r12.reply_to;
        if (r14 == null) goto L21;
        if ((r14 instanceof TLRPC.TL_messageReplyHeader) == false) goto L21;
        TLRPC.TL_messageReplyHeader r15 = (TLRPC.TL_messageReplyHeader) r14;
        int r42 = r15.reply_to_msg_id;
        if (r42 == 0) goto L21;
        r0.replyToMsgId = r42;
        TLRPC.Peer r16 = r15.reply_to_peer_id;
        if (r16 == null) goto L17;
        long r43 = MessageObject.getPeerId(r16);
    L18:
        r0.replyToPeerId = r43;
        if (r43 != r0.peerId) goto L21;
        r0.replyToPeerId = 0;
        goto L21
    L17:
        r43 = 0;
        goto L18
    L7:
        r0.fromId = r4;
    L21:
        boolean r44 = true;
        if ((r12 instanceof TLRPC.TL_message) == false) goto L73;
        TLRPC.TL_message r17 = (TLRPC.TL_message) r12;
        TLRPC.MessageFwdHeader r5 = r17.fwd_from;
        if ((r5 instanceof TLRPC.TL_messageFwdHeader) == false) goto L45;
        TLRPC.TL_messageFwdHeader r52 = (TLRPC.TL_messageFwdHeader) r5;
        String r6 = r52.from_name;
        if (r6 != null) goto L28;
    L30:
        String r62 = _UrlKt.FRAGMENT_ENCODE_SET;
    L32:
        if (MessageObject.getPeerId(r52.from_id) == 0) goto L34;
    L37:
        boolean r7 = true;
    L38:
        r0.forwarded = r7;
        r0.forwardedDate = r52.date;
        if (r7 == true) goto L41;
    L43:
        r44 = false;
    L44:
        r0.showForwardedAsOriginal = r44;
        r0.savedFromChatId = MessageObject.getPeerId(r52.saved_from_id);
        r0.forwardedFromName = r62;
        goto L45
    L41:
        if (MessageObject.getPeerId(r52.saved_from_id) == 0) goto L43;
    L34:
        if (r62.isEmpty() == false) goto L37;
        r7 = false;
        goto L38
    L28:
        if (r6.isEmpty() == true) goto L30;
        r62 = r52.from_name;
    L45:
        String r45 = r12.post_author;
        if (r45 == null) goto L48;
        r0.signature = r45;
    L48:
        TLRPC.MessageReplyHeader r122 = r12.reply_to;
        if (r122 != null) goto L51;
    L59:
        long r46 = r17.via_bot_id;
        if (r46 == 0) goto L62;
        r0.viaBotId = r46;
    L62:
        TLRPC.MessageMedia r123 = r17.media;
        if (r123 == null) goto L65;
        r0.media = ParseMedia(r11, r123, r13, r0.date);
    L65:
        TLRPC.ReplyMarkup r112 = r17.reply_markup;
        if (r112 != null) goto L68;
    L70:
        r0.text = ParseText(r17.message, r17.entities);
        return r0;
    L68:
        if ((r112 instanceof TLRPC.TL_replyKeyboardMarkup) == false) goto L70;
        r0.inlineButtonRows = ButtonRowsFromTL((TLRPC.TL_replyKeyboardMarkup) r112);
        goto L70
    L51:
        if ((r122 instanceof TLRPC.TL_messageReplyHeader) == false) goto L59;
        TLRPC.TL_messageReplyHeader r124 = (TLRPC.TL_messageReplyHeader) r122;
        int r47 = r124.reply_to_msg_id;
        if (r47 == 0) goto L59;
        r0.replyToMsgId = r47;
        TLRPC.Peer r125 = r124.reply_to_peer_id;
        if (r125 == null) goto L57;
        long r48 = MessageObject.getPeerId(r125);
    L58:
        r0.replyToPeerId = r48;
        goto L59
    L57:
        r48 = 0;
        goto L58
    L73:
        if ((r12 instanceof TLRPC.TL_messageService) == false) goto L83;
        TLRPC.TL_messageService r18 = (TLRPC.TL_messageService) r12;
        TLRPC.MessageAction r2 = r18.action;
        if ((r2 instanceof TLRPC.TL_messageActionSuggestProfilePhoto) == false) goto L78;
        TLRPC.Photo r22 = ((TLRPC.TL_messageActionSuggestProfilePhoto) r2).photo;
        StringBuilder r3 = new StringBuilder();
        r3.append(r13);
        r3.append("photos/");
        int r132 = r11.photos + 1;
        r11.photos = r132;
        r3.append(PreparePhotoFileName(r132, r12.date));
        r0.parsedAction = new ApiWrap$ActionSuggestProfilePhoto(ParsePhoto(r22, r3.toString()));
    L80:
        TLRPC.MessageAction r113 = r18.action;
        if (r113 == null) goto L83;
        r0.action = r113;
        goto L83
    L78:
        if ((r2 instanceof TLRPC.TL_messageActionChatEditPhoto) == false) goto L80;
        TLRPC.Photo r23 = ((TLRPC.TL_messageActionChatEditPhoto) r2).photo;
        StringBuilder r32 = new StringBuilder();
        r32.append(r13);
        r32.append("photos/");
        int r133 = r11.photos + 1;
        r11.photos = r133;
        r32.append(PreparePhotoFileName(r133, r12.date));
        r0.parsedAction = new ApiWrap$ActionChatEditPhoto(ParsePhoto(r23, r32.toString()));
    L83:
        return r0;
    }

    public static ApiWrap$MessagesSlice ParseMessagesSlice(ApiWrap$ParseMediaContext r4, ArrayList<TLRPC.Message> r5, ArrayList<TLRPC.User> r6, ArrayList<TLRPC.Chat> r7, String r8) {
        ApiWrap$MessagesSlice r0 = new ApiWrap$MessagesSlice();
        int r1 = r5.size();
    L3:
        if (r1 == 0) goto L5;
        r1 = r1 - 1;
        r0.list.add(ParseMessage(r4, r5.get(r1), r8));
        goto L3
    L5:
        r0.peers = ParsePeersLists(r6, r7);
        return r0;
    }

    public static HashMap<Long, ApiWrap$Peer> ParsePeersLists(ArrayList<TLRPC.User> r7, ArrayList<TLRPC.Chat> r8) {
        LinkedHashMap r0 = new LinkedHashMap();
        int r1 = r7.size();
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        TLRPC.User r4 = r7.get(r3);
        r3 = r3 + 1;
        ApiWrap$User r42 = ParseUser(r4);
        r0.put(r42.info.userId, new ApiWrap$Peer(r42));
        goto L3
    L5:
        int r72 = r8.size();
    L6:
        if (r2 >= r72) goto L8;
        TLRPC.Chat r12 = r8.get(r2);
        r2 = r2 + 1;
        ApiWrap$Chat r13 = ParseChat(r12);
        r0.put(Long.valueOf(r13.bareId), new ApiWrap$Peer(r13));
        goto L6
    L8:
        Iterator r73 = r0.values().iterator();
    L10:
        if (r73.hasNext() == false) goto L18;
        ApiWrap$Peer r82 = (ApiWrap$Peer) r73.next();
        ApiWrap$Chat r14 = r82.chat;
        if (r14 == null) goto L10;
        if (r14.isMonoforum == false) goto L10;
        ApiWrap$Peer r15 = (ApiWrap$Peer) r0.get(Long.valueOf(r14.monoforumLinkId));
        if (r15 == null) goto L10;
        ApiWrap$Chat r83 = r82.chat;
        r83.isMonoforumAdmin = r15.chat.hasMonoforumAdminRights;
        r83.isMonoforumOfPublicBroadcast = !TextUtils.isEmpty(r1.username);
        goto L10
    L18:
        return r0;
    }

    public static ApiWrap$ExportPersonalInfo ParsePersonalInfo(TLRPC.TL_users_userFull r3) {
        ApiWrap$ExportPersonalInfo r0 = new ApiWrap$ExportPersonalInfo();
        r0.user = ParseUser(r3.users.get(0));
        TLRPC.UserFull r32 = r3.full_user;
        if ((r32 instanceof TLRPC.TL_userFull) == false) goto L7;
        String r33 = ((TLRPC.TL_userFull) r32).about;
        if (r33 == null) goto L7;
        r0.bio = r33;
    L7:
        return r0;
    }

    public static HtmlWriter.Photo ParsePhoto(TLRPC.Photo r3, String r4) {
        HtmlWriter.Photo r0 = new HtmlWriter.Photo();
        if ((r3 instanceof TLRPC.TL_photoEmpty) == false) goto L7;
        r0.id = ((TLRPC.TL_photoEmpty) r3).id;
        return r0;
    L7:
        if ((r3 instanceof TLRPC.TL_photo) == false) goto L9;
        TLRPC.TL_photo r32 = (TLRPC.TL_photo) r3;
        r0.id = r32.id;
        r0.date = r32.date;
        r0.image = ParseMaxImage(r32, r4);
    L9:
        return r0;
    }

    public static ApiWrap$SessionsList ParseSessionsList(TL_account.authorizations r2) {
        ApiWrap$SessionsList r0 = new ApiWrap$SessionsList();
        r0.list.addAll(r2.authorizations);
        return r0;
    }

    public static ApiWrap$StoriesSlice ParseStoriesSlice(ArrayList<TL_stories.StoryItem> r12, int r13) {
        ApiWrap$StoriesSlice r0 = new ApiWrap$StoriesSlice();
        int r1 = r12.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L33;
        TL_stories.StoryItem r3 = r12.get(r2);
        r2 = r2 + 1;
        TL_stories.StoryItem r32 = r3;
        r0.lastId = r32.id;
        r0.skipped++;
        int r4 = r32.date;
        ApiWrap$Media r5 = new ApiWrap$Media();
        TLRPC.MessageMedia r6 = r32.media;
        String r8 = ".jpg";
        if ((r6 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L12;
        TLRPC.TL_messageMediaPhoto r62 = (TLRPC.TL_messageMediaPhoto) r6;
        StringBuilder r7 = new StringBuilder("stories/");
        r13 = r13 + 1;
        r7.append(PrepareStoryFileName(r13, r4, ".jpg"));
        String r72 = r7.toString();
        TLRPC.Photo r82 = r62.photo;
        if (r82 == null) goto L9;
        HtmlWriter.Photo r73 = ParsePhoto(r82, r72);
    L10:
        r73.spoilered = r62.spoiler;
        r5.content = r73;
    L27:
        if ((r5.content instanceof ApiWrap$UnsupportedMedia) == true) goto L3;
        ApiWrap$Story r63 = new ApiWrap$Story();
        r63.id = r32.id;
        r63.date = r4;
        r63.expires = r32.expire_date;
        r63.media = r5;
        r63.pinned = r32.pinned;
        String r42 = r32.caption;
        if (r42 == null) goto L31;
        ArrayList<ApiWrap$TextPart> r33 = ParseText(r42, r32.entities);
    L32:
        r63.caption = r33;
        r0.list.add(r63);
        r0.skipped--;
        goto L3
    L31:
        r33 = new ArrayList();
        goto L32
    L9:
        r73 = new HtmlWriter.Photo();
        goto L10
    L12:
        if ((r6 instanceof TLRPC.TL_messageMediaDocument) == false) goto L25;
        TLRPC.TL_messageMediaDocument r64 = (TLRPC.TL_messageMediaDocument) r6;
        TLRPC.Document r74 = r64.document;
        ApiWrap$ParseMediaContext r10 = new ApiWrap$ParseMediaContext();
        if (r74 == null) goto L16;
        ApiWrap$Document r75 = ParseDocument(r10, r74, "stories", r4);
    L18:
        if (r75.mime.equals("image/jpeg") == false) goto L21;
    L24:
        ApiWrap$File r102 = r75.file;
        StringBuilder r11 = new StringBuilder("stories/");
        r13 = r13 + 1;
        r11.append(PrepareStoryFileName(r13, r4, r8));
        String r83 = r11.toString();
        r102.suggestedPath = r83;
        r75.thumb.file.suggestedPath = r83.concat("_thumb.jpg");
        r75.spoilered = r64.spoiler;
        r5.content = r75;
        goto L27
    L21:
        if (r75.mime.equals("image/png") == false) goto L23;
        r8 = ".png";
        goto L24
    L23:
        r8 = getExtensionFromMime(r75.mime, null);
        goto L24
    L16:
        r75 = new ApiWrap$Document();
        goto L18
    L25:
        r5.content = new ApiWrap$UnsupportedMedia();
        goto L27
    L33:
        return r0;
    }

    public static ArrayList<ApiWrap$TextPart> ParseText(String r10, ArrayList<TLRPC.MessageEntity> r11) {
        int r0 = r10.length();
        ArrayList<ApiWrap$TextPart> r1 = new ArrayList();
        int r2 = r11.size();
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L89;
        TLRPC.MessageEntity r5 = r11.get(r4);
        r4 = r4 + 1;
        TLRPC.MessageEntity r52 = r5;
        int r6 = r52.offset;
        int r7 = r52.length;
        if (r6 < r3) goto L3;
        if (r7 <= 0) goto L3;
        int r72 = r7 + r6;
        if (r72 > r0) goto L3;
        addTextPart(r6, r3, r10, r1);
        ApiWrap$TextPart r32 = new ApiWrap$TextPart();
        ApiWrap$TextPart.Type r8 = ApiWrap$TextPart.Type.Unknown;
        if ((r52 instanceof TLRPC.TL_messageEntityMention) == false) goto L14;
        r8 = ApiWrap$TextPart.Type.Mention;
    L70:
        r32.type = r8;
        r32.text = r10.substring(r6, r72);
        if ((r52 instanceof TLRPC.TL_messageEntityPre) == false) goto L74;
        String r53 = ((TLRPC.TL_messageEntityPre) r52).language;
    L88:
        r32.additional = r53;
        r1.add(r32);
        r3 = r72;
        goto L3
    L74:
        if ((r52 instanceof TLRPC.TL_messageEntityTextUrl) == false) goto L77;
        r53 = ((TLRPC.TL_messageEntityTextUrl) r52).url;
        goto L88
    L77:
        if ((r52 instanceof TLRPC.TL_messageEntityMentionName) == false) goto L80;
        r53 = String.valueOf(((TLRPC.TL_messageEntityMentionName) r52).user_id);
        goto L88
    L80:
        if ((r52 instanceof TLRPC.TL_messageEntityCustomEmoji) == false) goto L83;
        r53 = String.valueOf(((TLRPC.TL_messageEntityCustomEmoji) r52).document_id);
        goto L88
    L83:
        if ((r52 instanceof TLRPC.TL_messageEntityBlockquote) == true) goto L85;
    L87:
        r53 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L88
    L85:
        if (((TLRPC.TL_messageEntityBlockquote) r52).collapsed == false) goto L87;
        r53 = "1";
        goto L88
    L14:
        if ((r52 instanceof TLRPC.TL_messageEntityHashtag) == false) goto L17;
        r8 = ApiWrap$TextPart.Type.Hashtag;
        goto L70
    L17:
        if ((r52 instanceof TLRPC.TL_messageEntityBotCommand) == false) goto L20;
        r8 = ApiWrap$TextPart.Type.BotCommand;
        goto L70
    L20:
        if ((r52 instanceof TLRPC.TL_messageEntityUrl) == false) goto L23;
        r8 = ApiWrap$TextPart.Type.Url;
        goto L70
    L23:
        if ((r52 instanceof TLRPC.TL_messageEntityEmail) == false) goto L26;
        r8 = ApiWrap$TextPart.Type.Email;
        goto L70
    L26:
        if ((r52 instanceof TLRPC.TL_messageEntityBold) == false) goto L29;
        r8 = ApiWrap$TextPart.Type.Bold;
        goto L70
    L29:
        if ((r52 instanceof TLRPC.TL_messageEntityItalic) == false) goto L32;
        r8 = ApiWrap$TextPart.Type.Italic;
        goto L70
    L32:
        if ((r52 instanceof TLRPC.TL_messageEntityCode) == false) goto L35;
        r8 = ApiWrap$TextPart.Type.Code;
        goto L70
    L35:
        if ((r52 instanceof TLRPC.TL_messageEntityPre) == false) goto L38;
        r8 = ApiWrap$TextPart.Type.Pre;
        goto L70
    L38:
        if ((r52 instanceof TLRPC.TL_messageEntityTextUrl) == false) goto L41;
        r8 = ApiWrap$TextPart.Type.TextUrl;
        goto L70
    L41:
        if ((r52 instanceof TLRPC.TL_messageEntityMentionName) == false) goto L44;
        r8 = ApiWrap$TextPart.Type.MentionName;
        goto L70
    L44:
        if ((r52 instanceof TLRPC.TL_inputMessageEntityMentionName) == false) goto L47;
        r8 = ApiWrap$TextPart.Type.MentionName;
        goto L70
    L47:
        if ((r52 instanceof TLRPC.TL_messageEntityPhone) == false) goto L50;
        r8 = ApiWrap$TextPart.Type.Phone;
        goto L70
    L50:
        if ((r52 instanceof TLRPC.TL_messageEntityCashtag) == false) goto L53;
        r8 = ApiWrap$TextPart.Type.Cashtag;
        goto L70
    L53:
        if ((r52 instanceof TLRPC.TL_messageEntityUnderline) == false) goto L56;
        r8 = ApiWrap$TextPart.Type.Underline;
        goto L70
    L56:
        if ((r52 instanceof TLRPC.TL_messageEntityStrike) == false) goto L59;
        r8 = ApiWrap$TextPart.Type.Strike;
        goto L70
    L59:
        if ((r52 instanceof TLRPC.TL_messageEntityBlockquote) == false) goto L62;
        r8 = ApiWrap$TextPart.Type.Blockquote;
        goto L70
    L62:
        if ((r52 instanceof TLRPC.TL_messageEntityBankCard) == false) goto L65;
        r8 = ApiWrap$TextPart.Type.BankCard;
        goto L70
    L65:
        if ((r52 instanceof TLRPC.TL_messageEntitySpoiler) == false) goto L68;
        r8 = ApiWrap$TextPart.Type.Spoiler;
        goto L70
    L68:
        if ((r52 instanceof TLRPC.TL_messageEntityCustomEmoji) == false) goto L70;
        r8 = ApiWrap$TextPart.Type.CustomEmoji;
        goto L70
    L89:
        addTextPart(r0, r3, r10, r1);
        return r1;
    }

    public static ApiWrap$User ParseUser(TLRPC.User r8) {
        ApiWrap$User r0 = new ApiWrap$User();
        r0.info = ParseContactInfo(r8);
        if ((r8 instanceof TLRPC.TL_user) == false) goto L20;
        TLRPC.TL_user r82 = (TLRPC.TL_user) r8;
        Long r1 = Long.valueOf(r82.id);
        r0.bareId = r1;
        TLRPC.PeerColor r2 = r82.color;
        if (r2 == null) goto L9;
        int r22 = r2.color;
        if (r22 == 0) goto L9;
    L10:
        r0.colorIndex = r22;
        r0.username = r82.username;
        r0.isBot = r82.bot;
        r0.isSelf = r82.self;
        long r12 = r82.id;
        boolean r4 = false;
        if (r12 != UserObject.REPLY_BOT) goto L13;
        boolean r3 = true;
    L14:
        r0.isReplies = r3;
        if (r12 != UserObject.VERIFY) goto L17;
        r4 = true;
    L17:
        r0.isVerifyCodes = r4;
        TLRPC.TL_inputUser r13 = new TLRPC.TL_inputUser();
        r13.user_id = r82.id;
        r13.access_hash = r82.access_hash;
        r0.input = r13;
        return r0;
    L13:
        r3 = false;
    L9:
        r22 = PeerColorIndex(r1.longValue());
        goto L10
    L20:
        if ((r8 instanceof TLRPC.TL_userEmpty) == false) goto L22;
        TLRPC.TL_inputUser r14 = new TLRPC.TL_inputUser();
        r14.user_id = ((TLRPC.TL_userEmpty) r8).id;
        r14.access_hash = 0;
        r0.input = r14;
    L22:
        return r0;
    }

    public static ArrayList<HtmlWriter.Photo> ParseUserpicsSlice(ArrayList<TLRPC.Photo> r6, int r7) {
        ArrayList<HtmlWriter.Photo> r0 = new ArrayList(r6.size());
        int r1 = r6.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        TLRPC.Photo r3 = r6.get(r2);
        r2 = r2 + 1;
        TLRPC.Photo r32 = r3;
        StringBuilder r4 = new StringBuilder("profile_pictures/");
        r7 = r7 + 1;
        r4.append(PreparePhotoFileName(r7, r32.date));
        r0.add(ParsePhoto(r32, r4.toString()));
        goto L3
    L5:
        return r0;
    }

    public static HashMap<Long, ApiWrap$User> ParseUsersList(ArrayList<TLRPC.User> r5) {
        LinkedHashMap r0 = new LinkedHashMap();
        int r1 = r5.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        TLRPC.User r3 = r5.get(r2);
        r2 = r2 + 1;
        ApiWrap$User r32 = ParseUser(r3);
        r0.put(r32.info.userId, r32);
        goto L3
    L5:
        return r0;
    }

    public static ApiWrap$WebSession ParseWebSession(TLRPC.TL_webAuthorization r9, HashMap<Long, ApiWrap$User> r10) {
        if (r10 == null) goto L8;
        ApiWrap$User r102 = r10.get(Long.valueOf(r9.bot_id));
        if (r102 == null) goto L8;
        String r103 = r102.username;
        if (r103 == null) goto L8;
    L7:
        String r1 = r103;
        return new ApiWrap$WebSession(r1, r9.domain, r9.browser, r9.platform, r9.date_created, r9.date_active, r9.ip, r9.region);
    L8:
        r103 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L7
    }

    public static ApiWrap$SessionsList ParseWebSessionsList(TL_account.webAuthorizations r6) {
        ApiWrap$SessionsList r0 = new ApiWrap$SessionsList();
        HashMap<Long, ApiWrap$User> r1 = ParseUsersList(r6.users);
        ArrayList<TLRPC.TL_webAuthorization> r62 = r6.authorizations;
        int r2 = r62.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L5;
        TLRPC.TL_webAuthorization r4 = r62.get(r3);
        r3 = r3 + 1;
        r0.webList.add(ParseWebSession(r4, r1));
        goto L3
    L5:
        return r0;
    }

    public static int PeerColorIndex(long r0) {
        return ((int) r0) % 7;
    }

    private static String PrepareFileNameDatePart(int r6) {
        if (r6 != 0) goto L4;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L4:
        return "@" + LocaleController.getInstance().getExportFileFormatter().format(r6 * 1000);
    }

    private static String PreparePhotoFileName(int r2, int r3) {
        return "photo_" + r2 + PrepareFileNameDatePart(r3) + ".jpg";
    }

    private static String PrepareStoryFileName(int r2, int r3, String r4) {
        return "story_" + r2 + PrepareFileNameDatePart(r3) + r4;
    }

    public static boolean RefreshFileReference(TLRPC.InputFileLocation r7, TLRPC.InputFileLocation r8) {
        if (r7.getClass() == r8.getClass()) goto L6;
        return false;
    L6:
        if ((r7 instanceof TLRPC.TL_inputPhotoFileLocation) == false) goto L16;
        if (r7.id == r8.id) goto L10;
    L14:
        return false;
    L10:
        if (Objects.equals(r7.thumb_size, r8.thumb_size) == false) goto L14;
        r7.file_reference = r8.file_reference;
        return true;
    L16:
        if ((r7 instanceof TLRPC.TL_inputDocumentFileLocation) == true) goto L18;
    L24:
        return false;
    L18:
        if (r7.id != r8.id) goto L24;
        if (Objects.equals(r7.thumb_size, r8.thumb_size) == false) goto L24;
        r7.file_reference = r8.file_reference;
        return true;
    }

    public static int SettingsFromDialogsType(ApiWrap$DialogInfo.Type r1) {
        switch(AnonymousClass2.$SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[r1.ordinal()]) {
            case 1: goto L16;
            case 2: goto L16;
            case 3: goto L14;
            case 4: goto L12;
            case 5: goto L12;
            case 6: goto L10;
            case 7: goto L8;
            case 8: goto L6;
            default: goto L4;
        };
    L4:
        return 0;
    L6:
        return 1024;
    L8:
        return 512;
    L10:
        return 256;
    L12:
        return 128;
    L14:
        return 64;
    L16:
        return 32;
    }

    public static boolean SingleMessageAfter(TLRPC.messages_Messages r0, int r1) {
        int r02 = SingleMessageDate(r0);
        if (r02 <= 0) goto L7;
        if (r02 <= r1) goto L9;
        return true;
    L9:
        return false;
    L7:
        return false;
    }

    public static boolean SingleMessageBefore(TLRPC.messages_Messages r0, int r1) {
        int r02 = SingleMessageDate(r0);
        if (r02 <= 0) goto L7;
        if (r02 >= r1) goto L9;
        return true;
    L9:
        return false;
    L7:
        return false;
    }

    public static int SingleMessageDate(TLRPC.messages_Messages r2) {
        if ((r2 instanceof TLRPC.TL_messages_messagesNotModified) == false) goto L5;
        return 0;
    L5:
        ArrayList<TLRPC.Message> r22 = r2.messages;
        if (r22.isEmpty() == false) goto L9;
        return 0;
    L9:
        if ((r22.get(0) instanceof TLRPC.TL_messageEmpty) == false) goto L12;
        return 0;
    L12:
        return r22.get(0).date;
    }

    public static boolean SkipMessageByDate(ApiWrap$Message r4, ExportSettings r5) {
        int r0 = r5.singlePeerFrom;
        if (r0 > 0) goto L5;
    L8:
        boolean r02 = true;
    L9:
        int r52 = r5.singlePeerTill;
        if (r52 > 0) goto L12;
    L15:
        boolean r42 = true;
    L16:
        if (r02 == false) goto L20;
        if (r42 == false) goto L20;
        return false;
    L20:
        return true;
    L12:
        if (r4.date < r52) goto L15;
        r42 = false;
        goto L16
    L5:
        if (r0 <= r4.date) goto L8;
        r02 = false;
        goto L9
    }

    public static ArrayList<Integer> SortedContactsIndices(ApiWrap$ContactsList r8) {
        int r0 = r8.list.size();
        ArrayList r1 = new ArrayList(r0);
        ArrayList<ApiWrap$ContactInfo> r82 = r8.list;
        int r2 = r82.size();
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L5;
        ApiWrap$ContactInfo r5 = r82.get(r4);
        r4 = r4 + 1;
        ApiWrap$ContactInfo r52 = r5;
        r1.add((r52.firstName + " " + r52.lastName).toLowerCase());
        goto L3
    L5:
        ArrayList<Integer> r83 = new ArrayList(r0);
    L6:
        if (r3 >= r0) goto L8;
        r83.add(Integer.valueOf(r3));
        r3 = r3 + 1;
        goto L6
    L8:
        Collections.sort(r83, Comparator.comparing(new DataTypesUtils$$ExternalSyntheticLambda0(r1)));
        return r83;
    }

    public static long StringBarePeerId(String r9) {
        Long r2 = 255L;
        int r3 = 0;
    L4:
        if (r3 >= r9.length()) goto L7;
        r2 = Long.valueOf(((r2.longValue() * 239) + r9.charAt(r3)) & 255);
        r3 = r3 + 1;
        goto L4
    L7:
        return r2.longValue();
    }

    public static String TypeString(ApiWrap$DialogInfo.Type r1) {
        switch(AnonymousClass2.$SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[r1.ordinal()]) {
            case 1: goto L14;
            case 2: goto L14;
            case 3: goto L12;
            case 4: goto L10;
            case 5: goto L10;
            case 6: goto L10;
            case 7: goto L8;
            case 8: goto L8;
            case 9: goto L6;
            case 10: goto L14;
            case 11: goto L14;
            default: goto L5;
        };
    L6:
        return "unknown";
    L8:
        return "channel";
    L10:
        return "group";
    L12:
        return "bot";
    L14:
        return "private";
    L5:
        throw new IncompatibleClassChangeError();
    }

    private static void addTextPart(int r1, int r2, String r3, ArrayList<ApiWrap$TextPart> r4) {
        if (r1 <= r2) goto L5;
        ApiWrap$TextPart r0 = new ApiWrap$TextPart();
        r0.text = r3.substring(r2, r1);
        r4.add(r0);
        return;
    }

    public static boolean forwardedNeedsWrap(ApiWrap$Message r6, HtmlWriter.MessageInfo r7) {
        if (messageNeedsWrap(r6, r7) == false) goto L5;
        return true;
    L5:
        long r2 = r6.forwardedFromId;
        if (r2 != 0) goto L8;
    L18:
        return true;
    L8:
        if (r2 != r7.forwardedFromId) goto L18;
        if (ChatUtils.getInstance().getMessageStorage().getUser(r6.forwardedFromId) != null) goto L14;
        return true;
    L14:
        if (Math.abs(r6.forwardedDate - r7.forwardedDate) <= 900) goto L16;
        return true;
    L16:
        return false;
    }

    private static long getArea(TLRPC.PhotoSize r4) {
        if ((r4 instanceof TLRPC.TL_photoSizeEmpty) == false) goto L6;
        return 0;
    L6:
        if ((r4 instanceof TLRPC.TL_photoStrippedSize) == false) goto L9;
        return 0;
    L9:
        if ((r4 instanceof TLRPC.TL_photoPathSize) == false) goto L12;
        return 0;
    L12:
        return r4.w * r4.h;
    }

    private static String getExtensionFromMime(String r0, TLRPC.Document r1) {
        if (Objects.equals(r0, "image/webp") == false) goto L7;
        return ".webp";
    L7:
        if (Objects.equals(r0, "application/x-tgsticker") == false) goto L11;
        return ".tgs";
    L11:
        if (Objects.equals(r0, "application/x-tgwallpattern") == false) goto L15;
        return ".tgv";
    L15:
        if (Objects.equals(r0, "application/x-tdesktop-theme") == false) goto L17;
        return ".tdesktop-theme";
    L17:
        if (Objects.equals(r0, "application/x-tgtheme-tdesktop") == false) goto L20;
        return ".tdesktop-theme";
    L20:
        if (Objects.equals(r0, "application/x-tdesktop-palette") == false) goto L24;
        return ".tdesktop-palette";
    L24:
        if (Objects.equals(r0, "video/mp4") == false) goto L28;
        return ".mp4";
    L28:
        if (Objects.equals(r0, "audio/ogg") == false) goto L31;
        return ".ogg";
    L31:
        return ".unknown";
    }

    public static boolean messageNeedsWrap(ApiWrap$Message r9, HtmlWriter.MessageInfo r10) {
        if (r10 != null) goto L6;
        return true;
    L6:
        if (r10.type == HtmlWriter.MessageInfo.Type.Default) goto L8;
        return true;
    L8:
        long r1 = r9.fromId;
        if (r1 != 0) goto L11;
    L39:
        return true;
    L11:
        if (r10.fromId != r1) goto L39;
        if (r9.viaBotId == r10.viaBotId) goto L17;
        return true;
    L17:
        if (LocaleController.getInstance().getFormatterYear().format(r10.date * 1000).trim().equals(LocaleController.getInstance().getFormatterYear().format(r9.date * 1000).trim()) == true) goto L20;
        return true;
    L20:
        if (r9.forwarded != r10.forwarded) goto L39;
        if (r9.showForwardedAsOriginal != r10.showForwardedAsOriginal) goto L39;
        if (r9.forwardedFromId != r10.forwardedFromId) goto L39;
        if (Objects.equals(r9.forwardedFromName, r10.forwardedFromName) == false) goto L39;
        int r102 = Math.abs(r9.date - r10.date);
        if (r9.forwardedFromId == 0) goto L31;
    L34:
        int r92 = 1;
    L35:
        if (r102 <= r92) goto L37;
        return true;
    L37:
        return false;
    L31:
        if (r9.forwardedFromName.isEmpty() == false) goto L34;
        r92 = 900;
        goto L35
    }

    private static ApiWrap$GeoPoint parseGeoPoint(TLRPC.GeoPoint r3) {
        ApiWrap$GeoPoint r0 = new ApiWrap$GeoPoint();
        if ((r3 instanceof TLRPC.TL_geoPoint) == false) goto L5;
        TLRPC.TL_geoPoint r32 = (TLRPC.TL_geoPoint) r3;
        r0.latitude = r32.lat;
        r0.longitude = r32._long;
        r0.valid = true;
    L5:
        return r0;
    }

    private static ApiWrap$GiveawayResults parseGiveaway(TLRPC.TL_messageMediaGiveawayResults r13) {
        ApiWrap$GiveawayResults r0 = new ApiWrap$GiveawayResults(r13.channel_id, r13.until_date, r13.launch_msg_id, r13.additional_peers_count, r13.winners_count, r13.unclaimed_count, r13.months, r13.stars, r13.refunded, r13.only_new_subscribers);
        r0.winners.addAll(r13.winners);
        String r1 = r13.prize_description;
        if (r1 != null) goto L5;
    L7:
        return r0;
    L5:
        if (r1.isEmpty() == true) goto L7;
        r0.additionalPrize = r13.prize_description;
        goto L7
    }

    private static ApiWrap$GiveawayStart parseGiveaway(TLRPC.TL_messageMediaGiveaway r7) {
        ApiWrap$GiveawayStart r0 = new ApiWrap$GiveawayStart(r7.until_date, r7.stars, r7.quantity, r7.months, !r7.only_new_subscribers);
        r0.channels.addAll(r7.channels);
        if (r7.countries_iso2.isEmpty() == true) goto L5;
        r0.countries.addAll(r7.countries_iso2);
    L5:
        String r1 = r7.prize_description;
        if (r1 != null) goto L8;
    L10:
        return r0;
    L8:
        if (r1.isEmpty() == true) goto L10;
        r0.additionalPrize = r7.prize_description;
        goto L10
    }
}
