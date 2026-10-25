package com.exteragram.messenger.ai.data;

import java.util.Objects;

/* loaded from: classes4.dex */
public final class Message {
    private final String content;
    private byte[] imageData;
    private String mimeType;
    private final String role;

    public Message(String r1, String r2) {
        this.role = r1;
        this.content = r2;
    }

    public Message(String r1, String r2, byte[] r3, String r4) {
        this.role = r1;
        this.content = r2;
        this.imageData = r3;
        this.mimeType = r4;
    }

    public String content() {
        return this.content;
    }

    public boolean equals(Object r3) {
        if (this != r3) goto L5;
        return true;
    L5:
        if (r3 != null) goto L7;
        return false;
    L7:
        if (Message.class == r3.getClass()) goto L10;
        return false;
    L10:
        return this.content.equals(((Message) r3).content);
    }

    public byte[] getImageData() {
        return this.imageData;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.role, this.content});
    }

    public String role() {
        return this.role;
    }
}
