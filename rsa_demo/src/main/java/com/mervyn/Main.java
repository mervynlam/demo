package com.mervyn;

import com.mervyn.utils.RsaUtils;

public class Main {
    public static void main(String[] args) {
//        RsaUtils.generateKeyPair();
//        String source = "14、管理员导入真实考试试题时，显示的是加密的内容（编码处理），管理员只能导入数据，无法查看具体内容。每套试题增加一个试题说明，这个内容可以查看，不加密。14、管理员导入真实考试试题时，显示的是加密的内容（编码处理），管理员只能导入数据，无法查看具体内容。每套试题增加一个试题说明，这个内容可以查看，不加密。";
//        System.out.println("源文: " + source);
//        String encryptStr = RsaUtils.encrypt(source);
//        System.out.println("加密: " + encryptStr);
//        String decryptStr = RsaUtils.decrypt(encryptStr);
//        System.out.println("解密: " + decryptStr);

        RsaUtils.generateKeyPair();
        String source = "既然如此， 了解清楚科学和人文谁更有意义到底是一种怎么样的存在，是解决一切问题的关键。 奥普拉·温弗瑞曾经提到过，你相信什么，你就成为什么样的人。这句话语虽然很短，但令我浮想联翩。 伏尔泰曾经说过，坚持意志伟大的事业需要始终不渝的精神。这不禁令我深思。 要想清楚，科学和人文谁更有意义，到底是一种怎么样的存在。 一般来讲，我们都必须务必慎重的考虑考虑。 我们不得不面对一个非常尴尬的事实，那就是， 这样看来。";
        System.out.println("源文: " + source);
        String encryptStr = RsaUtils.encryptSegment(source);
        System.out.println("加密: " + encryptStr);
        String decryptStr = RsaUtils.decryptSegment(encryptStr);
        System.out.println("解密: " + decryptStr);
    }
}
