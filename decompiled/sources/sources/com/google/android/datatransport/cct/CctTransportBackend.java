package com.google.android.datatransport.cct;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.cct.internal.AndroidClientInfo;
import com.google.android.datatransport.cct.internal.BatchedLogRequest;
import com.google.android.datatransport.cct.internal.ClientInfo;
import com.google.android.datatransport.cct.internal.ComplianceData;
import com.google.android.datatransport.cct.internal.ExperimentIds;
import com.google.android.datatransport.cct.internal.ExternalPRequestContext;
import com.google.android.datatransport.cct.internal.ExternalPrivacyContext;
import com.google.android.datatransport.cct.internal.LogEvent;
import com.google.android.datatransport.cct.internal.LogRequest;
import com.google.android.datatransport.cct.internal.LogResponse;
import com.google.android.datatransport.cct.internal.NetworkConnectionInfo;
import com.google.android.datatransport.cct.internal.QosTier;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.retries.Retries;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.gms.cast.MediaError;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.EncodingException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final class CctTransportBackend implements TransportBackend {
    private final Context applicationContext;
    private final ConnectivityManager connectivityManager;
    private final DataEncoder dataEncoder;
    final URL endPoint;
    private final int readTimeout;
    private final Clock uptimeClock;
    private final Clock wallTimeClock;

    public static final class HttpRequest {
        final String apiKey;
        final BatchedLogRequest requestBody;
        final URL url;

        public HttpRequest(URL r1, BatchedLogRequest r2, String r3) {
            this.url = r1;
            this.requestBody = r2;
            this.apiKey = r3;
        }

        public HttpRequest withUrl(URL r3) {
            return new HttpRequest(r3, this.requestBody, this.apiKey);
        }
    }

    public static final class HttpResponse {
        final int code;
        final long nextRequestMillis;
        final URL redirectUrl;

        public HttpResponse(int r1, URL r2, long r3) {
            this.code = r1;
            this.redirectUrl = r2;
            this.nextRequestMillis = r3;
        }
    }

    public static /* synthetic */ HttpResponse $r8$lambda$5Jm_DhYLwiwBKncaqypAUFDEpD8(CctTransportBackend r0, HttpRequest r1) {
        return r0.doSend(r1);
    }

    /* renamed from: $r8$lambda$ZhERf3WZ-xxKL1vqOg2FJv71EaQ, reason: not valid java name */
    public static /* synthetic */ HttpRequest m1802$r8$lambda$ZhERf3WZxxKL1vqOg2FJv71EaQ(HttpRequest r3, HttpResponse r4) {
        URL r0 = r4.redirectUrl;
        if (r0 == null) goto L6;
        Logging.d("CctTransportBackend", "Following redirect to: %s", r0);
        return r3.withUrl(r4.redirectUrl);
    L6:
        return null;
    }

    public CctTransportBackend(Context r2, Clock r3, Clock r4) {
        this(r2, r3, r4, 130000);
    }

    public CctTransportBackend(Context r2, Clock r3, Clock r4, int r5) {
        this.dataEncoder = BatchedLogRequest.createDataEncoder();
        this.applicationContext = r2;
        this.connectivityManager = (ConnectivityManager) r2.getSystemService("connectivity");
        this.endPoint = parseUrlOrThrow(CCTDestination.DEFAULT_END_POINT);
        this.uptimeClock = r4;
        this.wallTimeClock = r3;
        this.readTimeout = r5;
    }

    private HttpResponse doSend(HttpRequest r12) {
        Logging.i("CctTransportBackend", "Making request to: %s", r12.url);
        HttpURLConnection r0 = (HttpURLConnection) r12.url.openConnection();
        r0.setConnectTimeout(30000);
        r0.setReadTimeout(this.readTimeout);
        r0.setDoOutput(true);
        r0.setInstanceFollowRedirects(false);
        r0.setRequestMethod("POST");
        r0.setRequestProperty("User-Agent", String.format("datatransport/%s android/", new Object[]{"3.3.0"}));
        r0.setRequestProperty("Content-Encoding", "gzip");
        r0.setRequestProperty("Content-Type", "application/json");
        r0.setRequestProperty("Accept-Encoding", "gzip");
        String r3 = r12.apiKey;
        if (r3 == null) goto L90;
        r0.setRequestProperty("X-Goog-Api-Key", r3);
    L90:
        OutputStream r7 = r0.getOutputStream();     // Catch: IOException -> L13 EncodingException -> L15 UnknownHostException -> L17 ConnectException -> L19
        GZIPOutputStream r8 = new GZIPOutputStream(r7);     // Catch: Throwable -> L58
        this.dataEncoder.encode(r12.requestBody, new BufferedWriter(new OutputStreamWriter(r8)));     // Catch: Throwable -> L60
        r8.close();     // Catch: Throwable -> L58
        if (r7 == null) goto L21;
        r7.close();     // Catch: IOException -> L13 EncodingException -> L15 UnknownHostException -> L17 ConnectException -> L19
    L21:
        int r11 = r0.getResponseCode();
        Logging.i("CctTransportBackend", "Status Code: %d", Integer.valueOf(r11));
        Logging.d("CctTransportBackend", "Content-Type: %s", r0.getHeaderField("Content-Type"));
        Logging.d("CctTransportBackend", "Content-Encoding: %s", r0.getHeaderField("Content-Encoding"));
        if (r11 == 302) goto L57;
        if (r11 == 301) goto L57;
        if (r11 == 307) goto L57;
        if (r11 != 200) goto L31;
        InputStream r122 = r0.getInputStream();
        InputStream r02 = maybeUnGzip(r122, r0.getHeaderField("Content-Encoding"));     // Catch: Throwable -> L38
        HttpResponse r32 = new HttpResponse(r11, null, LogResponse.fromJson(new BufferedReader(new InputStreamReader(r02))).getNextRequestWaitMillis());     // Catch: Throwable -> L43
        if (r02 == null) goto L40;
        r02.close();     // Catch: Throwable -> L38
    L40:
        if (r122 == null) goto L42;
        r122.close();
    L42:
        return r32;
    L43:
        th = move-exception;
        if (r02 != null) goto L78;
    L49:
        throw th;     // Catch: Throwable -> L38
    L78:
        r02.close();     // Catch: Throwable -> L47
    L47:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L38
    L38:
        th = move-exception;
        if (r122 != null) goto L76;
    L55:
        throw th;
    L76:
        r122.close();     // Catch: Throwable -> L53
    L53:
        th = move-exception;
        th.addSuppressed(th);
        goto L55
    L31:
        return new HttpResponse(r11, null, 0);
    L57:
        return new HttpResponse(r11, new URL(r0.getHeaderField("Location")), 0);
    L60:
        th = move-exception;
        r8.close();     // Catch: Throwable -> L63
    L65:
        throw th;     // Catch: Throwable -> L58
    L63:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L58
    L58:
        th = move-exception;
        if (r7 != null) goto L82;
    L71:
        throw th;     // Catch: IOException -> L13 EncodingException -> L15 UnknownHostException -> L17 ConnectException -> L19
    L82:
        r7.close();     // Catch: Throwable -> L69
    L69:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L13 EncodingException -> L15 UnknownHostException -> L17 ConnectException -> L19
    L15:
        e = e;
    L72:
        Logging.e("CctTransportBackend", "Couldn't encode request, returning with 400", e);
        return new HttpResponse(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, null, 0);
    L19:
        e = e;
    L74:
        Logging.e("CctTransportBackend", "Couldn't open connection, returning with 500", e);
        return new HttpResponse(MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, null, 0);
    L17:
        e = e;
    L13:
        e = e;
        goto L72
    }

    private static String getMccMncOrEmpty(Context r0) {
        String r02 = getTelephonyManager(r0).getSimOperator();
        if (r02 == null) goto L5;
        return r02;
    L5:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private static int getNetSubtypeValue(NetworkInfo r1) {
        if (r1 == null) goto L4;
        int r12 = r1.getSubtype();
        if (r12 != (-1)) goto L10;
        return NetworkConnectionInfo.MobileSubtype.COMBINED.getValue();
    L10:
        if (NetworkConnectionInfo.MobileSubtype.forNumber(r12) == null) goto L12;
        return r12;
    L12:
        return 0;
    L4:
        return NetworkConnectionInfo.MobileSubtype.UNKNOWN_MOBILE_SUBTYPE.getValue();
    }

    private static int getNetTypeValue(NetworkInfo r0) {
        if (r0 != null) goto L6;
        return NetworkConnectionInfo.NetworkType.NONE.getValue();
    L6:
        return r0.getType();
    }

    private static int getPackageVersionCode(Context r2) {
        return r2.getPackageManager().getPackageInfo(r2.getPackageName(), 0).versionCode;
    L4:
        e = move-exception;
        Logging.e("CctTransportBackend", "Unable to find version code for package", e);
        return -1;
    }

    private BatchedLogRequest getRequestBody(BackendRequest r11) {
        HashMap r0 = new HashMap();
        Iterator<EventInternal> r112 = r11.getEvents().iterator();
    L4:
        if (r112.hasNext() == false) goto L9;
        EventInternal r1 = r112.next();
        String r2 = r1.getTransportName();
        if (r0.containsKey(r2) == false) goto L7;
        ((List) r0.get(r2)).add(r1);
        goto L4
    L7:
        ArrayList r3 = new ArrayList();
        r3.add(r1);
        r0.put(r2, r3);
        goto L4
    L9:
        ArrayList r113 = new ArrayList();
        Iterator r02 = r0.entrySet().iterator();
    L11:
        if (r02.hasNext() == false) goto L46;
        Map.Entry r12 = (Map.Entry) r02.next();
        EventInternal r22 = (EventInternal) ((List) r12.getValue()).get(0);
        LogRequest.Builder r23 = LogRequest.builder().setQosTier(QosTier.DEFAULT).setRequestTimeMs(this.wallTimeClock.getTime()).setRequestUptimeMs(this.uptimeClock.getTime()).setClientInfo(ClientInfo.builder().setClientType(ClientInfo.ClientType.ANDROID_FIREBASE).setAndroidClientInfo(AndroidClientInfo.builder().setSdkVersion(Integer.valueOf(r22.getInteger("sdk-version"))).setModel(r22.get("model")).setHardware(r22.get("hardware")).setDevice(r22.get("device")).setProduct(r22.get("product")).setOsBuild(r22.get("os-uild")).setManufacturer(r22.get("manufacturer")).setFingerprint(r22.get("fingerprint")).setCountry(r22.get("country")).setLocale(r22.get("locale")).setMccMnc(r22.get("mcc_mnc")).setApplicationBuild(r22.get("application_build")).build()).build());
        r23.setSource(Integer.parseInt((String) r12.getKey()));     // Catch: NumberFormatException -> L15
    L16:
        ArrayList r32 = new ArrayList();
        Iterator r13 = ((List) r12.getValue()).iterator();
    L18:
        if (r13.hasNext() == false) goto L44;
        EventInternal r4 = (EventInternal) r13.next();
        EncodedPayload r5 = r4.getEncodedPayload();
        Encoding r6 = r5.getEncoding();
        if (r6.equals(Encoding.of("proto")) == true) goto L21;
        if (r6.equals(Encoding.of("json")) == true) goto L24;
        Logging.w("CctTransportBackend", "Received event of unsupported encoding %s. Skipping...", r6);
        goto L18
    L24:
        LogEvent.Builder r52 = LogEvent.jsonBuilder(new String(r5.getBytes(), Charset.forName("UTF-8")));
    L25:
        r52.setEventTimeMs(r4.getEventMillis()).setEventUptimeMs(r4.getUptimeMillis()).setTimezoneOffsetSeconds(r4.getLong("tz-offset")).setNetworkConnectionInfo(NetworkConnectionInfo.builder().setNetworkType(NetworkConnectionInfo.NetworkType.forNumber(r4.getInteger("net-type"))).setMobileSubtype(NetworkConnectionInfo.MobileSubtype.forNumber(r4.getInteger("mobile-subtype"))).build());
        if (r4.getCode() == null) goto L29;
        r52.setEventCode(r4.getCode());
    L29:
        if (r4.getProductId() == null) goto L32;
        r52.setComplianceData(ComplianceData.builder().setPrivacyContext(ExternalPrivacyContext.builder().setPrequest(ExternalPRequestContext.builder().setOriginAssociatedProductId(r4.getProductId()).build()).build()).setProductIdOrigin(ComplianceData.ProductIdOrigin.EVENT_OVERRIDE).build());
    L32:
        if (r4.getExperimentIdsClear() == null) goto L34;
    L35:
        ExperimentIds.Builder r62 = ExperimentIds.builder();
        if (r4.getExperimentIdsClear() == null) goto L39;
        r62.setClearBlob(r4.getExperimentIdsClear());
    L39:
        if (r4.getExperimentIdsEncrypted() == null) goto L41;
        r62.setEncryptedBlob(r4.getExperimentIdsEncrypted());
    L41:
        r52.setExperimentIds(r62.build());
    L42:
        r32.add(r52.build());
        goto L18
    L34:
        if (r4.getExperimentIdsEncrypted() == null) goto L42;
    L21:
        r52 = LogEvent.protoBuilder(r5.getBytes());
        goto L25
    L44:
        r23.setLogEvents(r32);
        r113.add(r23.build());
    L15:
        r23.setSource((String) r12.getKey());
        goto L16
    L46:
        return BatchedLogRequest.create(r113);
    }

    private static TelephonyManager getTelephonyManager(Context r1) {
        return (TelephonyManager) r1.getSystemService("phone");
    }

    public static long getTzOffset() {
        Calendar.getInstance();
        return TimeZone.getDefault().getOffset(Calendar.getInstance().getTimeInMillis()) / 1000;
    }

    private static InputStream maybeUnGzip(InputStream r1, String r2) {
        if ("gzip".equals(r2) == true) goto L5;
        return r1;
    L5:
        return new GZIPInputStream(r1);
    }

    private static URL parseUrlOrThrow(String r4) {
        return new URL(r4);
    L4:
        e = move-exception;
        throw new IllegalArgumentException("Invalid url: " + r4, e);
    }

    @Override // com.google.android.datatransport.runtime.backends.TransportBackend
    public EventInternal decorate(EventInternal r5) {
        NetworkInfo r0 = this.connectivityManager.getActiveNetworkInfo();
        return r5.toBuilder().addMetadata("sdk-version", Build.VERSION.SDK_INT).addMetadata("model", Build.MODEL).addMetadata("hardware", Build.HARDWARE).addMetadata("device", Build.DEVICE).addMetadata("product", Build.PRODUCT).addMetadata("os-uild", Build.ID).addMetadata("manufacturer", Build.MANUFACTURER).addMetadata("fingerprint", Build.FINGERPRINT).addMetadata("tz-offset", getTzOffset()).addMetadata("net-type", getNetTypeValue(r0)).addMetadata("mobile-subtype", getNetSubtypeValue(r0)).addMetadata("country", Locale.getDefault().getCountry()).addMetadata("locale", Locale.getDefault().getLanguage()).addMetadata("mcc_mnc", getMccMncOrEmpty(this.applicationContext)).addMetadata("application_build", Integer.toString(getPackageVersionCode(this.applicationContext))).build();
    }

    @Override // com.google.android.datatransport.runtime.backends.TransportBackend
    public BackendResponse send(BackendRequest r5) {
        BatchedLogRequest r0 = getRequestBody(r5);
        URL r1 = this.endPoint;
        String r3 = null;
        if (r5.getExtras() == null) goto L35;
        CCTDestination r52 = CCTDestination.fromByteArray(r5.getExtras());     // Catch: IllegalArgumentException -> L11
        if (r52.getAPIKey() == null) goto L8;
        r3 = r52.getAPIKey();     // Catch: IllegalArgumentException -> L11
    L8:
        if (r52.getEndPoint() == null) goto L35;
        r1 = parseUrlOrThrow(r52.getEndPoint());     // Catch: IllegalArgumentException -> L11
    L12:
        return BackendResponse.fatalError();
    L35:
        HttpResponse r4 = (HttpResponse) Retries.retry(5, new HttpRequest(r1, r0, r3), new CctTransportBackend$$ExternalSyntheticLambda0(this), new CctTransportBackend$$ExternalSyntheticLambda1());     // Catch: IOException -> L30
        int r53 = r4.code;     // Catch: IOException -> L30
        if (r53 != 200) goto L18;
        return BackendResponse.ok(r4.nextRequestMillis);
    L18:
        if (r53 >= 500) goto L29;
        if (r53 == 404) goto L29;
        if (r53 != 400) goto L27;
        return BackendResponse.invalidPayload();
    L27:
        return BackendResponse.fatalError();
    L29:
        return BackendResponse.transientError();
    L30:
        e = move-exception;
        Logging.e("CctTransportBackend", "Could not make request to the backend", e);
        return BackendResponse.transientError();
    }
}
