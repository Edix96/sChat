/**
 * Copyright Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.firebase.codelab.friendlychat;

public class FriendlyMessage {

    private String id;
    private String textHmac;
    private String textAES;
    private String name;
    private String photoUrl;
    private String imageUrl;

    public FriendlyMessage() {
    }

    public FriendlyMessage(String name, String photoUrl, String imageUrl, String textHmac, String textAES) {
        this.textHmac = textHmac;
        this.textAES = textAES;
        this.name = name;
        this.photoUrl = photoUrl;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHmac(String textHmac) {
        this.textHmac = textHmac;
    }

    public void setTextAES(String textAES) {
        this.textAES = textAES;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getTextHmac() {
        return textHmac;
    }

    public String getTextAES() {
        return textAES;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String toString() {
        String s = "Nome:"+getName()+"\n HMAC: "+getTextHmac()+"\n Photo: " +getPhotoUrl()+"\n Imagem: "+getImageUrl()+"\n AES/CBC: "+getTextAES()+"|";
        return s;
    }
}
