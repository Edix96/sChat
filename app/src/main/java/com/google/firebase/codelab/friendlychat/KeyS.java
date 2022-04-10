package com.google.firebase.codelab.friendlychat;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;



public class KeyS {

    public KeyStore ks;


    public static void KeySt (String palavra) throws KeyStoreException {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());

        // get user password and file input stream
        char[] password = palavra.toCharArray();





        try (FileInputStream fis = new FileInputStream("keyStoreName")) {
            ks.load(fis, password);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }




    }

    private final void load(FileInputStream fis, char[] password) throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException {


        KeyStore.PasswordProtection protParam = new KeyStore.PasswordProtection(password);
        // get my private key
        KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry("privateKeyAlias", protParam);
        PrivateKey myPrivateKey = pkEntry.getPrivateKey();

        // save my secret key
        javax.crypto.SecretKey mySecretKey = null;
        KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(mySecretKey);
        ks.setEntry("secretKeyAlias", skEntry, protParam);

        // store away the keystore
        try (FileOutputStream fos = new FileOutputStream("newKeyStoreName")) {
            ks.store(fos, password);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }

}

