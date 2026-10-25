package com.exteragram.messenger.plugins;

import com.chaquo.python.internal.Common;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u001e\u0018\u0000 =2\u00020\u0001:\u0001=B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u001d\u001a\u00020\u0003J\b\u0010\u001e\u001a\u0004\u0018\u00010\u0003J\u0010\u0010\u001f\u001a\u00020 2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003J\b\u0010!\u001a\u0004\u0018\u00010\u0003J\u0010\u0010\"\u001a\u00020 2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003J\b\u0010#\u001a\u0004\u0018\u00010\u0003J\u0010\u0010$\u001a\u00020 2\b\u0010\f\u001a\u0004\u0018\u00010\u0003J\u0006\u0010%\u001a\u00020\u0003J\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010&\u001a\u00020 2\u0006\u0010'\u001a\u00020\u000eJ\b\u0010(\u001a\u0004\u0018\u00010\u0013J\u0010\u0010)\u001a\u00020 2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u0006\u0010*\u001a\u00020\u000eJ\b\u0010+\u001a\u0004\u0018\u00010\u0003J\u0006\u0010,\u001a\u00020\u0011J\b\u0010-\u001a\u0004\u0018\u00010\u0003J\u0010\u0010.\u001a\u00020 2\b\u0010\n\u001a\u0004\u0018\u00010\u0003J\u000e\u0010/\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0016J\b\u00100\u001a\u0004\u0018\u00010\u0003J\u0010\u00101\u001a\u00020 2\b\u0010\b\u001a\u0004\u0018\u00010\u0003J\b\u00102\u001a\u0004\u0018\u00010\u0003J\u0010\u00103\u001a\u00020 2\b\u0010\t\u001a\u0004\u0018\u00010\u0003J\u0016\u00104\u001a\u00020 2\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0016J\u0006\u0010\u0014\u001a\u00020\u000eJ\u000e\u00105\u001a\u00020 2\u0006\u00106\u001a\u00020\u000eJ\b\u00107\u001a\u0004\u0018\u00010\u0003J\u0010\u00108\u001a\u00020 2\b\u00109\u001a\u0004\u0018\u00010\u0003J\u0013\u0010:\u001a\u00020\u000e2\b\u0010;\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010<\u001a\u00020\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006>"}, d2 = {"Lcom/exteragram/messenger/plugins/Plugin;", _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "name", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "engine", "appVersion", "sdkVersion", "version", MediaTrack.ROLE_DESCRIPTION, "author", "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "pack", "index", _UrlKt.FRAGMENT_ENCODE_SET, "error", _UrlKt.FRAGMENT_ENCODE_SET, "isNotResponding", Common.ASSET_REQUIREMENTS, _UrlKt.FRAGMENT_ENCODE_SET, "cachedEngine", "Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;", "getCachedEngine", "()Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;", "setCachedEngine", "(Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;)V", "getId", "getDescription", "setDescription", _UrlKt.FRAGMENT_ENCODE_SET, "getEngine", "setEngine", "getAuthor", "setAuthor", "getName", "setEnabled", "enabled", "getError", "setError", "hasError", "getPack", "getIndex", "getVersion", "setVersion", "getRequirements", "getAppVersion", "setAppVersion", "getSdkVersion", "setSdkVersion", "setRequirements", "setNotResponding", "notResponding", "getIcon", "setIcon", "link", "equals", "other", "hashCode", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Plugin {
    public static final Companion Companion = null;
    private String appVersion;
    private String author;
    private transient PluginsController.PluginsEngine cachedEngine;
    private String description;
    private String engine;
    private volatile Throwable error;
    private final String id;
    private int index;
    private volatile boolean isEnabled;
    private volatile boolean isNotResponding;
    private final String name;
    private String pack;
    private List<String> requirements;
    private String sdkVersion;
    private String version;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002¨\u0006\b"}, d2 = {"Lcom/exteragram/messenger/plugins/Plugin$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isIconValid", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.TYPE_INPUT, _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ boolean access$isIconValid(Companion r0, String r1) {
            return r0.isIconValid(r1);
        }

        private final boolean isIconValid(String r2) {
            if (r2 != null) goto L4;
            return false;
        L4:
            if (new Regex("^[a-zA-Z][a-zA-Z0-9_]*/\\d+$").matches(r2) == false) goto L9;
            return true;
        L9:
            return false;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public Plugin(String r2, String r3) {
        this.id = r2;
        this.name = r3;
        this.appVersion = ">=12.10.6";
        this.sdkVersion = ">=" + PythonPluginsEngine.Companion.getSDK_VERSION();
        this.version = "1.0";
        this.description = LocaleController.getString(R.string.PluginNoDescription);
        this.author = LocaleController.getString(R.string.PluginNoAuthor);
        this.index = -1;
        this.requirements = new ArrayList();
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof Plugin) == true) goto L5;
        if (this == r2) goto L9;
        return false;
    L9:
        return true;
    L5:
        return Intrinsics.areEqual(((Plugin) r2).id, this.id);
    }

    public final String getAppVersion() {
        return this.appVersion;
    }

    public final String getAuthor() {
        return this.author;
    }

    public final PluginsController.PluginsEngine getCachedEngine() {
        return this.cachedEngine;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getEngine() {
        return this.engine;
    }

    public final Throwable getError() {
        return this.error;
    }

    public final String getIcon() {
        String r0 = this.pack;
        if (r0 == null) goto L9;
        int r2 = this.index;
        if (r2 >= 0) goto L8;
        return null;
    L8:
        return r0 + "/" + r2;
    L9:
        return null;
    }

    public final String getId() {
        return this.id;
    }

    public final int getIndex() {
        return this.index;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPack() {
        return this.pack;
    }

    public final List<String> getRequirements() {
        return this.requirements;
    }

    public final String getSdkVersion() {
        return this.sdkVersion;
    }

    public final String getVersion() {
        return this.version;
    }

    public final boolean hasError() {
        if (this.error == null) goto L6;
        return true;
    L6:
        return false;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public final boolean isEnabled() {
        if (hasError() == false) goto L5;
        return false;
    L5:
        if (this.isEnabled == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final boolean isNotResponding() {
        return this.isNotResponding;
    }

    public final void setAppVersion(String r1) {
        this.appVersion = r1;
    }

    public final void setAuthor(String r1) {
        this.author = r1;
    }

    public final void setCachedEngine(PluginsController.PluginsEngine r1) {
        this.cachedEngine = r1;
    }

    public final void setDescription(String r1) {
        this.description = r1;
    }

    public final void setEnabled(boolean r1) {
        if (r1 == true) goto L4;
    L6:
        boolean r12 = false;
    L7:
        this.isEnabled = r12;
        return;
    L4:
        if (hasError() == true) goto L6;
        r12 = true;
        goto L7
    }

    public final void setEngine(String r1) {
        this.engine = r1;
    }

    public final void setError(Throwable r1) {
        this.error = r1;
        if (hasError() == false) goto L6;
        this.isEnabled = false;
        return;
    }

    public final void setIcon(String r8) {
        if (Companion.access$isIconValid(Companion, r8) == false) goto L10;
        int r82 = StringsKt.lastIndexOf$default(r8, '/', 0, false, 6, null);
        if (r82 != (-1)) goto L8;
        return;
    L8:
        this.pack = r8.substring(0, r82);
        this.index = Integer.parseInt(r8.substring(r82 + 1));
        return;
    }

    public final void setNotResponding(boolean r1) {
        this.isNotResponding = r1;
    }

    public final void setRequirements(List<String> r1) {
        this.requirements = r1;
    }

    public final void setSdkVersion(String r1) {
        this.sdkVersion = r1;
    }

    public final void setVersion(String r1) {
        this.version = r1;
    }
}
