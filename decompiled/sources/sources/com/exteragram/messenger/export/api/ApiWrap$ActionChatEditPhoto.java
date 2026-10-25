package com.exteragram.messenger.export.api;

import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import com.exteragram.messenger.export.output.html.HtmlWriter;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class ApiWrap$ActionChatEditPhoto extends RecordTag {
    private final HtmlWriter.Photo photo;

    private /* synthetic */ boolean $record$equals(Object r2) {
        if ((r2 instanceof ApiWrap$ActionChatEditPhoto) == true) goto L5;
        return false;
    L5:
        if (Objects.equals(this.photo, ((ApiWrap$ActionChatEditPhoto) r2).photo) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ Object[] $record$getFieldsAsObjects() {
        return new Object[]{this.photo};
    }

    /* renamed from: -$$Nest$fgetphoto, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HtmlWriter.Photo m1308$$Nest$fgetphoto(ApiWrap$ActionChatEditPhoto r0) {
        return r0.photo;
    }

    public ApiWrap$ActionChatEditPhoto(HtmlWriter.Photo r1) {
        this.photo = r1;
    }

    public final boolean equals(Object r1) {
        return $record$equals(r1);
    }

    public final int hashCode() {
        return ApiWrap$ActionChatEditPhoto$$ExternalSyntheticRecord0.m(this.photo);
    }

    public HtmlWriter.Photo photo() {
        return this.photo;
    }

    public final String toString() {
        return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), ApiWrap$ActionChatEditPhoto.class, "photo");
    }
}
