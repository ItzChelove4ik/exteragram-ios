package org.telegram.messenger.chromecast;

import android.net.Uri;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;

/* loaded from: classes5.dex */
public class ChromecastMedia {
    public final String externalPath;
    public final int height;
    public final Uri internalUri;
    public final MediaMetadata mediaMetadata;
    public final String mimeType;
    public final int width;

    public static class Builder {
        private MediaMetadata baseMetadata;
        private final String externalPath;
        private int height;
        private final Uri internalUri;
        private final String mimeType;
        private String subtitle;
        private String title;
        private int width;

        /* renamed from: -$$Nest$fgetexternalPath, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m5144$$Nest$fgetexternalPath(Builder r0) {
            return r0.externalPath;
        }

        /* renamed from: -$$Nest$fgetheight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m5145$$Nest$fgetheight(Builder r0) {
            return r0.height;
        }

        /* renamed from: -$$Nest$fgetinternalUri, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Uri m5146$$Nest$fgetinternalUri(Builder r0) {
            return r0.internalUri;
        }

        /* renamed from: -$$Nest$fgetmimeType, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m5147$$Nest$fgetmimeType(Builder r0) {
            return r0.mimeType;
        }

        /* renamed from: -$$Nest$fgetwidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m5148$$Nest$fgetwidth(Builder r0) {
            return r0.width;
        }

        /* renamed from: -$$Nest$mbuildMetadata, reason: not valid java name */
        public static /* bridge */ /* synthetic */ MediaMetadata m5149$$Nest$mbuildMetadata(Builder r0) {
            return r0.buildMetadata();
        }

        private Builder(String r1, Uri r2, String r3) {
            this.mimeType = r1;
            this.internalUri = r2;
            this.externalPath = r3;
        }

        private MediaMetadata buildMetadata() {
            String r0 = this.mimeType;
            r0.getClass();
            int r2 = 3;
            char r4 = 65535;
            switch(r0.hashCode()) {
                case -1487394660: goto L18;
                case -979127466: goto L14;
                case -879258763: goto L10;
                case 1331848029: goto L6;
                default: goto L21;
            };
        L21:
            switch(r4) {
                case 0: goto L28;
                case 1: goto L27;
                case 2: goto L28;
                case 3: goto L27;
                default: goto L23;
            };
        L27:
            r2 = 1;
        L29:
            MediaMetadata r02 = this.baseMetadata;
            if (r02 != null) goto L32;
            r02 = new MediaMetadata(r2);
        L32:
            StringBuilder r1 = new StringBuilder();
            StringBuilder r22 = new StringBuilder();
            String r3 = this.title;
            if (r3 == null) goto L35;
            r1.append(r3);
        L35:
            String r32 = this.subtitle;
            if (r32 == null) goto L38;
            r22.append(r32);
        L38:
            int r33 = this.width;
            if (r33 == 0) goto L47;
            if (this.height == 0) goto L47;
            r02.putInt(MediaMetadata.KEY_WIDTH, r33);
            r02.putInt(MediaMetadata.KEY_HEIGHT, this.height);
            if (r22.length() <= 0) goto L45;
            r22.append(' ');
        L45:
            r22.append("(");
            r22.append(this.width);
            r22.append("x");
            r22.append(this.height);
            r22.append(")");
        L47:
            if (r1.length() <= 0) goto L49;
            r02.putString(MediaMetadata.KEY_TITLE, r1.toString());
        L51:
            if (r22.length() <= 0) goto L53;
            r02.putString(MediaMetadata.KEY_SUBTITLE, r22.toString());
        L53:
            return r02;
        L49:
            r02.putString(MediaMetadata.KEY_TITLE, "No Title");
            goto L51
        L28:
            r2 = 4;
            goto L29
        L23:
            if (this.mimeType.startsWith("audio/") == true) goto L29;
            return null;
        L6:
            if (r0.equals("video/mp4") == false) goto L21;
            r4 = 3;
            goto L21
        L10:
            if (r0.equals("image/png") == false) goto L21;
            r4 = 2;
            goto L21
        L14:
            if (r0.equals("application/x-mpegURL") == false) goto L21;
            r4 = 1;
            goto L21
        L18:
            if (r0.equals("image/jpeg") == false) goto L21;
            r4 = 0;
            goto L21
        }

        public static Builder fromUri(Uri r1, String r2, String r3) {
            return new Builder(r3, r1, r2);
        }

        public ChromecastMedia build() {
            return new ChromecastMedia(this, null);
        }

        public Builder setMetadata(MediaMetadata r1) {
            this.baseMetadata = r1;
            return this;
        }

        public Builder setSize(int r1, int r2) {
            this.width = r1;
            this.height = r2;
            return this;
        }

        public Builder setSubtitle(String r1) {
            this.subtitle = r1;
            return this;
        }

        public Builder setTitle(String r1) {
            this.title = r1;
            return this;
        }
    }

    private ChromecastMedia(Builder r2) {
        this.mimeType = Builder.m5147$$Nest$fgetmimeType(r2);
        this.mediaMetadata = Builder.m5149$$Nest$mbuildMetadata(r2);
        this.internalUri = Builder.m5146$$Nest$fgetinternalUri(r2);
        this.externalPath = Builder.m5144$$Nest$fgetexternalPath(r2);
        this.width = Builder.m5148$$Nest$fgetwidth(r2);
        this.height = Builder.m5145$$Nest$fgetheight(r2);
    }

    public /* synthetic */ ChromecastMedia(Builder r1, ChromecastMediaIA r2) {
        this(r1);
    }

    public MediaInfo buildMediaInfo(String r3, String r4) {
        return new MediaInfo.Builder(getExternalUri(r3) + r4).setContentType(this.mimeType).setMetadata(this.mediaMetadata).setStreamType(1).build();
    }

    public String getExternalUri(String r1) {
        return ChromecastFileServer.getUrlToSource(r1, this.externalPath);
    }
}
