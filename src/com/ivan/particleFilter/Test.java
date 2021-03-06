package com.ivan.particleFilter;

import java.util.Random;

/**
 * Created by hyf on 2017/11/21.
 */
public class Test {
    public static void main(String args[]) throws Exception{
        int particleNum = 100;   //ç»®æçéï¿?
        int stepNum = 20;
        int positionNum = stepNum + 1;  //æµ£å¶çéï¿?
        float stepLength = 1f;  //éªç·çå§ã©æ±
        float stepLengthBias = 0.1f;    //å§ã©æ±é¥åç¾éå¿æ¨?
        float stepOrientation = 0f; //éªç·çéç°æ
        float stepOrientationBias = 0f;     //éç°æé¥åç¾éå¿æ¨?
        float Fnoise = 0.1f;    //å§ã©æ±çîæ¨
        float Tnoise = 0.1f;    //éç°æçîæ¨
        float Snoise = 0.1f;    //é²å¿ç¥´çîæ¨
        Random random = new Random();

        //éæ¿îéæ «ç²¡ç»®æçå©ãå°éåº£æ®æã¨æ
        Point[] afterPFTrack = new Point[positionNum];
        for (int i = 0; i < positionNum; i ++){
            afterPFTrack[i] = new Point();
        }

        //éæ¿îéæ DRå¯°æ¥åé¨å¬îé?ååºå¨´å¬ªï¿½çæ°éç°æé²å¿ç¥´éï¿?
        float[] stepLengths = new float[stepNum];   //PDRå§ã©æ±é²å¿ç¥´éï¿½
        float[] stepOrientations = new float[stepNum];  //PDRéç°æé²å¿ç¥´éï¿½
        for (int i = 0; i < stepNum; i ++){
            stepLengths[i] = stepLength + stepLengthBias + Fnoise * (float) random.nextGaussian();
            stepOrientations[i] = stepOrientation + stepOrientationBias + Tnoise * (float) random.nextGaussian();
        }

        //éæ¿îéæ «æ¹¡ç¹ç¶ç¹éã¨å»ºæ©ç°æ°é²å¿ç¥´æ©æ¬å§©æã¨æ
        Point startPosition = new Point(0,2);   //çå±¼æ±éæ¿îæµ£å¶ç?
        Point tmpPosition = startPosition;
        Point[] trueTrack = new Point[positionNum];
        Point[] measurementTrack = new Point[positionNum];
        for (int i = 0; i < positionNum; i ++){
            //éªç·çæã¨æç§å¬ªï¿½ç¡·ç´å¨ç¸³æå­îéç°ææ©æ¬å§©
            trueTrack[i] = new Point(tmpPosition.x,tmpPosition.y);
            //é²å¿ç¥´æã¨æç§å¬ªï¿½ï¿½
            measurementTrack[i] = new Point(tmpPosition.x + Snoise * (float) random.nextGaussian(),
                    tmpPosition.y + Snoise * (float) random.nextGaussian());
            tmpPosition.x += stepLength;
        }

        //ç»®æçç¼ãåµæ¿®å¬ªå¯²éå±½èçå§çéîî¨é£îï¼?
        ParticleFilter filter = new ParticleFilter(particleNum,measurementTrack[0]);
        filter.setNoise(Fnoise,Tnoise,Snoise);
        afterPFTrack[0].x = filter.getAverageParticle().x;
        afterPFTrack[0].y = filter.getAverageParticle().y;
        //å¯®ï¿½æ¿®å¬­ç¹éï¿?
        for (int i = 1; i < positionNum; i ++){
            filter.move(stepOrientations[i-1],stepLengths[i-1]);    //ç»®æçæ©æ¶îç»è¯²å§?
            filter.resample(measurementTrack[i]);
            afterPFTrack[i].x = filter.getAverageParticle().x;
            afterPFTrack[i].y = filter.getAverageParticle().y;
        }
        System.out.println("haha");
    }
}
