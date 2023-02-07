package com.example.fund;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FundApplicationTests {

    @Test
    void contextLoads() {
        String number = "510500";
        System.out.println("长度位："+number.length());
        System.out.println("是否位数字："+NumberUtil.isNumber(number));
        String str = StrUtil.sub(number, 0, 3);
        if(StrUtil.containsAny(str, "600","601","603", "900","688")){
            System.out.println("上海交易所");
        }else if(StrUtil.containsAny(str, "000","200","002", "300","580","031")){
            System.out.println("深圳交易所");
        }else{
            System.out.println("默认上交所");
        }
    }

}
