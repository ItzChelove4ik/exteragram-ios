package com.exteragram.messenger.ai.network.backend;

import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ai.data.Message;
import com.exteragram.messenger.ai.data.Role;
import com.exteragram.messenger.ai.data.Service;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class BackendRequest extends RecordTag {
    private final Message currentMessage;
    private final List<Message> history;
    private final int maxOutputTokens;
    private final Role role;
    private final Service service;
    private final boolean stream;
    private final float temperature;

    private /* synthetic */ boolean $record$equals(Object r3) {
        if ((r3 instanceof BackendRequest) == false) goto L20;
        BackendRequest r32 = (BackendRequest) r3;
        if (this.stream == r32.stream) goto L7;
        return false;
    L7:
        if (this.maxOutputTokens == r32.maxOutputTokens) goto L9;
        return false;
    L9:
        if (this.temperature == r32.temperature) goto L11;
        return false;
    L11:
        if (Objects.equals(this.service, r32.service) == true) goto L13;
        return false;
    L13:
        if (Objects.equals(this.role, r32.role) == true) goto L15;
        return false;
    L15:
        if (Objects.equals(this.history, r32.history) == true) goto L17;
        return false;
    L17:
        if (Objects.equals(this.currentMessage, r32.currentMessage) == false) goto L28;
        return true;
    L28:
        return false;
    L20:
        return false;
    }

    private /* synthetic */ Object[] $record$getFieldsAsObjects() {
        return new Object[]{this.service, this.role, this.history, this.currentMessage, Boolean.valueOf(this.stream), Float.valueOf(this.temperature), Integer.valueOf(this.maxOutputTokens)};
    }

    public BackendRequest(Service r1, Role r2, List<Message> r3, Message r4, boolean r5, float r6, int r7) {
        this.service = r1;
        this.role = r2;
        this.history = r3;
        this.currentMessage = r4;
        this.stream = r5;
        this.temperature = r6;
        this.maxOutputTokens = r7;
    }

    public Message currentMessage() {
        return this.currentMessage;
    }

    public final boolean equals(Object r1) {
        return $record$equals(r1);
    }

    public final int hashCode() {
        return BackendRequest$$ExternalSyntheticRecord0.m(this.stream, this.maxOutputTokens, this.temperature, this.service, this.role, this.history, this.currentMessage);
    }

    public List<Message> history() {
        return this.history;
    }

    public int maxOutputTokens() {
        return this.maxOutputTokens;
    }

    public Role role() {
        return this.role;
    }

    public Service service() {
        return this.service;
    }

    public boolean stream() {
        return this.stream;
    }

    public float temperature() {
        return this.temperature;
    }

    public final String toString() {
        return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), BackendRequest.class, "service;role;history;currentMessage;stream;temperature;maxOutputTokens");
    }
}
