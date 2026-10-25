package com.exteragram.messenger.ai.data;

import android.text.TextUtils;
import com.exteragram.messenger.ai.AiConfig;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public class Service implements Serializable {
    private String id;
    private String key;
    private Kind kind;
    private String model;
    private boolean reasoningEnabled;
    private String url;

    public enum Kind extends Enum<Kind> {
        private static final /* synthetic */ Kind[] $VALUES = null;
        public static final Kind ON_DEVICE = null;
        public static final Kind REST = null;

        private static /* synthetic */ Kind[] $values() {
            return new Kind[]{REST, ON_DEVICE};
        }

        static {
            REST = new Kind("REST", 0);
            ON_DEVICE = new Kind("ON_DEVICE", 1);
            $VALUES = $values();
        }

        Kind(String r1, int r2) {
        }

        public static Kind valueOf(String r1) {
            return (Kind) Enum.valueOf(Kind.class, r1);
        }

        public static Kind[] values() {
            return (Kind[]) $VALUES.clone();
        }
    }

    public Service(String r7, String r8, String r9, String r10) {
        this(r7, r8, r9, r10, false);
    }

    public Service(String r1, String r2, String r3, String r4, boolean r5) {
        this.id = r1;
        this.url = r2;
        this.model = r3;
        this.key = r4;
        this.reasoningEnabled = r5;
    }

    public Service(String r8, String r9, String r10, boolean r11) {
        this(UUID.randomUUID().toString(), r8, r9, r10, r11);
    }

    public boolean ensureId() {
        String r0 = this.id;
        if (r0 != null) goto L5;
    L9:
        this.id = UUID.randomUUID().toString();
        return true;
    L5:
        if (r0.isEmpty() == true) goto L9;
        return false;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L19:
        return false;
    L8:
        if (getClass() != r5.getClass()) goto L19;
        Service r52 = (Service) r5;
        if (getKind() != r52.getKind()) goto L19;
        if (Objects.equals(this.url, r52.url) == false) goto L19;
        if (Objects.equals(this.model, r52.model) == false) goto L19;
        if (Objects.equals(this.key, r52.key) == false) goto L19;
        return true;
    }

    public String getId() {
        ensureId();
        return this.id;
    }

    public String getKey() {
        return this.key;
    }

    public Kind getKind() {
        Kind r0 = this.kind;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return Kind.REST;
    }

    public int getLegacyHash() {
        return (this.url + this.model + this.key).hashCode();
    }

    public String getModel() {
        return this.model;
    }

    public String getShortModel() {
        String r2 = this.model;
        if (r2 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        String r22 = r2.split("/")[r2.length - 1];
        int r0 = r22.indexOf(58);
        if (r0 != (-1)) goto L9;
        return r22;
    L9:
        return r22.substring(0, r0);
    }

    public String getUrl() {
        return this.url;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{getKind(), this.url, this.model, this.key});
    }

    public boolean isOnDevice() {
        if (getKind() != Kind.ON_DEVICE) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isReasoningEnabled() {
        return this.reasoningEnabled;
    }

    public boolean isSelected() {
        String r0 = AiConfig.getSelectedServiceId();
        if (TextUtils.isEmpty(r0) == true) goto L7;
        return Objects.equals(r0, getId());
    L7:
        return Objects.equals(AiConfig.getSelectedService().getId(), getId());
    }

    public void setId(String r1) {
        this.id = r1;
    }

    public void setKind(Kind r1) {
        this.kind = r1;
    }

    public void setModel(String r1) {
        this.model = r1;
    }

    public void setReasoningEnabled(boolean r1) {
        this.reasoningEnabled = r1;
    }
}
