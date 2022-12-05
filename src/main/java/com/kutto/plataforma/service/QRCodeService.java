package com.kutto.plataforma.service;

public interface QRCodeService {

    byte[] generateQRCode(String qrContent, int width, int height);
}
