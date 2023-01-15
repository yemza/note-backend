package com.note.configuration;


import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class GenerateKayPair {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair=keyPairGenerator.generateKeyPair();/*i change code here*/
        byte[] pub = keyPair.getPublic().getEncoded();
        byte[] pri = keyPair.getPrivate().getEncoded();
        PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\hamza.elbouazzaoui\\Desktop\\Java-Workspace\\note\\WebModule\\src\\main\\resources\\keys\\pub.pem")));
        PemObject pemObject=new PemObject("PUBLIC KEY",pub);
        pemWriter.writeObject(pemObject);
        pemWriter.close();
        PemWriter pemWriter2 = new PemWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\hamza.elbouazzaoui\\Desktop\\Java-Workspace\\note\\WebModule\\src\\main\\resources\\keys\\pri.pem")));
        PemObject pemObject2=new PemObject("PRIVATE KEY",pri);
        pemWriter2.writeObject(pemObject2);
        pemWriter2.close();
    }
}