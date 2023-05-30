package org.sample;

import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jwt.EncryptedJWT;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class DecryptIDToken {
    public static void main(String[] args) throws Exception {

        KeyStore keyStore = KeyStore.getInstance("JKS");
        FileInputStream fileInputStream = new FileInputStream("/Users/krishanc/Documents/GitHub/Decryptor/src/org/sample/wso2carbon.jks");
        char[] password = "wso2carbon".toCharArray();
        keyStore.load(fileInputStream, password);
        fileInputStream.close();
        // Get the private key and certificate chain from the keystore
        String alias = "wso2carbon";
        String keyPassword = "wso2carbon";
        KeyStore.PasswordProtection keyProtection = new KeyStore.PasswordProtection(keyPassword.toCharArray());
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, keyProtection);
        RSAPrivateKey PrivateKey = (RSAPrivateKey) privateKeyEntry.getPrivateKey();
        Certificate[] certChain = privateKeyEntry.getCertificateChain();

        // Extract the public key from the certificate
        Certificate cert = certChain[0];
        RSAPublicKey publicKey = (RSAPublicKey) cert.getPublicKey();


        // Enter encrypted JWT String here.
        String encryptedJWTString = "eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.QZFDDUFzdGyw2inAq45QEUGyKt7-GpRJJ9SqRSyvRKLEJLcg2jbKLBBKDCNdru9ZqAZpw21ERkIikQxnt-baxSuFbRS5F6XEEMfDI2zkZSrF7dAjOjy2w_Ai54QH2ghFOMqaOFuP1BPWy2K0P8ehg-yaAnPMsrUnqWJpnvBNagGLYJRh7wa96KUCvH_-hFZiKwrRVLmQqI5vFsSwHGCAPxlQzJ_7aD55j7gFbYh6RZ_Psn_Es9zlbo3ae0mEKl38Hn2mtpt2XME17h64sbq3ajHZ68fOa13_mM0NZFr66C67DAfnI5IyYDWVoGhAdBNo41IkYMPSG7wuGkCSCEDPXg.xveN6xYKl7hkB2im.p2THY1Y7dDVKzsMEeKtPbbcf1lrsB4yomPIifwk0jc2y3Y5LM8bi_jHIM4RdYy4kOb9hpXZDX5hvPX3Xbuh6Zx0s4jWNMjhXReWtmg4KyUuUpLm48vM81SEjU64utynBf2YgD1pGJmdRGtSJphotPtrcSloPCW51kR5DTujx31yR2P26-6_Pe3ddQnv1ie0M-9sOPojYnv5c_-p8xJfNTtjfycmNh7EMraaH86NpNuAW9h26ZazKfe1xxESEHqg-qfVNt-jZzxCgyrwNn2utPtNiCHyuf-wwvNjU8kFHky4DNPJ5WJlU923e-yYbPtMNNm8eAe8nUG7AUnWkv-fq1EqyP6BObP7W_OZB6x1uAFZ5d_tyYMqoUxyLljtRCfJ33_l8KOEmAmSwXulKVvi4gHZy7vPBpCNNwA6U6C_JeGFVCg4IzNT6RdiKKVF8sEsqSYkkSk2uTsu9_y75xgDc0Ebx5F0xIzYteFeUTbM-Vs0g25q0RMIUnBK35pQoZokn0-aNOfAn_CL9R4XKlagZ4BG_sQ6HyOjf5Ui9Y9AOQ99GjANSUygmk0ivFHawx0u8XLFVaKwOe1Mbh5McNHuMV3dECX2VdyM_QS2hOavM4KAJ9Fnp3YHme_nqRKbuvnvDGAeAoSv-WF8LY_GDidUEkZOyPxQrFOyhseC2jcaiU7mES6diPaueQ3yeB6TnNX3JNGfNT5Reir6z2Mi5Gv3_jWEzCVZouyHSd0IFWoZJqIRiXyAm63IwmMsuP4VFL1dFizL0LColZic7Vdwkr4W-QozyrUmbSyWy2ntZcw21eK-lTKDZBkxVJWXe9Zb4sx3J5pilPFlLTtj8egLwkVu354wGu8e1XmyEeQH2tEyro4lfvT7WzSJoYEOYWulaSP3UWRU40LTT_V6AWA2IWlsixr9GuJDfYund5FEyrunqm7ojfmVxgM52-0exwPuN-T49Gw2kai7Y31DRpT1ZqJROL5UJk4mi6Vb7bWMhb0PC-e9enWlDBYGtX7ulNIhBR9VK87ZB5l659kynAMZ8CrwPrasv-dsDOsaK_PEWdeUbx0GGm80JbOzpAeYEnrHzedfoNIdQs9ecK5QrUui9rYiUiC3f-w24EGl320-g8GayH0PGMpq9zHFB0SfaPjuR-oxa9kn1x-lfeqlHGm0R21BIaJIiN2Bp7B8NWMPv7OB_jFEyWWhqNZUEDQ0evkMRI-evvDA2U3gW9GtQ_ap-7Zb-78G29mdgL9tfxyJSf8xbYvLbGfWiI0cacCA-1WubwBrX6RTLiibMJZcn8JPC27ZXRC3FIcBo85s7JJ3qjL-8gjhLNaUnDHDX5ieZYb8sFGUUIRFMuIkZDHB1u_-FZETiQcLXxMoiQS6-sXH8DVVVmMaahtz763Pfnh3ilSmZPZ8XUkvn4Qbm25RbWU3FISjlOYk_1Cm7K0Z6rL1kNGEcb1O8jFjHuhWa7989jCnuuT73yxyduyfU1yBoprBCzWy-7zL6meSuTqcbj7Bb-g9LBoVVQ8e4XmZMXQPnmyFPzQoveDrIriLg3IHVmqsDwrIF63YWmWxzHvoczMt8CV5yYiX--BXSXteTa8fua1rqh82xFLFcmFv4S8PyV1hFUhVTD3i8WqHTCT6PfHb-KwWS2sIPqARi8ILAXx5HHDyCrwaBJq2e78GSQhH785aGutcwSDlBrhmSplsNmkQghJf2bhRB4Oi6-BRxJAgOXr2J8NjyD-jXo52TTWKU5kPlRsZyoDBpjZ9RQP2ssxdc_TQsKQdzHeSNdIoD6rpwY7qWkhnbv69Re0oIPTpPvAJ_7_UW7NvUDFu56xN3.U1nq0A6rc8mdTdb1iL2s3w";

        EncryptedJWT jwt = EncryptedJWT.parse(encryptedJWTString);

        // Create a decrypter with the specified private RSA key.
        RSADecrypter decrypter = new RSADecrypter(PrivateKey);

        jwt.decrypt(decrypter);

       System.out.println(jwt.getPayload());
    }

}