package com.stripe.android.net;

import java.net.InetAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes5.dex */
public class StripeSSLSocketFactory extends SSLSocketFactory {
    private final boolean tlsv11Supported;
    private final boolean tlsv12Supported;
    private final SSLSocketFactory under;

    public StripeSSLSocketFactory() {
        this.under = HttpsURLConnection.getDefaultSSLSocketFactory();
        int r0 = 0;
        String[] r1 = SSLContext.getDefault().getSupportedSSLParameters().getProtocols();     // Catch: NoSuchAlgorithmException -> L5
    L6:
        int r2 = r1.length;
        boolean r3 = false;
        boolean r4 = false;
    L7:
        if (r0 >= r2) goto L15;
        String r5 = r1[r0];
        if (r5.equals("TLSv1.1") == false) goto L12;
        r3 = true;
    L14:
        r0 = r0 + 1;
        goto L7
    L12:
        if (r5.equals("TLSv1.2") == false) goto L14;
        r4 = true;
        goto L14
    L15:
        this.tlsv11Supported = r3;
        this.tlsv12Supported = r4;
        return;
    L5:
        r1 = new String[0];
        goto L6
    }

    private Socket fixupSocket(Socket r3) {
        if ((r3 instanceof SSLSocket) == true) goto L5;
        return r3;
    L5:
        SSLSocket r32 = (SSLSocket) r3;
        HashSet r0 = new HashSet(Arrays.asList(r32.getEnabledProtocols()));
        if (this.tlsv11Supported == false) goto L9;
        r0.add("TLSv1.1");
    L9:
        if (this.tlsv12Supported == false) goto L11;
        r0.add("TLSv1.2");
    L11:
        r32.setEnabledProtocols((String[]) r0.toArray(new String[0]));
        return r32;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String r2, int r3) {
        return fixupSocket(this.under.createSocket(r2, r3));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String r2, int r3, InetAddress r4, int r5) {
        return fixupSocket(this.under.createSocket(r2, r3, r4, r5));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress r2, int r3) {
        return fixupSocket(this.under.createSocket(r2, r3));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress r2, int r3, InetAddress r4, int r5) {
        return fixupSocket(this.under.createSocket(r2, r3, r4, r5));
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket r2, String r3, int r4, boolean r5) {
        return fixupSocket(this.under.createSocket(r2, r3, r4, r5));
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.under.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.under.getSupportedCipherSuites();
    }
}
