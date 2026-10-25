package com.exteragram.messenger.export.api;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public class ApiWrap$File {
    public byte[] content;
    public int dcId;
    public ApiWrap$FileLocation location;
    public String relativePath;
    public long size;
    public SkipReason skipReason;
    public String suggestedPath;

    public enum SkipReason extends Enum<SkipReason> {
        private static final /* synthetic */ SkipReason[] $VALUES = null;
        public static final SkipReason DateLimits = null;
        public static final SkipReason FileSize = null;
        public static final SkipReason FileType = null;
        public static final SkipReason None = null;
        public static final SkipReason Unavailable = null;

        private static /* synthetic */ SkipReason[] $values() {
            return new SkipReason[]{None, Unavailable, FileType, FileSize, DateLimits};
        }

        static {
            None = new SkipReason("None", 0);
            Unavailable = new SkipReason("Unavailable", 1);
            FileType = new SkipReason("FileType", 2);
            FileSize = new SkipReason("FileSize", 3);
            DateLimits = new SkipReason("DateLimits", 4);
            $VALUES = $values();
        }

        SkipReason(String r1, int r2) {
        }

        public static SkipReason valueOf(String r1) {
            return (SkipReason) Enum.valueOf(SkipReason.class, r1);
        }

        public static SkipReason[] values() {
            return (SkipReason[]) $VALUES.clone();
        }
    }

    public ApiWrap$File() {
        this.size = 0;
        this.dcId = 0;
        this.relativePath = _UrlKt.FRAGMENT_ENCODE_SET;
        this.skipReason = SkipReason.None;
    }
}
