/**
 * File Name:VerfyCodeUtil.java
 * Copyright (c) 2014, doubibi All Rights Reserved.
 */
package com.tofly.youke.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Description:<br/>
 *
 * @author salman
 * @version 0.0.1
 * @date 2014年3月25日 下午9:17:33
 * @since JDK 1.6
 *
 * History:2014年3月25日 salman 初始创建
 */
public class VerfyCodeUtil {

    /**
     * 纯数字
     */
    public final static String DIGIT = "digit";
    /**
     * 纯字母
     */
    public final static String CHARACTER = "character";
    /**
     * 字母+数字
     */
    public final static String DIGIT_CHARACTER = "digit_character";

    /***
     * random:(随机生成验证码字符串). <br/>
     *
     * @author salman
     * @param codeType 生成验证码类型
     * @return String
     */
    public static String random(String codeType, int codeLength) {

        // 因为o0,l1很难区分,所以,去掉o,l,y,v,0,1
        String digit = "123456789";
        String character = "ABCDEFGHIJKLMNPQRSTUVWXYZ";
        String digitCharacter = "23456789ABCDEFGHIJLKMNPQRSTUVWXYZ";// 初始化种子

        if (DIGIT.equals(codeType)) {
            return RandomStringUtils.random(codeLength, digit);// 返回length位的字符串
        } else if (CHARACTER.equals(codeType)) {
            return RandomStringUtils.random(codeLength, character);// 返回length位的字符串
        } else {
            return RandomStringUtils.random(codeLength, digitCharacter);// 返回length位的字符串  
        }
    }

    /**
     * createImage:(根据要求的数字生成图片,背景为白色,字体大小16,字体颜色黑色粗体，字符长度不能超过6个)
     *
     * @param outString 要生成的字符
     * @param out       输出流
     */
    public static void createImage(String outString, int width, int height, OutputStream out) throws IOException {
        if (outString.getBytes().length > 6) {
            throw new IllegalArgumentException("The length of param char cannot exceed 6.");
        }

        // 在内存中创建图象
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();

        // 画边框
        java.util.Random random = new java.util.Random();
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0, 0, width, height);

        // 设置字体
        Font mFont = new Font("Tahoma", Font.BOLD, 16);
        graphics2D.setFont(mFont);
        // 设置字体颜色
        graphics2D.setColor(Color.BLACK);

        // 画认证码,每个认证码在不同的水平位置
        String[] itemOut = new String[outString.length()];
        for (int i = 0; i < itemOut.length; i++) {
            itemOut[i] = outString.substring(i, i + 1);
            int w = 0;
            int x = (i + 1) % 3;

            // 随机生成验证码字符的水平偏移量
            if (x == random.nextInt(3)) {
                w = 19 - random.nextInt(outString.length() + 1);
            } else {
                w = 19 + random.nextInt(outString.length() + 1);
            }

            // 随机生成颜色
            Color color = new Color(random.nextInt(180), random.nextInt(180), random.nextInt(180));
            graphics2D.setColor(color);
            graphics2D.drawString(itemOut[i], 12 * i + 4, w);
        }

        // 随机产生干扰点,并用不同的颜色表示，使图象中的认证码不易被其它程序探测到
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            graphics2D.setColor(color); // 随机画各种颜色的点

            graphics2D.drawOval(x, y, 0, 0);
        }

        // 画干扰线
        for (int i = 0; i < 0; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            Color color1 = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            // 随即画各种颜色的线
            graphics2D.setColor(color1);
            graphics2D.drawLine(x, y, x1, y1);
        }
        // 图像生效
        graphics2D.dispose();
        /*
        // 生成波纹效果
        double dMultValue = 3;// 波形的幅度倍数，越大扭曲的程序越高，一般为3  
        double dPhase = random.nextInt(6);// 波形的起始相位，取值区间（0-2＊PI）  
        BufferedImage destBi = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);  
        for (int i = 0; i < destBi.getWidth(); i++) {  
            for (int j = 0; j < destBi.getHeight(); j++) {  
                int nOldX = getXPosition4Twist(dPhase, dMultValue,destBi.getHeight(), i, j);  
                int nOldY = j;  
                if (nOldX >= 0 && nOldX < destBi.getWidth() && nOldY >= 0 && nOldY < destBi.getHeight()) {  
                    destBi.setRGB(nOldX, nOldY, bufferedImage.getRGB(i, j));  
                }  
            }  
        }  
        */
        // 输出页面
        ImageIO.write(bufferedImage, "jpg", out);
        out.close();
    }


    /**
     * @return int
     * @Description:获取扭曲后的x轴位置
     */
    @SuppressWarnings("unused")
    private static int getXPosition4Twist(double dPhase, double dMultValue, int height, int xPosition, int yPosition) {
        // 此值越大，扭曲程度越大
        double PI = 3.1415926535897932384626433832799;
        double dx = PI * yPosition / height + dPhase;
        double dy = Math.sin(dx);
        return xPosition + (int) (dy * dMultValue);
    }

    public static void main(String[] args) throws IOException {
        String num = random(VerfyCodeUtil.DIGIT_CHARACTER, 6);
        System.out.println(num);
        createImage(num, 80, 30, new FileOutputStream("/data/123.jpg"));
        System.out.println("Image generated.");
    }
}
