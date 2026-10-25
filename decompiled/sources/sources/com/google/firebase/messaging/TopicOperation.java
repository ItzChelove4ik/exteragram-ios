package com.google.firebase.messaging;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
final class TopicOperation {
    private static final Pattern TOPIC_NAME_REGEXP = null;
    private final String operation;
    private final String serializedString;
    private final String topic;

    static {
        TOPIC_NAME_REGEXP = Pattern.compile("[a-zA-Z0-9-_.~%]{1,900}");
    }

    private TopicOperation(String r2, String r3) {
        this.topic = normalizeTopicOrThrow(r3, r2);
        this.operation = r2;
        this.serializedString = r2 + "!" + r3;
    }

    public static TopicOperation from(String r3) {
        if (TextUtils.isEmpty(r3) == false) goto L5;
        return null;
    L5:
        String[] r32 = r3.split("!", -1);
        if (r32.length == 2) goto L9;
        return null;
    L9:
        return new TopicOperation(r32[0], r32[1]);
    }

    private static String normalizeTopicOrThrow(String r1, String r2) {
        if (r1 != null) goto L4;
    L6:
        if (r1 != null) goto L8;
    L10:
        FieldSet$$ExternalSyntheticBUOutline0.m("Invalid topic name: %s does not match the allowed format %s.", new Object[]{r1, "[a-zA-Z0-9-_.~%]{1,900}"});
        return null;
    L8:
        if (TOPIC_NAME_REGEXP.matcher(r1).matches() == false) goto L10;
        return r1;
    L4:
        if (r1.startsWith("/topics/") == false) goto L6;
        Log.w("FirebaseMessaging", String.format("Format /topics/topic-name is deprecated. Only 'topic-name' should be used in %s.", new Object[]{r2}));
        r1 = r1.substring(8);
        goto L6
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof TopicOperation) == true) goto L5;
        return false;
    L5:
        TopicOperation r42 = (TopicOperation) r4;
        if (this.topic.equals(r42.topic) == true) goto L8;
    L11:
        return false;
    L8:
        if (this.operation.equals(r42.operation) == false) goto L11;
        return true;
    }

    public String getOperation() {
        return this.operation;
    }

    public String getTopic() {
        return this.topic;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.operation, this.topic});
    }

    public String serialize() {
        return this.serializedString;
    }
}
