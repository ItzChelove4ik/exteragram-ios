package com.exteragram.messenger.export.output.json;

import android.util.Log;
import android.util.Pair;
import com.exteragram.messenger.export.ExportSettings;
import com.exteragram.messenger.export.api.ApiWrap$Chat;
import com.exteragram.messenger.export.api.ApiWrap$ContactInfo;
import com.exteragram.messenger.export.api.ApiWrap$ContactsList;
import com.exteragram.messenger.export.api.ApiWrap$DialogInfo;
import com.exteragram.messenger.export.api.ApiWrap$DialogsInfo;
import com.exteragram.messenger.export.api.ApiWrap$ExportPersonalInfo;
import com.exteragram.messenger.export.api.ApiWrap$File;
import com.exteragram.messenger.export.api.ApiWrap$Message;
import com.exteragram.messenger.export.api.ApiWrap$MessagesSlice;
import com.exteragram.messenger.export.api.ApiWrap$SessionsList;
import com.exteragram.messenger.export.api.ApiWrap$StoriesSlice;
import com.exteragram.messenger.export.api.ApiWrap$Story;
import com.exteragram.messenger.export.api.ApiWrap$TopPeer;
import com.exteragram.messenger.export.api.ApiWrap$UserpicsInfo;
import com.exteragram.messenger.export.api.ApiWrap$WebSession;
import com.exteragram.messenger.export.api.DataTypesUtils;
import com.exteragram.messenger.export.output.AbstractWriter;
import com.exteragram.messenger.export.output.FileManager;
import com.exteragram.messenger.export.output.OutputFile;
import com.exteragram.messenger.export.output.html.HtmlWriter;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public class JsonWriter extends AbstractWriter {
    private volatile JsonContext _chat;
    private volatile JsonContext _chatSummary;
    private volatile JsonContext _contacts;
    private ApiWrap$DialogInfo _dialog;
    private HtmlWriter.DialogsMode _dialogsMode;
    private int _messagesCount;
    private volatile JsonContext _otherData;
    private volatile JsonContext _sessions;
    private ExportSettings _settings;
    private OutputFile.Stats _stats;
    private volatile JsonContext _stories;
    private JsonContext _summary;

    /* renamed from: com.exteragram.messenger.export.output.json.JsonWriter$1, reason: invalid class name */
    public class AnonymousClass1 implements Utilities.Callback2<ArrayList<ApiWrap$TopPeer>, String> {
        final /* synthetic */ JsonWriter this$0;
        final /* synthetic */ StringBuilder val$block;

        public AnonymousClass1(JsonWriter r1, StringBuilder r2) {
            this.this$0 = r1;
            this.val$block = r2;
        }

        @Override // org.telegram.messenger.Utilities.Callback2
        public /* bridge */ /* synthetic */ void run(ArrayList<ApiWrap$TopPeer> r1, String r2) {
            run2(r1, r2);
        }

        /* renamed from: run, reason: avoid collision after fix types in other method */
        public void run2(ArrayList<ApiWrap$TopPeer> r13, String r14) {
            int r0 = r13.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L19;
            ApiWrap$TopPeer r2 = r13.get(r1);
            r1 = r1 + 1;
            ApiWrap$TopPeer r22 = r2;
            ApiWrap$Chat r3 = r22.peer.chat;
            if (r3 == null) goto L17;
            boolean r4 = r3.username.isEmpty();
            boolean r5 = r3.isBroadcast;
            if (r4 == false) goto L14;
            if (r5 == false) goto L11;
            String r32 = "private_channel";
        L18:
            StringBuilder r42 = this.val$block;
            JsonWriter r52 = this.this$0;
            r42.append(JsonWriter.m1361$$Nest$mprepareArrayItemStart(r52, JsonWriter.m1360$$Nest$fget_contacts(r52)));
            this.val$block.append(JsonContext.SerializeObject(JsonWriter.m1360$$Nest$fget_contacts(this.this$0), new Pair[]{new Pair("id", String.valueOf(r22.peer.id())), new Pair("category", JsonContext.SerializeString(r14)), new Pair("type", JsonContext.SerializeString(r32)), new Pair("name", JsonWriter.m1362$$Nest$smStringAllowNull(r22.peer.name())), new Pair("rating", String.valueOf(r22.rating))}));
            goto L3
        L11:
            if (r3.isSupergroup == false) goto L13;
            r32 = "private_supergroup";
            goto L18
        L13:
            r32 = "private_group";
            goto L18
        L14:
            if (r5 == false) goto L16;
            r32 = "public_channel";
            goto L18
        L16:
            r32 = "public_supergroup";
            goto L18
        L17:
            r32 = "user";
            goto L18
        }
    }

    /* renamed from: com.exteragram.messenger.export.output.json.JsonWriter$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type = null;
        static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason = null;

        static {
            int[] r0 = new int[ApiWrap$File.SkipReason.values().length];
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason = r0;
            r0[ApiWrap$File.SkipReason.Unavailable.ordinal()] = 1;     // Catch: NoSuchFieldError -> L25
        L59:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[ApiWrap$File.SkipReason.FileSize.ordinal()] = 2;     // Catch: NoSuchFieldError -> L26
        L47:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[ApiWrap$File.SkipReason.FileType.ordinal()] = 3;     // Catch: NoSuchFieldError -> L27
        L55:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[ApiWrap$File.SkipReason.None.ordinal()] = 4;     // Catch: NoSuchFieldError -> L28
        L61:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[ApiWrap$File.SkipReason.DateLimits.ordinal()] = 5;     // Catch: NoSuchFieldError -> L29
        L12:
            int[] r5 = new int[ApiWrap$DialogInfo.Type.values().length];
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type = r5;
            r5[ApiWrap$DialogInfo.Type.Unknown.ordinal()] = 1;     // Catch: NoSuchFieldError -> L30
        L65:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.Self.ordinal()] = 2;     // Catch: NoSuchFieldError -> L31
        L71:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.Replies.ordinal()] = 3;     // Catch: NoSuchFieldError -> L32
        L43:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.VerifyCodes.ordinal()] = 4;     // Catch: NoSuchFieldError -> L33
        L49:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.Personal.ordinal()] = 5;     // Catch: NoSuchFieldError -> L34
        L69:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.Bot.ordinal()] = 6;     // Catch: NoSuchFieldError -> L35
        L41:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.PrivateGroup.ordinal()] = 7;     // Catch: NoSuchFieldError -> L36
        L63:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.PrivateSupergroup.ordinal()] = 8;     // Catch: NoSuchFieldError -> L37
        L67:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.PublicSupergroup.ordinal()] = 9;     // Catch: NoSuchFieldError -> L38
        L51:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.PrivateChannel.ordinal()] = 10;     // Catch: NoSuchFieldError -> L39
        L57:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.PublicChannel.ordinal()] = 11;     // Catch: NoSuchFieldError -> L40
            return;
        }
    }

    /* renamed from: -$$Nest$fget_contacts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ JsonContext m1360$$Nest$fget_contacts(JsonWriter r0) {
        return r0._contacts;
    }

    /* renamed from: -$$Nest$mprepareArrayItemStart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m1361$$Nest$mprepareArrayItemStart(JsonWriter r0, JsonContext r1) {
        return r0.prepareArrayItemStart(r1);
    }

    /* renamed from: -$$Nest$smStringAllowNull, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m1362$$Nest$smStringAllowNull(String r0) {
        return StringAllowNull(r0);
    }

    public JsonWriter() {
        this._dialogsMode = HtmlWriter.DialogsMode.None;
    }

    private static String StringAllowNull(String r1) {
        if (r1 != null) goto L4;
        return "null";
    L4:
        if (r1.isEmpty() == false) goto L7;
        return "null";
    L7:
        return JsonContext.SerializeString(r1);
    }

    private OutputFile fileWithRelativePath(String r4) {
        return new OutputFile(this._settings.path + "/" + r4, this._stats);
    }

    private static String messagesFile(int r2) {
        return "messages" + r2 + ".json";
    }

    private String popNesting(JsonContext r4) {
        ArrayList<Boolean> r3 = r4.nesting;
        Boolean r32 = r3.get(r3.size() - 1);
        ArrayList<Boolean> r0 = r4.nesting;
        r0.remove(r0.size() - 1);
        r4._currentNestingHadItem = true;
        StringBuilder r02 = new StringBuilder("\n");
        r02.append(JsonContext.Indentation(r4));
        if (r32.booleanValue() != true) goto L5;
        char r33 = '}';
    L6:
        r02.append(r33);
        return r02.toString();
    L5:
        r33 = ']';
        goto L6
    }

    private String prepareArrayItemStart(JsonContext r3) {
        StringBuilder r0 = new StringBuilder();     // Catch: Throwable -> L6
        if (r3._currentNestingHadItem == false) goto L8;
        String r1 = ",\n";
    L9:
        r0.append(r1);     // Catch: Throwable -> L6
        r0.append(JsonContext.Indentation(r3));     // Catch: Throwable -> L6
        String r02 = r0.toString();     // Catch: Throwable -> L6
        r3._currentNestingHadItem = true;
        return r02;
    L8:
        r1 = "\n";
    L6:
        th = move-exception;
        r3._currentNestingHadItem = true;
        throw th;
    }

    private String prepareObjectItemStart(String r3, JsonContext r4) {
        StringBuilder r0 = new StringBuilder();     // Catch: Throwable -> L6
        if (r4._currentNestingHadItem == false) goto L8;
        String r1 = ",\n";
    L9:
        r0.append(r1);     // Catch: Throwable -> L6
        r0.append(JsonContext.Indentation(r4));     // Catch: Throwable -> L6
        r0.append(JsonContext.SerializeString(r3));     // Catch: Throwable -> L6
        r0.append(": ");     // Catch: Throwable -> L6
        String r32 = r0.toString();     // Catch: Throwable -> L6
        r4._currentNestingHadItem = true;
        return r32;
    L8:
        r1 = "\n";
    L6:
        th = move-exception;
        r4._currentNestingHadItem = true;
        throw th;
    }

    private String pushNesting(Boolean r1, JsonContext r2) {
        r2.nesting.add(r1);
        r2._currentNestingHadItem = false;
        if (r1.booleanValue() != true) goto L6;
        return "{";
    L6:
        return "[";
    }

    private AbstractWriter.Result switchToNextChatFile(int r4) {
        this._chat = new JsonContext(fileWithRelativePath(this._dialog.relativePath + messagesFile(r4)));
        return AbstractWriter.Result.Success();
    }

    private AbstractWriter.Result writeChatsEnd() {
        return AbstractWriter.Result.Success();
    }

    private AbstractWriter.Result writeChatsStart(String r1, String r2) {
        return AbstractWriter.Result.Success();
    }

    private AbstractWriter.Result writeFrequentContacts(ApiWrap$ContactsList r5) {
        StringBuilder r0 = new StringBuilder(prepareObjectItemStart("frequent_contacts", this._contacts));
        r0.append(pushNesting(Boolean.TRUE, this._contacts));
        r0.append(prepareObjectItemStart("about", this._contacts));
        r0.append(JsonContext.SerializeString("_environment.aboutFrequent"));
        r0.append(prepareObjectItemStart("list", this._contacts));
        r0.append(pushNesting(Boolean.FALSE, this._contacts));
        AnonymousClass1 r1 = new AnonymousClass1(this, r0);
        r1.run2(r5.correspondents, "people");
        r1.run2(r5.inlineBots, "inline_bots");
        r1.run2(r5.phoneCalls, "calls");
        r0.append(popNesting(this._contacts));
        return this._contacts.writeBlock(r0 + popNesting(this._contacts));
    }

    private AbstractWriter.Result writeSavedContacts(ApiWrap$ContactsList r14) {
        StringBuilder r0 = new StringBuilder(prepareObjectItemStart("contacts", this._contacts));
        r0.append(pushNesting(Boolean.TRUE, this._contacts));
        r0.append(prepareObjectItemStart("about", this._contacts));
        r0.append(JsonContext.SerializeString("_environment.aboutContacts"));
        r0.append(prepareObjectItemStart("list", this._contacts));
        r0.append(pushNesting(Boolean.FALSE, this._contacts));
        ArrayList<Integer> r1 = DataTypesUtils.SortedContactsIndices(r14);
        int r2 = r1.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L16;
        Integer r4 = r1.get(r3);
        r3 = r3 + 1;
        ApiWrap$ContactInfo r42 = r14.list.get(r4.intValue());
        r0.append(prepareArrayItemStart(this._contacts));
        if (r42.firstName.isEmpty() == false) goto L11;
        if (r42.lastName.isEmpty() == false) goto L11;
        if (r42.phoneNumber.isEmpty() == false) goto L11;
        r0.append(JsonContext.SerializeObject(this._contacts, new Pair[]{new Pair("date_unixtime", JsonContext.SerializeString(String.valueOf(r42.date)))}));
    L11:
        JsonContext r5 = this._contacts;
        if (r42.userId.longValue() == 0) goto L14;
        String r8 = String.valueOf(r42.userId);
    L15:
        r0.append(JsonContext.SerializeObject(r5, new Pair[]{new Pair("user_id", r8), new Pair("first_name", JsonContext.SerializeString(r42.firstName)), new Pair("last_name", JsonContext.SerializeString(r42.lastName)), new Pair("phone_number", JsonContext.SerializeString(PhoneFormat.getInstance().format(r42.phoneNumber))), new Pair("date_unixtime", JsonContext.SerializeString(String.valueOf(r42.date)))}));
        goto L3
    L14:
        r8 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L15
    L16:
        r0.append(popNesting(this._contacts));
        return this._contacts.writeBlock(r0 + popNesting(this._contacts));
    }

    private AbstractWriter.Result writeSessions(ApiWrap$SessionsList r19) {
        StringBuilder r1 = new StringBuilder(prepareObjectItemStart("sessions", this._sessions));
        r1.append(pushNesting(Boolean.TRUE, this._sessions));
        r1.append(prepareObjectItemStart("about", this._sessions));
        r1.append(JsonContext.SerializeString("_environment.aboutSessions"));
        r1.append(prepareObjectItemStart("list", this._sessions));
        r1.append(pushNesting(Boolean.FALSE, this._sessions));
        ArrayList<TLRPC.TL_authorization> r2 = r19.list;
        int r3 = r2.size();
        int r4 = 0;
    L3:
        if (r4 >= r3) goto L9;
        TLRPC.TL_authorization r5 = r2.get(r4);
        r4 = r4 + 1;
        TLRPC.TL_authorization r52 = r5;
        r1.append(prepareArrayItemStart(this._sessions));
        JsonContext r6 = this._sessions;
        Pair r7 = new Pair("last_active", JsonContext.SerializeString(String.valueOf(r52.date_active)));
        Pair r8 = new Pair("last_ip", JsonContext.SerializeString(r52.ip));
        Pair r9 = new Pair("last_country", JsonContext.SerializeString(r52.country));
        Pair r10 = new Pair("last_region", JsonContext.SerializeString(r52.region));
        Pair r11 = new Pair("application_name", StringAllowNull(r52.app_name));
        if (r52.app_version.isEmpty() == false) goto L7;
        String r13 = _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        r1.append(JsonContext.SerializeObject(r6, new Pair[]{r7, r8, r9, r10, r11, new Pair("application_version", r13), new Pair("device_model", JsonContext.SerializeString(r52.device_model)), new Pair("platform", JsonContext.SerializeString(r52.platform)), new Pair("system_version", JsonContext.SerializeString(r52.system_version)), new Pair("created", JsonContext.SerializeString(String.valueOf(r52.date_created)))}));
        r2 = r2;
        r3 = r3;
        goto L3
    L7:
        r13 = JsonContext.SerializeString(r52.app_version);
        goto L8
    L9:
        r1.append(popNesting(this._sessions));
        return this._sessions.writeBlock(r1 + popNesting(this._sessions));
    }

    private AbstractWriter.Result writeWebSessions(ApiWrap$SessionsList r15) {
        StringBuilder r0 = new StringBuilder(prepareObjectItemStart("web_sessions", this._sessions));
        r0.append(pushNesting(Boolean.TRUE, this._sessions));
        r0.append(prepareObjectItemStart("about", this._sessions));
        r0.append(JsonContext.SerializeString("_environment.aboutWebSessions"));
        r0.append(prepareObjectItemStart("list", this._sessions));
        r0.append(pushNesting(Boolean.FALSE, this._sessions));
        ArrayList<ApiWrap$WebSession> r152 = r15.webList;
        int r1 = r152.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        ApiWrap$WebSession r3 = r152.get(r2);
        r2 = r2 + 1;
        ApiWrap$WebSession r32 = r3;
        r0.append(prepareArrayItemStart(this._sessions));
        r0.append(JsonContext.SerializeObject(this._sessions, new Pair[]{new Pair("last_active_unixtime", JsonContext.SerializeString(String.valueOf(r32.lastActive()))), new Pair("last_ip", JsonContext.SerializeString(r32.ip())), new Pair("last_region", JsonContext.SerializeString(r32.region())), new Pair("bot_username", StringAllowNull(r32.botUsername())), new Pair("domain_name", StringAllowNull(r32.domain())), new Pair("browser", JsonContext.SerializeString(r32.browser())), new Pair("platform", JsonContext.SerializeString(r32.platform())), new Pair("created_unixtime", JsonContext.SerializeString(String.valueOf(r32.created())))}));
        goto L3
    L5:
        r0.append(popNesting(this._sessions));
        return this._sessions.writeBlock(r0 + popNesting(this._sessions));
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result finish() {
        if (this._settings.onlySinglePeer() == true) goto L5;
        JsonContext r0 = this._summary;
        return r0.writeBlock(popNesting(r0));
    L5:
        return AbstractWriter.Result.Success();
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public String mainFilePath() {
        return this._settings.path + "/result.json";
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result start(ExportSettings r2, OutputFile.Stats r3) {
        this._settings = r2;
        this._stats = r3;
        this._summary = new JsonContext(fileWithRelativePath("result.json"));
        this._contacts = new JsonContext(fileWithRelativePath("contacts.json"));
        this._stories = new JsonContext(fileWithRelativePath("stories.json"));
        if (this._settings.onlySinglePeer() == false) goto L7;
        return AbstractWriter.Result.Success();
    L7:
        return this._summary.writeBlock(pushNesting(Boolean.TRUE, this._summary) + prepareObjectItemStart("about", this._summary) + JsonContext.SerializeString("Here is the data you requested. Remember: Telegram is ad free, it doesn't use your data for ad targeting and doesn't sell it to others. Telegram only keeps the information it needs to function as a secure and feature-rich cloud service.\\n\\nCheck out Settings > Privacy & Security on Telegram's mobile apps for the relevant settings."));
    }

    public AbstractWriter.Result validateDialogsMode(boolean r4) {
        if (r4 == false) goto L4;
        HtmlWriter.DialogsMode r0 = HtmlWriter.DialogsMode.Left;
    L5:
        HtmlWriter.DialogsMode r1 = this._dialogsMode;
        if (r1 != r0) goto L10;
        return AbstractWriter.Result.Success();
    L10:
        if (r1 == HtmlWriter.DialogsMode.None) goto L14;
        AbstractWriter.Result r12 = writeChatsEnd();
        if (r12.isSuccess() == true) goto L14;
        return r12;
    L14:
        this._dialogsMode = r0;
        if (r4 == false) goto L17;
        String r02 = "left_chats";
    L18:
        if (r4 == false) goto L20;
        String r42 = "Below are the supergroups and channels from this export that you've left or where you were banned.\\n\\nNote that when you leave a channel or supergroup you've created, you have the option to either delete it, or simply leave (in case you want to rejoin later, or keep the community alive despite not being a member).";
    L22:
        return writeChatsStart(r02, r42);
    L20:
        r42 = "This page lists all chats from this export.";
        goto L22
    L17:
        r02 = "chats";
        goto L18
    L4:
        r0 = HtmlWriter.DialogsMode.Chats;
        goto L5
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeContactsList(ApiWrap$ContactsList r3) {
        AbstractWriter.Result r0 = writeSavedContacts(r3);
        if (r0.isSuccess() == true) goto L5;
        return r0;
    L5:
        AbstractWriter.Result r2 = writeFrequentContacts(r3);
        if (r2.isSuccess() == true) goto L9;
        return r2;
    L9:
        return AbstractWriter.Result.Success();
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeDialogEnd() {
        this._chatSummary.writeBlock(prepareObjectItemStart("msgsCount", this._chatSummary) + this._messagesCount);
        return this._chatSummary.writeBlock(popNesting(this._chatSummary));
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeDialogSlice(ApiWrap$MessagesSlice r7) {
        int r0 = this._messagesCount;
        int r1 = 0;
        if (r0 <= 0) goto L5;
        int r2 = (r0 - 1) / 100;
    L6:
        int r02 = r0 / 100;
        Log.d("exteraGram", "switching to next chat file! old index: " + r2 + ", new index: " + r02);
        switchToNextChatFile(r02);
        this._chat.writeBlock(pushNesting(Boolean.FALSE, this._chat));
    L8:
        if (r1 >= r7.list.size()) goto L17;
        ApiWrap$Message r03 = r7.list.get(r1);
        if (DataTypesUtils.SkipMessageByDate(r03, this._settings) == true) goto L16;
        this._chat.writeBlock(JsonContext.SerializeMessage(this._chat, r03, r7.peers, "https://t.me/"));
        if (r1 == (r7.list.size() - 1)) goto L15;
        this._chat._currentNestingHadItem = true;
        this._chat.writeBlock(prepareArrayItemStart(this._chat));
    L15:
        this._messagesCount++;
    L16:
        r1 = r1 + 1;
        goto L8
    L17:
        this._chat.writeBlock(popNesting(this._chat));
        return AbstractWriter.Result.Success();
    L5:
        r2 = 0;
        goto L6
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeDialogStart(ApiWrap$DialogInfo r6) {
        this._chatSummary = new JsonContext(fileWithRelativePath(r6.relativePath + "info.json"));
        this._chat = new JsonContext(fileWithRelativePath(r6.relativePath + messagesFile(0)));
        this._summary.writeBlock(prepareObjectItemStart("msgsCount", this._summary) + r6.messagesCountPerSplit.get(0));
        this._messagesCount = 0;
        this._dialog = r6;
        if (this._settings.onlySinglePeer() == true) goto L7;
        AbstractWriter.Result r0 = validateDialogsMode(r6.isLeftChannel);
        if (r0.isSuccess() == true) goto L7;
        return r0;
    L7:
        int r02 = AnonymousClass2.$SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[r6.type.ordinal()];
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        switch(r02) {
            case 1: goto L9;
            case 2: goto L19;
            case 3: goto L18;
            case 4: goto L17;
            case 5: goto L16;
            case 6: goto L15;
            case 7: goto L14;
            case 8: goto L13;
            case 9: goto L12;
            case 10: goto L11;
            case 11: goto L10;
            default: goto L9;
        };
    L9:
        String r03 = _UrlKt.FRAGMENT_ENCODE_SET;
    L21:
        if (this._settings.onlySinglePeer() == true) goto L24;
        r1 = prepareArrayItemStart(this._chatSummary);
    L24:
        StringBuilder r2 = new StringBuilder(r1);
        r2.append(pushNesting(Boolean.TRUE, this._chatSummary));
        ApiWrap$DialogInfo.Type r12 = r6.type;
        if (r12 != ApiWrap$DialogInfo.Type.Self) goto L27;
    L31:
        r2.append(prepareObjectItemStart("type", this._chatSummary) + StringAllowNull(r03));
        r2.append(prepareObjectItemStart("id", this._chatSummary) + r6.peerId);
        return this._chatSummary.writeBlock(r2.toString());
    L27:
        if (r12 == ApiWrap$DialogInfo.Type.Replies) goto L31;
        if (r12 == ApiWrap$DialogInfo.Type.VerifyCodes) goto L31;
        r2.append(prepareObjectItemStart("name", this._chatSummary) + StringAllowNull(r6.name));
        goto L31
    L10:
        r03 = "public_channel";
        goto L21
    L11:
        r03 = "private_channel";
        goto L21
    L12:
        r03 = "public_supergroup";
        goto L21
    L13:
        r03 = "private_supergroup";
        goto L21
    L14:
        r03 = "private_group";
        goto L21
    L15:
        r03 = "bot_chat";
        goto L21
    L16:
        r03 = "personal_chat";
        goto L21
    L17:
        r03 = "verification_codes";
        goto L21
    L18:
        r03 = "replies";
        goto L21
    L19:
        r03 = "saved_messages";
        goto L21
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeDialogsEnd() {
        return AbstractWriter.Result.Success();
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeDialogsStart(ApiWrap$DialogsInfo r10) {
        StringBuilder r0 = new StringBuilder(prepareArrayItemStart(this._summary));
        JsonContext r1 = this._summary;
        r1._currentNestingHadItem = false;
        r0.append(prepareObjectItemStart("chats", r1));
        r0.append(pushNesting(Boolean.FALSE, this._summary));
        ArrayList<ApiWrap$DialogInfo> r12 = r10.chats;
        int r3 = r12.size();
        int r6 = 0;
        boolean r5 = true;
    L4:
        if (r6 >= r3) goto L10;
        ApiWrap$DialogInfo r8 = r12.get(r6);
        r6 = r6 + 1;
        ApiWrap$DialogInfo r82 = r8;
        if (r5 == true) goto L8;
        r0.append(",\n");
    L9:
        r0.append(this._summary.SerializeDialog(r82, false));
        goto L4
    L8:
        r5 = false;
        goto L9
    L10:
        ArrayList<ApiWrap$DialogInfo> r102 = r10.left;
        int r13 = r102.size();
        int r32 = 0;
    L11:
        if (r32 >= r13) goto L17;
        ApiWrap$DialogInfo r62 = r102.get(r32);
        r32 = r32 + 1;
        ApiWrap$DialogInfo r63 = r62;
        if (r5 == true) goto L15;
        r0.append(",\n");
    L16:
        r0.append(this._summary.SerializeDialog(r63, true));
        goto L11
    L15:
        r5 = false;
        goto L16
    L17:
        r0.append(popNesting(this._summary));
        return this._summary.writeBlock(r0.toString());
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeOtherData(ApiWrap$File r2) {
        OutputFile r22 = fileWithRelativePath(r2.relativePath);
        String r0 = FileManager.readFileContent(r22._file);
        if (r0 == null) goto L10;
        if (r0.isEmpty() == true) goto L10;
        this._otherData = new JsonContext(r22);
        prepareObjectItemStart("other_data", this._otherData);
        return AbstractWriter.Result.Success();
    L10:
        return AbstractWriter.Result.Success();
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writePersonal(ApiWrap$ExportPersonalInfo r11) {
        ApiWrap$ContactInfo r0 = r11.user.info;
        JsonContext r1 = this._summary;
        StringBuilder r2 = new StringBuilder();
        r2.append(prepareObjectItemStart("personal_information", this._summary));
        JsonContext r10 = this._summary;
        Pair r3 = new Pair("user_id", r11.user.bareId);
        Pair r4 = new Pair("first_name", JsonContext.SerializeString(r0.firstName));
        Pair r5 = new Pair("last_name", JsonContext.SerializeString(r0.lastName));
        Pair r6 = new Pair("phone_number", JsonContext.SerializeString(r0.phoneNumber));
        if (r11.user.username.isEmpty() == true) goto L5;
        String r02 = JsonContext.SerializeString(r11.user.username);
    L6:
        Pair r7 = new Pair("username", r02);
        if (r11.bio.isEmpty() == true) goto L9;
        String r112 = JsonContext.SerializeString(r11.bio);
    L10:
        r2.append(JsonContext.SerializeObject(r10, new Pair[]{r3, r4, r5, r6, r7, new Pair("bio", r112)}));
        return r1.writeBlock(r2.toString());
    L9:
        r112 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L10
    L5:
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeSessionsList(ApiWrap$SessionsList r3) {
        this._sessions = new JsonContext(fileWithRelativePath("sessions.json"));
        AbstractWriter.Result r0 = writeSessions(r3);
        if (r0.isSuccess() == true) goto L5;
        return r0;
    L5:
        AbstractWriter.Result r2 = writeWebSessions(r3);
        if (r2.isSuccess() == true) goto L9;
        return r2;
    L9:
        return AbstractWriter.Result.Success();
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeStoriesEnd() {
        return this._stories.writeBlock(popNesting(this._stories));
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeStoriesSlice(ApiWrap$StoriesSlice r11) {
        if (r11.list.isEmpty() == true) goto L5;
        StringBuilder r0 = new StringBuilder();
        ArrayList<ApiWrap$Story> r112 = r11.list;
        int r1 = r112.size();
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L39;
        ApiWrap$Story r3 = r112.get(r2);
        r2 = r2 + 1;
        ApiWrap$Story r32 = r3;
        ApiWrap$File r4 = r32.file();
        int r5 = AnonymousClass2.$SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[r4.skipReason.ordinal()];
        if (r5 != 1) goto L11;
        String r42 = "(Photo unavailable, please try again later)";
    L26:
        r0.append(prepareArrayItemStart(this._stories));
        JsonContext r52 = this._stories;
        int r7 = r32.date;
        String r8 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r7 == 0) goto L29;
        String r72 = JsonContext.SerializeString(String.valueOf(r7));
    L30:
        Pair r6 = new Pair("date_unixtime", r72);
        int r9 = r32.expires;
        if (r9 == 0) goto L33;
        r8 = JsonContext.SerializeString(String.valueOf(r9));
    L33:
        Pair r73 = new Pair("expires_unixtime", r8);
        if (r32.pinned == false) goto L36;
        String r33 = "true";
    L37:
        r0.append(JsonContext.SerializeObject(r52, new Pair[]{r6, r73, new Pair("pinned", r33), new Pair("media", JsonContext.SerializeString(r42))}));
        goto L7
    L36:
        r33 = "false";
        goto L37
    L29:
        r72 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L30
    L11:
        if (r5 != 2) goto L13;
        r42 = "(Photo exceeds maximum size. Change data exporting settings to download.)";
        goto L26
    L13:
        if (r5 != 3) goto L15;
        r42 = "(Photo not included. Change data exporting settings to download.)";
        goto L26
    L15:
        if (r5 != 4) goto L17;
        r42 = r4.relativePath;
        goto L26
    L17:
        if (r5 != 5) goto L21;
        Segment$$ExternalSyntheticBUOutline1.m("date limited skip reason while writing story path");
        return null;
    L21:
        throw new IncompatibleClassChangeError();
    L39:
        return this._stories.writeBlock(r0.toString());
    L5:
        return AbstractWriter.Result.Success();
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeStoriesStart(int r4) {
        String r42 = prepareObjectItemStart("stories", this._stories);
        return this._stories.writeBlock(r42 + pushNesting(Boolean.FALSE, this._stories));
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeUserpicsEnd() {
        JsonContext r0 = this._summary;
        return r0.writeBlock(popNesting(r0));
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeUserpicsSlice(ArrayList<HtmlWriter.Photo> r9) {
        StringBuilder r0 = new StringBuilder();
        int r1 = r9.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L28;
        HtmlWriter.Photo r3 = r9.get(r2);
        r2 = r2 + 1;
        HtmlWriter.Photo r32 = r3;
        ApiWrap$File r4 = r32.image.file;
        int r5 = AnonymousClass2.$SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[r4.skipReason.ordinal()];
        if (r5 != 1) goto L7;
        String r42 = "(Photo unavailable, please try again later)";
    L22:
        r0.append(prepareArrayItemStart(this._summary));
        JsonContext r52 = this._summary;
        int r33 = r32.date;
        if (r33 == 0) goto L25;
        String r34 = JsonContext.SerializeString(String.valueOf(r33));
    L26:
        r0.append(JsonContext.SerializeObject(r52, new Pair[]{new Pair("date_unixtime", r34), new Pair("photo", JsonContext.SerializeString(r42))}));
        goto L3
    L25:
        r34 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L26
    L7:
        if (r5 != 2) goto L9;
        r42 = "(Photo exceeds maximum size. Change data exporting settings to download.)";
        goto L22
    L9:
        if (r5 != 3) goto L11;
        r42 = "(Photo not included. Change data exporting settings to download.)";
        goto L22
    L11:
        if (r5 != 4) goto L13;
        r42 = r4.relativePath;
        goto L22
    L13:
        if (r5 != 5) goto L17;
        Segment$$ExternalSyntheticBUOutline1.m("Skip reason while writing photo path.");
        return null;
    L17:
        throw new IncompatibleClassChangeError();
    L28:
        return this._summary.writeBlock(r0.toString());
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeUserpicsStart(ApiWrap$UserpicsInfo r4) {
        return this._summary.writeBlock(prepareObjectItemStart("profile_pictures", this._summary) + pushNesting(Boolean.FALSE, this._summary));
    }
}
