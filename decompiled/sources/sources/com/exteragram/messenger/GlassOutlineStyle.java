package com.exteragram.messenger;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/GlassOutlineStyle;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "GLARE", "SOLID", "HIDDEN", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public enum GlassOutlineStyle extends Enum<GlassOutlineStyle> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ GlassOutlineStyle[] $VALUES = null;
    public static final GlassOutlineStyle GLARE = null;
    public static final GlassOutlineStyle HIDDEN = null;
    public static final GlassOutlineStyle SOLID = null;

    private static final /* synthetic */ GlassOutlineStyle[] $values() {
        return new GlassOutlineStyle[]{GLARE, SOLID, HIDDEN};
    }

    static {
        GLARE = new GlassOutlineStyle("GLARE", 0);
        SOLID = new GlassOutlineStyle("SOLID", 1);
        HIDDEN = new GlassOutlineStyle("HIDDEN", 2);
        GlassOutlineStyle[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    GlassOutlineStyle(String r1, int r2) {
    }

    public static EnumEntries<GlassOutlineStyle> getEntries() {
        return $ENTRIES;
    }

    public static GlassOutlineStyle valueOf(String r1) {
        return (GlassOutlineStyle) Enum.valueOf(GlassOutlineStyle.class, r1);
    }

    public static GlassOutlineStyle[] values() {
        return (GlassOutlineStyle[]) $VALUES.clone();
    }
}
