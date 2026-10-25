package com.exteragram.messenger.export.api;

import com.exteragram.messenger.export.api.ApiWrap$File;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public class ApiWrap$Message {
    public TLRPC.MessageAction action;
    public int date;
    public int edited;
    public boolean forwarded;
    public int forwardedDate;
    public long forwardedFromId;
    public String forwardedFromName;
    public long fromId;
    public int id;
    public ArrayList<ArrayList<ApiWrap$HistoryMessageMarkupButton>> inlineButtonRows;
    public ApiWrap$Media media;
    public boolean out;
    public Object parsedAction;
    public long peerId;
    public ArrayList<ApiWrap$Reaction> reactions;
    public int replyToMsgId;
    public long replyToPeerId;
    public long savedFromChatId;
    public long selfId;
    public boolean showForwardedAsOriginal;
    public String signature;
    public ApiWrap$File.SkipReason skipReason;
    public ArrayList<ApiWrap$TextPart> text;
    public long viaBotId;

    public ApiWrap$Message() {
        this.id = 0;
        this.date = 0;
        this.edited = 0;
        this.fromId = 0;
        this.peerId = 0;
        this.selfId = 0;
        this.forwardedFromId = 0;
        this.forwardedFromName = _UrlKt.FRAGMENT_ENCODE_SET;
        this.forwardedDate = 0;
        this.forwarded = false;
        this.showForwardedAsOriginal = false;
        this.savedFromChatId = 0;
        this.signature = _UrlKt.FRAGMENT_ENCODE_SET;
        this.replyToMsgId = 0;
        this.replyToPeerId = 0;
        this.text = new ArrayList();
        this.reactions = new ArrayList();
        this.skipReason = ApiWrap$File.SkipReason.None;
        this.out = false;
        this.media = new ApiWrap$Media();
    }

    public ApiWrap$File getFile() {
        Object r0 = this.parsedAction;
        if ((r0 instanceof ApiWrap$ActionSuggestProfilePhoto) == false) goto L7;
        return ApiWrap$ActionSuggestProfilePhoto.m1309$$Nest$fgetphoto((ApiWrap$ActionSuggestProfilePhoto) r0).image.file;
    L7:
        if ((r0 instanceof ApiWrap$ActionChatEditPhoto) == true) goto L9;
        ApiWrap$Media r2 = this.media;
        if (r2 == null) goto L15;
        return r2.getFile();
    L15:
        return new ApiWrap$File();
    L9:
        return ApiWrap$ActionChatEditPhoto.m1308$$Nest$fgetphoto((ApiWrap$ActionChatEditPhoto) r0).image.file;
    }
}
