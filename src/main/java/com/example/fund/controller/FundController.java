package com.example.fund.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.json.pojo.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author lzw
 * Date  2023/1/13 13:51
 * Description
 */
@Controller
public class FundController {
    @RequestMapping(value = "/")
    public String index() {
        return "fund";
    }

    @RequestMapping(value = "/fund")
    @ResponseBody
    public Data fund(@RequestParam(value = "number") String number) {
        if (number.length() != 6) {
            return null;
        }
        String str = StrUtil.sub(number, 0, 3);
        if (StrUtil.containsAny(str, "000", "200", "002", "300", "580", "031")) {
            System.out.println("深圳交易所");
            number = "SZ" + number;
        } else {
            System.out.println("默认上交所");
            number = "SH" + number;
        }

        String result1 = HttpUtil.get("https://stock.xueqiu.com/v5/stock/realtime/quotec.json?symbol=" + number);
        Data data = JSONUtil.toList(JSONUtil.parseArray(JSONUtil.parseObj(result1).getStr("data")), Data.class).get(0);
        return data;
    }
}

