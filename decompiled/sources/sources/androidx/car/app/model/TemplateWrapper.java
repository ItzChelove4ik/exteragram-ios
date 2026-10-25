package androidx.car.app.model;

import androidx.car.app.utils.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public final class TemplateWrapper {
    private int mCurrentTaskStep;
    private String mId;
    private boolean mIsRefresh;
    private Template mTemplate;
    private List<TemplateInfo> mTemplateInfoForScreenStack;

    private TemplateWrapper() {
        this.mTemplateInfoForScreenStack = new ArrayList();
        this.mTemplate = null;
        this.mId = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private TemplateWrapper(Template r2, String r3) {
        this.mTemplateInfoForScreenStack = new ArrayList();
        this.mTemplate = r2;
        this.mId = r3;
    }

    public static TemplateWrapper copyOf(TemplateWrapper r2) {
        TemplateWrapper r0 = wrap(r2.getTemplate(), r2.getId());
        r0.setRefresh(r2.isRefresh());
        r0.setCurrentTaskStep(r2.getCurrentTaskStep());
        List<TemplateInfo> r22 = r2.getTemplateInfosForScreenStack();
        if (r22 == null) goto L5;
        r0.setTemplateInfosForScreenStack(r22);
    L5:
        return r0;
    }

    private static String createRandomId() {
        return UUID.randomUUID().toString();
    }

    public static TemplateWrapper wrap(Template r1) {
        return wrap(r1, createRandomId());
    }

    public static TemplateWrapper wrap(Template r1, String r2) {
        Objects.requireNonNull(r1);
        Objects.requireNonNull(r2);
        return new TemplateWrapper(r1, r2);
    }

    public int getCurrentTaskStep() {
        return this.mCurrentTaskStep;
    }

    public String getId() {
        String r0 = this.mId;
        Objects.requireNonNull(r0);
        return r0;
    }

    public Template getTemplate() {
        Template r0 = this.mTemplate;
        Objects.requireNonNull(r0);
        return r0;
    }

    public List<TemplateInfo> getTemplateInfosForScreenStack() {
        return CollectionUtils.emptyIfNull(this.mTemplateInfoForScreenStack);
    }

    public boolean isRefresh() {
        return this.mIsRefresh;
    }

    public void setCurrentTaskStep(int r1) {
        this.mCurrentTaskStep = r1;
    }

    public void setId(String r1) {
        this.mId = r1;
    }

    public void setRefresh(boolean r1) {
        this.mIsRefresh = r1;
    }

    public void setTemplate(Template r1) {
        this.mTemplate = r1;
    }

    public void setTemplateInfosForScreenStack(List<TemplateInfo> r1) {
        this.mTemplateInfoForScreenStack = r1;
    }

    public String toString() {
        return "[template: " + this.mTemplate + ", ID: " + this.mId + "]";
    }
}
