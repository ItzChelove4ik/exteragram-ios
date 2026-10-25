package androidx.car.app;

import androidx.car.app.model.Template;
import androidx.car.app.navigation.model.NavigationTemplate;
import com.google.android.gms.cast.MediaTrack;
import com.google.common.collect.ImmutableSet;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes3.dex */
public class SessionInfo {
    private static final ImmutableSet<Class<? extends Template>> CLUSTER_SUPPORTED_TEMPLATES_API_6 = null;
    private static final ImmutableSet<Class<? extends Template>> CLUSTER_SUPPORTED_TEMPLATES_LESS_THAN_API_6 = null;
    public static final SessionInfo DEFAULT_SESSION_INFO = null;
    public static final int DISPLAY_TYPE_CLUSTER = 1;
    public static final int DISPLAY_TYPE_MAIN = 0;
    private static final char DIVIDER = '/';
    private final int mDisplayType;
    private final String mSessionId;

    static {
        CLUSTER_SUPPORTED_TEMPLATES_API_6 = ImmutableSet.of(NavigationTemplate.class);
        CLUSTER_SUPPORTED_TEMPLATES_LESS_THAN_API_6 = ImmutableSet.of();
        DEFAULT_SESSION_INFO = new SessionInfo(0, MediaTrack.ROLE_MAIN);
    }

    private SessionInfo() {
        this.mSessionId = MediaTrack.ROLE_MAIN;
        this.mDisplayType = 0;
    }

    public SessionInfo(int r1, String r2) {
        this.mDisplayType = r1;
        this.mSessionId = r2;
    }

    public boolean equals(Object r5) {
        if (r5 != null) goto L6;
        return false;
    L6:
        if ((r5 instanceof SessionInfo) == true) goto L9;
        return false;
    L9:
        if (r5 != this) goto L11;
        return true;
    L11:
        SessionInfo r52 = (SessionInfo) r5;
        if (getSessionId().equals(r52.getSessionId()) == true) goto L14;
    L16:
        return false;
    L14:
        if (getDisplayType() != r52.getDisplayType()) goto L16;
        return true;
    }

    public int getDisplayType() {
        return this.mDisplayType;
    }

    public String getSessionId() {
        return this.mSessionId;
    }

    public Set<Class<? extends Template>> getSupportedTemplates(int r2) {
        if (this.mDisplayType == 1) goto L5;
        return null;
    L5:
        if (r2 < 6) goto L9;
        return CLUSTER_SUPPORTED_TEMPLATES_API_6;
    L9:
        return CLUSTER_SUPPORTED_TEMPLATES_LESS_THAN_API_6;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mSessionId, Integer.valueOf(this.mDisplayType)});
    }

    public String toString() {
        return String.valueOf(this.mDisplayType) + DIVIDER + this.mSessionId;
    }
}
