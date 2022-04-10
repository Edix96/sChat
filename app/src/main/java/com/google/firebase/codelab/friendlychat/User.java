package com.google.firebase.codelab.friendlychat;

public class User {

    public String name, email, nonce, nextnonce, cnonce;

    public User(){

    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;

    }

    public User(String name, String email, String nonce, String nextnonce, String cnonce) {
        this.name = name;
        this.email = email;
        this.nonce = nonce;
        this.nextnonce = nextnonce;
        this.cnonce = cnonce;
    }



    public String getNouce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getNextnonce() {
        return nextnonce;
    }

    public void setNextnonce(String nextnonce) {
        this.nextnonce = nextnonce;
    }

    public String getCnonce() {
        return cnonce;
    }

    public void setCnonce(String cnonce) {
        this.cnonce = cnonce;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", nonce='" + nonce + '\'' +
                ", nextnonce='" + nextnonce + '\'' +
                ", cnonce='" + cnonce + '\'' +
                '}';
    }
}
