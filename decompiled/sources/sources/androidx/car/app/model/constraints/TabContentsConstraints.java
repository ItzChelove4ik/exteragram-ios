package androidx.car.app.model.constraints;

import androidx.car.app.model.GridTemplate;
import androidx.car.app.model.ListTemplate;
import androidx.car.app.model.MessageTemplate;
import androidx.car.app.model.PaneTemplate;
import androidx.car.app.model.SearchTemplate;
import androidx.car.app.model.Template;
import androidx.car.app.navigation.model.NavigationTemplate;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes3.dex */
public class TabContentsConstraints {
    public static final TabContentsConstraints API_7 = null;
    public static final TabContentsConstraints DEFAULT = null;
    private HashSet<Class<? extends Template>> mAllowedTemplateTypes;

    static {
        DEFAULT = new TabContentsConstraints(Arrays.asList(new Class[]{ListTemplate.class, PaneTemplate.class, GridTemplate.class, MessageTemplate.class, SearchTemplate.class}));
        API_7 = new TabContentsConstraints(Arrays.asList(new Class[]{ListTemplate.class, PaneTemplate.class, GridTemplate.class, MessageTemplate.class, SearchTemplate.class, NavigationTemplate.class}));
    }

    private TabContentsConstraints(List<Class<? extends Template>> r2) {
        this.mAllowedTemplateTypes = new HashSet(r2);
    }

    public void validateOrThrow(Template r2) {
        if (this.mAllowedTemplateTypes.contains(r2.getClass()) == false) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("Type is not allowed in tabs: ".concat(r2.getClass().getSimpleName()));
    }
}
