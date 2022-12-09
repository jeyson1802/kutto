package com.kutto.plataforma.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ImageUtil {

    public String getImgData(byte[] byteData) {

        if(StringUtil.isEmpty(byteData)) {
            return null;
        }
        return new String(byteData, StandardCharsets.UTF_8);
    }
}
