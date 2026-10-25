package com.exteragram.messenger.export.api;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public class ApiWrap$Document {
    public int date;
    public int duration;
    public ApiWrap$File file;
    public int height;
    public long id;
    public boolean isAnimated;
    public boolean isAudioFile;
    public boolean isSticker;
    public boolean isVideoFile;
    public boolean isVideoMessage;
    public boolean isVoiceMessage;
    public String mime;
    public String name;
    public String songPerformer;
    public String songTitle;
    public boolean spoilered;
    public TLRPC.Document sticker;
    public String stickerEmoji;
    public ApiWrap$Image thumb;
    public int width;

    public ApiWrap$Document() {
        this.id = 0;
        this.date = 0;
        this.width = 0;
        this.height = 0;
        this.isSticker = false;
        this.isAnimated = false;
        this.isVideoMessage = false;
        this.isVoiceMessage = false;
        this.isVideoFile = false;
        this.isAudioFile = false;
        this.spoilered = false;
    }
}
