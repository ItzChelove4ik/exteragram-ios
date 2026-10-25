package org.chromium.support_lib_boundary;

import android.content.Context;
import android.webkit.WebView;
import com.sun.jna.Native$$ExternalSyntheticBUOutline0;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* loaded from: classes5.dex */
public interface WebViewBuilderBoundaryInterface {

    @Retention(RetentionPolicy.SOURCE)
    public @interface Baseline {
        public static final int DEFAULT = 0;
    }

    public static class Config implements Consumer<BiConsumer<Integer, Object>> {
        public int baseline;
        Map<String, Boolean> mJavascriptInterfaceNames;
        List<Object> mJavascriptInterfaceObjects;
        List<List<String>> mJavascriptInterfaceOriginPatterns;
        public String profileName;
        public boolean restrictJavascriptInterface;

        public Config() {
            this.baseline = 0;
            this.mJavascriptInterfaceObjects = new ArrayList();
            this.mJavascriptInterfaceNames = new LinkedHashMap();
            this.mJavascriptInterfaceOriginPatterns = new ArrayList();
        }

        @Override // java.util.function.Consumer
        public /* bridge */ /* synthetic */ void accept(BiConsumer<Integer, Object> r1) {
            accept2(r1);
        }

        /* renamed from: accept, reason: avoid collision after fix types in other method */
        public void accept2(BiConsumer<Integer, Object> r5) {
            r5.accept(0, Integer.valueOf(this.baseline));
            r5.accept(2, Boolean.valueOf(this.restrictJavascriptInterface));
            r5.accept(1, new Object[]{this.mJavascriptInterfaceObjects, new ArrayList(this.mJavascriptInterfaceNames.keySet()), this.mJavascriptInterfaceOriginPatterns});
            if (this.profileName == null) goto L6;
            r5.accept(3, this.profileName);
            return;
        }

        public void addJavascriptInterface(Object r2, String r3, List<String> r4) {
            if (this.mJavascriptInterfaceNames.containsKey(r3) == true) goto L6;
            this.mJavascriptInterfaceObjects.add(r2);
            this.mJavascriptInterfaceNames.put(r3, Boolean.TRUE);
            this.mJavascriptInterfaceOriginPatterns.add(r4);
            return;
        L6:
            Native$$ExternalSyntheticBUOutline0.m("A duplicate JavaScript interface was provided for \"", r3, "\"");
        }
    }

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ConfigField {
        public static final int BASELINE = 0;
        public static final int JAVASCRIPT_INTERFACE = 1;
        public static final int PROFILE_NAME = 3;
        public static final int RESTRICT_JAVASCRIPT_INTERFACE = 2;
    }

    void applyTo(WebView r1, Consumer<BiConsumer<Integer, Object>> r2);

    WebView build(Context r1, Consumer<BiConsumer<Integer, Object>> r2);
}
