package com.exteragram.messenger.export.output.html;

import android.util.Pair;
import androidx.camera.core.ImageCapture$$ExternalSyntheticBackport0;
import androidx.camera.core.streamsharing.StreamSharing$$ExternalSyntheticBackport0;
import com.exteragram.messenger.export.ExportSettings;
import com.exteragram.messenger.export.api.ApiWrap$ContactInfo;
import com.exteragram.messenger.export.api.ApiWrap$ContactsList;
import com.exteragram.messenger.export.api.ApiWrap$DialogInfo;
import com.exteragram.messenger.export.api.ApiWrap$DialogsInfo;
import com.exteragram.messenger.export.api.ApiWrap$ExportPersonalInfo;
import com.exteragram.messenger.export.api.ApiWrap$File;
import com.exteragram.messenger.export.api.ApiWrap$Image;
import com.exteragram.messenger.export.api.ApiWrap$Message;
import com.exteragram.messenger.export.api.ApiWrap$MessagesSlice;
import com.exteragram.messenger.export.api.ApiWrap$Peer;
import com.exteragram.messenger.export.api.ApiWrap$SessionsList;
import com.exteragram.messenger.export.api.ApiWrap$StoriesSlice;
import com.exteragram.messenger.export.api.ApiWrap$Story;
import com.exteragram.messenger.export.api.ApiWrap$StoryData;
import com.exteragram.messenger.export.api.ApiWrap$TopPeer;
import com.exteragram.messenger.export.api.ApiWrap$User;
import com.exteragram.messenger.export.api.ApiWrap$UserpicsInfo;
import com.exteragram.messenger.export.api.ApiWrap$WebSession;
import com.exteragram.messenger.export.api.DataTypesUtils;
import com.exteragram.messenger.export.output.AbstractWriter;
import com.exteragram.messenger.export.output.FileManager;
import com.exteragram.messenger.export.output.OutputFile;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.google.android.gms.cast.CredentialsData;
import com.google.android.gms.cast.MediaStatus;
import com.google.zxing.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public class HtmlWriter extends AbstractWriter {
    private HtmlContext _chat;
    private boolean _chatFileEmpty;
    private HtmlContext _chats;
    private int _dateMessageId;
    private ApiWrap$ExportPersonalInfo _delayedPersonalInfo;
    private ApiWrap$DialogInfo _dialog;
    private DialogsMode _dialogsMode;
    private String _dialogsRelativePath;
    private boolean _haveSections;
    private final ArrayList<Integer> _lastMessageIdsPerFile;
    private MessageInfo _lastMessageInfo;
    private int _messagesCount;
    private final ArrayList<SavedSection> _savedSections;
    private int _selfColorIndex;
    private ExportSettings _settings;
    private OutputFile.Stats _stats;
    private HtmlContext _stories;
    private int _storiesCount;
    private HtmlContext _summary;
    private boolean _summaryNeedDivider;
    private HtmlContext _userpics;
    private int _userpicsCount;
    private int selectedAcc;

    /* renamed from: com.exteragram.messenger.export.output.html.HtmlWriter$1, reason: invalid class name */
    public class AnonymousClass1 implements Utilities.Callback2<ArrayList<ApiWrap$TopPeer>, String> {
        final /* synthetic */ HtmlWriter this$0;
        final /* synthetic */ StringBuilder val$block;
        final /* synthetic */ HtmlContext val$file;

        public AnonymousClass1(HtmlWriter r1, StringBuilder r2, HtmlContext r3) {
            this.this$0 = r1;
            this.val$block = r2;
            this.val$file = r3;
        }

        @Override // org.telegram.messenger.Utilities.Callback2
        public /* bridge */ /* synthetic */ void run(ArrayList<ApiWrap$TopPeer> r1, String r2) {
            run2(r1, r2);
        }

        /* renamed from: run, reason: avoid collision after fix types in other method */
        public void run2(ArrayList<ApiWrap$TopPeer> r12, String r13) {
            int r0 = r12.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L18;
            ApiWrap$TopPeer r2 = r12.get(r1);
            r1 = r1 + 1;
            ApiWrap$TopPeer r22 = r2;
            ApiWrap$Peer r3 = r22.peer;
            if (r3.chat == null) goto L7;
            String r32 = r3.name();
        L11:
            ApiWrap$User r4 = r22.peer.user;
            if (r4 != null) goto L14;
        L16:
            String r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        L17:
            UserpicData r6 = new UserpicData();
            r6.colorIndex = DataTypesUtils.PeerColorIndex(r22.peer.id());
            r6.pixelSize = 48;
            r6.firstName = r32;
            r6.lastName = r42;
            String r9 = r13;
            this.val$block.append(this.val$file.pushListEntry(r6, DataTypesUtils.ComposeName(r6, "Deleted Account"), "Rating: " + r22.rating, r9, _UrlKt.FRAGMENT_ENCODE_SET));
            r13 = r9;
            goto L3
        L14:
            if (r4.isSelf == true) goto L16;
            r42 = r4.info.lastName;
            goto L17
        L7:
            ApiWrap$User r33 = r3.user;
            if (r33.isSelf == false) goto L10;
            r32 = "Saved messages";
            goto L11
        L10:
            r32 = r33.info.firstName;
            goto L11
        }
    }

    /* renamed from: com.exteragram.messenger.export.output.html.HtmlWriter$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type = null;
        static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason = null;

        static {
            int[] r0 = new int[ApiWrap$File.SkipReason.values().length];
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason = r0;
            r0[ApiWrap$File.SkipReason.Unavailable.ordinal()] = 1;     // Catch: NoSuchFieldError -> L15
        L23:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[ApiWrap$File.SkipReason.FileSize.ordinal()] = 2;     // Catch: NoSuchFieldError -> L16
        L29:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[ApiWrap$File.SkipReason.FileType.ordinal()] = 3;     // Catch: NoSuchFieldError -> L17
        L37:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[ApiWrap$File.SkipReason.None.ordinal()] = 4;     // Catch: NoSuchFieldError -> L18
        L25:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[ApiWrap$File.SkipReason.DateLimits.ordinal()] = 5;     // Catch: NoSuchFieldError -> L19
        L10:
            int[] r3 = new int[ApiWrap$DialogInfo.Type.values().length];
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type = r3;
            r3[ApiWrap$DialogInfo.Type.Self.ordinal()] = 1;     // Catch: NoSuchFieldError -> L20
        L31:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.Replies.ordinal()] = 2;     // Catch: NoSuchFieldError -> L21
        L35:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.VerifyCodes.ordinal()] = 3;     // Catch: NoSuchFieldError -> L22
            return;
        }
    }

    public enum DialogsMode extends Enum<DialogsMode> {
        private static final /* synthetic */ DialogsMode[] $VALUES = null;
        public static final DialogsMode Chats = null;
        public static final DialogsMode Left = null;
        public static final DialogsMode None = null;

        private static /* synthetic */ DialogsMode[] $values() {
            return new DialogsMode[]{None, Chats, Left};
        }

        static {
            None = new DialogsMode("None", 0);
            Chats = new DialogsMode("Chats", 1);
            Left = new DialogsMode("Left", 2);
            $VALUES = $values();
        }

        DialogsMode(String r1, int r2) {
        }

        public static DialogsMode valueOf(String r1) {
            return (DialogsMode) Enum.valueOf(DialogsMode.class, r1);
        }

        public static DialogsMode[] values() {
            return (DialogsMode[]) $VALUES.clone();
        }
    }

    public static class MessageInfo {
        public int date;
        public boolean forwarded;
        public int forwardedDate;
        public long forwardedFromId;
        public String forwardedFromName;
        public long fromId;
        public int id;
        public boolean showForwardedAsOriginal;
        public Type type;
        public long viaBotId;

        public enum Type extends Enum<Type> {
            private static final /* synthetic */ Type[] $VALUES = null;
            public static final Type Default = null;
            public static final Type Service = null;

            private static /* synthetic */ Type[] $values() {
                return new Type[]{Service, Default};
            }

            static {
                Service = new Type("Service", 0);
                Default = new Type("Default", 1);
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

        public MessageInfo() {
            this.forwardedDate = 0;
            this.forwarded = false;
            this.showForwardedAsOriginal = false;
            this.type = Type.Service;
        }
    }

    public static class Photo {
        public int date;
        public long id;
        public ApiWrap$Image image;
        public boolean spoilered;

        public Photo() {
            this.id = 0;
            this.date = 0;
            this.spoilered = false;
            this.image = new ApiWrap$Image();
        }
    }

    public static class SavedSection {
        int count;
        String label;
        String path;
        int priority;
        String type;

        public SavedSection() {
            this.priority = 0;
            this.count = 0;
        }
    }

    public static class UserpicData {
        public int colorIndex;
        public String firstName;
        public String imageLink;
        public String largeLink;
        public String lastName;
        public int pixelSize;
        public String tooltip;

        public UserpicData() {
            this.colorIndex = 0;
            this.pixelSize = 0;
            this.imageLink = _UrlKt.FRAGMENT_ENCODE_SET;
            this.largeLink = _UrlKt.FRAGMENT_ENCODE_SET;
            this.firstName = _UrlKt.FRAGMENT_ENCODE_SET;
            this.lastName = _UrlKt.FRAGMENT_ENCODE_SET;
            this.tooltip = _UrlKt.FRAGMENT_ENCODE_SET;
        }
    }

    /* renamed from: $r8$lambda$--0DixWPzMjZ6lOOvAG9W5PMq7o, reason: not valid java name */
    public static /* synthetic */ Dimension m1339$r8$lambda$0DixWPzMjZ6lOOvAG9W5PMq7o(UserpicData r0, Dimension r1) {
        int r02 = r0.pixelSize * 2;
        return new Dimension(r02, r02);
    }

    public static /* synthetic */ String $r8$lambda$2dSqEVJURf13bNOAZqePQw0jRUw(HtmlWriter r0, int r1, String r2) {
        return r0.wrapMessageLink(r1, r2);
    }

    /* renamed from: $r8$lambda$B8MGw-MXHL_evNdsjLxR-h5-KBA, reason: not valid java name */
    public static /* synthetic */ Dimension m1340$r8$lambda$B8MGwMXHL_evNdsjLxRh5KBA(Dimension r2) {
        return new Dimension(90, 160);
    }

    public static /* synthetic */ Dimension $r8$lambda$LUfwZiBWR44LYKa4oSGSBHvO5Xg(UserpicData r0, Dimension r1) {
        int r02 = r0.pixelSize * 2;
        return new Dimension(r02, r02);
    }

    public static /* synthetic */ boolean $r8$lambda$SUVYRCCFgnq2nXdHrqF2pYunQwQ(int r0, Integer r1) {
        if (r0 > r1.intValue()) goto L6;
        return true;
    L6:
        return false;
    }

    public static /* synthetic */ Integer $r8$lambda$bmNpIGVZP4Kr6xVZH5wBQCZ5maQ(SavedSection r0) {
        return Integer.valueOf(r0.priority);
    }

    public HtmlWriter() {
        this._selfColorIndex = 0;
        this._haveSections = false;
        this._summaryNeedDivider = false;
        this._userpicsCount = 0;
        this._storiesCount = 0;
        this._lastMessageIdsPerFile = new ArrayList();
        this._savedSections = new ArrayList();
    }

    public static String MakeLinks(String r6) {
        StringBuilder r0 = new StringBuilder();
        int r1 = 0;
    L3:
        int r2 = r6.indexOf("https://telegram.org/", r1);
        if (r2 < 0) goto L6;
        int r3 = r2 + 21;
    L15:
        if (r3 == r6.length()) goto L36;
        char r4 = r6.charAt(r3);
        if (r4 < 'a') goto L21;
        if (r4 > 'z') goto L21;
    L35:
        r3 = r3 + 1;
    L21:
        if (r4 < 'A') goto L25;
        if (r4 <= 'Z') goto L35;
    L25:
        if (r4 < '0') goto L29;
        if (r4 <= '9') goto L35;
    L29:
        if (r4 == '-') goto L35;
        if (r4 == '_') goto L35;
        if (r4 == '/') goto L35;
    L36:
        if (r2 <= r1) goto L3;
        String r42 = r6.substring(r2, r3 - r2);
        r0.append(r6.substring(r1, r2 - r1));
        r0.append("<a href=\"");
        r0.append(r42);
        r0.append("\">");
        r0.append(r42);
        r0.append("</a>");
        r1 = r3;
        goto L3
    L6:
        if (r0.length() != 0) goto L9;
        return r6;
    L9:
        if (r1 >= r6.length()) goto L12;
        r0.append(r6.substring(r1));
    L12:
        return r0.toString();
    }

    private String SerializeList(ArrayList<String> r5) {
        int r4 = r5.size();
        if (r4 == 1) goto L5;
        if (r4 <= 1) goto L13;
        StringBuilder r2 = new StringBuilder(r5.get(0));
        int r0 = 1;
    L8:
        int r3 = r4 - 1;
        if (r0 == r3) goto L12;
        r2.append(", ");
        r2.append(r5.get(r0));
        r0 = r0 + 1;
        goto L8
    L12:
        return r2 + " and " + r5.get(r3);
    L13:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return r5.get(0);
    }

    public static String WriteUserpicThumb(String r6, String r7, UserpicData r8) {
        return (String) HtmlContext.WriteImageThumb(r6, r7, new HtmlWriter$$ExternalSyntheticLambda4(r8), null, 0, "_thumb").first;
    }

    private HtmlContext fileWithRelativePath(String r3) {
        return new HtmlContext(HtmlContext.pathWithRelativePath(r3), this._settings.path, this._stats);
    }

    private static String messagesFile(int r2) {
        StringBuilder r0 = new StringBuilder("messages");
        if (r2 <= 0) goto L5;
        Object r22 = Integer.valueOf(r2 + 1);
    L6:
        r0.append(r22);
        r0.append(".html");
        return r0.toString();
    L5:
        r22 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    private void pushRows(String r4, List<Pair<String, String>> r5, StringBuilder r6) {
        r6.append(this._summary.pushDiv("rows " + r4));
        Iterator<Pair<String, String>> r42 = r5.iterator();
    L4:
        if (r42.hasNext() == false) goto L9;
        Pair<String, String> r52 = r42.next();
        String r0 = (String) r52.first;
        String r53 = (String) r52.second;
        if (r53.isEmpty() == true) goto L4;
        r6.append(this._summary.pushDiv("row"));
        r6.append(this._summary.pushDiv("label details"));
        r6.append(HtmlContext.SerializeString(r0));
        r6.append(this._summary.popTag());
        r6.append(this._summary.pushDiv("value bold"));
        r6.append(HtmlContext.SerializeString(r53));
        r6.append(this._summary.popTag());
        r6.append(this._summary.popTag());
        goto L4
    L9:
        r6.append(this._summary.popTag());
    }

    private static String storiesFilePath() {
        return "lists/stories.html";
    }

    private AbstractWriter.Result switchToNextChatFile(int r6) {
        String r62 = messagesFile(r6);
        AbstractWriter.Result r0 = this._chat.writeBlock(this._chat.pushTag("a", new Pair[]{new Pair("class", "pagination block_link"), new Pair("href", r62)}) + "Next messages" + this._chat.popTag());
        if (r0.isSuccess() == true) goto L5;
        return r0;
    L5:
        AbstractWriter.Result r02 = this._chat.close();
        if (r02.isSuccess() == true) goto L8;
        return r02;
    L8:
        this._chat = fileWithRelativePath(this._dialog.relativePath + r62);
        this._chatFileEmpty = true;
        return AbstractWriter.Result.Success();
    }

    private static String userpicsFilePath() {
        return "lists/profile_pictures.html";
    }

    private AbstractWriter.Result validateDialogsMode(boolean r4) {
        if (r4 == false) goto L4;
        DialogsMode r0 = DialogsMode.Left;
    L5:
        DialogsMode r1 = this._dialogsMode;
        if (r1 != r0) goto L10;
        return AbstractWriter.Result.Success();
    L10:
        if (r1 == DialogsMode.None) goto L14;
        HtmlContext r12 = this._chats;
        AbstractWriter.Result r13 = r12.writeBlock(r12.popTag());
        if (r13.isSuccess() == true) goto L14;
        return r13;
    L14:
        this._dialogsMode = r0;
        StringBuilder r02 = new StringBuilder();
        HtmlContext r14 = this._chats;
        if (r4 == false) goto L17;
        String r42 = "left chats";
    L18:
        r02.append(r14.pushAbout(r42, false));
        r02.append(this._chats.pushDiv("entry_list"));
        String r43 = r02.toString();
        return this._chats.writeBlock(r43);
    L17:
        r42 = "just a chat";
        goto L18
    L4:
        r0 = DialogsMode.Chats;
        goto L5
    }

    private String wrapMessageLink(int r4, String r5) {
        Optional<Integer> r0 = this._lastMessageIdsPerFile.stream().filter(new HtmlWriter$$ExternalSyntheticLambda5(r4)).findFirst();
        if (r0.isPresent() == true) goto L7;
        return "<a href=\"#go_to_message" + r4 + "\" onclick=\"return GoToMessage(" + r4 + ")\">" + r5 + "</a>";
    L7:
        return "<a href=\"" + messagesFile(r0.get().intValue() - this._lastMessageIdsPerFile.get(0).intValue()) + "#go_to_message" + r4 + "\">" + r5 + "</a>";
    }

    public static String wrapPeerName(long r1) {
        String r12 = ChatUtils.getInstance().getName(r1);
        if (r12.isEmpty() == false) goto L6;
        return "Deleted";
    L6:
        return r12;
    }

    public static String wrapPeerName(TLRPC.Peer r3) {
        String r32 = ChatUtils.getInstance().getName(DialogObject.getPeerDialogId(r3));
        if (r32.isEmpty() == false) goto L6;
        return "Deleted";
    L6:
        return r32;
    }

    private static String wrapUserName(Long r1) {
        String r12 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(r1).first_name;
        if (r12.isEmpty() == false) goto L6;
        return "Deleted Account";
    L6:
        return r12;
    }

    private static String wrapUserNames(ArrayList<Long> r4) {
        StringBuilder r0 = new StringBuilder();
        int r1 = r4.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L6;
        Long r3 = r4.get(r2);
        r2 = r2 + 1;
        r0.append(wrapUserName(r3));
        r0.append(" ");
        goto L3
    L6:
        return r0.toString();
    }

    private AbstractWriter.Result writeDialogOpening(int r6) {
        if (this._dialog.name.isEmpty() == true) goto L5;
    L7:
        String r0 = this._dialog.name + ' ' + this._dialog.lastName;
    L8:
        HtmlContext r2 = this._chat;
        if (this._settings.onlySinglePeer() == false) goto L11;
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        StringBuilder r1 = new StringBuilder(r2.pushHeader(r0, r3));
        r1.append(this._chat.pushDiv("page_body chat_page"));
        r1.append(this._chat.pushDiv("history"));
        if (r6 <= 0) goto L16;
        r1.append(this._chat.pushTag("a", new Pair[]{new Pair("class", "pagination block_link"), new Pair("href", messagesFile(r6 - 1))}));
        r1.append("Previous messages");
        r1.append(this._chat.popTag());
    L16:
        return this._chat.writeBlock(String.valueOf(r1));
    L11:
        r3 = this._dialogsRelativePath;
        goto L12
    L5:
        if (this._dialog.lastName.isEmpty() == false) goto L7;
        r0 = "Deleted Account";
        goto L8
    }

    private AbstractWriter.Result writeEmptySinglePeer() {
        if (this._settings.onlySinglePeer() == false) goto L13;
        if (this._messagesCount != 0) goto L13;
        AbstractWriter.Result r0 = writeDialogOpening(0);
        if (r0.isSuccess() == true) goto L10;
        return r0;
    L10:
        HtmlContext r2 = this._chat;
        int r3 = this._dateMessageId - 1;
        this._dateMessageId = r3;
        return r2.writeBlock(r2.pushServiceMessage(r3, this._dialog, this._settings.path, "No exported messages", null));
    L13:
        return AbstractWriter.Result.Success();
    }

    private AbstractWriter.Result writeFrequentContacts(ApiWrap$ContactsList r9) {
        int r6 = (r9.correspondents.size() + r9.inlineBots.size()) + r9.phoneCalls.size();
        if (r6 == 0) goto L5;
        HtmlContext r0 = fileWithRelativePath("lists/frequent.html");
        StringBuilder r1 = new StringBuilder(r0.pushHeader("Frequent contacts", "export_results.html"));
        r1.append(r0.pushDiv("page_body list_page"));
        r1.append(r0.pushAbout("_environment.aboutFrequent", false));
        r1.append(r0.pushDiv("entry_list"));
        AnonymousClass1 r2 = new AnonymousClass1(this, r1, r0);
        r2.run2(r9.correspondents, "people");
        r2.run2(r9.inlineBots, "inline bots");
        r2.run2(r9.phoneCalls, "calls");
        AbstractWriter.Result r92 = r0.writeBlock(r1.toString());
        if (r92.isSuccess() == true) goto L9;
        return r92;
    L9:
        AbstractWriter.Result r93 = r0.close();
        if (r93.isSuccess() == true) goto L12;
        return r93;
    L12:
        pushSection(3, "Frequent contacts", "frequent", r6, "lists/frequent.html");
        return AbstractWriter.Result.Success();
    L5:
        return AbstractWriter.Result.Success();
    }

    private AbstractWriter.Result writePreparedPersonal(ApiWrap$ExportPersonalInfo r11, String r12) {
        ApiWrap$ContactInfo r0 = r11.user.info;
        UserpicData r1 = new UserpicData();
        r1.colorIndex = this._selfColorIndex;
        r1.pixelSize = 90;
        boolean r2 = r12.isEmpty();
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r2 == false) goto L5;
        String r22 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        r1.largeLink = r22;
        r1.imageLink = (String) HtmlContext.WriteImageThumb(this._settings.path, r12, new HtmlWriter$$ExternalSyntheticLambda1(r1), null, -1, "_info").first;
        r1.firstName = r0.firstName;
        r1.lastName = r0.lastName;
        StringBuilder r122 = new StringBuilder(this._summary.pushDiv("personal_info clearfix"));
        r122.append(this._summary.pushDiv("pull_right userpic_wrap"));
        r122.append(this._summary.pushUserpic(r1));
        r122.append(this._summary.popTag());
        pushRows("names", StreamSharing$$ExternalSyntheticBackport0.m(new Pair("First name", r0.firstName), new Pair("Last name", r0.lastName)), r122);
        if (r0.phoneNumber.isEmpty() == true) goto L10;
        r3 = PhoneFormat.getInstance().format("+" + r0.phoneNumber);
    L10:
        Pair r13 = new Pair("Phone number", r3);
        boolean r23 = r11.user.username.isEmpty();
        ApiWrap$User r32 = r11.user;
        if (r23 == false) goto L13;
        String r24 = r32.username;
    L14:
        pushRows("info", StreamSharing$$ExternalSyntheticBackport0.m(r13, new Pair("Username", r24)), r122);
        pushRows("bio", ImageCapture$$ExternalSyntheticBackport0.m(new Pair("Bio", r11.bio)), r122);
        r122.append(this._summary.popTag());
        this._summaryNeedDivider = true;
        return this._summary.writeBlock(String.valueOf(r122));
    L13:
        r24 = "@" + r32.username;
        goto L14
    L5:
        r22 = userpicsFilePath();
        goto L6
    }

    private AbstractWriter.Result writeSavedContacts(ApiWrap$ContactsList r15) {
        ArrayList<ApiWrap$ContactInfo> r0 = r15.list;
        if (r0 == null) goto L22;
        if (r0.isEmpty() == true) goto L22;
        HtmlContext r02 = fileWithRelativePath("lists/contacts.html");
        StringBuilder r7 = new StringBuilder(r02.pushHeader("Contacts", "export_results.html"));
        r7.append(r02.pushDiv("page_body list_page"));
        int r2 = 0;
        r7.append(r02.pushAbout("_environment.aboutContacts", false));
        r7.append(r02.pushDiv("entry_list"));
        ArrayList<Integer> r8 = DataTypesUtils.SortedContactsIndices(r15);
        int r9 = r8.size();
    L8:
        if (r2 >= r9) goto L13;
        int r10 = r2 + 1;
        ApiWrap$ContactInfo r1 = r15.list.get(r8.get(r2).intValue());
        UserpicData r12 = new UserpicData();
        r12.colorIndex = r1.colorIndex;
        r12.pixelSize = 48;
        r12.firstName = r1.firstName;
        r12.lastName = r1.lastName;
        if (r1.userId.longValue() == 0) goto L12;
        r12.tooltip = "ID: " + r1.userId;
    L12:
        r7.append(r02.pushListEntry(r12, DataTypesUtils.ComposeName(r12, "Deleted Account"), PhoneFormat.getInstance().format(r1.phoneNumber), LocaleController.formatDate(r1.date), _UrlKt.FRAGMENT_ENCODE_SET));
        r2 = r10;
        goto L8
    L13:
        AbstractWriter.Result r13 = r02.writeBlock(r7.toString());
        if (r13.isSuccess() == true) goto L16;
        return r13;
    L16:
        AbstractWriter.Result r03 = r02.close();
        if (r03.isSuccess() == true) goto L19;
        return r03;
    L19:
        pushSection(2, "Contacts", "contacts", r15.list.size(), "lists/contacts.html");
        return AbstractWriter.Result.Success();
    L22:
        return AbstractWriter.Result.Success();
    }

    private AbstractWriter.Result writeSections() {
        if (this._savedSections.isEmpty() == true) goto L5;
        int r1 = 0;
        if (this._haveSections == true) goto L17;
        HtmlContext r0 = this._summary;
        if (this._summaryNeedDivider == false) goto L11;
        String r2 = "sections with_divider";
    L12:
        String r02 = r0.pushDiv(r2, _UrlKt.FRAGMENT_ENCODE_SET);
        if (this._summary.writeBlock(r02).isSuccess() == false) goto L15;
        this._haveSections = true;
        this._summaryNeedDivider = false;
        goto L17
    L15:
        return AbstractWriter.Result.Success();
    L11:
        r2 = "sections";
    L17:
        Collections.sort(this._savedSections, Comparator.comparing(new HtmlWriter$$ExternalSyntheticLambda0()));
        ArrayList<String> r03 = new ArrayList();
        ArrayList<SavedSection> r22 = this._savedSections;
        int r3 = r22.size();
    L18:
        if (r1 >= r3) goto L21;
        SavedSection r4 = r22.get(r1);
        r1 = r1 + 1;
        SavedSection r42 = r4;
        HtmlContext r5 = this._summary;
        r03.add(r5.pushSection(r42.label, r42.type, r42.count, r5.relativePath(r42.path)));
        goto L18
    L21:
        return this._summary.writeBlock(r03);
    L5:
        return AbstractWriter.Result.Success();
    }

    private AbstractWriter.Result writeSessions(ApiWrap$SessionsList r19) {
        if (r19.list.isEmpty() == true) goto L5;
        HtmlContext r7 = fileWithRelativePath("lists/sessions.html");
        StringBuilder r2 = new StringBuilder(r7.pushHeader("Sessions", "export_results.html"));
        r2.append(r7.pushDiv("page_body list_page"));
        int r4 = 0;
        r2.append(r7.pushAbout("We store session info to display your connected devices in Settings > Privacy & Security > Active Sessions.", false));
        r2.append(r7.pushDiv("entry_list"));
        ArrayList<TLRPC.TL_authorization> r3 = r19.list;
        int r5 = r3.size();
    L7:
        if (r4 >= r5) goto L18;
        TLRPC.TL_authorization r8 = r3.get(r4);
        int r42 = r4 + 1;
        TLRPC.TL_authorization r82 = r8;
        int r9 = r82.api_id;
        StringBuilder r10 = new StringBuilder();
        if (r82.app_name.isEmpty() == false) goto L11;
        String r11 = "Unknown";
    L12:
        r10.append(r11);
        r10.append(' ');
        r10.append(r82.app_version);
        String r102 = r10.toString();
        StringBuilder r12 = new StringBuilder();
        r12.append(r82.device_model);
        String r13 = ", ";
        r12.append(", ");
        r12.append(r82.platform);
        r12.append(' ');
        r12.append(r82.system_version);
        String r112 = r12.toString();
        StringBuilder r14 = new StringBuilder();
        r14.append(r82.ip);
        r14.append(" - ");
        r14.append(r82.region);
        if (r82.region.isEmpty() == false) goto L15;
    L16:
        r13 = _UrlKt.FRAGMENT_ENCODE_SET;
    L17:
        r14.append(r13);
        r14.append(r82.country);
        String r132 = r14.toString();
        StringBuilder r142 = new StringBuilder("Last active: ");
        ArrayList<TLRPC.TL_authorization> r16 = r3;
        r142.append(LocaleController.formatDate(r82.date_active));
        r2.append(r7.pushSessionListEntry(r9, r102, r112, new ArrayList(Arrays.asList(new String[]{r132, r142.toString(), "Created: " + LocaleController.formatDate(r82.date_created)})), _UrlKt.FRAGMENT_ENCODE_SET));
        r3 = r16;
        r4 = r42;
        goto L7
    L15:
        if (r82.country.isEmpty() == false) goto L17;
    L11:
        r11 = r82.app_name;
        goto L12
    L18:
        AbstractWriter.Result r22 = r7.writeBlock(r2.toString());
        if (r22.isSuccess() == true) goto L21;
        return r22;
    L21:
        AbstractWriter.Result r23 = r7.close();
        if (r23.isSuccess() == true) goto L24;
        return r23;
    L24:
        pushSection(6, "Sessions", "sessions", r19.list.size(), "lists/sessions.html");
        return AbstractWriter.Result.Success();
    L5:
        return AbstractWriter.Result.Success();
    }

    private AbstractWriter.Result writeWebSessions(ApiWrap$SessionsList r19) {
        if (r19.webList.isEmpty() == true) goto L5;
        HtmlContext r7 = fileWithRelativePath("lists/web_sessions.html");
        StringBuilder r2 = new StringBuilder(r7.pushHeader("Web sessions", "export_results.html"));
        r2.append(r7.pushDiv("page_body list_page"));
        int r4 = 0;
        r2.append(r7.pushAbout("We store this to display the websites where you logged in using authentication via Telegram. This information is shown in Settings > Privacy & Security > Active Sessions.", false));
        r2.append(r7.pushDiv("entry_list"));
        ArrayList<ApiWrap$WebSession> r3 = r19.webList;
        int r5 = r3.size();
    L7:
        if (r4 >= r5) goto L18;
        ApiWrap$WebSession r8 = r3.get(r4);
        int r42 = r4 + 1;
        ApiWrap$WebSession r82 = r8;
        int r9 = (int) (DataTypesUtils.StringBarePeerId(r82.domain()) + MediaStatus.COMMAND_EDIT_TRACKS);
        if (r82.domain().isEmpty() == false) goto L11;
        String r10 = "Unknown";
    L12:
        String r11 = r82.platform() + ", " + r82.browser();
        String r92 = r10;
        String r13 = r82.ip() + " - " + r82.region();
        StringBuilder r14 = new StringBuilder("Last active: ");
        ArrayList<ApiWrap$WebSession> r16 = r3;
        r14.append(LocaleController.formatDate(r82.lastActive()));
        ArrayList<String> r112 = new ArrayList(Arrays.asList(new String[]{r13, r14.toString(), "Created: " + LocaleController.formatDate(r82.created())}));
        if (r82.botUsername().isEmpty() == false) goto L16;
        String r32 = _UrlKt.FRAGMENT_ENCODE_SET;
    L17:
        r2.append(r7.pushSessionListEntry(r9, r92, r11, r112, r32));
        r3 = r16;
        r4 = r42;
        goto L7
    L16:
        r32 = "@" + r82.botUsername();
        goto L17
    L11:
        r10 = r82.domain();
        goto L12
    L18:
        AbstractWriter.Result r22 = r7.writeBlock(r2.toString());
        if (r22.isSuccess() == true) goto L21;
        return r22;
    L21:
        AbstractWriter.Result r23 = r7.close();
        if (r23.isSuccess() == true) goto L24;
        return r23;
    L24:
        pushSection(7, "Web sessions", CredentialsData.CREDENTIALS_TYPE_WEB, r19.webList.size(), "lists/web_sessions.html");
        return AbstractWriter.Result.Success();
    L5:
        return AbstractWriter.Result.Success();
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result finish() {
        if (this._settings.onlySinglePeer() == true) goto L5;
        AbstractWriter.Result r0 = writeSections();
        if (r0.isSuccess() == true) goto L9;
        return r0;
    L9:
        StringBuilder r02 = new StringBuilder();
        if (this._haveSections == false) goto L12;
        r02.append(this._summary.popTag());
        this._summaryNeedDivider = true;
        this._haveSections = false;
    L12:
        r02.append(this._summary.pushAbout("about telegram bla bla lorum ipsum", this._summaryNeedDivider));
        AbstractWriter.Result r03 = this._summary.writeBlock(r02.toString());
        if (r03.isSuccess() == true) goto L16;
        return r03;
    L16:
        return this._summary.close();
    L5:
        return AbstractWriter.Result.Success();
    }

    /*  JADX ERROR: ConcurrentModificationException in pass: ConstructorVisitor
        java.util.ConcurrentModificationException
        	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1095)
        	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1049)
        	at jadx.core.dex.visitors.ConstructorVisitor.insertPhiInsn(ConstructorVisitor.java:139)
        	at jadx.core.dex.visitors.ConstructorVisitor.processInvoke(ConstructorVisitor.java:91)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:56)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    public java.lang.String getTextFromAction(
    /*  JADX ERROR: ConcurrentModificationException in pass: ConstructorVisitor
        java.util.ConcurrentModificationException
        	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1095)
        	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1049)
        	at jadx.core.dex.visitors.ConstructorVisitor.insertPhiInsn(ConstructorVisitor.java:139)
        	at jadx.core.dex.visitors.ConstructorVisitor.processInvoke(ConstructorVisitor.java:91)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:56)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r12v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:405)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        */

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public String mainFilePath() {
        if (this._settings.onlySinglePeer() == false) goto L5;
        String r0 = messagesFile(0);
    L7:
        return HtmlContext.pathWithRelativePath(r0);
    L5:
        r0 = "export_results.html";
        goto L7
    }

    public void pushSection(int r2, String r3, String r4, int r5, String r6) {
        SavedSection r0 = new SavedSection();
        r0.priority = r2;
        r0.label = r3;
        r0.type = r4;
        r0.count = r5;
        r0.path = r6;
        this._savedSections.add(r0);
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result start(ExportSettings r4, OutputFile.Stats r5) {
        this._settings = r4;
        File r52 = FileManager.defaultSavePath;
        new File(r52, "css").mkdirs();
        new File(r52, "js").mkdirs();
        new File(r52, "images").mkdirs();
        FileManager.copyAssets();
        if (this._settings.onlySinglePeer() == true) goto L5;
        HtmlContext r42 = fileWithRelativePath("export_results.html");
        this._summary = r42;
        return r42.writeBlock(this._summary.pushHeader("Exported Data", _UrlKt.FRAGMENT_ENCODE_SET) + this._summary.pushDiv("page_body"));
    L5:
        return AbstractWriter.Result.Success();
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

    public AbstractWriter.Result writeDelayedPersonal(String r8) {
        ApiWrap$ExportPersonalInfo r0 = this._delayedPersonalInfo;
        if (r0 == null) goto L5;
        AbstractWriter.Result r82 = writePreparedPersonal(r0, r8);
        if (r82.isSuccess() == true) goto L9;
        return r82;
    L9:
        int r5 = this._userpicsCount;
        if (r5 == 0) goto L13;
        pushSection(4, "Profile pictures", "photos", r5, userpicsFilePath());
    L13:
        return AbstractWriter.Result.Success();
    L5:
        return AbstractWriter.Result.Success();
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeDialogEnd() {
        AbstractWriter.Result r0 = writeEmptySinglePeer();
        if (r0.isSuccess() == true) goto L5;
        return r0;
    L5:
        AbstractWriter.Result r02 = this._chat.close();
        if (r02.isSuccess() == true) goto L9;
        return r02;
    L9:
        if (this._settings.onlySinglePeer() == true) goto L11;
        int r03 = AnonymousClass2.$SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[this._dialog.type.ordinal()];
        int r2 = 3;
        if (r03 != 1) goto L15;
        String r04 = "Saved messages";
    L21:
        ApiWrap$DialogInfo r1 = this._dialog;
        ApiWrap$DialogInfo.Type r3 = r1.type;
        ApiWrap$DialogInfo.Type r4 = ApiWrap$DialogInfo.Type.Personal;
        String r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r3 != r4) goto L24;
    L27:
        String r12 = r1.lastName;
    L28:
        UserpicData r7 = new UserpicData();
        ApiWrap$DialogInfo r32 = this._dialog;
        ApiWrap$DialogInfo.Type r42 = r32.type;
        if (r42 != ApiWrap$DialogInfo.Type.Self) goto L31;
    L36:
        r7.pixelSize = 48;
        r7.colorIndex = r2;
        r7.firstName = r04;
        r7.lastName = r12;
        AbstractWriter.Result r05 = validateDialogsMode(this._dialog.isLeftChannel);
        if (r05.isSuccess() == true) goto L39;
        return r05;
    L39:
        HtmlContext r6 = this._chats;
        String r8 = DataTypesUtils.ComposeName(r7, DataTypesUtils.DeletedString(this._dialog.type));
        String r9 = DataTypesUtils.CountString(this._messagesCount, this._dialog.onlyMyMessages);
        String r10 = DataTypesUtils.TypeString(this._dialog.type);
        if (this._messagesCount <= 0) goto L43;
        r5 = this._dialog.relativePath + "messages.html";
    L43:
        return r6.writeBlock(r6.pushListEntry(r7, r8, r9, r10, r5));
    L31:
        if (r42 == ApiWrap$DialogInfo.Type.Replies) goto L36;
        if (r42 == ApiWrap$DialogInfo.Type.VerifyCodes) goto L36;
        r2 = DataTypesUtils.PeerColorIndex(r32.peerId);
        goto L36
    L24:
        if (r3 == ApiWrap$DialogInfo.Type.Bot) goto L27;
        r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L28
    L15:
        if (r03 == 2) goto L19;
        if (r03 == 3) goto L18;
        r04 = this._dialog.name;
        goto L21
    L18:
        r04 = "Verification Codes";
        goto L21
    L19:
        r04 = "Replies";
        goto L21
    L11:
        return AbstractWriter.Result.Success();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6, types: [int] */
    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeDialogSlice(ApiWrap$MessagesSlice r24) {
        int r0 = this._messagesCount;
        boolean r10 = false;
        if (r0 <= 0) goto L5;
        int r02 = (r0 - 1) / 1000;
    L6:
        MessageInfo r1 = this._lastMessageInfo;
        MessageInfo r2 = new MessageInfo();
        StringBuilder r3 = new StringBuilder();
        ArrayList<ApiWrap$Message> r11 = r24.list;
        int r12 = r11.size();
        int r4 = 0;
    L7:
        if (r4 >= r12) goto L41;
        int r13 = r4 + 1;
        ApiWrap$Message r5 = r11.get(r4);
        if (DataTypesUtils.SkipMessageByDate(r5, this._settings) == true) goto L10;
        int r42 = this._messagesCount / 1000;
        if (r02 == r42) goto L25;
        AbstractWriter.Result r03 = this._chat.writeBlock(r3.toString());
        if (r03.isSuccess() == false) goto L15;
        AbstractWriter.Result r04 = switchToNextChatFile(r42);
        if (r04.isSuccess() == false) goto L24;
        ArrayList<Integer> r05 = this._lastMessageIdsPerFile;
        if (r2 == null) goto L21;
        int r14 = r2.id;
    L22:
        r05.add(Integer.valueOf(r14));
        StringBuilder r06 = new StringBuilder();
        r1 = null;
        this._lastMessageInfo = null;
        StringBuilder r15 = r06;
        int r142 = r42;
    L23:
        MessageInfo r22 = r1;
        if (this._chatFileEmpty == false) goto L32;
        AbstractWriter.Result r07 = writeDialogOpening(r142);
        if (r07.isSuccess() == false) goto L30;
        this._chatFileEmpty = r10;
        goto L32
    L30:
        return r07;
    L32:
        int r08 = r5.date;
        if (r22 == null) goto L35;
        ?? r16 = r22.date;
    L37:
        if (DataTypesUtils.DisplayDate(r08, r16) == false) goto L39;
        HtmlContext r17 = this._chat;
        int r32 = this._dateMessageId - 1;
        this._dateMessageId = r32;
        ArrayList<ApiWrap$Message> r222 = r11;
        r15.append(r17.pushServiceMessage(r32, this._dialog, this._settings.path, LocaleController.formatDate(r08), null));
    L40:
        Pair<MessageInfo, String> r09 = this._chat.pushMessage(r5, r22, this._dialog, this._settings.path, r24.peers, this, "https://t.me/", new HtmlWriter$$ExternalSyntheticLambda2(this));
        r2 = (MessageInfo) r09.first;
        r15.append((String) r09.second);
        this._messagesCount++;
        r1 = r2;
        r4 = r13;
        r02 = r142;
        r3 = r15;
        r11 = r222;
        r10 = false;
        goto L7
    L39:
        r222 = r11;
        goto L40
    L35:
        r16 = r10;
        goto L37
    L21:
        r14 = this._lastMessageInfo.id;
        goto L22
    L24:
        return r04;
    L15:
        return r03;
    L25:
        r142 = r02;
        r15 = r3;
        goto L23
    L10:
        r4 = r13;
        goto L7
    L41:
        if (r2 == null) goto L44;
        this._lastMessageInfo = r2;
    L44:
        if (r3.toString().isEmpty() == false) goto L48;
        return AbstractWriter.Result.Success();
    L48:
        return this._chat.writeBlock(r3.toString());
    L5:
        r02 = 0;
        goto L6
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeDialogStart(ApiWrap$DialogInfo r4) {
        this._chat = fileWithRelativePath(r4.relativePath + messagesFile(0));
        this._chatFileEmpty = true;
        this._messagesCount = 0;
        this._dateMessageId = 0;
        this._lastMessageInfo = null;
        this._lastMessageIdsPerFile.clear();
        this._dialog = r4;
        return AbstractWriter.Result.Success();
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeDialogsEnd() {
        HtmlContext r0 = this._chats;
        if (r0 == null) goto L7;
        return r0.close();
    L7:
        return AbstractWriter.Result.Success();
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeDialogsStart(ApiWrap$DialogsInfo r6) {
        if (this._chats == null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("chats already initialized!");
        return null;
    L5:
        if (r6.chats.isEmpty() == false) goto L11;
        if (r6.left.isEmpty() == false) goto L11;
        return AbstractWriter.Result.Success();
    L11:
        if (this._settings.onlySinglePeer() == true) goto L13;
        this._dialogsRelativePath = "lists/chats.html";
        HtmlContext r1 = fileWithRelativePath("lists/chats.html");
        this._chats = r1;
        new StringBuilder(r1.pushHeader("Chats", "export_results.html"));
        this._chats.pushDiv("page_body list_page");
        SavedSection r12 = new SavedSection();
        r12.priority = 0;
        r12.label = "Chats";
        r12.type = "chats";
        r12.count = r6.chats.size() + r6.left.size();
        r12.path = "lists/chats.html";
        this._savedSections.add(r12);
        return writeSections();
    L13:
        return AbstractWriter.Result.Success();
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeOtherData(ApiWrap$File r7) {
        pushSection(8, "Other data", "other", 1, r7.relativePath);
        return AbstractWriter.Result.Success();
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writePersonal(ApiWrap$ExportPersonalInfo r2) {
        this._selfColorIndex = r2.user.info.colorIndex;
        if ((this._settings.types & 2) == 0) goto L7;
        this._delayedPersonalInfo = new ApiWrap$ExportPersonalInfo(r2);
        return AbstractWriter.Result.Success();
    L7:
        return writePreparedPersonal(r2, _UrlKt.FRAGMENT_ENCODE_SET);
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeSessionsList(ApiWrap$SessionsList r3) {
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
        pushSection(5, "Stories archive", "stories", this._storiesCount, storiesFilePath());
        HtmlContext r6 = this._stories;
        if (r6 == null) goto L7;
        return r6.close();
    L7:
        return AbstractWriter.Result.Success();
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeStoriesSlice(ApiWrap$StoriesSlice r21) {
        this._storiesCount -= r21.skipped;
        ArrayList<ApiWrap$Story> r2 = r21.list;
        if (r2 == null) goto L48;
        if (r2.isEmpty() == true) goto L48;
        StringBuilder r22 = new StringBuilder();
        ArrayList<ApiWrap$Story> r1 = r21.list;
        int r3 = r1.size();
        int r4 = 0;
    L8:
        if (r4 >= r3) goto L46;
        ApiWrap$Story r5 = r1.get(r4);
        r4 = r4 + 1;
        ApiWrap$Story r52 = r5;
        ApiWrap$StoryData r7 = new ApiWrap$StoryData();
        ApiWrap$File r6 = r52.file();
        ArrayList<String> r9 = new ArrayList();
        if (r52.pinned == false) goto L13;
        r9.add("Saved to Profile");
    L13:
        if (r52.expires <= 0) goto L15;
        r9.add("Expiring: ");
        r9.add(LocaleController.formatDate(r52.expires));
    L15:
        int r8 = AnonymousClass2.$SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[r6.skipReason.ordinal()];
        if (r8 != 1) goto L18;
    L30:
        String r13 = r52.file().relativePath;
        if (r52.thumb().file.relativePath.isEmpty() == false) goto L34;
        String r62 = r52.file().relativePath;
    L35:
        r7.imageLink = (String) HtmlContext.WriteImageThumb(this._settings.path, r62, new HtmlWriter$$ExternalSyntheticLambda3(), null, 0, null).first;
        int r63 = r52.date;
        if (r63 <= 0) goto L39;
        String r64 = LocaleController.formatDate(r63);
    L38:
        String r10 = r64;
        HtmlContext r65 = this._stories;
        if (r13.isEmpty() == false) goto L43;
        String r82 = "Story unavailable";
    L44:
        r22.append(r65.pushStoriesListEntry(r7, r82, r9, r10, r52.caption, "_environment.internalLinksDomain", r13));
        goto L8
    L43:
        r82 = r13;
        goto L44
    L39:
        r64 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L38
    L34:
        r62 = r52.thumb().file.relativePath;
        goto L35
    L18:
        if (r8 == 2) goto L30;
        if (r8 == 3) goto L30;
        if (r8 != 4) goto L24;
        AndroidUtilities.formatFileSize(r6.size);
        goto L30
    L24:
        if (r8 != 5) goto L28;
        Segment$$ExternalSyntheticBUOutline1.m("Skip reason while writing story path.");
        return null;
    L28:
        throw new IncompatibleClassChangeError();
    L46:
        return this._stories.writeBlock(r22.toString());
    L48:
        return AbstractWriter.Result.Success();
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeStoriesStart(int r4) {
        this._storiesCount = r4;
        if (r4 == 0) goto L5;
        this._stories = fileWithRelativePath(storiesFilePath());
        AbstractWriter.Result r3 = this._stories.writeBlock(this._stories.pushHeader("Stories archive", "export_results.html") + this._stories.pushDiv("page_body list_page") + this._stories.pushDiv("entry_list"));
        if (r3.isSuccess() == true) goto L10;
        return r3;
    L10:
        return AbstractWriter.Result.Success();
    L5:
        return AbstractWriter.Result.Success();
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeUserpicsEnd() {
        AbstractWriter.Result r0 = writeDelayedPersonal(_UrlKt.FRAGMENT_ENCODE_SET);
        if (r0.isSuccess() == true) goto L5;
        return r0;
    L5:
        HtmlContext r2 = this._userpics;
        if (r2 == null) goto L10;
        return r2.close();
    L10:
        return AbstractWriter.Result.Success();
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeUserpicsSlice(ArrayList<Photo> r13) {
        int r0 = 0;
        AbstractWriter.Result r1 = writeDelayedPersonal(r13.get(0).image.file.relativePath);
        if (r1.isSuccess() == true) goto L5;
        return r1;
    L5:
        StringBuilder r12 = new StringBuilder();
        int r2 = r13.size();
    L6:
        if (r0 >= r2) goto L32;
        Photo r3 = r13.get(r0);
        r0 = r0 + 1;
        Photo r32 = r3;
        UserpicData r5 = new UserpicData();
        r5.colorIndex = this._selfColorIndex;
        r5.pixelSize = 48;
        ApiWrap$File r4 = r32.image.file;
        int r6 = AnonymousClass2.$SwitchMap$com$exteragram$messenger$export$api$ApiWrap$File$SkipReason[r4.skipReason.ordinal()];
        if (r6 != 1) goto L10;
        String r42 = "(Photo unavailable, please try again later)";
    L16:
        String r7 = r42;
        String r9 = r32.image.file.relativePath;
        r5.imageLink = WriteUserpicThumb(this._settings.path, r9, r5);
        r5.firstName = r9;
        HtmlContext r43 = this._userpics;
        if (r9.isEmpty() == false) goto L24;
        String r62 = "Photo unavailable";
    L25:
        int r33 = r32.date;
        if (r33 <= 0) goto L29;
        String r34 = LocaleController.formatDate(r33);
    L30:
        r12.append(r43.pushListEntry(r5, r62, r7, r34, r9));
        goto L6
    L29:
        r34 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L30
    L24:
        r62 = r9;
        goto L25
    L10:
        if (r6 != 2) goto L12;
        r42 = "(Photo exceeds maximum size. Change data exporting settings to download.)";
        goto L16
    L12:
        if (r6 != 3) goto L14;
        r42 = "(Photo not included. Change data exporting settings to download.)";
        goto L16
    L14:
        if (r6 == 4) goto L17;
        r42 = null;
        goto L16
    L17:
        r42 = AndroidUtilities.formatFileSize(r4.size);
        goto L16
    L32:
        return this._userpics.writeBlock(r12.toString());
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeUserpicsStart(ApiWrap$UserpicsInfo r7) {
        int r72 = r7.count();
        this._userpicsCount = r72;
        if (r72 == 0) goto L5;
        this._userpics = fileWithRelativePath(userpicsFilePath());
        AbstractWriter.Result r73 = this._userpics.writeBlock(this._userpics.pushHeader("Profile pictures", "export_results.html") + this._userpics.pushDiv("page_body list_page") + this._userpics.pushDiv("entry_list"));
        if (r73.isSuccess() == true) goto L10;
        return r73;
    L10:
        if (this._delayedPersonalInfo != null) goto L13;
        pushSection(4, "Profile pictures", "photos", this._userpicsCount, userpicsFilePath());
    L13:
        return AbstractWriter.Result.Success();
    L5:
        return AbstractWriter.Result.Success();
    }
}
