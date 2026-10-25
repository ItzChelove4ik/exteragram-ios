package com.exteragram.messenger.ai.data;

import com.exteragram.messenger.ai.AiConfig;
import java.io.Serializable;
import java.util.Objects;

/* loaded from: classes4.dex */
public class Role implements Comparable<Role>, Serializable {
    private long emojiId;
    private boolean isSuggestion;
    private String name;
    private String prompt;

    public Role(String r1, String r2) {
        this.name = r1;
        this.prompt = r2;
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(Role r1) {
        return this.name.compareTo(r1.getName());
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Role r1) {
        return compareTo2(r1);
    }

    public boolean equals(Object r3) {
        if (this != r3) goto L5;
        return true;
    L5:
        if (r3 != null) goto L7;
        return false;
    L7:
        if (getClass() == r3.getClass()) goto L10;
        return false;
    L10:
        return this.name.equals(((Role) r3).name);
    }

    public long getEmojiId() {
        return this.emojiId;
    }

    public String getName() {
        return this.name;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public boolean isSelected() {
        return Objects.equals(AiConfig.getSelectedRole(), this.name);
    }

    public boolean isSuggestion() {
        return this.isSuggestion;
    }

    public Role setEmojiId(long r1) {
        this.emojiId = r1;
        return this;
    }

    public Role setSuggestion(boolean r1) {
        this.isSuggestion = r1;
        return this;
    }
}
