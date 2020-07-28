package com.thecocktail.hackerrank.infrastructure.net;

import javax.net.ssl.*;
import java.net.Socket;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class TrustAllHosts {

    public static SSLContext trustAllHosts() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509ExtendedTrustManager() {
                        @Override public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        @Override public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                        @Override public void checkServerTrusted(X509Certificate[] certs, String authType) { }
                        @Override public void checkClientTrusted(X509Certificate[] xcs, String string, Socket socket) { }
                        @Override public void checkServerTrusted(X509Certificate[] xcs, String string, Socket socket) { }
                        @Override public void checkClientTrusted(X509Certificate[] xcs, String string, SSLEngine sslEngine) { }
                        @Override public void checkServerTrusted(X509Certificate[] xcs, String string, SSLEngine sslEngine) { }
                    }
            };
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = (hostname, session) -> true;
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
            return sc;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
