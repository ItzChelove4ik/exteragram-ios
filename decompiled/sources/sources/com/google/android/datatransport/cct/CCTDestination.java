package com.google.android.datatransport.cct;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.runtime.EncodedDestination;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public final class CCTDestination implements EncodedDestination {
    private static final String DEFAULT_API_KEY = null;
    static final String DEFAULT_END_POINT = null;
    public static final CCTDestination INSTANCE = null;
    static final String LEGACY_END_POINT = null;
    public static final CCTDestination LEGACY_INSTANCE = null;
    private static final Set<Encoding> SUPPORTED_ENCODINGS = null;
    private final String apiKey;
    private final String endPoint;

    static {
        String r0 = StringMerger.mergeStrings("hts/frbslgiggolai.o/0clgbthfra=snpoo", "tp:/ieaeogn.ogepscmvc/o/ac?omtjo_rt3");
        DEFAULT_END_POINT = r0;
        String r1 = StringMerger.mergeStrings("hts/frbslgigp.ogepscmv/ieo/eaybtho", "tp:/ieaeogn-agolai.o/1frlglgc/aclg");
        LEGACY_END_POINT = r1;
        String r2 = StringMerger.mergeStrings("AzSCki82AwsLzKd5O8zo", "IayckHiZRO1EFl1aGoK");
        DEFAULT_API_KEY = r2;
        SUPPORTED_ENCODINGS = Collections.unmodifiableSet(new HashSet(Arrays.asList(new Encoding[]{Encoding.of("proto"), Encoding.of("json")})));
        INSTANCE = new CCTDestination(r0, null);
        LEGACY_INSTANCE = new CCTDestination(r1, r2);
    }

    public CCTDestination(String r1, String r2) {
        this.endPoint = r1;
        this.apiKey = r2;
    }

    public static CCTDestination fromByteArray(byte[] r4) {
        String r0 = new String(r4, Charset.forName("UTF-8"));
        String r1 = null;
        if (r0.startsWith("1$") == false) goto L18;
        String[] r02 = r0.substring(2).split(Pattern.quote("\\"), 2);
        if (r02.length != 2) goto L16;
        String r42 = r02[0];
        if (r42.isEmpty() == true) goto L14;
        String r03 = r02[1];
        if (r03.isEmpty() == true) goto L13;
        r1 = r03;
    L13:
        return new CCTDestination(r42, r1);
    L14:
        f$$ExternalSyntheticBUOutline1.m("Missing endpoint in CCTDestination extras");
        return null;
    L16:
        f$$ExternalSyntheticBUOutline1.m("Extra is not a valid encoded LegacyFlgDestination");
        return null;
    L18:
        f$$ExternalSyntheticBUOutline1.m("Version marker missing from extras");
        return null;
    }

    public byte[] asByteArray() {
        String r0 = this.apiKey;
        if (r0 == null) goto L5;
    L8:
        String r3 = this.endPoint;
        if (r0 != null) goto L12;
        r0 = _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        return String.format("%s%s%s%s", new Object[]{"1$", r3, "\\", r0}).getBytes(Charset.forName("UTF-8"));
    L5:
        if (this.endPoint != null) goto L8;
        return null;
    }

    public String getAPIKey() {
        return this.apiKey;
    }

    public String getEndPoint() {
        return this.endPoint;
    }

    @Override // com.google.android.datatransport.runtime.Destination
    public byte[] getExtras() {
        return asByteArray();
    }

    @Override // com.google.android.datatransport.runtime.Destination
    public String getName() {
        return "cct";
    }

    @Override // com.google.android.datatransport.runtime.EncodedDestination
    public Set<Encoding> getSupportedEncodings() {
        return SUPPORTED_ENCODINGS;
    }
}
