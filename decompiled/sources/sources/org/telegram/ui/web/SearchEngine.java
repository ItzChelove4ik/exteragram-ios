package org.telegram.ui.web;

import android.text.TextUtils;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import j$.net.URLEncoder;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;

/* loaded from: classes3.dex */
public class SearchEngine {
    private static ArrayList<SearchEngine> searchEngines;
    public final String autocomplete_url;
    public final String homepage;
    public final String name;
    public final String privacy_policy_url;
    public final String search_url;

    public SearchEngine(String r1, String r2, String r3, String r4) {
        this.name = r1;
        this.homepage = null;
        this.search_url = r2;
        this.autocomplete_url = r3;
        this.privacy_policy_url = r4;
    }

    public SearchEngine(String r1, String r2, String r3, String r4, String r5) {
        this.name = r1;
        this.homepage = r2;
        this.search_url = r3;
        this.autocomplete_url = r4;
        this.privacy_policy_url = r5;
    }

    public static SearchEngine getCurrent() {
        ArrayList<SearchEngine> r0 = getSearchEngines();
        if (r0.isEmpty() == false) goto L7;
        return new SearchEngine("Google", "https://www.google.com/search?q=", "https://suggestqueries.google.com/complete/search?client=chrome&amp;q=", "https://policies.google.com/privacy");
    L7:
        return r0.get(Utilities.clamp(SharedConfig.searchEngineType, r0.size() - 1, 0));
    }

    public static ArrayList<SearchEngine> getSearchEngines() {
        if (searchEngines != null) goto L10;
        searchEngines = new ArrayList();
        int r1 = 1;
    L5:
        String r2 = nullable(LocaleController.getString("SearchEngine" + r1 + "Name"));
        if (r2 == null) goto L7;
        searchEngines.add(new SearchEngine(r2, nullable(LocaleController.getString("SearchEngine" + r1 + "SearchURL")), nullable(LocaleController.getString("SearchEngine" + r1 + "AutocompleteURL")), nullable(LocaleController.getString("SearchEngine" + r1 + "PrivacyPolicyURL"))));
        r1 = r1 + 1;
        goto L5
    L7:
        searchEngines.add(1, ExteraConfig.getYandexSearchEngine());
    L10:
        return searchEngines;
    }

    private static String nullable(String r1) {
        if (r1 != null) goto L4;
        return null;
    L4:
        if (r1.startsWith("LOC_ERR") == false) goto L6;
        return null;
    L6:
        if ("reserved".equals(r1) == true) goto L12;
        return r1;
    L12:
        return null;
    }

    public ArrayList<String> extractSuggestions(String r6) {
        ArrayList<String> r5 = new ArrayList();
        int r0 = 0;
        JSONArray r1 = new JSONArray(r6).getJSONArray(1);     // Catch: Exception -> L8
        int r2 = 0;
    L4:
        if (r2 >= r1.length()) goto L28;
        r5.add(r1.getString(r2));     // Catch: Exception -> L8
        r2 = r2 + 1;
    L28:
        return r5;
    L8:
        e = move-exception;
        FileLog.e(e);
        JSONArray r12 = new JSONObject(r6).getJSONObject("gossip").getJSONArray("results");     // Catch: Exception -> L15
        int r22 = 0;
    L11:
        if (r22 >= r12.length()) goto L28;
        r5.add(r12.getJSONObject(r22).getString(PluginsConstants.Settings.KEY));     // Catch: Exception -> L15
        r22 = r22 + 1;
    L15:
        e = move-exception;
        FileLog.e(e);
        JSONArray r13 = new JSONArray(r6);     // Catch: Exception -> L24
    L18:
        if (r0 >= r13.length()) goto L28;
        String r62 = r13.getJSONObject(r0).getString("phrase");     // Catch: Exception -> L24
        if (TextUtils.isEmpty(r62) == true) goto L26;
        r5.add(r62);     // Catch: Exception -> L24
    L26:
        r0 = r0 + 1;
    L24:
        e = move-exception;
        FileLog.e(e);
        goto L28
    }

    public String getAutocompleteURL(String r2) {
        if (this.autocomplete_url != null) goto L7;
        return null;
    L7:
        return this.autocomplete_url + URLEncoder.encode(r2);
    }

    public String getHomepage() {
        return this.homepage;
    }

    public String getSearchURL(String r2) {
        if (this.search_url != null) goto L7;
        return null;
    L7:
        return this.search_url + URLEncoder.encode(r2);
    }
}
