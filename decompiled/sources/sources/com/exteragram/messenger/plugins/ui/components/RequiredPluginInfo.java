package com.exteragram.messenger.plugins.ui.components;

import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0005\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0010J\u0006\u0010\u0012\u001a\u00020\u0010J\u0006\u0010\u0013\u001a\u00020\u0003R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\f\u0010\u000e¨\u0006\u0015"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo;", _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;)V", "name", "requiredVersion", "currentVersion", "downloadUrl", "state", _UrlKt.FRAGMENT_ENCODE_SET, "isSatisfied", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "title", _UrlKt.FRAGMENT_ENCODE_SET, MediaTrack.ROLE_SUBTITLE, "actionText", "contentKey", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class RequiredPluginInfo {
    public static final Companion Companion = null;
    public static final int STATE_DISABLED = 1;
    public static final int STATE_MISSING = 0;
    public static final int STATE_OUTDATED = 2;
    public static final int STATE_SATISFIED = 3;

    @JvmField
    public String currentVersion;

    @JvmField
    public String downloadUrl;

    @JvmField
    public final String id;

    @JvmField
    public String name;

    @JvmField
    public String requiredVersion;

    @JvmField
    public int state;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/RequiredPluginInfo$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "STATE_MISSING", _UrlKt.FRAGMENT_ENCODE_SET, "STATE_DISABLED", "STATE_OUTDATED", "STATE_SATISFIED", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public RequiredPluginInfo(String r1) {
        this.id = r1;
    }

    public final CharSequence actionText() {
        int r1 = this.state;
        if (r1 == 1) goto L15;
        if (r1 == 2) goto L13;
        if (r1 == 3) goto L11;
        return LocaleController.getString(R.string.PluginRequiredDownload);
    L11:
        return LocaleController.getString(R.string.PluginRequiredInstalled);
    L13:
        return LocaleController.getString(R.string.PluginRequiredUpdate);
    L15:
        return LocaleController.getString(R.string.Enable);
    }

    public final String contentKey() {
        return this.state + "|" + this.name + "|" + this.currentVersion + "|" + this.requiredVersion;
    }

    public final boolean isSatisfied() {
        if (this.state != 3) goto L6;
        return true;
    L6:
        return false;
    }

    public final CharSequence subtitle() {
        int r0 = this.state;
        if (r0 == 1) goto L33;
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r0 != 2) goto L7;
        int r02 = R.string.PluginRequiredOutdated;
        String r1 = this.currentVersion;
        if (r1 != null) goto L26;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L26:
        String r3 = this.requiredVersion;
        if (r3 == null) goto L31;
        r2 = r3;
    L31:
        return LocaleController.formatString(r02, new Object[]{r1, r2});
    L7:
        if (r0 == 3) goto L17;
        String r03 = this.requiredVersion;
        if (r03 == null) goto L16;
        if (r03.length() == 0) goto L16;
        return LocaleController.formatString(R.string.PluginRequiredVersion, new Object[]{this.requiredVersion});
    L16:
        return LocaleController.getString(R.string.PluginRequiredMissing);
    L17:
        int r04 = R.string.PluginVersionValue;
        String r32 = this.currentVersion;
        if (r32 == null) goto L22;
        r2 = r32;
    L22:
        return LocaleController.formatString(r04, new Object[]{r2});
    L33:
        return LocaleController.getString(R.string.PluginRequiredDisabled);
    }

    public final CharSequence title() {
        String r0 = this.name;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return this.id;
    }
}
