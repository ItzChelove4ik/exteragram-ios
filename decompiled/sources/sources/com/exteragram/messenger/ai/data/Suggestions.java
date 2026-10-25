package com.exteragram.messenger.ai.data;

/* loaded from: classes4.dex */
public enum Suggestions extends Enum<Suggestions> {
    private static final /* synthetic */ Suggestions[] $VALUES = null;
    public static final Suggestions ASSISTANT = null;
    public static final Suggestions PROOFREADER = null;
    public static final Suggestions SUMMARIZER = null;
    private final Role role;

    private static /* synthetic */ Suggestions[] $values() {
        return new Suggestions[]{ASSISTANT, SUMMARIZER, PROOFREADER};
    }

    static {
        ASSISTANT = new Suggestions("ASSISTANT", 0, "Assistant", "You are a helpful personal assistant inside a chat app.\nAdapt to the user's tone, language, and context. Keep answers practical, concise, and easy to act on.\nAsk a clarifying question only when the request is ambiguous enough that answering directly would likely be wrong.", 5372981976804366741L);
        SUMMARIZER = new Suggestions("SUMMARIZER", 1, "Summarizer", "Summarize the provided message or conversation without replying to it.\nPreserve the important facts, decisions, requests, names, dates, and next steps. Omit filler and repeated details.\nKeep the summary under 3 short sentences and under 60 words.", 5188311512791393083L);
        PROOFREADER = new Suggestions("PROOFREADER", 2, "Proofreader", "Proofread and improve the user's text while preserving its meaning, language, and intent.\nFix grammar, spelling, punctuation, awkward phrasing, and clarity issues. Keep the original tone unless a cleaner wording is clearly better.\nReturn only the revised text unless the user explicitly asks for explanations.", 5334882760735598374L);
        $VALUES = $values();
    }

    Suggestions(String r1, int r2, String r3, String r4, long r5) {
        this.role = new Role(r3, r4).setEmojiId(r5).setSuggestion(true);
    }

    public static Suggestions valueOf(String r1) {
        return (Suggestions) Enum.valueOf(Suggestions.class, r1);
    }

    public static Suggestions[] values() {
        return (Suggestions[]) $VALUES.clone();
    }

    public Role getRole() {
        return this.role;
    }
}
