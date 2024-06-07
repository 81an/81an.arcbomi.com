package io.grpc.util;

import com.google.common.io.BaseEncoding;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

/* loaded from: classes2.dex */
public final class CertificateUtils {
    public static X509Certificate[] getX509Certificates(InputStream inputStream) throws CertificateException {
        return (X509Certificate[]) CertificateFactory.getInstance("X.509").generateCertificates(inputStream).toArray(new X509Certificate[0]);
    }

    public static PrivateKey getPrivateKey(InputStream inputStream) throws UnsupportedEncodingException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String readLine;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        do {
            readLine = bufferedReader.readLine();
            if (readLine == null) {
                break;
            }
        } while (!"-----BEGIN PRIVATE KEY-----".equals(readLine));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine2 = bufferedReader.readLine();
            if (readLine2 == null || "-----END PRIVATE KEY-----".equals(readLine2)) {
                break;
            }
            sb.append(readLine2);
        }
        PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(BaseEncoding.base64().decode(sb.toString()));
        try {
            try {
                return KeyFactory.getInstance("RSA").generatePrivate(pKCS8EncodedKeySpec);
            } catch (InvalidKeySpecException e) {
                throw new InvalidKeySpecException("Neither RSA nor EC worked", e);
            }
        } catch (InvalidKeySpecException unused) {
            return KeyFactory.getInstance("EC").generatePrivate(pKCS8EncodedKeySpec);
        }
    }
}
